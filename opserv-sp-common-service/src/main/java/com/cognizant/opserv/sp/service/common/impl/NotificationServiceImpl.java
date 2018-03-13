package com.cognizant.opserv.sp.service.common.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.opserv.common.domain.MessageTemplate;
import com.cognizant.opserv.sp.common.CommonConstants;
import com.cognizant.opserv.sp.common.NotificationType;
import com.cognizant.opserv.sp.messaging.GenericPublisher;
import com.cognizant.opserv.sp.model.NotificationMessage;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.service.common.NotificationService;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.notification.client.service.NotifyClient;
/**
 * ****************************************************************************.
 *
 * @Interface NotificationServiceImpl contains implementations for NotificationService
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/06/2016
 * ***************************************************************************
 */
@Service
public class NotificationServiceImpl implements NotificationService{

	
	@Autowired
    private NotifyClient notifyClient;
	
	@Autowired
	private GenericPublisher genericPublisher;
	
	 /**
	 *  Opserv Logger
	 */
	private static final OpservLogger  LOGGER = OpservLoggerFactory.getOpservLogger(NotificationServiceImpl.class);
		
	/**
	 * Send email.
	 *
	 * @param toAddrs the to addrs
	 * @param ccAddrs the cc addrs
	 * @param bccAddrs the bcc addrs
	 * @param tmpId the tmp id
	 * @param args the args
	 * @param files the files
	 * @param userDetails the user details
	 */
	public void sendEmailNotification(List<Integer> toAddrs,List<Integer> ccAddrs,List<Integer> bccAddrs,MessageTemplate tmpId,Map<String,String> args,List<String> files,UserDetails userDetails){
	        
        NotificationMessage notifyMessage = new NotificationMessage();
        notifyMessage.setClientCode((int)userDetails.getTenantId());
        notifyMessage.setNotifyType(NotificationType.EMAIL);
        notifyMessage.setMsgTemplate(tmpId.toString());
	        
       if(null == args){
       	 args = new HashMap<String, String>();
       }
       args.put(CommonConstants.USER_ID, userDetails.getUserId().toString());
       args.put(CommonConstants.TENANT_CODE, userDetails.getTenantCode());
       
        notifyMessage.setParams(args);
        notifyMessage.setRecipients(toAddrs);
        notifyMessage.setCcs(ccAddrs);
        notifyMessage.setBccs(bccAddrs);
        notifyMessage.setAttachFiles(files);
        
        LOGGER.info("Publising message to "+CommonConstants.NOTFICATION_SUBMIT_QUEUE);
        genericPublisher.sendObjectToQueue(CommonConstants.NOTFICATION_SUBMIT_QUEUE, notifyMessage);
	 }
}
