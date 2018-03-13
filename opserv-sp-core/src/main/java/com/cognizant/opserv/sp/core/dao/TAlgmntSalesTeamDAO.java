package com.cognizant.opserv.sp.core.dao;

import java.util.Date;
import java.util.List;

import com.cognizant.opserv.sp.core.entity.TAlgmnt;
import com.cognizant.opserv.sp.core.entity.TAlgmntSalesTeam;
import com.cognizant.opserv.sp.core.entity.TAlgmntSalesTeamId;
import com.cognizant.opserv.sp.core.entity.TSalesTeam;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TAlgmntSalesTeam DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TAlgmntSalesTeamDAO {

	/**
	 * Stores a new TAlgmntSalesTeam entity object in to the persistent store
	 * 
	 * @param tAlgmntSalesTeam
	 *            TAlgmntSalesTeam Entity object to be persisted
	 * @return tAlgmntSalesTeam Persisted TAlgmntSalesTeam object
	 */
	TAlgmntSalesTeam createTAlgmntSalesTeam(TAlgmntSalesTeam tAlgmntSalesTeam);

	/**
	 * Deletes a TAlgmntSalesTeam entity object from the persistent store
	 * 
	 * @param tAlgmntSalesTeam
	 *            TAlgmntSalesTeam Entity object to be deleted
	 */
	void deleteTAlgmntSalesTeam(TAlgmntSalesTeamId tAlgmntSalesTeamId);

	/**
	 * Updates a TAlgmntSalesTeam entity object in to the persistent store
	 * 
	 * @param tAlgmntSalesTeam
	 *            TAlgmntSalesTeam Entity object to be updated
	 * @return tAlgmntSalesTeam Persisted TAlgmntSalesTeam object
	 */
	TAlgmntSalesTeam updateTAlgmntSalesTeam(TAlgmntSalesTeam tAlgmntSalesTeam);

	/**
	 * Retrieve an TAlgmntSalesTeam object based on given TAlgmntSalesTeamId.
	 * 
	 * @param tAlgmntSalesTeamId
	 *            the primary key value of the TAlgmntSalesTeam Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TAlgmntSalesTeam findTAlgmntSalesTeamById(TAlgmntSalesTeamId tAlgmntSalesTeamId);

	/**
	 * Retrieve TAlgmntSalesTeam based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TAlgmntSalesTeam> list of TAlgmntSalesTeams if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TAlgmntSalesTeam> findTAlgmntSalesTeams(SearchFilter<TAlgmntSalesTeam> searchFilter);

	/**
	 * Count TAlgmntSalesTeam based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTAlgmntSalesTeams(SearchFilter<TAlgmntSalesTeam> searchFilter);

	/**
	 * Retrieve TAlgmntSalesTeam based on given search criteria using JPA named Query.
	 * The search criteria is of TSalesTeam type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TAlgmntSalesTeam> list of TAlgmntSalesTeams if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TAlgmntSalesTeam> getTAlgmntSalesTeamsByTSalesTeam(SearchFilter<TSalesTeam> searchFilter);

	/**
	 * Count TAlgmntSalesTeam based on given search criteria using JPA named Query.
	 * The search criteria is of TSalesTeam type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTAlgmntSalesTeamsByTSalesTeam(SearchFilter<TSalesTeam> searchFilter);

	/**
	 * Retrieve TAlgmntSalesTeam based on given search criteria using JPA named Query.
	 * The search criteria is of TAlgmnt type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TAlgmntSalesTeam> list of TAlgmntSalesTeams if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TAlgmntSalesTeam> getTAlgmntSalesTeamsByTAlgmnt(SearchFilter<TAlgmnt> searchFilter);

	/**
	 * Count TAlgmntSalesTeam based on given search criteria using JPA named Query.
	 * The search criteria is of TAlgmnt type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTAlgmntSalesTeamsByTAlgmnt(SearchFilter<TAlgmnt> searchFilter);
	
	/**
	 * Find active algmnts.
	 *
	 * @param searchFilter the search filter
	 * @return the list
	 */
	List<TAlgmntSalesTeam> findActiveAlgmnts(SearchFilter<TAlgmntSalesTeam> searchFilter);

	/**
	 * Find alignment search.
	 *
	 * @param salesTeam the sales team
	 * @param algnName the algn name
	 * @param buName the bu name
	 * @param status the status
	 * @param tenantId the tenant id
	 * @return the list
	 */
	List<TAlgmntSalesTeam> findAlignmentSearch(String salesTeam, String algnName,String buName, Integer status, Short tenantId);
	
	//List<TAlgmntSalesTeam> findAlignSearch(String salesTeam, String algnName,String buName, Integer status, Short tenantId,List<UserBuStDetails> usrList,int tRoleTypeId,String localeId); 
	
	/**
	 * Find t algmnt sales team by t sales team bu ids.
	 *
	 * @param salesTeamId the sales team id
	 * @param bussUnitId the buss unit id
	 * @return the list
	 */
	List<TAlgmntSalesTeam> findTAlgmntSalesTeamByTSalesTeamBUIds(long salesTeamId,long bussUnitId);

	/**
	 * Gets the aff alignments.
	 *
	 * @param tenantId the tenant id
	 * @return the aff alignments
	 */
	List<Object[]> getAffAlignments(Short tenantId);

	/**
	 * Gets the cR config.
	 *
	 * @param tenantId the tenant id
	 * @return the cR config
	 */
	List<Object[]> getCRConfig(Short tenantId);

	/**
	 * Fetch active algmnts data.
	 *
	 * @param effEdDt the eff ed dt
	 * @param tenantId the tenant id
	 * @return the list
	 */
	List<Object[]> fetchActiveAlgmntsData(Date effEdDt, Short tenantId);
}
