package com.cognizant.opserv.sp.service.impl.test.alignment;

import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cognizant.opserv.sp.common.util.JSONUtil;
import com.cognizant.opserv.sp.core.dao.TCustAffDAO;
import com.cognizant.opserv.sp.exception.AffiliationServiceException;
import com.cognizant.opserv.sp.model.Alignment;
import com.cognizant.opserv.sp.model.BusinessUnit;
import com.cognizant.opserv.sp.model.Customer;
import com.cognizant.opserv.sp.model.CustomerAffiliation;
import com.cognizant.opserv.sp.model.SalesTeam;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.service.alignment.CustomerAffiliationService;
import com.cognizant.opserv.sp.service.base.test.BaseTest;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;

public class CustomerAffiliationServiceImplTest extends BaseTest {
	
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(CustomerAffiliationServiceImplTest.class);
	
	@Autowired
	CustomerAffiliationService customerAffiliationService;
	
	@Autowired
	TCustAffDAO tCustAffDAO;
	
	@BeforeClass
	public static void setUp() {  
		System.setProperty("opserv.config.file", "c:/opserv/config/opserv-config.properties");
		System.setProperty("opserv.activeMQ.brokerURL", "tcp://localhost:61616");
	}
	
	private Alignment getAlignment(){
		BusinessUnit businessUnit= new BusinessUnit();
		businessUnit.setId((long) 2);
		
		SalesTeam salesTeam = new SalesTeam();
		salesTeam.setId((long) 7);
		salesTeam.setBusinessUnit(businessUnit);
		
		Alignment alignment = new Alignment();
		alignment.setId((long) 7);
		alignment.setSalesTeam(salesTeam);
		return alignment;
	}
	
	private Customer getCustomer(){
		Customer customer = new Customer();
		customer.setId(1973L);
		return customer;
	}
	
	@Test
	public void getCustomerAffiliationTest() {
		LOGGER.info("In getCustomerAffiliationTest");
		UserDetails userInfo = new UserDetails();
		userInfo.setTenantId((short)1);
		Customer customer = getCustomer();		
		List<CustomerAffiliation> affiliationList;
		try {
			affiliationList = customerAffiliationService.getCustomerAffiliation(customer, userInfo);
			if(null != affiliationList && !affiliationList.isEmpty()){
				LOGGER.info("custAff size===" + JSONUtil.toJSON(affiliationList));
				for(CustomerAffiliation custAff : affiliationList) {
					LOGGER.info("custAff size===" + JSONUtil.toJSON(custAff.getAffiliatedCustomers()));
				}			
			}		
			Assert.assertNotNull(affiliationList);	
		} catch (AffiliationServiceException e) {
			LOGGER.info("Error while fetching Affiliations", e);
		}						
	}
	
	@Test
	public void getCustomerAffiliationAlignmentTest() {
		LOGGER.info("In getCustomerAffiliationAlignmentTest");
		UserDetails userInfo = new UserDetails();
		userInfo.setTenantId((short)1);
		Customer customer = getCustomer();
		Alignment alignment = getAlignment();
		List<CustomerAffiliation> affiliationList;
		try {
			affiliationList = customerAffiliationService.getCustomerAffiliationByAlignment(customer, alignment, userInfo);
			if(null != affiliationList && !affiliationList.isEmpty()){
				LOGGER.info("custAff size===" + affiliationList.size());
				for(CustomerAffiliation custAff : affiliationList) {
					LOGGER.info("custAff size===" + JSONUtil.toJSON(custAff.getAffiliatedCustomers()));
				}			
			}		
			Assert.assertNotNull(affiliationList);
		} catch (AffiliationServiceException e) {
			LOGGER.info("Error while fetching Alignment Affiliations", e);
		}
							
	}
	
	@Test
	public void getAccountCustomerAffiliationsTest() {
		LOGGER.info("In getAccountCustomerAffiliationsTest");
		UserDetails userInfo = new UserDetails();
		userInfo.setTenantId((short)1);
		Customer customer = getCustomer();
		List<CustomerAffiliation> affiliationList;
		try {
			affiliationList = customerAffiliationService.getAccountCustomerAffiliations(customer, userInfo);
			if(null != affiliationList && !affiliationList.isEmpty()){	
				LOGGER.info("custAff size===" + JSONUtil.toJSON(affiliationList));
				for(CustomerAffiliation custAff : affiliationList) {
					LOGGER.info("custAff size===" + JSONUtil.toJSON(custAff.getAffiliatedCustomers()));
				}			
			}		
			Assert.assertNotNull(affiliationList);
		} catch (AffiliationServiceException e) {
			LOGGER.info("Error while fetching Account Affiliations", e);
		}							
	}	
}
	
