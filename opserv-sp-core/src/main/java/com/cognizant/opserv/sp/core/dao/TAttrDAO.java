package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TAttr;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TAttr DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TAttrDAO {

	/**
	 * Stores a new TAttr entity object in to the persistent store
	 * 
	 * @param tAttr
	 *            TAttr Entity object to be persisted
	 * @return tAttr Persisted TAttr object
	 */
	TAttr createTAttr(TAttr tAttr);

	/**
	 * Deletes a TAttr entity object from the persistent store
	 * 
	 * @param tAttr
	 *            TAttr Entity object to be deleted
	 */
	void deleteTAttr(Long attrId);

	/**
	 * Updates a TAttr entity object in to the persistent store
	 * 
	 * @param tAttr
	 *            TAttr Entity object to be updated
	 * @return tAttr Persisted TAttr object
	 */
	TAttr updateTAttr(TAttr tAttr);

	/**
	 * Retrieve an TAttr object based on given attrId.
	 * 
	 * @param attrId
	 *            the primary key value of the TAttr Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TAttr findTAttrById(Long attrId);

	/**
	 * Retrieve TAttr based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TAttr> list of TAttrs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TAttr> findTAttrs(SearchFilter<TAttr> searchFilter);

	/**
	 * Count TAttr based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTAttrs(SearchFilter<TAttr> searchFilter);

	/**
	 * Retrieve TAttr based on given search criteria using JPA named Query.
	 * The search criteria is of TAttrGroup type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TAttr> list of TAttrs if it exists against given
	 *         criteria. Returns null if not found
	 */
	//List<TAttr> getTAttrsByTAttrGroup(SearchFilter<TAttrGroup> searchFilter);

	/**
	 * Count TAttr based on given search criteria using JPA named Query.
	 * The search criteria is of TAttrGroup type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	//Object countTAttrsByTAttrGroup(SearchFilter<TAttrGroup> searchFilter);

	/**
	 * Retrieve TAttr based on given search criteria using JPA named Query.
	 * The search criteria is of TAttrDataType type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TAttr> list of TAttrs if it exists against given
	 *         criteria. Returns null if not found
	 */
	//List<TAttr> getTAttrsByTAttrDataType(SearchFilter<TAttrDataType> searchFilter);

	/**
	 * Count TAttr based on given search criteria using JPA named Query.
	 * The search criteria is of TAttrDataType type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	//Object countTAttrsByTAttrDataType(SearchFilter<TAttrDataType> searchFilter);

}
