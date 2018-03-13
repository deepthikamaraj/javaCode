package com.cognizant.opserv.sp.service.alignment;

import java.util.List;

import com.cognizant.opserv.sp.exception.AlignmentServiceException;
import com.cognizant.opserv.sp.model.Customer;
import com.cognizant.opserv.sp.model.CustomerProductAlignment;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.model.auth.UserDetails;

/******************************************************************************
 * Class Name: ProductAlignmentService
 * 
 * @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * @since 1/4/2016
 *****************************************************************************/

public interface CustomerProductAlignmentService {
	
	
	/**
	 * Gets the all customer product by sales position.
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
	List<CustomerProductAlignment> getAllCustomerProducts(Customer customer,SalesPosition salesPos,UserDetails userDetails) throws AlignmentServiceException;
	
	/**
	 * Gets the all customer product by sales position.
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
	List<CustomerProductAlignment> getAllCustomerProducts(SalesPosition salesPos,UserDetails userDetails) throws AlignmentServiceException;

}
