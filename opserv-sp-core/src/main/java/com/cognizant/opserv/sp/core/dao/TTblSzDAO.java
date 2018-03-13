package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TTblSz;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TTblSz DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TTblSzDAO {

	/**
	 * Stores a new TTblSz entity object in to the persistent store
	 * 
	 * @param tTblSz
	 *            TTblSz Entity object to be persisted
	 * @return tTblSz Persisted TTblSz object
	 */
	TTblSz createTTblSz(TTblSz tTblSz);

	/**
	 * Deletes a TTblSz entity object from the persistent store
	 * 
	 * @param tTblSz
	 *            TTblSz Entity object to be deleted
	 */
	void deleteTTblSz(String tblName);

	/**
	 * Updates a TTblSz entity object in to the persistent store
	 * 
	 * @param tTblSz
	 *            TTblSz Entity object to be updated
	 * @return tTblSz Persisted TTblSz object
	 */
	TTblSz updateTTblSz(TTblSz tTblSz);

	/**
	 * Retrieve an TTblSz object based on given tblName.
	 * 
	 * @param tblName
	 *            the primary key value of the TTblSz Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TTblSz findTTblSzById(String tblName);

	/**
	 * Retrieve TTblSz based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TTblSz> list of TTblSzs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TTblSz> findTTblSzs(SearchFilter<TTblSz> searchFilter);

	/**
	 * Count TTblSz based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTTblSzs(SearchFilter<TTblSz> searchFilter);

}
