package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TCustCallPlan;
import com.cognizant.opserv.sp.core.entity.TCustCallPlanChngReqDet;
import com.cognizant.opserv.sp.core.entity.TCustCallPlanChngReqDetId;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TCustCallPlanChngReqDet DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TCustCallPlanChngReqDetDAO {

	/**
	 * Stores a new TCustCallPlanChngReqDet entity object in to the persistent
	 * store
	 * 
	 * @param tCustCallPlanChngReqDet
	 *            TCustCallPlanChngReqDet Entity object to be persisted
	 * @return tCustCallPlanChngReqDet Persisted TCustCallPlanChngReqDet object
	 */
	TCustCallPlanChngReqDet createTCustCallPlanChngReqDet(
			TCustCallPlanChngReqDet tCustCallPlanChngReqDet);

	/**
	 * Deletes a TCustCallPlanChngReqDet entity object from the persistent store
	 * 
	 * @param tCustCallPlanChngReqDet
	 *            TCustCallPlanChngReqDet Entity object to be deleted
	 */
	void deleteTCustCallPlanChngReqDet(
			TCustCallPlanChngReqDetId tCustCallPlanChngReqDetId);

	/**
	 * Updates a TCustCallPlanChngReqDet entity object in to the persistent
	 * store
	 * 
	 * @param tCustCallPlanChngReqDet
	 *            TCustCallPlanChngReqDet Entity object to be updated
	 * @return tCustCallPlanChngReqDet Persisted TCustCallPlanChngReqDet object
	 */
	TCustCallPlanChngReqDet updateTCustCallPlanChngReqDet(
			TCustCallPlanChngReqDet tCustCallPlanChngReqDet);

	
	List<TCustCallPlanChngReqDet> updateTCustCallPlanChngReqDet(List<TCustCallPlanChngReqDet> tCustCallPlanChngReqDetList);
	/**
	 * Retrieve an TCustCallPlanChngReqDet object based on given
	 * TCustCallPlanChngReqDetId.
	 * 
	 * @param tCustCallPlanChngReqDetId
	 *            the primary key value of the TCustCallPlanChngReqDet Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TCustCallPlanChngReqDet findTCustCallPlanChngReqDetById(
			TCustCallPlanChngReqDetId tCustCallPlanChngReqDetId);

	/**
	 * Retrieve TCustCallPlanChngReqDet based on given search criteria using
	 * Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCustCallPlanChngReqDet> list of TCustCallPlanChngReqDets if
	 *         it exists against given criteria. Returns null if not found
	 */
	List<TCustCallPlanChngReqDet> findTCustCallPlanChngReqDets(
			SearchFilter<TCustCallPlanChngReqDet> searchFilter);

	/**
	 * Count TCustCallPlanChngReqDet based on given search criteria using
	 * Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTCustCallPlanChngReqDets(
			SearchFilter<TCustCallPlanChngReqDet> searchFilter);

//	/**
//	 * Retrieve TCustCallPlanChngReqDet based on given search criteria using JPA
//	 * named Query. The search criteria is of TChngReqDetail type.
//	 * 
//	 * @param searchFilter
//	 *            The query criteria and search filter conditions are set
//	 * @return List<TCustCallPlanChngReqDet> list of TCustCallPlanChngReqDets if
//	 *         it exists against given criteria. Returns null if not found
//	 */
//	List<TCustCallPlanChngReqDet> getTCustCallPlanChngReqDetsByTChngReqDetail(
//			SearchFilter<TChngReqDetail> searchFilter);

//	/**
//	 * Count TCustCallPlanChngReqDet based on given search criteria using JPA
//	 * named Query. The search criteria is of TChngReqDetail type.
//	 * 
//	 * @param searchFilter
//	 *            The query criteria and search filter conditions are set
//	 * @return a Object indicating the count
//	 */
//	Object countTCustCallPlanChngReqDetsByTChngReqDetail(
//			SearchFilter<TChngReqDetail> searchFilter);

	/**
	 * Retrieve TCustCallPlanChngReqDet based on given search criteria using JPA
	 * named Query. The search criteria is of TCustCallPlan type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCustCallPlanChngReqDet> list of TCustCallPlanChngReqDets if
	 *         it exists against given criteria. Returns null if not found
	 */
	List<TCustCallPlanChngReqDet> getTCustCallPlanChngReqDetsByTCustCallPlan(
			SearchFilter<TCustCallPlan> searchFilter);

	/**
	 * Count TCustCallPlanChngReqDet based on given search criteria using JPA
	 * named Query. The search criteria is of TCustCallPlan type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTCustCallPlanChngReqDetsByTCustCallPlan(
			SearchFilter<TCustCallPlan> searchFilter);

	/**
	 * Fetch detail list.
	 *
	 * @param params the params
	 * @param Query the query
	 * @return the list
	 */
	List<TCustCallPlanChngReqDet> fetchDetailList(List<Object> params,
			String Query);

	/* Added By 407986 To fetch all CustomerCallPlanReqDetails by ChngReqId */
	/**
	 * Gets the customer callplan req details.
	 * 
	 * @param params
	 *            the params
	 * @return the customer callplan req details
	 */

	List<Object> getCustomerCallPlanReqDetails(List params);

	/**
	 * Gets the cust call plan change req dtl.
	 *
	 * @param custCallPlanId the cust call plan id
	 * @param tenantId the tenant id
	 * @return the cust call plan change req dtl
	 */
	public TCustCallPlanChngReqDet getCustCallPlanChangeReqDtl(
			Integer custCallPlanId, Short tenantId);

	/**
	 * 
	 * @author 409793 to fetch customer id, if CR forAdd/Remove Customer
	 *         CallPlan is there on same SP for pending submission OR approval
	 *         status and Customer is present in that CR.
	 */
	List<Object[]> getCustPendingCR(List<Long> salesPositionId,
			List<Long> salesHiearchyID, List<Integer> triggerList,
			List<Integer> statusList, List<Integer> custId, Short tenantId);
	/**
	 * Gets the locked customer for callplan req details.
	 * 
	 * @param callPlanIds
	 *            the callPlanIds
	 * @Param statusList
	 *             statusList
	 *@Param tenantId
	 *           TenantId
	 * @return the Locked customer callplan 
	 */
	// Added By 407986 To fetch All the Locked Call Plans
	List<Object> fetchLockedCustCallPlan(List<Integer> callPlanIds,
			List<Integer> statusList, Short tenantId);
	/**
	 * Gets the locked customer for callplan req details.
	 * 
	 * @param callPlanIds
	 *            the callPlanIds
	 * @Param statusList
	 *             statusList
	 *@Param tenantId
	 *           TenantId
	 * @return the Locked customer callplan 
	 */
	// Added By 407986 To fetch All  Locked Customers For the Call Plan
			List<Object[]> fetchLockedCustByCallPlan(List<Integer> callPlanIds,
					List<Integer> statusList, Short tenantId);

}
