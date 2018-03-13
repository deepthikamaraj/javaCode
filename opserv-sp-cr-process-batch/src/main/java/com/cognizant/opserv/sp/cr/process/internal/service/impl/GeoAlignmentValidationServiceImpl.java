package com.cognizant.opserv.sp.cr.process.internal.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.opserv.sp.cr.process.dto.CustomerAlignmentDTO;
import com.cognizant.opserv.sp.cr.process.dto.ErrorCode;
import com.cognizant.opserv.sp.cr.process.dto.ErrorDTO;
import com.cognizant.opserv.sp.cr.process.dto.GeographyAlignmentDTO;
import com.cognizant.opserv.sp.cr.process.dto.MirrorCustAlgmtDTO;
import com.cognizant.opserv.sp.cr.process.dto.MirrorGeoAlgmtDTO;
import com.cognizant.opserv.sp.cr.process.internal.service.CustomerAlignmentValidationService;
import com.cognizant.opserv.sp.cr.process.internal.service.GeoAlignmentValidationService;
import com.cognizant.opserv.sp.exception.AffiliationServiceException;
import com.cognizant.opserv.sp.exception.AlignmentServiceException;
import com.cognizant.opserv.sp.exception.AlignmentServiceException.AlignmentServiceExceptionCode;
import com.cognizant.opserv.sp.exception.SalesPositionServiceException;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.persistence.dao.service.CustomerAffiliationDAOService;
import com.cognizant.opserv.sp.persistence.dao.service.GeographyAlignmentDAOService;
import com.cognizant.opserv.sp.service.common.CustomerAlignmentCommonService;
import com.cognizant.opserv.sp.service.common.SalesPositionCommonService;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;

