package com.cognizant.opserv.sp.rest.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cognizant.opserv.sp.common.util.DateUtil;
import com.cognizant.opserv.sp.cr.process.dto.CustomerAlignmentDTO;
import com.cognizant.opserv.sp.cr.process.dto.GeographyAlignmentDTO;
import com.cognizant.opserv.sp.cr.process.dto.MirrorCustAlgmtDTO;
import com.cognizant.opserv.sp.cr.process.dto.MirrorGeoAlgmtDTO;
import com.cognizant.opserv.sp.cr.process.internal.service.CustomerPushOfflineService;
import com.cognizant.opserv.sp.cr.process.internal.service.GeoMovementOfflineService;
import com.cognizant.opserv.sp.exception.AlignmentServiceException;
import com.cognizant.opserv.sp.exception.CallPlanServiceException;
import com.cognizant.opserv.sp.exception.ChangeRequestServiceException;
import com.cognizant.opserv.sp.exception.CustomerServiceException;
import com.cognizant.opserv.sp.exception.ViewServiceException;
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
import com.cognizant.opserv.sp.model.cr.CustomerAlignmentChangeRequestDetails;
import com.cognizant.opserv.sp.rest.util.ModelAssembler;
import com.cognizant.opserv.sp.service.changereq.ChangeRequestService;
import com.cognizant.opserv.sp.service.changereq.impl.ChangeRequestServiceImpl;
import com.cognizant.opserv.sp.service.common.ChangeRequestCommonService;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.rest.common.StatusCode;
import com.cognizant.peg.web.common.ServiceResponse;
import com.cognizant.peg.web.common.ServiceResult;
import com.cognizant.peg.web.common.ServiceStatus;
import com.cognizant.opserv.sp.model.cr.ChangeRequest;
import com.cognizant.opserv.sp.common.ChangeRequestStatusType;




/**
 * ****************************************************************************.
 *
 * @class CustomerPushOfflineRestController contains to call the rest services for CustomerPushOfflineService
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 * ***************************************************************************
 */
@Controller
public class CustomerPushOfflineRestController {

	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(CustomerPushOfflineRestController.class);

	@Autowired
	CustomerPushOfflineService customerPushOfflineService;

	@Autowired
	ChangeRequestServiceImpl changeRequestServiceImpl;

	@Autowired
	ChangeRequestCommonService changeRequestCommonService;

	@Autowired
	private ChangeRequestService changeRequestService;

	@Autowired
	private CustomerPushOfflineService custPushOfflineService;
	@Autowired
	GeoMovementOfflineService geoMovementOfflineService;

