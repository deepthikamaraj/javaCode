package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TRptCategory;
import com.cognizant.opserv.sp.core.entity.TRptCategoryId;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TRptCategoryDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tRptCategoryDAO")
public class TRptCategoryDAOImpl implements TRptCategoryDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TRptCategoryDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private final Class<TRptCategory> clazz;

	public Class<TRptCategory> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TRptCategoryDAOImpl() {
		super();
		this.clazz = TRptCategory.class;
	}

	private static final String JPAQL = "select tRptCategoryentity from TRptCategory tRptCategoryentity";

	private static final String JPACOUNTQL = "select count(tRptCategoryentity) from TRptCategory tRptCategoryentity";

	private static final String[] RESTRICTIONS = {
			"tRptCategoryentity.tRptCategoryId.rptCategoryId = #{tRptCategoryentity.tRptCategoryId.getRptCategoryId}",
			"tRptCategoryentity.tRptCategoryId.localeId = #{tRptCategoryentity.tRptCategoryId.getLocaleId}",
			"tRptCategoryentity.rptCategoryName like '#{tRptCategoryentity.getRptCategoryName}%'",
			"tRptCategoryentity.rptCategoryDesc like '#{tRptCategoryentity.getRptCategoryDesc}%'",
			"tRptCategoryentity.activeFlag = #{tRptCategoryentity.getActiveFlag}",
			"tRptCategoryentity.createdBy = #{tRptCategoryentity.getCreatedBy}",
			"Date(tRptCategoryentity.createDt) = '#{tRptCategoryentity.getCreateDt}'",
			"tRptCategoryentity.updatedBy = #{tRptCategoryentity.getUpdatedBy}",
			"Date(tRptCategoryentity.updateDate) = '#{tRptCategoryentity.getUpdateDate}'",
			"tRptCategoryentity.tenantId = #{tRptCategoryentity.getTenantId}" };

	/**
	 * Stores a new TRptCategory entity object in to the persistent store
	 * 
	 * @param tRptCategory
	 *            TRptCategory Entity object to be persisted
	 * @return tRptCategory Persisted TRptCategory object
	 */
	public TRptCategory createTRptCategory(final TRptCategory tRptCategory) {
		LOGGER.info("=========== Create TRptCategory ===========");
		return genericDAO.store(tRptCategory);
	}

	/**
	 * Deletes a TRptCategory entity object from the persistent store
	 * 
	 * @param tRptCategory
	 *            TRptCategory Entity object to be deleted
	 */
	public void deleteTRptCategory(final Integer rptCategoryId) {
		LOGGER.info("=========== Delete TRptCategory ===========");
		final TRptCategory tRptCategory = genericDAO.get(clazz, rptCategoryId);
		genericDAO.remove(tRptCategory);
	}

	/**
	 * Updates a TRptCategory entity object in to the persistent store
	 * 
	 * @param tRptCategory
	 *            TRptCategory Entity object to be updated
	 * @return tRptCategory Persisted TRptCategory object
	 */
	public TRptCategory updateTRptCategory(final TRptCategory tRptCategory) {
		LOGGER.info("=========== Update TRptCategory ===========");
		return genericDAO.update(tRptCategory);
	}

	/**
	 * Retrieve an TRptCategory object based on given TRptCategory rptCategoryId.
	 * 
	 * @param tRptCategoryId
	 *            the primary key value of the TRptCategory Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TRptCategory findTRptCategoryById(final TRptCategoryId tRptCategoryId) {
		LOGGER.info("find TRptCategory instance with rptCategoryId: " + tRptCategoryId);
		return genericDAO.get(clazz, tRptCategoryId);
	}

	/**
	 * Retrieve TRptCategory based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRptCategory> list of TRptCategory if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TRptCategory> findTRptCategorys(final SearchFilter<TRptCategory> searchFilter) {
		LOGGER.info("=========== Find TRptCategorys ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TRptCategory tRptCategory = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tRptCategoryentity", tRptCategory);
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		jpaQuery.setCacheable(true);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TRptCategorys.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTRptCategorys(final SearchFilter<TRptCategory> searchFilter) {
		LOGGER.info("=========== Count TRptCategory ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TRptCategory tRptCategory = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tRptCategoryentity", tRptCategory);

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}
	
	
	@Override
	public List<TRptCategory> findTRptCategoryByCategoryIds(Integer rptCategoryId) {
		// TODO Auto-generated method stub
		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(rptCategoryId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("findTRptCategoryByCategoryIds", queryParam, 0, -1);
	}

	@Override
	public List<Object[]> findCategoryName(Integer rptCategoryId, String localeId) {
		List<Object> obj = new ArrayList<Object>();
		obj.add(rptCategoryId);
		obj.add(localeId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("findCategoryName", obj, 0, -1);
	}
	
	@Override
	public List<Object[]> findAllCategories(String localeId, short tenantId) {
		List<Object> obj = new ArrayList<Object>();
		obj.add(localeId);
		obj.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("findAllCategories", obj, 0, -1);
	}
	@Override
	public String findCategoryNameByCategoryId(Integer categoryId) {
		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(categoryId);
		List<String> catList = genericDAO.findEntitiesByNamedQueryMultiCond("findCategoryNameByCategoryId", queryParam, 0, -1);
		if (catList.get(0) != null) return catList.get(0);
		else return null;
	}
}
