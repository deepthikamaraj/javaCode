package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TCust;
import com.cognizant.opserv.sp.core.entity.TCustSalesTeam;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TCustSalesTeam DAO.
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 29/04/2016
 */
public interface TCustSalesTeamDAO {
	
	

	/**
	 * Find all t custs by AL,BU, ST and custIds.
	 *
	 * @param alId the al id
	 * @param buId the bu id
	 * @param stId the st id
	 * @param custId the cust id
	 * @return the list
	 */
	List<TCustSalesTeam> findTCustSalesTeamByCustId(Long alId, Long buId, Long stId,
			List<Integer> custId,short tenantId);

	/**
	 * Updates a TCustSalesTeam entity object in to the persistent store
	 * 
	 * @param tCustSalesTeam
	 *            TCustSalesTeam Entity object to be updated
	 * @return tCustSalesTeam Persisted TCustSalesTeam object
	 */
	TCustSalesTeam updateTCustSalesTeam(TCustSalesTeam tCustSalesTeam);

	/**
	 * Retrieve an TCustSalesTeam object based on given custSalesTeamId.
	 * 
	 * @param custSalesTeamId
	 *            the primary key value of the TCustSalesTeam Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TCustSalesTeam findTCustSalesTeamById(Long custSalesTeamId);
	
	/**
	 * Retrieve TCustSalesTeam based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCustSalesTeam> list of TCustSalesTeams if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TCustSalesTeam> findTCustSalesTeams(SearchFilter<TCustSalesTeam> searchFilter);

	/**
	 * Count TCustSalesTeam based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTCustSalesTeams(SearchFilter<TCustSalesTeam> searchFilter);

	/**
	 * Retrieve TCustSalesTeam based on given search criteria using JPA named Query.
	 * The search criteria is of TCust type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCustSalesTeam> list of TCustSalesTeams if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TCustSalesTeam> getTCustSalesTeamsByTCust(SearchFilter<TCust> searchFilter);

	/**
	 * Count TCustSalesTeam based on given search criteria using JPA named Query.
	 * The search criteria is of TCust type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTCustSalesTeamsByTCust(SearchFilter<TCust> searchFilter);
}
