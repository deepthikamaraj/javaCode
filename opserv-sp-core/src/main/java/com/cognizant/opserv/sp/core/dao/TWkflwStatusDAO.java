package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TWkflwStatus;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TWkflwStatus DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TWkflwStatusDAO {

	/**
	 * Stores a new TWkflwStatus entity object in to the persistent store
	 * 
	 * @param tWkflwStatus
	 *            TWkflwStatus Entity object to be persisted
	 * @return tWkflwStatus Persisted TWkflwStatus object
	 */
	TWkflwStatus createTWkflwStatus(TWkflwStatus tWkflwStatus);

	/**
	 * Deletes a TWkflwStatus entity object from the persistent store
	 * 
	 * @param tWkflwStatus
	 *            TWkflwStatus Entity object to be deleted
	 */
	void deleteTWkflwStatus(Integer statusId);

	/**
	 * Updates a TWkflwStatus entity object in to the persistent store
	 * 
	 * @param tWkflwStatus
	 *            TWkflwStatus Entity object to be updated
	 * @return tWkflwStatus Persisted TWkflwStatus object
	 */
	TWkflwStatus updateTWkflwStatus(TWkflwStatus tWkflwStatus);

	/**
	 * Retrieve an TWkflwStatus object based on given statusId.
	 * 
	 * @param statusId
	 *            the primary key value of the TWkflwStatus Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TWkflwStatus findTWkflwStatusById(Integer statusId);

	/**
	 * Retrieve TWkflwStatus based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TWkflwStatus> list of TWkflwStatuss if it exists against
	 *         given criteria. Returns null if not found
	 */
	List<TWkflwStatus> findTWkflwStatuss(SearchFilter<TWkflwStatus> searchFilter);

	/**
	 * Count TWkflwStatus based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTWkflwStatuss(SearchFilter<TWkflwStatus> searchFilter);

	List<TWkflwStatus> findAllTWkflwStatus();

	List<TWkflwStatus> findAllTWkflwStatusByTenantID();

	List<TWkflwStatus> findAllTWkflwStatusByTenantID(Short tenantId);
	public List<TWkflwStatus> FindAllTWkflwStatus(Short tenantId,String localeId);
}
