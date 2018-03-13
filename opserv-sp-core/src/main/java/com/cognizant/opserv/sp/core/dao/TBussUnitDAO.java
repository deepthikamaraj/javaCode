package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TBussUnit;
import com.cognizant.opserv.sp.core.entity.TOrg;
import com.cognizant.peg.core.common.ISearchCriteria;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TBussUnit DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TBussUnitDAO {

	/**
	 * Stores a new TBussUnit entity object in to the persistent store
	 * 
	 * @param tBussUnit
	 *            TBussUnit Entity object to be persisted
	 * @return tBussUnit Persisted TBussUnit object
	 */
	TBussUnit createTBussUnit(TBussUnit tBussUnit);

	/**
	 * Deletes a TBussUnit entity object from the persistent store
	 * 
	 * @param tBussUnit
	 *            TBussUnit Entity object to be deleted
	 */
	void deleteTBussUnit(Long bussUnitId);

	/**
	 * Updates a TBussUnit entity object in to the persistent store
	 * 
	 * @param tBussUnit
	 *            TBussUnit Entity object to be updated
	 * @return tBussUnit Persisted TBussUnit object
	 */
	TBussUnit updateTBussUnit(TBussUnit tBussUnit);

	/**
	 * Retrieve an TBussUnit object based on given bussUnitId.
	 * 
	 * @param bussUnitId
	 *            the primary key value of the TBussUnit Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TBussUnit findTBussUnitById(Long bussUnitId);

	/**
	 * Retrieve TBussUnit based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TBussUnit> list of TBussUnits if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TBussUnit> findTBussUnits(SearchFilter<TBussUnit> searchFilter);

	/**
	 * Count TBussUnit based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTBussUnits(SearchFilter<TBussUnit> searchFilter);

	/**
	 * Retrieve TBussUnit based on given search criteria using JPA named Query.
	 * The search criteria is of TOrg type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TBussUnit> list of TBussUnits if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TBussUnit> getTBussUnitsByTOrg(SearchFilter<TOrg> searchFilter);

	/**
	 * Count TBussUnit based on given search criteria using JPA named Query.
	 * The search criteria is of TOrg type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTBussUnitsByTOrg(SearchFilter<TOrg> searchFilter);
	
	/**
	 * Find t buss unitsby date.
	 *
	 * @param searchFilter the search filter
	 * @return the list
	 */
	List<TBussUnit> findTBussUnitsbyDate(SearchFilter<TBussUnit> searchFilter);
	
	/**
	 * Find all buss unit.
	 *
	 * @param tenantId the tenant id
	 * @return the list
	 */
	List<Object[]> findAllBussUnit(Short tenantId);

	/**
	 * Fetch buss unit names by ids.
	 *
	 * @param Ids the ids
	 * @param tenantId the tenant id
	 * @return the list
	 */
	List<String> fetchBussUnitNamesByIds(List<Long> Ids,Short tenantId);
	
	/**
	 * Find t buss unit by search criteria.
	 *
	 * @param criteria the criteria
	 * @return the list
	 */
	List<TBussUnit> findTBussUnitBySearchCriteria(ISearchCriteria criteria);
}
