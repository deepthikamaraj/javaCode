package com.cognizant.opserv.sp.rest.controller;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;



import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cognizant.opserv.sp.model.EntityTemplate;
import com.cognizant.opserv.sp.model.EntityTemplateAlignment;
import com.cognizant.opserv.sp.common.EntityType;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.rest.util.ModelAssembler;
import com.cognizant.opserv.sp.service.extdattr.EntityTemplateSetupService;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.rest.common.StatusCode;
import com.cognizant.peg.web.common.ServiceResponse;
import com.cognizant.peg.web.common.ServiceResult;
import com.cognizant.peg.web.common.ServiceStatus;
import com.cognizant.opserv.sp.exception.AlignmentServiceException;
import com.cognizant.opserv.sp.exception.AttributeServiceException;

@Controller
public class EntityTemplateSetupRestController {
	/*
	 * Instantiate the Logger
	 */	
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(CustomerAffiliationRestController.class);
	
	@Autowired
	private EntityTemplateSetupService entitytempSetupService;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/getEntityTemplateById/{templateId}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody ServiceResponse getEntityTemplateById(@PathVariable("templateId") Integer templateId, HttpServletRequest request) {
		ServiceStatus serviceStatus = null;
		ServiceResponse serviceResponse = new ServiceResponse();
		ServiceResult serviceResult = new ServiceResult();
		try {
			EntityTemplate template = new EntityTemplate();
			
			template.setId(templateId.longValue());
			UserDetails userDetails = ModelAssembler.getUserDetails();
			userDetails.setLocaleId("en_US");
			userDetails.setUserId(1);
			userDetails.setTenantId((short)1);
			userDetails.setStaffId(1);
			EntityTemplate temp= entitytempSetupService.getEntityTemplateById(template, userDetails);
			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
			serviceResult.setDetail(temp);
		} catch (AttributeServiceException e) {
			serviceResult.setDetail("Exception occured "+e.getMessage());
			serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR, "500 ERROR");
		}
		serviceResponse.setStatus(serviceStatus);
		serviceResponse.setResult(serviceResult);
		LOGGER.info(" Returning REST response with Result.."+serviceResult.getDetail()+" and status "+serviceResponse.getStatus().getCode().getCode()+"-"+serviceResponse.getStatus().getMessage());
		return serviceResponse;
	}
	
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/getEntityTemplateByEntity/{entity}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody ServiceResponse getEntityTemplateByEntity(@PathVariable("entity") EntityType entity, HttpServletRequest request) {
		ServiceStatus serviceStatus = null;
		ServiceResponse serviceResponse = new ServiceResponse();
		ServiceResult serviceResult = new ServiceResult();
		try {
			EntityTemplate template = new EntityTemplate();
			template.setEntity(entity);
			
			UserDetails userDetails = ModelAssembler.getUserDetails();
			userDetails.setLocaleId("en_US");
			userDetails.setUserId(1);
			userDetails.setTenantId((short)1);
			userDetails.setStaffId(1);
			List<EntityTemplate> tempList= entitytempSetupService.getEntityTemplateByEntity(template, userDetails);
			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
			serviceResult.setList(tempList);
		} catch (AttributeServiceException e) {
			serviceResult.setDetail("Exception occured "+e.getMessage());
			serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR, "500 ERROR");
		}
		serviceResponse.setStatus(serviceStatus);
		serviceResponse.setResult(serviceResult);
		LOGGER.info(" Returning REST response with Result.."+serviceResult.getDetail()+" and status "+serviceResponse.getStatus().getCode().getCode()+"-"+serviceResponse.getStatus().getMessage());
		return serviceResponse;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/getAlignmentEntityTempaltes/{templateId}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody ServiceResponse getAlignmentEntityTempaltes(@PathVariable("templateId") Integer templateId, HttpServletRequest request) {
		ServiceStatus serviceStatus = null;
		ServiceResponse serviceResponse = new ServiceResponse();
		ServiceResult serviceResult = new ServiceResult();
		try {
			EntityTemplate template = new EntityTemplate();
			template.setId(templateId.longValue());
			
			UserDetails userDetails = ModelAssembler.getUserDetails();
			userDetails.setLocaleId("en_US");
			userDetails.setUserId(1);
			userDetails.setTenantId((short)1);
			userDetails.setStaffId(1);
			List<EntityTemplateAlignment> tempAlgmntList= entitytempSetupService.getAlignmentEntityTempaltes(template, userDetails);
			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
			serviceResult.setList(tempAlgmntList);
		} catch (AlignmentServiceException e) {
			serviceResult.setDetail("Exception occured "+e.getMessage());
			serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR, "500 ERROR");
		}
		serviceResponse.setStatus(serviceStatus);
		serviceResponse.setResult(serviceResult);
		LOGGER.info(" Returning REST response with Result.."+serviceResult.getDetail()+" and status "+serviceResponse.getStatus().getCode().getCode()+"-"+serviceResponse.getStatus().getMessage());
		return serviceResponse;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/assignTemplatesToAlignment/{templateId}/{al}/{bu}/{st}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody ServiceResponse assignTemplatesToAlignment(@PathVariable("templateId") Integer templateId, 
			@PathVariable("al") Long al,
			@PathVariable("bu") Long bu,
			@PathVariable("st") Long st,HttpServletRequest request) {
		ServiceStatus serviceStatus = null;
		ServiceResponse serviceResponse = new ServiceResponse();
		ServiceResult serviceResult = new ServiceResult();
		try {
			List<EntityTemplateAlignment> templateAlignments=new ArrayList<EntityTemplateAlignment>();
			EntityTemplateAlignment templateAlignment =new EntityTemplateAlignment();
			
			
				EntityTemplate temp=new EntityTemplate();
				
				temp.setId(templateId.longValue());
				templateAlignment.setTemplate(temp);
				
				templateAlignment.setAlignment(ModelAssembler.getAlignmentModel(al,bu,st));
				templateAlignments.add(templateAlignment);
			UserDetails userDetails = ModelAssembler.getUserDetails();
			userDetails.setLocaleId("en_US");
			userDetails.setUserId(1);
			userDetails.setTenantId((short)1);
			userDetails.setStaffId(1);
			 entitytempSetupService.assignTemplatesToAlignment(templateAlignments, userDetails);
			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
			serviceResult.setDetail("assigned templates to aligmnent");
		} catch (AlignmentServiceException e) {
			serviceResult.setDetail("Exception occured "+e.getMessage());
			serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR, "500 ERROR");
		}
		serviceResponse.setStatus(serviceStatus);
		serviceResponse.setResult(serviceResult);
		LOGGER.info(" Returning REST response with Result.."+serviceResult.getDetail()+" and status "+serviceResponse.getStatus().getCode().getCode()+"-"+serviceResponse.getStatus().getMessage());
		return serviceResponse;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/copyAndCreateEntityTemplate/{srctemplateId}/{newTemplateName}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody ServiceResponse copyAndCreateEntityTemplate(@PathVariable("srctemplateId") Integer srctemplateId, 
			@PathVariable("newTemplateName") String newTemplateName,
			HttpServletRequest request) {
		ServiceStatus serviceStatus = null;
		ServiceResponse serviceResponse = new ServiceResponse();
		ServiceResult serviceResult = new ServiceResult();
		try {
			EntityTemplate newTemplate =new EntityTemplate();
			EntityTemplate srcTemplate=new EntityTemplate();
			newTemplate.setName(newTemplateName);
			newTemplate.setDescription("Customer save");
			newTemplate.setStartDate(new Date());
			newTemplate.setCreatedDate(new Date());
			newTemplate.setCreatedBy(1);
			newTemplate.setTenantId((short) 1);
			srcTemplate.setId(srctemplateId.longValue());
			srcTemplate.setStartDate(new Date());
			srcTemplate.setCreatedBy(1);
			srcTemplate.setTenantId((short) 1);
			UserDetails userDetails = ModelAssembler.getUserDetails();
			userDetails.setLocaleId("en_US");
			userDetails.setUserId(1);
			userDetails.setTenantId((short)1);
			userDetails.setStaffId(1);
			EntityTemplate temp =entitytempSetupService.copyAndCreateEntityTemplate(srcTemplate, newTemplate,userDetails);
			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
			serviceResult.setDetail(temp);
		} catch (AttributeServiceException e) {
			serviceResult.setDetail("Exception occured "+e.getMessage());
			serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR, "500 ERROR");
		}
		serviceResponse.setStatus(serviceStatus);
		serviceResponse.setResult(serviceResult);
		LOGGER.info(" Returning REST response with Result.."+serviceResult.getDetail()+" and status "+serviceResponse.getStatus().getCode().getCode()+"-"+serviceResponse.getStatus().getMessage());
		return serviceResponse;
	}
	
	
}
