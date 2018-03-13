
package com.cognizant.opserv.sp.cr.process.internal.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.opserv.sp.common.BussObj;
import com.cognizant.opserv.sp.common.ChangeRequestStatusType;
import com.cognizant.opserv.sp.common.ChangeRequestTriggerType;
import com.cognizant.opserv.sp.common.CommonConstants;
import com.cognizant.opserv.sp.common.EventType;
import com.cognizant.opserv.sp.common.MetricTriggerType;
import com.cognizant.opserv.sp.cr.process.internal.service.ChangeRequestUpdateService;
import com.cognizant.opserv.sp.cr.process.internal.service.CustomerEditOfflineService;
import com.cognizant.opserv.sp.cr.process.internal.service.CustomerPullOfflineService;
import com.cognizant.opserv.sp.cr.process.internal.service.MetricOfflineService;
import com.cognizant.opserv.sp.exception.AffiliationServiceException;
import com.cognizant.opserv.sp.exception.AlignmentServiceException;
import com.cognizant.opserv.sp.exception.CallPlanServiceException;
import com.cognizant.opserv.sp.exception.ChangeRequestServiceException;
import com.cognizant.opserv.sp.exception.CustomerServiceException;
import com.cognizant.opserv.sp.exception.MetricExecutionException;
import com.cognizant.opserv.sp.exception.MetricViolationException;
import com.cognizant.opserv.sp.exception.SalesPositionServiceException;
import com.cognizant.opserv.sp.exception.ViewServiceException;
import com.cognizant.opserv.sp.model.GeographyAlignment;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.model.cr.ChangeRequest;
import com.cognizant.opserv.sp.model.cr.ChangeRequestLineItem;
import com.cognizant.opserv.sp.model.cr.CustomerAlignmentChangeRequestDetails;
import com.cognizant.opserv.sp.model.metric.MetricResult;
import com.cognizant.opserv.sp.persistence.dao.service.ChangeRequestDAOService;
import com.cognizant.opserv.sp.persistence.dao.service.ChangeRequestOfflineDAOService;
import com.cognizant.opserv.sp.service.common.SPAssignmentsViewService;
import com.cognizant.opserv.sp.service.util.NotificationUtil;
import com.cognizant.peg.core.exception.OpservDataAccessException;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;

/**
 * @author 472731
 * @class ChangeRequestUpdateServiceImpl
 */
@Service
public class ChangeRequestUpdateServiceImpl implements ChangeRequestUpdateService {

	/**
	 * getTasksURL
	 */
	@Value("${activiti.get.tasks}")
	private String getTasksURL;

	/**
	 * processTaskURL
	 */
	@Value("${activiti.process.task}")
	private String processTaskURL;

	/**
	 * LOGGER
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(ChangeRequestUpdateServiceImpl.class);

	/**
	 * changeRequestDAOService
	 */
	@Autowired
	private ChangeRequestDAOService changeRequestDAOService;

	/**
	 * metricService
	 */
	@Autowired
	private MetricOfflineService mtrOffServ;

	/**
	 * tempChngreqDAOService
	 */
	@Autowired
	private ChangeRequestOfflineDAOService tempChngreqDAOService;

	/**
	 * customerAlignmentProcessIntServiceImpl
	 */
	/*
	 * @Autowired private CustomerAlignmentProcessIntServiceImpl
	 * customerAlignmentProcessIntServiceImpl;
	 */

	/**
	 * spAssignmentsViewService
	 */
	@Autowired
	private SPAssignmentsViewService spAssignmentsViewService;

	/**
	 * notificationUtil
	 */
	@Autowired
	private NotificationUtil notificationUtil;
	
	/**
	 * customerEditOfflineService
	 */
	@Autowired
	private CustomerEditOfflineService customerEditOfflineService;

