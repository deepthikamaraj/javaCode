package com.cognizant.opserv.sp.core.dao;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TContactType;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TContactTypeDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tContactTypeDAO")
public class TContactTypeDAOImpl implements TContactTypeDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TContactTypeDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private final Class<TContactType> clazz;

	public Class<TContactType> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TContactTypeDAOImpl() {
		super();
		this.clazz = TContactType.class;
	}

	private static final String JPAQL = "select tContactTypeentity from TContactType tContactTypeentity";

	private static final String JPACOUNTQL = "select count(tContactTypeentity) from TContactType tContactTypeentity";

	private static final String[] RESTRICTIONS = {
			"tContactTypeentity.contactTypeId = #{tContactTypeentity.getContactTypeId}",
			"tContactTypeentity.contactTypeName like '#{tContactTypeentity.getContactTypeName}%'",
			"tContactTypeentity.contactTypeDesc like '#{tContactTypeentity.getContactTypeDesc}%'",
			"tContactTypeentity.activeFlag = #{tContactTypeentity.getActiveFlag}",
			"tContactTypeentity.createdBy = #{tContactTypeentity.getCreatedBy}",
			"Date(tContactTypeentity.createDt) = '#{tContactTypeentity.getCreateDt}'",
			"tContactTypeentity.updatedBy = #{tContactTypeentity.getUpdatedBy}",
			"Date(tContactTypeentity.updateDt) = '#{tContactTypeentity.getUpdateDt}'",
			"tContactTypeentity.tenantId = #{tContactTypeentity.getTenantId}" };

	/**
	 * Stores a new TContactType entity object in to the persistent store
	 * 
	 * @param tContactType
	 *            TContactType Entity object to be persisted
	 * @return tContactType Persisted TContactType object
	 */
	public TContactType createTContactType(final TContactType tContactType) {
		LOGGER.info("=========== Create TContactType ===========");
		return genericDAO.store(tContactType);
	}

	/**
	 * Deletes a TContactType entity object from the persistent store
	 * 
	 * @param tContactType
	 *            TContactType Entity object to be deleted
	 */
	public void deleteTContactType(final Integer contactTypeId) {
		LOGGER.info("=========== Delete TContactType ===========");
		final TContactType tContactType = genericDAO.get(clazz, contactTypeId);
		genericDAO.remove(tContactType);
	}

	/**
	 * Updates a TContactType entity object in to the persistent store
	 * 
	 * @param tContactType
	 *            TContactType Entity object to be updated
	 * @return tContactType Persisted TContactType object
	 */
	public TContactType updateTContactType(final TContactType tContactType) {
		LOGGER.info("=========== Update TContactType ===========");
		return genericDAO.update(tContactType);
	}

	/**
	 * Retrieve an TContactType object based on given TContactType contactTypeId.
	 * 
	 * @param tContactTypeId
	 *            the primary key value of the TContactType Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TContactType findTContactTypeById(final Integer tContactTypeId) {
		LOGGER.info("find TContactType instance with contactTypeId: " + tContactTypeId);
		return genericDAO.get(clazz, tContactTypeId);
	}

	/**
	 * Retrieve TContactType based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TContactType> list of TContactType if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TContactType> findTContactTypes(final SearchFilter<TContactType> searchFilter) {
		LOGGER.info("=========== Find TContactTypes ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TContactType tContactType = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tContactTypeentity", tContactType);
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TContactTypes.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTContactTypes(final SearchFilter<TContactType> searchFilter) {
		LOGGER.info("=========== Count TContactType ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TContactType tContactType = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tContactTypeentity", tContactType);

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

}
