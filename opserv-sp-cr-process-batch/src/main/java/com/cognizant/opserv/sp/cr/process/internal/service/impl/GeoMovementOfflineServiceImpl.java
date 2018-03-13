package com.cognizant.opserv.sp.cr.process.internal.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.opserv.sp.common.ChangeRequestTriggerType;
import com.cognizant.opserv.sp.common.MetricTriggerType;
import com.cognizant.opserv.sp.cr.process.dto.ErrorCode;
import com.cognizant.opserv.sp.cr.process.dto.ErrorDTO;
import com.cognizant.opserv.sp.cr.process.dto.GeographyAlignmentDTO;
import com.cognizant.opserv.sp.cr.process.dto.MirrorCustAlgmtDTO;
import com.cognizant.opserv.sp.cr.process.dto.MirrorGeoAlgmtDTO;
import com.cognizant.opserv.sp.cr.process.internal.service.ChangeRequestOfflineService;
import com.cognizant.opserv.sp.cr.process.internal.service.GeoAlignmentService;
import com.cognizant.opserv.sp.cr.process.internal.service.GeoMovementOfflineService;
import com.cognizant.opserv.sp.cr.process.internal.service.MetricOfflineService;
import com.cognizant.opserv.sp.cr.process.internal.service.OfflineRequestService;
import com.cognizant.opserv.sp.exception.AffiliationServiceException;
import com.cognizant.opserv.sp.exception.AlignmentServiceException;
import com.cognizant.opserv.sp.exception.AlignmentServiceException.AlignmentServiceExceptionCode;
import com.cognizant.opserv.sp.exception.ChangeRequestServiceException;
import com.cognizant.opserv.sp.exception.ChangeRequestServiceException.ChangeRequestServiceExceptionCode;
import com.cognizant.opserv.sp.exception.MetricExecutionException;
import com.cognizant.opserv.sp.exception.MetricViolationException;
import com.cognizant.opserv.sp.exception.SalesPositionServiceException;
import com.cognizant.opserv.sp.model.GeographyAlignment;
import com.cognizant.opserv.sp.model.PostalCode;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.model.cr.ChangeRequest;
import com.cognizant.opserv.sp.model.cr.ChangeRequestBusinessReason;
import com.cognizant.opserv.sp.model.cr.ChangeRequestDefinition;
import com.cognizant.opserv.sp.model.cr.ZipAlignmentChangeRequestDetails;
import com.cognizant.opserv.sp.model.metric.MetricResult;
import com.cognizant.opserv.sp.persistence.dao.service.MetricResultDAOService;
import com.cognizant.opserv.sp.service.common.ChangeRequestCommonService;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;

/**
 * ****************************************************************************.
 * 
 * @class GeoMovementOfflineService
 *        request
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 *        ************************************************************
 *        ***************
 */
@Service
public class GeoMovementOfflineServiceImpl implements GeoMovementOfflineService {
	
	/**
	 *LOGGER 
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory
			.getOpservLogger(GeoMovementOfflineServiceImpl.class);
	
	/**
	 * geoAlgmtService
	 */
	@Autowired
	private GeoAlignmentService geoAlgmtService;
		
	/**
	 * geoAlgmtValidService
	 */
	@Autowired
	private GeoAlignmentValidationServiceImpl geoAlgmtValidService;
	
	/**
	 * crCommonService
	 */
	@Autowired
	private ChangeRequestCommonService crCommonService;
	
	/**
	 * crOfflineService
	 */
	@Autowired
	private ChangeRequestOfflineService crOfflineService;
	
	/**
	 * offlineReqService
	 */
	@Autowired
	private OfflineRequestService offlineReqService;
	
	/**
	 * metricCommonService
	 */
	@Autowired
	private MetricResultDAOService metricResultDAOService;
	
	@Autowired
	private MetricOfflineService mtrOffServ;
	
	

	/** (non-Javadoc)
	 * @see com.cognizant.opserv.sp.cr.process.internal.service.GeoMovementOfflineService#lockRequestForProcessing(java.lang.Long, com.cognizant.opserv.sp.model.auth.UserDetails)
	 */
	@Override
	public void lockRequestForProcessing(Long offlineId, UserDetails userDetails) throws AlignmentServiceException {
		//TODO: error handling
		//Loock the offline id
		offlineReqService.lockRequestForProcessing(offlineId, userDetails);
		
	}

