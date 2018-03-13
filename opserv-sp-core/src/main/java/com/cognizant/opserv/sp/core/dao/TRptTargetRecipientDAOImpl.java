package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TPers;
import com.cognizant.opserv.sp.core.entity.TRptConfig;
import com.cognizant.opserv.sp.core.entity.TRptTargetRecipient;
import com.cognizant.opserv.sp.core.entity.TRptTargetRecipientId;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TRptTargetRecipientDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tRptTargetRecipientDAO")
public class TRptTargetRecipientDAOImpl implements TRptTargetRecipientDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TRptTargetRecipientDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private static final String TPERS = "tPers";
	private static final String TRPTCONFIG = "tRptConfig";

	private final Class<TRptTargetRecipient> clazz;

	public Class<TRptTargetRecipient> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TRptTargetRecipientDAOImpl() {
		super();
		this.clazz = TRptTargetRecipient.class;
	}

	private static final String JPAQL = "select tRptTargetRecipiententity from TRptTargetRecipient tRptTargetRecipiententity";

	private static final String JPACOUNTQL = "select count(*) from TRptTargetRecipient tRptTargetRecipiententity";

	private static final String[] RESTRICTIONS = {
			"tRptTargetRecipiententity.tRptTargetRecipientId.rptConfigId = #{tRptTargetRecipiententity.tRptTargetRecipientId.getRptConfigId}",
			"tRptTargetRecipiententity.tRptTargetRecipientId.staffId = #{tRptTargetRecipiententity.tRptTargetRecipientId.getStaffId}",
			"tRptTargetRecipiententity.activeFlag = #{tRptTargetRecipiententity.getActiveFlag}",
			"tRptTargetRecipiententity.tenantId = #{tRptTargetRecipiententity.getTenantId}",
			"tRptTargetRecipiententity.createdBy = #{tRptTargetRecipiententity.getCreatedBy}",
			"Date(tRptTargetRecipiententity.createDt) = '#{tRptTargetRecipiententity.getCreateDt}'",
			"tRptTargetRecipiententity.updatedBy = #{tRptTargetRecipiententity.getUpdatedBy}",
			"Date(tRptTargetRecipiententity.updateDt) = '#{tRptTargetRecipiententity.getUpdateDt}'",
			"tRptTargetRecipiententity.tPers.staffId = #{tRptTargetRecipiententity.tPers.getStaffId}",
			"tRptTargetRecipiententity.tRptConfig.rptConfigId = #{tRptTargetRecipiententity.tRptConfig.getRptConfigId}" };

	/**
	 * Stores a new TRptTargetRecipient entity object in to the persistent store
	 * 
	 * @param tRptTargetRecipient
	 *            TRptTargetRecipient Entity object to be persisted
	 * @return tRptTargetRecipient Persisted TRptTargetRecipient object
	 */
	public TRptTargetRecipient createTRptTargetRecipient(final TRptTargetRecipient tRptTargetRecipient) {
		LOGGER.info("=========== Create TRptTargetRecipient ===========");
		return genericDAO.store(tRptTargetRecipient);
	}

	/**
	 * Deletes a TRptTargetRecipient entity object from the persistent store
	 * 
	 * @param tRptTargetRecipient
	 *            TRptTargetRecipient Entity object to be deleted
	 */
	public void deleteTRptTargetRecipient(final TRptTargetRecipientId tRptTargetRecipientId) {
		LOGGER.info("=========== Delete TRptTargetRecipient ===========");
		final TRptTargetRecipient tRptTargetRecipient = genericDAO.get(clazz, tRptTargetRecipientId);
		genericDAO.remove(tRptTargetRecipient);
	}

	/**
	 * Updates a TRptTargetRecipient entity object in to the persistent store
	 * 
	 * @param tRptTargetRecipient
	 *            TRptTargetRecipient Entity object to be updated
	 * @return tRptTargetRecipient Persisted TRptTargetRecipient object
	 */
	public TRptTargetRecipient updateTRptTargetRecipient(final TRptTargetRecipient tRptTargetRecipient) {
		LOGGER.info("=========== Update TRptTargetRecipient ===========");
		return genericDAO.update(tRptTargetRecipient);
	}

	/**
	 * Retrieve an TRptTargetRecipient object based on given TRptTargetRecipient TRptTargetRecipientId.
	 * 
	 * @param tRptTargetRecipientId
	 *            the primary key value of the TRptTargetRecipient Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TRptTargetRecipient findTRptTargetRecipientById(final TRptTargetRecipientId tRptTargetRecipientId) {
		LOGGER.info("find TRptTargetRecipient instance with TRptTargetRecipientId: " + tRptTargetRecipientId);
		return genericDAO.get(clazz, tRptTargetRecipientId);
	}

	/**
	 * Retrieve TRptTargetRecipient based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRptTargetRecipient> list of TRptTargetRecipient if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TRptTargetRecipient> findTRptTargetRecipients(final SearchFilter<TRptTargetRecipient> searchFilter) {
		LOGGER.info("=========== Find TRptTargetRecipients ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TRptTargetRecipient tRptTargetRecipient = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tRptTargetRecipiententity", tRptTargetRecipient);

		if (tRptTargetRecipient.getTPers() == null) {
			jpaQuery.bind(TPERS, new TPers());
		} else {
			LOGGER.info("tPers {}"+ tRptTargetRecipient.getTPers());

			jpaQuery.bind(TPERS, tRptTargetRecipient.getTPers());
		}

		if (tRptTargetRecipient.getTRptConfig() == null) {
			jpaQuery.bind(TRPTCONFIG, new TRptConfig());
		} else {
			LOGGER.info("tRptConfig {}"+ tRptTargetRecipient.getTRptConfig());

			jpaQuery.bind(TRPTCONFIG, tRptTargetRecipient.getTRptConfig());
		}
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TRptTargetRecipients.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTRptTargetRecipients(final SearchFilter<TRptTargetRecipient> searchFilter) {
		LOGGER.info("=========== Count TRptTargetRecipient ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TRptTargetRecipient tRptTargetRecipient = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tRptTargetRecipiententity", tRptTargetRecipient);

		if (tRptTargetRecipient.getTPers() == null) {
			jpaCountQuery.bind(TPERS, new TPers());
		} else {
			LOGGER.info("tPers {}"+ tRptTargetRecipient.getTPers());

			jpaCountQuery.bind(TPERS, tRptTargetRecipient.getTPers());
		}

		if (tRptTargetRecipient.getTRptConfig() == null) {
			jpaCountQuery.bind(TRPTCONFIG, new TRptConfig());
		} else {
			LOGGER.info("tRptConfig {}"+ tRptTargetRecipient.getTRptConfig());

			jpaCountQuery.bind(TRPTCONFIG, tRptTargetRecipient.getTRptConfig());
		}

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TRptTargetRecipient based on given search criteria using JPA named Query.
	 * The search criteria is of TPers type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRptTargetRecipient> list of TRptTargetRecipients if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TRptTargetRecipient> getTRptTargetRecipientsByTPers(final SearchFilter<TPers> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTRptTargetRecipientByTPers", queryParam, index, maxresult);
	}

	/**
	 * Count TRptTargetRecipient based on given search criteria using JPA named Query.
	 * The search criteria is of TPers type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTRptTargetRecipientsByTPers(final SearchFilter<TPers> searchFilter) {

		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());
		return genericDAO.findEntitiesByNamedQuery("CountTRptTargetRecipientsByTPers", queryParam);
	}

	/**
	 * Retrieve TRptTargetRecipient based on given search criteria using JPA named Query.
	 * The search criteria is of TRptConfig type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRptTargetRecipient> list of TRptTargetRecipients if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TRptTargetRecipient> getTRptTargetRecipientsByTRptConfig(final SearchFilter<TRptConfig> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTRptTargetRecipientByTRptConfig", queryParam, index, maxresult);
	}

	/**
	 * Count TRptTargetRecipient based on given search criteria using JPA named Query.
	 * The search criteria is of TRptConfig type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTRptTargetRecipientsByTRptConfig(final SearchFilter<TRptConfig> searchFilter) {

		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());
		return genericDAO.findEntitiesByNamedQuery("CountTRptTargetRecipientsByTRptConfig", queryParam);
	}
	
	@Override
	public List<Integer> findTargetRecipientsByConfigId(Integer rptConfigId, Short tenantId) {
		List<Object> list = new ArrayList<Object>();
		list.add(rptConfigId);
		list.add(tenantId);
		
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTargetRecipientsByConfigId", list, 0, -1); 
	}

}
