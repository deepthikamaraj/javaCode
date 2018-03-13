package com.cognizant.opserv.sp.persistence.dao.service;

import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.peg.core.exception.OpservDataAccessException;
/**
 * ****************************************************************************.
 *
 * @class SPAssignmentsViewDAOService contains services to update SP view tables 
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 13/04/2016
 * ***************************************************************************
 */
public interface SPAssignmentsViewDAOService {
	/**
	 * 
	 * @param spId
	 * @param entityId
	 * @param userDetails
	 * @throws OpservDataAccessException
	 */
	 void markCustAlgmntFlagAsDirty(Long spId, Long entityId, UserDetails userDetails) throws OpservDataAccessException;
	 
	 /**
	 * @param spId
	 * @param entityId
	 * @param userDetails
	 * @throws OpservDataAccessException
	 */
	void markChangeRequestFlagAsDirty(Long entityId, UserDetails userDetails) throws OpservDataAccessException;
	
	/**
	 * 
	 * @param spId
	 * @param zipCode
	 * @param userDetails
	 * @throws OpservDataAccessException
	 */
	 void markZipSalesPosFlagAsDirty(Long spId, String zipCode, UserDetails userDetails) throws OpservDataAccessException;
	 
	 
}
