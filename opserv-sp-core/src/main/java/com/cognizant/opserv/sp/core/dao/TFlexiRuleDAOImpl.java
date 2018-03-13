package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TAlgmntSalesTeam;
import com.cognizant.opserv.sp.core.entity.TFlexiRule;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TFlexiRuleDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tFlexiRuleDAO")
public class TFlexiRuleDAOImpl implements TFlexiRuleDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TFlexiRuleDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private static final String TALGMNTSALESTEAM = "tAlgmntSalesTeam";

	private final Class<TFlexiRule> clazz;

	public Class<TFlexiRule> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TFlexiRuleDAOImpl() {
		super();
		this.clazz = TFlexiRule.class;
	}

	private static final String JPAQL = "select tFlexiRuleentity from TFlexiRule tFlexiRuleentity";

	private static final String JPACOUNTQL = "select count(tFlexiRuleentity) from TFlexiRule tFlexiRuleentity";

	private static final String[] RESTRICTIONS = { "tFlexiRuleentity.flexiRuleId = #{tFlexiRuleentity.getFlexiRuleId}",
			"tFlexiRuleentity.ruleName like '#{tFlexiRuleentity.getRuleName}%'",
			"tFlexiRuleentity.ruleDesc like '#{tFlexiRuleentity.getRuleDesc}%'",
			"tFlexiRuleentity.activeFlag = #{tFlexiRuleentity.getActiveFlag}",
			"tFlexiRuleentity.ruleExpr like '#{tFlexiRuleentity.getRuleExpr}%'",
			"tFlexiRuleentity.createdBy = #{tFlexiRuleentity.getCreatedBy}",
			"Date(tFlexiRuleentity.createDt) = '#{tFlexiRuleentity.getCreateDt}'",
			"tFlexiRuleentity.updatedBy = #{tFlexiRuleentity.getUpdatedBy}",
			"Date(tFlexiRuleentity.updateDt) = '#{tFlexiRuleentity.getUpdateDt}'",
			"tFlexiRuleentity.tenantId = #{tFlexiRuleentity.getTenantId}",
			"tFlexiRuleentity.tAlgmntSalesTeam.tAlgmntSalesTeamId = #{tFlexiRuleentity.tAlgmntSalesTeam.getTAlgmntSalesTeamId}" };

	/**
	 * Stores a new TFlexiRule entity object in to the persistent store
	 * 
	 * @param tFlexiRule
	 *            TFlexiRule Entity object to be persisted
	 * @return tFlexiRule Persisted TFlexiRule object
	 */
	public TFlexiRule createTFlexiRule(final TFlexiRule tFlexiRule) {
		LOGGER.info("=========== Create TFlexiRule ===========");
		return genericDAO.store(tFlexiRule);
	}

	/**
	 * Deletes a TFlexiRule entity object from the persistent store
	 * 
	 * @param tFlexiRule
	 *            TFlexiRule Entity object to be deleted
	 */
	public void deleteTFlexiRule(final Integer flexiRuleId) {
		LOGGER.info("=========== Delete TFlexiRule ===========");
		final TFlexiRule tFlexiRule = genericDAO.get(clazz, flexiRuleId);
		genericDAO.remove(tFlexiRule);
	}

	/**
	 * Updates a TFlexiRule entity object in to the persistent store
	 * 
	 * @param tFlexiRule
	 *            TFlexiRule Entity object to be updated
	 * @return tFlexiRule Persisted TFlexiRule object
	 */
	public TFlexiRule updateTFlexiRule(final TFlexiRule tFlexiRule) {
		LOGGER.info("=========== Update TFlexiRule ===========");
		return genericDAO.update(tFlexiRule);
	}

	/**
	 * Retrieve an TFlexiRule object based on given TFlexiRule flexiRuleId.
	 * 
	 * @param tFlexiRuleId
	 *            the primary key value of the TFlexiRule Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TFlexiRule findTFlexiRuleById(final Integer tFlexiRuleId) {
		LOGGER.info("find TFlexiRule instance with flexiRuleId: " + tFlexiRuleId);
		return genericDAO.get(clazz, tFlexiRuleId);
	}

	/**
	 * Retrieve TFlexiRule based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TFlexiRule> list of TFlexiRule if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TFlexiRule> findTFlexiRules(final SearchFilter<TFlexiRule> searchFilter) {
		LOGGER.info("=========== Find TFlexiRules ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TFlexiRule tFlexiRule = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tFlexiRuleentity", tFlexiRule);

		if (tFlexiRule.getTAlgmntSalesTeam() == null) {
			jpaQuery.bind(TALGMNTSALESTEAM, new TAlgmntSalesTeam());
		} else {
			LOGGER.info("tAlgmntSalesTeam {}" + tFlexiRule.getTAlgmntSalesTeam());

			jpaQuery.bind(TALGMNTSALESTEAM, tFlexiRule.getTAlgmntSalesTeam());
		}
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TFlexiRules.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTFlexiRules(final SearchFilter<TFlexiRule> searchFilter) {
		LOGGER.info("=========== Count TFlexiRule ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TFlexiRule tFlexiRule = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tFlexiRuleentity", tFlexiRule);

		if (tFlexiRule.getTAlgmntSalesTeam() == null) {
			jpaCountQuery.bind(TALGMNTSALESTEAM, new TAlgmntSalesTeam());
		} else {
			LOGGER.info("tAlgmntSalesTeam {}" + tFlexiRule.getTAlgmntSalesTeam());

			jpaCountQuery.bind(TALGMNTSALESTEAM, tFlexiRule.getTAlgmntSalesTeam());
		}

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TFlexiRule based on given search criteria using JPA named Query.
	 * The search criteria is of TAlgmntSalesTeam type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TFlexiRule> list of TFlexiRules if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TFlexiRule> getTFlexiRulesByTAlgmntSalesTeam(final SearchFilter<TAlgmntSalesTeam> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TAlgmntSalesTeam tAlgmntSalesTeam = searchFilter.getEntityClass();
		List<Object> tAlgmntSalesTeamList = new ArrayList<Object>();
		tAlgmntSalesTeamList.add(tAlgmntSalesTeam);
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTFlexiRuleByTAlgmntSalesTeam", tAlgmntSalesTeamList, index,
				maxresult);
	}

	/**
	 * Count TFlexiRule based on given search criteria using JPA named Query.
	 * The search criteria is of TAlgmntSalesTeam type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTFlexiRulesByTAlgmntSalesTeam(final SearchFilter<TAlgmntSalesTeam> searchFilter) {

		final TAlgmntSalesTeam tAlgmntSalesTeam = searchFilter.getEntityClass();
		List<Object> tAlgmntSalesTeamList = new ArrayList<Object>();
		tAlgmntSalesTeamList.add(tAlgmntSalesTeam);
		return genericDAO.findEntitiesByNamedQuery("CountTFlexiRulesByTAlgmntSalesTeam", tAlgmntSalesTeamList);
	}

}
