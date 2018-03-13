package com.cognizant.opserv.sp.persistence.dao.service;

import java.util.Map;

import com.cognizant.opserv.sp.common.MetricTriggerType;
import com.cognizant.opserv.sp.common.MetricValidationType;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.peg.core.exception.OpservDataAccessException;


/**
 * ****************************************************************************.
 *
 * @class MetricDAOService contains all the DAO metric services 
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 * ***************************************************************************
 */
public interface MetricDAOService {

	
	/**
	 * @method checkMetricViolation - This method is to check if metrics are violated or not
	 * @param metricTriggerType - the metric trigger types
	 * @param salesPosition - sales position information
	 * @param user - user details
	 * @return MetricValidationType - holds if metrics validation type
	 * @throws OpservDataAccessException - if we have violation or any issue.
	 */
	MetricValidationType checkMetricViolation(SalesPosition salesPosition,MetricTriggerType metricTriggerType,
			UserDetails user) throws OpservDataAccessException;

	Map<String, Integer> getFeatureAndType(Integer mtrTriggerId, Short tenantId);

	Map<Integer, String> getMtrData(Long alignmentId, Long bussUnitId,
			Long salesTeamId, Long salesHierId, Long salesPosId,
			Integer mtrTrgTypeId, Short tenantId);
	
}
