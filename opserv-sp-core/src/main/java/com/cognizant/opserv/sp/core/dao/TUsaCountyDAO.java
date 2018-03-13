package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TUsaCounty;
import com.cognizant.opserv.sp.core.entity.TUsaState;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TUsaCounty DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TUsaCountyDAO {

	/**
	 * Stores a new TUsaCounty entity object in to the persistent store
	 * 
	 * @param tUsaCounty
	 *            TUsaCounty Entity object to be persisted
	 * @return tUsaCounty Persisted TUsaCounty object
	 */
	TUsaCounty createTUsaCounty(TUsaCounty tUsaCounty);

	/**
	 * Deletes a TUsaCounty entity object from the persistent store
	 * 
	 * @param tUsaCounty
	 *            TUsaCounty Entity object to be deleted
	 */
	void deleteTUsaCounty(Integer countyId);

	/**
	 * Updates a TUsaCounty entity object in to the persistent store
	 * 
	 * @param tUsaCounty
	 *            TUsaCounty Entity object to be updated
	 * @return tUsaCounty Persisted TUsaCounty object
	 */
	TUsaCounty updateTUsaCounty(TUsaCounty tUsaCounty);

	/**
	 * Retrieve an TUsaCounty object based on given countyId.
	 * 
	 * @param countyId
	 *            the primary key value of the TUsaCounty Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TUsaCounty findTUsaCountyById(Integer countyId);

	/**
	 * Retrieve TUsaCounty based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TUsaCounty> list of TUsaCountys if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TUsaCounty> findTUsaCountys(SearchFilter<TUsaCounty> searchFilter);

	/**
	 * Count TUsaCounty based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTUsaCountys(SearchFilter<TUsaCounty> searchFilter);

	/**
	 * Retrieve TUsaCounty based on given search criteria using JPA named Query.
	 * The search criteria is of TUsaState type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TUsaCounty> list of TUsaCountys if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TUsaCounty> getTUsaCountysByTUsaState(SearchFilter<TUsaState> searchFilter);

	/**
	 * Count TUsaCounty based on given search criteria using JPA named Query.
	 * The search criteria is of TUsaState type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTUsaCountysByTUsaState(SearchFilter<TUsaState> searchFilter);
	
	List<String> getTUsaCounty();

}
