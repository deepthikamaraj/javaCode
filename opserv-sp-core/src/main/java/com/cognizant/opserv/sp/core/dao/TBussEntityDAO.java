package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TBussEntity;
import com.cognizant.opserv.sp.core.entity.TBussObj;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TBussEntity DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TBussEntityDAO {

	/**
	 * Stores a new TBussEntity entity object in to the persistent store
	 * 
	 * @param tBussEntity
	 *            TBussEntity Entity object to be persisted
	 * @return tBussEntity Persisted TBussEntity object
	 */
	TBussEntity createTBussEntity(TBussEntity tBussEntity);

	/**
	 * Deletes a TBussEntity entity object from the persistent store
	 * 
	 * @param tBussEntity
	 *            TBussEntity Entity object to be deleted
	 */
	void deleteTBussEntity(String entityId);

	/**
	 * Updates a TBussEntity entity object in to the persistent store
	 * 
	 * @param tBussEntity
	 *            TBussEntity Entity object to be updated
	 * @return tBussEntity Persisted TBussEntity object
	 */
	TBussEntity updateTBussEntity(TBussEntity tBussEntity);

	/**
	 * Retrieve an TBussEntity object based on given entityId.
	 * 
	 * @param bussObjId
	 *            the primary key value of the TBussEntity Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TBussEntity findTBussEntityById(Integer bussObjId);

	/**
	 * Retrieve TBussEntity based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TBussEntity> list of TBussEntitys if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TBussEntity> findTBussEntitys(SearchFilter<TBussEntity> searchFilter);

	/**
	 * Count TBussEntity based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTBussEntitys(SearchFilter<TBussEntity> searchFilter);

	/**
	 * Retrieve TBussEntity based on given search criteria using JPA named Query.
	 * The search criteria is of TBussObj type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TBussEntity> list of TBussEntitys if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TBussEntity> getTBussEntitysByTBussObj(SearchFilter<TBussObj> searchFilter);

	/**
	 * Count TBussEntity based on given search criteria using JPA named Query.
	 * The search criteria is of TBussObj type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTBussEntitysByTBussObj(SearchFilter<TBussObj> searchFilter);
	
	/**
	 * Gets the t buss entitys by t pern id.
	 *
	 * @param searchFilter the search filter
	 * @return the t buss entitys by t pern id
	 */
	List<TBussEntity> getTBussEntitysByTPernId(final SearchFilter<TBussEntity> searchFilter);
	
	/**
	 * Find all t buss entitys by drv flag.
	 *
	 * @return the list
	 */
	List<TBussEntity> findAllTBussEntitysByDrvFlag();
	
	/**
	 * Find all t buss entitys by t buss obj.
	 *
	 * @param tBussObj the t buss obj
	 * @return the list
	 */
	List<TBussEntity> findAllTBussEntitysByTBussObj(TBussObj tBussObj);

	/**
	 * Find buss entities by tenant id.
	 *
	 * @param tenantId the tenant id
	 * @return the list
	 */
	List<TBussEntity> findBussEntitiesByTenantId(Short tenantId);

	/**
	 * Find t buss entities by tenant id.
	 *
	 * @param tenantId the tenant id
	 * @return the list
	 */
	List<TBussEntity> findTBussEntitiesByTenantId(Short tenantId);
	
	/**
	 * Find t buss entities by entity name.
	 *
	 * @param entityName the entity name
	 * @param tenantId the tenant id
	 * @return the list
	 */
	List<TBussEntity> findTBussEntitiesByEntityName(String entityName, Short tenantId);
	
	/**
	 * @param bussFunctionType
	 * @param tenantId
	 * @param bussFunctionType2 
	 * @return List<TBussEntity>
	 */
	
	List<TBussEntity> findTBussEntitiesByBussObjType(String bussObjectName, Short tenantId, String bussFunctionType);

     /**
	 * Gets the t buss entity by buss obj id.
	 *
	 * @param bussObjId the buss obj id
	 * @param tenantId the tenant id
	 * @return the t buss entity by buss obj id
	 */
	TBussEntity getTBussEntityByBussObjId(TBussObj tBussObj, Short tenantId);
	
	/**
	 * Gets the t buss entity name by bussFunctionType.
	 * 
	 * @param bussFunctionType the bussFunctionType
	 * @param tenantId the tenant id
	 * @return t buss entity name by bussFunctionType
	 */
	List<String> getTBussEntityNameByEntityType(String bussFunctionType, Short tenantId);
	
}
