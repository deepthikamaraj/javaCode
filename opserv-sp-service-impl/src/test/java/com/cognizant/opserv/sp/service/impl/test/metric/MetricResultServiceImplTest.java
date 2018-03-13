package com.cognizant.opserv.sp.service.impl.test.metric;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cognizant.opserv.sp.common.MetricTriggerType;
import com.cognizant.opserv.sp.exception.AlignmentServiceException;
import com.cognizant.opserv.sp.exception.MetricServiceException;
import com.cognizant.opserv.sp.model.Alignment;
import com.cognizant.opserv.sp.model.BusinessUnit;
import com.cognizant.opserv.sp.model.PostalCode;
import com.cognizant.opserv.sp.model.SalesOrgHierarchy;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.model.SalesTeam;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.model.metric.MetricExecutionTrigger;
import com.cognizant.opserv.sp.model.metric.MetricResult;
import com.cognizant.opserv.sp.service.base.test.BaseTest;
import com.cognizant.opserv.sp.service.metric.MetricResultService;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;

public class MetricResultServiceImplTest extends BaseTest {

	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(MetricResultServiceImplTest.class);
	
	@Autowired
	private MetricResultService metricResultService;
	
	@BeforeClass
	public static void setUp() {
		System.setProperty("opserv.activeMQ.brokerURL", "tcp://localhost:61616");
		System.setProperty("opserv.config.file","C:/opserv/config/opserv-config.properties");
	}
	
	@Test
	public void testGetAllMetricResults() {
		LOGGER.info("----Started testGetAllMetricResults------");
		SalesPosition sp = getSalesPosition1();
		//SalesPosition sp = getSalesPosition2();
		UserDetails userDetails = getUserDetails();
		try {
			List<MetricResult> list = metricResultService.getAllMetricResults(sp, userDetails);
			assertNotNull(list);
			if(list != null && !list.isEmpty()){
				for(MetricResult mtrRes: list){
					LOGGER.info("=========Start=====");
					LOGGER.info("Object Number = " + list.indexOf(mtrRes));
					LOGGER.info(" Metric Id   = " +mtrRes.getMetric().getId());
					LOGGER.info(" Metric Name = " +mtrRes.getMetric().getName());
					LOGGER.info(" Min Value   = " +mtrRes.getMetric().getMinValue());
					LOGGER.info(" Max Value   = " +mtrRes.getMetric().getMaxValue());
					LOGGER.info(" Value       = " +mtrRes.getBaseValue());
					LOGGER.info(" Value       = " +mtrRes.getActualValue());
					LOGGER.info(" Value       = " +mtrRes.getProposedValue());
					LOGGER.info("=========End =====");
				}
			}
		} catch (MetricServiceException e) {
			LOGGER.error("Error");
		} catch (AlignmentServiceException e) {
			LOGGER.error("Error");
		}
	}
	
	@Test
	public void testGetAllMetricResultsWithTypes() {
		LOGGER.info("----Started testGetAllMetricResultsWithTypes------");
		SalesPosition sp = getSalesPosition1();
	//	SalesPosition sp = getSalesPosition2();
		
		List<MetricTriggerType> types = getTypes();
		UserDetails userDetails = getUserDetails();
		try {
			List<MetricResult> list = metricResultService.getAllMetricResults(sp,types, userDetails);
			assertNotNull(list);
			if(list != null && !list.isEmpty()){
				for(MetricResult mtrRes: list){
					LOGGER.info("=========Start=====");
					LOGGER.info("Object Number = " + list.indexOf(mtrRes));
					LOGGER.info(" Metric Id   = " +mtrRes.getMetric().getId());
					LOGGER.info(" Metric Name = " +mtrRes.getMetric().getName());
					LOGGER.info(" Min Value   = " +mtrRes.getMetric().getMinValue());
					LOGGER.info(" Max Value   = " +mtrRes.getMetric().getMaxValue());
					LOGGER.info(" Value       = " +mtrRes.getBaseValue());
					LOGGER.info(" Value       = " +mtrRes.getActualValue());
					LOGGER.info(" Value       = " +mtrRes.getProposedValue());
					LOGGER.info("=========End =====");
				}
			}
		} catch (MetricServiceException e) {
			LOGGER.error("Error");
		} catch (AlignmentServiceException e) {
			LOGGER.error("Error");
		}
	}
	
