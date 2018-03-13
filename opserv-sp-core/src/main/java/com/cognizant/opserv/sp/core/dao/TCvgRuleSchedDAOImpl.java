package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TCvgRuleOrder;
import com.cognizant.opserv.sp.core.entity.TCvgRuleSched;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;
import com.cognizant.opserv.sp.common.util.DateUtil;

/**
 * Class provides API implementation for TCvgRuleSchedDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tCvgRuleSchedDAO")
public class TCvgRuleSchedDAOImpl implements TCvgRuleSchedDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TCvgRuleSchedDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private static final String TCVGRULEORDER = "tCvgRuleOrder";

	private final Class<TCvgRuleSched> clazz;

	public Class<TCvgRuleSched> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TCvgRuleSchedDAOImpl() {
		super();
		this.clazz = TCvgRuleSched.class;
	}

	private static final String JPAQL = "select tCvgRuleSchedentity from TCvgRuleSched tCvgRuleSchedentity";

	private static final String JPACOUNTQL = "select count(tCvgRuleSchedentity) from TCvgRuleSched tCvgRuleSchedentity";

	private static final String[] RESTRICTIONS = { "tCvgRuleSchedentity.txnId = #{tCvgRuleSchedentity.getTxnId}",
			"tCvgRuleSchedentity.salesHierId = #{tCvgRuleSchedentity.getSalesHierId}",
			"tCvgRuleSchedentity.salesPosId = #{tCvgRuleSchedentity.getSalesPosId}",
			"tCvgRuleSchedentity.createdBy = #{tCvgRuleSchedentity.getCreatedBy}",
			"Date(tCvgRuleSchedentity.createDt) = '#{tCvgRuleSchedentity.getCreateDt}'",
			"tCvgRuleSchedentity.updatedBy = #{tCvgRuleSchedentity.getUpdatedBy}",
			"Date(tCvgRuleSchedentity.updateDt) = '#{tCvgRuleSchedentity.getUpdateDt}'",
			"tCvgRuleSchedentity.tenantId = #{tCvgRuleSchedentity.getTenantId}",
			"tCvgRuleSchedentity.activeFlag = #{tCvgRuleSchedentity.getActiveFlag}",
			"tCvgRuleSchedentity.tCvgRuleOrder.orderId = #{tCvgRuleSchedentity.tCvgRuleOrder.getOrderId}" };

	/**
	 * Stores a new TCvgRuleSched entity object in to the persistent store
	 * 
	 * @param tCvgRuleSched
	 *            TCvgRuleSched Entity object to be persisted
	 * @return tCvgRuleSched Persisted TCvgRuleSched object
	 */
	public TCvgRuleSched createTCvgRuleSched(final TCvgRuleSched tCvgRuleSched) {
		LOGGER.info("=========== Create TCvgRuleSched ===========");
		return genericDAO.store(tCvgRuleSched);
	}

	/**
	 * Deletes a TCvgRuleSched entity object from the persistent store
	 * 
	 * @param tCvgRuleSched
	 *            TCvgRuleSched Entity object to be deleted
	 */
	public void deleteTCvgRuleSched(final Integer txnId) {
		LOGGER.info("=========== Delete TCvgRuleSched ===========");
		final TCvgRuleSched tCvgRuleSched = genericDAO.get(clazz, txnId);
		genericDAO.remove(tCvgRuleSched);
	}

	/**
	 * Updates a TCvgRuleSched entity object in to the persistent store
	 * 
	 * @param tCvgRuleSched
	 *            TCvgRuleSched Entity object to be updated
	 * @return tCvgRuleSched Persisted TCvgRuleSched object
	 */
	public TCvgRuleSched updateTCvgRuleSched(final TCvgRuleSched tCvgRuleSched) {
		LOGGER.info("=========== Update TCvgRuleSched ===========");
		return genericDAO.update(tCvgRuleSched);
	}

	/**
	 * Retrieve an TCvgRuleSched object based on given TCvgRuleSched txnId.
	 * 
	 * @param tCvgRuleSchedId
	 *            the primary key value of the TCvgRuleSched Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TCvgRuleSched findTCvgRuleSchedById(final Integer tCvgRuleSchedId) {
		LOGGER.info("find TCvgRuleSched instance with txnId: " + tCvgRuleSchedId);
		return genericDAO.get(clazz, tCvgRuleSchedId);
	}

	/**
	 * Retrieve TCvgRuleSched based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCvgRuleSched> list of TCvgRuleSched if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TCvgRuleSched> findTCvgRuleScheds(final SearchFilter<TCvgRuleSched> searchFilter) {
		LOGGER.info("=========== Find TCvgRuleScheds ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TCvgRuleSched tCvgRuleSched = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tCvgRuleSchedentity", tCvgRuleSched);

		if (tCvgRuleSched.getTCvgRuleOrder() == null) {
			jpaQuery.bind(TCVGRULEORDER, new TCvgRuleOrder());
		} else {
			LOGGER.info("tCvgRuleOrder {}" + tCvgRuleSched.getTCvgRuleOrder());

			jpaQuery.bind(TCVGRULEORDER, tCvgRuleSched.getTCvgRuleOrder());
		}
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, 0, -1);
	}

	/**
	 * Fetch count of records for TCvgRuleScheds.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTCvgRuleScheds(final SearchFilter<TCvgRuleSched> searchFilter) {
		LOGGER.info("=========== Count TCvgRuleSched ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TCvgRuleSched tCvgRuleSched = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tCvgRuleSchedentity", tCvgRuleSched);

		if (tCvgRuleSched.getTCvgRuleOrder() == null) {
			jpaCountQuery.bind(TCVGRULEORDER, new TCvgRuleOrder());
		} else {
			LOGGER.info("tCvgRuleOrder {}" + tCvgRuleSched.getTCvgRuleOrder());

			jpaCountQuery.bind(TCVGRULEORDER, tCvgRuleSched.getTCvgRuleOrder());
		}

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TCvgRuleSched based on given search criteria using JPA named Query.
	 * The search criteria is of TCvgRuleOrder type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCvgRuleSched> list of TCvgRuleScheds if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TCvgRuleSched> getTCvgRuleSchedsByTCvgRuleOrder(final SearchFilter<TCvgRuleOrder> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TCvgRuleOrder tCvgRuleOrder = searchFilter.getEntityClass();
		List<Object> tCvgRuleOrderList = new ArrayList<Object>();
		tCvgRuleOrderList.add(tCvgRuleOrder);
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTCvgRuleSchedByTCvgRuleOrder", tCvgRuleOrderList, index, maxresult);
	}

	/**
	 * Count TCvgRuleSched based on given search criteria using JPA named Query.
	 * The search criteria is of TCvgRuleOrder type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTCvgRuleSchedsByTCvgRuleOrder(final SearchFilter<TCvgRuleOrder> searchFilter) {

		final TCvgRuleOrder tCvgRuleOrder = searchFilter.getEntityClass();
		List<Object> tCvgRuleOrderList = new ArrayList<Object>();
		tCvgRuleOrderList.add(tCvgRuleOrder);
		return genericDAO.findEntitiesByNamedQuery("CountTCvgRuleSchedsByTCvgRuleOrder", tCvgRuleOrderList);
	}
	 /**
	 * Retrieve TCvgRulesched for given criteria
	 * 
	 * 
	 * @param tenantId
	 *            -holds tenant Id
	 * @param txtId
	 *            -holds txtId
	 * @param flag
	 *            -holds flag
	 * 
	 * @return List<TCvgRuleSched> list of TCvgRule if it exists against given
	 *         criteria. 
	 */
	@Override
	public List<TCvgRuleSched> findTCvgRuleSchedsByTxtId(Integer txtId,
		 short tenantId,boolean flag) {
		List<Object> paramList = new ArrayList<>();
		
		paramList.add(txtId);
		paramList.add(tenantId);
		if(flag){
		return genericDAO.findEntitiesByNamedQueryMultiCond(
				"FindTCvgRuleSchedByTxnId", paramList, 0, -1);
		}else{
		return genericDAO.findEntitiesByNamedQueryMultiCond(
				"FindTCvgRuleSchedByTenantId", paramList, 0, -1);
		}
		
	}
	/**
	 * Update t cvg sched by sales pos id.
	 *
	 * @param tenantId the tenant id
	 * @param salesPosId the sales pos id
	 * @param updatedBy the updated by
	 * @return the int
	 */
	@Override
	public int updateTCvgSchedBySalesPosId(Short tenantId, Long salesPosId,Integer updatedBy) {
		List paramList = new ArrayList();
		paramList.add(DateUtil.getCurrentDate());
		paramList.add(updatedBy);
		paramList.add(tenantId);
		paramList.add(salesPosId);
		return genericDAO.updateEntitiesByNamedQueryMultiCond("UpdateTCvgSchedBySalesPosId", paramList, -1, -1);
		
	}
	/**
	 * Gets the order ids by sales pos id.
	 *
	 * @param salesPosId the sales pos id
	 * @param tenantId the tenant id
	 * @return the order ids by sales pos id
	 */
	@Override
	public List<Integer> getOrderIdsBySalesPosId(Long salesPosId, short tenantId) {
		
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(salesPosId);
		paramList.add(tenantId);
		
		return genericDAO.findEntitiesByNamedQueryMultiCond(
				"getOrderIdsBySalesPosId", paramList, 0, -1);
	}

	/**
	 * Find t cvg txn id count.
	 *
	 * @param tenantId the tenant id
	 * @param orderId the order id
	 * @param flag the flag
	 * @return the list
	 */
	public List<Object> findTCvgTxnIdCount( short tenantId,int orderId,Character flag) {
		
		List<Object> paramList = new ArrayList<Object>();
	
		paramList.add(tenantId);
		paramList.add(orderId);
		paramList.add(flag);
		paramList.add(flag);
		List<Object> count = genericDAO.findEntitiesByNamedQueryMultiCond("FindTCvgTxnIdCount", paramList, 0, -1);
		return count;
	}
	/**
	 * Update t cvg sched by oder id.
	 *
	 * @param activeFlag the active flag
	 * @param updateDt the update dt
	 * @param updatedBy the updated by
	 * @param tenantId the tenant id
	 * @param orderId the order id
	 * @param txnIds the txn ids
	 */
	public void UpdateTCvgSchedByOderId(Character activeFlag,
			Date updateDt, Integer updateBy, Short tenantId, Integer orderId,
			List<Integer> txnIds) {
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(activeFlag);
		paramList.add(updateDt);
		paramList.add(updateBy);
		paramList.add(tenantId);
		paramList.add(orderId);
		paramList.add(txnIds);
		genericDAO.updateEntitiesByNamedQueryMultiCond("UpdateTCvgSchedByOderId", paramList, 0, -1);
		
	}

}
