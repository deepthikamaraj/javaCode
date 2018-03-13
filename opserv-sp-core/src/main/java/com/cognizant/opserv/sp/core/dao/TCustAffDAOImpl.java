package com.cognizant.opserv.sp.core.dao;


import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.common.QueryConstants;
import com.cognizant.opserv.sp.core.entity.TAlgmntSalesTeam;
import com.cognizant.opserv.sp.core.entity.TCust;
import com.cognizant.opserv.sp.core.entity.TCustAff;
import com.cognizant.opserv.sp.common.util.DateUtil;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TCustAffDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tCustAffDAO")
public class TCustAffDAOImpl implements TCustAffDAO {
	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TCustAffDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private static final String TCUSTBYAFFCUSTID = "tCustByAffCustId";
	private static final String TALGMNTSALESTEAM = "tAlgmntSalesTeam";

	private static final String TCUSTBYCUSTID = "tCustByCustId";

	private final Class<TCustAff> clazz;

	public Class<TCustAff> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TCustAffDAOImpl() {
		super();
		this.clazz = TCustAff.class;
	}

	private static final String JPAQL = "select tCustAffentity from TCustAff tCustAffentity";

	private static final String JPACOUNTQL = "select count(tCustAffentity) from TCustAff tCustAffentity";

	private static final String[] RESTRICTIONS = {
			"tCustAffentity.custAffId = #{tCustAffentity.getCustAffId}",
			"tCustAffentity.activeFlag = #{tCustAffentity.getActiveFlag}",
			"Date(tCustAffentity.affStartDt) = '#{tCustAffentity.getAffStartDt}'",
			"Date(tCustAffentity.affEndDt) = '#{tCustAffentity.getAffEndDt}'",
			"tCustAffentity.createdBy = #{tCustAffentity.getCreatedBy}",
			"Date(tCustAffentity.createDt) = '#{tCustAffentity.getCreateDt}'",
			"tCustAffentity.updatedBy = #{tCustAffentity.getUpdatedBy}",
			"Date(tCustAffentity.updateDt) = '#{tCustAffentity.getUpdateDt}'",
			"tCustAffentity.tenantId = #{tCustAffentity.getTenantId}",
			"tCustAffentity.tCustByAffCustId.custId = #{tCustAffentity.tCustByAffCustId.getCustId}",
			"tCustAffentity.tAlgmntSalesTeam.tAlgmntSalesTeamId = #{tCustAffentity.tAlgmntSalesTeam.getTAlgmntSalesTeamId}",
			"tCustAffentity.prAffFlag = #{tCustAffentity.getPrAffFlag}",
			"tCustAffentity.relTypeId = #{tCustAffentity.getRelTypeId}",
			"tCustAffentity.rootAffCustId = #{tCustAffentity.rootAffCustId}",
			"tCustAffentity.tCustByCustId.custId = #{tCustAffentity.tCustByCustId.getCustId}" };

	/**
	 * Stores a new TCustAff entity object in to the persistent store
	 * 
	 * @param tCustAff
	 *            TCustAff Entity object to be persisted
	 * @return tCustAff Persisted TCustAff object
	 */
	public TCustAff createTCustAff(final TCustAff tCustAff) {
		LOGGER.info("=========== Create TCustAff ===========");
		return genericDAO.store(tCustAff);
	}

	/**
	 * Deletes a TCustAff entity object from the persistent store
	 * 
	 * @param tCustAff
	 *            TCustAff Entity object to be deleted
	 */
	public void deleteTCustAff(final Integer custAffId) {
		LOGGER.info("=========== Delete TCustAff ===========");
		final TCustAff tCustAff = genericDAO.get(clazz, custAffId);
		genericDAO.remove(tCustAff);
	}

