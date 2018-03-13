package com.cognizant.opserv.sp.core.dao;


import java.util.List;

import com.cognizant.opserv.sp.core.entity.TRangeConfig;
import com.cognizant.opserv.sp.core.entity.TValueRange;
import com.cognizant.opserv.sp.core.entity.TValueRangeId;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TValueRange DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TValueRangeDAO {

	/**
	 * Stores a new TValueRange entity object in to the persistent store
	 * 
	 * @param tValueRange
	 *            TValueRange Entity object to be persisted
	 * @return tValueRange Persisted TValueRange object
	 */
	TValueRange createTValueRange(TValueRange tValueRange);

	/**
	 * Deletes a TValueRange entity object from the persistent store
	 * 
	 * @param tValueRange
	 *            TValueRange Entity object to be deleted
	 */
	void deleteTValueRange(TValueRangeId tValueRangeId);

	/**
	 * Updates a TValueRange entity object in to the persistent store
	 * 
	 * @param tValueRange
	 *            TValueRange Entity object to be updated
	 * @return tValueRange Persisted TValueRange object
	 */
	TValueRange updateTValueRange(TValueRange tValueRange);

	/**
	 * Retrieve an TValueRange object based on given TValueRangeId.
	 * 
	 * @param tValueRangeId
	 *            the primary key value of the TValueRange Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TValueRange findTValueRangeById(TValueRangeId tValueRangeId);

	/**
	 * Retrieve TValueRange based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TValueRange> list of TValueRanges if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TValueRange> findTValueRanges(SearchFilter<TValueRange> searchFilter);

	/**
	 * Count TValueRange based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTValueRanges(SearchFilter<TValueRange> searchFilter);

	/**
	 * Retrieve TValueRange based on given search criteria using JPA named Query.
	 * The search criteria is of TRangeConfig type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TValueRange> list of TValueRanges if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TValueRange> getTValueRangesByTRangeConfig(SearchFilter<TRangeConfig> searchFilter);

	/**
	 * Count TValueRange based on given search criteria using JPA named Query.
	 * The search criteria is of TRangeConfig type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTValueRangesByTRangeConfig(SearchFilter<TRangeConfig> searchFilter);
	
	List<TValueRange> findTValueRangeByRangeId(int rangeId);

	List<TValueRange> FindTValueRangeByIdAndLevel(int rangeConfigId,
			int numLevel, Short tenantId);

}
