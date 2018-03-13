package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TPrd;
import com.cognizant.opserv.sp.core.entity.TPrdHier;
import com.cognizant.opserv.sp.core.entity.TPrdHierGroup;
import com.cognizant.opserv.sp.core.entity.TPrdHierId;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TPrdHier DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TPrdHierDAO {

	/**
	 * Stores a new TPrdHier entity object in to the persistent store
	 * 
	 * @param tPrdHier
	 *            TPrdHier Entity object to be persisted
	 * @return tPrdHier Persisted TPrdHier object
	 */
	TPrdHier createTPrdHier(TPrdHier tPrdHier);

	/**
	 * Deletes a TPrdHier entity object from the persistent store
	 * 
	 * @param tPrdHier
	 *            TPrdHier Entity object to be deleted
	 */
	void deleteTPrdHier(TPrdHierId tPrdHierId);

	/**
	 * Updates a TPrdHier entity object in to the persistent store
	 * 
	 * @param tPrdHier
	 *            TPrdHier Entity object to be updated
	 * @return tPrdHier Persisted TPrdHier object
	 */
	TPrdHier updateTPrdHier(TPrdHier tPrdHier);

	/**
	 * Retrieve an TPrdHier object based on given TPrdHierId.
	 * 
	 * @param tPrdHierId
	 *            the primary key value of the TPrdHier Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TPrdHier findTPrdHierById(TPrdHierId tPrdHierId);

	/**
	 * Retrieve TPrdHier based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TPrdHier> list of TPrdHiers if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TPrdHier> findTPrdHiers(SearchFilter<TPrdHier> searchFilter);

	/**
	 * Count TPrdHier based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTPrdHiers(SearchFilter<TPrdHier> searchFilter);

	/**
	 * Retrieve TPrdHier based on given search criteria using JPA named Query.
	 * The search criteria is of TPrdHierGroup type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TPrdHier> list of TPrdHiers if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TPrdHier> getTPrdHiersByTPrdHierGroup(SearchFilter<TPrdHierGroup> searchFilter);

	/**
	 * Count TPrdHier based on given search criteria using JPA named Query.
	 * The search criteria is of TPrdHierGroup type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTPrdHiersByTPrdHierGroup(SearchFilter<TPrdHierGroup> searchFilter);

	/**
	 * Retrieve TPrdHier based on given search criteria using JPA named Query.
	 * The search criteria is of TPrd type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TPrdHier> list of TPrdHiers if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TPrdHier> getTPrdHiersByTPrd(SearchFilter<TPrd> searchFilter);

	/**
	 * Count TPrdHier based on given search criteria using JPA named Query.
	 * The search criteria is of TPrd type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTPrdHiersByTPrd(SearchFilter<TPrd> searchFilter);

	/**
	 * Retrieve TPrdHier based on given search criteria using JPA named Query.
	 * The search criteria is of TPrdHier type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TPrdHier> list of TPrdHiers if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TPrdHier> getTPrdHiersByTPrdHier(SearchFilter<TPrdHier> searchFilter);

	/**
	 * Count TPrdHier based on given search criteria using JPA named Query.
	 * The search criteria is of TPrdHier type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTPrdHiersByTPrdHier(SearchFilter<TPrdHier> searchFilter);

}
