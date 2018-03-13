package com.cognizant.opserv.sp.service.internal;

import java.util.List;

import com.cognizant.opserv.sp.exception.MetricServiceException;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.model.metric.RangeDetails;

/**
 * ****************************************************************************.
 *
 * @class MetricIntService contains all the metric internal services 
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 * ***************************************************************************
 */
public interface MetricIntService {
	
	/**
	 * Gets the range details for salespos metrics.
	 *
	 * @param mtrId the mtr id
	 * @param userDetails the user details
	 * @return the range details by metric
	 * @throws MetricServiceException the metric service exception
	 */
	List<RangeDetails> getRangeDetailsForSalesPosMetrics(long mtrId, UserDetails userDetails) throws MetricServiceException;
	
	/**
	 * Gets the range details for geo metrics.
	 *
	 * @param mtrId the mtr id
	 * @param userDetails the user details
	 * @return the range details for geo metrics
	 * @throws MetricServiceException the metric service exception
	 */
	public List<RangeDetails> getRangeDetailsForGeoMetrics(Long mtrId,UserDetails userDetails) throws MetricServiceException; 

}
