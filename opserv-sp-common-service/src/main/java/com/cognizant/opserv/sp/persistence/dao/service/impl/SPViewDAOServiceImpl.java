/**
 * 
 */
package com.cognizant.opserv.sp.persistence.dao.service.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cognizant.opserv.query.IQuery;
import com.cognizant.opserv.query.InvalidQueryException;
import com.cognizant.opserv.sp.assembler.SalesPositionViewAssembler;
import com.cognizant.opserv.sp.core.dao.TAlgmntViewStoreDAO;
import com.cognizant.opserv.sp.core.dao.TBussAttrDAO;
import com.cognizant.opserv.sp.core.dao.TBussEntityDAO;
import com.cognizant.opserv.sp.core.entity.TBussAttr;
import com.cognizant.opserv.sp.core.entity.TBussEntity;
import com.cognizant.opserv.sp.model.FieldMetaData;
import com.cognizant.opserv.sp.model.ViewData;
import com.cognizant.opserv.sp.model.ViewHeader;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.persistence.dao.service.SPViewDAOService;
import com.cognizant.peg.core.exception.OpservDataAccessException;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;

/******************************************************************************.
 *
 * @class SPViewDAOServiceImpl contains implementation for SPViewDAOService
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 11/05/2016
 * 
 ******************************************************************************
 */
@Component("spViewDAOService")
public class SPViewDAOServiceImpl implements SPViewDAOService {
	
	@Autowired
	private TBussAttrDAO bussAttrDAO;
	
	@Autowired
	private TBussEntityDAO bussEntityDAO;
	
	@Autowired
	private TAlgmntViewStoreDAO viewStoreDAO;
	
	
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(SPViewDAOServiceImpl.class);
	

