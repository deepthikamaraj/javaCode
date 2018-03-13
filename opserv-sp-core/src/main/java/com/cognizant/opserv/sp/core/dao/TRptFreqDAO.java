package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TRptFreq;
import com.cognizant.opserv.sp.core.entity.TRptFreqId;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TRptFreq DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TRptFreqDAO {

	/**
	 * Stores a new TRptFreq entity object in to the persistent store
	 * 
	 * @param tRptFreq
	 *            TRptFreq Entity object to be persisted
	 * @return tRptFreq Persisted TRptFreq object
	 */
	TRptFreq createTRptFreq(TRptFreq tRptFreq);

	/**
	 * Deletes a TRptFreq entity object from the persistent store
	 * 
	 * @param tRptFreq
	 *            TRptFreq Entity object to be deleted
	 */
	void deleteTRptFreq(Integer rptFreqId);

	/**
	 * Updates a TRptFreq entity object in to the persistent store
	 * 
	 * @param tRptFreq
	 *            TRptFreq Entity object to be updated
	 * @return tRptFreq Persisted TRptFreq object
	 */
	TRptFreq updateTRptFreq(TRptFreq tRptFreq);

	/**
	 * Retrieve an TRptFreq object based on given rptFreqId.
	 * 
	 * @param rptFreqId
	 *            the primary key value of the TRptFreq Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TRptFreq findTRptFreqById(TRptFreqId rptFreqId);

	/**
	 * Retrieve TRptFreq based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRptFreq> list of TRptFreqs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TRptFreq> findTRptFreqs(SearchFilter<TRptFreq> searchFilter);

	/**
	 * Count TRptFreq based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTRptFreqs(SearchFilter<TRptFreq> searchFilter);

}
