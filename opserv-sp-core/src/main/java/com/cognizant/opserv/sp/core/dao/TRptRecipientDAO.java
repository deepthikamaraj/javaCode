package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TAckStatus;
import com.cognizant.opserv.sp.core.entity.TNoteStatus;
import com.cognizant.opserv.sp.core.entity.TPers;
import com.cognizant.opserv.sp.core.entity.TRejectReason;
import com.cognizant.opserv.sp.core.entity.TRpt;
import com.cognizant.opserv.sp.core.entity.TRptRecipient;
import com.cognizant.opserv.sp.core.entity.TRptRecipientId;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TRptRecipient DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TRptRecipientDAO {

	/**
	 * Stores a new TRptRecipient entity object in to the persistent store
	 * 
	 * @param tRptRecipient
	 *            TRptRecipient Entity object to be persisted
	 * @return tRptRecipient Persisted TRptRecipient object
	 */
	TRptRecipient createTRptRecipient(TRptRecipient tRptRecipient);

	/**
	 * Deletes a TRptRecipient entity object from the persistent store
	 * 
	 * @param tRptRecipient
	 *            TRptRecipient Entity object to be deleted
	 */
	void deleteTRptRecipient(TRptRecipientId tRptRecipientId);

	/**
	 * Updates a TRptRecipient entity object in to the persistent store
	 * 
	 * @param tRptRecipient
	 *            TRptRecipient Entity object to be updated
	 * @return tRptRecipient Persisted TRptRecipient object
	 */
	TRptRecipient updateTRptRecipient(TRptRecipient tRptRecipient);

	/**
	 * Retrieve an TRptRecipient object based on given TRptRecipientId.
	 * 
	 * @param tRptRecipientId
	 *            the primary key value of the TRptRecipient Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TRptRecipient findTRptRecipientById(TRptRecipientId tRptRecipientId);

	/**
	 * Retrieve TRptRecipient based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRptRecipient> list of TRptRecipients if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TRptRecipient> findTRptRecipients(SearchFilter<TRptRecipient> searchFilter);

	/**
	 * Count TRptRecipient based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTRptRecipients(SearchFilter<TRptRecipient> searchFilter);

	/**
	 * Retrieve TRptRecipient based on given search criteria using JPA named Query.
	 * The search criteria is of TNoteStatus type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRptRecipient> list of TRptRecipients if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TRptRecipient> getTRptRecipientsByTNoteStatus(SearchFilter<TNoteStatus> searchFilter);

	/**
	 * Count TRptRecipient based on given search criteria using JPA named Query.
	 * The search criteria is of TNoteStatus type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTRptRecipientsByTNoteStatus(SearchFilter<TNoteStatus> searchFilter);

	/**
	 * Retrieve TRptRecipient based on given search criteria using JPA named Query.
	 * The search criteria is of TPers type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRptRecipient> list of TRptRecipients if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TRptRecipient> getTRptRecipientsByTPers(SearchFilter<TPers> searchFilter);

	/**
	 * Count TRptRecipient based on given search criteria using JPA named Query.
	 * The search criteria is of TPers type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTRptRecipientsByTPers(SearchFilter<TPers> searchFilter);

	/**
	 * Retrieve TRptRecipient based on given search criteria using JPA named Query.
	 * The search criteria is of TRpt type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRptRecipient> list of TRptRecipients if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TRptRecipient> getTRptRecipientsByTRpt(SearchFilter<TRpt> searchFilter);

	/**
	 * Count TRptRecipient based on given search criteria using JPA named Query.
	 * The search criteria is of TRpt type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTRptRecipientsByTRpt(SearchFilter<TRpt> searchFilter);

	/**
	 * Retrieve TRptRecipient based on given search criteria using JPA named Query.
	 * The search criteria is of TRejectReason type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRptRecipient> list of TRptRecipients if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TRptRecipient> getTRptRecipientsByTRejectReason(SearchFilter<TRejectReason> searchFilter);

	/**
	 * Count TRptRecipient based on given search criteria using JPA named Query.
	 * The search criteria is of TRejectReason type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTRptRecipientsByTRejectReason(SearchFilter<TRejectReason> searchFilter);

	/**
	 * Retrieve TRptRecipient based on given search criteria using JPA named Query.
	 * The search criteria is of TAckStatus type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRptRecipient> list of TRptRecipients if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TRptRecipient> getTRptRecipientsByTAckStatus(SearchFilter<TAckStatus> searchFilter);

	/**
	 * Count TRptRecipient based on given search criteria using JPA named Query.
	 * The search criteria is of TAckStatus type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTRptRecipientsByTAckStatus(SearchFilter<TAckStatus> searchFilter);
	
	List<TRptRecipient> findTRptRecipientsByMonth(final SearchFilter<TRptRecipient> searchFilter);
	
	/**
	 * Retrieve TRptRecipient based on given search criteria using JPA named Query.
	 * The search criteria is of TRptTypeId type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRptRecipient> list of TRptRecipients if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TRptRecipient> getTRptRecipientsByTRptType(Integer reportTypeId,Short tenantId);

	/**
	 * Retrieve TRptRecipient based on given search criteria using JPA named Query.
	 * The search criteria is of TRpt and TPers type.
	 * 
	 * @param tRpt
	 * @param tPers
	 * @return TRptRecipient if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TRptRecipient> getTRptRecipientByTRptAndTPers(TRpt tRpt, TPers tPers,int index,int maxResult);
	
	/**
	 * Retrieve Report design object based on given TRptRecipientId.
	 * The search criteria is of TRpt and TPers type.
	 * 
	 * @param tRptRecipientId
	 * 		the primary key value of the TRptRecipient Entity.
	 * 
	 *  @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	byte[] findRptDesignByTRptAndTPers(TRptRecipientId tRptRecipientId) ;

}
