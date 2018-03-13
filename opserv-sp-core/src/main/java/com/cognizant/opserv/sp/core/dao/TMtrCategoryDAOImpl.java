package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TMtrCategory;
import com.cognizant.opserv.sp.core.entity.TMtrCategoryId;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TMtrCategoryDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tMtrCategoryDAO")
public class TMtrCategoryDAOImpl implements TMtrCategoryDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TMtrCategoryDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private final Class<TMtrCategory> clazz;

	public Class<TMtrCategory> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TMtrCategoryDAOImpl() {
		super();
		this.clazz = TMtrCategory.class;
	}

	private static final String JPAQL = "select tMtrCategoryentity from TMtrCategory tMtrCategoryentity";

	private static final String JPACOUNTQL = "select count(tMtrCategoryentity) from TMtrCategory tMtrCategoryentity";

	private static final String[] RESTRICTIONS = {
			"tMtrCategoryentity.tMtrCategoryId.mtrCategoryId = #{tMtrCategoryentity.tMtrCategoryId.getMtrCategoryId}",
			"tMtrCategoryentity.tMtrCategoryId.localeId = #{tMtrCategoryentity.tMtrCategoryId.getLocaleId}",
			"tMtrCategoryentity.mtrCategoryDesc like '#{tMtrCategoryentity.getMtrCategoryDesc}%'",
			"tMtrCategoryentity.mtrCategoryCd like '#{tMtrCategoryentity.getMtrCategoryCd}%'",
			"tMtrCategoryentity.activeFlag = #{tMtrCategoryentity.getActiveFlag}",
			"tMtrCategoryentity.createdBy = #{tMtrCategoryentity.getCreatedBy}",
			"Date(tMtrCategoryentity.createDt) = '#{tMtrCategoryentity.getCreateDt}'",
			"tMtrCategoryentity.updatedBy = #{tMtrCategoryentity.getUpdatedBy}",
			"Date(tMtrCategoryentity.updateDt) = '#{tMtrCategoryentity.getUpdateDt}'",
			"tMtrCategoryentity.tenantId = #{tMtrCategoryentity.getTenantId}" };

	/**
	 * Stores a new TMtrCategory entity object in to the persistent store
	 * 
	 * @param tMtrCategory
	 *            TMtrCategory Entity object to be persisted
	 * @return tMtrCategory Persisted TMtrCategory object
	 */
	public TMtrCategory createTMtrCategory(final TMtrCategory tMtrCategory) {
		LOGGER.info("=========== Create TMtrCategory ===========");
		return genericDAO.store(tMtrCategory);
	}

	/**
	 * Deletes a TMtrCategory entity object from the persistent store
	 * 
	 * @param tMtrCategory
	 *            TMtrCategory Entity object to be deleted
	 */
	public void deleteTMtrCategory(final Integer mtrCategoryId) {
		LOGGER.info("=========== Delete TMtrCategory ===========");
		final TMtrCategory tMtrCategory = genericDAO.get(clazz, mtrCategoryId);
		genericDAO.remove(tMtrCategory);
	}

	/**
	 * Updates a TMtrCategory entity object in to the persistent store
	 * 
	 * @param tMtrCategory
	 *            TMtrCategory Entity object to be updated
	 * @return tMtrCategory Persisted TMtrCategory object
	 */
	public TMtrCategory updateTMtrCategory(final TMtrCategory tMtrCategory) {
		LOGGER.info("=========== Update TMtrCategory ===========");
		return genericDAO.update(tMtrCategory);
	}

	/**
	 * Retrieve an TMtrCategory object based on given TMtrCategory mtrCategoryId.
	 * 
	 * @param tMtrCategoryId
	 *            the primary key value of the TMtrCategory Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TMtrCategory findTMtrCategoryById(final TMtrCategoryId tMtrCategoryId) {
		LOGGER.info("find TMtrCategory instance with mtrCategoryId: " + tMtrCategoryId);
		List<Object> paramList = new ArrayList<Object>();
		 paramList.add(tMtrCategoryId.getMtrCategoryId());
		 paramList.add(tMtrCategoryId.getLocaleId());
		List<TMtrCategory> list = genericDAO.findEntitiesByNamedQueryMultiCond("FindAllTMtrCategorysByIds", paramList, 0, -1);
		if(list != null && !list.isEmpty()){
			return list.get(0);
		}
		//return genericDAO.get(clazz, tMtrCategoryId);
		else{
			return null;
		}
	}

	/**
	 * Retrieve TMtrCategory based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TMtrCategory> list of TMtrCategory if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TMtrCategory> findTMtrCategorys(final SearchFilter<TMtrCategory> searchFilter) {
		LOGGER.info("=========== Find TMtrCategorys ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TMtrCategory tMtrCategory = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tMtrCategoryentity", tMtrCategory);
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TMtrCategorys.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTMtrCategorys(final SearchFilter<TMtrCategory> searchFilter) {
		LOGGER.info("=========== Count TMtrCategory ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TMtrCategory tMtrCategory = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tMtrCategoryentity", tMtrCategory);

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	@Override
	public List<TMtrCategory> findActiveTMtrCategorys(Short tenantId){		
		List<Object> paramList = new ArrayList<Object>();		
		paramList.add(tenantId);
		return  genericDAO.findEntitiesByNamedQueryMultiCond("FindActiveTMtrCategorys",paramList,0,-1);
	}

	@Override
	public List<Object[]> findTMtrCategoryList(
			SearchFilter<TMtrCategory> searchFilter, String localeId) {
		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TMtrCategory tMtrCategory = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		List<Object> paramList = new ArrayList<Object>();
		paramList.add(tMtrCategory.getTenantId());
		paramList.add(localeId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTMtrCategory",paramList, index, maxresult);
	}
	
	@Override
	public List<TMtrCategory> getActiveTMtrCategorys(Short tenantId,String localeId){		
		List<Object> paramList = new ArrayList<Object>();		
		paramList.add(tenantId);
		paramList.add(localeId);
		return  genericDAO.findEntitiesByNamedQueryMultiCond("GetTMtrCategorysByLocale",paramList,0,-1);
	}
	
	
	@Override
	public List<TMtrCategory> findAllCategories(Character activeFlag, String localeId, short tenantId) {
		List<Object> queryParam = new ArrayList<Object>();
		queryParam.add(activeFlag);
		queryParam.add(localeId);
		queryParam.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindActiveTMtrCategoryByLocaleId", queryParam, 0, -1);
	}
	
	
}
