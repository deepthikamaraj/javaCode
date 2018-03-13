package com.cognizant.opserv.sp.cr.process.internal.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.opserv.sp.common.ChangeRequestTriggerType;
import com.cognizant.opserv.sp.common.MetricTriggerType;
import com.cognizant.opserv.sp.cr.process.dto.CustomerAlignmentDTO;
import com.cognizant.opserv.sp.cr.process.dto.ErrorCode;
import com.cognizant.opserv.sp.cr.process.dto.ErrorDTO;
import com.cognizant.opserv.sp.cr.process.dto.MirrorCustAlgmtDTO;
import com.cognizant.opserv.sp.cr.process.internal.service.CallPlanOfflineService;
import com.cognizant.opserv.sp.cr.process.internal.service.ChangeRequestOfflineService;
import com.cognizant.opserv.sp.cr.process.internal.service.CustomerAlignmentValidationService;
import com.cognizant.opserv.sp.cr.process.internal.service.CustomerProductAlignmentOfflineService;
import com.cognizant.opserv.sp.cr.process.internal.service.CustomerPushOfflineService;
import com.cognizant.opserv.sp.cr.process.internal.service.MetricOfflineService;
import com.cognizant.opserv.sp.cr.process.internal.service.OfflineRequestService;
import com.cognizant.opserv.sp.cr.process.internal.service.ProductAlignmentService;
import com.cognizant.opserv.sp.exception.AffiliationServiceException;
import com.cognizant.opserv.sp.exception.AlignmentServiceException;
import com.cognizant.opserv.sp.exception.AlignmentServiceException.AlignmentServiceExceptionCode;
import com.cognizant.opserv.sp.exception.CallPlanServiceException;
import com.cognizant.opserv.sp.exception.ChangeRequestServiceException;
import com.cognizant.opserv.sp.exception.CustomerServiceException;
import com.cognizant.opserv.sp.exception.CustomerServiceException.CustomerServiceExceptionCode;
import com.cognizant.opserv.sp.exception.MetricExecutionException;
import com.cognizant.opserv.sp.exception.MetricViolationException;
import com.cognizant.opserv.sp.exception.SalesPositionServiceException;
import com.cognizant.opserv.sp.exception.ViewServiceException;
import com.cognizant.opserv.sp.model.Customer;
import com.cognizant.opserv.sp.model.CustomerAlignment;
import com.cognizant.opserv.sp.model.CustomerCallPlan;
import com.cognizant.opserv.sp.model.CustomerProductAlignment;
import com.cognizant.opserv.sp.model.ProductAlignment;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.model.cr.ChangeRequest;
import com.cognizant.opserv.sp.model.cr.ChangeRequestDefinition;
import com.cognizant.opserv.sp.model.cr.CustomerAlignmentChangeRequestDetails;
import com.cognizant.opserv.sp.model.metric.MetricResult;
import com.cognizant.opserv.sp.persistence.dao.service.CustomerAlignmentDAOService;
import com.cognizant.opserv.sp.persistence.dao.service.CustomerDAOService;
import com.cognizant.opserv.sp.persistence.dao.service.CustomerProductAlignmentDAOService;
import com.cognizant.opserv.sp.persistence.dao.service.GeographyAlignmentDAOService;
import com.cognizant.opserv.sp.persistence.dao.service.ProductAlignmentDAOService;
import com.cognizant.opserv.sp.service.common.ChangeRequestCommonService;
import com.cognizant.opserv.sp.service.common.CustomerAlignmentCommonService;
import com.cognizant.peg.core.exception.OpservDataAccessException;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;


/**
 * ****************************************************************************.
 * 
 * @class CustomerPushOfflineService
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 * ***************************************************************************
 */
@Service
public class CustomerPushOfflineServiceImpl implements CustomerPushOfflineService {
	

	 /**
	 * LOGGER
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(CustomerPushOfflineServiceImpl.class);

	/**
	 * crCustomerService
	 */
	@Autowired
	private ChangeRequestOfflineService crCustomerService;
	
	/**
	 * callPlanOfflineService
	 */
	@Autowired 
	private CallPlanOfflineService callPlanOfflineService;
	
	/**
	 * custAlgmtValidationService
	 */
	@Autowired
	private CustomerAlignmentValidationService custAlgmtValidationService;
	
	/**
	 * crCommonService
	 */
	@Autowired
	private ChangeRequestCommonService crCommonService;
	
	/**
	 * crOfflineService
	 */
	@Autowired
	private ChangeRequestOfflineService crOfflineService;

	/**
	 * custAlgnmtCommonService
	 */
	@Autowired
	private CustomerAlignmentCommonService custAlgnmtCommonService;
	
	/**
	 * metricCommonService
	 */
	@Autowired
	private MetricOfflineService mtrOffServ;
	
	/**
	 * customerDAOService
	 */
	@Autowired
	private CustomerDAOService customerDAOService;
	
	/**
	 * geographyAlignmentDAOService
	 */
	@Autowired
	private GeographyAlignmentDAOService geographyAlignmentDAOService;
	
	/**
	 * customerAlignmentDAOService
	 */
	@Autowired
	private CustomerAlignmentDAOService customerAlignmentDAOService;

	/**
	 * productAlignmentDAOService
	 */
	@Autowired
	private ProductAlignmentDAOService productAlignmentDAOService;
	
	/**
	 * customerProductAlignmentService
	 */
	@Autowired
	private CustomerProductAlignmentOfflineService customerProductAlignmentService;
	
	/**
	 * productAlignmentService
	 */
	@Autowired
	private ProductAlignmentService productAlignmentService;
	
	/**
	 * customerproductAlignmentDAOService
	 */
	@Autowired
	private CustomerProductAlignmentDAOService customerproductAlignmentDAOService;
	
	/**
	 * customer Product Alignment Offline Service
	 */
	@Autowired
	private CustomerProductAlignmentOfflineService custProdAlgmtOfflineService;
	
	/**
	 * offline Request Service
	 */
	@Autowired
	private OfflineRequestService offlineRequestService;
	
