package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TPers;
import com.cognizant.opserv.sp.core.entity.TUsrRptPref;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TUsrRptPrefDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tUsrRptPrefDAO")
public class TUsrRptPrefDAOImpl implements TUsrRptPrefDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TUsrRptPrefDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private static final String TUSRRPTPREF = "tUsrRptPref";
	private static final String TPERS = "tPers";

	private final Class<TUsrRptPref> clazz;

	public Class<TUsrRptPref> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TUsrRptPrefDAOImpl() {
		super();
		this.clazz = TUsrRptPref.class;
	}

	private static final String JPAQL = "select tUsrRptPrefentity from TUsrRptPref tUsrRptPrefentity";

	private static final String JPACOUNTQL = "select count(*) from TUsrRptPref tUsrRptPrefentity";

	private static final String[] RESTRICTIONS = {
			"tUsrRptPrefentity.prefId = #{tUsrRptPrefentity.getPrefId}",
			"tUsrRptPrefentity.staffId = #{tUsrRptPrefentity.getStaffId}",
			"tUsrRptPrefentity.parentFolderId = #{tUsrRptPrefentity.getParentFolderId}",
			"tUsrRptPrefentity.folderName like '#{tUsrRptPrefentity.getFolderName}%'",
			"tUsrRptPrefentity.activeFlag = #{tUsrRptPrefentity.getActiveFlag}",
			"tUsrRptPrefentity.folderDesc like '#{tUsrRptPrefentity.getFolderDesc}%'",
			"tUsrRptPrefentity.createdBy = #{tUsrRptPrefentity.getCreatedBy}",
			"Date(tUsrRptPrefentity.createDt) = '#{tUsrRptPrefentity.getCreateDt}'",
			"tUsrRptPrefentity.updatedBy = #{tUsrRptPrefentity.getUpdatedBy}",
			"Date(tUsrRptPrefentity.updateDate) = '#{tUsrRptPrefentity.getUpdateDate}'",
			"tUsrRptPrefentity.tenantId = #{tUsrRptPrefentity.getTenantId}",
			//"tUsrRptPrefentity.tUsrRptPref.tUsrRptPrefId = #{tUsrRptPrefentity.tUsrRptPref.getTUsrRptPrefId}",
			"tUsrRptPrefentity.tPers.staffId = #{tUsrRptPrefentity.tPers.getStaffId}" };

	/**
	 * Stores a new TUsrRptPref entity object in to the persistent store
	 * 
	 * @param tUsrRptPref
	 *            TUsrRptPref Entity object to be persisted
	 * @return tUsrRptPref Persisted TUsrRptPref object
	 */
	public TUsrRptPref createTUsrRptPref(final TUsrRptPref tUsrRptPref) {
		LOGGER.info("=========== Create TUsrRptPref ===========");
		return genericDAO.store(tUsrRptPref);
	}

	/**
	 * Deletes a TUsrRptPref entity object from the persistent store
	 * 
	 * @param tUsrRptPref
	 *            TUsrRptPref Entity object to be deleted
	 */
	public void deleteTUsrRptPref(final Integer prefId) {
		LOGGER.info("=========== Delete TUsrRptPref ===========");
		final TUsrRptPref tUsrRptPref = genericDAO.get(clazz, prefId);
		genericDAO.remove(tUsrRptPref);
	}
	/*public void deleteTUsrRptPref(final TUsrRptPrefId tUsrRptPrefId) {
		LOGGER.info("=========== Delete TUsrRptPref ===========");
		final TUsrRptPref tUsrRptPref = genericDAO.get(clazz, tUsrRptPrefId);
		genericDAO.remove(tUsrRptPref);
	}*/

	/**
	 * Updates a TUsrRptPref entity object in to the persistent store
	 * 
	 * @param tUsrRptPref
	 *            TUsrRptPref Entity object to be updated
	 * @return tUsrRptPref Persisted TUsrRptPref object
	 */
	public TUsrRptPref updateTUsrRptPref(final TUsrRptPref tUsrRptPref) {
		LOGGER.info("=========== Update TUsrRptPref ===========");
		return genericDAO.update(tUsrRptPref);
	}

	/**
	 * Retrieve an TUsrRptPref object based on given TUsrRptPref TUsrRptPrefId.
	 * 
	 * @param tUsrRptPrefId
	 *            the primary key value of the TUsrRptPref Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	/*public TUsrRptPref findTUsrRptPrefById(final TUsrRptPrefId tUsrRptPrefId) {
		LOGGER.info("find TUsrRptPref instance with TUsrRptPrefId: " + tUsrRptPrefId);
		return genericDAO.get(clazz, tUsrRptPrefId);
	}*/
	
	public TUsrRptPref findTUsrRptPrefById(Integer prefId){
		LOGGER.info("find TUsrRptPref instance with TUsrRptPrefId: " + prefId);
		return genericDAO.get(clazz, prefId);
	}
	
	/**
	 * Retrieve TUsrRptPref based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TUsrRptPref> list of TUsrRptPref if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TUsrRptPref> findTUsrRptPrefs(final SearchFilter<TUsrRptPref> searchFilter) {
		LOGGER.info("=========== Find TUsrRptPrefs ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TUsrRptPref tUsrRptPref = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tUsrRptPrefentity", tUsrRptPref);

		if (tUsrRptPref == null) {
			jpaQuery.bind(TUSRRPTPREF, new TUsrRptPref());
		} else {
			LOGGER.info("tUsrRptPref {}"+ tUsrRptPref.getTUsrRptPref());

			jpaQuery.bind(TUSRRPTPREF, tUsrRptPref.getTUsrRptPrefss());
		}

		if (tUsrRptPref.getTPers() == null) {
			jpaQuery.bind(TPERS, new TPers());
		} else {
			LOGGER.info("tPers {}"+ tUsrRptPref.getTPers());

			jpaQuery.bind(TPERS, tUsrRptPref.getTPers());
		}
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TUsrRptPrefs.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTUsrRptPrefs(final SearchFilter<TUsrRptPref> searchFilter) {
		LOGGER.info("=========== Count TUsrRptPref ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TUsrRptPref tUsrRptPref = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tUsrRptPrefentity", tUsrRptPref);

		if (tUsrRptPref.getTUsrRptPref() == null) {
			jpaCountQuery.bind(TUSRRPTPREF, new TUsrRptPref());
		} else {
			LOGGER.info("tUsrRptPref {}"+ tUsrRptPref.getTUsrRptPref());

			jpaCountQuery.bind(TUSRRPTPREF, tUsrRptPref.getTUsrRptPref());
		}

		if (tUsrRptPref.getTPers() == null) {
			jpaCountQuery.bind(TPERS, new TPers());
		} else {
			LOGGER.info("tPers {}"+ tUsrRptPref.getTPers());

			jpaCountQuery.bind(TPERS, tUsrRptPref.getTPers());
		}

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TUsrRptPref based on given search criteria using JPA named Query.
	 * The search criteria is of TUsrRptPref type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TUsrRptPref> list of TUsrRptPrefs if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TUsrRptPref> getTUsrRptPrefsByTUsrRptPref(final SearchFilter<TUsrRptPref> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTUsrRptPrefByTUsrRptPref", queryParam, index, maxresult);
	}
	
	public List<TUsrRptPref> findTUsrRptPrefByParentId(final SearchFilter<TUsrRptPref> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TUsrRptPref tUsrRptPref = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(tUsrRptPref.getPrefId());
		paramList.add(tUsrRptPref.getTenantId());
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTUsrRptPrefByParentId", paramList,  index, maxresult);
		//return genericDAO.findEntitiesByNamedQuery("FindTUsrRptPrefByParentId", tUsrRptPref.getPrefId(), index, maxresult);
	}
	
	public List<TUsrRptPref> findTUsrRptPrefByFolderName(final SearchFilter<TUsrRptPref> searchFilter){
		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TUsrRptPref tUsrRptPref = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(tUsrRptPref.getFolderName());
		paramList.add(tUsrRptPref.getParentFolderId());
		paramList.add(tUsrRptPref.getStaffId());
		paramList.add(tUsrRptPref.getTenantId());

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTUsrRptPrefByFolderName", paramList, index, maxresult);
	}
	
	public List<TUsrRptPref> findTUsrRptPrefsByStaffAndRptType(final Integer prnFolderId, final Integer staffId, final Short tenantId){		
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(prnFolderId);
		paramList.add(staffId);
		paramList.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTUsrRptPrefsByStaffAndRptType", paramList,  0, -1);
	}

	/**
	 * Count TUsrRptPref based on given search criteria using JPA named Query.
	 * The search criteria is of TUsrRptPref type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTUsrRptPrefsByTUsrRptPref(final SearchFilter<TUsrRptPref> searchFilter) {

		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());
		return genericDAO.findEntitiesByNamedQuery("CountTUsrRptPrefsByTUsrRptPref", queryParam);
	}

	/**
	 * Retrieve TUsrRptPref based on given search criteria using JPA named Query.
	 * The search criteria is of TPers type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TUsrRptPref> list of TUsrRptPrefs if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TUsrRptPref> getTUsrRptPrefsByTPers(final SearchFilter<TPers> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTUsrRptPrefByTPers", queryParam, index, maxresult);
	}

	/**
	 * Count TUsrRptPref based on given search criteria using JPA named Query.
	 * The search criteria is of TPers type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTUsrRptPrefsByTPers(final SearchFilter<TPers> searchFilter) {

		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());
		return genericDAO.findEntitiesByNamedQuery("CountTUsrRptPrefsByTPers", queryParam);
	}

	@Override
	public List<Object[]> fetchUserFoldersByRptType(Integer prefId,
			Integer staffId, Short tenantId) {
		List<Object> params = new ArrayList<Object>();
		params.add(prefId);
		params.add(staffId);
		params.add(tenantId);
		return genericDAO.findEntitiesByNamedQuery("FetchUserFoldersByRptType", params);
	}	
	
	/**
	 * @method - countByFolderNameAndParentFolder
	 * @param folderName - folder Name
	 * @param parentFolderId - parent Folder Id
	 * @param staffId - staff Id
	 * @param tenantId - tenant Id
	 * @return Integer - count By Folder Name And Parent Folder
	 */
	@Override
	public Integer countByFolderNameAndParentFolder(String folderName, 
			Integer parentFolderId, Integer staffId, Short tenantId){
		
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(folderName);
		paramList.add(parentFolderId);
		paramList.add(staffId);
		paramList.add(tenantId);
		List<Object> list = genericDAO.findEntitiesByNamedQueryMultiCond("CountByFolderNameAndParentFolder", paramList, 0, -1);
		
		return Integer.parseInt(list.get(0).toString());
	}
	
	/**
	 * @method - countOfSubFolders
	 * @param parentFolderId - parent Folder Id
	 * @param tenantId - tenant Id
	 * @return Integer - count By sub Folder
	 */
	@Override
	public Integer countOfSubFolders(Integer parentFolderId, Short tenantId){
		
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(parentFolderId);
		paramList.add(tenantId);
		List<Object> list = genericDAO.findEntitiesByNamedQueryMultiCond("CountOfSubFolders", paramList, 0, -1);
		
		return Integer.parseInt(list.get(0).toString());
	}
}
