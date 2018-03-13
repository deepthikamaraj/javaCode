package com.cognizant.opserv.sp.service.alignment;

import java.util.List;

import com.cognizant.opserv.sp.exception.AlignmentServiceException;
import com.cognizant.opserv.sp.model.Alignment;
import com.cognizant.opserv.sp.model.EmployeeAlignment;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.model.auth.UserDetails;
/**
 * ****************************************************************************.
 *
 * @class EmployeeAlignmentService contains all the services for employee alignment
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 * ***************************************************************************
 */
public interface EmployeeAlignmentService {
	/**
	 * 
	 * @method getAllEmployeeAlignments
	 * @param userDetails
	 * @return
	 * @throws AlignmentServiceException
	 */
	List<EmployeeAlignment> getAllEmployeeAlignments(UserDetails userDetails) throws AlignmentServiceException;
	/**
	 * 
	 * @method getAllPrimaryEmployeeAlignments
	 * @param alignment
	 * @param userDetails
	 * @return
	 * @throws AlignmentServiceException
	 */
	List<EmployeeAlignment> getAllPrimaryEmployeeAlignments(Alignment alignment,UserDetails userDetails) throws AlignmentServiceException;
	
	
	/**
	 * Gets the all employee alignments by sales postion.
	 *
	 * @method getAllEmployeeAlignmentsBySalesPostion
	 * @param salesPos the sales pos
	 * @param userDetails the user details
	 * @return the all employee alignments by sales postion
	 * @throws AlignmentServiceException the alignment service exception
	 */
	List<EmployeeAlignment> getAllEmployeeAlignmentsBySalesPostion(SalesPosition salesPos,UserDetails userDetails) throws AlignmentServiceException;
	
}