	/**
	 * Updates a TCustAff entity object in to the persistent store
	 * 
	 * @param tCustAff
	 *            TCustAff Entity object to be updated
	 * @return tCustAff Persisted TCustAff object
	 */
	public TCustAff updateTCustAff(final TCustAff tCustAff) {
		LOGGER.info("=========== Update TCustAff ===========");
		return genericDAO.update(tCustAff);
	}

	/**
	 * Retrieve an TCustAff object based on given TCustAff custAffId.
	 * 
	 * @param tCustAffId
	 *            the primary key value of the TCustAff Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TCustAff findTCustAffById(final Integer tCustAffId) {
		LOGGER.info("find TCustAff instance with custAffId: " + tCustAffId);
		return genericDAO.get(clazz, tCustAffId);
	}

	/**
	 * Retrieve TCustAff based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCustAff> list of TCustAff if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TCustAff> findTCustAffs(final SearchFilter<TCustAff> searchFilter) {
		LOGGER.info("=========== Find TCustAffs ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TCustAff tCustAff = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
	
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tCustAffentity", tCustAff);

		if (tCustAff.getTCustByAffCustId() == null) {
			jpaQuery.bind(TCUSTBYAFFCUSTID, new TCust());
		} else {
			LOGGER.info("tCustByAffCustId {}" + tCustAff.getTCustByAffCustId());

			jpaQuery.bind(TCUSTBYAFFCUSTID, tCustAff.getTCustByAffCustId());
		}

		if (tCustAff.getTAlgmntSalesTeam() == null) {
			jpaQuery.bind(TALGMNTSALESTEAM, new TAlgmntSalesTeam());
		} else {
			LOGGER.info("tAlgmntSalesTeam {}" + tCustAff.getTAlgmntSalesTeam());

			jpaQuery.bind(TALGMNTSALESTEAM, tCustAff.getTAlgmntSalesTeam());
		}

		

		if (tCustAff.getTCustByCustId() == null) {
			jpaQuery.bind(TCUSTBYCUSTID, new TCust());
		} else {
			LOGGER.info("tCustByCustId {}" + tCustAff.getTCustByCustId());

			jpaQuery.bind(TCUSTBYCUSTID, tCustAff.getTCustByCustId());
		}
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TCustAffs.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTCustAffs(final SearchFilter<TCustAff> searchFilter) {
		LOGGER.info("=========== Count TCustAff ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TCustAff tCustAff = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tCustAffentity", tCustAff);

		if (tCustAff.getTCustByAffCustId() == null) {
			jpaCountQuery.bind(TCUSTBYAFFCUSTID, new TCust());
		} else {
			LOGGER.info("tCustByAffCustId {}" + tCustAff.getTCustByAffCustId());

			jpaCountQuery.bind(TCUSTBYAFFCUSTID, tCustAff.getTCustByAffCustId());
		}

		if (tCustAff.getTAlgmntSalesTeam() == null) {
			jpaCountQuery.bind(TALGMNTSALESTEAM, new TAlgmntSalesTeam());
		} else {
			LOGGER.info("tAlgmntSalesTeam {}" + tCustAff.getTAlgmntSalesTeam());

			jpaCountQuery.bind(TALGMNTSALESTEAM, tCustAff.getTAlgmntSalesTeam());
		}


		if (tCustAff.getTCustByCustId() == null) {
			jpaCountQuery.bind(TCUSTBYCUSTID, new TCust());
		} else {
			LOGGER.info("tCustByCustId {}" + tCustAff.getTCustByCustId());

			jpaCountQuery.bind(TCUSTBYCUSTID, tCustAff.getTCustByCustId());
		}

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TCustAff based on given search criteria using JPA named Query.
	 * The search criteria is of TCustByAffCustId type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCustAff> list of TCustAffs if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TCustAff> getTCustAffsByTCustByAffCustId(final SearchFilter<TCust> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TCust tCust = searchFilter.getEntityClass();
		List<Object> tCustList = new ArrayList<Object>();
		tCustList.add(tCust);
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTCustAffByTCustByAffCustId", tCustList, index, maxresult);
	}

	/**
	 * Count TCustAff based on given search criteria using JPA named Query.
	 * The search criteria is of TCust type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTCustAffsByTCustByAffCustId(final SearchFilter<TCust> searchFilter) {

		final TCust tCust = searchFilter.getEntityClass();
		List<Object> tCustList = new ArrayList<Object>();
		tCustList.add(tCust);
		return genericDAO.findEntitiesByNamedQuery("CountTCustAffsByTCustByAffCustId", tCustList);
	}

	/**
	 * Retrieve TCustAff based on given search criteria using JPA named Query.
	 * The search criteria is of TAlgmntSalesTeam type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCustAff> list of TCustAffs if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TCustAff> getTCustAffsByTAlgmntSalesTeam(final SearchFilter<TAlgmntSalesTeam> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TAlgmntSalesTeam tAlgmntSalesTeam = searchFilter.getEntityClass();
		List<Object> tAlgmntSalesTeamList = new ArrayList<Object>();
		tAlgmntSalesTeamList.add(tAlgmntSalesTeam);
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO
				.findEntitiesByNamedQueryMultiCond("FindTCustAffByTAlgmntSalesTeam", tAlgmntSalesTeamList, index, maxresult);
	}

	/**
	 * Count TCustAff based on given search criteria using JPA named Query.
	 * The search criteria is of TAlgmntSalesTeam type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTCustAffsByTAlgmntSalesTeam(final SearchFilter<TAlgmntSalesTeam> searchFilter) {

		final TAlgmntSalesTeam tAlgmntSalesTeam = searchFilter.getEntityClass();
		List<Object> tAlgmntSalesTeamList = new ArrayList<Object>();
		tAlgmntSalesTeamList.add(tAlgmntSalesTeam);
		return genericDAO.findEntitiesByNamedQuery("CountTCustAffsByTAlgmntSalesTeam", tAlgmntSalesTeamList);
	}

	

	/**
	 * Retrieve TCustAff based on given search criteria using JPA named Query.
	 * The search criteria is of TCustByCustId type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCustAff> list of TCustAffs if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TCustAff> getTCustAffsByTCustByCustId(final SearchFilter<TCust> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TCust tCust = searchFilter.getEntityClass();
		List<Object> tCustList = new ArrayList<Object>();
		tCustList.add(tCust);
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTCustAffByTCustByCustId", tCustList, index, maxresult);
	}

	/**
	 * Count TCustAff based on given search criteria using JPA named Query.
	 * The search criteria is of TCust type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTCustAffsByTCustByCustId(final SearchFilter<TCust> searchFilter) {

		final TCust tCust = searchFilter.getEntityClass();
		List<Object> tCustList = new ArrayList<Object>();
		tCustList.add(tCust);
		return genericDAO.findEntitiesByNamedQuery("CountTCustAffsByTCustByCustId", tCustList);
	}

	/**
	 * Retrieve TCustAff based on given search criteria using JPA named Query.
	 * The search criteria is of TCust type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCustAff> list of TCustAffs if it exists against given
	 *         criteria. Returns null if not found
	 */
	@Override
	public List<TCustAff> getTCustAffsByTAlgmntSalesTeamAndTCustByCustId(Integer algmntId, Integer businessUnitId, Integer salesTeamId, Integer customerId,Character activeFlag){
        List<Object> queryParam = new ArrayList<Object>();
        queryParam.add((long)algmntId);
        queryParam.add((long)businessUnitId);
        queryParam.add((long)salesTeamId);
        queryParam.add(customerId);
        queryParam.add(activeFlag);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTCustAffsByTAlgmntSalesTeamAndTCustByCustId", queryParam , 0, -1);
	}
	
	
	/**
	 * Retrieve TCustAff based on given search criteria using JPA named Query.
	 * The search criteria is of TCust type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCustAff> list of TCustAffs if it exists against given
	 *         criteria. Returns null if not found
	 */
	@Override
	public List<TCustAff> getTCustAffsByTAlgmntSalesTeamAndTCustByAffCustId(Integer algmntId, Integer businessUnitId, Integer salesTeamId, Integer customerId,Character activeFlag){
		  List<Object> queryParam = new ArrayList<Object>();
	      queryParam.add((long)algmntId);
	      queryParam.add((long)businessUnitId);
	      queryParam.add((long)salesTeamId);
	      queryParam.add(customerId);
	      queryParam.add(activeFlag);
	      return genericDAO.findEntitiesByNamedQueryMultiCond("FindTCustAffsByTAlgmntSalesTeamAndTCustByAffCustId", queryParam , 0, -1);
	}
	/**
	 * Retrieve an True or False based on given  custId.
	 * 
	 * @param custId
	 *            the primary key value of the TCustAff Entity.
	 * @return True or False if it exists against given primary key. 
	 */
	@Override
	public Boolean checkAffiliation(Integer custId, Long algmntId,Long bussUnitId, Long salesTeamId, Short tenantId) {
		List<Object> queryParam = new ArrayList<Object>();
		queryParam.add(custId);
		queryParam.add(algmntId);
		queryParam.add(bussUnitId);
		queryParam.add(salesTeamId);
		queryParam.add(tenantId);
		List<Object> affDtls = genericDAO.findEntitiesByNamedQueryMultiCond("getAffiliationDtl", queryParam, 0, -1);
		if(affDtls.size()>0)
			return true;
		else
			return false;
	}
	/**
	 * Retrieve an TCustAff object based on given TCustAff custId.
	 * 
	 * @param custId
	 *            the primary key value of the TCustAff Entity.
	 * @return List of  AffDetails if it exists against given primary key. Returns null of
	 *         not found
	 */
	@Override
	public List<TCustAff> getTCustAffs(Integer custId,Long algmntId, Long bussUnitId, Long salesTeamId, Short tenantId){
		List<Object> queryParam = new ArrayList<Object>();
		queryParam.add(custId);
		queryParam.add(algmntId);
		queryParam.add(bussUnitId);
		queryParam.add(salesTeamId);
		queryParam.add(tenantId);
		List<TCustAff> affDtls = genericDAO.findEntitiesByNamedQueryMultiCond("getAffiliationDtl", queryParam, 0, -1);
		return affDtls;
	}
	/**
	 * Retrieve an TCustAff object based on given TCustAff custId.
	 * 
	 * @param custId
	 *            the primary key value of the TCustAff Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	@Override
	public List<TCustAff> getTCustAffsCallPlan(Integer custId,Long algmntId, Long bussUnitId, Long salesTeamId, Short tenantId,Long salesHierId,Long salesPosId){
		List<Object> queryParam = new ArrayList<Object>();
		queryParam.add(custId);
		queryParam.add(algmntId);
		queryParam.add(bussUnitId);
		queryParam.add(salesTeamId);
		queryParam.add(tenantId);
		queryParam.add(salesHierId);
		queryParam.add(salesPosId);
		queryParam.add(DateUtil.getCurrentDate());
		List<TCustAff> affDtls = genericDAO.findEntitiesByNamedQueryMultiCond("getAffiliationDtlCallPlan", queryParam, 0, -1);
		return affDtls;
	}
	/**
	 * Retrieve an TCustAff object based on given TCustAff custId.
	 * 
	 * @param custId
	 *            the primary key value of the TCustAff Entity.
	 * @return List of  AffDetails if it exists against given primary key. Returns null of
	 *         not found
	 */
	@Override
	public List<Object[]> checkTCustAffHeirFrCust(List<Integer> custIdList, Short tenantId) {
		List<Object> queryParam = new ArrayList<Object>();
		queryParam.add(custIdList);
		queryParam.add(tenantId);
		
		List<Object[]> custAffDtlList  = genericDAO.findEntitiesByNamedQueryMultiCond("checkTCustAffHeirFrCust", queryParam, 0, -1);
		return custAffDtlList;
	}
	/**
	 * Retrieve an count based on given TCustAff custId.
	 * 
	 * @param custId
	 *            the primary key value of the TCustAff Entity.
	 * @return  count if it exists against given primary key. Returns null of
	 *         not found
	 */
	@Override
	public Object countTCustAffByAffCustId(Integer custId,
			Character activeFlag, Date affStartDt, Date affEndDt, Short tenantId) {
		List<Object> queryParam = new ArrayList<Object>();
		queryParam.add(custId);
		queryParam.add(activeFlag);
		queryParam.add(affStartDt);
		queryParam.add(affEndDt);
		queryParam.add(tenantId);
		
		
		Object cnt  = genericDAO.countEntitiesByNamedQueryMultiCond("CountTCustAffByCustId", queryParam, 0, -1);
		return cnt;
	}
	/**
	 * Retrieve an count based on given TCustAff custId.
	 * 
	 * @param custId
	 *            the primary key value of the TCustAff Entity.
	 * @return  count if it exists against given primary key. Returns null of
	 *         not found
	 */
	@Override
	public Object countTCustAffByCustId(Integer custId,
			Character activeFlag, Date affStartDt, Date affEndDt, Short tenantId) {
		List<Object> queryParam = new ArrayList<Object>();
		queryParam.add(custId);
		queryParam.add(activeFlag);
		queryParam.add(affStartDt);
		queryParam.add(affEndDt);
		queryParam.add(tenantId);
		
		Object cnt  = genericDAO.countEntitiesByNamedQueryMultiCond("CountTCustAffByAffCustId", queryParam, 0, -1);
		return cnt;
	}
	/**
	 * Retrieve an CustsForAff object based on given TCustAff custId.
	 * 
	 * @param custId
	 *            the primary key value of the TCustAff Entity.
	 * @return custList if it exists against given primary key. Returns null of
	 *         not found
	 */
	
