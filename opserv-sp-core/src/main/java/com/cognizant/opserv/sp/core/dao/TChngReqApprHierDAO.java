package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TChngReqApprHier;
import com.cognizant.opserv.sp.core.entity.TChngReqApprHierId;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TChngReqApprHier DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TChngReqApprHierDAO {

	/**
	 * Stores a new TChngReqApprHier entity object in to the persistent store
	 * 
	 * @param tChngReqApprHier
	 *            TChngReqApprHier Entity object to be persisted
	 * @return tChngReqApprHier Persisted TChngReqApprHier object
	 */
	TChngReqApprHier createTChngReqApprHier(TChngReqApprHier tChngReqApprHier);

	/**
	 * Deletes a TChngReqApprHier entity object from the persistent store
	 * 
	 * @param tChngReqApprHier
	 *            TChngReqApprHier Entity object to be deleted
	 */
	void deleteTChngReqApprHier(TChngReqApprHierId tChngReqApprHierId);

	/**
	 * Updates a TChngReqApprHier entity object in to the persistent store
	 * 
	 * @param tChngReqApprHier
	 *            TChngReqApprHier Entity object to be updated
	 * @return tChngReqApprHier Persisted TChngReqApprHier object
	 */
	TChngReqApprHier updateTChngReqApprHier(TChngReqApprHier tChngReqApprHier);

	/**
	 * Retrieve an TChngReqApprHier object based on given TChngReqApprHierId.
	 * 
	 * @param tChngReqApprHierId
	 *            the primary key value of the TChngReqApprHier Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TChngReqApprHier findTChngReqApprHierById(
			TChngReqApprHierId tChngReqApprHierId);

	/**
	 * Retrieve TChngReqApprHier based on given search criteria using Dynamic
	 * JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TChngReqApprHier> list of TChngReqApprHiers if it exists
	 *         against given criteria. Returns null if not found
	 */
	List<TChngReqApprHier> findTChngReqApprHiers(
			SearchFilter<TChngReqApprHier> searchFilter);

	/**
	 * Count TChngReqApprHier based on given search criteria using Dynamic
	 * JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTChngReqApprHiers(SearchFilter<TChngReqApprHier> searchFilter);

	// Added by 407986 To fetch partial Sales Hierarchy Ids
	/**
	 * Fetch partial sales hier ids.
	 *
	 * @param chngreqConfigId the chngreq config id
	 * @param tenantId the tenant id
	 * @return the list
	 */
	List<Object> fetchPartialSalesHierIds(Integer chngreqConfigId,
			short tenantId);

	/**
	 * Find sales hierarchy ids.
	 *
	 * @param chngreqConfigId the chngreq config id
	 * @param tenantId the tenant id
	 * @return the list
	 */
	List<TChngReqApprHier> findSalesHierarchyIds(Integer chngreqConfigId,
			 Short tenantId);

	/**
	 * Find activ sales hier.
	 *
	 * @param chngreqConfigId the chngreq config id
	 * @param activeFlag the active flag
	 * @param tenantId the tenant id
	 * @return the list
	 */
	List<Object> findActivSalesHier(Integer chngreqConfigId,Character activeFlag,Short tenantId);
}
