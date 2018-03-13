/**
 * 
 */
package com.cognizant.opserv.sp.service.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cognizant.opserv.common.domain.MessageTemplate;
import com.cognizant.opserv.sp.event.messages.OfflineRequestMessage;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.model.cr.ChangeRequest;
import com.cognizant.opserv.sp.persistence.dao.service.EmployeeAlignmentDAOService;
import com.cognizant.opserv.sp.service.common.NotificationService;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;

/**
 * The Class CustomerNotificationUtil.
 */

@Component
public class NotificationUtil {

	/** The notification service. */
	@Autowired
	private NotificationService notificationService;
	
	@Autowired
	private EmployeeAlignmentDAOService employeeAlignmentDAOService;
	
	/** The Constant LOGGER. */
	private static final OpservLogger LOGGER = OpservLoggerFactory
			.getOpservLogger(NotificationUtil.class);

	/*
	 * @Value("${opserv.admin.email}") private String adminEmail;
	 */

	/**
	 * Gets the details to notify.
	 * 
	 * @param messToQueOnSaveMap
	 *            the mess to que on save map
	 * @return the details to notify
	 */
	public void toNotify(
			Map<MessageTemplate, OfflineRequestMessage> messToQueOnSaveMap) {
		
		LOGGER.info("******************* EMAIL NOTIFICATION FOR CUSTOMER PUSH/PULL STARTED*******************");

		MessageTemplate messageTemplate = null;
		OfflineRequestMessage tempCRBlobMessage = null;
		if (messToQueOnSaveMap != null) {

			for (Map.Entry<MessageTemplate, OfflineRequestMessage> entry : messToQueOnSaveMap
					.entrySet()) {
				messageTemplate = entry.getKey();
				tempCRBlobMessage = entry.getValue();

				List<String> srcSpList = tempCRBlobMessage.getSrcSpNameList();
				List<String> tarSpList = tempCRBlobMessage.getTarSpNameList();
				//List<String> custNameList = tempCRBlobMessage.getCustNameList();
				String custName = tempCRBlobMessage.getCustName();
				
				List<Integer> toAddrs = new ArrayList<Integer>();
				if(tempCRBlobMessage.getStaffIdList() != null && !tempCRBlobMessage.getStaffIdList().isEmpty()){
					LOGGER.info("******************EMPLOYEE MAIL ID LIST******************************"+tempCRBlobMessage.getStaffIdList());
					for (Integer staffId : tempCRBlobMessage.getStaffIdList()) {
						toAddrs.add(staffId);
					}
				}else{
					LOGGER.info("******************EMPLOYEE MAILID LIST IS EMPTY******************************");
				}
				
				// toAddrs.add(adminEmail);
				
				Map<String, String> args = new LinkedHashMap<String, String>();
				if (srcSpList != null && !srcSpList.isEmpty()) {
					LOGGER.info("******************SOURCE SP NAME LIST******************************"+tempCRBlobMessage.getSrcSpNameList());
					args.put("srcSalespositions", srcSpList.toString());
				}else{
					LOGGER.info("******************SOURCE SP NAME LIST IS EMPTY******************************");
				}

				if (tarSpList != null && !tarSpList.isEmpty()) {
					LOGGER.info("******************TARGET SP NAME LIST******************************"+tempCRBlobMessage.getTarSpNameList());
					args.put("tarSalespositions", tarSpList.toString());
				}else{
					LOGGER.info("******************TARGET SP NAME LIST IS EMPTY******************************");
				}

				if (custName != null) {
					LOGGER.info("******************CUSTOMER NAME LIST******************************"+tempCRBlobMessage.getCustName());
					args.put("customers", custName);
				}else{
					LOGGER.info("******************CUSTOMER NAME LIST IS EMPTY******************************");
				}

				notificationService.sendEmailNotification(toAddrs, null, null, messageTemplate, args, null, tempCRBlobMessage.getUserDetails());

			}
		}
		LOGGER.info("******************* EMAIL NOTIFICATION FOR CUSTOMER PUSH/PULL ENDED*******************");
	}
	
