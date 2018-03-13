package com.cognizant.opserv.sp.persistence.dao.service;

import java.util.List;

import com.cognizant.opserv.sp.model.Alignment;
import com.cognizant.opserv.sp.model.Customer;
import com.cognizant.opserv.sp.model.CustomerAffiliation;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.peg.core.exception.OpservDataAccessException;

/**
 * ****************************************************************************.
 *
 * @class CustomerAffiliationDAOService contains all the dao services for customer affiliation
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 * ***************************************************************************
 */
public interface CustomerAffiliationDAOService {
	
	/**
	 * @Method getCustomerAffiliation - Method to fetch all the customer affiliations
	 * @param customer
	 * @param userDetails
	 * @return List<CustomerAffiliation> - holds the list of customer affiliation 
	 * @throws OpservDataAccessException
	 */
	List<CustomerAffiliation> getCustomerAffiliation(Customer customer, UserDetails userDetails) throws OpservDataAccessException;
	
	/**
	 * @Method getCustomerAffiliationByAlignment - Method to fetch all customer affiliations by Alignment
	 * @param customer
	 * @param alignment
	 * @param userDetails
	 * @return List<CustomerAffiliation> - holds the list of customer affiliation 
	 * @throws OpservDataAccessException
	 */
	List<CustomerAffiliation> getCustomerAffiliationByAlignment(Customer customer, Alignment alignment, UserDetails userDetails) throws OpservDataAccessException;
	
	
	/**
	 * @Method getAccountCustomerAffiliations - To fetch all Account customer affiliations 
	 * @param customerId
	 * @param userDetails
	 * @return List<Customer> - list of customer
	 * @throws OpservDataAccessException
	 */ 
	List<CustomerAffiliation> getAccountCustomerAffiliations(Customer customer, UserDetails userDetails) throws OpservDataAccessException;
	
	/**
	 * @Method getAffiliatedAccountCustomers - Method to fetch Account Affiliations if a given customer is root 
	 * @param custAlgmt
	 * @param userDetails
	 * @return List<CustomerAffiliation> - list of customers affiliations 
	 * @throws OpservDataAccessException
	 */	
	List<CustomerAffiliation> getAffiliatedAccountCustomers(Customer customer, UserDetails userDetails) throws OpservDataAccessException;
	
}
