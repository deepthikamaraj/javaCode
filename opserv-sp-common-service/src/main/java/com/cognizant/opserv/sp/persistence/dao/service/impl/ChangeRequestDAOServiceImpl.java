package com.cognizant.opserv.sp.persistence.dao.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.cognizant.opserv.sp.assembler.ChangeRequestAssembler;
import com.cognizant.opserv.sp.assembler.CustomerAlignmentEntityAssembler;
import com.cognizant.opserv.sp.assembler.CustomerAlignmentModelAssembler;
import com.cognizant.opserv.sp.assembler.GeographyAlignmentModelAssembler;
import com.cognizant.opserv.sp.changerequest.json.ChangeRequestDetailsBlob;
import com.cognizant.opserv.sp.changerequest.json.ChangeRequestZipAlignmentDetailsBlob;
import com.cognizant.opserv.sp.common.ChangeRequestApproverType;
import com.cognizant.opserv.sp.common.ChangeRequestStatusType;
import com.cognizant.opserv.sp.common.ChangeRequestTriggerType;
import com.cognizant.opserv.sp.common.CommonConstants;
import com.cognizant.opserv.sp.common.util.DateUtil;
import com.cognizant.opserv.sp.common.util.JSONUtil;
import com.cognizant.opserv.sp.core.dao.TChngReqApprDAO;
import com.cognizant.opserv.sp.core.dao.TChngReqApprHierDAO;
import com.cognizant.opserv.sp.core.dao.TChngReqApprTypeDAO;
import com.cognizant.opserv.sp.core.dao.TChngReqBussReasonDAO;
import com.cognizant.opserv.sp.core.dao.TChngReqConfigDAO;
import com.cognizant.opserv.sp.core.dao.TChngReqDAO;
import com.cognizant.opserv.sp.core.dao.TCustAlgmntChngReqDetDAO;
import com.cognizant.opserv.sp.core.dao.TCustAlgmntDAO;
import com.cognizant.opserv.sp.core.dao.TSalesPosDAO;
import com.cognizant.opserv.sp.core.dao.TTerrZipAlgmntChngReqDetDAO;
import com.cognizant.opserv.sp.core.dao.TTerrZipAlgmntDAO;
import com.cognizant.opserv.sp.core.entity.TChngReq;
import com.cognizant.opserv.sp.core.entity.TChngReqAppr;
import com.cognizant.opserv.sp.core.entity.TChngReqApprType;
import com.cognizant.opserv.sp.core.entity.TChngReqBussReason;
import com.cognizant.opserv.sp.core.entity.TChngReqConfig;
import com.cognizant.opserv.sp.core.entity.TCustAffChngReqDet;
import com.cognizant.opserv.sp.core.entity.TCustAlgmnt;
import com.cognizant.opserv.sp.core.entity.TCustAlgmntChngReqDet;
import com.cognizant.opserv.sp.core.entity.TCustAlgmntChngReqDetId;
import com.cognizant.opserv.sp.core.entity.TCustCallPlanChngReqDet;
import com.cognizant.opserv.sp.core.entity.TPrdAlgmntChngReqDet;
import com.cognizant.opserv.sp.core.entity.TSalesPos;
import com.cognizant.opserv.sp.core.entity.TSalesPosChngReqDet;
import com.cognizant.opserv.sp.core.entity.TTerrZipAlgmnt;
import com.cognizant.opserv.sp.core.entity.TTerrZipAlgmntChngReqDet;
import com.cognizant.opserv.sp.exception.CallPlanServiceException;
import com.cognizant.opserv.sp.model.Customer;
import com.cognizant.opserv.sp.model.CustomerAlignment;
import com.cognizant.opserv.sp.model.CustomerCallPlan;
import com.cognizant.opserv.sp.model.CustomerProductAlignment;
import com.cognizant.opserv.sp.model.GeographyAlignment;
import com.cognizant.opserv.sp.model.PostalCode;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.model.cr.ChangeRequest;
import com.cognizant.opserv.sp.model.cr.ChangeRequestApproverDetails;
import com.cognizant.opserv.sp.model.cr.ChangeRequestLineItem;
import com.cognizant.opserv.sp.model.cr.CustomerAlignmentChangeRequestDetails;
import com.cognizant.opserv.sp.model.cr.ZipAlignmentChangeRequestDetails;
import com.cognizant.opserv.sp.persistence.dao.service.CallPlanDAOService;
import com.cognizant.opserv.sp.persistence.dao.service.ChangeRequestDAOService;
import com.cognizant.opserv.sp.persistence.dao.service.CustomerAlignmentDAOService;
import com.cognizant.opserv.sp.persistence.dao.service.CustomerProductAlignmentDAOService;
import com.cognizant.opserv.sp.persistence.dao.service.GeographyAlignmentDAOService;
import com.cognizant.opserv.sp.persistence.dao.service.ProductAlignmentDAOService;
import com.cognizant.opserv.sp.persistence.dao.service.SPAssignmentsViewDAOService;
import com.cognizant.opserv.sp.service.workflow.changereq.RestClient;
import com.cognizant.peg.core.exception.OpservDataAccessException;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
/**
 * ****************************************************************************.
 * 
 * @class ChangeRequestDAOServiceImpl contains all the DAO services for change
 *        request
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 *        ************************************************************
 *        ***************
 */
@Component
public class ChangeRequestDAOServiceImpl implements ChangeRequestDAOService {

	/** LOGGER. */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(ChangeRequestDAOServiceImpl.class);
	
	/** The create process instance url. */
	@Value("${activiti.create.process.instance}") 
	private  String createProcessInstanceURL;
	
	/** changeRequestDao. */
	@Autowired
	private TChngReqDAO changeRequestDao;

	
	/** customerAlignmentDAOService. */
	@Autowired
	private CustomerAlignmentDAOService customerAlignmentDAOService;
	
	/** productAlignmentDAOService. */
	@Autowired
	private ProductAlignmentDAOService productAlignmentDAOService;

	/** tSalesPosDAO. */
	@Autowired
	private TSalesPosDAO tSalesPosDAO;
	
	
	/** tTerrZipAlgmntDAO. */
	@Autowired
	private TTerrZipAlgmntDAO tTerrZipAlgmntDAO;
	
	/** tCustAlgmntDAO. */
	@Autowired
	private TCustAlgmntDAO tCustAlgmntDAO;

	/** changeRequestAssembler. */
	@Autowired
	private ChangeRequestAssembler changeRequestAssembler;

	/** The customer alignment model assembler. */
	@Autowired
	private CustomerAlignmentModelAssembler customerAlignmentModelAssembler;
	
	/** The customer alignment entity assembler. */
	@Autowired
	private CustomerAlignmentEntityAssembler customerAlignmentEntityAssembler;
	
	/** The change request config dao. */
	@Autowired
	private TChngReqConfigDAO changeRequestConfigDAO;

	/** The t chng req buss reason dao. */
	@Autowired
	private TChngReqBussReasonDAO tChngReqBussReasonDAO;
	
	/** The customer aligment chnage request details dao. */
	@Autowired
	private TCustAlgmntChngReqDetDAO customerAligmentChnageRequestDetailsDAO;
	
	/** The t terr zip algmnt chng req det dao. */
	@Autowired
	private TTerrZipAlgmntChngReqDetDAO tTerrZipAlgmntChngReqDetDAO;
	
	/** The change request approver dao. */
	@Autowired
	private TChngReqApprDAO changeRequestApproverDAO;
	
	/** The change Request Approver Type DAO. */
	@Autowired
	private TChngReqApprTypeDAO changeRequestApproverTypeDAO;
	
	/** The change Request Approver Hierarchy DAO */
	@Autowired
	private TChngReqApprHierDAO changeRequestApproverHierarchyDAO;
	
//	/** The call plan int service. */
//	@Autowired
//	private CallPlanIntService callPlanIntService;
	
	@Autowired
	private SPAssignmentsViewDAOService spAssignmentsViewService;
	
	@Autowired
	private GeographyAlignmentModelAssembler geographyAlignmentModelAssembler;
	
	@Autowired
	private GeographyAlignmentDAOService geographyAlignmentProcessDAOService;
	
	@Autowired
	private CustomerProductAlignmentDAOService customerProductAlignmentDAOService;
	
	@Autowired
	private CallPlanDAOService callPlanOfflineService;
	
	
	/**
 * (non-Javadoc).
 *
 * @param salesPostion the sales postion
 * @param userDetails the user details
 * @return the all change requests for approval
 * @throws OpservDataAccessException the opserv data access exception
 * @see com.cognizant.opserv.sp.persistence.dao.service.ChangeRequestDAOService#getAllChangeRequestsForApproval(com.cognizant.opserv.sp.model.SalesPosition, com.cognizant.opserv.sp.model.auth.UserDetails)
 */
	@Override
	public List<ChangeRequest> getAllChangeRequestsForApproval(SalesPosition salesPostion, UserDetails userDetails) throws OpservDataAccessException {
		Integer statusId = ChangeRequestStatusType.PENDING_FOR_APPROVAL.getId();
		List<TChngReq> changeRequestList = changeRequestDao.findAllTChngReqForApproval(salesPostion.getId(), salesPostion.getHierarchy().getId(),
				salesPostion.getTenantId(), statusId);
		return changeRequestAssembler.convertTChngReqToChangeRequestModel(changeRequestList);
	}

	/**
	 * (non-Javadoc).
	 *
	 * @param salesPostion the sales postion
	 * @param userDetails the user details
	 * @return the all change requests
	 * @throws OpservDataAccessException the opserv data access exception
	 * @see com.cognizant.opserv.sp.persistence.dao.service.ChangeRequestDAOService#getAllChangeRequests(com.cognizant.opserv.sp.model.SalesPosition, com.cognizant.opserv.sp.model.auth.UserDetails)
	 */
	@Override
	public List<ChangeRequest> getAllChangeRequests(SalesPosition salesPostion, UserDetails userDetails) throws OpservDataAccessException {
	try{
		List<TChngReq> changeRequestList = changeRequestDao.findAllTChngReq(salesPostion.getId(), salesPostion.getHierarchy().getId(),
				salesPostion.getTenantId());
	
		return changeRequestAssembler.convertTChngReqToChangeRequestModel(changeRequestList);
	}catch(RuntimeException re){
		LOGGER.error("Error occurred while getting all Change Requests");
		throw new OpservDataAccessException("Exception occurred while getting all Change Requests", re);
	}
		
	}

	/**
	 * (non-Javadoc).
	 *
	 * @param customer the customer
	 * @param salesPostion the sales postion
	 * @param userDetails the user details
	 * @return the all change requests of customer
	 * @throws OpservDataAccessException the opserv data access exception
	 * @see com.cognizant.opserv.sp.persistence.dao.service.ChangeRequestDAOService#getAllChangeRequestsOfCustomer(com.cognizant.opserv.sp.model.Customer, com.cognizant.opserv.sp.model.SalesPosition, com.cognizant.opserv.sp.model.auth.UserDetails)
	 */
	@Override
	public List<ChangeRequest> getAllChangeRequestsOfCustomer(Customer customer, SalesPosition salesPostion, UserDetails userDetails)
			throws OpservDataAccessException {
		try{
			List<TChngReq> changeRequestList = changeRequestDao.findAllTChngReqByCustomer(customer.getId().intValue(), salesPostion.getId(), salesPostion
					.getHierarchy().getId(), salesPostion.getAlignmment().getId(), salesPostion.getAlignmment().getSalesTeam().getBusinessUnit().getId(),
					salesPostion.getAlignmment().getSalesTeam().getId(), salesPostion.getTenantId());

			return changeRequestAssembler.convertTChngReqToChangeRequestModel(changeRequestList);
		}catch(RuntimeException re){
			LOGGER.error("Error occurred while getting all Change Requests of customer");
			throw new OpservDataAccessException("Exception occurred while getting all Change Requests", re);
		}
	}

	/** (non-Javadoc)
	 * @see com.cognizant.opserv.sp.persistence.dao.service.ChangeRequestDAOService#getChangeRequestByChangeRequestId(com.cognizant.opserv.sp.model.cr.ChangeRequest)
	 */
	@Override
	public ChangeRequest getChangeRequestByChangeRequestId(ChangeRequest chngReq) throws OpservDataAccessException{
		
		try{
		if(chngReq.getId() != null){
		TChngReq tChngReq = changeRequestDao.findTChngReqById(chngReq.getId());
		if(tChngReq != null){
		return changeRequestAssembler.convertTChngReqToChangeRequestModel(tChngReq);
		}
		}else{
			throw new OpservDataAccessException("Change Request Id is Null");
		}
		}catch (RuntimeException dae) {
			LOGGER.error("Error while fetching change Request details / Change Request Id is Null");
			throw new OpservDataAccessException(
					"Exception while fetching change Request details / Change Request Id is Null",dae);
		}
		return null;
	}
	
	/** (non-Javadoc)
	 * @see com.cognizant.opserv.sp.persistence.dao.service.ChangeRequestDAOService#getChangeRequestStatusByChangeRequestId(com.cognizant.opserv.sp.model.cr.ChangeRequest)
	 */
	@Override
	public ChangeRequest getChangeRequestStatusByChangeRequestId(ChangeRequest chngReq) throws OpservDataAccessException {
		try{
			TChngReq tChngReq = changeRequestDao.findTChngReqById(chngReq.getId());
			ChangeRequest changeRequest = new ChangeRequest();
			changeRequest.setId(tChngReq.getChngReqId());
			changeRequest.setStatus(ChangeRequestStatusType.getChangeRequestStatusType(tChngReq.getStatusId()));
			return changeRequest;
		}catch(RuntimeException re){
			LOGGER.error("Error while fetching change Request status Change Request Id");
			throw new OpservDataAccessException("Exception while fetching change Request status Change Request Id ",re);
		}
		
	}


	
	/** (non-Javadoc)
	 * @see com.cognizant.opserv.sp.persistence.dao.service.ChangeRequestDAOService#getChangeRequestLineItemsByChangeRequestId(com.cognizant.opserv.sp.model.cr.ChangeRequest)
	 */
	@Override
	public ChangeRequest getChangeRequestLineItemsByChangeRequestId(ChangeRequest chngReq) throws OpservDataAccessException {
		try{
			TChngReq tChngReq = changeRequestDao.findTChngReqById(chngReq.getId());
			ChangeRequest changeRequest = changeRequestAssembler.convertTChngReqToChangeRequestModel(tChngReq);
			if (tChngReq.getTriggerId().equals(ChangeRequestTriggerType.PUSH_CUSTOMER.getId()) || tChngReq.getTriggerId().equals(ChangeRequestTriggerType.PULL_CUSTOMER.getId())
					|| tChngReq.getTriggerId().equals(ChangeRequestTriggerType.PUSH_ZIP.getId()) || tChngReq.getTriggerId().equals(ChangeRequestTriggerType.EDIT_CUSTOMER.getId())) {
				if (null != tChngReq.gettCustAlgmntChngReqDetss()) {
					List<CustomerAlignmentChangeRequestDetails> customerAlignmentChangeRequestDetailsList = null;
					List<ChangeRequestLineItem> chnageRequestLineItemsList = new ArrayList<ChangeRequestLineItem>();
					customerAlignmentChangeRequestDetailsList = changeRequestAssembler.convertListofTCustAlignmentChngReqToListofCustomerAlignmentChangeRequestModel(tChngReq);
					chnageRequestLineItemsList.addAll(customerAlignmentChangeRequestDetailsList);
					changeRequest.setLineItems(chnageRequestLineItemsList);
				}

			}
			if (tChngReq.getTriggerId().equals(ChangeRequestTriggerType.PUSH_ZIP.getId())) {
				if (null != tChngReq.gettTerrZipAlgmntChngReqDetss()) {
					List<ZipAlignmentChangeRequestDetails> zipAlignmentChangeRequestDetailsList = null;
					List<ChangeRequestLineItem> chnageRequestLineItemsList = new ArrayList<ChangeRequestLineItem>();
					zipAlignmentChangeRequestDetailsList = changeRequestAssembler.convertListofTCustAlignChngReqToListofZipAlignmentChangeRequestModel(tChngReq);
					chnageRequestLineItemsList.addAll(zipAlignmentChangeRequestDetailsList);
					changeRequest.setLineItems(chnageRequestLineItemsList);
				}
			}
			return changeRequest;
		}catch(RuntimeException re){
			LOGGER.error("Error while fetching change Request LineItems Change Request Id");
			throw new OpservDataAccessException("Exception while fetching change Request LineItems Change Request Id ",re);
		}
		
	}
	
	/** (non-Javadoc)
	 * @see com.cognizant.opserv.sp.persistence.dao.service.ChangeRequestDAOService#getCustomerAlignmentChangeRequestDetailsByChangeRequest(com.cognizant.opserv.sp.model.cr.ChangeRequest)
	 */
	@Override
	public List<CustomerAlignmentChangeRequestDetails> getCustomerAlignmentChangeRequestDetailsByChangeRequest(ChangeRequest chngReq) throws OpservDataAccessException {
		try{
			List<CustomerAlignmentChangeRequestDetails> customerAlignmentChangeRequestDetailsList = null;
			TChngReq tChngReq = changeRequestDao.findTChngReqById(chngReq.getId());
			if (tChngReq.getTriggerId().equals(ChangeRequestTriggerType.PUSH_CUSTOMER.getId()) || tChngReq.getTriggerId().equals(ChangeRequestTriggerType.PULL_CUSTOMER.getId())
					|| tChngReq.getTriggerId().equals(ChangeRequestTriggerType.PUSH_ZIP.getId()) || tChngReq.getTriggerId().equals(ChangeRequestTriggerType.EDIT_CUSTOMER.getId())) {
				if (null != tChngReq.gettCustAlgmntChngReqDetss()) {
					customerAlignmentChangeRequestDetailsList = changeRequestAssembler.convertListofTCustAlignChngReqToListofCustomerAlignmentChangeRequestModel(tChngReq);
				}

			}
			return customerAlignmentChangeRequestDetailsList;
		}catch(RuntimeException re){
			LOGGER.error("Error while fetching custAlignmentCR details by change request");
			throw new OpservDataAccessException("Exception while fetching custAlignmentCR details by change request",re);
		}
	}

	
	/** (non-Javadoc)
	 * @see com.cognizant.opserv.sp.persistence.dao.service.ChangeRequestDAOService#getZipAlignmentChangeRequestDetailsByChangeRequest(com.cognizant.opserv.sp.model.cr.ChangeRequest)
	 */
	@Override
	public List<ZipAlignmentChangeRequestDetails> getZipAlignmentChangeRequestDetailsByChangeRequest(ChangeRequest chngReq) throws OpservDataAccessException {
		try{
			List<ZipAlignmentChangeRequestDetails> zipAlignmentChangeRequestDetailsList = null;
			TChngReq tChngReq = changeRequestDao.findTChngReqById(chngReq.getId());
			if (tChngReq.getTriggerId().equals(ChangeRequestTriggerType.PUSH_ZIP.getId())) {
				if (null != tChngReq.gettTerrZipAlgmntChngReqDetss()) {
					zipAlignmentChangeRequestDetailsList = changeRequestAssembler.convertListofTCustAlignChngReqToListofZipAlignmentChangeRequestModel(tChngReq);
				}
			}
			return zipAlignmentChangeRequestDetailsList;
		}catch(RuntimeException re){
			LOGGER.error("Error while fetching zipAlignmentCR details by change request");
			throw new OpservDataAccessException("Exception while fetching zipAlignmentCR details by change request",re);
		}
	}

	/**
	 * Sets the change request dao.
	 *
	 * @param changeRequestDao the new change request dao
	 */
	public void setChangeRequestDao(TChngReqDAO changeRequestDao) {
		this.changeRequestDao = changeRequestDao;
	}
	
	/**
	 * Sets the customer alignment dao service.
	 *
	 * @param customerAlignmentDAOService the customerAlignmentDAOService to set
	 */
	public void setCustomerAlignmentDAOService(CustomerAlignmentDAOService customerAlignmentDAOService) {
		this.customerAlignmentDAOService = customerAlignmentDAOService;
	}

	/**
	 * Sets the t sales pos dao.
	 *
	 * @param tSalesPosDAO the tSalesPosDAO to set
	 */
	public void settSalesPosDAO(TSalesPosDAO tSalesPosDAO) {
		this.tSalesPosDAO = tSalesPosDAO;
	}

	/**
	 * Sets the product alignment dao service.
	 *
	 * @param productAlignmentDAOService the productAlignmentDAOService to set
	 */
	public void setProductAlignmentDAOService(ProductAlignmentDAOService productAlignmentDAOService) {
		this.productAlignmentDAOService = productAlignmentDAOService;
	}

	

	/**
	 * Sets the t terr zip algmnt dao.
	 *
	 * @param tTerrZipAlgmntDAO the tTerrZipAlgmntDAO to set
	 */
	public void settTerrZipAlgmntDAO(TTerrZipAlgmntDAO tTerrZipAlgmntDAO) {
		this.tTerrZipAlgmntDAO = tTerrZipAlgmntDAO;
	}

