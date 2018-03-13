package com.cognizant.opserv.sp.service.alignment;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.cognizant.opserv.sp.exception.AffiliationServiceException;
import com.cognizant.opserv.sp.exception.AlignmentServiceException;
import com.cognizant.opserv.sp.exception.CallPlanServiceException;
import com.cognizant.opserv.sp.exception.ChangeRequestServiceException;
import com.cognizant.opserv.sp.exception.CustomerServiceException;
import com.cognizant.opserv.sp.exception.MetricViolationException;
import com.cognizant.opserv.sp.exception.SalesPositionServiceException;
import com.cognizant.opserv.sp.exception.ViewServiceException;
import com.cognizant.opserv.sp.model.Alignment;
import com.cognizant.opserv.sp.model.BusinessReason;
import com.cognizant.opserv.sp.model.CustomerAlignment;
import com.cognizant.opserv.sp.model.CustomerProductAlignment;
import com.cognizant.opserv.sp.model.EntityTemplate;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.model.attrb.ExtdAttribue;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.model.cr.ChangeRequest;
/**
 * ****************************************************************************.
 *
 * @class CustomerAlignmentService contains all the services for customer affiliation
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 * ***************************************************************************
 */
public interface CustomerAlignmentService {
	/**
	 * 
	 * @method getAllCustomerAlignmentsBySalesPosition
	 * @param salesPosition
	 * @param userDetails
	 * @return
	 * @throws AlignmentServiceException
	 * @throws CustomerServiceException 
	 */
	List<CustomerAlignment> getAllCustomerAlignmentsBySalesPosition(SalesPosition salesPosition,UserDetails userDetails) throws AlignmentServiceException, CustomerServiceException;
	/**
	 * 
	 * @method getAllCustomerAlignments
	 * @param customerId
	 * @param buId
	 * @param userDetails
	 * @return
	 * @throws AlignmentServiceException
	 */
	List<CustomerAlignment> getAllCustomerAlignments(long customerId,long buId,UserDetails userDetails) throws AlignmentServiceException;
	/**
	 * 
	 * @method getAllCustomerAlignments
	 * @param customerId
	 * @param userDetails
	 * @return
	 * @throws AlignmentServiceException
	 * @throws CustomerServiceException 
	 * @throws ParseException 
	 */
	List<CustomerAlignment> getAllCustomerAlignments(long customerId,UserDetails userDetails) throws AlignmentServiceException;
	
	/**
	 * 
	 * @method pushCustomers
	 * @param sourceSalesPos
	 * @param targetSalesPos
	 * @param custAlgns
	 * @param action
	 * @param userDetails
	 * @return
	 * @throws AlignmentServiceException
	 * @throws MetricViolationException
	 * @throws CustomerServiceException
	 * @throws AffiliationServiceException
	 * @throws SalesPositionServiceException
	 * @throws ChangeRequestServiceException
	 * @throws CallPlanServiceException 
	 * 
	 */
	long pushCustomer(CustomerAlignment orgCustAlgn,CustomerAlignment newCustAlgn,String comments,UserDetails userDetails) throws AlignmentServiceException,MetricViolationException, CustomerServiceException, AffiliationServiceException, SalesPositionServiceException, ChangeRequestServiceException, CallPlanServiceException,ViewServiceException;
	
	
	/**
	 * @param chngReq
	 * @param orgCustAlgn
	 * @param newCustAlgn
	 * @param comments
	 * @param userDetails
	 * @throws AlignmentServiceException
	 * @throws ChangeRequestServiceException
	 */
	void pushCustomer(ChangeRequest chngReq, CustomerAlignment orgCustAlgn, CustomerAlignment newCustAlgn,
			String comments, UserDetails userDetails) throws AlignmentServiceException, ChangeRequestServiceException,ViewServiceException;
	
	
	/**
     * getExtAttrDetails - To get Extended Attribute for Customer Sales Team
     * @param entityType
     * @param alignment
     * @param userDetails
     * @param custId
     * @return  Map<Integer, List<ExtdAttribue>>
	 * @throws AlignmentServiceException 
     */
	Map<Integer, List<ExtdAttribue>> getCustomerAlignmentExtAttrDetails(
			EntityTemplate entityTemplate, Alignment alignment,
			UserDetails userDetails, List<Integer> custId) throws AlignmentServiceException;
	
