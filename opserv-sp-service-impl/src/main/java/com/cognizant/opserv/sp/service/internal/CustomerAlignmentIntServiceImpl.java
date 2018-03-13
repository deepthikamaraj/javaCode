package com.cognizant.opserv.sp.service.internal;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.opserv.sp.common.BussObj;
import com.cognizant.opserv.sp.common.ChangeRequestOfflineStatusType;
import com.cognizant.opserv.sp.common.ChangeRequestStatusType;
import com.cognizant.opserv.sp.common.ChangeRequestTriggerType;
import com.cognizant.opserv.sp.common.CommonConstants;
import com.cognizant.opserv.sp.common.EventType;
import com.cognizant.opserv.sp.common.StatusType;
import com.cognizant.opserv.sp.core.entity.TChngreqOffline;
import com.cognizant.opserv.sp.event.messages.OfflineRequestMessage;
import com.cognizant.opserv.sp.exception.AffiliationServiceException;
import com.cognizant.opserv.sp.exception.AlignmentServiceException;
import com.cognizant.opserv.sp.exception.AlignmentServiceException.AlignmentServiceExceptionCode;
import com.cognizant.opserv.sp.exception.CallPlanServiceException;
import com.cognizant.opserv.sp.exception.ChangeRequestServiceException;
import com.cognizant.opserv.sp.exception.CustomerServiceException;
import com.cognizant.opserv.sp.exception.MetricViolationException;
import com.cognizant.opserv.sp.exception.SalesPositionServiceException;
import com.cognizant.opserv.sp.exception.ViewServiceException;
import com.cognizant.opserv.sp.messaging.GenericPublisher;
import com.cognizant.opserv.sp.model.BusinessReason;
import com.cognizant.opserv.sp.model.CustomerAlignment;
import com.cognizant.opserv.sp.model.CustomerProductAlignment;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.model.cr.ChangeRequest;
import com.cognizant.opserv.sp.persistence.dao.service.ChangeRequestOfflineDAOService;
import com.cognizant.opserv.sp.persistence.dao.service.CustomerAlignmentDAOService;
import com.cognizant.opserv.sp.service.changereq.ChangeRequestService;
import com.cognizant.opserv.sp.service.common.CustomerAlignmentCommonService;
import com.cognizant.opserv.sp.service.common.SPAssignmentsViewService;
import com.cognizant.peg.core.exception.OpservDataAccessException;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;

/**
 * ****************************************************************************.
 * 
 * @class CustomerAlignmentIntServiceImpl contains all the internal services for
 *        customer alignment
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 *        ************************************************************
 *        ***************
 */
