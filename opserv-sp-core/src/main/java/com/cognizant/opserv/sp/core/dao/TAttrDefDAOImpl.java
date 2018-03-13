package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TAttrDataType;
import com.cognizant.opserv.sp.core.entity.TAttrDef;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TAttrDefDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tAttrDefDAO")
public class TAttrDefDAOImpl implements TAttrDefDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TAttrDefDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	//private static final String TATTRDATATYPE = "tAttrDataType";

	private final Class<TAttrDef> clazz;

	public Class<TAttrDef> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TAttrDefDAOImpl() {
		super();
		this.clazz = TAttrDef.class;
	}

	private static final String JPAQL = "select tAttrDefentity from TAttrDef tAttrDefentity";

	private static final String JPACOUNTQL = "select count(tAttrDefentity) from TAttrDef tAttrDefentity";

	private static final String[] RESTRICTIONS = { "tAttrDefentity.attrId = #{tAttrDefentity.getAttrId}",
			"tAttrDefentity.attrName like '#{tAttrDefentity.getAttrName}%'",
			"tAttrDefentity.attrDesc like '#{tAttrDefentity.getAttrDesc}%'",
			"tAttrDefentity.attrType = #{tAttrDefentity.getAttrType}",
			"tAttrDefentity.allowedValue like '#{tAttrDefentity.getAllowedValue}%'",
			"tAttrDefentity.groupAttrFlag = #{tAttrDefentity.getGroupAttrFlag}",
			"Date(tAttrDefentity.effStartDt) = '#{tAttrDefentity.getEffStartDt}'",
			"Date(tAttrDefentity.effEndDt) = '#{tAttrDefentity.getEffEndDt}'",
			"tAttrDefentity.dynAttrFlag = #{tAttrDefentity.getDynAttrFlag}",
			"tAttrDefentity.activeFlag = #{tAttrDefentity.getActiveFlag}",
			"tAttrDefentity.displayName like '#{tAttrDefentity.getDisplayName}%'",
			"tAttrDefentity.entityId = #{tAttrDefentity.getEntityId}",
			"tAttrDefentity.attrLen = #{tAttrDefentity.getAttrLen}",
			"tAttrDefentity.createdBy = #{tAttrDefentity.getCreatedBy}",
			"Date(tAttrDefentity.createDt) = '#{tAttrDefentity.getCreateDt}'",
			"tAttrDefentity.updatedBy = #{tAttrDefentity.getUpdatedBy}",
			"Date(tAttrDefentity.updateDt) = '#{tAttrDefentity.getUpdateDt}'",
			"tAttrDefentity.tenantId = #{tAttrDefentity.getTenantId}",
			"tAttrDefentity.mtrFlag = #{tAttrDefentity.getMtrFlag}",
			"tAttrDefentity.cvgFlag = #{tAttrDefentity.getCvgFlag}",
			"tAttrDefentity.elFlag = #{tAttrDefentity.getElFlag}",
			"tAttrDefentity.attrDataTypeId = #{tAttrDefentity.getAttrDataTypeId}" };

	/**
	 * Stores a new TAttrDef entity object in to the persistent store
	 * 
	 * @param tAttrDef
	 *            TAttrDef Entity object to be persisted
	 * @return tAttrDef Persisted TAttrDef object
	 */
	public TAttrDef createTAttrDef(final TAttrDef tAttrDef) {
		LOGGER.info("=========== Create TAttrDef ===========");
		return genericDAO.store(tAttrDef);
	}

	/**
	 * Deletes a TAttrDef entity object from the persistent store
	 * 
	 * @param tAttrDef
	 *            TAttrDef Entity object to be deleted
	 */
	public void deleteTAttrDef(final Long attrId) {
		LOGGER.info("=========== Delete TAttrDef ===========");
		final TAttrDef tAttrDef = genericDAO.get(clazz, attrId);
		genericDAO.remove(tAttrDef);
	}

	/**
	 * Updates a TAttrDef entity object in to the persistent store
	 * 
	 * @param tAttrDef
	 *            TAttrDef Entity object to be updated
	 * @return tAttrDef Persisted TAttrDef object
	 */
	public TAttrDef updateTAttrDef(final TAttrDef tAttrDef) {
		LOGGER.info("=========== Update TAttrDef ===========");
		return genericDAO.update(tAttrDef);
	}

	/**
	 * Retrieve an TAttrDef object based on given TAttrDef attrId.
	 * 
	 * @param tAttrDefId
	 *            the primary key value of the TAttrDef Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TAttrDef findTAttrDefById(final Long tAttrDefId) {
		LOGGER.info("find TAttrDef instance with attrId: " + tAttrDefId);
		return genericDAO.get(clazz, tAttrDefId);
	}

	/**
	 * Retrieve TAttrDef based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TAttrDef> list of TAttrDef if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TAttrDef> findTAttrDefs(final SearchFilter<TAttrDef> searchFilter) {
		LOGGER.info("=========== Find TAttrDefs ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TAttrDef tAttrDef = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tAttrDefentity", tAttrDef);

		/*if (tAttrDef.getTAttrDataType() == null) {
			jpaQuery.bind(TATTRDATATYPE, new TAttrDataType());
		} else {
			LOGGER.info("tAttrDataType {}", tAttrDef.getTAttrDataType());

			jpaQuery.bind(TATTRDATATYPE, tAttrDef.getTAttrDataType());
		}*/
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TAttrDefs.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTAttrDefs(final SearchFilter<TAttrDef> searchFilter) {
		LOGGER.info("=========== Count TAttrDef ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TAttrDef tAttrDef = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tAttrDefentity", tAttrDef);

		/*if (tAttrDef.getTAttrDataType() == null) {
			jpaCountQuery.bind(TATTRDATATYPE, new TAttrDataType());
		} else {
			LOGGER.info("tAttrDataType {}", tAttrDef.getTAttrDataType());

			jpaCountQuery.bind(TATTRDATATYPE, tAttrDef.getTAttrDataType());
		}*/

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TAttrDef based on given search criteria using JPA named Query.
	 * The search criteria is of TAttrDataType type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TAttrDef> list of TAttrDefs if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TAttrDef> getTAttrDefsByTAttrDataType(final SearchFilter<TAttrDataType> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		List<Object> tAttrDataTypeList = new ArrayList<Object>();
		tAttrDataTypeList.add(searchFilter.getEntityClass().gettAttrDataTypeId().getAttrDataTypeId());
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTAttrDefByTAttrDataType", tAttrDataTypeList, index, maxresult);
	}

	/**
	 * Count TAttrDef based on given search criteria using JPA named Query.
	 * The search criteria is of TAttrDataType type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTAttrDefsByTAttrDataType(final SearchFilter<TAttrDataType> searchFilter) {

		//final TAttrDataType tAttrDataType = searchFilter.getEntityClass();
		List<Object> tAttrDataTypeList = new ArrayList<Object>();
		tAttrDataTypeList.add(searchFilter.getEntityClass().gettAttrDataTypeId().getAttrDataTypeId());
		return genericDAO.findEntitiesByNamedQuery("CountTAttrDefsByTAttrDataType", tAttrDataTypeList);
	}
	
	/**
	 * Retrieve an TAttrDef object based on given attrName.
	 * 
	 * @param attrName
	 *            the attrName of the TAttrDef Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	@Override
	public List<Object> findTAttrDefByAttrName(final String attrName,Short tenantId,int index,int maxresult) {
		LOGGER.info("find TAttrDef instance with attrName: " + attrName);
		List list = new ArrayList();
		list.add(attrName);
		list.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTAttrDefByAttrName", list,0,-1);
	}
	
	/**
	 * Gets the attribute of buss obj by id.
	 *
	 * @param bussObjId the buss obj id
	 * @param tenantId the tenant id
	 * @return the attribute of buss obj by id
	 */
	@Override
	public List<TAttrDef> getAttributeOfBussObjById(final Integer bussObjId,final short tenantId) {
		List list = new ArrayList();
		list.add(bussObjId);
		list.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("findTAttrDefByBussObj", list,0,-1);
	}
	/**
	 * Gets the attribute of alg templ id.
	 *
	 * @param algTemplId the alg templ id
	 * @param tenantId the tenant id
	 * @return the attribute of alg templ id
	 */
	@Override
	public List<TAttrDef> getAttributeOfAlgTemplId(Integer algTemplId,
			short tenantId) {
		List list = new ArrayList();
		list.add(algTemplId);
		list.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("findTAttrDefByAlgTemplId", list,0,-1);
	}
	/**
	 * Find t attr def by entity id and tenant id.
	 *
	 * @param searchFilter the search filter
	 * @return the list
	 */
	@Override
	public List<TAttrDef> findTAttrDefByEntityIdAndTenantId(final SearchFilter<TAttrDef> searchFilter) {
		int index = searchFilter.getPaginationInfo().getStartIndex();
		int maxresult = searchFilter.getPaginationInfo().getMaxRows();
		final TAttrDef tAttrDef = searchFilter.getEntityClass();
		final Short tenantId = tAttrDef.getTenantId();
		final Integer entityId = tAttrDef.getEntityId();
		final Character dynaAttrFlag = tAttrDef.getDynAttrFlag();
		@SuppressWarnings("rawtypes")
		List paramList = new ArrayList<>();
		paramList.add(tenantId);
		paramList.add(entityId);
		paramList.add(dynaAttrFlag);
		List<TAttrDef> tAttrDefList = genericDAO.findEntitiesByNamedQueryMultiCond("FindTAttrDefByEntityIdAndTenantId", paramList, index, maxresult);
		return tAttrDefList;
	}
	/**
	 * Find t attr def byattr id.
	 *
	 * @param attrId the attr id
	 * @param entityId the entity id
	 * @return the list
	 */
	@Override
	public List<TAttrDef> findTAttrDefByattrId(final Long attrId,final Integer entityId) {
		
		List list = new ArrayList();
		list.add(attrId);
		list.add(entityId);	
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTAttrDefByattrId", list,0,-1);
	}

/* Start : Added By Somanath Nanda (408372) for Metric Calculation */
	/**
	 * Find attr and entity.
	 *
	 * @param attirbuteIds the attirbute ids
	 * @return the list
	 */
	@Override
	public List<Object> findAttrAndEntity(Set<Long> attributeIds) {
		final List<Object> queryParam = new ArrayList<Object>(attributeIds);
		return genericDAO.findEntitiesByNamedInQuery("findAttributeAndEntitiesINQuery", queryParam, 0, -1);
	}
	
	/**
	 * Find t attr def by tenant id.
	 *
	 * @param tenantId the tenant id
	 * @return the list
	 */
	@Override
	public List<TAttrDef> findTAttrDefByTenantId(final short tenantId) {
		List list = new ArrayList<>();
		list.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindAllTAttrDefsByTenantId", list,0,-1);
	}
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
	@Override
	public List<Object[]> getAttributeInfoByAlBuStBobjName(Long alId,Long buId, Long stId,String busObjName,Short tenatId) {
		final List<Object> queryParam = new ArrayList<Object>();
		queryParam.add(alId);
		queryParam.add(buId);
		queryParam.add(stId);
		queryParam.add(busObjName);
		queryParam.add(tenatId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("GetAttributeInfoByAlBuStBobjName", queryParam, 0, -1);
	}
	/**
	 * Find extend attr list.
	 *
	 * @param tenantId the tenant id
	 * @return the list
	 */
	@Override
	public List<TAttrDef> findExtendAttrList(Short tenantId) {
		List list = new ArrayList<>();
		list.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindExtendAttrsByTenantId", list,0,-1);
	}
	
	@Override
	public List<Object[]> getAttributesByAlBuStBobjName(Long alId,Long buId, Long stId,String busObjName,Short tenatId) {
		final List<Object> queryParam = new ArrayList<Object>();
		queryParam.add(alId);
		queryParam.add(buId);
		queryParam.add(stId);
		queryParam.add(busObjName);
		queryParam.add(tenatId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("GetAttributesByAlBuStBobjName", queryParam, 0, -1);
	}
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
	@Override
	public List<Object[]> getActiveAttributesByAlBuStBobjName(Long alId,Long buId, Long stId,String busObjName,Short tenatId) {
		final List<Object> queryParam = new ArrayList<Object>();
		queryParam.add(alId);
		queryParam.add(buId);
		queryParam.add(stId);
		queryParam.add(busObjName);
		queryParam.add(tenatId);		
		return genericDAO.findEntitiesByNamedQueryMultiCond("GetActiveAttributesByAlBuStBobjName", queryParam, 0, -1);
	}
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
	@Override
	public List<Object[]> getActiveAttributesByAlBuStEntities(Long alId,Long buId, Long stId,List<Integer> eIds,Short tenatId) {
		final List<Object> queryParam = new ArrayList<Object>();
		queryParam.add(alId);
		queryParam.add(buId);
		queryParam.add(stId);
		queryParam.add(eIds);
		queryParam.add(tenatId);		
		return genericDAO.findEntitiesByNamedQueryMultiCond("GetActiveAttributesByAlBuStEntities", queryParam, 0, -1);
	}
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
	@Override
	public List<Object[]> getSearchActiveAttributesByAlBuStBobjName(Long alId,Long buId, Long stId,String busObjName,Short tenatId) {
		final List<Object> queryParam = new ArrayList<Object>();
		queryParam.add(alId);
		queryParam.add(buId);
		queryParam.add(stId);
		queryParam.add(busObjName);
		queryParam.add(tenatId);		
		return genericDAO.findEntitiesByNamedQueryMultiCond("GetSearchActiveAttributesByAlBuStBobjName", queryParam, 0, -1);
	}
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
	@Override
	public List<Object[]> getSearchAttributeRulessByAlBuStBobjName(Long alId,Long buId, Long stId,String busObjName,Short tenatId) {
		final List<Object> queryParam = new ArrayList<Object>();
		queryParam.add(alId);
		queryParam.add(buId);
		queryParam.add(stId);
		queryParam.add(busObjName);
		queryParam.add(tenatId);		
		return genericDAO.findEntitiesByNamedQueryMultiCond("GetSearchAttributeRulessByAlBuStBobjName", queryParam, 0, -1);
	}
	/**
	 * Gets the search active attributes by al bu st tmpl.
	 *
	 * @param temId the tem id
	 * @param tenatId the tenat id
	 * @return the search active attributes by al bu st tmpl
	 */
	@Override
	public List<Object[]> getSearchActiveAttributesByAlBuStTmpl(Integer temId,Short tenatId) {
		final List<Object> queryParam = new ArrayList<Object>();		
		queryParam.add(temId);
		queryParam.add(tenatId);		
		return genericDAO.findEntitiesByNamedQueryMultiCond("GetSearchActiveAttributesByAlBuStTmpl", queryParam, 0, -1);
	}
	/**
	 * Gets the search attribute ruless by al bu st tmpl.
	 *
	 * @param temId the tem id
	 * @param tenatId the tenat id
	 * @return the search attribute ruless by al bu st tmpl
	 */
	@Override
	public List<Object[]> getSearchAttributeRulessByAlBuStTmpl(Integer temId,Short tenatId) {
		final List<Object> queryParam = new ArrayList<Object>();		
		queryParam.add(temId);
		queryParam.add(tenatId);		
		return genericDAO.findEntitiesByNamedQueryMultiCond("GetSearchAttributeRulessByAlBuStTmpl", queryParam, 0, -1);
	}
	/**
	 * Gets the active attributes by al bu st tmpl.
	 *
	 * @param temId the tem id
	 * @param tenatId the tenat id
	 * @return the active attributes by al bu st tmpl
	 */
	@Override
	public List<Object[]> getActiveAttributesByAlBuStTmpl(Integer temId,Short tenatId) {
		final List<Object> queryParam = new ArrayList<Object>();		
		queryParam.add(temId);
		queryParam.add(tenatId);		
		return genericDAO.findEntitiesByNamedQueryMultiCond("GetActiveAttributesByAlBuStTmpl", queryParam, 0, -1);
	}
	/**
	 * Gets the attribute ruless by al bu st tmpl.
	 *
	 * @param temId the tem id
	 * @param tenatId the tenat id
	 * @return the attribute ruless by al bu st tmpl
	 */
	@Override
	public List<Object[]> getAttributeRulessByAlBuStTmpl(Integer temId,Short tenatId) {
		final List<Object> queryParam = new ArrayList<Object>();		
		queryParam.add(temId);
		queryParam.add(tenatId);		
		return genericDAO.findEntitiesByNamedQueryMultiCond("GetAttributeRulessByAlBuStTmpl", queryParam, 0, -1);
	}
	
	
}
