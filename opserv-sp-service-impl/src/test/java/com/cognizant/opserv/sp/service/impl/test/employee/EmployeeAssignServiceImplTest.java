package com.cognizant.opserv.sp.service.impl.test.employee;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cognizant.opserv.sp.exception.EmployeeServiceException;
import com.cognizant.opserv.sp.model.Alignment;
import com.cognizant.opserv.sp.model.BusinessUnit;
import com.cognizant.opserv.sp.model.EmpSearch;
import com.cognizant.opserv.sp.model.Employee;
import com.cognizant.opserv.sp.model.EmployeeAlignment;
import com.cognizant.opserv.sp.model.SalesOrgHierarchy;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.model.SalesTeam;
import com.cognizant.opserv.sp.model.attrb.AttributeDefinition;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.service.base.test.BaseTest;
import com.cognizant.opserv.sp.service.employee.EmployeeAssignService;
import com.cognizant.opserv.sp.service.employee.EmployeeService;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;

public class EmployeeAssignServiceImplTest extends BaseTest {
	

	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(EmployeeAssignServiceImplTest.class);
	
	@Autowired
	 private EmployeeService employeeService;
	
	@Autowired
	private EmployeeAssignService employeeAssignService;
	@Test
	public void getEmplDetails() throws EmployeeServiceException {
		List<EmployeeAlignment> empAlignment = new ArrayList<EmployeeAlignment>();
		SalesPosition salesPos = new SalesPosition();
		Alignment align = new Alignment();
		SalesTeam salesTeam = new SalesTeam();
		BusinessUnit businessUnit = new BusinessUnit();
		SalesOrgHierarchy hierarchy = new SalesOrgHierarchy();
		hierarchy.setId(5l);
		businessUnit.setId(2l);
		align.setId(7l);
		salesPos.setId(484l);
		salesTeam.setId(7l);
		salesTeam.setBusinessUnit(businessUnit);
		align.setSalesTeam(salesTeam);
		salesPos.setAlignmment(align);
		salesPos.setHierarchy(hierarchy);
		UserDetails userDetails = new UserDetails();
		userDetails.setLocaleId("en_US");
		userDetails.setTenantId((short) 1);
		empAlignment = employeeAssignService.getEmpDtls(0, -1, salesPos,
				userDetails);
		LOGGER.info(" StaffId :: " + empAlignment.get(0).getId());
		Assert.assertNotNull(empAlignment);

	}	
	
	@Test
	public void getEmployeTypes() throws  EmployeeServiceException {
		 List<EmpSearch> empSearchList = new ArrayList<EmpSearch>();
		Short tenantId = 1;
		String localeId = "en_US";
		empSearchList = employeeAssignService.getEmpTypes(tenantId, localeId);
		Assert.assertNotNull(empSearchList);
		
	}
	
	@Test
	public void getEmployeeSearch() throws  EmployeeServiceException {
		  List<Employee> empList = new ArrayList<Employee>();
		EmpSearch empSearchVO = new EmpSearch();
		int start =0 ;
		int results = -1;
		SalesPosition salesPosition = new  SalesPosition();
		salesPosition.setId(484l);
		Short tenantId = 1;
		empSearchVO.setEmpType("Permanent");
		empSearchVO.setName("Daniel");
		empSearchVO.setCity("Putnam");
		empSearchVO.setState("NY");
		empSearchVO.setCode("150");
		empSearchVO.setZip("10579");
		String localeId = "en_US";
		empList = employeeAssignService.searchEmployees(empSearchVO, start, results, tenantId, localeId);
		Assert.assertNotNull(empList);
		
	}
	
	@Test
	public void getEmployeeAdvSearch() throws  EmployeeServiceException {		
		List<AttributeDefinition> attributeDefList = new ArrayList<AttributeDefinition>();
		SalesPosition salesPosition = new  SalesPosition();
		salesPosition.setId(371l);
		UserDetails userDetails = new UserDetails();
		userDetails.setLocaleId("en_US");
		userDetails.setTenantId((short) 1);			
		attributeDefList = employeeAssignService.showAdvSrch(salesPosition, userDetails);
		Assert.assertNotNull(attributeDefList);
		
	}
		
	

}
