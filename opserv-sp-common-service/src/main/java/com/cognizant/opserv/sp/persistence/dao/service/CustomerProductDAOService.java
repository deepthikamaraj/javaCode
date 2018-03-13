package com.cognizant.opserv.sp.persistence.dao.service;

import java.util.List;

import com.cognizant.opserv.sp.model.CustomerProduct;
import com.cognizant.opserv.sp.model.Product;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.peg.core.exception.OpservDataAccessException;

/**
 * ****************************************************************************.
 *
 * @class CustomerProductDAOService contains all the DAO services customer product
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 * ***************************************************************************
 */
public interface CustomerProductDAOService {
	/**
	 * Gets the products of customer.
	 * @method getProductByCustomerId
	 * @param customerId the customer id
	 * @param userDetails the user details
	 * @return the products of customer
	 * @throws OpservDataAccessException the opserv data access exception
	 */
	List<Product> getProductByCustomerId(Long CustomerId, UserDetails userDetails) throws OpservDataAccessException;
	/**
	 * Gets the customerproducts of customer.
	 * @method getCustomerProductByCustomerId
	 * @param customerIdList the customer id list
	 * @param userDetails the user details
	 * @return the customerproducts of customer
	 * @throws OpservDataAccessException the opserv data access exception
	 */
	List<CustomerProduct> getCustomerProductByCustomerId(List<Long> customerIdList, UserDetails userDetails) throws OpservDataAccessException;
	/**
	 * Gets the customerproducts of customer.
	 * @method getCustomerProductBySalesPosition
	 * @param salesPosition the salesPosition
	 * @param userDetails the user details
	 * @return the customerproducts of customer
	 * @throws OpservDataAccessException the opserv data access exception
	 */
	List<CustomerProduct> getCustomerProductBySalesPosition(SalesPosition salesPosition , UserDetails userDetails) throws OpservDataAccessException;
}
