package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TIndiaPin;
import com.cognizant.opserv.sp.core.entity.TIndiaTaluk;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TIndiaPin DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TIndiaPinDAO {

	/**
	 * Stores a new TIndiaPin entity object in to the persistent store
	 * 
	 * @param tIndiaPin
	 *            TIndiaPin Entity object to be persisted
	 * @return tIndiaPin Persisted TIndiaPin object
	 */
	TIndiaPin createTIndiaPin(TIndiaPin tIndiaPin);

	/**
	 * Deletes a TIndiaPin entity object from the persistent store
	 * 
	 * @param tIndiaPin
	 *            TIndiaPin Entity object to be deleted
	 */
	void deleteTIndiaPin(String pinCd);

	/**
	 * Updates a TIndiaPin entity object in to the persistent store
	 * 
	 * @param tIndiaPin
	 *            TIndiaPin Entity object to be updated
	 * @return tIndiaPin Persisted TIndiaPin object
	 */
	TIndiaPin updateTIndiaPin(TIndiaPin tIndiaPin);

	/**
	 * Retrieve an TIndiaPin object based on given pinCd.
	 * 
	 * @param pinCd
	 *            the primary key value of the TIndiaPin Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TIndiaPin findTIndiaPinById(String pinCd);

	/**
	 * Retrieve TIndiaPin based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TIndiaPin> list of TIndiaPins if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TIndiaPin> findTIndiaPins(SearchFilter<TIndiaPin> searchFilter);

	/**
	 * Count TIndiaPin based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTIndiaPins(SearchFilter<TIndiaPin> searchFilter);

	/**
	 * Retrieve TIndiaPin based on given search criteria using JPA named Query.
	 * The search criteria is of TIndiaTaluk type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TIndiaPin> list of TIndiaPins if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TIndiaPin> getTIndiaPinsByTIndiaTaluk(SearchFilter<TIndiaTaluk> searchFilter);

	/**
	 * Count TIndiaPin based on given search criteria using JPA named Query.
	 * The search criteria is of TIndiaTaluk type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTIndiaPinsByTIndiaTaluk(SearchFilter<TIndiaTaluk> searchFilter);
	List<String> getTIndiaZips(String query);

}
