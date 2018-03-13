package com.cognizant.opserv.sp.persistence.dao.service.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.opserv.sp.assembler.SalesPositionModelAssembler;
import com.cognizant.opserv.sp.common.MirrorSalesPositionType;
import com.cognizant.opserv.sp.common.SalesPositionType;
import com.cognizant.opserv.sp.core.dao.TAlgmntSalesHierDAO;
import com.cognizant.opserv.sp.core.dao.TMirSalesPosDAO;
import com.cognizant.opserv.sp.core.dao.TSalesPosDAO;
import com.cognizant.opserv.sp.core.dao.TSalesPosGeoDAO;
import com.cognizant.opserv.sp.core.dao.TSalesPosGeoUnitDAO;
import com.cognizant.opserv.sp.core.dao.TTerrZipAlgmntDAO;
import com.cognizant.opserv.sp.core.entity.TMirSalesPos;
import com.cognizant.opserv.sp.core.entity.TSalesPos;
import com.cognizant.opserv.sp.model.Alignment;
import com.cognizant.opserv.sp.model.GeoShapePolygon;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.persistence.dao.service.SalesPositionDAOService;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.exception.OpservDataAccessException;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
/**
 * ****************************************************************************.
 *
 * @class SalesPositionServiceImpl contains all the services for sales position  
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 * ***************************************************************************
 */
@Component
public class SalesPositionDAOServiceImpl implements SalesPositionDAOService {

	/**
	 * LOGGER
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(SalesPositionDAOServiceImpl.class);
	
	/** The t sales pos dao. */
	@Autowired
	private TSalesPosDAO tSalesPosDao;
	
	/** The t mir sales pos. */
	@Autowired
	private TMirSalesPosDAO tMirSalesPosDao;
	
	/** The t terr zip algmnt dao. */
	@Autowired
	private TTerrZipAlgmntDAO tTerrZipAlgmntDAO;
	
	/** The t algmnt sales hier dao. */
	@Autowired
	private TAlgmntSalesHierDAO tAlgmntSalesHierDAO;
	
	/** The t sales pos geo unit dao. */
	@Autowired
	private TSalesPosGeoUnitDAO tSalesPosGeoUnitDAO;
	
	/** The t sales pos geo dao. */
	@Autowired
	private TSalesPosGeoDAO tSalesPosGeoDAO;
	
	/** The sales position model assembler. */
	@Autowired
	private SalesPositionModelAssembler salesPositionModelAssembler;
	
	/* (non-Javadoc)
	 * @see com.cognizant.opserv.sp.persistence.dao.service.SalesPositionDAOService#getSalesPosInformations(long, short)
	 */
	@Override
	public SalesPosition getSalesPosInformations(long salesPosId, UserDetails userDetail) throws OpservDataAccessException{
		try{
			Short tenantId = userDetail.getTenantId();
			TSalesPos salesPos = tSalesPosDao.findTSalesPosBySPId(salesPosId, tenantId);
			SalesPosition salesPosition = new SalesPosition();
			if(null != salesPos){
				salesPosition = salesPositionModelAssembler.mapSPEntityDetailsToSalesPos(salesPos, tenantId);
				
				List<SalesPosition> childSalesPositions = getChildSalesPostion(salesPos.getSalesPosId(),salesPosition, tenantId);
				salesPosition.setChildSalesPositions(childSalesPositions);
			}
			
			return salesPosition;
		}catch(RuntimeException e){
			LOGGER.error("Error occur during fetching SP informations");
			throw new OpservDataAccessException("Error occur during fetching SP informations", e);
		}
		
	}
	
