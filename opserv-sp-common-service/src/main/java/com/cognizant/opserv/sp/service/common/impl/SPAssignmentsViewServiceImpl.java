package com.cognizant.opserv.sp.service.common.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.opserv.sp.common.BussObj;
import com.cognizant.opserv.sp.common.EventType;
import com.cognizant.opserv.sp.common.MessageType;
import com.cognizant.opserv.sp.event.messages.EntityChangeEvent;
import com.cognizant.opserv.sp.event.messages.StoreConstants;
import com.cognizant.opserv.sp.exception.ViewServiceException;
import com.cognizant.opserv.sp.exception.ViewServiceException.ViewServiceExceptionCode;
import com.cognizant.opserv.sp.messaging.GenericPublisher;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.persistence.dao.service.SPAssignmentsViewDAOService;
import com.cognizant.opserv.sp.persistence.dao.service.impl.SPAssignmentsViewDAOServiceImpl;
import com.cognizant.opserv.sp.service.common.SPAssignmentsViewService;
import com.cognizant.peg.core.exception.OpservDataAccessException;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;

@Service
@Qualifier("sPAssignmentsViewServiceImpl")
public class SPAssignmentsViewServiceImpl implements SPAssignmentsViewService {
	
	/** The Constant LOGGER. */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(SPAssignmentsViewDAOServiceImpl.class);
	
	/**
	 * The SPAssignmentsViewDAOService
	 */
	@Autowired
	SPAssignmentsViewDAOService sPAssignmentsViewDAOService;
	
	/**
	 * The GenericPublisher
	 */
	@Autowired
	private GenericPublisher genericPublisher;

