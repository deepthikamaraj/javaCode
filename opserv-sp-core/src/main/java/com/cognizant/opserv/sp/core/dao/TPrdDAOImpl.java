package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TPrd;
import com.cognizant.opserv.sp.core.entity.TPrdAttr;
import com.cognizant.opserv.sp.common.util.DateUtil;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TPrdDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tPrdDAO")
public class TPrdDAOImpl implements TPrdDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TPrdDAOImpl.class);

	private final static String COUNT_QUERY = "select count(tPrd) from TPrd tPrd ";
	private final static String LIST_QUERY = "select tPrd from TPrd tPrd";
	
	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private final Class<TPrd> clazz;

	public Class<TPrd> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TPrdDAOImpl() {
		super();
		this.clazz = TPrd.class;
	}

	private static final String JPAQL = "select tPrdentity from TPrd tPrdentity";

	private static final String JPACOUNTQL = "select count(tPrdentity) from TPrd tPrdentity";

	private static final String[] RESTRICTIONS = {
			"tPrdentity.prdId like '#{tPrdentity.getPrdId}%'",
			"tPrdentity.prdCd like '#{tPrdentity.getPrdCd}%'",
			"tPrdentity.prdDesc like '#{tPrdentity.getPrdDesc}%'",
			"tPrdentity.prdName like '#{tPrdentity.getPrdName}%'",
			"tPrdentity.activeFlag = #{tPrdentity.getActiveFlag}",
			"Date(tPrdentity.effStartDt) = '#{tPrdentity.getEffStartDt}'",
			"Date(tPrdentity.effEndDt) = '#{tPrdentity.getEffEndDt}'",
			"tPrdentity.createdBy = #{tPrdentity.getCreatedBy}",
			"Date(tPrdentity.createDt) = '#{tPrdentity.getCreateDt}'",
			"tPrdentity.updatedBy = #{tPrdentity.getUpdatedBy}",
			"Date(tPrdentity.updateDt) = '#{tPrdentity.getUpdateDt}'",
			"tPrdentity.tenantId = #{tPrdentity.getTenantId}",
			"tPrdentity.tenantId = #{tPrdentity.getTenantId}"

	};

	/**
	 * Stores a new TPrd entity object in to the persistent store
	 * 
	 * @param tPrd
	 *            TPrd Entity object to be persisted
	 * @return tPrd Persisted TPrd object
	 */
	public TPrd createTPrd(final TPrd tPrd) {
		LOGGER.info("=========== Create TPrd ===========");
		return genericDAO.store(tPrd);
	}

	/**
	 * Deletes a TPrd entity object from the persistent store
	 * 
	 * @param tPrd
	 *            TPrd Entity object to be deleted
	 */
	public void deleteTPrd(final Long prdId) {
		LOGGER.info("=========== Delete TPrd ===========");
		final TPrd tPrd = genericDAO.get(clazz, prdId);
		genericDAO.remove(tPrd);
	}

	/**
	 * Updates a TPrd entity object in to the persistent store
	 * 
	 * @param tPrd
	 *            TPrd Entity object to be updated
	 * @return tPrd Persisted TPrd object
	 */
	public TPrd updateTPrd(final TPrd tPrd) {
		LOGGER.info("=========== Update TPrd ===========");
		return genericDAO.update(tPrd);
	}

	/**
	 * Retrieve an TPrd object based on given TPrd prdId.
	 * 
	 * @param tPrdId
	 *            the primary key value of the TPrd Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TPrd findTPrdById(final Long tPrdId) {
		LOGGER.info("find TPrd instance with prdId: " + tPrdId);
		return genericDAO.get(clazz, tPrdId);
	}

	/**
	 * Retrieve TPrd based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TPrd> list of TPrd if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TPrd> findTPrds(final SearchFilter<TPrd> searchFilter) {
		LOGGER.info("=========== Find TPrds ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TPrd tPrd = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		// int maxresult = 3;
		// int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo
				.getLogicalOperatorAnd();

		final JPAQuery jpaQuery = new JPAQuery("tPrdentity", tPrd);
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TPrds.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTPrds(final SearchFilter<TPrd> searchFilter) {
		LOGGER.info("=========== Count TPrd ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TPrd tPrd = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tPrdentity", tPrd);

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * findrecentlyAddedProduct
	 * 
	 * @param algId
	 * @param bussId
	 * @param salesTeamId
	 * @param salesPosId
	 * @param salesHierId
	 * @param tenantId
	 * @param flag
	 * @param currentDate
	 * @return
	 */
	@Override
	public List<Object[]> findrecentlyAddedProduct(Long algId, Long bussId,
			Long salesTeamId, Long salesPosId, Long salesHierId,
			Short tenantId, Character flag, Date currentdate) {
		List paramList = new ArrayList();
		paramList.add(tenantId);
		paramList.add(algId);
		paramList.add(bussId);
		paramList.add(salesTeamId);
		paramList.add(flag);
		paramList.add(salesPosId);
		paramList.add(salesHierId);
		paramList.add(currentdate);
		return genericDAO.findEntitiesByNamedQueryMultiCond(
				"FindRecentlyAddedProductToSP", paramList, 0, -1);

	}

	/**
	 * productsWithExtAttr
	 * 
	 * @param searchFilter
	 * @return
	 */
	@Override
	public List<TPrd> productsWithExtAttr(SearchFilter<TPrd> searchFilter) {
		StringBuffer Query = new StringBuffer();
		TPrd tPrd = searchFilter.getEntityClass();
		List<TPrd> productList = new ArrayList<TPrd>();

		Set<TPrdAttr> tPrdAttr = tPrd.getTPrdAttrss();
		
		String prdEffEndDt = DateUtil.getDefaultDateTimeFormat(DateUtil.getCurrentDate(),Locale.ENGLISH);
		if (tPrdAttr != null && tPrdAttr.size() > 0) {
			Query.append("select tPrd from TPrd tPrd, TPrdAttr tPrdAttr where tPrd.activeFlag='Y' AND (tPrd.effEndDt IS NULL OR tPrd.effEndDt>='"+prdEffEndDt+"')");
		} else {
			Query.append("select tPrd from TPrd tPrd where tPrd.activeFlag='Y' AND (tPrd.effEndDt IS NULL OR tPrd.effEndDt>='"+prdEffEndDt+"')");
		}

		if (tPrd.getPrdId() != null && tPrd.getPrdId() != 0) {
			Query.append(" AND tPrd.prdId=" + "'" + tPrd.getPrdId()+ "'");
		}
		if (tPrd.getPrdName() != null && !(tPrd.getPrdName().isEmpty())) {
			Query.append(" AND tPrd.prdName LIKE " + "'%" + tPrd.getPrdName()
					+ "%'");
		}
		if (tPrd.getPrdDesc() != null && !(tPrd.getPrdDesc().isEmpty())) {
			Query.append(" AND tPrd.prdDesc LIKE " + "'%" + tPrd.getPrdDesc()
					+ "%'");
		}
		if (tPrd.getPrdCd() != null && !(tPrd.getPrdCd().isEmpty())) {
			Query.append(" AND tPrd.prdCd LIKE " + "'%" + tPrd.getPrdCd()
					+ "%'");
		}

		if (tPrdAttr != null && tPrdAttr.size() > 0) {
			for (TPrdAttr prdAtr : tPrdAttr) {
				Query.append(" AND tPrd.prdId=tPrdAttr.tPrdAttrId.prdId AND tPrdAttr.tPrdAttrId.attrId='"
						+ prdAtr.getTPrdAttrId().getAttrId()
						+ "' AND tPrdAttr.attrValue LIKE"
						+ "'%"
						+ prdAtr.getAttrValue() + "%'");
			}

		}
		Query.append(" GROUP BY tPrd.prdId");
		try {
			productList = genericDAO.findEntitiesByBuildQuery(Query.toString(),
					searchFilter.getPaginationInfo().getStartIndex(),
					searchFilter.getPaginationInfo().getMaxRows());
			/*
			 * productList=
			 * genericDAO.findEntitiesByNamedQuery(Query.toString());
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}
		return productList;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Long findProductCount(SearchFilter<TPrd> filter, Short tenantId) {
		List paramList = new ArrayList();
		paramList.add(tenantId);
		String finalQuery = COUNT_QUERY+" where tenantId=?1";
		if(filter.getEntityClass().getPrdCd()!=null && filter.getEntityClass().getPrdCd().trim().length()>0){
			
			finalQuery = finalQuery+" and prdCd LIKE"+"?"+(paramList.size()+1);
			paramList.add("%"+filter.getEntityClass().getPrdCd()+"%");
		}
		if(filter.getEntityClass().getPrdName()!=null && filter.getEntityClass().getPrdName().trim().length()>0){
			finalQuery = finalQuery+" and prdName LIKE "+"?"+(paramList.size()+1);
			paramList.add("%"+filter.getEntityClass().getPrdName()+"%");
		}
		if(filter.getEntityClass().getActiveFlag()!=null && filter.getEntityClass().getActiveFlag().toString().trim().length()>0){
			finalQuery = finalQuery+" and activeFlag=?"+(paramList.size()+1);
			paramList.add(filter.getEntityClass().getActiveFlag());
			/*if(filter.getEntityClass().getActiveFlag().equals('Y')){
				finalQuery = finalQuery+" and effEndDt>?"+(paramList.size()+1)+" or effEndDt is null";
				paramList.add(DateUtil.getCurrentDate());
				}else{
					finalQuery = finalQuery+" or effEndDt<=?"+(paramList.size()+1);
					paramList.add(DateUtil.getCurrentDate());
				}*/
				
		}
		if(filter.getEntityClass().getPrdId()!=null && filter.getEntityClass().getPrdId()>0){
			finalQuery = finalQuery+" and prdId LIKE "+"?"+(paramList.size()+1);
			paramList.add("%"+filter.getEntityClass().getPrdId()+"%");
		}
		
		return (Long) genericDAO.findEntitiesByBuildQueries(finalQuery, paramList, 0, -1).get(0);
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<TPrd> findProductList(SearchFilter<TPrd> filter,Short tenantId,int start,int size) {
		List paramList = new ArrayList();
		paramList.add(tenantId);
		String finalQuery = LIST_QUERY+" where tenantId=?1";
		if(filter.getEntityClass().getPrdCd()!=null && filter.getEntityClass().getPrdCd().trim().length()>0){
			finalQuery = finalQuery+" and prdCd LIKE " + "?"+ (paramList.size()+1);
			paramList.add("%"+filter.getEntityClass().getPrdCd()+"%");
		}
		if(filter.getEntityClass().getPrdName()!=null && filter.getEntityClass().getPrdName().trim().length()>0){
			finalQuery = finalQuery+" and prdName LIKE " + "?"+ (paramList.size()+1);
			paramList.add("%"+filter.getEntityClass().getPrdName()+"%");
		}
		if(filter.getEntityClass().getActiveFlag()!=null && filter.getEntityClass().getActiveFlag().toString().trim().length()>0){
			finalQuery = finalQuery+" and activeFlag=?"+(paramList.size()+1);
			paramList.add(filter.getEntityClass().getActiveFlag());
			
			/*if(filter.getEntityClass().getActiveFlag().equals('Y')){
			finalQuery = finalQuery+" and effEndDt>?"+(paramList.size()+1)+" or effEndDt is null";
			paramList.add(DateUtil.getCurrentDate());
			}else{
				finalQuery = finalQuery+" or effEndDt<=?"+(paramList.size()+1);
				paramList.add(DateUtil.getCurrentDate());
			}*/
			
		}
		if(filter.getEntityClass().getPrdId()!=null && filter.getEntityClass().getPrdId()>0){
			finalQuery = finalQuery+" and prdId LIKE " + "?"+ (paramList.size()+1);
			paramList.add("%"+filter.getEntityClass().getPrdId()+"%");
		}
		
		Set<String> sortColumns = filter.getSortInfo().getSortColumns();
		
		if(sortColumns !=null&&sortColumns.size()>0){
			String coulmnName = sortColumns.toArray()[0].toString();
			//SortOrderEnum sortOrder = filter.getSortInfo().getOrder(coulmnName);
			
			
			if(coulmnName!=null && !(coulmnName.equalsIgnoreCase("")))
			{
				String ordBy = "DESC";
				if(filter.getSortInfo().getOrder(coulmnName).toString().equals("ASCENDING")){
					ordBy = "ASC";
				}
				
				finalQuery = finalQuery+" order by "+coulmnName + " "+ordBy;
			}
		}
		return  genericDAO.findEntitiesByBuildQueries(finalQuery, paramList, start, size);
	}

	
	@Override
	public List<TPrd> findTPrdsByPrdId(List<Long> productIds,Short tenantId) {
		List<Object> paramList = new ArrayList<Object>();

		paramList.add(productIds);
		paramList.add(tenantId);


		return genericDAO.findEntitiesByNamedQueryMultiCond(
				"FindTPrdsByPrdIds", paramList, 0, -1);
	}
	

}