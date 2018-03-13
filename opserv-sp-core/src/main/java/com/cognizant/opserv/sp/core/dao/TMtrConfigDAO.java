package com.cognizant.opserv.sp.core.dao;

import java.util.List;
import java.util.Set;

import com.cognizant.opserv.sp.core.entity.TAlgmntSalesHier;
import com.cognizant.opserv.sp.core.entity.TMtr;
import com.cognizant.opserv.sp.core.entity.TMtrConfig;
import com.cognizant.opserv.sp.core.entity.TMtrConfigId;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TMtrConfig DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TMtrConfigDAO {

	/**
	 * Stores a new TMtrConfig entity object in to the persistent store
	 * 
	 * @param tMtrConfig
	 *            TMtrConfig Entity object to be persisted
	 * @return tMtrConfig Persisted TMtrConfig object
	 */
	TMtrConfig createTMtrConfig(TMtrConfig tMtrConfig);

	/**
	 * Deletes a TMtrConfig entity object from the persistent store
	 * 
	 * @param tMtrConfig
	 *            TMtrConfig Entity object to be deleted
	 */
	void deleteTMtrConfig(TMtrConfigId tMtrConfigId);

	/**
	 * Updates a TMtrConfig entity object in to the persistent store
	 * 
	 * @param tMtrConfig
	 *            TMtrConfig Entity object to be updated
	 * @return tMtrConfig Persisted TMtrConfig object
	 */
	TMtrConfig updateTMtrConfig(TMtrConfig tMtrConfig);

	/**
	 * Retrieve an TMtrConfig object based on given TMtrConfigId.
	 * 
	 * @param tMtrConfigId
	 *            the primary key value of the TMtrConfig Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TMtrConfig findTMtrConfigById(TMtrConfigId tMtrConfigId);

	/**
	 * Retrieve TMtrConfig based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TMtrConfig> list of TMtrConfigs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TMtrConfig> findTMtrConfigs(SearchFilter<TMtrConfig> searchFilter);

	/**
	 * Count TMtrConfig based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTMtrConfigs(SearchFilter<TMtrConfig> searchFilter);

	/**
	 * Retrieve TMtrConfig based on given search criteria using JPA named Query.
	 * The search criteria is of TAlgmntSalesHier type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TMtrConfig> list of TMtrConfigs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TMtrConfig> getTMtrConfigsByTAlgmntSalesHier(SearchFilter<TAlgmntSalesHier> searchFilter);

	/**
	 * Count TMtrConfig based on given search criteria using JPA named Query.
	 * The search criteria is of TAlgmntSalesHier type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTMtrConfigsByTAlgmntSalesHier(SearchFilter<TAlgmntSalesHier> searchFilter);

	/**
	 * Retrieve TMtrConfig based on given search criteria using JPA named Query.
	 * The search criteria is of TMtr type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TMtrConfig> list of TMtrConfigs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TMtrConfig> getTMtrConfigsByTMtr(SearchFilter<TMtr> searchFilter);

	/**
	 * Count TMtrConfig based on given search criteria using JPA named Query.
	 * The search criteria is of TMtr type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTMtrConfigsByTMtr(SearchFilter<TMtr> searchFilter);
	
	List countTMtrConfigsByTMtrIdAndSalesHierId(Integer metricID,  Long SalesHierId);
	
	List<TMtrConfig> findTMtrConfigBySalesHierId(Long salesHier, Short tenantId);

	List<TMtrConfig> findTMtrConfigByMetrics(int category, Long algmntId,Long bussUnitId, Long salesTeamId, Long salesHierId, Short tenantId);
	
	List<TMtrConfig> findTMtrConfigByMetricsWitoutCategory(Long algmntId,Long bussUnitId, Long salesTeamId, Long salesHierId, Short tenantId);
	
	List<TMtrConfig> findTMtrConfigByMtrId(Integer metricID);

	List<Object[]> getConfiguredHierarchies(List<Long> sphIds, Set<Integer> mtrIds);

	List<Object[]> getCofiguredHierarchiesTriggersByMtrIDs(Set<Integer> mtrIds,	Short tenantId);

}
