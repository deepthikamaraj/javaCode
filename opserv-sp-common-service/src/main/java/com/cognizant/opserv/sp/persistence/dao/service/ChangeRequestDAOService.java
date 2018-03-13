package com.cognizant.opserv.sp.persistence.dao.service;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TChngReq;
import com.cognizant.opserv.sp.exception.CallPlanServiceException;
import com.cognizant.opserv.sp.exception.ChangeRequestServiceException;
import com.cognizant.opserv.sp.model.Customer;
import com.cognizant.opserv.sp.model.GeographyAlignment;
import com.cognizant.opserv.sp.model.PostalCode;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.model.cr.ChangeRequest;
import com.cognizant.opserv.sp.model.cr.ChangeRequestApproverDetails;
import com.cognizant.opserv.sp.model.cr.CustomerAlignmentChangeRequestDetails;
import com.cognizant.opserv.sp.model.cr.ZipAlignmentChangeRequestDetails;
import com.cognizant.peg.core.exception.OpservDataAccessException;
import com.cognizant.opserv.sp.common.ChangeRequestStatusType;

/**
 * ****************************************************************************.
 * 
 * @class ChangeRequestDAOService contains all the services DAO for change request
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 * ***************************************************************************
 */
public interface ChangeRequestDAOService {

	/**
	 * To Get All ChangeRequests For Approval
	 * @param salesPostion - Sales Postion
	 * @param userDetails - User Details
	 * @return List<ChangeRequest>
	 * @throws OpservDataAccessException
	 */
	List<ChangeRequest> getAllChangeRequestsForApproval(SalesPosition salesPostion, UserDetails userDetails) throws OpservDataAccessException;

	/**
	 * To Get All ChangeRequests
	 * @param salesPostion - Sales Postion
	 * @param userDetails - User Details
	 * @return List<ChangeRequest>
	 * @throws OpservDataAccessException
	 */
	List<ChangeRequest> getAllChangeRequests(SalesPosition salesPostion, UserDetails userDetails) throws OpservDataAccessException;

	/**
	 * To Get All ChangeRequests Of Customer
	 * @param customer - Customer
	 * @param salesPostion - Sales Postion
	 * @param userDetails - User Details
	 * @return List<ChangeRequest>
	 * @throws OpservDataAccessException
	 */
	List<ChangeRequest> getAllChangeRequestsOfCustomer(Customer customer, SalesPosition salesPostion, UserDetails userDetails) throws OpservDataAccessException;

	/**
	 * To Get ChangeRequest Details By ChangeRequest Id
	 * @param chngReq - ChangeRequest
	 * @return ChangeRequest
	 * @throws OpservDataAccessException
	 */
	ChangeRequest getChangeRequestByChangeRequestId(ChangeRequest chngReq) throws OpservDataAccessException;
	
	/**
	 * To Get Change Request Line Items By Change Request Id
	 * @param chngReq
	 * @return ChangeRequest
	 * @throws OpservDataAccessException
	 */
	public ChangeRequest getChangeRequestLineItemsByChangeRequestId(ChangeRequest chngReq) throws OpservDataAccessException;	
	
	 /**
	  * To Get Customer Alignment Change Request Details By Change Request
	 * @param chngReq
	 * @return List
	 * @throws ChangeRequestServiceException
	 */
	List<CustomerAlignmentChangeRequestDetails> getCustomerAlignmentChangeRequestDetailsByChangeRequest(ChangeRequest chngReq) throws OpservDataAccessException;

