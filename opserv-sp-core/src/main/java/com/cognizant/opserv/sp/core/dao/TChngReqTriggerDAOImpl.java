package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TChngReqTrigger;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TChngReqTriggerDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tChngReqTriggerDAO")
public class TChngReqTriggerDAOImpl implements TChngReqTriggerDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TChngReqTriggerDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	/*private static final String TFEATURE = "tFeature";*/

	private final Class<TChngReqTrigger> clazz;

	public Class<TChngReqTrigger> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TChngReqTriggerDAOImpl() {
		super();
		this.clazz = TChngReqTrigger.class;
	}

	private static final String JPAQL = "select tChngReqTriggerentity from TChngReqTrigger tChngReqTriggerentity";

	private static final String JPACOUNTQL = "select count(tChngReqTriggerentity) from TChngReqTrigger tChngReqTriggerentity";

	private static final String[] RESTRICTIONS = {
			"tChngReqTriggerentity.triggerId = #{tChngReqTriggerentity.getTriggerId}",
			"tChngReqTriggerentity.typeId = #{tChngReqTriggerentity.getTypeId}",
			"tChngReqTriggerentity.activeFlag = #{tChngReqTriggerentity.getActiveFlag}",
			"tChngReqTriggerentity.nstChngReqFlag = #{tChngReqTriggerentity.getNstChngReqFlag}",
			"tChngReqTriggerentity.createdBy = #{tChngReqTriggerentity.getCreatedBy}",
			"Date(tChngReqTriggerentity.createDt) = '#{tChngReqTriggerentity.getCreateDt}'",
			"tChngReqTriggerentity.updatedBy = #{tChngReqTriggerentity.getUpdatedBy}",
			"Date(tChngReqTriggerentity.updateDt) = '#{tChngReqTriggerentity.getUpdateDt}'",
			"tChngReqTriggerentity.tenantId = #{tChngReqTriggerentity.getTenantId}",
			"tChngReqTriggerentity.featureId = #{tChngReqTriggerentity.getFeatureId}" };

	/**
	 * Stores a new TChngReqTrigger entity object in to the persistent store
	 * 
	 * @param tChngReqTrigger
	 *            TChngReqTrigger Entity object to be persisted
	 * @return tChngReqTrigger Persisted TChngReqTrigger object
	 */
	public TChngReqTrigger createTChngReqTrigger(
			final TChngReqTrigger tChngReqTrigger) {
		LOGGER.info("=========== Create TChngReqTrigger ===========");
		return genericDAO.store(tChngReqTrigger);
	}

	/**
	 * Deletes a TChngReqTrigger entity object from the persistent store
	 * 
	 * @param tChngReqTrigger
	 *            TChngReqTrigger Entity object to be deleted
	 */
	public void deleteTChngReqTrigger(final Integer triggerId) {
		LOGGER.info("=========== Delete TChngReqTrigger ===========");
		final TChngReqTrigger tChngReqTrigger = genericDAO
				.get(clazz, triggerId);
		genericDAO.remove(tChngReqTrigger);
	}

	/**
	 * Updates a TChngReqTrigger entity object in to the persistent store
	 * 
	 * @param tChngReqTrigger
	 *            TChngReqTrigger Entity object to be updated
	 * @return tChngReqTrigger Persisted TChngReqTrigger object
	 */
	public TChngReqTrigger updateTChngReqTrigger(
			final TChngReqTrigger tChngReqTrigger) {
		LOGGER.info("=========== Update TChngReqTrigger ===========");
		return genericDAO.update(tChngReqTrigger);
	}

	/**
	 * Retrieve an TChngReqTrigger object based on given TChngReqTrigger
	 * triggerId.
	 * 
	 * @param tChngReqTriggerId
	 *            the primary key value of the TChngReqTrigger Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TChngReqTrigger findTChngReqTriggerById(
			final Integer tChngReqTriggerId) {
		LOGGER.info("find TChngReqTrigger instance with triggerId: "
				+ tChngReqTriggerId);
		return genericDAO.get(clazz, tChngReqTriggerId);
	}

	/**
	 * Retrieve TChngReqTrigger based on given search criteria using Dynamic
	 * JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TChngReqTrigger> list of TChngReqTrigger if it exists
	 *         against given criteria. Returns null if not found
	 */
	public List<TChngReqTrigger> findTChngReqTriggers(
			final SearchFilter<TChngReqTrigger> searchFilter) {
		LOGGER.info("=========== Find TChngReqTriggers ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TChngReqTrigger tChngReqTrigger = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		// int maxresult = 3;
		// int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tChngReqTriggerentity",
				tChngReqTrigger);

		/*if (tChngReqTrigger.getTFeature() == null) {
			jpaQuery.bind(TFEATURE, new TFeature());
		} else {
			LOGGER.info("tFeature {}", tChngReqTrigger.getTFeature());

			jpaQuery.bind(TFEATURE, tChngReqTrigger.getTFeature());
		}*/
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TChngReqTriggers.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTChngReqTriggers(
			final SearchFilter<TChngReqTrigger> searchFilter) {
		LOGGER.info("=========== Count TChngReqTrigger ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TChngReqTrigger tChngReqTrigger = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tChngReqTriggerentity",
				tChngReqTrigger);

		/*if (tChngReqTrigger.getTFeature() == null) {
			jpaCountQuery.bind(TFEATURE, new TFeature());
		} else {
			LOGGER.info("tFeature {}", tChngReqTrigger.getTFeature());

			jpaCountQuery.bind(TFEATURE, tChngReqTrigger.getTFeature());
		}
*/
		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TChngReqTrigger based on given search criteria using JPA named
	 * Query. The search criteria is of TFeature type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TChngReqTrigger> list of TChngReqTriggers if it exists
	 *         against given criteria. Returns null if not found
	 */
/*	public List<TChngReqTrigger> getTChngReqTriggersByTFeature(
			final SearchFilter<TFeature> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TFeature tFeature = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQuery(
				"FindTChngReqTriggerByTFeature", tFeature, index, maxresult);
	}*/

	/**
	 * Count TChngReqTrigger based on given search criteria using JPA named
	 * Query. The search criteria is of TFeature type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	/*public Object countTChngReqTriggersByTFeature(
			final SearchFilter<TFeature> searchFilter) {

		final TFeature tFeature = searchFilter.getEntityClass();
		return genericDAO.findEntitiesByNamedQuery(
				"CountTChngReqTriggersByTFeature", tFeature);
	}*/
	/**
	 * Find feature type by trigger id.
	 *
	 * @param triggerId the trigger id
	 * @param tenantId the tenant id
	 * @return the list
	 */
	public List<Object[]> findFeatureTypeByTriggerId(Integer triggerId,
			Short tenantId) {
		List params = new ArrayList();
		params.add(triggerId);
		params.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond(
				"getfeatureTypeByTriggerId", params, 0, -1);
	}

	/*
	 * Added  By 407986 To fetch All the TChngReqTrigger
	 * */
	/**
	 * Fetch all triggers by tenants.
	 *
	 * @return the list
	 */
	public List<Object[]> fetchAllTriggersByTenants() {
		return genericDAO.findEntitiesByNamedQuery("FetchAllTriggersByTenants");
	}

	/*
	 * Added  By 407986 To fetch All the TChngReqTrigger
	 * */
	/**
	 * Fetch all triggers by tenants.
	 *
	 * @param tenantId the tenant id
	 * @return the list
	 */
	public List<Object[]> fetchAllTriggersByTenants(Short tenantId) {

		List<Object> params = new ArrayList<Object>();
		params.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond(
				"FetchAllTriggersByTenantId", params, 0, -1);
	}

}
