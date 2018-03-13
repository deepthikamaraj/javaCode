package com.cognizant.opserv.sp.rest.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cognizant.opserv.sp.cr.process.dto.CustomerAlignmentDTO;
import com.cognizant.opserv.sp.cr.process.internal.service.OfflineRequestService;
import com.cognizant.opserv.sp.event.messages.OfflineRequestMessage;
import com.cognizant.opserv.sp.exception.AlignmentServiceException;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.rest.util.ModelAssembler;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.rest.common.StatusCode;
import com.cognizant.peg.web.common.ServiceResponse;
import com.cognizant.peg.web.common.ServiceResult;
import com.cognizant.peg.web.common.ServiceStatus;

/**
 * ****************************************************************************.
 *
 * @class OfflineRequestServiceRestController contains to call the rest services for OfflineRequestService
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 * ***************************************************************************
 */

@Controller
public class OfflineRequestServiceRestController {
	
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(OfflineRequestServiceRestController.class);

	@Autowired
	OfflineRequestService offlineRequestService;
	
	/**
	 * @param offlineReqId
	 * @param userId
	 * @param request
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/offlineRequest/getCustomerOfflineRequest/{offlineReqId}/{userID}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody ServiceResponse<CustomerAlignmentDTO> getCustomerOfflineRequest(@PathVariable("offlineReqId") Long offlineReqId,
																						@PathVariable("userId") Integer userId, 
																						HttpServletRequest request) {

		ServiceStatus serviceStatus = null;
		ServiceResponse serviceResponse = new ServiceResponse();
		ServiceResult serviceResult = new ServiceResult();
		
		try {
			OfflineRequestMessage message = new OfflineRequestMessage();
			message.setOfflineReqId(offlineReqId);
			
			UserDetails userDetails = ModelAssembler.getDefaultUserDetails();
			userDetails.setUserId(userId);
			
			serviceResult.setDetail(offlineRequestService.getCustomerOfflineRequest(message, userDetails));
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
