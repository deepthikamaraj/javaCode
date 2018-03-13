package com.cognizant.opserv.sp.persistence.dao.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cognizant.opserv.sp.assembler.SalesOrgRoleAssembler;
import com.cognizant.opserv.sp.common.util.DateUtil;
import com.cognizant.opserv.sp.core.dao.TRoleListDAO;
import com.cognizant.opserv.sp.core.entity.TRoleList;
import com.cognizant.opserv.sp.model.SalesOrgRole;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.persistence.dao.service.SalesOrgRoleSetupDAOService;
import com.cognizant.peg.core.common.ISearchCriteria;
import com.cognizant.peg.core.exception.OpservDataAccessException;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;

@Component
public class SalesOrgRoleSetupDAOServiceImpl implements
		SalesOrgRoleSetupDAOService {

	private static final OpservLogger LOGGER = OpservLoggerFactory
			.getOpservLogger(SalesOrgRoleSetupDAOServiceImpl.class);
	
	@Autowired
	TRoleListDAO tRoleListDAO;
	
	@Autowired
	SalesOrgRoleAssembler salesOrgRoleAssembler;
	
	@Override
	public SalesOrgRole createSalesOrgRole(SalesOrgRole salesOrgRole,
			UserDetails userDetails) throws OpservDataAccessException {
		
		try {
			TRoleList trList = tRoleListDAO.createTRoleList(salesOrgRoleAssembler.mapSalesOrgRoleModelToEntity(salesOrgRole, userDetails));
			
			SalesOrgRole salesOrgRoleNew = salesOrgRoleAssembler.mapEntityToSalesOrgRoleModel(trList);
			
			return salesOrgRoleNew;
			
		} catch(RuntimeException cause) {
			LOGGER.error(
					"Error occurred in SalesOrgRoleSetupDAOServiceImpl method createSalesOrgRole: "
							+ "salesOrgRole: " + salesOrgRole, cause);
			throw new OpservDataAccessException("Error occur during creating salesorgrole", cause);
		}
	}

	@Override
	public void updateSalesOrgRole(SalesOrgRole salesOrgRole,
			UserDetails userDetails) throws OpservDataAccessException {
		
		try {
			
			TRoleList trList = tRoleListDAO.findTRoleListById(salesOrgRole.getId().intValue());
			
			if (null != trList) {
				SalesOrgRole salesOrgRoleDB = salesOrgRoleAssembler.mapEntityToSalesOrgRoleModel(trList);
				
				tRoleListDAO.updateTRoleList(salesOrgRoleAssembler.mapSalesOrgRoleModelToEntityUpdate(salesOrgRoleDB, salesOrgRole, userDetails));
			}
			
		} catch(RuntimeException cause) {
			LOGGER.error(
					"Error occurred in SalesOrgRoleSetupDAOServiceImpl method updateSalesOrgRole: "
							+ "salesOrgRole: " + salesOrgRole, cause);
			throw new OpservDataAccessException("Error occur during updating salesorgrole", cause);
		}
	}

	@Override
	public void inactiveSalesOrgRole(Long salesOrgRoleId,
			UserDetails userDetails) throws OpservDataAccessException {
		
		try {
			
			TRoleList trList =  tRoleListDAO.findTRoleListById(salesOrgRoleId.intValue());
			
			if (null != trList) {
				trList.setActiveFlag('N');
				trList.setEffEndDt(DateUtil.getCurrentDate());
				trList.setUpdatedBy(userDetails.getStaffId());
				trList.setUpdateDt(DateUtil.getCurrentDate());
				
				tRoleListDAO.updateTRoleList(trList);
			}
			
		} catch(RuntimeException cause) {
			LOGGER.error(
					"Error occurred in SalesOrgRoleSetupDAOServiceImpl method inactiveSalesOrgRole: "
							+ "salesOrgRoleId: " + salesOrgRoleId, cause);
			throw new OpservDataAccessException("Error occur during inactive salesorgrole", cause);
		}
	}

	@Override
	public SalesOrgRole getSalesOrgRoleDetails(Long salesOrgRoleId,
			UserDetails userDetails) throws OpservDataAccessException {
		
		try {
			
			SalesOrgRole salesOrgRoleDetails = null;
			
			TRoleList trList = tRoleListDAO.findTRoleListById(salesOrgRoleId.intValue());
			
			if(null != trList) {
				salesOrgRoleDetails = salesOrgRoleAssembler.mapEntityToSalesOrgRoleModel(trList);
			}
			
		return salesOrgRoleDetails;
			
		} catch(RuntimeException cause) {
			LOGGER.error(
					"Error occurred in SalesOrgRoleSetupDAOServiceImpl method getSalesOrgRoleDetails: "
							+ "salesOrgRoleId: " + salesOrgRoleId, cause);
			throw new OpservDataAccessException("Error occur during fetching details of salesorgrole", cause);
		}
	}

	@Override
	public List<SalesOrgRole> fetchSalesOrgRoles(
			ISearchCriteria searchCriteria, UserDetails userDetails)
			throws OpservDataAccessException {
		
		try {
			
			List<TRoleList> trLists = tRoleListDAO.findTRoleListBySearchCriteria(searchCriteria);
			List<SalesOrgRole> salesOrgRoleList = new ArrayList<SalesOrgRole>();
			
			if (null != trLists) {
				for (TRoleList role : trLists) {
					SalesOrgRole salesOrgRole = salesOrgRoleAssembler.mapEntityToSalesOrgRoleModel(role);
					salesOrgRoleList.add(salesOrgRole);
				}
			}
			
		return salesOrgRoleList;
			
		} catch(RuntimeException cause) {
			LOGGER.error(
					"Error occurred in SalesOrgRoleSetupDAOServiceImpl method fetchSalesOrgRoles: "
							+ "searchCriteria: " + searchCriteria, cause);
			throw new OpservDataAccessException("Error occur during fetch all salesorgrole", cause);
		}
	}

}