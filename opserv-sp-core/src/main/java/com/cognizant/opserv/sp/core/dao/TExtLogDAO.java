package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TDsStg;
import com.cognizant.opserv.sp.core.entity.TExtLog;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TExtLog DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TExtLogDAO {

	/**
	 * Stores a new TExtLog entity object in to the persistent store
	 * 
	 * @param tExtLog
	 *            TExtLog Entity object to be persisted
	 * @return tExtLog Persisted TExtLog object
	 */
	TExtLog createTExtLog(TExtLog tExtLog);

	/**
	 * Deletes a TExtLog entity object from the persistent store
	 * 
	 * @param tExtLog
	 *            TExtLog Entity object to be deleted
	 */
	void deleteTExtLog(Long logId);

	/**
	 * Updates a TExtLog entity object in to the persistent store
	 * 
	 * @param tExtLog
	 *            TExtLog Entity object to be updated
	 * @return tExtLog Persisted TExtLog object
	 */
	TExtLog updateTExtLog(TExtLog tExtLog);

	/**
	 * Retrieve an TExtLog object based on given logId.
	 * 
	 * @param logId
	 *            the primary key value of the TExtLog Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TExtLog findTExtLogById(Long logId);

	/**
	 * Retrieve TExtLog based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TExtLog> list of TExtLogs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TExtLog> findTExtLogs(SearchFilter<TExtLog> searchFilter);

	/**
	 * Count TExtLog based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTExtLogs(SearchFilter<TExtLog> searchFilter);

	/**
	 * Retrieve TExtLog based on given search criteria using JPA named Query.
	 * The search criteria is of TDsStg type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TExtLog> list of TExtLogs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TExtLog> getTExtLogsByTDsStg(SearchFilter<TDsStg> searchFilter);

	/**
	 * Count TExtLog based on given search criteria using JPA named Query.
	 * The search criteria is of TDsStg type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTExtLogsByTDsStg(SearchFilter<TDsStg> searchFilter);

}
