package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TCust;
import com.cognizant.opserv.sp.core.entity.TCustAddr;
import com.cognizant.opserv.sp.core.entity.TCustCategory;
import com.cognizant.opserv.sp.core.entity.TCustType;
import com.cognizant.opserv.sp.core.entity.TPrtType;
import com.cognizant.peg.core.common.ISearchCriteria;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchCriteria;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.exception.BusinessException;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TCustDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tCustDAO")
public class TCustDAOImpl implements TCustDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TCustDAOImpl.class);
	
	private final static String COUNT_QUERY = "select count(tcust) from TCust tcust ";
	private final static String LIST_QUERY = "select tcust from TCust tcust";
	private final static String CUST_DATA_QUERY = "select tcust.custId, tcust.custName, tcust.custFirstName, tcust.custMiddleName, tcust.custLastName, " +
			" tcust.activeFlag, tcust.custCd, tcust.categoryId, tcust.custTypeId from TCust tcust";
	
	private final static String LIST_TCUST_QUERY = "select custId,custName,custFirstName,custMiddleName,custLastName," +
			"activeFlag,categoryId,custCd,custTypeId from TCust tcust";
	

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	//private static final String TCUSTTYPE = "tCustType";
    //private static final String TPRTTYPE = "tPrtType";
	//private static final String TCUSTCATEGORY = "tCustCategory";

	private final Class<TCust> clazz;

	public Class<TCust> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TCustDAOImpl() {
		super();
		this.clazz = TCust.class;
	}

	private static final String JPAQL = "select tCustentity from TCust tCustentity";

	private static final String JPACOUNTQL = "select count(tCustentity) from TCust tCustentity";

	private static final String[] RESTRICTIONS = { "tCustentity.custId = #{tCustentity.getCustId}",
			"tCustentity.custName like '#{tCustentity.getCustName}%'",
			"tCustentity.custFirstName like '#{tCustentity.getCustFirstName}%'",
			"tCustentity.custMiddleName like '#{tCustentity.getCustMiddleName}%'",
			"tCustentity.custLastName like '#{tCustentity.getCustLastName}%'",
			"tCustentity.activeFlag = #{tCustentity.getActiveFlag}",
			"tCustentity.stateLicId like '#{tCustentity.getStateLicId}%'",
			"tCustentity.deaId like '#{tCustentity.getDeaId}%'",
			"tCustentity.comments like '#{tCustentity.getComments}%'",
			"tCustentity.createdBy = #{tCustentity.getCreatedBy}",
			"Date(tCustentity.createDt) = '#{tCustentity.getCreateDt}'",
			"tCustentity.updatedBy = #{tCustentity.getUpdatedBy}",
			"Date(tCustentity.updateDt) = '#{tCustentity.getUpdateDt}'",
			"tCustentity.tenantId = #{tCustentity.getTenantId}", "tCustentity.custCd like '#{tCustentity.getCustCd}%'",
			"tCustentity.custTypeId = #{tCustentity.getCustTypeId}",
			"tCustentity.prtTypeId = #{tCustentity.getPrtTypeId}",
			"tCustentity.custTypeId = #{tCustentity.getCustTypeId}",
			"tCustentity.prtTypeId = #{tCustentity.getPrtTypeId}",
			"tCustentity.categoryId = #{tCustentity.getCategoryId}",
			"tCustentity.namePfx like '#{tCustentity.getNamePfx}%'",
			"tCustentity.nameSfx like '#{tCustentity.getNameSfx}%'",
			"tCustentity.statusId = #{tCustentity.getStatusId}",
			"tCustentity.attr1 like '#{tCustentity.getAttr1}%'", "tCustentity.attr2 like '#{tCustentity.getAttr2}%'",
			"tCustentity.attr3 like '#{tCustentity.getAttr3}%'", "tCustentity.attr4 like '#{tCustentity.getAttr4}%'",
			"tCustentity.attr5 like '#{tCustentity.getAttr5}%'", "tCustentity.attr6 like '#{tCustentity.getAttr6}%'",
			"tCustentity.attr7 like '#{tCustentity.getAttr7}%'", "tCustentity.attr8 like '#{tCustentity.getAttr8}%'",
			"tCustentity.attr9 like '#{tCustentity.getAttr9}%'", "tCustentity.attr10 like '#{tCustentity.getAttr10}%'",
			"tCustentity.attr11 like '#{tCustentity.getAttr11}%'",
			"tCustentity.attr12 like '#{tCustentity.getAttr12}%'",
			"tCustentity.attr13 like '#{tCustentity.getAttr13}%'",
			"tCustentity.attr14 like '#{tCustentity.getAttr14}%'",
			"tCustentity.attr15 like '#{tCustentity.getAttr15}%'",
			"tCustentity.attr16 like '#{tCustentity.getAttr16}%'",
			"tCustentity.attr17 like '#{tCustentity.getAttr17}%'",
			"tCustentity.attr18 like '#{tCustentity.getAttr18}%'",
			"tCustentity.attr19 like '#{tCustentity.getAttr19}%'",
			"tCustentity.attr20 like '#{tCustentity.getAttr20}%'",
			"tCustentity.attr21 like '#{tCustentity.getAttr21}%'",
			"tCustentity.attr22 like '#{tCustentity.getAttr22}%'",
			"tCustentity.attr23 like '#{tCustentity.getAttr23}%'",
			"tCustentity.attr24 like '#{tCustentity.getAttr24}%'",
			"tCustentity.attr25 like '#{tCustentity.getAttr25}%'",
			"tCustentity.attr26 like '#{tCustentity.getAttr26}%'",
			"tCustentity.attr27 like '#{tCustentity.getAttr27}%'",
			"tCustentity.attr28 like '#{tCustentity.getAttr28}%'",
			"tCustentity.attr29 like '#{tCustentity.getAttr29}%'",
			"tCustentity.attr30 like '#{tCustentity.getAttr30}%'",
			"tCustentity.attr31 like '#{tCustentity.getAttr31}%'",
			"tCustentity.attr32 like '#{tCustentity.getAttr32}%'",
			"tCustentity.attr33 like '#{tCustentity.getAttr33}%'",
			"tCustentity.attr34 like '#{tCustentity.getAttr34}%'",
			"tCustentity.attr35 like '#{tCustentity.getAttr35}%'",
			"tCustentity.attr36 like '#{tCustentity.getAttr36}%'",
			"tCustentity.attr37 like '#{tCustentity.getAttr37}%'",
			"tCustentity.attr38 like '#{tCustentity.getAttr38}%'",
			"tCustentity.attr39 like '#{tCustentity.getAttr39}%'",
			"tCustentity.attr40 like '#{tCustentity.getAttr40}%'",
			"tCustentity.attr41 like '#{tCustentity.getAttr41}%'",
			"tCustentity.attr42 like '#{tCustentity.getAttr42}%'",
			"tCustentity.attr43 like '#{tCustentity.getAttr43}%'",
			"tCustentity.attr44 like '#{tCustentity.getAttr44}%'",
			"tCustentity.attr45 like '#{tCustentity.getAttr45}%'",
			"tCustentity.attr46 like '#{tCustentity.getAttr46}%'",
			"tCustentity.attr47 like '#{tCustentity.getAttr47}%'",
			"tCustentity.attr48 like '#{tCustentity.getAttr48}%'",
			"tCustentity.attr49 like '#{tCustentity.getAttr49}%'",
			"tCustentity.attr50 like '#{tCustentity.getAttr50}%'" };


	/**
	 * Stores a new TCust entity object in to the persistent store
	 * 
	 * @param tCust
	 *            TCust Entity object to be persisted
	 * @return tCust Persisted TCust object
	 */
	public TCust createTCust(final TCust tCust) {
		LOGGER.info("=========== Create TCust ===========");
		return genericDAO.store(tCust);
	}

	/**
	 * Deletes a TCust entity object from the persistent store
	 * 
	 * @param tCust
	 *            TCust Entity object to be deleted
	 */
	public void deleteTCust(final Integer custId) {
		LOGGER.info("=========== Delete TCust ===========");
		final TCust tCust = genericDAO.get(clazz, custId);
		genericDAO.remove(tCust);
	}

	/**
	 * Updates a TCust entity object in to the persistent store
	 * 
	 * @param tCust
	 *            TCust Entity object to be updated
	 * @return tCust Persisted TCust object
	 */
	public TCust updateTCust(final TCust tCust) {
		LOGGER.info("=========== Update TCust ===========");
		return genericDAO.update(tCust);
	}

	/**
	 * Retrieve an TCust object based on given TCust custId.
	 * 
	 * @param tCustId
	 *            the primary key value of the TCust Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TCust findTCustById(final Integer tCustId) {
		LOGGER.info("find TCust instance with custId: " + tCustId);
		return genericDAO.get(clazz, tCustId);
	}

	/**
	 * Retrieve TCust based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCust> list of TCust if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TCust> findTCusts(final SearchFilter<TCust> searchFilter) {
		LOGGER.info("=========== Find TCusts ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TCust tCust = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tCustentity", tCust);

		/*if (tCust.getTCustType() == null) {
			jpaQuery.bind(TCUSTTYPE, new TCustType());
		} else {
			LOGGER.info("tCustType {}", tCust.getTCustType());

			jpaQuery.bind(TCUSTTYPE, tCust.getTCustType());
		}

		if (tCust.getTPrtType() == null) {
			jpaQuery.bind(TPRTTYPE, new TPrtType());
		} else {
			LOGGER.info("tPrtType {}", tCust.getTPrtType());

			jpaQuery.bind(TPRTTYPE, tCust.getTPrtType());
		}

		if (tCust.getTCustCategory() == null) {
			jpaQuery.bind(TCUSTCATEGORY, new TCustCategory());
		} else {
			LOGGER.info("tCustCategory {}", tCust.getTCustCategory());

			jpaQuery.bind(TCUSTCATEGORY, tCust.getTCustCategory());
		}*/
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TCusts.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTCusts(final SearchFilter<TCust> searchFilter) {
		LOGGER.info("=========== Count TCust ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TCust tCust = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tCustentity", tCust);

		/*if (tCust.getTCustType() == null) {
			jpaCountQuery.bind(TCUSTTYPE, new TCustType());
		} else {
			LOGGER.info("tCustType {}", tCust.getTCustType());

			jpaCountQuery.bind(TCUSTTYPE, tCust.getTCustType());
		}

		if (tCust.getTPrtType() == null) {
			jpaCountQuery.bind(TPRTTYPE, new TPrtType());
		} else {
			LOGGER.info("tPrtType {}", tCust.getTPrtType());

			jpaCountQuery.bind(TPRTTYPE, tCust.getTPrtType());
		}

		if (tCust.getTCustCategory() == null) {
			jpaCountQuery.bind(TCUSTCATEGORY, new TCustCategory());
		} else {
			LOGGER.info("tCustCategory {}", tCust.getTCustCategory());

			jpaCountQuery.bind(TCUSTCATEGORY, tCust.getTCustCategory());
		}*/

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TCust based on given search criteria using JPA named Query.
	 * The search criteria is of TCustType type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCust> list of TCusts if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TCust> getTCustsByTCustType(final SearchFilter<TCustType> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TCustType tCustType = searchFilter.getEntityClass();
		List<Object> tCustTypeList = new ArrayList<Object>();
		tCustTypeList.add(tCustType);
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTCustByTCustType", tCustTypeList, index, maxresult);
	}

	/**
	 * Count TCust based on given search criteria using JPA named Query.
	 * The search criteria is of TCustType type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTCustsByTCustType(final SearchFilter<TCustType> searchFilter) {

		final TCustType tCustType = searchFilter.getEntityClass();
		List<Object> tCustTypeList = new ArrayList<Object>();
		tCustTypeList.add(tCustType);
		return genericDAO.findEntitiesByNamedQuery("CountTCustsByTCustType", tCustTypeList);
	}

	/**
	 * Retrieve TCust based on given search criteria using JPA named Query.
	 * The search criteria is of TPrtType type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCust> list of TCusts if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TCust> getTCustsByTPrtType(final SearchFilter<TPrtType> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TPrtType tPrtType = searchFilter.getEntityClass();
		List<Object> tPrtTypeList = new ArrayList<Object>();
		tPrtTypeList.add(tPrtType);
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTCustByTPrtType", tPrtTypeList, index, maxresult);
	}

	/**
	 * Count TCust based on given search criteria using JPA named Query.
	 * The search criteria is of TPrtType type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTCustsByTPrtType(final SearchFilter<TPrtType> searchFilter) {

		final TPrtType tPrtType = searchFilter.getEntityClass();
		List<Object> tPrtTypeList = new ArrayList<Object>();
		tPrtTypeList.add(tPrtType);
		return genericDAO.findEntitiesByNamedQuery("CountTCustsByTPrtType", tPrtTypeList);
	}

	/**
	 * Retrieve TCust based on given search criteria using JPA named Query.
	 * The search criteria is of TCustCategory type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCust> list of TCusts if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TCust> getTCustsByTCustCategory(final SearchFilter<TCustCategory> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TCustCategory tCustCategory = searchFilter.getEntityClass();
		List<Object> tCustCategoryList = new ArrayList<Object>();
		tCustCategoryList.add(tCustCategory);
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTCustByTCustCategory", tCustCategoryList, index, maxresult);
	}

	/**
	 * Count TCust based on given search criteria using JPA named Query.
	 * The search criteria is of TCustCategory type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTCustsByTCustCategory(final SearchFilter<TCustCategory> searchFilter) {

		final TCustCategory tCustCategory = searchFilter.getEntityClass();
		List<Object> tCustCategoryList = new ArrayList<Object>();
		tCustCategoryList.add(tCustCategory);
		return genericDAO.findEntitiesByNamedQuery("CountTCustsByTCustCategory", tCustCategoryList);
	}

	/**
	 * Gets the cust ids.
	 *
	 * @param query the query
	 * @return the cust ids
	 */
	public List<Integer> getCustIds(String query){
		return genericDAO.findByNativeQuery(query);
	}
	/**
	 * Find customers by zip.
	 *
	 * @param zipCode the zip code
	 * @return the list
	 */
	@Override
	public List<Object> findCustomersByZip(String zipCode) {	
		List<Object> zipCodeList = new ArrayList<Object>();
		zipCodeList.add(zipCode);
		List<Object> objList = genericDAO.findEntitiesByNamedQueryMultiCond("FindCustomersByZip", zipCodeList, -1, -1);		
		return objList;
	}
	
	 /**
 	 * Gets ProdUCT type from cid
 	 *
 	 * @param cid the cid
 	 * @return the t prt type from cid
 	 */
	public TPrtType getTPrtTypeFromCID(Integer cid) {
		List<TPrtType> tPrtTypeList=null;

		List paramList = new ArrayList();
		paramList.add(cid);

		tPrtTypeList = genericDAO.findEntitiesByNamedQueryMultiCond(
				"FindAllTPrtTypesFromCid", paramList, 0, -1);
		
		if(tPrtTypeList!=null && !(tPrtTypeList.isEmpty()))
		{
			return tPrtTypeList.get(0);
		}

		return null;

	}

	/**
	 * Gets tcusttype from custId.
	 *
	 * @param cid the cid
	 * @return the t cust type from cust id
	 */
	public TCustType getTCustTypeFromCustId(Integer cid) {

		List<TCustType> tCustTypeList = null;

		List paramList = new ArrayList();
		paramList.add(cid);

		tCustTypeList = genericDAO.findEntitiesByNamedQueryMultiCond(
				"FindAllTCusttypesFromCid", paramList, 0, -1);

		if (tCustTypeList != null &&!(tCustTypeList.isEmpty())) {
			return tCustTypeList.get(0);
		}

		return null;
	}
	 
	
	/**
	 * Gets the all customers by postal cd.
	 *
	 * @param postalCd the postal cd
	 * @param start the start
	 * @param results the results
	 * @return the all customers by postal cd
	 */
	public List<TCust> getAllCustomersByPostalCD(String postalCd,int start,int results)
	{
		List<TCust> tCustList = null;
		
		List paramList = new ArrayList();
		paramList.add(postalCd);
		
		//address id =1
		paramList.add(Integer.valueOf(1));
		
		// character flag = y 
		paramList.add(Character.valueOf('Y'));
		
		
		tCustList = genericDAO.findEntitiesByNamedQueryMultiCond(
				"FindAllTCustsFromPostalCd", paramList, start, results);
		
		if (tCustList != null &&!(tCustList.isEmpty())) {
			return tCustList;
		}
		
		
		return null;
	}
	
	/**
	 * Gets the all customers by city.
	 *
	 * @param city the city
	 * @param start the start
	 * @param results the results
	 * @return the all customers by city
	 */
	public List<TCust> getAllCustomersByCity(String city,int start,int results)
	{
		List<TCust> tCustList = null;		
		List paramList = new ArrayList();
		paramList.add(city);
		
		
		//address id =1
		paramList.add(Integer.valueOf(1));
				
		// character flag = y 
		paramList.add(Character.valueOf('Y'));
		
		tCustList = genericDAO.findEntitiesByNamedQueryMultiCond(
				"FindAllTCustsFromCity", paramList, start, results);		
		
		
		if (tCustList != null &&!(tCustList.isEmpty())) {
			return tCustList;
		}		
		
		return null;
	}
	

	/** 4
	 * get TCustAddr from cid
	 * @param cid
	 * @return
	 */
	public TCustAddr getTCustAddressFromCustId(Integer cid) {

		List<TCustAddr> tCustAddressList = null;

		List paramList = new ArrayList();
		paramList.add(cid);
		
		//address id =1
		paramList.add(Integer.valueOf(1));
						
		// character flag = y 
		paramList.add(Character.valueOf('Y'));

		tCustAddressList = genericDAO.findEntitiesByNamedQueryMultiCond(
				"FindAllTCustAddrsFromCid", paramList, 0, -1);

		if (tCustAddressList != null &&!(tCustAddressList.isEmpty())) {
			return tCustAddressList.get(0);
		}

		return null;

	}
	
	/**
	 * Gets the all customers from priority description
	 *
	 * @param prtDesc the prt desc
	 * @param start the start
	 * @param results the results
	 * @return the all cust with prt desc
	 */
	public List<TCust> getAllCustWithPrtDesc(String prtDesc,int start,int results)
	{
		List<TCust> tcustList = new ArrayList<TCust>();
		
		List paramList = new ArrayList();
		paramList.add(prtDesc);
		
		
		try{
			tcustList = genericDAO.findEntitiesByNamedQueryMultiCond("FindAllCustFromPrioritydesc", paramList, start, results);
           }
           catch(PersistenceException e){
        	   LOGGER.error("Error while retrieving universal details");
           }
          
		
		return tcustList;
	}
	
	/**
	 * Gets the all customers from cust description
	 *
	 * @param custDesc the cust desc
	 * @param start the start
	 * @param results the results
	 * @return the all cust with cust type desc
	 */
	public List<TCust> getAllCustWithCustTypeDesc(String custDesc,int start,int results)
	{
		List<TCust> tcustList = new ArrayList<TCust>();
		
		List paramList = new ArrayList();
		paramList.add(custDesc);
		
		
		try{
			tcustList = genericDAO.findEntitiesByNamedQueryMultiCond("FindAllTCustFromType", paramList, start, results);
           }
           catch(PersistenceException e){
        	   LOGGER.error("Error while retrieving universal details");
           }
          
		
		return tcustList;
	}
	/**
	 * Findrecently added customer.
	 *
	 * @param algId the alg id
	 * @param bussId the buss id
	 * @param salesTeamId the sales team id
	 * @param salesPosId the sales pos id
	 * @param salesHierId the sales hier id
	 * @param tenantId the tenant id
	 * @param flag the flag
	 * @param currentDate the current date
	 * @param localeId the locale id
	 * @return the list
	 */
	@Override
	public List<Object[]> findrecentlyAddedCustomer(Long algId, Long bussId,
			Long salesTeamId, Long salesPosId, Long salesHierId,
			Short tenantId, Character flag,Date currentDate, String localeId) {
		List paramList = new ArrayList();
		paramList.add(tenantId);
		paramList.add(algId);
		paramList.add(bussId);
		paramList.add(salesTeamId);
		paramList.add(salesPosId);
		paramList.add(salesHierId);
		paramList.add(currentDate);
		//paramList.add(LocaleId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindRecentlyAddedCustomerToSP", paramList, 0, 10);
	}

	/*public List<TCust> findrecentlyAddedCustomer(Set<Long> childsp,
			Long alignmentId, Long bussinessUnitId, Long salesTeamId,
			Short tenantId, Date currentDate, String localeId, List<Integer> crStatusList) {
		List paramList = new ArrayList();
		paramList.add(tenantId);
		paramList.add(childsp);
		paramList.add(alignmentId);
		paramList.add(bussinessUnitId);
		paramList.add(salesTeamId);
		paramList.add(currentDate);
		paramList.add(localeId);
		paramList.add(crStatusList);
		
		
		return genericDAO.findEntitiesByNamedQueryMultiCond(
				"FindRecentlyAddedCustomerToSP", paramList, 0, -1);
		
	}*/
	/**
  	 * Find customers.
  	 *
  	 * @param algId the alg id
  	 * @param bussId the buss id
  	 * @param salesTeamId the sales team id
  	 * @param salesPosId the sales pos id
  	 * @param salesHierId the sales hier id
  	 * @param tenantId the tenant id
  	 * @param flag the flag
  	 * @param custName the cust name
  	 * @return the list
  	 */
	@Override
	public List<TCust> findCustomers(Long algId, Long bussId, Long salesTeamId,
			Long salesPosId, Long salesHierId, Short tenantId,
			Character flag, String custName) {
		List paramList = new ArrayList();
		paramList.add(tenantId);
		paramList.add(algId);
		paramList.add(bussId);
		paramList.add(salesTeamId);
		paramList.add(flag);
		paramList.add(salesPosId);
		paramList.add(salesHierId);
		paramList.add(custName);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindCustomerToSP", paramList, 0, -1);
	}
	/**
 	 * Gets the all t prt type.
 	 *
 	 * @return the all t prt type
 	 */
	@Override
	public List<TPrtType> getAllTPrtType(){
		return genericDAO.findEntitiesByNamedQuery("FindAllTPrtTypes");
	}
	 /**
 	 * Find customers by tentent id.
 	 *
 	 * @param tenantId the tenant id
 	 * @return the list
 	 */
	@Override
	public List<TCust> findCustomersByTententID(Short tenantId) {
		List paramList = new ArrayList();
		paramList.add(tenantId);
		
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindCustomerByTententID", paramList, 0, -1);
	}
	
	/**
 	 * Find customers count.
 	 *
 	 * @param filter the filter
 	 * @param tenantId the tenant id
 	 * @return the long
 	 */
	@Override
	public Long findCustomersCount(SearchFilter<TCust> filter, Short tenantId) {
		List paramList = new ArrayList();
		paramList.add(tenantId);
		String finalQuery = COUNT_QUERY+" where tenantId=?1";
		if(filter.getEntityClass().getCustCd()!=null && filter.getEntityClass().getCustCd().trim().length()>0){
			finalQuery = finalQuery+" and custCd LIKE ?"+(paramList.size()+1);
			paramList.add("%"+filter.getEntityClass().getCustCd()+"%");
		}
		if(filter.getEntityClass().getCustName()!=null && filter.getEntityClass().getCustName().trim().length()>0){
			
			finalQuery = finalQuery+" and custName LIKE ?"+(paramList.size()+1);
			paramList.add("%"+filter.getEntityClass().getCustName()+"%");
		}
		if(filter.getEntityClass().getCategoryId()!=null){
			finalQuery = finalQuery+" and categoryId=?"+(paramList.size()+1);
			paramList.add(filter.getEntityClass().getCategoryId());
		}
		
			finalQuery = finalQuery+" and activeFlag='Y' ";
		
		return (Long) genericDAO.findEntitiesByBuildQueries(finalQuery, paramList, 0, -1).get(0);
	}
	
	/**
	 * Find customers list.
	 *
	 * @param filter the filter
	 * @param tenantId the tenant id
	 * @param start the start
	 * @param size the size
	 * @return the list
	 */
	@Override
	public List<TCust> findCustomersList(SearchFilter<TCust> filter,Short tenantId,int start,int size) {
		List paramList = new ArrayList();
		paramList.add(tenantId);
		String finalQuery = LIST_QUERY+" where tenantId=?1";
		if(filter.getEntityClass().getCustCd()!=null && filter.getEntityClass().getCustCd().trim().length()>0){
			
			finalQuery = finalQuery+" and custCd LIKE ?"+(paramList.size()+1);
			paramList.add("%"+filter.getEntityClass().getCustCd()+"%");
		}
		if(filter.getEntityClass().getCustName()!=null && filter.getEntityClass().getCustName().trim().length()>0){
			
			finalQuery = finalQuery+" and custName LIKE ?"+(paramList.size()+1);
			paramList.add("%"+filter.getEntityClass().getCustName()+"%");
		}
		if(filter.getEntityClass().getCategoryId()!=null){
			finalQuery = finalQuery+" and categoryId=?"+(paramList.size()+1);
			paramList.add(filter.getEntityClass().getCategoryId());
		}
		
		finalQuery = finalQuery+" and activeFlag='Y' ";
		Set<String> sortColumns = filter.getSortInfo().getSortColumns();
		
		if(sortColumns !=null&&!(sortColumns.isEmpty())){
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
	public List<Object[]> findCustsList(SearchFilter<TCust> filter,Short tenantId,int start,int size) {
		List paramList = new ArrayList();
		paramList.add(tenantId);
		String finalQuery = CUST_DATA_QUERY+" where tenantId=?1";
		if(filter.getEntityClass().getCustCd()!=null && filter.getEntityClass().getCustCd().trim().length()>0){
			
			finalQuery = finalQuery+" and custCd LIKE ?"+(paramList.size()+1);
			paramList.add("%"+filter.getEntityClass().getCustCd()+"%");
		}
		if(filter.getEntityClass().getCustName()!=null && filter.getEntityClass().getCustName().trim().length()>0){
			
			finalQuery = finalQuery+" and custName LIKE ?"+(paramList.size()+1);
			paramList.add("%"+filter.getEntityClass().getCustName()+"%");
		}
		if(filter.getEntityClass().getCategoryId()!=null){
			finalQuery = finalQuery+" and categoryId=?"+(paramList.size()+1);
			paramList.add(filter.getEntityClass().getCategoryId());
		}
		
		finalQuery = finalQuery+" and activeFlag='Y' ";
		Set<String> sortColumns = filter.getSortInfo().getSortColumns();
		
		if(sortColumns !=null&&!(sortColumns.isEmpty())){
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
		return genericDAO.findEntitiesByBuildQueries(finalQuery, paramList, start, size);
	}
	
	/**
	 * Gets the t cust type from cust id fr gis.
	 *
	 * @param cid the cid
	 * @param tenantId the tenant id
	 * @param localeId the locale id
	 * @return the t cust type from cust id fr gis
	 */
	public TCustType getTCustTypeFromCustIdFrGIS(Integer cid, Short tenantId, String localeId) {

		List<TCustType> tCustTypeList = null;

		List paramList = new ArrayList();
		paramList.add(cid);
		paramList.add(tenantId);
		paramList.add(localeId);

		tCustTypeList = genericDAO.findEntitiesByNamedQueryMultiCond(
				"FindAllTCusttypesFromCidFrGIS", paramList, 0, -1);

		if (tCustTypeList != null &&!(tCustTypeList.isEmpty())) {
			return tCustTypeList.get(0);
		}

		return null;
	}
	/**
	 * Find all t custs by ids.
	 *
	 * @param custIds the cust ids
	 * @return the list
	 */
	@Override
	public List<TCust> findAllTCustsByIDS(List<Integer> custIds) {
		
		List paramList = new ArrayList();
		paramList.add(custIds);

		List<TCust> list = genericDAO.findEntitiesByNamedQueryMultiCond(
				"FindAllTCustsByIDS", paramList, 0, -1);
		return list;
	}
	/**
	 * Builds the query from where clause.
	 *
	 * @param code the code
	 * @param zip the zip
	 * @param city the city
	 * @param name the name
	 * @param priority the priority
	 * @param type the type
	 * @param start the start
	 * @param results the results
	 * @param tenantId the tenant id
	 * @return the list
	 * @throws BusinessException the business exception
	 */
	@Override
	public List<TCust> buildQueryFromWhereClause(String code, String zip,
			String city, String name, List<Integer> priority, Integer type, int start,
			int results, Short tenantId) throws BusinessException {

		LOGGER.info("********************** inside buildQueryFromWhereClause of TCustDAO ******************************  ");
		ISearchCriteria temp = null;
		//FOR ACTIVE CUSTOMERS
		ISearchCriteria searchCriteria = new SearchCriteria();
		searchCriteria.addEqualTo("activeFlag",'Y');

		// FOR TENANT ID
		ISearchCriteria searchCriteriaTenant = new SearchCriteria();
		searchCriteriaTenant.addEqualTo("tenantId",tenantId);
		searchCriteria.addAndCriteria(searchCriteriaTenant);
		temp = searchCriteriaTenant;
		
		// ADD PAGINATION 
		searchCriteria.addLimit(start==-1?start+1:start, results);

		// 1. SEARCH BY CUSTOMER CODE
		if (code != null && !code.equals("")) {
			ISearchCriteria searchCriteriaCode = new SearchCriteria();
			searchCriteriaCode.addLike("custCd","%" + code + "%");
			temp.addAndCriteria(searchCriteriaCode);
			temp = searchCriteriaCode;
		}
		
		// 2. SEARCH BY CUSTOMER NAME
		if (name != null && !name.equals("") ) {
			ISearchCriteria searchCriteriaName = new SearchCriteria();
			searchCriteriaName.addLike("custName","%" + name + "%");
			temp.addAndCriteria(searchCriteriaName);
			temp = searchCriteriaName;
		}

		// 3. SEARCH BY ZIP
		if (zip != null  && !zip.equals("") ) {
			
			String postalCd = "com.cognizant.opserv.sp.core.entity.TCustAddr:tCustAddrss.postalCd";
			String prAddrFlag = "com.cognizant.opserv.sp.core.entity.TCustAddr:tCustAddrss.prAddrFlag";
			String activeFlag = "com.cognizant.opserv.sp.core.entity.TCustAddr:tCustAddrss.activeFlag";
			
			// FOR ACTIVE FLAG 
			ISearchCriteria searchCriteriaAddrActiveFlag = new SearchCriteria();
			searchCriteriaAddrActiveFlag.addEqualTo(activeFlag, 'Y');
			// FOR PRIMARY ADDRESS
			ISearchCriteria searchCriteriaAddrPrAddrFlag = new SearchCriteria();
			searchCriteriaAddrPrAddrFlag.addEqualTo(prAddrFlag, 'Y');
			searchCriteriaAddrActiveFlag.addAndCriteria(searchCriteriaAddrPrAddrFlag);
			ISearchCriteria subtemp = searchCriteriaAddrPrAddrFlag;
			// FOR ZIP
			ISearchCriteria searchCriteriaAddr = new SearchCriteria();
			searchCriteriaAddr.addLike(postalCd, "%" + zip + "%");
			subtemp.addAndCriteria(searchCriteriaAddr);
			subtemp = searchCriteriaAddr;
			temp.addAndCriteria(subtemp);
			temp = subtemp;
			
			
		}

		// 4. SEARCH BY CITY
		if (city != null && !city.equals("")) {
			
			String searchCity = "com.cognizant.opserv.sp.core.entity.TCustAddr:tCustAddrss.city";
			String prAddrFlag = "com.cognizant.opserv.sp.core.entity.TCustAddr:tCustAddrss.prAddrFlag";
			String activeFlag = "com.cognizant.opserv.sp.core.entity.TCustAddr:tCustAddrss.activeFlag";

			// FOR ACTIVE FLAG 
			ISearchCriteria searchCriteriaAddrActiveFlag = new SearchCriteria();
			searchCriteriaAddrActiveFlag.addEqualTo(activeFlag, 'Y');
			// FOR PRIMARY ADDRESS
			ISearchCriteria searchCriteriaAddrPrAddrFlag = new SearchCriteria();
			searchCriteriaAddrPrAddrFlag.addEqualTo(prAddrFlag, 'Y');
			searchCriteriaAddrActiveFlag.addAndCriteria(searchCriteriaAddrPrAddrFlag);
			ISearchCriteria subTemp = searchCriteriaAddrPrAddrFlag;
			// FOR CITY
			ISearchCriteria searchCriteriaCity = new SearchCriteria();
			searchCriteriaCity.addLike(searchCity, "%" + city + "%");
			subTemp.addAndCriteria(searchCriteriaCity);
			subTemp = searchCriteriaCity;
			temp.addAndCriteria(subTemp);
			temp = subTemp;

		}
		
		// 5. SEARCH BY PRIORITY
		if (priority != null && !priority.isEmpty() ) {
			ISearchCriteria searchCriteriaPriority = new SearchCriteria();
			searchCriteriaPriority.addIn("prtTypeId", priority);
			temp.addAndCriteria(searchCriteriaPriority);
			temp = searchCriteriaPriority;
		}
		
		// 6. SEARCH BY TYPE
		if (type != -1 ) {
			ISearchCriteria searchCriteriaType = new SearchCriteria();
			searchCriteriaType.addEqualTo("custTypeId", type);
			temp.addAndCriteria(searchCriteriaType);
			temp = searchCriteriaType;
		}
		
		try{
			List<TCust> result = genericDAO.search(clazz, searchCriteria);
			LOGGER.info("********************** ends buildQueryFromWhereClause of TCUSTDAO******************************  ");
			return result;
		}catch(DataAccessException e){
			throw new BusinessException(e.getMessage(),
					"Fetch universal customers on search", e);
		}
	}
	/**
	 * Find all t custtypes for all cid.
	 *
	 * @param cid the cid
	 * @param tenantId the tenant id
	 * @return the list
	 */
	@Override
	public List<TCustType> findAllTCusttypesForAllCid(List<Integer> cid, Short tenantId) {
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(cid);
		paramList.add(tenantId);
		
		List<TCustType> tCustTypeList = genericDAO.findEntitiesByNamedQueryMultiCond(
				"FindAllTCusttypesForAllCid", paramList, 0, -1);

		return tCustTypeList;
	}
	/**
	 * Find all t prt types for all cid.
	 *
	 * @param cid the cid
	 * @param tenantId the tenant id
	 * @return the list
	 */
	@Override
	public List<TPrtType> findAllTPrtTypesForAllCid(List<Integer> cid, Short tenantId) {
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(cid);
		paramList.add(tenantId);
		
		List<TPrtType> tPrtTypeList = genericDAO.findEntitiesByNamedQueryMultiCond(
				"FindAllTPrtTypesForAllCid", paramList, 0, -1);
		
		return tPrtTypeList;

	}
	
	/**
	 * Gets the t prt type from cid for tier.
	 *
	 * @param custAlgmntId the cust algmnt id
	 * @return the t prt type from cid for tier
	 */
	@Override
	public TPrtType getTPrtTypeFromCIDForTier(Long custAlgmntIds) {
		List<TPrtType> tPrtTypeList=null;

		List paramList = new ArrayList();
		paramList.add(custAlgmntIds);

		tPrtTypeList = genericDAO.findEntitiesByNamedQueryMultiCond(
				"FindAllTPrtTypesFromCidForTier", paramList, 0, -1);
		
		if(tPrtTypeList!=null && !(tPrtTypeList.isEmpty()))
		{
			return tPrtTypeList.get(0);
		
		}
		return null;
	}
	/**
	 * Gets the customer by cust code.
	 *
	 * @param tenantId the tenant id
	 * @param custCode the cust code
	 * @return the customer by cust code
	 */
	@Override
	public TCust getCustomerByCustCode(Short tenantId,String custCode){
		List<Object> paramList = new ArrayList<Object>();		
		paramList.add(tenantId);
		paramList.add(custCode);
		List<TCust> custs = genericDAO.findEntitiesByNamedQueryMultiCond("GetCustomerByCustCode", paramList, 0, -1);
		return (custs!=null&&!(custs.isEmpty()))?custs.get(0):null;
	}
	/**
	 * Gets the cust data.
	 *
	 * @param localeId the locale id
	 * @param custId the cust id
	 * @param tenantId the tenant id
	 * @return the cust data
	 */
	@Override
	public List<Object[]> getCustData(String localeId,Integer custId, Short tenantId){
		List<Object> paramList = new ArrayList<Object>();		
		paramList.add(localeId);
		paramList.add(custId);
		paramList.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("GetCustData", paramList, 0, -1);
	}
	/**
	 * Gets the cust basic data.
	 *
	 * @param localeId the locale id
	 * @param custId the cust id
	 * @param tenantId the tenant id
	 * @return the cust basic data
	 */
	@Override
	public List<Object[]> getCustBasicData(String localeId, Integer custId,
			Short tenantId) {
		List<Object> paramList = new ArrayList<Object>();		
		paramList.add(localeId);
		paramList.add(custId);
		paramList.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("GetCustBasicData", paramList, 0, -1);
	}
	/**
	 * Gets the customers basic data.
	 *
	 * @param localeId the locale id
	 * @param custIds the cust ids
	 * @param tenantId the tenant id
	 * @return the customers basic data
	 */
	@Override
	public List<Object[]> getCustomersBasicData(String localeId,List<Integer> custIds,
			Short tenantId) {
		List<Object> paramList = new ArrayList<Object>();		
		paramList.add(localeId);
		paramList.add(custIds);
		paramList.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("GetCustomersBasicData", paramList, 0, -1);
	}
	/**
	 * Find names of all t custs by ids.
	 *
	 * @param custIds the cust ids
	 * @param tenantId the tenant id
	 * @return the list
	 */
	@Override
	public List<Object[]> findNamesOfAllTCustsByIDS(List<Integer> custIds, Short tenantId) {
		
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(custIds);
		paramList.add(tenantId);

		return genericDAO.findEntitiesByNamedQueryMultiCond(
				"FindNamesOfAllTCustsByIDS", paramList, 0, -1);
	}
	/**
	 * Update t custs.
	 *
	 * @param tCusts the t custs
	 * @return the list
	 */
	@Override
	public List<TCust> updateTCusts(List<TCust> tCusts){
		return genericDAO.update(tCusts);
	}
	/**
	 * Gets the customer by cust code count.
	 *
	 * @param tenantId the tenant id
	 * @param custCode the cust code
	 * @return the customer by cust code count
	 */
	@Override
	public Integer getCustomerByCustCodeCount(Short tenantId,String custCode){
		List<Object> paramList = new ArrayList<Object>();		
		paramList.add(tenantId);
		paramList.add(custCode);
		List<Object> list = genericDAO.findEntitiesByNamedQueryMultiCond("GetCustomerByCustCodeCount", paramList, 0, -1);
		return Integer.parseInt(list.get(0).toString());
	}
	
	/**
	 * Find req customers list.
	 *
	 * @param filter the filter
	 * @param tenantId the tenant id
	 * @param start the start
	 * @param size the size
	 * @return the list
	 */
	@Override
	public List<Object[]> findReqCustomersList(SearchFilter<TCust> filter,Short tenantId,int start,int size) {
		List paramList = new ArrayList();
		paramList.add(tenantId);
		String finalQuery = LIST_TCUST_QUERY+" where tenantId=?1";
		if(filter.getEntityClass().getCustCd()!=null && filter.getEntityClass().getCustCd().trim().length()>0){
			
			finalQuery = finalQuery+" and custCd LIKE ?"+(paramList.size()+1);
			paramList.add(filter.getEntityClass().getCustCd()+"%");
		}
		if(filter.getEntityClass().getCustName()!=null && filter.getEntityClass().getCustName().trim().length()>0){
			
			finalQuery = finalQuery+" and custName LIKE ?"+(paramList.size()+1);
			paramList.add(filter.getEntityClass().getCustName()+"%");
		}
		if(filter.getEntityClass().getCategoryId()!=null){
			finalQuery = finalQuery+" and categoryId=?"+(paramList.size()+1);
			paramList.add(filter.getEntityClass().getCategoryId());
		}
		
		finalQuery = finalQuery+" and activeFlag='Y' ";
		Set<String> sortColumns = filter.getSortInfo().getSortColumns();
		
		if(sortColumns !=null&&!(sortColumns.isEmpty())){
			String coulmnName = sortColumns.toArray()[0].toString();
			
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
	/**
	 * Find custs list.
	 *
	 * @param filter the filter
	 * @param tenantId the tenant id
	 * @param start the start
	 * @param size the size
	 * @return the list
	 */
	@Override
	public List<TCust> findTCustsBySearchCriteria(ISearchCriteria criteria) {
		return genericDAO.search(clazz, criteria);
	}
	/**
	 * Gets the customer count by criteria.
	 *
	 * @param criteria the criteria
	 * @param userDetails the user details
	 * @return the customer count by criteria
	 */
	@Override
	public Long getCustomerCountByCriteria(ISearchCriteria criteria,
			Short tenantId) {
		return genericDAO.searchWithParametersForCount(clazz, criteria);
	}

	/**
	 * Fetch cust names fr cust ids.
	 *
	 * @param custIdList the cust id list
	 * @param tenantId the tenant id
	 * @return the list
	 */
	@Override
	public List<String> fetchCustNameFrCustIds(List<Integer> custIdList,
			Short tenantId) {
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(custIdList);
		paramList.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("fetchCustNameFrCustIds", paramList, 0, -1);
	}

}
