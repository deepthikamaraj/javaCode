package com.cognizant.opserv.sp.service.changereq.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.opserv.sp.common.ChangeRequestStatusType;
import com.cognizant.opserv.sp.common.CommonConstants;
import com.cognizant.opserv.sp.core.entity.TChngreqOffline;
import com.cognizant.opserv.sp.event.messages.ChangeRequestMessage;
import com.cognizant.opserv.sp.event.messages.OfflineRequestMessage;
import com.cognizant.opserv.sp.exception.AlignmentServiceException;
import com.cognizant.opserv.sp.exception.ChangeRequestServiceException;
import com.cognizant.opserv.sp.messaging.GenericPublisher;
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
import com.cognizant.opserv.sp.persistence.dao.service.ChangeRequestDAOService;
import com.cognizant.opserv.sp.persistence.dao.service.CustomerAlignmentDAOService;
import com.cognizant.opserv.sp.service.changereq.ChangeRequestService;
import com.cognizant.opserv.sp.service.common.ChangeRequestCommonService;
import com.cognizant.opserv.sp.service.internal.ChangeRequestIntService;
import com.cognizant.opserv.sp.service.workflow.changereq.RestClient;
import com.cognizant.peg.core.exception.OpservDataAccessException;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
//import com.cognizant.opserv.sp.service.internal.SPAssignmentsService;

/**
 * ****************************************************************************.
 * 
 * @class ChangeRequestServiceImpl contains all the services for change request
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 * ***************************************************************************
 */
@Service("changeRequestService")
public class ChangeRequestServiceImpl implements ChangeRequestService {

	@Value("${activiti.get.tasks}") 
	private  String getTasksURL;
	 
	@Value("${activiti.process.task}") 
	private  String processTaskURL;
	/**
	 * LOGGER
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(ChangeRequestServiceImpl.class);
	
	/**
	 * changeRequestDAOService
	 */
	@Autowired
	private ChangeRequestDAOService changeRequestDAOService;
	
	@Autowired
	private CustomerAlignmentDAOService customerAlignmentDAOService;
	
	@Autowired
	private ChangeRequestCommonService crCommonService;

	/**
	 * spTargetedAssignmentsService
	 */
//	@Autowired
//	private SPAssignmentsService spAssignmentsService;
	
	@Autowired
	private ChangeRequestIntService changeRequestIntService;
	
	@Autowired
	private GenericPublisher genericPublisher;
	
	
	
	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public ChangeRequest generatePushCustomerCR(SalesPosition sourceSP, SalesPosition targetSP, UserDetails userDetails) throws ChangeRequestServiceException, AlignmentServiceException {
		return crCommonService.generatePushCustomerChangeRequest(null, sourceSP, targetSP, userDetails);
	}



	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public ChangeRequest generatePullCustomerCR(SalesPosition sourceSP, SalesPosition targetSP, UserDetails userDetails) throws ChangeRequestServiceException, AlignmentServiceException {
		return crCommonService.generatePullCustomerChangeRequest(null, sourceSP, targetSP, userDetails);
	}



	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public ChangeRequest generateEditCustomerCR(SalesPosition customerSP,  UserDetails userDetails) throws ChangeRequestServiceException {
		return crCommonService.generateEditCustomerAlignmentChangeRequest(null, customerSP, userDetails);
	}



	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public ChangeRequest generateZipMovementCR(SalesPosition sourceSP, SalesPosition targetSP, UserDetails userDetails) throws ChangeRequestServiceException {
		return crCommonService.generateZipMovementChangeRequest(null, sourceSP, targetSP, userDetails);
	}

