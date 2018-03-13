package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TAlgmntSalesTeam;
import com.cognizant.opserv.sp.core.entity.TCallPlanConfig;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TCallPlanConfig DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TCallPlanConfigDAO {

	/**
	 * Stores a new TCallPlanConfig entity object in to the persistent store
	 * 
	 * @param tCallPlanConfig
	 *            TCallPlanConfig Entity object to be persisted
	 * @return tCallPlanConfig Persisted TCallPlanConfig object
	 */
	TCallPlanConfig createTCallPlanConfig(TCallPlanConfig tCallPlanConfig);

	/**
	 * Deletes a TCallPlanConfig entity object from the persistent store
	 * 
	 * @param tCallPlanConfig
	 *            TCallPlanConfig Entity object to be deleted
	 */
	void deleteTCallPlanConfig(Integer callPlanConfigId);

	/**
	 * Updates a TCallPlanConfig entity object in to the persistent store
	 * 
	 * @param tCallPlanConfig
	 *            TCallPlanConfig Entity object to be updated
	 * @return tCallPlanConfig Persisted TCallPlanConfig object
	 */
	TCallPlanConfig updateTCallPlanConfig(TCallPlanConfig tCallPlanConfig);

	/**
	 * Retrieve an TCallPlanConfig object based on given callPlanConfigId.
	 * 
	 * @param callPlanConfigId
	 *            the primary key value of the TCallPlanConfig Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TCallPlanConfig findTCallPlanConfigById(Integer callPlanConfigId);

	/**
	 * Retrieve TCallPlanConfig based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCallPlanConfig> list of TCallPlanConfigs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TCallPlanConfig> findTCallPlanConfigs(SearchFilter<TCallPlanConfig> searchFilter);

	/**
	 * Count TCallPlanConfig based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTCallPlanConfigs(SearchFilter<TCallPlanConfig> searchFilter);

	/**
	 * Retrieve TCallPlanConfig based on given search criteria using JPA named Query.
	 * The search criteria is of TAlgmntSalesTeam type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCallPlanConfig> list of TCallPlanConfigs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TCallPlanConfig> getTCallPlanConfigsByTAlgmntSalesTeam(SearchFilter<TAlgmntSalesTeam> searchFilter);

	/**
	 * Count TCallPlanConfig based on given search criteria using JPA named Query.
	 * The search criteria is of TAlgmntSalesTeam type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTCallPlanConfigsByTAlgmntSalesTeam(SearchFilter<TAlgmntSalesTeam> searchFilter);

	/**
	 * Updates a TCallPlanConfig entity object activeFlag member variable to the
	 * persistent store
	 * 
	 * 
	 * 
	 * @return tCallPlanConfig Persisted TCallPlanConfig object
	 */

	void updateTCallPlanConfig(Character activeFlag, Integer tCallPlanConfigID);
	/**
	 * Retrieve StatusOfCallPlan based on given search criteria using JPA named Query.
	 * 
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<object> list of StatusOfCallPlan if it exists against given
	 *         criteria.
	 */
	List<Object> findStatusOfCallPlan(Long posId, Long salesHeirId, Short tenantId);
	/**
	 * Retrieve TCallPlanConfig based on given search criteria using JPA named Query.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCallPlanConfig> list of TCallPlanConfigsByAlBuSt if it exists against given
	 *         criteria. 
	 */
	
	List<TCallPlanConfig> getCallPlanConfig(TCallPlanConfig tCallPlanConfig);
	/**
	 * Retrieve TCallPlanConfig based on given search criteria using JPA named Query.
	 * The search criteria is of TAlgmntSalesTeam type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCallPlanConfig> list of TCallPlanConfigsByFlag if it exists against given
	 *         criteria. 
	 */
	List<TCallPlanConfig> getCallPlanConfigByFlag(Character affiliationFlag, Character callDirChangeFlag,Short tenantId);
	/**
	 * Retrieve TCallPlanConfigsByAlBuSt based on given search criteria using JPA named Query.
	 * The search criteria is of TCallPlanConfigsByAlBuSt
	 * 
	 * @param algmntId
	 *           - algmntId
	 * @Param tenantId
	 *           -tenantId  
	 *@Param salesTeamId
	 *          -salesTeamId 
	 *@Param bussUnitId
	 *           -bussUnitId 
	 *@Param activeFlag
	 *           -activeFlag 
	 *@Param actFlag
	 *           -actFlag         
	 * @return List<character> list of TCallPlanConfigsByAlBuSt if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<Character> findActiveTCallPlanConfigsByAlBuSt(Long algmntId,
			Long bussUnitId, Long salesTeamId, Character activeFlag,
			Character actFlag, Short tenantId);


}
