package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TPrdHierConfig;
import com.cognizant.opserv.sp.core.entity.TPrdHierGroup;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TPrdHierGroup DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TPrdHierGroupDAO {

	/**
	 * Stores a new TPrdHierGroup entity object in to the persistent store
	 * 
	 * @param tPrdHierGroup
	 *            TPrdHierGroup Entity object to be persisted
	 * @return tPrdHierGroup Persisted TPrdHierGroup object
	 */
	TPrdHierGroup createTPrdHierGroup(TPrdHierGroup tPrdHierGroup);

	/**
	 * Deletes a TPrdHierGroup entity object from the persistent store
	 * 
	 * @param tPrdHierGroup
	 *            TPrdHierGroup Entity object to be deleted
	 */
	void deleteTPrdHierGroup(Integer groupId);

	/**
	 * Updates a TPrdHierGroup entity object in to the persistent store
	 * 
	 * @param tPrdHierGroup
	 *            TPrdHierGroup Entity object to be updated
	 * @return tPrdHierGroup Persisted TPrdHierGroup object
	 */
	TPrdHierGroup updateTPrdHierGroup(TPrdHierGroup tPrdHierGroup);

	/**
	 * Retrieve an TPrdHierGroup object based on given groupId.
	 * 
	 * @param groupId
	 *            the primary key value of the TPrdHierGroup Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TPrdHierGroup findTPrdHierGroupById(Integer groupId);

	/**
	 * Retrieve TPrdHierGroup based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TPrdHierGroup> list of TPrdHierGroups if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TPrdHierGroup> findTPrdHierGroups(SearchFilter<TPrdHierGroup> searchFilter);

	/**
	 * Count TPrdHierGroup based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTPrdHierGroups(SearchFilter<TPrdHierGroup> searchFilter);

	/**
	 * Retrieve TPrdHierGroup based on given search criteria using JPA named Query.
	 * The search criteria is of TPrdHierConfig type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TPrdHierGroup> list of TPrdHierGroups if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TPrdHierGroup> getTPrdHierGroupsByTPrdHierConfig(SearchFilter<TPrdHierConfig> searchFilter);

	/**
	 * Count TPrdHierGroup based on given search criteria using JPA named Query.
	 * The search criteria is of TPrdHierConfig type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTPrdHierGroupsByTPrdHierConfig(SearchFilter<TPrdHierConfig> searchFilter);

	/**
	 * Retrieve TPrdHierGroup based on given search criteria using JPA named Query.
	 * The search criteria is of hierLevelId.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TPrdHierGroup> list of TPrdHierGroups if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TPrdHierGroup> getTPrdHierGroupsByTPrdHierLevelId(Integer hierLevelId);
	
}
