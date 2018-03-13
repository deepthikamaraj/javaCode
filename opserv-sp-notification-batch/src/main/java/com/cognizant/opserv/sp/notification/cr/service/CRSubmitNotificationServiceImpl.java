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
import com.cognizant.opserv.sp.common.CommonConstants;
import com.cognizant.opserv.sp.core.dao.TPersContactDAO;
import com.cognizant.opserv.sp.model.Employee;
import com.cognizant.opserv.sp.model.cr.ChangeRequest;
import com.cognizant.opserv.sp.persistence.dao.service.EmployeeDAOService;
import com.cognizant.peg.core.exception.BusinessException;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.notification.core.common.NotifyMessage;
import com.cognizant.peg.notification.core.common.NotifyServiceException;
import com.cognizant.peg.notification.core.common.NotifyType;
/**
 * ****************************************************************************.
 *
 * @class CRSubmitNotificationServiceImpl ,CRNotificationService implementation class for CR submit
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 28/09/2016
 * ***************************************************************************
 */
@Service("submitNotificationService")
public class CRSubmitNotificationServiceImpl implements CRNotificationService {

	/** The Constant LOGGER. */
	private static final OpservLogger  LOGGER = OpservLoggerFactory.getOpservLogger(CRSubmitNotificationServiceImpl.class);
	
	private String PULL_CUSTOMER="Pull Customer";
	
	private String PUSH_CUSTOMER="Push Customer";
	
	private String EDIT_CUSTOMER="Edit Customer";
	
	private String PUSH_ZIP="Push Zip";

	@Autowired
	private TPersContactDAO tPersContactDAO;
	
	@Autowired
	private EmployeeDAOService employeeDAOService;
	
	@Value("${amgenFFLink}")
	private String amgenFFLink;
	
	//TODO - replace with MessageTemplate
	private String CR_SUMBIT_NOTIFY= "2";
	/**
	 * Email To: submitter of CR
		Email Subject: <Request ID> : Change Request submitted 
		Address as: Dear Last Name First Name
		Body of email:
		The following Change request initiated by you has been submitted for further action.
		Request Details:
		Request ID : 24870912
		Request Type : Pull Customer
		Submitted On : 10/11/2015
		Effective date : 1/1/2016
		Approver Name : Thomas Jefferson
		Business Reason : Member of Key Practice
		Comments : Attribute changed
		 
		Please visit <Amgen field facing link with hyperlink> to review the Change Request
		Closing: Regards,
		               ATLAS Support Team
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public List<NotifyMessage> configureMessage(ChangeRequest changeRequest, Short tenantId,
			String crType) throws NotifyServiceException, JMSException,
			BusinessException {
		List<NotifyMessage> notifyMessages = null;
		List<Employee> recipients = getRecipients(changeRequest
				.getRequestedSalesPosition().getId(), tenantId,
				AllocationType.PRIMARY.getId());
		if(null != recipients && !recipients.isEmpty()){
			notifyMessages = new ArrayList<NotifyMessage>();
			for (Employee employee : recipients) {
				NotifyMessage notifyMessage=new NotifyMessage();
				notifyMessage.setClientCode(tenantId.intValue());
				notifyMessage.setNotifyType(NotifyType.EMAIL);
				notifyMessage.setMsgTemplate(CR_SUMBIT_NOTIFY);
				notifyMessage.setParams(getTemplateParams(changeRequest, tenantId,employee.getName()));
				List<String> eMailRecipient = new ArrayList<String>();
				eMailRecipient.add(employee.getEmployeeContact().getEmail());
				notifyMessage.setRecipients(eMailRecipient);
				notifyMessages.add(notifyMessage);
			}
		}else{
			String error="No recepients found.ChangeReqId="+changeRequest.getId()+",Requested SP id="+changeRequest
					.getRequestedSalesPosition().getId();
			LOGGER.error(error);
			throw new BusinessException("NC_SUBMIT_NO_RECEPIENTS", error);
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
			ChangeRequest changeRequest, Short tenantId,String raisedBy) {
		Character targetAppvrFlg='Y';
		Map<String,String> params = new HashMap<String, String>();
		params.put("reqId",  changeRequest.getId().toString());
		if(changeRequest.getCrDefinition().getTrigger().equals(ChangeRequestTriggerType.PUSH_CUSTOMER)){
			params.put("reqType", PUSH_CUSTOMER );
			targetAppvrFlg=CommonConstants.CHAR_NO;
		}else if(changeRequest.getCrDefinition().getTrigger().equals(ChangeRequestTriggerType.PULL_CUSTOMER)){
			params.put("reqType", PULL_CUSTOMER );
			targetAppvrFlg=CommonConstants.CHAR_YES;
		}else if(changeRequest.getCrDefinition().getTrigger().equals(ChangeRequestTriggerType.PUSH_ZIP)){
			params.put("reqType", PUSH_ZIP);
			targetAppvrFlg=CommonConstants.CHAR_NO;
		}else if(changeRequest.getCrDefinition().getTrigger().equals(ChangeRequestTriggerType.EDIT_CUSTOMER)){
			params.put("reqType", EDIT_CUSTOMER );
			targetAppvrFlg=CommonConstants.CHAR_YES;
		}
		params.put("addressAs",raisedBy);
		params.put("submittedOn", changeRequest.getCrDefinition().getCreatedDate().toString() );
		List<Employee> approverList=getApproverList(changeRequest.getId(),targetAppvrFlg,tenantId,AllocationType.PRIMARY.getId());
		params.put("approver",approverList!=null?approverList.get(0).getName():"");
		//TODO
		params.put("effectiveDate", changeRequest.getCrDefinition()
				.getStartDate() != null ? changeRequest.getCrDefinition()
				.getStartDate().toString() : changeRequest.getCrDefinition()
				.getCreatedDate().toString());
		params.put("bussReason", changeRequest.getBusinessReason()!=null?changeRequest.getBusinessReason():"NA");
		params.put("comments", changeRequest.getComments()!=null?changeRequest.getComments():"NA");
		params.put("amgenfflink", amgenFFLink );
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
