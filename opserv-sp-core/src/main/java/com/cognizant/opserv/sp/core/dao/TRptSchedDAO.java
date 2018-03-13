package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TRptConfig;
import com.cognizant.opserv.sp.core.entity.TRptFreq;
import com.cognizant.opserv.sp.core.entity.TRptSched;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TRptSched DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TRptSchedDAO {

	/**
	 * Stores a new TRptSched entity object in to the persistent store
	 * 
	 * @param tRptSched
	 *            TRptSched Entity object to be persisted
	 * @return tRptSched Persisted TRptSched object
	 */
	TRptSched createTRptSched(TRptSched tRptSched);

	/**
	 * Deletes a TRptSched entity object from the persistent store
	 * 
	 * @param tRptSched
	 *            TRptSched Entity object to be deleted
	 */
	void deleteTRptSched(Integer tRptSchedId);

	/**
	 * Updates a TRptSched entity object in to the persistent store
	 * 
	 * @param tRptSched
	 *            TRptSched Entity object to be updated
	 * @return tRptSched Persisted TRptSched object
	 */
	TRptSched updateTRptSched(TRptSched tRptSched);

	/**
	 * Retrieve an TRptSched object based on given TRptSchedId.
	 * 
	 * @param tRptSchedId
	 *            the primary key value of the TRptSched Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TRptSched findTRptSchedById(Integer tRptSchedId);

	/**
	 * Retrieve TRptSched based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRptSched> list of TRptScheds if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TRptSched> findTRptScheds(SearchFilter<TRptSched> searchFilter);

	/**
	 * Count TRptSched based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTRptScheds(SearchFilter<TRptSched> searchFilter);

	/**
	 * Retrieve TRptSched based on given search criteria using JPA named Query.
	 * The search criteria is of TRptFreq type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRptSched> list of TRptScheds if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TRptSched> getTRptSchedsByTRptFreq(SearchFilter<TRptFreq> searchFilter);

	/**
	 * Count TRptSched based on given search criteria using JPA named Query.
	 * The search criteria is of TRptFreq type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTRptSchedsByTRptFreq(SearchFilter<TRptFreq> searchFilter);

	/**
	 * Retrieve TRptSched based on given search criteria using JPA named Query.
	 * The search criteria is of TRptConfig type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRptSched> list of TRptScheds if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TRptSched> getTRptSchedsByTRptConfig(SearchFilter<TRptConfig> searchFilter);

	/**
	 * Count TRptSched based on given search criteria using JPA named Query.
	 * The search criteria is of TRptConfig type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTRptSchedsByTRptConfig(SearchFilter<TRptConfig> searchFilter);
	List<TRptSched> loadAllTRptScheds(Short tenantID);

}
