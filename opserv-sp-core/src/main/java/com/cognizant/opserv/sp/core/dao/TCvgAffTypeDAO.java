package com.cognizant.opserv.sp.core.dao;


import java.util.List;

import com.cognizant.opserv.sp.core.entity.TCvgAffType;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TCvgAffType DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TCvgAffTypeDAO {

	/**
	 * Stores a new TCvgAffType entity object in to the persistent store
	 * 
	 * @param tCvgAffType
	 *            TCvgAffType Entity object to be persisted
	 * @return tCvgAffType Persisted TCvgAffType object
	 */
	TCvgAffType createTCvgAffType(TCvgAffType tCvgAffType);

	/**
	 * Deletes a TCvgAffType entity object from the persistent store
	 * 
	 * @param tCvgAffType
	 *            TCvgAffType Entity object to be deleted
	 */
	//void deleteTCvgAffType(TCvgAffTypeId tCvgAffTypeId);

	/**
	 * Updates a TCvgAffType entity object in to the persistent store
	 * 
	 * @param tCvgAffType
	 *            TCvgAffType Entity object to be updated
	 * @return tCvgAffType Persisted TCvgAffType object
	 */
	TCvgAffType updateTCvgAffType(TCvgAffType tCvgAffType);

	/**
	 * Retrieve an TCvgAffType object based on given TCvgAffTypeId.
	 * 
	 * @param tCvgAffTypeId
	 *            the primary key value of the TCvgAffType Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	//TCvgAffType findTCvgAffTypeById(TCvgAffTypeId tCvgAffTypeId);

	/**
	 * Retrieve TCvgAffType based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCvgAffType> list of TCvgAffTypes if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TCvgAffType> findTCvgAffTypes(SearchFilter<TCvgAffType> searchFilter);

	/**
	 * Count TCvgAffType based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTCvgAffTypes(SearchFilter<TCvgAffType> searchFilter);
	
	/**
	 * findTCvgAffTypesByTnIdLocaleId based on the tenatId and LocaleId
	 * @param tenantId
	 * @param localeId
	 * @return List<TCvgAffType> list of TCvgAffTypes if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TCvgAffType> findTCvgAffTypesByTnIdLocaleId(Short tenantId, String localeId);

}