	/**
	 * Gets the details to notify for CR.
	 * 
	 * @param crId -
	 *            change request ID
	 * @return 
	 */
	public void getDetailsToNotifyForCR(
			Map<MessageTemplate, OfflineRequestMessage> messToQueOnSubmitMap) {

		MessageTemplate messageTemplate = null;
		OfflineRequestMessage tempCRBlobMessage = new OfflineRequestMessage();

		if (messToQueOnSubmitMap != null) {
			for (Map.Entry<MessageTemplate, OfflineRequestMessage> entry : messToQueOnSubmitMap.entrySet()) {
				messageTemplate = entry.getKey();
				tempCRBlobMessage = entry.getValue();
				
				Long crId = tempCRBlobMessage.getChngReqID();
				
				List<Integer> toAddrs = new ArrayList<Integer>();
				if(tempCRBlobMessage.getStaffIdList() != null && !tempCRBlobMessage.getStaffIdList().isEmpty()){
					LOGGER.info("******************EMPLOYEE MAIL ID LIST******************************"+tempCRBlobMessage.getStaffIdList());
					for (Integer staffId : tempCRBlobMessage.getStaffIdList()) {
						toAddrs.add(staffId);
					}
				}else{
					LOGGER.info("******************EMPLOYEE MAILID LIST IS EMPTY******************************");
				}
				
				Map<String, String> args = new LinkedHashMap<String, String>();
				args.put("crId", crId.toString());

				notificationService.sendEmailNotification(toAddrs, null, null, messageTemplate, args, null, tempCRBlobMessage.getUserDetails());

			}

		}
	}


	public void notificationForChangeRequest(ChangeRequest chngReq, UserDetails userDetails, MessageTemplate tmpId) {
		/************Notification - Start **************/
		List<Long> spIds = new ArrayList<Long>();
		spIds.add(chngReq.getRequestedSalesPosition().getId());
		spIds.add(chngReq.getRaisedSalesPosition().getId());
		
		List<Integer> staffIdList = employeeAlignmentDAOService.fetchStaffIds(spIds, userDetails);
		
		OfflineRequestMessage tempCRBlobMessage = new OfflineRequestMessage();
		tempCRBlobMessage.setChngReqID(chngReq.getId());
		tempCRBlobMessage.setUserDetails(userDetails);
		
		if (staffIdList != null && !staffIdList.isEmpty()) {
			tempCRBlobMessage.setStaffIdList(staffIdList);
		}
		
		Map<MessageTemplate, OfflineRequestMessage> messToQueOnSubmitMap = new HashMap<MessageTemplate, OfflineRequestMessage>();
		messToQueOnSubmitMap.put(tmpId, tempCRBlobMessage);
		
		getDetailsToNotifyForCR(messToQueOnSubmitMap);
		
		/************Notification - End **************/
	}

	
	
