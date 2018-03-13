package com.cognizant.opserv.sp.cr.process.internal.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.opserv.sp.common.CustomerCategoryType;
import com.cognizant.opserv.sp.cr.process.dto.CustomerAlignmentDTO;
import com.cognizant.opserv.sp.cr.process.dto.MirrorCustAlgmtDTO;
import com.cognizant.opserv.sp.cr.process.internal.service.CustomerAlignmentOfflineService;
import com.cognizant.opserv.sp.cr.process.internal.service.CustomerAlignmentValidationService;
import com.cognizant.opserv.sp.exception.AffiliationServiceException;
import com.cognizant.opserv.sp.exception.AlignmentServiceException;
import com.cognizant.opserv.sp.exception.SalesPositionServiceException;
import com.cognizant.opserv.sp.model.Customer;
import com.cognizant.opserv.sp.model.CustomerAffiliation;
import com.cognizant.opserv.sp.model.CustomerAlignment;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.persistence.dao.service.CustomerAlignmentDAOService;
import com.cognizant.opserv.sp.persistence.dao.service.CustomerDAOService;
import com.cognizant.opserv.sp.persistence.dao.service.SalesPositionDAOService;
import com.cognizant.opserv.sp.service.common.CustomerAffiliationCommonService;
import com.cognizant.opserv.sp.service.common.SalesPositionCommonService;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;

