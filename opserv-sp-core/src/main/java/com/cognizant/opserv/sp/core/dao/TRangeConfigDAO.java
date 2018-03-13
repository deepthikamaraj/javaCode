package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TRangeConfig;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TRangeConfig DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TRangeConfigDAO {

	/**
	 * Stores a new TRangeConfig entity object in to the persistent store
	 * 
	 * @param tRangeConfig
	 *            TRangeConfig Entity object to be persisted
	 * @return tRangeConfig Persisted TRangeConfig object
	 */
	TRangeConfig createTRangeConfig(TRangeConfig tRangeConfig);

	/**
	 * Deletes a TRangeConfig entity object from the persistent store
	 * 
	 * @param tRangeConfig
	 *            TRangeConfig Entity object to be deleted
	 */
	void deleteTRangeConfig(Integer rangeConfigId);

	/**
	 * Updates a TRangeConfig entity object in to the persistent store
	 * 
	 * @param tRangeConfig
	 *            TRangeConfig Entity object to be updated
	 * @return tRangeConfig Persisted TRangeConfig object
	 */
	TRangeConfig updateTRangeConfig(TRangeConfig tRangeConfig);

	/**
	 * Retrieve an TRangeConfig object based on given rangeConfigId.
	 * 
	 * @param rangeConfigId
	 *            the primary key value of the TRangeConfig Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TRangeConfig findTRangeConfigById(Integer rangeConfigId);

	/**
	 * Retrieve TRangeConfig based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRangeConfig> list of TRangeConfigs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TRangeConfig> findTRangeConfigs(SearchFilter<TRangeConfig> searchFilter);

	/**
	 * Count TRangeConfig based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTRangeConfigs(SearchFilter<TRangeConfig> searchFilter);
	
	List<TRangeConfig> findTRangeConfigByLimits(int lowerLimit, int upperLimit,short tenantId);

	List<TRangeConfig> findTRangeConfigByTenant(Short tenantId);

	List<TRangeConfig> findTRangeConfig(Short tenantId);

}
