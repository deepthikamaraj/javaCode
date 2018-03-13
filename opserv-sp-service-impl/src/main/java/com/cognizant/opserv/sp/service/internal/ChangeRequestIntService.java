package com.cognizant.opserv.sp.service.internal;

import com.cognizant.opserv.sp.exception.ChangeRequestServiceException;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.model.cr.ChangeRequest;
/**
 * ****************************************************************************
 * Interface Name: ChangeRequestIntService.
 * 
 * @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * @since 15/04/2016
 * *****************************************************************************
 */
public interface ChangeRequestIntService {

	
	/**
	 * To Find Existing ChangeRequest Id
	 * @param targetSalesPostion - SalesPosition
	 * @param sourceSalesPostion - SalesPosition
	 * @param userDetails - UserDetails
	 * @param triggerId - Integer
	 * @return TChngReq
	 */
	ChangeRequest findExistingChangeRequestId(SalesPosition targetSalesPostion, SalesPosition sourceSalesPostion, UserDetails userDetails, Integer triggerId);


	/**
	 * Update change request status.
	 *
	 * @param changeRequestId - the change request id
	 * @param statusId - the status id
	 * @param userDetails - the user details
	 * @throws ChangeRequestServiceException the change request service exception
	 */
	void updateChangeRequestStatus(long changeRequestId, Integer statusId, UserDetails userDetails ) throws ChangeRequestServiceException;

}