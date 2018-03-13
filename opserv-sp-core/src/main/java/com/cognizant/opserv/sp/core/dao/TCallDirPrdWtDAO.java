package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TCallDirConfig;
import com.cognizant.opserv.sp.core.entity.TCallDirPrdWt;
import com.cognizant.opserv.sp.core.entity.TCallDirPrdWtId;
import com.cognizant.opserv.sp.core.entity.TCustCallPlan;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TCallDirPrdWt DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TCallDirPrdWtDAO {

	/**
	 * Stores a new TCallDirPrdWt entity object in to the persistent store
	 * 
	 * @param tCallDirPrdWt
	 *            TCallDirPrdWt Entity object to be persisted
	 * @return tCallDirPrdWt Persisted TCallDirPrdWt object
	 */
	TCallDirPrdWt createTCallDirPrdWt(TCallDirPrdWt tCallDirPrdWt);

	/**
	 * Deletes a TCallDirPrdWt entity object from the persistent store
	 * 
	 * @param tCallDirPrdWt
	 *            TCallDirPrdWt Entity object to be deleted
	 */
	void deleteTCallDirPrdWt(TCallDirPrdWtId tCallDirPrdWtId);

	/**
	 * Updates a TCallDirPrdWt entity object in to the persistent store
	 * 
	 * @param tCallDirPrdWt
	 *            TCallDirPrdWt Entity object to be updated
	 * @return tCallDirPrdWt Persisted TCallDirPrdWt object
	 */
	TCallDirPrdWt updateTCallDirPrdWt(TCallDirPrdWt tCallDirPrdWt);

	/**
	 * Retrieve an TCallDirPrdWt object based on given TCallDirPrdWtId.
	 * 
	 * @param tCallDirPrdWtId
	 *            the primary key value of the TCallDirPrdWt Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TCallDirPrdWt findTCallDirPrdWtById(TCallDirPrdWtId tCallDirPrdWtId);

	/**
	 * Retrieve TCallDirPrdWt based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCallDirPrdWt> list of TCallDirPrdWts if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TCallDirPrdWt> findTCallDirPrdWts(SearchFilter<TCallDirPrdWt> searchFilter);

	/**
	 * Count TCallDirPrdWt based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTCallDirPrdWts(SearchFilter<TCallDirPrdWt> searchFilter);

	/**
	 * Retrieve TCallDirPrdWt based on given search criteria using JPA named Query.
	 * The search criteria is of TCallDirConfig type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCallDirPrdWt> list of TCallDirPrdWts if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TCallDirPrdWt> getTCallDirPrdWtsByTCallDirConfig(SearchFilter<TCallDirConfig> searchFilter);

	/**
	 * Count TCallDirPrdWt based on given search criteria using JPA named Query.
	 * The search criteria is of TCallDirConfig type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTCallDirPrdWtsByTCallDirConfig(SearchFilter<TCallDirConfig> searchFilter);

	/**
	 * Retrieve TCallDirPrdWt based on given search criteria using JPA named Query.
	 * The search criteria is of TPrdPrtType type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCallDirPrdWt> list of TCallDirPrdWts if it exists against given
	 *         criteria. Returns null if not found
	 */
/*	List<TCallDirPrdWt> getTCallDirPrdWtsByTPrdPrtType(SearchFilter<TPrdPrtType> searchFilter);*/

	/**
	 * Count TCallDirPrdWt based on given search criteria using JPA named Query.
	 * The search criteria is of TPrdPrtType type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
/*	Object countTCallDirPrdWtsByTPrdPrtType(SearchFilter<TPrdPrtType> searchFilter);*/
	
	/**
	 * 
	 * @param custCallPlanId
	 * 
	 * @return an Object of WeightageMatrix type
	 * 
	 */
	
	List<Object> getWeightMatrix(TCustCallPlan tCustCallPlan);
	/**
	 * Retrieve WeightMatrix based on given search criteria using JPA named Query.
	 * The search criteria is of WeightMatrix
	 * 
	 * @param TCustCallPlan
	 *           - TCustCallPlan
	 * @return List<object> list of WeightMatrix if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<Object> getWeightMatrixByCallDirection(TCustCallPlan tCustCallPlan);
	
	/**
	 * Retrieve ProductSplitStatus based on given search criteria using JPA named Query.
	 * The search criteria is of ProductSplitStatus
	 * 
	 * @param TCustCallPlan
	 *           - TCustCallPlan
	 * @Param tenantId
	 *           -tenantId          
	 * @return List<character> list of ProductSplitStatus if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<Character> getProductSplitStatus(Integer custCallPlanId,Short tenantId);

}
