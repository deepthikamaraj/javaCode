package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TRoleList;
import com.cognizant.peg.core.common.ISearchCriteria;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TRoleList DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TRoleListDAO {

	/**
	 * Stores a new TRoleList entity object in to the persistent store
	 * 
	 * @param tRoleList
	 *            TRoleList Entity object to be persisted
	 * @return tRoleList Persisted TRoleList object
	 */
	TRoleList createTRoleList(TRoleList tRoleList);

	/**
	 * Deletes a TRoleList entity object from the persistent store
	 * 
	 * @param tRoleList
	 *            TRoleList Entity object to be deleted
	 */
	void deleteTRoleList(Integer roleId);

	/**
	 * Updates a TRoleList entity object in to the persistent store
	 * 
	 * @param tRoleList
	 *            TRoleList Entity object to be updated
	 * @return tRoleList Persisted TRoleList object
	 */
	TRoleList updateTRoleList(TRoleList tRoleList);

	/**
	 * Retrieve an TRoleList object based on given roleId.
	 * 
	 * @param roleId
	 *            the primary key value of the TRoleList Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TRoleList findTRoleListById(Integer roleId);

	/**
	 * Retrieve TRoleList based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRoleList> list of TRoleLists if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TRoleList> findTRoleLists(SearchFilter<TRoleList> searchFilter);

	/**
	 * Count TRoleList based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTRoleLists(SearchFilter<TRoleList> searchFilter);

	/**
	 * Retrieve TRoleList based on given search criteria using JPA named Query.
	 * The search criteria is of TRoleList type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRoleList> list of TRoleLists if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TRoleList> getTRoleListsByTRoleList(SearchFilter<TRoleList> searchFilter);

	/**
	 * Count TRoleList based on given search criteria using JPA named Query.
	 * The search criteria is of TRoleList type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTRoleListsByTRoleList(SearchFilter<TRoleList> searchFilter);

	List<Object> findTRoleListByMaxOfRoleId(Short tenantId);
	
	List<Integer> findTRoleListByMaxRoleId(Short tenantId);

	List<Object[]> findRoleIdAndRoleName(Short tenantId);
	
	List<TRoleList> findTRoleListBySearchCriteria(ISearchCriteria criteria);
}
