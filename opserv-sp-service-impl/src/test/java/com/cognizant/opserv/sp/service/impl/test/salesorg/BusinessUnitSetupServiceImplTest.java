package com.cognizant.opserv.sp.service.impl.test.salesorg;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cognizant.opserv.sp.common.util.DateUtil;
import com.cognizant.opserv.sp.exception.BussUnitServiceException;
import com.cognizant.opserv.sp.model.BusinessUnit;
import com.cognizant.opserv.sp.model.SalesOrg;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.service.salesorg.BusinessUnitSetupService;
import com.cognizant.peg.core.common.ISearchCriteria;
import com.cognizant.peg.core.common.SearchCriteria;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext-servicetest.xml" })
public class BusinessUnitSetupServiceImplTest {

    @Autowired
    BusinessUnitSetupService businessUnitSetupService;
    
	@BeforeClass
	public static void setUp() {
	   System.setProperty("opserv.activeMQ.brokerURL", "tcp://localhost:61616");
		System.setProperty("opserv.config.file", "c:/opserv/config/opserv-config.properties");
	}
	
	@Test
	public void createBusinessUnitTest(){
		try {
			System.out.println(System.currentTimeMillis());
			businessUnitSetupService.createBusinessUnit(getBusinessUnit(), userDetails());
			System.out.println(System.currentTimeMillis());
		} catch (BussUnitServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void updateBusinessUnitTest(){
		try {
			System.out.println(System.currentTimeMillis() % 1000);
			businessUnitSetupService.updateBusinessUnit(getBusinessUnit(), userDetails());
			System.out.println(System.currentTimeMillis() % 1000);
		} catch (BussUnitServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void inactiveBusinessUnitTest(){
		System.out.println(System.currentTimeMillis() % 1000);
		Long businessUnitId = 1L;
		try {
			businessUnitSetupService.inactiveBussUnit(businessUnitId, userDetails());
			System.out.println(System.currentTimeMillis() % 1000);
		} catch (BussUnitServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test // 900mili sec 1SP with one child
	public void getBusinessUnitDetailsTest(){
		System.out.println(System.currentTimeMillis() % 1000);
		Long businessUnitId = 1L;
		BusinessUnit businessUnit  = null;
		try {
			businessUnit = businessUnitSetupService.getBussUnitDetails(businessUnitId, userDetails());
			System.out.println(System.currentTimeMillis() % 1000);
		} catch (BussUnitServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Test 
	public void fetchBusinessUnitsTest(){
		System.out.println(System.currentTimeMillis() % 1000);
		ISearchCriteria criteria = new SearchCriteria();
		criteria.addEqualTo("bussUnitId", 1);
		ISearchCriteria criteria2 = new SearchCriteria();
		criteria2.addEqualTo("bussUnitName", "BCBU");
		criteria.addAndCriteria(criteria2);
		List<BusinessUnit> listOfBU = new ArrayList<BusinessUnit>();
		try {
			listOfBU = businessUnitSetupService.fetchBussUnitsBySearchCriteria(criteria, userDetails());
			System.out.println(System.currentTimeMillis() % 1000);
			Assert.assertNotNull(listOfBU);
			System.out.println("List Size ::: " + listOfBU.size());
		} catch (BussUnitServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static UserDetails userDetails(){
		UserDetails details = new UserDetails();
		details.setTenantId((short)1);
		return details;
	}
	
	public static BusinessUnit getBusinessUnit(){
		
		BusinessUnit businessUnit = new BusinessUnit();
//		businessUnit.setId(1L);
		businessUnit.setActive(true);
		businessUnit.setCode("BCBUnewEntry");
		businessUnit.setCreatedBy(1);
		businessUnit.setCreatedDate(DateUtil.getCurrentDate());
		businessUnit.setName("BCBUnewEntry");
		businessUnit.setDescription("BCBUnewEntry");
		businessUnit.setStartDate(DateUtil.getCurrentDate());
		businessUnit.setEndDate(null);
		businessUnit.setTenantId((short)1);
		businessUnit.setUpdatedBy(null);
		businessUnit.setUpdatedDate(null);
		
		SalesOrg salesOrg = new SalesOrg();
		salesOrg.setId(1L);
		businessUnit.setSalesOrg(salesOrg);
		return businessUnit;
	}
}
