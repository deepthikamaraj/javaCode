package com.cognizant.opserv.sp.service.common.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.opserv.sp.common.ChangeRequestStatusType;
import com.cognizant.opserv.sp.common.CustomerCategoryType;
import com.cognizant.opserv.sp.exception.AlignmentServiceException;
import com.cognizant.opserv.sp.exception.AlignmentServiceException.AlignmentServiceExceptionCode;
import com.cognizant.opserv.sp.exception.ViewServiceException;
import com.cognizant.opserv.sp.model.Customer;
import com.cognizant.opserv.sp.model.CustomerAffiliation;
import com.cognizant.opserv.sp.model.CustomerAlignment;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.persistence.dao.service.CustomerAffiliationDAOService;
import com.cognizant.opserv.sp.persistence.dao.service.CustomerAlignmentDAOService;
import com.cognizant.opserv.sp.persistence.dao.service.GeographyAlignmentDAOService;
import com.cognizant.opserv.sp.service.common.CustomerAlignmentCommonService;
import com.cognizant.opserv.sp.service.common.SPAssignmentsViewService;
import com.cognizant.peg.core.exception.OpservDataAccessException;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;


@Service
public class CustomerAlignmentCommonServiceImpl implements
		CustomerAlignmentCommonService {

	@Autowired
	private CustomerAlignmentDAOService customerAlignmentDAOService;
	
	@Autowired
	private SPAssignmentsViewService spAssignmentsViewService;
	
	@Autowired
	private CustomerAffiliationDAOService customerAffiliationService;
	
	@Autowired
	private GeographyAlignmentDAOService geographyAlignmentDAOService;
	/*
	@Autowired
	private CustomerAlignmentDAOService customerAlignmnetDAOService;*/

	private static final OpservLogger LOGGER = OpservLoggerFactory
			.getOpservLogger(CustomerAlignmentCommonServiceImpl.class);
	
	@Override
	@Transactional(rollbackFor = AlignmentServiceException.class)
	public boolean lockCustomerAlignment(CustomerAlignment customerAlgmnt, UserDetails userDetails) throws AlignmentServiceException, ViewServiceException {
		LOGGER.info("========= Lock of Customer Alignment ==============Started");
		try{
			LOGGER.info("=========Update of lock Customer Alignment  ==============Started");
			boolean isLocked = customerAlignmentDAOService.lockCustomerAlignment(customerAlgmnt.getId(), ChangeRequestStatusType.PENDING_FOR_APPROVAL.getId(),userDetails.getUserId());
			LOGGER.info("========= Update of lock Customer Alignment ==============Ended");
			if(isLocked){
				
				LOGGER.info("=========Mark Cust Alignment As Dirty  ==============Started");
				spAssignmentsViewService.markCustAlgmntFlagAsDirty(customerAlgmnt.getSalesPosition().getId(), customerAlgmnt.getCustomer().getId(), userDetails);
				LOGGER.info("=========Mark Cust Alignment As Dirty  ==============Ended");
				return true;
			} else {
				return false;
			}
		
		} catch(OpservDataAccessException e){
			LOGGER.error("********************Issue occurred while Locking the Customer Alignment ****************** ");
			Object[] args = new Object[] {"CustomerAlignment"};
			throw new AlignmentServiceException(AlignmentServiceExceptionCode.ALGN_SER_EX_CD_257,"This Issue occurred while Locking the Customer Alignment", args, e);
		}
	}
	
	@Override
	public Boolean isCustomerAffiliated(Customer customer,
			UserDetails userDetails) {
		// TODO
		LOGGER.info("ZIPMVMT::INSIDE isCustomerAffiliated() Method (code)->"+customer.getCode()+" (id)-> "+customer.getId());
		Integer custID = (int) (long) customer.getId();
		if ( CustomerCategoryType.ACCOUNT.getId().equals(geographyAlignmentDAOService.getCustomerCategory(custID,
				userDetails))) {

			List<CustomerAffiliation> custAffList = customerAffiliationService
					.getCustomerAffiliation(customer, userDetails);

			if (custAffList != null && !custAffList.isEmpty()) {
				CustomerAffiliation customerAffiliation = custAffList
						.get(0);
				if (customer.getId().equals(
						customerAffiliation.getCustomer().getId())
						&& customerAffiliation.isRoot()) {
					//customer is root
					return false;
				}
			}else{
				//customer has no affiliations
				return false;
			}
		} else {
			//customer is not ACCOUNT type so no need to check Affiliation
			return false;
		}
		LOGGER.info("ZIPMVMT::BEFORE LEAVING isCustomerAffiliated() Method ");
		//customer is affiliated customer
		return true;
	}

	@Override
	@Transactional(rollbackFor = AlignmentServiceException.class)
	public boolean unLockCustomerAlignment(CustomerAlignment customerAlgmnt,
			UserDetails userDetails) throws AlignmentServiceException, ViewServiceException {
		LOGGER.info("========= Lock of Customer Alignment ==============Started");
		try{
			LOGGER.info("=========Update of lock Customer Alignment  ==============Started");
			boolean isUnlocked = customerAlignmentDAOService.unlockCustomerAlignment(customerAlgmnt.getId(), ChangeRequestStatusType.APPROVED.getId(),userDetails.getUserId());
			LOGGER.info("========= Update of lock Customer Alignment ==============Ended");
			if(isUnlocked){
				LOGGER.info("=========Mark Cust Alignment As Dirty  ==============Started");
				spAssignmentsViewService.markCustAlgmntFlagAsDirty(customerAlgmnt.getSalesPosition().getId(), customerAlgmnt.getCustomer().getId(), userDetails);
				LOGGER.info("=========Mark Cust Alignment As Dirty  ==============Ended");
				return true;
			} else {
				return false;
			}
		} catch(OpservDataAccessException e){
			LOGGER.error("********************Issue occurred while Locking the Customer Alignment ****************** ");
			Object[] args = new Object[] {"CustomerAlignment"};
			throw new AlignmentServiceException(AlignmentServiceExceptionCode.ALGN_SER_EX_CD_330,"This Issue occurred while Locking the Customer Alignment", args, e);
		}
	}
	
	@Override
	@Transactional
	public boolean unLockCustomerAlignments(List<CustomerAlignment> customerAlgmnt,
			UserDetails userDetails) throws AlignmentServiceException, ViewServiceException {
		LOGGER.info("========= Lock of Customer Alignment ==============Started");
		try{
			List<Long> custAlId = new ArrayList<Long>();
			for(CustomerAlignment customerAlignment :customerAlgmnt){
				custAlId.add(customerAlignment.getId());
			}
			LOGGER.info("=========Update of lock Customer Alignment  ==============Started");
			boolean isUnlocked = customerAlignmentDAOService.unlockCustomerAlignments(custAlId, ChangeRequestStatusType.APPROVED.getId(),userDetails.getUserId());
			LOGGER.info("========= Update of lock Customer Alignment ==============Ended");
			if(isUnlocked){
				for(CustomerAlignment customerAlignment :customerAlgmnt){
				LOGGER.info("=========Mark Cust Alignment As Dirty  ==============Started");
				spAssignmentsViewService.markCustAlgmntFlagAsDirty(customerAlignment.getSalesPosition().getId(), customerAlignment.getCustomer().getId(), userDetails);
				LOGGER.info("=========Mark Cust Alignment As Dirty  ==============Ended");
				}
				return true;
			} else {
				return false;
			}
		} catch(OpservDataAccessException e){
			LOGGER.error("********************Issue occurred while Locking the Customer Alignment ****************** ");
			Object[] args = new Object[] {"CustomerAlignment"};
			throw new AlignmentServiceException(AlignmentServiceExceptionCode.ALGN_SER_EX_CD_330,"This Issue occurred while Locking the Customer Alignment", args, e);
		}
	}
	
}
