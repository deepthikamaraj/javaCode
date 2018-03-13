package com.cognizant.opserv.sp.service.impl.test.internal;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.opserv.sp.common.BussObj;
import com.cognizant.opserv.sp.common.EventType;
import com.cognizant.opserv.sp.exception.ViewServiceException;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.service.base.test.BaseTest;
import com.cognizant.opserv.sp.service.common.SPAssignmentsViewService;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;

public class SPAssignmentsViewServiceImplTest extends BaseTest{
	
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(SPAssignmentsViewServiceImplTest.class);
	
	/**
	 * The SPAssignmentsViewService
	 */
	@Autowired
	SPAssignmentsViewService sPAssignmentsViewService;
	
	private UserDetails initUserDetails() {
		UserDetails userDtls = new UserDetails();
		userDtls.setTenantCode("am");
		userDtls.setTenantId((short) 1);
		return userDtls;
	}
	
	/**
	 * test method - testMarkCustAlgmntFlagAsDirty
	 * @throws ViewServiceException 
	 */
	@Test
	@Transactional
	@Rollback(false)
	public void testMarkCustAlgmntFlagAsDirty() throws ViewServiceException{
		Long spId = 269L;
		Long entityId = 6900296L;
		UserDetails userDetails = initUserDetails();
		LOGGER.info("============Inside testMarkCustAlgmntFlagAsDirty()================");
		sPAssignmentsViewService.markCustAlgmntFlagAsDirty(spId, entityId, userDetails);
		LOGGER.info("============testMarkCustAlgmntFlagAsDirty() test successful================");
	}
	
	/**
	 * test method - testUpdateAssignmentSPView
	 * @throws ViewServiceException 
	 */
	@Test
	public void testUpdateAssignmentSPView() throws ViewServiceException{
		Long spId = 398L;
		Long entityId = 1083071L;
		String bussObjName = BussObj.CUSTOMER_ALIGNMENT_VIEW.getBussObjName();
		UserDetails userDetails = initUserDetails();
		LOGGER.info("============Inside testUpdateAssignmentSPView()================");
		sPAssignmentsViewService.updateSPView(spId, entityId, bussObjName, EventType.SUBMITTED , userDetails);
		LOGGER.info("============testUpdateAssignmentSPView() test successful================");
	}

	/**
	 * test method - testMarkChangeRequestFlagAsDirty
	 * @throws ViewServiceException 
	 */
	@Test
	@Transactional
	@Rollback(false)
	public void testMarkChangeRequestFlagAsDirty() throws ViewServiceException {
		Long entityId = 1083071L;
		UserDetails userDetails = initUserDetails();
		LOGGER.info("============Inside testMarkChangeRequestFlagAsDirty()================");
		sPAssignmentsViewService.markChangeRequestFlagAsDirty(entityId, userDetails);
		LOGGER.info("============testMarkChangeRequestFlagAsDirty() test successful================");
	}

	/**
	 * test method - testUpdateSCR
	 * @throws ViewServiceException 
	 */
	@Test
	@Transactional
	@Rollback(false)
	public void testUpdateSCR() throws ViewServiceException {
		Long spId = 398L;
		Long entityId = 1083071L;
		String bussObjName = BussObj.CUSTOMER_ALIGNMENT_VIEW.getBussObjName();
		UserDetails userDetails = initUserDetails();
		LOGGER.info("============Inside testMarkChangeRequestFlagAsDirty()================");
		sPAssignmentsViewService.updateSCR(spId, entityId, bussObjName, userDetails);
		LOGGER.info("============testMarkChangeRequestFlagAsDirty() test successful================");
	}

	/**
	 * test method - testMarkZipSalesPosFlagAsDirty
	 * @throws ViewServiceException 
	 */
	@Test
	@Transactional
	@Rollback(false)
	public void testMarkZipSalesPosFlagAsDirty() throws ViewServiceException {
		Long spId = 398L;
		String zipCode = "";
		UserDetails userDetails = initUserDetails();
		LOGGER.info("============Inside testMarkChangeRequestFlagAsDirty()================");
		sPAssignmentsViewService.markZipSalesPosFlagAsDirty(spId, zipCode, userDetails);
		LOGGER.info("============testMarkZipSalesPosFlagAsDirty() test successful================");
		
	}

	/**
	 * test method - testUpdateZipSalesPosView
	 * @throws ViewServiceException 
	 */
	@Test
	@Transactional
	@Rollback(false)
	public void testUpdateZipSalesPosView() throws ViewServiceException {
		Long spId = 398L;
		String zipCode = "";
		String bussObjName = BussObj.CUSTOMER_ALIGNMENT_VIEW.getBussObjName();
		UserDetails userDetails = initUserDetails();
		LOGGER.info("============Inside testUpdateZipSalesPosView()================");
		sPAssignmentsViewService.updateZipSalesPosView(spId, zipCode, bussObjName, EventType.SUBMITTED, userDetails);
		LOGGER.info("============testUpdateZipSalesPosView() test successful================");
	}
	
}
