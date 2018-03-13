package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TRejectReason;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TRejectReason DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TRejectReasonDAO {

	/**
	 * Stores a new TRejectReason entity object in to the persistent store
	 * 
	 * @param tRejectReason
	 *            TRejectReason Entity object to be persisted
	 * @return tRejectReason Persisted TRejectReason object
	 */
	TRejectReason createTRejectReason(TRejectReason tRejectReason);

	/**
	 * Deletes a TRejectReason entity object from the persistent store
	 * 
	 * @param tRejectReason
	 *            TRejectReason Entity object to be deleted
	 */
	void deleteTRejectReason(Integer rejectReasonId);

	/**
	 * Updates a TRejectReason entity object in to the persistent store
	 * 
	 * @param tRejectReason
	 *            TRejectReason Entity object to be updated
	 * @return tRejectReason Persisted TRejectReason object
	 */
	TRejectReason updateTRejectReason(TRejectReason tRejectReason);

	/**
	 * Retrieve an TRejectReason object based on given rejectReasonId.
	 * 
	 * @param rejectReasonId
	 *            the primary key value of the TRejectReason Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TRejectReason findTRejectReasonById(Integer rejectReasonId);

	/**
	 * Retrieve TRejectReason based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRejectReason> list of TRejectReasons if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TRejectReason> findTRejectReasons(SearchFilter<TRejectReason> searchFilter);

	/**
	 * Count TRejectReason based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTRejectReasons(SearchFilter<TRejectReason> searchFilter);

}
