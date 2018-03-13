package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TPers;
import com.cognizant.opserv.sp.core.entity.TRpt;
import com.cognizant.opserv.sp.core.entity.TRptPublisher;
import com.cognizant.opserv.sp.core.entity.TRptPublisherId;
import com.cognizant.opserv.sp.core.entity.TTargetRecipientPref;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TRptPublisher DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TRptPublisherDAO {

	/**
	 * Stores a new TRptPublisher entity object in to the persistent store
	 * 
	 * @param tRptPublisher
	 *            TRptPublisher Entity object to be persisted
	 * @return tRptPublisher Persisted TRptPublisher object
	 */
	TRptPublisher createTRptPublisher(TRptPublisher tRptPublisher);

	/**
	 * Deletes a TRptPublisher entity object from the persistent store
	 * 
	 * @param tRptPublisher
	 *            TRptPublisher Entity object to be deleted
	 */
	void deleteTRptPublisher(TRptPublisherId tRptPublisherId);

	/**
	 * Updates a TRptPublisher entity object in to the persistent store
	 * 
	 * @param tRptPublisher
	 *            TRptPublisher Entity object to be updated
	 * @return tRptPublisher Persisted TRptPublisher object
	 */
	TRptPublisher updateTRptPublisher(TRptPublisher tRptPublisher);

	/**
	 * Retrieve an TRptPublisher object based on given TRptPublisherId.
	 * 
	 * @param tRptPublisherId
	 *            the primary key value of the TRptPublisher Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TRptPublisher findTRptPublisherById(TRptPublisherId tRptPublisherId);

	/**
	 * Retrieve TRptPublisher based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRptPublisher> list of TRptPublishers if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TRptPublisher> findTRptPublishers(SearchFilter<TRptPublisher> searchFilter);

	/**
	 * Count TRptPublisher based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTRptPublishers(SearchFilter<TRptPublisher> searchFilter);

	/**
	 * Retrieve TRptPublisher based on given search criteria using JPA named Query.
	 * The search criteria is of TTargetRecipientPref type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRptPublisher> list of TRptPublishers if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TRptPublisher> getTRptPublishersByTTargetRecipientPref(SearchFilter<TTargetRecipientPref> searchFilter);

	/**
	 * Count TRptPublisher based on given search criteria using JPA named Query.
	 * The search criteria is of TTargetRecipientPref type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTRptPublishersByTTargetRecipientPref(SearchFilter<TTargetRecipientPref> searchFilter);

	/**
	 * Retrieve TRptPublisher based on given search criteria using JPA named Query.
	 * The search criteria is of TPers type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRptPublisher> list of TRptPublishers if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TRptPublisher> getTRptPublishersByTPers(SearchFilter<TPers> searchFilter);

	/**
	 * Count TRptPublisher based on given search criteria using JPA named Query.
	 * The search criteria is of TPers type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTRptPublishersByTPers(SearchFilter<TPers> searchFilter);

	/**
	 * Retrieve TRptPublisher based on given search criteria using JPA named Query.
	 * The search criteria is of TRpt type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRptPublisher> list of TRptPublishers if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TRptPublisher> getTRptPublishersByTRpt(SearchFilter<TRpt> searchFilter);

	/**
	 * Count TRptPublisher based on given search criteria using JPA named Query.
	 * The search criteria is of TRpt type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTRptPublishersByTRpt(SearchFilter<TRpt> searchFilter);

}
