package com.cognizant.opserv.sp.core.dao;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TUsrPref;
import com.cognizant.opserv.sp.core.entity.TUsrPrefId;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TUsrPrefDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tUsrPrefDAO")
public class TUsrPrefDAOImpl implements TUsrPrefDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TUsrPrefDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	//private static final String TFEATURE = "tFeature";
	//private static final String TUSRROLE = "tUsrRole";

	private final Class<TUsrPref> clazz;

	public Class<TUsrPref> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TUsrPrefDAOImpl() {
		super();
		this.clazz = TUsrPref.class;
	}

	private static final String JPAQL = "select tUsrPrefentity from TUsrPref tUsrPrefentity";

	private static final String JPACOUNTQL = "select count(*) from TUsrPref tUsrPrefentity";

	private static final String[] RESTRICTIONS = {
			"tUsrPrefentity.tUsrPrefId.usrId = #{tUsrPrefentity.tUsrPrefId.getUsrId}",
			"tUsrPrefentity.tUsrPrefId.featureId = #{tUsrPrefentity.tUsrPrefId.getFeatureId}",
			"tUsrPrefentity.tUsrPrefId.roleId = #{tUsrPrefentity.tUsrPrefId.getRoleId}",
			"tUsrPrefentity.activeFlag = #{tUsrPrefentity.getActiveFlag}",
			"tUsrPrefentity.createdBy = #{tUsrPrefentity.getCreatedBy}",
			"Date(tUsrPrefentity.createDt) = '#{tUsrPrefentity.getCreateDt}'",
			"tUsrPrefentity.updatedBy = #{tUsrPrefentity.getUpdatedBy}",
			"Date(tUsrPrefentity.updateDt) = '#{tUsrPrefentity.getUpdateDt}'",
			"tUsrPrefentity.tenantId = #{tUsrPrefentity.getTenantId}",
			"tUsrPrefentity.featureId = #{tUsrPrefentity.getFeatureId}",
			"tUsrPrefentity.tUsrRole.tUsrRoleId = #{tUsrPrefentity.tUsrRole.getTUsrRoleId}" };

	/**
	 * Stores a new TUsrPref entity object in to the persistent store
	 * 
	 * @param tUsrPref
	 *            TUsrPref Entity object to be persisted
	 * @return tUsrPref Persisted TUsrPref object
	 */
	public TUsrPref createTUsrPref(final TUsrPref tUsrPref) {
		LOGGER.info("=========== Create TUsrPref ===========");
		return genericDAO.store(tUsrPref);
	}

	/**
	 * Deletes a TUsrPref entity object from the persistent store
	 * 
	 * @param tUsrPref
	 *            TUsrPref Entity object to be deleted
	 */
	public void deleteTUsrPref(final TUsrPrefId tUsrPrefId) {
		LOGGER.info("=========== Delete TUsrPref ===========");
		final TUsrPref tUsrPref = genericDAO.get(clazz, tUsrPrefId);
		genericDAO.remove(tUsrPref);
	}

	/**
	 * Updates a TUsrPref entity object in to the persistent store
	 * 
	 * @param tUsrPref
	 *            TUsrPref Entity object to be updated
	 * @return tUsrPref Persisted TUsrPref object
	 */
	public TUsrPref updateTUsrPref(final TUsrPref tUsrPref) {
		LOGGER.info("=========== Update TUsrPref ===========");
		return genericDAO.update(tUsrPref);
	}

	/**
	 * Retrieve an TUsrPref object based on given TUsrPref TUsrPrefId.
	 * 
	 * @param tUsrPrefId
	 *            the primary key value of the TUsrPref Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TUsrPref findTUsrPrefById(final TUsrPrefId tUsrPrefId) {
		LOGGER.info("find TUsrPref instance with TUsrPrefId: " + tUsrPrefId);
		return genericDAO.get(clazz, tUsrPrefId);
	}

	/**
	 * Retrieve TUsrPref based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TUsrPref> list of TUsrPref if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TUsrPref> findTUsrPrefs(final SearchFilter<TUsrPref> searchFilter) {
		LOGGER.info("=========== Find TUsrPrefs ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TUsrPref tUsrPref = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tUsrPrefentity", tUsrPref);

	/*	if (tUsrPref.getTFeature() == null) {
			jpaQuery.bind(TFEATURE, new TFeature());
		} else {
			LOGGER.info("tFeature {}", tUsrPref.getTFeature());

			jpaQuery.bind(TFEATURE, tUsrPref.getTFeature());
		}*/

		/*if (tUsrPref.getTUsrRole() == null) {
			jpaQuery.bind(TUSRROLE, new TUsrRole());
		} else {
			LOGGER.info("tUsrRole {}", tUsrPref.getTUsrRole());

			jpaQuery.bind(TUSRROLE, tUsrPref.getTUsrRole());
		}*/
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TUsrPrefs.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTUsrPrefs(final SearchFilter<TUsrPref> searchFilter) {
		LOGGER.info("=========== Count TUsrPref ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TUsrPref tUsrPref = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tUsrPrefentity", tUsrPref);

		/*if (tUsrPref.getTFeature() == null) {
			jpaCountQuery.bind(TFEATURE, new TFeature());
		} else {
			LOGGER.info("tFeature {}", tUsrPref.getTFeature());

			jpaCountQuery.bind(TFEATURE, tUsrPref.getTFeature());
		}*/

		/*if (tUsrPref.getTUsrRole() == null) {
			jpaCountQuery.bind(TUSRROLE, new TUsrRole());
		} else {
			LOGGER.info("tUsrRole {}", tUsrPref.getTUsrRole());

			jpaCountQuery.bind(TUSRROLE, tUsrPref.getTUsrRole());
		}*/

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TUsrPref based on given search criteria using JPA named Query.
	 * The search criteria is of TFeature type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TUsrPref> list of TUsrPrefs if it exists against given
	 *         criteria. Returns null if not found
	 */
	/*public List<TUsrPref> getTUsrPrefsByTFeature(final SearchFilter<TFeature> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TFeature tFeature = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQuery("FindTUsrPrefByTFeature", tFeature, index, maxresult);
	}
*/
	/**
	 * Count TUsrPref based on given search criteria using JPA named Query.
	 * The search criteria is of TFeature type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	/*public Object countTUsrPrefsByTFeature(final SearchFilter<TFeature> searchFilter) {

		final TFeature tFeature = searchFilter.getEntityClass();
		return genericDAO.findEntitiesByNamedQuery("CountTUsrPrefsByTFeature", tFeature);
	}*/

	

}
