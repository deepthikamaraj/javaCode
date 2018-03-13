package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TBussReason;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TBussReason DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TBussReasonDAO {

	/**
	 * Stores a new TBussReason entity object in to the persistent store
	 * 
	 * @param tBussReason
	 *            TBussReason Entity object to be persisted
	 * @return tBussReason Persisted TBussReason object
	 */
	TBussReason createTBussReason(TBussReason tBussReason);

	/**
	 * Deletes a TBussReason entity object from the persistent store
	 * 
	 * @param tBussReason
	 *            TBussReason Entity object to be deleted
	 */
	void deleteTBussReason(Integer bussReasonId);

	/**
	 * Updates a TBussReason entity object in to the persistent store
	 * 
	 * @param tBussReason
	 *            TBussReason Entity object to be updated
	 * @return tBussReason Persisted TBussReason object
	 */
	TBussReason updateTBussReason(TBussReason tBussReason);

	/**
	 * Retrieve an TBussReason object based on given bussReasonId.
	 * 
	 * @param bussReasonId
	 *            the primary key value of the TBussReason Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TBussReason findTBussReasonById(Integer bussReasonId);

	/**
	 * Retrieve TBussReason based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TBussReason> list of TBussReasons if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TBussReason> findTBussReasons(SearchFilter<TBussReason> searchFilter);

	/**
	 * Count TBussReason based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTBussReasons(SearchFilter<TBussReason> searchFilter);
	
	/**
	 * Retrieve an TBusinessReason object based on given alignment and flow.
	 * 
	 * @param alignment, buss unit, sales team 
	 * 			  values of the TBussReason Entity.
	 * @param trTypeId
	 *            value of the TChngReqTrigger Entity.
	 * @param custCategoryId
	 *            value of the TCustCategory Entity.
	 * @return List of Objects if it exists against given criteria. Returns null of
	 *         not found
	 */
	List<Object[]> findTBussReasons(Long algmntId, Long bussUnitId, Long salesTeamId, Integer trTypeId, Integer custCategoryId, Short tenantId);

}