
package com.cognizant.opserv.sp.cr.process.internal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.opserv.sp.common.MetricTriggerType;
import com.cognizant.opserv.sp.common.MetricValidationType;
import com.cognizant.opserv.sp.cr.process.internal.service.MetricExecutionService;
import com.cognizant.opserv.sp.cr.process.internal.service.MetricOfflineService;
import com.cognizant.opserv.sp.exception.AlignmentServiceException;
import com.cognizant.opserv.sp.exception.MetricExecutionException;
import com.cognizant.opserv.sp.exception.MetricViolationException;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.persistence.dao.service.MetricDAOService;
import com.cognizant.peg.core.exception.OpservDataAccessException;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;

/**
 * ****************************************************************************.
 *
 * @class MetricOfflineServiceImpl contains the metric offline services 
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 29/09/2016
 * ***************************************************************************
 */
@Service
public class MetricOfflineServiceImpl implements MetricOfflineService {

	/**
	 * LOGGER
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(MetricOfflineServiceImpl.class);

	/**
	 * MetricExecutionService has the services to process metrics.
	 */
	@Autowired
	private MetricExecutionService metricExecutionService;
	
	/**
	 * MetricDAOService has the services to process & fetch metrics data.
	 */
	@Autowired
	private MetricDAOService metricDAOService;

	/**
	 * Process the metrics
	 * @method processCalculativeMetrics
	 * @param salespos the sales position
	 * @param mtrTrgType the metric trigger type
	 * @param userDetails the user details the user details
	 * @return void
	 * @throws MetricExecutionException the metric execution exception
	 */
	public void processCalculativeMetrics(SalesPosition salespos, MetricTriggerType mtrTrgType, UserDetails userDetails) throws MetricExecutionException {
		metricExecutionService.processCalculativeMetrics(salespos, mtrTrgType, userDetails);
	}

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
	public boolean checkCustomerPushMetricViolation(SalesPosition souceSalesPos, SalesPosition targetSalesPos, UserDetails userDetails)
		 throws AlignmentServiceException, MetricViolationException {

		Boolean result = false;
		try {
			MetricValidationType unAssignFlag = metricDAOService.checkMetricViolation(souceSalesPos, MetricTriggerType.UNASSIGN_CUSTOMER, userDetails);
			LOGGER.info("In MetricOfflineServiceImpl :: PUSH_CUSTOMER --> unAssignFlag:" + unAssignFlag);
			if (MetricValidationType.VALID.equals(unAssignFlag) || MetricValidationType.INVALID_AND_NON_ENFORCABLE.equals(unAssignFlag) ||
				MetricValidationType.NOT_APPILICABLE.equals(unAssignFlag)) {
				MetricValidationType assignFlag = metricDAOService.checkMetricViolation(targetSalesPos, MetricTriggerType.ASSIGN_CUSTOMER, userDetails);
				LOGGER.info("In MetricOfflineServiceImpl :: PUSH_CUSTOMER --> assignFlag:" + assignFlag);
				if (MetricValidationType.VALID.equals(assignFlag) || MetricValidationType.INVALID_AND_NON_ENFORCABLE.equals(assignFlag) ||
					MetricValidationType.NOT_APPILICABLE.equals(assignFlag)) {
					result = true;
				}
				else {
					result = false;
				}
			}
			else {
				result = false;
			}
		}
		catch (OpservDataAccessException exception) {
			LOGGER.error(" Exception in checkCustomerPushMetricViolation - Error while checking metric Validation Type " + exception.getMessage());
			throw new MetricViolationException("Error while checking metric Validation Type", exception.getMessage());
		}
		return result;
	}

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
	public boolean checkCustomerPullMetricViolation(SalesPosition souceSalesPos, SalesPosition targetSalesPos, UserDetails userDetails)
		throws MetricViolationException, AlignmentServiceException {

		Boolean result = false;
		try {
			MetricValidationType unAssignFlag = metricDAOService.checkMetricViolation(souceSalesPos, MetricTriggerType.UNASSIGN_CUSTOMER, userDetails);
			LOGGER.info("In MetricOfflineServiceImpl :: PULL_CUSTOMER --> unAssignFlag:" + unAssignFlag);
			if (MetricValidationType.VALID.equals(unAssignFlag) || MetricValidationType.INVALID_AND_NON_ENFORCABLE.equals(unAssignFlag) ||
				MetricValidationType.NOT_APPILICABLE.equals(unAssignFlag)) {
				MetricValidationType assignFlag = metricDAOService.checkMetricViolation(targetSalesPos, MetricTriggerType.ASSIGN_CUSTOMER, userDetails);
				LOGGER.info("In MetricOfflineServiceImpl :: PULL_CUSTOMER --> assignFlag:" + assignFlag);
				if (MetricValidationType.VALID.equals(assignFlag) || MetricValidationType.INVALID_AND_NON_ENFORCABLE.equals(assignFlag) ||
					MetricValidationType.NOT_APPILICABLE.equals(assignFlag)) {
					result = true;
				}
				else {
					result = false;
				}
			}
			else {
				result = false;
			}
		}
		catch (OpservDataAccessException exception) {
			LOGGER.error(" Exception in checkMetricValidationType - Error while checking metric Validation Type " + exception.getMessage());
			throw new MetricViolationException("Error while checking metric Validation Type", exception.getMessage());
		}
		return result;
	}

