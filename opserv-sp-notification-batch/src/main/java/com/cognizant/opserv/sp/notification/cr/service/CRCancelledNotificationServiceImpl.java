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
import com.cognizant.opserv.sp.common.ChangeRequestTriggerType;
import com.cognizant.opserv.sp.core.dao.TPersContactDAO;
import com.cognizant.opserv.sp.model.Employee;
import com.cognizant.opserv.sp.model.cr.ChangeRequest;
import com.cognizant.opserv.sp.notification.util.NotificaionUtil;
import com.cognizant.opserv.sp.notification.util.NotificationConstants;
import com.cognizant.opserv.sp.persistence.dao.service.EmployeeDAOService;
import com.cognizant.peg.core.exception.BusinessException;
import com.cognizant.peg.notification.core.common.NotifyMessage;
import com.cognizant.peg.notification.core.common.NotifyServiceException;
import com.cognizant.peg.notification.core.common.NotifyType;
/**
 * ****************************************************************************.
 *
 * @class CRRejectionNotificationServiceImpl ,CRNotificationService implementation class for CR rejection
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 28/09/2016
 * ***************************************************************************
 */
@Service("cancelledNotificationService")
public class CRCancelledNotificationServiceImpl implements CRNotificationService {

	@Autowired
	private TPersContactDAO tPersContactDAO;
	
	@Autowired
	private EmployeeDAOService employeeDAOService;
	
	@Value("${amgenFFLink}")
	private String amgenFFLink;
	//TODO - replace with MessageTemplate
	private String CR_FULL_REJECT_NOTIFY="4";
	/**
	 * Email to: Submitted User and approver
	    Email subject: <Request ID> : Change Requests Cancelled
		Address as : Dear Last Name First Name
		Body of email: Change Requests have been cancelled 
		Request Details:
		Request ID : 24870912
		Request Type : Pull Customer
		Submitted On : 10/11/2015
		Effective date : 1/1/2016
		Approver Name : Thomas Jefferson
		Business Reason : Member of Key Practice
		Comments : Attribute changed
		
		Rejection Reason: <Customers have been locked by another user.>
		
		      Please visit <Amgen field facing link with hyperlink> to review the Change Request. These requests will be visible under ‘Requests for My Approval Tab’ 
		Closing: Regards,
		               ATLAS Support Team
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public List<NotifyMessage> configureMessage(ChangeRequest changeRequest, Short tenantId,
			String crType) throws NotifyServiceException, JMSException,
			BusinessException {
		List<NotifyMessage> notifyMessages = new ArrayList<NotifyMessage>();
		List<Employee> recipients = getRecipients(changeRequest
				.getRequestedSalesPosition().getId(), tenantId,
				AllocationType.PRIMARY.getId());
		for (Employee employee : recipients) {
			NotifyMessage notifyMessage=new NotifyMessage();
			notifyMessage.setClientCode(tenantId.intValue());
			notifyMessage.setNotifyType(NotifyType.EMAIL);
			notifyMessage.setMsgTemplate(CR_FULL_REJECT_NOTIFY);
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
		Character targetAppvrFlg=NotificaionUtil.getFirstLevelApproverFlag(changeRequest);
		Map<String,String> params = new HashMap<String, String>();
		params.put(NotificationConstants.CR_ID,  changeRequest.getId().toString());
		params.put(NotificationConstants.CHANGE_REQ_TYPE,NotificaionUtil.getRequestType(changeRequest.getCrDefinition().getTrigger()) );
		params.put(NotificationConstants.ADDRESS_AS, empName);
		params.put(NotificationConstants.SUBMITTED_ON, changeRequest.getCrDefinition().getCreatedDate().toString() );
		List<Employee> employees=getApproverList(changeRequest.getId(),targetAppvrFlg,tenantId,AllocationType.PRIMARY.getId());
		params.put(NotificationConstants.APPROVER_NAME,employees!=null?employees.get(0).getName():NotificationConstants.EMPTY);
		//TODO
		params.put(NotificationConstants.EFFECTIVE_DATE, changeRequest.getCrDefinition()
				.getStartDate() != null ? changeRequest.getCrDefinition()
				.getStartDate().toString() : changeRequest.getCrDefinition()
				.getCreatedDate().toString());
		params.put(NotificationConstants.BUSINESS_REASON, changeRequest.getBusinessReason()!=null?changeRequest.getBusinessReason():NotificationConstants.NOT_APPLICABLE);
		params.put(NotificationConstants.COMMENTS, changeRequest.getComments()!=null?changeRequest.getComments():NotificationConstants.NOT_APPLICABLE);
		params.put(NotificationConstants.AMGEN_FF_LINK, amgenFFLink );
		if(!changeRequest.getCrDefinition().getTrigger().equals(ChangeRequestTriggerType.PUSH_ZIP)){
			params.put(NotificationConstants.REJECT_REASON, "Zip codes have been locked by another user" );
		}else{
			params.put(NotificationConstants.REJECT_REASON, "Customers have been locked by another user" );
		}
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
	private List<Employee> getApproverList(Long chngReqId,Character targetAppvrFlg,Short tenantId,Integer allocTypeId) {
		return  employeeDAOService.fetchChangReqApprovers(chngReqId, targetAppvrFlg, tenantId, allocTypeId);
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
	private List<Employee> getRecipients(Long salesPosId,Short tenantId,Integer allocTypeId) {
		return  employeeDAOService.findEmpployeeBySalesPosId(salesPosId, allocTypeId, tenantId);
	}
}
