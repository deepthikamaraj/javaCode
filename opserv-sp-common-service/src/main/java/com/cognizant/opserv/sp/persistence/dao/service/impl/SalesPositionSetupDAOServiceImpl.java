package com.cognizant.opserv.sp.persistence.dao.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cognizant.opserv.sp.assembler.SalesPositionModelAssembler;
import com.cognizant.opserv.sp.assembler.SalesPositionSetupModelAssembler;
import com.cognizant.opserv.sp.common.util.DateUtil;
import com.cognizant.opserv.sp.core.dao.TAlgmntSalesHierDAO;
import com.cognizant.opserv.sp.core.dao.TSalesPosDAO;
import com.cognizant.opserv.sp.core.entity.TSalesPos;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.persistence.dao.service.SalesPositionSetupDAOService;
import com.cognizant.peg.core.common.ISearchCriteria;
import com.cognizant.peg.core.exception.OpservDataAccessException;

/**
 * The Class SalesPositionSetupDAOServiceImpl.
 */
@Component
public class SalesPositionSetupDAOServiceImpl implements SalesPositionSetupDAOService{

	/** The t sales pos dao. */
	@Autowired
	private TSalesPosDAO tSalesPosDao;
	
	/** The t algmnt sales hier dao. */
	@Autowired
	private TAlgmntSalesHierDAO tAlgmntSalesHierDAO;
	
	/** The sales position setup model assembler. */
	@Autowired
	private SalesPositionSetupModelAssembler salesPositionSetupModelAssembler;
	
	/** The sales position model assembler. */
	@Autowired
	private SalesPositionModelAssembler salesPositionModelAssembler;

	/* (non-Javadoc)
	 * @see com.cognizant.opserv.sp.persistence.dao.service.SalesPositionSetupDAOService#createNewSalesPosition(com.cognizant.opserv.sp.model.SalesPosition, com.cognizant.opserv.sp.model.auth.UserDetails)
	 */
	@Override
	public SalesPosition createNewSalesPosition(SalesPosition salesPosition,
			UserDetails userDetails) throws OpservDataAccessException {
		try{
			TSalesPos tsalesPos = null;
			Short tenantId = userDetails.getTenantId();
			//new entry for SalesPosition with mapper
			TSalesPos mappedTsalesPos = salesPositionSetupModelAssembler.mapSPModelToSalesPosEntity(salesPosition, tenantId);
			tsalesPos = tSalesPosDao.createTSalesPos(mappedTsalesPos);
			return salesPositionSetupModelAssembler.mapSPEntityDetailsToSalesPos(tsalesPos, tenantId);
		}catch(RuntimeException runtimeException){
			throw new OpservDataAccessException("Error during inserting new SalesPosition",runtimeException);
		}
	}
		

	/* (non-Javadoc)
	 * @see com.cognizant.opserv.sp.persistence.dao.service.SalesPositionSetupDAOService#updateSalesPositionInfo(com.cognizant.opserv.sp.model.SalesPosition, com.cognizant.opserv.sp.model.auth.UserDetails)
	 */
	@Override
	public TSalesPos updateSalesPositionInfo(SalesPosition salesPosition,
			UserDetails userDetails) throws OpservDataAccessException {
		try{
			TSalesPos tsalesPos = null;
			Short tenantId = userDetails.getTenantId();
			if(null != salesPosition.getId()){
			 tsalesPos = tSalesPosDao.findTSalesPosBySPId(salesPosition.getId(), tenantId);
			}
			//mapped salesPosition model to entity
			tsalesPos = salesPositionSetupModelAssembler.updateMapSalesPosEntityToSPModel(tsalesPos, salesPosition, tenantId);
			tsalesPos.setUpdateDt(DateUtil.getCurrentDate());
			if(userDetails.getUserId()!=null){
				tsalesPos.setUpdatedBy(userDetails.getUserId());				
			}
			
			return tSalesPosDao.updateTSalesPos(tsalesPos);
		}catch(RuntimeException runtimeException){
			throw new OpservDataAccessException("Error during updating SalesPosition information",runtimeException);
		}
		
	}


