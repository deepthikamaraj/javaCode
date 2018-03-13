package com.cognizant.opserv.sp.persistence.dao.service;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TSalesPos;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.peg.core.common.ISearchCriteria;
import com.cognizant.peg.core.exception.OpservDataAccessException;

// TODO: Auto-generated Javadoc
/**
 * The Interface SalesPositionSetupDAOService.
 */
public interface SalesPositionSetupDAOService {

	/**
	 * Creates the new sales position.
	 *
	 * @param salesPosition the sales position
	 * @param userDetails the user details
	 * @return the sales position
	 * @throws OpservDataAccessException the opserv data access exception
	 */
	SalesPosition createNewSalesPosition(SalesPosition salesPosition, UserDetails userDetails) throws OpservDataAccessException;
	
	/**
	 * Update sales position info.
	 *
	 * @param salesPosition the sales position
	 * @param userDetails the user details
	 * @return the t sales pos
	 * @throws OpservDataAccessException the opserv data access exception
	 */
	TSalesPos updateSalesPositionInfo(SalesPosition salesPosition, UserDetails userDetails) throws OpservDataAccessException;
	
	/**
	 * Gets the all details of sales position.
	 *
	 * @param salePositionId the sale position id
	 * @param userDetails the user details
	 * @return the all details of sales position
	 * @throws OpservDataAccessException the opserv data access exception
	 */
	SalesPosition getAllDetailsOfSalesPosition(Long salePositionId, UserDetails userDetails) throws OpservDataAccessException;
	
	/**
	 * Fetch sales positions by criteria.
	 *
	 * @param searchCriteria the search criteria
	 * @param userDetails the user details
	 * @return the list
	 * @throws OpservDataAccessException the opserv data access exception
	 */
	List<SalesPosition> fetchSalesPositionsByCriteria(ISearchCriteria searchCriteria, UserDetails userDetails) throws OpservDataAccessException;
	
	/**
	 * Soft deletion of sales position.
	 *
	 * @param salePositionId the sale position id
	 * @param userDetails the user details
	 * @return the t sales pos
	 * @throws OpservDataAccessException the opserv data access exception
	 */
	TSalesPos softDeletionOfSalesPosition(Long salePositionId, UserDetails userDetails) throws OpservDataAccessException;
	
}

