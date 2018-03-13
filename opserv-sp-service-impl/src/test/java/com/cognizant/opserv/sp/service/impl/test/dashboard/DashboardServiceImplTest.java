/**
 * 
 */
package com.cognizant.opserv.sp.service.impl.test.dashboard;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cognizant.opserv.sp.common.ChangeRequestStatusType;
import com.cognizant.opserv.sp.exception.AlignmentServiceException;
import com.cognizant.opserv.sp.exception.DashboardServiceException;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.service.base.test.BaseTest;
import com.cognizant.opserv.sp.service.dashboard.DashboardService;


/**
 * The Class DashboardServiceImplTest.
 */
public class DashboardServiceImplTest extends BaseTest{
	
	/** The dashboard service. */
	@Autowired
	DashboardService dashboardService;
	
	/**
	 * Sets the up.
	 */
	@BeforeClass
	public static void setUp() {
		System.setProperty("opserv.activeMQ.brokerURL", "tcp://localhost:61616");
		System.setProperty("opserv.config.file","C:/OPSERV/config/opserv-config.properties");
	} 
	
	/**
	 * Gets the cR count test.
	 *
	 * @return the cR count test
	 * @throws AlignmentServiceException the alignment service exception
	 * @throws DashboardServiceException the dashboard service exception
	 */
	@Test
	public void getCRCountTest() throws AlignmentServiceException, DashboardServiceException{
		List<SalesPosition> sps= new ArrayList<SalesPosition>();
		
		SalesPosition sp1 = new SalesPosition();
		sp1.setId(174L);
		SalesPosition sp2 = new SalesPosition();
		sp2.setId(175L);
		
		sps.add(sp1);
		sps.add(sp2);
		
		UserDetails userDts = new UserDetails();
		userDts.setTenantId(Short.parseShort("1"));
		
		Integer count = dashboardService.getCRCountBySalesPositionAndStatus(sps, ChangeRequestStatusType.CANCELLED, userDts);
		Assert.assertNotNull(count);
		
	}

}
