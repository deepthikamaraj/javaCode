package com.cognizant.opserv.sp.view;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cognizant.opserv.sp.common.BussObj;
import com.cognizant.opserv.sp.common.EventType;
import com.cognizant.opserv.sp.common.MessageType;
import com.cognizant.opserv.sp.event.messages.EntityChangeEvent;
import com.cognizant.opserv.sp.event.messages.ViewConstants;
import com.cognizant.opserv.sp.messaging.GenericPublisher;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;

/**
 * ****************************************************************************
 * 
 * @class EntityChangeEventPublisher
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 01/04/2016
 * ****************************************************************************       
 */
public class EntityChangeEventPublisher {
	
	/**
	 * The private empty constructor
	 */
	private EntityChangeEventPublisher (){
		
	}

	/** The Application Context. */
	private static AbstractApplicationContext context = null;

	/** The Constant LOGGER. */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(EntityChangeEventPublisher.class);

	private static UserDetails getUserDetails() {
		UserDetails userDtls = new UserDetails();
		userDtls.setTenantCode("am");
		userDtls.setTenantId((short) 1);
		return userDtls;
	}

	private static EntityChangeEvent getMessageDetails(String[] args) {
		final EntityChangeEvent storeMsg = new EntityChangeEvent();
		storeMsg.setUserDetails(getUserDetails());
//		storeMsg.setSalesPosid(Long.valueOf(args[0]));
//		storeMsg.setEntityId(Long.valueOf(args[1]));
//		storeMsg.setZipCode(args[2]);
//		storeMsg.setBussObjName(args[3]);
//		storeMsg.setMessageType(args[4]);
//		storeMsg.setEntityStatus(args[5]);
		storeMsg.setSalesPosid(11121L);
		storeMsg.setEntityId(null);
		storeMsg.setZipCode("560045");
		storeMsg.setBussObjName(BussObj.ZIP_SALESPOS_VIEW.getBussObjName());
		storeMsg.setMessageType(MessageType.VIEW);
		
		storeMsg.setEventType(EventType.SUBMITTED);
		
		return storeMsg;
	}

	public static void main(String[] args) {

		System.setProperty("opserv.config.file", "c:/opserv/config/opserv-config.properties");

		LOGGER.info("Start of EntityChangeEventPublisher using applicationContext-view-publisher.xml...");
		context = new ClassPathXmlApplicationContext("applicationContext-view-publisher.xml");
		
		GenericPublisher genericPublisher = (GenericPublisher) context.getBean(GenericPublisher.class);
		
		try {
			genericPublisher.sendObjectToQueue(ViewConstants.SP_STORE_QUEUE_NAME, getMessageDetails(args));
		} catch(Exception e) {
			LOGGER.error("Exception occured in EntityChangeEventPublisher while sending message into queue", e);
		}

		LOGGER.info("Sent Entity Message. Closing the context now");
		context.close();
	}

}