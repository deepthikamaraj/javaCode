package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TCountry;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TCountryDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tCountryDAO")
public class TCountryDAOImpl implements TCountryDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TCountryDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private final Class<TCountry> clazz;

	public Class<TCountry> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TCountryDAOImpl() {
		super();
		this.clazz = TCountry.class;
	}

	private static final String JPAQL = "select tCountryentity from TCountry tCountryentity";

	private static final String JPACOUNTQL = "select count(tCountryentity) from TCountry tCountryentity";

	private static final String[] RESTRICTIONS = { "tCountryentity.countryId = #{tCountryentity.getCountryId}",
			"tCountryentity.countryName like '#{tCountryentity.getCountryName}%'",
			"tCountryentity.postalCdName like '#{tCountryentity.getPostalCdName}%'" };

	/**
	 * Stores a new TCountry entity object in to the persistent store
	 * 
	 * @param tCountry
	 *            TCountry Entity object to be persisted
	 * @return tCountry Persisted TCountry object
	 */
	public TCountry createTCountry(final TCountry tCountry) {
		LOGGER.info("=========== Create TCountry ===========");
		return genericDAO.store(tCountry);
	}

	/**
	 * Deletes a TCountry entity object from the persistent store
	 * 
	 * @param tCountry
	 *            TCountry Entity object to be deleted
	 */
	public void deleteTCountry(final Integer countryId) {
		LOGGER.info("=========== Delete TCountry ===========");
		final TCountry tCountry = genericDAO.get(clazz, countryId);
		genericDAO.remove(tCountry);
	}

	/**
	 * Updates a TCountry entity object in to the persistent store
	 * 
	 * @param tCountry
	 *            TCountry Entity object to be updated
	 * @return tCountry Persisted TCountry object
	 */
	public TCountry updateTCountry(final TCountry tCountry) {
		LOGGER.info("=========== Update TCountry ===========");
		return genericDAO.update(tCountry);
	}

	/**
	 * Retrieve an TCountry object based on given TCountry countryId.
	 * 
	 * @param tCountryId
	 *            the primary key value of the TCountry Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TCountry findTCountryById(final Integer tCountryId) {
		LOGGER.info("find TCountry instance with countryId: " + tCountryId);
		return genericDAO.get(clazz, tCountryId);
	}

	/**
	 * Retrieve TCountry based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCountry> list of TCountry if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TCountry> findTCountrys(final SearchFilter<TCountry> searchFilter) {
		LOGGER.info("=========== Find TCountrys ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TCountry tCountry = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tCountryentity", tCountry);
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		jpaQuery.setCacheable(true);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TCountrys.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTCountrys(final SearchFilter<TCountry> searchFilter) {
		LOGGER.info("=========== Count TCountry ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TCountry tCountry = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tCountryentity", tCountry);

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}
	/**
	 * Find all country names.
	 *
	 * @return the list
	 */
	@Override
	public List<Object[]> findAllCountryNames(){
		return genericDAO.findEntitiesByNamedQuery("FindAllCountryNames");
	}
	/**
	 * Gets the all country names by order.
	 *
	 * @return the all country names by order
	 */
	@Override
	public List<String> getAllCountryNamesByOrder(){
		return genericDAO.findEntitiesByNamedQuery("GetAllCountryNamesByOrder");
	}
	
}
