package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TNoteStatus;
import com.cognizant.opserv.sp.core.entity.TNoteStatusId;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TNoteStatus DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TNoteStatusDAO {

	/**
	 * Stores a new TNoteStatus entity object in to the persistent store
	 * 
	 * @param tNoteStatus
	 *            TNoteStatus Entity object to be persisted
	 * @return tNoteStatus Persisted TNoteStatus object
	 */
	TNoteStatus createTNoteStatus(TNoteStatus tNoteStatus);

	/**
	 * Deletes a TNoteStatus entity object from the persistent store
	 * 
	 * @param tNoteStatus
	 *            TNoteStatus Entity object to be deleted
	 */
	void deleteTNoteStatus(Integer noteStatusId);

	/**
	 * Updates a TNoteStatus entity object in to the persistent store
	 * 
	 * @param tNoteStatus
	 *            TNoteStatus Entity object to be updated
	 * @return tNoteStatus Persisted TNoteStatus object
	 */
	TNoteStatus updateTNoteStatus(TNoteStatus tNoteStatus);

	/**
	 * Retrieve an TNoteStatus object based on given noteStatusId.
	 * 
	 * @param noteStatusId
	 *            the primary key value of the TNoteStatus Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TNoteStatus findTNoteStatusById(TNoteStatusId noteStatusId);

	/**
	 * Retrieve TNoteStatus based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TNoteStatus> list of TNoteStatuss if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TNoteStatus> findTNoteStatuss(SearchFilter<TNoteStatus> searchFilter);

	/**
	 * Count TNoteStatus based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTNoteStatuss(SearchFilter<TNoteStatus> searchFilter);

}