	/* (non-Javadoc)
	 * @see com.cognizant.opserv.sp.persistence.dao.service.SalesPositionDAOService#fetchAllSalesPostionsByName(java.lang.String, com.cognizant.opserv.sp.model.Alignment, short)
	 */
	@Override
	public List<SalesPosition> fetchAllSalesPostionsByName(String name, Alignment alignmt, UserDetails userDetail) throws OpservDataAccessException{
		try{
			Short tenantId = userDetail.getTenantId();
			List<SalesPosition> salesPositions = new ArrayList<SalesPosition>();
			List<TSalesPos> tSalesPositions = tSalesPosDao.getAllSalesPositionByName(name, tenantId, alignmt.getId(), alignmt.getSalesTeam().getBusinessUnit().getId(),alignmt.getSalesTeam().getId());
			
			if(null !=tSalesPositions &&  !tSalesPositions.isEmpty()){
				for(TSalesPos salesPos : tSalesPositions){
					
					SalesPosition salesPosition= salesPositionModelAssembler.mapSPEntityDetailsToSalesPos(salesPos, tenantId);
					List<SalesPosition> childSalesPositions = getChildSalesPostion(salesPos.getSalesPosId(),salesPosition, tenantId);
					salesPosition.setChildSalesPositions(childSalesPositions);
					
					salesPositions.add(salesPosition);
				}
			}
			
			return salesPositions;
		}catch(RuntimeException e){
			LOGGER.error("Error during fetching SalesPositions by PositionName");
			throw new OpservDataAccessException("Error during fetching SalesPositions by PositionName", e);
		}
		
	}
	
	/* (non-Javadoc)
	 * @see com.cognizant.opserv.sp.persistence.dao.service.SalesPositionDAOService#getImdChildSalesPostion(long, short)
	 */
	@Override
	public List<SalesPosition> getImdChildSalesPostion(long salesPosId, UserDetails userDetail) throws OpservDataAccessException{
	
		try{
			Short tenantId = userDetail.getTenantId();
			List<SalesPosition> childSps=new ArrayList<SalesPosition>();
			//Actual
			List<Long> paramList = new ArrayList<Long>();
			paramList.add(salesPosId);
			List<Object[]> childSPIdList = tSalesPosDao.getChildrenById(paramList,tenantId);
			
			for(Object[] child: childSPIdList){
				TSalesPos childIterativeSalesPos = tSalesPosDao.findTSalesPosBySalesPosId(Long.parseLong(child[0].toString()),tenantId);
				SalesPosition salesPositionDetails = salesPositionModelAssembler.mapSPEntityDetailsToSalesPos(childIterativeSalesPos, tenantId);
				childSps.add(salesPositionDetails);
			}
			return childSps;	
		}catch(RuntimeException e){
			LOGGER.error("Error during fetching immediate child SalesPosition");
			throw new OpservDataAccessException("Error during fetching immediate child SalesPosition", e);
			 
		}
	
	}
	
	/* (non-Javadoc)
	 * @see com.cognizant.opserv.sp.persistence.dao.service.SalesPositionDAOService#fetchtAllSalesPositionsByAlignment(com.cognizant.opserv.sp.model.Alignment, java.lang.Short)
	 */
	@Override
	public List<SalesPosition> fetchtAllSalesPositionsByAlignment(Alignment alignmt, UserDetails userDetails) throws OpservDataAccessException{
		try{
			List<SalesPosition> salesPositions = new ArrayList<SalesPosition>();
			Short tenantId = userDetails.getTenantId();
			long algmntId = alignmt.getId();
			long buId = alignmt.getSalesTeam().getBusinessUnit().getId();
			long stId = alignmt.getSalesTeam().getId();
			List<TSalesPos> tSalesPositions = tSalesPosDao.findAllSPoByALBUST(algmntId, buId, stId, tenantId);
			LOGGER.info("BEFORE getting All SalesPositions By Alignment.");
			if(!tSalesPositions.isEmpty()){
				for(TSalesPos salesPos : tSalesPositions){
					SalesPosition salesPosition= salesPositionModelAssembler.mapSPEntityDetailsToSalesPos(salesPos, tenantId);
					salesPositions.add(salesPosition);
				}
			}
			LOGGER.info("AFTER getting All SalesPositions By Alignment.");
			return salesPositions;
		}catch(RuntimeException e){
			LOGGER.error("Error during fetching SalesPosition by Alignment");
			throw new OpservDataAccessException("Error during fetching SalesPosition by Alignment",e);
		}

	}
	
	
	public List<SalesPosition> getChildSalesPostion(long salesPosId, SalesPosition salesPosition, short tenantId) throws OpservDataAccessException{
		try{
			List<SalesPosition> childSps=new ArrayList<SalesPosition>();
			//Actual
			List<Long> paramList = new ArrayList<Long>();
			paramList.add(salesPosId);
			List<Object[]> childSPIdList = tSalesPosDao.getChildrenById(paramList,tenantId);
			LOGGER.info("BEFORE getting child SalesPosition By SalesPosition.");
			for(Object[] child: childSPIdList){
				TSalesPos childIterativeSalesPos = tSalesPosDao.findTSalesPosBySalesPosId(Long.parseLong(child[0].toString()),tenantId);

				SalesPosition salesPositionDetails = salesPositionModelAssembler.mapSPEntityDetailsToSalesPos(childIterativeSalesPos, tenantId);
				
				childSps.add(salesPositionDetails);
			}
			LOGGER.info("AFTER getting child SalesPosition By SalesPosition.");
			return childSps;		
		}catch(RuntimeException e){
			LOGGER.error("Error during fetching child SalesPosition by SPId");
			throw new OpservDataAccessException("Error during fetching child SalesPosition by SPId",e);
		}
		
		
	}
	
