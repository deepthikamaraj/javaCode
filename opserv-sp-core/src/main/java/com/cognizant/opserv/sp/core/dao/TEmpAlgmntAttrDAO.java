package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TAttrDef;
import com.cognizant.opserv.sp.core.entity.TEmpAlgmnt;
import com.cognizant.opserv.sp.core.entity.TEmpAlgmntAttr;
import com.cognizant.opserv.sp.core.entity.TEmpAlgmntAttrId;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TEmpAlgmntAttr DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TEmpAlgmntAttrDAO {

	/**
	 * Stores a new TEmpAlgmntAttr entity object in to the persistent store
	 * 
	 * @param tEmpAlgmntAttr
	 *            TEmpAlgmntAttr Entity object to be persisted
	 * @return tEmpAlgmntAttr Persisted TEmpAlgmntAttr object
	 */
	TEmpAlgmntAttr createTEmpAlgmntAttr(TEmpAlgmntAttr tEmpAlgmntAttr);

	/**
	 * Deletes a TEmpAlgmntAttr entity object from the persistent store
	 * 
	 * @param tEmpAlgmntAttr
	 *            TEmpAlgmntAttr Entity object to be deleted
	 */
	void deleteTEmpAlgmntAttr(TEmpAlgmntAttrId tEmpAlgmntAttrId);

	/**
	 * Updates a TEmpAlgmntAttr entity object in to the persistent store
	 * 
	 * @param tEmpAlgmntAttr
	 *            TEmpAlgmntAttr Entity object to be updated
	 * @return tEmpAlgmntAttr Persisted TEmpAlgmntAttr object
	 */
	TEmpAlgmntAttr updateTEmpAlgmntAttr(TEmpAlgmntAttr tEmpAlgmntAttr);

	/**
	 * Retrieve an TEmpAlgmntAttr object based on given TEmpAlgmntAttrId.
	 * 
	 * @param tEmpAlgmntAttrId
	 *            the primary key value of the TEmpAlgmntAttr Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TEmpAlgmntAttr findTEmpAlgmntAttrById(TEmpAlgmntAttrId tEmpAlgmntAttrId);

	/**
	 * Retrieve TEmpAlgmntAttr based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TEmpAlgmntAttr> list of TEmpAlgmntAttrs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TEmpAlgmntAttr> findTEmpAlgmntAttrs(SearchFilter<TEmpAlgmntAttr> searchFilter);

	/**
	 * Count TEmpAlgmntAttr based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTEmpAlgmntAttrs(SearchFilter<TEmpAlgmntAttr> searchFilter);

	/**
	 * Retrieve TEmpAlgmntAttr based on given search criteria using JPA named Query.
	 * The search criteria is of TEmpAlgmnt type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TEmpAlgmntAttr> list of TEmpAlgmntAttrs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TEmpAlgmntAttr> getTEmpAlgmntAttrsByTEmpAlgmnt(SearchFilter<TEmpAlgmnt> searchFilter);

	/**
	 * Count TEmpAlgmntAttr based on given search criteria using JPA named Query.
	 * The search criteria is of TEmpAlgmnt type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTEmpAlgmntAttrsByTEmpAlgmnt(SearchFilter<TEmpAlgmnt> searchFilter);

	/**
	 * Retrieve TEmpAlgmntAttr based on given search criteria using JPA named Query.
	 * The search criteria is of TAttrDef type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TEmpAlgmntAttr> list of TEmpAlgmntAttrs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TEmpAlgmntAttr> getTEmpAlgmntAttrsByTAttrDef(SearchFilter<TAttrDef> searchFilter);

	/**
	 * Count TEmpAlgmntAttr based on given search criteria using JPA named Query.
	 * The search criteria is of TAttrDef type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTEmpAlgmntAttrsByTAttrDef(SearchFilter<TAttrDef> searchFilter);

}
