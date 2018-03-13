package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TOrg;
import com.cognizant.opserv.sp.core.entity.TOrgRole;
import com.cognizant.opserv.sp.core.entity.TRoleList;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TOrgRole DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TOrgRoleDAO {

	/**
	 * Stores a new TOrgRole entity object in to the persistent store
	 * 
	 * @param tOrgRole
	 *            TOrgRole Entity object to be persisted
	 * @return tOrgRole Persisted TOrgRole object
	 */
	TOrgRole createTOrgRole(TOrgRole tOrgRole);

	/**
	 * Deletes a TOrgRole entity object from the persistent store
	 * 
	 * @param tOrgRole
	 *            TOrgRole Entity object to be deleted
	 */
	void deleteTOrgRole(Integer orgRoleId);

	/**
	 * Updates a TOrgRole entity object in to the persistent store
	 * 
	 * @param tOrgRole
	 *            TOrgRole Entity object to be updated
	 * @return tOrgRole Persisted TOrgRole object
	 */
	TOrgRole updateTOrgRole(TOrgRole tOrgRole);

	/**
	 * Retrieve an TOrgRole object based on given orgRoleId.
	 * 
	 * @param orgRoleId
	 *            the primary key value of the TOrgRole Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TOrgRole findTOrgRoleById(Integer orgRoleId);

	/**
	 * Retrieve TOrgRole based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TOrgRole> list of TOrgRoles if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TOrgRole> findTOrgRoles(SearchFilter<TOrgRole> searchFilter);

	/**
	 * Count TOrgRole based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTOrgRoles(SearchFilter<TOrgRole> searchFilter);

	/**
	 * Retrieve TOrgRole based on given search criteria using JPA named Query.
	 * The search criteria is of TOrg type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TOrgRole> list of TOrgRoles if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TOrgRole> getTOrgRolesByTOrg(SearchFilter<TOrg> searchFilter);

	/**
	 * Count TOrgRole based on given search criteria using JPA named Query.
	 * The search criteria is of TOrg type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTOrgRolesByTOrg(SearchFilter<TOrg> searchFilter);

	/**
	 * Retrieve TOrgRole based on given search criteria using JPA named Query.
	 * The search criteria is of TOrgRole type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TOrgRole> list of TOrgRoles if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TOrgRole> getTOrgRolesByTOrgRole(SearchFilter<TOrgRole> searchFilter);

	/**
	 * Count TOrgRole based on given search criteria using JPA named Query.
	 * The search criteria is of TOrgRole type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTOrgRolesByTOrgRole(SearchFilter<TOrgRole> searchFilter);

	/**
	 * Retrieve TOrgRole based on given search criteria using JPA named Query.
	 * The search criteria is of TRoleList type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TOrgRole> list of TOrgRoles if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TOrgRole> getTOrgRolesByTRoleList(SearchFilter<TRoleList> searchFilter);

	/**
	 * Count TOrgRole based on given search criteria using JPA named Query.
	 * The search criteria is of TRoleList type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTOrgRolesByTRoleList(SearchFilter<TRoleList> searchFilter);

	List<Object[]> findRoleIdOrgRoleIdAndName(Short tenantId);

	List<Object[]> findUnMappedRoleIdOrgRoleIdAndRoleName(Short tenantId);

}
