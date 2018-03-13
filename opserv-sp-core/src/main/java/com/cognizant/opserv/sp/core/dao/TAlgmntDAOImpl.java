package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.common.QueryConstants;
import com.cognizant.opserv.sp.core.entity.TAlgmnt;
import com.cognizant.opserv.sp.core.entity.TAlgmntSalesTeam;
import com.cognizant.opserv.sp.core.entity.TAlgmntStatus;
import com.cognizant.opserv.sp.core.entity.TBussUnit;
import com.cognizant.opserv.sp.core.entity.TSalesTeam;
import com.cognizant.peg.core.common.ISearchCriteria;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchCriteria;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TAlgmntDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tAlgmntDAO")
public class TAlgmntDAOImpl implements TAlgmntDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TAlgmntDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private static final String TBUSSUNIT = "tBussUnit";
//	private static final String TALGMNTSTATUS = "tAlgmntStatus";

	private final Class<TAlgmnt> clazz;

	public Class<TAlgmnt> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TAlgmntDAOImpl() {
		super();
		this.clazz = TAlgmnt.class;
	}

	private static final String JPAQL = "select tAlgmntentity from TAlgmnt tAlgmntentity";

	private static final String JPACOUNTQL = "select count(tAlgmntentity) from TAlgmnt tAlgmntentity";

	private static final String[] RESTRICTIONS = { "tAlgmntentity.algmntId = #{tAlgmntentity.getAlgmntId}",
			"tAlgmntentity.algmntName like '#{tAlgmntentity.getAlgmntName}%'",
			"Date(tAlgmntentity.effStartDt) = '#{tAlgmntentity.getEffStartDt}'",
			"Date(tAlgmntentity.effEndDt) = '#{tAlgmntentity.getEffEndDt}'",
			"tAlgmntentity.createdBy = #{tAlgmntentity.getCreatedBy}",
			"Date(tAlgmntentity.createDt) = '#{tAlgmntentity.getCreateDt}'",
			"tAlgmntentity.updatedBy = #{tAlgmntentity.getUpdatedBy}",
			"Date(tAlgmntentity.updateDt) = '#{tAlgmntentity.getUpdateDt}'",
			"tAlgmntentity.tenantId = #{tAlgmntentity.getTenantId}",
			"tAlgmntentity.tBussUnit.bussUnitId = #{tAlgmntentity.tBussUnit.getBussUnitId}",
			"tAlgmntentity.algmntStatusId = #{tAlgmntentity.algmntStatusId}" };

	/**
	 * Stores a new TAlgmnt entity object in to the persistent store
	 * 
	 * @param tAlgmnt
	 *            TAlgmnt Entity object to be persisted
	 * @return tAlgmnt Persisted TAlgmnt object
	 */
	public TAlgmnt createTAlgmnt(final TAlgmnt tAlgmnt) {
		LOGGER.info("=========== Create TAlgmnt ===========");
		return genericDAO.store(tAlgmnt);
	}

	/**
	 * Deletes a TAlgmnt entity object from the persistent store
	 * 
	 * @param tAlgmnt
	 *            TAlgmnt Entity object to be deleted
	 */
	public void deleteTAlgmnt(final Long algmntId) {
		LOGGER.info("=========== Delete TAlgmnt ===========");
		final TAlgmnt tAlgmnt = genericDAO.get(clazz, algmntId);
		genericDAO.remove(tAlgmnt);
	}

	/**
	 * Updates a TAlgmnt entity object in to the persistent store
	 * 
	 * @param tAlgmnt
	 *            TAlgmnt Entity object to be updated
	 * @return tAlgmnt Persisted TAlgmnt object
	 */
	public TAlgmnt updateTAlgmnt(final TAlgmnt tAlgmnt) {
		LOGGER.info("=========== Update TAlgmnt ===========");
		return genericDAO.update(tAlgmnt);
	}

	/**
	 * Retrieve an TAlgmnt object based on given TAlgmnt algmntId.
	 * 
	 * @param tAlgmntId
	 *            the primary key value of the TAlgmnt Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TAlgmnt findTAlgmntById(final Long tAlgmntId) {
		LOGGER.info("find TAlgmnt instance with algmntId: " + tAlgmntId);
		return genericDAO.get(clazz, tAlgmntId);
	}

	/**
	 * Retrieve TAlgmnt based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TAlgmnt> list of TAlgmnt if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TAlgmnt> findTAlgmnts(final SearchFilter<TAlgmnt> searchFilter) {
		LOGGER.info("=========== Find TAlgmnts ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TAlgmnt tAlgmnt = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tAlgmntentity", tAlgmnt);

		if (tAlgmnt.getTBussUnit() == null) {
			jpaQuery.bind(TBUSSUNIT, new TBussUnit());
		} else {
			LOGGER.info("tBussUnit {}"+tAlgmnt.getTBussUnit());

			jpaQuery.bind(TBUSSUNIT, tAlgmnt.getTBussUnit());
		}

		/*if (tAlgmnt.getTAlgmntStatus() == null) {
			jpaQuery.bind(TALGMNTSTATUS, new TAlgmntStatus());
		} else {
			LOGGER.info("tAlgmntStatus {}", tAlgmnt.getTAlgmntStatus());

			jpaQuery.bind(TALGMNTSTATUS, tAlgmnt.getTAlgmntStatus());
		}*/
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TAlgmnts.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTAlgmnts(final SearchFilter<TAlgmnt> searchFilter) {
		LOGGER.info("=========== Count TAlgmnt ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TAlgmnt tAlgmnt = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tAlgmntentity", tAlgmnt);

		if (tAlgmnt.getTBussUnit() == null) {
			jpaCountQuery.bind(TBUSSUNIT, new TBussUnit());
		} else {
			LOGGER.info("tBussUnit {}"+tAlgmnt.getTBussUnit());

			jpaCountQuery.bind(TBUSSUNIT, tAlgmnt.getTBussUnit());
		}

		/*if (tAlgmnt.getTAlgmntStatus() == null) {
			jpaCountQuery.bind(TALGMNTSTATUS, new TAlgmntStatus());
		} else {
			LOGGER.info("tAlgmntStatus {}", tAlgmnt.getTAlgmntStatus());

			jpaCountQuery.bind(TALGMNTSTATUS, tAlgmnt.getTAlgmntStatus());
		}*/

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TAlgmnt based on given search criteria using JPA named Query.
	 * The search criteria is of TBussUnit type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TAlgmnt> list of TAlgmnts if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TAlgmnt> getTAlgmntsByTBussUnit(final SearchFilter<TBussUnit> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TBussUnit tBussUnit = searchFilter.getEntityClass();
		List<Object> tBussUnitList = new ArrayList<Object>();
		tBussUnitList.add(tBussUnit);
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTAlgmntByTBussUnit", tBussUnitList, index, maxresult);
	}

	/**
	 * Count TAlgmnt based on given search criteria using JPA named Query.
	 * The search criteria is of TBussUnit type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTAlgmntsByTBussUnit(final SearchFilter<TBussUnit> searchFilter) {

		final TBussUnit tBussUnit = searchFilter.getEntityClass();
		List<Object> tBussUnitList = new ArrayList<Object>();
		tBussUnitList.add(tBussUnit);
		return genericDAO.findEntitiesByNamedQuery("CountTAlgmntsByTBussUnit", tBussUnitList);
	}

	/**
	 * Retrieve TAlgmnt based on given search criteria using JPA named Query.
	 * The search criteria is of TAlgmntStatus type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TAlgmnt> list of TAlgmnts if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TAlgmnt> getTAlgmntsByTAlgmntStatus(final SearchFilter<TAlgmntStatus> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TAlgmntStatus tAlgmntStatus = searchFilter.getEntityClass();
		List<Object> tAlgmntStatusList = new ArrayList<Object>();
		tAlgmntStatusList.add(tAlgmntStatus.getTAlgmntStatusId().getStatusId());
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTAlgmntByTAlgmntStatus", tAlgmntStatusList, index, maxresult);
	}

	/**
	 * Count TAlgmnt based on given search criteria using JPA named Query.
	 * The search criteria is of TAlgmntStatus type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTAlgmntsByTAlgmntStatus(final SearchFilter<TAlgmntStatus> searchFilter) {

		final TAlgmntStatus tAlgmntStatus = searchFilter.getEntityClass();
		List<Object> tAlgmntStatusList = new ArrayList<Object>();
		tAlgmntStatusList.add(tAlgmntStatus);
		
		return genericDAO.findEntitiesByNamedQuery("CountTAlgmntsByTAlgmntStatus", tAlgmntStatusList);
	}
	
	/**
	 * Find active algmnt.
	 *
	 * @param searchFilter the search filter
	 * @return the list
	 */
	 public List<TAlgmnt> findActiveAlgmnt(SearchFilter<TAlgmnt> searchFilter){
		 
		final PaginationInfo paginationInfo=searchFilter.getPaginationInfo();
		final  TAlgmnt tAlgmnt=searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		
		List paramList = new ArrayList();
		paramList.add(tAlgmnt.getTenantId());
		paramList.add(new Date());
		
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindActiveAlgmntByDate", paramList, index, maxresult);
	 }
	 /**
		 * Gets the all t algmnts by name.
		 *
		 * @param alignmentname the alignmentname
		 * @return the all t algmnts by name
		 */
	 public List<TAlgmnt> getAllTAlgmntsByName(String bizName) {
		 List<Object> bizNameList = new ArrayList<Object>();
		 bizNameList.add(bizName);
		 return genericDAO.findEntitiesByNamedQuery("FindAllTAlgmntsByName", bizNameList);	
	 }
	 
	 /**
		 * Find t algmnts.
		 *
		 * @param id the id
		 * @return the list
		 */
     @Override
     public List<TAlgmnt> findTAlgmnts(Short id){
    	 List<Object> idList = new ArrayList<Object>();
    	 idList.add(id);
    	 return genericDAO.findEntitiesByNamedQueryMultiCond("FindAllTAlgmnts", idList , 0 , -1);
     }
     
     /**
 	 * Find t algmnts by status.
 	 *
 	 * @param params the params
 	 * @param alignmentStatusId the alignment status id
 	 * @return the list
 	 */
     @Override
     public List<TAlgmnt> findTAlgmntsByStatus(List<Object>params,Integer alignmentStatusId){ 
    	 if(alignmentStatusId == 1){
    	return  genericDAO.findEntitiesByNamedQueryMultiCond("FindAllTAlgmntsByStatusIdActive", params, 0, -1);
    	 }else{
    		 return  genericDAO.findEntitiesByNamedQueryMultiCond("FindAllTAlgmntsByStatusIdProposed", params, 0, -1);
    	 }
     }
     /**
 	 * Find t algmntsfor active proposed.
 	 * 
 	 * @param id the id
 	 * @return the list
 	 */
     // Please check the method before use. Mathod arguments are not maching 
     //with the parameters in the named query.
	 @Override
	public List<TAlgmnt> findTAlgmntsforActiveProposed(Short id) {
		 
		 List<Object> idList = new ArrayList<Object>();
		 idList.add(id);
		return genericDAO.findEntitiesByNamedQuery("FindTAlgmntsforActiveProposed", idList);
	}
	/**
	 * Find all active proposed algmnts.
	 *
	 * @param id the id
	 * @return the list
	 */
	@Override
	public List<TAlgmnt> findAllActiveProposedAlgmnts(Short id) {
		 List<Object> idList = new ArrayList<Object>();
		 idList.add(id);
		return genericDAO.findEntitiesByNamedQuery("FindAllActiveProposedAlgmnts", idList);
	}
     
	/**
	 * Method to find present/past/future alignments for a user based on assnmntType,locale,staff and tenant Id's
	 * @param assnmntType
	 * @param localeId
	 * @param stId
	 * @param tenantId
	 * @return list of object[] with each object array containing alName,alStatus,startDate,endDate,salesPosName,salesPosCode,algmntType in that order,
	 * null if no present/past/future alignments found
	 */
	@Override
	public List<Object[]> findAlgmntDetails(String assnmntType,
			String localeId, Integer stId, Short tenantId) {
		List<Object> queryParam = new ArrayList<Object>();
		queryParam.add(stId);
		queryParam.add(tenantId);
		queryParam.add(localeId);
		queryParam.add(new Date());
		Calendar cal=Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		queryParam.add(cal.getTime());
		final StringBuilder query = new StringBuilder();
		query.append(QueryConstants.FINDALGMNTDETAILS);
		if(assnmntType.equals("current")){
			query.append(" and  myTEmpAlgmnt.effStartDt<=?4 and myTEmpAlgmnt.effEndDt>=?5");	
		}
		else if(assnmntType.equals("previous")){
			query.append(" and  myTEmpAlgmnt.effStartDt<?4 and myTEmpAlgmnt.effEndDt<?5");	
		}
		else if(assnmntType.equals("future")){
			query.append(" and  myTEmpAlgmnt.effStartDt>?4 and myTEmpAlgmnt.effEndDt>?5");	
		}
		List<Object[]> custList  = genericDAO.findEntitiesByBuildQueries(query.toString(), queryParam, 0, -1);
		return custList;
	}
	
	/**
	 * Find active algmnt by algmnt id.
	 *
	 * @param searchFilter the search filter
	 * @return the list
	 */
	public List<TAlgmnt> findActiveAlgmntByAlgmntId(SearchFilter<TAlgmnt> searchFilter){
		final PaginationInfo paginationInfo=searchFilter.getPaginationInfo();
		final  TAlgmnt tAlgmnt=searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		
		List paramList = new ArrayList();
		paramList.add(tAlgmnt.getAlgmntId());
		paramList.add(tAlgmnt.getTenantId());
		paramList.add(new Date());
		
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindActiveAlgmntByDateAndAlgId", paramList, index, maxresult);
	}

	/**
	 * Gets the bus rule config.
	 *
	 * @param tenantId the tenant id
	 * @return the bus rule config
	 */
	@Override
	public List<Object[]> getBusRuleConfig(Short tenantId) {
		StringBuilder query = new StringBuilder().append(QueryConstants.ALIGMENT_BUS_RULE_CONFIG);
		List<Object> queryParam = new ArrayList<Object>();
		queryParam.add(tenantId);
		return genericDAO.findEntitiesByBuildQueries(query.toString(), queryParam, 0, -1);		
	}
	
	@Override
	public List<TAlgmnt> findTAlgnmntBySearchCriteria(ISearchCriteria criteria, Short tenantId) {
		EntityManager em = genericDAO.getEntityManagerFromJPA();
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();

		CriteriaQuery<TAlgmnt> criteriaQuery = criteriaBuilder.createQuery(TAlgmnt.class);
		Root<TAlgmnt> alignment = criteriaQuery.from(TAlgmnt.class);
		Join<TAlgmnt,TBussUnit> bUnit = alignment.join("tBussUnit");
		Join<TAlgmnt,TAlgmntSalesTeam> buSaleteam = alignment.join("tAlgmntSalesTeamss");
		Join<TAlgmntSalesTeam,TSalesTeam> alSaleteam = buSaleteam.join("tSalesTeam");

		alignment.fetch("tBussUnit");
		Fetch saleTeamAlignment = alignment.fetch("tAlgmntSalesTeamss");
		saleTeamAlignment.fetch("tSalesTeam");
		// fAlignment.fetch("tAlgmntSalesTeamss").

		String buName = null;
		String alName = null;
		String stName = null;
		Integer alStatusID = null;

		SearchCriteria searchCriteria = (SearchCriteria) criteria;
		if(searchCriteria.getCriteriaObject() != null && searchCriteria.getCriteriaObject().getAttribute()!=null){
			String attribute = searchCriteria.getCriteriaObject().getAttribute();
			if(attribute.equals("bussUnitName")){
				buName = (String) searchCriteria.getCriteriaObject().getValue(); 
			}else if(attribute.equals("algmntName")){
				alName = (String) searchCriteria.getCriteriaObject().getValue();
			}else if(attribute.equals("algmntStatusId")){
				alStatusID = (Integer) searchCriteria.getCriteriaObject().getValue();
			}else if(attribute.equals("salesTeamName")){
				stName = (String) searchCriteria.getCriteriaObject().getValue();
			}

			if(searchCriteria.getOperant() != null){
				for(ISearchCriteria subCriteria :  searchCriteria.getOperant()){
					SearchCriteria search = (SearchCriteria) subCriteria;
					String subAttribute = search.getCriteriaObject().getAttribute();
					if(subAttribute.equals("bussUnitName")){
						buName = (String) search.getCriteriaObject().getValue(); 
					}else if(subAttribute.equals("algmntName")){
						alName = (String) search.getCriteriaObject().getValue();
					}else if(subAttribute.equals("algmntStatusId")){
						alStatusID = Integer.parseInt(search.getCriteriaObject().getValue().toString());
					}else if(subAttribute.equals("salesTeamName")){
						stName = (String) search.getCriteriaObject().getValue();
					}
				} 
			}
		}

		List<Predicate> predicates = new ArrayList<Predicate>();
		
		//ActiveFlag Check
		Predicate buActiveFlag = criteriaBuilder.equal(bUnit.<String>get("activeFlag"),'Y');
		Predicate buSTActiveFlag = criteriaBuilder.equal(buSaleteam.<String>get("activeFlag"),'Y');
		Predicate stActiveFlag = criteriaBuilder.equal(alSaleteam.<String>get("activeFlag"),'Y');

		predicates.add(buActiveFlag);
		predicates.add(buSTActiveFlag);
		predicates.add(stActiveFlag);
		
		//Tenant Id check
		if(tenantId != null){
			Predicate alTenantId = criteriaBuilder.equal(alignment.<String>get("tenantId"),tenantId);
			Predicate buTenantId = criteriaBuilder.equal(bUnit.<String>get("tenantId"),tenantId);
			Predicate buSTTenantId = criteriaBuilder.equal(buSaleteam.<String>get("tenantId"),tenantId);
			Predicate stTenantId = criteriaBuilder.equal(alSaleteam.<String>get("tenantId"),tenantId);
			
			predicates.add(alTenantId);
			predicates.add(buTenantId);
			predicates.add(buSTTenantId);
			predicates.add(stTenantId);
		}

		if(alName != null && !alName.isEmpty()){
			Predicate AlgnName = criteriaBuilder.like(alignment.<String>get("algmntName"), alName);
			predicates.add(AlgnName);
		}
		if(alStatusID != null ){
			Predicate algnStID = criteriaBuilder.equal(alignment.get("algmntStatusId"), alStatusID); 
			predicates.add(algnStID);
		}
		if(buName != null && !buName.isEmpty()){
			Predicate bName = criteriaBuilder.like(bUnit.<String>get("bussUnitName"), buName);
			predicates.add(bName);
		}
		if( stName != null && !stName.isEmpty()){
			Predicate salesTeamName = criteriaBuilder.like(alSaleteam.<String>get("salesTeamName"), stName);
			predicates.add(salesTeamName);
		}

		criteriaQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()])));
		criteriaQuery.select(alignment);

		TypedQuery<TAlgmnt> typedQuery = em.createQuery(criteriaQuery);
		return typedQuery.getResultList();

	}
	
	
	@Override
	public TAlgmnt findAlgmntByAlgmntId(Long algId, Short tenantId) {
		List paramList = new ArrayList();
		paramList.add(algId);
		paramList.add(tenantId);
		
		List<TAlgmnt> algList = genericDAO.findEntitiesByNamedQueryMultiCond("FindAlgmntByAlgId", paramList, 0, -1);
		if(algList!=null&&algList.size()>0)
		{
			return algList.get(0);
		}else return  null;
	}
	
	
}
