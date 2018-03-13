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
import com.cognizant.opserv.sp.cr.process.internal.service.CustomerPullOfflineService;
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
import com.cognizant.opserv.sp.service.common.ChangeRequestCommonService;
import com.cognizant.opserv.sp.service.common.CustomerAlignmentCommonService;
import com.cognizant.peg.core.exception.OpservDataAccessException;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;

/**
 * ****************************************************************************.
 * 
 * @class CustomerPullOfflineServiceImpl
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 23/09/2016
 *        ************************************************************
 *        ***************
 */
@Service
public class CustomerPullOfflineServiceImpl implements CustomerPullOfflineService {

	/** The Constant LOGGER. */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(CustomerPullOfflineServiceImpl.class);

	/** The cr customer service. */
	@Autowired
	private ChangeRequestOfflineService crCustomerService;

	/** The call plan offline service. */
	@Autowired
	private CallPlanOfflineService callPlanOfflineService;

	/** The cust algmt validation service. */
	@Autowired
	private CustomerAlignmentValidationService custAlgmtValidationService;

	/** The cust algnmt common service. */
	@Autowired
	private CustomerAlignmentCommonService custAlgnmtCommonService;

	/** The cr offline service. */
	@Autowired
	private ChangeRequestOfflineService crOfflineService;

	/** The metric common service. */
	@Autowired
	private MetricOfflineService metricOfflineService;

	/** The cr common service. */
	@Autowired
	private ChangeRequestCommonService crCommonService;

	/** The cust prod algmt offline service. */
	@Autowired
	private CustomerProductAlignmentOfflineService custProdAlgmtOfflineService;

	/** productAlignmentService. */
	@Autowired
	private ProductAlignmentService productAlignmentService;

	/** customerproductAlignmentDAOService. */
	@Autowired
	private CustomerProductAlignmentDAOService customerproductAlignmentDAOService;

	/** customerAlignmentDAOService. */
	@Autowired
	private CustomerAlignmentDAOService customerAlignmentDAOService;

	/** customerService. */
	@Autowired
	private CustomerDAOService customerDAOService;

	/** geographyAlignmentDAOService. */
	@Autowired
	private GeographyAlignmentDAOService geographyAlignmentDAOService;

	/**
	 * offline Request Service
	 */
	@Autowired
	private OfflineRequestService offlineRequestService;

