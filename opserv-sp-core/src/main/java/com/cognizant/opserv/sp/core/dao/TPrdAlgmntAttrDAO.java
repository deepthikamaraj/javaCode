package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TAttrDef;
import com.cognizant.opserv.sp.core.entity.TPrdAlgmnt;
import com.cognizant.opserv.sp.core.entity.TPrdAlgmntAttr;
import com.cognizant.opserv.sp.core.entity.TPrdAlgmntAttrId;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TPrdAlgmntAttr DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TPrdAlgmntAttrDAO {

	/**
	 * Stores a new TPrdAlgmntAttr entity object in to the persistent store
	 * 
	 * @param tPrdAlgmntAttr
	 *            TPrdAlgmntAttr Entity object to be persisted
	 * @return tPrdAlgmntAttr Persisted TPrdAlgmntAttr object
	 */
	TPrdAlgmntAttr createTPrdAlgmntAttr(TPrdAlgmntAttr tPrdAlgmntAttr);

	/**
	 * Deletes a TPrdAlgmntAttr entity object from the persistent store
	 * 
	 * @param tPrdAlgmntAttr
	 *            TPrdAlgmntAttr Entity object to be deleted
	 */
	void deleteTPrdAlgmntAttr(TPrdAlgmntAttrId tPrdAlgmntAttrId);

	/**
	 * Updates a TPrdAlgmntAttr entity object in to the persistent store
	 * 
	 * @param tPrdAlgmntAttr
	 *            TPrdAlgmntAttr Entity object to be updated
	 * @return tPrdAlgmntAttr Persisted TPrdAlgmntAttr object
	 */
	TPrdAlgmntAttr updateTPrdAlgmntAttr(TPrdAlgmntAttr tPrdAlgmntAttr);

	/**
	 * Retrieve an TPrdAlgmntAttr object based on given TPrdAlgmntAttrId.
	 * 
	 * @param tPrdAlgmntAttrId
	 *            the primary key value of the TPrdAlgmntAttr Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TPrdAlgmntAttr findTPrdAlgmntAttrById(TPrdAlgmntAttrId tPrdAlgmntAttrId);

	/**
	 * Retrieve TPrdAlgmntAttr based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TPrdAlgmntAttr> list of TPrdAlgmntAttrs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TPrdAlgmntAttr> findTPrdAlgmntAttrs(SearchFilter<TPrdAlgmntAttr> searchFilter);

	/**
	 * Count TPrdAlgmntAttr based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTPrdAlgmntAttrs(SearchFilter<TPrdAlgmntAttr> searchFilter);

	/**
	 * Retrieve TPrdAlgmntAttr based on given search criteria using JPA named Query.
	 * The search criteria is of TPrdAlgmnt type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TPrdAlgmntAttr> list of TPrdAlgmntAttrs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TPrdAlgmntAttr> getTPrdAlgmntAttrsByTPrdAlgmnt(SearchFilter<TPrdAlgmnt> searchFilter);

	/**
	 * Count TPrdAlgmntAttr based on given search criteria using JPA named Query.
	 * The search criteria is of TPrdAlgmnt type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTPrdAlgmntAttrsByTPrdAlgmnt(SearchFilter<TPrdAlgmnt> searchFilter);

	/**
	 * Retrieve TPrdAlgmntAttr based on given search criteria using JPA named Query.
	 * The search criteria is of TAttrDef type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TPrdAlgmntAttr> list of TPrdAlgmntAttrs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TPrdAlgmntAttr> getTPrdAlgmntAttrsByTAttrDef(SearchFilter<TAttrDef> searchFilter);

	/**
	 * Count TPrdAlgmntAttr based on given search criteria using JPA named Query.
	 * The search criteria is of TAttrDef type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTPrdAlgmntAttrsByTAttrDef(SearchFilter<TAttrDef> searchFilter);

	List<TPrdAlgmntAttr> findPrdExtAttrByPrdAlgmnt(Long prdAlgmntId, List<Long> attrId);

	List<TPrdAlgmntAttr> GetTPrdAlgmntAttrById(
			SearchFilter<TPrdAlgmntAttr> searchFilter);

	List<TPrdAlgmntAttr> getTPrdAlgmntAttrByAttrId(TPrdAlgmntAttr tPrdAlgmntAttr);

}
