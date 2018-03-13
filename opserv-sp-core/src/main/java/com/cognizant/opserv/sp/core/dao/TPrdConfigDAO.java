package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TPrdConfig;
import com.cognizant.opserv.sp.core.entity.TPrdHierType;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TPrdConfig DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TPrdConfigDAO {

	/**
	 * Stores a new TPrdConfig entity object in to the persistent store
	 * 
	 * @param tPrdConfig
	 *            TPrdConfig Entity object to be persisted
	 * @return tPrdConfig Persisted TPrdConfig object
	 */
	TPrdConfig createTPrdConfig(TPrdConfig tPrdConfig);

	/**
	 * Deletes a TPrdConfig entity object from the persistent store
	 * 
	 * @param tPrdConfig
	 *            TPrdConfig Entity object to be deleted
	 */
	void deleteTPrdConfig(Integer prdConfigId);

	/**
	 * Updates a TPrdConfig entity object in to the persistent store
	 * 
	 * @param tPrdConfig
	 *            TPrdConfig Entity object to be updated
	 * @return tPrdConfig Persisted TPrdConfig object
	 */
	TPrdConfig updateTPrdConfig(TPrdConfig tPrdConfig);

	/**
	 * Retrieve an TPrdConfig object based on given prdConfigId.
	 * 
	 * @param prdConfigId
	 *            the primary key value of the TPrdConfig Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TPrdConfig findTPrdConfigById(Integer prdConfigId);

	/**
	 * Retrieve TPrdConfig based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TPrdConfig> list of TPrdConfigs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TPrdConfig> findTPrdConfigs(SearchFilter<TPrdConfig> searchFilter);

	/**
	 * Count TPrdConfig based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTPrdConfigs(SearchFilter<TPrdConfig> searchFilter);

	/**
	 * Retrieve TPrdConfig based on given search criteria using JPA named Query.
	 * The search criteria is of TPrdHierType type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TPrdConfig> list of TPrdConfigs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TPrdConfig> getTPrdConfigsByTPrdHierType(SearchFilter<TPrdHierType> searchFilter);

	/**
	 * Count TPrdConfig based on given search criteria using JPA named Query.
	 * The search criteria is of TPrdHierType type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTPrdConfigsByTPrdHierType(SearchFilter<TPrdHierType> searchFilter);

}
