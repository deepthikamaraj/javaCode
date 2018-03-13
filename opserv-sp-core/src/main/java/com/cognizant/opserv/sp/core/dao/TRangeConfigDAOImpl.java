package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TRangeConfig;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TRangeConfigDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tRangeConfigDAO")
public class TRangeConfigDAOImpl implements TRangeConfigDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TRangeConfigDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private final Class<TRangeConfig> clazz;

	public Class<TRangeConfig> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TRangeConfigDAOImpl() {
		super();
		this.clazz = TRangeConfig.class;
	}

	private static final String JPAQL = "select tRangeConfigentity from TRangeConfig tRangeConfigentity";

	private static final String JPACOUNTQL = "select count(tRangeConfigentity) from TRangeConfig tRangeConfigentity";

	private static final String[] RESTRICTIONS = {
			"tRangeConfigentity.rangeConfigId = #{tRangeConfigentity.getRangeConfigId}",
			"tRangeConfigentity.lowLim = #{tRangeConfigentity.getLowLim}",
			"tRangeConfigentity.upLim = #{tRangeConfigentity.getUpLim}",
			"tRangeConfigentity.numLevel = #{tRangeConfigentity.getNumLevel}",
			"tRangeConfigentity.activeFlag = #{tRangeConfigentity.getActiveFlag}",
			"tRangeConfigentity.createdBy = #{tRangeConfigentity.getCreatedBy}",
			"Date(tRangeConfigentity.createDt) = '#{tRangeConfigentity.getCreateDt}'",
			"tRangeConfigentity.updatedBy = #{tRangeConfigentity.getUpdatedBy}",
			"Date(tRangeConfigentity.updateDt) = '#{tRangeConfigentity.getUpdateDt}'",
			"tRangeConfigentity.tenantId = #{tRangeConfigentity.getTenantId}" };

	/**
	 * Stores a new TRangeConfig entity object in to the persistent store
	 * 
	 * @param tRangeConfig
	 *            TRangeConfig Entity object to be persisted
	 * @return tRangeConfig Persisted TRangeConfig object
	 */
	public TRangeConfig createTRangeConfig(final TRangeConfig tRangeConfig) {
		LOGGER.info("=========== Create TRangeConfig ===========");
		return genericDAO.store(tRangeConfig);
	}

	/**
	 * Deletes a TRangeConfig entity object from the persistent store
	 * 
	 * @param tRangeConfig
	 *            TRangeConfig Entity object to be deleted
	 */
	public void deleteTRangeConfig(final Integer rangeConfigId) {
		LOGGER.info("=========== Delete TRangeConfig ===========");
		final TRangeConfig tRangeConfig = genericDAO.get(clazz, rangeConfigId);
		genericDAO.remove(tRangeConfig);
	}

	/**
	 * Updates a TRangeConfig entity object in to the persistent store
	 * 
	 * @param tRangeConfig
	 *            TRangeConfig Entity object to be updated
	 * @return tRangeConfig Persisted TRangeConfig object
	 */
	public TRangeConfig updateTRangeConfig(final TRangeConfig tRangeConfig) {
		LOGGER.info("=========== Update TRangeConfig ===========");
		return genericDAO.update(tRangeConfig);
	}

	/**
	 * Retrieve an TRangeConfig object based on given TRangeConfig rangeConfigId.
	 * 
	 * @param tRangeConfigId
	 *            the primary key value of the TRangeConfig Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TRangeConfig findTRangeConfigById(final Integer tRangeConfigId) {
		LOGGER.info("find TRangeConfig instance with rangeConfigId: " + tRangeConfigId);
		return genericDAO.get(clazz, tRangeConfigId);
	}

	/**
	 * Retrieve TRangeConfig based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRangeConfig> list of TRangeConfig if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TRangeConfig> findTRangeConfigs(final SearchFilter<TRangeConfig> searchFilter) {
		LOGGER.info("=========== Find TRangeConfigs ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TRangeConfig tRangeConfig = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tRangeConfigentity", tRangeConfig);
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TRangeConfigs.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTRangeConfigs(final SearchFilter<TRangeConfig> searchFilter) {
		LOGGER.info("=========== Count TRangeConfig ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TRangeConfig tRangeConfig = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tRangeConfigentity", tRangeConfig);

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	@Override
	public List<TRangeConfig> findTRangeConfigByLimits(int lowerLimit,
			int upperLimit,short tenantId) {
		List queryParam = new ArrayList();
		queryParam.add(lowerLimit);
		queryParam.add(upperLimit);
		queryParam.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTRangeConfigByLimits", queryParam, 0,-1);
	}

	@Override
	public List<TRangeConfig> findTRangeConfigByTenant(Short tenantId) {
		List queryParam = new ArrayList();
		queryParam.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTRangeConfigByTenant", queryParam, 0,-1);
	}

	@Override
	public List<TRangeConfig> findTRangeConfig(Short tenantId) {
		List queryParam = new ArrayList();
		queryParam.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTRangeConfig", queryParam, 0,-1);
	}

}
