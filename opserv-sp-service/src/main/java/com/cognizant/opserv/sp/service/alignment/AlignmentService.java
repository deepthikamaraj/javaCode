package com.cognizant.opserv.sp.service.alignment;

import java.util.List;

import com.cognizant.opserv.sp.exception.AlignmentServiceException;
import com.cognizant.opserv.sp.model.Alignment;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.peg.core.common.ISearchCriteria;
/**
 * ****************************************************************************.
 *
 * @class AlignmentService contains all the Alignment Management services 
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 13/05/2016
 * ***************************************************************************
 */
public interface AlignmentService {
	/**
	 * @method 	fetchAlignments -  this method is to fetch alignment based on search criteria
	 * @param criteria - holds search criteria like alignment name, status etc
	 * @param userDetails - user data
	 * @return List<Alignment>
	 * @throws AlignmentServiceException
	 */
	List<Alignment> fetchAlignments(ISearchCriteria criteria,UserDetails userDetails) throws AlignmentServiceException;
}
