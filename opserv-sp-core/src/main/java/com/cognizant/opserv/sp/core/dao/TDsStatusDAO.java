package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TDsStatus;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TDsStatus DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TDsStatusDAO {

	/**
	 * Stores a new TDsStatus entity object in to the persistent store
	 * 
	 * @param tDsStatus
	 *            TDsStatus Entity object to be persisted
	 * @return tDsStatus Persisted TDsStatus object
	 */
	TDsStatus createTDsStatus(TDsStatus tDsStatus);

	/**
	 * Deletes a TDsStatus entity object from the persistent store
	 * 
	 * @param tDsStatus
	 *            TDsStatus Entity object to be deleted
	 */
	void deleteTDsStatus(Integer statusId);

	/**
	 * Updates a TDsStatus entity object in to the persistent store
	 * 
	 * @param tDsStatus
	 *            TDsStatus Entity object to be updated
	 * @return tDsStatus Persisted TDsStatus object
	 */
	TDsStatus updateTDsStatus(TDsStatus tDsStatus);

	/**
	 * Retrieve an TDsStatus object based on given statusId.
	 * 
	 * @param statusId
	 *            the primary key value of the TDsStatus Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TDsStatus findTDsStatusById(Integer statusId);
	
	/**
	 * Retrieve an TDsStatus object based on given statusId.
	 * 
	 * @param statusId
	 *            the primary key value of the TDsStatus Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	 List<TDsStatus> findTDsStatusByIdAndTenantId(final Integer tDsStatusId, final String locale, final Short tDsTenantId);

	/**
	 * Retrieve TDsStatus based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TDsStatus> list of TDsStatuss if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TDsStatus> findTDsStatuss(SearchFilter<TDsStatus> searchFilter);

	/**
	 * Count TDsStatus based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTDsStatuss(SearchFilter<TDsStatus> searchFilter);

	/**
	 * Find t ds created by id.
	 *
	 * @param createdById the created by id
	 * @return the list
	 */
	List<TDsStatus> findTDsCreatedById(Integer createdById);
}
