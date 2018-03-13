package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.common.util.DateUtil;
import com.cognizant.opserv.sp.core.entity.TSalesPos;
import com.cognizant.opserv.sp.core.entity.TSalesPosGeoUnit;
import com.cognizant.opserv.sp.core.entity.TTerrZipAlgmnt;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TTerrZipAlgmntDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tTerrZipAlgmntDAO")
public class TTerrZipAlgmntDAOImpl implements TTerrZipAlgmntDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TTerrZipAlgmntDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private static final String TSALESPOSGEOUNIT = "tSalesPosGeoUnit";

	private final Class<TTerrZipAlgmnt> clazz;

	public Class<TTerrZipAlgmnt> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TTerrZipAlgmntDAOImpl() {
		super();
		this.clazz = TTerrZipAlgmnt.class;
	}

	private static final String JPAQL = "select tTerrZipAlgmntentity from TTerrZipAlgmnt tTerrZipAlgmntentity";

	private static final String JPACOUNTQL = "select count(tTerrZipAlgmntentity) from TTerrZipAlgmnt tTerrZipAlgmntentity";

	private static final String[] RESTRICTIONS = {
			"tTerrZipAlgmntentity.geoZipId = #{tTerrZipAlgmntentity.getGeoZipId}",
			"tTerrZipAlgmntentity.assignedFlag = #{tTerrZipAlgmntentity.getAssignedFlag}",
			"tTerrZipAlgmntentity.pointZipFlag = #{tTerrZipAlgmntentity.getPointZipFlag}",
			"tTerrZipAlgmntentity.countryId = #{tTerrZipAlgmntentity.getCountryId}",
			"tTerrZipAlgmntentity.activeFlag = #{tTerrZipAlgmntentity.getActiveFlag}",
			"tTerrZipAlgmntentity.postalCd like '#{tTerrZipAlgmntentity.getPostalCd}%'",
			"tTerrZipAlgmntentity.createdBy = #{tTerrZipAlgmntentity.getCreatedBy}",
			"Date(tTerrZipAlgmntentity.createDt) = '#{tTerrZipAlgmntentity.getCreateDt}'",
			"tTerrZipAlgmntentity.updatedBy = #{tTerrZipAlgmntentity.getUpdatedBy}",
			"Date(tTerrZipAlgmntentity.updateDt) = '#{tTerrZipAlgmntentity.getUpdateDt}'",
			"tTerrZipAlgmntentity.tenantId = #{tTerrZipAlgmntentity.getTenantId}",
			"Date(tTerrZipAlgmntentity.effStartDt) = '#{tTerrZipAlgmntentity.getEffStartDt}'",
			"Date(tTerrZipAlgmntentity.effEndDt) = '#{tTerrZipAlgmntentity.getEffEndDt}'" ,
			"tTerrZipAlgmntentity.statusId = #{tTerrZipAlgmntentity.getStatusId}",
			"tTerrZipAlgmntentity.tSalesPosGeoUnit.tSalesPosGeoUnitId = #{tTerrZipAlgmntentity.tSalesPosGeoUnit.getTSalesPosGeoUnitId}" };

	/**
	 * Stores a new TTerrZipAlgmnt entity object in to the persistent store
	 * 
	 * @param tTerrZipAlgmnt
	 *            TTerrZipAlgmnt Entity object to be persisted
	 * @return tTerrZipAlgmnt Persisted TTerrZipAlgmnt object
	 */
	public TTerrZipAlgmnt createTTerrZipAlgmnt(final TTerrZipAlgmnt tTerrZipAlgmnt) {
		LOGGER.info("=========== Create TTerrZipAlgmnt ===========");
		return genericDAO.store(tTerrZipAlgmnt);
	}

	/**
	 * Deletes a TTerrZipAlgmnt entity object from the persistent store
	 * 
	 * @param tTerrZipAlgmnt
	 *            TTerrZipAlgmnt Entity object to be deleted
	 */
	public void deleteTTerrZipAlgmnt(final Integer geoZipId) {
		LOGGER.info("=========== Delete TTerrZipAlgmnt ===========");
		final TTerrZipAlgmnt tTerrZipAlgmnt = genericDAO.get(clazz, geoZipId);
		genericDAO.remove(tTerrZipAlgmnt);
	}

	/**
	 * Updates a TTerrZipAlgmnt entity object in to the persistent store
	 * 
	 * @param tTerrZipAlgmnt
	 *            TTerrZipAlgmnt Entity object to be updated
	 * @return tTerrZipAlgmnt Persisted TTerrZipAlgmnt object
	 */
	public TTerrZipAlgmnt updateTTerrZipAlgmnt(final TTerrZipAlgmnt tTerrZipAlgmnt) {
		LOGGER.info("=========== Update TTerrZipAlgmnt ===========");
		return genericDAO.update(tTerrZipAlgmnt);
	}

	/**
	 * Retrieve an TTerrZipAlgmnt object based on given TTerrZipAlgmnt geoZipId.
	 * 
	 * @param tTerrZipAlgmntId
	 *            the primary key value of the TTerrZipAlgmnt Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TTerrZipAlgmnt findTTerrZipAlgmntById(final Integer tTerrZipAlgmntId) {
		LOGGER.info("find TTerrZipAlgmnt instance with geoZipId: " + tTerrZipAlgmntId);
		return genericDAO.get(clazz, tTerrZipAlgmntId);
	}

	/**
	 * Retrieve TTerrZipAlgmnt based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TTerrZipAlgmnt> list of TTerrZipAlgmnt if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TTerrZipAlgmnt> findTTerrZipAlgmnts(final SearchFilter<TTerrZipAlgmnt> searchFilter) {
		LOGGER.info("=========== Find TTerrZipAlgmnts ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TTerrZipAlgmnt tTerrZipAlgmnt = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tTerrZipAlgmntentity", tTerrZipAlgmnt);

		if (tTerrZipAlgmnt.getTSalesPosGeoUnit() == null) {
			jpaQuery.bind(TSALESPOSGEOUNIT, new TSalesPosGeoUnit());
		} else {
			LOGGER.info("tSalesPosGeoUnit {}"+ tTerrZipAlgmnt.getTSalesPosGeoUnit());

			jpaQuery.bind(TSALESPOSGEOUNIT, tTerrZipAlgmnt.getTSalesPosGeoUnit());
		}
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TTerrZipAlgmnts.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTTerrZipAlgmnts(final SearchFilter<TTerrZipAlgmnt> searchFilter) {
		LOGGER.info("=========== Count TTerrZipAlgmnt ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TTerrZipAlgmnt tTerrZipAlgmnt = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tTerrZipAlgmntentity", tTerrZipAlgmnt);

		if (tTerrZipAlgmnt.getTSalesPosGeoUnit() == null) {
			jpaCountQuery.bind(TSALESPOSGEOUNIT, new TSalesPosGeoUnit());
		} else {
			LOGGER.info("tSalesPosGeoUnit {}"+ tTerrZipAlgmnt.getTSalesPosGeoUnit());

			jpaCountQuery.bind(TSALESPOSGEOUNIT, tTerrZipAlgmnt.getTSalesPosGeoUnit());
		}

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TTerrZipAlgmnt based on given search criteria using JPA named Query.
	 * The search criteria is of TSalesPosGeoUnit type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TTerrZipAlgmnt> list of TTerrZipAlgmnts if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TTerrZipAlgmnt> getTTerrZipAlgmntsByTSalesPosGeoUnit(final SearchFilter<TSalesPosGeoUnit> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTTerrZipAlgmntByTSalesPosGeoUnit", queryParam, index,
				maxresult);
	}

	/**
	 * Count TTerrZipAlgmnt based on given search criteria using JPA named Query.
	 * The search criteria is of TSalesPosGeoUnit type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTTerrZipAlgmntsByTSalesPosGeoUnit(final SearchFilter<TSalesPosGeoUnit> searchFilter) {

		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());
		return genericDAO.findEntitiesByNamedQuery("CountTTerrZipAlgmntsByTSalesPosGeoUnit", queryParam);
	}
	
	
	@Override
	public List<TTerrZipAlgmnt> getTTsalesPOS(String posCode,Short tenantId) {
		List queryParam = new ArrayList();
		queryParam.add(posCode);
		queryParam.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("getTTsalesPOS", queryParam, 0, -1);
		
	}
	@Override
	public List<TTerrZipAlgmnt> findTTerrZipAlgmntByIds(Long salesPosId,Long salesHierId,Short tenantId) {
		List queryParam = new ArrayList();
		queryParam.add(salesPosId);
		queryParam.add(salesHierId);
		queryParam.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTTerrZipAlgmntByIds", queryParam, 0, -1);
		
	}

	@Override
	public List<TTerrZipAlgmnt> getTTerrZipAlgmntsByTSalesPos(
			SearchFilter<TSalesPos> searchFilter) {
		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTTerrZipAlgmntByTSalesPos", queryParam, index, maxresult);
	}
	@Override
	public List<TTerrZipAlgmnt> findTTerrZipAlgmntByTenantId(
			Long salesPosId,Long salesHierId, Short tenantId) {
		List queryParam = new ArrayList();
		queryParam.add(salesPosId);
		queryParam.add(salesHierId);
		queryParam.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTTerrZipAlgmntByTenantId", queryParam, 0, -1);
		
	}
	public List<TTerrZipAlgmnt> FindTTerrZipAlgmntByPostalCd(final SearchFilter<TTerrZipAlgmnt> searchFilter) {
		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TTerrZipAlgmnt tTerrZipAlgmnt = searchFilter.getEntityClass();
		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(tTerrZipAlgmnt.getPostalCd());
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex(); 
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTTerrZipAlgmntByPostalCd", queryParam, index, maxresult);
		//List<TTerrZipAlgmnt> tTerrZipAlgmntList = genericDAO.findEntitiesByNamedQueryMultiCond("FindTTerrZipAlgmntByPostalCd", queryParam, index, maxresult);
		//return tTerrZipAlgmntList;
	}
	public List<String> FindTTZAForAssignedFlagByPostalCd(final SearchFilter<TTerrZipAlgmnt> searchFilter) {
		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TTerrZipAlgmnt tTerrZipAlgmnt = searchFilter.getEntityClass();
		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(tTerrZipAlgmnt.getPostalCd());
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTTZAForAssignedFlagByPostalCd", queryParam, index, maxresult);
	}
	public List<TTerrZipAlgmnt> FindTTerrZipAlgmntBySalesHier(final SearchFilter<TTerrZipAlgmnt> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTTerrZipAlgmntBySalesHier", queryParam, index, maxresult);
	}

	@Override
	public Long countTTerrZipAlgmntsCount(Long salesPosId, Long salesHierId,
			Short tenantId) {
		List paramList = new ArrayList();
		
		paramList.add(salesPosId);
		paramList.add(salesHierId);
		paramList.add(tenantId);
		paramList.add(DateUtil.getCurrentDate());
		paramList.add('Y');
		List<Object> count = genericDAO.findEntitiesByNamedQueryMultiCond("FindCountOfTTerZipAlgmntForSP", paramList, 0, -1);
		Long countL = (Long) count.get(0) ;
		return countL;
		
	}

	@Override
	public List<TTerrZipAlgmnt> getTTerrZipAlgmnt(Long saleHierId,
			Long salePosId, String postalCd,Short tenantId) {
		List queryParam = new ArrayList();
		queryParam.add(saleHierId);
		queryParam.add(salePosId);
		queryParam.add(postalCd);
		queryParam.add(tenantId);
		
		return genericDAO.findEntitiesByNamedQueryMultiCond("getTTerrZipAlgmnt", queryParam, 0, -1);
	}

	@Override
	public List<TTerrZipAlgmnt> getTTerrZipAlgmntForComb(Long saleHierId,
			Long salePosId, String postalCd,Short tenantId) {
		List queryParam = new ArrayList();
		queryParam.add(saleHierId);
		queryParam.add(salePosId);
		queryParam.add(postalCd);
		queryParam.add(tenantId);
		
		return genericDAO.findEntitiesByNamedQueryMultiCond("getTTerrZipAlgmntForComb", queryParam, 0, -1);
	}

	@Override
	public Long countTTerrZipAlgmntsCountForChild(Set<Long> childSp,
			Short tenantId) {
List paramList = new ArrayList();
		
		paramList.add(childSp);
		paramList.add(tenantId);
		List<Object> count = genericDAO.findEntitiesByNamedQueryMultiCond("FindCountOfTTerZipAlgmntForSPAndChild", paramList, 0, -1);
		Long countL = (Long) count.get(0) ;
		return countL;
	}

	@Override
	public List<TTerrZipAlgmnt> getSalesPosByPostalCode(Long algmntId,
			Long bussUnitId, Long salesTeamId, List<String> zipCodes, Short tenantId) {
		List queryParam = new ArrayList();
		queryParam.add(algmntId);
		queryParam.add(bussUnitId);
		queryParam.add(salesTeamId);
		queryParam.add(zipCodes);
		queryParam.add(tenantId);
		
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindSalesPosByPostalCode", queryParam, 0, -1);
	}

	@Override
	public List<TTerrZipAlgmnt> getTTerrZipAlgmntbyGeoID(Long salesHierId,
			Long salesPosId, String postalCd, Integer geoids, Short tenantId) {
		List queryParam = new ArrayList();
		queryParam.add(salesHierId);
		queryParam.add(salesPosId);
		queryParam.add(postalCd);
		queryParam.add(geoids);
		queryParam.add(tenantId);
		
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTTerrZipAlgmntbyGeoID", queryParam, 0, -1);
	}

	@Override
	public List<TTerrZipAlgmnt> getSalesPosId(List<String> postalCds,
			Short tenantId) {
		List queryParam = new ArrayList();
		queryParam.add(postalCds);
		queryParam.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("getSalesPosId", queryParam, 0, -1);
	}

	@Override
	public List<TTerrZipAlgmnt> findzipCodeByparentID(Long alignId,
			Long bussID, Long salesteamID, List<Long> salesPosIDs,
			short tenantID) {
		List queryParam = new ArrayList();
		queryParam.add(alignId);
		queryParam.add(bussID);
		queryParam.add(salesteamID);
		queryParam.add(salesPosIDs);
		queryParam.add(tenantID);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindzipCodesByparentID", queryParam, 0, -1);
	}

	@Override
	public List<TTerrZipAlgmnt> findTTerrZipAlgmntBySpShIdList(
			List<Long> spIdList, List<Long> shIdList, Short tenantId) {
		List queryParam = new ArrayList();
		queryParam.add(spIdList);
		queryParam.add(shIdList);
		queryParam.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("findTTerrZipAlgmntBySpShIdList", queryParam, 0, -1);
	}

	@Override
	public List<TTerrZipAlgmnt> getTTsalesPOSForHeatMap(List<String> posCode,Short tenantId) {
		List queryParam = new ArrayList();
		queryParam.add(posCode);
		queryParam.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("getTTsalesPOSForHeatMap", queryParam, 0, -1);
		
	}
	
	@Override
	public List<TTerrZipAlgmnt> getTTsalesPOSFrDataDots(List<String> zipCodeList,Short tenantId) {
		List queryParam = new ArrayList();
		queryParam.add(zipCodeList);
		queryParam.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("getTTsalesPOSFrDataDots", queryParam, 0, -1);
		
	}
	
	@Override
	public List<String> getPostalCodesForALBUSTSPS(Long alId,Long buId, Long stId, List<Long> salesPosIDs,short tenantID){
		List<Object> queryParam = new ArrayList<Object>();
		queryParam.add(alId);
		queryParam.add(buId);
		queryParam.add(stId);
		queryParam.add(salesPosIDs);
		queryParam.add(tenantID);
		return genericDAO.findEntitiesByNamedQueryMultiCond("GetPostalCodesForALBUSTSPS", queryParam, 0, -1);
	}

	@Override
	public List<TTerrZipAlgmnt> getActiveTTerrZipAlgmnt(Long salesHierId,
			Long salesPosId, String zip, Integer geoids, Short tenantId) {
		List queryParam = new ArrayList();
		queryParam.add(salesHierId);
		queryParam.add(salesPosId);
		queryParam.add(zip);
		queryParam.add(geoids);
		queryParam.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("getActiveTTerrZipAlgmnt", queryParam, 0, -1);
	}

	@Override
	public List<TTerrZipAlgmnt> getActiveTTerrZipAlgmntByZipCode(String zip,
			Short tenantId) {
		List queryParam = new ArrayList();
		queryParam.add(zip);
		queryParam.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("getActiveTTerrZipAlgmntByZipCode", queryParam, 0, -1);
	}
	
	@Override
	public Long findTTerrZipAlgmntsByTSalesPosCount(TSalesPos tSalesPos, Short tenantId) {
		List queryParam = new ArrayList();
		queryParam.add(tSalesPos.getTAlgmntSalesHier().getSalesHierId());
		queryParam.add(tSalesPos.getSalesPosId());
		queryParam.add(tenantId);
		List<Object> count = genericDAO.findEntitiesByNamedQueryMultiCond("getTTerrZipAlgmntsByTSalesPosCount", queryParam, 0, -1);
		Long countL = (Long) count.get(0);
		return countL;
	}

public List<Object[]> getActiveZipCodeFrSP(List<Long> salesPosId, List<Long> salesHierId, Short tenantId, Integer startInd, Integer numOfRows, String srchFld, String srchSPFld){
		final Integer index = startInd;
		final Integer maxresult = numOfRows;
		
		List queryParam = new ArrayList();
		queryParam.add(salesPosId);
		queryParam.add(salesHierId);
		queryParam.add(tenantId);
		List<Object[]> zipCodesList = null;
		if(srchFld != null && !srchFld.equals("") && (srchSPFld==null || srchSPFld.length()==0)){
			queryParam.add("%"+srchFld+"%");
			zipCodesList = genericDAO.findEntitiesByNamedQueryMultiCond("getActiveZipCodeFrSPOnSrch", queryParam, index, maxresult);
		}else if(srchSPFld != null && !srchSPFld.equals("") && (srchFld==null || srchFld.length()==0)){
			queryParam.add("%"+srchSPFld+"%");
			zipCodesList = genericDAO.findEntitiesByNamedQueryMultiCond("getActiveBrickCodesForSPOnSrch", queryParam, index, maxresult);
		}else if(srchFld != null && !srchFld.equals("") && srchSPFld != null && !srchSPFld.equals("")){
			queryParam.add("%"+srchSPFld+"%");
			queryParam.add("%"+srchFld+"%");
			zipCodesList = genericDAO.findEntitiesByNamedQueryMultiCond("getActiveBrickCodesForBoth", queryParam, index, maxresult);
		} else {
			zipCodesList = genericDAO.findEntitiesByNamedQueryMultiCond("getActiveZipCodeFrSP", queryParam, index, maxresult);
		}
		
		
		return zipCodesList;
	}
	
	@Override
	public List<String> getAllAssignedZipCdFrSp(Long salesPosId, Long salesHierId, Short tenantId) {

	List queryParam = new ArrayList();
	queryParam.add(salesPosId);
	queryParam.add(tenantId);
	queryParam.add(salesHierId);
	List<String> assignedZipCodes = genericDAO.findEntitiesByNamedQueryMultiCond("FindAllPostalCodeAssignedFrSp", queryParam, 0, -1);
			return assignedZipCodes;
	}
	
	@Override
	public List<TTerrZipAlgmnt> getActiveTTerrZipAlgmntGeoId(Long saleHierId,
			Long salePosId, String postalCd,Short tenantId) {
		List queryParam = new ArrayList();
		queryParam.add(saleHierId);
		queryParam.add(salePosId);
		queryParam.add(postalCd);
		queryParam.add(tenantId);
		
		return genericDAO.findEntitiesByNamedQueryMultiCond("getActiveTTerrZipAlgmntGeoId", queryParam, 0, -1);
	}
	
	public List<TTerrZipAlgmnt> getTTerrZipAlgmntGeoId(Long saleHierId, Long salePosId,
			String postalCd, Short tenantId, Integer geoId){
		List queryParam = new ArrayList();
		queryParam.add(saleHierId);
		queryParam.add(salePosId);
		queryParam.add(postalCd);
		queryParam.add(tenantId);
		queryParam.add(geoId);
		
		return genericDAO.findEntitiesByNamedQueryMultiCond("getTTerrZipAlgmntGeoId", queryParam, 0, -1);
	}

	@Override
	public Long getActiveZipCodeFrSPCount(List<Long> salesPosId,
			List<Long> salesHierId, Short tenantId, Integer startInd,
			Integer numOfRows, String srchFld,String srchSPFld) {
		final Integer index = startInd;
		final Integer maxresult = numOfRows;
		
		List queryParam = new ArrayList();
		queryParam.add(salesPosId);
		queryParam.add(salesHierId);
		queryParam.add(tenantId);
		List zipCodesCount =null;
		
		if(srchFld != null && !srchFld.equals("") && (srchSPFld==null||srchSPFld.length()==0)){
			queryParam.add("%"+srchFld+"%");
			zipCodesCount = genericDAO.findEntitiesByNamedQueryMultiCond("getActiveZipCodeFrSPCountOnSrch", queryParam, index, maxresult);
		} else if(srchSPFld != null && !srchSPFld.equals("") && (srchFld==null|| srchFld.length()==0)){
			queryParam.add("%"+srchSPFld+"%");
			zipCodesCount = genericDAO.findEntitiesByNamedQueryMultiCond("getActiveZipCodeCountForSPOnSrch", queryParam, index, maxresult);
		} else if(srchSPFld != null && !srchSPFld.equals("") && srchFld != null && !srchFld.equals("")){
			queryParam.add("%"+srchSPFld+"%");
			queryParam.add("%"+srchFld+"%");
			zipCodesCount = genericDAO.findEntitiesByNamedQueryMultiCond("getActiveZipCodeCountForBoth", queryParam, index, maxresult);
		} else {
			zipCodesCount = genericDAO.findEntitiesByNamedQueryMultiCond("getActiveZipCodeFrSPCount", queryParam, index, maxresult);
		}
			
		Long activeZipCodesCount = (Long) zipCodesCount.get(0);
		return activeZipCodesCount;
	}

	@Override
	public List<String> getCheckFrBricksInTargetSP(List<Long> shId, List<Long> spId,
			List<String> postalCdList, Short tenantId){
		List queryParam = new ArrayList();
		queryParam.add(shId);
		queryParam.add(spId);
		queryParam.add(postalCdList); 
		queryParam.add(tenantId); 
		List<String> brickList = genericDAO.findEntitiesByNamedQueryMultiCond("getCheckFrBricksInTargetSP", queryParam, 0, -1);
		return brickList;
	}
	@Override
	public Long fetchTheZipCountforSPFrAssociatedBricks(Long salesPosId,
			Long salesHierId, Short tenantId) {
		
		List queryParam = new ArrayList();
		queryParam.add(salesPosId);
		queryParam.add(salesHierId);
		queryParam.add(tenantId);
		
		List associateBrick = genericDAO.findEntitiesByNamedQueryMultiCond("FindCountOfTTerZipAlgmntForSPAssociatedBricks", queryParam, 0, -1);
		Long associateBrickCount = (Long) associateBrick.get(0);
		return associateBrickCount;
	}

	@Override
	public List<Object[]> fetchShareTerrSPFrBrick(String brickCode,
			Short tenantId) {
		List queryParam = new ArrayList();
		queryParam.add(brickCode);
		queryParam.add(tenantId);
		
		List<Object[]> shareTerrSPFrBrick = genericDAO.findEntitiesByNamedQueryMultiCond("fetchShareTerrSPFrBrick", queryParam, 0, -1);
		return shareTerrSPFrBrick;
	}

	@Override
	public List<Object[]> fetchBrickDetails(List<String> brickCode,
			Short tenantId) {
		// TODO Auto-generated method stub
		List queryParam = new ArrayList();
		queryParam.add(brickCode);
		queryParam.add(tenantId);
		
		List<Object[]> brickObject = genericDAO.findEntitiesByNamedQueryMultiCond("getActiveBrickPostalCd", queryParam, 0, -1);
		return brickObject;
	}
	
	@Override
	public Long findCountOfTTerZipAlgmntForAlBuSt1(Long algmntId, Long bussUnitId, Long salesTeamId, Short tenantId){
		
		List queryParam = new ArrayList();
		queryParam.add(algmntId);
		queryParam.add(bussUnitId);
		queryParam.add(salesTeamId);		
		queryParam.add(tenantId);
		
		List brick = genericDAO.findEntitiesByNamedQueryMultiCond("FindCountOfTTerZipAlgmntForAlBuSt1", queryParam, 0, -1);
		Long associateBrickCount = (Long) brick.get(0);
		return associateBrickCount;
	}
	
	@Override
	public Long findCountOfTTerZipAlgmntForAlBuSt(List<Long> salesPos, Short tenantId){
		
		List queryParam = new ArrayList();
		
		queryParam.add(salesPos);		
		queryParam.add(tenantId);
		
		List brick = genericDAO.findEntitiesByNamedQueryMultiCond("FindCountOfTTerZipAlgmntForAlBuSt", queryParam, 0, -1);
		Long associateBrickCount = (Long) brick.get(0);
		return associateBrickCount;
	}
	
	@Override
	public List<TTerrZipAlgmnt> getActiveTTerrZipAlgmntByAlBUSTZip(Long alId, Long buId, Long stId, String postalCd, Short tenantId){	
		
		List queryParam = new ArrayList();
		
		queryParam.add(alId);	
		queryParam.add(buId);
		queryParam.add(stId);
		queryParam.add(postalCd);
		queryParam.add(tenantId);
		
	List<TTerrZipAlgmnt> tTerrZipAlgmntList = genericDAO.findEntitiesByNamedQueryMultiCond("getActiveTTerrZipAlgmntByAlBUSTZip", queryParam, 0, -1);
	return tTerrZipAlgmntList;
	}

	public List<TTerrZipAlgmnt> getAllPostalCodes(TTerrZipAlgmnt terrZipAlgmnt) {
		List queryParam = new ArrayList();
		queryParam.add(terrZipAlgmnt.getTSalesPosGeoUnit().getAlgmntId());
		queryParam.add(terrZipAlgmnt.getTSalesPosGeoUnit().getBussUnitId());
		queryParam.add(terrZipAlgmnt.getTSalesPosGeoUnit().getSalesTeamId());
		queryParam.add(terrZipAlgmnt.getTenantId());
		queryParam.add("%"+terrZipAlgmnt.getPostalCd()+"%");
			
		return genericDAO.findEntitiesByNamedQueryMultiCond("getAllPostalCodes", queryParam, 0, -1);
	}

	/* (non-Javadoc)
	 * @see com.cognizant.opserv.sp.core.dao.TTerrZipAlgmntDAO#getAllGeographyAlignments(com.cognizant.opserv.sp.core.entity.TTerrZipAlgmnt)
	 */
	public List<TTerrZipAlgmnt> getAllGeographyAlignments(TTerrZipAlgmnt tTerrZipAlgmnt){
		
		List queryParam = new ArrayList();
		List<TTerrZipAlgmnt> terrZipAlgmntsList = null;
		queryParam.add(tTerrZipAlgmnt.getTSalesPos().getTAlgmntSalesHier().getSalesHierId());
		queryParam.add(tTerrZipAlgmnt.getTSalesPos().getSalesPosId());
		queryParam.add(tTerrZipAlgmnt.getTenantId());
		
		terrZipAlgmntsList = genericDAO.findEntitiesByNamedQueryMultiCond("getAllGeographyAlignments", queryParam, 0, -1);
		
		return terrZipAlgmntsList;
	}

	
	/** (non-Javadoc)
	 * @see com.cognizant.opserv.sp.core.dao.TTerrZipAlgmntDAO#fetchTTerrZipAlgmntByChangeRequest(java.lang.Integer, java.lang.String, java.lang.Long, java.lang.Long, java.lang.Short)
	 */
	@Override
	public List<TTerrZipAlgmnt> fetchTTerrZipAlgmntByChangeRequest(Integer geoId, String postalCd, Long salePosId, Long saleHierId, Short tenantId) {
		List<Object> params = new ArrayList<Object>();
		params.add(geoId);
		params.add(postalCd);
		params.add(salePosId);
		params.add(saleHierId);
		params.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("fetchTTerrZipAlgmntByChangeRequest", params, 0, -1);
	}

	
	@Override
	public List<TTerrZipAlgmnt> getActiveTTerrZipAlgmntsBySPId(List<Long> salePosId, String postalCd,Short tenantId) {
		List queryParam = new ArrayList();
		queryParam.add(salePosId);
		queryParam.add(postalCd);
		queryParam.add(tenantId);
		
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTTerZipAlgmntForSP", queryParam, 0, -1);
	}

	@Override
	public List<TTerrZipAlgmnt> getTTerrZipAlgmntsByPostalCd(Long salesHierId,
			Long salesPosId, String postalCd,Short tenantId) {
		List queryParam = new ArrayList();
		queryParam.add(salesHierId);
		queryParam.add(salesPosId);
		queryParam.add(postalCd);
		queryParam.add(tenantId);
		
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTTerrZipAlgmntsByPostalCd", queryParam, 0, -1);
	}

	@Override
	public List<TTerrZipAlgmnt> getActiveTTerrZipAlgmntList(Long saleHierId,Long salePosId,
			String postalCd, Short tenantId,Integer geoId) {
		List queryParam = new ArrayList();

		queryParam.add(saleHierId);
		queryParam.add(salePosId);
		queryParam.add(postalCd);
		queryParam.add(tenantId);
		queryParam.add(geoId);
		
		return genericDAO.findEntitiesByNamedQueryMultiCond("getActiveTTerrZipAlgmntByGeoId", queryParam, 0, -1);
	}

	@Override
	public Long getZipCountForSpList(List<Long> spIdList, Short tenantId) {
		List paramList = new ArrayList();
		paramList.add(spIdList);
		paramList.add(DateUtil.getCurrentDate());
		paramList.add(tenantId);
		List<Object> count = genericDAO.findEntitiesByNamedQueryMultiCond("getZipCountForSpList", paramList, 0, -1);
		Long countL = (Long) count.get(0);
		return countL;
	}
	
	@Override
	public List<TTerrZipAlgmnt> getActiveTTerrZipAlgmntList(Long saleHierId,Long salePosId,
			String postalCd, Short tenantId) {
		List queryParam = new ArrayList();

		queryParam.add(saleHierId);
		queryParam.add(salePosId);
		queryParam.add(postalCd);
		queryParam.add(tenantId);
		//queryParam.add(geoId);
		
		return genericDAO.findEntitiesByNamedQueryMultiCond("getActiveAssFrZip", queryParam, 0, -1);
	}
	
	@Override
	public List<Long> getActiveTTerrZipAlgmntsByPostalCdSalesPos(List<Long> salePosIds,
			String postalCd, Short tenantId) {
		List queryParam = new ArrayList();
		queryParam.add(salePosIds);
		queryParam.add(postalCd);
		queryParam.add(tenantId);
		
		return genericDAO.findEntitiesByNamedQueryMultiCond("getActiveSpsFrZip", queryParam, 0, -1);
	}

	@Override
	public TTerrZipAlgmnt fetchSourceMirrorGeoAlgmnt(long spId, String code, Short tenantId) {
		List queryParam = new ArrayList();
		queryParam.add(spId);
		queryParam.add(code);
		queryParam.add(tenantId);
		List<TTerrZipAlgmnt> list =  genericDAO.findEntitiesByNamedQueryMultiCond("getGeoAlignmntBySPnPostalcode", queryParam, 0, -1);
		 if(!list.isEmpty()){
				return (TTerrZipAlgmnt) list.get(0);
			}
			return null;
	}

	@Override
	public TTerrZipAlgmnt getTTerrZipAlgmnts( String postalCode,
			Integer geoZipId, long targetSPId, Short tenantId) {
		List queryParam = new ArrayList();
		queryParam.add(targetSPId);
		queryParam.add(postalCode);
		queryParam.add(tenantId);
		queryParam.add(geoZipId.intValue());
		
		List<TTerrZipAlgmnt> list =  genericDAO.findEntitiesByNamedQueryMultiCond("getGeoAlignmntByMltpleCd", queryParam, 0, -1);
		 if(!list.isEmpty()){
				return (TTerrZipAlgmnt) list.get(0);
			}
			return null;
	}
		
}
