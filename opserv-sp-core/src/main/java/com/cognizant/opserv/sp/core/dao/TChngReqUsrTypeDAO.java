package com.cognizant.opserv.sp.core.dao;



import java.util.List;

import com.cognizant.opserv.sp.core.entity.TChngReqUsrType;
import com.cognizant.opserv.sp.core.entity.TChngReqUsrTypeId;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TChngReqUsrType DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TChngReqUsrTypeDAO {

	/**
	 * Stores a new TChngReqUsrType entity object in to the persistent store
	 * 
	 * @param tChngReqUsrType
	 *            TChngReqUsrType Entity object to be persisted
	 * @return tChngReqUsrType Persisted TChngReqUsrType object
	 */
	TChngReqUsrType createTChngReqUsrType(TChngReqUsrType tChngReqUsrType);

	/**
	 * Deletes a TChngReqUsrType entity object from the persistent store
	 * 
	 * @param tChngReqUsrType
	 *            TChngReqUsrType Entity object to be deleted
	 */
	void deleteTChngReqUsrType(TChngReqUsrTypeId tChngReqUsrTypeId);

	/**
	 * Updates a TChngReqUsrType entity object in to the persistent store
	 * 
	 * @param tChngReqUsrType
	 *            TChngReqUsrType Entity object to be updated
	 * @return tChngReqUsrType Persisted TChngReqUsrType object
	 */
	TChngReqUsrType updateTChngReqUsrType(TChngReqUsrType tChngReqUsrType);

	/**
	 * Retrieve an TChngReqUsrType object based on given TChngReqUsrTypeId.
	 * 
	 * @param tChngReqUsrTypeId
	 *            the primary key value of the TChngReqUsrType Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TChngReqUsrType findTChngReqUsrTypeById(TChngReqUsrTypeId tChngReqUsrTypeId);

	/**
	 * Retrieve TChngReqUsrType based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TChngReqUsrType> list of TChngReqUsrTypes if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TChngReqUsrType> findTChngReqUsrTypes(SearchFilter<TChngReqUsrType> searchFilter);

	/**
	 * Count TChngReqUsrType based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTChngReqUsrTypes(SearchFilter<TChngReqUsrType> searchFilter);

	/**
	 * Fetch all t chng req usr types by tenants.
	 *
	 * @return the list
	 */
	List<TChngReqUsrType> fetchAllTChngReqUsrTypesByTenants();
	
	/**
	 * Fetch all t chng req usr types by tenants.
	 *
	 * @param tenantId the tenant id
	 * @return the list
	 */
	List<TChngReqUsrType> fetchAllTChngReqUsrTypesByTenants(Short tenantId);
	
	/**
	 * Find t usr desc.
	 *
	 * @param usrTypeId the usr type id
	 * @param userDetails the user details
	 * @return the list
	 */
	List<Object[]> findTUsrDesc(Integer usrTypeId, Short tenantId, String localeId);
	
}
