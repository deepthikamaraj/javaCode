package com.cognizant.opserv.sp.notification.cr.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jms.JMSException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.opserv.sp.common.AllocationType;
import com.cognizant.opserv.sp.common.ChangeRequestStatusType;
import com.cognizant.opserv.sp.model.Employee;
import com.cognizant.opserv.sp.model.cr.ChangeRequest;
import com.cognizant.opserv.sp.notification.util.NotificaionUtil;
import com.cognizant.opserv.sp.notification.util.NotificationConstants;
import com.cognizant.opserv.sp.persistence.dao.service.ChangeRequestDAOService;
import com.cognizant.opserv.sp.persistence.dao.service.ChangeRequestOfflineDAOService;
import com.cognizant.opserv.sp.persistence.dao.service.EmployeeDAOService;
import com.cognizant.peg.core.exception.BusinessException;
import com.cognizant.peg.notification.core.common.NotifyMessage;
import com.cognizant.peg.notification.core.common.NotifyServiceException;
import com.cognizant.peg.notification.core.common.NotifyType;
/**
 * ****************************************************************************.
 *
 * @class CRPartialSubmitNotificationServiceImpl ,CRNotificationService implementation class for partial submission 
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 28/09/2016
 * ***************************************************************************
 */
@Service("rejectionNotificationService")
public class CRRejectionNotificationServiceImpl implements CRNotificationService {

	@Autowired
	private EmployeeDAOService employeeDAOService;
	
	@Autowired
	private ChangeRequestOfflineDAOService changeRequestOfflineDAOService;
	
	@Autowired 
	private ChangeRequestDAOService changeRequestDAOService;
	
	//TODO - replace with MessageTemplate
	private String CR_APPROVAL_NOTIFY= "6";
	
	@Value("${amgenFFLink}")
	private String amgenFFLink;
	/**
	 * Email to: Submitted User 
		Email subject: <Request ID> : Partial Change Request Submitted
		Address as : Dear Last Name First Name
		Body of email: Partial Change Request has been Submitted
		Request Details:
		Request ID : 24870912
		Request Type : Pull Customer
		Submitted On : 10/11/2015
		Effective date : 1/1/2016
		Approver Name : Thomas Jefferson
		Business Reason : Member of Key Practice
		Comments : Attribute changed
		
		Rejection Reason: <Customers (Comma separated {Customer Code-Customer Name}) has been locked by another user, So this customer has not been Pulled>
		<In case of Zip lock show Comma separated Zip code-Territory Name>
		      
		Please visit <Amgen field facing link with hyperlink> to review the Change Request. Request(s) will be visible under ‘My Request’ 
		Closing: Regards,
		               ATLAS Support Team
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public List<NotifyMessage> configureMessage(ChangeRequest changeRequest, Short tenantId,
			String crType) throws NotifyServiceException, JMSException,
			BusinessException {
		List<NotifyMessage> notifyMessages = new ArrayList<NotifyMessage>();
		List<Employee> recipients = getRecipients(changeRequest, tenantId,
				AllocationType.PRIMARY.getId());
		for (Employee employee : recipients) {
			NotifyMessage notifyMessage=new NotifyMessage();
			notifyMessage.setClientCode(tenantId.intValue());
			notifyMessage.setNotifyType(NotifyType.EMAIL);
			notifyMessage.setMsgTemplate(CR_APPROVAL_NOTIFY);
			notifyMessage.setParams(getTemplateParams(changeRequest, tenantId,employee.getName()));
			List<String> eMailRecipient = new ArrayList<String>();
			eMailRecipient.add(employee.getEmployeeContact().getEmail());
			notifyMessage.setRecipients(eMailRecipient);
			notifyMessages.add(notifyMessage);
		}
		return notifyMessages;
	}

	/**
	 * To get the parameters for a template
	 * @param changeRequest
	 * @param tenantId
	 * @return Map<String,String>
	 */
	private Map<String,String> getTemplateParams(
			ChangeRequest changeRequest, Short tenantId,String empName) {
		Map<String,String> params = new HashMap<String, String>();
		params.put(NotificationConstants.CR_ID,  changeRequest.getId().toString());
		params.put(NotificationConstants.CHANGE_REQ_TYPE, NotificaionUtil.getRequestType(changeRequest.getCrDefinition().getTrigger()));
		params.put(NotificationConstants.ADDRESS_AS,empName);
		params.put(NotificationConstants.SUBMITTED_ON, changeRequest.getCrDefinition().getCreatedDate().toString() );
		List<Employee> employees=getApproverList(changeRequest, tenantId, AllocationType.PRIMARY.getId());
		params.put(NotificationConstants.APPROVER_NAME,employees!=null?employees.get(0).getName():NotificationConstants.EMPTY);
		//TODO
		params.put(NotificationConstants.EFFECTIVE_DATE, changeRequest.getCrDefinition()
				.getStartDate() != null ? changeRequest.getCrDefinition()
				.getStartDate().toString() : changeRequest.getCrDefinition()
				.getCreatedDate().toString());
		params.put(NotificationConstants.BUSINESS_REASON, changeRequest.getBusinessReason()!=null?changeRequest.getBusinessReason():NotificationConstants.NOT_APPLICABLE);
		params.put(NotificationConstants.COMMENTS, changeRequest.getComments()!=null?changeRequest.getComments():NotificationConstants.NOT_APPLICABLE);
		String rejectReason=fetchRejectReason(changeRequest, tenantId);
		params.put(NotificationConstants.REJECT_REASON,rejectReason!=null?rejectReason:NotificationConstants.EMPTY);
		params.put(NotificationConstants.AMGEN_FF_LINK, amgenFFLink );
		return params;
	}
	
