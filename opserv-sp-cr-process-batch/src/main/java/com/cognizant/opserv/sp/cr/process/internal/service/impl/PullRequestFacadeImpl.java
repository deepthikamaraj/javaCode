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
import com.cognizant.opserv.sp.cr.process.internal.service.CustomerPullOfflineService;
import com.cognizant.opserv.sp.cr.process.internal.service.OfflineRequestService;
import com.cognizant.opserv.sp.cr.process.internal.service.PullRequestFacade;
import com.cognizant.opserv.sp.event.messages.OfflineRequestMessage;
import com.cognizant.opserv.sp.exception.AffiliationServiceException;
import com.cognizant.opserv.sp.exception.AlignmentServiceException;
import com.cognizant.opserv.sp.exception.CallPlanServiceException;
import com.cognizant.opserv.sp.exception.ChangeRequestServiceException;
import com.cognizant.opserv.sp.exception.CustomerServiceException;
import com.cognizant.opserv.sp.exception.MetricExecutionException;
import com.cognizant.opserv.sp.exception.SalesPositionServiceException;
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

/**
 * The Class PullRequestFacadeImpl.
 */
@Service
public class PullRequestFacadeImpl implements PullRequestFacade {

	/** The cust pull offline service. */
	@Autowired
	private CustomerPullOfflineService custPullOfflineService;

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

	/** The Constant LOGGER. */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(PullRequestFacadeImpl.class);
	
	/** The Constant LOGGER. */
	private static final OpservLogger WORKLOG = OpservLoggerFactory.getOpservLogger(CommonConstants.CR_PROCESSING_LOG);