	@Override
	public List<Object[]> findCustsForAff(String custName, String custCd,
			String postalCd, String city, Integer custTypeId, Date createDt,
			Integer custId, Character activeFlag, Date effStartDt,
			Date effEndDt, Short tenantId, int index, int maxresult) throws ParseException {
		boolean addedWhere = false;
		
		List<Object> queryParam = new ArrayList<Object>();
		
		final StringBuilder query = new StringBuilder();
		query.append(QueryConstants.FINDCUSTSFORAFF);
		
		if( postalCd != ""  || city != "" ){
			addedWhere  = true;
			query.append(", TCustAddr myTCustAddr where myTCust.tenantId = myTCustAddr.tenantId and myTCust.custId = myTCustAddr.custId " +
					" and myTCustAddr.activeFlag = '"+activeFlag+"'" +
					" and myTCustAddr.prAddrFlag =  '"+activeFlag+"'");
			
			if(postalCd != ""){
				query.append(" and myTCustAddr.postalCd like '%"+postalCd+"%' ");
			}
			if(city != ""){
				query.append(" and myTCustAddr.city like '%"+city+"%' ");
			}
		}
		
		if(custName != ""){
			if(addedWhere){
				query.append(" and myTCust.custName like '%"+custName+"%' ");
			}else{
				addedWhere  = true;
				query.append(" where myTCust.custName like '%"+custName+"%' ");
			}
		}
		
		if(custCd != ""){
			if(addedWhere){
				query.append(" and myTCust.custCd like '%"+custCd+"%' ");
			}else{
				addedWhere  = true;
				query.append(" where myTCust.custCd like '%"+custCd+"%' ");
			}
		}
		
		if(addedWhere){
			query.append(" and myTCust.custTypeId = ?1 and myTCust.custId not in " +
					"(select myTCustAff.tCustByAffCustId.custId from TCustAff myTCustAff where myTCustAff.tCustByCustId.custId = ?2 " +
					"and myTCustAff.activeFlag = ?3 and myTCustAff.affStartDt <= ?4 and " +
							"(myTCustAff.affEndDt is null or myTCustAff.affEndDt > ?5 ) ) " +
					"and myTCust.tenantId = ?6 ");
		}else{
			query.append(" where myTCust.custTypeId = ?1 and myTCust.custId not in " +
					"(select myTCustAff.tCustByAffCustId.custId from TCustAff myTCustAff where myTCustAff.tCustByCustId.custId = ?2 " +
					"and myTCustAff.activeFlag = ?3 and myTCustAff.affStartDt <= ?4 and " +
							"(myTCustAff.affEndDt is null or myTCustAff.affEndDt > ?5 ) ) " +
					"and myTCust.tenantId = ?6 ");	
		}
		
		queryParam.add(custTypeId);
		queryParam.add(custId);
		queryParam.add(activeFlag);
		queryParam.add(effStartDt);
		queryParam.add(effEndDt);
		queryParam.add(tenantId);
		
		if(createDt != null){
			String str = DateUtil.getDefaultFormat(createDt, null);
			Date dateBefore = DateUtil.getDefaultDateTimeFormat(str.concat(" 00:00:00"), null);
			Date dateAfter =DateUtil.getDefaultDateTimeFormat(str.concat(" 23:59:59"), null);
			if(addedWhere){
				query.append(" and myTCust.createDt between ?7  and ?8 ");	
			}else{
				addedWhere  = true;
				query.append(" and myTCust.createDt between ?7  and ?8 ");	
			}
			queryParam.add(dateBefore);
			queryParam.add(dateAfter);
		}
		
		List<Object[]> custList  = genericDAO.findEntitiesByBuildQueries(query.toString(), queryParam, index, maxresult);
		return custList;
	}
	
