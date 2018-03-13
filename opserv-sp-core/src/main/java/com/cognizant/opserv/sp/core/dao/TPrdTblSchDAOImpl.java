package com.cognizant.opserv.sp.core.dao;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TPrdTblSch;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TPrdTblSchDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tPrdTblSchDAO")
public class TPrdTblSchDAOImpl implements TPrdTblSchDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TPrdTblSchDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private final Class<TPrdTblSch> clazz;

	public Class<TPrdTblSch> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TPrdTblSchDAOImpl() {
		super();
		this.clazz = TPrdTblSch.class;
	}

	private static final String JPAQL = "select tPrdTblSchentity from TPrdTblSch tPrdTblSchentity";

	private static final String JPACOUNTQL = "select count(tPrdTblSchentity) from TPrdTblSch tPrdTblSchentity";

	private static final String[] RESTRICTIONS = { "tPrdTblSchentity.tblId = #{tPrdTblSchentity.getTblId}",
			"tPrdTblSchentity.tblName like '#{tPrdTblSchentity.getTblName}%'",
			"tPrdTblSchentity.colName like '#{tPrdTblSchentity.getColName}%'",
			"tPrdTblSchentity.colAlias like '#{tPrdTblSchentity.getColAlias}%'",
			"tPrdTblSchentity.dataType like '#{tPrdTblSchentity.getDataType}%'",
			"tPrdTblSchentity.keyCol = #{tPrdTblSchentity.getKeyCol}",
			"tPrdTblSchentity.tblAlias like '#{tPrdTblSchentity.getTblAlias}%'",
			"tPrdTblSchentity.nullOption = #{tPrdTblSchentity.getNullOption}" };

	/**
	 * Stores a new TPrdTblSch entity object in to the persistent store
	 * 
	 * @param tPrdTblSch
	 *            TPrdTblSch Entity object to be persisted
	 * @return tPrdTblSch Persisted TPrdTblSch object
	 */
	public TPrdTblSch createTPrdTblSch(final TPrdTblSch tPrdTblSch) {
		LOGGER.info("=========== Create TPrdTblSch ===========");
		return genericDAO.store(tPrdTblSch);
	}

	/**
	 * Deletes a TPrdTblSch entity object from the persistent store
	 * 
	 * @param tPrdTblSch
	 *            TPrdTblSch Entity object to be deleted
	 */
	public void deleteTPrdTblSch(final Integer tblId) {
		LOGGER.info("=========== Delete TPrdTblSch ===========");
		final TPrdTblSch tPrdTblSch = genericDAO.get(clazz, tblId);
		genericDAO.remove(tPrdTblSch);
	}

	/**
	 * Updates a TPrdTblSch entity object in to the persistent store
	 * 
	 * @param tPrdTblSch
	 *            TPrdTblSch Entity object to be updated
	 * @return tPrdTblSch Persisted TPrdTblSch object
	 */
	public TPrdTblSch updateTPrdTblSch(final TPrdTblSch tPrdTblSch) {
		LOGGER.info("=========== Update TPrdTblSch ===========");
		return genericDAO.update(tPrdTblSch);
	}

	/**
	 * Retrieve an TPrdTblSch object based on given TPrdTblSch tblId.
	 * 
	 * @param tPrdTblSchId
	 *            the primary key value of the TPrdTblSch Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TPrdTblSch findTPrdTblSchById(final Integer tPrdTblSchId) {
		LOGGER.info("find TPrdTblSch instance with tblId: " + tPrdTblSchId);
		return genericDAO.get(clazz, tPrdTblSchId);
	}

	/**
	 * Retrieve TPrdTblSch based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TPrdTblSch> list of TPrdTblSch if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TPrdTblSch> findTPrdTblSchs(final SearchFilter<TPrdTblSch> searchFilter) {
		LOGGER.info("=========== Find TPrdTblSchs ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TPrdTblSch tPrdTblSch = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tPrdTblSchentity", tPrdTblSch);
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TPrdTblSchs.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTPrdTblSchs(final SearchFilter<TPrdTblSch> searchFilter) {
		LOGGER.info("=========== Count TPrdTblSch ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TPrdTblSch tPrdTblSch = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tPrdTblSchentity", tPrdTblSch);

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}
	
	/**
	 * Retrieve All TPrdTblSch using JPA named Query.
	 *  
	 * @return List<TPrdTblSch> list of TPrdTblSch if it exists. 
	 * Returns null if not found
	 * 
	 */
	public List<TPrdTblSch> getAllTPrdTblSch() {

		List<TPrdTblSch> prdTblSchList = genericDAO.findEntitiesByNamedQuery("FindAllTPrdTblSchs");
		return prdTblSchList;
	}
	
	public List<TPrdTblSch> findTPrdTblSchByTenantId(final SearchFilter<TPrdTblSch> searchFilter){
		final TPrdTblSch tPrdTblSch = searchFilter.getEntityClass();
		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(tPrdTblSch.getTenantId());
		List<TPrdTblSch> tPrdTblSchList = genericDAO.findEntitiesByNamedQuery("FindTPrdTblSchByTenantId", queryParam);
		return tPrdTblSchList;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<TPrdTblSch> findTPrdTblSchByTblAlias(
			SearchFilter<TPrdTblSch> searchFilter) {
		int index = searchFilter.getPaginationInfo().getStartIndex();
		int maxresult = searchFilter.getPaginationInfo().getMaxRows();
		final TPrdTblSch tPrdTblSch = searchFilter.getEntityClass();
		final Short tenantId = tPrdTblSch.getTenantId();
		final String targetTblAlias = tPrdTblSch.getTblAlias();
		@SuppressWarnings("rawtypes")
		List paramList = new ArrayList<>();
		paramList.add(tenantId);
		paramList.add(targetTblAlias);
		List<TPrdTblSch> tPrdTblSchList = genericDAO.findEntitiesByNamedQueryMultiCond("FindTPrdTblSchByTblAlias", paramList, index, maxresult);
		return tPrdTblSchList;
	}


}
