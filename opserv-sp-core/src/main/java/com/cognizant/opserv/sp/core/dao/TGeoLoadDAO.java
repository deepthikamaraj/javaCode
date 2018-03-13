package com.cognizant.opserv.sp.core.dao;

import java.util.Date;
import java.util.List;

import com.cognizant.opserv.sp.core.entity.TGeoLoad;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TGeoLoad DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TGeoLoadDAO {

	/**
	 * Stores a new TGeoLoad entity object in to the persistent store
	 * 
	 * @param tGeoLoad
	 *            TGeoLoad Entity object to be persisted
	 * @return tGeoLoad Persisted TGeoLoad object
	 */
	TGeoLoad createTGeoLoad(TGeoLoad tGeoLoad);

	/**
	 * Deletes a TGeoLoad entity object from the persistent store
	 * 
	 * @param tGeoLoad
	 *            TGeoLoad Entity object to be deleted
	 */
	void deleteTGeoLoad(Integer dsId);

	/**
	 * Updates a TGeoLoad entity object in to the persistent store
	 * 
	 * @param tGeoLoad
	 *            TGeoLoad Entity object to be updated
	 * @return tGeoLoad Persisted TGeoLoad object
	 */
	TGeoLoad updateTGeoLoad(TGeoLoad tGeoLoad);

	/**
	 * Retrieve an TGeoLoad object based on given dsId.
	 * 
	 * @param dsId
	 *            the primary key value of the TGeoLoad Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TGeoLoad findTGeoLoadById(Integer dsId);

	/**
	 * Retrieve TGeoLoad based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TGeoLoad> list of TGeoLoads if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TGeoLoad> findTGeoLoads(SearchFilter<TGeoLoad> searchFilter);

	/**
	 * Count TGeoLoad based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTGeoLoads(SearchFilter<TGeoLoad> searchFilter);
	
	List<Object[]> getStaticHierarchyLevelLoad(Date beforeDate, Date createDate,Short tenantId);

}
