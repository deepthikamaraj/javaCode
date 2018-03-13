package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TUsaCounty;
import com.cognizant.opserv.sp.core.entity.TUsaState;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TUsaCountyDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tUsaCountyDAO")
public class TUsaCountyDAOImpl implements TUsaCountyDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TUsaCountyDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private static final String TUSASTATE = "tUsaState";

	private final Class<TUsaCounty> clazz;

	public Class<TUsaCounty> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TUsaCountyDAOImpl() {
		super();
		this.clazz = TUsaCounty.class;
	}

	private static final String JPAQL = "select tUsaCountyentity from TUsaCounty tUsaCountyentity";

	private static final String JPACOUNTQL = "select count(tUsaCountyentity) from TUsaCounty tUsaCountyentity";

	private static final String[] RESTRICTIONS = { "tUsaCountyentity.countyId = #{tUsaCountyentity.getCountyId}",
			"tUsaCountyentity.countyCd like '#{tUsaCountyentity.getCountyCd}%'",
			"tUsaCountyentity.countyName like '#{tUsaCountyentity.getCountyName}%'",
			"tUsaCountyentity.shapePolygon = #{tUsaCountyentity.getShapePolygon}",
			"tUsaCountyentity.tUsaState.stateId = #{tUsaCountyentity.tUsaState.getStateId}" };

	/**
	 * Stores a new TUsaCounty entity object in to the persistent store
	 * 
	 * @param tUsaCounty
	 *            TUsaCounty Entity object to be persisted
	 * @return tUsaCounty Persisted TUsaCounty object
	 */
	public TUsaCounty createTUsaCounty(final TUsaCounty tUsaCounty) {
		LOGGER.info("=========== Create TUsaCounty ===========");
		return genericDAO.store(tUsaCounty);
	}

	/**
	 * Deletes a TUsaCounty entity object from the persistent store
	 * 
	 * @param tUsaCounty
	 *            TUsaCounty Entity object to be deleted
	 */
	public void deleteTUsaCounty(final Integer countyId) {
		LOGGER.info("=========== Delete TUsaCounty ===========");
		final TUsaCounty tUsaCounty = genericDAO.get(clazz, countyId);
		genericDAO.remove(tUsaCounty);
	}

	/**
	 * Updates a TUsaCounty entity object in to the persistent store
	 * 
	 * @param tUsaCounty
	 *            TUsaCounty Entity object to be updated
	 * @return tUsaCounty Persisted TUsaCounty object
	 */
	public TUsaCounty updateTUsaCounty(final TUsaCounty tUsaCounty) {
		LOGGER.info("=========== Update TUsaCounty ===========");
		return genericDAO.update(tUsaCounty);
	}

	/**
	 * Retrieve an TUsaCounty object based on given TUsaCounty countyId.
	 * 
	 * @param tUsaCountyId
	 *            the primary key value of the TUsaCounty Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TUsaCounty findTUsaCountyById(final Integer tUsaCountyId) {
		LOGGER.info("find TUsaCounty instance with countyId: " + tUsaCountyId);
		return genericDAO.get(clazz, tUsaCountyId);
	}

	/**
	 * Retrieve TUsaCounty based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TUsaCounty> list of TUsaCounty if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TUsaCounty> findTUsaCountys(final SearchFilter<TUsaCounty> searchFilter) {
		LOGGER.info("=========== Find TUsaCountys ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TUsaCounty tUsaCounty = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tUsaCountyentity", tUsaCounty);

		if (tUsaCounty.getTUsaState() == null) {
			jpaQuery.bind(TUSASTATE, new TUsaState());
		} else {
			LOGGER.info("tUsaState {}"+ tUsaCounty.getTUsaState());

			jpaQuery.bind(TUSASTATE, tUsaCounty.getTUsaState());
		}
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TUsaCountys.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTUsaCountys(final SearchFilter<TUsaCounty> searchFilter) {
		LOGGER.info("=========== Count TUsaCounty ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TUsaCounty tUsaCounty = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tUsaCountyentity", tUsaCounty);

		if (tUsaCounty.getTUsaState() == null) {
			jpaCountQuery.bind(TUSASTATE, new TUsaState());
		} else {
			LOGGER.info("tUsaState {}"+ tUsaCounty.getTUsaState());

			jpaCountQuery.bind(TUSASTATE, tUsaCounty.getTUsaState());
		}

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TUsaCounty based on given search criteria using JPA named Query.
	 * The search criteria is of TUsaState type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TUsaCounty> list of TUsaCountys if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TUsaCounty> getTUsaCountysByTUsaState(final SearchFilter<TUsaState> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTUsaCountyByTUsaState", queryParam, index, maxresult);
	}

	/**
	 * Count TUsaCounty based on given search criteria using JPA named Query.
	 * The search criteria is of TUsaState type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTUsaCountysByTUsaState(final SearchFilter<TUsaState> searchFilter) {

		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());
		return genericDAO.findEntitiesByNamedQuery("CountTUsaCountysByTUsaState", queryParam);
	}
	
	public List<String> getTUsaCounty(){
		/*List<String> countyIDList=new ArrayList<String>(); 
		List countyID = genericDAO.findByNativeQuery("select distinct county_name from t_usa_county");
		for(int i=0;i< countyID.size();i++)
		{
			String id = String.valueOf(countyID.get(i));
			countyIDList.add(id);
		}*/
		//return countyIDList;
		return genericDAO.findByNativeQuery("select distinct county_name from t_usa_county");
	}


}