	/**
	 * Sets the t cust algmnt dao.
	 *
	 * @param tCustAlgmntDAO the tCustAlgmntDAO to set
	 */
	public void settCustAlgmntDAO(TCustAlgmntDAO tCustAlgmntDAO) {
		this.tCustAlgmntDAO = tCustAlgmntDAO;
	}

	/** (non-Javadoc)
	 * @see com.cognizant.opserv.sp.persistence.dao.service.ChangeRequestDAOService#updateChangeRequestStatus(long, java.lang.Integer, com.cognizant.opserv.sp.model.auth.UserDetails)
	 */
	@Override
	public void updateChangeRequestStatus(long changeRequestId, Integer statusId, UserDetails userDetails)
			throws OpservDataAccessException {
		try{
			changeRequestDao.updateCRStatus(changeRequestId, statusId, userDetails.getUserId(), userDetails.getTenantId());	
		}
		catch(RuntimeException runtimeException){
			LOGGER.error("Error occurred during updating change request status");
			throw new OpservDataAccessException("Error occur during updating change request status",runtimeException);
		}
        

	}
	
	
	@Override
	public void updateCRLineStatusForCustAlgmt(long changeRequestId, long custAlgmtId, Integer statusId, UserDetails userDetails) throws OpservDataAccessException {
		try{
			customerAligmentChnageRequestDetailsDAO.updateCustAlgmntCRDetStatus(custAlgmtId, changeRequestId, statusId, userDetails.getUserId(), userDetails.getTenantId());	
		}
		catch(RuntimeException runtimeException){
			throw new OpservDataAccessException("Error occur during updating customer alignement CR detail",runtimeException);
		}
		
	}

	/** (non-Javadoc)
	 * @see com.cognizant.opserv.sp.persistence.dao.service.ChangeRequestDAOService#markCRsAsDirty(java.lang.Long, com.cognizant.opserv.sp.model.auth.UserDetails)
	 */
	@Override
	public boolean markCRsAsDirty(Long spId, UserDetails userDetails)
			throws OpservDataAccessException {
		try{
			List<TChngReq> tChngReqs = changeRequestDao.markChangeRequestAsDirty(spId, userDetails.getTenantId());
			if(!tChngReqs.isEmpty() || tChngReqs.size() != 0){
				return true;
			}
			return false;
		}
		catch(RuntimeException runtimeException){
			LOGGER.error("Error occur during updating change request status");
			throw new OpservDataAccessException("Error occur during updating change request status",runtimeException);
		}
		
		
	}

	/**
	 * (non-Javadoc).
	 *
	 * @param chngReq the chng req
	 * @param userDetails the user details
	 * @return the list
	 * @throws OpservDataAccessException the opserv data access exception
	 * @see com.cognizant.opserv.sp.persistence.dao.service.ChangeRequestDAOService#findParentChangeRequestIdByChildChangeRequestId(com.cognizant.opserv.sp.model.cr.ChangeRequest, com.cognizant.opserv.sp.model.auth.UserDetails)
	 */
	@Override
	public List<Long> findParentChangeRequestIdByChildChangeRequestId(ChangeRequest chngReq, UserDetails userDetails) throws OpservDataAccessException {
		try{
			List<Long> parentChangeRequestIds = new ArrayList<Long>();
			List<Object[]> parentChangeRequestIdsList = changeRequestDao.findParentChangeRequestIdByChildChangeRequestId(chngReq.getId(), userDetails.getTenantId());
			if (null != parentChangeRequestIdsList) {
				for (Object parentChngId : parentChangeRequestIdsList) {
					if (null != parentChngId) {
						Long parentChangeRequestId = (Long) parentChngId;
						parentChangeRequestIds.add(parentChangeRequestId);
					}
				}
			}
			return parentChangeRequestIds;
		}catch(RuntimeException runtimeException){
			LOGGER.error("Error occurred while finding parent CR by child CR");
			throw new OpservDataAccessException("Error occurred while finding parent CR by child CR",runtimeException);
		}
	}

	/** (non-Javadoc)
	 * @see com.cognizant.opserv.sp.persistence.dao.service.ChangeRequestDAOService#getCRApproversDetails(com.cognizant.opserv.sp.model.cr.ChangeRequest, com.cognizant.opserv.sp.model.auth.UserDetails)
	 */
	@Override
	public List<ChangeRequestApproverDetails> getCRApproversDetails(ChangeRequest changeRequest, UserDetails userDetails) throws OpservDataAccessException {
		try {
			List<ChangeRequestApproverDetails> crApproverDetailList = new ArrayList<ChangeRequestApproverDetails>();
			ChangeRequestApproverDetails changeRequestApproverDetails = new ChangeRequestApproverDetails();
			Short tenantId = userDetails.getTenantId();
			List<TChngReqAppr> tChngReqApprs = changeRequestApproverDAO.fetchCRApproverDetails(changeRequest.getId(), tenantId);
			if (null != tChngReqApprs && !tChngReqApprs.isEmpty()) {
				for (TChngReqAppr tChngReqAppr : tChngReqApprs) {
					changeRequestApproverDetails = changeRequestAssembler.convertTChngApprToChangeReqApprModel(tChngReqAppr, userDetails);
					crApproverDetailList.add(changeRequestApproverDetails);
				}
			}
			return crApproverDetailList;
		} catch (RuntimeException e) {
			LOGGER.error("Error during fetching details of CRApprovers");
			throw new OpservDataAccessException("Error during fetching details of CRApprovers", e);
		}
		
		
		
	}

	/** (non-Javadoc)
	 * @see com.cognizant.opserv.sp.persistence.dao.service.ChangeRequestDAOService#createChangeRequest(com.cognizant.opserv.sp.model.SalesPosition, com.cognizant.opserv.sp.model.SalesPosition, com.cognizant.opserv.sp.model.auth.UserDetails, java.lang.Integer)
	 */
	@SuppressWarnings("unused")
	@Override
	public ChangeRequest createChangeRequest(SalesPosition sourceSalesPostion, SalesPosition targetSalesPostion, UserDetails userDetails, Integer triggerId)
		throws OpservDataAccessException {
		TChngReq tChngReq = null;
		TChngReq chngRequest = null;
		TChngReqConfig tChngReqConfig = null;
		try {
			List<TChngReqConfig> changeRequestConfigList =
				changeRequestConfigDAO.getTChngReqConfig(
					sourceSalesPostion.getAlignmment().getSalesTeam().getId(), sourceSalesPostion.getAlignmment().getId(),
					sourceSalesPostion.getAlignmment().getSalesTeam().getBusinessUnit().getId(), triggerId, userDetails.getTenantId());
			if (changeRequestConfigList != null && !changeRequestConfigList.isEmpty()) {
				if (changeRequestConfigList != null && changeRequestConfigList.size() == 1) {
					tChngReqConfig = changeRequestConfigList.get(0);
					tChngReq = new TChngReq();
					if (null != tChngReqConfig.getChngReqConfigId()) {
						tChngReq.setChngReqConfigId(tChngReqConfig.getChngReqConfigId());
					}
					tChngReq.setChngReqNum((long) 1000000);
					if (null != sourceSalesPostion.getId()) {
						tChngReq.setReqSalesPosId(sourceSalesPostion.getId());
					}
					if (null != sourceSalesPostion.getHierarchy() && null != sourceSalesPostion.getHierarchy().getId()) {
						tChngReq.setReqSalesHierId(sourceSalesPostion.getHierarchy().getId());
					}
					if (null != targetSalesPostion && null != targetSalesPostion.getId()) {
						tChngReq.setRaiseSalesPosId(targetSalesPostion.getId());
					}
					if (null != targetSalesPostion && null != targetSalesPostion.getHierarchy() && null != targetSalesPostion.getHierarchy().getId()) {
						tChngReq.setRaiseSalesHierId(targetSalesPostion.getHierarchy().getId());
					}
					if (null != ChangeRequestStatusType.SUBMISSION_IN_PROGRESS.getId()) {
						tChngReq.setStatusId(ChangeRequestStatusType.SUBMISSION_IN_PROGRESS.getId());
					}
					tChngReq.setActiveFlag(CommonConstants.ACTIVE_Y);
					if (null != userDetails.getUserId()) {
						tChngReq.setCreatedBy(userDetails.getUserId());
						tChngReq.setActionBy(userDetails.getUserId());
						tChngReq.setUpdatedBy(userDetails.getUserId());
					}
					tChngReq.setCreateDt(DateUtil.getCurrentDate());
					tChngReq.setTenantId(userDetails.getTenantId());
					tChngReq.setTriggerId(triggerId);
					tChngReq.setLastSalesPosId(targetSalesPostion.getId());
					tChngReq.setLastSalesHierId(targetSalesPostion.getHierarchy().getId());
					chngRequest = changeRequestDao.createTChngReq(tChngReq);
				}
			}
			ChangeRequest changeRequest = changeRequestAssembler.convertTChngReqToBasicChangeRequestModel(chngRequest);
			return changeRequest;
		}
		catch (RuntimeException e) {
			LOGGER.error("Error during creating ChangeRequest");
			throw new OpservDataAccessException("Error during creating ChangeRequest", e);
		}
	}

	/** (non-Javadoc)
	 * @see com.cognizant.opserv.sp.persistence.dao.service.ChangeRequestDAOService#createMirrorChangeRequest(com.cognizant.opserv.sp.model.cr.ChangeRequest, com.cognizant.opserv.sp.model.SalesPosition, com.cognizant.opserv.sp.model.SalesPosition, com.cognizant.opserv.sp.model.auth.UserDetails, java.lang.Integer)
	 */
	@Override
	public ChangeRequest createMirrorChangeRequest(
		ChangeRequest parentChnageRequest, SalesPosition sourceSalesPostion, SalesPosition targetSalesPostion, UserDetails userDetails, Integer triggerId)
		throws OpservDataAccessException {

		TChngReq tChngReq = null;
		TChngReq chngRequest = null;
		TChngReqConfig tChngReqConfig = null;
		ChangeRequest changeRequest = null;
		//Logic added for Mirror CR when source has mirrors and target has no mirrors
		if (null == targetSalesPostion || null == targetSalesPostion.getId()) {
			targetSalesPostion = sourceSalesPostion;
		}
		try {
			List<TChngReq> changeRequestList =
				changeRequestDao.getChangeRequestByStatus(
					sourceSalesPostion.getId(), sourceSalesPostion.getHierarchy().getId(), targetSalesPostion.getId(), targetSalesPostion.getHierarchy().getId(),
					ChangeRequestStatusType.SUBMISSION_IN_PROGRESS.getId(), triggerId, userDetails.getTenantId());
			if (changeRequestList != null && changeRequestList.size() > 0) {
				tChngReq = changeRequestList.get(0);
				changeRequest =changeRequestAssembler.convertTChngReqToBasicChangeRequestModel(tChngReq);
			}
			else {
				List<TChngReqConfig> changeRequestConfigList =
					changeRequestConfigDAO.getTChngReqConfig(
						sourceSalesPostion.getAlignmment().getSalesTeam().getId(), sourceSalesPostion.getAlignmment().getId(),
						sourceSalesPostion.getAlignmment().getSalesTeam().getBusinessUnit().getId(), triggerId, userDetails.getTenantId());
				if (changeRequestConfigList != null && !changeRequestConfigList.isEmpty()) {
					if (changeRequestConfigList != null && changeRequestConfigList.size() == 1) {
						tChngReqConfig = changeRequestConfigList.get(0);
						tChngReq = new TChngReq();
						if (null != tChngReqConfig.getChngReqConfigId()) {
							tChngReq.setChngReqConfigId(tChngReqConfig.getChngReqConfigId());
						}
						tChngReq.setChngReqNum((long) 1000000);
						if (null != sourceSalesPostion.getId()) {
							tChngReq.setReqSalesPosId(sourceSalesPostion.getId());
						}
						if (null != sourceSalesPostion.getHierarchy() && null != sourceSalesPostion.getHierarchy().getId()) {
							tChngReq.setReqSalesHierId(sourceSalesPostion.getHierarchy().getId());
						}
						if (null != targetSalesPostion && null != targetSalesPostion.getId()) {
							tChngReq.setRaiseSalesPosId(targetSalesPostion.getId());
						}
						if (null != targetSalesPostion && null != targetSalesPostion.getHierarchy() && null != targetSalesPostion.getHierarchy().getId()) {
							tChngReq.setRaiseSalesHierId(targetSalesPostion.getHierarchy().getId());
						}
						if (null != ChangeRequestStatusType.SUBMISSION_IN_PROGRESS.getId()) {
							tChngReq.setStatusId(ChangeRequestStatusType.SUBMISSION_IN_PROGRESS.getId());
						}
						tChngReq.setActiveFlag(CommonConstants.ACTIVE_Y);
						if (null != userDetails.getUserId()) {
							tChngReq.setCreatedBy(userDetails.getUserId());
							tChngReq.setActionBy(userDetails.getUserId());
							tChngReq.setUpdatedBy(userDetails.getUserId());
						}
						tChngReq.setCreateDt(DateUtil.getCurrentDate());
						tChngReq.setTenantId(userDetails.getTenantId());
						tChngReq.setTriggerId(triggerId);
						tChngReq.setLastSalesPosId(targetSalesPostion.getId());
						tChngReq.setLastSalesHierId(targetSalesPostion.getHierarchy().getId());
						if (null != userDetails.getUserId()) {
							tChngReq.setUpdatedBy(userDetails.getUserId());
						}
						tChngReq.setUpdateDt(DateUtil.getCurrentDate());
						TChngReq parentTChngReq = changeRequestDao.findTChngReqById(parentChnageRequest.getId());
						tChngReq.setPrnChngReq(parentTChngReq);
						chngRequest = changeRequestDao.createTChngReq(tChngReq);
					}
				}
				changeRequest = changeRequestAssembler.convertTChngReqToBasicChangeRequestModel(chngRequest);
			}

			return changeRequest;
		}
		catch (RuntimeException e) {
			LOGGER.error("Error during creating mirror ChangeRequest");
			throw new OpservDataAccessException("Error during creating mirror ChangeRequest", e);
		}

	}


	/** (non-Javadoc)
	 * @see com.cognizant.opserv.sp.persistence.dao.service.ChangeRequestDAOService#findChildChangeRequestByParentChangeRequest(com.cognizant.opserv.sp.model.cr.ChangeRequest)
	 */
	@Override
	public List<ChangeRequest> findChildChangeRequestByParentChangeRequest(
			ChangeRequest chngReq) throws OpservDataAccessException {
		try{
			List<TChngReq> childChangeRequestList = changeRequestDao.findTChngReqByParentChangeRequestId(chngReq.getId());
			List<ChangeRequest> changeRequestList = new ArrayList<ChangeRequest>();
			for (TChngReq changReq : childChangeRequestList) {
				ChangeRequest changeReq =
					changeRequestAssembler.convertTChngReqToChangeRequestModel(changReq);
				changeRequestList.add(changeReq);
			}
			return changeRequestList;
		}catch(RuntimeException runtimeException){
			LOGGER.error("Error occurred while finding child CR by parent CR");
			throw new OpservDataAccessException("Error occurred while finding child CR by parent CR",runtimeException);
		}
		
	}
	
	

	/** (non-Javadoc)
	 * @see com.cognizant.opserv.sp.persistence.dao.service.ChangeRequestDAOService#approveChangeRequest(com.cognizant.opserv.sp.model.cr.ChangeRequest, com.cognizant.opserv.sp.model.auth.UserDetails)
	 */
	//@Transactional
	public void approveChangeRequest(ChangeRequest chngReq, UserDetails userDetails, SalesPosition salesPosition) throws OpservDataAccessException {
		try{
			LOGGER.info("Marking CR as dirty and Updating View in approveChangeRequest");
			spAssignmentsViewService.markChangeRequestFlagAsDirty(chngReq.getId(), userDetails);
			TChngReq changeRequest = changeRequestDao.findTChngReqById(chngReq.getId());
			changeRequest.setComments(chngReq.getComments());
			if (null != changeRequest) {
				this.approveChangeRequest(changeRequest, userDetails, salesPosition);
				changeRequestDao.updateTChngReq(changeRequest);
				LOGGER.debug("ChangeRequest Approved Dao Impl..............");
			}
		}catch(RuntimeException runtimeException){
			LOGGER.error("Error occurred while approve change request");
			throw new OpservDataAccessException("Error occurred while approve change request",runtimeException);
		}
	}

	
	

	/** (non-Javadoc)
	 * @see com.cognizant.opserv.sp.persistence.dao.service.ChangeRequestDAOService#withdrawChangeRequest(com.cognizant.opserv.sp.model.cr.ChangeRequest, com.cognizant.opserv.sp.model.auth.UserDetails)
	 */
	//@Transactional
	public void withdrawChangeRequest(ChangeRequest chngReq, UserDetails userDetails) throws OpservDataAccessException {
		try{
			LOGGER.info("Marking CR as dirty and Updating View in withdrawChangeRequest");
			spAssignmentsViewService.markChangeRequestFlagAsDirty(chngReq.getId(), userDetails);
			TChngReq changeRequest = changeRequestDao.findTChngReqById(chngReq.getId());
			withDrawlChangeRequest(changeRequest, userDetails);
			changeRequestDao.updateTChngReq(changeRequest);
			List<TChngReq> childChangeRequestList = changeRequestDao.findTChngReqByParentChangeRequestId(chngReq.getId());
			if (null != childChangeRequestList && !childChangeRequestList.isEmpty() ) {
				for (TChngReq childChangeRequest : childChangeRequestList) {
					withDrawlChangeRequest(childChangeRequest, userDetails);
				}
				changeRequestDao.updateTChngReq(childChangeRequestList);
				
			}
			LOGGER.debug("ChangeRequest Withdrwal Dao Impl..............");
		}catch(RuntimeException runtimeException){
			LOGGER.error("Error occurred while withdraw change request");
			throw new OpservDataAccessException("Error occurred while withdraw change request",runtimeException);
		}
		LOGGER.debug("ChangeRequest Withdrwal Dao Impl..............");
	}

	
	
