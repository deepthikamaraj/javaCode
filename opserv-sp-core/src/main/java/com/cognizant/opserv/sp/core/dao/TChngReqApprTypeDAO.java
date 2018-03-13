package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TChngReqApprType;
import com.cognizant.opserv.sp.core.entity.TChngReqApprTypeId;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TChngReqApprType DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TChngReqApprTypeDAO {

	/**
	 * Stores a new TChngReqApprType entity object in to the persistent store
	 * 
	 * @param tChngReqApprType
	 *            TChngReqApprType Entity object to be persisted
	 * @return tChngReqApprType Persisted TChngReqApprType object
	 */
	TChngReqApprType createTChngReqApprType(TChngReqApprType tChngReqApprType);

	/**
	 * Deletes a TChngReqApprType entity object from the persistent store
	 * 
	 * @param tChngReqApprType
	 *            TChngReqApprType Entity object to be deleted
	 */
	void deleteTChngReqApprType(TChngReqApprTypeId tChngReqApprTypeId);

	/**
	 * Updates a TChngReqApprType entity object in to the persistent store
	 * 
	 * @param tChngReqApprType
	 *            TChngReqApprType Entity object to be updated
	 * @return tChngReqApprType Persisted TChngReqApprType object
	 */
	TChngReqApprType updateTChngReqApprType(TChngReqApprType tChngReqApprType);

	/**
	 * Retrieve an TChngReqApprType object based on given TChngReqApprTypeId.
	 * 
	 * @param tChngReqApprTypeId
	 *            the primary key value of the TChngReqApprType Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TChngReqApprType findTChngReqApprTypeById(TChngReqApprTypeId tChngReqApprTypeId);

	/**
	 * Retrieve TChngReqApprType based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TChngReqApprType> list of TChngReqApprTypes if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TChngReqApprType> findTChngReqApprTypes(SearchFilter<TChngReqApprType> searchFilter);

	/**
	 * Count TChngReqApprType based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTChngReqApprTypes(SearchFilter<TChngReqApprType> searchFilter);
	
	/**
	 * Find appr type by locale.
	 *
	 * @param tenantId the tenant id
	 * @param localeId the locale id
	 * @return the list
	 */
	List<TChngReqApprType> findApprTypeByLocale(short tenantId,String localeId);
	
	/**
	 * Find t chng req appr type by change req config prapprtypeid.
	 *
	 * @param tenantId the tenant id
	 * @param aprTypeId the apr type id
	 * @return the list
	 */
	List<Object> findTChngReqApprTypeByChangeReqConfigPrapprtypeid(short tenantId,Integer aprTypeId);
	
	/**
	 * Find appr types.
	 *
	 * @param tenantId the tenant id
	 * @param localeId the locale id
	 * @return the list
	 */
	List<Object[]> findApprTypes(short tenantId,String localeId);

}