	@Override
	public void lockRequestForProcessing(Long offlineId, UserDetails userDetails) {
		// TODO Auto-generated method stub
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public boolean lockCustomerAlignment(CustomerAlignment customerAlgmt, UserDetails userDetails) throws AlignmentServiceException, ViewServiceException {
		LOGGER.info("========= Execute of lock Customer Alignment ==============");
		
		try {
			if(customerAlgmt != null && userDetails != null && userDetails.getTenantId() != null && userDetails.getUserId() != null){
					return custAlgnmtCommonService.lockCustomerAlignment(customerAlgmt, userDetails);
			} else {
				Object[] args = new Object[] { "customerAlgmnt or TenantId or UserId is null" };
				throw new AlignmentServiceException(args);
			}
			
		} catch(AlignmentServiceException e){
			LOGGER.error("********************Issue occurred while Locking the Customer Alignment ****************** ");
			Object[] args = new Object[] {"CustomerAlignment" };
			throw new AlignmentServiceException(AlignmentServiceExceptionCode.ALGN_SER_EX_CD_257,"This Issue occurred while Locking the Customer Alignment", args, e);
		}

	}

	/**
	 * Process source on submit.
	 * 
	 * @param customerAlgmtDTO
	 *            the customer algmt dto
	 * @param userDetails
	 *            the user details
	 * @throws ChangeRequestServiceException
	 *             the change request service exception
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void processSourceOnSubmit(CustomerAlignmentDTO customerAlgmtDTO, UserDetails userDetails) throws ChangeRequestServiceException {
		List<CustomerAlignmentChangeRequestDetails> crLineItems = new ArrayList<CustomerAlignmentChangeRequestDetails>();
		// TODO : break the customer algmt dto into line items
		LOGGER.info("========= Execute of processSourceOnSubmit For PULL-- started ");

		LOGGER.info("========= Adding Root Customer Alignment with Base for Line Items to be created For PULL-- started ");
		CustomerAlignmentChangeRequestDetails custAlChngReqDetail = new CustomerAlignmentChangeRequestDetails();
		custAlChngReqDetail.setOldCustomerAlignment(customerAlgmtDTO.getSourceBaseCustAlgmt());
		custAlChngReqDetail.setParentChangeRequest(customerAlgmtDTO.getMainCR());
		crLineItems.add(custAlChngReqDetail);
		LOGGER.info("========= Line Items to be created for Root Customer with Base is ===" + crLineItems.size());
		LOGGER.info("========= Adding Root Customer Alignment with Base for Line Items to be created For PULL-- ended ");

		LOGGER.info("========= Adding Affiliated Customer Alignments with Base for Line Items to be created For PULL-- started ");
		List<CustomerAlignment> sourceBaseAffCustAlgmts = customerAlgmtDTO.getSourceBaseAffCustAlgmts();
		if (sourceBaseAffCustAlgmts != null && !sourceBaseAffCustAlgmts.isEmpty()) {
			for (CustomerAlignment customerAlignment : sourceBaseAffCustAlgmts) {
				CustomerAlignmentChangeRequestDetails custAlChngReqDetailFrAffCust = new CustomerAlignmentChangeRequestDetails();
				custAlChngReqDetailFrAffCust.setOldCustomerAlignment(customerAlignment);
				custAlChngReqDetailFrAffCust.setParentChangeRequest(customerAlgmtDTO.getMainCR());
				crLineItems.add(custAlChngReqDetailFrAffCust);
			}
		}
		LOGGER.info("========= Line Items to be created for Affiliated Customers with Base is ===" + crLineItems.size());
		LOGGER.info("========= Adding Affiliated Customer Alignments with Base for Line Items to be created For PULL-- ended ");

		LOGGER.info("========= Adding Root and Affiliated Customer Alignments with Mirrors for Line Items to be created For PULL-- started ");
		List<MirrorCustAlgmtDTO> mirrors = customerAlgmtDTO.getMirrors();
		if (mirrors != null && !mirrors.isEmpty()) {
			for (MirrorCustAlgmtDTO mirCustAlgmntDTO : mirrors) {

				if (mirCustAlgmntDTO.getSourceMirrorCustAlgmts() != null && !mirCustAlgmntDTO.getSourceMirrorCustAlgmts().isEmpty()) {

					for (CustomerAlignment sourceMirrorCustAlgmt : mirCustAlgmntDTO.getSourceMirrorCustAlgmts()) {
						CustomerAlignmentChangeRequestDetails custAlChngReqDetailFrRoot_AffCust = new CustomerAlignmentChangeRequestDetails();
						custAlChngReqDetailFrRoot_AffCust.setOldCustomerAlignment(sourceMirrorCustAlgmt);
						custAlChngReqDetailFrRoot_AffCust.setParentChangeRequest(mirCustAlgmntDTO.getChildCR());
						crLineItems.add(custAlChngReqDetailFrRoot_AffCust);
					}
				}
			}
		}
		LOGGER.info("========= Line Items to be created for Root and Affiliated Customer Alignments with Mirrors is ===" + crLineItems.size());
		LOGGER.info("========= Adding Root and Affiliated Customer Alignments with Mirrors for Line Items to be created For PULL -- ended ");
		customerAlgmtDTO.setCrLineItemsForSource(crLineItems);
		crCustomerService.processLineItemForSourceCustomer(crLineItems, userDetails);
	}

	/**
	 * Process target on submit.
	 * 
	 * @param customerAlgmtDTO
	 *            the customer algmt dto
	 * @param userDetails
	 *            the user details
	 * @throws ChangeRequestServiceException
	 *             the change request service exception
	 * @throws CallPlanServiceException
	 *             the call plan service exception
	 * @throws CustomerServiceException 
	 * @throws AlignmentServiceException 
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void processTargetOnSubmit(CustomerAlignmentDTO customerAlgmtDTO, UserDetails userDetails) throws ChangeRequestServiceException,
			CallPlanServiceException, CustomerServiceException, AlignmentServiceException {

		// TODO : break the customer algmt dto into line items

		List<CustomerAlignmentChangeRequestDetails> crLineItems = new ArrayList<CustomerAlignmentChangeRequestDetails>();
		List<CustomerAlignment> savedAffCustomerAlignmentList = new ArrayList<CustomerAlignment>();
		List<CustomerAlignment> savedMirrCustomerAlignmentList = new ArrayList<CustomerAlignment>();

		// Insert to cust alg

		LOGGER.info("========= Insert into TCustAlgmnt table For PULL--- started=======");
		CustomerAlignment savedBaseCustAlgnmt = this.insertCustAlDatafrTagetSp(customerAlgmtDTO, savedAffCustomerAlignmentList, savedMirrCustomerAlignmentList,
				crLineItems, userDetails);
		LOGGER.info("========= Insert into TCustAlgmnt table For PULL--- ended=======");

		// Insert to cust prod alg

		LOGGER.info("========= Insert into TCustPrdAlgmnt table For PULL--- started=======");
		this.insertCustPrdAlDataFrTagerSP(customerAlgmtDTO, savedBaseCustAlgnmt, savedAffCustomerAlignmentList, savedMirrCustomerAlignmentList, userDetails);
		LOGGER.info("========= Insert into TCustPrdAlgmnt table For PULL--- ended=======");

		// Insert to Call Plan

		LOGGER.info("========= Insert into TCustCallPlan table For PULL--- started=======");
		this.insertCustCallPlanDataFrTagerSP(customerAlgmtDTO, savedBaseCustAlgnmt, savedAffCustomerAlignmentList, savedMirrCustomerAlignmentList, userDetails);
		LOGGER.info("========= Insert into TCustCallPlan table For PULL--- ended=======");

		customerAlgmtDTO.setCrLineItemsForTarget(crLineItems);
		// callPlanOfflineService.createCallPlan(custCallPlans, userDetails);
		LOGGER.info("========= Process Line Item For Target Customer For PULL--- started=======");
		crCustomerService.processLineItemForTargetCustomer(crLineItems, userDetails);
		LOGGER.info("========= Process Line Item For Target Customer For PULL--- ended=======");

	}

	/**
	 * Validate.
	 * 
	 * @param customerAlgmtDTO
	 *            the customer algmt dto
	 * @param userDetails
	 *            the user details
	 * @throws CallPlanServiceException
	 *             the call plan service exception
	 * @throws AlignmentServiceException
	 * @throws AffiliationServiceException 
	 */
	@Override
	@Transactional(readOnly = true)
	public void validate(CustomerAlignmentDTO custAlgmtDTO, UserDetails userDetails) throws AffiliationServiceException, AlignmentServiceException, SalesPositionServiceException {

		try {
			LOGGER.info("========= Validate Date Overlap for Target Customer Alignment --- started=======");
			offlineRequestService.validateDateOverlapForTarCustAlgmnt(custAlgmtDTO.getTargetBaseCustAlgmt(), userDetails);
			LOGGER.info("========= Validate Date Overlap for Target Customer Alignment --- ended=======");
		} catch (AlignmentServiceException ae) {
			LOGGER.error("Validate error due to Date Overlapping in Pull", ae);
			ErrorDTO error = new ErrorDTO();
			error.addParam("Customer Id", custAlgmtDTO.getSourceBaseCustAlgmt().getCustomer().getId());
			error.setErrorCode(ErrorCode.ERROR_CPL_2040);
			custAlgmtDTO.addErrorDTO(error);
			return;
		}
		
		// Affliation Validation
		try {
			LOGGER.info("========= Validate Affiliated Customer Alignment for PULL CUSTOMER--- started=======");
			custAlgmtValidationService.validateAffiliatedCustomerAlignment(custAlgmtDTO, userDetails);
			LOGGER.info("========= Validate Affiliated Customer Alignment for PULL CUSTOMER--- ended=======");
		} catch (AlignmentServiceException | AffiliationServiceException e) {
			LOGGER.error("Error while fetching Affiliated Customer for Pull", e.getMessage());
			ErrorDTO error = new ErrorDTO();
			error.addParam("Customer Id", custAlgmtDTO.getSourceBaseCustAlgmt().getCustomer().getId());
			error.setErrorCode(ErrorCode.ERROR_CPL_2049);
			custAlgmtDTO.addErrorDTO(error);
			return;
		}

		// Mirror Validation
		try {
			LOGGER.info("========= Validate Mirrors Customer Alignment for PULL CUSTOMER--- started=======");
			custAlgmtValidationService.validateMirrorsForCustomerAlignment(custAlgmtDTO, userDetails);
			LOGGER.info("========= Validate Mirrors Customer Alignment for PULL CUSTOMER--- ended=======");
		} catch (AlignmentServiceException e) {
			LOGGER.error("Error while fetching Mirrors for Customers", e);
			ErrorDTO error = new ErrorDTO();
			error.addParam("Customer Id", custAlgmtDTO.getSourceBaseCustAlgmt().getCustomer().getId());
			error.setErrorCode(ErrorCode.ERROR_CPL_2050);
			custAlgmtDTO.addErrorDTO(error);
			return;
		}

		//Call Plan
		try {
			LOGGER.info("========= Populate Call Plan for PULL CUSTOMER--- started=======");
			callPlanOfflineService.populateCallPlan(custAlgmtDTO, userDetails);
			LOGGER.info("========= Populate Call Plan for PULL CUSTOMER--- ended=======");
		} catch (CallPlanServiceException cpe) {
			LOGGER.error("Error while fetching Call Plan for Pull", cpe);
			ErrorDTO error = new ErrorDTO();
			error.addParam("Customer Id", custAlgmtDTO.getSourceBaseCustAlgmt().getCustomer().getId());
			error.setErrorCode(ErrorCode.ERROR_CPL_2046);
			custAlgmtDTO.addErrorDTO(error);
			return;
		}

		// Customer Product Alignment
		try {
			LOGGER.info("========= Populate Customer Product Alignment for PULL CUSTOMER--- started=======");
			custProdAlgmtOfflineService.populateCustomerProductAlignment(custAlgmtDTO, userDetails);
			LOGGER.info("========= Populate Customer Product Alignment for PULL CUSTOMER--- ended=======");
		} catch (AlignmentServiceException e) {
			LOGGER.error("Error while fetching Customer Product Alignment for Pull", e);
			ErrorDTO error = new ErrorDTO();
			error.addParam("Customer Id", custAlgmtDTO.getSourceBaseCustAlgmt().getCustomer().getId());
			error.setErrorCode(ErrorCode.ERROR_CPL_2047);
			custAlgmtDTO.addErrorDTO(error);
			return;
		}

		/*
		 * //TODO : this need to be revisited. ArrayList<String> fatalErrorCodes
		 * = new ArrayList<String>();
		 * fatalErrorCodes.add("ACCD");fatalErrorCodes.add("SSSD"); for(ErrorDTO
		 * err : custAlgmtDTO.getErrors()) {
		 * if(fatalErrorCodes.contains(err.getErrorCode())) { //Throw validation
		 * exception } }
		 */

	}

	/**
	 * Handle metrics on submit.
	 * 
	 * @param customerAlgmtDTO
	 *            the customer algmt dto
	 * @param userDetails
	 *            the user details
	 * @return the boolean
	 * @throws MetricExecutionException 
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW , rollbackFor = {MetricViolationException.class,AlignmentServiceException.class,MetricExecutionException.class})
	public boolean handleMetricsOnSubmit(CustomerAlignmentDTO custAlgmtDTO, UserDetails userDetails){

		// Calculate the metrics - synchronous call
		// Check for soft and hard metric violations.
		// For any errors fill the error DTO in the custAlgmtDTO
		// For any hard metric violation return false, else return true.
		// Auto reject the Main CR - See below code

		// / Below code is a placeholder. TODO : to be reviewed

		Boolean metricResult = false;
		try {
			List<MetricResult> metricResultSource = null;
			List<MetricResult> metricResultTarget = null;
			LOGGER.info("Before Calculating metrics... ChangeRequestProcessInternalServiceImpl --> submitChangeRequestTransaction :: chngReq:"
					+ custAlgmtDTO.getMainCR().getId());

			ChangeRequestDefinition definition = new ChangeRequestDefinition();
			definition.setTrigger(ChangeRequestTriggerType.PULL_CUSTOMER);
			custAlgmtDTO.getMainCR().setCrDefinition(definition);
			custAlgmtDTO.getMainCR().setRequestedSalesPosition(custAlgmtDTO.getSourceBaseCustAlgmt().getSalesPosition());
			custAlgmtDTO.getMainCR().setRaisedSalesPosition(custAlgmtDTO.getTargetBaseCustAlgmt().getSalesPosition());
			calcuateProcessMetrics(custAlgmtDTO.getMainCR(), userDetails);
			LOGGER.info("After Calculating metrics... ChangeRequestProcessInternalServiceImpl --> submitChangeRequestTransaction :: chngReq:"
					+ custAlgmtDTO.getMainCR().getId());
			metricResult = changeRequestMetricsResults(custAlgmtDTO.getMainCR(), metricResultSource, metricResultTarget, userDetails);
			LOGGER.info("metricResult... ChangeRequestProcessInternalServiceImpl --> submitChangeRequestTransaction:" + metricResult);
		}catch (MetricViolationException exception) {
			List<ErrorDTO> errors = new ArrayList<ErrorDTO>();
			ErrorDTO errorDTO = new ErrorDTO();
			errorDTO.setErrorCode(ErrorCode.ERROR_2014);
			errors.add(errorDTO);
			custAlgmtDTO.setErrors(errors);
		}catch (AlignmentServiceException exeption) {
			List<ErrorDTO> errors = new ArrayList<ErrorDTO>();
			ErrorDTO errorDTO = new ErrorDTO();
			errorDTO.setErrorCode(ErrorCode.ERROR_2015);
			errors.add(errorDTO);
			custAlgmtDTO.setErrors(errors);
		}catch (MetricExecutionException exeption) {
			List<ErrorDTO> errors = new ArrayList<ErrorDTO>();
			ErrorDTO errorDTO = new ErrorDTO();
			errorDTO.setErrorCode(ErrorCode.ERROR_2016);
			errors.add(errorDTO);
			custAlgmtDTO.setErrors(errors);
		}
		return metricResult;

	}

	@Override
	public void emitPostProcessEvents(CustomerAlignmentDTO custAlgmtDTO, UserDetails userDetails) {
		// TODO : Submit events for CR , customer

	}

	/**
	 * Update change requests.
	 * 
	 * @param customerAlgmtDTO
	 *            the customer algmt dto
	 * @param userDetails
	 *            the user details
	 * @throws ChangeRequestServiceException
	 *             the change request service exception
	 */
	@Override
	public void updateChangeRequests(CustomerAlignmentDTO custAlgmtDTO, UserDetails userDetails) throws ChangeRequestServiceException {
		// Update the main CR and mirror CR to Pending Approval
		// Initiate the workflow for those CRs
		crCustomerService.updateCRandInitiateWorkflow(custAlgmtDTO, userDetails);
	}

	/**
	 * Creates the mirror cr.
	 * 
	 * @param parentCR
	 *            the parent cr
	 * @param mirrorDTO
	 *            the mirror dto
	 * @param userDetails
	 *            the user details
	 * @throws ChangeRequestServiceException
	 *             the change request service exception
	 * @throws AlignmentServiceException 
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW , rollbackFor = {ChangeRequestServiceException.class, AlignmentServiceException.class})
	public boolean createMirrorCR(ChangeRequest parentCR, MirrorCustAlgmtDTO mirrorDTO, UserDetails userDetails) throws ChangeRequestServiceException, AlignmentServiceException {
		if(parentCR == null) {
			return false;
		}
		synchronized(parentCR) {
			try {
		ChangeRequest childCR = crCommonService.generatePullCustomerChangeRequest(parentCR, mirrorDTO.getSourceSP(), mirrorDTO.getTargetSP(), userDetails);
		mirrorDTO.setChildCR(childCR);
			} catch(AlignmentServiceException | ChangeRequestServiceException cre) {
				return false;
			}
		}
		LOGGER.info("================Create Mirror CR=============--Ended" );
		return true;
	}

	/**
	 * Gets the mirror c rs for pull.
	 * 
	 * @param custAlgmtDTO
	 *            the cust algmt dto
	 * @param userDetails
	 *            the user details
	 * @return the mirror c rs for pull
	 * @throws ChangeRequestServiceException
	 *             the change request service exception
	 */
	@Override
	public void getMirrorCRsForPull(CustomerAlignmentDTO custAlgmtDTO, UserDetails userDetails) throws ChangeRequestServiceException {
		List<ChangeRequest> mirrorCRs = null;
		try {
			mirrorCRs = crCommonService.findMirrorCRs(custAlgmtDTO.getMainCR(), userDetails);
		} catch(ChangeRequestServiceException cse) {
			LOGGER.error("Error finding mirror cr for pull",cse);
			//TODO : Put a error DTO for this.
			ErrorDTO error = new ErrorDTO();
			// TODO : Set appropriate code ..
			error.addParam("Customer Id", custAlgmtDTO.getSourceBaseCustAlgmt().getCustomer().getId());
			error.setErrorCode(ErrorCode.ERROR_CPL_2048);
			custAlgmtDTO.addErrorDTO(error);
			return;
		}
		for (ChangeRequest mirrorCR : mirrorCRs) {
			for (MirrorCustAlgmtDTO mirrorDTO : custAlgmtDTO.getMirrors()) {
				if (mirrorDTO.getSourceSP() != null && mirrorDTO.getTargetSP() != null && mirrorDTO.getSourceSP().getId().equals(mirrorCR.getRequestedSalesPosition().getId())
						&& mirrorDTO.getTargetSP().getId().equals(mirrorCR.getRaisedSalesPosition().getId())) {
					mirrorDTO.setChildCR(mirrorCR);
					break;
				}
			}
		}
	}

	/**
	 * Assign approvers for source and target.
	 * 
	 * @param customerAlgmtDTO
	 *            the customer algmt dto
	 * @param userDetails
	 *            the user details
	 * @throws ChangeRequestServiceException
	 *             the change request service exception
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW ,rollbackFor = {ChangeRequestServiceException.class, AlignmentServiceException.class})
	public void assignApproversForSourceAndTarget(CustomerAlignmentDTO custAlgmtDTO, UserDetails userDetails) throws ChangeRequestServiceException {
		// Assigning approvers for the base (source and target)
		LOGGER.info("================Assign approvers for Source Started=============");
		crOfflineService.assignApproversForSource(custAlgmtDTO.getMainCR(), custAlgmtDTO.getSourceBaseCustAlgmt().getSalesPosition(), userDetails);
		LOGGER.info("================Assign approvers for Source Ended=============");

		LOGGER.info("================Assign approvers for Target Started=============");
		crOfflineService.assignApproversForTarget(custAlgmtDTO.getMainCR(), custAlgmtDTO.getTargetBaseCustAlgmt().getSalesPosition(), userDetails);
		LOGGER.info("================Assign approvers for Target Ended=============");
		// Assigning approvers for the mirrors
		/*
		 * for(MirrorCustAlgmtDTO mirror : custAlgmtDTO.getMirrors()) {
		 * crOfflineService.assignApproversForSource(mirror.getChildCR(),
		 * mirror.getSourceSP(), userDetails);
		 * crOfflineService.assignApproversForTarget(mirror.getChildCR(),
		 * mirror.getTargetSP(), userDetails); }
		 */

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
			List<CustomerAlignment> savedMirrCustomerAlignmentList, List<CustomerAlignmentChangeRequestDetails> crLineItems, UserDetails userDetails)
			throws CustomerServiceException, AlignmentServiceException {
		LOGGER.info("========= Insert into TCustAlgmnt table for Target Base CustAlgmt for PULL CUSTOMER--- started=======");
		Boolean geoAlgmntflag = false;

		geoAlgmntflag = this.getGeoAlgmntflag(customerAlgmtDTO.getSourceBaseCustAlgmt().getCustomer().getId(), userDetails, customerAlgmtDTO
				.getTargetBaseCustAlgmt().getSalesPosition().getId(), customerAlgmtDTO.getTargetBaseCustAlgmt().getSalesPosition().getHierarchy().getId());

		CustomerAlignmentChangeRequestDetails custAlChngReqDetail = new CustomerAlignmentChangeRequestDetails();
		CustomerAlignment savedBaseCustAlgnmt = customerAlignmentDAOService.saveCustomerAlignments(customerAlgmtDTO.getTargetBaseCustAlgmt(), userDetails,
				geoAlgmntflag, customerAlgmtDTO.getSourceBaseCustAlgmt());
		if (customerAlgmtDTO.getTargetBaseCustAlgmt().getExtdAttributes() != null) {
			savedBaseCustAlgnmt.setExtdAttributes(customerAlgmtDTO.getTargetBaseCustAlgmt().getExtdAttributes());
		}
		if (customerAlgmtDTO.getSourceOldCustProdAlgmts() != null) {
			custAlChngReqDetail.setOldCustomerProductAlignments(customerAlgmtDTO.getSourceOldCustProdAlgmts());
		}
		if (customerAlgmtDTO.getSourceUpdatedCustProdAlgmts() != null) {
			custAlChngReqDetail.setNewCustomerProductAlignments(customerAlgmtDTO.getSourceUpdatedCustProdAlgmts());
		}

		custAlChngReqDetail.setNewCustomerAlignment(savedBaseCustAlgnmt);
		custAlChngReqDetail.setParentChangeRequest(customerAlgmtDTO.getMainCR());
		crLineItems.add(custAlChngReqDetail);

		LOGGER.info("========= Insert into TCustAlgmnt table for Target Base CustAlgmt for PULL CUSTOMER--- ended=======");

		LOGGER.info("========= Insert into TCustAlgmnt table for Target Base Affiliated CustAlgmt for PULL CUSTOMER--- started =======");
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

		LOGGER.info("========= Insert into TCustAlgmnt table for Target Base Affiliated CustAlgmt for PULL CUSTOMER--- ended =======");

		LOGGER.info("========= Insert into TCustAlgmnt table for Target Mirror CustAlgmt for PULL CUSTOMER--- started=======");
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
		LOGGER.info("========= Insert into TCustAlgmnt table for Target Mirror CustAlgmt for PULL CUSTOMER--- ended=======");
		return savedBaseCustAlgnmt;
	}

