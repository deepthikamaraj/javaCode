package com.cognizant.opserv.sp.persistence.dao.service;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TSalesTeam;
import com.cognizant.opserv.sp.model.SalesTeam;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.peg.core.common.ISearchCriteria;
import com.cognizant.peg.core.exception.OpservDataAccessException;


// TODO: Auto-generated Javadoc
/**
 * The Interface SalesTeamSetupDAOService.
 */
public interface SalesTeamSetupDAOService {

	/**
	 * Creates the new sales team.
	 *
	 * @param salesTeam the sales team
	 * @param userDetails the user details
	 * @return the sales team
	 * @throws OpservDataAccessException the opserv data access exception
	 */
	SalesTeam createNewSalesTeam(SalesTeam salesTeam, UserDetails userDetails) throws OpservDataAccessException;
	
	/**
	 * Update sales team info.
	 *
	 * @param salesTeam the sales team
	 * @param userDetails the user details
	 * @return the t sales team
	 * @throws OpservDataAccessException the opserv data access exception
	 */
	TSalesTeam updateSalesTeamInfo(SalesTeam salesTeam, UserDetails userDetails) throws OpservDataAccessException;
	
	/**
	 * Soft deletion of sales team.
	 *
	 * @param salesTeamId the sales team id
	 * @param bussUnitId the buss unit id
	 * @param userDetails the user details
	 * @return the t sales team
	 * @throws OpservDataAccessException the opserv data access exception
	 */
	TSalesTeam softDeletionOfSalesTeam(Long salesTeamId, Long bussUnitId, UserDetails userDetails) throws OpservDataAccessException;
	
	/**
	 * Gets the all details of sales team.
	 *
	 * @param salesTeamId the sales team id
	 * @param bussUnitId the buss unit id
	 * @param userDetails the user details
	 * @return the all details of sales team
	 * @throws OpservDataAccessException the opserv data access exception
	 */
	SalesTeam getAllDetailsOfSalesTeam(Long salesTeamId,Long bussUnitId, UserDetails userDetails) throws OpservDataAccessException;
	
	/**
	 * Fetch sales teams by criteria.
	 *
	 * @param searchCriteria the search criteria
	 * @param userDetails the user details
	 * @return the list
	 * @throws OpservDataAccessException the opserv data access exception
	 */
	List<SalesTeam> fetchSalesTeamsByCriteria(ISearchCriteria searchCriteria, UserDetails userDetails) throws OpservDataAccessException;
	
	
}