	/**
	 * @param chngReq
	 * @param userDetails
	 * @throws CallPlanServiceException 
	 */
	private void autoApproveChangeRequest(TChngReq chngReq, UserDetails userDetails, Integer aprStsId, Integer autoaprStsId) throws OpservDataAccessException, CallPlanServiceException {
	try{
		LOGGER.info("Inside ChangeRequestProcessDAOServiceImpl --> autoApproveChangeRequest :: chngReq:"+chngReq.getChngReqId());
		//boolean updateFlag = false;
		Short tenantId = userDetails.getTenantId();
		Date date = DateUtil.getCurrentDate();
		String comments = null;
		if (autoaprStsId.equals(ChangeRequestStatusType.AUTO_APPROVED.getId())) {
			comments = "Auto Approved ";
			LOGGER.info("Approval type: "+comments);
		} else {
			comments = "Approved ";
			LOGGER.info("Approval type: "+comments);
		}
		TSalesPos tsp = tSalesPosDAO.findTSalesPosBySalesPosId(chngReq.getRaiseSalesPosId(), tenantId);
		String cmts = comments.concat("[" + tsp.getPosCd() + " : " + userDetails.getUserName() + " : " + DateUtil.getDefaultFormat(date, Locale.US) + "]");
		LOGGER.info("comments -->"+cmts);
		chngReq.setSubmitDtTm(date);
		chngReq.setUpdatedBy(userDetails.getUserId());
		chngReq.setActionBy(userDetails.getUserId());
		chngReq.setUpdateDt(date);
		chngReq.setActionDtTm(date);
		chngReq.setComments(cmts);
		chngReq.setLastSalesPosId(chngReq.getRaiseSalesPosId());
		chngReq.setLastSalesHierId(chngReq.getRaiseSalesHierId());
		chngReq.setStatusId(aprStsId);
		Map<Long, String> spMap = new HashMap<Long, String>();
		Map<Long, Long> custAlignMap;
		Map<String, Integer> zipAlignMap;
		String spCode = "";
		List<TChngReqAppr> tcrApprlist = new ArrayList<TChngReqAppr>();
		for (TChngReqAppr appr : chngReq.getTChngReqApprss()) {
			if (spMap.containsKey(appr.getApprSalesPosId())) {
				spCode = (String) spMap.get(appr.getApprSalesPosId());
			} else {
				tsp = tSalesPosDAO.findTSalesPosBySalesPosId(appr.getApprSalesPosId(), tenantId);
				spMap.put(appr.getApprSalesPosId(), tsp.getPosCd());
				spCode = tsp.getPosCd();
			}
			cmts = comments.concat("[" + spCode + " : " + userDetails.getUserName() + " : " + DateUtil.getDefaultFormat(date, Locale.US) + "]");
			LOGGER.info("In autoApproveChangeRequest :: crId:"+chngReq.getChngReqId()+" :: userDetails.getUserId():"+userDetails.getUserId());
			appr.setStatusId(autoaprStsId);
			appr.setUpdatedBy(userDetails.getUserId());
			if (autoaprStsId.equals(ChangeRequestStatusType.AUTO_APPROVED.getId())) {
				appr.setActionBy(userDetails.getUserId());
			}
			appr.setUpdateDt(date);
			appr.setActionDtTm(date);
			/*if (null == appr.getComments() || appr.getComments().isEmpty()) {
				appr.setComments(cmts);
			}*/
			tcrApprlist.add(appr);
		}

		/*if (!tcrApprlist.isEmpty()) {
			updateFlag = true;
		} else {
			updateFlag = false;
		}
		if (updateFlag) {*/
			chngReq.setStatusId(aprStsId);
			updateDetailStatus(chngReq, aprStsId, userDetails);
			int dcTrggrId = ChangeRequestTriggerType.UNASSIGN_CUSTOMER.getId();
			int acPullTrggrId = ChangeRequestTriggerType.PULL_CUSTOMER.getId();
			int acPushTrggrId = ChangeRequestTriggerType.PUSH_CUSTOMER.getId();
			int azPushTrggrId = ChangeRequestTriggerType.PUSH_ZIP.getId();
			int ecTrggrId = ChangeRequestTriggerType.EDIT_CUSTOMER.getId();
			if (dcTrggrId == chngReq.getTriggerId()) {

				if (null != chngReq.gettCustAlgmntChngReqDetss()) {
					custAlignMap = new HashMap<Long, Long>();
					for (TCustAlgmntChngReqDet custChngReq : chngReq.gettCustAlgmntChngReqDetss()) {
						if (null != custChngReq.getTCustAlgmnt()
								&& ((custChngReq.getTCustAlgmnt().getEffEndDt().compareTo(DateUtil.getCurrentDate()) < 0) || (custChngReq.getTCustAlgmnt().getEffEndDt()
										.compareTo(DateUtil.getCurrentDate()) == 0))) {
							if (!custAlignMap.containsKey(custChngReq.getTCustAlgmnt().getCustAlgmntId())) {
								custChngReq.getTCustAlgmnt().setActiveFlag(CommonConstants.ACTIVE_N);
								custChngReq.getTCustAlgmnt().setUpdateDt(DateUtil.getCurrentDate());
								custChngReq.getTCustAlgmnt().setUpdatedBy(userDetails.getUserId());
								CustomerAlignment customerAlignment = customerAlignmentModelAssembler.convertTCustAlToModel(custChngReq.getTCustAlgmnt(), false);
								customerAlignmentDAOService.updateCustomerAlignmentChangeRequest(customerAlignment);
								custAlignMap.put(custChngReq.getTCustAlgmnt().getCustAlgmntId(), custChngReq.getTCustAlgmnt().getCustAlgmntId());
							}

						}
					}
				}

			} else if (acPullTrggrId == chngReq.getTriggerId()) {
				if (null != chngReq.gettCustAlgmntChngReqDetss()) {
					custAlignMap = new HashMap<Long, Long>();
					List<TCustAlgmnt> custAlgmntList = new ArrayList<TCustAlgmnt>();
					List<TCustAlgmnt> custAlgmntSourceList = new ArrayList<TCustAlgmnt>();
					List<TCustAlgmnt> custAlgmntTargetList = new ArrayList<TCustAlgmnt>();
					List<TCustAlgmnt> custAlgmntSourceEffectiveEndDateListPerCustomer = new ArrayList<TCustAlgmnt>();
					for (TCustAlgmntChngReqDet custChngReq : chngReq.gettCustAlgmntChngReqDetss()) {
						if (null != custChngReq.getTCustAlgmnt()) {
							if (!custAlignMap.containsKey(custChngReq.getTCustAlgmnt().getCustAlgmntId())) {
								if (null != custChngReq.getAlgmntFlag() && custChngReq.getAlgmntFlag().equals(CommonConstants.SOURCE)) {
									custChngReq.getTCustAlgmnt().setActiveFlag('N');
									custChngReq.getTCustAlgmnt().setStatusId(aprStsId);
									custChngReq.getTCustAlgmnt().setUpdatedBy(userDetails.getUserId());
									custChngReq.getTCustAlgmnt().setUpdateDt(DateUtil.getCurrentDate());
									custAlgmntSourceList.add(custChngReq.getTCustAlgmnt());
									custAlgmntList.add(custChngReq.getTCustAlgmnt());
									List<CustomerCallPlan> customerCallPlan=callPlanOfflineService.getCallPlanByCustAlIdForChngReq(custChngReq.getTCustAlgmnt().getCustAlgmntId(), userDetails);
									if (null != customerCallPlan && !customerCallPlan.isEmpty()) {
										for (CustomerCallPlan custCallPlan : customerCallPlan) {
											callPlanOfflineService.updateCallPlanCRStatusFrChngReqApprove(custCallPlan.getId(),ChangeRequestStatusType.APPROVED.getId(),
															CommonConstants.ACTIVE_N,userDetails);
										}
									}

									List<CustomerProductAlignment> custPrdAlgmntList = customerProductAlignmentDAOService.fetchTCustPrdAlgmnt(custChngReq.getTCustAlgmnt().getCustAlgmntId(),
											userDetails,CommonConstants.ACTIVE_Y);
									if(null != custPrdAlgmntList && !custPrdAlgmntList.isEmpty()){
										for(CustomerProductAlignment customerProductAlignment:custPrdAlgmntList){
										customerProductAlignmentDAOService.updateCustPrdAlgmntForCR(customerProductAlignment.getCustomerAlgmntId(),customerProductAlignment.getProductAlgmntId(),
												CommonConstants.ACTIVE_N, userDetails);
										}
									}
									spAssignmentsViewService.markCustAlgmntFlagAsDirty(custChngReq.getTCustAlgmnt().getSalesPosId(), custChngReq.getTCustAlgmnt().getTCust()
											.getCustId().longValue(), userDetails);
								}
								if (null != custChngReq.getAlgmntFlag() && custChngReq.getAlgmntFlag().equals(CommonConstants.TARGET)) {
									custChngReq.getTCustAlgmnt().setActiveFlag('Y');
									custChngReq.getTCustAlgmnt().setStatusId(aprStsId);
									custChngReq.getTCustAlgmnt().setUpdatedBy(userDetails.getUserId());
									custChngReq.getTCustAlgmnt().setUpdateDt(DateUtil.getCurrentDate());
									custAlgmntTargetList.add(custChngReq.getTCustAlgmnt());
									List<CustomerCallPlan> customerCallPlan=callPlanOfflineService.getCallPlanByCustAlIdForChngReq(custChngReq.getTCustAlgmnt().getCustAlgmntId(), userDetails);
									if (null != customerCallPlan && !customerCallPlan.isEmpty()) {
										for (CustomerCallPlan custCallPlan : customerCallPlan) {
											callPlanOfflineService.updateCallPlanCRStatusFrChngReqApprove(custCallPlan.getId(),ChangeRequestStatusType.APPROVED.getId(),
															CommonConstants.ACTIVE_Y,userDetails);
										}
									}
									
									List<CustomerProductAlignment> custPrdAlgmntList = customerProductAlignmentDAOService.fetchTCustPrdAlgmnt(custChngReq.getTCustAlgmnt().getCustAlgmntId(),
											userDetails,CommonConstants.ACTIVE_N);
									if(null != custPrdAlgmntList && !custPrdAlgmntList.isEmpty()){
										for(CustomerProductAlignment customerProductAlignment:custPrdAlgmntList){
										customerProductAlignmentDAOService.updateCustPrdAlgmntForCR(customerProductAlignment.getCustomerAlgmntId(),customerProductAlignment.getProductAlgmntId(),
												CommonConstants.ACTIVE_Y,userDetails);
										}
									}
									
									custAlgmntList.add(custChngReq.getTCustAlgmnt());
								}
								custAlignMap.put(custChngReq.getTCustAlgmnt().getCustAlgmntId(), custChngReq.getTCustAlgmnt().getCustAlgmntId());
							}

						}
					}
                if (null != custAlgmntList && !custAlgmntList.isEmpty() && custAlgmntList.size() > 0) {
						tCustAlgmntDAO.updateTCustAlgmnts(custAlgmntList);
					}
                sourceEffectiveEndDateLessThanTargetEffectiveStartDate(custAlgmntSourceList, custAlgmntTargetList, custAlgmntSourceEffectiveEndDateListPerCustomer);
				}
			} else if (acPushTrggrId == chngReq.getTriggerId()) {
				if (null != chngReq.gettCustAlgmntChngReqDetss()) {
					custAlignMap = new HashMap<Long, Long>();
					List<TCustAlgmnt> custAlgmntList = new ArrayList<TCustAlgmnt>();
					List<TCustAlgmnt> custAlgmntSourceList = new ArrayList<TCustAlgmnt>();
					List<TCustAlgmnt> custAlgmntTargetList = new ArrayList<TCustAlgmnt>();
					List<TCustAlgmnt> custAlgmntSourceEffectiveEndDateListPerCustomer = new ArrayList<TCustAlgmnt>();
					for (TCustAlgmntChngReqDet custChngReq : chngReq.gettCustAlgmntChngReqDetss()) {
						if (null != custChngReq.getTCustAlgmnt()) {
							if (!custAlignMap.containsKey(custChngReq.getTCustAlgmnt().getCustAlgmntId())) {
								if (null != custChngReq.getAlgmntFlag() && custChngReq.getAlgmntFlag().equals(CommonConstants.SOURCE)) {
									custChngReq.getTCustAlgmnt().setActiveFlag('N');
									custChngReq.getTCustAlgmnt().setStatusId(aprStsId);
									custChngReq.getTCustAlgmnt().setUpdatedBy(userDetails.getUserId());
									custChngReq.getTCustAlgmnt().setUpdateDt(DateUtil.getCurrentDate());
									custAlgmntSourceList.add(custChngReq.getTCustAlgmnt());
									custAlgmntList.add(custChngReq.getTCustAlgmnt());
									List<CustomerCallPlan> customerCallPlan=callPlanOfflineService.getCallPlanByCustAlIdForChngReq(custChngReq.getTCustAlgmnt().getCustAlgmntId(), userDetails);
									if (null != customerCallPlan) {
										for (CustomerCallPlan custCallPlan : customerCallPlan) {
											callPlanOfflineService.updateCallPlanCRStatusFrChngReqApprove(custCallPlan.getId(),ChangeRequestStatusType.APPROVED.getId(),
															CommonConstants.ACTIVE_N,userDetails);
										}
									}
									
									List<CustomerProductAlignment> custPrdAlgmntList = customerProductAlignmentDAOService.fetchTCustPrdAlgmnt(custChngReq.getTCustAlgmnt().getCustAlgmntId(),
											userDetails,CommonConstants.ACTIVE_Y);
									if(null != custPrdAlgmntList && !custPrdAlgmntList.isEmpty()){
										for(CustomerProductAlignment customerProductAlignment:custPrdAlgmntList){
										customerProductAlignmentDAOService.updateCustPrdAlgmntForCR(customerProductAlignment.getCustomerAlgmntId(),customerProductAlignment.getProductAlgmntId(),
												CommonConstants.ACTIVE_N, userDetails);
										}
									}
									spAssignmentsViewService.markCustAlgmntFlagAsDirty(custChngReq.getTCustAlgmnt().getSalesPosId(), custChngReq.getTCustAlgmnt().getTCust()
											.getCustId().longValue(), userDetails);
								}
								if (null != custChngReq.getAlgmntFlag() && custChngReq.getAlgmntFlag().equals(CommonConstants.TARGET)) {
									custChngReq.getTCustAlgmnt().setActiveFlag('Y');
									custChngReq.getTCustAlgmnt().setStatusId(aprStsId);
									custChngReq.getTCustAlgmnt().setUpdatedBy(userDetails.getUserId());
									custChngReq.getTCustAlgmnt().setUpdateDt(DateUtil.getCurrentDate());
									custAlgmntTargetList.add(custChngReq.getTCustAlgmnt());
									List<CustomerCallPlan> customerCallPlan=callPlanOfflineService.getCallPlanByCustAlIdForChngReq(custChngReq.getTCustAlgmnt().getCustAlgmntId(), userDetails);
									if (null != customerCallPlan) {
										for (CustomerCallPlan custCallPlan : customerCallPlan) {
											callPlanOfflineService.updateCallPlanCRStatusFrChngReqApprove(custCallPlan.getId(),ChangeRequestStatusType.APPROVED.getId(),
															CommonConstants.ACTIVE_Y,userDetails);
										}
									}
									
									List<CustomerProductAlignment> custPrdAlgmntList = customerProductAlignmentDAOService.fetchTCustPrdAlgmnt(custChngReq.getTCustAlgmnt().getCustAlgmntId(),
											userDetails,CommonConstants.ACTIVE_N);
									if(null != custPrdAlgmntList && !custPrdAlgmntList.isEmpty()){
										for(CustomerProductAlignment customerProductAlignment:custPrdAlgmntList){
										customerProductAlignmentDAOService.updateCustPrdAlgmntForCR(customerProductAlignment.getCustomerAlgmntId(),customerProductAlignment.getProductAlgmntId(),
												CommonConstants.ACTIVE_Y, userDetails);
										}
									}
									custAlgmntList.add(custChngReq.getTCustAlgmnt());
								}
								custAlignMap.put(custChngReq.getTCustAlgmnt().getCustAlgmntId(), custChngReq.getTCustAlgmnt().getCustAlgmntId());
							}

						}
					}
					if (null != custAlgmntList && !custAlgmntList.isEmpty() && custAlgmntList.size() > 0) {
						tCustAlgmntDAO.updateTCustAlgmnts(custAlgmntList);
					}
					sourceEffectiveEndDateLessThanTargetEffectiveStartDate(custAlgmntSourceList, custAlgmntTargetList, custAlgmntSourceEffectiveEndDateListPerCustomer);
				    
				}
			} else if (azPushTrggrId == chngReq.getTriggerId()) {
				LOGGER.info("Inside azPushTrggrId block");
				if (null != chngReq.gettCustAlgmntChngReqDetss()) {
					custAlignMap = new HashMap<Long, Long>();
					List<TCustAlgmnt> custAlgmntList = new ArrayList<TCustAlgmnt>();
					List<TCustAlgmnt> custAlgmntSourceList = new ArrayList<TCustAlgmnt>();
					List<TCustAlgmnt> custAlgmntTargetList = new ArrayList<TCustAlgmnt>();
					List<TCustAlgmnt> custAlgmntSourceEffectiveEndDateListPerCustomer = new ArrayList<TCustAlgmnt>();
					for (TCustAlgmntChngReqDet custChngReq : chngReq.gettCustAlgmntChngReqDetss()) {
						if (null != custChngReq.getTCustAlgmnt()) {
							if (!custAlignMap.containsKey(custChngReq.getTCustAlgmnt().getCustAlgmntId())) {
								if (null != custChngReq.getAlgmntFlag() && custChngReq.getAlgmntFlag().equals(CommonConstants.SOURCE)) {
									custChngReq.getTCustAlgmnt().setActiveFlag('N');
									custChngReq.getTCustAlgmnt().setStatusId(aprStsId);
									custChngReq.getTCustAlgmnt().setUpdatedBy(userDetails.getUserId());
									custChngReq.getTCustAlgmnt().setUpdateDt(DateUtil.getCurrentDate());
									custAlgmntSourceList.add(custChngReq.getTCustAlgmnt());
									custAlgmntList.add(custChngReq.getTCustAlgmnt());
									List<CustomerCallPlan> customerCallPlan=callPlanOfflineService.getCallPlanByCustAlIdForChngReq(custChngReq.getTCustAlgmnt().getCustAlgmntId(), userDetails);
									if (null != customerCallPlan) {
										for (CustomerCallPlan custCallPlan : customerCallPlan) {
											callPlanOfflineService.updateCallPlanCRStatusFrChngReqApprove(custCallPlan.getId(),ChangeRequestStatusType.APPROVED.getId(),
															CommonConstants.ACTIVE_N,userDetails);
										}
									}
									spAssignmentsViewService.markCustAlgmntFlagAsDirty(custChngReq.getTCustAlgmnt().getSalesPosId(), custChngReq.getTCustAlgmnt().getTCust()
											.getCustId().longValue(), userDetails);
								}
								if (null != custChngReq.getAlgmntFlag() && custChngReq.getAlgmntFlag().equals(CommonConstants.TARGET)) {
									custChngReq.getTCustAlgmnt().setActiveFlag('Y');
									custChngReq.getTCustAlgmnt().setStatusId(aprStsId);
									custChngReq.getTCustAlgmnt().setUpdatedBy(userDetails.getUserId());
									custChngReq.getTCustAlgmnt().setUpdateDt(DateUtil.getCurrentDate());
									custAlgmntTargetList.add(custChngReq.getTCustAlgmnt());
									List<CustomerCallPlan> customerCallPlan=callPlanOfflineService.getCallPlanByCustAlIdForChngReq(custChngReq.getTCustAlgmnt().getCustAlgmntId(), userDetails);
									if (null != customerCallPlan) {
										for (CustomerCallPlan custCallPlan : customerCallPlan) {
											callPlanOfflineService.updateCallPlanCRStatusFrChngReqApprove(custCallPlan.getId(),ChangeRequestStatusType.APPROVED.getId(),
															CommonConstants.ACTIVE_Y,userDetails);
										}
									}
									custAlgmntList.add(custChngReq.getTCustAlgmnt());
								}
								custAlignMap.put(custChngReq.getTCustAlgmnt().getCustAlgmntId(), custChngReq.getTCustAlgmnt().getCustAlgmntId());
							}

						}
					}
					if (null != custAlgmntList && !custAlgmntList.isEmpty() && custAlgmntList.size() > 0) {
						tCustAlgmntDAO.updateTCustAlgmnts(custAlgmntList);
					}
					sourceEffectiveEndDateLessThanTargetEffectiveStartDate(custAlgmntSourceList, custAlgmntTargetList, custAlgmntSourceEffectiveEndDateListPerCustomer);
				}
				if (null != chngReq.gettTerrZipAlgmntChngReqDetss()) {
					zipAlignMap = new HashMap<String, Integer>();
					for (TTerrZipAlgmntChngReqDet zipChangeRequest : chngReq.gettTerrZipAlgmntChngReqDetss()) {
						if (null != zipChangeRequest.getGeoId() && null != zipChangeRequest.getPostalCd() && null != zipChangeRequest.getSalesPosId()
								&& null != zipChangeRequest.getSalesHierId()) {
							String zipChangeRequestMapKey = zipChangeRequest.getGeoId() + zipChangeRequest.getPostalCd() + zipChangeRequest.getSalesPosId()
									+ zipChangeRequest.getSalesHierId();
							if (!zipAlignMap.containsKey(zipChangeRequestMapKey)) {
								
								if(geographyAlignmentProcessDAOService.updateZipAlgmntStatus(zipChangeRequest,aprStsId,userDetails)){
									zipAlignMap.put(zipChangeRequestMapKey, zipChangeRequest.getGeoId());
								}
								
							}
						}
					}
				}
			} else if (ecTrggrId == chngReq.getTriggerId()) {
				if (null != chngReq.gettCustAlgmntChngReqDetss()) {
					custAlignMap = new HashMap<Long, Long>();
					for (TCustAlgmntChngReqDet custChngReq : chngReq.gettCustAlgmntChngReqDetss()) {
						if (null != custChngReq.getTCustAlgmnt()) {
							if (!custAlignMap.containsKey(custChngReq.getTCustAlgmnt().getCustAlgmntId())) {
								custChngReq.getTCustAlgmnt().setStatusId(ChangeRequestStatusType.APPROVED.getId());
								custChngReq.getTCustAlgmnt().setUpdatedBy(userDetails.getUserId());
								custChngReq.getTCustAlgmnt().setUpdateDt(DateUtil.getCurrentDate());
								CustomerAlignment customerAlignment = customerAlignmentModelAssembler.convertTCustAlToModel(custChngReq.getTCustAlgmnt(), false);
								customerAlignmentDAOService.updateCustomerAlignmentChangeRequest(customerAlignment);
								if (null != custChngReq.getAlgmntFlag() && custChngReq.getAlgmntFlag().equals(CommonConstants.SOURCE)) {
									spAssignmentsViewService.markCustAlgmntFlagAsDirty(custChngReq.getTCustAlgmnt().getSalesPosId(), custChngReq.getTCustAlgmnt().getTCust()
											.getCustId().longValue(), userDetails);
								}
								custAlignMap.put(custChngReq.getTCustAlgmnt().getCustAlgmntId(), custChngReq.getTCustAlgmnt().getCustAlgmntId());
							}

						}
					}
				}
			}
	}catch(RuntimeException runtimeException){
			LOGGER.error("Error occurred while auto approve change request");
			throw new OpservDataAccessException("Error occurred while auto approve change request",runtimeException);
		}
		}

