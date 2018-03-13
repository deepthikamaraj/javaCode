package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TAttrDataType;
import com.cognizant.opserv.sp.core.entity.TAttrDataTypeId;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TAttrDataType DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TAttrDataTypeDAO {

	/**
	 * Stores a new TAttrDataType entity object in to the persistent store
	 * 
	 * @param tAttrDataType
	 *            TAttrDataType Entity object to be persisted
	 * @return tAttrDataType Persisted TAttrDataType object
	 */
	TAttrDataType createTAttrDataType(TAttrDataType tAttrDataType);

	/**
	 * Deletes a TAttrDataType entity object from the persistent store
	 * 
	 * @param tAttrDataType
	 *            TAttrDataType Entity object to be deleted
	 */
	void deleteTAttrDataType(Integer attrDataTypeId);

	/**
	 * Updates a TAttrDataType entity object in to the persistent store
	 * 
	 * @param tAttrDataType
	 *            TAttrDataType Entity object to be updated
	 * @return tAttrDataType Persisted TAttrDataType object
	 */
	TAttrDataType updateTAttrDataType(TAttrDataType tAttrDataType);

	/**
	 * Retrieve an TAttrDataType object based on given attrDataTypeId.
	 * 
	 * @param attrDataTypeId
	 *            the primary key value of the TAttrDataType Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TAttrDataType findTAttrDataTypeById(TAttrDataTypeId attrDataTypeId);

	/**
	 * Retrieve TAttrDataType based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TAttrDataType> list of TAttrDataTypes if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TAttrDataType> findTAttrDataTypes(SearchFilter<TAttrDataType> searchFilter);

	/**
	 * Count TAttrDataType based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTAttrDataTypes(SearchFilter<TAttrDataType> searchFilter);

	/**
	 * Find t attr data type by id.
	 *
	 * @param attrDataTypeId the attr data type id
	 * @param localeId the locale id
	 * @return the t attr data type
	 */
	TAttrDataType findTAttrDataTypeById(Integer attrDataTypeId, String localeId);

	/**
	 * Find t attr data types by locale id.
	 *
	 * @param localeId the locale id
	 * @param activeFlag the active flag
	 * @return the list
	 */
	List<Object[]> findTAttrDataTypesByLocaleId(String localeId,
			Character activeFlag);

}