	@Autowired
	private CustomerPullOfflineService customerPullOfflineService;
	/**
	 * (non-Javadoc)
	 * 
	 * @throws CallPlanServiceException
	 * @throws CustomerServiceException
	 * @throws SalesPositionServiceException
	 * @throws AffiliationServiceException
	 * @throws MetricExecutionException 
	 * @see com.cognizant.opserv.sp.service.internal.ChangeRequestIntService#submitChangeRequestTransaction(com.cognizant.opserv.sp.model.cr.ChangeRequest,
	 *      com.cognizant.opserv.sp.model.auth.UserDetails)
	 */
	@Transactional
	public ChangeRequest approveChangeRequestTransaction(ChangeRequest chngReq, UserDetails userDetails, SalesPosition salesPosition)
		throws MetricViolationException, AlignmentServiceException, ChangeRequestServiceException, AffiliationServiceException, SalesPositionServiceException,
		CustomerServiceException, CallPlanServiceException, MetricExecutionException {

		List<MetricResult> metricResultSource = null;
		List<MetricResult> metricResultTarget = null;
		String comments = chngReq.getComments();
		chngReq = changeRequestDAOService.getChangeRequestByChangeRequestId(chngReq);
		chngReq.setComments(comments);
		if (null != chngReq && null != chngReq.getRaisedSalesPosition() && null != chngReq.getRequestedSalesPosition()) {
			LOGGER.info("Before Calculating metrics... ChangeRequestProcessInternalServiceImpl --> approveChangeRequestTransaction");
			calcuateProcessMetrics(chngReq, userDetails);
			LOGGER.info("After Calculating metrics... ChangeRequestProcessInternalServiceImpl --> approveChangeRequestTransaction");
			Boolean metricResult = changeRequestMetricsResults(chngReq, metricResultSource, metricResultTarget, userDetails);
			LOGGER.info("metricResult... ChangeRequestProcessInternalServiceImpl --> approveChangeRequestTransaction" + metricResult);
			if (metricResult) {
				if (chngReq.getStatus().getId().equals(ChangeRequestStatusType.APPROVAL_IN_PROGRESS.getId()) || chngReq.getStatus().getId().equals(ChangeRequestStatusType.PENDING_FOR_APPROVAL.getId())) {
					changeRequestDAOService.approveChangeRequest(chngReq, userDetails, salesPosition);

				}
				else {
					changeRequestDAOService.withdrawChangeRequest(chngReq, userDetails);
				}
			}
			else {
				LOGGER.error("Metrics violation occured while approveCR... crId:" + chngReq.getId());
				LOGGER.info("Rejecting CR since metricResult is false for chngReq:" + chngReq.getId());
				changeRequestDAOService.autoRejectForBaseAndMirrorChangeRequests(
					chngReq, userDetails, ChangeRequestStatusType.REJECTED.getId(), ChangeRequestStatusType.AUTO_REJECTED.getId());
				Object[] args = new Object[1];
				args[0] = chngReq.getId();
				throw new ChangeRequestServiceException(ChangeRequestServiceException.ChangeRequestServiceExceptionCode.CR_SER_EX_CD_003.getCode(), "");
			}

		}
		else {
			Object[] args = new Object[1];
			args[0] = chngReq.getId();
			throw new ChangeRequestServiceException(ChangeRequestServiceException.ChangeRequestServiceExceptionCode.CR_SER_EX_CD_001.getCode(), "");
		}
		return chngReq;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @throws CallPlanServiceException
	 * @throws CustomerServiceException
	 * @throws SalesPositionServiceException
	 * @throws AffiliationServiceException
	 * @throws MetricExecutionException 
	 * @see com.cognizant.opserv.sp.service.internal.ChangeRequestIntService#submitChangeRequestTransaction(com.cognizant.opserv.sp.model.cr.ChangeRequest,
	 *      com.cognizant.opserv.sp.model.auth.UserDetails)
	 */
	@Transactional
	public ChangeRequest rejectChangeRequestTransaction(ChangeRequest chngReq, UserDetails userDetails, SalesPosition salesPosition)
		throws MetricViolationException, AlignmentServiceException, ChangeRequestServiceException, AffiliationServiceException, SalesPositionServiceException,
		CustomerServiceException, CallPlanServiceException, MetricExecutionException {

		List<MetricResult> metricResultSource = null;
		List<MetricResult> metricResultTarget = null;
		String comments = chngReq.getComments();
		chngReq = changeRequestDAOService.getChangeRequestByChangeRequestId(chngReq);
		chngReq.setComments(comments);
		if (null != chngReq && null != chngReq.getRaisedSalesPosition() && null != chngReq.getRequestedSalesPosition()) {
			LOGGER.info("Before Calculating metrics... ChangeRequestProcessInternalServiceImpl --> rejectChangeRequestTransaction");
			calcuateProcessMetrics(chngReq, userDetails);
			LOGGER.info("After Calculating metrics... ChangeRequestProcessInternalServiceImpl --> rejectChangeRequestTransaction");
			Boolean metricResult = changeRequestMetricsResults(chngReq, metricResultSource, metricResultTarget, userDetails);
			LOGGER.info("metricResult... ChangeRequestProcessInternalServiceImpl --> rejectChangeRequestTransaction" + metricResult);
			if (metricResult) {
				if (chngReq.getStatus().getId().equals(ChangeRequestStatusType.REJECTION_IN_PROGRESS.getId())) {
					changeRequestDAOService.rejectChangeRequest(chngReq, userDetails, salesPosition);

				}
				else {
					changeRequestDAOService.autoRejectForBaseAndMirrorChangeRequests(
						chngReq, userDetails, ChangeRequestStatusType.REJECTED.getId(), ChangeRequestStatusType.REJECTED.getId());
				}
			}
			else {
				LOGGER.error("Metrics violation occured while rejectCR... crId:" + chngReq.getId());
				changeRequestDAOService.autoRejectForBaseAndMirrorChangeRequests(
					chngReq, userDetails, ChangeRequestStatusType.REJECTED.getId(), ChangeRequestStatusType.REJECTED.getId());
				Object[] args = new Object[1];
				args[0] = chngReq.getId();
				throw new ChangeRequestServiceException(ChangeRequestServiceException.ChangeRequestServiceExceptionCode.CR_SER_EX_CD_003.getCode(), "");
			}

		}
		else {
			Object[] args = new Object[1];
			args[0] = chngReq.getId();
			throw new ChangeRequestServiceException(ChangeRequestServiceException.ChangeRequestServiceExceptionCode.CR_SER_EX_CD_001.getCode(), "");
		}
		return chngReq;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @throws MetricExecutionException 
	 * @throws ViewServiceException 
	 * @see com.cognizant.opserv.sp.service.internal.ChangeRequestIntService#changeRequestDocStore(com.cognizant.opserv.sp.model.cr.ChangeRequest,
	 *      com.cognizant.opserv.sp.model.auth.UserDetails)
	 */
	@Transactional
	public void changeRequestDocStore(ChangeRequest chngReq, EventType eventType, UserDetails userDetails)
		throws MetricExecutionException, ViewServiceException {

		spAssignmentsViewService.updateSPView(null, chngReq.getId(), BussObj.CHANGE_REQUEST_VIEW.getBussObjName(), eventType, userDetails);
		if (chngReq.getCrDefinition().getTrigger().getId().equals(ChangeRequestTriggerType.PUSH_CUSTOMER.getId()) ||
			chngReq.getCrDefinition().getTrigger().getId().equals(ChangeRequestTriggerType.PULL_CUSTOMER.getId()) ||
			chngReq.getCrDefinition().getTrigger().getId().equals(ChangeRequestTriggerType.EDIT_CUSTOMER.getId())) {
			List<Long> custIds = changeRequestDAOService.findCustomerIdByChangeRequestId(chngReq, userDetails);
			for (Long custId : custIds) {
				spAssignmentsViewService.updateSPView(chngReq.getRequestedSalesPosition().getId(), custId, BussObj.CUSTOMER_ALIGNMENT_VIEW.getBussObjName(), eventType, userDetails);
				if (chngReq.getRequestedSalesPosition().getId() != chngReq.getRaisedSalesPosition().getId()) {
					spAssignmentsViewService.updateSPView(
						chngReq.getRaisedSalesPosition().getId(), custId, BussObj.CUSTOMER_ALIGNMENT_VIEW.getBussObjName(), eventType, userDetails);
				}
			}
			List<Long> salesPositionIds = changeRequestDAOService.findCustomerAlignmentSalesPostionIdByChangeRequestId(chngReq, userDetails);
			for (Long custId : custIds) {
				for (Long salesPostionId : salesPositionIds) {
					spAssignmentsViewService.updateSPView(salesPostionId, custId, BussObj.CUSTOMER_ALIGNMENT_VIEW.getBussObjName(), eventType, userDetails);
				}
			}

		}
		else if (chngReq.getCrDefinition().getTrigger().getId().equals(ChangeRequestTriggerType.PUSH_ZIP.getId())) {
			List<Long> custIds = changeRequestDAOService.findCustomerIdByChangeRequestId(chngReq, userDetails);
			for (Long custId : custIds) {
				spAssignmentsViewService.updateSPView(chngReq.getRequestedSalesPosition().getId(), custId, BussObj.CUSTOMER_ALIGNMENT_VIEW.getBussObjName(), eventType, userDetails);
				if (chngReq.getRequestedSalesPosition().getId() != chngReq.getRaisedSalesPosition().getId()) {
					spAssignmentsViewService.updateSPView(
						chngReq.getRaisedSalesPosition().getId(), custId, BussObj.CUSTOMER_ALIGNMENT_VIEW.getBussObjName(), eventType, userDetails);
				}
			}
			List<Long> salesPositionIds = changeRequestDAOService.findCustomerAlignmentSalesPostionIdByChangeRequestId(chngReq, userDetails);
			for (Long custId : custIds) {
				for (Long salesPostionId : salesPositionIds) {
					spAssignmentsViewService.updateSPView(salesPostionId, custId, BussObj.CUSTOMER_ALIGNMENT_VIEW.getBussObjName(), eventType, userDetails);
				}
			}

			List<GeographyAlignment> geoGraphyAlignmentSourceList =
				changeRequestDAOService.findZipAlignmentByChangeRequestId(chngReq, chngReq.getRequestedSalesPosition(), CommonConstants.SOURCE, userDetails);
			if (null != geoGraphyAlignmentSourceList && geoGraphyAlignmentSourceList.size() > 0) {
				for (GeographyAlignment sourceGeoAlign : geoGraphyAlignmentSourceList) {
					spAssignmentsViewService.updateZipSalesPosView(
						chngReq.getRequestedSalesPosition().getId(), sourceGeoAlign.getPostalCode().getCode(), BussObj.ZIP_SALESPOS_VIEW.getBussObjName(), eventType, userDetails);
				}
			}

			List<GeographyAlignment> geoGraphyAlignmentTargetList =
				changeRequestDAOService.findZipAlignmentByChangeRequestId(chngReq, chngReq.getRaisedSalesPosition(), CommonConstants.TARGET, userDetails);
			if (null != geoGraphyAlignmentTargetList && geoGraphyAlignmentTargetList.size() > 0) {
				for (GeographyAlignment targetGeoAlign : geoGraphyAlignmentTargetList) {
					spAssignmentsViewService.updateZipSalesPosView(
						chngReq.getRaisedSalesPosition().getId(), targetGeoAlign.getPostalCode().getCode(), BussObj.ZIP_SALESPOS_VIEW.getBussObjName(), eventType, userDetails);
				}
			}

			List<Object> geoGraphyAlignmentMirrirSourceList = changeRequestDAOService.findMirrorZipAlignmentByChangeRequestId(chngReq, CommonConstants.SOURCE, userDetails);
			if (geoGraphyAlignmentMirrirSourceList != null) {
				for (Object object1 : geoGraphyAlignmentMirrirSourceList) {
					Object[] obj = (Object[]) object1;
					if (obj[0] != null && obj[1] != null) {
						String postalCode = (String) obj[0];
						Long salesPosId = (Long) obj[1];
						spAssignmentsViewService.updateZipSalesPosView(salesPosId, postalCode, BussObj.ZIP_SALESPOS_VIEW.getBussObjName(), eventType, userDetails);
					}
				}
			}

			List<Object> geoGraphyAlignmentMirrirTargetList = changeRequestDAOService.findMirrorZipAlignmentByChangeRequestId(chngReq, CommonConstants.TARGET, userDetails);
			if (geoGraphyAlignmentMirrirTargetList != null) {
				for (Object object1 : geoGraphyAlignmentMirrirTargetList) {
					Object[] obj = (Object[]) object1;
					if (obj[0] != null && obj[1] != null) {
						String postalCode = (String) obj[0];
						Long salesPosId = (Long) obj[1];
						spAssignmentsViewService.updateZipSalesPosView(salesPosId, postalCode, BussObj.ZIP_SALESPOS_VIEW.getBussObjName(), eventType, userDetails);
					}
				}
			}
		}
		calcuateProcessMetrics(chngReq, userDetails);
	}

