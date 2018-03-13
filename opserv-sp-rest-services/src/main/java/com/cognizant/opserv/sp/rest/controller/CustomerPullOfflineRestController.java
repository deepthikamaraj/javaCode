package com.cognizant.opserv.sp.rest.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cognizant.opserv.sp.common.ChangeRequestStatusType;
import com.cognizant.opserv.sp.common.util.DateUtil;
import com.cognizant.opserv.sp.cr.process.dto.CustomerAlignmentDTO;
import com.cognizant.opserv.sp.cr.process.dto.MirrorCustAlgmtDTO;
import com.cognizant.opserv.sp.cr.process.internal.service.CustomerPullOfflineService;
import com.cognizant.opserv.sp.exception.AffiliationServiceException;
import com.cognizant.opserv.sp.exception.AlignmentServiceException;
import com.cognizant.opserv.sp.exception.CallPlanServiceException;
import com.cognizant.opserv.sp.exception.ChangeRequestServiceException;
import com.cognizant.opserv.sp.exception.SalesPositionServiceException;
import com.cognizant.opserv.sp.model.Alignment;
import com.cognizant.opserv.sp.model.BusinessUnit;
import com.cognizant.opserv.sp.model.Customer;
import com.cognizant.opserv.sp.model.CustomerAlignment;
import com.cognizant.opserv.sp.model.CustomerCallPlan;
import com.cognizant.opserv.sp.model.CustomerProductAlignment;
import com.cognizant.opserv.sp.model.SalesOrgHierarchy;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.model.SalesTeam;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.model.cr.ChangeRequest;
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
 * @class CustomerPullOfflineRestController contains to call the rest services for CustomerPullOfflineService
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 29/09/2016
 * ***************************************************************************
 */
@Controller
public class CustomerPullOfflineRestController {
	
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(CustomerPullOfflineRestController.class);


	@Autowired
	CustomerPullOfflineService customerPullOfflineService;
	
	
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
	@RequestMapping(value="/customerpulloffline/validate/" +
			"{srcCustId}/{srcSpId}/{scrHeirId}/{srcAlId}/{srcBuId}/{srcStId}/{srcPlanCalls}/{srcCustAlgmtId}/" +
			"{tarCustId}/{tarSpId}/{tarHeirId}/{tarAlId}/{tarBuId}/{tarStId}/{tarPlanCalls}/{tarCustAlgmtId}/" +
			"{userId}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody ServiceResponse<List<CustomerProductAlignment>> validateCustomerAlignmentDTO(
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
				HttpServletRequest request) throws AffiliationServiceException, AlignmentServiceException, SalesPositionServiceException {
		
		
		
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
		
		customerPullOfflineService.validate(customerAlignmentDTO, userDetails);
		
		serviceResult.setDetail(customerAlignmentDTO);
		serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
		serviceResponse.setStatus(serviceStatus);
		serviceResponse.setResult(serviceResult);
		LOGGER.info(" Returning REST response with Result.."+serviceResult.getDetail()+" and status "+serviceResponse.getStatus().getCode().getCode()+"-"+serviceResponse.getStatus().getMessage());
		return serviceResponse;
	}
	

	@RequestMapping(value="/CustomerPullOffline/geoUpdateChangeRequests/{chngreqId}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody
	ServiceResponse<Boolean> geoUpdateChangeRequests(@PathVariable("chngreqId") Long chngreqId, HttpServletRequest request)
			throws ChangeRequestServiceException, AlignmentServiceException {

		ServiceStatus serviceStatus = null;
		ServiceResponse serviceResponse = new ServiceResponse();
		ServiceResult serviceResult = new ServiceResult();
		try{
			
		CustomerAlignmentDTO customerAlgmtDTO=new CustomerAlignmentDTO();
		ChangeRequest parentCR = new ChangeRequest();
		parentCR.setId(chngreqId);
		parentCR.setActive(true);
		parentCR.setBusinessReason("Reason");
		parentCR.setComments("comments");
		parentCR.setCode("code");
		parentCR.setCreatedBy(1);
		parentCR.setCreatedDate(DateUtil.getCurrentDate());
		parentCR.setName("PUSH_CUSTOMER");
		parentCR.setStatus(ChangeRequestStatusType.PENDING_FOR_SUBMISSION);
		parentCR.setTenantId((short)1);		
		customerAlgmtDTO.setMainCR(parentCR);
		customerPullOfflineService.updateChangeRequests(customerAlgmtDTO, ModelAssembler.getDefaultUserDetails());
		serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
			} catch (Exception e) {
				serviceResult.setDetail("Exception occured "+e.getMessage());
				serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR, "500 ERROR");
			}
		serviceResponse.setStatus(serviceStatus);
		serviceResponse.setResult(serviceResult);
		LOGGER.info(" Returning REST response with Result.."+serviceResult.getDetail()+" and status "+serviceResponse.getStatus().getCode().getCode()+"-"+serviceResponse.getStatus().getMessage());
		return serviceResponse;
	}
	

