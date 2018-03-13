package com.cognizant.opserv.sp.service.impl.test.salesorg;

import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cognizant.opserv.sp.exception.AlignmentServiceException;
import com.cognizant.opserv.sp.model.Alignment;
import com.cognizant.opserv.sp.model.BusinessUnit;
import com.cognizant.opserv.sp.model.SalesTeam;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.service.base.test.BaseTest;
import com.cognizant.opserv.sp.service.salesorg.AlignmentSetupService;
import com.cognizant.peg.core.common.ISearchCriteria;
import com.cognizant.peg.core.common.SearchCriteria;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext-servicetest.xml" })
public class AlignmentSetupServiceImplTest extends BaseTest {
	
	
	@Autowired
	 private AlignmentSetupService alignmentSetupService;
	
		
		@Test
		public void getAlignmentDetails() throws   AlignmentServiceException {
			UserDetails userDetails = new UserDetails();
			userDetails.setTenantId((short)1);
			Alignment alignment 	= alignmentSetupService.getAlignmentDetails(1l, userDetails);
			System.out.println("Alignment Name:"+alignment.getName());
			System.out.println("Alignment StartDate:"+alignment.getStartDate());
			System.out.println("Alignment EndDate:"+alignment.getEndDate());
			System.out.println("Alignment SalesTeam Name:"+alignment.getSalesTeam().getName());
			System.out.println("Alignment SalesTeam Name:"+alignment.getSalesTeam().getId());
			System.out.println("Alignment BU Name:"+alignment.getSalesTeam().getBusinessUnit().getName());
			System.out.println("Alignment BU Code:"+alignment.getSalesTeam().getBusinessUnit().getCode());
			Assert.assertNotNull(alignment);
			
		}
	
		@Test
		public void getAlignmentInactive() throws   AlignmentServiceException {
			UserDetails userDetails = new UserDetails();
			userDetails.setTenantId((short)1);
			Alignment alignment =new Alignment();
			alignment.setId(1l);
			alignment.setActive(false);
			alignmentSetupService.inactivateAlignment(alignment, userDetails);
			Assert.assertNotNull(alignment);
			
		}
		
		
		@Test
		public void searchAlignmentsByCriteriaTest() throws AlignmentServiceException{
			ISearchCriteria criteria = new SearchCriteria();
			UserDetails userDetails = new UserDetails();
			userDetails.setTenantId((short)1);
			criteria.addLike("algmntName", "%DEMO%");
			criteria.addLimit(0, 5);
			List<Alignment> AlignmentList = alignmentSetupService.fetchAlignments(criteria, userDetails);
			
		}
		
		
		@Test
		public void updateAlignmentDetails() throws   AlignmentServiceException {
			UserDetails userDetails = new UserDetails();
			userDetails.setTenantId((short)1);
			userDetails.setStaffId(1);
			Alignment alignment =new Alignment();
			SalesTeam salesTeam =new SalesTeam();
			BusinessUnit businessUnit =new BusinessUnit();
			businessUnit.setId(2l);
			salesTeam.setId(3l);
			salesTeam.setBusinessUnit(businessUnit);
			alignment.setSalesTeam(salesTeam);
			alignment.setName("TestALG");
			alignment.setEndDate(new Date());
			alignment.setStartDate(new Date());
			alignment.setActive(true);
			alignment.setId(15l);
			
			 alignmentSetupService.updateAlignment(alignment, userDetails);
			Assert.assertNotNull(alignment);
			
		}
		
		@Test
		public void createAlignment() throws   AlignmentServiceException {
			UserDetails userDetails = new UserDetails();
			userDetails.setTenantId((short)1);
			userDetails.setStaffId(1);
			Alignment alignment =new Alignment();
			SalesTeam salesTeam =new SalesTeam();
			BusinessUnit businessUnit =new BusinessUnit();
			businessUnit.setId(2l);
			salesTeam.setId(3l);
			salesTeam.setBusinessUnit(businessUnit);
			alignment.setSalesTeam(salesTeam);
			alignment.setName("TestALG");
			alignment.setEndDate(new Date());
			alignment.setStartDate(new Date());
			alignment.setActive(true);
			
			 alignmentSetupService.createAlignment(alignment, userDetails);
			Assert.assertNotNull(alignment);
			
			
		}
	
	

}
