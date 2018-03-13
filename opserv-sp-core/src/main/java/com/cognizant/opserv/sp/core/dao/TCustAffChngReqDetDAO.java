package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TCustAff;
import com.cognizant.opserv.sp.core.entity.TCustAffChngReqDet;
import com.cognizant.opserv.sp.core.entity.TCustAffChngReqDetId;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TCustAffChngReqDet DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TCustAffChngReqDetDAO {

	/**
	 * Stores a new TCustAffChngReqDet entity object in to the persistent store
	 * 
	 * @param tCustAffChngReqDet
	 *            TCustAffChngReqDet Entity object to be persisted
	 * @return tCustAffChngReqDet Persisted TCustAffChngReqDet object
	 */
	TCustAffChngReqDet createTCustAffChngReqDet(TCustAffChngReqDet tCustAffChngReqDet);

	/**
	 * Deletes a TCustAffChngReqDet entity object from the persistent store
	 * 
	 * @param tCustAffChngReqDet
	 *            TCustAffChngReqDet Entity object to be deleted
	 */
	void deleteTCustAffChngReqDet(TCustAffChngReqDetId tCustAffChngReqDetId);

	/**
	 * Updates a TCustAffChngReqDet entity object in to the persistent store
	 * 
	 * @param tCustAffChngReqDet
	 *            TCustAffChngReqDet Entity object to be updated
	 * @return tCustAffChngReqDet Persisted TCustAffChngReqDet object
	 */
	TCustAffChngReqDet updateTCustAffChngReqDet(TCustAffChngReqDet tCustAffChngReqDet);
	
	List<TCustAffChngReqDet> updateTCustAffChngReqDet(List<TCustAffChngReqDet> tCustAffChngReqDetList);

	/**
	 * Retrieve an TCustAffChngReqDet object based on given TCustAffChngReqDetId.
	 * 
	 * @param tCustAffChngReqDetId
	 *            the primary key value of the TCustAffChngReqDet Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TCustAffChngReqDet findTCustAffChngReqDetById(TCustAffChngReqDetId tCustAffChngReqDetId);

	/**
	 * Retrieve TCustAffChngReqDet based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCustAffChngReqDet> list of TCustAffChngReqDets if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TCustAffChngReqDet> findTCustAffChngReqDets(SearchFilter<TCustAffChngReqDet> searchFilter);

	/**
	 * Count TCustAffChngReqDet based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTCustAffChngReqDets(SearchFilter<TCustAffChngReqDet> searchFilter);

	/**
	 * Retrieve TCustAffChngReqDet based on given search criteria using JPA named Query.
	 * The search criteria is of TCustAff type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCustAffChngReqDet> list of TCustAffChngReqDets if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TCustAffChngReqDet> getTCustAffChngReqDetsByTCustAff(SearchFilter<TCustAff> searchFilter);

	/**
	 * Count TCustAffChngReqDet based on given search criteria using JPA named Query.
	 * The search criteria is of TCustAff type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTCustAffChngReqDetsByTCustAff(SearchFilter<TCustAff> searchFilter);

//	/**
//	 * Retrieve TCustAffChngReqDet based on given search criteria using JPA named Query.
//	 * The search criteria is of TChngReqDetail type.
//	 * 
//	 * @param searchFilter
//	 *            The query criteria and search filter conditions are set
//	 * @return List<TCustAffChngReqDet> list of TCustAffChngReqDets if it exists against given
//	 *         criteria. Returns null if not found
//	 */
//	List<TCustAffChngReqDet> getTCustAffChngReqDetsByTChngReqDetail(SearchFilter<TChngReqDetail> searchFilter);
//
//	/**
//	 * Count TCustAffChngReqDet based on given search criteria using JPA named Query.
//	 * The search criteria is of TChngReqDetail type.
//	 * 
//	 * @param searchFilter
//	 *            The query criteria and search filter conditions are set
//	 * @return a Object indicating the count
//	 */
//	Object countTCustAffChngReqDetsByTChngReqDetail(SearchFilter<TChngReqDetail> searchFilter);
//		List<TCustAffChngReqDet> fetchDetailList(List<Object> params,String Query);

		
	List<TCustAffChngReqDet> getTCustAffChngReqDetsByCustAffId(Integer custAffId, Short tenantId);
	
	
	/*Added By 407986 To fetch all CustomerAffiliationReqDetails by ChngReqId  */
    /**
     * Gets the customer affiliation req details.
     *
     * @param params the params
     * @return the customer affiliation req details
     */
    List<Object> getCustomerAffiliationReqDetails(List<Object> params) ;
	
	
	/**
	 * 
	 * @author 409793 to fetch customer id, if CR forAdd/Remove Customer
	 *         Affiliation is there on same SP for pending submission OR
	 *         approval status and Customer is present in that CR.
	 */
	List<Object[]> getCustPendingCR(List<Long> salesPositionId,
			List<Long> salesHiearchyID, List<Integer> triggerList,
			List<Integer> statusList, List<Integer> custId,Short tenantId);
	
    /**
     * Gets the cust aff count.
     *
     * @param params the params
     * @param query the query
     * @return the cust aff count
     */
    public List<Object[]> getCustAffCount(List<Object> params,String query);
}
