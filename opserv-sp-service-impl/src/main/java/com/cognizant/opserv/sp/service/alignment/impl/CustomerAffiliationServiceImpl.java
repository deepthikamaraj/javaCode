package com.cognizant.opserv.sp.service.alignment.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.opserv.sp.exception.AffiliationServiceException;
import com.cognizant.opserv.sp.model.Alignment;
import com.cognizant.opserv.sp.model.Customer;
import com.cognizant.opserv.sp.model.CustomerAffiliation;
import com.cognizant.opserv.sp.model.CustomerAlignment;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.persistence.dao.service.CustomerAffiliationDAOService;
import com.cognizant.opserv.sp.service.alignment.CustomerAffiliationService;
import com.cognizant.peg.core.exception.OpservDataAccessException;
/**
 * ****************************************************************************.
 *
 * @class CustomerAffiliationServiceImpl contains all the services for customer affiliation
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 * ***************************************************************************
 */
@Service("customerAffiliationService")
@Transactional(rollbackFor = AffiliationServiceException.class)
public class CustomerAffiliationServiceImpl implements CustomerAffiliationService{
	
	/** The customer affiliation dao service. */
	@Autowired
	private CustomerAffiliationDAOService customerAffiliationDAOService;
	
	/**
	 * @Method getCustomerAffiliation - Method to fetch all the customer affiliations
	 * @param customer
	 * @param userDetails
	 * @return CustomerAffiliation - holds the details of customer affiliation 
	 * @throws AffiliationServiceException
	 */
	@Override
	public List<CustomerAffiliation> getCustomerAffiliation(Customer customer, UserDetails userDetails) throws AffiliationServiceException {
		try{
			if(null != customer && null != customer.getId() && customer.getId() != 0 
					&& null != userDetails && null != userDetails.getTenantId()){
				return customerAffiliationDAOService.getCustomerAffiliation(customer, userDetails);
			}else{
                Object[] args  ={"customerId"};
                throw new AffiliationServiceException(args);
			}
		} catch(OpservDataAccessException e) {
			Object[] args = new Object[]{customer.getId()};
		    throw new AffiliationServiceException(AffiliationServiceException.AffiliationServiceExceptionCode.AFF_SER_EX_CD_001,"Exception in getCustomerAffiliation",args,e);
		}
	}
	
	
	/**
	 * @Method getCustomerAffiliationByAlignment - Method to fetch all customer affiliations by Alignment
	 * @param customer
	 * @param alignment
	 * @param userDetails
	 * @return CustomerAffiliation - holds the details of customer affiliation 
	 * @throws AffiliationServiceException
	 */
	@Override
	public List<CustomerAffiliation> getCustomerAffiliationByAlignment(
			Customer customer, Alignment alignment, UserDetails userDetails)
			throws AffiliationServiceException {
		try {
			if (null != customer
					&& null != customer.getId()
					&& customer.getId() != 0
					&& null != alignment
					&& null != alignment.getId() && alignment.getId() != 0
					&& null != alignment.getSalesTeam()	&& null != alignment.getSalesTeam().getId()	&& alignment.getSalesTeam().getId() != 0
					&& null != alignment.getSalesTeam().getBusinessUnit()
					&& null != alignment.getSalesTeam().getBusinessUnit().getId()
					&& alignment.getSalesTeam().getBusinessUnit().getId() != 0
					&& null != userDetails && null != userDetails.getTenantId()) {
				return customerAffiliationDAOService.getCustomerAffiliationByAlignment(customer, alignment,	userDetails);
			} else {
				Object[] args = { "customerId", "AlgmntId", "BuId", "StId" };
				throw new AffiliationServiceException(args);
			}
		} catch (OpservDataAccessException e) {
			Object[] args = new Object[] { customer.getId(), alignment.getId() };
			throw new AffiliationServiceException(
					AffiliationServiceException.AffiliationServiceExceptionCode.AFF_SER_EX_CD_002,
					"Exception in getCustomerAffiliationByAlignment", args, e);
		}
	}

	/**
	 * @Method getAccountCustomerAffiliations - Method to fetch account customer hierarchy 
	 * @param customer
	 * @param userDetails
	 * @return List<Customer> - list of customers  
	 * @throws AffiliationServiceException
	 */
	@Override
	public List<CustomerAffiliation> getAccountCustomerAffiliations(Customer customer, UserDetails userDetails) throws AffiliationServiceException {
		try {
			if(null != customer && null != customer.getId() && customer.getId() != 0 
					&& null != userDetails && null != userDetails.getTenantId()){
				return customerAffiliationDAOService.getAccountCustomerAffiliations(customer, userDetails);			
			} else {
                Object[] args  ={"customerId"};
                throw new AffiliationServiceException(args);
			}			
		} catch(OpservDataAccessException e) {
			Object[] args = new Object[]{customer.getId()};
		    throw new AffiliationServiceException(AffiliationServiceException.AffiliationServiceExceptionCode.AFF_SER_EX_CD_003,"Exception in getAccountCustomerAffiliations",args,e);
		}
	}
	
	/**
	 * @Method getAffiliatedAccountCustomers - Method to fetch Account Affiliations if a given customer is root 
	 * @param custAlgmt
	 * @param userDetails
	 * @return List<CustomerAffiliation> - list of customers affiliations 
	 * @throws AffiliationServiceException
	 */
	@Override
	public List<CustomerAffiliation> getAffiliatedAccountCustomers(CustomerAlignment custAlgmt, UserDetails userDetails) throws AffiliationServiceException{
		try {
			if(null != custAlgmt && null != custAlgmt.getCustomer()	&& null != userDetails) {
				return customerAffiliationDAOService.getAffiliatedAccountCustomers(custAlgmt.getCustomer(), userDetails);			
			} else {
                Object[] args  ={"cust Algmt"};
                throw new AffiliationServiceException(args);
			}			
		} catch(OpservDataAccessException e) {
			Object[] args = new Object[]{custAlgmt.getCustomer()};
		    throw new AffiliationServiceException(AffiliationServiceException.AffiliationServiceExceptionCode.AFF_SER_EX_CD_003,"Exception in getAffiliatedAccountCustomers",args,e);
		}
	}
}
