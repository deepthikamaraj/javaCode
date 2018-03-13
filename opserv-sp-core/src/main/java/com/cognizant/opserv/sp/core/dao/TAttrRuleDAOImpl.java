package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TAttrDef;
import com.cognizant.opserv.sp.core.entity.TAttrRule;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TAttrRuleDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tAttrRuleDAO")
public class TAttrRuleDAOImpl implements TAttrRuleDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TAttrRuleDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	//private static final String TATTR = "tAttr";

	private final Class<TAttrRule> clazz;

	public Class<TAttrRule> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TAttrRuleDAOImpl() {
		super();
		this.clazz = TAttrRule.class;
	}

	private static final String JPAQL = "select tAttrRuleentity from TAttrRule tAttrRuleentity";

	private static final String JPACOUNTQL = "select count(tAttrRuleentity) from TAttrRule tAttrRuleentity";

	private static final String[] RESTRICTIONS = { "tAttrRuleentity.ruleId = #{tAttrRuleentity.getRuleId}",
			"tAttrRuleentity.minValue like '#{tAttrRuleentity.getMinValue}%'",
			"tAttrRuleentity.maxValue like '#{tAttrRuleentity.getMaxValue}%'",
			"tAttrRuleentity.ruleExpr like '#{tAttrRuleentity.getRuleExpr}%'",
			"tAttrRuleentity.valTypeId = #{tAttrRuleentity.getValTypeId}",
			"tAttrRuleentity.createdBy = #{tAttrRuleentity.getCreatedBy}",
			"Date(tAttrRuleentity.createDt) = '#{tAttrRuleentity.getCreateDt}'",
			"tAttrRuleentity.updatedBy = #{tAttrRuleentity.getUpdatedBy}",
			"Date(tAttrRuleentity.updateDt) = '#{tAttrRuleentity.getUpdateDt}'",
			"tAttrRuleentity.tenantId = #{tAttrRuleentity.getTenantId}",
			"tAttrRuleentity.activeFlag = #{tAttrRuleentity.getActiveFlag}",
			"tAttrRuleentity.tAttr.attrId = #{tAttrRuleentity.tAttr.getAttrId}" };

	/**
	 * Stores a new TAttrRule entity object in to the persistent store
	 * 
	 * @param tAttrRule
	 *            TAttrRule Entity object to be persisted
	 * @return tAttrRule Persisted TAttrRule object
	 */
	public TAttrRule createTAttrRule(final TAttrRule tAttrRule) {
		LOGGER.info("=========== Create TAttrRule ===========");
		return genericDAO.store(tAttrRule);
	}

	/**
	 * Deletes a TAttrRule entity object from the persistent store
	 * 
	 * @param tAttrRule
	 *            TAttrRule Entity object to be deleted
	 */
	public void deleteTAttrRule(final Integer ruleId) {
		LOGGER.info("=========== Delete TAttrRule ===========");
		final TAttrRule tAttrRule = genericDAO.get(clazz, ruleId);
		genericDAO.remove(tAttrRule);
	}

	/**
	 * Updates a TAttrRule entity object in to the persistent store
	 * 
	 * @param tAttrRule
	 *            TAttrRule Entity object to be updated
	 * @return tAttrRule Persisted TAttrRule object
	 */
	public TAttrRule updateTAttrRule(final TAttrRule tAttrRule) {
		LOGGER.info("=========== Update TAttrRule ===========");
		return genericDAO.update(tAttrRule);
	}

	/**
	 * Retrieve an TAttrRule object based on given TAttrRule ruleId.
	 * 
	 * @param tAttrRuleId
	 *            the primary key value of the TAttrRule Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TAttrRule findTAttrRuleById(final Integer tAttrRuleId) {
		LOGGER.info("find TAttrRule instance with ruleId: " + tAttrRuleId);
		return genericDAO.get(clazz, tAttrRuleId);
	}

	/**
	 * Retrieve TAttrRule based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TAttrRule> list of TAttrRule if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TAttrRule> findTAttrRules(final SearchFilter<TAttrRule> searchFilter) {
		LOGGER.info("=========== Find TAttrRules ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TAttrRule tAttrRule = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tAttrRuleentity", tAttrRule);


		/*if (tAttrRule.getTAttr() == null) {

		if (tAttrRule.getTAttrDef() == null) {

			jpaQuery.bind(TATTR, new TAttr());
		} else {
			LOGGER.info("tAttr {}", tAttrRule.getTAttrDef());

			jpaQuery.bind(TATTR, tAttrRule.getTAttrDef());
		}*/
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TAttrRules.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTAttrRules(final SearchFilter<TAttrRule> searchFilter) {
		LOGGER.info("=========== Count TAttrRule ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TAttrRule tAttrRule = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tAttrRuleentity", tAttrRule);
/*
		if (tAttrRule.getTAttrDef() == null) {
			jpaCountQuery.bind(TATTR, new TAttr());
		} else {
			LOGGER.info("tAttr {}", tAttrRule.getTAttrDef());

			jpaCountQuery.bind(TATTR, tAttrRule.getTAttrDef());
		}*/

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TAttrRule based on given search criteria using JPA named Query.
	 * The search criteria is of TAttr type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TAttrRule> list of TAttrRules if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TAttrRule> getTAttrRulesByTAttrDef(final SearchFilter<TAttrDef> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TAttrDef tAttrDef = searchFilter.getEntityClass();
		List<Object> tAttrDefList = new ArrayList<Object>();
		tAttrDefList.add(tAttrDef);
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTAttrRuleByTAttrDef", tAttrDefList, index, maxresult);
	}

	/**
	 * Count TAttrRule based on given search criteria using JPA named Query.
	 * The search criteria is of TAttr type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTAttrRulesByTAttrDef(final SearchFilter<TAttrDef> searchFilter) {

		final TAttrDef tAttrDef = searchFilter.getEntityClass();
		List<Object> tAttrDefList = new ArrayList<Object>();
		tAttrDefList.add(tAttrDef);
		return genericDAO.findEntitiesByNamedQuery("CountTAttrRulesByTAttrDef", tAttrDefList);
	}

}
