package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TMtrExec;
import com.cognizant.opserv.sp.core.entity.TSalesPosMtrValue;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TSalesPosMtrValue DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TSalesPosMtrValueDAO {

	/**
	 * Stores a new TSalesPosMtrValue entity object in to the persistent store
	 * 
	 * @param tSalesPosMtrValue
	 *            TSalesPosMtrValue Entity object to be persisted
	 * @return tSalesPosMtrValue Persisted TSalesPosMtrValue object
	 */
	TSalesPosMtrValue createTSalesPosMtrValue(TSalesPosMtrValue tSalesPosMtrValue);

	/**
	 * Deletes a TSalesPosMtrValue entity object from the persistent store
	 * 
	 * @param tSalesPosMtrValue
	 *            TSalesPosMtrValue Entity object to be deleted
	 */
	void deleteTSalesPosMtrValue(Long seqNumber);

	/**
	 * Updates a TSalesPosMtrValue entity object in to the persistent store
	 * 
	 * @param tSalesPosMtrValue
	 *            TSalesPosMtrValue Entity object to be updated
	 * @return tSalesPosMtrValue Persisted TSalesPosMtrValue object
	 */
	TSalesPosMtrValue updateTSalesPosMtrValue(TSalesPosMtrValue tSalesPosMtrValue);

	/**
	 * Retrieve an TSalesPosMtrValue object based on given seqNumber.
	 * 
	 * @param seqNumber
	 *            the primary key value of the TSalesPosMtrValue Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TSalesPosMtrValue findTSalesPosMtrValueById(Long seqNumber);

	/**
	 * Retrieve TSalesPosMtrValue based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TSalesPosMtrValue> list of TSalesPosMtrValues if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TSalesPosMtrValue> findTSalesPosMtrValues(SearchFilter<TSalesPosMtrValue> searchFilter);

	/**
	 * Count TSalesPosMtrValue based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTSalesPosMtrValues(SearchFilter<TSalesPosMtrValue> searchFilter);

	/**
	 * Retrieve TSalesPosMtrValue based on given search criteria using JPA named Query.
	 * The search criteria is of TMtrExec type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TSalesPosMtrValue> list of TSalesPosMtrValues if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TSalesPosMtrValue> getTSalesPosMtrValuesByTMtrExec(SearchFilter<TMtrExec> searchFilter);

	/**
	 * Count TSalesPosMtrValue based on given search criteria using JPA named Query.
	 * The search criteria is of TMtrExec type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTSalesPosMtrValuesByTMtrExec(SearchFilter<TMtrExec> searchFilter);
	
	List<Object> getOldMetricValue(Integer metricId,Short tenantId);
/**
	 * Retrieve TSalesPosMtrValue based on given search criteria using JPA named Query.
	
	 * @param salesPosId
	 *            The query criteria and search filter conditions are set
	 * @return List<TSalesPosMtrValue> list of TSalesPosMtrValues if it exists against given
	 *         criteria. Returns null if not found
	 */

	
	List<TSalesPosMtrValue> fetchmetricsBySalesPosId(Short tenantId,
			Long salesPosId);
	/**
	 * Retrieve TSalesPosMtrValue based on given search criteria using JPA named Query.
	
	 * @param salesPosId
	 *            The query criteria and search filter conditions are set
	 * @return List<TSalesPosMtrValue> list of TSalesPosMtrValues if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<Object[]> fetchMetricValueByBaseValueFlag(Long salesPosId, Long salesHier, Short tenantId,Integer metricID, Integer mtrValueTypeId);

    List<Object[]> fetchMetricValueByBaseValueFlagList(Short tenantId,List<Integer> metricID,Long salesPosId, Long salesHierId, Integer mtrValueTypeId);
	
	List<TSalesPosMtrValue> findSalesPosMtrValByPrnIDs(Long prnSalesPosId, Long prnSalesHierId,
			int mtrId);

	List<TSalesPosMtrValue> getTSalesPosMtrValuesByPosIdHierIdAndTenant(Long salesPosId,Long salesHierId,Short tenantId,boolean flag);
	
	List<TSalesPosMtrValue> findTSalesPosMtrValueBySalesPosId(Long salesPosId);

	List findTSalesPosMtrValueBySalesPosMtrId(Long salesPosId,
			Long salesHierId, int mtrId, Short tenantId, Integer mtrValueTypeId);
	
	List getTSalesPosMtrValue(String query);
	public List<Object[]> fetchMetricValueByBaseFlag(Long salesPosId, Long salesHier,Short tenantId,Integer metricID,  Integer mtrValueTypeId);

	List<TSalesPosMtrValue> getMetricValForSP(int mtrId, Long salesHierId,
			List<Long> spList, Short tenantId, Integer mtrValueTypeId);
	
	List<TSalesPosMtrValue> checkMetricIds(int mtrId,Short tenantId);

	void updateMetricBaseValuesByMetricId(int mtrId, Short tenantId, Integer mtrValueTypeId);
	
	public List<Integer> checkIfMetricsExecuted(List<Integer> mtrIds,Integer mtrValueTypeId, Character activeFlag,Short tenantId);

	void inactivateSPMetricValues(List<Integer> mtrIds, Short tenantId,	Long spId, Integer mtrValueTypeId, Character actFlag);

	List<Object[]> getMtrValueBySpAndValueType(Long spId,Integer mtrValueTypeId,Short tenantId);

	List<Object[]> getMtrValueByMtrSpAndBaseFlag(List<Integer> mtrId,Long spId,List<Integer> mtrValueTypeId,Short tenantId);

	List<Object[]> getMtrValueBySpAndValueTypeAndMtrIdAndConfigId(Long salesPosId,Integer mtrValueTypeId,Character activeFlag, List<Integer> mtrIds,List<Integer> configIds, Short tenantId);
	
	List<Object[]> getMtrValueBySpListAndValueType(List<Long> spIdList, Integer mtrValueTypeId, Integer mtrId, Short tenantId);
	
	List<Object[]> findMinAndMaxValueByMtrId(Long mtrId,Short tenantId);
	
}
