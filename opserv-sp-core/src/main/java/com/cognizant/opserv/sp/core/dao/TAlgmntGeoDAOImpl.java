package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TAlgmntGeo;
import com.cognizant.opserv.sp.core.entity.TAlgmntGeoHier;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TAlgmntGeoDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tAlgmntGeoDAO")
public class TAlgmntGeoDAOImpl implements TAlgmntGeoDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(TAlgmntGeoDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private static final String TALGMNTGEO = "tAlgmntGeo";
	private static final String TALGMNTGEOHIER = "tAlgmntGeoHier";

	private final Class<TAlgmntGeo> clazz;

	public Class<TAlgmntGeo> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TAlgmntGeoDAOImpl() {
		super();
		this.clazz = TAlgmntGeo.class;
	}

	private static final String JPAQL = "select tAlgmntGeoentity from TAlgmntGeo tAlgmntGeoentity";

	private static final String JPACOUNTQL = "select count(tAlgmntGeoentity) from TAlgmntGeo tAlgmntGeoentity";

	private static final String[] RESTRICTIONS = { "tAlgmntGeoentity.geoPosId = #{tAlgmntGeoentity.getGeoPosId}",
		"tAlgmntGeoentity.algmntId = #{tAlgmntGeoentity.getAlgmntId}",
		"tAlgmntGeoentity.bussUnitId = #{tAlgmntGeoentity.getBussUnitId}",
		"tAlgmntGeoentity.salesTeamId = #{tAlgmntGeoentity.getSalesTeamId}",
		"tAlgmntGeoentity.posName like '#{tAlgmntGeoentity.getPosName}%'",
		"tAlgmntGeoentity.posCd like '#{tAlgmntGeoentity.getPosCd}%'",
		"tAlgmntGeoentity.activeFlag = #{tAlgmntGeoentity.getActiveFlag}",
		"tAlgmntGeoentity.createdBy = #{tAlgmntGeoentity.getCreatedBy}",
		"Date(tAlgmntGeoentity.createDt) = '#{tAlgmntGeoentity.getCreateDt}'",
		"tAlgmntGeoentity.updatedBy = #{tAlgmntGeoentity.getUpdatedBy}",
		"Date(tAlgmntGeoentity.updateDt) = '#{tAlgmntGeoentity.getUpdateDt}'",
		"tAlgmntGeoentity.tenantId = #{tAlgmntGeoentity.getTenantId}",
		"Date(tAlgmntGeoentity.effStartDt) = '#{tAlgmntGeoentity.getEffStartDt}'",
		"Date(tAlgmntGeoentity.effEndDt) = '#{tAlgmntGeoentity.getEffEndDt}'",
		"tAlgmntGeoentity.tAlgmntGeo.geoPosId = #{tAlgmntGeoentity.tAlgmntGeo.getGeoPosId}",
	"tAlgmntGeoentity.tAlgmntGeoHier.geoHierId = #{tAlgmntGeoentity.tAlgmntGeoHier.getGeoHierId}" };

	/**
	 * Stores a new TAlgmntGeo entity object in to the persistent store
	 * 
	 * @param tAlgmntGeo
	 *            TAlgmntGeo Entity object to be persisted
	 * @return tAlgmntGeo Persisted TAlgmntGeo object
	 */
	public TAlgmntGeo createTAlgmntGeo(final TAlgmntGeo tAlgmntGeo) {
		LOGGER.info("=========== Create TAlgmntGeo ===========");
		return genericDAO.store(tAlgmntGeo);
	}

	/**
	 * Deletes a TAlgmntGeo entity object from the persistent store
	 * 
	 * @param tAlgmntGeo
	 *            TAlgmntGeo Entity object to be deleted
	 */
	public void deleteTAlgmntGeo(final Long geoPosId) {
		LOGGER.info("=========== Delete TAlgmntGeo ===========");
		final TAlgmntGeo tAlgmntGeo = genericDAO.get(clazz, geoPosId);
		genericDAO.remove(tAlgmntGeo);
	}

	/**
	 * Updates a TAlgmntGeo entity object in to the persistent store
	 * 
	 * @param tAlgmntGeo
	 *            TAlgmntGeo Entity object to be updated
	 * @return tAlgmntGeo Persisted TAlgmntGeo object
	 */
	public TAlgmntGeo updateTAlgmntGeo(final TAlgmntGeo tAlgmntGeo) {
		LOGGER.info("=========== Update TAlgmntGeo ===========");
		return genericDAO.update(tAlgmntGeo);
	}

	/**
	 * Retrieve an TAlgmntGeo object based on given TAlgmntGeo geoPosId.
	 * 
	 * @param tAlgmntGeoId
	 *            the primary key value of the TAlgmntGeo Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TAlgmntGeo findTAlgmntGeoById(final Long tAlgmntGeoId) {
		LOGGER.info("find TAlgmntGeo instance with geoPosId: " + tAlgmntGeoId);
		return genericDAO.get(clazz, tAlgmntGeoId);
	}

	/**
	 * Retrieve TAlgmntGeo based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TAlgmntGeo> list of TAlgmntGeo if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TAlgmntGeo> findTAlgmntGeos(final SearchFilter<TAlgmntGeo> searchFilter) {
		LOGGER.info("=========== Find TAlgmntGeos ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TAlgmntGeo tAlgmntGeo = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tAlgmntGeoentity", tAlgmntGeo);

		if (tAlgmntGeo.getTAlgmntGeo() == null) {
			jpaQuery.bind(TALGMNTGEO, new TAlgmntGeo());
		} else {
			LOGGER.info("tAlgmntGeo {}", tAlgmntGeo.getTAlgmntGeo());

			jpaQuery.bind(TALGMNTGEO, tAlgmntGeo.getTAlgmntGeo());
		}

		if (tAlgmntGeo.getTAlgmntGeoHier() == null) {
			jpaQuery.bind(TALGMNTGEOHIER, new TAlgmntGeoHier());
		} else {
			LOGGER.info("tAlgmntGeoHier {}", tAlgmntGeo.getTAlgmntGeoHier());

			jpaQuery.bind(TALGMNTGEOHIER, tAlgmntGeo.getTAlgmntGeoHier());
		}
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TAlgmntGeos.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTAlgmntGeos(final SearchFilter<TAlgmntGeo> searchFilter) {
		LOGGER.info("=========== Count TAlgmntGeo ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TAlgmntGeo tAlgmntGeo = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tAlgmntGeoentity", tAlgmntGeo);

		if (tAlgmntGeo.getTAlgmntGeo() == null) {
			jpaCountQuery.bind(TALGMNTGEO, new TAlgmntGeo());
		} else {
			LOGGER.info("tAlgmntGeo {}", tAlgmntGeo.getTAlgmntGeo());

			jpaCountQuery.bind(TALGMNTGEO, tAlgmntGeo.getTAlgmntGeo());
		}

		if (tAlgmntGeo.getTAlgmntGeoHier() == null) {
			jpaCountQuery.bind(TALGMNTGEOHIER, new TAlgmntGeoHier());
		} else {
			LOGGER.info("tAlgmntGeoHier {}", tAlgmntGeo.getTAlgmntGeoHier());

			jpaCountQuery.bind(TALGMNTGEOHIER, tAlgmntGeo.getTAlgmntGeoHier());
		}

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}
	/**
	 * Gets the alignment geo hier ids by al bu st.
	 *
	 * @param alId the al id
	 * @param buId the bu id
	 * @param stId the st id
	 * @param tenantId the tenant id
	 * @param flag the flag
	 * @return the alignment geo hier ids by al bu st
	 */
	@Override
	public List<Long> getAlignmentGeoHierIdsByAlBuSt(Long alId,Long buId,Long stId,Short tenantId,Character flag) {
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(alId);
		paramList.add(buId);
		paramList.add(stId);
		paramList.add(tenantId);
		paramList.add(flag);
		List<Long> list = genericDAO.findEntitiesByNamedQueryMultiCond("GetAlignmentGeoHierIdsByAlBuSt", paramList, 0, -1);
		return list;
	}
	/**
	 * Checks if is alignment geo hier ids are mapped.
	 *
	 * @param levelIds the level ids
	 * @param tenantId the tenant id
	 * @param flag the flag
	 * @return true, if is alignment geo hier ids are mapped
	 */
	@Override
	public boolean isAlignmentGeoHierIdsAreMapped(List<Long> levelIds,Short tenantId,Character flag) {
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(levelIds);
		paramList.add(tenantId);
		paramList.add(flag);		
		List<Object> count =  genericDAO.findEntitiesByNamedQueryMultiCond("IsAlignmentGeoHierIdsAreMapped", paramList, 0, -1);
		Long countL = (Long) count.get(0);
		return (countL>0)?true:false;
	}
	/**
	 * Gets the all alignment geo hiers.
	 *
	 * @param tenantId the tenant id
	 * @param flag the flag
	 * @return the all alignment geo hiers
	 */
	@Override
	public List<Object[]> getAllAlignmentGeoHiers(Short tenantId,Character flag) {
		List<Object> paramList = new ArrayList<Object>();	
		paramList.add(tenantId);
		paramList.add(flag);
		List<Object[]> list = genericDAO.findEntitiesByNamedQueryMultiCond("GetAllAlignmentGeoHiers", paramList, 0, -1);
		return list;
	}

}