@Service
public class CustomerAlignmentValidationServiceImpl implements
		CustomerAlignmentValidationService {

	/** The Constant LOGGER. */
	private static final OpservLogger LOGGER = OpservLoggerFactory
			.getOpservLogger(CustomerAlignmentValidationServiceImpl.class);

	/**
	 * Customer Affiliation Common Service
	 */
	@Autowired
	private CustomerAffiliationCommonService custAffCommonService;
	
	/**
	 * Customer Alignment Service
	 */
	@Autowired
	private CustomerAlignmentOfflineService custAlgmtOfflineService;
	
	/**
	 * SalesPosition Common Service
	 */
	@Autowired
	private SalesPositionCommonService salesPositionService;
	
	/**
	 * Customer DAO Service
	 */
	@Autowired
	private CustomerDAOService customerDAOService;
	
	/**
	 * Customer Alignment DAO Service
	 */
	@Autowired
	private CustomerAlignmentDAOService customerAlignmentDAOService;

	/**
	 * SalesPosition DAO Service
	 */
	@Autowired 
	private SalesPositionDAOService salesPositionDAOService;
	
	/**
	 * Validate Affiliated Customer Alignments -This method will populate the
	 * Source Base Affiliated Customers and set on the customer Alignment DTO
	 * 
	 * @param custAlgmtDTO
	 * @param userDetails
	 * @throws AffiliationServiceException 
	 * @throws AlignmentServiceException 
	 */
	@Override
	public void validateAffiliatedCustomerAlignment(CustomerAlignmentDTO custAlgmtDTO, UserDetails userDetails) throws AffiliationServiceException, AlignmentServiceException {

		LOGGER.info("========= Execution of validateAffiliatedCustomerAlignment-----started ==========");

		List<CustomerAffiliation> affiliatedCustomers = null;
		List<CustomerAlignment> validAffCustAlgmts = null;
		List<CustomerAffiliation> allChildAffCustomers = null;

		Customer custDetails = customerDAOService.fetchCustDetails(custAlgmtDTO.getSourceBaseCustAlgmt().getCustomer().getId().intValue());

		LOGGER.info("============== To check whether the customer category is 'Account'==========");
		if (custDetails != null && custDetails.isActive() && custDetails.getCategory() != null
				&& custDetails.getCategory().getId().equals(CustomerCategoryType.ACCOUNT.getId())) {
			LOGGER.info("============= If customer category is 'Account' , then fetch the Affiliated customers=============");

				if (null != custAlgmtDTO.getSourceBaseCustAlgmt()) {
					affiliatedCustomers = custAffCommonService.getAffiliatedAccountCustomers(custAlgmtDTO.getSourceBaseCustAlgmt(), userDetails);
				}
			

			List<Customer> affChildCusts = new ArrayList<Customer>();
			if (affiliatedCustomers != null && !affiliatedCustomers.isEmpty()) {

				if (null != custAlgmtDTO.getSourceBaseCustAlgmt()) {
						List<CustomerAffiliation> childAffCustList = new ArrayList<CustomerAffiliation>();

						allChildAffCustomers = getAllChildAffCustomers(affiliatedCustomers, childAffCustList);

						if (allChildAffCustomers != null && !allChildAffCustomers.isEmpty()) {
							for (CustomerAffiliation custAff : allChildAffCustomers) {
								if (!custAff.getCustomer().getId().equals(custAlgmtDTO.getSourceBaseCustAlgmt().getCustomer().getId())) {
									affChildCusts.add(custAff.getCustomer());
								}

							}
						}
						validAffCustAlgmts = custAlgmtOfflineService.getValidAffiliatedCustomerAlignments(affChildCusts, custAlgmtDTO.getSourceBaseCustAlgmt()
								.getSalesPosition(), userDetails);
						if (validAffCustAlgmts != null && !validAffCustAlgmts.isEmpty()) {
							custAlgmtDTO.setSourceBaseAffCustAlgmts(validAffCustAlgmts);
						}

				}
			}

			/*if (affChildCusts != null && validAffCustAlgmts != null && affChildCusts.size() != validAffCustAlgmts.size()) {
				ErrorDTO error = new ErrorDTO();
				error.setErrorCode(ErrorCode.ERROR_2004);
				//error.addParam("SOMEKYE", "VAL"); // TODO : to be extended
				custAlgmtDTO.addErrorDTO(error);
			}*/
		}
		LOGGER.info("========= Execution of validateAffiliatedCustomerAlignment -----ended ==========");
	}

	/**
	 * Validate Mirrors for the Customer Alignment Based on the affliated
	 * customers obtained in the customer AlignmentDTO, finding mirrors for
	 * Source and Target. For those source mirror SPs, validating which
	 * customers (base + affiliated customers) can be used.
	 * 
	 * This method will create the mirror DTO based on the customer alignment
	 * DTO
	 * 
	 * @param custAlgmtDTO
	 * @param userDetails
	 * @throws AlignmentServiceException 
	 */
	@Override
	public void validateMirrorsForCustomerAlignment(CustomerAlignmentDTO custAlgmtDTO, UserDetails userDetails) throws AlignmentServiceException, SalesPositionServiceException {
		LOGGER.info("========= Execution of validateMirrorsForCustomerAlignment -- started========");
		
		if(custAlgmtDTO.getMirrors() == null || custAlgmtDTO.getMirrors().isEmpty()) {
			LOGGER.info("========= Mirror SPs are not present, so fetch  ========");
			if(null != custAlgmtDTO.getSourceBaseCustAlgmt()){
				if(hasMirrors(custAlgmtDTO.getSourceBaseCustAlgmt().getSalesPosition(), userDetails)) {
					identifySourceMirrorSP(custAlgmtDTO, userDetails);
					identifyTargetMirrorSP(custAlgmtDTO, userDetails);
				}
			}
		}
		
		// Validate check for if the source and target sp are present for the mirrors. If not throw excepption.
/*		if(custAlgmtDTO.getMirrors() != null && !custAlgmtDTO.getMirrors().isEmpty()){
			for(MirrorCustAlgmtDTO mirrorCustAlgmtDTO : custAlgmtDTO.getMirrors()){
				if(mirrorCustAlgmtDTO.getSourceSP() == null || mirrorCustAlgmtDTO.getTargetSP() == null){
					LOGGER.info("========= One of the Base Sales Positions doesn't have Mirror Sales Position========");
					ErrorDTO error = new ErrorDTO();
					error.setErrorCode(ErrorCode.ERROR_2003); 
					error.addParam("SOMEKYE", "VAL"); 
					custAlgmtDTO.addErrorDTO(error);
				} 
			}
		}*/
		
		// The list of root and affiliated Customers
		List<Customer> custList = new ArrayList<Customer>();
		custList.add(custAlgmtDTO.getSourceBaseCustAlgmt().getCustomer());
		if(custAlgmtDTO.getSourceBaseAffCustAlgmts() != null && !custAlgmtDTO.getSourceBaseAffCustAlgmts().isEmpty()){
			for(CustomerAlignment custAligns : custAlgmtDTO.getSourceBaseAffCustAlgmts()) {
				custList.add(custAligns.getCustomer());
			}
		}
		
		LOGGER.info("========= The list of root and affiliated Customers is ===" + custList);
		
		if(custAlgmtDTO.getMirrors() != null){
			for(MirrorCustAlgmtDTO mirror : custAlgmtDTO.getMirrors()) {
				// Valid Customer and Affiliated customers in the mirror Source SP
				mirror.setSourceMirrorCustAlgmts(custAlgmtOfflineService.getValidAffiliatedCustomerAlignments(custList,mirror.getSourceSP(), userDetails));
				if(null != mirror.getTargetSP()){
					mirror.setTargetMirrorCustAlgmts(custAlgmtOfflineService.getValidAffiliatedCustomerAlignments(custList,mirror.getTargetSP(), userDetails));
				}
			}
		}
		
		
		LOGGER.info("========= Execution of validateMirrorsForCustomerAlignment -- ended========");
	}
	
	/**
	 * Checks for mirrors.
	 * 
	 * @param sp
	 *            the sp
	 * @param userDetails
	 *            the user details
	 * @return true, if successful
	 */
	public boolean hasMirrors(SalesPosition sp, UserDetails userDetails) {
			// Logic to check if SP has mirrors or not.
		LOGGER.info("========= If the SalesTeam is Mirrored, then Check whether Mirror SPs are present or not -- started========");
		boolean isSalesTeamMirrored = this.isSalesTeamMirrored(sp.getAlignmment().getId(), sp.getAlignmment()
				.getSalesTeam().getBusinessUnit().getId(), sp.getAlignmment()
				.getSalesTeam().getId(), userDetails.getTenantId());
		LOGGER.info("========= If the SalesTeam is Mirrored, then Check whether Mirror SPs are present or not -- ended========");
		return isSalesTeamMirrored;
	}
	
	/**
	 * Identify source mirror sp.
	 * 
	 * @param custAlgmtDTO
	 *            the cust algmt dto
	 * @param userDetails
	 *            the user details
	 */
	public void identifySourceMirrorSP(CustomerAlignmentDTO custAlgmtDTO, UserDetails userDetails) throws SalesPositionServiceException {
		
		LOGGER.info("****** Execution of identifySourceMirrorSP ---- started *****");
		// 1. Getting Mirrored SP for Source
		LOGGER.info("****** Step1 : Get the Mirrored SPs for Source SP ---- started *****");
		List<SalesPosition> mirrorSourceSPs = salesPositionService.getMirroredSalesPosition(custAlgmtDTO.getSourceBaseCustAlgmt().getSalesPosition(), userDetails);
		LOGGER.info("****** Step1 : Get the Mirrored SPs for Source SP ---- ended *****");
		
		
		List<Long> mirrorSourceSPIds = null;
		if(mirrorSourceSPs != null && !mirrorSourceSPs.isEmpty()){
			mirrorSourceSPIds = new ArrayList<Long>();
			for(SalesPosition mirrSP : mirrorSourceSPs){
				mirrorSourceSPIds.add(mirrSP.getId());
			}
		}
		LOGGER.info("****** Source Mirror SPIds  are ==== " + mirrorSourceSPIds);
		
		// TODO : 2. Filter out those SP in which the the base customers exists.
		LOGGER.info("****** Step2 : Filter out those Mirror SPs in which the the root customers exists  ---- started *****");
		List<SalesPosition> spWithBaseCustList = customerAlignmentDAOService.fetchAssignedCustForSp(custAlgmtDTO.getSourceBaseCustAlgmt().getCustomer().getId(), mirrorSourceSPIds, userDetails);
		LOGGER.info("****** Step2 : Filter out those Mirror SPs in which the the root customers exists ---- ended *****");
		
		// For each mirror source SP (filtered in step 2) , creating the mirror DTO
		for(SalesPosition mirrorSourceSP : spWithBaseCustList) {
			MirrorCustAlgmtDTO mirrorDTO = new MirrorCustAlgmtDTO();
			mirrorDTO.setSourceSP(mirrorSourceSP);
			custAlgmtDTO.addMirrorDTO(mirrorDTO);
			
		}
		LOGGER.info("****** Execution of identifySourceMirrorSP ---- ended *****");
	}
	
	/**
	 * Identify target mirror sp.
	 * 
	 * @param custAlgmtDTO
	 *            the cust algmt dto
	 * @param userDetails
	 *            the user details
	 */
	public void identifyTargetMirrorSP(CustomerAlignmentDTO custAlgmtDTO, UserDetails userDetails) throws SalesPositionServiceException {
		LOGGER.info("****** Execution of identifyTargetMirrorSP ---- started *****");
		
		// Getting Mirrored SP for Target
		LOGGER.info("****** Step1 : Get the Mirrored SPs for Target SP ---- started *****");
		List<SalesPosition> mirrorTargetSPs = salesPositionService.getMirroredSalesPosition(custAlgmtDTO.getTargetBaseCustAlgmt().getSalesPosition(), userDetails);
		LOGGER.info("****** Step1 : Get the Mirrored SPs for Target SP ---- ended *****");
		
		// Map of Mirror SP which are Primary. Used to map Source mirror and Target Mirror
		Map<Long,SalesPosition> targetSPMap = new HashMap<Long,SalesPosition>();
		if (mirrorTargetSPs != null && !mirrorTargetSPs.isEmpty()) {
			for (SalesPosition mirrorTargetSP : mirrorTargetSPs) {
				if (mirrorTargetSP.isPrimaryMirror()) {
					targetSPMap.put(mirrorTargetSP.getAlignmment().getId(),
							mirrorTargetSP);
				}
			}
		}
		
		if (custAlgmtDTO.getMirrors() != null && !custAlgmtDTO.getMirrors().isEmpty()) {
			for (MirrorCustAlgmtDTO mirror : custAlgmtDTO.getMirrors()) {
				mirror.setTargetSP(targetSPMap.get(mirror.getSourceSP()
						.getAlignmment().getId()));
			}
		}
		
		LOGGER.info("****** Execution of identifyTargetMirrorSP ---- ended *****");
	}
	
	/**
	 * Gets the all child aff customers.
	 * 
	 * @param custAffList
	 *            the cust aff list
	 * @param childCustAffList
	 *            the child cust aff list
	 * @return the all child aff customers
	 */ 
	
	// To be deleted from old file
	private List<CustomerAffiliation> getAllChildAffCustomers(
			List<CustomerAffiliation> custAffList,
			List<CustomerAffiliation> childCustAffList) {
		LOGGER.info("**************************Get All Child Affiliated Customers  started******************************");
		for (CustomerAffiliation ca : custAffList) {
			childCustAffList.add(ca);
			if (ca.getAffiliatedCustomers() != null
					&& !ca.getAffiliatedCustomers().isEmpty()) {
				getAllChildAffCustomers(ca.getAffiliatedCustomers(),
						childCustAffList);
			}
		}
		LOGGER.info("**************************Get All Child Affiliated Customers  ended******************************");
		return childCustAffList;
	}

	
	/**
	 * Is SalesTeam Mirrored.
	 * 
	 * @param algmntId
	 *            the algmnt id
	 * @param bussUnitId
	 *            the buss unit id
	 * @param salesTeamId
	 *            the sales team id
	 * @param tenantId
	 *            the tenant id
	 * @return true, if is sales team mirrored
	 */
	// To be deleted from old file
	private boolean isSalesTeamMirrored(Long algmntId, Long bussUnitId,
			Long salesTeamId, Short tenantId) {
		LOGGER.info("****** Check whether the SalesTeam is Mirrored  started*****");
		return salesPositionDAOService.isSalesTeamMirrored(algmntId, bussUnitId, salesTeamId, tenantId);
	}

	
	
}
