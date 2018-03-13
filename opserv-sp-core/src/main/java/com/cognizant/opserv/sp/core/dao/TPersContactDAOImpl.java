package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TPers;
import com.cognizant.opserv.sp.core.entity.TPersContact;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TPersContactDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tPersContactDAO")
public class TPersContactDAOImpl implements TPersContactDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TPersContactDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;
	
	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private final Class<TPersContact> clazz;

	public Class<TPersContact> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TPersContactDAOImpl() {
		super();
		this.clazz = TPersContact.class;
	}

	private static final String JPAQL = "select tPersContactentity from TPersContact tPersContactentity";

	private static final String JPACOUNTQL = "select count(*) from TPersContact tPersContactentity";

	private static final String[] RESTRICTIONS = {
			"tPersContactentity.staffId = #{tPersContactentity.getStaffId}",
			"tPersContactentity.persContactId = #{tPersContactentity.getPersContactId}",
			"tPersContactentity.contactTypeId = #{tPersContactentity.getContactTypeId}",
			"tPersContactentity.contactDetail like '#{tPersContactentity.getContactDetail}%'",
			"tPersContactentity.createdBy = #{tPersContactentity.getCreatedBy}",
			"Date(tPersContactentity.createDt) = '#{tPersContactentity.getCreateDt}'",
			"tPersContactentity.updatedBy = #{tPersContactentity.getUpdatedBy}",
			"Date(tPersContactentity.updateDt) = '#{tPersContactentity.getUpdateDt}'",
			"tPersContactentity.tenantId = #{tPersContactentity.getTenantId}",
			"tPersContactentity.contactExtn like '#{tPersContactentity.getContactExtn}%'",
			"tPersContactentity.tPers.staffId = #{tPersContactentity.tPers.getStaffId}" };

	/**
	 * Stores a new TPersContact entity object in to the persistent store
	 * 
	 * @param tPersContact
	 *            TPersContact Entity object to be persisted
	 * @return tPersContact Persisted TPersContact object
	 */
	public TPersContact createTPersContact(final TPersContact tPersContact) {
		LOGGER.info("=========== Create TPersContact ===========");
		return genericDAO.store(tPersContact);
	}

	/**
	 * Deletes a TPersContact entity object from the persistent store
	 * 
	 * @param tPersContact
	 *            TPersContact Entity object to be deleted
	 */
