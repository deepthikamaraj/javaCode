/*package com.cognizant.opserv.sp.service.impl.test.store;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cognizant.opserv.sp.exception.SalesPositionServiceException;
import com.cognizant.opserv.sp.model.CustomerAlignment;
import com.cognizant.opserv.sp.model.CustomerCallPlan;
import com.cognizant.opserv.sp.model.CustomerProductAlignment;
import com.cognizant.opserv.sp.model.EmployeeAlignment;
import com.cognizant.opserv.sp.model.GeoCustomerAlignment;
import com.cognizant.opserv.sp.model.ProductAlignment;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.service.base.test.BaseTest;
import com.cognizant.opserv.sp.service.internal.SPAssignmentsService;
import com.cognizant.opserv.sp.service.store.SPTargetedAssignmentsService;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;

public class SPTargetedAssignmentsServiceImplTest extends BaseTest {

	private static final OpservLogger LOGGER = OpservLoggerFactory
			.getOpservLogger(SPTargetedAssignmentsServiceImplTest.class);

	@Autowired
	private SPTargetedAssignmentsService iSPAssignmentsService;

	@Autowired
	private SPAssignmentsService spAssignmentsService;
	

	private UserDetails initUserDetails() {
		UserDetails userDetails = new UserDetails();
		userDetails.setTenantId((short) 1);
		return userDetails;
	}

	private SalesPosition initSalesPositionDetails() {
		SalesPosition salesPosition = new SalesPosition();
		salesPosition.setId(386L);
		return salesPosition;
	}

	// test for GetTargetedCustomerAlignments
	@Test
	public void testGetTargetedCustomerAlignments()
			throws SalesPositionServiceException {
		UserDetails userDetails = initUserDetails();
		SalesPosition salesPosition = initSalesPositionDetails();
		List<CustomerAlignment> customerAlignments = iSPAssignmentsService
				.getTargetedCustomerAlignments(salesPosition, userDetails);
		Assert.assertNotNull(customerAlignments);

	}

	// test for GetNonTargetedCustAlignments
	@Test
	public void testGetNonTargetedCustAlignments()
			throws SalesPositionServiceException {
		UserDetails userDetails = initUserDetails();
		SalesPosition salesPosition = initSalesPositionDetails();
		List<CustomerAlignment> customerAlignments = iSPAssignmentsService
				.getNonTargetedCustAlignments(salesPosition, userDetails);
		Assert.assertNotNull(customerAlignments);
		LOGGER.info("Customer Alignmnet ::"+customerAlignments);

	}

	// test for GetTargetedProductAlignments
	@Test
	public void testGetTargetedProductAlignments()
			throws SalesPositionServiceException {
		UserDetails userDetails = initUserDetails();
		SalesPosition salesPosition = initSalesPositionDetails();
		List<ProductAlignment> productAlignments = iSPAssignmentsService
				.getTargetedProductAlignments(salesPosition, userDetails);
		Assert.assertNotNull(productAlignments);

	}

	// test for GetTargetedEmployeeAlignments
	@Test
	public void testGetTargetedEmployeeAlignments()
			throws SalesPositionServiceException {
		UserDetails userDetails = initUserDetails();
		SalesPosition salesPosition = initSalesPositionDetails();
		List<EmployeeAlignment> employeeAlignments = iSPAssignmentsService
				.getTargetedEmployeeAlignments(salesPosition, userDetails);
		Assert.assertNotNull(employeeAlignments);

	}

	// test for GetTargetedCustProdAlignments
	@Test
	public void testGetTargetedCustProdAlignments()
			throws SalesPositionServiceException {
		UserDetails userDetails = initUserDetails();
		SalesPosition salesPosition = initSalesPositionDetails();
		List<CustomerProductAlignment> customerProductAlignments = iSPAssignmentsService
				.getTargetedCustProdAlignments(salesPosition, userDetails);
		Assert.assertNotNull(customerProductAlignments);

	}
	
	// test for GetNonTargetedCustProdAlignments
	@Test
	public void testGetNonTargetedCustProdAlignments()
			throws SalesPositionServiceException {
		UserDetails userDetails = initUserDetails();
		SalesPosition salesPosition = initSalesPositionDetails();
		List<CustomerProductAlignment> customerProductAlignments = iSPAssignmentsService
				.getNonTargetedCustProdAlignments(salesPosition, userDetails);
		Assert.assertNotNull(customerProductAlignments);

	}

	// test for GetTargetedGeoCustomerAlignments
	@Test
	public void testGetTargetedGeoCustomerAlignments()
			throws SalesPositionServiceException {
		UserDetails userDetails = initUserDetails();
		SalesPosition salesPosition = initSalesPositionDetails();
		List<GeoCustomerAlignment> geographyCustAlignments = iSPAssignmentsService
				.getTargetedGeoCustomerAlignments(salesPosition, userDetails);
		Assert.assertNotNull(geographyCustAlignments);

	}

	// test for GetTargetedCallPlans
	@Test
	public void testGetTargetedCallPlans() throws SalesPositionServiceException {
		UserDetails userDetails = initUserDetails();
		SalesPosition salesPosition = initSalesPositionDetails();
		List<CustomerCallPlan> customerCallPlans = iSPAssignmentsService
				.getTargetedCallPlans(salesPosition, userDetails);
		Assert.assertNotNull(customerCallPlans);

	}
	
}
*/