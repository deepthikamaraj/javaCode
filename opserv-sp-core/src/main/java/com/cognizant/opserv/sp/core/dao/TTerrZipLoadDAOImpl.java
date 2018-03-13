package com.cognizant.opserv.sp.core.dao;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TLoadStatus;
import com.cognizant.opserv.sp.core.entity.TTerrZipLoad;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TTerrZipLoadDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tTerrZipLoadDAO")
public class TTerrZipLoadDAOImpl implements TTerrZipLoadDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(TTerrZipLoadDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private static final String TLOADSTATUS = "tLoadStatus";

	private final Class<TTerrZipLoad> clazz;

	public Class<TTerrZipLoad> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TTerrZipLoadDAOImpl() {
		super();
		this.clazz = TTerrZipLoad.class;
	}

	private static final String JPAQL = "select tTerrZipLoadentity from TTerrZipLoad tTerrZipLoadentity";

	private static final String JPACOUNTQL = "select count(tTerrZipLoadentity) from TTerrZipLoad tTerrZipLoadentity";

	private static final String[] RESTRICTIONS = { "tTerrZipLoadentity.dsId = #{tTerrZipLoadentity.getDsId}",
			"Date(tTerrZipLoadentity.extDtTm) = '#{tTerrZipLoadentity.getExtDtTm}'",
			"tTerrZipLoadentity.createdBy = #{tTerrZipLoadentity.getCreatedBy}",
			"Date(tTerrZipLoadentity.createDt) = '#{tTerrZipLoadentity.getCreateDt}'",
			"tTerrZipLoadentity.tenantId = #{tTerrZipLoadentity.getTenantId}",
			"tTerrZipLoadentity.fileName like '#{tTerrZipLoadentity.getFileName}%'",
			"tTerrZipLoadentity.dsName like '#{tTerrZipLoadentity.getDsName}%'",
			"tTerrZipLoadentity.dsDesc like '#{tTerrZipLoadentity.getDsDesc}%'",
			"tTerrZipLoadentity.tblName like '#{tTerrZipLoadentity.getTblName}%'",
			"tTerrZipLoadentity.fileType like '#{tTerrZipLoadentity.getFileType}%'",
			"Date(tTerrZipLoadentity.loadDtTm) = '#{tTerrZipLoadentity.getLoadDtTm}'",
			"tTerrZipLoadentity.tLoadStatus.statusId = #{tTerrZipLoadentity.tLoadStatus.getStatusId}" };

	/**
	 * Stores a new TTerrZipLoad entity object in to the persistent store
	 * 
	 * @param tTerrZipLoad
	 *            TTerrZipLoad Entity object to be persisted
	 * @return tTerrZipLoad Persisted TTerrZipLoad object
	 */
	public TTerrZipLoad createTTerrZipLoad(final TTerrZipLoad tTerrZipLoad) {
		LOGGER.info("=========== Create TTerrZipLoad ===========");
		return genericDAO.store(tTerrZipLoad);
	}

	/**
	 * Deletes a TTerrZipLoad entity object from the persistent store
	 * 
	 * @param tTerrZipLoad
	 *            TTerrZipLoad Entity object to be deleted
	 */
	public void deleteTTerrZipLoad(final Integer dsId) {
		LOGGER.info("=========== Delete TTerrZipLoad ===========");
		final TTerrZipLoad tTerrZipLoad = genericDAO.get(clazz, dsId);
		genericDAO.remove(tTerrZipLoad);
	}

	/**
	 * Updates a TTerrZipLoad entity object in to the persistent store
	 * 
	 * @param tTerrZipLoad
	 *            TTerrZipLoad Entity object to be updated
	 * @return tTerrZipLoad Persisted TTerrZipLoad object
	 */
	public TTerrZipLoad updateTTerrZipLoad(final TTerrZipLoad tTerrZipLoad) {
		LOGGER.info("=========== Update TTerrZipLoad ===========");
		return genericDAO.update(tTerrZipLoad);
	}

	/**
	 * Retrieve an TTerrZipLoad object based on given TTerrZipLoad dsId.
	 * 
	 * @param tTerrZipLoadId
	 *            the primary key value of the TTerrZipLoad Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TTerrZipLoad findTTerrZipLoadById(final Integer tTerrZipLoadId) {
		LOGGER.info("find TTerrZipLoad instance with dsId: " + tTerrZipLoadId);
		return genericDAO.get(clazz, tTerrZipLoadId);
	}

	/**
	 * Retrieve TTerrZipLoad based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TTerrZipLoad> list of TTerrZipLoad if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TTerrZipLoad> findTTerrZipLoads(final SearchFilter<TTerrZipLoad> searchFilter) {
		LOGGER.info("=========== Find TTerrZipLoads ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TTerrZipLoad tTerrZipLoad = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tTerrZipLoadentity", tTerrZipLoad);

		if (tTerrZipLoad.getTLoadStatus() == null) {
			jpaQuery.bind(TLOADSTATUS, new TLoadStatus());
		} else {
			LOGGER.info("tLoadStatus {}", tTerrZipLoad.getTLoadStatus());

			jpaQuery.bind(TLOADSTATUS, tTerrZipLoad.getTLoadStatus());
		}
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TTerrZipLoads.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTTerrZipLoads(final SearchFilter<TTerrZipLoad> searchFilter) {
		LOGGER.info("=========== Count TTerrZipLoad ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TTerrZipLoad tTerrZipLoad = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tTerrZipLoadentity", tTerrZipLoad);

		if (tTerrZipLoad.getTLoadStatus() == null) {
			jpaCountQuery.bind(TLOADSTATUS, new TLoadStatus());
		} else {
			LOGGER.info("tLoadStatus {}", tTerrZipLoad.getTLoadStatus());

			jpaCountQuery.bind(TLOADSTATUS, tTerrZipLoad.getTLoadStatus());
		}

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TTerrZipLoad based on given search criteria using JPA named Query.
	 * The search criteria is of TLoadStatus type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TTerrZipLoad> list of TTerrZipLoads if it exists against given
	 *         criteria. Returns null if not found
	 */
	/*public List<TTerrZipLoad> getTTerrZipLoadsByTLoadStatus(final SearchFilter<TLoadStatus> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TLoadStatus tLoadStatus = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQuery("FindTTerrZipLoadByTLoadStatus", tLoadStatus, index, maxresult);
	}*/

	/**
	 * Count TTerrZipLoad based on given search criteria using JPA named Query.
	 * The search criteria is of TLoadStatus type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	/*public Object countTTerrZipLoadsByTLoadStatus(final SearchFilter<TLoadStatus> searchFilter) {

		final TLoadStatus tLoadStatus = searchFilter.getEntityClass();
		return genericDAO.findEntitiesByNamedQuery("CountTTerrZipLoadsByTLoadStatus", tLoadStatus);
	}*/
	
	public List<TTerrZipLoad> findTTerrZipLoadCreatedAftrDate(final SearchFilter<TTerrZipLoad> searchFilter, Date beforeDate, Date createDate) {
		final TTerrZipLoad tTerrZipLoad = searchFilter.getEntityClass();
		List<Object> paramList = new ArrayList<Object>();
		
		paramList.add(tTerrZipLoad.getTenantId());		
		paramList.add(beforeDate);
		paramList.add(createDate);
		
		List<TTerrZipLoad> tTerrZipLoadList = genericDAO.findEntitiesByNamedQueryMultiCond("TTerrZipLoadAftrCreateDt", paramList, 0, -1);
		return tTerrZipLoadList;
	}

}
