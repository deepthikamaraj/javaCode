package com.cognizant.opserv.sp.cr.process.internal.service;

import java.util.List;

import com.cognizant.opserv.sp.cr.process.dto.CustomerAlignmentDTO;
import com.cognizant.opserv.sp.cr.process.dto.MirrorCustAlgmtDTO;
import com.cognizant.opserv.sp.exception.AffiliationServiceException;
import com.cognizant.opserv.sp.exception.AlignmentServiceException;
import com.cognizant.opserv.sp.exception.CallPlanServiceException;
import com.cognizant.opserv.sp.exception.ChangeRequestServiceException;
import com.cognizant.opserv.sp.exception.CustomerServiceException;
import com.cognizant.opserv.sp.exception.SalesPositionServiceException;
import com.cognizant.opserv.sp.exception.ViewServiceException;
import com.cognizant.opserv.sp.model.CustomerAlignment;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.model.cr.ChangeRequest;
/**
 * Services for Customer Push in Offline .
 * 
 * @author 426111
 * @interface CustomerPushOfflineService
 */
public interface CustomerPushOfflineService {

	
	/**
	 * getMirrorCRsForPush -Gets The Mirror CR fro Push
	 * 
	 * @param customerAlgmtDTO
	 *            -Customer Alignment DTO
	 * @param userDetails
	 *            -user Details
	 */
	void getMirrorCRsForPush(CustomerAlignmentDTO custAlgmtDTO, UserDetails userDetails);
	
	/**
	 * createMirrorCR -Creates The Mirror CR
	 * 
	 * @param parentCR
	 *            -parent CR
	 * @param mirrorDTO
	 *            -mirror DTO
	 * @param userDetails
	 *            -user Details
	 * @throws ChangeRequestServiceException
	 */
	boolean createMirrorCR(ChangeRequest parentCR, MirrorCustAlgmtDTO mirrorDTO, UserDetails userDetails);
	
	/**
	 * validate - To validate the customer
	 * 
	 * @param custAlgmtDTO
	 *            the custAlgmtDTO
	 * @param userDetails
	 *            the userDetails
	 * @throws AffiliationServiceException 
	 * @throws AlignmentServiceException 
	 * @throws SalesPositionServiceException
	 */
	void validate(CustomerAlignmentDTO customerAlgmtDTO, UserDetails userDetails) throws AffiliationServiceException, AlignmentServiceException, SalesPositionServiceException;
	
	/**
	 * Lock customer alignment- Update Change Request Status Type to
	 * PENDING_FOR_SUBMISSION.
	 * 
	 * @param customerAlgmt
	 *            the customer algmt
	 * @param userDetails
	 *            the user details
	 * @return true, if successful
	 * @throws AlignmentServiceException 
	 * @throws ViewServiceException 
	 */
	boolean lockCustomerAlignment(CustomerAlignment customerAlgmt, UserDetails userDetails) throws AlignmentServiceException, ViewServiceException;

	/**
	 * Unlock a customer alignment
	 * @param customerAlgmnt
	 * @param userDetails
	 * @return
	 * @throws AlignmentServiceException
	 * @throws ViewServiceException 
	 */
	boolean unLockCustomerAlignment(CustomerAlignment customerAlgmnt, UserDetails userDetails) throws AlignmentServiceException, ViewServiceException;
	
	/**
	 * Unlock multiple customer aligments
	 * @param customerAlgmnt
	 * @param userDetails
	 * @return
	 * @throws AlignmentServiceException
	 * @throws ViewServiceException 
	 */
	boolean unLockCustomerAlignments(List<CustomerAlignment> customerAlgmnt, UserDetails userDetails) throws AlignmentServiceException, ViewServiceException;
	
	
	/**
	 * processSourceOnSubmit -Process Line Items for Source Customer
	 * 
	 * @param customerAlgmtDTO
	 *            -Customer Alignment DTO
	 * @param userDetails
	 *            -UserDetails
	 * @throws ChangeRequestServiceException
	 */
	void processSourceOnSubmit(CustomerAlignmentDTO customerAlgmtDTO, UserDetails userDetails) throws ChangeRequestServiceException;
	/**
	 * processTargetOnSubmit -Process Line Items for Target Customer
	 * 
	 * @param customerAlgmtDTO
	 *            -Customer Alignment DTO
	 * @param userDetails
	 *            -UserDetails
	 * @throws ChangeRequestServiceException
	 * @throws CallPlanServiceException 
	 * @throws CustomerServiceException 
	 * @throws AlignmentServiceException 
	 */
	void processTargetOnSubmit(CustomerAlignmentDTO customerAlgmtDTO, UserDetails userDetails) throws ChangeRequestServiceException, CallPlanServiceException, CustomerServiceException, AlignmentServiceException;
	
	/**
	 * handleMetricsOnSubmit - To handle metrics on submit
	 * 
	 * @param custAlgmtDTO
	 *            the custAlgmtDTO
	 * @param userDetails
	 *            the userDetails
	 */
	boolean handleMetricsOnSubmit(CustomerAlignmentDTO customerAlgmtDTO, UserDetails userDetails);

	/**
	 * updateChangeRequests -updates Change Request
	 * 
	 * @param customerAlgmtDTO
	 *            -Customer Alignment DTO
	 * @param userDetails
	 *            -user Details
	 * @throws ChangeRequestServiceException
	 */
	void updateChangeRequests(CustomerAlignmentDTO customerAlgmtDTO, UserDetails userDetails) throws ChangeRequestServiceException;
	
	/**
	 * emitPostProcessEvents -Emits Post Process Events
	 * 
	 * @param customerAlgmtDTO
	 *            -Customer Alignment DTO
	 * @param userDetails
	 *            -user Details
	 */
	void emitPostProcessEvents(CustomerAlignmentDTO customerAlgmtDTO, UserDetails userDetails);
	
	/**
	 * assignApproversForSourceAndTarget -Assigns approve for Source and Target
	 * 
	 * @param customerAlgmtDTO
	 *            -Customer Alignment DTO
	 * @param userDetails
	 *            -user Details
	 * @throws ChangeRequestServiceException
	 */
	void assignApproversForSourceAndTarget(CustomerAlignmentDTO customerAlgmtDTO, UserDetails userDetails) throws ChangeRequestServiceException;
}
