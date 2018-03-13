package com.cognizant.opserv.sp.service.metric.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.opserv.sp.common.MetricTriggerType;
import com.cognizant.opserv.sp.common.MetricValueType;
import com.cognizant.opserv.sp.exception.AlignmentServiceException;
import com.cognizant.opserv.sp.exception.MetricServiceException;
import com.cognizant.opserv.sp.exception.MetricServiceException.MetricServiceExceptionCode;
import com.cognizant.opserv.sp.model.Alignment;
import com.cognizant.opserv.sp.model.PostalCode;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.model.metric.MetricExecutionTrigger;
import com.cognizant.opserv.sp.model.metric.MetricResult;
import com.cognizant.opserv.sp.persistence.dao.service.MetricResultDAOService;
import com.cognizant.opserv.sp.service.metric.MetricResultService;
import com.cognizant.peg.core.exception.OpservDataAccessException;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;

/**
 * ****************************************************************************.
 *
 * @class MetricResultServiceImpl contains all the metric services 
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 * ***************************************************************************
 */
@Service("metricResultService")
public class MetricResultServiceImpl implements  MetricResultService {
	
	/** The Constant LOGGER. */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(MetricResultServiceImpl.class);

	/**
	 * MetricResultDAOService has the services to fetch metrics data.
	 */
	@Autowired
	private MetricResultDAOService metricResultDAOService;
	
	/**
	 * Gets the all metric results.
	 * @method getAllMetricResults
	 * @param salesPosition the sales position
	 * @param userDetails the user details
	 * @return the all metric results
	 * @throws MetricServiceException the metric service exception
	 * @throws AlignmentServiceException 
	 */
	@Override
	public List<MetricResult> getAllMetricResults(SalesPosition salesPosition,
			UserDetails userDetails) throws MetricServiceException, AlignmentServiceException {
		try {
			boolean value = checkInputs(salesPosition);
			if(value){
				return metricResultDAOService.getAllMetricResults(salesPosition,userDetails);
			}else{
				LOGGER.error("Missing Alignment Id / Buss unit id / Sales team id /Sales Pos Id/Sales Hier Id");
				throw new AlignmentServiceException(new Object[]{"Missing Alignment Id / Buss unit id / Sales team id /Sales Pos Id/Sales Hier Id"});
			}
		} catch(OpservDataAccessException e) {
			LOGGER.error("In getAllMetricResults - Error during fetching of metric results based on given SP ( Sales Pos Id: "+ salesPosition.getId() +").");
			Object[] args = new Object[]{salesPosition.getId(),salesPosition.getHierarchy().getId()};
			throw new MetricServiceException(MetricServiceException.MetricServiceExceptionCode.MTR_SER_EX_CD_001,
					"Exception in getAllMetricResults",args,e);
		}
	}


	/**
	 * Gets the all metric results.
	 *
	 * @method getAllMetricResults
	 * @param salesPosition the sales position
	 * @param types the types
	 * @param userDetails the user details
	 * @return the all metric results
	 * @throws MetricServiceException the metric service exception
	 * @throws AlignmentServiceException the metric service exception
	 */
	@Override
	public List<MetricResult> getAllMetricResults(SalesPosition salesPosition,
			List<MetricTriggerType> types, UserDetails userDetails)
			throws MetricServiceException, AlignmentServiceException {
		try {
			boolean value = checkInputs(salesPosition);
			if(value){
				return metricResultDAOService.getAllMetricResults(salesPosition, types,userDetails);
			}else{
				LOGGER.error("Missing Alignment Id / Buss unit id / Sales team id /Sales Pos Id/Sales Hier Id");
				throw new AlignmentServiceException(new Object[]{"Missing Alignment Id / Buss unit id / Sales team id /Sales Pos Id/Sales Hier Id"});
			}
		} catch(OpservDataAccessException e) {
			LOGGER.error("In getAllMetricResults - Error during fetching of metric results based on given SP and trigger types ( Sales Pos Id: "+ salesPosition.getId() +").");
			Object[] args = new Object[]{salesPosition.getId(),salesPosition.getHierarchy().getId(),types};
			throw new MetricServiceException(MetricServiceException.MetricServiceExceptionCode.MTR_SER_EX_CD_004,
					"Exception in getAllMetricResults",args,e);
		}
	}

	
	/**
	 * Gets the all metric results.
	 * 
	 * @method getAllMetricResults
	 * @param salesPosition
	 *            the sales position
	 * @param type
	 *            the type
	 * @param userDetails
	 *            the user details
	 * @return the all metric results
	 * @throws MetricServiceException
	 *             the metric service exception
	 * @throws AlignmentServiceException 
	 */
	public List<MetricResult> getAllMetricResults(SalesPosition salesPosition,
			MetricTriggerType type, UserDetails userDetails)
			throws MetricServiceException, AlignmentServiceException {
		try {
			boolean value = checkInputs(salesPosition);
			if(value){
				return metricResultDAOService.getAllMetricResults(salesPosition,type, userDetails);
			}else{
				LOGGER.error("Missing Alignment Id / Buss unit id / Sales team id /Sales Pos Id/Sales Hier Id");
				throw new AlignmentServiceException(new Object[]{"Missing Alignment Id / Buss unit id / Sales team id /Sales Pos Id/Sales Hier Id"});
			}
		} catch(OpservDataAccessException e) {
			LOGGER.error("In getAllMetricResults - Error during fetching of metric results based on given SP and trigger type ( Sales Pos Id: "+ salesPosition.getId() +").");
			Object[] args = new Object[]{salesPosition.getId(),salesPosition.getHierarchy().getId(),type};
			throw new MetricServiceException(MetricServiceException.MetricServiceExceptionCode.MTR_SER_EX_CD_007,
					"Exception in getAllLastMetricResults",args,e);
		}
	}

