package com.cognizant.opserv.sp.service.alignment;

import java.util.List;

import com.cognizant.opserv.sp.exception.AlignmentServiceException;
import com.cognizant.opserv.sp.model.ProductAlignment;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.model.auth.UserDetails;

/******************************************************************************
 * Class Name: ProductAlignmentService
 * 
 * @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * @since 1/4/2016
 *****************************************************************************/
public interface ProductAlignmentService {

	/**
	 * Gets the all product alignments by sales position.
	 * 
	 * @method getAllProductAlignmentsBySalesPosition
	 * @param salesPos
	 *            the sales pos
	 * @param userDetails
	 *            the user details
	 * @return the all product alignments by sales position
	 * @throws AlignmentServiceException
	 *             the alignment service exception
	 */
	List<ProductAlignment> getAllProductAlignmentsBySalesPosition(SalesPosition salesPos, UserDetails userDetails) throws AlignmentServiceException;
	
	/**
	 * 
	 * @method saveProductAlignment
	 * @param productAlignment
	 * @throws AlignmentServiceException
	 */
	void saveProductAlignmentChangeRequest(ProductAlignment productAlignment) throws AlignmentServiceException;

	/**
	 * 
	 * @method updateProductAlignment
	 * @param productAlignment
	 * @throws AlignmentServiceException
	 */
	void updateProductAlignmentChangeRequest(ProductAlignment productAlignment) throws AlignmentServiceException;


}
