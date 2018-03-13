package com.cognizant.opserv.sp.persistence.dao.service;

import java.util.List;

import com.cognizant.opserv.sp.model.Alignment;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.model.metric.Metric;
import com.cognizant.opserv.sp.model.metric.MetricConfig;
import com.cognizant.opserv.sp.model.metric.MetricExecutionTrigger;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.exception.OpservDataAccessException;

/**
 * ****************************************************************************.
 *
 * @class MetricSetupDAOService contains all the DAO metrics services 
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 * ***************************************************************************
 */
public interface MetricSetupDAOService {
	
	
	 /**
 	 * Gets the created metric informations.
 	 *
 	 * @param metric
 	 * @param tenantId the tenant id
 	 * @return the created metric informations
 	 * @throws MetricServiceException the metric service exception
 	 */
	Metric createMetric(Metric metric, UserDetails userDetails) throws OpservDataAccessException;
	
	
	 /**
 	 *  updated metric informations.
 	 *
 	 * @param metric
 	 * @param tenantId the tenant id
 	 * @return 
 	 * @throws MetricServiceException the metric service exception
 	 */
	void updateMetric(Metric metric, UserDetails userDetails) throws OpservDataAccessException;
	
	 /**
 	 * fetches the list of metrics  informations by alignment.
 	 *
 	 * @param alignment the alignment
 	 * @param tenantId the tenant id
 	 * @return the list of metric informations
 	 * @throws MetricServiceException the metric service exception
 	 */
	List<Metric> fetchMetricsByAlignment(Alignment alignment,
			PaginationInfo paginInfo, UserDetails userDetails) throws OpservDataAccessException;
	
	 /**
 	 *  configures the metric.
 	 *
 	 * @param mConfig
 	 * @param tenantId the tenant id
 	 * @return 
 	 * @throws MetricServiceException the metric service exception
 	 */
	void configureMetric(MetricConfig mConfig, UserDetails userDetails) throws OpservDataAccessException;
	
	
	/**
 	 *  configures the metric executiontrigger .
 	 *
 	 * @param mTrigger
 	 * @param tenantId the tenant id
 	 * @return 
 	 * @throws MetricServiceException the metric service exception
 	 */
	void configureMetricExecutionTrigger(
			MetricExecutionTrigger mTrigger, UserDetails userDetails) throws OpservDataAccessException;
			
	/**
 	 *  configures the list of metric executiontrigger .
 	 *
 	 * @param mTrigger
 	 * @param tenantId the tenant id
 	 * @return 
 	 * @throws MetricServiceException the metric service exception
 	 */	
	void configureMetricExecutionTrigger(
			List<MetricExecutionTrigger> mTrigger, UserDetails userDetails) throws OpservDataAccessException;

}
