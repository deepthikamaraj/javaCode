package com.cognizant.opserv.sp.cr.process.internal.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.opserv.sp.assembler.CustomerProductAlignmentModelAssembler;
import com.cognizant.opserv.sp.cr.process.dto.CustomerAlignmentDTO;
import com.cognizant.opserv.sp.cr.process.dto.MirrorCustAlgmtDTO;
import com.cognizant.opserv.sp.cr.process.internal.service.CustomerProductAlignmentOfflineService;
import com.cognizant.opserv.sp.exception.AlignmentServiceException;
import com.cognizant.opserv.sp.exception.AlignmentServiceException.AlignmentServiceExceptionCode;
import com.cognizant.opserv.sp.exception.CallPlanServiceException;
import com.cognizant.opserv.sp.exception.CallPlanServiceException.CallPlanServiceExceptionCode;
import com.cognizant.opserv.sp.model.CustomerAlignment;
import com.cognizant.opserv.sp.model.CustomerProductAlignment;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.persistence.dao.service.CustomerProductAlignmentDAOService;
import com.cognizant.peg.core.exception.OpservDataAccessException;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;

@Service
public class CustomerProductAlignmentOfflineServiceImpl implements CustomerProductAlignmentOfflineService {

	/**
	 * LOGGER
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(CustomerProductAlignmentOfflineServiceImpl.class);

	@Autowired
	private CustomerProductAlignmentDAOService customerProductAlignmentDAOService;

	@Autowired
	private CustomerProductAlignmentModelAssembler customerProductAlignmentModelAssembler;

	/**
	 * getCustomerProductAlignments-gets the customer product
	 * 
	 * @param custAlgmtDTO
	 *            -customer Alignment DTO
	 * @param userDetails
	 *            -userDetails
	 * @return List<CustomerProductAlignment> - List of Customer Product
	 *         Alignment
	 */
	@Override
	public List<CustomerProductAlignment> getCustomerProductAlignments(List<CustomerAlignment> custAlgmnts, UserDetails userDetails) {

		List<Long> custALIdList = new ArrayList<Long>();

		for (CustomerAlignment customerAlignment : custAlgmnts) {
			custALIdList.add(customerAlignment.getId());
		}
		List<CustomerProductAlignment> customerProductAlignmentList = customerProductAlignmentDAOService.populateCustPrdAlgmntByCustAlId(custALIdList,
				userDetails.getTenantId());

		return customerProductAlignmentList;
	}

	@Override
	public void populateCustomerProductAlignment(CustomerAlignmentDTO custAlgmtDTO, UserDetails userDetails) throws AlignmentServiceException {
		try{
			List<CustomerAlignment> custAlgmntList = new ArrayList<CustomerAlignment>();

			custAlgmntList.add(custAlgmtDTO.getSourceBaseCustAlgmt());
			LOGGER.info("**************************Root Customer Alignment with Base is ====*****" + custAlgmntList.size());

			if (custAlgmtDTO.getSourceBaseAffCustAlgmts() != null && !custAlgmtDTO.getSourceBaseAffCustAlgmts().isEmpty()) {
				for (CustomerAlignment custAlgnmnt : custAlgmtDTO.getSourceBaseAffCustAlgmts()) {
					custAlgmntList.add(custAlgnmnt);
				}
			}
			LOGGER.info("**************************Affliated Customer Alignment with Base are ====*****" + custAlgmntList.size());

			if (custAlgmtDTO.getMirrors() != null && !custAlgmtDTO.getMirrors().isEmpty()) {
				for (MirrorCustAlgmtDTO custAlgnmntMirrorDTO : custAlgmtDTO.getMirrors()) {
					for (CustomerAlignment sourceMirrorCustAlgmt : custAlgnmntMirrorDTO.getSourceMirrorCustAlgmts()) {
						custAlgmntList.add(sourceMirrorCustAlgmt);
					}
				}
			}
			LOGGER.info("**************************Customer Alignments(Root + Affiliated) with Mirror are ====*****" + custAlgmntList.size());

			List<CustomerProductAlignment> custPrdAlgmntFrBaseCustList = new ArrayList<CustomerProductAlignment>();
			List<CustomerProductAlignment> custPrdAlgmntFrAffCustList = new ArrayList<CustomerProductAlignment>();
			List<CustomerProductAlignment> custPrdAlgmntFrMirrList = null;

			List<CustomerProductAlignment> customerProductAlignments = this.getCustomerProductAlignments(custAlgmntList, userDetails);

			if (customerProductAlignments != null && !customerProductAlignments.isEmpty()) {

				for (CustomerProductAlignment custPrdAlgmnt : customerProductAlignments) {
					if (custAlgmtDTO.getSourceBaseCustAlgmt().getId().equals(custPrdAlgmnt.getCustomerAlgmntId())) {
						custPrdAlgmntFrBaseCustList.add(custPrdAlgmnt);
						custAlgmtDTO.setSourceOldCustProdAlgmts(custPrdAlgmntFrBaseCustList);
					}

					if (custAlgmtDTO.getSourceBaseAffCustAlgmts() != null && !custAlgmtDTO.getSourceBaseAffCustAlgmts().isEmpty()) {
						for (CustomerAlignment sourceBaseAffCustAlgmt : custAlgmtDTO.getSourceBaseAffCustAlgmts()) {
							if (sourceBaseAffCustAlgmt.getId().equals(custPrdAlgmnt.getCustomerAlgmntId())) {
								custPrdAlgmntFrAffCustList.add(custPrdAlgmnt);
								custAlgmtDTO.setAffCustProdAlgmts(custPrdAlgmntFrAffCustList);
							}
						}
					}

				}

				if (custAlgmtDTO.getMirrors() != null && !custAlgmtDTO.getMirrors().isEmpty()) {
					for (MirrorCustAlgmtDTO mirrorCustAlgmtDTO : custAlgmtDTO.getMirrors()) {
						if (mirrorCustAlgmtDTO.getSourceMirrorCustAlgmts() != null && !mirrorCustAlgmtDTO.getSourceMirrorCustAlgmts().isEmpty()) {

							custPrdAlgmntFrMirrList = new ArrayList<CustomerProductAlignment>();
							for (CustomerAlignment sourceMirrorCustAlgmt : mirrorCustAlgmtDTO.getSourceMirrorCustAlgmts()) {
								for (CustomerProductAlignment custPrdAlgmnt : customerProductAlignments) {
									if (sourceMirrorCustAlgmt.getId().equals(custPrdAlgmnt.getCustomerAlgmntId())) {
										custPrdAlgmntFrMirrList.add(custPrdAlgmnt);
										mirrorCustAlgmtDTO.setSourcemirrorCustProdAlgmts(custPrdAlgmntFrMirrList);
									}
								}
							}
						}
					}
				}
			}
		}catch(OpservDataAccessException ex){

			LOGGER.error("********************Issue occurred while fetching Customer Product Alignment  ******************  ");
			Object[] args = new Object[] { "CustomerAlignmentDTO" };
			throw new AlignmentServiceException(AlignmentServiceExceptionCode.ALGN_SER_EX_CD_331,
					"Issue occurred while fetching Customer Product Alignment", args, ex);
		
		}
		}
		

}