@Service
public class GeoAlignmentValidationServiceImpl implements
		GeoAlignmentValidationService {
	
	@Autowired
	private CustomerAlignmentValidationService custAlgmtValidService;
	
	@Autowired
	private SalesPositionCommonService spCommonService;
	
	@Autowired
	private GeographyAlignmentDAOService geographyAlignmentDAOService;
	
	@Autowired
	private CustomerAffiliationDAOService customerAffiliationService;
	
	@Autowired
	private CustomerAlignmentCommonService customerAlignmentCommonService;
	
	
	
	private static final OpservLogger LOGGER = OpservLoggerFactory
			.getOpservLogger(GeoAlignmentValidationServiceImpl.class);
	@Override
	public void validateAffiliatedGeoCustomerAlignment(
			GeographyAlignmentDTO geoAlgmtDTO, UserDetails userDetails) throws AlignmentServiceException{
		try{
		Iterator<CustomerAlignmentDTO> custAlgIterator = geoAlgmtDTO.getCustAlgmtDTOs().iterator();
		//Process affiliated customers for source customers
		while(custAlgIterator.hasNext()){
			CustomerAlignmentDTO custAlgmtDTO = custAlgIterator.next();
			LOGGER.info("ZIP MOVEMENT >> Customer Movement Started");
			try {
				custAlgmtValidService.validateAffiliatedCustomerAlignment(custAlgmtDTO, userDetails);
			} catch (AffiliationServiceException exception) {
				LOGGER.error("Exception propagated from  CustomerAffiliationCommonServiceImpl.getAffiliatedAccountCustomers()");
				ErrorDTO errorDTO = new ErrorDTO();
				errorDTO.setErrorCode(ErrorCode.ERROR_ZM_2027);
				errorDTO.addParam(geoAlgmtDTO.getOfflineReqId().toString(),exception.getMessage());
				geoAlgmtDTO.addErrorDTO(errorDTO);
			}
		}
		}catch (AlignmentServiceException ex) {
			Object[] obj = new Object[] {};
			LOGGER.error("=====================Error while validateAffiliatedGeoCustomerAlignment=========================");
			throw new AlignmentServiceException(
					AlignmentServiceExceptionCode.ALGN_SER_EX_CD_323,
					"Error while validateAffiliatedGeoCustomerAlignment",
					obj, ex);
		}
		/*catch (AffiliationServiceException ex) {
			Object[] obj = new Object[] {};
			LOGGER.error("=====================Error while validateAffiliatedGeoCustomerAlignment=========================");
			throw new AlignmentServiceException(
					AlignmentServiceExceptionCode.ALGN_SER_EX_CD_323,
					"Error while validateAffiliatedGeoCustomerAlignment",
					obj, ex);
		}*/
	}

	@Override
	public void validateMirrorsForGeoAlignment(
			GeographyAlignmentDTO geoAlgmtDTO, UserDetails userDetails) throws AlignmentServiceException, SalesPositionServiceException {

		//zip movement mirror validations.. 
		
		//Source Mirrored SP
		Map<Long,SalesPosition> sourceSPMap = new HashMap<Long,SalesPosition>();
		Map<Long,SalesPosition> targetSPMap = new HashMap<Long,SalesPosition>();
		
		LOGGER.info("======== Find the mirror SPs of source and target based on ZIP ==== Started ======");
		//Find the mirror SPs of source and target based on ZIP
		List<SalesPosition> mirrorSourceSPs = spCommonService.getMirroredSalesPosition(geoAlgmtDTO.getSourceGeoAlgmt().getSalesPosition(), userDetails);
		LOGGER.info("======== Find the mirror SPs of source and target based on ZIP ==== Ended ======");
		
		if(null != mirrorSourceSPs && !mirrorSourceSPs.isEmpty()){
			//Filter out SPs in which the the base zip doesnt exist.
			//Validate only valid mirrorSP 
			LOGGER.info("======== Validate only valid mirrorSP, Filter out SPs in which the the base zip doesnt exist. ==== Started ======");
			mirrorSourceSPs =  geographyAlignmentDAOService.filterActiveSPsContaingZip(mirrorSourceSPs, geoAlgmtDTO.getSourceGeoAlgmt().getPostalCode().getCode(),userDetails);
			LOGGER.info("======== Validate only valid mirrorSP ==== Ended ======");
			
			LOGGER.debug("ZIP MOVEMENT >> Mirror Source SPs"+mirrorSourceSPs.toString());
			

			//Populate   srcSp in mirror
			for(SalesPosition sp:mirrorSourceSPs){
				MirrorGeoAlgmtDTO mirror = new MirrorGeoAlgmtDTO();
				mirror.setSourceSP(sp);			
				//Populate sourceSPMap<algmntId, SP>
				if(sourceSPMap.get(sp.getAlignmment().getId()) == null ){
					sourceSPMap.put(sp.getAlignmment().getId(), sp);
					LOGGER.info("======== Setting Source mirror geography alignment==== Started ======");
					mirror.setSourceMirrorGeoAlgmt(geographyAlignmentDAOService.getSourceMirrorGeographyAlignment(mirror.getSourceSP(), geoAlgmtDTO.getSourceGeoAlgmt().getPostalCode().getCode(), userDetails.getTenantId()));
					LOGGER.info("======== Setting Source mirror geography alignment==== Ended ======");
				}else{
					//if multiple source mirrors containing zip in a particular alignment, throw validation error
					LOGGER.error("Multiple source mirrors containing zip in a particular alignment . Alignment _id : "+sp.getAlignmment().getId());
					ErrorDTO errorDTO = new ErrorDTO();
					errorDTO.setErrorCode(ErrorCode.ERROR_ZM_2020);
					errorDTO.addParam(sp.getAlignmment().getId().toString(),"Multiple source mirrors containing zip in a particular alignment");
					geoAlgmtDTO.addErrorDTO(errorDTO);
				}
				geoAlgmtDTO.addMirrorDTO(mirror);
			}
		}
		
		
		
		// Getting Mirrored SP for Target
		LOGGER.info("======== Getting all Mirrored SP for Target ==== Started ======");
		List<SalesPosition> mirrorTargetSPs = spCommonService.getMirroredSalesPosition(geoAlgmtDTO.getTargetGeoAlgmt().getSalesPosition(), userDetails);
		LOGGER.info("======== Getting all Mirrored SP for Target ==== Ended ======");
		
		if(null != mirrorTargetSPs && !mirrorTargetSPs.isEmpty()){
			// Map of Mirror SP which are Primary. Used to map Source mirror and Target Mirror
			Iterator<SalesPosition> mirrorTrgtSPsIter = mirrorTargetSPs.iterator();
			while(mirrorTrgtSPsIter.hasNext()){
				SalesPosition mirrorTargetSP = mirrorTrgtSPsIter.next();
				if(mirrorTargetSP.isPrimaryMirror()) {
					if(targetSPMap.get(mirrorTargetSP.getAlignmment().getId()) == null ){ //to check for multiple primary mirrors in a particular alignment
						targetSPMap.put(mirrorTargetSP.getAlignmment().getId(), mirrorTargetSP);
					}else{
						//if multiple primary target mirrors present in a particular alignment, throw validation error
						LOGGER.error("Multiple primary target mirrors present in a particular alignment . Alignment _id : "+mirrorTargetSP.getAlignmment().getId());
						ErrorDTO errorDTO = new ErrorDTO();
						errorDTO.setErrorCode(ErrorCode.ERROR_ZM_2019);
						errorDTO.addParam(mirrorTargetSP.getAlignmment().getId().toString(),"Multiple primary target mirrors present in a particular alignment");
						geoAlgmtDTO.addErrorDTO(errorDTO);
					}
				}else{
					//Mirror target SP is not primary and shouldn't be considered.
					LOGGER.info("Mirror target SP :"+ mirrorTargetSP.getId() +" is not primary and shouldn't be considered.");
					mirrorTrgtSPsIter.remove();
				}

			}
			
			//Set target mirror SPs
			if(null!=geoAlgmtDTO.getMirrors()){
				for(MirrorGeoAlgmtDTO mirror : geoAlgmtDTO.getMirrors()) {
					Long srcAlgmntId = mirror.getSourceSP().getAlignmment().getId();
					SalesPosition mirrTargetSP = targetSPMap.get(mirror.getSourceSP().getAlignmment().getId());
//					mirror.setSourceMirrorGeoAlgmt(geographyAlignmentDAOService.getSourceMirrorGeographyAlignment(mirror.getSourceSP(), geoAlgmtDTO.getSourceGeoAlgmt().getPostalCode().getCode(), userDetails.getTenantId()));
					if(null != mirrTargetSP){
						mirror.setTargetSP(targetSPMap.get(srcAlgmntId));
					}else{
						//primary target mirror not present for the particular alignment.
						LOGGER.error("Source mirror present for an alignment but primary target mirror not present"+srcAlgmntId);
						ErrorDTO errorDTO = new ErrorDTO();
						errorDTO.setErrorCode(ErrorCode.ERROR_ZM_2021);
						errorDTO.addParam(srcAlgmntId.toString(),"Source mirror present for an alignment but primary target mirror not present");
						geoAlgmtDTO.addErrorDTO(errorDTO);
					}
				}
			}
		}
		LOGGER.info("======== Set only valid mirrorSP for target ==========");
		
		LOGGER.info("======== Populate mirrors for each base customer ===== Started =====");
		//Populate mirrors for each base customer
		if(null!=geoAlgmtDTO.getCustAlgmtDTOs()){
			for(CustomerAlignmentDTO custAlgmtDTO:geoAlgmtDTO.getCustAlgmtDTOs()){
				for(Long key: sourceSPMap.keySet()){
					MirrorCustAlgmtDTO mirror = new MirrorCustAlgmtDTO();
					mirror.setSourceSP(sourceSPMap.get(key));
					mirror.setTargetSP(targetSPMap.get(key));
					custAlgmtDTO.addMirrorDTO(mirror);
				}		
				custAlgmtValidService.validateMirrorsForCustomerAlignment(custAlgmtDTO, userDetails);
			}
		}
		LOGGER.info("======== Populate mirrors for each base customer ===== Ended =====");

	}
}
