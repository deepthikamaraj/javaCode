package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TMsgType;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TMsgTypeDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tMsgTypeDAO")
public class TMsgTypeDAOImpl implements TMsgTypeDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TMsgTypeDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private final Class<TMsgType> clazz;

	public Class<TMsgType> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TMsgTypeDAOImpl() {
		super();
		this.clazz = TMsgType.class;
	}

	private static final String JPAQL = "select tMsgTypeentity from TMsgType tMsgTypeentity";

	private static final String JPACOUNTQL = "select count(tMsgTypeentity) from TMsgType tMsgTypeentity";

	private static final String[] RESTRICTIONS = { "tMsgTypeentity.msgTypeId = #{tMsgTypeentity.getMsgTypeId}",
			"tMsgTypeentity.msgTypeDesc like '#{tMsgTypeentity.getMsgTypeDesc}%'",
			"tMsgTypeentity.activeFlag = #{tMsgTypeentity.getActiveFlag}" };

	/**
	 * Stores a new TMsgType entity object in to the persistent store
	 * 
	 * @param tMsgType
	 *            TMsgType Entity object to be persisted
	 * @return tMsgType Persisted TMsgType object
	 */
	public TMsgType createTMsgType(final TMsgType tMsgType) {
		LOGGER.info("=========== Create TMsgType ===========");
		return genericDAO.store(tMsgType);
	}

	/**
	 * Deletes a TMsgType entity object from the persistent store
	 * 
	 * @param tMsgType
	 *            TMsgType Entity object to be deleted
	 */
	public void deleteTMsgType(final Integer msgTypeId) {
		LOGGER.info("=========== Delete TMsgType ===========");
		final TMsgType tMsgType = genericDAO.get(clazz, msgTypeId);
		genericDAO.remove(tMsgType);
	}

	/**
	 * Updates a TMsgType entity object in to the persistent store
	 * 
	 * @param tMsgType
	 *            TMsgType Entity object to be updated
	 * @return tMsgType Persisted TMsgType object
	 */
	public TMsgType updateTMsgType(final TMsgType tMsgType) {
		LOGGER.info("=========== Update TMsgType ===========");
		return genericDAO.update(tMsgType);
	}

	/**
	 * Retrieve an TMsgType object based on given TMsgType msgTypeId.
	 * 
	 * @param tMsgTypeId
	 *            the primary key value of the TMsgType Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TMsgType findTMsgTypeById(final Integer tMsgTypeId) {
		LOGGER.info("find TMsgType instance with msgTypeId: " + tMsgTypeId);
		return genericDAO.get(clazz, tMsgTypeId);
	}

	/**
	 * Retrieve TMsgType based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TMsgType> list of TMsgType if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TMsgType> findTMsgTypes(final SearchFilter<TMsgType> searchFilter) {
		LOGGER.info("=========== Find TMsgTypes ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TMsgType tMsgType = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tMsgTypeentity", tMsgType);
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TMsgTypes.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTMsgTypes(final SearchFilter<TMsgType> searchFilter) {
		LOGGER.info("=========== Count TMsgType ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TMsgType tMsgType = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tMsgTypeentity", tMsgType);

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

}
