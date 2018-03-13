package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TFeatureType;
import com.cognizant.opserv.sp.core.entity.TMtrTrigger;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TMtrTriggerDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tMtrTriggerDAO")
public class TMtrTriggerDAOImpl implements TMtrTriggerDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TMtrTriggerDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private static final String TFEATURETYPE = "tFeatureType";
	/*private static final String TFEATURE = "tFeature";*/

	private final Class<TMtrTrigger> clazz;

	public Class<TMtrTrigger> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TMtrTriggerDAOImpl() {
		super();
		this.clazz = TMtrTrigger.class;
	}

	private static final String JPAQL = "select tMtrTriggerentity from TMtrTrigger tMtrTriggerentity";

	private static final String JPACOUNTQL = "select count(tMtrTriggerentity) from TMtrTrigger tMtrTriggerentity";

	private static final String[] RESTRICTIONS = { "tMtrTriggerentity.triggerId = #{tMtrTriggerentity.getTriggerId}",
			"tMtrTriggerentity.activeFlag = #{tMtrTriggerentity.getActiveFlag}",
			"tMtrTriggerentity.createdBy = #{tMtrTriggerentity.getCreatedBy}",
			"Date(tMtrTriggerentity.createDt) = '#{tMtrTriggerentity.getCreateDt}'",
			"tMtrTriggerentity.updatedBy = #{tMtrTriggerentity.getUpdatedBy}",
			"Date(tMtrTriggerentity.updateDt) = '#{tMtrTriggerentity.getUpdateDt}'",
			"tMtrTriggerentity.tenantId = #{tMtrTriggerentity.getTenantId}",
			"tMtrTriggerentity.tFeatureType.typeId = #{tMtrTriggerentity.tFeatureType.getTypeId}",
			"tMtrTriggerentity.featureId = #{tMtrTriggerentity.getFeatureId}" };

	/**
	 * Stores a new TMtrTrigger entity object in to the persistent store
	 * 
	 * @param tMtrTrigger
	 *            TMtrTrigger Entity object to be persisted
	 * @return tMtrTrigger Persisted TMtrTrigger object
	 */
	public TMtrTrigger createTMtrTrigger(final TMtrTrigger tMtrTrigger) {
		LOGGER.info("=========== Create TMtrTrigger ===========");
		return genericDAO.store(tMtrTrigger);
	}

	/**
	 * Deletes a TMtrTrigger entity object from the persistent store
	 * 
	 * @param tMtrTrigger
	 *            TMtrTrigger Entity object to be deleted
	 */
	public void deleteTMtrTrigger(final Integer triggerId) {
		LOGGER.info("=========== Delete TMtrTrigger ===========");
		final TMtrTrigger tMtrTrigger = genericDAO.get(clazz, triggerId);
		genericDAO.remove(tMtrTrigger);
	}

	/**
	 * Updates a TMtrTrigger entity object in to the persistent store
	 * 
	 * @param tMtrTrigger
	 *            TMtrTrigger Entity object to be updated
	 * @return tMtrTrigger Persisted TMtrTrigger object
	 */
	public TMtrTrigger updateTMtrTrigger(final TMtrTrigger tMtrTrigger) {
		LOGGER.info("=========== Update TMtrTrigger ===========");
		return genericDAO.update(tMtrTrigger);
	}

	/**
	 * Retrieve an TMtrTrigger object based on given TMtrTrigger triggerId.
	 * 
	 * @param tMtrTriggerId
	 *            the primary key value of the TMtrTrigger Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TMtrTrigger findTMtrTriggerById(final Integer tMtrTriggerId) {
		LOGGER.info("find TMtrTrigger instance with triggerId: " + tMtrTriggerId);
		return genericDAO.get(clazz, tMtrTriggerId);
	}

	/**
	 * Retrieve TMtrTrigger based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TMtrTrigger> list of TMtrTrigger if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TMtrTrigger> findTMtrTriggers(final SearchFilter<TMtrTrigger> searchFilter) {
		LOGGER.info("=========== Find TMtrTriggers ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TMtrTrigger tMtrTrigger = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tMtrTriggerentity", tMtrTrigger);

		if (tMtrTrigger.getTFeatureType() == null) {
			jpaQuery.bind(TFEATURETYPE, new TFeatureType());
		} else {
			LOGGER.info("tFeatureType {}"+ tMtrTrigger.getTFeatureType());

			jpaQuery.bind(TFEATURETYPE, tMtrTrigger.getTFeatureType());
		}

		/*if (tMtrTrigger.getTFeature() == null) {
			jpaQuery.bind(TFEATURE, new TFeature());
		} else {
			LOGGER.info("tFeature {}", tMtrTrigger.getTFeature());

			jpaQuery.bind(TFEATURE, tMtrTrigger.getTFeature());
		}*/
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TMtrTriggers.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTMtrTriggers(final SearchFilter<TMtrTrigger> searchFilter) {
		LOGGER.info("=========== Count TMtrTrigger ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TMtrTrigger tMtrTrigger = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tMtrTriggerentity", tMtrTrigger);

		if (tMtrTrigger.getTFeatureType() == null) {
			jpaCountQuery.bind(TFEATURETYPE, new TFeatureType());
		} else {
			LOGGER.info("tFeatureType {}"+ tMtrTrigger.getTFeatureType());

			jpaCountQuery.bind(TFEATURETYPE, tMtrTrigger.getTFeatureType());
		}

	/*	if (tMtrTrigger.getTFeature() == null) {
			jpaCountQuery.bind(TFEATURE, new TFeature());
		} else {
			LOGGER.info("tFeature {}", tMtrTrigger.getTFeature());

			jpaCountQuery.bind(TFEATURE, tMtrTrigger.getTFeature());
		}*/

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TMtrTrigger based on given search criteria using JPA named Query.
	 * The search criteria is of TFeatureType type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TMtrTrigger> list of TMtrTriggers if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TMtrTrigger> getTMtrTriggersByTFeatureType(final SearchFilter<TFeatureType> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TFeatureType tFeatureType = searchFilter.getEntityClass();
		List<Object> tFeatureTypeList = new ArrayList<Object>();
		tFeatureTypeList.add(tFeatureType);
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTMtrTriggerByTFeatureType", tFeatureTypeList, index, maxresult);
	}

	/**
	 * Count TMtrTrigger based on given search criteria using JPA named Query.
	 * The search criteria is of TFeatureType type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTMtrTriggersByTFeatureType(final SearchFilter<TFeatureType> searchFilter) {

		final TFeatureType tFeatureType = searchFilter.getEntityClass();
		List<Object> tFeatureTypeList = new ArrayList<Object>();
		tFeatureTypeList.add(tFeatureType);
		return genericDAO.findEntitiesByNamedQuery("CountTMtrTriggersByTFeatureType", tFeatureTypeList);
	}

	/**
	 * Retrieve TMtrTrigger based on given search criteria using JPA named Query.
	 * The search criteria is of TFeature type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TMtrTrigger> list of TMtrTriggers if it exists against given
	 *         criteria. Returns null if not found
	 */
	/*public List<TMtrTrigger> getTMtrTriggersByTFeature(final SearchFilter<TFeature> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TFeature tFeature = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQuery("FindTMtrTriggerByTFeature", tFeature, index, maxresult);
	}*/

	/**
	 * Count TMtrTrigger based on given search criteria using JPA named Query.
	 * The search criteria is of TFeature type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	/*public Object countTMtrTriggersByTFeature(final SearchFilter<TFeature> searchFilter) {

		final TFeature tFeature = searchFilter.getEntityClass();
		return genericDAO.findEntitiesByNamedQuery("CountTMtrTriggersByTFeature", tFeature);
	}*/

	@Override
	public List<Object> getTMtrTriggerByTFeatureAndTFeatureType(
			Integer featureTypeId, Integer featureId,Short tenantId) {
		List queryParam = new ArrayList();
		queryParam.add(featureTypeId);
		queryParam.add(featureId);
		queryParam.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("findMetricTriggerByFeatures", queryParam, 0, -1);
	}
	
	
	public List<TMtrTrigger> findAllTMtrTriggers(Short tenantId){
		List<Object> tenantIdList = new ArrayList<Object>();
		tenantIdList.add(tenantId);
		return genericDAO.findEntitiesByNamedQuery("FindAllTMtrTriggersWithActiveFlag",tenantIdList);
	}

	@Override
	public List<Object[]> getAllTMtrTrigger(Short tenantId){
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindAllTMtrTrigger", paramList, 0, -1);
	}
}
