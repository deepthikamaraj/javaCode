package com.cognizant.opserv.sp.notification.util;

import com.cognizant.opserv.sp.common.ChangeRequestTriggerType;
import com.cognizant.opserv.sp.common.CommonConstants;
import com.cognizant.opserv.sp.model.cr.ChangeRequest;
/******************************************************************************
* @class NotificaionUtil is providing utility methods for notification
* @author Cognizant Technology Solutions
* @version OpServ 3.0
* @since 17/10/2016
*****************************************************************************/
public class NotificaionUtil {

	public static final String PULL_CUSTOMER="Pull Customer";
	
	public static final String PUSH_CUSTOMER="Push Customer";
	
	public static final String EDIT_CUSTOMER="Edit Customer";
	
	public static final String PUSH_ZIP="Push Zip";
	
	public static final Character FLAG_NOT_FOUND='F';
	
	/**
	 * Gets the first level approver(DM1) flag.
	 *____________________
 	  |			|DM1 |DM2 |
 	  |-------------------|
	  |	Push	|N	 |Y   |
	  |	----------------- |
	  |	Pull	|Y	 |N   |
	  |	----------------- |
	  |	Edit	|Y	 |NULL|
	  |	------------------|
	  |	Zip		|N	 | Y  |
	  |_________|____|____|
	 *
	 * @param changeRequest the change request
	 * @return the first level approver flag
	 */
	public static Character getFirstLevelApproverFlag(ChangeRequest changeRequest){
		Character targetAppvrFlg=FLAG_NOT_FOUND;
		if(changeRequest.getCrDefinition().getTrigger().equals(ChangeRequestTriggerType.PUSH_CUSTOMER)){
			targetAppvrFlg=CommonConstants.CHAR_NO;
		}else if(changeRequest.getCrDefinition().getTrigger().equals(ChangeRequestTriggerType.PULL_CUSTOMER)){
			targetAppvrFlg=CommonConstants.CHAR_YES;
		}else if(changeRequest.getCrDefinition().getTrigger().equals(ChangeRequestTriggerType.PUSH_ZIP)){
			targetAppvrFlg=CommonConstants.CHAR_NO;
		}else if(changeRequest.getCrDefinition().getTrigger().equals(ChangeRequestTriggerType.EDIT_CUSTOMER)){
			targetAppvrFlg=CommonConstants.CHAR_YES;
		}
		return targetAppvrFlg;
	}
	/**
	 * Gets the second level approver(DM2) flag.
	 * ____________________
 	  |			|DM1 |DM2 |
 	  |-------------------|
	  |	Push	|N	 |Y   |
	  |	----------------- |
	  |	Pull	|Y	 |N   |
	  |	----------------- |
	  |	Edit	|Y	 |NULL|
	  |	------------------|
	  |	Zip		|N	 | Y  |
	  |_________|____|____|
	 *
	 * @param changeRequest the change request
	 * @return the flag corresponding to second level approver flag
	 */
	public static Character getSecondLevelApproverFlag(ChangeRequest changeRequest){
		Character target2AppvrFlg=FLAG_NOT_FOUND;
		if(changeRequest.getCrDefinition().getTrigger().equals(ChangeRequestTriggerType.PUSH_CUSTOMER)){
			target2AppvrFlg=CommonConstants.CHAR_YES;
		}else if(changeRequest.getCrDefinition().getTrigger().equals(ChangeRequestTriggerType.PULL_CUSTOMER)){
			target2AppvrFlg=CommonConstants.CHAR_NO;
		}else if(changeRequest.getCrDefinition().getTrigger().equals(ChangeRequestTriggerType.PUSH_ZIP)){
			target2AppvrFlg=CommonConstants.CHAR_YES;
		}
		return target2AppvrFlg;
	}
	
	/**
	 * Gets the request type.
	 *
	 * @param changeRequestTriggerType the change request trigger type
	 * @return the reqest type
	 */
	public static String getRequestType(ChangeRequestTriggerType changeRequestTriggerType){
		if(changeRequestTriggerType.equals(ChangeRequestTriggerType.PUSH_CUSTOMER)){
			return  PUSH_CUSTOMER;
		}else if(changeRequestTriggerType.equals(ChangeRequestTriggerType.PULL_CUSTOMER)){
			return  PULL_CUSTOMER;
		}else if(changeRequestTriggerType.equals(ChangeRequestTriggerType.PUSH_ZIP)){
			return  PUSH_ZIP;
		}else if(changeRequestTriggerType.equals(ChangeRequestTriggerType.EDIT_CUSTOMER)){
			return  EDIT_CUSTOMER ;
		}else{
			return "Not found";
		}
	
	}
	
	/**
	 * To check offline item's failed reason is matching with locked.
	 * @param failedReason
	 * @return
	 */
	public static boolean isCustomerfailedDueToLock(String failedReason){
		
		if (failedReason
				.contains(NotificationConstants.LOCK_FALIED_FOR_AFFILIATED_MIRROR)
				|| failedReason
						.contains(NotificationConstants.LOCK_FALIED_FOR_BASE)
				|| failedReason
						.contains(NotificationConstants.LOCK_FALIED_FOR_ROOT_MIRROR)
				|| failedReason
						.contains(NotificationConstants.LOCK_FOR_AFFILIATED_MIRROR_FAILED)
				|| failedReason
						.contains(NotificationConstants.LOCK_FOR_BASE_FAILED)
				|| failedReason
						.contains(NotificationConstants.LOCK_FOR_ROOT_MIRROR_FAILED)
				|| failedReason
						.contains(NotificationConstants.LOCK_FOR_ROOT_MIRROR_FAILED_)) {
			return true;
		}
		return false;
	}
	
	/**
	 * To check offline item's failed reason is matching with locked.
	 * @param failedReason
	 * @return
	 */
	public static boolean isZipfailedDueToLock(String failedReason){
		
		if (failedReason.contains(NotificationConstants.ZIP_LOCKED)) {
			return true;
		}
		return false;
	}
	
}
