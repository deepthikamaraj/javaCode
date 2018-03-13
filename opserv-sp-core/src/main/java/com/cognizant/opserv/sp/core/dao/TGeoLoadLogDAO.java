package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TGeoLoadLog;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TGeoLoadLog DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TGeoLoadLogDAO {

	/**
	 * Stores a new TGeoLoadLog entity object in to the persistent store
	 * 
	 * @param tGeoLoadLog
	 *            TGeoLoadLog Entity object to be persisted
	 * @return tGeoLoadLog Persisted TGeoLoadLog object
	 */
	TGeoLoadLog createTGeoLoadLog(TGeoLoadLog tGeoLoadLog);

	/**
	 * Deletes a TGeoLoadLog entity object from the persistent store
	 * 
	 * @param tGeoLoadLog
	 *            TGeoLoadLog Entity object to be deleted
	 */
	void deleteTGeoLoadLog(Long logId);

	/**
	 * Updates a TGeoLoadLog entity object in to the persistent store
	 * 
	 * @param tGeoLoadLog
	 *            TGeoLoadLog Entity object to be updated
	 * @return tGeoLoadLog Persisted TGeoLoadLog object
	 */
	TGeoLoadLog updateTGeoLoadLog(TGeoLoadLog tGeoLoadLog);

	/**
	 * Retrieve an TGeoLoadLog object based on given logId.
	 * 
	 * @param logId
	 *            the primary key value of the TGeoLoadLog Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TGeoLoadLog findTGeoLoadLogById(Long logId);

	/**
	 * Retrieve TGeoLoadLog based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TGeoLoadLog> list of TGeoLoadLogs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TGeoLoadLog> findTGeoLoadLogs(SearchFilter<TGeoLoadLog> searchFilter);

	/**
	 * Count TGeoLoadLog based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTGeoLoadLogs(SearchFilter<TGeoLoadLog> searchFilter);	

}
