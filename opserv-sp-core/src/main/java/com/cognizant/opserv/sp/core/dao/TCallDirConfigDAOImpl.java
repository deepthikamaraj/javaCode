package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TCallDirConfig;
import com.cognizant.opserv.sp.core.entity.TCallPlanConfig;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TCallDirConfigDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tCallDirConfigDAO")
public class TCallDirConfigDAOImpl implements TCallDirConfigDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TCallDirConfigDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private static final String TCALLPLANCONFIG = "tCallPlanConfig";

	private final Class<TCallDirConfig> clazz;

	public Class<TCallDirConfig> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TCallDirConfigDAOImpl() {
		super();
		this.clazz = TCallDirConfig.class;
	}

	private static final String JPAQL = "select tCallDirConfigentity from TCallDirConfig tCallDirConfigentity";

	private static final String JPACOUNTQL = "select count(tCallDirConfigentity) from TCallDirConfig tCallDirConfigentity";

	private static final String[] RESTRICTIONS = {
			"tCallDirConfigentity.callDirConfigId = #{tCallDirConfigentity.getCallDirConfigId}",
			"tCallDirConfigentity.prdCnt = #{tCallDirConfigentity.getPrdCnt}",
			"tCallDirConfigentity.createdBy = #{tCallDirConfigentity.getCreatedBy}",
			"Date(tCallDirConfigentity.createDt) = '#{tCallDirConfigentity.getCreateDt}'",
			"tCallDirConfigentity.updatedBy = #{tCallDirConfigentity.getUpdatedBy}",
			"Date(tCallDirConfigentity.updateDt) = '#{tCallDirConfigentity.getUpdateDt}'",
			"tCallDirConfigentity.tenantId = #{tCallDirConfigentity.getTenantId}",
			"tCallDirConfigentity.activeFlag = #{tCallDirConfigentity.getActiveFlag}",
			"tCallDirConfigentity.tCallPlanConfig.callPlanConfigId = #{tCallDirConfigentity.tCallPlanConfig.getCallPlanConfigId}" };

	/**
	 * Stores a new TCallDirConfig entity object in to the persistent store
	 * 
	 * @param tCallDirConfig
	 *            TCallDirConfig Entity object to be persisted
	 * @return tCallDirConfig Persisted TCallDirConfig object
	 */
	public TCallDirConfig createTCallDirConfig(final TCallDirConfig tCallDirConfig) {
		LOGGER.info("=========== Create TCallDirConfig ===========");
		return genericDAO.store(tCallDirConfig);
	}

	/**
	 * Deletes a TCallDirConfig entity object from the persistent store
	 * 
	 * @param tCallDirConfig
	 *            TCallDirConfig Entity object to be deleted
	 */
	public void deleteTCallDirConfig(final Integer callDirConfigId) {
		LOGGER.info("=========== Delete TCallDirConfig ===========");
		final TCallDirConfig tCallDirConfig = genericDAO.get(clazz, callDirConfigId);
		genericDAO.remove(tCallDirConfig);
	}

	/**
	 * Updates a TCallDirConfig entity object in to the persistent store
	 * 
	 * @param tCallDirConfig
	 *            TCallDirConfig Entity object to be updated
	 * @return tCallDirConfig Persisted TCallDirConfig object
	 */
	public TCallDirConfig updateTCallDirConfig(final TCallDirConfig tCallDirConfig) {
		LOGGER.info("=========== Update TCallDirConfig ===========");
		return genericDAO.update(tCallDirConfig);
	}

	/**
	 * Retrieve an TCallDirConfig object based on given TCallDirConfig callDirConfigId.
	 * 
	 * @param tCallDirConfigId
	 *            the primary key value of the TCallDirConfig Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TCallDirConfig findTCallDirConfigById(final Integer tCallDirConfigId) {
		LOGGER.info("find TCallDirConfig instance with callDirConfigId: " + tCallDirConfigId);
		return genericDAO.get(clazz, tCallDirConfigId);
	}

	/**
	 * Retrieve TCallDirConfig based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCallDirConfig> list of TCallDirConfig if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TCallDirConfig> findTCallDirConfigs(final SearchFilter<TCallDirConfig> searchFilter) {
		LOGGER.info("=========== Find TCallDirConfigs ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TCallDirConfig tCallDirConfig = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tCallDirConfigentity", tCallDirConfig);

		if (tCallDirConfig.getTCallPlanConfig() == null) {
			jpaQuery.bind(TCALLPLANCONFIG, new TCallPlanConfig());
		} else {
			LOGGER.info("tCallPlanConfig {}" + tCallDirConfig.getTCallPlanConfig());

			jpaQuery.bind(TCALLPLANCONFIG, tCallDirConfig.getTCallPlanConfig());
		}
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TCallDirConfigs.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTCallDirConfigs(final SearchFilter<TCallDirConfig> searchFilter) {
		LOGGER.info("=========== Count TCallDirConfig ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TCallDirConfig tCallDirConfig = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tCallDirConfigentity", tCallDirConfig);

		if (tCallDirConfig.getTCallPlanConfig() == null) {
			jpaCountQuery.bind(TCALLPLANCONFIG, new TCallPlanConfig());
		} else {
			LOGGER.info("tCallPlanConfig {}" + tCallDirConfig.getTCallPlanConfig());

			jpaCountQuery.bind(TCALLPLANCONFIG, tCallDirConfig.getTCallPlanConfig());
		}

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TCallDirConfig based on given search criteria using JPA named Query.
	 * The search criteria is of TCallPlanConfig type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCallDirConfig> list of TCallDirConfigs if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TCallDirConfig> getTCallDirConfigsByTCallPlanConfig(final SearchFilter<TCallPlanConfig> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TCallPlanConfig tCallPlanConfig = searchFilter.getEntityClass();
		List<Object> tCallPlanConfigList = new ArrayList<Object>();
		tCallPlanConfigList.add(tCallPlanConfig);
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTCallDirConfigByTCallPlanConfig", tCallPlanConfigList, index,
				maxresult);
	}

	/**
	 * Count TCallDirConfig based on given search criteria using JPA named Query.
	 * The search criteria is of TCallPlanConfig type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTCallDirConfigsByTCallPlanConfig(final SearchFilter<TCallPlanConfig> searchFilter) {

		final TCallPlanConfig tCallPlanConfig = searchFilter.getEntityClass();
		List<Object> tCallPlanConfigList = new ArrayList<Object>();
		return genericDAO.findEntitiesByNamedQuery("CountTCallDirConfigsByTCallPlanConfig", tCallPlanConfigList);
	}
	
	
	public List<TCallDirConfig> getTCallDirConfig(Integer callPlanId, Integer prdCnt, Short tenantId) {

		List paramList=new ArrayList();
		paramList.add(callPlanId);
		paramList.add(prdCnt);
		paramList.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTCallDirConfig", paramList, 0, -1);
	}
	
	
}
