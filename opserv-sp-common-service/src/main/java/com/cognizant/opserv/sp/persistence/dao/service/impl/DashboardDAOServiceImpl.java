/**
 * 
 */
package com.cognizant.opserv.sp.persistence.dao.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cognizant.opserv.sp.common.ChangeRequestStatusType;
import com.cognizant.opserv.sp.core.dao.TChngReqApprDAO;
import com.cognizant.opserv.sp.core.dao.TChngReqDAO;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.persistence.dao.service.DashboardDAOService;
import com.cognizant.peg.core.exception.OpservDataAccessException;

// TODO: Auto-generated Javadoc
/**
 * ****************************************************************************.
 *
 * @class DashboardDAOServiceImpl contains all the Dashboard DAO services 
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 09/06/2016
 * ***************************************************************************
 */
@Component
public class DashboardDAOServiceImpl implements DashboardDAOService {
	
	/** The t chng req dao. */
	@Autowired
	private TChngReqDAO tChngReqDAO;
	
	/** The t chng req appr dao. */
	@Autowired
	private TChngReqApprDAO tChngReqApprDAO;
	

	/* (non-Javadoc)
	 * @see com.cognizant.opserv.sp.persistence.dao.service.DashboardDAOService#getCRCountBySPAndStatus(java.util.List, com.cognizant.opserv.sp.common.ChangeRequestStatusType, com.cognizant.opserv.sp.model.auth.UserDetails)
	 */
	@Override
	public Integer getCRCountBySPAndStatus(List<Long> spIds,
			ChangeRequestStatusType crStatus, UserDetails userDetails)
			throws OpservDataAccessException {
		
		Integer count = 0;
		try{
			Short tenantId = userDetails.getTenantId();
			count = tChngReqDAO.getCRCountBySpsAndStatus(spIds, crStatus.getId(), tenantId);
			return count;
		}catch(RuntimeException e){
			throw new OpservDataAccessException("Exception occur while getting count of CR by SalesPosition and Status ",e);
		}
		
		
	}

	@Override
	public Integer getApproversCountBySPAndStatus(List<Long> spIds,
			ChangeRequestStatusType crStatus, UserDetails userDetails)
			throws OpservDataAccessException {
		
		Integer count = 0;
		try{
			Short tenantId = userDetails.getTenantId();
			count = tChngReqApprDAO.getApproversCountBySpsAndStatus(spIds, crStatus.getId(), tenantId);
			return count;
		}catch(RuntimeException e){
			throw new OpservDataAccessException("Exception occur while getting count of CR by SalesPosition and Status ",e);
		}
	}
}
