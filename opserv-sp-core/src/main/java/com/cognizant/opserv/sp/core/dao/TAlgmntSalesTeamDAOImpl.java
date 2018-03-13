package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.common.QueryConstants;
import com.cognizant.opserv.sp.core.entity.TAlgmnt;
import com.cognizant.opserv.sp.core.entity.TAlgmntSalesTeam;
import com.cognizant.opserv.sp.core.entity.TAlgmntSalesTeamId;
import com.cognizant.opserv.sp.core.entity.TSalesTeam;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TAlgmntSalesTeamDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tAlgmntSalesTeamDAO")
public class TAlgmntSalesTeamDAOImpl implements TAlgmntSalesTeamDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TAlgmntSalesTeamDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private static final String TSALESTEAM = "tSalesTeam";
	private static final String TALGMNT = "tAlgmnt";

	private final Class<TAlgmntSalesTeam> clazz;

	public Class<TAlgmntSalesTeam> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TAlgmntSalesTeamDAOImpl() {
		super();
		this.clazz = TAlgmntSalesTeam.class;
	}

	private static final String JPAQL = "select tAlgmntSalesTeamentity from TAlgmntSalesTeam tAlgmntSalesTeamentity";

	private static final String JPACOUNTQL = "select count(*) from TAlgmntSalesTeam tAlgmntSalesTeamentity";

	private static final String[] RESTRICTIONS = {
			"tAlgmntSalesTeamentity.tAlgmntSalesTeamId.algmntId = #{tAlgmntSalesTeamentity.tAlgmntSalesTeamId.getAlgmntId}",
			"tAlgmntSalesTeamentity.tAlgmntSalesTeamId.bussUnitId = #{tAlgmntSalesTeamentity.tAlgmntSalesTeamId.getBussUnitId}",
			"tAlgmntSalesTeamentity.tAlgmntSalesTeamId.salesTeamId = #{tAlgmntSalesTeamentity.tAlgmntSalesTeamId.getSalesTeamId}",
			"Date(tAlgmntSalesTeamentity.effStartDt) = '#{tAlgmntSalesTeamentity.getEffStartDt}'",
			"Date(tAlgmntSalesTeamentity.effEndDt) = '#{tAlgmntSalesTeamentity.getEffEndDt}'",
			"tAlgmntSalesTeamentity.activeFlag = #{tAlgmntSalesTeamentity.getActiveFlag}",
			"tAlgmntSalesTeamentity.createdBy = #{tAlgmntSalesTeamentity.getCreatedBy}",
			"Date(tAlgmntSalesTeamentity.createDt) = '#{tAlgmntSalesTeamentity.getCreateDt}'",
			"tAlgmntSalesTeamentity.updatedBy = #{tAlgmntSalesTeamentity.getUpdatedBy}",
			"Date(tAlgmntSalesTeamentity.updateDt) = '#{tAlgmntSalesTeamentity.getUpdateDt}'",
			"tAlgmntSalesTeamentity.tenantId = #{tAlgmntSalesTeamentity.getTenantId}",
			"tAlgmntSalesTeamentity.tSalesTeam.tSalesTeamId = #{tAlgmntSalesTeamentity.tSalesTeam.getTSalesTeamId}",
			"tAlgmntSalesTeamentity.tAlgmnt.algmntId = #{tAlgmntSalesTeamentity.tAlgmnt.getAlgmntId}" };

	/**
	 * Stores a new TAlgmntSalesTeam entity object in to the persistent store
	 * 
	 * @param tAlgmntSalesTeam
	 *            TAlgmntSalesTeam Entity object to be persisted
	 * @return tAlgmntSalesTeam Persisted TAlgmntSalesTeam object
	 */
	public TAlgmntSalesTeam createTAlgmntSalesTeam(final TAlgmntSalesTeam tAlgmntSalesTeam) {
		LOGGER.info("=========== Create TAlgmntSalesTeam ===========");
		return genericDAO.store(tAlgmntSalesTeam);
	}

	/**
	 * Deletes a TAlgmntSalesTeam entity object from the persistent store
	 * 
	 * @param tAlgmntSalesTeam
	 *            TAlgmntSalesTeam Entity object to be deleted
	 */
	public void deleteTAlgmntSalesTeam(final TAlgmntSalesTeamId tAlgmntSalesTeamId) {
		LOGGER.info("=========== Delete TAlgmntSalesTeam ===========");
		final TAlgmntSalesTeam tAlgmntSalesTeam = genericDAO.get(clazz, tAlgmntSalesTeamId);
		genericDAO.remove(tAlgmntSalesTeam);
	}

	/**
	 * Updates a TAlgmntSalesTeam entity object in to the persistent store
	 * 
	 * @param tAlgmntSalesTeam
	 *            TAlgmntSalesTeam Entity object to be updated
	 * @return tAlgmntSalesTeam Persisted TAlgmntSalesTeam object
	 */
	public TAlgmntSalesTeam updateTAlgmntSalesTeam(final TAlgmntSalesTeam tAlgmntSalesTeam) {
		LOGGER.info("=========== Update TAlgmntSalesTeam ===========");
		return genericDAO.update(tAlgmntSalesTeam);
	}

	/**
	 * Retrieve an TAlgmntSalesTeam object based on given TAlgmntSalesTeam TAlgmntSalesTeamId.
	 * 
	 * @param tAlgmntSalesTeamId
	 *            the primary key value of the TAlgmntSalesTeam Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TAlgmntSalesTeam findTAlgmntSalesTeamById(final TAlgmntSalesTeamId tAlgmntSalesTeamId) {
		LOGGER.info("find TAlgmntSalesTeam instance with TAlgmntSalesTeamId: " + tAlgmntSalesTeamId);
		return genericDAO.get(clazz, tAlgmntSalesTeamId);
	}

	/**
	 * Retrieve TAlgmntSalesTeam based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TAlgmntSalesTeam> list of TAlgmntSalesTeam if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TAlgmntSalesTeam> findTAlgmntSalesTeams(final SearchFilter<TAlgmntSalesTeam> searchFilter) {
		LOGGER.info("=========== Find TAlgmntSalesTeams ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TAlgmntSalesTeam tAlgmntSalesTeam = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tAlgmntSalesTeamentity", tAlgmntSalesTeam);

		if (tAlgmntSalesTeam.getTSalesTeam() == null) {
			jpaQuery.bind(TSALESTEAM, new TSalesTeam());
		} else {
			LOGGER.info("tSalesTeam {}" + tAlgmntSalesTeam.getTSalesTeam());

			jpaQuery.bind(TSALESTEAM, tAlgmntSalesTeam.getTSalesTeam());
		}

		if (tAlgmntSalesTeam.getTAlgmnt() == null) {
			jpaQuery.bind(TALGMNT, new TAlgmnt());
		} else {
			LOGGER.info("tAlgmnt {}" + tAlgmntSalesTeam.getTAlgmnt());

			jpaQuery.bind(TALGMNT, tAlgmntSalesTeam.getTAlgmnt());
		}
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TAlgmntSalesTeams.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTAlgmntSalesTeams(final SearchFilter<TAlgmntSalesTeam> searchFilter) {
		LOGGER.info("=========== Count TAlgmntSalesTeam ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TAlgmntSalesTeam tAlgmntSalesTeam = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tAlgmntSalesTeamentity", tAlgmntSalesTeam);

		if (tAlgmntSalesTeam.getTSalesTeam() == null) {
			jpaCountQuery.bind(TSALESTEAM, new TSalesTeam());
		} else {
			LOGGER.info("tSalesTeam {}" + tAlgmntSalesTeam.getTSalesTeam());

			jpaCountQuery.bind(TSALESTEAM, tAlgmntSalesTeam.getTSalesTeam());
		}

		if (tAlgmntSalesTeam.getTAlgmnt() == null) {
			jpaCountQuery.bind(TALGMNT, new TAlgmnt());
		} else {
			LOGGER.info("tAlgmnt {}" + tAlgmntSalesTeam.getTAlgmnt());

			jpaCountQuery.bind(TALGMNT, tAlgmntSalesTeam.getTAlgmnt());
		}

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TAlgmntSalesTeam based on given search criteria using JPA named Query.
	 * The search criteria is of TSalesTeam type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TAlgmntSalesTeam> list of TAlgmntSalesTeams if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TAlgmntSalesTeam> getTAlgmntSalesTeamsByTSalesTeam(final SearchFilter<TSalesTeam> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TSalesTeam tSalesTeam = searchFilter.getEntityClass();
		List<Object> tSalesTeamList = new ArrayList<Object>();
		tSalesTeamList.add(searchFilter.getEntityClass());
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTAlgmntSalesTeamByTSalesTeam", tSalesTeamList, index, maxresult);
	}

	/**
	 * Count TAlgmntSalesTeam based on given search criteria using JPA named Query.
	 * The search criteria is of TSalesTeam type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTAlgmntSalesTeamsByTSalesTeam(final SearchFilter<TSalesTeam> searchFilter) {

		final TSalesTeam tSalesTeam = searchFilter.getEntityClass();
		List<Object> tSalesTeamList = new ArrayList<Object>();
		return genericDAO.findEntitiesByNamedQuery("CountTAlgmntSalesTeamsByTSalesTeam", tSalesTeamList);
	}

	/**
	 * Retrieve TAlgmntSalesTeam based on given search criteria using JPA named Query.
	 * The search criteria is of TAlgmnt type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TAlgmntSalesTeam> list of TAlgmntSalesTeams if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TAlgmntSalesTeam> getTAlgmntSalesTeamsByTAlgmnt(final SearchFilter<TAlgmnt> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		List<Object> tAlgmntList = new ArrayList<Object>();
		tAlgmntList.add(searchFilter.getEntityClass());
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTAlgmntSalesTeamByTAlgmnt", tAlgmntList, index, maxresult);
	}

	/**
	 * Count TAlgmntSalesTeam based on given search criteria using JPA named Query.
	 * The search criteria is of TAlgmnt type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTAlgmntSalesTeamsByTAlgmnt(final SearchFilter<TAlgmnt> searchFilter) {

		List<Object> tAlgmntList = new ArrayList<Object>();
		tAlgmntList.add(searchFilter.getEntityClass());
		return genericDAO.findEntitiesByNamedQuery("CountTAlgmntSalesTeamsByTAlgmnt", tAlgmntList);
	}
	/**
	 * Find active algmnts.
	 *
	 * @param searchFilter the search filter
	 * @return the list
	 */
	public List<TAlgmntSalesTeam> findActiveAlgmnts(SearchFilter<TAlgmntSalesTeam> searchFilter){
		final PaginationInfo paginationInfo=searchFilter.getPaginationInfo();
		final  TAlgmntSalesTeam tAlgmntSalesTeam=searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		
		List paramList = new ArrayList();
		
		paramList.add(tAlgmntSalesTeam.getTenantId());
		paramList.add(tAlgmntSalesTeam.getActiveFlag());
		paramList.add(tAlgmntSalesTeam.getEffEndDt());
		
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindActiveAlgmnts", paramList, index, maxresult);
	}
	/**
	 * Find alignment search.
	 *
	 * @param salesTeam the sales team
	 * @param algnName the algn name
	 * @param buName the bu name
	 * @param status the status
	 * @param tenantId the tenant id
	 * @return the list
	 */
	@Override
    public List<TAlgmntSalesTeam> findAlignmentSearch(final String salesTeam, final String algnName, final String buName, final Integer status, final Short tenantId){    
        List<Object> paramList = new ArrayList<Object>();
        paramList.add("%"+salesTeam+"%");
        paramList.add("%"+algnName+"%");
       //paramList.add(status);
        paramList.add("%"+buName+"%");
        paramList.add(tenantId);
		paramList.add(status);
        
        if(status == 1){
        	
        	 List<TAlgmntSalesTeam> list= genericDAO.findEntitiesByNamedQueryMultiCond("FetchAlgmntActive", paramList, 0, -1);
        	 return list;
        } else if(status ==2){
        	
        	 List<TAlgmntSalesTeam> list= genericDAO.findEntitiesByNamedQueryMultiCond("FetchAlgmntInActive", paramList, 0, -1);
        	 return list;
        } else if(status ==3){
        	 List<TAlgmntSalesTeam> list= genericDAO.findEntitiesByNamedQueryMultiCond("FetchAlgmntPropos", paramList, 0, -1);
        	 return list;
        }else{
        	List<TAlgmntSalesTeam> list= genericDAO.findEntitiesByNamedQueryMultiCond("FetchAlgmntSuspended", paramList, 0, -1);
       	    return list;
        }
        
       // List<TAlgmntSalesTeam> list= genericDAO.findEntitiesByNamedQueryMultiCond("FindTAlgmntSalesTeamBySearch", paramList, 0, -1);
       
        //return list;
           
    }

	/*@Override
	public List<TAlgmntSalesTeam> findAlignSearch(final String salesTeam,
			final String algnName,final String buName, final Integer status, final Short tenantId,final List<UserBuStDetails> usrList,int tRoleTypeId,String localeId) {
		List<TAlgmntSalesTeam> list= new ArrayList<TAlgmntSalesTeam>();
		final List<Object> paramList = new ArrayList<Object>();
		paramList.add("%"+salesTeam+"%");
		paramList.add("%"+algnName+"%");
		paramList.add(status);
		paramList.add("%"+buName+"%");
		paramList.add(tenantId);
		paramList.add(localeId);
		if(!usrList.isEmpty()){
			final StringBuilder query = new StringBuilder();
			query.append(QueryConstants.FINDTALGMNTST);
			//NOTE : Step 1
			if(!usrList.isEmpty()){
				query.append(" and (");	
			}
			//NOTE : Step 2
			int startIndex=7;
			for(int i=0;i<usrList.size();i++){
				final String appendPart = "(myTAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId=?"+startIndex+" " +
						"and myTAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId=?"+(startIndex+1)+")";
				query.append(appendPart);
				if(usrList.size()-i!=1){
					query.append(" or ");	
				}		
				startIndex=startIndex+2;
			}
			//NOTE : Step 3
			if(!usrList.isEmpty()){
				query.append(")");	
			}
			for(UserBuStDetails userBuStDetails : usrList){
				paramList.add(userBuStDetails.getBussUnitId());
				paramList.add(userBuStDetails.getSalesTeamId());
			}
			final String finalQuery = query.toString();
			list = genericDAO.findEntitiesByBuildQueries(finalQuery, paramList, 0, -1);
		}else if(tRoleTypeId==AttributeConstants.ROLE_TYPE && usrList.isEmpty()){
			 list = genericDAO.findEntitiesByBuildQueries(QueryConstants.FINDTALGMNTST, paramList, 0, -1);
		 }
		return list;
	}*/
	
	/**
	 * Fetch TAlgmntSalesTeam based on salesTeamId,bussUnitId.
	 * 
	 * @param salesTeamId,bussUnitId
	 * @return List<TAlgmntSalesTeam>
	 */
	public List<TAlgmntSalesTeam> findTAlgmntSalesTeamByTSalesTeamBUIds(long salesTeamId,long bussUnitId){
		List paramList = new ArrayList();
		paramList.add(salesTeamId);
		paramList.add(bussUnitId);
		
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTAlgmntSalesTeamByTSalesTeamBU", paramList, 0, -1);
	}
	/**
	 * Gets the aff alignments.
	 *
	 * @param tenantId the tenant id
	 * @return the aff alignments
	 */
	@Override
	public List<Object[]> getAffAlignments(Short tenantId) {
		StringBuilder query = new StringBuilder().append(QueryConstants.ALIGMENT_IS_AFFILIATED);
		List<Object> queryParam = new ArrayList<Object>();
		queryParam.add(tenantId);
		return genericDAO.findEntitiesByBuildQueries(query.toString(), queryParam, 0, -1);		
	}
	/**
	 * Gets the cR config.
	 *
	 * @param tenantId the tenant id
	 * @return the cR config
	 */
	@Override
	public List<Object[]> getCRConfig(Short tenantId) {
		StringBuilder query = new StringBuilder().append(QueryConstants.ALIGMENT_CR_CONFIG);
		List<Object> queryParam = new ArrayList<Object>();
		queryParam.add(tenantId);
		return genericDAO.findEntitiesByBuildQueries(query.toString(), queryParam, 0, -1);		
	}
	
	/**
	 * Fetch active algmnts data.
	 *
	 * @param effEdDt the eff ed dt
	 * @param tenantId the tenant id
	 * @return the list
	 */
	@Override
	public List<Object[]> fetchActiveAlgmntsData(Date effEdDt,Short tenantId){
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(effEdDt);
		paramList.add(tenantId);
		
		return genericDAO.findEntitiesByNamedQueryMultiCond("FetchActiveAlgmntsData", paramList, 0, -1);
	}
}
