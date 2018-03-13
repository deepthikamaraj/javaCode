package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TClassType;
import com.cognizant.opserv.sp.core.entity.TClassValue;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TClassValue DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TClassValueDAO {

	/**
	 * Stores a new TClassValue entity object in to the persistent store
	 * 
	 * @param tClassValue
	 *            TClassValue Entity object to be persisted
	 * @return tClassValue Persisted TClassValue object
	 */
	TClassValue createTClassValue(TClassValue tClassValue);

	/**
	 * Deletes a TClassValue entity object from the persistent store
	 * 
	 * @param tClassValue
	 *            TClassValue Entity object to be deleted
	 */
	void deleteTClassValue(Integer classValueId);

	/**
	 * Updates a TClassValue entity object in to the persistent store
	 * 
	 * @param tClassValue
	 *            TClassValue Entity object to be updated
	 * @return tClassValue Persisted TClassValue object
	 */
	TClassValue updateTClassValue(TClassValue tClassValue);

	/**
	 * Retrieve an TClassValue object based on given classValueId.
	 * 
	 * @param classValueId
	 *            the primary key value of the TClassValue Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TClassValue findTClassValueById(Integer classValueId);

	/**
	 * Retrieve TClassValue based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TClassValue> list of TClassValues if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TClassValue> findTClassValues(SearchFilter<TClassValue> searchFilter);

	/**
	 * Count TClassValue based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTClassValues(SearchFilter<TClassValue> searchFilter);

	/**
	 * Retrieve TClassValue based on given search criteria using JPA named Query.
	 * The search criteria is of TClassType type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TClassValue> list of TClassValues if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TClassValue> getTClassValuesByTClassType(SearchFilter<TClassType> searchFilter);

	/**
	 * Count TClassValue based on given search criteria using JPA named Query.
	 * The search criteria is of TClassType type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTClassValuesByTClassType(SearchFilter<TClassType> searchFilter);

}
