package com.cognizant.opserv.sp.rest.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cognizant.opserv.sp.common.ChangeRequestTriggerType;
import com.cognizant.opserv.sp.exception.AlignmentServiceException;
import com.cognizant.opserv.sp.exception.ChangeRequestServiceException;
import com.cognizant.opserv.sp.model.Alignment;
import com.cognizant.opserv.sp.model.BusinessUnit;
import com.cognizant.opserv.sp.model.Customer;
import com.cognizant.opserv.sp.model.SalesOrgHierarchy;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.model.SalesTeam;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.model.cr.ChangeRequest;
import com.cognizant.opserv.sp.model.cr.ChangeRequestApproverDetails;
import com.cognizant.opserv.sp.model.cr.ChangeRequestDefinition;
import com.cognizant.opserv.sp.model.cr.CustomerAlignmentChangeRequestDetails;
import com.cognizant.opserv.sp.model.cr.ZipAlignmentChangeRequestDetails;
import com.cognizant.opserv.sp.rest.util.ModelAssembler;
import com.cognizant.opserv.sp.service.changereq.ChangeRequestService;
import com.cognizant.opserv.sp.service.common.ChangeRequestCommonService;
import com.cognizant.opserv.sp.service.internal.ChangeRequestIntService;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.rest.common.StatusCode;
import com.cognizant.peg.web.common.ServiceResponse;
import com.cognizant.peg.web.common.ServiceResult;
import com.cognizant.peg.web.common.ServiceStatus;

/**
 * @author 429184
 * 
 */
