package com.cognizant.opserv.sp.core.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TAlgmntSalesHier;
import com.cognizant.opserv.sp.core.entity.TAlgmntSalesTeam;
import com.cognizant.opserv.sp.core.entity.TSalesPos;
import com.cognizant.opserv.sp.core.entity.TTerrZipAlgmnt;
import com.cognizant.opserv.sp.core.entity.TWkflwStatus;
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
 * Class provides API implementation for TSalesPosDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tSalesPosDAO")
public class TSalesPosDAOImpl implements TSalesPosDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TSalesPosDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private static final String TALGMNTSALESHIER = "tAlgmntSalesHier";
	private static final String TALGMNTSALESTEAM = "tAlgmntSalesTeam";
	private final Class<TSalesPos> clazz;

	public Class<TSalesPos> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TSalesPosDAOImpl() {
		super();
		this.clazz = TSalesPos.class;
	}

	private static final String JPAQL = "select tSalesPosentity from TSalesPos tSalesPosentity";

	private static final String JPACOUNTQL = "select count(*) from TSalesPos tSalesPosentity";

	private static final String[] RESTRICTIONS = {
			"tSalesPosentity.tSalesPosId.salesHierId = #{tSalesPosentity.tSalesPosId.getSalesHierId}",
			"tSalesPosentity.tSalesPosId.salesPosId = #{tSalesPosentity.tSalesPosId.getSalesPosId}",
			"tSalesPosentity.posName like '#{tSalesPosentity.getPosName}%'",
			"tSalesPosentity.activeFlag = #{tSalesPosentity.getActiveFlag}",
			"Date(tSalesPosentity.effStartDt) = '#{tSalesPosentity.getEffStartDt}'",
			"Date(tSalesPosentity.effEndDt) = '#{tSalesPosentity.getEffEndDt}'",
			"tSalesPosentity.posCd like '#{tSalesPosentity.getPosCd}%'",
			"tSalesPosentity.statusId = #{tSalesPosentity.getStatusId}",
			"tSalesPosentity.algmntId = #{tSalesPosentity.getAlgmntId}",
			"tSalesPosentity.bussUnitId = #{tSalesPosentity.getBussUnitId}",
			"tSalesPosentity.salesTeamId = #{tSalesPosentity.getSalesTeamId}",
			"tSalesPosentity.createdBy = #{tSalesPosentity.getCreatedBy}",
			"Date(tSalesPosentity.createDt) = '#{tSalesPosentity.getCreateDt}'",
			"tSalesPosentity.updatedBy = #{tSalesPosentity.getUpdatedBy}",
			"Date(tSalesPosentity.updateDt) = '#{tSalesPosentity.getUpdateDt}'",
			"tSalesPosentity.tenantId = #{tSalesPosentity.getTenantId}",
			"tSalesPosentity.shapePolygon = #{tSalesPosentity.getShapePolygon}",
			"tSalesPosentity.podTitle like '#{tSalesPosentity.getPodTitle}%'",
			"tSalesPosentity.tAlgmntSalesHier.salesHierId = #{tSalesPosentity.tAlgmntSalesHier.getSalesHierId}",
			"tSalesPosentity.tAlgmntSalesTeam.tAlgmntSalesTeamId = #{tSalesPosentity.tAlgmntSalesTeam.getTAlgmntSalesTeamId}",
			"tSalesPosentity.salesPosTypeId = #{tSalesPosentity.getSalesPosTypeId}",
			"tSalesPosentity.tWkflwStatus.statusId = #{tSalesPosentity.tWkflwStatus.getStatusId}" };

	/**
	 * Stores a new TSalesPos entity object in to the persistent store
	 * 
	 * @param tSalesPos
	 *            TSalesPos Entity object to be persisted
	 * @return tSalesPos Persisted TSalesPos object
	 */
	public TSalesPos createTSalesPos(final TSalesPos tSalesPos) {
		LOGGER.info("=========== Create TSalesPos ===========");
		return genericDAO.store(tSalesPos);
	}

	/**
	 * Deletes a TSalesPos entity object from the persistent store
	 * 
	 * @param tSalesPos
	 *            TSalesPos Entity object to be deleted
	 */
	public void deleteTSalesPos(final Long tSalesPosId) {
		LOGGER.info("=========== Delete TSalesPos ===========");
		final TSalesPos tSalesPos = genericDAO.get(clazz, tSalesPosId);
		genericDAO.remove(tSalesPos);
	}

	/**
	 * Updates a TSalesPos entity object in to the persistent store
	 * 
	 * @param tSalesPos
	 *            TSalesPos Entity object to be updated
	 * @return tSalesPos Persisted TSalesPos object
	 */
	public TSalesPos updateTSalesPos(final TSalesPos tSalesPos) {
		LOGGER.info("=========== Update TSalesPos ===========");
		return genericDAO.update(tSalesPos);
	}

	/**
	 * Retrieve an TSalesPos object based on given TSalesPos TSalesPosId.
	 * 
	 * @param tSalesPosId
	 *            the primary key value of the TSalesPos Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TSalesPos findTSalesPosById(final Long tSalesPosId) {
		LOGGER.info("find TSalesPos instance with TSalesPosId: " + tSalesPosId);
		return genericDAO.get(clazz, tSalesPosId);
	}

	/**
	 * Retrieve TSalesPos based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TSalesPos> list of TSalesPos if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TSalesPos> findTSalesPoss(final SearchFilter<TSalesPos> searchFilter) {
		LOGGER.info("=========== Find TSalesPoss ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TSalesPos tSalesPos = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tSalesPosentity", tSalesPos);

		if (tSalesPos.getTAlgmntSalesHier() == null) {
			jpaQuery.bind(TALGMNTSALESHIER, new TAlgmntSalesHier());
		} else {
			LOGGER.info("tAlgmntSalesHier {}"+ tSalesPos.getTAlgmntSalesHier());

			jpaQuery.bind(TALGMNTSALESHIER, tSalesPos.getTAlgmntSalesHier());
		}

		if (tSalesPos.getTAlgmntSalesTeam() == null) {
			jpaQuery.bind(TALGMNTSALESTEAM, new TAlgmntSalesTeam());
		} else {
			LOGGER.info("tAlgmntSalesTeam {}"+ tSalesPos.getTAlgmntSalesTeam());

			jpaQuery.bind(TALGMNTSALESTEAM, tSalesPos.getTAlgmntSalesTeam());
		}

		/*if (tSalesPos.getTWkflwStatus() == null) {
			jpaQuery.bind(TWKFLWSTATUS, new TWkflwStatus());
		} else {
			LOGGER.info("tWkflwStatus {}", tSalesPos.getTWkflwStatus());

			jpaQuery.bind(TWKFLWSTATUS, tSalesPos.getTWkflwStatus());
		}*/
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TSalesPoss.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTSalesPoss(final SearchFilter<TSalesPos> searchFilter) {
		LOGGER.info("=========== Count TSalesPos ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TSalesPos tSalesPos = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tSalesPosentity", tSalesPos);

		if (tSalesPos.getTAlgmntSalesHier() == null) {
			jpaCountQuery.bind(TALGMNTSALESHIER, new TAlgmntSalesHier());
		} else {
			LOGGER.info("tAlgmntSalesHier {}"+ tSalesPos.getTAlgmntSalesHier());

			jpaCountQuery.bind(TALGMNTSALESHIER, tSalesPos.getTAlgmntSalesHier());
		}

		if (tSalesPos.getTAlgmntSalesTeam() == null) {
			jpaCountQuery.bind(TALGMNTSALESTEAM, new TAlgmntSalesTeam());
		} else {
			LOGGER.info("tAlgmntSalesTeam {}"+ tSalesPos.getTAlgmntSalesTeam());

			jpaCountQuery.bind(TALGMNTSALESTEAM, tSalesPos.getTAlgmntSalesTeam());
		}

		/*if (tSalesPos.getTWkflwStatus() == null) {
			jpaCountQuery.bind(TWKFLWSTATUS, new TWkflwStatus());
		} else {
			LOGGER.info("tWkflwStatus {}", tSalesPos.getTWkflwStatus());

			jpaCountQuery.bind(TWKFLWSTATUS, tSalesPos.getTWkflwStatus());
		}
*/
		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TSalesPos based on given search criteria using JPA named Query.
	 * The search criteria is of TAlgmntSalesHier type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TSalesPos> list of TSalesPoss if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TSalesPos> getTSalesPossByTAlgmntSalesHier(final SearchFilter<TAlgmntSalesHier> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTSalesPosByTAlgmntSalesHier", queryParam, index,
				maxresult);
	}

	/**
	 * Count TSalesPos based on given search criteria using JPA named Query.
	 * The search criteria is of TAlgmntSalesHier type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTSalesPossByTAlgmntSalesHier(final SearchFilter<TAlgmntSalesHier> searchFilter) {

		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());
		return genericDAO.findEntitiesByNamedQuery("CountTSalesPossByTAlgmntSalesHier", queryParam);
	}

	/**
	 * Retrieve TSalesPos based on given search criteria using JPA named Query.
	 * The search criteria is of TAlgmntSalesTeam type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TSalesPos> list of TSalesPoss if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TSalesPos> getTSalesPossByTAlgmntSalesTeam(final SearchFilter<TAlgmntSalesTeam> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTSalesPosByTAlgmntSalesTeam", queryParam, index,
				maxresult);
	}

	/**
	 * Count TSalesPos based on given search criteria using JPA named Query.
	 * The search criteria is of TAlgmntSalesTeam type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTSalesPossByTAlgmntSalesTeam(final SearchFilter<TAlgmntSalesTeam> searchFilter) {

		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());
		return genericDAO.findEntitiesByNamedQuery("CountTSalesPossByTAlgmntSalesTeam", queryParam);
	}

	/**
	 * Retrieve TSalesPos based on given search criteria using JPA named Query.
	 * The search criteria is of TWkflwStatus type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TSalesPos> list of TSalesPoss if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TSalesPos> getTSalesPossByTWkflwStatus(final SearchFilter<TWkflwStatus> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTSalesPosByTWkflwStatus", queryParam, index, maxresult);
	}

	/**
	 * Count TSalesPos based on given search criteria using JPA named Query.
	 * The search criteria is of TWkflwStatus type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTSalesPossByTWkflwStatus(final SearchFilter<TWkflwStatus> searchFilter) {

		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());
		return genericDAO.findEntitiesByNamedQuery("CountTSalesPossByTWkflwStatus", queryParam);
	}

	public void updateTSalesPossByID(String query)
	{
		genericDAO.executeNativeQuery(query);		
	}

	@Override
	public List<String> findTSalesPossPolygon(String saleposId) {
		return genericDAO.findByNativeQuery("select AsText(shape_polygon) as shape from t_sales_pos_geo where sales_pos_id="+saleposId);
	}
	
	public List<TSalesPos> FindTSalesPosByPodTitle(final SearchFilter<TSalesPos> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTSalesPosByPodTitle", queryParam, index, maxresult);
	}
	
	public List<TSalesPos> FindTSalesPosBySalesHierId(final SearchFilter<TSalesPos> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTSalesPosBySalesHierId", queryParam, index, maxresult);
	}
	
	@Override
	public List<String> findTSalesPosPOD(String filter,short tenantId)
	{
		return genericDAO.findByNativeQuery("select pos_name from t_sales_pos where "+ filter+" AND tenant_id="+tenantId);
		
	}
	
	@Override
	public List<String> findTSalesPosPODForSF(String filter1, String filter2, String filter3, short tenantId)
	{
		return genericDAO.findByNativeQuery("select t_sales_pos.pos_name from t_sales_pos, t_sales_pos_geo where t_sales_pos."+ filter1+" and t_sales_pos."+ filter2+" " +
				"and t_sales_pos."+ filter3+" AND t_sales_pos.sales_hier_id=t_sales_pos_geo.sales_hier_id and t_sales_pos.sales_pos_id=t_sales_pos_geo.sales_pos_id and " +
						"t_sales_pos.root_hier_id=t_sales_pos_geo.root_hier_id and t_sales_pos_geo.shape_polygon is not null and tenant_id="+tenantId+" " +
								"order by t_sales_pos.pos_name");
		
	}
	
	@Override
	public List<String> findTSalesPosPODForSFFilter(String filter1, String filter2, short tenantId)
	{
		return genericDAO.findByNativeQuery("select t_sales_pos.pos_name from t_sales_pos, t_sales_pos_geo where t_sales_pos."+ filter1+" and t_sales_pos."+ filter2+" " +
				"and t_sales_pos.sales_hier_id=t_sales_pos_geo.sales_hier_id and t_sales_pos.sales_pos_id=t_sales_pos_geo.sales_pos_id and " +
						"t_sales_pos.root_hier_id=t_sales_pos_geo.root_hier_id and t_sales_pos_geo.shape_polygon is not null and tenant_id="+tenantId+" " +
								"order by t_sales_pos.pos_name");
		
	}
	
	@Override
	public List<String> findTSalesPosPODByPRNID(long prnSaleId) {
		return genericDAO.findByNativeQuery("select pod_title from t_sales_pos where prn_sales_pos_id"+ prnSaleId);
	}
	
	public List<String> findTSalesPosByTitlePod(final SearchFilter<TSalesPos> searchFilter){
		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TSalesPos tSalesPos = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();		
		List paramList = new ArrayList();
		//paramList.add(tSalesPos.getPodTitle());
		paramList.add(tSalesPos.getTenantId());
		//return genericDAO.countEntitiesNamedQuery("FindTSalesPosByPod", queryParam)
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTSalesPosByPod",paramList, index, maxresult);	
		
	}	

	
	/**
	 * 
	 */
	public Long findMaxSalesPosId(final SearchFilter<TSalesPos> searchFilter) {
		
		List paramList = new ArrayList();
		paramList.add(searchFilter.getEntityClass().getTenantId());
		
		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		List maxSalesPosIdList = genericDAO.findEntitiesByNamedQueryMultiCond("FindMaxSalesPosId", paramList, index, maxresult);
		Long salesPosId= (Long)maxSalesPosIdList.get(0);
		if(salesPosId!=null){
			return salesPosId;
		}else{
			return 9999l;
		}
//		return salesPosId;
	}

	@Override
	public List<BigInteger> findTSalesPosPODParent(String filter) {		
		 return genericDAO.findByNativeQuery("select prn_sales_pos_id from t_sales_pos where "+ filter);
	}
	
	/**
	 * find by salesposition Id and tenant ID
	 */
	@Override
	public TSalesPos getTSalesPosByTSalesPosIdandTenantId(Long salesPosId,short tenantId) {	
		
		List paramList = new ArrayList();
        //paramList.add(tSalesPosId);
		paramList.add(Long.valueOf(salesPosId));
        paramList.add(tenantId);        
        List<TSalesPos> listTSalesPos= genericDAO.findEntitiesByNamedQueryMultiCond("FindTSalesPosByIdandTenantId", paramList, 0, -1);		
		
		return listTSalesPos.get(0);
	}
	
	
	public TSalesPos getTSalesPosByTSalesPosId(Long salesPosId) {
		
		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(salesPosId);
		List<TSalesPos> listTSalesPos = genericDAO.findEntitiesByNamedQueryMultiCond(
				"FindTSalesPosById", queryParam, -1, -1);
		return listTSalesPos.get(0);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Object> getParentIdsForSp(Long salesPosId, Long salesHierId) {
		List paramList = new ArrayList();
        paramList.add(salesPosId);
        paramList.add(salesHierId);
		List<Object> list= genericDAO.findEntitiesByNamedQueryMultiCond("FindTSalPosHierId", paramList, 0, -1);
	    return list;
	}
	
	@Override
	public void updateWithPolygon(TSalesPos tPos) {
		// TODO Auto-generated method stub
		updateTSalesPos(tPos);
	}
	@Override
	public List<Object[]> getChildrenById(List paramList,final Short tenantId){
		List queryParam = new ArrayList();
		queryParam.add(paramList);
		queryParam.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindChildrenBySalPosId", queryParam, -1, -1);		
	}
	
	@Override
	public List<TSalesPos> createSalesPosTree(Long algmntId, Long bussUnitId, Long salesTeamId,Short tenantId) {
		List queryParam = new ArrayList();
		queryParam.add(algmntId);
		queryParam.add(bussUnitId);
		queryParam.add(salesTeamId);
		queryParam.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("createTreeQuery", queryParam, 0, -1);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public TSalesPos findTSalesPosBySalesPosId(Long salesPosId,Short tenantId) {
		List paramList = new ArrayList();
		paramList.add(salesPosId);
		paramList.add(tenantId);
		List<TSalesPos> pos = genericDAO.findEntitiesByNamedQueryMultiCond("findTSalesPositionBySalesPosId", paramList, 0, -1);
		return (pos!=null&&pos.size()>0)?pos.get(0):null;
	}
	@Override
	public List<TSalesPos> findTSalesPosBySalesHierId(Long algmntId, Long bussUnitId, Long salesTeamId, Long salesHierId,Short usrTenantId) {
		List paramList = new ArrayList();
		paramList.add(salesHierId);
		paramList.add(usrTenantId);
		paramList.add(algmntId);
		paramList.add(bussUnitId);
		paramList.add(salesTeamId);
		List<TSalesPos> tSalesPosList =  genericDAO.findEntitiesByNamedQueryMultiCond("findTSalesPositionBySalesHierId", paramList, 0, -1);
		return tSalesPosList;
	}
	
	@Override
	public List<TSalesPos> findTSalesPosBySalesHierIdFrMtr(Long algmntId, Long bussUnitId, Long salesTeamId, Long salesHierId,Short usrTenantId) {
		List paramList = new ArrayList();
		paramList.add(salesHierId);
		paramList.add(usrTenantId);
		paramList.add(algmntId);
		paramList.add(bussUnitId);
		paramList.add(salesTeamId);
		//paramList.add(salesPosId);
		List<TSalesPos> tSalesPosList =  genericDAO.findEntitiesByNamedQueryMultiCond("findTSalesPositionBySalesHierIdFrMtr", paramList, 0, -1);
		return tSalesPosList;
	}
	@Override
	public List<TSalesPos> findHierValuesByIDs(Long salesHierId, Long algmntId,
			Long bussUnitId, Long salesTeamId) {
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(salesHierId);
		paramList.add(algmntId);
		paramList.add(bussUnitId);
		paramList.add(salesTeamId);
		//paramList.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond(
				"FindTSalesPosByIds", paramList,0,-1);
	}
	@Override
	public List<TSalesPos> findTSalesPositionByPrnSalesHerId(Long salesHierId,Short tenantId) {
		List queryParam = new ArrayList();
		queryParam.add(salesHierId);
		queryParam.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTSalesPositionByPrnSalesHerId", queryParam, 0, -1);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<Object[]> getChildrenBySPHId(List paramList, Short tenantId) {
		List queryParam = new ArrayList(paramList);
		//queryParam.add(paramList);
		queryParam.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindChildrenBySPHId", queryParam, -1, -1);	
	}
	
	@Override
	public List<Object[]> searchChildren(List spIds,String searchValue,String searchCode, Short tenantId) {
		List queryParam = new ArrayList();
		queryParam.add(spIds);
		queryParam.add("%"+searchValue+"%");
		queryParam.add("%"+searchCode+"%");
		queryParam.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("searchChildren", queryParam, -1, -1);
	}
	
	@Override
	public List<Object[]> getSpChildrenById(List paramList,final Short tenantId){
		List queryParam = new ArrayList();
		queryParam.add(paramList);
		queryParam.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("SPFindChildrenBySalPosId", queryParam, -1, -1);		
	}

	@Override
	public List<Object[]> findChildSalesPositions(List paramList) {
		
		return genericDAO.findEntitiesByNamedQueryMultiCond("fetchChildSalesPositionForSP", paramList,0,-1);
	}

	@Override
	public List<Object[]> findTSalesPosWhichisActive(
			SearchFilter<TSalesPos> searchFilter) {
		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TSalesPos tSalesPos = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();		
		List paramList = new ArrayList();
		paramList.add(tSalesPos.getCreatedBy());
		paramList.add(tSalesPos.getSalesPosId());
		paramList.add(tSalesPos.getActiveFlag());
		paramList.add(tSalesPos.getEffStartDt());
		paramList.add(tSalesPos.getTenantId());
		//paramList.add(id);
		return  null; //genericDAO.findEntitiesByNamedQueryMultiCond("FindSalesPositionActive",paramList, index, maxresult);
	}
	
	@Override
	public List<Object[]> findTSalesPosWhichisActiveChild(
			SearchFilter<TSalesPos> searchFilter) {
		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TSalesPos tSalesPos = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();		
		List paramList = new ArrayList();
		paramList.add(tSalesPos.getSalesPosId());
		paramList.add(tSalesPos.getTAlgmntSalesHier().getSalesHierId());
		paramList.add(tSalesPos.getActiveFlag());
		paramList.add(tSalesPos.getEffStartDt());
		paramList.add(tSalesPos.getTenantId());
		//paramList.add(id);
		return  null; //genericDAO.findEntitiesByNamedQueryMultiCond("FindSalesPositionActiveChild",paramList, index, maxresult);
	}

	@Override
	public List<Object[]> getTSalesPossByTSalesPosAndEmp(
			SearchFilter<TSalesPos> searchFilter) {
		
		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TSalesPos tSalesPos = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();		
		List paramList = new ArrayList();
		paramList.add(tSalesPos.getCreatedBy());
		paramList.add(tSalesPos.getEffStartDt());
		paramList.add(tSalesPos.getTAlgmntSalesTeam().getTAlgmntSalesTeamId().getAlgmntId());
		paramList.add(tSalesPos.getTAlgmntSalesTeam().getTAlgmntSalesTeamId().getBussUnitId());
		paramList.add(tSalesPos.getTAlgmntSalesTeam().getTAlgmntSalesTeamId().getSalesTeamId());
		paramList.add(tSalesPos.getTenantId());
		paramList.add(tSalesPos.getActiveFlag());
		return  null; //genericDAO.findEntitiesByNamedQueryMultiCond("findTSalesPositionAndEmpAlgmnt",paramList, index, maxresult);
	}

	@Override
	public Long maxOfTSalesPoss() {
		List<Object> list = genericDAO.findEntitiesByNamedQuery("MaxOfTSalesPoss");
		return (Long)list.get(0) ;
	}
	
	@Override
	public List<Object> fetchParentIdsForSp(Long salesPosId, Long salesHierId,Short tenantId) {
		List paramList = new ArrayList();
        paramList.add(salesPosId);
        paramList.add(salesHierId);
        paramList.add(tenantId);
		List<Object> list= genericDAO.findEntitiesByNamedQueryMultiCond("FetchTSalPosHierId", paramList, 0, -1);
	    return list;
}

	@Override
	public List<Object> uniqueChildsalesHierID(Long prnSalesHierId, Short tenantId) {
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(prnSalesHierId);
		paramList.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("UniqueChildsalesHierID", paramList,0,-1);
	}

	@Override
	public List<TSalesPos> findChildSalesPositionsBySPId(Long spId,Long spHierId,Long algmntId,Long buId,Long stId,Short tenantId) {
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(spId);
		paramList.add(spHierId);
		paramList.add(algmntId);
		paramList.add(buId);
		paramList.add(stId);
		paramList.add(tenantId);
		return  genericDAO.findEntitiesByNamedQueryMultiCond("findChildSalesPositionsBySPId",paramList,0,-1);
	}
	
	
	@Override
	public List<TSalesPos> findSalesPositionsByALBUST(Long spId,Long spHierId,Long algmntId,Long buId,Long stId,Short tenantId) {
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(spId);
		paramList.add(spHierId);
		paramList.add(algmntId);
		paramList.add(buId);
		paramList.add(stId);
		paramList.add(tenantId);
		return  genericDAO.findEntitiesByNamedQueryMultiCond("findActiveSalesPositionsByalbust",paramList,0,-1);
	}

		@Override
	public Long getSpIDByPosCd(List paramList) {
			Long id=null;
		List list =genericDAO.findEntitiesByNamedQueryMultiCond("getSpIdByPosCd",paramList,0,-1);
		if(!list.isEmpty()){
			return (long) list.get(0);
		}
	
		return id;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<Object[]> getActiveChildrenById(List paramList,final Short tenantId){
		List queryParam = new ArrayList(paramList);
		//queryParam.addAll(paramList);
		queryParam.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindActiveChildrenBySalPosId", queryParam, -1, -1);		
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Object[]> getSpIdsByPosName(String posName, Short tenantId) {		
		List queryParam = new ArrayList();
		queryParam.add(posName);
		queryParam.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindSPIdByPOSName", queryParam, -1, -1);	
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Object[]> getSpIdsByPosCode(String posCode, Short tenantId) {
		List queryParam = new ArrayList();
		queryParam.add(posCode);
		queryParam.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindSPIdByPOSCode", queryParam, -1, -1);
	}	
	
	@Override
		public List<Object[]> fetchParentForSp(Long salesposId, Long saleshierId,
				Long algmntId, Long bussUnitId, Long salesTeamId, Short tenantId) {
			List paramList = new ArrayList();
	        paramList.add(salesposId);
	        paramList.add(saleshierId);
	        paramList.add(algmntId);
	        paramList.add(bussUnitId);
	        paramList.add(salesTeamId);
	        paramList.add(tenantId);
			List<Object[]> list= genericDAO.findEntitiesByNamedQueryMultiCond("FetchTSalPosHier", paramList, 0, -1);
		    return list;
		}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<Object[]> getActiveChildrenSPById(List paramList,final Short tenantId){
		List queryParam = new ArrayList(paramList);
		//queryParam.addAll(paramList);
		queryParam.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("findActiveChildSalesPositionsBySPId", queryParam, -1, -1);		
	}
	@Override
	public List<Object[]> getActiveSalesPosNHierByALBUST(Long algmntId,
			Long bussUnitId, Long salesTeamId, Short tenantId) {

		List queryParam = new ArrayList();
		queryParam.add(algmntId);
		queryParam.add(bussUnitId);
		queryParam.add(salesTeamId);
		queryParam.add(tenantId);

		return genericDAO.findEntitiesByNamedQueryMultiCond(
				"findActiveSalesPosNSalesHierIDByALBUST", queryParam, -1, -1);
	}

	@Override
	public List<Object[]> findSalesPositionsTreeDetails(Set<Long> spId,
			Short tenantId) {
		List queryParam = new ArrayList();
		queryParam.add(spId);
		queryParam.add(tenantId);

		return genericDAO.findEntitiesByNamedQueryMultiCond(
				"findSalesPositionTreeDetails", queryParam, 0, -1);
		
	}
	
	
	@Override
	public List<Object[]> getSalesPosMtrVals(List queryParam) {		
		return genericDAO.findEntitiesByNamedQueryMultiCond("GetSalesPosMetricCal", queryParam, 0, -1);		
	}
	
	
	@Override
	public List<Object[]> getCompareSalesPosMtrVals(List queryParam) {		
		return genericDAO.findEntitiesByNamedQueryMultiCond("GetCompareSalesPosMetricCal", queryParam, 0, -1);		
	}
	
	@Override
	public List<Object[]> getActiveChildrenForReportsBySalPosId(List queryParam) {		
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindActiveChildrenForReportsBySalPosIds", queryParam, 0, -1);		
	}	
	
	/**
	 * This DAO method fetches date vlaue of SP by SpId from DB Function
	 * @param salesPosId
	 * @return SP EffEnd Date
	 */
	@Override
	public List<Date> findSPEffEndDt(long spId){

		return  genericDAO.findByNativeQuery(
				"select find_sp_eff_dt("+spId+")");
	}
	
	@Override
	public List<TSalesPos> FindAllActiveTSalesPoss(Long algmntId,Date date,Short tenantId) {
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(algmntId);
		paramList.add(date);
		paramList.add(tenantId);
		return  genericDAO.findEntitiesByNamedQueryMultiCond("FindAllActiveTSalesPoss",paramList,0,-1);
	}

	@Override
	public List<Object[]> getActiveCDSBySalPosId(List queryParam) {		
		return genericDAO.findEntitiesByNamedQueryMultiCond("GetActiveCDSBySalPosId", queryParam, 0, -1);		
	}
	
	@Override
	public List<TSalesPos> getAllInActiveTSalesPoss(Date date,Short tenantId,Character activeFlag) {
		List<Object> paramList = new ArrayList<Object>();		
		paramList.add(date);
		paramList.add(tenantId);
		paramList.add(activeFlag);
		return  genericDAO.findEntitiesByNamedQueryMultiCond("FindAllInActiveTSalesPoss",paramList,0,-1);
	}
	
	
	@Override
	public TSalesPos findSalesPositionBySalesPosId(Long salesPosId,Short tenantId) {
		List paramList = new ArrayList();
		paramList.add(salesPosId);
		paramList.add(tenantId);
		List<TSalesPos> list = genericDAO.findEntitiesByNamedQueryMultiCond("findSalesPositionBySalesPosId", paramList, 0, -1);
		if(!list.isEmpty()){
			return (TSalesPos) list.get(0);
		}
		return null;
	}
	
	
	@Override
	public List<TSalesPos> findTSalesPosBySalesHierIdFrGoTo(Long algmntId, Long bussUnitId, Long salesTeamId, Long salesHierId,Short usrTenantId, Long salesPosId) {
		List paramList = new ArrayList();
		paramList.add(salesHierId);
		paramList.add(usrTenantId);
		paramList.add(algmntId);
		paramList.add(bussUnitId);
		paramList.add(salesTeamId);
		paramList.add(salesPosId);
		List<TSalesPos> tSalesPosList =  genericDAO.findEntitiesByNamedQueryMultiCond("findTSalesPosBySalesHierIdFrGoTo", paramList, 0, -1);
		return tSalesPosList;
	}

	@Override
	public Object[] findSPDetails(Long salesPosId, Short tenantId) {
		List paramList = new ArrayList();
		paramList.add(salesPosId);
		paramList.add(tenantId);
		List<Object[]> list = genericDAO.findEntitiesByNamedQueryMultiCond("FindSPDetails", paramList, 0, -1);
		if(!list.isEmpty()){
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<Object[]> findSPParentDetails(List<Object> queryParam) {
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindSPParentDetails", queryParam, 0, -1);		
	}
	
	/**
	 * To find the sales position name 
	 * @param spId
	 * @param  spHierId



	 * @param  tenantId
	 *  @return a String if it exists. Returns null of
	 *         not found
	 */
	@Override
	public String findSpNameBySPId(Long spId,Long spHierId,Short tenantId) {
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(spId);
		paramList.add(spHierId);



		paramList.add(tenantId);
		List<Object[]> list = genericDAO.findEntitiesByNamedQueryMultiCond("FindSpNameBySPId",paramList,0,-1);
		if(list != null && !list.isEmpty()){
			return list.toString() ;
		}
		return null;
	}
	/**
	 * To find the sales position name 
	 * @param spId
	 * @param  spHierId
	 * @param  algmntId
	 * @param  buId
	 * @param  stId
	 * @param  tenantId
	 *  @return a String if it exists. Returns null of
	 *         not found
	 */
	@Override
	public String findSpNameByALBUST(Long spId,Long spHierId,Long algmntId,Long buId,Long stId,Short tenantId) {
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(spId);
		paramList.add(spHierId);
		paramList.add(algmntId);
		paramList.add(buId);
		paramList.add(stId);
		paramList.add(tenantId);
		List<Object[]> list = genericDAO.findEntitiesByNamedQueryMultiCond("FindSpNameByALBUST",paramList,0,-1);
		if(list != null && !list.isEmpty()){
			return list.toString() ;
		}
		return null;
	}

	@Override
	public TSalesPos findTSalesPosBySPId(Long salesPosId, Short tenantId) {
		List paramList = new ArrayList();
		paramList.add(salesPosId);
		paramList.add(tenantId);
		List<TSalesPos> pos = genericDAO.findEntitiesByNamedQueryMultiCond("findTSalesPosBySPId", paramList, 0, -1);
		return (pos!=null&&pos.size()>0)?pos.get(0):null;
	}

	@Override
	public List<Object[]> findTSalesPosEffDates(Long spId, Long spHierId,
			Short tenantId) {
		List paramList = new ArrayList();
		paramList.add(spHierId);
		paramList.add(spId);
		paramList.add(tenantId);
		List<Object[]> list = genericDAO.findEntitiesByNamedQueryMultiCond("FindTSalesPosEffDates", paramList, 0, -1);
		return list;
	}
	
	@Override
	public Long findPrnSalesHierIdByALBUST(Long alginmentId, Long buId, Long stId, Short tenantId){
		Long salesHierId=null;
		List<Object> idList = new ArrayList<Object>();
		idList.add(alginmentId);
		idList.add(buId);
		idList.add(stId);
		idList.add(tenantId);
		List<Long> list = genericDAO.findEntitiesByNamedQuery("findPrnSalesHierIdByALBUST",idList);
		if(CollectionUtils.isNotEmpty(list)){
			salesHierId=list.get(0) ;
		}
		return salesHierId;
	}

	@Override
	public List<Object[]> findFullSPDetails(Long spId,Short tenantId) {
		List paramList = new ArrayList();
		paramList.add(spId);			
		paramList.add(tenantId);
		List<Object[]> list = genericDAO.findEntitiesByNamedQueryMultiCond("FindFullSPDetails", paramList, 0, -1);
		return list;
	}
	
	@Override
	public List<Object[]> getSPNameAndParentSPId(Long spId, Short tenantId) {
		List paramList = new ArrayList();
		paramList.add(spId);				
		paramList.add(tenantId);
		List<Object[]> list = genericDAO.findEntitiesByNamedQueryMultiCond("GetSPNameAndParentSPId", paramList, 0, -1);
		return list;
	}
	
	@Override
	public Long findTSalesPositionByPrnSalesHerIdCount(Long salesHierId,Short tenantId) {
		List queryParam = new ArrayList();
		queryParam.add(salesHierId);
		queryParam.add(tenantId);
		List<Object> count =  genericDAO.findEntitiesByNamedQueryMultiCond("FindTSalesPositionByPrnSalesHerIdCount", queryParam, 0, -1);
		Long countL = (Long) count.get(0);
		return countL;
	}
	
	@Override
	public List<Object[]> findActiveSPForActAlgmnt(Date effEdDt,Short tenantId){
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(effEdDt);
		paramList.add(tenantId);
		
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindActiveSPForActAlgmnt", paramList, 0, -1);
	}

	/**
	 * To find the sales position name, start date and end date 
	 * @param salePosId
	 * @param  saleHierId
	 * @param  tenantId
	 *  @return List<Object[]> 
	 */
	@Override
	public List<Object[]> findSpNameAndDates(Long salePosId, Long saleHierId,Short tenantId) {
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(salePosId);
		paramList.add(saleHierId);
		paramList.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindSpNameAndDates", paramList, 0, -1);
	}
	
	@Override
	public List<TSalesPos> createSalesPosTree(Long salesPosId, Long salesHierId, Short tenantId) {
		List queryParam = new ArrayList();
		queryParam.add(salesPosId);
		queryParam.add(salesHierId);
		queryParam.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("createSPTreeQuery", queryParam, 0, -1);
	}
	
	/* (non-Javadoc)
	 * @see com.cognizant.opserv.sp.core.dao.TSalesPosDAO#getAllSalesPositionByName(java.lang.String, short, long, long, long)
	 */
	@Override
	public List<TSalesPos> getAllSalesPositionByName(String name,
			short tenantId, long alignmentId, long buId, long stId) {
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(name+'%');
		paramList.add(tenantId);
		paramList.add(alignmentId);
		paramList.add(buId);
		paramList.add(stId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("GetAllSPByName", paramList, 0, -1);
	}
	
	/* (non-Javadoc)
	 * @see com.cognizant.opserv.sp.core.dao.TSalesPosDAO#findAllSPoByALBUST(long, long, long, short)
	 */
	@Override
	public List<TSalesPos> findAllSPoByALBUST(long algmntId, long buId, long stId, short tenantId) {
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(algmntId);
		paramList.add(buId);
		paramList.add(stId);
		paramList.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("fetchAllSalesPositionsByAl", paramList, 0, -1);
	}
	
	/* (non-Javadoc)
	 * @see com.cognizant.opserv.sp.core.dao.TSalesPosDAO#getMirroredSalesPositions(long, short)
	 */
	@Override
	public  List<TSalesPos> getMirroredSalesPositions(long salesPosId, short tenantId){
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(salesPosId);
		paramList.add(tenantId);
		
		return genericDAO.findEntitiesByNamedQueryMultiCond("getMirroredSalesPositionsBySP", paramList, 0, -1);
	}
	/**
	 * Gets the sales pos details.
	 *
	 * @param terrZipAlgmnt the terr zip algmnt
	 * @return the sales pos details
	 */
	@Override
	public List<TSalesPos> getSalesPosDetails(TTerrZipAlgmnt terrZipAlgmnt) {
		List queryParam = new ArrayList();
		queryParam.add(terrZipAlgmnt.getPostalCd());
		queryParam.add(terrZipAlgmnt.getTenantId());
		
		return genericDAO.findEntitiesByNamedQueryMultiCond("getSalesPosDetails", queryParam, 0, -1);
		
	}
	
	/**
	 * Check if SP is active.
	 *
	 * @param salesPosId the sales position id
	 * @param tenantId the tenantId
	 * @return the count
	 */
	@Override
	public List<Object> checkIfActiveSP(long salesPosId, short tenantId){
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(salesPosId);
		paramList.add(tenantId);
		
		return genericDAO.findEntitiesByNamedQuery("checkIfActiveSP", paramList);
	}

	@Override
	public List<TSalesPos> findTSalesPosBySearchCriteria(
			ISearchCriteria criteria) {
		return genericDAO.search(clazz, criteria);
	}

	@Override
	public Long countOfBaseSPFrST(Long algmntId, Long bussUnitId,
			Long salesTeamId, Short tenantId) {
		
		List<Object> paramList = new ArrayList<Object>();
		Long mirrSPCount = null;
		paramList.add(algmntId);
		paramList.add(bussUnitId);
		paramList.add(salesTeamId);
		paramList.add(tenantId);
		
		List<Object> countOfMirroredSP = genericDAO.findEntitiesByNamedQuery("countOfBaseSPFrST", paramList);
		mirrSPCount = (Long) countOfMirroredSP.get(0);
		
		return mirrSPCount;
	}

	@Override
	public List<String> fetchSpNamesFrSpIds(List<Long> spIdList, Short tenantId) {
		List<Object> paraList = new ArrayList<Object>();
		paraList.add(spIdList);
		paraList.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("fetchSpNamesFrSpIds", paraList, 0, -1);
	}

	@Override
	public List<Long> getChildSPs(Set<Long> salesPosIds,Short tenantId) {	
		List paramList = new ArrayList();
		paramList.add(salesPosIds);
        paramList.add(tenantId);        
        return genericDAO.findEntitiesByNamedQueryMultiCond("getChildSPs", paramList, 0, -1);		
	}
	
	/**
	 * Find t sales position by id.
	 *
	 * @param spId the sp id
	 * @param userDetails the user details
	 * @return the list
	 */
	@Override
    public List<Object[]> findTSalesPositionById(long spId, Short tenantId) {
           List paramList = new ArrayList();
           paramList.add(spId);
           paramList.add(tenantId);
           List<Object[]> spListInfo = genericDAO.findEntitiesByNamedQueryMultiCond("findTSalesPositionById", paramList, 0, -1);
           return spListInfo;
    }

    /**
	 * To get salespostion name and code for a person
	 * @param staffId
	 * @param tenantId
	 * @return List<Object[]>
	 */
	@Override
	public List<Object[]> findSPNameAndCodeForEmp(Integer staffId, Short tenantId) {
		 List<Object> paramList = new ArrayList<Object>();
         paramList.add(staffId);
         paramList.add(tenantId);
		return genericDAO.findEntitiesByNamedQuery("findSPNameAndCodeForEmp", paramList);
	}

}
