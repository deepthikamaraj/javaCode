package com.cognizant.opserv.sp.core.dao;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TChngReqUsrNoteConfig;
import com.cognizant.opserv.sp.core.entity.TChngReqUsrNoteConfigId;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TChngReqUsrNoteConfigDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tChngReqUsrNoteConfigDAO")
public class TChngReqUsrNoteConfigDAOImpl implements TChngReqUsrNoteConfigDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TChngReqUsrNoteConfigDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private final Class<TChngReqUsrNoteConfig> clazz;

	public Class<TChngReqUsrNoteConfig> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TChngReqUsrNoteConfigDAOImpl() {
		super();
		this.clazz = TChngReqUsrNoteConfig.class;
	}

	private static final String JPAQL = "select tChngReqUsrNoteConfigentity from TChngReqUsrNoteConfig tChngReqUsrNoteConfigentity";

	private static final String JPACOUNTQL = "select count(*) from TChngReqUsrNoteConfig tChngReqUsrNoteConfigentity";

	private static final String[] RESTRICTIONS = {
			"tChngReqUsrNoteConfigentity.tChngReqUsrNoteConfigId.chngReqConfigId = #{tChngReqUsrNoteConfigentity.tChngReqUsrNoteConfigId.getChngReqConfigId}",
			"tChngReqUsrNoteConfigentity.tChngReqUsrNoteConfigId.usrTypeId = #{tChngReqUsrNoteConfigentity.tChngReqUsrNoteConfigId.getUsrTypeId}",
			"tChngReqUsrNoteConfigentity.notifyFlag = #{tChngReqUsrNoteConfigentity.getNotifyFlag}",
			"tChngReqUsrNoteConfigentity.activeFlag = #{tChngReqUsrNoteConfigentity.getActiveFlag}",
			"tChngReqUsrNoteConfigentity.algmntId = #{tChngReqUsrNoteConfigentity.getAlgmntId}",
			"tChngReqUsrNoteConfigentity.bussUnitId = #{tChngReqUsrNoteConfigentity.getBussUnitId}",
			"tChngReqUsrNoteConfigentity.salesTeamId = #{tChngReqUsrNoteConfigentity.getSalesTeamId}",
			"tChngReqUsrNoteConfigentity.createdBy = #{tChngReqUsrNoteConfigentity.getCreatedBy}",
			"Date(tChngReqUsrNoteConfigentity.createDt) = '#{tChngReqUsrNoteConfigentity.getCreateDt}'",
			"tChngReqUsrNoteConfigentity.updatedBy = #{tChngReqUsrNoteConfigentity.getUpdatedBy}",
			"Date(tChngReqUsrNoteConfigentity.updateDt) = '#{tChngReqUsrNoteConfigentity.getUpdateDt}'",
			"tChngReqUsrNoteConfigentity.tenantId = #{tChngReqUsrNoteConfigentity.getTenantId}" };

	/**
	 * Stores a new TChngReqUsrNoteConfig entity object in to the persistent store
	 * 
	 * @param tChngReqUsrNoteConfig
	 *            TChngReqUsrNoteConfig Entity object to be persisted
	 * @return tChngReqUsrNoteConfig Persisted TChngReqUsrNoteConfig object
	 */
	public TChngReqUsrNoteConfig createTChngReqUsrNoteConfig(final TChngReqUsrNoteConfig tChngReqUsrNoteConfig) {
		LOGGER.info("=========== Create TChngReqUsrNoteConfig ===========");
		return genericDAO.store(tChngReqUsrNoteConfig);
	}

	/**
	 * Deletes a TChngReqUsrNoteConfig entity object from the persistent store
	 * 
	 * @param tChngReqUsrNoteConfig
	 *            TChngReqUsrNoteConfig Entity object to be deleted
	 */
	public void deleteTChngReqUsrNoteConfig(final TChngReqUsrNoteConfigId tChngReqUsrNoteConfigId) {
		LOGGER.info("=========== Delete TChngReqUsrNoteConfig ===========");
		final TChngReqUsrNoteConfig tChngReqUsrNoteConfig = genericDAO.get(clazz, tChngReqUsrNoteConfigId);
		genericDAO.remove(tChngReqUsrNoteConfig);
	}

	/**
	 * Updates a TChngReqUsrNoteConfig entity object in to the persistent store
	 * 
	 * @param tChngReqUsrNoteConfig
	 *            TChngReqUsrNoteConfig Entity object to be updated
	 * @return tChngReqUsrNoteConfig Persisted TChngReqUsrNoteConfig object
	 */
	public TChngReqUsrNoteConfig updateTChngReqUsrNoteConfig(final TChngReqUsrNoteConfig tChngReqUsrNoteConfig) {
		LOGGER.info("=========== Update TChngReqUsrNoteConfig ===========");
		return genericDAO.update(tChngReqUsrNoteConfig);
	}

	/**
	 * Retrieve an TChngReqUsrNoteConfig object based on given TChngReqUsrNoteConfig TChngReqUsrNoteConfigId.
	 * 
	 * @param tChngReqUsrNoteConfigId
	 *            the primary key value of the TChngReqUsrNoteConfig Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TChngReqUsrNoteConfig findTChngReqUsrNoteConfigById(final TChngReqUsrNoteConfigId tChngReqUsrNoteConfigId) {
		LOGGER.info("find TChngReqUsrNoteConfig instance with TChngReqUsrNoteConfigId: " + tChngReqUsrNoteConfigId);
		return genericDAO.get(clazz, tChngReqUsrNoteConfigId);
	}

	/**
	 * Retrieve TChngReqUsrNoteConfig based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TChngReqUsrNoteConfig> list of TChngReqUsrNoteConfig if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TChngReqUsrNoteConfig> findTChngReqUsrNoteConfigs(final SearchFilter<TChngReqUsrNoteConfig> searchFilter) {
		LOGGER.info("=========== Find TChngReqUsrNoteConfigs ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TChngReqUsrNoteConfig tChngReqUsrNoteConfig = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tChngReqUsrNoteConfigentity", tChngReqUsrNoteConfig);
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TChngReqUsrNoteConfigs.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTChngReqUsrNoteConfigs(final SearchFilter<TChngReqUsrNoteConfig> searchFilter) {
		LOGGER.info("=========== Count TChngReqUsrNoteConfig ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TChngReqUsrNoteConfig tChngReqUsrNoteConfig = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tChngReqUsrNoteConfigentity", tChngReqUsrNoteConfig);

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}
	
	/*
	 * Added By 407986 To Fetch User Notification Config Flag By UserTypeIds
	 * */
	/**
	 * Fetch user notification config flag.
	 *
	 * @param configId the config id
	 * @param userTypeId the user type id
	 * @param tenatId the tenat id
	 * @return the list
	 */
	public List<Object[]> fetchUserNotificationConfigFlag(Integer chngreqConfigId,List<Integer> userTypeId,Short tenantId){
		
		List<Object> queryParam=new ArrayList<Object>();
        queryParam.add(userTypeId);
        queryParam.add(chngreqConfigId);
        queryParam.add(tenantId);
        return genericDAO.findEntitiesByNamedQueryMultiCond("FetchUserNotificationConfigFlag", queryParam, 0, -1);
	}
	/**
	 * Gets the chng req usr note config by cr cofig id.
	 *
	 * @param crCofigId the cr cofig id
	 * @param tenantId the tenant id
	 * @return the chng req usr note config by cr cofig id
	 */
	@Override
	public List<TChngReqUsrNoteConfig> getChngReqUsrNoteConfigByCRCofigId(Integer crCofigId, Short tenantId) {
		List<Object> queryParam=new ArrayList<Object>();
        queryParam.add(crCofigId);      
        queryParam.add(tenantId);
        return genericDAO.findEntitiesByNamedQueryMultiCond("getChngReqUsrNoteConfigByCRCofigId", queryParam, 0, -1);
	}

}
