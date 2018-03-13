package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TAlgmntSalesTeam;
import com.cognizant.opserv.sp.core.entity.TMtr;
import com.cognizant.opserv.sp.core.entity.TMtrCategory;
import com.cognizant.peg.core.common.SearchFilter;


/**
 * Interface represents API's of TMtr DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TMtrDAO {

	/**
	 * Stores a new TMtr entity object in to the persistent store
	 * 
	 * @param tMtr
	 *            TMtr Entity object to be persisted
	 * @return tMtr Persisted TMtr object
	 */
	TMtr createTMtr(TMtr tMtr);

	/**
	 * Deletes a TMtr entity object from the persistent store
	 * 
	 * @param tMtr
	 *            TMtr Entity object to be deleted
	 */
	void deleteTMtr(Integer mtrId);

	/**
	 * Updates a TMtr entity object in to the persistent store
	 * 
	 * @param tMtr
	 *            TMtr Entity object to be updated
	 * @return tMtr Persisted TMtr object
	 */
	TMtr updateTMtr(TMtr tMtr);

	/**
	 * Retrieve an TMtr object based on given mtrId.
	 * 
	 * @param mtrId
	 *            the primary key value of the TMtr Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TMtr findTMtrById(Integer mtrId);

	/**
	 * Retrieve TMtr based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TMtr> list of TMtrs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TMtr> findTMtrs(SearchFilter<TMtr> searchFilter);

	/**
	 * Count TMtr based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTMtrs(SearchFilter<TMtr> searchFilter);

	/**
	 * Retrieve TMtr based on given search criteria using JPA named Query.
	 * The search criteria is of TMtrCategory type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TMtr> list of TMtrs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TMtr> getTMtrsByTMtrCategory(SearchFilter<TMtrCategory> searchFilter);

	/**
	 * Count TMtr based on given search criteria using JPA named Query.
	 * The search criteria is of TMtrCategory type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTMtrsByTMtrCategory(SearchFilter<TMtrCategory> searchFilter);

	/**
	 * Retrieve TMtr based on given search criteria using JPA named Query.
	 * The search criteria is of TAlgmntSalesTeam type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TMtr> list of TMtrs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TMtr> getTMtrsByTAlgmntSalesTeam(SearchFilter<TAlgmntSalesTeam> searchFilter);

	/**
	 * Count TMtr based on given search criteria using JPA named Query.
	 * The search criteria is of TAlgmntSalesTeam type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTMtrsByTAlgmntSalesTeam(SearchFilter<TAlgmntSalesTeam> searchFilter);
	
	List<TMtr> findMetricsForHeat(String alignmentId,String salesTeamId,String bussUnitId,Character dataDotFlag,String hierarchyLevel);
	List<TMtr> findMetricsForWorkLoad(String alignmentId,String salesTeamId,String bussUnitId,String hierarchyLevel);
	
	List<TMtr> findTMtrByAlgmntID(Long alignmentId,Short tenantId);
	
	List<Object[]> findMetricsHeat(String metricName,Long salesPosId,Short tenantId);
	
	List<Object[]> findMetricsWorkLoad(Long salesPosId, Short tenantId);
	
	List<Object[]> findOnlyMetricsForHeat(Long salesPosId,Short tenantId);
	
	List<Object[]> findMetricsandCountFromSalesAandZip(Long salesPosId, String zip,Short tenantId);

	List<Object[]> findMetricsandCountFromSalesPosHierAndZip(Long salesPosId, String zip,Integer metricId, Short tenantId);
	
	List<Object[]> findValueZipFromSalesAandZipandMetricName(Long salesPosId, String zip,String metricName,Short tenantId);

	List<Object> getTMtrForBatch();
	List<TMtr> FindTMtrByIDs(Long algmntId,Long bussUnitId,Long salesTeamId,Long salesHierId, short tenantId);

	List<TMtr> findMetricsForCatAlBUSt(Long alignmentId,Long salesTeamId,Long bussUnitId,Integer catId,Short tenantId,Long salesHierId);

	List<TMtr> findMetricsForAlBUSt(Long alignmentId,Long salesTeamId,Long bussUnitId,Short tenantId, int index, int noOfResults);

	List<TMtr> findMetricsForAlBUSt(Long alignmentId,Long salesTeamId,Long bussUnitId,Short tenantId, Long salesHierId);
	
	List<TMtr> FindTMtrByMtrVal(int mtrId);
	
	List<TMtr> FindTMtrByABSIds(Long algmntId,Long bussUnitId,Long salesTeamId);
	
	List<TMtr> findTMtrByCtgID(int mtrCtgId,Long salesHierId, Short tenantId);
	
	List<TMtr> findTMtrByCtgID(int mtrCtgId,short tenantId);

	List<TMtr> getTMtrByCtgIDALBUST(int mtrCtgId, short tenantId, Long alId,Long buId, Long stId);

	List<Object[]> getTMtrCatByALBUSTSH(String localeId,short tenantId,Long alId,Long buId,Long stId,Long shId);

	List<TMtr> findAllTMtrsFrGIS(Long algmntId, Long bussUnitId, Long salesTeamId,
			Long salesHierId, short tenantId);
	
	List<Object[]> findMetricsandCountFromSalesPosHierAndZipFrMtrList(Long salesPosId, String zip, List<Integer> metricIdList, Short tenantId);

	List<Object[]> getMetricInfoByALBUSTCate(Long alId, Long buId, Long stId, Long shId, Integer mtrCategoryId,Short tenantId);

	List<Object[]> getMetricInfoByALBUST(Long alId, Long buId, Long stId,Long shId, Short tenantId);
	
	List<Object[]> getMetricInfoByALBUSTExprType(Long alId, Long buId,
			Long stId, Long shId, List<Character> exprTypes, Integer triggerId,
			Short tenantId);

	List<Object[]> findTMtrByMtrId(Integer mtrId, Short tenantId);
	
	List<Object[]> findTMtrByMtrIds(List<Integer> mtrIds, Short tenantId);

	List<Object[]> getMetricInfoByAL(Long alId, Short tenantId);
	
	
}
