package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TRptType;
import com.cognizant.opserv.sp.core.entity.TRptTypeId;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TRptTypeDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tRptTypeDAO")
public class TRptTypeDAOImpl implements TRptTypeDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TRptTypeDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private final Class<TRptType> clazz;

	public Class<TRptType> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TRptTypeDAOImpl() {
		super();
		this.clazz = TRptType.class;
	}

	private static final String JPAQL = "select tRptTypeentity from TRptType tRptTypeentity";

	private static final String JPACOUNTQL = "select count(tRptTypeentity) from TRptType tRptTypeentity";

	private static final String[] RESTRICTIONS = { "tRptTypeentity.rptTypeId = #{tRptTypeentity.getRptTypeId}",
			"tRptTypeentity.rptTypeName like '#{tRptTypeentity.getRptTypeName}%'",
			"tRptTypeentity.rptTypeDesc like '#{tRptTypeentity.getRptTypeDesc}%'",
			"tRptTypeentity.activeFlag = #{tRptTypeentity.getActiveFlag}",
			"tRptTypeentity.createdBy = #{tRptTypeentity.getCreatedBy}",
			"Date(tRptTypeentity.createDt) = '#{tRptTypeentity.getCreateDt}'",
			"tRptTypeentity.updatedBy = #{tRptTypeentity.getUpdatedBy}",
			"Date(tRptTypeentity.updateDate) = '#{tRptTypeentity.getUpdateDate}'",
			"tRptTypeentity.tenantId = #{tRptTypeentity.getTenantId}" };

	/**
	 * Stores a new TRptType entity object in to the persistent store
	 * 
	 * @param tRptType
	 *            TRptType Entity object to be persisted
	 * @return tRptType Persisted TRptType object
	 */
	public TRptType createTRptType(final TRptType tRptType) {
		LOGGER.info("=========== Create TRptType ===========");
		return genericDAO.store(tRptType);
	}

	/**
	 * Deletes a TRptType entity object from the persistent store
	 * 
	 * @param tRptType
	 *            TRptType Entity object to be deleted
	 */
	public void deleteTRptType(final Integer rptTypeId) {
		LOGGER.info("=========== Delete TRptType ===========");
		final TRptType tRptType = genericDAO.get(clazz, rptTypeId);
		genericDAO.remove(tRptType);
	}

	/**
	 * Updates a TRptType entity object in to the persistent store
	 * 
	 * @param tRptType
	 *            TRptType Entity object to be updated
	 * @return tRptType Persisted TRptType object
	 */
	public TRptType updateTRptType(final TRptType tRptType) {
		LOGGER.info("=========== Update TRptType ===========");
		return genericDAO.update(tRptType);
	}

	/**
	 * Retrieve an TRptType object based on given TRptType rptTypeId.
	 * 
	 * @param tRptTypeId
	 *            the primary key value of the TRptType Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TRptType findTRptTypeById(final TRptTypeId tRptTypeId) {
		LOGGER.info("find TRptType instance with rptTypeId: " + tRptTypeId);
		return genericDAO.get(clazz, tRptTypeId);
	}

	/**
	 * Retrieve TRptType based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRptType> list of TRptType if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TRptType> findTRptTypes(final SearchFilter<TRptType> searchFilter) {
		LOGGER.info("=========== Find TRptTypes ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TRptType tRptType = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tRptTypeentity", tRptType);
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TRptTypes.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTRptTypes(final SearchFilter<TRptType> searchFilter) {
		LOGGER.info("=========== Count TRptType ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TRptType tRptType = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tRptTypeentity", tRptType);

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

}
