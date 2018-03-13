package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TAlgmntSalesTeam;
import com.cognizant.opserv.sp.core.entity.TMtr;
import com.cognizant.opserv.sp.core.entity.TMtrCategory;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TMtrDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tMtrDAO")
public class TMtrDAOImpl implements TMtrDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TMtrDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private static final String TMTRCATEGORY = "tMtrCategory";
	private static final String TALGMNTSALESTEAM = "tAlgmntSalesTeam";

	private final Class<TMtr> clazz;

	public Class<TMtr> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TMtrDAOImpl() {
		super();
		this.clazz = TMtr.class;
	}

	private static final String JPAQL = "select tMtrentity from TMtr tMtrentity";

	private static final String JPACOUNTQL = "select count(tMtrentity) from TMtr tMtrentity";

	private static final String[] RESTRICTIONS = { "tMtrentity.mtrId = #{tMtrentity.getMtrId}",
			"tMtrentity.enforceFlag = #{tMtrentity.getEnforceFlag}",
			"tMtrentity.activeFlag = #{tMtrentity.getActiveFlag}",
			"Date(tMtrentity.effStartDt) = '#{tMtrentity.getEffStartDt}'",
			"Date(tMtrentity.effEndDt) = '#{tMtrentity.getEffEndDt}'",
			"tMtrentity.minValue = #{tMtrentity.getMinValue}", "tMtrentity.maxValue = #{tMtrentity.getMaxValue}",
			"tMtrentity.mtrName like '#{tMtrentity.getMtrName}%'",
			"tMtrentity.dataDotFlag = #{tMtrentity.getDataDotFlag}", "tMtrentity.prec = #{tMtrentity.getPrec}",
			"tMtrentity.visibleFlag = #{tMtrentity.getVisibleFlag}",
			"tMtrentity.createdBy = #{tMtrentity.getCreatedBy}",
			"Date(tMtrentity.createDt) = '#{tMtrentity.getCreateDt}'",
			"tMtrentity.updatedBy = #{tMtrentity.getUpdatedBy}",
			"Date(tMtrentity.updateDt) = '#{tMtrentity.getUpdateDt}'",
			"tMtrentity.tenantId = #{tMtrentity.getTenantId}",
			"tMtrentity.mtrCategoryId = #{tMtrentity.getMtrCategoryId}",
			"tMtrentity.compMtrFlag = #{tMtrentity.getCompMtrFlag}",
			"tMtrentity.uomId = #{tMtrentity.getUomId}",
			"tMtrentity.mtrFype = #{tMtrentity.getMtrType}",
			"tMtrentity.tAlgmntSalesTeam.tAlgmntSalesTeamId = #{tMtrentity.tAlgmntSalesTeam.getTAlgmntSalesTeamId}" };

	/**
	 * Stores a new TMtr entity object in to the persistent store
	 * 
	 * @param tMtr
	 *            TMtr Entity object to be persisted
	 * @return tMtr Persisted TMtr object
	 */
	public TMtr createTMtr(final TMtr tMtr) {
		LOGGER.info("=========== Create TMtr ===========");
		return genericDAO.store(tMtr);
	}

	/**
	 * Deletes a TMtr entity object from the persistent store
	 * 
	 * @param tMtr
	 *            TMtr Entity object to be deleted
	 */
	public void deleteTMtr(final Integer mtrId) {
		LOGGER.info("=========== Delete TMtr ===========");
		final TMtr tMtr = genericDAO.get(clazz, mtrId);
		genericDAO.remove(tMtr);
	}

	/**
	 * Updates a TMtr entity object in to the persistent store
	 * 
	 * @param tMtr
	 *            TMtr Entity object to be updated
	 * @return tMtr Persisted TMtr object
	 */
	public TMtr updateTMtr(final TMtr tMtr) {
		LOGGER.info("=========== Update TMtr ===========");
		return genericDAO.update(tMtr);
	}

	/**
	 * Retrieve an TMtr object based on given TMtr mtrId.
	 * 
	 * @param tMtrId
	 *            the primary key value of the TMtr Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TMtr findTMtrById(final Integer tMtrId) {
		LOGGER.info("find TMtr instance with mtrId: " + tMtrId);
		return genericDAO.get(clazz, tMtrId);
	}

	/**
	 * Retrieve TMtr based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TMtr> list of TMtr if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TMtr> findTMtrs(final SearchFilter<TMtr> searchFilter) {
		LOGGER.info("=========== Find TMtrs ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TMtr tMtr = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tMtrentity", tMtr);

		if (tMtr.getMtrCategoryId() == null) {
			jpaQuery.bind(TMTRCATEGORY, new TMtrCategory());
		} else {
			LOGGER.info("tMtrCategory {}"+ tMtr.getMtrCategoryId());

			jpaQuery.bind(TMTRCATEGORY, tMtr.getMtrCategoryId());
		}

		if (tMtr.getTAlgmntSalesTeam() == null) {
			jpaQuery.bind(TALGMNTSALESTEAM, new TAlgmntSalesTeam());
		} else {
			LOGGER.info("tAlgmntSalesTeam {}"+ tMtr.getTAlgmntSalesTeam());

			jpaQuery.bind(TALGMNTSALESTEAM, tMtr.getTAlgmntSalesTeam());
		}
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TMtrs.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTMtrs(final SearchFilter<TMtr> searchFilter) {
		LOGGER.info("=========== Count TMtr ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TMtr tMtr = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tMtrentity", tMtr);

		if (tMtr.getMtrCategoryId() == null) {
			jpaCountQuery.bind(TMTRCATEGORY, new TMtrCategory());
		} else {
			LOGGER.info("tMtrCategory {}"+ tMtr.getMtrCategoryId());

			jpaCountQuery.bind(TMTRCATEGORY, tMtr.getMtrCategoryId());
		}

		if (tMtr.getTAlgmntSalesTeam() == null) {
			jpaCountQuery.bind(TALGMNTSALESTEAM, new TAlgmntSalesTeam());
		} else {
			LOGGER.info("tAlgmntSalesTeam {}"+ tMtr.getTAlgmntSalesTeam());

			jpaCountQuery.bind(TALGMNTSALESTEAM, tMtr.getTAlgmntSalesTeam());
		}

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TMtr based on given search criteria using JPA named Query.
	 * The search criteria is of TMtrCategory type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TMtr> list of TMtrs if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TMtr> getTMtrsByTMtrCategory(final SearchFilter<TMtrCategory> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TMtrCategory tMtrCategory = searchFilter.getEntityClass();
		List<Object> tMtrCategoryList = new ArrayList<Object>();
		tMtrCategoryList.add(tMtrCategory);
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTMtrByTMtrCategory", tMtrCategoryList, index, maxresult);
	}

	/**
	 * Count TMtr based on given search criteria using JPA named Query.
	 * The search criteria is of TMtrCategory type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTMtrsByTMtrCategory(final SearchFilter<TMtrCategory> searchFilter) {

		//final TMtrCategory tMtrCategory = searchFilter.getEntityClass();
		final Integer tMtrCategoryId = searchFilter.getEntityClass().gettMtrCategoryId().getMtrCategoryId();
		List<Object> tMtrCategoryIdList = new ArrayList<Object>();
		tMtrCategoryIdList.add(tMtrCategoryId);
		return genericDAO.findEntitiesByNamedQuery("CountTMtrsByTMtrCategory", tMtrCategoryIdList);
	}

	/**
	 * Retrieve TMtr based on given search criteria using JPA named Query.
	 * The search criteria is of TAlgmntSalesTeam type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TMtr> list of TMtrs if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TMtr> getTMtrsByTAlgmntSalesTeam(final SearchFilter<TAlgmntSalesTeam> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TAlgmntSalesTeam tAlgmntSalesTeam = searchFilter.getEntityClass();
		List<Object> tAlgmntSalesTeamList = new ArrayList<Object>();
		tAlgmntSalesTeamList.add(tAlgmntSalesTeam);
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTMtrByTAlgmntSalesTeam", tAlgmntSalesTeamList, index, maxresult);
	}

	/**
	 * Count TMtr based on given search criteria using JPA named Query.
	 * The search criteria is of TAlgmntSalesTeam type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTMtrsByTAlgmntSalesTeam(final SearchFilter<TAlgmntSalesTeam> searchFilter) {

		final TAlgmntSalesTeam tAlgmntSalesTeam = searchFilter.getEntityClass();
		List<Object> tAlgmntSalesTeamList = new ArrayList<Object>();
		tAlgmntSalesTeamList.add(tAlgmntSalesTeam);
		return genericDAO.findEntitiesByNamedQuery("CountTMtrsByTAlgmntSalesTeam", tAlgmntSalesTeamList);
	}

	
	public List<TMtr> findMetricsForHeat(String alignmentId,String salesTeamId,String bussUnitId,Character dataDotFlag,String hierarchyLevel)
	{
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(Long.valueOf(bussUnitId));
		paramList.add(Long.valueOf(salesTeamId));
		paramList.add(Long.valueOf(alignmentId));
		paramList.add(Character.valueOf(dataDotFlag));
		paramList.add(String.valueOf(hierarchyLevel));
		
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindHeatMetricsForSalesAlignMent", paramList, 0, -1);
	}
	
	/**
	 * 
	 * @param metricName
	 * @param salesPosId
	 * @return
	 */
	public List<Object[]> findMetricsHeat(String metricName, Long salesPosId,
			Short tenantId) {
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(metricName);
		paramList.add(Long.valueOf(salesPosId));
		paramList.add(Character.valueOf('Y'));

		paramList.add(tenantId);

		return genericDAO.findEntitiesByNamedQueryMultiCond(
				"FindMetricsForHeat", paramList, 0, -1);

	}
	
	/**
	 * 
	 * @param salesPosId
	 * @return
	 */
	public List<Object[]> findOnlyMetricsForHeat(Long salesPosId, Short tenantId)
	{
		List<Object> paramList = new ArrayList<Object>();	
		paramList.add(Long.valueOf(salesPosId));
		paramList.add(Character.valueOf('Y'));
		paramList.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindOnlyMetricsForHeat", paramList, 0, -1);
	}
	
	
	
	public List<Object[]> findMetricsWorkLoad(Long salesPosId,Short tenantId)
	{
		List<Object> paramList = new ArrayList<Object>();	
		 
		paramList.add(Long.valueOf(salesPosId));
		
		paramList.add(tenantId);
		
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindMetricsForWorkLoad", paramList, 0, -1);
		
	}
	
	
	
	public List<TMtr> findMetricsForWorkLoad(String alignmentId,String salesTeamId,String bussUnitId,String hierarchyLevel)
	{
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(Long.valueOf(bussUnitId));
		paramList.add(Long.valueOf(salesTeamId));
		paramList.add(Long.valueOf(alignmentId));		
		paramList.add(String.valueOf(hierarchyLevel));		
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindWorkLoadMetricsForSalesAlignMent", paramList, 0, -1);
	}

	@Override
	public List<TMtr> findTMtrByAlgmntID(Long alignmentId,Short tenantId) {
		List<Object> list = new ArrayList<Object>();
		list.add(alignmentId);
		list.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindEntitiesByNamedQuery", list, 0, -1);
	}
	
	/**
	 * 
	 * @param salesPosId
	 * @param zip
	 * @return
	 */
	public List<Object[]> findMetricsandCountFromSalesAandZip(Long salesPosId, String zip,Short tenantId)
	{
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(Long.valueOf(salesPosId));
		paramList.add(zip);
		paramList.add(tenantId);
		
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindMetricsandCountFromSalesAandZip", paramList, 0, -1);
	}
	
	public List<Object[]> findMetricsandCountFromSalesPosHierAndZip(Long salesPosId, String zip,Integer metricId, Short tenantId)
	{
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(Long.valueOf(salesPosId));
		paramList.add(zip);
		paramList.add(metricId);
		paramList.add(tenantId);
		
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindMetricsandCountFromSalesPosHierAndZip", paramList, 0, -1);
	}
	
	public List<Object[]> findValueZipFromSalesAandZipandMetricName(Long salesPosId, String zip,String metricName,Short tenantId)
	{
		
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(Long.valueOf(salesPosId));
		paramList.add(zip);
		paramList.add(metricName);		
		paramList.add(tenantId);
		
		Character.valueOf('Y');
		
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindValueFromSalesAandZipandName", paramList, 0, 1);
	}

