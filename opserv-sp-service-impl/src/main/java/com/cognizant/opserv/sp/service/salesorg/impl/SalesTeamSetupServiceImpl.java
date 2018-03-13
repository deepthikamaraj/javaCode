package com.cognizant.opserv.sp.service.salesorg.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.opserv.sp.exception.SalesTeamServiceException;
import com.cognizant.opserv.sp.exception.SalesTeamServiceException.SalesTeamServiceExceptionCode;
import com.cognizant.opserv.sp.model.SalesTeam;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.persistence.dao.service.SalesTeamSetupDAOService;
import com.cognizant.opserv.sp.service.salesorg.SalesTeamSetupService;
import com.cognizant.peg.core.common.ISearchCriteria;
import com.cognizant.peg.core.exception.OpservDataAccessException;

@Service("salesTeamSetupService")
public class SalesTeamSetupServiceImpl implements SalesTeamSetupService{

	@Autowired
	private SalesTeamSetupDAOService salesTeamSetupDAOService;

	
	@Override
	@Transactional
	public SalesTeam createSalesTeam(SalesTeam salesTeam,
			UserDetails userDetails) throws SalesTeamServiceException {
		try{
			if(salesTeam == null || salesTeam.getBusinessUnit() == null || salesTeam.getBusinessUnit().getId() == null || salesTeam.getName() == null ||
					salesTeam.getStartDate() == null || salesTeam.getCreatedBy() == null ||
					salesTeam.getCreatedDate() == null || userDetails == null || userDetails.getTenantId() == null){
				Object[] missingArgs = new Object[]{"Required information is missing"};
				throw new SalesTeamServiceException(missingArgs);
			}
			return salesTeamSetupDAOService.createNewSalesTeam(salesTeam, userDetails);
		}catch(OpservDataAccessException opservDataAccessException){
			Object[] args = new Object[]{"Business unit creation failed!!"};
			throw new SalesTeamServiceException(SalesTeamServiceExceptionCode.SALESTEAM_SER_EX_CD_001, "create_exception", args, opservDataAccessException);
		}
	}

	@Override
	@Transactional
	public void updateSalesTeam(SalesTeam salesTeam, UserDetails userDetails)
			throws SalesTeamServiceException {
		try{
			if(salesTeam == null || salesTeam.getId() == null ||salesTeam.getBusinessUnit() == null || 
					salesTeam.getBusinessUnit().getId() == null || salesTeam.getName() == null || 
					salesTeam.getCreatedBy() == null || salesTeam.getCreatedDate() == null || 
					userDetails == null || userDetails.getTenantId() == null){
				Object[] missingArgs= new Object[]{"Required information is missing"};
					throw new SalesTeamServiceException(missingArgs);
				}
			salesTeamSetupDAOService.updateSalesTeamInfo(salesTeam, userDetails);
		}catch(OpservDataAccessException dataAccessException){
			Object[] args = new Object[]{"Business unit updation failed!!"};
			throw new SalesTeamServiceException(SalesTeamServiceExceptionCode.SALESTEAM_SER_EX_CD_002, "update_exception", args, dataAccessException);
		}
	}

	@Override
	@Transactional
	public void inactiveSalesTeam(Long salesTeamId, Long bussUnitId, UserDetails userDetails)
			throws SalesTeamServiceException {
		try {
			if(salesTeamId == null || userDetails==null || userDetails.getTenantId() == null){
				Object[] missingArgs = new Object[]{"Required information is missing"};
				throw new SalesTeamServiceException(missingArgs);
			}
			salesTeamSetupDAOService.softDeletionOfSalesTeam(salesTeamId, bussUnitId, userDetails);
		} catch (OpservDataAccessException e) {
			Object[] args = new Object[]{salesTeamId};
			throw new SalesTeamServiceException(SalesTeamServiceExceptionCode.SALESTEAM_SER_EX_CD_003, "inactive_exception", args, e);
		}
	}

	@Override
	@Transactional
	public SalesTeam getSalesTeamDetails(Long salesTeamId,Long bussUnitId,
			UserDetails userDetails) throws SalesTeamServiceException {
		try {
			if(salesTeamId == null || userDetails==null || userDetails.getTenantId() == null){
				Object[] missingArgs = new Object[]{"Required information is missing"};
				throw new SalesTeamServiceException(missingArgs);
			}
			return salesTeamSetupDAOService.getAllDetailsOfSalesTeam(salesTeamId, bussUnitId, userDetails);
		} catch (OpservDataAccessException e) {
			Object[] args = new Object[]{salesTeamId};
			throw new SalesTeamServiceException(SalesTeamServiceExceptionCode.SALESTEAM_SER_EX_CD_004, "fetch_exception", args, e);
		}
	}

	@Override
	@Transactional
	public List<SalesTeam> fetchSalesTeamsBySearchCriteria(ISearchCriteria searchCriteria,
			UserDetails userDetails) throws SalesTeamServiceException {
		try {
			if(searchCriteria == null || userDetails==null || userDetails.getTenantId() == null){
				Object[] missingArgs = new Object[]{"Required information is missing"};
				throw new SalesTeamServiceException(missingArgs);
			}
			return salesTeamSetupDAOService.fetchSalesTeamsByCriteria(searchCriteria, userDetails);
		} catch (OpservDataAccessException e) {
			Object[] args = new Object[]{"criteria is missing!!"};
			throw new SalesTeamServiceException(SalesTeamServiceExceptionCode.SALESTEAM_SER_EX_CD_005, "fetch_exception", args, e);
		}
	}
}
