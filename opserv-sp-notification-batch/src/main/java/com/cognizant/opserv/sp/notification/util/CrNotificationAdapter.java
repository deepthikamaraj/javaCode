package com.cognizant.opserv.sp.notification.util;

import java.util.ArrayList;
import java.util.List;

import javax.jms.JMSException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.cognizant.opserv.sp.common.ChangeRequestOfflineStatusType;
import com.cognizant.opserv.sp.common.ChangeRequestStatusType;
import com.cognizant.opserv.sp.common.CommonConstants;
import com.cognizant.opserv.sp.core.entity.TChngreqOffline;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.model.cr.ChangeRequest;
import com.cognizant.opserv.sp.notification.cr.service.CRNotificationService;
import com.cognizant.opserv.sp.persistence.dao.service.ChangeRequestDAOService;
import com.cognizant.opserv.sp.persistence.dao.service.ChangeRequestOfflineDAOService;
import com.cognizant.peg.core.exception.BusinessException;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.notification.client.service.NotifyClient;
import com.cognizant.peg.notification.core.common.NotifyMessage;
import com.cognizant.peg.notification.core.common.NotifyServiceException;
import com.cognizant.opserv.sp.common.ChangeRequestTriggerType;

/**
 * ****************************************************************************.
 * @class CrNotificationAdapter ,class to trigger email notification for a change request
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 27/09/2016
 * ***************************************************************************
 */
@Component("crNotificationAdapter")
public class CrNotificationAdapter {

	/** The Constant LOGGER. */
	private static final OpservLogger  LOGGER = OpservLoggerFactory.getOpservLogger(CrNotificationAdapter.class);

	@Autowired
    private NotifyClient notifyClient;
	
	@Autowired 
	private ChangeRequestDAOService changeRequestDAOService;
	
	@Autowired
	private ChangeRequestOfflineDAOService changeRequestOfflineDAOService;
	
	@Autowired 
	@Qualifier("submitNotificationService")
	private CRNotificationService submitNotificationService;
	
	@Autowired 
	@Qualifier("submitApproverNotificationService")
	private CRNotificationService submitApproverNotificationService;
	
	@Autowired 
	@Qualifier("partialSubmitNotificationService")
	private CRNotificationService partialSubmitNotificationService;
	
	@Autowired 
	@Qualifier("cancelledNotificationService")
	private CRNotificationService cancelledNotificationService;
	
	@Autowired 
	@Qualifier("approvalNotificationService")
	private CRNotificationService approvalNotificationService;
	
	@Autowired 
	@Qualifier("rejectionNotificationService")
	private CRNotificationService rejectionNotificationService;
	
	private List<TChngreqOffline> offlineCrs=null;
	/**
	 * To trigger an email notification based on the change request
	 * @param chngReqId
	 * @param tenantId
	 */
	public void triggerCrNotification(Long chngReqId,Short tenantId,String action)throws NotifyServiceException, JMSException, BusinessException{
		ChangeRequest changeRequest =  new ChangeRequest();
		changeRequest.setId(chngReqId);
		changeRequest=changeRequestDAOService.getChangeRequestByChangeRequestId(changeRequest);
		String changeReqType = getCRType(changeRequest);
		List<NotifyMessage> notifyMessages = extractMessage(changeRequest, tenantId, changeReqType,action);
		for (NotifyMessage notifyMessage : notifyMessages) {
			notifyClient.send(notifyMessage);
		}
	}
	
