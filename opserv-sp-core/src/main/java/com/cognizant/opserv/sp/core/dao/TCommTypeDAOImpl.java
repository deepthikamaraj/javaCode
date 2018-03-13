package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TCommType;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TCommTypeDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tCommTypeDAO")
public class TCommTypeDAOImpl implements TCommTypeDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TCommTypeDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private final Class<TCommType> clazz;

	public Class<TCommType> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TCommTypeDAOImpl() {
		super();
		this.clazz = TCommType.class;
	}

	private static final String JPAQL = "select tCommTypeentity from TCommType tCommTypeentity";

	private static final String JPACOUNTQL = "select count(tCommTypeentity) from TCommType tCommTypeentity";

	private static final String[] RESTRICTIONS = { "tCommTypeentity.commTypeId = #{tCommTypeentity.getCommTypeId}",
			"tCommTypeentity.commTypeName like '#{tCommTypeentity.getCommTypeName}%'",
			"tCommTypeentity.commTypeDesc like '#{tCommTypeentity.getCommTypeDesc}%'",
			"tCommTypeentity.activeFlag = #{tCommTypeentity.getActiveFlag}",
			"tCommTypeentity.createdBy = #{tCommTypeentity.getCreatedBy}",
			"Date(tCommTypeentity.createDt) = '#{tCommTypeentity.getCreateDt}'",
			"tCommTypeentity.updatedBy = #{tCommTypeentity.getUpdatedBy}",
			"Date(tCommTypeentity.updateDate) = '#{tCommTypeentity.getUpdateDate}'",
			"tCommTypeentity.tenantId = #{tCommTypeentity.getTenantId}" };

	/**
	 * Stores a new TCommType entity object in to the persistent store
	 * 
	 * @param tCommType
	 *            TCommType Entity object to be persisted
	 * @return tCommType Persisted TCommType object
	 */
	public TCommType createTCommType(final TCommType tCommType) {
		LOGGER.info("=========== Create TCommType ===========");
		return genericDAO.store(tCommType);
	}

	/**
	 * Deletes a TCommType entity object from the persistent store
	 * 
	 * @param tCommType
	 *            TCommType Entity object to be deleted
	 */
	public void deleteTCommType(final Integer commTypeId) {
		LOGGER.info("=========== Delete TCommType ===========");
		final TCommType tCommType = genericDAO.get(clazz, commTypeId);
		genericDAO.remove(tCommType);
	}

	/**
	 * Updates a TCommType entity object in to the persistent store
	 * 
	 * @param tCommType
	 *            TCommType Entity object to be updated
	 * @return tCommType Persisted TCommType object
	 */
	public TCommType updateTCommType(final TCommType tCommType) {
		LOGGER.info("=========== Update TCommType ===========");
		return genericDAO.update(tCommType);
	}

	/**
	 * Retrieve an TCommType object based on given TCommType commTypeId.
	 * 
	 * @param tCommTypeId
	 *            the primary key value of the TCommType Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TCommType findTCommTypeById(final Integer tCommTypeId) {
		LOGGER.info("find TCommType instance with commTypeId: " + tCommTypeId);
		return genericDAO.get(clazz, tCommTypeId);
	}

	/**
	 * Retrieve TCommType based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCommType> list of TCommType if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TCommType> findTCommTypes(final SearchFilter<TCommType> searchFilter) {
		LOGGER.info("=========== Find TCommTypes ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TCommType tCommType = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tCommTypeentity", tCommType);
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TCommTypes.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTCommTypes(final SearchFilter<TCommType> searchFilter) {
		LOGGER.info("=========== Count TCommType ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TCommType tCommType = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tCommTypeentity", tCommType);

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

}
