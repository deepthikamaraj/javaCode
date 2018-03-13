package com.cognizant.opserv.sp.rest.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cognizant.opserv.query.ExpressionCriteria;
import com.cognizant.opserv.query.IExpressionCriteria;
import com.cognizant.opserv.query.IQuery;
import com.cognizant.opserv.query.Query;
import com.cognizant.opserv.sp.exception.ViewServiceException;
import com.cognizant.opserv.sp.model.ViewData;
import com.cognizant.opserv.sp.model.ViewHeader;
import com.cognizant.opserv.sp.rest.util.ModelAssembler;
import com.cognizant.opserv.sp.service.view.ViewService;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.rest.common.StatusCode;
import com.cognizant.peg.web.common.ServiceResponse;
import com.cognizant.peg.web.common.ServiceResult;
import com.cognizant.peg.web.common.ServiceStatus;

@Controller
public class SPViewRestController {
	
	/**
	 * The constant LOGGER
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(SPViewRestController.class);
	
	/**
	 * The viewService bean
	 */
	@Autowired
	ViewService viewService;

	/**
	 * @param request
	 * @return
	 */
	// URL : http://10.232.199.133:8080/sprs/customerAlignmentViewHeader?client=am
	@RequestMapping(value="/customerAlignmentViewHeader", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody ServiceResponse<ViewHeader> getCustomerAlignmentViewHeader(HttpServletRequest request) {
		
		ServiceStatus serviceStatus = null;
		ServiceResponse serviceResponse = new ServiceResponse();
		ServiceResult serviceResult = new ServiceResult();
		
		try {
			LOGGER.info("===============Inside getCustomerAlignmentViewHeader==================");
			serviceResult.setDetail(viewService.getCustomerAlignmentViewHeader(ModelAssembler.getDefaultUserDetails()));
			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
		} catch (ViewServiceException e) {
			LOGGER.error("Error in getCustomerAlignmentViewHeader");
			serviceResult.setDetail("Exception occured "+e.getMessage());
			serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR, "500 ERROR");
		}
		serviceResponse.setStatus(serviceStatus);
		serviceResponse.setResult(serviceResult);
		LOGGER.info(" Returning REST response with Result.."+serviceResult.getDetail()+" and status "+serviceResponse.getStatus().getCode().getCode()+"-"+serviceResponse.getStatus().getMessage());
		return serviceResponse;
	}
	
	/**
	 * @param request
	 * @return
	 */
	//URL : http://10.232.199.133:8080/sprs/customerAlignmentViewList/SalesPositionID/57/sales_team_id/2/and/SalesPositionID/SalesPositionID?client=am
	@RequestMapping(value="/customerAlignmentViewList/{criteriaName1}/{criteriaValue1}/{criteriaName2}/{criteriaValue2}/{condition}/{orderByVal}/{groupByVal}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody ServiceResponse<List<ViewData>> getCustomerAlignments(@PathVariable("criteriaName1") String criteriaName1, 
																				@PathVariable("criteriaValue1") Object criteriaValue1, 
																				@PathVariable("criteriaName2") String criteriaName2, 
																				@PathVariable("criteriaValue2") Object criteriaValue2, 
																				@PathVariable("condition") String condition, 
																				@PathVariable("orderByVal") String orderByVal, 
																				@PathVariable("groupByVal") String groupByVal, 
																				HttpServletRequest request) {
		
		ServiceStatus serviceStatus = null;
		ServiceResponse serviceResponse = new ServiceResponse();
		ServiceResult serviceResult = new ServiceResult();
		
		IExpressionCriteria expressionSP = ExpressionCriteria.createEqualToCriteria(criteriaName1, criteriaValue1);
		IExpressionCriteria expressionSP2 = null;
		
		if(null != criteriaName2 && null != criteriaValue2 && !criteriaName2.equalsIgnoreCase("null") && !criteriaValue2.equals("null")){
			expressionSP2 = ExpressionCriteria.createEqualToCriteria(criteriaName2, criteriaValue2);;
		}

		IExpressionCriteria expressionTargetCustomer = null;
		
		IQuery queryStructure = null;
		
		boolean multiCondition = false;
		
		if(null != condition && !condition.equalsIgnoreCase("null") && null != expressionSP2){
			multiCondition = true;
			if(condition.equalsIgnoreCase("and")){
				expressionTargetCustomer = expressionSP.and(expressionSP2);
				if(null != orderByVal && !orderByVal.equalsIgnoreCase("null")){
					queryStructure = new Query().criteria(expressionTargetCustomer).orderBy(orderByVal);
				}
				if(null != groupByVal && !groupByVal.equalsIgnoreCase("null")){
					queryStructure = new Query().criteria(expressionTargetCustomer).groupBy(groupByVal);
				}
			}
			else if (condition.equalsIgnoreCase("or")){
				expressionTargetCustomer = expressionSP.or(expressionSP2);
				if(null != orderByVal && !orderByVal.equalsIgnoreCase("null")){
					queryStructure = new Query().criteria(expressionTargetCustomer).orderBy(orderByVal);
				}
				if(null != groupByVal && !groupByVal.equalsIgnoreCase("null")){
					queryStructure = new Query().criteria(expressionTargetCustomer).groupBy(groupByVal);
				}
			}
		}
        
		if (multiCondition){
			serviceResponse = getResponseForgetCustomerAlignments(serviceResponse, serviceResult, serviceStatus, queryStructure);
		}
		
		return serviceResponse;
		
		
	}
	
