package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TChngReq;
import com.cognizant.opserv.sp.core.entity.TChngreqOffline;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TChngreqOffline DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TChngreqOfflineDAO {

	/**
	 * Stores a new TChngreqOffline entity object in to the persistent store
	 * 
	 * @param TChngreqOffline
	 *            TChngreqOffline Entity object to be persisted
	 * @return TChngreqOffline Persisted TChngreqOffline object
	 */
	TChngreqOffline createTChngreqOffline(TChngreqOffline TChngreqOffline);

	/**
	 * Deletes a TChngreqOffline entity object from the persistent store
	 * 
	 * @param TChngreqOffline
	 *            TChngreqOffline Entity object to be deleted
	 */
	void deleteTChngreqOffline(Long offlineId);

	/**
	 * Updates a TChngreqOffline entity object in to the persistent store
	 * 
	 * @param TChngreqOffline
	 *            TChngreqOffline Entity object to be updated
	 * @return TChngreqOffline Persisted TChngreqOffline object
	 */
	TChngreqOffline updateTChngreqOffline(TChngreqOffline TChngreqOffline);

	/**
	 * Retrieve an TChngreqOffline object based on given offlineId.
	 * 
	 * @param offlineId
	 *            the primary key value of the TChngreqOffline Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TChngreqOffline findTChngreqOfflineById(Long offlineId);

	/**
	 * Retrieve TChngreqOffline based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TChngreqOffline> list of TChngreqOfflines if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TChngreqOffline> findTChngreqOfflines(SearchFilter<TChngreqOffline> searchFilter);

	/**
	 * Count TChngreqOffline based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTChngreqOfflines(SearchFilter<TChngreqOffline> searchFilter);

	/**
	 * Retrieve TChngreqOffline based on given search criteria using JPA named Query.
	 * The search criteria is of TChngReq type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TChngreqOffline> list of TChngreqOfflines if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TChngreqOffline> getTChngreqOfflinesByTChngReq(SearchFilter<TChngReq> searchFilter);

	/**
	 * Count TChngreqOffline based on given search criteria using JPA named Query.
	 * The search criteria is of TChngReq type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTChngreqOfflinesByTChngReq(SearchFilter<TChngReq> searchFilter);
	
	/**
	 * Retrieve TChngreqOffline based on CRID and Tenant Id
	 * 
	 * @param crID
	 * @param tenantId
	 * @return List<TChngreqOffline>
	 */
	List<TChngreqOffline> fetchTChngreqOffline(Long crID, short tenantId);

	/**
	 * Fetch temp cr info for offline status Initiated(1).
	 *
	 * @param tempCRId the temp cr id
	 * @param userDetails the user details
	 * @return the temp cr
	 */
	List<TChngreqOffline> fetchTChngreqOfflineByOfflineId(Long tempCrID,
			short tenantId);

	/**
	 * update TChngreqOffline By StatusId
	 * 
	 * @param stausId
	 *            -Status Id
	 * @param crOfflineId
	 *            - offline Cr Id
	 * @param userId
	 *            - user Id
	 * @param tenantId
	 *            - Tenant Id
	 * @param failedReason
	 *            - failed Reason
	 */
	public void updateTChngreqOfflineByStatusId(Integer stausId, Long crOfflineId, Integer userId, Short tenantId, String failedReason);
	
	/**
	 * update TChngreqOffline By StatusId
	 * @param stausId
	 * @param crOfflineId
	 * @param tenantId
	 */
	public long countOfOfflineCRStatus(Long CrId, Short tenantId);
	
	/**
	 * checkStatusOfTChngreqOffline
	 * @param offlineID
	 * @param tenantId
	 * @return Long
	 */
	Long checkStatusOfTChngreqOffline(Long offlineID, Short tenantId);
	
	/**
	 * Retrieve an TChngreqOffline object based on given statusId.
	 * 
	 * @param statusId
	 *            the status Id.
	 * @return List of Object if it exists against given Status Id. Returns null of
	 *         not found
	 */
	List<TChngreqOffline> getTChngreqOfflineByStatusId(Integer statusId, Short tenantId);

	/**
	 * Find status of t chngreq offline.
	 *
	 * @param offlineReqId the offline req id
	 * @param userDetails the user details
	 * @return the integer
	 */
	Integer findStatusOfTChngreqOffline(Long offlineID, Short tenantId);

}
