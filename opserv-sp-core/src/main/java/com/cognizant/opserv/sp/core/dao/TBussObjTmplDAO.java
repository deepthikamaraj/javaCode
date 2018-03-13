package com.cognizant.opserv.sp.core.dao;

import java.util.Date;
import java.util.List;

import com.cognizant.opserv.sp.core.entity.TBussObj;
import com.cognizant.opserv.sp.core.entity.TBussObjTmpl;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TBussObjTmpl DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TBussObjTmplDAO {

	/**
	 * Stores a new TBussObjTmpl entity object in to the persistent store
	 * 
	 * @param tBussObjTmpl
	 *            TBussObjTmpl Entity object to be persisted
	 * @return tBussObjTmpl Persisted TBussObjTmpl object
	 */
	TBussObjTmpl createTBussObjTmpl(TBussObjTmpl tBussObjTmpl);

	/**
	 * Deletes a TBussObjTmpl entity object from the persistent store
	 * 
	 * @param tBussObjTmpl
	 *            TBussObjTmpl Entity object to be deleted
	 */
	void deleteTBussObjTmpl(Integer tmplId);

	/**
	 * Updates a TBussObjTmpl entity object in to the persistent store
	 * 
	 * @param tBussObjTmpl
	 *            TBussObjTmpl Entity object to be updated
	 * @return tBussObjTmpl Persisted TBussObjTmpl object
	 */
	TBussObjTmpl updateTBussObjTmpl(TBussObjTmpl tBussObjTmpl);

	/**
	 * Retrieve an TBussObjTmpl object based on given tmplId.
	 * 
	 * @param tmplId
	 *            the primary key value of the TBussObjTmpl Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TBussObjTmpl findTBussObjTmplById(Integer tmplId);

	/**
	 * Retrieve TBussObjTmpl based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TBussObjTmpl> list of TBussObjTmpls if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TBussObjTmpl> findTBussObjTmpls(SearchFilter<TBussObjTmpl> searchFilter);

	/**
	 * Count TBussObjTmpl based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTBussObjTmpls(SearchFilter<TBussObjTmpl> searchFilter);

	/**
	 * Retrieve TBussObjTmpl based on given search criteria using JPA named Query.
	 * The search criteria is of TBussObj type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TBussObjTmpl> list of TBussObjTmpls if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TBussObjTmpl> getTBussObjTmplsByTBussObj(SearchFilter<TBussObj> searchFilter);

	/**
	 * Count TBussObjTmpl based on given search criteria using JPA named Query.
	 * The search criteria is of TBussObj type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTBussObjTmplsByTBussObj(SearchFilter<TBussObj> searchFilter);
   
	/**
	 * Find t buss obj tmpl by buss obj id.
	 *
	 * @param bussObjId the buss obj id
	 * @param tenantId the tenant id
	 * @return the list
	 */
	List<TBussObjTmpl> findTBussObjTmplByBussObjId(Integer bussObjId, Short tenantId);
	
	/**
	 * Find active t buss obj tmpl by t buss obj.
	 *
	 * @param tBussObj the t buss obj
	 * @return the list
	 */
	List<TBussObjTmpl> findActiveTBussObjTmplByTBussObj(TBussObj tBussObj);
	
	/**
	 * Delete from t buss obj tmpl hist table.
	 *
	 * @param tmplId the tmpl id
	 * @return the int
	 */
	int deleteFromTBussObjTmplHistTable(Integer tmplId);
	
	/**
	 * Find def t buss obj tmpl.
	 *
	 * @param bussFunctionType the buss function type
	 * @param defFlag the def flag
	 * @param activeFlag the active flag
	 * @param effStartDt the eff start dt
	 * @param effEndDt the eff end dt
	 * @return the list
	 */
	List<TBussObjTmpl> findDefTBussObjTmpl(String bussFunctionType, Character defFlag,Character activeFlag,Date effStartDt,Date effEndDt);

	/**
	 * Find all buss obj id by tmpl id.
	 *
	 * @param templateId the template id
	 * @param tenantId the tenant id
	 * @return the t buss obj tmpl
	 */
	TBussObjTmpl findAllBussObjIdByTmplId(Integer templateId, short tenantId);
}
