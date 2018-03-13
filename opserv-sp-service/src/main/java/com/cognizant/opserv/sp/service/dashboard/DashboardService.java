package com.cognizant.opserv.sp.service.dashboard;

import java.util.List;
import com.cognizant.opserv.sp.common.ChangeRequestStatusType;
import com.cognizant.opserv.sp.exception.AlignmentServiceException;
import com.cognizant.opserv.sp.exception.DashboardServiceException;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.model.auth.UserDetails;

/**
 * ****************************************************************************.
 *
 * @class DashboardService contains all the Dashboard services 
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 09/06/2016
 * ***************************************************************************
 */
public interface DashboardService {
	
	/**
	 * Gets the product count for sales positions.
	 *
	 * @param salesPositions the sales positions
	 * @param userDetails the user details
	 * @return the product count for sales positions
	 * @throws DashboardServiceException 
	 */
	Long getProductCountForSalesPositions(List<SalesPosition> salesPositions,UserDetails userDetails) throws DashboardServiceException;
	
	/**
	 * Gets the product names for sales positions.
	 *
	 * @param salesPositions the sales positions
	 * @param userDetails the user details
	 * @return the product names for sales positions
	 * @throws DashboardServiceException 
	 */
	List<String> getProductNamesForSalesPositions(List<SalesPosition> salesPositions,UserDetails userDetails) throws DashboardServiceException;
	
	/**
	 * Gets the zip count.
	 *
	 * @param salesPositions the sales positions
	 * @param userDetails the user details
	 * @return the zip count
	 * @throws DashboardServiceException 
	 */
	Long getZipCountForSalesPositions(List<SalesPosition> salesPositions,UserDetails userDetails) throws DashboardServiceException;
	
	/**
	 * Gets the customer count for sales positions.
	 *
	 * @param salesPositions the sales positions
	 * @param userDetails the user details
	 * @return the customer count for sales positions
	 * @throws DashboardServiceException the dashboard service exception
	 * @throws AlignmentServiceException the alignment service exception
	 */
	Long getCustomerCountForSalesPositions(List<SalesPosition> salesPositions,UserDetails userDetails) throws DashboardServiceException;
	
	/**
	 * Gets the geo algd customer count for sales positions.
	 *
	 * @param salesPositions the sales positions
	 * @param userDetails the user details
	 * @return the geo algd customer count for sales positions
	 * @throws DashboardServiceException the dashboard service exception
	 * @throws AlignmentServiceException the alignment service exception
	 */
	Long getGeoAlgdCustomerCountForSalesPositions(List<SalesPosition> salesPositions,UserDetails userDetails) throws DashboardServiceException;
	
	/**
	 * Gets the non geo algd customer count for sales positions.
	 *
	 * @param salesPositions the sales positions
	 * @param userDetails the user details
	 * @return the non geo algd customer count for sales positions
	 * @throws DashboardServiceException the dashboard service exception
	 * @throws AlignmentServiceException the alignment service exception
	 */
	Long getNonGeoAlgdCustomerCountForSalesPositions(List<SalesPosition> salesPositions,UserDetails userDetails) throws DashboardServiceException;
	

	/**
	 * Gets the cR count by sales position and status.
	 *
	 * @param salesPositions the sales positions
	 * @param crStatus the cr status
	 * @param userDetails the user details
	 * @return the cR count by sales position and status
	 * @throws DashboardServiceException the dashboard service exception
	 * @throws AlignmentServiceException the alignment service exception
	 */
	Integer getCRCountBySalesPositionAndStatus(List<SalesPosition> salesPositions, ChangeRequestStatusType crStatus, UserDetails userDetails) throws DashboardServiceException, AlignmentServiceException;
	
	
	/**
	 * Gets the approvers count by sales position and status.
	 *
	 * @param salesPositions the sales positions
	 * @param crStatus the cr status
	 * @param userDetails the user details
	 * @return the approvers count by sales position and status
	 * @throws DashboardServiceException the dashboard service exception
	 * @throws AlignmentServiceException the alignment service exception
	 */
	Integer getApproversCountBySalesPositionAndStatus(List<SalesPosition> salesPositions, ChangeRequestStatusType crStatus, UserDetails userDetails) throws DashboardServiceException, AlignmentServiceException;


	
	
	
	
}
