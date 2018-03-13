package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TComm;
import com.cognizant.opserv.sp.core.entity.TCommPublisher;
import com.cognizant.opserv.sp.core.entity.TCommPublisherId;
import com.cognizant.opserv.sp.core.entity.TPers;
import com.cognizant.opserv.sp.core.entity.TTargetRecipientPref;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TCommPublisher DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TCommPublisherDAO {

	/**
	 * Stores a new TCommPublisher entity object in to the persistent store
	 * 
	 * @param tCommPublisher
	 *            TCommPublisher Entity object to be persisted
	 * @return tCommPublisher Persisted TCommPublisher object
	 */
	TCommPublisher createTCommPublisher(TCommPublisher tCommPublisher);

	/**
	 * Deletes a TCommPublisher entity object from the persistent store
	 * 
	 * @param tCommPublisher
	 *            TCommPublisher Entity object to be deleted
	 */
	void deleteTCommPublisher(TCommPublisherId tCommPublisherId);

	/**
	 * Updates a TCommPublisher entity object in to the persistent store
	 * 
	 * @param tCommPublisher
	 *            TCommPublisher Entity object to be updated
	 * @return tCommPublisher Persisted TCommPublisher object
	 */
	TCommPublisher updateTCommPublisher(TCommPublisher tCommPublisher);

	/**
	 * Retrieve an TCommPublisher object based on given TCommPublisherId.
	 * 
	 * @param tCommPublisherId
	 *            the primary key value of the TCommPublisher Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TCommPublisher findTCommPublisherById(TCommPublisherId tCommPublisherId);

	/**
	 * Retrieve TCommPublisher based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCommPublisher> list of TCommPublishers if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TCommPublisher> findTCommPublishers(SearchFilter<TCommPublisher> searchFilter);

	/**
	 * Count TCommPublisher based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTCommPublishers(SearchFilter<TCommPublisher> searchFilter);

	/**
	 * Retrieve TCommPublisher based on given search criteria using JPA named Query.
	 * The search criteria is of TTargetRecipientPref type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCommPublisher> list of TCommPublishers if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TCommPublisher> getTCommPublishersByTTargetRecipientPref(SearchFilter<TTargetRecipientPref> searchFilter);

	/**
	 * Count TCommPublisher based on given search criteria using JPA named Query.
	 * The search criteria is of TTargetRecipientPref type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTCommPublishersByTTargetRecipientPref(SearchFilter<TTargetRecipientPref> searchFilter);

	/**
	 * Retrieve TCommPublisher based on given search criteria using JPA named Query.
	 * The search criteria is of TPers type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCommPublisher> list of TCommPublishers if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TCommPublisher> getTCommPublishersByTPers(SearchFilter<TPers> searchFilter);

	/**
	 * Count TCommPublisher based on given search criteria using JPA named Query.
	 * The search criteria is of TPers type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTCommPublishersByTPers(SearchFilter<TPers> searchFilter);

	/**
	 * Retrieve TCommPublisher based on given search criteria using JPA named Query.
	 * The search criteria is of TComm type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCommPublisher> list of TCommPublishers if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TCommPublisher> getTCommPublishersByTComm(SearchFilter<TComm> searchFilter);

	/**
	 * Count TCommPublisher based on given search criteria using JPA named Query.
	 * The search criteria is of TComm type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTCommPublishersByTComm(SearchFilter<TComm> searchFilter);

}
