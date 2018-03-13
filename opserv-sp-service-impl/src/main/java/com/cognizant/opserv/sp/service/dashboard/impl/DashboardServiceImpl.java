package com.cognizant.opserv.sp.service.dashboard.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.opserv.sp.common.ChangeRequestStatusType;
import com.cognizant.opserv.sp.exception.AlignmentServiceException;
import com.cognizant.opserv.sp.exception.DashboardServiceException;
import com.cognizant.opserv.sp.exception.DashboardServiceException.DashboardServiceExceptionCode;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.persistence.dao.service.CustomerAlignmentDAOService;
import com.cognizant.opserv.sp.persistence.dao.service.DashboardDAOService;
import com.cognizant.opserv.sp.persistence.dao.service.GeographyAlignmentDAOService;
import com.cognizant.opserv.sp.persistence.dao.service.ProductAlignmentDAOService;
import com.cognizant.opserv.sp.service.dashboard.DashboardService;
import com.cognizant.peg.core.exception.OpservDataAccessException;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;


/**
 * ****************************************************************************.
 * 
 * @class DashboardServiceImpl contains all the Dashboard services
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 09/06/2016
 *        ************************************************************
 *        ***************
 */
@Service("dashboardService")
public class DashboardServiceImpl implements DashboardService {

	/** The Constant LOGGER. */
	private static final OpservLogger LOGGER = OpservLoggerFactory
			.getOpservLogger(DashboardServiceImpl.class);
	
	@Autowired
	private DashboardDAOService dashboardDAOService;

	@Autowired
	private  ProductAlignmentDAOService productAlignmentDAOService;
	
	@Autowired
	private GeographyAlignmentDAOService geographyAlignmentDAOService;
	
	@Autowired
	private CustomerAlignmentDAOService customerAlignmentDAOService;

	/**
	 * Gets the product count for sales positions.
	 *
	 * @param salesPositions the sales positions
	 * @param userDetails the user details
	 * @return the product count for sales positions
	 * @throws DashboardServiceException 
	 */
	@Override
	public Long getProductCountForSalesPositions(
			List<SalesPosition> salesPositions, UserDetails userDetails) throws DashboardServiceException {
		try{
		if (salesPositions.size() != 0 && !salesPositions.isEmpty() && null != userDetails
				&& null != userDetails.getTenantId()
				&& userDetails.getTenantId() != 0) {
			return productAlignmentDAOService.getProductCountForSalesPosition(salesPositions, userDetails);
		}
			else {
					LOGGER.info("*****************INPUT PARAMETER SALESPOSITION OR USER DETAILS IS NULL*****************");
					Object[] args = { "SalesPosition List is empty or null" };
					throw new DashboardServiceException(args);
				}
			}catch (OpservDataAccessException opEx) {
				LOGGER.error("*****************ERROR WHILE FETCHING PRODUCT COUNT FOR SALES POSITIONS*****************"+opEx.getMessage());
				Object[] args = { "Error while fetching product count for sales positions" };
				throw new DashboardServiceException(
						DashboardServiceExceptionCode.DSHBD_SER_EX_CD_002,
						"Error while fetching product count for sales positions", args,
						opEx);
			}
	}

	/**
	 * Gets the product names for sales positions.
	 *
	 * @param salesPositions the sales positions
	 * @param userDetails the user details
	 * @return the product names for sales positions
	 * @throws DashboardServiceException 
	 */
	@Override
	public List<String> getProductNamesForSalesPositions(
			List<SalesPosition> salesPositions, UserDetails userDetails) throws DashboardServiceException {
		try{
		if (salesPositions.size() != 0 && !salesPositions.isEmpty() && null != userDetails
				&& null != userDetails.getTenantId()
				&& userDetails.getTenantId() != 0) {
			return productAlignmentDAOService.getProductNamesForSalesPosition(salesPositions, userDetails);
		}
			else {
					LOGGER.info("*****************INPUT PARAMETER SALESPOSITION OR USER DETAILS IS NULL*****************");
					Object[] args = { "SalesPosition List is empty or null" };
					throw new DashboardServiceException(args);
				}
			}catch (OpservDataAccessException opEx) {
				LOGGER.error("*****************ERROR WHILE FETCHING PRODUCT NAMES FOR SALES POSITION*****************"+opEx.getMessage());
				Object[] args = { "Error while fetching Product names for Sales Position" };
				throw new DashboardServiceException(
						DashboardServiceExceptionCode.DSHBD_SER_EX_CD_003,
						"Error while fetching Product names for Sales Position", args,
						opEx);
			}
	}
	
	
	/**
	 * Gets the zip count.
	 *
	 * @param salesPositions the sales positions
	 * @param userDetails the user details
	 * @return the zip count
	 * @throws DashboardServiceException 
	 */
	@Override
	public Long getZipCountForSalesPositions(List<SalesPosition> salesPositions,
			UserDetails userDetails) throws DashboardServiceException {
		try{
		if (salesPositions.size() != 0 && !salesPositions.isEmpty() && null != userDetails
				&& null != userDetails.getTenantId()
				&& userDetails.getTenantId() != 0) {
			return geographyAlignmentDAOService.getZipCountForSpList(salesPositions,
					userDetails);		}
			else {
					LOGGER.info("*****************INPUT PARAMETER SALESPOSITION OR USER DETAILS IS NULL*****************");
					Object[] args = { "SalesPosition List is empty or null" };
					throw new DashboardServiceException(args);
				}
			}catch (OpservDataAccessException opEx) {
				LOGGER.error("*****************ERROR WHILE FETCHING ZIP COUNT FOR SALES POSITIONS*****************");
				Object[] args = { "Error while fetching zip count for sales positions"+opEx.getMessage() };
				throw new DashboardServiceException(
						DashboardServiceExceptionCode.DSHBD_SER_EX_CD_004,
						"Error while fetching zip count for sales positions", args,
						opEx);
			}
	}
	