		/**
		 * @param custAlgmntSourceList
		 * @param custAlgmntTargetList
		 * @param custAlgmntSourceEffectiveEndDateListPerCustomer
		 */
		private void sourceEffectiveEndDateLessThanTargetEffectiveStartDate(
			List<TCustAlgmnt> custAlgmntSourceList, List<TCustAlgmnt> custAlgmntTargetList, List<TCustAlgmnt> custAlgmntSourceEffectiveEndDateListPerCustomer)throws OpservDataAccessException {
	
			try{
				for (TCustAlgmnt custAlgmntSrc : custAlgmntSourceList) {
					for (TCustAlgmnt custAlgmntTrg : custAlgmntTargetList) {
						if (null != custAlgmntSrc.getTCust() && null != custAlgmntTrg.getTCust() && null != custAlgmntSrc.getTCust().getCustId() && null != custAlgmntTrg.getTCust().getCustId() && custAlgmntSrc.getTCust().getCustId().equals(custAlgmntTrg.getTCust().getCustId())) {
							if (null != custAlgmntSrc.getEffEndDt() && null != custAlgmntTrg.getEffStartDt()) {
								custAlgmntSrc.setEffEndDt(DateUtil.getDateLessThanTargetStartEffDate(custAlgmntTrg.getEffStartDt()));
								custAlgmntSourceEffectiveEndDateListPerCustomer.add(custAlgmntSrc);
							}
						}
					}
				}
				if (null != custAlgmntSourceEffectiveEndDateListPerCustomer && !custAlgmntSourceEffectiveEndDateListPerCustomer.isEmpty() && custAlgmntSourceEffectiveEndDateListPerCustomer.size() > 0) {
					tCustAlgmntDAO.updateTCustAlgmnts(custAlgmntSourceEffectiveEndDateListPerCustomer);
				}
			}catch(RuntimeException re){
				LOGGER.error("Error occurred while validating sourceEffectiveEndDate and TargetEffectiveStartDate");
				throw new OpservDataAccessException("Error occurred while validating sourceEffectiveEndDate and TargetEffectiveStartDate",re);
			}
			
		}

	/**
	 * @param chngReq
	 * @param userDetails
	 */
	private void autoRejectChangeRequest(TChngReq chngReq, UserDetails userDetails,Integer rejctStsId,Integer rejectStsId) throws OpservDataAccessException {
	try{
		//boolean updateFlag = false;
		Short tenantId = userDetails.getTenantId();
		Date date = DateUtil.getCurrentDate();
		String comments = null;
		if (rejectStsId.equals(ChangeRequestStatusType.AUTO_REJECTED.getId())) {
			comments = "Auto Rejected ";
		} else {
			comments = "Rejected ";
		}
		
		TSalesPos tsp = tSalesPosDAO.findTSalesPosBySalesPosId(chngReq.getRaiseSalesPosId(), tenantId);
		String cmts = comments.concat("[" + tsp.getPosCd() + " : " + userDetails.getUserName() + " : " + DateUtil.getDefaultFormat(date, Locale.US) + "]");
		chngReq.setSubmitDtTm(date);
		chngReq.setUpdatedBy(userDetails.getUserId());
		chngReq.setActionBy(userDetails.getUserId());
		chngReq.setUpdateDt(date);
		chngReq.setActionDtTm(date);
		chngReq.setComments(cmts);
		chngReq.setLastSalesPosId(chngReq.getRaiseSalesPosId());
		chngReq.setLastSalesHierId(chngReq.getRaiseSalesHierId());
		chngReq.setStatusId(rejctStsId);
		Map<Long, String> spMap = new HashMap<Long, String>();
		Map<Long, Long> custAlignMap;
		Map<String, Integer> zipAlignMap;
		String spCode = "";
		List<TChngReqAppr> tcrApprlist = new ArrayList<TChngReqAppr>();
		for (TChngReqAppr appr : chngReq.getTChngReqApprss()) {
			if (spMap.containsKey(appr.getApprSalesPosId())) {
				spCode = (String) spMap.get(appr.getApprSalesPosId());
			} else {
				tsp = tSalesPosDAO.findTSalesPosBySalesPosId(appr.getApprSalesPosId(), tenantId);
				spMap.put(appr.getApprSalesPosId(), tsp.getPosCd());
				spCode = tsp.getPosCd();
			}
			cmts = comments.concat("[" + spCode + " : " + userDetails.getUserName() + " : " + DateUtil.getDefaultFormat(date, Locale.US) + "]");

			appr.setStatusId(rejectStsId);
			appr.setUpdatedBy(userDetails.getUserId());
			if (rejectStsId.equals(ChangeRequestStatusType.AUTO_REJECTED.getId())) {
				appr.setActionBy(userDetails.getUserId());
			}
			appr.setUpdateDt(date);
			appr.setActionDtTm(date);
			/*if (null == appr.getComments() || appr.getComments().isEmpty()) {
				appr.setComments(cmts);
			}*/
			tcrApprlist.add(appr);
		}

		/*if (!tcrApprlist.isEmpty()) {
			updateFlag = true;
		} else {
			updateFlag = false;
		}
		if (updateFlag) {*/
			chngReq.setStatusId(rejctStsId);
			updateDetailStatus(chngReq, rejctStsId, userDetails);
			int dcTrggrId = ChangeRequestTriggerType.UNASSIGN_CUSTOMER.getId();
			int acPullTrggrId = ChangeRequestTriggerType.PULL_CUSTOMER.getId();
			int acPushTrggrId = ChangeRequestTriggerType.PUSH_CUSTOMER.getId();
			int azPushTrggrId = ChangeRequestTriggerType.PUSH_ZIP.getId();
			int ecTrggrId =  ChangeRequestTriggerType.EDIT_CUSTOMER.getId();
			if (dcTrggrId == chngReq.getTriggerId()) {

				if (null != chngReq.gettCustAlgmntChngReqDetss()) {
					custAlignMap = new HashMap<Long, Long>();
					for (TCustAlgmntChngReqDet custChngReq : chngReq.gettCustAlgmntChngReqDetss()) {
						if (null != custChngReq.getTCustAlgmnt()
								&& ((custChngReq.getTCustAlgmnt().getEffEndDt().compareTo(DateUtil.getCurrentDate()) < 0) || (custChngReq.getTCustAlgmnt().getEffEndDt()
										.compareTo(DateUtil.getCurrentDate()) == 0))) {
							if (!custAlignMap.containsKey(custChngReq.getTCustAlgmnt().getCustAlgmntId())) {
								custChngReq.getTCustAlgmnt().setActiveFlag(CommonConstants.ACTIVE_N);
								custChngReq.getTCustAlgmnt().setUpdateDt(DateUtil.getCurrentDate());
								custChngReq.getTCustAlgmnt().setUpdatedBy(userDetails.getUserId());
								CustomerAlignment customerAlignment = customerAlignmentModelAssembler.convertTCustAlToModel(custChngReq.getTCustAlgmnt(), false);
								customerAlignmentDAOService.updateCustomerAlignmentChangeRequest(customerAlignment);
								custAlignMap.put(custChngReq.getTCustAlgmnt().getCustAlgmntId(), custChngReq.getTCustAlgmnt().getCustAlgmntId());
							}

						}
					}
				}

			} else if (acPullTrggrId == chngReq.getTriggerId()) {
				if (null != chngReq.gettCustAlgmntChngReqDetss()) {
					custAlignMap = new HashMap<Long, Long>();
					List<TCustAlgmnt> custAlgmntList = new ArrayList<TCustAlgmnt>();
					for (TCustAlgmntChngReqDet custChngReq : chngReq.gettCustAlgmntChngReqDetss()) {
						if (null != custChngReq.getTCustAlgmnt()) {
							if (!custAlignMap.containsKey(custChngReq.getTCustAlgmnt().getCustAlgmntId())) {
								if (null != custChngReq.getAlgmntFlag() && custChngReq.getAlgmntFlag().equals(CommonConstants.SOURCE)) {
									custChngReq.getTCustAlgmnt().setStatusId(ChangeRequestStatusType.APPROVED.getId());
									custAlgmntList.add(custChngReq.getTCustAlgmnt());
									spAssignmentsViewService.markCustAlgmntFlagAsDirty(custChngReq.getTCustAlgmnt().getSalesPosId(), custChngReq.getTCustAlgmnt().getTCust()
											.getCustId().longValue(), userDetails);
								}
								if (null != custChngReq.getAlgmntFlag() && custChngReq.getAlgmntFlag().equals(CommonConstants.TARGET)) {
									custChngReq.getTCustAlgmnt().setStatusId(ChangeRequestStatusType.APPROVED.getId());
									custAlgmntList.add(custChngReq.getTCustAlgmnt());
								}
								custAlignMap.put(custChngReq.getTCustAlgmnt().getCustAlgmntId(), custChngReq.getTCustAlgmnt().getCustAlgmntId());
							}

						}
					}
					if (null != custAlgmntList && !custAlgmntList.isEmpty() && custAlgmntList.size() > 0) {
						tCustAlgmntDAO.updateTCustAlgmnts(custAlgmntList);
					}
				}
			} else if (acPushTrggrId == chngReq.getTriggerId()) {
				if (null != chngReq.gettCustAlgmntChngReqDetss()) {
					custAlignMap = new HashMap<Long, Long>();
					List<TCustAlgmnt> custAlgmntList = new ArrayList<TCustAlgmnt>();
					for (TCustAlgmntChngReqDet custChngReq : chngReq.gettCustAlgmntChngReqDetss()) {
						if (null != custChngReq.getTCustAlgmnt()) {
							if (!custAlignMap.containsKey(custChngReq.getTCustAlgmnt().getCustAlgmntId())) {
								if (null != custChngReq.getAlgmntFlag() && custChngReq.getAlgmntFlag().equals(CommonConstants.SOURCE)) {
									custChngReq.getTCustAlgmnt().setStatusId(ChangeRequestStatusType.APPROVED.getId());
									custAlgmntList.add(custChngReq.getTCustAlgmnt());
									spAssignmentsViewService.markCustAlgmntFlagAsDirty(custChngReq.getTCustAlgmnt().getSalesPosId(), custChngReq.getTCustAlgmnt().getTCust()
											.getCustId().longValue(), userDetails);
								}
								if (null != custChngReq.getAlgmntFlag() && custChngReq.getAlgmntFlag().equals(CommonConstants.TARGET)) {
									custChngReq.getTCustAlgmnt().setStatusId(ChangeRequestStatusType.APPROVED.getId());
									custAlgmntList.add(custChngReq.getTCustAlgmnt());
								}
								custAlignMap.put(custChngReq.getTCustAlgmnt().getCustAlgmntId(), custChngReq.getTCustAlgmnt().getCustAlgmntId());
							}

						}
					}
					if (null != custAlgmntList && !custAlgmntList.isEmpty() && custAlgmntList.size() > 0) {
						tCustAlgmntDAO.updateTCustAlgmnts(custAlgmntList);
					}
				}
			} else if (azPushTrggrId == chngReq.getTriggerId()) {
				if (null != chngReq.gettCustAlgmntChngReqDetss()) {
					custAlignMap = new HashMap<Long, Long>();
					List<TCustAlgmnt> custAlgmntList = new ArrayList<TCustAlgmnt>();
					for (TCustAlgmntChngReqDet custChngReq : chngReq.gettCustAlgmntChngReqDetss()) {
						if (null != custChngReq.getTCustAlgmnt()) {
							if (!custAlignMap.containsKey(custChngReq.getTCustAlgmnt().getCustAlgmntId())) {
								if (null != custChngReq.getAlgmntFlag() && custChngReq.getAlgmntFlag().equals(CommonConstants.SOURCE)) {
									custChngReq.getTCustAlgmnt().setStatusId(ChangeRequestStatusType.APPROVED.getId());
									custAlgmntList.add(custChngReq.getTCustAlgmnt());
									spAssignmentsViewService.markCustAlgmntFlagAsDirty(custChngReq.getTCustAlgmnt().getSalesPosId(), custChngReq.getTCustAlgmnt().getTCust()
											.getCustId().longValue(), userDetails);
								}
								if (null != custChngReq.getAlgmntFlag() && custChngReq.getAlgmntFlag().equals(CommonConstants.TARGET)) {
									custChngReq.getTCustAlgmnt().setStatusId(ChangeRequestStatusType.APPROVED.getId());
									custAlgmntList.add(custChngReq.getTCustAlgmnt());
								}
								custAlignMap.put(custChngReq.getTCustAlgmnt().getCustAlgmntId(), custChngReq.getTCustAlgmnt().getCustAlgmntId());
							}

						}
					}
					if (null != custAlgmntList && !custAlgmntList.isEmpty() && custAlgmntList.size() > 0) {
						tCustAlgmntDAO.updateTCustAlgmnts(custAlgmntList);
					}
				}
				if (null != chngReq.gettTerrZipAlgmntChngReqDetss()) {
					zipAlignMap = new HashMap<String, Integer>();
					for (TTerrZipAlgmntChngReqDet zipChangeRequest : chngReq.gettTerrZipAlgmntChngReqDetss()) {
						if (null != zipChangeRequest.getGeoId() && null != zipChangeRequest.getPostalCd() && null != zipChangeRequest.getSalesPosId()
								&& null != zipChangeRequest.getSalesHierId()) {
							String zipChangeRequestMapKey = zipChangeRequest.getGeoId() + zipChangeRequest.getPostalCd() + zipChangeRequest.getSalesPosId()
									+ zipChangeRequest.getSalesHierId();
							if (!zipAlignMap.containsKey(zipChangeRequestMapKey)) {
								geographyAlignmentProcessDAOService.updateSrcZipAlgmntStatus(zipChangeRequest, ChangeRequestStatusType.APPROVED.getId(),userDetails);
							}
						}
					}
				}
			}
			else if(ecTrggrId == chngReq.getTriggerId())
			{
				if (null != chngReq.gettCustAlgmntChngReqDetss()) {
					custAlignMap = new HashMap<Long, Long>();
					for (TCustAlgmntChngReqDet custChngReq : chngReq.gettCustAlgmntChngReqDetss()) {
						if (null != custChngReq.getTCustAlgmnt()) {
							if (!custAlignMap.containsKey(custChngReq.getTCustAlgmnt().getCustAlgmntId())) {
								custChngReq.getTCustAlgmnt().setStatusId(ChangeRequestStatusType.APPROVED.getId());
								CustomerAlignment customerAlignment = customerAlignmentModelAssembler.convertTCustAlToModel(custChngReq.getTCustAlgmnt(),false);
								customerAlignmentDAOService.updateCustomerAlignmentChangeRequest(customerAlignment);
								if (null != custChngReq.getAlgmntFlag() && custChngReq.getAlgmntFlag().equals(CommonConstants.SOURCE)) {
									spAssignmentsViewService.markCustAlgmntFlagAsDirty(custChngReq.getTCustAlgmnt().getSalesPosId(), custChngReq.getTCustAlgmnt().getTCust()
											.getCustId().longValue(), userDetails);
								}
								custAlignMap.put(custChngReq.getTCustAlgmnt().getCustAlgmntId(), custChngReq.getTCustAlgmnt().getCustAlgmntId());
							}

						}
					}
				}
			}
		//}
	}catch(RuntimeException re){
			LOGGER.error("Error occurred while auto rejecting the Change Request");
			throw new OpservDataAccessException("Error occurred while auto rejecting the Change Request",re);
		}
		
	}
	
	
	/**
	 * @param chngReq
	 * @param userDetails
	 */
	private void approveChangeRequest(TChngReq chngReq, UserDetails userDetails, SalesPosition salesPosition)throws OpservDataAccessException {
		try{
		Date date = DateUtil.getCurrentDate();
		int aprStsId = ChangeRequestStatusType.APPROVED.getId();
		chngReq.setSubmitDtTm(date);
		chngReq.setUpdatedBy(userDetails.getUserId());
		chngReq.setActionBy(userDetails.getUserId());
		chngReq.setUpdateDt(date);
		chngReq.setActionDtTm(date);
		chngReq.setLastSalesPosId(chngReq.getRaiseSalesPosId());
		chngReq.setLastSalesHierId(chngReq.getRaiseSalesHierId());
		List<TChngReqAppr> tcrApprlist = new ArrayList<TChngReqAppr>();
		for (TChngReqAppr appr : chngReq.getTChngReqApprss()) {
			if (appr.getStatusId().equals(ChangeRequestStatusType.PENDING_FOR_APPROVAL.getId()) && appr.getApprSalesPosId().equals(salesPosition.getId())
					&& appr.getApprSalesHierId().equals(salesPosition.getHierarchy().getId())) {
				LOGGER.info("In approveChangeRequest :: crId:"+chngReq.getChngReqId()+" :: userDetails.getUserId():"+userDetails.getUserId());
				appr.setStatusId(aprStsId);
				appr.setUpdatedBy(userDetails.getUserId());
				appr.setActionBy(userDetails.getUserId());
				appr.setComments(chngReq.getComments());
				appr.setUpdateDt(date);
				appr.setActionDtTm(date);
				tcrApprlist.add(appr);
			}
		}
		}catch(RuntimeException re){
			LOGGER.error("Error occurred while approving the Change Request");
			throw new OpservDataAccessException("Error occurred while approving the Change Request",re);
		}
	}

