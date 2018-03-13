package com.cognizant.opserv.sp.rest.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cognizant.opserv.sp.exception.CallPlanServiceException;
import com.cognizant.opserv.sp.model.Alignment;
import com.cognizant.opserv.sp.model.BusinessUnit;
import com.cognizant.opserv.sp.model.Customer;
import com.cognizant.opserv.sp.model.CustomerCallPlan;
import com.cognizant.opserv.sp.model.SalesOrgHierarchy;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.model.SalesTeam;
import com.cognizant.opserv.sp.persistence.dao.service.CallPlanDAOService;
import com.cognizant.opserv.sp.rest.util.ModelAssembler;
import com.cognizant.opserv.sp.service.callplan.CallPlanService;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.rest.common.StatusCode;
import com.cognizant.peg.web.common.ServiceResponse;
import com.cognizant.peg.web.common.ServiceResult;
import com.cognizant.peg.web.common.ServiceStatus;

@Controller
public class CallPlanRestController {
	/*
	 * Instantiate the Logger
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(CallPlanRestController.class);

	@Autowired
	private CallPlanService callPlanService;
	
	@Autowired
	private CallPlanDAOService callPlanDAOService;
	

	

	@RequestMapping(value = "/callPlan/getCustomerCallPlanDetails/{algnId}/{buId}/{stId}/{spId}/{shId}/{custId}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	ServiceResponse<List<CustomerCallPlan>> getCustomerCallPlanDetails(@PathVariable("algnId") Long algnId, @PathVariable("buId") Long buID, @PathVariable("stId") Long stId,
			@PathVariable("spId") Long spId, @PathVariable("shId") Long shId, @PathVariable("custId") Long custId, HttpServletRequest request) throws CallPlanServiceException {

		ServiceStatus serviceStatus = null;
		ServiceResponse serviceResponse = new ServiceResponse();
		ServiceResult serviceResult = new ServiceResult();

		SalesPosition salesPos = new SalesPosition();
		salesPos.setId(spId);
		SalesOrgHierarchy salesOrgHierarchy = new SalesOrgHierarchy();
		salesOrgHierarchy.setId(shId);
		salesPos.setHierarchy(salesOrgHierarchy);

		Alignment alignment = new Alignment();
		alignment.setId(algnId);
		SalesTeam salesTeam = new SalesTeam();
		salesTeam.setId(stId);
		BusinessUnit businessUnit = new BusinessUnit();
		businessUnit.setId(buID);
		alignment.setSalesTeam(salesTeam);
		salesTeam.setBusinessUnit(businessUnit);
		salesPos.setAlignmment(alignment);
		Customer customer = new Customer();
		customer.setId(custId);

		try {
			serviceResult.setDetail(callPlanService.getCustomerCallPlanDetails(customer, salesPos, ModelAssembler.getDefaultUserDetails()));

			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
		} catch (CallPlanServiceException e) {
			serviceResult.setDetail("Exception occured " + e.getMessage());
			serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR, "500 ERROR");
		}
		serviceResponse.setStatus(serviceStatus);
		serviceResponse.setResult(serviceResult);
		LOGGER.info(" Returning REST response with Result.." + serviceResult.getDetail() + " and status " + serviceResponse.getStatus().getCode().getCode() + "-"
				+ serviceResponse.getStatus().getMessage());
		return serviceResponse;
	}
	/*
	@Transactional
	@RequestMapping(value = "/callPlan/updateCallPlanCRStatusForApprove/{custCallPlanId}/{statusId}/{activeFlag}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	ServiceResponse<CustomerCallPlan> updateCallPlanCRStatusForApprove(@PathVariable("custCallPlanId") Long custCallPlanId,@PathVariable("statusId") int statusId,@PathVariable("activeFlag") Character activeFlag, HttpServletRequest request) throws CallPlanServiceException {
		
		ServiceStatus serviceStatus = null;
		ServiceResponse serviceResponse = new ServiceResponse();
		ServiceResult serviceResult = new ServiceResult();

		CustomerCallPlan customerCallPlanDetails = new CustomerCallPlan();
		
		com.cognizant.opserv.sp.model.CustomerAlignment customerAlignment = new com.cognizant.opserv.sp.model.CustomerAlignment();

		
		customerAlignment.setCustomerCallPlan(customerCallPlanDetails);
		UserDetails userDetails = ModelAssembler.getDefaultUserDetails();
		userDetails.setTenantId((short) 1);
		userDetails.setUserId(80);
				
		try {
			CallPlanDAOService.updateCallPlanCRStatusFrChngReqApprove(custCallPlanId,statusId,activeFlag,userDetails);
			serviceResult.setDetail("Success");
			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
		} catch (CallPlanServiceException e) {
			serviceResult.setDetail("Exception occured " + e.getMessage());
			serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR, "500 ERROR");
		}
		serviceResponse.setStatus(serviceStatus);
		serviceResponse.setResult(serviceResult);
		LOGGER.info(" Returning REST response with Result.." + serviceResult.getDetail() + " and status " + serviceResponse.getStatus().getCode().getCode() + "-"
				+ serviceResponse.getStatus().getMessage());
		return serviceResponse;
	}
	@Transactional
	@RequestMapping(value = "/callPlan/getCallPlanByCustAlIdForChngReq/{custAlId}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	ServiceResponse<List<CustomerCallPlan>> getCallPlanByCustAlIdForChngReq(@PathVariable("custAlId") Long custAlId, HttpServletRequest request) throws CallPlanServiceException {
		
		ServiceStatus serviceStatus = null;
		ServiceResponse serviceResponse = new ServiceResponse();
		ServiceResult serviceResult = new ServiceResult();

		CustomerCallPlan customerCallPlanDetails = new CustomerCallPlan();
		
		com.cognizant.opserv.sp.model.CustomerAlignment customerAlignment = new com.cognizant.opserv.sp.model.CustomerAlignment();

		
		customerAlignment.setCustomerCallPlan(customerCallPlanDetails);
		UserDetails userDetails = ModelAssembler.getDefaultUserDetails();
		userDetails.setTenantId((short) 1);
		userDetails.setUserId(80);
				
		try {
			serviceResult.setDetail(CallPlanOfflineService.getCallPlanByCustAlIdForChngReq(custAlId,userDetails));
			//serviceResult.setDetail("Success");
			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
		} catch (CallPlanServiceException e) {
			serviceResult.setDetail("Exception occured " + e.getMessage());
			serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR, "500 ERROR");
		}
		serviceResponse.setStatus(serviceStatus);
		serviceResponse.setResult(serviceResult);
		LOGGER.info(" Returning REST response with Result.." + serviceResult.getDetail() + " and status " + serviceResponse.getStatus().getCode().getCode() + "-"
				+ serviceResponse.getStatus().getMessage());
		return serviceResponse;
	}
	@Transactional
	@RequestMapping(value = "/callPlan/getCallPlanByCustAlIdForChngReqEdit/{custAlId}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	ServiceResponse<CustomerCallPlan> getCallPlanByCustAlIdForChngReqEdit(@PathVariable("custAlId") Long custAlId, HttpServletRequest request) throws CallPlanServiceException {
		
		ServiceStatus serviceStatus = null;
		ServiceResponse serviceResponse = new ServiceResponse();
		ServiceResult serviceResult = new ServiceResult();

		CustomerCallPlan customerCallPlanDetails = new CustomerCallPlan();
		
		com.cognizant.opserv.sp.model.CustomerAlignment customerAlignment = new com.cognizant.opserv.sp.model.CustomerAlignment();

		
		customerAlignment.setCustomerCallPlan(customerCallPlanDetails);
		UserDetails userDetails = ModelAssembler.getDefaultUserDetails();
		userDetails.setTenantId((short) 1);
		userDetails.setUserId(80);
				
		try {
			serviceResult.setDetail(CallPlanOfflineService.getCallPlanByCustAlIdForChngReqEdit(custAlId,userDetails));
			//serviceResult.setDetail("Success");
			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
		} catch (CallPlanServiceException e) {
			serviceResult.setDetail("Exception occured " + e.getMessage());
			serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR, "500 ERROR");
		}
		serviceResponse.setStatus(serviceStatus);
		serviceResponse.setResult(serviceResult);
		LOGGER.info(" Returning REST response with Result.." + serviceResult.getDetail() + " and status " + serviceResponse.getStatus().getCode().getCode() + "-"
				+ serviceResponse.getStatus().getMessage());
		return serviceResponse;
	}
	@Transactional
	@RequestMapping(value = "/callPlan/createDirBasedCallPlan/{custAlgId}/{custId}/{callPlanTypeId}/{plannedCalls}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	ServiceResponse<CustomerCallPlan> createDirBasedCallPlan( @PathVariable("custAlgId") Long custAlgId, @PathVariable("callPlanTypeId") int callPlanTypeId,
			@PathVariable("plannedCalls") int plannedCalls,@PathVariable("custId") int custId, HttpServletRequest request) throws CallPlanServiceException {
		
		ServiceStatus serviceStatus = null;
		ServiceResponse serviceResponse = new ServiceResponse();
		ServiceResult serviceResult = new ServiceResult();

		UserDetails userDetails = ModelAssembler.getDefaultUserDetails();
		userDetails.setUserId(80);
		
		List<CustomerCallPlan> customerCallPlanDetailsList = new ArrayList<CustomerCallPlan>();
		Customer customer=new Customer();
		customer.setId((long) custId);
		com.cognizant.opserv.sp.model.CustomerAlignment customerAlignment = new com.cognizant.opserv.sp.model.CustomerAlignment();
		customerAlignment.setId(custAlgId);
		CustomerCallPlan customerCallPlanDetails = new CustomerCallPlan();
		customerAlignment.setCustomerCallPlan(customerCallPlanDetails);
		customerCallPlanDetails.setPlannedCalls(plannedCalls);
		customerCallPlanDetails.setType(CallPlanType.getAttributeType(callPlanTypeId));
		customerAlignment.setCustomer(customer);
		customerCallPlanDetailsList.add(customerCallPlanDetails);	
		
		
		
		try {
			CallPlanOfflineService.createCallPlan(customerCallPlanDetailsList,customerAlignment, userDetails);
			serviceResult.setDetail("Success");
			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
		} catch (CallPlanServiceException e) {
			serviceResult.setDetail("Exception occured " + e.getMessage());
			serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR, "500 ERROR");
		}
		serviceResponse.setStatus(serviceStatus);
		serviceResponse.setResult(serviceResult);
		LOGGER.info(" Returning REST response with Result.." + serviceResult.getDetail() + " and status " + serviceResponse.getStatus().getCode().getCode() + "-"
				+ serviceResponse.getStatus().getMessage());
		return serviceResponse;
	}
	@Transactional
	@RequestMapping(value = "/callPlan/populateCallPlan/{custAlIdBase}/{custAlIdAff1}/{custAlIdAff2}/{custAlIdMirror1}/{custAlIdMirror2}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	ServiceResponse<CustomerCallPlan> populateCallPlan(@PathVariable("custAlIdBase") Long custAlIdBase,@PathVariable("custAlIdAff1") Long custAlIdAff1,
			@PathVariable("custAlIdAff2") Long custAlIdAff2,
			@PathVariable("custAlIdMirror1") Long custAlIdMirror1,@PathVariable("custAlIdMirror2") Long custAlIdMirror2,HttpServletRequest request) throws CallPlanServiceException {
		
		ServiceStatus serviceStatus = null;
		ServiceResponse serviceResponse = new ServiceResponse();
		ServiceResult serviceResult = new ServiceResult();

		
		UserDetails userDetails = ModelAssembler.getDefaultUserDetails();
		userDetails.setTenantId((short) 1);
		userDetails.setUserId(80);
		
		CustomerAlignmentDTO customerAlignmentDTO = new CustomerAlignmentDTO();
		CustomerAlignment custAlgnmntAff = new CustomerAlignment();
		CustomerAlignment custAlgnmntMirror = new CustomerAlignment();
		CustomerAlignment customerAlignment = new CustomerAlignment();
		customerAlignment.setId(custAlIdBase);
		
		customerAlignmentDTO.setSourceBaseCustAlgmt(customerAlignment);
		
        List<CustomerAlignment> sourceBaseAffCustAlgmts= new ArrayList<CustomerAlignment>();
        List<MirrorCustAlgmtDTO> sourceMirrorCustAlgmt1= new ArrayList<MirrorCustAlgmtDTO>();
		
			
				custAlgnmntAff.setId(custAlIdAff1);
				custAlgnmntAff.setId(custAlIdAff2);
				sourceBaseAffCustAlgmts.add(custAlgnmntAff);
		
		
			customerAlignmentDTO.setSourceBaseAffCustAlgmts(sourceBaseAffCustAlgmts);
		
			custAlgnmntMirror.setId(custAlIdMirror1);
			custAlgnmntMirror.setId(custAlIdMirror2);
			
		
			customerAlignmentDTO.setMirrors(sourceMirrorCustAlgmt1);
				
		try {
			CallPlanOfflineService.populateCallPlan(customerAlignmentDTO, userDetails);
			serviceResult.setDetail("Success");
			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
		} catch (CallPlanServiceException e) {
			serviceResult.setDetail("Exception occured " + e.getMessage());
			serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR, "500 ERROR");
		}
		serviceResponse.setStatus(serviceStatus);
		serviceResponse.setResult(serviceResult);
		LOGGER.info(" Returning REST response with Result.." + serviceResult.getDetail() + " and status " + serviceResponse.getStatus().getCode().getCode() + "-"
				+ serviceResponse.getStatus().getMessage());
		return serviceResponse;
	}
	
	@Transactional
	@RequestMapping(value = "/callPlan/editCustomerCallPlan/{custAlIdBase}/{custAlIdMirror1}/{custAlIdMirror2}/{plannedCalls}/{custCallPlanId}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	ServiceResponse<CustomerCallPlan> editCustomerCallPlan(@PathVariable("custAlIdBase") Long custAlIdBase,
			@PathVariable("custAlIdMirror1") Long custAlIdMirror1,@PathVariable("custAlIdMirror2") Long custAlIdMirror2,
			@PathVariable("plannedCalls") Short plannedCalls,@PathVariable("custCallPlanId") Long custCallPlanId,HttpServletRequest request) throws CallPlanServiceException {
		
		ServiceStatus serviceStatus = null;
		ServiceResponse serviceResponse = new ServiceResponse();
		ServiceResult serviceResult = new ServiceResult();

		
		UserDetails userDetails = ModelAssembler.getDefaultUserDetails();
		userDetails.setTenantId((short) 1);
		userDetails.setUserId(80);
		
		CustomerAlignmentDTO customerAlignmentDTO = new CustomerAlignmentDTO();
		CustomerAlignment customerAlignment = new CustomerAlignment();
		customerAlignment.setId(custAlIdBase);
		CustomerCallPlan customerCallPlan = new CustomerCallPlan();
		customerCallPlan.setPlannedCalls(plannedCalls);
		customerCallPlan.setId(custCallPlanId);
		customerAlignment.setCustomerCallPlan(customerCallPlan);
		customerAlignmentDTO.setTargetBaseCustAlgmt(customerAlignment);
		if(customerAlignmentDTO.getMirrors() != null && !customerAlignmentDTO.getMirrors().isEmpty()){
			for (MirrorCustAlgmtDTO custAlgnmntMirrorDTO : customerAlignmentDTO.getMirrors()) {
				
				if (custAlgnmntMirrorDTO.getSourceMirrorCustAlgmts() != null
						&& !custAlgnmntMirrorDTO.getSourceMirrorCustAlgmts().isEmpty()) {
					
					for (CustomerAlignment custAlgmntMirror : custAlgnmntMirrorDTO
							.getSourceMirrorCustAlgmts()) {
						custAlgmntMirror.setId(custAlIdMirror1);
						custAlgmntMirror.setId(custAlIdMirror2);
				}

			}
		}
		
		}		
		try {
			CallPlanOfflineService.editCustomerCallPlan(customerAlignmentDTO, userDetails);
			serviceResult.setDetail("Success");
			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
		} catch (CallPlanServiceException e) {
			serviceResult.setDetail("Exception occured " + e.getMessage());
			serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR, "500 ERROR");
		}
		serviceResponse.setStatus(serviceStatus);
		serviceResponse.setResult(serviceResult);
		LOGGER.info(" Returning REST response with Result.." + serviceResult.getDetail() + " and status " + serviceResponse.getStatus().getCode().getCode() + "-"
				+ serviceResponse.getStatus().getMessage());
		return serviceResponse;
	}*/
}