	/**
	 * @param changeRequest
	 * @param orgCustAlgn
	 * @param newCustAlgn
	 * @param comments
	 * @param userDetails
	 * @return
	 * @throws ChangeRequestServiceException
	 */
	@Override
	@Transactional
	public OfflineRequestMessage createOffLinePushRequest(ChangeRequest changeRequest, CustomerAlignment orgCustAlgn, CustomerAlignment newCustAlgn, String comments,UserDetails userDetails) throws ChangeRequestServiceException {
		
		OfflineRequestMessage offLineReq = null;
		
		try {
				
			TChngreqOffline tChngreqOffline = customerAlignmentDAOService.saveIntoCROffline(orgCustAlgn, newCustAlgn, changeRequest,
					comments, userDetails,CommonConstants.PUSH, null, null, null);
	
			if(tChngreqOffline != null) {
				offLineReq = new  OfflineRequestMessage();
				offLineReq.setChngReqID(changeRequest.getId());
				offLineReq.setOfflineReqId(tChngreqOffline.getOfflineId());
				offLineReq.setTenantCode(userDetails.getTenantCode());
				offLineReq.setTenantId(tChngreqOffline.getTenantId());
				offLineReq.setUserDetails(userDetails);
				offLineReq.setTriggerId(tChngreqOffline.getTriggerId());
			}

		} catch (OpservDataAccessException e) {
			LOGGER.error("Exception while saving push customer offline request into t_chngreq_offline table : " + e.getMessage());
			throw new ChangeRequestServiceException("ChangeRequestServiceExceptionCode.CR_SER_EX_CD_019", "Exception while saving Push Customer Offline Request");
		}
		
		return offLineReq;
	}
	
	/**
	 * @param changeRequest
	 * @param orgCustAlgn
	 * @param newCustAlgn
	 * @param comments
	 * @param userDetails
	 * @param businessReason
	 * @param oldCustomerProductAlignments
	 * @param newCustomerProductAlignments
	 * @return
	 * @throws ChangeRequestServiceException
	 */
	@Override
	@Transactional
	public OfflineRequestMessage createOffLinePullRequest(ChangeRequest changeRequest, CustomerAlignment orgCustAlgn,
			CustomerAlignment newCustAlgn, String comments, UserDetails userDetails, BusinessReason businessReason,
			List<CustomerProductAlignment> oldCustomerProductAlignments, List<CustomerProductAlignment> newCustomerProductAlignments) throws ChangeRequestServiceException {
		
		OfflineRequestMessage offLineReq = null;
		
		try {
				TChngreqOffline tChngreqOffline = customerAlignmentDAOService.saveIntoCROffline(orgCustAlgn, newCustAlgn, changeRequest,
								comments, userDetails, CommonConstants.PULL, businessReason, oldCustomerProductAlignments, newCustomerProductAlignments);
				
				if(tChngreqOffline != null) {
					offLineReq = new  OfflineRequestMessage();
					offLineReq.setChngReqID(changeRequest.getId());
					offLineReq.setOfflineReqId(tChngreqOffline.getOfflineId());
					offLineReq.setTenantCode(userDetails.getTenantCode());
					offLineReq.setTenantId(tChngreqOffline.getTenantId());
					offLineReq.setUserDetails(userDetails);
					offLineReq.setTriggerId(tChngreqOffline.getTriggerId());
					offLineReq.setBusinessReason(businessReason);
				}

		} catch (OpservDataAccessException e) {
			LOGGER.error("Exception while saving pull customer offline request into t_chngreq_offline table : " + e.getMessage());
			throw new ChangeRequestServiceException("ChangeRequestServiceExceptionCode.CR_SER_EX_CD_019", "Exception while saving Pull Customer Offline Request");
		}		
		
		return offLineReq;
	}



