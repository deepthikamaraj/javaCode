package com.cognizant.opserv.sp.service.impl.test.salesorg;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cognizant.opserv.sp.exception.SalesOrgServiceException;
import com.cognizant.opserv.sp.model.Alignment;
import com.cognizant.opserv.sp.model.BusinessUnit;
import com.cognizant.opserv.sp.model.SalesOrgHierarchy;
import com.cognizant.opserv.sp.model.SalesTeam;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.service.base.test.BaseTest;
import com.cognizant.opserv.sp.service.salesorg.SalesHierarchyService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext-servicetest.xml" })
public class SalesHierarchyServiceImplTest extends BaseTest{

	@Autowired
	private SalesHierarchyService salesHierarchyService;
	
	@Test  
	public void getAlignmentSalesHierarchyTest() throws SalesOrgServiceException{
		Alignment alignment = new Alignment();
		alignment.setId(1L);
		SalesTeam salesTeam = new SalesTeam();
		salesTeam.setId(1L);
		BusinessUnit businessUnit = new BusinessUnit();
		businessUnit.setId(2L);
		salesTeam.setBusinessUnit(businessUnit);
		alignment.setSalesTeam(salesTeam);
		UserDetails userDetails = new UserDetails();
		userDetails.setTenantId((short)1);
		
		List<SalesOrgHierarchy> salesOrgHierarchies = salesHierarchyService.getAlignmentSalesHierarchy(alignment, userDetails);
		Assert.assertNotNull(salesOrgHierarchies);
		Assert.assertEquals(salesOrgHierarchies.size(), 4);
		System.out.println("salesOrgHierarchies size ::: " + salesOrgHierarchies.size());
//		System.out.println("salesOrgHierarchies name1 ::: " + salesOrgHierarchies.get(0).getLevel());
//		System.out.println("salesOrgHierarchies name2 ::: " + salesOrgHierarchies.get(1).getLevel());
//		System.out.println("salesOrgHierarchies name3 ::: " + salesOrgHierarchies.get(2).getLevel());
//		System.out.println("salesOrgHierarchies name4 ::: " + salesOrgHierarchies.get(3).getLevel());
		
	}
	
	@Test
	public void getSalesHierarchyInformationTest() throws SalesOrgServiceException{
		Long salesHierId = 2l;
		UserDetails userDetails = new UserDetails();
		userDetails.setTenantId((short)1);

		SalesOrgHierarchy salesOrgHier = salesHierarchyService.getSalesHierarchyInformation(salesHierId, userDetails);
		Assert.assertNotNull(salesOrgHier);
	}
	
	@Test
	public void getChildSalesHierarchyTest() throws SalesOrgServiceException{
		Long salesHierId = 2l;
		
		UserDetails userDetails = new UserDetails();
		userDetails.setTenantId((short)1);
		
		List<SalesOrgHierarchy> salesOrgHIerList = salesHierarchyService.getChildSalesHierarchy(salesHierId, userDetails);
		Assert.assertNotNull(salesOrgHIerList);
	}
	
	@Test
	public void getZipAssignmentsTest() throws SalesOrgServiceException{
		Alignment alignment = new Alignment();
		alignment.setId(7L);
		BusinessUnit businessUnit = new BusinessUnit();
		businessUnit.setId(2L);
		SalesTeam salesTeam = new SalesTeam();
		salesTeam.setId(7L);
		salesTeam.setBusinessUnit(businessUnit);
		alignment.setSalesTeam(salesTeam);
		UserDetails userDetails = new UserDetails();
		userDetails.setTenantId((short) 1);

		List<SalesOrgHierarchy> zipAssignments = salesHierarchyService.getZipAssignmentsByAlignment(alignment, userDetails);
	}
}
