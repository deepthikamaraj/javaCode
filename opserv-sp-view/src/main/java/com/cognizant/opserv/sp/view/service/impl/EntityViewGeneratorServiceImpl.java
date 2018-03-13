package com.cognizant.opserv.sp.view.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cognizant.opserv.sp.common.BussObj;
import com.cognizant.opserv.sp.common.CommonConstants;
import com.cognizant.opserv.sp.event.messages.EntityChangeEvent;
import com.cognizant.opserv.sp.exception.AlignmentServiceException;
import com.cognizant.opserv.sp.exception.AlignmentServiceException.AlignmentServiceExceptionCode;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.view.service.EntityViewGeneratorService;
import com.cognizant.opserv.sp.view.service.IViewGenerator;
import com.cognizant.opserv.tenant.ctx.TenantContext;
import com.cognizant.peg.core.exception.OpservDataAccessException;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;

/**
 * ****************************************************************************.
 *
 * @class EntityViewGeneratorServiceImpl  
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 * ***************************************************************************
 */
@Service
public class EntityViewGeneratorServiceImpl implements EntityViewGeneratorService {

	@Autowired
	@Qualifier("customerViewGeneratorService")
	private IViewGenerator customerViewGeneratorService;
	
	@Autowired
	@Qualifier("zipViewGeneratorService")
	private IViewGenerator zipViewGeneratorService;
	
	@Autowired
	@Qualifier("crViewGeneratorService")
	private IViewGenerator crViewGeneratorService;
	
	
	/** The Constant LOGGER. */
	private static final OpservLogger  LOGGER = OpservLoggerFactory.getOpservLogger(EntityViewGeneratorServiceImpl.class);
	
	/* (non-Javadoc)
	 * @see com.cognizant.opserv.sp.view.service.EntityViewGeneratorService#generateView(com.cognizant.opserv.sp.messaging.EntityChangeEvent)
	 */
	public void generateView(EntityChangeEvent entityChangeEvent) throws AlignmentServiceException {
		
		try{
			UserDetails userDetails = entityChangeEvent.getUserDetails();
			if(null != userDetails && null != userDetails.getTenantCode()){
				TenantContext.setTenantKey(userDetails.getTenantCode());
			}
			LOGGER.info("Inside generateView in EntityViewGeneratorServiceImpl Class");
			if(null != entityChangeEvent.getBussObjName()) {
				IViewGenerator viewGeneratorService = getViewCreator(entityChangeEvent.getBussObjName());
				if(null != viewGeneratorService){
					LOGGER.info("Inside generateView in EntityViewGeneratorServiceImpl Class before generateEntityView");
					viewGeneratorService.generateEntityView(entityChangeEvent, userDetails);
					LOGGER.info("Inside generateView in EntityViewGeneratorServiceImpl Class after generateEntityView");
				}
			}
		}
		catch (OpservDataAccessException odae){
			Object[] args = new Object[5];
			args[0] = CommonConstants.SPVIEW;
			args[1] = entityChangeEvent.getSalesPosid();
			args[2] = entityChangeEvent.getEntityId();
			args[3] = entityChangeEvent.getBussObjName();
			args[4] = entityChangeEvent.getEventType();
			throw new AlignmentServiceException(AlignmentServiceExceptionCode.ALGN_SER_EX_CD_206, CommonConstants.SPVIEW, args, odae);
		}
		finally {
			TenantContext.clearTenantKey();
		}
		
	}
	
	/**
	 * @param entityType
	 * @return
	 */
	private IViewGenerator getViewCreator(String entityType) { 
		
		if (BussObj.CUSTOMER_ALIGNMENT_VIEW.getBussObjName().equalsIgnoreCase(entityType)) {
			return customerViewGeneratorService;
		} 
		else if (BussObj.ZIP_SALESPOS_VIEW.getBussObjName().equalsIgnoreCase(entityType)) {
			return zipViewGeneratorService;
		} 
		else if (BussObj.CHANGE_REQUEST_VIEW.getBussObjName().equalsIgnoreCase(entityType)) {
			return crViewGeneratorService;
		} 
		return null;
	}

	

}
