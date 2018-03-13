package com.cognizant.opserv.sp.persistence.dao.service;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TBussUnit;
import com.cognizant.opserv.sp.model.BusinessUnit;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.peg.core.common.ISearchCriteria;
import com.cognizant.peg.core.exception.OpservDataAccessException;

// TODO: Auto-generated Javadoc
/**
 * The Interface BusinessUnitSetupDAOService.
 */
public interface BusinessUnitSetupDAOService {

	/**
	 * Creates the new business unit.
	 *
	 * @param businessUnit the business unit
	 * @param userDetails the user details
	 * @return the business unit
	 * @throws OpservDataAccessException the opserv data access exception
	 */
	BusinessUnit createNewBusinessUnit(BusinessUnit businessUnit, UserDetails userDetails) throws OpservDataAccessException;
	
	/**
	 * Update business unit info.
	 *
	 * @param businessUnit the business unit
	 * @param userDetails the user details
	 * @return the t buss unit
	 * @throws OpservDataAccessException the opserv data access exception
	 */
	TBussUnit updateBusinessUnitInfo(BusinessUnit businessUnit, UserDetails userDetails) throws OpservDataAccessException;
	
	/**
	 * Gets the all details of business unit.
	 *
	 * @param businessUnitId the business unit id
	 * @param userDetails the user details
	 * @return the all details of business unit
	 * @throws OpservDataAccessException the opserv data access exception
	 */
	BusinessUnit getAllDetailsOfBusinessUnit(Long businessUnitId, UserDetails userDetails) throws OpservDataAccessException;
	
	/**
	 * Fetch business units by criteria.
	 *
	 * @param searchCriteria the search criteria
	 * @param userDetails the user details
	 * @return the list
	 * @throws OpservDataAccessException the opserv data access exception
	 */
	List<BusinessUnit> fetchBusinessUnitsByCriteria(ISearchCriteria searchCriteria, UserDetails userDetails) throws OpservDataAccessException;
	
	/**
	 * Soft deletion of business unit.
	 *
	 * @param businessUnitId the business unit id
	 * @param userDetails the user details
	 * @return the t buss unit
	 * @throws OpservDataAccessException the opserv data access exception
	 */
	TBussUnit softDeletionOfBusinessUnit(Long businessUnitId, UserDetails userDetails) throws OpservDataAccessException;
	
}
