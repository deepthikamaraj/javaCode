package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TMirSalesPos;
import com.cognizant.opserv.sp.core.entity.TSalesPos;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TMirSalesPos DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TMirSalesPosDAO {

	/**
	 * Stores a new TMirSalesPos entity object in to the persistent store
	 * 
	 * @param tMirSalesPos
	 *            TMirSalesPos Entity object to be persisted
	 * @return tMirSalesPos Persisted TMirSalesPos object
	 */
	TMirSalesPos createTMirSalesPos(TMirSalesPos tMirSalesPos);

	/**
	 * Deletes a TMirSalesPos entity object from the persistent store
	 * 
	 * @param tMirSalesPos
	 *            TMirSalesPos Entity object to be deleted
	 */
	void deleteTMirSalesPos(Long salesPosMirId);

	/**
	 * Updates a TMirSalesPos entity object in to the persistent store
	 * 
	 * @param tMirSalesPos
	 *            TMirSalesPos Entity object to be updated
	 * @return tMirSalesPos Persisted TMirSalesPos object
	 */
	TMirSalesPos updateTMirSalesPos(TMirSalesPos tMirSalesPos);

	/**
	 * Retrieve an TMirSalesPos object based on given salesPosMirId.
	 * 
	 * @param salesPosMirId
	 *            the primary key value of the TMirSalesPos Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TMirSalesPos findTMirSalesPosById(Long salesPosMirId);

	/**
	 * Retrieve TMirSalesPos based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TMirSalesPos> list of TMirSalesPoss if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TMirSalesPos> findTMirSalesPoss(SearchFilter<TMirSalesPos> searchFilter);

	/**
	 * Count TMirSalesPos based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTMirSalesPoss(SearchFilter<TMirSalesPos> searchFilter);

	/**
	 * Retrieve TMirSalesPos based on given search criteria using JPA named Query.
	 * The search criteria is of TSalesPos type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TMirSalesPos> list of TMirSalesPoss if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TMirSalesPos> getTMirSalesPossByTSalesPos(SearchFilter<TSalesPos> searchFilter);

	/**
	 * Count TMirSalesPos based on given search criteria using JPA named Query.
	 * The search criteria is of TSalesPos type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTMirSalesPossByTSalesPos(SearchFilter<TSalesPos> searchFilter);

	/**
	 * Retrieve TMirSalesPos based on given search criteria using JPA named Query.
	 * The search criteria is of TSalesPos type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TMirSalesPos> list of TMirSalesPoss if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TMirSalesPos> getTMirSalesPossByTSalesPosByTMirSalesPosIbfk2(SearchFilter<TSalesPos> searchFilter);

	/**
	 * Count TMirSalesPos based on given search criteria using JPA named Query.
	 * The search criteria is of TSalesPos type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTMirSalesPossByTSalesPosByTMirSalesPosIbfk2(SearchFilter<TSalesPos> searchFilter);

	
	/**
	 * Gets the primary sales poss by t sales pos.
	 *
	 * @param searchFilter the search filter
	 * @return the primary sales poss by t sales pos
	 */
	TMirSalesPos getPrimarySalesPossByTSalesPos(final SearchFilter<TSalesPos> searchFilter);

	/**
	 * Gets the t mir sp by sp id.
	 *
	 * @param salesPosId the sales pos id
	 * @param userDetails the user details
	 * @return the t mir sp by sp id
	 */
	List<Object[]> getTMirSpBySpId(Long salesPosId, Short tenantId);
}
