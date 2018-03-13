package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TCust;
import com.cognizant.opserv.sp.core.entity.TCustChngReqDet;
import com.cognizant.opserv.sp.core.entity.TCustChngReqDetId;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TCustChngReqDet DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TCustChngReqDetDAO {

	/**
	 * Stores a new TCustChngReqDet entity object in to the persistent store
	 * 
	 * @param tCustChngReqDet
	 *            TCustChngReqDet Entity object to be persisted
	 * @return tCustChngReqDet Persisted TCustChngReqDet object
	 */
	TCustChngReqDet createTCustChngReqDet(TCustChngReqDet tCustChngReqDet);

	/**
	 * Deletes a TCustChngReqDet entity object from the persistent store
	 * 
	 * @param tCustChngReqDet
	 *            TCustChngReqDet Entity object to be deleted
	 */
	void deleteTCustChngReqDet(TCustChngReqDetId tCustChngReqDetId);

	/**
	 * Updates a TCustChngReqDet entity object in to the persistent store
	 * 
	 * @param tCustChngReqDet
	 *            TCustChngReqDet Entity object to be updated
	 * @return tCustChngReqDet Persisted TCustChngReqDet object
	 */
	TCustChngReqDet updateTCustChngReqDet(TCustChngReqDet tCustChngReqDet);

	/**
	 * Retrieve an TCustChngReqDet object based on given TCustChngReqDetId.
	 * 
	 * @param tCustChngReqDetId
	 *            the primary key value of the TCustChngReqDet Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TCustChngReqDet findTCustChngReqDetById(TCustChngReqDetId tCustChngReqDetId);

	/**
	 * Retrieve TCustChngReqDet based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCustChngReqDet> list of TCustChngReqDets if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TCustChngReqDet> findTCustChngReqDets(SearchFilter<TCustChngReqDet> searchFilter);

	/**
	 * Count TCustChngReqDet based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTCustChngReqDets(SearchFilter<TCustChngReqDet> searchFilter);

//	/**
//	 * Retrieve TCustChngReqDet based on given search criteria using JPA named Query.
//	 * The search criteria is of TChngReqDetail type.
//	 * 
//	 * @param searchFilter
//	 *            The query criteria and search filter conditions are set
//	 * @return List<TCustChngReqDet> list of TCustChngReqDets if it exists against given
//	 *         criteria. Returns null if not found
//	 */
//	List<TCustChngReqDet> getTCustChngReqDetsByTChngReqDetail(SearchFilter<TChngReqDetail> searchFilter);
//
//	/**
//	 * Count TCustChngReqDet based on given search criteria using JPA named Query.
//	 * The search criteria is of TChngReqDetail type.
//	 * 
//	 * @param searchFilter
//	 *            The query criteria and search filter conditions are set
//	 * @return a Object indicating the count
//	 */
//	Object countTCustChngReqDetsByTChngReqDetail(SearchFilter<TChngReqDetail> searchFilter);

	/**
	 * Retrieve TCustChngReqDet based on given search criteria using JPA named Query.
	 * The search criteria is of TCust type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCustChngReqDet> list of TCustChngReqDets if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TCustChngReqDet> getTCustChngReqDetsByTCust(SearchFilter<TCust> searchFilter);

	/**
	 * Count TCustChngReqDet based on given search criteria using JPA named Query.
	 * The search criteria is of TCust type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTCustChngReqDetsByTCust(SearchFilter<TCust> searchFilter);

}