	/**
	 * Pull customer - Placeholder for message. This will be the message object
	 * pulled from online.
	 * 
	 * @param message
	 *            the message
	 * @throws ChangeRequestServiceException
	 *             the change request service exception
	 * @throws AlignmentServiceException
	 *             the alignment service exception
	 * @throws MetricExecutionException
	 * @throws AffiliationServiceException
	 * @throws ViewServiceException 
	 * @throws CallPlanServiceException
	 */
	@Override
	public void pullCustomer(OfflineRequestMessage message) throws ChangeRequestServiceException, AlignmentServiceException, AffiliationServiceException, ViewServiceException, SalesPositionServiceException {

		LOGGER.info("========= PULL CUSTOMER STARTED===========");
		WORKLOG.info("Start of Pull Customer");
		
		UserDetails userDetails = message.getUserDetails();
		CustomerAlignmentDTO custAlgmtDTO = null;
		
		WORKLOG.info("Fetch of Offline Request - Started");
		
		try {
			
			LOGGER.info("========= Get Customer Offline Request  for PULL --- started===========");
			custAlgmtDTO = offlineService.getCustomerOfflineRequest(message, userDetails);
			LOGGER.info("========= Get Customer Offline Request  for PULL --- ended===========");
			
		} catch (AlignmentServiceException e) {
			
			WORKLOG.error("Error in fetching the offline request",e);
			LOGGER.error("Error in getting customer offline request for Pull", e);
			
			if (custAlgmtDTO != null) {
				custPullOfflineService.unLockCustomerAlignment(custAlgmtDTO.getSourceBaseCustAlgmt(), userDetails);
				WORKLOG.error("Unlocked the base customer");
			}
			
			if (message != null && message.getOfflineReqId() != null && message.getChngReqID() != null) {
				
				offlineService.updateRequestOnError(message.getOfflineReqId(), userDetails, "Unable to form the customer DTO");
				crNotifyService.triggerCrNotification(message.getChngReqID(), userDetails, CommonConstants.CR_ACTION_SUBMIT);
				WORKLOG.error("Offline Id is canceled and triggerd for notification");
				
			}
			return;
		}
		WORKLOG.info("Fetch of Offline Request - Completed."+System.lineSeparator());
		
		
		/*********** Validation **************/
		
		WORKLOG.info("Validation - Started");
		
		LOGGER.info("========= Validation  for PULL --- started===========");
		custPullOfflineService.validate(custAlgmtDTO, userDetails);
		LOGGER.info("========= Validation  for PULL --- ended===========");
		ErrorCode errs[] = { ErrorCode.ERROR_CPL_2040, ErrorCode.ERROR_2002, ErrorCode.ERROR_2003, ErrorCode.ERROR_CPL_2046, ErrorCode.ERROR_CPL_2047,
				ErrorCode.ERROR_CPL_2049, ErrorCode.ERROR_CPL_2050 };
		if (errs != null) {
			if (custAlgmtDTO.hasErrors(Arrays.asList(errs))) {
				WORKLOG.error("Validation failed with errors : "+custAlgmtDTO.getErrorCodeString());
				handleError(message, custAlgmtDTO);
				WORKLOG.error("Handled the validation errors. Aborting further processing.");
				return;
			}
		}
		WORKLOG.info("Validation - Completed."+System.lineSeparator());
		
		
		// Completion of validation.

		// Customer Alignment DTO is now complete to proceed to build the
		// child CRs and the line items.
		
		LOGGER.info("========= Get Mirror CRs  for PULL --- started===========");
		custPullOfflineService.getMirrorCRsForPull(custAlgmtDTO, userDetails);
		LOGGER.info("========= Get Mirror CRs  for PULL --- ended===========");
		
		// If mirror CR not created then create or else proceed to next
		// step.
		
		boolean isMirrorCRCreated = false;
		WORKLOG.info("Fetching or Creating the Mirror CRs - Started");
		
		if (custAlgmtDTO.getMirrors() != null && !custAlgmtDTO.getMirrors().isEmpty()) {
			for (MirrorCustAlgmtDTO mirror : custAlgmtDTO.getMirrors()) {
				if (mirror.getChildCR() == null) {
					LOGGER.info("========= Create Mirror CRs  for PULL --- started===========");
					isMirrorCRCreated = custPullOfflineService.createMirrorCR(custAlgmtDTO.getMainCR(), mirror, userDetails);
					if (!isMirrorCRCreated) {
						// TODO : Add appr Error DTO with code.
						ErrorDTO error = new ErrorDTO();
						error.addParam("Customer Id", custAlgmtDTO.getSourceBaseCustAlgmt().getCustomer().getId());
						error.setErrorCode(ErrorCode.ERROR_CPL_2041);
						custAlgmtDTO.addErrorDTO(error);
					}
					
					WORKLOG.info("Mirror CR id : "+mirror.getChildCR().getId()+" for Customer Id : "+custAlgmtDTO.getSourceBaseCustAlgmt().getCustomer().getId()+" created ");
					LOGGER.info("========= Create Mirror CRs  for PULL --- ended===========");
				}
			}
		}

		// TODO : Include the error code which are to be checked for valid
		// method. This is an example only.
		ErrorCode mirrorCRErrors[] = { ErrorCode.ERROR_CPL_2041,ErrorCode.ERROR_CPL_2048 };
		if (mirrorCRErrors != null) {
			if (custAlgmtDTO.hasErrors(Arrays.asList(mirrorCRErrors))) {
				
				WORKLOG.error("Fetching or creating mirror CRs failed with errors : "+custAlgmtDTO.getErrorCodeString());
				handleError(message, custAlgmtDTO);
				WORKLOG.error("Handled the mirrors. Aborting further processing.");
				return;
			}
		}
		WORKLOG.info("Fetching or Creating the Mirror CRs - Completed."+System.lineSeparator());
		// Lock the offline ID for processing
		try {
			
			LOGGER.info("========= lock Request For Processing  for PULL --- started===========");
			WORKLOG.info("Locking the request - Started");
			offlineService.lockRequestForProcessing(message.getOfflineReqId(), userDetails);
			WORKLOG.info("Locking the request - Completed."+System.lineSeparator());
			LOGGER.info("========= lock Request For Processing  for PULL --- ended===========");
			
		} catch (Exception e) {
			// TODO : Create erorr DTO..
			ErrorDTO error = new ErrorDTO();
			error.setErrorCode(ErrorCode.ERROR_CPL_2042);
			custAlgmtDTO.addErrorDTO(error);
			WORKLOG.error("Locking the request failed with errors : "+custAlgmtDTO.getErrorCodeString());
			handleError(message, custAlgmtDTO);
			WORKLOG.error("Handled Locking the request. Aborting further processing.");
			return;
		}

			// Locking the Root customer at all mirrors.
			LOGGER.info("========= Locking the Root customer at all mirrors for PULL--- started===========");
			WORKLOG.info("Locking the Root customer at mirrors - Started.");
			
			if (custAlgmtDTO.getMirrors() != null && !custAlgmtDTO.getMirrors().isEmpty()) {
				Iterator<MirrorCustAlgmtDTO> mirrorIter = custAlgmtDTO.getMirrors().iterator();
				while (mirrorIter.hasNext()) {
					MirrorCustAlgmtDTO mirror = mirrorIter.next();
					boolean isRootCustLocked = false;
					if (null != mirror.getSourceMirrorCustAlgmts() && !mirror.getSourceMirrorCustAlgmts().isEmpty()) {
						for (CustomerAlignment srcCustAlgn : mirror.getSourceMirrorCustAlgmts()) {
							if (srcCustAlgn.getCustomer().getId().equals(custAlgmtDTO.getSourceBaseCustAlgmt().getCustomer().getId())) {
								try {
									isRootCustLocked = custPullOfflineService.lockCustomerAlignment(srcCustAlgn, userDetails);
									WORKLOG.info("The root customer id : "+custAlgmtDTO.getSourceBaseCustAlgmt().getCustomer().getId()+" got locked at the mirror SP Id : " + srcCustAlgn.getSalesPosition().getId() + "=" + isRootCustLocked);
									LOGGER.info("========= The Root customer got locked at the mirror SP " + srcCustAlgn.getSalesPosition().getId() + isRootCustLocked +"===========");
									srcCustAlgn.setLocked(isRootCustLocked);
								} catch (AlignmentServiceException ae) {
									LOGGER.error("Couldn't lock the root customer at the mirror");
									isRootCustLocked = false;
								}
								break;
							}
						}
					}
					// If the Root customer cannot be locked for the mirror,
					// then this mirror doesn't make sense to be processed and
					// hence moving it to invalid mirrors.
					if (!isRootCustLocked) {
						// TODO : Add error DTO.
						ErrorDTO error = new ErrorDTO();
						error.setErrorCode(ErrorCode.ERROR_CPL_2043);
						custAlgmtDTO.addErrorDTO(error);
						// TODO : Unlock the base customer and notify.
						// custAlgmtDTO.addInvalidMirrorDTO(mirror);
						// mirrorIter.remove();
						WORKLOG.info("Locking the Root customer at mirrors failed with errors : "+custAlgmtDTO.getErrorCodeString());
						handleError(message, custAlgmtDTO);
						WORKLOG.error("Handled Locking the Root customer at the mirrors. Aborting further processing.");
						return;
					} else {

					}
				}
			}
			
			LOGGER.info("========= Locking the Root customer at all mirrors for PULL--- ended ===========");
			WORKLOG.info("Locking the Root customer at all mirrors - Completed."+System.lineSeparator());
			
			
			// Locking the valid affliations at the base.
			LOGGER.info("========= Locking the valid affliations at the base for PULL --- started ===========");
			if (custAlgmtDTO.getSourceBaseAffCustAlgmts() != null && !custAlgmtDTO.getSourceBaseAffCustAlgmts().isEmpty()) {
				for (CustomerAlignment affCust : custAlgmtDTO.getSourceBaseAffCustAlgmts()) {
					boolean isAffCustLocked = custPullOfflineService.lockCustomerAlignment(affCust, userDetails);
					
					LOGGER.info("========= The affiliated customer got locked at the base SP " + affCust.getSalesPosition().getId() + isAffCustLocked +"===========");
					WORKLOG.info("The affiliated customer Id : "+affCust.getCustomer().getId()+" got locked at the mirror SP Id : " + affCust.getSalesPosition().getId() + "=" + isAffCustLocked);
					
					affCust.setLocked(isAffCustLocked);
					if (!isAffCustLocked) {
						ErrorDTO error = new ErrorDTO();
						error.setErrorCode(ErrorCode.ERROR_CPL_2044);
						custAlgmtDTO.addErrorDTO(error);
						WORKLOG.info("Locking the Affiliated customer at base failed with errors : "+custAlgmtDTO.getErrorCodeString());
						handleError(message, custAlgmtDTO);
						WORKLOG.error("Handled Locking the Affiliated customer at the base. Aborting further processing.");
						return;
					}
				}
			}
			LOGGER.info("========= Locking the valid affliations at the base for PULL --- ended ===========");
			WORKLOG.info("Locking the Affiliated customers at base - Completed."+System.lineSeparator());
			
			
			// Locking the affilated customers (non-root) at the mirrors....
			LOGGER.info("========= Locking the affilated customers (non-root) at the mirrors....for PULL--- started ===========");
			WORKLOG.info("Locking the Affiliated customers at mirrors - Started.");
			
			if (null != custAlgmtDTO.getMirrors() && !custAlgmtDTO.getMirrors().isEmpty()) {
				for (MirrorCustAlgmtDTO mirror : custAlgmtDTO.getMirrors()) {
					if (null != mirror.getSourceMirrorCustAlgmts() && !mirror.getSourceMirrorCustAlgmts().isEmpty()) {
						for (CustomerAlignment srcCustAlgn : mirror.getSourceMirrorCustAlgmts()) {
							if (!srcCustAlgn.getCustomer().getId().equals(custAlgmtDTO.getSourceBaseCustAlgmt().getCustomer().getId())) {
								boolean isAffCustAtMirrLocked = custPullOfflineService.lockCustomerAlignment(srcCustAlgn, userDetails);
								LOGGER.info("========= The affiliated customer got locked at the mirror SP " + srcCustAlgn.getSalesPosition().getId() + isAffCustAtMirrLocked +"===========");
								WORKLOG.info("The affiliated customer Id : "+srcCustAlgn.getCustomer().getId()+" got locked at the mirror SP Id : " + srcCustAlgn.getSalesPosition().getId() + "=" + isAffCustAtMirrLocked);
								srcCustAlgn.setLocked(isAffCustAtMirrLocked);
								if (!isAffCustAtMirrLocked) {
									ErrorDTO error = new ErrorDTO();
									error.setErrorCode(ErrorCode.ERROR_CPL_2045);
									custAlgmtDTO.addErrorDTO(error);
									WORKLOG.info("Locking the Affiliated customer at mirror failed with errors : "+custAlgmtDTO.getErrorCodeString());
									handleError(message, custAlgmtDTO);
									WORKLOG.error("Handled Locking the Affiliated customer at the mirrors. Aborting further processing.");
									return;
								}
							}
						}
					}
				}
			}
			WORKLOG.info("Locking the Affiliated customers at mirrors - Completed."+System.lineSeparator());
			LOGGER.info("========= Locking the affilated customers (non-root) at the mirrors....for PULL--- ended ===========");
			
			
			WORKLOG.info("Processing for CR Line Items - Started.");
			try {
				
				WORKLOG.info("Processing for Source - Started");
				LOGGER.info("========= Process Source On Submit for PULL--- started ===========");
				custPullOfflineService.processSourceOnSubmit(custAlgmtDTO, userDetails);
				LOGGER.info("========= Process Source On Submit for PULL--- ended ===========");
				WORKLOG.info("Processing for Source - Completed.");
				
				WORKLOG.info("Processing for Target - Started");
				LOGGER.info("========= Process Target On Submit for PULL--- started ===========");
				custPullOfflineService.processTargetOnSubmit(custAlgmtDTO, userDetails);
				LOGGER.info("========= Process Target On Submit for PULL--- ended ===========");
				WORKLOG.info("Processing for Target - Completed.");

			} catch (ChangeRequestServiceException | CallPlanServiceException | AlignmentServiceException | CustomerServiceException e) {
				ErrorDTO error = new ErrorDTO();
				error.setErrorCode(ErrorCode.ERROR_CR_3000);
				custAlgmtDTO.addErrorDTO(error);
				WORKLOG.info("Processing for Source and Target failed with errors : "+custAlgmtDTO.getErrorCodeString());
				WORKLOG.info("Will be reverting the CR Line Items");
				revertCRLineItems(custAlgmtDTO, userDetails);
				handleError(message, custAlgmtDTO);
				WORKLOG.info("Handled the Source and Target Line items. Aborting further processing.");
				return;
			}
			
			WORKLOG.info("Processing for CR Line Items - Completed."+System.lineSeparator());
			LOGGER.info("========= Update Request On Success for PULL--- started ===========");
			offlineService.updateRequestOnSuccess(message.getOfflineReqId(), userDetails);
			WORKLOG.info("Offline Request is marked as Completed.");
			LOGGER.info("========= Update Request On Success for PULL--- ended ===========");

		WORKLOG.info("Post Processing - Started.");
		handlePostProcessing(message, custAlgmtDTO);
		WORKLOG.info("Post Processing - Completed."+System.lineSeparator());
		
		
		WORKLOG.info("Pull Customer - Completed."+System.lineSeparator());
		LOGGER.info("=============== Execute of Pull Customer -- ended");
	}

