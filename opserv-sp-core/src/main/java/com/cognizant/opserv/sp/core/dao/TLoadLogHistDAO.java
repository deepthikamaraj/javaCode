package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TLoadLog;
import com.cognizant.opserv.sp.core.entity.TLoadLogHist;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TLoadLogHist DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TLoadLogHistDAO {

	/**
	 * Stores a new TLoadLogHist entity object in to the persistent store
	 * 
	 * @param tLoadLogHist
	 *            TLoadLogHist Entity object to be persisted
	 * @return tLoadLogHist Persisted TLoadLogHist object
	 */
	TLoadLogHist createTLoadLogHist(TLoadLogHist tLoadLogHist);

	/**
	 * Deletes a TLoadLogHist entity object from the persistent store
	 * 
	 * @param tLoadLogHist
	 *            TLoadLogHist Entity object to be deleted
	 */
	void deleteTLoadLogHist(Integer revId);

	/**
	 * Updates a TLoadLogHist entity object in to the persistent store
	 * 
	 * @param tLoadLogHist
	 *            TLoadLogHist Entity object to be updated
	 * @return tLoadLogHist Persisted TLoadLogHist object
	 */
	TLoadLogHist updateTLoadLogHist(TLoadLogHist tLoadLogHist);

	/**
	 * Retrieve an TLoadLogHist object based on given revId.
	 * 
	 * @param revId
	 *            the primary key value of the TLoadLogHist Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TLoadLogHist findTLoadLogHistById(Integer revId);

	/**
	 * Retrieve TLoadLogHist based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TLoadLogHist> list of TLoadLogHists if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TLoadLogHist> findTLoadLogHists(SearchFilter<TLoadLogHist> searchFilter);

	/**
	 * Count TLoadLogHist based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTLoadLogHists(SearchFilter<TLoadLogHist> searchFilter);

	/**
	 * Retrieve TLoadLogHist based on given search criteria using JPA named Query.
	 * The search criteria is of TLoadLog type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TLoadLogHist> list of TLoadLogHists if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TLoadLogHist> getTLoadLogHistsByTLoadLog(SearchFilter<TLoadLog> searchFilter);

	/**
	 * Count TLoadLogHist based on given search criteria using JPA named Query.
	 * The search criteria is of TLoadLog type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTLoadLogHistsByTLoadLog(SearchFilter<TLoadLog> searchFilter);

}
