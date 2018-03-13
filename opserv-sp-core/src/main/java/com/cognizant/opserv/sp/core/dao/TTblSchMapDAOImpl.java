package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TDsStg;
import com.cognizant.opserv.sp.core.entity.TTblSchMap;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TTblSchMapDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tTblSchMapDAO")
public class TTblSchMapDAOImpl implements TTblSchMapDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TTblSchMapDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private static final String TDSSTG = "tDsStg";

	private final Class<TTblSchMap> clazz;

	public Class<TTblSchMap> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TTblSchMapDAOImpl() {
		super();
		this.clazz = TTblSchMap.class;
	}

	private static final String JPAQL = "select tTblSchMapentity from TTblSchMap tTblSchMapentity";

	private static final String JPACOUNTQL = "select count(tTblSchMapentity) from TTblSchMap tTblSchMapentity";

	private static final String[] RESTRICTIONS = {
			"tTblSchMapentity.mappingId like '#{tTblSchMapentity.getMappingId}%'",
			"tTblSchMapentity.stgIp like '#{tTblSchMapentity.getStgIp}%'",
			"tTblSchMapentity.stgDb like '#{tTblSchMapentity.getStgDb}%'",
			"tTblSchMapentity.stgTblName like '#{tTblSchMapentity.getStgTblName}%'",
			"tTblSchMapentity.stgColumnName like '#{tTblSchMapentity.getStgColumnName}%'",
			"tTblSchMapentity.stgDataType like '#{tTblSchMapentity.getStgDataType}%'",
			"tTblSchMapentity.stgColumnLength like '#{tTblSchMapentity.getStgColumnLength}%'",
			"tTblSchMapentity.stgNullOption like '#{tTblSchMapentity.getStgNullOption}%'",
			"tTblSchMapentity.prdIp like '#{tTblSchMapentity.getPrdIp}%'",
			"tTblSchMapentity.prdDb like '#{tTblSchMapentity.getPrdDb}%'",
			"tTblSchMapentity.prdTblName like '#{tTblSchMapentity.getPrdTblName}%'",
			"tTblSchMapentity.prdColumnName like '#{tTblSchMapentity.getPrdColumnName}%'",
			"Date(tTblSchMapentity.mapDtTm) = '#{tTblSchMapentity.getMapDtTm}'",
			"tTblSchMapentity.createdBy = #{tTblSchMapentity.getCreatedBy}",
			"Date(tTblSchMapentity.createDt) = '#{tTblSchMapentity.getCreateDt}'",
			"tTblSchMapentity.updatedBy = #{tTblSchMapentity.getUpdatedBy}",
			"Date(tTblSchMapentity.updateDt) = '#{tTblSchMapentity.getUpdateDt}'",
			"tTblSchMapentity.tenantId = #{tTblSchMapentity.getTenantId}",
			"tTblSchMapentity.colTrnsform like '#{tTblSchMapentity.getColTrnsform}%'",
			"tTblSchMapentity.stgKeyCol = #{tTblSchMapentity.getStgKeyCol}",
			"tTblSchMapentity.stgUnqCol = #{tTblSchMapentity.getStgUnqCol}",
			"tTblSchMapentity.startPos = #{tTblSchMapentity.getStartPos}",
			"tTblSchMapentity.endPos = #{tTblSchMapentity.getEndPos}",
			"tTblSchMapentity.tDsStg.dsId = #{tTblSchMapentity.tDsStg.getDsId}" };

	/**
	 * Stores a new TTblSchMap entity object in to the persistent store
	 * 
	 * @param tTblSchMap
	 *            TTblSchMap Entity object to be persisted
	 * @return tTblSchMap Persisted TTblSchMap object
	 */
	public TTblSchMap createTTblSchMap(final TTblSchMap tTblSchMap) {
		LOGGER.info("=========== Create TTblSchMap ===========");
		TTblSchMap tblRow = genericDAO.store(tTblSchMap);
		return tblRow;
	}

	/**
	 * Deletes a TTblSchMap entity object from the persistent store
	 * 
	 * @param tTblSchMap
	 *            TTblSchMap Entity object to be deleted
	 */
	public void deleteTTblSchMap(final String mappingId) {
		LOGGER.info("=========== Delete TTblSchMap ===========");
		final TTblSchMap tTblSchMap = genericDAO.get(clazz, mappingId);
		genericDAO.remove(tTblSchMap);
	}

	/**
	 * Updates a TTblSchMap entity object in to the persistent store
	 * 
	 * @param tTblSchMap
	 *            TTblSchMap Entity object to be updated
	 * @return tTblSchMap Persisted TTblSchMap object
	 */
	public TTblSchMap updateTTblSchMap(final TTblSchMap tTblSchMap) {
		LOGGER.info("=========== Update TTblSchMap ===========");
		return genericDAO.update(tTblSchMap);
	}

	/**
	 * Retrieve an TTblSchMap object based on given TTblSchMap mappingId.
	 * 
	 * @param tTblSchMapId
	 *            the primary key value of the TTblSchMap Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TTblSchMap findTTblSchMapById(final Long tTblSchMapId) {
		LOGGER.info("find TTblSchMap instance with mappingId: " + tTblSchMapId);
		return genericDAO.get(clazz, tTblSchMapId);
	}

	/**
	 * Retrieve TTblSchMap based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TTblSchMap> list of TTblSchMap if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TTblSchMap> findTTblSchMaps(final SearchFilter<TTblSchMap> searchFilter) {
		LOGGER.info("=========== Find TTblSchMaps ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TTblSchMap tTblSchMap = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tTblSchMapentity", tTblSchMap);

		if (tTblSchMap.getTDsStg() == null) {
			jpaQuery.bind(TDSSTG, new TDsStg());
		} else {
			LOGGER.info("tDsStg {}"+ tTblSchMap.getTDsStg());

			jpaQuery.bind(TDSSTG, tTblSchMap.getTDsStg());
		}
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TTblSchMaps.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTTblSchMaps(final SearchFilter<TTblSchMap> searchFilter) {
		LOGGER.info("=========== Count TTblSchMap ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TTblSchMap tTblSchMap = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tTblSchMapentity", tTblSchMap);

		if (tTblSchMap.getTDsStg() == null) {
			jpaCountQuery.bind(TDSSTG, new TDsStg());
		} else {
			LOGGER.info("tDsStg {}"+ tTblSchMap.getTDsStg());

			jpaCountQuery.bind(TDSSTG, tTblSchMap.getTDsStg());
		}

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TTblSchMap based on given search criteria using JPA named Query.
	 * The search criteria is of TDsStg type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TTblSchMap> list of TTblSchMaps if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TTblSchMap> getTTblSchMapsByTDsStg(final SearchFilter<TDsStg> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTTblSchMapByTDsStg", queryParam, index, maxresult);
	}

	/**
	 * Count TTblSchMap based on given search criteria using JPA named Query.
	 * The search criteria is of TDsStg type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTTblSchMapsByTDsStg(final SearchFilter<TDsStg> searchFilter) {

		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());
		return genericDAO.findEntitiesByNamedQuery("CountTTblSchMapsByTDsStg", queryParam);
	}

	
	/**
	 * Retrieve All TPrdTblSch using JPA named Query.
	 *  
	 * @return List<TPrdTblSch> list of TPrdTblSch if it exists. 
	 * Returns null if not found
	 * 
	 */
	@Override
	public List<TTblSchMap> getAllTTblSchMap() {
		List<TTblSchMap> tTblSchMapList = genericDAO.findEntitiesByNamedQuery("FindAllTTblSchMaps");
		return tTblSchMapList;		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TTblSchMap> findTTblSchMapByTDsStgAndKeyCol(
			SearchFilter<TTblSchMap> searchFilter,Character compKey) {
		
		
		int index = searchFilter.getPaginationInfo().getStartIndex();
		int maxresult = searchFilter.getPaginationInfo().getMaxRows();
		final TTblSchMap tTblSchMap = searchFilter.getEntityClass();
		final Character prdKelCol = tTblSchMap.getPrdKeyCol();
		final TDsStg tDsStg = tTblSchMap.gettDsStg();
		@SuppressWarnings("rawtypes")
		List paramList = new ArrayList<>();
		paramList.add(tDsStg);
		paramList.add(prdKelCol);
		paramList.add(compKey);
		List<TTblSchMap> tTblSchMapList = genericDAO.findEntitiesByNamedQueryMultiCond("FindTTblSchMapByTDsStgAndKeyCol", paramList, index, maxresult);
		return tTblSchMapList;

	}

	@Override
	public int updateTable(String query) {
		int status = genericDAO.executeNativeQuery(query);
		return status;
	}
	
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<TTblSchMap> getTTblSchMapsByTDsStgAndTenantAndTableName(SearchFilter<TTblSchMap> searchFilter){
		int index = searchFilter.getPaginationInfo().getStartIndex();
		int maxresult = searchFilter.getPaginationInfo().getMaxRows();
		final TTblSchMap tTblSchMap = searchFilter.getEntityClass();		
		final TDsStg tDsStg = tTblSchMap.gettDsStg();		
		List paramList = new ArrayList<>();
		paramList.add(tDsStg);
		paramList.add(tTblSchMap.getStgTblName());
		paramList.add(tTblSchMap.getTenantId());
		List<TTblSchMap> tTblSchMapList = genericDAO.findEntitiesByNamedQueryMultiCond("GetTTblSchMapsByTDsStgAndTenantAndTableName", paramList, index, maxresult);
		return tTblSchMapList;

	}

}
