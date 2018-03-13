package com.cognizant.opserv.sp.service.impl.test.alignment;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cognizant.opserv.sp.common.util.DateUtil;
import com.cognizant.opserv.sp.exception.AffiliationServiceException;
import com.cognizant.opserv.sp.exception.AlignmentServiceException;
import com.cognizant.opserv.sp.exception.CallPlanServiceException;
import com.cognizant.opserv.sp.exception.ChangeRequestServiceException;
import com.cognizant.opserv.sp.exception.CustomerServiceException;
import com.cognizant.opserv.sp.exception.MetricViolationException;
import com.cognizant.opserv.sp.exception.SalesPositionServiceException;
import com.cognizant.opserv.sp.exception.ViewServiceException;
import com.cognizant.opserv.sp.model.Alignment;
import com.cognizant.opserv.sp.model.BusinessUnit;
import com.cognizant.opserv.sp.model.Customer;
import com.cognizant.opserv.sp.model.CustomerAlignment;
import com.cognizant.opserv.sp.model.CustomerCallPlan;
import com.cognizant.opserv.sp.model.SalesOrgHierarchy;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.model.SalesTeam;
import com.cognizant.opserv.sp.model.attrb.AttributeDefinition;
import com.cognizant.opserv.sp.model.attrb.ExtdAttribue;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.service.alignment.CustomerAlignmentService;
import com.cognizant.opserv.sp.service.base.test.BaseTest;
import com.cognizant.opserv.sp.service.internal.CustomerAlignmnetIntService;

public class CustomerAlignmentServiceTest extends BaseTest{
	
	@BeforeClass
	public static void setUp() {
		System.setProperty("opserv.activeMQ.brokerURL", "tcp://localhost:61616");
		System.setProperty("opserv.config.file","C:/OPSERV/config/opserv-config.properties");
	} 

	@Autowired
	private CustomerAlignmentService customerAlignmentService;
	
	@Autowired 
	private CustomerAlignmnetIntService customerAlignmentIntService;
	
	
	@Test
	public void getAllCustomerAlignmentsBySalesPositionTest() throws AlignmentServiceException, CustomerServiceException{
		
		SalesPosition sp = new SalesPosition();
		Alignment alignment = new Alignment();
		BusinessUnit businessUnit = new BusinessUnit();
		SalesTeam salesTeam = new SalesTeam();
		SalesOrgHierarchy salesOrgHierarchy = new SalesOrgHierarchy();
		UserDetails userDetails= new UserDetails();
		
		userDetails.setTenantId((short) 1);
		userDetails.setLocaleId("en_US");
		salesTeam.setId(1L);
		businessUnit.setId(1L);
		alignment.setId(7L);
		sp.setId((long) 3);
		salesOrgHierarchy.setId((long) 10);
		sp.setHierarchy(salesOrgHierarchy);
		alignment.setSalesTeam(salesTeam);
		salesTeam.setBusinessUnit(businessUnit);
		sp.setAlignmment(alignment);

		List<CustomerAlignment> allCustomerAlignments = customerAlignmentService.getAllCustomerAlignmentsBySalesPosition(sp,userDetails);
		
		
		Assert.assertNotNull(allCustomerAlignments);
	}

	@Test
	public void getCustAlignments() throws AlignmentServiceException{
		
		UserDetails userDetails= new UserDetails();
		userDetails.setTenantId((short) 1);
		userDetails.setLocaleId("en_US");
		
        List<CustomerAlignment> allCustomerAlignments = customerAlignmentService.getAllCustomerAlignments(9L,userDetails);
        

        Assert.assertNotNull(allCustomerAlignments);

	}
	

