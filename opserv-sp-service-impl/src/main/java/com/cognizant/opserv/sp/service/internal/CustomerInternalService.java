package com.cognizant.opserv.sp.service.internal;

import com.cognizant.opserv.sp.exception.CustomerServiceException;
import com.cognizant.opserv.sp.model.Customer;
import com.cognizant.opserv.sp.model.auth.UserDetails;

/**
 * The Interface CustomerInternalService.
 */
public interface CustomerInternalService {

	/**
	 * Edits the customer details.
	 *
	 * @param customer the customer
	 * @param userDetails the user details
	 * @throws CustomerServiceException the customer service exception
	 */
	void editCustomerDetails(Customer customer, UserDetails userDetails) throws CustomerServiceException;
	
}
