package com.cognizant.opserv.sp.persistence.dao.service;

import java.util.List;

import com.cognizant.opserv.sp.model.Employee;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.peg.core.exception.OpservDataAccessException;

/**
 * ****************************************************************************.
 *
 * @class EmployeeDAOService contains all the DAO services of employee
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 * ***************************************************************************
 */
public interface EmployeeDAOService {
	
	/**
	 * Gets the employee details.
	 *
	 * @param staffId the staff id
	 * @param tenentId the tenent id
	 * @return Employee
	 * @throws OpservDataAccessException the opserv data access exception
	 * @method getEmployeeDetails
	 */
	
	Employee	getEmployeeDetails(Integer staffId , short tenentId) throws OpservDataAccessException;
	  
  	/**
  	 * Update.
  	 *
  	 * @param employee the employee
  	 * @param userDetails the user details
  	 * @return the employee
  	 * @throws OpservDataAccessException the opserv data access exception
  	 */
	Employee updateTEmployee(Employee employee, UserDetails userDetails)throws OpservDataAccessException;
    
/**
	 * Creates Employee.
	 *
	 * @param employee the employee
	 * @param userDetails the user details
	 * @return the employee
	 * @throws OpservDataAccessException the opserv data access exception
	 */
	Employee	createEmployee(Employee employee, UserDetails userDetails) throws OpservDataAccessException;
	
	/**
	 * Gets the employee details by user.
	 *
	 * @param userid the userid
	 * @param tenentId the tenent id
	 * @return Employee
	 * @throws OpservDataAccessException the opserv data access exception
	 * @method getEmployeeDetailsByUser
	 */
	
	Employee	getEmployeeDetailsByUser(Integer userid , short tenentId) throws OpservDataAccessException;

	/**
	 * Fetch chang req approvers.
	 *
	 * @param chngReqId the chng req id
	 * @param targetAprFlg the target apr flg
	 * @param tenantId the tenant id
	 * @param allocTypeId the alloc type id
	 * @return the list
	 * @throws OpservDataAccessException the opserv data access exception
	 */
	public List<Employee> fetchChangReqApprovers(Long chngReqId,
			Character targetAprFlg, Short tenantId, Integer allocTypeId)
			throws OpservDataAccessException;
	
	/**
	 * Gets the emp name by user id.
	 *
	 * @param userid the userid
	 * @param tenentId the tenent id
	 * @return the emp name by user id
	 * @throws OpservDataAccessException the opserv data access exception
	 */
	public String getEmpNameByUserId(Integer userid , short tenentId)throws OpservDataAccessException;
	
	
	/**
	 * Find empployee by sales pos id.
	 *
	 * @param salesPosId the sales pos id
	 * @param allocTypeId the alloc type id
	 * @param tenantId the tenant id
	 * @return the list
	 * @throws OpservDataAccessException the opserv data access exception
	 */
	public List<Employee> findEmpployeeBySalesPosId(Long salesPosId,
			 Integer allocTypeId,Short tenantId)
			throws OpservDataAccessException;
	
}
