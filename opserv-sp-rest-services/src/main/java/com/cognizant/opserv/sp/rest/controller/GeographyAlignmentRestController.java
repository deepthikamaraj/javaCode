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

import com.cognizant.opserv.sp.common.PriorityType;
import com.cognizant.opserv.sp.common.util.DateUtil;
import com.cognizant.opserv.sp.exception.AffiliationServiceException;
import com.cognizant.opserv.sp.exception.AlignmentServiceException;
import com.cognizant.opserv.sp.exception.CallPlanServiceException;
import com.cognizant.opserv.sp.exception.ChangeRequestServiceException;
import com.cognizant.opserv.sp.exception.CustomerServiceException;
import com.cognizant.opserv.sp.exception.MetricViolationException;
import com.cognizant.opserv.sp.exception.SalesPositionServiceException;
import com.cognizant.opserv.sp.exception.ViewServiceException;
import com.cognizant.opserv.sp.model.Alignment;
import com.cognizant.opserv.sp.model.Customer;
import com.cognizant.opserv.sp.model.CustomerAlignment;
import com.cognizant.opserv.sp.model.GeographyAlignment;
import com.cognizant.opserv.sp.model.PostalCode;
import com.cognizant.opserv.sp.model.SalesOrgHierarchy;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.model.cr.ChangeRequest;
import com.cognizant.opserv.sp.persistence.dao.service.ChangeRequestDAOService;
import com.cognizant.opserv.sp.rest.util.ModelAssembler;
import com.cognizant.opserv.sp.service.alignment.GeographyAlignmentService;
import com.cognizant.opserv.sp.service.changereq.ChangeRequestService;
import com.cognizant.opserv.sp.service.common.ChangeRequestCommonService;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.rest.common.StatusCode;
import com.cognizant.peg.web.common.ServiceResponse;
import com.cognizant.peg.web.common.ServiceResult;
import com.cognizant.peg.web.common.ServiceStatus;

@Controller
public class GeographyAlignmentRestController {

	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(CustomerAlignmentRestController.class);
	
	@Autowired
	 GeographyAlignmentService geographyAlignmentService;
	
	@Autowired
	ChangeRequestCommonService changeRequestCommonService;
	
	@Autowired
	private ChangeRequestService changeRequestService;
	
	@Autowired
	private ChangeRequestDAOService changeRequestDAOService;
	
