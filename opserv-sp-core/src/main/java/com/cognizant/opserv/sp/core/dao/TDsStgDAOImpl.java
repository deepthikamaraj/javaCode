package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TDsStatus;
import com.cognizant.opserv.sp.core.entity.TDsStg;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TDsStgDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tDsStgDAO")
public class TDsStgDAOImpl implements TDsStgDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TDsStgDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private static final String TDSSTATUS = "tDsStatus";

	private final Class<TDsStg> clazz;

	public Class<TDsStg> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TDsStgDAOImpl() {
		super();
		this.clazz = TDsStg.class;
	}

	private static final String JPAQL = "select tDsStgentity from TDsStg tDsStgentity";

	private static final String JPACOUNTQL = "select count(tDsStgentity) from TDsStg tDsStgentity";

	private static final String[] RESTRICTIONS = { "tDsStgentity.dsId = #{tDsStgentity.getDsId}",
			"tDsStgentity.dataFileName like '#{tDsStgentity.getDataFileName}%'",
			"tDsStgentity.dsDesc like '#{tDsStgentity.getDsDesc}%'",
			"Date(tDsStgentity.extDtTm) = '#{tDsStgentity.getExtDtTm}'",
			"Date(tDsStgentity.apprDtTm) = '#{tDsStgentity.getApprDtTm}'",
			"Date(tDsStgentity.loadDtTm) = '#{tDsStgentity.getLoadDtTm}'",
			"Date(tDsStgentity.arcDtTm) = '#{tDsStgentity.getArcDtTm}'",
			"tDsStgentity.bussFunction like '#{tDsStgentity.getBussFunction}%'",
			"tDsStgentity.incDataFlag = #{tDsStgentity.getIncDataFlag}",
			"tDsStgentity.fileName like '#{tDsStgentity.getFileName}%'",
			"tDsStgentity.tblName like '#{tDsStgentity.getTblName}%'",
			"tDsStgentity.dataFileLocation like '#{tDsStgentity.getDataFileLocation}%'",
			"Date(tDsStgentity.dataFileImportDtTm) = '#{tDsStgentity.getDataFileImportDtTm}'",
			"tDsStgentity.dsName like '#{tDsStgentity.getDsName}%'",
			"tDsStgentity.rptDsFlag = #{tDsStgentity.getRptDsFlag}",
			"tDsStgentity.createdBy = #{tDsStgentity.getCreatedBy}",
			"Date(tDsStgentity.createDt) = '#{tDsStgentity.getCreateDt}'",
			"tDsStgentity.updatedBy = #{tDsStgentity.getUpdatedBy}",
			"Date(tDsStgentity.updateDt) = '#{tDsStgentity.getUpdateDt}'",
			"tDsStgentity.tenantId = #{tDsStgentity.getTenantId}",
			"tDsStgentity.fileType like '#{tDsStgentity.getFileType}%'",
			"tDsStgentity.fmtType like '#{tDsStgentity.getFmtType}%'",
			"tDsStgentity.txtDelim like '#{tDsStgentity.getTxtDelim}%'",
			"tDsStgentity.errAction = #{tDsStgentity.getErrAction}",
			"tDsStgentity.tDsStatus.statusId = #{tDsStgentity.tDsStatus.getStatusId}" };

	/**
	 * Stores a new TDsStg entity object in to the persistent store
	 * 
	 * @param tDsStg
	 *            TDsStg Entity object to be persisted
	 * @return tDsStg Persisted TDsStg object
	 */
	public TDsStg createTDsStg(final TDsStg tDsStg) {
		LOGGER.info("=========== Create TDsStg ===========");
		return genericDAO.store(tDsStg);
	}

	/**
	 * Deletes a TDsStg entity object from the persistent store
	 * 
	 * @param tDsStg
	 *            TDsStg Entity object to be deleted
	 */
	public void deleteTDsStg(final Long dsId) {
		LOGGER.info("=========== Delete TDsStg ===========");
		final TDsStg tDsStg = genericDAO.get(clazz, dsId);
		genericDAO.remove(tDsStg);
	}

	/**
	 * Updates a TDsStg entity object in to the persistent store
	 * 
	 * @param tDsStg
	 *            TDsStg Entity object to be updated
	 * @return tDsStg Persisted TDsStg object
	 */
	public TDsStg updateTDsStg(final TDsStg tDsStg) {
		LOGGER.info("=========== Update TDsStg ===========");
		return genericDAO.update(tDsStg);
	}

	/**
	 * Retrieve an TDsStg object based on given TDsStg dsId.
	 * 
	 * @param tDsStgId
	 *            the primary key value of the TDsStg Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TDsStg findTDsStgById(final Long tDsStgId) {
		LOGGER.info("find TDsStg instance with dsId: " + tDsStgId);
		return genericDAO.get(clazz, tDsStgId);
	}

	/**
	 * Retrieve TDsStg based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TDsStg> list of TDsStg if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TDsStg> findTDsStgs(final SearchFilter<TDsStg> searchFilter) {
		LOGGER.info("=========== Find TDsStgs ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TDsStg tDsStg = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tDsStgentity", tDsStg);

		if (tDsStg.getTDsStatus() == null) {
			jpaQuery.bind(TDSSTATUS, new TDsStatus());
		} else {
			LOGGER.info("tDsStatus {}"+ tDsStg.getTDsStatus());

			jpaQuery.bind(TDSSTATUS, tDsStg.getTDsStatus());
		}
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TDsStgs.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTDsStgs(final SearchFilter<TDsStg> searchFilter) {
		LOGGER.info("=========== Count TDsStg ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TDsStg tDsStg = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tDsStgentity", tDsStg);

		if (tDsStg.getTDsStatus() == null) {
			jpaCountQuery.bind(TDSSTATUS, new TDsStatus());
		} else {
			LOGGER.info("tDsStatus {}"+ tDsStg.getTDsStatus());

			jpaCountQuery.bind(TDSSTATUS, tDsStg.getTDsStatus());
		}

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TDsStg based on given search criteria using JPA named Query.
	 * The search criteria is of TDsStatus type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TDsStg> list of TDsStgs if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TDsStg> getTDsStgsByTDsStatus(final SearchFilter<TDsStatus> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TDsStatus tDsStatus = searchFilter.getEntityClass();
		List<Object> tDsStatusList = new ArrayList<Object>();
		tDsStatusList.add(tDsStatus);
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTDsStgByTDsStatus", tDsStatusList, index, maxresult);
	}

	/**
	 * Count TDsStg based on given search criteria using JPA named Query.
	 * The search criteria is of TDsStatus type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTDsStgsByTDsStatus(final SearchFilter<TDsStatus> searchFilter) {

		final TDsStatus tDsStatus = searchFilter.getEntityClass();
		List<Object> tDsStatusList = new ArrayList<Object>();
		tDsStatusList.add(tDsStatus);
		return genericDAO.findEntitiesByNamedQuery("CountTDsStgsByTDsStatus", tDsStatusList);
	}
	/**
	 * Find t ds stgs by name.
	 *
	 * @param searchFilter the search filter
	 * @return the list
	 */
	public List<TDsStg> findTDsStgsByName(final SearchFilter<TDsStg> searchFilter){
		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TDsStg tDsStg = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(tDsStg.getTenantId());
		paramList.add(tDsStg.getDsName());

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTDsStgByTDsName", paramList, index, maxresult);
	}

