package com.cognizant.opserv.sp.service.common;

import java.util.List;

import com.cognizant.opserv.sp.exception.AlignmentServiceException;
import com.cognizant.opserv.sp.exception.ViewServiceException;
import com.cognizant.opserv.sp.model.Customer;
import com.cognizant.opserv.sp.model.CustomerAlignment;
import com.cognizant.opserv.sp.model.auth.UserDetails;

public interface CustomerAlignmentCommonService {

	boolean lockCustomerAlignment(CustomerAlignment customerAlgmnt, UserDetails userDetails) throws AlignmentServiceException, ViewServiceException;
	
	public Boolean isCustomerAffiliated(Customer customer, UserDetails userDetails);
	
	boolean unLockCustomerAlignment(CustomerAlignment customerAlgmnt, UserDetails userDetails) throws AlignmentServiceException, ViewServiceException;
	
	boolean unLockCustomerAlignments(List<CustomerAlignment> customerAlgmnt, UserDetails userDetails) throws AlignmentServiceException, ViewServiceException;
}
