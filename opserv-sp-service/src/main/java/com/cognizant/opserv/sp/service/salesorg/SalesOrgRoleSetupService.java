package com.cognizant.opserv.sp.service.salesorg;

import java.util.List;

import com.cognizant.opserv.sp.exception.SalesOrgServiceException;
import com.cognizant.opserv.sp.model.SalesOrgRole;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.peg.core.common.ISearchCriteria;

/**
 * The Interface SalesOrgRoleSetupService.
 */
public interface SalesOrgRoleSetupService {

	/**
	 * Creates the sales org role.
	 *
	 * @param salesOrgRole the sales org role
	 * @param userDetails the user details
	 * @return the sales org role
	 * @throws SalesOrgServiceException the sales org service exception
	 */
	SalesOrgRole createSalesOrgRole(SalesOrgRole salesOrgRole, UserDetails userDetails) throws SalesOrgServiceException;
	
	/**
	 * Update sales org role.
	 *
	 * @param salesOrgRole the sales org role
	 * @param userDetails the user details
	 * @throws SalesOrgServiceException the sales org service exception
	 */
	void updateSalesOrgRole(SalesOrgRole salesOrgRole, UserDetails userDetails) throws SalesOrgServiceException;
	
	/**
	 * Inactive sales org role.
	 *
	 * @param salesOrgRoleId the sales org role id
	 * @param userDetails the user details
	 * @throws SalesOrgServiceException the sales org service exception
	 */
	void inactiveSalesOrgRole(Long salesOrgRoleId, UserDetails userDetails) throws SalesOrgServiceException;
	
	/**
	 * Gets the sales org role details.
	 *
	 * @param salesOrgRoleId the sales org role id
	 * @param userDetails the user details
	 * @return the sales org role details
	 * @throws SalesOrgServiceException the sales org service exception
	 */
	SalesOrgRole getSalesOrgRoleDetails(Long salesOrgRoleId, UserDetails userDetails) throws SalesOrgServiceException;
	
	/**
	 * Fetch sales org roles.
	 *
	 * @param searchCriteria the search criteria
	 * @param userDetails the user details
	 * @return the list
	 * @throws SalesOrgServiceException the sales org service exception
	 */
	List<SalesOrgRole> fetchSalesOrgRoles(ISearchCriteria searchCriteria, UserDetails userDetails) throws SalesOrgServiceException;	

}