	/**
	 * To Get Zip Alignment ChangeRequest Details By ChangeRequest
	 * @param chngReq
	 * @return List
	 * @throws ChangeRequestServiceException
	 */
	List<ZipAlignmentChangeRequestDetails> getZipAlignmentChangeRequestDetailsByChangeRequest(ChangeRequest chngReq) throws OpservDataAccessException;
	
	
	/**
	 * Update change request status.
	 *
	 * @param changeRequestId the change request id
	 * @param statusId the status id
	 * @param userDetails the user details
	 * @throws OpservDataAccessException the opserv data access exception
	 */
	void updateChangeRequestStatus(long changeRequestId, Integer statusId, UserDetails userDetails) throws OpservDataAccessException;
	
	
	/**
	 * Update the CR Line Item status for CR Id and Cust Algmnt Id
	 * @param changeRequestId
	 * @param custAlgmtId
	 * @param statusId
	 * @param userDetails
	 * @throws OpservDataAccessException
	 */
	void updateCRLineStatusForCustAlgmt(long changeRequestId, long custAlgmtId, Integer statusId, UserDetails userDetails) throws OpservDataAccessException;
	
	
	/**
	 * Mark CRs as dirty.
	 *
	 * @param spId the sp id
	 * @param userDetails the user details
	 * @return true, if successful
	 * @throws OpservDataAccessException the opserv data access exception
	 */
	boolean markCRsAsDirty(Long spId,UserDetails userDetails) throws OpservDataAccessException;
	
	
	/**
	 * To Get ChangeRequest Status By ChangeRequest Id
	 * @param chngReq
	 * @return ChangeRequest
	 * @throws OpservDataAccessException
	 */
	ChangeRequest getChangeRequestStatusByChangeRequestId(ChangeRequest chngReq) throws OpservDataAccessException;
	
	/**
	 * To Find Parent ChangeRequest Id By Child ChangeRequest Id
	 * @param chngReq
	 * @param userDetails
	 * @return List<Long>
	 * @throws OpservDataAccessException
	 */
	List<Long> findParentChangeRequestIdByChildChangeRequestId(ChangeRequest chngReq,UserDetails userDetails) throws OpservDataAccessException;

	/**
	 * @param changeRequest
	 * @param userDetails
	 * @return
	 * @throws OpservDataAccessException
	 */
	List<ChangeRequestApproverDetails> getCRApproversDetails(ChangeRequest changeRequest, UserDetails userDetails) throws OpservDataAccessException;
	
	
	/**
	 * Creates the change request.
	 *
	 * @param sourceSalesPostion the source sales postion
	 * @param targetSalesPostion the target sales postion
	 * @param userDetails the user details
	 * @param triggerId the trigger id
	 * @return the change request
	 * @throws OpservDataAccessException the opserv data access exception
	 */
	ChangeRequest createChangeRequest(SalesPosition sourceSalesPostion, SalesPosition targetSalesPostion, UserDetails userDetails,
			Integer triggerId) throws OpservDataAccessException;;
	
	

	/**
	 * Creates the mirror change request.
	 *
	 * @param parentChnageRequest the parent chnage request
	 * @param sourceSalesPostion the source sales postion
	 * @param targetSalesPostion the target sales postion
	 * @param userDetails the user details
	 * @param triggerId the trigger id
	 * @return the change request
	 * @throws OpservDataAccessException the opserv data access exception
	 */
	ChangeRequest createMirrorChangeRequest(ChangeRequest parentChnageRequest,SalesPosition sourceSalesPostion, SalesPosition targetSalesPostion, UserDetails userDetails,
			Integer triggerId) throws OpservDataAccessException;
	
	
	
	/**
	 * @param chngReq
	 * @return List
	 * @throws OpservDataAccessException
	 */
	List<ChangeRequest> findChildChangeRequestByParentChangeRequest(ChangeRequest chngReq) throws OpservDataAccessException;
	
	

	/**
	 * @param chngReq
	 * @param userDetails
	 * @throws OpservDataAccessException
	 */
	void rejectChangeRequest(ChangeRequest chngReq, UserDetails userDetails,SalesPosition salesPosition) throws OpservDataAccessException;
	
	
		
