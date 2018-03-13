package com.cognizant.opserv.sp.rest.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cognizant.opserv.sp.common.MetricTriggerType;
import com.cognizant.opserv.sp.cr.process.internal.service.MetricOfflineService;
import com.cognizant.opserv.sp.exception.AlignmentServiceException;
import com.cognizant.opserv.sp.exception.MetricExecutionException;
import com.cognizant.opserv.sp.exception.MetricViolationException;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.rest.util.ModelAssembler;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.rest.common.StatusCode;
import com.cognizant.peg.web.common.ServiceResponse;
import com.cognizant.peg.web.common.ServiceResult;
import com.cognizant.peg.web.common.ServiceStatus;

@Controller
public class MetricOfflineRestController {

	/*
	 * Instantiate the Logger
	 */	
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(MetricOfflineRestController.class);
	
	/**
	 * MetricOfflineService has the execute metrics.
	 */
	@Autowired
	private MetricOfflineService metricOfflineService;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/metrics/processCalculativeMetrics/{al}/{bu}/{st}/{spId}/{shId}/{metricTriggerType}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody ServiceResponse<String> processCalculativeMetrics(@PathVariable("spId") Long spId,
			@PathVariable("shId") Long shId,
			@PathVariable("al") Long alId,
			@PathVariable("bu") Long buId,
			@PathVariable("st") Long stId,
			@PathVariable("metricTriggerType") MetricTriggerType type,
			HttpServletRequest request){
		ServiceStatus serviceStatus = null;
		ServiceResponse serviceResponse = new ServiceResponse();
		ServiceResult serviceResult = new ServiceResult();
		try {
			SalesPosition salesPosition = ModelAssembler.getSalesPosition(spId, shId, alId, buId, stId);
			metricOfflineService.processCalculativeMetrics(salesPosition, type, ModelAssembler.getDefaultUserDetails());
			serviceResult.setDetail("Metrics Executed");
			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
		} catch (MetricExecutionException e) {
			serviceResult.setDetail("Exception occured "+e.getMessage());
			serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR, "500 ERROR");
		}
		serviceResponse.setStatus(serviceStatus);
		serviceResponse.setResult(serviceResult);
		LOGGER.info(" Returning REST response with Result.."+serviceResult.getDetail()+" and status "+serviceResponse.getStatus().getCode().getCode()+"-"+serviceResponse.getStatus().getMessage());
		return serviceResponse;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/metrics/checkCustomerPushMetricViolation/{al}/{bu}/{st}/{srcsp}/{srcsh}/{tarsp}/{tarsh}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody ServiceResponse<Boolean> checkCustomerPushMetricViolation(@PathVariable("al") Long alId,
			@PathVariable("bu") Long buId,
			@PathVariable("st") Long stId,
			@PathVariable("srcsp") Long srcsp,
			@PathVariable("srcsh") Long srcsh,
			@PathVariable("tarsp") Long tarsp,
			@PathVariable("tarsh") Long tarsh,
			HttpServletRequest request) throws AlignmentServiceException, MetricViolationException{
		ServiceStatus serviceStatus = null;
		ServiceResponse serviceResponse = new ServiceResponse();
		ServiceResult serviceResult = new ServiceResult();
		try{
			SalesPosition souceSalesPos = ModelAssembler.getSalesPosition(srcsp, srcsh, alId, buId, stId);
			SalesPosition targetSalesPos = ModelAssembler.getSalesPosition(tarsp, tarsh, alId, buId, stId);
			serviceResult.setDetail(metricOfflineService.checkCustomerPushMetricViolation(souceSalesPos, targetSalesPos, ModelAssembler.getDefaultUserDetails()));
			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
		} catch (AlignmentServiceException e) {
			serviceResult.setDetail("Exception occured " + e.getMessage());
			serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR,
					"500 ERROR");
		}
		catch (MetricViolationException e) {
			serviceResult.setDetail("Exception occured " + e.getMessage());
			serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR,
					"500 ERROR");
		}
		serviceResponse.setStatus(serviceStatus);
		serviceResponse.setResult(serviceResult);
		LOGGER.info(" Returning REST response with Result.."+serviceResult.getDetail()+" and status "+serviceResponse.getStatus().getCode().getCode()+"-"+serviceResponse.getStatus().getMessage());
		return serviceResponse;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/metrics/checkCustomerPullMetricViolation/{al}/{bu}/{st}/{srcsp}/{srcsh}/{tarsp}/{tarsh}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody ServiceResponse<Boolean> checkCustomerPullMetricViolation(@PathVariable("al") Long alId,
			@PathVariable("bu") Long buId,
			@PathVariable("st") Long stId,
			@PathVariable("srcsp") Long srcsp,
			@PathVariable("srcsh") Long srcsh,
			@PathVariable("tarsp") Long tarsp,
			@PathVariable("tarsh") Long tarsh,
			HttpServletRequest request) throws AlignmentServiceException, MetricViolationException{
		ServiceStatus serviceStatus = null;
		ServiceResponse serviceResponse = new ServiceResponse();
		ServiceResult serviceResult = new ServiceResult();
		try{
			SalesPosition souceSalesPos = ModelAssembler.getSalesPosition(srcsp, srcsh, alId, buId, stId);
			SalesPosition targetSalesPos = ModelAssembler.getSalesPosition(tarsp, tarsh, alId, buId, stId);
			serviceResult.setDetail(metricOfflineService.checkCustomerPullMetricViolation(souceSalesPos, targetSalesPos, ModelAssembler.getDefaultUserDetails()));
			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
		} catch (AlignmentServiceException e) {
			serviceResult.setDetail("Exception occured " + e.getMessage());
			serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR,
					"500 ERROR");
		}
		catch (MetricViolationException e) {
			serviceResult.setDetail("Exception occured " + e.getMessage());
			serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR,
					"500 ERROR");
		}
		serviceResponse.setStatus(serviceStatus);
		serviceResponse.setResult(serviceResult);
		LOGGER.info(" Returning REST response with Result.."+serviceResult.getDetail()+" and status "+serviceResponse.getStatus().getCode().getCode()+"-"+serviceResponse.getStatus().getMessage());
		return serviceResponse;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/metrics/checkCustomerEditMetricViolation/{al}/{bu}/{st}/{tarsp}/{tarsh}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody ServiceResponse<Boolean> checkCustomerEditMetricViolation(@PathVariable("al") Long alId,
			@PathVariable("bu") Long buId,
			@PathVariable("st") Long stId,
			@PathVariable("tarsp") Long tarsp,
			@PathVariable("tarsh") Long tarsh,
			HttpServletRequest request) throws AlignmentServiceException, MetricViolationException{
		ServiceStatus serviceStatus = null;
		ServiceResponse serviceResponse = new ServiceResponse();
		ServiceResult serviceResult = new ServiceResult();
		try{
			SalesPosition targetSalesPos = ModelAssembler.getSalesPosition(tarsp, tarsh, alId, buId, stId);
			serviceResult.setDetail(metricOfflineService.checkCustomerEditMetricViolation(targetSalesPos, ModelAssembler.getDefaultUserDetails()));
			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
		} catch (AlignmentServiceException e) {
			serviceResult.setDetail("Exception occured " + e.getMessage());
			serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR,
					"500 ERROR");
		}
		catch (MetricViolationException e) {
			serviceResult.setDetail("Exception occured " + e.getMessage());
			serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR,
					"500 ERROR");
		}
		serviceResponse.setStatus(serviceStatus);
		serviceResponse.setResult(serviceResult);
		LOGGER.info(" Returning REST response with Result.."+serviceResult.getDetail()+" and status "+serviceResponse.getStatus().getCode().getCode()+"-"+serviceResponse.getStatus().getMessage());
		return serviceResponse;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/metrics/checkZipMovementMetricViolation/{al}/{bu}/{st}/{srcsp}/{srcsh}/{tarsp}/{tarsh}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody ServiceResponse<Boolean> checkZipMovementMetricViolation(@PathVariable("al") Long alId,
			@PathVariable("bu") Long buId,
			@PathVariable("st") Long stId,
			@PathVariable("srcsp") Long srcsp,
			@PathVariable("srcsh") Long srcsh,
			@PathVariable("tarsp") Long tarsp,
			@PathVariable("tarsh") Long tarsh,
			HttpServletRequest request) throws AlignmentServiceException, MetricViolationException{
		ServiceStatus serviceStatus = null;
		ServiceResponse serviceResponse = new ServiceResponse();
		ServiceResult serviceResult = new ServiceResult();
		try{
			SalesPosition souceSalesPos = ModelAssembler.getSalesPosition(srcsp, srcsh, alId, buId, stId);
			SalesPosition targetSalesPos = ModelAssembler.getSalesPosition(tarsp, tarsh, alId, buId, stId);
			serviceResult.setDetail(metricOfflineService.checkZipMovementMetricViolation(souceSalesPos, targetSalesPos, ModelAssembler.getDefaultUserDetails()));
			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
		} catch (AlignmentServiceException e) {
			serviceResult.setDetail("Exception occured " + e.getMessage());
			serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR,
					"500 ERROR");
		}
		catch (MetricViolationException e) {
			serviceResult.setDetail("Exception occured " + e.getMessage());
			serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR,
					"500 ERROR");
		}
		serviceResponse.setStatus(serviceStatus);
		serviceResponse.setResult(serviceResult);
		LOGGER.info(" Returning REST response with Result.."+serviceResult.getDetail()+" and status "+serviceResponse.getStatus().getCode().getCode()+"-"+serviceResponse.getStatus().getMessage());
		return serviceResponse;
	}
	
}
