package com.cognizant.opserv.sp.service.alignment.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.opserv.sp.common.CommonConstants;
import com.cognizant.opserv.sp.common.util.JSONUtil;
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
import com.cognizant.opserv.sp.model.Alignment;
import com.cognizant.opserv.sp.model.BusinessReason;
import com.cognizant.opserv.sp.model.CustomerAlignment;
import com.cognizant.opserv.sp.model.CustomerProductAlignment;
import com.cognizant.opserv.sp.model.EntityTemplate;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.model.attrb.ExtdAttribue;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.model.cr.ChangeRequest;
import com.cognizant.opserv.sp.persistence.dao.service.CustomerAlignmentDAOService;
import com.cognizant.opserv.sp.service.alignment.CustomerAlignmentService;
import com.cognizant.opserv.sp.service.changereq.ChangeRequestService;
import com.cognizant.opserv.sp.service.common.CustomerAlignmentCommonService;
import com.cognizant.opserv.sp.service.internal.ChangeRequestIntService;
import com.cognizant.opserv.sp.service.internal.CustomerAlignmnetIntService;
import com.cognizant.peg.core.exception.OpservDataAccessException;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;

/**
 * ****************************************************************************.
 * 
 * @class CustomerAlignmentServiceImpl contains all the services for customer
 *        affiliation
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 *        ************************************************************
 *        ***************
 */
@Service("customerAlignmentService")
public class CustomerAlignmentServiceImpl implements CustomerAlignmentService {

	
	/** The customer Alignment DAO Service. */
	@Autowired
	private CustomerAlignmentDAOService customerAlignmentDAOService;

	/** The request service. */
	@Autowired
	private ChangeRequestIntService requestService;

	@Autowired
	private CustomerAlignmnetIntService customerAlignmnetIntService;
	
	@Autowired
	private CustomerAlignmentCommonService custAlgmtCommonService;

	@Autowired
	private GenericPublisher genericPublisher;
	
	@Autowired
	private CustomerAlignmentCommonService customerAlignmentCommonService;
	
	@Autowired
	private ChangeRequestService changeRequestService;

	private static final OpservLogger LOGGER = OpservLoggerFactory
			.getOpservLogger(CustomerAlignmentServiceImpl.class);

	/**
	 * 
	 * @method getAllCustomerAlignmentsBySalesPosition
	 * @param salesPosition
	 * @param userDetails
	 * @return
	 * @throws AlignmentServiceException
	 * @throws CustomerServiceException  
	 */
	@Override
	@Transactional
	public List<CustomerAlignment> getAllCustomerAlignmentsBySalesPosition(SalesPosition salesPosition, UserDetails userDetails)
			throws AlignmentServiceException, CustomerServiceException {

		try {
			if (null != salesPosition
					&& null != salesPosition.getId()
					&& null != salesPosition.getAlignmment()
					&& null != salesPosition.getAlignmment().getId()
					&& null != salesPosition.getAlignmment().getSalesTeam()
					&& null != salesPosition.getAlignmment().getSalesTeam().getId()
					&& null != salesPosition.getAlignmment().getSalesTeam()
							.getBusinessUnit()
					&& null != salesPosition.getAlignmment().getSalesTeam()
							.getBusinessUnit().getId()
					&& salesPosition.getAlignmment().getId() != 0
					&& salesPosition.getAlignmment().getSalesTeam()
							.getBusinessUnit().getId() != 0
					&& salesPosition.getAlignmment().getSalesTeam().getId() != 0
					&& salesPosition.getId() != 0 && null != userDetails
					&& null != userDetails.getTenantId()
					&& userDetails.getTenantId() != 0) {
			return customerAlignmentDAOService
					.getAllCustomerAlignmentsBySalesPosition(salesPosition,
							userDetails);
			}else{
				LOGGER.info("*****************INPUT PARAMETER SALESPOSITION IS NULL*****************");
				Object[] args  ={"SalesPosition Data is null"};
            	throw new AlignmentServiceException(args);
			}
		} catch (OpservDataAccessException opEx) {
			LOGGER.error("*****************ERROR WHILE FETCHING CUSTOMER DETAILS FOR SALES POSITION*****************"+opEx.getMessage());
			Object[] args = new Object[1];
			args[0] = salesPosition.getId();
			throw new AlignmentServiceException(
					AlignmentServiceExceptionCode.ALGN_SER_EX_CD_006,
					"Exception while fetching customer details for a sales position",
					args, opEx);
		}
	}