@Controller
public class ChangeRequestRestController {
	/*
	 * Instantiate the Logger
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(ChangeRequestRestController.class);

	@Autowired
	private ChangeRequestService changeRequestService;
	
	@Autowired
	private ChangeRequestIntService changeRequestIntService;

	@Autowired
	private ChangeRequestCommonService changeRequestCommonService;
	
	/**
	 * Generate push customer cr.
	 *
	 * @param ssalesPosId the ssales pos id
	 * @param tsalesPosId the tsales pos id
	 * @param request the request
	 * @return the service response
	 * @throws AlignmentServiceException 
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/changeRequest/generatePushCustomerCR/{al}/{bu}/{st}/{srcsp}/{srcsh}/{tarsp}/{tarsh}/{userId}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	ServiceResponse<ChangeRequest> generatePushCustomerCR(@PathVariable("al") Long alId, @PathVariable("bu") Long buId,
			@PathVariable("st") Long stId, @PathVariable("srcsp") Long srcsp,
			@PathVariable("srcsh") Long srcsh,
			@PathVariable("tarsp") Long tarsp,
			@PathVariable("tarsh") Long tarsh, @PathVariable("userId") Integer userId, HttpServletRequest request) throws AlignmentServiceException {
		
		ServiceStatus serviceStatus = null;
		ServiceResponse<ChangeRequest> serviceResponse = new ServiceResponse<ChangeRequest>();
		@SuppressWarnings("rawtypes")
		ServiceResult serviceResult = new ServiceResult();
		
		SalesPosition srcSalesPosition = ModelAssembler.getSalesPosition(srcsp, srcsh);
		
		
		Alignment alignment = ModelAssembler.getAlignmentModel(alId, buId, stId);		
		
		srcSalesPosition.setAlignmment(alignment);
		
		SalesPosition targetSalesPosition = ModelAssembler.getSalesPosition(tarsp, tarsh);
		
		targetSalesPosition.setAlignmment(alignment);
		
		ChangeRequestDefinition changeRequestDefinition = new ChangeRequestDefinition();
		changeRequestDefinition.setId(71L);
		changeRequestDefinition.setTrigger(ChangeRequestTriggerType.PUSH_CUSTOMER);
		
		UserDetails userDetails = ModelAssembler.getDefaultUserDetails();
		userDetails.setUserId(userId);
		try {
			serviceResult.setDetail(changeRequestCommonService.generatePushCustomerChangeRequest(null, srcSalesPosition, targetSalesPosition, userDetails));
			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
		} catch (ChangeRequestServiceException e) {
			serviceResult.setDetail("Exception occured " + e.getMessage());
			serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR, "500 ERROR");
		}catch (AlignmentServiceException  e) {
			serviceResult.setDetail("Exception occured " + e.getMessage());
			serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR, "500 ERROR");
		}
		serviceResponse.setStatus(serviceStatus);
		serviceResponse.setResult(serviceResult);
		LOGGER.info(" Returning REST response with Result.." + serviceResult.getDetail() + " and status " + serviceResponse.getStatus().getCode().getCode() + "-"
				+ serviceResponse.getStatus().getMessage());
		return serviceResponse;
	}
	
	/**
	 * Generate pull customer cr.
	 *
	 * @param ssalesPosId the ssales pos id
	 * @param tsalesPosId the tsales pos id
	 * @param request the request
	 * @return the service response
	 * @throws AlignmentServiceException 
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/changeRequest/generatePullCustomerCR/{al}/{bu}/{st}/{srcsp}/{srcsh}/{tarsp}/{tarsh}/{userId}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	ServiceResponse<ChangeRequest> generatePullCustomerCR(@PathVariable("al") Long alId, @PathVariable("bu") Long buId,
			@PathVariable("st") Long stId, @PathVariable("srcsp") Long srcsp,
			@PathVariable("srcsh") Long srcsh,
			@PathVariable("tarsp") Long tarsp,
			@PathVariable("tarsh") Long tarsh, @PathVariable("userId") Integer userId, HttpServletRequest request) throws AlignmentServiceException {
		ServiceStatus serviceStatus = null;
		ServiceResponse<ChangeRequest> serviceResponse = new ServiceResponse<ChangeRequest>();
		@SuppressWarnings("rawtypes")
		ServiceResult serviceResult = new ServiceResult();
		
		SalesPosition srcSalesPosition = ModelAssembler.getSalesPosition(srcsp, srcsh);
		
		Alignment alignment = ModelAssembler.getAlignmentModel(alId, buId, stId);		
		
		srcSalesPosition.setAlignmment(alignment);
		
		SalesPosition targetSalesPosition = ModelAssembler.getSalesPosition(tarsp, tarsh);
		
		targetSalesPosition.setAlignmment(alignment);
		
		ChangeRequestDefinition changeRequestDefinition = new ChangeRequestDefinition();
		changeRequestDefinition.setId(87L);
		changeRequestDefinition.setTrigger(ChangeRequestTriggerType.PULL_CUSTOMER);
		
		UserDetails userDetails = ModelAssembler.getDefaultUserDetails();
		userDetails.setUserId(userId);
		
		try {
			serviceResult.setDetail(changeRequestCommonService.generatePullCustomerChangeRequestSubmit(null, srcSalesPosition, targetSalesPosition, userDetails));
			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
		} catch (ChangeRequestServiceException e) {
			serviceResult.setDetail("Exception occured " + e.getMessage());
			serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR, "500 ERROR");
		}catch (AlignmentServiceException  e) {
			serviceResult.setDetail("Exception occured " + e.getMessage());
			serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR, "500 ERROR");
		}
		serviceResponse.setStatus(serviceStatus);
		serviceResponse.setResult(serviceResult);
		LOGGER.info(" Returning REST response with Result.." + serviceResult.getDetail() + " and status " + serviceResponse.getStatus().getCode().getCode() + "-"
				+ serviceResponse.getStatus().getMessage());
		return serviceResponse;
	}	
	
	/**
	 * Generate edit customer alignment cr.
	 *
	 * @param salesPosId the sales pos id
	 * @param request the request
	 * @return the service response
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/changeRequest/generateEditCustomerAlignmentCR/{al}/{bu}/{st}/{srcsp}/{srcsh}/{userId}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	ServiceResponse<ChangeRequest> generateEditCustomerAlignmentCR(@PathVariable("al") Long alId, @PathVariable("bu") Long buId,
			@PathVariable("st") Long stId, @PathVariable("srcsp") Long srcsp,
			@PathVariable("srcsh") Long srcsh, @PathVariable("userId") Integer userId, HttpServletRequest request) {
		ServiceStatus serviceStatus = null;
		ServiceResponse<ChangeRequest> serviceResponse = new ServiceResponse<ChangeRequest>();
		@SuppressWarnings("rawtypes")
		ServiceResult serviceResult = new ServiceResult();
		
		SalesPosition srcSalesPosition = ModelAssembler.getSalesPosition(srcsp, srcsh);
		
		Alignment alignment = ModelAssembler.getAlignmentModel(alId, buId, stId);		
		
		srcSalesPosition.setAlignmment(alignment);
		
		ChangeRequestDefinition changeRequestDefinition = new ChangeRequestDefinition();
		changeRequestDefinition.setId(71L);
		changeRequestDefinition.setTrigger(ChangeRequestTriggerType.EDIT_CUSTOMER);

		UserDetails userDetails = ModelAssembler.getDefaultUserDetails();
		userDetails.setUserId(userId);
		try {
			serviceResult.setDetail(changeRequestCommonService.generateEditCustomerAlignmentChangeRequest(null, srcSalesPosition, userDetails));
			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
		} catch (ChangeRequestServiceException e) {
			serviceResult.setDetail("Exception occured " + e.getMessage());
			serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR, "500 ERROR");
		}
		serviceResponse.setStatus(serviceStatus);
		serviceResponse.setResult(serviceResult);
		LOGGER.info(" Returning REST response with Result.." + serviceResult.getDetail() + " and status " + serviceResponse.getStatus().getCode().getCode() + "-"
				+ serviceResponse.getStatus().getMessage());
		return serviceResponse;
	}
	
	/**
	 * Generate zip movement cr.
	 *
	 * @param salesPosId the sales pos id
	 * @param request the request
	 * @return the service response
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/changeRequest/generateZipMovementCR/{al}/{bu}/{st}/{srcsp}/{srcsh}/{tarsp}/{tarsh}/{userId}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	ServiceResponse<ChangeRequest> generateZipMovementCR(@PathVariable("al") Long alId, @PathVariable("bu") Long buId,
			@PathVariable("st") Long stId, @PathVariable("srcsp") Long srcsp,
			@PathVariable("srcsh") Long srcsh,
			@PathVariable("tarsp") Long tarsp,
			@PathVariable("tarsh") Long tarsh, @PathVariable("userId") Integer userId, HttpServletRequest request) {
		
		ServiceStatus serviceStatus = null;
		ServiceResponse<ChangeRequest> serviceResponse = new ServiceResponse<ChangeRequest>();
		@SuppressWarnings("rawtypes")
		ServiceResult serviceResult = new ServiceResult();
		
		SalesPosition srcSalesPosition = ModelAssembler.getSalesPosition(srcsp, srcsh);
		
		Alignment alignment = ModelAssembler.getAlignmentModel(alId, buId, stId);		
		
		srcSalesPosition.setAlignmment(alignment);
		
		SalesPosition targetSalesPosition = ModelAssembler.getSalesPosition(tarsp, tarsh);
		
		targetSalesPosition.setAlignmment(alignment);
		
		ChangeRequestDefinition changeRequestDefinition = new ChangeRequestDefinition();
		changeRequestDefinition.setId(92L);
		changeRequestDefinition.setTrigger(ChangeRequestTriggerType.PUSH_ZIP);
		
		UserDetails userDetails = ModelAssembler.getDefaultUserDetails();
		userDetails.setUserId(userId);
		try {
			serviceResult.setDetail(changeRequestCommonService.generateZipMovementChangeRequest(null, srcSalesPosition, targetSalesPosition, userDetails));
			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
		} catch (ChangeRequestServiceException e) {
			serviceResult.setDetail("Exception occured " + e.getMessage());
			serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR, "500 ERROR");
		}
		serviceResponse.setStatus(serviceStatus);
		serviceResponse.setResult(serviceResult);
		LOGGER.info(" Returning REST response with Result.." + serviceResult.getDetail() + " and status " + serviceResponse.getStatus().getCode().getCode() + "-"
				+ serviceResponse.getStatus().getMessage());
		return serviceResponse;
	}
	
	/**** Change request offline service ------ Start-------****/
	
	

	
	
