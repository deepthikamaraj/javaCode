package com.cognizant.opserv.sp.cr.process.batch;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cognizant.opserv.sp.messaging.GenericPublisher;
import com.cognizant.opserv.sp.event.messages.OfflineRequestMessage;
import com.cognizant.opserv.sp.model.auth.UserDetails;

public class CRRequestPublisher {
	
	/** The Application Context. */
	private static AbstractApplicationContext context = null;

	public static void main(String[] args) {

		System.setProperty("opserv.config.file", "c:/opserv/config/opserv-config.properties");

		context = new ClassPathXmlApplicationContext("applicationContext-request-process-publisher.xml");
		
		GenericPublisher genericPublisher = (GenericPublisher) context.getBean(GenericPublisher.class);
		
		try {
			genericPublisher.sendObjectToQueue("tempCRProcessQueue", getMessageDetails(args));
		} catch(Exception e) {
			e.printStackTrace();
		}

		context.close();
	}
	
	private static OfflineRequestMessage getMessageDetails(String[] args) {
		final OfflineRequestMessage storeMsg = new OfflineRequestMessage();
		storeMsg.setUserDetails(getUserDetails());
		storeMsg.setOfflineReqId(5L);
		storeMsg.setChngReqID(266L);
		storeMsg.setTenantCode("am");
		storeMsg.setTenantId((short)1);
		
//		storeMsg.setSalesPosid(Long.valueOf(args[0]));
//		storeMsg.setEntityId(Long.valueOf(args[1]));
//		storeMsg.setEntityType(args[2]);
//		storeMsg.setSalesPosid(3L);
//		storeMsg.setEntityId(4L);
//		storeMsg.setEntityType("CUSTOMER_ALIGNMENT");
		return storeMsg;
	}
	
	private static UserDetails getUserDetails() {
		UserDetails userDtls = new UserDetails();
		userDtls.setTenantCode("am");
		userDtls.setTenantId((short) 1);
		return userDtls;
	}
}
