package com.cognizant.opserv.sp.cr.process.internal.service;

import com.cognizant.opserv.sp.common.MetricTriggerType;
import com.cognizant.opserv.sp.exception.MetricExecutionException;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.model.auth.UserDetails;


/**
 * ****************************************************************************.
 *
 * @class MetricExecutionService contains the metric execution services 
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 29/09/2016
 * ***************************************************************************
 */
public interface MetricExecutionService {

	/**
	 * Process the metrics
	 * @method processCalculativeMetrics
	 * @param salespos the sales position
	 * @param mtrTrgType the metric trigger type
	 * @param userDetails the user details
	 * @return void
	 * @throws MetricExecutionException the metric execution exception
	 */
	void processCalculativeMetrics(SalesPosition salespos, MetricTriggerType mtrTrgType, UserDetails userDetails) throws MetricExecutionException;
    
}
