package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TAttrDef;
import com.cognizant.opserv.sp.core.entity.TMtr;
import com.cognizant.opserv.sp.core.entity.TMtrAttr;
import com.cognizant.opserv.sp.core.entity.TMtrAttrId;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TMtrAttr DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TMtrAttrDAO {

	/**
	 * Stores a new TMtrAttr entity object in to the persistent store
	 * 
	 * @param tMtrAttr
	 *            TMtrAttr Entity object to be persisted
	 * @return tMtrAttr Persisted TMtrAttr object
	 */
	TMtrAttr createTMtrAttr(TMtrAttr tMtrAttr);

	/**
	 * Deletes a TMtrAttr entity object from the persistent store
	 * 
	 * @param tMtrAttr
	 *            TMtrAttr Entity object to be deleted
	 */
	void deleteTMtrAttr(TMtrAttrId tMtrAttrId);

	/**
	 * Updates a TMtrAttr entity object in to the persistent store
	 * 
	 * @param tMtrAttr
	 *            TMtrAttr Entity object to be updated
	 * @return tMtrAttr Persisted TMtrAttr object
	 */
	TMtrAttr updateTMtrAttr(TMtrAttr tMtrAttr);

	/**
	 * Retrieve an TMtrAttr object based on given TMtrAttrId.
	 * 
	 * @param tMtrAttrId
	 *            the primary key value of the TMtrAttr Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TMtrAttr findTMtrAttrById(TMtrAttrId tMtrAttrId);

	/**
	 * Retrieve TMtrAttr based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TMtrAttr> list of TMtrAttrs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TMtrAttr> findTMtrAttrs(SearchFilter<TMtrAttr> searchFilter);

	/**
	 * Count TMtrAttr based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTMtrAttrs(SearchFilter<TMtrAttr> searchFilter);

	/**
	 * Retrieve TMtrAttr based on given search criteria using JPA named Query.
	 * The search criteria is of TMtr type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TMtrAttr> list of TMtrAttrs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TMtrAttr> getTMtrAttrsByTMtr(SearchFilter<TMtr> searchFilter);

	/**
	 * Count TMtrAttr based on given search criteria using JPA named Query.
	 * The search criteria is of TMtr type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTMtrAttrsByTMtr(SearchFilter<TMtr> searchFilter);

	/**
	 * Retrieve TMtrAttr based on given search criteria using JPA named Query.
	 * The search criteria is of TAttrDef type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TMtrAttr> list of TMtrAttrs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TMtrAttr> getTMtrAttrsByTAttrDef(SearchFilter<TAttrDef> searchFilter);

	/**
	 * Count TMtrAttr based on given search criteria using JPA named Query.
	 * The search criteria is of TAttrDef type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTMtrAttrsByTAttrDef(SearchFilter<TAttrDef> searchFilter);

}
