package com.cognizant.opserv.sp.cr.process.internal.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.opserv.sp.common.ChangeRequestOfflineStatusType;
import com.cognizant.opserv.sp.common.ChangeRequestTriggerType;
import com.cognizant.opserv.sp.common.util.JSONUtil;
import com.cognizant.opserv.sp.cr.process.dto.CustomerAlignmentDTO;
import com.cognizant.opserv.sp.cr.process.dto.ErrorCode;
import com.cognizant.opserv.sp.cr.process.dto.ErrorDTO;
import com.cognizant.opserv.sp.cr.process.dto.GeographyAlignmentDTO;
import com.cognizant.opserv.sp.cr.process.internal.service.OfflineRequestService;
import com.cognizant.opserv.sp.event.messages.OfflineRequestMessage;
import com.cognizant.opserv.sp.exception.AlignmentServiceException;
import com.cognizant.opserv.sp.exception.AlignmentServiceException.AlignmentServiceExceptionCode;
import com.cognizant.opserv.sp.exception.ChangeRequestServiceException;
import com.cognizant.opserv.sp.exception.ChangeRequestServiceException.ChangeRequestServiceExceptionCode;
import com.cognizant.opserv.sp.json.CustomerBlobDetails;
import com.cognizant.opserv.sp.model.Customer;
import com.cognizant.opserv.sp.model.CustomerAlignment;
import com.cognizant.opserv.sp.model.GeographyAlignment;
import com.cognizant.opserv.sp.model.OfflineRequest;
import com.cognizant.opserv.sp.model.ZipBlob;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.model.cr.ChangeRequest;
import com.cognizant.opserv.sp.model.cr.ChangeRequestDefinition;
import com.cognizant.opserv.sp.persistence.dao.service.ChangeRequestOfflineDAOService;
import com.cognizant.opserv.sp.persistence.dao.service.CustomerAlignmentDAOService;
import com.cognizant.peg.core.exception.OpservDataAccessException;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;

/**
 * The Class OfflineRequestServiceImpl.
 */
@Service
public class OfflineRequestServiceImpl implements OfflineRequestService {
	
	/** The Constant LOGGER. */
	private static final OpservLogger LOGGER = OpservLoggerFactory
			.getOpservLogger(OfflineRequestServiceImpl.class);
	
	/** The change request offline dao service. */
	@Autowired
	private ChangeRequestOfflineDAOService changeRequestOfflineDAOService;
	
	/**
	 * customer Alignment DAO Service
	 */
	@Autowired
	private CustomerAlignmentDAOService customerAlignmentDAOService;

	
	/**
	 * Based on the offline request, we need to constrcut the customer algmt DTO which will be passed to subsequent processing in other services.
	 * Construct the Customer Alignment DTO from the Offline request.
	 * This DTO will be passed to subsequent services and collating necessary info and errors 
	 * @param message
	 * @param userDetails
	 * @return
	 * @throws AlignmentServiceException 
	 */
	@Transactional(readOnly=true)
	public CustomerAlignmentDTO getCustomerOfflineRequest(OfflineRequestMessage message, UserDetails userDetails) throws AlignmentServiceException {
		try{
			if(message != null && userDetails != null && message.getUserDetails() != null ){
		LOGGER.info("============== Data in message coming from the Queue is=============== "
				+ message.toString());
		
		CustomerAlignmentDTO custAlgmtDTO = new CustomerAlignmentDTO();
		OfflineRequest crOfflineData = changeRequestOfflineDAOService.fetchtempCRInfo(
			message.getOfflineReqId(), message.getUserDetails());
		if (crOfflineData != null) {
			
			LOGGER.info("============== Data from t_chng_req_offline table for the offline Id ("
					+ message.getOfflineReqId()
					+ ") with status as 'Initiated' is"
					+ crOfflineData.toString());
			
			CustomerBlobDetails customerBlobDetails = JSONUtil.toObject(
					crOfflineData.getJsonString(),
					CustomerBlobDetails.class);
			LOGGER.info("========= Data from the t_temp_chngreq table for offline_id (" + message.getOfflineReqId() + ") ======= is " + crOfflineData.toString());
			LOGGER.info("========= Data from the t_temp_chngreq table for offline_id ("
					+ message.getOfflineReqId()
					+ ") ======= is "
					+ crOfflineData.toString());

			// TODO : Fetch cust info, CR info etc and set on the DTO...
			if (customerBlobDetails != null) {
				custAlgmtDTO.setSourceBaseCustAlgmt(customerBlobDetails
						.getSrcCustomerAlignment());
				custAlgmtDTO.setTargetBaseCustAlgmt(customerBlobDetails
						.getTarCustomerAlignment());
				custAlgmtDTO.setMainCR(customerBlobDetails.getChangeRequest());
				if(customerBlobDetails.getNewCustomerProductAlignments() != null && !customerBlobDetails.getNewCustomerProductAlignments().isEmpty()){
					custAlgmtDTO.setSourceUpdatedCustProdAlgmts(customerBlobDetails.getNewCustomerProductAlignments());
				}
				if(customerBlobDetails.getOldCustomerProductAlignments() != null && !customerBlobDetails.getOldCustomerProductAlignments().isEmpty()){
					custAlgmtDTO.setSourceOldCustProdAlgmts(customerBlobDetails.getOldCustomerProductAlignments());
				}
			}
			return custAlgmtDTO;
		} else {
			throw new AlignmentServiceException(
					"AlignmentServiceExceptionCode.ALGN_SER_EX_CD_320",
					"Status of CR Offline Id "
					+ message.getOfflineReqId()
					+ " is not Initiated, hence could not Push a customer=======");
		}
		
	
		}else{
			Object[] args = new Object[] { "message or userDetails is null" };
			throw new AlignmentServiceException(args);
		}
			}catch (AlignmentServiceException ex) {
				Object[] obj = new Object[] { message.getChngReqID() };
				LOGGER.error("=====================Error while Fetching Customer Offline Request=========================");
				throw new AlignmentServiceException(
						AlignmentServiceExceptionCode.ALGN_SER_EX_CD_323,
						"Error while Fetching Customer Offline Request",
						obj, ex);
			}
	}
	
