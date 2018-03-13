package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TPers;
import com.cognizant.opserv.sp.core.entity.TRptConfig;
import com.cognizant.opserv.sp.core.entity.TRptTargetRecipient;
import com.cognizant.opserv.sp.core.entity.TRptTargetRecipientId;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TRptTargetRecipient DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TRptTargetRecipientDAO {

	/**
	 * Stores a new TRptTargetRecipient entity object in to the persistent store
	 * 
	 * @param tRptTargetRecipient
	 *            TRptTargetRecipient Entity object to be persisted
	 * @return tRptTargetRecipient Persisted TRptTargetRecipient object
	 */
	TRptTargetRecipient createTRptTargetRecipient(TRptTargetRecipient tRptTargetRecipient);

	/**
	 * Deletes a TRptTargetRecipient entity object from the persistent store
	 * 
	 * @param tRptTargetRecipient
	 *            TRptTargetRecipient Entity object to be deleted
	 */
	void deleteTRptTargetRecipient(TRptTargetRecipientId tRptTargetRecipientId);

	/**
	 * Updates a TRptTargetRecipient entity object in to the persistent store
	 * 
	 * @param tRptTargetRecipient
	 *            TRptTargetRecipient Entity object to be updated
	 * @return tRptTargetRecipient Persisted TRptTargetRecipient object
	 */
	TRptTargetRecipient updateTRptTargetRecipient(TRptTargetRecipient tRptTargetRecipient);

	/**
	 * Retrieve an TRptTargetRecipient object based on given TRptTargetRecipientId.
	 * 
	 * @param tRptTargetRecipientId
	 *            the primary key value of the TRptTargetRecipient Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TRptTargetRecipient findTRptTargetRecipientById(TRptTargetRecipientId tRptTargetRecipientId);

	/**
	 * Retrieve TRptTargetRecipient based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRptTargetRecipient> list of TRptTargetRecipients if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TRptTargetRecipient> findTRptTargetRecipients(SearchFilter<TRptTargetRecipient> searchFilter);

	/**
	 * Count TRptTargetRecipient based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTRptTargetRecipients(SearchFilter<TRptTargetRecipient> searchFilter);

	/**
	 * Retrieve TRptTargetRecipient based on given search criteria using JPA named Query.
	 * The search criteria is of TPers type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRptTargetRecipient> list of TRptTargetRecipients if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TRptTargetRecipient> getTRptTargetRecipientsByTPers(SearchFilter<TPers> searchFilter);

	/**
	 * Count TRptTargetRecipient based on given search criteria using JPA named Query.
	 * The search criteria is of TPers type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTRptTargetRecipientsByTPers(SearchFilter<TPers> searchFilter);

	/**
	 * Retrieve TRptTargetRecipient based on given search criteria using JPA named Query.
	 * The search criteria is of TRptConfig type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRptTargetRecipient> list of TRptTargetRecipients if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TRptTargetRecipient> getTRptTargetRecipientsByTRptConfig(SearchFilter<TRptConfig> searchFilter);

	/**
	 * Count TRptTargetRecipient based on given search criteria using JPA named Query.
	 * The search criteria is of TRptConfig type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTRptTargetRecipientsByTRptConfig(SearchFilter<TRptConfig> searchFilter);

	List<Integer> findTargetRecipientsByConfigId(Integer rptConfigId,
			Short tenantId);
}
