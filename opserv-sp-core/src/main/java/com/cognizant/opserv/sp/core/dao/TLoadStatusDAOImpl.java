package com.cognizant.opserv.sp.core.dao;


import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TLoadStatus;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TLoadStatusDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tLoadStatusDAO")
public class TLoadStatusDAOImpl implements TLoadStatusDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(TLoadStatusDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private final Class<TLoadStatus> clazz;

	public Class<TLoadStatus> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TLoadStatusDAOImpl() {
		super();
		this.clazz = TLoadStatus.class;
	}

	private static final String JPAQL = "select tLoadStatusentity from TLoadStatus tLoadStatusentity";

	private static final String JPACOUNTQL = "select count(tLoadStatusentity) from TLoadStatus tLoadStatusentity";

	private static final String[] RESTRICTIONS = { "tLoadStatusentity.statusId = #{tLoadStatusentity.getStatusId}",
			"tLoadStatusentity.statusName like '#{tLoadStatusentity.getStatusName}%'",
			"tLoadStatusentity.createdBy = #{tLoadStatusentity.getCreatedBy}",
			"Date(tLoadStatusentity.createDt) = '#{tLoadStatusentity.getCreateDt}'",
			"tLoadStatusentity.updatedBy = #{tLoadStatusentity.getUpdatedBy}",
			"Date(tLoadStatusentity.updateDt) = '#{tLoadStatusentity.getUpdateDt}'",
			"tLoadStatusentity.tenantId = #{tLoadStatusentity.getTenantId}",
			"tLoadStatusentity.statusDesc like '#{tLoadStatusentity.getStatusDesc}%'",
			"tLoadStatusentity.localeId like '#{tLoadStatusentity.getLocaleId}%'" };

	/**
	 * Stores a new TLoadStatus entity object in to the persistent store
	 * 
	 * @param tLoadStatus
	 *            TLoadStatus Entity object to be persisted
	 * @return tLoadStatus Persisted TLoadStatus object
	 */
	public TLoadStatus createTLoadStatus(final TLoadStatus tLoadStatus) {
		LOGGER.info("=========== Create TLoadStatus ===========");
		return genericDAO.store(tLoadStatus);
	}

	/**
	 * Deletes a TLoadStatus entity object from the persistent store
	 * 
	 * @param tLoadStatus
	 *            TLoadStatus Entity object to be deleted
	 */
	public void deleteTLoadStatus(final Integer statusId) {
		LOGGER.info("=========== Delete TLoadStatus ===========");
		final TLoadStatus tLoadStatus = genericDAO.get(clazz, statusId);
		genericDAO.remove(tLoadStatus);
	}

	/**
	 * Updates a TLoadStatus entity object in to the persistent store
	 * 
	 * @param tLoadStatus
	 *            TLoadStatus Entity object to be updated
	 * @return tLoadStatus Persisted TLoadStatus object
	 */
	public TLoadStatus updateTLoadStatus(final TLoadStatus tLoadStatus) {
		LOGGER.info("=========== Update TLoadStatus ===========");
		return genericDAO.update(tLoadStatus);
	}

	/**
	 * Retrieve an TLoadStatus object based on given TLoadStatus statusId.
	 * 
	 * @param tLoadStatusId
	 *            the primary key value of the TLoadStatus Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TLoadStatus findTLoadStatusById(final Integer tLoadStatusId) {
		LOGGER.info("find TLoadStatus instance with statusId: " + tLoadStatusId);
		return genericDAO.get(clazz, tLoadStatusId);
	}

	/**
	 * Retrieve TLoadStatus based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TLoadStatus> list of TLoadStatus if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TLoadStatus> findTLoadStatuss(final SearchFilter<TLoadStatus> searchFilter) {
		LOGGER.info("=========== Find TLoadStatuss ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TLoadStatus tLoadStatus = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tLoadStatusentity", tLoadStatus);
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TLoadStatuss.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTLoadStatuss(final SearchFilter<TLoadStatus> searchFilter) {
		LOGGER.info("=========== Count TLoadStatus ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TLoadStatus tLoadStatus = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tLoadStatusentity", tLoadStatus);

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

}
