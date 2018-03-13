package com.cognizant.opserv.sp.core.dao;

import java.util.List;
import java.util.Set;

import com.cognizant.opserv.sp.core.entity.TAttrDataType;
import com.cognizant.opserv.sp.core.entity.TAttrDef;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TAttrDef DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TAttrDefDAO {

	/**
	 * Stores a new TAttrDef entity object in to the persistent store
	 * 
	 * @param tAttrDef
	 *            TAttrDef Entity object to be persisted
	 * @return tAttrDef Persisted TAttrDef object
	 */
	TAttrDef createTAttrDef(TAttrDef tAttrDef);

	/**
	 * Deletes a TAttrDef entity object from the persistent store
	 * 
	 * @param tAttrDef
	 *            TAttrDef Entity object to be deleted
	 */
	void deleteTAttrDef(Long attrId);

	/**
	 * Updates a TAttrDef entity object in to the persistent store
	 * 
	 * @param tAttrDef
	 *            TAttrDef Entity object to be updated
	 * @return tAttrDef Persisted TAttrDef object
	 */
	TAttrDef updateTAttrDef(TAttrDef tAttrDef);

	/**
	 * Retrieve an TAttrDef object based on given attrId.
	 * 
	 * @param attrId
	 *            the primary key value of the TAttrDef Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TAttrDef findTAttrDefById(Long attrId);

	/**
	 * Retrieve TAttrDef based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TAttrDef> list of TAttrDefs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TAttrDef> findTAttrDefs(SearchFilter<TAttrDef> searchFilter);

	/**
	 * Count TAttrDef based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTAttrDefs(SearchFilter<TAttrDef> searchFilter);

	/**
	 * Retrieve TAttrDef based on given search criteria using JPA named Query.
	 * The search criteria is of TAttrDataType type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TAttrDef> list of TAttrDefs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TAttrDef> getTAttrDefsByTAttrDataType(SearchFilter<TAttrDataType> searchFilter);

	/**
	 * Count TAttrDef based on given search criteria using JPA named Query.
	 * The search criteria is of TAttrDataType type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTAttrDefsByTAttrDataType(SearchFilter<TAttrDataType> searchFilter);
	
	/**
	 * Gets the attribute of buss obj by id.
	 *
	 * @param bussObjId the buss obj id
	 * @param tenantId the tenant id
	 * @return the attribute of buss obj by id
	 */
	List<TAttrDef> getAttributeOfBussObjById(Integer bussObjId, short tenantId);
	
	/**
	 * Find t attr def by attr name.
	 *
	 * @param attrName the attr name
	 * @param tenantId the tenant id
	 * @param index the index
	 * @param maxresult the maxresult
	 * @return the list
	 */
	List<Object> findTAttrDefByAttrName(String attrName,Short tenantId, int index,int maxresult);

	/**
	 * Find t attr def by entity id and tenant id.
	 *
	 * @param searchFilter the search filter
	 * @return the list
	 */
	List<TAttrDef> findTAttrDefByEntityIdAndTenantId(SearchFilter<TAttrDef> searchFilter);

	/**
	 * Find t attr def byattr id.
	 *
	 * @param attrId the attr id
	 * @param entityId the entity id
	 * @return the list
	 */
	List<TAttrDef> findTAttrDefByattrId(Long attrId,Integer  entityId);
	
	
	/* Start : Added By Somanath Nanda (408372) for Metric Calculation */
	
	/**
	 * Find attr and entity.
	 *
	 * @param attirbuteIds the attirbute ids
	 * @return the list
	 */
	List<Object> findAttrAndEntity(Set<Long> attirbuteIds);

	/**
	 * Find t attr def by tenant id.
	 *
	 * @param tenantId the tenant id
	 * @return the list
	 */
	List<TAttrDef> findTAttrDefByTenantId(short tenantId);

	/**
	 * Gets the attribute info by al bu st bobj name.
	 *
	 * @param alId the al id
	 * @param buId the bu id
	 * @param stId the st id
	 * @param busObjName the bus obj name
	 * @param tenatId the tenat id
	 * @return the attribute info by al bu st bobj name
	 */
	List<Object[]> getAttributeInfoByAlBuStBobjName(Long alId, Long buId,Long stId, String busObjName, Short tenatId);
	
	/**
	 * Gets the attributes by al bu st bobj name.
	 *
	 * @param alId the al id
	 * @param buId the bu id
	 * @param stId the st id
	 * @param busObjName the bus obj name
	 * @param tenatId the tenat id
	 * @return the attributes by al bu st bobj name
	 */
	List<Object[]> getAttributesByAlBuStBobjName(Long alId, Long buId,Long stId, String busObjName, Short tenatId);

	/**
	 * Gets the active attributes by al bu st bobj name.
	 *
	 * @param alId the al id
	 * @param buId the bu id
	 * @param stId the st id
	 * @param busObjName the bus obj name
	 * @param tenatId the tenat id
	 * @return the active attributes by al bu st bobj name
	 */
	List<Object[]> getActiveAttributesByAlBuStBobjName(Long alId,Long buId, Long stId, String busObjName, Short tenatId);

	/**
	 * Gets the active attributes by al bu st entities.
	 *
	 * @param alId the al id
	 * @param buId the bu id
	 * @param stId the st id
	 * @param eIds the e ids
	 * @param tenatId the tenat id
	 * @return the active attributes by al bu st entities
	 */
	List<Object[]> getActiveAttributesByAlBuStEntities(Long alId, Long buId,Long stId, List<Integer> eIds, Short tenatId);
	
	/* End : Added By Somanath Nanda (408372) for Metric Calculation */
	
	/**
	 * Find extend attr list.
	 *
	 * @param tenantId the tenant id
	 * @return the list
	 */
	List<TAttrDef> findExtendAttrList(Short tenantId);

	/**
	 * Gets the attribute of alg templ id.
	 *
	 * @param algTemplId the alg templ id
	 * @param tenantId the tenant id
	 * @return the attribute of alg templ id
	 */
	List<TAttrDef> getAttributeOfAlgTemplId(Integer algTemplId,short tenantId);

	/**
	 * Gets the search active attributes by al bu st bobj name.
	 *
	 * @param alId the al id
	 * @param buId the bu id
	 * @param stId the st id
	 * @param busObjName the bus obj name
	 * @param tenatId the tenat id
	 * @return the search active attributes by al bu st bobj name
	 */
	List<Object[]> getSearchActiveAttributesByAlBuStBobjName(Long alId,Long buId, Long stId, String busObjName, Short tenatId);

	/**
	 * Gets the search attribute ruless by al bu st bobj name.
	 *
	 * @param alId the al id
	 * @param buId the bu id
	 * @param stId the st id
	 * @param busObjName the bus obj name
	 * @param tenatId the tenat id
	 * @return the search attribute ruless by al bu st bobj name
	 */
	List<Object[]> getSearchAttributeRulessByAlBuStBobjName(Long alId,Long buId, Long stId, String busObjName, Short tenatId);

	/**
	 * Gets the search active attributes by al bu st tmpl.
	 *
	 * @param temId the tem id
	 * @param tenatId the tenat id
	 * @return the search active attributes by al bu st tmpl
	 */
	List<Object[]> getSearchActiveAttributesByAlBuStTmpl(Integer temId, Short tenatId);

	/**
	 * Gets the search attribute ruless by al bu st tmpl.
	 *
	 * @param temId the tem id
	 * @param tenatId the tenat id
	 * @return the search attribute ruless by al bu st tmpl
	 */
	List<Object[]> getSearchAttributeRulessByAlBuStTmpl(Integer temId, Short tenatId);

	/**
	 * Gets the active attributes by al bu st tmpl.
	 *
	 * @param temId the tem id
	 * @param tenatId the tenat id
	 * @return the active attributes by al bu st tmpl
	 */
	List<Object[]> getActiveAttributesByAlBuStTmpl(Integer temId, Short tenatId);

	/**
	 * Gets the attribute ruless by al bu st tmpl.
	 *
	 * @param temId the tem id
	 * @param tenatId the tenat id
	 * @return the attribute ruless by al bu st tmpl
	 */
	List<Object[]> getAttributeRulessByAlBuStTmpl(Integer temId, Short tenatId);
}