	@Transactional(readOnly=true)
	public GeographyAlignmentDTO getGeoOfflineRequest(OfflineRequestMessage message, UserDetails userDetails) throws AlignmentServiceException {
		GeographyAlignmentDTO geoAlgmtDTO = new GeographyAlignmentDTO();
		try{
		OfflineRequest OfflineCR = changeRequestOfflineDAOService.fetchtempCRInfo(
				message.getOfflineReqId(),
				message.getUserDetails());
		LOGGER.debug("*****************AFTER CALLING changeRequestOfflineDAOService.fetchtempCRInfo()  *****************",OfflineCR);

		ZipBlob zipBlob = JSONUtil.toObject(OfflineCR.getJsonString(),
				ZipBlob.class);
		if (zipBlob == null) {
			LOGGER.error("zipBlob is null");
			ErrorDTO errorDTO = new ErrorDTO();
			errorDTO.setErrorCode(ErrorCode.ERROR_ZM_2012);
			errorDTO.addParam(message.getOfflineReqId().toString(),"zipBlob is null");
			geoAlgmtDTO.addErrorDTO(errorDTO);
			return geoAlgmtDTO;
		}
		if(zipBlob.getSrcGeographyAlignment() == null){
			LOGGER.error("VALIDATION FAILED::: Mandatory field Source Geography Alignment is null");
			ErrorDTO errorDTO = new ErrorDTO();
			errorDTO.setErrorCode(ErrorCode.ERROR_ZM_2016);
			errorDTO.addParam(message.getOfflineReqId().toString(),"VALIDATION FAILED::: Mandatory field Source Geography Alignment is null");
			geoAlgmtDTO.addErrorDTO(errorDTO);
			return geoAlgmtDTO;
		}
		if(zipBlob.getTargetGeographyAlignment() == null){
			LOGGER.error("VALIDATION FAILED::: Mandatory field Target Geography Alignment is null");
			ErrorDTO errorDTO = new ErrorDTO();
			errorDTO.setErrorCode(ErrorCode.ERROR_ZM_2017);
			errorDTO.addParam(message.getOfflineReqId().toString(),"VALIDATION FAILED::: Mandatory field Target Geography Alignment is null");
			geoAlgmtDTO.addErrorDTO(errorDTO);
			return geoAlgmtDTO;
		}
		if(message.getChngReqID() == null){
			LOGGER.error("VALIDATION FAILED::: Mandatory field Change Request ID is null");
			ErrorDTO errorDTO = new ErrorDTO();
			errorDTO.setErrorCode(ErrorCode.ERROR_ZM_2018);
			errorDTO.addParam(message.getOfflineReqId().toString(),"VALIDATION FAILED::: Mandatory field Change Request ID is null");
			geoAlgmtDTO.addErrorDTO(errorDTO);
			return geoAlgmtDTO;
		}
		
		//Populating basic info of SourceGeoAlign
		GeographyAlignment sourceGeoAlign = zipBlob.getSrcGeographyAlignment();
		LOGGER.debug("sourceGeoAlign :"+sourceGeoAlign.toString());
		
		//Populating basic info of TargetGeoAlign
		GeographyAlignment targetGeoAlign = zipBlob.getTargetGeographyAlignment();
		LOGGER.debug("targetGeoAlign :"+targetGeoAlign.toString());
		
		ChangeRequest mainCR = new ChangeRequest();
		ChangeRequestDefinition crDefinition = new ChangeRequestDefinition();
		crDefinition.setTrigger(ChangeRequestTriggerType.PUSH_ZIP);
		
		mainCR.setCrDefinition(crDefinition);
		mainCR.setId(message.getChngReqID());
		if(null!=message.getBusinessReason() && null!=message.getBusinessReason().getReason()){
			mainCR.setBusinessReason(message.getBusinessReason().getReason());	
		}
		if(null!=message.getBusinessReason() && null!=message.getBusinessReason().getChngType()){
			String chngType = message.getBusinessReason().getChngType();
		}
		if(null!=message.getBusinessReason() && null!=message.getBusinessReason().getDescription()){
			mainCR.setComments(message.getBusinessReason().getDescription());	
		}
		
		//Populating basic info of CustAlignmentDTO
		List<CustomerAlignment> custAligns = zipBlob.getCustomerAlignmentList();

		if (custAligns != null && !custAligns.isEmpty()) {
			List<CustomerAlignmentDTO> custAlgmtDTOs = new LinkedList<CustomerAlignmentDTO>();
			//CustomerAlignmentDTO custAlgmtDTO = null;
			for (CustomerAlignment srcCustAlign : custAligns) {
				CustomerAlignmentDTO custAlgmtDTO = new CustomerAlignmentDTO();
				custAlgmtDTO.setSourceBaseCustAlgmt(srcCustAlign);
				CustomerAlignment trgtCustAlign =  generateTargetCustAlgmntDTO(custAlgmtDTO, targetGeoAlign);
				custAlgmtDTO.setTargetBaseCustAlgmt(trgtCustAlign);
				custAlgmtDTO.setMainCR(mainCR);
				custAlgmtDTOs.add(custAlgmtDTO);
				LOGGER.debug("custAlgmtDTO :"+custAlgmtDTO.toString());
			}
			geoAlgmtDTO.setCustAlgmtDTOs(custAlgmtDTOs);
		}else{
			LOGGER.info("ZIP MOVEMENT >> No customers to move");
		}
		
		//ChangeRequestStatusType status = message.getBusinessReason().getChngType();
		geoAlgmtDTO.setMainCR(mainCR);
		geoAlgmtDTO.setOfflineReqId(message.getOfflineReqId());
		geoAlgmtDTO.setSourceGeoAlgmt(sourceGeoAlign);
		geoAlgmtDTO.setTargetGeoAlgmt(targetGeoAlign);
		LOGGER.debug("geoAlgmtDTO :"+geoAlgmtDTO.toString());
		}catch (OpservDataAccessException ex) {
			Object[] obj = new Object[] {};
			LOGGER.error("=====================Exception while updating CR Offline Status=========================");
			throw new AlignmentServiceException(
					AlignmentServiceExceptionCode.ALGN_SER_EX_CD_323,
					"Exception while getGeoOfflineRequest",
					obj, ex);
		}
		return geoAlgmtDTO;
	}

	
	/**
	 * Lock request for processing- update CR Offline Status to PROCESSING.
	 *
	 * @param offlineId the offline id
	 * @param userDetails the user details
	 */
	@Transactional(propagation=Propagation.REQUIRES_NEW , rollbackFor = AlignmentServiceException.class)
	public void lockRequestForProcessing(Long offlineId, UserDetails userDetails) throws AlignmentServiceException{
		try{
			if(null != offlineId && offlineId != 0 && null != userDetails && null != userDetails.getTenantId() && null != userDetails.getUserId() && userDetails.getTenantId() != 0 && userDetails.getUserId() != 0 ){
				LOGGER.info("=========== Update status in t_chng_req_offline table to PROCESSING for offline_id (" + offlineId+ ") ----- Started");
				changeRequestOfflineDAOService.updateCROfflineStatus(ChangeRequestOfflineStatusType.PROCESSING.getId(), offlineId , userDetails.getUserId(),userDetails.getTenantId(), null);
				LOGGER.info("=========== Update status in t_chng_req_offline table to PROCESSING for offline_id (" + offlineId+ ") ----- Ended");
			}else{
				LOGGER.error("=====================OfflineId/UserDetails is NULL=========================");
				throw new AlignmentServiceException(AlignmentServiceExceptionCode.ALGN_SER_EX_CD_333,
						"OfflineId or UserDetails is null",
						null, null);

			}
			}catch (AlignmentServiceException ex) {
				Object[] obj = new Object[] { offlineId };
				LOGGER.error("=====================Exception while updating CR Offline Status=========================");
				throw new AlignmentServiceException(
						AlignmentServiceExceptionCode.ALGN_SER_EX_CD_323,
						"Exception while updating CR Offline Status",
						obj, ex);
			}
	}
	
