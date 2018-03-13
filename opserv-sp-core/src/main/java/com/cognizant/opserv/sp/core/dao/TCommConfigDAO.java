package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TCommConfig;
import com.cognizant.opserv.sp.core.entity.TCommType;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TCommConfig DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TCommConfigDAO {

	/**
	 * Stores a new TCommConfig entity object in to the persistent store
	 * 
	 * @param tCommConfig
	 *            TCommConfig Entity object to be persisted
	 * @return tCommConfig Persisted TCommConfig object
	 */
	TCommConfig createTCommConfig(TCommConfig tCommConfig);

	/**
	 * Deletes a TCommConfig entity object from the persistent store
	 * 
	 * @param tCommConfig
	 *            TCommConfig Entity object to be deleted
	 */
	void deleteTCommConfig(Integer configId);

	/**
	 * Updates a TCommConfig entity object in to the persistent store
	 * 
	 * @param tCommConfig
	 *            TCommConfig Entity object to be updated
	 * @return tCommConfig Persisted TCommConfig object
	 */
	TCommConfig updateTCommConfig(TCommConfig tCommConfig);

	/**
	 * Retrieve an TCommConfig object based on given configId.
	 * 
	 * @param configId
	 *            the primary key value of the TCommConfig Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TCommConfig findTCommConfigById(Integer configId);

	/**
	 * Retrieve TCommConfig based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCommConfig> list of TCommConfigs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TCommConfig> findTCommConfigs(SearchFilter<TCommConfig> searchFilter);

	/**
	 * Count TCommConfig based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTCommConfigs(SearchFilter<TCommConfig> searchFilter);

	/**
	 * Retrieve TCommConfig based on given search criteria using JPA named Query.
	 * The search criteria is of TCommType type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCommConfig> list of TCommConfigs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TCommConfig> getTCommConfigsByTCommType(SearchFilter<TCommType> searchFilter);

	/**
	 * Count TCommConfig based on given search criteria using JPA named Query.
	 * The search criteria is of TCommType type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTCommConfigsByTCommType(SearchFilter<TCommType> searchFilter);

}
