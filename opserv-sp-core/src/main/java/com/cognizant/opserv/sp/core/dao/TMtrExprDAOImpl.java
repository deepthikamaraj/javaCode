package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TMtr;
import com.cognizant.opserv.sp.core.entity.TMtrExpr;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TMtrExprDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tMtrExprDAO")
public class TMtrExprDAOImpl implements TMtrExprDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TMtrExprDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private static final String TMTR = "tMtr";

	private final Class<TMtrExpr> clazz;

	public Class<TMtrExpr> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TMtrExprDAOImpl() {
		super();
		this.clazz = TMtrExpr.class;
	}

	private static final String JPAQL = "select tMtrExprentity from TMtrExpr tMtrExprentity";

	private static final String JPACOUNTQL = "select count(tMtrExprentity) from TMtrExpr tMtrExprentity";

	private static final String[] RESTRICTIONS = { "tMtrExprentity.exprId = #{tMtrExprentity.getExprId}",
			"tMtrExprentity.mtrExpr like '#{tMtrExprentity.getMtrExpr}%'",
			"tMtrExprentity.createdBy = #{tMtrExprentity.getCreatedBy}",
			"Date(tMtrExprentity.createDt) = '#{tMtrExprentity.getCreateDt}'",
			"tMtrExprentity.updatedBy = #{tMtrExprentity.getUpdatedBy}",
			"Date(tMtrExprentity.updateDt) = '#{tMtrExprentity.getUpdateDt}'",
			"tMtrExprentity.tenantId = #{tMtrExprentity.getTenantId}",
			"tMtrExprentity.exprType = #{tMtrExprentity.getExprType}",
			"tMtrExprentity.tMtr.mtrId = #{tMtrExprentity.tMtr.getMtrId}" };

	/**
	 * Stores a new TMtrExpr entity object in to the persistent store
	 * 
	 * @param tMtrExpr
	 *            TMtrExpr Entity object to be persisted
	 * @return tMtrExpr Persisted TMtrExpr object
	 */
	public TMtrExpr createTMtrExpr(final TMtrExpr tMtrExpr) {
		LOGGER.info("=========== Create TMtrExpr ===========");
		return genericDAO.store(tMtrExpr);
	}

	/**
	 * Deletes a TMtrExpr entity object from the persistent store
	 * 
	 * @param tMtrExpr
	 *            TMtrExpr Entity object to be deleted
	 */
	public void deleteTMtrExpr(final Integer exprId) {
		LOGGER.info("=========== Delete TMtrExpr ===========");
		final TMtrExpr tMtrExpr = genericDAO.get(clazz, exprId);
		genericDAO.remove(tMtrExpr);
	}

	/**
	 * Updates a TMtrExpr entity object in to the persistent store
	 * 
	 * @param tMtrExpr
	 *            TMtrExpr Entity object to be updated
	 * @return tMtrExpr Persisted TMtrExpr object
	 */
	public TMtrExpr updateTMtrExpr(final TMtrExpr tMtrExpr) {
		LOGGER.info("=========== Update TMtrExpr ===========");
		return genericDAO.update(tMtrExpr);
	}

	/**
	 * Retrieve an TMtrExpr object based on given TMtrExpr exprId.
	 * 
	 * @param tMtrExprId
	 *            the primary key value of the TMtrExpr Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TMtrExpr findTMtrExprById(final Integer tMtrExprId) {
		LOGGER.info("find TMtrExpr instance with exprId: " + tMtrExprId);
		return genericDAO.get(clazz, tMtrExprId);
	}

	/**
	 * Retrieve TMtrExpr based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TMtrExpr> list of TMtrExpr if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TMtrExpr> findTMtrExprs(final SearchFilter<TMtrExpr> searchFilter) {
		LOGGER.info("=========== Find TMtrExprs ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TMtrExpr tMtrExpr = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tMtrExprentity", tMtrExpr);

		if (tMtrExpr.getTMtr() == null) {
			jpaQuery.bind(TMTR, new TMtr());
		} else {
			LOGGER.info("tMtr {}"+ tMtrExpr.getTMtr());

			jpaQuery.bind(TMTR, tMtrExpr.getTMtr());
		}
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TMtrExprs.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTMtrExprs(final SearchFilter<TMtrExpr> searchFilter) {
		LOGGER.info("=========== Count TMtrExpr ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TMtrExpr tMtrExpr = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tMtrExprentity", tMtrExpr);

		if (tMtrExpr.getTMtr() == null) {
			jpaCountQuery.bind(TMTR, new TMtr());
		} else {
			LOGGER.info("tMtr {}"+ tMtrExpr.getTMtr());

			jpaCountQuery.bind(TMTR, tMtrExpr.getTMtr());
		}

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TMtrExpr based on given search criteria using JPA named Query.
	 * The search criteria is of TMtr type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TMtrExpr> list of TMtrExprs if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TMtrExpr> getTMtrExprsByTMtr(final SearchFilter<TMtr> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TMtr tMtr = searchFilter.getEntityClass();
		List<Object> tMtrList = new ArrayList<Object>();
		tMtrList.add(tMtr);
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTMtrExprByTMtr", tMtrList, index, maxresult);
	}

	/**
	 * Count TMtrExpr based on given search criteria using JPA named Query.
	 * The search criteria is of TMtr type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTMtrExprsByTMtr(final SearchFilter<TMtr> searchFilter) {

		final TMtr tMtr = searchFilter.getEntityClass();
		List<Object> tMtrList = new ArrayList<Object>();
		tMtrList.add(tMtr);
		return genericDAO.findEntitiesByNamedQuery("CountTMtrExprsByTMtr", tMtrList);
	}

	@Override
	public List<TMtrExpr> getMetricExpressions(Integer metricId,Character expressionType,Short tenantId) {
		// TODO Auto-generated method stub
		List queryParam = new ArrayList();
		queryParam.add(metricId);
		queryParam.add(expressionType);
		queryParam.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("getMetricExpression", queryParam, 0, -1);
	}

	@Override
	public List<Object> executeExpression(String nativeQuery) {
		// TODO Auto-generated method stub
		return genericDAO.findByNativeQuery(nativeQuery);
	}

	@Override
	public List<TMtrExpr> findExprIdByMtrId(Integer metricId) {
		List<Object> metricIdList = new ArrayList<Object>();
		metricIdList.add(metricId);
		
		return genericDAO.findEntitiesByNamedQuery("FindExprIdByMtrId", metricIdList);
	}

	@Override
	public int deleteFromHistTable(Integer exprId) {
		return genericDAO.executeNativeQuery("delete from t_mtr_expr_hist where expr_id = "+exprId);
	}
	
	@Override
	public List<Object[]> findTMtrExprByMtrId(List<Integer> metricIds,List<Character> expressionTypes,Short tenantId) {
		List<Object> queryParam = new ArrayList<Object>();
		queryParam.add(metricIds);
		queryParam.add(expressionTypes);
		queryParam.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTMtrExprByMtrId", queryParam, 0, -1);
	}

}