	/**
	 * Gets the customer count for sales positions.
	 *
	 * @param salesPositions the sales positions
	 * @param userDetails the user details
	 * @return the customer count for sales positions
	 * @throws DashboardServiceException 
	 */
	@Override
	public Long getCustomerCountForSalesPositions(
			List<SalesPosition> salesPositions, UserDetails userDetails)
			throws DashboardServiceException {
		Long customerCount= 0L;
		
		try {
			if (null != salesPositions && salesPositions.size() > 0
					&& null != userDetails && null != userDetails.getTenantId()
					&& userDetails.getTenantId() != 0) {

				customerCount = customerAlignmentDAOService
						.getCustomerCountForSalesPositions(salesPositions,
								userDetails);
						

			} else {
				LOGGER.info("*****************INPUT PARAMETER SALESPOSITION OR USER DETAILS IS NULL*****************");
				Object[] args = { "SalesPosition List is null or userDetails is null" };
				throw new DashboardServiceException(args);
			}
		} catch (OpservDataAccessException opEx) {
			LOGGER.error("*****************ERROR WHILE FETCHING CUSTOMER COUNT FOR SALES POSITIONS*****************"+opEx.getMessage());
			Object[] args = new Object[1];
			args[0] = salesPositions;
			throw new DashboardServiceException(
					DashboardServiceExceptionCode.DSHBD_SER_EX_CD_005,
					"Error while fetching Customer Count for sales positions", args,
					opEx);
		}
		return customerCount;
	}

	/**
	 * Gets the geo algd customer count for sales positions.
	 *
	 * @param salesPositions the sales positions
	 * @param userDetails the user details
	 * @return the geo algd customer count for sales positions
	 */
	@Override
	public Long getGeoAlgdCustomerCountForSalesPositions(
			List<SalesPosition> salesPositions, UserDetails userDetails)
			throws DashboardServiceException {
		Long geoAlgdCustomerCount= 0L;
		
		try {
			if (null != salesPositions && salesPositions.size() > 0
					&& null != userDetails && null != userDetails.getTenantId()
					&& userDetails.getTenantId() != 0) {
				
				geoAlgdCustomerCount = customerAlignmentDAOService
						.getGeoAlgdCustomerCountForSalesPositions(
								salesPositions, userDetails);
			} else {
				LOGGER.info("*****************INPUT PARAMETER SALESPOSITION OR USER DETAILS IS NULL*****************");
				Object[] args = { "SalesPosition List is null or userDetails is null" };
				throw new DashboardServiceException(args);
			}
		} catch (OpservDataAccessException opEx) {
			LOGGER.error("*****************ERROR WHILE FETCHING GEO ALGD CUSTOMER COUNT FOR SALES POSITION*****************"+opEx.getMessage());
			Object[] args = new Object[1];
			args[0] = salesPositions;
			throw new DashboardServiceException(
					DashboardServiceExceptionCode.DSHBD_SER_EX_CD_006,
					"Error while fetching Geo Algd Customer Count for sales positions", args,
					opEx);
		}
		return geoAlgdCustomerCount;
	}

