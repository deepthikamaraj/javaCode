package com.cognizant.opserv.sp.service.alignment.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.opserv.sp.common.CommonConstants;
import com.cognizant.opserv.sp.exception.AlignmentServiceException;
import com.cognizant.opserv.sp.exception.AlignmentServiceException.AlignmentServiceExceptionCode;
import com.cognizant.opserv.sp.exception.ChangeRequestServiceException;
import com.cognizant.opserv.sp.exception.SalesPositionServiceException;
import com.cognizant.opserv.sp.exception.SalesPositionServiceException.SalesPositionServiceExceptionCode;
import com.cognizant.opserv.sp.model.Alignment;
import com.cognizant.opserv.sp.model.GeoShapePolygon;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.persistence.dao.service.SalesPositionDAOService;
import com.cognizant.opserv.sp.service.alignment.GeographyAlignmentService;
import com.cognizant.opserv.sp.service.alignment.SalesPositionService;
import com.cognizant.opserv.sp.service.changereq.ChangeRequestService;
import com.cognizant.peg.core.exception.OpservDataAccessException;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
/**
 * ****************************************************************************.
 *
 * @class SalesPositionServiceImpl contains all the services for sales position  
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 * ***************************************************************************
 */
@Service("salesPositionService")
public class SalesPositionServiceImpl implements SalesPositionService {

	
	/**
	 * LOGGER
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(SalesPositionServiceImpl.class);
	
	/*
	 * SalesPositionDAOService has all methods to call DAO and to map the entity
	 */
	/** The sales position dao service. */
	@Autowired
	private SalesPositionDAOService salesPositionDAOService;	
	
	/** The geography alignment service. */
	@Autowired
	private GeographyAlignmentService geographyAlignmentService;
	
	
	
	/** The change request service. */
	@Autowired
	private ChangeRequestService changeRequestService;
	
