package com.cognizant.opserv.sp.service.customer;

import java.util.List;

import com.cognizant.opserv.sp.exception.CustomerServiceException;
import com.cognizant.opserv.sp.model.CustomerProduct;
import com.cognizant.opserv.sp.model.Product;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.model.auth.UserDetails;
/**
 * ****************************************************************************.
 *
 * @class CustomerProductServiceimpl contains all the services customer product
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 * ***************************************************************************
 */
public interface CustomerProductService {
	
	/**
	 * Gets the products of customer.
	 * 
	 * @method getProductsOfCustomer
	 * @param customerId the customer id
	 * @param userDetails the user details
	 * @return the products of customer
	 * @throws CustomerServiceException the customer service exception
	 */
	List<Product> getProductsByCustomerId(Long customerId,UserDetails userDetails) throws CustomerServiceException;
	
	/**
	 * Gets the products of customers.
	 *
	 * @method getProductsOfCustomers
	 * @param customerIds the customer ids
	 * @param userDetails the user details
	 * @return the products of customers
	 * @throws CustomerServiceException the customer service exception
	 */
	List<CustomerProduct> getCustomerProducts(List<Long> customerIds,UserDetails userDetails) throws CustomerServiceException;
		
	/**
	 * Gets the products of customers.
	 *
	 * @method getProductsOfCustomers
	 * @param salesPosition the sales position
	 * @param userDetails the user details
	 * @return the products of customers
	 * @throws CustomerServiceException the customer service exception
	 */
	List<CustomerProduct> getProductsOfCustomersbySalesPositionId(SalesPosition salesPosition,UserDetails userDetails) throws CustomerServiceException;
	
}