	/**
	 * (non-Javadoc)
	 * @throws ViewServiceException 
	 * 
	 * @see com.cognizant.opserv.sp.cr.process.internal.service.ChangeRequestBatchInternalService#changeRequestAlignmentRulesExceution(com.cognizant.opserv.sp.model.cr.ChangeRequest,
	 *      com.cognizant.opserv.sp.model.auth.UserDetails)
	 */
	@Transactional
	public void changeRequestAlignmentRulesExceution(ChangeRequest chngReq, UserDetails userDetails)
		throws MetricViolationException, AlignmentServiceException, ViewServiceException {

		if (chngReq.getCrDefinition().getTrigger().getId().equals(ChangeRequestTriggerType.PUSH_CUSTOMER.getId()) ||
			chngReq.getCrDefinition().getTrigger().getId().equals(ChangeRequestTriggerType.PULL_CUSTOMER.getId()) ||
			chngReq.getCrDefinition().getTrigger().getId().equals(ChangeRequestTriggerType.EDIT_CUSTOMER.getId())) {
			List<Long> custIds = changeRequestDAOService.findCustomerIdByChangeRequestId(chngReq, userDetails);
			for (Long custId : custIds) {
				spAssignmentsViewService.updateSCR(chngReq.getRequestedSalesPosition().getId(), custId, BussObj.CUSTOMER_ALIGNMENT_VIEW.getBussObjName(), userDetails);
				if (chngReq.getRequestedSalesPosition().getId() != chngReq.getRaisedSalesPosition().getId()) {
					spAssignmentsViewService.updateSCR(chngReq.getRaisedSalesPosition().getId(), custId, BussObj.CUSTOMER_ALIGNMENT_VIEW.getBussObjName(), userDetails);
				}
			}
			List<Long> salesPositionIds = changeRequestDAOService.findCustomerAlignmentSalesPostionIdByChangeRequestId(chngReq, userDetails);
			for (Long custId : custIds) {
				for (Long salesPostionId : salesPositionIds) {
					spAssignmentsViewService.updateSCR(salesPostionId, custId, BussObj.CUSTOMER_ALIGNMENT_VIEW.getBussObjName(), userDetails);
				}
			}

		}
		else if (chngReq.getCrDefinition().getTrigger().getId().equals(ChangeRequestTriggerType.PUSH_ZIP.getId())) {
			List<Long> custIds = changeRequestDAOService.findCustomerIdByChangeRequestId(chngReq, userDetails);
			for (Long custId : custIds) {
				spAssignmentsViewService.updateSCR(chngReq.getRequestedSalesPosition().getId(), custId, BussObj.CUSTOMER_ALIGNMENT_VIEW.getBussObjName(), userDetails);
				if (chngReq.getRequestedSalesPosition().getId() != chngReq.getRaisedSalesPosition().getId()) {
					spAssignmentsViewService.updateSCR(chngReq.getRaisedSalesPosition().getId(), custId, BussObj.CUSTOMER_ALIGNMENT_VIEW.getBussObjName(), userDetails);
				}
			}
			List<Long> salesPositionIds = changeRequestDAOService.findCustomerAlignmentSalesPostionIdByChangeRequestId(chngReq, userDetails);
			for (Long custId : custIds) {
				for (Long salesPostionId : salesPositionIds) {
					spAssignmentsViewService.updateSCR(salesPostionId, custId, BussObj.CUSTOMER_ALIGNMENT_VIEW.getBussObjName(), userDetails);
				}
			}

		}
	}