	/**
	 * Update request on success - update CR Offline Status to COMPLETED..
	 *
	 * @param offlineId the offline id
	 * @param userDetails the user details
	 */
	@Transactional(propagation=Propagation.REQUIRES_NEW, rollbackFor = AlignmentServiceException.class)
	public void updateRequestOnSuccess(Long offlineId, UserDetails userDetails) throws AlignmentServiceException{
		try{
			if(null != offlineId && offlineId != 0 && null != userDetails && null != userDetails.getTenantId() && null != userDetails.getUserId() && userDetails.getTenantId() != 0 && userDetails.getUserId() != 0 ){
				LOGGER.info("=========== Update status in t_chng_req_offline table to COMPLETED for offline_id (" + offlineId+ ") ----- Started");
				changeRequestOfflineDAOService.updateCROfflineStatus(ChangeRequestOfflineStatusType.COMPLETED.getId(), offlineId , userDetails.getUserId(),userDetails.getTenantId(), null);
				LOGGER.info("=========== Update status in t_chng_req_offline table to COMPLETED for offline_id (" + offlineId+ ") ----- Ended");
			}else{
				LOGGER.error("=====================OfflineId/UserDetails is NULL=========================");
				throw new AlignmentServiceException(AlignmentServiceExceptionCode.ALGN_SER_EX_CD_333,
						"OfflineId or UserDetails is null",
						null, null);
			}
			}catch (OpservDataAccessException ex) {
				Object[] obj = new Object[] { offlineId };
				LOGGER.error("=====================Exception while updating CR Offline Status=========================");
				throw new AlignmentServiceException(
						AlignmentServiceExceptionCode.ALGN_SER_EX_CD_323,
						"Exception while updating CR Offline Status",
						obj, ex);
			}
	}
	
