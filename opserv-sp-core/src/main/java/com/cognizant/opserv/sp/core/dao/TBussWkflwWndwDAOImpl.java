package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TAlgmntSalesTeam;
import com.cognizant.opserv.sp.core.entity.TBussWkflwWndw;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TBussWkflwWndwDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tBussWkflwWndwDAO")
public class TBussWkflwWndwDAOImpl implements TBussWkflwWndwDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TBussWkflwWndwDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private static final String TALGMNTSALESTEAM = "tAlgmntSalesTeam";

	private final Class<TBussWkflwWndw> clazz;

	public Class<TBussWkflwWndw> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TBussWkflwWndwDAOImpl() {
		super();
		this.clazz = TBussWkflwWndw.class;
	}

	private static final String JPAQL = "select tBussWkflwWndwentity from TBussWkflwWndw tBussWkflwWndwentity";

	private static final String JPACOUNTQL = "select count(tBussWkflwWndwentity) from TBussWkflwWndw tBussWkflwWndwentity";

	private static final String[] RESTRICTIONS = {
			"tBussWkflwWndwentity.wkflwWndwId = #{tBussWkflwWndwentity.getWkflwWndwId}",
			"Date(tBussWkflwWndwentity.startDt) = '#{tBussWkflwWndwentity.getStartDt}'",
			"Date(tBussWkflwWndwentity.endDt) = '#{tBussWkflwWndwentity.getEndDt}'",
			"tBussWkflwWndwentity.activeFlag = #{tBussWkflwWndwentity.getActiveFlag}",
			"tBussWkflwWndwentity.createdBy = #{tBussWkflwWndwentity.getCreatedBy}",
			"Date(tBussWkflwWndwentity.createDt) = '#{tBussWkflwWndwentity.getCreateDt}'",
			"tBussWkflwWndwentity.updatedBy = #{tBussWkflwWndwentity.getUpdatedBy}",
			"Date(tBussWkflwWndwentity.updateDt) = '#{tBussWkflwWndwentity.getUpdateDt}'",
			"tBussWkflwWndwentity.tenantId = #{tBussWkflwWndwentity.getTenantId}",
			"tBussWkflwWndwentity.wkflwId = #{tBussWkflwWndwentity.getWkflwId}",
			"tBussWkflwWndwentity.tAlgmntSalesTeam.tAlgmntSalesTeamId = #{tBussWkflwWndwentity.tAlgmntSalesTeam.getTAlgmntSalesTeamId}" };

	/**
	 * Stores a new TBussWkflwWndw entity object in to the persistent store
	 * 
	 * @param tBussWkflwWndw
	 *            TBussWkflwWndw Entity object to be persisted
	 * @return tBussWkflwWndw Persisted TBussWkflwWndw object
	 */
	public TBussWkflwWndw createTBussWkflwWndw(final TBussWkflwWndw tBussWkflwWndw) {
		LOGGER.info("=========== Create TBussWkflwWndw ===========");
		return genericDAO.store(tBussWkflwWndw);
	}

	/**
	 * Deletes a TBussWkflwWndw entity object from the persistent store
	 * 
	 * @param tBussWkflwWndw
	 *            TBussWkflwWndw Entity object to be deleted
	 */
	public void deleteTBussWkflwWndw(final Integer wkflwWndwId) {
		LOGGER.info("=========== Delete TBussWkflwWndw ===========");
		final TBussWkflwWndw tBussWkflwWndw = genericDAO.get(clazz, wkflwWndwId);
		genericDAO.remove(tBussWkflwWndw);
	}

	/**
	 * Updates a TBussWkflwWndw entity object in to the persistent store
	 * 
	 * @param tBussWkflwWndw
	 *            TBussWkflwWndw Entity object to be updated
	 * @return tBussWkflwWndw Persisted TBussWkflwWndw object
	 */
	public TBussWkflwWndw updateTBussWkflwWndw(final TBussWkflwWndw tBussWkflwWndw) {
		LOGGER.info("=========== Update TBussWkflwWndw ===========");
		return genericDAO.update(tBussWkflwWndw);
	}

	/**
	 * Retrieve an TBussWkflwWndw object based on given TBussWkflwWndw wkflwWndwId.
	 * 
	 * @param tBussWkflwWndwId
	 *            the primary key value of the TBussWkflwWndw Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TBussWkflwWndw findTBussWkflwWndwById(final Integer tBussWkflwWndwId) {
		LOGGER.info("find TBussWkflwWndw instance with wkflwWndwId: " + tBussWkflwWndwId);
		return genericDAO.get(clazz, tBussWkflwWndwId);
	}

	/**
	 * Retrieve TBussWkflwWndw based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TBussWkflwWndw> list of TBussWkflwWndw if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TBussWkflwWndw> findTBussWkflwWndws(final SearchFilter<TBussWkflwWndw> searchFilter) {
		LOGGER.info("=========== Find TBussWkflwWndws ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TBussWkflwWndw tBussWkflwWndw = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tBussWkflwWndwentity", tBussWkflwWndw);

		if (tBussWkflwWndw.getTAlgmntSalesTeam() == null) {
			jpaQuery.bind(TALGMNTSALESTEAM, new TAlgmntSalesTeam());
		} else {
			LOGGER.info("tAlgmntSalesTeam {}"+ tBussWkflwWndw.getTAlgmntSalesTeam());

			jpaQuery.bind(TALGMNTSALESTEAM, tBussWkflwWndw.getTAlgmntSalesTeam());
		}
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TBussWkflwWndws.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTBussWkflwWndws(final SearchFilter<TBussWkflwWndw> searchFilter) {
		LOGGER.info("=========== Count TBussWkflwWndw ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TBussWkflwWndw tBussWkflwWndw = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tBussWkflwWndwentity", tBussWkflwWndw);

		if (tBussWkflwWndw.getTAlgmntSalesTeam() == null) {
			jpaCountQuery.bind(TALGMNTSALESTEAM, new TAlgmntSalesTeam());
		} else {
			LOGGER.info("tAlgmntSalesTeam {}"+ tBussWkflwWndw.getTAlgmntSalesTeam());

			jpaCountQuery.bind(TALGMNTSALESTEAM, tBussWkflwWndw.getTAlgmntSalesTeam());
		}

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TBussWkflwWndw based on given search criteria using JPA named Query.
	 * The search criteria is of TAlgmntSalesTeam type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TBussWkflwWndw> list of TBussWkflwWndws if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TBussWkflwWndw> getTBussWkflwWndwsByTAlgmntSalesTeam(final SearchFilter<TAlgmntSalesTeam> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TAlgmntSalesTeam tAlgmntSalesTeam = searchFilter.getEntityClass();
		List<Object> tAlgmntSalesTeamList = new ArrayList<Object>();
		tAlgmntSalesTeamList.add(tAlgmntSalesTeam);
		
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTBussWkflwWndwByTAlgmntSalesTeam", tAlgmntSalesTeamList, index,
				maxresult);
	}

	/**
	 * Count TBussWkflwWndw based on given search criteria using JPA named Query.
	 * The search criteria is of TAlgmntSalesTeam type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTBussWkflwWndwsByTAlgmntSalesTeam(final SearchFilter<TAlgmntSalesTeam> searchFilter) {

		final TAlgmntSalesTeam tAlgmntSalesTeam = searchFilter.getEntityClass();
		List<Object> tAlgmntSalesTeamList = new ArrayList<Object>();
		tAlgmntSalesTeamList.add(tAlgmntSalesTeam);
		return genericDAO.findEntitiesByNamedQuery("CountTBussWkflwWndwsByTAlgmntSalesTeam", tAlgmntSalesTeamList);
	}

}
