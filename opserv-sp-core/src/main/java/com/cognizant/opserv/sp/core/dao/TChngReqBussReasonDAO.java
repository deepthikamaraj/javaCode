package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TBussReason;
import com.cognizant.opserv.sp.core.entity.TChngReqBussReason;
import com.cognizant.opserv.sp.core.entity.TChngReqTrigger;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TChngReqBussReason DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TChngReqBussReasonDAO {

	/**
	 * Stores a new TChngReqBussReason entity object in to the persistent store
	 * 
	 * @param tChngReqBussReason
	 *            TChngReqBussReason Entity object to be persisted
	 * @return tChngReqBussReason Persisted TChngReqBussReason object
	 */
	TChngReqBussReason createTChngReqBussReason(TChngReqBussReason tChngReqBussReason);

	/**
	 * Deletes a TChngReqBussReason entity object from the persistent store
	 * 
	 * @param tChngReqBussReason
	 *            TChngReqBussReason Entity object to be deleted
	 */
	void deleteTChngReqBussReason(Long chngReqBrId);

	/**
	 * Updates a TChngReqBussReason entity object in to the persistent store
	 * 
	 * @param tChngReqBussReason
	 *            TChngReqBussReason Entity object to be updated
	 * @return tChngReqBussReason Persisted TChngReqBussReason object
	 */
	TChngReqBussReason updateTChngReqBussReason(TChngReqBussReason tChngReqBussReason);

	/**
	 * Retrieve an TChngReqBussReason object based on given chngReqBrId.
	 * 
	 * @param chngReqBrId
	 *            the primary key value of the TChngReqBussReason Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TChngReqBussReason findTChngReqBussReasonById(Long chngReqBrId);

	/**
	 * Retrieve TChngReqBussReason based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TChngReqBussReason> list of TChngReqBussReasons if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TChngReqBussReason> findTChngReqBussReasons(SearchFilter<TChngReqBussReason> searchFilter);

	/**
	 * Count TChngReqBussReason based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTChngReqBussReasons(SearchFilter<TChngReqBussReason> searchFilter);

	/**
	 * Retrieve TChngReqBussReason based on given search criteria using JPA named Query.
	 * The search criteria is of TChngReqTrigger type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TChngReqBussReason> list of TChngReqBussReasons if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TChngReqBussReason> getTChngReqBussReasonsByTChngReqTrigger(SearchFilter<TChngReqTrigger> searchFilter);

	/**
	 * Count TChngReqBussReason based on given search criteria using JPA named Query.
	 * The search criteria is of TChngReqTrigger type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTChngReqBussReasonsByTChngReqTrigger(SearchFilter<TChngReqTrigger> searchFilter);

	/**
	 * Retrieve TChngReqBussReason based on given search criteria using JPA named Query.
	 * The search criteria is of TBussReason type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TChngReqBussReason> list of TChngReqBussReasons if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TChngReqBussReason> getTChngReqBussReasonsByTBussReason(SearchFilter<TBussReason> searchFilter);

	/**
	 * Count TChngReqBussReason based on given search criteria using JPA named Query.
	 * The search criteria is of TBussReason type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTChngReqBussReasonsByTBussReason(SearchFilter<TBussReason> searchFilter);

}