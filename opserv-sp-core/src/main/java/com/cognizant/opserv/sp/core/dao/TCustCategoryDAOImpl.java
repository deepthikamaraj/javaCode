package com.cognizant.opserv.sp.core.dao;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TCustCategory;
import com.cognizant.opserv.sp.core.entity.TCustCategoryId;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TCustCategoryDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tCustCategoryDAO")
public class TCustCategoryDAOImpl implements TCustCategoryDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TCustCategoryDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private final Class<TCustCategory> clazz;

	public Class<TCustCategory> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TCustCategoryDAOImpl() {
		super();
		this.clazz = TCustCategory.class;
	}

	private static final String JPAQL = "select tCustCategoryentity from TCustCategory tCustCategoryentity";

	private static final String JPACOUNTQL = "select count(tCustCategoryentity) from TCustCategory tCustCategoryentity";

	private static final String[] RESTRICTIONS = {
			"tCustCategoryentity.tCustCategoryId.custCategoryId = '#{tCustCategoryentity.tCustCategoryId.getCustCategoryId}'",
			"tCustCategoryentity.categoryName like '#{tCustCategoryentity.getCategoryName}%'",
			"tCustCategoryentity.categoryDesc like '#{tCustCategoryentity.getCategoryDesc}%'",
			"tCustCategoryentity.activeFlag = #{tCustCategoryentity.getActiveFlag}",
			"tCustCategoryentity.createdBy = #{tCustCategoryentity.getCreatedBy}",
			"Date(tCustCategoryentity.createDt) = '#{tCustCategoryentity.getCreateDt}'",
			"tCustCategoryentity.updatedBy = #{tCustCategoryentity.getUpdatedBy}",
			"Date(tCustCategoryentity.updateDt) = '#{tCustCategoryentity.getUpdateDt}'",
			"tCustCategoryentity.tCustCategoryId.localeId = '#{tCustCategoryentity.tCustCategoryId.getLocaleId}'",
			"tCustCategoryentity.tenantId = #{tCustCategoryentity.getTenantId}" };

	/**
	 * Stores a new TCustCategory entity object in to the persistent store
	 * 
	 * @param tCustCategory
	 *            TCustCategory Entity object to be persisted
	 * @return tCustCategory Persisted TCustCategory object
	 */
	public TCustCategory createTCustCategory(final TCustCategory tCustCategory) {
		LOGGER.info("=========== Create TCustCategory ===========");
		return genericDAO.store(tCustCategory);
	}

	/**
	 * Deletes a TCustCategory entity object from the persistent store
	 * 
	 * @param tCustCategory
	 *            TCustCategory Entity object to be deleted
	 */
	public void deleteTCustCategory(final Integer categoryId) {
		LOGGER.info("=========== Delete TCustCategory ===========");
		final TCustCategory tCustCategory = genericDAO.get(clazz, categoryId);
		genericDAO.remove(tCustCategory);
	}

	/**
	 * Updates a TCustCategory entity object in to the persistent store
	 * 
	 * @param tCustCategory
	 *            TCustCategory Entity object to be updated
	 * @return tCustCategory Persisted TCustCategory object
	 */
	public TCustCategory updateTCustCategory(final TCustCategory tCustCategory) {
		LOGGER.info("=========== Update TCustCategory ===========");
		return genericDAO.update(tCustCategory);
	}

	/**
	 * Retrieve an TCustCategory object based on given TCustCategory categoryId.
	 * 
	 * @param tCustCategoryId
	 *            the primary key value of the TCustCategory Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TCustCategory findTCustCategoryById(final Integer tCustCategoryId) {
		LOGGER.info("find TCustCategory instance with categoryId: " + tCustCategoryId);
		return genericDAO.get(clazz, tCustCategoryId);
	}



     	public TCustCategory findTCustCategoryById(final TCustCategoryId tCustCategoryId) {
		LOGGER.info("find TCustCategory instance with categoryId: " + tCustCategoryId);
		
		return genericDAO.get(clazz, tCustCategoryId);
	}
	/**
	 * Retrieve TCustCategory based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCustCategory> list of TCustCategory if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TCustCategory> findTCustCategorys(final SearchFilter<TCustCategory> searchFilter) {
		LOGGER.info("=========== Find TCustCategorys ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TCustCategory tCustCategory = searchFilter.getEntityClass();
		
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tCustCategoryentity", tCustCategory);
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		jpaQuery.setCacheable(true);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TCustCategorys.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTCustCategorys(final SearchFilter<TCustCategory> searchFilter) {
		LOGGER.info("=========== Count TCustCategory ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TCustCategory tCustCategory = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tCustCategoryentity", tCustCategory);

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}
	/**
	 * Find all categorys.
	 *
	 * @param tenantId the tenant id
	 * @param localeId the locale id
	 * @return the list
	 */
	@Override
	public List<TCustCategory> findAllCategorys(Short tenantId, String localeId) {
		 List<Object> paramList = new ArrayList<Object>();
		 paramList.add(tenantId);
		 paramList.add(localeId);
		 List<TCustCategory> list= genericDAO.findEntitiesByNamedQueryMultiCond("FindTCustCategorys", paramList, 0, -1);		
		 return list;
	}
	/**
	 * Find all categories.
	 *
	 * @param tenantId the tenant id
	 * @param localeId the locale id
	 * @return the list
	 */
	@Override
	public List<Object[]> findAllCategories(Short tenantId, String localeId) {
		 List<Object> paramList = new ArrayList<Object>();
		 paramList.add(tenantId);
		 paramList.add(localeId);
		 return genericDAO.findEntitiesByNamedQueryMultiCond("FindAllTCustCategories", paramList, 0, -1);		
	}
	/**
	 * Find all t cust cat name and id.
	 *
	 * @param tenantId the tenant id
	 * @param localeId the locale id
	 * @return the map
	 */
	@Override
	public Map<Integer,String> findAllTCustCatNameAndId(Short tenantId, String localeId) {

		List<Object> paramList = new ArrayList<Object>();
		paramList.add(tenantId);
		paramList.add(localeId);
		List<Object[]> list = genericDAO.findEntitiesByNamedQueryMultiCond("FindAllTCustCatNameAndId", paramList, 0, -1);
		Map<Integer,String> res = new HashMap<Integer,String>();
		for(Object[] obj : list) {
			res.put(Integer.parseInt(obj[0].toString()), obj[2].toString());
		}
		return res ;
	}
	
}