	/** (non-Javadoc)
	 * @throws AlignmentServiceException 
	 * @throws AffiliationServiceException 
	 * @see com.cognizant.opserv.sp.cr.process.internal.service.GeoMovementOfflineService#validate(com.cognizant.opserv.sp.cr.process.dto.GeographyAlignmentDTO, com.cognizant.opserv.sp.model.auth.UserDetails)
	 */
	@Override
	@Transactional(readOnly=true)
	public void validate(GeographyAlignmentDTO geoAlgmtDTO,
			UserDetails userDetails) throws AlignmentServiceException, SalesPositionServiceException {
		//TODO: error handling		

		if(null != geoAlgmtDTO.getCustAlgmtDTOs()  && !geoAlgmtDTO.getCustAlgmtDTOs().isEmpty()){
			LOGGER.info("======== Validating Affiliated customer for geoAlignmnet ==== Started ======");
			geoAlgmtValidService.validateAffiliatedGeoCustomerAlignment(geoAlgmtDTO, userDetails);
			LOGGER.info("======== Validating Affiliated customer for geoAlignmnet ==== Ended ======");
		}else{
			LOGGER.info("=======ZIP MOVEMENT >> no customers to move======");
		}
		LOGGER.info("======== Validating mirrors for zip movement ==== Started ======");
		//Validating mirrors for zip movement
		geoAlgmtValidService.validateMirrorsForGeoAlignment(geoAlgmtDTO, userDetails);
		LOGGER.info("======== Validating mirrors for zip movement ==== Ended ======");
		
	}

