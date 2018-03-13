package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TPers;
import com.cognizant.opserv.sp.core.entity.TPersAddr;
import com.cognizant.opserv.sp.core.entity.TPersAddrId;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TPersAddrDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tPersAddrDAO")
public class TPersAddrDAOImpl implements TPersAddrDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TPersAddrDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private static final String TPERS = "tPers";

	private final Class<TPersAddr> clazz;

	public Class<TPersAddr> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TPersAddrDAOImpl() {
		super();
		this.clazz = TPersAddr.class;
	}

	private static final String JPAQL = "select tPersAddrentity from TPersAddr tPersAddrentity";

	private static final String JPACOUNTQL = "select count(*) from TPersAddr tPersAddrentity";

	private static final String[] RESTRICTIONS = {
			"tPersAddrentity.tPersAddrId.staffId = #{tPersAddrentity.tPersAddrId.getStaffId}",
			"tPersAddrentity.tPersAddrId.addrId = #{tPersAddrentity.tPersAddrId.getAddrId}",
			"tPersAddrentity.doorNumber like '#{tPersAddrentity.getDoorNumber}%'",
			"tPersAddrentity.streetName like '#{tPersAddrentity.getStreetName}%'",
			"tPersAddrentity.addrLine like '#{tPersAddrentity.getAddrLine}%'",
			"tPersAddrentity.city like '#{tPersAddrentity.getCity}%'",
			"tPersAddrentity.state like '#{tPersAddrentity.getState}%'",
			"tPersAddrentity.cntry like '#{tPersAddrentity.getCntry}%'",
			"tPersAddrentity.prAddrFlag like '#{tPersAddrentity.getPrAddrFlag}%'",
			"tPersAddrentity.activeFlag = #{tPersAddrentity.getActiveFlag}",
			"tPersAddrentity.addrTypeId = #{tPersAddrentity.getAddrTypeId}",
			"tPersAddrentity.postalCd like '#{tPersAddrentity.getPostalCd}%'",
			"tPersAddrentity.createdBy = #{tPersAddrentity.getCreatedBy}",
			"Date(tPersAddrentity.createDt) = '#{tPersAddrentity.getCreateDt}'",
			"tPersAddrentity.updatedBy = #{tPersAddrentity.getUpdatedBy}",
			"Date(tPersAddrentity.updateDt) = '#{tPersAddrentity.getUpdateDt}'",
			"tPersAddrentity.tenantId = #{tPersAddrentity.getTenantId}",
			"tPersAddrentity.postalExtn like '#{tPersAddrentity.getPostalExtn}%'",
			"tPersAddrentity.addrLine2 like '#{tPersAddrentity.getAddrLine2}%'",
			"tPersAddrentity.addrLine3 like '#{tPersAddrentity.getAddrLine3}%'",
			"tPersAddrentity.addrLine4 like '#{tPersAddrentity.getAddrLine4}%'",
			"tPersAddrentity.tPers.staffId = #{tPersAddrentity.tPers.getStaffId}" };

	/**
	 * Stores a new TPersAddr entity object in to the persistent store
	 * 
	 * @param tPersAddr
	 *            TPersAddr Entity object to be persisted
	 * @return tPersAddr Persisted TPersAddr object
	 */
	public TPersAddr createTPersAddr(final TPersAddr tPersAddr) {
		LOGGER.info("=========== Create TPersAddr ===========");
		return genericDAO.store(tPersAddr);
	}

	/**
	 * Deletes a TPersAddr entity object from the persistent store
	 * 
	 * @param tPersAddr
	 *            TPersAddr Entity object to be deleted
	 */
	public void deleteTPersAddr(final TPersAddrId tPersAddrId) {
		LOGGER.info("=========== Delete TPersAddr ===========");
		final TPersAddr tPersAddr = genericDAO.get(clazz, tPersAddrId);
		genericDAO.remove(tPersAddr);
	}

	/**
	 * Updates a TPersAddr entity object in to the persistent store
	 * 
	 * @param tPersAddr
	 *            TPersAddr Entity object to be updated
	 * @return tPersAddr Persisted TPersAddr object
	 */
	public TPersAddr updateTPersAddr(final TPersAddr tPersAddr) {
		LOGGER.info("=========== Update TPersAddr ===========");
		return genericDAO.update(tPersAddr);
	}

	/**
	 * Retrieve an TPersAddr object based on given TPersAddr TPersAddrId.
	 * 
	 * @param tPersAddrId
	 *            the primary key value of the TPersAddr Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TPersAddr findTPersAddrById(final TPersAddrId tPersAddrId) {
		LOGGER.info("find TPersAddr instance with TPersAddrId: " + tPersAddrId);
		return genericDAO.get(clazz, tPersAddrId);
	}

	/**
	 * Retrieve TPersAddr based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TPersAddr> list of TPersAddr if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TPersAddr> findTPersAddrs(final SearchFilter<TPersAddr> searchFilter) {
		LOGGER.info("=========== Find TPersAddrs ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TPersAddr tPersAddr = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tPersAddrentity", tPersAddr);

		if (tPersAddr.getTPers() == null) {
			jpaQuery.bind(TPERS, new TPers());
		} else {
			LOGGER.info("tPers {}"+ tPersAddr.getTPers());

			jpaQuery.bind(TPERS, tPersAddr.getTPers());
		}
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TPersAddrs.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTPersAddrs(final SearchFilter<TPersAddr> searchFilter) {
		LOGGER.info("=========== Count TPersAddr ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TPersAddr tPersAddr = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tPersAddrentity", tPersAddr);

		if (tPersAddr.getTPers() == null) {
			jpaCountQuery.bind(TPERS, new TPers());
		} else {
			LOGGER.info("tPers {}"+ tPersAddr.getTPers());

			jpaCountQuery.bind(TPERS, tPersAddr.getTPers());
		}

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TPersAddr based on given search criteria using JPA named Query.
	 * The search criteria is of TPers type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TPersAddr> list of TPersAddrs if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TPersAddr> getTPersAddrsByTPers(final SearchFilter<TPers> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTPersAddrByTPers", queryParam, index, maxresult);
	}

	/**
	 * Count TPersAddr based on given search criteria using JPA named Query.
	 * The search criteria is of TPers type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTPersAddrsByTPers(final SearchFilter<TPers> searchFilter) {

		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());

		return genericDAO.findEntitiesByNamedQuery("CountTPersAddrsByTPers", queryParam);
	}

	public TPersAddr findTPersAddrById(int staffId ,int addrId,short tenantId){
		List queryparam = new ArrayList();
		queryparam.add(addrId);
		queryparam.add(staffId);
		queryparam.add(tenantId);
		List<TPersAddr> list =  genericDAO.findEntitiesByNamedQueryMultiCond("FindAddressByAddrId", queryparam, 0, -1);
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		return null;
		
		//return (TPersAddr) genericDAO.findEntitiesByNamedQueryMultiCond("FindAddressByAddrId", queryparam, 0, -1).get(0);
		
		
	}

	@Override
	public List<TPersAddr> findPriAddressForEmp(List<Integer> staffId,
			Short tenantId,String prAddrFlag,Character activeFlag) {
		List<Object> queryParam = new ArrayList<Object>();
		queryParam.add(staffId);
		queryParam.add(tenantId);
		queryParam.add(prAddrFlag);
		queryParam.add(activeFlag);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindPriAddressForEmp", queryParam, 0, -1);
	}
	
	/* (non-Javadoc)
	 * @see com.cognizant.opserv.sp.core.dao.TPersAddrDAO#findAddressesForEmp(java.lang.Integer, java.lang.Short, java.lang.String)
	 */
	@Override
	public List<Object[]> findAddressesForEmp(Integer staffId,Short tenantId,String locale){
		List<Object> queryParam = new ArrayList<Object>();
		queryParam.add(staffId);
		queryParam.add(tenantId);
		queryParam.add(locale);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindAllAddressesForEmp",queryParam,0,-1);
		
	}
	
	
}