	/**
	 * @param chngReq
	 * @param metricResultSource
	 * @param metricResultTarget
	 * @return Boolean
	 * @throws AlignmentServiceException
	 * @throws MetricViolationException
	 * @throws ChangeRequestServiceException
	 */
	private Boolean changeRequestMetricsResults(ChangeRequest chngReq, List<MetricResult> metricResultSource, List<MetricResult> metricResultTarget, UserDetails userDetails)
		throws MetricViolationException, AlignmentServiceException {

		Boolean result = false;
		if (chngReq.getCrDefinition().getTrigger().getId().equals(ChangeRequestTriggerType.PULL_CUSTOMER.getId())) {
			result = mtrOffServ.checkCustomerPullMetricViolation(chngReq.getRequestedSalesPosition(), chngReq.getRaisedSalesPosition(), userDetails);
		}else if(chngReq.getCrDefinition().getTrigger().getId().equals(ChangeRequestTriggerType.PUSH_CUSTOMER.getId() )){
			result = mtrOffServ.checkCustomerPushMetricViolation(chngReq.getRequestedSalesPosition(), chngReq.getRaisedSalesPosition(), userDetails);
		}else if (chngReq.getCrDefinition().getTrigger().getId().equals(ChangeRequestTriggerType.PUSH_ZIP.getId())) {
			result = mtrOffServ.checkZipMovementMetricViolation(chngReq.getRequestedSalesPosition(), chngReq.getRaisedSalesPosition(), userDetails);
		}else if (chngReq.getCrDefinition().getTrigger().getId().equals(ChangeRequestTriggerType.EDIT_CUSTOMER.getId())) {
			result = mtrOffServ.checkCustomerEditMetricViolation(chngReq.getRaisedSalesPosition(), userDetails);
		}
		return result;
	}