	/**
	 * insertCustPrdAlDataFrTagerSP -Inserts Customer Alignment Product Data for
	 * Target SalesPosition
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
		
		LOGGER.info("========= Fetch the Products assigned for the Base Target SP(Product Alignment) from TPrdAlgmnt table for PULL CUSTOMER--- started=======");
		List<ProductAlignment> prdAlgmntFrTarBaseSps = productAlignmentService.getProductAlignments(customerAlgmtDTO.getTargetBaseCustAlgmt()
				.getSalesPosition(), userDetails);
		LOGGER.info("size of prdAlgmntFrTarMirSps for Mirror Target SP " + customerAlgmtDTO.getTargetBaseCustAlgmt().getSalesPosition().getId() + " is "
				+ prdAlgmntFrTarBaseSps.size());
		LOGGER.info("========= Fetch the Products assigned for the Base Target SP(Product Alignment) from TPrdAlgmnt table for PULL CUSTOMER--- ended=======");

		LOGGER.info("========= Fetch the Products assigned for the Mirror Target SP(Product Alignment) from TPrdAlgmnt table for PULL CUSTOMER--- started=======");
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
		LOGGER.info("========= Fetch the Products assigned for the Mirror Target SP(Product Alignment) from TPrdAlgmnt table for PULL CUSTOMER--- ended=======");

		LOGGER.info("========= Get the Customer Product Alignment set for Root Customer Alignments(Base) for PULL CUSTOMER--- started=======");
		if (customerAlgmtDTO.getSourceOldCustProdAlgmts() != null && !customerAlgmtDTO.getSourceOldCustProdAlgmts().isEmpty()) {
			for (CustomerProductAlignment sourceCustProdAlgmt : customerAlgmtDTO.getSourceOldCustProdAlgmts()) {
				for (ProductAlignment productAlignment : prdAlgmntFrTarBaseSps) {
					if (sourceCustProdAlgmt.getProduct().getId().equals(productAlignment.getProduct().getId())) {
						customerproductAlignmentDAOService.saveCustomerProductAlignmnets(savedBaseCustAlgnmt, productAlignment, sourceCustProdAlgmt);
					}
				}
			}
		}
		LOGGER.info("========= Get the Customer Product Alignment set for Root Customer Alignments(Base) for PULL CUSTOMER--- ended=======");

		LOGGER.info("========= Get the Customer Product Alignment set for Affiliated Customer Alignments(Base) for PULL CUSTOMER--- started=======");
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
		LOGGER.info("========= Get the Customer Product Alignment set for Affiliated Customer Alignments(Base) for PULL CUSTOMER--- ended=======");

		LOGGER.info("========= Get the Customer Product Alignment set for Mirror Customer Alignments(Root + Affiliated Customers) for PULL CUSTOMER --- started=======");

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
		LOGGER.info("========= Get the Customer Product Alignment set for Mirror Customer Alignments(Root + Affiliated Customers) for PULL CUSTOMER--- ended=======");

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
	public void insertCustCallPlanDataFrTagerSP(CustomerAlignmentDTO customerAlgmtDTO, CustomerAlignment savedBaseCustAlgnmt,
			List<CustomerAlignment> savedAffCustomerAlignmentList, List<CustomerAlignment> savedMirrCustomerAlignmentList, UserDetails userDetails)
			throws CallPlanServiceException {

		List<CustomerCallPlan> customerCallPlanList = new ArrayList<CustomerCallPlan>();

		// For Root Customer's CP - take Planned Call Plan data from UI, Base Call Plan
		// data from source
		LOGGER.info("========= Insert Call Plans for Root Customer with Base SP for PULL CUSTOMER --- started ============");
		if (customerAlgmtDTO.getTargetBaseCustAlgmt().getCustomerCallPlan() != null) {
			customerCallPlanList.add(customerAlgmtDTO.getTargetBaseCustAlgmt().getCustomerCallPlan());
		}

		if (customerAlgmtDTO.getSourceBaseCustAlgmt().getBaseCustomerCallPlan() != null) {
			customerCallPlanList.add(customerAlgmtDTO.getSourceBaseCustAlgmt().getBaseCustomerCallPlan());
		}

		if (null != customerCallPlanList && !customerCallPlanList.isEmpty()) {
			callPlanOfflineService.createCallPlan(customerCallPlanList, savedBaseCustAlgnmt, userDetails);
		}
		LOGGER.info("========= Insert Call Plans for Root Customer with Base SP for PULL CUSTOMER --- ended ============");

		// For Affiliated Customers' CP - take Planned Call Plan data from source
		LOGGER.info("========= Insert Call Plans for Affiliated Customers with Base SP started ============");
		if (savedAffCustomerAlignmentList != null && !savedAffCustomerAlignmentList.isEmpty()) {

			for (CustomerAlignment savedBaseAffCustAlgmt : savedAffCustomerAlignmentList) {
				if (customerAlgmtDTO.getSourceBaseAffCustAlgmts() != null && !customerAlgmtDTO.getSourceBaseAffCustAlgmts().isEmpty()) {
					for (CustomerAlignment custAlgnmnt : customerAlgmtDTO.getSourceBaseAffCustAlgmts()) {
						if (custAlgnmnt.getCustomer().getId().equals(savedBaseAffCustAlgmt.getCustomer().getId())) {
							List<CustomerCallPlan> customerCallPlanBaseAffList = new ArrayList<CustomerCallPlan>();
							if (custAlgnmnt.getBaseCustomerCallPlan() != null) {
								customerCallPlanBaseAffList.add(custAlgnmnt.getBaseCustomerCallPlan());
							}
							if (custAlgnmnt.getCustomerCallPlan() != null) {
								customerCallPlanBaseAffList.add(custAlgnmnt.getCustomerCallPlan());
							}

							if (customerCallPlanBaseAffList != null && !customerCallPlanBaseAffList.isEmpty()) {
								callPlanOfflineService.createCallPlan(customerCallPlanBaseAffList, savedBaseAffCustAlgmt, userDetails);
							}
						}
					}
				}
			}
		}
		LOGGER.info("========= Insert Call Plans for Affiliated Customers with Base SP ended ============");

		// For Mirror CPs - If the Base CP is getting changed from zero to non
		// zero, then
		// take Call Plan data from UI else take it from source.

		LOGGER.info("========= Insert Call Plans for Affiliated Customers and Root Customer with Mirror SPs started ============");
		if (savedMirrCustomerAlignmentList != null && !savedMirrCustomerAlignmentList.isEmpty()) {
			if (customerAlgmtDTO.getMirrors() != null && !customerAlgmtDTO.getMirrors().isEmpty()) {
				for (MirrorCustAlgmtDTO custAlgnmntMirrorDTO : customerAlgmtDTO.getMirrors()) {
					if (custAlgnmntMirrorDTO.getSourceMirrorCustAlgmts() != null && !custAlgnmntMirrorDTO.getSourceMirrorCustAlgmts().isEmpty()) {
						for (CustomerAlignment sourceMirrorCustAlgmt : custAlgnmntMirrorDTO.getSourceMirrorCustAlgmts()) {
							for (CustomerAlignment savedMirrorCustAlgmt : savedMirrCustomerAlignmentList) {

								if (sourceMirrorCustAlgmt.getCustomer().getId().equals(savedMirrorCustAlgmt.getCustomer().getId())
										&& sourceMirrorCustAlgmt.getSalesPosition().getAlignmment().getId()
												.equals(savedMirrorCustAlgmt.getSalesPosition().getAlignmment().getId())) {

									LOGGER.info("========= Base CP is changed from zero to a non zero --- =======");
									LOGGER.info("Source Base Customer Algmnt planned calls ===== "
											+ customerAlgmtDTO.getSourceBaseCustAlgmt().getCustomerCallPlan().getPlannedCalls()
											+ "and Target Base Customer Algmnt planned calls === "
											+ customerAlgmtDTO.getTargetBaseCustAlgmt().getCustomerCallPlan().getPlannedCalls());
									List<CustomerCallPlan> customerCallPlanMirrList = new ArrayList<CustomerCallPlan>();
									if(sourceMirrorCustAlgmt.getCustomer().getId().equals(customerAlgmtDTO.getSourceBaseCustAlgmt().getCustomer().getId())){
										// For Root Customer with Mirror SP
										LOGGER.info("=========== Source Mirror - Root Customer Alignment Call Plan =========" + "Customer ID=="
												+ sourceMirrorCustAlgmt.getCustomer().getId() + "Customer Algmnt Id" + sourceMirrorCustAlgmt.getId());
										if (customerAlgmtDTO.getTargetBaseCustAlgmt().getCustomerCallPlan() != null
												&& customerAlgmtDTO.getTargetBaseCustAlgmt().getCustomerCallPlan().getPlannedCalls() > 0
												&& customerAlgmtDTO.getSourceBaseCustAlgmt().getCustomerCallPlan() != null
												&& customerAlgmtDTO.getSourceBaseCustAlgmt().getCustomerCallPlan().getPlannedCalls() == 0) {
											
												LOGGER.info("=========== Source Mirror - Root Customer Alignment Call Plan is zero, hence take Call Plan data from UI ==============");
												if (customerAlgmtDTO.getTargetBaseCustAlgmt().getCustomerCallPlan() != null) {
													customerCallPlanMirrList.add(customerAlgmtDTO.getTargetBaseCustAlgmt().getCustomerCallPlan());
												}
	
											} else {
												LOGGER.info("=========== Source Mirror- Root Customer Alignment Call Plan is non zero, hence take Call Plan data from source ==============");
												if (sourceMirrorCustAlgmt.getCustomerCallPlan() != null) {
													customerCallPlanMirrList.add(sourceMirrorCustAlgmt.getCustomerCallPlan());
												}
											}
										
											if (sourceMirrorCustAlgmt.getBaseCustomerCallPlan() != null) {
												customerCallPlanMirrList.add(sourceMirrorCustAlgmt.getBaseCustomerCallPlan());
											}
										} else {
											
										// For Affiliated Customers with Mirror SP
										LOGGER.info("=========== Source Mirror - Affiliated Customer Alignment Call Plan =========" + "Customer ID=="
												+ sourceMirrorCustAlgmt.getCustomer().getId() + "Customer Algmnt Id" + sourceMirrorCustAlgmt.getId());
										if (sourceMirrorCustAlgmt.getCustomerCallPlan() != null) {
											customerCallPlanMirrList.add(sourceMirrorCustAlgmt.getCustomerCallPlan());
										}

										if (sourceMirrorCustAlgmt.getBaseCustomerCallPlan() != null) {
											customerCallPlanMirrList.add(sourceMirrorCustAlgmt.getBaseCustomerCallPlan());
										}
									}

									if (customerCallPlanMirrList != null && !customerCallPlanMirrList.isEmpty()) {
										callPlanOfflineService.createCallPlan(customerCallPlanMirrList, savedMirrorCustAlgmt, userDetails);
									}
								}

							}

						}
					}
				}
			}
		}
		LOGGER.info("========= Insert Call Plans for Affiliated Customers and Root Customer with Mirror SPs ended ============");
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
	public Boolean getGeoAlgmntflag(Long custId, UserDetails userDetails, Long spId, Long shId) throws AlignmentServiceException, CustomerServiceException {
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
			LOGGER.info("Inside calcuateProcessMetrics for PULL CUSTOMER");
			if (chngReq.getCrDefinition().getTrigger().getId().equals(ChangeRequestTriggerType.PULL_CUSTOMER.getId())) {
				metricOfflineService.processCalculativeMetrics(chngReq.getRequestedSalesPosition(), MetricTriggerType.UNASSIGN_CUSTOMER, userDetails);
				metricOfflineService.processCalculativeMetrics(chngReq.getRaisedSalesPosition(), MetricTriggerType.ASSIGN_CUSTOMER, userDetails);
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
		if (chngReq.getCrDefinition().getTrigger().getId().equals(ChangeRequestTriggerType.PULL_CUSTOMER.getId())) {
			result = metricOfflineService.checkCustomerPullMetricViolation(chngReq.getRequestedSalesPosition(), chngReq.getRaisedSalesPosition(), userDetails);
		}
		return result;
	}

	@Transactional(rollbackFor = {CallPlanServiceException.class, AlignmentServiceException.class})
	@Override
	public void updateExtdAttrOnApprForPull(CustomerAlignment targetCustomerAlignment, List<CustomerProductAlignment> updatedCustomerProductAlignments,
			UserDetails userDetails) throws AlignmentServiceException, CallPlanServiceException {

		LOGGER.info("============= Edit of Root Customer With Base Customer Alignment Extended Attributes in TCustSalesTeam tabl --- started ==========");
		this.editExtAttrFrCustSalesTeam(targetCustomerAlignment, userDetails);
		LOGGER.info("============= Edit of Root Customer With Base Customer Alignment Extended Attributes in TCustSalesTeam table --- ended ==========");

		LOGGER.info("============= Edit of Root Customer With Base Customer Product Alignment Extended Attributes in TCustPrdSalesteam table--- started ==========");
		this.editExtAttrCustPrdSalesTeam(targetCustomerAlignment, updatedCustomerProductAlignments, userDetails);
		LOGGER.info("============= Edit of Root Customer With Base Customer Product Alignment Extended Attributes in TCustPrdSalesteam table --- ended ==========");

	}

	/**
	 * To Edit Customer Product Extended Attributes
	 * 
	 * @param newCustomerProductAlignments
	 *            - Customer Product Alignments
	 * @param userDetails
	 *            -userDetails
	 * @throws AlignmentServiceException
	 */
	public void editExtAttrCustPrdSalesTeam(CustomerAlignment targetCustomerAlignment, List<CustomerProductAlignment> updatedCustomerProductAlignments,
			UserDetails userDetails) throws AlignmentServiceException {
		LOGGER.info("*****************Execute of editExtAttrCustPrdSalesTeam --- started *****************");
		try {
			List<CustomerProductAlignment> customerProductAlignmentList = new ArrayList<CustomerProductAlignment>();
			if (updatedCustomerProductAlignments != null && !updatedCustomerProductAlignments.isEmpty()) {

				for (CustomerProductAlignment customerProductAlignment : updatedCustomerProductAlignments) {

					if (customerProductAlignment.getExtdAttributes() != null && !customerProductAlignment.getExtdAttributes().isEmpty()) {
						CustomerProductAlignment custPrdAlgmnt = new CustomerProductAlignment();
						custPrdAlgmnt.setCustomer(customerProductAlignment.getCustomer());
						custPrdAlgmnt.setProduct(customerProductAlignment.getProduct());
						custPrdAlgmnt.setExtdAttributes(customerProductAlignment.getExtdAttributes());
						custPrdAlgmnt.setSalesPosition(targetCustomerAlignment.getSalesPosition());
						customerProductAlignmentList.add(custPrdAlgmnt);

					}

				}
			}
			LOGGER.info("*****************START OF EDIT CUSTOMER PRODUCT ALIGNMENT EXTENDED ATTRIBUTES FOR BASE*****************");
			customerAlignmentDAOService.editExtAttrFrCustPrdSalesTeam(customerProductAlignmentList, userDetails);
			LOGGER.info("*****************END OF EDIT CUSTOMER PRODUCT ALIGNMENT EXTENDED ATTRIBUTES FOR BASE*****************");

		} catch (OpservDataAccessException e) {
			LOGGER.error("Exception while editing customer Products's extended attributes" + e.getMessage());
			throw new AlignmentServiceException(AlignmentServiceExceptionCode.ALGN_SER_EX_CD_315,
					"Exception while editing customer Products's extended attributes", null, e);
		}
		LOGGER.info("*****************Execute of editExtAttrCustPrdSalesTeam --- ended *****************");
	}