@Override
	public List<Object> getTMtrForBatch(){
		return genericDAO.findEntitiesByNamedQuery("getMtrIdForBatch");
	}

@Override
public List<TMtr> FindTMtrByIDs(Long algmntId, Long bussUnitId, Long salesTeamId,Long salesHierId, short tenantId) {
	
	List<Object> paramList = new ArrayList<Object>();
    
	
    paramList.add(algmntId);
    paramList.add(bussUnitId);
    paramList.add(salesTeamId);
    paramList.add(salesHierId);
    paramList.add(tenantId);
    List<TMtr> tMtrList = genericDAO.findEntitiesByNamedQueryMultiCond("FindTMtrByIDs", paramList,0,-1);
    
   return tMtrList;
}

public List<TMtr> findMetricsForCatAlBUSt(Long alignmentId,Long salesTeamId,Long bussUnitId,Integer catId,Short tenantId,Long salesHierId)
{
	List<Object> paramList = new ArrayList<Object>();
	paramList.add(bussUnitId);
	paramList.add(salesTeamId);
	paramList.add(alignmentId);
	paramList.add(catId);
	paramList.add(tenantId);
	paramList.add(salesHierId);
	
	return genericDAO.findEntitiesByNamedQueryMultiCond("FindMetricsForCatAlBUSt", paramList, 0, -1);
}