	/**
	 * Retrieve an TCustAff object based on given TCustAff customerId.
	 * 
	 * @param customerId
	 *            the primary key value of the TCustAff Entity.
	 * @return  List<TCustAff> if it exists against given primary key. Returns null of
	 *         not found
	 */
	@Override
	public List<TCustAff> findTCustAffsByCustId(Integer customerId,Character activeFlag,Short tenantId){
        List<Object> queryParam = new ArrayList<Object>();
        queryParam.add(customerId);
        queryParam.add(activeFlag);
        queryParam.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTCustAffsByCustId", queryParam , 0, -1);
	}
	/**
	 * Find t cust data by cust id.
	 *
	 * @param localeId the locale id
	 * @param custId the cust id
	 * @param activeFlag the active flag
	 * @param tenantId the tenant id
	 * @return the list
	 */
	@Override
	public List<Object[]> findTCustDataByCustId(String localeId, Integer custId,
			Character activeFlag, Short tenantId){
		List<Object> paramList = new ArrayList<Object>();		
		paramList.add(localeId);
		paramList.add(custId);
		paramList.add(activeFlag);
		paramList.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTCustDataByCustId", paramList, 0, -1);
	}
	/**
	 * Find t cust data by aff cust id.
	 *
	 * @param localeId the locale id
	 * @param custId the cust id
	 * @param activeFlag the active flag
	 * @param tenantId the tenant id
	 * @return the list
	 */
	@Override
	public List<Object[]> findTCustDataByAffCustId(String localeId, Integer custId,
			Character activeFlag, Short tenantId) {
		List<Object> paramList = new ArrayList<Object>();		
		paramList.add(localeId);
		paramList.add(custId);
		paramList.add(activeFlag);
		paramList.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTCustDataByAffCustId", paramList, 0, -1);
	}
	