	/**
	 * Gets the approver list.
	 *
	 * @param chngReqId the chng req id
	 * @param apprTypeId the appr type id
	 * @param tenantId the tenant id
	 * @param allocTypeId the alloc type id
	 * @return the approver list
	 */
	private List<Employee> getRecipients(ChangeRequest changeRequest,Short tenantId,Integer allocTypeId) {
		 
		List<Employee> employees=employeeDAOService.findEmpployeeBySalesPosId(changeRequest
				.getRequestedSalesPosition().getId(), allocTypeId, tenantId);
		
		if(checkforFinalRejection(changeRequest, tenantId)){
			
			 Character targetAppvrFlg=NotificaionUtil.getFirstLevelApproverFlag(changeRequest);
			/**
			 * If rejection from secondary approver then mail should go to promary approver as well
			 */
			employees.addAll(employeeDAOService.fetchChangReqApprovers(changeRequest.getId(),
					targetAppvrFlg,tenantId, AllocationType.PRIMARY.getId()));
		}
		return employees;
	}
	
	/**
	 * Checkfor final approval.
	 *
	 * @param changeRequest the change request
	 * @param tenantId the tenant id
	 * @return true, if successful
	 */
	private boolean checkforFinalRejection(ChangeRequest changeRequest,
			 Short tenantId){
		 Character targetAppvrFlg=NotificaionUtil.getFirstLevelApproverFlag(changeRequest);
		 Character target2AppvrFlg=NotificaionUtil.getSecondLevelApproverFlag(changeRequest);
			//for request with source and destination from same district
			if(changeRequest.getRequestedSalesPosition().getId().equals(changeRequest.getRaisedSalesPosition().getId())){
				ChangeRequestStatusType firstLevelApproverStatus =  changeRequestDAOService.getChangeRequestTargetApproverStatus(changeRequest.getId(), targetAppvrFlg, tenantId);
				if(firstLevelApproverStatus.equals(ChangeRequestStatusType.REJECTED)) {
					return true;
				}
			}else{
				if(target2AppvrFlg != NotificaionUtil.FLAG_NOT_FOUND){
					ChangeRequestStatusType firstLevelApproverStatus =  changeRequestDAOService.getChangeRequestTargetApproverStatus(changeRequest.getId(), targetAppvrFlg, tenantId);
					ChangeRequestStatusType secondLevelApproverStatus =  changeRequestDAOService.getChangeRequestTargetApproverStatus(changeRequest.getId(), target2AppvrFlg, tenantId);
					if(null != firstLevelApproverStatus && null !=secondLevelApproverStatus){
						if(firstLevelApproverStatus.equals(ChangeRequestStatusType.APPROVED) && secondLevelApproverStatus.equals(ChangeRequestStatusType.REJECTED)){
							return true;
						}
					}
				}
			}
		return false;
	}
	
	/**
	 * Gets the approver list.
	 *
	 * @param chngReqId the chng req id
	 * @param apprTypeId the appr type id
	 * @param tenantId the tenant id
	 * @param allocTypeId the alloc type id
	 * @return the approver list
	 */
	private List<Employee> getApproverList(ChangeRequest changeRequest,Short tenantId,Integer allocTypeId) {
		
		boolean finalRejection=checkforFinalRejection(changeRequest, tenantId);
		/**
		 * To peimary approver
		 */
		if(finalRejection){
			if(changeRequest.getRequestedSalesPosition().getId().equals(changeRequest.getRaisedSalesPosition().getId())){
				Character targetAppvrFlg=NotificaionUtil.getFirstLevelApproverFlag(changeRequest);
				if(targetAppvrFlg !=NotificaionUtil.FLAG_NOT_FOUND){
					return  employeeDAOService.fetchChangReqApprovers(changeRequest.getId(), targetAppvrFlg, tenantId, allocTypeId);
				}
			}else{
				Character target2AppvrFlg=NotificaionUtil.getSecondLevelApproverFlag(changeRequest);
				
				if(target2AppvrFlg!=NotificaionUtil.FLAG_NOT_FOUND){
					return  employeeDAOService.fetchChangReqApprovers(changeRequest.getId(), target2AppvrFlg, tenantId, allocTypeId);
				}
			}
		}else{
			Character targetAppvrFlg=NotificaionUtil.getFirstLevelApproverFlag(changeRequest);
			if(targetAppvrFlg !=NotificaionUtil.FLAG_NOT_FOUND){
				return  employeeDAOService.fetchChangReqApprovers(changeRequest.getId(), targetAppvrFlg, tenantId, allocTypeId);
			}
		}
		return null;
	}
	
	/**
	 * Fetch reject reason.
	 *
	 * @param changeRequest the change request
	 * @param tenantId the tenant id
	 * @return the string
	 */
	private String fetchRejectReason(ChangeRequest changeRequest,Short tenantId){
		boolean finalRejection=checkforFinalRejection(changeRequest, tenantId);
		/**
		 * To primary approver
		 */
		if(finalRejection){
			Character target2AppvrFlg=NotificaionUtil.getSecondLevelApproverFlag(changeRequest);
			if(target2AppvrFlg!=NotificaionUtil.FLAG_NOT_FOUND){
				return changeRequestDAOService.getChangeRequestfindApproverComments(changeRequest.getId(), target2AppvrFlg, tenantId);
			}
		}else{
			Character targetAppvrFlg=NotificaionUtil.getFirstLevelApproverFlag(changeRequest);
			if(targetAppvrFlg !=NotificaionUtil.FLAG_NOT_FOUND){
				return changeRequestDAOService.getChangeRequestfindApproverComments(changeRequest.getId(), targetAppvrFlg, tenantId);
			}
		}
		return null;
	}
}

