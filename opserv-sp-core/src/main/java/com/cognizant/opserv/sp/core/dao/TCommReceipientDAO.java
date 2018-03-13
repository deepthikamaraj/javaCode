package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TAckStatus;
import com.cognizant.opserv.sp.core.entity.TComm;
import com.cognizant.opserv.sp.core.entity.TCommReceipient;
import com.cognizant.opserv.sp.core.entity.TCommReceipientId;
import com.cognizant.opserv.sp.core.entity.TNoteStatus;
import com.cognizant.opserv.sp.core.entity.TPers;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TCommReceipient DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TCommReceipientDAO {

	/**
	 * Stores a new TCommReceipient entity object in to the persistent store
	 * 
	 * @param tCommReceipient
	 *            TCommReceipient Entity object to be persisted
	 * @return tCommReceipient Persisted TCommReceipient object
	 */
	TCommReceipient createTCommReceipient(TCommReceipient tCommReceipient);

	/**
	 * Deletes a TCommReceipient entity object from the persistent store
	 * 
	 * @param tCommReceipient
	 *            TCommReceipient Entity object to be deleted
	 */
	void deleteTCommReceipient(TCommReceipientId tCommReceipientId);

	/**
	 * Updates a TCommReceipient entity object in to the persistent store
	 * 
	 * @param tCommReceipient
	 *            TCommReceipient Entity object to be updated
	 * @return tCommReceipient Persisted TCommReceipient object
	 */
	TCommReceipient updateTCommReceipient(TCommReceipient tCommReceipient);

	/**
	 * Retrieve an TCommReceipient object based on given TCommReceipientId.
	 * 
	 * @param tCommReceipientId
	 *            the primary key value of the TCommReceipient Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TCommReceipient findTCommReceipientById(TCommReceipientId tCommReceipientId);

	/**
	 * Retrieve TCommReceipient based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCommReceipient> list of TCommReceipients if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TCommReceipient> findTCommReceipients(SearchFilter<TCommReceipient> searchFilter);

	/**
	 * Count TCommReceipient based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTCommReceipients(SearchFilter<TCommReceipient> searchFilter);

	/**
	 * Retrieve TCommReceipient based on given search criteria using JPA named Query.
	 * The search criteria is of TNoteStatus type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCommReceipient> list of TCommReceipients if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TCommReceipient> getTCommReceipientsByTNoteStatus(SearchFilter<TNoteStatus> searchFilter);

	/**
	 * Count TCommReceipient based on given search criteria using JPA named Query.
	 * The search criteria is of TNoteStatus type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTCommReceipientsByTNoteStatus(SearchFilter<TNoteStatus> searchFilter);

	/**
	 * Retrieve TCommReceipient based on given search criteria using JPA named Query.
	 * The search criteria is of TPers type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCommReceipient> list of TCommReceipients if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TCommReceipient> getTCommReceipientsByTPers(SearchFilter<TPers> searchFilter);

	/**
	 * Count TCommReceipient based on given search criteria using JPA named Query.
	 * The search criteria is of TPers type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTCommReceipientsByTPers(SearchFilter<TPers> searchFilter);

	/**
	 * Retrieve TCommReceipient based on given search criteria using JPA named Query.
	 * The search criteria is of TComm type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCommReceipient> list of TCommReceipients if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TCommReceipient> getTCommReceipientsByTComm(SearchFilter<TComm> searchFilter);

	/**
	 * Count TCommReceipient based on given search criteria using JPA named Query.
	 * The search criteria is of TComm type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTCommReceipientsByTComm(SearchFilter<TComm> searchFilter);

	/**
	 * Retrieve TCommReceipient based on given search criteria using JPA named Query.
	 * The search criteria is of TAckStatus type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCommReceipient> list of TCommReceipients if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TCommReceipient> getTCommReceipientsByTAckStatus(SearchFilter<TAckStatus> searchFilter);

	/**
	 * Count TCommReceipient based on given search criteria using JPA named Query.
	 * The search criteria is of TAckStatus type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTCommReceipientsByTAckStatus(SearchFilter<TAckStatus> searchFilter);
	
	
	/**
	 * Gets the t comm recipients by status date.
	 *
	 * @param searchFilter the search filter
	 * @return the t comm recipients by status date
	 */
	List<TCommReceipient> getTCommRecipientsByStatusDate(final SearchFilter<TCommReceipient> searchFilter);

	/**
	 * Gets the t comms by month for loggedin.
	 *
	 * @param searchFilter the search filter
	 * @return the t comms by month for loggedin
	 */
	List<Object[]> getTCommsByMonthForLoggedin(SearchFilter<TCommReceipient> searchFilter);

	/**
	 * Gets the t comms by date.
	 *
	 * @param searchFilter the search filter
	 * @return the t comms by date
	 */
	List<Object[]> getTCommsByDate(
			SearchFilter<TCommReceipient> searchFilter);

	/**
	 * Gets the t comm recipients by ack value.
	 *
	 * @param searchFilter the search filter
	 * @return the t comm recipients by ack value
	 */
	List<TCommReceipient> getTCommRecipientsByAckValue(final SearchFilter<TCommReceipient> searchFilter);

	/**
	 * Gets the count t comm recipients by ack value.
	 *
	 * @param searchFilter the search filter
	 * @return the count t comm recipients by ack value
	 */
	List<Object> getCountTCommRecipientsByAckValue(SearchFilter<TCommReceipient> searchFilter);

	/**
	 * Gets the tcomm recepients by comm id and staff id.
	 *
	 * @param id the id
	 * @param staffId the staff id
	 * @param tenantId the tenant id
	 * @return the tcomm recepients by comm id and staff id
	 */
	List<TCommReceipient>  getTcommRecepientsByCommIDAndStaffId(Long id, Integer staffId, Short tenantId);

	/**
	 * Gets the count t comm recipients all.
	 *
	 * @param searchFilter the search filter
	 * @return the count t comm recipients all
	 */
	List<Object> getCountTCommRecipientsAll(SearchFilter<TCommReceipient> searchFilter);

	/**
	 * Gets the t comm recipients by status publ date.
	 *
	 * @param searchFilter the search filter
	 * @return the t comm recipients by status publ date
	 */
	List<Object[]> getTCommRecipientsByStatusPublDate(SearchFilter<TCommReceipient> searchFilter);

    /**
     * Gets the t comm recipients by dynamic data.
     *
     * @param query the query
     * @param queryParam the query param
     * @param index the index
     * @param maxresult the maxresult
     * @return the t comm recipients by dynamic data
     */
    List<TCommReceipient> getTCommRecipientsByDynamicData( StringBuilder query, List queryParam,  int index,  int maxresult);
    
    /**
     * Creates the t comm recipients.
     *
     * @param tCommRecipients the t comm recipients
     * @return the list
     */
    List<TCommReceipient> createTCommReceipients(List<TCommReceipient> tCommReceipients);
    
    /**
     * Update t comm receipient.
     *
     * @param tCommReceipients the t comm receipients
     * @return the list
     */
    List<TCommReceipient> updateTCommReceipients( List<TCommReceipient> tCommReceipients); 
  
}
