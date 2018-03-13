package com.cognizant.opserv.sp.cr.process.internal.service;



import java.util.Map;

import com.cognizant.opserv.sp.common.ChangeRequestOfflineStatusType;
import com.cognizant.opserv.sp.cr.process.dto.CustomerAlignmentDTO;
import com.cognizant.opserv.sp.cr.process.dto.GeographyAlignmentDTO;
import com.cognizant.opserv.sp.event.messages.OfflineRequestMessage;
import com.cognizant.opserv.sp.exception.AlignmentServiceException;
import com.cognizant.opserv.sp.exception.ChangeRequestServiceException;
import com.cognizant.opserv.sp.model.CustomerAlignment;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.model.cr.ChangeRequest;

/**
 * The Interface OfflineRequestService.
 */
public interface OfflineRequestService {
	
	/**
	 * Construct the Customer Alignment DTO from the Offline request. This DTO
	 * will be passed to subsequent services and collating necessary info and
	 * errors
	 * 
	 * @param message
	 * @param userDetails
	 * @return
	 * @throws AlignmentServiceException
	 */
	CustomerAlignmentDTO getCustomerOfflineRequest(OfflineRequestMessage message, UserDetails userDetails) throws AlignmentServiceException;
	
	GeographyAlignmentDTO getGeoOfflineRequest(OfflineRequestMessage message,UserDetails userDetails) throws AlignmentServiceException;

	/**
	 * Lock request for processing- update CR Offline Status to PROCESSING.
	 * 
	 * @param offlineId
	 *            the offline id
	 * @param userDetails
	 *            the user details
	 */
	void lockRequestForProcessing(Long offlineId, UserDetails userDetails) throws AlignmentServiceException;
	
	/**
	 * Update request on success - update CR Offline Status to COMPLETED..
	 *
	 * @param offlineId the offline id
	 * @param userDetails the user details
	 */
	void updateRequestOnSuccess(Long offlineId, UserDetails userDetails) throws AlignmentServiceException;
	
	/**
	 * Update request on error - update CR Offline Status to CANCELLED...
	 *
	 * @param offlineId the offline id
	 * @param userDetails the user details
	 */
	void updateRequestOnError(Long offlineId, UserDetails userDetails, String failedReason) throws AlignmentServiceException;
	
	/**
	 * Check if any offline items are pending for the given CR
	 * @param crId
	 * @param userDetails
	 * @return
	 */
	boolean hasPendingRequest(Long crId, UserDetails userDetails) throws AlignmentServiceException;
	
	
	/**
	 * Get the current status of the offline items of the CR 
	 * @param changeRequest
	 * @return
	 * @throws AlignmentServiceException
	 */
	Map<ChangeRequestOfflineStatusType,Integer> getOffLineStatuses(ChangeRequest changeRequest, UserDetails userDetails) throws ChangeRequestServiceException;
	
	
	/**
	 * Validate ass date overlapping.
	 * 
	 * @param newCustAlgn
	 *            the new cust algn
	 * @param orgCustAlgn
	 *            the org cust algn
	 * @param userDetails
	 *            the user details
	 * @throws AlignmentServiceException
	 *             the alignment service exception
	 */
	public void validateDateOverlapForTarCustAlgmnt(CustomerAlignment targetCustomerAlignment, UserDetails userDetails) throws AlignmentServiceException;
	
	
}