	/**
	 * Update request on error - update CR Offline Status to CANCELLED...
	 *
	 * @param offlineId the offline id
	 * @param userDetails the user details
	 */
	@Transactional(propagation=Propagation.REQUIRES_NEW, rollbackFor = AlignmentServiceException.class)
	public void updateRequestOnError(Long offlineId, UserDetails userDetails, String failedReason) throws AlignmentServiceException{
		try{
		if(null != offlineId && offlineId != 0 && null != userDetails && null != userDetails.getTenantId() && null != userDetails.getUserId() && userDetails.getTenantId() != 0 && userDetails.getUserId() != 0 ){
			LOGGER.info("=========== Update status in t_chng_req_offline table to CANCELLED for offline_id (" + offlineId+ ") ----- Started");
			changeRequestOfflineDAOService.updateCROfflineStatus(ChangeRequestOfflineStatusType.CANCELLED.getId(), offlineId , userDetails.getUserId(),userDetails.getTenantId(), failedReason);
			LOGGER.info("=========== Update status in t_chng_req_offline table to CANCELLED for offline_id (" + offlineId+ ") ----- Ended");
		}else{
			LOGGER.error("=====================OfflineId/UserDetails is NULL=========================");
			throw new AlignmentServiceException("Exception while updating CR Offline Status","Exception while updating CR Offline Status");
		}
		}catch (AlignmentServiceException ex) {
			Object[] obj = new Object[] { offlineId };
			LOGGER.error("=====================Exception while updating CR Offline Status=========================");
			throw new AlignmentServiceException(
					AlignmentServiceExceptionCode.ALGN_SER_EX_CD_323,
					"Exception while updating CR Offline Status",
					obj, ex);
		}
	}

