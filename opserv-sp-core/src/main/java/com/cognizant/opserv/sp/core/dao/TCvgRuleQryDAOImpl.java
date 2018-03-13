package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TCvgRuleQry;
import com.cognizant.opserv.sp.core.entity.TCvgRuleQryId;
import com.cognizant.opserv.sp.core.entity.TCvgRuleSched;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TCvgRuleQryDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tCvgRuleQryDAO")
public class TCvgRuleQryDAOImpl implements TCvgRuleQryDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(TCvgRuleQryDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private static final String TCVGRULESCHED = "tCvgRuleSched";

	private final Class<TCvgRuleQry> clazz;

	public Class<TCvgRuleQry> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TCvgRuleQryDAOImpl() {
		super();
		this.clazz = TCvgRuleQry.class;
	}

	private static final String JPAQL = "select tCvgRuleQryentity from TCvgRuleQry tCvgRuleQryentity";

	private static final String JPACOUNTQL = "select count(*) from TCvgRuleQry tCvgRuleQryentity";

	private static final String[] RESTRICTIONS = {
			"tCvgRuleQryentity.tCvgRuleQryId.txnId = #{tCvgRuleQryentity.tCvgRuleQryId.getTxnId}",
			"tCvgRuleQryentity.tCvgRuleQryId.ruleId = #{tCvgRuleQryentity.tCvgRuleQryId.getRuleId}",
			"tCvgRuleQryentity.qryStmt like '#{tCvgRuleQryentity.getQryStmt}%'",
			"tCvgRuleQryentity.affQryStmt like '#{tCvgRuleQryentity.getAffQryStmt}%'",
			"tCvgRuleQryentity.createdBy = #{tCvgRuleQryentity.getCreatedBy}",
			"Date(tCvgRuleQryentity.createDt) = '#{tCvgRuleQryentity.getCreateDt}'",
			"tCvgRuleQryentity.updatedBy = #{tCvgRuleQryentity.getUpdatedBy}",
			"Date(tCvgRuleQryentity.updateDt) = '#{tCvgRuleQryentity.getUpdateDt}'",
			"tCvgRuleQryentity.tenantId = #{tCvgRuleQryentity.getTenantId}",
			"tCvgRuleQryentity.affHierLevelId = #{tCvgRuleQryentity.getAffHierLevelId}",
			"tCvgRuleQryentity.affTypeId = #{tCvgRuleQryentity.getAffTypeId}",
			"tCvgRuleQryentity.tCvgRuleSched.txnId = #{tCvgRuleQryentity.tCvgRuleSched.getTxnId}" };

	/**
	 * Stores a new TCvgRuleQry entity object in to the persistent store
	 * 
	 * @param tCvgRuleQry
	 *            TCvgRuleQry Entity object to be persisted
	 * @return tCvgRuleQry Persisted TCvgRuleQry object
	 */
	public TCvgRuleQry createTCvgRuleQry(final TCvgRuleQry tCvgRuleQry) {
		LOGGER.info("=========== Create TCvgRuleQry ===========");
		return genericDAO.store(tCvgRuleQry);
	}

	/**
	 * Deletes a TCvgRuleQry entity object from the persistent store
	 * 
	 * @param tCvgRuleQry
	 *            TCvgRuleQry Entity object to be deleted
	 */
	public void deleteTCvgRuleQry(final TCvgRuleQryId tCvgRuleQryId) {
		LOGGER.info("=========== Delete TCvgRuleQry ===========");
		final TCvgRuleQry tCvgRuleQry = genericDAO.get(clazz, tCvgRuleQryId);
		genericDAO.remove(tCvgRuleQry);
	}

	/**
	 * Updates a TCvgRuleQry entity object in to the persistent store
	 * 
	 * @param tCvgRuleQry
	 *            TCvgRuleQry Entity object to be updated
	 * @return tCvgRuleQry Persisted TCvgRuleQry object
	 */
	public TCvgRuleQry updateTCvgRuleQry(final TCvgRuleQry tCvgRuleQry) {
		LOGGER.info("=========== Update TCvgRuleQry ===========");
		return genericDAO.update(tCvgRuleQry);
	}

	/**
	 * Retrieve an TCvgRuleQry object based on given TCvgRuleQry TCvgRuleQryId.
	 * 
	 * @param tCvgRuleQryId
	 *            the primary key value of the TCvgRuleQry Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TCvgRuleQry findTCvgRuleQryById(final TCvgRuleQryId tCvgRuleQryId) {
		LOGGER.info("find TCvgRuleQry instance with TCvgRuleQryId: " + tCvgRuleQryId);
		return genericDAO.get(clazz, tCvgRuleQryId);
	}

	/**
	 * Retrieve TCvgRuleQry based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCvgRuleQry> list of TCvgRuleQry if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TCvgRuleQry> findTCvgRuleQrys(final SearchFilter<TCvgRuleQry> searchFilter) {
		LOGGER.info("=========== Find TCvgRuleQrys ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TCvgRuleQry tCvgRuleQry = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tCvgRuleQryentity", tCvgRuleQry);

		if (tCvgRuleQry.getTCvgRuleSched() == null) {
			jpaQuery.bind(TCVGRULESCHED, new TCvgRuleSched());
		} else {
			LOGGER.info("tCvgRuleSched {}", tCvgRuleQry.getTCvgRuleSched());

			jpaQuery.bind(TCVGRULESCHED, tCvgRuleQry.getTCvgRuleSched());
		}
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TCvgRuleQrys.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTCvgRuleQrys(final SearchFilter<TCvgRuleQry> searchFilter) {
		LOGGER.info("=========== Count TCvgRuleQry ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TCvgRuleQry tCvgRuleQry = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tCvgRuleQryentity", tCvgRuleQry);

		if (tCvgRuleQry.getTCvgRuleSched() == null) {
			jpaCountQuery.bind(TCVGRULESCHED, new TCvgRuleSched());
		} else {
			LOGGER.info("tCvgRuleSched {}", tCvgRuleQry.getTCvgRuleSched());

			jpaCountQuery.bind(TCVGRULESCHED, tCvgRuleQry.getTCvgRuleSched());
		}

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TCvgRuleQry based on given search criteria using JPA named Query.
	 * The search criteria is of TCvgRuleSched type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCvgRuleQry> list of TCvgRuleQrys if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TCvgRuleQry> getTCvgRuleQrysByTCvgRuleSched(final SearchFilter<TCvgRuleSched> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TCvgRuleSched tCvgRuleSched = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTCvgRuleQryByTCvgRuleSched", (List<Object>) tCvgRuleSched, index, maxresult);
	}

	/**
	 * Count TCvgRuleQry based on given search criteria using JPA named Query.
	 * The search criteria is of TCvgRuleSched type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTCvgRuleQrysByTCvgRuleSched(final SearchFilter<TCvgRuleSched> searchFilter) {

		final TCvgRuleSched tCvgRuleSched = searchFilter.getEntityClass();
		return genericDAO.findEntitiesByNamedQuery("CountTCvgRuleQrysByTCvgRuleSched", (List<Object>) tCvgRuleSched);
	}

	@Override
	public List<TCvgRuleQry> findTCvgRuleQryByIds(Integer txtId,
			Integer ruleId, short tenantId) {
		List<Object> paramList = new ArrayList<>();
		paramList.add(txtId);
		paramList.add(ruleId);
		paramList.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond(
				"FindTCvgRuleQryByIds", paramList, 0, -1);
	}
	
	

}
