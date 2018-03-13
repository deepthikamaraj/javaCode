package com.cognizant.opserv.sp.cr.process;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cognizant.opserv.sp.cr.process.dto.CustomerAlignmentDTO;
import com.cognizant.opserv.sp.cr.process.internal.service.CustomerPushOfflineService;
import com.cognizant.opserv.sp.model.Alignment;
import com.cognizant.opserv.sp.model.BusinessUnit;
import com.cognizant.opserv.sp.model.Customer;
import com.cognizant.opserv.sp.model.CustomerAlignment;
import com.cognizant.opserv.sp.model.SalesOrgHierarchy;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.model.SalesTeam;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.model.cr.ChangeRequest;
import com.cognizant.opserv.tenant.util.BatchMultiTenancyUtil;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext-CR-test.xml" })
public class CustomerPushOfflineServiceTest {

		private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(CustomerPushOfflineServiceTest.class);
		
		@Autowired
		private CustomerPushOfflineService customerPushOfflineService;
		
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
		public void testLockCustomerAlignment() {
			LOGGER.info("----Started lockCustomerAlignment------");
			BatchMultiTenancyUtil.setTenantContext("am");
			//SalesPosition sp = getSalesPosition2();
			UserDetails userDetails = getUserDetails();
			try {
				CustomerAlignment customerAlgmt = new CustomerAlignment();
				SalesPosition salesPosition = new SalesPosition();
				salesPosition.setId(Long.valueOf(174));
				
				Customer customer =new Customer();
				customer.setId(Long.valueOf(1078));
				customerAlgmt.setId(Long.valueOf(3));
				customerAlgmt.setSalesPosition(salesPosition);
				customerAlgmt.setCustomer(customer);
				boolean result =customerPushOfflineService.lockCustomerAlignment(customerAlgmt,userDetails);
				Assert.assertTrue(result);
			} catch (Exception e) {
				LOGGER.error("Error");
			} 
		}
	 	
	 	@Test
		public void testAssignApproversForSourceAndTarget() {
			LOGGER.info("----Started AssignApproversForSourceAndTarget------");
			BatchMultiTenancyUtil.setTenantContext("am");
			try {
				UserDetails userDetails = getUserDetails();
				CustomerAlignmentDTO customerAlgmtDTO = getCustomerAlignmentDTO();
				customerPushOfflineService.assignApproversForSourceAndTarget(customerAlgmtDTO, userDetails);
				Assert.assertTrue(true);
			} catch (Exception e) {
				LOGGER.error("Error");
			} 
		}
	 	
	 	private CustomerAlignmentDTO getCustomerAlignmentDTO(){
			CustomerAlignmentDTO custAlgmDTO = new CustomerAlignmentDTO();
			CustomerAlignment sourceBaseCustAlgmt = new CustomerAlignment();
			CustomerAlignment targetBaseCustAlgmt = new CustomerAlignment();
			ChangeRequest mainCR = new ChangeRequest();
			
			mainCR.setId((long)  1886);
			custAlgmDTO.setMainCR(mainCR);
			sourceBaseCustAlgmt.setSalesPosition(getSrcSalesPosition());
			targetBaseCustAlgmt.setSalesPosition(getTrgtSalesPosition());

			custAlgmDTO.setSourceBaseCustAlgmt(sourceBaseCustAlgmt);
			custAlgmDTO.setTargetBaseCustAlgmt(targetBaseCustAlgmt);
			return custAlgmDTO;
		}

		private SalesPosition getSrcSalesPosition(){
			SalesPosition salesPosition = new SalesPosition();
			Alignment alignmment = new Alignment();
			SalesTeam salesTeam = new SalesTeam();
			BusinessUnit businessUnit = new BusinessUnit();
			SalesOrgHierarchy hierarchy = new SalesOrgHierarchy();

			alignmment.setId((long) 7);
			businessUnit.setId((long) 2);
			salesTeam.setId((long) 7);

			salesPosition.setId((long) 313);
			hierarchy.setId((long) 5);

			salesPosition.setHierarchy(hierarchy);		
			salesTeam.setBusinessUnit(businessUnit);		
			alignmment.setSalesTeam(salesTeam);		
			salesPosition.setAlignmment(alignmment);
			return salesPosition;
		}

		private SalesPosition getTrgtSalesPosition(){
			SalesPosition salesPosition = new SalesPosition();
			Alignment alignmment = new Alignment();
			SalesTeam salesTeam = new SalesTeam();
			BusinessUnit businessUnit = new BusinessUnit();
			SalesOrgHierarchy hierarchy = new SalesOrgHierarchy();

			alignmment.setId((long) 7);
			businessUnit.setId((long) 2);
			salesTeam.setId((long) 7);

			salesPosition.setId((long) 313);
			hierarchy.setId((long) 5);

			salesPosition.setHierarchy(hierarchy);		
			salesTeam.setBusinessUnit(businessUnit);		
			alignmment.setSalesTeam(salesTeam);		
			salesPosition.setAlignmment(alignmment);
			return salesPosition;
		}

	 	
	 	private UserDetails getUserDetails(){
			UserDetails userDetails = new UserDetails();
			userDetails.setTenantId((short) 1);
			userDetails.setUserId(1);
			userDetails.setTenantCode("am");
			return userDetails;
		}
	 	
	 	@Test
		public void test() {
	 		System.out.println("Working");
	 		LOGGER.info("Working");
	 	}
	 	
	 	@Test
		public void testProcessSourceOnSubmit() {
	 		CustomerAlignmentDTO customerAlgmtDTO = null;
	 		try {
	 			customerPushOfflineService.processSourceOnSubmit(customerAlgmtDTO, getUserDetails());
			} catch (Exception e) {
				LOGGER.error("Error while executing test method for processTargetOnSubmit");
			} 
	 		
	 	}
	 	
	 	@Test
		public void testProcessTargetOnSubmit() {
	 		CustomerAlignmentDTO customerAlgmtDTO = null;
	 		try {
	 			customerPushOfflineService.processTargetOnSubmit(customerAlgmtDTO, getUserDetails());
			} catch (Exception e) {
				LOGGER.error("Error while executing test method for processTargetOnSubmit");
			} 
	 		
	 	}
	 	
}