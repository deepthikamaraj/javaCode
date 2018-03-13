package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TEmpAlgmnt;
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
 * Class provides API implementation for TEmpAlgmntDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tEmpAlgmntDAO")
public class TEmpAlgmntDAOImpl implements TEmpAlgmntDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TEmpAlgmntDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private static final String TPERS = "tPers";

	private final Class<TEmpAlgmnt> clazz;

	public Class<TEmpAlgmnt> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TEmpAlgmntDAOImpl() {
		super();
		this.clazz = TEmpAlgmnt.class;
	}

	private static final String JPAQL = "select tEmpAlgmntentity from TEmpAlgmnt tEmpAlgmntentity";

	private static final String JPACOUNTQL = "select count(tEmpAlgmntentity) from TEmpAlgmnt tEmpAlgmntentity";

	private static final String[] RESTRICTIONS = {
			"tEmpAlgmntentity.empAlgmntId = #{tEmpAlgmntentity.getEmpAlgmntId}",
			"tEmpAlgmntentity.reasonId = #{tEmpAlgmntentity.getReasonId}",
			"Date(tEmpAlgmntentity.effStartDt) = '#{tEmpAlgmntentity.getEffStartDt}'",
			"Date(tEmpAlgmntentity.effEndDt) = '#{tEmpAlgmntentity.getEffEndDt}'",
			"tEmpAlgmntentity.activeFlag = #{tEmpAlgmntentity.getActiveFlag}",
			"tEmpAlgmntentity.allocTypeId = #{tEmpAlgmntentity.getAllocTypeId}",
			"tEmpAlgmntentity.sysRoleId = #{tEmpAlgmntentity.getSysRoleId}",
			"tEmpAlgmntentity.allocPct = #{tEmpAlgmntentity.getAllocPct}",
			"tEmpAlgmntentity.salesPosId = #{tEmpAlgmntentity.getSalesPosId}",
			"tEmpAlgmntentity.salesHierId = #{tEmpAlgmntentity.getSalesHierId}",
			"tEmpAlgmntentity.orgRoleId = #{tEmpAlgmntentity.getOrgRoleId}",
			"tEmpAlgmntentity.algmntId = #{tEmpAlgmntentity.getAlgmntId}",
			"tEmpAlgmntentity.bussUnitId = #{tEmpAlgmntentity.getBussUnitId}",
			"tEmpAlgmntentity.salesTeamId = #{tEmpAlgmntentity.getSalesTeamId}",
			"tEmpAlgmntentity.createdBy = #{tEmpAlgmntentity.getCreatedBy}",
			"Date(tEmpAlgmntentity.createDt) = '#{tEmpAlgmntentity.getCreateDt}'",
			"tEmpAlgmntentity.updatedBy = #{tEmpAlgmntentity.getUpdatedBy}",
			"Date(tEmpAlgmntentity.updateDt) = '#{tEmpAlgmntentity.getUpdateDt}'",
			"tEmpAlgmntentity.tenantId = #{tEmpAlgmntentity.getTenantId}",
			"tEmpAlgmntentity.tPers.staffId = #{tEmpAlgmntentity.getTPers.getStaffId}" };

	/**
	 * Stores a new TEmpAlgmnt entity object in to the persistent store
	 * 
	 * @param tEmpAlgmnt
	 *            TEmpAlgmnt Entity object to be persisted
	 * @return 
	 * @return tEmpAlgmnt Persisted TEmpAlgmnt object
	 */
	public TEmpAlgmnt TEmpAlgmnt (final TEmpAlgmnt tEmpAlgmnt) {
		LOGGER.info("=========== Create TEmpAlgmnt ===========");
		return genericDAO.store(tEmpAlgmnt);
	}

	/**
	 * Deletes a TEmpAlgmnt entity object from the persistent store
	 * 
	 * @param tEmpAlgmnt
	 *            TEmpAlgmnt Entity object to be deleted
	 */
	public void deleteTEmpAlgmnt(final Long empAlgmntId) {
		LOGGER.info("=========== Delete TEmpAlgmnt ===========");
		final TEmpAlgmnt tEmpAlgmnt = genericDAO.get(clazz, empAlgmntId);
		genericDAO.remove(tEmpAlgmnt);
	}

	/**
	 * Updates a TEmpAlgmnt entity object in to the persistent store
	 * 
	 * @param tEmpAlgmnt
	 *            TEmpAlgmnt Entity object to be updated
	 * @return tEmpAlgmnt Persisted TEmpAlgmnt object
	 */
	public TEmpAlgmnt updateTEmpAlgmnt(final TEmpAlgmnt tEmpAlgmnt) {
		LOGGER.info("=========== Update TEmpAlgmnt ===========");
		return genericDAO.update(tEmpAlgmnt);
	}

	/**
	 * Retrieve an TEmpAlgmnt object based on given TEmpAlgmnt empAlgmntId.
	 * 
	 * @param tEmpAlgmntId
	 *            the primary key value of the TEmpAlgmnt Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TEmpAlgmnt findTEmpAlgmntById(final Long tEmpAlgmntId) {
		LOGGER.info("find TEmpAlgmnt instance with empAlgmntId: " + tEmpAlgmntId);
		return genericDAO.get(clazz, tEmpAlgmntId);
	}

	/**
	 * Retrieve TEmpAlgmnt based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TEmpAlgmnt> list of TEmpAlgmnt if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TEmpAlgmnt> findTEmpAlgmnts(final SearchFilter<TEmpAlgmnt> searchFilter) {
		LOGGER.info("=========== Find TEmpAlgmnts ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TEmpAlgmnt tEmpAlgmnt = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tEmpAlgmntentity", tEmpAlgmnt);

		if (tEmpAlgmnt.getTPers() == null) {
			jpaQuery.bind(TPERS, new TPers());
		} else {
			LOGGER.info("tPers {}"+ tEmpAlgmnt.getTPers());

			jpaQuery.bind(TPERS, tEmpAlgmnt.getTPers());
		}
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TEmpAlgmnts.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTEmpAlgmnts(final SearchFilter<TEmpAlgmnt> searchFilter) {
		LOGGER.info("=========== Count TEmpAlgmnt ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TEmpAlgmnt tEmpAlgmnt = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tEmpAlgmntentity", tEmpAlgmnt);

		if (tEmpAlgmnt.getTPers() == null) {
			jpaCountQuery.bind(TPERS, new TPers());
		} else {
			LOGGER.info("tPers {}"+ tEmpAlgmnt.getTPers());

			jpaCountQuery.bind(TPERS, tEmpAlgmnt.getTPers());
		}

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	
	
	
	@Override
	public List<TEmpAlgmnt> getTEmpAlgmntsByStaffId(Integer staffId, Short tenantId) {
		List paramList = new ArrayList();
		paramList.add(staffId);
		paramList.add(tenantId);
		List<TEmpAlgmnt> result = null;
		 try{
	    	   result = genericDAO.findEntitiesByNamedQueryMultiCond("FindAllTEmpAlgmntsByStaffId", paramList, 0, -1);
	       }
	       catch(PersistenceException e){
	    	   LOGGER.error("Error while retrieving Sales Position Mapped details");
	       }
		return result;
	}
	
	
	@Override
	public List<TEmpAlgmnt> findPrimaryTEmpAlgmntforAllSp(Long algmntId, Long bussUnitId,
			Long salesTeamId, Integer allocTypeId, Short tenantId) {
	//	Date currentDate = new Date();
		Integer statusId =1;//active Tpers status ID = 1
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(algmntId);
		paramList.add(bussUnitId);
		paramList.add(salesTeamId);
		paramList.add(allocTypeId);
		paramList.add(tenantId);
		paramList.add(statusId);
		
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindPrimaryAllocTEmpAlgmntsForAllSp", paramList,0 , -1);
	}
	
	
	@Override
	public List<TEmpAlgmnt> findTEmpAlgmntForSalesPosId(Long spId, Long spHierId,Long algmntId,Long buId,Long stId,Short tenantId) {
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(spId);
		paramList.add(spHierId);
		paramList.add(algmntId);
		paramList.add(buId);
		paramList.add(stId);
		paramList.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("findTEmpAlgmntForSalesPosId", paramList,0 , -1);
	}

	@Override
	public TEmpAlgmnt createTEmpAlgmnt(TEmpAlgmnt tEmpAlgmnt) {
		LOGGER.info("=========== Create TEmpAlgmnt ===========");
		return genericDAO.store(tEmpAlgmnt);
	}

	/**
	 * Find emp algmnt.
	 *
	 * @param staffid the staffid
	 * @param algmntId the algmnt id
	 * @param bussUnitId the buss unit id
	 * @param salesTeamId the sales team id
	 * @param salesPosId the sales pos id
	 * @param salesHierId the sales hier id
	 * @param tenantId the tenant id
	 * @return the list
	 */
	@Override
	public List<Object[]> findEmpAlgmnt(List<Integer> staffids, Long algmntId,
			Long bussUnitId, Long salesTeamId, Long salesPosId,
			Long salesHierId, Short tenantId) {
		List<Object>param=new ArrayList<>();
		param.add(staffids);
		param.add(algmntId);
		param.add(bussUnitId);
		param.add(salesTeamId);
		param.add(salesPosId);
		param.add(salesHierId);
		param.add(tenantId);
		
		return genericDAO.findEntitiesByNamedQuery("FindEmpAlgmnt",param);
	}

	@Override
	public List<Object[]> findEmpAlgmntByIds(Long staffid, Long algmntId,
			Long bussUnitId, Long salesTeamId, Long salesPosId,
			Long salesHierId, Short tenantId) {
		List<Object>param=new ArrayList<>();
		param.add(staffid.intValue());
		param.add(algmntId);
		param.add(bussUnitId);
		param.add(salesTeamId);
		param.add(salesPosId);
		param.add(salesHierId);
		param.add(tenantId);
		
		return genericDAO.findEntitiesByNamedQuery("findEmpAlgmntByIds",param);
	}
