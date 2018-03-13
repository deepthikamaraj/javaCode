package com.cognizant.opserv.sp.rest.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cognizant.opserv.sp.exception.AlignmentServiceException;
import com.cognizant.opserv.sp.model.Customer;
import com.cognizant.opserv.sp.model.CustomerProductAlignment;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.rest.util.ModelAssembler;
import com.cognizant.opserv.sp.service.alignment.CustomerProductAlignmentService;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.rest.common.StatusCode;
import com.cognizant.peg.web.common.ServiceResponse;
import com.cognizant.peg.web.common.ServiceResult;
import com.cognizant.peg.web.common.ServiceStatus;

/**
 * ****************************************************************************.
 *
 * @class CustomerProductAlignmentRestController contains to call the rest services for Customer Product alignment
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 * ***************************************************************************
 */
@Controller
public class CustomerProductAlignmentRestController {
	
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(CustomerProductAlignmentRestController.class);

	@Autowired
	CustomerProductAlignmentService custPrdAligService;
	
	/**
	 *  @Method getAllCustomerProductsByCustIdSpId - This method is to fetch the
	 *         Customer Product dts By CustomerId & Sales Postion Id
	 * @param salesPos
	 * @param userDetails
	 * @return ServiceResponse<List<ProductAlignment>> - Json Object list of the Customer  Product Details
	 * @throws AlignmentServiceException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/salespos/alignment/{custId}/{spId}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody ServiceResponse<List<CustomerProductAlignment>> getAllCustomerProductsByCustIdSpId(@PathVariable("custId") Long custId, 
			@PathVariable("spId") Long spId,			
				HttpServletRequest request) {

		ServiceStatus serviceStatus = null;
		ServiceResponse serviceResponse = new ServiceResponse();
		ServiceResult serviceResult = new ServiceResult();
		
		try {
			Customer cust = new Customer();
			cust.setId(custId);
			SalesPosition al = ModelAssembler.getSalesPosition(spId,0l);
			al.setActive(true);
			serviceResult.setList(custPrdAligService.getAllCustomerProducts(cust,al, ModelAssembler.getDefaultUserDetails()));
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
	
	
	
	/**
	 *  @Method getAllCustomerProductsBySpId - This method is to fetch the
	 *         Customer Product dts By  Sales Postion Id
	 * @param salesPos
	 * @param userDetails
	 * @return ServiceResponse<List<CustomerProductAlignment>> - Json Object list of the Customer  Product Details
	 * @throws AlignmentServiceException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/salespos/alignment/{spId}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody ServiceResponse<List<CustomerProductAlignment>> getAllCustomerProductsBySpId(@PathVariable("spId") Long spId,			
				HttpServletRequest request) {

		ServiceStatus serviceStatus = null;
		ServiceResponse serviceResponse = new ServiceResponse();
		ServiceResult serviceResult = new ServiceResult();
		
		try {
			SalesPosition al = ModelAssembler.getSalesPosition(spId,0l);
			al.setActive(true);
			serviceResult.setList(custPrdAligService.getAllCustomerProducts(al, ModelAssembler.getDefaultUserDetails()));
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
