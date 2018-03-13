package com.cognizant.opserv.sp.core.dao;


import java.util.List;

import com.cognizant.opserv.sp.core.entity.TMtrConfig;
import com.cognizant.opserv.sp.core.entity.TMtrExecConfig;
import com.cognizant.opserv.sp.core.entity.TMtrTrigger;
import com.cognizant.opserv.sp.core.entity.TSalesPos;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TMtrExecConfig DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TMtrExecConfigDAO {

	/**
	 * Stores a new TMtrExecConfig entity object in to the persistent store
	 * 
	 * @param tMtrExecConfig
	 *            TMtrExecConfig Entity object to be persisted
	 * @return tMtrExecConfig Persisted TMtrExecConfig object
	 */
	TMtrExecConfig createTMtrExecConfig(TMtrExecConfig tMtrExecConfig);

	/**
	 * Deletes a TMtrExecConfig entity object from the persistent store
	 * 
	 * @param tMtrExecConfig
	 *            TMtrExecConfig Entity object to be deleted
	 */
	void deleteTMtrExecConfig(Integer configId);

	/**
	 * Updates a TMtrExecConfig entity object in to the persistent store
	 * 
	 * @param tMtrExecConfig
	 *            TMtrExecConfig Entity object to be updated
	 * @return tMtrExecConfig Persisted TMtrExecConfig object
	 */
	TMtrExecConfig updateTMtrExecConfig(TMtrExecConfig tMtrExecConfig);

	/**
	 * Retrieve an TMtrExecConfig object based on given configId.
	 * 
	 * @param configId
	 *            the primary key value of the TMtrExecConfig Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TMtrExecConfig findTMtrExecConfigById(Integer configId);

	/**
	 * Retrieve TMtrExecConfig based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TMtrExecConfig> list of TMtrExecConfigs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TMtrExecConfig> findTMtrExecConfigs(SearchFilter<TMtrExecConfig> searchFilter);

	/**
	 * Count TMtrExecConfig based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTMtrExecConfigs(SearchFilter<TMtrExecConfig> searchFilter);

	/**
	 * Retrieve TMtrExecConfig based on given search criteria using JPA named Query.
	 * The search criteria is of TMtrConfig type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TMtrExecConfig> list of TMtrExecConfigs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TMtrExecConfig> getTMtrExecConfigsByTMtrConfig(SearchFilter<TMtrConfig> searchFilter);

	/**
	 * Count TMtrExecConfig based on given search criteria using JPA named Query.
	 * The search criteria is of TMtrConfig type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTMtrExecConfigsByTMtrConfig(SearchFilter<TMtrConfig> searchFilter);

	/**
	 * Retrieve TMtrExecConfig based on given search criteria using JPA named Query.
	 * The search criteria is of TMtrTrigger type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TMtrExecConfig> list of TMtrExecConfigs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TMtrExecConfig> getTMtrExecConfigsByTMtrTrigger(SearchFilter<TMtrTrigger> searchFilter);

	/**
	 * Count TMtrExecConfig based on given search criteria using JPA named Query.
	 * The search criteria is of TMtrTrigger type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTMtrExecConfigsByTMtrTrigger(SearchFilter<TMtrTrigger> searchFilter);
	
	List<TMtrExecConfig> findTMtrExecConfigByMtrId(Integer metricId);
	List<TMtrExecConfig> getTMtrExecConfigss(Integer triggerId,Long salesHierId,Short tenantId,Long alignmentId, Long businessUnitId, Long salesTeamId);
	
	List<TSalesPos> getSalesPosDtl(Long alignmentId, Long businessUnitId, Long salesTeamId, Long salesHierId,Short tenantId );
	
	List<Object> getTMtrExecConfig(Integer metricId, Long slaesHierId, Integer triggerId);
	
	List<TMtrExecConfig> getExecutionConfiguration(Short tenantId);
	
	List<TMtrExecConfig> getTMtrExecConfigsForTrigger(Integer metricId, Long slaesHierId, Integer triggerId);
	
	List<TMtrExecConfig> findAllTMtrExecConfigByMtrId(Integer metricId,Long slaesHierId);
	
	List<Object> getMetricExecutionConfig(Long alignmentId, Long businessUnitId, Long salesTeamId, Long salesHierId,List<Integer> triggerIdList, Short tenantId);

	List<Object[]> getMetricExecConfig(Long alignmentId, Long businessUnitId,
			Long salesTeamId, Long salesHierId, List<Integer> metricIds,
			List<Integer> triggerIdList, Short tenantId);

	List<Object[]> findAllTMtrExecConfigByALBUST(Long alignmentId,
			Long businessUnitId, Long salesTeamId, Short tenantId);
}