	/**
	 * 
	 * @method getAllCustomerAlignments
	 * @param customerId
	 * @param buId
	 * @param userDetails
	 * @return
	 * @throws AlignmentServiceException
	 */
	@Override
	@Transactional
	public List<CustomerAlignment> getAllCustomerAlignments(long customerId,
			long buId, UserDetails userDetails)
			throws AlignmentServiceException {
		try {
			if(customerId!=0 && buId!=0 && userDetails != null ){
				return customerAlignmentDAOService.getCustomerAlignmentFrBuid(
						customerId, buId, userDetails);	
			}else{
				LOGGER.info("*****************INPUT PARAMETER CUSTOMER ID OR BUID IS NULL*****************");
                Object[] args  ={"BuId or customerId is 0"};
                	throw new AlignmentServiceException(args);

			}
		} catch (OpservDataAccessException e) {
			LOGGER.error("*****************ERROR DURING FETCHING OF ALIGNMENTS BY CUSTID BASED ON BUID*****************"+e.getMessage());
			Object[] args = new Object[] { customerId };
			throw new AlignmentServiceException(
					AlignmentServiceExceptionCode.ALGN_SER_EX_CD_200,
					"Error during fetching of alignments by custid based on BuId",
					args, e);
		}
	}

	/**
	 * 
	 * @method getAllCustomerAlignments
	 * @param customerId
	 * @param userDetails
	 * @return
	 * @throws AlignmentServiceException
	 */
	@Override
	@Transactional
	public List<CustomerAlignment> getAllCustomerAlignments(long customerId,
			UserDetails userDetails) throws AlignmentServiceException {

		try {
			if(customerId!=0 && userDetails != null){
				return customerAlignmentDAOService.getCustomerAlignment(customerId,
					userDetails);
			}else{
				LOGGER.info("*****************INPUT PARAMETER CUSTOMER ID NULL*****************");
				Object[] args  ={"customerId is 0"};
            	throw new AlignmentServiceException(args);
			}
		} catch (OpservDataAccessException e) {
			LOGGER.error("*****************ERROR DURING FETCHING OF ALIGNMENTS BY CUSTID*****************"+e.getMessage());
			Object[] args = new Object[] { customerId };
			throw new AlignmentServiceException(
					AlignmentServiceExceptionCode.ALGN_SER_EX_CD_201,
					"Error during fetching of alignments by custid", args, e);
		}
	}

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
	 * @throws ViewServiceException 
	 * @Deprecated To be deleted later.
	 */

	@Override
	@Deprecated
	public long pushCustomer(CustomerAlignment orgCustAlgn,
			CustomerAlignment newCustAlgn, String comments,
			UserDetails userDetails) throws AlignmentServiceException,
			MetricViolationException, CustomerServiceException,
			AffiliationServiceException, SalesPositionServiceException,
			ChangeRequestServiceException, CallPlanServiceException, ViewServiceException {
	
		LOGGER.info("Source CustomerAlignment Json : "+JSONUtil.toJSON(orgCustAlgn));
		LOGGER.info("Target CustomerAlignment Json : "+JSONUtil.toJSON(newCustAlgn));
		LOGGER.info("Comments Json : "+JSONUtil.toJSON(comments));
		LOGGER.info("UserDetails Json : "+JSONUtil.toJSON(userDetails));
	
		TChngreqOffline tChngreqOffline = customerAlignmnetIntService.pushPullCustUpdateOnSave(orgCustAlgn, newCustAlgn, comments, userDetails, CommonConstants.PUSH,null,null,null);
		
		LOGGER.info("tChngreqOffline Json : "+JSONUtil.toJSON(tChngreqOffline));
		
		return this.sendCrOfflineDataToQueue(tChngreqOffline, userDetails, null);
		
	}
	
	/**
	 * @param requestService the requestService to set
	 */
	public void setRequestService(ChangeRequestIntService requestService) {
		this.requestService = requestService;
	}
	
