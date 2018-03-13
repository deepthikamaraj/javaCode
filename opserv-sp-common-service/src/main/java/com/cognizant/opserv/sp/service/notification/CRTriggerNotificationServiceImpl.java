package com.cognizant.opserv.sp.service.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.opserv.sp.common.CommonConstants;
import com.cognizant.opserv.sp.messaging.GenericPublisher;
import com.cognizant.opserv.sp.model.CRNotificationMessage;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
/**
 * ****************************************************************************.
 * @class CRTriggerNotificationServiceImpl  implementation class for CRTriggerNotificationService
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 29/09/2016
 * ***************************************************************************
 */
@Service
public class CRTriggerNotificationServiceImpl implements CRTriggerNotificationService{

	@Autowired
	private GenericPublisher genericPublisher;
	
	 /**
	 *  Opserv Logger
	 */
	private static final OpservLogger  LOGGER = OpservLoggerFactory.getOpservLogger(CRTriggerNotificationServiceImpl.class);
	
	/**
	 * Create a new message for the change request and publish to queue
	 *
	 * @param ChngReqId the chng req id
	 * @param type the type , like "customer" or zip
	 * @param userDetails the user details
	 * @throws Exception the exception
	 */
	@Override
	public void triggerCrNotification(Long ChngReqId,
			UserDetails userDetails,String action) {
		
			CRNotificationMessage crNotificationMessage = new CRNotificationMessage();
			crNotificationMessage.setChngReqId(ChngReqId);
			crNotificationMessage.setTenantCode(userDetails.getTenantCode());
			crNotificationMessage.setTenantId(userDetails.getTenantId());
			crNotificationMessage.setAction(action);
	        LOGGER.info("Publising message to "+CommonConstants.CR_NOTFICATION_SUBMIT_QUEUE);
	        genericPublisher.sendObjectToQueue(CommonConstants.CR_NOTFICATION_SUBMIT_QUEUE, crNotificationMessage);
	}

	
}
