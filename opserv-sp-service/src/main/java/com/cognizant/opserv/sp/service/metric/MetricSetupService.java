package com.cognizant.opserv.sp.service.metric;

import java.util.List;

import com.cognizant.opserv.sp.exception.MetricServiceException;
import com.cognizant.opserv.sp.model.Alignment;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.model.metric.Metric;
import com.cognizant.opserv.sp.model.metric.MetricConfig;
import com.cognizant.opserv.sp.model.metric.MetricExecutionTrigger;
import com.cognizant.peg.core.common.PaginationInfo;

/**
 * ****************************************************************************.
 *
 * @class MetricSetupService contains all the metrics services 
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 * ***************************************************************************
 */
public interface MetricSetupService {

	/**
	 * 
	 * @method createMetric
	 * @param metric
	 * @param userDetails
	 * @return createdMetric
	 * @throws MetricServiceException
	 */
    Metric createMetric(Metric metric,UserDetails userDetails) throws MetricServiceException;
    
    /**
	 * 
	 * @method updateMetric
	 * @param metric
	 * @param userDetails
	 * @return
	 * @throws MetricServiceException
	 */
	void updateMetric(Metric metric,UserDetails userDetails) throws MetricServiceException;
	
	/**
	 * 
	 * @method fetchMetricsByAlignment
	 * @param alignment
	 * @param paginInfo
	 * @param userDetails
	 * @return list of Metrics
	 * @throws MetricServiceException
	 */
	List<Metric> fetchMetricsByAlignment(Alignment alignment,PaginationInfo paginInfo,UserDetails userDetails) throws MetricServiceException;
	
	/**
	 * 
	 * @method configureMetric
	 * @param mConfig
	 * @param userDetails
	 * @return
	 * @throws MetricServiceException
	 */
	void configureMetric(MetricConfig mConfig,UserDetails userDetails) throws MetricServiceException;
	
	/**
	 * 
	 * @method configureMetricExecutionTrigger
	 * @param mTrigger
	 * @param userDetails
	 * @return
	 * @throws MetricServiceException
	 */
	void configureMetricExecutionTrigger(MetricExecutionTrigger mTrigger,UserDetails userDetails) throws MetricServiceException;
	
	/**
	 * 
	 * @method configureMetricExecutionTrigger
	 * @param mTrigger
	 * @param userDetails
	 * @return
	 * @throws MetricServiceException
	 */
	void configureMetricExecutionTrigger(List<MetricExecutionTrigger> mTrigger,UserDetails userDetails) throws MetricServiceException;
	
	
}

