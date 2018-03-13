package com.cognizant.opserv.sp.cr.process.internal.service.impl;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.opserv.sp.common.ChangeRequestOfflineStatusType;
import com.cognizant.opserv.sp.common.ChangeRequestStatusType;
import com.cognizant.opserv.sp.common.CommonConstants;
import com.cognizant.opserv.sp.cr.process.dto.CustomerAlignmentDTO;
import com.cognizant.opserv.sp.cr.process.dto.ErrorCode;
import com.cognizant.opserv.sp.cr.process.dto.ErrorDTO;
import com.cognizant.opserv.sp.cr.process.dto.MirrorCustAlgmtDTO;
import com.cognizant.opserv.sp.cr.process.internal.service.ChangeRequestOfflineService;
import com.cognizant.opserv.sp.cr.process.internal.service.CustomerEditOfflineService;
import com.cognizant.opserv.sp.cr.process.internal.service.EditRequestFacade;
import com.cognizant.opserv.sp.cr.process.internal.service.OfflineRequestService;
import com.cognizant.opserv.sp.event.messages.OfflineRequestMessage;
import com.cognizant.opserv.sp.exception.AlignmentServiceException;
import com.cognizant.opserv.sp.exception.CallPlanServiceException;
import com.cognizant.opserv.sp.exception.ChangeRequestServiceException;
import com.cognizant.opserv.sp.exception.SalesPositionServiceException;
import com.cognizant.opserv.sp.exception.MetricExecutionException;
import com.cognizant.opserv.sp.exception.ViewServiceException;
import com.cognizant.opserv.sp.model.CustomerAlignment;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.model.cr.ChangeRequest;
import com.cognizant.opserv.sp.model.cr.CustomerAlignmentChangeRequestDetails;
import com.cognizant.opserv.sp.service.common.ChangeRequestCommonService;
import com.cognizant.opserv.sp.service.notification.CRTriggerNotificationService;
import com.cognizant.opserv.sp.service.util.NotificationUtil;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;

@Service
public class EditRequestFacadeImpl implements EditRequestFacade {

	/** The Constant LOGGER. */
	private static final OpservLogger LOGGER = OpservLoggerFactory
			.getOpservLogger(EditRequestFacadeImpl.class);
	
	/** The Constant LOGGER. */
	private static final OpservLogger WORKLOG = OpservLoggerFactory.getOpservLogger(CommonConstants.CR_PROCESSING_LOG);

	/** The cust Edit offline service. */
	@Autowired
	private CustomerEditOfflineService custEditOfflineService;

	/** The offline service. */
	@Autowired
	private OfflineRequestService offlineService;
		
	@Autowired
	private NotificationUtil notificationUtil;
	
	@Autowired
	private CRTriggerNotificationService crNotifyService;
	
	@Autowired
	private ChangeRequestCommonService crCommonService;
	
