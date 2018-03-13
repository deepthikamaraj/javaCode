package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TRoleList;
import com.cognizant.peg.core.common.ISearchCriteria;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TRoleListDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tRoleListDAO")
public class TRoleListDAOImpl implements TRoleListDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TRoleListDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private static final String TROLELIST = "tRoleList";

	private final Class<TRoleList> clazz;

	public Class<TRoleList> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TRoleListDAOImpl() {
		super();
		this.clazz = TRoleList.class;
	}

	private static final String JPAQL = "select tRoleListentity from TRoleList tRoleListentity";

	private static final String JPACOUNTQL = "select count(tRoleListentity) from TRoleList tRoleListentity";

	private static final String[] RESTRICTIONS = { "tRoleListentity.roleId = #{tRoleListentity.getRoleId}",
			"tRoleListentity.roleName like '#{tRoleListentity.getRoleName}%'",
			"tRoleListentity.roleDesc like '#{tRoleListentity.getRoleDesc}%'",
			"tRoleListentity.defRoleFlag = #{tRoleListentity.getDefRoleFlag}",
			"tRoleListentity.activeFlag = #{tRoleListentity.getActiveFlag}",
			"Date(tRoleListentity.effStartDt) = '#{tRoleListentity.getEffStartDt}'",
			"Date(tRoleListentity.effEndDt) = '#{tRoleListentity.getEffEndDt}'",
			"tRoleListentity.createdBy = #{tRoleListentity.getCreatedBy}",
			"Date(tRoleListentity.createDt) = '#{tRoleListentity.getCreateDt}'",
			"tRoleListentity.updatedBy = #{tRoleListentity.getUpdatedBy}",
			"Date(tRoleListentity.updateDt) = '#{tRoleListentity.getUpdateDt}'",
			"tRoleListentity.tenantId = #{tRoleListentity.getTenantId}",
			"tRoleListentity.tRoleList.roleId = #{tRoleListentity.tRoleList.getRoleId}" };

	/**
	 * Stores a new TRoleList entity object in to the persistent store
	 * 
	 * @param tRoleList
	 *            TRoleList Entity object to be persisted
	 * @return tRoleList Persisted TRoleList object
	 */
	public TRoleList createTRoleList(final TRoleList tRoleList) {
		LOGGER.info("=========== Create TRoleList ===========");
		return genericDAO.store(tRoleList);
	}

	/**
	 * Deletes a TRoleList entity object from the persistent store
	 * 
	 * @param tRoleList
	 *            TRoleList Entity object to be deleted
	 */
	public void deleteTRoleList(final Integer roleId) {
		LOGGER.info("=========== Delete TRoleList ===========");
		final TRoleList tRoleList = genericDAO.get(clazz, roleId);
		genericDAO.remove(tRoleList);
	}

	/**
	 * Updates a TRoleList entity object in to the persistent store
	 * 
	 * @param tRoleList
	 *            TRoleList Entity object to be updated
	 * @return tRoleList Persisted TRoleList object
	 */
	public TRoleList updateTRoleList(final TRoleList tRoleList) {
		LOGGER.info("=========== Update TRoleList ===========");
		return genericDAO.update(tRoleList);
	}

	/**
	 * Retrieve an TRoleList object based on given TRoleList roleId.
	 * 
	 * @param tRoleListId
	 *            the primary key value of the TRoleList Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TRoleList findTRoleListById(final Integer tRoleListId) {
		LOGGER.info("find TRoleList instance with roleId: " + tRoleListId);
		return genericDAO.get(clazz, tRoleListId);
	}

	/**
	 * Retrieve TRoleList based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRoleList> list of TRoleList if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TRoleList> findTRoleLists(final SearchFilter<TRoleList> searchFilter) {
		LOGGER.info("=========== Find TRoleLists ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TRoleList tRoleList = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tRoleListentity", tRoleList);

		if (tRoleList.getTRoleList() == null) {
			jpaQuery.bind(TROLELIST, new TRoleList());
		} else {
			LOGGER.info("tRoleList {}"+ tRoleList.getTRoleList());

			jpaQuery.bind(TROLELIST, tRoleList.getTRoleList());
		}
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TRoleLists.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTRoleLists(final SearchFilter<TRoleList> searchFilter) {
		LOGGER.info("=========== Count TRoleList ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TRoleList tRoleList = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tRoleListentity", tRoleList);

		if (tRoleList.getTRoleList() == null) {
			jpaCountQuery.bind(TROLELIST, new TRoleList());
		} else {
			LOGGER.info("tRoleList {}"+ tRoleList.getTRoleList());

			jpaCountQuery.bind(TROLELIST, tRoleList.getTRoleList());
		}

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TRoleList based on given search criteria using JPA named Query.
	 * The search criteria is of TRoleList type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRoleList> list of TRoleLists if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TRoleList> getTRoleListsByTRoleList(final SearchFilter<TRoleList> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTRoleListByTRoleList", queryParam, index, maxresult);
	}

	/**
	 * Count TRoleList based on given search criteria using JPA named Query.
	 * The search criteria is of TRoleList type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTRoleListsByTRoleList(final SearchFilter<TRoleList> searchFilter) {

		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());
		return genericDAO.findEntitiesByNamedQuery("CountTRoleListsByTRoleList", queryParam);
	}

	@Override
	public List<Object> findTRoleListByMaxOfRoleId(Short tenantId) {
		List paramList = new ArrayList();
		paramList.add('Y');
		paramList.add(tenantId);
		int index=0;
		int maxresult=-1;
		List<Object> list=genericDAO.findEntitiesByNamedQueryMultiCond("FindTRoleListByMaxId",paramList, index, maxresult);
		return list;
		}

	@Override
	public List<Integer> findTRoleListByMaxRoleId(Short tenantId) {
		List paramList = new ArrayList();
		paramList.add(tenantId);
		//return genericDAO.findEntitiesByNamedQuery("FindTRoleListByMaxRoleId");
		return genericDAO.findEntitiesByNamedQuery("FindTRoleListByMaxRoleId", paramList);
	}

	@Override
	public List<Object[]> findRoleIdAndRoleName(Short tenantId) {
		List paramList = new ArrayList();
		paramList.add('Y');
		paramList.add(tenantId);
		int index=0;
		int maxresult=-1;
		List<Object[]> tRoleList = genericDAO.findEntitiesByNamedQueryMultiCond("FindRoleIdAndRoleName", paramList, index, maxresult);
		return tRoleList;
	}
	
	@Override
	public List<TRoleList> findTRoleListBySearchCriteria(
			ISearchCriteria criteria) {
		LOGGER.info("=========== Find TRoleLists by Search Criteria===========");
		return genericDAO.search(clazz, criteria);
	}

}
