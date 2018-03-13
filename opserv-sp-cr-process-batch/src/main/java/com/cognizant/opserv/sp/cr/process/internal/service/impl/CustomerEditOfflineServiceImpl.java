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
import com.cognizant.opserv.sp.cr.process.internal.service.CustomerEditOfflineService;
import com.cognizant.opserv.sp.cr.process.internal.service.MetricOfflineService;
import com.cognizant.opserv.sp.cr.process.internal.service.OfflineRequestService;
import com.cognizant.opserv.sp.exception.AlignmentServiceException;
import com.cognizant.opserv.sp.exception.AlignmentServiceException.AlignmentServiceExceptionCode;
import com.cognizant.opserv.sp.exception.CallPlanServiceException;
import com.cognizant.opserv.sp.exception.ChangeRequestServiceException;
import com.cognizant.opserv.sp.exception.MetricExecutionException;
import com.cognizant.opserv.sp.exception.MetricViolationException;
import com.cognizant.opserv.sp.exception.SalesPositionServiceException;
import com.cognizant.opserv.sp.exception.ViewServiceException;
import com.cognizant.opserv.sp.model.CustomerAlignment;
import com.cognizant.opserv.sp.model.CustomerProductAlignment;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.model.cr.ChangeRequest;
import com.cognizant.opserv.sp.model.cr.ChangeRequestDefinition;
import com.cognizant.opserv.sp.model.cr.CustomerAlignmentChangeRequestDetails;
import com.cognizant.opserv.sp.model.metric.MetricResult;
import com.cognizant.opserv.sp.persistence.dao.service.CustomerAlignmentDAOService;
import com.cognizant.opserv.sp.service.common.ChangeRequestCommonService;
import com.cognizant.opserv.sp.service.common.CustomerAlignmentCommonService;
import com.cognizant.peg.core.exception.OpservDataAccessException;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;

@Service
public class CustomerEditOfflineServiceImpl implements CustomerEditOfflineService {
	
	/**
	 * LOGGER
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(CustomerEditOfflineServiceImpl.class);

	/**
	 * custAlgnmtCommonService
	 */
	@Autowired
	private CustomerAlignmentCommonService custAlgnmtCommonService;
	
	/**
	 * crOfflineService
	 */
	@Autowired
	private ChangeRequestOfflineService crOfflineService;
	
	/**
	 * crCommonService
	 */
	@Autowired
	private ChangeRequestCommonService crCommonService;
	
	/**
	 * custAlgmtValidationService
	 */
	@Autowired
	private CustomerAlignmentValidationService custAlgmtValidationService;
	
	/**
	 * callPlanOfflineService
	 */
	@Autowired 
	private CallPlanOfflineService callPlanOfflineService;

	/**
	 * customerAlignmentDAOService
	 */
	@Autowired
	private CustomerAlignmentDAOService customerAlignmentDAOService;
	
	/**
	 *  OfflineRequestService
	 */
	@Autowired
	private OfflineRequestService offlineRequestService;
	
	/** The metric common service. */
	@Autowired
	private MetricOfflineService metricOfflineService;
	
	@Override
	public void getMirrorCRsForEdit(CustomerAlignmentDTO custAlgmtDTO, UserDetails userDetails) throws ChangeRequestServiceException {
		LOGGER.info("================Get Mirror CR for Edit Started=============" );
		List<ChangeRequest> mirrorCRs = crCommonService.findMirrorCRs(custAlgmtDTO.getMainCR(), userDetails);
		if(mirrorCRs != null && !mirrorCRs.isEmpty()){
			for(ChangeRequest mirrorCR : mirrorCRs) {
				if(custAlgmtDTO.getMirrors() != null){
				for(MirrorCustAlgmtDTO mirrorDTO : custAlgmtDTO.getMirrors()) {
					if(mirrorDTO.getSourceSP().getId().equals(mirrorCR.getRaisedSalesPosition().getId()) && 
					   mirrorDTO.getTargetSP().getId().equals(mirrorCR.getRequestedSalesPosition().getId())) {
						mirrorDTO.setChildCR(mirrorCR);
						break;
					}
				}
				}
			}
		}
		LOGGER.info("================Get Mirror CR for Edit Ended=============" );
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
		if(parentCR == null) {
			return false;
		}
		synchronized(parentCR) {
			try {
				LOGGER.info("================Create Mirror CR=============--Started" +parentCR.getId());
				ChangeRequest childCR = crCommonService.generateEditCustomerAlignmentChangeRequest(parentCR,mirrorDTO.getTargetSP(), userDetails);
				mirrorDTO.setChildCR(childCR);
			} catch(ChangeRequestServiceException cre) {
				return false;
			}
		}
		LOGGER.info("================Create Mirror CR=============--Ended" );
		return true;
	}