	/** The cr offline service. */
	@Autowired
	private ChangeRequestOfflineService crOfflineService;
	
	
	@Override
	public void editCustomer(OfflineRequestMessage message) throws ChangeRequestServiceException, AlignmentServiceException, MetricExecutionException, CallPlanServiceException, ViewServiceException, SalesPositionServiceException  {
		
		WORKLOG.info("Start of Edit Customer");
		LOGGER.info("=============== Execute of editCustomer -- started");
		// TODO Auto-generated method stub
		UserDetails userDetails = message.getUserDetails();
		CustomerAlignmentDTO custAlgmtDTO = null;
		WORKLOG.info("Fetch of Offline Request - Started");
		try {
			
			LOGGER.info("========= Get Customer Offline Request  for EDIT --- started===========");
			custAlgmtDTO = offlineService.getCustomerOfflineRequest(message,userDetails);
			LOGGER.info("========= Get Customer Offline Request  for EDIT --- started===========");
		} catch (AlignmentServiceException e) {
			WORKLOG.error("Error in fetching the offline request",e);
			LOGGER.error("Error in getting customer offline request for Edit",e);
			if(custAlgmtDTO != null) {
				custEditOfflineService.unLockCustomerAlignment(custAlgmtDTO.getSourceBaseCustAlgmt(), userDetails);
				WORKLOG.error("Unlocked the base customer");
			}
			if (message != null && message.getOfflineReqId() != null
					&& message.getChngReqID() != null) {
				offlineService.updateRequestOnError(message.getOfflineReqId(),userDetails, "Unable to form the customer DTO");
				crNotifyService.triggerCrNotification(message.getChngReqID(),
						userDetails,CommonConstants.CR_ACTION_SUBMIT);
				WORKLOG.error("Offline Id is canceled and triggerd for notification");
				
			}
			return;
		}
		
		
			/*********** Validation **************/
		    WORKLOG.info("Validation - Started");
			LOGGER.info("========= Validation  for EDIT --- started===========");
			custEditOfflineService.validate(custAlgmtDTO, userDetails);
			ErrorCode errs[] = {ErrorCode.ERROR_CE_2080,ErrorCode.ERROR_2003,ErrorCode.ERROR_CE_2081,ErrorCode.ERROR_CE_2085};
			if(errs != null){
				if(custAlgmtDTO.hasErrors(Arrays.asList(errs))) {
					WORKLOG.error("Validation failed with errors : "+custAlgmtDTO.getErrorCodeString());
					handleError(message, custAlgmtDTO);
					WORKLOG.error("Handled the validation errors. Aborting further processing.");
					return;
				}
			}
			LOGGER.info("========= Validation  for EDIT --- ended===========");
			WORKLOG.info("Validation - Completed."+System.lineSeparator());
			// Completion of validation. 
	
			LOGGER.info("========= Get Mirror CRs  for EDIT --- started===========");
			boolean isMirrorCRCreated = false;
			WORKLOG.info("Fetching or Creating the Mirror CRs - Started");
			custEditOfflineService.getMirrorCRsForEdit(custAlgmtDTO, userDetails);
			
			// If mirror CR not created then create or else proceed to next step.
			if(custAlgmtDTO.getMirrors() != null && !custAlgmtDTO.getMirrors().isEmpty()){
				for(MirrorCustAlgmtDTO mirror : custAlgmtDTO.getMirrors()) {
					if(mirror.getChildCR() == null) {
						LOGGER.info("========= Create Mirror CRs  for EDIT --- started===========");
						isMirrorCRCreated=custEditOfflineService.createMirrorCR(custAlgmtDTO.getMainCR(),mirror, userDetails);
						if(!isMirrorCRCreated) {
							ErrorDTO error = new ErrorDTO();
							error.addParam("Customer Id", custAlgmtDTO.getSourceBaseCustAlgmt().getCustomer().getId());
							error.setErrorCode(ErrorCode.ERROR_CE_2082);
							custAlgmtDTO.addErrorDTO(error);
							
						}
						WORKLOG.info("Mirror CR id : "+mirror.getChildCR().getId()+" for Customer Id : "+custAlgmtDTO.getSourceBaseCustAlgmt().getCustomer().getId()+" created ");
						LOGGER.info("========= Create Mirror CRs  for EDIT --- ended===========");
				}
			}
		}
			//TODO : Include the error code which are to be checked for valid method. This is an example only.
			ErrorCode mirrorCRErrors[] = {ErrorCode.ERROR_CE_2082};
			if(mirrorCRErrors != null){
				if(custAlgmtDTO.hasErrors(Arrays.asList(mirrorCRErrors))) {
					WORKLOG.error("Fetching or creating mirror CRs failed with errors : "+custAlgmtDTO.getErrorCodeString());
					handleError(message, custAlgmtDTO);
					WORKLOG.error("Handled the mirrors. Aborting further processing.");
					return;
				}
			}
			LOGGER.info("========= Get Mirror CRs  for EDIT --- ended===========");
			WORKLOG.info("Fetching or Creating the Mirror CRs - Completed."+System.lineSeparator());
		
			try{
				// Lock the offline ID for processing
				WORKLOG.info("Locking the request - Started");
				LOGGER.info("========= Lock Offline Request For Processing  for EDIT --- started===========");
				offlineService.lockRequestForProcessing(message.getOfflineReqId(), userDetails);
				}catch(Exception e) {
				ErrorDTO error = new ErrorDTO();
				error.setErrorCode(ErrorCode.ERROR_CE_2083);
				custAlgmtDTO.addErrorDTO(error);
				WORKLOG.error("Locking the request failed with errors : "+custAlgmtDTO.getErrorCodeString());
				handleError(message, custAlgmtDTO);
				WORKLOG.error("Handled Locking the request. Aborting further processing.");
				return;
				}
				LOGGER.info("========= Lock Offline Request For Processing  for EDIT --- ended===========");
			    WORKLOG.info("Locking the request - Completed."+System.lineSeparator());
				
			
			// Locking the Root customer at all mirrors.
			LOGGER.info("========= Locking the Root customer at all mirrors --- started===========");
			WORKLOG.info("Locking the Root customer at mirrors - Started.");
			if(custAlgmtDTO.getMirrors() != null && !custAlgmtDTO.getMirrors().isEmpty()){
				Iterator<MirrorCustAlgmtDTO> mirrorIter = custAlgmtDTO.getMirrors().iterator();
				while(mirrorIter.hasNext()) {
					MirrorCustAlgmtDTO mirror = mirrorIter.next();
					boolean isRootCustLocked = false;
					if(mirror.getSourceMirrorCustAlgmts() != null && !mirror.getSourceMirrorCustAlgmts().isEmpty()){
					for(CustomerAlignment srcCustAlgn : mirror.getSourceMirrorCustAlgmts()) {
						if(srcCustAlgn.getCustomer().getId().equals(custAlgmtDTO.getSourceBaseCustAlgmt().getCustomer().getId())) {
							try {
								WORKLOG.info("The root customer id : "+custAlgmtDTO.getSourceBaseCustAlgmt().getCustomer().getId()+" got locked at the mirror SP Id : " + srcCustAlgn.getSalesPosition().getId() + "=" + isRootCustLocked);
								isRootCustLocked = custEditOfflineService.lockCustomerAlignment(srcCustAlgn, userDetails);
								LOGGER.info("========= The root customer got locked at the mirror SP " + srcCustAlgn.getSalesPosition().getId() + isRootCustLocked +"===========");
								srcCustAlgn.setLocked(isRootCustLocked);
							} catch (AlignmentServiceException ae) {
								LOGGER.error("Couldn't lock the root customer at the mirror");
								isRootCustLocked = false;
							}
							break;
						}
					}
					}
					// If the Root customer cannot be locked for the mirror, then this mirror doesn't make sense to be processed and hence moving it to invalid mirrors.
					if(!isRootCustLocked) {
					    //custAlgmtDTO.addInvalidMirrorDTO(mirror);
						//mirrorIter.remove();
						ErrorDTO error = new ErrorDTO();
						error.setErrorCode(ErrorCode.ERROR_CE_2084);
						custAlgmtDTO.addErrorDTO(error);
						WORKLOG.info("Locking the Root customer at mirrors failed with errors : "+custAlgmtDTO.getErrorCodeString());
						handleError(message, custAlgmtDTO);
						WORKLOG.error("Handled Locking the Root customer at the mirrors. Aborting further processing.");
						return;
					}
				}
			}
			WORKLOG.info("Locking the Root customer at all mirrors - Completed."+System.lineSeparator());
			LOGGER.info("========= Locking the Root customer at all mirrors --- ended ===========");	
			
			WORKLOG.info("Processing for CR Line Items - Started.");
			LOGGER.info("========= Process Source On Submit for EDIT--- started ===========");
			try {
				WORKLOG.info("Processing on submit- Started");
				custEditOfflineService.processOnSubmit(custAlgmtDTO,userDetails);
				WORKLOG.info("Processing on submit- Completed.");

			} catch (ChangeRequestServiceException | CallPlanServiceException
					| AlignmentServiceException e) {
				ErrorDTO error = new ErrorDTO();
				error.setErrorCode(ErrorCode.ERROR_CR_3001);
				custAlgmtDTO.addErrorDTO(error);
				WORKLOG.info("Processing on submit failed with errors : "+custAlgmtDTO.getErrorCodeString());
				WORKLOG.info("Will be reverting the CR Line Items");
				revertCRLineItems(custAlgmtDTO, userDetails);
				handleError(message, custAlgmtDTO);
				WORKLOG.info("Handled Line items. Aborting further processing.");
				return;
			}
			WORKLOG.info("Processing for CR Line Items - Completed."+System.lineSeparator());
			LOGGER.info("========= Process Source On Submit for EDIT--- ended ===========");

			LOGGER.info("========= Update Offline Request On Success for EDIT--- started ===========");
			offlineService.updateRequestOnSuccess(message.getOfflineReqId(), userDetails);
			WORKLOG.info("Offline Request is marked as Completed.");
			
			WORKLOG.info("Post Processing - Started.");
			handlePostProcessing(message,custAlgmtDTO);
			WORKLOG.info("Post Processing - Completed."+System.lineSeparator());
			LOGGER.info("========= Update Offline Request On Success for EDIT--- ended ===========");

			WORKLOG.info("Edit Customer - Completed."+System.lineSeparator());
		LOGGER.info("=============== Execute of editCustomer -- ended");
		//TODO : Notification in bulk.
	}
	