	/**
	 *  @Method getAllCustomerProductsByCustIdSpId - This method is to fetch the
	 *         Customer Product dts By CustomerId & Sales Postion Id
	 * @param salesPos
	 * @param userDetails
	 * @return ServiceResponse<List<ProductAlignment>> - Json Object list of the Customer  Product Details
	 * @throws ViewServiceException 
	 * @throws AlignmentServiceException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/customerpushoffline/lockcustomer/{custId}/{spId}/{custAlgmtId}/{userId}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody ServiceResponse<List<CustomerProductAlignment>> lockCustomerAlignment(@PathVariable("custId") Long custId, 
			@PathVariable("spId") Long spId,@PathVariable("custAlgmtId") Long custAlgmtId,@PathVariable("userId") Integer userId,		
				HttpServletRequest request) throws ViewServiceException {

		ServiceStatus serviceStatus = null;
		ServiceResponse serviceResponse = new ServiceResponse();
		ServiceResult serviceResult = new ServiceResult();
		
		try {
			CustomerAlignment customerAlgmt = new CustomerAlignment();
			SalesPosition salesPosition = new SalesPosition();
			salesPosition.setId(spId);
			
			Customer customer =new Customer();
			customer.setId(custId);
			customerAlgmt.setId(custAlgmtId);
			customerAlgmt.setSalesPosition(salesPosition);
			customerAlgmt.setCustomer(customer);
			
			UserDetails userDetails =ModelAssembler.getDefaultUserDetails();
			userDetails.setUserId(userId);
			
			serviceResult.setDetail(customerPushOfflineService.lockCustomerAlignment(customerAlgmt, userDetails));
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
	 * @throws CallPlanServiceException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/customerpushoffline/validate/" +
			"{srcCustId}/{srcSpId}/{scrHeirId}/{srcAlId}/{srcBuId}/{srcStId}/{srcPlanCalls}/{srcCustAlgmtId}/" +
			"{tarCustId}/{tarSpId}/{tarHeirId}/{tarAlId}/{tarBuId}/{tarStId}/{tarPlanCalls}/{tarCustAlgmtId}/" +
			"{userId}",
			method=RequestMethod.GET, produces="application/json")
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
				HttpServletRequest request) {
		
		
		
		ServiceStatus serviceStatus = null;
		ServiceResponse serviceResponse = new ServiceResponse();
		ServiceResult serviceResult = new ServiceResult();
		
		try {
			
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
			
			customerPushOfflineService.validate(customerAlignmentDTO, userDetails);
			
			serviceResult.setDetail(customerAlignmentDTO);
			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
		} catch (Exception e) {
			serviceResult.setDetail("CallPlan Exception occured "+e.getMessage());
			serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR, "500 ERROR");
		}
		serviceResponse.setStatus(serviceStatus);
		serviceResponse.setResult(serviceResult);
		LOGGER.info(" Returning REST response with Result.."+serviceResult.getDetail()+" and status "+serviceResponse.getStatus().getCode().getCode()+"-"+serviceResponse.getStatus().getMessage());
		return serviceResponse;
	}

	// URL : http://localhost:8080/sprs/customerPushOffline/processSourceOnSubmit/1/7/2/7/1/100/200/5/25/2?client=am		---- case 1 : for base
	// URL : http://localhost:8080/sprs/customerPushOffline/processSourceOnSubmit/1/7/2/7/1/100/200/5/25/2?client=am		---- case 2 : for base + affilated
	// URL : http://localhost:8080/sprs/customerPushOffline/processSourceOnSubmit/1/7/2/7/1/100/200/5/25/2?client=am		---- case 3 : for base + mirror
	// URL : http://localhost:8080/sprs/customerPushOffline/processSourceOnSubmit/1/7/2/7/1/100/200/5/25/2?client=am		---- case 4 : for base + affilated + mirror
                
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/customerPushOffline/processSourceOnSubmit/{mode}/{al}/{bu}/{st}/{trigger}/{custId}/{spId}/{hierId}/{custAlId}/{userId}/{custAlignIDForAffiliation}/{custAlignIDForMirror}/{salesPositionSourceIdForMirror}/{salesPositionTargetIdForMirror}/{hierIdforMirror}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    ServiceResponse<List<CustomerAlignmentChangeRequestDetails>> processSourceOnSubmitTest(
                 @PathVariable("mode") Integer mode, 
                 @PathVariable("al") Long al,
                 @PathVariable("bu") Long bu, 
                 @PathVariable("st") Long st,
                 @PathVariable("trigger") Long trigger, 
                 @PathVariable("custId") Long custId,
                 @PathVariable("spId") Long spId, 
                 @PathVariable("hierId") Long hierId,
                 @PathVariable("custAlId") Long custAlId, 
                 @PathVariable("userId") Integer userId,
                 @PathVariable("custAlignIDForAffiliation") Long custAlignIDForAffiliation,
                 @PathVariable("custAlignIDForMirror") Long custAlignIDForMirror,
                 @PathVariable("salesPositionSourceIdForMirror") Long salesPositionSourceIdForMirror,
                 @PathVariable("salesPositionTargetIdForMirror") Long salesPositionTargetIdForMirror,
                 @PathVariable("hierIdforMirror") Long hierIdforMirror,
                 HttpServletRequest request) throws AlignmentServiceException {
           

           ServiceStatus serviceStatus = null;
           ServiceResponse<List<CustomerAlignmentChangeRequestDetails>> serviceResponse = new ServiceResponse<List<CustomerAlignmentChangeRequestDetails>>();
           @SuppressWarnings("rawtypes")
           ServiceResult serviceResult = new ServiceResult();
    
           try {
                 
                 UserDetails userDetails = ModelAssembler.getDefaultUserDetails();
                 userDetails.setUserId(userId);
                 
                 Customer customer = new Customer();
                 customer.setId(custId);
                 
                 BusinessUnit businessUnit = new BusinessUnit();
                 businessUnit.setId(bu);
                 
                 SalesTeam salesTeam = new SalesTeam();
                 salesTeam.setId(st);
                 salesTeam.setBusinessUnit(businessUnit);
                 
                 Alignment alignmment = new Alignment();
                 alignmment.setId(al);
                 alignmment.setSalesTeam(salesTeam);
                 
                 SalesOrgHierarchy hier = new SalesOrgHierarchy();
                 hier.setId(hierId);
                 
                 SalesPosition salesPosition = new SalesPosition();
                 salesPosition.setId(spId);
                 salesPosition.setAlignmment(alignmment); 
                 salesPosition.setHierarchy(hier);
                 
                 CustomerAlignment sourceBaseCustAlgmt = new CustomerAlignment();
                 sourceBaseCustAlgmt.setId(custAlId);
                 sourceBaseCustAlgmt.setCustomer(customer);
                 sourceBaseCustAlgmt.setSalesPosition(salesPosition);
                 
                 SalesPosition salesPositionTarget = new SalesPosition();
                 salesPositionTarget.setHierarchy(hier);
                 salesPositionTarget.setId(spId);
                 
                 LOGGER.info("Before CR was created");
                 ChangeRequest chngReq = changeRequestService.generatePushCustomerCR(salesPosition, salesPositionTarget, userDetails);
                 LOGGER.info("CR was created");
                 
                 /***********start of setting of objects**********/
                 
