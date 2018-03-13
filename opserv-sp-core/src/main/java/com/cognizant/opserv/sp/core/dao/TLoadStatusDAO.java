package com.cognizant.opserv.sp.core.dao;



import java.util.List;

import com.cognizant.opserv.sp.core.entity.TLoadStatus;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TLoadStatus DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TLoadStatusDAO {

	/**
	 * Stores a new TLoadStatus entity object in to the persistent store
	 * 
	 * @param tLoadStatus
	 *            TLoadStatus Entity object to be persisted
	 * @return tLoadStatus Persisted TLoadStatus object
	 */
	TLoadStatus createTLoadStatus(TLoadStatus tLoadStatus);

	/**
	 * Deletes a TLoadStatus entity object from the persistent store
	 * 
	 * @param tLoadStatus
	 *            TLoadStatus Entity object to be deleted
	 */
	void deleteTLoadStatus(Integer statusId);

	/**
	 * Updates a TLoadStatus entity object in to the persistent store
	 * 
	 * @param tLoadStatus
	 *            TLoadStatus Entity object to be updated
	 * @return tLoadStatus Persisted TLoadStatus object
	 */
	TLoadStatus updateTLoadStatus(TLoadStatus tLoadStatus);

	/**
	 * Retrieve an TLoadStatus object based on given statusId.
	 * 
	 * @param statusId
	 *            the primary key value of the TLoadStatus Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TLoadStatus findTLoadStatusById(Integer statusId);

	/**
	 * Retrieve TLoadStatus based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TLoadStatus> list of TLoadStatuss if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TLoadStatus> findTLoadStatuss(SearchFilter<TLoadStatus> searchFilter);

	/**
	 * Count TLoadStatus based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTLoadStatuss(SearchFilter<TLoadStatus> searchFilter);

}
