package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TChngReqFeature;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TChngReqFeatureDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tChngReqFeatureDAO")
public class TChngReqFeatureDAOImpl implements TChngReqFeatureDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(TChngReqFeatureDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private final Class<TChngReqFeature> clazz;

	public Class<TChngReqFeature> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TChngReqFeatureDAOImpl() {
		super();
		this.clazz = TChngReqFeature.class;
	}

	private static final String JPAQL = "select tChngReqFeatureentity from TChngReqFeature tChngReqFeatureentity";

	private static final String JPACOUNTQL = "select count(tChngReqFeatureentity) from TChngReqFeature tChngReqFeatureentity";

	private static final String[] RESTRICTIONS = {
			"tChngReqFeatureentity.featureId = #{tChngReqFeatureentity.getFeatureId}",
			"tChngReqFeatureentity.featureName like '#{tChngReqFeatureentity.getFeatureName}%'",
			"tChngReqFeatureentity.featureDesc like '#{tChngReqFeatureentity.getFeatureDesc}%'",
			"tChngReqFeatureentity.activeFlag = #{tChngReqFeatureentity.getActiveFlag}",
			"tChngReqFeatureentity.createdBy = #{tChngReqFeatureentity.getCreatedBy}",
			"Date(tChngReqFeatureentity.createDt) = '#{tChngReqFeatureentity.getCreateDt}'",
			"tChngReqFeatureentity.updatedBy = #{tChngReqFeatureentity.getUpdatedBy}",
			"Date(tChngReqFeatureentity.updateDt) = '#{tChngReqFeatureentity.getUpdateDt}'",
			"tChngReqFeatureentity.modName like '#{tChngReqFeatureentity.getModName}%'",
			"tChngReqFeatureentity.modDesc like '#{tChngReqFeatureentity.getModDesc}%'" };

	/**
	 * Stores a new TChngReqFeature entity object in to the persistent store
	 * 
	 * @param tChngReqFeature
	 *            TChngReqFeature Entity object to be persisted
	 * @return tChngReqFeature Persisted TChngReqFeature object
	 */
	public TChngReqFeature createTChngReqFeature(final TChngReqFeature tChngReqFeature) {
		LOGGER.info("=========== Create TChngReqFeature ===========");
		return genericDAO.store(tChngReqFeature);
	}

	/**
	 * Deletes a TChngReqFeature entity object from the persistent store
	 * 
	 * @param tChngReqFeature
	 *            TChngReqFeature Entity object to be deleted
	 */
	public void deleteTChngReqFeature(final Integer featureId) {
		LOGGER.info("=========== Delete TChngReqFeature ===========");
		final TChngReqFeature tChngReqFeature = genericDAO.get(clazz, featureId);
		genericDAO.remove(tChngReqFeature);
	}

	/**
	 * Updates a TChngReqFeature entity object in to the persistent store
	 * 
	 * @param tChngReqFeature
	 *            TChngReqFeature Entity object to be updated
	 * @return tChngReqFeature Persisted TChngReqFeature object
	 */
	public TChngReqFeature updateTChngReqFeature(final TChngReqFeature tChngReqFeature) {
		LOGGER.info("=========== Update TChngReqFeature ===========");
		return genericDAO.update(tChngReqFeature);
	}

	/**
	 * Retrieve an TChngReqFeature object based on given TChngReqFeature featureId.
	 * 
	 * @param tChngReqFeatureId
	 *            the primary key value of the TChngReqFeature Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TChngReqFeature findTChngReqFeatureById(final Integer tChngReqFeatureId) {
		LOGGER.info("find TChngReqFeature instance with featureId: " + tChngReqFeatureId);
		return genericDAO.get(clazz, tChngReqFeatureId);
	}

	/**
	 * Retrieve TChngReqFeature based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TChngReqFeature> list of TChngReqFeature if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TChngReqFeature> findTChngReqFeatures(final SearchFilter<TChngReqFeature> searchFilter) {
		LOGGER.info("=========== Find TChngReqFeatures ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TChngReqFeature tChngReqFeature = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tChngReqFeatureentity", tChngReqFeature);
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TChngReqFeatures.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTChngReqFeatures(final SearchFilter<TChngReqFeature> searchFilter) {
		LOGGER.info("=========== Count TChngReqFeature ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TChngReqFeature tChngReqFeature = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tChngReqFeatureentity", tChngReqFeature);

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

}