	/**
	 * Check if Metrics are violated for given sales position
	 * @method checkCustomerEditMetricViolation
	 * @param targetSalesPos the target sales position
	 * @param userDetails the user details
	* @return boolean true if metrics are not violated
	 * @throws AlignmentServiceException the alignment service exception
	 * @throws MetricViolationException the metric violation exception
	 */
	public boolean checkCustomerEditMetricViolation(SalesPosition targetSalesPos, UserDetails userDetails)
		throws MetricViolationException, AlignmentServiceException {

		Boolean result = false;
		try {
			MetricValidationType unAssignFlag =  metricDAOService.checkMetricViolation(targetSalesPos, MetricTriggerType.EDIT_CUSTOMER, userDetails);
			LOGGER.info("In MetricOfflineServiceImpl :: EDIT CUSTOMER --> unAssignFlag:" + unAssignFlag);
			if (MetricValidationType.VALID.equals(unAssignFlag) || MetricValidationType.INVALID_AND_NON_ENFORCABLE.equals(unAssignFlag) ||
				MetricValidationType.NOT_APPILICABLE.equals(unAssignFlag)) {
				result = true;
			}
			else {
				result = false;
			}
		}
		catch (OpservDataAccessException exception) {
			LOGGER.error(" Exception in checkMetricValidationType - Error while checking metric Validation Type " + exception.getMessage());
			throw new MetricViolationException("Error while checking metric Validation Type", exception.getMessage());
		}
		return result;
	}

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
	public boolean checkZipMovementMetricViolation(SalesPosition souceSalesPos, SalesPosition targetSalesPos, UserDetails userDetails)
		throws MetricViolationException, AlignmentServiceException {

		Boolean result = false;
		try {
			MetricValidationType unAssignFlag =  metricDAOService.checkMetricViolation(souceSalesPos, MetricTriggerType.UNASSIGN_CUSTOMER, userDetails);
			LOGGER.info("In MetricOfflineServiceImpl :: ZIP_MOVEMENT --> unAssignFlag:" + unAssignFlag);
			if (MetricValidationType.VALID.equals(unAssignFlag) || MetricValidationType.INVALID_AND_NON_ENFORCABLE.equals(unAssignFlag) ||
				MetricValidationType.NOT_APPILICABLE.equals(unAssignFlag)) {
				MetricValidationType assignFlag =  metricDAOService.checkMetricViolation(targetSalesPos, MetricTriggerType.ASSIGN_CUSTOMER, userDetails);
				LOGGER.info("In MetricOfflineServiceImpl :: ZIP_MOVEMENT --> assignFlag:" + assignFlag);
				if (MetricValidationType.VALID.equals(assignFlag) || MetricValidationType.INVALID_AND_NON_ENFORCABLE.equals(assignFlag) ||
					MetricValidationType.NOT_APPILICABLE.equals(assignFlag)) {
					result = true;
				}
				else {
					result = false;
				}
			}
			else {
				result = false;
			}
		}
		catch (OpservDataAccessException exception) {
			LOGGER.error(" Exception in checkMetricValidationType - Error while checking metric Validation Type " + exception.getMessage());
			throw new MetricViolationException("Error while checking metric Validation Type", exception.getMessage());
		}
		return result;
	}

}
