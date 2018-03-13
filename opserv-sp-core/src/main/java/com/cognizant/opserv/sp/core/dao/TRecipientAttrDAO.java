package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TAttr;
import com.cognizant.opserv.sp.core.entity.TRecipientAttr;
import com.cognizant.opserv.sp.core.entity.TRecipientAttrId;
import com.cognizant.opserv.sp.core.entity.TTargetRecipientPref;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TRecipientAttr DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TRecipientAttrDAO {

	/**
	 * Stores a new TRecipientAttr entity object in to the persistent store
	 * 
	 * @param tRecipientAttr
	 *            TRecipientAttr Entity object to be persisted
	 * @return tRecipientAttr Persisted TRecipientAttr object
	 */
	TRecipientAttr createTRecipientAttr(TRecipientAttr tRecipientAttr);

	/**
	 * Deletes a TRecipientAttr entity object from the persistent store
	 * 
	 * @param tRecipientAttr
	 *            TRecipientAttr Entity object to be deleted
	 */
	void deleteTRecipientAttr(TRecipientAttrId tRecipientAttrId);

	/**
	 * Updates a TRecipientAttr entity object in to the persistent store
	 * 
	 * @param tRecipientAttr
	 *            TRecipientAttr Entity object to be updated
	 * @return tRecipientAttr Persisted TRecipientAttr object
	 */
	TRecipientAttr updateTRecipientAttr(TRecipientAttr tRecipientAttr);

	/**
	 * Retrieve an TRecipientAttr object based on given TRecipientAttrId.
	 * 
	 * @param tRecipientAttrId
	 *            the primary key value of the TRecipientAttr Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TRecipientAttr findTRecipientAttrById(TRecipientAttrId tRecipientAttrId);

	/**
	 * Retrieve TRecipientAttr based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRecipientAttr> list of TRecipientAttrs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TRecipientAttr> findTRecipientAttrs(SearchFilter<TRecipientAttr> searchFilter);

	/**
	 * Count TRecipientAttr based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTRecipientAttrs(SearchFilter<TRecipientAttr> searchFilter);

	/**
	 * Retrieve TRecipientAttr based on given search criteria using JPA named Query.
	 * The search criteria is of TTargetRecipientPref type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRecipientAttr> list of TRecipientAttrs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TRecipientAttr> getTRecipientAttrsByTTargetRecipientPref(SearchFilter<TTargetRecipientPref> searchFilter);

	/**
	 * Count TRecipientAttr based on given search criteria using JPA named Query.
	 * The search criteria is of TTargetRecipientPref type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTRecipientAttrsByTTargetRecipientPref(SearchFilter<TTargetRecipientPref> searchFilter);

	/**
	 * Retrieve TRecipientAttr based on given search criteria using JPA named Query.
	 * The search criteria is of TAttr type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRecipientAttr> list of TRecipientAttrs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TRecipientAttr> getTRecipientAttrsByTAttr(SearchFilter<TAttr> searchFilter);

	/**
	 * Count TRecipientAttr based on given search criteria using JPA named Query.
	 * The search criteria is of TAttr type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTRecipientAttrsByTAttr(SearchFilter<TAttr> searchFilter);
	
	List<Object[]> findTRecipientAttrsByRecipientPrefId(Integer recipientPrefId); 

}