/*	public List<TDsStg> findTDsStgByTenantIdAndDSStatus(final SearchFilter<TDsStg> searchFilter){
		final TDsStg tDsStg = searchFilter.getEntityClass();
		final Short tenantId = tDsStg.getTenantId();
		final Integer dsStatus = tDsStg.getTDsStatus().getStatusId();
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(tenantId);
		paramList.add(dsStatus);
		List<TDsStg> dsStgsList = genericDAO.findEntitiesByNamedQueryMultiCond("FindTDsStgByTenantIdAndDSStatus", paramList, 0, -1);
                                                                                                                                                                                                                                                                                                                                                                                    		return dsStgsList;
	}*/
	/**
	 * Find t ds stg by tenant id and ds status.
	 *
	 * @param tenantId the tenant id
	 * @param statusIdList the status id list
	 * @return the list
	 */
	public List<TDsStg> findTDsStgByTenantIdAndDSStatus(short tenantId, List<Integer> statusIdList){
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(tenantId);
		paramList.add(statusIdList);
		List<TDsStg> dsStgsList = genericDAO.findEntitiesByNamedQueryMultiCond("FindTDsStgByTenantIdAndDSStatus", paramList, 0, -1);
                                                                                                                                                                                                                                                                                                                                                                                    		return dsStgsList;
	}
	
// Created by Sheel to fetch logs in EL:Monitor for last 30 days
	/**
	 * Find t ds stgs created aftr date.
	 *
	 * @param searchFilter the search filter
	 * @param beforeDate the before date
	 * @param createDate the create date
	 * @return the list
	 */
	public List<TDsStg> findTDsStgsCreatedAftrDate(final SearchFilter<TDsStg> searchFilter, Date beforeDate, Date createDate) {
		final TDsStg tDsStg = searchFilter.getEntityClass();
		List<Object> paramList = new ArrayList<Object>();
		
		paramList.add(tDsStg.getTenantId());		
		paramList.add(beforeDate);
		paramList.add(createDate);
		
		List<TDsStg> dsStgsList = genericDAO.findEntitiesByNamedQueryMultiCond("TDsStgsAftrCreateDt", paramList, 0, -1);
		return dsStgsList;
	}
	/**
	 * Find t ds stgs by ds name.
	 *
	 * @param entityName the entity name
	 * @param tenantId the tenant id
	 * @return the list
	 */
	public List<TDsStg> findTDsStgsByDSName(final String entityName, Short tenantId){
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(tenantId);
		paramList.add(entityName);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTDsStgByTDsName", paramList, 0, -1);
	}
	/**
	 * Gets the active data source.
	 *
	 * @param tenantId the tenant id
	 * @return the active data source
	 */
	@Override
	public List<Object[]> getActiveDataSource(Short tenantId) {
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(tenantId);		
		return genericDAO.findEntitiesByNamedQueryMultiCond("GetActiveDs", paramList, 0, -1);
	}
	
/* Added by 407986 For Call Plan Load */
	/**
	 * Update buss function for call plan.
	 *
	 * @param bussFun the buss fun
	 * @param tableName the table name
	 * @param tenantId the tenant id
	 */
	public void updateBussFunctionForCallPlan(String bussFun,String tableName, Short tenantId){
		List<Object> paramList = new ArrayList<Object>();
		
		paramList.add(bussFun);
		paramList.add(tableName);
		paramList.add(tenantId);
		try {
			genericDAO.updateEntitiesByNamedQueryMultiCond(
					"UpadteBussFuntionforCallPlanLoad", paramList, 0, -1);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
