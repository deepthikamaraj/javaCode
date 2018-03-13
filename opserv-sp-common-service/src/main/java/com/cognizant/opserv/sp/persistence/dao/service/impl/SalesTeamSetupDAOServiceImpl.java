package com.cognizant.opserv.sp.persistence.dao.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cognizant.opserv.sp.assembler.SalesTeamModelAssembler;
import com.cognizant.opserv.sp.common.util.DateUtil;
import com.cognizant.opserv.sp.core.common.ApplicationConstant;
import com.cognizant.opserv.sp.core.dao.TSalesTeamDAO;
import com.cognizant.opserv.sp.core.entity.TSalesTeam;
import com.cognizant.opserv.sp.core.entity.TSalesTeamId;
import com.cognizant.opserv.sp.model.SalesTeam;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.persistence.dao.service.SalesTeamSetupDAOService;
import com.cognizant.peg.core.common.ISearchCriteria;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.exception.OpservDataAccessException;

/**
 * The Class SalesTeamSetupDAOServiceImpl.
 */
@Component
public class SalesTeamSetupDAOServiceImpl implements SalesTeamSetupDAOService{

	/** The t sales team dao. */
	@Autowired
	private TSalesTeamDAO tSalesTeamDAO;
	
	/** The generic dao. */
	@Autowired
	private GenericDAO genericDAO;
	
	/** The sales team model assembler. */
	@Autowired 
	private SalesTeamModelAssembler salesTeamModelAssembler;
	
	/* (non-Javadoc)
	 * @see com.cognizant.opserv.sp.persistence.dao.service.SalesTeamSetupDAOService#createNewSalesTeam(com.cognizant.opserv.sp.model.SalesTeam, com.cognizant.opserv.sp.model.auth.UserDetails)
	 */
	@Override
	public SalesTeam createNewSalesTeam(SalesTeam salesTeam,
			UserDetails userDetails) throws OpservDataAccessException {
		try{
			TSalesTeam tSalesTeam = new TSalesTeam();
			Short tenantId = userDetails.getTenantId();
			Long salesTeamId = null;
			//new entry for ST with mapper
			salesTeamId = genericDAO.generateID("SALES_TEAM_GRP", ApplicationConstant.TABLE, ApplicationConstant.VALUE_COL, ApplicationConstant.PRIM_KEY_COL,"1");
			TSalesTeam mappedTSalesTeam = salesTeamModelAssembler.updateMapSalesTeamModelToSTEntity(tSalesTeam, salesTeam,salesTeamId, tenantId);
			tSalesTeam =tSalesTeamDAO.createTSalesTeam(mappedTSalesTeam);
			return salesTeamModelAssembler.mapSalesTeamEntityToSalesTeamModel(tSalesTeam, salesTeamId, tenantId);
		}catch(RuntimeException runtimeException){
			throw new OpservDataAccessException("Error during creating new SalesTeam",runtimeException);
		}
	}

	/* (non-Javadoc)
	 * @see com.cognizant.opserv.sp.persistence.dao.service.SalesTeamSetupDAOService#updateSalesTeamInfo(com.cognizant.opserv.sp.model.SalesTeam, com.cognizant.opserv.sp.model.auth.UserDetails)
	 */
	@Override
	public TSalesTeam updateSalesTeamInfo(SalesTeam salesTeam,
			UserDetails userDetails) throws OpservDataAccessException {
		try{
			TSalesTeam tSalesTeam = null;
			Short tenantId = userDetails.getTenantId();
			TSalesTeamId tSalesTeamId = new TSalesTeamId();
			if(salesTeam!=null && salesTeam.getId()!=null && salesTeam.getBusinessUnit()!=null && salesTeam.getBusinessUnit().getId()!=null ){
				tSalesTeamId.setBussUnitId(salesTeam.getBusinessUnit().getId());//(bcz of composite key)
				tSalesTeamId.setSalesTeamId(salesTeam.getId());
			}
			 tSalesTeam = tSalesTeamDAO.findTSalesTeamById(tSalesTeamId);
			//mapped bu model to entity
			 if(tSalesTeam != null){
				 tSalesTeam = salesTeamModelAssembler.updateMapSalesTeamModelToSTEntity(tSalesTeam, salesTeam,salesTeam.getId(), tenantId);	 
			 }
			return tSalesTeamDAO.updateTSalesTeam(tSalesTeam);
		}catch(RuntimeException runtimeException){
			throw new OpservDataAccessException("Error during updating SalesTeam information",runtimeException);
		}
	}

