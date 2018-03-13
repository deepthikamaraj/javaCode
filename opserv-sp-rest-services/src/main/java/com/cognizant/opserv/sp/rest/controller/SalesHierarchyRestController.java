package com.cognizant.opserv.sp.rest.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cognizant.opserv.sp.exception.SalesOrgServiceException;
import com.cognizant.opserv.sp.model.Alignment;
import com.cognizant.opserv.sp.model.BusinessUnit;
import com.cognizant.opserv.sp.model.SalesOrgHierarchy;
import com.cognizant.opserv.sp.model.SalesTeam;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.rest.util.ModelAssembler;
import com.cognizant.opserv.sp.service.salesorg.SalesHierarchyService;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.rest.common.StatusCode;
import com.cognizant.peg.web.common.ServiceResponse;
import com.cognizant.peg.web.common.ServiceResult;
import com.cognizant.peg.web.common.ServiceStatus;

@Controller
public class SalesHierarchyRestController {
	
	/** The Constant LOGGER. */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(SalesPositionRestController.class);
	
	/** The sales hierarchy service. */
	@Autowired
	private SalesHierarchyService salesHierarchyService;
	
	@RequestMapping(value="/saleshierarchy/alignment/{al}/{bu}/{st}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody ServiceResponse<List<SalesOrgHierarchy>> getSalesHierarchyByAlignment(@PathVariable("al") Long alId,
			@PathVariable("bu") Long buId,
			@PathVariable("st") Long stId,
			HttpServletRequest request) {

		ServiceStatus serviceStatus = null;
		ServiceResponse serviceResponse = new ServiceResponse();
		ServiceResult serviceResult = new ServiceResult();

		try {
			
			Alignment al = ModelAssembler.getAlignmentModel(alId,buId,stId);
					
			serviceResult.setList(salesHierarchyService.getAlignmentSalesHierarchy(al, ModelAssembler.getDefaultUserDetails()));
			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
		} catch (SalesOrgServiceException e) {
			serviceResult.setDetail("Exception occured "+e.getMessage());
			serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR, "500 ERROR");
		}
		serviceResponse.setStatus(serviceStatus);
		serviceResponse.setResult(serviceResult);
		LOGGER.info(" Returning REST response with Result.."+serviceResult.getDetail()+" and status "+serviceResponse.getStatus().getCode().getCode()+"-"+serviceResponse.getStatus().getMessage());
		return serviceResponse;
	}
	
	@RequestMapping(value="/saleshierarchy/getZipAssignment/{al}/{bu}/{st}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody ServiceResponse<SalesOrgHierarchy> getZipAssignments(@PathVariable("al") Long alId,
			@PathVariable("bu") Long buId,@PathVariable("st") Long stId,HttpServletRequest request) {

		ServiceStatus serviceStatus = null;
		ServiceResponse<SalesOrgHierarchy> serviceResponse = new ServiceResponse<SalesOrgHierarchy>();
		ServiceResult<SalesOrgHierarchy> serviceResult = new ServiceResult<SalesOrgHierarchy>();

		try {
			UserDetails userDetails = ModelAssembler.getDefaultUserDetails();
			Alignment alignment = new Alignment();
			alignment.setId(alId);
			BusinessUnit businessUnit = new BusinessUnit();
			businessUnit.setId(buId);
			SalesTeam salesTeam = new SalesTeam();
			salesTeam.setId(stId);
			salesTeam.setBusinessUnit(businessUnit);
			alignment.setSalesTeam(salesTeam);
			
			serviceResult.setList(salesHierarchyService.getZipAssignmentsByAlignment(alignment, userDetails));
			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
		} catch (SalesOrgServiceException e) {
			serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR, "500 ERROR");
		}
		serviceResponse.setStatus(serviceStatus);
		serviceResponse.setResult(serviceResult);
		LOGGER.info(" Returning REST response with Result.."+serviceResult.getList()+" and status "+serviceResponse.getStatus().getCode().getCode()+"-"+serviceResponse.getStatus().getMessage());
		return serviceResponse;
	}
	
	@RequestMapping(value="/saleshierarchy/getSalesHierarchy/{shId}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody ServiceResponse<SalesOrgHierarchy> getSalesHierarchyInformation(@PathVariable("shId") long shId,
			HttpServletRequest request) {

		ServiceStatus serviceStatus = null;
		ServiceResponse<SalesOrgHierarchy> serviceResponse = new ServiceResponse<SalesOrgHierarchy>();
		ServiceResult<SalesOrgHierarchy> serviceResult = new ServiceResult<SalesOrgHierarchy>();

		try {
			UserDetails userDetails = ModelAssembler.getDefaultUserDetails();
			serviceResult.setDetail(salesHierarchyService.getSalesHierarchyInformation(shId, userDetails));
			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
		} catch (SalesOrgServiceException e) {
			serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR, "500 ERROR");
		}
		serviceResponse.setStatus(serviceStatus);
		serviceResponse.setResult(serviceResult);
		LOGGER.info(" Returning REST response with Result.."+serviceResult.getDetail()+" and status "+serviceResponse.getStatus().getCode().getCode()+"-"+serviceResponse.getStatus().getMessage());
		return serviceResponse;
	}
	
	@RequestMapping(value="/saleshierarchy/getChildHierarchy/{shId}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody ServiceResponse<SalesOrgHierarchy> getChildSalesHierarchy(@PathVariable("shId") long shId,
			HttpServletRequest request) {

		ServiceStatus serviceStatus = null;
		ServiceResponse<SalesOrgHierarchy> serviceResponse = new ServiceResponse<SalesOrgHierarchy>();
		ServiceResult<SalesOrgHierarchy> serviceResult = new ServiceResult<SalesOrgHierarchy>();

		try {
			UserDetails userDetails = ModelAssembler.getDefaultUserDetails();
			serviceResult.setList(salesHierarchyService.getChildSalesHierarchy(shId, userDetails));
			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
		} catch (SalesOrgServiceException e) {
			serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR, "500 ERROR");
		}
		serviceResponse.setStatus(serviceStatus);
		serviceResponse.setResult(serviceResult);
		LOGGER.info(" Returning REST response with Result.."+serviceResult.getList()+" and status "+serviceResponse.getStatus().getCode().getCode()+"-"+serviceResponse.getStatus().getMessage());
		return serviceResponse;
	}
}
