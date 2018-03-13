package com.cognizant.opserv.sp.persistence.dao.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cognizant.opserv.sp.common.CommonConstants;
import com.cognizant.opserv.sp.core.dao.TAlgmntBussRuleDAO;
import com.cognizant.opserv.sp.core.entity.TAlgmntBussRule;
import com.cognizant.opserv.sp.model.Alignment;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.persistence.dao.service.BusinessRuleDAOService;
import com.cognizant.peg.core.exception.OpservDataAccessException;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;

/**
 * ****************************************************************************.
 *
 * @class BusinessRuleDAOServiceImpl contains all the DAO services for business rule
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 20/04/2016
 * ***************************************************************************
 */
@Component
public class BusinessRuleDAOServiceImpl implements BusinessRuleDAOService{
	
	/** The Constant LOGGER. */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(BusinessRuleDAOServiceImpl.class);
	
	@Autowired
	TAlgmntBussRuleDAO tAlgmntBussRuleDAO;
	
	/**
	 * Checks if is customer movement allowed.
	 *
	 * @param alignment the alignment
	 * @param userDetails the user details
	 * @return true, if is customer movement allowed
	 * @throws OpservDataAccessException the opserv data access exception
	 */
	@Override
	public boolean isCustomerMovementAllowed(Alignment alignment,
			UserDetails userDetails) throws OpservDataAccessException {
		try{
			boolean customerMovementFlag = false;
			Long alignmentId = alignment.getId();
			Long salesTeamId = alignment.getSalesTeam().getId();
			Long businessUnitId = alignment.getSalesTeam().getBusinessUnit().getId();
			List<TAlgmntBussRule> tAlgmntBussRuleList = tAlgmntBussRuleDAO.getBusinessRuleValue(alignmentId, businessUnitId, 
					salesTeamId, CommonConstants.CUSTOMER_MOVEMENT, userDetails.getTenantId());
			if(null != tAlgmntBussRuleList && !tAlgmntBussRuleList.isEmpty()){
				if(tAlgmntBussRuleList.get(0).getValue().equals(CommonConstants.ACTIVE_FLAG) && 
						tAlgmntBussRuleList.get(0).getActiveFlag().equals(CommonConstants.ACTIVE_FLAG)){
					customerMovementFlag = true;
				}
			}
			return customerMovementFlag;
		}
		catch(RuntimeException e){
			LOGGER.error("exception while fetching customer movement flag", e);
			throw new OpservDataAccessException("exception while fetching customer movement flag", e);
		}
	}
	
	/**
	 * Checks if is contiguity check enabled.
	 *
	 * @param alignment the alignment
	 * @param userDetails the user details
	 * @return true, if is contiguity check enabled
	 * @throws OpservDataAccessException the opserv data access exception
	 */
	@Override
	public boolean isContiguityCheckEnabled(Alignment alignment,
			UserDetails userDetails) throws OpservDataAccessException {
		try{
			boolean isContiguityCheckEnabled = false;
			Long alignmentId = alignment.getId();
			Long salesTeamId = alignment.getSalesTeam().getId();
			Long businessUnitId = alignment.getSalesTeam().getBusinessUnit().getId();
			List<TAlgmntBussRule> tAlgmntBussRuleList = tAlgmntBussRuleDAO.getBusinessRuleValue(alignmentId, businessUnitId, 
					salesTeamId, CommonConstants.CONTIGUITY_CHECK_ENABLED, userDetails.getTenantId());
			if(null != tAlgmntBussRuleList && !tAlgmntBussRuleList.isEmpty()){
				if(tAlgmntBussRuleList.get(0).getValue().equals(CommonConstants.ACTIVE_FLAG) && 
						tAlgmntBussRuleList.get(0).getActiveFlag().equals(CommonConstants.ACTIVE_FLAG)){
					isContiguityCheckEnabled = true;
				}
			}
			return isContiguityCheckEnabled;
		}
		catch(RuntimeException e){
			LOGGER.error("exception while fetching customer movement flag", e);
			throw new OpservDataAccessException("exception while fetching customer movement flag", e);
		}
	}

}
