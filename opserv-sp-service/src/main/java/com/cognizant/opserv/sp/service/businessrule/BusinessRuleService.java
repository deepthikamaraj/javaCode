package com.cognizant.opserv.sp.service.businessrule;

import com.cognizant.opserv.sp.exception.CustomerServiceException;
import com.cognizant.opserv.sp.model.Alignment;
import com.cognizant.opserv.sp.model.auth.UserDetails;

/**
 * ****************************************************************************.
 *
 * @class BusinessRuleService contains all the services for business rule  
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 * ***************************************************************************
 */
public interface BusinessRuleService {
	
	/**
	 * Checks if is customer movement allowed.
	 *
	 * @param alignment the alignment
	 * @param userDetails the user details
	 * @return true, if is customer movement allowed
	 */
	public boolean isCustomerMovementAllowed(Alignment alignment,UserDetails userDetails) throws CustomerServiceException;
	
	/**
	 * Checks if is contiguity check enabled.
	 *
	 * @param alignment the alignment
	 * @param userDetails the user details
	 * @return true, if is contiguity check enabled
	 */
	public boolean isContiguityCheckEnabled(Alignment alignment,UserDetails userDetails)throws CustomerServiceException;

}
