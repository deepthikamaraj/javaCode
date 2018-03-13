package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TBussEntity;
import com.cognizant.opserv.sp.core.entity.TBussObj;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TBussEntityDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tBussEntityDAO")
public class TBussEntityDAOImpl implements TBussEntityDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TBussEntityDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private static final String TBUSSOBJ = "tBussObj";

	private final Class<TBussEntity> clazz;

	public Class<TBussEntity> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TBussEntityDAOImpl() {
		super();
		this.clazz = TBussEntity.class;
	}

	private static final String JPAQL = "select tBussEntityentity from TBussEntity tBussEntityentity";

	private static final String JPACOUNTQL = "select count(tBussEntityentity) from TBussEntity tBussEntityentity";

	private static final String[] RESTRICTIONS = {
			"tBussEntityentity.entityId like '#{tBussEntityentity.getEntityId}%'",
			"tBussEntityentity.entityType like '#{tBussEntityentity.getEntityType}%'",
			"tBussEntityentity.entityName like '#{tBussEntityentity.getEntityName}%'",
			"tBussEntityentity.entityAliasName like '#{tBussEntityentity.getEntityAliasName}%'",
			"tBussEntityentity.createdBy = #{tBussEntityentity.getCreatedBy}",
			"Date(tBussEntityentity.createDt) = '#{tBussEntityentity.getCreateDt}'",
			"tBussEntityentity.updatedBy = #{tBussEntityentity.getUpdatedBy}",
			"Date(tBussEntityentity.updateDt) = '#{tBussEntityentity.getUpdateDt}'",
			"tBussEntityentity.tenantId = #{tBussEntityentity.getTenantId}",
			"tBussEntityentity.tBussObj.bussObjId = #{tBussEntityentity.tBussObj.getBussObjId}" };

	/**
	 * Stores a new TBussEntity entity object in to the persistent store
	 * 
	 * @param tBussEntity
	 *            TBussEntity Entity object to be persisted
	 * @return tBussEntity Persisted TBussEntity object
	 */
	public TBussEntity createTBussEntity(final TBussEntity tBussEntity) {
		LOGGER.info("=========== Create TBussEntity ===========");
		return genericDAO.store(tBussEntity);
	}

	/**
	 * Deletes a TBussEntity entity object from the persistent store
	 * 
	 * @param tBussEntity
	 *            TBussEntity Entity object to be deleted
	 */
	public void deleteTBussEntity(final String entityId) {
		LOGGER.info("=========== Delete TBussEntity ===========");
		final TBussEntity tBussEntity = genericDAO.get(clazz, entityId);
		genericDAO.remove(tBussEntity);
	}

	/**
	 * Updates a TBussEntity entity object in to the persistent store
	 * 
	 * @param tBussEntity
	 *            TBussEntity Entity object to be updated
	 * @return tBussEntity Persisted TBussEntity object
	 */
	public TBussEntity updateTBussEntity(final TBussEntity tBussEntity) {
		LOGGER.info("=========== Update TBussEntity ===========");
		return genericDAO.update(tBussEntity);
	}

	/**
	 * Retrieve an TBussEntity object based on given TBussEntity entityId.
	 * 
	 * @param tBussEntityId
	 *            the primary key value of the TBussEntity Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TBussEntity findTBussEntityById(final Integer tBussEntityId) {
		LOGGER.info("find TBussEntity instance with entityId: " + tBussEntityId);
		return genericDAO.get(clazz, tBussEntityId);
	}

	/**
	 * Retrieve TBussEntity based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TBussEntity> list of TBussEntity if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TBussEntity> findTBussEntitys(final SearchFilter<TBussEntity> searchFilter) {
		LOGGER.info("=========== Find TBussEntitys ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TBussEntity tBussEntity = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tBussEntityentity", tBussEntity);
		jpaQuery.setCacheable(searchFilter.isCacheable());
		
		if (tBussEntity.getTBussObj() == null) {
			jpaQuery.bind(TBUSSOBJ, new TBussObj());
		} else {
			LOGGER.info("tBussObj {}" + tBussEntity.getTBussObj());

			jpaQuery.bind(TBUSSOBJ, tBussEntity.getTBussObj());
		}
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TBussEntitys.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTBussEntitys(final SearchFilter<TBussEntity> searchFilter) {
		LOGGER.info("=========== Count TBussEntity ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TBussEntity tBussEntity = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tBussEntityentity", tBussEntity);

		if (tBussEntity.getTBussObj() == null) {
			jpaCountQuery.bind(TBUSSOBJ, new TBussObj());
		} else {
			LOGGER.info("tBussObj {}"+ tBussEntity.getTBussObj());

			jpaCountQuery.bind(TBUSSOBJ, tBussEntity.getTBussObj());
		}

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TBussEntity based on given search criteria using JPA named Query.
	 * The search criteria is of TBussObj type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TBussEntity> list of TBussEntitys if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TBussEntity> getTBussEntitysByTBussObj(final SearchFilter<TBussObj> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TBussObj tBussObj = searchFilter.getEntityClass();
		List<Object> tBussObjList = new ArrayList<Object>();
		tBussObjList.add(searchFilter.getEntityClass());
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTBussEntityByTBussObj", tBussObjList, index, maxresult);
	}

	/**
	 * Count TBussEntity based on given search criteria using JPA named Query.
	 * The search criteria is of TBussObj type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTBussEntitysByTBussObj(final SearchFilter<TBussObj> searchFilter) {

		final TBussObj tBussObj = searchFilter.getEntityClass();
		List<Object> tBussObjList = new ArrayList<Object>();
		tBussObjList.add(searchFilter.getEntityClass());
		return genericDAO.findEntitiesByNamedQuery("CountTBussEntitysByTBussObj", tBussObjList);
	}

	/**
	 * Gets the t buss entitys by t pern id.
	 *
	 * @param searchFilter the search filter
	 * @return the t buss entitys by t pern id
	 */
	public List<TBussEntity> getTBussEntitysByTPernId(final SearchFilter<TBussEntity> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TBussEntity tBussEntity = searchFilter.getEntityClass();
        final int  entityId = tBussEntity.getEntityId();
        
        List<Object> entityIdList = new ArrayList<Object>();
        entityIdList.add(entityId);
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("GetTBussEntitysByPrnId",entityIdList , index, maxresult);
	}
	
	/**
	 * Find t buss entitys byt buss obj.
	 *
	 * @param tBussObj the t buss obj
	 * @return the list
	 */
	public List<TBussEntity> findTBussEntitysBytBussObj(TBussObj tBussObj){
		List<Object> tBussObjList = new ArrayList<Object>();
		tBussObjList.add(tBussObj);
		return genericDAO.findEntitiesByNamedQuery("FindTBussEntityByTBussObj", tBussObjList);
	}
	/**
	 * Find all t buss entitys by drv flag.
	 *
	 * @return the list
	 */
	@Override
	public List<TBussEntity> findAllTBussEntitysByDrvFlag() {
		return genericDAO.findEntitiesByNamedQuery("FindAllTBussEntitysByDrvFlag");
	}
	/**
	 * Find all t buss entitys by t buss obj.
	 *
	 * @param tBussObj the t buss obj
	 * @return the list
	 */
	@Override
	public List<TBussEntity> findAllTBussEntitysByTBussObj(TBussObj tBussObj) {
		List<Object> tBussObjList = new ArrayList<Object>();
		tBussObjList.add(tBussObj);
		return genericDAO.findEntitiesByNamedQuery("FindTBussEntityByTBussObj",tBussObjList);
	}
	/**
	 * Find buss entities by tenant id.
	 *
	 * @param tenantId the tenant id
	 * @return the list
	 */
	@Override
	public List<TBussEntity> findBussEntitiesByTenantId(Short tenantId) {
		List list = new ArrayList<>();
		list.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindAllTBussEntitysByTenantId", list,0,-1);//FindAllTBussFuncByTenantId", list,0,-1);
	}
	/**
	 * Find buss entities by tenant id.
	 *
	 * @param tenantId the tenant id
	 * @return the list
	 */
	@Override
	public List<TBussEntity> findTBussEntitiesByTenantId(Short tenantId) {
		List list = new ArrayList<>();
		list.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindAllTBussEntitysByTenantId", list,0,-1);
	}
	/**
	 * Find t buss entities by entity name.
	 *
	 * @param entityName the entity name
	 * @param tenantId the tenant id
	 * @return the list
	 */
	@Override
	public List<TBussEntity> findTBussEntitiesByEntityName(String entityName, Short tenantId) {
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(entityName);
		paramList.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindAllTBussEntitysByEntityName", paramList, 0, -1);
	}

	@Override
	public List<TBussEntity> findTBussEntitiesByBussObjType(String bussObjectName, Short tenantId, String bussFunctionType) {
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(bussObjectName);
		paramList.add(tenantId);
		paramList.add(bussFunctionType);
		return genericDAO.findEntitiesByNamedQuery("FindAllTBussEntitysByBussFuncType", paramList);
	}

	@SuppressWarnings("unchecked")
	@Override
	public TBussEntity getTBussEntityByBussObjId(TBussObj tBussObj, Short tenantId) {
		List paramList = new ArrayList();
		paramList.add(tBussObj);
		paramList.add(tenantId);
		
		
		List<TBussEntity> tBussEntityList = genericDAO
				.findEntitiesByNamedQueryMultiCond(
						"FindTBussEntityByTBussObjWithBaseFlag", paramList, 0, -1);
		if (tBussEntityList != null
				&& !(tBussEntityList.isEmpty()))
			return tBussEntityList.get(0);
		else
			return null;
	}

	@Override
	public List<String> getTBussEntityNameByEntityType(String bussFunctionType, Short tenantId) {
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(bussFunctionType);
		paramList.add(tenantId);
		return genericDAO.findEntitiesByNamedQuery("FindTBussEntityNameByBussFuncType", paramList);
	}
}
