package com.cognizant.opserv.sp.service.impl.test.cutomerProduct;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.cognizant.opserv.sp.exception.CustomerServiceException;
import com.cognizant.opserv.sp.model.CustomerProduct;
import com.cognizant.opserv.sp.model.Product;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.service.base.test.BaseTest;
import com.cognizant.opserv.sp.service.customer.CustomerProductService;

public class CustomerProductServiceTest extends BaseTest{
	
	@Autowired
	private CustomerProductService customerProductService;
	
	@Test
	public void getProductsOfCustomerTest() throws CustomerServiceException{
		
		UserDetails userDetails = new UserDetails();
		userDetails.setTenantId((short) 1);
		List<Product> productList = customerProductService.getProductsByCustomerId(1001L, userDetails);
		Assert.notNull(productList);
	}

	@Test
	public void getProductsOfCustomersTest() throws CustomerServiceException{
		
		List<Long> custIdList = new ArrayList<Long>();
		custIdList.add(1001L);
		custIdList.add(1002L);
		UserDetails userDetails = new UserDetails();
		userDetails.setTenantId((short) 1);
		List<CustomerProduct> custProductList = customerProductService.getCustomerProducts(custIdList, userDetails);
		Assert.notNull(custProductList);
	}
	

	@Test
	public void getProductsOfCustomersBySalesPosTest() throws CustomerServiceException{ 
		SalesPosition salesPos = new SalesPosition();
		salesPos.setId(3L);
		UserDetails userDetails = new UserDetails();
		userDetails.setTenantId((short) 1);
		List<CustomerProduct> productList = customerProductService.getProductsOfCustomersbySalesPositionId(salesPos, userDetails);
		Assert.notNull(productList);
	}
}
