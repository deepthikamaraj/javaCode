package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TChngReqTrack;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TChngReqTrackDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tChngReqTrackDAO")
public class TChngReqTrackDAOImpl implements TChngReqTrackDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(TChngReqTrackDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private final Class<TChngReqTrack> clazz;

	public Class<TChngReqTrack> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TChngReqTrackDAOImpl() {
		super();
		this.clazz = TChngReqTrack.class;
	}

	private static final String JPAQL = "select tChngReqTrackentity from TChngReqTrack tChngReqTrackentity";

	private static final String JPACOUNTQL = "select count(tChngReqTrackentity) from TChngReqTrack tChngReqTrackentity";

	private static final String[] RESTRICTIONS = {
			"tChngReqTrackentity.chngReqTrackId = #{tChngReqTrackentity.getChngReqTrackId}",
			"tChngReqTrackentity.activeFlag = #{tChngReqTrackentity.getActiveFlag}",
			"tChngReqTrackentity.createdBy = #{tChngReqTrackentity.getCreatedBy}",
			"Date(tChngReqTrackentity.createDt) = '#{tChngReqTrackentity.getCreateDt}'",
			"tChngReqTrackentity.updatedBy = #{tChngReqTrackentity.getUpdatedBy}",
			"Date(tChngReqTrackentity.updateDt) = '#{tChngReqTrackentity.getUpdateDt}'",
			"tChngReqTrackentity.tenantId = #{tChngReqTrackentity.getTenantId}",
			"tChngReqTrackentity.chngReqMode like '#{tChngReqTrackentity.getChngReqMode}%'",
			"Date(tChngReqTrackentity.submitDtTm) = '#{tChngReqTrackentity.getSubmitDtTm}'" };

	/**
	 * Stores a new TChngReqTrack entity object in to the persistent store
	 * 
	 * @param tChngReqTrack
	 *            TChngReqTrack Entity object to be persisted
	 * @return tChngReqTrack Persisted TChngReqTrack object
	 */
	public TChngReqTrack createTChngReqTrack(final TChngReqTrack tChngReqTrack) {
		LOGGER.info("=========== Create TChngReqTrack ===========");
		return genericDAO.store(tChngReqTrack);
	}

	/**
	 * Deletes a TChngReqTrack entity object from the persistent store
	 * 
	 * @param tChngReqTrack
	 *            TChngReqTrack Entity object to be deleted
	 */
	public void deleteTChngReqTrack(final Integer chngReqTrackId) {
		LOGGER.info("=========== Delete TChngReqTrack ===========");
		final TChngReqTrack tChngReqTrack = genericDAO.get(clazz, chngReqTrackId);
		genericDAO.remove(tChngReqTrack);
	}

	/**
	 * Updates a TChngReqTrack entity object in to the persistent store
	 * 
	 * @param tChngReqTrack
	 *            TChngReqTrack Entity object to be updated
	 * @return tChngReqTrack Persisted TChngReqTrack object
	 */
	public TChngReqTrack updateTChngReqTrack(final TChngReqTrack tChngReqTrack) {
		LOGGER.info("=========== Update TChngReqTrack ===========");
		return genericDAO.update(tChngReqTrack);
	}

	/**
	 * Retrieve an TChngReqTrack object based on given TChngReqTrack chngReqTrackId.
	 * 
	 * @param tChngReqTrackId
	 *            the primary key value of the TChngReqTrack Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TChngReqTrack findTChngReqTrackById(final Integer tChngReqTrackId) {
		LOGGER.info("find TChngReqTrack instance with chngReqTrackId: " + tChngReqTrackId);
		return genericDAO.get(clazz, tChngReqTrackId);
	}

	/**
	 * Retrieve TChngReqTrack based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TChngReqTrack> list of TChngReqTrack if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TChngReqTrack> findTChngReqTracks(final SearchFilter<TChngReqTrack> searchFilter) {
		LOGGER.info("=========== Find TChngReqTracks ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TChngReqTrack tChngReqTrack = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tChngReqTrackentity", tChngReqTrack);
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TChngReqTracks.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTChngReqTracks(final SearchFilter<TChngReqTrack> searchFilter) {
		LOGGER.info("=========== Count TChngReqTrack ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TChngReqTrack tChngReqTrack = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tChngReqTrackentity", tChngReqTrack);

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

}
