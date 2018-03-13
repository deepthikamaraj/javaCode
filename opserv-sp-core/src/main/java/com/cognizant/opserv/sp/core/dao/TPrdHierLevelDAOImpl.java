package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TPrdHierLevel;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TPrdHierLevelDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tPrdHierLevelDAO")
public class TPrdHierLevelDAOImpl implements TPrdHierLevelDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TPrdHierLevelDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private static final String TPRDHIERLEVEL = "tPrdHierLevel";

	private final Class<TPrdHierLevel> clazz;

	public Class<TPrdHierLevel> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TPrdHierLevelDAOImpl() {
		super();
		this.clazz = TPrdHierLevel.class;
	}

	private static final String JPAQL = "select tPrdHierLevelentity from TPrdHierLevel tPrdHierLevelentity";

	private static final String JPACOUNTQL = "select count(tPrdHierLevelentity) from TPrdHierLevel tPrdHierLevelentity";

	private static final String[] RESTRICTIONS = {
			"tPrdHierLevelentity.hierLevelId = #{tPrdHierLevelentity.getHierLevelId}",
			"tPrdHierLevelentity.hierLevelName like '#{tPrdHierLevelentity.getHierLevelName}%'",
			"tPrdHierLevelentity.hierLevelDesc like '#{tPrdHierLevelentity.getHierLevelDesc}%'",
			"tPrdHierLevelentity.activeFlag = #{tPrdHierLevelentity.getActiveFlag}",
			"Date(tPrdHierLevelentity.effStartDt) = '#{tPrdHierLevelentity.getEffStartDt}'",
			"Date(tPrdHierLevelentity.effEndDt) = '#{tPrdHierLevelentity.getEffEndDt}'",
			"tPrdHierLevelentity.createdBy = #{tPrdHierLevelentity.getCreatedBy}",
			"Date(tPrdHierLevelentity.createDt) = '#{tPrdHierLevelentity.getCreateDt}'",
			"tPrdHierLevelentity.updatedBy = #{tPrdHierLevelentity.getUpdatedBy}",
			"Date(tPrdHierLevelentity.updateDt) = '#{tPrdHierLevelentity.getUpdateDt}'",
			"tPrdHierLevelentity.tenantId = #{tPrdHierLevelentity.getTenantId}",
			"tPrdHierLevelentity.tPrdHierLevel.hierLevelId = #{tPrdHierLevelentity.tPrdHierLevel.getHierLevelId}" };

	/**
	 * Stores a new TPrdHierLevel entity object in to the persistent store
	 * 
	 * @param tPrdHierLevel
	 *            TPrdHierLevel Entity object to be persisted
	 * @return tPrdHierLevel Persisted TPrdHierLevel object
	 */
	public TPrdHierLevel createTPrdHierLevel(final TPrdHierLevel tPrdHierLevel) {
		LOGGER.info("=========== Create TPrdHierLevel ===========");
		return genericDAO.store(tPrdHierLevel);
	}

	/**
	 * Deletes a TPrdHierLevel entity object from the persistent store
	 * 
	 * @param tPrdHierLevel
	 *            TPrdHierLevel Entity object to be deleted
	 */
	public void deleteTPrdHierLevel(final Integer hierLevelId) {
		LOGGER.info("=========== Delete TPrdHierLevel ===========");
		final TPrdHierLevel tPrdHierLevel = genericDAO.get(clazz, hierLevelId);
		genericDAO.remove(tPrdHierLevel);
	}

	/**
	 * Updates a TPrdHierLevel entity object in to the persistent store
	 * 
	 * @param tPrdHierLevel
	 *            TPrdHierLevel Entity object to be updated
	 * @return tPrdHierLevel Persisted TPrdHierLevel object
	 */
	public TPrdHierLevel updateTPrdHierLevel(final TPrdHierLevel tPrdHierLevel) {
		LOGGER.info("=========== Update TPrdHierLevel ===========");
		return genericDAO.update(tPrdHierLevel);
	}

	/**
	 * Retrieve an TPrdHierLevel object based on given TPrdHierLevel hierLevelId.
	 * 
	 * @param tPrdHierLevelId
	 *            the primary key value of the TPrdHierLevel Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TPrdHierLevel findTPrdHierLevelById(final Integer tPrdHierLevelId) {
		LOGGER.info("find TPrdHierLevel instance with hierLevelId: " + tPrdHierLevelId);
		return genericDAO.get(clazz, tPrdHierLevelId);
	}

	/**
	 * Retrieve TPrdHierLevel based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TPrdHierLevel> list of TPrdHierLevel if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TPrdHierLevel> findTPrdHierLevels(final SearchFilter<TPrdHierLevel> searchFilter) throws Exception{
		LOGGER.info("=========== Find TPrdHierLevels ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TPrdHierLevel tPrdHierLevel = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tPrdHierLevelentity", tPrdHierLevel);

		if (tPrdHierLevel.getTPrdHierLevel() == null) {
			jpaQuery.bind(TPRDHIERLEVEL, new TPrdHierLevel());
		} else {
			LOGGER.info("tPrdHierLevel {}"+ tPrdHierLevel.getTPrdHierLevel());

			jpaQuery.bind(TPRDHIERLEVEL, tPrdHierLevel.getTPrdHierLevel());
		}
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TPrdHierLevels.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTPrdHierLevels(final SearchFilter<TPrdHierLevel> searchFilter) {
		LOGGER.info("=========== Count TPrdHierLevel ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TPrdHierLevel tPrdHierLevel = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tPrdHierLevelentity", tPrdHierLevel);

		if (tPrdHierLevel.getTPrdHierLevel() == null) {
			jpaCountQuery.bind(TPRDHIERLEVEL, new TPrdHierLevel());
		} else {
			LOGGER.info("tPrdHierLevel {}"+ tPrdHierLevel.getTPrdHierLevel());

			jpaCountQuery.bind(TPRDHIERLEVEL, tPrdHierLevel.getTPrdHierLevel());
		}

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TPrdHierLevel based on given search criteria using JPA named Query.
	 * The search criteria is of TPrdHierLevel type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TPrdHierLevel> list of TPrdHierLevels if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TPrdHierLevel> getTPrdHierLevelsByTPrdHierLevel(final SearchFilter<TPrdHierLevel> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTPrdHierLevelByTPrdHierLevel", queryParam, index, maxresult);
	}

	/**
	 * Count TPrdHierLevel based on given search criteria using JPA named Query.
	 * The search criteria is of TPrdHierLevel type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTPrdHierLevelsByTPrdHierLevel(final SearchFilter<TPrdHierLevel> searchFilter) {

		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());
		return genericDAO.findEntitiesByNamedQuery("CountTPrdHierLevelsByTPrdHierLevel", queryParam);
	}

	@Override
	public List<Object> findTPrdHierLevelByMaxOfHierLevelId(Short tenantId) {
		// TODO Auto-generated method stub
		List paramList = new ArrayList();
		paramList.add('Y');
		paramList.add(tenantId);
		int index=0;
		int maxresult=-1;
		List<Object> list=genericDAO.findEntitiesByNamedQueryMultiCond("FindTPrdHierLevelByMaxOfHierLevelId",paramList, index, maxresult);
		return list;
	}

	@Override
	public List<TPrdHierLevel> findAllTPrdHierLevels(Short tenantId) {
		List paramList = new ArrayList();
		paramList.add(tenantId);
		return genericDAO.findEntitiesByNamedQuery("FindAllTPrdHierLevels",paramList);
	}

	@Override
	public List<Integer> findMaxOfHierLevelId(Short tenantId) {
		List paramList = new ArrayList();
		paramList.add(tenantId);
		return genericDAO.findEntitiesByNamedQuery("FindMaxOfHierLevelId",paramList);
	}

}
