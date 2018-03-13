package com.cognizant.opserv.sp.core.dao;


import java.util.List;

import com.cognizant.opserv.sp.core.entity.TBussEntity;
import com.cognizant.opserv.sp.core.entity.TRefBussEntity;
import com.cognizant.opserv.sp.core.entity.TRefBussEntityId;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TRefBussEntity DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TRefBussEntityDAO {

	/**
	 * Stores a new TRefBussEntity entity object in to the persistent store
	 * 
	 * @param tRefBussEntity
	 *            TRefBussEntity Entity object to be persisted
	 * @return tRefBussEntity Persisted TRefBussEntity object
	 */
	TRefBussEntity createTRefBussEntity(TRefBussEntity tRefBussEntity);

	/**
	 * Deletes a TRefBussEntity entity object from the persistent store
	 * 
	 * @param tRefBussEntity
	 *            TRefBussEntity Entity object to be deleted
	 */
	void deleteTRefBussEntity(TRefBussEntityId tRefBussEntityId);

	/**
	 * Updates a TRefBussEntity entity object in to the persistent store
	 * 
	 * @param tRefBussEntity
	 *            TRefBussEntity Entity object to be updated
	 * @return tRefBussEntity Persisted TRefBussEntity object
	 */
	TRefBussEntity updateTRefBussEntity(TRefBussEntity tRefBussEntity);

	/**
	 * Retrieve an TRefBussEntity object based on given TRefBussEntityId.
	 * 
	 * @param tRefBussEntityId
	 *            the primary key value of the TRefBussEntity Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TRefBussEntity findTRefBussEntityById(TRefBussEntityId tRefBussEntityId);

	/**
	 * Retrieve TRefBussEntity based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRefBussEntity> list of TRefBussEntitys if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TRefBussEntity> findTRefBussEntitys(SearchFilter<TRefBussEntity> searchFilter);

	/**
	 * Count TRefBussEntity based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTRefBussEntitys(SearchFilter<TRefBussEntity> searchFilter);

	/**
	 * Retrieve TRefBussEntity based on given search criteria using JPA named Query.
	 * The search criteria is of TBussEntity type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRefBussEntity> list of TRefBussEntitys if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TRefBussEntity> getTRefBussEntitysByTBussEntityByEntityId(SearchFilter<TBussEntity> searchFilter);

	/**
	 * Count TRefBussEntity based on given search criteria using JPA named Query.
	 * The search criteria is of TBussEntity type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTRefBussEntitysByTBussEntityByEntityId(SearchFilter<TBussEntity> searchFilter);

	/**
	 * Retrieve TRefBussEntity based on given search criteria using JPA named Query.
	 * The search criteria is of TBussEntity type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRefBussEntity> list of TRefBussEntitys if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TRefBussEntity> getTRefBussEntitysByTBussEntityByPrnEntityId(SearchFilter<TBussEntity> searchFilter);

	/**
	 * Count TRefBussEntity based on given search criteria using JPA named Query.
	 * The search criteria is of TBussEntity type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTRefBussEntitysByTBussEntityByPrnEntityId(SearchFilter<TBussEntity> searchFilter);
	
	List<Integer> getTRefBussEntitysByEntityId(Integer entityId,Short tenantId);

}
