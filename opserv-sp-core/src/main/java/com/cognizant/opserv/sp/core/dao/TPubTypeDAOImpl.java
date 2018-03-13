package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TPubType;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TPubTypeDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tPubTypeDAO")
public class TPubTypeDAOImpl implements TPubTypeDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TPubTypeDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private final Class<TPubType> clazz;

	public Class<TPubType> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TPubTypeDAOImpl() {
		super();
		this.clazz = TPubType.class;
	}

	private static final String JPAQL = "select tPubTypeentity from TPubType tPubTypeentity";

	private static final String JPACOUNTQL = "select count(tPubTypeentity) from TPubType tPubTypeentity";

	private static final String[] RESTRICTIONS = { "tPubTypeentity.pubTypeId = #{tPubTypeentity.getPubTypeId}",
			"tPubTypeentity.pubTypeDesc like '#{tPubTypeentity.getPubTypeDesc}%'",
			"tPubTypeentity.activeFlag = #{tPubTypeentity.getActiveFlag}",
			"tPubTypeentity.createdBy = #{tPubTypeentity.getCreatedBy}",
			"Date(tPubTypeentity.createDt) = '#{tPubTypeentity.getCreateDt}'",
			"tPubTypeentity.updatedBy = #{tPubTypeentity.getUpdatedBy}",
			"Date(tPubTypeentity.updateDt) = '#{tPubTypeentity.getUpdateDt}'",
			"tPubTypeentity.tenantId = #{tPubTypeentity.getTenantId}" };

	/**
	 * Stores a new TPubType entity object in to the persistent store
	 * 
	 * @param tPubType
	 *            TPubType Entity object to be persisted
	 * @return tPubType Persisted TPubType object
	 */
	public TPubType createTPubType(final TPubType tPubType) {
		LOGGER.info("=========== Create TPubType ===========");
		return genericDAO.store(tPubType);
	}

	/**
	 * Deletes a TPubType entity object from the persistent store
	 * 
	 * @param tPubType
	 *            TPubType Entity object to be deleted
	 */
	public void deleteTPubType(final Integer pubTypeId) {
		LOGGER.info("=========== Delete TPubType ===========");
		final TPubType tPubType = genericDAO.get(clazz, pubTypeId);
		genericDAO.remove(tPubType);
	}

	/**
	 * Updates a TPubType entity object in to the persistent store
	 * 
	 * @param tPubType
	 *            TPubType Entity object to be updated
	 * @return tPubType Persisted TPubType object
	 */
	public TPubType updateTPubType(final TPubType tPubType) {
		LOGGER.info("=========== Update TPubType ===========");
		return genericDAO.update(tPubType);
	}

	/**
	 * Retrieve an TPubType object based on given TPubType pubTypeId.
	 * 
	 * @param tPubTypeId
	 *            the primary key value of the TPubType Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TPubType findTPubTypeById(final Integer tPubTypeId) {
		LOGGER.info("find TPubType instance with pubTypeId: " + tPubTypeId);
		return genericDAO.get(clazz, tPubTypeId);
	}

	/**
	 * Retrieve TPubType based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TPubType> list of TPubType if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TPubType> findTPubTypes(final SearchFilter<TPubType> searchFilter) {
		LOGGER.info("=========== Find TPubTypes ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TPubType tPubType = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tPubTypeentity", tPubType);
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TPubTypes.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTPubTypes(final SearchFilter<TPubType> searchFilter) {
		LOGGER.info("=========== Count TPubType ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TPubType tPubType = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tPubTypeentity", tPubType);

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

}