	/**
	 * Find t cust aff by cust id.
	 *
	 * @param custId the cust id
	 * @param activeFlag the active flag
	 * @param tenantId the tenant id
	 * @param curDate the cur date
	 * @param localeId the locale id
	 * @return the list
	 */
	@Override
	public List<Object[]> findTCustAffByCustId(Integer custId,
			Character activeFlag,  Short tenantId, Date curDate, String localeId) {
		
		List<Object> queryParam = new ArrayList<Object>();
		queryParam.add(custId);
		queryParam.add(activeFlag);
		queryParam.add(tenantId);
		queryParam.add(curDate);
		queryParam.add(localeId);
		
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTCustAffByCustId", queryParam, 0, -1);
	}
	/**
	 * Find t cust aff by aff cust id.
	 *
	 * @param custId the cust id
	 * @param activeFlag the active flag
	 * @param tenantId the tenant id
	 * @param curDate the cur date
	 * @param localeId the locale id
	 * @return the list
	 */
	@Override
	public List<Object[]> findTCustAffByAffCustId(Integer custId,
			Character activeFlag,  Short tenantId, Date curDate, String localeId) {
		
		List<Object> queryParam = new ArrayList<Object>();
		queryParam.add(custId);
		queryParam.add(activeFlag);
		queryParam.add(tenantId);
		queryParam.add(curDate);
		queryParam.add(localeId);
		
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTCustAffByAffCustId", queryParam, 0, -1);
	}
	
