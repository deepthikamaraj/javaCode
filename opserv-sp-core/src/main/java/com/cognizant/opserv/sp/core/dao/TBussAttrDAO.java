package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TBussAttr;
import com.cognizant.opserv.sp.core.entity.TBussEntity;
import com.cognizant.opserv.sp.core.entity.TBussObj;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TBussAttr DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TBussAttrDAO {

	/**
	 * Stores a new TBussAttr entity object in to the persistent store
	 * 
	 * @param tBussAttr
	 *            TBussAttr Entity object to be persisted
	 * @return tBussAttr Persisted TBussAttr object
	 */
	TBussAttr createTBussAttr(TBussAttr tBussAttr);

	/**
	 * Deletes a TBussAttr entity object from the persistent store
	 * 
	 * @param tBussAttr
	 *            TBussAttr Entity object to be deleted
	 */
	void deleteTBussAttr(String attrId);

	/**
	 * Updates a TBussAttr entity object in to the persistent store
	 * 
	 * @param tBussAttr
	 *            TBussAttr Entity object to be updated
	 * @return tBussAttr Persisted TBussAttr object
	 */
	TBussAttr updateTBussAttr(TBussAttr tBussAttr);

	/**
	 * Retrieve an TBussAttr object based on given attrId.
	 * 
	 * @param attrId
	 *            the primary key value of the TBussAttr Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TBussAttr findTBussAttrById(String attrId);

	/**
	 * Retrieve TBussAttr based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TBussAttr> list of TBussAttrs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TBussAttr> findTBussAttrs(SearchFilter<TBussAttr> searchFilter);

	/**
	 * Count TBussAttr based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTBussAttrs(SearchFilter<TBussAttr> searchFilter);

	/**
	 * Retrieve TBussAttr based on given search criteria using JPA named Query.
	 * The search criteria is of TBussEntity type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TBussAttr> list of TBussAttrs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TBussAttr> getTBussAttrsByTBussEntity(SearchFilter<TBussEntity> searchFilter);

	/**
	 * Count TBussAttr based on given search criteria using JPA named Query.
	 * The search criteria is of TBussEntity type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTBussAttrsByTBussEntity(SearchFilter<TBussEntity> searchFilter);

	/**
	 * Retrieve TBussAttr based on given search criteria using JPA named Query.
	 * The search criteria is of TBussObj type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TBussAttr> list of TBussAttrs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TBussAttr> getTBussAttrsByTBussObj(SearchFilter<TBussObj> searchFilter);

	/**
	 * Count TBussAttr based on given search criteria using JPA named Query.
	 * The search criteria is of TBussObj type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTBussAttrsByTBussObj(SearchFilter<TBussObj> searchFilter);
	
	
	/**
	 * @param bussEntity
	 * @return List<TBussAttr>
	 */
	List<TBussAttr> getTBussAttrsByTBussEntity(TBussEntity bussEntity);

}
