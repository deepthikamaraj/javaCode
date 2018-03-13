package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TPrdHierLevel;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TPrdHierLevel DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TPrdHierLevelDAO {

	/**
	 * Stores a new TPrdHierLevel entity object in to the persistent store
	 * 
	 * @param tPrdHierLevel
	 *            TPrdHierLevel Entity object to be persisted
	 * @return tPrdHierLevel Persisted TPrdHierLevel object
	 */
	TPrdHierLevel createTPrdHierLevel(TPrdHierLevel tPrdHierLevel);

	/**
	 * Deletes a TPrdHierLevel entity object from the persistent store
	 * 
	 * @param tPrdHierLevel
	 *            TPrdHierLevel Entity object to be deleted
	 */
	void deleteTPrdHierLevel(Integer hierLevelId);

	/**
	 * Updates a TPrdHierLevel entity object in to the persistent store
	 * 
	 * @param tPrdHierLevel
	 *            TPrdHierLevel Entity object to be updated
	 * @return tPrdHierLevel Persisted TPrdHierLevel object
	 */
	TPrdHierLevel updateTPrdHierLevel(TPrdHierLevel tPrdHierLevel);

	/**
	 * Retrieve an TPrdHierLevel object based on given hierLevelId.
	 * 
	 * @param hierLevelId
	 *            the primary key value of the TPrdHierLevel Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TPrdHierLevel findTPrdHierLevelById(Integer hierLevelId);

	/**
	 * Retrieve TPrdHierLevel based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TPrdHierLevel> list of TPrdHierLevels if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TPrdHierLevel> findTPrdHierLevels(SearchFilter<TPrdHierLevel> searchFilter)  throws Exception;

	/**
	 * Count TPrdHierLevel based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTPrdHierLevels(SearchFilter<TPrdHierLevel> searchFilter);

	/**
	 * Retrieve TPrdHierLevel based on given search criteria using JPA named Query.
	 * The search criteria is of TPrdHierLevel type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TPrdHierLevel> list of TPrdHierLevels if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TPrdHierLevel> getTPrdHierLevelsByTPrdHierLevel(SearchFilter<TPrdHierLevel> searchFilter);

	/**
	 * Count TPrdHierLevel based on given search criteria using JPA named Query.
	 * The search criteria is of TPrdHierLevel type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTPrdHierLevelsByTPrdHierLevel(SearchFilter<TPrdHierLevel> searchFilter);
	
	public List<Object> findTPrdHierLevelByMaxOfHierLevelId(Short tenantId);
	
	List<TPrdHierLevel> findAllTPrdHierLevels(Short tenantId);
	
	public List<Integer> findMaxOfHierLevelId(Short tenantId);
	

}