	/* (non-Javadoc)
	 * @see com.cognizant.opserv.sp.persistence.dao.service.SPViewDAOService#getCustomerAlignmentViewHeader(com.cognizant.opserv.sp.model.auth.UserDetails)
	 */
	@Override
	public ViewHeader getEntityAlignmentViewHeader(UserDetails userDetails, String bussObjectName, String bussFunctionType) throws OpservDataAccessException {
		
		ViewHeader viewHeader = null;
		
		TBussEntity bussEntity = new TBussEntity();
		short tenantId = 0;
		if(null != userDetails.getTenantId()){
			tenantId = userDetails.getTenantId();
		}
		try {
			List<TBussEntity> bussEntityList = bussEntityDAO.findTBussEntitiesByBussObjType(bussObjectName, tenantId, bussFunctionType);
			if(null != bussEntityList.get(0).getEntityId()) {
				bussEntity.setEntityId(bussEntityList.get(0).getEntityId());
			}
			List<TBussAttr> bussAttrList = bussAttrDAO.getTBussAttrsByTBussEntity(bussEntity);
			if (null != bussAttrList && !bussAttrList.isEmpty()) {
				viewHeader = SalesPositionViewAssembler.convertTBussAttrToModel(bussAttrList);
			}
			return viewHeader;
		} catch (RuntimeException rte) {
			throw new OpservDataAccessException("Exceptiion occurs in getCustomerAlignmentViewHeader of SPViewDAOServiceImpl :: "+rte.getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see com.cognizant.opserv.sp.persistence.dao.service.SPViewDAOService#getCustomerAlignments(com.cognizant.peg.core.common.ISearchCriteria, com.cognizant.opserv.sp.model.auth.UserDetails)
	 */
	@Override
	public ViewData getEntityAlignmentViewData(IQuery queryStructure, UserDetails userDetails, String bussObjectName, String bussFunctionType) throws OpservDataAccessException {
		
		ViewData viewData = null;
		ViewHeader viewHeader = null;
		String entityTableName = null;
		List<Object[]> objArrayList = null;
		
		TBussEntity bussEntity = new TBussEntity();
		short tenantId = 0;
		if(null != userDetails.getTenantId()){
			tenantId = userDetails.getTenantId();
		}
		
		Map<String, String> headerInfoQueryGeneration = new HashMap<String, String>();
		
		List<String> selectParameterList = new ArrayList<String>();
		List<String> displayParameterList = new ArrayList<String>();
		try { 
			List<TBussEntity> bussEntityList = bussEntityDAO.findTBussEntitiesByBussObjType(bussObjectName, tenantId, bussFunctionType);
			if(null != bussEntityList.get(0).getEntityId()) {
				bussEntity.setEntityId(bussEntityList.get(0).getEntityId());
			}
			List<TBussAttr> bussAttrList = bussAttrDAO.getTBussAttrsByTBussEntity(bussEntity);
			if (null != bussAttrList && !bussAttrList.isEmpty()) {
				entityTableName = bussAttrList.get(0).getTBussEntity().getEntityName();
				viewHeader = SalesPositionViewAssembler.convertTBussAttrToModel(bussAttrList);
			}

			if (null != viewHeader && !viewHeader.getFields().isEmpty() && null != viewHeader.getFields()) {
				for (FieldMetaData metaData : viewHeader.getFields()) {
					selectParameterList.add(metaData.getName());
					displayParameterList.add(metaData.getDisplayName());
					headerInfoQueryGeneration.put(metaData.getDisplayName(), metaData.getName());
				}
				
				if(!headerInfoQueryGeneration.isEmpty()) {
					objArrayList = viewStoreDAO.findTCustAlgmntViewStoreByNativeQuery(entityTableName, selectParameterList, headerInfoQueryGeneration, queryStructure);
				}
				
				if(!objArrayList.isEmpty()){
					viewData = SalesPositionViewAssembler.convertObjectArrayListToViewDataList(objArrayList, selectParameterList, displayParameterList, viewHeader);
				}
			}
		} catch (InvalidQueryException e) {
			throw new OpservDataAccessException("InvalidQueryException occurs in getCustomerAlignment of SPViewDAOServiceImpl",e);
		} catch (RuntimeException rte) {
			throw new OpservDataAccessException("Exceptiion occurs in getCustomerAlignment of SPViewDAOServiceImpl :: ",rte);
		} 
		
		
		return viewData;
	}
	
	
	/* (non-Javadoc)
	 * @see com.cognizant.opserv.sp.persistence.dao.service.SPViewDAOService#getResultCountBasedOnSearchCriteria(com.cognizant.peg.core.common.IQuery, com.cognizant.opserv.sp.model.auth.UserDetails, java.lang.String, java.lang.String)
	 */
	@Override
	public int getResultCountBasedOnSearchCriteria(IQuery queryStructure, UserDetails userDetails, String bussObjectName, String bussFunctionType) throws OpservDataAccessException {
		LOGGER.info("Inside getResultCountBasedOnSearchCriteria in SPViewDAOServiceImpl ");
		
		int count = 0;
		TBussEntity bussEntity = new TBussEntity();
		short tenantId = 0;
		String entityTableName = null;
		ViewHeader viewHeader = null;
		Map<String, String> headerInfoQueryGeneration = new HashMap<String, String>();
		
		if(null != userDetails.getTenantId()){
			tenantId = userDetails.getTenantId();
		}
		try { 
			List<TBussEntity> bussEntityList = bussEntityDAO.findTBussEntitiesByBussObjType(bussObjectName, tenantId, bussFunctionType);
			if(null != bussEntityList.get(0).getEntityId()) {
				bussEntity.setEntityId(bussEntityList.get(0).getEntityId());
			}
			List<TBussAttr> bussAttrList = bussAttrDAO.getTBussAttrsByTBussEntity(bussEntity);
			if (null != bussAttrList && !bussAttrList.isEmpty()) {
				entityTableName = bussAttrList.get(0).getTBussEntity().getEntityName();
				viewHeader = SalesPositionViewAssembler.convertTBussAttrToModel(bussAttrList);
			}
			
			if (null != viewHeader && !viewHeader.getFields().isEmpty() && null != viewHeader.getFields()) {
				for (FieldMetaData metaData : viewHeader.getFields()) {
					headerInfoQueryGeneration.put(metaData.getDisplayName(), metaData.getName());
				}
				if(null!= entityTableName) {
					BigInteger longValue;
					longValue = viewStoreDAO.findResultCountBasedOnSearchCriteria(entityTableName, queryStructure, headerInfoQueryGeneration);
					count = longValue.intValue();
				}
			}
		} catch (InvalidQueryException e) {
			throw new OpservDataAccessException("InvalidQueryException occurs in getResultCountBasedOnSearchCriteria of SPViewDAOServiceImpl :: ",e);
		} catch (RuntimeException rte) {
			throw new OpservDataAccessException("Exceptiion occurs in getResultCountBasedOnSearchCriteria of SPViewDAOServiceImpl :: ",rte);
		} 
		
		return count;
	}

}
