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
import com.cognizant.opserv.sp.exception.SalesTeamServiceException;
import com.cognizant.opserv.sp.model.BusinessUnit;
import com.cognizant.opserv.sp.model.SalesTeam;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.service.salesorg.SalesTeamSetupService;
import com.cognizant.peg.core.common.ISearchCriteria;
import com.cognizant.peg.core.common.SearchCriteria;
import com.cognizant.peg.core.dao.GenericDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext-servicetest.xml" })
public class SalesTeamSetupServiceImplTest {

    @Autowired
    SalesTeamSetupService salesTeamSetupService;
    @Autowired
	private GenericDAO genericDAO;
    
	@BeforeClass
	public static void setUp() {
	   System.setProperty("opserv.activeMQ.brokerURL", "tcp://localhost:61616");
		System.setProperty("opserv.config.file", "c:/opserv/config/opserv-config.properties");
	}
	
	@Test
	public void createSalesTeamTest(){
		try {
			System.out.println(System.currentTimeMillis());
			salesTeamSetupService.createSalesTeam(getSalesTeam(), userDetails());
			System.out.println(System.currentTimeMillis());
		} catch (SalesTeamServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void updateSalesTeamTest(){
		try {
			System.out.println(System.currentTimeMillis() % 1000);
			salesTeamSetupService.updateSalesTeam(getSalesTeam(), userDetails());
			System.out.println(System.currentTimeMillis() % 1000);
		} catch (SalesTeamServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void inactiveSalesTeamTest(){
		System.out.println(System.currentTimeMillis() % 1000);
		Long salesTeamId = 1L;
		Long bussUnitId = 2L;
		try {
			salesTeamSetupService.inactiveSalesTeam(salesTeamId, bussUnitId, userDetails());
			System.out.println(System.currentTimeMillis() % 1000);
		} catch (SalesTeamServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test // 900mili sec 1SP with one child
	public void getSalesTeamDetailsTest(){
		System.out.println(System.currentTimeMillis() % 1000);
		Long salesTeamId = 1L;
		Long bussUnitId = 2L;
		SalesTeam SalesTeam  = null;
		try {
			SalesTeam = salesTeamSetupService.getSalesTeamDetails(salesTeamId, bussUnitId, userDetails());
			System.out.println(System.currentTimeMillis() % 1000);
		} catch (SalesTeamServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Test // 52mili sec for 1SP with one child
	public void fetchSalesTeamsTest(){
		System.out.println(System.currentTimeMillis() % 1000);
		ISearchCriteria criteria = new SearchCriteria();
		criteria.addEqualTo("salesTeamName", "INBU ZENITH SF");
//		ISearchCriteria criteria2 = new SearchCriteria();
//		criteria2.addEqualTo("salesTeamId", 1);
//		criteria.addAndCriteria(criteria2);
		List<SalesTeam> listOfST = new ArrayList<SalesTeam>();
		try {
			listOfST = salesTeamSetupService.fetchSalesTeamsBySearchCriteria(criteria, userDetails());
			System.out.println(System.currentTimeMillis() % 1000);
			Assert.assertNotNull(listOfST);
			System.out.println("List Size ::: " + listOfST.size());
		} catch (SalesTeamServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static UserDetails userDetails(){
		UserDetails details = new UserDetails();
		details.setTenantId((short)1);
		return details;
	}
	
	public SalesTeam getSalesTeam(){
		
		SalesTeam salesTeam = new SalesTeam();
//		Long salesTeamId = genericDAO.generateID("SALES_TEAM_GRP", ApplicationConstant.TABLE, ApplicationConstant.VALUE_COL, ApplicationConstant.PRIM_KEY_COL,"1");
		salesTeam.setId(56L);
		salesTeam.setActive(true);
		salesTeam.setCode("STCOde");
		salesTeam.setCreatedBy(1);
		salesTeam.setCreatedDate(DateUtil.getCurrentDate());
		salesTeam.setName("updatedEntry");
		salesTeam.setStartDate(DateUtil.getCurrentDate());
		salesTeam.setEndDate(null);
		salesTeam.setTenantId((short)1);
		salesTeam.setUpdatedBy(null);
		salesTeam.setUpdatedDate(null);
		
		BusinessUnit businessUnit =new BusinessUnit();
		businessUnit.setId(2L);
//		businessUnit.setActive(true);
//		businessUnit.setCode("INBU");
//		businessUnit.setCreatedBy(1);
//		businessUnit.setCreatedDate(DateUtil.getCurrentDate());
//		businessUnit.setName("INBU");
//		businessUnit.setDescription("INBU");
//		businessUnit.setStartDate(DateUtil.getCurrentDate());
//		businessUnit.setEndDate(null);
//		businessUnit.setTenantId((short)1);
//		businessUnit.setUpdatedBy(null);
//		businessUnit.setUpdatedDate(null);
//		
//		SalesOrg salesOrg = new SalesOrg();
//		salesOrg.setId(1L);
//		businessUnit.setSalesOrg(salesOrg);
		
		salesTeam.setBusinessUnit(businessUnit);
		
		
		return salesTeam;
	}
	
	
}
