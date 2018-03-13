package com.cognizant.opserv.sp.service.internal;

import java.util.Date;
import java.util.List;

import com.cognizant.opserv.sp.core.entity.TChngreqOffline;
import com.cognizant.opserv.sp.event.messages.OfflineRequestMessage;
import com.cognizant.opserv.sp.exception.AffiliationServiceException;
import com.cognizant.opserv.sp.exception.AlignmentServiceException;
import com.cognizant.opserv.sp.exception.CallPlanServiceException;
import com.cognizant.opserv.sp.exception.ChangeRequestServiceException;
import com.cognizant.opserv.sp.exception.CustomerServiceException;
import com.cognizant.opserv.sp.exception.MetricViolationException;
import com.cognizant.opserv.sp.exception.SalesPositionServiceException;
import com.cognizant.opserv.sp.exception.ViewServiceException;
import com.cognizant.opserv.sp.model.CustomerAlignment;
import com.cognizant.opserv.sp.model.GeographyAlignment;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.model.cr.ChangeRequest;

/**
 * The Interface GeoAlgmntInternalService.
 */
public interface GeoAlgmntInternalService {

	/**
	 * Do zip movement.
	 * 
	 * @param sourceGeoAlign
	 *            the source geo align
	 * @param targetGeoAlign
	 *            the target geo align
	 * @param custAligns
	 *            the cust aligns
	 * @param comment
	 *            the comment
	 * @param userDetails
	 *            the user details
	 * @return the long
	 * @throws AlignmentServiceException
	 *             the alignment service exception
	 * @throws MetricViolationException
	 *             the metric violation exception
	 * @throws CustomerServiceException
	 *             the customer service exception
	 * @throws AffiliationServiceException
	 *             the affiliation service exception
	 * @throws SalesPositionServiceException
	 *             the sales position service exception
	 * @throws ChangeRequestServiceException
	 *             the change request service exception
	 * @throws CallPlanServiceException
	 *             the call plan service exception
	 */
	/*Long doZipMovement(GeographyAlignment sourceGeoAlign,
			GeographyAlignment targetGeoAlign,
			List<CustomerAlignment> custAligns, String comment,
			UserDetails userDetails) throws AlignmentServiceException,
			MetricViolationException, CustomerServiceException,
			AffiliationServiceException, SalesPositionServiceException,
			ChangeRequestServiceException, CallPlanServiceException;*/

	/**
	 * Update doc store.
	 * 
	 * @param sourceGeoAlign
	 *            the source geo align
	 * @param targetGeoAlign
	 *            the target geo align
	 * @param custAligns
	 *            the cust aligns
	 * @param userDetails
	 *            the user details
	 * @throws AlignmentServiceException
	 *             the alignment service exception
	 */
	/*void updateDocStore(GeographyAlignment sourceGeoAlign,
			GeographyAlignment targetGeoAlign,
			List<CustomerAlignment> custAligns, UserDetails userDetails)
			throws AlignmentServiceException;*/

	/**
	 * Update source effective end date.
	 * 
	 * @param sourceGeoAlign
	 *            the source geo align
	 * @param newEndDate
	 *            the new end date
	 * @param userDetails
	 *            the user details
	 */
	void updateSourceEffectiveEndDate(GeographyAlignment sourceGeoAlign,
			Date newEndDate, UserDetails userDetails);

	/**
	 * Gets the zip count.
	 * 
	 * @param salesPosition
	 *            the sales position
	 * @param userDetails
	 *            the user details
	 * @return the zip count
	 * @throws AlignmentServiceException
	 *             the alignment service exception
	 */
	Long getZipCount(SalesPosition salesPosition, UserDetails userDetails)
			throws AlignmentServiceException;
	
	/**
	 * Do zip movement offline.
	 * 
	 * @param sourceGeoAlign
	 *            the source geo align
	 * @param targetGeoAlign
	 *            the target geo align
	 * @param custAligns
	 *            the cust aligns
	 * @param comment
	 *            the comment
	 * @param userDetails
	 *            the user details
	 * @return the t chngreq offline
	 * @throws AlignmentServiceException
	 *             the alignment service exception
	 * @throws MetricViolationException
	 *             the metric violation exception
	 * @throws CustomerServiceException
	 *             the customer service exception
	 * @throws AffiliationServiceException
	 *             the affiliation service exception
	 * @throws SalesPositionServiceException
	 *             the sales position service exception
	 * @throws ChangeRequestServiceException
	 *             the change request service exception
	 * @throws CallPlanServiceException
	 *             the call plan service exception
	 */
	TChngreqOffline doZipMovementOnline(GeographyAlignment sourceGeoAlign,
			GeographyAlignment targetGeoAlign,
			List<CustomerAlignment> custAligns, String comment,
			UserDetails userDetails) throws AlignmentServiceException,
			MetricViolationException, CustomerServiceException,
			AffiliationServiceException, SalesPositionServiceException,
			ChangeRequestServiceException, CallPlanServiceException;
	

	/**
	 * @param changeRequest
	 * @param sourceGeoAlign
	 * @param targetGeoAlign
	 * @param custAligns
	 * @param comment
	 * @param userDetails
	 * @return
	 * @throws AlignmentServiceException
	 * @throws MetricViolationException
	 * @throws CustomerServiceException
	 * @throws AffiliationServiceException
	 * @throws SalesPositionServiceException
	 * @throws ChangeRequestServiceException
	 * @throws CallPlanServiceException
	 * @throws ViewServiceException 
	 */
	OfflineRequestMessage doZipMovementOnline(ChangeRequest changeRequest, GeographyAlignment sourceGeoAlign,
			GeographyAlignment targetGeoAlign,
			List<CustomerAlignment> custAligns, String comment,
			UserDetails userDetails) throws AlignmentServiceException,
			MetricViolationException, CustomerServiceException,
			AffiliationServiceException, SalesPositionServiceException,
			ChangeRequestServiceException, CallPlanServiceException, ViewServiceException;

	/**
	 * @param geoAlign
	 * @param custAligns
	 * @param offlineMsg
	 * @param userDetails
	 * @throws AlignmentServiceException
	 * @throws ViewServiceException 
	 */
	void revertlockZipAndInsertIntoOffline(GeographyAlignment geoAlign,
			List<CustomerAlignment> custAligns, OfflineRequestMessage offlineMsg, UserDetails userDetails) throws AlignmentServiceException, ViewServiceException;

	/**
	 * Publish to queue.
	 * 
	 * @param tChngreqOffline
	 *            the t chngreq offline
	 * @param userDetails
	 *            the user details
	 */
	void publishToQueue(TChngreqOffline tChngreqOffline, UserDetails userDetails);
}