	//Newly Added
	/**
	 * Retrieve TCustAff based on given search criteria using JPA named Query.
	 * The search criteria is of TCust type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCustAff> list of TCustAffs if it exists against given
	 *         criteria. Returns null if not found
	 */
	/*@Override
	public List<TCustAff> getTCustAffsByAlignmentAndCustId(Long algmntId, Long businessUnitId, Long salesTeamId, Integer customerId, Character activeFlag, Date curDate, Short tenantId){
        List<Object> queryParam = new ArrayList<Object>();
        queryParam.add(algmntId);
        queryParam.add(businessUnitId);
        queryParam.add(salesTeamId);
        queryParam.add(customerId);
        queryParam.add(activeFlag);
        queryParam.add(tenantId);
        queryParam.add(curDate);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTCustAffsByAlignmentAndCustId", queryParam , 0, -1);
	}*/
	
	
	/**
	 * Retrieve TCustAff based on given search criteria using JPA named Query.
	 * The search criteria is of TCust type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCustAff> list of TCustAffs if it exists against given
	 *         criteria. Returns null if not found
	 */
	/*@Override
	public List<TCustAff> getTCustAffsByAlignmentAndAffCustId(Long algmntId, Long businessUnitId, Long salesTeamId, Integer customerId,Character activeFlag, Date curDate, Short tenantId){
		  List<Object> queryParam = new ArrayList<Object>();
	      queryParam.add(algmntId);
	      queryParam.add(businessUnitId);
	      queryParam.add(salesTeamId);
	      queryParam.add(customerId);
	      queryParam.add(activeFlag);
	      queryParam.add(tenantId);
	      queryParam.add(curDate);
	      return genericDAO.findEntitiesByNamedQueryMultiCond("FindTCustAffsByAlignmentAndAffCustId", queryParam , 0, -1);
	}*/
	