                 CustomerAlignmentDTO customerAlgmtDTO = new CustomerAlignmentDTO();
                 
                 //start of Affiliated setting
                 CustomerAlignment sourceBaseCustAlgmtAffiliated = new CustomerAlignment();
                 sourceBaseCustAlgmtAffiliated.setId(custAlignIDForAffiliation);
                 sourceBaseCustAlgmtAffiliated.setCustomer(customer);
                 sourceBaseCustAlgmtAffiliated.setSalesPosition(salesPosition);
                 
                 List<CustomerAlignment> sourceBaseAffCustAlgmts = new ArrayList<CustomerAlignment>();
                 sourceBaseAffCustAlgmts.add(sourceBaseCustAlgmtAffiliated);
                 //end of Affiliated setting
                 
                 //start of Mirror setting
                 List<MirrorCustAlgmtDTO> mirrorCustAlgmtDTOs = new ArrayList<MirrorCustAlgmtDTO>();
          	 	 MirrorCustAlgmtDTO mirrorCustAlgmtDTOForMirror = new MirrorCustAlgmtDTO();
          	 	 List<CustomerAlignment> srcBaseCustAlgmtMirror = new ArrayList<CustomerAlignment>();
          	 	
                  customerAlgmtDTO.setSourceBaseCustAlgmt(sourceBaseCustAlgmt);
                  customerAlgmtDTO.setMainCR(chngReq);
                  CustomerAlignment sourceBaseCustAlgmtMirror = new CustomerAlignment();
                  sourceBaseCustAlgmtMirror.setId(custAlignIDForMirror);
                  sourceBaseCustAlgmtMirror.setCustomer(customer);
                  sourceBaseCustAlgmtMirror.setSalesPosition(salesPosition);  
                  
                  SalesOrgHierarchy hierForMirror = new SalesOrgHierarchy();
                  hierForMirror.setId(hierId);
                  
                  SalesPosition salesPositionForMirror = new SalesPosition();
                  salesPositionForMirror.setId(salesPositionSourceIdForMirror);
                  salesPositionForMirror.setAlignmment(alignmment); 
                  salesPositionForMirror.setHierarchy(hier);
                  
                  SalesPosition salesPositionTargetForMirror = new SalesPosition();
                  SalesOrgHierarchy hierforMirror = new SalesOrgHierarchy();
                  hierforMirror.setId(hierIdforMirror);
                  salesPositionTargetForMirror.setHierarchy(hierforMirror);
                  salesPositionTargetForMirror.setId(salesPositionTargetIdForMirror);
                 
                  mirrorCustAlgmtDTOForMirror.setSourceSP(salesPositionForMirror);
                  mirrorCustAlgmtDTOForMirror.setTargetSP(salesPositionTargetForMirror);
                  
                  srcBaseCustAlgmtMirror.add(sourceBaseCustAlgmtMirror);
                  mirrorCustAlgmtDTOForMirror.setSourceMirrorCustAlgmts(srcBaseCustAlgmtMirror);
                  //end of Mirror setting
                  
