package com.cognizant.opserv.sp.core.dao;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TAttrGroupMap;
import com.cognizant.opserv.sp.core.entity.TUsrAttrPref;
import com.cognizant.opserv.sp.core.entity.TUsrPref;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;


/**
 * Class provides API implementation for TUsrAttrPrefDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tUsrAttrPrefDAO")
public class TUsrAttrPrefDAOImpl implements TUsrAttrPrefDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TUsrAttrPrefDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private static final String TATTRGROUPMAP = "tAttrGroupMap";
	private static final String TUSRPREF = "tUsrPref";

	private final Class<TUsrAttrPref> clazz;

	public Class<TUsrAttrPref> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TUsrAttrPrefDAOImpl() {
		super();
		this.clazz = TUsrAttrPref.class;
	}

	private static final String JPAQL = "select tUsrAttrPrefentity from TUsrAttrPref tUsrAttrPrefentity";

	private static final String JPACOUNTQL = "select count(tUsrAttrPrefentity) from TUsrAttrPref tUsrAttrPrefentity";

	private static final String[] RESTRICTIONS = {
			"tUsrAttrPrefentity.prefId = #{tUsrAttrPrefentity.getPrefId}",
			"tUsrAttrPrefentity.activeFlag = #{tUsrAttrPrefentity.getActiveFlag}",
			"tUsrAttrPrefentity.createdBy = #{tUsrAttrPrefentity.getCreatedBy}",
			"Date(tUsrAttrPrefentity.createDt) = '#{tUsrAttrPrefentity.getCreateDt}'",
			"tUsrAttrPrefentity.updatedBy = #{tUsrAttrPrefentity.getUpdatedBy}",
			"Date(tUsrAttrPrefentity.updateDt) = '#{tUsrAttrPrefentity.getUpdateDt}'",
			"tUsrAttrPrefentity.tenantId = #{tUsrAttrPrefentity.getTenantId}",
			"tUsrAttrPrefentity.tAttrGroupMap.tAttrGroupMapId = #{tUsrAttrPrefentity.tAttrGroupMap.getTAttrGroupMapId}",
			"tUsrAttrPrefentity.tUsrPref.tUsrPrefId = #{tUsrAttrPrefentity.tUsrPref.getTUsrPrefId}" };

	/**
	 * Stores a new TUsrAttrPref entity object in to the persistent store
	 * 
	 * @param tUsrAttrPref
	 *            TUsrAttrPref Entity object to be persisted
	 * @return tUsrAttrPref Persisted TUsrAttrPref object
	 */
	public TUsrAttrPref createTUsrAttrPref(final TUsrAttrPref tUsrAttrPref) {
		LOGGER.info("=========== Create TUsrAttrPref ===========");
		return genericDAO.store(tUsrAttrPref);
	}

	/**
	 * Deletes a TUsrAttrPref entity object from the persistent store
	 * 
	 * @param tUsrAttrPref
	 *            TUsrAttrPref Entity object to be deleted
	 */
	public void deleteTUsrAttrPref(final Integer prefId) {
		LOGGER.info("=========== Delete TUsrAttrPref ===========");
		final TUsrAttrPref tUsrAttrPref = genericDAO.get(clazz, prefId);
		genericDAO.remove(tUsrAttrPref);
	}

	/**
	 * Updates a TUsrAttrPref entity object in to the persistent store
	 * 
	 * @param tUsrAttrPref
	 *            TUsrAttrPref Entity object to be updated
	 * @return tUsrAttrPref Persisted TUsrAttrPref object
	 */
	public TUsrAttrPref updateTUsrAttrPref(final TUsrAttrPref tUsrAttrPref) {
		LOGGER.info("=========== Update TUsrAttrPref ===========");
		return genericDAO.update(tUsrAttrPref);
	}

	/**
	 * Retrieve an TUsrAttrPref object based on given TUsrAttrPref prefId.
	 * 
	 * @param tUsrAttrPrefId
	 *            the primary key value of the TUsrAttrPref Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TUsrAttrPref findTUsrAttrPrefById(final Integer tUsrAttrPrefId) {
		LOGGER.info("find TUsrAttrPref instance with prefId: " + tUsrAttrPrefId);
		return genericDAO.get(clazz, tUsrAttrPrefId);
	}

	/**
	 * Retrieve TUsrAttrPref based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TUsrAttrPref> list of TUsrAttrPref if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TUsrAttrPref> findTUsrAttrPrefs(final SearchFilter<TUsrAttrPref> searchFilter) {
		LOGGER.info("=========== Find TUsrAttrPrefs ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TUsrAttrPref tUsrAttrPref = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tUsrAttrPrefentity", tUsrAttrPref);

		if (tUsrAttrPref.getTAttrGroupMap() == null) {
			jpaQuery.bind(TATTRGROUPMAP, new TAttrGroupMap());
		} else {
			LOGGER.info("tAttrGroupMap {}"+ tUsrAttrPref.getTAttrGroupMap());

			jpaQuery.bind(TATTRGROUPMAP, tUsrAttrPref.getTAttrGroupMap());
		}

		if (tUsrAttrPref.getTUsrPref() == null) {
			jpaQuery.bind(TUSRPREF, new TUsrPref());
		} else {
			LOGGER.info("tUsrPref {}"+ tUsrAttrPref.getTUsrPref());

			jpaQuery.bind(TUSRPREF, tUsrAttrPref.getTUsrPref());
		}
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TUsrAttrPrefs.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTUsrAttrPrefs(final SearchFilter<TUsrAttrPref> searchFilter) {
		LOGGER.info("=========== Count TUsrAttrPref ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TUsrAttrPref tUsrAttrPref = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tUsrAttrPrefentity", tUsrAttrPref);

		if (tUsrAttrPref.getTAttrGroupMap() == null) {
			jpaCountQuery.bind(TATTRGROUPMAP, new TAttrGroupMap());
		} else {
			LOGGER.info("tAttrGroupMap {}"+ tUsrAttrPref.getTAttrGroupMap());

			jpaCountQuery.bind(TATTRGROUPMAP, tUsrAttrPref.getTAttrGroupMap());
		}

		if (tUsrAttrPref.getTUsrPref() == null) {
			jpaCountQuery.bind(TUSRPREF, new TUsrPref());
		} else {
			LOGGER.info("tUsrPref {}"+ tUsrAttrPref.getTUsrPref());

			jpaCountQuery.bind(TUSRPREF, tUsrAttrPref.getTUsrPref());
		}

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TUsrAttrPref based on given search criteria using JPA named Query.
	 * The search criteria is of TAttrGroupMap type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TUsrAttrPref> list of TUsrAttrPrefs if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TUsrAttrPref> getTUsrAttrPrefsByTAttrGroupMap(final SearchFilter<TAttrGroupMap> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTUsrAttrPrefByTAttrGroupMap", queryParam, index, maxresult);
	}

	/**
	 * Count TUsrAttrPref based on given search criteria using JPA named Query.
	 * The search criteria is of TAttrGroupMap type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTUsrAttrPrefsByTAttrGroupMap(final SearchFilter<TAttrGroupMap> searchFilter) {

		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());
		return genericDAO.findEntitiesByNamedQuery("CountTUsrAttrPrefsByTAttrGroupMap", queryParam);
	}

	/**
	 * Retrieve TUsrAttrPref based on given search criteria using JPA named Query.
	 * The search criteria is of TUsrPref type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TUsrAttrPref> list of TUsrAttrPrefs if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TUsrAttrPref> getTUsrAttrPrefsByTUsrPref(final SearchFilter<TUsrPref> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTUsrAttrPrefByTUsrPref", queryParam, index, maxresult);
	}

	/**
	 * Count TUsrAttrPref based on given search criteria using JPA named Query.
	 * The search criteria is of TUsrPref type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTUsrAttrPrefsByTUsrPref(final SearchFilter<TUsrPref> searchFilter) {

		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());
		return genericDAO.findEntitiesByNamedQuery("CountTUsrAttrPrefsByTUsrPref", queryParam);
	}
	
	@Override
	public List<Object[]> findTUsrAttrPrefsByTUsrPref(final Integer featureId,final Integer usrId,final Integer roleId, 
			final Character activeFlag , final Short tenantId ) {
		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(featureId);
		queryParam.add(usrId);
		queryParam.add(roleId);
		queryParam.add(activeFlag);
		queryParam.add(tenantId);
		return genericDAO.findEntitiesByNamedQuery("FindTUsrAttrPrefsByTUsrPref", queryParam);
	}

}
