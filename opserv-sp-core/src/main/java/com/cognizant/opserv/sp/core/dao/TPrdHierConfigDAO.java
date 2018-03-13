package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TPrdConfig;
import com.cognizant.opserv.sp.core.entity.TPrdHierConfig;
import com.cognizant.opserv.sp.core.entity.TPrdHierConfigId;
import com.cognizant.opserv.sp.core.entity.TPrdHierLevel;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TPrdHierConfig DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TPrdHierConfigDAO {

	/**
	 * Stores a new TPrdHierConfig entity object in to the persistent store
	 * 
	 * @param tPrdHierConfig
	 *            TPrdHierConfig Entity object to be persisted
	 * @return tPrdHierConfig Persisted TPrdHierConfig object
	 */
	TPrdHierConfig createTPrdHierConfig(TPrdHierConfig tPrdHierConfig);

	/**
	 * Deletes a TPrdHierConfig entity object from the persistent store
	 * 
	 * @param tPrdHierConfig
	 *            TPrdHierConfig Entity object to be deleted
	 */
	void deleteTPrdHierConfig(TPrdHierConfigId tPrdHierConfigId);

	/**
	 * Updates a TPrdHierConfig entity object in to the persistent store
	 * 
	 * @param tPrdHierConfig
	 *            TPrdHierConfig Entity object to be updated
	 * @return tPrdHierConfig Persisted TPrdHierConfig object
	 */
	TPrdHierConfig updateTPrdHierConfig(TPrdHierConfig tPrdHierConfig);

	/**
	 * Retrieve an TPrdHierConfig object based on given TPrdHierConfigId.
	 * 
	 * @param tPrdHierConfigId
	 *            the primary key value of the TPrdHierConfig Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TPrdHierConfig findTPrdHierConfigById(TPrdHierConfigId tPrdHierConfigId);

	/**
	 * Retrieve TPrdHierConfig based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TPrdHierConfig> list of TPrdHierConfigs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TPrdHierConfig> findTPrdHierConfigs(SearchFilter<TPrdHierConfig> searchFilter);

	/**
	 * Count TPrdHierConfig based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTPrdHierConfigs(SearchFilter<TPrdHierConfig> searchFilter);

	/**
	 * Retrieve TPrdHierConfig based on given search criteria using JPA named Query.
	 * The search criteria is of TPrdConfig type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TPrdHierConfig> list of TPrdHierConfigs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TPrdHierConfig> getTPrdHierConfigsByTPrdConfig(SearchFilter<TPrdConfig> searchFilter);

	/**
	 * Count TPrdHierConfig based on given search criteria using JPA named Query.
	 * The search criteria is of TPrdConfig type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTPrdHierConfigsByTPrdConfig(SearchFilter<TPrdConfig> searchFilter);

	/**
	 * Retrieve TPrdHierConfig based on given search criteria using JPA named Query.
	 * The search criteria is of TPrdHierLevel type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TPrdHierConfig> list of TPrdHierConfigs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TPrdHierConfig> getTPrdHierConfigsByTPrdHierLevel(SearchFilter<TPrdHierLevel> searchFilter);

	/**
	 * Count TPrdHierConfig based on given search criteria using JPA named Query.
	 * The search criteria is of TPrdHierLevel type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTPrdHierConfigsByTPrdHierLevel(SearchFilter<TPrdHierLevel> searchFilter);
	
	List<TPrdHierConfig> FindTPrdHierConfigByHierLevelID(int hierLevelId,Short tenantId);

}
