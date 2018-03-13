package com.cognizant.opserv.sp.core.dao;

import java.util.Date;
import java.util.List;

import com.cognizant.opserv.sp.core.entity.TPers;
import com.cognizant.opserv.sp.core.entity.TPubType;
import com.cognizant.opserv.sp.core.entity.TTargetRecipientPref;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TTargetRecipientPref DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TTargetRecipientPrefDAO {

	/**
	 * Stores a new TTargetRecipientPref entity object in to the persistent store
	 * 
	 * @param tTargetRecipientPref
	 *            TTargetRecipientPref Entity object to be persisted
	 * @return tTargetRecipientPref Persisted TTargetRecipientPref object
	 */
	TTargetRecipientPref createTTargetRecipientPref(TTargetRecipientPref tTargetRecipientPref);

	/**
	 * Deletes a TTargetRecipientPref entity object from the persistent store
	 * 
	 * @param tTargetRecipientPref
	 *            TTargetRecipientPref Entity object to be deleted
	 */
	void deleteTTargetRecipientPref(Integer recipientPrefId);

	/**
	 * Updates a TTargetRecipientPref entity object in to the persistent store
	 * 
	 * @param tTargetRecipientPref
	 *            TTargetRecipientPref Entity object to be updated
	 * @return tTargetRecipientPref Persisted TTargetRecipientPref object
	 */
	TTargetRecipientPref updateTTargetRecipientPref(TTargetRecipientPref tTargetRecipientPref);

	/**
	 * Retrieve an TTargetRecipientPref object based on given recipientPrefId.
	 * 
	 * @param recipientPrefId
	 *            the primary key value of the TTargetRecipientPref Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TTargetRecipientPref findTTargetRecipientPrefById(Integer recipientPrefId);

	/**
	 * Retrieve TTargetRecipientPref based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TTargetRecipientPref> list of TTargetRecipientPrefs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TTargetRecipientPref> findTTargetRecipientPrefs(SearchFilter<TTargetRecipientPref> searchFilter);

	/**
	 * Count TTargetRecipientPref based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTTargetRecipientPrefs(SearchFilter<TTargetRecipientPref> searchFilter);

	/**
	 * Retrieve TTargetRecipientPref based on given search criteria using JPA named Query.
	 * The search criteria is of TPers type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TTargetRecipientPref> list of TTargetRecipientPrefs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TTargetRecipientPref> getTTargetRecipientPrefsByTPers(SearchFilter<TPers> searchFilter);

	/**
	 * Count TTargetRecipientPref based on given search criteria using JPA named Query.
	 * The search criteria is of TPers type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTTargetRecipientPrefsByTPers(SearchFilter<TPers> searchFilter);

	/**
	 * Retrieve TTargetRecipientPref based on given search criteria using JPA named Query.
	 * The search criteria is of TPubType type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TTargetRecipientPref> list of TTargetRecipientPrefs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TTargetRecipientPref> getTTargetRecipientPrefsByTPubType(SearchFilter<TPubType> searchFilter);

	/**
	 * Count TTargetRecipientPref based on given search criteria using JPA named Query.
	 * The search criteria is of TPubType type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTTargetRecipientPrefsByTPubType(SearchFilter<TPubType> searchFilter);
	
	List<TTargetRecipientPref> getTCommTargetRecipientsByHistory(final SearchFilter<TTargetRecipientPref> searchFilter,Date fromDate, Date toDate);
	
	/**
	 * Creates the t comm target recipients.
	 *
	 * @param tTargetRecipientPrefs the t target recipient prefs
	 * @return the list
	 */
	List<TTargetRecipientPref> createTCommTargetRecipients(List<TTargetRecipientPref> tTargetRecipientPrefs);

}
