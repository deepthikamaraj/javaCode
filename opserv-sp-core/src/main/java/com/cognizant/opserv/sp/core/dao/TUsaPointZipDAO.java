package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TUsaPointZip;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TUsaPointZip DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TUsaPointZipDAO {

	/**
	 * Stores a new TUsaPointZip entity object in to the persistent store
	 * 
	 * @param tUsaPointZip
	 *            TUsaPointZip Entity object to be persisted
	 * @return tUsaPointZip Persisted TUsaPointZip object
	 */
	TUsaPointZip createTUsaPointZip(TUsaPointZip tUsaPointZip);

	/**
	 * Deletes a TUsaPointZip entity object from the persistent store
	 * 
	 * @param tUsaPointZip
	 *            TUsaPointZip Entity object to be deleted
	 */
	void deleteTUsaPointZip(Integer zipId);

	/**
	 * Updates a TUsaPointZip entity object in to the persistent store
	 * 
	 * @param tUsaPointZip
	 *            TUsaPointZip Entity object to be updated
	 * @return tUsaPointZip Persisted TUsaPointZip object
	 */
	TUsaPointZip updateTUsaPointZip(TUsaPointZip tUsaPointZip);

	/**
	 * Retrieve an TUsaPointZip object based on given zipId.
	 * 
	 * @param zipId
	 *            the primary key value of the TUsaPointZip Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TUsaPointZip findTUsaPointZipById(Integer zipId);

	/**
	 * Retrieve TUsaPointZip based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TUsaPointZip> list of TUsaPointZips if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TUsaPointZip> findTUsaPointZips(SearchFilter<TUsaPointZip> searchFilter);

	/**
	 * Count TUsaPointZip based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTUsaPointZips(SearchFilter<TUsaPointZip> searchFilter);

	List<TUsaPointZip> findAllTUsaPointZips();

	public List<TUsaPointZip> findAllTUsaPointZipsByPrnId(List<String> zipPrnIdList);
	

}
