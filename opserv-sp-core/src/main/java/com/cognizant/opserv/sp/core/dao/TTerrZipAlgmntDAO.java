package com.cognizant.opserv.sp.core.dao;

import java.util.List;
import java.util.Set;

import com.cognizant.opserv.sp.core.entity.TSalesPos;
import com.cognizant.opserv.sp.core.entity.TSalesPosGeoUnit;
import com.cognizant.opserv.sp.core.entity.TTerrZipAlgmnt;
import com.cognizant.opserv.sp.model.GeographyAlignment;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TTerrZipAlgmnt DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TTerrZipAlgmntDAO {

	/**
	 * Stores a new TTerrZipAlgmnt entity object in to the persistent store
	 * 
	 * @param tTerrZipAlgmnt
	 *            TTerrZipAlgmnt Entity object to be persisted
	 * @return tTerrZipAlgmnt Persisted TTerrZipAlgmnt object
	 */
	TTerrZipAlgmnt createTTerrZipAlgmnt(TTerrZipAlgmnt tTerrZipAlgmnt);

	/**
	 * Deletes a TTerrZipAlgmnt entity object from the persistent store
	 * 
	 * @param tTerrZipAlgmnt
	 *            TTerrZipAlgmnt Entity object to be deleted
	 */
	void deleteTTerrZipAlgmnt(Integer geoZipId);

	/**
	 * Updates a TTerrZipAlgmnt entity object in to the persistent store
	 * 
	 * @param tTerrZipAlgmnt
	 *            TTerrZipAlgmnt Entity object to be updated
	 * @return tTerrZipAlgmnt Persisted TTerrZipAlgmnt object
	 */
	TTerrZipAlgmnt updateTTerrZipAlgmnt(TTerrZipAlgmnt tTerrZipAlgmnt);

	/**
	 * Retrieve an TTerrZipAlgmnt object based on given geoZipId.
	 * 
	 * @param geoZipId
	 *            the primary key value of the TTerrZipAlgmnt Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TTerrZipAlgmnt findTTerrZipAlgmntById(Integer geoZipId);

	/**
	 * Retrieve TTerrZipAlgmnt based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TTerrZipAlgmnt> list of TTerrZipAlgmnts if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TTerrZipAlgmnt> findTTerrZipAlgmnts(SearchFilter<TTerrZipAlgmnt> searchFilter);

	/**
	 * Count TTerrZipAlgmnt based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTTerrZipAlgmnts(SearchFilter<TTerrZipAlgmnt> searchFilter);

	/**
	 * Retrieve TTerrZipAlgmnt based on given search criteria using JPA named Query.
	 * The search criteria is of TSalesPosGeoUnit type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TTerrZipAlgmnt> list of TTerrZipAlgmnts if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TTerrZipAlgmnt> getTTerrZipAlgmntsByTSalesPosGeoUnit(SearchFilter<TSalesPosGeoUnit> searchFilter);

	/**
	 * Count TTerrZipAlgmnt based on given search criteria using JPA named Query.
	 * The search criteria is of TSalesPosGeoUnit type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTTerrZipAlgmntsByTSalesPosGeoUnit(SearchFilter<TSalesPosGeoUnit> searchFilter);

	List<TTerrZipAlgmnt> getTTsalesPOS(String posCode, Short tenantId);
	
	List<TTerrZipAlgmnt> findTTerrZipAlgmntByIds(Long salesPosId,
			Long salesHierId, Short tenantId);
	
	List<TTerrZipAlgmnt> getTTerrZipAlgmntsByTSalesPos(SearchFilter<TSalesPos> searchFilter);
	
	List<TTerrZipAlgmnt>  findTTerrZipAlgmntByTenantId(Long salesPosId,Long salesHierId,Short tenantId);
	
	List<TTerrZipAlgmnt> FindTTerrZipAlgmntByPostalCd(SearchFilter<TTerrZipAlgmnt> searchFilter);
	
	List<String> FindTTZAForAssignedFlagByPostalCd(SearchFilter<TTerrZipAlgmnt> searchFilter);
	
	List<TTerrZipAlgmnt> FindTTerrZipAlgmntBySalesHier(SearchFilter<TTerrZipAlgmnt> searchFilter);

	Long countTTerrZipAlgmntsCount(Long salesPosId,Long salesHierId, Short tenantId);
	
	Long countTTerrZipAlgmntsCountForChild(Set<Long> childSp, Short tenantId);
	
	List<TTerrZipAlgmnt> getTTerrZipAlgmnt(Long saleHierId, Long salePosId,
			String postalCd, Short tenantId);

	List<TTerrZipAlgmnt> getTTerrZipAlgmntForComb(Long saleHierId, Long salePosId,
			String postalCd, Short tenantId);

	List<TTerrZipAlgmnt> getSalesPosByPostalCode(Long algmntId,
			Long bussUnitId, Long salesTeamId, List<String> zipCodes, Short tenantId);

	List<TTerrZipAlgmnt> getTTerrZipAlgmntbyGeoID(Long salesHierId,
			Long salesPosId, String postalCd, Integer geoids, Short tenantId);

	List<TTerrZipAlgmnt> getSalesPosId(List<String> postalCds, Short tenantId);

     List<TTerrZipAlgmnt> findzipCodeByparentID(Long alignId, Long bussID,
					Long salesteamID, List<Long> salesPosIDs, short tenantID);
     
    List<TTerrZipAlgmnt> findTTerrZipAlgmntBySpShIdList(List<Long> spIdList, List<Long> shIdList, Short tenantId);

	List<TTerrZipAlgmnt> getTTsalesPOSForHeatMap(List<String> zipid, Short tenantId);

	List<TTerrZipAlgmnt> getTTsalesPOSFrDataDots(List<String> zipCodeList, Short tenantId);

	List<String> getPostalCodesForALBUSTSPS(Long alId, Long buId, Long stId,List<Long> salesPosIDs, short tenantID);

	List<TTerrZipAlgmnt> getActiveTTerrZipAlgmnt(Long salesHierId,
			Long salesPosId, String zip, Integer geoids, Short tenantId);

	List<TTerrZipAlgmnt> getActiveTTerrZipAlgmntByZipCode(String zip,
			Short tenantId);

	Long findTTerrZipAlgmntsByTSalesPosCount(TSalesPos tSalesPos, Short tenantId);

	
	List<Object[]> getActiveZipCodeFrSP(List<Long> salesPosId, List<Long> salesHierId, Short tenantId, Integer startInd, Integer numOfRows, String srchFld,String srchSPFld);
	
	List<String> getAllAssignedZipCdFrSp(Long salesPosId, Long salesHierId, Short tenantId );
	
	List<TTerrZipAlgmnt> getActiveTTerrZipAlgmntGeoId(Long saleHierId, Long salePosId,
			String postalCd, Short tenantId);
	
	List<TTerrZipAlgmnt> getTTerrZipAlgmntGeoId(Long saleHierId, Long salePosId,
			String postalCd, Short tenantId, Integer geoId);
	
	
	Long getActiveZipCodeFrSPCount(List<Long> salesPosId, List<Long> salesHierId, Short tenantId, Integer startInd, Integer numOfRows, String srchFld,String srchSPFld);

	List<String> getCheckFrBricksInTargetSP(List<Long> shId, List<Long> spId,
			List<String> postalCd, Short tenantId);

	Long fetchTheZipCountforSPFrAssociatedBricks(Long parentSalesPosId,
			Long parentSalesHierId, Short tenantId);

	List<Object[]> fetchShareTerrSPFrBrick(String brickCode, Short tenantId);
	
	List<Object[]> fetchBrickDetails(List<String> brickCode, Short tenantId);
	
	/**
	 * Find count of t ter zip algmnt for al bu st.
	 *
	 * @param algmntId the algmnt id
	 * @param bussUnitId the buss unit id
	 * @param salesTeamId the sales team id
	 * @param tenantId the tenant id
	 * @return the long
	 */
	Long findCountOfTTerZipAlgmntForAlBuSt1(Long algmntId, Long bussUnitId, Long salesTeamId, Short tenantId);
	
	Long findCountOfTTerZipAlgmntForAlBuSt(List<Long> salesPos, Short tenantId);
	
	List<TTerrZipAlgmnt> getActiveTTerrZipAlgmntByAlBUSTZip(Long alId, Long buId, Long stId, String postalCd, Short tenantId);

	List<TTerrZipAlgmnt> getAllPostalCodes(TTerrZipAlgmnt terrZipAlgmnt);
	
	List<TTerrZipAlgmnt> getAllGeographyAlignments(TTerrZipAlgmnt terrZipAlgmnt);

	/**
	 * @param changerRequestId
	 * @param tenantId
	 * @return List
	 */
	List<TTerrZipAlgmnt> fetchTTerrZipAlgmntByChangeRequest(Integer geoId, String postalCd, Long salePosId, Long saleHierId, Short tenantId);
	List<TTerrZipAlgmnt> getActiveTTerrZipAlgmntsBySPId(List<Long> salePosId, String postalCd,Short tenantId);
	List<TTerrZipAlgmnt> getTTerrZipAlgmntsByPostalCd(Long salesHierId,Long salesPosId, String postalCd,Short tenantId);
	List<TTerrZipAlgmnt> getActiveTTerrZipAlgmntList(Long saleHierId,Long salePosId,String postalCd, Short tenantId,Integer geoId);

	/**
	 * Gets the zip count for sp list.
	 *
	 * @param spIdList the sp id list
	 * @param tenantId the tenant id
	 * @return the zip count for sp list
	 */
	Long getZipCountForSpList(List<Long> spIdList, Short tenantId);

	List<TTerrZipAlgmnt> getActiveTTerrZipAlgmntList(Long saleHierId,
			Long salePosId, String postalCd, Short tenantId);

	List<Long> getActiveTTerrZipAlgmntsByPostalCdSalesPos(
			List<Long> salePosIds, String postalCd, Short tenantId);

	TTerrZipAlgmnt fetchSourceMirrorGeoAlgmnt(long spId, String code, Short tenantId);

	TTerrZipAlgmnt getTTerrZipAlgmnts(String postalCode,
			Integer geoZipId, long targetSPId, Short tenantId);
	
}
