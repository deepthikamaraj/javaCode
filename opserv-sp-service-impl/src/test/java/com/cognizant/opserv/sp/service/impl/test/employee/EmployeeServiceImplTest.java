package com.cognizant.opserv.sp.service.impl.test.employee;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cognizant.opserv.sp.exception.EmployeeServiceException;
import com.cognizant.opserv.sp.model.Contact;
import com.cognizant.opserv.sp.model.Employee;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.service.base.test.BaseTest;
import com.cognizant.opserv.sp.service.employee.EmployeeService;
import com.cognizant.opserv.sp.service.impl.test.alignment.CustomerAffiliationServiceImplTest;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.opserv.sp.common.EmployeeStatusType;
import com.cognizant.opserv.sp.common.EmploymentType;
import com.cognizant.opserv.sp.common.GenderType;
import com.cognizant.opserv.sp.model.Address;

public class EmployeeServiceImplTest extends BaseTest {
	
	
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(CustomerAffiliationServiceImplTest.class);
	
	@Autowired
	 private EmployeeService employeeService;
	
	
		@Test
		public void getEmployeeDetails() throws  EmployeeServiceException {
			
			
			UserDetails userDetails = new UserDetails();
			userDetails.setStaffId(101);
			userDetails.setTenantId((short)1);
			Employee employee 	= employeeService.getEmployeeDetails(userDetails);
					
			LOGGER.info(" StaffId :: " + employee.getId());
			LOGGER.info(" FirstName :: " + employee.getFirstName());
			LOGGER.info(" FirstName :: " + employee.getName());
			Assert.assertNotNull(employee);
			
		}		
		
		@Test
	 	public void createEmployee() throws  EmployeeServiceException, ParseException {
			
		UserDetails userDetails = new UserDetails();
		userDetails.setUserId(2);
		userDetails.setTenantId((short) 1);
		Employee employee = new Employee();
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
		String dateInString = "08-31-1982";
		Date date = sdf.parse(dateInString);
		employee.setDateOfBirth(date);
		//employee.setId(2663L);
		employee.setFirstName("firstanm1");
		employee.setMiddleName("middlenm1");
		employee.setLastName("lastnm");
		employee.setName("Name");
		employee.setCode("Code");
		employee.setGender(GenderType.getGenderType("M"));
		employee.setOrgName("OrgNm");
		employee.setClientCode("10061");
		employee.setDivCode("10031");
		employee.setEndDate(new Date());
		employee.setJobTitle("JobTitle");
		employee.setRoleName("Rolename");
		employee.setQualCode(4);
		employee.setNamePrefix("Mr");
		// employee.setNameSuffix();
		employee.setTerminationDate(new Date());
		employee.setJoiningDate(new Date());
		employee.setCreatedBy(1);
		employee.setCreatedDate(new Date());
		employee.setManagerCode(2);
		employee.setEmployeeIdentifier(EmploymentType.getEmploymentType(1));
		employee.setStatus(EmployeeStatusType.getEmployeeStatusType(1));
		Address address = new Address();
		Contact empContact = new  Contact();
		empContact.setEmail("test@gmail.com");
		empContact.setContactExtension("contactExtension");
		empContact.setCreatedBy(employee.getCreatedBy());
		empContact.setCreatedDate(new Date());
		employee.setEmployeeContact(empContact);
		address.setAddressLine("addressLine1");
		address.setAddressLine2("addressLine2");
		address.setAddressLine3("addressLine3");
		address.setAddressLine4("addressLine4");
		address.setAddressType(1);
		address.setCity("city");
		address.setCountry("country");
		address.setDoorNumber("12");
		address.setPostalCode("1234");
		address.setCreatedBy(1);
		address.setPrimaryAddr(true);
		employee.setEmployeeAdress(address);
		Employee emp = employeeService.createEmployee(employee, userDetails);

		LOGGER.info(" StaffId :: " + employee.getId());
		LOGGER.info(" FirstName :: " + employee.getFirstName());
		LOGGER.info(" FirstName :: " + employee.getName());
		Assert.assertNotNull(emp.getId());

	}
		
		@Test
		public void getEmployeeDetailsByUser() throws  EmployeeServiceException {
			
			
			UserDetails userDetails = new UserDetails();
			userDetails.setUserId(102);
			userDetails.setTenantId((short)1);
			Employee employee 	= employeeService.getEmployeeDetailsByUser(userDetails);
					
			LOGGER.info(" StaffId :: " + employee.getId());
			LOGGER.info(" FirstName :: " + employee.getFirstName());
			LOGGER.info(" FirstName :: " + employee.getName());
			Assert.assertNotNull(employee);
			
		}			
		
		
		

	}
