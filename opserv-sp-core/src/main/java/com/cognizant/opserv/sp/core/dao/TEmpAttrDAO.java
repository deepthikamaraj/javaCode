package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TEmpAttr;
import com.cognizant.opserv.sp.core.entity.TEmpAttrId;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TEmpAttr DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TEmpAttrDAO {

	/**
	 * Stores a new TEmpAttr entity object in to the persistent store
	 * 
	 * @param tEmpAttr
	 *            TEmpAttr Entity object to be persisted
	 * @return tEmpAttr Persisted TEmpAttr object
	 */
	TEmpAttr createTEmpAttr(TEmpAttr tEmpAttr);

	/**
	 * Deletes a TEmpAttr entity object from the persistent store
	 * 
	 * @param tEmpAttr
	 *            TEmpAttr Entity object to be deleted
	 */
	void deleteTEmpAttr(TEmpAttrId tEmpAttrId);

	/**
	 * Updates a TEmpAttr entity object in to the persistent store
	 * 
	 * @param tEmpAttr
	 *            TEmpAttr Entity object to be updated
	 * @return tEmpAttr Persisted TEmpAttr object
	 */
	TEmpAttr updateTEmpAttr(TEmpAttr tEmpAttr);

	/**
	 * Retrieve an TEmpAttr object based on given TEmpAttrId.
	 * 
	 * @param tEmpAttrId
	 *            the primary key value of the TEmpAttr Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TEmpAttr findTEmpAttrById(TEmpAttrId tEmpAttrId);

	/**
	 * Retrieve TEmpAttr based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TEmpAttr> list of TEmpAttrs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TEmpAttr> findTEmpAttrs(SearchFilter<TEmpAttr> searchFilter);

	/**
	 * Count TEmpAttr based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTEmpAttrs(SearchFilter<TEmpAttr> searchFilter);
	
	/**
	 * 
	 * @param searchFilter
	 * @return
	 */
	List<TEmpAttr> GetTEmpAttrById(SearchFilter<TEmpAttr> searchFilter);

	List<TEmpAttr> getTEmpAttrByAttrId(TEmpAttr tEmpAttr);
	
	List findEmpExtAttrByGrp(Long algmntId,String bussUnitId,Long salesTeamId,Integer staffId,short tenantId);

	List<TEmpAttr> getTEmpAttrsByTAttrDef(Long attrId, Short tenantId);
  
	List<TEmpAttr> GetTEmpAttrById(int staffID, Short tenantId);
	
	List findEmpExtAttrByGrpDef(Long algmntId, String bussUnitId,Long salesTeamId,short tenantId);

	List<Object[]> findTEmpAttrByTAttGrpMapAndStaffID(List<Integer> empIdList,
			Long algmntId, String bussUnitId, Long salesTeamId, short tenantId);
	
	
}
