package com.cognizant.opserv.sp.persistence.dao.service;

import com.cognizant.opserv.sp.model.Alignment;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.peg.core.exception.OpservDataAccessException;

/**
 * ****************************************************************************.
 *
 * @class BusinessRuleDAOService contains all the DAO services for business rule
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 20/04/2016
 * ***************************************************************************
 */
public interface BusinessRuleDAOService {
	
	/**
	 * Checks if is customer movement allowed.
	 *
	 * @param alignment the alignment
	 * @param userDetails the user details
	 * @return true, if is customer movement allowed
	 * @throws OpservDataAccessException the opserv data access exception
	 */
	public boolean isCustomerMovementAllowed(Alignment alignment,UserDetails userDetails) throws OpservDataAccessException;
	
	/**
	 * Checks if is contiguity check enabled.
	 *
	 * @param alignment the alignment
	 * @param userDetails the user details
	 * @return true, if is contiguity check enabled
	 * @throws OpservDataAccessException the opserv data access exception
	 */
	public boolean isContiguityCheckEnabled(Alignment alignment,UserDetails userDetails)throws OpservDataAccessException;

}
