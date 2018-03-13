package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TChngReqTrigger;
import com.cognizant.opserv.sp.core.entity.TNestChngReq;
import com.cognizant.opserv.sp.core.entity.TNestChngReqId;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TNestChngReq DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TNestChngReqDAO {

	/**
	 * Stores a new TNestChngReq entity object in to the persistent store
	 * 
	 * @param tNestChngReq
	 *            TNestChngReq Entity object to be persisted
	 * @return tNestChngReq Persisted TNestChngReq object
	 */
	TNestChngReq createTNestChngReq(TNestChngReq tNestChngReq);

	/**
	 * Deletes a TNestChngReq entity object from the persistent store
	 * 
	 * @param tNestChngReq
	 *            TNestChngReq Entity object to be deleted
	 */
	void deleteTNestChngReq(TNestChngReqId tNestChngReqId);

	/**
	 * Updates a TNestChngReq entity object in to the persistent store
	 * 
	 * @param tNestChngReq
	 *            TNestChngReq Entity object to be updated
	 * @return tNestChngReq Persisted TNestChngReq object
	 */
	TNestChngReq updateTNestChngReq(TNestChngReq tNestChngReq);

	/**
	 * Retrieve an TNestChngReq object based on given TNestChngReqId.
	 * 
	 * @param tNestChngReqId
	 *            the primary key value of the TNestChngReq Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TNestChngReq findTNestChngReqById(TNestChngReqId tNestChngReqId);

	/**
	 * Retrieve TNestChngReq based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TNestChngReq> list of TNestChngReqs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TNestChngReq> findTNestChngReqs(SearchFilter<TNestChngReq> searchFilter);

	/**
	 * Count TNestChngReq based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTNestChngReqs(SearchFilter<TNestChngReq> searchFilter);

	/**
	 * Retrieve TNestChngReq based on given search criteria using JPA named Query.
	 * The search criteria is of TChngReqTrigger type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TNestChngReq> list of TNestChngReqs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TNestChngReq> getTNestChngReqsByTChngReqTriggerByTriggerId(SearchFilter<TChngReqTrigger> searchFilter);

	/**
	 * Count TNestChngReq based on given search criteria using JPA named Query.
	 * The search criteria is of TChngReqTrigger type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTNestChngReqsByTChngReqTriggerByTriggerId(SearchFilter<TChngReqTrigger> searchFilter);

	/**
	 * Retrieve TNestChngReq based on given search criteria using JPA named Query.
	 * The search criteria is of TChngReqTrigger type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TNestChngReq> list of TNestChngReqs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TNestChngReq> getTNestChngReqsByTChngReqTriggerByNstTriggerId(SearchFilter<TChngReqTrigger> searchFilter);

	/**
	 * Count TNestChngReq based on given search criteria using JPA named Query.
	 * The search criteria is of TChngReqTrigger type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTNestChngReqsByTChngReqTriggerByNstTriggerId(SearchFilter<TChngReqTrigger> searchFilter);

}