                  List<ChangeRequest> listOfChangeRequests = new ArrayList<ChangeRequest>();
                  
                  /***********end of setting of objects**********/
                 
                 switch(mode){
                 case 1 :      // Root Customer Alignment with Base for Line Items
                        customerAlgmtDTO.setSourceBaseCustAlgmt(sourceBaseCustAlgmt);
                        customerAlgmtDTO.setMainCR(chngReq);
                        break;
                 case 2 :      // Affiliated Customer Alignments with Base for Line Items
                        customerAlgmtDTO.setSourceBaseCustAlgmt(sourceBaseCustAlgmt);
                        customerAlgmtDTO.setMainCR(chngReq);
                        customerAlgmtDTO.setSourceBaseAffCustAlgmts(sourceBaseAffCustAlgmts);
                        break;
                 case 3 :      // Root with Mirrors for Line Items
	                    custPushOfflineService.createMirrorCR(chngReq, mirrorCustAlgmtDTOForMirror, userDetails);
	                    mirrorCustAlgmtDTOs.add(mirrorCustAlgmtDTOForMirror);
	                    customerAlgmtDTO.setMirrors(mirrorCustAlgmtDTOs);
	                    
	                    listOfChangeRequests.add(chngReq);
	                    listOfChangeRequests.add(mirrorCustAlgmtDTOForMirror.getChildCR());
	                    break;
                 case 4 :      // Root and Affiliated Customer Alignments with Mirrors for Line Items
                	 	//start of affiliation setting
                	 	customerAlgmtDTO.setSourceBaseCustAlgmt(sourceBaseCustAlgmt);
                	 	customerAlgmtDTO.setMainCR(chngReq);
                	 	customerAlgmtDTO.setSourceBaseAffCustAlgmts(sourceBaseAffCustAlgmts);
                	 	//end of affiliation setting
                     
                	 	//start of mirror setting
                	 	custPushOfflineService.createMirrorCR(chngReq, mirrorCustAlgmtDTOForMirror, userDetails);
	                    mirrorCustAlgmtDTOs.add(mirrorCustAlgmtDTOForMirror);
	                    customerAlgmtDTO.setMirrors(mirrorCustAlgmtDTOs);
	                    
	                    listOfChangeRequests.add(chngReq);
	                    listOfChangeRequests.add(mirrorCustAlgmtDTOForMirror.getChildCR());
                	 	//end of mirror setting
	                    break;
                 default :
                        LOGGER.info("Appropriate mode was not selected");
                 }
                 
                 LOGGER.info("Before processSourceOnSubmit method call");
                 customerPushOfflineService.processSourceOnSubmit(customerAlgmtDTO, userDetails);
                 LOGGER.info("processSourceOnSubmit method executed");
                 
                 LOGGER.info("calling getCustomerAlignmentChangeRequestDetailsByChangeRequest");
                 
                 List<CustomerAlignmentChangeRequestDetails> getCustomerAlignmentChangeRequestDetailsByChangeRequest = new ArrayList<CustomerAlignmentChangeRequestDetails>();
                 
                 for(ChangeRequest changeRequest: listOfChangeRequests){
                	 List<CustomerAlignmentChangeRequestDetails> getList = changeRequestServiceImpl.getCustomerAlignmentChangeRequestDetailsByChangeRequest(changeRequest, userDetails);
                	 getCustomerAlignmentChangeRequestDetailsByChangeRequest.addAll(getList);
                 }
                 
                 LOGGER.info("getCustomerAlignmentChangeRequestDetailsByChangeRequest executed successfully");
                 
