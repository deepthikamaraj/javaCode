package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TNoteType;
import com.cognizant.opserv.sp.core.entity.TRptConfig;
import com.cognizant.opserv.sp.core.entity.TRptConfigTNoteType;
import com.cognizant.opserv.sp.core.entity.TRptConfigTNoteTypeId;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TRptConfigTNoteTypeDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tRptConfigTNoteTypeDAO")
public class TRptConfigTNoteTypeDAOImpl implements TRptConfigTNoteTypeDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TRptConfigTNoteTypeDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	//private static final String TNOTETYPE = "tNoteType";
	private static final String TRPTCONFIG = "tRptConfig";

	private final Class<TRptConfigTNoteType> clazz;

	public Class<TRptConfigTNoteType> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TRptConfigTNoteTypeDAOImpl() {
		super();
		this.clazz = TRptConfigTNoteType.class;
	}

	private static final String JPAQL = "select tRptConfigTNoteTypeentity from TRptConfigTNoteType tRptConfigTNoteTypeentity";

	private static final String JPACOUNTQL = "select count(*) from TRptConfigTNoteType tRptConfigTNoteTypeentity";

	private static final String[] RESTRICTIONS = {
			"tRptConfigTNoteTypeentity.tRptConfigTNoteTypeId.rptConfigId = #{tRptConfigTNoteTypeentity.tRptConfigTNoteTypeId.getRptConfigId}",
			"tRptConfigTNoteTypeentity.tRptConfigTNoteTypeId.noteTypeId = #{tRptConfigTNoteTypeentity.tRptConfigTNoteTypeId.getNoteTypeId}",
			"tRptConfigTNoteTypeentity.tenantId = #{tRptConfigTNoteTypeentity.getTenantId}",
			"tRptConfigTNoteTypeentity.tNoteType.noteTypeId = #{tRptConfigTNoteTypeentity.tNoteType.getNoteTypeId}",
			"tRptConfigTNoteTypeentity.tRptConfig.rptConfigId = #{tRptConfigTNoteTypeentity.tRptConfig.getRptConfigId}" };

	/**
	 * Stores a new TRptConfigTNoteType entity object in to the persistent store
	 * 
	 * @param tRptConfigTNoteType
	 *            TRptConfigTNoteType Entity object to be persisted
	 * @return tRptConfigTNoteType Persisted TRptConfigTNoteType object
	 */
	public TRptConfigTNoteType createTRptConfigTNoteType(final TRptConfigTNoteType tRptConfigTNoteType) {
		LOGGER.info("=========== Create TRptConfigTNoteType ===========");
		return genericDAO.store(tRptConfigTNoteType);
	}

	/**
	 * Deletes a TRptConfigTNoteType entity object from the persistent store
	 * 
	 * @param tRptConfigTNoteType
	 *            TRptConfigTNoteType Entity object to be deleted
	 */
	public void deleteTRptConfigTNoteType(final TRptConfigTNoteTypeId tRptConfigTNoteTypeId) {
		LOGGER.info("=========== Delete TRptConfigTNoteType ===========");
		final TRptConfigTNoteType tRptConfigTNoteType = genericDAO.get(clazz, tRptConfigTNoteTypeId);
		genericDAO.remove(tRptConfigTNoteType);
	}

	/**
	 * Updates a TRptConfigTNoteType entity object in to the persistent store
	 * 
	 * @param tRptConfigTNoteType
	 *            TRptConfigTNoteType Entity object to be updated
	 * @return tRptConfigTNoteType Persisted TRptConfigTNoteType object
	 */
	public TRptConfigTNoteType updateTRptConfigTNoteType(final TRptConfigTNoteType tRptConfigTNoteType) {
		LOGGER.info("=========== Update TRptConfigTNoteType ===========");
		return genericDAO.update(tRptConfigTNoteType);
	}

	/**
	 * Retrieve an TRptConfigTNoteType object based on given TRptConfigTNoteType TRptConfigTNoteTypeId.
	 * 
	 * @param tRptConfigTNoteTypeId
	 *            the primary key value of the TRptConfigTNoteType Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TRptConfigTNoteType findTRptConfigTNoteTypeById(final TRptConfigTNoteTypeId tRptConfigTNoteTypeId) {
		LOGGER.info("find TRptConfigTNoteType instance with TRptConfigTNoteTypeId: " + tRptConfigTNoteTypeId);
		return genericDAO.get(clazz, tRptConfigTNoteTypeId);
	}

	/**
	 * Retrieve TRptConfigTNoteType based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRptConfigTNoteType> list of TRptConfigTNoteType if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TRptConfigTNoteType> findTRptConfigTNoteTypes(final SearchFilter<TRptConfigTNoteType> searchFilter) {
		LOGGER.info("=========== Find TRptConfigTNoteTypes ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TRptConfigTNoteType tRptConfigTNoteType = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tRptConfigTNoteTypeentity", tRptConfigTNoteType);

		/*if (tRptConfigTNoteType.getTNoteType() == null) {
			jpaQuery.bind(TNOTETYPE, new TNoteType());
		} else {
			LOGGER.info("tNoteType {}", tRptConfigTNoteType.getTNoteType());

			jpaQuery.bind(TNOTETYPE, tRptConfigTNoteType.getTNoteType());
		}
*/
		if (tRptConfigTNoteType.getTRptConfig() == null) {
			jpaQuery.bind(TRPTCONFIG, new TRptConfig());
		} else {
			LOGGER.info("tRptConfig {}"+ tRptConfigTNoteType.getTRptConfig());

			jpaQuery.bind(TRPTCONFIG, tRptConfigTNoteType.getTRptConfig());
		}
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TRptConfigTNoteTypes.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTRptConfigTNoteTypes(final SearchFilter<TRptConfigTNoteType> searchFilter) {
		LOGGER.info("=========== Count TRptConfigTNoteType ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TRptConfigTNoteType tRptConfigTNoteType = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tRptConfigTNoteTypeentity", tRptConfigTNoteType);

		/*if (tRptConfigTNoteType.getTNoteType() == null) {
			jpaCountQuery.bind(TNOTETYPE, new TNoteType());
		} else {
			LOGGER.info("tNoteType {}", tRptConfigTNoteType.getTNoteType());

			jpaCountQuery.bind(TNOTETYPE, tRptConfigTNoteType.getTNoteType());
		}
*/
		if (tRptConfigTNoteType.getTRptConfig() == null) {
			jpaCountQuery.bind(TRPTCONFIG, new TRptConfig());
		} else {
			LOGGER.info("tRptConfig {}"+ tRptConfigTNoteType.getTRptConfig());

			jpaCountQuery.bind(TRPTCONFIG, tRptConfigTNoteType.getTRptConfig());
		}

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TRptConfigTNoteType based on given search criteria using JPA named Query.
	 * The search criteria is of TNoteType type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRptConfigTNoteType> list of TRptConfigTNoteTypes if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TRptConfigTNoteType> getTRptConfigTNoteTypesByTNoteType(final SearchFilter<TNoteType> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTRptConfigTNoteTypeByTNoteType", queryParam, index, maxresult);
	}

	/**
	 * Count TRptConfigTNoteType based on given search criteria using JPA named Query.
	 * The search criteria is of TNoteType type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTRptConfigTNoteTypesByTNoteType(final SearchFilter<TNoteType> searchFilter) {

		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());
		return genericDAO.findEntitiesByNamedQuery("CountTRptConfigTNoteTypesByTNoteType", queryParam);
	}

	/**
	 * Retrieve TRptConfigTNoteType based on given search criteria using JPA named Query.
	 * The search criteria is of TRptConfig type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRptConfigTNoteType> list of TRptConfigTNoteTypes if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TRptConfigTNoteType> getTRptConfigTNoteTypesByTRptConfig(final SearchFilter<TRptConfig> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTRptConfigTNoteTypeByTRptConfig", queryParam, index, maxresult);
	}

	/**
	 * Count TRptConfigTNoteType based on given search criteria using JPA named Query.
	 * The search criteria is of TRptConfig type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTRptConfigTNoteTypesByTRptConfig(final SearchFilter<TRptConfig> searchFilter) {

		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());
		return genericDAO.findEntitiesByNamedQuery("CountTRptConfigTNoteTypesByTRptConfig", queryParam);
	}

}
