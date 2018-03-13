package com.cognizant.opserv.sp.core.dao;


import java.util.List;

import com.cognizant.opserv.sp.core.entity.TAlgmnt;
import com.cognizant.opserv.sp.core.entity.TAlgmntSalesTeam;
import com.cognizant.opserv.sp.core.entity.TAlgmntTmpl;
import com.cognizant.opserv.sp.core.entity.TBussObjTmpl;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TAlgmntTmpl DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TAlgmntTmplDAO {

	/**
	 * Stores a new TAlgmntTmpl entity object in to the persistent store
	 * 
	 * @param tAlgmntTmpl
	 *            TAlgmntTmpl Entity object to be persisted
	 * @return tAlgmntTmpl Persisted TAlgmntTmpl object
	 */
	TAlgmntTmpl createTAlgmntTmpl(TAlgmntTmpl tAlgmntTmpl);

	/**
	 * Deletes a TAlgmntTmpl entity object from the persistent store
	 * 
	 * @param tAlgmntTmpl
	 *            TAlgmntTmpl Entity object to be deleted
	 */
	void deleteTAlgmntTmpl(Integer algmntTmplId);

	/**
	 * Updates a TAlgmntTmpl entity object in to the persistent store
	 * 
	 * @param tAlgmntTmpl
	 *            TAlgmntTmpl Entity object to be updated
	 * @return tAlgmntTmpl Persisted TAlgmntTmpl object
	 */
	TAlgmntTmpl updateTAlgmntTmpl(TAlgmntTmpl tAlgmntTmpl);

	/**
	 * Retrieve an TAlgmntTmpl object based on given algmntTmplId.
	 * 
	 * @param algmntTmplId
	 *            the primary key value of the TAlgmntTmpl Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TAlgmntTmpl findTAlgmntTmplById(Integer algmntTmplId);

	/**
	 * Retrieve TAlgmntTmpl based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TAlgmntTmpl> list of TAlgmntTmpls if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TAlgmntTmpl> findTAlgmntTmpls(SearchFilter<TAlgmntTmpl> searchFilter);

	/**
	 * Count TAlgmntTmpl based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTAlgmntTmpls(SearchFilter<TAlgmntTmpl> searchFilter);

	/**
	 * Retrieve TAlgmntTmpl based on given search criteria using JPA named Query.
	 * The search criteria is of TBussObjTmpl type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TAlgmntTmpl> list of TAlgmntTmpls if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TAlgmntTmpl> getTAlgmntTmplsByTBussObjTmpl(SearchFilter<TBussObjTmpl> searchFilter);

	/**
	 * Count TAlgmntTmpl based on given search criteria using JPA named Query.
	 * The search criteria is of TBussObjTmpl type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTAlgmntTmplsByTBussObjTmpl(SearchFilter<TBussObjTmpl> searchFilter);

	/**
	 * Retrieve TAlgmntTmpl based on given search criteria using JPA named Query.
	 * The search criteria is of TAlgmntSalesTeam type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TAlgmntTmpl> list of TAlgmntTmpls if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TAlgmntTmpl> getTAlgmntTmplsByTAlgmntSalesTeam(SearchFilter<TAlgmntSalesTeam> searchFilter);

	/**
	 * Count TAlgmntTmpl based on given search criteria using JPA named Query.
	 * The search criteria is of TAlgmntSalesTeam type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTAlgmntTmplsByTAlgmntSalesTeam(SearchFilter<TAlgmntSalesTeam> searchFilter);
	
	/**
	 * Find t algnmt tempt id by alg bu sales i ds.
	 *
	 * @param salesTeamId the sales team id
	 * @param algmntId the algmnt id
	 * @param bussUnitId the buss unit id
	 * @param tenantId the tenant id
	 * @return the list
	 */
	List<TAlgmntTmpl> FindTAlgnmtTemptIdByAlgBuSalesIDs(Long salesTeamId, Long algmntId,Long bussUnitId,short tenantId );
	
	/**
	 * Find t algmnt tmpls by t buss obj tmpl.
	 *
	 * @param tBussObjTmpl the t buss obj tmpl
	 * @return the list
	 */
	List<TAlgmntTmpl> findTAlgmntTmplsByTBussObjTmpl(TBussObjTmpl tBussObjTmpl);
	
	/**
	 * Find t algmnt tmpl by t algmnt.
	 *
	 * @param tAlgmnt the t algmnt
	 * @param tenantId the tenant id
	 * @return the list
	 */
	List<TAlgmntTmpl> findTAlgmntTmplByTAlgmnt(TAlgmnt tAlgmnt,Short tenantId);

	/**
	 * Find t algnmt tmpl id by al bu st buss obj tenant.
	 *
	 * @param algmntId the algmnt id
	 * @param bussUnitId the buss unit id
	 * @param salesTeamId the sales team id
	 * @param bussEntyName the buss enty name
	 * @param tenantId the tenant id
	 * @return the list
	 */
	List<TAlgmntTmpl> findTAlgnmtTmplIdByAlBuSTBussObjTenant(long algmntId,long bussUnitId,long salesTeamId,String bussEntyName, Short tenantId);
	
	/**
	 * Find algmnt of template.
	 *
	 * @param tmplId the tmpl id
	 * @param tenantId the tenant id
	 * @param flag the flag
	 * @return the list
	 */
	List<Object[]> findAlgmntOfTemplate(Integer tmplId,Short tenantId,Character flag);

	/**
	 * Find t algnmt tmpl id by al bu st buss obj.
	 *
	 * @param algmntId the algmnt id
	 * @param bussUnitId the buss unit id
	 * @param salesTeamId the sales team id
	 * @param bussEntyName the buss enty name
	 * @param tenantId the tenant id
	 * @return the list
	 */
	List<Integer> findTAlgnmtTmplIdByAlBuSTBussObj(long algmntId,
			long bussUnitId, long salesTeamId, String bussEntyName,
			Short tenantId);

	/**
	 * Find t algnmt tmpl id by al bu st buss obj id.
	 *
	 * @param AlId the al id
	 * @param BuId the bu id
	 * @param StId the st id
	 * @param bussObjId the buss obj id
	 * @param tenantId the tenant id
	 * @return the list
	 */
	List<Integer> findTAlgnmtTmplIdByAlBuSTBussObjId(Long AlId,Long BuId,Long StId,Integer bussObjId, short tenantId);
}