	/**
	 * @param chngReq
	 * @param userDetails
	 * @return List
	 * @throws OpservDataAccessException
	 */
	List<CustomerAlignmentChangeRequestDetails> getCustomerAlignmentChangeRequestDetails(ChangeRequest chngReq, UserDetails userDetails) throws OpservDataAccessException;

	

	
	/**
	 * @param chngReq
	 * @param userDetails
	 * @return List
	 */
	List<Long> findCustomerAlignmentSalesPostionIdByChangeRequestId(ChangeRequest chngReq,UserDetails userDetails)throws OpservDataAccessException;
	
	/**
	 * @param chngReq
	 * @param userDetails
	 * @return List
	 */
	List<Long> findZipAlignmentSalesPostionIdByChangeRequestId(ChangeRequest chngReq,UserDetails userDetails)throws OpservDataAccessException;
	
	
	/**
	 * @param chngReq
	 * @param userDetails
	 * @throws OpservDataAccessException
	 */
	public void approveChangeRequest(ChangeRequest chngReq, UserDetails userDetails,SalesPosition salesPosition) throws OpservDataAccessException;
	

	/**
	 * To get CustomerAlignmentChangeRequestDetails By Parent CRID
	 * @param chngReq
	 * @return List<CustomerAlignmentChangeRequestDetails>
	 * @throws OpservDataAccessException
	 */
	List<CustomerAlignmentChangeRequestDetails> getCustAlgnCRDetailsByPrntCRId(ChangeRequest chngReq)throws OpservDataAccessException; 
	
	
	/**
	 * @param chngReqId
	 * @param tenantId
	 * @return
	 */
	List<Long> findCustomerIdByChangeRequestId(ChangeRequest chngReq,UserDetails userDetails)throws OpservDataAccessException;
	
	/**
	 * @param chngReq
	 * @return ChangeRequest
	 * @throws OpservDataAccessException
	 */
	TChngReq getChangeRequestByChangeRequest(ChangeRequest chngReq) throws OpservDataAccessException;


		
	/**
	 * @param chngReq
	 * @param userDetails
	 * @return Long
	 * @throws OpservDataAccessException
	 */
	Long fetchTChngReqApprsTypeByStatus(ChangeRequest chngReq,UserDetails userDetails) throws OpservDataAccessException;
	
	
	
	/**
	 * @param chngReq
	 * @param userDetails
	 * @param custAlgmnSalesPostionIds
	 * @param geoAlgmnSalesPostionIds
	 * @param aprStsId
	 * @param autoAprStsId
	 * @throws CallPlanServiceException 
	 */
	public void autoApproveForBaseAndMirrorChangeRequests(ChangeRequest chngReq, UserDetails userDetails,Integer aprStsId,Integer autoAprStsId) throws OpservDataAccessException,CallPlanServiceException;
	
	
	/**
	 * @param chngReq
	 * @param userDetails
	 * @param custAlgmnSalesPostionIds
	 * @param geoAlgmnSalesPostionIds
	 * @param rejectStsId
	 * @param rejectAprStsId
	 */
	public void autoRejectForBaseAndMirrorChangeRequests(ChangeRequest chngReq, UserDetails userDetails, Integer rejectStsId,Integer rejectAprStsId) throws OpservDataAccessException ;
	
	
	/**
	 * @param chngReq
	 * @param alignmentFlag
	 * @param userDetails
	 * @return List<GeographyAlignment>
	 * @throws OpservDataAccessException
	 */
	List<GeographyAlignment> findZipAlignmentByChangeRequestId(ChangeRequest chngReq,SalesPosition salesPostion,Character alignmentFlag ,UserDetails userDetails) throws OpservDataAccessException ;
	
	
	
	/**
	 * @param chngReq
	 * @param alignmentFlag
	 * @param userDetails
	 * @return List<GeographyAlignment>
	 * @throws OpservDataAccessException
	 */
	List<Object> findMirrorZipAlignmentByChangeRequestId(ChangeRequest chngReq,Character alignmentFlag ,UserDetails userDetails) throws OpservDataAccessException ;
	
	
	/**
	 * @param chngReq
	 * @param userDetails
	 * @return
	 * @throws OpservDataAccessException
	 */
	List<ChangeRequestApproverDetails> fetchTChngReqDetails(ChangeRequest chngReq,UserDetails userDetails) throws OpservDataAccessException ;
	
	
	