	private void handleError(OfflineRequestMessage message, CustomerAlignmentDTO custAlgmtDTO) throws ChangeRequestServiceException, AlignmentServiceException, ViewServiceException {
		// Unlock the Root Customer at base
		WORKLOG.info("Unlocked the customer at the base.");
		custPullOfflineService.unLockCustomerAlignment(custAlgmtDTO.getSourceBaseCustAlgmt(), message.getUserDetails());

		// Unlock the Affiliated Customer at base
		if (custAlgmtDTO.getSourceBaseAffCustAlgmts() != null && !custAlgmtDTO.getSourceBaseAffCustAlgmts().isEmpty()) {
			for (CustomerAlignment affCustomerAlignment : custAlgmtDTO.getSourceBaseAffCustAlgmts()) {
				if (affCustomerAlignment.isLocked()) {
					custPullOfflineService.unLockCustomerAlignment(affCustomerAlignment, message.getUserDetails());
					WORKLOG.info("Unlocked the affiliated customer id : "+affCustomerAlignment.getCustomer().getId()+" at the base which was already locked in this process.");
				}
			}

		}

		// Unlock the Root and Affilaited Customers at Mirrors
		if (custAlgmtDTO.getMirrors() != null && !custAlgmtDTO.getMirrors().isEmpty()) {
			for (MirrorCustAlgmtDTO mirrorCustAlgmtDTO : custAlgmtDTO.getMirrors()) {
				if (mirrorCustAlgmtDTO.getSourceMirrorCustAlgmts() != null && !mirrorCustAlgmtDTO.getSourceMirrorCustAlgmts().isEmpty()) {
					for (CustomerAlignment mirrCustAl : mirrorCustAlgmtDTO.getSourceMirrorCustAlgmts()) {
						if (mirrCustAl.isLocked()) {
							custPullOfflineService.unLockCustomerAlignment(mirrCustAl, message.getUserDetails());
							WORKLOG.info("Unlocked the root / affiliated customer id : "+mirrCustAl.getCustomer().getId()+" at the mirrors which was already locked in this process.");
						}
					}
				}
			}
		}
		offlineService.updateRequestOnError(message.getOfflineReqId(), message.getUserDetails(), custAlgmtDTO.getErrorCodeString());
		WORKLOG.info("Updated the Offline Request as CANCELLED due to errors.");
		handlePostProcessing(message, custAlgmtDTO);
	}