	/**
	 * @param chngReq
	 * @param userDetails
	 */
	private void withDrawlChangeRequest(TChngReq chngReq, UserDetails userDetails) throws OpservDataAccessException{
	try{
		boolean updateFlag = false;
		Date date = DateUtil.getCurrentDate();
		Map<Long, Long> custAlignMap;
		Map<String, Integer> zipAlignMap;
		Integer cnclStsId = ChangeRequestStatusType.CANCELLED.getId();
		chngReq.setSubmitDtTm(date);
		chngReq.setUpdatedBy(userDetails.getUserId());
		chngReq.setActionBy(userDetails.getUserId());
		chngReq.setUpdateDt(date);
		chngReq.setActionDtTm(date);
		chngReq.setStatusId(cnclStsId);
		List<TChngReqAppr> tcrApprlist = new ArrayList<TChngReqAppr>();
		for (TChngReqAppr appr : chngReq.getTChngReqApprss()) {

			appr.setStatusId(cnclStsId);
			appr.setUpdatedBy(userDetails.getUserId());
			appr.setActionBy(userDetails.getUserId());
			appr.setUpdateDt(date);
			appr.setActionDtTm(date);

			tcrApprlist.add(appr);
		}

		if (!tcrApprlist.isEmpty()) {
			updateFlag = true;
		} else {
			updateFlag = false;
		}
		if (updateFlag) {
			updateDetailStatus(chngReq, ChangeRequestStatusType.CANCELLED.getId(), userDetails);
			int acPullTrggrId = ChangeRequestTriggerType.PULL_CUSTOMER.getId();
			int acPushTrggrId = ChangeRequestTriggerType.PUSH_CUSTOMER.getId();
			int azPushTrggrId = ChangeRequestTriggerType.PUSH_ZIP.getId();
			int ecTrggrId = ChangeRequestTriggerType.EDIT_CUSTOMER.getId();
		   if (acPullTrggrId == chngReq.getTriggerId()) {
				if (null != chngReq.gettCustAlgmntChngReqDetss()) {
					custAlignMap = new HashMap<Long, Long>();
					for (TCustAlgmntChngReqDet custChngReq : chngReq.gettCustAlgmntChngReqDetss()) {
						if (null != custChngReq.getTCustAlgmnt()) {
							if (!custAlignMap.containsKey(custChngReq.getTCustAlgmnt().getCustAlgmntId())) {
								custChngReq.getTCustAlgmnt().setStatusId(ChangeRequestStatusType.CANCELLED.getId());
								CustomerAlignment customerAlignment = customerAlignmentModelAssembler.convertTCustAlToModel(custChngReq.getTCustAlgmnt(),false);
								customerAlignmentDAOService.updateCustomerAlignmentChangeRequest(customerAlignment);
								if (null != custChngReq.getAlgmntFlag() && custChngReq.getAlgmntFlag().equals(CommonConstants.SOURCE)) {
									spAssignmentsViewService.markCustAlgmntFlagAsDirty(custChngReq.getTCustAlgmnt().getSalesPosId(), custChngReq.getTCustAlgmnt().getTCust()
											.getCustId().longValue(), userDetails);
								}
								custAlignMap.put(custChngReq.getTCustAlgmnt().getCustAlgmntId(), custChngReq.getTCustAlgmnt().getCustAlgmntId());
							}

						}
					}
				}
			} else if (acPushTrggrId == chngReq.getTriggerId()) {
				if (null != chngReq.gettCustAlgmntChngReqDetss()) {
					custAlignMap = new HashMap<Long, Long>();
					for (TCustAlgmntChngReqDet custChngReq : chngReq.gettCustAlgmntChngReqDetss()) {
						if (null != custChngReq.getTCustAlgmnt()) {
							if (!custAlignMap.containsKey(custChngReq.getTCustAlgmnt().getCustAlgmntId())) {
								custChngReq.getTCustAlgmnt().setStatusId(ChangeRequestStatusType.CANCELLED.getId());
								CustomerAlignment customerAlignment = customerAlignmentModelAssembler.convertTCustAlToModel(custChngReq.getTCustAlgmnt(),false);
								customerAlignmentDAOService.updateCustomerAlignmentChangeRequest(customerAlignment);
								if (null != custChngReq.getAlgmntFlag() && custChngReq.getAlgmntFlag().equals(CommonConstants.SOURCE)) {
									spAssignmentsViewService.markCustAlgmntFlagAsDirty(custChngReq.getTCustAlgmnt().getSalesPosId(), custChngReq.getTCustAlgmnt().getTCust()
											.getCustId().longValue(), userDetails);
								}
								custAlignMap.put(custChngReq.getTCustAlgmnt().getCustAlgmntId(), custChngReq.getTCustAlgmnt().getCustAlgmntId());
							}

						}
					}
				}
			}
			else if (azPushTrggrId == chngReq.getTriggerId()) {
				if (null != chngReq.gettCustAlgmntChngReqDetss()) {
					custAlignMap = new HashMap<Long, Long>();
					for (TCustAlgmntChngReqDet custChngReq : chngReq.gettCustAlgmntChngReqDetss()) {
						if (null != custChngReq.getTCustAlgmnt()) {
							if (!custAlignMap.containsKey(custChngReq.getTCustAlgmnt().getCustAlgmntId())) {
								custChngReq.getTCustAlgmnt().setStatusId(ChangeRequestStatusType.CANCELLED.getId());
								CustomerAlignment customerAlignment = customerAlignmentModelAssembler.convertTCustAlToModel(custChngReq.getTCustAlgmnt(),false);
								customerAlignmentDAOService.updateCustomerAlignmentChangeRequest(customerAlignment);
								if (null != custChngReq.getAlgmntFlag() && custChngReq.getAlgmntFlag().equals(CommonConstants.SOURCE)) {
									spAssignmentsViewService.markCustAlgmntFlagAsDirty(custChngReq.getTCustAlgmnt().getSalesPosId(), custChngReq.getTCustAlgmnt().getTCust()
											.getCustId().longValue(), userDetails);
								}
								custAlignMap.put(custChngReq.getTCustAlgmnt().getCustAlgmntId(), custChngReq.getTCustAlgmnt().getCustAlgmntId());
							}

						}
					}
				}
				if (null != chngReq.gettTerrZipAlgmntChngReqDetss()) {
					zipAlignMap = new HashMap<String, Integer>();
					for (TTerrZipAlgmntChngReqDet zipChangeRequest : chngReq.gettTerrZipAlgmntChngReqDetss()) {
						if (null != zipChangeRequest.getGeoId() && null !=zipChangeRequest.getPostalCd() && null != zipChangeRequest.getSalesPosId() && null != zipChangeRequest.getSalesHierId()) {
							String zipChangeRequestMapKey = zipChangeRequest.getGeoId()+zipChangeRequest.getPostalCd()+zipChangeRequest.getSalesPosId()+zipChangeRequest.getSalesHierId();
							if (!zipAlignMap.containsKey(zipChangeRequestMapKey)) {
								List<TTerrZipAlgmnt> tTerrZipAlgmntList = tTerrZipAlgmntDAO.fetchTTerrZipAlgmntByChangeRequest(zipChangeRequest.getGeoId(),
										zipChangeRequest.getPostalCd(), zipChangeRequest.getSalesPosId(), zipChangeRequest.getSalesHierId(), zipChangeRequest.getTenantId());
								if (null != tTerrZipAlgmntList && tTerrZipAlgmntList.size() > 0) {
									geographyAlignmentProcessDAOService.updateSrcZipAlgmntStatus(zipChangeRequest, ChangeRequestStatusType.CANCELLED.getId(),userDetails);
								}
							}
						}
					}
				
				
				}
			}
			else if(ecTrggrId == chngReq.getTriggerId())
			{
				if (null != chngReq.gettCustAlgmntChngReqDetss()) {
					custAlignMap = new HashMap<Long, Long>();
					for (TCustAlgmntChngReqDet custChngReq : chngReq.gettCustAlgmntChngReqDetss()) {
						if (null != custChngReq.getTCustAlgmnt()) {
							if (!custAlignMap.containsKey(custChngReq.getTCustAlgmnt().getCustAlgmntId())) {
								custChngReq.getTCustAlgmnt().setStatusId(ChangeRequestStatusType.CANCELLED.getId());
								CustomerAlignment customerAlignment = customerAlignmentModelAssembler.convertTCustAlToModel(custChngReq.getTCustAlgmnt(),false);
								customerAlignmentDAOService.updateCustomerAlignmentChangeRequest(customerAlignment);
								if (null != custChngReq.getAlgmntFlag() && custChngReq.getAlgmntFlag().equals(CommonConstants.SOURCE)) {
									spAssignmentsViewService.markCustAlgmntFlagAsDirty(custChngReq.getTCustAlgmnt().getSalesPosId(), custChngReq.getTCustAlgmnt().getTCust()
											.getCustId().longValue(), userDetails);
								}
								custAlignMap.put(custChngReq.getTCustAlgmnt().getCustAlgmntId(), custChngReq.getTCustAlgmnt().getCustAlgmntId());
							}

						}
					}
				}
			}
		}
	}catch(RuntimeException re){
			LOGGER.error("Error occurred while withdraw the Change Request");
			throw new OpservDataAccessException("Error occurred while withdraw the Change Request",re);
		}
	}
	
	
	
	/**
	 * @param chngReq
	 * @param userDetails
	 */
	private void rejectChangeRequest(TChngReq chngReq, UserDetails userDetails, SalesPosition salesPosition)throws OpservDataAccessException {
		try{
			Date date = DateUtil.getCurrentDate();
			Integer cnclStsId = ChangeRequestStatusType.REJECTED.getId();
			chngReq.setSubmitDtTm(date);
			chngReq.setUpdatedBy(userDetails.getUserId());
			chngReq.setActionBy(userDetails.getUserId());
			chngReq.setUpdateDt(date);
			chngReq.setActionDtTm(date);
			List<TChngReqAppr> tcrApprlist = new ArrayList<TChngReqAppr>();
			for (TChngReqAppr appr : chngReq.getTChngReqApprss()) {

				if (appr.getStatusId().equals(ChangeRequestStatusType.PENDING_FOR_APPROVAL.getId()) && appr.getApprSalesPosId().equals(salesPosition.getId())
						&& appr.getApprSalesHierId().equals(salesPosition.getHierarchy().getId())) {
					appr.setStatusId(cnclStsId);
					appr.setUpdatedBy(userDetails.getUserId());
					appr.setActionBy(userDetails.getUserId());
					appr.setUpdateDt(date);
					appr.setComments(chngReq.getComments());
					appr.setActionDtTm(date);
					tcrApprlist.add(appr);
				}
			}
		}catch(RuntimeException re){
			LOGGER.error("Error occurred while rejecting the Change Request");
			throw new OpservDataAccessException("Error occurred while rejecting the Change Request",re);
		}
	}

	/**
	 * @param chngReq
	 * @param status
	 * @param userDetails
	 * @throws OpservDataAccessException
	 */
	private void updateDetailStatus(TChngReq chngReq, Integer status, UserDetails userDetails) throws OpservDataAccessException {
		try{
			List<TCustAlgmntChngReqDet> custList;
			List<TPrdAlgmntChngReqDet> prdList;
			List<TSalesPosChngReqDet> salesPosList;
			List<TTerrZipAlgmntChngReqDet> zipList;
			List<TCustCallPlanChngReqDet> callPlanList;
			List<TCustAffChngReqDet> custAffList;
			Date date = DateUtil.getCurrentDate();
			Integer feature = chngReq.getTriggerId();

			switch (feature) {
			case 1:// ADD CUSTOMER
				custList = new ArrayList<TCustAlgmntChngReqDet>();
				if (null != chngReq.gettCustAlgmntChngReqDetss()) {
					for (TCustAlgmntChngReqDet custChngReq : chngReq.gettCustAlgmntChngReqDetss()) {
						custChngReq.setUpdatedBy(userDetails.getUserId());
						custChngReq.setUpdateDt(date);
						custChngReq.setStatusId(status);
						custList.add(custChngReq);

				}
				
			}

			break;
		case 2:// EDIT CUSTOMER
			custList = new ArrayList<TCustAlgmntChngReqDet>();
			if (null != chngReq.gettCustAlgmntChngReqDetss()) {
				for (TCustAlgmntChngReqDet custChngReq : chngReq.gettCustAlgmntChngReqDetss()) {
					custChngReq.setUpdatedBy(userDetails.getUserId());
					custChngReq.setUpdateDt(date);
					custChngReq.setStatusId(status);
					custList.add(custChngReq);

				}
				
			}

			break;
		case 3:// REMOVE CUSTOMER
			custList = new ArrayList<TCustAlgmntChngReqDet>();
			if (null != chngReq.gettCustAlgmntChngReqDetss()) {
				for (TCustAlgmntChngReqDet custChngReq : chngReq.gettCustAlgmntChngReqDetss()) {
					custChngReq.setUpdatedBy(userDetails.getUserId());
					custChngReq.setUpdateDt(date);
					custChngReq.setStatusId(status);
					custList.add(custChngReq);

				}
				
			}

			break;
		case 4:// ADD PRODUCT
			prdList = new ArrayList<TPrdAlgmntChngReqDet>();
			if (null != chngReq.gettPrdAlgmntChngReqDetss()) {
				for (TPrdAlgmntChngReqDet prdChngReq : chngReq.gettPrdAlgmntChngReqDetss()) {
					prdChngReq.setUpdatedBy(userDetails.getUserId());
					prdChngReq.setUpdateDt(date);
					prdChngReq.setStatusId(status);
					prdList.add(prdChngReq);
				}
			}

			break;
		case 5:// EDIT PRODUCT
			prdList = new ArrayList<TPrdAlgmntChngReqDet>();
			if (null != chngReq.gettPrdAlgmntChngReqDetss()) {
				for (TPrdAlgmntChngReqDet prdChngReq : chngReq.gettPrdAlgmntChngReqDetss()) {
					prdChngReq.setUpdatedBy(userDetails.getUserId());
					prdChngReq.setUpdateDt(date);
					prdChngReq.setStatusId(status);
					prdList.add(prdChngReq);
				}
			}

			break;
		case 6:// REMOVE PRODUCT
			prdList = new ArrayList<TPrdAlgmntChngReqDet>();
			if (null != chngReq.gettPrdAlgmntChngReqDetss()) {
				for (TPrdAlgmntChngReqDet prdChngReq : chngReq.gettPrdAlgmntChngReqDetss()) {
					prdChngReq.setUpdatedBy(userDetails.getUserId());
					prdChngReq.setUpdateDt(date);
					prdChngReq.setStatusId(status);
					prdList.add(prdChngReq);
				}
			}

			break;
		case 7:// EDIT CALL PLAN
			callPlanList = new ArrayList<TCustCallPlanChngReqDet>();
			if (null != chngReq.gettCustCallPlanChngReqDetss()) {
				for (TCustCallPlanChngReqDet callPlanChngReq : chngReq.gettCustCallPlanChngReqDetss()) {
					callPlanChngReq.setUpdatedBy(userDetails.getUserId());
					callPlanChngReq.setUpdateDt(date);
					callPlanChngReq.setStatusId(status);
					callPlanList.add(callPlanChngReq);
				}
			}

			break;
		case 8:// ADD AFFILATION
			custAffList = new ArrayList<TCustAffChngReqDet>();
			if (null != chngReq.gettCustAffChngReqDetss()) {
				for (TCustAffChngReqDet custAffChngReq : chngReq.gettCustAffChngReqDetss()) {
					custAffChngReq.setUpdatedBy(userDetails.getUserId());
					custAffChngReq.setUpdateDt(date);
					custAffChngReq.setStatusId(status);
					custAffList.add(custAffChngReq);
				}
			}

			custList = new ArrayList<TCustAlgmntChngReqDet>();
			if (null != chngReq.gettCustAlgmntChngReqDetss()) {
				for (TCustAlgmntChngReqDet custChngReq : chngReq.gettCustAlgmntChngReqDetss()) {
					custChngReq.setUpdatedBy(userDetails.getUserId());
					custChngReq.setUpdateDt(date);
					custChngReq.setStatusId(status);
					custList.add(custChngReq);
				}
			}

			break;
		case 9:// EDIT AFFILATION
			custAffList = new ArrayList<TCustAffChngReqDet>();
			if (null != chngReq.gettCustAffChngReqDetss()) {
				for (TCustAffChngReqDet custAffChngReq : chngReq.gettCustAffChngReqDetss()) {
					custAffChngReq.setUpdatedBy(userDetails.getUserId());
					custAffChngReq.setUpdateDt(date);
					custAffChngReq.setStatusId(status);
					custAffList.add(custAffChngReq);
				}
			}

			custList = new ArrayList<TCustAlgmntChngReqDet>();
			if (null != chngReq.gettCustAlgmntChngReqDetss()) {
				for (TCustAlgmntChngReqDet custChngReq : chngReq.gettCustAlgmntChngReqDetss()) {
					custChngReq.setUpdatedBy(userDetails.getUserId());
					custChngReq.setUpdateDt(date);
					custChngReq.setStatusId(status);
					custList.add(custChngReq);
				}
			}

			break;
		case 10:// ADD ZIP
			zipList = new ArrayList<TTerrZipAlgmntChngReqDet>();
			if (null != chngReq.gettTerrZipAlgmntChngReqDetss()) {
				for (TTerrZipAlgmntChngReqDet zipChngReq : chngReq.gettTerrZipAlgmntChngReqDetss()) {
					zipChngReq.setUpdatedBy(userDetails.getUserId());
					zipChngReq.setUpdateDt(date);
					zipChngReq.setStatusId(status);
					zipList.add(zipChngReq);
				}
			}
			
			custList = new ArrayList<TCustAlgmntChngReqDet>();
			if (null != chngReq.gettCustAlgmntChngReqDetss()) {
				for (TCustAlgmntChngReqDet custChngReq : chngReq.gettCustAlgmntChngReqDetss()) {
					custChngReq.setUpdatedBy(userDetails.getUserId());
					custChngReq.setUpdateDt(date);
					custChngReq.setStatusId(status);
					custList.add(custChngReq);
				}
			}

			break;
		
	   case 11:// ADD SALES POSITION
			salesPosList = new ArrayList<TSalesPosChngReqDet>();
			if (null != chngReq.gettSalesPosChngReqDetss()) {
				for (TSalesPosChngReqDet salesPosChngReq : chngReq.gettSalesPosChngReqDetss()) {
					salesPosChngReq.setUpdatedBy(userDetails.getUserId());
					salesPosChngReq.setUpdateDt(date);
					salesPosChngReq.setStatusId(status);
					salesPosList.add(salesPosChngReq);
				}
			}

			break;
		case 12:// EDIT SALES POSITION
			salesPosList = new ArrayList<TSalesPosChngReqDet>();
			if (null != chngReq.gettSalesPosChngReqDetss()) {
				for (TSalesPosChngReqDet salesPosChngReq : chngReq.gettSalesPosChngReqDetss()) {
					salesPosChngReq.setUpdatedBy(userDetails.getUserId());
					salesPosChngReq.setUpdateDt(date);
					salesPosChngReq.setStatusId(status);
					salesPosList.add(salesPosChngReq);
				}
			}

			break;
		case 13:// REMOVE SALES POSITION
			salesPosList = new ArrayList<TSalesPosChngReqDet>();
			if (null != chngReq.gettSalesPosChngReqDetss()) {
				for (TSalesPosChngReqDet salesPosChngReq : chngReq.gettSalesPosChngReqDetss()) {
					salesPosChngReq.setUpdatedBy(userDetails.getUserId());
					salesPosChngReq.setUpdateDt(date);
					salesPosChngReq.setStatusId(status);
					salesPosList.add(salesPosChngReq);
				}
			}

			break;
		case 14:// MOVE REPORTING
			salesPosList = new ArrayList<TSalesPosChngReqDet>();
			if (null != chngReq.gettSalesPosChngReqDetss()) {
				for (TSalesPosChngReqDet salesPosChngReq : chngReq.gettSalesPosChngReqDetss()) {
					salesPosChngReq.setUpdatedBy(userDetails.getUserId());
					salesPosChngReq.setUpdateDt(date);
					salesPosChngReq.setStatusId(status);
					salesPosList.add(salesPosChngReq);
				}
			}

			break;
		case 15:// REMOVE ZIP
			zipList = new ArrayList<TTerrZipAlgmntChngReqDet>();
			if (null != chngReq.gettTerrZipAlgmntChngReqDetss()) {
				for (TTerrZipAlgmntChngReqDet zipChngReq : chngReq.gettTerrZipAlgmntChngReqDetss()) {
					zipChngReq.setUpdatedBy(userDetails.getUserId());
					zipChngReq.setUpdateDt(date);
					zipChngReq.setStatusId(status);
					zipList.add(zipChngReq);
				}
			}
			
			custList = new ArrayList<TCustAlgmntChngReqDet>();
			if (null != chngReq.gettCustAlgmntChngReqDetss()) {
				for (TCustAlgmntChngReqDet custChngReq : chngReq.gettCustAlgmntChngReqDetss()) {
					custChngReq.setUpdatedBy(userDetails.getUserId());
					custChngReq.setUpdateDt(date);
					custChngReq.setStatusId(status);
					custList.add(custChngReq);
				}
			}

			break;
		
		case 16:// PUSH CUSTOMER
			custList = new ArrayList<TCustAlgmntChngReqDet>();
			if (null != chngReq.gettCustAlgmntChngReqDetss()) {
				for (TCustAlgmntChngReqDet custChngReq : chngReq.gettCustAlgmntChngReqDetss()) {
					custChngReq.setUpdatedBy(userDetails.getUserId());
					custChngReq.setUpdateDt(date);
					custChngReq.setStatusId(status);
					custList.add(custChngReq);

				}
				
			}

			break;
		case 17:// PULL CUSTOMER
			custList = new ArrayList<TCustAlgmntChngReqDet>();
			if (null != chngReq.gettCustAlgmntChngReqDetss()) {
				for (TCustAlgmntChngReqDet custChngReq : chngReq.gettCustAlgmntChngReqDetss()) {
					custChngReq.setUpdatedBy(userDetails.getUserId());
					custChngReq.setUpdateDt(date);
					custChngReq.setStatusId(status);
					custList.add(custChngReq);

				}
				
			}

			break;
		case 18:// ADD PUSH ZIP
			zipList = new ArrayList<TTerrZipAlgmntChngReqDet>();
			if (null != chngReq.gettTerrZipAlgmntChngReqDetss()) {
				for (TTerrZipAlgmntChngReqDet zipChngReq : chngReq.gettTerrZipAlgmntChngReqDetss()) {
					zipChngReq.setUpdatedBy(userDetails.getUserId());
					zipChngReq.setUpdateDt(date);
					zipChngReq.setStatusId(status);
					zipList.add(zipChngReq);
				}
			}
			
			custList = new ArrayList<TCustAlgmntChngReqDet>();
			if (null != chngReq.gettCustAlgmntChngReqDetss()) {
				for (TCustAlgmntChngReqDet custChngReq : chngReq.gettCustAlgmntChngReqDetss()) {
					custChngReq.setUpdatedBy(userDetails.getUserId());
					custChngReq.setUpdateDt(date);
					custChngReq.setStatusId(status);
					custList.add(custChngReq);
				}
			}

			break;
		case 19:// ADD PULL ZIP
			zipList = new ArrayList<TTerrZipAlgmntChngReqDet>();
			if (null != chngReq.gettTerrZipAlgmntChngReqDetss()) {
				for (TTerrZipAlgmntChngReqDet zipChngReq : chngReq.gettTerrZipAlgmntChngReqDetss()) {
					zipChngReq.setUpdatedBy(userDetails.getUserId());
					zipChngReq.setUpdateDt(date);
					zipChngReq.setStatusId(status);
					zipList.add(zipChngReq);
				}
			}
			
			custList = new ArrayList<TCustAlgmntChngReqDet>();
			if (null != chngReq.gettCustAlgmntChngReqDetss()) {
				for (TCustAlgmntChngReqDet custChngReq : chngReq.gettCustAlgmntChngReqDetss()) {
					custChngReq.setUpdatedBy(userDetails.getUserId());
					custChngReq.setUpdateDt(date);
					custChngReq.setStatusId(status);
					custList.add(custChngReq);
				}
			}

			break;
	   
		default:
			break;

		}
	}catch(RuntimeException re){
			LOGGER.error("Error occurred while updating Change Request details status");
			throw new OpservDataAccessException("Error occurred while updating Change Request details status",re);
		}
	}

	
	
