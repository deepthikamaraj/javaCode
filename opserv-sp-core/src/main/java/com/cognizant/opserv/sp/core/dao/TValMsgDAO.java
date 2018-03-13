package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TAttrRule;
import com.cognizant.opserv.sp.core.entity.TValMsg;
import com.cognizant.opserv.sp.core.entity.TValMsgId;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TValMsg DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TValMsgDAO {

	/**
	 * Stores a new TValMsg entity object in to the persistent store
	 * 
	 * @param tValMsg
	 *            TValMsg Entity object to be persisted
	 * @return tValMsg Persisted TValMsg object
	 */
	TValMsg createTValMsg(TValMsg tValMsg);

	/**
	 * Deletes a TValMsg entity object from the persistent store
	 * 
	 * @param tValMsg
	 *            TValMsg Entity object to be deleted
	 */
	void deleteTValMsg(TValMsgId tValMsgId);

	/**
	 * Updates a TValMsg entity object in to the persistent store
	 * 
	 * @param tValMsg
	 *            TValMsg Entity object to be updated
	 * @return tValMsg Persisted TValMsg object
	 */
	TValMsg updateTValMsg(TValMsg tValMsg);

	/**
	 * Retrieve an TValMsg object based on given TValMsgId.
	 * 
	 * @param tValMsgId
	 *            the primary key value of the TValMsg Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TValMsg findTValMsgById(TValMsgId tValMsgId);

	/**
	 * Retrieve TValMsg based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TValMsg> list of TValMsgs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TValMsg> findTValMsgs(SearchFilter<TValMsg> searchFilter);

	/**
	 * Count TValMsg based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTValMsgs(SearchFilter<TValMsg> searchFilter);

	/**
	 * Retrieve TValMsg based on given search criteria using JPA named Query.
	 * The search criteria is of TAttrRule type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TValMsg> list of TValMsgs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TValMsg> getTValMsgsByTAttrRule(SearchFilter<TAttrRule> searchFilter);

	/**
	 * Count TValMsg based on given search criteria using JPA named Query.
	 * The search criteria is of TAttrRule type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTValMsgsByTAttrRule(SearchFilter<TAttrRule> searchFilter);

}
