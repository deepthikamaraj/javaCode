package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TComm;
import com.cognizant.opserv.sp.core.entity.TCommTargetRecipient;
import com.cognizant.opserv.sp.core.entity.TCommTargetRecipientId;
import com.cognizant.opserv.sp.core.entity.TPers;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TCommTargetRecipient DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TCommTargetRecipientDAO {

	/**
	 * Stores a new TCommTargetRecipient entity object in to the persistent store
	 * 
	 * @param tCommTargetRecipient
	 *            TCommTargetRecipient Entity object to be persisted
	 * @return tCommTargetRecipient Persisted TCommTargetRecipient object
	 */
	TCommTargetRecipient createTCommTargetRecipient(TCommTargetRecipient tCommTargetRecipient);

	/**
	 * Deletes a TCommTargetRecipient entity object from the persistent store
	 * 
	 * @param tCommTargetRecipient
	 *            TCommTargetRecipient Entity object to be deleted
	 */
	void deleteTCommTargetRecipient(TCommTargetRecipientId tCommTargetRecipientId);

	/**
	 * Updates a TCommTargetRecipient entity object in to the persistent store
	 * 
	 * @param tCommTargetRecipient
	 *            TCommTargetRecipient Entity object to be updated
	 * @return tCommTargetRecipient Persisted TCommTargetRecipient object
	 */
	TCommTargetRecipient updateTCommTargetRecipient(TCommTargetRecipient tCommTargetRecipient);

	/**
	 * Retrieve an TCommTargetRecipient object based on given TCommTargetRecipientId.
	 * 
	 * @param tCommTargetRecipientId
	 *            the primary key value of the TCommTargetRecipient Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TCommTargetRecipient findTCommTargetRecipientById(TCommTargetRecipientId tCommTargetRecipientId);

	/**
	 * Retrieve TCommTargetRecipient based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCommTargetRecipient> list of TCommTargetRecipients if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TCommTargetRecipient> findTCommTargetRecipients(SearchFilter<TCommTargetRecipient> searchFilter);

	/**
	 * Count TCommTargetRecipient based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTCommTargetRecipients(SearchFilter<TCommTargetRecipient> searchFilter);

	/**
	 * Retrieve TCommTargetRecipient based on given search criteria using JPA named Query.
	 * The search criteria is of TPers type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCommTargetRecipient> list of TCommTargetRecipients if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TCommTargetRecipient> getTCommTargetRecipientsByTPers(SearchFilter<TPers> searchFilter);

	/**
	 * Count TCommTargetRecipient based on given search criteria using JPA named Query.
	 * The search criteria is of TPers type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTCommTargetRecipientsByTPers(SearchFilter<TPers> searchFilter);

	/**
	 * Retrieve TCommTargetRecipient based on given search criteria using JPA named Query.
	 * The search criteria is of TComm type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCommTargetRecipient> list of TCommTargetRecipients if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TCommTargetRecipient> getTCommTargetRecipientsByTComm(SearchFilter<TComm> searchFilter);

	/**
	 * Count TCommTargetRecipient based on given search criteria using JPA named Query.
	 * The search criteria is of TComm type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTCommTargetRecipientsByTComm(SearchFilter<TComm> searchFilter);
	

	/**
	 * Gets the t comm target recipients by status date.
	 *
	 * @param searchFilter the search filter
	 * @return the t comm target recipients by status date
	 */
	List<TCommTargetRecipient> getTCommTargetRecipientsByStatusDate(SearchFilter<TCommTargetRecipient> searchFilter);
	
	/**
	 * Creates the t comm target recipients.
	 *
	 * @param tCommTargetRecipients the t comm target recipients
	 * @return the list
	 */
	List<TCommTargetRecipient> createTCommTargetRecipients(List<TCommTargetRecipient> tCommTargetRecipients);
	
	 /**
 	 * Update t comm target receipient.
 	 *
 	 * @param tCommTargetRecipients the t comm target recipients
 	 * @return the list
 	 */
 	List<TCommTargetRecipient> updateTCommTargetReceipients( List<TCommTargetRecipient> tCommTargetRecipients);

}
