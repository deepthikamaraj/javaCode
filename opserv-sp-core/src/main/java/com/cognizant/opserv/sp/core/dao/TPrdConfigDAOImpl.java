package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TPrdConfig;
import com.cognizant.opserv.sp.core.entity.TPrdHierType;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TPrdConfigDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tPrdConfigDAO")
public class TPrdConfigDAOImpl implements TPrdConfigDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TPrdConfigDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	//private static final String TPRDHIERTYPE = "tPrdHierType";

	private final Class<TPrdConfig> clazz;

	public Class<TPrdConfig> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TPrdConfigDAOImpl() {
		super();
		this.clazz = TPrdConfig.class;
	}

	private static final String JPAQL = "select tPrdConfigentity from TPrdConfig tPrdConfigentity";

	private static final String JPACOUNTQL = "select count(tPrdConfigentity) from TPrdConfig tPrdConfigentity";

	private static final String[] RESTRICTIONS = { "tPrdConfigentity.prdConfigId = #{tPrdConfigentity.getPrdConfigId}",
			"tPrdConfigentity.prdConfigName like '#{tPrdConfigentity.getPrdConfigName}%'",
			"tPrdConfigentity.prdConfigDesc like '#{tPrdConfigentity.getPrdConfigDesc}%'",
			"tPrdConfigentity.activeFlag = #{tPrdConfigentity.getActiveFlag}",
			"tPrdConfigentity.createdBy = #{tPrdConfigentity.getCreatedBy}",
			"Date(tPrdConfigentity.createDt) = '#{tPrdConfigentity.getCreateDt}'",
			"tPrdConfigentity.updatedBy = #{tPrdConfigentity.getUpdatedBy}",
			"Date(tPrdConfigentity.updateDt) = '#{tPrdConfigentity.getUpdateDt}'",
			"tPrdConfigentity.tenantId = #{tPrdConfigentity.getTenantId}",
			"tPrdConfigentity.hierTypeId = #{tPrdConfigentity.getHierTypeId}",
			"tPrdConfigentity.orgId = #{tPrdConfigentity.getOrgId}"};

	/**
	 * Stores a new TPrdConfig entity object in to the persistent store
	 * 
	 * @param tPrdConfig
	 *            TPrdConfig Entity object to be persisted
	 * @return tPrdConfig Persisted TPrdConfig object
	 */
	public TPrdConfig createTPrdConfig(final TPrdConfig tPrdConfig) {
		LOGGER.info("=========== Create TPrdConfig ===========");
		return genericDAO.store(tPrdConfig);
	}

	/**
	 * Deletes a TPrdConfig entity object from the persistent store
	 * 
	 * @param tPrdConfig
	 *            TPrdConfig Entity object to be deleted
	 */
	public void deleteTPrdConfig(final Integer prdConfigId) {
		LOGGER.info("=========== Delete TPrdConfig ===========");
		final TPrdConfig tPrdConfig = genericDAO.get(clazz, prdConfigId);
		genericDAO.remove(tPrdConfig);
	}

	/**
	 * Updates a TPrdConfig entity object in to the persistent store
	 * 
	 * @param tPrdConfig
	 *            TPrdConfig Entity object to be updated
	 * @return tPrdConfig Persisted TPrdConfig object
	 */
	public TPrdConfig updateTPrdConfig(final TPrdConfig tPrdConfig) {
		LOGGER.info("=========== Update TPrdConfig ===========");
		return genericDAO.update(tPrdConfig);
	}

	/**
	 * Retrieve an TPrdConfig object based on given TPrdConfig prdConfigId.
	 * 
	 * @param tPrdConfigId
	 *            the primary key value of the TPrdConfig Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TPrdConfig findTPrdConfigById(final Integer tPrdConfigId) {
		LOGGER.info("find TPrdConfig instance with prdConfigId: " + tPrdConfigId);
		return genericDAO.get(clazz, tPrdConfigId);
	}

	/**
	 * Retrieve TPrdConfig based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TPrdConfig> list of TPrdConfig if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TPrdConfig> findTPrdConfigs(final SearchFilter<TPrdConfig> searchFilter) {
		LOGGER.info("=========== Find TPrdConfigs ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TPrdConfig tPrdConfig = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tPrdConfigentity", tPrdConfig);

		/*if (tPrdConfig.getTPrdHierType() == null) {
			jpaQuery.bind(TPRDHIERTYPE, new TPrdHierType());
		} else {
			LOGGER.info("tPrdHierType {}", tPrdConfig.getTPrdHierType());

			jpaQuery.bind(TPRDHIERTYPE, tPrdConfig.getTPrdHierType());
		}*/
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TPrdConfigs.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTPrdConfigs(final SearchFilter<TPrdConfig> searchFilter) {
		LOGGER.info("=========== Count TPrdConfig ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TPrdConfig tPrdConfig = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tPrdConfigentity", tPrdConfig);

		/*if (tPrdConfig.getTPrdHierType() == null) {
			jpaCountQuery.bind(TPRDHIERTYPE, new TPrdHierType());
		} else {
			LOGGER.info("tPrdHierType {}", tPrdConfig.getTPrdHierType());

			jpaCountQuery.bind(TPRDHIERTYPE, tPrdConfig.getTPrdHierType());
		}*/

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TPrdConfig based on given search criteria using JPA named Query.
	 * The search criteria is of TPrdHierType type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TPrdConfig> list of TPrdConfigs if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TPrdConfig> getTPrdConfigsByTPrdHierType(final SearchFilter<TPrdHierType> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTPrdConfigByTPrdHierType", queryParam, index, maxresult);
	}

	/**
	 * Count TPrdConfig based on given search criteria using JPA named Query.
	 * The search criteria is of TPrdHierType type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTPrdConfigsByTPrdHierType(final SearchFilter<TPrdHierType> searchFilter) {

		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());
		return genericDAO.findEntitiesByNamedQuery("CountTPrdConfigsByTPrdHierType", queryParam);
	}

}