	/**
	 * @param chngReq
	 * @param metricResultSource
	 * @param metricResultTarget
	 * @return Boolean
	 * @throws MetricExecutionException 
	 */
	private void calcuateProcessMetrics(ChangeRequest chngReq, UserDetails userDetails)
			throws MetricExecutionException {
		LOGGER.info("Inside calcuateProcessMetrics");
		if (chngReq.getCrDefinition().getTrigger().getId().equals(ChangeRequestTriggerType.PUSH_CUSTOMER.getId()) ||
				chngReq.getCrDefinition().getTrigger().getId().equals(ChangeRequestTriggerType.PULL_CUSTOMER.getId())) {
			mtrOffServ.processCalculativeMetrics(chngReq.getRequestedSalesPosition(), MetricTriggerType.UNASSIGN_CUSTOMER, userDetails);
			mtrOffServ.processCalculativeMetrics(chngReq.getRaisedSalesPosition(), MetricTriggerType.ASSIGN_CUSTOMER, userDetails);
		}
		else if (chngReq.getCrDefinition().getTrigger().getId().equals(ChangeRequestTriggerType.PUSH_ZIP.getId())) {
			mtrOffServ.processCalculativeMetrics(chngReq.getRequestedSalesPosition(), MetricTriggerType.UNASSIGN_CUSTOMER, userDetails);
			mtrOffServ.processCalculativeMetrics(chngReq.getRaisedSalesPosition(), MetricTriggerType.ASSIGN_CUSTOMER, userDetails);
		}
		else if (chngReq.getCrDefinition().getTrigger().getId().equals(ChangeRequestTriggerType.EDIT_CUSTOMER.getId())) {
			mtrOffServ.processCalculativeMetrics(chngReq.getRequestedSalesPosition(), MetricTriggerType.EDIT_CUSTOMER, userDetails);
		}

	}

