package com.cognizant.opserv.sp.core.dao;



import java.util.List;

import com.cognizant.opserv.sp.core.entity.TMtrValueType;
import com.cognizant.opserv.sp.core.entity.TMtrValueTypeId;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TMtrValueType DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TMtrValueTypeDAO {

	/**
	 * Stores a new TMtrValueType entity object in to the persistent store
	 * 
	 * @param tMtrValueType
	 *            TMtrValueType Entity object to be persisted
	 * @return tMtrValueType Persisted TMtrValueType object
	 */
	TMtrValueType createTMtrValueType(TMtrValueType tMtrValueType);

	/**
	 * Deletes a TMtrValueType entity object from the persistent store
	 * 
	 * @param tMtrValueType
	 *            TMtrValueType Entity object to be deleted
	 */
	void deleteTMtrValueType(TMtrValueTypeId tMtrValueTypeId);

	/**
	 * Updates a TMtrValueType entity object in to the persistent store
	 * 
	 * @param tMtrValueType
	 *            TMtrValueType Entity object to be updated
	 * @return tMtrValueType Persisted TMtrValueType object
	 */
	TMtrValueType updateTMtrValueType(TMtrValueType tMtrValueType);

	/**
	 * Retrieve an TMtrValueType object based on given TMtrValueTypeId.
	 * 
	 * @param tMtrValueTypeId
	 *            the primary key value of the TMtrValueType Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TMtrValueType findTMtrValueTypeById(TMtrValueTypeId tMtrValueTypeId);

	/**
	 * Retrieve TMtrValueType based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TMtrValueType> list of TMtrValueTypes if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TMtrValueType> findTMtrValueTypes(SearchFilter<TMtrValueType> searchFilter);

	/**
	 * Count TMtrValueType based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTMtrValueTypes(SearchFilter<TMtrValueType> searchFilter);

}
