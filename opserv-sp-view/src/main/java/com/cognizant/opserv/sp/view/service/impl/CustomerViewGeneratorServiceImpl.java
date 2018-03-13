package com.cognizant.opserv.sp.view.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cognizant.opserv.sp.event.messages.EntityChangeEvent;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.view.persistence.dao.service.ICustomerViewGeneratorDAO;
import com.cognizant.opserv.sp.view.service.IViewGenerator;
import com.cognizant.peg.core.exception.OpservDataAccessException;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;

/**
 * ****************************************************************************.
 *
 * @class CustomerViewGeneratorServiceImpl  
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 * ***************************************************************************
 */
@Service("customerViewGeneratorService")
public class CustomerViewGeneratorServiceImpl implements IViewGenerator {

	@Autowired
	@Qualifier("customerViewGeneratorDAO")
	private ICustomerViewGeneratorDAO customerViewGeneratorDAO;
	
	/** The Constant LOGGER. */
	private static final OpservLogger  LOGGER = OpservLoggerFactory.getOpservLogger(CustomerViewGeneratorServiceImpl.class);
	
	
	/* (non-Javadoc)
	 * @see com.cognizant.opserv.sp.view.service.IViewGenerator#generateEntityView(com.cognizant.opserv.sp.event.messages.EntityChangeEvent, com.cognizant.opserv.sp.model.auth.UserDetails)
	 */
	@Override
	public void generateEntityView(EntityChangeEvent entityChangeEvent, UserDetails userDetails)  throws OpservDataAccessException{
		if(null != entityChangeEvent.getSalesPosid() && null != entityChangeEvent.getEntityId() && null != entityChangeEvent.getBussObjName()){
			LOGGER.info("Inside generateEntityView in CustomerViewGeneratorServiceImpl Class");
			try {
				customerViewGeneratorDAO.generateEntityViewDAO(entityChangeEvent.getSalesPosid(), entityChangeEvent.getEntityId(), entityChangeEvent.getBussObjName(), entityChangeEvent.getEventType(), userDetails);
			} catch (OpservDataAccessException e) {
				throw new OpservDataAccessException("Exception occurred in generateEntityView of CustomerViewGeneratorServiceImpl", e);
			}
		}
	}

}
