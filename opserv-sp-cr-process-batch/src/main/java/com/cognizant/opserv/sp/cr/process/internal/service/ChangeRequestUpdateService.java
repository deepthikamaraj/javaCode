
package com.cognizant.opserv.sp.cr.process.internal.service;

import com.cognizant.opserv.sp.common.EventType;
import com.cognizant.opserv.sp.exception.AffiliationServiceException;
import com.cognizant.opserv.sp.exception.AlignmentServiceException;
import com.cognizant.opserv.sp.exception.CallPlanServiceException;
import com.cognizant.opserv.sp.exception.ChangeRequestServiceException;
import com.cognizant.opserv.sp.exception.CustomerServiceException;
import com.cognizant.opserv.sp.exception.MetricExecutionException;
import com.cognizant.opserv.sp.exception.MetricViolationException;
import com.cognizant.opserv.sp.exception.SalesPositionServiceException;
import com.cognizant.opserv.sp.exception.ViewServiceException;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.model.cr.ChangeRequest;
import com.cognizant.peg.core.exception.OpservDataAccessException;

/**
 * @author 472731
 * @Interface ChangeRequestUpdateService
 */
public interface ChangeRequestUpdateService {

	/**
	 * @param chngReq
	 * @param userDetails
	 * @param custAlgmnSalesPostionIds
	 * @param geoAlgmnSalesPostionIds
	 * @param aprStsId
	 * @param autoAprStsId
	 * @throws ChangeRequestServiceException
	 */
	void autoRejectChangeRequest(ChangeRequest chngReq, UserDetails userDetails, Integer aprStsId, Integer autoAprStsId)
		throws ChangeRequestServiceException;

	/**
	 * @param chngReq
	 * @param userDetails
	 * @throws MetricExecutionException 
	 * @throws ViewServiceException 
	 */
	void changeRequestDocStore(ChangeRequest chngReq, EventType eventType, UserDetails userDetails)
		throws MetricExecutionException, ViewServiceException;

	/**
	 * @param chngReq
	 * @param userDetails
	 * @return
	 * @throws MetricViolationException
	 * @throws AlignmentServiceException
	 * @throws ChangeRequestServiceException
	 * @throws MetricExecutionException 
	 */
	ChangeRequest approveChangeRequestTransaction(ChangeRequest chngReq, UserDetails userDetails, SalesPosition salesPosition)
		throws MetricViolationException, AlignmentServiceException, ChangeRequestServiceException, AffiliationServiceException, SalesPositionServiceException,
		CustomerServiceException, CallPlanServiceException, MetricExecutionException;

	/**
	 * @param chngReq
	 * @param userDetails
	 * @param custAlgmnSalesPostionIds
	 * @param geoAlgmnSalesPostionIds
	 * @param aprStsId
	 * @param autoAprStsId
	 * @throws ChangeRequestServiceException
	 * @throws CallPlanServiceException
	 * @throws OpservDataAccessException
	 */
	void autoApproveChangeRequest(ChangeRequest chngReq, UserDetails userDetails, Integer aprStsId, Integer autoAprStsId)
		throws ChangeRequestServiceException, OpservDataAccessException, CallPlanServiceException;

	/**
	 * @param chngReq
	 * @param userDetails
	 * @throws ViewServiceException 
	 */
	void changeRequestAlignmentRulesExceution(ChangeRequest chngReq, UserDetails userDetails)
		throws MetricViolationException, AlignmentServiceException, ViewServiceException;

	/**
	 * @param chngReq
	 * @param userDetails
	 * @return
	 * @throws MetricViolationException
	 * @throws AlignmentServiceException
	 * @throws ChangeRequestServiceException
	 * @throws MetricExecutionException 
	 */
	ChangeRequest rejectChangeRequestTransaction(ChangeRequest chngReq, UserDetails userDetails, SalesPosition salesPosition)
		throws MetricViolationException, AlignmentServiceException, ChangeRequestServiceException, AffiliationServiceException, SalesPositionServiceException,
		CustomerServiceException, CallPlanServiceException, MetricExecutionException;

	/**
	 * @param chngReq
	 * @param userDetails
	 */
	void updateChangeRequestStatus(ChangeRequest chngReq, UserDetails userDetails)
		throws MetricViolationException, AlignmentServiceException;
	
	/**
	 * @param chngReq
	 * @param userDetails
	 * @throws AlignmentServiceException
	 * @throws CallPlanServiceException
	 */
	void updateCustSalesTeamAndCustPrdSalesTeam(ChangeRequest chngReq, UserDetails userDetails)
					throws AlignmentServiceException, CallPlanServiceException;
	
    /**
     * @param chngReq
     * @param userDetails
     * @throws AlignmentServiceException
     * @throws CallPlanServiceException
     */
    void updateMirrorCustSalesTeamAndCustSalesTeam(ChangeRequest chngReq, UserDetails userDetails)
					throws AlignmentServiceException, CallPlanServiceException ;

}