@Override
public List<TMtr> findMetricsForAlBUSt(Long alignmentId,Long salesTeamId,Long bussUnitId, Short tenantId, int index, int noOfResults)
{
	List<Object> paramList = new ArrayList<Object>();
	paramList.add(bussUnitId);
	paramList.add(salesTeamId);
	paramList.add(alignmentId);
	paramList.add(tenantId);
	
	return genericDAO.findEntitiesByNamedQueryMultiCond("FindMetricsForAlBUSt", paramList, index, noOfResults);
}

@Override
public List<TMtr> findMetricsForAlBUSt(Long alignmentId,Long salesTeamId,Long bussUnitId,Short tenantId,Long salesHierId)
{
	List<Object> paramList = new ArrayList<Object>();
	paramList.add(bussUnitId);
	paramList.add(salesTeamId);
	paramList.add(alignmentId);
	paramList.add(tenantId);
	paramList.add(salesHierId);
	
	return genericDAO.findEntitiesByNamedQueryMultiCond("FindMetricsForAlBUSt", paramList, 0, -1);
}


@Override
public List<TMtr> FindTMtrByMtrVal(int mtrId) {
		List<Object> mtrIdList = new ArrayList<Object>();
		mtrIdList.add(mtrId);
		return genericDAO.findEntitiesByNamedQuery("FindTMtrByMtrVal", mtrIdList);
	  
	}

