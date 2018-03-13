package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TCustAffHier;
import com.cognizant.opserv.sp.core.entity.TCustAlgmntAff;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TCustAffHierDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tCustAffHierDAO")
public class TCustAffHierDAOImpl implements TCustAffHierDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TCustAffHierDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private static final String TCUSTALGMNTAFFBYAFFTYPEID = "tCustAlgmntAffByAffTypeId";
	private static final String TCUSTALGMNTAFFBYPRNAFFTYPEID = "tCustAlgmntAffByPrnAffTypeId";

	private final Class<TCustAffHier> clazz;

	public Class<TCustAffHier> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TCustAffHierDAOImpl() {
		super();
		this.clazz = TCustAffHier.class;
	}

	private static final String JPAQL = "select tCustAffHierentity from TCustAffHier tCustAffHierentity";

	private static final String JPACOUNTQL = "select count(tCustAffHierentity) from TCustAffHier tCustAffHierentity";

	private static final String[] RESTRICTIONS = {
			"tCustAffHierentity.affHierId = #{tCustAffHierentity.getAffHierId}",
			"tCustAffHierentity.activeFlag = #{tCustAffHierentity.getActiveFlag}",
			"tCustAffHierentity.createdBy = #{tCustAffHierentity.getCreatedBy}",
			"Date(tCustAffHierentity.createDt) = '#{tCustAffHierentity.getCreateDt}'",
			"tCustAffHierentity.updatedBy = #{tCustAffHierentity.getUpdatedBy}",
			"Date(tCustAffHierentity.updateDt) = '#{tCustAffHierentity.getUpdateDt}'",
			"tCustAffHierentity.tenantId = #{tCustAffHierentity.getTenantId}",
			"tCustAffHierentity.tCustAlgmntAffByAffTypeId.affTypeId = #{tCustAffHierentity.tCustAlgmntAffByAffTypeId.getAffTypeId}",
			"tCustAffHierentity.tCustAlgmntAffByPrnAffTypeId.affTypeId = #{tCustAffHierentity.tCustAlgmntAffByPrnAffTypeId.getAffTypeId}" };

	/**
	 * Stores a new TCustAffHier entity object in to the persistent store
	 * 
	 * @param tCustAffHier
	 *            TCustAffHier Entity object to be persisted
	 * @return tCustAffHier Persisted TCustAffHier object
	 */
	public TCustAffHier createTCustAffHier(final TCustAffHier tCustAffHier) {
		LOGGER.info("=========== Create TCustAffHier ===========");
		return genericDAO.store(tCustAffHier);
	}

	/**
	 * Deletes a TCustAffHier entity object from the persistent store
	 * 
	 * @param tCustAffHier
	 *            TCustAffHier Entity object to be deleted
	 */
	public void deleteTCustAffHier(final Integer affHierId) {
		LOGGER.info("=========== Delete TCustAffHier ===========");
		final TCustAffHier tCustAffHier = genericDAO.get(clazz, affHierId);
		genericDAO.remove(tCustAffHier);
	}

	/**
	 * Updates a TCustAffHier entity object in to the persistent store
	 * 
	 * @param tCustAffHier
	 *            TCustAffHier Entity object to be updated
	 * @return tCustAffHier Persisted TCustAffHier object
	 */
	public TCustAffHier updateTCustAffHier(final TCustAffHier tCustAffHier) {
		LOGGER.info("=========== Update TCustAffHier ===========");
		return genericDAO.update(tCustAffHier);
	}

	/**
	 * Retrieve an TCustAffHier object based on given TCustAffHier affHierId.
	 * 
	 * @param tCustAffHierId
	 *            the primary key value of the TCustAffHier Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TCustAffHier findTCustAffHierById(final Integer tCustAffHierId) {
		LOGGER.info("find TCustAffHier instance with affHierId: " + tCustAffHierId);
		return genericDAO.get(clazz, tCustAffHierId);
	}

	/**
	 * Retrieve TCustAffHier based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCustAffHier> list of TCustAffHier if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TCustAffHier> findTCustAffHiers(final SearchFilter<TCustAffHier> searchFilter) {
		LOGGER.info("=========== Find TCustAffHiers ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TCustAffHier tCustAffHier = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tCustAffHierentity", tCustAffHier);

		if (tCustAffHier.getTCustAlgmntAffByAffTypeId() == null) {
			jpaQuery.bind(TCUSTALGMNTAFFBYAFFTYPEID, new TCustAlgmntAff());
		} else {
			LOGGER.info("tCustAlgmntAffByAffTypeId {}" + tCustAffHier.getTCustAlgmntAffByAffTypeId());

			jpaQuery.bind(TCUSTALGMNTAFFBYAFFTYPEID, tCustAffHier.getTCustAlgmntAffByAffTypeId());
		}

		if (tCustAffHier.getTCustAlgmntAffByPrnAffTypeId() == null) {
			jpaQuery.bind(TCUSTALGMNTAFFBYPRNAFFTYPEID, new TCustAlgmntAff());
		} else {
			LOGGER.info("tCustAlgmntAffByPrnAffTypeId {}" + tCustAffHier.getTCustAlgmntAffByPrnAffTypeId());

			jpaQuery.bind(TCUSTALGMNTAFFBYPRNAFFTYPEID, tCustAffHier.getTCustAlgmntAffByPrnAffTypeId());
		}
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TCustAffHiers.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTCustAffHiers(final SearchFilter<TCustAffHier> searchFilter) {
		LOGGER.info("=========== Count TCustAffHier ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TCustAffHier tCustAffHier = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tCustAffHierentity", tCustAffHier);

		if (tCustAffHier.getTCustAlgmntAffByAffTypeId() == null) {
			jpaCountQuery.bind(TCUSTALGMNTAFFBYAFFTYPEID, new TCustAlgmntAff());
		} else {
			LOGGER.info("tCustAlgmntAffByAffTypeId {}" + tCustAffHier.getTCustAlgmntAffByAffTypeId());

			jpaCountQuery.bind(TCUSTALGMNTAFFBYAFFTYPEID, tCustAffHier.getTCustAlgmntAffByAffTypeId());
		}

		if (tCustAffHier.getTCustAlgmntAffByPrnAffTypeId() == null) {
			jpaCountQuery.bind(TCUSTALGMNTAFFBYPRNAFFTYPEID, new TCustAlgmntAff());
		} else {
			LOGGER.info("tCustAlgmntAffByPrnAffTypeId {}" + tCustAffHier.getTCustAlgmntAffByPrnAffTypeId());

			jpaCountQuery.bind(TCUSTALGMNTAFFBYPRNAFFTYPEID, tCustAffHier.getTCustAlgmntAffByPrnAffTypeId());
		}

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TCustAffHier based on given search criteria using JPA named Query.
	 * The search criteria is of TCustAlgmntAffByAffTypeId type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCustAffHier> list of TCustAffHiers if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TCustAffHier> getTCustAffHiersByTCustAlgmntAffByAffTypeId(
			final SearchFilter<TCustAlgmntAff> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TCustAlgmntAff tCustAlgmntAff = searchFilter.getEntityClass();
		List<Object> tCustAlgmntAffList = new ArrayList<Object>();
		tCustAlgmntAffList.add(tCustAlgmntAff);
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTCustAffHierByTCustAlgmntAffByAffTypeId", tCustAlgmntAffList,
				index, maxresult);
	}

	/**
	 * Count TCustAffHier based on given search criteria using JPA named Query.
	 * The search criteria is of TCustAlgmntAff type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTCustAffHiersByTCustAlgmntAffByAffTypeId(final SearchFilter<TCustAlgmntAff> searchFilter) {

		final TCustAlgmntAff tCustAlgmntAff = searchFilter.getEntityClass();
		List<Object> tCustAlgmntAffList = new ArrayList<Object>();
		tCustAlgmntAffList.add(tCustAlgmntAff);
		return genericDAO.findEntitiesByNamedQuery("CountTCustAffHiersByTCustAlgmntAffByAffTypeId", tCustAlgmntAffList);
	}

	/**
	 * Retrieve TCustAffHier based on given search criteria using JPA named Query.
	 * The search criteria is of TCustAlgmntAffByPrnAffTypeId type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCustAffHier> list of TCustAffHiers if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TCustAffHier> getTCustAffHiersByTCustAlgmntAffByPrnAffTypeId(
			final SearchFilter<TCustAlgmntAff> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TCustAlgmntAff tCustAlgmntAff = searchFilter.getEntityClass();
		List<Object> tCustAlgmntAffList = new ArrayList<Object>();
		tCustAlgmntAffList.add(tCustAlgmntAff);
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTCustAffHierByTCustAlgmntAffByPrnAffTypeId", tCustAlgmntAffList,
				index, maxresult);
	}

	/**
	 * Count TCustAffHier based on given search criteria using JPA named Query.
	 * The search criteria is of TCustAlgmntAff type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTCustAffHiersByTCustAlgmntAffByPrnAffTypeId(final SearchFilter<TCustAlgmntAff> searchFilter) {

		final TCustAlgmntAff tCustAlgmntAff = searchFilter.getEntityClass();
		List<Object> tCustAlgmntAffList = new ArrayList<Object>();
		tCustAlgmntAffList.add(tCustAlgmntAff);
		return genericDAO.findEntitiesByNamedQuery("CountTCustAffHiersByTCustAlgmntAffByPrnAffTypeId", tCustAlgmntAffList);
	}

}