	/* (non-Javadoc)
	 * @see com.cognizant.opserv.sp.persistence.dao.service.SalesTeamSetupDAOService#softDeletionOfSalesTeam(java.lang.Long, java.lang.Long, com.cognizant.opserv.sp.model.auth.UserDetails)
	 */
	@Override
	public TSalesTeam softDeletionOfSalesTeam(Long salesTeamId,Long bussUnitId,
			UserDetails userDetails) throws OpservDataAccessException {
		try{
			TSalesTeamId tSalesTeamId = new TSalesTeamId();
			tSalesTeamId.setBussUnitId(bussUnitId);//(bcz of composite key)
			tSalesTeamId.setSalesTeamId(salesTeamId);
			TSalesTeam tSalesTeam = tSalesTeamDAO.findTSalesTeamById(tSalesTeamId);
			tSalesTeam.setUpdateDt(DateUtil.getCurrentDate());
			tSalesTeam.setUpdatedBy(userDetails.getStaffId());
			tSalesTeam.setActiveFlag('N');
			return tSalesTeamDAO.updateTSalesTeam(tSalesTeam);
		}catch(RuntimeException runtimeException){
			throw new OpservDataAccessException("Error during updating SalesTeam as Inactive",runtimeException);
		}
	}

	/* (non-Javadoc)
	 * @see com.cognizant.opserv.sp.persistence.dao.service.SalesTeamSetupDAOService#getAllDetailsOfSalesTeam(java.lang.Long, java.lang.Long, com.cognizant.opserv.sp.model.auth.UserDetails)
	 */
	@Override
	public SalesTeam getAllDetailsOfSalesTeam(Long salesTeamId,Long bussUnitId,
			UserDetails userDetails) throws OpservDataAccessException {
		try{
			Short tenantId = userDetails.getTenantId();
			TSalesTeamId tSalesTeamId = new TSalesTeamId();
			tSalesTeamId.setBussUnitId(bussUnitId);//(bcz of composite key)
			tSalesTeamId.setSalesTeamId(salesTeamId);
			TSalesTeam tSalesTeam = tSalesTeamDAO.findTSalesTeamById(tSalesTeamId);
			SalesTeam salesTeam = new SalesTeam();
			if(null != tSalesTeam){
				salesTeam = salesTeamModelAssembler.mapSalesTeamEntityToSalesTeamModel(tSalesTeam, salesTeamId, tenantId);
			}
			return salesTeam;
		}catch(RuntimeException e){
			throw new OpservDataAccessException("Error occur during fetching SalesTeam informations", e);
		}
	}

	/* (non-Javadoc)
	 * @see com.cognizant.opserv.sp.persistence.dao.service.SalesTeamSetupDAOService#fetchSalesTeamsByCriteria(com.cognizant.peg.core.common.ISearchCriteria, com.cognizant.opserv.sp.model.auth.UserDetails)
	 */
	@Override
	public List<SalesTeam> fetchSalesTeamsByCriteria(
			ISearchCriteria searchCriteria, UserDetails userDetails)
			throws OpservDataAccessException {
		try{
			List<SalesTeam> salesTeamList = null;
			SalesTeam salesTeam = new SalesTeam();
			Short tenantId = userDetails.getTenantId(); 
			List<TSalesTeam> tSalesTeamList = tSalesTeamDAO.findTSalesTeamBySearchCriteria(searchCriteria);
			if(tSalesTeamList != null && !tSalesTeamList.isEmpty()){
				salesTeamList = new ArrayList<SalesTeam>();
//				salesTeamId = genericDAO.generateID("SALES_TEAM_GRP", ApplicationConstant.TABLE, ApplicationConstant.VALUE_COL, ApplicationConstant.PRIM_KEY_COL,"1");
				for(TSalesTeam tSalesTeam : tSalesTeamList){
					salesTeam = salesTeamModelAssembler.mapSalesTeamEntityToSalesTeamModel(tSalesTeam,null, tenantId);
					salesTeamList.add(salesTeam);
				}
			}
			return salesTeamList;
		}
		catch(RuntimeException e){
			throw new OpservDataAccessException("Error during fetching SalesTeam by search criteria",e);
		}
	}

}
