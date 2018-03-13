package com.cognizant.opserv.sp.persistence.dao.service;

import java.util.List;
import java.util.Map;

import com.cognizant.opserv.sp.model.EmpSearch;
import com.cognizant.opserv.sp.model.Employee;
import com.cognizant.opserv.sp.model.EmployeeAlignment;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.model.attrb.AttributeDefinition;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.peg.core.exception.OpservDataAccessException;

public interface EmployeeAssignDAOService {
	

	/**
	 * Assgin employee emp.
	 *
	 * @param empList the emp list
	 * @param userDetails the user details
	 * @return the integer
	 */
	public Integer assgEmp(List<EmployeeAlignment> empList,UserDetails userDetails) ;

	/**
	 * Validate t emp assg.
	 *
	 * @param empList the emp list
	 * @param userDetails the user details
	 * @return the map
	 * @throws ParseException the parse exception
	 */
	public Map<String, String> validateTEmpAssg(List<EmployeeAlignment> empList, UserDetails userDetails)throws java.text.ParseException;

    /**
	 * Gets the emp dtls.
	 *
	 * @param index the index
	 * @param noOfResults the no of results
	 * @param salesPos the sales pos
	 * @param userDetails the user details
	 * @return the emp dtls
	 * @throws OpservDataAccessException the opserv data access exception
	 */
	public List<EmployeeAlignment> getEmpDtls(int index,
			int noOfResults,SalesPosition salesPos,
			UserDetails userDetails) throws OpservDataAccessException;

	/**
	 * Gets the emp types.
	 *
	 * @param tenantId the tenant id
	 * @param localeId the locale id
	 * @return the emp types
	 * @throws OpservDataAccessException the opserv data access exception
	 */
	public List<EmpSearch> getEmpTypes(Short tenantId, String localeId) throws OpservDataAccessException ;
	
	/**
	 * Search employees.
	 *
	 * @param empSearchVO the emp search vo
	 * @param start the start
	 * @param results the results
	 * @param tenantId the tenant id
	 * @param localeId the locale id
	 * @return the list
	 * @throws OpservDataAccessException the opserv data access exception
	 */
	public List<Employee> searchEmployees(EmpSearch empSearchVO,
			int start,int results,Short tenantId,String localeId) throws OpservDataAccessException;
	
	/**
	 * Show advanced srch.
	 *
	 * @param salesPosition the sales position
	 * @param userDetails the user details
	 * @return the list
	 * @throws OpservDataAccessException the opserv data access exception
	 */
	public List<AttributeDefinition> showAdvSrch(SalesPosition salesPosition,
			UserDetails userDetails) throws OpservDataAccessException;
}
