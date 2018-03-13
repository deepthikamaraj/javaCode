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

import com.cognizant.opserv.sp.exception.AlignmentServiceException;
import com.cognizant.opserv.sp.exception.SalesPositionServiceException;
import com.cognizant.opserv.sp.model.Alignment;
import com.cognizant.opserv.sp.model.GeoShapePolygon;
import com.cognizant.opserv.sp.model.SalesOrgHierarchy;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.rest.util.ModelAssembler;
import com.cognizant.opserv.sp.service.alignment.SalesPositionService;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.rest.common.StatusCode;
import com.cognizant.peg.web.common.ServiceResponse;
import com.cognizant.peg.web.common.ServiceResult;
import com.cognizant.peg.web.common.ServiceStatus;

/**
 * The Class SalesPositionRestController.
 */
@Controller
public class SalesPositionRestController {

	/** The Constant LOGGER. */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(SalesPositionRestController.class);

	/** The sales position service. */
	@Autowired
	SalesPositionService salesPositionService;

	/**
	 * Gets the all sales positions by alignment.
	 *
	 * @param alId the al id
	 * @param buId the bu id
	 * @param stId the st id
	 * @param request the request
	 * @return the all sales positions by alignment
	 */
	/*@RequestMapping(value="/salespos/alignment/{al}/{bu}/{st}", method=RequestMethod.GET, produces="application/json")
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
	*/
	