	/**
	 * To Edit Customer Alignment Extended Attributes
	 * 
	 * @param customerAlignmentList
	 *            -List of Customer Alignments
	 * @param userDetails
	 *            -userDetails
	 * @throws AlignmentServiceException
	 */
	public void editExtAttrFrCustSalesTeam(CustomerAlignment targetCustomerAlignment, UserDetails userDetails) throws AlignmentServiceException {
		try {
			LOGGER.info("*****************Execute of editExtAttrFrCustSalesTeam --- started *****************");
			if (targetCustomerAlignment != null && targetCustomerAlignment.getExtdAttributes() != null
					&& !targetCustomerAlignment.getExtdAttributes().isEmpty()) {

				LOGGER.info("*****************START OF EDIT CUSTOMER ALIGNMENT EXTENDED ATTRIBUTES FOR BASE SP*****************");
				customerAlignmentDAOService.editExtAttrFrCustSalesTeam(targetCustomerAlignment.getSalesPosition(), targetCustomerAlignment, userDetails);
				LOGGER.info("*****************END OF EDIT CUSTOMER ALIGNMENT EXTENDED ATTRIBUTES FOR BASE SP*****************");

			}

		} catch (OpservDataAccessException e) {
			LOGGER.error("Exception while editing customerAlignment's extended attributes" + e.getMessage());
			throw new AlignmentServiceException(AlignmentServiceExceptionCode.ALGN_SER_EX_CD_314,
					"Exception while editing customerAlignment's extended attributes", null, e);
		}
		LOGGER.info("*****************Execute of editExtAttrFrCustSalesTeam --- ended *****************");
	}
	
	/**
	 * Unlock a customer alignment
	 * @param customerAlgmnt
	 * @param userDetails
	 * @return
	 * @throws AlignmentServiceException
	 * @throws ViewServiceException 
	 */
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

	/**
	 * Unlock multiple customer aligments
	 * @param customerAlgmnt
	 * @param userDetails
	 * @return
	 * @throws AlignmentServiceException
	 * @throws ViewServiceException 
	 */
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


}