	private void handlePostProcessing(OfflineRequestMessage message, CustomerAlignmentDTO custAlgmtDTO) throws ChangeRequestServiceException,
			AlignmentServiceException {

		UserDetails userDetails = message.getUserDetails();
		boolean stillToProcess = offlineService.hasPendingRequest(message.getChngReqID(), userDetails);
		LOGGER.info("========= Has Pending Request for Pull--- ended ===== and the result is " + stillToProcess);
		if (stillToProcess) {
			WORKLOG.info("The CR has some more items to be processed. Hence cannot proceed to compute metrics and assigning of approvers.");
			return;
		}
		ChangeRequest changeRequest = new ChangeRequest();
		changeRequest.setId(message.getChngReqID());
		Map<ChangeRequestOfflineStatusType, Integer> statusMap = offlineService.getOffLineStatuses(changeRequest, userDetails);

		if (statusMap.containsKey(ChangeRequestOfflineStatusType.INITIATED) || statusMap.containsKey(ChangeRequestOfflineStatusType.PROCESSING)) {
			// Still something is in progress.
			return;
		}

		if (!statusMap.containsKey(ChangeRequestOfflineStatusType.COMPLETED)) {
			// Nothing is completed.
			WORKLOG.info("Couldn't find any one COMPLETED offline line item. Hence cannot proceed to compute metrics and assigning of approvers.");
			notifyAndOtherEventProcessing(message, custAlgmtDTO);				
			return;
		}
		WORKLOG.info("One or more offline line items have been completed. Checking for Metrics.");
		boolean metricResult = custPullOfflineService.handleMetricsOnSubmit(custAlgmtDTO, userDetails);
		LOGGER.info("========= Metric Result Status is " + metricResult);	LOGGER.info("========= Metric Result Status is " + metricResult);
		if (metricResult) {
			
			WORKLOG.info("Metrics Passed. Assigning Approvers for Source and Target");
			custPullOfflineService.assignApproversForSourceAndTarget(custAlgmtDTO, userDetails);
			WORKLOG.info("Updating the Change Request for Pending Approval and initiating CR Workflow");
			custPullOfflineService.updateChangeRequests(custAlgmtDTO, userDetails);
			
		} else {
			
			WORKLOG.info("Metrics violation found. Cannot proceed to approval of this CR and hence Auto rejecting.");
			crCommonService.autoRejectChangeRequestOnSubmit(custAlgmtDTO.getMainCR(), userDetails);
			WORKLOG.info("Auto rejecting - Completed.");
		}
		notifyAndOtherEventProcessing(message, custAlgmtDTO);

	}

