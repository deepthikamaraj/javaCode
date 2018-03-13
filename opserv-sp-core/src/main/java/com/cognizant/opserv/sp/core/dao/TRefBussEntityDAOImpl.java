package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TBussEntity;
import com.cognizant.opserv.sp.core.entity.TRefBussEntity;
import com.cognizant.opserv.sp.core.entity.TRefBussEntityId;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TRefBussEntityDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tRefBussEntityDAO")
public class TRefBussEntityDAOImpl implements TRefBussEntityDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TRefBussEntityDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private static final String TBUSSENTITYBYENTITYID = "tBussEntityByEntityId";
	private static final String TBUSSENTITYBYPRNENTITYID = "tBussEntityByPrnEntityId";

	private final Class<TRefBussEntity> clazz;

	public Class<TRefBussEntity> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TRefBussEntityDAOImpl() {
		super();
		this.clazz = TRefBussEntity.class;
	}

	private static final String JPAQL = "select tRefBussEntityentity from TRefBussEntity tRefBussEntityentity";

	private static final String JPACOUNTQL = "select count(*) from TRefBussEntity tRefBussEntityentity";

	private static final String[] RESTRICTIONS = {
			"tRefBussEntityentity.tRefBussEntityId.entityId = #{tRefBussEntityentity.tRefBussEntityId.getEntityId}",
			"tRefBussEntityentity.tRefBussEntityId.prnEntityId = #{tRefBussEntityentity.tRefBussEntityId.getPrnEntityId}",
			"tRefBussEntityentity.createdBy = #{tRefBussEntityentity.getCreatedBy}",
			"Date(tRefBussEntityentity.createDt) = '#{tRefBussEntityentity.getCreateDt}'",
			"tRefBussEntityentity.updatedBy = #{tRefBussEntityentity.getUpdatedBy}",
			"Date(tRefBussEntityentity.updateDt) = '#{tRefBussEntityentity.getUpdateDt}'",
			"tRefBussEntityentity.tenantId = #{tRefBussEntityentity.getTenantId}",
			"tRefBussEntityentity.tBussEntityByEntityId.entityId = #{tRefBussEntityentity.tBussEntityByEntityId.getEntityId}",
			"tRefBussEntityentity.tBussEntityByPrnEntityId.entityId = #{tRefBussEntityentity.tBussEntityByPrnEntityId.getEntityId}" };

	/**
	 * Stores a new TRefBussEntity entity object in to the persistent store
	 * 
	 * @param tRefBussEntity
	 *            TRefBussEntity Entity object to be persisted
	 * @return tRefBussEntity Persisted TRefBussEntity object
	 */
	public TRefBussEntity createTRefBussEntity(final TRefBussEntity tRefBussEntity) {
		LOGGER.info("=========== Create TRefBussEntity ===========");
		return genericDAO.store(tRefBussEntity);
	}

	/**
	 * Deletes a TRefBussEntity entity object from the persistent store
	 * 
	 * @param tRefBussEntity
	 *            TRefBussEntity Entity object to be deleted
	 */
	public void deleteTRefBussEntity(final TRefBussEntityId tRefBussEntityId) {
		LOGGER.info("=========== Delete TRefBussEntity ===========");
		final TRefBussEntity tRefBussEntity = genericDAO.get(clazz, tRefBussEntityId);
		genericDAO.remove(tRefBussEntity);
	}

	/**
	 * Updates a TRefBussEntity entity object in to the persistent store
	 * 
	 * @param tRefBussEntity
	 *            TRefBussEntity Entity object to be updated
	 * @return tRefBussEntity Persisted TRefBussEntity object
	 */
	public TRefBussEntity updateTRefBussEntity(final TRefBussEntity tRefBussEntity) {
		LOGGER.info("=========== Update TRefBussEntity ===========");
		return genericDAO.update(tRefBussEntity);
	}

	/**
	 * Retrieve an TRefBussEntity object based on given TRefBussEntity TRefBussEntityId.
	 * 
	 * @param tRefBussEntityId
	 *            the primary key value of the TRefBussEntity Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TRefBussEntity findTRefBussEntityById(final TRefBussEntityId tRefBussEntityId) {
		LOGGER.info("find TRefBussEntity instance with TRefBussEntityId: " + tRefBussEntityId);
		return genericDAO.get(clazz, tRefBussEntityId);
	}

	/**
	 * Retrieve TRefBussEntity based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRefBussEntity> list of TRefBussEntity if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TRefBussEntity> findTRefBussEntitys(final SearchFilter<TRefBussEntity> searchFilter) {
		LOGGER.info("=========== Find TRefBussEntitys ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TRefBussEntity tRefBussEntity = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tRefBussEntityentity", tRefBussEntity);

		if (tRefBussEntity.getTBussEntityByEntityId() == null) {
			jpaQuery.bind(TBUSSENTITYBYENTITYID, new TBussEntity());
		} else {
			LOGGER.info("tBussEntityByEntityId {}"+ tRefBussEntity.getTBussEntityByEntityId());

			jpaQuery.bind(TBUSSENTITYBYENTITYID, tRefBussEntity.getTBussEntityByEntityId());
		}

		if (tRefBussEntity.getTBussEntityByPrnEntityId() == null) {
			jpaQuery.bind(TBUSSENTITYBYPRNENTITYID, new TBussEntity());
		} else {
			LOGGER.info("tBussEntityByPrnEntityId {}"+ tRefBussEntity.getTBussEntityByPrnEntityId());

			jpaQuery.bind(TBUSSENTITYBYPRNENTITYID, tRefBussEntity.getTBussEntityByPrnEntityId());
		}
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TRefBussEntitys.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTRefBussEntitys(final SearchFilter<TRefBussEntity> searchFilter) {
		LOGGER.info("=========== Count TRefBussEntity ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TRefBussEntity tRefBussEntity = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tRefBussEntityentity", tRefBussEntity);

		if (tRefBussEntity.getTBussEntityByEntityId() == null) {
			jpaCountQuery.bind(TBUSSENTITYBYENTITYID, new TBussEntity());
		} else {
			LOGGER.info("tBussEntityByEntityId {}"+ tRefBussEntity.getTBussEntityByEntityId());

			jpaCountQuery.bind(TBUSSENTITYBYENTITYID, tRefBussEntity.getTBussEntityByEntityId());
		}

		if (tRefBussEntity.getTBussEntityByPrnEntityId() == null) {
			jpaCountQuery.bind(TBUSSENTITYBYPRNENTITYID, new TBussEntity());
		} else {
			LOGGER.info("tBussEntityByPrnEntityId {}"+ tRefBussEntity.getTBussEntityByPrnEntityId());

			jpaCountQuery.bind(TBUSSENTITYBYPRNENTITYID, tRefBussEntity.getTBussEntityByPrnEntityId());
		}

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TRefBussEntity based on given search criteria using JPA named Query.
	 * The search criteria is of TBussEntityByEntityId type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRefBussEntity> list of TRefBussEntitys if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TRefBussEntity> getTRefBussEntitysByTBussEntityByEntityId(final SearchFilter<TBussEntity> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());
		
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTRefBussEntityByTBussEntityByEntityId", queryParam, index,
				maxresult);
	}

	/**
	 * Count TRefBussEntity based on given search criteria using JPA named Query.
	 * The search criteria is of TBussEntity type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTRefBussEntitysByTBussEntityByEntityId(final SearchFilter<TBussEntity> searchFilter) {

		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());
		return genericDAO.findEntitiesByNamedQuery("CountTRefBussEntitysByTBussEntityByEntityId", queryParam);
	}

	/**
	 * Retrieve TRefBussEntity based on given search criteria using JPA named Query.
	 * The search criteria is of TBussEntityByPrnEntityId type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRefBussEntity> list of TRefBussEntitys if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TRefBussEntity> getTRefBussEntitysByTBussEntityByPrnEntityId(
			final SearchFilter<TBussEntity> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTRefBussEntityByTBussEntityByPrnEntityId", queryParam, index,
				maxresult);
	}

	/**
	 * Count TRefBussEntity based on given search criteria using JPA named Query.
	 * The search criteria is of TBussEntity type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTRefBussEntitysByTBussEntityByPrnEntityId(final SearchFilter<TBussEntity> searchFilter) {

		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());
		return genericDAO.findEntitiesByNamedQuery("CountTRefBussEntitysByTBussEntityByPrnEntityId", queryParam);
	}

	@Override
	public List<Integer> getTRefBussEntitysByEntityId(Integer entityId,Short tenantId) {
		List<Object>list = new ArrayList<Object>();
		list.add(entityId);
		list.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("getTRefBussEntitysByEntityId", list, 0, -1);
	}

}
