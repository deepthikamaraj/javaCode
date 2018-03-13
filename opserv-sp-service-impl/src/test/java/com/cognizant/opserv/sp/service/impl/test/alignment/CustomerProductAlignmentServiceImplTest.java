package com.cognizant.opserv.sp.service.impl.test.alignment;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cognizant.opserv.sp.exception.AlignmentServiceException;
import com.cognizant.opserv.sp.model.Customer;
import com.cognizant.opserv.sp.model.CustomerProductAlignment;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.service.alignment.CustomerProductAlignmentService;
import com.cognizant.opserv.sp.service.base.test.BaseTest;

public class CustomerProductAlignmentServiceImplTest extends BaseTest {
	
	@Autowired
	CustomerProductAlignmentService customerProductAlignmentService;
	
	
	@Test
	public void getCustPrdAlign() throws AlignmentServiceException {
		UserDetails userDts = new UserDetails();
		userDts.setTenantId(Short.parseShort("1"));
		Customer c = new Customer();
		c.setId(1l);
		SalesPosition sp = new SalesPosition();
		sp.setId(153l);
		List<CustomerProductAlignment> list = customerProductAlignmentService.getAllCustomerProducts(c,sp,userDts);
		if(null!=list && list.size()>0){
			for(CustomerProductAlignment cpAlg:list){
				System.out.println("############################ ProductName:"+cpAlg.getProduct().getName());
				System.out.println("############################ CustomerName:" +cpAlg.getCustomer().getName());
				
			}
			Assert.assertNotNull(list);
		}
}
		
	
	@Test
	public void getCustPrdAlignusingSP() throws AlignmentServiceException {
		UserDetails userDts = new UserDetails();
		userDts.setTenantId(Short.parseShort("1"));
		SalesPosition sp = new SalesPosition();
		sp.setId(153l);
		List<CustomerProductAlignment> list = customerProductAlignmentService.getAllCustomerProducts(sp,userDts);
	
		if(null!=list && list.size()>0){
			for(CustomerProductAlignment cpAlg:list){
				System.out.println("############################ ProductName:"+cpAlg.getProduct().getName());
				System.out.println("############################ CustomerName:" +cpAlg.getCustomer().getName());
				
			}
		
		}
		
		Assert.assertNotNull(list);
}
		
	
}
