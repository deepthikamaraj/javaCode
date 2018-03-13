package com.cognizant.opserv.sp.persistence.dao.service;

import java.util.List;

import com.cognizant.opserv.sp.exception.AlignmentServiceException;
import com.cognizant.opserv.sp.exception.DashboardServiceException;
import com.cognizant.opserv.sp.model.ProductAlignment;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.peg.core.exception.OpservDataAccessException;

/**
 * ****************************************************************************.
 *
 * @class ProductAlignmentDAOService contains all Dao the services for product  alignment
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 * ***************************************************************************
 */
public interface ProductAlignmentDAOService {
	
	/**
	 * @Method getAllProductAlignmentsBySalesPosition - This method is to fetch the
	 *         Products Alignment dts By Sales Position
	 * @param salesPos
	 * @param userDetails
	 * @return List<ProductAlignment> - list of the ProductAlignment Details
	 * @throws OpservDataAccessException
	 */
	List<ProductAlignment> getAllProductAlignmentsBySalesPosition(
			SalesPosition salesPos, UserDetails userDetails)
			throws OpservDataAccessException;
	/**
	 * 
	 * @method saveProductAlignment
	 * @param productAlignment
	 * @throws OpservDataAccessException
	 */
	void saveProductAlignmentChangeRequest(ProductAlignment productAlignment) throws OpservDataAccessException;

	/**
	 * 
	 * @method updateProductAlignment
	 * @param productAlignment
	 * @throws OpservDataAccessException
	 */
	void updateProductAlignmentChangeRequest(ProductAlignment productAlignment) throws OpservDataAccessException;
		
	/**
	 * Gets the prd al list for sp.
	 *
	 * @param salesPosition the sales position
	 * @param userDetails the user details
	 * @param prdIdList the prd id list
	 * @return the prd al list for sp
	 */
	List<ProductAlignment> getPrdAlListForSP(SalesPosition salesPosition,
			UserDetails userDetails, List<Long> prdIdList)throws OpservDataAccessException;
	
	/**
	 * Gets the product count for sales position.
	 *
	 * @param spId the sp id
	 * @param userDetails the user details
	 * @return the product count for sales position
	 */
	long getProductCountForSalesPosition(SalesPosition salesPosition,UserDetails userDetails)throws OpservDataAccessException;
	
	/**
	 * Gets the product names for sales position.
	 *
	 * @param salesPosition the salesPosition
	 * @param userDetails the user details
	 * @return the product names for sales position
	 * @throws AlignmentServiceException 
	 */
	List<String> getProductNamesForSalesPosition(SalesPosition salesPosition,UserDetails userDetails) throws OpservDataAccessException;
	
	/**
	 * Gets the product count for sales position.
	 *
	 * @param salesPositions the sales positions
	 * @param userDetails the user details
	 * @return the product count for sales position
	 * @throws DashboardServiceException 
	 */
	Long getProductCountForSalesPosition(List<SalesPosition> salesPositions,
			UserDetails userDetails) throws DashboardServiceException;
	
	/**
	 * Gets the product names for sales position.
	 *
	 * @param salesPositions the sales positions
	 * @param userDetails the user details
	 * @return the product names for sales position
	 * @throws DashboardServiceException 
	 */
	List<String> getProductNamesForSalesPosition(
			List<SalesPosition> salesPositions, UserDetails userDetails) throws DashboardServiceException;
}
