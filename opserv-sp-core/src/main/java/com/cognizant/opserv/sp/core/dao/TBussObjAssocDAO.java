package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TBussFunction;
import com.cognizant.opserv.sp.core.entity.TBussObj;
import com.cognizant.opserv.sp.core.entity.TBussObjAssoc;
import com.cognizant.opserv.sp.core.entity.TBussObjAssocId;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TBussObjAssoc DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TBussObjAssocDAO {

	/**
	 * Stores a new TBussObjAssoc entity object in to the persistent store
	 * 
	 * @param tBussObjAssoc
	 *            TBussObjAssoc Entity object to be persisted
	 * @return tBussObjAssoc Persisted TBussObjAssoc object
	 */
	TBussObjAssoc createTBussObjAssoc(TBussObjAssoc tBussObjAssoc);

	/**
	 * Deletes a TBussObjAssoc entity object from the persistent store
	 * 
	 * @param tBussObjAssoc
	 *            TBussObjAssoc Entity object to be deleted
	 */
	void deleteTBussObjAssoc(TBussObjAssocId tBussObjAssocId);

	/**
	 * Updates a TBussObjAssoc entity object in to the persistent store
	 * 
	 * @param tBussObjAssoc
	 *            TBussObjAssoc Entity object to be updated
	 * @return tBussObjAssoc Persisted TBussObjAssoc object
	 */
	TBussObjAssoc updateTBussObjAssoc(TBussObjAssoc tBussObjAssoc);

	/**
	 * Retrieve an TBussObjAssoc object based on given TBussObjAssocId.
	 * 
	 * @param tBussObjAssocId
	 *            the primary key value of the TBussObjAssoc Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TBussObjAssoc findTBussObjAssocById(TBussObjAssocId tBussObjAssocId);

	/**
	 * Retrieve TBussObjAssoc based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TBussObjAssoc> list of TBussObjAssocs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TBussObjAssoc> findTBussObjAssocs(SearchFilter<TBussObjAssoc> searchFilter);

	/**
	 * Count TBussObjAssoc based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTBussObjAssocs(SearchFilter<TBussObjAssoc> searchFilter);

	/**
	 * Retrieve TBussObjAssoc based on given search criteria using JPA named Query.
	 * The search criteria is of TBussFunction type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TBussObjAssoc> list of TBussObjAssocs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TBussObjAssoc> getTBussObjAssocsByTBussFunction(SearchFilter<TBussFunction> searchFilter);

	/**
	 * Count TBussObjAssoc based on given search criteria using JPA named Query.
	 * The search criteria is of TBussFunction type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTBussObjAssocsByTBussFunction(SearchFilter<TBussFunction> searchFilter);

	/**
	 * Retrieve TBussObjAssoc based on given search criteria using JPA named Query.
	 * The search criteria is of TBussObj type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TBussObjAssoc> list of TBussObjAssocs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TBussObjAssoc> getTBussObjAssocsByTBussObj(SearchFilter<TBussObj> searchFilter);

	/**
	 * Count TBussObjAssoc based on given search criteria using JPA named Query.
	 * The search criteria is of TBussObj type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTBussObjAssocsByTBussObj(SearchFilter<TBussObj> searchFilter);

}
