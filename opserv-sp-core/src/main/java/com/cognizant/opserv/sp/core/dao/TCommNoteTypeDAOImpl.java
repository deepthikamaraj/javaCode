package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TComm;
import com.cognizant.opserv.sp.core.entity.TCommNoteType;
import com.cognizant.opserv.sp.core.entity.TCommNoteTypeId;
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
 * Class provides API implementation for TCommNoteTypeDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tCommNoteTypeDAO")
public class TCommNoteTypeDAOImpl implements TCommNoteTypeDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TCommNoteTypeDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	//private static final String TNOTETYPE = "tNoteType";
	private static final String TCOMM = "tComm";

	private final Class<TCommNoteType> clazz;
	
	public Class<TCommNoteType> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TCommNoteTypeDAOImpl() {
		super();
		this.clazz = TCommNoteType.class;
	}

	private static final String JPAQL = "select tCommNoteTypeentity from TCommNoteType tCommNoteTypeentity";

	private static final String JPACOUNTQL = "select count(*) from TCommNoteType tCommNoteTypeentity";
	

	private static final String[] RESTRICTIONS = {
			"tCommNoteTypeentity.tCommNoteTypeId.commId = #{tCommNoteTypeentity.tCommNoteTypeId.getCommId}",
			"tCommNoteTypeentity.tCommNoteTypeId.noteTypeId = #{tCommNoteTypeentity.tCommNoteTypeId.getNoteTypeId}",
			"tCommNoteTypeentity.tenantId = #{tCommNoteTypeentity.getTenantId}",
			"tCommNoteTypeentity.tNoteType.noteTypeId = #{tCommNoteTypeentity.tNoteType.getNoteTypeId}",
			"tCommNoteTypeentity.tComm.commId = #{tCommNoteTypeentity.tComm.getCommId}" };

	/**
	 * Stores a new TCommNoteType entity object in to the persistent store
	 * 
	 * @param tCommNoteType
	 *            TCommNoteType Entity object to be persisted
	 * @return tCommNoteType Persisted TCommNoteType object
	 */
	public TCommNoteType createTCommNoteType(final TCommNoteType tCommNoteType) {
		LOGGER.info("=========== Create TCommNoteType ===========");
		return genericDAO.store(tCommNoteType);
	}
	
	/**
	 * Deletes a TCommNoteType entity object from the persistent store
	 * 
	 * @param tCommNoteType
	 *            TCommNoteType Entity object to be deleted
	 */
	public void deleteTCommNoteType(final TCommNoteTypeId tCommNoteTypeId) {
		LOGGER.info("=========== Delete TCommNoteType ===========");
		final TCommNoteType tCommNoteType = genericDAO.get(clazz, tCommNoteTypeId);
		genericDAO.remove(tCommNoteType);
	}

	/**
	 * Deletes a TCommNoteType entity object from the persistent store
	 * 
	 * @param tCommNoteType
	 *            TCommNoteType Entity object to be deleted
	 */
	public TCommNoteType findTCommNoteTypeByTCommID(final TCommNoteTypeId tCommNoteTypeId) {
		LOGGER.info("=========== Delete TCommNoteType ===========");
		/*Long tCommID=tCommNoteTypeId.getCommId();*/
		TCommNoteType tCommNoteType =  null;        /*genericDAO.findEntitiesById(tCommID);*/
		/*genericDAO.remove(tCommNoteType);*/
		
		return tCommNoteType;
	}

	/**
	 * Updates a TCommNoteType entity object in to the persistent store
	 * 
	 * @param tCommNoteType
	 *            TCommNoteType Entity object to be updated
	 * @return tCommNoteType Persisted TCommNoteType object
	 */
	public TCommNoteType updateTCommNoteType(final TCommNoteType tCommNoteType) {
		LOGGER.info("=========== Update TCommNoteType ===========");
		return genericDAO.update(tCommNoteType);
	}

	/**
	 * Retrieve an TCommNoteType object based on given TCommNoteType TCommNoteTypeId.
	 * 
	 * @param tCommNoteTypeId
	 *            the primary key value of the TCommNoteType Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TCommNoteType findTCommNoteTypeById(final TCommNoteTypeId tCommNoteTypeId) {
		LOGGER.info("find TCommNoteType instance with TCommNoteTypeId: " + tCommNoteTypeId);
		return genericDAO.get(clazz, tCommNoteTypeId);
	}

	/**
	 * Retrieve TCommNoteType based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCommNoteType> list of TCommNoteType if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TCommNoteType> findTCommNoteTypes(final SearchFilter<TCommNoteType> searchFilter) {
		LOGGER.info("=========== Find TCommNoteTypes ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TCommNoteType tCommNoteType = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tCommNoteTypeentity", tCommNoteType);

		/*if (tCommNoteType.getTNoteType() == null) {
			jpaQuery.bind(TNOTETYPE, new TNoteType());
		} else {
			LOGGER.info("tNoteType {}", tCommNoteType.getTNoteType());

			jpaQuery.bind(TNOTETYPE, tCommNoteType.getTNoteType());
		}*/

		if (tCommNoteType.getTComm() == null) {
			jpaQuery.bind(TCOMM, new TComm());
		} else {
			LOGGER.info("tComm {}" + tCommNoteType.getTComm());

			jpaQuery.bind(TCOMM, tCommNoteType.getTComm());
		}
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TCommNoteTypes.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTCommNoteTypes(final SearchFilter<TCommNoteType> searchFilter) {
		LOGGER.info("=========== Count TCommNoteType ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TCommNoteType tCommNoteType = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tCommNoteTypeentity", tCommNoteType);

		/*if (tCommNoteType.getTNoteType() == null) {
			jpaCountQuery.bind(TNOTETYPE, new TNoteType());
		} else {
			LOGGER.info("tNoteType {}", tCommNoteType.getTNoteType());

			jpaCountQuery.bind(TNOTETYPE, tCommNoteType.getTNoteType());
		}*/

		if (tCommNoteType.getTComm() == null) {
			jpaCountQuery.bind(TCOMM, new TComm());
		} else {
			LOGGER.info("tComm {}" + tCommNoteType.getTComm());

			jpaCountQuery.bind(TCOMM, tCommNoteType.getTComm());
		}

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TCommNoteType based on given search criteria using JPA named Query.
	 * The search criteria is of TNoteType type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCommNoteType> list of TCommNoteTypes if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TCommNoteType> getTCommNoteTypesByTNoteType(final SearchFilter<TNoteType> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TNoteType tNoteType = searchFilter.getEntityClass();
		List<Object> tNoteTypeList = new ArrayList<Object>();
		tNoteTypeList.add(tNoteType);
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTCommNoteTypeByTNoteType", tNoteTypeList, index, maxresult);
	}

	/**
	 * Count TCommNoteType based on given search criteria using JPA named Query.
	 * The search criteria is of TNoteType type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTCommNoteTypesByTNoteType(final SearchFilter<TNoteType> searchFilter) {

		final TNoteType tNoteType = searchFilter.getEntityClass();
		List<Object> tNoteTypeList = new ArrayList<Object>();
		tNoteTypeList.add(tNoteType);
		return genericDAO.findEntitiesByNamedQuery("CountTCommNoteTypesByTNoteType", tNoteTypeList);
	}

	/**
	 * Retrieve TCommNoteType based on given search criteria using JPA named Query.
	 * The search criteria is of TComm type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCommNoteType> list of TCommNoteTypes if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TCommNoteType> getTCommNoteTypesByTComm(final SearchFilter<TComm> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TComm tComm = searchFilter.getEntityClass();
		List<Object> tCommList = new ArrayList<Object>();
		tCommList.add(tComm);
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTCommNoteTypeByTComm", tCommList, index, maxresult);
	}

	/**
	 * Count TCommNoteType based on given search criteria using JPA named Query.
	 * The search criteria is of TComm type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTCommNoteTypesByTComm(final SearchFilter<TComm> searchFilter) {

		final TComm tComm = searchFilter.getEntityClass();
		List<Object> tCommList = new ArrayList<Object>();
		tCommList.add(tComm);
		return genericDAO.findEntitiesByNamedQuery("CountTCommNoteTypesByTComm", tCommList);
	}
	/**
	 * Gets the notification details.
	 *
	 * @param searchFilter the search filter
	 * @return the notification details
	 */
	@Override
	public List<TCommNoteType> getNotificationDetails(
			SearchFilter<TCommNoteType> searchFilter) {
		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TCommNoteType tCommNoteType = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		List paramList = new ArrayList();
		paramList.add(tCommNoteType.getTComm().getValidityStartDt());
		paramList.add(tCommNoteType.getTCommNoteTypeId().getNoteTypeId());
		paramList.add(tCommNoteType.getTComm().getTCommStatus().getCommStatusId());
	
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTCommNoteTypeByTCommAndNoteType", paramList, index, maxresult);
		
	}


	/*@Override
	public TCommNoteType findEntitiesById(final Long tCommID) {
		
				LOGGER.info("=========== findEntities ===========");
				return getJpaTemplate().execute(new JpaCallback() {
					public Object doInJpa(final EntityManager entityMgr) throws PersistenceException {
						
				final Query namedQuery = entityMgr.createQuery("select myTCommNoteType from TCommNoteType myTCommNoteType where myTCommNoteType.tComm = "+tCommID);
				List<TCommNoteType> list=namedQuery.getResultList();
				TCommNoteType tcommNoteType=new TCommNoteType();
			if(list !=null && list.size()>0) {
				TCommNoteTypeId tCommNoteTypeId = new TCommNoteTypeId();
				
				 tCommNoteTypeId.setNoteTypeId(list.get(0).getTCommNoteTypeId().getNoteTypeId());
				 tCommNoteTypeId.setCommId(list.get(0).getTCommNoteTypeId().getCommId());
				 tcommNoteType.setTCommNoteTypeId(tCommNoteTypeId);
				tcommNoteType.setTenantId(list.get(0).getTenantId());
				 updateTCommNoteTypeById(tcommNoteType);
			}
				return tcommNoteType;
			}
		});
				
	}

	
	@Override
	public TCommNoteType updateTCommNoteTypeById(final TCommNoteType tCommNoteType) {
		LOGGER.info("=========== findEntities ===========");
		return getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(final EntityManager entityMgr) throws PersistenceException {
				EntityTransaction tx = entityMgr.getTransaction();
		final Query query = entityMgr.createQuery("delete from TCommNoteType myTCommNoteType where myTCommNoteType.tComm = "+tCommNoteType.getTCommNoteTypeId().getCommId());
		query.executeUpdate();
		tx.commit();
		return null;
	}
});
	}*/

	/**
	 * Find t comm note type by comm id.
	 *
	 * @param commId the comm id
	 * @param tenantId the tenant id
	 * @return the list
	 */
	public List<TCommNoteType> findTCommNoteTypeByCommID(Long commId, Short tenantId) {
		//final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		//final TCommNoteType tCommNoteType = searchFilter.getEntityClass();
		//final int maxresult = paginationInfo.getMaxRows();
		//final int index = paginationInfo.getStartIndex();
		List paramList = new ArrayList();
		paramList.add(commId);
		paramList.add(tenantId);
	//	paramList.add(tCommNoteType.getTComm().getTCommStatus().getCommStatusId());
	
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTCommNoteTypeByCommID", paramList, 0, -1);
		
	}


}
