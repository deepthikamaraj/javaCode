package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TBussObj;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TBussObj DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TBussObjDAO {

	/**
	 * Stores a new TBussObj entity object in to the persistent store
	 * 
	 * @param tBussObj
	 *            TBussObj Entity object to be persisted
	 * @return tBussObj Persisted TBussObj object
	 */
	TBussObj createTBussObj(TBussObj tBussObj);

	/**
	 * Deletes a TBussObj entity object from the persistent store
	 * 
	 * @param tBussObj
	 *            TBussObj Entity object to be deleted
	 */
	void deleteTBussObj(Integer bussObjId);

	/**
	 * Updates a TBussObj entity object in to the persistent store
	 * 
	 * @param tBussObj
	 *            TBussObj Entity object to be updated
	 * @return tBussObj Persisted TBussObj object
	 */
	TBussObj updateTBussObj(TBussObj tBussObj);

	/**
	 * Retrieve an TBussObj object based on given bussObjId.
	 * 
	 * @param bussObjId
	 *            the primary key value of the TBussObj Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TBussObj findTBussObjById(Integer bussObjId);

	/**
	 * Retrieve TBussObj based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TBussObj> list of TBussObjs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TBussObj> findTBussObjs(SearchFilter<TBussObj> searchFilter);

	/**
	 * Count TBussObj based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTBussObjs(SearchFilter<TBussObj> searchFilter);

	/**
	 * getAttributeBussFuncTypeInTBussObjs
	 * 
	 * @param tenantId
	 * @return
	 */
	List<TBussObj> getAttributeBussFuncTypeInTBussObjs(Short tenantId);

	/**
	 * fetchBussObjByFunctionId
	 * 
	 * @param busfId
	 * @param tenantId
	 * @return
	 */
	List<TBussObj> fetchBussObjByFunctionId(Integer busfId, Short tenantId);

	/**
	 * findTBussObjByBussFuncTypeBussNameAndTenant
	 * 
	 * @param SPName
	 * @param tenantId
	 * @return
	 */
	List<TBussObj> findTBussObjByBussFuncTypeBussNameAndTenant(String SPName,
			Short tenantId);
}