	/**
	 * Submit change request.
	 *
	 * @method submitChangeRequest
	 * @param chngReq the chng req
	 * @param userDetails the user details
	 * @throws ChangeRequestServiceException the change request service exception
	 * @method submitChangeRequest
	 */
	@Override
	@Transactional
	public void submitChangeRequest(ChangeRequest chngReq, UserDetails userDetails) throws ChangeRequestServiceException {
		if (null != chngReq && null != chngReq.getId() && null != userDetails && null != userDetails.getTenantId() && null != userDetails.getUserId()) {
			LOGGER.info("submitChangeRequest online started :: crId:"+chngReq.getId());
			try {
				List<Long> parentChangeRequestIds = changeRequestDAOService.findParentChangeRequestIdByChildChangeRequestId(chngReq, userDetails);
				if (parentChangeRequestIds.size() > 0) {
					Object[] args = new Object[1];
					args[0] = "Mirrored ChangeRequest Id = " + chngReq.getId() + " Can't submit";
					throw new ChangeRequestServiceException(args);
				} else {
					ChangeRequest chnageRequest = changeRequestDAOService.getChangeRequestByChangeRequestId(chngReq);
					if (chnageRequest.getStatus().getId() == ChangeRequestStatusType.PENDING_FOR_SUBMISSION.getId()) {
						// update CR status as 11 SUBMISSION_IN_PROGRESS
						changeRequestIntService.updateChangeRequestStatus(chnageRequest.getId(), ChangeRequestStatusType.SUBMISSION_IN_PROGRESS.getId(), userDetails);
						ChangeRequestMessage changeRequestMessage = new ChangeRequestMessage();
						changeRequestMessage.setChngReqID(chngReq.getId());
						changeRequestMessage.setUserDetails(userDetails);
						changeRequestMessage.setTenantId(userDetails.getTenantId());
						changeRequestMessage.setTenantCode(userDetails.getTenantCode());
						LOGGER.info("Pushing message to ONLINE_CHNAGE_REQUEST_SUBMIT_QUEUE");
						genericPublisher.sendObjectToQueue(CommonConstants.ONLINE_CHNAGE_REQUEST_SUBMIT_QUEUE, changeRequestMessage);
					} else {
						Object[] args = new Object[1];
						args[0] = "Status should be in PENDING_FOR_SUBMISSION for CR_ID = " + chngReq.getId();
						throw new ChangeRequestServiceException(args);
					}
				}
			} catch (OpservDataAccessException exception) {
				Object[] args = new Object[1];
				args[0] = chngReq.getId();
				throw new ChangeRequestServiceException(ChangeRequestServiceException.ChangeRequestServiceExceptionCode.CR_SER_EX_CD_001, "", args, exception);
			}
		} else {
			Object[] args = new Object[1];
			args[0] = "Either chngReq or chngReqId or  userDetails or userID or TenantID is null";
			throw new ChangeRequestServiceException(args);

		}

	}

	

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
	@Override
	@Transactional(readOnly = true)
	public List<ChangeRequest> getAllChangeRequestsForApproval(SalesPosition salesPostion, UserDetails userDetails) throws ChangeRequestServiceException {
		List<ChangeRequest> changeRequestsList = null;
		if (null != salesPostion && null != salesPostion.getId() && null != salesPostion.getHierarchy() && null != salesPostion.getHierarchy().getId() && null != salesPostion.getTenantId()) {
			try {
				changeRequestsList = changeRequestDAOService.getAllChangeRequestsForApproval(salesPostion, userDetails);
			} catch (OpservDataAccessException exception) {
				Object[] args = new Object[1];
				args[0] = salesPostion.getId();
				throw new ChangeRequestServiceException(ChangeRequestServiceException.ChangeRequestServiceExceptionCode.CR_SER_EX_CD_001, "", args, exception);
			}
		} else {
			Object[] args = new Object[1];
			args[0] = "Either salesPostion or salesPostionId or salesHierarchyId or TenantId is null";
			throw new ChangeRequestServiceException(args);
		}
		return changeRequestsList;
	}

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
	@Override
	@Transactional(readOnly = true)
	public List<ChangeRequest> getAllChangeRequests(SalesPosition salesPostion, UserDetails userDetails) throws ChangeRequestServiceException {
		List<ChangeRequest> changeRequestsList = null;
		if (null != salesPostion && null != salesPostion.getId() && null != salesPostion.getHierarchy() && null != salesPostion.getHierarchy().getId() && null != salesPostion.getTenantId()) {
			try {
				changeRequestsList = changeRequestDAOService.getAllChangeRequests(salesPostion, userDetails);
			} catch (OpservDataAccessException exception) {
				Object[] args = new Object[1];
				args[0] = salesPostion.getId();
				throw new ChangeRequestServiceException(ChangeRequestServiceException.ChangeRequestServiceExceptionCode.CR_SER_EX_CD_001, "", args, exception);
			}
		} else {
			Object[] args = new Object[1];
			args[0] = "Either salesPostion or salesPostionId or salesHierarchyId or TenantId is null";
			throw new ChangeRequestServiceException(args);
		}
		return changeRequestsList;
	}

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
	@Override
	@Transactional(readOnly = true)
	public List<ChangeRequest> getAllChangeRequestsOfCustomer(Customer customer, SalesPosition salesPostion, UserDetails userDetails) throws ChangeRequestServiceException {
		List<ChangeRequest> changeReqestLst = null;
		if (null != customer && null != salesPostion && null != customer.getId() && null != salesPostion.getId() && null != salesPostion.getHierarchy() && null != salesPostion.getHierarchy().getId()
				&& null != salesPostion.getAlignmment() && null != salesPostion.getAlignmment().getId() && null != salesPostion.getAlignmment().getSalesTeam() && null != salesPostion.getAlignmment().getSalesTeam().getId() &&  null != salesPostion.getAlignmment().getSalesTeam().getBusinessUnit() && null != salesPostion.getAlignmment().getSalesTeam().getBusinessUnit().getId()
				&& null != userDetails && null != userDetails.getTenantId()) {
			try {
				changeReqestLst = changeRequestDAOService.getAllChangeRequestsOfCustomer(customer, salesPostion, userDetails);
			} catch (OpservDataAccessException exception) {
				Object[] args = new Object[1];
				args[0] = customer.getId();
				throw new ChangeRequestServiceException(ChangeRequestServiceException.ChangeRequestServiceExceptionCode.CR_SER_EX_CD_001, "", args, exception);
			}
		} else {
			Object[] args = new Object[1];
			args[0] = "Either customer or salesPostion or customerId or salesPostionId or salesPostionHierId or AlignmentId or BuId or tenantId is null";
			throw new ChangeRequestServiceException(args);
		}
		return changeReqestLst;
	}