	@RequestMapping(value="/geography/alignment/{postalCode}/{al}/{bu}/{st}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody ServiceResponse<List<PostalCode>> getAllPostalCodes(@PathVariable("postalCode") String postalCode,@PathVariable("al") Long alId,
			@PathVariable("bu") Long buId,
			@PathVariable("st") Long stId, HttpServletRequest request) {

		ServiceStatus serviceStatus = null;
		ServiceResponse serviceResponse = new ServiceResponse();
		ServiceResult serviceResult = new ServiceResult();

		try {
			Alignment alignment = ModelAssembler.getAlignmentModel(alId,buId,stId);;
			serviceResult.setList(geographyAlignmentService.getAllPostalCodes(postalCode, alignment, ModelAssembler.getDefaultUserDetails()));
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
	
	@RequestMapping(value="/geography/alignment/{postalCode}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody ServiceResponse<SalesPosition> getAlignedSalesPositionInfoForZip(@PathVariable("postalCode") String postalCode, HttpServletRequest request) {

		ServiceStatus serviceStatus1 = null;
		ServiceResponse serviceResponse1 = new ServiceResponse();
		ServiceResult serviceResult1 = new ServiceResult();

		try {
			
			PostalCode code = ModelAssembler.getPostalCodeModel(postalCode);
			serviceResult1.setDetail(geographyAlignmentService.getAlignedSalesPositionInfoForZip(code, ModelAssembler.getDefaultUserDetails()));
			serviceStatus1 = new ServiceStatus(StatusCode.OK, "200 OK");
		} catch (AlignmentServiceException e) {
			serviceResult1.setDetail("Exception occured "+e.getMessage());
			serviceStatus1 = new ServiceStatus(StatusCode.SERVER_ERROR, "500 ERROR");
		}
		serviceResponse1.setStatus(serviceStatus1);
		serviceResponse1.setResult(serviceResult1);
		LOGGER.info(" Returning REST response with Result.."+serviceResult1.getDetail()+" and status "+serviceResponse1.getStatus().getCode().getCode()+"-"+serviceResponse1.getStatus().getMessage());
		return serviceResponse1;
	
	}
	
	@RequestMapping(value="/geography/alignment/{sp}/{hier}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody ServiceResponse<List<PostalCode>> getAllGeographyAlignments(
			@PathVariable("sp") Long spId,
			@PathVariable("hier") Long hierId, HttpServletRequest request) {

		ServiceStatus serviceStatus2 = null;
		ServiceResponse serviceResponse2 = new ServiceResponse();
		ServiceResult serviceResult2 = new ServiceResult();

		try {
			SalesPosition salesPosition = ModelAssembler.getSalesPosition(spId, hierId);
			serviceResult2.setList(geographyAlignmentService.getAllGeographyAlignments(salesPosition, ModelAssembler.getDefaultUserDetails()));
			serviceStatus2 = new ServiceStatus(StatusCode.OK, "200 OK");
		} catch (AlignmentServiceException e) {
			serviceResult2.setDetail("Exception occured "+e.getMessage());
			serviceStatus2 = new ServiceStatus(StatusCode.SERVER_ERROR, "500 ERROR");
		}
		serviceResponse2.setStatus(serviceStatus2);
		serviceResponse2.setResult(serviceResult2);
		LOGGER.info(" Returning REST response with Result.."+serviceResult2.getDetail()+" and status "+serviceResponse2.getStatus().getCode().getCode()+"-"+serviceResponse2.getStatus().getMessage());
		return serviceResponse2;
	
	}
	
	@RequestMapping(value="/geography/alignment/move/{sp}/{sh}/{al}/{bu}/{st}/{zip}/{geoId}/{countryCode}/{pointZipFlag}/{sp1}/{sh1}/{al1}/{bu1}/{st1}/{custId}/{custAlgmntID}/{startDate}/{endDate}/{custFlag}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody ServiceResponse<Long> movePostalCode(
			@PathVariable("al") Long oldAlId,
			@PathVariable("bu") Long oldBuId,
			@PathVariable("st") Long oldStId,
			@PathVariable("al1") Long newAlId,
			@PathVariable("bu1") Long newBuId,
			@PathVariable("st1") Long newStId,
			@PathVariable("zip") String oldZip,
			@PathVariable("geoId") Long geoId,
			@PathVariable("countryCode") String countryCode,
			@PathVariable("pointZipFlag") boolean pointZipFlag,
			
			@PathVariable("sp") Long oldSpId,
			@PathVariable("sh") Long oldHierId,
			@PathVariable("sp1") Long newSpId,
			@PathVariable("sh1") Long newHierId,
			@PathVariable("custId") Long cust,
			@PathVariable("custAlgmntID") Long custAlgmntID,
			@PathVariable("startDate") String startDate,
			@PathVariable("endDate") String endDate,
			@PathVariable("custFlag") String custFlag,
			HttpServletRequest request) throws ViewServiceException {

		ServiceStatus serviceStatus = null;
		ServiceResponse serviceResponse = new ServiceResponse();
		ServiceResult serviceResult = new ServiceResult();

		try {
			SalesPosition salesPosition = ModelAssembler. getSalesPosition(oldSpId,oldHierId,oldAlId, oldBuId, oldStId);
			PostalCode code = ModelAssembler.getPostalCodeModel(oldZip);
			code.setGeoCode(geoId);
			GeographyAlignment oldGeoAlign = ModelAssembler.getGeographyAlignment(salesPosition, code);
			oldGeoAlign.setId(geoId);
			oldGeoAlign.setCountryCode(countryCode);
			oldGeoAlign.setPointZipFlag(pointZipFlag);
			
			
			salesPosition = ModelAssembler. getSalesPosition(newSpId,newHierId,newAlId, newBuId, newStId);
			code = ModelAssembler.getPostalCodeModel(oldZip);
			GeographyAlignment newGeoAlign = ModelAssembler.getGeographyAlignment(salesPosition, code);
			newGeoAlign.setStartDate(DateUtil.getDefaultFormatDate(startDate));
			newGeoAlign.setEndDate(DateUtil.getDefaultFormatDate(endDate));
			List<CustomerAlignment> custAligns = null;
			UserDetails userDetails = ModelAssembler.getDefaultUserDetails();
			userDetails.setUserId(80);
			if (null != custFlag && custFlag.equalsIgnoreCase("Y")) {
				custAligns = new ArrayList<CustomerAlignment>();
				CustomerAlignment orgCustAlgn = new CustomerAlignment();
				Customer oldCustomer = new Customer();
				oldCustomer.setId(cust);
				orgCustAlgn.setId(custAlgmntID);
				orgCustAlgn.setCustomer(oldCustomer);
				orgCustAlgn.setSalesPosition(salesPosition);
				orgCustAlgn.setAffBasedAssignment(false);
				orgCustAlgn.setCustomerTargeted(true);
				orgCustAlgn.setPriority(PriorityType.valueOf("TIER_1"));
				orgCustAlgn.setCompAligned(false);
				orgCustAlgn.setStartDate(DateUtil.getDefaultFormatDate(startDate));
				orgCustAlgn.setEndDate(DateUtil.getDefaultFormatDate(endDate));
				custAligns.add(orgCustAlgn);
			}
			try {
				serviceResult.setDetail(geographyAlignmentService.movePostalCodes(oldGeoAlign, newGeoAlign, custAligns, "Testing",userDetails ));
			} catch (MetricViolationException | CustomerServiceException
					| AffiliationServiceException
					| SalesPositionServiceException
					| ChangeRequestServiceException | CallPlanServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
		} catch (AlignmentServiceException e) {
			serviceResult.setDetail("Exception occured "+e.getMessage());
			serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR, "500 ERROR");
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		serviceResponse.setStatus(serviceStatus);
		serviceResponse.setResult(serviceResult);
		LOGGER.info(" Returning REST response with Result.."+serviceResult.getDetail()+" and status "+serviceResponse.getStatus().getCode().getCode()+"-"+serviceResponse.getStatus().getMessage());
		return serviceResponse;
	
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/geography/alignment/moveNew/{sp}/{sh}/{al}/{bu}/{st}/{zip}/{geoId}/{countryCode}/{pointZipFlag}/{sp1}/{sh1}/{al1}/{bu1}/{st1}/{custIds}/{custAlgmntIDs}/{startDate}/{endDate}/{custFlag}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody ServiceResponse<Long> movePostalCodeNew(
			@PathVariable("al") Long oldAlId,
			@PathVariable("bu") Long oldBuId,
			@PathVariable("st") Long oldStId,
			@PathVariable("al1") Long newAlId,
			@PathVariable("bu1") Long newBuId,
			@PathVariable("st1") Long newStId,
			@PathVariable("zip") String oldZip,
			@PathVariable("geoId") Long geoId,
			@PathVariable("countryCode") String countryCode,
			@PathVariable("pointZipFlag") boolean pointZipFlag,
			
			@PathVariable("sp") Long oldSpId,
			@PathVariable("sh") Long oldHierId,
			@PathVariable("sp1") Long newSpId,
			@PathVariable("sh1") Long newHierId,
			@PathVariable("custIds") List<Long> custIds,
			@PathVariable("custAlgmntIDs") List<Long> custAlgmntIDs,
			@PathVariable("startDate") String startDate,
			@PathVariable("endDate") String endDate,
			@PathVariable("custFlag") String custFlag,
			HttpServletRequest request) throws ViewServiceException {

		ServiceStatus serviceStatus = null;
		ServiceResponse serviceResponse = new ServiceResponse();
		@SuppressWarnings("rawtypes")
		ServiceResult serviceResult = new ServiceResult();

		try {
			SalesPosition srcSalesPosition = ModelAssembler. getSalesPosition(oldSpId,oldHierId,oldAlId, oldBuId, oldStId);
			PostalCode code = ModelAssembler.getPostalCodeModel(oldZip);
			code.setGeoCode(geoId);
			GeographyAlignment oldGeoAlign = ModelAssembler.getGeographyAlignment(srcSalesPosition, code);
			oldGeoAlign.setId(geoId);
			oldGeoAlign.setCountryCode(countryCode);
			oldGeoAlign.setPointZipFlag(pointZipFlag);
			
			
			SalesPosition tarSalesPosition = ModelAssembler. getSalesPosition(newSpId,newHierId,newAlId, newBuId, newStId);
			code = ModelAssembler.getPostalCodeModel(oldZip);
			GeographyAlignment newGeoAlign = ModelAssembler.getGeographyAlignment(tarSalesPosition, code);
			newGeoAlign.setStartDate(DateUtil.getDefaultFormatDate(startDate));
			newGeoAlign.setEndDate(DateUtil.getDefaultFormatDate(endDate));
			List<CustomerAlignment> custAligns = null;
			UserDetails userDetails = ModelAssembler.getDefaultUserDetails();
			userDetails.setUserId(80);
			if (null != custFlag && custFlag.equalsIgnoreCase("Y")) {
				custAligns = new ArrayList<CustomerAlignment>();
				for(int i=0; i < custIds.size(); i++){
					Long custId = custIds.get(i);
					Long custAlgmntID = custAlgmntIDs.get(i);
					CustomerAlignment orgCustAlgn = new CustomerAlignment();
					Customer oldCustomer = new Customer();
					oldCustomer.setId(custId);
					orgCustAlgn.setId(custAlgmntID);
					orgCustAlgn.setCustomer(oldCustomer);
					orgCustAlgn.setSalesPosition(srcSalesPosition);
					orgCustAlgn.setAffBasedAssignment(false);
					orgCustAlgn.setCustomerTargeted(true);
					orgCustAlgn.setPriority(PriorityType.valueOf("TIER_1"));
					orgCustAlgn.setCompAligned(false);
					orgCustAlgn.setStartDate(DateUtil.getDefaultFormatDate(startDate));
					orgCustAlgn.setEndDate(DateUtil.getDefaultFormatDate(endDate));
					custAligns.add(orgCustAlgn);
				}
				
			}
			try {
				
				ChangeRequest chngReq = changeRequestService.generateZipMovementCR(srcSalesPosition, tarSalesPosition, userDetails);
				
				geographyAlignmentService.movePostalCodes(chngReq,oldGeoAlign, newGeoAlign, custAligns, "ZipMovement", userDetails);
				
				serviceResult.setDetail("ChangeRequest Id: " + chngReq.getId());
				
			} catch (MetricViolationException | CustomerServiceException
					| AffiliationServiceException
					| SalesPositionServiceException
					| ChangeRequestServiceException | CallPlanServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
		} catch (AlignmentServiceException e) {
			serviceResult.setDetail("Exception occured "+e.getMessage());
			serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR, "500 ERROR");
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
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
	@RequestMapping(value="/geography/alignment/moveSubmit/{crId}/{zip}/{geoId}/{countryCode}/{pointZipFlag}/{custIds}/{custAlgmntIDs}/{startDate}/{endDate}/{custFlag}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody ServiceResponse<Long> movePostalCodeSubmit(
			@PathVariable("crId") Long crId,
			@PathVariable("zip") String oldZip,
			@PathVariable("geoId") Long geoId,
			@PathVariable("countryCode") String countryCode,
			@PathVariable("pointZipFlag") boolean pointZipFlag,
			@PathVariable("custIds") List<Long> custIds,
			@PathVariable("custAlgmntIDs") List<Long> custAlgmntIDs,
			@PathVariable("startDate") String startDate,
			@PathVariable("endDate") String endDate,
			@PathVariable("custFlag") String custFlag,
			HttpServletRequest request) throws ViewServiceException {

		ServiceStatus serviceStatus = null;
		ServiceResponse serviceResponse = new ServiceResponse();
		@SuppressWarnings("rawtypes")
		ServiceResult serviceResult = new ServiceResult();

		try {
			
			ChangeRequest chngReq = new ChangeRequest();
			chngReq.setId(crId);
			
			chngReq = changeRequestDAOService.getChangeRequestByChangeRequestId(chngReq);

			// Source Customer Alignment Data
			Alignment alignment = ModelAssembler.getAlignmentModel(chngReq.getRequestedSalesPosition().getAlignmment().getId(),
					chngReq.getRequestedSalesPosition().getAlignmment().getSalesTeam().getBusinessUnit().getId(),
					chngReq.getRequestedSalesPosition().getAlignmment().getSalesTeam().getId());
			
			SalesPosition srcSalesPosition = ModelAssembler.getSalesPosition(chngReq.getRequestedSalesPosition().getId(),
					chngReq.getRequestedSalesPosition().getHierarchy().getId());
			
			srcSalesPosition.setAlignmment(alignment);
			
			PostalCode code = ModelAssembler.getPostalCodeModel(oldZip);
			code.setGeoCode(geoId);
			GeographyAlignment oldGeoAlign = ModelAssembler.getGeographyAlignment(srcSalesPosition, code);
			oldGeoAlign.setId(geoId);
			oldGeoAlign.setCountryCode(countryCode);
			oldGeoAlign.setPointZipFlag(pointZipFlag);
			
			
			SalesPosition tarSalesPosition = ModelAssembler.getSalesPosition(chngReq.getRaisedSalesPosition().getId(),
					chngReq.getRaisedSalesPosition().getHierarchy().getId());
			
			tarSalesPosition.setAlignmment(alignment);
			
			code = ModelAssembler.getPostalCodeModel(oldZip);
			GeographyAlignment newGeoAlign = ModelAssembler.getGeographyAlignment(tarSalesPosition, code);
			newGeoAlign.setStartDate(DateUtil.getDefaultFormatDate(startDate));
			newGeoAlign.setEndDate(DateUtil.getDefaultFormatDate(endDate));
			List<CustomerAlignment> custAligns = null;
			UserDetails userDetails = ModelAssembler.getDefaultUserDetails();
			userDetails.setUserId(80);
			if (null != custFlag && custFlag.equalsIgnoreCase("Y")) {
				custAligns = new ArrayList<CustomerAlignment>();
				for(int i=0; i < custIds.size(); i++){
					Long custId = custIds.get(i);
					Long custAlgmntID = custAlgmntIDs.get(i);
					CustomerAlignment orgCustAlgn = new CustomerAlignment();
					Customer oldCustomer = new Customer();
					oldCustomer.setId(custId);
					orgCustAlgn.setId(custAlgmntID);
					orgCustAlgn.setCustomer(oldCustomer);
					orgCustAlgn.setSalesPosition(srcSalesPosition);
					orgCustAlgn.setAffBasedAssignment(false);
					orgCustAlgn.setCustomerTargeted(true);
					orgCustAlgn.setPriority(PriorityType.valueOf("TIER_1"));
					orgCustAlgn.setCompAligned(false);
					orgCustAlgn.setStartDate(DateUtil.getDefaultFormatDate(startDate));
					orgCustAlgn.setEndDate(DateUtil.getDefaultFormatDate(endDate));
					custAligns.add(orgCustAlgn);
				}
				
			}
			try {
				
				geographyAlignmentService.movePostalCodes(chngReq,oldGeoAlign, newGeoAlign, custAligns, "ZipMovement", userDetails);
				
				serviceResult.setDetail("ChangeRequest Id: " + chngReq.getId());
				
			} catch (MetricViolationException | CustomerServiceException
					| AffiliationServiceException
					| SalesPositionServiceException
					| ChangeRequestServiceException | CallPlanServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
		} catch (AlignmentServiceException e) {
			serviceResult.setDetail("Exception occured "+e.getMessage());
			serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR, "500 ERROR");
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		serviceResponse.setStatus(serviceStatus);
		serviceResponse.setResult(serviceResult);
		LOGGER.info(" Returning REST response with Result.."
				+ serviceResult.getDetail() + " and status "
				+ serviceResponse.getStatus().getCode().getCode() + "-"
				+ serviceResponse.getStatus().getMessage());
		return serviceResponse;
	}	
	
	@RequestMapping(value="/geography/alignment/movemultiple/{sp}/{sh}/{al}/{bu}/{st}/{zip}/{geoId}/{countryCode}/{pointZipFlag}/{sp1}/{sh1}/{al1}/{bu1}/{st1}/{custId}/{custAlgmntID}/{startDate}/{endDate}/{custFlag}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody ServiceResponse<Long> movePostalCodeOffline(
			@PathVariable("al") Long oldAlId,
			@PathVariable("bu") Long oldBuId,
			@PathVariable("st") Long oldStId,
			@PathVariable("al1") Long newAlId,
			@PathVariable("bu1") Long newBuId,
			@PathVariable("st1") Long newStId,
			@PathVariable("zip") List<String> oldZip,
			@PathVariable("geoId") List<Long> geoId,
			@PathVariable("countryCode") String countryCode,
			@PathVariable("pointZipFlag") boolean pointZipFlag,
			
			@PathVariable("sp") List<Long> oldSpId,
			@PathVariable("sh") List<Long> oldHierId,
			@PathVariable("sp1") Long newSpId,
			@PathVariable("sh1") Long newHierId,
			@PathVariable("custId") Long cust,
			@PathVariable("custAlgmntID") Long custAlgmntID,
			@PathVariable("startDate") String startDate,
			@PathVariable("endDate") String endDate,
			@PathVariable("custFlag") String custFlag,
			HttpServletRequest request) throws ViewServiceException {

		ServiceStatus serviceStatus = null;
		ServiceResponse serviceResponse = new ServiceResponse();
		ServiceResult serviceResult = new ServiceResult();

		try {
			
			SalesPosition salesPosition = null;
			GeographyAlignment oldGeoAlign = null;
			List<GeographyAlignment> srcGeoAlignList = new ArrayList<GeographyAlignment>();
			PostalCode code = null;
			int ctr = 0;
			for(Long sPos:oldSpId){
				salesPosition = ModelAssembler. getSalesPosition(sPos,oldHierId.get(ctr),oldAlId, oldBuId, oldStId);
				code = ModelAssembler.getPostalCodeModel(oldZip.get(ctr));
				code.setGeoCode(geoId.get(ctr));
				oldGeoAlign = ModelAssembler.getGeographyAlignment(salesPosition, code);
				oldGeoAlign.setId(geoId.get(ctr));
				oldGeoAlign.setCountryCode(countryCode);
				oldGeoAlign.setPointZipFlag(pointZipFlag);
				srcGeoAlignList.add(oldGeoAlign);
				ctr++;
			}
			
			
			
			
			SalesPosition newsalesPosition = ModelAssembler. getSalesPosition(newSpId,newHierId,newAlId, newBuId, newStId);
			GeographyAlignment newGeoAlign = ModelAssembler.getGeographyAlignment(newsalesPosition, code);
			newGeoAlign.setStartDate(DateUtil.getDefaultFormatDate(startDate));
			newGeoAlign.setEndDate(DateUtil.getDefaultFormatDate(endDate));
			List<CustomerAlignment> custAligns = null;
			UserDetails userDetails = ModelAssembler.getDefaultUserDetails();
			userDetails.setUserId(80);
			if (null != custFlag && custFlag.equalsIgnoreCase("Y")) {
				custAligns = new ArrayList<CustomerAlignment>();
				CustomerAlignment orgCustAlgn = new CustomerAlignment();
				Customer oldCustomer = new Customer();
				oldCustomer.setId(cust);
				orgCustAlgn.setId(custAlgmntID);
				orgCustAlgn.setCustomer(oldCustomer);
				orgCustAlgn.setSalesPosition(salesPosition);
				orgCustAlgn.setAffBasedAssignment(true);
				orgCustAlgn.setCustomerTargeted(true);
				orgCustAlgn.setPriority(PriorityType.valueOf("TIER_1"));
				orgCustAlgn.setCompAligned(false);
				orgCustAlgn.setStartDate(DateUtil.getDefaultFormatDate(startDate));
				orgCustAlgn.setEndDate(DateUtil.getDefaultFormatDate(endDate));
				custAligns.add(orgCustAlgn);
			}
			try {
				PostalCode pCode = null;
				for(GeographyAlignment geoAlign : srcGeoAlignList){
					pCode = new PostalCode();
					pCode.setCode(geoAlign.getPostalCode().getCode());
					newGeoAlign.setPostalCode(pCode);
					serviceResult.setDetail(geographyAlignmentService.movePostalCodes(geoAlign, newGeoAlign, custAligns, "Testing Multiple Rest",userDetails ));
					newGeoAlign.setPostalCode(null);
				}
				//serviceResult.setDetail(geographyAlignmentService.movePostalCodes(oldGeoAlign, newGeoAlign, custAligns, "Testing Rest",userDetails ));
			} catch (MetricViolationException | CustomerServiceException
					| AffiliationServiceException
					| SalesPositionServiceException
					| ChangeRequestServiceException | CallPlanServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
		} catch (AlignmentServiceException e) {
			serviceResult.setDetail("Exception occured "+e.getMessage());
			serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR, "500 ERROR");
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		serviceResponse.setStatus(serviceStatus);
		serviceResponse.setResult(serviceResult);
		LOGGER.info(" Returning REST response with Result.."+serviceResult.getDetail()+" and status "+serviceResponse.getStatus().getCode().getCode()+"-"+serviceResponse.getStatus().getMessage());
		return serviceResponse;
	}
	
	@RequestMapping(value="/geography/alignment/movemultipleNew/{sp}/{sh}/{al}/{bu}/{st}/{zip}/{geoId}/{countryCode}/{pointZipFlag}/{sp1}/{sh1}/{al1}/{bu1}/{st1}/{custId}/{custAlgmntID}/{startDate}/{endDate}/{custFlag}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody ServiceResponse<Long> movePostalCodeOfflineNew(
			@PathVariable("al") Long oldAlId,
			@PathVariable("bu") Long oldBuId,
			@PathVariable("st") Long oldStId,
			@PathVariable("al1") Long newAlId,
			@PathVariable("bu1") Long newBuId,
			@PathVariable("st1") Long newStId,
			@PathVariable("zip") List<String> oldZip,
			@PathVariable("geoId") List<Long> geoId,
			@PathVariable("countryCode") String countryCode,
			@PathVariable("pointZipFlag") boolean pointZipFlag,
			
			@PathVariable("sp") List<Long> oldSpId,
			@PathVariable("sh") List<Long> oldHierId,
			@PathVariable("sp1") Long newSpId,
			@PathVariable("sh1") Long newHierId,
			@PathVariable("custId") Long cust,
			@PathVariable("custAlgmntID") Long custAlgmntID,
			@PathVariable("startDate") String startDate,
			@PathVariable("endDate") String endDate,
			@PathVariable("custFlag") String custFlag,
			HttpServletRequest request) throws ViewServiceException {

		ServiceStatus serviceStatus = null;
		ServiceResponse serviceResponse = new ServiceResponse();
		ServiceResult serviceResult = new ServiceResult();

		try {
			
			SalesPosition salesPosition = null;
			GeographyAlignment oldGeoAlign = null;
			List<GeographyAlignment> srcGeoAlignList = new ArrayList<GeographyAlignment>();
			PostalCode code = null;
			int ctr = 0;
			for(Long sPos:oldSpId){
				salesPosition = ModelAssembler. getSalesPosition(sPos,oldHierId.get(ctr),oldAlId, oldBuId, oldStId);
				code = ModelAssembler.getPostalCodeModel(oldZip.get(ctr));
				code.setGeoCode(geoId.get(ctr));
				oldGeoAlign = ModelAssembler.getGeographyAlignment(salesPosition, code);
				oldGeoAlign.setId(geoId.get(ctr));
				oldGeoAlign.setCountryCode(countryCode);
				oldGeoAlign.setPointZipFlag(pointZipFlag);
				srcGeoAlignList.add(oldGeoAlign);
				ctr++;
			}
			
			
			
			
			SalesPosition newsalesPosition = ModelAssembler. getSalesPosition(newSpId,newHierId,newAlId, newBuId, newStId);
			GeographyAlignment newGeoAlign = ModelAssembler.getGeographyAlignment(newsalesPosition, code);
			newGeoAlign.setStartDate(DateUtil.getDefaultFormatDate(startDate));
			newGeoAlign.setEndDate(DateUtil.getDefaultFormatDate(endDate));
			List<CustomerAlignment> custAligns = null;
			UserDetails userDetails = ModelAssembler.getDefaultUserDetails();
			userDetails.setUserId(80);
			if (null != custFlag && custFlag.equalsIgnoreCase("Y")) {
				custAligns = new ArrayList<CustomerAlignment>();
				CustomerAlignment orgCustAlgn = new CustomerAlignment();
				Customer oldCustomer = new Customer();
				oldCustomer.setId(cust);
				orgCustAlgn.setId(custAlgmntID);
				orgCustAlgn.setCustomer(oldCustomer);
				orgCustAlgn.setSalesPosition(salesPosition);
				orgCustAlgn.setAffBasedAssignment(true);
				orgCustAlgn.setCustomerTargeted(true);
				orgCustAlgn.setPriority(PriorityType.valueOf("TIER_1"));
				orgCustAlgn.setCompAligned(false);
				orgCustAlgn.setStartDate(DateUtil.getDefaultFormatDate(startDate));
				orgCustAlgn.setEndDate(DateUtil.getDefaultFormatDate(endDate));
				custAligns.add(orgCustAlgn);
			}
			try {
				PostalCode pCode = null;
				for(GeographyAlignment geoAlign : srcGeoAlignList){
					pCode = new PostalCode();
					pCode.setCode(geoAlign.getPostalCode().getCode());
					newGeoAlign.setPostalCode(pCode);
					//serviceResult.setDetail(geographyAlignmentService.movePostalCodes(geoAlign, newGeoAlign, custAligns, "Testing Multiple Rest",userDetails ));
					geographyAlignmentService.movePostalCodes(null, geoAlign, newGeoAlign, custAligns, "Testing Multiple Rest", userDetails);
					newGeoAlign.setPostalCode(null);
				}
				//serviceResult.setDetail(geographyAlignmentService.movePostalCodes(oldGeoAlign, newGeoAlign, custAligns, "Testing Rest",userDetails ));
			} catch (MetricViolationException | CustomerServiceException
					| AffiliationServiceException
					| SalesPositionServiceException
					| ChangeRequestServiceException | CallPlanServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
		} catch (AlignmentServiceException e) {
			serviceResult.setDetail("Exception occured "+e.getMessage());
			serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR, "500 ERROR");
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		serviceResponse.setStatus(serviceStatus);
		serviceResponse.setResult(serviceResult);
		LOGGER.info(" Returning REST response with Result.."+serviceResult.getDetail()+" and status "+serviceResponse.getStatus().getCode().getCode()+"-"+serviceResponse.getStatus().getMessage());
		return serviceResponse;
	
	}
	
	@RequestMapping(value="/geoAlgmnt/getPostalCodes/{spId}/{shId}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody ServiceResponse<PostalCode> getPostalCodesBySalesPosition(@PathVariable("spId") Long spId,
			@PathVariable("shId") Long shId,HttpServletRequest request) {

		ServiceStatus serviceStatus = null;
		ServiceResponse<PostalCode> serviceResponse = new ServiceResponse<PostalCode>();
		ServiceResult<PostalCode> serviceResult = new ServiceResult<PostalCode>();

		try {
			UserDetails userDetails = ModelAssembler.getDefaultUserDetails();
			SalesPosition salesPosition = new SalesPosition();
			salesPosition.setId(spId);
			SalesOrgHierarchy salesOrgHierarchy = new SalesOrgHierarchy();
			salesOrgHierarchy.setId(shId);
			salesPosition.setHierarchy(salesOrgHierarchy);
			serviceResult.setList(geographyAlignmentService.getPostalCodesBySalesPosition(salesPosition, userDetails));
			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
		} catch (AlignmentServiceException e) {
			serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR, "500 ERROR");
		}
		serviceResponse.setStatus(serviceStatus);
		serviceResponse.setResult(serviceResult);
		LOGGER.info(" Returning REST response with Result.."+serviceResult.getList()+" and status "+serviceResponse.getStatus().getCode().getCode()+"-"+serviceResponse.getStatus().getMessage());
		return serviceResponse;
	}
	
	@RequestMapping(value="/geography/alignment/movezips/{spIds}/{shIds}/{alIds}/{buIds}/{stIds}/{zipIds}/{geoIds}/{countryCodes}/{pointZipFlags}/{sp1}/{sh1}/{al1}/{bu1}/{st1}/{custId}/{custAlgmntID}/{startDate}/{endDate}/{custFlag}", method=RequestMethod.GET, produces="application/json")
    public @ResponseBody ServiceResponse<Long> moveMultiplePostalCodes(
                  @PathVariable("spIds") List<Long> oldSpId,
                  @PathVariable("shIds") List<Long> oldHierId,
                  @PathVariable("alIds") Long oldAlId,
                  @PathVariable("buIds") Long oldBuId,
                  @PathVariable("stIds") Long oldStId,
                  @PathVariable("zipIds") List<String> oldZip,
                  @PathVariable("geoIds") List<Long> geoId,
                  @PathVariable("countryCodes") List<String> countryCode,
                  @PathVariable("pointZipFlags") boolean pointZipFlag,
                  
                  @PathVariable("al1") Long newAlId,
                  @PathVariable("bu1") Long newBuId,
                  @PathVariable("st1") Long newStId,
                  @PathVariable("sp1") Long newSpId,
                  @PathVariable("sh1") Long newHierId,
                  @PathVariable("custId") Long cust,
                  @PathVariable("custAlgmntID") Long custAlgmntID,
                  @PathVariable("startDate") String startDate,
                  @PathVariable("endDate") String endDate,
                  @PathVariable("custFlag") String custFlag,
                  HttpServletRequest request) throws ViewServiceException {

           ServiceStatus serviceStatus = null;
           ServiceResponse serviceResponse = new ServiceResponse();
           ServiceResult serviceResult = new ServiceResult();

           try {
                  SalesPosition salesPosition = null;
                  for( Long spId : oldSpId){
                        for( Long shId : oldHierId){
                               for( String zip : oldZip){
                                             for( Long geo : geoId){
                                                    for( String cc : countryCode){
                                                           salesPosition = ModelAssembler.getSalesPosition(spId,shId, oldAlId, oldBuId, oldStId);
                                                           PostalCode code = ModelAssembler.getPostalCodeModel(zip);
                                                           code.setGeoCode(geo);
                                                           GeographyAlignment oldGeoAlign = ModelAssembler.getGeographyAlignment(salesPosition, code);
                                                                  oldGeoAlign.setId(geo);
                                                                  oldGeoAlign.setCountryCode(cc);
                                                                  oldGeoAlign.setPointZipFlag(pointZipFlag);
                                                    
                                                                  
                                                                  SalesPosition salesPositionTarget = ModelAssembler. getSalesPosition(newSpId,newHierId,newAlId, newBuId, newStId);
                                                                  code = ModelAssembler.getPostalCodeModel(zip);
                                                                  GeographyAlignment newGeoAlign = ModelAssembler.getGeographyAlignment(salesPositionTarget, code);
                                                                  newGeoAlign.setStartDate(DateUtil.getDefaultFormatDate(startDate));
                                                                  newGeoAlign.setEndDate(DateUtil.getDefaultFormatDate(endDate));
                                                                  UserDetails userDetails = ModelAssembler.getDefaultUserDetails();
                                                                  userDetails.setUserId(80);
                                                                  
                                                                  
                                                                  
                                                                  List<CustomerAlignment> custAligns = null;
                                                                  if (null != custFlag && custFlag.equalsIgnoreCase("Y")) {
                                                                        custAligns = new ArrayList<CustomerAlignment>();
                                                                        CustomerAlignment orgCustAlgn = new CustomerAlignment();
                                                                        Customer oldCustomer = new Customer();
                                                                        oldCustomer.setId(cust);
                                                                         orgCustAlgn.setId(custAlgmntID);
                                                                         orgCustAlgn.setCustomer(oldCustomer);
                                                                         orgCustAlgn.setSalesPosition(salesPosition);
                                                                         orgCustAlgn.setAffBasedAssignment(false);
                                                                         orgCustAlgn.setCustomerTargeted(true);
                                                                         orgCustAlgn.setPriority(PriorityType.valueOf("TIER_1"));
                                                                         orgCustAlgn.setCompAligned(false);
                                                                         orgCustAlgn.setStartDate(DateUtil.getDefaultFormatDate(startDate));
                                                                         orgCustAlgn.setEndDate(DateUtil.getDefaultFormatDate(endDate));
                                                                         custAligns.add(orgCustAlgn);
                                                                         
                                                                  }
                                                                  
                                                                  serviceResult.setDetail(geographyAlignmentService.movePostalCodes(oldGeoAlign, newGeoAlign, custAligns, "Testing",userDetails ));
                                             }
                                      }
                               }
                        }
                  }

                  serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
           } catch (AlignmentServiceException e) {
                  serviceResult.setDetail("Exception occured "+e.getMessage());
                  serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR, "500 ERROR");
           } catch (ParseException e1) {
                  // TODO Auto-generated catch block
                  e1.printStackTrace();
           } catch (MetricViolationException e) {
                  // TODO Auto-generated catch block
                  e.printStackTrace();
           } catch (CustomerServiceException e) {
                  // TODO Auto-generated catch block
                  e.printStackTrace();
           } catch (AffiliationServiceException e) {
                  // TODO Auto-generated catch block
                  e.printStackTrace();
           } catch (SalesPositionServiceException e) {
                  // TODO Auto-generated catch block
                  e.printStackTrace();
           } catch (ChangeRequestServiceException e) {
                  // TODO Auto-generated catch block
                  e.printStackTrace();
           } catch (CallPlanServiceException e) {
                  // TODO Auto-generated catch block
                  e.printStackTrace();
           }
           serviceResponse.setStatus(serviceStatus);
           serviceResponse.setResult(serviceResult);
           LOGGER.info(" Returning REST response with Result.."+serviceResult.getDetail()+" and status "+serviceResponse.getStatus().getCode().getCode()+"-"+serviceResponse.getStatus().getMessage());
           return serviceResponse;
    
    }
	
	@RequestMapping(value="/geography/alignment/movezipsNew/{spIds}/{shIds}/{alIds}/{buIds}/{stIds}/{zipIds}/{geoIds}/{countryCodes}/{pointZipFlags}/{sp1}/{sh1}/{al1}/{bu1}/{st1}/{custId}/{custAlgmntID}/{startDate}/{endDate}/{custFlag}", method=RequestMethod.GET, produces="application/json")
    public @ResponseBody ServiceResponse<Long> moveMultiplePostalCodesNew(
                  @PathVariable("spIds") List<Long> oldSpId,
                  @PathVariable("shIds") List<Long> oldHierId,
                  @PathVariable("alIds") Long oldAlId,
                  @PathVariable("buIds") Long oldBuId,
                  @PathVariable("stIds") Long oldStId,
                  @PathVariable("zipIds") List<String> oldZip,
                  @PathVariable("geoIds") List<Long> geoId,
                  @PathVariable("countryCodes") List<String> countryCode,
                  @PathVariable("pointZipFlags") boolean pointZipFlag,
                  
                  @PathVariable("al1") Long newAlId,
                  @PathVariable("bu1") Long newBuId,
                  @PathVariable("st1") Long newStId,
                  @PathVariable("sp1") Long newSpId,
                  @PathVariable("sh1") Long newHierId,
                  @PathVariable("custId") Long cust,
                  @PathVariable("custAlgmntID") Long custAlgmntID,
                  @PathVariable("startDate") String startDate,
                  @PathVariable("endDate") String endDate,
                  @PathVariable("custFlag") String custFlag,
                  HttpServletRequest request) throws ViewServiceException {

           ServiceStatus serviceStatus = null;
           ServiceResponse serviceResponse = new ServiceResponse();
           ServiceResult serviceResult = new ServiceResult();

           try {
                  SalesPosition salesPosition = null;
                  for( Long spId : oldSpId){
                        for( Long shId : oldHierId){
                               for( String zip : oldZip){
                                             for( Long geo : geoId){
                                                    for( String cc : countryCode){
                                                           salesPosition = ModelAssembler.getSalesPosition(spId,shId, oldAlId, oldBuId, oldStId);
                                                           PostalCode code = ModelAssembler.getPostalCodeModel(zip);
                                                           code.setGeoCode(geo);
                                                           GeographyAlignment oldGeoAlign = ModelAssembler.getGeographyAlignment(salesPosition, code);
                                                                  oldGeoAlign.setId(geo);
                                                                  oldGeoAlign.setCountryCode(cc);
                                                                  oldGeoAlign.setPointZipFlag(pointZipFlag);
                                                    
                                                                  
                                                                  SalesPosition salesPositionTarget = ModelAssembler. getSalesPosition(newSpId,newHierId,newAlId, newBuId, newStId);
                                                                  code = ModelAssembler.getPostalCodeModel(zip);
                                                                  GeographyAlignment newGeoAlign = ModelAssembler.getGeographyAlignment(salesPositionTarget, code);
                                                                  newGeoAlign.setStartDate(DateUtil.getDefaultFormatDate(startDate));
                                                                  newGeoAlign.setEndDate(DateUtil.getDefaultFormatDate(endDate));
                                                                  UserDetails userDetails = ModelAssembler.getDefaultUserDetails();
                                                                  userDetails.setUserId(80);
                                                                  
                                                                  
                                                                  
                                                                  List<CustomerAlignment> custAligns = null;
                                                                  if (null != custFlag && custFlag.equalsIgnoreCase("Y")) {
                                                                        custAligns = new ArrayList<CustomerAlignment>();
                                                                        CustomerAlignment orgCustAlgn = new CustomerAlignment();
                                                                        Customer oldCustomer = new Customer();
                                                                        oldCustomer.setId(cust);
                                                                         orgCustAlgn.setId(custAlgmntID);
                                                                         orgCustAlgn.setCustomer(oldCustomer);
                                                                         orgCustAlgn.setSalesPosition(salesPosition);
                                                                         orgCustAlgn.setAffBasedAssignment(false);
                                                                         orgCustAlgn.setCustomerTargeted(true);
                                                                         orgCustAlgn.setPriority(PriorityType.valueOf("TIER_1"));
                                                                         orgCustAlgn.setCompAligned(false);
                                                                         orgCustAlgn.setStartDate(DateUtil.getDefaultFormatDate(startDate));
                                                                         orgCustAlgn.setEndDate(DateUtil.getDefaultFormatDate(endDate));
                                                                         custAligns.add(orgCustAlgn);
                                                                         
                                                                  }
                                                                  
                                                                  //serviceResult.setDetail(geographyAlignmentService.movePostalCodes(oldGeoAlign, newGeoAlign, custAligns, "Testing",userDetails ));
                                                                  geographyAlignmentService.movePostalCodes(null,oldGeoAlign, newGeoAlign, custAligns, "Testing",userDetails );
                                             }
                                      }
                               }
                        }
                  }

                  serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
           } catch (AlignmentServiceException e) {
                  serviceResult.setDetail("Exception occured "+e.getMessage());
                  serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR, "500 ERROR");
           } catch (ParseException e1) {
                  // TODO Auto-generated catch block
                  e1.printStackTrace();
           } catch (MetricViolationException e) {
                  // TODO Auto-generated catch block
                  e.printStackTrace();
           } catch (CustomerServiceException e) {
                  // TODO Auto-generated catch block
                  e.printStackTrace();
           } catch (AffiliationServiceException e) {
                  // TODO Auto-generated catch block
                  e.printStackTrace();
           } catch (SalesPositionServiceException e) {
                  // TODO Auto-generated catch block
                  e.printStackTrace();
           } catch (ChangeRequestServiceException e) {
                  // TODO Auto-generated catch block
                  e.printStackTrace();
           } catch (CallPlanServiceException e) {
                  // TODO Auto-generated catch block
                  e.printStackTrace();
           }
           serviceResponse.setStatus(serviceStatus);
           serviceResponse.setResult(serviceResult);
           LOGGER.info(" Returning REST response with Result.."+serviceResult.getDetail()+" and status "+serviceResponse.getStatus().getCode().getCode()+"-"+serviceResponse.getStatus().getMessage());
           return serviceResponse;
    
    }
    

	
}
