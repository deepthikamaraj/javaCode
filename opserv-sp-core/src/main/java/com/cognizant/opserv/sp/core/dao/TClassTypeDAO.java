package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TClassType;
import com.cognizant.opserv.sp.core.entity.TClassTypeId;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TClassType DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TClassTypeDAO {

	/**
	 * Stores a new TClassType entity object in to the persistent store
	 * 
	 * @param tClassType
	 *            TClassType Entity object to be persisted
	 * @return tClassType Persisted TClassType object
	 */
	TClassType createTClassType(TClassType tClassType);

	/**
	 * Deletes a TClassType entity object from the persistent store
	 * 
	 * @param tClassType
	 *            TClassType Entity object to be deleted
	 */
	void deleteTClassType(TClassTypeId tClassTypeId);

	/**
	 * Updates a TClassType entity object in to the persistent store
	 * 
	 * @param tClassType
	 *            TClassType Entity object to be updated
	 * @return tClassType Persisted TClassType object
	 */
	TClassType updateTClassType(TClassType tClassType);

	/**
	 * Retrieve an TClassType object based on given TClassTypeId.
	 * 
	 * @param tClassTypeId
	 *            the primary key value of the TClassType Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TClassType findTClassTypeById(TClassTypeId tClassTypeId);

	/**
	 * Retrieve TClassType based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TClassType> list of TClassTypes if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TClassType> findTClassTypes(SearchFilter<TClassType> searchFilter);

	/**
	 * Count TClassType based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTClassTypes(SearchFilter<TClassType> searchFilter);

}
