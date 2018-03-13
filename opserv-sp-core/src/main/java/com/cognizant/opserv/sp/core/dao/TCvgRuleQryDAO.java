package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TCvgRuleQry;
import com.cognizant.opserv.sp.core.entity.TCvgRuleQryId;
import com.cognizant.opserv.sp.core.entity.TCvgRuleSched;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TCvgRuleQry DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TCvgRuleQryDAO {

	/**
	 * Stores a new TCvgRuleQry entity object in to the persistent store
	 * 
	 * @param tCvgRuleQry
	 *            TCvgRuleQry Entity object to be persisted
	 * @return tCvgRuleQry Persisted TCvgRuleQry object
	 */
	TCvgRuleQry createTCvgRuleQry(TCvgRuleQry tCvgRuleQry);

	/**
	 * Deletes a TCvgRuleQry entity object from the persistent store
	 * 
	 * @param tCvgRuleQry
	 *            TCvgRuleQry Entity object to be deleted
	 */
	void deleteTCvgRuleQry(TCvgRuleQryId tCvgRuleQryId);

	/**
	 * Updates a TCvgRuleQry entity object in to the persistent store
	 * 
	 * @param tCvgRuleQry
	 *            TCvgRuleQry Entity object to be updated
	 * @return tCvgRuleQry Persisted TCvgRuleQry object
	 */
	TCvgRuleQry updateTCvgRuleQry(TCvgRuleQry tCvgRuleQry);

	/**
	 * Retrieve an TCvgRuleQry object based on given TCvgRuleQryId.
	 * 
	 * @param tCvgRuleQryId
	 *            the primary key value of the TCvgRuleQry Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TCvgRuleQry findTCvgRuleQryById(TCvgRuleQryId tCvgRuleQryId);

	/**
	 * Retrieve TCvgRuleQry based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCvgRuleQry> list of TCvgRuleQrys if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TCvgRuleQry> findTCvgRuleQrys(SearchFilter<TCvgRuleQry> searchFilter);

	/**
	 * Count TCvgRuleQry based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTCvgRuleQrys(SearchFilter<TCvgRuleQry> searchFilter);

	/**
	 * Retrieve TCvgRuleQry based on given search criteria using JPA named Query.
	 * The search criteria is of TCvgRuleSched type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCvgRuleQry> list of TCvgRuleQrys if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TCvgRuleQry> getTCvgRuleQrysByTCvgRuleSched(SearchFilter<TCvgRuleSched> searchFilter);

	/**
	 * Count TCvgRuleQry based on given search criteria using JPA named Query.
	 * The search criteria is of TCvgRuleSched type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTCvgRuleQrysByTCvgRuleSched(SearchFilter<TCvgRuleSched> searchFilter);
	
	/**
	 * Retrieve TCvgRuleQry for given criteria
	 * 
	 * @param txtId
	 *            -holds txtId
	 *@param ruleId
	 *            -holds txtId
	 * @param tenantId
	 *            -holds tenant Id
	 * 
	 * @return List<TCvgRuleQry> list of TCvgRuleQry if it exists against given
	 *         criteria. 
	 */

	List<TCvgRuleQry> findTCvgRuleQryByIds(Integer txtId,Integer ruleId,short tenantId);

}
