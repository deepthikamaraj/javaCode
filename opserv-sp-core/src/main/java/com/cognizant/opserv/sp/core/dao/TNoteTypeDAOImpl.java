package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TNoteType;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TNoteTypeDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tNoteTypeDAO")
public class TNoteTypeDAOImpl implements TNoteTypeDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TNoteTypeDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private final Class<TNoteType> clazz;

	public Class<TNoteType> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TNoteTypeDAOImpl() {
		super();
		this.clazz = TNoteType.class;
	}

	private static final String JPAQL = "select tNoteTypeentity from TNoteType tNoteTypeentity";

	private static final String JPACOUNTQL = "select count(tNoteTypeentity) from TNoteType tNoteTypeentity";

	private static final String[] RESTRICTIONS = { "tNoteTypeentity.noteTypeId = #{tNoteTypeentity.getNoteTypeId}",
			"tNoteTypeentity.noteTypeName like '#{tNoteTypeentity.getNoteTypeName}%'",
			"tNoteTypeentity.noteTypeDesc like '#{tNoteTypeentity.getNoteTypeDesc}%'",
			"tNoteTypeentity.activeFlag = #{tNoteTypeentity.getActiveFlag}",
			"tNoteTypeentity.createdBy = #{tNoteTypeentity.getCreatedBy}",
			"Date(tNoteTypeentity.createDt) = '#{tNoteTypeentity.getCreateDt}'",
			"tNoteTypeentity.updatedBy = #{tNoteTypeentity.getUpdatedBy}",
			"Date(tNoteTypeentity.updateDate) = '#{tNoteTypeentity.getUpdateDate}'",
			"tNoteTypeentity.tNoteTypeId.localeId = '#{tNoteTypeentity.tNoteTypeId.getLocaleId}'",
			"tNoteTypeentity.tenantId = #{tNoteTypeentity.getTenantId}" };

	/**
	 * Stores a new TNoteType entity object in to the persistent store
	 * 
	 * @param tNoteType
	 *            TNoteType Entity object to be persisted
	 * @return tNoteType Persisted TNoteType object
	 */
	public TNoteType createTNoteType(final TNoteType tNoteType) {
		LOGGER.info("=========== Create TNoteType ===========");
		return genericDAO.store(tNoteType);
	}

	/**
	 * Deletes a TNoteType entity object from the persistent store
	 * 
	 * @param tNoteType
	 *            TNoteType Entity object to be deleted
	 */
	public void deleteTNoteType(final Integer noteTypeId) {
		LOGGER.info("=========== Delete TNoteType ===========");
		final TNoteType tNoteType = genericDAO.get(clazz, noteTypeId);
		genericDAO.remove(tNoteType);
	}

	/**
	 * Updates a TNoteType entity object in to the persistent store
	 * 
	 * @param tNoteType
	 *            TNoteType Entity object to be updated
	 * @return tNoteType Persisted TNoteType object
	 */
	public TNoteType updateTNoteType(final TNoteType tNoteType) {
		LOGGER.info("=========== Update TNoteType ===========");
		return genericDAO.update(tNoteType);
	}

	/**
	 * Retrieve an TNoteType object based on given TNoteType noteTypeId.
	 * 
	 * @param tNoteTypeId
	 *            the primary key value of the TNoteType Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TNoteType findTNoteTypeById(final Integer tNoteTypeId) {
		LOGGER.info("find TNoteType instance with noteTypeId: " + tNoteTypeId);
		return genericDAO.get(clazz, tNoteTypeId);
	}

	/**
	 * Retrieve TNoteType based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TNoteType> list of TNoteType if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TNoteType> findTNoteTypes(final SearchFilter<TNoteType> searchFilter) {
		LOGGER.info("=========== Find TNoteTypes ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TNoteType tNoteType = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tNoteTypeentity", tNoteType);
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		jpaQuery.setCacheable(true);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TNoteTypes.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTNoteTypes(final SearchFilter<TNoteType> searchFilter) {
		LOGGER.info("=========== Count TNoteType ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TNoteType tNoteType = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tNoteTypeentity", tNoteType);

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}
	
	/*
	 * Added  By 407986 To fetch All the TNoteTypes
	 * */
	
	public List<TNoteType> fetchAllNoteTypes(){
		
		return genericDAO.findEntitiesByNamedQuery("FindAllTNoteTypes");
	}
	
	/*
	 * Added  By 407986 To fetch All the TNoteTypes
	 * */
	
	public List<TNoteType> fetchAllNoteTypes(Short tenantId){
		
		List<Object> queryParam = new ArrayList<Object>();
		queryParam.add(tenantId);
		
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindAllTNoteTypesByTenant", queryParam, 0, -1);
	}
	
	public List<TNoteType> FindTNoteTypesByNoteTypeId(Integer noteTypeId,short tenantId){		
		List<Object> queryParam = new ArrayList<Object>();
		queryParam.add(noteTypeId);
		queryParam.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTNoteTypesByNoteTypeId", queryParam, 0, -1);
	}

}
