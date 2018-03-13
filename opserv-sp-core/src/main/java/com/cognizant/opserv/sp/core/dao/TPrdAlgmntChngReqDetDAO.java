package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TPrdAlgmnt;
import com.cognizant.opserv.sp.core.entity.TPrdAlgmntChngReqDet;
import com.cognizant.opserv.sp.core.entity.TPrdAlgmntChngReqDetId;
import com.cognizant.peg.core.common.SearchFilter;

// TODO: Auto-generated Javadoc
/**
 * Interface represents API's of TPrdAlgmntChngReqDet DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TPrdAlgmntChngReqDetDAO {

	/**
	 * Stores a new TPrdAlgmntChngReqDet entity object in to the persistent
	 * store.
	 * 
	 * @param tPrdAlgmntChngReqDet
	 *            TPrdAlgmntChngReqDet Entity object to be persisted
	 * @return tPrdAlgmntChngReqDet Persisted TPrdAlgmntChngReqDet object
	 */
	TPrdAlgmntChngReqDet createTPrdAlgmntChngReqDet(
			TPrdAlgmntChngReqDet tPrdAlgmntChngReqDet);

	/**
	 * Deletes a TPrdAlgmntChngReqDet entity object from the persistent store.
	 * 
	 * @param tPrdAlgmntChngReqDetId
	 *            the t prd algmnt chng req det id
	 */
	void deleteTPrdAlgmntChngReqDet(
			TPrdAlgmntChngReqDetId tPrdAlgmntChngReqDetId);

	/**
	 * Updates a TPrdAlgmntChngReqDet entity object in to the persistent store.
	 * 
	 * @param tPrdAlgmntChngReqDet
	 *            TPrdAlgmntChngReqDet Entity object to be updated
	 * @return tPrdAlgmntChngReqDet Persisted TPrdAlgmntChngReqDet object
	 */
	TPrdAlgmntChngReqDet updateTPrdAlgmntChngReqDet(
			TPrdAlgmntChngReqDet tPrdAlgmntChngReqDet);

	
	List<TPrdAlgmntChngReqDet> updateTPrdAlgmntChngReqDet(
			 List<TPrdAlgmntChngReqDet> tPrdAlgmntChngReqDetList);
	/**
	 * Retrieve an TPrdAlgmntChngReqDet object based on given
	 * TPrdAlgmntChngReqDetId.
	 * 
	 * @param tPrdAlgmntChngReqDetId
	 *            the primary key value of the TPrdAlgmntChngReqDet Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TPrdAlgmntChngReqDet findTPrdAlgmntChngReqDetById(
			TPrdAlgmntChngReqDetId tPrdAlgmntChngReqDetId);

	/**
	 * Retrieve TPrdAlgmntChngReqDet based on given search criteria using
	 * Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TPrdAlgmntChngReqDet> list of TPrdAlgmntChngReqDets if it
	 *         exists against given criteria. Returns null if not found
	 */
	List<TPrdAlgmntChngReqDet> findTPrdAlgmntChngReqDets(
			SearchFilter<TPrdAlgmntChngReqDet> searchFilter);

	/**
	 * Count TPrdAlgmntChngReqDet based on given search criteria using Dynamic
	 * JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTPrdAlgmntChngReqDets(
			SearchFilter<TPrdAlgmntChngReqDet> searchFilter);

	/**
	 * Retrieve TPrdAlgmntChngReqDet based on given search criteria using JPA
	 * named Query. The search criteria is of TPrdAlgmnt type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TPrdAlgmntChngReqDet> list of TPrdAlgmntChngReqDets if it
	 *         exists against given criteria. Returns null if not found
	 */
	List<TPrdAlgmntChngReqDet> getTPrdAlgmntChngReqDetsByTPrdAlgmnt(
			SearchFilter<TPrdAlgmnt> searchFilter);

	/**
	 * Count TPrdAlgmntChngReqDet based on given search criteria using JPA named
	 * Query. The search criteria is of TPrdAlgmnt type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTPrdAlgmntChngReqDetsByTPrdAlgmnt(
			SearchFilter<TPrdAlgmnt> searchFilter);

//	/**
//	 * Retrieve TPrdAlgmntChngReqDet based on given search criteria using JPA
//	 * named Query. The search criteria is of TChngReqDetail type.
//	 * 
//	 * @param searchFilter
//	 *            The query criteria and search filter conditions are set
//	 * @return List<TPrdAlgmntChngReqDet> list of TPrdAlgmntChngReqDets if it
//	 *         exists against given criteria. Returns null if not found
//	 */
//	List<TPrdAlgmntChngReqDet> getTPrdAlgmntChngReqDetsByTChngReqDetail(
//			SearchFilter<TChngReqDetail> searchFilter);
//
//	/**
//	 * Count TPrdAlgmntChngReqDet based on given search criteria using JPA named
//	 * Query. The search criteria is of TChngReqDetail type.
//	 * 
//	 * @param searchFilter
//	 *            The query criteria and search filter conditions are set
//	 * @return a Object indicating the count
//	 */
//	Object countTPrdAlgmntChngReqDetsByTChngReqDetail(
//			SearchFilter<TChngReqDetail> searchFilter);

	/* Added By 407986 */
	/**
	 * Gets the product req details.
	 * 
	 * @param params
	 *            the params
	 * @return the product req details
	 */
	List<Object> getProductReqDetails(List params);

	TPrdAlgmntChngReqDet getTPrdAlgmntChngReqDetsByPrdAlgmntId(Long prdAlgmntId);

	List<TPrdAlgmntChngReqDet> fetchDetailList(List<Object> params, String Query);

	List<Object[]> getPrdPendingCR(List<Long> salesPositionId,
			List<Long> salesHiearchyID, List<Integer> triggerList,
			List<Integer> statusList, List<String> prdId, Short tenantID);

	List<Object[]> getPrdPendingInCallPlan(List<Long> salesPositionId,
			List<Long> salesHiearchyID, List<Integer> triggerList,
			List<Integer> statusList, List<String> prdId,Short tenantID);

	List<Object[]> getPrdPendingInCallDir(List<Long> salesPositionId,
			List<Long> salesHiearchyID, List<Integer> triggerList,
			List<Integer> statusList, List<String> prdId,Short tenantID);

}
