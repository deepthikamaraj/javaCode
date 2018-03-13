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
import com.cognizant.opserv.sp.notification.util.NotificationConstants;
import com.cognizant.opserv.sp.persistence.dao.service.ChangeRequestDAOService;
import com.cognizant.opserv.sp.persistence.dao.service.EmployeeDAOService;
import com.cognizant.opserv.sp.persistence.dao.service.SalesPositionDAOService;
import com.cognizant.peg.core.exception.BusinessException;
import com.cognizant.peg.notification.core.common.NotifyMessage;
import com.cognizant.peg.notification.core.common.NotifyServiceException;
import com.cognizant.peg.notification.core.common.NotifyType;
import com.cognizant.opserv.sp.common.ChangeRequestStatusType;
/**
 * ****************************************************************************.
 *
 * @class CRSubmitApproverNotificationServiceImpl ,CRNotificationService implementation class for CR approver notification
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 28/09/2016
 * ***************************************************************************
 */
@Service("submitApproverNotificationService")
public class CRSubmitApproverNotificationServiceImpl implements CRNotificationService {

	private String PULL_CUSTOMER="Pull Customer";
	
	private String PUSH_CUSTOMER="Push Customer";
	
	private String EDIT_CUSTOMER="Edit Customer";
	
	private String PUSH_ZIP="Push Zip";

	@Autowired
	private TPersContactDAO tPersContactDAO;
	
	@Autowired
	private EmployeeDAOService employeeDAOService;
	
	@Autowired
	private SalesPositionDAOService salesPositionDAOService;
	
	@Autowired
	private ChangeRequestDAOService changeRequestDAOService;
	
	private String  territoryName= null;
	private String territoryCode = null;
	
	@Value("${amgenFFLink}")
	private String amgenFFLink;
	
