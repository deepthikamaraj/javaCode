package com.cognizant.opserv.sp.cr.process.internal.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.opserv.sp.common.ChangeRequestStatusType;
import com.cognizant.opserv.sp.cr.process.internal.service.CustomerAlignmentOfflineService;
import com.cognizant.opserv.sp.exception.AlignmentServiceException;
import com.cognizant.opserv.sp.exception.AlignmentServiceException.AlignmentServiceExceptionCode;
import com.cognizant.opserv.sp.model.Customer;
import com.cognizant.opserv.sp.model.CustomerAlignment;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.persistence.dao.service.CustomerAlignmentDAOService;
import com.cognizant.opserv.sp.service.common.CustomerAlignmentCommonService;
import com.cognizant.opserv.sp.service.common.SPAssignmentsViewService;
import com.cognizant.peg.core.exception.OpservDataAccessException;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;

@Service
public class CustomerAlignmentOfflineServiceImpl implements CustomerAlignmentOfflineService {


	@Autowired
	private CustomerAlignmentCommonService custAlgmtCommonService;
	
	@Autowired
	private CustomerAlignmentDAOService customerAlignmentDAOService;
	
	@Autowired
	private SPAssignmentsViewService spAssignmentsViewService;
	
	private static final OpservLogger LOGGER = OpservLoggerFactory
			.getOpservLogger(CustomerAlignmentOfflineServiceImpl.class);
	/**
	 * Get all the valid affiliated customers in a given sales position.
	 * @param customer
	 * @param salesposition
	 * @param userDetails
	 * @return
	 * @throws AlignmentServiceException 
	 */
	@Override
	@Transactional
	public List<CustomerAlignment> getValidAffiliatedCustomerAlignments(List<Customer> affCustomers, SalesPosition salesposition,
			UserDetails userDetails) throws AlignmentServiceException {
		try{
		List<Integer> affCustIds = null;
		if(affCustomers != null && !affCustomers.isEmpty()){
			affCustIds = new ArrayList<Integer>();
			for(Customer customer : affCustomers){
				affCustIds.add(customer.getId().intValue());
			}
		}
		
		List<CustomerAlignment> validAffCustAlgmts = null;
		if(affCustIds != null && !affCustIds.isEmpty()){
			validAffCustAlgmts = customerAlignmentDAOService.fetchCustAlFrCust(salesposition, affCustIds, userDetails);
		}
		
		return validAffCustAlgmts;
	
		}catch(OpservDataAccessException e) {
			LOGGER.error("********************Issue occurred while getting Valid Affiliated Customer Alignments  ****************** " + e.getMessage());
			Object[] args = new Object[] { "affCustomers" };
			throw new AlignmentServiceException(AlignmentServiceExceptionCode.ALGN_SER_EX_CD_259, "Issue occurred while getting Valid Affiliated Customer Alignments", args, e);
		}
	}

	@Override
	public void unLockCustomerAlignmentsOnApprove(List<CustomerAlignment> customerAlgmnts, UserDetails userDetails) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unLockCustomerAlignmentsOnReject(List<CustomerAlignment> customerAlgmnts, UserDetails userDetails) {
		// TODO Auto-generated method stub
		
	}


	
	
	
}
