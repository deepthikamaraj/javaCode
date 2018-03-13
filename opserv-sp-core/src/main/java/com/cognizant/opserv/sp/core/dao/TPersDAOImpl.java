package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.common.QueryConstants;
import com.cognizant.opserv.sp.core.entity.TPers;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TPersDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tPersDAO")
public class TPersDAOImpl implements TPersDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TPersDAOImpl.class);
	
	private final static String COUNT_QUERY = "select count(tpers) from TPers tpers ";
	private final static String LIST_QUERY = "select tpers from TPers tpers";

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private final Class<TPers> clazz;

	public Class<TPers> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TPersDAOImpl() {
		super();
		this.clazz = TPers.class;
	}

	private static final String JPAQL = "select tPersentity from TPers tPersentity";

	private static final String JPACOUNTQL = "select count(tPersentity) from TPers tPersentity";

	private static final String[] RESTRICTIONS = { "tPersentity.staffId = #{tPersentity.getStaffId}",
			"tPersentity.firstName like '#{tPersentity.getFirstName}%'",
			"tPersentity.middleName like '#{tPersentity.getMiddleName}%'",
			"tPersentity.lastName like '#{tPersentity.getLastName}%'",
			"tPersentity.title like '#{tPersentity.getTitle}%'",
			//"tPersentity.birthDtStr like '#{tPersentity.getBirthDtStr}%'",
			//"Date(tPersentity.birthDt) = '#{tPersentity.getBirthDt}'",
			"tPersentity.managerId = #{tPersentity.getManagerId}",
			"tPersentity.createdBy = #{tPersentity.getCreatedBy}",
			"Date(tPersentity.createDt) = '#{tPersentity.getCreateDt}'",
			"tPersentity.updatedBy = #{tPersentity.getUpdatedBy}",
			"Date(tPersentity.updateDt) = '#{tPersentity.getUpdateDt}'",
			"tPersentity.tenantId = #{tPersentity.getTenantId}",
			"tPersentity.clientId = '#{tPersentity.getClientId}'",
			"tPersentity.iattainId like '#{tPersentity.getIattainId}%'",
			"tPersentity.gender like '#{tPersentity.getGender}%'", "tPersentity.qualId = #{tPersentity.getQualId}",
			"tPersentity.orgName like '#{tPersentity.getOrgName}%'",
			"tPersentity.divCd like '#{tPersentity.getDivCd}%'",
			"tPersentity.jobTitle like '#{tPersentity.getJobTitle}%'",
			"tPersentity.roleName like '#{tPersentity.getRoleName}%'",
			"Date(tPersentity.empDt) = '#{tPersentity.getEmpDt}'", "tPersentity.statusId = #{tPersentity.getStatusId}",
			"tPersentity.empTypeId = #{tPersentity.getEmpTypeId}",
			"tPersentity.tUsr.usrId = #{tPersentity.tUsr.getUsrId}" ,
			"tPersentity.tPersContactss.contactDetail like '#{tPersentity.tPersContactss.contactDetail}%'",
			"tPersentity.namePfx like '#{tPersentity.getNamePfx}%'",
			"tPersentity.nameSfx like '#{tPersentity.getNameSfx}%'",
			"Date(tPersentity.termDt) = '#{tPersentity.getTermDt}'" };

	/**
	 * Stores a new TPers entity object in to the persistent store
	 * 
	 * @param tPers
	 *            TPers Entity object to be persisted
	 * @return tPers Persisted TPers object
	 */
	public TPers createTPers(final TPers tPers) {
		LOGGER.info("=========== Create TPers ===========");
		return genericDAO.store(tPers);
		
	}

	/**
	 * Deletes a TPers entity object from the persistent store
	 * 
	 * @param tPers
	 *            TPers Entity object to be deleted
	 */
	public void deleteTPers(final Integer staffId) {
		LOGGER.info("=========== Delete TPers ===========");
		final TPers tPers = genericDAO.get(clazz, staffId);
		genericDAO.remove(tPers);
	}

	/**
	 * Updates a TPers entity object in to the persistent store
	 * 
	 * @param tPers
	 *            TPers Entity object to be updated
	 * @return tPers Persisted TPers object
	 */
	public TPers updateTPers(final TPers tPers) {
		LOGGER.info("=========== Update TPers ===========");

		return genericDAO.update(tPers);
	}

	/**
	 * Retrieve an TPers object based on given TPers staffId.
	 * 
	 * @param tPersId
	 *            the primary key value of the TPers Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TPers findTPersById(final Integer tPersId) {
		LOGGER.info("find TPers instance with staffId: " + tPersId);
		return genericDAO.get(clazz, tPersId);
	}

	/**
	 * Retrieve TPers based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TPers> list of TPers if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TPers> findTPerss(final SearchFilter<TPers> searchFilter) {
		LOGGER.info("=========== Find TPerss ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TPers tPers = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		
		final JPAQuery jpaQuery = new JPAQuery("tPersentity", tPers);

	/*	if (tPers.getTUsr() == null) {
			jpaQuery.bind(TUSR, new TUsr());
		} else {
			LOGGER.info("tUsr {}", tPers.getTUsr());

			jpaQuery.bind(TUSR, tPers.getTUsr());
		}
		*/
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TPerss.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTPerss(final SearchFilter<TPers> searchFilter) {
		LOGGER.info("=========== Count TPers ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TPers tPers = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tPersentity", tPers);

	/*	if (tPers.getTUsr() == null) {
			jpaCountQuery.bind(TUSR, new TUsr());
		} else {
			LOGGER.info("tUsr {}", tPers.getTUsr());

			jpaCountQuery.bind(TUSR, tPers.getTUsr());
		}*/

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}


	@Override
	public List getTPersByNativeQuery(String query) {
		// TODO Auto-generated method stub
		List tPersList = genericDAO.findByNativeQuery(query);
		//return tPersList;
		return tPersList;
	}
	
	public List<TPers> FindTPersByName(final SearchFilter<TPers> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TPers tPers = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		List paramList = new ArrayList();
		paramList.add(tPers.getFirstName());
		paramList.add(tPers.getLastName());
		paramList.add(tPers.getTenantId());
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTPersByName", paramList, index, maxresult);
	}
	public List<TPers> getTPerssByTPers(final SearchFilter<TPers> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TPers tPers = searchFilter.getEntityClass();
                final int  managerId = tPers.getStaffId();
                List<Object> queryParam=new ArrayList<Object> ();
        		queryParam.add(managerId);
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTPersByTPers",queryParam , index, maxresult);
	}
	
	
	public List<Object[]> getTPersByUserId(List<Object> params) {

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTPersByUserId", params, 0, -1);
		
	}
	
	public List<TPers> getTpersByClientId(List<Object> params){
		
		
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTPersByClientId", params, 0, -1);
		
		
	}
	
	
	public List<TPers> getPersById(Integer userId, String localeId, Short tenantId){
		List queryParam = new ArrayList();
		queryParam.add(userId);
		queryParam.add(localeId);
		queryParam.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("getTpersById", queryParam, 0, -1);
	}

	@Override
	public TPers findTPersByUserIdSingle(List<Object> params) {
		TPers tPers = new TPers();
		List<TPers> pers =  genericDAO.findEntitiesByNamedQueryMultiCond("FindTPersByUserId", params, 0, -1);
		tPers.setEmpName(pers.get(0).getEmpName());

		return tPers;
	}

	@Override
	public Long findEmployeeCount(SearchFilter<TPers> filter,
			short tenantId) {
		List paramList = new ArrayList();
		paramList.add(tenantId);
		String finalQuery = COUNT_QUERY+" where tenantId=?1";
		if(filter.getEntityClass().getFirstName()!=null && filter.getEntityClass().getFirstName().trim().length()>0){
			
			filter.getEntityClass().setFirstName(filter.getEntityClass().getFirstName()+"%");
			finalQuery = finalQuery+" and firstName LIKE ?"+(paramList.size()+1);
			paramList.add(filter.getEntityClass().getFirstName());
		}
		if(filter.getEntityClass().getLastName()!=null && filter.getEntityClass().getLastName().trim().length()>0){
			
			
			filter.getEntityClass().setLastName(filter.getEntityClass().getLastName()+"%");
			finalQuery = finalQuery+" and lastName LIKE ?"+(paramList.size()+1);
			paramList.add(filter.getEntityClass().getLastName());
		}
		if(filter.getEntityClass().getClientId()!=null && filter.getEntityClass().getClientId().trim().length()>0){
			
			filter.getEntityClass().setClientId(filter.getEntityClass().getClientId()+"%");
			finalQuery = finalQuery+" and clientId LIKE ?"+(paramList.size()+1);
			paramList.add(filter.getEntityClass().getClientId());
		}
		if(filter.getEntityClass().getStatusId()!=null){
			
			finalQuery = finalQuery+" and statusId=?"+(paramList.size()+1);
			paramList.add(filter.getEntityClass().getStatusId());
		}
		if(filter.getEntityClass().getQualId()!=null){
			finalQuery = finalQuery+" and qualId=?"+(paramList.size()+1);
			paramList.add(filter.getEntityClass().getQualId());
		}
		if(filter.getEntityClass().getGender()!=null && filter.getEntityClass().getGender().trim().length()>0){
			finalQuery = finalQuery+" and gender=?"+(paramList.size()+1);
			paramList.add(filter.getEntityClass().getGender());
		}
		if(filter.getEntityClass().getBirthDt()!=null){
			finalQuery = finalQuery+" and birthDt=?"+(paramList.size()+1);
			paramList.add(filter.getEntityClass().getBirthDt());
		}
		if(filter.getEntityClass().getOrgName()!=null && filter.getEntityClass().getOrgName().trim().length()>0){
			finalQuery = finalQuery+" and orgName=?"+(paramList.size()+1);
			paramList.add(filter.getEntityClass().getOrgName());
		}
		if(filter.getEntityClass().getJobTitle()!=null && filter.getEntityClass().getJobTitle().trim().length()>0){
			finalQuery = finalQuery+" and jobTitle=?"+(paramList.size()+1);
			paramList.add(filter.getEntityClass().getJobTitle());
		}
		if(filter.getEntityClass().getRoleName()!=null && filter.getEntityClass().getRoleName().trim().length()>0){
			finalQuery = finalQuery+" and roleName=?"+(paramList.size()+1);
			paramList.add(filter.getEntityClass().getRoleName());
		}
		if(filter.getEntityClass().getDivCd()!=null && filter.getEntityClass().getDivCd().trim().length()>0){
			finalQuery = finalQuery+" and divCd=?"+(paramList.size()+1);
			paramList.add(filter.getEntityClass().getDivCd());
		}	
		if(filter.getEntityClass().getEmpTypeId()!=null){
			finalQuery = finalQuery+" and empTypeId=?"+(paramList.size()+1);
			paramList.add(filter.getEntityClass().getEmpTypeId());
		}
		if(filter.getEntityClass().getEmpDt()!=null){
			finalQuery = finalQuery+" and empDt=?"+(paramList.size()+1);
			paramList.add(filter.getEntityClass().getEmpDt());
		}
		
		return (Long) genericDAO.findEntitiesByBuildQueries(finalQuery, paramList, 0, -1).get(0);
	}
	
	
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<TPers> findEmployeeList(SearchFilter<TPers> filter,Short tenantId,int start,int size) {
		List paramList = new ArrayList();
		paramList.add(tenantId);
		String finalQuery = LIST_QUERY+" where tenantId=?1";
         if(filter.getEntityClass().getFirstName()!=null && filter.getEntityClass().getFirstName().trim().length()>0){
			
        	filter.getEntityClass().setFirstName("%"+filter.getEntityClass().getFirstName()+"%");
        	finalQuery = finalQuery+" and firstName LIKE ?"+(paramList.size()+1);
			paramList.add(filter.getEntityClass().getFirstName());
		}
		if(filter.getEntityClass().getLastName()!=null && filter.getEntityClass().getLastName().trim().length()>0){
			
			filter.getEntityClass().setLastName("%"+filter.getEntityClass().getLastName()+"%");
			finalQuery = finalQuery+" and lastName LIKE ?"+(paramList.size()+1);
			paramList.add(filter.getEntityClass().getLastName());
		}
		if(filter.getEntityClass().getClientId()!=null && filter.getEntityClass().getClientId().trim().length()>0){
			
			filter.getEntityClass().setClientId("%"+filter.getEntityClass().getClientId()+"%");
			finalQuery = finalQuery+" and clientId LIKE ?"+(paramList.size()+1);
			paramList.add(filter.getEntityClass().getClientId());
		}
		if(filter.getEntityClass().getStatusId()!=null){
			finalQuery = finalQuery+" and statusId=?"+(paramList.size()+1);
			paramList.add(filter.getEntityClass().getStatusId());
		}
		if(filter.getEntityClass().getQualId()!=null){
			finalQuery = finalQuery+" and qualId=?"+(paramList.size()+1);
			paramList.add(filter.getEntityClass().getQualId());
		}
		if(filter.getEntityClass().getGender()!=null && filter.getEntityClass().getGender().trim().length()>0){
			finalQuery = finalQuery+" and gender=?"+(paramList.size()+1);
			paramList.add(filter.getEntityClass().getGender());
		}
		if(filter.getEntityClass().getBirthDt()!=null){
			finalQuery = finalQuery+" and birthDt=?"+(paramList.size()+1);
			paramList.add(filter.getEntityClass().getBirthDt());
		}
		if(filter.getEntityClass().getOrgName()!=null && filter.getEntityClass().getOrgName().trim().length()>0){
			finalQuery = finalQuery+" and orgName=?"+(paramList.size()+1);
			paramList.add(filter.getEntityClass().getOrgName());
		}
		if(filter.getEntityClass().getJobTitle()!=null && filter.getEntityClass().getJobTitle().trim().length()>0){
			finalQuery = finalQuery+" and jobTitle=?"+(paramList.size()+1);
			paramList.add(filter.getEntityClass().getJobTitle());
		}
		if(filter.getEntityClass().getRoleName()!=null && filter.getEntityClass().getRoleName().trim().length()>0){
			finalQuery = finalQuery+" and roleName=?"+(paramList.size()+1);
			paramList.add(filter.getEntityClass().getRoleName());
		}
		if(filter.getEntityClass().getDivCd()!=null && filter.getEntityClass().getDivCd().trim().length()>0){
			finalQuery = finalQuery+" and divCd=?"+(paramList.size()+1);
			paramList.add(filter.getEntityClass().getDivCd());
		}	
		if(filter.getEntityClass().getEmpTypeId()!=null){
			finalQuery = finalQuery+" and empTypeId=?"+(paramList.size()+1);
			paramList.add(filter.getEntityClass().getEmpTypeId());
		}
		if(filter.getEntityClass().getEmpDt()!=null){
			finalQuery = finalQuery+" and empDt=?"+(paramList.size()+1);
			paramList.add(filter.getEntityClass().getEmpDt());
		}
		
		Set<String> sortColumns = filter.getSortInfo().getSortColumns();
		
		if(sortColumns !=null&&sortColumns.size()>0){
			String coulmnName = sortColumns.toArray()[0].toString();
			//SortOrderEnum sortOrder = filter.getSortInfo().getOrder(coulmnName);
			
			
			if(coulmnName!=null && !(coulmnName.equalsIgnoreCase("")))
			{
				String ordBy = "DESC";
				if(filter.getSortInfo().getOrder(coulmnName).toString().equals("ASCENDING")){
					ordBy = "ASC";
				}
			finalQuery = finalQuery+" order by "+coulmnName + " "+ordBy;
			}
		}
		return  genericDAO.findEntitiesByBuildQueries(finalQuery, paramList, start, size);
	}
	
	
	
	@Override
	public String findTPersByUserIdSingleFrComm(List<Object> params) {
		 List<Object> empName = genericDAO.findEntitiesByNamedQueryMultiCond("FindTPersByUserIdFrComm", params, 0, -1);
		 if(null != empName && !empName.isEmpty()){
			 return empName.get(0).toString();
		 }
		return null;
	}
	
	
	/* (non-Javadoc)
	 * @see com.cognizant.opserv.sp.core.dao.TPersDAO#buildQueryFromWhereClause(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.Integer, java.lang.String, java.util.Map, java.lang.Integer, int, int, java.lang.Short)
	 */
	@Override
	public List<Object[]> buildQueryFromWhereClause(String name, String code, String zip,
			String city, Integer type,String state,Map<String,String> attrMap, Integer statusId, int start,
			int results, Short tenantId) {
		
		boolean addedWhere = false;
		
		final StringBuilder query = new StringBuilder();
		query.append(QueryConstants.FINDEMPSFORASSIGNMENT);
		
		//CHECK FOR OTHER ENTITY's
		if( zip!= ""  || city!= "" ||  state!= ""){
			addedWhere  = true;
			if(attrMap != null && attrMap.size() > 0){
				query.append(" , TPersAddr myTPersAddr , TEmpAttr myTEmpAttr WHERE myTPers.staffId =  myTPersAddr.staffId AND myTPers.tenantId =  myTPersAddr.tenantId " +
						" AND myTEmpAttr.tenantId = myTPers.tenantId AND myTEmpAttr.tEmpAttrId.staffId = myTPers.staffId AND " +
						"myTPers.statusId = "+statusId + " AND myTPers.tenantId = " +tenantId + " ");
			}else{
				query.append(" , TPersAddr myTPersAddr WHERE myTPers.staffId =  myTPersAddr.staffId AND myTPers.tenantId =  myTPersAddr.tenantId ");
			}
		}else if(attrMap != null && attrMap.size() > 0){
			addedWhere  = true;
			query.append(" , TEmpAttr myTEmpAttr WHERE myTEmpAttr.tenantId = myTPers.tenantId AND myTEmpAttr.tEmpAttrId.staffId = myTPers.staffId AND " +
					" myTPers.statusId = "+statusId + " AND myTPers.tenantId = " +tenantId + " ");
		}
		
		if(!addedWhere){
			query.append(" WHERE myTPers.statusId = "+statusId + " AND myTPers.tenantId = " +tenantId);
			addedWhere  = true;
		}else{
			query.append(" AND myTPers.statusId = "+statusId + " AND myTPers.tenantId = " +tenantId);
		}
		
		//FOR EMPLOYEE NAME
		if(name != ""){
			if(addedWhere){
				query.append(" AND (myTPers.firstName like '%"+name+"%' OR myTPers.middleName like '%"+name+"%' OR myTPers.lastName like '%"+name+"%' ) ");
			}
		}
		//FOR EMPLOYEE CODE
		if(code != ""){
			if(addedWhere){
				query.append(" AND myTPers.clientId like '%"+code+"%' ");
			}
		}
		//FOR EMPLOYEE TYPE
		if(type != -1){
			if(!addedWhere){
				query.append(" AND myTPers.empTypeId = "+type + " ");
			}
			
		}
		//FOR EMPLOYEE ZIP
		if(zip != ""){
			if(addedWhere){
				query.append(" AND ");
			}
			query.append(" myTPersAddr.postalCd like '%"+zip+"%' ");
		}
		//FOR EMPLOYEE CITY
		if(city != ""){
			if(addedWhere){
				query.append(" AND ");
			}
			query.append(" myTPersAddr.city like '%"+city+"%' ");
		}
		//FOR EMPLOYEE STATE
		if(state != ""){
			if(addedWhere){
				query.append(" AND ");
			}
			query.append(" myTPersAddr.state like '%"+state+"%' ");
		}
		
		//FOR EXTENDED ATTRIBUTES
		if(attrMap != null && attrMap.size() > 0){
			StringBuilder comQuery = query;
			int count = 0;
			for (Map.Entry<String, String> entry : attrMap.entrySet())
			{
			    LOGGER.info(entry.getKey() + "/" + entry.getValue() );
			    if(addedWhere){
					query.append(" AND ");
				}
				query.append(" myTEmpAttr.tEmpAttrId.attrId = "+entry.getKey()+" AND myTEmpAttr.attrValue like '%"+entry.getValue()+"%' " );
				if(attrMap.size() > count){
					query.append(" UNION " + comQuery);
				}
				count++;
			}
		}
		
		LOGGER.info(query.toString());
		
		List<Object[]> list  = genericDAO.findEntitiesByBuildQuery(query.toString(), start, results);
		return list;
		
	}
	
	/* (non-Javadoc)
	 * @see com.cognizant.opserv.sp.core.dao.TPersDAO#findEmpDetails(java.lang.Integer, java.lang.Short, java.lang.String)
	 */
	@Override
	public Object[] findEmpDetails(Integer staffId,Short tenantId,String locale){
	List<Object> queryParam = new ArrayList<Object>();
	queryParam.add(staffId);
	queryParam.add(locale);
	//queryParam.add(tenantId);
	
	List<Object[]> list=genericDAO.findEntitiesByNamedQueryMultiCond("FindTPersDetailsByStaffId",queryParam,0,-1);
	if(list!=null && list.size()>0){
		return list.get(0);
	}
	
	return null;
	}
	@Override
	public TPers findTPersSingleByUserId(List<Object> params) {
		TPers tPers = new TPers();
		List<TPers> pers =  genericDAO.findEntitiesByNamedQueryMultiCond("findTPersSingleByUserId", params, 0, -1);
		tPers.setEmpName(pers.get(0).getEmpName());
		
		return tPers;
	}
	
	@Override
	public List<TPers> findTPersDtlsByUserId(Integer usrId){
		List<Object> queryParam = new ArrayList<Object>();
		queryParam.add(usrId);
		return genericDAO.findEntitiesByNamedQuery("FindTPersDtlsByUserId", queryParam);
	}
	
	@Override
	public List<Object[]> getTPersInfoByUserIds(List<Integer> userIds,Short tenantId) {
		List<Object> params = new ArrayList<Object>();
		params.add(userIds);
		params.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("GetTPersInfoByUserIds", params, 0, -1);		
	}

	@Override
	public TPers findTPersByStaffId(Integer staffId, Short tenantId) {
		List<Object> queryParam = new ArrayList<Object>();
		queryParam.add(staffId);
		queryParam.add(tenantId);
		List<TPers> pers =  genericDAO.findEntitiesByNamedQueryMultiCond("findTPersSingleBystaffId", queryParam, 0, -1);
		
		if(null !=pers && pers.size()>0)
		{
			return pers.get(0);
		}
		return null;
		
	}

	@Override
	public TPers findTPersByUserId(Integer userId, Short tenantId) {
		List<Object> queryParam = new ArrayList<Object>();
		queryParam.add(userId);
		queryParam.add(tenantId);
		List<TPers> pers =  genericDAO.findEntitiesByNamedQueryMultiCond("findTPersSingleByUserId", queryParam, 0, -1);
		
		if(null !=pers && pers.size()>0)
		{
			return pers.get(0);
		}
		return null;
	}
	
	@Override
	public List<TPers> findChngReqApprovers(Long chngReqId,Character targetAprFlg,Short tenantId,Integer allocTypeId) {
		
		List<Object> queryParam = new ArrayList<Object>();
		queryParam.add(chngReqId);
		queryParam.add(targetAprFlg);
		queryParam.add(allocTypeId);
		queryParam.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindChngReqApprovers", queryParam, 0, -1);
	}

	@Override
	public List<TPers> findTPersBySalesPosId(Long spId, Integer allocTypeId,
			Short tenantId) {
		List<Object> queryParam = new ArrayList<Object>();
		queryParam.add(spId);
		queryParam.add(allocTypeId);
		queryParam.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTPersBySalesPosId", queryParam, 0, -1);
	}
}
