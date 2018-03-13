package com.cognizant.opserv.sp.cr.process;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cognizant.opserv.sp.cr.process.dto.CustomerAlignmentDTO;
import com.cognizant.opserv.sp.cr.process.internal.service.OfflineRequestService;
import com.cognizant.opserv.sp.event.messages.OfflineRequestMessage;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.tenant.util.BatchMultiTenancyUtil;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext-CR-test.xml" })
public class OfflineRequestServiceTest {

		private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(OfflineRequestServiceTest.class);
		
		@Autowired
		private OfflineRequestService offlineRequestService;
		
		@BeforeClass
		public static void setUp() {
	    	
			Assert.assertTrue("----->SETUP<-----", true);
			System.setProperty("opserv.activeMQ.brokerURL", "tcp://localhost:61616");
			System.setProperty("opserv.config.file", "c:/opserv/config/opserv-config.properties");
			System.setProperty("appType", "standalone");
			System.setProperty("targetDataSource", "app");
			System.setProperty("distributed", "true");
			System.setProperty("appName", "TriggerCRProcess");
			
		}
		
	 
	 	@Test
		public void testlockRequestForProcessing() {
			LOGGER.info("----Started testGetAllMetricResults------");
			BatchMultiTenancyUtil.setTenantContext("am");
			//SalesPosition sp = getSalesPosition2();
			UserDetails userDetails = getUserDetails();
			try {
				offlineRequestService.lockRequestForProcessing(Long.valueOf(3), userDetails);
			} catch (Exception e) {
				LOGGER.error("Error");
			} 
		}
	 	
	 	private UserDetails getUserDetails(){
			UserDetails userDetails = new UserDetails();
			userDetails.setTenantId((short) 1);
			userDetails.setUserId(80);
			userDetails.setTenantCode("am");
			return userDetails;
		}

	 	
	 	@Test
		public void testGetCustomerOfflineRequest() {
	 		Long offlineReqId = 13L; 
	 		OfflineRequestMessage message = new OfflineRequestMessage();
	 		message.setOfflineReqId(offlineReqId);
	 		CustomerAlignmentDTO result = null;
	 		
	 		try {
	 			result = offlineRequestService.getCustomerOfflineRequest(message, getUserDetails());
	 			System.out.println("Offline ID : "+result.getOfflineReqId());
	 			LOGGER.info(("Offline ID : "+result.getOfflineReqId()));
			} catch (Exception e) {
				LOGGER.error("Error while executing test method for getCustomerOfflineRequest");
			} 
	 	}
	 	
}
