package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TAttrRule;
import com.cognizant.opserv.sp.core.entity.TValMsg;
import com.cognizant.opserv.sp.core.entity.TValMsgId;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TValMsgDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tValMsgDAO")
public class TValMsgDAOImpl implements TValMsgDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TValMsgDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private static final String TATTRRULE = "tAttrRule";

	private final Class<TValMsg> clazz;

	public Class<TValMsg> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TValMsgDAOImpl() {
		super();
		this.clazz = TValMsg.class;
	}

	private static final String JPAQL = "select tValMsgentity from TValMsg tValMsgentity";

	private static final String JPACOUNTQL = "select count(*) from TValMsg tValMsgentity";

	private static final String[] RESTRICTIONS = {
			"tValMsgentity.tValMsgId.ruleId = #{tValMsgentity.tValMsgId.getRuleId}",
			"tValMsgentity.tValMsgId.localeId like '#{tValMsgentity.tValMsgId.getLocaleId}%'",
			"tValMsgentity.errMsg like '#{tValMsgentity.getErrMsg}%'",
			"tValMsgentity.createdBy = #{tValMsgentity.getCreatedBy}",
			"Date(tValMsgentity.createDt) = '#{tValMsgentity.getCreateDt}'",
			"tValMsgentity.updatedBy = #{tValMsgentity.getUpdatedBy}",
			"Date(tValMsgentity.updateDt) = '#{tValMsgentity.getUpdateDt}'",
			"tValMsgentity.tenantId = #{tValMsgentity.getTenantId}",
			"tValMsgentity.tAttrRule.ruleId = #{tValMsgentity.tAttrRule.getRuleId}" };

	/**
	 * Stores a new TValMsg entity object in to the persistent store
	 * 
	 * @param tValMsg
	 *            TValMsg Entity object to be persisted
	 * @return tValMsg Persisted TValMsg object
	 */
	public TValMsg createTValMsg(final TValMsg tValMsg) {
		LOGGER.info("=========== Create TValMsg ===========");
		return genericDAO.store(tValMsg);
	}

	/**
	 * Deletes a TValMsg entity object from the persistent store
	 * 
	 * @param tValMsg
	 *            TValMsg Entity object to be deleted
	 */
	public void deleteTValMsg(final TValMsgId tValMsgId) {
		LOGGER.info("=========== Delete TValMsg ===========");
		final TValMsg tValMsg = genericDAO.get(clazz, tValMsgId);
		genericDAO.remove(tValMsg);
	}

	/**
	 * Updates a TValMsg entity object in to the persistent store
	 * 
	 * @param tValMsg
	 *            TValMsg Entity object to be updated
	 * @return tValMsg Persisted TValMsg object
	 */
	public TValMsg updateTValMsg(final TValMsg tValMsg) {
		LOGGER.info("=========== Update TValMsg ===========");
		return genericDAO.update(tValMsg);
	}

	/**
	 * Retrieve an TValMsg object based on given TValMsg TValMsgId.
	 * 
	 * @param tValMsgId
	 *            the primary key value of the TValMsg Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TValMsg findTValMsgById(final TValMsgId tValMsgId) {
		LOGGER.info("find TValMsg instance with TValMsgId: " + tValMsgId);
		return genericDAO.get(clazz, tValMsgId);
	}

	/**
	 * Retrieve TValMsg based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TValMsg> list of TValMsg if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TValMsg> findTValMsgs(final SearchFilter<TValMsg> searchFilter) {
		LOGGER.info("=========== Find TValMsgs ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TValMsg tValMsg = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tValMsgentity", tValMsg);

		if (tValMsg.getTAttrRule() == null) {
			jpaQuery.bind(TATTRRULE, new TAttrRule());
		} else {
			LOGGER.info("tAttrRule {}"+ tValMsg.getTAttrRule());

			jpaQuery.bind(TATTRRULE, tValMsg.getTAttrRule());
		}
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TValMsgs.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTValMsgs(final SearchFilter<TValMsg> searchFilter) {
		LOGGER.info("=========== Count TValMsg ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TValMsg tValMsg = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tValMsgentity", tValMsg);

		if (tValMsg.getTAttrRule() == null) {
			jpaCountQuery.bind(TATTRRULE, new TAttrRule());
		} else {
			LOGGER.info("tAttrRule {}"+ tValMsg.getTAttrRule());

			jpaCountQuery.bind(TATTRRULE, tValMsg.getTAttrRule());
		}

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TValMsg based on given search criteria using JPA named Query.
	 * The search criteria is of TAttrRule type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TValMsg> list of TValMsgs if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TValMsg> getTValMsgsByTAttrRule(final SearchFilter<TAttrRule> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTValMsgByTAttrRule", queryParam, index, maxresult);
	}

	/**
	 * Count TValMsg based on given search criteria using JPA named Query.
	 * The search criteria is of TAttrRule type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTValMsgsByTAttrRule(final SearchFilter<TAttrRule> searchFilter) {
		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());
		return genericDAO.findEntitiesByNamedQuery("CountTValMsgsByTAttrRule", queryParam);
	}

}
