package com.cognizant.opserv.sp.cr.process.internal.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.opserv.sp.common.CommonConstants;
import com.cognizant.opserv.sp.common.StatusType;
import com.cognizant.opserv.sp.cr.process.internal.service.GeoAlignmentService;
import com.cognizant.opserv.sp.exception.AlignmentServiceException;
import com.cognizant.opserv.sp.exception.AlignmentServiceException.AlignmentServiceExceptionCode;
import com.cognizant.opserv.sp.model.GeographyAlignment;
import com.cognizant.opserv.sp.model.PostalCode;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.persistence.dao.service.GeographyAlignmentDAOService;
import com.cognizant.peg.core.exception.OpservDataAccessException;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;

@Service
public class GeoAlignmentServiceImpl implements GeoAlignmentService {

	
	@Autowired
	private GeographyAlignmentDAOService geoAlignmntCommonDAOService;
	
	private static final OpservLogger LOGGER = OpservLoggerFactory
			.getOpservLogger(GeoAlignmentServiceImpl.class);
	@Override
	public void lockGeographyAlignment(GeographyAlignment geoAlgmnt,
			UserDetails userDetails) throws AlignmentServiceException {
		
		LOGGER.info("************************* Locking geography alignment ****************");
		try{
			if(null!=geoAlgmnt && null!=userDetails){
				geoAlignmntCommonDAOService.deactivateZipAlgmntOffline(geoAlgmnt, StatusType.PENDING_APPROVAL, CommonConstants.CHAR_YES, userDetails);	
			}
		}catch(OpservDataAccessException dataAccessException){
			LOGGER.error("Exception occurred while deactivating zip alignment offline based on salesPosition and postal code");
			throw new AlignmentServiceException(AlignmentServiceExceptionCode.ALGN_SER_EX_CD_401, "Exception occurred while deactivating zip alignment offline based on salesPosition and postal code", null, dataAccessException);
		}
	}

	@Override
	public void unLockGeographyAlignmentOnApprove(GeographyAlignment geoAlgmnt,
			UserDetails userDetails) throws AlignmentServiceException {
		LOGGER.info("************************* unlocking geography alignment on Reject****************");
		Integer geoId = null;
		if(null!=geoAlgmnt && null!=userDetails){
			 geoId = (int)geoAlgmnt.getPostalCode().getGeoCode();	
		}
		try{
			if(null!=geoId){
				geoAlignmntCommonDAOService.updateTTerrZipAlgmntStatus(geoAlgmnt.getSalesPosition().getId(),
						geoAlgmnt.getPostalCode().getCode(),geoAlgmnt.getSalesPosition().getHierarchy().getId(),
						geoId, StatusType.APPROVED.getId(),'Y',userDetails);
			}
		}catch(OpservDataAccessException dataAccessException){
			LOGGER.error("Exception occurred while updating territory zip alignment status as cancelled on reject");
			throw new AlignmentServiceException(AlignmentServiceExceptionCode.ALGN_SER_EX_CD_402, "Exception occurred while updating territory zip alignment status as cancelled on reject", null, dataAccessException);
		}
		
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW, rollbackFor = AlignmentServiceException.class)
	public void unLockGeographyAlignmentOnReject(GeographyAlignment geoAlgmnt,
			UserDetails userDetails)throws AlignmentServiceException {

		LOGGER.info("************************* unlocking geography alignment on Reject****************");
		Integer geoId = null;
		if(null!=geoAlgmnt && null!=userDetails){
			 geoId = (int)geoAlgmnt.getPostalCode().getGeoCode();	
		}
		try{
			if(null!=geoId){
				geoAlignmntCommonDAOService.updateTTerrZipAlgmntStatus(geoAlgmnt.getSalesPosition().getId(),
						geoAlgmnt.getPostalCode().getCode(),geoAlgmnt.getSalesPosition().getHierarchy().getId(),
						geoId, StatusType.APPROVED.getId(),'Y',userDetails);
			}
		}catch(OpservDataAccessException dataAccessException){
			LOGGER.error("Exception occurred while updating territory zip alignment status as cancelled on reject");
			throw new AlignmentServiceException(AlignmentServiceExceptionCode.ALGN_SER_EX_CD_402, "Exception occurred while updating territory zip alignment status as cancelled on reject", null, dataAccessException);
		}
		
		
		
	}
	@Override
	@Transactional
	public Integer createTargetGeoID(UserDetails userDetails) throws AlignmentServiceException{
		try{
			return geoAlignmntCommonDAOService.newTargetGeoID(userDetails);
		}catch(OpservDataAccessException dataAccessException){
			LOGGER.error("Error during fetching geo id");
			throw new AlignmentServiceException(AlignmentServiceExceptionCode.ALGN_SER_EX_CD_406, "Exception occurred while creating target geo id", null, dataAccessException);
		}
	}
	
	@Override
	@Transactional
	public Integer insertTargetZipAlgmnt(GeographyAlignment targetGeoAlign,
			PostalCode postalCd, UserDetails userDetails, Integer geoID)
			throws AlignmentServiceException {
		try{
			LOGGER.info("************************* inserting records for target zip alignment ****************");
			Integer geoZipId = null;
			if(null!=targetGeoAlign && null!=userDetails && null!=postalCd && null!=geoID){
				geoZipId = geoAlignmntCommonDAOService.insertTTerrZipAlgmnt(targetGeoAlign, postalCd, userDetails, geoID);
			}
			return geoZipId;
		}catch(OpservDataAccessException dataAccessException){
			LOGGER.error("Exception occurred while inserting target zip alignment.");
			throw new AlignmentServiceException(AlignmentServiceExceptionCode.ALGN_SER_EX_CD_403, "Exception occurred while inserting target zip alignment.", null, dataAccessException);
		}
	}
	@Override
	@Transactional
	public Map<Long, Integer> fetchExistingGeoIDs(SalesPosition salesPosition,
			UserDetails userDetails) throws AlignmentServiceException {
		
		try{
			Map<Long, Integer> existingGeoIds = null;
			LOGGER.info("************************* Fetching existing Geo unit ids ****************");
			if(null!=salesPosition && null!=userDetails){
				existingGeoIds = geoAlignmntCommonDAOService.getExistingGeoIDs(salesPosition, userDetails);
			}
			return existingGeoIds;
		}catch(OpservDataAccessException dataAccessException){
			LOGGER.error("Exception occurred while fetching existing Geo unit ids");
			throw new AlignmentServiceException(AlignmentServiceExceptionCode.ALGN_SER_EX_CD_404, "Exception occurred while fetching existing Geo unit ids", null, dataAccessException);
		}
	}

	@Override
	@Transactional
	public GeographyAlignment getGeographyAlgmnt(
			String postalCode, Integer geoZipId, long targetSPId,
			UserDetails userDetails) throws AlignmentServiceException {
		try{
			GeographyAlignment getGeographyAlgmnt =null;
			LOGGER.info("************************* Fetching geography alignments ****************");
			if(null!=postalCode && null!=geoZipId && null!=userDetails){
				getGeographyAlgmnt = geoAlignmntCommonDAOService.getGeoAlignment(postalCode, geoZipId,targetSPId,userDetails.getTenantId());
			}
			return getGeographyAlgmnt;
		}catch(OpservDataAccessException dataAccessException){
			LOGGER.error("Exception occurred while fetching geography alignments");
			throw new AlignmentServiceException(AlignmentServiceExceptionCode.ALGN_SER_EX_CD_405, "Exception occurred while fetching geography alignments", null, dataAccessException);
		}
		
	}

	
}
