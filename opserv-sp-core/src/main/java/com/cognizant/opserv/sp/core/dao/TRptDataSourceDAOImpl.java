package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TRptConfig;
import com.cognizant.opserv.sp.core.entity.TRptDataSource;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TRptDataSourceDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tRptDataSourceDAO")
public class TRptDataSourceDAOImpl implements TRptDataSourceDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TRptDataSourceDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private static final String TRPTCONFIG = "tRptConfig";

	private final Class<TRptDataSource> clazz;

	public Class<TRptDataSource> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TRptDataSourceDAOImpl() {
		super();
		this.clazz = TRptDataSource.class;
	}

	private static final String JPAQL = "select tRptDataSourceentity from TRptDataSource tRptDataSourceentity";

	private static final String JPACOUNTQL = "select count(*) from TRptDataSource tRptDataSourceentity";

	private static final String[] RESTRICTIONS = {
			"tRptDataSourceentity.tRptDataSourceId.docLocationId = #{tRptDataSourceentity.tRptDataSourceId.getDocLocationId}",
			"tRptDataSourceentity.tRptDataSourceId.rptConfigId = #{tRptDataSourceentity.tRptDataSourceId.getRptConfigId}",
			"tRptDataSourceentity.docName like '#{tRptDataSourceentity.getDocName}%'",
			"tRptDataSourceentity.docLocation like '#{tRptDataSourceentity.getDocLocation}%'",
			"tRptDataSourceentity.docSuffixFormat like '#{tRptDataSourceentity.getDocSuffixFormat}%'",
			"tRptDataSourceentity.docExtensionFormat like '#{tRptDataSourceentity.getDocExtensionFormat}%'",
			"tRptDataSourceentity.activeFlag = #{tRptDataSourceentity.getActiveFlag}",
			"tRptDataSourceentity.createdBy = #{tRptDataSourceentity.getCreatedBy}",
			"Date(tRptDataSourceentity.createDt) = '#{tRptDataSourceentity.getCreateDt}'",
			"tRptDataSourceentity.updatedBy = #{tRptDataSourceentity.getUpdatedBy}",
			"Date(tRptDataSourceentity.updateDate) = '#{tRptDataSourceentity.getUpdateDate}'",
			"tRptDataSourceentity.tenantId = #{tRptDataSourceentity.getTenantId}",
			"tRptDataSourceentity.tRptConfig.rptConfigId = #{tRptDataSourceentity.tRptConfig.getRptConfigId}",
			"tRptDataSourceentity.dataSrcDesign = #{tRptDataSourceentity.getDataSrcDesign}" };

	/**
	 * Stores a new TRptDataSource entity object in to the persistent store
	 * 
	 * @param tRptDataSource
	 *            TRptDataSource Entity object to be persisted
	 * @return tRptDataSource Persisted TRptDataSource object
	 */
	public TRptDataSource createTRptDataSource(final TRptDataSource tRptDataSource) {
		LOGGER.info("=========== Create TRptDataSource ===========");
		return genericDAO.store(tRptDataSource);
	}

	/**
	 * Deletes a TRptDataSource entity object from the persistent store
	 * 
	 * @param tRptDataSource
	 *            TRptDataSource Entity object to be deleted
	 */
	public void deleteTRptDataSource(final Integer tRptDataSourceId) {
		LOGGER.info("=========== Delete TRptDataSource ===========");
		final TRptDataSource tRptDataSource = genericDAO.get(clazz, tRptDataSourceId);
		genericDAO.remove(tRptDataSource);
	}

	/**
	 * Updates a TRptDataSource entity object in to the persistent store
	 * 
	 * @param tRptDataSource
	 *            TRptDataSource Entity object to be updated
	 * @return tRptDataSource Persisted TRptDataSource object
	 */
	public TRptDataSource updateTRptDataSource(final TRptDataSource tRptDataSource) {
		LOGGER.info("=========== Update TRptDataSource ===========");
		return genericDAO.update(tRptDataSource);
	}

	/**
	 * Retrieve an TRptDataSource object based on given TRptDataSource TRptDataSourceId.
	 * 
	 * @param tRptDataSourceId
	 *            the primary key value of the TRptDataSource Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TRptDataSource findTRptDataSourceById(final Integer tRptDataSourceId) {
		LOGGER.info("find TRptDataSource instance with TRptDataSourceId: " + tRptDataSourceId);
		return genericDAO.get(clazz, tRptDataSourceId);
	}

	/**
	 * Retrieve TRptDataSource based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRptDataSource> list of TRptDataSource if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TRptDataSource> findTRptDataSources(final SearchFilter<TRptDataSource> searchFilter) {
		LOGGER.info("=========== Find TRptDataSources ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TRptDataSource tRptDataSource = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tRptDataSourceentity", tRptDataSource);

		if (tRptDataSource.getTRptConfig() == null) {
			jpaQuery.bind(TRPTCONFIG, new TRptConfig());
		} else {
			LOGGER.info("tRptConfig {}"+ tRptDataSource.getTRptConfig());

			jpaQuery.bind(TRPTCONFIG, tRptDataSource.getTRptConfig());
		}
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TRptDataSources.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTRptDataSources(final SearchFilter<TRptDataSource> searchFilter) {
		LOGGER.info("=========== Count TRptDataSource ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TRptDataSource tRptDataSource = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tRptDataSourceentity", tRptDataSource);

		if (tRptDataSource.getTRptConfig() == null) {
			jpaCountQuery.bind(TRPTCONFIG, new TRptConfig());
		} else {
			LOGGER.info("tRptConfig {}"+ tRptDataSource.getTRptConfig());

			jpaCountQuery.bind(TRPTCONFIG, tRptDataSource.getTRptConfig());
		}

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TRptDataSource based on given search criteria using JPA named Query.
	 * The search criteria is of TRptConfig type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRptDataSource> list of TRptDataSources if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TRptDataSource> getTRptDataSourcesByTRptConfig(final SearchFilter<TRptConfig> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTRptDataSourceByTRptConfig", queryParam, index, maxresult);
	}

	/**
	 * Count TRptDataSource based on given search criteria using JPA named Query.
	 * The search criteria is of TRptConfig type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTRptDataSourcesByTRptConfig(final SearchFilter<TRptConfig> searchFilter) {

		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());
		return genericDAO.findEntitiesByNamedQuery("CountTRptDataSourcesByTRptConfig", queryParam);
	}

}
