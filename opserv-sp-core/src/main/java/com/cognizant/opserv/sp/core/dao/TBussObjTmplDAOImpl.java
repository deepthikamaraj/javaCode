package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TBussObj;
import com.cognizant.opserv.sp.core.entity.TBussObjTmpl;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TBussObjTmplDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tBussObjTmplDAO")
public class TBussObjTmplDAOImpl implements TBussObjTmplDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TBussObjTmplDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private static final String TBUSSOBJ = "tBussObj";

	private final Class<TBussObjTmpl> clazz;

	public Class<TBussObjTmpl> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TBussObjTmplDAOImpl() {
		super();
		this.clazz = TBussObjTmpl.class;
	}

	private static final String JPAQL = "select tBussObjTmplentity from TBussObjTmpl tBussObjTmplentity";

	private static final String JPACOUNTQL = "select count(tBussObjTmplentity) from TBussObjTmpl tBussObjTmplentity";

	private static final String[] RESTRICTIONS = { "tBussObjTmplentity.tmplId = #{tBussObjTmplentity.getTmplId}",
			"tBussObjTmplentity.tmplName like '#{tBussObjTmplentity.getTmplName}%'",
			"tBussObjTmplentity.tmplDesc like '#{tBussObjTmplentity.getTmplDesc}%'",
			"tBussObjTmplentity.defFlag = #{tBussObjTmplentity.getDefFlag}",
			"Date(tBussObjTmplentity.effStartDt) = '#{tBussObjTmplentity.getEffStartDt}'",
			"Date(tBussObjTmplentity.effEndDt) = '#{tBussObjTmplentity.getEffEndDt}'",
			"tBussObjTmplentity.createdBy = #{tBussObjTmplentity.getCreatedBy}",
			"Date(tBussObjTmplentity.createDt) = '#{tBussObjTmplentity.getCreateDt}'",
			"tBussObjTmplentity.updatedBy = #{tBussObjTmplentity.getUpdatedBy}",
			"Date(tBussObjTmplentity.updateDt) = '#{tBussObjTmplentity.getUpdateDt}'",
			"tBussObjTmplentity.tenantId = #{tBussObjTmplentity.getTenantId}",
			"tBussObjTmplentity.tBussObj.bussObjId = #{tBussObjTmplentity.tBussObj.getBussObjId}" };

	/**
	 * Stores a new TBussObjTmpl entity object in to the persistent store
	 * 
	 * @param tBussObjTmpl
	 *            TBussObjTmpl Entity object to be persisted
	 * @return tBussObjTmpl Persisted TBussObjTmpl object
	 */
	public TBussObjTmpl createTBussObjTmpl(final TBussObjTmpl tBussObjTmpl) {
		LOGGER.info("=========== Create TBussObjTmpl ===========");
		return genericDAO.store(tBussObjTmpl);
	}

	/**
	 * Deletes a TBussObjTmpl entity object from the persistent store
	 * 
	 * @param tBussObjTmpl
	 *            TBussObjTmpl Entity object to be deleted
	 */
	public void deleteTBussObjTmpl(final Integer tmplId) {
		LOGGER.info("=========== Delete TBussObjTmpl ===========");
		final TBussObjTmpl tBussObjTmpl = genericDAO.get(clazz, tmplId);
		genericDAO.remove(tBussObjTmpl);
	}

	/**
	 * Updates a TBussObjTmpl entity object in to the persistent store
	 * 
	 * @param tBussObjTmpl
	 *            TBussObjTmpl Entity object to be updated
	 * @return tBussObjTmpl Persisted TBussObjTmpl object
	 */
	public TBussObjTmpl updateTBussObjTmpl(final TBussObjTmpl tBussObjTmpl) {
		LOGGER.info("=========== Update TBussObjTmpl ===========");
		return genericDAO.update(tBussObjTmpl);
	}

	/**
	 * Retrieve an TBussObjTmpl object based on given TBussObjTmpl tmplId.
	 * 
	 * @param tBussObjTmplId
	 *            the primary key value of the TBussObjTmpl Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TBussObjTmpl findTBussObjTmplById(final Integer tBussObjTmplId) {
		LOGGER.info("find TBussObjTmpl instance with tmplId: " + tBussObjTmplId);
		return genericDAO.get(clazz, tBussObjTmplId);
	}

	/**
	 * Retrieve TBussObjTmpl based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TBussObjTmpl> list of TBussObjTmpl if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TBussObjTmpl> findTBussObjTmpls(final SearchFilter<TBussObjTmpl> searchFilter) {
		LOGGER.info("=========== Find TBussObjTmpls ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TBussObjTmpl tBussObjTmpl = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tBussObjTmplentity", tBussObjTmpl);

		if (tBussObjTmpl.getTBussObj() == null) {
			jpaQuery.bind(TBUSSOBJ, new TBussObj());
		} else {
			LOGGER.info("tBussObj {}"+ tBussObjTmpl.getTBussObj());

			jpaQuery.bind(TBUSSOBJ, tBussObjTmpl.getTBussObj());
		}
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		
		/*setting cacheable true to enable caching*/
		jpaQuery.setCacheable(true);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TBussObjTmpls.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTBussObjTmpls(final SearchFilter<TBussObjTmpl> searchFilter) {
		LOGGER.info("=========== Count TBussObjTmpl ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TBussObjTmpl tBussObjTmpl = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tBussObjTmplentity", tBussObjTmpl);

		if (tBussObjTmpl.getTBussObj() == null) {
			jpaCountQuery.bind(TBUSSOBJ, new TBussObj());
		} else {
			LOGGER.info("tBussObj {}" + tBussObjTmpl.getTBussObj());

			jpaCountQuery.bind(TBUSSOBJ, tBussObjTmpl.getTBussObj());
		}

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TBussObjTmpl based on given search criteria using JPA named Query.
	 * The search criteria is of TBussObj type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TBussObjTmpl> list of TBussObjTmpls if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TBussObjTmpl> getTBussObjTmplsByTBussObj(final SearchFilter<TBussObj> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TBussObj tBussObj = searchFilter.getEntityClass();
		List<Object> tBussObjList = new ArrayList<Object>();
		tBussObjList.add(tBussObj);
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTBussObjTmplByTBussObj", tBussObjList, index, maxresult);
	}

	/**
	 * Count TBussObjTmpl based on given search criteria using JPA named Query.
	 * The search criteria is of TBussObj type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTBussObjTmplsByTBussObj(final SearchFilter<TBussObj> searchFilter) {

		final TBussObj tBussObj = searchFilter.getEntityClass();
		List<Object> tBussObjList = new ArrayList<Object>();
		tBussObjList.add(tBussObj);
		return genericDAO.findEntitiesByNamedQuery("CountTBussObjTmplsByTBussObj", tBussObjList);
	}
	/**
	 * Find t buss obj tmpl by buss obj id.
	 *
	 * @param bussObjId the buss obj id
	 * @param tenantId the tenant id
	 * @return the list
	 */
	@Override
	public List<TBussObjTmpl> findTBussObjTmplByBussObjId(Integer bussObjId, Short tenantId) {	
		List<Object> list = new ArrayList<Object>();
		list.add(bussObjId);
		list.add(tenantId);
		int index=0;
		int maxresult=-1;
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTBussObjTmplByTBussObjId",list,index,maxresult);
	}
	/**
	 * Find active t buss obj tmpl by t buss obj.
	 *
	 * @param tBussObj the t buss obj
	 * @return the list
	 */
	@Override
	public List<TBussObjTmpl> findActiveTBussObjTmplByTBussObj(TBussObj tBussObj) {
		List<Object> tBussObjList = new ArrayList<Object>();
		tBussObjList.add(tBussObj);
		return genericDAO.findEntitiesByNamedQuery("FindActiveTBussObjTmplByTBussObj", tBussObjList);
	}
	/**
	 * Delete from t buss obj tmpl hist table.
	 *
	 * @param tmplId the tmpl id
	 * @return the int
	 */
	@Override
	public int deleteFromTBussObjTmplHistTable(Integer tmplId) {
		return genericDAO.executeNativeQuery("delete from t_buss_obj_tmpl where tmp_id = "+tmplId);
	}
	/**
	 * find bussObjId  t buss obj  tmpl table.
	 *
	 * @param tmplId the tmpl id
	 * @return the int
	 */
	
	@Override
	public TBussObjTmpl findAllBussObjIdByTmplId(Integer templateId, short tenantId){
			List<Object> paramList = new ArrayList<Object>();
			paramList.add(templateId);
			paramList.add(tenantId);
			
			List<TBussObjTmpl> tBussObjTmplList = genericDAO
					.findEntitiesByNamedQueryMultiCond(
							"FindBussObjIdByTmplId", paramList, 0, -1);
			if (tBussObjTmplList != null
					&& !(tBussObjTmplList.isEmpty()))
				return tBussObjTmplList.get(0);
			else
				return null;
			//return genericDAO.findEntitiesByNamedQueryMultiCond("FindBussObjIdByTmplId",paramList,0,-1);
		} 
	/**
	 * Find def t buss obj tmpl.
	 *
	 * @param bussFunctionType the buss function type
	 * @param defFlag the def flag
	 * @param activeFlag the active flag
	 * @param effStartDt the eff start dt
	 * @param effEndDt the eff end dt
	 * @return the list
	 */
	@Override
	public List<TBussObjTmpl> findDefTBussObjTmpl(String bussFunctionType,
			Character defFlag, Character activeFlag, Date effStartDt,
			Date effEndDt) {
		List<Object> list = new ArrayList<Object>();
		list.add(bussFunctionType);
		list.add(defFlag);
		list.add(activeFlag);
		list.add(effStartDt);
		list.add(effEndDt);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindDefTBussObjTmpl",list,0,-1);
	}
}
