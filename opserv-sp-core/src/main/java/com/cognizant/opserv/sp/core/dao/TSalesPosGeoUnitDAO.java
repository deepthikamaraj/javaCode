package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TSalesPosGeoUnit;
import com.cognizant.opserv.sp.core.entity.TSalesPosGeoUnitId;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TSalesPosGeoUnit DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TSalesPosGeoUnitDAO {

	/**
	 * Stores a new TSalesPosGeoUnit entity object in to the persistent store
	 * 
	 * @param tSalesPosGeoUnit
	 *            TSalesPosGeoUnit Entity object to be persisted
	 * @return tSalesPosGeoUnit Persisted TSalesPosGeoUnit object
	 */
	TSalesPosGeoUnit createTSalesPosGeoUnit(TSalesPosGeoUnit tSalesPosGeoUnit);

	/**
	 * Deletes a TSalesPosGeoUnit entity object from the persistent store
	 * 
	 * @param tSalesPosGeoUnit
	 *            TSalesPosGeoUnit Entity object to be deleted
	 */
	void deleteTSalesPosGeoUnit(TSalesPosGeoUnitId tSalesPosGeoUnitId);

	/**
	 * Updates a TSalesPosGeoUnit entity object in to the persistent store
	 * 
	 * @param tSalesPosGeoUnit
	 *            TSalesPosGeoUnit Entity object to be updated
	 * @return tSalesPosGeoUnit Persisted TSalesPosGeoUnit object
	 */
	TSalesPosGeoUnit updateTSalesPosGeoUnit(TSalesPosGeoUnit tSalesPosGeoUnit);

	/**
	 * Retrieve an TSalesPosGeoUnit object based on given TSalesPosGeoUnitId.
	 * 
	 * @param tSalesPosGeoUnitId
	 *            the primary key value of the TSalesPosGeoUnit Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TSalesPosGeoUnit findTSalesPosGeoUnitById(TSalesPosGeoUnitId tSalesPosGeoUnitId);

	/**
	 * Retrieve TSalesPosGeoUnit based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TSalesPosGeoUnit> list of TSalesPosGeoUnits if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TSalesPosGeoUnit> findTSalesPosGeoUnits(SearchFilter<TSalesPosGeoUnit> searchFilter);

	/**
	 * Count TSalesPosGeoUnit based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTSalesPosGeoUnits(SearchFilter<TSalesPosGeoUnit> searchFilter);

	List<TSalesPosGeoUnit> getExistingGeoIDs(Long algmntId, Long bussUnitId, Long salesTeamId, Long salesPosId, Short tenantId);
	List<Object> findZipByGeoId(Long algmntId, Long bussUnitId, Long salesTeamId,
			Long salehierId, Long salePosId, Integer geoId, Short tenantId);

	List<TSalesPosGeoUnit> findGeoIdFrSP(Long algmntId, Long bussUnitId, Long salesTeamId, Long salesHierId, Long salesPosId, Short tenantId);
	List<TSalesPosGeoUnit> getExistingGeoIDs(Long algmntId, Long bussUnitId, Long salesTeamId,List<Long> salesPosId,
			Short tenantId);
	Object getMaxGeoID(Short tenantId);
}