	@Test
	public void testGetAllMetricResultsWithSingleTypes() {
		LOGGER.info("----Started testGetAllMetricResultsWithSingleTypes------");
		SalesPosition sp = getSalesPosition1();
		//SalesPosition sp = getSalesPosition2();
		
		MetricTriggerType type = getType();
		UserDetails userDetails = getUserDetails();
		try {
			List<MetricResult> list = metricResultService.getAllMetricResults(sp,type,userDetails);
			assertNotNull(list);
			if(list != null && !list.isEmpty()){
				for(MetricResult mtrRes: list){
					LOGGER.info("=========Start=====");
					LOGGER.info("Object Number = " + list.indexOf(mtrRes));
					LOGGER.info(" Metric Id   = " +mtrRes.getMetric().getId());
					LOGGER.info(" Metric Name = " +mtrRes.getMetric().getName());
					LOGGER.info(" Min Value   = " +mtrRes.getMetric().getMinValue());
					LOGGER.info(" Max Value   = " +mtrRes.getMetric().getMaxValue());
					LOGGER.info(" Value       = " +mtrRes.getBaseValue());
					LOGGER.info(" Value       = " +mtrRes.getActualValue());
					LOGGER.info(" Value       = " +mtrRes.getProposedValue());
					LOGGER.info("=========End =====");
				}
			}
		} catch (MetricServiceException e) {
			LOGGER.error("Error");
		} catch (AlignmentServiceException e) {
			LOGGER.error("Error");
		}
	}
	
	@Test
	public void getMetricResultsBySalesPositionsTest() throws MetricServiceException{
		List<SalesPosition> salesPositions = new ArrayList<SalesPosition>();
		SalesPosition salesPosition1 = new SalesPosition();
		salesPosition1.setId(386l);
		salesPositions.add(salesPosition1);
		SalesPosition salesPosition2 = new SalesPosition();
		salesPosition2.setId(397l);
		salesPositions.add(salesPosition2);
		SalesPosition salesPosition3 = new SalesPosition();
		salesPosition3.setId(394l);
		salesPositions.add(salesPosition3);
		UserDetails userDetails = new UserDetails();
		userDetails.setTenantId((short) 1);
		List<MetricResult> metricResultList = metricResultService.getMetricResultsBySalesPositions(12l, salesPositions, userDetails);
		Assert.assertNotNull(metricResultList);
	}
	
	@Test
	public void getMetricResultsByPostalCodesTest() throws MetricServiceException{
		List<PostalCode> postalCodes = getPostalCodes();
		UserDetails userDetails = new UserDetails();
		userDetails.setTenantId((short) 1);
		Map<PostalCode, MetricResult> metricResults = metricResultService.getMetricResultsByPostalCodes(12l, postalCodes, userDetails);
		Assert.assertNotNull(metricResults);
	}

	
	@Test
	public void getAllMetricResultsByPostalCodes() throws MetricServiceException{
		try{
			List<PostalCode> postalCodes = getPostalCodes();
			UserDetails userDetails = getUserDetails();
			Map<PostalCode, List<MetricResult>> metricResults = metricResultService.getAllMetricResultsByPostalCodes(postalCodes, userDetails);
			Assert.assertNotNull(metricResults);
			if(metricResults != null && !metricResults.isEmpty()){
				for(Entry<PostalCode, List<MetricResult>> entry : metricResults.entrySet()){
					PostalCode pstCd = entry.getKey();
					LOGGER.info( " PostalCode === " + pstCd.getCode());
					 List<MetricResult> list =  entry.getValue();
					 if(list != null && !list.isEmpty()){
						 for(MetricResult mtrRes: list){
								LOGGER.info("=========Start=====");
								LOGGER.info("Object Number = " + list.indexOf(mtrRes));
								LOGGER.info(" Metric Id   = " +mtrRes.getMetric().getId());
								LOGGER.info(" Metric Name = " +mtrRes.getMetric().getName());
								LOGGER.info(" Min Value   = " +mtrRes.getMetric().getMinValue());
								LOGGER.info(" Max Value   = " +mtrRes.getMetric().getMaxValue());
								LOGGER.info(" Base Value       = " +mtrRes.getBaseValue());
								LOGGER.info(" Actual Value       = " +mtrRes.getActualValue());
								LOGGER.info(" Proposed Value       = " +mtrRes.getProposedValue());
								LOGGER.info("=========End =====");
							}
					 }
				}
			}
		} catch (MetricServiceException e) {
			LOGGER.error("Error");
		}
	}
	
