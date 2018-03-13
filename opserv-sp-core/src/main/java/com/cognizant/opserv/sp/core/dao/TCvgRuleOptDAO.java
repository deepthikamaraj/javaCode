package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TCvgRuleOpt;
import com.cognizant.opserv.sp.core.entity.TCvgRuleOptId;
import com.cognizant.opserv.sp.core.entity.TCvgRuleOrder;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TCvgRuleOpt DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TCvgRuleOptDAO {

	/**
	 * Stores a new TCvgRuleOpt entity object in to the persistent store
	 * 
	 * @param tCvgRuleOpt
	 *            TCvgRuleOpt Entity object to be persisted
	 * @return tCvgRuleOpt Persisted TCvgRuleOpt object
	 */
	TCvgRuleOpt createTCvgRuleOpt(TCvgRuleOpt tCvgRuleOpt);

	/**
	 * Deletes a TCvgRuleOpt entity object from the persistent store
	 * 
	 * @param tCvgRuleOpt
	 *            TCvgRuleOpt Entity object to be deleted
	 */
	void deleteTCvgRuleOpt(TCvgRuleOptId tCvgRuleOptId);

	/**
	 * Updates a TCvgRuleOpt entity object in to the persistent store
	 * 
	 * @param tCvgRuleOpt
	 *            TCvgRuleOpt Entity object to be updated
	 * @return tCvgRuleOpt Persisted TCvgRuleOpt object
	 */
	TCvgRuleOpt updateTCvgRuleOpt(TCvgRuleOpt tCvgRuleOpt);

	/**
	 * Retrieve an TCvgRuleOpt object based on given TCvgRuleOptId.
	 * 
	 * @param tCvgRuleOptId
	 *            the primary key value of the TCvgRuleOpt Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TCvgRuleOpt findTCvgRuleOptById(TCvgRuleOptId tCvgRuleOptId);

	/**
	 * Retrieve TCvgRuleOpt based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCvgRuleOpt> list of TCvgRuleOpts if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TCvgRuleOpt> findTCvgRuleOpts(SearchFilter<TCvgRuleOpt> searchFilter);

	/**
	 * Count TCvgRuleOpt based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTCvgRuleOpts(SearchFilter<TCvgRuleOpt> searchFilter);

	/**
	 * Retrieve TCvgRuleOpt based on given search criteria using JPA named Query.
	 * The search criteria is of TCvgRuleOrder type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCvgRuleOpt> list of TCvgRuleOpts if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TCvgRuleOpt> getTCvgRuleOptsByTCvgRuleOrder(SearchFilter<TCvgRuleOrder> searchFilter);

	/**
	 * Count TCvgRuleOpt based on given search criteria using JPA named Query.
	 * The search criteria is of TCvgRuleOrder type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTCvgRuleOptsByTCvgRuleOrder(SearchFilter<TCvgRuleOrder> searchFilter);

	/**
	 * Gets the t cvg rule opts by order id.
	 *
	 * @param searchFilter the search filter
	 * @return the t cvg rule opts by order id
	 */
	List<TCvgRuleOpt> getTCvgRuleOptsByOrderId(SearchFilter<TCvgRuleOpt> searchFilter);

	/**
	 * Find t cvg rule opt by id.
	 *
	 * @param tCvgRuleOptId the t cvg rule opt id
	 * @param tenantId the tenant id
	 * @return the t cvg rule opt
	 */
	TCvgRuleOpt findTCvgRuleOptById(TCvgRuleOptId tCvgRuleOptId, Short tenantId);

	/**
	 * Delete t cvg rule opt.
	 *
	 * @param tCvgRuleOptId the t cvg rule opt id
	 * @param tenantId the tenant id
	 */
	void deleteTCvgRuleOpt(TCvgRuleOptId tCvgRuleOptId, Short tenantId);
	
	
	/**
	 * Gets the list of rule ids by order id.
	 *
	 * @param orderId the order id
	 * @param tenantId the tenant id
	 * @return the list of rule ids by order id
	 */
	List<Object[]> getListOfRuleIdsByOrderId(Integer orderId , short tenantId);

	/**
	 * Count of rule idb based on order id.
	 *
	 * @param orderId the order id
	 * @param tenantId the tenant id
	 * @return the list
	 */
	List<Object> countOfRuleIdbBasedOnOrderId( int orderId,Short tenantId);
	
}
