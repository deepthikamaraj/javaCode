package com.cognizant.opserv.sp.service.impl.test.businessrule;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cognizant.opserv.sp.exception.CustomerServiceException;
import com.cognizant.opserv.sp.model.Alignment;
import com.cognizant.opserv.sp.model.BusinessUnit;
import com.cognizant.opserv.sp.model.SalesTeam;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.service.base.test.BaseTest;
import com.cognizant.opserv.sp.service.businessrule.BusinessRuleService;

public class BusinessRuleServiceTest extends BaseTest{
	
	@Autowired
	BusinessRuleService businessRuleService;
	
	@Test
	public void isCustomerMovementAllowedTest() throws CustomerServiceException{
		Alignment alignment = new Alignment();
		alignment.setId(11L);
		BusinessUnit businessUnit = new BusinessUnit();
		businessUnit.setId(6L);
		SalesTeam salesTeam = new SalesTeam();
		salesTeam.setId(2L);
		salesTeam.setBusinessUnit(businessUnit);
		alignment.setSalesTeam(salesTeam);
		UserDetails userDetails = new UserDetails();
		userDetails.setTenantId((short) 1);
		
		Date before = new Date();
		System.out.println(businessRuleService.isCustomerMovementAllowed(alignment, userDetails));
		Date after = new Date();
		System.out.println(after.getTime() - before.getTime());
	}
	
	@Test
	public void isContiguityCheckEnabledTest() throws CustomerServiceException{
		Alignment alignment = new Alignment();
		alignment.setId(11L);
		BusinessUnit businessUnit = new BusinessUnit();
		businessUnit.setId(6L);
		SalesTeam salesTeam = new SalesTeam();
		salesTeam.setId(2L);
		salesTeam.setBusinessUnit(businessUnit);
		alignment.setSalesTeam(salesTeam);
		UserDetails userDetails = new UserDetails();
		userDetails.setTenantId((short) 1);
		
		Date before = new Date();
		System.out.println(businessRuleService.isContiguityCheckEnabled(alignment, userDetails));
		Date after = new Date();
		System.out.println(after.getTime() - before.getTime());
	}

}
