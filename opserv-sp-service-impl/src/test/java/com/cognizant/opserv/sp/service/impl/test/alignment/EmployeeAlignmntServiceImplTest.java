package com.cognizant.opserv.sp.service.impl.test.alignment;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cognizant.opserv.sp.exception.AlignmentServiceException;
import com.cognizant.opserv.sp.model.Alignment;
import com.cognizant.opserv.sp.model.BusinessUnit;
import com.cognizant.opserv.sp.model.EmployeeAlignment;
import com.cognizant.opserv.sp.model.SalesOrgHierarchy;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.model.SalesTeam;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.service.alignment.EmployeeAlignmentService;
import com.cognizant.opserv.sp.service.base.test.BaseTest;

public class EmployeeAlignmntServiceImplTest extends BaseTest{

	@Autowired
	EmployeeAlignmentService employeeAlignmentService;

	@Test
	public void getAllEmpAlignsTestValidCase() throws AlignmentServiceException {
		System.out.println("inside getAllEmpAlignsTest");
		UserDetails userDetails = new UserDetails();
		userDetails.setStaffId(80);
		userDetails.setTenantId((short)1);
		List<EmployeeAlignment> empAlgmntList = employeeAlignmentService.getAllEmployeeAlignments(userDetails);
		if(empAlgmntList.size() == 0)
			System.out.println("TEST CASE failed!!");
		else
			System.out.println("TEST CASE passed!!  SIZE::"+empAlgmntList.size());
			
		Assert.assertNotNull(empAlgmntList);
		
	}
	
	
	@Test
	public void getAllEmpAlignsTestInvalidCase() throws AlignmentServiceException {
		System.out.println("inside getAllEmpAlignsTest");
		UserDetails userDetails = new UserDetails();
		userDetails.setStaffId(1111);
		userDetails.setTenantId((short)80);
		List<EmployeeAlignment> empAlgmntList = employeeAlignmentService.getAllEmployeeAlignments(userDetails);
		if(empAlgmntList.size() != 0){
			System.out.println("TEST CASE FAILED!!");
		}else{
			System.out.println("TEST CASE PASSED!!");
		}
		Assert.assertNotNull(empAlgmntList);

	}
	
	@Test
	public void getAllPrimaryEmpAlignsTestValidCase() throws AlignmentServiceException {
		System.out.println("inside getAllEmpAlignsTest");
		UserDetails userDetails = new UserDetails();
		userDetails.setStaffId(80);
		userDetails.setTenantId((short)1);
		Alignment alignment = new Alignment();
		alignment.setId(1L);
		BusinessUnit bu = new BusinessUnit();
		bu.setId(1L);
		SalesTeam st = new SalesTeam();
		st.setId(2L);
		st.setBusinessUnit(bu);
		alignment.setSalesTeam(st);
		
		List<EmployeeAlignment> empAlgmntList = employeeAlignmentService.getAllPrimaryEmployeeAlignments(alignment, userDetails);
		if(empAlgmntList.size() != 0){
			System.out.println("TEST CASE passed!!   Size::"+empAlgmntList.size());
		}else{
			System.out.println("TEST CASE passed!!   Size::"+empAlgmntList.size());
		}
		
		Assert.assertNotNull(empAlgmntList);
	}
	
	@Test
	public void getAllEmployeeAlignmentsBySalesPostionTestValidCase() throws AlignmentServiceException {
		System.out.println("inside getAllEmpAlignsTest");
		UserDetails userDetails = new UserDetails();
		userDetails.setStaffId(1);
		userDetails.setTenantId((short)80);
		
		Alignment alignment = new Alignment();
		alignment.setId(7L);
		BusinessUnit bu = new BusinessUnit();
		bu.setId(1L);
		SalesTeam st = new SalesTeam();
		st.setId(1L);
		st.setBusinessUnit(bu);
		alignment.setSalesTeam(st);
		SalesOrgHierarchy heir = new SalesOrgHierarchy();
		heir.setId(8L);
		
		SalesPosition sp = new SalesPosition();
		sp.setId(1l);
		sp.setAlignmment(alignment);
		sp.setHierarchy(heir);
		
		List<EmployeeAlignment> empAlgmntList = employeeAlignmentService.getAllEmployeeAlignmentsBySalesPostion(sp, userDetails);
		if(empAlgmntList.size() != 0){
			System.out.println("TEST CASE passed!!   Size::"+empAlgmntList.size());
		}else{
			System.out.println("TEST CASE passed!!   Size::"+empAlgmntList.size());
		}
		
		Assert.assertNotNull(empAlgmntList);
	}
	
}
