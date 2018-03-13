package com.cognizant.opserv.sp.rest.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cognizant.opserv.sp.common.MetricTriggerType;
import com.cognizant.opserv.sp.exception.AlignmentServiceException;
import com.cognizant.opserv.sp.exception.ChangeRequestServiceException;
import com.cognizant.opserv.sp.exception.CustomerServiceException;
import com.cognizant.opserv.sp.exception.MetricServiceException;
import com.cognizant.opserv.sp.model.Alignment;
import com.cognizant.opserv.sp.model.Country;
import com.cognizant.opserv.sp.model.CountryDivision;
import com.cognizant.opserv.sp.model.Customer;
import com.cognizant.opserv.sp.model.CustomerProduct;
import com.cognizant.opserv.sp.model.EmployeeAlignment;
import com.cognizant.opserv.sp.model.GeographyAlignment;
import com.cognizant.opserv.sp.model.Product;
import com.cognizant.opserv.sp.model.ProductAlignment;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.model.cr.ChangeRequest;
import com.cognizant.opserv.sp.model.metric.MetricResult;
import com.cognizant.opserv.sp.rest.util.ModelAssembler;
import com.cognizant.opserv.sp.service.alignment.EmployeeAlignmentService;
import com.cognizant.opserv.sp.service.alignment.GeographyAlignmentService;
import com.cognizant.opserv.sp.service.alignment.ProductAlignmentService;
import com.cognizant.opserv.sp.service.alignment.SalesPositionService;
import com.cognizant.opserv.sp.service.businessrule.BusinessRuleService;
import com.cognizant.opserv.sp.service.changereq.ChangeRequestService;
import com.cognizant.opserv.sp.service.common.CommonService;
import com.cognizant.opserv.sp.service.customer.CustomerProductService;
import com.cognizant.opserv.sp.service.customer.CustomerService;
import com.cognizant.opserv.sp.service.metric.MetricResultService;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.rest.common.StatusCode;
import com.cognizant.peg.web.common.ServiceResponse;
import com.cognizant.peg.web.common.ServiceResult;
import com.cognizant.peg.web.common.ServiceStatus;

/**
 * 
 * @author 426111
 *
 */
@Controller
public class HomePageRestController {
	/*
	 * Instantiate the Logger
	 */	
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(HomePageRestController.class);

	@Autowired
	private SalesPositionService salesPositionService;
	
	@Autowired
	private GeographyAlignmentService geoAlignmentService;

	@Autowired
	private ProductAlignmentService productAlignmentService; 
	
	@Autowired
	private EmployeeAlignmentService employeeAlignmentService;
	
	@Autowired
	private ChangeRequestService changeRequestService;
	
	@Autowired
	private MetricResultService metricResultService;

	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private CustomerProductService customerProdService;
	
	@Autowired
	private CommonService commonService;
	
	@Autowired
	private BusinessRuleService businessRuleService;
	
//	@RequestMapping(value="/saleshierarchy/alignment/{al}/{bu}/{st}", method=RequestMethod.GET, produces="application/json")
//	public @ResponseBody ServiceResponse<List<SalesOrgHierarchy>> getSalesHierarchyByAlignment(@PathVariable("al") Long alId,
//			@PathVariable("bu") Long buId,
//			@PathVariable("st") Long stId,
//			HttpServletRequest request) {
//
//		ServiceStatus serviceStatus = null;
//		ServiceResponse serviceResponse = new ServiceResponse();
//		ServiceResult serviceResult = new ServiceResult();
//
//		try {
//			
//			Alignment al = ModelAssembler.getAlignmentModel(alId,buId,stId);
//					
//			serviceResult.setList(salesHierarchyService.getAlignmentSalesHierarchy(al, ModelAssembler.getDefaultUserDetails()));
//			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
//		} catch (SalesOrgServiceException e) {
//			serviceResult.setDetail("Exception occured "+e.getMessage());
//			serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR, "500 ERROR");
//		}
//		serviceResponse.setStatus(serviceStatus);
//		serviceResponse.setResult(serviceResult);
//		LOGGER.info(" Returning REST response with Result.."+serviceResult.getDetail()+" and status "+serviceResponse.getStatus().getCode().getCode()+"-"+serviceResponse.getStatus().getMessage());
//		return serviceResponse;
//	}	
	
