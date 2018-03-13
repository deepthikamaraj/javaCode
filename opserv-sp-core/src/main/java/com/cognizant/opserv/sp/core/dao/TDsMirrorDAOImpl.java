package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TDsMirror;
import com.cognizant.opserv.sp.core.entity.TDsStg;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TDsMirrorDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tDsMirrorDAO")
public class TDsMirrorDAOImpl implements TDsMirrorDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TDsMirrorDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private static final String TDSSTGBYDSID = "tDsStgByDsId";
	private static final String TDSSTGBYMIRRORDSID = "tDsStgByMirrorDsId";

	private final Class<TDsMirror> clazz;

	public Class<TDsMirror> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TDsMirrorDAOImpl() {
		super();
		this.clazz = TDsMirror.class;
	}

	private static final String JPAQL = "select tDsMirrorentity from TDsMirror tDsMirrorentity";

	private static final String JPACOUNTQL = "select count(tDsMirrorentity) from TDsMirror tDsMirrorentity";

	private static final String[] RESTRICTIONS = { "tDsMirrorentity.dsMirrorId = #{tDsMirrorentity.getDsMirrorId}",
			"tDsMirrorentity.createdBy = #{tDsMirrorentity.getCreatedBy}",
			"Date(tDsMirrorentity.createDt) = '#{tDsMirrorentity.getCreateDt}'",
			"tDsMirrorentity.updatedBy = #{tDsMirrorentity.getUpdatedBy}",
			"Date(tDsMirrorentity.updateDt) = '#{tDsMirrorentity.getUpdateDt}'",
			"tDsMirrorentity.tenantId = #{tDsMirrorentity.getTenantId}",
			"tDsMirrorentity.tDsStgByDsId.dsId = #{tDsMirrorentity.tDsStgByDsId.getDsId}",
			"tDsMirrorentity.tDsStgByMirrorDsId.dsId = #{tDsMirrorentity.tDsStgByMirrorDsId.getDsId}" };

	/**
	 * Stores a new TDsMirror entity object in to the persistent store
	 * 
	 * @param tDsMirror
	 *            TDsMirror Entity object to be persisted
	 * @return tDsMirror Persisted TDsMirror object
	 */
	public TDsMirror createTDsMirror(final TDsMirror tDsMirror) {
		LOGGER.info("=========== Create TDsMirror ===========");
		return genericDAO.store(tDsMirror);
	}

	/**
	 * Deletes a TDsMirror entity object from the persistent store
	 * 
	 * @param tDsMirror
	 *            TDsMirror Entity object to be deleted
	 */
	public void deleteTDsMirror(final Integer dsMirrorId) {
		LOGGER.info("=========== Delete TDsMirror ===========");
		final TDsMirror tDsMirror = genericDAO.get(clazz, dsMirrorId);
		genericDAO.remove(tDsMirror);
	}

	/**
	 * Updates a TDsMirror entity object in to the persistent store
	 * 
	 * @param tDsMirror
	 *            TDsMirror Entity object to be updated
	 * @return tDsMirror Persisted TDsMirror object
	 */
	public TDsMirror updateTDsMirror(final TDsMirror tDsMirror) {
		LOGGER.info("=========== Update TDsMirror ===========");
		return genericDAO.update(tDsMirror);
	}

	/**
	 * Retrieve an TDsMirror object based on given TDsMirror dsMirrorId.
	 * 
	 * @param tDsMirrorId
	 *            the primary key value of the TDsMirror Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TDsMirror findTDsMirrorById(final Integer tDsMirrorId) {
		LOGGER.info("find TDsMirror instance with dsMirrorId: " + tDsMirrorId);
		return genericDAO.get(clazz, tDsMirrorId);
	}

	/**
	 * Retrieve TDsMirror based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TDsMirror> list of TDsMirror if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TDsMirror> findTDsMirrors(final SearchFilter<TDsMirror> searchFilter) {
		LOGGER.info("=========== Find TDsMirrors ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TDsMirror tDsMirror = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tDsMirrorentity", tDsMirror);

		if (tDsMirror.getTDsStgByDsId() == null) {
			jpaQuery.bind(TDSSTGBYDSID, new TDsStg());
		} else {
			LOGGER.info("tDsStgByDsId {}"+ tDsMirror.getTDsStgByDsId());

			jpaQuery.bind(TDSSTGBYDSID, tDsMirror.getTDsStgByDsId());
		}

		if (tDsMirror.getTDsStgByMirrorDsId() == null) {
			jpaQuery.bind(TDSSTGBYMIRRORDSID, new TDsStg());
		} else {
			LOGGER.info("tDsStgByMirrorDsId {}"+ tDsMirror.getTDsStgByMirrorDsId());

			jpaQuery.bind(TDSSTGBYMIRRORDSID, tDsMirror.getTDsStgByMirrorDsId());
		}
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TDsMirrors.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTDsMirrors(final SearchFilter<TDsMirror> searchFilter) {
		LOGGER.info("=========== Count TDsMirror ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TDsMirror tDsMirror = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tDsMirrorentity", tDsMirror);

		if (tDsMirror.getTDsStgByDsId() == null) {
			jpaCountQuery.bind(TDSSTGBYDSID, new TDsStg());
		} else {
			LOGGER.info("tDsStgByDsId {}"+ tDsMirror.getTDsStgByDsId());

			jpaCountQuery.bind(TDSSTGBYDSID, tDsMirror.getTDsStgByDsId());
		}

		if (tDsMirror.getTDsStgByMirrorDsId() == null) {
			jpaCountQuery.bind(TDSSTGBYMIRRORDSID, new TDsStg());
		} else {
			LOGGER.info("tDsStgByMirrorDsId {}"+ tDsMirror.getTDsStgByMirrorDsId());

			jpaCountQuery.bind(TDSSTGBYMIRRORDSID, tDsMirror.getTDsStgByMirrorDsId());
		}

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TDsMirror based on given search criteria using JPA named Query.
	 * The search criteria is of TDsStgByDsId type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TDsMirror> list of TDsMirrors if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TDsMirror> getTDsMirrorsByTDsStgByDsId(final SearchFilter<TDsStg> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TDsStg tDsStg = searchFilter.getEntityClass();
		List<Object> tDsStgList = new ArrayList<Object>();
		tDsStgList.add(tDsStg);
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTDsMirrorByTDsStgByDsId", tDsStgList, index, maxresult);
	}

	/**
	 * Count TDsMirror based on given search criteria using JPA named Query.
	 * The search criteria is of TDsStg type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTDsMirrorsByTDsStgByDsId(final SearchFilter<TDsStg> searchFilter) {

		final TDsStg tDsStg = searchFilter.getEntityClass();
		List<Object> tDsStgList = new ArrayList<Object>();
		tDsStgList.add(tDsStg);
		return genericDAO.findEntitiesByNamedQuery("CountTDsMirrorsByTDsStgByDsId", tDsStgList);
	}

	/**
	 * Retrieve TDsMirror based on given search criteria using JPA named Query.
	 * The search criteria is of TDsStgByMirrorDsId type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TDsMirror> list of TDsMirrors if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TDsMirror> getTDsMirrorsByTDsStgByMirrorDsId(final SearchFilter<TDsStg> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TDsStg tDsStg = searchFilter.getEntityClass();
		List<Object> tDsStgList = new ArrayList<Object>();
		tDsStgList.add(tDsStg);
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTDsMirrorByTDsStgByMirrorDsId", tDsStgList, index, maxresult);
	}

	/**
	 * Count TDsMirror based on given search criteria using JPA named Query.
	 * The search criteria is of TDsStg type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTDsMirrorsByTDsStgByMirrorDsId(final SearchFilter<TDsStg> searchFilter) {

		final TDsStg tDsStg = searchFilter.getEntityClass();
		List<Object> tDsStgList = new ArrayList<Object>();
		tDsStgList.add(tDsStg);
		return genericDAO.findEntitiesByNamedQuery("CountTDsMirrorsByTDsStgByMirrorDsId", tDsStgList);
	}

}