	/* (non-Javadoc)
	 * @see com.cognizant.opserv.sp.service.internal.SPAssignmentsViewService#markCustAlgmntFlagAsDirty(java.lang.Long, java.lang.Long, com.cognizant.opserv.sp.model.auth.UserDetails)
	 */
	@Override
	@Transactional
	public void markCustAlgmntFlagAsDirty(Long spId, Long entityId, UserDetails userDetails) throws ViewServiceException {
		LOGGER.info("===============Inside SPAssignmentsViewServiceImpl.markCustAlgmntFlagAsDirty()=====================");
		try{
			sPAssignmentsViewDAOService.markCustAlgmntFlagAsDirty(spId, entityId, userDetails);
		}catch(OpservDataAccessException e) {
			Object[] args = new Object[]{};
		    throw new ViewServiceException(ViewServiceExceptionCode.VIEW_SER_EX_CD_002 ,"markCustAlgmntFlagAsDirty",args,e);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.cognizant.opserv.sp.service.internal.SPAssignmentsViewService#markCustAlgmntFlagAsDirty(java.lang.Long, java.lang.Long, com.cognizant.opserv.sp.model.auth.UserDetails)
	 */
	@Override
	@Transactional
	public void markChangeRequestFlagAsDirty(Long entityId, UserDetails userDetails) throws ViewServiceException {
		LOGGER.info("===============Inside SPAssignmentsViewServiceImpl.markChangeRequestFlagAsDirty()=====================");
		try{
			sPAssignmentsViewDAOService.markChangeRequestFlagAsDirty(entityId, userDetails);
		}catch(OpservDataAccessException e) {
			Object[] args = new Object[]{};
		    throw new ViewServiceException(ViewServiceExceptionCode.VIEW_SER_EX_CD_002 ,"markChangeRequestFlagAsDirty",args,e);
		}
	}
	

	/* (non-Javadoc)
	 * @see com.cognizant.opserv.sp.service.internal.SPAssignmentsViewService#updateSPView(java.lang.Long, java.lang.Long, com.cognizant.opserv.sp.messaging.SPEntityType, com.cognizant.opserv.sp.model.auth.UserDetails)
	 */
	@Override
	public void updateSPView(Long spId, Long entityId, String bussObjName, EventType eventType, UserDetails userDetails) throws ViewServiceException {
		LOGGER.info("===============Inside SPAssignmentsViewServiceImpl.updateSPView()=====================");
		try{
			final EntityChangeEvent entityChangeEventMsg = new EntityChangeEvent();
			if(bussObjName.equals(BussObj.CHANGE_REQUEST_VIEW.getBussObjName())){
				entityChangeEventMsg.setUserDetails(userDetails);
				entityChangeEventMsg.setBussObjName(bussObjName);
				entityChangeEventMsg.setEntityId(entityId);
				entityChangeEventMsg.setMessageType(MessageType.VIEW);
				entityChangeEventMsg.setEventType(eventType);
				
				genericPublisher.sendObjectToQueue(StoreConstants.SP_STORE_QUEUE_NAME, entityChangeEventMsg);
				LOGGER.info("===============Message successfully sent=====================");
			}
			else{
				if(null!=spId){
					entityChangeEventMsg.setSalesPosid(spId);
					entityChangeEventMsg.setUserDetails(userDetails);
					entityChangeEventMsg.setBussObjName(bussObjName);
					entityChangeEventMsg.setEntityId(entityId);
					entityChangeEventMsg.setMessageType(MessageType.VIEW);
					entityChangeEventMsg.setEventType(eventType);
					
					genericPublisher.sendObjectToQueue(StoreConstants.SP_STORE_QUEUE_NAME, entityChangeEventMsg);
					LOGGER.info("===============Message successfully sent=====================");
				}
			}
		}catch(OpservDataAccessException e) {
			Object[] args = new Object[]{};
		    throw new ViewServiceException(ViewServiceExceptionCode.VIEW_SER_EX_CD_002 ,"updateSPView",args,e);
		}
		
	}
	
	/* (non-Javadoc)
	 * @see com.cognizant.opserv.sp.service.internal.SPAssignmentsViewService#updateSCR(java.lang.Long, java.lang.Long, com.cognizant.opserv.sp.messaging.SPEntityType, com.cognizant.opserv.sp.model.auth.UserDetails)
	 */
	@Override
	public void updateSCR(Long spId, Long entityId, String bussObjName, UserDetails userDetails) throws ViewServiceException {
		LOGGER.info("===============Inside SPAssignmentsViewServiceImpl.updateSCR()=====================");
		try{
			final EntityChangeEvent entityChangeEventMsg = new EntityChangeEvent();
			if(null!=spId){
				entityChangeEventMsg.setSalesPosid(spId);
				entityChangeEventMsg.setUserDetails(userDetails);
				entityChangeEventMsg.setBussObjName(bussObjName);
				entityChangeEventMsg.setEntityId(entityId);
				entityChangeEventMsg.setMessageType(MessageType.SCR);
				
				genericPublisher.sendObjectToQueue(StoreConstants.SP_STORE_QUEUE_NAME, entityChangeEventMsg);
				LOGGER.info("===============Message successfully sent=====================");
			}
		}catch(OpservDataAccessException e) {
			Object[] args = new Object[]{};
		    throw new ViewServiceException(ViewServiceExceptionCode.VIEW_SER_EX_CD_002 ,"updateSCR",args,e);
		}
	}
	
	@Override
	@Transactional
	public void markZipSalesPosFlagAsDirty(Long spId, String zipCode, UserDetails userDetails) throws ViewServiceException {
		LOGGER.info("===============Inside SPAssignmentsViewServiceImpl.markZipSalesPosFlagAsDirty()=====================");
		try{
			sPAssignmentsViewDAOService.markZipSalesPosFlagAsDirty(spId, zipCode, userDetails);
		}catch(OpservDataAccessException e) {
			Object[] args = new Object[]{};
		    throw new ViewServiceException(ViewServiceExceptionCode.VIEW_SER_EX_CD_002 ,"markZipSalesPosFlagAsDirty",args,e);
		}
	}
	
	@Override
	public void updateZipSalesPosView(Long spId, String zipCode, String bussObjName, EventType eventType, UserDetails userDetails) throws ViewServiceException {
		LOGGER.info("===============Inside SPAssignmentsViewServiceImpl.updateZipSalesPosView()=====================");
		try{
			final EntityChangeEvent entityChangeEventMsg = new EntityChangeEvent();
			if(null!=spId){
				entityChangeEventMsg.setSalesPosid(spId);
				entityChangeEventMsg.setUserDetails(userDetails);
				entityChangeEventMsg.setBussObjName(bussObjName);
				entityChangeEventMsg.setZipCode(zipCode);
				entityChangeEventMsg.setMessageType(MessageType.VIEW);
				entityChangeEventMsg.setEventType(eventType);
				
				genericPublisher.sendObjectToQueue(StoreConstants.SP_STORE_QUEUE_NAME, entityChangeEventMsg);
				LOGGER.info("===============Message successfully sent=====================");
			}
		}catch(OpservDataAccessException e) {
			Object[] args = new Object[]{};
		    throw new ViewServiceException(ViewServiceExceptionCode.VIEW_SER_EX_CD_002 ,"updateZipSalesPosView",args,e);
		}
	}

}
