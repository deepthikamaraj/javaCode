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
import com.cognizant.opserv.sp.common.ChangeRequestOfflineStatusType;
import com.cognizant.opserv.sp.common.ChangeRequestTriggerType;
import com.cognizant.opserv.sp.core.dao.TPersContactDAO;
import com.cognizant.opserv.sp.json.CustomerBlobDetails;
import com.cognizant.opserv.sp.model.Employee;
import com.cognizant.opserv.sp.model.OfflineRequest;
import com.cognizant.opserv.sp.model.ZipBlob;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.model.cr.ChangeRequest;
import com.cognizant.opserv.sp.notification.util.NotificaionUtil;
import com.cognizant.opserv.sp.notification.util.NotificationConstants;
import com.cognizant.opserv.sp.persistence.dao.service.ChangeRequestOfflineDAOService;
import com.cognizant.opserv.sp.persistence.dao.service.CustomerDAOService;
import com.cognizant.opserv.sp.persistence.dao.service.EmployeeDAOService;
import com.cognizant.peg.core.common.JSONUtil;
import com.cognizant.peg.core.exception.BusinessException;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
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
@Service("partialSubmitNotificationService")
public class CRPartialSubmitNotificationServiceImpl implements CRNotificationService {

	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(CRPartialSubmitNotificationServiceImpl.class);
	
	@Autowired
	private TPersContactDAO tPersContactDAO;
	
	@Autowired
	private EmployeeDAOService employeeDAOService;
	
	@Autowired
	private ChangeRequestOfflineDAOService changeRequestOfflineDAOService;
	
	@Autowired
	private CustomerDAOService customerDAOService;
	//TODO - replace with MessageTemplate
	private String CR_PARTIAL_SUMBIT_NOTIFY= "5";
	
	@Value("${amgenFFLink}")
	private String amgenFFLink;
	
	private String REJ_TYPE_CUST="Customers";
	