	private void handleError(OfflineRequestMessage message, CustomerAlignmentDTO custAlgmtDTO) throws ChangeRequestServiceException, AlignmentServiceException, MetricExecutionException, ViewServiceException {
		//Unlock the Root Customer at base 
		
		custEditOfflineService.unLockCustomerAlignment(custAlgmtDTO.getSourceBaseCustAlgmt(), message.getUserDetails());
		WORKLOG.info("Unlocked the customer at the base.");
		
		//Unlock the Root and Affilaited Customers at Mirrors
		if(custAlgmtDTO.getMirrors() != null && !custAlgmtDTO.getMirrors().isEmpty()){
		for(MirrorCustAlgmtDTO mirrorCustAlgmtDTO : custAlgmtDTO.getMirrors()){
				if(mirrorCustAlgmtDTO.getSourceMirrorCustAlgmts() != null && !mirrorCustAlgmtDTO.getSourceMirrorCustAlgmts().isEmpty()){
					for(CustomerAlignment mirrCustAl : mirrorCustAlgmtDTO.getSourceMirrorCustAlgmts()){
						if(mirrCustAl.isLocked()) {
							custEditOfflineService.unLockCustomerAlignment(mirrCustAl, message.getUserDetails());
							WORKLOG.info("Unlocked the root / affiliated customer id : "+mirrCustAl.getCustomer().getId()+" at the mirrors which was already locked in this process.");
							
						}
					}
				}
			}
		}
		offlineService.updateRequestOnError(message.getOfflineReqId(), message.getUserDetails(), custAlgmtDTO.getErrorCodeString());
		WORKLOG.info("Updated the Offline Request as CANCELLED due to errors.");
		handlePostProcessing(message,custAlgmtDTO);
}
	
