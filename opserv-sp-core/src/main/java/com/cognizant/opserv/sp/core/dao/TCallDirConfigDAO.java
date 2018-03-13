package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TCallDirConfig;
import com.cognizant.opserv.sp.core.entity.TCallPlanConfig;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TCallDirConfig DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TCallDirConfigDAO {

	/**
	 * Stores a new TCallDirConfig entity object in to the persistent store
	 * 
	 * @param tCallDirConfig
	 *            TCallDirConfig Entity object to be persisted
	 * @return tCallDirConfig Persisted TCallDirConfig object
	 */
	TCallDirConfig createTCallDirConfig(TCallDirConfig tCallDirConfig);

	/**
	 * Deletes a TCallDirConfig entity object from the persistent store
	 * 
	 * @param tCallDirConfig
	 *            TCallDirConfig Entity object to be deleted
	 */
	void deleteTCallDirConfig(Integer callDirConfigId);

	/**
	 * Updates a TCallDirConfig entity object in to the persistent store
	 * 
	 * @param tCallDirConfig
	 *            TCallDirConfig Entity object to be updated
	 * @return tCallDirConfig Persisted TCallDirConfig object
	 */
	TCallDirConfig updateTCallDirConfig(TCallDirConfig tCallDirConfig);

	/**
	 * Retrieve an TCallDirConfig object based on given callDirConfigId.
	 * 
	 * @param callDirConfigId
	 *            the primary key value of the TCallDirConfig Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TCallDirConfig findTCallDirConfigById(Integer callDirConfigId);

	/**
	 * Retrieve TCallDirConfig based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCallDirConfig> list of TCallDirConfigs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TCallDirConfig> findTCallDirConfigs(SearchFilter<TCallDirConfig> searchFilter);

	/**
	 * Count TCallDirConfig based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTCallDirConfigs(SearchFilter<TCallDirConfig> searchFilter);

	/**
	 * Retrieve TCallDirConfig based on given search criteria using JPA named Query.
	 * The search criteria is of TCallPlanConfig type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCallDirConfig> list of TCallDirConfigs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TCallDirConfig> getTCallDirConfigsByTCallPlanConfig(SearchFilter<TCallPlanConfig> searchFilter);

	/**
	 * Count TCallDirConfig based on given search criteria using JPA named Query.
	 * The search criteria is of TCallPlanConfig type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTCallDirConfigsByTCallPlanConfig(SearchFilter<TCallPlanConfig> searchFilter);
	/**
	 * Retrieve TCallDirConfig based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param callPlanId
	 *          - callPlanId
	 * @Param size
	 *        -size
	 * @Param tenantId
	 *           -TenantId       
	 * @return List<TCallDirConfig> list of TCallDirConfigs if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TCallDirConfig> getTCallDirConfig(Integer callPlanId, Integer size, Short tenantId);

}
