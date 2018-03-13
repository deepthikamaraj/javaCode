package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TIndiaState;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;

/**
 * Class provides API implementation for TIndiaStateDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tIndiaStateDAO")
public class TIndiaStateDAOImpl implements TIndiaStateDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TIndiaStateDAOImpl.class);

//	@Autowired
//	private GisDAO gisDAO;
//
//	public GisDAO getgisDAO() {
//		return gisDAO;
//	}
//
//	public void setgisDAO(final GisDAO gisDAO) {
//		this.gisDAO = gisDAO;
//	}

	private final Class<TIndiaState> clazz;

	public Class<TIndiaState> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TIndiaStateDAOImpl() {
		super();
		this.clazz = TIndiaState.class;
	}

	/**
	 * Stores a new TIndiaState entity object in to the persistent store
	 * 
	 * @param tIndiaState
	 *            TIndiaState Entity object to be persisted
	 * @return tIndiaState Persisted TIndiaState object
	 */
	public TIndiaState createTIndiaState(final TIndiaState tIndiaState) {
		LOGGER.info("=========== Create TIndiaState ===========");
//		return gisDAO.store(tIndiaState);
		return null;
	}

	/**
	 * Deletes a TIndiaState entity object from the persistent store
	 * 
	 * @param tIndiaState
	 *            TIndiaState Entity object to be deleted
	 */
	public void deleteTIndiaState(final Integer stateId) {
		LOGGER.info("=========== Delete TIndiaState ===========");
//		final TIndiaState tIndiaState = gisDAO.get(clazz, stateId);
//		gisDAO.remove(tIndiaState);
	}

	/**
	 * Updates a TIndiaState entity object in to the persistent store
	 * 
	 * @param tIndiaState
	 *            TIndiaState Entity object to be updated
	 * @return tIndiaState Persisted TIndiaState object
	 */
	public TIndiaState updateTIndiaState(final TIndiaState tIndiaState) {
		LOGGER.info("=========== Update TIndiaState ===========");
//		return gisDAO.update(tIndiaState);
		return null;
	}

	/**
	 * Retrieve an TIndiaState object based on given TIndiaState stateId.
	 * 
	 * @param tIndiaStateId
	 *            the primary key value of the TIndiaState Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TIndiaState findTIndiaStateById(final Integer tIndiaStateId) {
		LOGGER.info("find TIndiaState instance with stateId: " + tIndiaStateId);
//		return gisDAO.get(clazz, tIndiaStateId);
		return null;
	}

	/**
	 * Retrieve TIndiaState based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TIndiaState> list of TIndiaState if it exists against given criteria.
	 *         Returns null if not found
	 */
//	public List<TIndiaState> findTIndiaStates(final SearchFilter<TIndiaState> searchFilter) {
//		LOGGER.info("=========== Find TIndiaStates ===========");
//
//		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
//		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
//
//		final TIndiaState tIndiaState = searchFilter.getEntityClass();
//		final int maxresult = paginationInfo.getMaxRows();
//		final int index = paginationInfo.getStartIndex();
//		//int maxresult = 3;
//		//int index=0;
//		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
//
//		final JPAQuery jpaQuery = new JPAQuery("tIndiaStateentity", tIndiaState);
//
//		if (tIndiaState.getTCountry() == null) {
//			jpaQuery.bind(TCOUNTRY, new TCountry());
//		} else {
//			LOGGER.info("tCountry {}", tIndiaState.getTCountry());
//
//			jpaQuery.bind(TCOUNTRY, tIndiaState.getTCountry());
//		}
//		jpaQuery.setJPAql(JPAQL);
//		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
//		jpaQuery.setLogicalOperatorEnum(logOpEnum);
//		return gisDAO.findEntities(jpaQuery, index, maxresult);
//	}

	/**
	 * Fetch count of records for TIndiaStates.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
//	public Object countTIndiaStates(final SearchFilter<TIndiaState> searchFilter) {
//		LOGGER.info("=========== Count TIndiaState ===========");
//
//		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
//		final TIndiaState tIndiaState = searchFilter.getEntityClass();
//		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
//		final JPAQuery jpaCountQuery = new JPAQuery("tIndiaStateentity", tIndiaState);
//
//		if (tIndiaState.getTCountry() == null) {
//			jpaCountQuery.bind(TCOUNTRY, new TCountry());
//		} else {
//			LOGGER.info("tCountry {}", tIndiaState.getTCountry());
//
//			jpaCountQuery.bind(TCOUNTRY, tIndiaState.getTCountry());
//		}
//
//		jpaCountQuery.setJPAql(JPACOUNTQL);
//		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
//		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);
//
//		return gisDAO.findEntities(jpaCountQuery, -1, -1);
//	}

	/**
	 * Retrieve TIndiaState based on given search criteria using JPA named Query.
	 * The search criteria is of TCountry type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TIndiaState> list of TIndiaStates if it exists against given
	 *         criteria. Returns null if not found
	 */
//	public List<TIndiaState> getTIndiaStatesByTCountry(final SearchFilter<TCountry> searchFilter) {
//
//		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
//		final TCountry tCountry = searchFilter.getEntityClass();
//		final int maxresult = paginationInfo.getMaxRows();
//		final int index = paginationInfo.getStartIndex();
//
//		return gisDAO.findEntitiesByNamedQuery("FindTIndiaStateByTCountry", tCountry, index, maxresult);
//	}

	/**
	 * Count TIndiaState based on given search criteria using JPA named Query.
	 * The search criteria is of TCountry type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
//	public Object countTIndiaStatesByTCountry(final SearchFilter<TCountry> searchFilter) {
//
//		final TCountry tCountry = searchFilter.getEntityClass();
//		return gisDAO.findEntitiesByNamedQuery("CountTIndiaStatesByTCountry", tCountry);
//	}
	
	public List<String> findTIndiaStatesPolygon(){		
		
//		return gisDAO.findByNativeQuery("select AsText(ST_Envelope(shape_polygon)) as shape from t_india_state");
		return null;
	}

	public List<String> findIndiaStates(){
		
//		return gisDAO.findByNativeQuery("select state_name from t_india_state");
		return null;
	}
	
}
