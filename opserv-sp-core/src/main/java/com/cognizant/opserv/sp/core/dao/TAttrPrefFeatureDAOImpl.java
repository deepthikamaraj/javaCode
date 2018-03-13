package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TAttrPrefFeature;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TAttrPrefFeatureDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tAttrPrefFeatureDAO")
public class TAttrPrefFeatureDAOImpl implements TAttrPrefFeatureDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(TAttrPrefFeatureDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private final Class<TAttrPrefFeature> clazz;

	public Class<TAttrPrefFeature> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TAttrPrefFeatureDAOImpl() {
		super();
		this.clazz = TAttrPrefFeature.class;
	}

	private static final String JPAQL = "select tAttrPrefFeatureentity from TAttrPrefFeature tAttrPrefFeatureentity";

	private static final String JPACOUNTQL = "select count(tAttrPrefFeatureentity) from TAttrPrefFeature tAttrPrefFeatureentity";

	private static final String[] RESTRICTIONS = {
			"tAttrPrefFeatureentity.featureId = #{tAttrPrefFeatureentity.getFeatureId}",
			"tAttrPrefFeatureentity.featureName like '#{tAttrPrefFeatureentity.getFeatureName}%'",
			"tAttrPrefFeatureentity.featureDesc like '#{tAttrPrefFeatureentity.getFeatureDesc}%'",
			"tAttrPrefFeatureentity.activeFlag = #{tAttrPrefFeatureentity.getActiveFlag}",
			"tAttrPrefFeatureentity.createdBy = #{tAttrPrefFeatureentity.getCreatedBy}",
			"Date(tAttrPrefFeatureentity.createDt) = '#{tAttrPrefFeatureentity.getCreateDt}'",
			"tAttrPrefFeatureentity.updatedBy = #{tAttrPrefFeatureentity.getUpdatedBy}",
			"Date(tAttrPrefFeatureentity.updateDt) = '#{tAttrPrefFeatureentity.getUpdateDt}'",
			"tAttrPrefFeatureentity.modName like '#{tAttrPrefFeatureentity.getModName}%'",
			"tAttrPrefFeatureentity.modDesc like '#{tAttrPrefFeatureentity.getModDesc}%'" };

	/**
	 * Stores a new TAttrPrefFeature entity object in to the persistent store
	 * 
	 * @param tAttrPrefFeature
	 *            TAttrPrefFeature Entity object to be persisted
	 * @return tAttrPrefFeature Persisted TAttrPrefFeature object
	 */
	public TAttrPrefFeature createTAttrPrefFeature(final TAttrPrefFeature tAttrPrefFeature) {
		LOGGER.info("=========== Create TAttrPrefFeature ===========");
		return genericDAO.store(tAttrPrefFeature);
	}

	/**
	 * Deletes a TAttrPrefFeature entity object from the persistent store
	 * 
	 * @param tAttrPrefFeature
	 *            TAttrPrefFeature Entity object to be deleted
	 */
	public void deleteTAttrPrefFeature(final Integer featureId) {
		LOGGER.info("=========== Delete TAttrPrefFeature ===========");
		final TAttrPrefFeature tAttrPrefFeature = genericDAO.get(clazz, featureId);
		genericDAO.remove(tAttrPrefFeature);
	}

	/**
	 * Updates a TAttrPrefFeature entity object in to the persistent store
	 * 
	 * @param tAttrPrefFeature
	 *            TAttrPrefFeature Entity object to be updated
	 * @return tAttrPrefFeature Persisted TAttrPrefFeature object
	 */
	public TAttrPrefFeature updateTAttrPrefFeature(final TAttrPrefFeature tAttrPrefFeature) {
		LOGGER.info("=========== Update TAttrPrefFeature ===========");
		return genericDAO.update(tAttrPrefFeature);
	}

	/**
	 * Retrieve an TAttrPrefFeature object based on given TAttrPrefFeature featureId.
	 * 
	 * @param tAttrPrefFeatureId
	 *            the primary key value of the TAttrPrefFeature Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TAttrPrefFeature findTAttrPrefFeatureById(final Integer tAttrPrefFeatureId) {
		LOGGER.info("find TAttrPrefFeature instance with featureId: " + tAttrPrefFeatureId);
		return genericDAO.get(clazz, tAttrPrefFeatureId);
	}

	/**
	 * Retrieve TAttrPrefFeature based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TAttrPrefFeature> list of TAttrPrefFeature if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TAttrPrefFeature> findTAttrPrefFeatures(final SearchFilter<TAttrPrefFeature> searchFilter) {
		LOGGER.info("=========== Find TAttrPrefFeatures ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TAttrPrefFeature tAttrPrefFeature = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tAttrPrefFeatureentity", tAttrPrefFeature);
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TAttrPrefFeatures.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTAttrPrefFeatures(final SearchFilter<TAttrPrefFeature> searchFilter) {
		LOGGER.info("=========== Count TAttrPrefFeature ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TAttrPrefFeature tAttrPrefFeature = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tAttrPrefFeatureentity", tAttrPrefFeature);

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

}
