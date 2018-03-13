package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TAlgmntStatus;
import com.cognizant.opserv.sp.core.entity.TAlgmntStatusId;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TAlgmntStatus DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TAlgmntStatusDAO {

	/**
	 * Stores a new TAlgmntStatus entity object in to the persistent store
	 * 
	 * @param tAlgmntStatus
	 *            TAlgmntStatus Entity object to be persisted
	 * @return tAlgmntStatus Persisted TAlgmntStatus object
	 */
	TAlgmntStatus createTAlgmntStatus(TAlgmntStatus tAlgmntStatus);

	/**
	 * Deletes a TAlgmntStatus entity object from the persistent store
	 * 
	 * @param tAlgmntStatus
	 *            TAlgmntStatus Entity object to be deleted
	 */
	void deleteTAlgmntStatus(Integer statusId);

	/**
	 * Updates a TAlgmntStatus entity object in to the persistent store
	 * 
	 * @param tAlgmntStatus
	 *            TAlgmntStatus Entity object to be updated
	 * @return tAlgmntStatus Persisted TAlgmntStatus object
	 */
	TAlgmntStatus updateTAlgmntStatus(TAlgmntStatus tAlgmntStatus);

	/**
	 * Retrieve an TAlgmntStatus object based on given statusId.
	 * 
	 * @param statusId
	 *            the primary key value of the TAlgmntStatus Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TAlgmntStatus findTAlgmntStatusById(TAlgmntStatusId statusId);

	/**
	 * Retrieve TAlgmntStatus based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TAlgmntStatus> list of TAlgmntStatuss if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TAlgmntStatus> findTAlgmntStatuss(SearchFilter<TAlgmntStatus> searchFilter);

	/**
	 * Count TAlgmntStatus based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTAlgmntStatuss(SearchFilter<TAlgmntStatus> searchFilter);
	
	/**
	 * Find t algmnt status by ids.
	 *
	 * @param tenantId the tenant id
	 * @param localeId the locale id
	 * @return the list
	 */
	List<TAlgmntStatus> findTAlgmntStatusByIds(Short tenantId,String localeId);

}