	@Override
	public void validate(CustomerAlignmentDTO customerAlgmtDTO, UserDetails userDetails) throws CallPlanServiceException, AlignmentServiceException, SalesPositionServiceException {

		LOGGER.info("********************Execute if validate for EDIT -- started******************");
		
		LOGGER.info("========= Check whether Root Customer-Base SP planned calls is changed from zero to a non zero --- started=======");
		if (customerAlgmtDTO.getTargetBaseCustAlgmt() != null && customerAlgmtDTO.getSourceBaseCustAlgmt() != null
				&& customerAlgmtDTO.getTargetBaseCustAlgmt().getCustomerCallPlan() != null
				&& customerAlgmtDTO.getTargetBaseCustAlgmt().getCustomerCallPlan().getPlannedCalls() > 0
				&& customerAlgmtDTO.getSourceBaseCustAlgmt().getCustomerCallPlan() != null
				&& customerAlgmtDTO.getSourceBaseCustAlgmt().getCustomerCallPlan().getPlannedCalls() == 0) {
			LOGGER.info("Source Base Customer Algmnt planned calls ===== " + customerAlgmtDTO.getSourceBaseCustAlgmt().getCustomerCallPlan().getPlannedCalls()
					+ "and Target Base Customer Algmnt planned calls === " + customerAlgmtDTO.getTargetBaseCustAlgmt().getCustomerCallPlan().getPlannedCalls());
			// Mirror Validation
			try {
				LOGGER.info("========= Validate Mirrors Customer Alignment for EDIT CUSTOMER as Base Plannded calls is changed from zero to a non zero--- started=======");
				custAlgmtValidationService.validateMirrorsForCustomerAlignment(customerAlgmtDTO, userDetails);
				LOGGER.info("========= Validate Mirrors Customer Alignment for EDIT CUSTOMER as Base Plannded calls is changed from zero to a non zero--- ended=======");
			} catch (AlignmentServiceException e) {
				LOGGER.error("Error while fetching Mirrors for Edit Customers", e);
				ErrorDTO error = new ErrorDTO();
				error.addParam("Customer Id", customerAlgmtDTO.getSourceBaseCustAlgmt().getCustomer().getId());
				error.setErrorCode(ErrorCode.ERROR_CE_2085);
				customerAlgmtDTO.addErrorDTO(error);
				return;
			}

		} else {
			LOGGER.info("********************Mirror SPs are not considered for Edit as already existing planned calls is greater than zero******************");
		}
		LOGGER.info("========= Check whether Root Customer-Base SP planned calls is changed from zero to a non zero --- started=======");

		LOGGER.info("========= Populate Call Plan for EDIT CUSTOMER--- started=======");
		try {
			callPlanOfflineService.populateCallPlan(customerAlgmtDTO, userDetails);
		} catch (CallPlanServiceException cpe) {
			LOGGER.error("Error while fetching Call Plan for Edit", cpe);
			ErrorDTO error = new ErrorDTO();
			error.setErrorCode(ErrorCode.ERROR_CE_2081);
			customerAlgmtDTO.addErrorDTO(error);
			return;

		}
		LOGGER.info("========= Populate Call Plan for EDIT CUSTOMER--- ended=======");
		
		LOGGER.info("========= Check whether Root Customer-Source Mirror SPs planned calls is changed from zero to a non zero. If so, then set Target Base Customer Algmt CallPlan --- started=======");
		List<MirrorCustAlgmtDTO> invalidMirrorCustAlgmtDTOs = null;
		if (customerAlgmtDTO.getMirrors() != null && !customerAlgmtDTO.getMirrors().isEmpty()) {
			invalidMirrorCustAlgmtDTOs = new ArrayList<MirrorCustAlgmtDTO>();
			for (MirrorCustAlgmtDTO mirCustAlgmntDTO : customerAlgmtDTO.getMirrors()) {
				if (mirCustAlgmntDTO.getSourceMirrorCustAlgmts() != null && !mirCustAlgmntDTO.getSourceMirrorCustAlgmts().isEmpty()) {

					for (CustomerAlignment sourceMirrorCustAlgmt : mirCustAlgmntDTO.getSourceMirrorCustAlgmts()) {
						if (sourceMirrorCustAlgmt.getCustomerCallPlan() != null && sourceMirrorCustAlgmt.getCustomerCallPlan().getPlannedCalls() == 0) {
							LOGGER.info("================Source Mirror Customer Call Plan is " + sourceMirrorCustAlgmt.getCustomerCallPlan().getPlannedCalls());
							if (mirCustAlgmntDTO.getTargetMirrorCustAlgmts() != null && !mirCustAlgmntDTO.getTargetMirrorCustAlgmts().isEmpty()) {
								for (CustomerAlignment targetMirrorCustAlgmt : mirCustAlgmntDTO.getTargetMirrorCustAlgmts()) {
									if (sourceMirrorCustAlgmt.getCustomer().getId().equals(targetMirrorCustAlgmt.getCustomer().getId())) {
										LOGGER.info("================Target Mirror Customer Call Plan to be set "
												+ customerAlgmtDTO.getTargetBaseCustAlgmt().getCustomerCallPlan().getPlannedCalls());
										targetMirrorCustAlgmt.setCustomerCallPlan(customerAlgmtDTO.getTargetBaseCustAlgmt().getCustomerCallPlan());
									}
								}
							}

						} else {
							customerAlgmtDTO.addInvalidMirrorDTO(mirCustAlgmntDTO);
							invalidMirrorCustAlgmtDTOs.add(mirCustAlgmntDTO);
							// customerAlgmtDTO.getMirrors().remove(mirCustAlgmntDTO);
						}
					}
				}
			}
			if (invalidMirrorCustAlgmtDTOs != null && !invalidMirrorCustAlgmtDTOs.isEmpty()) {
				for (MirrorCustAlgmtDTO invalidMirrorCustAlgmtDTO : invalidMirrorCustAlgmtDTOs) {
					customerAlgmtDTO.getMirrors().remove(invalidMirrorCustAlgmtDTO);
				}

			}

		}
		LOGGER.info("========= Check whether Root Customer-Source Mirror SPs planned calls is changed from zero to a non zero. If so, then set Target Base Customer Algmt CallPlan --- ended=======");

		// Customer Product Alignment
		// custProdAlgmtOfflineService.populateCustomerProductAlignment(customerAlgmtDTO,
		// userDetails);

		LOGGER.info("********************Execute if validate for EDIT -- started******************");
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW,rollbackFor = AlignmentServiceException.class)
	public boolean lockCustomerAlignment(CustomerAlignment customerAlgmt, UserDetails userDetails) throws AlignmentServiceException, ViewServiceException {
		LOGGER.info("========= Execute of lock Customer Alignment ==============");
		try{
			if(customerAlgmt != null && userDetails != null && userDetails.getTenantId() != null && userDetails.getUserId() != null){
				return custAlgnmtCommonService.lockCustomerAlignment(customerAlgmt, userDetails);
			}	else{
				Object[] args = new Object[] { "customerAlgmnt or TenantId or UserId is null" };
				throw new AlignmentServiceException(args);
			}
		} catch(AlignmentServiceException e){
			LOGGER.error("********************Issue occurred while Locking the Customer Alignment ****************** ");
			Object[] args = new Object[] {"CustomerAlignment" };
			throw new AlignmentServiceException(AlignmentServiceExceptionCode.ALGN_SER_EX_CD_257,"This Issue occurred while Locking the Customer Alignment", args, e);
		}
		
		
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW,rollbackFor = {ChangeRequestServiceException.class,AlignmentServiceException.class})
	public void processOnSubmit(CustomerAlignmentDTO customerAlgmtDTO, UserDetails userDetails) throws ChangeRequestServiceException,AlignmentServiceException, CallPlanServiceException  {
		List<CustomerAlignmentChangeRequestDetails> crLineItems = new ArrayList<CustomerAlignmentChangeRequestDetails>();
		//TODO : break the customer algmt dto into line items
		LOGGER.info("========= Execute of processSourceOnSubmit -- started ");
		
		LOGGER.info("========= Adding Root Customer Alignment with Base for Line Items to be created -- started ");
		CustomerAlignmentChangeRequestDetails custAlChngReqDetail = new CustomerAlignmentChangeRequestDetails();
		custAlChngReqDetail.setOldCustomerAlignment(customerAlgmtDTO.getSourceBaseCustAlgmt());
		custAlChngReqDetail.setNewCustomerAlignment(customerAlgmtDTO.getTargetBaseCustAlgmt());
		custAlChngReqDetail.setOldCustomerProductAlignments(customerAlgmtDTO.getSourceOldCustProdAlgmts());
		custAlChngReqDetail.setNewCustomerProductAlignments(customerAlgmtDTO.getSourceUpdatedCustProdAlgmts());
		custAlChngReqDetail.setParentChangeRequest(customerAlgmtDTO.getMainCR());
		crLineItems.add(custAlChngReqDetail);
		LOGGER.info("========= Adding Root Customer Alignment with Base for Line Items to be created -- ended ");
		
		
		LOGGER.info("========= Adding Root Customer Alignments with Mirrors for Line Items to be created -- started ");
		List<MirrorCustAlgmtDTO> mirrors = customerAlgmtDTO.getMirrors();
		if (mirrors != null && !mirrors.isEmpty()) {
			for (MirrorCustAlgmtDTO mirCustAlgmntDTO : mirrors) {
				
				if (mirCustAlgmntDTO.getSourceMirrorCustAlgmts() != null && !mirCustAlgmntDTO.getSourceMirrorCustAlgmts().isEmpty()) {
					
					for (CustomerAlignment targetMirrorCustAlgmt : mirCustAlgmntDTO.getTargetMirrorCustAlgmts()) {
						CustomerAlignmentChangeRequestDetails custAlChngReqDetailForMirror = new CustomerAlignmentChangeRequestDetails();
						// Extended attributes are not edited for mirror SP's
						/*targetMirrorCustAlgmt.setExtdAttributes(customerAlgmtDTO.getTargetBaseCustAlgmt().getExtdAttributes());*/
						custAlChngReqDetailForMirror.setNewCustomerAlignment(targetMirrorCustAlgmt);
						/*custAlChngReqDetailForMirror.setNewCustomerProductAlignments(customerAlgmtDTO.getSourceUpdatedCustProdAlgmts());*/
						custAlChngReqDetailForMirror.setParentChangeRequest(mirCustAlgmntDTO.getChildCR());
						crLineItems.add(custAlChngReqDetailForMirror);
					}
				}
			}
		}
		
		LOGGER.info("========= Line Items to be created = " + crLineItems.size());
		crOfflineService.processLineItemForTargetCustomer(crLineItems, userDetails);
		customerAlgmtDTO.setCrLineItemsForSource(crLineItems);
		LOGGER.info("========= Execute of processSourceOnSubmit -- ended ");
	}	
	