	/** (non-Javadoc)
	 * @see com.cognizant.opserv.sp.persistence.dao.service.ChangeRequestDAOService#getCustomerAlignmentChangeRequestDetails(com.cognizant.opserv.sp.model.cr.ChangeRequest, com.cognizant.opserv.sp.model.auth.UserDetails)
	 */
	public List<CustomerAlignmentChangeRequestDetails> getCustomerAlignmentChangeRequestDetails(ChangeRequest chngReq, UserDetails userDetails)
			throws OpservDataAccessException {
		try{
			List<CustomerAlignmentChangeRequestDetails> customerAlignmentList;
			TChngReq changeRequest = changeRequestDao.findTChngReqById(chngReq.getId());
			customerAlignmentList = changeRequestAssembler.convertTCustAlignChngReqToCustomerAlignmentChangeRequestModel(changeRequest);
			return customerAlignmentList;
		}catch(RuntimeException re){
			LOGGER.error("Error occurred while fetching customer alignment cr details");
			throw new OpservDataAccessException("Error occurred while fetching customer alignment cr details",re);
		}
	}

	/**
	 * @param chngReq
	 * @param userDetails
	 * @return List
	 */
	public List<Long> findCustomerAlignmentSalesPostionIdByChangeRequestId(ChangeRequest chngReq, UserDetails userDetails)throws OpservDataAccessException {
		
		try{
			List<Long> salesPostionIds = new ArrayList<Long>();
			List<Object[]> salesPostions = changeRequestDao.findCustomerAlignmentSalesPostionIdByChangeRequestId(chngReq.getId(), userDetails.getTenantId());
			for (Object salesPos : salesPostions) {
				Long salesPostionId = (Long) salesPos;
				salesPostionIds.add(salesPostionId);
			}
			return salesPostionIds;
		}catch(RuntimeException re){
			LOGGER.error("Error occurred while fetching customer alignment spId by CR id");
			throw new OpservDataAccessException("Error occurred while fetching customer alignment spId by CR id",re);
		}
	}
	
	
	/** (non-Javadoc)
	 * @see com.cognizant.opserv.sp.persistence.dao.service.ChangeRequestDAOService#findZipAlignmentSalesPostionIdByChangeRequestId(com.cognizant.opserv.sp.model.cr.ChangeRequest, com.cognizant.opserv.sp.model.auth.UserDetails)
	 */
	public List<Long> findZipAlignmentSalesPostionIdByChangeRequestId(ChangeRequest chngReq, UserDetails userDetails)throws OpservDataAccessException {
		try{
			List<Long> salesPostionIds = new ArrayList<Long>();
			List<Object[]> salesPostions = changeRequestDao.findZipAlignmentSalesPostionIdByChangeRequestId(chngReq.getId(), userDetails.getTenantId());
			for (Object salesPos : salesPostions) {
				Long salesPostionId = (Long) salesPos;
				salesPostionIds.add(salesPostionId);
			}
			return salesPostionIds;
		}catch(RuntimeException re){
			LOGGER.error("Error occurred while fetching zip alignment spId by CR id");
			throw new OpservDataAccessException("Error occurred while fetching zip alignment spId by CR id",re);
		}
	}


    //@Transactional
	public void rejectChangeRequest(ChangeRequest chngReq, UserDetails userDetails, SalesPosition salesPosition) throws OpservDataAccessException {
		try{
			LOGGER.info("Marking CR as dirty and Updating View in approveChangeRequest");
			spAssignmentsViewService.markChangeRequestFlagAsDirty(chngReq.getId(), userDetails);
			TChngReq changeRequest = changeRequestDao.findTChngReqById(chngReq.getId());
			changeRequest.setComments(chngReq.getComments());
			if (null != changeRequest) {
				this.rejectChangeRequest(changeRequest, userDetails, salesPosition);
				changeRequestDao.updateTChngReq(changeRequest);
				LOGGER.debug("ChangeRequest Reject Dao Impl..............");
			}
		}catch(RuntimeException re){
			LOGGER.error("Error occurred while rejecting CR");
			throw new OpservDataAccessException("Error occurred while rejecting CR",re);
		}
	}


    /**
	 * To get CustomerAlignmentChangeRequestDetails By Parent CRID
	 * @param chngReq
	 * @return List<CustomerAlignmentChangeRequestDetails>
	 * @throws OpservDataAccessException
	 */
   // @Transactional
    public List<CustomerAlignmentChangeRequestDetails> getCustAlgnCRDetailsByPrntCRId(ChangeRequest chngReq)
			throws OpservDataAccessException {
    	try{
    		List<CustomerAlignmentChangeRequestDetails> customerAlignmentList = null;
    		List<TChngReq> changeRequest = changeRequestDao.findTChngReqByParentChangeRequestId(chngReq.getId());
    		for(TChngReq tChngReq:changeRequest){
    			customerAlignmentList = changeRequestAssembler.convertTCustAlignChngReqToCustomerAlignmentChangeRequestModel(tChngReq);
    		}
    		return customerAlignmentList;
    	}catch (RuntimeException dae) {
    		LOGGER.error("Exception while fetching Customer Alignment Change Request Details By Change request ID");
			throw new OpservDataAccessException(
					"Exception while fetching Customer Alignment Change Request Details By Change request ID",
					dae);
		}
		
	}


	public List<Long> findCustomerIdByChangeRequestId(ChangeRequest chngReq, UserDetails userDetails)throws OpservDataAccessException {
		try{
			List<Long> custIds = new ArrayList<Long>();
			List<Object[]> custIdsList = changeRequestDao.findCustomerIdByChangeRequestId(chngReq.getId(), userDetails.getTenantId());
			for (Object custId : custIdsList) {
				Integer customerId = (Integer) custId;
				custIds.add(customerId.longValue());
			}
			return custIds;
		}catch(RuntimeException re){
			LOGGER.error("Error occurred while getting customerId by CRid");
			throw new OpservDataAccessException("Error occurred while getting customerId by CRid",re);
		}
		
	}


	@Override
	public TChngReq getChangeRequestByChangeRequest(ChangeRequest chngReq) throws OpservDataAccessException {
		try{
			return changeRequestDao.findTChngReqById(chngReq.getId());
		}catch(RuntimeException re){
			LOGGER.error("Error occurred while getting change request by CRid");
			throw new OpservDataAccessException("Error occurred while getting change request by CRid",re);
		}
	}


	
	
	/** (non-Javadoc)
	 * @see com.cognizant.opserv.sp.cr.process.internal.persistence.dao.service.ChangeRequestProcessDAOService#fetchTChngReqApprsTypeByStatus(com.cognizant.opserv.sp.model.cr.ChangeRequest, com.cognizant.opserv.sp.model.auth.UserDetails)
	 */
	@Override
	public Long fetchTChngReqApprsTypeByStatus(ChangeRequest chngReq, UserDetails userDetails) throws OpservDataAccessException
	{
		try{
			return changeRequestApproverDAO.fetchTChngReqApprsTypeByStatus(chngReq.getId(), chngReq.getStatus().getId(), userDetails.getTenantId());
		}catch(RuntimeException re){
			LOGGER.error("Error occurred while getting changeRequestApprovers by CRid and status");
			throw new OpservDataAccessException("Error occurred while getting changeRequestApprovers by CRid and status",re);
		}
		
	}
	
	
	
	@Override
	//@Transactional
	public void autoApproveForBaseAndMirrorChangeRequests(ChangeRequest changeRequest, UserDetails userDetails, Integer aprStsId, Integer autoAprStsId)
			throws OpservDataAccessException, CallPlanServiceException {
		try{
			TChngReq chngReq = changeRequestDao.findTChngReqById(changeRequest.getId());
			autoApproveChangeRequest(chngReq, userDetails, aprStsId, autoAprStsId);
			changeRequestDao.updateTChngReq(chngReq);
			List<TChngReq> childChangeRequestList = changeRequestDao.findTChngReqByParentChangeRequestId(changeRequest.getId());
			if (null != childChangeRequestList && !childChangeRequestList.isEmpty()) {
				for (TChngReq childChangeRequest : childChangeRequestList) {
					autoApproveChangeRequest(childChangeRequest, userDetails, aprStsId, autoAprStsId);
				}
				changeRequestDao.updateTChngReq(childChangeRequestList);
			}
		}catch(RuntimeException re){
			LOGGER.error("Error occurred while getting changeRequestApprovers by CRid and status");
			throw new OpservDataAccessException("Error occurred while getting changeRequestApprovers by CRid and status",re);
		}
	}


	@Override
	//@Transactional
	public void autoRejectForBaseAndMirrorChangeRequests(ChangeRequest changeRequest, UserDetails userDetails, Integer rejectStsId, Integer rejectAprStsId)
			throws OpservDataAccessException {
		try{
			TChngReq chngReq = changeRequestDao.findTChngReqById(changeRequest.getId());
			autoRejectChangeRequest(chngReq, userDetails, rejectStsId, rejectAprStsId);
			changeRequestDao.updateTChngReq(chngReq);
			List<TChngReq> childChangeRequestList = changeRequestDao.findTChngReqByParentChangeRequestId(changeRequest.getId());
			if (null != childChangeRequestList && !childChangeRequestList.isEmpty()) {
				for (TChngReq childChangeRequest : childChangeRequestList) {
					autoRejectChangeRequest(childChangeRequest, userDetails, rejectStsId, rejectAprStsId);
				}
				changeRequestDao.updateTChngReq(childChangeRequestList);
			}
		}catch(RuntimeException re){
			LOGGER.error("Error occurred while auto rejecting the base and mirror CR");
			throw new OpservDataAccessException("Error occurred while auto rejecting the base and mirror CR",re);
		}
	}


	/** (non-Javadoc)
	 * @see com.cognizant.opserv.sp.cr.process.internal.persistence.dao.service.ChangeRequestProcessDAOService#findZipAlignmentByChangeRequestId(com.cognizant.opserv.sp.model.cr.ChangeRequest, com.cognizant.opserv.sp.model.SalesPosition, java.lang.Character, com.cognizant.opserv.sp.model.auth.UserDetails)
	 */
	@Override
	//@Transactional
	public List<GeographyAlignment> findZipAlignmentByChangeRequestId(ChangeRequest chngReq, SalesPosition salesPostion, Character alignmentFlag, UserDetails userDetails)
			throws OpservDataAccessException {
		try{
			List<TTerrZipAlgmnt> zipAlignmentList = changeRequestDao.findZipAlignmentByChangeRequestId(chngReq.getId(), alignmentFlag, userDetails.getTenantId());
			List<GeographyAlignment> geoGraphyAlignmentList = geographyAlignmentModelAssembler.convertTTerrZipAlgmntsToGeoAlignment(zipAlignmentList,salesPostion);
			return geoGraphyAlignmentList;
		}catch(RuntimeException re){
			LOGGER.error("Error occurred while fetching zip alignment by CRid");
			throw new OpservDataAccessException("Error occurred while fetching zip alignment by CRid",re);
		}
	}
	
	
	/** (non-Javadoc)
	 * @see com.cognizant.opserv.sp.cr.process.internal.persistence.dao.service.ChangeRequestProcessDAOService#findZipAlignmentByChangeRequestId(com.cognizant.opserv.sp.model.cr.ChangeRequest, com.cognizant.opserv.sp.model.SalesPosition, java.lang.Character, com.cognizant.opserv.sp.model.auth.UserDetails)
	 */
	@Override
	//@Transactional
	public List<Object> findMirrorZipAlignmentByChangeRequestId(ChangeRequest chngReq, Character alignmentFlag, UserDetails userDetails)
			throws OpservDataAccessException {
		try{
			List<Object> zipAlignmentList = changeRequestDao.findMirrorZipAlignmentByChangeRequestId(chngReq.getId(), alignmentFlag, userDetails.getTenantId());
			return zipAlignmentList;
		}catch(RuntimeException re){
			LOGGER.error("Error occurred while fetching mirror zip alignment by CRid");
			throw new OpservDataAccessException("Error occurred while fetching mirror zip alignment by CRid",re);
		}
	}


	@Override
	public List<ChangeRequestApproverDetails> fetchTChngReqDetails(ChangeRequest chngReq, UserDetails userDetails) throws OpservDataAccessException {
		try{
			List<ChangeRequestApproverDetails> changeRequestApproverList = new ArrayList<ChangeRequestApproverDetails>();
			List<TChngReqAppr> approvalList = changeRequestApproverDAO.fetchTChngReqDetails(chngReq.getId(), userDetails.getTenantId());
			for (TChngReqAppr tChngReqAppr : approvalList) {
				ChangeRequestApproverDetails changeRequestApproverDetails = changeRequestAssembler.convertTChngApprToChangeReqApprModel(tChngReqAppr, userDetails);
				changeRequestApproverList.add(changeRequestApproverDetails);
			}
			return changeRequestApproverList;
		}catch(RuntimeException re){
			LOGGER.error("Error occurred while fetching change request details");
			throw new OpservDataAccessException("Error occurred while fetching change request details",re);
		}
		
	}
	
	
	
	/** (non-Javadoc)
	 * @see com.cognizant.opserv.sp.persistence.dao.service.ChangeRequestDAOService#createLineItemForSourceCustomer(java.util.List, com.cognizant.opserv.sp.model.auth.UserDetails)
	 */
	@Override
	public void createLineItemForSourceCustomer(List<CustomerAlignmentChangeRequestDetails> crLineItems, UserDetails userDetails) throws OpservDataAccessException {
		
		List<TCustAlgmntChngReqDet> tCAlgmntCRDets = new ArrayList<TCustAlgmntChngReqDet>();
		try{
			if (crLineItems != null && crLineItems.size() > 0) {
				for(CustomerAlignmentChangeRequestDetails  customerAlignmentChangeRequestDetails : crLineItems){
					TCustAlgmntChngReqDet tCustAlgmntChngReqDet = new TCustAlgmntChngReqDet();
					TCustAlgmntChngReqDetId tCustAlgmntChngReqDetId = new TCustAlgmntChngReqDetId();
					tCustAlgmntChngReqDetId.setChngReqId(customerAlignmentChangeRequestDetails.getParentChangeRequest().getId());
					tCustAlgmntChngReqDetId.setCustAlgmntId(customerAlignmentChangeRequestDetails.getOldCustomerAlignment().getId());
					tCustAlgmntChngReqDet.setTCustAlgmntChngReqDetId(tCustAlgmntChngReqDetId);
					tCustAlgmntChngReqDet.setAlgmntFlag(CommonConstants.SOURCE);
					tCustAlgmntChngReqDet.setStatusId(ChangeRequestStatusType.SUBMISSION_IN_PROGRESS.getId());
					if (null != userDetails.getUserId()) {
						tCustAlgmntChngReqDet.setCreatedBy(userDetails.getUserId());
					}
					tCustAlgmntChngReqDet.setTCustAlgmntChngReqDetId(tCustAlgmntChngReqDetId);
					if (null != customerAlignmentChangeRequestDetails.getChangeRequestBusinessReason() && null != customerAlignmentChangeRequestDetails.getChangeRequestBusinessReason().getId()) {
						TChngReqBussReason tChngReqBussReason = tChngReqBussReasonDAO.findTChngReqBussReasonById(customerAlignmentChangeRequestDetails.getChangeRequestBusinessReason().getId());
						if (null != tChngReqBussReason) {
							tCustAlgmntChngReqDet.setTChngReqBussReason(tChngReqBussReason);
						}
					}
					tCustAlgmntChngReqDet.setCreateDt(DateUtil.getCurrentDate());
					tCustAlgmntChngReqDet.setTenantId(userDetails.getTenantId());
					tCustAlgmntChngReqDet.setComments(customerAlignmentChangeRequestDetails.getComments());
					ChangeRequestDetailsBlob changeRequestDetailsBlobTarget = new ChangeRequestDetailsBlob();
					if (null != customerAlignmentChangeRequestDetails.getOldCustomerAlignment() && null != customerAlignmentChangeRequestDetails.getOldCustomerAlignment().getId()) {
						changeRequestDetailsBlobTarget.setPreviousValue(customerAlignmentChangeRequestDetails.getOldCustomerAlignment());				
						}
					
					String jsonReqDetailsTarget = JSONUtil.toJSON(changeRequestDetailsBlobTarget);
					tCustAlgmntChngReqDet.setReqDetails(jsonReqDetailsTarget);
					tCAlgmntCRDets.add(tCustAlgmntChngReqDet);
					LOGGER.info("saveChangeRequestDetailsPull Target Customer Alignment Id : " + customerAlignmentChangeRequestDetails.getOldCustomerAlignment().getId());
					spAssignmentsViewService.markCustAlgmntFlagAsDirty(customerAlignmentChangeRequestDetails.getOldCustomerAlignment().getSalesPosition().getId(), customerAlignmentChangeRequestDetails.getOldCustomerAlignment().getCustomer().getId(), userDetails);
				}
				customerAligmentChnageRequestDetailsDAO.createTCustAlgmntChngReqDet(tCAlgmntCRDets);
			}
		}catch(RuntimeException e){
			LOGGER.error("Error during creating lineItem For SourceCustomer"+e.getMessage());
			throw new OpservDataAccessException("Error during creating lineItem For SourceCustomer",e);
		}
	}

	/** (non-Javadoc)
	 * @see com.cognizant.opserv.sp.persistence.dao.service.ChangeRequestDAOService#createLineItemForTargetCustomer(java.util.List, com.cognizant.opserv.sp.model.auth.UserDetails)
	 */
	@Override
	public void createLineItemForTargetCustomer(
			List<CustomerAlignmentChangeRequestDetails> crLineItems,
			UserDetails userDetails) throws OpservDataAccessException {
	
		List<TCustAlgmntChngReqDet> tCAlgmntCRDets = new ArrayList<TCustAlgmntChngReqDet>();
		try{
			if (crLineItems != null && crLineItems.size() > 0) {
				for(CustomerAlignmentChangeRequestDetails  customerAlignmentChangeRequestDetails : crLineItems){
					TCustAlgmntChngReqDet tCustAlgmntChngReqDet = new TCustAlgmntChngReqDet();
					TCustAlgmntChngReqDetId tCustAlgmntChngReqDetId = new TCustAlgmntChngReqDetId();
					tCustAlgmntChngReqDetId.setChngReqId(customerAlignmentChangeRequestDetails.getParentChangeRequest().getId());
					tCustAlgmntChngReqDetId.setCustAlgmntId(customerAlignmentChangeRequestDetails.getNewCustomerAlignment().getId());
					tCustAlgmntChngReqDet.setTCustAlgmntChngReqDetId(tCustAlgmntChngReqDetId);
					tCustAlgmntChngReqDet.setAlgmntFlag(CommonConstants.TARGET);
					tCustAlgmntChngReqDet.setStatusId(ChangeRequestStatusType.SUBMISSION_IN_PROGRESS.getId());
					if (null != userDetails.getUserId()) {
						tCustAlgmntChngReqDet.setCreatedBy(userDetails.getUserId());
					}
					tCustAlgmntChngReqDet.setTCustAlgmntChngReqDetId(tCustAlgmntChngReqDetId);
					if (null != customerAlignmentChangeRequestDetails.getChangeRequestBusinessReason() && null != customerAlignmentChangeRequestDetails.getChangeRequestBusinessReason().getId()) {
						TChngReqBussReason tChngReqBussReason = tChngReqBussReasonDAO.findTChngReqBussReasonById(customerAlignmentChangeRequestDetails.getChangeRequestBusinessReason().getId());
						if (null != tChngReqBussReason) {
							tCustAlgmntChngReqDet.setTChngReqBussReason(tChngReqBussReason);
						}
					}
					tCustAlgmntChngReqDet.setCreateDt(DateUtil.getCurrentDate());
					tCustAlgmntChngReqDet.setTenantId(userDetails.getTenantId());
					tCustAlgmntChngReqDet.setComments(customerAlignmentChangeRequestDetails.getComments());
					ChangeRequestDetailsBlob changeRequestDetailsBlobTarget = new ChangeRequestDetailsBlob();
					if (null != customerAlignmentChangeRequestDetails.getNewCustomerAlignment() && null != customerAlignmentChangeRequestDetails.getNewCustomerAlignment().getId()) {
						changeRequestDetailsBlobTarget.setUpdatedValue(customerAlignmentChangeRequestDetails.getNewCustomerAlignment());	
						changeRequestDetailsBlobTarget.setNewCustomerProductAlignments(customerAlignmentChangeRequestDetails.getNewCustomerProductAlignments());
						}
					
					String jsonReqDetailsTarget = JSONUtil.toJSON(changeRequestDetailsBlobTarget);
					tCustAlgmntChngReqDet.setReqDetails(jsonReqDetailsTarget);
					tCAlgmntCRDets.add(tCustAlgmntChngReqDet);
					LOGGER.info("saveChangeRequestDetailsPull Target Customer Alignment Id : " + customerAlignmentChangeRequestDetails.getNewCustomerAlignment().getId());
					spAssignmentsViewService.markCustAlgmntFlagAsDirty(customerAlignmentChangeRequestDetails.getNewCustomerAlignment().getSalesPosition().getId(), customerAlignmentChangeRequestDetails.getNewCustomerAlignment().getCustomer().getId(), userDetails);
				}
				customerAligmentChnageRequestDetailsDAO.createTCustAlgmntChngReqDet(tCAlgmntCRDets);
			}
		}catch(RuntimeException e){
			LOGGER.error("Error during creating lineItem For TargetCustomer"+e.getMessage());
			throw new OpservDataAccessException("Error during creating lineItem For TargetCustomer",e);
		}
		
	}

