/**
 * 
 */
package com.cognizant.opserv.sp.persistence.dao.service;

import com.cognizant.opserv.query.IQuery;
import com.cognizant.opserv.sp.model.ViewData;
import com.cognizant.opserv.sp.model.ViewHeader;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.peg.core.exception.OpservDataAccessException;

/**
 * ****************************************************************************.
 *
 * @class SPViewDAOService contains all the view service
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 11/05/2016
 * ***************************************************************************
 */
public interface SPViewDAOService {
	
	/**
	 * @param userDetails
	 * @param bussObjectName 
	 * @param bussFunctionType 
	 * @return
	 * @throws OpservDataAccessException
	 */
	ViewHeader getEntityAlignmentViewHeader(UserDetails userDetails, String bussObjectName, String bussFunctionType) throws OpservDataAccessException;
	
	/**
	 * @param criteria
	 * @param userDetails
	 * @param entityId 
	 * @param bussFunctionType2 
	 * @return
	 * @throws OpservDataAccessException
	 */
	ViewData getEntityAlignmentViewData(IQuery queryStructure, UserDetails userDetails, String bussObjectName, String bussFunctionType) throws OpservDataAccessException;
	
	/**
	 * @param queryStructure
	 * @param userDetails
	 * @param bussObjectName
	 * @param bussFunctionType
	 * @return result Count
	 * @throws OpservDataAccessException
	 */
	int getResultCountBasedOnSearchCriteria(IQuery queryStructure, UserDetails userDetails, String bussObjectName, String bussFunctionType) throws OpservDataAccessException;


}
