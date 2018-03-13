package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TAttrDef;
import com.cognizant.opserv.sp.core.entity.TPrd;
import com.cognizant.opserv.sp.core.entity.TPrdAttr;
import com.cognizant.opserv.sp.core.entity.TPrdAttrId;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TPrdAttr DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TPrdAttrDAO {

	/**
	 * Stores a new TPrdAttr entity object in to the persistent store
	 * 
	 * @param tPrdAttr
	 *            TPrdAttr Entity object to be persisted
	 * @return tPrdAttr Persisted TPrdAttr object
	 */
	TPrdAttr createTPrdAttr(TPrdAttr tPrdAttr);

	/**
	 * Deletes a TPrdAttr entity object from the persistent store
	 * 
	 * @param tPrdAttr
	 *            TPrdAttr Entity object to be deleted
	 */
	void deleteTPrdAttr(TPrdAttrId tPrdAttrId);

	/**
	 * Updates a TPrdAttr entity object in to the persistent store
	 * 
	 * @param tPrdAttr
	 *            TPrdAttr Entity object to be updated
	 * @return tPrdAttr Persisted TPrdAttr object
	 */
	TPrdAttr updateTPrdAttr(TPrdAttr tPrdAttr);

	/**
	 * Retrieve an TPrdAttr object based on given TPrdAttrId.
	 * 
	 * @param tPrdAttrId
	 *            the primary key value of the TPrdAttr Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TPrdAttr findTPrdAttrById(TPrdAttrId tPrdAttrId);

	/**
	 * Retrieve TPrdAttr based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TPrdAttr> list of TPrdAttrs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TPrdAttr> findTPrdAttrs(SearchFilter<TPrdAttr> searchFilter);

	/**
	 * Count TPrdAttr based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTPrdAttrs(SearchFilter<TPrdAttr> searchFilter);

	/**
	 * Retrieve TPrdAttr based on given search criteria using JPA named Query.
	 * The search criteria is of TPrd type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TPrdAttr> list of TPrdAttrs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TPrdAttr> getTPrdAttrsByTPrd(SearchFilter<TPrd> searchFilter);

	/**
	 * Count TPrdAttr based on given search criteria using JPA named Query.
	 * The search criteria is of TPrd type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTPrdAttrsByTPrd(SearchFilter<TPrd> searchFilter);

	/**
	 * Retrieve TPrdAttr based on given search criteria using JPA named Query.
	 * The search criteria is of TAttrDef type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TPrdAttr> list of TPrdAttrs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TPrdAttr> getTPrdAttrsByTAttrDef(SearchFilter<TAttrDef> searchFilter);

	/**
	 * Count TPrdAttr based on given search criteria using JPA named Query.
	 * The search criteria is of TAttrDef type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTPrdAttrsByTAttrDef(SearchFilter<TAttrDef> searchFilter);
	
	
    /**
     * 
     * @param searchFilter
     * @return
     */
	List<TPrdAttr> GetTPrdAttrById(SearchFilter<TPrdAttr> searchFilter);

	List<TPrdAttr> getTPrdAttrByAttrId(TPrdAttr tPrdAttr);

	List findPrdExtAttrByGrp(Long algmntId,String bussUnitId,Long salesTeamId,Long prdId,short tenantId);
	
	List<TPrdAttr> findPrdExtAttrByPrdId(Long prdId, Long attrId,short tenantId);
	
	List<TPrdAttr> findPrdExtAttrByPrdIdAndAttrList(Long prdId, List<Long> attrId);

	List findPrdExtAttrByGrpDefine(Long algmntId, String bussUnitId,Long salesTeamId, short tenantId);
	
	

	public List<Object[]> findTPrdAttrByPrdIDList(List<Long> prdIdList ,
			Long algmntId,String bussUnitId,Long salesTeamId,short tenantId);
	
	
}
