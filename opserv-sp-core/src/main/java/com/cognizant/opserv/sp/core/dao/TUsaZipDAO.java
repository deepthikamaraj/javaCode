package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TUsaCounty;
import com.cognizant.opserv.sp.core.entity.TUsaZip;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TUsaZip DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TUsaZipDAO {

	/**
	 * Stores a new TUsaZip entity object in to the persistent store
	 * 
	 * @param tUsaZip
	 *            TUsaZip Entity object to be persisted
	 * @return tUsaZip Persisted TUsaZip object
	 */
	TUsaZip createTUsaZip(TUsaZip tUsaZip);

	/**
	 * Deletes a TUsaZip entity object from the persistent store
	 * 
	 * @param tUsaZip
	 *            TUsaZip Entity object to be deleted
	 */
	void deleteTUsaZip(Integer zipId);

	/**
	 * Updates a TUsaZip entity object in to the persistent store
	 * 
	 * @param tUsaZip
	 *            TUsaZip Entity object to be updated
	 * @return tUsaZip Persisted TUsaZip object
	 */
	TUsaZip updateTUsaZip(TUsaZip tUsaZip);

	/**
	 * Retrieve an TUsaZip object based on given zipId.
	 * 
	 * @param zipId
	 *            the primary key value of the TUsaZip Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TUsaZip findTUsaZipById(Integer zipId);

	/**
	 * Retrieve TUsaZip based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TUsaZip> list of TUsaZips if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TUsaZip> findTUsaZips(SearchFilter<TUsaZip> searchFilter);

	/**
	 * Count TUsaZip based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTUsaZips(SearchFilter<TUsaZip> searchFilter);

	/**
	 * Retrieve TUsaZip based on given search criteria using JPA named Query.
	 * The search criteria is of TUsaCounty type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TUsaZip> list of TUsaZips if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TUsaZip> getTUsaZipsByTUsaCounty(SearchFilter<TUsaCounty> searchFilter);

	/**
	 * Count TUsaZip based on given search criteria using JPA named Query.
	 * The search criteria is of TUsaCounty type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTUsaZipsByTUsaCounty(SearchFilter<TUsaCounty> searchFilter);
	
	List<String> getTUsaZips(String query);
	List<String> getTUsaZipsFromTenantDb(String query);
	List<Integer> getTUsaZipsMetric(String query);
	List<String> getTUsaZips_pointZipFlag(String zipcodes);
	List<String> getTUsaZip(List<String> AssignedZipcodes);



}
