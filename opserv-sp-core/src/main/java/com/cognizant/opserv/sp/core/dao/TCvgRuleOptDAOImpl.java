package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TCvgRuleOpt;
import com.cognizant.opserv.sp.core.entity.TCvgRuleOptId;
import com.cognizant.opserv.sp.core.entity.TCvgRuleOrder;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TCvgRuleOptDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tCvgRuleOptDAO")
public class TCvgRuleOptDAOImpl implements TCvgRuleOptDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TCvgRuleOptDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private static final String TCVGRULEORDER = "tCvgRuleOrder";

	private final Class<TCvgRuleOpt> clazz;

	public Class<TCvgRuleOpt> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TCvgRuleOptDAOImpl() {
		super();
		this.clazz = TCvgRuleOpt.class;
	}

	private static final String JPAQL = "select tCvgRuleOptentity from TCvgRuleOpt tCvgRuleOptentity";

	private static final String JPACOUNTQL = "select count(*) from TCvgRuleOpt tCvgRuleOptentity";

	private static final String[] RESTRICTIONS = {
			"tCvgRuleOptentity.tCvgRuleOptId.orderId = #{tCvgRuleOptentity.tCvgRuleOptId.getOrderId}",
			"tCvgRuleOptentity.tCvgRuleOptId.ruleId = #{tCvgRuleOptentity.tCvgRuleOptId.getRuleId}",
			"tCvgRuleOptentity.orderSeq = #{tCvgRuleOptentity.getOrderSeq}",
			"tCvgRuleOptentity.createdBy = #{tCvgRuleOptentity.getCreatedBy}",
			"Date(tCvgRuleOptentity.createDt) = '#{tCvgRuleOptentity.getCreateDt}'",
			"tCvgRuleOptentity.updatedBy = #{tCvgRuleOptentity.getUpdatedBy}",
			"Date(tCvgRuleOptentity.updateDt) = '#{tCvgRuleOptentity.getUpdateDt}'",
			"tCvgRuleOptentity.tenantId = #{tCvgRuleOptentity.getTenantId}",
			"tCvgRuleOptentity.ruleExpr like '#{tCvgRuleOptentity.getRuleExpr}%'",
			"tCvgRuleOptentity.tCvgRuleOrder.orderId = #{tCvgRuleOptentity.tCvgRuleOrder.getOrderId}" };

	/**
	 * Stores a new TCvgRuleOpt entity object in to the persistent store
	 * 
	 * @param tCvgRuleOpt
	 *            TCvgRuleOpt Entity object to be persisted
	 * @return tCvgRuleOpt Persisted TCvgRuleOpt object
	 */
	public TCvgRuleOpt createTCvgRuleOpt(final TCvgRuleOpt tCvgRuleOpt) {
		LOGGER.info("=========== Create TCvgRuleOpt ===========");
		return genericDAO.store(tCvgRuleOpt);
	}

	/**
	 * Deletes a TCvgRuleOpt entity object from the persistent store
	 * 
	 * @param tCvgRuleOpt
	 *            TCvgRuleOpt Entity object to be deleted
	 */
	public void deleteTCvgRuleOpt(final TCvgRuleOptId tCvgRuleOptId) {
		LOGGER.info("=========== Delete TCvgRuleOpt ===========");
		final TCvgRuleOpt tCvgRuleOpt = genericDAO.get(clazz, tCvgRuleOptId);
		genericDAO.remove(tCvgRuleOpt);
	}

	/**
	 * Updates a TCvgRuleOpt entity object in to the persistent store
	 * 
	 * @param tCvgRuleOpt
	 *            TCvgRuleOpt Entity object to be updated
	 * @return tCvgRuleOpt Persisted TCvgRuleOpt object
	 */
	public TCvgRuleOpt updateTCvgRuleOpt(final TCvgRuleOpt tCvgRuleOpt) {
		LOGGER.info("=========== Update TCvgRuleOpt ===========");
		return genericDAO.update(tCvgRuleOpt);
	}

	/**
	 * Retrieve an TCvgRuleOpt object based on given TCvgRuleOpt TCvgRuleOptId.
	 * 
	 * @param tCvgRuleOptId
	 *            the primary key value of the TCvgRuleOpt Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TCvgRuleOpt findTCvgRuleOptById(final TCvgRuleOptId tCvgRuleOptId) {
		LOGGER.info("find TCvgRuleOpt instance with TCvgRuleOptId: " + tCvgRuleOptId);
		return genericDAO.get(clazz, tCvgRuleOptId);
	}

	/**
	 * Retrieve TCvgRuleOpt based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCvgRuleOpt> list of TCvgRuleOpt if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TCvgRuleOpt> findTCvgRuleOpts(final SearchFilter<TCvgRuleOpt> searchFilter) {
		LOGGER.info("=========== Find TCvgRuleOpts ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TCvgRuleOpt tCvgRuleOpt = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tCvgRuleOptentity", tCvgRuleOpt);

		if (tCvgRuleOpt.getTCvgRuleOrder() == null) {
			jpaQuery.bind(TCVGRULEORDER, new TCvgRuleOrder());
		} else {
			LOGGER.info("tCvgRuleOrder {}" + tCvgRuleOpt.getTCvgRuleOrder());

			jpaQuery.bind(TCVGRULEORDER, tCvgRuleOpt.getTCvgRuleOrder());
		}
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		/*Setting cacheable true*/
		jpaQuery.setCacheable(true);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TCvgRuleOpts.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTCvgRuleOpts(final SearchFilter<TCvgRuleOpt> searchFilter) {
		LOGGER.info("=========== Count TCvgRuleOpt ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TCvgRuleOpt tCvgRuleOpt = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tCvgRuleOptentity", tCvgRuleOpt);

		if (tCvgRuleOpt.getTCvgRuleOrder() == null) {
			jpaCountQuery.bind(TCVGRULEORDER, new TCvgRuleOrder());
		} else {
			LOGGER.info("tCvgRuleOrder {}"+ tCvgRuleOpt.getTCvgRuleOrder());

			jpaCountQuery.bind(TCVGRULEORDER, tCvgRuleOpt.getTCvgRuleOrder());
		}

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		/*Setting cacheable true*/
		jpaCountQuery.setCacheable(true);
		
		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TCvgRuleOpt based on given search criteria using JPA named Query.
	 * The search criteria is of TCvgRuleOrder type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCvgRuleOpt> list of TCvgRuleOpts if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TCvgRuleOpt> getTCvgRuleOptsByTCvgRuleOrder(final SearchFilter<TCvgRuleOrder> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TCvgRuleOrder tCvgRuleOrder = searchFilter.getEntityClass();
		List<Object> tCvgRuleOrderList = new ArrayList<Object>();
		tCvgRuleOrderList.add(tCvgRuleOrder);
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTCvgRuleOptByTCvgRuleOrder", tCvgRuleOrderList, index, maxresult);
	}

	/**
	 * Count TCvgRuleOpt based on given search criteria using JPA named Query.
	 * The search criteria is of TCvgRuleOrder type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTCvgRuleOptsByTCvgRuleOrder(final SearchFilter<TCvgRuleOrder> searchFilter) {

		final TCvgRuleOrder tCvgRuleOrder = searchFilter.getEntityClass();
		List<Object> tCvgRuleOrderList = new ArrayList<Object>();
		tCvgRuleOrderList.add(tCvgRuleOrder);
		return genericDAO.findEntitiesByNamedQuery("CountTCvgRuleOptsByTCvgRuleOrder", tCvgRuleOrderList);
	}
	/**
	 * Gets the t cvg rule opts by order id.
	 *
	 * @param searchFilter the search filter
	 * @return the t cvg rule opts by order id
	 */
	@Override
	public List<TCvgRuleOpt> getTCvgRuleOptsByOrderId(
			SearchFilter<TCvgRuleOpt> searchFilter) {
	
		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TCvgRuleOpt tCvgRuleOpt = searchFilter.getEntityClass();
		final Integer orderId = tCvgRuleOpt.getTCvgRuleOptId().getOrderId();
		List<Object> orderIdList = new ArrayList<Object>();
		orderIdList.add(orderId);
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTCvgRuleOptByOrderId", orderIdList, index, maxresult);
	}
	/**
	 * Find t cvg rule opt by id.
	 *
	 * @param tCvgRuleOptId the t cvg rule opt id
	 * @param tenantId the tenant id
	 * @return the t cvg rule opt
	 */
	@Override
	public TCvgRuleOpt findTCvgRuleOptById(TCvgRuleOptId tCvgRuleOptId,
			Short tenantId) {
		TCvgRuleOpt tCvgRuleOpt = new TCvgRuleOpt();
		List paramList = new ArrayList();
		paramList.add(tCvgRuleOptId);
		paramList.add(tenantId);
		List<TCvgRuleOpt> tCvgRuleOptList = genericDAO.findEntitiesByNamedQueryMultiCond("FindAllTCvgRuleOptsById", paramList, 0, -1);
		if(tCvgRuleOptList!= null){
			tCvgRuleOpt = tCvgRuleOptList.get(0);
		}
		return tCvgRuleOpt;
	}
	/**
	 * Delete t cvg rule opt.
	 *
	 * @param tCvgRuleOptId the t cvg rule opt id
	 * @param tenantId the tenant id
	 */
	@Override
	public void deleteTCvgRuleOpt(TCvgRuleOptId tCvgRuleOptId, Short tenantId) {
		List paramList = new ArrayList();
		paramList.add(tCvgRuleOptId);
		paramList.add(tenantId);
		
	}
	/**
	 * Gets the list of rule ids by order id.
	 *
	 * @param orderId the order id
	 * @param tenantId the tenant id
	 * @return the list of rule ids by order id
	 */
	@Override
	public List<Object[]> getListOfRuleIdsByOrderId(Integer orderId,short tenantId) {
		
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(orderId);
		paramList.add(tenantId);		
		return genericDAO.findEntitiesByNamedQueryMultiCond("getListOfRuleIdsByOrderId",paramList, 0, -1);
	}
	/**
	 * Count of rule idb based on order id.
	 *
	 * @param orderId the order id
	 * @param tenantId the tenant id
	 * @return the list
	 */
	@Override
	public List<Object> countOfRuleIdbBasedOnOrderId(int orderId,Short tenantId) {
	
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(orderId);
		paramList.add(tenantId);
		List<Object> count = genericDAO.findEntitiesByNamedQueryMultiCond("countOfRuleIdbBasedOnOrderId", paramList, 0, -1);
		return count;
	
	}
}