	@Test
	public void getCustomerAlignmentswithBuId() throws AlignmentServiceException{
		
		UserDetails userDetails= new UserDetails();
		userDetails.setTenantId((short) 1);
		userDetails.setLocaleId("en_US");
		
        List<CustomerAlignment> allCustomerAlignmentswithBuId = customerAlignmentService.getAllCustomerAlignments(3L,1L,userDetails);
        

        Assert.assertNotNull(allCustomerAlignmentswithBuId);

	}
	


@Test
       public void pushCustomertest() throws AlignmentServiceException, MetricViolationException, CustomerServiceException, AffiliationServiceException, SalesPositionServiceException, ChangeRequestServiceException, CallPlanServiceException, ViewServiceException{ 
  {
              SalesPosition srcsp = new SalesPosition();
              SalesPosition tarsp = new SalesPosition();
              Alignment alignment = new Alignment();
              BusinessUnit businessUnit = new BusinessUnit();
              SalesTeam salesTeam = new SalesTeam();
              SalesOrgHierarchy srcSalesOrgHierarchy = new SalesOrgHierarchy();
              SalesOrgHierarchy tarsalesOrgHierarchy = new SalesOrgHierarchy();
             
              CustomerAlignment orgCustAlgn = new CustomerAlignment();
              CustomerAlignment newCustAlgn = new CustomerAlignment();
              UserDetails userDetails = new UserDetails();
              
              userDetails.setTenantId((short) 1);
              userDetails.setStaffId(80);
              userDetails.setUserId(101);
              salesTeam.setId(6L);
              businessUnit.setId(3L);
              alignment.setId(10L);
              srcsp.setId((long) 408);
              srcSalesOrgHierarchy.setId((long) 20);
              srcsp.setHierarchy(srcSalesOrgHierarchy);
              alignment.setSalesTeam(salesTeam);
              salesTeam.setBusinessUnit(businessUnit);
              srcsp.setAlignmment(alignment);
              srcsp.setCreatedBy(4845);
              Customer customer = new Customer();
              customer.setId(3L);//CUSTID
              orgCustAlgn.setId(102L);
              orgCustAlgn.setCustomer(customer);
              orgCustAlgn.setSalesPosition(srcsp);
              
              //TARGET SP
              tarsp.setId((long) 357);//SP
              tarsalesOrgHierarchy.setId((long) 19);//SH
              tarsp.setHierarchy(tarsalesOrgHierarchy);//SH
              tarsp.setCreatedBy(101);
              alignment.setSalesTeam(salesTeam);//ST
              salesTeam.setBusinessUnit(businessUnit);//BU
              tarsp.setAlignmment(alignment);//AL
              tarsp.setStartDate(DateUtil.getCurrentDate());
              newCustAlgn.setCustomer(customer);
              newCustAlgn.setSalesPosition(tarsp);
              
              customerAlignmentService.pushCustomer(orgCustAlgn, newCustAlgn, null, userDetails);
              
       }
       
}
	@Test
	public void getCustomerCountForSalesPosition() throws AlignmentServiceException {
		

		SalesPosition sp = new SalesPosition();
		SalesOrgHierarchy salesOrgHierarchy = new SalesOrgHierarchy();
		Alignment  alignment = new Alignment();
		SalesTeam  salesTeam = new SalesTeam();
		BusinessUnit businessUnit = new BusinessUnit();
		salesOrgHierarchy.setId(5L);
		sp.setId(556L);
		sp.setHierarchy(salesOrgHierarchy);
		
		
		
		UserDetails userDts = new UserDetails();
		userDts.setTenantId(Short.parseShort("1"));
		long custCount = customerAlignmentIntService.getCustomerCountForSalesPosition(sp, userDts);
		System.out.println("++++++++++++++++++++++++++++Cust Count ++++" +custCount);
}
	
	@Test
	  public void editCustomer() throws AlignmentServiceException, MetricViolationException, CustomerServiceException, AffiliationServiceException, SalesPositionServiceException, ChangeRequestServiceException, CallPlanServiceException{
			UserDetails userDetails = new UserDetails();
			userDetails.setTenantId((short) 1);
			userDetails.setLocaleId("en_US");
			userDetails.setUserId(80);
	    	CustomerAlignment tarCustomerAlignment = new CustomerAlignment(); 
	    	CustomerAlignment srccustomerAlignment = new CustomerAlignment(); 
	    	
	    	
	    	Customer customer = new Customer(); 
	    	customer.setId(74292L);
	    	tarCustomerAlignment.setCustomer(customer);
	    	SalesPosition salesPosition = new SalesPosition();
			salesPosition.setId(250L);	
			SalesOrgHierarchy salesOrgHierarchy=new  SalesOrgHierarchy();
			salesOrgHierarchy.setId(5L);
			salesPosition.setHierarchy(salesOrgHierarchy);
//			salesPosition.setCode("2ndEntryCode");
//			salesPosition.setName("2ndEntryINBU APEX SF NSDupdated");
			Alignment alignment = new Alignment();
			SalesTeam salesTeam = new SalesTeam();
			BusinessUnit businessUnit = new BusinessUnit();
			CustomerCallPlan customerCallPlan = new CustomerCallPlan();
			
			alignment.setId(7L);
			salesTeam.setId(7L);
			businessUnit.setId(2L);
			
			salesTeam.setBusinessUnit(businessUnit);
			alignment.setSalesTeam(salesTeam);
			salesPosition.setAlignmment(alignment);
			
	    	
			tarCustomerAlignment.setSalesPosition(salesPosition);
	    	List<ExtdAttribue> list = new ArrayList<ExtdAttribue>();  
	    	ExtdAttribue extdAttribue = new ExtdAttribue();
	    	AttributeDefinition attrDef=new AttributeDefinition();
	    	attrDef.setName("attr_6");
	    	extdAttribue.setDefinition(attrDef);
	    	extdAttribue.setName("ExtName");
	    	extdAttribue.setValue("Dialysis Center");
	    	
	    	ExtdAttribue extdAttribue2 = new ExtdAttribue();
	    	AttributeDefinition attrDef2=new AttributeDefinition();
	    	attrDef2.setName("attr_2");
	    	extdAttribue2.setDefinition(attrDef2);
	    	extdAttribue2.setName("ExtName");
	    	extdAttribue2.setValue("20000");
	    	
	    	
	    	customerCallPlan.setId(11043L);
		//	customerCallPlan.setPlannedCalls(20);
		//	customerCallPlan.setCustomerAlgn(tarCustomerAlignment);
			tarCustomerAlignment.setCustomerCallPlan(customerCallPlan);
			
			tarCustomerAlignment.setId(49792L);
	    	
			srccustomerAlignment.setSalesPosition(salesPosition);
			
	    	list.add(extdAttribue);
	    	list.add(extdAttribue2);
	    	tarCustomerAlignment.setExtdAttributes(list);
	    	tarCustomerAlignment.setTenantId((short)1);
	    	tarCustomerAlignment.setCreatedBy(1);
	    	tarCustomerAlignment.setCreatedDate(DateUtil.getCurrentDate());
	    	 //long crId = customerAlignmentService.editCustomerOnline(srccustomerAlignment, tarCustomerAlignment, "EDIT", userDetails);
	    	 //System.out.println("++++++++++++++++++++++++++++Cust CRID ++++" +crId);
		}
	
}