	@Test
	public void getAllMetricResultsByPostalCodesAndAlmgnt() throws MetricServiceException{
		try{
			Long algmntId = 7l;
			List<PostalCode> postalCodes = getPostalCodes();
			UserDetails userDetails = getUserDetails();
			Map<PostalCode, List<MetricResult>> metricResults = metricResultService.getAllMetricResultsByPostalCodes(postalCodes,algmntId,userDetails);
			Assert.assertNotNull(metricResults);
			if(metricResults != null && !metricResults.isEmpty()){
				for(Entry<PostalCode, List<MetricResult>> entry : metricResults.entrySet()){
					PostalCode pstCd = entry.getKey();
					LOGGER.info( " PostalCode === " + pstCd.getCode());
					 List<MetricResult> list =  entry.getValue();
					 if(list != null && !list.isEmpty()){
						 for(MetricResult mtrRes: list){
								LOGGER.info("=========Start=====");
								LOGGER.info("Object Number = " + list.indexOf(mtrRes));
								LOGGER.info(" Metric Id   = " +mtrRes.getMetric().getId());
								LOGGER.info(" Metric Name = " +mtrRes.getMetric().getName());
								LOGGER.info(" Min Value   = " +mtrRes.getMetric().getMinValue());
								LOGGER.info(" Max Value   = " +mtrRes.getMetric().getMaxValue());
								LOGGER.info(" Base Value       = " +mtrRes.getBaseValue());
								LOGGER.info(" Actual Value       = " +mtrRes.getActualValue());
								LOGGER.info(" Proposed Value       = " +mtrRes.getProposedValue());
								LOGGER.info("=========End =====");
							}
					 }
				}
			}
		} catch (MetricServiceException e) {
			LOGGER.error("Error");
		}
	}
	
	
	@Test
	public void getMetricExecutionConfigTest() throws MetricServiceException{
		try{
			Alignment alignment = getSalesPosition1().getAlignmment();
			UserDetails userDetails = getUserDetails();
			
			List<MetricExecutionTrigger> list = metricResultService.getMetricExecutionConfig(alignment, userDetails);
			Assert.assertNotNull(list);
			 if(list != null && !list.isEmpty()){
				 for(MetricExecutionTrigger mtrRes: list){
						LOGGER.info("=========Start=====");
						LOGGER.info(" Metric Id   = " +mtrRes.getMetricConfig().getMetric().getId());
						LOGGER.info(" Metric Name = " +mtrRes.getMetricConfig().getMetric().getName());
						LOGGER.info(" Min Value   = " +mtrRes.getMetricConfig().getMetric().getMinValue());
						LOGGER.info(" Max Value   = " +mtrRes.getMetricConfig().getMetric().getMaxValue());
						LOGGER.info(" Trigger Type   = " +mtrRes.getTriggerType());
						LOGGER.info(" Hierarchy Id   = " +mtrRes.getMetricConfig().getHierarchy());
						LOGGER.info("=========End =====");
					}
			 }
		} catch (MetricServiceException e) {
			LOGGER.error("Error");
		}
	}
	
	private List<PostalCode> getPostalCodes(){
		List<PostalCode> postalCodes = new ArrayList<PostalCode>();
		PostalCode postalCode1 = new PostalCode();
		postalCode1.setCode("39475");
		postalCodes.add(postalCode1);
		PostalCode postalCode4 = new PostalCode();
		postalCode4.setCode("39476");
		postalCodes.add(postalCode4);
		return postalCodes;
	}
	
	private MetricTriggerType getType() {
		//return MetricTriggerType.CUSTOMER_DELETE;
		return MetricTriggerType.ASSIGN_CUSTOMER;
	}
	
	private List<MetricTriggerType> getTypes() {
		List<MetricTriggerType> types = new ArrayList<MetricTriggerType>();
		MetricTriggerType type1 = MetricTriggerType.ASSIGN_CUSTOMER;
		MetricTriggerType type2 = MetricTriggerType.UNASSIGN_CUSTOMER;
		
		types.add(type1);
		types.add(type2);
		return types;
	}


	private SalesPosition getSalesPosition1(){
		SalesPosition salesPosition = new SalesPosition();
		Alignment alignmment = new Alignment();
		SalesTeam salesTeam = new SalesTeam();
		BusinessUnit businessUnit = new BusinessUnit();
		SalesOrgHierarchy hierarchy = new SalesOrgHierarchy();
		
		/*	ALSO TESTED WITH BELOW DATA
		 * AL - 10 BU - 3 ST - 6 SP -357 SH-19 metrics - 7,8,9
		 * AL - 9 BU - 3 ST - 4 SP -459 SH-16  metrics - 10,11,12
		*/
		
		alignmment.setId((long) 1);
		businessUnit.setId((long) 1);
		salesTeam.setId((long) 2);
		
		salesPosition.setId((long) 255);
		hierarchy.setId((long) 4);
		
		salesPosition.setHierarchy(hierarchy);		
		salesTeam.setBusinessUnit(businessUnit);		
		alignmment.setSalesTeam(salesTeam);		
		salesPosition.setAlignmment(alignmment);
		return salesPosition;
	}
	
	/*private SalesPosition getSalesPosition2(){
		SalesPosition salesPosition = new SalesPosition();
		Alignment alignmment = new Alignment();
		SalesTeam salesTeam = new SalesTeam();
		BusinessUnit businessUnit = new BusinessUnit();
		SalesOrgHierarchy hierarchy = new SalesOrgHierarchy();
		
		alignmment.setId((long) 7);
		businessUnit.setId((long) 1);
		salesTeam.setId((long) 1);
		
		salesPosition.setId((long) 2);
		hierarchy.setId((long) 9);
		
		salesPosition.setHierarchy(hierarchy);		
		salesTeam.setBusinessUnit(businessUnit);		
		alignmment.setSalesTeam(salesTeam);		
		salesPosition.setAlignmment(alignmment);
		return salesPosition;
	}*/
	
	private UserDetails getUserDetails(){
		UserDetails userDetails = new UserDetails();
		userDetails.setTenantId((short) 1);
		return userDetails;
	}

}
