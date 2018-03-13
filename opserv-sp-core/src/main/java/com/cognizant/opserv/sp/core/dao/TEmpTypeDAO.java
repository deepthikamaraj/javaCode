package com.cognizant.opserv.sp.core.dao;


import java.util.List;

import com.cognizant.opserv.sp.core.entity.TEmpType;
import com.cognizant.opserv.sp.core.entity.TEmpTypeId;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TEmpType DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TEmpTypeDAO {

	/**
	 * Stores a new TEmpType entity object in to the persistent store
	 * 
	 * @param tEmpType
	 *            TEmpType Entity object to be persisted
	 * @return tEmpType Persisted TEmpType object
	 */
	TEmpType createTEmpType(TEmpType tEmpType);

	/**
	 * Deletes a TEmpType entity object from the persistent store
	 * 
	 * @param tEmpType
	 *            TEmpType Entity object to be deleted
	 */
	void deleteTEmpType(TEmpTypeId tEmpTypeId);

	/**
	 * Updates a TEmpType entity object in to the persistent store
	 * 
	 * @param tEmpType
	 *            TEmpType Entity object to be updated
	 * @return tEmpType Persisted TEmpType object
	 */
	TEmpType updateTEmpType(TEmpType tEmpType);

	/**
	 * Retrieve an TEmpType object based on given TEmpTypeId.
	 * 
	 * @param tEmpTypeId
	 *            the primary key value of the TEmpType Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TEmpType findTEmpTypeById(TEmpTypeId tEmpTypeId);

	/**
	 * Retrieve TEmpType based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TEmpType> list of TEmpTypes if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TEmpType> findTEmpTypes(SearchFilter<TEmpType> searchFilter);

	/**
	 * Count TEmpType based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTEmpTypes(SearchFilter<TEmpType> searchFilter);

	/**find TEmpType based on tenantId and locale
	 * @param tenantId
	 * @param locale
	 * @return List<TEmpType>
	 */
	List<TEmpType> findEmpTypeByLocale(Short tenantId, String locale);

}
