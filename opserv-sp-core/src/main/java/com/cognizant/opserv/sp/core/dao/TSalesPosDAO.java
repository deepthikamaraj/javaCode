package com.cognizant.opserv.sp.core.dao;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.cognizant.opserv.sp.core.entity.TAlgmntSalesHier;
import com.cognizant.opserv.sp.core.entity.TAlgmntSalesTeam;
import com.cognizant.opserv.sp.core.entity.TSalesPos;
import com.cognizant.opserv.sp.core.entity.TTerrZipAlgmnt;
import com.cognizant.opserv.sp.core.entity.TWkflwStatus;
import com.cognizant.peg.core.common.ISearchCriteria;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TSalesPos DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TSalesPosDAO {

	/**
	 * Stores a new TSalesPos entity object in to the persistent store
	 * 
	 * @param tSalesPos
	 *            TSalesPos Entity object to be persisted
	 * @return tSalesPos Persisted TSalesPos object
	 */
	TSalesPos createTSalesPos(TSalesPos tSalesPos);

	/**
	 * Deletes a TSalesPos entity object from the persistent store
	 * 
	 * @param tSalesPos
	 *            TSalesPos Entity object to be deleted
	 */
	void deleteTSalesPos(Long tSalesPosId);

	/**
	 * Updates a TSalesPos entity object in to the persistent store
	 * 
	 * @param tSalesPos
	 *            TSalesPos Entity object to be updated
	 * @return tSalesPos Persisted TSalesPos object
	 */
	TSalesPos updateTSalesPos(TSalesPos tSalesPos);

	/**
	 * Retrieve an TSalesPos object based on given TSalesPosId.
	 * 
	 * @param tSalesPosId
	 *            the primary key value of the TSalesPos Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TSalesPos findTSalesPosById(Long tSalesPosId);

	/**
	 * Retrieve TSalesPos based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TSalesPos> list of TSalesPoss if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TSalesPos> findTSalesPoss(SearchFilter<TSalesPos> searchFilter);

	/**
	 * Count TSalesPos based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTSalesPoss(SearchFilter<TSalesPos> searchFilter);

	/**
	 * Retrieve TSalesPos based on given search criteria using JPA named Query.
	 * The search criteria is of TAlgmntSalesHier type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TSalesPos> list of TSalesPoss if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TSalesPos> getTSalesPossByTAlgmntSalesHier(SearchFilter<TAlgmntSalesHier> searchFilter);

	/**
	 * Count TSalesPos based on given search criteria using JPA named Query.
	 * The search criteria is of TAlgmntSalesHier type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTSalesPossByTAlgmntSalesHier(SearchFilter<TAlgmntSalesHier> searchFilter);

	/**
	 * Retrieve TSalesPos based on given search criteria using JPA named Query.
	 * The search criteria is of TAlgmntSalesTeam type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TSalesPos> list of TSalesPoss if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TSalesPos> getTSalesPossByTAlgmntSalesTeam(SearchFilter<TAlgmntSalesTeam> searchFilter);

	/**
	 * Count TSalesPos based on given search criteria using JPA named Query.
	 * The search criteria is of TAlgmntSalesTeam type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTSalesPossByTAlgmntSalesTeam(SearchFilter<TAlgmntSalesTeam> searchFilter);

	/**
	 * Retrieve TSalesPos based on given search criteria using JPA named Query.
	 * The search criteria is of TWkflwStatus type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TSalesPos> list of TSalesPoss if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TSalesPos> getTSalesPossByTWkflwStatus(SearchFilter<TWkflwStatus> searchFilter);

	/**
	 * Count TSalesPos based on given search criteria using JPA named Query.
	 * The search criteria is of TWkflwStatus type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	List<TSalesPos> FindTSalesPosByPodTitle(SearchFilter<TSalesPos> searchFilter);
	
	List<TSalesPos> FindTSalesPosBySalesHierId(SearchFilter<TSalesPos> searchFilter);
	
	Object countTSalesPossByTWkflwStatus(SearchFilter<TWkflwStatus> searchFilter);

	void updateTSalesPossByID(String query);

	List<String> findTSalesPossPolygon(String salesPosId);

	List<String> findTSalesPosPOD(String filter,short tenantId);	
	
	List<String> findTSalesPosPODForSF(String filter1, String filter2 ,String filter3, short tenantId);	
	
	List<String> findTSalesPosPODForSFFilter(String filter1, String filter2 , short tenantId);
	
	List<BigInteger> findTSalesPosPODParent(String filter);
	
	List<String> findTSalesPosPODByPRNID(long filter);		
	
	 List<String> findTSalesPosByTitlePod(final SearchFilter<TSalesPos> searchFilter);	
	 Long findMaxSalesPosId(final SearchFilter<TSalesPos> searchFilter);
	 TSalesPos getTSalesPosByTSalesPosId(Long tSalesPosId);	
	List<Object> getParentIdsForSp(Long salesPosId,Long salesHierId);
	void updateWithPolygon(TSalesPos tPos);
	List<Object[]> getChildrenById(List paramList, Short tenantId);	
	
	List<TSalesPos> createSalesPosTree(Long algmntId, Long bussUnitId, Long salesTeamId,Short tenantId);
	
	TSalesPos getTSalesPosByTSalesPosIdandTenantId(Long tSalesPosId,short tenantId);
	TSalesPos findTSalesPosBySalesPosId(Long salesPosId,Short usrTenantId);
	List<TSalesPos> findTSalesPosBySalesHierId(Long algmntId, Long bussUnitId, Long salesTeamId, Long salesHierId,Short usrTenantId);
	
	List<TSalesPos> findHierValuesByIDs(Long salesHierId,Long algmntId,Long bussUnitId,Long salesTeamId);
List<TSalesPos> findTSalesPositionByPrnSalesHerId(Long salesHierId,Short tenantId );
	
	List<Object[]> getChildrenBySPHId(List paramList,final Short tenantId);
	
	List<Object[]> searchChildren(List spIds,String searchValue,String searchCode, Short tenantId);	
	
	List<Object[]> getSpChildrenById(List paramList,final Short tenantId);
	
	List<Object []> findChildSalesPositions(List paramList);
	
	List<Object[]> findTSalesPosWhichisActive(SearchFilter<TSalesPos> searchFilter);
	
	List<Object[]> findTSalesPosWhichisActiveChild(SearchFilter<TSalesPos> searchFilter);
		
	List<Object[]> getTSalesPossByTSalesPosAndEmp(SearchFilter<TSalesPos> searchFilter);	

	Long maxOfTSalesPoss();
	
	List<Object> fetchParentIdsForSp(Long salesPosId,Long salesHierId,Short tenantId);

	List<Object> uniqueChildsalesHierID(Long prnSalesHierId,final Short tenantId);
	
	List<TSalesPos> findChildSalesPositionsBySPId(Long spId,Long spHierId,Long algmntId,Long buId,Long stId,final Short tenantId);
	
	List<TSalesPos> findSalesPositionsByALBUST(Long spId,Long spHierId,Long algmntId,Long buId,Long stId,Short tenantId);

		public Long getSpIDByPosCd(List paramList);

	List<Object[]> getActiveChildrenById(List paramList, Short tenantId);
	
	List<Object[]> getSpIdsByPosName(String posName, Short tenantId);
	
	List<Object[]> getSpIdsByPosCode(String posCode, Short tenantId);
	
	List<Object[]> fetchParentForSp(Long salesposId, Long saleshierId,
				Long algmntId, Long bussUnitId, Long salesTeamId, Short tenantId);
				
	List<Object[]> getActiveChildrenSPById(List paramList,final Short tenantId);
	
	List<Object[]> getActiveSalesPosNHierByALBUST(Long algmntId,
			Long bussUnitId, Long salesTeamId, Short tenantId);
	
	
	List<Object[]> findSalesPositionsTreeDetails(Set<Long> spId,Short tenantId);
	
	List<Object[]> getSalesPosMtrVals(List paramList);
	
	List<Object[]> getCompareSalesPosMtrVals(List paramList);

	List<Object[]> getActiveChildrenForReportsBySalPosId(List queryParam);
	
	/**
	 * This DAO method fetches date vlaue of SP by SpId from DB Function
	 * @param salesPosId
	 * @return SP EffEnd Date
	 */
	public List<Date> findSPEffEndDt(long spId);	
	
	List<TSalesPos> FindAllActiveTSalesPoss(Long algmntId,Date date ,Short tenantId);

	List<Object[]> getActiveCDSBySalPosId(List queryParam);

	List<TSalesPos> getAllInActiveTSalesPoss(Date date, Short tenantId,Character activeFlag);

	TSalesPos findSalesPositionBySalesPosId(Long salesPosId, Short tenantId);
	
	public List<TSalesPos> findTSalesPosBySalesHierIdFrMtr(Long algmntId, Long bussUnitId, Long salesTeamId, Long salesHierId,Short usrTenantId);

	List<TSalesPos> findTSalesPosBySalesHierIdFrGoTo(Long algmntId, Long bussUnitId, Long salesTeamId, Long salesHierId,Short usrTenantId, Long salesPosId);
	
	Object[] findSPDetails(Long salesPosId, Short tenantId) ;
	
	List<Object[]> findSPParentDetails(List<Object> queryParam);

	/**
	 * To find the sales position name 
	 * @param spId
	 * @param  spHierId
	 * @param  algmntId
	 * @param  buId
	 * @param  stId
	 * @param  tenantId
	 *  @return a String if it exists. Returns null of
	 *         not found
	 */
	 String findSpNameBySPId(Long spId, Long spHierId, Short tenantId);
	String findSpNameByALBUST(Long spId, Long spHierId, Long algmntId,
			Long buId, Long stId, Short tenantId);
	
	TSalesPos findTSalesPosBySPId(Long salesPosId,Short tenantId);
	
	List<Object[]> findTSalesPosEffDates(Long spId, Long spHierId, Short tenantId);

	Long findPrnSalesHierIdByALBUST(Long alginmentId, Long buId, Long stId,	Short tenantId);

	List<Object[]> findFullSPDetails(Long spId, Short tenantId);

	List<Object[]> getSPNameAndParentSPId(Long spId, Short tenantId);

	Long findTSalesPositionByPrnSalesHerIdCount(Long salesHierId, Short tenantId);

	List<Object[]> findActiveSPForActAlgmnt(Date effEdDt, Short tenantId);
	
	/**
	 * To find the sales position name, start date and end date 
	 * @param salePosId
	 * @param  saleHierId
	 * @param  tenantId
	 *  @return List<Object[]> 
	 */
	List<Object[]> findSpNameAndDates(Long salePosId, Long saleHierId,Short tenantId);
	public List<TSalesPos> createSalesPosTree(Long salesPosId, Long salesHierId, Short tenantId);
	
	/**
	 * To find the sales position by name.
	 *
	 * @param name the name
	 * @param tenantId the tenant id
	 * @param alignmentId the alignment id
	 * @param buId the bu id
	 * @param stId the st id
	 * @return List<TSalesPos>
	 */
	 List<TSalesPos> getAllSalesPositionByName(String name, short tenantId, long alignmentId, long buId,long stId);
	
	/**
	 * Find all s po by albust.
	 *
	 * @param spHierId the sp hier id
	 * @param algmntId the algmnt id
	 * @param buId the bu id
	 * @param stId the st id
	 * @param tenantId the tenant id
	 * @return the list
	 */
	 List<TSalesPos> findAllSPoByALBUST( long algmntId, long buId, long stId, short tenantId);
	 
	 /**
 	 * Gets the mirrored sales positions.
 	 *
 	 * @param salesPosId the sales pos id
 	 * @param tenantId the tenant id
 	 * @return the mirrored sales positions
 	 */
 	List<TSalesPos> getMirroredSalesPositions(long salesPosId, short tenantId);
 	
 	/**
 	 * Gets Sales Position Details
 	 * @param terrZipAlgmnt
 	 * @return List<TSalesPos>
 	 */
 	List<TSalesPos> getSalesPosDetails(TTerrZipAlgmnt terrZipAlgmnt);

	/**
	 * Check if SP is active.
	 *
	 * @param salesPosId the sales position id
	 * @param tenantId the tenantId
	 * @return the count
	 */
 	List<Object> checkIfActiveSP(long salesPosId, short tenantId);

 	
	 /**
 	 * Find t sales pos by search criteria.
 	 *
 	 * @param criteria the criteria
 	 * @return the list
 	 */
 	List<TSalesPos> findTSalesPosBySearchCriteria(ISearchCriteria criteria);
		
	/**
	 * Check if SP is active.
	 *
	 * @param salesPosId the sales position id
	 * @param tenantId the tenantId
	 * @return the count
	 */
 	Long countOfBaseSPFrST(Long algmntId, Long bussUnitId, Long salesTeamId, Short tenantId);

	/**
	 * Fetch sp names fr sp ids.
	 *
	 * @param spIdList the sp id list
	 * @param tenantId the tenant id
	 * @return the list
	 */
	List<String> fetchSpNamesFrSpIds(List<Long> spIdList, Short tenantId);

	List<Long> getChildSPs(Set<Long> salesPosIds, Short tenantId);
	
	/**
	 * Find t sales position by id.
	 *
	 * @param spId the sp id
	 * @param userDetails the user details
	 * @return the list
	 */
	List<Object[]> findTSalesPositionById(long spId, Short tenantId);
	
	/**
	 * To get salespostion name and code for a person
	 * @param staffId
	 * @param tenantId
	 * @return
	 */
	List<Object[]> findSPNameAndCodeForEmp(Integer staffId, Short tenantId);
}