	private String REJ_TYPE_ZIP="Zip codes";
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
		List<Employee> recipients = getRecipients(changeRequest
				.getRequestedSalesPosition().getId(), tenantId,
				AllocationType.PRIMARY.getId());
		if(null != recipients && !recipients.isEmpty()){
			for (Employee employee : recipients) {
				NotifyMessage notifyMessage=new NotifyMessage();
				notifyMessage.setClientCode(tenantId.intValue());
				notifyMessage.setNotifyType(NotifyType.EMAIL);
				notifyMessage.setMsgTemplate(CR_PARTIAL_SUMBIT_NOTIFY);
				notifyMessage.setParams(getTemplateParams(changeRequest, tenantId,employee.getName()));
				List<String> eMailRecipient = new ArrayList<String>();
				eMailRecipient.add(employee.getEmployeeContact().getEmail());
				notifyMessage.setRecipients(eMailRecipient);
				notifyMessages.add(notifyMessage);
			}
		}else{
			LOGGER.error("No recepient mail id found for sales position "+changeRequest
					.getRequestedSalesPosition().getId());
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
		String rejType=getRejectType(changeRequest.getCrDefinition().getTrigger());
		Map<String,String> params = new HashMap<String, String>();
		params.put(NotificationConstants.CR_ID,  changeRequest.getId().toString());
		params.put(NotificationConstants.CHANGE_REQ_TYPE,NotificaionUtil.getRequestType(changeRequest.getCrDefinition().getTrigger()));
		params.put(NotificationConstants.ADDRESS_AS, empName);
		params.put(NotificationConstants.SUBMITTED_ON, changeRequest.getCrDefinition().getCreatedDate().toString() );
		List<Employee> approverList=getApproverList(changeRequest.getId(),targetAppvrFlg,tenantId,AllocationType.PRIMARY.getId());
		params.put(NotificationConstants.APPROVER_NAME, approverList!=null?approverList.get(0).getName():"");
		//TODO
		params.put(NotificationConstants.EFFECTIVE_DATE, changeRequest.getCrDefinition()
				.getStartDate() != null ? changeRequest.getCrDefinition()
				.getStartDate().toString() : changeRequest.getCrDefinition()
				.getCreatedDate().toString());
		params.put(NotificationConstants.BUSINESS_REASON, changeRequest.getBusinessReason()!=null?changeRequest.getBusinessReason():"NA");
		params.put(NotificationConstants.COMMENTS, changeRequest.getComments()!=null?changeRequest.getComments():"NA");
		params.put(NotificationConstants.AMGEN_FF_LINK, amgenFFLink );
		params.put(NotificationConstants.REJECT_TYPE, rejType );
		if(!changeRequest.getCrDefinition().getTrigger().equals(ChangeRequestTriggerType.PUSH_ZIP)){
			params.put(NotificationConstants.REJECTED_FIELDS, getRejectedCustomerNames(changeRequest,tenantId) );
		}else{
			params.put(NotificationConstants.REJECTED_FIELDS, getRejectedZipCodes(changeRequest,tenantId) );
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
	private List<Employee> getApproverList(Long chngReqId,Character targetAprFlg,Short tenantId,Integer allocTypeId) {
		return  employeeDAOService.fetchChangReqApprovers(chngReqId, targetAprFlg, tenantId, allocTypeId);
	}
  
	/**
	 * To check rejected customers in a CR.- Partial CR
	 * @param changeRequest
	 * @return comma saperated rejected customer names
	 */
	private String getRejectedCustomerNames(ChangeRequest changeRequest,Short tenantId){
		UserDetails userDetails = new UserDetails();
		userDetails.setTenantId(tenantId);
		 StringBuffer customers= null;
		 List<Integer> custIdList = null;
		 List<OfflineRequest> offlineCRs= changeRequestOfflineDAOService.findTempCRsByChangeRequest(changeRequest, userDetails);
		 for (OfflineRequest crOfflineData : offlineCRs) {
			
			if(crOfflineData.getOfflineStatus().equals(ChangeRequestOfflineStatusType.CANCELLED)){
				if(NotificaionUtil.isCustomerfailedDueToLock(crOfflineData.getFailedReason())){
					CustomerBlobDetails customerBlobDetails = JSONUtil.toObject(
							crOfflineData.getJsonString(),
							CustomerBlobDetails.class);
					if(null == custIdList){
						if(null != customerBlobDetails.getSrcCustomerAlignment() && null!=customerBlobDetails.getSrcCustomerAlignment().getCustomer()){
							custIdList = new ArrayList<Integer>();
							custIdList.add(customerBlobDetails.getSrcCustomerAlignment().getCustomer().getId().intValue());
						}
					}else{
						if(null != customerBlobDetails.getSrcCustomerAlignment() && null!=customerBlobDetails.getSrcCustomerAlignment().getCustomer()){
							custIdList.add(customerBlobDetails.getSrcCustomerAlignment().getCustomer().getId().intValue());
						}
					}
				}
			}
		}
		if(null!=custIdList && !custIdList.isEmpty()){
			List<String> customerNameList =customerDAOService.fetchCustNameFrCustIds(custIdList, userDetails);
			if(null != customerNameList && !customerNameList.isEmpty()){
				for (String name : customerNameList) {
					if(null == customers){
						customers = new StringBuffer();
						customers.append(name);
					}else{
						customers.append(","+name);
					}
					
				}
			}
		}
		if(null !=customers ){
			return customers.toString();
		}
		return null;
	}
	
	/**
	 * To check rejected ZipCodes in a CR.- Partial CR
	 * @param changeRequest
	 * @return comma separated zip codes
	 */
	private String getRejectedZipCodes(ChangeRequest changeRequest,Short tenantId){
		UserDetails userDetails = new UserDetails();
		userDetails.setTenantId(tenantId);
		StringBuffer zipCodes=null;
		
		 List<OfflineRequest> offlineCRs= changeRequestOfflineDAOService.findTempCRsByChangeRequest(changeRequest, userDetails);
		 for (OfflineRequest crOfflineData : offlineCRs) {
			 if(crOfflineData.getOfflineStatus().equals(ChangeRequestOfflineStatusType.CANCELLED)){
					if(NotificaionUtil.isZipfailedDueToLock(crOfflineData.getFailedReason())){
						ZipBlob zipBlobDetails = JSONUtil.toObject(
								crOfflineData.getJsonString(),
								ZipBlob.class);
					if(null == zipCodes){
						if(null != zipBlobDetails.getSrcGeographyAlignment() && null != zipBlobDetails.getSrcGeographyAlignment().getPostalCode()){
							zipCodes = new StringBuffer(zipBlobDetails.getSrcGeographyAlignment().getPostalCode().getCode());
						}
					}else{
						if(null != zipBlobDetails.getSrcGeographyAlignment() && null != zipBlobDetails.getSrcGeographyAlignment().getPostalCode()){
							zipCodes=zipCodes.append(","+zipBlobDetails.getSrcGeographyAlignment().getPostalCode().getCode());
						}
					}
				}
			 }
		}
		if(null!=zipCodes){
			return zipCodes.toString();
		}
		return null;
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
	
	/**
	 * Gets the reject type.
	 *
	 * @param changeRequest the change request
	 * @return the reject type
	 */
	private String getRejectType(ChangeRequestTriggerType changeRequestTriggerType){
		String rejType=null;
		if(changeRequestTriggerType.equals(ChangeRequestTriggerType.PUSH_CUSTOMER)){
			rejType=REJ_TYPE_CUST;
		}else if(changeRequestTriggerType.equals(ChangeRequestTriggerType.PULL_CUSTOMER)){
			rejType=REJ_TYPE_CUST;
		}else if(changeRequestTriggerType.equals(ChangeRequestTriggerType.PUSH_ZIP)){
			rejType=REJ_TYPE_ZIP;
		}else if(changeRequestTriggerType.equals(ChangeRequestTriggerType.EDIT_CUSTOMER)){
			rejType=REJ_TYPE_CUST;
		}
		return rejType;
	}
}

