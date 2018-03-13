package com.cognizant.opserv.sp.service.impl.test.changereq;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cognizant.opserv.sp.exception.AlignmentServiceException;
import com.cognizant.opserv.sp.exception.ChangeRequestServiceException;
import com.cognizant.opserv.sp.model.Alignment;
import com.cognizant.opserv.sp.model.BusinessUnit;
import com.cognizant.opserv.sp.model.Customer;
import com.cognizant.opserv.sp.model.SalesOrgHierarchy;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.model.SalesTeam;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.model.cr.ChangeRequest;
import com.cognizant.opserv.sp.service.alignment.SalesPositionService;
import com.cognizant.opserv.sp.service.base.test.BaseTest;
import com.cognizant.opserv.sp.service.changereq.ChangeRequestService;
import com.cognizant.opserv.sp.service.internal.ChangeRequestIntService;

public class ChangeRequestServiceTest extends BaseTest {

	@Autowired
	private ChangeRequestService changeRequestService;
	
	@Autowired
	private ChangeRequestIntService changeRequestIntService;

	@Autowired
	private SalesPositionService salesPositionService;


	@Test
	public void isTransientSPTest() throws ChangeRequestServiceException, AlignmentServiceException {
		Long spId = 1830L;
		UserDetails userDetails = new UserDetails();
		userDetails.setTenantId(new Integer(1).shortValue());
		userDetails.setUserId(1);
		
		boolean res = salesPositionService.isSalesPositionTransient(spId, userDetails);
	}
	
	@Test
	public void dirtyCRTest() throws ChangeRequestServiceException {
		Long spId = 183L;
		UserDetails userDetails = new UserDetails();
		userDetails.setTenantId(new Integer(1).shortValue());
		userDetails.setUserId(1);
		
		boolean res = changeRequestService.changeRequestsMarkAsDirty(spId, userDetails);
	}


	@Test
	public void updateChangeRequestStatusTest() throws ChangeRequestServiceException {
	
		long changeRequestId = 6L;
		Integer statusId = 10;
		
		UserDetails userDetails = new UserDetails();
		userDetails.setTenantId(new Integer(1).shortValue());
		userDetails.setUserId(1);
		
		changeRequestIntService.updateChangeRequestStatus(changeRequestId, statusId, userDetails);

	}
	
	
	
	@Test
	public void submitChangeRequestTest() throws ChangeRequestServiceException {
		ChangeRequest changeRequest = new ChangeRequest();
		changeRequest.setId(new Long(50));
		UserDetails userDetails = new UserDetails();
		userDetails.setTenantId(new Integer(1).shortValue());
		userDetails.setUserId(1);
		userDetails.setUserName("Pramod");
		Assert.assertNotNull(changeRequest);
		changeRequestService.submitChangeRequest(changeRequest,userDetails);

	}

	@Test
	public void getAllChangeRequestsOfCustomerTest() throws ChangeRequestServiceException {

		Customer cust = new Customer();
		SalesPosition salesPos = new SalesPosition();
		SalesOrgHierarchy salesHier = new SalesOrgHierarchy();
		Alignment align = new Alignment();
		SalesTeam sales = new SalesTeam();
		BusinessUnit bussiUnit = new BusinessUnit();

		cust.setId(new Long(4));
		salesPos.setId(new Long(3978));
		salesHier.setId(new Long(46));
		salesPos.setHierarchy(salesHier);
		align.setId(new Long(18));
		sales.setId(new Long(26));
		bussiUnit.setId(new Long(10));
		sales.setBusinessUnit(bussiUnit);
		align.setSalesTeam(sales);
		salesPos.setAlignmment(align);
		salesPos.setTenantId(new Integer(1).shortValue());
		UserDetails user = new UserDetails();
		List<ChangeRequest> changeRquestList = changeRequestService.getAllChangeRequestsOfCustomer(cust, salesPos, user);

		Assert.assertNotNull(changeRquestList.get(0).getId());
	}

	@Test
	public void getAllChangeRequestsTest() throws ChangeRequestServiceException {
		SalesPosition salesPos = new SalesPosition();
		SalesOrgHierarchy salesHier = new SalesOrgHierarchy();
		UserDetails user = new UserDetails();
		salesPos.setId(new Long(5101));
		salesHier.setId(new Long(53));
		salesPos.setHierarchy(salesHier);
		salesPos.setTenantId(new Integer(1).shortValue());
		List<ChangeRequest> changeRquestList = changeRequestService.getAllChangeRequests(salesPos, user);

		Assert.assertNotNull(changeRquestList);
	}

	@Test
	public void getAllChangeRequestsForApprovalTest() throws ChangeRequestServiceException {
		SalesPosition salesPos = new SalesPosition();
		SalesOrgHierarchy salesHier = new SalesOrgHierarchy();
		UserDetails user = new UserDetails();
		salesPos.setId(new Long(5101));
		salesHier.setId(new Long(53));
		salesPos.setHierarchy(salesHier);
		salesPos.setTenantId(new Integer(1).shortValue());
		List<ChangeRequest> changeRquestList = changeRequestService.getAllChangeRequestsForApproval(salesPos, user);

		Assert.assertNotNull(changeRquestList);
	}

	@Test
	public void withDrawlChangeRequestForTest() throws ChangeRequestServiceException {
		ChangeRequest changeRequest = new ChangeRequest();
		changeRequest.setId(new Long(66));
		UserDetails userDetails = new UserDetails();
		userDetails.setTenantId(new Integer(1).shortValue());
		Assert.assertNotNull(changeRequest);
		changeRequestService.withdrawChangeRequest(changeRequest, userDetails);

	}
	

	public void setChangeRequestService(ChangeRequestService changeRequestService) {
		this.changeRequestService = changeRequestService;
	}

}
