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

import com.cognizant.opserv.sp.common.MirrorSalesPositionType;
import com.cognizant.opserv.sp.common.SalesPositionType;
import com.cognizant.opserv.sp.common.util.DateUtil;
import com.cognizant.opserv.sp.exception.SalesPositionServiceException;
import com.cognizant.opserv.sp.model.Alignment;
import com.cognizant.opserv.sp.model.BusinessUnit;
import com.cognizant.opserv.sp.model.SalesOrgHierarchy;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.model.SalesTeam;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.service.salesorg.SalesPositionSetupService;
import com.cognizant.peg.core.common.ISearchCriteria;
import com.cognizant.peg.core.common.SearchCriteria;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext-servicetest.xml" })
public class SalesPositionSetupServiceImplTest {

    @Autowired
    SalesPositionSetupService salesPositionSetupService;
    
	@BeforeClass
	public static void setUp() {
	   System.setProperty("opserv.activeMQ.brokerURL", "tcp://localhost:61616");
		System.setProperty("opserv.config.file", "c:/opserv/config/opserv-config.properties");
	}
		
	@Test
	public void createSalesPositionTest(){
		try {
			System.out.println(System.currentTimeMillis());
			salesPositionSetupService.createSalesPosition(getSalesPosition(), userDetails());
			System.out.println(System.currentTimeMillis());
		} catch (SalesPositionServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void updateSalesPositionTest(){
		try {
			System.out.println(System.currentTimeMillis() % 1000);
			salesPositionSetupService.updateSalesPosition(getSalesPosition(), userDetails());
			System.out.println(System.currentTimeMillis() % 1000);
		} catch (SalesPositionServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void inactiveSalesPositionTest(){
		System.out.println(System.currentTimeMillis() % 1000);
		Long salePositionId = 613L;
		try {
			salesPositionSetupService.inactiveSalesPosition(salePositionId, userDetails());
			System.out.println(System.currentTimeMillis() % 1000);
		} catch (SalesPositionServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test // 900mili sec 1SP with one child
	public void getSalesPositionDetailsTest(){
		System.out.println(System.currentTimeMillis() % 1000);
		Long salePositionId = 5L;
		SalesPosition salesPosition = null;
		try {
			salesPosition = salesPositionSetupService.getSalesPositionDetails(salePositionId, userDetails());
			System.out.println(System.currentTimeMillis() % 1000);
		} catch (SalesPositionServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Test // 52mili sec for 1SP with one child
	public void fetchSalesPositionsTest(){
		System.out.println(System.currentTimeMillis() % 1000);
		ISearchCriteria criteria = new SearchCriteria();
		criteria.addLike("posName", "%INBU APEX SF NSD%");
		ISearchCriteria criteria2 = new SearchCriteria();
		criteria2.addEqualTo("posCd", "30000");
		criteria.addAndCriteria(criteria2);
		List<SalesPosition> listOfSP = new ArrayList<SalesPosition>();
		try {
			listOfSP = salesPositionSetupService.fetchSalesPositions(criteria, userDetails());
			System.out.println(System.currentTimeMillis() % 1000);
			Assert.assertNotNull(listOfSP);
			System.out.println("List Size ::: " + listOfSP.size());
		} catch (SalesPositionServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static UserDetails userDetails(){
		UserDetails details = new UserDetails();
		details.setTenantId((short)1);
		return details;
	}
	   
	public static SalesPosition getSalesPosition(){
		SalesPosition salesPosition = new SalesPosition();
		
//		salesPosition.setId(613L);	
		salesPosition.setCode("2ndEntryCode");
		salesPosition.setName("2ndEntryINBU APEX SF NSDupdated");
		Alignment alignment = new Alignment();
		SalesTeam salesTeam = new SalesTeam();
		BusinessUnit businessUnit = new BusinessUnit();
		
		alignment.setId(7L);
		salesTeam.setId(7L);
		businessUnit.setId(2L);
		
		salesTeam.setBusinessUnit(businessUnit);
		alignment.setSalesTeam(salesTeam);
		
		salesPosition.setAlignmment(alignment);
		
		SalesPosition parentSalesPos = new SalesPosition();
		parentSalesPos.setId(null);

		salesPosition.setParentSalesPosition(parentSalesPos);
		
		SalesOrgHierarchy salesOrgHierarchy = new SalesOrgHierarchy();
		salesOrgHierarchy.setId(1L);

		SalesOrgHierarchy parentOrgHier = new SalesOrgHierarchy();
		parentOrgHier.setId(null);
		salesOrgHierarchy.setParentHierarchy(parentOrgHier);
		
		salesOrgHierarchy.setName("DM");
		salesOrgHierarchy.setActive(true);
		salesOrgHierarchy.setLevel((short)2);
		salesOrgHierarchy.setCreatedBy(1);
		salesOrgHierarchy.setCreatedDate(DateUtil.getCurrentDate());
		salesOrgHierarchy.setStartDate(DateUtil.getCurrentDate());
		salesOrgHierarchy.setEndDate(null);
		salesOrgHierarchy.setTenantId((short)1);
		salesOrgHierarchy.setAssignZipFlag(true);	
		salesOrgHierarchy.setMoveSpFlag(false);

		salesPosition.setHierarchy(salesOrgHierarchy);
	
		salesPosition.setSalesPositionType(SalesPositionType.BASE);//Enum
		salesPosition.setActive(true);
		salesPosition.setCreatedDate(DateUtil.getCurrentDate());
		salesPosition.setCreatedBy(1);
		salesPosition.setStartDate(DateUtil.getCurrentDate());
		salesPosition.setEndDate(null);
		salesPosition.setTenantId((short)1);
		
		// isJobShared
		salesPosition.setJobShared(true);
		salesPosition.setMirrorSalesPositionType(MirrorSalesPositionType.ONE_N);	
		salesPosition.setRoot(true);
		
		return salesPosition;
	}
}
