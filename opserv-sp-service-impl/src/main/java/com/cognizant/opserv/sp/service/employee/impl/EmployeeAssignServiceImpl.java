package com.cognizant.opserv.sp.service.employee.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.opserv.sp.exception.EmployeeServiceException;
import com.cognizant.opserv.sp.exception.EmployeeServiceException.EmployeeServiceExceptionCode;
import com.cognizant.opserv.sp.model.EmpSearch;
import com.cognizant.opserv.sp.model.Employee;
import com.cognizant.opserv.sp.model.EmployeeAlignment;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.model.attrb.AttributeDefinition;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.persistence.dao.service.EmployeeAssignDAOService;
import com.cognizant.opserv.sp.service.employee.EmployeeAssignService;
import com.cognizant.peg.core.exception.OpservDataAccessException;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;

@Service("assignService")
@Transactional
public class EmployeeAssignServiceImpl implements EmployeeAssignService{

	@Autowired
	private EmployeeAssignDAOService employeeAssignDAOService;
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(EmployeeAssignServiceImpl.class);

	/**
	 * Assign employee.
	 *
	 * @param empList the emp list
	 * @param userDetails the user details
	 * @return the integer
	 * @throws EmployeeServiceException the employee service exception
	 */
	@Override
	public Integer assgEmp(List<EmployeeAlignment> empList,
			UserDetails userDetails) throws EmployeeServiceException {
		Integer empalmId = null;

		try {
			 empalmId=employeeAssignDAOService.assgEmp(empList,userDetails);

		} catch (OpservDataAccessException e) {
			LOGGER.error(e.getMessage());
		}
		return empalmId;
	}

	/**
	 * Validate emp assg.
	 *
	 * @param empList the emp list
	 * @param userDetails the user details
	 * @return the map
	 * @throws EmployeeServiceException the employee service exception
	 */
	@Override
	public Map<String, String> validateEmpAssg(List<EmployeeAlignment> empList,
			 UserDetails userDetails)throws EmployeeServiceException {
		Map<String, String>map = null;
		try {
			map=employeeAssignDAOService.validateTEmpAssg(empList,userDetails);
		} catch (OpservDataAccessException e) {
			// TODO Auto-generated catch block
			LOGGER.error(e.getMessage());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			LOGGER.error(e.getMessage());
		}
		return map;
	}


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
	@Override
	public List<EmployeeAlignment> getEmpDtls(int index, int noOfResults,
			SalesPosition salesPos, UserDetails userDetails)
			throws EmployeeServiceException {	
		
		List<EmployeeAlignment>  empAlignmentList = new  ArrayList<EmployeeAlignment>();
		try{
			if(null == userDetails || null == salesPos  )
			{
                Object[] args  ={"userDetails" , "userDetails"};
				throw new EmployeeServiceException(args );
			}
			empAlignmentList	= employeeAssignDAOService.getEmpDtls(index, noOfResults, salesPos, userDetails);
			
		}catch(OpservDataAccessException e) {
		       Object[] args = new Object[]{userDetails.getStaffId()};
		      throw new EmployeeServiceException(EmployeeServiceExceptionCode.EMP_SER_EX_CD_001,"This issue occured while fetching employee details",args,e);
		}
		return empAlignmentList;
		
		
	}

	/**
	 * Gets the emp types.
	 *
	 * @param tenantId the tenant id
	 * @param localeId the locale id
	 * @return the emp types
	 * @throws EmployeeServiceException the employee service exception
	 */
	@Override
	public List<EmpSearch> getEmpTypes(Short tenantId, String localeId) throws EmployeeServiceException {
		List<EmpSearch>  empTypeList = new ArrayList<EmpSearch>();
		
    try {
		if(null!= tenantId && null!= localeId ){
			empTypeList = employeeAssignDAOService.getEmpTypes(tenantId, localeId);
		  }
		else{
			Object[] args = new Object[]{"tenantId","localeId"};
			throw new EmployeeServiceException(args );
		}
	} catch (OpservDataAccessException e) {
		Object[] args = new Object[]{"tenantId","localeId"};
		 throw new EmployeeServiceException(EmployeeServiceExceptionCode.EMP_SER_EX_CD_002,"This issue occured while fetching emp types",args,e);
	}
		return empTypeList;
	}

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
	@Override
	public List<Employee> searchEmployees(EmpSearch empSearchVO, int start,
			int results, Short tenantId, String localeId
			) throws EmployeeServiceException {
		  List<Employee> empList = new ArrayList<Employee>();
		 try {
				if(null!= empSearchVO ){
					empList = employeeAssignDAOService.searchEmployees( empSearchVO,
							 start, results, tenantId, localeId);
				  }
				else{
					Object[] args = new Object[]{"empSearchVO"};
					throw new EmployeeServiceException(args );
				}
			} catch (OpservDataAccessException e) {
				Object[] args = new Object[]{"empSearchVO"};
				 throw new EmployeeServiceException(EmployeeServiceExceptionCode.EMP_SER_EX_CD_003,"This issue occured for search employees",args,e);
			}
		return empList;
	}

	/**
	 * For Advanced Search
	 *
	 * @param salesPosition the sales position
	 * @param userDetails the user details
	 * @return the list
	 * @throws EmployeeServiceException the employee service exception
	 */
	@Override
	public List<AttributeDefinition> showAdvSrch(SalesPosition salesPosition,
			UserDetails userDetails) throws EmployeeServiceException {
		List<AttributeDefinition> attributeDefList = new ArrayList<AttributeDefinition>();
		 try {
				if(null!= userDetails && null!= salesPosition ){
					attributeDefList = employeeAssignDAOService.showAdvSrch( salesPosition,
							userDetails);
				  }
				else{
					Object[] args = new Object[]{"userDetails","salesPosition"};
					throw new EmployeeServiceException(args );
				}
			} catch (OpservDataAccessException e) {
				Object[] args = new Object[]{"userDetails","salesPosition"};
				 throw new EmployeeServiceException(EmployeeServiceExceptionCode.EMP_SER_EX_CD_004,"This issue occured for advanced search employees",args,e);
			}
		return attributeDefList;
	}


}
