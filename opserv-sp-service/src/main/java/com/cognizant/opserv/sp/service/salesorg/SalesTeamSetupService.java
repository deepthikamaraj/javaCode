package com.cognizant.opserv.sp.service.salesorg;

import java.util.List;

import com.cognizant.opserv.sp.exception.SalesTeamServiceException;
import com.cognizant.opserv.sp.model.SalesTeam;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.peg.core.common.ISearchCriteria;


/**
 * The Interface SalesTeamSetupService.
 */
public interface SalesTeamSetupService {


	/**
	 * Creates the sales team.
	 *
	 * @param salesTeam the sales team
	 * @param userDetails the user details
	 * @return the sales team
	 * @throws SalesTeamServiceException the sales team service exception
	 */
	SalesTeam createSalesTeam(SalesTeam salesTeam,UserDetails userDetails) throws SalesTeamServiceException;
	
	/**
	 * Update sales team.
	 *
	 * @param salesTeam the sales team
	 * @param userDetails the user details
	 * @throws SalesTeamServiceException the sales team service exception
	 */
	void updateSalesTeam(SalesTeam salesTeam,UserDetails userDetails) throws SalesTeamServiceException;
	
	/**
	 * Inactive sales team.
	 *
	 * @param salesTeamId the sales team id
	 * @param userDetails the user details
	 * @throws SalesTeamServiceException the sales team service exception
	 */
	void inactiveSalesTeam(Long salesTeamId, Long bussUnitId, UserDetails userDetails) throws SalesTeamServiceException;
	

	/**
	 * Gets the sales team details.
	 *
	 * @param salesTeamId the sales team id
	 * @param bussUnitId the buss unit id
	 * @param userDetails the user details
	 * @return the sales team details
	 * @throws SalesTeamServiceException the sales team service exception
	 */
	SalesTeam getSalesTeamDetails(Long salesTeamId,Long bussUnitId,UserDetails userDetails) throws SalesTeamServiceException;
	
	/**
	 * Fetch sales teams.
	 *
	 * @param searchCriteria the search criteria
	 * @param userDetails the user details
	 * @return the list
	 * @throws SalesTeamServiceException the sales team service exception
	 */
	List<SalesTeam> fetchSalesTeamsBySearchCriteria(ISearchCriteria searchCriteria,UserDetails userDetails) throws SalesTeamServiceException;	

}
