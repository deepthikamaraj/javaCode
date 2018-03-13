package com.cognizant.opserv.sp.view.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cognizant.opserv.sp.event.messages.EntityChangeEvent;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.view.persistence.dao.service.IZipViewGeneratorDAO;
import com.cognizant.opserv.sp.view.service.IViewGenerator;
import com.cognizant.peg.core.exception.OpservDataAccessException;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;

/**
 * ****************************************************************************.
 *
 * @class ZipViewGeneratorServiceImpl  
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 * ***************************************************************************
 */

@Service("zipViewGeneratorService")
public class ZipViewGeneratorServiceImpl implements IViewGenerator{
	
	@Autowired
	@Qualifier("zipViewGeneratorDAO")
	private IZipViewGeneratorDAO zipViewGeneratorDAO;

	/** The Constant LOGGER. */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(ZipViewGeneratorServiceImpl.class);

	@Override
	public void generateEntityView(EntityChangeEvent entityChangeEvent, UserDetails userDetails) {
		if(null != entityChangeEvent.getSalesPosid() && null != entityChangeEvent.getZipCode() && null != entityChangeEvent.getBussObjName()){
			LOGGER.info("Inside generateEntityView in ZipViewGeneratorServiceImpl Class");
			try {
				zipViewGeneratorDAO.generateZipViewDAO(entityChangeEvent.getSalesPosid(), entityChangeEvent.getZipCode(), entityChangeEvent.getBussObjName(), entityChangeEvent.getEventType(), userDetails);
			} catch (OpservDataAccessException e) {
				throw new OpservDataAccessException("Exception occurred in generateEntityView of ZipViewGeneratorServiceImpl", e);
			}
		}
	}

}
