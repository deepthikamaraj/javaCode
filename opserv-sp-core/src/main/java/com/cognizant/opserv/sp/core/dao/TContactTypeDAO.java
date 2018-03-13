package com.cognizant.opserv.sp.core.dao;


import java.util.List;

import com.cognizant.opserv.sp.core.entity.TContactType;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TContactType DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TContactTypeDAO {

	/**
	 * Stores a new TContactType entity object in to the persistent store
	 * 
	 * @param tContactType
	 *            TContactType Entity object to be persisted
	 * @return tContactType Persisted TContactType object
	 */
	TContactType createTContactType(TContactType tContactType);

	/**
	 * Deletes a TContactType entity object from the persistent store
	 * 
	 * @param tContactType
	 *            TContactType Entity object to be deleted
	 */
	void deleteTContactType(Integer contactTypeId);

	/**
	 * Updates a TContactType entity object in to the persistent store
	 * 
	 * @param tContactType
	 *            TContactType Entity object to be updated
	 * @return tContactType Persisted TContactType object
	 */
	TContactType updateTContactType(TContactType tContactType);

	/**
	 * Retrieve an TContactType object based on given contactTypeId.
	 * 
	 * @param contactTypeId
	 *            the primary key value of the TContactType Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TContactType findTContactTypeById(Integer contactTypeId);

	/**
	 * Retrieve TContactType based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TContactType> list of TContactTypes if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TContactType> findTContactTypes(SearchFilter<TContactType> searchFilter);

	/**
	 * Count TContactType based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTContactTypes(SearchFilter<TContactType> searchFilter);

}