	/**** Change request offline service ------ End-------****/
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * To Get All ChangeRequests For Approval
	 * @param salesPosId - Sales Position Id
	 * @param salesHierId - Sales Hierarchy Id
	 * @param request - HttpServletRequest
	 * @return List<ChangeRequest>
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/changeRequest/fetchAllChangeRequestsForApproval/{salesPosId}/{salesHierId}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	ServiceResponse<List<ChangeRequest>> getAllChangeRequestsForApproval(@PathVariable("salesPosId") Long salesPosId, @PathVariable("salesHierId") Long salesHierId,
			HttpServletRequest request) {

		ServiceStatus serviceStatus = null;
		ServiceResponse<List<ChangeRequest>> serviceResponse = new ServiceResponse<List<ChangeRequest>>();
		@SuppressWarnings("rawtypes")
		ServiceResult serviceResult = new ServiceResult();
		SalesPosition salesPos = new SalesPosition();
		SalesOrgHierarchy salesHier = new SalesOrgHierarchy();
		UserDetails userDetails = ModelAssembler.getDefaultUserDetails();
		salesPos.setId(salesPosId);
		salesHier.setId(salesHierId);
		salesPos.setHierarchy(salesHier);
		salesPos.setTenantId(userDetails.getTenantId());

		try {
			serviceResult.setList(changeRequestService.getAllChangeRequestsForApproval(salesPos, userDetails));
			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
		} catch (ChangeRequestServiceException e) {
			serviceResult.setDetail("Exception occured " + e.getMessage());
			serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR, "500 ERROR");
		}
		serviceResponse.setStatus(serviceStatus);
		serviceResponse.setResult(serviceResult);
		LOGGER.info(" Returning REST response with Result.." + serviceResult.getDetail() + " and status " + serviceResponse.getStatus().getCode().getCode() + "-"
				+ serviceResponse.getStatus().getMessage());
		return serviceResponse;
	}

	/**
	 * To Get All ChangeRequests
	 * @param salesPosId - Sales Position Id
	 * @param salesHierId - Sales Hierarchy Id
	 * @param request - HttpServletRequest
	 * @return List<ChangeRequest>
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/changeRequest/fetchAllChangeRequests/{salesPosId}/{salesHierId}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	ServiceResponse<List<ChangeRequest>> getAllChangeRequests(@PathVariable("salesPosId") Long salesPosId, @PathVariable("salesHierId") Long salesHierId, HttpServletRequest request) {

		ServiceStatus serviceStatus = null;
		ServiceResponse<List<ChangeRequest>> serviceResponse = new ServiceResponse<List<ChangeRequest>>();
		@SuppressWarnings("rawtypes")
		ServiceResult serviceResult = new ServiceResult();
		SalesPosition salesPos = new SalesPosition();
		SalesOrgHierarchy salesHier = new SalesOrgHierarchy();
		UserDetails userDetails = ModelAssembler.getDefaultUserDetails();
		salesPos.setId(salesPosId);
		salesHier.setId(salesHierId);
		salesPos.setHierarchy(salesHier);
		salesPos.setTenantId(userDetails.getTenantId());

		try {
			serviceResult.setList(changeRequestService.getAllChangeRequests(salesPos, userDetails));
			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
		} catch (ChangeRequestServiceException e) {
			serviceResult.setDetail("Exception occured " + e.getMessage());
			serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR, "500 ERROR");
		}
		serviceResponse.setStatus(serviceStatus);
		serviceResponse.setResult(serviceResult);
		LOGGER.info(" Returning REST response with Result.." + serviceResult.getDetail() + " and status " + serviceResponse.getStatus().getCode().getCode() + "-"
				+ serviceResponse.getStatus().getMessage());
		return serviceResponse;
	}

	/**
	 * To Get All Change Requests Of Customer 
	 * @param custId
	 * @param salesPosId - Sales Position Id
	 * @param salesHierId - Sales Hierarchy Id
	 * @param alignId - Alignment Id
	 * @param businessId - Business Id
	 * @param salesTeamId - Sales Team Id
	 * @param request - HttpServletRequest
	 * @return List<ChangeRequest>
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/changeRequest/fetchAllChangeRequestsOfCustomer/{custId}/{salesPosId}/{salesHierId}/{alignId}/{businessId}/{salesTeamId}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	ServiceResponse<List<ChangeRequest>> getAllChangeRequestsOfCustomer(@PathVariable("custId") Long custId, @PathVariable("salesPosId") Long salesPosId,
			@PathVariable("salesHierId") Long salesHierId, @PathVariable("alignId") Long alignId, @PathVariable("businessId") Long businessId,
			@PathVariable("salesTeamId") Long salesTeamId, HttpServletRequest request) {

		ServiceStatus serviceStatus = null;
		ServiceResponse<List<ChangeRequest>> serviceResponse = new ServiceResponse<List<ChangeRequest>>();
		@SuppressWarnings("rawtypes")
		ServiceResult serviceResult = new ServiceResult();
		Customer cust = new Customer();
		SalesPosition salesPos = new SalesPosition();
		SalesOrgHierarchy salesHier = new SalesOrgHierarchy();
		Alignment align = new Alignment();
		SalesTeam sales = new SalesTeam();
		BusinessUnit bussiUnit = new BusinessUnit();
		UserDetails user = ModelAssembler.getDefaultUserDetails();
		cust.setId(custId);
		salesPos.setId(salesPosId);
		salesHier.setId(salesHierId);
		salesPos.setHierarchy(salesHier);
		align.setId(alignId);
		sales.setId(salesTeamId);
		bussiUnit.setId(businessId);
		sales.setBusinessUnit(bussiUnit);
		align.setSalesTeam(sales);
		salesPos.setAlignmment(align);
		salesPos.setTenantId(user.getTenantId());

		try {
			serviceResult.setList(changeRequestService.getAllChangeRequestsOfCustomer(cust, salesPos, user));
			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
		} catch (ChangeRequestServiceException e) {
			serviceResult.setDetail("Exception occured " + e.getMessage());
			serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR, "500 ERROR");
		}
		serviceResponse.setStatus(serviceStatus);
		serviceResponse.setResult(serviceResult);
		LOGGER.info(" Returning REST response with Result.." + serviceResult.getDetail() + " and status " + serviceResponse.getStatus().getCode().getCode() + "-"
				+ serviceResponse.getStatus().getMessage());
		return serviceResponse;
	}
	
	/**
	 * To Submit CR
	 * @param crID - Change Request Id
	 * @param request - HttpServletRequest
	 * @return Boolean
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/changeRequest/submitCR/{crID}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	ServiceResponse<Boolean> submitCR(@PathVariable("crID") Long crID,
			HttpServletRequest request) {

		ServiceStatus serviceStatus = null;
		ServiceResponse<Boolean> serviceResponse = new ServiceResponse<Boolean>();
		@SuppressWarnings("rawtypes")
		ServiceResult serviceResult = new ServiceResult();
	
		UserDetails userDetails = ModelAssembler.getDefaultUserDetails();
		userDetails.setUserId(80);
		ChangeRequest changeRequest = new ChangeRequest();
		changeRequest.setId(crID);

		try {
			changeRequestService.submitChangeRequest(changeRequest, userDetails);
			serviceResult.setDetail(true);
			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
		} catch (ChangeRequestServiceException e) {
			serviceResult.setDetail("Exception occured " + e.getMessage());
			serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR, "500 ERROR");
		}
		serviceResponse.setStatus(serviceStatus);
		serviceResponse.setResult(serviceResult);
		LOGGER.info(" Returning REST response with Result.." + serviceResult.getDetail() + " and status " + serviceResponse.getStatus().getCode().getCode() + "-"
				+ serviceResponse.getStatus().getMessage());
		return serviceResponse;
	}
	
	/**
	 * To WithDraw CR
	 * @param crID - Change Request Id
	 * @param request - HttpServletRequest
	 * @return Boolean
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/changeRequest/cancelCR/{crID}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	ServiceResponse<Boolean> withDrawlCR(@PathVariable("crID") Long crID,
			HttpServletRequest request) {

		ServiceStatus serviceStatus = null;
		ServiceResponse<Boolean> serviceResponse = new ServiceResponse<Boolean>();
		@SuppressWarnings("rawtypes")
		ServiceResult serviceResult = new ServiceResult();
	
		UserDetails userDetails = ModelAssembler.getDefaultUserDetails();
		userDetails.setUserId(80);
		ChangeRequest changeRequest = new ChangeRequest();
		changeRequest.setId(crID);

		try {
			changeRequestService.withdrawChangeRequest(changeRequest, userDetails);
			serviceResult.setDetail(true);
			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
		} catch (ChangeRequestServiceException e) {
			serviceResult.setDetail("Exception occured " + e.getMessage());
			serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR, "500 ERROR");
		}
		serviceResponse.setStatus(serviceStatus);
		serviceResponse.setResult(serviceResult);
		LOGGER.info(" Returning REST response with Result.." + serviceResult.getDetail() + " and status " + serviceResponse.getStatus().getCode().getCode() + "-"
				+ serviceResponse.getStatus().getMessage());
		return serviceResponse;
	}

	/**
	 * To Get CR Line Items By CR Id
	 * @param crID - Change Request Id
	 * @param request - HttpServletRequest
	 * @return changeRequest - ChangeRequest
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/changeRequest/getCRLineItemsByCRId/{crID}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	ServiceResponse<ChangeRequest> getCRLineItemsByCRId(@PathVariable("crID") Long crID, HttpServletRequest request) {

		ServiceStatus serviceStatus = null;
		ServiceResponse<ChangeRequest> serviceResponse = new ServiceResponse<ChangeRequest>();
		@SuppressWarnings("rawtypes")
		ServiceResult serviceResult = new ServiceResult();
		ChangeRequest changeRequest = new ChangeRequest();
		changeRequest.setId(crID);
		UserDetails user = ModelAssembler.getDefaultUserDetails();

		try {
			serviceResult.setDetail(changeRequestService.getAllChangeRequestsLineItemsbyChangeRequestId(changeRequest, user));
			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
		} catch (ChangeRequestServiceException e) {
			serviceResult.setDetail("Exception occured " + e.getMessage());
			serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR, "500 ERROR");
		}
		serviceResponse.setStatus(serviceStatus);
		serviceResponse.setResult(serviceResult);
		LOGGER.info(" Returning REST response with Result.." + serviceResult.getDetail() + " and status " + serviceResponse.getStatus().getCode().getCode() + "-"
				+ serviceResponse.getStatus().getMessage());
		return serviceResponse;
	}

	/**
	 * To Get Customer Alignment ChangeRequest Details By ChangeRequest
	 * @param crID - Change Request Id
	 * @param request - HttpServletRequest
	 * @return List<CustomerAlignmentChangeRequestDetails>
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/changeRequest/getCACRLineItemsByCRId/{crID}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	ServiceResponse<List<CustomerAlignmentChangeRequestDetails>> getCustomerAlignmentChangeRequestDetailsByChangeRequest(@PathVariable("crID") Long crID, HttpServletRequest request) {

		ServiceStatus serviceStatus = null;
		ServiceResponse<List<CustomerAlignmentChangeRequestDetails>> serviceResponse = new ServiceResponse<List<CustomerAlignmentChangeRequestDetails>>();
		@SuppressWarnings("rawtypes")
		ServiceResult serviceResult = new ServiceResult();
		ChangeRequest changeRequest = new ChangeRequest();
		changeRequest.setId(crID);
		UserDetails user = ModelAssembler.getDefaultUserDetails();

		try {
			serviceResult.setDetail(changeRequestService.getCustomerAlignmentChangeRequestDetailsByChangeRequest(changeRequest, user));
			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
		} catch (ChangeRequestServiceException e) {
			serviceResult.setDetail("Exception occured " + e.getMessage());
			serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR, "500 ERROR");
		}
		serviceResponse.setStatus(serviceStatus);
		serviceResponse.setResult(serviceResult);
		LOGGER.info(" Returning REST response with Result.." + serviceResult.getDetail() + " and status " + serviceResponse.getStatus().getCode().getCode() + "-"
				+ serviceResponse.getStatus().getMessage());
		return serviceResponse;
	}

	/** 
	 * To Get ChangeRequests Tasks From Approver SalesPosition
	 * @param salesPositionID - Sales Position ID
	 * @param hierID - Hierarchy ID
	 * @param request - HttpServletRequest
	 * @return List<String>
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/changeRequest/getApprTasks/{salesPositionID}/{hierID}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	ServiceResponse<List<String>> getChangeRequestsTasksFromApproverSalesPosition(@PathVariable("salesPositionID") Long salesPositionID, @PathVariable("hierID") Long hierID, HttpServletRequest request) {

		ServiceStatus serviceStatus = null;
		ServiceResponse<List<String>> serviceResponse = new ServiceResponse<List<String>>();
		@SuppressWarnings("rawtypes")
		ServiceResult serviceResult = new ServiceResult();
		SalesPosition salesPosition = new SalesPosition();
		salesPosition.setId(salesPositionID);
		UserDetails userDetails = ModelAssembler.getDefaultUserDetails();

		try {
			serviceResult.setDetail(changeRequestService.getChangeRequestsTasksFromApproverSalesPosition(salesPosition, userDetails));
			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
		} catch (ChangeRequestServiceException e) {
			serviceResult.setDetail("Exception occured " + e.getMessage());
			serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR, "500 ERROR");
		}
		serviceResponse.setStatus(serviceStatus);
		serviceResponse.setResult(serviceResult);
		LOGGER.info(" Returning REST response with Result.." + serviceResult.getDetail() + " and status " + serviceResponse.getStatus().getCode().getCode() + "-"
				+ serviceResponse.getStatus().getMessage());
		return serviceResponse;
	}
	
	/**
	 * To Get ZipAlignment ChangeRequest Details By ChangeRequest
	 * @param crID - Change Request Id
	 * @param request - HttpServletRequest
	 * @return List<ZipAlignmentChangeRequestDetails>
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/changeRequest/getZACRLineItemsByCRId/{crID}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	ServiceResponse<List<ZipAlignmentChangeRequestDetails>> getZipAlignmentChangeRequestDetailsByChangeRequest(@PathVariable("crID") Long crID, HttpServletRequest request) {

		ServiceStatus serviceStatus = null;
		ServiceResponse<List<ZipAlignmentChangeRequestDetails>> serviceResponse = new ServiceResponse<List<ZipAlignmentChangeRequestDetails>>();
		@SuppressWarnings("rawtypes")
		ServiceResult serviceResult = new ServiceResult();
		ChangeRequest changeRequest = new ChangeRequest();
		changeRequest.setId(crID);
		UserDetails user = ModelAssembler.getDefaultUserDetails();

		try {
			serviceResult.setDetail(changeRequestService.getZipAlignmentChangeRequestDetailsByChangeRequest(changeRequest, user));
			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
		} catch (ChangeRequestServiceException e) {
			serviceResult.setDetail("Exception occured " + e.getMessage());
			serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR, "500 ERROR");
		}
		serviceResponse.setStatus(serviceStatus);
		serviceResponse.setResult(serviceResult);
		LOGGER.info(" Returning REST response with Result.." + serviceResult.getDetail() + " and status " + serviceResponse.getStatus().getCode().getCode() + "-"
				+ serviceResponse.getStatus().getMessage());
		return serviceResponse;
	}
	
	/**
	 * To Approve CR
	 * @param crID - Change Request Id
	 * @param spID - Sales positionID
	 * @param hierID - Hierarchy ID
	 * @param request - HttpServletRequest
	 * @return Boolean
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/changeRequest/approveCR/{crID}/{spID}/{hierID}/{comments}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	ServiceResponse<Boolean> approveCR(@PathVariable("crID") Long crID, @PathVariable("spID") Long spID, @PathVariable("hierID") Long hierID,@PathVariable("comments") String comments,
			HttpServletRequest request) {

		ServiceStatus serviceStatus = null;
		ServiceResponse<Boolean> serviceResponse = new ServiceResponse<Boolean>();
		@SuppressWarnings("rawtypes")
		ServiceResult serviceResult = new ServiceResult();
	
		UserDetails userDetails = ModelAssembler.getDefaultUserDetails();
		userDetails.setUserId(80);
		ChangeRequest changeRequest = new ChangeRequest();
		changeRequest.setId(crID);
		
		SalesPosition salesPosition = new SalesPosition();
		SalesOrgHierarchy salesOrgHierarchy = new SalesOrgHierarchy();
		salesPosition.setId(spID);
		salesOrgHierarchy.setId(hierID);
		salesPosition.setHierarchy(salesOrgHierarchy);

		try {
			changeRequestService.approveChangeRequest(changeRequest, userDetails, salesPosition, comments);
			serviceResult.setDetail(true);
			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
		} catch (ChangeRequestServiceException e) {
			serviceResult.setDetail("Exception occured " + e.getMessage());
			serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR, "500 ERROR");
		}
		serviceResponse.setStatus(serviceStatus);
		serviceResponse.setResult(serviceResult);
		LOGGER.info(" Returning REST response with Result.." + serviceResult.getDetail() + " and status " + serviceResponse.getStatus().getCode().getCode() + "-"
				+ serviceResponse.getStatus().getMessage());
		return serviceResponse;
	}
	
	
	/**
	 * To Reject CR
	 * @param crID - Change Request Id
	 * @param spID - Sales positionID
	 * @param hierID - Hierarchy ID
	 * @param request - HttpServletRequest
	 * @return Boolean
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/changeRequest/rejectCR/{crID}/{spID}/{hierID}/{comments}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	ServiceResponse<Boolean> rejectCR(@PathVariable("crID") Long crID,@PathVariable("spID") Long spID,@PathVariable("hierID") Long hierID,@PathVariable("comments") String comments,
			HttpServletRequest request) {

		ServiceStatus serviceStatus = null;
		ServiceResponse<Boolean> serviceResponse = new ServiceResponse<Boolean>();
		@SuppressWarnings("rawtypes")
		ServiceResult serviceResult = new ServiceResult();
	
		UserDetails userDetails = ModelAssembler.getDefaultUserDetails();
		userDetails.setUserId(80);
		ChangeRequest changeRequest = new ChangeRequest();
		changeRequest.setId(crID);
		
		SalesPosition salesPosition = new SalesPosition();
		SalesOrgHierarchy salesOrgHierarchy = new SalesOrgHierarchy();
		salesPosition.setId(spID);
		salesOrgHierarchy.setId(hierID);
		salesPosition.setHierarchy(salesOrgHierarchy);

		try {
			changeRequestService.rejectChangeRequest(changeRequest, userDetails, salesPosition, comments);
			serviceResult.setDetail(true);
			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
		} catch (ChangeRequestServiceException e) {
			serviceResult.setDetail("Exception occured " + e.getMessage());
			serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR, "500 ERROR");
		}
		serviceResponse.setStatus(serviceStatus);
		serviceResponse.setResult(serviceResult);
		LOGGER.info(" Returning REST response with Result.." + serviceResult.getDetail() + " and status " + serviceResponse.getStatus().getCode().getCode() + "-"
				+ serviceResponse.getStatus().getMessage());
		return serviceResponse;
	}
	
	
	/**
	 * @param crID
	 * @param request
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/changeRequest/getCRApproversDetails/{crID}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	ServiceResponse<List<ChangeRequestApproverDetails>> 
	getChangeRequestApproversDetails(@PathVariable("crID") Long crID, HttpServletRequest request) {

		ServiceStatus serviceStatus = null;
		ServiceResponse<List<ChangeRequestApproverDetails>> serviceResponse = new ServiceResponse<List<ChangeRequestApproverDetails>>();
		@SuppressWarnings("rawtypes")
		ServiceResult serviceResult = new ServiceResult();
		ChangeRequest changeRequest = new ChangeRequest();
		changeRequest.setId(crID);
		UserDetails user = ModelAssembler.getDefaultUserDetails();

		try {
			serviceResult.setDetail(changeRequestService.getChangeRequestApproversDetails(changeRequest, user));
			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
		} catch (ChangeRequestServiceException e) {
			serviceResult.setDetail("Exception occured " + e.getMessage());
			serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR, "500 ERROR");
		}
		serviceResponse.setStatus(serviceStatus);
		serviceResponse.setResult(serviceResult);
		LOGGER.info(" Returning REST response with Result.." + serviceResult.getDetail() + " and status " + serviceResponse.getStatus().getCode().getCode() + "-"
				+ serviceResponse.getStatus().getMessage());
		return serviceResponse;
	}
	
	
	/**
	 * @param changeRequestService
	 *            the changeRequestService to set
	 */
	public void setChangeRequestService(ChangeRequestService changeRequestService) {
		this.changeRequestService = changeRequestService;
	}

}
