/**
 * 
 */
package com.cognizant.opserv.sp.rest.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cognizant.opserv.sp.common.ChangeRequestStatusType;
import com.cognizant.opserv.sp.exception.AlignmentServiceException;
import com.cognizant.opserv.sp.exception.DashboardServiceException;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.rest.util.ModelAssembler;
import com.cognizant.opserv.sp.service.dashboard.DashboardService;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.rest.common.StatusCode;
import com.cognizant.peg.web.common.ServiceResponse;
import com.cognizant.peg.web.common.ServiceResult;
import com.cognizant.peg.web.common.ServiceStatus;

/**
 * The Class DashboardRestController.
 * 
 */
@Controller
public class DashboardRestController {

	/** The dashboard service. */
	@Autowired
	DashboardService dashboardService;

	/** The Constant LOGGER. */
	private static final OpservLogger LOGGER = OpservLoggerFactory
			.getOpservLogger(DashboardRestController.class);

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/dashboard/prdCount/{spList}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	ServiceResponse<Long> getProductCountForSalesPositions(
			@PathVariable("spList") String spList,
			HttpServletRequest request) throws DashboardServiceException, AlignmentServiceException {

		ServiceStatus serviceStatus = null;
		ServiceResponse serviceResponse = new ServiceResponse();
		ServiceResult serviceResult = new ServiceResult();

		try {
			UserDetails userDetails = ModelAssembler.getDefaultUserDetails();
			userDetails.setLocaleId("en_US");
			
			List<String> salesPosList= Arrays.asList(spList.split("\\s*,\\s*"));
			List<SalesPosition> salesPosition= new ArrayList<SalesPosition>();
			
			SalesPosition sp1 = ModelAssembler.getSalesPositionId(Long.parseLong(salesPosList.get(0)));
			SalesPosition sp2 = ModelAssembler.getSalesPositionId(Long.parseLong(salesPosList.get(1)));
			SalesPosition sp3 = ModelAssembler.getSalesPositionId(Long.parseLong(salesPosList.get(2)));
			SalesPosition sp4 = ModelAssembler.getSalesPositionId(Long.parseLong(salesPosList.get(3)));
			SalesPosition sp5 = ModelAssembler.getSalesPositionId(Long.parseLong(salesPosList.get(4)));
			
			salesPosition.add(sp1);
			salesPosition.add(sp2);
			salesPosition.add(sp3);
			salesPosition.add(sp4);
			salesPosition.add(sp5);
			
			serviceResult.setDetail(dashboardService.getProductCountForSalesPositions(salesPosition, userDetails));

			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
		} catch (DashboardServiceException e) {
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
	@RequestMapping(value = "/dashboard/prdNames/{spList}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	ServiceResponse<List<String>> getProductNamesForSalesPositions(
			@PathVariable("spList") String spList,
			HttpServletRequest request) throws DashboardServiceException, AlignmentServiceException {

		ServiceStatus serviceStatus = null;
		ServiceResponse serviceResponse = new ServiceResponse();
		ServiceResult serviceResult = new ServiceResult();

		try {
			UserDetails userDetails = ModelAssembler.getDefaultUserDetails();
			userDetails.setLocaleId("en_US");
			
			List<String> salesPosList= Arrays.asList(spList.split("\\s*,\\s*"));
			List<SalesPosition> salesPosition= new ArrayList<SalesPosition>();
			
			SalesPosition sp1 = ModelAssembler.getSalesPositionId(Long.parseLong(salesPosList.get(0)));
			SalesPosition sp2 = ModelAssembler.getSalesPositionId(Long.parseLong(salesPosList.get(1)));
			SalesPosition sp3 = ModelAssembler.getSalesPositionId(Long.parseLong(salesPosList.get(2)));
			SalesPosition sp4 = ModelAssembler.getSalesPositionId(Long.parseLong(salesPosList.get(3)));
			SalesPosition sp5 = ModelAssembler.getSalesPositionId(Long.parseLong(salesPosList.get(4)));
			
			salesPosition.add(sp1);
			salesPosition.add(sp2);
			salesPosition.add(sp3);
			salesPosition.add(sp4);
			salesPosition.add(sp5);
			
			serviceResult.setDetail(dashboardService.getProductNamesForSalesPositions(salesPosition, userDetails));

			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
		} catch (DashboardServiceException e) {
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
	@RequestMapping(value = "/dashboard/zipCount/{spList}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	ServiceResponse<Long> getZipCountForSalesPositions(
			@PathVariable("spList") String spList,
			HttpServletRequest request) throws DashboardServiceException, AlignmentServiceException {

		ServiceStatus serviceStatus = null;
		ServiceResponse serviceResponse = new ServiceResponse();
		ServiceResult serviceResult = new ServiceResult();

		try {
			UserDetails userDetails = ModelAssembler.getDefaultUserDetails();
			userDetails.setLocaleId("en_US");
			
			List<String> salesPosList= Arrays.asList(spList.split("\\s*,\\s*"));
			List<SalesPosition> salesPosition= new ArrayList<SalesPosition>();
			
			SalesPosition sp1 = ModelAssembler.getSalesPositionId(Long.parseLong(salesPosList.get(0)));
			SalesPosition sp2 = ModelAssembler.getSalesPositionId(Long.parseLong(salesPosList.get(1)));
			SalesPosition sp3 = ModelAssembler.getSalesPositionId(Long.parseLong(salesPosList.get(2)));
			SalesPosition sp4 = ModelAssembler.getSalesPositionId(Long.parseLong(salesPosList.get(3)));
			SalesPosition sp5 = ModelAssembler.getSalesPositionId(Long.parseLong(salesPosList.get(4)));
			
			salesPosition.add(sp1);
			salesPosition.add(sp2);
			salesPosition.add(sp3);
			salesPosition.add(sp4);
			salesPosition.add(sp5);
			
			serviceResult.setDetail(dashboardService.getZipCountForSalesPositions(salesPosition, userDetails));

			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
		} catch (DashboardServiceException e) {
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
	@RequestMapping(value = "/dashboard/tarCustCount/{spList}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	ServiceResponse<Long> getCustomerCountForSalesPositions(
			@PathVariable("spList") String spList,
			HttpServletRequest request) throws DashboardServiceException, AlignmentServiceException {

		ServiceStatus serviceStatus = null;
		ServiceResponse serviceResponse = new ServiceResponse();
		ServiceResult serviceResult = new ServiceResult();

		try {
			UserDetails userDetails = ModelAssembler.getDefaultUserDetails();
			userDetails.setLocaleId("en_US");
			
			List<String> salesPosList= Arrays.asList(spList.split("\\s*,\\s*"));
			List<SalesPosition> salesPosition= new ArrayList<SalesPosition>();
			
			SalesPosition sp1 = ModelAssembler.getSalesPositionId(Long.parseLong(salesPosList.get(0)));
			SalesPosition sp2 = ModelAssembler.getSalesPositionId(Long.parseLong(salesPosList.get(1)));
			SalesPosition sp3 = ModelAssembler.getSalesPositionId(Long.parseLong(salesPosList.get(2)));
			SalesPosition sp4 = ModelAssembler.getSalesPositionId(Long.parseLong(salesPosList.get(3)));
			SalesPosition sp5 = ModelAssembler.getSalesPositionId(Long.parseLong(salesPosList.get(4)));
			
			salesPosition.add(sp1);
			salesPosition.add(sp2);
			salesPosition.add(sp3);
			salesPosition.add(sp4);
			salesPosition.add(sp5);
			
			serviceResult.setDetail(dashboardService.getCustomerCountForSalesPositions(salesPosition, userDetails));

			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
		} catch (DashboardServiceException e) {
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
	@RequestMapping(value = "/dashboard/geoCustCount/{spList}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	ServiceResponse<Long> getGeoAlgdCustomerCountForSalesPositions(
			@PathVariable("spList") String spList,
			HttpServletRequest request) throws DashboardServiceException, AlignmentServiceException {

		ServiceStatus serviceStatus = null;
		ServiceResponse serviceResponse = new ServiceResponse();
		ServiceResult serviceResult = new ServiceResult();

		try {
			UserDetails userDetails = ModelAssembler.getDefaultUserDetails();
			userDetails.setLocaleId("en_US");
			
			List<String> salesPosList= Arrays.asList(spList.split("\\s*,\\s*"));
			List<SalesPosition> salesPosition= new ArrayList<SalesPosition>();
			
			SalesPosition sp1 = ModelAssembler.getSalesPositionId(Long.parseLong(salesPosList.get(0)));
			SalesPosition sp2 = ModelAssembler.getSalesPositionId(Long.parseLong(salesPosList.get(1)));
			SalesPosition sp3 = ModelAssembler.getSalesPositionId(Long.parseLong(salesPosList.get(2)));
			SalesPosition sp4 = ModelAssembler.getSalesPositionId(Long.parseLong(salesPosList.get(3)));
			SalesPosition sp5 = ModelAssembler.getSalesPositionId(Long.parseLong(salesPosList.get(4)));
			
			salesPosition.add(sp1);
			salesPosition.add(sp2);
			salesPosition.add(sp3);
			salesPosition.add(sp4);
			salesPosition.add(sp5);
			
			serviceResult.setDetail(dashboardService.getGeoAlgdCustomerCountForSalesPositions(salesPosition, userDetails));

			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
		} catch (DashboardServiceException e) {
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
	@RequestMapping(value = "/dashboard/nonGeoCustCount/{spList}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	ServiceResponse<Long> getNonGeoAlgdCustomerCountForSalesPositions(
			@PathVariable("spList") String spList,
			HttpServletRequest request) throws DashboardServiceException, AlignmentServiceException {

		ServiceStatus serviceStatus = null;
		ServiceResponse serviceResponse = new ServiceResponse();
		ServiceResult serviceResult = new ServiceResult();

		try {
			UserDetails userDetails = ModelAssembler.getDefaultUserDetails();
			userDetails.setLocaleId("en_US");
			
			List<String> salesPosList= Arrays.asList(spList.split("\\s*,\\s*"));
			List<SalesPosition> salesPosition= new ArrayList<SalesPosition>();
			
			SalesPosition sp1 = ModelAssembler.getSalesPositionId(Long.parseLong(salesPosList.get(0)));
			SalesPosition sp2 = ModelAssembler.getSalesPositionId(Long.parseLong(salesPosList.get(1)));
			SalesPosition sp3 = ModelAssembler.getSalesPositionId(Long.parseLong(salesPosList.get(2)));
			SalesPosition sp4 = ModelAssembler.getSalesPositionId(Long.parseLong(salesPosList.get(3)));
			SalesPosition sp5 = ModelAssembler.getSalesPositionId(Long.parseLong(salesPosList.get(4)));
			
			salesPosition.add(sp1);
			salesPosition.add(sp2);
			salesPosition.add(sp3);
			salesPosition.add(sp4);
			salesPosition.add(sp5);
			
			serviceResult.setDetail(dashboardService.getNonGeoAlgdCustomerCountForSalesPositions(salesPosition, userDetails));

			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
		} catch (DashboardServiceException e) {
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

	/**
	 * Gets the cR count by sales position and status.
	 *
	 * @param spIds the sp ids
	 * @param crStatusType the cr status type
	 * @param request the request
	 * @return the cR count by sales position and status
	 * @throws DashboardServiceException the dashboard service exception
	 * @throws AlignmentServiceException the alignment service exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/dashboard/getCRCount/{sps}/{crStatus}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	ServiceResponse<Integer> getCRCountBySalesPositionAndStatus(@PathVariable("sps") List<String> spIds, @PathVariable("crStatus")  String crStatusType,
			HttpServletRequest request) throws DashboardServiceException, AlignmentServiceException {

		ServiceStatus serviceStatus = null;
		ServiceResponse serviceResponse = new ServiceResponse();
		ServiceResult serviceResult = new ServiceResult();

		try {
			UserDetails userDetails = ModelAssembler.getDefaultUserDetails();
			userDetails.setLocaleId("en_US");
			
			List<SalesPosition> sps= new ArrayList<SalesPosition>();

			for(String spId : spIds){
				SalesPosition sp = new SalesPosition();
				sp.setId(Long.valueOf(spId));
				sps.add(sp);
			}
			
			ChangeRequestStatusType statusType = ChangeRequestStatusType.valueOf(crStatusType);
			
			serviceResult.setDetail(dashboardService.getCRCountBySalesPositionAndStatus(sps, statusType, userDetails));

			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
		} catch (DashboardServiceException e) {
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
	
	/**
	 * Gets the approvers count by sales position and status.
	 *
	 * @param spIds the sp ids
	 * @param crStatusType the cr status type
	 * @param request the request
	 * @return the approvers count by sales position and status
	 * @throws DashboardServiceException the dashboard service exception
	 * @throws AlignmentServiceException the alignment service exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/dashboard/getApproversCount/{sps}/{crStatus}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	ServiceResponse<Integer> getApproversCountBySalesPositionAndStatus(@PathVariable("sps") List<String> spIds, @PathVariable("crStatus")  String crStatusType,
			HttpServletRequest request) throws DashboardServiceException, AlignmentServiceException {

		ServiceStatus serviceStatus = null;
		ServiceResponse serviceResponse = new ServiceResponse();
		ServiceResult serviceResult = new ServiceResult();

		try {
			UserDetails userDetails = ModelAssembler.getDefaultUserDetails();
			userDetails.setLocaleId("en_US");
			
			List<SalesPosition> sps= new ArrayList<SalesPosition>();

			for(String spId : spIds){
				SalesPosition sp = new SalesPosition();
				sp.setId(Long.valueOf(spId));
				sps.add(sp);
			}
			
			ChangeRequestStatusType statusType = ChangeRequestStatusType.valueOf(crStatusType);
			
			serviceResult.setDetail(dashboardService.getApproversCountBySalesPositionAndStatus(sps, statusType, userDetails));

			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
		} catch (DashboardServiceException e) {
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
