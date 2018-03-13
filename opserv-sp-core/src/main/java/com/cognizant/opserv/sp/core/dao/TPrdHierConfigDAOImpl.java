package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TPrdConfig;
import com.cognizant.opserv.sp.core.entity.TPrdHierConfig;
import com.cognizant.opserv.sp.core.entity.TPrdHierConfigId;
import com.cognizant.opserv.sp.core.entity.TPrdHierLevel;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TPrdHierConfigDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tPrdHierConfigDAO")
public class TPrdHierConfigDAOImpl implements TPrdHierConfigDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TPrdHierConfigDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	//private static final String TPRDCONFIG = "tPrdConfig";
	//private static final String TPRDHIERLEVEL = "tPrdHierLevel";

	private final Class<TPrdHierConfig> clazz;

	public Class<TPrdHierConfig> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TPrdHierConfigDAOImpl() {
		super();
		this.clazz = TPrdHierConfig.class;
	}

	private static final String JPAQL = "select tPrdHierConfigentity from TPrdHierConfig tPrdHierConfigentity";

	private static final String JPACOUNTQL = "select count(*) from TPrdHierConfig tPrdHierConfigentity";

	private static final String[] RESTRICTIONS = {
			"tPrdHierConfigentity.tPrdHierConfigId.hierLevelId = #{tPrdHierConfigentity.tPrdHierConfigId.getHierLevelId}",
			"tPrdHierConfigentity.tPrdHierConfigId.prdConfigId = #{tPrdHierConfigentity.tPrdHierConfigId.getPrdConfigId}",
			"tPrdHierConfigentity.activeFlag = #{tPrdHierConfigentity.getActiveFlag}",
			"Date(tPrdHierConfigentity.effStartDt) = '#{tPrdHierConfigentity.getEffStartDt}'",
			"Date(tPrdHierConfigentity.effEndDt) = '#{tPrdHierConfigentity.getEffEndDt}'",
			"tPrdHierConfigentity.createdBy = #{tPrdHierConfigentity.getCreatedBy}",
			"Date(tPrdHierConfigentity.createDt) = '#{tPrdHierConfigentity.getCreateDt}'",
			"tPrdHierConfigentity.updatedBy = #{tPrdHierConfigentity.getUpdatedBy}",
			"Date(tPrdHierConfigentity.updateDt) = '#{tPrdHierConfigentity.getUpdateDt}'",
			"tPrdHierConfigentity.tenantId = #{tPrdHierConfigentity.getTenantId}"
			/*"tPrdHierConfigentity.tPrdConfig.prdConfigId = #{tPrdHierConfigentity.tPrdConfig.getPrdConfigId}",
			"tPrdHierConfigentity.tPrdHierLevel.hierLevelId = #{tPrdHierConfigentity.tPrdHierLevel.getHierLevelId}"*/ };

	/**
	 * Stores a new TPrdHierConfig entity object in to the persistent store
	 * 
	 * @param tPrdHierConfig
	 *            TPrdHierConfig Entity object to be persisted
	 * @return tPrdHierConfig Persisted TPrdHierConfig object
	 */
	public TPrdHierConfig createTPrdHierConfig(final TPrdHierConfig tPrdHierConfig) {
		LOGGER.info("=========== Create TPrdHierConfig ===========");
		return genericDAO.store(tPrdHierConfig);
	}

	/**
	 * Deletes a TPrdHierConfig entity object from the persistent store
	 * 
	 * @param tPrdHierConfig
	 *            TPrdHierConfig Entity object to be deleted
	 */
	public void deleteTPrdHierConfig(final TPrdHierConfigId tPrdHierConfigId) {
		LOGGER.info("=========== Delete TPrdHierConfig ===========");
		final TPrdHierConfig tPrdHierConfig = genericDAO.get(clazz, tPrdHierConfigId);
		genericDAO.remove(tPrdHierConfig);
	}

	/**
	 * Updates a TPrdHierConfig entity object in to the persistent store
	 * 
	 * @param tPrdHierConfig
	 *            TPrdHierConfig Entity object to be updated
	 * @return tPrdHierConfig Persisted TPrdHierConfig object
	 */
	public TPrdHierConfig updateTPrdHierConfig(final TPrdHierConfig tPrdHierConfig) {
		LOGGER.info("=========== Update TPrdHierConfig ===========");
		return genericDAO.update(tPrdHierConfig);
	}

	/**
	 * Retrieve an TPrdHierConfig object based on given TPrdHierConfig TPrdHierConfigId.
	 * 
	 * @param tPrdHierConfigId
	 *            the primary key value of the TPrdHierConfig Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TPrdHierConfig findTPrdHierConfigById(final TPrdHierConfigId tPrdHierConfigId) {
		LOGGER.info("find TPrdHierConfig instance with TPrdHierConfigId: " + tPrdHierConfigId);
		return genericDAO.get(clazz, tPrdHierConfigId);
	}

	/**
	 * Retrieve TPrdHierConfig based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TPrdHierConfig> list of TPrdHierConfig if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TPrdHierConfig> findTPrdHierConfigs(final SearchFilter<TPrdHierConfig> searchFilter) {
		LOGGER.info("=========== Find TPrdHierConfigs ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TPrdHierConfig tPrdHierConfig = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tPrdHierConfigentity", tPrdHierConfig);

		/*if (tPrdHierConfig.getTPrdConfig() == null) {
			jpaQuery.bind(TPRDCONFIG, new TPrdConfig());
		} else {
			LOGGER.info("tPrdConfig {}", tPrdHierConfig.getTPrdConfig());

			jpaQuery.bind(TPRDCONFIG, tPrdHierConfig.getTPrdConfig());
		}

		if (tPrdHierConfig.getTPrdHierLevel() == null) {
			jpaQuery.bind(TPRDHIERLEVEL, new TPrdHierLevel());
		} else {
			LOGGER.info("tPrdHierLevel {}", tPrdHierConfig.getTPrdHierLevel());

			jpaQuery.bind(TPRDHIERLEVEL, tPrdHierConfig.getTPrdHierLevel());
		}*/
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TPrdHierConfigs.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTPrdHierConfigs(final SearchFilter<TPrdHierConfig> searchFilter) {
		LOGGER.info("=========== Count TPrdHierConfig ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TPrdHierConfig tPrdHierConfig = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tPrdHierConfigentity", tPrdHierConfig);

		/*if (tPrdHierConfig.getTPrdConfig() == null) {
			jpaCountQuery.bind(TPRDCONFIG, new TPrdConfig());
		} else {
			LOGGER.info("tPrdConfig {}", tPrdHierConfig.getTPrdConfig());

			jpaCountQuery.bind(TPRDCONFIG, tPrdHierConfig.getTPrdConfig());
		}if (tPrdHierConfig.getTPrdHierLevel() == null) {
			jpaCountQuery.bind(TPRDHIERLEVEL, new TPrdHierLevel());
		} else {
			LOGGER.info("tPrdHierLevel {}", tPrdHierConfig.getTPrdHierLevel());

			jpaCountQuery.bind(TPRDHIERLEVEL, tPrdHierConfig.getTPrdHierLevel());
		}*/

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TPrdHierConfig based on given search criteria using JPA named Query.
	 * The search criteria is of TPrdConfig type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TPrdHierConfig> list of TPrdHierConfigs if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TPrdHierConfig> getTPrdHierConfigsByTPrdConfig(final SearchFilter<TPrdConfig> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());

		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTPrdHierConfigByTPrdConfig", queryParam, index, maxresult);
	}

	/**
	 * Count TPrdHierConfig based on given search criteria using JPA named Query.
	 * The search criteria is of TPrdConfig type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTPrdHierConfigsByTPrdConfig(final SearchFilter<TPrdConfig> searchFilter) {

		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());

		return genericDAO.findEntitiesByNamedQuery("CountTPrdHierConfigsByTPrdConfig", queryParam);
	}

	/**
	 * Retrieve TPrdHierConfig based on given search criteria using JPA named Query.
	 * The search criteria is of TPrdHierLevel type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TPrdHierConfig> list of TPrdHierConfigs if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TPrdHierConfig> getTPrdHierConfigsByTPrdHierLevel(final SearchFilter<TPrdHierLevel> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TPrdHierLevel tPrdHierLevel = searchFilter.getEntityClass();
		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(tPrdHierLevel.getHierLevelId());

		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO
				.findEntitiesByNamedQueryMultiCond("FindTPrdHierConfigByTPrdHierLevel", queryParam,index, maxresult);
	}

	/**
	 * Count TPrdHierConfig based on given search criteria using JPA named Query.
	 * The search criteria is of TPrdHierLevel type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTPrdHierConfigsByTPrdHierLevel(final SearchFilter<TPrdHierLevel> searchFilter) {

		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());

		return genericDAO.findEntitiesByNamedQuery("CountTPrdHierConfigsByTPrdHierLevel", queryParam);
	}

	@Override
	public List<TPrdHierConfig> FindTPrdHierConfigByHierLevelID(int hierLevelID,Short tenantId) {
		List paramList = new ArrayList();
		paramList.add(hierLevelID);
		paramList.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTPrdHierConfigByHierLevelID",paramList,0,-1);
	}

}
