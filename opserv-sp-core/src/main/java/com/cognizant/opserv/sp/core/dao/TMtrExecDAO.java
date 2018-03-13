package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TExecutionStatus;
import com.cognizant.opserv.sp.core.entity.TMtrExec;
import com.cognizant.opserv.sp.core.entity.TMtrExecConfig;
import com.cognizant.opserv.sp.core.entity.TMtrExecId;
import com.cognizant.opserv.sp.core.entity.TSalesPos;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TMtrExec DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TMtrExecDAO {

	/**
	 * Stores a new TMtrExec entity object in to the persistent store
	 * 
	 * @param tMtrExec
	 *            TMtrExec Entity object to be persisted
	 * @return tMtrExec Persisted TMtrExec object
	 */
	TMtrExec createTMtrExec(TMtrExec tMtrExec);

	/**
	 * Deletes a TMtrExec entity object from the persistent store
	 * 
	 * @param tMtrExec
	 *            TMtrExec Entity object to be deleted
	 */
	void deleteTMtrExec(TMtrExecId tMtrExecId);

	/**
	 * Updates a TMtrExec entity object in to the persistent store
	 * 
	 * @param tMtrExec
	 *            TMtrExec Entity object to be updated
	 * @return tMtrExec Persisted TMtrExec object
	 */
	TMtrExec updateTMtrExec(TMtrExec tMtrExec);

	/**
	 * Retrieve an TMtrExec object based on given TMtrExecId.
	 * 
	 * @param tMtrExecId
	 *            the primary key value of the TMtrExec Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TMtrExec findTMtrExecById(TMtrExecId tMtrExecId);

	/**
	 * Retrieve TMtrExec based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TMtrExec> list of TMtrExecs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TMtrExec> findTMtrExecs(SearchFilter<TMtrExec> searchFilter);

	/**
	 * Count TMtrExec based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTMtrExecs(SearchFilter<TMtrExec> searchFilter);

	/**
	 * Retrieve TMtrExec based on given search criteria using JPA named Query.
	 * The search criteria is of TExecutionStatus type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TMtrExec> list of TMtrExecs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TMtrExec> getTMtrExecsByTExecutionStatus(SearchFilter<TExecutionStatus> searchFilter);

	/**
	 * Count TMtrExec based on given search criteria using JPA named Query.
	 * The search criteria is of TExecutionStatus type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTMtrExecsByTExecutionStatus(SearchFilter<TExecutionStatus> searchFilter);

	/**
	 * Retrieve TMtrExec based on given search criteria using JPA named Query.
	 * The search criteria is of TMtrExecConfig type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TMtrExec> list of TMtrExecs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TMtrExec> getTMtrExecsByTMtrExecConfig(SearchFilter<TMtrExecConfig> searchFilter);

	/**
	 * Count TMtrExec based on given search criteria using JPA named Query.
	 * The search criteria is of TMtrExecConfig type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTMtrExecsByTMtrExecConfig(SearchFilter<TMtrExecConfig> searchFilter);

	/**
	 * Retrieve TMtrExec based on given search criteria using JPA named Query.
	 * The search criteria is of TSalesPos type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TMtrExec> list of TMtrExecs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TMtrExec> getTMtrExecsByTSalesPos(SearchFilter<TSalesPos> searchFilter);

	/**
	 * Count TMtrExec based on given search criteria using JPA named Query.
	 * The search criteria is of TSalesPos type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTMtrExecsByTSalesPos(SearchFilter<TSalesPos> searchFilter);
	
	
	List<Object> getMaxExecutionId(Short tenantId);
}
