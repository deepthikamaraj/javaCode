package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TCountry;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TCountry DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TCountryDAO {

	/**
	 * Stores a new TCountry entity object in to the persistent store
	 * 
	 * @param tCountry
	 *            TCountry Entity object to be persisted
	 * @return tCountry Persisted TCountry object
	 */
	TCountry createTCountry(TCountry tCountry);

	/**
	 * Deletes a TCountry entity object from the persistent store
	 * 
	 * @param tCountry
	 *            TCountry Entity object to be deleted
	 */
	void deleteTCountry(Integer countryId);

	/**
	 * Updates a TCountry entity object in to the persistent store
	 * 
	 * @param tCountry
	 *            TCountry Entity object to be updated
	 * @return tCountry Persisted TCountry object
	 */
	TCountry updateTCountry(TCountry tCountry);

	/**
	 * Retrieve an TCountry object based on given countryId.
	 * 
	 * @param countryId
	 *            the primary key value of the TCountry Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TCountry findTCountryById(Integer countryId);

	/**
	 * Retrieve TCountry based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCountry> list of TCountrys if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TCountry> findTCountrys(SearchFilter<TCountry> searchFilter);

	/**
	 * Count TCountry based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTCountrys(SearchFilter<TCountry> searchFilter);
	
	/**
	 * Find all country names.
	 *
	 * @return the list
	 */
	List<Object[]> findAllCountryNames();

	/**
	 * Gets the all country names by order.
	 *
	 * @return the all country names by order
	 */
	List<String> getAllCountryNamesByOrder();

}