	//TODO - replace with MessageTemplate
	private String CR_SUMBIT_APPROVER_NOTIFY= "3";
	/**
	 * Email To: Approver of CR <determined based on approval workflow configuration>
		Email Subject: <Request ID> : Change Request submitted action required
		Address as: Dear Last Name First Name
		Body of email:
		The following Change Request has been submitted for your attention.
		 
		Request Details:
		Request ID : 24870912
		Request Type : Pull Customer
		Submitted On : 10/11/2015
		Submitted By : Julian Levy
		Effective date : 1/1/2016
		Territory Code : 11123
		Territory Name : Boston South
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
		 List<NotifyMessage> messages = null;
		 Character targetAppvrFlg='Y';
		 Character target2AppvrFlg='F';
			if(changeRequest.getCrDefinition().getTrigger().equals(ChangeRequestTriggerType.PUSH_CUSTOMER)){
				targetAppvrFlg=CommonConstants.CHAR_NO;
				target2AppvrFlg=CommonConstants.CHAR_YES;
			}else if(changeRequest.getCrDefinition().getTrigger().equals(ChangeRequestTriggerType.PULL_CUSTOMER)){
				targetAppvrFlg=CommonConstants.CHAR_YES;
				target2AppvrFlg=CommonConstants.CHAR_NO;
			}else if(changeRequest.getCrDefinition().getTrigger().equals(ChangeRequestTriggerType.PUSH_ZIP)){
				targetAppvrFlg=CommonConstants.CHAR_NO;
				target2AppvrFlg=CommonConstants.CHAR_YES;
			}else if(changeRequest.getCrDefinition().getTrigger().equals(ChangeRequestTriggerType.EDIT_CUSTOMER)){
				targetAppvrFlg=CommonConstants.CHAR_YES;
			}
		List<Employee> approvers = getApproverList(changeRequest.getId(),
				targetAppvrFlg,target2AppvrFlg,tenantId, AllocationType.PRIMARY.getId());
		if(null !=approvers && !approvers.isEmpty()){
			
			List<Object[]> spFieldlist=salesPositionDAOService.findSPNameAndCodeForEmp(approvers.get(0).getId().intValue(), tenantId);
			if (null != spFieldlist && !spFieldlist.isEmpty()) {
				for (Object[] obj : spFieldlist) {
					 territoryName= (String) obj[0];
					 territoryCode = (String) obj[1];
				}
			}
			messages = new ArrayList<NotifyMessage>();
			for (Employee employee : approvers) {
				
				NotifyMessage notifyMessage=new NotifyMessage();
				notifyMessage.setClientCode(tenantId.intValue());
				notifyMessage.setNotifyType(NotifyType.EMAIL);
				notifyMessage.setMsgTemplate(CR_SUMBIT_APPROVER_NOTIFY);
				notifyMessage.setParams(getTemplateParams(changeRequest, tenantId,employee.getName()));
				List<String> eMailRecipient = new ArrayList<String>();
				eMailRecipient.add(employee.getEmployeeContact().getEmail());
				notifyMessage.setRecipients(eMailRecipient);
				messages.add(notifyMessage);
			}
		}
		return messages;
	}

	/**
	 * To get the parameters for a template
	 * @param changeRequest
	 * @param tenantId
	 * @return Map<String,String>
	 */
	private Map<String,String> getTemplateParams(
			ChangeRequest changeRequest, Short tenantId,String approverName) {
		Map<String,String> params = new HashMap<String, String>();
		params.put("reqId",  changeRequest.getId().toString());
		if(changeRequest.getCrDefinition().getTrigger().equals(ChangeRequestTriggerType.PUSH_CUSTOMER)){
			params.put("reqType", PUSH_CUSTOMER );
		}else if(changeRequest.getCrDefinition().getTrigger().equals(ChangeRequestTriggerType.PULL_CUSTOMER)){
			params.put("reqType", PULL_CUSTOMER );
		}else if(changeRequest.getCrDefinition().getTrigger().equals(ChangeRequestTriggerType.PUSH_ZIP)){
			params.put("reqType", PUSH_ZIP);
		}else if(changeRequest.getCrDefinition().getTrigger().equals(ChangeRequestTriggerType.EDIT_CUSTOMER)){
			params.put("reqType", EDIT_CUSTOMER );
		}
		params.put("addressAs", approverName);
		params.put("submittedOn", changeRequest.getCrDefinition().getCreatedDate().toString() );
		String submittedBy=employeeDAOService.getEmpNameByUserId(changeRequest.getCreatedBy(), tenantId);
		params.put("submittedBy ",submittedBy!=null?submittedBy:NotificationConstants.EMPTY);
		//TODO
		params.put("effectiveDate", changeRequest.getCrDefinition()
				.getStartDate() != null ? changeRequest.getCrDefinition()
				.getStartDate().toString() : changeRequest.getCrDefinition()
				.getCreatedDate().toString());
		params.put("territoryCode",territoryName);
		params.put("territoryName",territoryCode);
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
	private List<Employee> getApproverList(Long chngReqId,Character targetAppvrFlg,Character target2AppvrFlg,Short tenantId,Integer allocTypeId) {
		
		boolean primariApproved=chekForPrimaryApproval(chngReqId, targetAppvrFlg, tenantId);
		/**
		 * To peimary approver
		 */
		if(!primariApproved){
			return  employeeDAOService.fetchChangReqApprovers(chngReqId, targetAppvrFlg, tenantId, allocTypeId);
		}else{
			/**
			 * To secondary approver(not for zip)
			 * 
			 */
			if(target2AppvrFlg !='F'){
				return  employeeDAOService.fetchChangReqApprovers(chngReqId, target2AppvrFlg, tenantId, allocTypeId);
			}
		}
		return null;
	}
  /**
   * To check whether the primary approver approves the request
   * @param chngReqId
   * @param targetAppvrFlg
   * @param tenantId
   * @return boolean
   */
	private boolean chekForPrimaryApproval(Long chngReqId,Character targetAppvrFlg,Short tenantId){
		
		ChangeRequestStatusType statusType =  changeRequestDAOService.getChangeRequestTargetApproverStatus(chngReqId, targetAppvrFlg, tenantId);
		if(null != statusType){
			if(statusType.equals(ChangeRequestStatusType.APPROVED)){
				return true;
			}
		}
		return  false;
	}
}