	@RequestMapping(value="/salespos/alignment/{al}/{bu}/{st}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody ServiceResponse<List<SalesPosition>> getAllSalesPositionsByAlignment(@PathVariable("al") Long alId,
			@PathVariable("bu") Long buId,
			@PathVariable("st") Long stId,
			HttpServletRequest request) {

		ServiceStatus serviceStatus = null;
		ServiceResponse serviceResponse = new ServiceResponse();
		ServiceResult serviceResult = new ServiceResult();

		try {
			
			Alignment al = ModelAssembler.getAlignmentModel(alId,buId,stId);
					
			serviceResult.setList(salesPositionService.getAllSalesPositionsByAlignment(al,ModelAssembler.getDefaultUserDetails()));
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
	
	
	@RequestMapping(value={"/geo/salespos/{spId}","/geo/salespos/{spId}/{shId}"}, method=RequestMethod.GET, produces="application/json")
	public @ResponseBody ServiceResponse<List<GeographyAlignment>> getAllGeographyAlignments(@PathVariable("spId") Long spId,
			@PathVariable("shId") Long shId,
			HttpServletRequest request) {

		ServiceStatus serviceStatus = null;
		ServiceResponse serviceResponse = new ServiceResponse();
		ServiceResult serviceResult = new ServiceResult();

		try {
			
			SalesPosition sp = ModelAssembler.getSalesPosition(spId, shId);
					
			serviceResult.setList(geoAlignmentService.getAllGeographyAlignments(sp,ModelAssembler.getDefaultUserDetails()));
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
		
		
	@RequestMapping(value="/prdAlignment/salesPos/{spId}/{alId}/{buId}/{stId}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody ServiceResponse<List<ProductAlignment>> getAllProductAlignmentsBySalesPosition(@PathVariable("spId") Long spId,
			@PathVariable("alId") Long alId,
			@PathVariable("buId") Long buId,
			@PathVariable("stId") Long stId,
			HttpServletRequest request) {

		ServiceStatus serviceStatus = null;
		ServiceResponse serviceResponse = new ServiceResponse();
		ServiceResult serviceResult = new ServiceResult();

		try {
			
			SalesPosition salesPosition = ModelAssembler.getSalesPosition(spId, null, alId, buId, stId);
					
			serviceResult.setDetail(productAlignmentService.getAllProductAlignmentsBySalesPosition(salesPosition,ModelAssembler.getDefaultUserDetails()));
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
	

	@RequestMapping(value="/empAlignment/usrDtls/{staffId}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody ServiceResponse<List<EmployeeAlignment>> getAllEmployeeAlignments(@PathVariable("staffId") Long staffId,
			HttpServletRequest request) {

		ServiceStatus serviceStatus = null;
		ServiceResponse serviceResponse = new ServiceResponse();
		ServiceResult serviceResult = new ServiceResult();

		try {
			
			UserDetails userDetails = ModelAssembler.getDefaultUserDetails();
			userDetails.setStaffId(staffId.intValue());
					
			serviceResult.setDetail(employeeAlignmentService.getAllEmployeeAlignments(userDetails));
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

	@RequestMapping(value="/empAlignment/primEmpAlg/{al}/{bu}/{st}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody ServiceResponse<List<EmployeeAlignment>> getAllPrimaryEmployeeAlignments(@PathVariable("al") Long alId,
			@PathVariable("bu") Long buId,
			@PathVariable("st") Long stId,
			HttpServletRequest request) {

		ServiceStatus serviceStatus = null;
		ServiceResponse serviceResponse = new ServiceResponse();
		ServiceResult serviceResult = new ServiceResult();

		try {
			
			Alignment alignment = ModelAssembler.getAlignmentModel(alId,buId,stId);
					
			serviceResult.setDetail(employeeAlignmentService.getAllPrimaryEmployeeAlignments(alignment, ModelAssembler.getDefaultUserDetails()));
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
	
	@RequestMapping(value="/chngReq/ChngReqFrApprvl/{spId}/{shId}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody ServiceResponse<List<ChangeRequest>> getAllChangeRequestsForApproval(@PathVariable("spId") Long spId,
			@PathVariable("shId") Long shId,
			HttpServletRequest request) {

		ServiceStatus serviceStatus = null;
		ServiceResponse serviceResponse = new ServiceResponse();
		ServiceResult serviceResult = new ServiceResult();

		try {
			
			SalesPosition salesPosition = ModelAssembler.getSalesPosition(spId, shId);
					
			serviceResult.setDetail(changeRequestService.getAllChangeRequestsForApproval(salesPosition, ModelAssembler.getDefaultUserDetails()));
			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
		} catch (ChangeRequestServiceException e) {
			serviceResult.setDetail("Exception occured "+e.getMessage());
			serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR, "500 ERROR");
		}
		serviceResponse.setStatus(serviceStatus);
		serviceResponse.setResult(serviceResult);
		LOGGER.info(" Returning REST response with Result.."+serviceResult.getDetail()+" and status "+serviceResponse.getStatus().getCode().getCode()+"-"+serviceResponse.getStatus().getMessage());
		return serviceResponse;
	}	
	
	
	/*@RequestMapping(value="/metric/metricResult/{spId}/{shId}/{al}/{bu}/{st}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody ServiceResponse<List<MetricResult>> getAllLastMetricResults(@PathVariable("spId") Long spId,
			@PathVariable("shId") Long shId,
			@PathVariable("alId") Long alId,
			@PathVariable("buId") Long buId,
			@PathVariable("stId") Long stId,
			HttpServletRequest request) {

		ServiceStatus serviceStatus = null;
		ServiceResponse serviceResponse = new ServiceResponse();
		ServiceResult serviceResult = new ServiceResult();

		try {
			
			SalesPosition salesPosition = ModelAssembler.getSalesPosition(spId, shId, alId, buId, stId);
					
			serviceResult.setDetail(metricResultService.getAllLastMetricResults(salesPosition, ModelAssembler.getDefaultUserDetails()));
			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
		} catch (MetricServiceException e) {
			serviceResult.setDetail("Exception occured "+e.getMessage());
			serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR, "500 ERROR");
		}
		serviceResponse.setStatus(serviceStatus);
		serviceResponse.setResult(serviceResult);
		LOGGER.info(" Returning REST response with Result.."+serviceResult.getDetail()+" and status "+serviceResponse.getStatus().getCode().getCode()+"-"+serviceResponse.getStatus().getMessage());
		return serviceResponse;
	}	*/
	
	/*@RequestMapping(value="/metric/baseMetricResult/{spId}/{shId}/{al}/{bu}/{st}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody ServiceResponse<List<MetricResult>> getAllBaseMetricResults(@PathVariable("spId") Long spId,
			@PathVariable("shId") Long shId,
			@PathVariable("alId") Long alId,
			@PathVariable("buId") Long buId,
			@PathVariable("stId") Long stId,
			HttpServletRequest request) {

		ServiceStatus serviceStatus = null;
		ServiceResponse serviceResponse = new ServiceResponse();
		ServiceResult serviceResult = new ServiceResult();

		try {
			
			SalesPosition salesPosition = ModelAssembler.getSalesPosition(spId, shId, alId, buId, stId);
					
			serviceResult.setDetail(metricResultService.getAllBaseMetricResults(salesPosition, ModelAssembler.getDefaultUserDetails()));
			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
		} catch (MetricServiceException e) {
			serviceResult.setDetail("Exception occured "+e.getMessage());
			serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR, "500 ERROR");
		}
		serviceResponse.setStatus(serviceStatus);
		serviceResponse.setResult(serviceResult);
		LOGGER.info(" Returning REST response with Result.."+serviceResult.getDetail()+" and status "+serviceResponse.getStatus().getCode().getCode()+"-"+serviceResponse.getStatus().getMessage());
		return serviceResponse;
	}	*/
	
	@RequestMapping(value="/metric/allMetricResult/{spId}/{shId}/{al}/{bu}/{st}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody ServiceResponse<List<MetricResult>> getAllMetricResults(@PathVariable("spId") Long spId,
			@PathVariable("shId") Long shId,
			@PathVariable("al") Long alId,
			@PathVariable("bu") Long buId,
			@PathVariable("st") Long stId,
			HttpServletRequest request) throws AlignmentServiceException {

		ServiceStatus serviceStatus = null;
		ServiceResponse serviceResponse = new ServiceResponse();
		ServiceResult serviceResult = new ServiceResult();

		try {
			
			SalesPosition salesPosition = ModelAssembler.getSalesPosition(spId, shId, alId, buId, stId);
					
			serviceResult.setDetail(metricResultService.getAllMetricResults(salesPosition, ModelAssembler.getDefaultUserDetails()));
			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
		} catch (MetricServiceException e) {
			serviceResult.setDetail("Exception occured "+e.getMessage());
			serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR, "500 ERROR");
		}
		serviceResponse.setStatus(serviceStatus);
		serviceResponse.setResult(serviceResult);
		LOGGER.info(" Returning REST response with Result.."+serviceResult.getDetail()+" and status "+serviceResponse.getStatus().getCode().getCode()+"-"+serviceResponse.getStatus().getMessage());
		return serviceResponse;
	}
	
	@RequestMapping(value="/metric/allMetricResult/{spId}/{shId}/{al}/{bu}/{stId}/{metricTriggerTypes}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody ServiceResponse<List<MetricResult>> getAllMetricResults(@PathVariable("spId") Long spId,
			@PathVariable("shId") Long shId,
			@PathVariable("al") Long alId,
			@PathVariable("bu") Long buId,
			@PathVariable("stId") Long stId,
			@PathVariable("metricTriggerTypes") List<MetricTriggerType> types,
			HttpServletRequest request) throws AlignmentServiceException {

		ServiceStatus serviceStatus = null;
		ServiceResponse serviceResponse = new ServiceResponse();
		ServiceResult serviceResult = new ServiceResult();

		try {
			
			SalesPosition salesPosition = ModelAssembler.getSalesPosition(spId, shId, alId, buId, stId);
			List<MetricTriggerType> mTriggertypes = ModelAssembler.getMetricTypes(types);
					
			serviceResult.setDetail(metricResultService.getAllMetricResults(salesPosition, mTriggertypes, ModelAssembler.getDefaultUserDetails()));
			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
		} catch (MetricServiceException e) {
			serviceResult.setDetail("Exception occured "+e.getMessage());
			serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR, "500 ERROR");
		}
		serviceResponse.setStatus(serviceStatus);
		serviceResponse.setResult(serviceResult);
		LOGGER.info(" Returning REST response with Result.."+serviceResult.getDetail()+" and status "+serviceResponse.getStatus().getCode().getCode()+"-"+serviceResponse.getStatus().getMessage());
		return serviceResponse;
	}
	
	/*@RequestMapping(value="/metric/allBaseMetricResult/{spId}/{shId}/{al}/{bu}/{stId}/{metricTriggerTypes}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody ServiceResponse<List<MetricResult>> getAllBaseMetricResults(@PathVariable("spId") Long spId,
			@PathVariable("shId") Long shId,
			@PathVariable("al") Long alId,
			@PathVariable("bu") Long buId,
			@PathVariable("stId") Long stId,
			@PathVariable("metricTriggerTypes") List<MetricTriggerType> types,
			HttpServletRequest request) {

		ServiceStatus serviceStatus = null;
		ServiceResponse serviceResponse = new ServiceResponse();
		ServiceResult serviceResult = new ServiceResult();

		try {
			
			SalesPosition salesPosition = ModelAssembler.getSalesPosition(spId, shId, alId, buId, stId);
			List<MetricTriggerType> mTriggertypes = ModelAssembler.getMetricTypes(types);
					
			serviceResult.setDetail(metricResultService.getAllBaseMetricResults(salesPosition, mTriggertypes, ModelAssembler.getDefaultUserDetails()));
			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
		} catch (MetricServiceException e) {
			serviceResult.setDetail("Exception occured "+e.getMessage());
			serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR, "500 ERROR");
		}
		serviceResponse.setStatus(serviceStatus);
		serviceResponse.setResult(serviceResult);
		LOGGER.info(" Returning REST response with Result.."+serviceResult.getDetail()+" and status "+serviceResponse.getStatus().getCode().getCode()+"-"+serviceResponse.getStatus().getMessage());
		return serviceResponse;
	}*/
	
	/*@RequestMapping(value="/metric/allLastMetricResult/{spId}/{shId}/{al}/{bu}/{stId}/{metricTriggerTypes}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody ServiceResponse<List<MetricResult>> getAllLastMetricResults(@PathVariable("spId") Long spId,
			@PathVariable("shId") Long shId,
			@PathVariable("al") Long alId,
			@PathVariable("bu") Long buId,
			@PathVariable("stId") Long stId,
			@PathVariable("metricTriggerTypes") List<MetricTriggerType> types,
			HttpServletRequest request) {

		ServiceStatus serviceStatus = null;
		ServiceResponse serviceResponse = new ServiceResponse();
		ServiceResult serviceResult = new ServiceResult();

		try {
			
			SalesPosition salesPosition = ModelAssembler.getSalesPosition(spId, shId, alId, buId, stId);
			List<MetricTriggerType> mTriggertypes = ModelAssembler.getMetricTypes(types);
					
			serviceResult.setDetail(metricResultService.getAllLastMetricResults(salesPosition, mTriggertypes, ModelAssembler.getDefaultUserDetails()));
			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
		} catch (MetricServiceException e) {
			serviceResult.setDetail("Exception occured "+e.getMessage());
			serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR, "500 ERROR");
		}
		serviceResponse.setStatus(serviceStatus);
		serviceResponse.setResult(serviceResult);
		LOGGER.info(" Returning REST response with Result.."+serviceResult.getDetail()+" and status "+serviceResponse.getStatus().getCode().getCode()+"-"+serviceResponse.getStatus().getMessage());
		return serviceResponse;
	}*/
	
	@RequestMapping(value="/productByCustomer/{custId}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody ServiceResponse<List<Product>> getAllProductsByCustomer(@PathVariable("custId") Long custId,
			HttpServletRequest request) {

		ServiceStatus serviceStatus = null;
		ServiceResponse serviceResponse = new ServiceResponse();
		ServiceResult serviceResult = new ServiceResult();

		try {	
			//Alignment al = ModelAssembler.getAlignmentModel(alId,buId,stId);
			UserDetails userDetails = new UserDetails();
			userDetails.setTenantId((short) 1);
			List<Product> prodList = customerProdService.getProductsByCustomerId(custId, userDetails);
			serviceResult.setList(prodList);
			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
		} catch (CustomerServiceException e) {
			//serviceResult.setDetail("Exception occured "+e.getMessage());	
			serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR, "500 ERROR");
		}
		serviceResponse.setStatus(serviceStatus);
		serviceResponse.setResult(serviceResult);
		LOGGER.info(" Returning REST response with Result.."+serviceResult.getDetail()+" and status "+serviceResponse.getStatus().getCode().getCode()+"-"+serviceResponse.getStatus().getMessage());
		return serviceResponse;
	}
	
	@RequestMapping(value="/productByCustomerList/{custIds}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody ServiceResponse<List<CustomerProduct>> getAllProductsByCustomerList(@PathVariable("custIds") List<Long> custIds,
			HttpServletRequest request) {

		ServiceStatus serviceStatus = null;
		ServiceResponse serviceResponse = new ServiceResponse();
		ServiceResult serviceResult = new ServiceResult();

		try {	
			//Alignment al = ModelAssembler.getAlignmentModel(alId,buId,stId);
			UserDetails userDetails = new UserDetails();
			userDetails.setTenantId((short) 1);
			List<CustomerProduct> custprodList = customerProdService.getCustomerProducts(custIds, userDetails);
			serviceResult.setList(custprodList);
			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
		} catch (CustomerServiceException e) {
			//serviceResult.setDetail("Exception occured "+e.getMessage());	
			serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR, "500 ERROR");
		}
		serviceResponse.setStatus(serviceStatus);
		serviceResponse.setResult(serviceResult);
		LOGGER.info(" Returning REST response with Result.."+serviceResult.getDetail()+" and status "+serviceResponse.getStatus().getCode().getCode()+"-"+serviceResponse.getStatus().getMessage());
		return serviceResponse;
	}
	
	@RequestMapping(value="/productBySalesPos/{salesPosId}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody ServiceResponse<List<SalesPosition>> getAllProductsBySalesPosId(@PathVariable("salesPosId") Long salesPosId,
			HttpServletRequest request) {

		ServiceStatus serviceStatus = null;
		ServiceResponse serviceResponse = new ServiceResponse();
		ServiceResult serviceResult = new ServiceResult();

		try {	
			//Alignment al = ModelAssembler.getAlignmentModel(alId,buId,stId);
			SalesPosition salesPosition = new SalesPosition();
			salesPosition.setId(salesPosId);
			UserDetails userDetails = new UserDetails();
			userDetails.setTenantId((short) 1);
			List<CustomerProduct> custprodList = customerProdService.getProductsOfCustomersbySalesPositionId(salesPosition, userDetails);
			serviceResult.setList(custprodList);
			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
		} catch (CustomerServiceException e) {
			//serviceResult.setDetail("Exception occured "+e.getMessage());	
			serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR, "500 ERROR");
		}
		serviceResponse.setStatus(serviceStatus);
		serviceResponse.setResult(serviceResult);
		LOGGER.info(" Returning REST response with Result.."+serviceResult.getDetail()+" and status "+serviceResponse.getStatus().getCode().getCode()+"-"+serviceResponse.getStatus().getMessage());
		return serviceResponse;
	}
	
	@RequestMapping(value="/customer", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody ServiceResponse<Customer> getCustomerDetails(@RequestParam("custId") Long custId,
			HttpServletRequest request) {
		Date before = new Date();
		ServiceStatus serviceStatus = null;
		ServiceResponse<Customer> serviceResponse = new ServiceResponse<Customer>();
		ServiceResult<Customer> serviceResult = new ServiceResult<Customer>();

		try {
			UserDetails userDetails = new UserDetails();
			userDetails.setTenantId((short) 1);
			Customer customer = customerService.getCustomerDetails(custId, userDetails);
			serviceResult.setDetail(customer);
			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
			Date after = new Date();
			long diff = after.getTime() - before.getTime();
			LOGGER.info("time taken for the service: "+diff);
		} catch (CustomerServiceException e) {
			serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR, "500 ERROR");
		}
		serviceResponse.setStatus(serviceStatus);
		serviceResponse.setResult(serviceResult);
		LOGGER.info(" Returning REST response with Result.."+serviceResult.getDetail()+" and status "+serviceResponse.getStatus().getCode().getCode()+"-"+serviceResponse.getStatus().getMessage());
		return serviceResponse;
	}	

//	@RequestMapping(value="/customer/search", method=RequestMethod.GET, produces="application/json")
//	public @ResponseBody ServiceResponse<List<Customer>> searchCustomersByCriteria(@RequestParam("query") String query,
//			@RequestParam("startIndex") Integer startIndex,@RequestParam("limit") Integer limit,HttpServletRequest request) {
//		Date before = new Date();
//		ServiceStatus serviceStatus = null;
//		ServiceResponse serviceResponse = new ServiceResponse();
//		ServiceResult serviceResult = new ServiceResult();
//
//		try {
//			UserDetails userDetails = ModelAssembler.getDefaultUserDetails();
//			String[] paramValue = query.split(",");
//			ISearchCriteria mainCriteria = new SearchCriteria();
//			mainCriteria.addEqualTo(paramValue[0].split(":")[0], paramValue[0].split(":")[1]);
//			if(paramValue.length > 1){
//				for(int i=1;i<paramValue.length;i++){
//					ISearchCriteria criteria = new SearchCriteria();
//					criteria.addEqualTo(paramValue[i].split(":")[0], paramValue[i].split(":")[1]);
//					mainCriteria.addAndCriteria(criteria);
//				}
//			}
//
//			//			for(String paramValue : query.split(",")){
//			//				ISearchCriteria criteria = new SearchCriteria();
//			//				criteria.addEqualTo(paramValue.split(":")[0], paramValue.split(":")[1]);
//			//				mainCriteria.addAndCriteria(criteria);
//			//			}
//			mainCriteria.addLimit(startIndex, limit);
//			List<Customer> customerList = customerService.searchCustomersByCriteria(mainCriteria, null, userDetails);
//			serviceResult.setList(customerList);
//			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
//			Date after = new Date();
//			long diff = after.getTime() - before.getTime();
//			LOGGER.info("time taken for the service: "+diff);
//		} catch (CustomerServiceException e) {
//			serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR, "500 ERROR");
//		}
//		serviceResponse.setStatus(serviceStatus);
//		serviceResponse.setResult(serviceResult);
//		LOGGER.info(" Returning REST response with Result.."+serviceResult.getList()+" and status "+serviceResponse.getStatus().getCode().getCode()+"-"+serviceResponse.getStatus().getMessage());
//		return serviceResponse;
//	}

//	@RequestMapping(value="/customer/getCount", method=RequestMethod.GET, produces="application/json")
//	public @ResponseBody ServiceResponse<Long> getCustomerCountByCriteria(@RequestParam("query") String query,
//			HttpServletRequest request) {
//		Date before = new Date();
//		ServiceStatus serviceStatus = null;
//		ServiceResponse serviceResponse = new ServiceResponse();
//		ServiceResult serviceResult = new ServiceResult();
//
//		try {
//			UserDetails userDetails = ModelAssembler.getDefaultUserDetails();
//			String[] paramValue = query.split(",");
//			ISearchCriteria mainCriteria = new SearchCriteria();
//			mainCriteria.addEqualTo(paramValue[0].split(":")[0], paramValue[0].split(":")[1]);
//			if(paramValue.length > 1){
//				for(int i=1;i<paramValue.length;i++){
//					ISearchCriteria criteria = new SearchCriteria();
//					criteria.addEqualTo(paramValue[i].split(":")[0], paramValue[i].split(":")[1]);
//					mainCriteria.addAndCriteria(criteria);
//				}
//			}
//			Long customerCount = customerService.getCustomerCountByCriteria(mainCriteria, userDetails);
//			serviceResult.setDetail(customerCount);
//			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
//			Date after = new Date();
//			long diff = after.getTime() - before.getTime();
//			LOGGER.info("time taken for the service: "+diff);
//		} catch (CustomerServiceException e) {
//			serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR, "500 ERROR");
//		}
//		serviceResponse.setStatus(serviceStatus);
//		serviceResponse.setResult(serviceResult);
//		LOGGER.info(" Returning REST response with Result.."+serviceResult.getDetail()+" and status "+serviceResponse.getStatus().getCode().getCode()+"-"+serviceResponse.getStatus().getMessage());
//		return serviceResponse;
//	}

	@RequestMapping(value="/common/getDefaultCountry", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody ServiceResponse<Country> getDefaultCountry(HttpServletRequest request) {
		ServiceStatus serviceStatus = null;
		ServiceResponse<Country> serviceResponse = new ServiceResponse<Country>();
		ServiceResult<Country> serviceResult = new ServiceResult<Country>();

		try {
			UserDetails userDetails = ModelAssembler.getDefaultUserDetails();
			Country country = commonService.getDefaultCountry(userDetails);
			serviceResult.setDetail(country);
			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
		} catch (Exception e) {
			serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR, "500 ERROR");
		}
		serviceResponse.setStatus(serviceStatus);
		serviceResponse.setResult(serviceResult);
		LOGGER.info(" Returning REST response with Result.."+serviceResult.getDetail()+" and status "+serviceResponse.getStatus().getCode().getCode()+"-"+serviceResponse.getStatus().getMessage());
		return serviceResponse;
	}
	
	@RequestMapping(value="/common/getLowestLookupTable", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody ServiceResponse<CountryDivision> getLowestLookupTableForDefaultCountry(HttpServletRequest request) {
		ServiceStatus serviceStatus = null;
		ServiceResponse<CountryDivision> serviceResponse = new ServiceResponse<CountryDivision>();
		ServiceResult<CountryDivision> serviceResult = new ServiceResult<CountryDivision>();

		try {
			UserDetails userDetails = ModelAssembler.getDefaultUserDetails();
			CountryDivision countryDivision = commonService.getLowestCountryDivision(userDetails);
			serviceResult.setDetail(countryDivision);
			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
		} catch (Exception e) {
			serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR, "500 ERROR");
		}
		serviceResponse.setStatus(serviceStatus);
		serviceResponse.setResult(serviceResult);
		LOGGER.info(" Returning REST response with Result.."+serviceResult.getDetail()+" and status "+serviceResponse.getStatus().getCode().getCode()+"-"+serviceResponse.getStatus().getMessage());
		return serviceResponse;
	}
	
	@RequestMapping(value="/businessRule/getCustomerMovementFlag/{alId}/{buId}/{stId}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody ServiceResponse<Boolean> isCustomerMovementAllowed(@PathVariable("alId") Long alId,
			@PathVariable("buId") Long buId,@PathVariable("stId") Long stId,HttpServletRequest request) {
		ServiceStatus serviceStatus = null;
		ServiceResponse<Boolean> serviceResponse = new ServiceResponse<Boolean>();
		ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();

		try {
			UserDetails userDetails = ModelAssembler.getDefaultUserDetails();
			Alignment alignment = ModelAssembler.getAlignmentModel(alId, buId, stId);
			Boolean customerMovementFlag = businessRuleService.isCustomerMovementAllowed(alignment, userDetails);
			serviceResult.setDetail(customerMovementFlag);
			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
		} catch (Exception e) {
			serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR, "500 ERROR");
		}
		serviceResponse.setStatus(serviceStatus);
		serviceResponse.setResult(serviceResult);
		LOGGER.info(" Returning REST response with Result.."+serviceResult.getDetail()+" and status "+serviceResponse.getStatus().getCode().getCode()+"-"+serviceResponse.getStatus().getMessage());
		return serviceResponse;
	}
	
	@RequestMapping(value="/businessRule/getContiguityFlag/{alId}/{buId}/{stId}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody ServiceResponse<Boolean> isContiguityCheckEnabled(@PathVariable("alId") Long alId,
			@PathVariable("buId") Long buId,@PathVariable("stId") Long stId,HttpServletRequest request) {
		ServiceStatus serviceStatus = null;
		ServiceResponse<Boolean> serviceResponse = new ServiceResponse<Boolean>();
		ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();

		try {
			UserDetails userDetails = ModelAssembler.getDefaultUserDetails();
			Alignment alignment = ModelAssembler.getAlignmentModel(alId, buId, stId);
			Boolean customerMovementFlag = businessRuleService.isContiguityCheckEnabled(alignment, userDetails);
			serviceResult.setDetail(customerMovementFlag);
			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
		} catch (Exception e) {
			serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR, "500 ERROR");
		}
		serviceResponse.setStatus(serviceStatus);
		serviceResponse.setResult(serviceResult);
		LOGGER.info(" Returning REST response with Result.."+serviceResult.getDetail()+" and status "+serviceResponse.getStatus().getCode().getCode()+"-"+serviceResponse.getStatus().getMessage());
		return serviceResponse;
	}



	
	
}
