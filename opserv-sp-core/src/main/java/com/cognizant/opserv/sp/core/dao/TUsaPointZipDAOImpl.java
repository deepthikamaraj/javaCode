package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TUsaPointZip;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TUsaPointZipDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tUsaPointZipDAO")
public class TUsaPointZipDAOImpl implements TUsaPointZipDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TUsaPointZipDAOImpl.class);

//	@Autowired
//	private GenericDAO genericDAO;

//	@Autowired
//	private GisDAO gisDAO;
//	
//	public GenericDAO getGenericDAO() {
//		return genericDAO;
//	}
//
//	public void setGenericDAO(final GenericDAO genericDAO) {
//		this.genericDAO = genericDAO;
//	}

//	public GisDAO getGisDAO() {
//		return gisDAO;
//	}
//
//	public void setGisDAO(final GisDAO gisDAO) {
//		this.gisDAO = gisDAO;
//	}

	private final Class<TUsaPointZip> clazz;

	public Class<TUsaPointZip> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TUsaPointZipDAOImpl() {
		super();
		this.clazz = TUsaPointZip.class;
	}

	private static final String JPAQL = "select tUsaPointZipentity from TUsaPointZip tUsaPointZipentity";

	private static final String JPACOUNTQL = "select count(tUsaPointZipentity) from TUsaPointZip tUsaPointZipentity";

	private static final String[] RESTRICTIONS = { "tUsaPointZipentity.zipId = #{tUsaPointZipentity.getZipId}",
			"tUsaPointZipentity.prnZipId = #{tUsaPointZipentity.getPrnZipId}",
			"tUsaPointZipentity.zipCd like '#{tUsaPointZipentity.getZipCd}%'" };

	/**
	 * Stores a new TUsaPointZip entity object in to the persistent store
	 * 
	 * @param tUsaPointZip
	 *            TUsaPointZip Entity object to be persisted
	 * @return tUsaPointZip Persisted TUsaPointZip object
	 */
	public TUsaPointZip createTUsaPointZip(final TUsaPointZip tUsaPointZip) {
		LOGGER.info("=========== Create TUsaPointZip ===========");
//		return gisDAO.store(tUsaPointZip);
		return null;
	}

	/**
	 * Deletes a TUsaPointZip entity object from the persistent store
	 * 
	 * @param tUsaPointZip
	 *            TUsaPointZip Entity object to be deleted
	 */
	public void deleteTUsaPointZip(final Integer zipId) {
		LOGGER.info("=========== Delete TUsaPointZip ===========");
//		final TUsaPointZip tUsaPointZip = gisDAO.get(clazz, zipId);
//		gisDAO.remove(tUsaPointZip);
	}

	/**
	 * Updates a TUsaPointZip entity object in to the persistent store
	 * 
	 * @param tUsaPointZip
	 *            TUsaPointZip Entity object to be updated
	 * @return tUsaPointZip Persisted TUsaPointZip object
	 */
	public TUsaPointZip updateTUsaPointZip(final TUsaPointZip tUsaPointZip) {
		LOGGER.info("=========== Update TUsaPointZip ===========");
//		return gisDAO.update(tUsaPointZip);
		return null;
	}

	/**
	 * Retrieve an TUsaPointZip object based on given TUsaPointZip zipId.
	 * 
	 * @param tUsaPointZipId
	 *            the primary key value of the TUsaPointZip Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TUsaPointZip findTUsaPointZipById(final Integer tUsaPointZipId) {
		LOGGER.info("find TUsaPointZip instance with zipId: " + tUsaPointZipId);
//		return gisDAO.get(clazz, tUsaPointZipId);
		return null;
	}

	/**
	 * Retrieve TUsaPointZip based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TUsaPointZip> list of TUsaPointZip if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TUsaPointZip> findTUsaPointZips(final SearchFilter<TUsaPointZip> searchFilter) {
		LOGGER.info("=========== Find TUsaPointZips ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TUsaPointZip tUsaPointZip = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tUsaPointZipentity", tUsaPointZip);
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
//		return gisDAO.findEntities(jpaQuery, index, maxresult);
		return null;
	}

	/**
	 * Fetch count of records for TUsaPointZips.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTUsaPointZips(final SearchFilter<TUsaPointZip> searchFilter) {
		LOGGER.info("=========== Count TUsaPointZip ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TUsaPointZip tUsaPointZip = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tUsaPointZipentity", tUsaPointZip);

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

//		return gisDAO.findEntities(jpaCountQuery, -1, -1);
		return null;
	}

	@Override
	public List<TUsaPointZip> findAllTUsaPointZips() {
//		return gisDAO.findEntitiesByNamedQuery("FindAllTUsaPointZips");
		return null;
	}

	@Override
	public List<TUsaPointZip> findAllTUsaPointZipsByPrnId(List<String> zipPrnIdList) {
		List queryParam = new ArrayList();
		queryParam.add(zipPrnIdList);
//		return gisDAO.findEntitiesByNamedQueryMultiCond("FindAllTUsaPointZipsByPrnId", queryParam, 0, -1);
		return null;
	}
}
