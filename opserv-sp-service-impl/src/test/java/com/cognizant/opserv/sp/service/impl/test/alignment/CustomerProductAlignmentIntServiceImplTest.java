package com.cognizant.opserv.sp.service.impl.test.alignment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cognizant.opserv.sp.exception.AlignmentServiceException;
import com.cognizant.opserv.sp.model.Customer;
import com.cognizant.opserv.sp.model.CustomerProductAlignment;
import com.cognizant.opserv.sp.model.Product;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.service.base.test.BaseTest;
import com.cognizant.opserv.sp.service.internal.CustomerProductAlignmentIntService;

public class CustomerProductAlignmentIntServiceImplTest extends BaseTest{

	
	@Autowired
	private CustomerProductAlignmentIntService customerProductAlignIntService;
	
	
	@Test
	public void insertCustomerProductAligmn() throws AlignmentServiceException {
		UserDetails userDetails = new UserDetails();
		List<CustomerProductAlignment> custProdAlignmList = new ArrayList<CustomerProductAlignment>();
		CustomerProductAlignment custPrdAlign = new  CustomerProductAlignment();
		Customer cust = new Customer();
		cust.setId(1123L); //for insert
		//cust.setId(1180l);
		Product prod = new Product();
		prod.setId(267L);
		custPrdAlign.setCustomer(cust);
		custPrdAlign.setProduct(prod);
		SalesPosition salesPosition = new SalesPosition();
		salesPosition.setId(174L);
		custPrdAlign.setSalesPosition(salesPosition);
		custPrdAlign.setActive(true);
		custPrdAlign.setTenantId((short)1);
		custPrdAlign.setCreatedBy(1);
		Date date = new Date();
		custPrdAlign.setCreatedDate(date);
		custPrdAlign.setAllocationPercentage(60);
		
		custProdAlignmList.add(custPrdAlign);
		
		List<CustomerProductAlignment> custPrdAlgmntResultList = customerProductAlignIntService.insertCustomerProductAlignment(custProdAlignmList, userDetails);
		
		//List<CustomerProductAlignment> custPrdAlgmntResultList = customerProductAlignIntService.updateAllocPerc(custProdAlignmList, userDetails);
		
		Assert.assertNotNull(custPrdAlgmntResultList);
		
}

}



