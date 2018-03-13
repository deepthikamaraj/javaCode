package com.cognizant.opserv.sp.core.dao;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TAlgmnt;
import com.cognizant.opserv.sp.core.entity.TAlgmntSalesTeam;
import com.cognizant.opserv.sp.core.entity.TAlgmntTmpl;
import com.cognizant.opserv.sp.core.entity.TBussObjTmpl;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TAlgmntTmplDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tAlgmntTmplDAO")
public class TAlgmntTmplDAOImpl implements TAlgmntTmplDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TAlgmntTmplDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private static final String TBUSSOBJTMPL = "tBussObjTmpl";
	private static final String TALGMNTSALESTEAM = "tAlgmntSalesTeam";

	private final Class<TAlgmntTmpl> clazz;

	public Class<TAlgmntTmpl> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TAlgmntTmplDAOImpl() {
		super();
		this.clazz = TAlgmntTmpl.class;
	}

	private static final String JPAQL = "select tAlgmntTmplentity from TAlgmntTmpl tAlgmntTmplentity";

	private static final String JPACOUNTQL = "select count(tAlgmntTmplentity) from TAlgmntTmpl tAlgmntTmplentity";

	private static final String[] RESTRICTIONS = {
			"tAlgmntTmplentity.algmntTmplId = #{tAlgmntTmplentity.getAlgmntTmplId}",
			"Date(tAlgmntTmplentity.effStartDt) = '#{tAlgmntTmplentity.getEffStartDt}'",
			"Date(tAlgmntTmplentity.effEndDt) = '#{tAlgmntTmplentity.getEffEndDt}'",
			"tAlgmntTmplentity.activeFlag = #{tAlgmntTmplentity.getActiveFlag}",
			"tAlgmntTmplentity.createdBy = #{tAlgmntTmplentity.getCreatedBy}",
			"Date(tAlgmntTmplentity.createDt) = '#{tAlgmntTmplentity.getCreateDt}'",
			"tAlgmntTmplentity.updatedBy = #{tAlgmntTmplentity.getUpdatedBy}",
			"Date(tAlgmntTmplentity.updateDt) = '#{tAlgmntTmplentity.getUpdateDt}'",
			"tAlgmntTmplentity.tenantId = #{tAlgmntTmplentity.getTenantId}",
			"tAlgmntTmplentity.tBussObjTmpl.tmplId = #{tAlgmntTmplentity.tBussObjTmpl.getTmplId}",
			"tAlgmntTmplentity.tAlgmntSalesTeam.tAlgmntSalesTeamId = #{tAlgmntTmplentity.tAlgmntSalesTeam.getTAlgmntSalesTeamId}" };

	/**
	 * Stores a new TAlgmntTmpl entity object in to the persistent store
	 * 
	 * @param tAlgmntTmpl
	 *            TAlgmntTmpl Entity object to be persisted
	 * @return tAlgmntTmpl Persisted TAlgmntTmpl object
	 */
	public TAlgmntTmpl createTAlgmntTmpl(final TAlgmntTmpl tAlgmntTmpl) {
		LOGGER.info("=========== Create TAlgmntTmpl ===========");
		return genericDAO.store(tAlgmntTmpl);
	}

	/**
	 * Deletes a TAlgmntTmpl entity object from the persistent store
	 * 
	 * @param tAlgmntTmpl
	 *            TAlgmntTmpl Entity object to be deleted
	 */
	public void deleteTAlgmntTmpl(final Integer algmntTmplId) {
		LOGGER.info("=========== Delete TAlgmntTmpl ===========");
		final TAlgmntTmpl tAlgmntTmpl = genericDAO.get(clazz, algmntTmplId);
		genericDAO.remove(tAlgmntTmpl);
	}

	/**
	 * Updates a TAlgmntTmpl entity object in to the persistent store
	 * 
	 * @param tAlgmntTmpl
	 *            TAlgmntTmpl Entity object to be updated
	 * @return tAlgmntTmpl Persisted TAlgmntTmpl object
	 */
	public TAlgmntTmpl updateTAlgmntTmpl(final TAlgmntTmpl tAlgmntTmpl) {
		LOGGER.info("=========== Update TAlgmntTmpl ===========");
		return genericDAO.update(tAlgmntTmpl);
	}

	/**
	 * Retrieve an TAlgmntTmpl object based on given TAlgmntTmpl algmntTmplId.
	 * 
	 * @param tAlgmntTmplId
	 *            the primary key value of the TAlgmntTmpl Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TAlgmntTmpl findTAlgmntTmplById(final Integer tAlgmntTmplId) {
		LOGGER.info("find TAlgmntTmpl instance with algmntTmplId: " + tAlgmntTmplId);
		return genericDAO.get(clazz, tAlgmntTmplId);
	}

	/**
	 * Retrieve TAlgmntTmpl based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TAlgmntTmpl> list of TAlgmntTmpl if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TAlgmntTmpl> findTAlgmntTmpls(final SearchFilter<TAlgmntTmpl> searchFilter) {
		LOGGER.info("=========== Find TAlgmntTmpls ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TAlgmntTmpl tAlgmntTmpl = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tAlgmntTmplentity", tAlgmntTmpl);

		if (tAlgmntTmpl.getTBussObjTmpl() == null) {
			jpaQuery.bind(TBUSSOBJTMPL, new TBussObjTmpl());
		} else {
			LOGGER.info("tBussObjTmpl {}" + tAlgmntTmpl.getTBussObjTmpl());

			jpaQuery.bind(TBUSSOBJTMPL, tAlgmntTmpl.getTBussObjTmpl());
		}

		if (tAlgmntTmpl.getTAlgmntSalesTeam() == null) {
			jpaQuery.bind(TALGMNTSALESTEAM, new TAlgmntSalesTeam());
		} else {
			LOGGER.info("tAlgmntSalesTeam {}" + tAlgmntTmpl.getTAlgmntSalesTeam());

			jpaQuery.bind(TALGMNTSALESTEAM, tAlgmntTmpl.getTAlgmntSalesTeam());
		}
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TAlgmntTmpls.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTAlgmntTmpls(final SearchFilter<TAlgmntTmpl> searchFilter) {
		LOGGER.info("=========== Count TAlgmntTmpl ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TAlgmntTmpl tAlgmntTmpl = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tAlgmntTmplentity", tAlgmntTmpl);

		if (tAlgmntTmpl.getTBussObjTmpl() == null) {
			jpaCountQuery.bind(TBUSSOBJTMPL, new TBussObjTmpl());
		} else {
			LOGGER.info("tBussObjTmpl {}" + tAlgmntTmpl.getTBussObjTmpl());

			jpaCountQuery.bind(TBUSSOBJTMPL, tAlgmntTmpl.getTBussObjTmpl());
		}

		if (tAlgmntTmpl.getTAlgmntSalesTeam() == null) {
			jpaCountQuery.bind(TALGMNTSALESTEAM, new TAlgmntSalesTeam());
		} else {
			LOGGER.info("tAlgmntSalesTeam {}" + tAlgmntTmpl.getTAlgmntSalesTeam());

			jpaCountQuery.bind(TALGMNTSALESTEAM, tAlgmntTmpl.getTAlgmntSalesTeam());
		}

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TAlgmntTmpl based on given search criteria using JPA named Query.
	 * The search criteria is of TBussObjTmpl type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TAlgmntTmpl> list of TAlgmntTmpls if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TAlgmntTmpl> getTAlgmntTmplsByTBussObjTmpl(final SearchFilter<TBussObjTmpl> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		List<Object> tBussObjTmplList = new ArrayList<Object>();
		tBussObjTmplList.add(searchFilter.getEntityClass());
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTAlgmntTmplByTBussObjTmpl", tBussObjTmplList, index, maxresult);
	}

	/**
	 * Count TAlgmntTmpl based on given search criteria using JPA named Query.
	 * The search criteria is of TBussObjTmpl type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTAlgmntTmplsByTBussObjTmpl(final SearchFilter<TBussObjTmpl> searchFilter) {

		List<Object> tBussObjTmplList = new ArrayList<Object>(); 
		tBussObjTmplList.add(searchFilter.getEntityClass());
		return genericDAO.findEntitiesByNamedQuery("CountTAlgmntTmplsByTBussObjTmpl", tBussObjTmplList);
	}

	/**
	 * Retrieve TAlgmntTmpl based on given search criteria using JPA named Query.
	 * The search criteria is of TAlgmntSalesTeam type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TAlgmntTmpl> list of TAlgmntTmpls if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TAlgmntTmpl> getTAlgmntTmplsByTAlgmntSalesTeam(final SearchFilter<TAlgmntSalesTeam> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		List<Object> tAlgmntSalesTeamList = new ArrayList<Object>();
		tAlgmntSalesTeamList.add(searchFilter.getEntityClass());
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTAlgmntTmplByTAlgmntSalesTeam", tAlgmntSalesTeamList, index,
				maxresult);
	}

	/**
	 * Count TAlgmntTmpl based on given search criteria using JPA named Query.
	 * The search criteria is of TAlgmntSalesTeam type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTAlgmntTmplsByTAlgmntSalesTeam(final SearchFilter<TAlgmntSalesTeam> searchFilter) {

		List<Object> tAlgmntSalesTeamList= new ArrayList<Object>();
		tAlgmntSalesTeamList.add(searchFilter.getEntityClass());
		
		return genericDAO.findEntitiesByNamedQuery("CountTAlgmntTmplsByTAlgmntSalesTeam", tAlgmntSalesTeamList);
	}
	/**
	 * Find t algnmt tempt id by alg bu sales i ds.
	 *
	 * @param salesTeamId the sales team id
	 * @param algmntId the algmnt id
	 * @param bussUnitId the buss unit id
	 * @param tenantId the tenant id
	 * @return the list
	 */
	@Override
	public List<TAlgmntTmpl> FindTAlgnmtTemptIdByAlgBuSalesIDs(Long salesTeamId, Long algmntId, Long bussUnitId,short tenantId) {
		List<Object> paramList = new ArrayList<Object>();
        paramList.add(salesTeamId);
        paramList.add(algmntId);
        paramList.add(bussUnitId);
        paramList.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTAlgnmtTemptIdByAlgBuSalesIDs",paramList,0,-1);
	}
	/**
	 * Find t algmnt tmpls by t buss obj tmpl.
	 *
	 * @param tBussObjTmpl the t buss obj tmpl
	 * @return the list
	 */
	@Override
	public List<TAlgmntTmpl> findTAlgmntTmplsByTBussObjTmpl(TBussObjTmpl tBussObjTmpl) {
		List<Object> tBussObjTmplList = new ArrayList<Object>();
		tBussObjTmplList.add(tBussObjTmpl);
		return genericDAO.findEntitiesByNamedQuery("FindTAlgmntTmplByTBussObjTmpl", tBussObjTmplList);
	}
	/**
	 * Find t algmnt tmpl by t algmnt.
	 *
	 * @param tAlgmnt the t algmnt
	 * @param tenantId the tenant id
	 * @return the list
	 */
	@Override
	public List<TAlgmntTmpl> findTAlgmntTmplByTAlgmnt(TAlgmnt tAlgmnt,Short tenantId) {
		List<Object> list = new ArrayList<Object>();
		list.add(tAlgmnt);
		list.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTAlgmntTmplByTAlgmnt", list, 0, -1);
//		return genericDAO.findEntitiesByNamedQuery("FindTAlgmntTmplByTAlgmnt", tAlgmnt);
	}
	/**
	 * Find t algnmt tmpl id by al bu st buss obj tenant.
	 *
	 * @param algmntId the algmnt id
	 * @param bussUnitId the buss unit id
	 * @param salesTeamId the sales team id
	 * @param bussEntyName the buss enty name
	 * @param tenantId the tenant id
	 * @return the list
	 */
	public List<TAlgmntTmpl> findTAlgnmtTmplIdByAlBuSTBussObjTenant(long algmntId,long bussUnitId,long salesTeamId,String bussEntyName, Short tenantId){
		List<Object> queryParam = new ArrayList<Object>();
		queryParam.add(algmntId);
		queryParam.add(bussUnitId);
		queryParam.add(salesTeamId);
		queryParam.add(bussEntyName);
		queryParam.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTAlgnmtTmplIdByAlBuSTBussObjTenant", queryParam, 0, -1);
	}
	/**
	 * Find algmnt of template.
	 *
	 * @param tmplId the tmpl id
	 * @param tenantId the tenant id
	 * @param flag the flag
	 * @return the list
	 */
	@Override
	public List<Object[]> findAlgmntOfTemplate(Integer tmplId, Short tenantId,
			Character flag) {
		List<Object> queryParam = new ArrayList<Object>();
		queryParam.add(tmplId);
		queryParam.add(tenantId);
		queryParam.add(flag);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindAlgmntOfTemplate", queryParam, 0, -1);
	}
	/**
	 * Find t algnmt tmpl id by al bu st buss obj.
	 *
	 * @param algmntId the algmnt id
	 * @param bussUnitId the buss unit id
	 * @param salesTeamId the sales team id
	 * @param bussEntyName the buss enty name
	 * @param tenantId the tenant id
	 * @return the list
	 */
	@Override
	public List<Integer> findTAlgnmtTmplIdByAlBuSTBussObj(long algmntId,long bussUnitId,long salesTeamId,String bussEntyName, Short tenantId){
		List<Object> queryParam = new ArrayList<Object>();
		queryParam.add(algmntId);
		queryParam.add(bussUnitId);
		queryParam.add(salesTeamId);
		queryParam.add(bussEntyName);
		queryParam.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTAlgnmtTmplIdByAlBuSTBussObj", queryParam, 0, -1);
	}
	/**
	 * Find t algnmt tmpl id by al bu st buss obj id.
	 *
	 * @param AlId the al id
	 * @param BuId the bu id
	 * @param StId the st id
	 * @param bussObjId the buss obj id
	 * @param tenantId the tenant id
	 * @return the list
	 */
	@Override
	public List<Integer> findTAlgnmtTmplIdByAlBuSTBussObjId(Long algmntId,
			Long bussUnitId, Long salesTeamId, Integer bussObjId, short tenantId) {
		List<Object> queryParam = new ArrayList<Object>();
		queryParam.add(algmntId);
		queryParam.add(bussUnitId);
		queryParam.add(salesTeamId);
		queryParam.add(bussObjId);
		queryParam.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTAlgnmtTmplIdByAlBuSTBussObjId", queryParam, 0, -1);
	}
}