	/**
	 * Check if any offline items are pending for the given CR
	 * @param crId
	 * @param userDetails
	 * @return
	 */
	@Override
	public boolean hasPendingRequest(Long crId, UserDetails userDetails) throws AlignmentServiceException{
		try{
			if(null != crId && crId != 0 && null != userDetails && null != userDetails.getTenantId() && userDetails.getTenantId() != 0 ){
				LOGGER.info("===========To check if any pending offline request for the CR id (" + crId+ ")");
				return changeRequestOfflineDAOService.hasPendingOfflineRequest(crId, userDetails.getTenantId());
			}else{
				LOGGER.error("=====================OfflineId/UserDetails is NULL=========================");
				throw new AlignmentServiceException("Exception while checking pending offline request","Exception while checking pending offline request");
			}
			}catch (AlignmentServiceException ex) {
				Object[] obj = new Object[] { crId };
				LOGGER.error("=====================Exception while checking pending offline request=========================");
				throw new AlignmentServiceException(
						AlignmentServiceExceptionCode.ALGN_SER_EX_CD_324,
						"Exception while checking pending offline request",
						obj, ex);
			}
	}
	
	
	@Override
	public Map<ChangeRequestOfflineStatusType, Integer> getOffLineStatuses(ChangeRequest changeRequest, UserDetails userDetails) throws ChangeRequestServiceException {

		if(null != changeRequest && changeRequest.getId() != null && changeRequest.getId() != 0 && null != userDetails && null != userDetails.getTenantId() && userDetails.getTenantId() != 0 ){
			Map<ChangeRequestOfflineStatusType,Integer> statusMap = new HashMap<ChangeRequestOfflineStatusType,Integer>();
			try {
				Map<Integer,Integer> statuses = changeRequestOfflineDAOService.getOffLineStatuses(changeRequest, userDetails);
				Iterator<Integer> statusIter = statuses.keySet().iterator();
				
				while(statusIter.hasNext()) {
					Integer statusId = statusIter.next();
					statusMap.put(ChangeRequestOfflineStatusType.getChangeRequestOfflineStatusType(statusId),statuses.get(statusId));
				}
			} catch (OpservDataAccessException ex) {
				Object[] obj = new Object[] { changeRequest.getId() };
				LOGGER.error("=====================Exception while updating CR Offline Status=========================");
				throw new ChangeRequestServiceException(ChangeRequestServiceExceptionCode.CR_SER_EX_CD_017,
						"Exception while getting Offline Status",
						obj, ex);
			}
			return statusMap;
		} else {
			LOGGER.error("=====================changeRequest/UserDetails is NULL=========================");
			throw new ChangeRequestServiceException("Exception while getting offline statuses","Exception while getting offline statuses");

		}
		
	}

	private CustomerAlignment generateTargetCustAlgmntDTO(
			CustomerAlignmentDTO custAlgmntDTO, GeographyAlignment targetGeoAlign){
		CustomerAlignment targetBaseCustAlgmt = new CustomerAlignment();
		Customer newCustomer = new Customer();

		// Target Customer Alignment Data
		newCustomer.setId(custAlgmntDTO.getSourceBaseCustAlgmt().getCustomer().getId());
		targetBaseCustAlgmt.setCustomer(newCustomer);
		targetBaseCustAlgmt.setSalesPosition(targetGeoAlign.getSalesPosition());
		targetBaseCustAlgmt.setStartDate(targetGeoAlign.getStartDate());
		targetBaseCustAlgmt.setEndDate(targetGeoAlign.getEndDate());
		targetBaseCustAlgmt
				.setAffBasedAssignment(custAlgmntDTO.getSourceBaseCustAlgmt().isAffBasedAssignment());
		targetBaseCustAlgmt.setCustomerTargeted(custAlgmntDTO.getSourceBaseCustAlgmt().isCustomerTargeted());
		targetBaseCustAlgmt.setPriority(custAlgmntDTO.getSourceBaseCustAlgmt().getPriority());
		targetBaseCustAlgmt.setCompAligned(custAlgmntDTO.getSourceBaseCustAlgmt().isCompAligned());
		return targetBaseCustAlgmt;
	}
	
