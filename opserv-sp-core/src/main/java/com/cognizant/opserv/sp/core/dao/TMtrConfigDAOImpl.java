package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TAlgmntSalesHier;
import com.cognizant.opserv.sp.core.entity.TMtr;
import com.cognizant.opserv.sp.core.entity.TMtrConfig;
import com.cognizant.opserv.sp.core.entity.TMtrConfigId;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TMtrConfigDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tMtrConfigDAO")
public class TMtrConfigDAOImpl implements TMtrConfigDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TMtrConfigDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private static final String TALGMNTSALESHIER = "tAlgmntSalesHier";
	private static final String TMTR = "tMtr";

	private final Class<TMtrConfig> clazz;

	public Class<TMtrConfig> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TMtrConfigDAOImpl() {
		super();
		this.clazz = TMtrConfig.class;
	}

	private static final String JPAQL = "select tMtrConfigentity from TMtrConfig tMtrConfigentity";

	private static final String JPACOUNTQL = "select count(*) from TMtrConfig tMtrConfigentity";

	private static final String[] RESTRICTIONS = {
			"tMtrConfigentity.tMtrConfigId.mtrId = #{tMtrConfigentity.tMtrConfigId.getMtrId}",
			"tMtrConfigentity.tMtrConfigId.salesHierId = #{tMtrConfigentity.tMtrConfigId.getSalesHierId}",
			"tMtrConfigentity.activeFlag = #{tMtrConfigentity.getActiveFlag}",
			"tMtrConfigentity.childRollupFlag = #{tMtrConfigentity.getChildRollupFlag}",
			"Date(tMtrConfigentity.effStartDt) = '#{tMtrConfigentity.getEffStartDt}'",
			"Date(tMtrConfigentity.effEndDt) = '#{tMtrConfigentity.getEffEndDt}'",
			"tMtrConfigentity.createdBy = #{tMtrConfigentity.getCreatedBy}",
			"Date(tMtrConfigentity.createDt) = '#{tMtrConfigentity.getCreateDt}'",
			"tMtrConfigentity.updatedBy = #{tMtrConfigentity.getUpdatedBy}",
			"Date(tMtrConfigentity.updateDt) = '#{tMtrConfigentity.getUpdateDt}'",
			"tMtrConfigentity.tenantId = #{tMtrConfigentity.getTenantId}",
			"tMtrConfigentity.tAlgmntSalesHier.salesHierId = #{tMtrConfigentity.tAlgmntSalesHier.getSalesHierId}",
			"tMtrConfigentity.tMtr.mtrId = #{tMtrConfigentity.tMtr.getMtrId}" };

	/**
	 * Stores a new TMtrConfig entity object in to the persistent store
	 * 
	 * @param tMtrConfig
	 *            TMtrConfig Entity object to be persisted
	 * @return tMtrConfig Persisted TMtrConfig object
	 */
	public TMtrConfig createTMtrConfig(final TMtrConfig tMtrConfig) {
		LOGGER.info("=========== Create TMtrConfig ===========");
		return genericDAO.store(tMtrConfig);
	}

	/**
	 * Deletes a TMtrConfig entity object from the persistent store
	 * 
	 * @param tMtrConfig
	 *            TMtrConfig Entity object to be deleted
	 */
	public void deleteTMtrConfig(final TMtrConfigId tMtrConfigId) {
		LOGGER.info("=========== Delete TMtrConfig ===========");
		final TMtrConfig tMtrConfig = genericDAO.get(clazz, tMtrConfigId);
		genericDAO.remove(tMtrConfig);
	}

	/**
	 * Updates a TMtrConfig entity object in to the persistent store
	 * 
	 * @param tMtrConfig
	 *            TMtrConfig Entity object to be updated
	 * @return tMtrConfig Persisted TMtrConfig object
	 */
	public TMtrConfig updateTMtrConfig(final TMtrConfig tMtrConfig) {
		LOGGER.info("=========== Update TMtrConfig ===========");
		return genericDAO.update(tMtrConfig);
	}

	/**
	 * Retrieve an TMtrConfig object based on given TMtrConfig TMtrConfigId.
	 * 
	 * @param tMtrConfigId
	 *            the primary key value of the TMtrConfig Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TMtrConfig findTMtrConfigById(final TMtrConfigId tMtrConfigId) {
		LOGGER.info("find TMtrConfig instance with TMtrConfigId: " + tMtrConfigId);
		return genericDAO.get(clazz, tMtrConfigId);
	}

	/**
	 * Retrieve TMtrConfig based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TMtrConfig> list of TMtrConfig if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TMtrConfig> findTMtrConfigs(final SearchFilter<TMtrConfig> searchFilter) {
		LOGGER.info("=========== Find TMtrConfigs ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TMtrConfig tMtrConfig = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tMtrConfigentity", tMtrConfig);

		if (tMtrConfig.getTAlgmntSalesHier() == null) {
			jpaQuery.bind(TALGMNTSALESHIER, new TAlgmntSalesHier());
		} else {
			LOGGER.info("tAlgmntSalesHier {}"+ tMtrConfig.getTAlgmntSalesHier());

			jpaQuery.bind(TALGMNTSALESHIER, tMtrConfig.getTAlgmntSalesHier());
		}

		if (tMtrConfig.getTMtr() == null) {
			jpaQuery.bind(TMTR, new TMtr());
		} else {
			LOGGER.info("tMtr {}"+ tMtrConfig.getTMtr());

			jpaQuery.bind(TMTR, tMtrConfig.getTMtr());
		}
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TMtrConfigs.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTMtrConfigs(final SearchFilter<TMtrConfig> searchFilter) {
		LOGGER.info("=========== Count TMtrConfig ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TMtrConfig tMtrConfig = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tMtrConfigentity", tMtrConfig);

		if (tMtrConfig.getTAlgmntSalesHier() == null) {
			jpaCountQuery.bind(TALGMNTSALESHIER, new TAlgmntSalesHier());
		} else {
			LOGGER.info("tAlgmntSalesHier {}"+ tMtrConfig.getTAlgmntSalesHier());

			jpaCountQuery.bind(TALGMNTSALESHIER, tMtrConfig.getTAlgmntSalesHier());
		}

		if (tMtrConfig.getTMtr() == null) {
			jpaCountQuery.bind(TMTR, new TMtr());
		} else {
			LOGGER.info("tMtr {}"+ tMtrConfig.getTMtr());

			jpaCountQuery.bind(TMTR, tMtrConfig.getTMtr());
		}

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TMtrConfig based on given search criteria using JPA named Query.
	 * The search criteria is of TAlgmntSalesHier type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TMtrConfig> list of TMtrConfigs if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TMtrConfig> getTMtrConfigsByTAlgmntSalesHier(final SearchFilter<TAlgmntSalesHier> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TAlgmntSalesHier tAlgmntSalesHier = searchFilter.getEntityClass();
		List<Object> tAlgmntSalesHierList = new ArrayList<Object>();
		tAlgmntSalesHierList.add(tAlgmntSalesHier);
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTMtrConfigByTAlgmntSalesHier", tAlgmntSalesHierList, index,
				maxresult);
	}

	/**
	 * Count TMtrConfig based on given search criteria using JPA named Query.
	 * The search criteria is of TAlgmntSalesHier type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTMtrConfigsByTAlgmntSalesHier(final SearchFilter<TAlgmntSalesHier> searchFilter) {

		final TAlgmntSalesHier tAlgmntSalesHier = searchFilter.getEntityClass();
		List<Object> tAlgmntSalesHierList = new ArrayList<Object>();
		tAlgmntSalesHierList.add(tAlgmntSalesHier);
		return genericDAO.findEntitiesByNamedQuery("CountTMtrConfigsByTAlgmntSalesHier", tAlgmntSalesHierList);
	}

	/**
	 * Retrieve TMtrConfig based on given search criteria using JPA named Query.
	 * The search criteria is of TMtr type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TMtrConfig> list of TMtrConfigs if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TMtrConfig> getTMtrConfigsByTMtr(final SearchFilter<TMtr> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TMtr tMtr = searchFilter.getEntityClass();
		List<Object> tMtrList = new ArrayList<Object>();
		tMtrList.add(tMtr);
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTMtrConfigByTMtr", tMtrList, index, maxresult);
	}

	/**
	 * Count TMtrConfig based on given search criteria using JPA named Query.
	 * The search criteria is of TMtr type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTMtrConfigsByTMtr(final SearchFilter<TMtr> searchFilter) {

		final TMtr tMtr = searchFilter.getEntityClass();
		List<Object> tMtrList = new ArrayList<Object>();
		tMtrList.add(tMtr);
		return genericDAO.findEntitiesByNamedQuery("CountTMtrConfigsByTMtr", tMtrList);
	}

	
	
	public List countTMtrConfigsByTMtrIdAndSalesHierId(Integer metricID,  Long salesHierId ) {

		List paramList = new ArrayList();
		paramList.add(metricID);
		paramList.add(salesHierId);

		return genericDAO.findEntitiesByNamedQueryMultiCond("CountTMtrConfigsBuMtrIdAndSalesHierId", paramList, 0, -1);
	
	}

	@Override
	public List<TMtrConfig> findTMtrConfigBySalesHierId(Long salesHier,
			Short tenantId) {
		List paramList = new ArrayList();
		paramList.add(salesHier);
		paramList.add(tenantId);

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTMtrConfigBySalesHierId", paramList, 0, -1);
	}

	@Override
	public List<TMtrConfig> findTMtrConfigByMetrics(int category, Long algmntId,Long bussUnitId, Long salesTeamId, Long salesHierId, Short tenantId) {
		//public List<TMtrConfig> findTMtrConfigByMetrics(int category, Long salesHierId, Short tenantId) {
		List paramList = new ArrayList();
		paramList.add(category);
		paramList.add(algmntId);
		paramList.add(bussUnitId);
		paramList.add(salesTeamId);
		paramList.add(salesHierId);
		paramList.add(tenantId);

		return genericDAO.findEntitiesByNamedQueryMultiCond("getTMtrConfigsByTMetrics", paramList, 0, -1);
	}

	@Override
	public List<TMtrConfig> findTMtrConfigByMetricsWitoutCategory(Long algmntId, Long bussUnitId, Long salesTeamId, Long salesHierId,Short tenantId) {
		List paramList = new ArrayList();
		paramList.add(algmntId);
		paramList.add(bussUnitId);
		paramList.add(salesTeamId);
		paramList.add(salesHierId);
		paramList.add(tenantId);

		return genericDAO.findEntitiesByNamedQueryMultiCond("getTMtrConfigsByTMetricsWithoutCat", paramList, 0, -1);
	}
	
	@Override
	public List<TMtrConfig> findTMtrConfigByMtrId(Integer metricID){
		List<Object> metricIDList = new ArrayList<Object>();
		metricIDList.add(metricID);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTMtrConfigByMtrID", metricIDList, 0, -1);
	}
	
	@Override
	public List<Object[]> getConfiguredHierarchies(List<Long> sphIds, Set<Integer> mtrIds){		
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(mtrIds);
		paramList.add(sphIds);
		return genericDAO.findEntitiesByNamedQueryMultiCond("GetCofiguredHierarchiesByMtrIDs", paramList, 0, -1);
	}
	
	@Override
	public List<Object[]> getCofiguredHierarchiesTriggersByMtrIDs(Set<Integer> mtrIds,Short tenantId){		
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(mtrIds);
		paramList.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("GetCofiguredHierarchiesTriggersByMtrIDs", paramList, 0, -1);
	}	
	
}
