package com.cognizant.opserv.sp.cr.process.internal.service;

import java.util.List;

import com.cognizant.opserv.sp.cr.process.dto.CustomerAlignmentDTO;
import com.cognizant.opserv.sp.cr.process.dto.GeographyAlignmentDTO;
import com.cognizant.opserv.sp.exception.AffiliationServiceException;
import com.cognizant.opserv.sp.exception.CallPlanServiceException;
import com.cognizant.opserv.sp.exception.ChangeRequestServiceException;
import com.cognizant.opserv.sp.exception.CustomerServiceException;
import com.cognizant.opserv.sp.exception.SalesPositionServiceException;
import com.cognizant.opserv.sp.model.PostalCode;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.model.cr.ChangeRequest;
import com.cognizant.opserv.sp.model.cr.CustomerAlignmentChangeRequestDetails;
import com.cognizant.opserv.sp.model.cr.ZipAlignmentChangeRequestDetails;

/**
 * Change Request Customer Service
 * This service will add line items to the CR.
 *
 * @author Cognizant
 */
public interface ChangeRequestOfflineService {

	/**
	 * Process line item for source customer.
	 * 
	 * @param crLineItems
	 *            the cr line items
	 * @param userDetails
	 *            the user details
	 * @throws ChangeRequestServiceException
	 *             the change request service exception
	 */
	void processLineItemForSourceCustomer(List<CustomerAlignmentChangeRequestDetails> crLineItems, UserDetails userDetails) throws ChangeRequestServiceException;
	
	/**
	 * Process line item for target customer.
	 * 
	 * @param crLineItems
	 *            the cr line items
	 * @param userDetails
	 *            the user details
	 * @throws ChangeRequestServiceException
	 *             the change request service exception
	 */
	void processLineItemForTargetCustomer(List<CustomerAlignmentChangeRequestDetails> crLineItems, UserDetails userDetails) throws ChangeRequestServiceException;
	
	/**
	 * Process line item for source zip.
	 * 
	 * @param crLineItems
	 *            the cr line items
	 * @param userDetails
	 *            the user details
	 * @throws ChangeRequestServiceException
	 *             the change request service exception
	 */
	void processLineItemForSourceZip(List<ZipAlignmentChangeRequestDetails> crLineItems, UserDetails userDetails) throws ChangeRequestServiceException;
	
	/**
	 * Process line item for target zip.
	 * 
	 * @param crLineItems
	 *            the cr line items
	 * @param userDetails
	 *            the user details
	 * @throws ChangeRequestServiceException
	 *             the change request service exception
	 */
	void processLineItemForTargetZip(List<ZipAlignmentChangeRequestDetails> crLineItems, UserDetails userDetails) throws ChangeRequestServiceException;
	
	/**
	 * Assign approvers for source.
	 * 
	 * @param chngReq
	 *            the chng req
	 * @param sourceSP
	 *            the source sp
	 * @param userDetails
	 *            the user details
	 * @throws ChangeRequestServiceException
	 *             the change request service exception
	 */
	void assignApproversForSource(ChangeRequest chngReq, SalesPosition sourceSP, UserDetails userDetails) throws ChangeRequestServiceException;
	
	/**
	 * Assign approvers for target.
	 * 
	 * @param chngReq
	 *            the chng req
	 * @param targetSP
	 *            the target sp
	 * @param userDetails
	 *            the user details
	 * @throws ChangeRequestServiceException
	 *             the change request service exception
	 */
	void assignApproversForTarget(ChangeRequest chngReq, SalesPosition targetSP, UserDetails userDetails) throws ChangeRequestServiceException;
	
	/**
	 * Update cr and initiate workflow.
	 * 
	 * @param custAlgmtDTO
	 *            the cust algmt dto
	 * @param userDetails
	 *            the user details
	 * @throws ChangeRequestServiceException
	 *             the change request service exception
	 */
	void updateCRandInitiateWorkflow(CustomerAlignmentDTO custAlgmtDTO, UserDetails userDetails) throws ChangeRequestServiceException;
	