	/**
	 * Extract message.
	 *
	 * @param changeRequest the change request
	 * @param tenantId the tenant id
	 * @param crType the cr type
	 * @return the notify message
	 * @throws BusinessException the business exception
	 */
	private List<NotifyMessage> extractMessage(ChangeRequest changeRequest,Short tenantId,String changeReqType,String action)throws NotifyServiceException, JMSException, BusinessException{
		
		List<NotifyMessage> messages = new ArrayList<NotifyMessage>();
			if(action.equals(CommonConstants.CR_ACTION_SUBMIT)){
				/*****************************************************************/
				String offlineStatus= determineStatusBasedOnoffLineCRs(changeRequest, tenantId);
				if(null !=offlineStatus){
					if(offlineStatus.equals(CommonConstants.CR_OFF_LINE_INITIATED)){
						//TODO
						LOGGER.info("Change request just initiated. No mail template is avilable");
					}else if(offlineStatus.equals(CommonConstants.CR_OFF_LINE_PROCESSING)){
						
						LOGGER.info("Change request is processing. No mail template is avilable");
					}else if(offlineStatus.equals(CommonConstants.CR_OFF_LINE_FULL_SUBMIT)){
						LOGGER.info("FULL CR SUBMIT ----- ");
						/**
						 * For submitter
						 */
						 messages.addAll(submitNotificationService.configureMessage(changeRequest, tenantId, changeReqType));
						 /**
						  * For approver
						  */
						 messages.addAll(submitApproverNotificationService.configureMessage(changeRequest, tenantId, changeReqType));
					}else if(offlineStatus.equals(CommonConstants.CR_OFF_LINE_PARTIAL_SUBMIT)){
						LOGGER.info("PARTIAL CR SUBMIT ----- ");
						/**
						 * For submitter
						 */
						if(chckCancelledViaLocked(changeRequest.getCrDefinition().getTrigger())){
							messages.addAll(partialSubmitNotificationService.configureMessage(changeRequest, tenantId, changeReqType));
						}else{
							LOGGER.info("CR has cancelled items but none of them are cancelled beacase of locked by another user.");
							messages.addAll(submitNotificationService.configureMessage(changeRequest, tenantId, changeReqType));
						}
						 /**
						  * For approver
						  */
						 messages.addAll(submitApproverNotificationService.configureMessage(changeRequest, tenantId, changeReqType));
					}else if(offlineStatus.equals(CommonConstants.CR_OFF_LINE_CANCELLED)){
						LOGGER.info("CR CANCELLED ----- ");
						/**
						 * mail only for sumbmitter
						 */
						if(chckCancelledViaLocked(changeRequest.getCrDefinition().getTrigger())){
							messages.addAll(cancelledNotificationService.configureMessage(changeRequest, tenantId, changeReqType));
						}else{
							LOGGER.info("Status of all items in the CR is cancelled but none of them cancelled because locked by another user. No mail send.");
						}
						
					}else if(offlineStatus.equals(CommonConstants.CR_OFF_LINE_COMPLETED)){
						//TODO
						LOGGER.info("Change request Completed. No mail template is avilable");
					}
				}else{
					LOGGER.error("Change request data is not avilable in offline table to check status");
				}
				/*****************************************************************/
			}else if(action.equals(CommonConstants.CR_ACTION_APPROVE)){
				/*****************************************************************/
				LOGGER.info("Change request Approved.");
				 //to Check whether DM1 and DM2 approves the request
				
				if(changeRequest.getRaisedSalesPosition().getId().longValue() == changeRequest.getRequestedSalesPosition().getId().longValue()){
					//mail to the submitter
					LOGGER.info("Change request Approved - Same district.");
					messages.addAll(approvalNotificationService.configureMessage(changeRequest, tenantId, changeReqType));
				}else{
					LOGGER.info("Change request Approved.Differnt district.");
					boolean fullyApproved=checkforFinalApproval(changeRequest, tenantId);
					if(fullyApproved){
						//mail to the submitter
						LOGGER.info("Change request - DM2 approved");
						messages.addAll(approvalNotificationService.configureMessage(changeRequest, tenantId, changeReqType));
					}else{
						// mail to DM2
						LOGGER.info("Change request - DM1 approved");
						messages.addAll(submitApproverNotificationService.configureMessage(changeRequest, tenantId, changeReqType));
					}
				}
				/*****************************************************************/
			}else if(action.equals(CommonConstants.CR_ACTION_REJECT)){
				/*****************************************************************/
				LOGGER.info("Change request Rejected.");
				messages.addAll(rejectionNotificationService.configureMessage(changeRequest, tenantId, changeReqType));
				/*****************************************************************/
			}
		return messages;
	}
	
