package com.cognizant.opserv.sp.rest.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;






import com.cognizant.opserv.sp.common.EntityType;
import com.cognizant.opserv.sp.exception.AttributeServiceException;
import com.cognizant.opserv.sp.model.EntityTemplate;
import com.cognizant.opserv.sp.model.attrb.AttributeGroup;
import com.cognizant.opserv.sp.rest.util.ModelAssembler;
import com.cognizant.opserv.sp.service.extdattr.AttributeSetupService;
import com.cognizant.opserv.sp.model.attrb.AttributeDefinition;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.rest.common.StatusCode;
import com.cognizant.peg.web.common.ServiceResponse;
import com.cognizant.peg.web.common.ServiceResult;
import com.cognizant.peg.web.common.ServiceStatus;
import com.cognizant.opserv.sp.model.auth.UserDetails;


@Controller
public class AttributeSetupRestController {
	
	/*
	 * Instantiate the Logger
	 */	
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(CustomerAffiliationRestController.class);
	
	@Autowired
	private AttributeSetupService attributeSetupService;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/attributeSetupDeleteGrp/{grpId}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody ServiceResponse deleteAttributeGroup(@PathVariable("grpId") Long grpId, HttpServletRequest request) {
		ServiceStatus serviceStatus = null;
		ServiceResponse serviceResponse = new ServiceResponse();
		ServiceResult serviceResult = new ServiceResult();
		try {
			AttributeGroup attrGrp = new AttributeGroup();
			attrGrp.setId(grpId);
			attributeSetupService.deleteAttributeGroup(attrGrp, ModelAssembler.getUserDetails());
			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
			serviceResult.setDetail("Attribute group deleted "+grpId);
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
	@RequestMapping(value="/attributeSetupDeleteAttr/{grpId}/{attrId}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody ServiceResponse deleteAttributeFromGroup(@PathVariable("grpId") Long grpId,@PathVariable("attrId") Long attrId, HttpServletRequest request) {
		ServiceStatus serviceStatus = null;
		ServiceResponse serviceResponse = new ServiceResponse();
		ServiceResult serviceResult = new ServiceResult();
		try {
			AttributeGroup attrGrp = ModelAssembler.getAttributeGroup(grpId,attrId);
			attributeSetupService.deleteAttributesFromGroup(attrGrp, ModelAssembler.getUserDetails());
			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
			serviceResult.setDetail("Attribute id " +attrId+ " from group id "+ grpId+ " is deleted");
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
	@RequestMapping(value="/attributeSetupCreateAttrGrp/{grpName}/{grpid}/{attrName}/{attrid}/{templateId}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody ServiceResponse createAttributeGrp(@PathVariable("grpName") String grpName,@PathVariable("grpid") Integer grpId,@PathVariable("attrName") String attrName, @PathVariable("attrid") Integer attrid,@PathVariable("templateId") Integer templateId,HttpServletRequest request) {
		ServiceStatus serviceStatus = null;
		ServiceResponse serviceResponse = new ServiceResponse();
		ServiceResult serviceResult = new ServiceResult();
		try {
			AttributeGroup attrGrp = ModelAssembler.getAttributeGroupWithAttrdefCreate(grpName,attrName,templateId,grpId,attrid);
			EntityTemplate template = ModelAssembler.getEntityTemplate(templateId);
			UserDetails userDetails = ModelAssembler.getUserDetails();
			userDetails.setLocaleId("en_US");
			userDetails.setUserId(1);
			boolean flag = attributeSetupService.createAttributeGroup(attrGrp, template ,userDetails );
			AttributeGroup  attributeGroupModel = null;
			if(flag){
				 attributeGroupModel = attributeSetupService.fetchAttributeGroup(grpName, templateId ,userDetails );
			}
			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
			serviceResult.setDetail(attributeGroupModel);
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
	@RequestMapping(value="/attributeSetupUpdateGrpAttr/{grpId}/{attrId}/{attrName}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody ServiceResponse updateAttributeData(@PathVariable("grpId") Long grpId,@PathVariable("attrId") Long attrId, @PathVariable("attrName") String attrName, HttpServletRequest request) {
		ServiceStatus serviceStatus = null;
		ServiceResponse serviceResponse = new ServiceResponse();
		ServiceResult serviceResult = new ServiceResult();
		try {
			AttributeGroup attrGrp = ModelAssembler.getAttributeGroupWithAttrdefUpdate(grpId,attrId,attrName);
			attributeSetupService.updateGroupAttribute(attrGrp,ModelAssembler.getUserDetails());
			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
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
	@RequestMapping(value="/attributeSetupuniqAttrGrp/{grpName}/{templateId}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody ServiceResponse uniqAttrGrp(@PathVariable("grpName") String grpName,@PathVariable("templateId") Integer templateId, HttpServletRequest request) {
		ServiceStatus serviceStatus = null;
		ServiceResponse serviceResponse = new ServiceResponse();
		ServiceResult serviceResult = new ServiceResult();
		try {
		boolean uniqueFlag =attributeSetupService.uniqAttrGrp(grpName, templateId, ModelAssembler.getUserDetails());
		serviceResult.setDetail(uniqueFlag);
		serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
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
	@RequestMapping(value="/attributeSetupuniqAttrName/{grpName}/{attrName}/{templateId}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody ServiceResponse uniqAttrName(@PathVariable("grpName") String grpName, @PathVariable("attrName") String attrName, @PathVariable("templateId") Integer templateId, HttpServletRequest request) {
		ServiceStatus serviceStatus = null;
		ServiceResponse serviceResponse = new ServiceResponse();
		ServiceResult serviceResult = new ServiceResult();
		try {
			String uniqueFlag = attributeSetupService.uniqAttrName(grpName, attrName, templateId,ModelAssembler.getUserDetails());
			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
			serviceResult.setDetail(uniqueFlag);
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
	@RequestMapping(value="/attrSetupCreateAttrGrpNameOnly/{attrName}/{templateId}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody ServiceResponse createAttributeGrp(@PathVariable("grpName") String grpName,@PathVariable("templateId") Integer templateId,HttpServletRequest request) {
		ServiceStatus serviceStatus = null;
		ServiceResponse serviceResponse = new ServiceResponse();
		ServiceResult serviceResult = new ServiceResult();
		try {
			AttributeGroup attrGrp = new AttributeGroup();
			attrGrp.setName(grpName);
			EntityTemplate template = ModelAssembler.getEntityTemplate(templateId);
			UserDetails userDetails = ModelAssembler.getUserDetails();
			userDetails.setLocaleId("en_US");
			userDetails.setUserId(1);
			boolean flag = attributeSetupService.createAttributeGroup(attrGrp, template ,userDetails );
			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
			serviceResult.setDetail(flag);
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
	@RequestMapping(value="/attributeSetupFetchAttrGrpbyName/{grpName}/{templateId}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody ServiceResponse fetchAttributeGrpbyName(@PathVariable("grpName") String grpName,@PathVariable("templateId") Integer templateId,HttpServletRequest request) {
		ServiceStatus serviceStatus = null;
		ServiceResponse serviceResponse = new ServiceResponse();
		ServiceResult serviceResult = new ServiceResult();
		try {
			UserDetails userDetails = ModelAssembler.getUserDetails();
			userDetails.setLocaleId("en_US");
			userDetails.setUserId(1);
			AttributeGroup  attributeGroupModel = attributeSetupService.fetchAttributeGroup(grpName, templateId ,userDetails );
			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
			serviceResult.setDetail(attributeGroupModel);
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
	@RequestMapping(value="/getTemplateAttributes/{templateId}/{entityId}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody ServiceResponse getTemplateAttributes(@PathVariable("templateId") Integer templateId,@PathVariable("entityId") Integer entityId,HttpServletRequest request) {
		ServiceStatus serviceStatus = null;
		ServiceResponse serviceResponse = new ServiceResponse();
		ServiceResult serviceResult = new ServiceResult();
		try {
			EntityTemplate template =new EntityTemplate();
			template.setId(templateId.longValue());
			template.setEntity(EntityType.SALESPOSITION);
			UserDetails userDetails = ModelAssembler.getUserDetails();
			userDetails.setLocaleId("en_US");
			userDetails.setUserId(1);
			userDetails.setTenantId((short)1);
			userDetails.setStaffId(1);
			List<AttributeDefinition>  attributeDefList = attributeSetupService.getTemplateAttributes(template, userDetails);
			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
			serviceResult.setList(attributeDefList);
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
	@RequestMapping(value="/saveAttributeDefinition/{attrName}/{entityId}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody ServiceResponse saveAttributeDefinition(@PathVariable("attrName") String attrName,@PathVariable("entityId") Integer entityId,HttpServletRequest request) {
		ServiceStatus serviceStatus = null;
		ServiceResponse serviceResponse = new ServiceResponse();
		ServiceResult serviceResult = new ServiceResult();
		try {
			AttributeDefinition attrDef= ModelAssembler.getAttributeDef(attrName,entityId);
			UserDetails userDetails = ModelAssembler.getUserDetails();
			userDetails.setLocaleId("en_US");
			userDetails.setUserId(1);
			userDetails.setTenantId((short)1);
			userDetails.setStaffId(1);
			AttributeDefinition  attributeDef = attributeSetupService.saveAttributeDefinition(attrDef, userDetails);
			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
			serviceResult.setDetail(attributeDef);
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
	@RequestMapping(value="/getAttributesByGroup/{attrGrpId}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody ServiceResponse getAttributesByGroup(@PathVariable("attrGrpId") Integer attrGrpId,HttpServletRequest request) {
		ServiceStatus serviceStatus = null;
		ServiceResponse serviceResponse = new ServiceResponse();
		ServiceResult serviceResult = new ServiceResult();
		try {
			AttributeGroup attributeGroup=new AttributeGroup();
			attributeGroup.setId(attrGrpId.longValue());
			UserDetails userDetails = ModelAssembler.getUserDetails();
			userDetails.setLocaleId("en_US");
			userDetails.setUserId(1);
			userDetails.setTenantId((short)1);
			userDetails.setStaffId(1);
			List<AttributeDefinition>  attributeDefList = attributeSetupService.getAttributesByGroup(attributeGroup, userDetails);
			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
			serviceResult.setList(attributeDefList);
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
