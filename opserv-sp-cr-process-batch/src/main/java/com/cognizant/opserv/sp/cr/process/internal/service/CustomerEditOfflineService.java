package com.cognizant.opserv.sp.cr.process.internal.service;

import java.util.List;

import com.cognizant.opserv.sp.cr.process.dto.CustomerAlignmentDTO;
import com.cognizant.opserv.sp.cr.process.dto.MirrorCustAlgmtDTO;
import com.cognizant.opserv.sp.exception.AffiliationServiceException;
import com.cognizant.opserv.sp.exception.AlignmentServiceException;
import com.cognizant.opserv.sp.exception.CallPlanServiceException;
import com.cognizant.opserv.sp.exception.ChangeRequestServiceException;
import com.cognizant.opserv.sp.exception.MetricExecutionException;
import com.cognizant.opserv.sp.exception.SalesPositionServiceException;
import com.cognizant.opserv.sp.exception.ViewServiceException;
import com.cognizant.opserv.sp.model.CustomerAlignment;
import com.cognizant.opserv.sp.model.CustomerProductAlignment;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.model.cr.ChangeRequest;
/**
 * Services for Customer Edit in Offline .
 * 
 * @author 426111
 *
 */
public interface CustomerEditOfflineService {
	/**
	 * getMirrorCRsForEdit -Gets The Mirror CR fro Edit
	 * 
	 * @param customerAlgmtDTO
	 *            -Customer Alignment DTO
	 * @param userDetails
	 *            -user Details
	 */
	void getMirrorCRsForEdit(CustomerAlignmentDTO custAlgmtDTO, UserDetails userDetails) throws ChangeRequestServiceException;
	/**
	 * createMirrorCR -Creates The Mirror CR
	 * 
	 * @param parentCR
	 *            -parent CR
	 * @param mirrorDTO
	 *            -mirror DTO
	 * @param userDetails
	 *            -user Details
	 * @return 
	 * @throws ChangeRequestServiceException
	 */
	boolean createMirrorCR(ChangeRequest parentCR, MirrorCustAlgmtDTO mirrorDTO, UserDetails userDetails) throws ChangeRequestServiceException;
	/**
	 * validate - To validate the customer
	 * 
	 * @param custAlgmtDTO
	 *            the custAlgmtDTO
	 * @param userDetails
	 *            the userDetails
	 * @throws AffiliationServiceException
	 * @throws SalesPositionServiceException
	 */
	void validate(CustomerAlignmentDTO customerAlgmtDTO, UserDetails userDetails) throws CallPlanServiceException, AlignmentServiceException, SalesPositionServiceException;
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
	 * processSourceOnSubmit -Process Line Items for Source Customer
	 * 
	 * @param customerAlgmtDTO
	 *            -Customer Alignment DTO
	 * @param userDetails
	 *            -UserDetails
	 * @throws ChangeRequestServiceException
	 */
	void processOnSubmit(CustomerAlignmentDTO customerAlgmtDTO, UserDetails userDetails) throws ChangeRequestServiceException, AlignmentServiceException, CallPlanServiceException;
	/**
	 * handleMetricsOnSubmit - To handle metrics on submit
	 * 
	 * @param custAlgmtDTO
	 *            the custAlgmtDTO
	 * @param userDetails
	 *            the userDetails
	 */
	boolean handleMetricsOnSubmit(CustomerAlignmentDTO customerAlgmtDTO, UserDetails userDetails) throws MetricExecutionException;
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
	void assignApprovers(CustomerAlignmentDTO customerAlgmtDTO, UserDetails userDetails) throws ChangeRequestServiceException;
	
	/**
	 * updates customer Details on Approval
	 * 
	 * @param targetCustomerAlignment
	 *            -target Customer Alignment
	 * @param updatedCustomerProductAlignments
	 *            -updated Customer Product Alignments
	 * @param userDetails
	 *            -userDetails
	 * @throws AlignmentServiceException
	 * @throws CallPlanServiceException
	 */
	void updateCustomerDetailsOnAppr(CustomerAlignment targetCustomerAlignment, List<CustomerProductAlignment> updatedCustomerProductAlignments, UserDetails userDetails) throws AlignmentServiceException, CallPlanServiceException;

	/**
	 * Unlock a customer alignment
	 * 
	 * @param customerAlgmnt
	 *            -customer Algmnt
	 * @param userDetails
	 *            -userDetails
	 * @return
	 * @throws AlignmentServiceException
	 * @throws ViewServiceException 
	 */
	boolean unLockCustomerAlignment(CustomerAlignment affCustomerAlignment,UserDetails userDetails) throws AlignmentServiceException, ViewServiceException;
	
}
