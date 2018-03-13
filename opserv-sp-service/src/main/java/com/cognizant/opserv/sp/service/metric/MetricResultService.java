package com.cognizant.opserv.sp.service.metric;

import java.util.List;
import java.util.Map;

import com.cognizant.opserv.sp.common.MetricTriggerType;
import com.cognizant.opserv.sp.exception.AlignmentServiceException;
import com.cognizant.opserv.sp.exception.MetricServiceException;
import com.cognizant.opserv.sp.model.Alignment;
import com.cognizant.opserv.sp.model.PostalCode;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.model.metric.MetricExecutionTrigger;
import com.cognizant.opserv.sp.model.metric.MetricResult;
/**
 * ****************************************************************************.
 *
 * @class MetricResultService contains all the metric services 
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 * ***************************************************************************
 */
public interface MetricResultService {

	/**
	 * @method getAllMetricResults Gets the all metric results.
	 * @method getAllMetricResults
	 * @param salesPosition the sales position
	 * @param userDetails the user details
	 * @return List<MetricResult> the all metric results
	 * @throws MetricServiceException the metric service exception
	 * @throws AlignmentServiceException 
	 */
	List<MetricResult> getAllMetricResults(SalesPosition salesPosition,UserDetails userDetails) throws MetricServiceException, AlignmentServiceException;
	
	/**
	 * @method getAllMetricResults Gets the all metric results.
	 * @param salesPosition the sales position
	 * @param types the types
	 * @param userDetails the user details
	 * @return List<MetricResult> the all metric results
	 * @throws MetricServiceException the metric service exception
	 * @throws AlignmentServiceException 
	 */
	List<MetricResult> getAllMetricResults(SalesPosition salesPosition,List<MetricTriggerType> types,UserDetails userDetails) throws MetricServiceException, AlignmentServiceException;
	
	/**
	 * Gets the all metric results.
	 *
	 * @method getAllMetricResults
	 * @param salesPosition the sales position
	 * @param type the type
	 * @param userDetails the user details
	 * @return the all metric results
	 * @throws MetricServiceException the metric service exception
	 * @throws AlignmentServiceException the metric service exception
	 */
	List<MetricResult> getAllMetricResults(SalesPosition salesPosition,MetricTriggerType type,UserDetails userDetails) throws MetricServiceException, AlignmentServiceException;
	
	/**
	 * @method getMetricResultsBySalesPositions Gets the metric results by sales positions.
	 * @param mtrId the mtr id
	 * @param salesPositions the sales positions
	 * @param userDetails the user details
	 * @return the metric results by sales positions
	 * @throws MetricServiceException the metric service exception
	 */
	List<MetricResult> getMetricResultsBySalesPositions(long mtrId, List<SalesPosition> salesPositions, UserDetails userDetails) throws MetricServiceException;
	
	/**
	 * @method getMetricResultsByPostalCodes Gets the metric results by postal codes.
	 * @param mtrId the mtr id
	 * @param postalCodes the postal codes
	 * @param userDetails the user details
	 * @return the metric results by postal codes
	 * @throws MetricServiceException the metric service exception
	 */
	Map<PostalCode,MetricResult> getMetricResultsByPostalCodes(long mtrId, List<PostalCode> postalCodes, UserDetails userDetails) throws MetricServiceException;

	/**
	 * @method getAllMetricResultsByPostalCodes Gets the metric results by postal codes.
	 * @param postalCodes the postal codes
	 * @param userDetails the user details
	 * @return the metric results by postal codes
	 * @throws MetricServiceException the metric service exception
	 */
	Map<PostalCode, List<MetricResult>> getAllMetricResultsByPostalCodes(
			List<PostalCode> postalCodes, UserDetails userDetails)
			throws MetricServiceException;
	
	/**
	 * @method getMetricExecutionConfig Gets the metric execution by alignment
	 * @param alignment the alignment
	 * @param userDetails the user details
	 * @return the metric execution trigger
	 * @throws MetricServiceException the metric service exception
	 */
	List<MetricExecutionTrigger> getMetricExecutionConfig(Alignment alignment, UserDetails userDetails) throws MetricServiceException;

	/**
	 * @method getAllMetricResultsByPostalCodes Gets the metric results by alignmentId and postal code
	 * @param postalCodes the postal codes
	 * @param alignmentId the alignment id
	 * @param userDetails the user details
	 * @return the metric results by postal codes
	 * @throws MetricServiceException the metric service exception
	 */
	Map<PostalCode, List<MetricResult>> getAllMetricResultsByPostalCodes(
			List<PostalCode> postalCodes, Long alignmentId,
			UserDetails userDetails) throws MetricServiceException;
	
}
