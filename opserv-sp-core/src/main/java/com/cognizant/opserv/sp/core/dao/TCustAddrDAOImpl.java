package com.cognizant.opserv.sp.core.dao;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TAddrType;
import com.cognizant.opserv.sp.core.entity.TCust;
import com.cognizant.opserv.sp.core.entity.TCustAddr;
import com.cognizant.opserv.sp.core.entity.TCustAddrId;
import com.cognizant.peg.core.common.ISearchCriteria;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TCustAddrDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tCustAddrDAO")
public class TCustAddrDAOImpl implements TCustAddrDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TCustAddrDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	//private static final String TCUST = "tCust";
	//private static final String TADDRTYPE = "tAddrType";

	private final Class<TCustAddr> clazz;

	public Class<TCustAddr> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TCustAddrDAOImpl() {
		super();
		this.clazz = TCustAddr.class;
	}

	private static final String JPAQL = "select tCustAddrentity from TCustAddr tCustAddrentity";

	private static final String JPACOUNTQL = "select count(*) from TCustAddr tCustAddrentity";

	private static final String[] RESTRICTIONS = {
			"tCustAddrentity.tCustAddrId.custId = #{tCustAddrentity.tCustAddrId.getCustId}",
			"tCustAddrentity.tCustAddrId.addrId = #{tCustAddrentity.tCustAddrId.getAddrId}",
			"tCustAddrentity.doorNum like '#{tCustAddrentity.getDoorNum}%'",
			"tCustAddrentity.streetName like '#{tCustAddrentity.getStreetName}%'",
			"tCustAddrentity.addrLine like '#{tCustAddrentity.getAddrLine}%'",
			"tCustAddrentity.city like '#{tCustAddrentity.getCity}%'",
			"tCustAddrentity.state like '#{tCustAddrentity.getState}%'",
			"tCustAddrentity.cntry like '#{tCustAddrentity.getCntry}%'",
			"tCustAddrentity.prAddrFlag = #{tCustAddrentity.getPrAddrFlag}",
			"tCustAddrentity.activeFlag = #{tCustAddrentity.getActiveFlag}",
			"tCustAddrentity.createdBy = #{tCustAddrentity.getCreatedBy}",
			"Date(tCustAddrentity.createDt) = '#{tCustAddrentity.getCreateDt}'",
			"tCustAddrentity.updatedBy = #{tCustAddrentity.getUpdatedBy}",
			"Date(tCustAddrentity.updateDt) = '#{tCustAddrentity.getUpdateDt}'",
			"tCustAddrentity.tenantId = #{tCustAddrentity.getTenantId}",
			"tCustAddrentity.postalCd like '#{tCustAddrentity.getPostalCd}%'",
			"tCustAddrentity.tCust.custId = #{tCustAddrentity.tCust.getCustId}",
			"tCustAddrentity.postalExtn like '#{tCustAddrentity.getPostalExtn}%'",
			"tCustAddrentity.addrLine2 like '#{tCustAddrentity.getAddrLine2}%'",
			"tCustAddrentity.addrLine3 like '#{tCustAddrentity.getAddrLine3}%'",
			"tCustAddrentity.addrLine4 like '#{tCustAddrentity.getAddrLine4}%'",
			"tCustAddrentity.tCust.custId = #{tCustAddrentity.tCust.getCustId}",
			"tCustAddrentity.addrTypeId = #{tCustAddrentity.getAddrTypeId}" };

	/**
	 * Stores a new TCustAddr entity object in to the persistent store
	 * 
	 * @param tCustAddr
	 *            TCustAddr Entity object to be persisted
	 * @return tCustAddr Persisted TCustAddr object
	 */
	public TCustAddr createTCustAddr(final TCustAddr tCustAddr) {
		LOGGER.info("=========== Create TCustAddr ===========");
		return genericDAO.store(tCustAddr);
	}

	/**
	 * Deletes a TCustAddr entity object from the persistent store
	 * 
	 * @param tCustAddr
	 *            TCustAddr Entity object to be deleted
	 */
	public void deleteTCustAddr(final TCustAddrId tCustAddrId) {
		LOGGER.info("=========== Delete TCustAddr ===========");
		final TCustAddr tCustAddr = genericDAO.get(clazz, tCustAddrId);
		genericDAO.remove(tCustAddr);
	}

	/**
	 * Updates a TCustAddr entity object in to the persistent store
	 * 
	 * @param tCustAddr
	 *            TCustAddr Entity object to be updated
	 * @return tCustAddr Persisted TCustAddr object
	 */
	public TCustAddr updateTCustAddr(final TCustAddr tCustAddr) {
		LOGGER.info("=========== Update TCustAddr ===========");
		return genericDAO.update(tCustAddr);
	}

	/**
	 * Retrieve an TCustAddr object based on given TCustAddr TCustAddrId.
	 * 
	 * @param tCustAddrId
	 *            the primary key value of the TCustAddr Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TCustAddr findTCustAddrById(final TCustAddrId tCustAddrId) {
		LOGGER.info("find TCustAddr instance with TCustAddrId: " + tCustAddrId);
		return genericDAO.get(clazz, tCustAddrId);
	}

	/**
	 * Retrieve TCustAddr based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCustAddr> list of TCustAddr if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TCustAddr> findTCustAddrs(final SearchFilter<TCustAddr> searchFilter) {
		LOGGER.info("=========== Find TCustAddrs ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TCustAddr tCustAddr = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tCustAddrentity", tCustAddr);

		/*if (tCustAddr.getTCust() == null) {
			jpaQuery.bind(TCUST, new TCust());
		} else {
			LOGGER.info("tCust {}", tCustAddr.getTCust());

			jpaQuery.bind(TCUST, tCustAddr.getTCust());
		}

		if (tCustAddr.getAddrId() == null) {
			jpaQuery.bind(TADDRTYPE, new TAddrType());
		} else {
			LOGGER.info("tAddrType {}", tCustAddr.getTAddrType());

			jpaQuery.bind(TADDRTYPE, tCustAddr.getTAddrType());
		}*/
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TCustAddrs.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTCustAddrs(final SearchFilter<TCustAddr> searchFilter) {
		LOGGER.info("=========== Count TCustAddr ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TCustAddr tCustAddr = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tCustAddrentity", tCustAddr);

		/*if (tCustAddr.getTCust() == null) {
			jpaCountQuery.bind(TCUST, new TCust());
		} else {
			LOGGER.info("tCust {}", tCustAddr.getTCust());

			jpaCountQuery.bind(TCUST, tCustAddr.getTCust());
		}

		if (tCustAddr.getTAddrType() == null) {
			jpaCountQuery.bind(TADDRTYPE, new TAddrType());
		} else {
			LOGGER.info("tAddrType {}", tCustAddr.getTAddrType());

			jpaCountQuery.bind(TADDRTYPE, tCustAddr.getTAddrType());
		}*/

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TCustAddr based on given search criteria using JPA named Query.
	 * The search criteria is of TCust type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCustAddr> list of TCustAddrs if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TCustAddr> getTCustAddrsByTCust(final SearchFilter<TCust> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TCust tCust = searchFilter.getEntityClass();
		List<Object> tCustList = new ArrayList<Object>();
		tCustList.add(tCust);
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTCustAddrByTCust", tCustList, index, maxresult);
	}
	
	
	/**
	 * Gets the customer address by id.
	 *
	 * @param addrid the addrid
	 * @param start the start
	 * @param results the results
	 * @return the customer address by id
	 */
	public List<TCustAddr> getCustomerAddressByID(Integer addrid,int start,int results)
	{
		List paramList = new ArrayList();
		paramList.add(addrid);
		
		List<TCustAddr> tCustAddrList = null;
		
		
		tCustAddrList = genericDAO.findEntitiesByNamedQueryMultiCond(
				"FindTCustAddrByTCust", paramList, start, results);
		
		return tCustAddrList;
		
	}

	/**
	 * Count TCustAddr based on given search criteria using JPA named Query.
	 * The search criteria is of TCust type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTCustAddrsByTCust(final SearchFilter<TCust> searchFilter) {

		final TCust tCust = searchFilter.getEntityClass();
		List<Object> tCustList = new ArrayList<Object>();
		tCustList.add(tCust);
		return genericDAO.findEntitiesByNamedQuery("CountTCustAddrsByTCust", tCustList);
	}

	/**
	 * Retrieve TCustAddr based on given search criteria using JPA named Query.
	 * The search criteria is of TAddrType type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCustAddr> list of TCustAddrs if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TCustAddr> getTCustAddrsByTAddrType(final SearchFilter<TAddrType> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TAddrType tAddrType = searchFilter.getEntityClass();
		List<Object> tAddrTypeList = new ArrayList<Object>();
		tAddrTypeList.add(tAddrType);
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTCustAddrByTAddrType", tAddrTypeList, index, maxresult);
	}

	/**
	 * Count TCustAddr based on given search criteria using JPA named Query.
	 * The search criteria is of TAddrType type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTCustAddrsByTAddrType(final SearchFilter<TAddrType> searchFilter) {

		final TAddrType tAddrType = searchFilter.getEntityClass();
		List<Object> tAddrTypeList = new ArrayList<Object>();
		tAddrTypeList.add(tAddrType);
		return genericDAO.findEntitiesByNamedQuery("CountTCustAddrsByTAddrType", tAddrTypeList);
	}
	/**
	 * Gets the postal cd by cust ids.
	 *
	 * @param query the query
	 * @return the postal cd by cust ids
	 */
	public List<String> getPostalCdByCustIds(String query){
		return genericDAO.findByNativeQuery(query);
	}
	/**
	 * Find pri address for customer.
	 *
	 * @param custId the cust id
	 * @param priFlg the pri flg
	 * @param activeFlag the active flag
	 * @param tenantId the tenant id
	 * @return the t cust addr
	 */
	public TCustAddr findPriAddressForCustomer(Integer custId, Character priFlg, Character activeFlag, short tenantId){
		List queryparam = new ArrayList();
		queryparam.add(custId);
		queryparam.add(priFlg);
		queryparam.add(activeFlag);
		queryparam.add(tenantId);
		
		return (TCustAddr) genericDAO.findEntitiesByNamedQueryMultiCond("FindPriAddressForCustomer", queryparam, 0, -1).get(0);
	}
	/**
	 * Gets the assigned customer.
	 *
	 * @param algmntId the algmnt id
	 * @param bussUnitId the buss unit id
	 * @param salesTeamId the sales team id
	 * @param slPosiD the sl posi d
	 * @param zip the zip
	 * @param tenantId the tenant id
	 * @return the assigned customer
	 */
	@Override
	public List<Object[]> getAssignedCustomer(Long algmntId, Long bussUnitId,
			Long salesTeamId,Long slPosiD, String zip, Short tenantId) {
		List queryParam = new ArrayList();
		queryParam.add(algmntId);
		queryParam.add(bussUnitId);
		queryParam.add(salesTeamId);
		queryParam.add(slPosiD);
		queryParam.add(zip);
		queryParam.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTCustAddrByPostalcode", queryParam, 0, -1);
	}
	/**
	 * Gets the assigned customerfor assign.
	 *
	 * @param algmntId the algmnt id
	 * @param bussUnitId the buss unit id
	 * @param salesTeamId the sales team id
	 * @param salesPosID the sales pos id
	 * @param salesHierID the sales hier id
	 * @param zip the zip
	 * @param tenantId the tenant id
	 * @return the assigned customerfor assign
	 */
	@Override
	public List<Object[]> getAssignedCustomerforAssign(Long algmntId,
			Long bussUnitId, Long salesTeamId,Long salesPosID,Long salesHierID, List<String> zip, Short tenantId) {
		List queryParam = new ArrayList();
		queryParam.add(algmntId);
		queryParam.add(bussUnitId);
		queryParam.add(salesTeamId);
		queryParam.add(salesPosID);
		queryParam.add(salesHierID);
		queryParam.add(zip);
		queryParam.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTCustAddrByAssign", queryParam, 0, -1);
	}
	
	/**
	 * Gets the customerfor un assign.
	 *
	 * @param zip the zip
	 * @param tenantId the tenant id
	 * @return the customerfor un assign
	 */
	@Override
	public List<Object[]> getCustomerforUnAssign(List<String> zip, Short tenantId) {
		List queryParam = new ArrayList();
		queryParam.add(zip);
		queryParam.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTCustAddrForUnAssign", queryParam, 0, -1);
	}
	/**
	 * Find pri address for all customer.
	 *
	 * @param custId the cust id
	 * @param tenantId the tenant id
	 * @return the list
	 */
	@Override
	public List<TCustAddr> findPriAddressForAllCustomer(List<Integer> custId,
			Short tenantId) {
		List<Object> queryParam = new ArrayList<Object>();
		queryParam.add(custId);
		queryParam.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindPriAddressForAllCustomer", queryParam, 0, -1);
	}
	/**
	 * Find country for all customer.
	 *
	 * @param tenantId the tenant id
	 * @return the list
	 */
	@Override
	public List<String> findCountryForAllCustomer(Short tenantId) {
		List<Object> queryParam = new ArrayList<Object>();
		queryParam.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindCountryForAllCustomer", queryParam, 0, -1);
	}
	/**
	 * Find country for state.
	 *
	 * @param tenantId the tenant id
	 * @return the list
	 */
	@Override
	public List<String> findCountryForState(Short tenantId) {
		List<Object> queryParam = new ArrayList<Object>();
		queryParam.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindCountryForState", queryParam, 0, -1);
	}
	/**
	 * Find addr of all t custs by ids.
	 *
	 * @param custIds the cust ids
	 * @param tenantId the tenant id
	 * @return the list
	 */
	@Override
	public List<TCustAddr> findAddrOfAllTCustsByIDS(List<Integer> custIds,
			Short tenantId) {
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(custIds);
		paramList.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond(
				"FindAddrOfAllTCustsByIDS", paramList, 0, -1);
	}
	/**
	 * Update t cust addrs.
	 *
	 * @param tCustAddrss the t cust addrss
	 * @return the list
	 */
	@Override
	public List<TCustAddr> updateTCustAddrs(List<TCustAddr> tCustAddrss) {
		return genericDAO.update(tCustAddrss);
	}
	/**
	 * Creates the t cust addrs.
	 *
	 * @param tCustAddrss the t cust addrss
	 * @return the list
	 */
	@Override
	public List<TCustAddr> createTCustAddrs(List<TCustAddr> tCustAddrss) {
		return genericDAO.storeBatch(tCustAddrss);
	}
	/**
	 * Find t cust addrs.
	 *
	 * @param addrIds the addr ids
	 * @param tenantId the tenant id
	 * @return the list
	 */
	@Override
	public List<TCustAddr> findTCustAddrs(List<Integer> addrIds,Short tenantId) {
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(addrIds);
		paramList.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond(
				"FindTCustAddrByIds", paramList, 0, -1);
	}
	/**
	 * Find all active addr by cust ids.
	 *
	 * @param custIds the cust ids
	 * @param tenantId the tenant id
	 * @return the list
	 */
	@Override
	public List<Object[]> findAllActiveAddrByCustIds(List<Integer> custIds,
			Short tenantId) {
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(custIds);
		paramList.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond(
				"FindAllActiveAddrByCustIds", paramList, 0, -1);
	}
	/**
	 * Gets the cust fr ass zip.
	 *
	 * @param algmntId the algmnt id
	 * @param bussUnitId the buss unit id
	 * @param salesTeamId the sales team id
	 * @param postalCd the postal cd
	 * @param salesPosId the sales pos id
	 * @param salesHierId the sales hier id
	 * @param tenantId the tenant id
	 * @return the cust fr ass zip
	 */
	@Override
	public List<Object[]> getCustFrAssZip(Long algmntId, Long bussUnitId, Long salesTeamId, List<String> postalCd, List<Long> salesPosId, List<Long> salesHierId, Short tenantId){
		List<Object> queryParam = new ArrayList<Object>();
		queryParam.add(algmntId);
		queryParam.add(bussUnitId);
		queryParam.add(salesTeamId);
		queryParam.add(salesPosId);
		queryParam.add(salesHierId);
		queryParam.add(postalCd);
		queryParam.add(tenantId);
		
		List<Object[]> zipCustList = genericDAO.findEntitiesByNamedQueryMultiCond("FindCustFrAssZip", queryParam, 0, -1);
		return zipCustList;
	}
	/**
	 * Gets the cust fr unassign zip.
	 *
	 * @param unassignZip the unassign zip
	 * @return the cust fr unassign zip
	 */
	@Override
	public List<Object[]> getcustFrUnassignZip(List<String> unassignZip) {
		List<Object> queryParam = new ArrayList<Object>();
		queryParam.add(unassignZip);
		List<Object[]> customersFrUnassignZip;
	 customersFrUnassignZip = genericDAO.findEntitiesByNamedQuery("FindCustFrUnAssZip", queryParam);
		return customersFrUnassignZip; 
	}
	/**
	 * Find search for state.
	 *
	 * @param tenantId the tenant id
	 * @param state the state
	 * @return the list
	 */
	@Override
	public List<String> FindSearchForState(Short tenantId,
			String state) {
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(tenantId);
		paramList.add(state+'%');
		return genericDAO.findEntitiesByNamedQueryMultiCond(
				"FindSearchForState", paramList, 0, -1);
	}

	/**
	 * Gets the univ customer fr bricks.
	 *
	 * @param algmntId the algmnt id
	 * @param bussUnitId the buss unit id
	 * @param salesTeamId the sales team id
	 * @param zipList the zip list
	 * @param tenantId the tenant id
	 * @return the univ customer fr bricks
	 */
	public List<Object[]> getUnivCustomerFrBricks(Long algmntId, Long bussUnitId, Long salesTeamId, List<String> zipList ,Short tenantId){
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(algmntId);
		paramList.add(bussUnitId);
		paramList.add(salesTeamId);
		paramList.add(zipList);
		paramList.add(tenantId);
		StringBuffer query = new StringBuffer();
		query .append("SELECT tcustAddr.cust_id, tcust.cust_cd, tcustAddr.postal_cd, tcust.cust_name FROM t_cust_addr tcustAddr, t_cust tcust WHERE tcustAddr.postal_cd IN ?4 AND tcustAddr.pr_addr_flag = 'Y' AND " +
				"tcustAddr.active_flag = 'Y' AND tcustAddr.cust_id =tcust.cust_id AND tcustAddr.tenant_id = tcust.tenant_id AND tcustAddr.cust_id NOT IN " +
				"(SELECT tCustAlgmnt.cust_id FROM t_cust_algmnt tCustAlgmnt WHERE tCustAlgmnt.algmnt_id =?1 AND tCustAlgmnt.buss_unit_id= ?2 "+
				 "AND tCustAlgmnt.sales_team_id= ?3 AND tCustAlgmnt.tenant_id = ?5)");
		List<Object[]> custList =  genericDAO.findByNativeQueryMultiCond(query.toString(), paramList , 0, -1);
		 return custList;
	}

	/**
	 * Find all customers addr list.
	 *
	 * @param custIds the cust ids
	 * @param tenantId the tenant id
	 * @return the list
	 */
	@Override
	public List<Object[]> findAllCustomersAddrList(List<Integer> custIds,Short tenantId) {
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(custIds);
		paramList.add(tenantId);
		return genericDAO.findEntitiesByNamedQuery("FindAllCustomersAddrList", paramList);
	}
	
	/**
	 * Find all sec addr of t custs by ids.
	 *
	 * @param custIds the cust ids
	 * @param tenantId the tenant id
	 * @return the list
	 */
	@Override
	public List<TCustAddr> findAllSecAddrOfTCustsByIDS(List<Integer> custIds,
			Short tenantId) {
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(custIds);
		paramList.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond(
				"FindAllSecAddrOfTCustsByIDS", paramList, 0, -1);
	}

		
	// newly added for secondary address validation	
	/**
	 * Find all addr of t custs by ids.Added for secondary address validation	
	 *
	 * @param custIdsList the cust ids list
	 * @param tenantId the tenant id
	 * @return the list
	 */
	@Override
	public List<TCustAddr> findAllAddrOfTCustsByIDS(Set<Integer> custIdsList,
			Short tenantId) {
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(custIdsList);
		paramList.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond(
				"FindAllAddrOfTCustsByIDS", paramList, 0, -1);
	}
	/**
	 * Find all customers primary addr list.
	 *
	 * @param custIds the cust ids
	 * @param tenantId the tenant id
	 * @return the list
	 */
	@Override
	public List<Object[]> findAllCustomersPrimaryAddrList(List<Integer> custIds,Short tenantId) {
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(custIds);
		paramList.add(tenantId);
		return genericDAO.findEntitiesByNamedQuery("FindAllCustomersPrimaryAddrList", paramList);
	}
	/**
	 * Find addr by addr ids.
	 *
	 * @param addrIds the addr ids
	 * @param tenantId the tenant id
	 * @return the list
	 */
	@Override
	public List<TCustAddr> findAddrByAddrIds(List<Integer> addrIds,Short tenantId) {
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(addrIds);
		paramList.add(tenantId);
		return genericDAO.findEntitiesByNamedQuery("FindAddrByAddrIds", paramList);
	}
	/**
	 * Retrieve TCustAlgmnt based on given search criteria.
	 * 
	 * @param criteria
	 *            The query criteria and search filter conditions are set
	 * @return List<TCustAlgmnt> list of TCustAlgmnts if it exists against given
	 *         criteria. Returns null if not found
	 */
	@Override
	public List<TCustAddr> findTCustAddrsBySearchCriteria(
			ISearchCriteria criteria) {
		return genericDAO.search(clazz, criteria);
	}
	
}
