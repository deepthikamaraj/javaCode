package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TAttrDef;
import com.cognizant.opserv.sp.core.entity.TSalesPos;
import com.cognizant.opserv.sp.core.entity.TSalesPosAttr;
import com.cognizant.opserv.sp.core.entity.TSalesPosAttrId;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TSalesPosAttr DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TSalesPosAttrDAO {

	/**
	 * Stores a new TSalesPosAttr entity object in to the persistent store
	 * 
	 * @param tSalesPosAttr
	 *            TSalesPosAttr Entity object to be persisted
	 * @return tSalesPosAttr Persisted TSalesPosAttr object
	 */
	TSalesPosAttr createTSalesPosAttr(TSalesPosAttr tSalesPosAttr);

	/**
	 * Deletes a TSalesPosAttr entity object from the persistent store
	 * 
	 * @param tSalesPosAttr
	 *            TSalesPosAttr Entity object to be deleted
	 */
	void deleteTSalesPosAttr(TSalesPosAttrId tSalesPosAttrId);

	/**
	 * Updates a TSalesPosAttr entity object in to the persistent store
	 * 
	 * @param tSalesPosAttr
	 *            TSalesPosAttr Entity object to be updated
	 * @return tSalesPosAttr Persisted TSalesPosAttr object
	 */
	TSalesPosAttr updateTSalesPosAttr(TSalesPosAttr tSalesPosAttr);

	/**
	 * Retrieve an TSalesPosAttr object based on given TSalesPosAttrId.
	 * 
	 * @param tSalesPosAttrId
	 *            the primary key value of the TSalesPosAttr Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TSalesPosAttr findTSalesPosAttrById(TSalesPosAttrId tSalesPosAttrId);

	/**
	 * Retrieve TSalesPosAttr based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TSalesPosAttr> list of TSalesPosAttrs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TSalesPosAttr> findTSalesPosAttrs(SearchFilter<TSalesPosAttr> searchFilter);

	/**
	 * Count TSalesPosAttr based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTSalesPosAttrs(SearchFilter<TSalesPosAttr> searchFilter);

	/**
	 * Retrieve TSalesPosAttr based on given search criteria using JPA named Query.
	 * The search criteria is of TSalesPos type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TSalesPosAttr> list of TSalesPosAttrs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TSalesPosAttr> getTSalesPosAttrsByTSalesPos(SearchFilter<TSalesPos> searchFilter);

	/**
	 * Count TSalesPosAttr based on given search criteria using JPA named Query.
	 * The search criteria is of TSalesPos type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTSalesPosAttrsByTSalesPos(SearchFilter<TSalesPos> searchFilter);

	/**
	 * Retrieve TSalesPosAttr based on given search criteria using JPA named Query.
	 * The search criteria is of TAttrDef type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TSalesPosAttr> list of TSalesPosAttrs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TSalesPosAttr> getTSalesPosAttrsByTAttrDef(SearchFilter<TAttrDef> searchFilter);

	/**
	 * Count TSalesPosAttr based on given search criteria using JPA named Query.
	 * The search criteria is of TAttrDef type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTSalesPosAttrsByTAttrDef(SearchFilter<TAttrDef> searchFilter);
	
	List<TSalesPosAttr> findTSalesPosAttrByTAttrDefPosIdAndTenant(long AttrdefId,long salesPosId,Short tenantId);
	
	List<TSalesPosAttr> getTSalesPosAttrsById(SearchFilter<TSalesPosAttr> searchFilter);
	
	List<TSalesPosAttr> getTSalesPosAttrByAttrId(TSalesPosAttr tSalesPosAttr);

	List<Object[]> getTSalesPosAttrValBySP(Long spId, Short tenatId);

}