	/** (non-Javadoc)
	 * @see com.cognizant.opserv.sp.cr.process.internal.service.GeoMovementOfflineService#lockSourceGeography(com.cognizant.opserv.sp.cr.process.dto.GeographyAlignmentDTO, com.cognizant.opserv.sp.model.auth.UserDetails)
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW , rollbackFor = AlignmentServiceException.class)
	public void lockSourceGeography(GeographyAlignmentDTO geoAlgmtDTO,
			UserDetails userDetails) {
		
		//TODO: error handling		
		//Lock mirror geography alignments
		for(MirrorGeoAlgmtDTO mirror: geoAlgmtDTO.getMirrors()){			
			try{
				//base source already locked in online process locking mirror source now
				LOGGER.info("Base source already locked in online process locking mirror source now");
				
				geoAlgmtService.lockGeographyAlignment(mirror.getSourceMirrorGeoAlgmt(), userDetails);
				mirror.getSourceMirrorGeoAlgmt().setLocked(true);

			}catch(AlignmentServiceException exception){
				if(AlignmentServiceExceptionCode.ALGN_SER_EX_CD_318.getCode().equals(exception.getExceptionCode())){
					LOGGER.error("ZIP selected is already submitted for movement. PostalCode :"+geoAlgmtDTO.getSourceGeoAlgmt().getPostalCode().getCode().toString());
					ErrorDTO errorDTO = new ErrorDTO();
					errorDTO.setErrorCode(ErrorCode.ERROR_2011);
					errorDTO.addParam(geoAlgmtDTO.getSourceGeoAlgmt().getPostalCode().getCode().toString(),exception.getMessage());
					geoAlgmtDTO.addErrorDTO(errorDTO);
				}else{
					LOGGER.error("ZIP doesn't belong to mirror sales pos :"+mirror.getSourceSP().getId());
					ErrorDTO errorDTO = new ErrorDTO();
					errorDTO.setErrorCode(ErrorCode.ERROR_2013);
					errorDTO.addParam(mirror.getSourceSP().getId().toString(),"ZIP doesn't belong to mirror sales pos");
					geoAlgmtDTO.addErrorDTO(errorDTO);
				}
			}
		}	
	}

	/** (non-Javadoc)
	 * @see com.cognizant.opserv.sp.cr.process.internal.service.GeoMovementOfflineService#processSourceOnSubmit(com.cognizant.opserv.sp.cr.process.dto.GeographyAlignmentDTO, com.cognizant.opserv.sp.model.auth.UserDetails)
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW , rollbackFor = ChangeRequestServiceException.class)
	public void processSourceOnSubmit(GeographyAlignmentDTO geoAlgmtDTO,
			UserDetails userDetails) throws ChangeRequestServiceException {
		//TODO: error handling  ?
		//TODO:Build Source CR Line items from GeoAlignmentDTO including mirror
		List<ZipAlignmentChangeRequestDetails> crLineItems = new ArrayList<ZipAlignmentChangeRequestDetails>();
		LOGGER.info("ZIPMVMT: ZipLineItems >> processSourceOnSubmit");
		LOGGER.info("*****************INPUT PARAMETERS FOR CHANGE REQUEST*****************");
		StringBuffer info = new StringBuffer();
		info.append("Source Details::PostalCode -> "
				+ geoAlgmtDTO.getSourceGeoAlgmt().getPostalCode().getCode() + ",GeoID-> "
				+ geoAlgmtDTO.getSourceGeoAlgmt().getPostalCode().getGeoCode());
		info.append("SalesPosition ->"
				+ geoAlgmtDTO.getSourceGeoAlgmt().getSalesPosition().getId() + ",Hier ID ->"
				+ geoAlgmtDTO.getSourceGeoAlgmt().getSalesPosition().getHierarchy().getId());
		info.append("MainCR ->"
				+ geoAlgmtDTO.getMainCR().getId()); 
		LOGGER.info("ZIPMVMT:" + info.toString());
		LOGGER.info("*****************INPUT PARAMETERS FOR CHANGE REQUEST*****************");
		//CR Line Item for Source Base 
		ZipAlignmentChangeRequestDetails srcBaseCRLineItem = new ZipAlignmentChangeRequestDetails();
		srcBaseCRLineItem.setOldGeographyAlignmentObject(geoAlgmtDTO.getSourceGeoAlgmt());
		srcBaseCRLineItem.setParentChangeRequest(geoAlgmtDTO.getMainCR());
		ChangeRequestBusinessReason srcBaseCRBusinessReason = new ChangeRequestBusinessReason();
		srcBaseCRBusinessReason.setTrigger(ChangeRequestTriggerType.PUSH_ZIP);
		srcBaseCRBusinessReason.setSalesPosition(geoAlgmtDTO.getSourceGeoAlgmt().getSalesPosition());
		if(geoAlgmtDTO.getMainCR().getBusinessReason() != null){
			srcBaseCRBusinessReason.setBusinessReason(Integer.parseInt(geoAlgmtDTO.getMainCR().getBusinessReason()));
		}
		//To-Do ::: Doubt : what to set in below items
		//srcBaseCRBusinessReason.setChangeRequestType(); // String changeRequestType;
		//srcBaseCRBusinessReason.setCategoryId(geoAlgmtDTO.);// Integer categoryId;
		srcBaseCRLineItem.setChangeRequestBusinessReason(srcBaseCRBusinessReason);
		crLineItems.add(srcBaseCRLineItem);
		
		//CR Line Item for Source Mirror
		if(null != geoAlgmtDTO.getMirrors() && !geoAlgmtDTO.getMirrors().isEmpty()){
			for(MirrorGeoAlgmtDTO mirrorGeoAlgmtDTO : geoAlgmtDTO.getMirrors()){
				
						ZipAlignmentChangeRequestDetails srcMirrCRLineItem = new ZipAlignmentChangeRequestDetails();
						srcMirrCRLineItem.setOldGeographyAlignmentObject(mirrorGeoAlgmtDTO.getSourceMirrorGeoAlgmt());
						ChangeRequestBusinessReason srcMirrCRBusinessReason = new ChangeRequestBusinessReason();
						srcMirrCRBusinessReason.setTrigger(ChangeRequestTriggerType.PUSH_ZIP);
						srcMirrCRBusinessReason.setSalesPosition(mirrorGeoAlgmtDTO.getSourceSP());
						if(geoAlgmtDTO.getMainCR().getBusinessReason() != null){
							srcMirrCRBusinessReason.setBusinessReason(Integer.parseInt(geoAlgmtDTO.getMainCR().getBusinessReason()));
						}
						//To-Do ::: Doubt : what to set in below items
						//srcMirrCRBusinessReason.setChangeRequestType(); // String changeRequestType;
						//srcMirrCRBusinessReason.setCategoryId();// Integer categoryId;
						srcMirrCRLineItem.setParentChangeRequest(mirrorGeoAlgmtDTO.getChildCR());
						srcMirrCRLineItem.setChangeRequestBusinessReason(srcMirrCRBusinessReason);
						crLineItems.add(srcMirrCRLineItem);
			}
		}
		
		//Populate CR Line items
		try {
			LOGGER.info("======== Populate CR Line items for source ==== Started ======");
			crOfflineService.processLineItemForSourceZip(crLineItems, userDetails);
			LOGGER.info("======== Populate CR Line items for source ==== Ended ======");
		} catch (ChangeRequestServiceException chngReqException) {
			if(ChangeRequestServiceExceptionCode.CR_SER_EX_CD_013.getCode().equals(chngReqException.getExceptionCode())){
				LOGGER.error("Error during processing LineItem for source zip movement. PostalCode :"+geoAlgmtDTO.getSourceGeoAlgmt().getPostalCode().getCode().toString());
				ErrorDTO errorDTO = new ErrorDTO();
				errorDTO.setErrorCode(ErrorCode.ERROR_ZM_2022);
				errorDTO.addParam(geoAlgmtDTO.getSourceGeoAlgmt().getPostalCode().getCode().toString(),chngReqException.getMessage());
				geoAlgmtDTO.addErrorDTO(errorDTO);
			}else{
				LOGGER.error("Missing crLineItems / tenant id");
				ErrorDTO errorDTO = new ErrorDTO();
				errorDTO.setErrorCode(ErrorCode.ERROR_ZM_2023);
				errorDTO.addParam(geoAlgmtDTO.getSourceGeoAlgmt().getPostalCode().getCode().toString(),"Missing crLineItems / tenant id");
				geoAlgmtDTO.addErrorDTO(errorDTO);
			}
		}		
	}

	/** (non-Javadoc)
	 * @throws AlignmentServiceException 
	 * @see com.cognizant.opserv.sp.cr.process.internal.service.GeoMovementOfflineService#processTargetOnSubmit(com.cognizant.opserv.sp.cr.process.dto.GeographyAlignmentDTO, com.cognizant.opserv.sp.model.auth.UserDetails)
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW , rollbackFor = {ChangeRequestServiceException.class, AlignmentServiceException.class})
	public void processTargetOnSubmit(GeographyAlignmentDTO geoAlgmtDTO,
			UserDetails userDetails) throws ChangeRequestServiceException, AlignmentServiceException {
		//TODO: error handling
		//TODO:Build Target CR Line items from GeoAlignmentDTO including mirror
		List<ZipAlignmentChangeRequestDetails> crLineItems = new ArrayList<ZipAlignmentChangeRequestDetails>();
		LOGGER.info("ZIPMVMT: ZipLineItems >> processTargetOnSubmit");
		LOGGER.info("*****************INPUT PARAMETERS FOR CHANGE REQUEST*****************");
		StringBuffer info = new StringBuffer();
		info.append("Target Details::PostalCode -> "
				+ geoAlgmtDTO.getTargetGeoAlgmt().getPostalCode().getCode() + ",GeoID-> "
				+ geoAlgmtDTO.getTargetGeoAlgmt().getPostalCode().getGeoCode());
		info.append("SalesPosition ->"
				+ geoAlgmtDTO.getTargetGeoAlgmt().getSalesPosition().getId() + ",Hier ID ->"
				+ geoAlgmtDTO.getTargetGeoAlgmt().getSalesPosition().getHierarchy().getId());
		info.append("MainCR ->"
				+ geoAlgmtDTO.getMainCR().getId()); 
		LOGGER.info("ZIPMVMT:" + info.toString());
		LOGGER.info("*****************INPUT PARAMETERS FOR CHANGE REQUEST*****************");

		//Code changes for target record insertion
		Integer spGeoId = this.fetchGeoID(userDetails,geoAlgmtDTO.getTargetGeoAlgmt().getSalesPosition()); 
		geoAlgmtDTO.getTargetGeoAlgmt().getPostalCode().setGeoCode(spGeoId);

		PostalCode pcTarget = new PostalCode();
		pcTarget.setCode(geoAlgmtDTO.getTargetGeoAlgmt().getPostalCode().getCode());
		pcTarget.setGeoCode(new Long(spGeoId));
		geoAlgmtDTO.getTargetGeoAlgmt().setPostalCode(pcTarget);
		LOGGER.info("targetGeoAlign:" + geoAlgmtDTO.getTargetGeoAlgmt().toString());
		//set pointZipFlag
		geoAlgmtDTO.getTargetGeoAlgmt().setPointZipFlag(geoAlgmtDTO.getSourceGeoAlgmt().isPointZipFlag());	

		// inserting or updating the target
		LOGGER.info("ZIPMVMT: Updating zip alignment data");
		Integer geoZipId = geoAlgmtService.insertTargetZipAlgmnt(geoAlgmtDTO.getTargetGeoAlgmt(), geoAlgmtDTO.getTargetGeoAlgmt().getPostalCode(), userDetails,spGeoId);
		//		targetGeoAlign.setId(new Long(geoZipId));
		//		sourceGeoAlign.setId(null);
		LOGGER.info("ZIPMVMT: Updating zip alignment data (geoZipId) -> "+geoZipId);

		LOGGER.info("ZIPMVMT: call to saveChangeRequestZipAssigmentPush (targetGeoAlign)-> "+geoAlgmtDTO.getTargetGeoAlgmt().toString());

		//CR Line Item for Source Base 
		ZipAlignmentChangeRequestDetails trgtBaseCRLineItem = new ZipAlignmentChangeRequestDetails();

		trgtBaseCRLineItem.setNewGeographyAlignmentObject(geoAlgmtDTO.getTargetGeoAlgmt());
		ChangeRequestBusinessReason trgtBaseCRBusinessReason = new ChangeRequestBusinessReason();
		trgtBaseCRBusinessReason.setTrigger(ChangeRequestTriggerType.PUSH_ZIP);

		trgtBaseCRBusinessReason.setSalesPosition(geoAlgmtDTO.getTargetGeoAlgmt().getSalesPosition());
		if(geoAlgmtDTO.getMainCR().getBusinessReason() != null){
			trgtBaseCRBusinessReason.setBusinessReason(Integer.parseInt(geoAlgmtDTO.getMainCR().getBusinessReason()));
		}
		//To-Do ::: Doubt : what to set in below items
		//trgtBaseCRBusinessReason.setChangeRequestType(); // String changeRequestType;
		//trgtBaseCRBusinessReason.setCategoryId(geoAlgmtDTO.);// Integer categoryId;
		trgtBaseCRLineItem.setParentChangeRequest(geoAlgmtDTO.getMainCR());
		trgtBaseCRLineItem.setChangeRequestBusinessReason(trgtBaseCRBusinessReason);

		crLineItems.add(trgtBaseCRLineItem);

		//CR Line Item for target Mirror
		if(null != geoAlgmtDTO.getMirrors() && !geoAlgmtDTO.getMirrors().isEmpty()){
			GeographyAlignment targetMirrorGeoAlgmt = new GeographyAlignment();
			targetMirrorGeoAlgmt.setStartDate(geoAlgmtDTO.getTargetGeoAlgmt().getStartDate());
			targetMirrorGeoAlgmt.setEndDate(geoAlgmtDTO.getTargetGeoAlgmt().getEndDate());

			for(MirrorGeoAlgmtDTO mirrorGeoAlgmtDTO : geoAlgmtDTO.getMirrors()){

				if (mirrorGeoAlgmtDTO.getTargetSP() != null) {
					targetMirrorGeoAlgmt.setSalesPosition(mirrorGeoAlgmtDTO.getTargetSP());
					//			for (GeographyAlignment sourceMirrorGeoAlgmt : mirrorGeoAlgmtDTO.getSourceMirrorGeoAlgmts()) {
					ZipAlignmentChangeRequestDetails trgtMirrCRLineItem = new ZipAlignmentChangeRequestDetails();
					try{
					Integer targetMirGeoId = this.fetchGeoID(userDetails,mirrorGeoAlgmtDTO.getTargetSP()); 
					//					geoAlgmtDTO.getTargetGeoAlgmt().getPostalCode().setGeoCode(spGeoIdmir);
					PostalCode targetMirPostalCode = new PostalCode();
					targetMirPostalCode.setCode(geoAlgmtDTO.getTargetGeoAlgmt().getPostalCode().getCode());
					targetMirPostalCode.setGeoCode(new Long(targetMirGeoId));
					//					geoAlgmtDTO.getTargetGeoAlgmt().setPostalCode(pcTargetmir);
					targetMirrorGeoAlgmt.setCountryCode(mirrorGeoAlgmtDTO.getSourceMirrorGeoAlgmt().getCountryCode());
					targetMirrorGeoAlgmt.setPointZipFlag(mirrorGeoAlgmtDTO.getSourceMirrorGeoAlgmt().isPointZipFlag());
						
						Integer geoZipIdmir = geoAlgmtService.insertTargetZipAlgmnt(targetMirrorGeoAlgmt, targetMirPostalCode, userDetails, targetMirGeoId);
						GeographyAlignment targetMirrorgeographyAlignment = geoAlgmtService.getGeographyAlgmnt(targetMirPostalCode.getCode(),geoZipIdmir,mirrorGeoAlgmtDTO.getTargetSP().getId(),userDetails);
						//Fetch Source mirror record
						trgtMirrCRLineItem.setNewGeographyAlignmentObject(targetMirrorgeographyAlignment);
						ChangeRequestBusinessReason trgtMirrCRBusinessReason = new ChangeRequestBusinessReason();
						trgtMirrCRBusinessReason.setTrigger(ChangeRequestTriggerType.PUSH_ZIP);
						trgtMirrCRBusinessReason.setSalesPosition(mirrorGeoAlgmtDTO.getSourceSP());
						if(geoAlgmtDTO.getMainCR().getBusinessReason() != null){
							trgtMirrCRBusinessReason.setBusinessReason(Integer.parseInt(geoAlgmtDTO.getMainCR().getBusinessReason()));
						}
						//To-Do ::: Doubt : what to set in below items
						//srcMirrCRBusinessReason.setChangeRequestType(); // String changeRequestType;
						//srcMirrCRBusinessReason.setCategoryId();// Integer categoryId;
						trgtMirrCRLineItem.setParentChangeRequest(mirrorGeoAlgmtDTO.getChildCR());
						trgtMirrCRLineItem.setChangeRequestBusinessReason(trgtMirrCRBusinessReason);
						crLineItems.add(trgtMirrCRLineItem);
					}
					catch(AlignmentServiceException algnmntServiceException){
						if(AlignmentServiceExceptionCode.ALGN_SER_EX_CD_404.getCode().equals(algnmntServiceException.getExceptionCode())){
							LOGGER.error("Exception occurred while fetching existing Geo unit ids");
							ErrorDTO errorDTO = new ErrorDTO();
							errorDTO.setErrorCode(ErrorCode.ERROR_ZM_2030);
							errorDTO.addParam("fetchGeoID",algnmntServiceException.getMessage());
							geoAlgmtDTO.addErrorDTO(errorDTO);
						}else if(AlignmentServiceExceptionCode.ALGN_SER_EX_CD_406.getCode().equals(algnmntServiceException.getExceptionCode())){
							LOGGER.error("Exception occurred while creating target geo id");
							ErrorDTO errorDTO = new ErrorDTO();
							errorDTO.setErrorCode(ErrorCode.ERROR_ZM_2031);
							errorDTO.addParam("fetchGeoID",algnmntServiceException.getMessage());
							geoAlgmtDTO.addErrorDTO(errorDTO);
						}else if(AlignmentServiceExceptionCode.ALGN_SER_EX_CD_403.getCode().equals(algnmntServiceException.getExceptionCode())){
							LOGGER.error("Exception occurred while inserting target zip alignment.");
							ErrorDTO errorDTO = new ErrorDTO();
							errorDTO.setErrorCode(ErrorCode.ERROR_ZM_2032);
							errorDTO.addParam("insertTargetZipAlgmnt",algnmntServiceException.getMessage());
							geoAlgmtDTO.addErrorDTO(errorDTO);
						}else if(AlignmentServiceExceptionCode.ALGN_SER_EX_CD_405.getCode().equals(algnmntServiceException.getExceptionCode())){
							LOGGER.error("Exception occurred while fetching geography alignments.");
							ErrorDTO errorDTO = new ErrorDTO();
							errorDTO.setErrorCode(ErrorCode.ERROR_ZM_2033);
							errorDTO.addParam("getGeographyAlgmnt",algnmntServiceException.getMessage());
							geoAlgmtDTO.addErrorDTO(errorDTO);
						}
					}
				}
			}
		}

			//Populate CR Line items
			try{
				LOGGER.info("======== Populate CR Line items for target ==== Started ======");
				crOfflineService.processLineItemForTargetZip(crLineItems, userDetails);		
				LOGGER.info("======== Populate CR Line items for target ==== Ended ======");
			} catch (ChangeRequestServiceException chngReqException) {
				if(ChangeRequestServiceExceptionCode.CR_SER_EX_CD_013.getCode().equals(chngReqException.getExceptionCode())){
					LOGGER.error("Error during processing LineItem for source zip movement. PostalCode :"+geoAlgmtDTO.getSourceGeoAlgmt().getPostalCode().getCode().toString());
					ErrorDTO errorDTO = new ErrorDTO();
					errorDTO.setErrorCode(ErrorCode.ERROR_ZM_2022);
					errorDTO.addParam(geoAlgmtDTO.getSourceGeoAlgmt().getPostalCode().getCode().toString(),chngReqException.getMessage());
					geoAlgmtDTO.addErrorDTO(errorDTO);
				}else{
					LOGGER.error("Missing crLineItems / tenant id");
					ErrorDTO errorDTO = new ErrorDTO();
					errorDTO.setErrorCode(ErrorCode.ERROR_ZM_2023);
					errorDTO.addParam(geoAlgmtDTO.getSourceGeoAlgmt().getPostalCode().getCode().toString(),"Missing crLineItems / tenant id");
					geoAlgmtDTO.addErrorDTO(errorDTO);
				}
			}
	}

	
	/** (non-Javadoc)
	 * @throws MetricExecutionException 
	 * @see com.cognizant.opserv.sp.cr.process.internal.service.GeoMovementOfflineService#handleMetricsOnSubmit(com.cognizant.opserv.sp.cr.process.dto.GeographyAlignmentDTO, com.cognizant.opserv.sp.model.auth.UserDetails)
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW , rollbackFor = {MetricViolationException.class, AlignmentServiceException.class,MetricExecutionException.class})
	public boolean handleMetricsOnSubmit(GeographyAlignmentDTO geoAlgmtDTO,
			UserDetails userDetails) {
		boolean metricResult = false;
		// Calculate the metrics - synchronous call		
		// For any errors fill the error DTO in the geoAlgmtDTO		
		//metricResultDAOService.processCalculativeMetrics(geoAlgmtDTO.getSourceGeoAlgmt().getSalesPosition(), MetricTriggerType.UNASSIGN_ZIP, userDetails);
		//metricResultDAOService.processCalculativeMetrics(geoAlgmtDTO.getTargetGeoAlgmt().getSalesPosition(), MetricTriggerType.ASSIGN_ZIP, userDetails);
		
		//List<MetricResult> sourceMetricResults = metricResultDAOService.getMetricResults(geoAlgmtDTO.getSourceGeoAlgmt().getSalesPosition(), MetricTriggerType.UNASSIGN_ZIP, userDetails);
		//List<MetricResult> targetMetricResults = metricResultDAOService.getMetricResults(geoAlgmtDTO.getTargetGeoAlgmt().getSalesPosition(), MetricTriggerType.ASSIGN_ZIP, userDetails);
		
		// Check for soft and hard metric violations.
		// Auto reject the Main CR - See below code
		// For any hard metric violation auto reject the CR		
		List<MetricResult> metricResultSource = null;
		List<MetricResult> metricResultTarget = null;
		LOGGER.info("Before Calculating metrics... ChangeRequestProcessInternalServiceImpl --> submitChangeRequestTransaction :: chngReq:" + geoAlgmtDTO.getMainCR().getId());
		
		ChangeRequestDefinition definition = new ChangeRequestDefinition();
		definition.setTrigger(ChangeRequestTriggerType.PUSH_CUSTOMER);
		geoAlgmtDTO.getMainCR().setCrDefinition(definition);
		geoAlgmtDTO.getMainCR().setRequestedSalesPosition(geoAlgmtDTO.getSourceGeoAlgmt().getSalesPosition());
		geoAlgmtDTO.getMainCR().setRaisedSalesPosition(geoAlgmtDTO.getTargetGeoAlgmt().getSalesPosition());
		try {
			calcuateProcessMetrics(geoAlgmtDTO.getMainCR(), userDetails);
		
		
		LOGGER.info("After Calculating metrics... ChangeRequestProcessInternalServiceImpl --> submitChangeRequestTransaction :: chngReq:" + geoAlgmtDTO.getMainCR().getId());
		metricResult = changeRequestMetricsResults(geoAlgmtDTO.getMainCR(), metricResultSource, metricResultTarget, userDetails);
		LOGGER.info("metricResult... ChangeRequestProcessInternalServiceImpl --> submitChangeRequestTransaction:" + metricResult);
		}catch (MetricViolationException e) {
			List<ErrorDTO> errors = new ArrayList<ErrorDTO>();
			ErrorDTO errorDTO = new ErrorDTO();
			errorDTO.setErrorCode(ErrorCode.ERROR_2014);
			errors.add(errorDTO);
			geoAlgmtDTO.setErrors(errors);
		} catch (AlignmentServiceException e) {
			List<ErrorDTO> errors = new ArrayList<ErrorDTO>();
			ErrorDTO errorDTO = new ErrorDTO();
			errorDTO.setErrorCode(ErrorCode.ERROR_2015);
			errors.add(errorDTO);
			geoAlgmtDTO.setErrors(errors);
		} catch (MetricExecutionException e) {
			List<ErrorDTO> errors = new ArrayList<ErrorDTO>();
			ErrorDTO errorDTO = new ErrorDTO();
			errorDTO.setErrorCode(ErrorCode.ERROR_2016);
			errors.add(errorDTO);
			geoAlgmtDTO.setErrors(errors);
		} 
		return metricResult;
			
	}

	/** (non-Javadoc)
	 * @see com.cognizant.opserv.sp.cr.process.internal.service.GeoMovementOfflineService#updateChangeRequests(com.cognizant.opserv.sp.cr.process.dto.GeographyAlignmentDTO, com.cognizant.opserv.sp.model.auth.UserDetails)
	 */
	@Override
	public void updateChangeRequests(GeographyAlignmentDTO geoAlgmtDTO,
			UserDetails userDetails) throws ChangeRequestServiceException {
		// Update the main CR and mirror CR to Pending Approval
		// Initiate the workflow for those CRs
		crOfflineService.updateCRandInitiateWorkflow(geoAlgmtDTO, userDetails);
		
	}

