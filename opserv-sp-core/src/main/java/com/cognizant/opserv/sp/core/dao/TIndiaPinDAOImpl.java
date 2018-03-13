package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TIndiaPin;
import com.cognizant.opserv.sp.core.entity.TIndiaTaluk;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TIndiaPinDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tIndiaPinDAO")
public class TIndiaPinDAOImpl implements TIndiaPinDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TIndiaPinDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private static final String TINDIATALUK = "tIndiaTaluk";

	private final Class<TIndiaPin> clazz;

	public Class<TIndiaPin> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TIndiaPinDAOImpl() {
		super();
		this.clazz = TIndiaPin.class;
	}

	private static final String JPAQL = "select tIndiaPinentity from TIndiaPin tIndiaPinentity";

	private static final String JPACOUNTQL = "select count(tIndiaPinentity) from TIndiaPin tIndiaPinentity";

	private static final String[] RESTRICTIONS = { "tIndiaPinentity.pinCd like '#{tIndiaPinentity.getPinCd}%'",
			"tIndiaPinentity.shapePolygon = #{tIndiaPinentity.getShapePolygon}",
			"tIndiaPinentity.dataPoint = #{tIndiaPinentity.getDataPoint}",
			"tIndiaPinentity.tIndiaTaluk.talukId = #{tIndiaPinentity.tIndiaTaluk.getTalukId}" };

	/**
	 * Stores a new TIndiaPin entity object in to the persistent store
	 * 
	 * @param tIndiaPin
	 *            TIndiaPin Entity object to be persisted
	 * @return tIndiaPin Persisted TIndiaPin object
	 */
	public TIndiaPin createTIndiaPin(final TIndiaPin tIndiaPin) {
		LOGGER.info("=========== Create TIndiaPin ===========");
		return genericDAO.store(tIndiaPin);
	}

	/**
	 * Deletes a TIndiaPin entity object from the persistent store
	 * 
	 * @param tIndiaPin
	 *            TIndiaPin Entity object to be deleted
	 */
	public void deleteTIndiaPin(final String pinCd) {
		LOGGER.info("=========== Delete TIndiaPin ===========");
		final TIndiaPin tIndiaPin = genericDAO.get(clazz, pinCd);
		genericDAO.remove(tIndiaPin);
	}

	/**
	 * Updates a TIndiaPin entity object in to the persistent store
	 * 
	 * @param tIndiaPin
	 *            TIndiaPin Entity object to be updated
	 * @return tIndiaPin Persisted TIndiaPin object
	 */
	public TIndiaPin updateTIndiaPin(final TIndiaPin tIndiaPin) {
		LOGGER.info("=========== Update TIndiaPin ===========");
		return genericDAO.update(tIndiaPin);
	}

	/**
	 * Retrieve an TIndiaPin object based on given TIndiaPin pinCd.
	 * 
	 * @param tIndiaPinId
	 *            the primary key value of the TIndiaPin Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TIndiaPin findTIndiaPinById(final String tIndiaPinId) {
		LOGGER.info("find TIndiaPin instance with pinCd: " + tIndiaPinId);
		return genericDAO.get(clazz, tIndiaPinId);
	}

	/**
	 * Retrieve TIndiaPin based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TIndiaPin> list of TIndiaPin if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TIndiaPin> findTIndiaPins(final SearchFilter<TIndiaPin> searchFilter) {
		LOGGER.info("=========== Find TIndiaPins ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TIndiaPin tIndiaPin = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tIndiaPinentity", tIndiaPin);

		if (tIndiaPin.getTIndiaTaluk() == null) {
			jpaQuery.bind(TINDIATALUK, new TIndiaTaluk());
		} else {
			LOGGER.info("tIndiaTaluk {}"+ tIndiaPin.getTIndiaTaluk());

			jpaQuery.bind(TINDIATALUK, tIndiaPin.getTIndiaTaluk());
		}
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TIndiaPins.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTIndiaPins(final SearchFilter<TIndiaPin> searchFilter) {
		LOGGER.info("=========== Count TIndiaPin ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TIndiaPin tIndiaPin = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tIndiaPinentity", tIndiaPin);

		if (tIndiaPin.getTIndiaTaluk() == null) {
			jpaCountQuery.bind(TINDIATALUK, new TIndiaTaluk());
		} else {
			LOGGER.info("tIndiaTaluk {}"+ tIndiaPin.getTIndiaTaluk());

			jpaCountQuery.bind(TINDIATALUK, tIndiaPin.getTIndiaTaluk());
		}

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TIndiaPin based on given search criteria using JPA named Query.
	 * The search criteria is of TIndiaTaluk type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TIndiaPin> list of TIndiaPins if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TIndiaPin> getTIndiaPinsByTIndiaTaluk(final SearchFilter<TIndiaTaluk> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TIndiaTaluk tIndiaTaluk = searchFilter.getEntityClass();
		List<Object> tIndiaTalukList = new ArrayList<Object>();
		tIndiaTalukList.add(tIndiaTaluk);
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTIndiaPinByTIndiaTaluk", tIndiaTalukList, index, maxresult);
	}

	/**
	 * Count TIndiaPin based on given search criteria using JPA named Query.
	 * The search criteria is of TIndiaTaluk type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTIndiaPinsByTIndiaTaluk(final SearchFilter<TIndiaTaluk> searchFilter) {

		final TIndiaTaluk tIndiaTaluk = searchFilter.getEntityClass();
		List<Object> tIndiaTalukList = new ArrayList<Object>();
		tIndiaTalukList.add(tIndiaTaluk);
		return genericDAO.findEntitiesByNamedQuery("CountTIndiaPinsByTIndiaTaluk", tIndiaTalukList);
	}

	@Override
	public List<String> getTIndiaZips(String query) {
		return genericDAO.findByNativeQuery(query);
	}

}
