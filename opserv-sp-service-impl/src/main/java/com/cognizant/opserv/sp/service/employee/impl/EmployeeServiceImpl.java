package com.cognizant.opserv.sp.service.employee.impl;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.opserv.sp.exception.EmployeeServiceException;
import com.cognizant.opserv.sp.exception.EmployeeServiceException.EmployeeServiceExceptionCode;
import com.cognizant.opserv.sp.model.Employee;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.persistence.dao.service.EmployeeDAOService;
import com.cognizant.opserv.sp.service.employee.EmployeeService;
import com.cognizant.peg.core.exception.OpservDataAccessException;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
/**
 * ****************************************************************************.
 *
 * @class EmployeeServiceimpl contains all the services of employee
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 * ***************************************************************************
 */
@Service("employeeService")
@Transactional 
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeDAOService employeeDAOService;
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(EmployeeServiceImpl.class);

	/**
	 * Gets the employee details.
	 * 
	 * @method getEmployeeDetails
	 * @param userDetails the user details
	 * @return the employee details
	 * @throws EmployeeServiceException the employee service exception
	 * @method getEmployeeDetails
	 */
	@Override
	public Employee getEmployeeDetails(UserDetails userDetails)
			throws EmployeeServiceException {
		Employee employee = new Employee(); 
		try{
			if(null == userDetails || null == userDetails.getStaffId() || null == userDetails.getTenantId())
			{
                Object[] args  ={"userDetails" , "userDetails"};
				throw new EmployeeServiceException(args );
			}
		    employee	= employeeDAOService.getEmployeeDetails(userDetails.getStaffId(), userDetails.getTenantId());
			
		}catch(OpservDataAccessException e) {
			LOGGER.error(e.getMessage());
		       Object[] args = new Object[]{userDetails.getStaffId()};
		      throw new EmployeeServiceException(EmployeeServiceExceptionCode.EMP_SER_EX_CD_001,"Fetch exception",args,e);
		}
		return employee;
	}

	  /**
	   * Update.
	   *
	   * @param employee the employee
	   * @param userDetails the user details
	   * @return the employee
	   */
	@Override
	public Employee update(Employee employee, UserDetails userDetails) {
		Employee tMpemployee=null;
		try {
			
			tMpemployee=employeeDAOService.updateTEmployee(employee, userDetails);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			}
		return tMpemployee;
	}


   /**
	   * Creates the employee.
	   *
	   * @param employee the employee
	   * @param userDetails the user details
	   * @return the employee
	   * @throws EmployeeServiceException the employee service exception
	   * @throws ParseException the parse exception
	   */

	@Override
	public Employee createEmployee(Employee employee, UserDetails userDetails)
			throws EmployeeServiceException, ParseException {
		Employee employe = new Employee(); 
		try {
			employe	= employeeDAOService.createEmployee(employee, userDetails);
		}
		
		catch(OpservDataAccessException e) {
		       Object[] args = new Object[]{userDetails.getStaffId()};
		      throw new EmployeeServiceException(EmployeeServiceExceptionCode.EMP_SER_EX_CD_005,"Issue while saving employee",args,e);
		}
		return employe;
	}

	/**
	 * Gets the employee details By user id.
	 * 
	 * @method getEmployeeDetails
	 * @param userDetails the user details
	 * @return the employee details
	 * @throws EmployeeServiceException the employee service exception
	 * @method getEmployeeDetails
	 */	
@Override
public Employee getEmployeeDetailsByUser(UserDetails userDetails)
		throws EmployeeServiceException {
	Employee employee = new Employee(); 
	try{
		if(null == userDetails || null == userDetails.getUserId() || null == userDetails.getTenantId())
		{
            Object[] args  ={"userDetails" , "userDetails"};
			throw new EmployeeServiceException(args );
		}
	    employee	= employeeDAOService.getEmployeeDetailsByUser(userDetails.getUserId(), userDetails.getTenantId());
		
	}catch(OpservDataAccessException e) {
		LOGGER.error(e.getMessage());
	       Object[] args = new Object[]{userDetails.getStaffId()};
	      throw new EmployeeServiceException(EmployeeServiceExceptionCode.EMP_SER_EX_CD_001,"Fetch exception",args,e);
	}
	
	return employee;
}

}




