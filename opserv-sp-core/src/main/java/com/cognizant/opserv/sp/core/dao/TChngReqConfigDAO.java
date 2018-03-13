package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TAlgmntSalesTeam;
import com.cognizant.opserv.sp.core.entity.TChngReqConfig;
import com.cognizant.opserv.sp.core.entity.TChngReqTrigger;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TChngReqConfig DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TChngReqConfigDAO {

	/**
	 * Stores a new TChngReqConfig entity object in to the persistent store
	 * 
	 * @param tChngReqConfig
	 *            TChngReqConfig Entity object to be persisted
	 * @return tChngReqConfig Persisted TChngReqConfig object
	 */
	TChngReqConfig createTChngReqConfig(TChngReqConfig tChngReqConfig);

	/**
	 * Deletes a TChngReqConfig entity object from the persistent store
	 * 
	 * @param tChngReqConfig
	 *            TChngReqConfig Entity object to be deleted
	 */
	void deleteTChngReqConfig(Integer chngReqConfigId);

	/**
	 * Updates a TChngReqConfig entity object in to the persistent store
	 * 
	 * @param tChngReqConfig
	 *            TChngReqConfig Entity object to be updated
	 * @return tChngReqConfig Persisted TChngReqConfig object
	 */
	TChngReqConfig updateTChngReqConfig(TChngReqConfig tChngReqConfig);

	/**
	 * Retrieve an TChngReqConfig object based on given chngReqConfigId.
	 * 
	 * @param chngReqConfigId
	 *            the primary key value of the TChngReqConfig Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TChngReqConfig findTChngReqConfigById(Integer chngReqConfigId);

	/**
	 * Retrieve TChngReqConfig based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TChngReqConfig> list of TChngReqConfigs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TChngReqConfig> findTChngReqConfigs(SearchFilter<TChngReqConfig> searchFilter);

	/**
	 * Count TChngReqConfig based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTChngReqConfigs(SearchFilter<TChngReqConfig> searchFilter);

	/**
	 * Retrieve TChngReqConfig based on given search criteria using JPA named Query.
	 * The search criteria is of TChngReqTrigger type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TChngReqConfig> list of TChngReqConfigs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TChngReqConfig> getTChngReqConfigsByTChngReqTrigger(SearchFilter<TChngReqTrigger> searchFilter);

	/**
	 * Count TChngReqConfig based on given search criteria using JPA named Query.
	 * The search criteria is of TChngReqTrigger type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTChngReqConfigsByTChngReqTrigger(SearchFilter<TChngReqTrigger> searchFilter);

	/**
	 * Retrieve TChngReqConfig based on given search criteria using JPA named Query.
	 * The search criteria is of TAlgmntSalesTeam type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TChngReqConfig> list of TChngReqConfigs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TChngReqConfig> getTChngReqConfigsByTAlgmntSalesTeam(SearchFilter<TAlgmntSalesTeam> searchFilter);

	/**
	 * Count TChngReqConfig based on given search criteria using JPA named Query.
	 * The search criteria is of TAlgmntSalesTeam type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTChngReqConfigsByTAlgmntSalesTeam(SearchFilter<TAlgmntSalesTeam> searchFilter);
	
	
	/*Added by Ashis Samal*/
	/**
	 * Gets the t chng req config.
	 *
	 * @param params the params
	 * @param tenantId the tenant id
	 * @return the List<TChngReqConfig> list of 
	 */
	List<TChngReqConfig> getTChngReqConfig(List<Object> params) ;
	
	/**
	 * Find business rule.
	 *
	 * @param algmntId the algmnt id
	 * @param bussUnitId the buss unit id
	 * @param salesTeamId the sales team id
	 * @param tenantId the tenant id
	 * @return the list
	 */
	List<Object> findBusinessRule(Long algmntId, Long bussUnitId, Long salesTeamId,Short tenantId);
	
	/**
	 * Gets the t chng req configs by t chng req trigger id.
	 *
	 * @param triggerId the trigger id
	 * @param algnmntId the algnmnt id
	 * @param buId the bu id
	 * @param salesTeamId the sales team id
	 * @param tenantId the tenant id
	 * @return the t chng req configs by t chng req trigger id
	 */
	List<Object[]> getTChngReqConfigsByTChngReqTriggerId(Integer triggerId,Long algnmntId,Long buId,Long salesTeamId,short tenantId);

	/**
	 * Gets the t chng req configs trigger id.
	 *
	 * @param triggerId the trigger id
	 * @param algnmntId the algnmnt id
	 * @param buId the bu id
	 * @param salesTeamId the sales team id
	 * @param tenantId the tenant id
	 * @return the t chng req configs trigger id
	 */
	List<TChngReqConfig> getTChngReqConfigsTriggerId(Integer triggerId,Long algnmntId, Long buId, Long salesTeamId, Short tenantId);

	/**
	 * Find t chng req triggers by al bu st.
	 *
	 * @param algnmntId the algnmnt id
	 * @param buId the bu id
	 * @param salesTeamId the sales team id
	 * @param tenantId the tenant id
	 * @return the list
	 */
	List<TChngReqConfig> findTChngReqTriggersByAlBuSt(Long algnmntId,Long buId,Long salesTeamId,short tenantId) ;
	
	/**
	 * Findprmy secdry by al bu st trger id.
	 *
	 * @param triggerId the trigger id
	 * @param algnmntId the algnmnt id
	 * @param buId the bu id
	 * @param salesTeamId the sales team id
	 * @param tenantId the tenant id
	 * @return the list
	 */
	List<Object[]> findprmySecdryByAlBuStTrgerId(Integer triggerId,Long algnmntId,Long buId,Long salesTeamId, short tenantId);
	
	/**
	 * Gets the t chng req config.
	 *
	 * @param salesTeamId the sales team id
	 * @param AlignmentId the alignment id
	 * @param businessUnitId the business unit id
	 * @param triggerId the trigger id
	 * @param tenantid the tenantid
	 * @return the t chng req config
	 */
	List<TChngReqConfig> getTChngReqConfig(Long salesTeamId,Long AlignmentId,Long businessUnitId,Integer triggerId,Short tenantid);
}
