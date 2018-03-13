package com.cognizant.opserv.sp.rest.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cognizant.opserv.sp.common.MetricCategoryType;
import com.cognizant.opserv.sp.common.util.DateUtil;
import com.cognizant.opserv.sp.exception.MetricServiceException;
import com.cognizant.opserv.sp.model.Alignment;
import com.cognizant.opserv.sp.model.SalesOrgHierarchy;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.model.metric.Metric;
import com.cognizant.opserv.sp.model.metric.MetricConfig;
import com.cognizant.opserv.sp.model.metric.MetricExecutionTrigger;
import com.cognizant.opserv.sp.rest.util.ModelAssembler;
import com.cognizant.opserv.sp.service.metric.MetricSetupService;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.rest.common.StatusCode;
import com.cognizant.peg.web.common.ServiceResponse;
import com.cognizant.peg.web.common.ServiceResult;
import com.cognizant.peg.web.common.ServiceStatus;
/**
 * The Class MetricSetupRestController.
 */
@Controller
public class MetricSetupRestController {
	
	/** The Constant LOGGER. */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(MetricSetupRestController.class);

	/** The sales position service. */
	@Autowired
	MetricSetupService metricSetupService;
	
	/**
	 * Creating a new Metric.
	 *
	 * @param spId the sp id
	 * @param request the request
	 * @return the created Metrics
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/createMetrics/{metName}/{al}/{bu}/{st}/{catgry}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody ServiceResponse<Metric> createMetric(@PathVariable("metName") String metricName,
			@PathVariable("al") Long alId, @PathVariable("bu") Long buId,
			@PathVariable("st") Long stId,@PathVariable("catgry") MetricCategoryType category,
			HttpServletRequest request) {

		ServiceStatus serviceStatus = null;
		ServiceResponse serviceResponse = new ServiceResponse();
		ServiceResult serviceResult = new ServiceResult();

		try {
			Metric metric=new Metric();
			metric.setName(metricName);
			Alignment alignment = ModelAssembler.getAlignmentModel(alId, buId,
					stId);
			metric.setAlignment(alignment);
			metric.setCategory(category);
			metric.setCreatedBy(ModelAssembler.getDefaultUserDetails().getTenantId().intValue());
			metric.setTenantId(ModelAssembler.getDefaultUserDetails().getTenantId());
			metric.setCreatedDate(DateUtil.getCurrentDate());
			metric.setValmessage(" exceeds range");
			serviceResult.setDetail(metricSetupService.createMetric(metric, ModelAssembler.getDefaultUserDetails()));
			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
		} catch (MetricServiceException e) {
			serviceResult.setDetail("Exception occured "+e.getMessage());
			serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR, "500 ERROR");
		}
		serviceResponse.setStatus(serviceStatus);
		serviceResponse.setResult(serviceResult);
		LOGGER.info(" Returning REST response with Result.."+serviceResult.getDetail()+" and status "+serviceResponse.getStatus().getCode().getCode()+"-"+serviceResponse.getStatus().getMessage());
		return serviceResponse;
	}	
	
	
	/**
	 * Updating a  Metric.
	 *
	 * @param spId the sp id
	 * @param request the request
	 * @return the updated Metrics
	 */
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/updateMetric/{metId}/{metName}/{al}/{bu}/{st}/{catgry}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody ServiceResponse<String> updateMetric(@PathVariable("metId") Long metricId,@PathVariable("metName") String metricName,
			@PathVariable("al") Long alId, @PathVariable("bu") Long buId,
			@PathVariable("st") Long stId,@PathVariable("catgry") MetricCategoryType category,
			HttpServletRequest request) {

		ServiceStatus serviceStatus = null;
		
		@SuppressWarnings("rawtypes")
		ServiceResponse serviceResponse = new ServiceResponse();
		
		@SuppressWarnings("rawtypes")
		ServiceResult serviceResult = new ServiceResult();

		try {
			Metric metric=new Metric();
			metric.setId(metricId);
			metric.setName(metricName);
			Alignment alignment = ModelAssembler.getAlignmentModel(alId, buId,
					stId);
			metric.setAlignment(alignment);
			metric.setCategory(category);
			metric.setCreatedBy(ModelAssembler.getDefaultUserDetails().getTenantId().intValue());
			metric.setTenantId(ModelAssembler.getDefaultUserDetails().getTenantId());
			metric.setCreatedDate(DateUtil.getCurrentDate());
			
			metricSetupService.updateMetric(metric, ModelAssembler.getDefaultUserDetails());
			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
			serviceResult.setDetail(" Metrics Updated  successfully");
		} catch (MetricServiceException e) {
			serviceResult.setDetail("Exception occured "+e.getMessage());
			serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR, "500 ERROR");
		}
		serviceResponse.setStatus(serviceStatus);
		serviceResponse.setResult(serviceResult);
		LOGGER.info(" Returning REST response with Result.."+serviceResult.getDetail()+" and status "+serviceResponse.getStatus().getCode().getCode()+"-"+serviceResponse.getStatus().getMessage());
		return serviceResponse;
	}	
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/getMetricsByAlignment/{al}/{bu}/{st}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody ServiceResponse<List<Metric>> getMetricsByAlignment(
			@PathVariable("al") Long alId, @PathVariable("bu") Long buId,
			@PathVariable("st") Long stId,
			HttpServletRequest request) {

		ServiceStatus serviceStatus = null;
		ServiceResponse serviceResponse = new ServiceResponse();
		ServiceResult serviceResult = new ServiceResult();

		try {
			
			Alignment alignment = ModelAssembler.getAlignmentModel(alId, buId,
					stId);
			
			PaginationInfo paginInfo = new PaginationInfo(0, 1);
			serviceResult.setList(metricSetupService.fetchMetricsByAlignment(alignment, paginInfo, ModelAssembler.getDefaultUserDetails()));
			
			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
		} catch (MetricServiceException e) {
			serviceResult.setDetail("Exception occured "+e.getMessage());
			serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR, "500 ERROR");
		}
		serviceResponse.setStatus(serviceStatus);
		serviceResponse.setResult(serviceResult);
		LOGGER.info(" Returning REST response with Result.."+serviceResult.getDetail()+" and status "+serviceResponse.getStatus().getCode().getCode()+"-"+serviceResponse.getStatus().getMessage());
		return serviceResponse;
	}	
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/configureMetric/{metId}/{shId}/{al}/{bu}/{st}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody ServiceResponse<String> configureMetric(@PathVariable("metId") Long metricId,
			@PathVariable("shId") Long saleshierId,
			@PathVariable("al") Long alId, @PathVariable("bu") Long buId,
			@PathVariable("st") Long stId,
			HttpServletRequest request) {

		ServiceStatus serviceStatus = null;
		ServiceResponse serviceResponse = new ServiceResponse();
		ServiceResult serviceResult = new ServiceResult();

		try {
			
			Alignment alignment = ModelAssembler.getAlignmentModel(alId, buId,
					stId);
			Metric metric =new Metric();
			metric.setId(metricId);
			metric.setAlignment(alignment);
			
			MetricConfig mConfig=new  MetricConfig();
			mConfig.setMetric(metric);
			SalesOrgHierarchy salesOrgHierarchy =new SalesOrgHierarchy();
			salesOrgHierarchy.setId(saleshierId);
			mConfig.setHierarchy(salesOrgHierarchy);
			metricSetupService.configureMetric(mConfig, ModelAssembler.getUserDetails());
			serviceResult.setDetail("Metrics Configured successfully");
			
			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
		} catch (MetricServiceException e) {
			serviceResult.setDetail("Exception occured "+e.getMessage());
			serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR, "500 ERROR");
		}
		serviceResponse.setStatus(serviceStatus);
		serviceResponse.setResult(serviceResult);
		LOGGER.info(" Returning REST response with Result.."+serviceResult.getDetail()+" and status "+serviceResponse.getStatus().getCode().getCode()+"-"+serviceResponse.getStatus().getMessage());
		return serviceResponse;
	}	
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/configureMetricExecutionTrigger/{metId}/{shId}/{triggerId}/{al}/{bu}/{st}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody ServiceResponse<String> configureMetricExecutionTrigger(@PathVariable("metId") Long metricId, @PathVariable("shId") Long saleshierId,
			@PathVariable("triggerId") Long triggerId,@PathVariable("al") Long alId, @PathVariable("bu") Long buId,
			@PathVariable("st") Long stId,
			HttpServletRequest request) {

		ServiceStatus serviceStatus = null;
		ServiceResponse serviceResponse = new ServiceResponse();
		ServiceResult serviceResult = new ServiceResult();

		try {
			
			Alignment alignment = ModelAssembler.getAlignmentModel(alId, buId,
					stId);
			Metric metric =new Metric();
			metric.setId(metricId);
			metric.setAlignment(alignment);
			
			MetricConfig mConfig=new  MetricConfig();
			mConfig.setMetric(metric);
			SalesOrgHierarchy salesOrgHierarchy =new SalesOrgHierarchy();
			salesOrgHierarchy.setId(saleshierId);
			mConfig.setHierarchy(salesOrgHierarchy);
			
			MetricExecutionTrigger mTrigger=new MetricExecutionTrigger();
			mTrigger.setId(triggerId);
			
			mTrigger.setMetricConfig(mConfig);
			UserDetails userDetails = ModelAssembler.getUserDetails();
			userDetails.setLocaleId("en_US");
			userDetails.setUserId(1);
			userDetails.setTenantId((short)1);
			userDetails.setStaffId(1);
			metricSetupService.configureMetricExecutionTrigger(mTrigger,userDetails); 
			serviceResult.setDetail("MetricExecution trigger Configured successfully");
			
			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
		} catch (MetricServiceException e) {
			serviceResult.setDetail("Exception occured "+e.getMessage());
			serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR, "500 ERROR");
		}
		serviceResponse.setStatus(serviceStatus);
		serviceResponse.setResult(serviceResult);
		LOGGER.info(" Returning REST response with Result.."+serviceResult.getDetail()+" and status "+serviceResponse.getStatus().getCode().getCode()+"-"+serviceResponse.getStatus().getMessage());
		return serviceResponse;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/configureMetricExecutionTriggerList/{metId}/{shId}/{triggerIds}/{al}/{bu}/{st}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody ServiceResponse<String> configureMetricExecutionTriggerList(@PathVariable("metId") Long metricId, @PathVariable("shId") Long saleshierId,
			@PathVariable("triggerIds") List<Integer> triggerIds,@PathVariable("al") Long alId, @PathVariable("bu") Long buId,
			@PathVariable("st") Long stId,
			HttpServletRequest request) {

		ServiceStatus serviceStatus = null;
		ServiceResponse serviceResponse = new ServiceResponse();
		ServiceResult serviceResult = new ServiceResult();

		try {
			
			Alignment alignment = ModelAssembler.getAlignmentModel(alId, buId,
					stId);
			Metric metric =new Metric();
			metric.setId(metricId);
			metric.setAlignment(alignment);
			
			MetricConfig mConfig=new  MetricConfig();
			mConfig.setMetric(metric);
			SalesOrgHierarchy salesOrgHierarchy =new SalesOrgHierarchy();
			salesOrgHierarchy.setId(saleshierId);
			mConfig.setHierarchy(salesOrgHierarchy);
			MetricExecutionTrigger mTrigger=new MetricExecutionTrigger();
			List<MetricExecutionTrigger> mTriggerList=new ArrayList<MetricExecutionTrigger>();
			for(Integer triggerId: triggerIds){
				MetricExecutionTrigger mTrigger1=new MetricExecutionTrigger();
				mTrigger1.setId(triggerId.longValue());;
				mTriggerList.add(mTrigger1);
			}
			mTrigger.setMetricConfig(mConfig);
			mTriggerList.add(mTrigger);
			metricSetupService.configureMetricExecutionTrigger(mTriggerList, ModelAssembler.getDefaultUserDetails()); 
			serviceResult.setDetail("MetricExecution trigger List Configured successfully");
			
			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
		} catch (MetricServiceException e) {
			serviceResult.setDetail("Exception occured "+e.getMessage());
			serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR, "500 ERROR");
		}
		serviceResponse.setStatus(serviceStatus);
		serviceResponse.setResult(serviceResult);
		LOGGER.info(" Returning REST response with Result.."+serviceResult.getDetail()+" and status "+serviceResponse.getStatus().getCode().getCode()+"-"+serviceResponse.getStatus().getMessage());
		return serviceResponse;
	}
}
