package com.cognizant.opserv.sp.persistence.dao.service;

import java.util.List;
import java.util.Map;

import com.cognizant.opserv.query.IQuery;
import com.cognizant.opserv.sp.common.EntityType;
import com.cognizant.opserv.sp.core.entity.TCust;
import com.cognizant.opserv.sp.model.Alignment;
import com.cognizant.opserv.sp.model.Customer;
import com.cognizant.opserv.sp.model.EntityTemplate;
import com.cognizant.opserv.sp.model.attrb.ExtdAttribue;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.peg.core.exception.OpservDataAccessException;

/**
 * ****************************************************************************.
 *
 * @class CustomerDAOService contains all the DAO customer services 
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 * ***************************************************************************
 */
public interface CustomerDAOService {
	
	/**
	 * Gets the customer details.
	 *
	 * @param customerId the customer id
	 * @param userDetails the user details
	 * @return the customer details
	 * @throws OpservDataAccessException the opserv data access exception
	 */
	Customer getCustomerDetails(Long customerId,UserDetails userDetails) throws OpservDataAccessException;
	
	/**
	 * Search customers by criteria.
	 *
	 * @param criteria the criteria
	 * @param userDetails the user details
	 * @return the list
	 * @throws OpservDataAccessException the opserv data access exception
	 */
	List<Customer> searchCustomers(IQuery query,UserDetails userDetails) throws OpservDataAccessException;
	
	/**
	 * Gets the ext attr details.
	 *
	 * @param entityType the entity type
	 * @param alignment the alignment
	 * @param userDetails the user details
	 * @param tCust the t cust
	 * @return the ext attr details
	 * @throws OpservDataAccessException the opserv data access exception
	 */
	List<ExtdAttribue> getExtAttrDetails(EntityTemplate entityTemplate, Alignment alignment,UserDetails userDetails,
			TCust tCust) throws OpservDataAccessException;
	
	/**
	 * Gets the customer count by criteria.
	 *
	 * @param criteria the criteria
	 * @param userDetails the user details
	 * @return the customer count by criteria
	 * @throws OpservDataAccessException the opserv data access exception
	 */
	Long getCustomerCount(IQuery query, UserDetails userDetails) throws OpservDataAccessException;
	
	/**
     * getExtAttrDetails - To get Extended Attribute for Customer
     * @param entityType
     * @param alignment
     * @param userDetails
     * @param custId
     * @return  Map<Integer, List<ExtdAttribue>>
     */
    Map<Integer, List<ExtdAttribue>> getExtAttrDetails(
			EntityType entityType, Alignment alignment,
			UserDetails userDetails, List<Integer> custId)throws OpservDataAccessException;

    
    /**
     * Edits the customer details.
     *
     * @param customer the customer
     * @param userDetails the user details
     * @throws OpservDataAccessException the opserv data access exception
     */
    void editCustomerDetails(Customer customer, UserDetails userDetails) throws OpservDataAccessException;
	/**
	 * Fetch cust names fr cust ids.
	 *
	 * @param custIdList the cust id list
	 * @param userDetails the user details
	 * @return the list
	 */
	List<String> fetchCustNameFrCustIds(List<Integer> custIdList,
			UserDetails userDetails)throws OpservDataAccessException;
	
	/**
	 * Gets the customer alignment ext attr details without templ.
	 *
	 * @param alignment the alignment
	 * @param userDetails the user details
	 * @param custId the cust id
	 * @return the customer alignment ext attr details without templ
	 */
	Map<Integer, List<ExtdAttribue>> getCustomerExtAttrDetailsWithoutTempl(List<Integer> custId,UserDetails userDetails) throws OpservDataAccessException;
	
	/**
	 * To Fetch Customer Details.
	 * @param custId
	 * @return Customer
	 */
	public Customer fetchCustDetails(Integer custId)throws OpservDataAccessException;
}