	/* (non-Javadoc)
	 * @see com.cognizant.opserv.sp.persistence.dao.service.SalesPositionSetupDAOService#getAllDetailsOfSalesPosition(java.lang.Long, com.cognizant.opserv.sp.model.auth.UserDetails)
	 */
	@Override
	public SalesPosition getAllDetailsOfSalesPosition(Long salePositionId,
			UserDetails userDetails) throws OpservDataAccessException {
		
		try{
			Short tenantId = userDetails.getTenantId();
			TSalesPos salesPos = tSalesPosDao.findTSalesPosBySPId(salePositionId, tenantId);
			SalesPosition salesPosition = new SalesPosition();
			if(null != salesPos){
				salesPosition = salesPositionModelAssembler.mapSPEntityDetailsToSalesPos(salesPos, tenantId);
				List<SalesPosition> childSalesPositions = getChildrensOfSalesPostion(salesPos.getSalesPosId(),salesPosition, tenantId);
				salesPosition.setChildSalesPositions(childSalesPositions);
			}
			
			return salesPosition;
		}catch(RuntimeException e){
			throw new OpservDataAccessException("Error occur during fetching SP informations", e);
		}
		
	}

	/* (non-Javadoc)
	 * @see com.cognizant.opserv.sp.persistence.dao.service.SalesPositionSetupDAOService#fetchSalesPositionsByCriteria(com.cognizant.peg.core.common.ISearchCriteria, com.cognizant.opserv.sp.model.auth.UserDetails)
	 */
	@Override
	public List<SalesPosition> fetchSalesPositionsByCriteria(
			ISearchCriteria searchCriteria, UserDetails userDetails)
			throws OpservDataAccessException {
		try{
			List<SalesPosition> salesPositionList = null;
			SalesPosition salesPosition = new SalesPosition();
			Short tenantId = userDetails.getTenantId(); 
			List<TSalesPos> tSalesPosList = tSalesPosDao.findTSalesPosBySearchCriteria(searchCriteria);
			if(tSalesPosList != null && !tSalesPosList.isEmpty()){
				salesPositionList = new ArrayList<SalesPosition>();
				for(TSalesPos tSalesPos : tSalesPosList){
					salesPosition = salesPositionModelAssembler.mapSPEntityDetailsToSalesPos(tSalesPos, tenantId);
					List<SalesPosition> childSalesPositions = getChildrensOfSalesPostion(tSalesPos.getSalesPosId(),salesPosition, tenantId);
					salesPosition.setChildSalesPositions(childSalesPositions);
					salesPositionList.add(salesPosition);
				}
			}
			return salesPositionList;
		}
		catch(RuntimeException e){
			throw new OpservDataAccessException("Error during fetching SalesPosition by search criteria",e);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.cognizant.opserv.sp.persistence.dao.service.SalesPositionSetupDAOService#softDeletionOfSalesPosition(java.lang.Long, com.cognizant.opserv.sp.model.auth.UserDetails)
	 */
	@Override
	public TSalesPos softDeletionOfSalesPosition(Long salePositionId,
			UserDetails userDetails) throws OpservDataAccessException {
		try{
			Short tenantId = userDetails.getTenantId();
			TSalesPos salesPos = tSalesPosDao.findTSalesPosBySPId(salePositionId, tenantId);
			salesPos.setUpdateDt(DateUtil.getCurrentDate());
			if(userDetails.getUserId()!=null){
				salesPos.setUpdatedBy(userDetails.getUserId());	
			}
			salesPos.setActiveFlag('N');
			return tSalesPosDao.updateTSalesPos(salesPos);
		}catch(RuntimeException runtimeException){
			throw new OpservDataAccessException("Error during updating SalesPosition as Inactive",runtimeException);
		}
	}
	

/**
 * Gets the childrens of sales postion.
 *
 * @param salesPosId the sales pos id
 * @param salesPosition the sales position
 * @param tenantId the tenant id
 * @return the childrens of sales postion
 */
public List<SalesPosition> getChildrensOfSalesPostion(long salesPosId, SalesPosition salesPosition, short tenantId){
		
		List<SalesPosition> childSps=new ArrayList<SalesPosition>();
		List<Long> paramList = new ArrayList<Long>();
		paramList.add(salesPosId);
		List<Object[]> childSPIdList = tSalesPosDao.getChildrenById(paramList,tenantId);
		
		for(Object[] child: childSPIdList){
			TSalesPos childIterativeSalesPos = tSalesPosDao.findTSalesPosBySalesPosId(Long.parseLong(child[0].toString()),tenantId);
			SalesPosition salesPositionDetails = salesPositionModelAssembler.mapSPEntityDetailsToSalesPos(childIterativeSalesPos, tenantId);
			childSps.add(salesPositionDetails);
		}
		return childSps;		
	}

}
