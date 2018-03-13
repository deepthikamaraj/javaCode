package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TCommConfig;
import com.cognizant.opserv.sp.core.entity.TCommType;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TCommConfigDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tCommConfigDAO")
public class TCommConfigDAOImpl implements TCommConfigDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TCommConfigDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private static final String TCOMMTYPE = "tCommType";

	private final Class<TCommConfig> clazz;

	public Class<TCommConfig> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TCommConfigDAOImpl() {
		super();
		this.clazz = TCommConfig.class;
	}

	private static final String JPAQL = "select tCommConfigentity from TCommConfig tCommConfigentity";

	private static final String JPACOUNTQL = "select count(tCommConfigentity) from TCommConfig tCommConfigentity";

	private static final String[] RESTRICTIONS = { "tCommConfigentity.configId = #{tCommConfigentity.getConfigId}",
			"tCommConfigentity.itemDisplayCount = #{tCommConfigentity.getItemDisplayCount}",
			"tCommConfigentity.activeFlag = #{tCommConfigentity.getActiveFlag}",
			"tCommConfigentity.createdBy = #{tCommConfigentity.getCreatedBy}",
			"Date(tCommConfigentity.createDt) = '#{tCommConfigentity.getCreateDt}'",
			"tCommConfigentity.updatedBy = #{tCommConfigentity.getUpdatedBy}",
			"Date(tCommConfigentity.updateDt) = '#{tCommConfigentity.getUpdateDt}'",
			"tCommConfigentity.tenantId = #{tCommConfigentity.getTenantId}",
			"tCommConfigentity.maxAttchSz like '#{tCommConfigentity.getMaxAttchSz}%'",
			"tCommConfigentity.fileFormat like '#{tCommConfigentity.getFileFormat}%'",
			"tCommConfigentity.maxAttchNum = #{tCommConfigentity.getMaxAttchNum}",
			"tCustContactentity.contactExtn like '#{tCustContactentity.getContactExtn}%'",
			"tCommConfigentity.tCommType.commTypeId = #{tCommConfigentity.tCommType.getCommTypeId}" };

	/**
	 * Stores a new TCommConfig entity object in to the persistent store
	 * 
	 * @param tCommConfig
	 *            TCommConfig Entity object to be persisted
	 * @return tCommConfig Persisted TCommConfig object
	 */
	public TCommConfig createTCommConfig(final TCommConfig tCommConfig) {
		LOGGER.info("=========== Create TCommConfig ===========");
		return genericDAO.store(tCommConfig);
	}

	/**
	 * Deletes a TCommConfig entity object from the persistent store
	 * 
	 * @param tCommConfig
	 *            TCommConfig Entity object to be deleted
	 */
	public void deleteTCommConfig(final Integer configId) {
		LOGGER.info("=========== Delete TCommConfig ===========");
		final TCommConfig tCommConfig = genericDAO.get(clazz, configId);
		genericDAO.remove(tCommConfig);
	}

	/**
	 * Updates a TCommConfig entity object in to the persistent store
	 * 
	 * @param tCommConfig
	 *            TCommConfig Entity object to be updated
	 * @return tCommConfig Persisted TCommConfig object
	 */
	public TCommConfig updateTCommConfig(final TCommConfig tCommConfig) {
		LOGGER.info("=========== Update TCommConfig ===========");
		return genericDAO.update(tCommConfig);
	}

	/**
	 * Retrieve an TCommConfig object based on given TCommConfig configId.
	 * 
	 * @param tCommConfigId
	 *            the primary key value of the TCommConfig Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TCommConfig findTCommConfigById(final Integer tCommConfigId) {
		LOGGER.info("find TCommConfig instance with configId: " + tCommConfigId);
		return genericDAO.get(clazz, tCommConfigId);
	}

	/**
	 * Retrieve TCommConfig based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCommConfig> list of TCommConfig if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TCommConfig> findTCommConfigs(final SearchFilter<TCommConfig> searchFilter) {
		LOGGER.info("=========== Find TCommConfigs ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TCommConfig tCommConfig = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tCommConfigentity", tCommConfig);

		if (tCommConfig.getTCommType() == null) {
			jpaQuery.bind(TCOMMTYPE, new TCommType());
		} else {
			LOGGER.info("tCommType {}" + tCommConfig.getTCommType());

			jpaQuery.bind(TCOMMTYPE, tCommConfig.getTCommType());
		}
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TCommConfigs.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTCommConfigs(final SearchFilter<TCommConfig> searchFilter) {
		LOGGER.info("=========== Count TCommConfig ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TCommConfig tCommConfig = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tCommConfigentity", tCommConfig);

		if (tCommConfig.getTCommType() == null) {
			jpaCountQuery.bind(TCOMMTYPE, new TCommType());
		} else {
			LOGGER.info("tCommType {}" + tCommConfig.getTCommType());

			jpaCountQuery.bind(TCOMMTYPE, tCommConfig.getTCommType());
		}

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TCommConfig based on given search criteria using JPA named Query.
	 * The search criteria is of TCommType type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCommConfig> list of TCommConfigs if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TCommConfig> getTCommConfigsByTCommType(final SearchFilter<TCommType> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TCommType tCommType = searchFilter.getEntityClass();
		List<Object> tCommTypeList = new ArrayList<Object>();
		tCommTypeList.add(tCommType);
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTCommConfigByTCommType", tCommTypeList, index, maxresult);
	}

	/**
	 * Count TCommConfig based on given search criteria using JPA named Query.
	 * The search criteria is of TCommType type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTCommConfigsByTCommType(final SearchFilter<TCommType> searchFilter) {

		final TCommType tCommType = searchFilter.getEntityClass();
		List<Object> tCommTypeList = new ArrayList<Object>();
		tCommTypeList.add(tCommType);
		return genericDAO.findEntitiesByNamedQuery("CountTCommConfigsByTCommType", tCommTypeList);
	}

}
