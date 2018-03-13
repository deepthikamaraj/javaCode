package com.cognizant.opserv.sp.service.impl.test.salesorg;

import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cognizant.opserv.sp.common.util.DateUtil;
import com.cognizant.opserv.sp.exception.SalesOrgServiceException;
import com.cognizant.opserv.sp.model.SalesOrgRole;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.service.salesorg.SalesOrgRoleSetupService;
import com.cognizant.peg.core.common.ISearchCriteria;
import com.cognizant.peg.core.common.SearchCriteria;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext-servicetest.xml" })
public class SalesOrgRoleSetupServiceImplTest {
	
	@Autowired
	private SalesOrgRoleSetupService salesOrgRoleSetupService;
	
    @BeforeClass
	public static void setUp() {
    	
		Assert.assertTrue("----->SETUP<-----", true);
		System.setProperty("opserv.activeMQ.brokerURL", "tcp://localhost:61616");
		System.setProperty("opserv.config.file", "c:/opserv/config/opserv-config.properties");
	}
	
	@Test
	public void createSalesOrgRole() throws SalesOrgServiceException {
		
		UserDetails userDetails = new UserDetails();
		userDetails.setTenantId((short)1);
		
		SalesOrgRole salesOrgRole = new SalesOrgRole();
		salesOrgRole.setName("Apollo Unit");
		salesOrgRole.setDescription("Health");
		salesOrgRole.setParentSalesOrgRoleId(6L);
		salesOrgRole.setActive(true);
		salesOrgRole.setStartDate(DateUtil.getCurrentDate());
		salesOrgRole.setCreatedBy(80);
		salesOrgRole.setCreatedDate(DateUtil.getCurrentDate());
		
		salesOrgRole = salesOrgRoleSetupService.createSalesOrgRole(salesOrgRole, userDetails);
		
		Assert.assertNotNull(salesOrgRole.getId());
		
	}
	
	@Test
	public void updateSalesOrgRole() throws SalesOrgServiceException {
		
		UserDetails userDetails = new UserDetails();
		userDetails.setTenantId((short)1);
		
		SalesOrgRole salesOrgRole = new SalesOrgRole();
		salesOrgRole.setId(8L);
		salesOrgRole.setName("GE Care");
		salesOrgRole.setDescription("HC");
		salesOrgRole.setParentSalesOrgRoleId(6L);
		salesOrgRole.setEndDate(DateUtil.getCurrentDate());
		salesOrgRole.setUpdatedBy(80);
		
		salesOrgRoleSetupService.updateSalesOrgRole(salesOrgRole, userDetails);
		
	}
	
	@Test
	public void inactiveSalesOrgRole() throws SalesOrgServiceException {
		
		long salesOrgRoleId = 8;
		
		UserDetails userDetails = new UserDetails();
		userDetails.setTenantId((short)1);
		
		salesOrgRoleSetupService.inactiveSalesOrgRole(salesOrgRoleId, userDetails);

	}
	
	@Test
	public void getSalesOrgRoleDetails() throws SalesOrgServiceException {
		
		long salesOrgRoleId = 8;
		
		UserDetails userDetails = new UserDetails();
		userDetails.setTenantId((short)1);
		
		SalesOrgRole salesOrgRoleCreate = salesOrgRoleSetupService.getSalesOrgRoleDetails(salesOrgRoleId, userDetails);
		
		Assert.assertNotNull(salesOrgRoleCreate.getName());
		
	}
	
	@Test
	public void fetchSalesOrgRoles() throws SalesOrgServiceException {
		
		ISearchCriteria searchCriteria = new SearchCriteria();
		searchCriteria.addLike("roleName", "%CAR%");

		UserDetails userDetails = new UserDetails();
		userDetails.setTenantId((short)1);		
		
		List<SalesOrgRole> salesOrgRoleList = salesOrgRoleSetupService.fetchSalesOrgRoles(searchCriteria, userDetails);
		
		Assert.assertNotNull(salesOrgRoleList);
	}

}