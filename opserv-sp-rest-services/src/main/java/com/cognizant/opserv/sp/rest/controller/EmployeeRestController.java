package com.cognizant.opserv.sp.rest.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cognizant.opserv.sp.common.AllocationType;
import com.cognizant.opserv.sp.common.SystemRoleType;
import com.cognizant.opserv.sp.common.util.DateUtil;
import com.cognizant.opserv.sp.exception.EmployeeServiceException;
import com.cognizant.opserv.sp.exception.ProductServiceException;
import com.cognizant.opserv.sp.model.Alignment;
import com.cognizant.opserv.sp.model.BusinessUnit;
import com.cognizant.opserv.sp.model.Employee;
import com.cognizant.opserv.sp.model.EmployeeAlignment;
import com.cognizant.opserv.sp.model.SalesOrgHierarchy;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.model.SalesTeam;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.rest.util.ModelAssembler;
import com.cognizant.opserv.sp.service.employee.EmployeeAssignService;
import com.cognizant.opserv.sp.service.employee.EmployeeService;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.rest.common.StatusCode;
import com.cognizant.peg.web.common.ServiceResponse;
import com.cognizant.peg.web.common.ServiceResult;
import com.cognizant.peg.web.common.ServiceStatus;

/**
 * ****************************************************************************.
 *
 * @class ProductRestController contains to call the rest services for product Dts
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 * ***************************************************************************
 */
@Controller
public class EmployeeRestController {
	
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(EmployeeRestController.class);

	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	EmployeeAssignService employeeAssignService;
	
	
	/**
	 * Gets the product details.
	 *
	 * @method getProductDetails
	 * @param productId the product id
	 * @param userDetails the user details
	 * @return ServiceResponse<List<Product>> - the Json Object of the product details
	 * @throws ProductServiceException the product service exception
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/employee/{staffId}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody ServiceResponse<Employee> getEmployeeDetails(@PathVariable("staffId") Integer staffId, 
				HttpServletRequest request) {

		ServiceStatus serviceStatus = null;
		ServiceResponse serviceResponse = new ServiceResponse();
		ServiceResult serviceResult = new ServiceResult();
		
		try {
			UserDetails userDetails =ModelAssembler.getDefaultUserDetails();
			
			userDetails.setStaffId(staffId);
			serviceResult.setDetail(employeeService.getEmployeeDetails(userDetails));
			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
		} catch (EmployeeServiceException e) {
			serviceResult.setDetail("Exception occured "+e.getMessage());
			serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR, "500 ERROR");
		}
		serviceResponse.setStatus(serviceStatus);
		serviceResponse.setResult(serviceResult);
		LOGGER.info(" Returning REST response with Result.."+serviceResult.getDetail()+" and status "+serviceResponse.getStatus().getCode().getCode()+"-"+serviceResponse.getStatus().getMessage());
		return serviceResponse;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/employee/assgEmp/{staffId}/{alignmentId}/{allocPercentage}/{businessUnitId}/{salesTeamId}/{salesPositionId}/{salesOrgHierarchyId}/{startdate}/{endDate}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody ServiceResponse<Integer>assgEmp(@PathVariable("staffId") Integer staffId,
			@PathVariable("alignmentId") Integer alignmentId,
			@PathVariable("allocPercentage") Integer allocPercentage,
			@PathVariable("businessUnitId") Long businessUnitId,
			@PathVariable("salesTeamId") Long salesTeamId,
			@PathVariable("salesPositionId") Long salesPositionId,
			@PathVariable("salesOrgHierarchyId") Long salesOrgHierarchyId,
			@PathVariable("startdate") String startdate,
			@PathVariable("endDate") String endDate
			,HttpServletRequest request){
		ServiceStatus serviceStatus = null;
		ServiceResponse serviceResponse = new ServiceResponse();
		ServiceResult serviceResult = new ServiceResult();
		Date curDate = DateUtil.getCurrentDate();
		List<EmployeeAlignment> empList=new ArrayList<EmployeeAlignment>();
		try {
			UserDetails userDetails =ModelAssembler.getDefaultUserDetails();
			userDetails.setUserId(1);
			EmployeeAlignment empVO=new EmployeeAlignment();
			Employee employee=new Employee();
			employee.setId(staffId.longValue());
			Alignment alignment=new Alignment();
			alignment.setId(alignmentId.longValue());
			empVO.setAllocPercentage(allocPercentage);
			
			BusinessUnit businessUnit=new BusinessUnit();
			businessUnit.setId(businessUnitId);
			
			SalesTeam salesTeam=new SalesTeam();
			salesTeam.setId(salesTeamId);
			salesTeam.setBusinessUnit(businessUnit);
			alignment.setSalesTeam(salesTeam);
			
			SalesPosition salesPosition=new SalesPosition();
			salesPosition.setId(salesPositionId);
			salesPosition.setAlignmment(alignment);
			SalesOrgHierarchy salesOrgHierarchy=new SalesOrgHierarchy();
			salesOrgHierarchy.setId(salesOrgHierarchyId);
			salesPosition.setHierarchy(salesOrgHierarchy);
			empVO.setSalesPosition(salesPosition);
		//	empVO.setAllocTypeId(AllocationType.PRIMARY.getId());
			empVO.setStartDate(DateUtil.getDefaultFormatDate(startdate));
			empVO.setEndDate(DateUtil.getDefaultFormatDate(endDate));	
			empVO.setSystemRole(SystemRoleType.ADMIN);
			empVO.setAllocation(AllocationType.PRIMARY);
			empVO.setEmployee(employee);
			empList.add(empVO);
			
	serviceResult.setDetail(employeeAssignService.assgEmp(empList, userDetails));
	serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
	
		} catch (Exception e) {
			serviceResult.setDetail("Exception occured "+e.getMessage());
			serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR, "500 ERROR");
		}
		serviceResponse.setStatus(serviceStatus);
		serviceResponse.setResult(serviceResult);
		return serviceResponse;
		
	}


	/**
	 * Gets the employee details by user.
	 *
	 * @param userId the user id
	 * @param request the request
	 * @return the employee details by user
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/employeeDetails/{userId}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody ServiceResponse<Employee> getEmployeeDetailsByUser (@PathVariable("userId") Integer userId,
				HttpServletRequest request) {

		ServiceStatus serviceStatus = null;
		ServiceResponse serviceResponse = new ServiceResponse();
		ServiceResult serviceResult = new ServiceResult();
		
		try {
			UserDetails userDetails =ModelAssembler.getDefaultUserDetails();
			userDetails.setUserId(userId);
			
			serviceResult.setDetail(employeeService.getEmployeeDetailsByUser(userDetails));
			
			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
		} catch (EmployeeServiceException e) {
			serviceResult.setDetail("Exception occured "+e.getMessage());
			serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR, "500 ERROR");
		}
		serviceResponse.setStatus(serviceStatus);
		serviceResponse.setResult(serviceResult);
		LOGGER.info(" Returning REST response with Result.."+serviceResult.getDetail()+" and status "+serviceResponse.getStatus().getCode().getCode()+"-"+serviceResponse.getStatus().getMessage());
		return serviceResponse;
	}
}
