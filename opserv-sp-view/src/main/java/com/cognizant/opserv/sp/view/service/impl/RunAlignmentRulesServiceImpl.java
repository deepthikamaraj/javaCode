package com.cognizant.opserv.sp.view.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cognizant.opserv.sp.common.CommonConstants;
import com.cognizant.opserv.sp.event.messages.EntityChangeEvent;
import com.cognizant.opserv.sp.exception.AlignmentServiceException;
import com.cognizant.opserv.sp.exception.AlignmentServiceException.AlignmentServiceExceptionCode;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.view.persistence.dao.service.IRunAlignmentRulesDAO;
import com.cognizant.opserv.sp.view.service.IRunAlignmentRulesService;
import com.cognizant.opserv.tenant.ctx.TenantContext;
import com.cognizant.peg.core.exception.OpservDataAccessException;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;

/**
 * ****************************************************************************.
 *
 * @class RunAlignmentRulesServiceImpl  
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 * ***************************************************************************
 */
@Service("runAlignmentRulesService")
public class RunAlignmentRulesServiceImpl implements IRunAlignmentRulesService{
	
	@Autowired
	@Qualifier("runAlignmentRulesDAO")
	private IRunAlignmentRulesDAO runAlignmentRulesDAO;

	/** The Constant LOGGER. */
	private static final OpservLogger  LOGGER = OpservLoggerFactory.getOpservLogger(RunAlignmentRulesServiceImpl.class);
	
	/* (non-Javadoc)
	 * @see com.cognizant.opserv.sp.view.service.IRunAlignmentRulesService#runAlgmntRules(com.cognizant.opserv.sp.messaging.EntityChangeEvent)
	 */
	@Override
	public void runAlgmntRules(EntityChangeEvent entityChangeEvent) throws AlignmentServiceException{
		
		try{
			UserDetails userDetails = entityChangeEvent.getUserDetails();
			if(null != userDetails && null != userDetails.getTenantCode()){
				TenantContext.setTenantKey(userDetails.getTenantCode());
			}
			
			LOGGER.info("Inside runAlgmntRules in RunAlignmentRulesServiceImpl Class");
			if(null != entityChangeEvent.getSalesPosid() && null != entityChangeEvent.getBussObjName()) {
				LOGGER.info("Inside runAlgmntRules in RunAlignmentRulesServiceImpl Class before runAlgmntRules");
				runAlignmentRulesDAO.runAlgmntRules(entityChangeEvent.getSalesPosid(), entityChangeEvent.getEntityId(), entityChangeEvent.getBussObjName(), userDetails);
				LOGGER.info("Inside runAlgmntRules in RunAlignmentRulesServiceImpl Class after runAlgmntRules");
			}
		}
		catch (OpservDataAccessException odae){
			Object[] args = new Object[4];
			args[0] = CommonConstants.SPVIEW;
			args[1] = entityChangeEvent.getSalesPosid();
			args[2] = entityChangeEvent.getEntityId();
			args[3] = entityChangeEvent.getBussObjName();
			throw new AlignmentServiceException(AlignmentServiceExceptionCode.ALGN_SER_EX_CD_206, CommonConstants.SPVIEW, args, odae);
		}
		finally {
			TenantContext.clearTenantKey();
		}
		
		
	}
	
	

}
