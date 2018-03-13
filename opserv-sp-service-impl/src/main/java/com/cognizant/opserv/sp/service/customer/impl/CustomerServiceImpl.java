package com.cognizant.opserv.sp.service.customer.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.opserv.query.IQuery;
import com.cognizant.opserv.sp.common.EntityType;
import com.cognizant.opserv.sp.exception.AlignmentServiceException;
import com.cognizant.opserv.sp.exception.CustomerServiceException;
import com.cognizant.opserv.sp.exception.CustomerServiceException.CustomerServiceExceptionCode;
import com.cognizant.opserv.sp.model.Alignment;
import com.cognizant.opserv.sp.model.Customer;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.model.attrb.ExtdAttribue;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.persistence.dao.service.CustomerDAOService;
import com.cognizant.opserv.sp.service.customer.CustomerService;
import com.cognizant.peg.core.exception.OpservDataAccessException;

/**
 * ****************************************************************************.
 *
 * @class CustomerServiceimpl contains all the customer services 
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 * ***************************************************************************
 */
@Service("customerService")
public class CustomerServiceImpl implements CustomerService {
	
	/** The customer dao service. */
	@Autowired
	private CustomerDAOService customerDAOService;
	
	/**
	 * Gets the customer details.
	 * @method getCustomerDetails
	 * @param customerId the customer id
	 * @param userDetails the user details
	 * @return Customer
	 * @throws CustomerServiceException the customer service exception
	 * @method getCustomerDetails
	 */
	@Override
	@Transactional
	public Customer getCustomerDetails(long customerId, UserDetails userDetails)
			throws CustomerServiceException {
		try{
			return customerDAOService.getCustomerDetails(customerId, userDetails);
		}
		catch(OpservDataAccessException e){
			throw new CustomerServiceException(CustomerServiceExceptionCode.CUST_SER_EX_CD_001, 
					"exception while fetching customer details", null, e);
		}
	}
	
	/**
     * getExtAttrDetails - To get Extended Attribute for Customer
     * @param entityType
     * @param alignment
     * @param userDetails
     * @param custId
     * @return  Map<Integer, List<ExtdAttribue>>
	 * @throws AlignmentServiceException 
     */
	public Map<Integer, List<ExtdAttribue>> getExtAttrDetails(EntityType entityType, Alignment alignment, UserDetails userDetails, List<Integer> custId)
			throws CustomerServiceException {
		try {
			return customerDAOService.getExtAttrDetails(entityType, alignment, userDetails, custId);
		} catch (OpservDataAccessException e) {
			Object[] args = new Object[] { custId };
			throw new CustomerServiceException(CustomerServiceExceptionCode.CUST_SER_EX_CD_005, "Error while fetching Customer Extended Attribute by custId",
					args, e);
		}
	}
	
	/**
	 * Search Customer 
	 * @param criteria
	 * @param userDetails
	 * @return
	 * @throws CustomerServiceException
	 */
	@Override
	@Transactional
	public List<Customer> searchCustomers(IQuery query, UserDetails userDetails) throws CustomerServiceException {
		try{
			if(query == null){
				throw new CustomerServiceException(new Object[] {"query"});
			}
			return customerDAOService.searchCustomers(query, userDetails);
		}
		catch(OpservDataAccessException e){
			throw new CustomerServiceException(CustomerServiceExceptionCode.CUST_SER_EX_CD_002, 
					"exception while fetching customer details", null, e);
		}
	}

	@Override
	public Long getCustomerCount(IQuery query, UserDetails userDetails) throws CustomerServiceException {
		try{
			if(query == null){
				throw new CustomerServiceException(new Object[] {"query"});
			}
			return customerDAOService.getCustomerCount(query, userDetails);
		}
		catch(OpservDataAccessException e){
			throw new CustomerServiceException(CustomerServiceExceptionCode.CUST_SER_EX_CD_004, 
					"exception while fetching customer count", null, e);
		}
	}

	@Override
	public List<Customer> fetchRecentlyAddedCust(SalesPosition salesPosition,
			UserDetails userDetails) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Customer> fetchSearchedCust(SalesPosition salesPosition,
			UserDetails userDetails, String custName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Customer> fetchCustomerList(Customer customer,
			UserDetails userDetails) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long fetchCountOfCustForSPandChild(
			List<SalesPosition> salesPositionList, UserDetails userDetails) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
