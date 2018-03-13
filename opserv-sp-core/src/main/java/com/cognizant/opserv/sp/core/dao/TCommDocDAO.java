package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TComm;
import com.cognizant.opserv.sp.core.entity.TCommDoc;
import com.cognizant.opserv.sp.core.entity.TCommDocId;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TCommDoc DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TCommDocDAO {

	/**
	 * Stores a new TCommDoc entity object in to the persistent store
	 * 
	 * @param tCommDoc
	 *            TCommDoc Entity object to be persisted
	 * @return tCommDoc Persisted TCommDoc object
	 */
	TCommDoc createTCommDoc(TCommDoc tCommDoc);

	/**
	 * Deletes a TCommDoc entity object from the persistent store
	 * 
	 * @param tCommDoc
	 *            TCommDoc Entity object to be deleted
	 */
	void deleteTCommDoc(TCommDocId tCommDocId);

	/**
	 * Updates a TCommDoc entity object in to the persistent store
	 * 
	 * @param tCommDoc
	 *            TCommDoc Entity object to be updated
	 * @return tCommDoc Persisted TCommDoc object
	 */
	TCommDoc updateTCommDoc(TCommDoc tCommDoc);

	/**
	 * Retrieve an TCommDoc object based on given TCommDocId.
	 * 
	 * @param tCommDocId
	 *            the primary key value of the TCommDoc Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TCommDoc findTCommDocById(TCommDocId tCommDocId);

	/**
	 * Retrieve TCommDoc based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCommDoc> list of TCommDocs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TCommDoc> findTCommDocs(SearchFilter<TCommDoc> searchFilter);

	/**
	 * Count TCommDoc based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTCommDocs(SearchFilter<TCommDoc> searchFilter);

	/**
	 * Retrieve TCommDoc based on given search criteria using JPA named Query.
	 * The search criteria is of TComm type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCommDoc> list of TCommDocs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TCommDoc> getTCommDocsByTComm(SearchFilter<TComm> searchFilter);

	/**
	 * Count TCommDoc based on given search criteria using JPA named Query.
	 * The search criteria is of TComm type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTCommDocsByTComm(SearchFilter<TComm> searchFilter);

	
	/**
	 * Count t comm docs by t comm ids.
	 *
	 * @param commids the commids
	 * @param tenantId the tenant id
	 * @return the list
	 */
	List<Object[]> countTCommDocsByTCommIds(List commids, short tenantId);
	
}
