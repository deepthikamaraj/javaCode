package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TAckStatus;
import com.cognizant.opserv.sp.core.entity.TAckStatusId;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TAckStatusDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tAckStatusDAO")
public class TAckStatusDAOImpl implements TAckStatusDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TAckStatusDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private final Class<TAckStatus> clazz;

	public Class<TAckStatus> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TAckStatusDAOImpl() {
		super();
		this.clazz = TAckStatus.class;
	}

	private static final String JPAQL = "select tAckStatusentity from TAckStatus tAckStatusentity";

	private static final String JPACOUNTQL = "select count(tAckStatusentity) from TAckStatus tAckStatusentity";

	private static final String[] RESTRICTIONS = { 
		    "tAckStatusentity.tAckStatusId.ackStatusId = #{tAckStatusentity.tAckStatusId.getAckStatusId}",
		    "tAckStatusentity.tAckStatusId.localeId = #{tAckStatusentity.tAckStatusId.getLocaleId}",
			"tAckStatusentity.ackStatusName like '#{tAckStatusentity.getAckStatusName}%'",
			"tAckStatusentity.ackStatusDesc like '#{tAckStatusentity.getAckStatusDesc}%'",
			"tAckStatusentity.activeFlag = #{tAckStatusentity.getActiveFlag}",
			"tAckStatusentity.createdBy = #{tAckStatusentity.getCreatedBy}",
			"Date(tAckStatusentity.createDt) = '#{tAckStatusentity.getCreateDt}'",
			"tAckStatusentity.updatedBy = #{tAckStatusentity.getUpdatedBy}",
			"Date(tAckStatusentity.updateDate) = '#{tAckStatusentity.getUpdateDate}'",
			"tAckStatusentity.tenantId = #{tAckStatusentity.getTenantId}" };

	/**
	 * Stores a new TAckStatus entity object in to the persistent store
	 * 
	 * @param tAckStatus
	 *            TAckStatus Entity object to be persisted
	 * @return tAckStatus Persisted TAckStatus object
	 */
	public TAckStatus createTAckStatus(final TAckStatus tAckStatus) {
		LOGGER.info("=========== Create TAckStatus ===========");
		return genericDAO.store(tAckStatus);
	}

	/**
	 * Deletes a TAckStatus entity object from the persistent store
	 * 
	 * @param tAckStatus
	 *            TAckStatus Entity object to be deleted
	 */
	public void deleteTAckStatus(final Integer ackStatusId) {
		LOGGER.info("=========== Delete TAckStatus ===========");
		final TAckStatus tAckStatus = genericDAO.get(clazz, ackStatusId);
		genericDAO.remove(tAckStatus);
	}

	/**
	 * Updates a TAckStatus entity object in to the persistent store
	 * 
	 * @param tAckStatus
	 *            TAckStatus Entity object to be updated
	 * @return tAckStatus Persisted TAckStatus object
	 */
	public TAckStatus updateTAckStatus(final TAckStatus tAckStatus) {
		LOGGER.info("=========== Update TAckStatus ===========");
		return genericDAO.update(tAckStatus);
	}

	/**
	 * Retrieve an TAckStatus object based on given TAckStatus ackStatusId.
	 * 
	 * @param tAckStatusId
	 *            the primary key value of the TAckStatus Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TAckStatus findTAckStatusById(final TAckStatusId tAckStatusId) {
		LOGGER.info("find TAckStatus instance with ackStatusId: " + tAckStatusId);
		return genericDAO.get(clazz, tAckStatusId);
	}

	/**
	 * Retrieve TAckStatus based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TAckStatus> list of TAckStatus if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TAckStatus> findTAckStatuss(final SearchFilter<TAckStatus> searchFilter) {
		LOGGER.info("=========== Find TAckStatuss ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TAckStatus tAckStatus = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tAckStatusentity", tAckStatus);
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		jpaQuery.setCacheable(true);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TAckStatuss.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTAckStatuss(final SearchFilter<TAckStatus> searchFilter) {
		LOGGER.info("=========== Count TAckStatus ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TAckStatus tAckStatus = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tAckStatusentity", tAckStatus);

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}
	
	
	public List<Object[]> findTAckStatusname(Integer ackStatusId,String localeId){
		List<Object> obj = new ArrayList<Object>();
		obj.add(ackStatusId);
		obj.add(localeId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("findTAckStatusname", obj, 0, -1);
	}

	@Override
	public List<TAckStatus> getAllAckStatus(){		
		return genericDAO.findEntitiesByNamedQuery("FindAllTAckStatuss");
	}

	@Override
	public List<Object[]> findAllActiveTAckStatus(String localeId,
			Short tenantId) {
		List<Object> params = new ArrayList<Object>();
		params.add(localeId);
		params.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindAllActiveTAckStatus", params, 0, -1);
	}

}
