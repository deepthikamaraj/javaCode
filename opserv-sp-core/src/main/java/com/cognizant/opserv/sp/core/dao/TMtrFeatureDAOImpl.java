package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TMtrFeature;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TMtrFeatureDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tMtrFeatureDAO")
public class TMtrFeatureDAOImpl implements TMtrFeatureDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(TMtrFeatureDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private final Class<TMtrFeature> clazz;

	public Class<TMtrFeature> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TMtrFeatureDAOImpl() {
		super();
		this.clazz = TMtrFeature.class;
	}

	private static final String JPAQL = "select tMtrFeatureentity from TMtrFeature tMtrFeatureentity";

	private static final String JPACOUNTQL = "select count(tMtrFeatureentity) from TMtrFeature tMtrFeatureentity";

	private static final String[] RESTRICTIONS = { "tMtrFeatureentity.featureId = #{tMtrFeatureentity.getFeatureId}",
			"tMtrFeatureentity.featureName like '#{tMtrFeatureentity.getFeatureName}%'",
			"tMtrFeatureentity.featureDesc like '#{tMtrFeatureentity.getFeatureDesc}%'",
			"tMtrFeatureentity.activeFlag = #{tMtrFeatureentity.getActiveFlag}",
			"tMtrFeatureentity.createdBy = #{tMtrFeatureentity.getCreatedBy}",
			"Date(tMtrFeatureentity.createDt) = '#{tMtrFeatureentity.getCreateDt}'",
			"tMtrFeatureentity.updatedBy = #{tMtrFeatureentity.getUpdatedBy}",
			"Date(tMtrFeatureentity.updateDt) = '#{tMtrFeatureentity.getUpdateDt}'",
			"tMtrFeatureentity.modName like '#{tMtrFeatureentity.getModName}%'",
			"tMtrFeatureentity.modDesc like '#{tMtrFeatureentity.getModDesc}%'" };

	/**
	 * Stores a new TMtrFeature entity object in to the persistent store
	 * 
	 * @param tMtrFeature
	 *            TMtrFeature Entity object to be persisted
	 * @return tMtrFeature Persisted TMtrFeature object
	 */
	public TMtrFeature createTMtrFeature(final TMtrFeature tMtrFeature) {
		LOGGER.info("=========== Create TMtrFeature ===========");
		return genericDAO.store(tMtrFeature);
	}

	/**
	 * Deletes a TMtrFeature entity object from the persistent store
	 * 
	 * @param tMtrFeature
	 *            TMtrFeature Entity object to be deleted
	 */
	public void deleteTMtrFeature(final Integer featureId) {
		LOGGER.info("=========== Delete TMtrFeature ===========");
		final TMtrFeature tMtrFeature = genericDAO.get(clazz, featureId);
		genericDAO.remove(tMtrFeature);
	}

	/**
	 * Updates a TMtrFeature entity object in to the persistent store
	 * 
	 * @param tMtrFeature
	 *            TMtrFeature Entity object to be updated
	 * @return tMtrFeature Persisted TMtrFeature object
	 */
	public TMtrFeature updateTMtrFeature(final TMtrFeature tMtrFeature) {
		LOGGER.info("=========== Update TMtrFeature ===========");
		return genericDAO.update(tMtrFeature);
	}

	/**
	 * Retrieve an TMtrFeature object based on given TMtrFeature featureId.
	 * 
	 * @param tMtrFeatureId
	 *            the primary key value of the TMtrFeature Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TMtrFeature findTMtrFeatureById(final Integer tMtrFeatureId) {
		LOGGER.info("find TMtrFeature instance with featureId: " + tMtrFeatureId);
		return genericDAO.get(clazz, tMtrFeatureId);
	}

	/**
	 * Retrieve TMtrFeature based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TMtrFeature> list of TMtrFeature if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TMtrFeature> findTMtrFeatures(final SearchFilter<TMtrFeature> searchFilter) {
		LOGGER.info("=========== Find TMtrFeatures ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TMtrFeature tMtrFeature = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tMtrFeatureentity", tMtrFeature);
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TMtrFeatures.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTMtrFeatures(final SearchFilter<TMtrFeature> searchFilter) {
		LOGGER.info("=========== Count TMtrFeature ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TMtrFeature tMtrFeature = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tMtrFeatureentity", tMtrFeature);

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

}
