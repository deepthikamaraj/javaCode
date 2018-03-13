package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TCommType;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TCommType DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TCommTypeDAO {

	/**
	 * Stores a new TCommType entity object in to the persistent store
	 * 
	 * @param tCommType
	 *            TCommType Entity object to be persisted
	 * @return tCommType Persisted TCommType object
	 */
	TCommType createTCommType(TCommType tCommType);

	/**
	 * Deletes a TCommType entity object from the persistent store
	 * 
	 * @param tCommType
	 *            TCommType Entity object to be deleted
	 */
	void deleteTCommType(Integer commTypeId);

	/**
	 * Updates a TCommType entity object in to the persistent store
	 * 
	 * @param tCommType
	 *            TCommType Entity object to be updated
	 * @return tCommType Persisted TCommType object
	 */
	TCommType updateTCommType(TCommType tCommType);

	/**
	 * Retrieve an TCommType object based on given commTypeId.
	 * 
	 * @param commTypeId
	 *            the primary key value of the TCommType Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TCommType findTCommTypeById(Integer commTypeId);

	/**
	 * Retrieve TCommType based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCommType> list of TCommTypes if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TCommType> findTCommTypes(SearchFilter<TCommType> searchFilter);

	/**
	 * Count TCommType based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTCommTypes(SearchFilter<TCommType> searchFilter);

}
