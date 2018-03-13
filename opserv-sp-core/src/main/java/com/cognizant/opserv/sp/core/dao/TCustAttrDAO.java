package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TAttrDef;
import com.cognizant.opserv.sp.core.entity.TCust;
import com.cognizant.opserv.sp.core.entity.TCustAttr;
import com.cognizant.opserv.sp.core.entity.TCustAttrId;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TCustAttr DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TCustAttrDAO {

	/**
	 * Stores a new TCustAttr entity object in to the persistent store
	 * 
	 * @param tCustAttr
	 *            TCustAttr Entity object to be persisted
	 * @return tCustAttr Persisted TCustAttr object
	 */
	TCustAttr createTCustAttr(TCustAttr tCustAttr);

	/**
	 * Deletes a TCustAttr entity object from the persistent store
	 * 
	 * @param tCustAttr
	 *            TCustAttr Entity object to be deleted
	 */
	void deleteTCustAttr(TCustAttrId tCustAttrId);

	/**
	 * Updates a TCustAttr entity object in to the persistent store
	 * 
	 * @param tCustAttr
	 *            TCustAttr Entity object to be updated
	 * @return tCustAttr Persisted TCustAttr object
	 */
	TCustAttr updateTCustAttr(TCustAttr tCustAttr);

	/**
	 * Retrieve an TCustAttr object based on given TCustAttrId.
	 * 
	 * @param tCustAttrId
	 *            the primary key value of the TCustAttr Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TCustAttr findTCustAttrById(TCustAttrId tCustAttrId);

	/**
	 * Retrieve TCustAttr based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCustAttr> list of TCustAttrs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TCustAttr> findTCustAttrs(SearchFilter<TCustAttr> searchFilter);

	/**
	 * Count TCustAttr based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTCustAttrs(SearchFilter<TCustAttr> searchFilter);

	/**
	 * Retrieve TCustAttr based on given search criteria using JPA named Query.
	 * The search criteria is of TCust type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCustAttr> list of TCustAttrs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TCustAttr> getTCustAttrsByTCust(SearchFilter<TCust> searchFilter);

	/**
	 * Count TCustAttr based on given search criteria using JPA named Query.
	 * The search criteria is of TCust type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTCustAttrsByTCust(SearchFilter<TCust> searchFilter);

	/**
	 * Retrieve TCustAttr based on given search criteria using JPA named Query.
	 * The search criteria is of TAttrDef type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCustAttr> list of TCustAttrs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TCustAttr> getTCustAttrsByTAttrDef(SearchFilter<TAttrDef> searchFilter);

	/**
	 * Count TCustAttr based on given search criteria using JPA named Query.
	 * The search criteria is of TAttrDef type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTCustAttrsByTAttrDef(SearchFilter<TAttrDef> searchFilter);
	
	 /**
 	 * Gets the t cust attr by id.
 	 *
 	 * @param searchFilter the search filter
 	 * @return the list
 	 */
	List<TCustAttr> GetTCustAttrById(SearchFilter<TCustAttr> searchFilter);
	
	
	/**
	 * Find cust ext attr by grp.
	 *
	 * @param algmntId the algmnt id
	 * @param bussUnitId the buss unit id
	 * @param salesTeamId the sales team id
	 * @param custId the cust id
	 * @param tenantId the tenant id
	 * @param custBuName the cust bu name
	 * @return the list
	 */
	List findCustExtAttrByGrp(Long algmntId,String bussUnitId,Long salesTeamId,Integer custId,short tenantId, String custBuName);

	/**
	 * Gets the t cust attr by attr id.
	 *
	 * @param tCustAttr the t cust attr
	 * @return the t cust attr by attr id
	 */
	List<TCustAttr> getTCustAttrByAttrId(TCustAttr tCustAttr);

	/**
	 * Gets the t cust attr by cust id.
	 *
	 * @param custId the cust id
	 * @return the t cust attr by cust id
	 */
	public List<TCustAttr> getTCustAttrByCustId(Integer custId);
	
	/**
	 * Gets the t cust attr by attr id list.
	 *
	 * @param custIdList the cust id list
	 * @param tenantId the tenant id
	 * @return the t cust attr by attr id list
	 */
	public List<TCustAttr> getTCustAttrByAttrIdList(
			List<Integer> custIdList, Short tenantId);

	/**
	 * Gets the t cust attr by attr id list.
	 *
	 * @param custIdList the cust id list
	 * @param attrId the attr id
	 * @param attrVal the attr val
	 * @return the t cust attr by attr id list
	 */
	List<Integer> getTCustAttrByAttrIdList(List<Integer> custIdList,Long attrId, String attrVal);
	
	/**
	 * Find cust ext attr names.
	 *
	 * @param algmntId the algmnt id
	 * @param bussUnitId the buss unit id
	 * @param salesTeamId the sales team id
	 * @param tenantId the tenant id
	 * @return the list
	 */
	List findCustExtAttrNames(Long algmntId,String bussUnitId,Long salesTeamId,short tenantId);

	/**
	 * Find all attrs for cust.
	 *
	 * @param custId the cust id
	 * @param attrIds the attr ids
	 * @return the list
	 */
	List<TCustAttr> findAllAttrsForCust(Integer custId, List<Long> attrIds);

	/**
	 * Find cust attrs for cust.
	 *
	 * @param custId the cust id
	 * @param attrIds the attr ids
	 * @param activeFlag the active flag
	 * @param tenantId the tenant id
	 * @return the list
	 */
	List<Object[]> findCustAttrsForCust(Integer custId, List<Long> attrIds,
			Character activeFlag, Short tenantId);

	/**
	 * Find all attrs for all cust.
	 *
	 * @param custIds the cust ids
	 * @param custAttrIds the cust attr ids
	 * @param tenantId the tenant id
	 * @return the list
	 */
	List<TCustAttr> findAllAttrsForAllCust(List<Integer> custIds,List<Long> custAttrIds, Short tenantId);
	
	/**
	 * Update t cust attr.
	 *
	 * @param tCustAttrs the t cust attrs
	 * @return the list
	 */
	List<TCustAttr> updateTCustAttr(List<TCustAttr> tCustAttrs);
	
	/**
	 * Creates the t cust attr.
	 *
	 * @param tCustAttrs the t cust attrs
	 * @return the list
	 */
	List<TCustAttr> createTCustAttr(List<TCustAttr> tCustAttrs);
	
	/**
	 * Find t cust attr by t att grp map and cust id.
	 *
	 * @param custId the cust id
	 * @param algmntId the algmnt id
	 * @param bussUnitId the buss unit id
	 * @param salesTeamId the sales team id
	 * @param tenantId the tenant id
	 * @return the list
	 */
	public List<Object[]> findTCustAttrByTAttGrpMapAndCustID(List<Integer> custId ,
			Long algmntId,String bussUnitId,Long salesTeamId,short tenantId); 
	
	/**
	 * Find all ext attrs for all cust.
	 *
	 * @param custIds the cust ids
	 * @param tenantId the tenant id
	 * @return the list
	 */
	List<TCustAttr> findAllExtAttrsForAllCust(List<Integer> custIds, Short tenantId);
}
