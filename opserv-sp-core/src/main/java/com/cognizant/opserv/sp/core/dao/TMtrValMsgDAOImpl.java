package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TMtr;
import com.cognizant.opserv.sp.core.entity.TMtrValMsg;
import com.cognizant.opserv.sp.core.entity.TMtrValMsgId;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TMtrValMsgDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tMtrValMsgDAO")
public class TMtrValMsgDAOImpl implements TMtrValMsgDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TMtrValMsgDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private static final String TMTR = "tMtr";

	private final Class<TMtrValMsg> clazz;

	public Class<TMtrValMsg> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TMtrValMsgDAOImpl() {
		super();
		this.clazz = TMtrValMsg.class;
	}

	private static final String JPAQL = "select tMtrValMsgentity from TMtrValMsg tMtrValMsgentity";

	private static final String JPACOUNTQL = "select count(*) from TMtrValMsg tMtrValMsgentity";

	private static final String[] RESTRICTIONS = {
			"tMtrValMsgentity.tMtrValMsgId.localeId like '#{tMtrValMsgentity.tMtrValMsgId.getLocaleId}%'",
			"tMtrValMsgentity.tMtrValMsgId.mtrId = #{tMtrValMsgentity.tMtrValMsgId.getMtrId}",
			"tMtrValMsgentity.valMsg like '#{tMtrValMsgentity.getValMsg}%'",
			"tMtrValMsgentity.createdBy = #{tMtrValMsgentity.getCreatedBy}",
			"Date(tMtrValMsgentity.createDt) = '#{tMtrValMsgentity.getCreateDt}'",
			"tMtrValMsgentity.updatedBy = #{tMtrValMsgentity.getUpdatedBy}",
			"Date(tMtrValMsgentity.updateDt) = '#{tMtrValMsgentity.getUpdateDt}'",
			"tMtrValMsgentity.tenantId = #{tMtrValMsgentity.getTenantId}",
			"tMtrValMsgentity.tMtr.mtrId = #{tMtrValMsgentity.tMtr.getMtrId}" };

	/**
	 * Stores a new TMtrValMsg entity object in to the persistent store
	 * 
	 * @param tMtrValMsg
	 *            TMtrValMsg Entity object to be persisted
	 * @return tMtrValMsg Persisted TMtrValMsg object
	 */
	public TMtrValMsg createTMtrValMsg(final TMtrValMsg tMtrValMsg) {
		LOGGER.info("=========== Create TMtrValMsg ===========");
		return genericDAO.store(tMtrValMsg);
	}

	/**
	 * Deletes a TMtrValMsg entity object from the persistent store
	 * 
	 * @param tMtrValMsg
	 *            TMtrValMsg Entity object to be deleted
	 */
	public void deleteTMtrValMsg(final TMtrValMsgId tMtrValMsgId) {
		LOGGER.info("=========== Delete TMtrValMsg ===========");
		final TMtrValMsg tMtrValMsg = genericDAO.get(clazz, tMtrValMsgId);
		genericDAO.remove(tMtrValMsg);
	}

	/**
	 * Updates a TMtrValMsg entity object in to the persistent store
	 * 
	 * @param tMtrValMsg
	 *            TMtrValMsg Entity object to be updated
	 * @return tMtrValMsg Persisted TMtrValMsg object
	 */
	public TMtrValMsg updateTMtrValMsg(final TMtrValMsg tMtrValMsg) {
		LOGGER.info("=========== Update TMtrValMsg ===========");
		return genericDAO.update(tMtrValMsg);
	}

	/**
	 * Retrieve an TMtrValMsg object based on given TMtrValMsg TMtrValMsgId.
	 * 
	 * @param tMtrValMsgId
	 *            the primary key value of the TMtrValMsg Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TMtrValMsg findTMtrValMsgById(final TMtrValMsgId tMtrValMsgId) {
		LOGGER.info("find TMtrValMsg instance with TMtrValMsgId: " + tMtrValMsgId);
		return genericDAO.get(clazz, tMtrValMsgId);
	}

	/**
	 * Retrieve TMtrValMsg based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TMtrValMsg> list of TMtrValMsg if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TMtrValMsg> findTMtrValMsgs(final SearchFilter<TMtrValMsg> searchFilter) {
		LOGGER.info("=========== Find TMtrValMsgs ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TMtrValMsg tMtrValMsg = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tMtrValMsgentity", tMtrValMsg);

		if (tMtrValMsg.getTMtr() == null) {
			jpaQuery.bind(TMTR, new TMtr());
		} else {
			LOGGER.info("tMtr {}"+ tMtrValMsg.getTMtr());

			jpaQuery.bind(TMTR, tMtrValMsg.getTMtr());
		}
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TMtrValMsgs.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTMtrValMsgs(final SearchFilter<TMtrValMsg> searchFilter) {
		LOGGER.info("=========== Count TMtrValMsg ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TMtrValMsg tMtrValMsg = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tMtrValMsgentity", tMtrValMsg);

		if (tMtrValMsg.getTMtr() == null) {
			jpaCountQuery.bind(TMTR, new TMtr());
		} else {
			LOGGER.info("tMtr {}"+ tMtrValMsg.getTMtr());

			jpaCountQuery.bind(TMTR, tMtrValMsg.getTMtr());
		}

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TMtrValMsg based on given search criteria using JPA named Query.
	 * The search criteria is of TMtr type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TMtrValMsg> list of TMtrValMsgs if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TMtrValMsg> getTMtrValMsgsByTMtr(final SearchFilter<TMtr> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TMtr tMtr = searchFilter.getEntityClass();
		List<Object> tMtrList = new ArrayList<Object>();
		tMtrList.add(tMtr);
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTMtrValMsgByTMtr", tMtrList, index, maxresult);
	}

	/**
	 * Count TMtrValMsg based on given search criteria using JPA named Query.
	 * The search criteria is of TMtr type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTMtrValMsgsByTMtr(final SearchFilter<TMtr> searchFilter) {

		final TMtr tMtr = searchFilter.getEntityClass();
		List<Object> tMtrList = new ArrayList<Object>();
		tMtrList.add(tMtr);
		return genericDAO.findEntitiesByNamedQuery("CountTMtrValMsgsByTMtr", tMtrList);
	}

	@Override
	public List<Object> findTMtrValMsgsByMtrId(Integer mtrId, short tenantId) {
		
		List<Object> list = new ArrayList<Object>();
		list.add(mtrId);
		list.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("findTMtrValMsgsByMtrId", list, 0, -1);
	}

}
