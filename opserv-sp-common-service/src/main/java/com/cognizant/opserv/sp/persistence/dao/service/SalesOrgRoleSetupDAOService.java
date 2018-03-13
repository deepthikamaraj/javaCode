package com.cognizant.opserv.sp.persistence.dao.service;

import java.util.List;

import com.cognizant.opserv.sp.model.SalesOrgRole;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.peg.core.common.ISearchCriteria;
import com.cognizant.peg.core.exception.OpservDataAccessException;

/**
 * ****************************************************************************.
 *
 * @class SalesOrgRoleSetupDAOService contains all the DAO  sales org role services 
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 25/05/2016
 * ***************************************************************************
 */
public interface SalesOrgRoleSetupDAOService {
	
	/**
	 * Creates the sales org role.
	 *
	 * @param salesOrgRole the sales org role
	 * @param userDetails the user details
	 * @return the sales org role
	 * @throws OpservDataAccessException the opserv data access exception
	 */
	SalesOrgRole createSalesOrgRole(SalesOrgRole salesOrgRole, UserDetails userDetails) throws OpservDataAccessException;
	
	/**
	 * Update sales org role.
	 *
	 * @param salesOrgRole the sales org role
	 * @param userDetails the user details
	 * @throws OpservDataAccessException the opserv data access exception
	 */
	void updateSalesOrgRole(SalesOrgRole salesOrgRole, UserDetails userDetails) throws OpservDataAccessException;
	
	/**
	 * Inactive sales org role.
	 *
	 * @param salesOrgRoleId the sales org role id
	 * @param userDetails the user details
	 * @throws OpservDataAccessException the opserv data access exception
	 */
	void inactiveSalesOrgRole(Long salesOrgRoleId, UserDetails userDetails) throws OpservDataAccessException;
	
	/**
	 * Gets the sales org role details.
	 *
	 * @param salesOrgRoleId the sales org role id
	 * @param userDetails the user details
	 * @return the sales org role details
	 * @throws OpservDataAccessException the opserv data access exception
	 */
	SalesOrgRole getSalesOrgRoleDetails(Long salesOrgRoleId, UserDetails userDetails) throws OpservDataAccessException;
	
	/**
	 * Fetch sales org roles.
	 *
	 * @param searchCriteria the search criteria
	 * @param userDetails the user details
	 * @return the list
	 * @throws OpservDataAccessException the opserv data access exception
	 */
	List<SalesOrgRole> fetchSalesOrgRoles(ISearchCriteria searchCriteria, UserDetails userDetails) throws OpservDataAccessException;
	

}