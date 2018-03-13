package com.cognizant.opserv.sp.cr.process.internal.service;

import java.util.List;

import com.cognizant.opserv.sp.exception.AlignmentServiceException;
import com.cognizant.opserv.sp.model.Customer;
import com.cognizant.opserv.sp.model.CustomerAlignment;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.model.auth.UserDetails;


public interface CustomerAlignmentOfflineService {

	/**
	 * Get all the valid affiliated customers in a given sales position.
	 * @param customer
	 * @param salesposition
	 * @param userDetails
	 * @return
	 * @throws AlignmentServiceException 
	 */
	List<CustomerAlignment> getValidAffiliatedCustomerAlignments(List<Customer> affCustomers, SalesPosition salesposition, UserDetails userDetails) throws AlignmentServiceException;
	
	void unLockCustomerAlignmentsOnApprove(List<CustomerAlignment> customerAlgmnts, UserDetails userDetails);
	
	void unLockCustomerAlignmentsOnReject(List<CustomerAlignment> customerAlgmnts, UserDetails userDetails);
	
	
}
