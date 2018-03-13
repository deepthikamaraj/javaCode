package com.cognizant.opserv.sp.service.gis;

import java.util.List;

import com.cognizant.opserv.query.IQuery;
import com.cognizant.opserv.sp.exception.AlignmentServiceException;
import com.cognizant.opserv.sp.model.Alignment;
import com.cognizant.opserv.sp.model.CustomerAlignment;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.peg.core.common.ISearchCriteria;

/**
 * ****************************************************************************.
 *
 * @class CustomerGISService contains all the customer gis services 
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 * ***************************************************************************
 */
public interface CustomerGISService {
	
	/**
	 * Gets the customers.
	 *
	 * @param criteria the criteria
	 * @param alignment the alignment
	 * @param userDetails the user details
	 * @return the customers
	 * @throws AlignmentServiceException the alignment service exception
	 */
	List<CustomerAlignment> getCustomers(ISearchCriteria criteria,Alignment alignment,UserDetails userDetails) throws AlignmentServiceException;
	
	/**
	 * Gets the customers.
	 *
	 * @param query the query
	 * @param alignment the alignment
	 * @param userDetails the user details
	 * @return the customers
	 * @throws AlignmentServiceException the alignment service exception
	 */
	List<CustomerAlignment> getCustomers(IQuery query,Alignment alignment,UserDetails userDetails) throws AlignmentServiceException;

}


