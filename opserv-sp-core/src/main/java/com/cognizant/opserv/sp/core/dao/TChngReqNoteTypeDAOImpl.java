package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TChngReqNoteType;
import com.cognizant.opserv.sp.core.entity.TChngReqNoteTypeId;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TChngReqNoteTypeDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tChngReqNoteTypeDAO")
public class TChngReqNoteTypeDAOImpl implements TChngReqNoteTypeDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TChngReqNoteTypeDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private final Class<TChngReqNoteType> clazz;

	public Class<TChngReqNoteType> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TChngReqNoteTypeDAOImpl() {
		super();
		this.clazz = TChngReqNoteType.class;
	}

	private static final String JPAQL = "select tChngReqNoteTypeentity from TChngReqNoteType tChngReqNoteTypeentity";

	private static final String JPACOUNTQL = "select count(*) from TChngReqNoteType tChngReqNoteTypeentity";

	private static final String[] RESTRICTIONS = {
			"tChngReqNoteTypeentity.tChngReqNoteTypeId.chngReqConfigId = #{tChngReqNoteTypeentity.tChngReqNoteTypeId.getChngReqConfigId}",
			"tChngReqNoteTypeentity.tChngReqNoteTypeId.noteTypeId = #{tChngReqNoteTypeentity.tChngReqNoteTypeId.getNoteTypeId}",
			"tChngReqNoteTypeentity.activeFlag = #{tChngReqNoteTypeentity.getActiveFlag}",
			"tChngReqNoteTypeentity.createdBy = #{tChngReqNoteTypeentity.getCreatedBy}",
			"Date(tChngReqNoteTypeentity.createDt) = '#{tChngReqNoteTypeentity.getCreateDt}'",
			"tChngReqNoteTypeentity.updatedBy = #{tChngReqNoteTypeentity.getUpdatedBy}",
			"Date(tChngReqNoteTypeentity.updateDt) = '#{tChngReqNoteTypeentity.getUpdateDt}'",
			"tChngReqNoteTypeentity.tenantId = #{tChngReqNoteTypeentity.getTenantId}" };

	/**
	 * Stores a new TChngReqNoteType entity object in to the persistent store
	 * 
	 * @param tChngReqNoteType
	 *            TChngReqNoteType Entity object to be persisted
	 * @return tChngReqNoteType Persisted TChngReqNoteType object
	 */
	public TChngReqNoteType createTChngReqNoteType(final TChngReqNoteType tChngReqNoteType) {
		LOGGER.info("=========== Create TChngReqNoteType ===========");
		return genericDAO.store(tChngReqNoteType);
	}

	/**
	 * Deletes a TChngReqNoteType entity object from the persistent store
	 * 
	 * @param tChngReqNoteType
	 *            TChngReqNoteType Entity object to be deleted
	 */
	public void deleteTChngReqNoteType(final TChngReqNoteTypeId tChngReqNoteTypeId) {
		LOGGER.info("=========== Delete TChngReqNoteType ===========");
		final TChngReqNoteType tChngReqNoteType = genericDAO.get(clazz, tChngReqNoteTypeId);
		genericDAO.remove(tChngReqNoteType);
	}

	/**
	 * Updates a TChngReqNoteType entity object in to the persistent store
	 * 
	 * @param tChngReqNoteType
	 *            TChngReqNoteType Entity object to be updated
	 * @return tChngReqNoteType Persisted TChngReqNoteType object
	 */
	public TChngReqNoteType updateTChngReqNoteType(final TChngReqNoteType tChngReqNoteType) {
		LOGGER.info("=========== Update TChngReqNoteType ===========");
		return genericDAO.update(tChngReqNoteType);
	}

	/**
	 * Retrieve an TChngReqNoteType object based on given TChngReqNoteType TChngReqNoteTypeId.
	 * 
	 * @param tChngReqNoteTypeId
	 *            the primary key value of the TChngReqNoteType Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TChngReqNoteType findTChngReqNoteTypeById(final TChngReqNoteTypeId tChngReqNoteTypeId) {
		LOGGER.info("find TChngReqNoteType instance with TChngReqNoteTypeId: " + tChngReqNoteTypeId);
		return genericDAO.get(clazz, tChngReqNoteTypeId);
	}

	/**
	 * Retrieve TChngReqNoteType based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TChngReqNoteType> list of TChngReqNoteType if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TChngReqNoteType> findTChngReqNoteTypes(final SearchFilter<TChngReqNoteType> searchFilter) {
		LOGGER.info("=========== Find TChngReqNoteTypes ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TChngReqNoteType tChngReqNoteType = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tChngReqNoteTypeentity", tChngReqNoteType);
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TChngReqNoteTypes.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTChngReqNoteTypes(final SearchFilter<TChngReqNoteType> searchFilter) {
		LOGGER.info("=========== Count TChngReqNoteType ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TChngReqNoteType tChngReqNoteType = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tChngReqNoteTypeentity", tChngReqNoteType);

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}
	
	/*
	 * Added By 407986 To Fetch  Notification  Status Type Active Flag By noteTypeIds
	 * */
	/**
	 * Fetch note type status.
	 *
	 * @param configId the config id
	 * @param noteTypeId the note type id
	 * @param tenantId the tenant id
	 * @return the list
	 */
	public List<Object[]> fetchNoteTypeStatus(Integer configId,List<Integer> noteTypeId,Short tenantId){
		
		List<Object> queryParam=new ArrayList<Object>();
        queryParam.add(noteTypeId);
        queryParam.add(configId);
        queryParam.add(tenantId);
        return genericDAO.findEntitiesByNamedQueryMultiCond("FetchNoteTypeStatus", queryParam, 0, -1);
		
	}
	/**
	 * Find t chng req note type by cr config id.
	 *
	 * @param crConfigId the cr config id
	 * @param tenantId the tenant id
	 * @return the list
	 */
	@Override
	public List<TChngReqNoteType> findTChngReqNoteTypeByCRConfigId(Integer crConfigId, Short tenantId) {
		List<Object> queryParam=new ArrayList<Object>();
		queryParam.add(crConfigId);
		queryParam.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("findTChngReqNoteTypeByCRConfigId", queryParam, 0, -1);
	}


}
