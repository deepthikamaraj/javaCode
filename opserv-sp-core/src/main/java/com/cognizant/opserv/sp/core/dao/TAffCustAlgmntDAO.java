package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TAffCustAlgmnt;
import com.cognizant.opserv.sp.core.entity.TAffCustAlgmntId;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TAffCustAlgmnt DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TAffCustAlgmntDAO {

	/**
	 * Stores a new TAffCustAlgmnt entity object in to the persistent store
	 * 
	 * @param tAffCustAlgmnt
	 *            TAffCustAlgmnt Entity object to be persisted
	 * @return tAffCustAlgmnt Persisted TAffCustAlgmnt object
	 */
	TAffCustAlgmnt createTAffCustAlgmnt(TAffCustAlgmnt tAffCustAlgmnt);

	/**
	 * Deletes a TAffCustAlgmnt entity object from the persistent store
	 * 
	 * @param tAffCustAlgmnt
	 *            TAffCustAlgmnt Entity object to be deleted
	 */
	void deleteTAffCustAlgmnt(TAffCustAlgmntId tAffCustAlgmntId);

	/**
	 * Updates a TAffCustAlgmnt entity object in to the persistent store
	 * 
	 * @param tAffCustAlgmnt
	 *            TAffCustAlgmnt Entity object to be updated
	 * @return tAffCustAlgmnt Persisted TAffCustAlgmnt object
	 */
	TAffCustAlgmnt updateTAffCustAlgmnt(TAffCustAlgmnt tAffCustAlgmnt);

	/**
	 * Retrieve an TAffCustAlgmnt object based on given TAffCustAlgmntId.
	 * 
	 * @param tAffCustAlgmntId
	 *            the primary key value of the TAffCustAlgmnt Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TAffCustAlgmnt findTAffCustAlgmntById(TAffCustAlgmntId tAffCustAlgmntId);

	/**
	 * Retrieve TAffCustAlgmnt based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TAffCustAlgmnt> list of TAffCustAlgmnts if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TAffCustAlgmnt> findTAffCustAlgmnts(SearchFilter<TAffCustAlgmnt> searchFilter);

	/**
	 * Count TAffCustAlgmnt based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTAffCustAlgmnts(SearchFilter<TAffCustAlgmnt> searchFilter);

	/**
	 * Gets the aff cust algmnt ids by algmnt ids.
	 *
	 * @param custAlgmntIds the cust algmnt ids
	 * @param tenantId the tenant id
	 * @return the aff cust algmnt ids by algmnt ids
	 */
	List<Long> getAffCustAlgmntIdsByAlgmntIds(List<Long> custAlgmntIds, Short tenantId);

	/**
	 * Inactivate aff cust algmnts by algmnt ids.
	 *
	 * @param custAlgmntIds the cust algmnt ids
	 * @param tenantId the tenant id
	 */
	void inactivateAffCustAlgmntsByAlgmntIds(List<Long> custAlgmntIds,Short tenantId);

	/**
	 * Gets the aff cust algmnts by algmnt ids.
	 *
	 * @param custAlgmntIds the cust algmnt ids
	 * @param tenantId the tenant id
	 * @param flag the flag
	 * @return the aff cust algmnts by algmnt ids
	 */
	List<TAffCustAlgmnt> getAffCustAlgmntsByAlgmntIds(List<Long> custAlgmntIds,	Short tenantId, Character flag);	

}
