package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TPrtType;
import com.cognizant.opserv.sp.core.entity.TPrtTypeId;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TPrtType DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TPrtTypeDAO {

	/**
	 * Stores a new TPrtType entity object in to the persistent store
	 * 
	 * @param tPrtType
	 *            TPrtType Entity object to be persisted
	 * @return tPrtType Persisted TPrtType object
	 */
	TPrtType createTPrtType(TPrtType tPrtType);

	/**
	 * Deletes a TPrtType entity object from the persistent store
	 * 
	 * @param tPrtType
	 *            TPrtType Entity object to be deleted
	 */
	void deleteTPrtType(TPrtTypeId tPrtTypeId);

	/**
	 * Updates a TPrtType entity object in to the persistent store
	 * 
	 * @param tPrtType
	 *            TPrtType Entity object to be updated
	 * @return tPrtType Persisted TPrtType object
	 */
	TPrtType updateTPrtType(TPrtType tPrtType);

	/**
	 * Retrieve an TPrtType object based on given TPrtTypeId.
	 * 
	 * @param tPrtTypeId
	 *            the primary key value of the TPrtType Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TPrtType findTPrtTypeById(TPrtTypeId tPrtTypeId);

	/**
	 * Retrieve TPrtType based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TPrtType> list of TPrtTypes if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TPrtType> findTPrtTypes(SearchFilter<TPrtType> searchFilter);

	/**
	 * Count TPrtType based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTPrtTypes(SearchFilter<TPrtType> searchFilter);
	/**
	 * Retrieve an object based on given ActiveTypesForLocale.
	 * 
	 * @param localeId
	 * @param tenantId
	 * 
	 * @return an ObjectActiveTypesForLocale
	 */
	List<Object[]> findActiveTypesForLocale(String localeId,Short tenantId);
	/**
	 * Retrieve String based on PrtNameForLocale.
	 * 
	 * @param localeId
	 * @param prtTypeID
	 * 
	 * @return String of PrtNameForLocale
	 */
	String findPrtNameForLocale(Integer prtTypeID,String localeId);
	/**
	 * Retrieve an object based on given ActiveTypesWithDesc
	 * 
	 * @param localeId
	 * @param tenantId
	 * @param Desc
	 * 
	 * @return an ActiveTypesWithDesc
	 */
	List<Object[]> FindActiveTypesWithDesc(String Desc,String localeId,Short tenantId);
	/**
	 * Retrieve an object based on given ActiveTypesWithDescFrView.
	 * 
	 * @param localeId
	 * @param tenantId
	 * @param Desc
	 * @return an ActiveTypesWithDesc
	 */
	List<Object[]> FindActiveTypesWithDescFrView(String Desc,String localeId, Short tenantId);

}