	/**
	 * 
	 * @method getAllSalesPositionsByAlignment
	 * @param alignment
	 * @param userDetails
	 * @return
	 * @throws AlignmentServiceException
	 */
	@Override
	@Transactional
	@Cacheable(value=CommonConstants.SALESPOSITION_SERVICE_CACHE,key="{#alignment.id,#alignment.salesTeam.businessUnit.id,#alignment.salesTeam.id,#userDetails.tenantId}")
	public List<SalesPosition> getAllSalesPositionsByAlignment(
			Alignment alignment, UserDetails userDetails)
			throws AlignmentServiceException {
		
		try {
			if(null == alignment || null == alignment.getSalesTeam() || null == alignment.getSalesTeam().getBusinessUnit() || null == userDetails ||
			  null == alignment.getId() || null == alignment.getSalesTeam().getBusinessUnit().getId() || 
			  null == alignment.getSalesTeam().getId() || null == userDetails.getTenantId()){
				String params = "Missing Alignment Id / Buss unit id / Sales team id / tenant id";
				Object[] args = new Object[]{params};
				LOGGER.error("Missing Alignment Id / Buss unit id / Sales team id / tenant id");
				throw new  AlignmentServiceException(args);
			}
			return salesPositionDAOService.fetchtAllSalesPositionsByAlignment(alignment, userDetails);
			} catch(OpservDataAccessException e) {
				Object[] args = new Object[]{alignment.getId()};
				LOGGER.error("Error during fetching SalesPosition by Alignment ( Alignment Id: "+ alignment.getId() +").");
			    throw new AlignmentServiceException(AlignmentServiceExceptionCode.ALGN_SER_EX_CD_001 ,"Fetch_exception",args,e);
			}
		
	}
	/**
	 * 
	 * @method getAllChildSalesPositions
	 * @param salesPosId
	 * @param userDetails
	 * @return
	 * @throws AlignmentServiceException
	 */
	@Override
	@Transactional
	public List<SalesPosition> getAllChildSalesPositions(long salesPosId,
			UserDetails userDetails) throws AlignmentServiceException {
		
		try {
			Long spId = null;
			try {
				spId = Long.valueOf(salesPosId);
			} catch(Exception e) {
				spId = null;
			}
			if( null == spId || null == userDetails || null == userDetails.getTenantId()){
				String params = "Missing Sales pos id / tenant id";
				LOGGER.error("Missing Sales pos id / tenant id");
				Object[] args = new Object[]{params};
				throw new  AlignmentServiceException(args);
			}
			
			return salesPositionDAOService.getImdChildSalesPostion(salesPosId, userDetails);
			} catch(OpservDataAccessException e) {
			       Object[] args = new Object[]{salesPosId};
			       LOGGER.error("Error during fetching immediate child SalesPosition (Sales pos id:" + salesPosId +").");
			       throw new AlignmentServiceException(AlignmentServiceExceptionCode.ALGN_SER_EX_CD_002 ,"Fetch_exception",args,e);
			}
	}
	/**
	 * 
	 * @method getSalesPositionInformation
	 * @param salesPosId
	 * @param userDetail
	 * @return
	 * @throws AlignmentServiceException
	 */
	@Override
	@Transactional
	public SalesPosition getSalesPositionInformation(long salesPosId,
			UserDetails userDetail) throws AlignmentServiceException {
			try {
				Long spId = null;
				try {
					spId = Long.valueOf(salesPosId);
				} catch(Exception e) {
					spId = null;
				}
				if( null == spId || null == userDetail || null == userDetail.getTenantId()){
					String params = "Missing Sales pos id / tenant id";
					LOGGER.error("Missing Sales pos id / tenant id");
					Object[] args = new Object[]{params};
					throw new  AlignmentServiceException(args);
				}
				return salesPositionDAOService.getSalesPosInformations(salesPosId, userDetail); 
				} catch(OpservDataAccessException e) {
					Object[] args = new Object[]{ salesPosId};
					LOGGER.error("Error occur during fetching SP informations ( Sales pos id: "+salesPosId+" ).");
				    throw new AlignmentServiceException(AlignmentServiceExceptionCode.ALGN_SER_EX_CD_003 ,"Fetch_exception",args,e);
				}
	}
	/**
	 * 
	 * @method getAllSalesPositionsByName
	 * @param name
	 * @param alignment
	 * @param userDetails
	 * @return
	 * @throws AlignmentServiceException
	 */
	@Override
	@Transactional
	public List<SalesPosition> getAllSalesPositionsByName(String name,
			Alignment alignment, UserDetails userDetails)
			throws AlignmentServiceException {
			try {
				if(null == name || name.isEmpty() || null == alignment || null == alignment.getSalesTeam() || null == alignment.getSalesTeam().getBusinessUnit() || null == userDetails ||
						  null == alignment.getId() || null == alignment.getSalesTeam().getBusinessUnit().getId() || 
						  null == alignment.getSalesTeam().getId() || null == userDetails.getTenantId()){
				
					String params = "Missing Sales Pos Name / Alignment Id / Buss unit id / Sales team id / tenant id";
					Object[] args = new Object[]{params};
					LOGGER.error("Missing Sales Pos Name / Alignment Id / Buss unit id / Sales team id / tenant id");
					throw new  AlignmentServiceException(args);
				}
			return salesPositionDAOService.fetchAllSalesPostionsByName(name,alignment, userDetails);
		
			} catch(OpservDataAccessException e) {
				Object[] args=  new Object[]{name};
				LOGGER.error("Error during fetching SalesPositions by PositionName (Sales Pos Name: "+ name +").");
			    throw new AlignmentServiceException(AlignmentServiceExceptionCode.ALGN_SER_EX_CD_004 ,"Fetch_exception",args,e);

			}
	}

	
	
	/**
	 * Gets the mirrored sales positions.
	 *
	 * @param salesPosition the sales position
	 * @param userDetails the user details
	 * @return the mirrored sales positions
	 * @throws AlignmentServiceException the alignment service exception
	 */
	@Override
	@Transactional
	public List<SalesPosition> getMirroredSalesPositions(
			SalesPosition salesPosition, UserDetails userDetails)
			throws AlignmentServiceException {
		try{
			if( null == salesPosition || null == salesPosition.getId() || null == userDetails || null == userDetails.getTenantId() ){
				String params = "Sales Pos id or tenant id is missing";
				Object[] args = new Object[]{params};
				LOGGER.error( "Sales Pos id or tenant id is missing");
				throw new  AlignmentServiceException(args);
			}
			return salesPositionDAOService.getMirroredSalesPositionsInfo(salesPosition, userDetails);
		}catch(OpservDataAccessException dataAccessException){
			Object[] args = new Object[]{salesPosition.getId()};
			LOGGER.error("Error during getting mirrored salesPostion information (Sales Pos id: "+ salesPosition.getId() +").");
			
			throw new AlignmentServiceException(AlignmentServiceExceptionCode.ALGN_SER_EX_CD_005, "Fetch_exception", args, dataAccessException);
		}
		
		
	}
	
