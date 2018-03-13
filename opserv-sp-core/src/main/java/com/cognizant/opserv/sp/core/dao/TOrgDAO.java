package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TOrg;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TOrg DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TOrgDAO {

	/**
	 * Stores a new TOrg entity object in to the persistent store
	 * 
	 * @param tOrg
	 *            TOrg Entity object to be persisted
	 * @return tOrg Persisted TOrg object
	 */
	TOrg createTOrg(TOrg tOrg);

	/**
	 * Deletes a TOrg entity object from the persistent store
	 * 
	 * @param tOrg
	 *            TOrg Entity object to be deleted
	 */
	void deleteTOrg(Integer orgId);

	/**
	 * Updates a TOrg entity object in to the persistent store
	 * 
	 * @param tOrg
	 *            TOrg Entity object to be updated
	 * @return tOrg Persisted TOrg object
	 */
	TOrg updateTOrg(TOrg tOrg);

	/**
	 * Retrieve an TOrg object based on given orgId.
	 * 
	 * @param orgId
	 *            the primary key value of the TOrg Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TOrg findTOrgById(Integer orgId);

	/**
	 * Retrieve TOrg based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TOrg> list of TOrgs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TOrg> findTOrgs(SearchFilter<TOrg> searchFilter);

	/**
	 * Count TOrg based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTOrgs(SearchFilter<TOrg> searchFilter);
	
	
	/**
	 * This method fetches the organization information using tenant Id 
	 * @param tenantId
	 * @return
	 */
	TOrg findTOrgByTenantId(Short tenantId);
	

}
