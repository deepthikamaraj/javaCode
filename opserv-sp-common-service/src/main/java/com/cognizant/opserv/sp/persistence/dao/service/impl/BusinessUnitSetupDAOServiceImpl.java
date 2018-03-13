package com.cognizant.opserv.sp.persistence.dao.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cognizant.opserv.sp.assembler.BusinessUnitModelAssembler;
import com.cognizant.opserv.sp.common.util.DateUtil;
import com.cognizant.opserv.sp.core.dao.TBussUnitDAO;
import com.cognizant.opserv.sp.core.entity.TBussUnit;
import com.cognizant.opserv.sp.model.BusinessUnit;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.persistence.dao.service.BusinessUnitSetupDAOService;
import com.cognizant.peg.core.common.ISearchCriteria;
import com.cognizant.peg.core.exception.OpservDataAccessException;


/**
 * The Class BusinessUnitSetupDAOServiceImpl.
 */
@Component 
public class BusinessUnitSetupDAOServiceImpl implements BusinessUnitSetupDAOService{

	/** The buss unit dao. */
	@Autowired
	private TBussUnitDAO bussUnitDAO;
	
	/** The business unit model assembler. */
	@Autowired
	private BusinessUnitModelAssembler businessUnitModelAssembler;
	
	/* (non-Javadoc)
	 * @see com.cognizant.opserv.sp.persistence.dao.service.BusinessUnitSetupDAOService#createNewBusinessUnit(com.cognizant.opserv.sp.model.BusinessUnit, com.cognizant.opserv.sp.model.auth.UserDetails)
	 */
	@Override
	public BusinessUnit createNewBusinessUnit(BusinessUnit businessUnit,
			UserDetails userDetails) throws OpservDataAccessException {
		try{
			TBussUnit tBussUnit = null;
			Short tenantId = userDetails.getTenantId();
			//new entry for BU with mapper
			TBussUnit mappedTBussUnit = businessUnitModelAssembler.mappedBUModelToBussUnitEntity(businessUnit, tenantId);
			tBussUnit = bussUnitDAO.createTBussUnit(mappedTBussUnit);
			return businessUnitModelAssembler.mapBussUnitEntityToBUModel(tBussUnit, tenantId);
		}catch(RuntimeException runtimeException){
			throw new OpservDataAccessException("Error during creating new businessUnit",runtimeException);
		}
	}

	/* (non-Javadoc)
	 * @see com.cognizant.opserv.sp.persistence.dao.service.BusinessUnitSetupDAOService#updateBusinessUnitInfo(com.cognizant.opserv.sp.model.BusinessUnit, com.cognizant.opserv.sp.model.auth.UserDetails)
	 */
	@Override
	public TBussUnit updateBusinessUnitInfo(BusinessUnit businessUnit,
			UserDetails userDetails) throws OpservDataAccessException {
		try{
			TBussUnit tBussUnit = null;
			Short tenantId = userDetails.getTenantId();
			if(null != businessUnit.getId()){
			 tBussUnit = bussUnitDAO.findTBussUnitById(businessUnit.getId());
			}
			//mapped bu model to entity
			tBussUnit = businessUnitModelAssembler.updateMapBUModelToBussUnitEntity(tBussUnit, businessUnit, tenantId);
			tBussUnit.setUpdateDate(DateUtil.getCurrentDate());
			if(userDetails.getUserId() != null){
				tBussUnit.setUpdatedBy(userDetails.getUserId());	
			}
			return bussUnitDAO.updateTBussUnit(tBussUnit);
		}catch(RuntimeException runtimeException){
			throw new OpservDataAccessException("Error during updating BussUnit information",runtimeException);
		}
	}

	/* (non-Javadoc)
	 * @see com.cognizant.opserv.sp.persistence.dao.service.BusinessUnitSetupDAOService#getAllDetailsOfBusinessUnit(java.lang.Long, com.cognizant.opserv.sp.model.auth.UserDetails)
	 */
	@Override
	public BusinessUnit getAllDetailsOfBusinessUnit(Long businessUnitId,
			UserDetails userDetails) throws OpservDataAccessException {
		try{
			Short tenantId = userDetails.getTenantId();
			TBussUnit tBussUnit = bussUnitDAO.findTBussUnitById(businessUnitId);
			BusinessUnit businessUnit = new BusinessUnit();
			if(null != tBussUnit){
				businessUnit = businessUnitModelAssembler.mapBussUnitEntityToBUModel(tBussUnit, tenantId);
			}
			return businessUnit;
		}catch(RuntimeException e){
			throw new OpservDataAccessException("Error occur during fetching BusinessUnit informations", e);
		}
	}

	/* (non-Javadoc)
	 * @see com.cognizant.opserv.sp.persistence.dao.service.BusinessUnitSetupDAOService#softDeletionOfBusinessUnit(java.lang.Long, com.cognizant.opserv.sp.model.auth.UserDetails)
	 */
	@Override
	public TBussUnit softDeletionOfBusinessUnit(Long businessUnitId,
			UserDetails userDetails) throws OpservDataAccessException {
		try{
			TBussUnit tBussUnit = bussUnitDAO.findTBussUnitById(businessUnitId);
			tBussUnit.setUpdateDate(DateUtil.getCurrentDate());
			if(userDetails.getUserId() != null){
				tBussUnit.setUpdatedBy(userDetails.getUserId());	
			}
			tBussUnit.setActiveFlag('N');
			return bussUnitDAO.updateTBussUnit(tBussUnit);
		}catch(RuntimeException runtimeException){
			throw new OpservDataAccessException("Error during updating BusinessUnit as Inactive",runtimeException);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.cognizant.opserv.sp.persistence.dao.service.BusinessUnitSetupDAOService#fetchBusinessUnitsByCriteria(com.cognizant.peg.core.common.ISearchCriteria, com.cognizant.opserv.sp.model.auth.UserDetails)
	 */
	@Override
	public List<BusinessUnit> fetchBusinessUnitsByCriteria(
			ISearchCriteria searchCriteria, UserDetails userDetails)
			throws OpservDataAccessException {
		try{
			List<BusinessUnit> bussUnitList = null;
			BusinessUnit businessUnit = new BusinessUnit();
			Short tenantId = userDetails.getTenantId(); 
			List<TBussUnit> tBusinessUnitList = bussUnitDAO.findTBussUnitBySearchCriteria(searchCriteria);
			if(tBusinessUnitList != null && !tBusinessUnitList.isEmpty()){
				bussUnitList = new ArrayList<BusinessUnit>();
				for(TBussUnit tBussUnit : tBusinessUnitList){
					businessUnit = businessUnitModelAssembler.mapBussUnitEntityToBUModel(tBussUnit, tenantId);
					bussUnitList.add(businessUnit);
				}
			}
			return bussUnitList;
		}
		catch(RuntimeException e){
			throw new OpservDataAccessException("Error during fetching BusinessUnit by search criteria",e);
		}
	}

}
