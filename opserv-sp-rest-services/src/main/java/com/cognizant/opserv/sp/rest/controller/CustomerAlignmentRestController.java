package com.cognizant.opserv.sp.rest.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cognizant.opserv.sp.common.CallPlanType;
import com.cognizant.opserv.sp.common.PriorityType;
import com.cognizant.opserv.sp.common.util.DateUtil;
import com.cognizant.opserv.sp.cr.process.dto.CustomerAlignmentDTO;
import com.cognizant.opserv.sp.cr.process.internal.service.CustomerAlignmentValidationService;
import com.cognizant.opserv.sp.exception.AffiliationServiceException;
import com.cognizant.opserv.sp.exception.AlignmentServiceException;
import com.cognizant.opserv.sp.exception.CallPlanServiceException;
import com.cognizant.opserv.sp.exception.ChangeRequestServiceException;
import com.cognizant.opserv.sp.exception.ChangeRequestServiceException.ChangeRequestServiceExceptionCode;
import com.cognizant.opserv.sp.exception.CustomerServiceException;
import com.cognizant.opserv.sp.exception.MetricViolationException;
import com.cognizant.opserv.sp.exception.SalesPositionServiceException;
import com.cognizant.opserv.sp.exception.CallPlanServiceException.CallPlanServiceExceptionCode;
import com.cognizant.opserv.sp.exception.ViewServiceException;
import com.cognizant.opserv.sp.model.Alignment;
import com.cognizant.opserv.sp.model.BusinessReason;
import com.cognizant.opserv.sp.model.BusinessUnit;
import com.cognizant.opserv.sp.model.Customer;
import com.cognizant.opserv.sp.model.CustomerAlignment;
import com.cognizant.opserv.sp.model.CustomerCallPlan;
import com.cognizant.opserv.sp.model.CustomerProductAlignment;
import com.cognizant.opserv.sp.model.Product;
import com.cognizant.opserv.sp.model.SalesOrgHierarchy;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.model.SalesTeam;
import com.cognizant.opserv.sp.model.OfflineRequest;
import com.cognizant.opserv.sp.model.attrb.AttributeDefinition;
import com.cognizant.opserv.sp.model.attrb.ExtdAttribue;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.model.cr.ChangeRequest;
import com.cognizant.opserv.sp.persistence.dao.service.ChangeRequestDAOService;
import com.cognizant.opserv.sp.persistence.dao.service.ChangeRequestOfflineDAOService;
import com.cognizant.opserv.sp.rest.util.ModelAssembler;
import com.cognizant.opserv.sp.service.alignment.CustomerAlignmentService;
import com.cognizant.opserv.sp.service.changereq.ChangeRequestService;
import com.cognizant.opserv.sp.service.internal.CustomerAlignmnetIntService;
import com.cognizant.peg.core.exception.OpservDataAccessException;
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
public class CustomerAlignmentRestController {
	/*
	 * Instantiate the Logger
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(CustomerAlignmentRestController.class);

	@Autowired
	private CustomerAlignmentService custAlignService;
	
	@Autowired
	private CustomerAlignmnetIntService customerAlignmnetIntService;
	
	@Autowired
	private ChangeRequestOfflineDAOService chngreqOfflineDAOService;
	
	@Autowired
	private ChangeRequestService changeRequestService;
	
	@Autowired
	private ChangeRequestDAOService changeRequestDAOService;
	
	@Autowired
	private CustomerAlignmentValidationService customerAlignmentValidationService;
	
	@RequestMapping(value="/customer/alignment/{custId}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody ServiceResponse<List<CustomerAlignment>> getAllCustomerAlignments(@PathVariable("custId") Long custId, HttpServletRequest request) {

		ServiceStatus serviceStatus = null;
		ServiceResponse serviceResponse = new ServiceResponse();
		ServiceResult serviceResult = new ServiceResult();

		try {
			serviceResult.setList(custAlignService.getAllCustomerAlignments(custId,ModelAssembler.getDefaultUserDetails()));
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
	
	@RequestMapping(value="/customer/alignment/{custId}/{buId}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody ServiceResponse<List<CustomerAlignment>> getAllCustomerAlignmentsBuId(@PathVariable("custId") Long custId,@PathVariable("buId") Long buId, HttpServletRequest request) {

		ServiceStatus serviceStatus = null;
		ServiceResponse serviceResponse = new ServiceResponse();
		ServiceResult serviceResult = new ServiceResult();

		try {
			serviceResult.setList(custAlignService.getAllCustomerAlignments(custId,buId,ModelAssembler.getDefaultUserDetails()));
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
	
	@RequestMapping(value="/customer/alignment/{al}/{bu}/{st}/{sp}/{hier}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody ServiceResponse<List<CustomerAlignment>> getAllCustomerAlignmentsBySalesPosition(@PathVariable("al") Long alId,
			@PathVariable("bu") Long buId,
			@PathVariable("st") Long stId,
			@PathVariable("sp") Long spId,
			@PathVariable("hier") Long hierId, HttpServletRequest request) throws CustomerServiceException {

		ServiceStatus serviceStatus = null;
		ServiceResponse serviceResponse = new ServiceResponse();
		ServiceResult serviceResult = new ServiceResult();

		try {
			UserDetails userDetails = ModelAssembler.getDefaultUserDetails();
			userDetails.setLocaleId("en_US");
			Alignment alignment = ModelAssembler.getAlignmentModel(alId,buId,stId);
			SalesPosition salesPosition = ModelAssembler.getSalesPosition(spId, hierId);
			salesPosition.setAlignmment(alignment);
			serviceResult.setList(custAlignService.getAllCustomerAlignmentsBySalesPosition(salesPosition,userDetails));
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

	@RequestMapping(value = "/customer/alignment/push/{al}/{bu}/{st}/{srcsp}/{srcsh}/{tarsp}/{tarsh}/{custId}/{srccustALId}/{isImplicitAssignment}/{isaffBased}/{isTargetted}/{prtType}/{startdate}/{endDate}/{comments}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody ServiceResponse<List<CustomerAlignment>> pushCustomer(
			@PathVariable("al") Long alId, @PathVariable("bu") Long buId,
			@PathVariable("st") Long stId, @PathVariable("srcsp") Long srcsp,
			@PathVariable("srcsh") Long srcsh,
			@PathVariable("tarsp") Long tarsp,
			@PathVariable("tarsh") Long tarsh,
			@PathVariable("custId") Long custId,
			@PathVariable("srccustALId") Long custALId,
			@PathVariable("isImplicitAssignment") Boolean isImplicitAssignment,
			@PathVariable("isaffBased") Boolean isaffBased,
			@PathVariable("isTargetted") Boolean isTargetted,
			@PathVariable("prtType") String prtType,
			@PathVariable("startdate") String startdate,
			@PathVariable("endDate") String endDate,
			@PathVariable("comments") String comments,
			HttpServletRequest request) throws MetricViolationException,
			CustomerServiceException, AffiliationServiceException,
			SalesPositionServiceException, ChangeRequestServiceException,
			CallPlanServiceException, ParseException, ViewServiceException {

		ServiceStatus serviceStatus = null;
		ServiceResponse serviceResponse = new ServiceResponse();
		ServiceResult serviceResult = new ServiceResult();

		try {

			CustomerAlignment orgCustAlgn = new CustomerAlignment();
			CustomerAlignment newCustAlgn = new CustomerAlignment();
			Customer oldCustomer = new Customer();
			Customer newCustomer = new Customer();
			UserDetails userDetails = ModelAssembler.getDefaultUserDetails();
			userDetails.setUserId(80);

			// Source Customer Alignment Data
			Alignment alignment = ModelAssembler.getAlignmentModel(alId, buId,
					stId);
			SalesPosition srcsalesPosition = ModelAssembler.getSalesPosition(
					srcsp, srcsh);
			srcsalesPosition.setAlignmment(alignment);
			oldCustomer.setId(custId);
			orgCustAlgn.setId(custALId);
			orgCustAlgn.setCustomer(oldCustomer);
			orgCustAlgn.setSalesPosition(srcsalesPosition);
			orgCustAlgn.setAffBasedAssignment(isaffBased);
			orgCustAlgn.setCustomerTargeted(isTargetted);
			orgCustAlgn.setPriority(PriorityType.valueOf(prtType));
			orgCustAlgn.setImplicitAssignment(isImplicitAssignment);

			// Target Customer Alignment Data
			newCustomer.setId(custId);
			newCustAlgn.setCustomer(newCustomer);
			newCustAlgn.setStartDate(DateUtil.getDefaultFormatDate(startdate));
			newCustAlgn.setEndDate(DateUtil.getDefaultFormatDate(endDate));
			
			SalesPosition tarsalesPosition = ModelAssembler.getSalesPosition(
					tarsp, tarsh);
			tarsalesPosition.setAlignmment(alignment);
			newCustAlgn.setSalesPosition(tarsalesPosition);

			serviceResult.setDetail(custAlignService.pushCustomer(orgCustAlgn,
					newCustAlgn, comments, userDetails));
			
			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
		} catch (AlignmentServiceException e) {
			serviceResult.setDetail("Exception occured " + e.getMessage());
			serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR,
					"500 ERROR");
		}
		serviceResponse.setStatus(serviceStatus);
		serviceResponse.setResult(serviceResult);
		LOGGER.info(" Returning REST response with Result.."
				+ serviceResult.getDetail() + " and status "
				+ serviceResponse.getStatus().getCode().getCode() + "-"
				+ serviceResponse.getStatus().getMessage());
		return serviceResponse;
	}
		
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/customer/alignment/pushCustomerNew/{al}/{bu}/{st}/{srcsp}/{srcsh}/{tarsp}/{tarsh}/{custId}/{srccustALId}/{isImplicitAssignment}/{isaffBased}/{isTargetted}/{prtType}/{startdate}/{endDate}/{comments}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody ServiceResponse<String> pushCustomerNew(
			@PathVariable("al") Long alId, @PathVariable("bu") Long buId,
			@PathVariable("st") Long stId, @PathVariable("srcsp") Long srcsp,
			@PathVariable("srcsh") Long srcsh,
			@PathVariable("tarsp") Long tarsp,
			@PathVariable("tarsh") Long tarsh,
			@PathVariable("custId") Long custId,
			@PathVariable("srccustALId") Long custALId,
			@PathVariable("isImplicitAssignment") Boolean isImplicitAssignment,
			@PathVariable("isaffBased") Boolean isaffBased,
			@PathVariable("isTargetted") Boolean isTargetted,
			@PathVariable("prtType") String prtType,
			@PathVariable("startdate") String startdate,
			@PathVariable("endDate") String endDate,
			@PathVariable("comments") String comments,
			HttpServletRequest request) throws MetricViolationException,
			CustomerServiceException, AffiliationServiceException,
			SalesPositionServiceException, ChangeRequestServiceException,
			CallPlanServiceException, ParseException, ViewServiceException {

		ServiceStatus serviceStatus = null;
		ServiceResponse<String> serviceResponse = new ServiceResponse<String>();
		@SuppressWarnings("rawtypes")
		ServiceResult serviceResult = new ServiceResult();

		try {

			CustomerAlignment orgCustAlgn = new CustomerAlignment();
			CustomerAlignment newCustAlgn = new CustomerAlignment();
			Customer oldCustomer = new Customer();
			Customer newCustomer = new Customer();
			UserDetails userDetails = ModelAssembler.getDefaultUserDetails();
			userDetails.setUserId(80);

			// Source Customer Alignment Data
			Alignment alignment = ModelAssembler.getAlignmentModel(alId, buId,
					stId);
			SalesPosition srcsalesPosition = ModelAssembler.getSalesPosition(
					srcsp, srcsh);
			srcsalesPosition.setAlignmment(alignment);
			oldCustomer.setId(custId);
			orgCustAlgn.setId(custALId);
			orgCustAlgn.setCustomer(oldCustomer);
			orgCustAlgn.setSalesPosition(srcsalesPosition);
			orgCustAlgn.setAffBasedAssignment(isaffBased);
			orgCustAlgn.setCustomerTargeted(isTargetted);
			orgCustAlgn.setPriority(PriorityType.valueOf(prtType));
			orgCustAlgn.setImplicitAssignment(isImplicitAssignment);

			// Target Customer Alignment Data
			newCustomer.setId(custId);
			newCustAlgn.setCustomer(newCustomer);
			newCustAlgn.setStartDate(DateUtil.getDefaultFormatDate(startdate));
			newCustAlgn.setEndDate(DateUtil.getDefaultFormatDate(endDate));
			
			SalesPosition tarsalesPosition = ModelAssembler.getSalesPosition(
					tarsp, tarsh);
			tarsalesPosition.setAlignmment(alignment);
			newCustAlgn.setSalesPosition(tarsalesPosition);
			
			ChangeRequest chngReq = changeRequestService.generatePushCustomerCR(srcsalesPosition, tarsalesPosition, userDetails);
			
			custAlignService.pushCustomer(chngReq, orgCustAlgn, newCustAlgn, comments, userDetails);
			
			serviceResult.setDetail("ChangeRequest Id: " + chngReq.getId());
			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
			
		} catch (AlignmentServiceException e) {
			serviceResult.setDetail("Exception occured " + e.getMessage());
			serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR,
					"500 ERROR");
		}
		serviceResponse.setStatus(serviceStatus);
		serviceResponse.setResult(serviceResult);
		LOGGER.info(" Returning REST response with Result.."
				+ serviceResult.getDetail() + " and status "
				+ serviceResponse.getStatus().getCode().getCode() + "-"
				+ serviceResponse.getStatus().getMessage());
		return serviceResponse;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/customer/alignment/pushCustomerSubmit/{crId}/{custId}/{srccustALId}/{isImplicitAssignment}/{isaffBased}/{isTargetted}/{prtType}/{startdate}/{endDate}/{comments}/{userId}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody ServiceResponse<String> pushCustomerSubmit(
			@PathVariable("crId") Long crId,
			@PathVariable("custId") Long custId,
			@PathVariable("srccustALId") Long custALId,
			@PathVariable("isImplicitAssignment") Boolean isImplicitAssignment,
			@PathVariable("isaffBased") Boolean isaffBased,
			@PathVariable("isTargetted") Boolean isTargetted,
			@PathVariable("prtType") String prtType,
			@PathVariable("startdate") String startdate,
			@PathVariable("endDate") String endDate,
			@PathVariable("comments") String comments,
			@PathVariable("userId") Integer userId,
			HttpServletRequest request) throws MetricViolationException,
			CustomerServiceException, AffiliationServiceException,
			SalesPositionServiceException, ChangeRequestServiceException,
			CallPlanServiceException, ParseException, ViewServiceException {

		ServiceStatus serviceStatus = null;
		ServiceResponse<String> serviceResponse = new ServiceResponse<String>();
		@SuppressWarnings("rawtypes")
		ServiceResult serviceResult = new ServiceResult();

		try {
			
			ChangeRequest chngReq = new ChangeRequest();
			chngReq.setId(crId);
			try {
				chngReq = changeRequestDAOService
						.getChangeRequestByChangeRequestId(chngReq);

			} catch (OpservDataAccessException e) {
				Object[] args = new Object[] { "chngReq" };
				throw new ChangeRequestServiceException(
						ChangeRequestServiceExceptionCode.CR_SER_EX_CD_016,
						"Exception while fetching change Request details / Change Request Id is Null", args, e);
			}
			// Source Customer Alignment Data
			Alignment alignment = ModelAssembler.getAlignmentModel(chngReq.getRequestedSalesPosition().getAlignmment().getId(),
					chngReq.getRequestedSalesPosition().getAlignmment().getSalesTeam().getBusinessUnit().getId(),
					chngReq.getRequestedSalesPosition().getAlignmment().getSalesTeam().getId());
			
			SalesPosition srcsalesPosition = ModelAssembler.getSalesPosition(chngReq.getRequestedSalesPosition().getId(),
					chngReq.getRequestedSalesPosition().getHierarchy().getId());
			
			CustomerAlignment orgCustAlgn = new CustomerAlignment();
			CustomerAlignment newCustAlgn = new CustomerAlignment();
			Customer oldCustomer = new Customer();
			Customer newCustomer = new Customer();
			
			srcsalesPosition.setAlignmment(alignment);
			oldCustomer.setId(custId);
			orgCustAlgn.setId(custALId);
			orgCustAlgn.setCustomer(oldCustomer);
			orgCustAlgn.setSalesPosition(srcsalesPosition);
			orgCustAlgn.setAffBasedAssignment(isaffBased);
			orgCustAlgn.setCustomerTargeted(isTargetted);
			orgCustAlgn.setPriority(PriorityType.valueOf(prtType));
			orgCustAlgn.setImplicitAssignment(isImplicitAssignment);

			// Target Customer Alignment Data
			newCustomer.setId(custId);
			newCustAlgn.setCustomer(newCustomer);
			newCustAlgn.setStartDate(DateUtil.getDefaultFormatDate(startdate));
			newCustAlgn.setEndDate(DateUtil.getDefaultFormatDate(endDate));
			
			SalesPosition tarsalesPosition = ModelAssembler.getSalesPosition(chngReq.getRaisedSalesPosition().getId(),
					chngReq.getRaisedSalesPosition().getHierarchy().getId());
			
			tarsalesPosition.setAlignmment(alignment);
			newCustAlgn.setSalesPosition(tarsalesPosition);
			
			UserDetails userDetails = ModelAssembler.getDefaultUserDetails();
			userDetails.setUserId(userId);
			
			custAlignService.pushCustomer(chngReq, orgCustAlgn, newCustAlgn, comments, userDetails);
			
			serviceResult.setDetail("ChangeRequest Id: " + chngReq.getId());
			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
			
		} catch (AlignmentServiceException e) {
			serviceResult.setDetail("Exception occured " + e.getMessage());
			serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR,
					"500 ERROR");
		} catch(ChangeRequestServiceException ec){
			serviceResult.setDetail("Exception occured " + ec.getMessage());
			serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR,
					"500 ERROR");
		}
		serviceResponse.setStatus(serviceStatus);
		serviceResponse.setResult(serviceResult);
		LOGGER.info(" Returning REST response with Result.."
				+ serviceResult.getDetail() + " and status "
				+ serviceResponse.getStatus().getCode().getCode() + "-"
				+ serviceResponse.getStatus().getMessage());
		return serviceResponse;
	}
	
	/*@RequestMapping(value = "/customer/alignment/updateDocStoreFrPushPull/{al}/{bu}/{st}/{srcsp}/{srcsh}/{tarsp}/{tarsh}/{custId}/{srccustALId}/{isImplicitAssignment}/{isaffBased}/{isTargetted}/{prtType}/{startdate}/{endDate}/{mirSalesPos}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody ServiceResponse<List<CustomerAlignment>> updateDocStoreFrPushPull(
			@PathVariable("al") Long alId, @PathVariable("bu") Long buId,
			@PathVariable("st") Long stId, @PathVariable("srcsp") Long srcsp,
			@PathVariable("srcsh") Long srcsh,
			@PathVariable("tarsp") Long tarsp,
			@PathVariable("tarsh") Long tarsh,
			@PathVariable("custId") Long custId,
			@PathVariable("srccustALId") Long custALId,
			@PathVariable("isImplicitAssignment") Boolean isImplicitAssignment,
			@PathVariable("isaffBased") Boolean isaffBased,
			@PathVariable("isTargetted") Boolean isTargetted,
			@PathVariable("prtType") String prtType,
			@PathVariable("startdate") String startdate,
			@PathVariable("endDate") String endDate,
			@PathVariable("mirSalesPos") String mirSalesPos,
			HttpServletRequest request) throws MetricViolationException,
			CustomerServiceException, AffiliationServiceException,
			SalesPositionServiceException, ChangeRequestServiceException,
			CallPlanServiceException, ParseException, AlignmentServiceException {

		ServiceStatus serviceStatus = null;
		ServiceResponse serviceResponse = new ServiceResponse();
		ServiceResult serviceResult = new ServiceResult();

		CustomerAlignment orgCustAlgn = new CustomerAlignment();
		CustomerAlignment newCustAlgn = new CustomerAlignment();
		Customer oldCustomer = new Customer();
		Customer newCustomer = new Customer();
		UserDetails userDetails = ModelAssembler.getDefaultUserDetails();
		userDetails.setUserId(80);

		// Source Customer Alignment Data
		Alignment alignment = ModelAssembler.getAlignmentModel(alId, buId,
				stId);
		SalesPosition srcsalesPosition = ModelAssembler.getSalesPosition(
				srcsp, srcsh);
		srcsalesPosition.setAlignmment(alignment);
		oldCustomer.setId(custId);
		orgCustAlgn.setId(custALId);
		orgCustAlgn.setCustomer(oldCustomer);
		orgCustAlgn.setSalesPosition(srcsalesPosition);
		orgCustAlgn.setAffBasedAssignment(isaffBased);
		orgCustAlgn.setCustomerTargeted(isTargetted);
		orgCustAlgn.setPriority(PriorityType.valueOf(prtType));
		orgCustAlgn.setImplicitAssignment(isImplicitAssignment);

		// Target Customer Alignment Data
		newCustomer.setId(custId);
		newCustAlgn.setCustomer(newCustomer);
		newCustAlgn.setStartDate(DateUtil.getDefaultFormatDate(startdate));
		newCustAlgn.setEndDate(DateUtil.getDefaultFormatDate(endDate));
		
		SalesPosition tarsalesPosition = ModelAssembler.getSalesPosition(
				tarsp, tarsh);
		tarsalesPosition.setAlignmment(alignment);
		newCustAlgn.setSalesPosition(tarsalesPosition);
		
		List<List<SalesPosition>> mirSalesPosList = new ArrayList<List<SalesPosition>>();
		
		List<String> mirSPLt= Arrays.asList(mirSalesPos.split("\\s*,\\s*"));
		
		List<SalesPosition> mirSrcSpList= new ArrayList<SalesPosition>();
		List<SalesPosition> mirTarSpList= new ArrayList<SalesPosition>();
		
		Alignment algmnt = ModelAssembler.getAlignmentModel(Long.parseLong(mirSPLt.get(0)), Long.parseLong(mirSPLt.get(1)),Long.parseLong(mirSPLt.get(2)));
		SalesPosition mirSrcSP = ModelAssembler.getSalesPosition(
				Long.parseLong(mirSPLt.get(3)), Long.parseLong(mirSPLt.get(4)));
		mirSrcSP.setAlignmment(algmnt);
		
		SalesPosition mirTarSP = ModelAssembler.getSalesPosition(
				Long.parseLong(mirSPLt.get(5)), Long.parseLong(mirSPLt.get(6)));
		mirTarSP.setAlignmment(algmnt);
		
		mirSrcSpList.add(mirSrcSP);
		mirTarSpList.add(mirTarSP);
		
		mirSalesPosList.add(mirSrcSpList);
		mirSalesPosList.add(mirTarSpList);
		long startTime=System.currentTimeMillis();
		customerAlignmnetIntService.updateDocStoreFrPushPull(orgCustAlgn,newCustAlgn,userDetails,mirSalesPosList);
		long endTime=System.currentTimeMillis();
		long totalTime = endTime-startTime;
		System.out.println("+++++++++++++++Time taken========"+totalTime);
		serviceResult.setDetail("Mirrored");
		serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
		serviceResponse.setStatus(serviceStatus);
		serviceResponse.setResult(serviceResult);
		LOGGER.info(" Returning REST response with Result.."
				+ serviceResult.getDetail() + " and status "
				+ serviceResponse.getStatus().getCode().getCode() + "-"
				+ serviceResponse.getStatus().getMessage());
		return serviceResponse;
	}*/
	
	

