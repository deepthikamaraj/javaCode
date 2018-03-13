package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TCvgRuleExec;
import com.cognizant.opserv.sp.core.entity.TCvgRuleSched;
import com.cognizant.opserv.sp.core.entity.TExecutionStatus;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TCvgRuleExec DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TCvgRuleExecDAO {

	/**
	 * Stores a new TCvgRuleExec entity object in to the persistent store
	 * 
	 * @param tCvgRuleExec
	 *            TCvgRuleExec Entity object to be persisted
	 * @return tCvgRuleExec Persisted TCvgRuleExec object
	 */
	TCvgRuleExec createTCvgRuleExec(TCvgRuleExec tCvgRuleExec);

	/**
	 * Deletes a TCvgRuleExec entity object from the persistent store
	 * 
	 * @param tCvgRuleExec
	 *            TCvgRuleExec Entity object to be deleted
	 */
	void deleteTCvgRuleExec(Integer cvgRuleExecId);

	/**
	 * Updates a TCvgRuleExec entity object in to the persistent store
	 * 
	 * @param tCvgRuleExec
	 *            TCvgRuleExec Entity object to be updated
	 * @return tCvgRuleExec Persisted TCvgRuleExec object
	 */
	TCvgRuleExec updateTCvgRuleExec(TCvgRuleExec tCvgRuleExec);

	/**
	 * Retrieve an TCvgRuleExec object based on given cvgRuleExecId.
	 * 
	 * @param cvgRuleExecId
	 *            the primary key value of the TCvgRuleExec Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TCvgRuleExec findTCvgRuleExecById(Integer cvgRuleExecId);

	/**
	 * Retrieve TCvgRuleExec based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCvgRuleExec> list of TCvgRuleExecs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TCvgRuleExec> findTCvgRuleExecs(SearchFilter<TCvgRuleExec> searchFilter);

	/**
	 * Count TCvgRuleExec based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTCvgRuleExecs(SearchFilter<TCvgRuleExec> searchFilter);

	/**
	 * Retrieve TCvgRuleExec based on given search criteria using JPA named Query.
	 * The search criteria is of TExecutionStatus type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCvgRuleExec> list of TCvgRuleExecs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TCvgRuleExec> getTCvgRuleExecsByTExecutionStatus(SearchFilter<TExecutionStatus> searchFilter);

	/**
	 * Count TCvgRuleExec based on given search criteria using JPA named Query.
	 * The search criteria is of TExecutionStatus type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTCvgRuleExecsByTExecutionStatus(SearchFilter<TExecutionStatus> searchFilter);

	/**
	 * Retrieve TCvgRuleExec based on given search criteria using JPA named Query.
	 * The search criteria is of TCvgRuleSched type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCvgRuleExec> list of TCvgRuleExecs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TCvgRuleExec> getTCvgRuleExecsByTCvgRuleSched(SearchFilter<TCvgRuleSched> searchFilter);

	/**
	 * Count TCvgRuleExec based on given search criteria using JPA named Query.
	 * The search criteria is of TCvgRuleSched type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTCvgRuleExecsByTCvgRuleSched(SearchFilter<TCvgRuleSched> searchFilter);
	

	/**
	 * Retrieve list of arrayobject based on given search criteria
	 * 
	 * @param ruleId
	 *            -holds ruleId
	 * @param tenantId
	 *            -holds tenant Id
	
	 * 
	 * @return list of object of tCvgRuleExec
	 */
	 List<Object[]> getTCvgRuleExecsByOrdername(
			Short tenantId,Integer ruleId);
	 /**
		 * Retrieve list of TCvgRuleExec based on given search criteria
		 * 
		 * @param searchFilter
		 * 
		 *            The query criteria and search filter conditions are set
		 * 
		 * 
		 * @return List<TCvgRuleExec> list of TCvgRuleExecs if it exists against
		 *         given criteria. Returns null if not found
		 * */
	List<TCvgRuleExec> getTCvgRuleExecsByRuleAndStatus(
			SearchFilter<TCvgRuleExec> searchFilter);
	/**
	 * Retrieve list of arrayobject based on given search criteria
	 * 
	 * @param ruleName
	 *            -holds ruleName
	 * @param tenantId
	 *            -holds tenant Id
	
	 * 
	 * @return list of object of tCvgRule
	 */
	List<Object[]> FindTCvgRuleExecByRuleName(Short tenantId, String ruleName);
	
	/**
	 * Find t cvg rule exec dts.
	 *
	 * @param tenantId the tenant id
	 * @param txnId the txn id
	 * @param ruleId the rule id
	 * @return the list
	 */
	List<TCvgRuleExec> findTCvgRuleExecDts(Short tenantId,Integer txnId,Integer ruleId);

	/**
	 * Find t cvg rules by execution id.
	 *
	 * @param tenantId the tenant id
	 * @param orderId the order id
	 * @return the list
	 */
	List<Object[]> FindTCvgRulesByExecutionId(Short tenantId, int orderId);

	/**
	 * Find t cvg rule by exec status id based on order id.
	 *
	 * @param tenantId the tenant id
	 * @param orderId the order id
	 * @param flag the flag
	 * @return the list
	 */
	List<Integer[]> findTCvgRuleByExecStatusIdBasedOnOrderId(Short tenantId, int orderId,Character flag);
	
			
			


}
