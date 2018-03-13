/*package com.cognizant.opserv.sp.rest.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cognizant.opserv.sp.exception.SalesPositionServiceException;
import com.cognizant.opserv.sp.model.CustomerAlignment;
import com.cognizant.opserv.sp.model.CustomerCallPlan;
import com.cognizant.opserv.sp.model.CustomerProductAlignment;
import com.cognizant.opserv.sp.model.EmployeeAlignment;
import com.cognizant.opserv.sp.model.GeoCustomerAlignment;
import com.cognizant.opserv.sp.model.ProductAlignment;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.model.SalesPositionAssignments;
import com.cognizant.opserv.sp.rest.util.ModelAssembler;
import com.cognizant.opserv.sp.service.store.SPTargetedAssignmentsService;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.rest.common.StatusCode;
import com.cognizant.peg.web.common.ServiceResponse;
import com.cognizant.peg.web.common.ServiceResult;
import com.cognizant.peg.web.common.ServiceStatus;


@Controller
public class SPTargetedAssignmentRestController {
	
	
	 * Instantiate the Logger
	 	
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(SPTargetedAssignmentRestController.class);
	
	@Autowired
	private SPTargetedAssignmentsService spTargetedAssignmentsService;
	
	@RequestMapping(value="/targetedCustomerAlignment/{spId}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody ServiceResponse<List<CustomerAlignment>> getTargetedCustomerAlignments(@PathVariable("spId") Long spId, HttpServletRequest request) {
		
		ServiceStatus serviceStatus = null;
		ServiceResponse serviceResponse = new ServiceResponse();
		ServiceResult serviceResult = new ServiceResult();
		
		SalesPosition salesPosition = new SalesPosition();
		salesPosition.setId(spId);

		try {
			serviceResult.setList(spTargetedAssignmentsService.getTargetedCustomerAlignments(salesPosition, ModelAssembler.getDefaultUserDetails()));
			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
		} catch (SalesPositionServiceException e) {
			serviceResult.setDetail("Exception occured "+e.getMessage());
			serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR, "500 ERROR");
		}
		serviceResponse.setStatus(serviceStatus);
		serviceResponse.setResult(serviceResult);
		LOGGER.info(" Returning REST response with Result.."+serviceResult.getDetail()+" and status "+serviceResponse.getStatus().getCode().getCode()+"-"+serviceResponse.getStatus().getMessage());
		return serviceResponse;
		
	}
	
	@RequestMapping(value="/productAlignment/{spId}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody ServiceResponse<List<ProductAlignment>> getTargetedProductAlignments(@PathVariable("spId") Long spId, HttpServletRequest request) {
		
		ServiceStatus serviceStatus = null;
		ServiceResponse serviceResponse = new ServiceResponse();
		ServiceResult serviceResult = new ServiceResult();
		
		SalesPosition salesPosition = new SalesPosition();
		salesPosition.setId(spId);

		try {
			serviceResult.setList(spTargetedAssignmentsService.getTargetedProductAlignments(salesPosition, ModelAssembler.getDefaultUserDetails()));
			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
		} catch (SalesPositionServiceException e) {
			serviceResult.setDetail("Exception occured "+e.getMessage());
			serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR, "500 ERROR");
		}
		serviceResponse.setStatus(serviceStatus);
		serviceResponse.setResult(serviceResult);
		LOGGER.info(" Returning REST response with Result.."+serviceResult.getDetail()+" and status "+serviceResponse.getStatus().getCode().getCode()+"-"+serviceResponse.getStatus().getMessage());
		return serviceResponse;
		
	}
	
	@RequestMapping(value="/employeeAlignment/{spId}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody ServiceResponse<List<EmployeeAlignment>> getTargetedEmployeeAlignments(@PathVariable("spId") Long spId, HttpServletRequest request) {
		
		ServiceStatus serviceStatus = null;
		ServiceResponse serviceResponse = new ServiceResponse();
		ServiceResult serviceResult = new ServiceResult();
		
		SalesPosition salesPosition = new SalesPosition();
		salesPosition.setId(spId);

		try {
			serviceResult.setList(spTargetedAssignmentsService.getTargetedEmployeeAlignments(salesPosition, ModelAssembler.getDefaultUserDetails()));
			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
		} catch (SalesPositionServiceException e) {
			serviceResult.setDetail("Exception occured "+e.getMessage());
			serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR, "500 ERROR");
		}
		serviceResponse.setStatus(serviceStatus);
		serviceResponse.setResult(serviceResult);
		LOGGER.info(" Returning REST response with Result.."+serviceResult.getDetail()+" and status "+serviceResponse.getStatus().getCode().getCode()+"-"+serviceResponse.getStatus().getMessage());
		return serviceResponse;
		
	}
	
	@RequestMapping(value="/custProdAlignment/{spId}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody ServiceResponse<List<CustomerProductAlignment>> getTargetedCustProdAlignments(@PathVariable("spId") Long spId, HttpServletRequest request) {
		
		ServiceStatus serviceStatus = null;
		ServiceResponse serviceResponse = new ServiceResponse();
		ServiceResult serviceResult = new ServiceResult();
		
		SalesPosition salesPosition = new SalesPosition();
		salesPosition.setId(spId);

		try {
			serviceResult.setList(spTargetedAssignmentsService.getTargetedCustProdAlignments(salesPosition, ModelAssembler.getDefaultUserDetails()));
			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
		} catch (SalesPositionServiceException e) {
			serviceResult.setDetail("Exception occured "+e.getMessage());
			serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR, "500 ERROR");
		}
		serviceResponse.setStatus(serviceStatus);
		serviceResponse.setResult(serviceResult);
		LOGGER.info(" Returning REST response with Result.."+serviceResult.getDetail()+" and status "+serviceResponse.getStatus().getCode().getCode()+"-"+serviceResponse.getStatus().getMessage());
		return serviceResponse;
		
	}
	
	@RequestMapping(value="/geoAlignment/{spId}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody ServiceResponse<List<GeoCustomerAlignment>> getTargetedGeoCustomerAlignments(@PathVariable("spId") Long spId, HttpServletRequest request) {
		
		ServiceStatus serviceStatus = null;
		ServiceResponse serviceResponse = new ServiceResponse();
		ServiceResult serviceResult = new ServiceResult();
		
		SalesPosition salesPosition = new SalesPosition();
		salesPosition.setId(spId);

		try {
			serviceResult.setList(spTargetedAssignmentsService.getTargetedGeoCustomerAlignments(salesPosition, ModelAssembler.getDefaultUserDetails()));
			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
		} catch (SalesPositionServiceException e) {
			serviceResult.setDetail("Exception occured "+e.getMessage());
			serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR, "500 ERROR");
		}
		serviceResponse.setStatus(serviceStatus);
		serviceResponse.setResult(serviceResult);
		LOGGER.info(" Returning REST response with Result.."+serviceResult.getDetail()+" and status "+serviceResponse.getStatus().getCode().getCode()+"-"+serviceResponse.getStatus().getMessage());
		return serviceResponse;
		
	}
	
	@RequestMapping(value="/callPlan/{spId}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody ServiceResponse<List<CustomerCallPlan>> getTargetedCallPlans(@PathVariable("spId") Long spId, HttpServletRequest request) {
		
		ServiceStatus serviceStatus = null;
		ServiceResponse serviceResponse = new ServiceResponse();
		ServiceResult serviceResult = new ServiceResult();
		
		SalesPosition salesPosition = new SalesPosition();
		salesPosition.setId(spId);

		try {
			serviceResult.setList(spTargetedAssignmentsService.getTargetedCallPlans(salesPosition, ModelAssembler.getDefaultUserDetails()));
			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
		} catch (SalesPositionServiceException e) {
			serviceResult.setDetail("Exception occured "+e.getMessage());
			serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR, "500 ERROR");
		}
		serviceResponse.setStatus(serviceStatus);
		serviceResponse.setResult(serviceResult);
		LOGGER.info(" Returning REST response with Result.."+serviceResult.getDetail()+" and status "+serviceResponse.getStatus().getCode().getCode()+"-"+serviceResponse.getStatus().getMessage());
		return serviceResponse;
		
	}
	
	@RequestMapping(value="/nonTargetedCustomerAlignment/{spId}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody ServiceResponse<List<CustomerAlignment>> getNonTargetedCustAlignments(@PathVariable("spId") Long spId, HttpServletRequest request) {
		
		ServiceStatus serviceStatus = null;
		ServiceResponse serviceResponse = new ServiceResponse();
		ServiceResult serviceResult = new ServiceResult();
		
		SalesPosition salesPosition = new SalesPosition();
		salesPosition.setId(spId);

		try {
			serviceResult.setList(spTargetedAssignmentsService.getNonTargetedCustAlignments(salesPosition, ModelAssembler.getDefaultUserDetails()));
			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
		} catch (SalesPositionServiceException e) {
			serviceResult.setDetail("Exception occured "+e.getMessage());
			serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR, "500 ERROR");
		}
		serviceResponse.setStatus(serviceStatus);
		serviceResponse.setResult(serviceResult);
		LOGGER.info(" Returning REST response with Result.."+serviceResult.getDetail()+" and status "+serviceResponse.getStatus().getCode().getCode()+"-"+serviceResponse.getStatus().getMessage());
		return serviceResponse;
		
	}
	
	@RequestMapping(value="/sPTargetedAssignments/{spId}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody ServiceResponse<List<SalesPositionAssignments>> getSPTargetedAssignments(@PathVariable("spId") Long spId, HttpServletRequest request) {
		
		ServiceStatus serviceStatus = null;
		ServiceResponse serviceResponse = new ServiceResponse();
		ServiceResult serviceResult = new ServiceResult();
		
		SalesPosition salesPosition = new SalesPosition();
		salesPosition.setId(spId);

		try {
			serviceResult.setDetail(spTargetedAssignmentsService.getSPTargetedAssignments(salesPosition, ModelAssembler.getDefaultUserDetails()));
			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
		} catch (SalesPositionServiceException e) {
			serviceResult.setDetail("Exception occured "+e.getMessage());
			serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR, "500 ERROR");
		}
		serviceResponse.setStatus(serviceStatus);
		serviceResponse.setResult(serviceResult);
		LOGGER.info(" Returning REST response with Result.."+serviceResult.getDetail()+" and status "+serviceResponse.getStatus().getCode().getCode()+"-"+serviceResponse.getStatus().getMessage());
		return serviceResponse;
		
	}
	
	@RequestMapping(value="/nonTargetedCustProdAlignments/{spId}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody ServiceResponse<List<CustomerProductAlignment>>getNonTargetedCustProdAlignments(@PathVariable("spId") Long spId, HttpServletRequest request) {
		
		ServiceStatus serviceStatus = null;
		ServiceResponse serviceResponse = new ServiceResponse();
		ServiceResult serviceResult = new ServiceResult();
		
		SalesPosition salesPosition = new SalesPosition();
		salesPosition.setId(spId);

		try {
			serviceResult.setList(spTargetedAssignmentsService.getNonTargetedCustProdAlignments(salesPosition, ModelAssembler.getDefaultUserDetails()));
			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
		} catch (SalesPositionServiceException e) {
			serviceResult.setDetail("Exception occured "+e.getMessage());
			serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR, "500 ERROR");
		}
		serviceResponse.setStatus(serviceStatus);
		serviceResponse.setResult(serviceResult);
		LOGGER.info(" Returning REST response with Result.."+serviceResult.getDetail()+" and status "+serviceResponse.getStatus().getCode().getCode()+"-"+serviceResponse.getStatus().getMessage());
		return serviceResponse;
		
	}
	
}
*/