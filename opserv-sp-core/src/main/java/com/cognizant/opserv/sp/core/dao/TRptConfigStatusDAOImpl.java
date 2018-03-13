package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TRptConfigStatus;
import com.cognizant.opserv.sp.core.entity.TRptConfigStatusId;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TRptConfigStatusDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tRptConfigStatusDAO")
public class TRptConfigStatusDAOImpl implements TRptConfigStatusDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TRptConfigStatusDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	//private static final String TRPTCONFIGSTATUS = "tRptConfigStatus";

	private final Class<TRptConfigStatus> clazz;

	public Class<TRptConfigStatus> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TRptConfigStatusDAOImpl() {
		super();
		this.clazz = TRptConfigStatus.class;
	}

	private static final String JPAQL = "select tRptConfigStatusentity from TRptConfigStatus tRptConfigStatusentity";

	private static final String JPACOUNTQL = "select count(tRptConfigStatusentity) from TRptConfigStatus tRptConfigStatusentity";

	private static final String[] RESTRICTIONS = {
			"tRptConfigStatusentity.rptConfigStatusId = #{tRptConfigStatusentity.getRptConfigStatusId}",
			"tRptConfigStatusentity.rptConfigStatusName like '#{tRptConfigStatusentity.getRptConfigStatusName}%'",
			"tRptConfigStatusentity.rptConfigStatusDesc like '#{tRptConfigStatusentity.getRptConfigStatusDesc}%'",
			"tRptConfigStatusentity.activeFlag = #{tRptConfigStatusentity.getActiveFlag}",
			"tRptConfigStatusentity.createdBy = #{tRptConfigStatusentity.getCreatedBy}",
			"Date(tRptConfigStatusentity.createDt) = '#{tRptConfigStatusentity.getCreateDt}'",
			"tRptConfigStatusentity.updatedBy = #{tRptConfigStatusentity.getUpdatedBy}",
			"Date(tRptConfigStatusentity.updateDate) = '#{tRptConfigStatusentity.getUpdateDate}'",
			"tRptConfigStatusentity.tenantId = #{tRptConfigStatusentity.getTenantId}",
			"tRptConfigStatusentity.tRptConfigStatus.tRptConfigStatusId.rptConfigStatusId = #{tRptConfigStatusentity.tRptConfigStatus.tRptConfigStatusId.getRptConfigStatusId}" };

	/**
	 * Stores a new TRptConfigStatus entity object in to the persistent store
	 * 
	 * @param tRptConfigStatus
	 *            TRptConfigStatus Entity object to be persisted
	 * @return tRptConfigStatus Persisted TRptConfigStatus object
	 */
	public TRptConfigStatus createTRptConfigStatus(final TRptConfigStatus tRptConfigStatus) {
		LOGGER.info("=========== Create TRptConfigStatus ===========");
		return genericDAO.store(tRptConfigStatus);
	}

	/**
	 * Deletes a TRptConfigStatus entity object from the persistent store
	 * 
	 * @param tRptConfigStatus
	 *            TRptConfigStatus Entity object to be deleted
	 */
	public void deleteTRptConfigStatus(final Integer rptConfigStatusId) {
		LOGGER.info("=========== Delete TRptConfigStatus ===========");
		final TRptConfigStatus tRptConfigStatus = genericDAO.get(clazz, rptConfigStatusId);
		genericDAO.remove(tRptConfigStatus);
	}

	/**
	 * Updates a TRptConfigStatus entity object in to the persistent store
	 * 
	 * @param tRptConfigStatus
	 *            TRptConfigStatus Entity object to be updated
	 * @return tRptConfigStatus Persisted TRptConfigStatus object
	 */
	public TRptConfigStatus updateTRptConfigStatus(final TRptConfigStatus tRptConfigStatus) {
		LOGGER.info("=========== Update TRptConfigStatus ===========");
		return genericDAO.update(tRptConfigStatus);
	}

	/**
	 * Retrieve an TRptConfigStatus object based on given TRptConfigStatus rptConfigStatusId.
	 * 
	 * @param tRptConfigStatusId
	 *            the primary key value of the TRptConfigStatus Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TRptConfigStatus findTRptConfigStatusById(final TRptConfigStatusId tRptConfigStatusId) {
		LOGGER.info("find TRptConfigStatus instance with rptConfigStatusId: " + tRptConfigStatusId);
		return genericDAO.get(clazz, tRptConfigStatusId);
	}

	/**
	 * Retrieve TRptConfigStatus based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRptConfigStatus> list of TRptConfigStatus if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TRptConfigStatus> findTRptConfigStatuss(final SearchFilter<TRptConfigStatus> searchFilter) {
		LOGGER.info("=========== Find TRptConfigStatuss ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TRptConfigStatus tRptConfigStatus = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tRptConfigStatusentity", tRptConfigStatus);

		/*if (tRptConfigStatus.getTRptConfigStatus() == null) {
			jpaQuery.bind(TRPTCONFIGSTATUS, new TRptConfigStatus());
		} else {
			LOGGER.info("tRptConfigStatus {}", tRptConfigStatus.getTRptConfigStatus());

			jpaQuery.bind(TRPTCONFIGSTATUS, tRptConfigStatus.getTRptConfigStatus());
		}*/
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TRptConfigStatuss.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTRptConfigStatuss(final SearchFilter<TRptConfigStatus> searchFilter) {
		LOGGER.info("=========== Count TRptConfigStatus ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TRptConfigStatus tRptConfigStatus = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tRptConfigStatusentity", tRptConfigStatus);

		/*if (tRptConfigStatus.getTRptConfigStatus() == null) {
			jpaCountQuery.bind(TRPTCONFIGSTATUS, new TRptConfigStatus());
		} else {
			LOGGER.info("tRptConfigStatus {}", tRptConfigStatus.getTRptConfigStatus());

			jpaCountQuery.bind(TRPTCONFIGSTATUS, tRptConfigStatus.getTRptConfigStatus());
		}*/

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TRptConfigStatus based on given search criteria using JPA named Query.
	 * The search criteria is of TRptConfigStatus type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRptConfigStatus> list of TRptConfigStatuss if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TRptConfigStatus> getTRptConfigStatussByTRptConfigStatus(
			final SearchFilter<TRptConfigStatus> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TRptConfigStatus tRptConfigStatus = searchFilter.getEntityClass();
		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(tRptConfigStatus.gettRptConfigStatusId().getRptConfigStatusId());
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTRptConfigStatusByTRptConfigStatus",queryParam , index,
				maxresult);
	}

	/**
	 * Count TRptConfigStatus based on given search criteria using JPA named Query.
	 * The search criteria is of TRptConfigStatus type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTRptConfigStatussByTRptConfigStatus(final SearchFilter<TRptConfigStatus> searchFilter) {

		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());
		return genericDAO.findEntitiesByNamedQuery("CountTRptConfigStatussByTRptConfigStatus", queryParam);
	}

}
