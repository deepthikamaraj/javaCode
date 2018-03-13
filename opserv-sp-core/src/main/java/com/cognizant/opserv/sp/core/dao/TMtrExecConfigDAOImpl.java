package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TMtrConfig;
import com.cognizant.opserv.sp.core.entity.TMtrExecConfig;
import com.cognizant.opserv.sp.core.entity.TMtrTrigger;
import com.cognizant.opserv.sp.core.entity.TSalesPos;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TMtrExecConfigDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tMtrExecConfigDAO")
public class TMtrExecConfigDAOImpl implements TMtrExecConfigDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TMtrExecConfigDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private static final String TMTRCONFIG = "tMtrConfig";
	private static final String TMTRTRIGGER = "tMtrTrigger";

	private final Class<TMtrExecConfig> clazz;

	public Class<TMtrExecConfig> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TMtrExecConfigDAOImpl() {
		super();
		this.clazz = TMtrExecConfig.class;
	}

	private static final String JPAQL = "select tMtrExecConfigentity from TMtrExecConfig tMtrExecConfigentity";

	private static final String JPACOUNTQL = "select count(tMtrExecConfigentity) from TMtrExecConfig tMtrExecConfigentity";

	private static final String[] RESTRICTIONS = {
			"tMtrExecConfigentity.configId = #{tMtrExecConfigentity.getConfigId}",
			"tMtrExecConfigentity.createdBy = #{tMtrExecConfigentity.getCreatedBy}",
			"Date(tMtrExecConfigentity.createDt) = '#{tMtrExecConfigentity.getCreateDt}'",
			"tMtrExecConfigentity.updatedBy = #{tMtrExecConfigentity.getUpdatedBy}",
			"Date(tMtrExecConfigentity.updateDt) = '#{tMtrExecConfigentity.getUpdateDt}'",
			"tMtrExecConfigentity.tenantId = #{tMtrExecConfigentity.getTenantId}",
			"tMtrExecConfigentity.activeFlag = #{tMtrExecConfigentity.getActiveFlag}",
			"tMtrExecConfigentity.tMtrConfig.tMtrConfigId = #{tMtrExecConfigentity.tMtrConfig.getTMtrConfigId}",
			"tMtrExecConfigentity.tMtrTrigger.triggerId = #{tMtrExecConfigentity.tMtrTrigger.getTriggerId}" };

	/**
	 * Stores a new TMtrExecConfig entity object in to the persistent store
	 * 
	 * @param tMtrExecConfig
	 *            TMtrExecConfig Entity object to be persisted
	 * @return tMtrExecConfig Persisted TMtrExecConfig object
	 */
	public TMtrExecConfig createTMtrExecConfig(final TMtrExecConfig tMtrExecConfig) {
		LOGGER.info("=========== Create TMtrExecConfig ===========");
		return genericDAO.store(tMtrExecConfig);
	}

	/**
	 * Deletes a TMtrExecConfig entity object from the persistent store
	 * 
	 * @param tMtrExecConfig
	 *            TMtrExecConfig Entity object to be deleted
	 */
	public void deleteTMtrExecConfig(final Integer configId) {
		LOGGER.info("=========== Delete TMtrExecConfig ===========");
		final TMtrExecConfig tMtrExecConfig = genericDAO.get(clazz, configId);
		genericDAO.remove(tMtrExecConfig);
	}

	/**
	 * Updates a TMtrExecConfig entity object in to the persistent store
	 * 
	 * @param tMtrExecConfig
	 *            TMtrExecConfig Entity object to be updated
	 * @return tMtrExecConfig Persisted TMtrExecConfig object
	 */
	public TMtrExecConfig updateTMtrExecConfig(final TMtrExecConfig tMtrExecConfig) {
		LOGGER.info("=========== Update TMtrExecConfig ===========");
		return genericDAO.update(tMtrExecConfig);
	}

	/**
	 * Retrieve an TMtrExecConfig object based on given TMtrExecConfig configId.
	 * 
	 * @param tMtrExecConfigId
	 *            the primary key value of the TMtrExecConfig Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TMtrExecConfig findTMtrExecConfigById(final Integer tMtrExecConfigId) {
		LOGGER.info("find TMtrExecConfig instance with configId: " + tMtrExecConfigId);
		return genericDAO.get(clazz, tMtrExecConfigId);
	}

	/**
	 * Retrieve TMtrExecConfig based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TMtrExecConfig> list of TMtrExecConfig if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TMtrExecConfig> findTMtrExecConfigs(final SearchFilter<TMtrExecConfig> searchFilter) {
		LOGGER.info("=========== Find TMtrExecConfigs ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TMtrExecConfig tMtrExecConfig = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tMtrExecConfigentity", tMtrExecConfig);

		if (tMtrExecConfig.getTMtrConfig() == null) {
			jpaQuery.bind(TMTRCONFIG, new TMtrConfig());
		} else {
			LOGGER.info("tMtrConfig {}"+ tMtrExecConfig.getTMtrConfig());

			jpaQuery.bind(TMTRCONFIG, tMtrExecConfig.getTMtrConfig());
		}

		if (tMtrExecConfig.getTMtrTrigger() == null) {
			jpaQuery.bind(TMTRTRIGGER, new TMtrTrigger());
		} else {
			LOGGER.info("tMtrTrigger {}"+ tMtrExecConfig.getTMtrTrigger());

			jpaQuery.bind(TMTRTRIGGER, tMtrExecConfig.getTMtrTrigger());
		}
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TMtrExecConfigs.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTMtrExecConfigs(final SearchFilter<TMtrExecConfig> searchFilter) {
		LOGGER.info("=========== Count TMtrExecConfig ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TMtrExecConfig tMtrExecConfig = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tMtrExecConfigentity", tMtrExecConfig);

		if (tMtrExecConfig.getTMtrConfig() == null) {
			jpaCountQuery.bind(TMTRCONFIG, new TMtrConfig());
		} else {
			LOGGER.info("tMtrConfig {}"+ tMtrExecConfig.getTMtrConfig());

			jpaCountQuery.bind(TMTRCONFIG, tMtrExecConfig.getTMtrConfig());
		}

		if (tMtrExecConfig.getTMtrTrigger() == null) {
			jpaCountQuery.bind(TMTRTRIGGER, new TMtrTrigger());
		} else {
			LOGGER.info("tMtrTrigger {}"+ tMtrExecConfig.getTMtrTrigger());

			jpaCountQuery.bind(TMTRTRIGGER, tMtrExecConfig.getTMtrTrigger());
		}

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TMtrExecConfig based on given search criteria using JPA named Query.
	 * The search criteria is of TMtrConfig type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TMtrExecConfig> list of TMtrExecConfigs if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TMtrExecConfig> getTMtrExecConfigsByTMtrConfig(final SearchFilter<TMtrConfig> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TMtrConfig tMtrConfig = searchFilter.getEntityClass();
		List<Object> tMtrConfigList = new ArrayList<Object>();
		tMtrConfigList.add(tMtrConfig);
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTMtrExecConfigByTMtrConfig", tMtrConfigList, index, maxresult);
	}

	/**
	 * Count TMtrExecConfig based on given search criteria using JPA named Query.
	 * The search criteria is of TMtrConfig type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTMtrExecConfigsByTMtrConfig(final SearchFilter<TMtrConfig> searchFilter) {

		final TMtrConfig tMtrConfig = searchFilter.getEntityClass();
		List<Object> tMtrConfigList = new ArrayList<Object>();
		tMtrConfigList.add(tMtrConfig);
		return genericDAO.findEntitiesByNamedQuery("CountTMtrExecConfigsByTMtrConfig", tMtrConfigList);
	}

	/**
	 * Retrieve TMtrExecConfig based on given search criteria using JPA named Query.
	 * The search criteria is of TMtrTrigger type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TMtrExecConfig> list of TMtrExecConfigs if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TMtrExecConfig> getTMtrExecConfigsByTMtrTrigger(final SearchFilter<TMtrTrigger> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TMtrTrigger tMtrTrigger = searchFilter.getEntityClass();
		List<Object> tMtrTriggerList = new ArrayList<Object>();
		tMtrTriggerList.add(tMtrTrigger);
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTMtrExecConfigByTMtrTrigger", tMtrTriggerList, index, maxresult);
	}

	/**
	 * Count TMtrExecConfig based on given search criteria using JPA named Query.
	 * The search criteria is of TMtrTrigger type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTMtrExecConfigsByTMtrTrigger(final SearchFilter<TMtrTrigger> searchFilter) {

		final TMtrTrigger tMtrTrigger = searchFilter.getEntityClass();
		List<Object> tMtrTriggerList = new ArrayList<Object>();
		tMtrTriggerList.add(tMtrTrigger);
		return genericDAO.findEntitiesByNamedQuery("CountTMtrExecConfigsByTMtrTrigger", tMtrTriggerList);
	}

	@Override
	public List<TMtrExecConfig> findTMtrExecConfigByMtrId(Integer metricId) {
		List<Object> metricIdList = new ArrayList<Object>();
		metricIdList.add(metricId);
		return genericDAO.findEntitiesByNamedQuery("FindTMtrExecConfigByMtrId", metricIdList);
	}

	@Override
	public List<TSalesPos> getSalesPosDtl(Long alignmentId,
			Long businessUnitId, Long salesTeamId, Long salesHierId, Short tenantId) {
		List queryParam = new ArrayList();
		queryParam.add(alignmentId);
		queryParam.add(businessUnitId);
		queryParam.add(salesTeamId);
		queryParam.add(salesHierId);
		queryParam.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("getTSalesPosDtlNew", queryParam, 0, -1);
	}
	
	@Override
	public List<Object> getTMtrExecConfig(Integer metricId, Long slaesHierId, Integer triggerId){
		List queryParam = new ArrayList();
		queryParam.add(metricId);
		queryParam.add(slaesHierId);
		queryParam.add(triggerId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("getConfigIdForTrigger", queryParam, 0, -1);
	}
	@Override
	public List<TMtrExecConfig> getTMtrExecConfigss(Integer triggerId,
			Long salesHierId, Short tenantId,Long algmntId, Long bussUnitId, Long salesTeamId) {
		List queryParam = new ArrayList();
		queryParam.add(triggerId);
		queryParam.add(salesHierId);
		queryParam.add(tenantId);
		queryParam.add(algmntId);
		queryParam.add(bussUnitId);
		queryParam.add(salesTeamId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("getTMtrExecConfigs", queryParam, 0, -1);
	}
	
	@Override
	public List<TMtrExecConfig> getExecutionConfiguration(Short tenantId){
		List<Object> tenantIdList = new ArrayList<Object>();
		tenantIdList.add(tenantId);
		return genericDAO.findEntitiesByNamedQuery("getExecutionConfiguration",tenantIdList);
	}
	
	@Override
	public List<TMtrExecConfig> getTMtrExecConfigsForTrigger(Integer metricId, Long slaesHierId, Integer triggerId){
		List<Object> queryParam = new ArrayList<Object>();
		queryParam.add(metricId);
		queryParam.add(slaesHierId);
		queryParam.add(triggerId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("getTMtrExecConfigsForTrigger", queryParam, 0, -1);
	}

	@Override
	public List<TMtrExecConfig> findAllTMtrExecConfigByMtrId(Integer metricId,Long slaesHierId) {
		List<Object> queryParam = new ArrayList<Object>();
		queryParam.add(metricId);
		queryParam.add(slaesHierId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindAllTMtrExecConfigByMtrId", queryParam, 0, -1);
	}
	
	@Override
	public List<Object> getMetricExecutionConfig(Long alignmentId, Long businessUnitId, Long salesTeamId, Long salesHierId,List<Integer> triggerIdList, Short tenantId){
		List queryParam = new ArrayList();
		queryParam.add(alignmentId);
		queryParam.add(businessUnitId);
		queryParam.add(salesTeamId);
		queryParam.add(salesHierId);
		queryParam.add(triggerIdList);
		queryParam.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindMetricExecutionConfiguration", queryParam, 0, -1);
	}
	
	@Override
	public List<Object[]> getMetricExecConfig(Long alignmentId, Long businessUnitId, Long salesTeamId, Long salesHierId,List<Integer> metricIds,List<Integer> triggerIdList, Short tenantId){
		List<Object> queryParam = new ArrayList<Object>();
		queryParam.add(alignmentId);
		queryParam.add(businessUnitId);
		queryParam.add(salesTeamId);
		queryParam.add(salesHierId);
		queryParam.add(metricIds);
		queryParam.add(triggerIdList);
		queryParam.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("GetMetricExecConfig", queryParam, 0, -1);
	}
	
	@Override
	public List<Object[]> findAllTMtrExecConfigByALBUST(Long alignmentId, Long businessUnitId, Long salesTeamId,Short tenantId){
		List<Object> queryParam = new ArrayList<Object>();
		queryParam.add(alignmentId);
		queryParam.add(businessUnitId);
		queryParam.add(salesTeamId);
		queryParam.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindAllTMtrExecConfigByALBUST", queryParam, 0, -1);
	}
	
	
}
