package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TAlgmntGeoHier;
import com.cognizant.opserv.sp.core.entity.TGeoHierList;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TAlgmntGeoHierDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tAlgmntGeoHierDAO")
public class TAlgmntGeoHierDAOImpl implements TAlgmntGeoHierDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(TAlgmntGeoHierDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private static final String TALGMNTGEOHIER = "tAlgmntGeoHier";
	private static final String TGEOHIERLIST = "tGeoHierList";

	private final Class<TAlgmntGeoHier> clazz;

	public Class<TAlgmntGeoHier> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TAlgmntGeoHierDAOImpl() {
		super();
		this.clazz = TAlgmntGeoHier.class;
	}

	private static final String JPAQL = "select tAlgmntGeoHierentity from TAlgmntGeoHier tAlgmntGeoHierentity";

	private static final String JPACOUNTQL = "select count(tAlgmntGeoHierentity) from TAlgmntGeoHier tAlgmntGeoHierentity";

	private static final String[] RESTRICTIONS = {
		"tAlgmntGeoHierentity.geoHierId = #{tAlgmntGeoHierentity.getGeoHierId}",
		"tAlgmntGeoHierentity.hierName like '#{tAlgmntGeoHierentity.getHierName}%'",
		"tAlgmntGeoHierentity.activeFlag = #{tAlgmntGeoHierentity.getActiveFlag}",
		"tAlgmntGeoHierentity.algmntId = #{tAlgmntGeoHierentity.getAlgmntId}",
		"tAlgmntGeoHierentity.bussUnitId = #{tAlgmntGeoHierentity.getBussUnitId}",
		"tAlgmntGeoHierentity.salesTeamId = #{tAlgmntGeoHierentity.getSalesTeamId}",
		"tAlgmntGeoHierentity.createdBy = #{tAlgmntGeoHierentity.getCreatedBy}",
		"Date(tAlgmntGeoHierentity.createDt) = '#{tAlgmntGeoHierentity.getCreateDt}'",
		"tAlgmntGeoHierentity.updatedBy = #{tAlgmntGeoHierentity.getUpdatedBy}",
		"Date(tAlgmntGeoHierentity.updateDt) = '#{tAlgmntGeoHierentity.getUpdateDt}'",
		"tAlgmntGeoHierentity.tenantId = #{tAlgmntGeoHierentity.getTenantId}",
		"Date(tAlgmntGeoHierentity.effStartDt) = '#{tAlgmntGeoHierentity.getEffStartDt}'",
		"Date(tAlgmntGeoHierentity.effEndDt) = '#{tAlgmntGeoHierentity.getEffEndDt}'",
		"tAlgmntGeoHierentity.tAlgmntGeoHier.geoHierId = #{tAlgmntGeoHierentity.tAlgmntGeoHier.getGeoHierId}",
	"tAlgmntGeoHierentity.tGeoHierList.geoHierId = #{tAlgmntGeoHierentity.tGeoHierList.getGeoHierId}" };

	/**
	 * Stores a new TAlgmntGeoHier entity object in to the persistent store
	 * 
	 * @param tAlgmntGeoHier
	 *            TAlgmntGeoHier Entity object to be persisted
	 * @return tAlgmntGeoHier Persisted TAlgmntGeoHier object
	 */
	public TAlgmntGeoHier createTAlgmntGeoHier(final TAlgmntGeoHier tAlgmntGeoHier) {
		LOGGER.info("=========== Create TAlgmntGeoHier ===========");
		return genericDAO.store(tAlgmntGeoHier);
	}

	/**
	 * Deletes a TAlgmntGeoHier entity object from the persistent store
	 * 
	 * @param tAlgmntGeoHier
	 *            TAlgmntGeoHier Entity object to be deleted
	 */
	public void deleteTAlgmntGeoHier(final Long geoHierId) {
		LOGGER.info("=========== Delete TAlgmntGeoHier ===========");
		final TAlgmntGeoHier tAlgmntGeoHier = genericDAO.get(clazz, geoHierId);
		genericDAO.remove(tAlgmntGeoHier);
	}

	/**
	 * Updates a TAlgmntGeoHier entity object in to the persistent store
	 * 
	 * @param tAlgmntGeoHier
	 *            TAlgmntGeoHier Entity object to be updated
	 * @return tAlgmntGeoHier Persisted TAlgmntGeoHier object
	 */
	public TAlgmntGeoHier updateTAlgmntGeoHier(final TAlgmntGeoHier tAlgmntGeoHier) {
		LOGGER.info("=========== Update TAlgmntGeoHier ===========");
		return genericDAO.update(tAlgmntGeoHier);
	}

	/**
	 * Retrieve an TAlgmntGeoHier object based on given TAlgmntGeoHier geoHierId.
	 * 
	 * @param tAlgmntGeoHierId
	 *            the primary key value of the TAlgmntGeoHier Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TAlgmntGeoHier findTAlgmntGeoHierById(final Long tAlgmntGeoHierId) {
		LOGGER.info("find TAlgmntGeoHier instance with geoHierId: " + tAlgmntGeoHierId);
		return genericDAO.get(clazz, tAlgmntGeoHierId);
	}

	/**
	 * Retrieve TAlgmntGeoHier based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TAlgmntGeoHier> list of TAlgmntGeoHier if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TAlgmntGeoHier> findTAlgmntGeoHiers(final SearchFilter<TAlgmntGeoHier> searchFilter) {
		LOGGER.info("=========== Find TAlgmntGeoHiers ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TAlgmntGeoHier tAlgmntGeoHier = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tAlgmntGeoHierentity", tAlgmntGeoHier);

		if (tAlgmntGeoHier.getTAlgmntGeoHier() == null) {
			jpaQuery.bind(TALGMNTGEOHIER, new TAlgmntGeoHier());
		} else {
			LOGGER.info("tAlgmntGeoHier {}", tAlgmntGeoHier.getTAlgmntGeoHier());

			jpaQuery.bind(TALGMNTGEOHIER, tAlgmntGeoHier.getTAlgmntGeoHier());
		}

		if (tAlgmntGeoHier.getTGeoHierList() == null) {
			jpaQuery.bind(TGEOHIERLIST, new TGeoHierList());
		} else {
			LOGGER.info("tGeoHierList {}", tAlgmntGeoHier.getTGeoHierList());

			jpaQuery.bind(TGEOHIERLIST, tAlgmntGeoHier.getTGeoHierList());
		}
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TAlgmntGeoHiers.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTAlgmntGeoHiers(final SearchFilter<TAlgmntGeoHier> searchFilter) {
		LOGGER.info("=========== Count TAlgmntGeoHier ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TAlgmntGeoHier tAlgmntGeoHier = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tAlgmntGeoHierentity", tAlgmntGeoHier);

		if (tAlgmntGeoHier.getTAlgmntGeoHier() == null) {
			jpaCountQuery.bind(TALGMNTGEOHIER, new TAlgmntGeoHier());
		} else {
			LOGGER.info("tAlgmntGeoHier {}", tAlgmntGeoHier.getTAlgmntGeoHier());

			jpaCountQuery.bind(TALGMNTGEOHIER, tAlgmntGeoHier.getTAlgmntGeoHier());
		}

		if (tAlgmntGeoHier.getTGeoHierList() == null) {
			jpaCountQuery.bind(TGEOHIERLIST, new TGeoHierList());
		} else {
			LOGGER.info("tGeoHierList {}", tAlgmntGeoHier.getTGeoHierList());

			jpaCountQuery.bind(TGEOHIERLIST, tAlgmntGeoHier.getTGeoHierList());
		}

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Inactivate alignment levels.
	 *
	 * @param alId the al id
	 * @param buId the bu id
	 * @param stId the st id
	 * @param tenantId the tenant id
	 * @param flag the flag
	 */
	@Override
	public void inactivateAlignmentLevels(Long alId,Long buId,Long stId,Short tenantId,Character flag) {
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(alId);
		paramList.add(buId);
		paramList.add(stId);
		paramList.add(tenantId);
		paramList.add(flag);
		genericDAO.updateEntitiesByNamedQueryMultiCond("InactivateAlignmentLevels", paramList, 0, -1);
	}
	/**
	 * Checks if is geo level mapped to alignments.
	 *
	 * @param geoHierId the geo hier id
	 * @param tenantId the tenant id
	 * @param flag the flag
	 * @return true, if is geo level mapped to alignments
	 */
	@Override
	public boolean isGeoLevelMappedToAlignments(Integer geoHierId,Short tenantId,Character flag) {
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(geoHierId);		
		paramList.add(tenantId);
		paramList.add(flag);	
		List<Object> count =  genericDAO.findEntitiesByNamedQueryMultiCond("IsGeoLevelMappedToAlignments", paramList, 0, -1);
		Long countL = (Long) count.get(0);
		return (countL>0)?true:false;
	}
	/**
	 * Gets the child level by level id.
	 *
	 * @param parentLevelId the parent level id
	 * @param tenantId the tenant id
	 * @param flag the flag
	 * @return the child level by level id
	 */
	@Override
	public List<Object[]> getSelectedAlignmentLevels(Long alId,Long buId,Long stId,Short tenantId,Character flag) {
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(alId);
		paramList.add(buId);
		paramList.add(stId);
		paramList.add(tenantId);
		paramList.add(flag);
		List<Object[]> list = genericDAO.findEntitiesByNamedQueryMultiCond("GetSelectedAlignmentLevels", paramList, 0, -1);
		return list;
	}
	/**
	 * Gets the selected alignment levels.
	 *
	 * @param alId the al id
	 * @param buId the bu id
	 * @param stId the st id
	 * @param tenantId the tenant id
	 * @param flag the flag
	 * @return the selected alignment levels
	 */
	@Override
	public List<Long> getChildLevelByLevelId(Long parentLevelId,Short tenantId,Character flag) {
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(parentLevelId);		
		paramList.add(tenantId);
		paramList.add(flag);
		List<Long> list = genericDAO.findEntitiesByNamedQueryMultiCond("GetChildLevelByLevelId", paramList, 0, -1);
		return list;
	}		
	/**
	 * Gets the all alignment levels.
	 *
	 * @param tenantId the tenant id
	 * @param flag the flag
	 * @return the all alignment levels
	 */
	@Override
	public List<Object[]> getAllAlignmentLevels(Short tenantId,Character flag) {
		List<Object> paramList = new ArrayList<Object>();	
		paramList.add(tenantId);
		paramList.add(flag);
		List<Object[]> list = genericDAO.findEntitiesByNamedQueryMultiCond("GetAllAlignmentLevels", paramList, 0, -1);
		return list;
	}

}