	/**
	 * Gets the non geo algd customer count for sales positions.
	 *
	 * @param salesPositions the sales positions
	 * @param userDetails the user details
	 * @return the non geo algd customer count for sales positions
	 */
	@Override
	public Long getNonGeoAlgdCustomerCountForSalesPositions(
			List<SalesPosition> salesPositions, UserDetails userDetails)
			throws DashboardServiceException {
		Long nonGeoAlgdCustomerCount= 0L;
		
		try {
			if (null != salesPositions && salesPositions.size() > 0
					&& null != userDetails && null != userDetails.getTenantId()
					&& userDetails.getTenantId() != 0) {
				
				nonGeoAlgdCustomerCount = customerAlignmentDAOService.getNonGeoAlgdCustomerCountForSalesPositions(salesPositions, userDetails);
			} else {
				LOGGER.info("*****************INPUT PARAMETER SALESPOSITION OR USER DETAILS IS NULL*****************");
				Object[] args = { "SalesPosition List is null or userDetails is null" };
				throw new DashboardServiceException(args);
			}
		} catch (OpservDataAccessException opEx) {
			LOGGER.error("*****************ERROR WHILE FETCHING NON GEO ALGD CUSTOMER COUNT FOR SALES POSITION*****************"+opEx.getMessage());
			Object[] args = new Object[1];
			args[0] = salesPositions;
			throw new DashboardServiceException(
					DashboardServiceExceptionCode.DSHBD_SER_EX_CD_007,
					"Error while fetching Non Geo Algd Customer Count for sales positions", args,
					opEx);
		}
		return nonGeoAlgdCustomerCount;
	}


	/* (non-Javadoc)
	 * @see com.cognizant.opserv.sp.service.dashboard.DashboardService#getCRCountBySalesPositionAndStatus(List, ChangeRequestStatusType, UserDetails)
	 */
	@Override
	public Integer getCRCountBySalesPositionAndStatus(
			List<SalesPosition> salesPositions,
			ChangeRequestStatusType crStatus, UserDetails userDetails)
			throws DashboardServiceException, AlignmentServiceException {
		
		List<Long> spIds = new ArrayList<Long>();
		try {
			if (salesPositions.size() != 0 && !salesPositions.isEmpty() && null != userDetails && null != userDetails.getTenantId() && userDetails.getTenantId() != 0) {
				for (SalesPosition salesPosition : salesPositions) {
					if (null != salesPosition && null != salesPosition.getId() && salesPosition.getId() != 0) {
						
						spIds.add(salesPosition.getId());
						
					} else {
						LOGGER.info("*****************INPUT PARAMETER SALESPOSITION IS NULL*****************");
						Object[] args = { "SalesPosition is null" };
						throw new DashboardServiceException(args);
					}
				}

			} else {
				LOGGER.info("*****************INPUT PARAMETER SALESPOSITION OR USER DETAILS IS NULL*****************");
				Object[] args = { "SalesPosition List is null or userDetails is null" };
				throw new DashboardServiceException(args);
			}
			return dashboardDAOService.getCRCountBySPAndStatus(spIds, crStatus, userDetails);
		} catch (OpservDataAccessException opEx) {
			LOGGER.error("*****************ERROR WHILE GETTING COUNT OF CRs BY SALES POSITION AND STATUS*****************"+opEx.getMessage());
			Object[] args = { "Error while count Change Request for sales positions" };
			throw new DashboardServiceException(args);
		}
	}

		/* (non-Javadoc)
	 * @see com.cognizant.opserv.sp.service.dashboard.DashboardService#getApproversCountBySalesPositionAndStatus(List, ChangeRequestStatusType, UserDetails)
	 */
	@Override
	public Integer getApproversCountBySalesPositionAndStatus(
			List<SalesPosition> salesPositions,
			ChangeRequestStatusType crStatus, UserDetails userDetails)
			throws DashboardServiceException, AlignmentServiceException {
		
		List<Long> spIds = new ArrayList<Long>();
		try {
			if (salesPositions.size() != 0 && !salesPositions.isEmpty() && null != userDetails && null != userDetails.getTenantId() && userDetails.getTenantId() != 0) {
				for (SalesPosition salesPosition : salesPositions) {
					if (null != salesPosition && null != salesPosition.getId() && salesPosition.getId() != 0) {
						
						spIds.add(salesPosition.getId());
						
					} else {
						LOGGER.info("*****************INPUT PARAMETER SALESPOSITION IS NULL*****************");
						Object[] args = { "SalesPosition is null" };
						throw new DashboardServiceException(args);
					}
				}

			} else {
				LOGGER.info("*****************INPUT PARAMETER SALESPOSITION OR USER DETAILS IS NULL*****************");
				Object[] args = { "SalesPosition List is null or userDetails is null" };
				throw new DashboardServiceException(args);
			}
			return dashboardDAOService.getApproversCountBySPAndStatus(spIds, crStatus, userDetails);
		} catch (OpservDataAccessException opEx) {
			LOGGER.error("*****************ERROR WHILE GETTING COUNT OF CRs BY SALES POSITION AND STATUS*****************"+opEx.getMessage());
			Object[] args = { "Error while count Change Request for sales positions" };
			throw new DashboardServiceException(args);
		}
	}

}
