package com.cognizant.opserv.sp.rest.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cognizant.opserv.sp.common.util.DateUtil;
import com.cognizant.opserv.sp.exception.SalesOrgServiceException;
import com.cognizant.opserv.sp.model.SalesOrgRole;
import com.cognizant.opserv.sp.rest.util.ModelAssembler;
import com.cognizant.opserv.sp.service.salesorg.SalesOrgRoleSetupService;
import com.cognizant.peg.core.common.SearchCriteria;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.rest.common.StatusCode;
import com.cognizant.peg.web.common.ServiceResponse;
import com.cognizant.peg.web.common.ServiceResult;
import com.cognizant.peg.web.common.ServiceStatus;

@Controller
public class SalesOrgRoleRestController {
	
	/*
	 * Instantiate the Logger
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(SalesOrgRoleRestController.class);
	
	@Autowired
	private SalesOrgRoleSetupService salesOrgRoleSetupService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/salesRole/createRole/{name}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ServiceResponse<SalesOrgRole> createRole(@PathVariable("name") String name, HttpServletRequest request) throws SalesOrgServiceException {

		ServiceStatus serviceStatus = null;
		ServiceResponse<SalesOrgRole> serviceResponse = new ServiceResponse<SalesOrgRole>();
		@SuppressWarnings("rawtypes")
		ServiceResult serviceResult = new ServiceResult();
		
		SalesOrgRole role = new SalesOrgRole();
		role.setName(name);
		role.setCreatedBy(80);
		role.setCreatedDate(DateUtil.getCurrentDate());
		role.setActive(true);

		try {
			serviceResult.setDetail(salesOrgRoleSetupService.createSalesOrgRole(role, ModelAssembler.getDefaultUserDetails()));
			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
		} catch (SalesOrgServiceException e) {
			serviceResult.setDetail("Exception occured " + e.getMessage());
			serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR, "500 ERROR");
		}
		serviceResponse.setStatus(serviceStatus);
		serviceResponse.setResult(serviceResult);
		LOGGER.info(" Returning REST response with Result.." + serviceResult.getDetail() + " and status " + serviceResponse.getStatus().getCode().getCode() + "-"
				+ serviceResponse.getStatus().getMessage());
		return serviceResponse;
	}
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/salesRole/updateRole/{id}/{name}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ServiceResponse<Boolean> updateRole(@PathVariable("id") Long id, @PathVariable("name") String name, HttpServletRequest request) 
			throws SalesOrgServiceException {

		ServiceStatus serviceStatus = null;
		ServiceResponse<Boolean> serviceResponse = new ServiceResponse<Boolean>();
		@SuppressWarnings("rawtypes")
		ServiceResult serviceResult = new ServiceResult();
		
		SalesOrgRole role = new SalesOrgRole();
		role.setId(id);
		role.setName(name);
		role.setUpdatedBy(80);
		
		try {
			salesOrgRoleSetupService.updateSalesOrgRole(role, ModelAssembler.getDefaultUserDetails());
			serviceResult.setDetail(true);
			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
		} catch (SalesOrgServiceException e) {
			serviceResult.setDetail("Exception occured " + e.getMessage());
			serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR, "500 ERROR");
		}
		serviceResponse.setStatus(serviceStatus);
		serviceResponse.setResult(serviceResult);
		LOGGER.info(" Returning REST response with Result.." + serviceResult.getDetail() + " and status " + serviceResponse.getStatus().getCode().getCode() + "-"
				+ serviceResponse.getStatus().getMessage());
		return serviceResponse;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/salesRole/inactiveRole/{id}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ServiceResponse<Boolean> inactiveRole(@PathVariable("id") Long id, HttpServletRequest request) 
			throws SalesOrgServiceException {

		ServiceStatus serviceStatus = null;
		ServiceResponse<Boolean> serviceResponse = new ServiceResponse<Boolean>();
		@SuppressWarnings("rawtypes")
		ServiceResult serviceResult = new ServiceResult();
		
		try {
			salesOrgRoleSetupService.inactiveSalesOrgRole(id, ModelAssembler.getDefaultUserDetails());
			serviceResult.setDetail(true);
			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
		} catch (SalesOrgServiceException e) {
			serviceResult.setDetail("Exception occured " + e.getMessage());
			serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR, "500 ERROR");
		}
		serviceResponse.setStatus(serviceStatus);
		serviceResponse.setResult(serviceResult);
		LOGGER.info(" Returning REST response with Result.." + serviceResult.getDetail() + " and status " + serviceResponse.getStatus().getCode().getCode() + "-"
				+ serviceResponse.getStatus().getMessage());
		return serviceResponse;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/salesRole/getRole/{id}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ServiceResponse<SalesOrgRole> getRole(@PathVariable("id") Long id, HttpServletRequest request) 
			throws SalesOrgServiceException {

		ServiceStatus serviceStatus = null;
		ServiceResponse<SalesOrgRole> serviceResponse = new ServiceResponse<SalesOrgRole>();
		@SuppressWarnings("rawtypes")
		ServiceResult serviceResult = new ServiceResult();

		try {
			serviceResult.setDetail(salesOrgRoleSetupService.getSalesOrgRoleDetails(id, ModelAssembler.getDefaultUserDetails()));
			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
		} catch (SalesOrgServiceException e) {
			serviceResult.setDetail("Exception occured " + e.getMessage());
			serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR, "500 ERROR");
		}
		serviceResponse.setStatus(serviceStatus);
		serviceResponse.setResult(serviceResult);
		LOGGER.info(" Returning REST response with Result.." + serviceResult.getDetail() + " and status " + serviceResponse.getStatus().getCode().getCode() + "-"
				+ serviceResponse.getStatus().getMessage());
		return serviceResponse;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/salesRole/getAllRoles/{nameLike}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ServiceResponse<List<SalesOrgRole>> getAllRoles(@PathVariable("nameLike") String nameLike, HttpServletRequest request) 
			throws SalesOrgServiceException {

		ServiceStatus serviceStatus = null;
		ServiceResponse<List<SalesOrgRole>> serviceResponse = new ServiceResponse<List<SalesOrgRole>>();
		@SuppressWarnings("rawtypes")
		ServiceResult serviceResult = new ServiceResult();
		
		SearchCriteria sc = new SearchCriteria();
		if(!nameLike.trim().isEmpty()) {
			nameLike = "%" + nameLike + "%";
			sc.addLike("roleName", nameLike);
		}
		
		try {
			serviceResult.setList(salesOrgRoleSetupService.fetchSalesOrgRoles(sc, ModelAssembler.getDefaultUserDetails()));
			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
		} catch (SalesOrgServiceException e) {
			serviceResult.setDetail("Exception occured " + e.getMessage());
			serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR, "500 ERROR");
		}
		serviceResponse.setStatus(serviceStatus);
		serviceResponse.setResult(serviceResult);
		LOGGER.info(" Returning REST response with Result.." + serviceResult.getDetail() + " and status " + serviceResponse.getStatus().getCode().getCode() + "-"
				+ serviceResponse.getStatus().getMessage());
		return serviceResponse;
	}

}
