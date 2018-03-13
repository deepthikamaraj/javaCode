package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TIndiaDistrict;
import com.cognizant.opserv.sp.core.entity.TIndiaTaluk;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TIndiaTaluk DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TIndiaTalukDAO {

	/**
	 * Stores a new TIndiaTaluk entity object in to the persistent store
	 * 
	 * @param tIndiaTaluk
	 *            TIndiaTaluk Entity object to be persisted
	 * @return tIndiaTaluk Persisted TIndiaTaluk object
	 */
	TIndiaTaluk createTIndiaTaluk(TIndiaTaluk tIndiaTaluk);

	/**
	 * Deletes a TIndiaTaluk entity object from the persistent store
	 * 
	 * @param tIndiaTaluk
	 *            TIndiaTaluk Entity object to be deleted
	 */
	void deleteTIndiaTaluk(Integer talukId);

	/**
	 * Updates a TIndiaTaluk entity object in to the persistent store
	 * 
	 * @param tIndiaTaluk
	 *            TIndiaTaluk Entity object to be updated
	 * @return tIndiaTaluk Persisted TIndiaTaluk object
	 */
	TIndiaTaluk updateTIndiaTaluk(TIndiaTaluk tIndiaTaluk);

	/**
	 * Retrieve an TIndiaTaluk object based on given talukId.
	 * 
	 * @param talukId
	 *            the primary key value of the TIndiaTaluk Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TIndiaTaluk findTIndiaTalukById(Integer talukId);

	/**
	 * Retrieve TIndiaTaluk based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TIndiaTaluk> list of TIndiaTaluks if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TIndiaTaluk> findTIndiaTaluks(SearchFilter<TIndiaTaluk> searchFilter);

	/**
	 * Count TIndiaTaluk based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTIndiaTaluks(SearchFilter<TIndiaTaluk> searchFilter);

	/**
	 * Retrieve TIndiaTaluk based on given search criteria using JPA named Query.
	 * The search criteria is of TIndiaDistrict type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TIndiaTaluk> list of TIndiaTaluks if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TIndiaTaluk> getTIndiaTaluksByTIndiaDistrict(SearchFilter<TIndiaDistrict> searchFilter);

	/**
	 * Count TIndiaTaluk based on given search criteria using JPA named Query.
	 * The search criteria is of TIndiaDistrict type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTIndiaTaluksByTIndiaDistrict(SearchFilter<TIndiaDistrict> searchFilter);
	// added for themes to logical layers
	List<String> findIndiaTaluk();
}