	/** (non-Javadoc)
	 * @see com.cognizant.opserv.sp.persistence.dao.service.ChangeRequestDAOService#createLineItemForSourceZip(java.util.List, com.cognizant.opserv.sp.model.auth.UserDetails)
	 */
	@Override
	public void createLineItemForSourceZip(
			List<ZipAlignmentChangeRequestDetails> crLineItems,
			UserDetails userDetails) throws OpservDataAccessException {
		
		List<TTerrZipAlgmntChngReqDet> tZAlgmntCRDetsList = new ArrayList<TTerrZipAlgmntChngReqDet>();
		TChngReq changeRequest = null;
		try{
			if (crLineItems != null && crLineItems.size() > 0) {
				for(ZipAlignmentChangeRequestDetails zipAlignmentChangeRequestDetails : crLineItems){
					TTerrZipAlgmntChngReqDet tTerrZipAlgmntChngReqDet = new TTerrZipAlgmntChngReqDet();
					
					if(null != zipAlignmentChangeRequestDetails.getParentChangeRequest().getId()){
						 changeRequest = changeRequestDao.findTChngReqById(zipAlignmentChangeRequestDetails.getParentChangeRequest().getId());	
						}
						if (null != changeRequest) {
							tTerrZipAlgmntChngReqDet.setTChngReq(changeRequest);
						}
						
				tTerrZipAlgmntChngReqDet.setPostalCd(zipAlignmentChangeRequestDetails.getOldGeographyAlignmentObject().getPostalCode().getCode());
				tTerrZipAlgmntChngReqDet.setGeoId((int) zipAlignmentChangeRequestDetails.getOldGeographyAlignmentObject().getPostalCode().getGeoCode());
				tTerrZipAlgmntChngReqDet.setSalesHierId(zipAlignmentChangeRequestDetails.getOldGeographyAlignmentObject().getSalesPosition().getHierarchy().getId());
				tTerrZipAlgmntChngReqDet.setSalesPosId(zipAlignmentChangeRequestDetails.getOldGeographyAlignmentObject().getSalesPosition().getId());
				tTerrZipAlgmntChngReqDet.setStatusId(ChangeRequestStatusType.SUBMISSION_IN_PROGRESS.getId());
				tTerrZipAlgmntChngReqDet.setAlgmntFlag(CommonConstants.SOURCE);
				tTerrZipAlgmntChngReqDet.setCreatedBy(userDetails.getUserId());
				tTerrZipAlgmntChngReqDet.setCreateDt(DateUtil.getCurrentDate());
				tTerrZipAlgmntChngReqDet.setTenantId(userDetails.getTenantId());
				tTerrZipAlgmntChngReqDet.setComments(zipAlignmentChangeRequestDetails.getComments());
				ChangeRequestZipAlignmentDetailsBlob changeRequestDetailsBlobSource = new ChangeRequestZipAlignmentDetailsBlob();
				if(null != zipAlignmentChangeRequestDetails.getOldGeographyAlignmentObject()) {
				changeRequestDetailsBlobSource.setPreviousValue(zipAlignmentChangeRequestDetails.getOldGeographyAlignmentObject());
				}
				String jsonReqDetailsSource = JSONUtil.toJSON(changeRequestDetailsBlobSource);
				tTerrZipAlgmntChngReqDet.setReqDetail(jsonReqDetailsSource);
				tZAlgmntCRDetsList.add(tTerrZipAlgmntChngReqDet);
				LOGGER.info("saveZipChangeRequestDetailsPush Source GeographyAlignment Postal Code : " + tTerrZipAlgmntChngReqDet.getPostalCd() + " Geo Code : "
						+ tTerrZipAlgmntChngReqDet.getGeoId() + " Hiererchy Id : " + tTerrZipAlgmntChngReqDet.getSalesHierId() + " SalesPostionId : "
						+ tTerrZipAlgmntChngReqDet.getSalesPosId());
				spAssignmentsViewService.markZipSalesPosFlagAsDirty(tTerrZipAlgmntChngReqDet.getSalesPosId(), tTerrZipAlgmntChngReqDet.getPostalCd(), userDetails);
				
			}
				tTerrZipAlgmntChngReqDetDAO.createTTerrZipAlgmntChngReqDet(tZAlgmntCRDetsList);
		}
	}catch(RuntimeException e){
		LOGGER.error("Error during creating lineItem For SourceZip"+e.getMessage());
		throw new OpservDataAccessException("Error during creating lineItem For SourceZip",e);
	}
		
	}

	/** (non-Javadoc)
	 * @see com.cognizant.opserv.sp.persistence.dao.service.ChangeRequestDAOService#createLineItemForTargetZip(java.util.List, com.cognizant.opserv.sp.model.auth.UserDetails)
	 */
	@Override
	public void createLineItemForTargetZip(
			List<ZipAlignmentChangeRequestDetails> crLineItems,
			UserDetails userDetails) throws OpservDataAccessException {
		List<TTerrZipAlgmntChngReqDet> tZAlgmntCRDetsList = new ArrayList<TTerrZipAlgmntChngReqDet>();
		TChngReq changeRequest = null;
		
		try{
			if (crLineItems != null && crLineItems.size() > 0) {
				for(ZipAlignmentChangeRequestDetails zipAlignmentChangeRequestDetails : crLineItems){
					TTerrZipAlgmntChngReqDet tTerrZipAlgmntChngReqDet = new TTerrZipAlgmntChngReqDet();

					if(null != zipAlignmentChangeRequestDetails.getParentChangeRequest().getId()){
					 changeRequest = changeRequestDao.findTChngReqById(zipAlignmentChangeRequestDetails.getParentChangeRequest().getId());	
					}
					if (null != changeRequest) {
						tTerrZipAlgmntChngReqDet.setTChngReq(changeRequest);
					}
					tTerrZipAlgmntChngReqDet.setPostalCd(zipAlignmentChangeRequestDetails.getNewGeographyAlignmentObject().getPostalCode().getCode());
					tTerrZipAlgmntChngReqDet.setGeoId((int) zipAlignmentChangeRequestDetails.getNewGeographyAlignmentObject().getPostalCode().getGeoCode());
					tTerrZipAlgmntChngReqDet.setSalesHierId(zipAlignmentChangeRequestDetails.getNewGeographyAlignmentObject().getSalesPosition().getHierarchy().getId());
					tTerrZipAlgmntChngReqDet.setSalesPosId(zipAlignmentChangeRequestDetails.getNewGeographyAlignmentObject().getSalesPosition().getId());
					tTerrZipAlgmntChngReqDet.setStatusId(ChangeRequestStatusType.SUBMISSION_IN_PROGRESS.getId());
					tTerrZipAlgmntChngReqDet.setAlgmntFlag(CommonConstants.TARGET);
					tTerrZipAlgmntChngReqDet.setCreatedBy(userDetails.getUserId());
					tTerrZipAlgmntChngReqDet.setCreateDt(DateUtil.getCurrentDate());
					tTerrZipAlgmntChngReqDet.setTenantId(userDetails.getTenantId());
					tTerrZipAlgmntChngReqDet.setComments(zipAlignmentChangeRequestDetails.getComments());
					ChangeRequestZipAlignmentDetailsBlob changeRequestDetailsBlobSource = new ChangeRequestZipAlignmentDetailsBlob();
					if(null != zipAlignmentChangeRequestDetails.getNewGeographyAlignmentObject()) {
					changeRequestDetailsBlobSource.setUpdatedValue(zipAlignmentChangeRequestDetails.getNewGeographyAlignmentObject());
					}
					String jsonReqDetailsSource = JSONUtil.toJSON(changeRequestDetailsBlobSource);
					tTerrZipAlgmntChngReqDet.setReqDetail(jsonReqDetailsSource);
					tZAlgmntCRDetsList.add(tTerrZipAlgmntChngReqDet);
					LOGGER.info("saveZipChangeRequestDetailsPush Source GeographyAlignment Postal Code : " + tTerrZipAlgmntChngReqDet.getPostalCd() + " Geo Code : "
							+ tTerrZipAlgmntChngReqDet.getGeoId() + " Hiererchy Id : " + tTerrZipAlgmntChngReqDet.getSalesHierId() + " SalesPostionId : "
							+ tTerrZipAlgmntChngReqDet.getSalesPosId());
					spAssignmentsViewService.markZipSalesPosFlagAsDirty(tTerrZipAlgmntChngReqDet.getSalesPosId(), tTerrZipAlgmntChngReqDet.getPostalCd(), userDetails);
					
				}
				tTerrZipAlgmntChngReqDetDAO.createTTerrZipAlgmntChngReqDet(tZAlgmntCRDetsList);
			}
		}catch(RuntimeException e){
			LOGGER.error("Error during creating lineItem For TargetZip"+e.getMessage());
			throw new OpservDataAccessException("Error during creating lineItem For TargetZip",e);
		}
	}
	
	/** (non-Javadoc)
	 * @see com.cognizant.opserv.sp.persistence.dao.service.ChangeRequestDAOService#createSourceApprovers(com.cognizant.opserv.sp.model.cr.ChangeRequest, com.cognizant.opserv.sp.model.SalesPosition, com.cognizant.opserv.sp.model.auth.UserDetails)
	 */
	@Override
	public void createSourceApprovers(ChangeRequest chngReq, SalesPosition sourceSP, UserDetails userDetails) throws OpservDataAccessException {
		
		Set<TChngReqAppr> tChngReqApprs = new LinkedHashSet<TChngReqAppr>();
		final TChngReqAppr tChngReqAppr = new TChngReqAppr();
		
		try {
			TChngReq changeRequest = changeRequestDao.findTChngReqById(chngReq
					.getId());
			List<Object[]> fetchSourceSalesPositionDetails = fetchSourceSalesPositionDetails(
					changeRequest.getTriggerId(), sourceSP, userDetails,
					sourceSP.getId(), sourceSP.getHierarchy().getId());
			if (fetchSourceSalesPositionDetails != null
					&& fetchSourceSalesPositionDetails.size() > 0) {
				for (Object[] salesPosition : fetchSourceSalesPositionDetails) {
					tChngReqAppr.settChngReq(changeRequest);
					tChngReqAppr.setApprSalesPosId((Long) salesPosition[0]);
					tChngReqAppr.setApprSalesHierId((Long) salesPosition[1]);
					tChngReqAppr
							.setStatusId(ChangeRequestStatusType.PENDING_FOR_APPROVAL
									.getId());
					/** NEED TO BE VERIFY(set null according to previous logic) **/
					tChngReqAppr.setApprTypeId((Integer) salesPosition[2]);
					tChngReqAppr.setTargetApprFlag(CommonConstants.TARGET_N);
					tChngReqAppr.setActionDtTm(DateUtil.getCurrentDate());
					if (null != userDetails.getUserId()) {
						tChngReqAppr.setCreatedBy(userDetails.getUserId());
					}
					tChngReqAppr.setCreateDt(DateUtil.getCurrentDate());
					tChngReqAppr.setTenantId(userDetails.getTenantId());
					tChngReqAppr.setActiveFlag(CommonConstants.ACTIVE_Y);
					if (null != userDetails.getUserId()) {
						tChngReqAppr.setUpdatedBy(userDetails.getUserId());
					}
					tChngReqAppr.setUpdateDt(DateUtil.getCurrentDate());
					tChngReqApprs.add(tChngReqAppr);
				}
			}
			List<TChngReqAppr> tChngReqApprList = new ArrayList<TChngReqAppr>();
			tChngReqApprList.addAll(tChngReqApprs);
			changeRequestApproverDAO.createTChngReqAppr(tChngReqApprList);

		}catch(RuntimeException e){
			LOGGER.error("Error during creating source Approver");
			throw new OpservDataAccessException("Error during creating source Approver",e);
		}
	}

	
	/**
	 * @param triggerId
	 * @param salesPostion
	 * @param userDetails
	 * @param sourceSalesPostionId
	 * @param sourceSalesHierarchyId
	 * @return List<Object[]>
	 */
	private List<Object[]> fetchSourceSalesPositionDetails(int triggerId, SalesPosition salesPostion, UserDetails userDetails, Long sourceSalesPostionId,
			Long sourceSalesHierarchyId) throws OpservDataAccessException {
		List<Object[]> sourcesplist = new ArrayList<Object[]>();
	try{
		List<TChngReqConfig> changeRequestConfigList = changeRequestConfigDAO.getTChngReqConfig(salesPostion.getAlignmment().getSalesTeam().getId(), salesPostion.getAlignmment()
				.getId(), salesPostion.getAlignmment().getSalesTeam().getBusinessUnit().getId(), triggerId, userDetails.getTenantId());

		if (changeRequestConfigList != null) {
			TChngReqConfig changeRequestConfig = changeRequestConfigList.get(0);

			Map<Integer, String> typeMap = getApprTypeMap(userDetails.getTenantId(), userDetails.getLocaleId());

			String primaryApprType = null;
			if (typeMap.containsKey(changeRequestConfig.getPrApprTypeId())) {
				primaryApprType = typeMap.get(changeRequestConfig.getPrApprTypeId());

			}

			Integer primaryId = changeRequestConfig.getPrApprTypeId();
			Integer secondaryId = changeRequestConfig.getSecApprTypeId();
            
			if (primaryId == null && secondaryId == null) {
				Object[] obj = { sourceSalesPostionId, sourceSalesHierarchyId, null };
				sourcesplist.add(obj);
			} else if (primaryId != null) {

				int acPullTrggrId = ChangeRequestTriggerType.PULL_CUSTOMER.getId();
				int acPushTrggrId = ChangeRequestTriggerType.PUSH_CUSTOMER.getId();
				int azPushTrggrId = ChangeRequestTriggerType.PUSH_ZIP.getId();
				if (changeRequestConfig.getPrApprTypeId().equals(ChangeRequestApproverType.OWNER.getId()) || changeRequestConfig.getPrApprTypeId().equals(ChangeRequestApproverType.PEER.getId())) {
					if ((triggerId == acPullTrggrId || triggerId == acPushTrggrId || triggerId == azPushTrggrId) && changeRequestConfig.getPrApprTypeId().equals(ChangeRequestApproverType.OWNER.getId())) {
						List<Object> parentIdsForSalesPosition = tSalesPosDAO.fetchParentIdsForSp(sourceSalesPostionId, sourceSalesHierarchyId, userDetails.getTenantId());
						if (parentIdsForSalesPosition != null) {
							for (Object object : parentIdsForSalesPosition) {
								Object[] obj = (Object[]) object;
								if (obj[0] != null && obj[1] != null) {
									Object[] obj1 = new Object[3];

									obj1[0] = (Object) obj[0];
									obj1[1] = (Object) obj[1];
									obj1[2] = primaryId;
									sourcesplist.add(obj1);
								}
							}
						}
					}else {

						if (changeRequestConfig.getPrApprTypeId().equals(ChangeRequestApproverType.PEER.getId())) {
							sourcesplist = fetchPeerApproverList(salesPostion, sourceSalesPostionId, sourceSalesHierarchyId, userDetails, new ArrayList<Object[]>(), primaryId);
						}

					}
				}

			}
}
	    return sourcesplist;
		}catch(RuntimeException re){
			LOGGER.error("Error occurred while fetching source sales position details");
			throw new OpservDataAccessException("Error occurred while fetching source sales position details",re);
		}
	
	}
	
	/**
	 * @param salesPostion
	 * @param salesPositionId
	 * @param salesHierId
	 * @param userDetail
	 * @param peerSalesPositionList
	 * @param primaryId
	 * @return List<Object[]>
	 */
	private List<Object[]> fetchPeerApproverList(SalesPosition salesPostion, Long salesPositionId, Long salesHierId, UserDetails userDetail, List<Object[]> peerSalesPositionList,
			int primaryId) throws OpservDataAccessException{

		List<Object> parentIdsForSalesPosition = null;

		Long salesPositonId = null;
		Long salesHierarchyId = null;

		try{
			parentIdsForSalesPosition = tSalesPosDAO.fetchParentIdsForSp(salesPositionId, salesHierId, userDetail.getTenantId());

		if (parentIdsForSalesPosition != null && parentIdsForSalesPosition.size() > 0) {

			for (Object object : parentIdsForSalesPosition) {
				Object[] obj = (Object[]) object;

				salesPositonId = (Long) obj[1];

				if (salesPostion.getHierarchy().getId().equals(salesHierarchyId)) {

					Object[] obj1 = new Object[3];

					obj1[0] = obj[0];
					obj1[1] = obj[1];
					obj1[2] = primaryId;

					peerSalesPositionList.add(obj1);

					break;

				} else {

					salesPositonId = (Long) obj[0];

					salesHierarchyId = (Long) obj[1];

					fetchPeerApproverList(salesPostion, salesPositonId, salesHierarchyId, userDetail, peerSalesPositionList, primaryId);
				}

			}

		} else {
			Object[] obj = { salesPositionId, salesHierId, primaryId };
			peerSalesPositionList.add(obj);
		}

		return peerSalesPositionList;
	}catch(RuntimeException re){
			LOGGER.error("Error occurred while fetching peer approvers");
			throw new OpservDataAccessException("Error occurred while fetching peer approvers",re);
		}
	}
	
	/**
	 * @param tenantId
	 * @param localeId
	 * @return
	 */
	private Map<Integer, String> getApprTypeMap(Short tenantId, String localeId) throws OpservDataAccessException{
		Map<Integer, String> apprType = new HashMap<Integer, String>();
	try{
		List<TChngReqApprType> apprTypelist = changeRequestApproverTypeDAO.findApprTypeByLocale(tenantId, CommonConstants.DEFAULT_LOCALE_ID);

		for (TChngReqApprType tapprType : apprTypelist) {
			apprType.put(tapprType.getTChngReqApprTypeId().getApprTypeId(), tapprType.getApprTypeName());
		}

		return apprType;
	}catch(RuntimeException re){
		LOGGER.error("Error occurred while fetching approvers type map");
		throw new OpservDataAccessException("Error occurred while fetching approvers type map",re);
		}
	}
	
	/** (non-Javadoc)
	 * @see com.cognizant.opserv.sp.persistence.dao.service.ChangeRequestDAOService#createTargetApprovers(com.cognizant.opserv.sp.model.cr.ChangeRequest, com.cognizant.opserv.sp.model.SalesPosition, com.cognizant.opserv.sp.model.auth.UserDetails)
	 */
	@Override
	public void createTargetApprovers(ChangeRequest chngReq,
			SalesPosition targetSP, UserDetails userDetails)throws OpservDataAccessException  {
		Set<TChngReqAppr> tChngReqApprs = new LinkedHashSet<TChngReqAppr>();
		final TChngReqAppr tChngReqAppr = new TChngReqAppr();
		try {
			TChngReq changeRequest = changeRequestDao.findTChngReqById(chngReq
					.getId());

			List<Object[]> fetchTargetSalesPositionDetails = fetchTargetSalesPositionDetails(
					changeRequest.getTriggerId(), targetSP, userDetails,
					targetSP.getId(), targetSP.getHierarchy().getId());
			if (fetchTargetSalesPositionDetails != null
					&& fetchTargetSalesPositionDetails.size() > 0) {
				for (Object[] salesPosition : fetchTargetSalesPositionDetails) {

					tChngReqAppr.settChngReq(changeRequest);
					tChngReqAppr.setApprSalesPosId((Long) salesPosition[0]);
					tChngReqAppr.setApprSalesHierId((Long) salesPosition[1]);
					tChngReqAppr
							.setStatusId(ChangeRequestStatusType.PENDING_FOR_APPROVAL
									.getId());
					/** NEED TO BE VERIFY(set null according to previous logic) **/
					tChngReqAppr.setApprTypeId((Integer) salesPosition[2]);
					tChngReqAppr.setTargetApprFlag(CommonConstants.TARGET_Y);
					tChngReqAppr.setActionDtTm(DateUtil.getCurrentDate());
					if (null != userDetails.getUserId()) {
						tChngReqAppr.setCreatedBy(userDetails.getUserId());
					}
					tChngReqAppr.setCreateDt(DateUtil.getCurrentDate());
					tChngReqAppr.setTenantId(userDetails.getTenantId());
					tChngReqAppr.setActiveFlag(CommonConstants.ACTIVE_Y);
					if (null != userDetails.getUserId()) {
						tChngReqAppr.setUpdatedBy(userDetails.getUserId());
					}
					tChngReqAppr.setUpdateDt(DateUtil.getCurrentDate());
					tChngReqApprs.add(tChngReqAppr);
				}
			}

			List<TChngReqAppr> tChngReqApprList = new ArrayList<TChngReqAppr>();
			tChngReqApprList.addAll(tChngReqApprs);
			changeRequestApproverDAO.createTChngReqAppr(tChngReqApprList);
		}catch(RuntimeException e){
			LOGGER.error("Error during creating target Approver");
			throw new OpservDataAccessException("Error during creating target Approver",e);
		}
	}
	
