package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TExecutionStatus;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TExecutionStatus DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TExecutionStatusDAO {

	/**
	 * Stores a new TExecutionStatus entity object in to the persistent store
	 * 
	 * @param tExecutionStatus
	 *            TExecutionStatus Entity object to be persisted
	 * @return tExecutionStatus Persisted TExecutionStatus object
	 */
	TExecutionStatus createTExecutionStatus(TExecutionStatus tExecutionStatus);

	/**
	 * Deletes a TExecutionStatus entity object from the persistent store
	 * 
	 * @param tExecutionStatus
	 *            TExecutionStatus Entity object to be deleted
	 */
	void deleteTExecutionStatus(Integer executionStatusId);

	/**
	 * Updates a TExecutionStatus entity object in to the persistent store
	 * 
	 * @param tExecutionStatus
	 *            TExecutionStatus Entity object to be updated
	 * @return tExecutionStatus Persisted TExecutionStatus object
	 */
	TExecutionStatus updateTExecutionStatus(TExecutionStatus tExecutionStatus);

	/**
	 * Retrieve an TExecutionStatus object based on given executionStatusId.
	 * 
	 * @param executionStatusId
	 *            the primary key value of the TExecutionStatus Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TExecutionStatus findTExecutionStatusById(Integer executionStatusId);

	/**
	 * Retrieve TExecutionStatus based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TExecutionStatus> list of TExecutionStatuss if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TExecutionStatus> findTExecutionStatuss(SearchFilter<TExecutionStatus> searchFilter);

	/**
	 * Count TExecutionStatus based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTExecutionStatuss(SearchFilter<TExecutionStatus> searchFilter);

	TExecutionStatus findTExecutionStatusById(Integer executionStatusId, Short tenantId);

}
