package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TMtr;
import com.cognizant.opserv.sp.core.entity.TMtrExpr;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TMtrExpr DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TMtrExprDAO {

	/**
	 * Stores a new TMtrExpr entity object in to the persistent store
	 * 
	 * @param tMtrExpr
	 *            TMtrExpr Entity object to be persisted
	 * @return tMtrExpr Persisted TMtrExpr object
	 */
	TMtrExpr createTMtrExpr(TMtrExpr tMtrExpr);

	/**
	 * Deletes a TMtrExpr entity object from the persistent store
	 * 
	 * @param tMtrExpr
	 *            TMtrExpr Entity object to be deleted
	 */
	void deleteTMtrExpr(Integer exprId);

	/**
	 * Updates a TMtrExpr entity object in to the persistent store
	 * 
	 * @param tMtrExpr
	 *            TMtrExpr Entity object to be updated
	 * @return tMtrExpr Persisted TMtrExpr object
	 */
	TMtrExpr updateTMtrExpr(TMtrExpr tMtrExpr);

	/**
	 * Retrieve an TMtrExpr object based on given exprId.
	 * 
	 * @param exprId
	 *            the primary key value of the TMtrExpr Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TMtrExpr findTMtrExprById(Integer exprId);

	/**
	 * Retrieve TMtrExpr based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TMtrExpr> list of TMtrExprs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TMtrExpr> findTMtrExprs(SearchFilter<TMtrExpr> searchFilter);

	/**
	 * Count TMtrExpr based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTMtrExprs(SearchFilter<TMtrExpr> searchFilter);

	/**
	 * Retrieve TMtrExpr based on given search criteria using JPA named Query.
	 * The search criteria is of TMtr type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TMtrExpr> list of TMtrExprs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TMtrExpr> getTMtrExprsByTMtr(SearchFilter<TMtr> searchFilter);

	/**
	 * Count TMtrExpr based on given search criteria using JPA named Query.
	 * The search criteria is of TMtr type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTMtrExprsByTMtr(SearchFilter<TMtr> searchFilter);
	/**
	 *  
	 * @param metricId and TYPE of Expression
	 * @return List of Metric Expressions 
	 */
	List<TMtrExpr> getMetricExpressions(Integer metricId,Character expressionType,Short tenantId);
	
	List<Object> executeExpression(String nativeQuery);

	List<TMtrExpr> findExprIdByMtrId(Integer metricId);
	
	int deleteFromHistTable(Integer exprId);

	List<Object[]> findTMtrExprByMtrId(List<Integer> metricIds,
			List<Character> expressionTypes, Short tenantId);
	
}
