package com.cognizant.opserv.sp.cr.process.internal.service;

import com.cognizant.opserv.sp.common.MetricTriggerType;
import com.cognizant.opserv.sp.exception.AlignmentServiceException;
import com.cognizant.opserv.sp.exception.MetricExecutionException;
import com.cognizant.opserv.sp.exception.MetricViolationException;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.model.auth.UserDetails;


/**
 * ****************************************************************************.
 *
 * @class MetricOfflineService contains the metric offline services 
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 29/09/2016
 * ***************************************************************************
 */
public interface MetricOfflineService {

	/**
	 * Process the metrics
	 * @method processCalculativeMetrics
	 * @param salespos the sales position
	 * @param mtrTrgType the metric trigger type
	 * @param userDetails the user details the user details
	 * @return void
	 * @throws MetricExecutionException the metric execution exception
	 */
	void processCalculativeMetrics(SalesPosition salespos, MetricTriggerType mtrTrgType, UserDetails userDetails) throws MetricExecutionException;
    
	/**
	 * Check if Metrics are violated for given sales positions
	 * @method checkCustomerPushMetricViolation
     * @param souceSalesPos the source sales position the source sales position
     * @param targetSalesPos the target sales position the target sales position
     * @param userDetails the user details
     * @return boolean true if metrics are not violated
     * @throws AlignmentServiceException the alignment service exception
     * @throws MetricViolationException the metric violation exception
     */
    boolean checkCustomerPushMetricViolation(SalesPosition souceSalesPos,SalesPosition targetSalesPos,UserDetails userDetails) throws AlignmentServiceException, MetricViolationException;
    
    /**
	 * Check if Metrics are violated for given sales positions
	 * @method checkCustomerPullMetricViolation
     * @param souceSalesPos the source sales position
     * @param targetSalesPos the target sales position
     * @param userDetails the user details
     * @return boolean true if metrics are not violated
     * @throws AlignmentServiceException the alignment service exception
     * @throws MetricViolationException the metric violation exception
     */
    boolean checkCustomerPullMetricViolation(SalesPosition souceSalesPos,SalesPosition targetSalesPos,UserDetails userDetails) throws MetricViolationException, AlignmentServiceException;
    
    /**
	 * Check if Metrics are violated for given sales position
	 * @method checkCustomerEditMetricViolation
     * @param targetSalesPos the target sales position
     * @param userDetails the user details
     * @return boolean true if metrics are not violated
     * @throws AlignmentServiceException the alignment service exception
     * @throws MetricViolationException the metric violation exception
     */
    boolean checkCustomerEditMetricViolation(SalesPosition targetSalesPos,UserDetails userDetails) throws MetricViolationException, AlignmentServiceException;
    
    /**
   	 * Check if Metrics are violated for given sales positions
   	 * @method checkZipMovementMetricViolation
     * @param souceSalesPos the source sales position
     * @param targetSalesPos the target sales position
     * @param userDetails the user details
     * @return boolean true if metrics are not violated
     * @throws AlignmentServiceException the alignment service exception
     * @throws MetricViolationException the metric violation exception
     */
    boolean checkZipMovementMetricViolation(SalesPosition souceSalesPos,SalesPosition targetSalesPos,UserDetails userDetails) throws MetricViolationException, AlignmentServiceException; 

}