	private void revertCRLineItems(CustomerAlignmentDTO custAlgmtDTO, UserDetails userDetails) throws ChangeRequestServiceException {
		
		WORKLOG.info("Reverting CR Lines for Source - Started");
			if (custAlgmtDTO.getCrLineItemsForSource() != null && !custAlgmtDTO.getCrLineItemsForSource().isEmpty()) {
				for (CustomerAlignmentChangeRequestDetails crSrcLineItem : custAlgmtDTO.getCrLineItemsForSource()) {
					crOfflineService.updateCRLineStatusForCustAlgmt(crSrcLineItem.getParentChangeRequest().getId(), crSrcLineItem.getOldCustomerAlignment()
							.getId(), ChangeRequestStatusType.CANCELLED.getId(), userDetails);
				}
			}
			WORKLOG.info("Reverting CR Lines for Source - Completed.");
			
			WORKLOG.info("Reverting CR Lines for Target - Started");
			if (custAlgmtDTO.getCrLineItemsForTarget() != null && !custAlgmtDTO.getCrLineItemsForTarget().isEmpty()) {
				for (CustomerAlignmentChangeRequestDetails crTargetLineItem : custAlgmtDTO.getCrLineItemsForTarget()) {
					crOfflineService.updateCRLineStatusForCustAlgmt(crTargetLineItem.getParentChangeRequest().getId(), crTargetLineItem
							.getNewCustomerAlignment().getId(), ChangeRequestStatusType.CANCELLED.getId(), userDetails);
				}
			}
			WORKLOG.info("Reverting CR Lines for Target - Completed.");
	}
	
	private void notifyAndOtherEventProcessing(OfflineRequestMessage message, CustomerAlignmentDTO custAlgmtDTO) {
		
		WORKLOG.info("Email Notification triggered");
		crNotifyService.triggerCrNotification(message.getChngReqID(),message.getUserDetails(),CommonConstants.CR_ACTION_SUBMIT);
		custPullOfflineService.emitPostProcessEvents(custAlgmtDTO, message.getUserDetails());
		return;
	}

}