	/**
	 * Withdraw change request.
	 *
	 * @method withdrawChangeRequest
	 * @param chngReq the chng req
	 * @param userDetails the user details
	 * @throws ChangeRequestServiceException the change request service exception
	 * @method withdrawChangeRequest
	 */
	@Override
	@Transactional
	public void withdrawChangeRequest(ChangeRequest chngReq, UserDetails userDetails) throws ChangeRequestServiceException {
		if (null != chngReq && null != chngReq.getId() && null != userDetails && null != userDetails.getTenantId() && null != userDetails.getUserId()) {
			LOGGER.info("withdrawChangeRequest online started :: crId:"+chngReq.getId());
			try {
				List<Long> parentChangeRequestIds = changeRequestDAOService.findParentChangeRequestIdByChildChangeRequestId(chngReq, userDetails);
				if (parentChangeRequestIds.size() > 0) {
					Object[] args = new Object[1];
					args[0] = "Mirrored ChangeRequest Id = " + chngReq.getId() + " Can't Withdraw";
					throw new ChangeRequestServiceException(args);
				} else {
					ChangeRequest chnageRequest = changeRequestDAOService.getChangeRequestByChangeRequestId(chngReq);
					if (chnageRequest.getStatus().getId() == ChangeRequestStatusType.PENDING_FOR_SUBMISSION.getId()
							|| chnageRequest.getStatus().getId() == ChangeRequestStatusType.WORK_IN_PROGRESS.getId()
							|| chnageRequest.getStatus().getId() == ChangeRequestStatusType.SUBMISSION_IN_PROGRESS.getId()) {
						changeRequestIntService.updateChangeRequestStatus(chnageRequest.getId(), ChangeRequestStatusType.WITHDRAWL_IN_PROGRESS.getId(), userDetails);
						ChangeRequestMessage changeRequestMessage = new ChangeRequestMessage();
						changeRequestMessage.setChngReqID(chngReq.getId());
						changeRequestMessage.setUserDetails(userDetails);
						LOGGER.info("Pushing message to ONLINE_CHNAGE_REQUEST_WITHDRAWL_QUEUE");
						genericPublisher.sendObjectToQueue(CommonConstants.ONLINE_CHNAGE_REQUEST_WITHDRAWL_QUEUE, changeRequestMessage);
					} else {
						Object[] args = new Object[1];
						args[0] = "Status should be in PENDING_FOR_SUBMISSION or WORK_IN_PROGRESS or SUBMISSION_IN_PROGRESS for CR_ID = " + chngReq.getId();
						throw new ChangeRequestServiceException(args);
					}
				}
			} catch (OpservDataAccessException exception) {
				Object[] args = new Object[1];
				args[0] = chngReq.getId();
				throw new ChangeRequestServiceException(ChangeRequestServiceException.ChangeRequestServiceExceptionCode.CR_SER_EX_CD_001, "", args, exception);
			}
		} else {
			Object[] args = new Object[1];
			args[0] = "Either chngReq or chngReqId or userDetails or userID or TenantID is null";
			throw new ChangeRequestServiceException(args);

		}

	}