	/* (non-Javadoc)
	 * @see com.cognizant.opserv.sp.persistence.dao.service.SalesPositionDAOService#getMirroredSalesPositionsInfo(com.cognizant.opserv.sp.model.SalesPosition, com.cognizant.opserv.sp.model.auth.UserDetails)
	 */
	@Override
	public List<SalesPosition> getMirroredSalesPositionsInfo(
			SalesPosition salesPosition, UserDetails userDetails)
			throws OpservDataAccessException {
		try{
			List<SalesPosition> salesPositions = new ArrayList<SalesPosition>();
			List<TSalesPos> mirroredSalesPositions = new ArrayList<TSalesPos>();
			long spId = salesPosition.getId();
			
			TSalesPos tSalesPos = tSalesPosDao.findTSalesPosById(spId);
			
			List<SalesPosition> sps = new ArrayList<SalesPosition>();
			
			if(tSalesPos.getSalesPosTypeId() == SalesPositionType.OVERLAY.getId()){
				return null;
			}else{
				LOGGER.info("BEFORE getting mirrored SalesPosition information By SalesPosition.");
				if(null != tSalesPos.getTMirType()){
					if(tSalesPos.getTMirType().getMirTypeId() == MirrorSalesPositionType.ONE_NONE.getId()){
						return null;
					}else if(tSalesPos.getTMirType().getMirTypeId() == MirrorSalesPositionType.ONE_N.getId() || tSalesPos.getTMirType().getMirTypeId() == MirrorSalesPositionType.N_ONE.getId()){
						
						SearchFilter<TSalesPos> searchFilterTSalesPos = new SearchFilter<TSalesPos>();
						OperatorInfo operatorInfo = new OperatorInfo();
						operatorInfo.setLogicalOperator(LogicalOperatorEnum.AND);
						PaginationInfo paginationInfo = new PaginationInfo(0, -1);

						searchFilterTSalesPos.setEntityClass(tSalesPos);
						searchFilterTSalesPos.setOperatorInfo(operatorInfo);
						searchFilterTSalesPos.setPaginationInfo(paginationInfo);
						List<TMirSalesPos> tMirSalesPositions = tMirSalesPosDao.getTMirSalesPossByTSalesPos(searchFilterTSalesPos);
						for(TMirSalesPos tMirSalesPos: tMirSalesPositions){
							SalesPosition sp = new SalesPosition();
							sp.setId(tMirSalesPos.getTSalesPosByTMirSalesPosIbfk2().getSalesPosId());
							if(null!=tMirSalesPos.getPrMirFlag()){
								sp.setPrimaryMirror(tMirSalesPos.getPrMirFlag()=='Y'?true:false);
							}
							sps.add(sp);
						}
						
						for(SalesPosition salesPost : sps){
							mirroredSalesPositions = tSalesPosDao.getMirroredSalesPositions(salesPost.getId(), userDetails.getTenantId());
							if(!mirroredSalesPositions.isEmpty() && mirroredSalesPositions.size()>0){
								for(TSalesPos salesPos : mirroredSalesPositions){
									SalesPosition mirroredSalesPosition= salesPositionModelAssembler.mapSPEntityDetailsToSalesPos(salesPos, userDetails.getTenantId());
									mirroredSalesPosition.setPrimaryMirror(salesPost.isPrimaryMirror()==true?true:false);
									
									salesPositions.add(mirroredSalesPosition);
								}
							}
						}

					}

				}
			}
			LOGGER.info("AFTER getting mirrored SalesPosition information By SalesPosition.");
			return salesPositions;
			
		}catch(RuntimeException e){
			LOGGER.error("Error during getting mirrored salesPostion information");
			throw new OpservDataAccessException("Error during getting mirrored salesPostion information", e);
		}
	}
	
