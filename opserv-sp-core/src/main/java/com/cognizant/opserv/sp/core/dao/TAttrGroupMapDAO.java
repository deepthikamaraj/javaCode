package com.cognizant.opserv.sp.core.dao;


import java.util.List;

import com.cognizant.opserv.sp.core.entity.TAttrDef;
import com.cognizant.opserv.sp.core.entity.TAttrGroup;
import com.cognizant.opserv.sp.core.entity.TAttrGroupMap;
import com.cognizant.opserv.sp.core.entity.TAttrGroupMapId;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TAttrGroupMap DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TAttrGroupMapDAO {

	/**
	 * Stores a new TAttrGroupMap entity object in to the persistent store
	 * 
	 * @param tAttrGroupMap
	 *            TAttrGroupMap Entity object to be persisted
	 * @return tAttrGroupMap Persisted TAttrGroupMap object
	 */
	TAttrGroupMap createTAttrGroupMap(TAttrGroupMap tAttrGroupMap);

	/**
	 * Deletes a TAttrGroupMap entity object from the persistent store
	 * 
	 * @param tAttrGroupMap
	 *            TAttrGroupMap Entity object to be deleted
	 */
	void deleteTAttrGroupMap(TAttrGroupMapId tAttrGroupMapId);

	/**
	 * Updates a TAttrGroupMap entity object in to the persistent store
	 * 
	 * @param tAttrGroupMap
	 *            TAttrGroupMap Entity object to be updated
	 * @return tAttrGroupMap Persisted TAttrGroupMap object
	 */
	TAttrGroupMap updateTAttrGroupMap(TAttrGroupMap tAttrGroupMap);

	/**
	 * Retrieve an TAttrGroupMap object based on given TAttrGroupMapId.
	 * 
	 * @param tAttrGroupMapId
	 *            the primary key value of the TAttrGroupMap Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TAttrGroupMap findTAttrGroupMapById(TAttrGroupMapId tAttrGroupMapId);

	/**
	 * Retrieve TAttrGroupMap based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TAttrGroupMap> list of TAttrGroupMaps if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TAttrGroupMap> findTAttrGroupMaps(SearchFilter<TAttrGroupMap> searchFilter);

	/**
	 * Count TAttrGroupMap based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTAttrGroupMaps(SearchFilter<TAttrGroupMap> searchFilter);

	/**
	 * Retrieve TAttrGroupMap based on given search criteria using JPA named Query.
	 * The search criteria is of TAttrGroup type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TAttrGroupMap> list of TAttrGroupMaps if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TAttrGroupMap> getTAttrGroupMapsByTAttrGroup(SearchFilter<TAttrGroup> searchFilter);

	/**
	 * Count TAttrGroupMap based on given search criteria using JPA named Query.
	 * The search criteria is of TAttrGroup type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTAttrGroupMapsByTAttrGroup(SearchFilter<TAttrGroup> searchFilter);

	/**
	 * Retrieve TAttrGroupMap based on given search criteria using JPA named Query.
	 * The search criteria is of TAttrDef type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TAttrGroupMap> list of TAttrGroupMaps if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TAttrGroupMap> getTAttrGroupMapsByTAttrDef(SearchFilter<TAttrDef> searchFilter);

	/**
	 * Count TAttrGroupMap based on given search criteria using JPA named Query.
	 * The search criteria is of TAttrDef type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTAttrGroupMapsByTAttrDef(SearchFilter<TAttrDef> searchFilter);

	/**
	 * Retrieve TAttrGroupMap based on given search criteria using JPA named Query.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TAttrGroupMap> list of TAttrGroupMaps if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TAttrGroupMap> getTAttrGroupMapByAttrName(String attrName);
	
	/**
	 * Retrieve TAttrGroupMap based on given search criteria using JPA named Query.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TAttrGroupMap> list of TAttrGroupMaps if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TAttrGroupMap> getTAttrGroupMapByAttrNameAndTAttrGroup(String attrName, String attrGroupName);
	
	
	/**
	 * Find t attr group maps by attr id.
	 *
	 * @param tAttrId the t attr id
	 * @return the list
	 */
	List<TAttrGroupMap> findTAttrGroupMapsByAttrId(Long tAttrId);
	
	
	/**
	 * Find t attr group maps by attrgroup id.
	 *
	 * @param tAttrGrpId the t attr grp id
	 * @param tenantId the tenant id
	 * @return the list
	 */
	List<TAttrGroupMap> findTAttrGroupMapsByAttrgroupId(Integer tAttrGrpId,short tenantId);
	
	/**
	 * Retrieve TAttrGroupMap based on given search criteria using JPA named Query.
	 * The search criteria is of TAttrGroup and TAttrDef type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRpt> list of TRpts if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TAttrGroupMap> getTAttrGroupMapsByTAttrGroupAndTAttrDef(TAttrGroup tAttrGroup, TAttrDef tAttrDef, Integer index, Integer maxresult);
	
	/**
	 * Delete from t attr group map hist table.
	 *
	 * @param groupId the group id
	 * @param attrId the attr id
	 * @return the int
	 */
	int deleteFromTAttrGroupMapHistTable(Integer groupId,Long attrId);
	
	/**
	 * Find group map by attr id.
	 *
	 * @param attrId the attr id
	 * @param tenantId the tenant id
	 * @return the list
	 */
	List<Object[]> findGroupMapByAttrId(Long attrId,Short tenantId);
}
