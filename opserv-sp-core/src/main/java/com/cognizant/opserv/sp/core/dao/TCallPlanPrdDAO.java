package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TCallPlanPrd;
import com.cognizant.opserv.sp.core.entity.TCustCallPlan;
import com.cognizant.opserv.sp.core.entity.TCustPrdAlgmnt;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TCallPlanPrd DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TCallPlanPrdDAO {

	/**
	 * Stores a new TCallPlanPrd entity object in to the persistent store
	 * 
	 * @param tCallPlanPrd
	 *            TCallPlanPrd Entity object to be persisted
	 * @return tCallPlanPrd Persisted TCallPlanPrd object
	 */
	TCallPlanPrd createTCallPlanPrd(TCallPlanPrd tCallPlanPrd);

	/**
	 * Deletes a TCallPlanPrd entity object from the persistent store
	 * 
	 * @param tCallPlanPrd
	 *            TCallPlanPrd Entity object to be deleted
	 */
	void deleteTCallPlanPrd(Integer callPlanPrdId);

	/**
	 * Updates a TCallPlanPrd entity object in to the persistent store
	 * 
	 * @param tCallPlanPrd
	 *            TCallPlanPrd Entity object to be updated
	 * @return tCallPlanPrd Persisted TCallPlanPrd object
	 */
	TCallPlanPrd updateTCallPlanPrd(TCallPlanPrd tCallPlanPrd);

	/**
	 * Retrieve an TCallPlanPrd object based on given callPlanPrdId.
	 * 
	 * @param callPlanPrdId
	 *            the primary key value of the TCallPlanPrd Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TCallPlanPrd findTCallPlanPrdById(Integer callPlanPrdId);

	/**
	 * Retrieve TCallPlanPrd based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCallPlanPrd> list of TCallPlanPrds if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TCallPlanPrd> findTCallPlanPrds(SearchFilter<TCallPlanPrd> searchFilter);

	/**
	 * Count TCallPlanPrd based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTCallPlanPrds(SearchFilter<TCallPlanPrd> searchFilter);

	/**
	 * Retrieve TCallPlanPrd based on given search criteria using JPA named Query.
	 * The search criteria is of TCustPrdAlgmnt type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCallPlanPrd> list of TCallPlanPrds if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TCallPlanPrd> getTCallPlanPrdsByTCustPrdAlgmnt(SearchFilter<TCustPrdAlgmnt> searchFilter);

	/**
	 * Count TCallPlanPrd based on given search criteria using JPA named Query.
	 * The search criteria is of TCustPrdAlgmnt type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTCallPlanPrdsByTCustPrdAlgmnt(SearchFilter<TCustPrdAlgmnt> searchFilter);

	/**
	 * Retrieve TCallPlanPrd based on given search criteria using JPA named Query.
	 * The search criteria is of TCustCallPlan type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCallPlanPrd> list of TCallPlanPrds if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TCallPlanPrd> getTCallPlanPrdsByTCustCallPlan(SearchFilter<TCustCallPlan> searchFilter);

	/**
	 * Count TCallPlanPrd based on given search criteria using JPA named Query.
	 * The search criteria is of TCustCallPlan type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTCallPlanPrdsByTCustCallPlan(SearchFilter<TCustCallPlan> searchFilter);
	/**
	 * Retrieve TCallPlanPrd based on given search criteria using JPA named Query.
	 * The search criteria is of TCallPlanPrd type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCallPlanPrd> list of TCallPlanPrdByJointStmt if it exists against given
	 *         criteria.
	 */
	List<TCallPlanPrd> findTCallPlanPrdByJointStmt(Long salesPosId, Short tenantId);
	/**
	 * Retrieve object based on given search criteria using JPA named Query.
	 * The search criteria is of CustomerCallPlanProducts type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return object of TCallPlanPrdByTCustCallPlanFromFetch if it exists against given
	 *         criteria.
	 */
	List<Object[]> findCustomerCallPlanProducts(Long custCallPlanId,Short tenantId);
	/**
	 * Retrieve object based on given search criteria using JPA named Query.
	 * The search criteria is of CallPlanProduct type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 *  @return List<TCallPlanPrd> list of CallPlanProdFromCustAlignment if it exists against given
	 *         criteria.
	 */
	List<TCallPlanPrd> findCallPlanProduct(Long alignmentId,String description,Long prdId,Short tenantId);
	/**
	 * Retrieve object based on given search criteria using JPA named Query.
	 * The search criteria is of CallPlanPrdsByPrdId type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 *  @return List<TCallPlanPrd> list of CallPlanPrdsByPrdId if it exists against given
	 *         criteria.
	 */
	List<TCallPlanPrd> findTCallPlanPrdsByPrdId(Long prdId);
	
}
