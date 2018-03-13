package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TExecutionStatus;
import com.cognizant.opserv.sp.core.entity.TRptSched;
import com.cognizant.opserv.sp.core.entity.TRptSchedExecution;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TRptSchedExecution DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TRptSchedExecutionDAO {

	/**
	 * Stores a new TRptSchedExecution entity object in to the persistent store
	 * 
	 * @param tRptSchedExecution
	 *            TRptSchedExecution Entity object to be persisted
	 * @return tRptSchedExecution Persisted TRptSchedExecution object
	 */
	TRptSchedExecution createTRptSchedExecution(TRptSchedExecution tRptSchedExecution);

	/**
	 * Deletes a TRptSchedExecution entity object from the persistent store
	 * 
	 * @param tRptSchedExecution
	 *            TRptSchedExecution Entity object to be deleted
	 */
	void deleteTRptSchedExecution(Integer schedExecutionId);

	/**
	 * Updates a TRptSchedExecution entity object in to the persistent store
	 * 
	 * @param tRptSchedExecution
	 *            TRptSchedExecution Entity object to be updated
	 * @return tRptSchedExecution Persisted TRptSchedExecution object
	 */
	TRptSchedExecution updateTRptSchedExecution(TRptSchedExecution tRptSchedExecution);

	/**
	 * Retrieve an TRptSchedExecution object based on given schedExecutionId.
	 * 
	 * @param schedExecutionId
	 *            the primary key value of the TRptSchedExecution Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TRptSchedExecution findTRptSchedExecutionById(Integer schedExecutionId);

	/**
	 * Retrieve TRptSchedExecution based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRptSchedExecution> list of TRptSchedExecutions if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TRptSchedExecution> findTRptSchedExecutions(SearchFilter<TRptSchedExecution> searchFilter);

	/**
	 * Count TRptSchedExecution based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTRptSchedExecutions(SearchFilter<TRptSchedExecution> searchFilter);

	/**
	 * Retrieve TRptSchedExecution based on given search criteria using JPA named Query.
	 * The search criteria is of TExecutionStatus type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRptSchedExecution> list of TRptSchedExecutions if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TRptSchedExecution> getTRptSchedExecutionsByTExecutionStatus(SearchFilter<TExecutionStatus> searchFilter);

	/**
	 * Count TRptSchedExecution based on given search criteria using JPA named Query.
	 * The search criteria is of TExecutionStatus type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTRptSchedExecutionsByTExecutionStatus(SearchFilter<TExecutionStatus> searchFilter);

	/**
	 * Retrieve TRptSchedExecution based on given search criteria using JPA named Query.
	 * The search criteria is of TRptSched type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRptSchedExecution> list of TRptSchedExecutions if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TRptSchedExecution> getTRptSchedExecutionsByTRptSched(SearchFilter<TRptSched> searchFilter);

	/**
	 * Count TRptSchedExecution based on given search criteria using JPA named Query.
	 * The search criteria is of TRptSched type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTRptSchedExecutionsByTRptSched(SearchFilter<TRptSched> searchFilter);

}