	/**
	 * Retrieve an TCustAff object based on given TCustAff custId.
	 * 
	 * @param custId
	 *            the primary key value of the TCustAff Entity.
	 * @return List of  AffDetails if it exists against given primary key. Returns null of
	 *         not found
	 */
	@Override
	public List<Integer> findAccountTCustAffsForTCust(Integer customerId, Short tenantId) {
		List<Object> queryParam = new ArrayList<Object>();
		queryParam.add(customerId);
		queryParam.add(tenantId);	
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindAccountTCustAffsForTCust", queryParam, 0, -1);			
	}
	
	/**
	 * Retrieve an TCustAff object based on given TCustAff customerId.
	 * 
	 * @param customerId
	 *            the primary key value of the TCustAff Entity.
	 * @return  List<TCustAff> if it exists against given primary key. Returns null of
	 *         not found
	 */
	@Override
	public List<TCustAff> findTCustAffsForAffCustId(Integer customerId, Character activeFlag, Short tenantId, Date curDate){
        List<Object> queryParam = new ArrayList<Object>();
        queryParam.add(customerId);
        queryParam.add(activeFlag);
        queryParam.add(tenantId);
        queryParam.add(curDate);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTCustAffsForAffCustId", queryParam , 0, -1);
	}
	
	/**
	 * Retrieve an TCustAff object based on given TCustAff customerId.
	 * 
	 * @param customerId
	 *            the primary key value of the TCust Entity.
	 * @return  List<TCustAff> if it exists against given key. Returns null of
	 *         not found
	 */
	@Override
	public List<TCustAff> findTCustAffsForCustId(Integer customerId, Character activeFlag, Short tenantId, Date curDate){
        List<Object> queryParam = new ArrayList<Object>();
        queryParam.add(customerId);
        queryParam.add(activeFlag);
        queryParam.add(tenantId);
        queryParam.add(curDate);
        return  genericDAO.findEntitiesByNamedQueryMultiCond("FindTCustAffForCustId", queryParam , 0, -1);
	}
	
