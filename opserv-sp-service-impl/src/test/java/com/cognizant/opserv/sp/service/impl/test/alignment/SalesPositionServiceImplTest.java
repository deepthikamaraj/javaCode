package com.cognizant.opserv.sp.service.impl.test.alignment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cognizant.opserv.sp.common.MirrorSalesPositionType;
import com.cognizant.opserv.sp.common.SalesPositionType;
import com.cognizant.opserv.sp.exception.AlignmentServiceException;
import com.cognizant.opserv.sp.exception.SalesPositionServiceException;
import com.cognizant.opserv.sp.model.Alignment;
import com.cognizant.opserv.sp.model.BusinessUnit;
import com.cognizant.opserv.sp.model.GeoShapePolygon;
import com.cognizant.opserv.sp.model.SalesOrgHierarchy;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.model.SalesTeam;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.service.alignment.SalesPositionService;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext-servicetest.xml" })
public class SalesPositionServiceImplTest  {

	private static final OpservLogger LOGGER= OpservLoggerFactory.getOpservLogger(SalesPositionServiceImplTest.class);
	@Autowired
	SalesPositionService salesPositionService;
	
	
	   @BeforeClass
		public static void setUp() {
		   System.setProperty("opserv.activeMQ.brokerURL", "tcp://localhost:61616");
			System.setProperty("opserv.config.file", "c:/opserv/config/opserv-config.properties");
		}
	
	@Test // ok tested 
	public void getAllChildSalesPositionsTest(){
			
			long salesPosId = 1L;
			UserDetails userDetails = new UserDetails();
			userDetails.setTenantId((short)1);
			
			List<SalesPosition> posLists;
			try {
				posLists = salesPositionService.getAllChildSalesPositions(salesPosId, userDetails);
				org.junit.Assert.assertNotNull(posLists);
				org.junit.Assert.assertEquals(posLists.size(), 2);
				LOGGER.debug(" 1st Child:::: " + posLists.get(0).getName());
				LOGGER.debug(" 2nd Child:::: " + posLists.get(1).getName());
				LOGGER.debug(" size:: " + posLists.size());
			} catch (AlignmentServiceException e) {
				// TODO Auto-generated catch block
				LOGGER.debug(" Msg :: " + e.getMessage());
			}
			
			
			}
	
	@Test //ok tested
	public void getSalesPositionInformationTest(){
		long salesPosId = 396L;
		UserDetails userDetails = new UserDetails();
		userDetails.setTenantId((short)1);
		try{
			SalesPosition position = salesPositionService.getSalesPositionInformation(salesPosId, userDetails);	
			
			org.junit.Assert.assertNotNull(position);
			org.junit.Assert.assertEquals(position.getName(),"SPTestMarch31");
			org.junit.Assert.assertTrue(position.getId() == 1L);
			
			LOGGER.debug(" Id :: " + position.getId());
			LOGGER.debug(" Name :: " + position.getName());
			LOGGER.debug(position.getChildSalesPositions().get(0).getName());
			LOGGER.debug(position.getChildSalesPositions().get(1).getName());
		}catch(AlignmentServiceException alignmentServiceException){
			alignmentServiceException.printStackTrace();
			LOGGER.debug(" Msg :: " + alignmentServiceException.getMessage());
		}
	}
	
