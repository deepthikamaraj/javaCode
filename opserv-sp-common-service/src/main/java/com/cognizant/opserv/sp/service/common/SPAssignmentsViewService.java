package com.cognizant.opserv.sp.service.common;

import com.cognizant.opserv.sp.common.EventType;
import com.cognizant.opserv.sp.exception.ViewServiceException;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.peg.core.exception.OpservDataAccessException;

/**
 * ****************************************************************************.
 *
 * @class SPAssignmentsViewService contains services to update SP view tables 
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 13/04/2016
 * ***************************************************************************
 */
public interface SPAssignmentsViewService {
	
	/**
	 * @param spId
	 * @param entityId
	 * @param userDetails
	 * @throws OpservDataAccessException
	 * @throws ViewServiceException 
	 */
	void markCustAlgmntFlagAsDirty(Long spId, Long entityId, UserDetails userDetails) throws ViewServiceException;
	
	/**
	 * @param spId
	 * @param entityId
	 * @param userDetails
	 * @throws OpservDataAccessException
	 * @throws ViewServiceException 
	 */
	void markChangeRequestFlagAsDirty(Long entityId, UserDetails userDetails) throws ViewServiceException;
	
	/**
	 * @param spId
	 * @param entityId
	 * @param bussObjName
	 * @param userDetails
	 * @throws OpservDataAccessException
	 * @throws ViewServiceException 
	 */
	void updateSPView(Long spId, Long entityId, String bussObjName, EventType eventType, UserDetails userDetails) throws ViewServiceException;
	
	/**
	 * @param spId
	 * @param entityId
	 * @param bussObjName
	 * @param userDetails
	 * @throws OpservDataAccessException
	 * @throws ViewServiceException 
	 */
	void updateSCR(Long spId, Long entityId, String bussObjName, UserDetails userDetails) throws ViewServiceException;
	
	/**
	 * @param spId
	 * @param zipCode
	 * @param userDetails
	 * @throws OpservDataAccessException
	 * @throws ViewServiceException 
	 */
	void markZipSalesPosFlagAsDirty(Long spId, String zipCode, UserDetails userDetails) throws ViewServiceException;
	
	/**
	 * @param spId
	 * @param zipCode
	 * @param bussObjName
	 * @param userDetails
	 * @throws OpservDataAccessException
	 * @throws ViewServiceException 
	 */
	void updateZipSalesPosView(Long spId, String zipCode, String bussObjName, EventType eventType, UserDetails userDetails) throws ViewServiceException;
	
}