	/**
	 * Gets the all child sales positions.
	 *
	 * @param spId the sp id
	 * @param request the request
	 * @return the all child sales positions
	 */
	@RequestMapping(value="/salespos/getAllChildSPs/{spId}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody ServiceResponse<List<SalesPosition>> getAllChildSalesPositions(@PathVariable("spId") Long spId,
			HttpServletRequest request) {

		ServiceStatus serviceStatus = null;
		ServiceResponse serviceResponse = new ServiceResponse();
		ServiceResult serviceResult = new ServiceResult();

		try {
			serviceResult.setList(salesPositionService.getAllChildSalesPositions(spId,ModelAssembler.getDefaultUserDetails()));
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
	 * Gets the sales position information.
	 *
	 * @param spId the sp id
	 * @param request the request
	 * @return the sales position information
	 */
	@RequestMapping(value="/salespos/SalesPositionInfo/{spId}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody ServiceResponse<SalesPosition> getSalesPositionInformation(@PathVariable("spId") Long spId,
			
			HttpServletRequest request) {

		ServiceStatus serviceStatus = null;
		ServiceResponse serviceResponse = new ServiceResponse();
		ServiceResult serviceResult = new ServiceResult();

		try {
			serviceResult.setDetail(salesPositionService.getSalesPositionInformation(spId, ModelAssembler.getDefaultUserDetails()));
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
	 * Gets the all sales positions by name.
	 *
	 * @param spName the sp name
	 * @param alId the al id
	 * @param buId the bu id
	 * @param stId the st id
	 * @param request the request
	 * @return the all sales positions by name
	 */
	@RequestMapping(value="/salespos/SPsByName/{spName}/{al}/{bu}/{st}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody ServiceResponse<List<SalesPosition>> getAllSalesPositionsByName(@PathVariable("spName") String spName,
			@PathVariable("al") Long alId,
			@PathVariable("bu") Long buId,
			@PathVariable("st") Long stId,
			HttpServletRequest request) {

		ServiceStatus serviceStatus = null;
		ServiceResponse serviceResponse = new ServiceResponse();
		ServiceResult serviceResult = new ServiceResult();

		try {
			Alignment al = ModelAssembler.getAlignmentModel(alId,buId,stId);
			
			serviceResult.setList(salesPositionService.getAllSalesPositionsByName(spName, al,ModelAssembler.getDefaultUserDetails()));
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
	 * Gets the mirrored sales positions.
	 *
	 * @param spId the sp id
	 * @param alId the al id
	 * @param buId the bu id
	 * @param stId the st id
	 * @param request the request
	 * @return the mirrored sales positions
	 */
	@RequestMapping(value="/salespos/getMirroredSPs/{spId}/{alId}/{buId}/{stId}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody ServiceResponse<List<SalesPosition>> getMirroredSalesPositions(@PathVariable("spId") Long spId,
			@PathVariable("alId") Long alId,
			@PathVariable("buId") Long buId,
			@PathVariable("stId") Long stId,
			HttpServletRequest request) {

		ServiceStatus serviceStatus = null;
		ServiceResponse serviceResponse = new ServiceResponse();
		ServiceResult serviceResult = new ServiceResult();

		try {
			Alignment algmnt = ModelAssembler.getAlignmentModel(alId, buId, stId);
			SalesPosition salesPosition = ModelAssembler.getSalesPosition(spId, null);
			salesPosition.setAlignmment(algmnt);
			
			serviceResult.setList(salesPositionService.getMirroredSalesPositions(salesPosition, ModelAssembler.getDefaultUserDetails()));
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
	
	@RequestMapping(value="/salespos/getParent/{spId}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody ServiceResponse<SalesPosition> getParentSalesPosition(@PathVariable("spId") Long spId,
			HttpServletRequest request) {

		ServiceStatus serviceStatus = null;
		ServiceResponse<SalesPosition> serviceResponse = new ServiceResponse<SalesPosition>();
		ServiceResult<SalesPosition> serviceResult = new ServiceResult<SalesPosition>();

		try {
			UserDetails userDetails = ModelAssembler.getDefaultUserDetails();
			SalesPosition salesPosition = new SalesPosition();
			salesPosition.setId(spId);
			serviceResult.setDetail(salesPositionService.getParentSalesPosition(salesPosition, userDetails));
			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
		} catch (AlignmentServiceException e) {
			serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR, "500 ERROR");
		}
		serviceResponse.setStatus(serviceStatus);
		serviceResponse.setResult(serviceResult);
		LOGGER.info(" Returning REST response with Result.."+serviceResult.getDetail()+" and status "+serviceResponse.getStatus().getCode().getCode()+"-"+serviceResponse.getStatus().getMessage());
		return serviceResponse;
	}
	
	@RequestMapping(value="/salespos/getShapePolygon/{shId}/{spIds}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody ServiceResponse<GeoShapePolygon> getShapePolygonBySalesPosition(@PathVariable("shId") Long shId,
			@PathVariable("spIds") List<Long> spIds,HttpServletRequest request) {

		ServiceStatus serviceStatus = null;
		ServiceResponse<GeoShapePolygon> serviceResponse = new ServiceResponse<GeoShapePolygon>();
		ServiceResult<GeoShapePolygon> serviceResult = new ServiceResult<GeoShapePolygon>();

		try {
			UserDetails userDetails = ModelAssembler.getDefaultUserDetails();
			List<SalesPosition> salesPositions = new ArrayList<SalesPosition>();
			for(Long spId : spIds){
				SalesPosition salesPosition = new SalesPosition();
				salesPosition.setId(spId);
				SalesOrgHierarchy salesOrgHierarchy = new SalesOrgHierarchy();
				salesOrgHierarchy.setId(shId);
				salesPosition.setHierarchy(salesOrgHierarchy);
				salesPositions.add(salesPosition);
			}
			serviceResult.setList(salesPositionService.getShapePolygonBySalesPosition(salesPositions, userDetails));
			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
		} catch (SalesPositionServiceException e) {
			serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR, "500 ERROR");
		}
		serviceResponse.setStatus(serviceStatus);
		serviceResponse.setResult(serviceResult);
		LOGGER.info(" Returning REST response with Result.."+serviceResult.getList()+" and status "+serviceResponse.getStatus().getCode().getCode()+"-"+serviceResponse.getStatus().getMessage());
		return serviceResponse;
	}

	/**
	 * Gets the mirror sales position basic data.
	 *
	 * @param spId the sp id
	 * @param alId the al id
	 * @param buId the bu id
	 * @param stId the st id
	 * @param request the request
	 * @return the mirror sales position basic data
	 */
	@RequestMapping(value = "/salespos/getMirroredSPList/{spId}/{alId}/{buId}/{stId}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	ServiceResponse<List<SalesPosition>> getMirrorSalesPositionBasicData(
			@PathVariable("spId") Long spId, @PathVariable("alId") Long alId,
			@PathVariable("buId") Long buId, @PathVariable("stId") Long stId,
			HttpServletRequest request) {

		ServiceStatus serviceStatus = null;
		ServiceResponse serviceResponse = new ServiceResponse();
		ServiceResult serviceResult = new ServiceResult();

		try {
			Alignment algmnt = ModelAssembler.getAlignmentModel(alId, buId,
					stId);
			SalesPosition salesPosition = ModelAssembler.getSalesPosition(spId,
					null);
			salesPosition.setAlignmment(algmnt);

			serviceResult.setList(salesPositionService
					.getMirrorSalesPositionBasicData(salesPosition,
							ModelAssembler.getDefaultUserDetails()));
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
