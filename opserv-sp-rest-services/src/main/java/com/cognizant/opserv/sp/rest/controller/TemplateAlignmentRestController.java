package com.cognizant.opserv.sp.rest.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cognizant.opserv.sp.common.EntityType;
import com.cognizant.opserv.sp.model.Alignment;
import com.cognizant.opserv.sp.rest.util.ModelAssembler;
import com.cognizant.opserv.sp.service.alignment.TemplateAlignmentService;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.rest.common.StatusCode;
import com.cognizant.peg.web.common.ServiceResponse;
import com.cognizant.peg.web.common.ServiceResult;
import com.cognizant.peg.web.common.ServiceStatus;
import com.cognizant.opserv.sp.exception.AlignmentServiceException;

@Controller
public class TemplateAlignmentRestController {
	
	/**
	 * Instantiate the Logger
	 */	
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(CustomerAlignmentRestController.class);
	
	/**
	 * templateAlignmentService has the services to fetch template results.
	 */
	@Autowired
	private TemplateAlignmentService templateAlignmentService;
	/**
	 * @method getEntityTemplate : used to get the template Info
	 * @param entityId : input parameter entityId
	 * @param alId : input parameter alignment Id 
	 * @param buId : input parameter business Unit Id
	 * @param stId : input parameter salesTeam Id
	 * @param request :  request Object
	 * @return :  returns template Info
	 * @throws AlignmentServiceException
	 */
	@RequestMapping(value="/template/alignment/{entId}/{al}/{bu}/{st}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody ServiceResponse<EntityType> getEntityTemplate(@PathVariable("entId") Long entityId,@PathVariable("al") Long alId,
			@PathVariable("bu") Long buId,
			@PathVariable("st") Long stId,
			HttpServletRequest request) throws AlignmentServiceException {

		ServiceStatus serviceStatus = null;
		ServiceResponse serviceResponse = new ServiceResponse();
		ServiceResult serviceResult = new ServiceResult();

		try {
			Alignment alignment = ModelAssembler.getAlignmentModel(alId,buId,stId);
			EntityType entityType = EntityType.getEntityType(entityId.intValue());
			if(entityType != null){
				serviceResult.setDetail(templateAlignmentService.getEntityTemplate(entityType,alignment,ModelAssembler.getDefaultUserDetails()));
			}
			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
		} catch (AlignmentServiceException e) {
			serviceResult.setDetail("Exception occured "+e.getMessage());
			serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR, "500 ERROR");
		}
		serviceResponse.setStatus(serviceStatus);
		serviceResponse.setResult(serviceResult);
		LOGGER.info(" Returning REST response with Result.."+serviceResult.getDetail()+" and status "+serviceResponse.getStatus().getCode().getCode()+"-"+serviceResponse.getStatus().getMessage());
		return serviceResponse;
	}	
	

}
