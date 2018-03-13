package com.cognizant.opserv.sp.persistence.dao.service;

import java.util.List;
import java.util.Map;

import com.cognizant.opserv.sp.core.entity.TChngreqOffline;
import com.cognizant.opserv.sp.model.OfflineRequest;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.model.cr.ChangeRequest;
import com.cognizant.peg.core.exception.OpservDataAccessException;

public interface ChangeRequestOfflineDAOService {
	
	
	/**
	 * Fetch temp cr info for offline status Initiated(1).
	 * 
	 * @param tempCRId
	 *            the temp cr id
	 * @param userDetails
	 *            the user details
	 * @return the temp cr
	 * @throws OpservDataAccessException
	 */
	OfflineRequest fetchtempCRInfo(Long tempCRId, UserDetails userDetails) throws OpservDataAccessException;
	
	List<TChngreqOffline> findTTempByChngreqId(ChangeRequest chnageRequest,UserDetails userDetails)throws OpservDataAccessException;
	
	/**
	 * checkStatusOfTChngreqOffline
	 * @param offlineID
	 * @param userDetails
	 * @return boolean
	 */
	public boolean checkStatusOfTChngreqOffline(Long offlineID, UserDetails userDetails)throws OpservDataAccessException;

	/**
	 * Update CR Offline Status
	 * 
	 * @param stausId
	 *            - staus Id
	 * @param crOfflineId
	 *            -cr Offline Id
	 * @param userDetails
	 *            -user Details
	 * @param failedReason
	 *            -failed Reason
	 */
	public void updateCROfflineStatus(Integer stausId, Long crOfflineId, Integer userId,
			Short tenantId, String failedReason) throws OpservDataAccessException;
	
	
	/**
	 * Checks for pending offline request.
	 *
	 * @param CrId the cr id
	 * @param tenantId the user details
	 * @return true, if successful
	 */
	public boolean hasPendingOfflineRequest(Long CrId, Short tenantId)throws OpservDataAccessException;
	
	/**
	 * Gets the off line statuses.
	 *
	 * @param changeRequest the change request
	 * @param userDetails the user details
	 * @return the off line statuses
	 * @throws OpservDataAccessException the opserv data access exception
	 */
	Map<Integer,Integer> getOffLineStatuses(ChangeRequest changeRequest, UserDetails userDetails) throws OpservDataAccessException;
	
	/**
	 * Find temp c rs by change request.
	 *
	 * @param changeRequest the change request
	 * @param userDetails the user details
	 * @return the list
	 * @throws OpservDataAccessException the opserv data access exception
	 */
	public List<OfflineRequest> findTempCRsByChangeRequest(ChangeRequest changeRequest,
			UserDetails userDetails) throws OpservDataAccessException;
	
}
