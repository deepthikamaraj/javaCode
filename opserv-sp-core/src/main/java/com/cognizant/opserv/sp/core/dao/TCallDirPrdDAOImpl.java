package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TCallDir;
import com.cognizant.opserv.sp.core.entity.TCallDirPrd;
import com.cognizant.opserv.sp.core.entity.TCallDirPrdId;
import com.cognizant.opserv.sp.core.entity.TPrdPrtType;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TCallDirPrdDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tCallDirPrdDAO")
public class TCallDirPrdDAOImpl implements TCallDirPrdDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TCallDirPrdDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private static final String TCALLDIR = "tCallDir";
	//private static final String TPRDPRTTYPE = "tPrdPrtType";

	private final Class<TCallDirPrd> clazz;

	public Class<TCallDirPrd> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TCallDirPrdDAOImpl() {
		super();
		this.clazz = TCallDirPrd.class;
	}

	private static final String JPAQL = "select tCallDirPrdentity from TCallDirPrd tCallDirPrdentity";

	private static final String JPACOUNTQL = "select count(*) from TCallDirPrd tCallDirPrdentity";

	private static final String[] RESTRICTIONS = {
			"tCallDirPrdentity.tCallDirPrdId.callDirId = #{tCallDirPrdentity.tCallDirPrdId.getCallDirId}",
			"tCallDirPrdentity.tCallDirPrdId.prdPrtTypeId = #{tCallDirPrdentity.tCallDirPrdId.getPrdPrtTypeId}",
			"tCallDirPrdentity.prdId = #{tCallDirPrdentity.getPrdId}",			
			"tCallDirPrdentity.activeFlag = #{tCallDirPrdentity.getActiveFlag}",
			"tCallDirPrdentity.createdBy = #{tCallDirPrdentity.getCreatedBy}",
			"Date(tCallDirPrdentity.createDt) = '#{tCallDirPrdentity.getCreateDt}'",
			"tCallDirPrdentity.updatedBy = #{tCallDirPrdentity.getUpdatedBy}",
			"Date(tCallDirPrdentity.updateDt) = '#{tCallDirPrdentity.getUpdateDt}'",
			"tCallDirPrdentity.tenantId = #{tCallDirPrdentity.getTenantId}",
			"tCallDirPrdentity.tCallDir.callDirId = #{tCallDirPrdentity.tCallDir.getCallDirId}",
			"tCallDirPrdentity.tPrdPrtType.prdPrtTypeId = #{tCallDirPrdentity.tPrdPrtType.getPrdPrtTypeId}" };

	/**
	 * Stores a new TCallDirPrd entity object in to the persistent store
	 * 
	 * @param tCallDirPrd
	 *            TCallDirPrd Entity object to be persisted
	 * @return tCallDirPrd Persisted TCallDirPrd object
	 */
	public TCallDirPrd createTCallDirPrd(final TCallDirPrd tCallDirPrd) {
		LOGGER.info("=========== Create TCallDirPrd ===========");
		return genericDAO.store(tCallDirPrd);
	}

	/**
	 * Deletes a TCallDirPrd entity object from the persistent store
	 * 
	 * @param tCallDirPrd
	 *            TCallDirPrd Entity object to be deleted
	 */
	public void deleteTCallDirPrd(final TCallDirPrdId tCallDirPrdId) {
		LOGGER.info("=========== Delete TCallDirPrd ===========");
		final TCallDirPrd tCallDirPrd = genericDAO.get(clazz, tCallDirPrdId);
		genericDAO.remove(tCallDirPrd);
	}

	/**
	 * Updates a TCallDirPrd entity object in to the persistent store
	 * 
	 * @param tCallDirPrd
	 *            TCallDirPrd Entity object to be updated
	 * @return tCallDirPrd Persisted TCallDirPrd object
	 */
	public TCallDirPrd updateTCallDirPrd(final TCallDirPrd tCallDirPrd) {
		LOGGER.info("=========== Update TCallDirPrd ===========");
		return genericDAO.update(tCallDirPrd);
	}

	/**
	 * Retrieve an TCallDirPrd object based on given TCallDirPrd TCallDirPrdId.
	 * 
	 * @param tCallDirPrdId
	 *            the primary key value of the TCallDirPrd Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TCallDirPrd findTCallDirPrdById(final TCallDirPrdId tCallDirPrdId) {
		LOGGER.info("find TCallDirPrd instance with TCallDirPrdId: " + tCallDirPrdId);
		return genericDAO.get(clazz, tCallDirPrdId);
	}

	/**
	 * Retrieve TCallDirPrd based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCallDirPrd> list of TCallDirPrd if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TCallDirPrd> findTCallDirPrds(final SearchFilter<TCallDirPrd> searchFilter) {
		LOGGER.info("=========== Find TCallDirPrds ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TCallDirPrd tCallDirPrd = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tCallDirPrdentity", tCallDirPrd);

		if (tCallDirPrd.getTCallDir() == null) {
			jpaQuery.bind(TCALLDIR, new TCallDir());
		} else {
			LOGGER.info("tCallDir {}"+ tCallDirPrd.getTCallDir());

			jpaQuery.bind(TCALLDIR, tCallDirPrd.getTCallDir());
		}

		/*if (tCallDirPrd.getTPrdPrtType() == null) {
			jpaQuery.bind(TPRDPRTTYPE, new TPrdPrtType());
		} else {
			LOGGER.info("tPrdPrtType {}", tCallDirPrd.getTPrdPrtType());

			jpaQuery.bind(TPRDPRTTYPE, tCallDirPrd.getTPrdPrtType());
		}*/
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TCallDirPrds.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTCallDirPrds(final SearchFilter<TCallDirPrd> searchFilter) {
		LOGGER.info("=========== Count TCallDirPrd ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TCallDirPrd tCallDirPrd = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tCallDirPrdentity", tCallDirPrd);

		if (tCallDirPrd.getTCallDir() == null) {
			jpaCountQuery.bind(TCALLDIR, new TCallDir());
		} else {
			LOGGER.info("tCallDir {}" + tCallDirPrd.getTCallDir());

			jpaCountQuery.bind(TCALLDIR, tCallDirPrd.getTCallDir());
		}

	/*	if (tCallDirPrd.getTPrdPrtType() == null) {
			jpaCountQuery.bind(TPRDPRTTYPE, new TPrdPrtType());
		} else {
			LOGGER.info("tPrdPrtType {}", tCallDirPrd.getTPrdPrtType());

			jpaCountQuery.bind(TPRDPRTTYPE, tCallDirPrd.getTPrdPrtType());
		}*/

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TCallDirPrd based on given search criteria using JPA named Query.
	 * The search criteria is of TCallDir type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCallDirPrd> list of TCallDirPrds if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TCallDirPrd> getTCallDirPrdsByTCallDir(final SearchFilter<TCallDir> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TCallDir tCallDir = searchFilter.getEntityClass();
		List<Object> tCallDirList = new ArrayList<Object>();
		tCallDirList.add(tCallDir);
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTCallDirPrdByTCallDir", tCallDirList, index, maxresult);
	}

	/**
	 * Count TCallDirPrd based on given search criteria using JPA named Query.
	 * The search criteria is of TCallDir type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTCallDirPrdsByTCallDir(final SearchFilter<TCallDir> searchFilter) {

		final TCallDir tCallDir = searchFilter.getEntityClass();
		List<Object> tCallDirList = new ArrayList<Object>();
		tCallDirList.add(tCallDir);
		return genericDAO.findEntitiesByNamedQuery("CountTCallDirPrdsByTCallDir", tCallDirList);
	}

	/**
	 * Retrieve TCallDirPrd based on given search criteria using JPA named Query.
	 * The search criteria is of TPrdPrtType type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCallDirPrd> list of TCallDirPrds if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TCallDirPrd> getTCallDirPrdsByTPrdPrtType(final SearchFilter<TPrdPrtType> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TPrdPrtType tPrdPrtType = searchFilter.getEntityClass();
		List<Object> tPrdPrtTypeList = new ArrayList<Object>();
		tPrdPrtTypeList.add(tPrdPrtType);
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTCallDirPrdByTPrdPrtType", tPrdPrtTypeList, index, maxresult);
	}

	/**
	 * Count TCallDirPrd based on given search criteria using JPA named Query.
	 * The search criteria is of TPrdPrtType type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTCallDirPrdsByTPrdPrtType(final SearchFilter<TPrdPrtType> searchFilter) {

		final TPrdPrtType tPrdPrtType = searchFilter.getEntityClass();
		List<Object> tPrdPrtTypeList = new ArrayList<Object>();
		tPrdPrtTypeList.add(tPrdPrtType);
		return genericDAO.findEntitiesByNamedQuery("CountTCallDirPrdsByTPrdPrtType", tPrdPrtTypeList);
	}
	
	
	public List<TCallDirPrd> findTCallDirPrdsByPrdId(final Long prdId)
	{
		List<Object> prdIdList = new ArrayList<Object>();
		prdIdList.add(prdId);
	return genericDAO.findEntitiesByNamedQuery("getTCallDirPrdsByPrdId", prdIdList);
	}


}