	/**
	 * (non-Javadoc)
	 * 
	 * @throws CallPlanServiceException
	 * @throws OpservDataAccessException
	 * @see com.cognizant.opserv.sp.cr.process.internal.service.ChangeRequestProcessInternalService#autoApproveChangeRequest(com.cognizant.opserv.sp.core.entity.TChngReq,
	 *      com.cognizant.opserv.sp.model.auth.UserDetails, java.util.List,
	 *      java.util.List, java.lang.Integer, java.lang.Integer)
	 */
	@Transactional
	public void autoApproveChangeRequest(ChangeRequest chngReq, UserDetails userDetails, Integer aprStsId, Integer autoAprStsId)
		throws ChangeRequestServiceException, OpservDataAccessException, CallPlanServiceException {

		changeRequestDAOService.autoApproveForBaseAndMirrorChangeRequests(chngReq, userDetails, aprStsId, autoAprStsId);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.cognizant.opserv.sp.cr.process.internal.service.ChangeRequestProcessInternalService#autoRejectChangeRequest(com.cognizant.opserv.sp.core.entity.TChngReq,
	 *      com.cognizant.opserv.sp.model.auth.UserDetails, java.util.List,
	 *      java.util.List, java.lang.Integer, java.lang.Integer)
	 */
	@Transactional
	public void autoRejectChangeRequest(ChangeRequest chngReq, UserDetails userDetails, Integer rejectStsId, Integer rejectAprStsId)
		throws ChangeRequestServiceException {

		changeRequestDAOService.autoRejectForBaseAndMirrorChangeRequests(chngReq, userDetails, rejectStsId, rejectAprStsId);
	}

