package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TRoleWdg;
import com.cognizant.opserv.sp.core.entity.TRoleWdgId;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TRoleWdg DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TRoleWdgDAO {

	
	
	
	
	/**
	 * Retrieve TRoleWdg based on given roleId.
	 * 
	 * @param roleId
	 * @param tenantId 
	 *            
	 * @return List<TRoleWdg> list of TRoleWdg if it exists against given
	 *         roleID. Returns null if not found
	 */
	
	List<TRoleWdg> findTRoleWdgs(Integer roleId, Short tenantId);
	
	
	
	
	
	
	/**
	 * Stores a new TRoleWdg entity object in to the persistent store
	 * 
	 * @param tRoleWdg
	 *            TRoleWdg Entity object to be persisted
	 * @return tRoleWdg Persisted TRoleWdg object
	 */
	TRoleWdg createTRoleWdg(TRoleWdg tRoleWdg);

	/**
	 * Deletes a TRoleWdg entity object from the persistent store
	 * 
	 * @param tRoleWdg
	 *            TRoleWdg Entity object to be deleted
	 */
	void deleteTRoleWdg(TRoleWdgId tRoleWdgId);

	/**
	 * Updates a TRoleWdg entity object in to the persistent store
	 * 
	 * @param tRoleWdg
	 *            TRoleWdg Entity object to be updated
	 * @return tRoleWdg Persisted TRoleWdg object
	 */
	TRoleWdg updateTRoleWdg(TRoleWdg tRoleWdg);

	/**
	 * Retrieve an TRoleWdg object based on given TRoleWdgId.
	 * 
	 * @param tRoleWdgId
	 *            the primary key value of the TRoleWdg Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TRoleWdg findTRoleWdgById(TRoleWdgId tRoleWdgId);

	/**
	 * Retrieve TRoleWdg based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRoleWdg> list of TRoleWdgs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TRoleWdg> findTRoleWdgs(SearchFilter<TRoleWdg> searchFilter);

	/**
	 * Count TRoleWdg based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTRoleWdgs(SearchFilter<TRoleWdg> searchFilter);
	
	/**
	 * 
	 * @param roleWdg
	 * @return
	 *//*
	boolean getMandatoryInfo(TRoleWdg roleWdg);*/

	/**
	 * 

	 * @param tRoleWdgList
	 * @return
	 */
	List<TRoleWdg> getMandatoryInfo(List<TRoleWdg> tRoleWdgList);


}