@Override
public List<TMtr> FindTMtrByABSIds(Long algmntId, Long bussUnitId,
		Long salesTeamId) {
	
	//final int index = paginInfo.getStartIndex();
	//final int maxresult = paginInfo.getMaxRows();
	
	List<Object> paramList = new ArrayList<Object>();
	paramList.add(algmntId);
	paramList.add(bussUnitId);
	paramList.add(salesTeamId);
	//paramList.add(userDetails.getTenantId());
	return genericDAO.findEntitiesByNamedQueryMultiCond("FindTMtrByABSIDs", paramList, 0, -1);
	
}

@Override
public List<TMtr> findTMtrByCtgID(int mtrCtgId, Long salesHierId, Short tenantId) {

	List<Object> paramList = new ArrayList<Object>();
	paramList.add(mtrCtgId);
	paramList.add(salesHierId);
	paramList.add(tenantId);

	return genericDAO.findEntitiesByNamedQueryMultiCond("FindTMtrByCtgID", paramList, 0, -1);
}

@Override
public List<TMtr> findTMtrByCtgID(int mtrCtgId,  short tenantId) {

	List<Object> paramList = new ArrayList<Object>();
	paramList.add(mtrCtgId);
	paramList.add(tenantId);

	return genericDAO.findEntitiesByNamedQueryMultiCond("FindTMtrByTenantCtgId", paramList, 0, -1);	
}

@Override
public List<TMtr> getTMtrByCtgIDALBUST(int mtrCtgId,  short tenantId,Long alId,Long buId,Long stId) {
	List<Object> paramList = new ArrayList<Object>();
	paramList.add(mtrCtgId);
	paramList.add(tenantId);	
	paramList.add(buId);
	paramList.add(stId);
	paramList.add(alId);
	return genericDAO.findEntitiesByNamedQueryMultiCond("GetTMtrByCtgIDALBUST", paramList, 0, -1);	
}

