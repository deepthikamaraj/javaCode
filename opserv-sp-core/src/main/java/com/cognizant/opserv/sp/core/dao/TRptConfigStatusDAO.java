package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TRptConfigStatus;
import com.cognizant.opserv.sp.core.entity.TRptConfigStatusId;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TRptConfigStatus DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TRptConfigStatusDAO {

	/**
	 * Stores a new TRptConfigStatus entity object in to the persistent store
	 * 
	 * @param tRptConfigStatus
	 *            TRptConfigStatus Entity object to be persisted
	 * @return tRptConfigStatus Persisted TRptConfigStatus object
	 */
	TRptConfigStatus createTRptConfigStatus(TRptConfigStatus tRptConfigStatus);

	/**
	 * Deletes a TRptConfigStatus entity object from the persistent store
	 * 
	 * @param tRptConfigStatus
	 *            TRptConfigStatus Entity object to be deleted
	 */
	void deleteTRptConfigStatus(Integer rptConfigStatusId);

	/**
	 * Updates a TRptConfigStatus entity object in to the persistent store
	 * 
	 * @param tRptConfigStatus
	 *            TRptConfigStatus Entity object to be updated
	 * @return tRptConfigStatus Persisted TRptConfigStatus object
	 */
	TRptConfigStatus updateTRptConfigStatus(TRptConfigStatus tRptConfigStatus);

	/**
	 * Retrieve an TRptConfigStatus object based on given rptConfigStatusId.
	 * 
	 * @param rptConfigStatusId
	 *            the primary key value of the TRptConfigStatus Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TRptConfigStatus findTRptConfigStatusById(TRptConfigStatusId rptConfigStatusId);

	/**
	 * Retrieve TRptConfigStatus based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRptConfigStatus> list of TRptConfigStatuss if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TRptConfigStatus> findTRptConfigStatuss(SearchFilter<TRptConfigStatus> searchFilter);

	/**
	 * Count TRptConfigStatus based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTRptConfigStatuss(SearchFilter<TRptConfigStatus> searchFilter);

	/**
	 * Retrieve TRptConfigStatus based on given search criteria using JPA named Query.
	 * The search criteria is of TRptConfigStatus type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRptConfigStatus> list of TRptConfigStatuss if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TRptConfigStatus> getTRptConfigStatussByTRptConfigStatus(SearchFilter<TRptConfigStatus> searchFilter);

	/**
	 * Count TRptConfigStatus based on given search criteria using JPA named Query.
	 * The search criteria is of TRptConfigStatus type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTRptConfigStatussByTRptConfigStatus(SearchFilter<TRptConfigStatus> searchFilter);

}
