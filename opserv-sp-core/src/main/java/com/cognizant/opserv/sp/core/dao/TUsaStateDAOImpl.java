package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TUsaState;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;

/**
 * Class provides API implementation for TUsaStateDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tUsaStateDAO")
public class TUsaStateDAOImpl implements TUsaStateDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TUsaStateDAOImpl.class);

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

//	private static final String TCOUNTRY = "tCountry";

	private final Class<TUsaState> clazz;

	public Class<TUsaState> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TUsaStateDAOImpl() {
		super();
		this.clazz = TUsaState.class;
	}

	/**
	 * Stores a new TUsaState entity object in to the persistent store
	 * 
	 * @param tUsaState
	 *            TUsaState Entity object to be persisted
	 * @return tUsaState Persisted TUsaState object
	 */
	public TUsaState createTUsaState(final TUsaState tUsaState) {
		LOGGER.info("=========== Create TUsaState ===========");
//		return gisDAO.store(tUsaState);
		return null;
	}

	/**
	 * Deletes a TUsaState entity object from the persistent store
	 * 
	 * @param tUsaState
	 *            TUsaState Entity object to be deleted
	 */
	public void deleteTUsaState(final Integer stateId) {
		LOGGER.info("=========== Delete TUsaState ===========");
//		final TUsaState tUsaState = gisDAO.get(clazz, stateId);
//		gisDAO.remove(tUsaState);
	}

	/**
	 * Updates a TUsaState entity object in to the persistent store
	 * 
	 * @param tUsaState
	 *            TUsaState Entity object to be updated
	 * @return tUsaState Persisted TUsaState object
	 */
	public TUsaState updateTUsaState(final TUsaState tUsaState) {
		LOGGER.info("=========== Update TUsaState ===========");
//		return gisDAO.update(tUsaState);
		return null;
	}

	/**
	 * Retrieve an TUsaState object based on given TUsaState stateId.
	 * 
	 * @param tUsaStateId
	 *            the primary key value of the TUsaState Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TUsaState findTUsaStateById(final Integer tUsaStateId) {
		LOGGER.info("find TUsaState instance with stateId: " + tUsaStateId);
//		return gisDAO.get(clazz, tUsaStateId);
		return null;
	}

	/**
	 * Retrieve TUsaState based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TUsaState> list of TUsaState if it exists against given criteria.
	 *         Returns null if not found
	 */
//	public List<TUsaState> findTUsaStates(final SearchFilter<TUsaState> searchFilter) {
//		LOGGER.info("=========== Find TUsaStates ===========");
//
//		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
//		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
//
//		final TUsaState tUsaState = searchFilter.getEntityClass();
//		final int maxresult = paginationInfo.getMaxRows();
//		final int index = paginationInfo.getStartIndex();
//		//int maxresult = 3;
//		//int index=0;
//		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
//
//		final JPAQuery jpaQuery = new JPAQuery("tUsaStateentity", tUsaState);
//
//		if (tUsaState.getTCountry() == null) {
//			jpaQuery.bind(TCOUNTRY, new TCountry());
//		} else {
//			LOGGER.info("tCountry {}", tUsaState.getTCountry());
//
//			jpaQuery.bind(TCOUNTRY, tUsaState.getTCountry());
//		}
//		jpaQuery.setJPAql(JPAQL);
//		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
//		jpaQuery.setLogicalOperatorEnum(logOpEnum);
//		return gisDAO.findEntities(jpaQuery, index, maxresult);
//	}

	/**
	 * Fetch count of records for TUsaStates.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
//	public Object countTUsaStates(final SearchFilter<TUsaState> searchFilter) {
//		LOGGER.info("=========== Count TUsaState ===========");
//
//		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
//		final TUsaState tUsaState = searchFilter.getEntityClass();
//		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
//		final JPAQuery jpaCountQuery = new JPAQuery("tUsaStateentity", tUsaState);
//
//		if (tUsaState.getTCountry() == null) {
//			jpaCountQuery.bind(TCOUNTRY, new TCountry());
//		} else {
//			LOGGER.info("tCountry {}", tUsaState.getTCountry());
//
//			jpaCountQuery.bind(TCOUNTRY, tUsaState.getTCountry());
//		}
//
//		jpaCountQuery.setJPAql(JPACOUNTQL);
//		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
//		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);
//
//		return gisDAO.findEntities(jpaCountQuery, -1, -1);
//	}

	/**
	 * Retrieve TUsaState based on given search criteria using JPA named Query.
	 * The search criteria is of TCountry type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TUsaState> list of TUsaStates if it exists against given
	 *         criteria. Returns null if not found
	 */
//	public List<TUsaState> getTUsaStatesByTCountry(final SearchFilter<TCountry> searchFilter) {
//
//		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
//		final TCountry tCountry = searchFilter.getEntityClass();
//		final int maxresult = paginationInfo.getMaxRows();
//		final int index = paginationInfo.getStartIndex();
//
//		return gisDAO.findEntitiesByNamedQuery("FindTUsaStateByTCountry", tCountry, index, maxresult);
//	}

	/**
	 * Count TUsaState based on given search criteria using JPA named Query.
	 * The search criteria is of TCountry type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
//	public Object countTUsaStatesByTCountry(final SearchFilter<TCountry> searchFilter) {
//
//		final TCountry tCountry = searchFilter.getEntityClass();
//		return gisDAO.findEntitiesByNamedQuery("CountTUsaStatesByTCountry", tCountry);
//	}
	
	
	public List<String> findTUsaStatesPolygon(){		
//		return gisDAO.findByNativeQuery("select AsText(ST_Envelope(shape_polygon)) as shape from t_usa_state where state_cd not in ('US-AK','US-HI','US-PR','US-MP','US-GU','US-AS','US-VI')");
		return null;
	}
	
	public List<String> findTUsaStatesCodes()
	{
//		return gisDAO.findByNativeQuery("select state_cd from t_usa_state where state_cd not in ('US-AK','US-HI','US-PR','US-MP','US-GU','US-AS','US-VI') ");
		return null;
	}
	
	public List<TUsaState> getTUsaStates(String query){		
//		return gisDAO.findByNativeQuery(query);
		return null;
	}

}