	@Test //ok Tested
	public void getAllSalesPositionsByNameTest() {
		String salesPosName = "SPTest";
		Alignment alignment = new Alignment();
		alignment.setId(7L);
		SalesTeam salesTeam = new SalesTeam();
		salesTeam.setId(1L);
		BusinessUnit businessUnit = new BusinessUnit();
		businessUnit.setId(1L);
		salesTeam.setBusinessUnit(businessUnit);
		alignment.setSalesTeam(salesTeam);
		UserDetails userDetails = new UserDetails();
		userDetails.setTenantId((short)1);
		List<SalesPosition> position;
		try {
			position = salesPositionService.getAllSalesPositionsByName(salesPosName, alignment, userDetails);
			
			org.junit.Assert.assertNotNull(position);
			org.junit.Assert.assertEquals(position.size(), 4);
			System.out.println(" size:: " + position.size());
		} catch (AlignmentServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(" Msg :: " + e.getMessage());
		}
	
		
	
	}
	@Test
	public void getAllSalesPositionsByAlignmentTest(){
		Alignment alignment = new Alignment();
		alignment.setId(7L);
		SalesTeam salesTeam = new SalesTeam();
		salesTeam.setId(7L);
		BusinessUnit businessUnit = new BusinessUnit();
		businessUnit.setId(2L);
		salesTeam.setBusinessUnit(businessUnit);
		
		alignment.setSalesTeam(salesTeam);
		UserDetails userDetails = new UserDetails();
		userDetails.setTenantId((short)1);
		List<SalesPosition> position;
		try {
			position = salesPositionService.getAllSalesPositionsByAlignment(alignment, userDetails);
			LOGGER.debug(" Count"+position.size());
			
			LOGGER.debug(" Caching.....");
			
			Alignment alignment1 = new Alignment();
			alignment1.setId(7L);
			SalesTeam salesTeam1 = new SalesTeam();
			salesTeam1.setId(7L);
			BusinessUnit businessUnit1 = new BusinessUnit();
			businessUnit1.setId(2L);
			salesTeam1.setBusinessUnit(businessUnit1);
			alignment1.setSalesTeam(salesTeam1);
			
			position = salesPositionService.getAllSalesPositionsByAlignment(alignment1, userDetails);
			LOGGER.debug(" Count"+position.size());
			
			Alignment alignment2 = new Alignment();
			alignment2.setId(7L);
			SalesTeam salesTeam2 = new SalesTeam();
			salesTeam2.setId(10L);
			BusinessUnit businessUnit2 = new BusinessUnit();
			businessUnit2.setId(2L);
			salesTeam2.setBusinessUnit(businessUnit2);
			alignment2.setSalesTeam(salesTeam2);
			
			position = salesPositionService.getAllSalesPositionsByAlignment(alignment2, userDetails);
			LOGGER.debug(" Count"+position.size());
			org.junit.Assert.assertNotNull(position);
			org.junit.Assert.assertEquals(position.size(), 4);
			LOGGER.debug(" size:: " + position.size());
		} catch (AlignmentServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			LOGGER.debug(" Msg :: " + e.getMessage());
		}
	}
	
	@Test
	public void getAllMirrorSalesPositionsByAlignmentTest(){
		
		Alignment alignment = new Alignment();
		alignment.setId(1L);
		SalesTeam salesTeam = new SalesTeam();
		salesTeam.setId(2L);
		BusinessUnit businessUnit = new BusinessUnit();
		businessUnit.setId(1L);
		salesTeam.setBusinessUnit(businessUnit);
		alignment.setSalesTeam(salesTeam);
		SalesOrgHierarchy hierarchy = new SalesOrgHierarchy();
		hierarchy.setId(1L);
		
		SalesPosition salesPosition=new SalesPosition();
		salesPosition.setId((long) 102);
		salesPosition.setAlignmment(alignment);
		salesPosition.setSalesPositionType(SalesPositionType.BASE);
		salesPosition.setMirrorSalesPositionType(MirrorSalesPositionType.ONE_N);
		salesPosition.setHierarchy(hierarchy);
		UserDetails userDetails=new UserDetails();
		userDetails.setTenantId((short) 1);
		
		try {
			
		List<SalesPosition>	salesPositionList=salesPositionService.getMirroredSalesPositions(salesPosition, userDetails);
		org.junit.Assert.assertNotNull(salesPositionList);
		LOGGER.debug(" size:: " + salesPositionList.size());
		} catch (AlignmentServiceException e) {
			
			e.printStackTrace();
		}
	}
	
	@Test
	public void getParentSalesPositionTest() throws AlignmentServiceException{
		SalesPosition salesPosition = new SalesPosition();
		salesPosition.setId(9L);
		UserDetails userDetails = new UserDetails();
		userDetails.setTenantId((short) 1);
		Date before = new Date();
		SalesPosition prnSalesPosition = salesPositionService.getParentSalesPosition(salesPosition, userDetails);
		Date after = new Date();
		org.junit.Assert.assertNotNull(prnSalesPosition);
	}
	
	@Test
	public void getShapePolygonBySalesPositionTest() throws SalesPositionServiceException{
		List<SalesPosition> salesPositions = new ArrayList<SalesPosition>();
		SalesOrgHierarchy salesOrgHierarchy = new SalesOrgHierarchy();
		salesOrgHierarchy.setId(5l);
		
		SalesPosition salesPosition1 = new SalesPosition();
		salesPosition1.setId(396l);
		salesPosition1.setHierarchy(salesOrgHierarchy);
		salesPositions.add(salesPosition1);
		SalesPosition salesPosition2 = new SalesPosition();
		salesPosition2.setId(398l);
		salesPositions.add(salesPosition2);
		SalesPosition salesPosition3 = new SalesPosition();
		salesPosition3.setId(174l);
		salesPositions.add(salesPosition3);
		SalesPosition salesPosition4 = new SalesPosition();
		salesPosition4.setId(397l);
		salesPositions.add(salesPosition4);
		UserDetails userDetails = new UserDetails();
		userDetails.setTenantId((short) 1);
		
		Date before = new Date();
		List<GeoShapePolygon> geoShapePolygons = salesPositionService.getShapePolygonBySalesPosition(salesPositions, userDetails);
		org.junit.Assert.assertNotNull(geoShapePolygons);
		Date after = new Date();
	}
	
}
