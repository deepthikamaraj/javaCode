package com.cognizant.opserv.sp.core.dao;

import java.util.List;
import java.util.Map;

import com.cognizant.opserv.sp.core.entity.TCvgRule;
import com.cognizant.opserv.sp.core.entity.TCvgRuleOrder;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TCvgRuleOrder DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TCvgRuleOrderDAO {

	/**
	 * Stores a new TCvgRuleOrder entity object in to the persistent store
	 * 
	 * @param tCvgRuleOrder
	 *            TCvgRuleOrder Entity object to be persisted
	 * @return tCvgRuleOrder Persisted TCvgRuleOrder object
	 */
	TCvgRuleOrder createTCvgRuleOrder(TCvgRuleOrder tCvgRuleOrder);

	/**
	 * Deletes a TCvgRuleOrder entity object from the persistent store
	 * 
	 * @param tCvgRuleOrder
	 *            TCvgRuleOrder Entity object to be deleted
	 */
	void deleteTCvgRuleOrder(Integer orderId);

	/**
	 * Updates a TCvgRuleOrder entity object in to the persistent store
	 * 
	 * @param tCvgRuleOrder
	 *            TCvgRuleOrder Entity object to be updated
	 * @return tCvgRuleOrder Persisted TCvgRuleOrder object
	 */
	TCvgRuleOrder updateTCvgRuleOrder(TCvgRuleOrder tCvgRuleOrder);

	/**
	 * Retrieve an TCvgRuleOrder object based on given orderId.
	 * 
	 * @param orderId
	 *            the primary key value of the TCvgRuleOrder Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TCvgRuleOrder findTCvgRuleOrderById(Integer orderId);

	/**
	 * Retrieve TCvgRuleOrder based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCvgRuleOrder> list of TCvgRuleOrders if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TCvgRuleOrder> findTCvgRuleOrders(SearchFilter<TCvgRuleOrder> searchFilter);

	/**
	 * Count TCvgRuleOrder based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTCvgRuleOrders(SearchFilter<TCvgRuleOrder> searchFilter);
	

	 /**
 	 * Find allby join query.
 	 *
 	 * @param orderId the order id
 	 * @param tenantId the tenant id
 	 * @return the list
 	 */
 	List<TCvgRule> findAllbyJoinQuery(Integer orderId,short tenantId);
	 
	 /**
 	 * Find order by name.
 	 *
 	 * @param searchFilter the search filter
 	 * @return the list
 	 */
 	List<TCvgRuleOrder> findOrderByName(SearchFilter<TCvgRuleOrder> searchFilter);
	 
	 /**
 	 * Find status.
 	 *
 	 * @param orderId the order id
 	 * @param tenantId the tenant id
 	 * @return the map
 	 */
 	Map<Integer,String> findStatus(Integer orderId, short tenantId);

	/**
	 * Find t cvg rule order by id.
	 *
	 * @param cvgRuleOrderID the cvg rule order id
	 * @param tenantId the tenant id
	 * @return the t cvg rule order
	 */
	TCvgRuleOrder findTCvgRuleOrderById(Integer cvgRuleOrderID, Short tenantId);
}
