package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TChngReq;
import com.cognizant.opserv.sp.core.entity.TChngReqNotify;
import com.cognizant.opserv.sp.core.entity.TChngReqNotifyId;
import com.cognizant.peg.core.common.SearchFilter;

// TODO: Auto-generated Javadoc
/**
 * Interface represents API's of TChngReqNotify DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TChngReqNotifyDAO {

	/**
	 * Stores a new TChngReqNotify entity object in to the persistent store.
	 *
	 * @param tChngReqNotify TChngReqNotify Entity object to be persisted
	 * @return tChngReqNotify Persisted TChngReqNotify object
	 */
	TChngReqNotify createTChngReqNotify(TChngReqNotify tChngReqNotify);

	/**
	 * Deletes a TChngReqNotify entity object from the persistent store.
	 *
	 * @param tChngReqNotifyId the t chng req notify id
	 */
	void deleteTChngReqNotify(TChngReqNotifyId tChngReqNotifyId);

	/**
	 * Updates a TChngReqNotify entity object in to the persistent store.
	 *
	 * @param tChngReqNotify TChngReqNotify Entity object to be updated
	 * @return tChngReqNotify Persisted TChngReqNotify object
	 */
	TChngReqNotify updateTChngReqNotify(TChngReqNotify tChngReqNotify);

	/**
	 * Retrieve an TChngReqNotify object based on given TChngReqNotifyId.
	 * 
	 * @param tChngReqNotifyId
	 *            the primary key value of the TChngReqNotify Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TChngReqNotify findTChngReqNotifyById(TChngReqNotifyId tChngReqNotifyId);

	/**
	 * Retrieve TChngReqNotify based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TChngReqNotify> list of TChngReqNotifys if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TChngReqNotify> findTChngReqNotifys(SearchFilter<TChngReqNotify> searchFilter);

	/**
	 * Count TChngReqNotify based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTChngReqNotifys(SearchFilter<TChngReqNotify> searchFilter);

	/**
	 * Retrieve TChngReqNotify based on given search criteria using JPA named Query.
	 * The search criteria is of TChngReq type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TChngReqNotify> list of TChngReqNotifys if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TChngReqNotify> getTChngReqNotifysByTChngReq(SearchFilter<TChngReq> searchFilter);

	/**
	 * Count TChngReqNotify based on given search criteria using JPA named Query.
	 * The search criteria is of TChngReq type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTChngReqNotifysByTChngReq(SearchFilter<TChngReq> searchFilter);
	
	/*Added By 407986*/
	/**
	 * Find all sales positions by cr id.
	 *
	 * @param CRId the cR id
	 * @param tenantId the tenant id
	 * @return the list
	 */
	List<Object> findAllSalesPositionsByCRId(Long CRId , Short tenantId) ;
	
	
	
	
	

}