	/** (non-Javadoc)
	 * @see com.cognizant.opserv.sp.cr.process.internal.service.GeoMovementOfflineService#emitPostProcessEvents(com.cognizant.opserv.sp.cr.process.dto.GeographyAlignmentDTO, com.cognizant.opserv.sp.model.auth.UserDetails)
	 */
	@Override
	public void emitPostProcessEvents(GeographyAlignmentDTO geoAlgmtDTO,
			UserDetails userDetails) {
		// TODO Auto-generated method stub		
	}
	
	/** (non-Javadoc)
	 * @see com.cognizant.opserv.sp.cr.process.internal.service.GeoMovementOfflineService#createMirrorCR(com.cognizant.opserv.sp.model.cr.ChangeRequest, com.cognizant.opserv.sp.cr.process.dto.MirrorGeoAlgmtDTO, com.cognizant.opserv.sp.model.auth.UserDetails)
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW,rollbackFor = {ChangeRequestServiceException.class})
	public void createMirrorCR(ChangeRequest parentCR,
			MirrorGeoAlgmtDTO mirrorDTO, MirrorCustAlgmtDTO mirrorCustAlgmtDTO, UserDetails userDetails) throws ChangeRequestServiceException {		
		//TODO: error handling	
		try{
			ChangeRequest mirrorCR = crCommonService.generateZipMovementChangeRequest(parentCR, mirrorDTO.getSourceSP(), mirrorDTO.getTargetSP(), userDetails);
			mirrorDTO.setChildCR(mirrorCR);		
			if(mirrorCustAlgmtDTO != null){
				mirrorCustAlgmtDTO.setChildCR(mirrorCR);
			}
		}catch (ChangeRequestServiceException ex) {
			Object[] obj = new Object[] {};
			LOGGER.error("=====================Error while createMirrorCR=========================");
			throw new ChangeRequestServiceException(
					ChangeRequestServiceExceptionCode.CR_SER_EX_CD_001,
					"Error while createMirrorCR",
					obj, ex);
		}
	}
	
	/** (non-Javadoc)
	 * @see com.cognizant.opserv.sp.cr.process.internal.service.GeoMovementOfflineService#assignApproversForSourceAndTarget(com.cognizant.opserv.sp.cr.process.dto.GeographyAlignmentDTO, com.cognizant.opserv.sp.model.auth.UserDetails)
	 */
	@Override
	@Transactional
	public void assignApproversForSourceAndTarget(
			GeographyAlignmentDTO geoAlgmtDTO, UserDetails userDetails) throws ChangeRequestServiceException {
		crOfflineService.assignApproversForSource(geoAlgmtDTO.getMainCR(), geoAlgmtDTO.getSourceGeoAlgmt().getSalesPosition(), userDetails);
		crOfflineService.assignApproversForTarget(geoAlgmtDTO.getMainCR(), geoAlgmtDTO.getTargetGeoAlgmt().getSalesPosition(), userDetails);		
	}
	
	
	/** (non-Javadoc)
	 * @see com.cognizant.opserv.sp.cr.process.internal.service.GeoMovementOfflineService#verifyMirrorCRsForGeoMovement(com.cognizant.opserv.sp.cr.process.dto.GeographyAlignmentDTO, com.cognizant.opserv.sp.model.auth.UserDetails)
	 */
	@Override
	@Transactional(readOnly=true)
	public void findMirrorCRsForGeoMovement(GeographyAlignmentDTO geoAlgmtDTO, UserDetails userDetails){
		List<ChangeRequest> mirrorCRs;
		try {
			mirrorCRs = crCommonService.findMirrorCRs(geoAlgmtDTO.getMainCR(), userDetails);
			for(ChangeRequest mirrorCR : mirrorCRs) {
				for(MirrorGeoAlgmtDTO mirrorDTO : geoAlgmtDTO.getMirrors()) {
					if(mirrorDTO.getSourceSP().getId().equals(mirrorCR.getRaisedSalesPosition().getId()) && 
					   mirrorDTO.getTargetSP().getId().equals(mirrorCR.getRequestedSalesPosition().getId())) {
						mirrorDTO.setChildCR(mirrorCR);
						break;
					}
				}
			}
		} catch (ChangeRequestServiceException chngReqException) {
			if(ChangeRequestServiceExceptionCode.CR_SER_EX_CD_020.getCode().equals(chngReqException.getExceptionCode())){
				LOGGER.error("Missing parentCR /  tenant id. Hence unable to find Mirror CR");
				ErrorDTO errorDTO = new ErrorDTO();
				errorDTO.setErrorCode(ErrorCode.ERROR_ZM_2028);
				errorDTO.addParam(geoAlgmtDTO.getOfflineReqId().toString(),"Missing parentCR /  tenant id. Hence unable to find Mirror CR");
				geoAlgmtDTO.addErrorDTO(errorDTO);
			}else if(ChangeRequestServiceExceptionCode.CR_SER_EX_CD_011.getCode().equals(chngReqException.getExceptionCode())){
				LOGGER.error("Data access exception while fetching mirror CR");
				ErrorDTO errorDTO = new ErrorDTO();
				errorDTO.setErrorCode(ErrorCode.ERROR_CP_2033);
				errorDTO.addParam(geoAlgmtDTO.getOfflineReqId().toString(),chngReqException.getMessage());
				geoAlgmtDTO.addErrorDTO(errorDTO);
			}else{
				chngReqException.printStackTrace();
			}
		}
		
	}
	