	@RequestMapping(value="/CustomerPullOffline/getMirrorCRsForPush/{chngreqId}", method=RequestMethod.GET, produces="application/json")	public @ResponseBody
	ServiceResponse<Boolean> getMirrorCRsForPush(@PathVariable("chngreqId") Long chngreqId, HttpServletRequest request) throws ChangeRequestServiceException {
		ServiceStatus serviceStatus = null;
		ServiceResponse serviceResponse = new ServiceResponse();
		ServiceResult serviceResult = new ServiceResult();
		try {
		
		CustomerAlignmentDTO custAlgmtDTO=new CustomerAlignmentDTO();
		ChangeRequest parentCR = new ChangeRequest();
		parentCR.setId(chngreqId);
		parentCR.setActive(true);
		parentCR.setBusinessReason("Reason");
		parentCR.setComments("comments");
		parentCR.setCode("code");
		parentCR.setCreatedBy(1);
		parentCR.setCreatedDate(DateUtil.getCurrentDate());
		parentCR.setName("PUSH_CUSTOMER");
		parentCR.setStatus(ChangeRequestStatusType.PENDING_FOR_SUBMISSION);
		parentCR.setTenantId((short)1);	
		
		custAlgmtDTO.setMainCR(parentCR);
		List<MirrorCustAlgmtDTO> list=new ArrayList<>();
		MirrorCustAlgmtDTO mirrors=new MirrorCustAlgmtDTO();
		mirrors.setChildCR(parentCR);

		list.add(mirrors);
		
		
		
		custAlgmtDTO.setMirrors(list);
		//custAlgmtDTO.setMirrors()
		
		customerPullOfflineService.getMirrorCRsForPull(custAlgmtDTO,  ModelAssembler.getDefaultUserDetails());
		
		
			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
		} catch (Exception e) {
			serviceResult.setDetail("Exception occured "+e.getMessage());
			serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR, "500 ERROR");
		}
		serviceResponse.setStatus(serviceStatus);
		serviceResponse.setResult(serviceResult);
		LOGGER.info(" Returning REST response with Result.."+serviceResult.getDetail()+" and status "+serviceResponse.getStatus().getCode().getCode()+"-"+serviceResponse.getStatus().getMessage());
		return serviceResponse;
	}
	
	

	@RequestMapping(value = "/CustomerPullOffline/createMirrorCR/{salespoId}/{buId}/{salesTeamId}/{almntId}/{hierarchyId}/{salespoTrgId}/{hierarchyTrgId}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	ServiceResponse<Boolean> createMirrorCR(
			@PathVariable("salespoId") Long salespoId,
			@PathVariable("buId") Long buId,
			@PathVariable("salesTeamId") Long salesTeamId,
			@PathVariable("almntId") Long almntId,
			@PathVariable("hierarchyId") Long hierarchyId,
			@PathVariable("salespoTrgId") Long salespoTrgId,
			@PathVariable("hierarchyTrgId") Long hierarchyTrgId,
			HttpServletRequest request)
			throws ChangeRequestServiceException, AlignmentServiceException {

		ServiceStatus serviceStatus = null;
		ServiceResponse serviceResponse = new ServiceResponse();
		ServiceResult serviceResult = new ServiceResult();
		try {
			ChangeRequest parentCR = new ChangeRequest();
			MirrorCustAlgmtDTO mirrorDTO = new MirrorCustAlgmtDTO();
			UserDetails userDts = new UserDetails();
			userDts.setTenantId(Short.parseShort("1"));
			userDts.setTenantCode("am");
			userDts.setUserId(80);

			SalesPosition sourceSP = new SalesPosition();
			sourceSP.setId(salespoId);

			Alignment alignment = new Alignment();
			SalesTeam salesTeam = new SalesTeam();
			BusinessUnit businessUnit = new BusinessUnit();
			businessUnit.setId(buId);
			salesTeam.setBusinessUnit(businessUnit);
			salesTeam.setId(salesTeamId);
			alignment.setSalesTeam(salesTeam);
			alignment.setId(almntId);
			sourceSP.setAlignmment(alignment);
			SalesOrgHierarchy salesOrgHierarchy = new SalesOrgHierarchy();
			salesOrgHierarchy.setId(hierarchyId);
			sourceSP.setHierarchy(salesOrgHierarchy);
			mirrorDTO.setSourceSP(sourceSP);

			SalesPosition targetSP = new SalesPosition();
			targetSP.setId(salespoTrgId);
			SalesOrgHierarchy tarsalesOrgHierarchy = new SalesOrgHierarchy();
			tarsalesOrgHierarchy.setId(hierarchyTrgId);
			targetSP.setHierarchy(tarsalesOrgHierarchy);
			ChangeRequest parentCR1 = new ChangeRequest();
			mirrorDTO.setTargetSP(targetSP);
			
			customerPullOfflineService.createMirrorCR(parentCR1, mirrorDTO,userDts);
			
		serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
		serviceResult.setDetail(true);

		} catch (Exception e) {
			serviceResult.setDetail("Exception occured "+e.getMessage());
			serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR, "500 ERROR");
		}
		serviceResponse.setStatus(serviceStatus);
		serviceResponse.setResult(serviceResult);
		LOGGER.info(" Returning REST response with Result.."+serviceResult.getDetail()+" and status "+serviceResponse.getStatus().getCode().getCode()+"-"+serviceResponse.getStatus().getMessage());
		return serviceResponse;
	}
}