	/**
	 * Creates the line item for source customer.
	 *
	 * @param crLineItems the cr line items
	 * @param userDetails the user details
	 * @throws OpservDataAccessException the opserv data access exception
	 */
	void createLineItemForSourceCustomer(List<CustomerAlignmentChangeRequestDetails> crLineItems,UserDetails userDetails)throws OpservDataAccessException;
	
	/**
	 * Creates the line item for target customer.
	 *
	 * @param crLineItems the cr line items
	 * @param userDetails the user details
	 * @throws OpservDataAccessException the opserv data access exception
	 */
	void createLineItemForTargetCustomer(List<CustomerAlignmentChangeRequestDetails> crLineItems,UserDetails userDetails)throws OpservDataAccessException;
	
	/**
	 * Process line item for source zip.
	 *
	 * @param crLineItems the cr line items
	 * @param userDetails the user details
	 * @throws OpservDataAccessException the opserv data access exception
	 */
	void createLineItemForSourceZip(List<ZipAlignmentChangeRequestDetails> crLineItems,UserDetails userDetails)throws OpservDataAccessException;
	
	/**
	 * Process line item for target zip.
	 *
	 * @param crLineItems the cr line items
	 * @param userDetails the user details
	 * @throws OpservDataAccessException the opserv data access exception
	 */
	void createLineItemForTargetZip(List<ZipAlignmentChangeRequestDetails> crLineItems,UserDetails userDetails)throws OpservDataAccessException;
	
	/**
	 * Creates the source approvers.
	 *
	 * @param chngReq the chng req
	 * @param sourceSP the source sp
	 * @param userDetails the user details
	 * @throws OpservDataAccessException the opserv data access exception
	 */
	void createSourceApprovers(ChangeRequest chngReq,SalesPosition sourceSP, UserDetails userDetails)throws OpservDataAccessException;
	
	/**
	 * Creates the target approvers.
	 *
	 * @param chngReq the chng req
	 * @param targetSP the target sp
	 * @param userDetails the user details
	 * @throws OpservDataAccessException the opserv data access exception
	 */
	void createTargetApprovers(ChangeRequest chngReq,SalesPosition targetSP, UserDetails userDetails)throws OpservDataAccessException;
	
	
	/**
	 * Submit change request.
	 *
	 * @param chngReq the chng req
	 * @param userDetails the user details
	 * @throws OpservDataAccessException the opserv data access exception
	 */
	void submitChangeRequests(ChangeRequest chngReq, UserDetails userDetails) throws OpservDataAccessException;
	
	
	/**
	 * @param chngReq
	 * @param userDetails
	 * @throws OpservDataAccessException
	 */
	void withdrawChangeRequest(ChangeRequest chngReq, UserDetails userDetails) throws OpservDataAccessException,CallPlanServiceException;
	void updateZipLineItemStatus(ChangeRequest chngReq, Integer status,
			PostalCode postalCode, UserDetails userDetails);
	
	/**
	 * Gets the change request target approver status.
	 *
	 * @param chngReqId the chng req id
	 * @param targetAppvrFlg the target appvr flg
	 * @param tenantId the tenant id
	 * @return the change request target approver status
	 * @throws OpservDataAccessException the opserv data access exception
	 */
	public ChangeRequestStatusType getChangeRequestTargetApproverStatus(Long chngReqId,Character targetAppvrFlg,Short tenantId)throws OpservDataAccessException;
	
	/**
	 * Gets the change requestfind approver comments.
	 *
	 * @param chngReqId the chng req id
	 * @param targetAppvrFlg the target appvr flg
	 * @param tenantId the tenant id
	 * @return the change requestfind approver comments
	 * @throws OpservDataAccessException the opserv data access exception
	 */
	public String getChangeRequestfindApproverComments(Long chngReqId,Character targetAppvrFlg,Short tenantId)throws OpservDataAccessException;
}
