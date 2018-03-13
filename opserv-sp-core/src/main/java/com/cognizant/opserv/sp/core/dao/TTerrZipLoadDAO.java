package com.cognizant.opserv.sp.core.dao;


import java.util.Date;
import java.util.List;

import com.cognizant.opserv.sp.core.entity.TTerrZipLoad;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TTerrZipLoad DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TTerrZipLoadDAO {

	/**
	 * Stores a new TTerrZipLoad entity object in to the persistent store
	 * 
	 * @param tTerrZipLoad
	 *            TTerrZipLoad Entity object to be persisted
	 * @return tTerrZipLoad Persisted TTerrZipLoad object
	 */
	TTerrZipLoad createTTerrZipLoad(TTerrZipLoad tTerrZipLoad);

	/**
	 * Deletes a TTerrZipLoad entity object from the persistent store
	 * 
	 * @param tTerrZipLoad
	 *            TTerrZipLoad Entity object to be deleted
	 */
	void deleteTTerrZipLoad(Integer dsId);

	/**
	 * Updates a TTerrZipLoad entity object in to the persistent store
	 * 
	 * @param tTerrZipLoad
	 *            TTerrZipLoad Entity object to be updated
	 * @return tTerrZipLoad Persisted TTerrZipLoad object
	 */
	TTerrZipLoad updateTTerrZipLoad(TTerrZipLoad tTerrZipLoad);

	/**
	 * Retrieve an TTerrZipLoad object based on given dsId.
	 * 
	 * @param dsId
	 *            the primary key value of the TTerrZipLoad Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TTerrZipLoad findTTerrZipLoadById(Integer dsId);

	/**
	 * Retrieve TTerrZipLoad based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TTerrZipLoad> list of TTerrZipLoads if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TTerrZipLoad> findTTerrZipLoads(SearchFilter<TTerrZipLoad> searchFilter);

	/**
	 * Count TTerrZipLoad based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTTerrZipLoads(SearchFilter<TTerrZipLoad> searchFilter);

	/**
	 * Retrieve TTerrZipLoad based on given search criteria using JPA named Query.
	 * The search criteria is of TLoadStatus type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TTerrZipLoad> list of TTerrZipLoads if it exists against given
	 *         criteria. Returns null if not found
	 */
	//List<TTerrZipLoad> getTTerrZipLoadsByTLoadStatus(SearchFilter<TLoadStatus> searchFilter);

	/**
	 * Count TTerrZipLoad based on given search criteria using JPA named Query.
	 * The search criteria is of TLoadStatus type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	//Object countTTerrZipLoadsByTLoadStatus(SearchFilter<TLoadStatus> searchFilter);
	
	List<TTerrZipLoad> findTTerrZipLoadCreatedAftrDate(final SearchFilter<TTerrZipLoad> searchFilter, Date beforeDate, Date createDate);

}
