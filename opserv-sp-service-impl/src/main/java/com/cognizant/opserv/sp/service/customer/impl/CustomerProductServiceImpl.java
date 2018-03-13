package com.cognizant.opserv.sp.service.customer.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.opserv.sp.exception.CustomerServiceException;
import com.cognizant.opserv.sp.exception.CustomerServiceException.CustomerServiceExceptionCode;
import com.cognizant.opserv.sp.model.CustomerProduct;
import com.cognizant.opserv.sp.model.Product;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.persistence.dao.service.CustomerProductDAOService;
import com.cognizant.opserv.sp.service.customer.CustomerProductService;
import com.cognizant.peg.core.exception.OpservDataAccessException;
/**
 * ****************************************************************************.
 *
 * @class CustomerProductServiceimpl contains all the services customer product
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 * ***************************************************************************
 */

@Service("customerProductService")
public class CustomerProductServiceImpl implements CustomerProductService {
	

@Autowired
private CustomerProductDAOService customerProdDaoService;
	/**
	 * Gets the products of customer.
	 * @method getProductsOfCustomer
	 * @param customerId the customer id
	 * @param userDetails the user details
	 * @return the products of customer
	 * @throws CustomerServiceException the customer service exception
	 */
	@Override
	public List<Product> getProductsByCustomerId(Long customerId,
			UserDetails userDetails) throws CustomerServiceException {
		// TODO Auto-generated method stub
		
		try{
		if(customerId == null || userDetails.getTenantId() == null){
			throw new CustomerServiceException(new Object[] {"customerId","userDetails"});
		}
		return customerProdDaoService.getProductByCustomerId(customerId, userDetails);
		}
		catch(OpservDataAccessException e)
		{
			e.printStackTrace();
		  throw new CustomerServiceException(CustomerServiceExceptionCode.CUST_SER_EX_CD_001, "Error while fetching products", null, e);
			
		}

		
	}
	/**
	 * Gets the products of customers.
	 * @method getProductsOfCustomers
	 * @param customerIds the customer ids
	 * @param userDetails the user details
	 * @return the products of customers
	 * @throws CustomerServiceException the customer service exception
	 */
	@Override
	@Transactional
	public List<CustomerProduct> getCustomerProducts(List<Long> customerIds,
			UserDetails userDetails) throws CustomerServiceException {
		// TODO Auto-generated method stub
		try{
		//List<CustomerProduct> customerProduct = new ArrayList<CustomerProduct>();
			if(customerIds == null || userDetails.getTenantId() == null){
				throw new CustomerServiceException(new Object[] {"customerIds","userDetails"});
			}
		return customerProdDaoService.getCustomerProductByCustomerId(customerIds, userDetails);
		}
		catch(OpservDataAccessException e)
		{
		  throw new CustomerServiceException(CustomerServiceExceptionCode.CUST_SER_EX_CD_001, "Error while fetching customersproducts", null, e);
			
		}
	
	}

	/**
	 * Gets the products of customers.
	 * @method getProductsOfCustomers
	 * @param salesPosition the sales position
	 * @param userDetails the user details
	 * @return the products of customers
	 * @throws CustomerServiceException the customer service exception
	 */
	@Override
	@Transactional
	public List<CustomerProduct> getProductsOfCustomersbySalesPositionId(
			SalesPosition salesPosition, UserDetails userDetails)
			throws CustomerServiceException {
		// TODO Auto-generated method stub
		
		/*try{
			if(salesPosition.getId() == null || userDetails.getTenantId() == null){
				throw new CustomerServiceException(new Object[] {"salesPosition","userDetails"});	
			}
			return customerProdDaoService.getCustomerProductBySalesPosition(salesPosition, userDetails);
		}
		catch(OpservDataAccessException e)
		{
			e.printStackTrace();
			throw new CustomerServiceException(CustomerServiceExceptionCode.CUST_SER_EX_CD_003, "Error while fetching customerproducts", null, e);
		}*/
		try{
			throw new OpservDataAccessException("Not implemented");
		}
		catch(OpservDataAccessException e) {
			throw new CustomerServiceException(CustomerServiceExceptionCode.CUST_SER_EX_CD_007, "Not yet implemented", null,e);
		}
		

		
	}
	

}
