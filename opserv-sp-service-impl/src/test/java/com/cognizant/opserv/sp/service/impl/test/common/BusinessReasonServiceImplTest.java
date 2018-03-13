package com.cognizant.opserv.sp.service.impl.test.common;

import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cognizant.opserv.sp.common.ChangeRequestTriggerType;
import com.cognizant.opserv.sp.common.CustomerCategoryType;
import com.cognizant.opserv.sp.exception.OpservUndefinedException;
import com.cognizant.opserv.sp.model.Alignment;
import com.cognizant.opserv.sp.model.BusinessReason;
import com.cognizant.opserv.sp.model.BusinessUnit;
import com.cognizant.opserv.sp.model.SalesTeam;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.service.common.BusinessReasonService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext-servicetest.xml" })
public class BusinessReasonServiceImplTest {

	@Autowired
	BusinessReasonService businessReasonService;
	
	@BeforeClass
	public static void setUp() {
    	
		Assert.assertTrue("----->SETUP<-----", true);
		System.setProperty("opserv.activeMQ.brokerURL", "tcp://localhost:61616");
		System.setProperty("opserv.config.file", "c:/opserv/config/opserv-config.properties");
	}
	
	@Test
	public void fetchBusinessReasons() throws OpservUndefinedException {
		
		Alignment alignment = new Alignment();
		alignment.setId(7L);
		SalesTeam salesTeam = new SalesTeam();
		salesTeam.setId(7L);
		BusinessUnit businessUnit = new BusinessUnit();
		businessUnit.setId(2L);
		salesTeam.setBusinessUnit(businessUnit);
		alignment.setSalesTeam(salesTeam);
		
		ChangeRequestTriggerType trType = ChangeRequestTriggerType.PUSH_CUSTOMER;
		
		CustomerCategoryType custCatagory = CustomerCategoryType.ACCOUNT;

		UserDetails userDetails = new UserDetails();
		userDetails.setTenantId((short)1);		
		
		List<BusinessReason> BusinessReasonList = businessReasonService.fetchBusinessReasons(alignment, trType, custCatagory, userDetails);
		
		Assert.assertNotNull(BusinessReasonList);
	}
}