	/**
	 * @param custAlignment
	 * @param comments
	 * @param userDetails
	 * @return
	 * @throws AlignmentServiceException
	 * @throws MetricViolationException
	 * @throws CustomerServiceException
	 * @throws ChangeRequestServiceException
	 * @throws CallPlanServiceException
	 */
	 long editCustomer(CustomerAlignment oldCustomerAlignment,CustomerAlignment newCustAlignment,
			String comments,UserDetails userDetails,BusinessReason businessReason,List<CustomerProductAlignment> oldCustomerProductAlignments ,List<CustomerProductAlignment> newCustomerProductAlignments) throws AlignmentServiceException,
			MetricViolationException, CustomerServiceException,
			ChangeRequestServiceException, CallPlanServiceException,ViewServiceException;
	
	
	/**
	 * @param chngReq
	 * @param oldCustAlignment
	 * @param newCustAlignment
	 * @param comments
	 * @param userDetails
	 * @param businessReason
	 * @param oldCustomerProductAlignments
	 * @param newCustomerProductAlignments
	 * @throws AlignmentServiceException
	 * @throws ChangeRequestServiceException
	 */
	void editCustomer(ChangeRequest chngReq, CustomerAlignment oldCustAlignment, CustomerAlignment newCustAlignment, String comments,
			UserDetails userDetails, BusinessReason businessReason, List<CustomerProductAlignment> oldCustomerProductAlignments, List<CustomerProductAlignment> newCustomerProductAlignments)
			throws AlignmentServiceException, ChangeRequestServiceException,ViewServiceException;

	 /**
	 	 * Pull customer.
	 	 *
	 	 * @param orgCustAlgn the org cust algn
	 	 * @param newCustAlgn the new cust algn
	 	 * @param comments the comments
	 	 * @param userDetails the user details
	 	 * @param businessReason the business reason
	 	 * @param oldCustomerProductAlignmentList the old customer product alignment list
	 	 * @param newCustomerProductAlignmentList the new customer product alignment list
	 	 * @return the long
	 	 * @throws AlignmentServiceException the alignment service exception
	 	 * @throws MetricViolationException the metric violation exception
	 	 * @throws CustomerServiceException the customer service exception
	 	 * @throws AffiliationServiceException the affiliation service exception
	 	 * @throws SalesPositionServiceException the sales position service exception
	 	 * @throws ChangeRequestServiceException the change request service exception
	 	 * @throws CallPlanServiceException the call plan service exception
	 	 */
	 	long pullCustomer(CustomerAlignment orgCustAlgn,CustomerAlignment newCustAlgn,String comments,UserDetails userDetails, BusinessReason businessReason,List<CustomerProductAlignment> oldCustomerProductAlignmentList,List<CustomerProductAlignment> newCustomerProductAlignmentList) throws AlignmentServiceException,MetricViolationException, CustomerServiceException, AffiliationServiceException, SalesPositionServiceException, ChangeRequestServiceException, CallPlanServiceException,ViewServiceException;
	 	 

	/**
	 * @param chngReq
	 * @param orgCustAlgn
	 * @param newCustAlgn
	 * @param comments
	 * @param userDetails
	 * @param businessReason
	 * @param oldCustomerProductAlignmentList
	 * @param newCustomerProductAlignmentList
	 * @throws AlignmentServiceException
	 * @throws ChangeRequestServiceException
	 */
	void pullCustomer(ChangeRequest chngReq, CustomerAlignment orgCustAlgn, CustomerAlignment newCustAlgn, String comments,
			UserDetails userDetails, BusinessReason businessReason, List<CustomerProductAlignment> oldCustomerProductAlignmentList, List<CustomerProductAlignment> newCustomerProductAlignmentList)
			throws AlignmentServiceException, ChangeRequestServiceException,ViewServiceException;
		/**
		 * Gets the all customer alignments by cust algnmt id.
		 *
		 * @param custAlgId the cust alg id
		 * @param userDetails the user details
		 * @return the all customer alignments by cust algnmt id
		 * @throws AlignmentServiceException the alignment service exception
		 */
		CustomerAlignment getAllCustomerAlignmentsByCustAlgnmtId(long custAlgId,UserDetails userDetails) throws AlignmentServiceException;
	
}