	@SuppressWarnings("unchecked")
	@RequestMapping(value="/customer/alignment/editcustomer/{custId}/{custAlId}/{al}/{bu}/{st}/{sp}/{sh}/{attrname}/{attrValue}/{plannedCalls}/{prdId}/{businessReasonId}/{isActive}/{startDate}/{endDate}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody ServiceResponse<List<CustomerAlignment>> editCustomerAlignmentDetails(@PathVariable("custId") Long custId,@PathVariable("custAlId") Long custAlId,
		   @PathVariable("al") Long alId, @PathVariable("bu") Long buId,@PathVariable("st") Long stId, @PathVariable("sp") Long sp,@PathVariable("sh") Long sh,
		   @PathVariable("attrname") String attrname,@PathVariable("attrValue") String attrValue,
		   @PathVariable("plannedCalls") int plannedCalls,@PathVariable("prdId") Long prdId,
		   @PathVariable("businessReasonId") long businessReasonId, @PathVariable("isActive") Boolean isActive,
		   @PathVariable("startDate") String startDate,@PathVariable("endDate") String endDate,
		   HttpServletRequest request) 
			throws MetricViolationException, CustomerServiceException, AffiliationServiceException,
			SalesPositionServiceException, ChangeRequestServiceException, CallPlanServiceException, ParseException, ViewServiceException {
		
		ServiceStatus serviceStatus = null;
		ServiceResponse serviceResponse = new ServiceResponse();
		ServiceResult serviceResult = new ServiceResult();

		try {
			CustomerAlignment newCustomerAlignment = new CustomerAlignment(); 
			
			CustomerAlignment oldCustomerAlignment = new CustomerAlignment(); 
			
			List<CustomerProductAlignment> newCustomerProductAlignmentList = new ArrayList<CustomerProductAlignment>();
			List<CustomerProductAlignment> oldCustomerProductAlignmentList = new ArrayList<CustomerProductAlignment>();
			
			UserDetails userDetails = ModelAssembler.getDefaultUserDetails();
			userDetails.setLocaleId("en_US");
			userDetails.setUserId(80);
		
			Customer customer = new Customer(); 
			customer.setId(custId);
			newCustomerAlignment.setCustomer(customer);

			SalesPosition salesPosition = new SalesPosition();
			SalesOrgHierarchy hierarchy = new SalesOrgHierarchy();
			
			Alignment alignment = new Alignment();
			SalesTeam salesTeam = new SalesTeam();
			BusinessUnit businessUnit = new BusinessUnit();
			CustomerCallPlan customerCallPlan = new CustomerCallPlan();
			BusinessReason businessReason = new BusinessReason();
			businessReason.setId(businessReasonId);
			
			hierarchy.setId(sh);
			salesPosition.setId(sp);
			alignment.setId(alId);
			salesTeam.setId(stId);
			businessUnit.setId(buId);
			
			salesPosition.setHierarchy(hierarchy);
			salesTeam.setBusinessUnit(businessUnit);
			alignment.setSalesTeam(salesTeam);
			salesPosition.setAlignmment(alignment);
			
			
			newCustomerAlignment.setSalesPosition(salesPosition);
			
			
			
			hierarchy.setId(sh);
			salesPosition.setId(sp);
			alignment.setId(alId);
			salesTeam.setId(stId);
			businessUnit.setId(buId);
			
			salesPosition.setHierarchy(hierarchy);
			salesTeam.setBusinessUnit(businessUnit);
			alignment.setSalesTeam(salesTeam);
			salesPosition.setAlignmment(alignment);
			
			
			List<ExtdAttribue> list = new ArrayList<ExtdAttribue>();  
			
			ExtdAttribue extdAttribue = new ExtdAttribue();
			AttributeDefinition attrDef=new AttributeDefinition();
			attrDef.setName(attrname);
			extdAttribue.setDefinition(attrDef);
			extdAttribue.setValue(attrValue);
			list.add(extdAttribue);
			
			ExtdAttribue extdAttribue2 = new ExtdAttribue();
			AttributeDefinition attrDef2=new AttributeDefinition();
			attrDef2.setName("attr_2");
			extdAttribue2.setDefinition(attrDef2);
			extdAttribue2.setValue("232");
			list.add(extdAttribue2);
			
			ExtdAttribue extdAttribue3 = new ExtdAttribue();
			AttributeDefinition attrDef3=new AttributeDefinition();
			attrDef3.setName("attr_16");
			extdAttribue3.setDefinition(attrDef3);
			extdAttribue3.setValue("555");
			list.add(extdAttribue3);
			
			
			List<ExtdAttribue> custPrdList = new ArrayList<ExtdAttribue>();  
			
			ExtdAttribue custPrdExtdAtt = new ExtdAttribue();
			AttributeDefinition custPrdAttrDef=new AttributeDefinition();
			custPrdAttrDef.setName(attrname);
			custPrdExtdAtt.setDefinition(custPrdAttrDef);
			custPrdExtdAtt.setValue(attrValue);
			custPrdList.add(custPrdExtdAtt);
			
			ExtdAttribue custPrdExtdAtt2 = new ExtdAttribue();
			AttributeDefinition custPrdAttrDef2=new AttributeDefinition();
			custPrdAttrDef2.setName("attr_2");
			custPrdExtdAtt2.setDefinition(custPrdAttrDef2);
			custPrdExtdAtt2.setValue("232");
			custPrdList.add(custPrdExtdAtt2);
			
			ExtdAttribue custPrdExtdAtt3 = new ExtdAttribue();
			AttributeDefinition custPrdAttrDef3=new AttributeDefinition();
			custPrdAttrDef3.setName("attr_16");
			custPrdExtdAtt3.setDefinition(custPrdAttrDef3);
			custPrdExtdAtt3.setValue("555");
			custPrdList.add(custPrdExtdAtt3);
			
			Product product1 = new Product();
			product1.setId(prdId);
			CustomerProductAlignment newCustomerProductAlignment = new CustomerProductAlignment();
			newCustomerProductAlignment.setProduct(product1);
			newCustomerProductAlignment.setCustomer(customer);
			newCustomerProductAlignment.setExtdAttributes(custPrdList);
			newCustomerProductAlignmentList.add(newCustomerProductAlignment);
			
			
			Product product2 = new Product();
			product2.setId(228L);
			CustomerProductAlignment nwCustomerProductAlignment2 = new CustomerProductAlignment();
			nwCustomerProductAlignment2.setProduct(product2);
			nwCustomerProductAlignment2.setCustomer(customer);
			nwCustomerProductAlignment2.setExtdAttributes(custPrdList);
			newCustomerProductAlignmentList.add(nwCustomerProductAlignment2);
			
			
			
		    customerCallPlan.setPlannedCalls(plannedCalls);
			//customerCallPlan.setCustomerAlgn(newcustomerAlignment);
		    newCustomerAlignment.setCustomerCallPlan(customerCallPlan);
			
		    newCustomerAlignment.setId(custAlId);
		    newCustomerAlignment.setExtdAttributes(list);
		    newCustomerAlignment.setTenantId((short)1);
		    newCustomerAlignment.setCreatedBy(1);
		    newCustomerAlignment.setCreatedDate(DateUtil.getCurrentDate());
		    newCustomerAlignment.setStartDate(DateUtil.getDefaultFormatDate(endDate));
		    newCustomerAlignment.setEndDate(DateUtil.getDefaultFormatDate(endDate));
		    newCustomerAlignment.setUpdatedBy(2);
		    newCustomerAlignment.setUpdatedDate(DateUtil.getCurrentDate());
		    newCustomerAlignment.setActive(isActive);
		    
		    oldCustomerAlignment.setId(custAlId);
			oldCustomerAlignment.setSalesPosition(salesPosition);
			oldCustomerAlignment.setCustomer(customer);
			
			serviceResult.setDetail(custAlignService.editCustomer(oldCustomerAlignment,newCustomerAlignment,"Edit", userDetails,businessReason,oldCustomerProductAlignmentList,newCustomerProductAlignmentList));
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
	
	
	@Transactional
	@RequestMapping(value = "/customer/chngReqOff/{cRId}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	ServiceResponse<OfflineRequest> fetchChngReqOff(
			@PathVariable("cRId") Long cRId, HttpServletRequest request) {

		ServiceStatus serviceStatus = null;
		ServiceResponse serviceResponse = new ServiceResponse();
		ServiceResult serviceResult = new ServiceResult();

		UserDetails userDetails = ModelAssembler.getDefaultUserDetails();

		serviceResult.setDetail(chngreqOfflineDAOService.fetchtempCRInfo(cRId, userDetails));
		serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
		serviceResponse.setStatus(serviceStatus);
		serviceResponse.setResult(serviceResult);
		LOGGER.info(" Returning REST response with Result.."
				+ serviceResult.getDetail() + " and status "
				+ serviceResponse.getStatus().getCode().getCode() + "-"
				+ serviceResponse.getStatus().getMessage());
		return serviceResponse;
	}   
	
	@RequestMapping(value = "/customer/alignment/pullEdit/{al}/{bu}/{st}/{srcsp}/{srcsh}/{tarsp}/{tarsh}/{custId}/{srccustALId}/{isImplicitAssignment}/{isaffBased}/{isTargetted}/{prtType}/{startdate}/{endDate}/{comments}/{plannedCalls}/{callPlanTypeId}/{businessReasonId}/{attrname}/{attrValue}/{prdId}", 
			method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody ServiceResponse<List<CustomerAlignment>> pullEdit(
			@PathVariable("al") Long alId, @PathVariable("bu") Long buId,
			@PathVariable("st") Long stId, @PathVariable("srcsp") Long srcsp,
			@PathVariable("srcsh") Long srcsh,
			@PathVariable("tarsp") Long tarsp,
			@PathVariable("tarsh") Long tarsh,
			@PathVariable("custId") Long custId,
			@PathVariable("srccustALId") Long custALId,
			@PathVariable("isImplicitAssignment") Boolean isImplicitAssignment,
			@PathVariable("isaffBased") Boolean isaffBased,
			@PathVariable("isTargetted") Boolean isTargetted,
			@PathVariable("prtType") String prtType,
			@PathVariable("startdate") String startdate,
			@PathVariable("endDate") String endDate,
			@PathVariable("comments") String comments,
			@PathVariable("plannedCalls") int plannedCalls,
			@PathVariable("callPlanTypeId") int callPlanTypeId,
			@PathVariable("businessReasonId") long businessReasonId,
			@PathVariable("attrname") String attrname,
			@PathVariable("attrValue") String attrValue,
			@PathVariable("prdId") Long prdId,
			
			
			HttpServletRequest request) throws MetricViolationException,
			CustomerServiceException, AffiliationServiceException,
			SalesPositionServiceException, ChangeRequestServiceException,
			CallPlanServiceException, ParseException, ViewServiceException {

		ServiceStatus serviceStatus = null;
		ServiceResponse serviceResponse = new ServiceResponse();
		ServiceResult serviceResult = new ServiceResult();

		try {
			
			List<CustomerProductAlignment> newCustomerProductAlignmentList = new ArrayList<CustomerProductAlignment>();
			List<CustomerProductAlignment> oldCustomerProductAlignmentList = new ArrayList<CustomerProductAlignment>();

			CustomerAlignment orgCustAlgn = new CustomerAlignment();
			CustomerAlignment newCustAlgn = new CustomerAlignment();
			Customer oldCustomer = new Customer();
			Customer newCustomer = new Customer();
			UserDetails userDetails = ModelAssembler.getDefaultUserDetails();
			userDetails.setUserId(80);

			// Source Customer Alignment Data
			Alignment alignment = ModelAssembler.getAlignmentModel(alId, buId,
					stId);
			SalesPosition srcsalesPosition = ModelAssembler.getSalesPosition(
					srcsp, srcsh);
			srcsalesPosition.setAlignmment(alignment);
			oldCustomer.setId(custId);
			orgCustAlgn.setId(custALId);
			orgCustAlgn.setCustomer(oldCustomer);
			orgCustAlgn.setSalesPosition(srcsalesPosition);
			orgCustAlgn.setAffBasedAssignment(isaffBased);
			orgCustAlgn.setCustomerTargeted(isTargetted);
			orgCustAlgn.setPriority(PriorityType.valueOf(prtType));
			orgCustAlgn.setImplicitAssignment(isImplicitAssignment);

			// Target Customer Alignment Data
			newCustomer.setId(custId);
			newCustAlgn.setCustomer(newCustomer);
			newCustAlgn.setStartDate(DateUtil.getDefaultFormatDate(startdate));
			newCustAlgn.setEndDate(DateUtil.getDefaultFormatDate(endDate));
			
			SalesPosition tarsalesPosition = ModelAssembler.getSalesPosition(
					tarsp, tarsh);
			tarsalesPosition.setAlignmment(alignment);
			newCustAlgn.setSalesPosition(tarsalesPosition);
			
			//Call Plan 
			CustomerCallPlan customerCallPlanDetails = new CustomerCallPlan();
			customerCallPlanDetails.setPlannedCalls(plannedCalls);
			customerCallPlanDetails.setType(CallPlanType.getAttributeType(callPlanTypeId));
			newCustAlgn.setCustomerCallPlan(customerCallPlanDetails);

			BusinessReason businessReason = new BusinessReason();
			
			businessReason.setId(businessReasonId);
			
			List<ExtdAttribue> list = new ArrayList<ExtdAttribue>();  
			
			ExtdAttribue extdAttribue = new ExtdAttribue();
			AttributeDefinition attrDef=new AttributeDefinition();
			attrDef.setName(attrname);
			extdAttribue.setDefinition(attrDef);
			extdAttribue.setValue(attrValue);
			list.add(extdAttribue);
			
			ExtdAttribue extdAttribue2 = new ExtdAttribue();
			AttributeDefinition attrDef2=new AttributeDefinition();
			attrDef2.setName("attr_2");
			extdAttribue2.setDefinition(attrDef2);
			extdAttribue2.setValue("232");
			list.add(extdAttribue2);
			
			ExtdAttribue extdAttribue3 = new ExtdAttribue();
			AttributeDefinition attrDef3=new AttributeDefinition();
			attrDef3.setName("attr_16");
			extdAttribue3.setDefinition(attrDef3);
			extdAttribue3.setValue("555");
			list.add(extdAttribue3);
			
			orgCustAlgn.setExtdAttributes(list);
			newCustAlgn.setExtdAttributes(list);
			
			
			List<ExtdAttribue> custPrdList = new ArrayList<ExtdAttribue>();  
			
			ExtdAttribue custPrdExtdAtt = new ExtdAttribue();
			AttributeDefinition custPrdAttrDef=new AttributeDefinition();
			custPrdAttrDef.setName(attrname);
			custPrdExtdAtt.setDefinition(custPrdAttrDef);
			custPrdExtdAtt.setValue(attrValue);
			custPrdList.add(custPrdExtdAtt);
			
			ExtdAttribue custPrdExtdAtt2 = new ExtdAttribue();
			AttributeDefinition custPrdAttrDef2=new AttributeDefinition();
			custPrdAttrDef2.setName("attr_2");
			custPrdExtdAtt2.setDefinition(custPrdAttrDef2);
			custPrdExtdAtt2.setValue("232");
			custPrdList.add(custPrdExtdAtt2);
			
			ExtdAttribue custPrdExtdAtt3 = new ExtdAttribue();
			AttributeDefinition custPrdAttrDef3=new AttributeDefinition();
			custPrdAttrDef3.setName("attr_16");
			custPrdExtdAtt3.setDefinition(custPrdAttrDef3);
			custPrdExtdAtt3.setValue("555");
			custPrdList.add(custPrdExtdAtt3);
			
			Product product1 = new Product();
			product1.setId(prdId);
			CustomerProductAlignment newCustomerProductAlignment = new CustomerProductAlignment();
			newCustomerProductAlignment.setProduct(product1);
			newCustomerProductAlignment.setCustomer(newCustomer);
			newCustomerProductAlignment.setExtdAttributes(custPrdList);
			newCustomerProductAlignmentList.add(newCustomerProductAlignment);
			
			
			Product product2 = new Product();
			product2.setId(228L);
			
			
			CustomerProductAlignment nwCustomerProductAlignment2 = new CustomerProductAlignment();
			nwCustomerProductAlignment2.setProduct(product2);
			nwCustomerProductAlignment2.setCustomer(newCustomer);
			nwCustomerProductAlignment2.setExtdAttributes(custPrdList);
			newCustomerProductAlignmentList.add(nwCustomerProductAlignment2);
			
			serviceResult.setDetail(custAlignService.pullCustomer(orgCustAlgn,
					newCustAlgn,comments, userDetails,businessReason,oldCustomerProductAlignmentList,newCustomerProductAlignmentList));
			
			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
		} catch (AlignmentServiceException e) {
			serviceResult.setDetail("Exception occured " + e.getMessage());
			serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR,
					"500 ERROR");
		}
		serviceResponse.setStatus(serviceStatus);
		serviceResponse.setResult(serviceResult);
		LOGGER.info(" Returning REST response with Result.."
				+ serviceResult.getDetail() + " and status "
				+ serviceResponse.getStatus().getCode().getCode() + "-"
				+ serviceResponse.getStatus().getMessage());
		return serviceResponse;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/customer/alignment/pullNew/{al}/{bu}/{st}/{srcsp}/{srcsh}/{tarsp}/{tarsh}/{custId}/{srccustALId}/{isImplicitAssignment}/{isaffBased}/{isTargetted}/{prtType}/{startdate}/{endDate}/{comments}/{businessReasonId}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody ServiceResponse<String> pullNew(
			@PathVariable("al") Long alId, @PathVariable("bu") Long buId,
			@PathVariable("st") Long stId, @PathVariable("srcsp") Long srcsp,
			@PathVariable("srcsh") Long srcsh,
			@PathVariable("tarsp") Long tarsp,
			@PathVariable("tarsh") Long tarsh,
			@PathVariable("custId") Long custId,
			@PathVariable("srccustALId") Long custALId,
			@PathVariable("isImplicitAssignment") Boolean isImplicitAssignment,
			@PathVariable("isaffBased") Boolean isaffBased,
			@PathVariable("isTargetted") Boolean isTargetted,
			@PathVariable("prtType") String prtType,
			@PathVariable("startdate") String startdate,
			@PathVariable("endDate") String endDate,
			@PathVariable("comments") String comments,
			@PathVariable("businessReasonId") long businessReasonId,
			
			HttpServletRequest request) throws MetricViolationException,
			CustomerServiceException, AffiliationServiceException,
			SalesPositionServiceException, ChangeRequestServiceException,
			CallPlanServiceException, ParseException, ViewServiceException {

		ServiceStatus serviceStatus = null;
		
		ServiceResponse<String> serviceResponse = new ServiceResponse<String>();
		@SuppressWarnings("rawtypes")
		ServiceResult serviceResult = new ServiceResult();

		try {

			CustomerAlignment orgCustAlgn = new CustomerAlignment();
			CustomerAlignment newCustAlgn = new CustomerAlignment();
			Customer oldCustomer = new Customer();
			Customer newCustomer = new Customer();
			
			UserDetails userDetails = ModelAssembler.getDefaultUserDetails();
			userDetails.setUserId(80);

			// Source Customer Alignment Data
			Alignment alignment = ModelAssembler.getAlignmentModel(alId, buId,
					stId);
			SalesPosition srcsalesPosition = ModelAssembler.getSalesPosition(
					srcsp, srcsh);
			srcsalesPosition.setAlignmment(alignment);
			oldCustomer.setId(custId);
			orgCustAlgn.setId(custALId);
			orgCustAlgn.setCustomer(oldCustomer);
			orgCustAlgn.setSalesPosition(srcsalesPosition);
			orgCustAlgn.setAffBasedAssignment(isaffBased);
			orgCustAlgn.setCustomerTargeted(isTargetted);
			orgCustAlgn.setPriority(PriorityType.valueOf(prtType));
			orgCustAlgn.setImplicitAssignment(isImplicitAssignment);

			// Target Customer Alignment Data
			newCustomer.setId(custId);
			newCustAlgn.setCustomer(newCustomer);
			newCustAlgn.setStartDate(DateUtil.getDefaultFormatDate(startdate));
			newCustAlgn.setEndDate(DateUtil.getDefaultFormatDate(endDate));
			
			SalesPosition tarsalesPosition = ModelAssembler.getSalesPosition(
					tarsp, tarsh);
			tarsalesPosition.setAlignmment(alignment);
			newCustAlgn.setSalesPosition(tarsalesPosition);

			BusinessReason businessReason = new BusinessReason();
			businessReason.setId(businessReasonId);
			
			ChangeRequest chngReq = changeRequestService.generatePullCustomerCR(srcsalesPosition, tarsalesPosition, userDetails);
			
			custAlignService.pullCustomer(chngReq, orgCustAlgn, newCustAlgn,
					comments, userDetails, businessReason, null, null);
			
			serviceResult.setDetail("ChangeRequest Id: " + chngReq.getId());
			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
			
		} catch (AlignmentServiceException e) {
			serviceResult.setDetail("Exception occured " + e.getMessage());
			serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR,
					"500 ERROR");
		}
		serviceResponse.setStatus(serviceStatus);
		serviceResponse.setResult(serviceResult);
		LOGGER.info(" Returning REST response with Result.."
				+ serviceResult.getDetail() + " and status "
				+ serviceResponse.getStatus().getCode().getCode() + "-"
				+ serviceResponse.getStatus().getMessage());
		return serviceResponse;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/customer/alignment/pullSubmit/{crId}/{custId}/{srccustALId}/{isImplicitAssignment}/{isaffBased}/{isTargetted}/{prtType}/{startdate}/{endDate}/{comments}/{businessReasonId}/{userId}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody ServiceResponse<String> pullSubmit(
			@PathVariable("crId") Long crId,
			@PathVariable("custId") Long custId,
			@PathVariable("srccustALId") Long custALId,
			@PathVariable("isImplicitAssignment") Boolean isImplicitAssignment,
			@PathVariable("isaffBased") Boolean isaffBased,
			@PathVariable("isTargetted") Boolean isTargetted,
			@PathVariable("prtType") String prtType,
			@PathVariable("startdate") String startdate,
			@PathVariable("endDate") String endDate,
			@PathVariable("comments") String comments,
			@PathVariable("businessReasonId") long businessReasonId,
			@PathVariable("userId") Integer userId,
			
			HttpServletRequest request) throws MetricViolationException,
			CustomerServiceException, AffiliationServiceException,
			SalesPositionServiceException, ChangeRequestServiceException,
			CallPlanServiceException, ParseException, ViewServiceException {

		ServiceStatus serviceStatus = null;
		
		ServiceResponse<String> serviceResponse = new ServiceResponse<String>();
		@SuppressWarnings("rawtypes")
		ServiceResult serviceResult = new ServiceResult();

		try {

			ChangeRequest chngReq = new ChangeRequest();
			chngReq.setId(crId);
		
			try {
				chngReq = changeRequestDAOService
						.getChangeRequestByChangeRequestId(chngReq);

			} catch (OpservDataAccessException e) {
				Object[] args = new Object[] { "chngReq" };
				throw new ChangeRequestServiceException(
						ChangeRequestServiceExceptionCode.CR_SER_EX_CD_016,
						"Exception while fetching change Request details / Change Request Id is Null", args, e);
			}

			// Source Customer Alignment Data
			Alignment alignment = ModelAssembler.getAlignmentModel(chngReq.getRequestedSalesPosition().getAlignmment().getId(),
					chngReq.getRequestedSalesPosition().getAlignmment().getSalesTeam().getBusinessUnit().getId(),
					chngReq.getRequestedSalesPosition().getAlignmment().getSalesTeam().getId());
			
			SalesPosition srcsalesPosition = ModelAssembler.getSalesPosition(chngReq.getRaisedSalesPosition().getId(),
					chngReq.getRaisedSalesPosition().getHierarchy().getId());
			
			CustomerAlignment orgCustAlgn = new CustomerAlignment();
			CustomerAlignment newCustAlgn = new CustomerAlignment();
			Customer oldCustomer = new Customer();
			Customer newCustomer = new Customer();
			
			srcsalesPosition.setAlignmment(alignment);
			oldCustomer.setId(custId);
			orgCustAlgn.setId(custALId);
			orgCustAlgn.setCustomer(oldCustomer);
			orgCustAlgn.setSalesPosition(srcsalesPosition);
			orgCustAlgn.setAffBasedAssignment(isaffBased);
			orgCustAlgn.setCustomerTargeted(isTargetted);
			orgCustAlgn.setPriority(PriorityType.valueOf(prtType));
			orgCustAlgn.setImplicitAssignment(isImplicitAssignment);

			// Target Customer Alignment Data
			newCustomer.setId(custId);
			newCustAlgn.setCustomer(newCustomer);
			newCustAlgn.setStartDate(DateUtil.getDefaultFormatDate(startdate));
			newCustAlgn.setEndDate(DateUtil.getDefaultFormatDate(endDate));
			
			SalesPosition tarsalesPosition = ModelAssembler.getSalesPosition(chngReq.getRequestedSalesPosition().getId(),
					chngReq.getRequestedSalesPosition().getHierarchy().getId());
			
			tarsalesPosition.setAlignmment(alignment);
			newCustAlgn.setSalesPosition(tarsalesPosition);

			BusinessReason businessReason = new BusinessReason();
			businessReason.setId(businessReasonId);
			
			UserDetails userDetails = ModelAssembler.getDefaultUserDetails();
			userDetails.setUserId(userId);

			custAlignService.pullCustomer(chngReq, orgCustAlgn, newCustAlgn,
					comments, userDetails, businessReason, null, null);
			
			serviceResult.setDetail("ChangeRequest Id: " + chngReq.getId());
			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
			
		} catch (AlignmentServiceException e) {
			serviceResult.setDetail("Exception occured " + e.getMessage());
			serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR,
					"500 ERROR");
		} catch(ChangeRequestServiceException ec){
			serviceResult.setDetail("Exception occured " + ec.getMessage());
			serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR,
					"500 ERROR");
		}
		serviceResponse.setStatus(serviceStatus);
		serviceResponse.setResult(serviceResult);
		LOGGER.info(" Returning REST response with Result.."
				+ serviceResult.getDetail() + " and status "
				+ serviceResponse.getStatus().getCode().getCode() + "-"
				+ serviceResponse.getStatus().getMessage());
		return serviceResponse;
	}	
	
	@RequestMapping(value="/customer/alignment/ext/{custAlgId}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody ServiceResponse<CustomerAlignment> getAllCustomerAlignmentsByCustAlgnmtId(@PathVariable("custAlgId") Long custAlgId, HttpServletRequest request) {

		ServiceStatus serviceStatus = null;
		ServiceResponse serviceResponse = new ServiceResponse();
		ServiceResult serviceResult = new ServiceResult();

		try {
			serviceResult.setDetail(custAlignService.getAllCustomerAlignmentsByCustAlgnmtId(custAlgId,ModelAssembler.getDefaultUserDetails()));
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
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/customer/alignment/editcustomerNew/{custId}/{custAlId}/{al}/{bu}/{st}/{sp}/{sh}/{attrname}/{attrValue}/{plannedCalls}/{prdId}/{businessReasonId}/{isActive}/{startDate}/{endDate}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody ServiceResponse<String> editCustomerAlignmentDetailsNew(@PathVariable("custId") Long custId,@PathVariable("custAlId") Long custAlId,
		   @PathVariable("al") Long alId, 
		   @PathVariable("bu") Long buId,
		   @PathVariable("st") Long stId,
		   @PathVariable("sp") Long sp,
		   @PathVariable("sh") Long sh,
		   @PathVariable("attrname") String attrname,
		   @PathVariable("attrValue") String attrValue,
		   @PathVariable("plannedCalls") int plannedCalls,
		   @PathVariable("prdId") Long prdId,
		   @PathVariable("businessReasonId") long businessReasonId, 
		   @PathVariable("isActive") Boolean isActive,
		   @PathVariable("startDate") String startDate,
		   @PathVariable("endDate") String endDate,
		
		   HttpServletRequest request) 
			throws MetricViolationException, CustomerServiceException, AffiliationServiceException,
			SalesPositionServiceException, ChangeRequestServiceException, CallPlanServiceException, ParseException, ViewServiceException {
		
		ServiceStatus serviceStatus = null;
		ServiceResponse<String> serviceResponse = new ServiceResponse<String>();
		@SuppressWarnings("rawtypes")
		ServiceResult serviceResult = new ServiceResult();

		try {
			CustomerAlignment newCustomerAlignment = new CustomerAlignment(); 
			CustomerAlignment oldCustomerAlignment = new CustomerAlignment();
			
			List<CustomerProductAlignment> newCustomerProductAlignmentList = new ArrayList<CustomerProductAlignment>();
			List<CustomerProductAlignment> oldCustomerProductAlignmentList = new ArrayList<CustomerProductAlignment>();
			
			UserDetails userDetails = ModelAssembler.getDefaultUserDetails();
			userDetails.setLocaleId("en_US");
			userDetails.setUserId(80);
		
			
			Customer customer = new Customer(); 
			customer.setId(custId);
			newCustomerAlignment.setCustomer(customer);
			SalesPosition salesPosition = new SalesPosition();
			SalesOrgHierarchy hierarchy = new SalesOrgHierarchy();
			
			Alignment alignment = new Alignment();
			SalesTeam salesTeam = new SalesTeam();
			BusinessUnit businessUnit = new BusinessUnit();
			CustomerCallPlan newCustomerCallPlan = new CustomerCallPlan();
			CustomerCallPlan oldCustomerCallPlan = new CustomerCallPlan();
			BusinessReason businessReason = new BusinessReason();
			businessReason.setId(businessReasonId);
			
			hierarchy.setId(sh);
			salesPosition.setId(sp);
			alignment.setId(alId);
			salesTeam.setId(stId);
			businessUnit.setId(buId);
			
			salesPosition.setHierarchy(hierarchy);
			salesTeam.setBusinessUnit(businessUnit);
			alignment.setSalesTeam(salesTeam);
			salesPosition.setAlignmment(alignment);
			
			
			newCustomerAlignment.setSalesPosition(salesPosition);
			
			List<ExtdAttribue> list = new ArrayList<ExtdAttribue>();  
			
			ExtdAttribue extdAttribue = new ExtdAttribue();
			AttributeDefinition attrDef=new AttributeDefinition();
			attrDef.setName(attrname);
			extdAttribue.setDefinition(attrDef);
			extdAttribue.setValue(attrValue);
			list.add(extdAttribue);
			
			ExtdAttribue extdAttribue2 = new ExtdAttribue();
			AttributeDefinition attrDef2=new AttributeDefinition();
			attrDef2.setName("attr_2");
			extdAttribue2.setDefinition(attrDef2);
			extdAttribue2.setValue("232");
			list.add(extdAttribue2);
			
			ExtdAttribue extdAttribue3 = new ExtdAttribue();
			AttributeDefinition attrDef3=new AttributeDefinition();
			attrDef3.setName("attr_16");
			extdAttribue3.setDefinition(attrDef3);
			extdAttribue3.setValue("555");
			list.add(extdAttribue3);
			
			
			List<ExtdAttribue> custPrdList = new ArrayList<ExtdAttribue>();  
			
			ExtdAttribue custPrdExtdAtt = new ExtdAttribue();
			AttributeDefinition custPrdAttrDef=new AttributeDefinition();
			custPrdAttrDef.setName(attrname);
			custPrdExtdAtt.setDefinition(custPrdAttrDef);
			custPrdExtdAtt.setValue(attrValue);
			custPrdList.add(custPrdExtdAtt);
			
			ExtdAttribue custPrdExtdAtt2 = new ExtdAttribue();
			AttributeDefinition custPrdAttrDef2=new AttributeDefinition();
			custPrdAttrDef2.setName("attr_2");
			custPrdExtdAtt2.setDefinition(custPrdAttrDef2);
			custPrdExtdAtt2.setValue("232");
			custPrdList.add(custPrdExtdAtt2);
			
			ExtdAttribue custPrdExtdAtt3 = new ExtdAttribue();
			AttributeDefinition custPrdAttrDef3=new AttributeDefinition();
			custPrdAttrDef3.setName("attr_16");
			custPrdExtdAtt3.setDefinition(custPrdAttrDef3);
			custPrdExtdAtt3.setValue("555");
			custPrdList.add(custPrdExtdAtt3);
			
			Product product1 = new Product();
			product1.setId(prdId);
			CustomerProductAlignment newCustomerProductAlignment = new CustomerProductAlignment();
			newCustomerProductAlignment.setProduct(product1);
			newCustomerProductAlignment.setCustomer(customer);
			newCustomerProductAlignment.setExtdAttributes(custPrdList);
			newCustomerProductAlignmentList.add(newCustomerProductAlignment);
			
			
			Product product2 = new Product();
			product2.setId(228L);
			CustomerProductAlignment nwCustomerProductAlignment2 = new CustomerProductAlignment();
			nwCustomerProductAlignment2.setProduct(product2);
			nwCustomerProductAlignment2.setCustomer(customer);
			nwCustomerProductAlignment2.setExtdAttributes(custPrdList);
			newCustomerProductAlignmentList.add(nwCustomerProductAlignment2);
			
			
			
			newCustomerCallPlan.setPlannedCalls(plannedCalls);
			//newCustomerCallPlan.setId(callPlanId);
			//customerCallPlan.setCustomerAlgn(newcustomerAlignment);
		    newCustomerAlignment.setCustomerCallPlan(newCustomerCallPlan);
			
		    newCustomerAlignment.setId(custAlId);
		    newCustomerAlignment.setExtdAttributes(list);
		    newCustomerAlignment.setTenantId((short)1);
		    newCustomerAlignment.setCreatedBy(1);
		    newCustomerAlignment.setCreatedDate(DateUtil.getCurrentDate());
		    newCustomerAlignment.setEndDate(DateUtil.getDefaultFormatDate(endDate));
		    newCustomerAlignment.setStartDate(DateUtil.getDefaultFormatDate(startDate));
		    newCustomerAlignment.setActive(isActive);
		    newCustomerAlignment.setAffBasedAssignment(false);
		    newCustomerAlignment.setCustomerTargeted(true);
		    newCustomerAlignment.setGeoAligned(true);
		    
		    
		    oldCustomerAlignment.setId(custAlId);
			oldCustomerAlignment.setSalesPosition(salesPosition);
			oldCustomerAlignment.setCustomer(customer);
			//oldCustomerCallPlan.setId(callPlanId);
			oldCustomerCallPlan.setPlannedCalls(5);
			
			oldCustomerAlignment.setCustomerCallPlan(oldCustomerCallPlan);
		    
			ChangeRequest chngReq = changeRequestService.generateEditCustomerCR(newCustomerAlignment.getSalesPosition(), userDetails);
			
			custAlignService.editCustomer(chngReq,oldCustomerAlignment, newCustomerAlignment, "Edit", userDetails, businessReason, oldCustomerProductAlignmentList, newCustomerProductAlignmentList);
			
			serviceResult.setDetail("ChangeRequest Id: " + chngReq.getId());
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
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/customer/alignment/editcustomerSubmit/{crId}/{custId}/{custAlId}/{attrname}/{attrValue}/{plannedCalls}/{prdId}/{businessReasonId}/{isActive}/{startDate}/{endDate}/{userId}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody ServiceResponse<String> editCustomerAlignmentDetailsSubmit(@PathVariable("custId") Long custId,@PathVariable("custAlId") Long custAlId,
		   @PathVariable("crId") Long crId,
		   @PathVariable("attrname") String attrname,
		   @PathVariable("attrValue") String attrValue,
		   @PathVariable("plannedCalls") int plannedCalls,
		   @PathVariable("prdId") Long prdId,
		   @PathVariable("businessReasonId") long businessReasonId, 
		   @PathVariable("isActive") Boolean isActive,
		   @PathVariable("startDate") String startDate,
		   @PathVariable("endDate") String endDate,
		   @PathVariable("userId") Integer userId,
		   HttpServletRequest request) 
			throws MetricViolationException, CustomerServiceException, AffiliationServiceException,
			SalesPositionServiceException, ChangeRequestServiceException, CallPlanServiceException, ParseException, ViewServiceException {
		
		ServiceStatus serviceStatus = null;
		ServiceResponse<String> serviceResponse = new ServiceResponse<String>();
		@SuppressWarnings("rawtypes")
		ServiceResult serviceResult = new ServiceResult();

		try {
			CustomerAlignment newCustomerAlignment = new CustomerAlignment(); 
			CustomerAlignment oldCustomerAlignment = new CustomerAlignment();
			
			List<CustomerProductAlignment> newCustomerProductAlignmentList = new ArrayList<CustomerProductAlignment>();
			List<CustomerProductAlignment> oldCustomerProductAlignmentList = new ArrayList<CustomerProductAlignment>();
			

			ChangeRequest chngReq = new ChangeRequest();
			chngReq.setId(crId);
			try {
				chngReq = changeRequestDAOService
						.getChangeRequestByChangeRequestId(chngReq);

			} catch (OpservDataAccessException e) {
				Object[] args = new Object[] { "chngReq" };
				throw new ChangeRequestServiceException(
						ChangeRequestServiceExceptionCode.CR_SER_EX_CD_016,
						"Exception while fetching change Request details / Change Request Id is Null", args, e);
			}
			
			chngReq = changeRequestDAOService.getChangeRequestByChangeRequestId(chngReq);

			// Source Customer Alignment Data
			Alignment alignment = ModelAssembler.getAlignmentModel(chngReq.getRequestedSalesPosition().getAlignmment().getId(),
					chngReq.getRequestedSalesPosition().getAlignmment().getSalesTeam().getBusinessUnit().getId(),
					chngReq.getRequestedSalesPosition().getAlignmment().getSalesTeam().getId());
		
			
			Customer customer = new Customer(); 
			customer.setId(custId);
			newCustomerAlignment.setCustomer(customer);
			
			SalesPosition salesPosition = ModelAssembler.getSalesPosition(chngReq.getRequestedSalesPosition().getId(),
					chngReq.getRequestedSalesPosition().getHierarchy().getId());

			CustomerCallPlan newCustomerCallPlan = new CustomerCallPlan();
			CustomerCallPlan oldCustomerCallPlan = new CustomerCallPlan();
			
			BusinessReason businessReason = new BusinessReason();
			businessReason.setId(businessReasonId);
			
			salesPosition.setAlignmment(alignment);
			
			newCustomerAlignment.setSalesPosition(salesPosition);
			
			List<ExtdAttribue> list = new ArrayList<ExtdAttribue>();  
			
			ExtdAttribue extdAttribue = new ExtdAttribue();
			AttributeDefinition attrDef=new AttributeDefinition();
			attrDef.setName(attrname);
			extdAttribue.setDefinition(attrDef);
			extdAttribue.setValue(attrValue);
			list.add(extdAttribue);
			
			ExtdAttribue extdAttribue2 = new ExtdAttribue();
			AttributeDefinition attrDef2=new AttributeDefinition();
			attrDef2.setName("attr_2");
			extdAttribue2.setDefinition(attrDef2);
			extdAttribue2.setValue("232");
			list.add(extdAttribue2);
			
			ExtdAttribue extdAttribue3 = new ExtdAttribue();
			AttributeDefinition attrDef3=new AttributeDefinition();
			attrDef3.setName("attr_16");
			extdAttribue3.setDefinition(attrDef3);
			extdAttribue3.setValue("555");
			list.add(extdAttribue3);
			
			
			List<ExtdAttribue> custPrdList = new ArrayList<ExtdAttribue>();  
			
			ExtdAttribue custPrdExtdAtt = new ExtdAttribue();
			AttributeDefinition custPrdAttrDef=new AttributeDefinition();
			custPrdAttrDef.setName(attrname);
			custPrdExtdAtt.setDefinition(custPrdAttrDef);
			custPrdExtdAtt.setValue(attrValue);
			custPrdList.add(custPrdExtdAtt);
			
			ExtdAttribue custPrdExtdAtt2 = new ExtdAttribue();
			AttributeDefinition custPrdAttrDef2=new AttributeDefinition();
			custPrdAttrDef2.setName("attr_2");
			custPrdExtdAtt2.setDefinition(custPrdAttrDef2);
			custPrdExtdAtt2.setValue("232");
			custPrdList.add(custPrdExtdAtt2);
			
			ExtdAttribue custPrdExtdAtt3 = new ExtdAttribue();
			AttributeDefinition custPrdAttrDef3=new AttributeDefinition();
			custPrdAttrDef3.setName("attr_16");
			custPrdExtdAtt3.setDefinition(custPrdAttrDef3);
			custPrdExtdAtt3.setValue("555");
			custPrdList.add(custPrdExtdAtt3);
			
			Product product1 = new Product();
			product1.setId(prdId);
			CustomerProductAlignment newCustomerProductAlignment = new CustomerProductAlignment();
			newCustomerProductAlignment.setProduct(product1);
			newCustomerProductAlignment.setCustomer(customer);
			newCustomerProductAlignment.setExtdAttributes(custPrdList);
			newCustomerProductAlignmentList.add(newCustomerProductAlignment);
			
			
			Product product2 = new Product();
			product2.setId(228L);
			CustomerProductAlignment nwCustomerProductAlignment2 = new CustomerProductAlignment();
			nwCustomerProductAlignment2.setProduct(product2);
			nwCustomerProductAlignment2.setCustomer(customer);
			nwCustomerProductAlignment2.setExtdAttributes(custPrdList);
			newCustomerProductAlignmentList.add(nwCustomerProductAlignment2);
			
			
			
			newCustomerCallPlan.setPlannedCalls(plannedCalls);
			//customerCallPlan.setCustomerAlgn(newcustomerAlignment);
		    newCustomerAlignment.setCustomerCallPlan(newCustomerCallPlan);
			
		    newCustomerAlignment.setId(custAlId);
		    newCustomerAlignment.setExtdAttributes(list);
		    newCustomerAlignment.setTenantId((short)1);
		    newCustomerAlignment.setCreatedBy(1);
		    newCustomerAlignment.setCreatedDate(DateUtil.getCurrentDate());
		    newCustomerAlignment.setEndDate(DateUtil.getDefaultFormatDate(endDate));
		    newCustomerAlignment.setStartDate(DateUtil.getDefaultFormatDate(startDate));
		    newCustomerAlignment.setActive(isActive);
		    newCustomerAlignment.setAffBasedAssignment(false);
		    newCustomerAlignment.setCustomerTargeted(true);
		    newCustomerAlignment.setGeoAligned(true);
		    
		    
		    oldCustomerAlignment.setId(custAlId);
			oldCustomerAlignment.setSalesPosition(salesPosition);
			oldCustomerAlignment.setCustomer(customer);
			oldCustomerCallPlan.setPlannedCalls(5);
			
			oldCustomerAlignment.setCustomerCallPlan(oldCustomerCallPlan);
			
			UserDetails userDetails = ModelAssembler.getDefaultUserDetails();
			userDetails.setUserId(userId);
			
			custAlignService.editCustomer(chngReq,oldCustomerAlignment, newCustomerAlignment, "Edit", userDetails, businessReason, oldCustomerProductAlignmentList, newCustomerProductAlignmentList);
			
			serviceResult.setDetail("ChangeRequest Id: " + chngReq.getId());
			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
		} catch (AlignmentServiceException e) {
			serviceResult.setDetail("Exception occured "+e.getMessage());
			serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR, "500 ERROR");
		} catch(ChangeRequestServiceException ec){
			serviceResult.setDetail("Exception occured " + ec.getMessage());
			serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR,
					"500 ERROR");
		}
		serviceResponse.setStatus(serviceStatus);
		serviceResponse.setResult(serviceResult);
		LOGGER.info(" Returning REST response with Result.."+serviceResult.getDetail()+" and status "+serviceResponse.getStatus().getCode().getCode()+"-"+serviceResponse.getStatus().getMessage());
		return serviceResponse;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/customer/alignment/editcustFrMir/{custId}/{custAlId}/{al}/{bu}/{st}/{sp}/{sh}/{attrname}/{attrValue}/{plannedCalls}/{prdId}/{businessReasonId}/{isActive}/{startDate}/{endDate}/{userId}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody ServiceResponse<String> editCustomerAlignmentDetailsFRMirror(@PathVariable("custId") Long custId,@PathVariable("custAlId") Long custAlId,
		   @PathVariable("al") Long alId, 
		   @PathVariable("bu") Long buId,
		   @PathVariable("st") Long stId,
		   @PathVariable("sp") Long sp,
		   @PathVariable("sh") Long sh,
		   @PathVariable("attrname") String attrname,
		   @PathVariable("attrValue") String attrValue,
		   @PathVariable("plannedCalls") int plannedCalls,
		   @PathVariable("prdId") Long prdId,
		   @PathVariable("businessReasonId") long businessReasonId, 
		   @PathVariable("isActive") Boolean isActive,
		   @PathVariable("startDate") String startDate,
		   @PathVariable("endDate") String endDate,
		   @PathVariable("userId") Integer userId,
		   HttpServletRequest request) 
			throws MetricViolationException, CustomerServiceException, AffiliationServiceException,
			SalesPositionServiceException, ChangeRequestServiceException, CallPlanServiceException, ParseException, ViewServiceException {
		
		ServiceStatus serviceStatus = null;
		ServiceResponse<String> serviceResponse = new ServiceResponse<String>();
		@SuppressWarnings("rawtypes")
		ServiceResult serviceResult = new ServiceResult();

		try {
			CustomerAlignment newCustomerAlignment = new CustomerAlignment(); 
			CustomerAlignment oldCustomerAlignment = new CustomerAlignment();
			
			List<CustomerProductAlignment> newCustomerProductAlignmentList = new ArrayList<CustomerProductAlignment>();
			List<CustomerProductAlignment> oldCustomerProductAlignmentList = new ArrayList<CustomerProductAlignment>();
			
			UserDetails userDetails = ModelAssembler.getDefaultUserDetails();
			userDetails.setLocaleId("en_US");
			userDetails.setUserId(userId);
		
			
			Customer customer = new Customer(); 
			customer.setId(custId);
			newCustomerAlignment.setCustomer(customer);
			SalesPosition salesPosition = new SalesPosition();
			SalesOrgHierarchy hierarchy = new SalesOrgHierarchy();
			
			Alignment alignment = new Alignment();
			SalesTeam salesTeam = new SalesTeam();
			BusinessUnit businessUnit = new BusinessUnit();
			CustomerCallPlan newCustomerCallPlan = new CustomerCallPlan();
			CustomerCallPlan oldCustomerCallPlan = new CustomerCallPlan();
			BusinessReason businessReason = new BusinessReason();
			businessReason.setId(businessReasonId);
			
			hierarchy.setId(sh);
			salesPosition.setId(sp);
			alignment.setId(alId);
			salesTeam.setId(stId);
			businessUnit.setId(buId);
			
			salesPosition.setHierarchy(hierarchy);
			salesTeam.setBusinessUnit(businessUnit);
			alignment.setSalesTeam(salesTeam);
			salesPosition.setAlignmment(alignment);
			
			
			newCustomerAlignment.setSalesPosition(salesPosition);
			
			List<ExtdAttribue> list = new ArrayList<ExtdAttribue>();  
			
			ExtdAttribue extdAttribue = new ExtdAttribue();
			AttributeDefinition attrDef=new AttributeDefinition();
			attrDef.setName(attrname);
			extdAttribue.setDefinition(attrDef);
			extdAttribue.setValue(attrValue);
			list.add(extdAttribue);
			
			ExtdAttribue extdAttribue2 = new ExtdAttribue();
			AttributeDefinition attrDef2=new AttributeDefinition();
			attrDef2.setName("attr_2");
			extdAttribue2.setDefinition(attrDef2);
			extdAttribue2.setValue("232");
			list.add(extdAttribue2);
			
			ExtdAttribue extdAttribue3 = new ExtdAttribue();
			AttributeDefinition attrDef3=new AttributeDefinition();
			attrDef3.setName("attr_16");
			extdAttribue3.setDefinition(attrDef3);
			extdAttribue3.setValue("555");
			list.add(extdAttribue3);
			
			
			List<ExtdAttribue> custPrdList = new ArrayList<ExtdAttribue>();  
			
			ExtdAttribue custPrdExtdAtt = new ExtdAttribue();
			AttributeDefinition custPrdAttrDef=new AttributeDefinition();
			custPrdAttrDef.setName(attrname);
			custPrdExtdAtt.setDefinition(custPrdAttrDef);
			custPrdExtdAtt.setValue(attrValue);
			custPrdList.add(custPrdExtdAtt);
			
			ExtdAttribue custPrdExtdAtt2 = new ExtdAttribue();
			AttributeDefinition custPrdAttrDef2=new AttributeDefinition();
			custPrdAttrDef2.setName("attr_2");
			custPrdExtdAtt2.setDefinition(custPrdAttrDef2);
			custPrdExtdAtt2.setValue("232");
			custPrdList.add(custPrdExtdAtt2);
			
			ExtdAttribue custPrdExtdAtt3 = new ExtdAttribue();
			AttributeDefinition custPrdAttrDef3=new AttributeDefinition();
			custPrdAttrDef3.setName("attr_16");
			custPrdExtdAtt3.setDefinition(custPrdAttrDef3);
			custPrdExtdAtt3.setValue("555");
			custPrdList.add(custPrdExtdAtt3);
			
			Product product1 = new Product();
			product1.setId(prdId);
			CustomerProductAlignment newCustomerProductAlignment = new CustomerProductAlignment();
			newCustomerProductAlignment.setProduct(product1);
			newCustomerProductAlignment.setCustomer(customer);
			newCustomerProductAlignment.setExtdAttributes(custPrdList);
			newCustomerProductAlignmentList.add(newCustomerProductAlignment);
			
			
			Product product2 = new Product();
			product2.setId(228L);
			CustomerProductAlignment nwCustomerProductAlignment2 = new CustomerProductAlignment();
			nwCustomerProductAlignment2.setProduct(product2);
			nwCustomerProductAlignment2.setCustomer(customer);
			nwCustomerProductAlignment2.setExtdAttributes(custPrdList);
			newCustomerProductAlignmentList.add(nwCustomerProductAlignment2);
			
			
			
			newCustomerCallPlan.setPlannedCalls(plannedCalls);
		//	newCustomerCallPlan.setId(callPlanId);
		    newCustomerAlignment.setCustomerCallPlan(newCustomerCallPlan);
			
		    newCustomerAlignment.setId(custAlId);
		    newCustomerAlignment.setExtdAttributes(list);
		    newCustomerAlignment.setTenantId((short)1);
		    newCustomerAlignment.setCreatedBy(1);
		    newCustomerAlignment.setCreatedDate(DateUtil.getCurrentDate());
		    newCustomerAlignment.setEndDate(DateUtil.getDefaultFormatDate(endDate));
		    newCustomerAlignment.setStartDate(DateUtil.getDefaultFormatDate(startDate));
		    newCustomerAlignment.setActive(isActive);
		    newCustomerAlignment.setAffBasedAssignment(false);
		    newCustomerAlignment.setCustomerTargeted(true);
		    newCustomerAlignment.setGeoAligned(true);
		    
		    
		    oldCustomerAlignment.setId(custAlId);
			oldCustomerAlignment.setSalesPosition(salesPosition);
			oldCustomerAlignment.setCustomer(customer);
			oldCustomerCallPlan.setPlannedCalls(0);
		//	oldCustomerCallPlan.setId(callPlanId);
			oldCustomerAlignment.setCustomerCallPlan(oldCustomerCallPlan);
		    
			ChangeRequest chngReq = changeRequestService.generateEditCustomerCR(newCustomerAlignment.getSalesPosition(), userDetails);
			
			custAlignService.editCustomer(chngReq,oldCustomerAlignment, newCustomerAlignment, "Edit", userDetails, businessReason, oldCustomerProductAlignmentList, newCustomerProductAlignmentList);
			
			serviceResult.setDetail("ChangeRequest Id: " + chngReq.getId());
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
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/customer/alignment/editCustAlgmntFRMirrorSubmit/{crId}/{custId}/{custAlId}/{attrname}/{attrValue}/{plannedCalls}/{prdId}/{businessReasonId}/{isActive}/{startDate}/{endDate}/{userId}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody ServiceResponse<String> editCustAlgmntFRMirrorSubmit(@PathVariable("crId") Long crId,@PathVariable("custId") Long custId,@PathVariable("custAlId") Long custAlId,
		   @PathVariable("attrname") String attrname,
		   @PathVariable("attrValue") String attrValue,
		   @PathVariable("plannedCalls") int plannedCalls,
		   @PathVariable("prdId") Long prdId,
		   @PathVariable("businessReasonId") long businessReasonId, 
		   @PathVariable("isActive") Boolean isActive,
		   @PathVariable("startDate") String startDate,
		   @PathVariable("endDate") String endDate,
		   @PathVariable("userId") Integer userId,
		   HttpServletRequest request) 
			throws MetricViolationException, CustomerServiceException, AffiliationServiceException,
			SalesPositionServiceException, ChangeRequestServiceException, CallPlanServiceException, ParseException, ViewServiceException {
		
		ServiceStatus serviceStatus = null;
		ServiceResponse<String> serviceResponse = new ServiceResponse<String>();
		@SuppressWarnings("rawtypes")
		ServiceResult serviceResult = new ServiceResult();

		try {
			CustomerAlignment newCustomerAlignment = new CustomerAlignment(); 
			CustomerAlignment oldCustomerAlignment = new CustomerAlignment();
			
			List<CustomerProductAlignment> newCustomerProductAlignmentList = new ArrayList<CustomerProductAlignment>();
			List<CustomerProductAlignment> oldCustomerProductAlignmentList = new ArrayList<CustomerProductAlignment>();
			ChangeRequest chngReq = new ChangeRequest();
			chngReq.setId(crId);
			try {
				chngReq = changeRequestDAOService
						.getChangeRequestByChangeRequestId(chngReq);

			} catch (OpservDataAccessException e) {
				Object[] args = new Object[] { "chngReq" };
				throw new ChangeRequestServiceException(
						ChangeRequestServiceExceptionCode.CR_SER_EX_CD_016,
						"Exception while fetching change Request details / Change Request Id is Null", args, e);
			}
			
			chngReq = changeRequestDAOService.getChangeRequestByChangeRequestId(chngReq);
			UserDetails userDetails = ModelAssembler.getDefaultUserDetails();
			userDetails.setLocaleId("en_US");
			userDetails.setUserId(userId);
		
			Alignment alignment = ModelAssembler.getAlignmentModel(chngReq.getRequestedSalesPosition().getAlignmment().getId(),
					chngReq.getRequestedSalesPosition().getAlignmment().getSalesTeam().getBusinessUnit().getId(),
					chngReq.getRequestedSalesPosition().getAlignmment().getSalesTeam().getId());
		
			
			Customer customer = new Customer(); 
			customer.setId(custId);
			newCustomerAlignment.setCustomer(customer);
			
			SalesPosition salesPosition = ModelAssembler.getSalesPosition(chngReq.getRequestedSalesPosition().getId(),
					chngReq.getRequestedSalesPosition().getHierarchy().getId());
			
			customer.setId(custId);
			newCustomerAlignment.setCustomer(customer);
			
			CustomerCallPlan newCustomerCallPlan = new CustomerCallPlan();
			CustomerCallPlan oldCustomerCallPlan = new CustomerCallPlan();
			BusinessReason businessReason = new BusinessReason();
			businessReason.setId(businessReasonId);
			salesPosition.setAlignmment(alignment);
			newCustomerAlignment.setSalesPosition(salesPosition);
		
			List<ExtdAttribue> list = new ArrayList<ExtdAttribue>();  
			
			ExtdAttribue extdAttribue = new ExtdAttribue();
			AttributeDefinition attrDef=new AttributeDefinition();
			attrDef.setName(attrname);
			extdAttribue.setDefinition(attrDef);
			extdAttribue.setValue(attrValue);
			list.add(extdAttribue);
			
			ExtdAttribue extdAttribue2 = new ExtdAttribue();
			AttributeDefinition attrDef2=new AttributeDefinition();
			attrDef2.setName("attr_2");
			extdAttribue2.setDefinition(attrDef2);
			extdAttribue2.setValue("232");
			list.add(extdAttribue2);
			
			ExtdAttribue extdAttribue3 = new ExtdAttribue();
			AttributeDefinition attrDef3=new AttributeDefinition();
			attrDef3.setName("attr_16");
			extdAttribue3.setDefinition(attrDef3);
			extdAttribue3.setValue("555");
			list.add(extdAttribue3);
			
			
			List<ExtdAttribue> custPrdList = new ArrayList<ExtdAttribue>();  
			
			ExtdAttribue custPrdExtdAtt = new ExtdAttribue();
			AttributeDefinition custPrdAttrDef=new AttributeDefinition();
			custPrdAttrDef.setName(attrname);
			custPrdExtdAtt.setDefinition(custPrdAttrDef);
			custPrdExtdAtt.setValue(attrValue);
			custPrdList.add(custPrdExtdAtt);
			
			ExtdAttribue custPrdExtdAtt2 = new ExtdAttribue();
			AttributeDefinition custPrdAttrDef2=new AttributeDefinition();
			custPrdAttrDef2.setName("attr_2");
			custPrdExtdAtt2.setDefinition(custPrdAttrDef2);
			custPrdExtdAtt2.setValue("232");
			custPrdList.add(custPrdExtdAtt2);
			
			ExtdAttribue custPrdExtdAtt3 = new ExtdAttribue();
			AttributeDefinition custPrdAttrDef3=new AttributeDefinition();
			custPrdAttrDef3.setName("attr_16");
			custPrdExtdAtt3.setDefinition(custPrdAttrDef3);
			custPrdExtdAtt3.setValue("555");
			custPrdList.add(custPrdExtdAtt3);
			
			Product product1 = new Product();
			product1.setId(prdId);
			CustomerProductAlignment newCustomerProductAlignment = new CustomerProductAlignment();
			newCustomerProductAlignment.setProduct(product1);
			newCustomerProductAlignment.setCustomer(customer);
			newCustomerProductAlignment.setExtdAttributes(custPrdList);
			newCustomerProductAlignmentList.add(newCustomerProductAlignment);
			
			
			Product product2 = new Product();
			product2.setId(228L);
			CustomerProductAlignment nwCustomerProductAlignment2 = new CustomerProductAlignment();
			nwCustomerProductAlignment2.setProduct(product2);
			nwCustomerProductAlignment2.setCustomer(customer);
			nwCustomerProductAlignment2.setExtdAttributes(custPrdList);
			newCustomerProductAlignmentList.add(nwCustomerProductAlignment2);
			
			
			
			newCustomerCallPlan.setPlannedCalls(plannedCalls);
		//	newCustomerCallPlan.setId(callPlanId);
		    newCustomerAlignment.setCustomerCallPlan(newCustomerCallPlan);
			
		    newCustomerAlignment.setId(custAlId);
		    newCustomerAlignment.setExtdAttributes(list);
		    newCustomerAlignment.setTenantId((short)1);
		    newCustomerAlignment.setCreatedBy(1);
		    newCustomerAlignment.setCreatedDate(DateUtil.getCurrentDate());
		    newCustomerAlignment.setEndDate(DateUtil.getDefaultFormatDate(endDate));
		    newCustomerAlignment.setStartDate(DateUtil.getDefaultFormatDate(startDate));
		    newCustomerAlignment.setActive(isActive);
		    newCustomerAlignment.setAffBasedAssignment(false);
		    newCustomerAlignment.setCustomerTargeted(true);
		    newCustomerAlignment.setGeoAligned(true);
		    
		    
		    oldCustomerAlignment.setId(custAlId);
			oldCustomerAlignment.setSalesPosition(salesPosition);
			oldCustomerAlignment.setCustomer(customer);
			oldCustomerCallPlan.setPlannedCalls(0);
		//	oldCustomerCallPlan.setId(callPlanId);
			oldCustomerAlignment.setCustomerCallPlan(oldCustomerCallPlan);
		    
			//ChangeRequest chngReq = changeRequestService.generateEditCustomerCR(newCustomerAlignment.getSalesPosition(), userDetails);
			
			custAlignService.editCustomer(chngReq,oldCustomerAlignment, newCustomerAlignment, "Edit", userDetails, businessReason, oldCustomerProductAlignmentList, newCustomerProductAlignmentList);
			
			serviceResult.setDetail("ChangeRequest Id: " + chngReq.getId());
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
	 * @Method CustomerProductAlignment - This method is to validate the Customer =
	 * @param custId
	 * @param spId
	 * @param heirId
	 * @param alId
	 * @param buId
	 * @param stId
	 * @param custAlgmtId
	 * @param userDetails
	 * @return ServiceResponse<CustomerAlignmentDTO> - Json Object list of the CustomerDTO
	 * @throws AffiliationServiceException 
	 * @throws AlignmentServiceException 
	 * @throws CallPlanServiceException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/customerAlignValidation/validateAffiliatedCustomerAlignment/{custId}/{spId}/{heirId}/{alId}/{buId}/{stId}/{custAlgmtId}/{userId}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody ServiceResponse<List<CustomerProductAlignment>> validateAffiliatedCustomerAlignment(@PathVariable("custId") Long custId, 
			@PathVariable("spId") Long spId,@PathVariable("heirId") Long heirId,@PathVariable("alId") Long alId,@PathVariable("buId") Long buId,@PathVariable("stId") Long stId,
			@PathVariable("custAlgmtId") Long custAlgmtId,@PathVariable("userId") Integer userId,		
				HttpServletRequest request) throws AffiliationServiceException, AlignmentServiceException {
		
		
		
		ServiceStatus serviceStatus = null;
		ServiceResponse serviceResponse = new ServiceResponse();
		ServiceResult serviceResult = new ServiceResult();
		
		Customer customer = new Customer();
		customer.setId(custId);
		
		SalesOrgHierarchy hierarchy = new SalesOrgHierarchy();
		hierarchy.setId(heirId);
		
		BusinessUnit businessUnit = new BusinessUnit();
		businessUnit.setId(buId);
		
		SalesTeam salesTeam = new SalesTeam();
		salesTeam.setId(stId);
		salesTeam.setBusinessUnit(businessUnit);
		
		Alignment alignment = new Alignment();
		alignment.setId(alId);
		alignment.setSalesTeam(salesTeam);
		
		
		SalesPosition salesPosition = new SalesPosition();
		salesPosition.setId(spId);
		salesPosition.setAlignmment(alignment);
		
		salesPosition.setHierarchy(hierarchy);
		
		
		CustomerAlignment customerAlignment = new CustomerAlignment();
		customerAlignment.setCustomer(customer);
		customerAlignment.setSalesPosition(salesPosition);
		
		CustomerAlignmentDTO customerAlignmentDTO = new CustomerAlignmentDTO();
		customerAlignmentDTO.setSourceBaseCustAlgmt(customerAlignment);

		UserDetails userDetails =ModelAssembler.getDefaultUserDetails();
		userDetails.setUserId(userId);
		
		customerAlignmentValidationService.validateAffiliatedCustomerAlignment(customerAlignmentDTO, userDetails);
		
		serviceResult.setDetail(customerAlignmentDTO);
		serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");

		serviceResponse.setStatus(serviceStatus);
		serviceResponse.setResult(serviceResult);
		LOGGER.info(" Returning REST response with Result.."+serviceResult.getDetail()+" and status "+serviceResponse.getStatus().getCode().getCode()+"-"+serviceResponse.getStatus().getMessage());
		return serviceResponse;
	}
	
	/**
	 * @Method CustomerProductAlignment - This method is to validate the Customer =
	 * @param custId
	 * @param spId
	 * @param heirId
	 * @param alId
	 * @param buId
	 * @param stId
	 * @param custAlgmtId
	 * @param userDetails
	 * @return ServiceResponse<CustomerAlignmentDTO> - Json Object list of the CustomerDTO
	 * @throws AlignmentServiceException 
	 * @throws CallPlanServiceException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/customerAlignValidation/validateMirrorsForCustomerAlignment/" +
			"{srcCustId}/{srcSpId}/{scrHeirId}/{srcAlId}/{srcBuId}/{srcStId}/{srcPlanCalls}/{srcCustAlgmtId}/" +
			"{tarCustId}/{tarSpId}/{tarHeirId}/{tarAlId}/{tarBuId}/{tarStId}/{tarPlanCalls}/{tarCustAlgmtId}/" +
			"{userId}",
			method=RequestMethod.GET, produces="application/json")
	public @ResponseBody ServiceResponse<List<CustomerProductAlignment>> validateMirrorsForCustomerAlignment(
			@PathVariable("srcCustId") Long srcCustId, 
			@PathVariable("srcSpId") Long srcSpId,
			@PathVariable("scrHeirId") Long scrHeirId,
			@PathVariable("srcAlId") Long srcAlId,
			@PathVariable("srcBuId") Long srcBuId,
			@PathVariable("srcStId") Long srcStId,
			@PathVariable("srcPlanCalls") Integer srcPlanCalls,
			@PathVariable("srcCustAlgmtId") Long srcCustAlgmtId,
			
			@PathVariable("tarCustId") Long tarCustId, 
			@PathVariable("tarSpId") Long tarSpId,
			@PathVariable("tarHeirId") Long tarHeirId,
			@PathVariable("tarAlId") Long tarAlId,
			@PathVariable("tarBuId") Long tarBuId,
			@PathVariable("tarStId") Long tarStId,
			@PathVariable("tarPlanCalls") Integer tarPlanCalls,
			@PathVariable("tarCustAlgmtId") Long tarCustAlgmtId,
			
			@PathVariable("userId") Integer userId,		
				HttpServletRequest request) throws AlignmentServiceException, SalesPositionServiceException {
		
		
		
		ServiceStatus serviceStatus = null;
		ServiceResponse serviceResponse = new ServiceResponse();
		ServiceResult serviceResult = new ServiceResult();
		
		//target customer
		Customer tarCustomer = new Customer();
		tarCustomer.setId(tarCustId);
		
		SalesOrgHierarchy tarHierarchy = new SalesOrgHierarchy();
		tarHierarchy.setId(tarHeirId);
		
		BusinessUnit tarBusinessUnit = new BusinessUnit();
		tarBusinessUnit.setId(tarBuId);
		
		SalesTeam tarSalesTeam = new SalesTeam();
		tarSalesTeam.setId(tarStId);
		tarSalesTeam.setBusinessUnit(tarBusinessUnit);
		
		Alignment tarAlignment = new Alignment();
		tarAlignment.setId(tarAlId);
		tarAlignment.setSalesTeam(tarSalesTeam);
		
		
		SalesPosition tarSalesPosition = new SalesPosition();
		tarSalesPosition.setId(tarSpId);
		tarSalesPosition.setAlignmment(tarAlignment);
		tarSalesPosition.setHierarchy(tarHierarchy);
		
		CustomerCallPlan tarCustomerCallPlan = new CustomerCallPlan();
		tarCustomerCallPlan.setPlannedCalls(tarPlanCalls);
		
		CustomerAlignment tarCustomerAlignment = new CustomerAlignment();
		tarCustomerAlignment.setId(tarCustAlgmtId);
		tarCustomerAlignment.setCustomer(tarCustomer);
		tarCustomerAlignment.setSalesPosition(tarSalesPosition);
		tarCustomerAlignment.setCustomerCallPlan(tarCustomerCallPlan);
		
		CustomerAlignmentDTO customerAlignmentDTO = new CustomerAlignmentDTO();
		customerAlignmentDTO.setTargetBaseCustAlgmt(tarCustomerAlignment);
		
		//source customer
		Customer srcCustomer = new Customer();
		srcCustomer.setId(srcCustId);
		
		SalesOrgHierarchy srcHierarchy = new SalesOrgHierarchy();
		srcHierarchy.setId(scrHeirId);
		
		BusinessUnit srcBusinessUnit = new BusinessUnit();
		srcBusinessUnit.setId(srcBuId);
		
		SalesTeam srcSalesTeam = new SalesTeam();
		srcSalesTeam.setId(srcStId);
		srcSalesTeam.setBusinessUnit(srcBusinessUnit);
		
		Alignment srcAlignment = new Alignment();
		srcAlignment.setId(srcAlId);
		srcAlignment.setSalesTeam(srcSalesTeam);
		
		
		SalesPosition srcSalesPosition = new SalesPosition();
		srcSalesPosition.setId(srcSpId);
		srcSalesPosition.setAlignmment(srcAlignment);
		srcSalesPosition.setHierarchy(srcHierarchy);
		
		CustomerCallPlan srcCustomerCallPlan = new CustomerCallPlan();
		srcCustomerCallPlan.setPlannedCalls(srcPlanCalls);
		
		CustomerAlignment srcCustomerAlignment = new CustomerAlignment();
		srcCustomerAlignment.setId(srcCustAlgmtId);
		srcCustomerAlignment.setCustomer(srcCustomer);
		srcCustomerAlignment.setSalesPosition(srcSalesPosition);
		srcCustomerAlignment.setCustomerCallPlan(srcCustomerCallPlan);
		
		customerAlignmentDTO.setSourceBaseCustAlgmt(srcCustomerAlignment);

		
		//user details
		UserDetails userDetails =ModelAssembler.getDefaultUserDetails();
		userDetails.setUserId(userId);
		
		customerAlignmentValidationService.validateMirrorsForCustomerAlignment(customerAlignmentDTO, userDetails);
		
		serviceResult.setDetail(customerAlignmentDTO);
		serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");

		serviceResponse.setStatus(serviceStatus);
		serviceResponse.setResult(serviceResult);
		LOGGER.info(" Returning REST response with Result.."+serviceResult.getDetail()+" and status "+serviceResponse.getStatus().getCode().getCode()+"-"+serviceResponse.getStatus().getMessage());
		return serviceResponse;
	}
	@RequestMapping(value = "/customer/alignment/pullWithEditNew/{al}/{bu}/{st}/{srcsp}/{srcsh}/{tarsp}/{tarsh}/{custId}/{srccustALId}/{isImplicitAssignment}/{isaffBased}/{isTargetted}/{prtType}/{startdate}/{endDate}/{comments}/{plannedCalls}/{businessReasonId}/{attrname}/{attrValue}/{prdId}", 
            method = RequestMethod.GET, produces = "application/json")
public @ResponseBody ServiceResponse<List<CustomerAlignment>> newPullWithEdit(
            @PathVariable("al") Long alId, @PathVariable("bu") Long buId,
            @PathVariable("st") Long stId, @PathVariable("srcsp") Long srcsp,
            @PathVariable("srcsh") Long srcsh,
            @PathVariable("tarsp") Long tarsp,
            @PathVariable("tarsh") Long tarsh,
            @PathVariable("custId") Long custId,
            @PathVariable("srccustALId") Long custALId,
            @PathVariable("isImplicitAssignment") Boolean isImplicitAssignment,
            @PathVariable("isaffBased") Boolean isaffBased,
            @PathVariable("isTargetted") Boolean isTargetted,
            @PathVariable("prtType") String prtType,
            @PathVariable("startdate") String startdate,
            @PathVariable("endDate") String endDate,
            @PathVariable("comments") String comments,
            @PathVariable("plannedCalls") int plannedCalls,
            @PathVariable("businessReasonId") long businessReasonId,
            @PathVariable("attrname") String attrname,
            @PathVariable("attrValue") String attrValue,
            @PathVariable("prdId") Long prdId,
            
            
            HttpServletRequest request) throws MetricViolationException,
            CustomerServiceException, AffiliationServiceException,
            SalesPositionServiceException, ChangeRequestServiceException,
            CallPlanServiceException, ParseException, ViewServiceException {

     ServiceStatus serviceStatus = null;
     ServiceResponse serviceResponse = new ServiceResponse();
     ServiceResult serviceResult = new ServiceResult();

     try {
            
            List<CustomerProductAlignment> newCustomerProductAlignmentList = new ArrayList<CustomerProductAlignment>();
            List<CustomerProductAlignment> oldCustomerProductAlignmentList = new ArrayList<CustomerProductAlignment>();

            CustomerAlignment orgCustAlgn = new CustomerAlignment();
            CustomerAlignment newCustAlgn = new CustomerAlignment();
            
     
            Customer oldCustomer = new Customer();
            Customer newCustomer = new Customer();
            UserDetails userDetails = ModelAssembler.getDefaultUserDetails();
            userDetails.setUserId(80);

            // Source Customer Alignment Data
            Alignment alignment = ModelAssembler.getAlignmentModel(alId, buId,
                         stId);
            SalesPosition srcsalesPosition = ModelAssembler.getSalesPosition(
                         srcsp, srcsh);
            srcsalesPosition.setAlignmment(alignment);
            oldCustomer.setId(custId);
            orgCustAlgn.setId(custALId);
            orgCustAlgn.setCustomer(oldCustomer);
            orgCustAlgn.setSalesPosition(srcsalesPosition);
            orgCustAlgn.setAffBasedAssignment(isaffBased);
            orgCustAlgn.setCustomerTargeted(isTargetted);
            orgCustAlgn.setPriority(PriorityType.valueOf(prtType));
            orgCustAlgn.setImplicitAssignment(isImplicitAssignment);

            // Target Customer Alignment Data
            newCustomer.setId(custId);
            newCustAlgn.setCustomer(newCustomer);
            newCustAlgn.setStartDate(DateUtil.getDefaultFormatDate(startdate));
            newCustAlgn.setEndDate(DateUtil.getDefaultFormatDate(endDate));
            
            SalesPosition tarsalesPosition = ModelAssembler.getSalesPosition(
                         tarsp, tarsh);
            tarsalesPosition.setAlignmment(alignment);
            newCustAlgn.setSalesPosition(tarsalesPosition);
            
            //Call Plan 
            CustomerCallPlan newCustomerCallPlan = new CustomerCallPlan();
            newCustomerCallPlan.setPlannedCalls(plannedCalls);
            newCustomerCallPlan.setType(CallPlanType.PLANNED);
            newCustAlgn.setCustomerCallPlan(newCustomerCallPlan);
           

            BusinessReason businessReason = new BusinessReason();
            
            businessReason.setId(businessReasonId);
            
            List<ExtdAttribue> list = new ArrayList<ExtdAttribue>();  
            
            ExtdAttribue extdAttribue = new ExtdAttribue();
            AttributeDefinition attrDef=new AttributeDefinition();
            attrDef.setName(attrname);
            extdAttribue.setDefinition(attrDef);
            extdAttribue.setValue(attrValue);
            list.add(extdAttribue);
            
            ExtdAttribue extdAttribue2 = new ExtdAttribue();
            AttributeDefinition attrDef2=new AttributeDefinition();
            attrDef2.setName("attr_2");
            extdAttribue2.setDefinition(attrDef2);
            extdAttribue2.setValue("232");
            list.add(extdAttribue2);
            
            ExtdAttribue extdAttribue3 = new ExtdAttribue();
            AttributeDefinition attrDef3=new AttributeDefinition();
            attrDef3.setName("attr_16");
            extdAttribue3.setDefinition(attrDef3);
            extdAttribue3.setValue("555");
            list.add(extdAttribue3);
            
            orgCustAlgn.setExtdAttributes(list);
            newCustAlgn.setExtdAttributes(list);
            
            
            List<ExtdAttribue> custPrdList = new ArrayList<ExtdAttribue>();  
            
            ExtdAttribue custPrdExtdAtt = new ExtdAttribue();
            AttributeDefinition custPrdAttrDef=new AttributeDefinition();
            custPrdAttrDef.setName(attrname);
            custPrdExtdAtt.setDefinition(custPrdAttrDef);
            custPrdExtdAtt.setValue(attrValue);
            custPrdList.add(custPrdExtdAtt);
            
            ExtdAttribue custPrdExtdAtt2 = new ExtdAttribue();
            AttributeDefinition custPrdAttrDef2=new AttributeDefinition();
            custPrdAttrDef2.setName("attr_2");
            custPrdExtdAtt2.setDefinition(custPrdAttrDef2);
            custPrdExtdAtt2.setValue("232");
            custPrdList.add(custPrdExtdAtt2);
            
            ExtdAttribue custPrdExtdAtt3 = new ExtdAttribue();
            AttributeDefinition custPrdAttrDef3=new AttributeDefinition();
            custPrdAttrDef3.setName("attr_16");
            custPrdExtdAtt3.setDefinition(custPrdAttrDef3);
            custPrdExtdAtt3.setValue("555");
            custPrdList.add(custPrdExtdAtt3);
            
            Product product1 = new Product();
            product1.setId(prdId);
            CustomerProductAlignment newCustomerProductAlignment = new CustomerProductAlignment();
            newCustomerProductAlignment.setProduct(product1);
            newCustomerProductAlignment.setCustomer(newCustomer);
            newCustomerProductAlignment.setExtdAttributes(custPrdList);
            newCustomerProductAlignmentList.add(newCustomerProductAlignment);
            
            
            Product product2 = new Product();
            product2.setId(228L);
            
            
            CustomerProductAlignment nwCustomerProductAlignment2 = new CustomerProductAlignment();
            nwCustomerProductAlignment2.setProduct(product2);
            nwCustomerProductAlignment2.setCustomer(newCustomer);
            nwCustomerProductAlignment2.setExtdAttributes(custPrdList);
            newCustomerProductAlignmentList.add(nwCustomerProductAlignment2);
            
            CustomerCallPlan oldCustomerCallPlan = new CustomerCallPlan();
            oldCustomerCallPlan.setPlannedCalls(0);
            orgCustAlgn.setCustomerCallPlan(oldCustomerCallPlan);
            
            ChangeRequest chngReq = changeRequestService.generatePullCustomerCR(srcsalesPosition, tarsalesPosition, userDetails);
            
            custAlignService.pullCustomer(chngReq, orgCustAlgn, newCustAlgn,
                         comments, userDetails, businessReason, oldCustomerProductAlignmentList, newCustomerProductAlignmentList);
            
            serviceResult.setDetail("ChangeRequest Id: " + chngReq.getId());
            
            serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
     } catch (AlignmentServiceException e) {
            serviceResult.setDetail("Exception occured " + e.getMessage());
            serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR,
                         "500 ERROR");
     }
     serviceResponse.setStatus(serviceStatus);
     serviceResponse.setResult(serviceResult);
     LOGGER.info(" Returning REST response with Result.."
                  + serviceResult.getDetail() + " and status "
                  + serviceResponse.getStatus().getCode().getCode() + "-"
                  + serviceResponse.getStatus().getMessage());
     return serviceResponse;
}

@RequestMapping(value = "/customer/alignment/pullWithEditSubmit/{crId}/{custId}/{srccustALId}/{isImplicitAssignment}/{isaffBased}/{isTargetted}/{prtType}/{startdate}/{endDate}/{comments}/{plannedCalls}/{businessReasonId}/{attrname}/{attrValue}/{prdId}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	ServiceResponse<List<CustomerAlignment>> pullWithEditSubmit(
			@PathVariable("crId") Long crId,
			@PathVariable("custId") Long custId,
			@PathVariable("srccustALId") Long custALId,
			@PathVariable("isImplicitAssignment") Boolean isImplicitAssignment,
			@PathVariable("isaffBased") Boolean isaffBased,
			@PathVariable("isTargetted") Boolean isTargetted,
			@PathVariable("prtType") String prtType,
			@PathVariable("startdate") String startdate,
			@PathVariable("endDate") String endDate,
			@PathVariable("comments") String comments,
			@PathVariable("plannedCalls") int plannedCalls,
			@PathVariable("businessReasonId") long businessReasonId,
			@PathVariable("attrname") String attrname,
			@PathVariable("attrValue") String attrValue,
			@PathVariable("prdId") Long prdId,

			HttpServletRequest request) throws MetricViolationException,
			CustomerServiceException, AffiliationServiceException,
			SalesPositionServiceException, ChangeRequestServiceException,
			CallPlanServiceException, ParseException, ViewServiceException {

		ServiceStatus serviceStatus = null;
		ServiceResponse serviceResponse = new ServiceResponse();
		ServiceResult serviceResult = new ServiceResult();

		try {

			List<CustomerProductAlignment> newCustomerProductAlignmentList = new ArrayList<CustomerProductAlignment>();
			List<CustomerProductAlignment> oldCustomerProductAlignmentList = new ArrayList<CustomerProductAlignment>();

			CustomerAlignment orgCustAlgn = new CustomerAlignment();
			CustomerAlignment newCustAlgn = new CustomerAlignment();

			Customer oldCustomer = new Customer();
			Customer newCustomer = new Customer();

			// User Details
			UserDetails userDetails = ModelAssembler.getDefaultUserDetails();
			userDetails.setUserId(80);

			BusinessReason businessReason = new BusinessReason();
			businessReason.setId(businessReasonId);

			// Change Request
			ChangeRequest chngReq = new ChangeRequest();
			chngReq.setId(crId);

			chngReq = changeRequestDAOService
					.getChangeRequestByChangeRequestId(chngReq);

			// Source Customer Alignment Data
			Alignment alignment = ModelAssembler.getAlignmentModel(chngReq
					.getRequestedSalesPosition().getAlignmment().getId(),
					chngReq.getRequestedSalesPosition().getAlignmment()
							.getSalesTeam().getBusinessUnit().getId(), chngReq
							.getRequestedSalesPosition().getAlignmment()
							.getSalesTeam().getId());

			SalesPosition srcsalesPosition = ModelAssembler.getSalesPosition(
					chngReq.getRaisedSalesPosition().getId(), chngReq
							.getRaisedSalesPosition().getHierarchy().getId());

			srcsalesPosition.setAlignmment(alignment);
			orgCustAlgn.setSalesPosition(srcsalesPosition);
			oldCustomer.setId(custId);
			oldCustomer.setId(custId);
			orgCustAlgn.setId(custALId);
			orgCustAlgn.setCustomer(oldCustomer);
			orgCustAlgn.setAffBasedAssignment(isaffBased);
			orgCustAlgn.setCustomerTargeted(isTargetted);
			orgCustAlgn.setPriority(PriorityType.valueOf(prtType));
			orgCustAlgn.setImplicitAssignment(isImplicitAssignment);

			CustomerCallPlan oldCustomerCallPlan = new CustomerCallPlan();
			oldCustomerCallPlan.setPlannedCalls(0);
			orgCustAlgn.setCustomerCallPlan(oldCustomerCallPlan);

			// Target Customer Alignment Data
			SalesPosition tarsalesPosition = ModelAssembler
					.getSalesPosition(chngReq.getRequestedSalesPosition()
							.getId(), chngReq.getRequestedSalesPosition()
							.getHierarchy().getId());

			tarsalesPosition.setAlignmment(alignment);
			newCustAlgn.setSalesPosition(tarsalesPosition);
			newCustomer.setId(custId);
			newCustAlgn.setCustomer(newCustomer);
			newCustAlgn.setStartDate(DateUtil.getDefaultFormatDate(startdate));
			newCustAlgn.setEndDate(DateUtil.getDefaultFormatDate(endDate));

			// Call Plan
			CustomerCallPlan newCustomerCallPlan = new CustomerCallPlan();
			newCustomerCallPlan.setPlannedCalls(plannedCalls);
			newCustomerCallPlan.setType(CallPlanType.PLANNED);
			newCustAlgn.setCustomerCallPlan(newCustomerCallPlan);

			List<ExtdAttribue> list = new ArrayList<ExtdAttribue>();

			ExtdAttribue extdAttribue = new ExtdAttribue();
			AttributeDefinition attrDef = new AttributeDefinition();
			attrDef.setName(attrname);
			extdAttribue.setDefinition(attrDef);
			extdAttribue.setValue(attrValue);
			list.add(extdAttribue);

			ExtdAttribue extdAttribue2 = new ExtdAttribue();
			AttributeDefinition attrDef2 = new AttributeDefinition();
			attrDef2.setName("attr_2");
			extdAttribue2.setDefinition(attrDef2);
			extdAttribue2.setValue("232");
			list.add(extdAttribue2);

			ExtdAttribue extdAttribue3 = new ExtdAttribue();
			AttributeDefinition attrDef3 = new AttributeDefinition();
			attrDef3.setName("attr_16");
			extdAttribue3.setDefinition(attrDef3);
			extdAttribue3.setValue("555");
			list.add(extdAttribue3);

			orgCustAlgn.setExtdAttributes(list);
			newCustAlgn.setExtdAttributes(list);

			List<ExtdAttribue> custPrdList = new ArrayList<ExtdAttribue>();

			ExtdAttribue custPrdExtdAtt = new ExtdAttribue();
			AttributeDefinition custPrdAttrDef = new AttributeDefinition();
			custPrdAttrDef.setName(attrname);
			custPrdExtdAtt.setDefinition(custPrdAttrDef);
			custPrdExtdAtt.setValue(attrValue);
			custPrdList.add(custPrdExtdAtt);

			ExtdAttribue custPrdExtdAtt2 = new ExtdAttribue();
			AttributeDefinition custPrdAttrDef2 = new AttributeDefinition();
			custPrdAttrDef2.setName("attr_2");
			custPrdExtdAtt2.setDefinition(custPrdAttrDef2);
			custPrdExtdAtt2.setValue("232");
			custPrdList.add(custPrdExtdAtt2);

			ExtdAttribue custPrdExtdAtt3 = new ExtdAttribue();
			AttributeDefinition custPrdAttrDef3 = new AttributeDefinition();
			custPrdAttrDef3.setName("attr_16");
			custPrdExtdAtt3.setDefinition(custPrdAttrDef3);
			custPrdExtdAtt3.setValue("555");
			custPrdList.add(custPrdExtdAtt3);

			Product product1 = new Product();
			product1.setId(prdId);
			CustomerProductAlignment newCustomerProductAlignment = new CustomerProductAlignment();
			newCustomerProductAlignment.setProduct(product1);
			newCustomerProductAlignment.setCustomer(newCustomer);
			newCustomerProductAlignment.setExtdAttributes(custPrdList);
			newCustomerProductAlignmentList.add(newCustomerProductAlignment);

			Product product2 = new Product();
			product2.setId(228L);

			CustomerProductAlignment nwCustomerProductAlignment2 = new CustomerProductAlignment();
			nwCustomerProductAlignment2.setProduct(product2);
			nwCustomerProductAlignment2.setCustomer(newCustomer);
			nwCustomerProductAlignment2.setExtdAttributes(custPrdList);
			newCustomerProductAlignmentList.add(nwCustomerProductAlignment2);

			custAlignService.pullCustomer(chngReq, orgCustAlgn, newCustAlgn,
					comments, userDetails, businessReason,
					oldCustomerProductAlignmentList,
					newCustomerProductAlignmentList);

			serviceResult.setDetail("ChangeRequest Id: " + chngReq.getId());

			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
		} catch (AlignmentServiceException e) {
			serviceResult.setDetail("Exception occured " + e.getMessage());
			serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR,
					"500 ERROR");
		}
		serviceResponse.setStatus(serviceStatus);
		serviceResponse.setResult(serviceResult);
		LOGGER.info(" Returning REST response with Result.."
				+ serviceResult.getDetail() + " and status "
				+ serviceResponse.getStatus().getCode().getCode() + "-"
				+ serviceResponse.getStatus().getMessage());
		return serviceResponse;
	}

	}
	