@Override
public List<Object[]> getTMtrCatByALBUSTSH(String localeId,short tenantId,Long alId,Long buId,Long stId,Long shId) {
	List<Object> paramList = new ArrayList<Object>();	
	paramList.add(alId);	
	paramList.add(buId);
	paramList.add(stId);	
	paramList.add(localeId);
	paramList.add(tenantId);
	paramList.add(shId);
	return genericDAO.findEntitiesByNamedQueryMultiCond("GetTMtrCatByALBUSTSH", paramList, 0, -1);	
}

@Override
public List<TMtr> findAllTMtrsFrGIS(Long algmntId, Long bussUnitId, Long salesTeamId,Long salesHierId, short tenantId) {
	

	List<Object> paramList = new ArrayList<Object>();
    
	
    paramList.add(algmntId);
    paramList.add(bussUnitId);
    paramList.add(salesTeamId);
    paramList.add(salesHierId);
    paramList.add(tenantId);
    List<TMtr> tMtrList = genericDAO.findEntitiesByNamedQueryMultiCond("findAllTMtrsFrGIS", paramList,0,-1);
    
   return tMtrList;
}

public List<Object[]> findMetricsandCountFromSalesPosHierAndZipFrMtrList(Long salesPosId, String zip, List<Integer> metricIdList, Short tenantId)
{
	List<Object> paramList = new ArrayList<Object>();
	paramList.add(Long.valueOf(salesPosId));
	paramList.add(zip);
	paramList.add(metricIdList);
	paramList.add(tenantId);
	
	return genericDAO.findEntitiesByNamedQueryMultiCond("FindMetricsandCountFromSalesPosHierAndZipFrMtrList", paramList, 0, -1);
}

@Override
public List<Object[]> getMetricInfoByALBUSTCate(Long alId,Long buId,Long stId,Long shId,Integer mtrCategoryId, Short tenantId){
	List<Object> paramList = new ArrayList<Object>();
	paramList.add(alId);
	paramList.add(buId);
	paramList.add(stId);
	paramList.add(shId);
	paramList.add(mtrCategoryId);
	paramList.add(tenantId);	
	return genericDAO.findEntitiesByNamedQueryMultiCond("GetMetricInfoByALBUSTCate", paramList, 0, -1);
}

@Override
public List<Object[]> getMetricInfoByALBUST(Long alId,Long buId,Long stId,Long shId,Short tenantId){
	List<Object> paramList = new ArrayList<Object>();
	paramList.add(alId);
	paramList.add(buId);
	paramList.add(stId);
	paramList.add(shId);
	paramList.add(tenantId);	
	return genericDAO.findEntitiesByNamedQueryMultiCond("GetMetricInfoByALBUST", paramList, 0, -1);
}

@Override
public List<Object[]> getMetricInfoByALBUSTExprType(Long alId,Long buId,Long stId,Long shId,List<Character> exprTypes,Integer triggerId,Short tenantId){
	List<Object> paramList = new ArrayList<Object>();
	paramList.add(alId);
	paramList.add(buId);
	paramList.add(stId);
	paramList.add(shId);
	paramList.add(exprTypes);
	paramList.add(triggerId);
	paramList.add(tenantId);	
	return genericDAO.findEntitiesByNamedQueryMultiCond("GetMetricInfoByALBUSTExprType", paramList, 0, -1);
}

@Override
public List<Object[]> findTMtrByMtrId(Integer mtrId,Short tenantId){
	List<Object> paramList = new ArrayList<Object>();
	paramList.add(mtrId);
	paramList.add(tenantId);	
	return genericDAO.findEntitiesByNamedQueryMultiCond("FindTMtrByMtrId", paramList, 0, -1);
}

@Override
public List<Object[]> findTMtrByMtrIds(List<Integer> mtrIds, Short tenantId) {
	List<Object> paramList = new ArrayList<Object>();
	paramList.add(mtrIds);
	paramList.add(tenantId);	
	return genericDAO.findEntitiesByNamedQueryMultiCond("FindTMtrByMtrIds", paramList, 0, -1);
}

@Override
public List<Object[]> getMetricInfoByAL(Long alId,Short tenantId){
	List<Object> paramList = new ArrayList<Object>();
	paramList.add(alId);
	paramList.add(tenantId);	
	return genericDAO.findEntitiesByNamedQueryMultiCond("GetMetricInfoByAL", paramList, 0, -1);
}


}
