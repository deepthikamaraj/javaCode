package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TFeatureType;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TFeatureTypeDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tFeatureTypeDAO")
public class TFeatureTypeDAOImpl implements TFeatureTypeDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TFeatureTypeDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private final Class<TFeatureType> clazz;

	public Class<TFeatureType> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TFeatureTypeDAOImpl() {
		super();
		this.clazz = TFeatureType.class;
	}

	private static final String JPAQL = "select tFeatureTypeentity from TFeatureType tFeatureTypeentity";

	private static final String JPACOUNTQL = "select count(tFeatureTypeentity) from TFeatureType tFeatureTypeentity";

	private static final String[] RESTRICTIONS = { "tFeatureTypeentity.typeId = #{tFeatureTypeentity.getTypeId}",
			"tFeatureTypeentity.typeDesc like '#{tFeatureTypeentity.getTypeDesc}%'",
			"tFeatureTypeentity.activeFlag = #{tFeatureTypeentity.getActiveFlag}",
			"tFeatureTypeentity.createdBy = #{tFeatureTypeentity.getCreatedBy}",
			"Date(tFeatureTypeentity.createDt) = '#{tFeatureTypeentity.getCreateDt}'",
			"tFeatureTypeentity.updatedBy = #{tFeatureTypeentity.getUpdatedBy}",
			"Date(tFeatureTypeentity.updateDt) = '#{tFeatureTypeentity.getUpdateDt}'",
			"tFeatureTypeentity.tenantId = #{tFeatureTypeentity.getTenantId}" };

	/**
	 * Stores a new TFeatureType entity object in to the persistent store
	 * 
	 * @param tFeatureType
	 *            TFeatureType Entity object to be persisted
	 * @return tFeatureType Persisted TFeatureType object
	 */
	public TFeatureType createTFeatureType(final TFeatureType tFeatureType) {
		LOGGER.info("=========== Create TFeatureType ===========");
		return genericDAO.store(tFeatureType);
	}

	/**
	 * Deletes a TFeatureType entity object from the persistent store
	 * 
	 * @param tFeatureType
	 *            TFeatureType Entity object to be deleted
	 */
	public void deleteTFeatureType(final Integer typeId) {
		LOGGER.info("=========== Delete TFeatureType ===========");
		final TFeatureType tFeatureType = genericDAO.get(clazz, typeId);
		genericDAO.remove(tFeatureType);
	}

	/**
	 * Updates a TFeatureType entity object in to the persistent store
	 * 
	 * @param tFeatureType
	 *            TFeatureType Entity object to be updated
	 * @return tFeatureType Persisted TFeatureType object
	 */
	public TFeatureType updateTFeatureType(final TFeatureType tFeatureType) {
		LOGGER.info("=========== Update TFeatureType ===========");
		return genericDAO.update(tFeatureType);
	}

	/**
	 * Retrieve an TFeatureType object based on given TFeatureType typeId.
	 * 
	 * @param tFeatureTypeId
	 *            the primary key value of the TFeatureType Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TFeatureType findTFeatureTypeById(final Integer tFeatureTypeId) {
		LOGGER.info("find TFeatureType instance with typeId: " + tFeatureTypeId);
		return genericDAO.get(clazz, tFeatureTypeId);
	}

	/**
	 * Retrieve TFeatureType based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TFeatureType> list of TFeatureType if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TFeatureType> findTFeatureTypes(final SearchFilter<TFeatureType> searchFilter) {
		LOGGER.info("=========== Find TFeatureTypes ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TFeatureType tFeatureType = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tFeatureTypeentity", tFeatureType);
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TFeatureTypes.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTFeatureTypes(final SearchFilter<TFeatureType> searchFilter) {
		LOGGER.info("=========== Count TFeatureType ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TFeatureType tFeatureType = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tFeatureTypeentity", tFeatureType);

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

}
