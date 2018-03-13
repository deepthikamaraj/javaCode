package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TSalesPosGeoUnit;
import com.cognizant.opserv.sp.core.entity.TSalesPosGeoUnitHist;
import com.cognizant.opserv.sp.core.entity.TSalesPosGeoUnitHistId;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TSalesPosGeoUnitHist DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TSalesPosGeoUnitHistDAO {

	/**
	 * Stores a new TSalesPosGeoUnitHist entity object in to the persistent store
	 * 
	 * @param tSalesPosGeoUnitHist
	 *            TSalesPosGeoUnitHist Entity object to be persisted
	 * @return tSalesPosGeoUnitHist Persisted TSalesPosGeoUnitHist object
	 */
	TSalesPosGeoUnitHist createTSalesPosGeoUnitHist(TSalesPosGeoUnitHist tSalesPosGeoUnitHist);

	/**
	 * Deletes a TSalesPosGeoUnitHist entity object from the persistent store
	 * 
	 * @param tSalesPosGeoUnitHist
	 *            TSalesPosGeoUnitHist Entity object to be deleted
	 */
	void deleteTSalesPosGeoUnitHist(TSalesPosGeoUnitHistId tSalesPosGeoUnitHistId);

	/**
	 * Updates a TSalesPosGeoUnitHist entity object in to the persistent store
	 * 
	 * @param tSalesPosGeoUnitHist
	 *            TSalesPosGeoUnitHist Entity object to be updated
	 * @return tSalesPosGeoUnitHist Persisted TSalesPosGeoUnitHist object
	 */
	TSalesPosGeoUnitHist updateTSalesPosGeoUnitHist(TSalesPosGeoUnitHist tSalesPosGeoUnitHist);

	/**
	 * Retrieve an TSalesPosGeoUnitHist object based on given TSalesPosGeoUnitHistId.
	 * 
	 * @param tSalesPosGeoUnitHistId
	 *            the primary key value of the TSalesPosGeoUnitHist Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TSalesPosGeoUnitHist findTSalesPosGeoUnitHistById(TSalesPosGeoUnitHistId tSalesPosGeoUnitHistId);

	/**
	 * Retrieve TSalesPosGeoUnitHist based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TSalesPosGeoUnitHist> list of TSalesPosGeoUnitHists if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TSalesPosGeoUnitHist> findTSalesPosGeoUnitHists(SearchFilter<TSalesPosGeoUnitHist> searchFilter);

	/**
	 * Count TSalesPosGeoUnitHist based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTSalesPosGeoUnitHists(SearchFilter<TSalesPosGeoUnitHist> searchFilter);

	/**
	 * Retrieve TSalesPosGeoUnitHist based on given search criteria using JPA named Query.
	 * The search criteria is of TSalesPosGeoUnit type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TSalesPosGeoUnitHist> list of TSalesPosGeoUnitHists if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TSalesPosGeoUnitHist> getTSalesPosGeoUnitHistsByTSalesPosGeoUnit(SearchFilter<TSalesPosGeoUnit> searchFilter);

	/**
	 * Count TSalesPosGeoUnitHist based on given search criteria using JPA named Query.
	 * The search criteria is of TSalesPosGeoUnit type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTSalesPosGeoUnitHistsByTSalesPosGeoUnit(SearchFilter<TSalesPosGeoUnit> searchFilter);

}
