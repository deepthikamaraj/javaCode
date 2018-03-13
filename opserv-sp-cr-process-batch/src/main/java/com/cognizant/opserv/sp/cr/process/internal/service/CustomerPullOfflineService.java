package com.cognizant.opserv.sp.cr.process.internal.service;

import java.util.List;

import com.cognizant.opserv.sp.cr.process.dto.CustomerAlignmentDTO;
import com.cognizant.opserv.sp.cr.process.dto.MirrorCustAlgmtDTO;
import com.cognizant.opserv.sp.exception.AffiliationServiceException;
import com.cognizant.opserv.sp.exception.AlignmentServiceException;
import com.cognizant.opserv.sp.exception.CallPlanServiceException;
import com.cognizant.opserv.sp.exception.ChangeRequestServiceException;
import com.cognizant.opserv.sp.exception.CustomerServiceException;
import com.cognizant.opserv.sp.exception.MetricExecutionException;
import com.cognizant.opserv.sp.exception.SalesPositionServiceException;
import com.cognizant.opserv.sp.exception.ViewServiceException;
import com.cognizant.opserv.sp.model.CustomerAlignment;
import com.cognizant.opserv.sp.model.CustomerProductAlignment;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.model.cr.ChangeRequest;
/**
 * ****************************************************************************.
 * 
 * @class CustomerPullOfflineServiceImpl
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 23/09/2016
 * ***************************************************************************
 */
public interface CustomerPullOfflineService {

	
	void lockRequestForProcessing(Long offlineId, UserDetails userDetails);
	
	/**
	 * Gets the mirror c rs for pull.
	 *
	 * @param custAlgmtDTO the cust algmt dto
	 * @param userDetails the user details
	 * @return the mirror c rs for pull
	 * @throws ChangeRequestServiceException the change request service exception
	 */
	void getMirrorCRsForPull(CustomerAlignmentDTO custAlgmtDTO, UserDetails userDetails) throws ChangeRequestServiceException;
	
	/**
	 * Creates the mirror cr.
	 *
	 * @param parentCR the parent cr
	 * @param mirrorDTO the mirror dto
	 * @param userDetails the user details
	 * @throws ChangeRequestServiceException the change request service exception
	 * @throws AlignmentServiceException 
	 */
	boolean createMirrorCR(ChangeRequest parentCR, MirrorCustAlgmtDTO mirrorDTO, UserDetails userDetails) throws ChangeRequestServiceException, AlignmentServiceException;
	
	/**
	 * Validate.
	 *
	 * @param customerAlgmtDTO the customer algmt dto
	 * @param userDetails the user details
	 * @throws CallPlanServiceException the call plan service exception
	 * @throws AlignmentServiceException 
	 * @throws AffiliationServiceException
	 * @throws SalesPositionServiceException
	 */
	void validate(CustomerAlignmentDTO customerAlgmtDTO, UserDetails userDetails) throws AffiliationServiceException, AlignmentServiceException, SalesPositionServiceException;
	
	boolean lockCustomerAlignment(CustomerAlignment customerAlgmt, UserDetails userDetails) throws AlignmentServiceException, ViewServiceException;
	
	/**
	 * Process source on submit.
	 *
	 * @param customerAlgmtDTO the customer algmt dto
	 * @param userDetails the user details
	 * @throws ChangeRequestServiceException the change request service exception
	 */
	void processSourceOnSubmit(CustomerAlignmentDTO customerAlgmtDTO, UserDetails userDetails) throws ChangeRequestServiceException;
	
	/**
	 * Process target on submit.
	 *
	 * @param customerAlgmtDTO the customer algmt dto
	 * @param userDetails the user details
	 * @throws ChangeRequestServiceException the change request service exception
	 * @throws CallPlanServiceException the call plan service exception
	 * @throws CustomerServiceException 
	 * @throws AlignmentServiceException 
	 */
	void processTargetOnSubmit(CustomerAlignmentDTO customerAlgmtDTO, UserDetails userDetails) throws ChangeRequestServiceException, CallPlanServiceException, CustomerServiceException, AlignmentServiceException;
	
	/**
	 * Handle metrics on submit.
	 *
	 * @param customerAlgmtDTO the customer algmt dto
	 * @param userDetails the user details
	 * @return the boolean
	 * @throws MetricExecutionException 
	 */
	boolean handleMetricsOnSubmit(CustomerAlignmentDTO customerAlgmtDTO, UserDetails userDetails);

	/**
	 * Update change requests.
	 *
	 * @param customerAlgmtDTO the customer algmt dto
	 * @param userDetails the user details
	 * @throws ChangeRequestServiceException the change request service exception
	 */
	void updateChangeRequests(CustomerAlignmentDTO customerAlgmtDTO, UserDetails userDetails) throws ChangeRequestServiceException;
	
	void emitPostProcessEvents(CustomerAlignmentDTO customerAlgmtDTO, UserDetails userDetails);
	
	/**
	 * Assign approvers for source and target.
	 *
	 * @param customerAlgmtDTO the customer algmt dto
	 * @param userDetails the user details
	 * @throws ChangeRequestServiceException the change request service exception
	 */
	void assignApproversForSourceAndTarget(CustomerAlignmentDTO customerAlgmtDTO, UserDetails userDetails) throws ChangeRequestServiceException;

	void updateExtdAttrOnApprForPull(CustomerAlignment targetCustomerAlignment,
			List<CustomerProductAlignment> updatedCustomerProductAlignments,
			UserDetails userDetails) throws AlignmentServiceException,
			CallPlanServiceException;
	
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
}
