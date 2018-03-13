package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TRuleType;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TRuleTypeDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tRuleTypeDAO")
public class TRuleTypeDAOImpl implements TRuleTypeDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TRuleTypeDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private final Class<TRuleType> clazz;

	public Class<TRuleType> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TRuleTypeDAOImpl() {
		super();
		this.clazz = TRuleType.class;
	}

	private static final String JPAQL = "select tRuleTypeentity from TRuleType tRuleTypeentity";

	private static final String JPACOUNTQL = "select count(tRuleTypeentity) from TRuleType tRuleTypeentity";

	private static final String[] RESTRICTIONS = { "tRuleTypeentity.ruleTypeId = #{tRuleTypeentity.getRuleTypeId}",
			"tRuleTypeentity.ruleTypeDesc like '#{tRuleTypeentity.getRuleTypeDesc}%'",
			"tRuleTypeentity.activeFlag = #{tRuleTypeentity.getActiveFlag}",
			"tRuleTypeentity.createdBy = #{tRuleTypeentity.getCreatedBy}",
			"Date(tRuleTypeentity.createDt) = '#{tRuleTypeentity.getCreateDt}'",
			"tRuleTypeentity.updatedBy = #{tRuleTypeentity.getUpdatedBy}",
			"Date(tRuleTypeentity.updateDt) = '#{tRuleTypeentity.getUpdateDt}'" };

	/**
	 * Stores a new TRuleType entity object in to the persistent store
	 * 
	 * @param tRuleType
	 *            TRuleType Entity object to be persisted
	 * @return tRuleType Persisted TRuleType object
	 */
	public TRuleType createTRuleType(final TRuleType tRuleType) {
		LOGGER.info("=========== Create TRuleType ===========");
		return genericDAO.store(tRuleType);
	}

	/**
	 * Deletes a TRuleType entity object from the persistent store
	 * 
	 * @param tRuleType
	 *            TRuleType Entity object to be deleted
	 */
	public void deleteTRuleType(final Integer ruleTypeId) {
		LOGGER.info("=========== Delete TRuleType ===========");
		final TRuleType tRuleType = genericDAO.get(clazz, ruleTypeId);
		genericDAO.remove(tRuleType);
	}

	/**
	 * Updates a TRuleType entity object in to the persistent store
	 * 
	 * @param tRuleType
	 *            TRuleType Entity object to be updated
	 * @return tRuleType Persisted TRuleType object
	 */
	public TRuleType updateTRuleType(final TRuleType tRuleType) {
		LOGGER.info("=========== Update TRuleType ===========");
		return genericDAO.update(tRuleType);
	}

	/**
	 * Retrieve an TRuleType object based on given TRuleType ruleTypeId.
	 * 
	 * @param tRuleTypeId
	 *            the primary key value of the TRuleType Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TRuleType findTRuleTypeById(final Integer tRuleTypeId) {
		LOGGER.info("find TRuleType instance with ruleTypeId: " + tRuleTypeId);
		return genericDAO.get(clazz, tRuleTypeId);
	}

	/**
	 * Retrieve TRuleType based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRuleType> list of TRuleType if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TRuleType> findTRuleTypes(final SearchFilter<TRuleType> searchFilter) {
		LOGGER.info("=========== Find TRuleTypes ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TRuleType tRuleType = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tRuleTypeentity", tRuleType);
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TRuleTypes.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTRuleTypes(final SearchFilter<TRuleType> searchFilter) {
		LOGGER.info("=========== Count TRuleType ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TRuleType tRuleType = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tRuleTypeentity", tRuleType);

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

}
