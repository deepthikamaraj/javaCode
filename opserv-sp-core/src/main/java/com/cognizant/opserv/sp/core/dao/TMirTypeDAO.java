package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TMirType;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TMirType DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TMirTypeDAO {

	/**
	 * Stores a new TMirType entity object in to the persistent store
	 * 
	 * @param tMirType
	 *            TMirType Entity object to be persisted
	 * @return tMirType Persisted TMirType object
	 */
	TMirType createTMirType(TMirType tMirType);

	/**
	 * Deletes a TMirType entity object from the persistent store
	 * 
	 * @param tMirType
	 *            TMirType Entity object to be deleted
	 */
	void deleteTMirType(Integer mirTypeId);

	/**
	 * Updates a TMirType entity object in to the persistent store
	 * 
	 * @param tMirType
	 *            TMirType Entity object to be updated
	 * @return tMirType Persisted TMirType object
	 */
	TMirType updateTMirType(TMirType tMirType);

	/**
	 * Retrieve an TMirType object based on given mirTypeId.
	 * 
	 * @param mirTypeId
	 *            the primary key value of the TMirType Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TMirType findTMirTypeById(Integer mirTypeId);

	/**
	 * Retrieve TMirType based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TMirType> list of TMirTypes if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TMirType> findTMirTypes(SearchFilter<TMirType> searchFilter);

	/**
	 * Count TMirType based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTMirTypes(SearchFilter<TMirType> searchFilter);

}