    @Override
	public boolean handleMetricsOnSubmit(CustomerAlignmentDTO custAlgmtDTO, UserDetails userDetails) throws MetricExecutionException {
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
    				definition.setTrigger(ChangeRequestTriggerType.EDIT_CUSTOMER);
    				custAlgmtDTO.getMainCR().setCrDefinition(definition);
    				custAlgmtDTO.getMainCR().setRequestedSalesPosition(custAlgmtDTO.getSourceBaseCustAlgmt().getSalesPosition());
    				custAlgmtDTO.getMainCR().setRaisedSalesPosition(custAlgmtDTO.getTargetBaseCustAlgmt().getSalesPosition());
    				calcuateProcessMetrics(custAlgmtDTO.getMainCR(), userDetails);
    				LOGGER.info("After Calculating metrics... ChangeRequestProcessInternalServiceImpl --> submitChangeRequestTransaction :: chngReq:"
    						+ custAlgmtDTO.getMainCR().getId());
    				metricResult = changeRequestMetricsResults(custAlgmtDTO.getMainCR(), metricResultSource, metricResultTarget, userDetails);
    				LOGGER.info("metricResult... ChangeRequestProcessInternalServiceImpl --> submitChangeRequestTransaction:" + metricResult);
    			} catch (MetricViolationException exception) {
    				List<ErrorDTO> errors = new ArrayList<ErrorDTO>();
    				ErrorDTO errorDTO = new ErrorDTO();
    				errorDTO.setErrorCode(ErrorCode.ERROR_2014);
    				errors.add(errorDTO);
    				custAlgmtDTO.setErrors(errors);
    			} catch (AlignmentServiceException exeption) {
    				List<ErrorDTO> errors = new ArrayList<ErrorDTO>();
    				ErrorDTO errorDTO = new ErrorDTO();
    				errorDTO.setErrorCode(ErrorCode.ERROR_2015);
    				errors.add(errorDTO);
    				custAlgmtDTO.setErrors(errors);
    			}
    			return metricResult;
		
	}

	@Override
	public void updateChangeRequests(CustomerAlignmentDTO customerAlgmtDTO, UserDetails userDetails) throws ChangeRequestServiceException {
		// Update the main CR and mirror CR to Pending Approval
		// Initiate the workflow for those CRs
		crOfflineService.updateCRandInitiateWorkflow(customerAlgmtDTO, userDetails);
	}

	@Override
	public void emitPostProcessEvents(CustomerAlignmentDTO customerAlgmtDTO, UserDetails userDetails) {
		// TODO Auto-generated method stub
	}
	/**
	 * assignApproversForSourceAndTarget -Assigns approve for Source and Target
	 * 
	 * @param customerAlgmtDTO
	 *            -Customer Alignment DTO
	 * @param userDetails
	 *            -user Details
	 * @throws ChangeRequestServiceException
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW,rollbackFor = ChangeRequestServiceException.class)
	public void assignApprovers(CustomerAlignmentDTO customerAlgmtDTO, UserDetails userDetails) throws ChangeRequestServiceException {
		// Assigning approvers
		LOGGER.info("================Assign approvers Started=============" );
		crOfflineService.assignApproversForTarget(customerAlgmtDTO.getMainCR(), customerAlgmtDTO.getTargetBaseCustAlgmt().getSalesPosition(), userDetails);
		LOGGER.info("================Assign approvers Ended=============" );
		
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
	public void editExtAttrCustPrdSalesTeam(CustomerAlignment targetCustomerAlignment,
			List<CustomerProductAlignment> updatedCustomerProductAlignments, UserDetails userDetails)
			throws AlignmentServiceException {
		LOGGER.info("*****************Execute of editExtAttrCustPrdSalesTeam --- started *****************");
		try {
			List<CustomerProductAlignment> customerProductAlignmentList = new ArrayList<CustomerProductAlignment>();
			if (updatedCustomerProductAlignments != null
					&& !updatedCustomerProductAlignments
							.isEmpty()) {

				for (CustomerProductAlignment customerProductAlignment : updatedCustomerProductAlignments) {

					if (customerProductAlignment.getExtdAttributes() != null
							&& !customerProductAlignment.getExtdAttributes()
									.isEmpty()) {
						CustomerProductAlignment custPrdAlgmnt = new CustomerProductAlignment();
						custPrdAlgmnt.setCustomer(customerProductAlignment
								.getCustomer());
						custPrdAlgmnt.setProduct(customerProductAlignment
								.getProduct());
						custPrdAlgmnt
								.setExtdAttributes(customerProductAlignment
										.getExtdAttributes());
						custPrdAlgmnt.setSalesPosition(targetCustomerAlignment.getSalesPosition());
						customerProductAlignmentList.add(custPrdAlgmnt);

					}

				}
			}
			LOGGER.info("*****************START OF EDIT CUSTOMER PRODUCT ALIGNMENT EXTENDED ATTRIBUTES FOR BASE*****************");
			customerAlignmentDAOService.editExtAttrFrCustPrdSalesTeam(
					customerProductAlignmentList, userDetails);
			LOGGER.info("*****************END OF EDIT CUSTOMER PRODUCT ALIGNMENT EXTENDED ATTRIBUTES FOR BASE*****************");

		} catch (OpservDataAccessException e) {
			LOGGER.error("Exception while editing customer Products's extended attributes"
					+ e.getMessage());
			throw new AlignmentServiceException(
					AlignmentServiceExceptionCode.ALGN_SER_EX_CD_315,
					"Exception while editing customer Products's extended attributes",
					null, e);
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
	 * To Edit Customer Alignment Extended Attributes
	 * 
	 * @param customerAlignmentList
	 *            -List of Customer Alignments
	 * @param userDetails
	 *            -userDetails
	 * @throws AlignmentServiceException
	 */
	public void editCustAlgmnt(CustomerAlignment targetCustomerAlignment,
			UserDetails userDetails) throws AlignmentServiceException {
		try {

			if (targetCustomerAlignment != null) {

				LOGGER.info("*****************Execute of editCustAlgmnt for Base SP-- started *****************");
				customerAlignmentDAOService.editCustAlgmnt(targetCustomerAlignment,userDetails);
				LOGGER.info("*****************Execute of editCustAlgmnt for Base SP-- ended *****************");
		
			}

		} catch (OpservDataAccessException e) {
			LOGGER.error("Exception occcurred while Updating Customer Alignments"
					+ e.getMessage());
			throw new AlignmentServiceException(
					AlignmentServiceExceptionCode.ALGN_SER_EX_CD_219,
					"Exception occcurred while Updating Customer Alignments",
					null, e);
		}
	}
	
	@Transactional(rollbackFor = {AlignmentServiceException.class,CallPlanServiceException.class})
	public void updateCustomerDetailsOnAppr(CustomerAlignment targetCustomerAlignment, List<CustomerProductAlignment> updatedCustomerProductAlignments, UserDetails userDetails) throws AlignmentServiceException, CallPlanServiceException{
		
		//this.validate(customerAlgmtDTO, userDetails);
		
		LOGGER.info("============= Edit of Root Customer With Base Customer Alignment Data in TCustAlgmnt table --- started ==========");
		this.editCustAlgmnt(targetCustomerAlignment, userDetails);
		LOGGER.info("============= Edit of Root Customer With Base Customer Alignment Data in TCustAlgmnt table --- ended ==========");
		
		LOGGER.info("============= Edit of Root Customer With Base Customer Alignment Extended Attributes in TCustSalesTeam tabl --- started ==========");
		this.editExtAttrFrCustSalesTeam(targetCustomerAlignment, userDetails);
		LOGGER.info("============= Edit of Root Customer With Base Customer Alignment Extended Attributes in TCustSalesTeam table --- ended ==========");
		
		LOGGER.info("============= Edit of Root Customer With Base Customer Product Alignment Extended Attributes in TCustPrdSalesteam table--- started ==========");
		this.editExtAttrCustPrdSalesTeam(targetCustomerAlignment, updatedCustomerProductAlignments, userDetails);
		LOGGER.info("============= Edit of Root Customer With Base Customer Product Alignment Extended Attributes in TCustPrdSalesteam table --- ended ==========");
	
		LOGGER.info("============= Edit of Customer CallPlan in TCustCallPlan table --- started ==========");
		callPlanOfflineService.editCustomerCallPlan(targetCustomerAlignment, userDetails);
		LOGGER.info("============= Edit of Customer CallPlan in TCustCallPlan table --- ended ==========");
		
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
		LOGGER.info("Inside calcuateProcessMetrics for EDIT CUSTOMER");
		if (chngReq.getCrDefinition().getTrigger().getId().equals(ChangeRequestTriggerType.EDIT_CUSTOMER.getId())) {
			metricOfflineService.processCalculativeMetrics(chngReq.getRequestedSalesPosition(), MetricTriggerType.EDIT_CUSTOMER, userDetails);
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
		if (chngReq.getCrDefinition().getTrigger().getId().equals(ChangeRequestTriggerType.EDIT_CUSTOMER.getId())) {
			result = metricOfflineService.checkCustomerEditMetricViolation(chngReq.getRaisedSalesPosition(), userDetails);
		}
		return result;
	}
	/**
	 * Unlock a customer alignment
	 * 
	 * @param customerAlgmnt
	 *            -customer Algmnt
	 * @param userDetails
	 *            -userDetails
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

}