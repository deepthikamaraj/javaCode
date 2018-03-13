package com.cognizant.opserv.sp.service.internal;

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
import com.cognizant.opserv.sp.model.BusinessReason;
import com.cognizant.opserv.sp.model.CustomerAlignment;
import com.cognizant.opserv.sp.model.CustomerProductAlignment;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.model.cr.ChangeRequest;
import com.cognizant.peg.core.exception.OpservDataAccessException;

/**
 * ****************************************************************************.
 * 
 * @class CustomerAlignmentService contains all the internal services for
 *        customer alignment
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 *        ************************************************************
 *        ***************
 */
public interface CustomerAlignmnetIntService {

	/**
	 * Validate input data.
	 * 
	 * @param orgCustAlgn
	 *            the org cust algn
	 * @param newCustAlgn
	 *            the new cust algn
	 * @param comments
	 *            the comments
	 * @param pushPull
	 *            the push pull
	 * @param userDetails
	 *            the user details
	 * @throws AlignmentServiceException
	 *             the alignment service exception
	 */
	public void validateInputData(ChangeRequest changeRequest, CustomerAlignment orgCustAlgn,
			CustomerAlignment newCustAlgn, String comments,
			UserDetails userDetails) throws AlignmentServiceException;

	/**
	 * Gets the Customer count for sales position.
	 * 
	 * @param salesPosition
	 *            the salesPosition
	 * @param userDetails
	 *            the user details
	 * @return the customer count for sales position
	 * @throws AlignmentServiceException
	 */
	long getCustomerCountForSalesPosition(SalesPosition salesPosition,
			UserDetails userDetails) throws AlignmentServiceException;

	/**
	 * Gets the geo algd customer count for sales position.
	 * 
	 * @param salesPosition
	 *            the sales position
	 * @param userDetails
	 *            the user details
	 * @return the geo algd customer count for sales position
	 * @throws AlignmentServiceException
	 *             the alignment service exception
	 */
	long getGeoAlgdCustomerCountForSalesPosition(SalesPosition salesPosition,
			UserDetails userDetails) throws AlignmentServiceException;

	/**
	 * Gets the non geo algd customer count for sales position.
	 * 
	 * @param salesPosition
	 *            the sales position
	 * @param userDetails
	 *            the user details
	 * @return the non geo algd customer count for sales position
	 * @throws AlignmentServiceException
	 *             the alignment service exception
	 */
	long getNonGeoAlgdCustomerCountForSalesPosition(
			SalesPosition salesPosition, UserDetails userDetails)
			throws AlignmentServiceException;

	/**
	 * Push pull cust update online.
	 * 
	 * @param orgCustAlgn
	 *            the org cust algn
	 * @param newCustAlgn
	 *            the new cust algn
	 * @param comments
	 *            the comments
	 * @param userDetails
	 *            the user details
	 * @param pushPull
	 *            the push pull
	 * @return the long
	 * @throws AlignmentServiceException
	 *             the alignment service exception
	 * @throws MetricViolationException
	 *             the metric violation exception
	 * @throws AffiliationServiceException
	 *             the affiliation service exception
	 * @throws SalesPositionServiceException
	 *             the sales position service exception
	 * @throws ChangeRequestServiceException
	 *             the change request service exception
	 * @throws CustomerServiceException
	 *             the customer service exception
	 * @throws CallPlanServiceException
	 *             the call plan service exception
	 * @throws ViewServiceException 
	 */
	TChngreqOffline pushPullCustUpdateOnSave(CustomerAlignment orgCustAlgn,
			CustomerAlignment newCustAlgn, String comments,
			UserDetails userDetails, String pushPull, BusinessReason businessReason,List<CustomerProductAlignment> oldCustomerProductAlignmentList,List<CustomerProductAlignment> newCustomerProductAlignmentList)
			throws AlignmentServiceException, MetricViolationException,
			AffiliationServiceException, SalesPositionServiceException,
			ChangeRequestServiceException, CustomerServiceException,
			CallPlanServiceException, ViewServiceException;

	
	/**
	 * Validate input data For Edit Customer
	 *
	 * @param newCustAlgn the new cust algn
	 * @param userDetails the user details
	 * @throws AlignmentServiceException the alignment service exception
	 */
	void validateInputDataFrEditCust(ChangeRequest chngReq, CustomerAlignment newCustAlgn,
			UserDetails userDetails) throws AlignmentServiceException;
	
	
	/**
	 * @param custAlignment
	 * @param newCustAlignment 
	 * @param comments
	 * @param userDetails
	 * @param newCustomerProduct 
	 * @return
	 * @throws AlignmentServiceException
	 * @throws MetricViolationException
	 * @throws CustomerServiceException
	 * @throws ChangeRequestServiceException
	 * @throws CallPlanServiceException
	 */
	TChngreqOffline editCustomerOnline(CustomerAlignment custAlignment, CustomerAlignment newCustAlignment, String comments,
			UserDetails userDetails,BusinessReason businessReason, List<CustomerProductAlignment> oldCustomerProductAlignments, List<CustomerProductAlignment> newCustomerProductAlignments ) throws AlignmentServiceException,
			MetricViolationException, CustomerServiceException,
			ChangeRequestServiceException, CallPlanServiceException;

