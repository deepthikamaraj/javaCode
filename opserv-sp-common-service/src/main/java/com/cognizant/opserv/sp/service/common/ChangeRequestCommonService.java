package com.cognizant.opserv.sp.service.common;

import java.util.List;

import com.cognizant.opserv.sp.exception.AlignmentServiceException;
import com.cognizant.opserv.sp.exception.ChangeRequestServiceException;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.model.cr.ChangeRequest;

/**
 * Change Request Common Service.
 * This service will create new change request. Line items are not part of this service.
 * @author Cognizant Technology Solutions
 *
 */
public interface ChangeRequestCommonService {

	/**
	 * Generates a change request for push and associates with the parent CR if provided.
	 *
	 * @param parentCR the parent cr
	 * @param sourceSP the source sp
	 * @param targetSP the target sp
	 * @param userDetails the user details
	 * @return the change request
	 * @throws ChangeRequestServiceException the change request service exception
	 * @throws AlignmentServiceException 
	 */
	ChangeRequest generatePushCustomerChangeRequest(ChangeRequest parentCR,SalesPosition sourceSP, SalesPosition targetSP, UserDetails userDetails) throws ChangeRequestServiceException, AlignmentServiceException;

	/**
	 * Generates a change request for pull and associates with the parent CR if provided.
	 *
	 * @param parentCR the parent cr
	 * @param sourceSP the source sp
	 * @param targetSP the target sp
	 * @param userDetails the user details
	 * @return the change request
	 * @throws ChangeRequestServiceException the change request service exception
	 * @throws AlignmentServiceException 
	 */
	ChangeRequest generatePullCustomerChangeRequest(ChangeRequest parentCR,SalesPosition sourceSP, SalesPosition targetSP, UserDetails userDetails)throws ChangeRequestServiceException, AlignmentServiceException;

	/**
	 * Generates a change request for pull and associates with the parent CR if provided.
	 *
	 * @param parentCR the parent cr
	 * @param sourceSP the source sp
	 * @param targetSP the target sp
	 * @param userDetails the user details
	 * @return the change request
	 * @throws ChangeRequestServiceException the change request service exception
	 * @throws AlignmentServiceException 
	 */
	ChangeRequest generatePullCustomerChangeRequestSubmit(ChangeRequest parentCR,SalesPosition sourceSP, SalesPosition targetSP, UserDetails userDetails)throws ChangeRequestServiceException, AlignmentServiceException;
	
	/**
	 * Generates a change request for edit and associates with the parent CR if provided.
	 *
	 * @param parentCR the parent cr
	 * @param customerSP the customer sp
	 * @param userDetails the user details
	 * @return the change request
	 * @throws ChangeRequestServiceException the change request service exception
	 */
	ChangeRequest generateEditCustomerAlignmentChangeRequest(ChangeRequest parentCR,SalesPosition customerSP, UserDetails userDetails)throws ChangeRequestServiceException;

	/**
	 * Generates a change request for zip and associates with the parent CR if provided.
	 *
	 * @param parentCR the parent cr
	 * @param sourceSP the source sp
	 * @param targetSP the target sp
	 * @param userDetails the user details
	 * @return the change request
	 * @throws ChangeRequestServiceException the change request service exception
	 */
	ChangeRequest generateZipMovementChangeRequest(ChangeRequest parentCR,SalesPosition sourceSP, SalesPosition targetSP, UserDetails userDetails)throws ChangeRequestServiceException;
	
	/**
	 * Find mirror c rs.
	 *
	 * @param parentCR the parent cr
	 * @param userDetails the user details
	 * @return the list
	 * @throws ChangeRequestServiceException 
	 */
	List<ChangeRequest> findMirrorCRs(ChangeRequest parentCR,UserDetails userDetails) throws ChangeRequestServiceException;
	
	
	/**
	 * Auto reject change request on submit.
	 *
	 * @param changeReq the change req
	 * @param userDetails the user details
	 * @return true, if successful
	 * @throws ChangeRequestServiceException 
	 */
	boolean autoRejectChangeRequestOnSubmit(ChangeRequest changeReq, UserDetails userDetails) throws ChangeRequestServiceException;
	
}
