package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TCvgRuleExec;
import com.cognizant.opserv.sp.core.entity.TCvgRuleSched;
import com.cognizant.opserv.sp.core.entity.TExecutionStatus;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TCvgRuleExecDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tCvgRuleExecDAO")
public class TCvgRuleExecDAOImpl implements TCvgRuleExecDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TCvgRuleExecDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private static final String TEXECUTIONSTATUS = "tExecutionStatus";
	private static final String TCVGRULESCHED = "tCvgRuleSched";

	private final Class<TCvgRuleExec> clazz;

	public Class<TCvgRuleExec> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TCvgRuleExecDAOImpl() {
		super();
		this.clazz = TCvgRuleExec.class;
	}

	private static final String JPAQL = "select tCvgRuleExecentity from TCvgRuleExec tCvgRuleExecentity";

	private static final String JPACOUNTQL = "select count(tCvgRuleExecentity) from TCvgRuleExec tCvgRuleExecentity";

	private static final String[] RESTRICTIONS = {
			"tCvgRuleExecentity.cvgRuleExecId = #{tCvgRuleExecentity.getCvgRuleExecId}",
			"Date(tCvgRuleExecentity.execDtTm) = '#{tCvgRuleExecentity.getExecDtTm}'",
			"tCvgRuleExecentity.failureReason like '#{tCvgRuleExecentity.getFailureReason}%'",
			"tCvgRuleExecentity.ruleId = #{tCvgRuleExecentity.getRuleId}",
			"tCvgRuleExecentity.createdBy = #{tCvgRuleExecentity.getCreatedBy}",
			"Date(tCvgRuleExecentity.createDt) = '#{tCvgRuleExecentity.getCreateDt}'",
			"tCvgRuleExecentity.updatedBy = #{tCvgRuleExecentity.getUpdatedBy}",
			"Date(tCvgRuleExecentity.updateDt) = '#{tCvgRuleExecentity.getUpdateDt}'",
			"tCvgRuleExecentity.tenantId = #{tCvgRuleExecentity.getTenantId}",
			"tCvgRuleExecentity.tExecutionStatus.executionStatusId = #{tCvgRuleExecentity.tExecutionStatus.getExecutionStatusId}",
			"tCvgRuleExecentity.tCvgRuleSched.txnId = #{tCvgRuleExecentity.tCvgRuleSched.getTxnId}" };

	/**
	 * Stores a new TCvgRuleExec entity object in to the persistent store
	 * 
	 * @param tCvgRuleExec
	 *            TCvgRuleExec Entity object to be persisted
	 * @return tCvgRuleExec Persisted TCvgRuleExec object
	 */
	public TCvgRuleExec createTCvgRuleExec(final TCvgRuleExec tCvgRuleExec) {
		LOGGER.info("=========== Create TCvgRuleExec ===========");
		return genericDAO.store(tCvgRuleExec);
	}

	/**
	 * Deletes a TCvgRuleExec entity object from the persistent store
	 * 
	 * @param tCvgRuleExec
	 *            TCvgRuleExec Entity object to be deleted
	 */
	public void deleteTCvgRuleExec(final Integer cvgRuleExecId) {
		LOGGER.info("=========== Delete TCvgRuleExec ===========");
		final TCvgRuleExec tCvgRuleExec = genericDAO.get(clazz, cvgRuleExecId);
		genericDAO.remove(tCvgRuleExec);
	}

	/**
	 * Updates a TCvgRuleExec entity object in to the persistent store
	 * 
	 * @param tCvgRuleExec
	 *            TCvgRuleExec Entity object to be updated
	 * @return tCvgRuleExec Persisted TCvgRuleExec object
	 */
	public TCvgRuleExec updateTCvgRuleExec(final TCvgRuleExec tCvgRuleExec) {
		LOGGER.info("=========== Update TCvgRuleExec ===========");
		return genericDAO.update(tCvgRuleExec);
	}

	/**
	 * Retrieve an TCvgRuleExec object based on given TCvgRuleExec cvgRuleExecId.
	 * 
	 * @param tCvgRuleExecId
	 *            the primary key value of the TCvgRuleExec Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TCvgRuleExec findTCvgRuleExecById(final Integer tCvgRuleExecId) {
		LOGGER.info("find TCvgRuleExec instance with cvgRuleExecId: " + tCvgRuleExecId);
		return genericDAO.get(clazz, tCvgRuleExecId);
	}

	/**
	 * Retrieve TCvgRuleExec based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCvgRuleExec> list of TCvgRuleExec if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TCvgRuleExec> findTCvgRuleExecs(final SearchFilter<TCvgRuleExec> searchFilter) {
		LOGGER.info("=========== Find TCvgRuleExecs ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TCvgRuleExec tCvgRuleExec = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tCvgRuleExecentity", tCvgRuleExec);

		if (tCvgRuleExec.getTExecutionStatus() == null) {
			jpaQuery.bind(TEXECUTIONSTATUS, new TExecutionStatus());
		} else {
			LOGGER.info("tExecutionStatus {}"+ tCvgRuleExec.getTExecutionStatus());

			jpaQuery.bind(TEXECUTIONSTATUS, tCvgRuleExec.getTExecutionStatus());
		}

		if (tCvgRuleExec.getTCvgRuleSched() == null) {
			jpaQuery.bind(TCVGRULESCHED, new TCvgRuleSched());
		} else {
			LOGGER.info("tCvgRuleSched {}"+ tCvgRuleExec.getTCvgRuleSched());

			jpaQuery.bind(TCVGRULESCHED, tCvgRuleExec.getTCvgRuleSched());
		}
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TCvgRuleExecs.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTCvgRuleExecs(final SearchFilter<TCvgRuleExec> searchFilter) {
		LOGGER.info("=========== Count TCvgRuleExec ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TCvgRuleExec tCvgRuleExec = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tCvgRuleExecentity", tCvgRuleExec);

		if (tCvgRuleExec.getTExecutionStatus() == null) {
			jpaCountQuery.bind(TEXECUTIONSTATUS, new TExecutionStatus());
		} else {
			LOGGER.info("tExecutionStatus {}" + tCvgRuleExec.getTExecutionStatus());

			jpaCountQuery.bind(TEXECUTIONSTATUS, tCvgRuleExec.getTExecutionStatus());
		}

		if (tCvgRuleExec.getTCvgRuleSched() == null) {
			jpaCountQuery.bind(TCVGRULESCHED, new TCvgRuleSched());
		} else {
			LOGGER.info("tCvgRuleSched {}" + tCvgRuleExec.getTCvgRuleSched());

			jpaCountQuery.bind(TCVGRULESCHED, tCvgRuleExec.getTCvgRuleSched());
		}

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TCvgRuleExec based on given search criteria using JPA named Query.
	 * The search criteria is of TExecutionStatus type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCvgRuleExec> list of TCvgRuleExecs if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TCvgRuleExec> getTCvgRuleExecsByTExecutionStatus(final SearchFilter<TExecutionStatus> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TExecutionStatus tExecutionStatus = searchFilter.getEntityClass();
		List<Object> tExecutionStatusList = new ArrayList<Object>();
		tExecutionStatusList.add(tExecutionStatus);
		
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTCvgRuleExecByTExecutionStatus", tExecutionStatusList, index,
				maxresult);
	}

	/**
	 * Count TCvgRuleExec based on given search criteria using JPA named Query.
	 * The search criteria is of TExecutionStatus type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTCvgRuleExecsByTExecutionStatus(final SearchFilter<TExecutionStatus> searchFilter) {

		final TExecutionStatus tExecutionStatus = searchFilter.getEntityClass();
		List<Object> tExecutionStatusList = new ArrayList<Object>();
		tExecutionStatusList.add(tExecutionStatus);
		return genericDAO.findEntitiesByNamedQuery("CountTCvgRuleExecsByTExecutionStatus", tExecutionStatusList);
	}

	/**
	 * Retrieve TCvgRuleExec based on given search criteria using JPA named Query.
	 * The search criteria is of TCvgRuleSched type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCvgRuleExec> list of TCvgRuleExecs if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TCvgRuleExec> getTCvgRuleExecsByTCvgRuleSched(final SearchFilter<TCvgRuleSched> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TCvgRuleSched tCvgRuleSched = searchFilter.getEntityClass();
		List<Object> tCvgRuleSchedList = new ArrayList<Object>();
		tCvgRuleSchedList.add(tCvgRuleSched);
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTCvgRuleExecByTCvgRuleSched", tCvgRuleSchedList, index, maxresult);
	}

	/**
	 * Count TCvgRuleExec based on given search criteria using JPA named Query.
	 * The search criteria is of TCvgRuleSched type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTCvgRuleExecsByTCvgRuleSched(final SearchFilter<TCvgRuleSched> searchFilter) {

		final TCvgRuleSched tCvgRuleSched = searchFilter.getEntityClass();
		List<Object> tCvgRuleSchedList = new ArrayList<Object>();
		tCvgRuleSchedList.add(tCvgRuleSched);
		return genericDAO.findEntitiesByNamedQuery("CountTCvgRuleExecsByTCvgRuleSched", tCvgRuleSchedList);
	}

	
	/**
	 * Retrieve list of arrayobject based on given search criteria
	 * 
	 * @param ruleId
	 *            -holds ruleId
	 * @param tenantId
	 *            -holds tenant Id
	
	 * 
	 * @return list of object of tCvgRuleExec
	 */
	public List<Object[]> getTCvgRuleExecsByOrdername(Short tenantId,Integer ruleId){
		List paramList = new ArrayList();
	
		paramList.add(tenantId);
		paramList.add(ruleId);
		
		
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTCvgRuleExecByOrdername", paramList, 0, -1);
	}

	/**
	 * Retrieve list of TCvgRuleExec based on given search criteria
	 * 
	 * @param searchFilter
	 * 
	 *            The query criteria and search filter conditions are set
	 * 
	 * 
	 * @return List<TCvgRuleExec> list of TCvgRuleExecs if it exists against
	 *         given criteria. Returns null if not found
	 * */
	public List<TCvgRuleExec> getTCvgRuleExecsByRuleAndStatus(
			SearchFilter<TCvgRuleExec> searchFilter) {
		
		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TCvgRuleExec tCvgRuleExec = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
        List queryParamList = new ArrayList();
        queryParamList.add(tCvgRuleExec.getRuleId());
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTCvgRuleExecByRuleIdAndStatus", queryParamList, index, maxresult);
	}

	/**
	 * Retrieve list of arrayobject based on given search criteria
	 * 
	 * @param ruleName
	 *            -holds ruleName
	 * @param tenantId
	 *            -holds tenant Id
	
	 * 
	 * @return list of object of tCvgRule
	 */
	public List<Object[]> FindTCvgRuleExecByRuleName(Short tenantId,String ruleName){
		List paramList = new ArrayList();
	
		paramList.add(tenantId);
		paramList.add(ruleName);
		
		
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTCvgRuleExecByRuleName", paramList, 0, -1);
	}
	/**
	 * Find t cvg rule exec dts.
	 *
	 * @param tenantId the tenant id
	 * @param txnId the txn id
	 * @param ruleId the rule id
	 * @return the list
	 */
	public List<TCvgRuleExec> findTCvgRuleExecDts(Short tenantId,Integer txnId,Integer ruleId){
		List paramList = new ArrayList();
	
		paramList.add(tenantId);
		paramList.add(txnId);
		paramList.add(ruleId);
		
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTCvgRuleExecDts", paramList, 0, -1);
	}
	/**
	 * Find t cvg rules by execution id.
	 *
	 * @param tenantId the tenant id
	 * @param orderId the order id
	 * @return the list
	 */
	@Override
	public List<Object[]> FindTCvgRulesByExecutionId(Short tenantId, int orderId) {
		// TODO Auto-generated method stub
		List paramList = new ArrayList();
		
		paramList.add(orderId);
		paramList.add(tenantId);
		
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTCvgRulesByOrderId", paramList, 0, -1);
	}

	/**
	 * Find t cvg rule by exec status id based on order id.
	 *
	 * @param tenantId the tenant id
	 * @param orderId the order id
	 * @param flag the flag
	 * @return the list
	 */
	public List<Integer[]> findTCvgRuleByExecStatusIdBasedOnOrderId(Short tenantId, int orderId,Character flag) {
		// TODO Auto-generated method stub
		List paramList = new ArrayList();
		
		paramList.add(tenantId);
		paramList.add(orderId);
		paramList.add(flag);
		paramList.add(flag);
		
		return genericDAO.findEntitiesByNamedQueryMultiCond("findTCvgRuleByExecStatusIdBasedOnOrderId", paramList, 0, -1);
	}
}
