package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TBussUnit;
import com.cognizant.opserv.sp.core.entity.TSalesTeam;
import com.cognizant.opserv.sp.core.entity.TSalesTeamId;
import com.cognizant.peg.core.common.ISearchCriteria;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TSalesTeam DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TSalesTeamDAO {

	/**
	 * Stores a new TSalesTeam entity object in to the persistent store
	 * 
	 * @param tSalesTeam
	 *            TSalesTeam Entity object to be persisted
	 * @return tSalesTeam Persisted TSalesTeam object
	 */
	TSalesTeam createTSalesTeam(TSalesTeam tSalesTeam);

	/**
	 * Deletes a TSalesTeam entity object from the persistent store
	 * 
	 * @param tSalesTeam
	 *            TSalesTeam Entity object to be deleted
	 */
	void deleteTSalesTeam(TSalesTeamId tSalesTeamId);

	/**
	 * Updates a TSalesTeam entity object in to the persistent store
	 * 
	 * @param tSalesTeam
	 *            TSalesTeam Entity object to be updated
	 * @return tSalesTeam Persisted TSalesTeam object
	 */
	TSalesTeam updateTSalesTeam(TSalesTeam tSalesTeam);

	/**
	 * Retrieve an TSalesTeam object based on given TSalesTeamId.
	 * 
	 * @param tSalesTeamId
	 *            the primary key value of the TSalesTeam Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TSalesTeam findTSalesTeamById(TSalesTeamId tSalesTeamId);

	/**
	 * Retrieve TSalesTeam based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TSalesTeam> list of TSalesTeams if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TSalesTeam> findTSalesTeams(SearchFilter<TSalesTeam> searchFilter);

	/**
	 * Count TSalesTeam based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTSalesTeams(SearchFilter<TSalesTeam> searchFilter);

	/**
	 * Retrieve TSalesTeam based on given search criteria using JPA named Query.
	 * The search criteria is of TBussUnit type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TSalesTeam> list of TSalesTeams if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TSalesTeam> getTSalesTeamsByTBussUnit(SearchFilter<TBussUnit> searchFilter);

	/**
	 * Count TSalesTeam based on given search criteria using JPA named Query.
	 * The search criteria is of TBussUnit type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTSalesTeamsByTBussUnit(SearchFilter<TBussUnit> searchFilter);
	
	Object maxTSalesTeamsByTBussUnit(SearchFilter<TSalesTeamId> searchFilter);
	
	List<TSalesTeam> getTSalesTeamsByTBussUnitIds(List<Long> bussUnitIds,
			Short tenantId);
	
	
	/**
	 * Find t sales team by search criteria.
	 *
	 * @param criteria the criteria
	 * @return the list
	 */
	List<TSalesTeam> findTSalesTeamBySearchCriteria(ISearchCriteria criteria);

}
