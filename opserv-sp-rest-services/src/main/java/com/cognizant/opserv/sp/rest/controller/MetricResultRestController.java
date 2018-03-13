package com.cognizant.opserv.sp.rest.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cognizant.opserv.sp.common.MetricTriggerType;
import com.cognizant.opserv.sp.exception.AlignmentServiceException;
import com.cognizant.opserv.sp.exception.MetricServiceException;
import com.cognizant.opserv.sp.model.Alignment;
import com.cognizant.opserv.sp.model.PostalCode;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.model.metric.MetricExecutionTrigger;
import com.cognizant.opserv.sp.model.metric.MetricResult;
import com.cognizant.opserv.sp.rest.util.ModelAssembler;
import com.cognizant.opserv.sp.service.metric.MetricResultService;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.rest.common.StatusCode;
import com.cognizant.peg.web.common.ServiceResponse;
import com.cognizant.peg.web.common.ServiceResult;
import com.cognizant.peg.web.common.ServiceStatus;

@Controller
public class MetricResultRestController {

	/*
	 * Instantiate the Logger
	 */	
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(CustomerAlignmentRestController.class);
	
	/**
	 * MetricResultService has the fetch metrics results.
	 */
	@Autowired
	private MetricResultService metricResultService;
	
	/**
	 * Gets the all metric results.
	 * @method getAllMetricResults
	 * @param salesPosition the sales position
	 * @param userDetails the user details
	 * @return the all metric results
	 * @throws AlignmentServiceException 
	 * @throws MetricServiceException the metric service exception
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/metric/metricResult/{spId}/{shId}/{al}/{bu}/{st}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody ServiceResponse<List<MetricResult>> getMetricResultsWithoutTriggerTypes(@PathVariable("spId") Long spId,
			@PathVariable("shId") Long shId,
			@PathVariable("al") Long alId,
			@PathVariable("bu") Long buId,
			@PathVariable("st") Long stId,
			HttpServletRequest request) throws AlignmentServiceException {

		ServiceStatus serviceStatus = null;
		ServiceResponse serviceResponse = new ServiceResponse();
		ServiceResult serviceResult = new ServiceResult();

		try {
			
			SalesPosition salesPosition = ModelAssembler.getSalesPosition(spId, shId, alId, buId, stId);
					
			serviceResult.setDetail(metricResultService.getAllMetricResults(salesPosition, ModelAssembler.getDefaultUserDetails()));
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
	 * Gets the all metric results.
	 *
	 * @method getAllMetricResults
	 * @param salesPosition the sales position
	 * @param types the types
	 * @param userDetails the user details
	 * @return the all metric results
	 * @throws AlignmentServiceException 
	 * @throws MetricServiceException the metric service exception
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/metric/metricResult/{spId}/{shId}/{al}/{bu}/{stId}/{metricTriggerTypes}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody ServiceResponse<List<MetricResult>> getMetricResultsByTriggerTypes(@PathVariable("spId") Long spId,
			@PathVariable("shId") Long shId,
			@PathVariable("al") Long alId,
			@PathVariable("bu") Long buId,
			@PathVariable("stId") Long stId,
			@PathVariable("metricTriggerTypes") List<MetricTriggerType> types,
			HttpServletRequest request) throws AlignmentServiceException {

		ServiceStatus serviceStatus = null;
		ServiceResponse serviceResponse = new ServiceResponse();
		ServiceResult serviceResult = new ServiceResult();

		try {
			
			SalesPosition salesPosition = ModelAssembler.getSalesPosition(spId, shId, alId, buId, stId);
			List<MetricTriggerType> mTriggertypes = ModelAssembler.getMetricTypes(types);
					
			serviceResult.setDetail(metricResultService.getAllMetricResults(salesPosition, mTriggertypes, ModelAssembler.getDefaultUserDetails()));
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
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/metric/getAllMetricResults/{spId}/{shId}/{al}/{bu}/{stId}/{metricTriggerType}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody ServiceResponse<List<MetricResult>> getLastMetricResults(@PathVariable("spId") Long spId,
			@PathVariable("shId") Long shId,
			@PathVariable("al") Long alId,
			@PathVariable("bu") Long buId,
			@PathVariable("stId") Long stId,
			@PathVariable("metricTriggerType") MetricTriggerType type,
			HttpServletRequest request) throws AlignmentServiceException {

		ServiceStatus serviceStatus = null;
		ServiceResponse serviceResponse = new ServiceResponse();
		ServiceResult serviceResult = new ServiceResult();

		try {			
			SalesPosition salesPosition = ModelAssembler.getSalesPosition(spId, shId, alId, buId, stId);
			serviceResult.setDetail(metricResultService.getAllMetricResults(salesPosition,type, ModelAssembler.getDefaultUserDetails()));
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
	
	@RequestMapping(value="/metric/getMetricsBySP/{mtrId}/{spIds}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody ServiceResponse<MetricResult> getMetricResultsBySalesPositions(@PathVariable("mtrId") long mtrId,
			@PathVariable("spIds") List<Long> spIds,HttpServletRequest request){

		ServiceStatus serviceStatus = null;
		ServiceResponse<MetricResult> serviceResponse = new ServiceResponse<MetricResult>();
		ServiceResult<MetricResult> serviceResult = new ServiceResult<MetricResult>();

		try {
			UserDetails userDetails = ModelAssembler.getDefaultUserDetails();
			List<SalesPosition> salesPositions = new ArrayList<SalesPosition>();
			for(Long spId : spIds){
				SalesPosition salesPosition = new SalesPosition();
				salesPosition.setId(spId);
				salesPositions.add(salesPosition);
			}
			serviceResult.setList(metricResultService.getMetricResultsBySalesPositions(mtrId, salesPositions, userDetails));			
			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
		} catch (MetricServiceException e) {
			serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR, "500 ERROR");
		}
		serviceResponse.setStatus(serviceStatus);
		serviceResponse.setResult(serviceResult);
		LOGGER.info(" Returning REST response with Result.."+serviceResult.getList()+" and status "+serviceResponse.getStatus().getCode().getCode()+"-"+serviceResponse.getStatus().getMessage());
		return serviceResponse;
	}
	
	@RequestMapping(value="/metric/getMetricsByPostalCodes/{mtrId}/{postalcodes}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody ServiceResponse<Map<PostalCode,MetricResult>> getMetricResultsByPostalCodes(@PathVariable("mtrId") Long mtrId,
			@PathVariable("postalcodes") List<Long> postalcodes,HttpServletRequest request){

		ServiceStatus serviceStatus = null;
		ServiceResponse<Map<PostalCode,MetricResult>> serviceResponse = new ServiceResponse<Map<PostalCode,MetricResult>>();
		ServiceResult<Map<PostalCode,MetricResult>> serviceResult = new ServiceResult<Map<PostalCode,MetricResult>>();

		try {
			UserDetails userDetails = ModelAssembler.getDefaultUserDetails();
			List<PostalCode> postalCdList = new ArrayList<PostalCode>();
			for(Long code : postalcodes){
				PostalCode postalCode = new PostalCode();
				postalCode.setCode(code+"");
				postalCdList.add(postalCode);
			}
			serviceResult.setDetail(metricResultService.getMetricResultsByPostalCodes(mtrId, postalCdList, userDetails));
			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
		} catch (MetricServiceException e) {
			serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR, "500 ERROR");
		}
		serviceResponse.setStatus(serviceStatus);
		serviceResponse.setResult(serviceResult);
		LOGGER.info(" Returning REST response with Result.."+serviceResult.getDetail()+" and status "+serviceResponse.getStatus().getCode().getCode()+"-"+serviceResponse.getStatus().getMessage());
		return serviceResponse;
	}
	
	@RequestMapping(value="/metric/getAllMetricResultsByPostalCodes/{postalcodes}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody ServiceResponse<Map<PostalCode, List<MetricResult>>> getAllMetricResultsByPostalCodes(@PathVariable("postalcodes") List<String> postalcodes,
			HttpServletRequest request){
		ServiceStatus serviceStatus = null;
		ServiceResponse<Map<PostalCode, List<MetricResult>>> serviceResponse = new ServiceResponse<Map<PostalCode, List<MetricResult>>>();
		ServiceResult<Map<PostalCode, List<MetricResult>>> serviceResult = new ServiceResult<Map<PostalCode, List<MetricResult>>>();
		try {
			UserDetails userDetails = ModelAssembler.getDefaultUserDetails();
			List<PostalCode> postalCodes = new ArrayList<PostalCode>();
			for(String code : postalcodes){
				PostalCode postalCode = new PostalCode();
				postalCode.setCode(code);
				postalCodes.add(postalCode);
			}
			serviceResult.setDetail(metricResultService.getAllMetricResultsByPostalCodes(postalCodes, userDetails));
			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
		} catch (MetricServiceException e) {
			serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR, "500 ERROR");
		}
		serviceResponse.setStatus(serviceStatus);
		serviceResponse.setResult(serviceResult);
		LOGGER.info(" Returning REST response with Result.."+serviceResult.getDetail()+" and status "+serviceResponse.getStatus().getCode().getCode()+"-"+serviceResponse.getStatus().getMessage());
		return serviceResponse;
	}
	
	@RequestMapping(value="/metric/getAllMetricResultsByPostalCodesAndAlgmnt/{postalcodes}/{al}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody ServiceResponse<Map<PostalCode, List<MetricResult>>> getAllMetricResultsByPostalCodesAndAlgmnt(@PathVariable("postalcodes") List<String> postalcodes,
			@PathVariable("al") Long alId,HttpServletRequest request){
		ServiceStatus serviceStatus = null;
		ServiceResponse<Map<PostalCode, List<MetricResult>>> serviceResponse = new ServiceResponse<Map<PostalCode, List<MetricResult>>>();
		ServiceResult<Map<PostalCode, List<MetricResult>>> serviceResult = new ServiceResult<Map<PostalCode, List<MetricResult>>>();
		try {
			UserDetails userDetails = ModelAssembler.getDefaultUserDetails();
			List<PostalCode> postalCodes = new ArrayList<PostalCode>();
			for(String code : postalcodes){
				PostalCode postalCode = new PostalCode();
				postalCode.setCode(code);
				postalCodes.add(postalCode);
			}
			serviceResult.setDetail(metricResultService.getAllMetricResultsByPostalCodes(postalCodes,alId,userDetails));
			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
		} catch (MetricServiceException e) {
			serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR, "500 ERROR");
		}
		serviceResponse.setStatus(serviceStatus);
		serviceResponse.setResult(serviceResult);
		LOGGER.info(" Returning REST response with Result.."+serviceResult.getDetail()+" and status "+serviceResponse.getStatus().getCode().getCode()+"-"+serviceResponse.getStatus().getMessage());
		return serviceResponse;
	}
	
	@RequestMapping(value="/metric/getMetricExecutionConfig/{al}/{bu}/{stId}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody ServiceResponse<List<MetricExecutionTrigger>> getMetricExecutionConfig(@PathVariable("al") Long alId,@PathVariable("bu") Long buId,
			@PathVariable("stId") Long stId,
			HttpServletRequest request){
		ServiceStatus serviceStatus = null;
		ServiceResponse<List<MetricExecutionTrigger>> serviceResponse = new ServiceResponse<List<MetricExecutionTrigger>>();
		ServiceResult<List<MetricExecutionTrigger>> serviceResult = new ServiceResult<List<MetricExecutionTrigger>>();
		try {
			UserDetails userDetails = ModelAssembler.getDefaultUserDetails();
			Alignment alignment = ModelAssembler.getAlignmentModel(alId, buId, stId);
			
			serviceResult.setDetail(metricResultService.getMetricExecutionConfig(alignment, userDetails));
			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
		} catch (MetricServiceException e) {
			serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR, "500 ERROR");
		}
		serviceResponse.setStatus(serviceStatus);
		serviceResponse.setResult(serviceResult);
		LOGGER.info(" Returning REST response with Result.."+serviceResult.getDetail()+" and status "+serviceResponse.getStatus().getCode().getCode()+"-"+serviceResponse.getStatus().getMessage());
		return serviceResponse;
	}
	
}
