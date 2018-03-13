package com.cognizant.opserv.sp.service.impl.test.callPlan;

import java.text.ParseException;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.opserv.sp.common.CallPlanType;
import com.cognizant.opserv.sp.exception.CallPlanServiceException;
import com.cognizant.opserv.sp.model.Alignment;
import com.cognizant.opserv.sp.model.BusinessUnit;
import com.cognizant.opserv.sp.model.Customer;
import com.cognizant.opserv.sp.model.CustomerAlignment;
import com.cognizant.opserv.sp.model.CustomerCallPlan;
import com.cognizant.opserv.sp.model.SalesOrgHierarchy;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.model.SalesTeam;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.service.base.test.BaseTest;
import com.cognizant.opserv.sp.service.callplan.CallPlanService;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;

public class CallPlanServiceTestmpl extends BaseTest {
	@BeforeClass
	public static void setUp() {
		System.setProperty("opserv.activeMQ.brokerURL", "tcp://localhost:61616");
		System.setProperty("opserv.config.file","C:/OPSERV/config/opserv-config.properties");
	} 

	@Autowired
	private CallPlanService callPlanService;
	

	private static final OpservLogger LOGGER = OpservLoggerFactory
			.getOpservLogger(CallPlanServiceTestmpl.class);

	
	@Test
	public void getCustomerCallPlanDetails() throws CallPlanServiceException {

		try {
			
			SalesPosition salesPos = new SalesPosition();
			salesPos.setId((long) 182);
			SalesOrgHierarchy salesOrgHierarchy = new SalesOrgHierarchy();
			salesOrgHierarchy.setId((long) 5);
			salesPos.setHierarchy(salesOrgHierarchy);

			Alignment alignment = new Alignment();
			alignment.setId((long) 7);
			SalesTeam salesTeam = new SalesTeam();
			salesTeam.setId((long) 7);
			BusinessUnit businessUnit = new BusinessUnit();
			businessUnit.setId((long) 2);
			alignment.setSalesTeam(salesTeam);
			salesTeam.setBusinessUnit(businessUnit);
			salesPos.setAlignmment(alignment);
			UserDetails details = new UserDetails();
			details.setTenantId((short) 1);
			Customer customer = new Customer();
			customer.setId((long) 1368);


			List<CustomerCallPlan> CustomerCallPlanDetails = callPlanService
					.getCustomerCallPlanDetails(customer, salesPos, details);
  
			for(CustomerCallPlan callPlan:CustomerCallPlanDetails){
				System.out.println(callPlan.getPlannedCalls());
			}
			 //System.out.println(CustomerCallPlanDetails.get(0).getPlannedCalls());
			 //System.out.println(CustomerCallPlanDetails.get(1).getPlannedCalls());
			
			Assert.assertNull(CustomerCallPlanDetails);

		} catch (CallPlanServiceException e) {
			LOGGER.info(" Msg :: " + e.getMessage());
		}
	}
	
	@Test
	@Transactional
	public void createDirBasedCallPlan() throws CallPlanServiceException, ParseException {
		Alignment alignment = new Alignment();
		alignment.setId((long) 1);
		SalesTeam salesTeam = new SalesTeam();
		salesTeam.setId((long) 2);
		BusinessUnit businessUnit = new BusinessUnit();
		businessUnit.setId((long) 1);
		alignment.setSalesTeam(salesTeam);
		salesTeam.setBusinessUnit(businessUnit);
		UserDetails details = new UserDetails();
		details.setTenantId((short) 1);
		details.setStaffId(1);
		CustomerCallPlan customerCallPlan = new CustomerCallPlan();
		CustomerAlignment customerAlignment = new CustomerAlignment();
		SalesPosition salesPosition = new SalesPosition();
		customerAlignment.setId((long) 136);

		customerCallPlan.setPlannedCalls(3);
		short id = (short) 2;
		CallPlanType callPlanType = CallPlanType.getAttributeType((int) id);
		customerCallPlan.setType(callPlanType);	
		Customer customer=new Customer();
		customer.setId((long) 13);
		customerAlignment.setCustomer(customer);
		salesPosition.setAlignmment(alignment);
		customerAlignment.setSalesPosition(salesPosition);
	}
		
	/*@Test
	public void getCallPlanBsdCustALId() throws CallPlanServiceException, ParseException {
		try {
			Alignment alignment = new Alignment();
			alignment.setId((long) 1);
			SalesTeam salesTeam = new SalesTeam();
			salesTeam.setId((long) 2);
			BusinessUnit businessUnit = new BusinessUnit();
			businessUnit.setId((long) 1);
			alignment.setSalesTeam(salesTeam);
			salesTeam.setBusinessUnit(businessUnit);
			UserDetails details = new UserDetails();
			details.setTenantId((short) 1);
			details.setStaffId(1);
			CustomerAlignment customerAlignment = new CustomerAlignment();
			customerAlignment.setId((long) 194);
			Customer customer=new Customer();
			customer.setId((long) 6);
			customerAlignment.setCustomer(customer);
			CustomerCallPlan customerCallPlan=new CustomerCallPlan();
			customerAlignment.setCustomerCallPlan(customerCallPlan);
								
			CustomerCallPlan save = callPlanIntService.getCallPlanBsdCustALId(customerAlignment,details);
			System.out.println(save.getPlannedCalls());
			Assert.assertNotNull(save);

		} catch (CallPlanServiceException e) {
			LOGGER.info(" Msg :: " + e.getMessage());
		}
	}*/



}