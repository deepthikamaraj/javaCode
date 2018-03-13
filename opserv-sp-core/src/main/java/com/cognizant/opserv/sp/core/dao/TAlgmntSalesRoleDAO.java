package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TAlgmntSalesHier;
import com.cognizant.opserv.sp.core.entity.TAlgmntSalesRole;
import com.cognizant.opserv.sp.core.entity.TAlgmntSalesRoleId;
import com.cognizant.opserv.sp.core.entity.TOrgRole;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TAlgmntSalesRole DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TAlgmntSalesRoleDAO {

	/**
	 * Stores a new TAlgmntSalesRole entity object in to the persistent store
	 * 
	 * @param tAlgmntSalesRole
	 *            TAlgmntSalesRole Entity object to be persisted
	 * @return tAlgmntSalesRole Persisted TAlgmntSalesRole object
	 */
	TAlgmntSalesRole createTAlgmntSalesRole(TAlgmntSalesRole tAlgmntSalesRole);

	/**
	 * Deletes a TAlgmntSalesRole entity object from the persistent store
	 * 
	 * @param tAlgmntSalesRole
	 *            TAlgmntSalesRole Entity object to be deleted
	 */
	void deleteTAlgmntSalesRole(TAlgmntSalesRoleId tAlgmntSalesRoleId);

	/**
	 * Updates a TAlgmntSalesRole entity object in to the persistent store
	 * 
	 * @param tAlgmntSalesRole
	 *            TAlgmntSalesRole Entity object to be updated
	 * @return tAlgmntSalesRole Persisted TAlgmntSalesRole object
	 */
	TAlgmntSalesRole updateTAlgmntSalesRole(TAlgmntSalesRole tAlgmntSalesRole);

	/**
	 * Retrieve an TAlgmntSalesRole object based on given TAlgmntSalesRoleId.
	 * 
	 * @param tAlgmntSalesRoleId
	 *            the primary key value of the TAlgmntSalesRole Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TAlgmntSalesRole findTAlgmntSalesRoleById(TAlgmntSalesRoleId tAlgmntSalesRoleId);

	/**
	 * Retrieve TAlgmntSalesRole based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TAlgmntSalesRole> list of TAlgmntSalesRoles if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TAlgmntSalesRole> findTAlgmntSalesRoles(SearchFilter<TAlgmntSalesRole> searchFilter);

	/**
	 * Count TAlgmntSalesRole based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTAlgmntSalesRoles(SearchFilter<TAlgmntSalesRole> searchFilter);

	/**
	 * Retrieve TAlgmntSalesRole based on given search criteria using JPA named Query.
	 * The search criteria is of TAlgmntSalesHier type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TAlgmntSalesRole> list of TAlgmntSalesRoles if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TAlgmntSalesRole> getTAlgmntSalesRolesByTAlgmntSalesHier(SearchFilter<TAlgmntSalesHier> searchFilter);

	/**
	 * Count TAlgmntSalesRole based on given search criteria using JPA named Query.
	 * The search criteria is of TAlgmntSalesHier type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTAlgmntSalesRolesByTAlgmntSalesHier(SearchFilter<TAlgmntSalesHier> searchFilter);

	/**
	 * Retrieve TAlgmntSalesRole based on given search criteria using JPA named Query.
	 * The search criteria is of TOrgRole type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TAlgmntSalesRole> list of TAlgmntSalesRoles if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TAlgmntSalesRole> getTAlgmntSalesRolesByTOrgRole(SearchFilter<TOrgRole> searchFilter);

	/**
	 * Count TAlgmntSalesRole based on given search criteria using JPA named Query.
	 * The search criteria is of TOrgRole type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTAlgmntSalesRolesByTOrgRole(SearchFilter<TOrgRole> searchFilter);


	/**
	 * Gets the t algmnt sales role b.
	 *
	 * @param searchFilter the search filter
	 * @return the t algmnt sales role b
	 */
	List<TAlgmntSalesRole> getTAlgmntSalesRoleB(
				SearchFilter<TAlgmntSalesRole> searchFilter);
	
	/**
	 * Gets the t algmnt sales role by named query.
	 *
	 * @param paramList the param list
	 * @return the t algmnt sales role by named query
	 */
	List<TAlgmntSalesRole> getTAlgmntSalesRoleByNamedQuery(List paramList);
	
	/**
	 * Gets the t alignment sales role id by sales hier id.
	 *
	 * @param salesHierId the sales hier id
	 * @param tenantId the tenant id
	 * @return the t alignment sales role id by sales hier id
	 */
	List<TAlgmntSalesRole> getTAlignmentSalesRoleIdBySalesHierId(Long salesHierId, short tenantId);
	
	/**
	 * Gets the role id details.
	 *
	 * @param searchFilter the search filter
	 * @return the role id details
	 */
	List<TAlgmntSalesRole> getRoleIdDetails(SearchFilter<TAlgmntSalesRole> searchFilter);
	
	/**
	 * Find active roles in t sales algmnt role.
	 *
	 * @param tenantId the tenant id
	 * @return the list
	 */
	List<Object> findActiveRolesInTSalesAlgmntRole(Short tenantId);
	
	/**
	 * Find active mapped roles in sales algmnt role.
	 *
	 * @param tenantId the tenant id
	 * @param orgRoleId the org role id
	 * @return the integer
	 */
	Integer findActiveMappedRolesInSalesAlgmntRole(Short tenantId, Integer orgRoleId);

	/**
	 * @param algmntId
	 * @param buId
	 * @param stId
	 * @param shId
	 * @param tenantId
	 * @param activeFlag
	 * @return List<TAlgmntSalesRole>
	 */
	List<TAlgmntSalesRole> getActiveTAlgmntSalesRole(Long algmntId,
		Long buId, Long stId, Long shId, short tenantId,
		Character activeFlag);
}