	private ServiceResponse<List<ViewData>> getResponseForgetCustomerAlignments(ServiceResponse serviceResponse, ServiceResult serviceResult, ServiceStatus serviceStatus, IQuery queryStructure){
		try {
			LOGGER.info("===============Inside getCustomerAlignments==================");
			serviceResult.setDetail(viewService.getCustomerAlignments(queryStructure, ModelAssembler.getDefaultUserDetails()));
			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
		} catch (ViewServiceException e) {
			LOGGER.error("Error in getCustomerAlignments");
			serviceResult.setDetail("Exception occured "+e.getMessage());
			serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR, "500 ERROR");
		}
		serviceResponse.setStatus(serviceStatus);
		serviceResponse.setResult(serviceResult);
		LOGGER.info(" Returning REST response with Result.."+serviceResult.getDetail()+" and status "+serviceResponse.getStatus().getCode().getCode()+"-"+serviceResponse.getStatus().getMessage());
		return serviceResponse;
	}
	
	/**
	 * @param request
	 * @return
	 */
	//URL : http://10.232.199.133:8080/sprs/getResultCountForCustomerAlgnmntViewList/DestinationTerritoryCode/3PA21?client=am
	@RequestMapping(value="/getResultCountForCustomerAlgnmntViewList/{criteriaName}/{criteriaValue}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody ServiceResponse<List<ViewData>> getResultCountForCustomerAlgnmntView(@PathVariable("criteriaName") String criteriaName, @PathVariable("criteriaValue") Object criteriaValue, HttpServletRequest request) {
		
		ServiceStatus serviceStatus = null;
		ServiceResponse serviceResponse = new ServiceResponse();
		ServiceResult serviceResult = new ServiceResult();
		IExpressionCriteria c1 = ExpressionCriteria.createEqualToCriteria(criteriaName, criteriaValue);
		
		IQuery queryStructure = new Query().criteria(c1);
		
		try {
			LOGGER.info("===============Inside getResultCountForCustomerAlgnmntView==================");
			serviceResult.setDetail(viewService.getResultCountForCustomerAlgnmntView(queryStructure, ModelAssembler.getDefaultUserDetails()));
			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
		} catch (ViewServiceException e) {
			LOGGER.error("Error in getResultCountForCustomerAlgnmntView");
			serviceResult.setDetail("Exception occured "+e.getMessage());
			serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR, "500 ERROR");
		}
		serviceResponse.setStatus(serviceStatus);
		serviceResponse.setResult(serviceResult);
		LOGGER.info(" Returning REST response with Result.."+serviceResult.getDetail()+" and status "+serviceResponse.getStatus().getCode().getCode()+"-"+serviceResponse.getStatus().getMessage());
		return serviceResponse;
	}
	
	/**
	 * @param request
	 * @return
	 */
	//URL : http://10.232.199.133:8080/sprs/getResultCountForChangeRequestViewList/DestinationTerritoryCode/3PA21?client=am
	@RequestMapping(value="/getResultCountForChangeRequestViewList/{criteriaName}/{criteriaValue}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody ServiceResponse<List<ViewData>> getResultCountForChangeRequestView(@PathVariable("criteriaName") String criteriaName, @PathVariable("criteriaValue") Object criteriaValue, HttpServletRequest request) {
		
		ServiceStatus serviceStatus = null;
		ServiceResponse serviceResponse = new ServiceResponse();
		ServiceResult serviceResult = new ServiceResult();
		IExpressionCriteria c1 = ExpressionCriteria.createEqualToCriteria(criteriaName, criteriaValue);
		
		IQuery queryStructure = new Query().criteria(c1);
		
		try {
			LOGGER.info("===============Inside getResultCountForChangeRequestView==================");
			serviceResult.setDetail(viewService.getResultCountForChangeRequestView(queryStructure, ModelAssembler.getDefaultUserDetails()));
			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
		} catch (ViewServiceException e) {
			LOGGER.error("Error in getResultCountForChangeRequestView");
			serviceResult.setDetail("Exception occured "+e.getMessage());
			serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR, "500 ERROR");
		}
		serviceResponse.setStatus(serviceStatus);
		serviceResponse.setResult(serviceResult);
		LOGGER.info(" Returning REST response with Result.."+serviceResult.getDetail()+" and status "+serviceResponse.getStatus().getCode().getCode()+"-"+serviceResponse.getStatus().getMessage());
		return serviceResponse;
	}
	
	/**
	 * @param request
	 * @return
	 */
	// URL : http://10.232.199.133:8080/sprs/getChangeRequestViewHeader/DestinationTerritoryCode/3PA21?client=am
	@RequestMapping(value="/getChangeRequestViewHeader/{criteriaName}/{criteriaValue}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody ServiceResponse<List<ViewData>> getChangeRequestViewHeader(@PathVariable("criteriaName") String criteriaName, @PathVariable("criteriaValue") Object criteriaValue, HttpServletRequest request) {
		
		ServiceStatus serviceStatus = null;
		ServiceResponse serviceResponse = new ServiceResponse();
		ServiceResult serviceResult = new ServiceResult();
		IExpressionCriteria c1 = ExpressionCriteria.createEqualToCriteria(criteriaName, criteriaValue);
		
		IQuery queryStructure = new Query().criteria(c1);
		
		try {
			LOGGER.info("===============Inside getChangeRequestViewHeader==================");
			serviceResult.setDetail(viewService.getChangeRequestViewHeader(ModelAssembler.getDefaultUserDetails()));
			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
		} catch (ViewServiceException e) {
			LOGGER.error("Error in getChangeRequestViewHeader");
			serviceResult.setDetail("Exception occured "+e.getMessage());
			serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR, "500 ERROR");
		}
		serviceResponse.setStatus(serviceStatus);
		serviceResponse.setResult(serviceResult);
		LOGGER.info(" Returning REST response with Result.."+serviceResult.getDetail()+" and status "+serviceResponse.getStatus().getCode().getCode()+"-"+serviceResponse.getStatus().getMessage());
		return serviceResponse;
	}
	
	/**
	 * @param request
	 * @return
	 */
	//URL : http://10.232.199.133:8080/sprs/getChangeRequestsList/DestinationTerritoryCode/3PA21?client=am
	@RequestMapping(value="/getChangeRequestsList/{criteriaName}/{criteriaValue}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody ServiceResponse<List<ViewData>> getChangeRequests(@PathVariable("criteriaName") String criteriaName, @PathVariable("criteriaValue") Object criteriaValue, HttpServletRequest request) {
		
		ServiceStatus serviceStatus = null;
		ServiceResponse serviceResponse = new ServiceResponse();
		ServiceResult serviceResult = new ServiceResult();
		IExpressionCriteria c1 = ExpressionCriteria.createEqualToCriteria(criteriaName, criteriaValue);
		
		IQuery queryStructure = new Query().criteria(c1);
		
		try {
			LOGGER.info("===============Inside getChangeRequests==================");
			serviceResult.setDetail(viewService.getChangeRequests(queryStructure, ModelAssembler.getDefaultUserDetails()));
			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
		} catch (ViewServiceException e) {
			LOGGER.error("Error in getChangeRequests");
			serviceResult.setDetail("Exception occured "+e.getMessage());
			serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR, "500 ERROR");
		}
		serviceResponse.setStatus(serviceStatus);
		serviceResponse.setResult(serviceResult);
		LOGGER.info(" Returning REST response with Result.."+serviceResult.getDetail()+" and status "+serviceResponse.getStatus().getCode().getCode()+"-"+serviceResponse.getStatus().getMessage());
		return serviceResponse;
	}
	
	/**
	 * @param request
	 * @return
	 */
	//URL : http://10.232.199.133:8080/sprs/getChangeRequestApproverViewHeader?client=am
	@RequestMapping(value="/getChangeRequestApproverViewHeader", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody ServiceResponse<List<ViewData>> getChangeRequestApproverViewHeader(HttpServletRequest request) {
		
		ServiceStatus serviceStatus = null;
		ServiceResponse serviceResponse = new ServiceResponse();
		ServiceResult serviceResult = new ServiceResult();
		
		try {
			LOGGER.info("===============Inside getChangeRequestApproverViewHeader==================");
			serviceResult.setDetail(viewService.getChangeRequestApproverViewHeader(ModelAssembler.getDefaultUserDetails()));
			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
		} catch (ViewServiceException e) {
			LOGGER.error("Error in getChangeRequests");
			serviceResult.setDetail("Exception occured "+e.getMessage());
			serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR, "500 ERROR");
		}
		serviceResponse.setStatus(serviceStatus);
		serviceResponse.setResult(serviceResult);
		LOGGER.info(" Returning REST response with Result.."+serviceResult.getDetail()+" and status "+serviceResponse.getStatus().getCode().getCode()+"-"+serviceResponse.getStatus().getMessage());
		return serviceResponse;
	}
	
	/**
	 * @param request
	 * @return
	 */
	//URL : http://10.232.199.133:8080/sprs/getChangeRequestApproversList/DestinationTerritoryCode/3PA21/SourceTerritoryCode/3PB22/and/RequestID/RequestID?client=am
	@RequestMapping(value="/getChangeRequestApproversList/{criteriaName1}/{criteriaValue1}/{criteriaName2}/{criteriaValue2}/{condition}/{orderByVal}/{groupByVal}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody ServiceResponse<List<ViewData>> getChangeRequestApprovers(@PathVariable("criteriaName1") String criteriaName1, 
																					@PathVariable("criteriaValue1") Object criteriaValue1, 
																					@PathVariable("criteriaName2") String criteriaName2, 
																					@PathVariable("criteriaValue2") Object criteriaValue2, 
																					@PathVariable("condition") String condition, 
																					@PathVariable("orderByVal") String orderByVal, 
																					@PathVariable("groupByVal") String groupByVal, 
																					HttpServletRequest request) {
		
		ServiceStatus serviceStatus = null;
		ServiceResponse serviceResponse = new ServiceResponse();
		ServiceResult serviceResult = new ServiceResult();
		
		IExpressionCriteria expressionSP = ExpressionCriteria.createEqualToCriteria(criteriaName1, criteriaValue1);
		IExpressionCriteria expressionSP2 = null;
		if(null != criteriaName2 && null != criteriaValue2 && !criteriaName2.equalsIgnoreCase("null") && !criteriaValue2.equals("null")){
			expressionSP2 = ExpressionCriteria.createEqualToCriteria(criteriaName2, criteriaValue2);;
		}
		
		IExpressionCriteria expressionTargetCustomer = null;
		
		IQuery queryStructure = null;
		
		boolean multiCondition = false;
		
		if(null != condition && !condition.equalsIgnoreCase("null") && null != expressionSP2){
			multiCondition = true;
			if(condition.equalsIgnoreCase("and")){
				expressionTargetCustomer = expressionSP.and(expressionSP2);
				if(null != orderByVal && !orderByVal.equalsIgnoreCase("null")){
					queryStructure = new Query().criteria(expressionTargetCustomer).orderBy(orderByVal);
				}
				if(null != groupByVal && !groupByVal.equalsIgnoreCase("null")){
					queryStructure = new Query().criteria(expressionTargetCustomer).groupBy(groupByVal);
				}
			}
			else if (condition.equalsIgnoreCase("or")){
				expressionTargetCustomer = expressionSP.or(expressionSP2);
				if(null != orderByVal && !orderByVal.equalsIgnoreCase("null")){
					queryStructure = new Query().criteria(expressionTargetCustomer).orderBy(orderByVal);
				}
				if(null != groupByVal && !groupByVal.equalsIgnoreCase("null")){
					queryStructure = new Query().criteria(expressionTargetCustomer).groupBy(groupByVal);
				}
			}
		}
        
		if (multiCondition){
			serviceResponse = getResponseForChangeRequestApprovers(serviceResponse, serviceResult, serviceStatus, queryStructure);
		}
		
		return serviceResponse;
		
	}
	
	
	/**
	 * @param serviceResponse
	 * @param serviceResult
	 * @param serviceStatus
	 * @param queryStructure
	 * @return
	 */
	private ServiceResponse<List<ViewData>> getResponseForChangeRequestApprovers(ServiceResponse serviceResponse, ServiceResult serviceResult, ServiceStatus serviceStatus, IQuery queryStructure){
		try {
			LOGGER.info("===============Inside getResponseForChangeRequestApprovers==================");
			serviceResult.setDetail(viewService.getChangeRequestApprovers(queryStructure, ModelAssembler.getDefaultUserDetails()));
			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
		} catch (ViewServiceException e) {
			LOGGER.error("Error in getChangeRequestApprovers");
			serviceResult.setDetail("Exception occured "+e.getMessage());
			serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR, "500 ERROR");
		}
		serviceResponse.setStatus(serviceStatus);
		serviceResponse.setResult(serviceResult);
		LOGGER.info(" Returning REST response with Result.."+serviceResult.getDetail()+" and status "+serviceResponse.getStatus().getCode().getCode()+"-"+serviceResponse.getStatus().getMessage());
		return serviceResponse;
	}
	
	
	/**
	 * @param request
	 * @return
	 */
	//URL : http://10.232.199.133:8080/sprs/getResultCountForChangeRequestApproverView/DestinationTerritoryCode/3PA21?client=am
	@RequestMapping(value="/getResultCountForChangeRequestApproverView/{criteriaName}/{criteriaValue}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody ServiceResponse<List<ViewData>> getResultCountForChangeRequestApproverView(@PathVariable("criteriaName") String criteriaName, @PathVariable("criteriaValue") Object criteriaValue, HttpServletRequest request) {
		
		ServiceStatus serviceStatus = null;
		ServiceResponse serviceResponse = new ServiceResponse();
		ServiceResult serviceResult = new ServiceResult();
		IExpressionCriteria c1 = ExpressionCriteria.createEqualToCriteria(criteriaName, criteriaValue);
		
		IQuery queryStructure = new Query().criteria(c1);
		
		try {
			LOGGER.info("===============Inside getResultCountForChangeRequestApproverView==================");
			serviceResult.setDetail(viewService.getResultCountForChangeRequestApproverView(queryStructure, ModelAssembler.getDefaultUserDetails()));
			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
		} catch (ViewServiceException e) {
			LOGGER.error("Error in getResultCountForChangeRequestApproverView");
			serviceResult.setDetail("Exception occured "+e.getMessage());
			serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR, "500 ERROR");
		}
		serviceResponse.setStatus(serviceStatus);
		serviceResponse.setResult(serviceResult);
		LOGGER.info(" Returning REST response with Result.."+serviceResult.getDetail()+" and status "+serviceResponse.getStatus().getCode().getCode()+"-"+serviceResponse.getStatus().getMessage());
		return serviceResponse;
	}
	
	/**
	 * @param request
	 * @return
	 */
	//URL : http://10.232.199.133:8080/sprs/getCustomerUniverseViewHeader?client=am
	@RequestMapping(value="/getCustomerUniverseViewHeader", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody ServiceResponse<List<ViewData>> getCustomerUniverseViewHeader(HttpServletRequest request) {
		
		ServiceStatus serviceStatus = null;
		ServiceResponse serviceResponse = new ServiceResponse();
		ServiceResult serviceResult = new ServiceResult();
		
		try {
			LOGGER.info("===============Inside getCustomerUniverseViewHeader==================");
			serviceResult.setDetail(viewService.getCustomerUniverseViewHeader(ModelAssembler.getDefaultUserDetails()));
			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
		} catch (ViewServiceException e) {
			LOGGER.error("Error in getCustomerUniverseViewHeader");
			serviceResult.setDetail("Exception occured "+e.getMessage());
			serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR, "500 ERROR");
		}
		serviceResponse.setStatus(serviceStatus);
		serviceResponse.setResult(serviceResult);
		LOGGER.info(" Returning REST response with Result.."+serviceResult.getDetail()+" and status "+serviceResponse.getStatus().getCode().getCode()+"-"+serviceResponse.getStatus().getMessage());
		return serviceResponse;
	}
	
	/**
	 * @param request
	 * @return
	 */
	//URL : http://10.232.199.133:8080/sprs/getCustomerUniverseList/Customer_ID/1001?client=am
	@RequestMapping(value="/getCustomerUniverseList/{criteriaName}/{criteriaValue}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody ServiceResponse<List<ViewData>> getCustomerUniverse(@PathVariable("criteriaName") String criteriaName, @PathVariable("criteriaValue") Object criteriaValue, HttpServletRequest request) {
		
		ServiceStatus serviceStatus = null;
		ServiceResponse serviceResponse = new ServiceResponse();
		ServiceResult serviceResult = new ServiceResult();
		IExpressionCriteria c1 = ExpressionCriteria.createEqualToCriteria(criteriaName, criteriaValue);
		
		IQuery queryStructure = new Query().criteria(c1);
		
		try {
			LOGGER.info("===============Inside getCustomerUniverse==================");
			serviceResult.setDetail(viewService.getCustomerUniverse(queryStructure, ModelAssembler.getDefaultUserDetails()));
			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
		} catch (ViewServiceException e) {
			LOGGER.error("Error in getCustomerUniverse");
			serviceResult.setDetail("Exception occured "+e.getMessage());
			serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR, "500 ERROR");
		}
		serviceResponse.setStatus(serviceStatus);
		serviceResponse.setResult(serviceResult);
		LOGGER.info(" Returning REST response with Result.."+serviceResult.getDetail()+" and status "+serviceResponse.getStatus().getCode().getCode()+"-"+serviceResponse.getStatus().getMessage());
		return serviceResponse;
	}
	
	/**
	 * @param request
	 * @return
	 */
	//URL : http://10.232.199.133:8080/sprs/getResultCountForCustomerUniverseView/Customer_ID/1001?client=am
	@RequestMapping(value="/getResultCountForCustomerUniverseView/{criteriaName}/{criteriaValue}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody ServiceResponse<List<ViewData>> getResultCountForCustomerUniverseView(@PathVariable("criteriaName") String criteriaName, @PathVariable("criteriaValue") Object criteriaValue, HttpServletRequest request) {
		
		ServiceStatus serviceStatus = null;
		ServiceResponse serviceResponse = new ServiceResponse();
		ServiceResult serviceResult = new ServiceResult();
		IExpressionCriteria c1 = ExpressionCriteria.createEqualToCriteria(criteriaName, criteriaValue);
		
		IQuery queryStructure = new Query().criteria(c1);
		
		try {
			LOGGER.info("===============Inside getResultCountForCustomerUniverseView==================");
			serviceResult.setDetail(viewService.getResultCountForCustomerUniverseView(queryStructure, ModelAssembler.getDefaultUserDetails()));
			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
		} catch (ViewServiceException e) {
			LOGGER.error("Error in getResultCountForCustomerUniverseView");
			serviceResult.setDetail("Exception occured "+e.getMessage());
			serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR, "500 ERROR");
		}
		serviceResponse.setStatus(serviceStatus);
		serviceResponse.setResult(serviceResult);
		LOGGER.info(" Returning REST response with Result.."+serviceResult.getDetail()+" and status "+serviceResponse.getStatus().getCode().getCode()+"-"+serviceResponse.getStatus().getMessage());
		return serviceResponse;
	}
	
}