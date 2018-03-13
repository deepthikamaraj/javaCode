package com.cognizant.opserv.sp.rest.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cognizant.opserv.sp.exception.ProductServiceException;
import com.cognizant.opserv.sp.model.Product;
import com.cognizant.opserv.sp.rest.util.ModelAssembler;
import com.cognizant.opserv.sp.service.product.ProductService;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.rest.common.StatusCode;
import com.cognizant.peg.web.common.ServiceResponse;
import com.cognizant.peg.web.common.ServiceResult;
import com.cognizant.peg.web.common.ServiceStatus;

/**
 * ****************************************************************************.
 *
 * @class ProductRestController contains to call the rest services for product Dts
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 * ***************************************************************************
 */
@Controller
public class ProductRestController {
	
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(ProductRestController.class);

	@Autowired
	private ProductService prdService;
	
	
	/**
	 * Gets the product details.
	 *
	 * @method getProductDetails
	 * @param productId the product id
	 * @param userDetails the user details
	 * @return ServiceResponse<List<Product>> - the Json Object of the product details
	 * @throws ProductServiceException the product service exception
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/product/{prdId}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody ServiceResponse<Product> getProductDetailsByProductId(@PathVariable("prdId") Long prdId, 
				HttpServletRequest request) {

		ServiceStatus serviceStatus = null;
		ServiceResponse serviceResponse = new ServiceResponse();
		ServiceResult serviceResult = new ServiceResult();
		
		try {
		
			serviceResult.setDetail(prdService.getProductDetails(prdId, ModelAssembler.getDefaultUserDetails()));
			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
		} catch (ProductServiceException e) {
			serviceResult.setDetail("Exception occured "+e.getMessage());
			serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR, "500 ERROR");
		}
		serviceResponse.setStatus(serviceStatus);
		serviceResponse.setResult(serviceResult);
		LOGGER.info(" Returning REST response with Result.."+serviceResult.getDetail()+" and status "+serviceResponse.getStatus().getCode().getCode()+"-"+serviceResponse.getStatus().getMessage());
		return serviceResponse;
	}
	
	
	/**
	 * Gets the product details.
	 *
	 * @method getProductDetails
	 * @param productIds the product ids
	 * @param userDetails the user details
	 * @return ServiceResponse<List<Product>> - returns the Json List of the product details
	 * @throws ProductServiceException the product service exception
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/products/{prdIds}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody ServiceResponse<List<Product>> getAllProductDetailsByProductIds(@PathVariable("prdIds") List<Long> prdIds, 
				HttpServletRequest request) {

		ServiceStatus serviceStatus = null;
		ServiceResponse serviceResponse = new ServiceResponse();
		ServiceResult serviceResult = new ServiceResult();
		
		try {
		
			serviceResult.setList(prdService.getProductDetails(prdIds, ModelAssembler.getDefaultUserDetails()));
			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
		} catch (ProductServiceException e) {
			serviceResult.setDetail("Exception occured "+e.getMessage());
			serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR, "500 ERROR");
		}
		serviceResponse.setStatus(serviceStatus);
		serviceResponse.setResult(serviceResult);
		LOGGER.info(" Returning REST response with Result.."+serviceResult.getDetail()+" and status "+serviceResponse.getStatus().getCode().getCode()+"-"+serviceResponse.getStatus().getMessage());
		return serviceResponse;
	}	
	


}