	/**
	 * Gets the parent sales position.
	 *
	 * @param salesPosition the sales position
	 * @param userDetails the user details
	 * @return the parent sales position
	 * @throws AlignmentServiceException the alignment service exception
	 */
	@Override
	@Transactional
	public SalesPosition getParentSalesPosition(SalesPosition salesPosition,
			UserDetails userDetails) throws AlignmentServiceException {
		try{
			if(null == salesPosition){
				LOGGER.error("SalesPosition is null.");
				throw new AlignmentServiceException(new Object[]{"salesPosition"});
			}
			SalesPosition salesPos = salesPositionDAOService.getParentSalesPosition(salesPosition.getId(), userDetails);
			return salesPos;
		}
		catch(OpservDataAccessException e){
			LOGGER.error("Error occured during fetching SP informations");
			throw new AlignmentServiceException(AlignmentServiceExceptionCode.ALGN_SER_EX_CD_003, "exception while fetching parent sales position", null, e);
		}
	}
	
	/**
	 * Gets the shape polygon by sales position.
	 *
	 * @param salesPositions the sales positions
	 * @param userDetails the user details
	 * @return the shape polygon by sales position
	 * @throws SalesPositionServiceException the sales position service exception
	 */
	@Override
	public List<GeoShapePolygon> getShapePolygonBySalesPosition(List<SalesPosition> salesPositions, 
			UserDetails userDetails) throws SalesPositionServiceException {
		try{
			if(null == salesPositions || salesPositions.isEmpty() || null == salesPositions.get(0).getHierarchy()){
				LOGGER.error("SalesPosition is null.");
				throw new SalesPositionServiceException(new Object[]{"salesPositions"});
			}
			return salesPositionDAOService.getShapePolygonBySalesPosition(salesPositions, userDetails);
		}
		catch(OpservDataAccessException e){
			LOGGER.error("Error occur while fetching shape polygon");
			throw new SalesPositionServiceException(SalesPositionServiceExceptionCode.SP_SER_EX_CD_001, "exception while fetching shape polygon details", null, e);
		}
	}

	/* (non-Javadoc)
	 * @see com.cognizant.opserv.sp.service.alignment.SalesPositionService#isSalesPositionTransient(Long, UserDetails)
	 */
	@Override
	@Transactional
	public boolean isSalesPositionTransient(Long spId, UserDetails userDetails)
			throws AlignmentServiceException, ChangeRequestServiceException {
		if (null != spId && null != userDetails && null != userDetails.getTenantId()) {
		try {
			return changeRequestService.changeRequestsMarkAsDirty(spId, userDetails);
		} catch (OpservDataAccessException exception) {
			Object[] args = new Object[1];
			LOGGER.error(" Error occured while making CR as dirty for spId("+spId+")");
			args[0] = " Exception occured while making CR as dirty for spId " + spId;
			throw new ChangeRequestServiceException(args);
		}
	} else {
		Object[] args = new Object[1];
		args[0] = "Either salesPositionId or tenantId is null";
		LOGGER.error("Either salesPositionId or tenantId is null");
		throw new AlignmentServiceException(args);
	}
	}
	
	/**
	 * Gets the mirrored sales position.
	 *
	 * @param salesPosition the sales position
	 * @param userDetails the user details
	 * @return the mirrored sales position
	 * @throws AlignmentServiceException the alignment service exception
	 */
	@Override
	@Transactional
	public List<SalesPosition> getMirrorSalesPositionBasicData(
			SalesPosition salesPosition, UserDetails userDetails)
			throws AlignmentServiceException {
		try {
			if (null == salesPosition || null == salesPosition.getId()
					|| null == userDetails || null == userDetails.getTenantId()) {
				String params = "Sales Pos id or tenant id is missing";
				Object[] args = new Object[] { params };
				LOGGER.error("Sales Pos id or tenant id is missing");
				throw new AlignmentServiceException(args);
			}
			return salesPositionDAOService.getMirrorSalesPositionBasicData(
					salesPosition, userDetails);
		} catch (OpservDataAccessException dataAccessException) {
			Object[] args = new Object[] { salesPosition.getId() };
			LOGGER.error("Error during getting mirrored salesPostion information (Sales Pos id: "
					+ salesPosition.getId() + ").");

			throw new AlignmentServiceException(
					AlignmentServiceExceptionCode.ALGN_SER_EX_CD_005,
					"Fetch_exception", args, dataAccessException);
		}

	}
	
}