	/**
	 * Gets the customer alignment change request details Line Items.
	 *
	 * @method getAllChangeRequestsLineItemsbyCRId
	 * @param chngReq the chng req
	 * @param userDetails the user details
	 * @return List
	 * @throws ChangeRequestServiceException the change request service exception
	 * @method getAllChangeRequestsLineItemsbyCRId
	 */
	@Override
	@Transactional(readOnly = true)
	public ChangeRequest getAllChangeRequestsLineItemsbyChangeRequestId(ChangeRequest chngReq, UserDetails userDetails) throws ChangeRequestServiceException {
		ChangeRequest changeRequest = null;
		if (null != chngReq && null != chngReq.getId() && null!= userDetails && null != userDetails.getTenantId()) {
			try {
				changeRequest = changeRequestDAOService.getChangeRequestLineItemsByChangeRequestId(chngReq);
			} catch (OpservDataAccessException exception) {
				Object[] args = new Object[1];
				args[0] = chngReq.getId();
				throw new ChangeRequestServiceException(ChangeRequestServiceException.ChangeRequestServiceExceptionCode.CR_SER_EX_CD_001, "", args, exception);
			}
		} else {
			Object[] args = new Object[1];
			args[0] = "Either changeRequestId or tenantId is null";
			throw new ChangeRequestServiceException(args);
		}
		return changeRequest;
	}

