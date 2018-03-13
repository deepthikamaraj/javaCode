package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TGeoLoad;
import com.cognizant.opserv.sp.core.entity.TLoadStatus;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TGeoLoadDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tGeoLoadDAO")
public class TGeoLoadDAOImpl implements TGeoLoadDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(TGeoLoadDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private static final String TLOADSTATUS = "tLoadStatus";

	private final Class<TGeoLoad> clazz;

	public Class<TGeoLoad> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TGeoLoadDAOImpl() {
		super();
		this.clazz = TGeoLoad.class;
	}

	private static final String JPAQL = "select tGeoLoadentity from TGeoLoad tGeoLoadentity";

	private static final String JPACOUNTQL = "select count(tGeoLoadentity) from TGeoLoad tGeoLoadentity";

	private static final String[] RESTRICTIONS = { "tGeoLoadentity.dsId = #{tGeoLoadentity.getDsId}",
		"Date(tGeoLoadentity.extDtTm) = '#{tGeoLoadentity.getExtDtTm}'",
		"tGeoLoadentity.createdBy = #{tGeoLoadentity.getCreatedBy}",
		"Date(tGeoLoadentity.createDt) = '#{tGeoLoadentity.getCreateDt}'",
		"tGeoLoadentity.tenantId = #{tGeoLoadentity.getTenantId}",
		"tGeoLoadentity.fileName like '#{tGeoLoadentity.getFileName}%'",
		"tGeoLoadentity.dsName like '#{tGeoLoadentity.getDsName}%'",
		"tGeoLoadentity.dsDesc like '#{tGeoLoadentity.getDsDesc}%'",
		"tGeoLoadentity.tblName like '#{tGeoLoadentity.getTblName}%'",
		"tGeoLoadentity.fileType like '#{tGeoLoadentity.getFileType}%'",
		"Date(tGeoLoadentity.loadDtTm) = '#{tGeoLoadentity.getLoadDtTm}'",
	"tGeoLoadentity.tLoadStatus.statusId = #{tGeoLoadentity.tLoadStatus.getStatusId}" };

	/**
	 * Stores a new TGeoLoad entity object in to the persistent store
	 * 
	 * @param tGeoLoad
	 *            TGeoLoad Entity object to be persisted
	 * @return tGeoLoad Persisted TGeoLoad object
	 */
	public TGeoLoad createTGeoLoad(final TGeoLoad tGeoLoad) {
		LOGGER.info("=========== Create TGeoLoad ===========");
		return genericDAO.store(tGeoLoad);
	}

	/**
	 * Deletes a TGeoLoad entity object from the persistent store
	 * 
	 * @param tGeoLoad
	 *            TGeoLoad Entity object to be deleted
	 */
	public void deleteTGeoLoad(final Integer dsId) {
		LOGGER.info("=========== Delete TGeoLoad ===========");
		final TGeoLoad tGeoLoad = genericDAO.get(clazz, dsId);
		genericDAO.remove(tGeoLoad);
	}

	/**
	 * Updates a TGeoLoad entity object in to the persistent store
	 * 
	 * @param tGeoLoad
	 *            TGeoLoad Entity object to be updated
	 * @return tGeoLoad Persisted TGeoLoad object
	 */
	public TGeoLoad updateTGeoLoad(final TGeoLoad tGeoLoad) {
		LOGGER.info("=========== Update TGeoLoad ===========");
		return genericDAO.update(tGeoLoad);
	}

	/**
	 * Retrieve an TGeoLoad object based on given TGeoLoad dsId.
	 * 
	 * @param tGeoLoadId
	 *            the primary key value of the TGeoLoad Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TGeoLoad findTGeoLoadById(final Integer tGeoLoadId) {
		LOGGER.info("find TGeoLoad instance with dsId: " + tGeoLoadId);
		return genericDAO.get(clazz, tGeoLoadId);
	}

	/**
	 * Retrieve TGeoLoad based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TGeoLoad> list of TGeoLoad if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TGeoLoad> findTGeoLoads(final SearchFilter<TGeoLoad> searchFilter) {
		LOGGER.info("=========== Find TGeoLoads ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TGeoLoad tGeoLoad = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tGeoLoadentity", tGeoLoad);

		if (tGeoLoad.getTLoadStatus() == null) {
			jpaQuery.bind(TLOADSTATUS, new TLoadStatus());
		} else {
			LOGGER.info("tLoadStatus {}", tGeoLoad.getTLoadStatus());

			jpaQuery.bind(TLOADSTATUS, tGeoLoad.getTLoadStatus());
		}
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TGeoLoads.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTGeoLoads(final SearchFilter<TGeoLoad> searchFilter) {
		LOGGER.info("=========== Count TGeoLoad ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TGeoLoad tGeoLoad = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tGeoLoadentity", tGeoLoad);

		if (tGeoLoad.getTLoadStatus() == null) {
			jpaCountQuery.bind(TLOADSTATUS, new TLoadStatus());
		} else {
			LOGGER.info("tLoadStatus {}", tGeoLoad.getTLoadStatus());

			jpaCountQuery.bind(TLOADSTATUS, tGeoLoad.getTLoadStatus());
		}

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}
	
	@Override
	public List<Object[]> getStaticHierarchyLevelLoad(Date beforeDate, Date createDate,Short tenantId) {
		List<Object> paramList = new ArrayList<Object>();		
		paramList.add(tenantId);		
		paramList.add(beforeDate);
		paramList.add(createDate);		
		List<Object[]> tTerrZipLoadList = genericDAO.findEntitiesByNamedQueryMultiCond("GetStaticHierarchyLevelLoad", paramList, 0, -1);
		return tTerrZipLoadList;
	}

}
