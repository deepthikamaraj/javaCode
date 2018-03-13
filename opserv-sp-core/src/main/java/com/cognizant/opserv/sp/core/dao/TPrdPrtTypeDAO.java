package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TPrdPrtType;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TPrdPrtType DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TPrdPrtTypeDAO {

	/**
	 * Stores a new TPrdPrtType entity object in to the persistent store
	 * 
	 * @param tPrdPrtType
	 *            TPrdPrtType Entity object to be persisted
	 * @return tPrdPrtType Persisted TPrdPrtType object
	 */
	TPrdPrtType createTPrdPrtType(TPrdPrtType tPrdPrtType);

	/**
	 * Deletes a TPrdPrtType entity object from the persistent store
	 * 
	 * @param tPrdPrtType
	 *            TPrdPrtType Entity object to be deleted
	 */
	void deleteTPrdPrtType(Integer prdPrtTypeId);

	/**
	 * Updates a TPrdPrtType entity object in to the persistent store
	 * 
	 * @param tPrdPrtType
	 *            TPrdPrtType Entity object to be updated
	 * @return tPrdPrtType Persisted TPrdPrtType object
	 */
	TPrdPrtType updateTPrdPrtType(TPrdPrtType tPrdPrtType);

	/**
	 * Retrieve an TPrdPrtType object based on given prdPrtTypeId.
	 * 
	 * @param prdPrtTypeId
	 *            the primary key value of the TPrdPrtType Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TPrdPrtType findTPrdPrtTypeById(Integer prdPrtTypeId);

	/**
	 * Retrieve TPrdPrtType based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TPrdPrtType> list of TPrdPrtTypes if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TPrdPrtType> findTPrdPrtTypes(SearchFilter<TPrdPrtType> searchFilter);

	/**
	 * Count TPrdPrtType based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTPrdPrtTypes(SearchFilter<TPrdPrtType> searchFilter);

	/**
	 * Fetch Product Priority Description for given PrdPrtTypesID.
	 * 
	 * @param tPrdPrtType
	 * 
	 * @return
	 */
	List<Object[]> findPrdPrtTypeDesc(TPrdPrtType tPrdPrtType);

}
