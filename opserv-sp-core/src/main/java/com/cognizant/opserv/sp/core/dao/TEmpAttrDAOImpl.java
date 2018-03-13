package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TEmpAttr;
import com.cognizant.opserv.sp.core.entity.TEmpAttrId;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TEmpAttrDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tEmpAttrDAO")
public class TEmpAttrDAOImpl implements TEmpAttrDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TEmpAttrDAOImpl.class);

 //private static final String TEMPATTRID = "tEmpAttrId";

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private final Class<TEmpAttr> clazz;

	public Class<TEmpAttr> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TEmpAttrDAOImpl() {
		super();
		this.clazz = TEmpAttr.class;
	}

	private static final String JPAQL = "select tEmpAttrentity from TEmpAttr tEmpAttrentity";

	private static final String JPACOUNTQL = "select count(*) from TEmpAttr tEmpAttrentity";

	private static final String[] RESTRICTIONS = {
			"tEmpAttrentity.tEmpAttrId.attrId != #{tEmpAttrentity.tEmpAttrId.getAttrId}",
			"tEmpAttrentity.tEmpAttrId.staffId = #{tEmpAttrentity.tEmpAttrId.getStaffId}",
			"tEmpAttrentity.tenantId = #{tEmpAttrentity.getTenantId}",
			"tEmpAttrentity.attrValue like '#{tEmpAttrentity.getAttrValue}%'",
			"Date(tEmpAttrentity.createDt) = '#{tEmpAttrentity.getCreateDt}'",
			"tEmpAttrentity.updatedBy = #{tEmpAttrentity.getUpdatedBy}",
			"tEmpAttrentity.createdBy = #{tEmpAttrentity.getCreatedBy}",
			"Date(tEmpAttrentity.updateDt) = '#{tEmpAttrentity.getUpdateDt}'",
			"tEmpAttrentity.activeFlag = #{tEmpAttrentity.getActiveFlag}" };

	/**
	 * Stores a new TEmpAttr entity object in to the persistent store
	 * 
	 * @param tEmpAttr
	 *            TEmpAttr Entity object to be persisted
	 * @return tEmpAttr Persisted TEmpAttr object
	 */
	public TEmpAttr createTEmpAttr(final TEmpAttr tEmpAttr) {
		LOGGER.info("=========== Create TEmpAttr ===========");
		return genericDAO.store(tEmpAttr);
	}

	/**
	 * Deletes a TEmpAttr entity object from the persistent store
	 * 
	 * @param tEmpAttr
	 *            TEmpAttr Entity object to be deleted
	 */
	public void deleteTEmpAttr(final TEmpAttrId tEmpAttrId) {
		LOGGER.info("=========== Delete TEmpAttr ===========");
		final TEmpAttr tEmpAttr = genericDAO.get(clazz, tEmpAttrId);
		genericDAO.remove(tEmpAttr);
	}

	/**
	 * Updates a TEmpAttr entity object in to the persistent store
	 * 
	 * @param tEmpAttr
	 *            TEmpAttr Entity object to be updated
	 * @return tEmpAttr Persisted TEmpAttr object
	 */
	public TEmpAttr updateTEmpAttr(final TEmpAttr tEmpAttr) {
		LOGGER.info("=========== Update TEmpAttr ===========");
		return genericDAO.update(tEmpAttr);
	}

	/**
	 * Retrieve an TEmpAttr object based on given TEmpAttr TEmpAttrId.
	 * 
	 * @param tEmpAttrId
	 *            the primary key value of the TEmpAttr Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TEmpAttr findTEmpAttrById(final TEmpAttrId tEmpAttrId) {
		LOGGER.info("find TEmpAttr instance with TEmpAttrId: " + tEmpAttrId);
		return genericDAO.get(clazz, tEmpAttrId);
	}

	/**
	 * Retrieve TEmpAttr based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TEmpAttr> list of TEmpAttr if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TEmpAttr> findTEmpAttrs(
			final SearchFilter<TEmpAttr> searchFilter) {
		LOGGER.info("=========== Find TEmpAttrs ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TEmpAttr tEmpAttr = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		// int maxresult = 3;
		// int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tEmpAttrentity", tEmpAttr);

		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TEmpAttrs.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTEmpAttrs(final SearchFilter<TEmpAttr> searchFilter) {
		LOGGER.info("=========== Count TEmpAttr ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TEmpAttr tEmpAttr = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tEmpAttrentity", tEmpAttr);

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	@Override
	public List<TEmpAttr> GetTEmpAttrById(SearchFilter<TEmpAttr> searchFilter) {
              
		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TEmpAttr tEmpAttr = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		List paramList = new ArrayList();
		paramList.add(tEmpAttr.getTEmpAttrId().getStaffId());
		paramList.add(tEmpAttr.getTenantId());
		
		return genericDAO.findEntitiesByNamedQueryMultiCond("GetTEmpAttrById",paramList, index, maxresult);
	}
	
	@Override
	public List<TEmpAttr> getTEmpAttrByAttrId(
			TEmpAttr tEmpAttr) {
		List paramList = new ArrayList();
		
		paramList.add(tEmpAttr.gettAttrDef().getAttrId());
		paramList.add(tEmpAttr.getAttrValue());
		paramList.add(tEmpAttr.getTenantId());
		paramList.add(tEmpAttr.getTEmpAttrId().getStaffId());
		
		return genericDAO.findEntitiesByNamedQueryMultiCond("GetTEmpAttrByAttrId",paramList,0,-1);
	}

	@Override
	public List findEmpExtAttrByGrp(Long algmntId, String bussUnitId,
			Long salesTeamId, Integer staffId, short tenantId) {
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(algmntId);
		paramList.add(Long.valueOf(bussUnitId));
		paramList.add(salesTeamId);
		paramList.add(staffId);
		paramList.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond(
				"FindTEmpAttrByTAttGrpMap", paramList,0,-1);
	}
	
	/**
	 * Retrieve TEmpAttr based on given search criteria using JPA named Query.
	 * The search criteria is of TAttrDef type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TEmpAttr> list of TEmpAttrs if it exists against given
	 *         criteria. Returns null if not found
	 */
	@Override
	public List<TEmpAttr> getTEmpAttrsByTAttrDef(final Long attrId,final Short tenantId) {
		
		List<Object> queryParam = new ArrayList<Object>();
		queryParam.add(0, attrId);
		queryParam.add(1, tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTEmpAttrByTAttrDef", queryParam , 0, -1);
	}
	
	
	public List<TEmpAttr> GetTEmpAttrById(int staffID, Short tenantId) {
		
		//final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		//final TEmpAttr tEmpAttr = searchFilter.getEntityClass();
		//final int maxresult = paginationInfo.getMaxRows();
		//final int index = paginationInfo.getStartIndex();

		List paramList = new ArrayList();
		paramList.add(staffID);
		paramList.add(tenantId);
		
		return genericDAO.findEntitiesByNamedQueryMultiCond("GetTEmpAttrById",paramList, 0, -1);
		
		
		}
	
	
	public List findEmpExtAttrByGrpDef(Long algmntId, String bussUnitId,
			Long salesTeamId, short tenantId) {
		List<Object> paramList = new ArrayList<Object>();
		paramList.add("Sales Position - Employee");
		paramList.add(algmntId);
		paramList.add(Long.valueOf(bussUnitId));
		paramList.add(salesTeamId);
		paramList.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond(
				"FindTEmpAttrByTAttGrpMapDef", paramList,0,-1);
	}
	
	
	@Override
	public List<Object[]> findTEmpAttrByTAttGrpMapAndStaffID(List<Integer> empIdList ,Long algmntId,String bussUnitId,Long salesTeamId,short tenantId) {
		List<Object> paramList = new ArrayList<Object>();
		
		paramList.add(empIdList);
		/*paramList.add(algmntId);
		paramList.add(Long.valueOf(bussUnitId));
		paramList.add(salesTeamId);*/
		paramList.add(tenantId);
		List<Object[]> custAttrList = genericDAO.findEntitiesByNamedQueryMultiCond(
				"FindTEmpAttrByTAttGrpMapValue", paramList,0,-1);
		return custAttrList;
	}
	
	
	
	
}
