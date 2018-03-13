package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TGeoHierList;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TGeoHierList DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TGeoHierListDAO {

	/**
	 * Stores a new TGeoHierList entity object in to the persistent store
	 * 
	 * @param tGeoHierList
	 *            TGeoHierList Entity object to be persisted
	 * @return tGeoHierList Persisted TGeoHierList object
	 */
	TGeoHierList createTGeoHierList(TGeoHierList tGeoHierList);

	/**
	 * Deletes a TGeoHierList entity object from the persistent store
	 * 
	 * @param tGeoHierList
	 *            TGeoHierList Entity object to be deleted
	 */
	void deleteTGeoHierList(Integer geoHierId);

	/**
	 * Updates a TGeoHierList entity object in to the persistent store
	 * 
	 * @param tGeoHierList
	 *            TGeoHierList Entity object to be updated
	 * @return tGeoHierList Persisted TGeoHierList object
	 */
	TGeoHierList updateTGeoHierList(TGeoHierList tGeoHierList);

	/**
	 * Retrieve an TGeoHierList object based on given geoHierId.
	 * 
	 * @param geoHierId
	 *            the primary key value of the TGeoHierList Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TGeoHierList findTGeoHierListById(Integer geoHierId);

	/**
	 * Retrieve TGeoHierList based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TGeoHierList> list of TGeoHierLists if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TGeoHierList> findTGeoHierLists(SearchFilter<TGeoHierList> searchFilter);

	/**
	 * Count TGeoHierList based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTGeoHierLists(SearchFilter<TGeoHierList> searchFilter);

	List<Object[]> getGeoHierLevels(Short tenantId, Character flag);

	boolean isLevelNameExists(String levelName, Short tenantId, Character flag);	

}
