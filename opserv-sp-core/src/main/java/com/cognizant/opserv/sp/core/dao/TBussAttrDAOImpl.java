package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TBussAttr;
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
 * Class provides API implementation for TBussAttrDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tBussAttrDAO")
public class TBussAttrDAOImpl implements TBussAttrDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TBussAttrDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private static final String TBUSSENTITY = "tBussEntity";
	private static final String TBUSSOBJ = "tBussObj";

	private final Class<TBussAttr> clazz;

	public Class<TBussAttr> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TBussAttrDAOImpl() {
		super();
		this.clazz = TBussAttr.class;
	}

	private static final String JPAQL = "select tBussAttrentity from TBussAttr tBussAttrentity";

	private static final String JPACOUNTQL = "select count(tBussAttrentity) from TBussAttr tBussAttrentity";

	private static final String[] RESTRICTIONS = { "tBussAttrentity.attrId like '#{tBussAttrentity.getAttrId}%'",
			"tBussAttrentity.attrName like '#{tBussAttrentity.getAttrName}%'",
			"tBussAttrentity.attrAliasName like '#{tBussAttrentity.getAttrAliasName}%'",
			"tBussAttrentity.attrDataType like '#{tBussAttrentity.getAttrDataType}%'",
			"tBussAttrentity.createdBy = #{tBussAttrentity.getCreatedBy}",
			"Date(tBussAttrentity.createDt) = '#{tBussAttrentity.getCreateDt}'",
			"tBussAttrentity.updatedBy = #{tBussAttrentity.getUpdatedBy}",
			"Date(tBussAttrentity.updateDt) = '#{tBussAttrentity.getUpdateDt}'",
			"tBussAttrentity.tenantId = #{tBussAttrentity.getTenantId}",
			"tBussAttrentity.prnObjName like '#{tBussAttrentity.getPrnObjName}%'",
			"tBussAttrentity.tBussEntity.entityId = #{tBussAttrentity.tBussEntity.getEntityId}",
			"tBussAttrentity.tBussObj.bussObjId = #{tBussAttrentity.tBussObj.getBussObjId}" };

	/**
	 * Stores a new TBussAttr entity object in to the persistent store
	 * 
	 * @param tBussAttr
	 *            TBussAttr Entity object to be persisted
	 * @return tBussAttr Persisted TBussAttr object
	 */
	public TBussAttr createTBussAttr(final TBussAttr tBussAttr) {
		LOGGER.info("=========== Create TBussAttr ===========");
		return genericDAO.store(tBussAttr);
	}

	/**
	 * Deletes a TBussAttr entity object from the persistent store
	 * 
	 * @param tBussAttr
	 *            TBussAttr Entity object to be deleted
	 */
	public void deleteTBussAttr(final String attrId) {
		LOGGER.info("=========== Delete TBussAttr ===========");
		final TBussAttr tBussAttr = genericDAO.get(clazz, attrId);
		genericDAO.remove(tBussAttr);
	}

	/**
	 * Updates a TBussAttr entity object in to the persistent store
	 * 
	 * @param tBussAttr
	 *            TBussAttr Entity object to be updated
	 * @return tBussAttr Persisted TBussAttr object
	 */
	public TBussAttr updateTBussAttr(final TBussAttr tBussAttr) {
		LOGGER.info("=========== Update TBussAttr ===========");
		return genericDAO.update(tBussAttr);
	}

	/**
	 * Retrieve an TBussAttr object based on given TBussAttr attrId.
	 * 
	 * @param tBussAttrId
	 *            the primary key value of the TBussAttr Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TBussAttr findTBussAttrById(final String tBussAttrId) {
		LOGGER.info("find TBussAttr instance with attrId: " + tBussAttrId);
		return genericDAO.get(clazz, tBussAttrId);
	}

	/**
	 * Retrieve TBussAttr based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TBussAttr> list of TBussAttr if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TBussAttr> findTBussAttrs(final SearchFilter<TBussAttr> searchFilter) {
		LOGGER.info("=========== Find TBussAttrs ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TBussAttr tBussAttr = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tBussAttrentity", tBussAttr);

		if (tBussAttr.getTBussEntity() == null) {
			jpaQuery.bind(TBUSSENTITY, new TBussEntity());
		} else {
			LOGGER.info("tBussEntity {}" + tBussAttr.getTBussEntity());

			jpaQuery.bind(TBUSSENTITY, tBussAttr.getTBussEntity());
		}

		if (tBussAttr.getTBussObj() == null) {
			jpaQuery.bind(TBUSSOBJ, new TBussObj());
		} else {
			LOGGER.info("tBussObj {}" + tBussAttr.getTBussObj());

			jpaQuery.bind(TBUSSOBJ, tBussAttr.getTBussObj());
		}
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TBussAttrs.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTBussAttrs(final SearchFilter<TBussAttr> searchFilter) {
		LOGGER.info("=========== Count TBussAttr ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TBussAttr tBussAttr = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tBussAttrentity", tBussAttr);

		if (tBussAttr.getTBussEntity() == null) {
			jpaCountQuery.bind(TBUSSENTITY, new TBussEntity());
		} else {
			LOGGER.info("tBussEntity {}" + tBussAttr.getTBussEntity());

			jpaCountQuery.bind(TBUSSENTITY, tBussAttr.getTBussEntity());
		}

		if (tBussAttr.getTBussObj() == null) {
			jpaCountQuery.bind(TBUSSOBJ, new TBussObj());
		} else {
			LOGGER.info("tBussObj {}" + tBussAttr.getTBussObj());

			jpaCountQuery.bind(TBUSSOBJ, tBussAttr.getTBussObj());
		}

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TBussAttr based on given search criteria using JPA named Query.
	 * The search criteria is of TBussEntity type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TBussAttr> list of TBussAttrs if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TBussAttr> getTBussAttrsByTBussEntity(final SearchFilter<TBussEntity> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		List<Object> tBussEntityList = new ArrayList<Object>();
		tBussEntityList.add(searchFilter.getEntityClass());
		int maxresult = 0;
		int index = -1;
		if (null != paginationInfo) {
			maxresult = paginationInfo.getMaxRows();
			index = paginationInfo.getStartIndex();
		}
		

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTBussAttrByTBussEntity", tBussEntityList, index, maxresult);
	}

	/**
	 * Count TBussAttr based on given search criteria using JPA named Query.
	 * The search criteria is of TBussEntity type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTBussAttrsByTBussEntity(final SearchFilter<TBussEntity> searchFilter) {

		final TBussEntity tBussEntity = searchFilter.getEntityClass();
		List<Object> tBussEntityList = new ArrayList<Object>();
		tBussEntityList.add(searchFilter.getEntityClass());
		return genericDAO.findEntitiesByNamedQuery("CountTBussAttrsByTBussEntity", tBussEntityList);
	}

	/**
	 * Retrieve TBussAttr based on given search criteria using JPA named Query.
	 * The search criteria is of TBussObj type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TBussAttr> list of TBussAttrs if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TBussAttr> getTBussAttrsByTBussObj(final SearchFilter<TBussObj> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TBussObj tBussObj = searchFilter.getEntityClass();
		List<Object> tBussObjList = new ArrayList<Object>();
		tBussObjList.add(searchFilter.getEntityClass());
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTBussAttrByTBussObj", tBussObjList, index, maxresult);
	}

	/**
	 * Count TBussAttr based on given search criteria using JPA named Query.
	 * The search criteria is of TBussObj type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTBussAttrsByTBussObj(final SearchFilter<TBussObj> searchFilter) {

		final TBussObj tBussObj = searchFilter.getEntityClass();
		List<Object> tBussObjList = new ArrayList<Object>();
		tBussObjList.add(searchFilter.getEntityClass());
		return genericDAO.findEntitiesByNamedQuery("CountTBussAttrsByTBussObj", tBussObjList);
	}

	@Override
	public List<TBussAttr> getTBussAttrsByTBussEntity(TBussEntity bussEntity) {
		LOGGER.info("Inside getTBussAttrsByTBussEntity and TBussEntity :: "+bussEntity);
		List<Object> tBussObjList = new ArrayList<Object>();
		tBussObjList.add(bussEntity);
		return genericDAO.findEntitiesByNamedQuery("FindTBussAttrByTBussEntity", tBussObjList);
	}

}