	/**
     * getExtAttrDetails - To get Extended Attribute for Customer Sales Team
     * @param entityType
     * @param alignment
     * @param userDetails
     * @param custId
     * @return  Map<Integer, List<ExtdAttribue>>
	 * @throws AlignmentServiceException 
     */
	@Override
	@Transactional
	public Map<Integer, List<ExtdAttribue>> getCustomerAlignmentExtAttrDetails(
			EntityTemplate entityTemplate, Alignment alignment,
			UserDetails userDetails, List<Integer> custId)
			throws AlignmentServiceException {
		try {
			if (entityTemplate != null && alignment != null
					&& userDetails != null && !custId.isEmpty() && custId != null) {
				return customerAlignmentDAOService
						.getCustomerAlignmentExtAttrDetails(entityTemplate,
								alignment, userDetails, custId);
			} else {
				LOGGER.info("*****************INPUT PARAMETERS ARE NULL*****************");
				Object[] args = { "Input parameters are null" };
				throw new AlignmentServiceException(args);

			}
		} catch (OpservDataAccessException e) {
			LOGGER.error("Error while fetching Customer Extended Attribute by custId "+e.getMessage());
			Object[] args = new Object[] { custId };
			throw new AlignmentServiceException(AlignmentServiceExceptionCode.ALGN_SER_EX_CD_225, "Error while fetching Customer Extended Attribute by custId",
					args, e);
		}
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
	@Deprecated
	public long editCustomer(CustomerAlignment oldCustomerAlignment,CustomerAlignment newCustAlignment,
			String comments,UserDetails userDetails,BusinessReason businessReason,List<CustomerProductAlignment> oldCustomerProductAlignments ,List<CustomerProductAlignment> newCustomerProductAlignments) throws AlignmentServiceException,
			MetricViolationException, CustomerServiceException,
			ChangeRequestServiceException, CallPlanServiceException {
				
				LOGGER.info("Old CustomerAlignment Json : "+JSONUtil.toJSON(oldCustomerAlignment));
				LOGGER.info("New CustomerAlignment Json : "+JSONUtil.toJSON(newCustAlignment));
				LOGGER.info("Comments Json : "+JSONUtil.toJSON(comments));
				LOGGER.info("UserDetails Json : "+JSONUtil.toJSON(userDetails));
				LOGGER.info("BusinessReason Json : "+JSONUtil.toJSON(businessReason));
				LOGGER.info("Old Customer Product Alignment Json : "+JSONUtil.toJSON(oldCustomerProductAlignments));	
				LOGGER.info("New Customer Product Alignment Json : "+JSONUtil.toJSON(newCustomerProductAlignments));
				
				TChngreqOffline tChngreqOffline = customerAlignmnetIntService.editCustomerOnline(oldCustomerAlignment,newCustAlignment, comments, userDetails, businessReason,oldCustomerProductAlignments,newCustomerProductAlignments);
				
				return this.sendCrOfflineDataToQueue(tChngreqOffline, userDetails, businessReason);
			
			}

	/**
	 * send CrData To Queue
	 * @param tChngreqOffline
	 * @param userDetails
	 * @param businessReason
	 * @return Long CRId
	 */
	private Long sendCrOfflineDataToQueue(TChngreqOffline tChngreqOffline, UserDetails userDetails, BusinessReason businessReason){

		LOGGER.info("****************Data going into the queue from Online Process**************" + tChngreqOffline.toString()); 
		Long crId =null;
		if (null != tChngreqOffline.getOfflineId()) {
			final OfflineRequestMessage tempCRBlobMessage = new OfflineRequestMessage();
			tempCRBlobMessage.setChngReqID(tChngreqOffline
					.getTChngReq().getChngReqId());
			tempCRBlobMessage.setOfflineReqId(tChngreqOffline
					.getOfflineId());
			tempCRBlobMessage.setTenantCode(userDetails
					.getTenantCode());
			tempCRBlobMessage.setTenantId(tChngreqOffline
					.getTenantId());
			tempCRBlobMessage.setUserDetails(userDetails);
			tempCRBlobMessage.setTriggerId(tChngreqOffline
					.getTriggerId());
			tempCRBlobMessage.setBusinessReason(businessReason);
			// Send the message to the Queue
			genericPublisher.sendObjectToQueue(
					CommonConstants.ONLINE_UPDATE_QUEUE,
					tempCRBlobMessage);
			crId = tChngreqOffline.getTChngReq().getChngReqId();
		}
		return crId;
	}

	
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
	 * @throws ViewServiceException 
 	 */
	@Override
	@Deprecated
	public long pullCustomer(CustomerAlignment orgCustAlgn,
			CustomerAlignment newCustAlgn, String comments,
			UserDetails userDetails, BusinessReason businessReason,
			List<CustomerProductAlignment> oldCustomerProductAlignmentList,
			List<CustomerProductAlignment> newCustomerProductAlignmentList)
			throws AlignmentServiceException, MetricViolationException,
			CustomerServiceException, AffiliationServiceException,
			SalesPositionServiceException, ChangeRequestServiceException,
			CallPlanServiceException, ViewServiceException {
		
		LOGGER.info("Source CustomerAlignment Json : "+JSONUtil.toJSON(orgCustAlgn));
		LOGGER.info("Target CustomerAlignment Json : "+JSONUtil.toJSON(newCustAlgn));
		LOGGER.info("Comments Json : "+JSONUtil.toJSON(comments));
		LOGGER.info("UserDetails Json : "+JSONUtil.toJSON(userDetails));
		
		LOGGER.info("Old Customer Product Alignment Json : "+JSONUtil.toJSON(oldCustomerProductAlignmentList));	
		LOGGER.info("New Customer Product Alignment Json : "+JSONUtil.toJSON(newCustomerProductAlignmentList));
		
		
		TChngreqOffline tChngreqOffline = customerAlignmnetIntService.pushPullCustUpdateOnSave(orgCustAlgn, newCustAlgn, comments, userDetails, CommonConstants.PULL, businessReason,oldCustomerProductAlignmentList,newCustomerProductAlignmentList);
		
		LOGGER.info("tChngreqOffline Json : "+JSONUtil.toJSON(tChngreqOffline));
		
		return this.sendCrOfflineDataToQueue(tChngreqOffline, userDetails, businessReason);
	}

	
	/**
	 * Gets the all customer alignments by cust algnmt id.
	 *
	 * @param custAlgId the cust alg id
	 * @param userDetails the user details
	 * @return the all customer alignments by cust algnmt id
	 * @throws AlignmentServiceException the alignment service exception
	 */
	@Override
	public CustomerAlignment getAllCustomerAlignmentsByCustAlgnmtId(
			long custAlgId, UserDetails userDetails)
			throws AlignmentServiceException {
		

		try {
			if(custAlgId!=0 && userDetails != null ){
				return customerAlignmentDAOService.getAllCustomerAlignmentsByCustAlgnmtId(custAlgId,userDetails);
			}else{
				LOGGER.info("*****************INPUT PARAMETER CUSTOMER ALIGNMENT ID IS NULL*****************");
                Object[] args  ={"CUSTOMER ALIGNMENT ID 0"};
                	throw new AlignmentServiceException(args);

			}
		} catch (OpservDataAccessException e) {
			LOGGER.error("*****************ERROR DURING FETCHING OF CUSTOMER ALIGNMENTS BY CUSTOMER ALIGNMENT ID*****************"+e.getMessage());
			Object[] args = new Object[] { custAlgId };
			throw new AlignmentServiceException(
					AlignmentServiceExceptionCode.ALGN_SER_EX_CD_200,
					"Error during fetching of customer alignments by customer alignment id",
					args, e);
		}
	
	}


	/**
	 * @param chngReq
	 * @param orgCustAlgn
	 * @param newCustAlgn
	 * @param comments
	 * @param userDetails
	 * @throws AlignmentServiceException
	 * @throws ChangeRequestServiceException
	 * @throws ViewServiceException 
	 */
	@Override
	public void pushCustomer(ChangeRequest chngReq, CustomerAlignment orgCustAlgn, CustomerAlignment newCustAlgn,
			String comments, UserDetails userDetails) throws AlignmentServiceException, ChangeRequestServiceException, ViewServiceException {
		
		LOGGER.info("Source CustomerAlignment Json : "+JSONUtil.toJSON(orgCustAlgn));
		LOGGER.info("Target CustomerAlignment Json : "+JSONUtil.toJSON(newCustAlgn));
		LOGGER.info("Comments Json : "+JSONUtil.toJSON(comments));
		LOGGER.info("UserDetails Json : "+JSONUtil.toJSON(userDetails));
		
		
		LOGGER.info("********************************Validate Input parameters Started ********************************");
		customerAlignmnetIntService.validateInputData(chngReq, orgCustAlgn, newCustAlgn, comments, userDetails);
		LOGGER.info("********************************Validate Input parameters Ended ********************************");

		LOGGER.info("********************************Source and destination SP cannot be same validation -- Started ********************************");
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
		
		LOGGER.info("********************************Source and destination SP cannot be same validation -- Ended ********************************");
		
		// Lock the Customer Alignment and Mark the View flag as dirty and insert into the CR offline request table
		OfflineRequestMessage offlineMsg = customerAlignmnetIntService.lockCustomerAndInsertIntoOffline(CommonConstants.PUSH, chngReq, orgCustAlgn, newCustAlgn, comments, userDetails, null, null, null);

		LOGGER.info("offlineMsg Json : "+JSONUtil.toJSON(offlineMsg));
		// Send the message to the Queue
		if(null != offlineMsg) {
			if(!sendToQueue(offlineMsg)) {
				// Revert the changes if send message to the Queue failed
				customerAlignmnetIntService.revertlockCustomerAndInsertIntoOffline(orgCustAlgn, offlineMsg, userDetails);
				throw new AlignmentServiceException("AlignmentServiceExceptionCode.ALGN_SER_EX_CD_224", "Error occurred during Push Customer");
			}
		} else {
			throw new AlignmentServiceException("AlignmentServiceExceptionCode.ALGN_SER_EX_CD_319", "Customer cannot be pushed since the customer is locked in the Alignment");
		}

	}

	/**
	 * @param chngReq
	 * @param newCustAlignment
	 * @param comments
	 * @param userDetails
	 * @param businessReason
	 * @param oldCustomerProductAlignments
	 * @param newCustomerProductAlignments
	 * @throws AlignmentServiceException
	 * @throws ChangeRequestServiceException
	 * @throws ViewServiceException 
	 */
	@Override
	public void editCustomer(ChangeRequest chngReq, CustomerAlignment oldCustAlignment, CustomerAlignment newCustAlignment,
			String comments, UserDetails userDetails, BusinessReason businessReason, List<CustomerProductAlignment> oldCustomerProductAlignments,
			List<CustomerProductAlignment> newCustomerProductAlignments) throws AlignmentServiceException, ChangeRequestServiceException, ViewServiceException {
		
		LOGGER.info("Old CustomerAlignment Json : "+JSONUtil.toJSON(oldCustAlignment));
		LOGGER.info("New CustomerAlignment Json : "+JSONUtil.toJSON(newCustAlignment));
		LOGGER.info("Comments Json : "+JSONUtil.toJSON(comments));
		LOGGER.info("UserDetails Json : "+JSONUtil.toJSON(userDetails));
		LOGGER.info("BusinessReason Json : "+JSONUtil.toJSON(businessReason));
		LOGGER.info("Old Customer Product Alignment Json : "+JSONUtil.toJSON(oldCustomerProductAlignments));	
		LOGGER.info("New Customer Product Alignment Json : "+JSONUtil.toJSON(newCustomerProductAlignments));
		
		LOGGER.info("***************START OF INPUT DATA VALIDATION FOR CUSTOMER EDIT*******************");
		customerAlignmnetIntService.validateInputDataFrEditCust(chngReq, newCustAlignment, userDetails);
		LOGGER.info("*****************END OF INPUT PARAMETER CUSTOMER ALIGNMENT NULL*****************");
		
		// Lock the Customer Alignment and Mark the View flag as dirty and insert into the CR offline request table
		OfflineRequestMessage offlineMsg = customerAlignmnetIntService.lockCustomerAndInsertIntoOffline(CommonConstants.EDIT,
						chngReq, oldCustAlignment, newCustAlignment, comments, userDetails, businessReason,
						oldCustomerProductAlignments, newCustomerProductAlignments);

		LOGGER.info("offlineMsg Json : "+JSONUtil.toJSON(offlineMsg));
		// Send the message to the Queue
		if(null != offlineMsg) {
			if(!sendToQueue(offlineMsg)) {
				//Revert the changes if send message to the Queue failed
				customerAlignmnetIntService.revertlockCustomerAndInsertIntoOffline(oldCustAlignment, offlineMsg, userDetails);
				throw new AlignmentServiceException("AlignmentServiceExceptionCode.ALGN_SER_EX_CD_308", "Error occurred during Edit Customer");
			}
		} else {
			throw new AlignmentServiceException("AlignmentServiceExceptionCode.ALGN_SER_EX_CD_327", "Customer cannot be edited since the customer is locked in the Alignment");
		}

	}

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
	 * @throws ViewServiceException 
	 */
	@Override
	public void pullCustomer(ChangeRequest chngReq, CustomerAlignment orgCustAlgn, CustomerAlignment newCustAlgn,
			String comments, UserDetails userDetails, BusinessReason businessReason, List<CustomerProductAlignment> oldCustomerProductAlignmentList,
			List<CustomerProductAlignment> newCustomerProductAlignmentList) throws AlignmentServiceException, ChangeRequestServiceException,ViewServiceException {

		LOGGER.info("Source CustomerAlignment Json : "+JSONUtil.toJSON(orgCustAlgn));
		LOGGER.info("Target CustomerAlignment Json : "+JSONUtil.toJSON(newCustAlgn));
		LOGGER.info("Comments Json : "+JSONUtil.toJSON(comments));
		LOGGER.info("UserDetails Json : "+JSONUtil.toJSON(userDetails));
		LOGGER.info("BusinessReason Json : "+JSONUtil.toJSON(businessReason));
		LOGGER.info("Old Customer Product Alignment Json : "+JSONUtil.toJSON(oldCustomerProductAlignmentList));	
		LOGGER.info("New Customer Product Alignment Json : "+JSONUtil.toJSON(newCustomerProductAlignmentList));

		LOGGER.info("********************************Validate Input parameters Started ********************************");
		customerAlignmnetIntService.validateInputData(chngReq, orgCustAlgn, newCustAlgn, comments, userDetails);
		LOGGER.info("********************************Validate Input parameters Ended ********************************");
		
		LOGGER.info("********************************Source and destination SP cannot be same validation -- Started ********************************");
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

		LOGGER.info("********************************Source and destination SP cannot be same validation -- ended ********************************");
		
		// Lock the Customer Alignment and Mark the View flag as dirty and insert into the CR offline request table
		OfflineRequestMessage offlineMsg = customerAlignmnetIntService.lockCustomerAndInsertIntoOffline(CommonConstants.PULL,
						chngReq, orgCustAlgn, newCustAlgn, comments, userDetails, businessReason,
						oldCustomerProductAlignmentList, newCustomerProductAlignmentList);

		LOGGER.info("offlineMsg Json : "+JSONUtil.toJSON(offlineMsg));
		// Send the message to the Queue
		if(null != offlineMsg) {
			if(!sendToQueue(offlineMsg)) {
				//Revert the changes if send message to the Queue failed
				customerAlignmnetIntService.revertlockCustomerAndInsertIntoOffline(orgCustAlgn, offlineMsg, userDetails);
				throw new AlignmentServiceException("AlignmentServiceExceptionCode.ALGN_SER_EX_CD_225", "Error occurred during Pull Customer");
			}
		} else {
			throw new AlignmentServiceException("AlignmentServiceExceptionCode.ALGN_SER_EX_CD_328", "Customer cannot be pulled since the customer is locked in the Alignment");
		}

	}

	/**
	 * @param offlineMsg
	 */
	private boolean sendToQueue(OfflineRequestMessage offlineMsg) {
		
		return genericPublisher.sendObjectToQueue(CommonConstants.ONLINE_UPDATE_QUEUE, offlineMsg);
	}
}