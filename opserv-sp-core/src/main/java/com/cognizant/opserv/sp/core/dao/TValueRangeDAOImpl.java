package com.cognizant.opserv.sp.core.dao;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TRangeConfig;
import com.cognizant.opserv.sp.core.entity.TValueRange;
import com.cognizant.opserv.sp.core.entity.TValueRangeId;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TValueRangeDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tValueRangeDAO")
public class TValueRangeDAOImpl implements TValueRangeDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TValueRangeDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private static final String TRANGECONFIG = "tRangeConfig";

	private final Class<TValueRange> clazz;

	public Class<TValueRange> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TValueRangeDAOImpl() {
		super();
		this.clazz = TValueRange.class;
	}

	private static final String JPAQL = "select tValueRangeentity from TValueRange tValueRangeentity";

	private static final String JPACOUNTQL = "select count(*) from TValueRange tValueRangeentity";

	private static final String[] RESTRICTIONS = {
			"tValueRangeentity.tValueRangeId.rangeConfigId = #{tValueRangeentity.tValueRangeId.getRangeConfigId}",
			"tValueRangeentity.tValueRangeId.levelId = #{tValueRangeentity.tValueRangeId.getLevelId}",
			"tValueRangeentity.colorCd like '#{tValueRangeentity.getColorCd}%'",
			"tValueRangeentity.activeFlag = #{tValueRangeentity.getActiveFlag}",
			"tValueRangeentity.createdBy = #{tValueRangeentity.getCreatedBy}",
			"Date(tValueRangeentity.createDt) = '#{tValueRangeentity.getCreateDt}'",
			"tValueRangeentity.updatedBy = #{tValueRangeentity.getUpdatedBy}",
			"Date(tValueRangeentity.updateDt) = '#{tValueRangeentity.getUpdateDt}'",
			"tValueRangeentity.tenantId = #{tValueRangeentity.getTenantId}",
			"tValueRangeentity.tRangeConfig.rangeConfigId = #{tValueRangeentity.tRangeConfig.getRangeConfigId}" };

	/**
	 * Stores a new TValueRange entity object in to the persistent store
	 * 
	 * @param tValueRange
	 *            TValueRange Entity object to be persisted
	 * @return tValueRange Persisted TValueRange object
	 */
	public TValueRange createTValueRange(final TValueRange tValueRange) {
		LOGGER.info("=========== Create TValueRange ===========");
		return genericDAO.store(tValueRange);
	}

	/**
	 * Deletes a TValueRange entity object from the persistent store
	 * 
	 * @param tValueRange
	 *            TValueRange Entity object to be deleted
	 */
	public void deleteTValueRange(final TValueRangeId tValueRangeId) {
		LOGGER.info("=========== Delete TValueRange ===========");
		final TValueRange tValueRange = genericDAO.get(clazz, tValueRangeId);
		genericDAO.remove(tValueRange);
	}

	/**
	 * Updates a TValueRange entity object in to the persistent store
	 * 
	 * @param tValueRange
	 *            TValueRange Entity object to be updated
	 * @return tValueRange Persisted TValueRange object
	 */
	public TValueRange updateTValueRange(final TValueRange tValueRange) {
		LOGGER.info("=========== Update TValueRange ===========");
		return genericDAO.update(tValueRange);
	}

	/**
	 * Retrieve an TValueRange object based on given TValueRange TValueRangeId.
	 * 
	 * @param tValueRangeId
	 *            the primary key value of the TValueRange Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TValueRange findTValueRangeById(final TValueRangeId tValueRangeId) {
		LOGGER.info("find TValueRange instance with TValueRangeId: " + tValueRangeId);
		return genericDAO.get(clazz, tValueRangeId);
	}

	/**
	 * Retrieve TValueRange based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TValueRange> list of TValueRange if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TValueRange> findTValueRanges(final SearchFilter<TValueRange> searchFilter) {
		LOGGER.info("=========== Find TValueRanges ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TValueRange tValueRange = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tValueRangeentity", tValueRange);

		if (tValueRange.getTRangeConfig() == null) {
			jpaQuery.bind(TRANGECONFIG, new TRangeConfig());
		} else {
			LOGGER.info("tRangeConfig {}"+ tValueRange.getTRangeConfig());

			jpaQuery.bind(TRANGECONFIG, tValueRange.getTRangeConfig());
		}
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TValueRanges.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTValueRanges(final SearchFilter<TValueRange> searchFilter) {
		LOGGER.info("=========== Count TValueRange ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TValueRange tValueRange = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tValueRangeentity", tValueRange);

		if (tValueRange.getTRangeConfig() == null) {
			jpaCountQuery.bind(TRANGECONFIG, new TRangeConfig());
		} else {
			LOGGER.info("tRangeConfig {}"+ tValueRange.getTRangeConfig());

			jpaCountQuery.bind(TRANGECONFIG, tValueRange.getTRangeConfig());
		}

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TValueRange based on given search criteria using JPA named Query.
	 * The search criteria is of TRangeConfig type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TValueRange> list of TValueRanges if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TValueRange> getTValueRangesByTRangeConfig(final SearchFilter<TRangeConfig> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		List<Object> tRangeConfigList=new ArrayList<Object>();
		tRangeConfigList.add(searchFilter.getEntityClass());
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTValueRangeByTRangeConfig", tRangeConfigList, index, maxresult);
	}

	/**
	 * Count TValueRange based on given search criteria using JPA named Query.
	 * The search criteria is of TRangeConfig type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTValueRangesByTRangeConfig(final SearchFilter<TRangeConfig> searchFilter) {
          List<Object> queryParam=new ArrayList<Object> ();
           queryParam.add(searchFilter.getEntityClass());
		return genericDAO.findEntitiesByNamedQuery("CountTValueRangesByTRangeConfig", queryParam);
	}

	@Override
	public List<TValueRange> findTValueRangeByRangeId(int rangeId) {
		  List<Object> queryParam=new ArrayList<Object> ();
		   queryParam.add(rangeId);
		  return genericDAO.findEntitiesByNamedQuery("FindTValueRangeById", queryParam);
	}

	@Override
	public List<TValueRange> FindTValueRangeByIdAndLevel(int rangeConfigId,
			int numLevel, Short tenantId) {
		List queryParam = new ArrayList();
		queryParam.add(rangeConfigId);
		queryParam.add(numLevel);
		queryParam.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTValueRangeByIdAndLevel", queryParam, 0,-1);
	}

}
