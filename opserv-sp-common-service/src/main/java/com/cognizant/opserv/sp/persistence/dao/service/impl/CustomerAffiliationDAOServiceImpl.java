package com.cognizant.opserv.sp.persistence.dao.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cognizant.opserv.sp.assembler.CustomerAffiliationModelAssembler;
import com.cognizant.opserv.sp.common.CommonConstants;
import com.cognizant.opserv.sp.core.dao.TCustAffDAO;
import com.cognizant.opserv.sp.core.dao.TCustDAO;
import com.cognizant.opserv.sp.core.entity.TCustAff;
import com.cognizant.opserv.sp.model.Alignment;
import com.cognizant.opserv.sp.model.Customer;
import com.cognizant.opserv.sp.model.CustomerAffiliation;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.persistence.dao.service.CustomerAffiliationDAOService;
import com.cognizant.peg.core.exception.OpservDataAccessException;
/**
 * ****************************************************************************.
 *
 * @class CustomerAffiliationDAOServiceImpl contains all the dao services for customer affiliation
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 * ***************************************************************************
 */
@Component
public class CustomerAffiliationDAOServiceImpl implements CustomerAffiliationDAOService {
	
	/*
	 *  TCustAffDAO has all the services related to TCustAff
	 */
	@Autowired
	private TCustAffDAO tCustAffDAO;
	
	/*
	 *  TCustDAO has all the services related to TCust
	 */
	@Autowired
	private TCustDAO tCustDAO;
	
	@Autowired
	private CustomerAffiliationModelAssembler customerAffiliationModelAssembler;
	
	/**
	 * @Method getCustomerAffiliation - Method to fetch all the customer affiliations
	 * @param customer
	 * @param userDetails
	 * @return List<CustomerAffiliation> - holds the list of customer affiliation 
	 * @throws OpservDataAccessException
	 */
	public List<CustomerAffiliation> getCustomerAffiliation(Customer customer, UserDetails userDetails) throws OpservDataAccessException{
		List<CustomerAffiliation> finalCustomerAffiliation = null;
		try{
			finalCustomerAffiliation = new ArrayList<CustomerAffiliation>();
			List<Integer> rootAffIds = tCustAffDAO.findRootAffIdsForCustId((int)customer.getId().longValue(), userDetails.getTenantId());
			if(null != rootAffIds && !rootAffIds.isEmpty()){
				for(Integer rootId : rootAffIds){	
					List<TCustAff> custList = tCustAffDAO.findTCustAffsForCustId(rootId, CommonConstants.ACTIVE_Y, userDetails.getTenantId(), new Date());
					if(null != custList && !custList.isEmpty()){
						finalCustomerAffiliation.add(customerAffiliationModelAssembler.populateHierarchy(custList));
					}						          
				}
			}			
		} catch (RuntimeException re) {
			throw new OpservDataAccessException("Error while fetching customer affiliations ", re);
		}
		return finalCustomerAffiliation;
	}		
			
	/**
	 * @Method getCustomerAffiliationByAlignment - Method to fetch all customer affiliations by Alignment
	 * @param customer
	 * @param alignment
	 * @param userDetails
	 * @return List<CustomerAffiliation> - holds the list of customer affiliation 
	 * @throws OpservDataAccessException
	 */
	public List<CustomerAffiliation> getCustomerAffiliationByAlignment(Customer customer, Alignment alignment, UserDetails userDetails) throws OpservDataAccessException{
		List<CustomerAffiliation> algCustAffiliationList = null;
		try{				
			algCustAffiliationList = new ArrayList<CustomerAffiliation>();
			Long alignmentId = alignment.getId();
			Long bussUnitId = alignment.getSalesTeam().getBusinessUnit().getId();
			Long salesTeamId = alignment.getSalesTeam().getId();
			List<Integer> rootAffIds = tCustAffDAO.findRootAffIdsForCustIdAndAlignment((int)customer.getId().longValue(), alignmentId, bussUnitId, salesTeamId, userDetails.getTenantId());
			if(null != rootAffIds && !rootAffIds.isEmpty()){
				for(Integer rootId : rootAffIds){	
					List<TCustAff> custList = tCustAffDAO.findTCustAffsForCustId(rootId, CommonConstants.ACTIVE_Y, userDetails.getTenantId(), new Date());
					if(null != custList && !custList.isEmpty()){
						algCustAffiliationList.add(customerAffiliationModelAssembler.populateHierarchy(custList));
					}
				}
			}
		} catch (RuntimeException re) {
			throw new OpservDataAccessException("Error while fetching alignment customer affiliations ", re);
		}		
		return algCustAffiliationList;
	}	
	
	/**
	 * @Method getAccountCustomerAffiliations - To fetch all account customer affiliations
	 * @param customerId
	 * @param userDetails
	 * @return List<Customer> - list of customer
	 * @throws OpservDataAccessException
	 */
	@Override
	public List<CustomerAffiliation> getAccountCustomerAffiliations(Customer customer, UserDetails userDetails) throws OpservDataAccessException {
		List<CustomerAffiliation> accAffiliationList = null;
		try{
			accAffiliationList = new ArrayList<CustomerAffiliation>();
			List<Integer> rootAffIds = tCustAffDAO.findAccountTCustAffsForTCust((int)customer.getId().longValue(), userDetails.getTenantId());
			if(null != rootAffIds && !rootAffIds.isEmpty()){
				for(Integer rootId : rootAffIds){					
					List<TCustAff> custList = tCustAffDAO.findTCustAffsForCustId(rootId, CommonConstants.ACTIVE_Y, userDetails.getTenantId(), new Date());
					if(null != custList && !custList.isEmpty()){
						accAffiliationList.add(customerAffiliationModelAssembler.populateAccountAffHierarchy(custList));
					}
				}
			} 
		} catch (RuntimeException re) {
			throw new OpservDataAccessException("Error while fetching account customer affiliations ", re);
		}
		return accAffiliationList;
	}
		
	/**
	 * @Method getAffiliatedAccountCustomers - Method to fetch Account Affiliations if a given customer is root 
	 * @param custAlgmt
	 * @param userDetails
	 * @return List<CustomerAffiliation> - list of customers affiliations 
	 * @throws OpservDataAccessException
	 */
	@Override
	public List<CustomerAffiliation> getAffiliatedAccountCustomers(Customer customer, UserDetails userDetails) throws OpservDataAccessException {
		List<CustomerAffiliation> accAffiliationList = null;
		try{						
			List<TCustAff> custList = tCustAffDAO.findAccountTCustAffsForCustId((int)customer.getId().longValue(), userDetails.getTenantId(), new Date());
			if(null != custList && !custList.isEmpty()){
				accAffiliationList = new ArrayList<CustomerAffiliation>();	
				accAffiliationList.add(customerAffiliationModelAssembler.populateAccountAffHierarchy(custList));
			}
		} catch (RuntimeException re) {
			throw new OpservDataAccessException("Error while fetching account customer affiliations ", re);
		}
		return accAffiliationList;
	}
} 