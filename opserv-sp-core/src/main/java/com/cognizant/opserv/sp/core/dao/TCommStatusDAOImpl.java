package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TCommStatus;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TCommStatusDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tCommStatusDAO")
public class TCommStatusDAOImpl implements TCommStatusDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TCommStatusDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private final Class<TCommStatus> clazz;

	public Class<TCommStatus> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TCommStatusDAOImpl() {
		super();
		this.clazz = TCommStatus.class;
	}

	private static final String JPAQL = "select tCommStatusentity from TCommStatus tCommStatusentity";

	private static final String JPACOUNTQL = "select count(tCommStatusentity) from TCommStatus tCommStatusentity";

	private static final String[] RESTRICTIONS = {
			"tCommStatusentity.commStatusId = #{tCommStatusentity.getCommStatusId}",
			"tCommStatusentity.commStatusName like '#{tCommStatusentity.getCommStatusName}%'",
			"tCommStatusentity.commStatusDesc like '#{tCommStatusentity.getCommStatusDesc}%'",
			"tCommStatusentity.activeFlag = #{tCommStatusentity.getActiveFlag}",
			"tCommStatusentity.createdBy = #{tCommStatusentity.getCreatedBy}",
			"Date(tCommStatusentity.createDt) = '#{tCommStatusentity.getCreateDt}'",
			"tCommStatusentity.updatedBy = #{tCommStatusentity.getUpdatedBy}",
			"Date(tCommStatusentity.updateDate) = '#{tCommStatusentity.getUpdateDate}'",
			"tCommStatusentity.tenantId = #{tCommStatusentity.getTenantId}" };

	/**
	 * Stores a new TCommStatus entity object in to the persistent store
	 * 
	 * @param tCommStatus
	 *            TCommStatus Entity object to be persisted
	 * @return tCommStatus Persisted TCommStatus object
	 */
	public TCommStatus createTCommStatus(final TCommStatus tCommStatus) {
		LOGGER.info("=========== Create TCommStatus ===========");
		return genericDAO.store(tCommStatus);
	}

	/**
	 * Deletes a TCommStatus entity object from the persistent store
	 * 
	 * @param tCommStatus
	 *            TCommStatus Entity object to be deleted
	 */
	public void deleteTCommStatus(final Integer commStatusId) {
		LOGGER.info("=========== Delete TCommStatus ===========");
		final TCommStatus tCommStatus = genericDAO.get(clazz, commStatusId);
		genericDAO.remove(tCommStatus);
	}

	/**
	 * Updates a TCommStatus entity object in to the persistent store
	 * 
	 * @param tCommStatus
	 *            TCommStatus Entity object to be updated
	 * @return tCommStatus Persisted TCommStatus object
	 */
	public TCommStatus updateTCommStatus(final TCommStatus tCommStatus) {
		LOGGER.info("=========== Update TCommStatus ===========");
		return genericDAO.update(tCommStatus);
	}

	/**
	 * Retrieve an TCommStatus object based on given TCommStatus commStatusId.
	 * 
	 * @param tCommStatusId
	 *            the primary key value of the TCommStatus Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TCommStatus findTCommStatusById(final Integer tCommStatusId) {
		LOGGER.info("find TCommStatus instance with commStatusId: " + tCommStatusId);
		return genericDAO.get(clazz, tCommStatusId);
	}

	/**
	 * Retrieve TCommStatus based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCommStatus> list of TCommStatus if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TCommStatus> findTCommStatuss(final SearchFilter<TCommStatus> searchFilter) {
		LOGGER.info("=========== Find TCommStatuss ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TCommStatus tCommStatus = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tCommStatusentity", tCommStatus);
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TCommStatuss.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTCommStatuss(final SearchFilter<TCommStatus> searchFilter) {
		LOGGER.info("=========== Count TCommStatus ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TCommStatus tCommStatus = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tCommStatusentity", tCommStatus);

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

}
