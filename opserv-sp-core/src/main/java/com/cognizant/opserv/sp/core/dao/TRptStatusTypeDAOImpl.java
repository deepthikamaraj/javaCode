package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TRptStatusType;
import com.cognizant.opserv.sp.core.entity.TRptStatusTypeId;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TRptStatusTypeDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tRptStatusTypeDAO")
public class TRptStatusTypeDAOImpl implements TRptStatusTypeDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TRptStatusTypeDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private final Class<TRptStatusType> clazz;

	public Class<TRptStatusType> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TRptStatusTypeDAOImpl() {
		super();
		this.clazz = TRptStatusType.class;
	}

	private static final String JPAQL = "select tRptStatusTypeentity from TRptStatusType tRptStatusTypeentity";

	private static final String JPACOUNTQL = "select count(tRptStatusTypeentity) from TRptStatusType tRptStatusTypeentity";

	private static final String[] RESTRICTIONS = {
			"tRptStatusTypeentity.tRptStatusTypeId.rptStatusTypeId = #{tRptStatusTypeentity.tRptStatusTypeId.getRptStatusTypeId}",
			"tRptStatusTypeentity.rptStatusName like '#{tRptStatusTypeentity.getRptStatusName}%'",
			"tRptStatusTypeentity.rptStatusDesc like '#{tRptStatusTypeentity.getRptStatusDesc}%'",
			"tRptStatusTypeentity.activeFlag = #{tRptStatusTypeentity.getActiveFlag}",
			"tRptStatusTypeentity.createdBy = #{tRptStatusTypeentity.getCreatedBy}",
			"Date(tRptStatusTypeentity.createDt) = '#{tRptStatusTypeentity.getCreateDt}'",
			"tRptStatusTypeentity.updatedBy = #{tRptStatusTypeentity.getUpdatedBy}",
			"Date(tRptStatusTypeentity.updateDate) = '#{tRptStatusTypeentity.getUpdateDate}'",
			"tRptStatusTypeentity.tenantId = #{tRptStatusTypeentity.getTenantId}" };

	/**
	 * Stores a new TRptStatusType entity object in to the persistent store
	 * 
	 * @param tRptStatusType
	 *            TRptStatusType Entity object to be persisted
	 * @return tRptStatusType Persisted TRptStatusType object
	 */
	public TRptStatusType createTRptStatusType(final TRptStatusType tRptStatusType) {
		LOGGER.info("=========== Create TRptStatusType ===========");
		return genericDAO.store(tRptStatusType);
	}

	/**
	 * Deletes a TRptStatusType entity object from the persistent store
	 * 
	 * @param tRptStatusType
	 *            TRptStatusType Entity object to be deleted
	 */
	public void deleteTRptStatusType(final Integer rptStatusTypeId) {
		LOGGER.info("=========== Delete TRptStatusType ===========");
		final TRptStatusType tRptStatusType = genericDAO.get(clazz, rptStatusTypeId);
		genericDAO.remove(tRptStatusType);
	}

	/**
	 * Updates a TRptStatusType entity object in to the persistent store
	 * 
	 * @param tRptStatusType
	 *            TRptStatusType Entity object to be updated
	 * @return tRptStatusType Persisted TRptStatusType object
	 */
	public TRptStatusType updateTRptStatusType(final TRptStatusType tRptStatusType) {
		LOGGER.info("=========== Update TRptStatusType ===========");
		return genericDAO.update(tRptStatusType);
	}

	/**
	 * Retrieve an TRptStatusType object based on given TRptStatusType rptStatusTypeId.
	 * 
	 * @param tRptStatusTypeId
	 *            the primary key value of the TRptStatusType Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TRptStatusType findTRptStatusTypeById(final TRptStatusTypeId tRptStatusTypeId) {
		LOGGER.info("find TRptStatusType instance with rptStatusTypeId: " + tRptStatusTypeId);
		return genericDAO.get(clazz, tRptStatusTypeId);
	}

	/**
	 * Retrieve TRptStatusType based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRptStatusType> list of TRptStatusType if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TRptStatusType> findTRptStatusTypes(final SearchFilter<TRptStatusType> searchFilter) {
		LOGGER.info("=========== Find TRptStatusTypes ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TRptStatusType tRptStatusType = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tRptStatusTypeentity", tRptStatusType);
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TRptStatusTypes.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTRptStatusTypes(final SearchFilter<TRptStatusType> searchFilter) {
		LOGGER.info("=========== Count TRptStatusType ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TRptStatusType tRptStatusType = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tRptStatusTypeentity", tRptStatusType);

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	@Override
	public TRptStatusType findTRptStatusTypeById(final Integer rptStatusTypeId,
			final Short tenantId) {
		LOGGER.info("=========== find TRptStatusTypeById ===========");
		TRptStatusType tRptStatusType = new TRptStatusType();
		List list = new ArrayList();
		list.add(rptStatusTypeId);
		list.add(tenantId);	
		List<TRptStatusType> tRptStTypeList= genericDAO.findEntitiesByNamedQueryMultiCond("FindAllTRptStatusTypesById", list,0,-1);
		if(tRptStTypeList!= null){
			tRptStatusType = tRptStTypeList.get(0);
		}
		return tRptStatusType;
	}

}
