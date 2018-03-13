package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TCommStatus;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TCommStatus DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TCommStatusDAO {

	/**
	 * Stores a new TCommStatus entity object in to the persistent store
	 * 
	 * @param tCommStatus
	 *            TCommStatus Entity object to be persisted
	 * @return tCommStatus Persisted TCommStatus object
	 */
	TCommStatus createTCommStatus(TCommStatus tCommStatus);

	/**
	 * Deletes a TCommStatus entity object from the persistent store
	 * 
	 * @param tCommStatus
	 *            TCommStatus Entity object to be deleted
	 */
	void deleteTCommStatus(Integer commStatusId);

	/**
	 * Updates a TCommStatus entity object in to the persistent store
	 * 
	 * @param tCommStatus
	 *            TCommStatus Entity object to be updated
	 * @return tCommStatus Persisted TCommStatus object
	 */
	TCommStatus updateTCommStatus(TCommStatus tCommStatus);

	/**
	 * Retrieve an TCommStatus object based on given commStatusId.
	 * 
	 * @param commStatusId
	 *            the primary key value of the TCommStatus Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TCommStatus findTCommStatusById(Integer commStatusId);

	/**
	 * Retrieve TCommStatus based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCommStatus> list of TCommStatuss if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TCommStatus> findTCommStatuss(SearchFilter<TCommStatus> searchFilter);

	/**
	 * Count TCommStatus based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTCommStatuss(SearchFilter<TCommStatus> searchFilter);

}
