package com.cognizant.opserv.sp.persistence.dao.service;

import java.util.List;

import com.cognizant.opserv.sp.model.Alignment;
import com.cognizant.opserv.sp.model.EmployeeAlignment;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.peg.core.exception.OpservDataAccessException;

/**
 * ****************************************************************************.
 *
 * @class EmployeeAlignmentDAOService contains all the Dao services for employee alignment
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 * ***************************************************************************
 */
public interface EmployeeAlignmentDAOService {
	
	/**
	 * Gets the all employee alignments.
	 *
	 * @param userDetails the user details
	 * @return the all employee alignments
	 * @throws OpservDataAccessException the opserv data access exception
	 */
	List<EmployeeAlignment> getAllEmployeeAlignments(UserDetails userDetails) throws OpservDataAccessException;
	
	/**
	 * Gets the all primary employee alignments.
	 *
	 * @param alignment the alignment
	 * @param userDetails the user details
	 * @return the all primary employee alignments
	 * @throws OpservDataAccessException the opserv data access exception
	 */
	List<EmployeeAlignment> getAllPrimaryEmployeeAlignments(Alignment alignment,UserDetails userDetails) throws OpservDataAccessException;
	
	/**
	 * Gets the all employee alignments by sales postion.
	 *
	 * @param salesPos the sales pos
	 * @param userDetails the user details
	 * @return the all employee alignments by sales postion
	 * @throws OpservDataAccessException the opserv data access exception
	 */
	List<EmployeeAlignment> getAllEmployeeAlignmentsBySalesPostion(SalesPosition salesPos,UserDetails userDetails) throws OpservDataAccessException;

	/**
	 * Fetch email id fr emp.
	 *
	 * @param sp the sp
	 * @param userDts the user dts
	 * @return the string
	 */
	List<String> fetchEmailIdFrEmp(List<Long> srcTarSpIDList, UserDetails userDts);

	/**
	 * Fetch staff id.
	 *
	 * @param sp the sp
	 * @param userDts the user dts
	 * @return the string
	 */
	List<Integer> fetchStaffIds(List<Long> srcTarSpIDList,
			UserDetails userDetails);
	
	
}
