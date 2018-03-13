package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TGeoHierList;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TGeoHierListDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tGeoHierListDAO")
public class TGeoHierListDAOImpl implements TGeoHierListDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(TGeoHierListDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private static final String TGEOHIERLIST = "tGeoHierList";

	private final Class<TGeoHierList> clazz;

	public Class<TGeoHierList> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TGeoHierListDAOImpl() {
		super();
		this.clazz = TGeoHierList.class;
	}

	private static final String JPAQL = "select tGeoHierListentity from TGeoHierList tGeoHierListentity";

	private static final String JPACOUNTQL = "select count(tGeoHierListentity) from TGeoHierList tGeoHierListentity";

	private static final String[] RESTRICTIONS = { "tGeoHierListentity.geoHierId = #{tGeoHierListentity.getGeoHierId}",
		"tGeoHierListentity.hierName like '#{tGeoHierListentity.getHierName}%'",
		"tGeoHierListentity.hierDesc like '#{tGeoHierListentity.getHierDesc}%'",
		"tGeoHierListentity.activeFlag = #{tGeoHierListentity.getActiveFlag}",
		"tGeoHierListentity.createdBy = #{tGeoHierListentity.getCreatedBy}",
		"Date(tGeoHierListentity.createDt) = '#{tGeoHierListentity.getCreateDt}'",
		"tGeoHierListentity.updatedBy = #{tGeoHierListentity.getUpdatedBy}",
		"Date(tGeoHierListentity.updateDt) = '#{tGeoHierListentity.getUpdateDt}'",
		"tGeoHierListentity.tenantId = #{tGeoHierListentity.getTenantId}",
	"tGeoHierListentity.tGeoHierList.geoHierId = #{tGeoHierListentity.tGeoHierList.getGeoHierId}" };

	/**
	 * Stores a new TGeoHierList entity object in to the persistent store
	 * 
	 * @param tGeoHierList
	 *            TGeoHierList Entity object to be persisted
	 * @return tGeoHierList Persisted TGeoHierList object
	 */
	public TGeoHierList createTGeoHierList(final TGeoHierList tGeoHierList) {
		LOGGER.info("=========== Create TGeoHierList ===========");
		return genericDAO.store(tGeoHierList);
	}

	/**
	 * Deletes a TGeoHierList entity object from the persistent store
	 * 
	 * @param tGeoHierList
	 *            TGeoHierList Entity object to be deleted
	 */
	public void deleteTGeoHierList(final Integer geoHierId) {
		LOGGER.info("=========== Delete TGeoHierList ===========");
		final TGeoHierList tGeoHierList = genericDAO.get(clazz, geoHierId);
		genericDAO.remove(tGeoHierList);
	}

	/**
	 * Updates a TGeoHierList entity object in to the persistent store
	 * 
	 * @param tGeoHierList
	 *            TGeoHierList Entity object to be updated
	 * @return tGeoHierList Persisted TGeoHierList object
	 */
	public TGeoHierList updateTGeoHierList(final TGeoHierList tGeoHierList) {
		LOGGER.info("=========== Update TGeoHierList ===========");
		return genericDAO.update(tGeoHierList);
	}

	/**
	 * Retrieve an TGeoHierList object based on given TGeoHierList geoHierId.
	 * 
	 * @param tGeoHierListId
	 *            the primary key value of the TGeoHierList Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TGeoHierList findTGeoHierListById(final Integer tGeoHierListId) {
		LOGGER.info("find TGeoHierList instance with geoHierId: " + tGeoHierListId);
		return genericDAO.get(clazz, tGeoHierListId);
	}

	/**
	 * Retrieve TGeoHierList based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TGeoHierList> list of TGeoHierList if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TGeoHierList> findTGeoHierLists(final SearchFilter<TGeoHierList> searchFilter) {
		LOGGER.info("=========== Find TGeoHierLists ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TGeoHierList tGeoHierList = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tGeoHierListentity", tGeoHierList);

		if (tGeoHierList.getTGeoHierList() == null) {
			jpaQuery.bind(TGEOHIERLIST, new TGeoHierList());
		} else {
			LOGGER.info("tGeoHierList {}", tGeoHierList.getTGeoHierList());

			jpaQuery.bind(TGEOHIERLIST, tGeoHierList.getTGeoHierList());
		}
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TGeoHierLists.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTGeoHierLists(final SearchFilter<TGeoHierList> searchFilter) {
		LOGGER.info("=========== Count TGeoHierList ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TGeoHierList tGeoHierList = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tGeoHierListentity", tGeoHierList);

		if (tGeoHierList.getTGeoHierList() == null) {
			jpaCountQuery.bind(TGEOHIERLIST, new TGeoHierList());
		} else {
			LOGGER.info("tGeoHierList {}", tGeoHierList.getTGeoHierList());

			jpaCountQuery.bind(TGEOHIERLIST, tGeoHierList.getTGeoHierList());
		}

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}	
	
	@Override
	public List<Object[]> getGeoHierLevels(Short tenantId,Character flag) {
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(tenantId);
		paramList.add(flag);
		List<Object[]> list = genericDAO.findEntitiesByNamedQueryMultiCond("GetGeoHierLevels", paramList, 0, -1);
		return list;
	}
	
	
	@Override
	public boolean isLevelNameExists(String levelName,Short tenantId,Character flag) {
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(levelName);
		paramList.add(tenantId);
		paramList.add(flag);
		List<Object> count =  genericDAO.findEntitiesByNamedQueryMultiCond("IsLevelNameExists", paramList, 0, -1);
		Long countL = (Long) count.get(0);
		return (countL>0)?true:false;
	}

}
