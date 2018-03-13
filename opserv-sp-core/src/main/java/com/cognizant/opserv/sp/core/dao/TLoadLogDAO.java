package com.cognizant.opserv.sp.core.dao;


import java.util.List;

import com.cognizant.opserv.sp.core.entity.TLoadLog;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TLoadLog DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TLoadLogDAO {

	/**
	 * Stores a new TLoadLog entity object in to the persistent store
	 * 
	 * @param tLoadLog
	 *            TLoadLog Entity object to be persisted
	 * @return tLoadLog Persisted TLoadLog object
	 */
	TLoadLog createTLoadLog(TLoadLog tLoadLog);

	/**
	 * Deletes a TLoadLog entity object from the persistent store
	 * 
	 * @param tLoadLog
	 *            TLoadLog Entity object to be deleted
	 */
	void deleteTLoadLog(Long logId);

	/**
	 * Updates a TLoadLog entity object in to the persistent store
	 * 
	 * @param tLoadLog
	 *            TLoadLog Entity object to be updated
	 * @return tLoadLog Persisted TLoadLog object
	 */
	TLoadLog updateTLoadLog(TLoadLog tLoadLog);

	/**
	 * Retrieve an TLoadLog object based on given logId.
	 * 
	 * @param logId
	 *            the primary key value of the TLoadLog Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TLoadLog findTLoadLogById(Long logId);

	/**
	 * Retrieve TLoadLog based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TLoadLog> list of TLoadLogs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TLoadLog> findTLoadLogs(SearchFilter<TLoadLog> searchFilter);

	/**
	 * Count TLoadLog based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTLoadLogs(SearchFilter<TLoadLog> searchFilter);

	/**
	 * Retrieve TLoadLog based on given search criteria using JPA named Query.
	 * The search criteria is of TTerrZipLoad type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TLoadLog> list of TLoadLogs if it exists against given
	 *         criteria. Returns null if not found
	 */
	//List<TLoadLog> getTLoadLogsByTTerrZipLoad(SearchFilter<TTerrZipLoad> searchFilter);

	/**
	 * Count TLoadLog based on given search criteria using JPA named Query.
	 * The search criteria is of TTerrZipLoad type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	//Object countTLoadLogsByTTerrZipLoad(SearchFilter<TTerrZipLoad> searchFilter);
	
	
	List<TLoadLog> findTLoadLogsBYDataSetID(Integer dataSetID  , short tenentID);

}
