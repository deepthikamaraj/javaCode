package com.cognizant.opserv.sp.persistence.dao.service;

import java.util.List;
import java.util.Map;

import com.cognizant.opserv.sp.common.MetricTriggerType;
import com.cognizant.opserv.sp.model.Alignment;
import com.cognizant.opserv.sp.model.PostalCode;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.model.metric.MetricExecutionTrigger;
import com.cognizant.opserv.sp.model.metric.MetricResult;
import com.cognizant.opserv.sp.model.metric.RangeDetails;
import com.cognizant.peg.core.exception.OpservDataAccessException;

/**
 * ****************************************************************************.
 *
 * @class MetricResultService contains all the DAO metric services 
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 * ***************************************************************************
 */
public interface MetricResultDAOService {
	
	/**
	 * Gets the all metric results.
	 * 
	 * @method getAllMetricResults
	 * @param salesPosition
	 *            the sales position
	 * @param userDetails
	 *            the user details
	 * @return the all metric results
	 * @throws OpservDataAccessException
	 *             the data access exception
	 */
	List<MetricResult> getAllMetricResults(SalesPosition salesPosition,
			UserDetails userDetails) throws OpservDataAccessException;

	/**
	 * Gets the all metric results.
	 * 
	 * @method getAllMetricResults
	 * @param salesPosition
	 *            the sales position
	 * @param types
	 *            the types
	 * @param userDetails
	 *            the user details
	 * @return the all metric results
	 * @throws OpservDataAccessException
	 *             the data access exception
	 */
	List<MetricResult> getAllMetricResults(SalesPosition salesPosition,
			List<MetricTriggerType> types, UserDetails userDetails)
			throws OpservDataAccessException;

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
	 * @throws OpservDataAccessException
	 *             the data access exception
	 */
	List<MetricResult> getAllMetricResults(SalesPosition salesPosition,
			MetricTriggerType type, UserDetails userDetails)
			throws OpservDataAccessException;
	
	/**
	 * @method getMetricResultsByPostalCodes - Gets the metric results by sales positions.
	 * @param mtrId the mtr id
	 * @param salesPositions the sales positions
	 * @param userDetails the user details
	 * @return the metric results by sales positions
	 * @throws OpservDataAccessException the opserv data access exception
	 */
	List<MetricResult> getMetricResultsBySalesPositions(Long mtrId, List<SalesPosition> salesPositions, 
			UserDetails userDetails) throws OpservDataAccessException;
	
	/**
	 * @method getMetricResultsByPostalCodes - Gets the metric results by postal codes.
	 * @param mtrId the mtr id
	 * @param postalCodes the postal codes
	 * @param metricValueType the metric value type
	 * @param userDetails the user details
	 * @return the metric results by postal codes
	 * @throws OpservDataAccessException the opserv data access exception
	 */
	Map<PostalCode, MetricResult> getMetricResultsByPostalCodes(Long mtrId, List<PostalCode> postalCodes, 
			Integer metricValueType, UserDetails userDetails) throws OpservDataAccessException;

	/**
	 * @method getAllMetricResultsByPostalCodes - Gets the metric results by postal codes.
	 * @param postalCodes the postal codes
	 * @param userDetails the user details
	 * @return Map<PostalCode, List<MetricResult>> the metric results by postal codes
	 * @throws OpservDataAccessException - the opserv data access exception
	 */
	Map<PostalCode, List<MetricResult>> getAllMetricResultsByPostalCodes(
			List<PostalCode> postalCodes, UserDetails userDetails)
			throws OpservDataAccessException;

	/**
	 * @method getMetricExecutionConfig - Gets the metric execution by alignment
	 * @param alignment the alignment
	 * @param userDetails the user details
	 * @return the metric execution trigger
	 * @throws OpservDataAccessException the metric service exception
	 */
	List<MetricExecutionTrigger> getMetricExecutionConfig(Alignment alignment, UserDetails userDetails) throws OpservDataAccessException;

	/**
	 * Gets the range details for salespos metrics.
	 * @method getRangeDetailsForSalesPosMetrics
	 * @param mtrId the mtr id
	 * @param userDetails the user details
	 * @return the range details by metric
	 * @throws OpservDataAccessException the opserv data access exception
	 */
	List<RangeDetails> getRangeDetailsForSalesPosMetrics(Long mtrId, UserDetails userDetails) throws OpservDataAccessException;
	
	/**
	 * Gets the range details for geo metrics.
	 * @method getRangeDetailsForGeoMetrics
	 * @param mtrId the mtr id
	 * @param userDetails the user details
	 * @return the range details for geo metrics
	 * @throws OpservDataAccessException the opserv data access exception
	 */
	List<RangeDetails> getRangeDetailsForGeoMetrics(Long mtrId, UserDetails userDetails) throws OpservDataAccessException;

	/**
	 * @method getAllMetricResultsByPostalCodes Gets the metric results by alignmentId and postal code
	 * @param postalCodes the postal codes
	 * @param alignmentId the alignment id
	 * @param userDetails the user details
	 * @return the metric results by postal codes
	 * @throws OpservDataAccessException the opserv data access exception
	 */
	Map<PostalCode, List<MetricResult>> getAllMetricResultsByPostalCodes(
			List<PostalCode> postalCodes, Long alignmentId,
			UserDetails userDetails) throws OpservDataAccessException;

}
