package com.cognizant.opserv.sp.service.changereq;

import java.util.List;

import com.cognizant.opserv.sp.event.messages.OfflineRequestMessage;
import com.cognizant.opserv.sp.exception.AlignmentServiceException;
import com.cognizant.opserv.sp.exception.ChangeRequestServiceException;
import com.cognizant.opserv.sp.model.BusinessReason;
import com.cognizant.opserv.sp.model.Customer;
import com.cognizant.opserv.sp.model.CustomerAlignment;
import com.cognizant.opserv.sp.model.CustomerProductAlignment;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.model.cr.ChangeRequest;
import com.cognizant.opserv.sp.model.cr.ChangeRequestApproverDetails;
import com.cognizant.opserv.sp.model.cr.CustomerAlignmentChangeRequestDetails;
import com.cognizant.opserv.sp.model.cr.ZipAlignmentChangeRequestDetails;

/**
 * ****************************************************************************.
 * 
 * @class ChangeRequestService contains all the services for change request
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 * ***************************************************************************
 */
public interface ChangeRequestService {
	
	
	/**
	 * @param sourceSP
	 * @param targetSP
	 * @param userDetails
	 * @return
	 * @throws ChangeRequestServiceException
	 * @throws AlignmentServiceException 
	 */
	ChangeRequest generatePushCustomerCR(SalesPosition sourceSP, SalesPosition targetSP, UserDetails userDetails) throws ChangeRequestServiceException, AlignmentServiceException;
	
	/**
	 * @param sourceSP
	 * @param targetSP
	 * @param userDetails
	 * @return
	 * @throws ChangeRequestServiceException
	 * @throws AlignmentServiceException 
	 */
	ChangeRequest generatePullCustomerCR(SalesPosition sourceSP, SalesPosition targetSP, UserDetails userDetails) throws ChangeRequestServiceException, AlignmentServiceException;
	
	/**
	 * @param customerSP
	 * @param userDetails
	 * @return
	 * @throws ChangeRequestServiceException
	 */
	ChangeRequest generateEditCustomerCR(SalesPosition customerSP, UserDetails userDetails) throws ChangeRequestServiceException;
	
	/**
	 * @param sourceSP
	 * @param targetSP
	 * @param userDetails
	 * @return
	 * @throws ChangeRequestServiceException
	 */
	ChangeRequest generateZipMovementCR(SalesPosition sourceSP, SalesPosition targetSP, UserDetails userDetails) throws ChangeRequestServiceException;
	
	/**
	 * @param changeRequest
	 * @param orgCustAlgn
	 * @param newCustAlgn
	 * @param comments
	 * @param userDetails
	 * @return
	 * @throws ChangeRequestServiceException
	 */
	OfflineRequestMessage createOffLinePushRequest(ChangeRequest changeRequest, CustomerAlignment orgCustAlgn, CustomerAlignment newCustAlgn,
			String comments, UserDetails userDetails) throws ChangeRequestServiceException;
	
	/**
	 * @param changeRequest
	 * @param orgCustAlgn
	 * @param newCustAlgn
	 * @param comments
	 * @param userDetails
	 * @return
	 * @throws ChangeRequestServiceException
	 */
	OfflineRequestMessage createOffLinePullRequest(ChangeRequest changeRequest, CustomerAlignment orgCustAlgn,
			CustomerAlignment newCustAlgn, String comments, UserDetails userDetails, BusinessReason businessReason, 
			List<CustomerProductAlignment> oldCustomerProductAlignments, List<CustomerProductAlignment> newCustomerProductAlignments) throws ChangeRequestServiceException;
	
	/**
	 * Submit change request.
	 * 
	 * @method submitChangeRequest
	 * @param chngReq the chng req
	 * @param userDetails the user details
	 * @throws ChangeRequestServiceException the change request service exception
	 * @method submitChangeRequest
	 */
	void submitChangeRequest(ChangeRequest chngReq, UserDetails userDetails) throws ChangeRequestServiceException;

	/**
	 * Gets the all change requests for approval.
	 * 
	 * @method getAllChangeRequestsForApproval
	 * @param salesPostion the sales postion
	 * @param userDetails the user details
	 * @return the all change requests for approval
	 * @throws ChangeRequestServiceException the change request service exception
	 * @method getAllChangeRequestsForApproval
	 */
	List<ChangeRequest> getAllChangeRequestsForApproval(SalesPosition salesPostion, UserDetails userDetails) throws ChangeRequestServiceException;

	/**
	 * Gets the all change requests.
	 *
	 * @method getAllChangeRequests
	 * @param salesPostion the sales postion
	 * @param userDetails the user details
	 * @return the all change requests
	 * @throws ChangeRequestServiceException the change request service exception
	 * @method getAllChangeRequests
	 */
	List<ChangeRequest> getAllChangeRequests(SalesPosition salesPostion, UserDetails userDetails) throws ChangeRequestServiceException;

