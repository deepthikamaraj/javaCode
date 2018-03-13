package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TCust;
import com.cognizant.opserv.sp.core.entity.TCustPrdSales;
import com.cognizant.opserv.sp.core.entity.TCustPrdSalesId;
import com.cognizant.opserv.sp.core.entity.TPrd;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TCustPrdDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tCustPrdDAO")
public class TCustPrdSalesDAOImpl implements TCustPrdSalesDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(TCustPrdSalesDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private static final String TCUST = "tCust";
	private static final String TPRD = "tPrd";

	private final Class<TCustPrdSales> clazz;

	public Class<TCustPrdSales> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TCustPrdSalesDAOImpl() {
		super();
		this.clazz = TCustPrdSales.class;
	}

	private static final String JPAQL = "select tCustPrdentity from TCustPrdSales tCustPrdentity";

	private static final String JPACOUNTQL = "select count(*) from TCustPrdSales tCustPrdentity";

	private static final String[] RESTRICTIONS = {
			"tCustPrdentity.tCustPrdId.custId = #{tCustPrdentity.tCustPrdId.getCustId}",
			"tCustPrdentity.tCustPrdId.prdId like '#{tCustPrdentity.tCustPrdId.getPrdId}%'",
			"tCustPrdentity.activeFlag = #{tCustPrdentity.getActiveFlag}",
			"tCustPrdentity.createdBy = #{tCustPrdentity.getCreatedBy}",
			"Date(tCustPrdentity.createDt) = '#{tCustPrdentity.getCreateDt}'",
			"tCustPrdentity.updatedBy = #{tCustPrdentity.getUpdatedBy}",
			"Date(tCustPrdentity.updateDt) = '#{tCustPrdentity.getUpdateDt}'",
			"tCustPrdentity.tenantId = #{tCustPrdentity.getTenantId}",
			"tCustPrdentity.tmPerId = #{tCustPrdentity.getTmPerId}",
			"tCustPrdentity.brdDlrSaleValue = #{tCustPrdentity.getBrdDlrSaleValue}",
			"tCustPrdentity.brdUnitSaleValue = #{tCustPrdentity.getBrdUnitSaleValue}",
			"tCustPrdentity.mktDlrSaleValue = #{tCustPrdentity.getMktDlrSaleValue}",
			"tCustPrdentity.mktUnitSaleValue = #{tCustPrdentity.getMktUnitSaleValue}",
			"tCustPrdentity.tCust.custId = #{tCustPrdentity.tCust.getCustId}",
			"tCustPrdentity.tPrd.prdId = #{tCustPrdentity.tPrd.getPrdId}" };

	/**
	 * Stores a new TCustPrdSales entity object in to the persistent store
	 * 
	 * @param tCustPrd
	 *            TCustPrd Entity object to be persisted
	 * @return tCustPrd Persisted TCustPrd object
	 */
	public TCustPrdSales createTCustPrd(final TCustPrdSales tCustPrd) {
		LOGGER.info("=========== Create TCustPrd ===========");
		return genericDAO.store(tCustPrd);
	}

	/**
	 * Deletes a TCustPrd entity object from the persistent store
	 * 
	 * @param tCustPrd
	 *            TCustPrd Entity object to be deleted
	 */
	public void deleteTCustPrd(final TCustPrdSalesId tCustPrdId) {
		LOGGER.info("=========== Delete TCustPrd ===========");
		final TCustPrdSales tCustPrd = genericDAO.get(clazz, tCustPrdId);
		genericDAO.remove(tCustPrd);
	}

	/**
	 * Updates a TCustPrd entity object in to the persistent store
	 * 
	 * @param tCustPrd
	 *            TCustPrd Entity object to be updated
	 * @return tCustPrd Persisted TCustPrd object
	 */
	public TCustPrdSales updateTCustPrd(final TCustPrdSales tCustPrd) {
		LOGGER.info("=========== Update TCustPrd ===========");
		return genericDAO.update(tCustPrd);
	}

	/**
	 * Retrieve an TCustPrd object based on given TCustPrd TCustPrdId.
	 * 
	 * @param tCustPrdId
	 *            the primary key value of the TCustPrd Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TCustPrdSales findTCustPrdById(final TCustPrdSalesId tCustPrdId) {
		LOGGER.info("find TCustPrd instance with TCustPrdId: " + tCustPrdId);
		return genericDAO.get(clazz, tCustPrdId);
	}

	/**
	 * Retrieve TCustPrd based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCustPrd> list of TCustPrd if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TCustPrdSales> findTCustPrds(final SearchFilter<TCustPrdSales> searchFilter) {
		LOGGER.info("=========== Find TCustPrds ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TCustPrdSales tCustPrd = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tCustPrdentity", tCustPrd);

		if (tCustPrd.getTCust() == null) {
			jpaQuery.bind(TCUST, new TCust());
		} else {
			LOGGER.info("tCust {}", tCustPrd.getTCust());

			jpaQuery.bind(TCUST, tCustPrd.getTCust());
		}

		if (tCustPrd.getTPrd() == null) {
			jpaQuery.bind(TPRD, new TPrd());
		} else {
			LOGGER.info("tPrd {}", tCustPrd.getTPrd());

			jpaQuery.bind(TPRD, tCustPrd.getTPrd());
		}
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TCustPrds.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTCustPrds(final SearchFilter<TCustPrdSales> searchFilter) {
		LOGGER.info("=========== Count TCustPrd ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TCustPrdSales tCustPrd = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tCustPrdentity", tCustPrd);

		if (tCustPrd.getTCust() == null) {
			jpaCountQuery.bind(TCUST, new TCust());
		} else {
			LOGGER.info("tCust {}", tCustPrd.getTCust());

			jpaCountQuery.bind(TCUST, tCustPrd.getTCust());
		}

		if (tCustPrd.getTPrd() == null) {
			jpaCountQuery.bind(TPRD, new TPrd());
		} else {
			LOGGER.info("tPrd {}", tCustPrd.getTPrd());

			jpaCountQuery.bind(TPRD, tCustPrd.getTPrd());
		}

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TCustPrd based on given search criteria using JPA named Query.
	 * The search criteria is of TCust type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCustPrd> list of TCustPrds if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TPrd> getTPrdsByTCust(final SearchFilter<TCust> searchFilter) {

		final TCust tCust = searchFilter.getEntityClass();
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(tCust);
		paramList.add(tCust.getTenantId());

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTPrdByTCust", paramList, 0, -1);
	}

	/**
	 * Count TCustPrd based on given search criteria using JPA named Query.
	 * The search criteria is of TCust type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTCustPrdsByTCust(final SearchFilter<TCust> searchFilter) {

		final TCust tCust = searchFilter.getEntityClass();
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(tCust);
		return genericDAO.findEntitiesByNamedQuery("CountTCustPrdsByTCust", paramList);
	}

	/**
	 * Retrieve TCustPrd based on given search criteria using JPA named Query.
	 * The search criteria is of TPrd type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCustPrd> list of TCustPrds if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TCustPrdSales> getTCustPrdsByTPrd(final SearchFilter<TPrd> searchFilter) {

		final TPrd tPrd = searchFilter.getEntityClass();
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(tPrd);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTCustPrdByTPrd", paramList, 0, -1);
	}

	/**
	 * Count TCustPrd based on given search criteria using JPA named Query.
	 * The search criteria is of TPrd type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTCustPrdsByTPrd(final SearchFilter<TPrd> searchFilter) {

		final TPrd tPrd = searchFilter.getEntityClass();
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(tPrd);
		return genericDAO.findEntitiesByNamedQuery("CountTCustPrdsByTPrd", paramList);
	}
	/**
	 * Retrieve TCustPrd based on given search criteria using JPA named Query.
	 * The search criteria is of TCust type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCustPrd> list of TCustPrds if it exists against given
	 *         criteria. Returns null if not found
	 */
	@Override
	public List<TCustPrdSales> getTCustPrdsByCustIdList(TCust customer,
			Short tenantId) {
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(customer);
		paramList.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTCustPrdByTCust", paramList, 0, -1);
	}

}