	/**
	 * @method checkInputs - to check input data
	 * @param salesPosition - holds sales position data
	 * @throws AlignmentServiceException - if we have violation or any issue.
	 */
	private boolean checkInputs(SalesPosition salesPosition) throws AlignmentServiceException{
		boolean value = true;
		if(salesPosition==null){
			value = false;
		}else{
			
			if(salesPosition.getAlignmment() == null){
				value = false;
			}else{
				Long alignmentId = salesPosition.getAlignmment().getId();
				Long salesPosId = salesPosition.getId();

				if(alignmentId==null){
					value = false;
				}else if(salesPosId==null){
					value = false;
				}
				
				if(salesPosition.getAlignmment().getSalesTeam() == null ){
					value = false;
				}else{
					Long salesTeamId = salesPosition.getAlignmment().getSalesTeam().getId();
					if(salesTeamId==null){
						value = false;
					}
					
					if(salesPosition.getAlignmment().getSalesTeam().getBusinessUnit() == null){
						value = false;
					}else{
						Long bussUnitId = salesPosition.getAlignmment().getSalesTeam().getBusinessUnit().getId();
						if(bussUnitId==null){
							value = false;
						}
					}
				}
			}
			
			if(salesPosition.getHierarchy() == null){
				value = false;
			}else{
				Long salesHierId = salesPosition.getHierarchy().getId();
				if(salesHierId==null){
					value = false;
				}
			}
		}
		return value;
	}

	/**
	 * Gets the metric results by sales positions.
	 *
	 * @param mtrId the mtr id
	 * @param salesPositions the sales positions
	 * @param userDetails the user details
	 * @return the metric results by sales positions
	 * @throws MetricServiceException the metric service exception
	 */
	@Override
	@Transactional
	public List<MetricResult> getMetricResultsBySalesPositions(long mtrId, List<SalesPosition> salesPositions, 
			UserDetails userDetails) throws MetricServiceException {
		try{
			List<MetricResult> metricResults = null;
			if(null == salesPositions || salesPositions.isEmpty()){
				LOGGER.error("Missing SalesPositions");
				throw new MetricServiceException(new Object[]{"Missing SalesPositions"});
			}
			else{
				metricResults = metricResultDAOService.getMetricResultsBySalesPositions(mtrId, salesPositions, userDetails);
			}
			return metricResults;
		}
		catch(OpservDataAccessException e){
			LOGGER.error("In getMetricResultsBySalesPositions - Error during fetching of metric results based on given SP and metric id ( Sales Positions: "+ salesPositions +").");
			throw new MetricServiceException(MetricServiceExceptionCode.MTR_SER_EX_CD_005, "exception while fetching metric results", null, e);
		}
	}

