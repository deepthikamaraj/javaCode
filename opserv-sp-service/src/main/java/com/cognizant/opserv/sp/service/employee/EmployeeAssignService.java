package com.cognizant.opserv.sp.service.employee;

import java.util.List;
import java.util.Map;

import com.cognizant.opserv.sp.exception.EmployeeServiceException;
import com.cognizant.opserv.sp.model.EmpSearch;
import com.cognizant.opserv.sp.model.Employee;
import com.cognizant.opserv.sp.model.EmployeeAlignment;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.model.attrb.AttributeDefinition;
import com.cognizant.opserv.sp.model.auth.UserDetails;

public interface EmployeeAssignService {
	
	/**
	 * Assign employee.
	 *
	 * @param empList the emp list
	 * @param userDetails the user details
	 * @return the integer
	 * @throws EmployeeServiceException the employee service exception
	 */
	public Integer assgEmp(List<EmployeeAlignment> empList,UserDetails userDetails)throws EmployeeServiceException ;
	
	/**
	 * Validate emp assg.
	 *
	 * @param empList the emp list
	 * @param userDetails the user details
	 * @return the map
	 * @throws EmployeeServiceException the employee service exception
	 */
	public Map<String, String> validateEmpAssg(List<EmployeeAlignment> empList, UserDetails userDetails)throws EmployeeServiceException;
    /**
	 * Gets the emp dtls.
	 *
	 * @param index the index
	 * @param noOfResults the no of results
	 * @param salesPos the sales pos
	 * @param userDetails the user details
	 * @return the emp dtls
	 * @throws EmployeeServiceException the employee service exception
	 */
	public List<EmployeeAlignment> getEmpDtls(int index, int noOfResults,
			SalesPosition salesPos, UserDetails userDetails)
			throws EmployeeServiceException;
	
	/**
	 * Gets the emp types.
	 *
	 * @param tenantId the tenant id
	 * @param localeId the locale id
	 * @return the emp types
	 * @throws EmployeeServiceException the employee service exception
	 */
	public List<EmpSearch> getEmpTypes(Short tenantId, String localeId) throws EmployeeServiceException ;

	/**
	 * Search employees.
	 *
	 * @param empSearchVO the emp search vo
	 * @param start the start
	 * @param results the results
	 * @param tenantId the tenant id
	 * @param localeId the locale id
	 * @param salesPosition the sales position
	 * @return the list
	 * @throws EmployeeServiceException the employee service exception
	 */
	public List<Employee> searchEmployees(EmpSearch empSearchVO,
			int start,int results,Short tenantId,String localeId) throws EmployeeServiceException;
	
	/**
	 * For Advanced Search
	 *
	 * @param salesPosition the sales position
	 * @param userDetails the user details
	 * @return the list
	 * @throws EmployeeServiceException the employee service exception
	 */
	public List<AttributeDefinition> showAdvSrch(SalesPosition salesPosition,
			UserDetails userDetails) throws EmployeeServiceException;
}
