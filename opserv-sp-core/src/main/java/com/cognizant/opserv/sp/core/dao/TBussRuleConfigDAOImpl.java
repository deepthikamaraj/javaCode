package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.common.ApplicationConstant;
import com.cognizant.opserv.sp.core.entity.TBussRuleConfig;
import com.cognizant.opserv.sp.core.entity.TBussRuleConfigId;
import com.cognizant.opserv.sp.core.entity.TOrg;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;
/**
 * Class provides API implementation for TBussRuleConfigDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tBussRuleConfigDAO")
public class TBussRuleConfigDAOImpl implements TBussRuleConfigDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TBussRuleConfigDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private static final String TORG = "tOrg";

	private final Class<TBussRuleConfig> clazz;

	public Class<TBussRuleConfig> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TBussRuleConfigDAOImpl() {
		super();
		this.clazz = TBussRuleConfig.class;
	}

	private static final String JPAQL = "select tBussRuleConfigentity from TBussRuleConfig tBussRuleConfigentity";

	private static final String JPACOUNTQL = "select count(*) from TBussRuleConfig tBussRuleConfigentity";

	private static final String[] RESTRICTIONS = {
			"tBussRuleConfigentity.tBussRuleConfigId.orgId = #{tBussRuleConfigentity.tBussRuleConfigId.getOrgId}",
			"tBussRuleConfigentity.tBussRuleConfigId.bussRuleConfigId = #{tBussRuleConfigentity.tBussRuleConfigId.getBussRuleConfigId}",
			"tBussRuleConfigentity.ruleDesc like '#{tBussRuleConfigentity.getRuleDesc}%'",
			"tBussRuleConfigentity.activeFlag = #{tBussRuleConfigentity.getActiveFlag}",
			"tBussRuleConfigentity.ruleName like '#{tBussRuleConfigentity.getRuleName}%'",
			"tBussRuleConfigentity.allowedValue like '#{tBussRuleConfigentity.getAllowedValue}%'",
			"tBussRuleConfigentity.tOrg.orgId = #{tBussRuleConfigentity.tOrg.getOrgId}" };

	/**
	 * Stores a new TBussRuleConfig entity object in to the persistent store
	 * 
	 * @param tBussRuleConfig
	 *            TBussRuleConfig Entity object to be persisted
	 * @return tBussRuleConfig Persisted TBussRuleConfig object
	 */
	public TBussRuleConfig createTBussRuleConfig(final TBussRuleConfig tBussRuleConfig) {
		LOGGER.info("=========== Create TBussRuleConfig ===========");
		return genericDAO.store(tBussRuleConfig);
	}

	/**
	 * Deletes a TBussRuleConfig entity object from the persistent store
	 * 
	 * @param tBussRuleConfig
	 *            TBussRuleConfig Entity object to be deleted
	 */
	public void deleteTBussRuleConfig(final TBussRuleConfigId tBussRuleConfigId) {
		LOGGER.info("=========== Delete TBussRuleConfig ===========");
		final TBussRuleConfig tBussRuleConfig = genericDAO.get(clazz, tBussRuleConfigId);
		genericDAO.remove(tBussRuleConfig);
	}

	/**
	 * Updates a TBussRuleConfig entity object in to the persistent store
	 * 
	 * @param tBussRuleConfig
	 *            TBussRuleConfig Entity object to be updated
	 * @return tBussRuleConfig Persisted TBussRuleConfig object
	 */
	public TBussRuleConfig updateTBussRuleConfig(final TBussRuleConfig tBussRuleConfig) {
		LOGGER.info("=========== Update TBussRuleConfig ===========");
		return genericDAO.update(tBussRuleConfig);
	}

	/**
	 * Retrieve an TBussRuleConfig object based on given TBussRuleConfig TBussRuleConfigId.
	 * 
	 * @param tBussRuleConfigId
	 *            the primary key value of the TBussRuleConfig Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TBussRuleConfig findTBussRuleConfigById(final TBussRuleConfigId tBussRuleConfigId) {
		LOGGER.info("find TBussRuleConfig instance with TBussRuleConfigId: " + tBussRuleConfigId);
		return genericDAO.get(clazz, tBussRuleConfigId);
	}

	/**
	 * Retrieve TBussRuleConfig based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TBussRuleConfig> list of TBussRuleConfig if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TBussRuleConfig> findTBussRuleConfigs(final SearchFilter<TBussRuleConfig> searchFilter) {
		LOGGER.info("=========== Find TBussRuleConfigs ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TBussRuleConfig tBussRuleConfig = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tBussRuleConfigentity", tBussRuleConfig);

		if (tBussRuleConfig.getTOrg() == null) {
			jpaQuery.bind(TORG, new TOrg());
		} else {
			LOGGER.info("tOrg {}"+ tBussRuleConfig.getTOrg());

			jpaQuery.bind(TORG, tBussRuleConfig.getTOrg());
		}
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TBussRuleConfigs.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTBussRuleConfigs(final SearchFilter<TBussRuleConfig> searchFilter) {
		LOGGER.info("=========== Count TBussRuleConfig ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TBussRuleConfig tBussRuleConfig = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tBussRuleConfigentity", tBussRuleConfig);

		if (tBussRuleConfig.getTOrg() == null) {
			jpaCountQuery.bind(TORG, new TOrg());
		} else {
			LOGGER.info("tOrg {}"+ tBussRuleConfig.getTOrg());

			jpaCountQuery.bind(TORG, tBussRuleConfig.getTOrg());
		}

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TBussRuleConfig based on given search criteria using JPA named Query.
	 * The search criteria is of TOrg type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TBussRuleConfig> list of TBussRuleConfigs if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TBussRuleConfig> getTBussRuleConfigsByTOrg(final SearchFilter<TOrg> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TOrg tOrg = searchFilter.getEntityClass();
		List<Object> tOrgList = new ArrayList<Object>();
		tOrgList.add(tOrg);
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTBussRuleConfigByTOrg", tOrgList, index, maxresult);
	}

	/**
	 * Count TBussRuleConfig based on given search criteria using JPA named Query.
	 * The search criteria is of TOrg type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTBussRuleConfigsByTOrg(final SearchFilter<TOrg> searchFilter) {

		final TOrg tOrg = searchFilter.getEntityClass();
		List<Object> tOrgList = new ArrayList<Object>();
		tOrgList.add(tOrg);
		return genericDAO.findEntitiesByNamedQuery("CountTBussRuleConfigsByTOrg", tOrgList);
	}
	/**
	 * Gets the t buss rule configs by rule name.
	 *
	 * @return the t buss rule configs by rule name
	 */
	@Override
	public  List<TBussRuleConfig> getTBussRuleConfigsByRuleName() {	
		List<Object> tBussFunctionList = new ArrayList<Object>();
		tBussFunctionList.add("Enable Contiguity Check");
		return genericDAO.findEntitiesByNamedQuery("FindTBussRuleConfigByRuleName", tBussFunctionList);
	}
	/**
	 * Gets the all t buss rule configs.
	 *
	 * @return the all t buss rule configs
	 */
	@Override
	public  List<TBussRuleConfig> getAllTBussRuleConfigs(){
		final List<Object> paramList = new ArrayList<Object>();
		paramList.add(ApplicationConstant.FLAG_YES);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindAllTBussRuleConfigsByFlag", paramList, 0, -1);
	}
}