	/**
	 * Update cr and initiate workflow.
	 * 
	 * @param geoAlgmtDTO
	 *            the geo algmt dto
	 * @param userDetails
	 *            the user details
	 * @throws ChangeRequestServiceException
	 *             the change request service exception
	 */
	void updateCRandInitiateWorkflow(GeographyAlignmentDTO geoAlgmtDTO, UserDetails userDetails) throws ChangeRequestServiceException;
	
	/**
	 * Approve change request.
	 * 
	 * @method submitChangeRequest
	 * @param chngReq the chng req
	 * @param userDetails the user details
	 * @throws ChangeRequestServiceException the change request service exception
	 * @method approveChangeRequest
	 */
	void approveChangeRequest(ChangeRequest chngReq, UserDetails userDetails,SalesPosition salesPosition) throws ChangeRequestServiceException, AffiliationServiceException, SalesPositionServiceException, CustomerServiceException, CallPlanServiceException;
	
	
	/**
	 * Reject change request.
	 * 
	 * @method submitChangeRequest
	 * @param chngReq the chng req
	 * @param userDetails the user details
	 * @throws ChangeRequestServiceException the change request service exception
	 * @method rejectChangeRequest
	 */
	void rejectChangeRequest(ChangeRequest chngReq, UserDetails userDetails,SalesPosition salesPosition) throws ChangeRequestServiceException, AffiliationServiceException, SalesPositionServiceException, CustomerServiceException, CallPlanServiceException;
	
	/**
	 * Approve change request Task.
	 * 
	 * @method approveChangeRequestTask
	 * @param chngReq the chng req
	 * @param userDetails the user details
	 * @param salesPosition the sales position
	 * @throws ChangeRequestServiceException the change request service exception
	 * @method approveChangeRequestTask
	 */
	void approveChangeRequestTask(ChangeRequest chngReq, UserDetails userDetails,SalesPosition salesPosition) throws ChangeRequestServiceException, AffiliationServiceException, SalesPositionServiceException, CustomerServiceException, CallPlanServiceException;
	
	/**
	 * Reject change request Task.
	 * 
	 * @method rejectChangeRequestTask
	 * @param chngReq the chng req
	 * @param userDetails the user details
	 * @param salesPosition the sales position
	 * @throws ChangeRequestServiceException the change request service exception
	 * @method rejectChangeRequestTask
	 */
	void rejectChangeRequestTask(ChangeRequest chngReq, UserDetails userDetails,SalesPosition salesPosition) throws ChangeRequestServiceException, AffiliationServiceException, SalesPositionServiceException, CustomerServiceException, CallPlanServiceException;
	
	/**
	 * Reminder change request Task.
	 * 
	 * @method reminderChangeRequestTask
	 * @param chngReq the chng req
	 * @param userDetails the user details
	 * @param salesPosition the sales position
	 * @throws ChangeRequestServiceException the change request service exception
	 * @method reminderChangeRequestTask
	 */
	void reminderChangeRequestTask(ChangeRequest chngReq, UserDetails userDetails,SalesPosition salesPosition) throws ChangeRequestServiceException, AffiliationServiceException, SalesPositionServiceException, CustomerServiceException, CallPlanServiceException;
	
	/**
	 * Update change request status.
	 *
	 * @param changeRequestId the change request id
	 * @param statusId the status id
	 * @param userDetails the user details
	 * @throws ChangeRequestServiceException the change request service exception
	 */
	void updateChangeRequestStatus(long changeRequestId, Integer statusId, UserDetails userDetails ) throws ChangeRequestServiceException;
	
	
	void updateCRLineStatusForCustAlgmt(long changeRequestId, long custAlgmtId, Integer statusId, UserDetails userDetails) throws ChangeRequestServiceException;

	void updateCRLineStatusForZip(ChangeRequest chngReq, Integer statusId, PostalCode postalCode, UserDetails userDetails) throws ChangeRequestServiceException;
	
	
	
}
