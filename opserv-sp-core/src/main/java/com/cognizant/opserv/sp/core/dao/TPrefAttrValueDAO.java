package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TPrefAttrValue;
import com.cognizant.opserv.sp.core.entity.TRecipientAttr;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TPrefAttrValue DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TPrefAttrValueDAO {

	/**
	 * Stores a new TPrefAttrValue entity object in to the persistent store
	 * 
	 * @param tPrefAttrValue
	 *            TPrefAttrValue Entity object to be persisted
	 * @return tPrefAttrValue Persisted TPrefAttrValue object
	 */
	TPrefAttrValue createTPrefAttrValue(TPrefAttrValue tPrefAttrValue);

	/**
	 * Deletes a TPrefAttrValue entity object from the persistent store
	 * 
	 * @param tPrefAttrValue
	 *            TPrefAttrValue Entity object to be deleted
	 */
	void deleteTPrefAttrValue(Integer attrValueSequenceId);

	/**
	 * Updates a TPrefAttrValue entity object in to the persistent store
	 * 
	 * @param tPrefAttrValue
	 *            TPrefAttrValue Entity object to be updated
	 * @return tPrefAttrValue Persisted TPrefAttrValue object
	 */
	TPrefAttrValue updateTPrefAttrValue(TPrefAttrValue tPrefAttrValue);

	/**
	 * Retrieve an TPrefAttrValue object based on given attrValueSequenceId.
	 * 
	 * @param attrValueSequenceId
	 *            the primary key value of the TPrefAttrValue Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TPrefAttrValue findTPrefAttrValueById(Integer attrValueSequenceId);

	/**
	 * Retrieve TPrefAttrValue based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TPrefAttrValue> list of TPrefAttrValues if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TPrefAttrValue> findTPrefAttrValues(SearchFilter<TPrefAttrValue> searchFilter);

	/**
	 * Count TPrefAttrValue based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTPrefAttrValues(SearchFilter<TPrefAttrValue> searchFilter);

	/**
	 * Retrieve TPrefAttrValue based on given search criteria using JPA named Query.
	 * The search criteria is of TRecipientAttr type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TPrefAttrValue> list of TPrefAttrValues if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TPrefAttrValue> getTPrefAttrValuesByTRecipientAttr(SearchFilter<TRecipientAttr> searchFilter);

	/**
	 * Count TPrefAttrValue based on given search criteria using JPA named Query.
	 * The search criteria is of TRecipientAttr type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTPrefAttrValuesByTRecipientAttr(SearchFilter<TRecipientAttr> searchFilter);

}