	/**
	 * Gets the metric results by postal codes.
	 *
	 * @param mtrId the mtr id
	 * @param postalCodes the postal codes
	 * @param userDetails the user details
	 * @return the metric results by postal codes
	 * @throws MetricServiceException the metric service exception
	 */
	@Override
	public Map<PostalCode, MetricResult> getMetricResultsByPostalCodes(long mtrId,
			List<PostalCode> postalCodes, UserDetails userDetails)
			throws MetricServiceException {
		try{
			Map<PostalCode, MetricResult> metricResults = null;
			if(null == postalCodes || postalCodes.isEmpty()){
				LOGGER.error("Missing SalesPosition / postalCodes ");
				throw new MetricServiceException(new Object[]{"Missing SalesPosition / postalCodes "});
			}
			else{
				metricResults = metricResultDAOService.getMetricResultsByPostalCodes(mtrId, postalCodes, 
						MetricValueType.CURRENT.getId(), userDetails);
			}
			return metricResults;
		}
		catch(OpservDataAccessException e){
			LOGGER.error("In getMetricResultsByPostalCodes - Error while fetching metric results based on postal codes ( Postal Codes: "+ postalCodes +").");
			throw new MetricServiceException(MetricServiceExceptionCode.MTR_SER_EX_CD_0016, "exception while fetching metric results", null, e);
		}
	}
	
	/**
	 * Gets the metric results by postal codes.
	 * @param postalCodes the postal codes
	 * @param userDetails the user details
	 * @return the metric results by postal codes
	 * @throws MetricServiceException the metric service exception
	 */
	@Override
	public Map<PostalCode, List<MetricResult>> getAllMetricResultsByPostalCodes(
			List<PostalCode> postalCodes, UserDetails userDetails)
			throws MetricServiceException {
		try{
			if(null == postalCodes || postalCodes.isEmpty()){
				LOGGER.error(" Missing Postal Codes ");
				throw new MetricServiceException(new Object[]{"Missing Postal Codes"});
			}else{
				return metricResultDAOService.getAllMetricResultsByPostalCodes(postalCodes, userDetails);
			}
		}
		catch(OpservDataAccessException e){
			LOGGER.error("In getAllMetricResultsByPostalCodes - Error while fetching list of metric results based on postal codes.( Postal Codes: "+ postalCodes +").");
			throw new MetricServiceException(MetricServiceExceptionCode.MTR_SER_EX_CD_0017, "exception while fetching list of metric results", null, e);
		}
	}


	/**
	 * Gets the metric execution by alignment
	 * @param alignment the alignment
	 * @param userDetails the user details
	 * @return the metric execution trigger
	 * @throws MetricServiceException the metric service exception
	 */
	@Override
	public List<MetricExecutionTrigger> getMetricExecutionConfig(
			Alignment alignment, UserDetails userDetails) throws MetricServiceException {
		try{
			if(null == alignment || alignment.getId() == null){
				LOGGER.error("Missing Alignment Id");
				throw new MetricServiceException(new Object[]{"Missing Alignment Id"});
			}
			else{
				return metricResultDAOService.getMetricExecutionConfig(alignment,userDetails);
			}
		}
		catch(OpservDataAccessException e){
			LOGGER.error("In getMetricExecutionConfig - Error while fetching list of metric execution data for given alignment( Alignment: "+ alignment.getId() +").");
			throw new MetricServiceException(MetricServiceExceptionCode.MTR_SER_EX_CD_0018, "exception fetching list of metric triggers for given alignment", null, e);
		}
	
	}
	
	/**
	 * @method getAllMetricResultsByPostalCodes Gets the metric results by alignmentId and postal code
	 * @param postalCodes the postal codes
	 * @param alignmentId the alignment id
	 * @param userDetails the user details
	 * @return the metric results by postal codes
	 * @throws MetricServiceException the metric service exception
	 */
	@Override
	public Map<PostalCode, List<MetricResult>> getAllMetricResultsByPostalCodes(
			List<PostalCode> postalCodes,Long alignmentId, UserDetails userDetails)
			throws MetricServiceException {
		try{
			if(null == postalCodes || postalCodes.isEmpty() || alignmentId == null){
				LOGGER.error("Missing postal codes/ Alignment Id");
				throw new MetricServiceException(new Object[]{"Missing postal codes/Alignment Id"});
			}
			else{
				return metricResultDAOService.getAllMetricResultsByPostalCodes(postalCodes, alignmentId,userDetails);
			}
		}
		catch(OpservDataAccessException e){
			LOGGER.error("In getMetricExecutionConfig - Error while fetching list of metric triggers for given alignment and postal codes( Alignment: "+ alignmentId +" postalCodes ="+postalCodes+" ).");
			throw new MetricServiceException(MetricServiceExceptionCode.MTR_SER_EX_CD_0019, "exception while fetching list of metric results", null, e);
		}
	}
	
}