	/**
	 * Validate ass date overlapping.
	 * 
	 * @param newCustAlgn
	 *            the new cust algn
	 * @param orgCustAlgn
	 *            the org cust algn
	 * @param userDetails
	 *            the user details
	 * @throws AlignmentServiceException
	 *             the alignment service exception
	 */
	@Transactional
	public void validateDateOverlapForTarCustAlgmnt(CustomerAlignment targetCustomerAlignment,
			UserDetails userDetails)
			throws AlignmentServiceException {

		List<CustomerAlignment> custAlList = customerAlignmentDAOService
				.getCustomerAlignmentFrSP(targetCustomerAlignment.getCustomer().getId(),
						targetCustomerAlignment.getSalesPosition(), userDetails);

		Date proposedEffStDt = targetCustomerAlignment.getStartDate();
		Date proposedEffEndDt = targetCustomerAlignment.getEndDate();

		if (custAlList != null && !custAlList.isEmpty()) {
			LOGGER.info("******* Date validation for Customer Alignment started");
			for (CustomerAlignment custAl : custAlList) {

					if (custAl.getEndDate() != null) {
						if (((custAl.getStartDate().compareTo(proposedEffStDt) < 0 && custAl
								.getStartDate().compareTo(proposedEffEndDt) < 0) && (custAl
								.getEndDate().compareTo(proposedEffStDt) < 0 && custAl
								.getEndDate().compareTo(proposedEffEndDt) < 0))) {
							LOGGER.info("******************** DATES NOT OVERLAPPED CASE ****************** For "
									+ custAl.getCustomer().getId());
						} else {
							if (custAl.getStartDate()
									.compareTo(proposedEffStDt) == 0
									&& custAl.getEndDate().compareTo(
											proposedEffEndDt) == 0) {
								LOGGER.error("******************** DATES OVERLAPPED CASE 1 ****************** For "
										+ custAl.getCustomer().getId());
								throw new AlignmentServiceException(
										"AlignmentServiceExceptionCode.ALGN_SER_EX_CD_229",
										"The customer is already assigned to this SalesPosition in this time period. Please change the Start & End dates");
							} else if (custAl.getStartDate().compareTo(
									proposedEffStDt) > 0) {
								if (custAl.getEndDate().compareTo(
										proposedEffEndDt) >= 0) {
									LOGGER.error("******************** DATES OVERLAPPED CASE 2 ****************** For "
											+ custAl.getCustomer().getId());
									throw new AlignmentServiceException(
											"AlignmentServiceExceptionCode.ALGN_SER_EX_CD_229",
											"The customer is already assigned to this SalesPosition in this time period. Please change the dates accordingly");
								} else {
									LOGGER.error("******************** DATES OVERLAPPED CASE 3 ****************** For "
											+ custAl.getCustomer().getId());
									throw new AlignmentServiceException(
											"AlignmentServiceExceptionCode.ALGN_SER_EX_CD_229",
											"The customer is already assigned to this SalesPosition in this time period. Please change the dates accordingly");
								}
							} else {
								if (custAl.getEndDate().compareTo(
										proposedEffEndDt) >= 0) {
									LOGGER.error("******************** DATES OVERLAPPED CASE 4 ****************** For "
											+ custAl.getCustomer().getId());
									throw new AlignmentServiceException(
											"AlignmentServiceExceptionCode.ALGN_SER_EX_CD_229",
											"The customer is already assigned to this SalesPosition in this time period. Please change the dates accordingly");
								} else {
									LOGGER.error("******************** DATES OVERLAPPED CASE 5 ****************** "
											+ custAl.getCustomer().getId());
									throw new AlignmentServiceException(
											"AlignmentServiceExceptionCode.ALGN_SER_EX_CD_229",
											"The customer is already assigned to this SalesPosition in this time period. Please change the dates accordingly");
								}
							}
						}
					}
			}
		}
		LOGGER.info("************ Date Valildation for Customer Alignment completed");
	}
}