	/**
	 * Rejects change request.
	 *
	 * @method rejectChangeRequest
	 * @param chngReq the chng req
	 * @param userDetails the user details
	 * @throws ChangeRequestServiceException the change request service exception
	 * @method rejectChangeRequest
	 */
	@Override
	@Transactional
	public void rejectChangeRequest(ChangeRequest chngReq, UserDetails userDetails, SalesPosition salesPosition,String comments) throws ChangeRequestServiceException {

		if (null != chngReq && null != chngReq.getId() && null != userDetails && null != userDetails.getTenantId() && null != userDetails.getUserId() && null != salesPosition
				&& null != salesPosition.getHierarchy() && null != salesPosition.getId() && null != salesPosition.getHierarchy().getId()) {
			LOGGER.info("rejectChangeRequest online started :: crId:"+chngReq.getId());
			try {
				List<Long> parentChangeRequestIds = changeRequestDAOService.findParentChangeRequestIdByChildChangeRequestId(chngReq, userDetails);
				if (parentChangeRequestIds.size() > 0) {
					Object[] args = new Object[1];
					args[0] = "Mirrored ChangeRequest Id = " + chngReq.getId() + " Can't Reject";
					throw new ChangeRequestServiceException(args);
				} else {
					ChangeRequest chnageRequest = changeRequestDAOService.getChangeRequestByChangeRequestId(chngReq);
					if (chnageRequest.getStatus().getId() == ChangeRequestStatusType.PENDING_FOR_APPROVAL.getId()) {
						changeRequestIntService.updateChangeRequestStatus(chnageRequest.getId(), ChangeRequestStatusType.REJECTION_IN_PROGRESS.getId(), userDetails);
						ChangeRequestMessage changeRequestMessage = new ChangeRequestMessage();
						changeRequestMessage.setChngReqID(chngReq.getId());
						changeRequestMessage.setUserDetails(userDetails);
						changeRequestMessage.setSalesPostion(salesPosition);
						changeRequestMessage.setSalesPosID(salesPosition.getId());
						changeRequestMessage.setComments(comments);
						changeRequestMessage.setSalesHierID(salesPosition.getHierarchy().getId());
						LOGGER.info("Pushing message to ONLINE_CHNAGE_REQUEST_REJECT_QUEUE");
						genericPublisher.sendObjectToQueue(CommonConstants.ONLINE_CHNAGE_REQUEST_REJECT_QUEUE, changeRequestMessage);
					} else {
						Object[] args = new Object[1];
						args[0] = "Status should be in PENDING_FOR_APPROVAL for CR_ID = " + chngReq.getId();
						throw new ChangeRequestServiceException(args);
					}
				}
			} catch (OpservDataAccessException exception) {
				Object[] args = new Object[1];
				args[0] = chngReq.getId();
				throw new ChangeRequestServiceException(ChangeRequestServiceException.ChangeRequestServiceExceptionCode.CR_SER_EX_CD_001, "", args, exception);
			}
		} else {
			Object[] args = new Object[1];
			args[0] = "Either chngReq or chngReqId or  userDetails or userID or TenantID or salesPosition id or hirarchy id is null";
			throw new ChangeRequestServiceException(args);

		}

	}
	
		
	/**
	 * Approve change request.
	 *
	 * @method approveChangeRequest
	 * @param chngReq the chng req
	 * @param userDetails the user details
	 * @throws ChangeRequestServiceException the change request service exception
	 * @method approveChangeRequest
	 */
	@Override
	@Transactional
	public void approveChangeRequest(ChangeRequest chngReq, UserDetails userDetails, SalesPosition salesPosition,String comments) throws ChangeRequestServiceException {

		if (null != chngReq && null != chngReq.getId() && null != userDetails && null != userDetails.getTenantId() && null != userDetails.getUserId() && null != salesPosition
				&& null != salesPosition.getHierarchy() && null != salesPosition.getId() && null != salesPosition.getHierarchy().getId()) {
			LOGGER.info("approveChangeRequest online started :: crId:"+chngReq.getId());
			try {
				List<Long> parentChangeRequestIds = changeRequestDAOService.findParentChangeRequestIdByChildChangeRequestId(chngReq, userDetails);
				if (parentChangeRequestIds.size() > 0) {
					Object[] args = new Object[1];
					args[0] = "Mirrored ChangeRequest Id = " + chngReq.getId() + " Can't Approve";
					throw new ChangeRequestServiceException(args);
				} else {
					ChangeRequest chnageRequest = changeRequestDAOService.getChangeRequestByChangeRequestId(chngReq);
					if (chnageRequest.getStatus().getId() == ChangeRequestStatusType.PENDING_FOR_APPROVAL.getId()) {
						changeRequestIntService.updateChangeRequestStatus(chnageRequest.getId(), ChangeRequestStatusType.APPROVAL_IN_PROGRESS.getId(), userDetails);
						ChangeRequestMessage changeRequestMessage = new ChangeRequestMessage();
						changeRequestMessage.setChngReqID(chngReq.getId());
						changeRequestMessage.setUserDetails(userDetails);
						changeRequestMessage.setSalesPosID(salesPosition.getId());
						changeRequestMessage.setComments(comments);
						changeRequestMessage.setSalesHierID(salesPosition.getHierarchy().getId());
						LOGGER.info("Pushing message to ONLINE_CHNAGE_REQUEST_APPROVE_QUEUE");
						genericPublisher.sendObjectToQueue(CommonConstants.ONLINE_CHNAGE_REQUEST_APPROVE_QUEUE, changeRequestMessage);
					} else {
						Object[] args = new Object[1];
						args[0] = "Status should be in PENDING_FOR_APPROVAL for CR_ID = " + chngReq.getId();
						throw new ChangeRequestServiceException(args);
					}
				}
			} catch (OpservDataAccessException exception) {
				Object[] args = new Object[1];
				args[0] = chngReq.getId();
				throw new ChangeRequestServiceException(ChangeRequestServiceException.ChangeRequestServiceExceptionCode.CR_SER_EX_CD_001, "", args, exception);
			}
		} else {
			Object[] args = new Object[1];
			args[0] = "Either chngReq or chngReqId or  userDetails or userID or TenantID or salesPosition id or hirarchy id  is null";
			throw new ChangeRequestServiceException(args);

		}
	}

