package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TCvgRule;
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
 * Class provides API implementation for TCvgRuleOrderDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tCvgRuleOrderDAO")
public class TCvgRuleOrderDAOImpl implements TCvgRuleOrderDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TCvgRuleOrderDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private final Class<TCvgRuleOrder> clazz;

	public Class<TCvgRuleOrder> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TCvgRuleOrderDAOImpl() {
		super();
		this.clazz = TCvgRuleOrder.class;
	}

	private static final String JPAQL = "select tCvgRuleOrderentity from TCvgRuleOrder tCvgRuleOrderentity";

	private static final String JPACOUNTQL = "select count(tCvgRuleOrderentity) from TCvgRuleOrder tCvgRuleOrderentity";

	private static final String[] RESTRICTIONS = {
			"tCvgRuleOrderentity.orderId = #{tCvgRuleOrderentity.getOrderId}",
			"tCvgRuleOrderentity.activeFlag = #{tCvgRuleOrderentity.getActiveFlag}",
			"tCvgRuleOrderentity.orderName like '#{tCvgRuleOrderentity.getOrderName}%'",
			"tCvgRuleOrderentity.orderDesc like '#{tCvgRuleOrderentity.getOrderDesc}%'",
			"tCvgRuleOrderentity.createdBy = #{tCvgRuleOrderentity.getCreatedBy}",
			"Date(tCvgRuleOrderentity.createDt) = '#{tCvgRuleOrderentity.getCreateDt}'",
			"tCvgRuleOrderentity.updatedBy = #{tCvgRuleOrderentity.getUpdatedBy}",
			"Date(tCvgRuleOrderentity.updateDt) = '#{tCvgRuleOrderentity.getUpdateDt}'",
			"tCvgRuleOrderentity.tenantId = #{tCvgRuleOrderentity.getTenantId}" };

	/**
	 * Stores a new TCvgRuleOrder entity object in to the persistent store
	 * 
	 * @param tCvgRuleOrder
	 *            TCvgRuleOrder Entity object to be persisted
	 * @return tCvgRuleOrder Persisted TCvgRuleOrder object
	 */
	public TCvgRuleOrder createTCvgRuleOrder(final TCvgRuleOrder tCvgRuleOrder) {
		LOGGER.info("=========== Create TCvgRuleOrder ===========");
		return genericDAO.store(tCvgRuleOrder);
	}

	/**
	 * Deletes a TCvgRuleOrder entity object from the persistent store
	 * 
	 * @param tCvgRuleOrder
	 *            TCvgRuleOrder Entity object to be deleted
	 */
	public void deleteTCvgRuleOrder(final Integer orderId) {
		LOGGER.info("=========== Delete TCvgRuleOrder ===========");
		final TCvgRuleOrder tCvgRuleOrder = genericDAO.get(clazz, orderId);
		genericDAO.remove(tCvgRuleOrder);
	}

	/**
	 * Updates a TCvgRuleOrder entity object in to the persistent store
	 * 
	 * @param tCvgRuleOrder
	 *            TCvgRuleOrder Entity object to be updated
	 * @return tCvgRuleOrder Persisted TCvgRuleOrder object
	 */
	public TCvgRuleOrder updateTCvgRuleOrder(final TCvgRuleOrder tCvgRuleOrder) {
		LOGGER.info("=========== Update TCvgRuleOrder ===========");
		return genericDAO.update(tCvgRuleOrder);
	}

	/**
	 * Retrieve an TCvgRuleOrder object based on given TCvgRuleOrder orderId.
	 * 
	 * @param tCvgRuleOrderId
	 *            the primary key value of the TCvgRuleOrder Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TCvgRuleOrder findTCvgRuleOrderById(final Integer tCvgRuleOrderId) {
		LOGGER.info("find TCvgRuleOrder instance with orderId: "
				+ tCvgRuleOrderId);
		return genericDAO.get(clazz, tCvgRuleOrderId);
	}

	/**
	 * Retrieve TCvgRuleOrder based on given search criteria using Dynamic
	 * JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCvgRuleOrder> list of TCvgRuleOrder if it exists against
	 *         given criteria. Returns null if not found
	 */
	public List<TCvgRuleOrder> findTCvgRuleOrders(
			final SearchFilter<TCvgRuleOrder> searchFilter) {
		LOGGER.info("=========== Find TCvgRuleOrders ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TCvgRuleOrder tCvgRuleOrder = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		// int maxresult = 3;
		// int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tCvgRuleOrderentity",
				tCvgRuleOrder);
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		
		//Setting the cacheable true to enable caching
		jpaQuery.setCacheable(true);
		
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TCvgRuleOrders.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTCvgRuleOrders(
			final SearchFilter<TCvgRuleOrder> searchFilter) {
		LOGGER.info("=========== Count TCvgRuleOrder ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TCvgRuleOrder tCvgRuleOrder = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tCvgRuleOrderentity",
				tCvgRuleOrder);

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}
	 /**
 	 * Find allby join query.
 	 *
 	 * @param orderId the order id
 	 * @param tenantId the tenant id
 	 * @return the list
 	 */
	@Override
	public List<TCvgRule> findAllbyJoinQuery(Integer orderId, short tenantId) {
		List list = new ArrayList();
		list.add(orderId);
		list.add(tenantId);
		List<TCvgRule> cvgRuleList = genericDAO.findEntitiesByNamedQueryMultiCond(
				"FindAllOrderByJoinStmt", list, 0, -1);
		return cvgRuleList;
	}
	 /**
 	 * Find order by name.
 	 *
 	 * @param searchFilter the search filter
 	 * @return the list
 	 */
	@Override
	public List<TCvgRuleOrder> findOrderByName(SearchFilter<TCvgRuleOrder> searchFilter) {
		// TODO Auto-generated method stub
		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TCvgRuleOrder tCvgRuleOrder = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		
		List paramList = new ArrayList();
		paramList.add(tCvgRuleOrder.getOrderName());
		paramList.add(tCvgRuleOrder.getTenantId());

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindOrdDtlsByOrderName", paramList, index, maxresult);
	}
	 /**
 	 * Find status.
 	 *
 	 * @param orderId the order id
 	 * @param tenantId the tenant id
 	 * @return the map
 	 */
	@Override
	public Map<Integer,String> findStatus(Integer orderId , short tenantId) {
		
			List list = new ArrayList();
			list.add(tenantId);
			list.add(orderId);
			//list.add(ruleID);
			list.add(orderId);
			
			Map<Integer,String> map = new HashMap<Integer,String>();
			
			List statusList = genericDAO.findEntitiesByNamedQueryMultiCond(
					"FindStatusOfOrderByJoinStmt", list, 0, -1);
			
			if (statusList != null && statusList.size() > 0) {
				for (Object objs : statusList) {
					Object[] row = (Object[]) objs;
					map.put(new Integer(row[1].toString()), row[0].toString());

				}
			}else{
				list = new ArrayList();
				list.add(tenantId);
				list.add(orderId);
				statusList = genericDAO.findEntitiesByNamedQueryMultiCond(
						"FindRuleIdsByJoinStmt", list, 0, -1);
				for (Object objs : statusList) {
					Object row = (Object) objs;
					map.put(new Integer(row.toString()), "Submitted");

				}
			}
			return map;
	
	}
	/**
	 * Retrieve an TCvgRuleOrder object based on given TCvgRuleOrder orderId,TenantId.
	 * 
	 * @param tCvgRuleOrderId
	 *            the primary key value of the TCvgRuleOrder Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 **/
	@Override
	public TCvgRuleOrder findTCvgRuleOrderById(Integer tCvgRuleOrderId,
			Short tenantId) {
		TCvgRuleOrder tCvgRuleOrder = new TCvgRuleOrder();
		List paramList = new ArrayList();
		paramList.add(tCvgRuleOrderId);
		paramList.add(tenantId);
		List<TCvgRuleOrder> tCvgRuleOrdersList = genericDAO.findEntitiesByNamedQueryMultiCond("FindAllTCvgRuleOrdersById", paramList, 0, -1);
		if(tCvgRuleOrdersList!= null){
			tCvgRuleOrder = tCvgRuleOrdersList.get(0);
		}
		return tCvgRuleOrder;
	}

}
