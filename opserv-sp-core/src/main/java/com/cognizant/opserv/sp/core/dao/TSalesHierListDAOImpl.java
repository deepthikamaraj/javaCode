package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TSalesHierList;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TSalesHierListDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tSalesHierListDAO")
public class TSalesHierListDAOImpl implements TSalesHierListDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TSalesHierListDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private static final String TSALESHIERLIST = "tSalesHierList";

	private final Class<TSalesHierList> clazz;

	public Class<TSalesHierList> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TSalesHierListDAOImpl() {
		super();
		this.clazz = TSalesHierList.class;
	}

	private static final String JPAQL = "select tSalesHierListentity from TSalesHierList tSalesHierListentity";

	private static final String JPACOUNTQL = "select count(tSalesHierListentity) from TSalesHierList tSalesHierListentity";

	private static final String[] RESTRICTIONS = {
			"tSalesHierListentity.salesHierId = #{tSalesHierListentity.getSalesHierId}",
			"tSalesHierListentity.hierName like '#{tSalesHierListentity.getHierName}%'",
			"tSalesHierListentity.hierDesc like '#{tSalesHierListentity.getHierDesc}%'",
			"tSalesHierListentity.defHierFlag = #{tSalesHierListentity.getDefHierFlag}",
			"tSalesHierListentity.activeFlag = #{tSalesHierListentity.getActiveFlag}",
			"Date(tSalesHierListentity.effStartDt) = '#{tSalesHierListentity.getEffStartDt}'",
			"Date(tSalesHierListentity.effEndDt) = '#{tSalesHierListentity.getEffEndDt}'",
			"tSalesHierListentity.createdBy = #{tSalesHierListentity.getCreatedBy}",
			"Date(tSalesHierListentity.createDt) = '#{tSalesHierListentity.getCreateDt}'",
			"tSalesHierListentity.updatedBy = #{tSalesHierListentity.getUpdatedBy}",
			"Date(tSalesHierListentity.updateDt) = '#{tSalesHierListentity.getUpdateDt}'",
			"tSalesHierListentity.tenantId = #{tSalesHierListentity.getTenantId}",
			"tSalesHierListentity.tSalesHierList.salesHierId = #{tSalesHierListentity.tSalesHierList.getSalesHierId}" };

	/**
	 * Stores a new TSalesHierList entity object in to the persistent store
	 * 
	 * @param tSalesHierList
	 *            TSalesHierList Entity object to be persisted
	 * @return tSalesHierList Persisted TSalesHierList object
	 */
	public TSalesHierList createTSalesHierList(final TSalesHierList tSalesHierList) {
		LOGGER.info("=========== Create TSalesHierList ===========");
		return genericDAO.store(tSalesHierList);
	}

	/**
	 * Deletes a TSalesHierList entity object from the persistent store
	 * 
	 * @param tSalesHierList
	 *            TSalesHierList Entity object to be deleted
	 */
	public void deleteTSalesHierList(final Long salesHierId) {
		LOGGER.info("=========== Delete TSalesHierList ===========");
		final TSalesHierList tSalesHierList = genericDAO.get(clazz, salesHierId);
		genericDAO.remove(tSalesHierList);
	}

	/**
	 * Updates a TSalesHierList entity object in to the persistent store
	 * 
	 * @param tSalesHierList
	 *            TSalesHierList Entity object to be updated
	 * @return tSalesHierList Persisted TSalesHierList object
	 */
	public TSalesHierList updateTSalesHierList(final TSalesHierList tSalesHierList) {
		LOGGER.info("=========== Update TSalesHierList ===========");
		return genericDAO.update(tSalesHierList);
	}

	/**
	 * Retrieve an TSalesHierList object based on given TSalesHierList salesHierId.
	 * 
	 * @param tSalesHierListId
	 *            the primary key value of the TSalesHierList Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TSalesHierList findTSalesHierListById(final Long tSalesHierListId) {
		LOGGER.info("find TSalesHierList instance with salesHierId: " + tSalesHierListId);
		return genericDAO.get(clazz, tSalesHierListId);
	}

	/**
	 * Retrieve TSalesHierList based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TSalesHierList> list of TSalesHierList if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TSalesHierList> findTSalesHierLists(final SearchFilter<TSalesHierList> searchFilter) {
		LOGGER.info("=========== Find TSalesHierLists ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TSalesHierList tSalesHierList = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tSalesHierListentity", tSalesHierList);

		if (tSalesHierList.getTSalesHierList() == null) {
			jpaQuery.bind(TSALESHIERLIST, new TSalesHierList());
		} else {
			LOGGER.info("tSalesHierList {}"+ tSalesHierList.getTSalesHierList());

			jpaQuery.bind(TSALESHIERLIST, tSalesHierList.getTSalesHierList());
		}
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TSalesHierLists.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTSalesHierLists(final SearchFilter<TSalesHierList> searchFilter) {
		LOGGER.info("=========== Count TSalesHierList ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TSalesHierList tSalesHierList = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tSalesHierListentity", tSalesHierList);

		if (tSalesHierList.getTSalesHierList() == null) {
			jpaCountQuery.bind(TSALESHIERLIST, new TSalesHierList());
		} else {
			LOGGER.info("tSalesHierList {}"+ tSalesHierList.getTSalesHierList());

			jpaCountQuery.bind(TSALESHIERLIST, tSalesHierList.getTSalesHierList());
		}

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TSalesHierList based on given search criteria using JPA named Query.
	 * The search criteria is of TSalesHierList type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TSalesHierList> list of TSalesHierLists if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TSalesHierList> getTSalesHierListsByTSalesHierList(final SearchFilter<TSalesHierList> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTSalesHierListByTSalesHierList", queryParam, index,
				maxresult);
	}

	/**
	 * Count TSalesHierList based on given search criteria using JPA named Query.
	 * The search criteria is of TSalesHierList type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTSalesHierListsByTSalesHierList(final SearchFilter<TSalesHierList> searchFilter) {

		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());
		return genericDAO.findEntitiesByNamedQuery("CountTSalesHierListsByTSalesHierList", queryParam);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<TSalesHierList> getActiveTSalesHierList(
			SearchFilter<TSalesHierList> searchFilter) {		
		final PaginationInfo paginationInfo=searchFilter.getPaginationInfo();
		final  TSalesHierList tSalesHierList=searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();		
		List paramList = new ArrayList();		
		paramList.add(tSalesHierList.getTenantId());
		paramList.add(tSalesHierList.getEffStartDt());
			
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindActiveTSalesHierList", paramList, index, maxresult);
	}

	@Override
	public List<TSalesHierList> fetchSequenceNumber(Short tenantId) {
		
		List queryParam = new ArrayList();
		
		queryParam.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("fetchSequenceNumber", queryParam, -1, 1);	
	}

}
