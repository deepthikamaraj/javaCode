package com.cognizant.opserv.sp.core.dao;

import java.util.Date;
import java.util.List;

import com.cognizant.opserv.sp.core.entity.TCvgRuleOrder;
import com.cognizant.opserv.sp.core.entity.TCvgRuleSched;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TCvgRuleSched DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TCvgRuleSchedDAO {

	/**
	 * Stores a new TCvgRuleSched entity object in to the persistent store
	 * 
	 * @param tCvgRuleSched
	 *            TCvgRuleSched Entity object to be persisted
	 * @return tCvgRuleSched Persisted TCvgRuleSched object
	 */
	TCvgRuleSched createTCvgRuleSched(TCvgRuleSched tCvgRuleSched);

	/**
	 * Deletes a TCvgRuleSched entity object from the persistent store
	 * 
	 * @param tCvgRuleSched
	 *            TCvgRuleSched Entity object to be deleted
	 */
	void deleteTCvgRuleSched(Integer txnId);

	/**
	 * Updates a TCvgRuleSched entity object in to the persistent store
	 * 
	 * @param tCvgRuleSched
	 *            TCvgRuleSched Entity object to be updated
	 * @return tCvgRuleSched Persisted TCvgRuleSched object
	 */
	TCvgRuleSched updateTCvgRuleSched(TCvgRuleSched tCvgRuleSched);

	/**
	 * Retrieve an TCvgRuleSched object based on given txnId.
	 * 
	 * @param txnId
	 *            the primary key value of the TCvgRuleSched Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TCvgRuleSched findTCvgRuleSchedById(Integer txnId);

	/**
	 * Retrieve TCvgRuleSched based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCvgRuleSched> list of TCvgRuleScheds if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TCvgRuleSched> findTCvgRuleScheds(SearchFilter<TCvgRuleSched> searchFilter);

	/**
	 * Count TCvgRuleSched based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTCvgRuleScheds(SearchFilter<TCvgRuleSched> searchFilter);

	/**
	 * Retrieve TCvgRuleSched based on given search criteria using JPA named Query.
	 * The search criteria is of TCvgRuleOrder type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCvgRuleSched> list of TCvgRuleScheds if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TCvgRuleSched> getTCvgRuleSchedsByTCvgRuleOrder(SearchFilter<TCvgRuleOrder> searchFilter);

	/**
	 * Count TCvgRuleSched based on given search criteria using JPA named Query.
	 * The search criteria is of TCvgRuleOrder type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTCvgRuleSchedsByTCvgRuleOrder(SearchFilter<TCvgRuleOrder> searchFilter);
	 /**
		 * Retrieve TCvgRulesched for given criteria
		 * 
		 * 
		 * @param tenantId
		 *            -holds tenant Id
		 * @param txtId
		 *            -holds txtId
		 * @param flag
		 *            -holds flag
		 * 
		 * @return List<TCvgRuleSched> list of TCvgRule if it exists against given
		 *         criteria. 
		 */

	List<TCvgRuleSched> findTCvgRuleSchedsByTxtId(Integer txtId, short tenantId,boolean flag);
	
	/**
	 * Update t cvg sched by sales pos id.
	 *
	 * @param tenantId the tenant id
	 * @param salesPosId the sales pos id
	 * @param updatedBy the updated by
	 * @return the int
	 */
	int updateTCvgSchedBySalesPosId(Short tenantId, Long salesPosId,Integer updatedBy);

	
	/**
	 * Gets the order ids by sales pos id.
	 *
	 * @param salesPosId the sales pos id
	 * @param tenantId the tenant id
	 * @return the order ids by sales pos id
	 */
	List<Integer> getOrderIdsBySalesPosId(Long salesPosId,short tenantId);
	
	/**
	 * Find t cvg txn id count.
	 *
	 * @param tenantId the tenant id
	 * @param orderId the order id
	 * @param flag the flag
	 * @return the list
	 */
	List<Object> findTCvgTxnIdCount( short tenantId,int orderId,Character flag);

	/**
	 * Update t cvg sched by oder id.
	 *
	 * @param activeFlag the active flag
	 * @param updateDt the update dt
	 * @param updatedBy the updated by
	 * @param tenantId the tenant id
	 * @param orderId the order id
	 * @param txnIds the txn ids
	 */
	void UpdateTCvgSchedByOderId(Character activeFlag, Date updateDt,
			Integer updatedBy, Short tenantId, Integer orderId, List<Integer> txnIds);



	
}