	private void revertCRLineItems(CustomerAlignmentDTO custAlgmtDTO, UserDetails userDetails) throws ChangeRequestServiceException {
		
		WORKLOG.info("Reverting CR Lines - Started.");
			if(custAlgmtDTO.getCrLineItemsForSource() != null && !custAlgmtDTO.getCrLineItemsForSource().isEmpty()){
				for(CustomerAlignmentChangeRequestDetails crSrcLineItem : custAlgmtDTO.getCrLineItemsForSource()) {
					crOfflineService.updateCRLineStatusForCustAlgmt(crSrcLineItem.getParentChangeRequest().getId(), crSrcLineItem.getNewCustomerAlignment().getId(), ChangeRequestStatusType.CANCELLED.getId(), userDetails);
				}
			}
		WORKLOG.info("Reverting CR Lines  - Completed.");
	}

   private void handlePostProcessing(OfflineRequestMessage message, CustomerAlignmentDTO custAlgmtDTO) throws ChangeRequestServiceException, AlignmentServiceException, MetricExecutionException {
	
		UserDetails userDetails = message.getUserDetails();
		boolean stillToProcess = offlineService.hasPendingRequest(message.getChngReqID(), userDetails);
		LOGGER.info("========= Has Pending Request for PUSH--- ended ===== and the result is " + stillToProcess);
		if(stillToProcess) {
			WORKLOG.info("The CR has some more items to be processed. Hence cannot proceed to compute metrics and assigning of approvers.");
			return;
		}
		ChangeRequest changeRequest = new ChangeRequest();
		changeRequest.setId(message.getChngReqID());
		Map<ChangeRequestOfflineStatusType, Integer> statusMap = offlineService.getOffLineStatuses(changeRequest, userDetails);
		
		if(statusMap.containsKey(ChangeRequestOfflineStatusType.INITIATED) || statusMap.containsKey(ChangeRequestOfflineStatusType.PROCESSING)) {
			// Still something is in progress.
			return;
		}
		
		if(!statusMap.containsKey(ChangeRequestOfflineStatusType.COMPLETED)) {
			// Nothing is completed.
			WORKLOG.info("Couldn't find any one COMPLETED offline line item. Hence cannot proceed to compute metrics and assigning of approvers.");
			notifyAndOtherEventProcessing(message, custAlgmtDTO);
			return;
		}
		WORKLOG.info("One or more offline line items have been completed. Checking for Metrics.");
		boolean metricResult = custEditOfflineService.handleMetricsOnSubmit(custAlgmtDTO,userDetails);
		LOGGER.info("========= Metric Result Status is " + metricResult);
		if(metricResult) {
			WORKLOG.info("Metrics Passed. Assigning Approvers");
			custEditOfflineService.assignApprovers(custAlgmtDTO, userDetails);
			WORKLOG.info("Updating the Change Request for Pending Approval and initiating CR Workflow");
			custEditOfflineService.updateChangeRequests(custAlgmtDTO, userDetails);
		} else {
			WORKLOG.info("Metrics violation found. Cannot proceed to approval of this CR and hence Auto rejecting.");
			crCommonService.autoRejectChangeRequestOnSubmit(custAlgmtDTO.getMainCR(),userDetails);
			WORKLOG.info("Auto rejecting - Completed.");
		}
		notifyAndOtherEventProcessing(message, custAlgmtDTO);
}

	
	private void notifyAndOtherEventProcessing(OfflineRequestMessage message, CustomerAlignmentDTO custAlgmtDTO) {
		WORKLOG.info("Email Notification triggered");
		crNotifyService.triggerCrNotification(message.getChngReqID(),message.getUserDetails(),CommonConstants.CR_ACTION_SUBMIT);
		custEditOfflineService.emitPostProcessEvents(custAlgmtDTO, message.getUserDetails());
		return;
	}
	
}
