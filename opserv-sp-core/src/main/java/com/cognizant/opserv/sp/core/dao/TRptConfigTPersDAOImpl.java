package com.cognizant.opserv.sp.core.dao;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TPers;
import com.cognizant.opserv.sp.core.entity.TRptConfig;
import com.cognizant.opserv.sp.core.entity.TRptConfigTPers;
import com.cognizant.opserv.sp.core.entity.TRptConfigTPersId;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TRptConfigTPersDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tRptConfigTPersDAO")
public class TRptConfigTPersDAOImpl implements TRptConfigTPersDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TRptConfigTPersDAOImpl.class);

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

	private final Class<TRptConfigTPers> clazz;

	public Class<TRptConfigTPers> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TRptConfigTPersDAOImpl() {
		super();
		this.clazz = TRptConfigTPers.class;
	}

	private static final String JPAQL = "select tRptConfigTPersentity from TRptConfigTPers tRptConfigTPersentity";

	private static final String JPACOUNTQL = "select count(*) from TRptConfigTPers tRptConfigTPersentity";

	private static final String[] RESTRICTIONS = {
			"tRptConfigTPersentity.tRptConfigTPersId.staffId = #{tRptConfigTPersentity.tRptConfigTPersId.getStaffId}",
			"tRptConfigTPersentity.tRptConfigTPersId.rptConfigId = #{tRptConfigTPersentity.tRptConfigTPersId.getRptConfigId}",
			"tRptConfigTPersentity.activeFlag = #{tRptConfigTPersentity.getActiveFlag}",
			"tRptConfigTPersentity.tenantId = #{tRptConfigTPersentity.getTenantId}",
			"tRptConfigTPersentity.tPers.staffId = #{tRptConfigTPersentity.tPers.getStaffId}",
			"tRptConfigTPersentity.tRptConfig.rptConfigId = #{tRptConfigTPersentity.tRptConfig.getRptConfigId}" };

	/**
	 * Stores a new TRptConfigTPers entity object in to the persistent store
	 * 
	 * @param tRptConfigTPers
	 *            TRptConfigTPers Entity object to be persisted
	 * @return tRptConfigTPers Persisted TRptConfigTPers object
	 */
	public TRptConfigTPers createTRptConfigTPers(final TRptConfigTPers tRptConfigTPers) {
		LOGGER.info("=========== Create TRptConfigTPers ===========");
		return genericDAO.store(tRptConfigTPers);
	}

	/**
	 * Deletes a TRptConfigTPers entity object from the persistent store
	 * 
	 * @param tRptConfigTPers
	 *            TRptConfigTPers Entity object to be deleted
	 */
	public void deleteTRptConfigTPers(final TRptConfigTPersId tRptConfigTPersId) {
		LOGGER.info("=========== Delete TRptConfigTPers ===========");
		final TRptConfigTPers tRptConfigTPers = genericDAO.get(clazz, tRptConfigTPersId);
		genericDAO.remove(tRptConfigTPers);
	}

	/**
	 * Updates a TRptConfigTPers entity object in to the persistent store
	 * 
	 * @param tRptConfigTPers
	 *            TRptConfigTPers Entity object to be updated
	 * @return tRptConfigTPers Persisted TRptConfigTPers object
	 */
	public TRptConfigTPers updateTRptConfigTPers(final TRptConfigTPers tRptConfigTPers) {
		LOGGER.info("=========== Update TRptConfigTPers ===========");
		return genericDAO.update(tRptConfigTPers);
	}

	/**
	 * Retrieve an TRptConfigTPers object based on given TRptConfigTPers TRptConfigTPersId.
	 * 
	 * @param tRptConfigTPersId
	 *            the primary key value of the TRptConfigTPers Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TRptConfigTPers findTRptConfigTPersById(final TRptConfigTPersId tRptConfigTPersId) {
		LOGGER.info("find TRptConfigTPers instance with TRptConfigTPersId: " + tRptConfigTPersId);
		return genericDAO.get(clazz, tRptConfigTPersId);
	}

	/**
	 * Retrieve TRptConfigTPers based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRptConfigTPers> list of TRptConfigTPers if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TRptConfigTPers> findTRptConfigTPerss(final SearchFilter<TRptConfigTPers> searchFilter) {
		LOGGER.info("=========== Find TRptConfigTPerss ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TRptConfigTPers tRptConfigTPers = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tRptConfigTPersentity", tRptConfigTPers);

		if (tRptConfigTPers.getTPers() == null) {
			jpaQuery.bind(TPERS, new TPers());
		} else {
			LOGGER.info("tPers {}"+ tRptConfigTPers.getTPers());

			jpaQuery.bind(TPERS, tRptConfigTPers.getTPers());
		}

		if (tRptConfigTPers.getTRptConfig() == null) {
			jpaQuery.bind(TRPTCONFIG, new TRptConfig());
		} else {
			LOGGER.info("tRptConfig {}"+ tRptConfigTPers.getTRptConfig());

			jpaQuery.bind(TRPTCONFIG, tRptConfigTPers.getTRptConfig());
		}
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TRptConfigTPerss.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTRptConfigTPerss(final SearchFilter<TRptConfigTPers> searchFilter) {
		LOGGER.info("=========== Count TRptConfigTPers ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TRptConfigTPers tRptConfigTPers = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tRptConfigTPersentity", tRptConfigTPers);

		if (tRptConfigTPers.getTPers() == null) {
			jpaCountQuery.bind(TPERS, new TPers());
		} else {
			LOGGER.info("tPers {}"+ tRptConfigTPers.getTPers());

			jpaCountQuery.bind(TPERS, tRptConfigTPers.getTPers());
		}

		if (tRptConfigTPers.getTRptConfig() == null) {
			jpaCountQuery.bind(TRPTCONFIG, new TRptConfig());
		} else {
			LOGGER.info("tRptConfig {}"+ tRptConfigTPers.getTRptConfig());

			jpaCountQuery.bind(TRPTCONFIG, tRptConfigTPers.getTRptConfig());
		}

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TRptConfigTPers based on given search criteria using JPA named Query.
	 * The search criteria is of TPers type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRptConfigTPers> list of TRptConfigTPerss if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TRptConfigTPers> getTRptConfigTPerssByTPers(final SearchFilter<TPers> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTRptConfigTPersByTPers", queryParam, index, maxresult);
	}

	/**
	 * Count TRptConfigTPers based on given search criteria using JPA named Query.
	 * The search criteria is of TPers type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTRptConfigTPerssByTPers(final SearchFilter<TPers> searchFilter) {

		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());
		return genericDAO.findEntitiesByNamedQuery("CountTRptConfigTPerssByTPers", queryParam);
	}

	/**
	 * Retrieve TRptConfigTPers based on given search criteria using JPA named Query.
	 * The search criteria is of TRptConfig type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRptConfigTPers> list of TRptConfigTPerss if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TRptConfigTPers> getTRptConfigTPerssByTRptConfig(final SearchFilter<TRptConfig> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTRptConfigTPersByTRptConfig", queryParam, index, maxresult);
	}

	/**
	 * Count TRptConfigTPers based on given search criteria using JPA named Query.
	 * The search criteria is of TRptConfig type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTRptConfigTPerssByTRptConfig(final SearchFilter<TRptConfig> searchFilter) {

		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());
		return genericDAO.findEntitiesByNamedQuery("CountTRptConfigTPerssByTRptConfig", queryParam);
	}

}
