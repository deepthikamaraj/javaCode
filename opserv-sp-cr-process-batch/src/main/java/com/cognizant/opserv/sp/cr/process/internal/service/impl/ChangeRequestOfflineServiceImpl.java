package com.cognizant.opserv.sp.cr.process.internal.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.opserv.common.domain.MessageTemplate;
import com.cognizant.opserv.sp.common.ChangeRequestStatusType;
import com.cognizant.opserv.sp.common.CommonConstants;
import com.cognizant.opserv.sp.common.EventType;
import com.cognizant.opserv.sp.cr.process.dto.CustomerAlignmentDTO;
import com.cognizant.opserv.sp.cr.process.dto.ErrorCode;
import com.cognizant.opserv.sp.cr.process.dto.ErrorDTO;
import com.cognizant.opserv.sp.cr.process.dto.GeographyAlignmentDTO;
import com.cognizant.opserv.sp.cr.process.internal.service.ChangeRequestOfflineService;
import com.cognizant.opserv.sp.cr.process.internal.service.ChangeRequestUpdateService;
import com.cognizant.opserv.sp.cr.process.internal.service.CustomerEditOfflineService;
import com.cognizant.opserv.sp.cr.process.util.RestClient;
import com.cognizant.opserv.sp.event.messages.ChangeRequestMessage;
import com.cognizant.opserv.sp.exception.AffiliationServiceException;
import com.cognizant.opserv.sp.exception.AlignmentServiceException;
import com.cognizant.opserv.sp.exception.CallPlanServiceException;
import com.cognizant.opserv.sp.exception.ChangeRequestServiceException;
import com.cognizant.opserv.sp.exception.ChangeRequestServiceException.ChangeRequestServiceExceptionCode;
import com.cognizant.opserv.sp.exception.CustomerServiceException;
import com.cognizant.opserv.sp.exception.MetricExecutionException;
import com.cognizant.opserv.sp.exception.MetricViolationException;
import com.cognizant.opserv.sp.exception.SalesPositionServiceException;
import com.cognizant.opserv.sp.exception.ViewServiceException;
import com.cognizant.opserv.sp.messaging.GenericPublisher;
import com.cognizant.opserv.sp.model.PostalCode;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.model.cr.ChangeRequest;
import com.cognizant.opserv.sp.model.cr.ChangeRequestApproverDetails;
import com.cognizant.opserv.sp.model.cr.CustomerAlignmentChangeRequestDetails;
import com.cognizant.opserv.sp.model.cr.ZipAlignmentChangeRequestDetails;
import com.cognizant.opserv.sp.persistence.dao.service.ChangeRequestDAOService;
import com.cognizant.opserv.sp.persistence.dao.service.ChangeRequestOfflineDAOService;
import com.cognizant.opserv.sp.service.notification.CRTriggerNotificationService;
import com.cognizant.opserv.sp.service.util.NotificationUtil;
import com.cognizant.peg.core.exception.OpservDataAccessException;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;


/**
 * The Class ChangeRequestOfflineServiceImpl.
 */
