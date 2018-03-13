package com.cognizant.opserv.sp.service.employee;

import java.text.ParseException;

import com.cognizant.opserv.sp.exception.EmployeeServiceException;
import com.cognizant.opserv.sp.model.Employee;
import com.cognizant.opserv.sp.model.auth.UserDetails;
// TODO: Auto-generated Javadoc
/**
 * ****************************************************************************.
 *
 * @class EmployeeService contains all the services of employee
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 * ***************************************************************************
 */
public interface EmployeeService {
  
  /**
   * Gets the employee details.
   *
   * @method getEmployeeDetails
   * @param userDetails the user details
   * @return the employee details
   * @throws EmployeeServiceException the employee service exception
   */
  Employee getEmployeeDetails(UserDetails userDetails) throws EmployeeServiceException;
  
  /**
   * Update.
   *
   * @param employee the employee
   * @param userDetails the user details
   * @return the employee
   */
  Employee update(Employee employee,UserDetails userDetails)throws EmployeeServiceException;
   /**
   * Creates the employee.
   *
   * @param employee the employee
   * @param userDetails the user details
   * @return the employee
   * @throws EmployeeServiceException the employee service exception
   * @throws ParseException the parse exception
   */
  Employee createEmployee(Employee employee, UserDetails userDetails)
			throws EmployeeServiceException, ParseException;
  
  
  /**
   * Gets the employee details.
   *
   * @method getEmployeeDetails
   * @param userDetails the user details
   * @return the employee details
   * @throws EmployeeServiceException the employee service exception
   */
  Employee getEmployeeDetailsByUser(UserDetails userDetails) throws EmployeeServiceException;
  

}
