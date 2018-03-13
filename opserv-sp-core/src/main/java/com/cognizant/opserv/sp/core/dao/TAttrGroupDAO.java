package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TAttrGroup;
import com.cognizant.opserv.sp.core.entity.TBussObjTmpl;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TAttrGroup DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TAttrGroupDAO {

	/**
	 * Stores a new TAttrGroup entity object in to the persistent store
	 * 
	 * @param tAttrGroup
	 *            TAttrGroup Entity object to be persisted
	 * @return tAttrGroup Persisted TAttrGroup object
	 */
	TAttrGroup createTAttrGroup(TAttrGroup tAttrGroup);

	/**
	 * Deletes a TAttrGroup entity object from the persistent store
	 * 
	 * @param tAttrGroup
	 *            TAttrGroup Entity object to be deleted
	 */
	void deleteTAttrGroup(Integer attrGroupId);

	/**
	 * Updates a TAttrGroup entity object in to the persistent store
	 * 
	 * @param tAttrGroup
	 *            TAttrGroup Entity object to be updated
	 * @return tAttrGroup Persisted TAttrGroup object
	 */
	TAttrGroup updateTAttrGroup(TAttrGroup tAttrGroup);

	/**
	 * Retrieve an TAttrGroup object based on given attrGroupId.
	 * 
	 * @param attrGroupId
	 *            the primary key value of the TAttrGroup Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TAttrGroup findTAttrGroupById(Integer attrGroupId);

	/**
	 * Retrieve TAttrGroup based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TAttrGroup> list of TAttrGroups if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TAttrGroup> findTAttrGroups(SearchFilter<TAttrGroup> searchFilter);

	/**
	 * Count TAttrGroup based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTAttrGroups(SearchFilter<TAttrGroup> searchFilter);

	/**
	 * Retrieve TAttrGroup based on given search criteria using JPA named Query.
	 * The search criteria is of TBussObjTmpl type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TAttrGroup> list of TAttrGroups if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TAttrGroup> getTAttrGroupsByTBussObjTmpl(SearchFilter<TBussObjTmpl> searchFilter);

	/**
	 * Count TAttrGroup based on given search criteria using JPA named Query.
	 * The search criteria is of TBussObjTmpl type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTAttrGroupsByTBussObjTmpl(SearchFilter<TBussObjTmpl> searchFilter);

	/**
	 * Retrieve an TAttrGroup object based on given attrGroupName.
	 * 
	 * @param attrGroupName
	 *            the group name of the TAttrGroup Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	List<Object> findTAttrGroupByName(String attrGroupName);

	/**
	 * Find t attr group by group name and t buss obj tmpl.
	 *
	 * @param attrGroupName the attr group name
	 * @param tmplId the tmpl id
	 * @param activeFlagY the active flag y
	 * @param tenantId the tenant id
	 * @return the list
	 */
	List<Object> findTAttrGroupByGroupNameAndTBussObjTmpl(String attrGroupName,
			Integer tmplId,Character activeFlagY, Short tenantId);
	
	/**
	 * Gets the t attr groups by t buss obj tmpl id.
	 *
	 * @param templateId the template id
	 * @param tenantID the tenant id
	 * @return the t attr groups by t buss obj tmpl id
	 */
	List<TAttrGroup> getTAttrGroupsByTBussObjTmplId(final Integer templateId, Short tenantID);
	
	/**
	 * Find non active t attr group by t buss obj tmpl.
	 *
	 * @param tBussObjTmpl the t buss obj tmpl
	 * @return the list
	 */
	List<TAttrGroup> findNonActiveTAttrGroupByTBussObjTmpl(TBussObjTmpl tBussObjTmpl);

	/**
	 * Find active t attr group by t buss obj tmpl.
	 *
	 * @param tmplId the tmpl id
	 * @param tenantId the tenant id
	 * @param activeFlag the active flag
	 * @return the list
	 */
	List<Object[]> findActiveTAttrGroupByTBussObjTmpl(Integer tmplId,
			Short tenantId, Character activeFlag);

	/**
	 * Find active groups by t buss obj tmpl.
	 *
	 * @param tmplId the tmpl id
	 * @param activeFlag the active flag
	 * @param tenantId the tenant id
	 * @return the list
	 */
	List<Object[]> findActiveGroupsByTBussObjTmpl(Integer tmplId,
			Character activeFlag, Short tenantId);
	
//	int deleteFromTAttrGroupHistTable(Integer attrGroupId);
	
}
