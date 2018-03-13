package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TPrdHierType;
import com.cognizant.opserv.sp.core.entity.TPrdHierTypeId;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TPrdHierTypeDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tPrdHierTypeDAO")
public class TPrdHierTypeDAOImpl implements TPrdHierTypeDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TPrdHierTypeDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private final Class<TPrdHierType> clazz;

	public Class<TPrdHierType> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TPrdHierTypeDAOImpl() {
		super();
		this.clazz = TPrdHierType.class;
	}

	private static final String JPAQL = "select tPrdHierTypeentity from TPrdHierType tPrdHierTypeentity";

	private static final String JPACOUNTQL = "select count(tPrdHierTypeentity) from TPrdHierType tPrdHierTypeentity";

	private static final String[] RESTRICTIONS = {
			"tPrdHierTypeentity.tPrdHierTypeId.hierTypeId = #{tPrdHierTypeentity.tPrdHierTypeId.getHierTypeId}",
			"tPrdHierTypeentity.tPrdHierTypeId.localeId = #{tPrdHierTypeentity.tPrdHierTypeId.getLocaleId}",
			"tPrdHierTypeentity.hierTypeDesc like '#{tPrdHierTypeentity.getHierTypeDesc}%'",
			"tPrdHierTypeentity.activeFlag = #{tPrdHierTypeentity.getActiveFlag}",
			"tPrdHierTypeentity.createdBy = #{tPrdHierTypeentity.getCreatedBy}",
			"Date(tPrdHierTypeentity.createDt) = '#{tPrdHierTypeentity.getCreateDt}'",
			"tPrdHierTypeentity.updatedBy = #{tPrdHierTypeentity.getUpdatedBy}",
			"Date(tPrdHierTypeentity.updateDt) = '#{tPrdHierTypeentity.getUpdateDt}'",
			"tPrdHierTypeentity.tenantId = #{tPrdHierTypeentity.getTenantId}",
			"tPrdHierTypeentity.hierTypeName like '#{tPrdHierTypeentity.getHierTypeName}%'"};

	/**
	 * Stores a new TPrdHierType entity object in to the persistent store
	 * 
	 * @param tPrdHierType
	 *            TPrdHierType Entity object to be persisted
	 * @return tPrdHierType Persisted TPrdHierType object
	 */
	public TPrdHierType createTPrdHierType(final TPrdHierType tPrdHierType) {
		LOGGER.info("=========== Create TPrdHierType ===========");
		return genericDAO.store(tPrdHierType);
	}

	/**
	 * Deletes a TPrdHierType entity object from the persistent store
	 * 
	 * @param tPrdHierType
	 *            TPrdHierType Entity object to be deleted
	 */
	public void deleteTPrdHierType(final Integer hierTypeId) {
		LOGGER.info("=========== Delete TPrdHierType ===========");
		final TPrdHierType tPrdHierType = genericDAO.get(clazz, hierTypeId);
		genericDAO.remove(tPrdHierType);
	}

	/**
	 * Updates a TPrdHierType entity object in to the persistent store
	 * 
	 * @param tPrdHierType
	 *            TPrdHierType Entity object to be updated
	 * @return tPrdHierType Persisted TPrdHierType object
	 */
	public TPrdHierType updateTPrdHierType(final TPrdHierType tPrdHierType) {
		LOGGER.info("=========== Update TPrdHierType ===========");
		return genericDAO.update(tPrdHierType);
	}

	/**
	 * Retrieve an TPrdHierType object based on given TPrdHierType hierTypeId.
	 * 
	 * @param tPrdHierTypeId
	 *            the primary key value of the TPrdHierType Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TPrdHierType findTPrdHierTypeById(final TPrdHierTypeId tPrdHierTypeId) {
		LOGGER.info("find TPrdHierType instance with hierTypeId: " + tPrdHierTypeId);
		return genericDAO.get(clazz, tPrdHierTypeId);
	}

	/**
	 * Retrieve TPrdHierType based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TPrdHierType> list of TPrdHierType if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TPrdHierType> findTPrdHierTypes(final SearchFilter<TPrdHierType> searchFilter) {
		LOGGER.info("=========== Find TPrdHierTypes ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TPrdHierType tPrdHierType = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tPrdHierTypeentity", tPrdHierType);
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TPrdHierTypes.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTPrdHierTypes(final SearchFilter<TPrdHierType> searchFilter) {
		LOGGER.info("=========== Count TPrdHierType ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TPrdHierType tPrdHierType = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tPrdHierTypeentity", tPrdHierType);

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

}