	/**
	 * Gets the all change requests of customer.
	 *
	 * @method getAllChangeRequestsOfCustomer
	 * @param customer the customer
	 * @param salesPostion the sales postion
	 * @param userDetails the user details
	 * @return the all change requests of customer
	 * @throws ChangeRequestServiceException the change request service exception
	 * @method getAllChangeRequestsOfCustomer
	 */
	List<ChangeRequest> getAllChangeRequestsOfCustomer(Customer customer, SalesPosition salesPostion, UserDetails userDetails) throws ChangeRequestServiceException;

	/**
	 * Withdraw change request.
	 *
	 * @method withdrawChangeRequest
	 * @param chngReq the chng req
	 * @param userDetails the user details
	 * @throws ChangeRequestServiceException the change request service exception
	 * @method withdrawChangeRequest
	 */
	void withdrawChangeRequest(ChangeRequest chngReq, UserDetails userDetails) throws ChangeRequestServiceException;

	
	/**
	 * Gets the all change requests.
	 *
	 * @method getAllChangeRequests
	 * @param salesPostion the sales postion
	 * @param userDetails the user details
	 * @return the all change requests
	 * @throws ChangeRequestServiceException the change request service exception
	 * @method getAllChangeRequests
	 */
	ChangeRequest getAllChangeRequestsLineItemsbyChangeRequestId(ChangeRequest chngReq, UserDetails userDetails) throws ChangeRequestServiceException;

	/**
	 * @param chngReq
	 * @param userDetails
	 * @return List
	 * @throws ChangeRequestServiceException
	 */
	List<CustomerAlignmentChangeRequestDetails> getCustomerAlignmentChangeRequestDetailsByChangeRequest(ChangeRequest chngReq, UserDetails userDetails) throws ChangeRequestServiceException;

	/**
	 * @param chngReq
	 * @param userDetails
	 * @return List
	 * @throws ChangeRequestServiceException
	 */
	List<ZipAlignmentChangeRequestDetails> getZipAlignmentChangeRequestDetailsByChangeRequest(ChangeRequest chngReq, UserDetails userDetails) throws ChangeRequestServiceException;
	
	
	/**
	 * @param salesPosition
	 * @param userDetails
	 * @return
	 * @throws ChangeRequestServiceException
	 */
	List<String> getChangeRequestsTasksFromApproverSalesPosition(SalesPosition salesPosition,UserDetails userDetails) throws ChangeRequestServiceException;;

/**
	 * Change requests mark as dirty.
	 *
	 * @param spId the sp id
	 * @param userDetails the user details
	 * @return true, if successful
	 * @throws ChangeRequestServiceException the change request service exception
	 */
	boolean changeRequestsMarkAsDirty(Long spId, UserDetails userDetails) throws ChangeRequestServiceException;
	
	
	/**
	 * Approve change request.
	 *
	 * @method approveChangeRequest
	 * @param chngReq the chng req
	 * @param userDetails the user details
	 * @throws ChangeRequestServiceException the change request service exception
	 * @method approveChangeRequest
	 */
	void approveChangeRequest(ChangeRequest chngReq, UserDetails userDetails,SalesPosition salesPosition,String comments) throws ChangeRequestServiceException;


	/**
	 * Reject change request.
	 *
	 * @method rejectChangeRequest
	 * @param chngReq the chng req
	 * @param userDetails the user details
	 * @throws ChangeRequestServiceException the change request service exception
	 * @method rejectChangeRequest
	 */
	void rejectChangeRequest(ChangeRequest chngReq, UserDetails userDetails,SalesPosition salesPosition,String comments) throws ChangeRequestServiceException;
	
    /**
    * Gets the change request approvers details.
    *
    * @param changeRequest the change request
    * @param userDetails the user details
    * @return the change request approvers details
    * @throws ChangeRequestServiceException the change request service exception
    */
    List<ChangeRequestApproverDetails> getChangeRequestApproversDetails(ChangeRequest changeRequest , UserDetails userDetails) throws ChangeRequestServiceException;
	
	/**
	 * @param changeRequest
	 * @param oldCustAlgn
	 * @param newCustAlgn
	 * @param comments
	 * @param userDetails
	 * @param businessReason
	 * @param oldCustomerProductAlignments
	 * @param newCustomerProductAlignments
	 * @return
	 * @throws ChangeRequestServiceException
	 */
	OfflineRequestMessage createOffLineEditRequest(ChangeRequest changeRequest, CustomerAlignment oldCustAlgn, CustomerAlignment newCustAlgn, String comments,
			UserDetails userDetails, BusinessReason businessReason, List<CustomerProductAlignment> oldCustomerProductAlignments, List<CustomerProductAlignment> newCustomerProductAlignments)
			throws ChangeRequestServiceException;

}