/*	public void deleteTPersContact(final TPersContactId tPersContactId) {
		LOGGER.info("=========== Delete TPersContact ===========");
		final TPersContact tPersContact = genericDAO.get(clazz, tPersContactId);
		genericDAO.remove(tPersContact);
	}*/

	/**
	 * Updates a TPersContact entity object in to the persistent store
	 * 
	 * @param tPersContact
	 *            TPersContact Entity object to be updated
	 * @return tPersContact Persisted TPersContact object
	 */
	public TPersContact updateTPersContact(final TPersContact tPersContact) {
		LOGGER.info("=========== Update TPersContact ===========");
		return genericDAO.update(tPersContact);
	}

	/**
	 * Retrieve an TPersContact object based on given TPersContact TPersContactId.
	 * 
	 * @param tPersContactId
	 *            the primary key value of the TPersContact Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TPersContact findTPersContactById(final Integer persContactId) {
		LOGGER.info("find TPersContact instance with persContactId: " + persContactId);
		return genericDAO.get(clazz, persContactId);
	}

	/**
	 * Retrieve TPersContact based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TPersContact> list of TPersContact if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TPersContact> findTPersContacts(final SearchFilter<TPersContact> searchFilter) {
		LOGGER.info("=========== Find TPersContacts ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TPersContact tPersContact = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tPersContactentity", tPersContact);

		/*if (tPersContact.getTPers() == null) {
			jpaQuery.bind(TPERS, new TPers());
		} else {
			LOGGER.info("tPers {}", tPersContact.getTPers());

			jpaQuery.bind(TPERS, tPersContact.getTPers());
		}*/
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TPersContacts.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTPersContacts(final SearchFilter<TPersContact> searchFilter) {
		LOGGER.info("=========== Count TPersContact ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TPersContact tPersContact = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tPersContactentity", tPersContact);

		/*if (tPersContact.getTPers() == null) {
			jpaCountQuery.bind(TPERS, new TPers());
		} else {
			LOGGER.info("tPers {}", tPersContact.getTPers());

			jpaCountQuery.bind(TPERS, tPersContact.getTPers());
		}*/

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TPersContact based on given search criteria using JPA named Query.
	 * The search criteria is of TPers type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TPersContact> list of TPersContacts if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TPersContact> getTPersContactsByTPers(final SearchFilter<TPers> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TPers tPers = searchFilter.getEntityClass();
		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(tPers.getStaffId());
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTPersContactByTPers", queryParam, index, maxresult);
	}

	/**
	 * Count TPersContact based on given search criteria using JPA named Query.
	 * The search criteria is of TPers type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTPersContactsByTPers(final SearchFilter<TPers> searchFilter) {

		final TPers tPers = searchFilter.getEntityClass();
		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(tPers.getStaffId());
		return genericDAO.findEntitiesByNamedQuery("CountTPersContactsByTPers", queryParam);
	}

	@Override
	public List<Integer> findMaxTPersContact() {
		
		List<Integer> max = genericDAO.findEntitiesByNamedQuery("findMaxTPersContact");
		
		return max;
	}

	@Override
	public List<TPersContact> getTPersContactsByTPersusingStaffId(
			SearchFilter<TPersContact> searchFilter) {
		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TPersContact tPersContact = searchFilter.getEntityClass();
		final int staffId = tPersContact.getStaffId();
		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(staffId);
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTPersContactByStaffId", queryParam, index, maxresult);
		
	}

	@Override
	public List<TPersContact> findTpersContactByStaffId(Integer contactTypeId, String prConatactFlag, Character flag,
			Integer StaffId, Short tenantId) {
		List paramList = new ArrayList();
		paramList.add(contactTypeId);
		paramList.add(StaffId);
		paramList.add(prConatactFlag);
		paramList.add(flag);
		paramList.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTPersContactByStaffAndType", paramList, 0, -1);
	}
	public List<TPersContact> getTPersContactsByStaffId(Integer staffId, Short tenantId){
		
		List paramList = new ArrayList();
		paramList.add(staffId);
		//paramList.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTPersContactByStaffId", paramList, 0, -1);
	}
	
	/* (non-Javadoc)
	 * @see com.cognizant.opserv.sp.core.dao.TPersContactDAO#getTPersContactDetailsByStaffId(java.lang.Integer)
	 */
	@Override
	public List<Object[]>  getTPersContactDetailsByStaffId(Integer staffId){
		List<Object> paramList=new ArrayList<Object> ();
		paramList.add(staffId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTPersContactDetailsByStaffId",paramList,0,-1);
		
	}
	
	
	@Override
	public List<TPersContact> getTPersContactsByStaffIds(
			Set<Integer> staffIdList, Short tenantId) {
		List paramList = new ArrayList();
		paramList.add(staffIdList);
		paramList.add(tenantId);		
		return genericDAO.findEntitiesByNamedQueryMultiCond("getTPersContactsByStaffIds",paramList,0,-1);
	}
	
	/**
	 * Find t pers by email.
	 *
	 * @param staffId the staff id
	 * @param contactTypeId the contact type id
	 * @return the list
	 */

	@Override
	public List<String> FindTPersEmailByStaffId(List<Integer> staffId,Integer contactTypeId) {
		List<Object> params = new ArrayList<Object>();
		params.add(staffId);
		params.add(contactTypeId);
		return genericDAO.findEntitiesByNamedQuery("FindTPersEmailByStaffId",params);
	}
	
}