	/**
	 * Edits the ext attr fr cust sales team.
	 *
	 * @param customerAlignment the customer alignment
	 * @param userDetails the user details
	 * @throws AlignmentServiceException the alignment service exception
	 */
	void editExtAttrFrCustSalesTeam(CustomerAlignment customerAlignment,
			UserDetails userDetails) throws AlignmentServiceException;
	
	/**
	 * Edits the ext attr fr cust prd sales team.
	 *
	 * @param newCustomerProductAlignments the new customer product alignments
	 * @param userDetails the user details
	 * @throws AlignmentServiceException the alignment service exception
	 */
	void editExtAttrFrCustPrdSalesTeam(List<CustomerProductAlignment> newCustomerProductAlignments,
			UserDetails userDetails) throws AlignmentServiceException;
	

	/**
	 * Execute fr source sp.
	 *
	 * @param orgCustAlgn the org cust algn
	 * @param userDetails the user details
	 * @return the list
	 * @throws AlignmentServiceException the alignment service exception
	 * @throws CustomerServiceException the customer service exception
	 * @throws CallPlanServiceException the call plan service exception
	 */
	public CustomerAlignment executeUpdatesFrBaseSrcSP(
			CustomerAlignment orgCustAlgn, UserDetails userDetails)
			throws AlignmentServiceException, CustomerServiceException,
			CallPlanServiceException;

	/**
	 * update View For PushPull For Source
	 * 
	 * @param updatedCustAlList
	 * @param userDetails
	 * @throws ViewServiceException 
	 * @throws OpservDataAccessException 
	 */
	void updateViewFrPushPullFrSrc(List<CustomerAlignment> updatedCustAlList,
			UserDetails userDetails) throws OpservDataAccessException, ViewServiceException;
	
	/**
	 * @param triggerType
	 * @param chngReq
	 * @param oldCustAlignment
	 * @param newCustAlignment
	 * @param comments
	 * @param userDetails
	 * @param businessReason
	 * @param oldCustomerProductAlignments
	 * @param newCustomerProductAlignments
	 * @return
	 * @throws ChangeRequestServiceException
	 * @throws ViewServiceException 
	 */
	OfflineRequestMessage lockCustomerAndInsertIntoOffline(String triggerType, ChangeRequest chngReq, CustomerAlignment oldCustAlignment, CustomerAlignment newCustAlignment,
			String comments, UserDetails userDetails, BusinessReason businessReason, List<CustomerProductAlignment> oldCustomerProductAlignments,
			List<CustomerProductAlignment> newCustomerProductAlignments) throws AlignmentServiceException, ChangeRequestServiceException, ViewServiceException;

	/**
	 * @param custAlignment
	 * @param offlineMsg
	 * @param userDetails
	 * @throws AlignmentServiceException
	 * @throws ViewServiceException 
	 */
	void revertlockCustomerAndInsertIntoOffline(CustomerAlignment custAlignment, OfflineRequestMessage offlineMsg, UserDetails userDetails)
			throws AlignmentServiceException, ViewServiceException;

}
