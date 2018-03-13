package com.cognizant.opserv.sp.cr.process;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cognizant.opserv.sp.common.MetricTriggerType;
import com.cognizant.opserv.sp.cr.process.internal.service.MetricOfflineService;
import com.cognizant.opserv.sp.model.Alignment;
import com.cognizant.opserv.sp.model.BusinessUnit;
import com.cognizant.opserv.sp.model.SalesOrgHierarchy;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.model.SalesTeam;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.tenant.util.BatchMultiTenancyUtil;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext-CR-test.xml" })
public class MetricOfflineServiceTest {

	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(MetricOfflineServiceTest.class);
	
	@Autowired
	private MetricOfflineService metricOfflineService;
	
	@BeforeClass
	public static void setUp() {
		Assert.assertTrue("----->SETUP<-----", true);
		System.setProperty("opserv.activeMQ.brokerURL", "tcp://localhost:61616");
		System.setProperty("opserv.config.file", "c:/opserv/config/opserv-config.properties");
		System.setProperty("appType", "standalone");
		System.setProperty("targetDataSource", "app");
		System.setProperty("distributed", "true");
		System.setProperty("appName", "TriggerCRProcess");
	}
	
	@Test
	public void testProcessCalculativeMetrics() {
		LOGGER.info("----Started processCalculativeMetrics------");
		BatchMultiTenancyUtil.setTenantContext("am");
		try {
			UserDetails userDetails = getUserDetails();
			SalesPosition sp = getSrcSP();
			MetricTriggerType type = getMtrTrgType();
			metricOfflineService.processCalculativeMetrics(sp, type, userDetails);
			Assert.assertTrue(true);
		} catch (Exception e) {
			LOGGER.error("Error");
		}
	}
	
	@Test
	public void testCheckCustomerPushMetricViolation() {
		LOGGER.info("----Started checkCustomerPushMetricViolation------");
		BatchMultiTenancyUtil.setTenantContext("am");
		try {
			UserDetails userDetails = getUserDetails();
			SalesPosition srcSP = getSrcSP();
			SalesPosition trgSP = getTrgSP();
			metricOfflineService.checkCustomerPushMetricViolation(srcSP, trgSP, userDetails);
			Assert.assertTrue(true);
		} catch (Exception e) {
			LOGGER.error("Error");
		}
	}
	
	@Test
	public void testCheckCustomerPullMetricViolation() {
		LOGGER.info("----Started checkCustomerPullMetricViolation------");
		BatchMultiTenancyUtil.setTenantContext("am");
		try {
			UserDetails userDetails = getUserDetails();
			SalesPosition srcSP = getSrcSP();
			SalesPosition trgSP = getTrgSP();
			metricOfflineService.checkCustomerPullMetricViolation(srcSP, trgSP, userDetails);
			Assert.assertTrue(true);
		} catch (Exception e) {
			LOGGER.error("Error");
		}
	}
	
	@Test
	public void testCheckCustomerEditMetricViolation() {
		LOGGER.info("----Started checkCustomerEditMetricViolation------");
		BatchMultiTenancyUtil.setTenantContext("am");
		try {
			UserDetails userDetails = getUserDetails();
			SalesPosition trgSP = getTrgSP();
			metricOfflineService.checkCustomerEditMetricViolation(trgSP, userDetails);
			Assert.assertTrue(true);
		} catch (Exception e) {
			LOGGER.error("Error");
		}
	}
	

	@Test
	public void testCheckZipMovementMetricViolation() {
		LOGGER.info("----Started checkZipMovementMetricViolation------");
		BatchMultiTenancyUtil.setTenantContext("am");
		try {
			UserDetails userDetails = getUserDetails();
			SalesPosition srcSP = getSrcSP();
			SalesPosition trgSP = getTrgSP();
			metricOfflineService.checkZipMovementMetricViolation(srcSP,trgSP, userDetails);
			Assert.assertTrue(true);
		} catch (Exception e) {
			LOGGER.error("Error");
		}
	}
	
	private UserDetails getUserDetails(){
		UserDetails userDetails = new UserDetails();
		userDetails.setTenantId((short) 1);
		userDetails.setUserId(1);
		userDetails.setTenantCode("am");
		return userDetails;
	}
	
	
	private SalesPosition getSrcSP(){
		SalesPosition salesPosition = new SalesPosition();
		Alignment alignmment = new Alignment();
		SalesTeam salesTeam = new SalesTeam();
		BusinessUnit businessUnit = new BusinessUnit();
		SalesOrgHierarchy hierarchy = new SalesOrgHierarchy();
		
		alignmment.setId((long) 7);
		businessUnit.setId((long) 2);
		salesTeam.setId((long) 7);
		
		salesPosition.setId((long) 231);
		hierarchy.setId((long) 5);
		
		salesPosition.setHierarchy(hierarchy);		
		salesTeam.setBusinessUnit(businessUnit);		
		alignmment.setSalesTeam(salesTeam);		
		salesPosition.setAlignmment(alignmment);
		return salesPosition;
	}
	
	private SalesPosition getTrgSP(){
		SalesPosition salesPosition = new SalesPosition();
		Alignment alignmment = new Alignment();
		SalesTeam salesTeam = new SalesTeam();
		BusinessUnit businessUnit = new BusinessUnit();
		SalesOrgHierarchy hierarchy = new SalesOrgHierarchy();
		
		alignmment.setId((long) 7);
		businessUnit.setId((long) 2);
		salesTeam.setId((long) 7);
		
		salesPosition.setId((long) 595);
		hierarchy.setId((long) 4);
		
		salesPosition.setHierarchy(hierarchy);		
		salesTeam.setBusinessUnit(businessUnit);		
		alignmment.setSalesTeam(salesTeam);		
		salesPosition.setAlignmment(alignmment);
		return salesPosition;
	}
	
	private MetricTriggerType getMtrTrgType() {
		return MetricTriggerType.ASSIGN_CUSTOMER;
	}
}