	public void notificationForZipMovement(List<Long> spIds, UserDetails userDetails, MessageTemplate tmpId, String postalCode) {
		/************Notification - Start **************/

		List<Integer> staffIdList = employeeAlignmentDAOService.fetchStaffIds(spIds, userDetails);
		
		OfflineRequestMessage tempCRBlobMessage = new OfflineRequestMessage();
		tempCRBlobMessage.setPostalCode(postalCode);
		tempCRBlobMessage.setUserDetails(userDetails);
		
		if (staffIdList != null && !staffIdList.isEmpty()) {
			tempCRBlobMessage.setStaffIdList(staffIdList);
		}
		
		Map<MessageTemplate, OfflineRequestMessage> messToQueOnSubmitMap = new HashMap<MessageTemplate, OfflineRequestMessage>();
		messToQueOnSubmitMap.put(tmpId, tempCRBlobMessage);
		
		getDetailsToNotifyForZipMovement(messToQueOnSubmitMap);
		
		/************Notification - End **************/
	}
	
	
	public void getDetailsToNotifyForZipMovement(
			Map<MessageTemplate, OfflineRequestMessage> messToQueOnSubmitMap) {

		MessageTemplate messageTemplate = null;
		OfflineRequestMessage tempCRBlobMessage = new OfflineRequestMessage();

		if (messToQueOnSubmitMap != null) {
			for (Map.Entry<MessageTemplate, OfflineRequestMessage> entry : messToQueOnSubmitMap.entrySet()) {
				messageTemplate = entry.getKey();
				tempCRBlobMessage = entry.getValue();
				
				String zipId = tempCRBlobMessage.getPostalCode();
				
				List<Integer> toAddrs = new ArrayList<Integer>();
				if(tempCRBlobMessage.getStaffIdList() != null && !tempCRBlobMessage.getStaffIdList().isEmpty()){
					LOGGER.info("******************EMPLOYEE MAIL ID LIST******************************"+tempCRBlobMessage.getStaffIdList());
					for (Integer staffId : tempCRBlobMessage.getStaffIdList()) {
						toAddrs.add(staffId);
					}
				}else{
					LOGGER.info("******************EMPLOYEE MAILID LIST IS EMPTY******************************");
				}
				
				Map<String, String> args = new LinkedHashMap<String, String>();
				args.put("zipId", zipId);

				notificationService.sendEmailNotification(toAddrs, null, null, messageTemplate, args, null, tempCRBlobMessage.getUserDetails());

			}

		}
	}
	
	
	public void notifyZipMovementFailForCust(List<Long> spIds, UserDetails userDetails, MessageTemplate tmpId,String postalCode, List<String> custNames) {
		/************Notification - Start **************/
		
		List<Integer> staffIdList = new ArrayList<Integer>();
		if(null!=spIds && !spIds.isEmpty()){
			staffIdList = employeeAlignmentDAOService.fetchStaffIds(spIds, userDetails);
		}
		OfflineRequestMessage tempCRBlobMessage = new OfflineRequestMessage();
		tempCRBlobMessage.setPostalCode(postalCode);
		tempCRBlobMessage.setUserDetails(userDetails);
		
		if (staffIdList != null && !staffIdList.isEmpty()) {
			tempCRBlobMessage.setStaffIdList(staffIdList);
		}
		
		Map<MessageTemplate, OfflineRequestMessage> messToQueOnSubmitMap = new HashMap<MessageTemplate, OfflineRequestMessage>();
		messToQueOnSubmitMap.put(tmpId, tempCRBlobMessage);
		
		
		notifyZipMovementFailed(messToQueOnSubmitMap, custNames);
		
		/************Notification - End **************/
	}
	
	public void notifyZipMovementFailed(
			Map<MessageTemplate, OfflineRequestMessage> messToQueOnSubmitMap, List<String> custNames) {

		MessageTemplate messageTemplate = null;
		OfflineRequestMessage tempCRBlobMessage = new OfflineRequestMessage();

		if (messToQueOnSubmitMap != null) {
			for (Map.Entry<MessageTemplate, OfflineRequestMessage> entry : messToQueOnSubmitMap.entrySet()) {
				messageTemplate = entry.getKey();
				tempCRBlobMessage = entry.getValue();
				
				String zipId = tempCRBlobMessage.getPostalCode();
				
				List<Integer> toAddrs = new ArrayList<Integer>();
				if(tempCRBlobMessage.getStaffIdList() != null && !tempCRBlobMessage.getStaffIdList().isEmpty()){
					LOGGER.info("******************EMPLOYEE MAIL ID LIST******************************"+tempCRBlobMessage.getStaffIdList());
					for (Integer staffId : tempCRBlobMessage.getStaffIdList()) {
						toAddrs.add(staffId);
					}
				}else{
					LOGGER.info("******************EMPLOYEE MAILID LIST IS EMPTY******************************");
				}
				
				Map<String, String> args = new LinkedHashMap<String, String>();
				args.put("zipId", zipId);
				args.put("customerNames", custNames.toString());

				notificationService.sendEmailNotification(toAddrs, null, null, messageTemplate, args, null, tempCRBlobMessage.getUserDetails());

			}

		}
	}
	
}