@Service
public class ChangeRequestOfflineServiceImpl implements
		ChangeRequestOfflineService {

	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(ChangeRequestOfflineServiceImpl.class);
	
	/** The change request dao service. */
	@Autowired
	private ChangeRequestDAOService changeRequestDAOService;
	
	/**
	 * changeRequestBatchInternalService
	 */
	@Autowired
	private ChangeRequestUpdateService changeRequestBatchInternalService;
	
	/**
	 * notificationUtil
	 */
	@Autowired
	private NotificationUtil notificationUtil;
	
	/**
	 * genericPublisher
	 */
	@Autowired
	private GenericPublisher genericPublisher;
	
	
	/**
	 * tempChngreqDAOService
	 */
	@Autowired
	private ChangeRequestOfflineDAOService tempChngreqDAOService;
	
	
	/**
	 * customerEditOfflineService
	 */
	@Autowired
	private CustomerEditOfflineService customerEditOfflineService;
	
	
	@Autowired
	private CRTriggerNotificationService crNotifyService;
	
	@Value("${activiti.get.tasks}") 
	private  String getTasksURL;
	 
	@Value("${activiti.process.task}") 
	private  String processTaskURL;
	
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
	@Override
	public void processLineItemForSourceCustomer(
			List<CustomerAlignmentChangeRequestDetails> crLineItems,
			UserDetails userDetails) throws ChangeRequestServiceException{
		
		try {
			if(null == crLineItems || null == userDetails || null == userDetails.getTenantId()){
				String params = "Missing crLineItems / tenant id";
				Object[] args = new Object[]{params};
				LOGGER.error("Missing crLineItems / tenant id");
				throw new  ChangeRequestServiceException(args);
			}
			changeRequestDAOService.createLineItemForSourceCustomer(crLineItems, userDetails);
		} catch(OpservDataAccessException e) {
			String params = "Error during processing LineItem for source customer.";
			Object[] args = new Object[]{params};
			LOGGER.error("Error during processing LineItem for source customer.");
		    throw new ChangeRequestServiceException(args);
		}
		
		
	}

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
	@Override
	public void processLineItemForTargetCustomer(
			List<CustomerAlignmentChangeRequestDetails> crLineItems,
			UserDetails userDetails) throws ChangeRequestServiceException {
		try {
			if(null == crLineItems || null == userDetails || null == userDetails.getTenantId()){
				String params = "Missing crLineItems / tenant id";
				Object[] args = new Object[]{params};
				LOGGER.error("Missing crLineItems / tenant id");
				throw new  ChangeRequestServiceException(args);
			}
			changeRequestDAOService.createLineItemForTargetCustomer(crLineItems, userDetails);
		} catch(OpservDataAccessException e) {
			String params = "Error during processing LineItem for target customer.";
			Object[] args = new Object[]{params};
			LOGGER.error("Error during processing LineItem for target customer.");
		    throw new ChangeRequestServiceException(args);
		}
	}

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
	@Override
	@Transactional
	public void processLineItemForSourceZip(
			List<ZipAlignmentChangeRequestDetails> crLineItems,
			UserDetails userDetails) throws ChangeRequestServiceException {
		try {
			if(null == crLineItems || null == userDetails || null == userDetails.getTenantId()){
				String params = "Missing crLineItems / tenant id";
				Object[] args = new Object[]{params};
				LOGGER.error("Missing crLineItems / tenant id");
				throw new ChangeRequestServiceException(
						ChangeRequestServiceExceptionCode.CR_SER_EX_CD_012,
						"Missing crLineItems / tenant id", args, null);
			}
			changeRequestDAOService.createLineItemForSourceZip(crLineItems, userDetails);
		} catch(OpservDataAccessException e) {
			String params = "Error during processing LineItem for source zip movement.";
			Object[] args = new Object[]{params};
			LOGGER.error("Error during processing LineItem for source zip movement.");
		    throw new ChangeRequestServiceException(
					ChangeRequestServiceExceptionCode.CR_SER_EX_CD_013,
					"Error during processing LineItem for source zip movement", args, null);
		}
		
		
	}

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
	@Override
	@Transactional
	public void processLineItemForTargetZip(
			List<ZipAlignmentChangeRequestDetails> crLineItems,
			UserDetails userDetails) throws ChangeRequestServiceException {
		try {
			if(null == crLineItems || null == userDetails || null == userDetails.getTenantId()){
				String params = "Missing crLineItems / tenant id";
				Object[] args = new Object[]{params};
				LOGGER.error("Missing crLineItems / tenant id");
				throw new ChangeRequestServiceException(
						ChangeRequestServiceExceptionCode.CR_SER_EX_CD_012,
						"Missing crLineItems / tenant id", args, null);
			}
			changeRequestDAOService.createLineItemForTargetZip(crLineItems, userDetails);
		} catch(OpservDataAccessException e) {
			String params = "Error during processing LineItem for target zip movement.";
			Object[] args = new Object[]{params};
			LOGGER.error("Error during processing LineItem for target zip movement.");
		    throw new ChangeRequestServiceException(
					ChangeRequestServiceExceptionCode.CR_SER_EX_CD_013,
					"Error during processing LineItem for target zip movement.", args, null);
		}
	}


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
	@Override
	public void assignApproversForSource(ChangeRequest chngReq,
			SalesPosition sourceSP, UserDetails userDetails) throws ChangeRequestServiceException {
		try {
			if(null == chngReq || null == sourceSP || null == userDetails || null == userDetails.getTenantId()){
				String params = "Missing chngReq/ sourceSP / tenant id";
				Object[] args = new Object[]{params};
				LOGGER.error("Missing chngReq/ sourceSP / tenant id");
				throw new  ChangeRequestServiceException(args);
			}
			changeRequestDAOService.createSourceApprovers(chngReq, sourceSP, userDetails);
		} catch(OpservDataAccessException e) {
			String params = "Error during assigning approvers for source.";
			Object[] args = new Object[]{params};
			LOGGER.error("Error during assigning approvers for source.");
		    throw new ChangeRequestServiceException(args);
		}
	}

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
	@Override
	public void assignApproversForTarget(ChangeRequest chngReq,
			SalesPosition targetSP, UserDetails userDetails) throws ChangeRequestServiceException{
		try {
			if(null == chngReq || null == targetSP || null == userDetails || null == userDetails.getTenantId()){
				String params = "Missing chngReq/ targetSP/ tenant id";
				Object[] args = new Object[]{params};
				LOGGER.error("Missing chngReq/ targetSP/ tenant id");
				throw new  ChangeRequestServiceException(args);
			}
			changeRequestDAOService.createTargetApprovers(chngReq, targetSP, userDetails);
		} catch(OpservDataAccessException e) {
			String params = "Error during assigning approvers for target.";
			Object[] args = new Object[]{params};
			LOGGER.error("Error during assigning approvers for target.");
		    throw new ChangeRequestServiceException(args);
		}
	}
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
	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void updateCRandInitiateWorkflow(CustomerAlignmentDTO custAlgmtDTO,
			UserDetails userDetails) throws ChangeRequestServiceException{
		try {
			if (null == custAlgmtDTO || null == userDetails
					|| null == userDetails.getTenantId()) {
				LOGGER.error("Missing customer alignment / tenant id");
				List<ErrorDTO> errors = new ArrayList<ErrorDTO>();
				ErrorDTO errorDTO = new ErrorDTO();
				errorDTO.setErrorCode(ErrorCode.ERROR_2006);
				errors.add(errorDTO);
				custAlgmtDTO.setErrors(errors);
			} else {
				changeRequestDAOService.submitChangeRequests(custAlgmtDTO.getMainCR(), userDetails);
			}
		} catch(OpservDataAccessException e) {
			LOGGER.error("Error during initialize CR workflow for zip movement.");
			List<ErrorDTO> errors = new ArrayList<ErrorDTO>();
			ErrorDTO errorDTO = new ErrorDTO();
			errorDTO.setErrorCode(ErrorCode.ERROR_ZM_2007);
			errors.add(errorDTO);
			custAlgmtDTO.setErrors(errors);
		}
		

	}

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
	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void updateCRandInitiateWorkflow(GeographyAlignmentDTO geoAlgmtDTO,
			UserDetails userDetails) throws ChangeRequestServiceException{
		try {
			if (null == geoAlgmtDTO || null == userDetails
					|| null == userDetails.getTenantId()) {
				LOGGER.error("Missing  geography alignment / tenant id");
				List<ErrorDTO> errors = new ArrayList<ErrorDTO>();
				ErrorDTO errorDTO = new ErrorDTO();
				errorDTO.setErrorCode(ErrorCode.ERROR_2005);
				errors.add(errorDTO);
				geoAlgmtDTO.setErrors(errors);
			} else {
				changeRequestDAOService.submitChangeRequests(
						geoAlgmtDTO.getMainCR(), userDetails);
			}
		} catch(OpservDataAccessException e) {
			LOGGER.error("Error during initialize CR workflow for zip movement.");
			List<ErrorDTO> errors = new ArrayList<ErrorDTO>();
			ErrorDTO errorDTO = new ErrorDTO();
			errorDTO.setErrorCode(ErrorCode.ERROR_ZM_2007);
			errors.add(errorDTO);
			geoAlgmtDTO.setErrors(errors);
		}
		
	}
	
	
	public void approveChangeRequest(ChangeRequest chngReq, UserDetails userDetails, SalesPosition salesPosition)
		throws ChangeRequestServiceException, AffiliationServiceException, SalesPositionServiceException, CustomerServiceException, CallPlanServiceException {

		if (null != chngReq && null != chngReq.getId() && null != userDetails && null != userDetails.getTenantId() && null != userDetails.getUserId()) {
			try {
				ChangeRequest chnageRequest = changeRequestDAOService.getChangeRequestByChangeRequestId(chngReq);
				if (!chnageRequest.getStatus().equals(ChangeRequestStatusType.APPROVED)) {
					LOGGER.info("approveChangeRequest offline started  :: crId:" + chngReq.getId());
					chngReq = changeRequestBatchInternalService.approveChangeRequestTransaction(chngReq, userDetails, salesPosition);
					ChangeRequest changeRequest = new ChangeRequest();
					changeRequest.setId(chngReq.getId());
					changeRequest.setStatus(ChangeRequestStatusType.PENDING_FOR_APPROVAL);
					Long count = changeRequestDAOService.fetchTChngReqApprsTypeByStatus(changeRequest, userDetails);
					if (count == 0) {
						changeRequestBatchInternalService.autoApproveChangeRequest(
							chngReq, userDetails, ChangeRequestStatusType.APPROVED.getId(), ChangeRequestStatusType.APPROVED.getId());
						// ---------------For Edit cust call plan --------------
						changeRequestBatchInternalService.updateCustSalesTeamAndCustPrdSalesTeam(chngReq, userDetails);
						changeRequestBatchInternalService.updateMirrorCustSalesTeamAndCustSalesTeam(chngReq, userDetails);
						changeRequestBatchInternalService.changeRequestAlignmentRulesExceution(chngReq, userDetails);
						changeRequestSourceTargetApprovalSame(chngReq, userDetails, salesPosition, chnageRequest);
					}
					else {
						changeRequestBatchInternalService.updateChangeRequestStatus(chngReq, userDetails);
						// changeRequestDAOService.updateChangeRequestStatus(chngReq.getId(),
						// ChangeRequestStatusType.PENDING_FOR_APPROVAL.getId(),
						// userDetails);
					}

					changeRequestBatchInternalService.changeRequestDocStore(chngReq, EventType.APPROVED, userDetails);

					/************ Notification - Start **************/
					LOGGER.info("************Notification - Start **************");
					crNotifyService.triggerCrNotification(chngReq.getId(),userDetails,CommonConstants.CR_ACTION_APPROVE);
					LOGGER.info("************Notification - End **************");
					/************ Notification - End **************/
				}
			}
			catch (OpservDataAccessException exception) {
				Object[] args = new Object[1];
				args[0] = chngReq.getId();
				throw new ChangeRequestServiceException(ChangeRequestServiceException.ChangeRequestServiceExceptionCode.CR_SER_EX_CD_001, "", args, exception);
			}
			catch (MetricViolationException exception) {
				Object[] args = new Object[1];
				args[0] = chngReq.getId();
				throw new ChangeRequestServiceException(ChangeRequestServiceException.ChangeRequestServiceExceptionCode.CR_SER_EX_CD_003, "", args, exception);
			}
			catch (AlignmentServiceException exception) {
				Object[] args = new Object[1];
				args[0] = chngReq.getId();
				throw new ChangeRequestServiceException(ChangeRequestServiceException.ChangeRequestServiceExceptionCode.CR_SER_EX_CD_004, "", args, exception);
			}
			catch (MetricExecutionException exception) {
				Object[] args = new Object[1];
				args[0] = chngReq.getId();
				throw new ChangeRequestServiceException(ChangeRequestServiceException.ChangeRequestServiceExceptionCode.CR_SER_EX_CD_015, "", args, exception);
			}
			catch (ViewServiceException exception) {
				Object[] args = new Object[1];
				args[0] = chngReq.getId();
				throw new ChangeRequestServiceException(ChangeRequestServiceException.ChangeRequestServiceExceptionCode.CR_SER_EX_CD_021, "", args, exception);
			}
		}
		else {
			Object[] args = new Object[1];
			args[0] = "Either chngReq or chngReqId or  userDetails or userID or TenantID is null";
			throw new ChangeRequestServiceException(args);

		}

	}

	private void changeRequestSourceTargetApprovalSame(ChangeRequest chngReq, UserDetails userDetails, SalesPosition salesPosition, ChangeRequest chnageRequest) {

		List<ChangeRequestApproverDetails> changeRequestApproverDetailsList = changeRequestDAOService.fetchTChngReqDetails(chngReq, userDetails);
		if (null != changeRequestApproverDetailsList && changeRequestApproverDetailsList.size() > 0) {
			Long sourceApprSalesPosId = null;
			Long targetApprSalesPosId = null;
			for (ChangeRequestApproverDetails approver : changeRequestApproverDetailsList) {
				if (approver.getTargetApprFlag().equals(CommonConstants.ACTIVE_N)) {
					sourceApprSalesPosId = approver.getApprSalesPosId();
				}
				else if (approver.getTargetApprFlag().equals(CommonConstants.ACTIVE_Y)) {
					targetApprSalesPosId = approver.getApprSalesPosId();
				}
			}
			if (null != sourceApprSalesPosId && null != targetApprSalesPosId && sourceApprSalesPosId.equals(targetApprSalesPosId)) {
				ChangeRequestMessage changeRequestMessage = new ChangeRequestMessage();
				changeRequestMessage.setChngReqID(chngReq.getId());
				changeRequestMessage.setUserDetails(userDetails);
				changeRequestMessage.setSalesPosID(salesPosition.getId());
				changeRequestMessage.setComments(CommonConstants.EMPTY_STR);
				changeRequestMessage.setSalesHierID(salesPosition.getHierarchy().getId());
				LOGGER.info("Pushing message to ONLINE_CHNAGE_REQUEST_APPROVE_QUEUE");
				genericPublisher.sendObjectToQueue(CommonConstants.ONLINE_CHNAGE_REQUEST_APPROVE_QUEUE, changeRequestMessage);
			}
		}
	}

	

	public void rejectChangeRequest(ChangeRequest chngReq, UserDetails userDetails, SalesPosition salesPosition)
		throws ChangeRequestServiceException, AffiliationServiceException, SalesPositionServiceException, CustomerServiceException, CallPlanServiceException {

		if (null != chngReq && null != chngReq.getId() && null != userDetails && null != userDetails.getTenantId() && null != userDetails.getUserId()) {
			try {
				LOGGER.info("rejectChangeRequest offline started :: crId:" + chngReq.getId());
				chngReq = changeRequestBatchInternalService.rejectChangeRequestTransaction(chngReq, userDetails, salesPosition);
				ChangeRequest changeRequest = new ChangeRequest();
				changeRequest.setId(chngReq.getId());
				changeRequest.setStatus(ChangeRequestStatusType.REJECTED);
				Long count = changeRequestDAOService.fetchTChngReqApprsTypeByStatus(changeRequest, userDetails);
				if (count > 0) {
					changeRequestBatchInternalService.autoRejectChangeRequest(
						changeRequest, userDetails, ChangeRequestStatusType.REJECTED.getId(), ChangeRequestStatusType.REJECTED.getId());
				}
				else {
					changeRequestDAOService.updateChangeRequestStatus(chngReq.getId(), ChangeRequestStatusType.PENDING_FOR_APPROVAL.getId(), userDetails);
				}
				changeRequestBatchInternalService.changeRequestDocStore(chngReq, EventType.REJECTED, userDetails);

				/************ Notification - Start **************/
				LOGGER.info("************Notification - Start **************");
				crNotifyService.triggerCrNotification(chngReq.getId(), userDetails,CommonConstants.CR_ACTION_REJECT);
				LOGGER.info("************Notification - End **************");
				/************ Notification - End **************/

			}
			catch (OpservDataAccessException exception) {
				Object[] args = new Object[1];
				args[0] = chngReq.getId();
				throw new ChangeRequestServiceException(ChangeRequestServiceException.ChangeRequestServiceExceptionCode.CR_SER_EX_CD_001, "", args, exception);
			}
			catch (MetricViolationException exception) {
				Object[] args = new Object[1];
				args[0] = chngReq.getId();
				throw new ChangeRequestServiceException(ChangeRequestServiceException.ChangeRequestServiceExceptionCode.CR_SER_EX_CD_003, "", args, exception);
			}
			catch (AlignmentServiceException exception) {
				Object[] args = new Object[1];
				args[0] = chngReq.getId();
				throw new ChangeRequestServiceException(ChangeRequestServiceException.ChangeRequestServiceExceptionCode.CR_SER_EX_CD_004, "", args, exception);
			}
			catch (MetricExecutionException exception) {
				Object[] args = new Object[1];
				args[0] = chngReq.getId();
				throw new ChangeRequestServiceException(ChangeRequestServiceException.ChangeRequestServiceExceptionCode.CR_SER_EX_CD_015, "", args, exception);
			}
			catch (ViewServiceException exception) {
				Object[] args = new Object[1];
				args[0] = chngReq.getId();
				throw new ChangeRequestServiceException(ChangeRequestServiceException.ChangeRequestServiceExceptionCode.CR_SER_EX_CD_021, "", args, exception);
			}
		}
		else {
			Object[] args = new Object[1];
			args[0] = "Either chngReq or chngReqId or  userDetails or userID or TenantID is null";
			throw new ChangeRequestServiceException(args);

		}
	}
	
	@Override
	public void approveChangeRequestTask(ChangeRequest chngReq, UserDetails userDetails, SalesPosition salesPosition) throws ChangeRequestServiceException,
			AffiliationServiceException, SalesPositionServiceException, CustomerServiceException, CallPlanServiceException {
		ChangeRequest changeRequest = changeRequestDAOService.getChangeRequestByChangeRequestId(chngReq);
		LOGGER.info("In ChangeRequestProcessInternalServiceImpl :: crId:"+chngReq.getId()+" getTasksURL:" + getTasksURL);
		String taskId = RestClient.getTaskListFromWorkFlow(changeRequest,salesPosition, userDetails,getTasksURL);
		LOGGER.info("In ChangeRequestProcessInternalServiceImpl :: taskId -->" + taskId);
		LOGGER.info("In ChangeRequestProcessInternalServiceImpl :: processTaskURL -->" + processTaskURL);
		RestClient.completeTask(chngReq, salesPosition, userDetails, "approve", taskId,processTaskURL+taskId);
	}

	@Override
	public void rejectChangeRequestTask(ChangeRequest chngReq, UserDetails userDetails, SalesPosition salesPosition) throws ChangeRequestServiceException,
			AffiliationServiceException, SalesPositionServiceException, CustomerServiceException, CallPlanServiceException {
		ChangeRequest changeRequest = changeRequestDAOService.getChangeRequestByChangeRequestId(chngReq);
		LOGGER.info("In ChangeRequestProcessInternalServiceImpl :: crId:"+chngReq.getId()+" getTasksURL:" + getTasksURL);
		String taskId = RestClient.getTaskListFromWorkFlow(changeRequest,salesPosition, userDetails,getTasksURL);
		LOGGER.info("In ChangeRequestProcessInternalServiceImpl :: taskId -->" + taskId);
		LOGGER.info("In ChangeRequestProcessInternalServiceImpl :: processTaskURL -->" + processTaskURL);
		RestClient.completeTask(chngReq, salesPosition, userDetails, "reject", taskId,processTaskURL+taskId);
	}

	@Override
	public void reminderChangeRequestTask(ChangeRequest chngReq, UserDetails userDetails, SalesPosition salesPosition) throws ChangeRequestServiceException,
			AffiliationServiceException, SalesPositionServiceException, CustomerServiceException, CallPlanServiceException {
		
	}
	
	/** (non-Javadoc)
	 * @see com.cognizant.opserv.sp.service.internal.ChangeRequestIntService#updateChangeRequestStatus(long, java.lang.Integer, com.cognizant.opserv.sp.model.auth.UserDetails)
	 */
	@Override
	public void updateChangeRequestStatus(long changeRequestId, Integer statusId, UserDetails userDetails)
			throws ChangeRequestServiceException {
		
		try {
			if(null!=statusId && null!=userDetails){
				changeRequestDAOService.updateChangeRequestStatus(changeRequestId, statusId, userDetails);	
			}
		} catch (OpservDataAccessException exception) {
			Object[] args = new Object[]{"CR Status updation failed!!"};
			throw new ChangeRequestServiceException(ChangeRequestServiceException.ChangeRequestServiceExceptionCode.CR_SER_EX_CD_001, "updation_exception", args, exception);
		}
		
	}

	@Override
	@Transactional
	public void updateCRLineStatusForCustAlgmt(long changeRequestId, long custAlgmtId, Integer statusId, UserDetails userDetails) throws ChangeRequestServiceException {
		try {
			if(null !=statusId && null!=userDetails){
				changeRequestDAOService.updateCRLineStatusForCustAlgmt(changeRequestId, custAlgmtId, statusId, userDetails);	
			}
		} catch (OpservDataAccessException exception) {
			Object[] args = new Object[]{"CR Status updation failed!!"};
			throw new ChangeRequestServiceException(ChangeRequestServiceException.ChangeRequestServiceExceptionCode.CR_SER_EX_CD_001, "updation_exception", args, exception);
		}
	}
	
	@Override
	@Transactional
	public void updateCRLineStatusForZip(ChangeRequest chngReq, Integer statusId, PostalCode postalCode, UserDetails userDetails) throws ChangeRequestServiceException {
		try {
			if(null !=statusId && null!=userDetails){
				changeRequestDAOService.updateZipLineItemStatus(chngReq, statusId, postalCode, userDetails);	
			}
		} catch (OpservDataAccessException exception) {
			Object[] args = new Object[]{"CR Status updation failed!!"};
			throw new ChangeRequestServiceException(ChangeRequestServiceException.ChangeRequestServiceExceptionCode.CR_SER_EX_CD_001, "updation_exception", args, exception);
		}
	}

}
