package com.cognizant.opserv.sp.service.impl.test.notification;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cognizant.opserv.common.domain.MessageTemplate;
import com.cognizant.opserv.sp.common.CommonConstants;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.service.base.test.BaseTest;
import com.cognizant.opserv.sp.service.common.NotificationService;
import com.cognizant.opserv.sp.service.notification.CRTriggerNotificationService;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;

public class NotificationServiceImplTest extends BaseTest {

	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(NotificationServiceImplTest.class);
	
	@Autowired
	private NotificationService notificationService;
	
	@Autowired
	private CRTriggerNotificationService crNotificationService; 
	
	@BeforeClass
	public static void setUp() {
		System.setProperty("opserv.activeMQ.brokerURL", "tcp://localhost:61616?wireFormat.maxInactivityDuration=0");
		System.setProperty("opserv.config.file","C:/opserv/config/opserv-config.properties");
	}
	
	@Test
	public void testGetAllMetricResults() {
		LOGGER.info("----Started testGetAllMetricResults------");
	
		//SalesPosition sp = getSalesPosition2();
		UserDetails userDetails = getUserDetails();
		try {
			List<Integer> toAddrs= new ArrayList<Integer>();
			toAddrs.add(2663);
			MessageTemplate tmpId = MessageTemplate.CUSTOMER_FOR_PUSH_FAIL_AFFILIATED;
			Map<String,String> args = new HashMap<String, String>();
			args.put("customers", "Cust1,cust2,cust3");
			notificationService.sendEmailNotification(toAddrs, null, null, tmpId, args, null, userDetails);
			System.out.println("Complted");
			
		} catch (Exception e) {
			LOGGER.error("Error");
		} 
	}
	
	@Test
	public void testCreateAndSendNotificationMessage() {
		LOGGER.info("----Started testGetAllMetricResults------");
	
		//SalesPosition sp = getSalesPosition2();
		UserDetails userDetails = getUserDetails();
		try {
			
			crNotificationService.triggerCrNotification(Long.valueOf(1779), userDetails,CommonConstants.CR_ACTION_SUBMIT);
		} catch (Exception e) {
			LOGGER.error("Error");
		} 
	}
	private UserDetails getUserDetails(){
		UserDetails userDetails = new UserDetails();
		userDetails.setTenantId((short) 1);
		userDetails.setUserId(1);
		userDetails.setTenantCode("am");
		return userDetails;
	}


}
