package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TCallPlanType;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TCallPlanTypeDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tCallPlanTypeDAO")
public class TCallPlanTypeDAOImpl implements TCallPlanTypeDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TCallPlanTypeDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private final Class<TCallPlanType> clazz;

	public Class<TCallPlanType> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TCallPlanTypeDAOImpl() {
		super();
		this.clazz = TCallPlanType.class;
	}

	private static final String JPAQL = "select tCallPlanTypeentity from TCallPlanType tCallPlanTypeentity";

	private static final String JPACOUNTQL = "select count(tCallPlanTypeentity) from TCallPlanType tCallPlanTypeentity";

	private static final String[] RESTRICTIONS = {
			"tCallPlanTypeentity.callPlanTypeId = #{tCallPlanTypeentity.getCallPlanTypeId}",
			"tCallPlanTypeentity.callPlanTypeDesc like '#{tCallPlanTypeentity.getCallPlanTypeDesc}%'",
			"tCallPlanTypeentity.activeFlag = #{tCallPlanTypeentity.getActiveFlag}",
			"tCallPlanTypeentity.createdBy = #{tCallPlanTypeentity.getCreatedBy}",
			"Date(tCallPlanTypeentity.createDt) = '#{tCallPlanTypeentity.getCreateDt}'",
			"tCallPlanTypeentity.updatedBy = #{tCallPlanTypeentity.getUpdatedBy}",
			"Date(tCallPlanTypeentity.updateDt) = '#{tCallPlanTypeentity.getUpdateDt}'",
			"tCallPlanTypeentity.tenantId = #{tCallPlanTypeentity.getTenantId}" };

	/**
	 * Stores a new TCallPlanType entity object in to the persistent store
	 * 
	 * @param tCallPlanType
	 *            TCallPlanType Entity object to be persisted
	 * @return tCallPlanType Persisted TCallPlanType object
	 */
	public TCallPlanType createTCallPlanType(final TCallPlanType tCallPlanType) {
		LOGGER.info("=========== Create TCallPlanType ===========");
		return genericDAO.store(tCallPlanType);
	}

	/**
	 * Deletes a TCallPlanType entity object from the persistent store
	 * 
	 * @param tCallPlanType
	 *            TCallPlanType Entity object to be deleted
	 */
	public void deleteTCallPlanType(final Short callPlanTypeId) {
		LOGGER.info("=========== Delete TCallPlanType ===========");
		final TCallPlanType tCallPlanType = genericDAO.get(clazz, callPlanTypeId);
		genericDAO.remove(tCallPlanType);
	}

	/**
	 * Updates a TCallPlanType entity object in to the persistent store
	 * 
	 * @param tCallPlanType
	 *            TCallPlanType Entity object to be updated
	 * @return tCallPlanType Persisted TCallPlanType object
	 */
	public TCallPlanType updateTCallPlanType(final TCallPlanType tCallPlanType) {
		LOGGER.info("=========== Update TCallPlanType ===========");
		return genericDAO.update(tCallPlanType);
	}

	/**
	 * Retrieve an TCallPlanType object based on given TCallPlanType callPlanTypeId.
	 * 
	 * @param tCallPlanTypeId
	 *            the primary key value of the TCallPlanType Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TCallPlanType findTCallPlanTypeById(final Short tCallPlanTypeId) {
		LOGGER.info("find TCallPlanType instance with callPlanTypeId: " + tCallPlanTypeId);
		return genericDAO.get(clazz, tCallPlanTypeId);
	}

	/**
	 * Retrieve TCallPlanType based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCallPlanType> list of TCallPlanType if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TCallPlanType> findTCallPlanTypes(final SearchFilter<TCallPlanType> searchFilter) {
		LOGGER.info("=========== Find TCallPlanTypes ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TCallPlanType tCallPlanType = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tCallPlanTypeentity", tCallPlanType);
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TCallPlanTypes.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTCallPlanTypes(final SearchFilter<TCallPlanType> searchFilter) {
		LOGGER.info("=========== Count TCallPlanType ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TCallPlanType tCallPlanType = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tCallPlanTypeentity", tCallPlanType);

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

}
