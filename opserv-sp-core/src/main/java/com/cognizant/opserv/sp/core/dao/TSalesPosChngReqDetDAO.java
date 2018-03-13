package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TSalesPosChngReqDet;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TSalesPosChngReqDet DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TSalesPosChngReqDetDAO {

	/**
	 * Stores a new TSalesPosChngReqDet entity object in to the persistent store
	 * 
	 * @param tSalesPosChngReqDet
	 *            TSalesPosChngReqDet Entity object to be persisted
	 * @return tSalesPosChngReqDet Persisted TSalesPosChngReqDet object
	 */
	TSalesPosChngReqDet createTSalesPosChngReqDet(
			TSalesPosChngReqDet tSalesPosChngReqDet);

	/**
	 * Deletes a TSalesPosChngReqDet entity object from the persistent store
	 * 
	 * @param tSalesPosChngReqDet
	 *            TSalesPosChngReqDet Entity object to be deleted
	 */
	void deleteTSalesPosChngReqDet(Long spChngReqId);

	/**
	 * Updates a TSalesPosChngReqDet entity object in to the persistent store
	 * 
	 * @param tSalesPosChngReqDet
	 *            TSalesPosChngReqDet Entity object to be updated
	 * @return tSalesPosChngReqDet Persisted TSalesPosChngReqDet object
	 */
	TSalesPosChngReqDet updateTSalesPosChngReqDet(
			TSalesPosChngReqDet tSalesPosChngReqDet);
	
	List<TSalesPosChngReqDet> updateTSalesPosChngReqDet(
			List<TSalesPosChngReqDet> tSalesPosChngReqDet);

	/**
	 * Retrieve an TSalesPosChngReqDet object based on given spChngReqId.
	 * 
	 * @param spChngReqId
	 *            the primary key value of the TSalesPosChngReqDet Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TSalesPosChngReqDet findTSalesPosChngReqDetById(Long spChngReqId);

	/**
	 * Retrieve TSalesPosChngReqDet based on given search criteria using Dynamic
	 * JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TSalesPosChngReqDet> list of TSalesPosChngReqDets if it
	 *         exists against given criteria. Returns null if not found
	 */
	List<TSalesPosChngReqDet> findTSalesPosChngReqDets(
			SearchFilter<TSalesPosChngReqDet> searchFilter);

	/**
	 * Count TSalesPosChngReqDet based on given search criteria using Dynamic
	 * JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTSalesPosChngReqDets(
			SearchFilter<TSalesPosChngReqDet> searchFilter);

//	/**
//	 * Retrieve TSalesPosChngReqDet based on given search criteria using JPA
//	 * named Query. The search criteria is of TChngReqDetail type.
//	 * 
//	 * @param searchFilter
//	 *            The query criteria and search filter conditions are set
//	 * @return List<TSalesPosChngReqDet> list of TSalesPosChngReqDets if it
//	 *         exists against given criteria. Returns null if not found
//	 */
//	List<TSalesPosChngReqDet> getTSalesPosChngReqDetsByTChngReqDetail(
//			SearchFilter<TChngReqDetail> searchFilter);
//
//	/**
//	 * Count TSalesPosChngReqDet based on given search criteria using JPA named
//	 * Query. The search criteria is of TChngReqDetail type.
//	 * 
//	 * @param searchFilter
//	 *            The query criteria and search filter conditions are set
//	 * @return a Object indicating the count
//	 */
//	Object countTSalesPosChngReqDetsByTChngReqDetail(
//			SearchFilter<TChngReqDetail> searchFilter);

	/* Added By 407986 */
	/**
	 * Gets the sales position req details.
	 * 
	 * @param params
	 *            the params
	 * @return the sales position req details
	 */
	List<Object> getSalesPositionReqDetails(List<Object> params);

	List<TSalesPosChngReqDet> fetchDetailList(List<Object> params, String Query);

	// Added For SP CR Locking
	Object getLockedSP(Long salesPositionId, Long salesHiearchyID,
			List<Integer> triggerList, List<Integer> statusList,
			String queryName, Short tenantId);

	// Added For CR Locking

	Object getSPPendingCR(Long salesPositionId, Long salesHiearchyID,
			List<Integer> triggerList, List<Integer> statusList,
			String queryName, Short tenantId);

	// Added For Zip-SP CR Locking

	List<Object[]> getSPPendingCRForZip(List<Long> salesPositionIds,
			List<Long> salesHiearchyIds, List<Integer> triggerList,
			List<Integer> statusList, String queryName, Short tenantId);

}