	/**
	 * Retrieve an List of TCustAff object based on given TCustAff customerId.
	 * 
	 * @param customerId
	 *            the primary key value of the TCust Entity.
	 * @return  List<TCustAff> if it exists against given key. Returns null of
	 *         not found
	 */
	@Override
	public List<Integer> findRootAffIdsForCustId(Integer customerId, Short tenantId){
		List<Object> queryParam = new ArrayList<Object>();
        queryParam.add(customerId);
        queryParam.add(tenantId);
        return genericDAO.findEntitiesByNamedQueryMultiCond("FetchRootAffIdsForCustId", queryParam , 0, -1);
	}
	
	/**
	 * Retrieve an List of TCustAff object based on given TCustAff customerId.
	 * 
	 * @param customerId
	 *            the primary key value of the TCust Entity.
	 * @return  List<TCustAff> if it exists against given key. Returns null of
	 *         not found
	 */
	@Override
	public List<Integer> findRootAffIdsForCustIdAndAlignment(Integer customerId, Long algmntId, Long businessUnitId, Long salesTeamId, Short tenantId){
		List<Object> queryParam = new ArrayList<Object>();
        queryParam.add(customerId);
        queryParam.add(algmntId);
	    queryParam.add(businessUnitId);
	    queryParam.add(salesTeamId);
        queryParam.add(tenantId);
        return genericDAO.findEntitiesByNamedQueryMultiCond("FetchRootAffIdsForCustIdAndAlignment", queryParam , 0, -1);
	}
	
	/**
	 * Retrieve an List of TCustAff object based on given TCustAff customerId.
	 * 
	 * @param customerId
	 *            the primary key value of the TCust Entity.
	 * @return  List<TCustAff> if it exists against given key. Returns null of
	 *         not found
	 */
	@Override
	public List<TCustAff> findTCustAffsForRootAffId(Integer rootAffId, Short tenantId){
		List<Object> queryParam = new ArrayList<Object>();
        queryParam.add(rootAffId);
        queryParam.add(tenantId);
        return genericDAO.findEntitiesByNamedQueryMultiCond("FetchTCustAffsForRootAffId", queryParam , 0, -1);
	}
	
	/**
	 * Find AccountTCustAffsForCustId for custId.
	 *
	 * @param customerId the customer id
	 * @param activeFlag the active flag
	 * @param tenantId the tenant id
	 * @param curDate the cur date
	 * @return the list
	 */
	@Override
	public List<TCustAff> findAccountTCustAffsForCustId(Integer customerId, Short tenantId, Date curDate){
        List<Object> queryParam = new ArrayList<Object>();
        queryParam.add(customerId);
        queryParam.add(tenantId);
        queryParam.add(curDate);
        return genericDAO.findEntitiesByNamedQueryMultiCond("FetchAccountTCustAffs", queryParam , 0, -1);
	}
	
}