@Override
	public List<String> fetchEmailIdFrEmp(List<Long> spIds, Short tenantId) {
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(spIds);
		paramList.add(tenantId);
		List<String> mailIdList = genericDAO.findEntitiesByNamedQueryMultiCond("fetchEmailIdFrEmp", paramList, 0, -1);
	return mailIdList;
}	
	@Override
	public List<Object[]> findPrimaryTEmpAlgmnt(Long algmntId, Long bussUnitId,
			Long salesTeamId, Long salesPosId, Long salesHierId,
			Integer primaryAllocId, Integer value1, Short tenantId) {
		List<Object>param=new ArrayList<>();
		param.add(algmntId);
		param.add(bussUnitId);
		param.add(salesTeamId);
		param.add(salesPosId);
		param.add(salesHierId);
		param.add(primaryAllocId);
		param.add(value1);
		param.add(tenantId);
		
		
		return genericDAO.findEntitiesByNamedQuery("FindPrimaryTEmpAlgmnt",param);	}
	
   @Override
	public List<Object[]> findEmpDetails(Long algmntID,Long buID,Long stID,Long spID,Long shID,
			 Short tenantId, String localeID,int index, int maxresult) {

		List<Object> paramList = new ArrayList<>();
		paramList.add(algmntID);
		paramList.add(buID);
		paramList.add(stID);
		paramList.add(spID);
		paramList.add(shID);
		
		//paramList.add(activeFlag);
//		paramList.add(effEdDt);
		paramList.add(tenantId);
		paramList.add(localeID);
		
		return genericDAO.findEntitiesByNamedQueryMultiCond(
				"FindEmpDetails", paramList,index, maxresult);
	}

	@Override
	public List<Integer> fetchStaffIds(List<Long> spIds, Short tenantId) {
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(spIds);
		paramList.add(tenantId);
		List<Integer> mailIdList = genericDAO.findEntitiesByNamedQueryMultiCond("fetchStaffIds", paramList, 0, -1);
	return mailIdList;
	}
	
	
}
