package com.cognizant.opserv.sp.service.customer;

import java.util.List;
import java.util.Map;

import com.cognizant.opserv.query.IQuery;
import com.cognizant.opserv.sp.common.EntityType;
import com.cognizant.opserv.sp.exception.AlignmentServiceException;
import com.cognizant.opserv.sp.exception.CustomerServiceException;
import com.cognizant.opserv.sp.model.Alignment;
import com.cognizant.opserv.sp.model.Customer;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.model.attrb.ExtdAttribue;
import com.cognizant.opserv.sp.model.auth.UserDetails;
/**
 * ****************************************************************************.
 *
 * @class CustomerService contains all the customer services 
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 * ***************************************************************************
 */
public interface CustomerService {

	/**
	 * Gets the customer details.
	 * @method getCustomerDetails
	 * @param customerId the customer id
	 * @param userDetails the user details
	 * @return the customer details
	 * @throws CustomerServiceException the customer service exception
	 */
	Customer getCustomerDetails(long customerId,UserDetails userDetails) throws CustomerServiceException;
	
	
	/**
	 * Search Customer 
	 * @param criteria
	 * @param userDetails
	 * @return
	 * @throws CustomerServiceException
	 */
	List<Customer> searchCustomers(IQuery query,UserDetails userDetails) throws CustomerServiceException;
	
	/**
	 * Gets the customer count .
	 * @method getCustomerCountByCriteria
	 * @param criteria the criteria
	 * @param userDetails the user details
	 * @return the customer count by criteria
	 * @throws CustomerServiceException the customer service exception
	 */
	Long getCustomerCount(IQuery query, UserDetails userDetails) throws CustomerServiceException;
	

	/**
     * getExtAttrDetails - To get Extended Attribute for Customer
     * @param entityType
     * @param alignment
     * @param userDetails
     * @param custId
     * @return  Map<Integer, List<ExtdAttribue>>
	 * @throws AlignmentServiceException 
     */
	Map<Integer, List<ExtdAttribue>> getExtAttrDetails(
			EntityType entityType, Alignment alignment,
			UserDetails userDetails, List<Integer> custId) throws CustomerServiceException;
	
	
	List<Customer> fetchRecentlyAddedCust(SalesPosition salesPosition, UserDetails userDetails);
	
	List<Customer> fetchSearchedCust(SalesPosition salesPosition, UserDetails userDetails, String custName);
	List<Customer> fetchCustomerList(Customer customer, UserDetails userDetails);

	Long fetchCountOfCustForSPandChild(List<SalesPosition> salesPositionList,
			UserDetails userDetails);

}
