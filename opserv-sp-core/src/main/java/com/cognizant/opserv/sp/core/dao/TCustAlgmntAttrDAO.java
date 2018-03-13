package com.cognizant.opserv.sp.core.dao;


import java.util.List;

import com.cognizant.opserv.sp.core.entity.TAttrDef;
import com.cognizant.opserv.sp.core.entity.TCustAlgmnt;
import com.cognizant.opserv.sp.core.entity.TCustAlgmntAttr;
import com.cognizant.opserv.sp.core.entity.TCustAlgmntAttrId;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TCustAlgmntAttr DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TCustAlgmntAttrDAO {

	/**
	 * Stores a new TCustAlgmntAttr entity object in to the persistent store
	 * 
	 * @param tCustAlgmntAttr
	 *            TCustAlgmntAttr Entity object to be persisted
	 * @return tCustAlgmntAttr Persisted TCustAlgmntAttr object
	 */
	TCustAlgmntAttr createTCustAlgmntAttr(TCustAlgmntAttr tCustAlgmntAttr);

	/**
	 * Deletes a TCustAlgmntAttr entity object from the persistent store
	 * 
	 * @param tCustAlgmntAttr
	 *            TCustAlgmntAttr Entity object to be deleted
	 */
	void deleteTCustAlgmntAttr(TCustAlgmntAttrId tCustAlgmntAttrId);

	/**
	 * Updates a TCustAlgmntAttr entity object in to the persistent store
	 * 
	 * @param tCustAlgmntAttr
	 *            TCustAlgmntAttr Entity object to be updated
	 * @return tCustAlgmntAttr Persisted TCustAlgmntAttr object
	 */
	TCustAlgmntAttr updateTCustAlgmntAttr(TCustAlgmntAttr tCustAlgmntAttr);

	/**
	 * Retrieve an TCustAlgmntAttr object based on given TCustAlgmntAttrId.
	 * 
	 * @param tCustAlgmntAttrId
	 *            the primary key value of the TCustAlgmntAttr Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TCustAlgmntAttr findTCustAlgmntAttrById(TCustAlgmntAttrId tCustAlgmntAttrId);

	/**
	 * Retrieve TCustAlgmntAttr based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCustAlgmntAttr> list of TCustAlgmntAttrs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TCustAlgmntAttr> findTCustAlgmntAttrs(SearchFilter<TCustAlgmntAttr> searchFilter);

	/**
	 * Count TCustAlgmntAttr based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTCustAlgmntAttrs(SearchFilter<TCustAlgmntAttr> searchFilter);

	/**
	 * Retrieve TCustAlgmntAttr based on given search criteria using JPA named Query.
	 * The search criteria is of TCustAlgmnt type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCustAlgmntAttr> list of TCustAlgmntAttrs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TCustAlgmntAttr> getTCustAlgmntAttrsByTCustAlgmnt(SearchFilter<TCustAlgmnt> searchFilter);

	/**
	 * Count TCustAlgmntAttr based on given search criteria using JPA named Query.
	 * The search criteria is of TCustAlgmnt type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTCustAlgmntAttrsByTCustAlgmnt(SearchFilter<TCustAlgmnt> searchFilter);

	/**
	 * Retrieve TCustAlgmntAttr based on given search criteria using JPA named Query.
	 * The search criteria is of TAttrDef type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCustAlgmntAttr> list of TCustAlgmntAttrs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TCustAlgmntAttr> getTCustAlgmntAttrsByTAttrDef(SearchFilter<TAttrDef> searchFilter);

	/**
	 * Count TCustAlgmntAttr based on given search criteria using JPA named Query.
	 * The search criteria is of TAttrDef type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTCustAlgmntAttrsByTAttrDef(SearchFilter<TAttrDef> searchFilter);
	
	/**
	 * Gets the t cust algmnt attrs by attr.
	 *
	 * @param custAlgmntId the cust algmnt id
	 * @param attrId the attr id
	 * @param tenantId the tenant id
	 * @return the t cust algmnt attrs by attr
	 */
	List<TCustAlgmntAttr> getTCustAlgmntAttrsByAttr(long custAlgmntId, Long attrId, short tenantId);
	
	
	/**
	 * Gets the t cust algmnt attr by id.
	 *
	 * @param searchFilter the search filter
	 * @return the t cust algmnt attr by id
	 */
	List<TCustAlgmntAttr> getTCustAlgmntAttrById(SearchFilter<TCustAlgmntAttr> searchFilter);
	
	/**
	 * Gets the t cust algmnt attrs by cust algmnt id.
	 *
	 * @param custAlgmntId the cust algmnt id
	 * @return the t cust algmnt attrs by cust algmnt id
	 */
	public List<TCustAlgmntAttr> getTCustAlgmntAttrsByCustAlgmntId(long custAlgmntId);
	
	
	/**
	 * Gets the t cust algmnt attr by attr id.
	 *
	 * @param tCustAlgmntAttr the t cust algmnt attr
	 * @return the t cust algmnt attr by attr id
	 */
	public List<TCustAlgmntAttr> getTCustAlgmntAttrByAttrId(TCustAlgmntAttr tCustAlgmntAttr);

	/**
	 * Gets the t cust algmnt attr by id list.
	 *
	 * @param custAlgmntIdList the cust algmnt id list
	 * @param tenantId the tenant id
	 * @return the t cust algmnt attr by id list
	 */
	List<TCustAlgmntAttr> getTCustAlgmntAttrByIdList(
			List<Long> custAlgmntIdList, Short tenantId);

	/**
	 * Find all attrs for cust algmnt.
	 *
	 * @param custAlgmntId the cust algmnt id
	 * @param attrIds the attr ids
	 * @return the list
	 */
	List<TCustAlgmntAttr> findAllAttrsForCustAlgmnt(Long custAlgmntId,
			List<Long> attrIds);

	/**
	 * Find all attrs for all cust algmnt.
	 *
	 * @param custAlgmntIds the cust algmnt ids
	 * @param attrIds the attr ids
	 * @param tenantId the tenant id
	 * @return the list
	 */
	List<TCustAlgmntAttr> findAllAttrsForAllCustAlgmnt(
			List<Long> custAlgmntIds,List<Long> attrIds, Short tenantId);
	
	/**
	 * Update t cust algmnt attrs.
	 *
	 * @param tCustAlgmntAttrs the t cust algmnt attrs
	 * @return the list
	 */
	List<TCustAlgmntAttr> updateTCustAlgmntAttrs(List<TCustAlgmntAttr> tCustAlgmntAttrs) ;
	
	/**
	 * Store t cust algmnt attrs.
	 *
	 * @param tCustAlgmntAttrs the t cust algmnt attrs
	 * @return the list
	 */
	List<TCustAlgmntAttr> storeTCustAlgmntAttrs(List<TCustAlgmntAttr> tCustAlgmntAttrs) ;
	
}