	/**
	 * Determine status of the Change request based on offline CR status.
	 *
	 * @param changeRequest the change request
	 * @param tenantId the tenant id
	 * @return the string
	 */
	private String determineStatusBasedOnoffLineCRs(ChangeRequest changeRequest,Short tenantId){
		
		boolean foundCancelled=false;
		boolean foundProcessing=false;
		boolean foundCompleted=false;
		UserDetails userDetails = new UserDetails();
		userDetails.setTenantId(tenantId);
		offlineCrs=changeRequestOfflineDAOService.findTTempByChngreqId(changeRequest, userDetails);
		for (TChngreqOffline tChngreqOffline : offlineCrs) {
			if(tChngreqOffline.getStatusId().equals(ChangeRequestOfflineStatusType.INITIATED.getId())){
				return CommonConstants.CR_OFF_LINE_INITIATED;
			}else if(tChngreqOffline.getStatusId().equals(ChangeRequestOfflineStatusType.PROCESSING.getId())){
				foundProcessing = true;
			}else if(tChngreqOffline.getStatusId().equals(ChangeRequestOfflineStatusType.COMPLETED.getId())){
				foundCompleted = true;
			}else {
				foundCancelled = true;
			}
		}
		/**
		 * Full submit
		 */
		if(foundProcessing ){
			return CommonConstants.CR_OFF_LINE_FULL_SUBMIT;
		}
		/**
		 * Partial submit
		 */
		if(foundCompleted && foundCancelled && !foundProcessing){
			return CommonConstants.CR_OFF_LINE_PARTIAL_SUBMIT;
		}
		/**
		 * full reject
		 */
		if(!foundProcessing && !foundCompleted && foundCancelled){
			return CommonConstants.CR_OFF_LINE_CANCELLED;
		}
		/**
		 * Completed
		 */
		if(foundCompleted && !foundProcessing && !foundCancelled){
			return CommonConstants.CR_OFF_LINE_FULL_SUBMIT;
		}
		return null;
	}
	
	/**
	 * Gets the cR type.
	 *
	 * @param changeRequest the change request
	 * @return the CR type
	 */
	private String getCRType(ChangeRequest changeRequest){
		
		ChangeRequestTriggerType changeRequestTriggerType = changeRequest.getCrDefinition().getTrigger();
		if(changeRequestTriggerType.equals(ChangeRequestTriggerType.PULL_CUSTOMER)||changeRequestTriggerType.equals(ChangeRequestTriggerType.PUSH_CUSTOMER)){
			return CommonConstants.CR_TYPE_CUSTOMER;
		}else if(changeRequestTriggerType.equals(ChangeRequestTriggerType.PUSH_ZIP)){
			return CommonConstants.CR_TYPE_ZIP;
		}else if(changeRequestTriggerType.equals(ChangeRequestTriggerType.EDIT_CUSTOMER)){
			return CommonConstants.CR_TYPE_CUSTOMER_EDIT;
		}
		return null;
	}
	
	/**
	 * Check for final approval.
	 *
	 * @param changeRequest the change request
	 * @param tenantId the tenant id
	 * @return true, if successful
	 */
	private boolean checkforFinalApproval(ChangeRequest changeRequest,
			 Short tenantId){
		Character targetAppvrFlg=NotificaionUtil.getFirstLevelApproverFlag(changeRequest);
		Character target2AppvrFlg=NotificaionUtil.getSecondLevelApproverFlag(changeRequest);
		ChangeRequestStatusType firstLevelApproverStatus =  changeRequestDAOService.getChangeRequestTargetApproverStatus(changeRequest.getId(), targetAppvrFlg, tenantId);
		if(target2AppvrFlg != NotificaionUtil.FLAG_NOT_FOUND){
			ChangeRequestStatusType secondLevelApproverStatus =  changeRequestDAOService.getChangeRequestTargetApproverStatus(changeRequest.getId(), target2AppvrFlg, tenantId);
			if(null != firstLevelApproverStatus && null !=secondLevelApproverStatus){
				if(firstLevelApproverStatus.equals(ChangeRequestStatusType.APPROVED) && secondLevelApproverStatus.equals(ChangeRequestStatusType.APPROVED)){
					return true;
				}
			}
		}else{
			if(firstLevelApproverStatus.equals(ChangeRequestStatusType.APPROVED)){
				return true;
			}
		}
		return false;
	}
	/**
	 * To check at least one item of the change request is cancelled because locked.
	 * @return boolean
	 */
	private boolean chckCancelledViaLocked(ChangeRequestTriggerType changeRequestTriggerType){
		
		if(null != offlineCrs && !offlineCrs.isEmpty()) {
			for (TChngreqOffline tChngreqOffline : offlineCrs) {
				if(tChngreqOffline.getStatusId().equals(ChangeRequestOfflineStatusType.CANCELLED.getId())){
					if(changeRequestTriggerType.equals(ChangeRequestTriggerType.PUSH_ZIP)){
						if(NotificaionUtil.isZipfailedDueToLock(tChngreqOffline.getFailedReason())){
							return true;
						}
					}else {
						if(NotificaionUtil.isCustomerfailedDueToLock(tChngreqOffline.getFailedReason())){
							return true;
						}
					}
				}
			}
		}
		return false;
	}
}