	/**
	 * @param chngReq
	 * @param userDetails
	 * @return List
	 * @throws ChangeRequestServiceException
	 */
	@Override
	@Transactional(readOnly = true)
	public List<CustomerAlignmentChangeRequestDetails> getCustomerAlignmentChangeRequestDetailsByChangeRequest(ChangeRequest chngReq, UserDetails userDetails) throws ChangeRequestServiceException {
		List<CustomerAlignmentChangeRequestDetails> changeRequestLineItems = null;
		if (null != chngReq && null != chngReq.getId() && null != userDetails && null != userDetails.getTenantId()) {
			try {
				changeRequestLineItems = changeRequestDAOService.getCustomerAlignmentChangeRequestDetailsByChangeRequest(chngReq);
			} catch (OpservDataAccessException exception) {
				Object[] args = new Object[1];
				args[0] = chngReq.getId();
				throw new ChangeRequestServiceException(ChangeRequestServiceException.ChangeRequestServiceExceptionCode.CR_SER_EX_CD_001, "", args, exception);
			}
		} else {
			Object[] args = new Object[1];
			args[0] = "Either changeRequestId or tenantId is null";
			throw new ChangeRequestServiceException(args);
		}
		return changeRequestLineItems;
	}


	/**
	 * @param chngReq
	 * @param userDetails
	 * @return List
	 * @throws ChangeRequestServiceException
	 */
	@Override
	@Transactional(readOnly = true)
	public List<ZipAlignmentChangeRequestDetails> getZipAlignmentChangeRequestDetailsByChangeRequest(ChangeRequest chngReq, UserDetails userDetails) throws ChangeRequestServiceException {
		List<ZipAlignmentChangeRequestDetails> changeRequestLineItems = null;
		if (null != chngReq && null != chngReq.getId() && null != userDetails && null != userDetails.getTenantId()) {
			try {
				changeRequestLineItems = changeRequestDAOService.getZipAlignmentChangeRequestDetailsByChangeRequest(chngReq);
			} catch (OpservDataAccessException exception) {
				Object[] args = new Object[1];
				args[0] = chngReq.getId();
				throw new ChangeRequestServiceException(ChangeRequestServiceException.ChangeRequestServiceExceptionCode.CR_SER_EX_CD_001, "", args, exception);
			}
		} else {
			Object[] args = new Object[1];
			args[0] = "Either changeRequestId or tenantId is null";
			throw new ChangeRequestServiceException(args);
		}
		return changeRequestLineItems;
	}

	/**
	 * Sets the change request dao service.
	 *
	 * @param changeRequestDAOService the new change request dao service
	 */
	public void setChangeRequestDAOService(ChangeRequestDAOService changeRequestDAOService) {
		this.changeRequestDAOService = changeRequestDAOService;
	}


//	/**
//	 * @param spAssignmentsService the spAssignmentsService to set
//	 */
//	public void setSpAssignmentsService(SPAssignmentsService spAssignmentsService) {
//		this.spAssignmentsService = spAssignmentsService;
//	}

	/**
	 * @param changeRequestIntService the changeRequestIntService to set
	 */
	public void setChangeRequestIntService(ChangeRequestIntService changeRequestIntService) {
		this.changeRequestIntService = changeRequestIntService;
	}

	/** (non-Javadoc)
	 * @see com.cognizant.opserv.sp.service.changereq.ChangeRequestService#getChangeRequestsTasksFromApproverSalesPosition(com.cognizant.opserv.sp.model.SalesPosition, com.cognizant.opserv.sp.model.auth.UserDetails)
	 */
	@Override
	public List<String> getChangeRequestsTasksFromApproverSalesPosition(SalesPosition salesPosition, UserDetails userDetails) throws ChangeRequestServiceException {
		LOGGER.info("In ChangeRequestProcessInternalServiceImpl :: getTasksURL -->" + getTasksURL);
		return RestClient.getTaskListFromWorkFlow(salesPosition, userDetails, getTasksURL);
	}

