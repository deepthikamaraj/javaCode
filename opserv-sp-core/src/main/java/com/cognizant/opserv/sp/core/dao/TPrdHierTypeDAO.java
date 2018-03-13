package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TPrdHierType;
import com.cognizant.opserv.sp.core.entity.TPrdHierTypeId;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TPrdHierType DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TPrdHierTypeDAO {

	/**
	 * Stores a new TPrdHierType entity object in to the persistent store
	 * 
	 * @param tPrdHierType
	 *            TPrdHierType Entity object to be persisted
	 * @return tPrdHierType Persisted TPrdHierType object
	 */
	TPrdHierType createTPrdHierType(TPrdHierType tPrdHierType);

	/**
	 * Deletes a TPrdHierType entity object from the persistent store
	 * 
	 * @param tPrdHierType
	 *            TPrdHierType Entity object to be deleted
	 */
	void deleteTPrdHierType(Integer hierTypeId);

	/**
	 * Updates a TPrdHierType entity object in to the persistent store
	 * 
	 * @param tPrdHierType
	 *            TPrdHierType Entity object to be updated
	 * @return tPrdHierType Persisted TPrdHierType object
	 */
	TPrdHierType updateTPrdHierType(TPrdHierType tPrdHierType);

	/**
	 * Retrieve an TPrdHierType object based on given hierTypeId.
	 * 
	 * @param hierTypeId
	 *            the primary key value of the TPrdHierType Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TPrdHierType findTPrdHierTypeById(TPrdHierTypeId hierTypeId);

	/**
	 * Retrieve TPrdHierType based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TPrdHierType> list of TPrdHierTypes if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TPrdHierType> findTPrdHierTypes(SearchFilter<TPrdHierType> searchFilter);

	/**
	 * Count TPrdHierType based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTPrdHierTypes(SearchFilter<TPrdHierType> searchFilter);

}
