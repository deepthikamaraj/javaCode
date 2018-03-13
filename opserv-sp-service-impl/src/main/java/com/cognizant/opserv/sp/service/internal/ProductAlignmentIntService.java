/**
 * 
 */
package com.cognizant.opserv.sp.service.internal;

import java.util.List;

import com.cognizant.opserv.sp.exception.AlignmentServiceException;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.model.auth.UserDetails;

/**
 * ****************************************************************************.
 *
 * @class ProductAlignmentIntService contains all the internal services for Product
 *        alignment
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 * ***************************************************************************
 */
public interface ProductAlignmentIntService {
	
	/**
	 * Gets the product count for sales position.
	 *
	 * @param salesPosition the salesPosition
	 * @param userDetails the user details
	 * @return the product count for sales position
	 * @throws AlignmentServiceException 
	 */
	long getProductCountForSalesPosition(SalesPosition salesPosition,UserDetails userDetails) throws AlignmentServiceException;
	
	/**
	 * Gets the product names for sales position.
	 *
	 * @param salesPosition the salesPosition
	 * @param userDetails the user details
	 * @return the product names for sales position
	 * @throws AlignmentServiceException 
	 */
	List<String> getProductNamesForSalesPosition(SalesPosition salesPosition,UserDetails userDetails) throws AlignmentServiceException;
	

}