	@Override
	@Transactional
	public void updateChangeRequestStatus(ChangeRequest chngReq, UserDetails userDetails) {

		changeRequestDAOService.updateChangeRequestStatus(chngReq.getId(), ChangeRequestStatusType.PENDING_FOR_APPROVAL.getId(), userDetails);

	}
	
	
	/**
	 * @param chngReq
	 * @param userDetails
	 * @throws AlignmentServiceException
	 * @throws CallPlanServiceException
	 */
	@Override
	@Transactional
	public void updateCustSalesTeamAndCustPrdSalesTeam(ChangeRequest chngReq, UserDetails userDetails)
		throws AlignmentServiceException, CallPlanServiceException {

		if (chngReq.getCrDefinition().getTrigger().getId() == ChangeRequestTriggerType.EDIT_CUSTOMER.getId()) {
			ChangeRequest changeRequest = changeRequestDAOService.getChangeRequestLineItemsByChangeRequestId(chngReq);
			List<ChangeRequestLineItem> customerAlignmentChangeRequestLineItems = changeRequest.getLineItems();
			for (ChangeRequestLineItem changeRequestLineItem : customerAlignmentChangeRequestLineItems) {
				CustomerAlignmentChangeRequestDetails customerAlignmentChangeRequestDetails = (CustomerAlignmentChangeRequestDetails) changeRequestLineItem;
		        customerEditOfflineService.updateCustomerDetailsOnAppr(customerAlignmentChangeRequestDetails.getNewCustomerAlignment(),
						customerAlignmentChangeRequestDetails.getNewCustomerProductAlignments(), userDetails);
			}
		}

		if (chngReq.getCrDefinition().getTrigger().getId() == ChangeRequestTriggerType.PULL_CUSTOMER.getId()) {
			ChangeRequest changeRequest = changeRequestDAOService.getChangeRequestLineItemsByChangeRequestId(chngReq);
			List<ChangeRequestLineItem> customerAlignmentChangeRequestLineItems = changeRequest.getLineItems();
			for (ChangeRequestLineItem changeRequestLineItem : customerAlignmentChangeRequestLineItems) {
				CustomerAlignmentChangeRequestDetails customerAlignmentChangeRequestDetails = (CustomerAlignmentChangeRequestDetails) changeRequestLineItem;
				customerPullOfflineService
						.updateExtdAttrOnApprForPull(
								customerAlignmentChangeRequestDetails
										.getNewCustomerAlignment(),
								customerAlignmentChangeRequestDetails
										.getNewCustomerProductAlignments(),
								userDetails);
			}
		}
	}
	
	
	/**
	 * @param chngReq
	 * @param userDetails
	 * @throws AlignmentServiceException
	 * @throws CallPlanServiceException
	 */
	@Transactional
	public void updateMirrorCustSalesTeamAndCustSalesTeam(ChangeRequest chngReq, UserDetails userDetails)
		throws AlignmentServiceException, CallPlanServiceException {

		List<ChangeRequest> childChangeRequests = changeRequestDAOService.findChildChangeRequestByParentChangeRequest(chngReq);
		for (ChangeRequest request : childChangeRequests) {
			if (request.getCrDefinition().getTrigger().getId() == ChangeRequestTriggerType.EDIT_CUSTOMER.getId()) {
				ChangeRequest changeRequest = changeRequestDAOService.getChangeRequestLineItemsByChangeRequestId(request);
				List<ChangeRequestLineItem> customerAlignmentChangeRequestLineItems = changeRequest.getLineItems();
				for (ChangeRequestLineItem changeRequestLineItem : customerAlignmentChangeRequestLineItems) {
					CustomerAlignmentChangeRequestDetails customerAlignmentChangeRequestDetails = (CustomerAlignmentChangeRequestDetails) changeRequestLineItem;
			        customerEditOfflineService.updateCustomerDetailsOnAppr(customerAlignmentChangeRequestDetails.getNewCustomerAlignment(),
							customerAlignmentChangeRequestDetails.getNewCustomerProductAlignments(), userDetails);
				}
			}

			if (request.getCrDefinition().getTrigger().getId() == ChangeRequestTriggerType.PULL_CUSTOMER.getId()) {
				ChangeRequest changeRequest = changeRequestDAOService.getChangeRequestLineItemsByChangeRequestId(request);
				List<ChangeRequestLineItem> customerAlignmentChangeRequestLineItems = changeRequest.getLineItems();
				for (ChangeRequestLineItem changeRequestLineItem : customerAlignmentChangeRequestLineItems) {
					CustomerAlignmentChangeRequestDetails customerAlignmentChangeRequestDetails = (CustomerAlignmentChangeRequestDetails) changeRequestLineItem;
					customerPullOfflineService
					.updateExtdAttrOnApprForPull(
							customerAlignmentChangeRequestDetails
									.getNewCustomerAlignment(),
							customerAlignmentChangeRequestDetails
									.getNewCustomerProductAlignments(),
							userDetails);
				}
			}
		}
	}
}
