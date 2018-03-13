package com.cognizant.opserv.sp.service.businessrule.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.opserv.sp.exception.CustomerServiceException;
import com.cognizant.opserv.sp.exception.CustomerServiceException.CustomerServiceExceptionCode;
import com.cognizant.opserv.sp.model.Alignment;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.persistence.dao.service.BusinessRuleDAOService;
import com.cognizant.opserv.sp.service.businessrule.BusinessRuleService;
import com.cognizant.peg.core.exception.OpservDataAccessException;

/**
 * ****************************************************************************.
 *
 * @class BusinessRuleServiceImpl contains all the services for business rule  
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 * ***************************************************************************
 */

@Service("businessRuleService")
public class BusinessRuleServiceImpl implements BusinessRuleService{
	
	@Autowired
	BusinessRuleDAOService businessRuleDAOService;
	
	/**
	 * Checks if is customer movement allowed.
	 *
	 * @param alignment the alignment
	 * @param userDetails the user details
	 * @return true, if is customer movement allowed
	 */
	@Override
	public boolean isCustomerMovementAllowed(Alignment alignment,
			UserDetails userDetails) throws CustomerServiceException{
		try{
			if(null == alignment){
				throw new CustomerServiceException(new Object[]{"alignment"});
			}
			return businessRuleDAOService.isCustomerMovementAllowed(alignment, userDetails);
		}
		catch(OpservDataAccessException e){
			throw new CustomerServiceException(CustomerServiceExceptionCode.CUST_SER_EX_CD_006, 
					"exception while fetching customer movement flag", null, e);
		}
	}
	
	/**
	 * Checks if is contiguity check enabled.
	 *
	 * @param alignment the alignment
	 * @param userDetails the user details
	 * @return true, if is contiguity check enabled
	 * @throws CustomerServiceException 
	 */
	@Override
	public boolean isContiguityCheckEnabled(Alignment alignment,
			UserDetails userDetails) throws CustomerServiceException {
		try{
			if(null == alignment){
				throw new CustomerServiceException(new Object[]{"alignment"});
			}
			return businessRuleDAOService.isContiguityCheckEnabled(alignment, userDetails);
		}
		catch(OpservDataAccessException e){
			throw new CustomerServiceException(CustomerServiceExceptionCode.CUST_SER_EX_CD_008, 
					"exception while fetching contiguity flag", null, e);
		}
	}

}
