package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.common.util.DateUtil;
import com.cognizant.opserv.sp.core.entity.TPrd;
import com.cognizant.opserv.sp.core.entity.TPrdAlgmnt;
import com.cognizant.opserv.sp.core.entity.TPrdAttr;
import com.cognizant.opserv.sp.core.entity.TSalesPos;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TPrdAlgmntDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tPrdAlgmntDAO")
public class TPrdAlgmntDAOImpl implements TPrdAlgmntDAO {
	
	/**
	 * instantiate Logger Object
	 */
	
	
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TPrdAlgmntDAOImpl.class);
	
	private final String fetchViewProductsForSearch ="SELECT tPrdAlgmnt from TPrdAlgmnt tPrdAlgmnt, TPrd tPrd where  tPrdAlgmnt.tPrd.prdId=tPrd.prdId ";
						
	//private final String fetchViewProductsForSearchForExtAttr ="SELECT tPrdAlgmnt from TPrdAlgmnt tPrdAlgmnt, TPrd tPrd,TPrdAttr tPrdAttr";
	private final String fetchViewProductsForSearchForExtAttr ="SELECT tPrdAlgmnt from TPrdAlgmnt tPrdAlgmnt, TPrd tPrd";
	private final String fetchViewProductsForSearchForExtAttr2 =" where tPrdAlgmnt.tPrd.prdId=tPrd.prdId ";
			


	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	/* private static final String TSALESPOS = "tSalesPos"; */
	private static final String TPRD = "tPrd";

	private final Class<TPrdAlgmnt> clazz;

	public Class<TPrdAlgmnt> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TPrdAlgmntDAOImpl() {
		super();
		this.clazz = TPrdAlgmnt.class;
	}

	private static final String JPAQL = "select tPrdAlgmntentity from TPrdAlgmnt tPrdAlgmntentity";

	private static final String JPACOUNTQL = "select count(*) from TPrdAlgmnt tPrdAlgmntentity";

	private static final String[] RESTRICTIONS = {
			"tPrdAlgmntentity.tPrdAlgmntId.prdAlgmntId = #{tPrdAlgmntentity.tPrdAlgmntId.getPrdAlgmntId}",
			"tPrdAlgmntentity.tPrdAlgmntId.salesPosId = #{tPrdAlgmntentity.tPrdAlgmntId.getSalesPosId}",
			"tPrdAlgmntentity.tPrdAlgmntId.prdId like '#{tPrdAlgmntentity.tPrdAlgmntId.getPrdId}%'",
			"tPrdAlgmntentity.tPrdAlgmntId.salesHierId = #{tPrdAlgmntentity.tPrdAlgmntId.getSalesHierId}",
			"tPrdAlgmntentity.activeFlag = #{tPrdAlgmntentity.getActiveFlag}",
			"Date(tPrdAlgmntentity.effStartDt) = '#{tPrdAlgmntentity.getEffStartDt}'",
			"Date(tPrdAlgmntentity.effEndDt) = '#{tPrdAlgmntentity.getEffEndDt}'",
			"tPrdAlgmntentity.wtg like '#{tPrdAlgmntentity.getWtg}%'",
			"tPrdAlgmntentity.bussUnitId = #{tPrdAlgmntentity.getBussUnitId}",
			"tPrdAlgmntentity.salesTeamId = #{tPrdAlgmntentity.getSalesTeamId}",
			"tPrdAlgmntentity.createdBy = #{tPrdAlgmntentity.getCreatedBy}",
			"Date(tPrdAlgmntentity.createDt) = '#{tPrdAlgmntentity.getCreateDt}'",
			"tPrdAlgmntentity.updatedBy = #{tPrdAlgmntentity.getUpdatedBy}",
			"Date(tPrdAlgmntentity.updateDt) = '#{tPrdAlgmntentity.getUpdateDt}'",
			"tPrdAlgmntentity.tenantId = #{tPrdAlgmntentity.getTenantId}",
			"tPrdAlgmntentity.salesPosId = #{tPrdAlgmntentity.getSalesPosId}",
			"tPrdAlgmntentity.salesHierId = #{tPrdAlgmntentity.getSalesHierId}",
			"tPrdAlgmntentity.tPrd.prdId = #{tPrdAlgmntentity.tPrd.getPrdId}",
			"tPrdAlgmntentity.algmntId = #{tPrdAlgmntentity.getAlgmntId}",
			"tPrdAlgmntentity.bussUnitId = #{tPrdAlgmntentity.getBussUnitId}",
			"tPrdAlgmntentity.salesTeamId = #{tPrdAlgmntentity.getSalesTeamId}" };

	/**
	 * Stores a new TPrdAlgmnt entity object in to the persistent store
	 * 
	 * @param tPrdAlgmnt
	 *            TPrdAlgmnt Entity object to be persisted
	 * @return tPrdAlgmnt Persisted TPrdAlgmnt object
	 */
	public TPrdAlgmnt createTPrdAlgmnt(final TPrdAlgmnt tPrdAlgmnt) {
		LOGGER.info("=========== Create TPrdAlgmnt ===========");
		return genericDAO.store(tPrdAlgmnt);
	}

	/**
	 * Deletes a TPrdAlgmnt entity object from the persistent store
	 * 
	 * @param tPrdAlgmnt
	 *            TPrdAlgmnt Entity object to be deleted
	 */
	public void deleteTPrdAlgmnt(final Long prdAlgmntId) {
		LOGGER.info("=========== Delete TPrdAlgmnt ===========");
		final TPrdAlgmnt tPrdAlgmnt = genericDAO.get(clazz, prdAlgmntId);
		tPrdAlgmnt.setActiveFlag('N');
		genericDAO.update(tPrdAlgmnt);
	}

	/**
	 * Updates a TPrdAlgmnt entity object in to the persistent store
	 * 
	 * @param tPrdAlgmnt
	 *            TPrdAlgmnt Entity object to be updated
	 * @return tPrdAlgmnt Persisted TPrdAlgmnt object
	 */
	public TPrdAlgmnt updateTPrdAlgmnt(final TPrdAlgmnt tPrdAlgmnt) {
		LOGGER.info("=========== Update TPrdAlgmnt ===========");
		return genericDAO.update(tPrdAlgmnt);
	}

	/**
	 * Retrieve an TPrdAlgmnt object based on given TPrdAlgmnt prdAlgmntId.
	 * 
	 * @param tPrdAlgmntId
	 *            the primary key value of the TPrdAlgmnt Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TPrdAlgmnt findTPrdAlgmntById(final Long tPrdAlgmntId) {
		LOGGER.info("find TPrdAlgmnt instance with prdAlgmntId: "
				+ tPrdAlgmntId);
		return genericDAO.get(clazz, tPrdAlgmntId);
	}

	/**
	 * Retrieve TPrdAlgmnt based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TPrdAlgmnt> list of TPrdAlgmnt if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TPrdAlgmnt> findTPrdAlgmnts(
			final SearchFilter<TPrdAlgmnt> searchFilter) {
		LOGGER.info("=========== Find TPrdAlgmnts ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TPrdAlgmnt tPrdAlgmnt = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		// int maxresult = 3;
		// int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tPrdAlgmntentity", tPrdAlgmnt);

		if (tPrdAlgmnt.gettPrd() == null) {
			jpaQuery.bind(TPRD, new TPrd());
		} else {
			LOGGER.info("tPrd {}"+ tPrdAlgmnt.gettPrd());

			jpaQuery.bind(TPRD, tPrdAlgmnt.gettPrd());
		}
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TPrdAlgmnts.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTPrdAlgmnts(final SearchFilter<TPrdAlgmnt> searchFilter) {
		LOGGER.info("=========== Count TPrdAlgmnt ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TPrdAlgmnt tPrdAlgmnt = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tPrdAlgmntentity",
				tPrdAlgmnt);

		if (tPrdAlgmnt.gettPrd() == null) {
			jpaCountQuery.bind(TPRD, new TPrd());
		} else {
			LOGGER.info("tPrd {}"+ tPrdAlgmnt.gettPrd());

			jpaCountQuery.bind(TPRD, tPrdAlgmnt.gettPrd());
		}

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TPrdAlgmnt based on given search criteria using JPA named Query.
	 * The search criteria is of TSalesPos type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TPrdAlgmnt> list of TPrdAlgmnts if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TPrdAlgmnt> getTPrdAlgmntsByTSalesPos(
			final SearchFilter<TSalesPos> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTPrdAlgmntByTSalesPos",
				queryParam, index, maxresult);
	}

	/**
	 * Count TPrdAlgmnt based on given search criteria using JPA named Query.
	 * The search criteria is of TSalesPos type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTPrdAlgmntsByTSalesPos(
			final SearchFilter<TSalesPos> searchFilter) {

		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());
		return genericDAO.findEntitiesByNamedQuery(
				"CountTPrdAlgmntsByTSalesPos", queryParam);
	}

	/**
	 * Retrieve TPrdAlgmnt based on given search criteria using JPA named Query.
	 * The search criteria is of TPrd type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TPrdAlgmnt> list of TPrdAlgmnts if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TPrdAlgmnt> getTPrdAlgmntsByTPrd(
			final SearchFilter<TPrd> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTPrdAlgmntByTPrd",
				queryParam, index, maxresult);
	}

	/**
	 * Count TPrdAlgmnt based on given search criteria using JPA named Query.
	 * The search criteria is of TPrd type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTPrdAlgmntsByTPrd(final SearchFilter<TPrd> searchFilter) {

		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());
		return genericDAO.findEntitiesByNamedQuery("CountTPrdAlgmntsByTPrd",
				queryParam);
	}

	@Override
	public List findProductTemplatesByAlignment(Long productId, Integer boId,
			Short tenantId) {
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(productId);
		paramList.add(boId);
		paramList.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond(
				"FindTPrdAlgmntByJoinQuery", paramList, 0, -1);
	}

	public List<TPrdAlgmnt> findTPrdAlgmntsByPrdId(Long prdId) {
		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(prdId);
		LOGGER.info("find TPrdAlgmnt instance with prdId: " + prdId);
		List<TPrdAlgmnt> tPrdAlgmnt = genericDAO.findEntitiesByNamedQuery(
				"FindTPrdAlgmntsByPrdId", queryParam);
		return tPrdAlgmnt;
	}

	@Override
	public Long countOfPrdAlgmnts(List paramList) {
		List<Object> count = genericDAO.findEntitiesByNamedQueryMultiCond(
				"CountTPrdAlgmntsTenant", paramList, -1, -1);
		Long countL = (Long) count.get(0);
		return countL;
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void delPrdAlgmnts(Date effEndDt,Short tenantId, Long salesPosId,
			Long salesHierId, Long alignId, Long buId, Long salesTeamId,
			Long prdIdStr,Integer updatedBy) {
		List paramList = new ArrayList();
		paramList.add(DateUtil.getCurrentDate());
		paramList.add(updatedBy);
		paramList.add(effEndDt);
		paramList.add(tenantId);
		paramList.add(salesPosId);
		paramList.add(salesHierId);
		paramList.add(alignId);
		paramList.add(buId);
		paramList.add(salesTeamId);
		paramList.add(prdIdStr);
		
		genericDAO.updateEntitiesByNamedQueryMultiCond(
				"DeleteTPrdAlgmntsTenant", paramList, -1, -1);
	}

	@Override
	public List<TPrdAlgmnt> findTPrdAlgmnts(Short tenantId, Long salesHierId,
			Long bussinessUnitId, Long salesTeamId, Long alignmentId,
			Long salesPosId, Long prdId) {
		List list = new ArrayList();
		list.add(tenantId);
		list.add(salesPosId);
		list.add(salesHierId);
		list.add(alignmentId);
		list.add(bussinessUnitId);
		list.add(salesTeamId);
		list.add('Y');
		list.add(prdId);

		// TODO Auto-generated method stub
		return genericDAO.findEntitiesByNamedQueryMultiCond(
				"FindTPrdAlgmntsForUnAssign", list, 0, -1);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TPrdAlgmnt> findProductsBySalePosId(final Long salesPosId,
			Long algmntId, Long bussUnitId, Long salesTeamId, int index,
			int noOfResult, Short tenantId) {
		Date today = DateUtil.getCurrentDate();
		List paramList = new ArrayList();
		paramList.add(salesPosId);
		paramList.add(algmntId);
		paramList.add(bussUnitId);
		paramList.add(salesTeamId);
		paramList.add(tenantId);
		paramList.add(today);

		paramList.add('Y');
		List<TPrdAlgmnt> list = genericDAO.findEntitiesByNamedQueryMultiCond(
				"FindTPrdAlgmntBySalesPosId", paramList, index, noOfResult);

		return list;
	}

	@Override
	public List<TPrdAlgmnt> findTPrdAlgmntsForUnAssignSP(Long salesPosId,
			Long salesHierId, Long alignmentId, Long bussinessUnitId,
			Long salesTeamId, Short tenantId, boolean flag) {
		List queryParam = new ArrayList();
		queryParam.add(salesPosId);
		queryParam.add(salesHierId);
		queryParam.add(alignmentId);
		queryParam.add(bussinessUnitId);
		queryParam.add(salesTeamId);
		queryParam.add(flag ? 'Y' : 'N');
		queryParam.add(tenantId);

		return genericDAO.findEntitiesByNamedQueryMultiCond(
				"FindTPrdAlgmntsForUnAssignSP", queryParam, 0, -1);
	}

	@Override
	public Long fetchCountOfPrdForSPandChild(Set<Long> childSP,
			Long alignmentId, Long bussinessUnitId, Long salesTeamId,
			Short tenantId, Date currentDate) {

		List paramList = new ArrayList();
		paramList.add(tenantId);
		//paramList.add(salesPosId);
		paramList.add(childSP);
		paramList.add(alignmentId);
		paramList.add(bussinessUnitId);
		paramList.add(salesTeamId);
		paramList.add('Y');
		paramList.add(currentDate);
		List<Object> count = genericDAO.findEntitiesByNamedQueryMultiCond(
				"FindCountOfPrdForSPandChild", paramList, 0, -1);
		Long countL = (Long) count.get(0);
		return countL;
	}
	
	
	@Override
	public Long fetchCountOfPrdForSP(Long salesPositionId,Long salesHierId,
			Short tenantId, Date currentDate) {

		List paramList = new ArrayList();
		paramList.add(tenantId);
		paramList.add(salesPositionId);
		paramList.add(salesHierId);
		paramList.add('Y');
		paramList.add(currentDate);
		List<Object> count = genericDAO.findEntitiesByNamedQueryMultiCond(
				"FindCountOfPrdForSP", paramList, 0, -1);
		Long countL = (Long) count.get(0);
		return countL;
	}

	@Override
	public List<TPrdAlgmnt> findProductsWithAlignmentInfoByList(
			List<Long> prodIdList, long salePosId, int index, int noOfResult,
			short tenantId) {

		List paramList = new ArrayList();
		paramList.add(salePosId);
		paramList.add(prodIdList);
		paramList.add(tenantId);

		List<TPrdAlgmnt> listOfPrd = new ArrayList<TPrdAlgmnt>();
		List<TPrdAlgmnt> list = genericDAO.findEntitiesByNamedQueryMultiCond(
				"findProductsWithAlignmentInfoByList", paramList, index,
				noOfResult);
		for (Long prdId : prodIdList) {
			for (TPrdAlgmnt prdAlgmnt : list) {
				if (prdId.longValue()==prdAlgmnt.gettPrd().getPrdId().longValue()) {
					listOfPrd.add(prdAlgmnt);
				}
			}
		}

		// TODO Auto-generated method stub
		return listOfPrd;
	}

	@Override
	public List<TPrdAlgmnt> getProductDetailsForProductSearch(
			SearchFilter<TPrdAlgmnt> searchFilter) {
		final TPrdAlgmnt tPrdAlgmnt = searchFilter.getEntityClass();
		List paramList = new ArrayList();
		paramList.add(tPrdAlgmnt.getAlgmntId());
		paramList.add(tPrdAlgmnt.getBussUnitId());
		paramList.add(tPrdAlgmnt.getSalesTeamId());
		paramList.add(tPrdAlgmnt.getSalesPosId());
		paramList.add(tPrdAlgmnt.getSalesHierId());
		paramList.add("%" + tPrdAlgmnt.gettPrd().getPrdName() + "%");
		paramList.add(tPrdAlgmnt.getTenantId());
		paramList.add(tPrdAlgmnt.getActiveFlag());
		return genericDAO.findEntitiesByNamedQueryMultiCond(
				"FindTPrdAlgmntsForProductSearch", paramList, 0, -1);
	}

	
	//Added By 407986 To Verify Products are Assigned to SP or not
	
	public List<Object> fetchAssignedProducts(List<String> productIds,
			Long spId, Long shId, Short tenantId) {

		List<Object> paramList = new ArrayList<>();
		paramList.add(productIds);
		paramList.add(spId);
		paramList.add(shId);
		paramList.add(tenantId);

		return genericDAO.findEntitiesByNamedQueryMultiCond(
				"FetchAssignedProducts", paramList, 0, -1);

	}

	@Override
	public List<TPrdAlgmnt> findProductsWithAlignmentInfoByPrdIdList(
			List<Long> prodIdlist, int index, int noOfResult, Short tenantId) {
		List paramList = new ArrayList();
		paramList.add(prodIdlist);
		paramList.add(tenantId);

		List<TPrdAlgmnt> listOfPrd = new ArrayList<TPrdAlgmnt>();
		List<TPrdAlgmnt> list = genericDAO.findEntitiesByNamedQueryMultiCond(
				"findProductsWithAlignmentInfoByPrdIdList", paramList, index,
				noOfResult);
		for (Long prdId : prodIdlist) {
			for (TPrdAlgmnt prdAlgmnt : list) {
				if (prdId.longValue()  == prdAlgmnt.gettPrd().getPrdId().longValue()) {
					listOfPrd.add(prdAlgmnt);
				}
			}
		}

		// TODO Auto-generated method stub
		return listOfPrd;
	}
	
	@Override
	public List<TPrdAlgmnt> fetchPrdAlgDtBySalesPosEffEndDt(short tenantId) {

		List<Object> paramList = new ArrayList<>();
		paramList.add(tenantId);
		paramList.add(DateUtil.getCurrentDate());
		paramList.add(DateUtil.getCurrentDate());
		return genericDAO.findEntitiesByNamedQueryMultiCond(
				"FetchTPrdAlgmntsByJoinQuery", paramList, 0, -1);
	}
	
	/**
	 * Method to fetch product Algmnts for the list of prodAlgmntList
	 * @param prdAlIdList
	 * @return List<TPrdAlgmnt>
	 */
	@Override
	public List<TPrdAlgmnt> fetchPrdAlgmntsFrRemoveCr(List<Long> prdAlgIdList,short tenantId) {
		List paramList = new ArrayList();
		paramList.add(prdAlgIdList);
		paramList.add(tenantId);
		paramList.add(DateUtil.getCurrentDate());
		List<TPrdAlgmnt> tPrdAlgmnt  = genericDAO.findEntitiesByNamedQueryMultiCond(
				"FetchPrdAlgmntsFrRemoveCr", paramList, 0, -1);
		return tPrdAlgmnt;
	}

	@Override
	public List<TPrdAlgmnt> fetchPrdALgmntsDtsforValidate(Long prdId,
			Long spId, Long algnmntId, Long bussId, Long salesTeamId,
			Long saleshierId, short tenantId) {
		List<Object> paramList = new ArrayList<>();
		paramList.add(prdId);
		paramList.add(spId);
		paramList.add(algnmntId);
		paramList.add(bussId);
		paramList.add(salesTeamId);
		paramList.add(saleshierId);
		paramList.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond(
				"FindTPrdAlgmntsDts", paramList, 0, -1);
	}

	@Override
	public List<TPrdAlgmnt> searchProductsBySalePosId(long salePosId,
			long algmntId, long bussId, long salesTeamId, 
			Short tenantId,SearchFilter<TPrdAlgmnt> searchFilter) {
		
		TPrdAlgmnt tPrdAlgmnt = searchFilter.getEntityClass();
		PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		
		Set<TPrdAttr> tPrdAttrs = tPrdAlgmnt.gettPrd().getTPrdAttrss();
		List<String> attrValList = new ArrayList<String>();
		List<String> attridList = new ArrayList<String>();
		List<Long> attrIdList = new ArrayList<Long>();
	//	if(tPrdAttrs!= null && tPrdAttrs.size()>0){
		for(TPrdAttr prdAttr : tPrdAttrs){
			if(prdAttr.getAttrValue()!= null && !prdAttr.getAttrValue().equals("") && prdAttr.getTPrdAttrId().getAttrId()!=null){
				attrValList.add(prdAttr.getAttrValue());
				attridList.add(prdAttr.getTPrdAttrId().getAttrId().toString());
			  }
			}
	//	}
		

		StringBuffer query = new StringBuffer();
		
		
		if(attrValList.size() == 0){
			query.append(fetchViewProductsForSearch);
	
				query.append(" AND tPrdAlgmnt.algmntId = " + tPrdAlgmnt.getAlgmntId() + " AND tPrdAlgmnt.bussUnitId = " + tPrdAlgmnt.getBussUnitId() 
				+ " AND tPrdAlgmnt.salesTeamId = " + tPrdAlgmnt.getSalesTeamId() + " AND tPrdAlgmnt.salesPosId = " + tPrdAlgmnt.getSalesPosId() + " AND tPrdAlgmnt.tenantId = " + tPrdAlgmnt.getTenantId() 
				+ " AND (tPrdAlgmnt.effEndDt IS NULL OR tPrdAlgmnt.effEndDt >= '"+ DateUtil.getDefaultFormat(tPrdAlgmnt.getEffEndDt(), Locale.ENGLISH)+"')" +" AND tPrdAlgmnt.activeFlag = 'Y'");
			
			if(tPrdAlgmnt.gettPrd() != null && tPrdAlgmnt.gettPrd().getPrdCd() != null && !tPrdAlgmnt.gettPrd().getPrdCd().equalsIgnoreCase("")){
				query.append(" AND tPrd.prdCd LIKE '%" + tPrdAlgmnt.gettPrd().getPrdCd() + "%'");
			}
			
			if(tPrdAlgmnt.gettPrd() != null && tPrdAlgmnt.gettPrd().getPrdDesc() != null && !tPrdAlgmnt.gettPrd().getPrdDesc().equalsIgnoreCase("")){
				query.append(" AND tPrd.prdDesc LIKE '%" + tPrdAlgmnt.gettPrd().getPrdDesc() + "%'");
			}
			
			if(tPrdAlgmnt.gettPrd() != null && tPrdAlgmnt.gettPrd().getPrdId() != null && tPrdAlgmnt.gettPrd().getPrdId()!= 0){
				query.append(" AND tPrd.prdId LIKE  '%" + tPrdAlgmnt.gettPrd().getPrdId()+ "%'");
			}
			
			if(tPrdAlgmnt.gettPrd() != null && tPrdAlgmnt.gettPrd().getPrdName() != null && !tPrdAlgmnt.gettPrd().getPrdName().equalsIgnoreCase("")){
				query.append(" AND tPrd.prdName LIKE '%"  + tPrdAlgmnt.gettPrd().getPrdName()+ "%'");
			}
			
			
		}
			
			if(attrValList.size() != 0){
				
				query.append(fetchViewProductsForSearchForExtAttr);
				if(tPrdAttrs!= null && tPrdAttrs.size()>0){
				for(int i=0;i<attrValList.size();i++){
						query.append(",TPrdAttr tPrdAttr"+i);
						
						
					}
				
				
				query.append(fetchViewProductsForSearchForExtAttr2);
				for(int j=0;j<attrValList.size();j++){
					query.append(" AND tPrd.prdId=tPrdAttr"+j+".tPrdAttrId.prdId");
					
					
				}
				}
			
							//query.append(fetchViewProductsForSearchForExtAttr2);
				
				query.append(" AND tPrdAlgmnt.algmntId = " + tPrdAlgmnt.getAlgmntId() + " AND tPrdAlgmnt.bussUnitId = " + tPrdAlgmnt.getBussUnitId() 
						 + " AND tPrdAlgmnt.salesTeamId = " + tPrdAlgmnt.getSalesTeamId() + " AND tPrdAlgmnt.salesPosId = " + tPrdAlgmnt.getSalesPosId() + " AND tPrdAlgmnt.tenantId = " + tPrdAlgmnt.getTenantId() 
						 + " AND (tPrdAlgmnt.effEndDt IS NULL OR tPrdAlgmnt.effEndDt >= '"+ DateUtil.getDefaultFormat(tPrdAlgmnt.getEffEndDt(), Locale.ENGLISH)+"')" +" AND tPrdAlgmnt.activeFlag = 'Y'");
			
				
				if(tPrdAlgmnt.gettPrd() != null && tPrdAlgmnt.gettPrd().getPrdCd() != null && !tPrdAlgmnt.gettPrd().getPrdCd().equalsIgnoreCase("")){
					query.append(" AND tPrd.prdCd LIKE '%" + tPrdAlgmnt.gettPrd().getPrdCd() + "%'");
				}
				
				if(tPrdAlgmnt.gettPrd() != null && tPrdAlgmnt.gettPrd().getPrdDesc() != null && !tPrdAlgmnt.gettPrd().getPrdDesc().equalsIgnoreCase("")){
					query.append(" AND tPrd.prdDesc LIKE '%" + tPrdAlgmnt.gettPrd().getPrdDesc() + "%'");
				}
				
				if(tPrdAlgmnt.gettPrd() != null && tPrdAlgmnt.gettPrd().getPrdId() != null && tPrdAlgmnt.gettPrd().getPrdId()!= 0){
					query.append(" AND tPrd.prdId LIKE  '%" + tPrdAlgmnt.gettPrd().getPrdId()+ "%'");
				}
				
				if(tPrdAlgmnt.gettPrd() != null && tPrdAlgmnt.gettPrd().getPrdName() != null && !tPrdAlgmnt.gettPrd().getPrdName().equalsIgnoreCase("")){
					query.append(" AND tPrd.prdName LIKE '%"  + tPrdAlgmnt.gettPrd().getPrdName()+ "%'");
				}
				
				
			/*for(TPrdAttr attr : tPrdAttrs){
			}
				if(attr.getAttrValue() != null && !attr.getAttrValue().equals("")){
					query.append(" myTCustAttr.attr_value LIKE '%" + attr.getAttrValue() +"%'" );
			}
			}*/
			
				//for (TPrdAttr prdAtr : tPrdAttrs) {
					if(attridList!= null && attrValList!= null && attrValList.size()>0 && attridList.size()>0){
						for(int i=0;i<attrValList.size();i++){
							//query.append(",TPrdAttr tPrdAttr"+i);
							query.append(" AND tPrdAttr"+i+".tPrdAttrId.attrId='"
									//+ prdAtr.getTPrdAttrId().getAttrId()
									+attridList.get(i)
									+ "' AND tPrdAttr"+i+".attrValue LIKE"
									+ "'%"
									+ attrValList.get(i) + "%'");
						}
					}
				//}
			
				//}
			
					
			
			}
			List<TPrdAlgmnt> productList= genericDAO.findEntitiesByBuildQuery(query.toString(), paginationInfo.getStartIndex(), paginationInfo.getMaxRows());
			
			
			return productList;
	}
	
	@Override
	public Long findTPrdAlgmntsForUnAssignSPCount(Long salesPosId,Long salesHierId, Long alignmentId, Long bussinessUnitId,Long salesTeamId, Short tenantId, boolean flag) {
		List queryParam = new ArrayList();
		queryParam.add(salesPosId);
		queryParam.add(salesHierId);
		queryParam.add(alignmentId);
		queryParam.add(bussinessUnitId);
		queryParam.add(salesTeamId);
		queryParam.add(flag ? 'Y' : 'N');
		queryParam.add(tenantId);
		List<Object> count =   genericDAO.findEntitiesByNamedQueryMultiCond("FindTPrdAlgmntsForUnAssignSPCount", queryParam, 0, -1);
		Long countL = (Long) count.get(0);
		return countL;
	}

	@Override
	public List<Object[]> findAssignedProducts(List<Long> listOfPrdIds,
			Long algmntId, String bussUnitId, Long salesTeamId,
			Long salesPosId, Short tenantId) {
		List<Object> paramList = new ArrayList<Object>();	
		
		
		paramList.add(listOfPrdIds);
		paramList.add(algmntId);
		paramList.add(Long.parseLong(bussUnitId));
		paramList.add(salesTeamId);
		paramList.add(salesPosId);
		paramList.add(tenantId);
				
		return genericDAO.findEntitiesByNamedQueryMultiCond("GetAssignPrd", paramList, 0, -1);
	}

	@Override
	public List<TPrdAlgmnt> fetchPrdALgmntsDtsByPrdId(Long prdId,
			short tenantId) {
        List<Object> paramList = new ArrayList<Object>();	
		paramList.add(prdId);
		paramList.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("GetAssignPrdByPrdId", paramList, 0, -1);
	}

	@Override
	public List<TPrdAlgmnt> getPrdAlListForSP(SalesPosition salesPosition,
			Short tenantId, List<Long> prdIdList) {
	        List paramList = new ArrayList();	
			paramList.add(salesPosition.getId());
			paramList.add(salesPosition.getHierarchy().getId());
			paramList.add(salesPosition.getAlignmment().getId());
			paramList.add(salesPosition.getAlignmment().getSalesTeam().getBusinessUnit().getId());
			paramList.add(salesPosition.getAlignmment().getSalesTeam().getId());
			paramList.add(prdIdList);
			paramList.add(tenantId);
			
			return genericDAO.findEntitiesByNamedQueryMultiCond("getPrdAlListForSP", paramList, 0, -1);
	}

	@Override
	public List<String> getProductNamesForSalesPosition(Long spId, Long shId,Date currentDate, Short tenantId) {
		List paramList = new ArrayList();
		paramList.add(spId);
		paramList.add(shId);
		paramList.add(currentDate);
		paramList.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("getProductNamesForSalesPosition", paramList, 0, -1);
	}

	/**
	 * Find prdAlgmntId from prdid and sales postion.
	 *
	 * @param prdId the product id
	 * @param spId the salesposition id
	 * @return the long
	 */
	@Override
	public Long findTPrdAlgmntByTPrdAndSPId(Long prdId, Long SPId) {
		// TODO Auto-generated method stub
		List paramList = new ArrayList();
		paramList.add(prdId);
		paramList.add(SPId);
		return (Long)genericDAO.findEntitiesByNamedQueryMultiCond("findTPrdAlgmntByTPrdAndSPId", paramList,0 , -1).get(0);
	}

	
	@Override
	public Long fetchCountOfPrdForSalesPositions(List<Long> salesPosIds,Short tenantId) {

		List paramList = new ArrayList();
		paramList.add(tenantId);
		paramList.add(salesPosIds);
		paramList.add(DateUtil.getCurrentDate());
		List<Object> count = genericDAO.findEntitiesByNamedQueryMultiCond(
				"fetchCountOfPrdForSalesPositions", paramList, 0, -1);
		Long countL = (Long) count.get(0);
		return countL;
	}

	@Override
	public List<String> getProductNamesForSalesPositions(List<Long> spIdList,
			Short tenantId) {
			List paramList = new ArrayList();
			paramList.add(spIdList);
			paramList.add(DateUtil.getCurrentDate());
			paramList.add(tenantId);
			return genericDAO.findEntitiesByNamedQueryMultiCond("getProductNamesForSalesPositions", paramList, 0, -1);
			}
	
	
	
}