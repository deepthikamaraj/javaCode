package com.cognizant.opserv.sp.service.view;

import com.cognizant.opserv.query.IQuery;
import com.cognizant.opserv.sp.exception.ViewServiceException;
import com.cognizant.opserv.sp.model.ViewData;
import com.cognizant.opserv.sp.model.ViewHeader;
import com.cognizant.opserv.sp.model.auth.UserDetails;

/**
 * ****************************************************************************.
 *
 * @interface ViewService contains all the view service
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 11/05/2016
 * ***************************************************************************
 */
public interface ViewService {	
	
	/**
	 * @param userDetails
	 * @return ViewHeader
	 * @throws ViewServiceException
	 */
	ViewHeader getCustomerAlignmentViewHeader(UserDetails userDetails) throws ViewServiceException;	
	
	/**
	 * @param criteria
	 * @param userDetails
	 * @return List<ViewData>
	 * @throws ViewServiceException
	 */
	ViewData getCustomerAlignments(IQuery queryStructure,UserDetails userDetails) throws ViewServiceException;
	
	/**
	 * @param queryStructure
	 * @return Result Count
	 * @throws ViewServiceException
	 */
	int getResultCountForCustomerAlgnmntView(IQuery queryStructure, UserDetails userDetails) throws ViewServiceException;
	
	/**
	 * @param userDetails
	 * @return ViewHeader
	 * @throws ViewServiceException
	 */
	ViewHeader getChangeRequestViewHeader(UserDetails userDetails) throws ViewServiceException;	
	
	/**
	 * @param criteria
	 * @param userDetails
	 * @return List<ViewData>
	 * @throws ViewServiceException
	 */
	ViewData getChangeRequests(IQuery queryStructure,UserDetails userDetails) throws ViewServiceException;


	/**
	 * @param queryStructure
	 * @return Result Count
	 * @throws ViewServiceException
	 */
	int getResultCountForChangeRequestView(IQuery queryStructure, UserDetails userDetails) throws ViewServiceException;
	
	/**
	 * @param userDetails
	 * @return ViewHeader
	 * @throws ViewServiceException
	 */
	ViewHeader getChangeRequestApproverViewHeader(UserDetails userDetails) throws ViewServiceException;	
	
	/**
	 * @param criteria
	 * @param userDetails
	 * @return List<ViewData>
	 * @throws ViewServiceException
	 */
	ViewData getChangeRequestApprovers(IQuery queryStructure,UserDetails userDetails) throws ViewServiceException;


	/**
	 * @param queryStructure
	 * @return Result Count
	 * @throws ViewServiceException
	 */
	int getResultCountForChangeRequestApproverView(IQuery queryStructure, UserDetails userDetails) throws ViewServiceException;
	
	/**
	 * @param userDetails
	 * @return ViewHeader
	 * @throws ViewServiceException
	 */
	ViewHeader getCustomerUniverseViewHeader(UserDetails userDetails) throws ViewServiceException;	
	
	/**
	 * @param criteria
	 * @param userDetails
	 * @return List<ViewData>
	 * @throws ViewServiceException
	 */
	ViewData getCustomerUniverse(IQuery queryStructure,UserDetails userDetails) throws ViewServiceException;


	/**
	 * @param queryStructure
	 * @return Result Count
	 * @throws ViewServiceException
	 */
	int getResultCountForCustomerUniverseView(IQuery queryStructure, UserDetails userDetails) throws ViewServiceException;
	
	
	/**
	 * @param userDetails
	 * @return ViewHeader
	 * @throws ViewServiceException
	 */
	ViewHeader getZipSalesPosViewHeader(UserDetails userDetails) throws ViewServiceException;	
	
	/**
	 * @param criteria
	 * @param userDetails
	 * @return List<ViewData>
	 * @throws ViewServiceException
	 */
	ViewData getZipSalesPos(IQuery queryStructure,UserDetails userDetails) throws ViewServiceException;
	
	/**
	 * @param queryStructure
	 * @return Result Count
	 * @throws ViewServiceException
	 */
	int getResultCountForZipSalesPosView(IQuery queryStructure, UserDetails userDetails) throws ViewServiceException;
	
}
