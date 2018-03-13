package com.cognizant.opserv.sp.core.dao;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TChngReqApprHier;
import com.cognizant.opserv.sp.core.entity.TChngReqApprHierId;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TChngReqApprHierDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tChngReqApprHierDAO")
public class TChngReqApprHierDAOImpl implements TChngReqApprHierDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TChngReqApprHierDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private final Class<TChngReqApprHier> clazz;

	public Class<TChngReqApprHier> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TChngReqApprHierDAOImpl() {
		super();
		this.clazz = TChngReqApprHier.class;
	}

	private static final String JPAQL = "select tChngReqApprHierentity from TChngReqApprHier tChngReqApprHierentity";

	private static final String JPACOUNTQL = "select count(*) from TChngReqApprHier tChngReqApprHierentity";

	private static final String[] RESTRICTIONS = {
			"tChngReqApprHierentity.tChngReqApprHierId.apprSalesHierId = #{tChngReqApprHierentity.tChngReqApprHierId.getApprSalesHierId}",
			"tChngReqApprHierentity.tChngReqApprHierId.chngReqConfigId = #{tChngReqApprHierentity.tChngReqApprHierId.getChngReqConfigId}",
			"tChngReqApprHierentity.activeFlag = #{tChngReqApprHierentity.getActiveFlag}",
			"tChngReqApprHierentity.createdBy = #{tChngReqApprHierentity.getCreatedBy}",
			"Date(tChngReqApprHierentity.createDt) = '#{tChngReqApprHierentity.getCreateDt}'",
			"tChngReqApprHierentity.updatedBy = #{tChngReqApprHierentity.getUpdatedBy}",
			"Date(tChngReqApprHierentity.updateDt) = '#{tChngReqApprHierentity.getUpdateDt}'",
			"tChngReqApprHierentity.tenantId = #{tChngReqApprHierentity.getTenantId}" };

	/**
	 * Stores a new TChngReqApprHier entity object in to the persistent store
	 * 
	 * @param tChngReqApprHier
	 *            TChngReqApprHier Entity object to be persisted
	 * @return tChngReqApprHier Persisted TChngReqApprHier object
	 */
	public TChngReqApprHier createTChngReqApprHier(final TChngReqApprHier tChngReqApprHier) {
		LOGGER.info("=========== Create TChngReqApprHier ===========");
		return genericDAO.store(tChngReqApprHier);
	}

	/**
	 * Deletes a TChngReqApprHier entity object from the persistent store
	 * 
	 * @param tChngReqApprHier
	 *            TChngReqApprHier Entity object to be deleted
	 */
	public void deleteTChngReqApprHier(final TChngReqApprHierId tChngReqApprHierId) {
		LOGGER.info("=========== Delete TChngReqApprHier ===========");
		final TChngReqApprHier tChngReqApprHier = genericDAO.get(clazz, tChngReqApprHierId);
		genericDAO.remove(tChngReqApprHier);
	}

	/**
	 * Updates a TChngReqApprHier entity object in to the persistent store
	 * 
	 * @param tChngReqApprHier
	 *            TChngReqApprHier Entity object to be updated
	 * @return tChngReqApprHier Persisted TChngReqApprHier object
	 */
	public TChngReqApprHier updateTChngReqApprHier(final TChngReqApprHier tChngReqApprHier) {
		LOGGER.info("=========== Update TChngReqApprHier ===========");
		return genericDAO.update(tChngReqApprHier);
	}

	/**
	 * Retrieve an TChngReqApprHier object based on given TChngReqApprHier TChngReqApprHierId.
	 * 
	 * @param tChngReqApprHierId
	 *            the primary key value of the TChngReqApprHier Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TChngReqApprHier findTChngReqApprHierById(final TChngReqApprHierId tChngReqApprHierId) {
		LOGGER.info("find TChngReqApprHier instance with TChngReqApprHierId: " + tChngReqApprHierId);
		return genericDAO.get(clazz, tChngReqApprHierId);
	}

	/**
	 * Retrieve TChngReqApprHier based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TChngReqApprHier> list of TChngReqApprHier if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TChngReqApprHier> findTChngReqApprHiers(final SearchFilter<TChngReqApprHier> searchFilter) {
		LOGGER.info("=========== Find TChngReqApprHiers ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TChngReqApprHier tChngReqApprHier = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tChngReqApprHierentity", tChngReqApprHier);
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TChngReqApprHiers.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTChngReqApprHiers(final SearchFilter<TChngReqApprHier> searchFilter) {
		LOGGER.info("=========== Count TChngReqApprHier ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TChngReqApprHier tChngReqApprHier = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tChngReqApprHierentity", tChngReqApprHier);

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}
	
	
	// Added by 407986 To fetch partial Sales Hierarchy Ids
	/**
	 * Fetch partial sales hier ids.
	 *
	 * @param chngreqConfigId the chngreq config id
	 * @param tenantId the tenant id
	 * @return the list
	 */
    public List<Object> fetchPartialSalesHierIds(Integer chngreqConfigId, short tenantId) {
           
           List<Object> queryParam=new ArrayList<Object>();
           queryParam.add(chngreqConfigId);
           queryParam.add(tenantId);
           return genericDAO.findEntitiesByNamedQueryMultiCond("FindPartialSalesHierarchyIds", queryParam, 0, -1);
    }
	/**
	 * Find sales hierarchy ids.
	 *
	 * @param chngreqConfigId the chngreq config id
	 * @param tenantId the tenant id
	 * @return the list
	 */
    public List<TChngReqApprHier> findSalesHierarchyIds(Integer chngreqConfigId,Short tenantId) {
        List<Object> queryParam=new ArrayList<Object>();
        queryParam.add(chngreqConfigId);
        queryParam.add(tenantId);
        return genericDAO.findEntitiesByNamedQueryMultiCond("FindSalesHierarchyIds", queryParam, 0, -1);
    }
	/**
	 * Find activ sales hier.
	 *
	 * @param chngreqConfigId the chngreq config id
	 * @param activeFlag the active flag
	 * @param tenantId the tenant id
	 * @return the list
	 */
    public List<Object> findActivSalesHier(Integer chngreqConfigId,Character activeFlag,Short tenantId) {
        List<Object> queryParam=new ArrayList<Object>();
        queryParam.add(chngreqConfigId);
        queryParam.add(activeFlag);
        queryParam.add(tenantId);
        return genericDAO.findEntitiesByNamedQueryMultiCond("FindActivSalesHier", queryParam, 0, -1);
    }
}