@Service
public class CustomerAlignmentIntServiceImpl implements
		CustomerAlignmnetIntService {

	/** The customer alignment dao service. */
	@Autowired
	private CustomerAlignmentDAOService customerAlignmentDAOService;

	/** The customer product alignment service. */
	@Autowired
	private CustomerProductAlignmentIntService customerProductAlignmentIntService;

	/** The request service. */
	@Autowired
	private ChangeRequestIntService requestService;

/*	*//** The call plan int service. *//*
	@Autowired
	private CallPlanIntService callPlanIntService;*/

//	/** The sp assignments service. */
//	@Autowired
//	private SPAssignmentsService spAssignmentsService;

	/** The generic publisher. */
	@Autowired
	private GenericPublisher genericPublisher;

	/** The sp assignments service. */
	@Autowired
	private SPAssignmentsViewService spAssignmentsViewService;
	
	@Autowired
	private CustomerAlignmentCommonService customerAlignmentCommonService;
	
	@Autowired
	private ChangeRequestService changeRequestService;
	
	@Autowired
	private ChangeRequestOfflineDAOService changeRequestOfflineDAOService;

	/** The Constant LOGGER. */
	private static final OpservLogger LOGGER = OpservLoggerFactory
			.getOpservLogger(CustomerAlignmentIntServiceImpl.class);

	

	/**
	 * Update cust al datafr source sp.
	 * 
	 * @param custAlToUpdate
	 *            the cust al to update
	 * @param userDetails
	 *            the user details
	 * @return the customer alignment
	 * @throws AlignmentServiceException
	 *             the alignment service exception
	 */
	private CustomerAlignment updateCustAlDatafrSourceSp(
			CustomerAlignment custAlToUpdate, UserDetails userDetails)
			throws AlignmentServiceException {
		LOGGER.info("************update CustAlData for SourceSp started*********");
		try {
			CustomerAlignment updatedTCustAlgmnt = customerAlignmentDAOService
					.updateCustomerAlignments(custAlToUpdate, userDetails,
							StatusType.PENDING_APPROVAL.getId());

			LOGGER.info("************update CustAlData for SourceSp ended*********");
			return updatedTCustAlgmnt;
		} catch (OpservDataAccessException e) {
			LOGGER.error("Error while updating Customer Alignments "+e.getMessage());
			Object[] args = new Object[] { custAlToUpdate.getCustomer().getId() };
			throw new AlignmentServiceException(
					AlignmentServiceExceptionCode.ALGN_SER_EX_CD_218,
					"Error while updating Customer Alignments", args, e);
		}
	}

	/**
	 * Update cust call plan fr source sp.
	 * 
	 * @param custAlToUp
	 *            the cust al to up
	 * @param userDetails
	 *            the user details
	 * @throws CallPlanServiceException
	 *             the call plan service exception
	 *//*
	private void updateCustCallPlanFrSourceSP(CustomerAlignment custAlToUp,
			UserDetails userDetails,Integer statusId) throws CallPlanServiceException {

		LOGGER.info("************update CustCallPlan For SourceSP started*********");
		List<CustomerCallPlan> customerCallPlanDetails = callPlanIntService
				.getCallPlanBsdCustId(custAlToUp, userDetails);
		if (customerCallPlanDetails != null && !customerCallPlanDetails.isEmpty()) {
			for(CustomerCallPlan customerCallPlan:customerCallPlanDetails){
			custAlToUp.setCustomerCallPlan(customerCallPlan);
			callPlanIntService.updateCallPlanCRStatusForPushPull(custAlToUp, userDetails,statusId);
			}
		
		}
		LOGGER.info("************update CustCallPlan For SourceSP ended*********");
	}*/

	
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

	@Override
	public void validateInputData(ChangeRequest chngReq,
			CustomerAlignment orgCustAlgn, CustomerAlignment newCustAlgn,
			String comments, UserDetails userDetails)
			throws AlignmentServiceException {
		LOGGER.info("===============Execute of validateInputData started=================");
		if (chngReq == null || chngReq.getId() == null) {

			throw new AlignmentServiceException(
					"AlignmentServiceExceptionCode.ALGN_SER_EX_CD_230",
					"Change Request for Push Customer was not raised successfully.");
		}
		if (newCustAlgn.getStartDate() == null) {
			LOGGER.error("******************** Target Start Date Is Not Set ******************");
			throw new AlignmentServiceException(
					"AlignmentServiceExceptionCode.ALGN_SER_EX_CD_241",
					"Target Start Date Is Not Set ");
		}

		if (newCustAlgn.getEndDate() == null) {
			LOGGER.error("******************** Target End Date Is Not Set ******************");
			throw new AlignmentServiceException(
					"AlignmentServiceExceptionCode.ALGN_SER_EX_CD_242",
					"Target End Date Is Not Set ");
		}
		if (newCustAlgn.getEndDate().compareTo(newCustAlgn.getStartDate()) <= 0) {
			LOGGER.error("******************** Effective end date for target must be greater than start date******************");
			throw new AlignmentServiceException(
					"AlignmentServiceExceptionCode.ALGN_SER_EX_CD_256",
					"Effective end date for target must be greater than start date");
		}

		if (orgCustAlgn == null || orgCustAlgn.getSalesPosition() == null
				|| orgCustAlgn.getSalesPosition().getAlignmment() == null
				|| newCustAlgn == null
				|| newCustAlgn.getSalesPosition() == null
				|| newCustAlgn.getSalesPosition().getAlignmment() == null) {
			LOGGER.error("******************** Source/Target Customer Alignment Data Is Null******************");
			throw new AlignmentServiceException(
					"AlignmentServiceExceptionCode.ALGN_SER_EX_CD_245",
					"Source/Target Customer Alignment Data Is Null");
		}

		if (orgCustAlgn.getSalesPosition().getAlignmment().getId() == null

		|| newCustAlgn.getSalesPosition().getAlignmment().getId() == null) {
			LOGGER.error("******************** Alignment Id Is Not Set ******************");
			throw new AlignmentServiceException(
					"AlignmentServiceExceptionCode.ALGN_SER_EX_CD_231",
					"Alignment Id Is Not Set");
		}

		if (orgCustAlgn.getSalesPosition().getAlignmment().getSalesTeam()
				.getBusinessUnit().getId() == null
				|| newCustAlgn.getSalesPosition().getAlignmment()
						.getSalesTeam().getBusinessUnit().getId() == null) {
			LOGGER.error("******************** Business Unit Id Is Not Set ******************");
			throw new AlignmentServiceException(
					"AlignmentServiceExceptionCode.ALGN_SER_EX_CD_232",
					"Business Unit Id Is Not Set ");
		}

		if (orgCustAlgn.getSalesPosition().getAlignmment().getSalesTeam()
				.getId() == null
				|| newCustAlgn.getSalesPosition().getAlignmment()
						.getSalesTeam().getId() == null) {
			LOGGER.error("******************** Sales Team Id Is Not Set ******************");
			throw new AlignmentServiceException(
					"AlignmentServiceExceptionCode.ALGN_SER_EX_CD_233",
					"Sales Team Id Is Not Set ");
		}

		if (orgCustAlgn.getSalesPosition().getId() == null) {
			LOGGER.error("******************** Source Sales Position Id Is Not Set ******************");
			throw new AlignmentServiceException(
					"AlignmentServiceExceptionCode.ALGN_SER_EX_CD_234",
					"Source Sales Position Id Is Not Set ");
		}

		if (orgCustAlgn.getSalesPosition().getHierarchy().getId() == null) {
			LOGGER.error("******************** Source Sales Hierarchy Id Is Not Set ******************");
			throw new AlignmentServiceException(
					"AlignmentServiceExceptionCode.ALGN_SER_EX_CD_235",
					"Source Sales Hierarchy Id Is Not Set ");
		}

		if (newCustAlgn.getSalesPosition().getId() == null) {
			LOGGER.error("******************** Target Sales Position Id Is Not Set ******************");
			throw new AlignmentServiceException(
					"AlignmentServiceExceptionCode.ALGN_SER_EX_CD_236",
					"Target Sales Position Id Is Not Set ");
		}

		if (newCustAlgn.getSalesPosition().getHierarchy().getId() == null) {
			LOGGER.error("******************** Target Sales Hierarchy Id Is Not Set ******************");
			throw new AlignmentServiceException(
					"AlignmentServiceExceptionCode.ALGN_SER_EX_CD_237",
					"Target Sales Hierarchy Id Is Not Set ");
		}

		if (orgCustAlgn.getCustomer() == null
				|| orgCustAlgn.getCustomer().getId() == null
				|| newCustAlgn.getCustomer() == null
				|| newCustAlgn.getCustomer().getId() == null) {
			LOGGER.error("******************** Customer Id Is Not Set ******************");
			throw new AlignmentServiceException(
					"AlignmentServiceExceptionCode.ALGN_SER_EX_CD_238",
					"Customer Id Is Not Set");
		}

		if (orgCustAlgn.getId() == null) {
			LOGGER.error("******************** Customer Alignment Id Is Not Set ******************");
			throw new AlignmentServiceException(
					"AlignmentServiceExceptionCode.ALGN_SER_EX_CD_239",
					"Customer Alignment Id Is Not Set");
		}

		

		if (userDetails.getUserId() == null
				|| userDetails.getTenantId() == null) {
			LOGGER.error("******************** User Id or Tenant Id Is Not Set. ******************");
			throw new AlignmentServiceException(
					"AlignmentServiceExceptionCode.ALGN_SER_EX_CD_244",
					"User Id or Tenant Id Is Not Set");
		}
		

		LOGGER.info("===============Execute of validateInputData ended=================");
	}

	/**
	 * Gets the Customer count for sales position.
	 *
	 * @param salesPosition the salesPosition
	 * @param userDetails the user details
	 * @return the customer count for sales position
	 * @throws AlignmentServiceException the alignment service exception
	 */
	@Override
	public long getCustomerCountForSalesPosition(SalesPosition salesPosition,
			UserDetails userDetails) throws AlignmentServiceException {
		try {
			LOGGER.info("*****************GET CUSTOMER COUNT FOR SALESPOSITION STARTED*****************");
			if (null != salesPosition && null != salesPosition.getId()
					&& null != salesPosition.getHierarchy()
					&& null != salesPosition.getHierarchy().getId()
					&& salesPosition.getId() != 0
					&& salesPosition.getHierarchy().getId() != 0
					&& null != userDetails && null != userDetails.getTenantId()
					&& userDetails.getTenantId() != 0) {
				return customerAlignmentDAOService
						.getCustomerCountForSalesPosition(salesPosition,
								userDetails);
			} else {
				LOGGER.info("*****************INPUT PARAMETER SALESPOSITION ID OR SALESPOSITION HIER ID OR USER DETAILS IS NULL*****************");
				Object[] args = { "spID is 0 or shID is 0 or userDetails is null" };
				throw new AlignmentServiceException(args);
			}
		} catch (AlignmentServiceException ex) {
			LOGGER.error("*****************ERROR WHILE FETCHING CUSTOMER COUNT FOR SALES POSITION*****************");
			Object[] args = new Object[1];
			args[0] = salesPosition.getId();
			throw new AlignmentServiceException(
					AlignmentServiceExceptionCode.ALGN_SER_EX_CD_252,
					"Error while fetching customer count for sales position",
					args, ex);

		}

	}

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
	@Override
	public long getGeoAlgdCustomerCountForSalesPosition(
			SalesPosition salesPosition, UserDetails userDetails)
			throws AlignmentServiceException {
		try {
			LOGGER.info("*****************GET GEO-ALGD CUSTOMER COUNT FOR SALESPOSITION STARTED*****************");
			if (null != salesPosition && null != salesPosition.getId()
					&& null != salesPosition.getHierarchy()
					&& null != salesPosition.getHierarchy().getId()
					&& salesPosition.getId() != 0
					&& salesPosition.getHierarchy().getId() != 0
					&& null != userDetails && null != userDetails.getTenantId()
					&& userDetails.getTenantId() != 0) {
				return customerAlignmentDAOService
						.getGeoAlgdCustomerCountForSalesPosition(salesPosition,
								userDetails);
			} else {
				LOGGER.info("*****************INPUT PARAMETER SALESPOSITION ID OR SALESPOSITION HIER ID OR USER DETAILS IS NULL*****************");
				Object[] args = { "spID is 0 or shID is 0 or userDetails is null" };
				throw new AlignmentServiceException(args);
			}
		} catch (AlignmentServiceException ex) {
			LOGGER.error("*****************ERROR WHILE FETCHING GEO ALIGNED CUSTOMER COUNT FOR SALES POSITION*****************");
			Object[] args = new Object[1];
			args[0] = salesPosition.getId();
			throw new AlignmentServiceException(
					AlignmentServiceExceptionCode.ALGN_SER_EX_CD_253,
					"Error while fetching geo aligned customer count for sales position",
					args, ex);

		}
	}

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
	@Override
	public long getNonGeoAlgdCustomerCountForSalesPosition(
			SalesPosition salesPosition, UserDetails userDetails)
			throws AlignmentServiceException {
		try {
			LOGGER.info("*****************GET NON GEO-ALGD CUSTOMER COUNT FOR SALESPOSITION STARTED*****************");
			if (null != salesPosition && null != salesPosition.getId()
					&& null != salesPosition.getHierarchy()
					&& null != salesPosition.getHierarchy().getId()
					&& salesPosition.getId() != 0
					&& salesPosition.getHierarchy().getId() != 0
					&& null != userDetails && null != userDetails.getTenantId()
					&& userDetails.getTenantId() != 0) {
				return customerAlignmentDAOService
						.getNonGeoAlgdCustomerCountForSalesPosition(
								salesPosition, userDetails);
			} else {
				LOGGER.info("*****************INPUT PARAMETER SALESPOSITION ID OR SALESPOSITION HIER ID OR USER DETAILS IS NULL*****************");
				Object[] args = { "spID is 0 or shID is 0 or userDetails is null" };
				throw new AlignmentServiceException(args);
			}
		} catch (AlignmentServiceException ex) {
			LOGGER.error("*****************ERROR WHILE FETCHING NON GEO ALIGNED CUSTOMER COUNT FOR SALES POSITION*****************");
			Object[] args = new Object[1];
			args[0] = salesPosition.getId();
			throw new AlignmentServiceException(
					AlignmentServiceExceptionCode.ALGN_SER_EX_CD_254,
					"Error while fetching non geo aligned customer count for sales position",
					args, ex);

		}
	}

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
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = {
			AlignmentServiceException.class, MetricViolationException.class,
			CustomerServiceException.class, AffiliationServiceException.class,
			SalesPositionServiceException.class,
			ChangeRequestServiceException.class, CallPlanServiceException.class })
	public TChngreqOffline pushPullCustUpdateOnSave(
			CustomerAlignment orgCustAlgn, CustomerAlignment newCustAlgn,
			String comments, UserDetails userDetails, String pushPull,
			BusinessReason businessReason,List<CustomerProductAlignment> oldCustomerProductAlignmentList,List<CustomerProductAlignment> newCustomerProductAlignmentList) throws AlignmentServiceException,
			MetricViolationException, AffiliationServiceException,
			SalesPositionServiceException, ChangeRequestServiceException,
			CustomerServiceException, CallPlanServiceException, ViewServiceException {
		
		ChangeRequest chngRequest = new ChangeRequest();
		TChngreqOffline tChngreqOffline = null;
		
		try {
			LOGGER.info("*****************Push/Pull Customer Update Online started*****************");
			// To validate Input parameters
			LOGGER.info("*****************To validate Input parameters*****************");

			this.validateInputData(null, orgCustAlgn, newCustAlgn, comments,
					userDetails);

			// Source and destination SP cannot be same validation
			try {
				if (orgCustAlgn.getSalesPosition().getId()
						.equals(newCustAlgn.getSalesPosition().getId())
						&& orgCustAlgn
								.getSalesPosition()
								.getHierarchy()
								.getId()
								.equals(newCustAlgn.getSalesPosition()
										.getHierarchy().getId())) {
					throw new AlignmentServiceException(
							"AlignmentServiceExceptionCode.ALGN_SER_EX_CD_255",
							"Source and Destination SalesPosition cannot be same");
				}

			} catch (AlignmentServiceException ex) {
				LOGGER.error("Source and Destination SalesPosition cannot be same");
				Object[] args = new Object[1];
				args[0] = orgCustAlgn.getSalesPosition().getId();

				throw new AlignmentServiceException(
						AlignmentServiceExceptionCode.ALGN_SER_EX_CD_255,
						"Source and Destination SalesPosition cannot be same",
						args, ex);

			}

			LOGGER.info("*****************To check whether the customer's CR status is in PS/PA in the Alignment*****************");
			boolean isLocked = customerAlignmentDAOService
					.checkCustCRStatusInAlgmnt(orgCustAlgn.getCustomer()
							.getId().intValue(), orgCustAlgn.getSalesPosition()
							.getAlignmment().getId(), orgCustAlgn
							.getSalesPosition().getAlignmment().getSalesTeam()
							.getBusinessUnit().getId(), orgCustAlgn
							.getSalesPosition().getAlignmment().getSalesTeam()
							.getId(),userDetails);
			
			if(isLocked){
				
				List<Long> custIdList = new ArrayList<Long>();
				custIdList.add(newCustAlgn.getCustomer().getId());

				// For Source SP -- To Update/Lock Customer with Base Source SP
				LOGGER.info("*****************EXECUTE FOR SOURCE SP ONLINE*****************");
				CustomerAlignment updatedCustAL = null;
				if (orgCustAlgn != null && orgCustAlgn.getSalesPosition() != null) {
					updatedCustAL = this.executeUpdatesFrBaseSrcSP(orgCustAlgn,
							userDetails);
				}

				// To Raise Change Request for Base Source and Base Target SP
				LOGGER.info("*****************To Raise Change Request for Base Source and Base Target SP*****************");
				if (pushPull.equalsIgnoreCase(CommonConstants.PUSH)) {
					chngRequest = requestService.findExistingChangeRequestId(
							newCustAlgn.getSalesPosition(),
							orgCustAlgn.getSalesPosition(), userDetails,
							ChangeRequestTriggerType.PUSH_CUSTOMER.getId());
					LOGGER.info("*****************CR ID generated from Push Online Process is*****************" + chngRequest.getId());

				} else if (pushPull.equalsIgnoreCase(CommonConstants.PULL)) {
					chngRequest = requestService.findExistingChangeRequestId(
							newCustAlgn.getSalesPosition(),
							orgCustAlgn.getSalesPosition(), userDetails,
							ChangeRequestTriggerType.PULL_CUSTOMER.getId());
					LOGGER.info("*****************CR ID generated from Pull Online Process is*****************" + chngRequest.getId());
					
				}
				
				try {
					if (chngRequest.getId() != null) {
						

						// To Insert the Base Source Customer Alignment, Base Target
						// Customer Alignment, CRId into the temp table
						LOGGER.info("*****************To Insert the Source Customer Alignment, Target Customer Alignment, CRId into the tChngreqOffline table*****************");
						tChngreqOffline = customerAlignmentDAOService
								.saveIntoCROffline(updatedCustAL, newCustAlgn,
										chngRequest, comments, userDetails,
										pushPull, businessReason,oldCustomerProductAlignmentList,newCustomerProductAlignmentList);
						LOGGER.info("*****************Push/Pull Customer Update Online ended*****************");
						
						requestService.updateChangeRequestStatus(tChngreqOffline
								.getTChngReq().getChngReqId(),
								ChangeRequestStatusType.WORK_IN_PROGRESS.getId(),
								userDetails);
						
						List<CustomerAlignment> updatedCustAlList = new ArrayList<CustomerAlignment>();
						updatedCustAlList.add(updatedCustAL);
						// For Source SP -- To UpdateSPView
						this.updateViewFrPushPullFrSrc(updatedCustAlList, userDetails);
						return tChngreqOffline;
					} else {
						throw new AlignmentServiceException(
								"AlignmentServiceExceptionCode.ALGN_SER_EX_CD_230",
								"Change Request was not raised successfully .");
					}

				} catch (AlignmentServiceException ex) {
					LOGGER.error("Change Request was not raised successfully");
					Object[] args = new Object[] {};
					throw new AlignmentServiceException(
							AlignmentServiceExceptionCode.ALGN_SER_EX_CD_230,
							"Change Request was not raised successfully ", args, ex);
				}
				
			}else{
				throw new AlignmentServiceException(
						"AlignmentServiceExceptionCode.ALGN_SER_EX_CD_319",
						"Customer cannot be pushed since the customer is locked in the Alignment");
			}
		} catch (OpservDataAccessException e) {
			LOGGER.error("Error while Push Customer"+e.getMessage());
			Object[] args = new Object[] { newCustAlgn.getCustomer().getId() };
			if (pushPull.equalsIgnoreCase(CommonConstants.PUSH)) {
				throw new AlignmentServiceException(
						AlignmentServiceExceptionCode.ALGN_SER_EX_CD_224,
						"Error while Push Customer", args, e);
			} else {
				throw new AlignmentServiceException(
						AlignmentServiceExceptionCode.ALGN_SER_EX_CD_225,
						"Error while Pull Customer", args, e);
			}

		}
	}

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
			CallPlanServiceException {

		LOGGER.info("****** Execution for Push/Pull for Source SP started*****");

		// Step 1: To Update in TCustAlgnmt table for Source Sps

		LOGGER.info("****** To update in TCustAlgmnt table for Mirror Source SP*****");
		CustomerAlignment updatedTCustAlgmnt = this.updateCustAlDatafrSourceSp(
				orgCustAlgn, userDetails);
		updatedTCustAlgmnt.setLocked(true);
		// Step ii : To save in TCustPrdAlgmnt table for Source SP
		/*LOGGER.info("****** To save in TCustPrdAlgmnt table for Source SP*****");
		this.updateCustPrdAlDataFrSourceSP(orgCustAlgn, userDetails);*/ //commented as status id not there in  TCustPrdAlgmnt table

		// Step iii : To save in TCustCallPlan
		// tables for Source SP
		LOGGER.info("****** To save in TCustCallPlan, TCallDir and TCallDirPrd tables for Source SP*****");
		/*if (orgCustAlgn != null && orgCustAlgn.getSalesPosition() != null) {
			this.updateCustCallPlanFrSourceSP(orgCustAlgn, userDetails,StatusType.PENDING_SUBMISSION.getId());
		}*/

		LOGGER.info("****** Execution for Push/Pull for Source SP ended*****");
		return updatedTCustAlgmnt;
	}


	/**
	 * Validate input data For Edit Customer
	 * 
	 * @param newCustAlgn
	 *            the new cust algn
	 * @param userDetails
	 *            the user details
	 * @throws AlignmentServiceException
	 *             the alignment service exception
	 */
	@Override
	public void validateInputDataFrEditCust(ChangeRequest chngReq,
			CustomerAlignment customerAlignment, UserDetails userDetails)
			throws AlignmentServiceException {
		LOGGER.info("******************** Execute of validateInputDataFrEditCust -- started******************");

		if (chngReq == null || chngReq.getId() == null) {
			throw new AlignmentServiceException(
					"AlignmentServiceExceptionCode.ALGN_SER_EX_CD_230",
					"Change Request was not raised successfully .");
		}

		if (customerAlignment == null
				|| customerAlignment.getSalesPosition() == null
				|| customerAlignment.getSalesPosition().getAlignmment() == null) {
			LOGGER.info("******************** Target Customer Alignment Data Is Null******************");
			throw new AlignmentServiceException(
					"AlignmentServiceExceptionCode.ALGN_SER_EX_CD_307",
					"Target Customer Alignment Data Is Null");
		}

		if (customerAlignment.getSalesPosition().getAlignmment().getId() == null) {
			LOGGER.info("******************** Alignment Id Is Not Set ******************");
			throw new AlignmentServiceException(
					"AlignmentServiceExceptionCode.ALGN_SER_EX_CD_231",
					"Alignment Id Is Not Set");
		}

		if (customerAlignment.getSalesPosition().getAlignmment().getSalesTeam()
				.getBusinessUnit().getId() == null) {
			LOGGER.info("******************** Business Unit Id Is Not Set ******************");
			throw new AlignmentServiceException(
					"AlignmentServiceExceptionCode.ALGN_SER_EX_CD_232",
					"Business Unit Id Is Not Set ");
		}

		if (customerAlignment.getSalesPosition().getAlignmment().getSalesTeam()
				.getId() == null) {
			LOGGER.info("******************** Sales Team Id Is Not Set ******************");
			throw new AlignmentServiceException(
					"AlignmentServiceExceptionCode.ALGN_SER_EX_CD_233",
					"Sales Team Id Is Not Set ");
		}

		if (customerAlignment.getSalesPosition().getId() == null) {
			LOGGER.info("******************** Target Sales Position Id Is Not Set ******************");
			throw new AlignmentServiceException(
					"AlignmentServiceExceptionCode.ALGN_SER_EX_CD_236",
					"Target Sales Position Id Is Not Set ");
		}

		if (customerAlignment.getSalesPosition().getHierarchy().getId() == null) {
			LOGGER.info("******************** Target Sales Hierarchy Id Is Not Set ******************");
			throw new AlignmentServiceException(
					"AlignmentServiceExceptionCode.ALGN_SER_EX_CD_237",
					"Target Sales Hierarchy Id Is Not Set ");
		}

		if (customerAlignment.getCustomer() == null
				|| customerAlignment.getCustomer().getId() == null) {
			LOGGER.info("******************** Customer Id Is Not Set ******************");
			throw new AlignmentServiceException(
					"AlignmentServiceExceptionCode.ALGN_SER_EX_CD_238",
					"Customer Id Is Not Set");
		}

		if (userDetails.getUserId() == null
				|| userDetails.getTenantId() == null) {
			LOGGER.info("******************** User Id or Tenant Id Is Not Set. ******************");
			throw new AlignmentServiceException(
					"AlignmentServiceExceptionCode.ALGN_SER_EX_CD_244",
					"User Id or Tenant Id Is Not Set");
		}

		if (customerAlignment.getEndDate().compareTo(
				customerAlignment.getStartDate()) <= 0) {
			LOGGER.info("******************** Effective end date for target must be greater than start date******************");
			throw new AlignmentServiceException(
					"AlignmentServiceExceptionCode.ALGN_SER_EX_CD_256",
					"Effective end date for target must be greater than start date");
		}

		LOGGER.info("******************** Execute of validateInputDataFrEditCust -- ended******************");
	}

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
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = {
			AlignmentServiceException.class, CustomerServiceException.class,
			MetricViolationException.class,
			ChangeRequestServiceException.class, CallPlanServiceException.class })
	public TChngreqOffline editCustomerOnline(
			CustomerAlignment oldCustAlignment,
			CustomerAlignment newCustAlignment, String comments,
			UserDetails userDetails, BusinessReason businessReason,
			List<CustomerProductAlignment> oldCustomerProductAlignments,
			List<CustomerProductAlignment> newCustomerProductAlignments)
			throws AlignmentServiceException, MetricViolationException,
			CustomerServiceException, ChangeRequestServiceException,
			CallPlanServiceException {
		try {
			// TODO Auto-generated method stub
			LOGGER.info("***************START OF INPUT DATA VALIDATION FOR CUSTOMER EDIT*******************");
			this.validateInputDataFrEditCust(null, newCustAlignment, userDetails);
			LOGGER.info("*****************END OF INPUT PARAMETER CUSTOMER ALIGNMENT NULL*****************");
			
			
			LOGGER.info("*****************START OF EDIT PLANNED CALLS*****************");
			this.updateCustAlDatafrSourceSp(newCustAlignment, userDetails);
	       // callPlanIntService.updateCallPlanCRStatusFrEdit(newCustAlignment, userDetails);
			
			LOGGER.info("*****************END OF EDIT PLANNED CALLS*****************");
             
			// To raise CR for Customer Edit
			Long crId=null;
			LOGGER.info("*****************START TO GENERATE CHANGE REQUEST ID*****************");
			 ChangeRequest chngRequest = requestService.findExistingChangeRequestId(
					 newCustAlignment.getSalesPosition(), newCustAlignment.getSalesPosition(), userDetails,
					ChangeRequestTriggerType.EDIT_CUSTOMER.getId());
				LOGGER.info("*****************END OF GENERATING CHANGE REQUEST ID*****************");

			
				if (chngRequest.getId() != null) {
					// To Insert the Source Customer Alignment, Target
					// Customer Alignment, CRId into the temp table
					LOGGER.info("*****************START TO  INSERT INTO TEMP CHANGE REQUEST TABLE*****************");
					TChngreqOffline tChngreqOffline = customerAlignmentDAOService
							.saveIntoCROffline(oldCustAlignment, newCustAlignment, chngRequest, comments,userDetails,CommonConstants.EDIT,businessReason,oldCustomerProductAlignments, newCustomerProductAlignments);
					LOGGER.info("*****************END OF INSERTING INTO TEMP CHANGE REQUEST TABLE*****************");
					
					requestService.updateChangeRequestStatus(tChngreqOffline
							.getTChngReq().getChngReqId(),
							ChangeRequestStatusType.WORK_IN_PROGRESS.getId(),
							userDetails);

				return tChngreqOffline;
			} else {
				throw new AlignmentServiceException(
						"AlignmentServiceExceptionCode.ALGN_SER_EX_CD_230",
						"Change Request was not raised successfully .");
			}

		} catch (OpservDataAccessException e) {
			LOGGER.error("*****************INPUT PARAMETER CUSTOMER ID NULL*****************"+e.getMessage());
			Object[] args = new Object[] { newCustAlignment.getCustomer().getId() };
			throw new AlignmentServiceException(
					AlignmentServiceExceptionCode.ALGN_SER_EX_CD_308,
					"Exception while editing customer alignment", args, e);
		}
	}

	/**
	 * Edits the ext attr fr cust sales team.
	 *
	 * @param customerAlignment the customer alignment
	 * @param userDetails the user details
	 * @throws AlignmentServiceException the alignment service exception
	 */
	@Override
	public void editExtAttrFrCustSalesTeam(CustomerAlignment customerAlignment,
			UserDetails userDetails)
			throws AlignmentServiceException {
		try{
			if(customerAlignment.getExtdAttributes()!=null || !customerAlignment.getExtdAttributes().isEmpty()){
			customerAlignmentDAOService.editExtAttrFrCustSalesTeam(null, customerAlignment, userDetails);
			}
		}catch(OpservDataAccessException e){
			LOGGER.error("Exception while editing customerAlignment's extended attributes"+e.getMessage());
			throw new AlignmentServiceException(AlignmentServiceExceptionCode.ALGN_SER_EX_CD_314,"Exception while editing customerAlignment's extended attributes", null, e);
		}		
	}
	
	
	/**
	 * Edits the ext attr fr cust prd sales team.
	 *
	 * @param newCustomerProductAlignments the new customer product alignments
	 * @param userDetails the user details
	 * @throws AlignmentServiceException the alignment service exception
	 */

	@Override
	public void editExtAttrFrCustPrdSalesTeam(List<CustomerProductAlignment> customerProductAlignmentList, UserDetails userDetails)
			throws AlignmentServiceException {
		try{
			if(customerProductAlignmentList != null && customerProductAlignmentList.size() !=0 ){
			customerAlignmentDAOService.editExtAttrFrCustPrdSalesTeam(customerProductAlignmentList, userDetails);
			}
		}catch(OpservDataAccessException e){
			LOGGER.error("Exception while editing customer Products's extended attributes"+e.getMessage());
			throw new AlignmentServiceException(AlignmentServiceExceptionCode.ALGN_SER_EX_CD_315,"Exception while editing customer Products's extended attributes", null, e);
		}		
	}

	
	/**
	 * update View For PushPull For Source.
	 * 
	 * @param updatedCustAlList
	 *            the updated cust al list
	 * @param userDetails
	 *            the user details
	 * @throws ViewServiceException 
	 * @throws OpservDataAccessException 
	 */

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void updateViewFrPushPullFrSrc(
			List<CustomerAlignment> updatedCustAlList, UserDetails userDetails) throws OpservDataAccessException, ViewServiceException {

		for (CustomerAlignment updatedCA : updatedCustAlList) {
			spAssignmentsViewService.updateSPView(updatedCA.getSalesPosition()
					.getId(), updatedCA.getCustomer().getId(),
					BussObj.CUSTOMER_ALIGNMENT_VIEW.getBussObjName(),EventType.LOCKED, userDetails);
			/*spAssignmentsViewService.updateSPView(updatedCA.getSalesPosition()
					.getId(), updatedCA.getCustomer().getId(),
					StoreEntity.CUSTOMER_ALIGNMENT_NON_POA, userDetails);*/
		}

	}

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
	 * @throws ViewServiceException 
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {AlignmentServiceException.class,ChangeRequestServiceException.class})
	public OfflineRequestMessage lockCustomerAndInsertIntoOffline(String triggerType, ChangeRequest chngReq, CustomerAlignment oldCustAlignment,
			CustomerAlignment newCustAlignment, String comments, UserDetails userDetails, BusinessReason businessReason,
			List<CustomerProductAlignment> oldCustomerProductAlignments, List<CustomerProductAlignment> newCustomerProductAlignments) throws AlignmentServiceException, ChangeRequestServiceException, ViewServiceException {

		// Lock the Customer Alignment and Mark the View flag as dirty
		
		boolean isLocked = customerAlignmentCommonService.lockCustomerAlignment(oldCustAlignment, userDetails);
		if(!isLocked) {
			return null;
		}
		
		OfflineRequestMessage offlineMsg = null;
		
		if (CommonConstants.PUSH.equalsIgnoreCase(triggerType)) {
			offlineMsg = changeRequestService.createOffLinePushRequest(chngReq, oldCustAlignment, newCustAlignment, comments, userDetails);
			
		} else if (CommonConstants.PULL.equalsIgnoreCase(triggerType)) {
			offlineMsg = changeRequestService.createOffLinePullRequest(chngReq, oldCustAlignment, newCustAlignment,
							comments, userDetails, businessReason, oldCustomerProductAlignments, newCustomerProductAlignments);
			
		} else if (CommonConstants.EDIT.equalsIgnoreCase(triggerType)) {
			offlineMsg = changeRequestService.createOffLineEditRequest(chngReq, oldCustAlignment, newCustAlignment,
							comments, userDetails, businessReason, oldCustomerProductAlignments, newCustomerProductAlignments);
		}
		
		return offlineMsg;
	}
	
	/**
	 * @param custAlignment
	 * @param offlineMsg
	 * @param userDetails
	 * @throws AlignmentServiceException
	 * @throws ViewServiceException 
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = AlignmentServiceException.class)
	public void revertlockCustomerAndInsertIntoOffline(CustomerAlignment custAlignment, OfflineRequestMessage offlineMsg,
			UserDetails userDetails) throws AlignmentServiceException, ViewServiceException {
		
		try {
				// Unlock customer alignment
				customerAlignmentCommonService.unLockCustomerAlignment(custAlignment, userDetails);
				// Cancel CR Offline
				changeRequestOfflineDAOService.updateCROfflineStatus(ChangeRequestOfflineStatusType.CANCELLED.getId(),
					offlineMsg.getOfflineReqId(), userDetails.getUserId(), userDetails.getTenantId(), "Internal Error - Message could not be delivered");
				
		} catch (OpservDataAccessException e) {
			LOGGER.error("Exception while updating customer offline request into t_chngreq_offline table : " + e.getMessage());
			throw new AlignmentServiceException("AlignmentServiceExceptionCode.ALGN_SER_EX_CD_323", "Exception while updating CR Offline Status");
		}
		
	}
}