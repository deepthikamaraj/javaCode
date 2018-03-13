package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TOrg;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TOrgDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tOrgDAO")
public class TOrgDAOImpl implements TOrgDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TOrgDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private final Class<TOrg> clazz;

	public Class<TOrg> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TOrgDAOImpl() {
		super();
		this.clazz = TOrg.class;
	}

	private static final String JPAQL = "select tOrgentity from TOrg tOrgentity";

	private static final String JPACOUNTQL = "select count(tOrgentity) from TOrg tOrgentity";

	private static final String TORG = "tOrg";
	private static final String[] RESTRICTIONS = { "tOrgentity.orgId = #{tOrgentity.getOrgId}",
			"tOrgentity.orgName like '#{tOrgentity.getOrgName}%'", "tOrgentity.createdBy = #{tOrgentity.getCreatedBy}",
			"Date(tOrgentity.createDt) = '#{tOrgentity.getCreateDt}'",
			"tOrgentity.updatedBy = #{tOrgentity.getUpdatedBy}",
			"Date(tOrgentity.updateDt) = '#{tOrgentity.getUpdateDt}'",
			"tOrgentity.tenantId = #{tOrgentity.getTenantId}" };

	/**
	 * Stores a new TOrg entity object in to the persistent store
	 * 
	 * @param tOrg
	 *            TOrg Entity object to be persisted
	 * @return tOrg Persisted TOrg object
	 */
	public TOrg createTOrg(final TOrg tOrg) {
		LOGGER.info("=========== Create TOrg ===========");
		return genericDAO.store(tOrg);
	}

	/**
	 * Deletes a TOrg entity object from the persistent store
	 * 
	 * @param tOrg
	 *            TOrg Entity object to be deleted
	 */
	public void deleteTOrg(final Integer orgId) {
		LOGGER.info("=========== Delete TOrg ===========");
		final TOrg tOrg = genericDAO.get(clazz, orgId);
		genericDAO.remove(tOrg);
	}

	/**
	 * Updates a TOrg entity object in to the persistent store
	 * 
	 * @param tOrg
	 *            TOrg Entity object to be updated
	 * @return tOrg Persisted TOrg object
	 */
	public TOrg updateTOrg(final TOrg tOrg) {
		LOGGER.info("=========== Update TOrg ===========");
		return genericDAO.update(tOrg);
	}

	/**
	 * Retrieve an TOrg object based on given TOrg orgId.
	 * 
	 * @param tOrgId
	 *            the primary key value of the TOrg Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TOrg findTOrgById(final Integer tOrgId) {
		LOGGER.info("find TOrg instance with orgId: " + tOrgId);
		return genericDAO.get(clazz, tOrgId);
	}

	/**
	 * Retrieve TOrg based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TOrg> list of TOrg if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TOrg> findTOrgs(final SearchFilter<TOrg> searchFilter) {
		LOGGER.info("=========== Find TOrgs ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TOrg tOrg = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tOrgentity", tOrg);
		if (tOrg.getTOrg() == null) {
			jpaQuery.bind(TORG, new TOrg());
		} else {
			LOGGER.info("tOrg {}", tOrg.getTOrg());

			jpaQuery.bind(TORG, tOrg.getTOrg());
		}
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TOrgs.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTOrgs(final SearchFilter<TOrg> searchFilter) {
		LOGGER.info("=========== Count TOrg ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TOrg tOrg = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tOrgentity", tOrg);
		if (tOrg.getTOrg() == null) {
			jpaCountQuery.bind(TORG, new TOrg());
		} else {
			LOGGER.info("tOrg {}", tOrg.getTOrg());

			jpaCountQuery.bind(TORG, tOrg.getTOrg());
		}
		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}
	
	@Override
	public TOrg findTOrgByTenantId(Short tenantId) {
		
		List<Object> object = new ArrayList<Object>();
		object.add(tenantId);
		
		List<TOrg> orgList  = genericDAO.findEntitiesByNamedQueryMultiCond("FindOrgByTenantId", object, 0, 1);		
		
		return orgList != null && orgList.size() > 0 ? orgList.get(0): null;
	}

}
