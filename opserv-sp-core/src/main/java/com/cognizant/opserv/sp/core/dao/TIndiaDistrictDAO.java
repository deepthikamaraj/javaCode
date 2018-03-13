package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TIndiaDistrict;
import com.cognizant.opserv.sp.core.entity.TIndiaState;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TIndiaDistrict DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TIndiaDistrictDAO {

	/**
	 * Stores a new TIndiaDistrict entity object in to the persistent store
	 * 
	 * @param tIndiaDistrict
	 *            TIndiaDistrict Entity object to be persisted
	 * @return tIndiaDistrict Persisted TIndiaDistrict object
	 */
	TIndiaDistrict createTIndiaDistrict(TIndiaDistrict tIndiaDistrict);

	/**
	 * Deletes a TIndiaDistrict entity object from the persistent store
	 * 
	 * @param tIndiaDistrict
	 *            TIndiaDistrict Entity object to be deleted
	 */
	void deleteTIndiaDistrict(Integer districtId);

	/**
	 * Updates a TIndiaDistrict entity object in to the persistent store
	 * 
	 * @param tIndiaDistrict
	 *            TIndiaDistrict Entity object to be updated
	 * @return tIndiaDistrict Persisted TIndiaDistrict object
	 */
	TIndiaDistrict updateTIndiaDistrict(TIndiaDistrict tIndiaDistrict);

	/**
	 * Retrieve an TIndiaDistrict object based on given districtId.
	 * 
	 * @param districtId
	 *            the primary key value of the TIndiaDistrict Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TIndiaDistrict findTIndiaDistrictById(Integer districtId);

	/**
	 * Retrieve TIndiaDistrict based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TIndiaDistrict> list of TIndiaDistricts if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TIndiaDistrict> findTIndiaDistricts(SearchFilter<TIndiaDistrict> searchFilter);

	/**
	 * Count TIndiaDistrict based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTIndiaDistricts(SearchFilter<TIndiaDistrict> searchFilter);

	/**
	 * Retrieve TIndiaDistrict based on given search criteria using JPA named Query.
	 * The search criteria is of TIndiaState type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TIndiaDistrict> list of TIndiaDistricts if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TIndiaDistrict> getTIndiaDistrictsByTIndiaState(SearchFilter<TIndiaState> searchFilter);

	/**
	 * Count TIndiaDistrict based on given search criteria using JPA named Query.
	 * The search criteria is of TIndiaState type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTIndiaDistrictsByTIndiaState(SearchFilter<TIndiaState> searchFilter);
//added for themes to logical layers
	List<String> findIndiaDistrict();
}
