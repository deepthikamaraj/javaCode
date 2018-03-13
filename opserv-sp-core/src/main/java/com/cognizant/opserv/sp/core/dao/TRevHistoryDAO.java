package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TRevHistory;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TRevHistory DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TRevHistoryDAO {

	/**
	 * Stores a new TRevHistory entity object in to the persistent store
	 * 
	 * @param tRevHistory
	 *            TRevHistory Entity object to be persisted
	 * @return tRevHistory Persisted TRevHistory object
	 */
	TRevHistory createTRevHistory(TRevHistory tRevHistory);

	/**
	 * Deletes a TRevHistory entity object from the persistent store
	 * 
	 * @param tRevHistory
	 *            TRevHistory Entity object to be deleted
	 */
	void deleteTRevHistory(Long revId);

	/**
	 * Updates a TRevHistory entity object in to the persistent store
	 * 
	 * @param tRevHistory
	 *            TRevHistory Entity object to be updated
	 * @return tRevHistory Persisted TRevHistory object
	 */
	TRevHistory updateTRevHistory(TRevHistory tRevHistory);

	/**
	 * Retrieve an TRevHistory object based on given revId.
	 * 
	 * @param revId
	 *            the primary key value of the TRevHistory Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TRevHistory findTRevHistoryById(Long revId);

	/**
	 * Retrieve TRevHistory based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRevHistory> list of TRevHistorys if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TRevHistory> findTRevHistorys(SearchFilter<TRevHistory> searchFilter);

	/**
	 * Count TRevHistory based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTRevHistorys(SearchFilter<TRevHistory> searchFilter);

}