	/**
	 * Fetch geo id.
	 *
	 * @param userDetails the user details
	 * @param targetSalesPos the target sales pos
	 * @return the integer
	 * @throws AlignmentServiceException 
	 */
	private Integer fetchGeoID(UserDetails userDetails,
			SalesPosition targetSalesPos) throws AlignmentServiceException {
		Map<Long, Integer> existingGeoIDs = null;
		Integer spGeoId = null;
		existingGeoIDs = geoAlgmtService.fetchExistingGeoIDs(targetSalesPos, userDetails);

		Long destSalePosId = targetSalesPos.getId();

		if (existingGeoIDs != null && existingGeoIDs.size() > 0) {
			spGeoId = existingGeoIDs.get(destSalePosId);
		}

		if (spGeoId == null || spGeoId == 0) {
			// generate random number
			spGeoId = geoAlgmtService.createTargetGeoID(userDetails);
		}
		return spGeoId;
	} 

	/**
	 * changeRequestMetricsResults- To change Metrics Results
	 * 
	 * @param chngReq
	 *            -chngReq
	 * @param metricResultSource
	 *            -metricResultSource
	 * @param metricResultTarget
	 *            -metricResultTarget
	 * @throws AlignmentServiceException
	 * @return Boolean
	 * @throws MetricViolationException
	 */
	private Boolean changeRequestMetricsResults(ChangeRequest chngReq, List<MetricResult> metricResultSource, List<MetricResult> metricResultTarget,
			UserDetails userDetails) throws MetricViolationException, AlignmentServiceException {
		Boolean result = false;
		 if (chngReq.getCrDefinition().getTrigger().getId().equals(ChangeRequestTriggerType.PUSH_ZIP.getId())) {
			result = mtrOffServ.checkZipMovementMetricViolation(chngReq.getRequestedSalesPosition(), chngReq.getRaisedSalesPosition(), userDetails);
		}
		return result;
	}

	
	private void calcuateProcessMetrics(ChangeRequest chngReq, UserDetails userDetails) throws MetricExecutionException {
		LOGGER.info("Inside calcuateProcessMetrics");
		if (chngReq.getCrDefinition().getTrigger().getId().equals(ChangeRequestTriggerType.PUSH_ZIP.getId())) {
			mtrOffServ.processCalculativeMetrics(chngReq.getRequestedSalesPosition(), MetricTriggerType.UNASSIGN_CUSTOMER, userDetails);
			mtrOffServ.processCalculativeMetrics(chngReq.getRaisedSalesPosition(), MetricTriggerType.ASSIGN_CUSTOMER, userDetails);
		} 
	}

}
