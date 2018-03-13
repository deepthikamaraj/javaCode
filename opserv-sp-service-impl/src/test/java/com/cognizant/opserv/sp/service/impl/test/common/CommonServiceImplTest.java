package com.cognizant.opserv.sp.service.impl.test.common;

import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cognizant.opserv.sp.exception.CustomerServiceException;
import com.cognizant.opserv.sp.model.CountryDivision;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.service.base.test.BaseTest;
import com.cognizant.opserv.sp.service.common.CommonService;

public class CommonServiceImplTest extends BaseTest {

	@Autowired
	private CommonService commonService;
	
	@Test
	public void getAllCustomerTypes() throws NumberFormatException, CustomerServiceException{
		 
		Map<Integer, String> customerTypes = commonService.getAllCustomerTypes(Short.valueOf("1"), "en_US");
		Assert.assertNotNull(customerTypes);

	}
	
	@Test
	public void getLowestCountryDivisionTest(){
		UserDetails userDetails = new UserDetails();
		userDetails.setTenantId((short) 1);
		CountryDivision countryDivision = commonService.getLowestCountryDivision(userDetails);
		Assert.assertNotNull(countryDivision);
	}
}
