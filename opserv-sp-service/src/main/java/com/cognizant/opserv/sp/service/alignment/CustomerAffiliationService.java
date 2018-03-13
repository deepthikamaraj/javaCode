package com.cognizant.opserv.sp.service.alignment;

import java.util.List;

import com.cognizant.opserv.sp.exception.AffiliationServiceException;
import com.cognizant.opserv.sp.model.Alignment;
import com.cognizant.opserv.sp.model.Customer;
import com.cognizant.opserv.sp.model.CustomerAffiliation;
import com.cognizant.opserv.sp.model.CustomerAlignment;
import com.cognizant.opserv.sp.model.auth.UserDetails;
/**
 * ****************************************************************************.
 *
 * @class CustomerAffiliationService contains all the services for customer affiliation
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 * ***************************************************************************
 */
public interface CustomerAffiliationService {	
	
	/**
	 * @Method getCustomerAffiliation - Method to fetch all the customer affiliations
	 * @param customer
	 * @param userDetails
	 * @return CustomerAffiliation - holds the details of customer affiliation 
	 * @throws AffiliationServiceException
	 */
	List<CustomerAffiliation> getCustomerAffiliation(Customer customer, UserDetails userDetails) throws AffiliationServiceException;
	
	
	/**
	 * @Method getCustomerAffiliationByAlignment - Method to fetch all customer affiliations by Alignment
	 * @param customer
	 * @param alignment
	 * @param userDetails
	 * @return CustomerAffiliation - holds the details of customer affiliation 
	 * @throws AffiliationServiceException
	 */
	List<CustomerAffiliation> getCustomerAffiliationByAlignment(Customer customer, Alignment alignment, UserDetails userDetails) throws AffiliationServiceException;
			
	
	/**
	 * @Method getAccountCustomerAffiliations - Method to fetch the account customer affiliation. 
	 * @param customer
	 * @param userDetails
	 * @return CustomerAffiliation - holds the details of customer affiliation  
	 * @throws AffiliationServiceException
	 */
	List<CustomerAffiliation> getAccountCustomerAffiliations(Customer customer, UserDetails userDetails) throws AffiliationServiceException;
	
	/**
	 * @Method getAffiliatedAccountCustomers - Method to fetch Account Affiliations if a given customer is root 
	 * @param custAlgmt
	 * @param userDetails
	 * @return List<CustomerAffiliation> - list of customers affiliations 
	 * @throws AffiliationServiceException
	 */	
	List<CustomerAffiliation> getAffiliatedAccountCustomers(CustomerAlignment custAlgmt, UserDetails userDetails)  throws AffiliationServiceException;

}
