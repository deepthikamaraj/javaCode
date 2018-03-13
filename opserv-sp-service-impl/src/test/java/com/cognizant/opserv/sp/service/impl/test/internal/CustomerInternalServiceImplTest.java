package com.cognizant.opserv.sp.service.impl.test.internal;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cognizant.opserv.sp.common.util.DateUtil;
import com.cognizant.opserv.sp.exception.CustomerServiceException;
import com.cognizant.opserv.sp.model.Alignment;
import com.cognizant.opserv.sp.model.BusinessUnit;
import com.cognizant.opserv.sp.model.Customer;
import com.cognizant.opserv.sp.model.CustomerAlignment;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.model.SalesTeam;
import com.cognizant.opserv.sp.model.attrb.AttributeDefinition;
import com.cognizant.opserv.sp.model.attrb.ExtdAttribue;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.service.internal.CustomerAlignmentIntServiceImpl;
import com.cognizant.opserv.sp.service.internal.CustomerInternalService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext-servicetest.xml" })
public class CustomerInternalServiceImplTest{

		@Autowired
		CustomerInternalService customerInternalService;
		
		@Autowired
		CustomerAlignmentIntServiceImpl customerAlignmentIntServiceImpl;
		
		@BeforeClass
		public static void setUp() {
		   System.setProperty("opserv.activeMQ.brokerURL", "tcp://localhost:61616");
			System.setProperty("opserv.config.file", "c:/opserv/config/opserv-config.properties");
		}
		
	    public static UserDetails userDetails(){
			UserDetails details = new UserDetails();
			details.setTenantId((short)1);
			return details;
		}
	    public static Customer getCustomer(){
	    	Customer customer = new Customer(); 
	    	customer.setId(1097L);//Non-Government Hospital Without Dialysis Center
	    	List<ExtdAttribue> list = new ArrayList<ExtdAttribue>();  
	    	ExtdAttribue extdAttribue = new ExtdAttribue();
	    	AttributeDefinition attrDef=new AttributeDefinition();
	    	attrDef.setName("attr_5");
	    	extdAttribue.setDefinition(attrDef);
	    	extdAttribue.setName("ExtName");
	    	extdAttribue.setValue("Dialysis Center");
	    	list.add(extdAttribue);
	    	customer.setExtdAttributes(list);
	    	customer.setTenantId((short)1);
	    	customer.setCreatedBy(1);
	    	customer.setCreatedDate(DateUtil.getCurrentDate());
//	    	customer.setCategory(CustomerCategoryType.ACCOUNT);
//	    	customer.setCode("Code");
//	    	customer.setName("Aaa");
//	    	customer.setActive(true);
			return customer;
		}
	    public static CustomerAlignment getCustomerAlignment(){
	    	CustomerAlignment customerAlignment = new CustomerAlignment(); 
	    	Customer customer = new Customer(); 
	    	customer.setId(1097L);
	    	customerAlignment.setCustomer(customer);
	    	SalesPosition salesPosition = new SalesPosition();
			salesPosition.setId(613L);	
//			salesPosition.setCode("2ndEntryCode");
//			salesPosition.setName("2ndEntryINBU APEX SF NSDupdated");
			Alignment alignment = new Alignment();
			SalesTeam salesTeam = new SalesTeam();
			BusinessUnit businessUnit = new BusinessUnit();
			
			alignment.setId(7L);
			salesTeam.setId(7L);
			businessUnit.setId(2L);
			
			salesTeam.setBusinessUnit(businessUnit);
			alignment.setSalesTeam(salesTeam);
			salesPosition.setAlignmment(alignment);
			
	    	
	    	customerAlignment.setSalesPosition(salesPosition);
	    	List<ExtdAttribue> list = new ArrayList<ExtdAttribue>();  
	    	ExtdAttribue extdAttribue = new ExtdAttribue();
	    	AttributeDefinition attrDef=new AttributeDefinition();
	    	attrDef.setName("attr_5");
	    	extdAttribue.setDefinition(attrDef);
	    	extdAttribue.setName("ExtName");
	    	extdAttribue.setValue("Dialysis Center");
	    	
	    	ExtdAttribue extdAttribue2 = new ExtdAttribue();
	    	AttributeDefinition attrDef2=new AttributeDefinition();
	    	attrDef2.setName("attr_20");
	    	extdAttribue2.setDefinition(attrDef2);
	    	extdAttribue2.setName("ExtName");
	    	extdAttribue2.setValue("20000");
	    	
	    	list.add(extdAttribue);
	    	list.add(extdAttribue2);
	    	customerAlignment.setExtdAttributes(list);
	    	customerAlignment.setTenantId((short)1);
	    	customerAlignment.setCreatedBy(1);
	    	customerAlignment.setCreatedDate(DateUtil.getCurrentDate());
			return customerAlignment;
		}
	    
		@Test
		public void editCustomerExtAttrTest(){
			try {
				customerInternalService.editCustomerDetails(getCustomer(), userDetails());
			} catch (CustomerServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		/*@Test
		public void editCustomerAligmentExtAttrTest(){
			try {
				customerAlignmentIntServiceImpl.editCustomerAlignmentDetails(getCustomerAlignment(), userDetails());
			} catch (AlignmentServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/
}
