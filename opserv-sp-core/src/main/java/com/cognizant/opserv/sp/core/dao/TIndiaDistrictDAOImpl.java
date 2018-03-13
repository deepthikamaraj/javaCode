package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TIndiaDistrict;
import com.cognizant.opserv.sp.core.entity.TIndiaState;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TIndiaDistrictDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tIndiaDistrictDAO")
public class TIndiaDistrictDAOImpl implements TIndiaDistrictDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TIndiaDistrictDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private static final String TINDIASTATE = "tIndiaState";

	private final Class<TIndiaDistrict> clazz;

	public Class<TIndiaDistrict> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TIndiaDistrictDAOImpl() {
		super();
		this.clazz = TIndiaDistrict.class;
	}

	private static final String JPAQL = "select tIndiaDistrictentity from TIndiaDistrict tIndiaDistrictentity";

	private static final String JPACOUNTQL = "select count(tIndiaDistrictentity) from TIndiaDistrict tIndiaDistrictentity";

	private static final String[] RESTRICTIONS = {
			"tIndiaDistrictentity.districtId = #{tIndiaDistrictentity.getDistrictId}",
			"tIndiaDistrictentity.districtName like '#{tIndiaDistrictentity.getDistrictName}%'",
			"tIndiaDistrictentity.shapePolygon = #{tIndiaDistrictentity.getShapePolygon}",
			"tIndiaDistrictentity.tIndiaState.stateId = #{tIndiaDistrictentity.tIndiaState.getStateId}" };

	/**
	 * Stores a new TIndiaDistrict entity object in to the persistent store
	 * 
	 * @param tIndiaDistrict
	 *            TIndiaDistrict Entity object to be persisted
	 * @return tIndiaDistrict Persisted TIndiaDistrict object
	 */
	public TIndiaDistrict createTIndiaDistrict(final TIndiaDistrict tIndiaDistrict) {
		LOGGER.info("=========== Create TIndiaDistrict ===========");
		return genericDAO.store(tIndiaDistrict);
	}

	/**
	 * Deletes a TIndiaDistrict entity object from the persistent store
	 * 
	 * @param tIndiaDistrict
	 *            TIndiaDistrict Entity object to be deleted
	 */
	public void deleteTIndiaDistrict(final Integer districtId) {
		LOGGER.info("=========== Delete TIndiaDistrict ===========");
		final TIndiaDistrict tIndiaDistrict = genericDAO.get(clazz, districtId);
		genericDAO.remove(tIndiaDistrict);
	}

	/**
	 * Updates a TIndiaDistrict entity object in to the persistent store
	 * 
	 * @param tIndiaDistrict
	 *            TIndiaDistrict Entity object to be updated
	 * @return tIndiaDistrict Persisted TIndiaDistrict object
	 */
	public TIndiaDistrict updateTIndiaDistrict(final TIndiaDistrict tIndiaDistrict) {
		LOGGER.info("=========== Update TIndiaDistrict ===========");
		return genericDAO.update(tIndiaDistrict);
	}

	/**
	 * Retrieve an TIndiaDistrict object based on given TIndiaDistrict districtId.
	 * 
	 * @param tIndiaDistrictId
	 *            the primary key value of the TIndiaDistrict Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TIndiaDistrict findTIndiaDistrictById(final Integer tIndiaDistrictId) {
		LOGGER.info("find TIndiaDistrict instance with districtId: " + tIndiaDistrictId);
		return genericDAO.get(clazz, tIndiaDistrictId);
	}

	/**
	 * Retrieve TIndiaDistrict based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TIndiaDistrict> list of TIndiaDistrict if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TIndiaDistrict> findTIndiaDistricts(final SearchFilter<TIndiaDistrict> searchFilter) {
		LOGGER.info("=========== Find TIndiaDistricts ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TIndiaDistrict tIndiaDistrict = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tIndiaDistrictentity", tIndiaDistrict);

		if (tIndiaDistrict.getTIndiaState() == null) {
			jpaQuery.bind(TINDIASTATE, new TIndiaState());
		} else {
			LOGGER.info("tIndiaState {}"+ tIndiaDistrict.getTIndiaState());

			jpaQuery.bind(TINDIASTATE, tIndiaDistrict.getTIndiaState());
		}
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TIndiaDistricts.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTIndiaDistricts(final SearchFilter<TIndiaDistrict> searchFilter) {
		LOGGER.info("=========== Count TIndiaDistrict ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TIndiaDistrict tIndiaDistrict = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tIndiaDistrictentity", tIndiaDistrict);

		if (tIndiaDistrict.getTIndiaState() == null) {
			jpaCountQuery.bind(TINDIASTATE, new TIndiaState());
		} else {
			LOGGER.info("tIndiaState {}"+ tIndiaDistrict.getTIndiaState());

			jpaCountQuery.bind(TINDIASTATE, tIndiaDistrict.getTIndiaState());
		}

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TIndiaDistrict based on given search criteria using JPA named Query.
	 * The search criteria is of TIndiaState type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TIndiaDistrict> list of TIndiaDistricts if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TIndiaDistrict> getTIndiaDistrictsByTIndiaState(final SearchFilter<TIndiaState> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TIndiaState tIndiaState = searchFilter.getEntityClass();
		List<Object> tIndiaStateList = new ArrayList<Object>();
		tIndiaStateList.add(tIndiaState);
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTIndiaDistrictByTIndiaState", tIndiaStateList, index, maxresult);
	}

	/**
	 * Count TIndiaDistrict based on given search criteria using JPA named Query.
	 * The search criteria is of TIndiaState type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTIndiaDistrictsByTIndiaState(final SearchFilter<TIndiaState> searchFilter) {

		final TIndiaState tIndiaState = searchFilter.getEntityClass();
		List<Object> tIndiaStateList = new ArrayList<Object>();
		tIndiaStateList.add(tIndiaState);
		return genericDAO.findEntitiesByNamedQuery("CountTIndiaDistrictsByTIndiaState", tIndiaStateList);
	}

	public List<String> findIndiaDistrict()
	{
		return genericDAO.findByNativeQuery("select district_name from t_india_district");
	}
}
