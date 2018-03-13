package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TMtr;
import com.cognizant.opserv.sp.core.entity.TMtrValMsg;
import com.cognizant.opserv.sp.core.entity.TMtrValMsgId;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TMtrValMsg DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TMtrValMsgDAO {

	/**
	 * Stores a new TMtrValMsg entity object in to the persistent store
	 * 
	 * @param tMtrValMsg
	 *            TMtrValMsg Entity object to be persisted
	 * @return tMtrValMsg Persisted TMtrValMsg object
	 */
	TMtrValMsg createTMtrValMsg(TMtrValMsg tMtrValMsg);

	/**
	 * Deletes a TMtrValMsg entity object from the persistent store
	 * 
	 * @param tMtrValMsg
	 *            TMtrValMsg Entity object to be deleted
	 */
	void deleteTMtrValMsg(TMtrValMsgId tMtrValMsgId);

	/**
	 * Updates a TMtrValMsg entity object in to the persistent store
	 * 
	 * @param tMtrValMsg
	 *            TMtrValMsg Entity object to be updated
	 * @return tMtrValMsg Persisted TMtrValMsg object
	 */
	TMtrValMsg updateTMtrValMsg(TMtrValMsg tMtrValMsg);

	/**
	 * Retrieve an TMtrValMsg object based on given TMtrValMsgId.
	 * 
	 * @param tMtrValMsgId
	 *            the primary key value of the TMtrValMsg Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TMtrValMsg findTMtrValMsgById(TMtrValMsgId tMtrValMsgId);

	/**
	 * Retrieve TMtrValMsg based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TMtrValMsg> list of TMtrValMsgs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TMtrValMsg> findTMtrValMsgs(SearchFilter<TMtrValMsg> searchFilter);

	/**
	 * Count TMtrValMsg based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTMtrValMsgs(SearchFilter<TMtrValMsg> searchFilter);

	/**
	 * Retrieve TMtrValMsg based on given search criteria using JPA named Query.
	 * The search criteria is of TMtr type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TMtrValMsg> list of TMtrValMsgs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TMtrValMsg> getTMtrValMsgsByTMtr(SearchFilter<TMtr> searchFilter);

	/**
	 * Count TMtrValMsg based on given search criteria using JPA named Query.
	 * The search criteria is of TMtr type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTMtrValMsgsByTMtr(SearchFilter<TMtr> searchFilter);
	
	
	List<Object> findTMtrValMsgsByMtrId(Integer mtrId,short tenantId);

}
