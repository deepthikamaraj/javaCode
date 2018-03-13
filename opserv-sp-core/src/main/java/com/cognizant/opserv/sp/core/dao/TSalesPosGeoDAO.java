package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TSalesPosGeo;
import com.cognizant.opserv.sp.core.entity.TSalesPosGeoId;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TSalesPosGeo DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TSalesPosGeoDAO {

	/**
	 * Stores a new TSalesPosGeo entity object in to the persistent store
	 * 
	 * @param tSalesPosGeo
	 *            TSalesPosGeo Entity object to be persisted
	 * @return tSalesPosGeo Persisted TSalesPosGeo object
	 */
	TSalesPosGeo createTSalesPosGeo(TSalesPosGeo tSalesPosGeo);

	/**
	 * Deletes a TSalesPosGeo entity object from the persistent store
	 * 
	 * @param tSalesPosGeo
	 *            TSalesPosGeo Entity object to be deleted
	 */
	void deleteTSalesPosGeo(TSalesPosGeoId tSalesPosGeoId);

	/**
	 * Updates a TSalesPosGeo entity object in to the persistent store
	 * 
	 * @param tSalesPosGeo
	 *            TSalesPosGeo Entity object to be updated
	 * @return tSalesPosGeo Persisted TSalesPosGeo object
	 */
	TSalesPosGeo updateTSalesPosGeo(TSalesPosGeo tSalesPosGeo);

	/**
	 * Retrieve an TSalesPosGeo object based on given TSalesPosGeoId.
	 * 
	 * @param tSalesPosGeoId
	 *            the primary key value of the TSalesPosGeo Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TSalesPosGeo findTSalesPosGeoById(TSalesPosGeoId tSalesPosGeoId);

	/**
	 * Retrieve TSalesPosGeo based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TSalesPosGeo> list of TSalesPosGeos if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TSalesPosGeo> findTSalesPosGeos(SearchFilter<TSalesPosGeo> searchFilter);

	/**
	 * Count TSalesPosGeo based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTSalesPosGeos(SearchFilter<TSalesPosGeo> searchFilter);

	Long countOfTSalesPosGeos(List paramList);
    Integer updateTSalesPosGeoByNative(String query); 
    String getPolygonForParent(String query);
    List<String> getPolygon(String query);

//	List<Object[]> getTSalesPosGeo(String sqlQueryForSPoly);

    List<Object[]> getTSalesPosGeo(Long salesHierId,List<Long> spList);
    
    String isShapePolygonContinousProc(Long salesPosId, Long salesHierId, String zipList, String operation, String lowestTableName, Integer userId);
    String updateChildParentShapePolygonProc(Long salesPosId, Long salesHierId, String zipList, String zipAssignedList, String flagAssignOrUnassign, String lowestTableName, Integer userId);
}