                  serviceResult.setDetail(getCustomerAlignmentChangeRequestDetailsByChangeRequest);
                 serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
           } catch (ChangeRequestServiceException e) {
                 serviceResult.setDetail("Exception occured "+e.getMessage());
                 serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR, "500 ERROR");
           }
           
           serviceResponse.setStatus(serviceStatus);
           serviceResponse.setResult(serviceResult);
           LOGGER.info(" Returning REST response with Result.." + serviceResult.getDetail() + " and status " + serviceResponse.getStatus().getCode().getCode() + "-"
                        + serviceResponse.getStatus().getMessage());
           return serviceResponse;
    }
    
    
    // URL : http://localhost:8080/sprs/customerPushOffline/processTargetOnSubmit/1/7/2/7/1/100/200/5/25/2/101/102/103/104/6?client=am		---- case 1 : for base
 	// URL : http://localhost:8080/sprs/customerPushOffline/processTargetOnSubmit/1/7/2/7/1/100/200/5/25/2/101/102/103/104/6?client=am		---- case 2 : for base + affilated
 	// URL : http://localhost:8080/sprs/customerPushOffline/processTargetOnSubmit/1/7/2/7/1/100/200/5/25/2/101/102/103/104/6?client=am		---- case 3 : for base + mirror
 	// URL : http://localhost:8080/sprs/customerPushOffline/processTargetOnSubmit/1/7/2/7/1/100/200/5/25/2/101/102/103/104/6?client=am		---- case 4 : for base + affilated + mirror
                 
     @SuppressWarnings("unchecked")
     @RequestMapping(value = "/customerPushOffline/processTargetOnSubmit/{mode}/{al}/{bu}/{st}/{trigger}/{custId}/{spId}/{hierId}/{custAlId}/{userId}/{custAlignIDForAffiliation}/{custAlignIDForMirror}/{salesPositionSourceIdForMirror}/{salesPositionTargetIdForMirror}/{hierIdforMirror}", method = RequestMethod.GET, produces = "application/json")
     public @ResponseBody
     ServiceResponse<List<CustomerAlignmentChangeRequestDetails>> processTargetOnSubmit(
                  @PathVariable("mode") Integer mode, 
                  @PathVariable("al") Long al,
                  @PathVariable("bu") Long bu, 
                  @PathVariable("st") Long st,
                  @PathVariable("trigger") Long trigger, 
                  @PathVariable("custId") Long custId,
                  @PathVariable("spId") Long spId, 
                  @PathVariable("hierId") Long hierId,
                  @PathVariable("custAlId") Long custAlId, 
                  @PathVariable("userId") Integer userId,
                  @PathVariable("custAlignIDForAffiliation") Long custAlignIDForAffiliation,
                  @PathVariable("custAlignIDForMirror") Long custAlignIDForMirror,
                  @PathVariable("salesPositionSourceIdForMirror") Long salesPositionSourceIdForMirror,
                  @PathVariable("salesPositionTargetIdForMirror") Long salesPositionTargetIdForMirror,
                  @PathVariable("hierIdforMirror") Long hierIdforMirror,
                  HttpServletRequest request) throws ParseException, AlignmentServiceException, CustomerServiceException {
            

            ServiceStatus serviceStatus = null;
            ServiceResponse<List<CustomerAlignmentChangeRequestDetails>> serviceResponse = new ServiceResponse<List<CustomerAlignmentChangeRequestDetails>>();
            @SuppressWarnings("rawtypes")
            ServiceResult serviceResult = new ServiceResult();
     
            try {
                  
                  UserDetails userDetails = ModelAssembler.getDefaultUserDetails();
                  userDetails.setUserId(userId);
                  
                  Customer customer = new Customer();
                  customer.setId(custId);
                  
                  BusinessUnit businessUnit = new BusinessUnit();
                  businessUnit.setId(bu);
                  
                  SalesTeam salesTeam = new SalesTeam();
                  salesTeam.setId(st);
                  salesTeam.setBusinessUnit(businessUnit);
                  
                  Alignment alignmment = new Alignment();
                  alignmment.setId(al);
                  alignmment.setSalesTeam(salesTeam);
                  
                  SalesOrgHierarchy hier = new SalesOrgHierarchy();
                  hier.setId(hierId);
                  
                  SalesPosition salesPosition = new SalesPosition();
                  salesPosition.setId(spId);
                  salesPosition.setAlignmment(alignmment); 
                  salesPosition.setHierarchy(hier);
                  
                  CustomerAlignment sourceBaseCustAlgmt = new CustomerAlignment();
                  sourceBaseCustAlgmt.setId(custAlId);
                  sourceBaseCustAlgmt.setCustomer(customer);
                  sourceBaseCustAlgmt.setSalesPosition(salesPosition);
                  
                  CustomerAlignment targetBaseCustAlgmt = new CustomerAlignment();
                  targetBaseCustAlgmt.setCustomer(customer);
                  targetBaseCustAlgmt.setSalesPosition(salesPosition);
                  targetBaseCustAlgmt.setStartDate(DateUtil.getCurrentDate());
                  targetBaseCustAlgmt.setEndDate(DateUtil.getDefaultFormatDate("9-10-2020"));
                  
                  SalesPosition salesPositionTarget = new SalesPosition();
                  salesPositionTarget.setHierarchy(hier);
                  salesPositionTarget.setId(spId);
                  
                  LOGGER.info("Before CR was created");
                  ChangeRequest chngReq = changeRequestService.generatePushCustomerCR(salesPosition, salesPositionTarget, userDetails);
                  LOGGER.info("CR was created");
                  
                  /***********start of setting of objects**********/
                  
                  CustomerAlignmentDTO customerAlgmtDTO = new CustomerAlignmentDTO();
                  
                  //start of Affiliated setting
                  CustomerAlignment sourceBaseCustAlgmtAffiliated = new CustomerAlignment();
                  sourceBaseCustAlgmtAffiliated.setId(custAlignIDForAffiliation);
                  sourceBaseCustAlgmtAffiliated.setCustomer(customer);
                  sourceBaseCustAlgmtAffiliated.setSalesPosition(salesPosition);
                  
                  List<CustomerAlignment> sourceBaseAffCustAlgmts = new ArrayList<CustomerAlignment>();
                  sourceBaseAffCustAlgmts.add(sourceBaseCustAlgmtAffiliated);
                  //end of Affiliated setting
                  
                  //start of Mirror setting
                  List<MirrorCustAlgmtDTO> mirrorCustAlgmtDTOs = new ArrayList<MirrorCustAlgmtDTO>();
           	 	  MirrorCustAlgmtDTO mirrorCustAlgmtDTOForMirror = new MirrorCustAlgmtDTO();
           	 	  List<CustomerAlignment> srcBaseCustAlgmtMirror = new ArrayList<CustomerAlignment>();
           	 	
                   customerAlgmtDTO.setSourceBaseCustAlgmt(sourceBaseCustAlgmt);
                   customerAlgmtDTO.setTargetBaseCustAlgmt(targetBaseCustAlgmt);
                   customerAlgmtDTO.setMainCR(chngReq);
                   CustomerAlignment sourceBaseCustAlgmtMirror = new CustomerAlignment();
                   sourceBaseCustAlgmtMirror.setId(custAlignIDForMirror);
                   sourceBaseCustAlgmtMirror.setCustomer(customer);
                   sourceBaseCustAlgmtMirror.setSalesPosition(salesPosition);  
                   
                   SalesOrgHierarchy hierForMirror = new SalesOrgHierarchy();
                   hierForMirror.setId(hierId);
                   
                   SalesPosition salesPositionForMirror = new SalesPosition();
                   salesPositionForMirror.setId(salesPositionSourceIdForMirror);
                   salesPositionForMirror.setAlignmment(alignmment); 
                   salesPositionForMirror.setHierarchy(hier);
                   
                   SalesPosition salesPositionTargetForMirror = new SalesPosition();
                   SalesOrgHierarchy hierforMirror = new SalesOrgHierarchy();
                   hierforMirror.setId(hierIdforMirror);
                   salesPositionTargetForMirror.setHierarchy(hierforMirror);
                   salesPositionTargetForMirror.setId(salesPositionTargetIdForMirror);
                  
                   mirrorCustAlgmtDTOForMirror.setSourceSP(salesPositionForMirror);
                   mirrorCustAlgmtDTOForMirror.setTargetSP(salesPositionTargetForMirror);
                   
                   srcBaseCustAlgmtMirror.add(sourceBaseCustAlgmtMirror);
                   mirrorCustAlgmtDTOForMirror.setSourceMirrorCustAlgmts(srcBaseCustAlgmtMirror);
                   //end of Mirror setting
                   /***********end of setting of objects**********/
                  
                  switch(mode){
                  case 1 :      // Root Customer Alignment with Base for Line Items
                         customerAlgmtDTO.setSourceBaseCustAlgmt(sourceBaseCustAlgmt);
                         customerAlgmtDTO.setMainCR(chngReq);
                         break;
                  case 2 :      // Affiliated Customer Alignments with Base for Line Items
                         customerAlgmtDTO.setSourceBaseCustAlgmt(sourceBaseCustAlgmt);
                         customerAlgmtDTO.setMainCR(chngReq);
                         customerAlgmtDTO.setSourceBaseAffCustAlgmts(sourceBaseAffCustAlgmts);
                         break;
                  case 3 :      // Root with Mirrors for Line Items
 	                    custPushOfflineService.createMirrorCR(chngReq, mirrorCustAlgmtDTOForMirror, userDetails);
 	                    mirrorCustAlgmtDTOs.add(mirrorCustAlgmtDTOForMirror);
 	                    customerAlgmtDTO.setMirrors(mirrorCustAlgmtDTOs);
 	                    break;
                  case 4 :      // Root and Affiliated Customer Alignments with Mirrors for Line Items
                 	 	//start of affiliation setting
                 	 	customerAlgmtDTO.setSourceBaseCustAlgmt(sourceBaseCustAlgmt);
                 	 	customerAlgmtDTO.setMainCR(chngReq);
                 	 	customerAlgmtDTO.setSourceBaseAffCustAlgmts(sourceBaseAffCustAlgmts);
                 	 	//end of affiliation setting
                      
                 	 	//start of mirror setting
                 	 	custPushOfflineService.createMirrorCR(chngReq, mirrorCustAlgmtDTOForMirror, userDetails);
 	                    mirrorCustAlgmtDTOs.add(mirrorCustAlgmtDTOForMirror);
 	                    customerAlgmtDTO.setMirrors(mirrorCustAlgmtDTOs);
                 	 	//end of mirror setting
 	                    break;
                  default :
                         LOGGER.info("Appropriate mode was not selected");
                  }
                  
                  LOGGER.info("Before processSourceOnSubmit method call");
                  customerPushOfflineService.processTargetOnSubmit(customerAlgmtDTO, userDetails);
                  LOGGER.info("processSourceOnSubmit method executed");
                  
                  LOGGER.info("calling getCustomerAlignmentChangeRequestDetailsByChangeRequest");
                  List<CustomerAlignmentChangeRequestDetails> getCustomerAlignmentChangeRequestDetailsByChangeRequest = changeRequestServiceImpl.getCustomerAlignmentChangeRequestDetailsByChangeRequest(chngReq, userDetails);
                  LOGGER.info("getCustomerAlignmentChangeRequestDetailsByChangeRequest executed successfully");
                  
                   serviceResult.setDetail(getCustomerAlignmentChangeRequestDetailsByChangeRequest);
                  serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
            } catch (ChangeRequestServiceException e) {
                  serviceResult.setDetail("Exception occured "+e.getMessage());
                  serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR, "500 ERROR");
            } catch (CallPlanServiceException e) {
                serviceResult.setDetail("Exception occured "+e.getMessage());
                serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR, "500 ERROR");
            }
            
            serviceResponse.setStatus(serviceStatus);
            serviceResponse.setResult(serviceResult);
            LOGGER.info(" Returning REST response with Result.." + serviceResult.getDetail() + " and status " + serviceResponse.getStatus().getCode().getCode() + "-"
                         + serviceResponse.getStatus().getMessage());
            return serviceResponse;
     }
     @RequestMapping(value = "/customerpushoffline/getMirrorCRsForPush/{srcsp}/{srcsh}/{tarsp}/{tarsh}/{custId}", method = RequestMethod.GET, produces = "application/json")
 	public @ResponseBody
 	ServiceResponse<Boolean> getMirrorCRsForPush(
 			@PathVariable("srcsp") Long srcsp,
 			@PathVariable("srcsh") Long srcsh,
 			@PathVariable("tarsp") Long tarsp,
 			@PathVariable("tarsh") Long tarsh,
 			@PathVariable("custId") Long custId,

 			HttpServletRequest request) throws ChangeRequestServiceException {
 		ServiceStatus serviceStatus = null;
 		ServiceResponse serviceResponse = new ServiceResponse();
 		ServiceResult serviceResult = new ServiceResult();
 		try {
 		
 		CustomerAlignmentDTO custAlgmtDTO=new CustomerAlignmentDTO();
 		SalesPosition srcsalesPosition = ModelAssembler.getSalesPosition(srcsp, srcsh);
 		SalesPosition tarsalesPosition = ModelAssembler.getSalesPosition(tarsp, tarsh);
 		ChangeRequest parentCR = new ChangeRequest();
 		parentCR.setRaisedSalesPosition(srcsalesPosition);
 		parentCR.setId(custId);
 		parentCR.setRequestedSalesPosition(tarsalesPosition);
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
 		MirrorCustAlgmtDTO mirrors=new MirrorCustAlgmtDTO();
 		mirrors.setChildCR(parentCR);
 		
 		custAlgmtDTO.setMirrors((List<MirrorCustAlgmtDTO>) mirrors);
 		//custAlgmtDTO.setMirrors()
 		
 		customerPushOfflineService.getMirrorCRsForPush(custAlgmtDTO, ModelAssembler.getDefaultUserDetails());
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
 	@RequestMapping(value = "/customerpushoffline/createMirrorCR/{salespoId}/{buId}/{salesTeamId}/{almntId}/{hierarchyId}/{salespoTrgId}/{hierarchyTrgId}", method = RequestMethod.GET, produces = "application/json")
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

 		customerPushOfflineService.createMirrorCR(parentCR1, mirrorDTO,userDts);
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
 	
 	@RequestMapping(value="/customerpushoffline/updateChangeRequests/{srcsp}/{srcsh}/{tarsp}/{tarsh}/{custId}", method=RequestMethod.GET, produces="application/json")
 	public @ResponseBody
 	ServiceResponse<Boolean> updateChangeRequests(
 			@PathVariable("srcsp") Long srcsp,
 			@PathVariable("srcsh") Long srcsh,
 			@PathVariable("tarsp") Long tarsp,
 			@PathVariable("tarsh") Long tarsh,
 			@PathVariable("custId") Long custId, HttpServletRequest request)
 			throws ChangeRequestServiceException, AlignmentServiceException {

 		ServiceStatus serviceStatus = null;
 		ServiceResponse serviceResponse = new ServiceResponse();
 		ServiceResult serviceResult = new ServiceResult();
 		try{
 		CustomerAlignmentDTO customerAlgmtDTO=new CustomerAlignmentDTO();	
 		SalesPosition srcsalesPosition = ModelAssembler.getSalesPosition(srcsp, srcsh);		
 		SalesPosition tarsalesPosition = ModelAssembler.getSalesPosition(tarsp, tarsh);
 		ChangeRequest parentCR = new ChangeRequest();
 		parentCR.setRaisedSalesPosition(srcsalesPosition);
 		parentCR.setId(custId);
 		parentCR.setRequestedSalesPosition(tarsalesPosition);
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

 		customerPushOfflineService.updateChangeRequests(customerAlgmtDTO, ModelAssembler.getDefaultUserDetails());
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
 	
 	@RequestMapping(value = "/customerpushoffline/geocreateMirrorCR/{salespoId}/{buId}/{salesTeamId}/{almntId}/{hierarchyId}/{salespoTrgId}/{hierarchyTrgId}", method = RequestMethod.GET, produces = "application/json")
 	public @ResponseBody
 	ServiceResponse<Boolean> geocreateMirrorCR(
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
 			MirrorGeoAlgmtDTO mirrorDTO = new MirrorGeoAlgmtDTO();
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
 			geoMovementOfflineService.createMirrorCR(parentCR, mirrorDTO,null, userDts);
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
 	
 	@RequestMapping(value="/GeoMovement/geoUpdateChangeRequests/{chngreqId}", method=RequestMethod.GET, produces="application/json")
 	public @ResponseBody
 	ServiceResponse<Boolean> geoUpdateChangeRequests(@PathVariable("chngreqId") Long chngreqId, HttpServletRequest request)
 			throws ChangeRequestServiceException, AlignmentServiceException {

 		ServiceStatus serviceStatus = null;
 		ServiceResponse serviceResponse = new ServiceResponse();
 		ServiceResult serviceResult = new ServiceResult();
 		try{
 		GeographyAlignmentDTO geoAlgmtDTO=new GeographyAlignmentDTO();
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
 		geoAlgmtDTO.setMainCR(parentCR);

 		geoMovementOfflineService.updateChangeRequests(geoAlgmtDTO, ModelAssembler.getDefaultUserDetails());
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
}