	/**
	 * @param triggerId
	 * @param salesPostion
	 * @param userDetails
	 * @param targetSalesPostionId
	 * @param targetSalesHierarchyId
	 * @return
	 */
	private List<Object[]> fetchTargetSalesPositionDetails(int triggerId, SalesPosition salesPostion, UserDetails userDetails, Long targetSalesPostionId,
			Long targetSalesHierarchyId) throws OpservDataAccessException{
		List<Object[]> targetSalesPositionlist = new ArrayList<Object[]>();

		String sharing = null;
	try{
		int acPullTrggrId = ChangeRequestTriggerType.PULL_CUSTOMER.getId();
		int acPushTrggrId = ChangeRequestTriggerType.PUSH_CUSTOMER.getId();
		int azPushTrggrId = ChangeRequestTriggerType.PUSH_ZIP.getId();
		int ecTrggrId = ChangeRequestTriggerType.EDIT_CUSTOMER.getId();
		// int affTrggrId = ChangeRequestTriggerType.ADD_AFFILIATION.getId();
		if (triggerId == acPullTrggrId || triggerId == acPushTrggrId || triggerId == azPushTrggrId) {

			List<Object> sharingList = changeRequestConfigDAO.findBusinessRule(salesPostion.getAlignmment().getSalesTeam().getId(), salesPostion.getAlignmment().getId(),
					salesPostion.getAlignmment().getSalesTeam().getBusinessUnit().getId(), userDetails.getTenantId());
			for (Object sharingObject : sharingList) {
				sharing = (String) sharingObject;
				break;
			}
		}
		Map<Integer, String> typeMap = getApprTypeMap(userDetails.getTenantId(), userDetails.getLocaleId());

		if ((("Y").equals(sharing))) {
			List<TChngReqConfig> changeRequestConfigList = null;
			if (triggerId == acPullTrggrId) {
				changeRequestConfigList = changeRequestConfigDAO.getTChngReqConfig(salesPostion.getAlignmment().getSalesTeam().getId(), salesPostion.getAlignmment().getId(),
						salesPostion.getAlignmment().getSalesTeam().getBusinessUnit().getId(), ChangeRequestTriggerType.PULL_CUSTOMER.getId(), userDetails.getTenantId());
			} else if (triggerId == acPushTrggrId) {
				changeRequestConfigList = changeRequestConfigDAO.getTChngReqConfig(salesPostion.getAlignmment().getSalesTeam().getId(), salesPostion.getAlignmment().getId(),
						salesPostion.getAlignmment().getSalesTeam().getBusinessUnit().getId(), ChangeRequestTriggerType.PUSH_CUSTOMER.getId(), userDetails.getTenantId());
			} else if (triggerId == azPushTrggrId) {
				changeRequestConfigList = changeRequestConfigDAO.getTChngReqConfig(salesPostion.getAlignmment().getSalesTeam().getId(), salesPostion.getAlignmment().getId(),
						salesPostion.getAlignmment().getSalesTeam().getBusinessUnit().getId(), ChangeRequestTriggerType.PUSH_ZIP.getId(), userDetails.getTenantId());
			}
			else if (triggerId == ecTrggrId) {
				changeRequestConfigList = changeRequestConfigDAO.getTChngReqConfig(salesPostion.getAlignmment().getSalesTeam().getId(), salesPostion.getAlignmment().getId(),
						salesPostion.getAlignmment().getSalesTeam().getBusinessUnit().getId(), ChangeRequestTriggerType.EDIT_CUSTOMER.getId(), userDetails.getTenantId());
			}
			if (changeRequestConfigList != null) {
				TChngReqConfig changeRequestConfig = changeRequestConfigList.get(0);
				List<Object> parentIdsForSalesPostion = null;
				Integer primaryId = changeRequestConfig.getPrApprTypeId();
				Integer secondaryId = changeRequestConfig.getSecApprTypeId();
				String primaryApprType = null;
				if (typeMap.containsKey(changeRequestConfig.getPrApprTypeId())) {
					primaryApprType = typeMap.get(primaryId);

				}
				if (primaryId == null && secondaryId == null) {
					Object[] obj = { targetSalesPostionId, targetSalesHierarchyId, null };
					targetSalesPositionlist.add(obj);
				} else if (primaryApprType != null) {

					if (changeRequestConfig.getPrApprTypeId().equals(ChangeRequestApproverType.WHOLEHIERARCHY.getId())) {
						parentIdsForSalesPostion = tSalesPosDAO.fetchParentIdsForSp(targetSalesPostionId, targetSalesHierarchyId, userDetails.getTenantId());
						if (parentIdsForSalesPostion != null) {
							for (Object object : parentIdsForSalesPostion) {
								Object[] obj = (Object[]) object;
								if (obj[0] != null && obj[1] != null) {
									Object[] obj1 = new Object[3];
									obj1[0] = (Object) obj[0];
									obj1[1] = (Object) obj[1];
									obj1[2] = primaryId;
									targetSalesPositionlist.add(obj1);

									while (obj[0] != null && obj[1] != null) {
										parentIdsForSalesPostion = tSalesPosDAO
												.fetchParentIdsForSp(new Long((Long) obj[0]), new Long((Long) obj[1]), userDetails.getTenantId());
										if (parentIdsForSalesPostion != null) {
											for (Object object1 : parentIdsForSalesPostion) {
												obj = (Object[]) object1;
												if (obj[0] != null && obj[1] != null) {
													obj1 = new Object[3];
													obj1[0] = (Object) obj[0];
													obj1[1] = (Object) obj[1];
													obj1[2] = primaryId;
													targetSalesPositionlist.add(obj1);
												}
											}
										}
									}
								}
							}
						}
					} else if (changeRequestConfig.getPrApprTypeId().equals(ChangeRequestApproverType.IMMEDIATE.getId())) {
						parentIdsForSalesPostion = tSalesPosDAO.fetchParentIdsForSp(targetSalesPostionId, targetSalesHierarchyId, userDetails.getTenantId());
						if (parentIdsForSalesPostion != null) {
							for (Object object : parentIdsForSalesPostion) {
								Object[] obj = (Object[]) object;
								if (obj[0] != null && obj[1] != null) {
									Object[] obj1 = new Object[3];

									obj1[0] = (Object) obj[0];
									obj1[1] = (Object) obj[1];
									obj1[2] = primaryId;
									targetSalesPositionlist.add(obj1);
								}
							}
						}
					}

					else if (changeRequestConfig.getPrApprTypeId().equals(ChangeRequestApproverType.PARTIALHIERARCHY.getId())) {

						List<Object[]> partialSalesPositionList = new ArrayList<Object[]>();

						fetchPartialHierApproverList(targetSalesPostionId, targetSalesHierarchyId, userDetails.getTenantId(), changeRequestConfig.getChngReqConfigId(),
								partialSalesPositionList, primaryId);
						targetSalesPositionlist = partialSalesPositionList;
					}

				}

				if (targetSalesPositionlist.size() == 0) {
					Object[] obj = { targetSalesPostionId, targetSalesHierarchyId, primaryId };
					targetSalesPositionlist.add(obj);
				}

			}
		} else // if (sharing.equals("N"))
		{
			List<TChngReqConfig> changeRequestConfigList = null;
			if (triggerId == acPullTrggrId) {
				changeRequestConfigList = changeRequestConfigDAO.getTChngReqConfig(salesPostion.getAlignmment().getSalesTeam().getId(), salesPostion.getAlignmment().getId(),
						salesPostion.getAlignmment().getSalesTeam().getBusinessUnit().getId(), ChangeRequestTriggerType.PULL_CUSTOMER.getId(), userDetails.getTenantId());
			} else if (triggerId == acPushTrggrId) {
				changeRequestConfigList = changeRequestConfigDAO.getTChngReqConfig(salesPostion.getAlignmment().getSalesTeam().getId(), salesPostion.getAlignmment().getId(),
						salesPostion.getAlignmment().getSalesTeam().getBusinessUnit().getId(), ChangeRequestTriggerType.PUSH_CUSTOMER.getId(), userDetails.getTenantId());
			} else if (triggerId == azPushTrggrId) {
				changeRequestConfigList = changeRequestConfigDAO.getTChngReqConfig(salesPostion.getAlignmment().getSalesTeam().getId(), salesPostion.getAlignmment().getId(),
						salesPostion.getAlignmment().getSalesTeam().getBusinessUnit().getId(), ChangeRequestTriggerType.PUSH_ZIP.getId(), userDetails.getTenantId());
			}
			else if (triggerId == ecTrggrId) {
				changeRequestConfigList = changeRequestConfigDAO.getTChngReqConfig(salesPostion.getAlignmment().getSalesTeam().getId(), salesPostion.getAlignmment().getId(),
						salesPostion.getAlignmment().getSalesTeam().getBusinessUnit().getId(), ChangeRequestTriggerType.EDIT_CUSTOMER.getId(), userDetails.getTenantId());
			}

			if (changeRequestConfigList != null) {
				TChngReqConfig changeRequestConfig = changeRequestConfigList.get(0);
				List<Object> parentIdsForSalesPostion = new ArrayList<Object>();
				Integer secondaryId = changeRequestConfig.getSecApprTypeId();
				Integer primaryId = changeRequestConfig.getPrApprTypeId();
				String secApprType = null;
				if (typeMap.containsKey(changeRequestConfig.getSecApprTypeId())) {
					secApprType = typeMap.get(secondaryId);

				}
				if (secondaryId == null && primaryId == null) {
					Object[] obj = { targetSalesPostionId, targetSalesHierarchyId, null };
					targetSalesPositionlist.add(obj);
				} else if (secApprType != null) {
					if (changeRequestConfig.getSecApprTypeId().equals(ChangeRequestApproverType.WHOLEHIERARCHY.getId())) {

						parentIdsForSalesPostion = tSalesPosDAO.fetchParentIdsForSp(targetSalesPostionId, targetSalesHierarchyId, userDetails.getTenantId());
						if (parentIdsForSalesPostion != null) {
							for (Object object : parentIdsForSalesPostion) {
								Object[] obj = (Object[]) object;
								if (obj[0] != null && obj[1] != null) {
									Object[] obj1 = new Object[3];
									obj1[0] = (Object) obj[0];
									obj1[1] = (Object) obj[1];
									obj1[2] = secondaryId;
									targetSalesPositionlist.add(obj1);
									while (obj[0] != null && obj[1] != null) {
										parentIdsForSalesPostion = tSalesPosDAO
												.fetchParentIdsForSp(new Long((Long) obj[0]), new Long((Long) obj[1]), userDetails.getTenantId());
										if (parentIdsForSalesPostion != null) {
											for (Object object1 : parentIdsForSalesPostion) {
												obj = (Object[]) object1;
												if (obj[0] != null && obj[1] != null) {
													obj1 = new Object[3];
													obj1[0] = (Object) obj[0];
													obj1[1] = (Object) obj[1];
													obj1[2] = secondaryId;
													targetSalesPositionlist.add(obj1);
												}
											}
										}
									}
								}

							}
						}
					}

					else if (changeRequestConfig.getSecApprTypeId().equals(ChangeRequestApproverType.IMMEDIATE.getId()) || changeRequestConfig.getSecApprTypeId().equals(ChangeRequestApproverType.PEER.getId())) {
						parentIdsForSalesPostion = tSalesPosDAO.fetchParentIdsForSp(targetSalesPostionId, targetSalesHierarchyId, userDetails.getTenantId());
						if (parentIdsForSalesPostion != null) {
							for (Object object : parentIdsForSalesPostion) {
								Object[] obj = (Object[]) object;
								if (obj[0] != null && obj[1] != null) {
									Object[] obj1 = new Object[3];

									obj1[0] = (Object) obj[0];
									obj1[1] = (Object) obj[1];
									obj1[2] = secondaryId;
									targetSalesPositionlist.add(obj1);
								}

							}
						}
					}

					else if (changeRequestConfig.getSecApprTypeId().equals(ChangeRequestApproverType.PARTIALHIERARCHY.getId())) {

						List<Object[]> partialSalesPositionList = new ArrayList<Object[]>();

						this.fetchPartialHierApproverList(targetSalesPostionId, targetSalesHierarchyId, userDetails.getTenantId(), changeRequestConfig.getChngReqConfigId(),
								partialSalesPositionList, secondaryId);
						targetSalesPositionlist = partialSalesPositionList;
					}
				}
				if (targetSalesPositionlist.size() == 0) {
					Object[] obj = { targetSalesPostionId, targetSalesHierarchyId, secondaryId };
					targetSalesPositionlist.add(obj);
				}

			}

		}

		return targetSalesPositionlist;
	}catch(RuntimeException e){
			LOGGER.error("Error during creating target Approver");
			throw new OpservDataAccessException("Error during fetching target salesPosition Details",e);
		}
	}
	
	/**
	 * @param salesPositionId
	 * @param salesHierId
	 * @param tenantId
	 * @param chngReqConfigId
	 * @param partialSPList
	 * @param apprTypeId
	 * @return List<Object[]>
	 */
	private List<Object[]> fetchPartialHierApproverList(Long salesPositionId, Long salesHierId, Short tenantId, Integer chngReqConfigId, List<Object[]> partialSPList,
			int apprTypeId)throws OpservDataAccessException {

		try{

		List<Object> fetchPartialSalesHierIds = changeRequestApproverHierarchyDAO.fetchPartialSalesHierIds(chngReqConfigId, tenantId);

		List<Object> parentIdsForSp = null;

		parentIdsForSp = tSalesPosDAO.fetchParentIdsForSp(salesPositionId, salesHierId, tenantId);

		if (parentIdsForSp != null && parentIdsForSp.size() > 0) {

			while (parentIdsForSp != null && parentIdsForSp.size() > 0) {
				Long shId = null;
				Long spId = null;
				for (Object shId1 : fetchPartialSalesHierIds) {

					Long fetchPartialSalesHierId = (Long) shId1;

					if (salesHierId > fetchPartialSalesHierId) {

						for (Object object : parentIdsForSp) {
							Object[] obj = (Object[]) object;

							shId = (Long) obj[1];
							spId = (Long) obj[0];
							if (fetchPartialSalesHierId.equals(shId)) {

								Object[] obj1 = new Object[3];

								obj1[0] = obj[0];
								obj1[1] = obj[1];
								obj1[2] = apprTypeId;

								partialSPList.add(obj1);

								break;

							}

						}
					}

				}

				parentIdsForSp = tSalesPosDAO.fetchParentIdsForSp(spId, shId, tenantId);
			}

		} else {
			Object[] obj1 = new Object[3];
			obj1[0] = salesHierId;
			obj1[1] = salesPositionId;
			obj1[2] = apprTypeId;

			partialSPList.add(obj1);
		}

		return partialSPList;
	}catch(RuntimeException e){
			LOGGER.error("Error during creating target Approver");
			throw new OpservDataAccessException("Error during fetching partial hier approvers",e);
		}
	}
	
	/** (non-Javadoc)
	 * @see com.cognizant.opserv.sp.persistence.dao.service.ChangeRequestDAOService#submitChangeRequest(com.cognizant.opserv.sp.model.cr.ChangeRequest, com.cognizant.opserv.sp.model.auth.UserDetails)
	 */
	@Override
	public void submitChangeRequests(ChangeRequest chngReq, UserDetails userDetails) throws OpservDataAccessException {
		
		try{
		TChngReq changeRequest = changeRequestDao.findTChngReqById(chngReq.getId());
		spAssignmentsViewService.markChangeRequestFlagAsDirty(chngReq.getId(), userDetails);
		pendingForApprovalChangeRequestSubmit(changeRequest, userDetails);
		if (null != changeRequest.gettChngReqApprss() && changeRequest.gettChngReqApprss().size() > 0) {
			LOGGER.info("changeRequest.gettChngReqApprss() Size : " + changeRequest.gettChngReqApprss().size());
		}
		LOGGER.info("Triggering WorkFlow for crId:"+chngReq.getId());
		ChangeRequest changeReq = changeRequestAssembler.convertTChngReqToChangeRequestModel(changeRequest);
		RestClient.triggerWorkFlow(changeRequest, userDetails,createProcessInstanceURL,changeRequest.gettChngReqApprss(), changeReq);	
		changeRequestDao.updateTChngReq(changeRequest);
		List<TChngReq> childChangeRequestList = changeRequestDao.findTChngReqByParentChangeRequestId(chngReq.getId());
			if (null != childChangeRequestList && !childChangeRequestList.isEmpty()) {
				for (TChngReq childChangeRequest : childChangeRequestList) {
					pendingForApprovalChangeRequestSubmit(childChangeRequest, userDetails);

				}
				changeRequestDao.updateTChngReq(childChangeRequestList);
			}
		}catch(CallPlanServiceException exception)
		{
			LOGGER.error("Error during submit Change Request of call plan ");
			throw new OpservDataAccessException("Error during submit Change Request of call plan ",exception);
		}catch(RuntimeException e){
			LOGGER.error("Error during submit Change Request");
			throw new OpservDataAccessException("Error during submit Change Request",e);
		}
	}
	
	/**
	 * Pending for approval change request.
	 *
	 * @param chngReq the chng req
	 * @param userDetails the user details
	 * @throws CallPlanServiceException 
	 */
	private void pendingForApprovalChangeRequestSubmit(TChngReq chngReq, UserDetails userDetails)throws OpservDataAccessException, CallPlanServiceException {
		try{
			Date date = DateUtil.getCurrentDate();
			Integer pendAprStsId = ChangeRequestStatusType.PENDING_FOR_APPROVAL.getId();
			chngReq.setSubmitDtTm(date);
			chngReq.setUpdatedBy(userDetails.getUserId());
			chngReq.setActionBy(userDetails.getUserId());
			chngReq.setUpdateDt(date);
			chngReq.setActionDtTm(date);
			chngReq.setLastSalesPosId(chngReq.getRaiseSalesPosId());
			chngReq.setLastSalesHierId(chngReq.getRaiseSalesHierId());
			chngReq.setStatusId(pendAprStsId);

			chngReq.setStatusId(ChangeRequestStatusType.PENDING_FOR_APPROVAL.getId());
			updateDetailStatus(chngReq, ChangeRequestStatusType.PENDING_FOR_APPROVAL.getId(), userDetails);
			
		}catch(RuntimeException e){
			LOGGER.error("Error during updating status as pendingForApprovalChangeRequest");
			throw new OpservDataAccessException("Error during updating status as pendingForApprovalChangeRequest",e);
		}
	}
	
	@Override
	public void updateZipLineItemStatus(ChangeRequest chngReq, Integer status, PostalCode postalCode, UserDetails userDetails) {
		List<TTerrZipAlgmntChngReqDet> zipList;
		Date date = DateUtil.getCurrentDate();
		zipList = tTerrZipAlgmntChngReqDetDAO.findZipLineItemByChngReqPostalCode(postalCode.getCode(), userDetails.getTenantId(), chngReq.getId());
			
		if (null != zipList && !zipList.isEmpty()) {
			for (TTerrZipAlgmntChngReqDet zipChngReq : zipList) {
				zipChngReq.setUpdatedBy(userDetails.getUserId());
				zipChngReq.setUpdateDt(date);
				zipChngReq.setStatusId(status);
				//zipList.add(zipChngReq);
			}
			
		tTerrZipAlgmntChngReqDetDAO.updateTTerrZipAlgmntChngReqDet(zipList);
		}
		
		//DO we need to handle unlocking of Customer Line Items also?? Doubt
		
	}
	/**
	 * Gets the target approver status.
	 *
	 * @param chngReqId the chng req id
	 * @param targetAppvrFlg the target appvr flg
	 * @param tenantId the tenant id
	 * @return the target approver status
	 */
	@Override
	public ChangeRequestStatusType getChangeRequestTargetApproverStatus(Long chngReqId,
			Character targetAppvrFlg, Short tenantId)
			throws OpservDataAccessException {
		try {
			Integer statusId= changeRequestApproverDAO.getTargetApproverStatus(chngReqId, targetAppvrFlg, tenantId);
			if(null != statusId){
				return ChangeRequestStatusType.getChangeRequestStatusType(statusId);
			}
		} catch (RuntimeException e) {
			LOGGER.error("Error while fetching the status of change request approver");
			throw new OpservDataAccessException("Error while fetching the status of change request approver",e);
		}
		return null;
	}
	/**
	 * Gets the change requestfind approver comments.
	 *
	 * @param chngReqId the chng req id
	 * @param targetAppvrFlg the target appvr flg
	 * @param tenantId the tenant id
	 * @return the change request approver comments
	 * @throws OpservDataAccessException the opserv data access exception
	 */
	@Override
	public String getChangeRequestfindApproverComments(
			Long chngReqId, Character targetAppvrFlg, Short tenantId)
			throws OpservDataAccessException {
		try {
			return changeRequestApproverDAO.findApproverComments(chngReqId, targetAppvrFlg, tenantId);
		} catch (RuntimeException e) {
			LOGGER.error("Error while fetching change request approver comments");
			throw new OpservDataAccessException("Error while fetching change request approver comments",e);
		}
	}

}
