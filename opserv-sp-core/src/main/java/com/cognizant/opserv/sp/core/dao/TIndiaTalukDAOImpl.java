package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TIndiaDistrict;
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
 * Class provides API implementation for TIndiaTalukDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tIndiaTalukDAO")
public class TIndiaTalukDAOImpl implements TIndiaTalukDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TIndiaTalukDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private static final String TINDIADISTRICT = "tIndiaDistrict";

	private final Class<TIndiaTaluk> clazz;

	public Class<TIndiaTaluk> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TIndiaTalukDAOImpl() {
		super();
		this.clazz = TIndiaTaluk.class;
	}

	private static final String JPAQL = "select tIndiaTalukentity from TIndiaTaluk tIndiaTalukentity";

	private static final String JPACOUNTQL = "select count(tIndiaTalukentity) from TIndiaTaluk tIndiaTalukentity";

	private static final String[] RESTRICTIONS = { "tIndiaTalukentity.talukId = #{tIndiaTalukentity.getTalukId}",
			"tIndiaTalukentity.talukName like '#{tIndiaTalukentity.getTalukName}%'",
			"tIndiaTalukentity.tehsil like '#{tIndiaTalukentity.getTehsil}%'",
			"tIndiaTalukentity.shapePolygon = #{tIndiaTalukentity.getShapePolygon}",
			"tIndiaTalukentity.tIndiaDistrict.districtId = #{tIndiaTalukentity.tIndiaDistrict.getDistrictId}" };

	/**
	 * Stores a new TIndiaTaluk entity object in to the persistent store
	 * 
	 * @param tIndiaTaluk
	 *            TIndiaTaluk Entity object to be persisted
	 * @return tIndiaTaluk Persisted TIndiaTaluk object
	 */
	public TIndiaTaluk createTIndiaTaluk(final TIndiaTaluk tIndiaTaluk) {
		LOGGER.info("=========== Create TIndiaTaluk ===========");
		return genericDAO.store(tIndiaTaluk);
	}

	/**
	 * Deletes a TIndiaTaluk entity object from the persistent store
	 * 
	 * @param tIndiaTaluk
	 *            TIndiaTaluk Entity object to be deleted
	 */
	public void deleteTIndiaTaluk(final Integer talukId) {
		LOGGER.info("=========== Delete TIndiaTaluk ===========");
		final TIndiaTaluk tIndiaTaluk = genericDAO.get(clazz, talukId);
		genericDAO.remove(tIndiaTaluk);
	}

	/**
	 * Updates a TIndiaTaluk entity object in to the persistent store
	 * 
	 * @param tIndiaTaluk
	 *            TIndiaTaluk Entity object to be updated
	 * @return tIndiaTaluk Persisted TIndiaTaluk object
	 */
	public TIndiaTaluk updateTIndiaTaluk(final TIndiaTaluk tIndiaTaluk) {
		LOGGER.info("=========== Update TIndiaTaluk ===========");
		return genericDAO.update(tIndiaTaluk);
	}

	/**
	 * Retrieve an TIndiaTaluk object based on given TIndiaTaluk talukId.
	 * 
	 * @param tIndiaTalukId
	 *            the primary key value of the TIndiaTaluk Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TIndiaTaluk findTIndiaTalukById(final Integer tIndiaTalukId) {
		LOGGER.info("find TIndiaTaluk instance with talukId: " + tIndiaTalukId);
		return genericDAO.get(clazz, tIndiaTalukId);
	}

	/**
	 * Retrieve TIndiaTaluk based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TIndiaTaluk> list of TIndiaTaluk if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TIndiaTaluk> findTIndiaTaluks(final SearchFilter<TIndiaTaluk> searchFilter) {
		LOGGER.info("=========== Find TIndiaTaluks ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TIndiaTaluk tIndiaTaluk = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tIndiaTalukentity", tIndiaTaluk);

		if (tIndiaTaluk.getTIndiaDistrict() == null) {
			jpaQuery.bind(TINDIADISTRICT, new TIndiaDistrict());
		} else {
			LOGGER.info("tIndiaDistrict {}"+ tIndiaTaluk.getTIndiaDistrict());

			jpaQuery.bind(TINDIADISTRICT, tIndiaTaluk.getTIndiaDistrict());
		}
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TIndiaTaluks.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTIndiaTaluks(final SearchFilter<TIndiaTaluk> searchFilter) {
		LOGGER.info("=========== Count TIndiaTaluk ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TIndiaTaluk tIndiaTaluk = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tIndiaTalukentity", tIndiaTaluk);

		if (tIndiaTaluk.getTIndiaDistrict() == null) {
			jpaCountQuery.bind(TINDIADISTRICT, new TIndiaDistrict());
		} else {
			LOGGER.info("tIndiaDistrict {}"+ tIndiaTaluk.getTIndiaDistrict());

			jpaCountQuery.bind(TINDIADISTRICT, tIndiaTaluk.getTIndiaDistrict());
		}

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TIndiaTaluk based on given search criteria using JPA named Query.
	 * The search criteria is of TIndiaDistrict type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TIndiaTaluk> list of TIndiaTaluks if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TIndiaTaluk> getTIndiaTaluksByTIndiaDistrict(final SearchFilter<TIndiaDistrict> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TIndiaDistrict tIndiaDistrict = searchFilter.getEntityClass();
		List<Object> tIndiaDistrictList = new ArrayList<Object>();
		tIndiaDistrictList.add(tIndiaDistrict);
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTIndiaTalukByTIndiaDistrict", tIndiaDistrictList, index, maxresult);
	}

	/**
	 * Count TIndiaTaluk based on given search criteria using JPA named Query.
	 * The search criteria is of TIndiaDistrict type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTIndiaTaluksByTIndiaDistrict(final SearchFilter<TIndiaDistrict> searchFilter) {

		final TIndiaDistrict tIndiaDistrict = searchFilter.getEntityClass();
		List<Object> tIndiaDistrictList = new ArrayList<Object>();
		tIndiaDistrictList.add(tIndiaDistrict);
		return genericDAO.findEntitiesByNamedQuery("CountTIndiaTaluksByTIndiaDistrict", tIndiaDistrictList);
	}
	
	public List<String> findIndiaTaluk()
	{
		return genericDAO.findByNativeQuery("select taluk_name from t_india_taluk");
	}

}
