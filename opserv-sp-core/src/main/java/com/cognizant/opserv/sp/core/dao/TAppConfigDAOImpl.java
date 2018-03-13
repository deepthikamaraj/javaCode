package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TAppConfig;
import com.cognizant.opserv.sp.core.entity.TOrg;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TAppConfigDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tAppConfigDAO")
public class TAppConfigDAOImpl implements TAppConfigDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TAppConfigDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private static final String TORG = "tOrg";

	private final Class<TAppConfig> clazz;

	public Class<TAppConfig> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TAppConfigDAOImpl() {
		super();
		this.clazz = TAppConfig.class;
	}

	private static final String JPAQL = "select tAppConfigentity from TAppConfig tAppConfigentity";

	private static final String JPACOUNTQL = "select count(tAppConfigentity) from TAppConfig tAppConfigentity";

	private static final String[] RESTRICTIONS = { "tAppConfigentity.orgId = #{tAppConfigentity.getOrgId}",
			"tAppConfigentity.sessTmout = #{tAppConfigentity.getSessTmout}",
			"tAppConfigentity.createdBy = #{tAppConfigentity.getCreatedBy}",
			"Date(tAppConfigentity.createDt) = '#{tAppConfigentity.getCreateDt}'",
			"tAppConfigentity.updatedBy = #{tAppConfigentity.getUpdatedBy}",
			"Date(tAppConfigentity.updateDt) = '#{tAppConfigentity.getUpdateDt}'",
			"tAppConfigentity.tenantId = #{tAppConfigentity.getTenantId}",
			"tAppConfigentity.tOrg.orgId = #{tAppConfigentity.tOrg.getOrgId}" };

	/**
	 * Stores a new TAppConfig entity object in to the persistent store
	 * 
	 * @param tAppConfig
	 *            TAppConfig Entity object to be persisted
	 * @return tAppConfig Persisted TAppConfig object
	 */
	public TAppConfig createTAppConfig(final TAppConfig tAppConfig) {
		LOGGER.info("=========== Create TAppConfig ===========");
		return genericDAO.store(tAppConfig);
	}

	/**
	 * Deletes a TAppConfig entity object from the persistent store
	 * 
	 * @param tAppConfig
	 *            TAppConfig Entity object to be deleted
	 */
	public void deleteTAppConfig(final Integer orgId) {
		LOGGER.info("=========== Delete TAppConfig ===========");
		final TAppConfig tAppConfig = genericDAO.get(clazz, orgId);
		genericDAO.remove(tAppConfig);
	}

	/**
	 * Updates a TAppConfig entity object in to the persistent store
	 * 
	 * @param tAppConfig
	 *            TAppConfig Entity object to be updated
	 * @return tAppConfig Persisted TAppConfig object
	 */
	public TAppConfig updateTAppConfig(final TAppConfig tAppConfig) {
		LOGGER.info("=========== Update TAppConfig ===========");
		return genericDAO.update(tAppConfig);
	}

	/**
	 * Retrieve an TAppConfig object based on given TAppConfig orgId.
	 * 
	 * @param tAppConfigId
	 *            the primary key value of the TAppConfig Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TAppConfig findTAppConfigById(final Integer tAppConfigId) {
		LOGGER.info("find TAppConfig instance with orgId: " + tAppConfigId);
		return genericDAO.get(clazz, tAppConfigId);
	}

	/**
	 * Retrieve TAppConfig based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TAppConfig> list of TAppConfig if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TAppConfig> findTAppConfigs(final SearchFilter<TAppConfig> searchFilter) {
		LOGGER.info("=========== Find TAppConfigs ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TAppConfig tAppConfig = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tAppConfigentity", tAppConfig);

		if (tAppConfig.getTOrg() == null) {
			jpaQuery.bind(TORG, new TOrg());
		} else {
			LOGGER.info("tOrg {}" + tAppConfig.getTOrg());

			jpaQuery.bind(TORG, tAppConfig.getTOrg());
		}
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TAppConfigs.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTAppConfigs(final SearchFilter<TAppConfig> searchFilter) {
		LOGGER.info("=========== Count TAppConfig ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TAppConfig tAppConfig = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tAppConfigentity", tAppConfig);

		if (tAppConfig.getTOrg() == null) {
			jpaCountQuery.bind(TORG, new TOrg());
		} else {
			LOGGER.info("tOrg {}" + tAppConfig.getTOrg());

			jpaCountQuery.bind(TORG, tAppConfig.getTOrg());
		}

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TAppConfig based on given search criteria using JPA named Query.
	 * The search criteria is of TOrg type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TAppConfig> list of TAppConfigs if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TAppConfig> getTAppConfigsByTOrg(final SearchFilter<TOrg> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		List<Object> tOrgList = new ArrayList<Object>();
		tOrgList.add(searchFilter.getEntityClass());
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTAppConfigByTOrg", tOrgList, index, maxresult);
	}

	/**
	 * Count TAppConfig based on given search criteria using JPA named Query.
	 * The search criteria is of TOrg type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTAppConfigsByTOrg(final SearchFilter<TOrg> searchFilter) {

		final TOrg tOrg = searchFilter.getEntityClass();
		List<Object> tOrgList = new ArrayList<Object>();
		return genericDAO.findEntitiesByNamedQuery("CountTAppConfigsByTOrg", tOrgList);
	}
	/**
	 * Gets the default country id.
	 *
	 * @param tenantId the tenant id
	 * @return the default country id
	 */
	@Override
	public  List<TAppConfig> getDefaultCountryID(Short tenantId) {
		List queryparam = new ArrayList();
		queryparam.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindDefaultCountryID", queryparam, 0, -1);
	}

}