	/* (non-Javadoc)
	 * @see com.cognizant.opserv.sp.service.changereq.ChangeRequestService#changeRequestsMarkAsDirty(Long, UserDetails)
	 */
	@Override
	public boolean changeRequestsMarkAsDirty(Long spId, UserDetails userDetails)
			throws ChangeRequestServiceException {
		if (null != spId && null != userDetails && null != userDetails.getTenantId()) {
			try {
				return changeRequestDAOService.markCRsAsDirty(spId, userDetails);
			} catch (OpservDataAccessException exception) {
				Object[] args = new Object[1];
				args[0] = " Exception occured while making CR as dirty for spId " + spId;
				throw new ChangeRequestServiceException(args);
			}
		} else {
			Object[] args = new Object[1];
			args[0] = "Either salesPositionId or tenantId is null";
			throw new ChangeRequestServiceException(args);
		}
	}



	/* (non-Javadoc)
	 * @see com.cognizant.opserv.sp.service.changereq.ChangeRequestService#getChangeRequestApproversDetails(com.cognizant.opserv.sp.model.cr.ChangeRequest, com.cognizant.opserv.sp.model.auth.UserDetails)
	 */
	@Override
	@Transactional(readOnly = true)
	public List<ChangeRequestApproverDetails> getChangeRequestApproversDetails(ChangeRequest changeRequest, UserDetails userDetails) throws ChangeRequestServiceException {
		try {
			if (null == changeRequest || null == changeRequest.getId() || null == userDetails || null == userDetails.getTenantId()) {
				String params = "Missing changeRequest Id / tenant id";
				Object[] args = new Object[] { params };
				LOGGER.error("Missing  changeRequest Id / tenant id");
				throw new ChangeRequestServiceException(args);
			}
			return changeRequestDAOService.getCRApproversDetails(changeRequest, userDetails);
		} catch (OpservDataAccessException e) {
			Object[] args = new Object[] { "Exception occured while fetching CR approvers details!!!(CR id:: " + changeRequest.getId() + ")" };
			LOGGER.error("Exception occured while fetching CR approvers details!!!(CR id:: " + changeRequest.getId() + ")");
			throw new ChangeRequestServiceException(args);
		}

	}


	/**
	 * @param changeRequest
	 * @param updatedCustAlgn
	 * @param newCustAlgn
	 * @param comments
	 * @param userDetails
	 * @param businessReason
	 * @param oldCustomerProductAlignments
	 * @param newCustomerProductAlignments
	 * @return
	 * @throws ChangeRequestServiceException
	 */
	@Override
	@Transactional
	public OfflineRequestMessage createOffLineEditRequest(
			ChangeRequest changeRequest, CustomerAlignment updatedCustAlgn, CustomerAlignment newCustAlgn, String comments,
			UserDetails userDetails, BusinessReason businessReason, List<CustomerProductAlignment> oldCustomerProductAlignments, List<CustomerProductAlignment> newCustomerProductAlignments)
			throws ChangeRequestServiceException {
		
		OfflineRequestMessage offLineReq = null;
		
		try {
				TChngreqOffline tChngreqOffline = customerAlignmentDAOService.saveIntoCROffline(updatedCustAlgn, newCustAlgn, changeRequest,
								comments, userDetails, CommonConstants.EDIT, businessReason, oldCustomerProductAlignments, newCustomerProductAlignments);
				
				if(tChngreqOffline != null) {
					offLineReq = new  OfflineRequestMessage();
					offLineReq.setChngReqID(changeRequest.getId());
					offLineReq.setOfflineReqId(tChngreqOffline.getOfflineId());
					offLineReq.setTenantCode(userDetails.getTenantCode());
					offLineReq.setTenantId(tChngreqOffline.getTenantId());
					offLineReq.setUserDetails(userDetails);
					offLineReq.setTriggerId(tChngreqOffline.getTriggerId());
					offLineReq.setBusinessReason(businessReason);
				}

		} catch (OpservDataAccessException e) {
			LOGGER.error("Exception while saving edit customer offline request into t_chngreq_offline table : " + e.getMessage());
			throw new ChangeRequestServiceException("ChangeRequestServiceExceptionCode.CR_SER_EX_CD_019", "Exception while saving Edit Customer Offline Request");
		}
	
		return offLineReq;
	}	

}
