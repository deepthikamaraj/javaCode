package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TMsgType;
import com.cognizant.opserv.sp.core.entity.TMtrExec;
import com.cognizant.opserv.sp.core.entity.TMtrExecLog;
import com.cognizant.opserv.sp.core.entity.TMtrExecLogId;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TMtrExecLog DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TMtrExecLogDAO {

	/**
	 * Stores a new TMtrExecLog entity object in to the persistent store
	 * 
	 * @param tMtrExecLog
	 *            TMtrExecLog Entity object to be persisted
	 * @return tMtrExecLog Persisted TMtrExecLog object
	 */
	TMtrExecLog createTMtrExecLog(TMtrExecLog tMtrExecLog);

	/**
	 * Deletes a TMtrExecLog entity object from the persistent store
	 * 
	 * @param tMtrExecLog
	 *            TMtrExecLog Entity object to be deleted
	 */
	void deleteTMtrExecLog(TMtrExecLogId tMtrExecLogId);

	/**
	 * Updates a TMtrExecLog entity object in to the persistent store
	 * 
	 * @param tMtrExecLog
	 *            TMtrExecLog Entity object to be updated
	 * @return tMtrExecLog Persisted TMtrExecLog object
	 */
	TMtrExecLog updateTMtrExecLog(TMtrExecLog tMtrExecLog);

	/**
	 * Retrieve an TMtrExecLog object based on given TMtrExecLogId.
	 * 
	 * @param tMtrExecLogId
	 *            the primary key value of the TMtrExecLog Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TMtrExecLog findTMtrExecLogById(TMtrExecLogId tMtrExecLogId);

	/**
	 * Retrieve TMtrExecLog based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TMtrExecLog> list of TMtrExecLogs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TMtrExecLog> findTMtrExecLogs(SearchFilter<TMtrExecLog> searchFilter);

	/**
	 * Count TMtrExecLog based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTMtrExecLogs(SearchFilter<TMtrExecLog> searchFilter);

	/**
	 * Retrieve TMtrExecLog based on given search criteria using JPA named Query.
	 * The search criteria is of TMsgType type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TMtrExecLog> list of TMtrExecLogs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TMtrExecLog> getTMtrExecLogsByTMsgType(SearchFilter<TMsgType> searchFilter);

	/**
	 * Count TMtrExecLog based on given search criteria using JPA named Query.
	 * The search criteria is of TMsgType type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTMtrExecLogsByTMsgType(SearchFilter<TMsgType> searchFilter);

	/**
	 * Retrieve TMtrExecLog based on given search criteria using JPA named Query.
	 * The search criteria is of TMtrExec type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TMtrExecLog> list of TMtrExecLogs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TMtrExecLog> getTMtrExecLogsByTMtrExec(SearchFilter<TMtrExec> searchFilter);

	/**
	 * Count TMtrExecLog based on given search criteria using JPA named Query.
	 * The search criteria is of TMtrExec type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTMtrExecLogsByTMtrExec(SearchFilter<TMtrExec> searchFilter);

}
