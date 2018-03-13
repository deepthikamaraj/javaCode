package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TCustAlgmnt;
import com.cognizant.opserv.sp.core.entity.TCustAlgmntChngReqDet;
import com.cognizant.opserv.sp.core.entity.TCustAlgmntChngReqDetId;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TCustAlgmntChngReqDet DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TCustAlgmntChngReqDetDAO {

	/**
	 * Stores a new TCustAlgmntChngReqDet entity object in to the persistent
	 * store
	 * 
	 * @param tCustAlgmntChngReqDet
	 *            TCustAlgmntChngReqDet Entity object to be persisted
	 * @return tCustAlgmntChngReqDet Persisted TCustAlgmntChngReqDet object
	 */
	TCustAlgmntChngReqDet createTCustAlgmntChngReqDet(
			TCustAlgmntChngReqDet tCustAlgmntChngReqDet);

	/**
	 * Deletes a TCustAlgmntChngReqDet entity object from the persistent store
	 * 
	 * @param tCustAlgmntChngReqDet
	 *            TCustAlgmntChngReqDet Entity object to be deleted
	 */
	void deleteTCustAlgmntChngReqDet(
			TCustAlgmntChngReqDetId tCustAlgmntChngReqDetId);

	/**
	 * Updates a TCustAlgmntChngReqDet entity object in to the persistent store
	 * 
	 * @param tCustAlgmntChngReqDet
	 *            TCustAlgmntChngReqDet Entity object to be updated
	 * @return tCustAlgmntChngReqDet Persisted TCustAlgmntChngReqDet object
	 */
	TCustAlgmntChngReqDet updateTCustAlgmntChngReqDet(
			TCustAlgmntChngReqDet tCustAlgmntChngReqDet);

	
	 List<TCustAlgmntChngReqDet> updateTCustAlgmntChngReqDet(final List<TCustAlgmntChngReqDet> list);
	/**
	 * Retrieve an TCustAlgmntChngReqDet object based on given
	 * TCustAlgmntChngReqDetId.
	 * 
	 * @param tCustAlgmntChngReqDetId
	 *            the primary key value of the TCustAlgmntChngReqDet Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TCustAlgmntChngReqDet findTCustAlgmntChngReqDetById(
			TCustAlgmntChngReqDetId tCustAlgmntChngReqDetId);

	/**
	 * Retrieve TCustAlgmntChngReqDet based on given search criteria using
	 * Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCustAlgmntChngReqDet> list of TCustAlgmntChngReqDets if it
	 *         exists against given criteria. Returns null if not found
	 */
	List<TCustAlgmntChngReqDet> findTCustAlgmntChngReqDets(
			SearchFilter<TCustAlgmntChngReqDet> searchFilter);

	/**
	 * Count TCustAlgmntChngReqDet based on given search criteria using Dynamic
	 * JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTCustAlgmntChngReqDets(
			SearchFilter<TCustAlgmntChngReqDet> searchFilter);

	/**
	 * Retrieve TCustAlgmntChngReqDet based on given search criteria using JPA
	 * named Query. The search criteria is of TCustAlgmnt type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCustAlgmntChngReqDet> list of TCustAlgmntChngReqDets if it
	 *         exists against given criteria. Returns null if not found
	 */
	List<TCustAlgmntChngReqDet> getTCustAlgmntChngReqDetsByTCustAlgmnt(
			SearchFilter<TCustAlgmnt> searchFilter);

	/**
	 * Count TCustAlgmntChngReqDet based on given search criteria using JPA
	 * named Query. The search criteria is of TCustAlgmnt type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTCustAlgmntChngReqDetsByTCustAlgmnt(
			SearchFilter<TCustAlgmnt> searchFilter);

//	/**
//	 * Retrieve TCustAlgmntChngReqDet based on given search criteria using JPA
//	 * named Query. The search criteria is of TChngReqDetail type.
//	 * 
//	 * @param searchFilter
//	 *            The query criteria and search filter conditions are set
//	 * @return List<TCustAlgmntChngReqDet> list of TCustAlgmntChngReqDets if it
//	 *         exists against given criteria. Returns null if not found
//	 */
//	List<TCustAlgmntChngReqDet> getTCustAlgmntChngReqDetsByTChngReqDetail(
//			SearchFilter<TChngReqDetail> searchFilter);
//
//	/**
//	 * Count TCustAlgmntChngReqDet based on given search criteria using JPA
//	 * named Query. The search criteria is of TChngReqDetail type.
//	 * 
//	 * @param searchFilter
//	 *            The query criteria and search filter conditions are set
//	 * @return a Object indicating the count
//	 */
//	Object countTCustAlgmntChngReqDetsByTChngReqDetail(
//			SearchFilter<TChngReqDetail> searchFilter);

	List<TCustAlgmntChngReqDet> fetchDetailList(List<Object> params,
			String Query);

	public Integer getTCustAlgmntChngReqDetByCustAlgmntId(long custAlgmntId, Integer pendingSub, Integer pendingAppr);	

	/*Added By 407986*/
	/**
	 * Gets the customer req details.
	 *
	 * @param params the params
	 * @return the customer req details
	 */
	List<Object> getCustomerReqDetails(List<Object> params);
	
	List<Object[]> getCustomerCRStatus(List<Long> custAlgmntIdList, List<Integer> crStatusIdList, Short tenantId);
	
	/**
	 * Added by 409793 to fetch customer pending submission/approve CR customer
	 * id.
	 * 
	 * @param salesPositionId
	 * @param salesHiearchyID
	 * @param triggerList
	 * @param statusList
	 * @param custId
	 * @param queryName
	 * @return
	 */
	List<Object[]> getCustPendingCR(List<Long> salesPositionId,
			List<Long> salesHiearchyID, List<Integer> triggerList,
			List<Integer> statusList, List<Integer> custId,Short tenantId);

	/**
	 * Added by 409793 to fetch Customer Global Attribute pending
	 * submission/approve CR customer id.
	 * 
	 * @param triggerList
	 * @param statusList
	 * @param custId
	 * @return
	 */
	List<Object[]> getCustGlobalAttrPendingCR(List<Integer> triggerList,
			List<Integer> statusList, List<Integer> custId,Short tenantId);

	/**
	 * Added by 407986 to fetch Customer locked For Zip Movement pending
	 * submission/approve CR customer id.
	 * 
	 * @param 
	 * @param custIds
	 * @param statusList
	 * @return
	 */
    List<Object[]> getLockedCustomerForZip(List<Integer> custIds,
		List<Integer> statusList, Short tenantId) ;

	/**
	 * Gets the locked customer for zip movement.
	 *
	 * @param custIds the cust ids
	 * @param statusList the status list
	 * @param triggerIds the trigger ids
	 * @param tenantId the tenant id
	 * @return the locked customer for zip movement
	 */
	public List<Object[]> getLockedCustomerForZipMovement(List<Integer> custIds,
		List<Integer> statusList,	List<Integer> triggerIds, Short tenantId);
		
	/**
	 * Gets the cust info by algnmt id.
	 *
	 * @param custAlgmntId the cust algmnt id
	 * @return the cust info by algnmt id
	 */
	List<Object[]>  getCustInfoByAlgnmtId(List<Long> custAlgmntId);
		
	/**
	 * Creates the t cust algmnt chng req det.
	 *
	 * @param tCustAlgmntChngReqDetList the t cust algmnt chng req det list
	 * @return the list
	 */
	List<TCustAlgmntChngReqDet> createTCustAlgmntChngReqDet(List<TCustAlgmntChngReqDet> tCustAlgmntChngReqDetList);

	/**
	 * @param custAlgmntId
	 * @param changeRequestId
	 * @param statusId
	 * @param userDetails
	 */
	void updateCustAlgmntCRDetStatus(long custAlgmntId,long changeRequestId, Integer statusId, Integer userId, Short tenantId);
	
	List<Object[]> getAllCrIdInTCustAffChngReqDets(Short tenantId);

}


