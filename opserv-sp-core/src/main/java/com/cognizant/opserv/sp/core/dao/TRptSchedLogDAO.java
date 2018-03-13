package com.cognizant.opserv.sp.core.dao;


import java.util.List;

import com.cognizant.opserv.sp.core.entity.TRptSchedLog;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TRptSchedLog DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TRptSchedLogDAO {

	/**
	 * Stores a new TRptSchedLog entity object in to the persistent store
	 * 
	 * @param tRptSchedLog
	 *            TRptSchedLog Entity object to be persisted
	 * @return tRptSchedLog Persisted TRptSchedLog object
	 */
	TRptSchedLog createTRptSchedLog(TRptSchedLog tRptSchedLog);

	/**
	 * Deletes a TRptSchedLog entity object from the persistent store
	 * 
	 * @param tRptSchedLog
	 *            TRptSchedLog Entity object to be deleted
	 */
	void deleteTRptSchedLog(Integer rptSchedId);

	/**
	 * Updates a TRptSchedLog entity object in to the persistent store
	 * 
	 * @param tRptSchedLog
	 *            TRptSchedLog Entity object to be updated
	 * @return tRptSchedLog Persisted TRptSchedLog object
	 */
	TRptSchedLog updateTRptSchedLog(TRptSchedLog tRptSchedLog);

	/**
	 * Retrieve an TRptSchedLog object based on given rptSchedId.
	 * 
	 * @param rptSchedId
	 *            the primary key value of the TRptSchedLog Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TRptSchedLog findTRptSchedLogById(Integer rptSchedId);

	/**
	 * Retrieve TRptSchedLog based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRptSchedLog> list of TRptSchedLogs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TRptSchedLog> findTRptSchedLogs(SearchFilter<TRptSchedLog> searchFilter);

	/**
	 * Count TRptSchedLog based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTRptSchedLogs(SearchFilter<TRptSchedLog> searchFilter);

	TRptSchedLog findTRptSchedLogById(Integer tRptSchedId, Short tenantId);

}
