package com.cognizant.opserv.sp.persistence.dao.service;

import java.util.List;

import com.cognizant.opserv.sp.common.ChangeRequestStatusType;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.peg.core.exception.OpservDataAccessException;

/**
 * ****************************************************************************.
 *
 * @class DashboardDAOService contains all the Dashboard DAO services 
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 09/06/2016
 * ***************************************************************************
 */
public interface DashboardDAOService {

	/**
	 * Gets the cR count by sp and status.
	 *
	 * @param spIds the sp ids
	 * @param crStatus the cr status
	 * @param userDetails the user details
	 * @return the cR count by sp and status
	 * @throws OpservDataAccessException the opserv data access exception
	 */
	Integer getCRCountBySPAndStatus (List<Long> spIds, ChangeRequestStatusType crStatus, UserDetails userDetails) throws OpservDataAccessException;

	/**
	 * Gets the approvers count by sp and status.
	 *
	 * @param spIds the sp ids
	 * @param crStatus the cr status
	 * @param userDetails the user details
	 * @return the approvers count by sp and status
	 * @throws OpservDataAccessException the opserv data access exception
	 */
	Integer getApproversCountBySPAndStatus(List<Long> spIds,ChangeRequestStatusType crStatus, UserDetails userDetails) throws OpservDataAccessException;


}
