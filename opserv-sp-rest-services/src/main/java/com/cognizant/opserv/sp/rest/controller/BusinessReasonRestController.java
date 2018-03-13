package com.cognizant.opserv.sp.rest.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cognizant.opserv.sp.common.ChangeRequestTriggerType;
import com.cognizant.opserv.sp.common.CustomerCategoryType;
import com.cognizant.opserv.sp.exception.OpservUndefinedException;
import com.cognizant.opserv.sp.model.Alignment;
import com.cognizant.opserv.sp.model.BusinessReason;
import com.cognizant.opserv.sp.model.BusinessUnit;
import com.cognizant.opserv.sp.model.SalesTeam;
import com.cognizant.opserv.sp.rest.util.ModelAssembler;
import com.cognizant.opserv.sp.service.common.BusinessReasonService;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.rest.common.StatusCode;
import com.cognizant.peg.web.common.ServiceResponse;
import com.cognizant.peg.web.common.ServiceResult;
import com.cognizant.peg.web.common.ServiceStatus;

@Controller
public class BusinessReasonRestController {

	/*
	 * Instantiate the Logger
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(BusinessReasonRestController.class);
	
	@Autowired
	private BusinessReasonService businessReasonService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/businessReason/getBusinessReasons/{algnId}/{buId}/{stId}/{trTypeId}/{custCategoryId}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ServiceResponse<List<BusinessReason>> getBusinessReasons(@PathVariable("algnId") Long algnId, @PathVariable("buId") Long buId, 
			@PathVariable("stId") Long stId, @PathVariable("trTypeId") Integer trTypeId, @PathVariable("custCategoryId") Integer custCategoryId, 
			HttpServletRequest request) throws OpservUndefinedException {

		ServiceStatus serviceStatus = null;
		ServiceResponse<List<BusinessReason>> serviceResponse = new ServiceResponse<List<BusinessReason>>();
		@SuppressWarnings("rawtypes")
		ServiceResult serviceResult = new ServiceResult();
		
		Alignment alignment = new Alignment();
		alignment.setId(algnId);
		BusinessUnit businessUnit = new BusinessUnit();
		SalesTeam salesTeam = new SalesTeam();
		salesTeam.setId(stId);
		businessUnit.setId(buId);
		salesTeam.setBusinessUnit(businessUnit);
		alignment.setSalesTeam(salesTeam);
		
		ChangeRequestTriggerType trType = ChangeRequestTriggerType.getChangeRequestTriggerType(trTypeId);
		CustomerCategoryType custType = CustomerCategoryType.getCustomerCategoryType(custCategoryId);

		try {
			serviceResult.setList(businessReasonService.fetchBusinessReasons(alignment, trType, custType, ModelAssembler.getDefaultUserDetails()));
			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
		} catch (OpservUndefinedException e) {
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
