package com.cognizant.opserv.sp.service.common.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.opserv.sp.exception.AffiliationServiceException;
import com.cognizant.opserv.sp.model.CustomerAffiliation;
import com.cognizant.opserv.sp.model.CustomerAlignment;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.persistence.dao.service.CustomerAffiliationDAOService;
import com.cognizant.opserv.sp.service.common.CustomerAffiliationCommonService;
import com.cognizant.peg.core.exception.OpservDataAccessException;

@Service
public class CustomerAffiliationCommonServiceImpl implements
		CustomerAffiliationCommonService {
	
	/** The customer affiliation dao service. */
	@Autowired
	private CustomerAffiliationDAOService customerAffiliationDAOService;

	/**
	 * @Method getAffiliatedAccountCustomers - Method to fetch Account Affiliations if a given customer is root 
	 * @param custAlgmt
	 * @param userDetails
	 * @return List<CustomerAffiliation> - list of customers affiliations 
	 * @throws AffiliationServiceException
	 */
	@Override
	public List<CustomerAffiliation> getAffiliatedAccountCustomers(CustomerAlignment custAlgmt, UserDetails userDetails)  throws AffiliationServiceException{
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
