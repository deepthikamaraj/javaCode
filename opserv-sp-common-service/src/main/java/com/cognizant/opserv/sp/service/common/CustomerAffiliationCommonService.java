package com.cognizant.opserv.sp.service.common;

import java.util.List;

import com.cognizant.opserv.sp.exception.AffiliationServiceException;
import com.cognizant.opserv.sp.model.CustomerAffiliation;
import com.cognizant.opserv.sp.model.CustomerAlignment;
import com.cognizant.opserv.sp.model.auth.UserDetails;

public interface CustomerAffiliationCommonService {
	
	/**
	 * @Method getAffiliatedAccountCustomers - Method to fetch Account Affiliations if a given customer is root 
	 * @param custAlgmt
	 * @param userDetails
	 * @return List<CustomerAffiliation> - list of customers affiliations 
	 * @throws AffiliationServiceException
	 */
	List<CustomerAffiliation> getAffiliatedAccountCustomers(CustomerAlignment custAlgmt, UserDetails userDetails) throws AffiliationServiceException;
}