	/**
 	 * Gets the parent sales position.
 	 *
 	 * @param salesPosId the sales pos id
 	 * @param userDetail the user detail
 	 * @return the parent sales position
 	 * @throws OpservDataAccessException the opserv data access exception
 	 */
	@Override
	public SalesPosition getParentSalesPosition(long salesPosId,
			UserDetails userDetail) throws OpservDataAccessException {
		try{
			SalesPosition child = null;
			List<SalesPosition> salesPosList = null;
			SalesPosition salesPosition = getSalesPosInformations(salesPosId, userDetail);
			if(null != salesPosition){
				salesPosList = new ArrayList<SalesPosition>();
				child = salesPosition;
				while(null != child.getParentSalesPosition() && null != child.getParentSalesPosition().getId()){
					SalesPosition parent = getSalesPosInformations(child.getParentSalesPosition().getId(), userDetail);
					salesPosList.add(parent);
					child = parent;
				}
				int j=0;
				for(int i=salesPosList.size()-1;i>=0;i--){
					j=i-1;
					if(i != 0){
						salesPosList.get(j).setParentSalesPosition(salesPosList.get(i));
					}
				}
			}
			if(null != salesPosList && !salesPosList.isEmpty()){
				salesPosition.setParentSalesPosition(salesPosList.get(0));
			}
			return salesPosition;
		}catch(RuntimeException e){
			LOGGER.error("Error occured during fetching SP informations");
			throw new OpservDataAccessException("Error occured during fetching SP informations", e);
		}
	}
	
	/**
	  * Gets the shape polygon by sales position.
	  *
	  * @param salesPositions the sales positions
	  * @param userDetails the user details
	  * @return the shape polygon by sales position
	  * @throws OpservDataAccessException the opserv data access exception
	  */
	@Override
	public List<GeoShapePolygon> getShapePolygonBySalesPosition(List<SalesPosition> salesPositions,
			UserDetails userDetails) throws OpservDataAccessException {
		List<GeoShapePolygon> geoShapePloygons = null;
		try{
			List<Long> spIdList = new ArrayList<Long>();
			long salesHierId = salesPositions.get(0).getHierarchy().getId();
			for(SalesPosition salesPos : salesPositions){
				spIdList.add(salesPos.getId());
			}
			List<Object[]> tSalesPosGeoList = tSalesPosGeoDAO.getTSalesPosGeo(salesHierId, spIdList);
			if(null != tSalesPosGeoList && !tSalesPosGeoList.isEmpty()){
				geoShapePloygons = new ArrayList<GeoShapePolygon>();
				for(Object[] tSalesPosGeo : tSalesPosGeoList){
					GeoShapePolygon geoShapePolygon = new GeoShapePolygon();
					geoShapePolygon.setShapePolygon((String) tSalesPosGeo[1]);
					geoShapePolygon.setId(((BigInteger) tSalesPosGeo[0]).longValue());
					geoShapePloygons.add(geoShapePolygon);
				}
			}
		}
		catch(RuntimeException e){
			LOGGER.error("Exception occur while fetching shape polygon.");
			throw new OpservDataAccessException("exception while fetching shape polygon", e);
		}
		return geoShapePloygons;
	}

	@Override
	public List<String> fetchSpNamesFrSpIds(List<Long> spIdList,
			UserDetails userDetails) throws OpservDataAccessException {
		try{
			List<String> spNamesList = tSalesPosDao.fetchSpNamesFrSpIds(spIdList,userDetails.getTenantId());
			return spNamesList;
		}catch(RuntimeException e){
			LOGGER.error("Exception occur while fetching SP names by SPIds.");
			throw new OpservDataAccessException("Exception occur while fetching SP names by SPIds.", e);
		}
		
	}
	