	/**
	 * Lock customer alignment- Update Change Request Status Type to
	 * PENDING_FOR_SUBMISSION.
	 * 
	 * @param customerAlgmt
	 *            the customer algmt
	 * @param userDetails
	 *            the user details
	 * @return true, if successful
	 * @throws AlignmentServiceException 
	 * @throws ViewServiceException 
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW , rollbackFor = AlignmentServiceException.class)
	public boolean lockCustomerAlignment(CustomerAlignment customerAlgmt, UserDetails userDetails) throws AlignmentServiceException, ViewServiceException {
		LOGGER.info("========= Execute of lock Customer Alignment ==============");
		if(customerAlgmt != null && userDetails != null && userDetails.getTenantId() != null && userDetails.getUserId() != null){
			try {
				return custAlgnmtCommonService.lockCustomerAlignment(customerAlgmt, userDetails);
			} catch(AlignmentServiceException e){
				LOGGER.error("********************Issue occurred while Locking the Customer Alignment ****************** ");
				Object[] args = new Object[] {"CustomerAlignment" };
				throw new AlignmentServiceException(AlignmentServiceExceptionCode.ALGN_SER_EX_CD_257,"This Issue occurred while Locking the Customer Alignment", args, e);
			}
		} else {
			Object[] args = new Object[] { "Customer Algmnt or TenantId or UserId is null" };
			throw new AlignmentServiceException(AlignmentServiceExceptionCode.ALGN_SER_EX_CD_332,"customerAlgmnt or TenantId or UserId is null", args, null);
		}
	}
	
	
	
	
	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW , rollbackFor = AlignmentServiceException.class)
	public boolean unLockCustomerAlignment(CustomerAlignment customerAlgmnt, UserDetails userDetails) throws AlignmentServiceException, ViewServiceException {
		LOGGER.info("========= Execute of lock Customer Alignment ==============");
		
		try {
			if(customerAlgmnt != null && userDetails != null && userDetails.getTenantId() != null && userDetails.getUserId() != null){
					return custAlgnmtCommonService.unLockCustomerAlignment(customerAlgmnt, userDetails);
			} else {
				Object[] args = new Object[] { "customerAlgmnt or TenantId or UserId is null" };
				throw new AlignmentServiceException(args);
			}
			
		} catch(AlignmentServiceException e){
			LOGGER.error("********************Issue occurred while Locking the Customer Alignment ****************** ");
			Object[] args = new Object[] {"CustomerAlignment" };
			throw new AlignmentServiceException(AlignmentServiceExceptionCode.ALGN_SER_EX_CD_258,"This Issue occurred while UnLocking the Customer Alignment", args, e);
		}
	}




	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW , rollbackFor = AlignmentServiceException.class)
	public boolean unLockCustomerAlignments(List<CustomerAlignment> customerAlgmnts, UserDetails userDetails) throws AlignmentServiceException, ViewServiceException {
		LOGGER.info("========= Execute of lock Customer Alignment ==============");
		
		try {
			if(customerAlgmnts != null && userDetails != null && userDetails.getTenantId() != null && userDetails.getUserId() != null){
					return custAlgnmtCommonService.unLockCustomerAlignments(customerAlgmnts, userDetails);
			} else {
				Object[] args = new Object[] { "customerAlgmnts or TenantId or UserId is null" };
				throw new AlignmentServiceException(args);
			}
			
		} catch(AlignmentServiceException e){
			LOGGER.error("********************Issue occurred while Locking the Customer Alignment ****************** ");
			Object[] args = new Object[] {"CustomerAlignment" };
			throw new AlignmentServiceException(AlignmentServiceExceptionCode.ALGN_SER_EX_CD_258,"This Issue occurred while UnLocking the Customer Alignments", args, e);
		}
	}




	/**
	 * processSourceOnSubmit -Process Line Items for Source Customer
	 * 
	 * @param customerAlgmtDTO
	 *            -Customer Alignment DTO
	 * @param userDetails
	 *            -UserDetails
	 * @throws ChangeRequestServiceException
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW , rollbackFor = ChangeRequestServiceException.class)
	public void processSourceOnSubmit(CustomerAlignmentDTO customerAlgmtDTO, UserDetails userDetails) throws ChangeRequestServiceException {
		List<CustomerAlignmentChangeRequestDetails> crLineItems = new ArrayList<CustomerAlignmentChangeRequestDetails>();
		//TODO : break the customer algmt dto into line items
		LOGGER.info("========= Execute of processSourceOnSubmit -- started ");
		
		LOGGER.info("========= Adding Root Customer Alignment with Base for Line Items to be created -- started ");
		CustomerAlignmentChangeRequestDetails custAlChngReqDetail = new CustomerAlignmentChangeRequestDetails();
		custAlChngReqDetail.setOldCustomerAlignment(customerAlgmtDTO.getSourceBaseCustAlgmt());
		custAlChngReqDetail.setParentChangeRequest(customerAlgmtDTO.getMainCR());
		crLineItems.add(custAlChngReqDetail);
		LOGGER.info("========= Line Items to be created for Root Customer with Base is ===" + crLineItems.size());
		LOGGER.info("========= Adding Root Customer Alignment with Base for Line Items to be created -- ended ");
		
		
		LOGGER.info("========= Adding Affiliated Customer Alignments with Base for Line Items to be created -- started ");
		List<CustomerAlignment> sourceBaseAffCustAlgmts = customerAlgmtDTO.getSourceBaseAffCustAlgmts();
		if(sourceBaseAffCustAlgmts!= null && !sourceBaseAffCustAlgmts.isEmpty()){
			for (CustomerAlignment customerAlignment : sourceBaseAffCustAlgmts) {
				CustomerAlignmentChangeRequestDetails custAlChngReqDetailFrAffCust = new CustomerAlignmentChangeRequestDetails();
				custAlChngReqDetailFrAffCust.setOldCustomerAlignment(customerAlignment);
				custAlChngReqDetailFrAffCust.setParentChangeRequest(customerAlgmtDTO.getMainCR());
				crLineItems.add(custAlChngReqDetailFrAffCust);
			}
		}
		LOGGER.info("========= Line Items to be created for Affiliated Customers with Base is ===" + crLineItems.size());
		LOGGER.info("========= Adding Affiliated Customer Alignments with Base for Line Items to be created -- ended ");
		
		LOGGER.info("========= Adding Root and Affiliated Customer Alignments with Mirrors for Line Items to be created -- started ");
		List<MirrorCustAlgmtDTO> mirrors = customerAlgmtDTO.getMirrors();
		if (mirrors != null && !mirrors.isEmpty()) {
			for (MirrorCustAlgmtDTO mirCustAlgmntDTO : mirrors) {
				
				if (mirCustAlgmntDTO.getSourceMirrorCustAlgmts() != null
						&& !mirCustAlgmntDTO.getSourceMirrorCustAlgmts().isEmpty()) {
					
					for (CustomerAlignment sourceMirrorCustAlgmt : mirCustAlgmntDTO
							.getSourceMirrorCustAlgmts()) {
						CustomerAlignmentChangeRequestDetails custAlChngReqDetailFrRoot_AffCust = new CustomerAlignmentChangeRequestDetails();
						custAlChngReqDetailFrRoot_AffCust
								.setOldCustomerAlignment(sourceMirrorCustAlgmt);
						custAlChngReqDetailFrRoot_AffCust
								.setParentChangeRequest(mirCustAlgmntDTO
										.getChildCR());
						crLineItems.add(custAlChngReqDetailFrRoot_AffCust);
					}
				}
			}
		}
		LOGGER.info("========= Line Items to be created for Root and Affiliated Customer Alignments with Mirrors is ===" + crLineItems.size());
		LOGGER.info("========= Adding Root and Affiliated Customer Alignments with Mirrors for Line Items to be created -- ended ");
		customerAlgmtDTO.setCrLineItemsForSource(crLineItems);
		crCustomerService.processLineItemForSourceCustomer(crLineItems, userDetails);
		LOGGER.info("========= Execute of processSourceOnSubmit -- ended ");
	}
	
	/**
	 * processTargetOnSubmit -Process Line Items for Target Customer
	 * 
	 * @param customerAlgmtDTO
	 *            -Customer Alignment DTO
	 * @param userDetails
	 *            -UserDetails
	 * @throws ChangeRequestServiceException
	 * @throws CallPlanServiceException 
	 * @throws CustomerServiceException 
	 * @throws AlignmentServiceException 
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW , rollbackFor = {ChangeRequestServiceException.class, CallPlanServiceException.class})
	public void processTargetOnSubmit(CustomerAlignmentDTO customerAlgmtDTO, UserDetails userDetails) throws ChangeRequestServiceException,
			CallPlanServiceException, CustomerServiceException, AlignmentServiceException {

		// TODO : break the customer algmt dto into line items
		List<CustomerAlignmentChangeRequestDetails> crLineItems = new ArrayList<CustomerAlignmentChangeRequestDetails>();
		List<CustomerAlignment> savedAffCustomerAlignmentList = new ArrayList<CustomerAlignment>();
		List<CustomerAlignment> savedMirrCustomerAlignmentList = new ArrayList<CustomerAlignment>();

		// Insert to cust alg
		LOGGER.info("========= Insert into TCustAlgmnt table --- started=======");
		CustomerAlignment savedBaseCustAlgnmt = this.insertCustAlDatafrTagetSp(customerAlgmtDTO, savedAffCustomerAlignmentList, savedMirrCustomerAlignmentList,
				crLineItems, userDetails);
		LOGGER.info("========= Insert into TCustAlgmnt table --- ended=======");

		// Insert to cust prod alg
		LOGGER.info("========= Insert into TCustPrdAlgmnt table --- started=======");
		this.insertCustPrdAlDataFrTagerSP(customerAlgmtDTO, savedBaseCustAlgnmt, savedAffCustomerAlignmentList, savedMirrCustomerAlignmentList, userDetails);
		LOGGER.info("========= Insert into TCustPrdAlgmnt table --- started=======");

		// Insert to call plan
		LOGGER.info("========= Insert into TCustCallPlan table --- started=======");
		this.insertCustCallPlanDataFrTagerSP(customerAlgmtDTO, savedBaseCustAlgnmt, savedAffCustomerAlignmentList, savedMirrCustomerAlignmentList, userDetails);
		LOGGER.info("========= Insert into TCustCallPlan table --- started=======");

		customerAlgmtDTO.setCrLineItemsForTarget(crLineItems);
		crCustomerService.processLineItemForTargetCustomer(crLineItems, userDetails);

	}

	/**
	 * validate - To validate the customer
	 * 
	 * @param custAlgmtDTO
	 *            the custAlgmtDTO
	 * @param userDetails
	 *            the userDetails
	 * @throws AffiliationServiceException 
	 * @throws CallPlanServiceException
	 * @throws AlignmentServiceException 
	 * 
	 */
	@Override
	@Transactional(readOnly=true)
	public void validate(CustomerAlignmentDTO custAlgmtDTO, UserDetails userDetails) throws AffiliationServiceException, AlignmentServiceException, SalesPositionServiceException {

		LOGGER.info("========= Validate Date Overlap for Target Customer Alignment For PUSH CUSTOMER --- started=======");
		try {
			offlineRequestService.validateDateOverlapForTarCustAlgmnt(custAlgmtDTO.getTargetBaseCustAlgmt(), userDetails);
		} catch (AlignmentServiceException ae) {
			LOGGER.error("Validate error due to Date Overlapping in Push", ae);
			ErrorDTO error = new ErrorDTO();
			error.addParam("Customer Id", custAlgmtDTO.getSourceBaseCustAlgmt().getCustomer().getId());
			error.setErrorCode(ErrorCode.ERROR_CP_2024);
			custAlgmtDTO.addErrorDTO(error);
			// TODO : set the app error code and error msg in the DTO
			return;
		}
		LOGGER.info("========= Validate Date Overlap for Target Customer Alignment --- ended=======");

		// Affliation Validation
		try {
			LOGGER.info("========= Validate Affiliated Customer Alignment for PUSH CUSTOMER--- started=======");
			custAlgmtValidationService.validateAffiliatedCustomerAlignment(custAlgmtDTO, userDetails);
			LOGGER.info("========= Validate Affiliated Customer Alignment for PUSH CUSTOMER--- ended=======");
		} catch (AlignmentServiceException | AffiliationServiceException e) {
			LOGGER.error("Error while fetching Affiliated Customer for Push", e.getMessage());
			ErrorDTO error = new ErrorDTO();
			error.addParam("Customer Id", custAlgmtDTO.getSourceBaseCustAlgmt().getCustomer().getId());
			error.setErrorCode(ErrorCode.ERROR_CP_2034);
			custAlgmtDTO.addErrorDTO(error);
			return;
		}

		// Mirror Validation
		try {
			LOGGER.info("========= Validate Mirrors Customer Alignment for PUSH CUSTOMER--- started=======");
			custAlgmtValidationService.validateMirrorsForCustomerAlignment(custAlgmtDTO, userDetails);
			LOGGER.info("========= Validate Mirrors Customer Alignment for PUSH CUSTOMER--- ended=======");
		} catch (AlignmentServiceException e) {
			LOGGER.error("Error while fetching Mirrors for Customers", e);
			ErrorDTO error = new ErrorDTO();
			error.addParam("Customer Id", custAlgmtDTO.getSourceBaseCustAlgmt().getCustomer().getId());
			error.setErrorCode(ErrorCode.ERROR_CP_2035);
			custAlgmtDTO.addErrorDTO(error);
			return;
		}

		try {
			LOGGER.info("========= Populate Call Plan for PUSH CUSTOMER--- started=======");
			callPlanOfflineService.populateCallPlan(custAlgmtDTO, userDetails);
			LOGGER.info("========= Populate Call Plan for PUSH CUSTOMER--- ended=======");
		} catch (CallPlanServiceException cpe) {
			LOGGER.error("Error while fetching Call Plan for Push", cpe);
			ErrorDTO err = new ErrorDTO();
			err.setErrorCode(ErrorCode.ERROR_CP_2030);
			custAlgmtDTO.addErrorDTO(err);
			return;
			// TODO : set the app error code and error msg in the DTO
		}

		LOGGER.info("========= Populate Customer Product Alignment for PUSH CUSTOMER--- started=======");
		try {
			custProdAlgmtOfflineService.populateCustomerProductAlignment(custAlgmtDTO, userDetails);
		} catch (AlignmentServiceException e) {
			LOGGER.error("Issue occurred while fetching Customer Product Alignment for Push", e);
			ErrorDTO err = new ErrorDTO();
			err.setErrorCode(ErrorCode.ERROR_CP_2031);
			custAlgmtDTO.addErrorDTO(err);
			return;
		}
		LOGGER.info("========= Populate Customer Product Alignment for PUSH CUSTOMER--- ended=======");

	}

	
	/**
	 * handleMetricsOnSubmit - To handle metrics on submit
	 * 
	 * @param custAlgmtDTO
	 *            the custAlgmtDTO
	 * @param userDetails
	 *            the userDetails
	 * 
	 * 
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW , rollbackFor = {MetricViolationException.class, AlignmentServiceException.class,MetricExecutionException.class})
	public boolean handleMetricsOnSubmit(CustomerAlignmentDTO custAlgmtDTO, UserDetails userDetails) {
		
		// Calculate the metrics - synchronous call
		// Check for soft and hard metric violations.
		// For any errors fill the error DTO in the custAlgmtDTO
		// For any hard metric violation return false, else return true.
		// Auto reject the Main CR - See below code

		/// Below code is a placeholder. TODO  : to be reviewed
		
		/*metricCommonService.processCalculativeMetrics(custAlgmtDTO.getSourceBaseCustAlgmt().getSalesPosition(), MetricTriggerType.UNASSIGN_CUSTOMER, userDetails);
		metricCommonService.processCalculativeMetrics(custAlgmtDTO.getTargetBaseCustAlgmt().getSalesPosition(), MetricTriggerType.UNASSIGN_CUSTOMER, userDetails);
		
		List<MetricResult> sourceMetricResults = metricCommonService.getMetricResults(custAlgmtDTO.getSourceBaseCustAlgmt().getSalesPosition(), MetricTriggerType.UNASSIGN_CUSTOMER, userDetails);
		List<MetricResult> targetMetricResults = metricCommonService.getMetricResults(custAlgmtDTO.getTargetBaseCustAlgmt().getSalesPosition(), MetricTriggerType.UNASSIGN_CUSTOMER, userDetails);*/
		Boolean metricResult = false;
		try {
			List<MetricResult> metricResultSource = null;
			List<MetricResult> metricResultTarget = null;
			LOGGER.info("Before Calculating metrics... ChangeRequestProcessInternalServiceImpl --> submitChangeRequestTransaction :: chngReq:" + custAlgmtDTO.getMainCR().getId());
			
			ChangeRequestDefinition definition = new ChangeRequestDefinition();
			definition.setTrigger(ChangeRequestTriggerType.PUSH_CUSTOMER);
			custAlgmtDTO.getMainCR().setCrDefinition(definition);
			custAlgmtDTO.getMainCR().setRequestedSalesPosition(custAlgmtDTO.getSourceBaseCustAlgmt().getSalesPosition());
			custAlgmtDTO.getMainCR().setRaisedSalesPosition(custAlgmtDTO.getTargetBaseCustAlgmt().getSalesPosition());
			calcuateProcessMetrics(custAlgmtDTO.getMainCR(), userDetails);
			LOGGER.info("After Calculating metrics... ChangeRequestProcessInternalServiceImpl --> submitChangeRequestTransaction :: chngReq:" + custAlgmtDTO.getMainCR().getId());
			metricResult = changeRequestMetricsResults(custAlgmtDTO.getMainCR(), metricResultSource, metricResultTarget, userDetails);
			LOGGER.info("metricResult... ChangeRequestProcessInternalServiceImpl --> submitChangeRequestTransaction:" + metricResult);
		}
		catch (MetricViolationException exception) {
			List<ErrorDTO> errors = new ArrayList<ErrorDTO>();
			ErrorDTO errorDTO = new ErrorDTO();
			errorDTO.setErrorCode(ErrorCode.ERROR_2014);
			errors.add(errorDTO);
			custAlgmtDTO.setErrors(errors);
		}
		catch (AlignmentServiceException exeption) {
			List<ErrorDTO> errors = new ArrayList<ErrorDTO>();
			ErrorDTO errorDTO = new ErrorDTO();
			errorDTO.setErrorCode(ErrorCode.ERROR_2015);
			errors.add(errorDTO);
			custAlgmtDTO.setErrors(errors);
		}
		catch (MetricExecutionException exeption) {
			List<ErrorDTO> errors = new ArrayList<ErrorDTO>();
			ErrorDTO errorDTO = new ErrorDTO();
			errorDTO.setErrorCode(ErrorCode.ERROR_2016);
			errors.add(errorDTO);
			custAlgmtDTO.setErrors(errors);
		}
		return metricResult;
	}

	/**
	 * calcuateProcessMetrics-to calculate Process metrics
	 * 
	 * @param chngReq
	 *            -chngReq
	 * @param metricResultSource
	 *            -metricResultSource
	 * @param metricResultTarget
	 *            -metricResultTarget
	 * @return Boolean
	 * @throws MetricExecutionException 
	 */
	private void calcuateProcessMetrics(ChangeRequest chngReq, UserDetails userDetails) throws MetricExecutionException {
		LOGGER.info("Inside calcuateProcessMetrics");
		if (chngReq.getCrDefinition().getTrigger().getId().equals(ChangeRequestTriggerType.PUSH_CUSTOMER.getId())) {
			mtrOffServ.processCalculativeMetrics(chngReq.getRequestedSalesPosition(), MetricTriggerType.UNASSIGN_CUSTOMER, userDetails);
			mtrOffServ.processCalculativeMetrics(chngReq.getRaisedSalesPosition(), MetricTriggerType.ASSIGN_CUSTOMER, userDetails);
		} else if (chngReq.getCrDefinition().getTrigger().getId().equals(ChangeRequestTriggerType.PUSH_ZIP.getId())) {
			mtrOffServ.processCalculativeMetrics(chngReq.getRequestedSalesPosition(), MetricTriggerType.UNASSIGN_CUSTOMER, userDetails);
			mtrOffServ.processCalculativeMetrics(chngReq.getRaisedSalesPosition(), MetricTriggerType.ASSIGN_CUSTOMER, userDetails);
		} 
	}

	/**
	 * changeRequestMetricsResults- To change Metrics Results
	 * 
	 * @param chngReq
	 *            -chngReq
	 * @param metricResultSource
	 *            -metricResultSource
	 * @param metricResultTarget
	 *            -metricResultTarget
	 * @throws AlignmentServiceException
	 * @return Boolean
	 * @throws MetricViolationException
	 */
	private Boolean changeRequestMetricsResults(ChangeRequest chngReq, List<MetricResult> metricResultSource, List<MetricResult> metricResultTarget,
			UserDetails userDetails) throws MetricViolationException, AlignmentServiceException {
		Boolean result = false;
		if (chngReq.getCrDefinition().getTrigger().getId().equals(ChangeRequestTriggerType.PUSH_CUSTOMER.getId())) {
			result = mtrOffServ.checkCustomerPushMetricViolation(chngReq.getRequestedSalesPosition(), chngReq.getRaisedSalesPosition(), userDetails);
		} else if (chngReq.getCrDefinition().getTrigger().getId().equals(ChangeRequestTriggerType.PUSH_ZIP.getId())) {
			result = mtrOffServ.checkZipMovementMetricViolation(chngReq.getRequestedSalesPosition(), chngReq.getRaisedSalesPosition(), userDetails);
		}
		return result;
	}

	/**
	 * emitPostProcessEvents -Emits Post Process Events
	 * 
	 * @param customerAlgmtDTO
	 *            -Customer Alignment DTO
	 * @param userDetails
	 *            -user Details
	 */
	@Override
	public void emitPostProcessEvents(CustomerAlignmentDTO custAlgmtDTO, UserDetails userDetails) {
		//TODO : Submit events for CR , customer
		
	}

	/**
	 * updateChangeRequests -updates Change Request
	 * 
	 * @param customerAlgmtDTO
	 *            -Customer Alignment DTO
	 * @param userDetails
	 *            -user Details
	 * @throws ChangeRequestServiceException
	 */
	@Override
	public void updateChangeRequests(CustomerAlignmentDTO custAlgmtDTO,
			UserDetails userDetails) throws ChangeRequestServiceException {
		// Update the main CR and mirror CR to Pending Approval
		// Initiate the workflow for those CRs
		crCustomerService.updateCRandInitiateWorkflow(custAlgmtDTO, userDetails);
	}

	/**
	 * createMirrorCR -Creates The Mirror CR
	 * 
	 * @param parentCR
	 *            -parent CR
	 * @param mirrorDTO
	 *            -mirror DTO
	 * @param userDetails
	 *            -user Details
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW,rollbackFor = {AlignmentServiceException.class , ChangeRequestServiceException.class})
	public boolean createMirrorCR(ChangeRequest parentCR, MirrorCustAlgmtDTO mirrorDTO, UserDetails userDetails) {
		LOGGER.info("================Create Mirror CR=============--Started" +parentCR.getId());
		if(parentCR == null) {
			return false;
		}
		synchronized(parentCR) {
			try {
				ChangeRequest childCR = crCommonService.generatePushCustomerChangeRequest(parentCR,mirrorDTO.getSourceSP(), mirrorDTO.getTargetSP(), userDetails);
				mirrorDTO.setChildCR(childCR);
			} catch(AlignmentServiceException | ChangeRequestServiceException cre) {
				return false;
			}
		}
		LOGGER.info("================Create Mirror CR=============--Ended" );
		return true;
	}
	/**
	 * getMirrorCRsForPush -Gets The Mirror CR fro Push
	 * 
	 * @param customerAlgmtDTO
	 *            -Customer Alignment DTO
	 * @param userDetails
	 *            -user Details
	 * @throws ChangeRequestServiceException
	 */
	@Override
	public void getMirrorCRsForPush(CustomerAlignmentDTO custAlgmtDTO, UserDetails userDetails) {
		LOGGER.info("================Get Mirror CR for Push Started=============" );
		List<ChangeRequest> mirrorCRs = null;
		try {
			mirrorCRs = crCommonService.findMirrorCRs(custAlgmtDTO.getMainCR(), userDetails);
		} catch(ChangeRequestServiceException cse) {
			LOGGER.error("Error finding mirror cr for push",cse);
			//TODO : Put a error DTO for this.
			ErrorDTO error = new ErrorDTO();
			// TODO : Set appropriate code ..
			error.addParam("Customer Id", custAlgmtDTO.getSourceBaseCustAlgmt().getCustomer().getId());
			error.setErrorCode(ErrorCode.ERROR_CP_2032);
			custAlgmtDTO.addErrorDTO(error);
			return;
		}
		if(mirrorCRs != null && !mirrorCRs.isEmpty()){
			for(ChangeRequest mirrorCR : mirrorCRs) {
				for(MirrorCustAlgmtDTO mirrorDTO : custAlgmtDTO.getMirrors()) {
					if(mirrorDTO.getSourceSP().getId().equals(mirrorCR.getRaisedSalesPosition().getId()) && 
					   mirrorDTO.getTargetSP().getId().equals(mirrorCR.getRequestedSalesPosition().getId())) {
						mirrorDTO.setChildCR(mirrorCR);
						break;
					}
				}
			}
		}
		LOGGER.info("================Get Mirror CR for Push Ended=============" );
	}


	/**
	 * assignApproversForSourceAndTarget -Assigns approvers for Source and Target
	 * 
	 * @param customerAlgmtDTO
	 *            -Customer Alignment DTO
	 * @param userDetails
	 *            -user Details
	 * @throws ChangeRequestServiceException
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW , rollbackFor = ChangeRequestServiceException.class)
	public void assignApproversForSourceAndTarget(CustomerAlignmentDTO custAlgmtDTO, UserDetails userDetails) throws ChangeRequestServiceException {
		// Assigning approvers for the base (source and target)
		LOGGER.info("================Assign approvers for Source Started=============" );
		crOfflineService.assignApproversForSource(custAlgmtDTO.getMainCR(), custAlgmtDTO.getSourceBaseCustAlgmt().getSalesPosition(), userDetails);
		LOGGER.info("================Assign approvers for Source Ended=============" );
		
		LOGGER.info("================Assign approvers for Target Started=============" );
		crOfflineService.assignApproversForTarget(custAlgmtDTO.getMainCR(), custAlgmtDTO.getTargetBaseCustAlgmt().getSalesPosition(), userDetails);
		LOGGER.info("================Assign approvers for Target Ended=============" );
	}

	/**
	 * Gets the geo algmntflag.
	 * 
	 * @param custId
	 *            the cust id
	 * @param userDetails
	 *            the user details
	 * @param spId
	 *            the sp id
	 * @return the geo algmntflag
	 * @throws CustomerServiceException
	 *             the customer service exception
	 * @throws AlignmentServiceException
	 *             the alignment service exception
	 */
	
	// To be removed from old code
	public Boolean getGeoAlgmntflag(Long custId, UserDetails userDetails, Long spId, Long shId) throws CustomerServiceException, AlignmentServiceException {
		Boolean geoAlgmntFlag = false;
		Customer customerDetails = null;
		try {
			// To get the value of geoAlgmntFlag to be set for all the customer ids
			LOGGER.info("************To set the Geo Algmnt Flag for Customer Id and SP started *********");
			customerDetails = customerDAOService.getCustomerDetails(custId, userDetails);
		} catch (OpservDataAccessException e) {
			LOGGER.error("Error during fetching customer details by custid", e);
			Object[] args = new Object[] {custId};
			throw new CustomerServiceException(CustomerServiceExceptionCode.CUST_SER_EX_CD_001, "Error during fetching customer details by custid", args, e);
		}
		try{
			if (customerDetails != null) {
				if (customerDetails.getPrimaryAddress() != null && !customerDetails.getPrimaryAddress().getPostalCode().trim().equals("")) {
					String postalCode = customerDetails.getPrimaryAddress().getPostalCode();

					geoAlgmntFlag = geographyAlignmentDAOService.getSPDataFrPostalCd(postalCode, spId, shId, userDetails);
				}
				// custIdGeoAlgmntFlgMap.put(custId, geoAlgmntFlag);
			}
			LOGGER.info("************To set the Geo Algmnt Flag for Customer Id and SP ended *********");
		} catch (OpservDataAccessException e) {
			LOGGER.error("This issue is occurred while Fetching PostalCodes for Aligned SalesPosition", e);
			Object[] args = new Object[] {custId};
			throw new AlignmentServiceException(AlignmentServiceExceptionCode.ALGN_SER_EX_CD_407, "This issue is occurred while Fetching PostalCodes for Aligned SalesPosition", args, e);
		}
		return geoAlgmntFlag;
	}
	
	/**
	 * insertCustAlDatafrTagetSp -Inserts Customer Alignment Data for Target
	 * SalesPosition
	 * 
	 * @param customerAlgmtDTO
	 *            -CustomerAlignment DTO
	 * @param savedAffCustomerAlignmentList
	 *            -Affiliation Customer List
	 * @param savedMirrCustomerAlignmentList
	 *            -Mirror Customer List
	 * @param crLineItems
	 *            -CR line Items
	 * @param userDetails
	 *            -UserDetails
	 * @return CustomerAlignment
	 * @throws CustomerServiceException 
	 * @throws AlignmentServiceException 
	 */
	public CustomerAlignment insertCustAlDatafrTagetSp(CustomerAlignmentDTO customerAlgmtDTO, List<CustomerAlignment> savedAffCustomerAlignmentList,
			List<CustomerAlignment> savedMirrCustomerAlignmentList, List<CustomerAlignmentChangeRequestDetails> crLineItems, UserDetails userDetails) throws CustomerServiceException, AlignmentServiceException {
		LOGGER.info("========= Insert into TCustAlgmnt table for Target Base CustAlgmt --- started=======");
		Boolean geoAlgmntflag = false;

		geoAlgmntflag = this.getGeoAlgmntflag(customerAlgmtDTO.getSourceBaseCustAlgmt().getCustomer().getId(), userDetails, customerAlgmtDTO
				.getTargetBaseCustAlgmt().getSalesPosition().getId(), customerAlgmtDTO.getTargetBaseCustAlgmt().getSalesPosition().getHierarchy().getId());

		CustomerAlignmentChangeRequestDetails custAlChngReqDetail = new CustomerAlignmentChangeRequestDetails();
		CustomerAlignment savedBaseCustAlgnmt = customerAlignmentDAOService.saveCustomerAlignments(customerAlgmtDTO.getTargetBaseCustAlgmt(), userDetails,
				geoAlgmntflag, customerAlgmtDTO.getSourceBaseCustAlgmt());

		custAlChngReqDetail.setNewCustomerAlignment(savedBaseCustAlgnmt);
		custAlChngReqDetail.setParentChangeRequest(customerAlgmtDTO.getMainCR());
		crLineItems.add(custAlChngReqDetail);

		LOGGER.info("========= Insert into TCustAlgmnt table for Target Base CustAlgmt --- ended=======");

		LOGGER.info("========= Insert into TCustAlgmnt table for Target Base Affiliated CustAlgmt --- started =======");
		if (customerAlgmtDTO.getSourceBaseAffCustAlgmts() != null && !customerAlgmtDTO.getSourceBaseAffCustAlgmts().isEmpty()) {
			for (CustomerAlignment sourceBaseAffCustAlgmt : customerAlgmtDTO.getSourceBaseAffCustAlgmts()) {
				CustomerAlignmentChangeRequestDetails custAlChngReqDetailFrAff = new CustomerAlignmentChangeRequestDetails();
				geoAlgmntflag = this.getGeoAlgmntflag(sourceBaseAffCustAlgmt.getCustomer().getId(), userDetails, customerAlgmtDTO.getTargetBaseCustAlgmt()
						.getSalesPosition().getId(), customerAlgmtDTO.getTargetBaseCustAlgmt().getSalesPosition().getHierarchy().getId());
				CustomerAlignment saveAffCustomerAlignment = customerAlignmentDAOService.saveCustomerAlignments(customerAlgmtDTO.getTargetBaseCustAlgmt(),
						userDetails, geoAlgmntflag, sourceBaseAffCustAlgmt);

				custAlChngReqDetailFrAff.setNewCustomerAlignment(saveAffCustomerAlignment);
				custAlChngReqDetailFrAff.setParentChangeRequest(customerAlgmtDTO.getMainCR());
				crLineItems.add(custAlChngReqDetailFrAff);

				savedAffCustomerAlignmentList.add(saveAffCustomerAlignment);
			}
		}

		LOGGER.info("========= Insert into TCustAlgmnt table for Target Base Affiliated CustAlgmt --- ended =======");

		LOGGER.info("========= Insert into TCustAlgmnt table for Target Mirror CustAlgmt --- started=======");
		if (customerAlgmtDTO.getMirrors() != null && !customerAlgmtDTO.getMirrors().isEmpty()) {

			CustomerAlignment targetMirrorCustAlgmt = new CustomerAlignment();
			targetMirrorCustAlgmt.setStartDate(customerAlgmtDTO.getTargetBaseCustAlgmt().getStartDate());
			targetMirrorCustAlgmt.setEndDate(customerAlgmtDTO.getTargetBaseCustAlgmt().getEndDate());

			for (MirrorCustAlgmtDTO mirCustAlgmntDTO : customerAlgmtDTO.getMirrors()) {

				if (mirCustAlgmntDTO.getSourceMirrorCustAlgmts() != null && !mirCustAlgmntDTO.getSourceMirrorCustAlgmts().isEmpty()) {
					if (mirCustAlgmntDTO.getTargetSP() != null) {
						targetMirrorCustAlgmt.setSalesPosition(mirCustAlgmntDTO.getTargetSP());

						for (CustomerAlignment sourceMirrorCustAlgmt : mirCustAlgmntDTO.getSourceMirrorCustAlgmts()) {
							CustomerAlignmentChangeRequestDetails custAlChngReqDetailFrMirr = new CustomerAlignmentChangeRequestDetails();
							geoAlgmntflag = this.getGeoAlgmntflag(sourceMirrorCustAlgmt.getCustomer().getId(), userDetails, mirCustAlgmntDTO.getTargetSP().getId(),
									mirCustAlgmntDTO.getTargetSP().getHierarchy().getId());

							CustomerAlignment saveCustomerAlignmentFrMir = customerAlignmentDAOService.saveCustomerAlignments(targetMirrorCustAlgmt,
									userDetails, geoAlgmntflag, sourceMirrorCustAlgmt);
							savedMirrCustomerAlignmentList.add(saveCustomerAlignmentFrMir);

							custAlChngReqDetailFrMirr.setNewCustomerAlignment(saveCustomerAlignmentFrMir);
							custAlChngReqDetailFrMirr.setParentChangeRequest(mirCustAlgmntDTO.getChildCR());
							crLineItems.add(custAlChngReqDetailFrMirr);
						}
					}
				}
			}

		}
		LOGGER.info("========= Insert into TCustAlgmnt table for Target Mirror CustAlgmt --- ended=======");
		return savedBaseCustAlgnmt;
	}
	
	/**
	 * insertCustPrdAlDataFrTagerSP -Inserts Customer Alignment Product Data for Target
	 * SalesPosition
	 * 
	 * @param customerAlgmtDTO
	 *            -CustomerAlignment DTO
	 * @param savedAffCustomerAlignmentList
	 *            -Affiliation Customer List
	 * @param savedMirrCustomerAlignmentList
	 *            -Mirror Customer List
	 * @param savedBaseCustAlgnmt
	 *            -Base Customer Alignment List
	 * @param userDetails
	 *            -UserDetails
	 * @return CustomerAlignment
	 */
	public void insertCustPrdAlDataFrTagerSP(CustomerAlignmentDTO customerAlgmtDTO, CustomerAlignment savedBaseCustAlgnmt,
			List<CustomerAlignment> savedAffCustomerAlignmentList, List<CustomerAlignment> savedMirrCustomerAlignmentList, UserDetails userDetails) {
		List<ProductAlignment> prdAlgmntFrTarMirSps = null;

		LOGGER.info("========= Fetch the Products assigned for the Base Target SP(Product Alignment) from TPrdAlgmnt table--- started=======");
		List<ProductAlignment> prdAlgmntFrTarBaseSps = productAlignmentService.getProductAlignments(customerAlgmtDTO.getTargetBaseCustAlgmt()
				.getSalesPosition(), userDetails);
		LOGGER.info("size of prdAlgmntFrTarMirSps for Mirror Target SP " + customerAlgmtDTO.getTargetBaseCustAlgmt().getSalesPosition().getId() + " is "
				+ prdAlgmntFrTarBaseSps.size());
		LOGGER.info("========= Fetch the Products assigned for the Base Target SP(Product Alignment) from TPrdAlgmnt table--- ended=======");

		LOGGER.info("========= Fetch the Products assigned for the Mirror Target SP(Product Alignment) from TPrdAlgmnt table--- started=======");
		if (customerAlgmtDTO.getMirrors() != null && !customerAlgmtDTO.getMirrors().isEmpty()) {
			prdAlgmntFrTarMirSps = new ArrayList<ProductAlignment>();
			for (MirrorCustAlgmtDTO mirrorCustAlgmtDTO : customerAlgmtDTO.getMirrors()) {
				if (mirrorCustAlgmtDTO.getTargetSP() != null) {
					prdAlgmntFrTarMirSps.addAll(productAlignmentService.getProductAlignments(mirrorCustAlgmtDTO.getTargetSP(), userDetails));
					LOGGER.info("size of prdAlgmntFrTarMirSps for Mirror Target SP " + mirrorCustAlgmtDTO.getTargetSP().getId() + " is "
							+ prdAlgmntFrTarMirSps.size());
				}
			}
		}
		LOGGER.info("========= Fetch the Products assigned for the Mirror Target SP(Product Alignment) from TPrdAlgmnt table--- ended=======");

		LOGGER.info("========= Get the Customer Product Alignment set for Root Customer Alignments(Base) --- started=======");
		if (customerAlgmtDTO.getSourceOldCustProdAlgmts() != null && !customerAlgmtDTO.getSourceOldCustProdAlgmts().isEmpty()) {
			for (CustomerProductAlignment sourceCustProdAlgmt : customerAlgmtDTO.getSourceOldCustProdAlgmts()) {
				for (ProductAlignment productAlignment : prdAlgmntFrTarBaseSps) {
					if (sourceCustProdAlgmt.getProduct().getId().equals(productAlignment.getProduct().getId())) {
						customerproductAlignmentDAOService.saveCustomerProductAlignmnets(savedBaseCustAlgnmt, productAlignment, sourceCustProdAlgmt);
					}
				}
			}
		}
		LOGGER.info("========= Get the Customer Product Alignment set for Root Customer Alignments(Base) --- started=======");

		LOGGER.info("========= Get the Customer Product Alignment set for Affiliated Customer Alignments(Base) --- started=======");
		if (customerAlgmtDTO.getAffCustProdAlgmts() != null && !customerAlgmtDTO.getAffCustProdAlgmts().isEmpty()) {
			for (CustomerProductAlignment affCustProdAlgmt : customerAlgmtDTO.getAffCustProdAlgmts()) {
				for (ProductAlignment productAlignment : prdAlgmntFrTarBaseSps) {
					if (affCustProdAlgmt.getProduct().getId().equals(productAlignment.getProduct().getId())) {
						for (CustomerAlignment saveAffCustomerAlignment : savedAffCustomerAlignmentList) {
							if (affCustProdAlgmt.getCustomer().getId().equals(saveAffCustomerAlignment.getCustomer().getId())) {
								customerproductAlignmentDAOService.saveCustomerProductAlignmnets(saveAffCustomerAlignment, productAlignment, affCustProdAlgmt);
							}
						}
					}
				}

			}
		}
		LOGGER.info("========= Get the Customer Product Alignment set for Affiliated Customer Alignments(Base) --- started=======");

		LOGGER.info("========= Get the Customer Product Alignment set for Mirror Customer Alignments(Root + Affiliated Customers) --- started=======");

		if (customerAlgmtDTO.getMirrors() != null && !customerAlgmtDTO.getMirrors().isEmpty()) {
			for (MirrorCustAlgmtDTO mirrorCustAlgmtDTO : customerAlgmtDTO.getMirrors()) {
				Long algmntId = mirrorCustAlgmtDTO.getSourceSP().getAlignmment().getId();
				if (mirrorCustAlgmtDTO.getSourcemirrorCustProdAlgmts() != null && !mirrorCustAlgmtDTO.getSourcemirrorCustProdAlgmts().isEmpty()) {
					for (CustomerProductAlignment sourcemirrorCustProdAlgmt : mirrorCustAlgmtDTO.getSourcemirrorCustProdAlgmts()) {
						for (ProductAlignment productAlignment : prdAlgmntFrTarMirSps) {
							if (sourcemirrorCustProdAlgmt.getProduct().getId().equals(productAlignment.getProduct().getId())) {
								for (CustomerAlignment saveCustomerAlignmentFrMir : savedMirrCustomerAlignmentList) {
									if (sourcemirrorCustProdAlgmt.getCustomer().getId().equals(saveCustomerAlignmentFrMir.getCustomer().getId())
											&& productAlignment.getSalesPosition().getAlignmment().getId()
													.equals(saveCustomerAlignmentFrMir.getSalesPosition().getAlignmment().getId())
											&& algmntId.equals(saveCustomerAlignmentFrMir.getSalesPosition().getAlignmment().getId())) {
										customerproductAlignmentDAOService.saveCustomerProductAlignmnets(saveCustomerAlignmentFrMir, productAlignment,
												sourcemirrorCustProdAlgmt);
									}
								}

							}

						}
					}
				}

			}

		}
		LOGGER.info("========= Get the Customer Product Alignment set for Mirror Customer Alignments(Root + Affiliated Customers) --- ended=======");

	}

	/**
	 * To insert Data to CallPlan
	 * 
	 * @param customerAlgmtDTO
	 *            - the customer Alignment DTO
	 * @param savedBaseCustAlgnmt
	 *            - Base Customer Alignment
	 * @param savedAffCustomerAlignmentList
	 *            -Affiliation Customer Alignment
	 * @param savedMirrCustomerAlignmentList
	 *            - Mirror Customer Alignment
	 * @param userDetails
	 *            -userDetails
	 * @throws CallPlanServiceException
	 */
	public void insertCustCallPlanDataFrTagerSP(
			CustomerAlignmentDTO customerAlgmtDTO,
			CustomerAlignment savedBaseCustAlgnmt,
			List<CustomerAlignment> savedAffCustomerAlignmentList,
			List<CustomerAlignment> savedMirrCustomerAlignmentList,
			UserDetails userDetails) throws CallPlanServiceException {

		List<CustomerCallPlan> customerCallPlanList = new ArrayList<CustomerCallPlan>();

		LOGGER.info("========= Call Plans for Root Customer============");
		if (customerAlgmtDTO.getSourceBaseCustAlgmt().getBaseCustomerCallPlan() != null) {
			customerCallPlanList.add(customerAlgmtDTO.getSourceBaseCustAlgmt()
					.getBaseCustomerCallPlan());
		}

		if (customerAlgmtDTO.getSourceBaseCustAlgmt().getCustomerCallPlan() != null) {
			customerCallPlanList.add(customerAlgmtDTO.getSourceBaseCustAlgmt()
					.getCustomerCallPlan());
		}

		LOGGER.info("========= Insert Call Plans for Root Customer with Base SP ============");
			if(customerCallPlanList != null && !customerCallPlanList.isEmpty()){
		        callPlanOfflineService.createCallPlan(customerCallPlanList,
				savedBaseCustAlgnmt, userDetails);
		}


		LOGGER.info("========= Call Plans for Affiliated Customers with Base SP ============");
		if (savedAffCustomerAlignmentList != null
				&& !savedAffCustomerAlignmentList.isEmpty()) {
			
			for (CustomerAlignment savedBaseAffCustAlgmt : savedAffCustomerAlignmentList) {
				// CustAlIdList.add(sourceBaseAffCustAlgmt.getId());
				if (customerAlgmtDTO.getSourceBaseAffCustAlgmts() != null
						&& !customerAlgmtDTO.getSourceBaseAffCustAlgmts()
								.isEmpty()) {
					for (CustomerAlignment custAlgnmnt : customerAlgmtDTO
							.getSourceBaseAffCustAlgmts()) {
						if (custAlgnmnt
								.getCustomer()
								.getId()
								.equals(savedBaseAffCustAlgmt.getCustomer()
										.getId())) {
							List<CustomerCallPlan> customerCallPlanBaseAffList = new ArrayList<CustomerCallPlan>();
							if (custAlgnmnt.getBaseCustomerCallPlan() != null) {
								customerCallPlanBaseAffList.add(custAlgnmnt
										.getBaseCustomerCallPlan());
							}
							if (custAlgnmnt.getCustomerCallPlan() != null) {
								customerCallPlanBaseAffList.add(custAlgnmnt
										.getCustomerCallPlan());
							}

							if (customerCallPlanBaseAffList != null
									&& !customerCallPlanBaseAffList.isEmpty()) {
								LOGGER.info("========= Insert Call Plans for Affiliated Customers with Base SP for CustomerAlgmntId============"
										+ savedBaseAffCustAlgmt.getId());
								callPlanOfflineService.createCallPlan(
										customerCallPlanBaseAffList,
										savedBaseAffCustAlgmt, userDetails);
							}
						}
					}
				}
			}

		}

		LOGGER.info("========= Call Plans for Affiliated Customers and Root Customer with Mirror SPs ============");
		if (savedMirrCustomerAlignmentList != null
				&& !savedMirrCustomerAlignmentList.isEmpty()) {
			// CustAlIdList.add(sourceMirrorCustAlgmt.getId());
			if (customerAlgmtDTO.getMirrors() != null
					&& !customerAlgmtDTO.getMirrors().isEmpty()) {
				for (MirrorCustAlgmtDTO custAlgnmntMirrorDTO : customerAlgmtDTO
						.getMirrors()) {
					if (custAlgnmntMirrorDTO.getSourceMirrorCustAlgmts() != null
							&& !custAlgnmntMirrorDTO
									.getSourceMirrorCustAlgmts().isEmpty()) {
						for (CustomerAlignment sourceMirrorCustAlgmt : custAlgnmntMirrorDTO
								.getSourceMirrorCustAlgmts()) {
							for (CustomerAlignment savedMirrorCustAlgmt : savedMirrCustomerAlignmentList) {

								if (sourceMirrorCustAlgmt
										.getCustomer()
										.getId()
										.equals(savedMirrorCustAlgmt
												.getCustomer().getId())
										&& sourceMirrorCustAlgmt
												.getSalesPosition()
												.getAlignmment()
												.getId()
												.equals(savedMirrorCustAlgmt
														.getSalesPosition()
														.getAlignmment()
														.getId())) {

									List<CustomerCallPlan> customerCallPlanMirrList = new ArrayList<CustomerCallPlan>();
									if (sourceMirrorCustAlgmt
											.getBaseCustomerCallPlan() != null) {
										customerCallPlanMirrList
												.add(sourceMirrorCustAlgmt
														.getBaseCustomerCallPlan());
									}
									if (sourceMirrorCustAlgmt
											.getCustomerCallPlan() != null) {
										customerCallPlanMirrList
												.add(sourceMirrorCustAlgmt
														.getCustomerCallPlan());
									}

									if (customerCallPlanMirrList != null
											&& !customerCallPlanMirrList
													.isEmpty()) {
										LOGGER.info("========= Insert Call Plans for Affiliated Customers and Root Customer with Mirror SPs for CustomerAlgmntId============"
												+ savedMirrorCustAlgmt.getId());
										callPlanOfflineService.createCallPlan(
												customerCallPlanMirrList,
												savedMirrorCustAlgmt,
												userDetails);
									}
								}

							}
						}
					}
				}
			}
		}
	}
	
	
}
