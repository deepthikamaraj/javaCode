package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TRpt;
import com.cognizant.opserv.sp.core.entity.TRptDoc;
import com.cognizant.opserv.sp.core.entity.TRptDocId;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TRptDoc DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TRptDocDAO {

	/**
	 * Stores a new TRptDoc entity object in to the persistent store
	 * 
	 * @param tRptDoc
	 *            TRptDoc Entity object to be persisted
	 * @return tRptDoc Persisted TRptDoc object
	 */
	TRptDoc createTRptDoc(TRptDoc tRptDoc);

	/**
	 * Deletes a TRptDoc entity object from the persistent store
	 * 
	 * @param tRptDoc
	 *            TRptDoc Entity object to be deleted
	 */
	void deleteTRptDoc(TRptDocId tRptDocId);

	/**
	 * Updates a TRptDoc entity object in to the persistent store
	 * 
	 * @param tRptDoc
	 *            TRptDoc Entity object to be updated
	 * @return tRptDoc Persisted TRptDoc object
	 */
	TRptDoc updateTRptDoc(TRptDoc tRptDoc);

	/**
	 * Retrieve an TRptDoc object based on given TRptDocId.
	 * 
	 * @param tRptDocId
	 *            the primary key value of the TRptDoc Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TRptDoc findTRptDocById(TRptDocId tRptDocId);

	/**
	 * Retrieve TRptDoc based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRptDoc> list of TRptDocs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TRptDoc> findTRptDocs(SearchFilter<TRptDoc> searchFilter);

	/**
	 * Count TRptDoc based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTRptDocs(SearchFilter<TRptDoc> searchFilter);

	/**
	 * Retrieve TRptDoc based on given search criteria using JPA named Query.
	 * The search criteria is of TRpt type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRptDoc> list of TRptDocs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TRptDoc> getTRptDocsByTRpt(SearchFilter<TRpt> searchFilter);

	/**
	 * Count TRptDoc based on given search criteria using JPA named Query.
	 * The search criteria is of TRpt type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTRptDocsByTRpt(SearchFilter<TRpt> searchFilter);

}