	/**
	 * Gets the mirror sales position basic data.
	 *
	 * @param salesPosition the sales position
	 * @param userDetails the user details
	 * @return the mirror sales position basic data
	 * @throws OpservDataAccessException the opserv data access exception
	 */
	@Override
	@Transactional
	public List<SalesPosition> getMirrorSalesPositionBasicData(
			SalesPosition salesPosition, UserDetails userDetails)
			throws OpservDataAccessException {
		try {
			List<SalesPosition> salesPositions = null;
			SalesPosition sp = new SalesPosition();
			long spId = salesPosition.getId();

			List<Object[]> tSalesPosObj = tSalesPosDao.findTSalesPositionById(
					spId, userDetails.getTenantId());

			if (null != tSalesPosObj && !tSalesPosObj.isEmpty()) {
				for (Object[] obj : tSalesPosObj) {
					Integer salesTypeId = (Integer) obj[0];
					Integer mirTypeId = (Integer) obj[1];

					  if(salesTypeId.equals(SalesPositionType.BASE.getId())){
						LOGGER.info("BEFORE getting mirrored SalesPosition information By SalesPosition.");
						if (mirTypeId == MirrorSalesPositionType.ONE_N
								.getId()
								|| mirTypeId == MirrorSalesPositionType.N_ONE
										.getId()) {

							salesPositions = new ArrayList<SalesPosition>();
							List<Object[]> tMirSalesPositionList = tMirSalesPosDao
									.getTMirSpBySpId(spId,
											userDetails.getTenantId());
							if (tMirSalesPositionList != null
									&& !tMirSalesPositionList.isEmpty()) {
								for (Object[] tMirSalesPos : tMirSalesPositionList) {

									sp = salesPositionModelAssembler
											.mapObjectToSalesPosition(
													tMirSalesPos,
													userDetails.getTenantId());
									salesPositions.add(sp);

								}
							}

						}
					}
				}
			}
			LOGGER.info("AFTER getting mirrored SalesPosition information By SalesPosition.");
			return salesPositions;

		} catch (RuntimeException e) {
			LOGGER.error("Error during getting mirrored salesPostion information");
			throw new OpservDataAccessException(
					"Error during getting mirrored salesPostion information", e);
		}
	}

	/**
 	 * Checks if is sales team mirrored.
 	 *
 	 * @param algmntId the algmnt id
 	 * @param bussUnitId the buss unit id
 	 * @param salesTeamId the sales team id
 	 * @param tenantId the tenant id
 	 * @return true, if is sales team mirrored
 	 */
	@Override
	public boolean isSalesTeamMirrored(Long algmntId, Long bussUnitId,
			Long salesTeamId, Short tenantId)throws OpservDataAccessException {
		
		try{
			LOGGER.info("****** Check whether the SalesTeam is Mirrored started*****");
			Long countOfMirr = tSalesPosDao.countOfBaseSPFrST(algmntId, bussUnitId,
					salesTeamId, tenantId);
			if (countOfMirr > 0) {
				return true;
			}
			LOGGER.info("****** Check whether the SalesTeam is Mirrored ended*****");
		}catch(RuntimeException re){
			LOGGER.error("Error during while checking SalesTeam id Mirrored or not ");
			throw new OpservDataAccessException(
					"Error during while checking SalesTeam id Mirrored or not", re);
		}
		return false;
	}

	/**
 	 * Checks if is sales position is active
 	 * @param salesPosId the sales position id
 	 * @param tenantId  the tenant id
 	 * @return true, if is sales position is active
 	 */
	@Override
	public boolean isSalesPosActive(Long salesPosId, Short tenantId) throws OpservDataAccessException{
		try{
			List<Object> count = tSalesPosDao.checkIfActiveSP(salesPosId, tenantId);
			Long spCnt =(Long) count.get(0);
			if(spCnt > 0 ){
				return true;
			}
			return false;
		} catch (RuntimeException e) {
			LOGGER.error("Error while checking if Sales Pos is Active");
			throw new OpservDataAccessException(
					"Error while checking if Sales Pos is Active", e);
		}
	}

	@Override
	public List<Object[]> findSPNameAndCodeForEmp(Integer staffId,
			Short tenantId)  throws OpservDataAccessException {
		try {
			return tSalesPosDao.findSPNameAndCodeForEmp(staffId, tenantId);
		} catch (RuntimeException e) {
			LOGGER.error("Error while getting sp name and code for person id "+staffId);
			throw new OpservDataAccessException(
					"Error while getting sp name and code for person id", e);
		}
	}
	
}
