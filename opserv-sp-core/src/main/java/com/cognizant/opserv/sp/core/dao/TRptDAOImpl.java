package com.cognizant.opserv.sp.core.dao;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TRpt;
import com.cognizant.opserv.sp.core.entity.TRptSchedExecution;
import com.cognizant.opserv.sp.core.entity.TRptStatusType;
import com.cognizant.opserv.sp.core.entity.TRptType;
import com.cognizant.opserv.sp.common.util.DateUtil;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TRptDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tRptDAO")
public class TRptDAOImpl implements TRptDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TRptDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}
	
	private static final String TRPTSCHEDEXECUTION = "tRptSchedExecution";

	private final Class<TRpt> clazz;

	public Class<TRpt> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TRptDAOImpl() {
		super();
		this.clazz = TRpt.class;
	}

	private static final String JPAQL = "select tRptentity from TRpt tRptentity";

	private static final String JPACOUNTQL = "select count(tRptentity) from TRpt tRptentity";

	private static final String[] RESTRICTIONS = { "tRptentity.rptId = #{tRptentity.getRptId}",
			"tRptentity.rptTitle like '#{tRptentity.getRptTitle}%'",
			"Date(tRptentity.publishedDt) = '#{tRptentity.getPublishedDt}'",
			"tRptentity.createdBy = #{tRptentity.getCreatedBy}",
			"Date(tRptentity.createDt) = '#{tRptentity.getCreateDt}'",
			"tRptentity.updatedBy = #{tRptentity.getUpdatedBy}",
			"Date(tRptentity.updateDate) = '#{tRptentity.getUpdateDate}'",
			"tRptentity.tenantId = #{tRptentity.getTenantId}",
			"tRptentity.tRptSchedExecution.schedExecutionId = #{tRptentity.tRptSchedExecution.getSchedExecutionId}",
			"tRptentity.tRptStatusType.rptStatusTypeId = #{tRptentity.tRptStatusType.getRptStatusTypeId}"};
	
	private static final String REPORT_QUERY = "select rpt.rptId,rpt.rptTitle,rptr.readDt,rptr.ackStatusId,rptc.rptCategoryId,rptr.tRptRecipientId.staffId," +
			"rpt.publishedDt,pos.posName,pos.tSalesPosId.salesPosId,alg.effStartDt,alg.effEndDt,pers.firstName,pers.middleName,pers.lastName" +
			" from TRpt rpt inner join rpt.tRptRecipientss" +
			" rptr inner join rpt.tRptSchedExecution rptse inner join rptse.tRptSched rpts inner join rpts.tRptConfig rptc inner" +
			" join rptr.tPers pers inner join pers.tEmpAlgmntss alg inner join alg.tSalesPos pos ";

	/**
	 * Stores a new TRpt entity object in to the persistent store
	 * 
	 * @param tRpt
	 *            TRpt Entity object to be persisted
	 * @return tRpt Persisted TRpt object
	 */
	public TRpt createTRpt(final TRpt tRpt) {
		LOGGER.info("=========== Create TRpt ===========");
		return genericDAO.store(tRpt);
	}

	/**
	 * Deletes a TRpt entity object from the persistent store
	 * 
	 * @param tRpt
	 *            TRpt Entity object to be deleted
	 */
	public void deleteTRpt(final Integer rptId) {
		LOGGER.info("=========== Delete TRpt ===========");
		final TRpt tRpt = genericDAO.get(clazz, rptId);
		genericDAO.remove(tRpt);
	}

	/**
	 * Updates a TRpt entity object in to the persistent store
	 * 
	 * @param tRpt
	 *            TRpt Entity object to be updated
	 * @return tRpt Persisted TRpt object
	 */
	public TRpt updateTRpt(final TRpt tRpt) {
		LOGGER.info("=========== Update TRpt ===========");
		return genericDAO.update(tRpt);
	}

	/**
	 * Retrieve an TRpt object based on given TRpt rptId.
	 * 
	 * @param tRptId
	 *            the primary key value of the TRpt Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TRpt findTRptById(final Integer tRptId) {
		LOGGER.info("find TRpt instance with rptId: " + tRptId);
		return genericDAO.get(clazz, tRptId);
	}

	/**
	 * Retrieve TRpt based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRpt> list of TRpt if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TRpt> findTRpts(final SearchFilter<TRpt> searchFilter) {
		LOGGER.info("=========== Find TRpts ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TRpt tRpt = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tRptentity", tRpt);

		if (tRpt.getTRptSchedExecution() == null) {
			jpaQuery.bind(TRPTSCHEDEXECUTION, new TRptSchedExecution());
		} else {
			LOGGER.info("tRptSchedExecution {}"+ tRpt.getTRptSchedExecution());

			jpaQuery.bind(TRPTSCHEDEXECUTION, tRpt.getTRptSchedExecution());
		}
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TRpts.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTRpts(final SearchFilter<TRpt> searchFilter) {
		LOGGER.info("=========== Count TRpt ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TRpt tRpt = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tRptentity", tRpt);

		if (tRpt.getTRptSchedExecution() == null) {
			jpaCountQuery.bind(TRPTSCHEDEXECUTION, new TRptSchedExecution());
		} else {
			LOGGER.info("tRptSchedExecution {}"+ tRpt.getTRptSchedExecution());

			jpaCountQuery.bind(TRPTSCHEDEXECUTION, tRpt.getTRptSchedExecution());
		}
		
		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TRpt based on given search criteria using JPA named Query.
	 * The search criteria is of TRptStatusType type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRpt> list of TRpts if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TRpt> getTRptsByTRptStatusType(final SearchFilter<TRptStatusType> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TRptStatusType tRptStatusType = searchFilter.getEntityClass();
		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(tRptStatusType.gettRptStatusTypeId().getRptStatusTypeId());
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTRptByTRptStatusType", queryParam, index, maxresult);
	}

	/**
	 * Count TRpt based on given search criteria using JPA named Query.
	 * The search criteria is of TRptStatusType type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTRptsByTRptStatusType(final SearchFilter<TRptStatusType> searchFilter) {

		final TRptStatusType tRptStatusType = searchFilter.getEntityClass();
		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(tRptStatusType.gettRptStatusTypeId().getRptStatusTypeId());
		return genericDAO.findEntitiesByNamedQuery("CountTRptsByTRptStatusType", queryParam);
	}
	
	public List<TRpt> getTRptsByRecipient(final SearchFilter<Integer> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTRptByRecipient", queryParam, index, maxresult);
	}
	
	public List<TRpt> findTRptsByMonth(final SearchFilter<TRpt> searchFilter) {
		List<Object> list = new ArrayList<Object>();
		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TRpt tRpt = searchFilter.getEntityClass();

		Calendar cal = Calendar.getInstance();
		cal.setTime(tRpt.getPublishedDt());
		list.add(cal.get(Calendar.MONTH)+1);
		list.add(cal.get(Calendar.YEAR));
		list.add(tRpt.getRptStatusTypeId());
		list.add(tRpt.getTenantId());

		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO
				.findEntitiesByNamedQueryMultiCond("FindTRptsByMonth", list, index, maxresult);

	}
	
	public List<TRpt> findTRptsByMonthAndType(final Integer staffId, Date date, int rptStatus, int rptType, Short tenantId, Integer index, Integer maxresult) {
		List<Object> list = new ArrayList<Object>();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		list.add(cal.get(Calendar.MONTH)+1);
		list.add(cal.get(Calendar.YEAR));
		list.add(rptStatus);
		list.add(rptType);
		list.add(tenantId);
		return genericDAO
				.findEntitiesByNamedQueryMultiCond("FindTRptsByMonthAndType", list, index, maxresult);
	}
	
	public List<TRpt> findOlderRptsByType(final Integer staffId, Date date, int rptStatusVal, int rptType, Short tenantId, Integer index, Integer maxresult){
		List<Object> list = new ArrayList<Object>();
		list.add(date);
		list.add(rptStatusVal);
		list.add(rptType);
		list.add(staffId);
		list.add(tenantId);
		return genericDAO
				.findEntitiesByNamedQueryMultiCond("FindOlderTRptsByType", list, index, maxresult);
	}

	/**
	 * Retrieve TRpt based on given search criteria using JPA named Query.
	 * The search criteria is of TRptSchedExecution type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRpt> list of TRpts if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TRpt> getTRptsByTRptSchedExecution(final SearchFilter<TRptSchedExecution> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());
		
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO
				.findEntitiesByNamedQueryMultiCond("FindTRptByTRptSchedExecution", queryParam, index, maxresult);
	}
	
	/**
	 * Count TRpt based on given search criteria using JPA named Query.
	 * The search criteria is of TRptSchedExecution type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTRptsByTRptSchedExecution(final SearchFilter<TRptSchedExecution> searchFilter) {

		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());
		
		return genericDAO.findEntitiesByNamedQuery("CountTRptsByTRptSchedExecution", queryParam);
	}
	
	public List<TRpt> getTRptsByRecipientAndTRptType(final Integer staffId ,Integer rptTypeId,Integer rptStatusId , short tenantId) {		
		List<Object> list = new ArrayList<Object>();
		list.add(staffId);
		list.add(rptTypeId);
		list.add(rptStatusId);
		list.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTRptByRecipientAndTRptType", list,0,-1);
		}
	
	@Override
	public Object findLastPublishedRptId(SearchFilter<TRpt> searchFilter) {
		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		final TRpt tRpt = searchFilter.getEntityClass();
		final Integer trptStatusId = tRpt.getTRptSchedExecution().getTRptSched().getTRptConfig().getRptTypeId(); //set published
		int trptStatusTypeId = 2;
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(trptStatusId);
		paramList.add(trptStatusTypeId);
        
		return genericDAO.findEntitiesByNamedQueryMultiCond("findLastPublishedRptId", paramList, index, maxresult);
	   
	}
	
	
	/**
	 * Retrieve TRpt based on given search criteria using JPA named Query.
	 * The search criteria is of TRptStatusType and TRptType type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRpt> list of TRpts if it exists against given
	 *         criteria. Returns null if not found
	 */
	@Override
	public List<TRpt> getTRptsByTRptStatusTypeAndTRptType(TRptStatusType tRptStatusType, TRptType tRptType,Short tenantId, Integer index, Integer maxresult) {

		List paramList = new ArrayList();
		paramList.add(tRptStatusType.gettRptStatusTypeId().getRptStatusTypeId());
		paramList.add(tRptType.gettRptTypeId().getRptTypeId());
		paramList.add(tenantId);

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTRptByTRptStatusTypeAndTRptType", paramList, index, maxresult);
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TRpt> searchFFQuery(List<Integer> staffId, String rptTitle, Date date, String ackStatus, String category, Short tenantId,Integer rptTypeId) {
		StringBuilder query = new StringBuilder();
		query.append("select distinct rpt.*  from t_rpt rpt join t_rpt_sched_execution exe on rpt.sched_execution_id = exe.sched_execution_id "+
						"join t_rpt_sched sch on exe.rpt_sched_id = sch.rpt_sched_id "+ 
						"join t_rpt_config conf on sch.rpt_config_id = conf.rpt_config_id "+	 
						"join t_rpt_category cat on conf.rpt_category_id = cat.rpt_category_id "+
						"join t_rpt_recipient rcp on rcp.rpt_id = rpt.rpt_id "+
						"join t_ack_status status on status.ack_status_id = rcp.ack_status_id where rpt.tenant_id = "+tenantId+
						" and conf.rpt_type_id ="+rptTypeId);
		if(rptTitle != null && rptTitle!= "")
			query.append(" and rpt.rpt_title like '%"+rptTitle+"%'");
		if(date!=null)
		{
			SimpleDateFormat dft = new SimpleDateFormat("MM/dd/YYYY");
			query.append(" and rpt.published_dt between STR_TO_DATE('"+dft.format(date)+"','%m/%d/%Y') and STR_TO_DATE('"+dft.format(date)+" 23:59:59','%m/%d/%Y')");
		}
		if(category!=null)
			query.append(" and cat.rpt_category_desc = '"+category+"'");
		if(ackStatus!=null)
			query.append(" and status.ack_status_desc = '"+ackStatus+"'");
		if(!staffId.isEmpty())
			query.append(" and rcp.staff_id IN( "+StringUtils.join(staffId.toArray(), ",")+")");
		
		return (List<TRpt>) genericDAO.findEntitiesByQuery(query.toString(), TRpt.class);
	}
	
	@Override
	public List<Object[]> reportSearchQuery(List<Integer> staffId, String rptTitle, Date date, Integer ackStatus, 
			String category, Short tenantId,Integer rptTypeId, boolean isSearch,
			Integer statusTypeId,List<Long> sps,Long algmntId,Integer start) throws ParseException {
		StringBuilder query = new StringBuilder();
		String fixedCond = " where rpt.tenantId =?1 and rpt.rptStatusTypeId =?2 and rptc.rptTypeId =?3 AND alg.algmntId = ?4";
		query.append(REPORT_QUERY).append(fixedCond);
		query.append(" and alg.effStartDt <= rpt.publishedDt and alg.effEndDt >= rpt.publishedDt ");
		List<Object> params =  new ArrayList<Object>();
		params.add(tenantId);
		params.add(statusTypeId);
		params.add(rptTypeId);
		params.add(algmntId);
		if(rptTitle != null && rptTitle!= ""){
			query.append(" and rpt.rptTitle like ?"+(params.size()+1));
			params.add("%"+rptTitle+"%");
		}
		if(date!=null){
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			
			if(isSearch){
				String str = DateUtil.getDefaultFormat(date, null);
				Date dateBefore = DateUtil.getDefaultDateTimeFormat(str.concat(" 00:00:00"), null);
				Date dateAfter =DateUtil.getDefaultDateTimeFormat(str.concat(" 23:59:59"), null);
				
				query.append(" and rpt.publishedDt  between ?"+(params.size()+1)+ " and ?"+(params.size()+2));
				params.add(dateBefore);	
				params.add(dateAfter);	
			}else {
				query.append(" and MONTH(rpt.publishedDt) = ?"+(params.size()+1));
				params.add(cal.get(Calendar.MONTH)+1);	
			}
		}
		if(category!=null){
			query.append(" AND rptc.rptCategoryId =?"+(params.size()+1));
			params.add(new Integer(category));
		}
		if(ackStatus!=null){
			query.append(" AND rptr.ackStatusId =?"+(params.size()+1));
			params.add(ackStatus);
		}
		if(!staffId.isEmpty()){
			query.append(" AND rptr.tRptRecipientId.staffId in ?"+(params.size()+1));
			params.add(staffId);
		}		
		if(sps.size()>0){			
			query.append(" AND pos.tSalesPosId.salesPosId in ?"+(params.size()+1));
			params.add(sps);				
		}
		query.append(" group by rpt.rptId,pos.tSalesPosId.salesPosId order by rpt.updateDate desc");
		return genericDAO.findEntitiesByBuildQueries(query.toString(), params, start,10);
	}
	
	public List<TRpt> findRptsByRptType(final Integer staffId ,Integer rptTypeId,Integer rptStatusId, Date date, short tenantId) {		
		List<Object> list = new ArrayList<Object>();
		list.add(staffId);
		list.add(rptTypeId);
		list.add(rptStatusId);
		list.add(date);
		list.add(tenantId);
		List<TRpt> rptList = genericDAO.findEntitiesByNamedQueryMultiCond("FindTRptsByRptType", list, 0, -1); 
		return rptList;
	}
	
	@Override
	public List<Object[]> getUserReports(final Integer staffId ,Integer rptTypeId,Integer rptStatusId, short tenantId,Long alignId,Integer index,Integer maxResult) {		
		List<Object> list = new ArrayList<Object>();
		list.add(staffId);
		list.add(rptTypeId);
		list.add(rptStatusId);
		list.add(tenantId);
		list.add(alignId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("GetUserRportsByStaff", list,index,maxResult);		 
	}
	
	@Override
	public List<Object[]> getMonthReports(Integer staffId,Integer rptTypeId,Integer rptStatusId, Date date, short tenantId,Long algmntId,Integer index,Integer maxResult) {		
		List<Object> list = new ArrayList<Object>();		
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		list.add(cal.get(Calendar.MONTH)+1);
		list.add(cal.get(Calendar.YEAR));
		list.add(rptTypeId);
		list.add(rptStatusId);		
		list.add(tenantId);
		list.add(staffId);
		list.add(algmntId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("GetUserRportsByMonth", list,index,maxResult);		 
	}

	@Override
	public List<Object[]> getSalesPosReports(List<Long> spIds,Integer rptTypeId,Integer rptStatusId, Date date, short tenantId,Integer index,Integer maxResult) {
		List<Object> list = new ArrayList<Object>();		
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		list.add(cal.get(Calendar.MONTH)+1);
		list.add(cal.get(Calendar.YEAR));
		list.add(rptTypeId);
		list.add(rptStatusId);		
		list.add(tenantId);	
		list.add(spIds);	
		return null; //genericDAO.findEntitiesByNamedQueryMultiCond("GetUserRportsBySalesPos", list,index,maxResult);		
	}

	@Override
	public String getReportTitle(Integer rpId) {
		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(rpId);
		List<String> list =  genericDAO.findEntitiesByNamedQuery("GetReportTitleByRptID", queryParam);
		if(!list.isEmpty()){
			return list.get(0);
		}
		return null;
	}

	/**
	 * Retrieve Report design object based on given report ID.
	 * The search criteria is of report ID
	 * 
	 * @param rptId
	 * 		the primary key value of the TRpt Entity.
	 * 
	 *  @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	@Override
	public byte[] fetchRptDesignOfConfigReport(Integer rptId) {
		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(rptId);
		  List<?> list = genericDAO.findEntitiesByNamedQuery("FetchRptDesignOfConfigReport", queryParam) ;
		  if(list != null & !list.isEmpty()){
			  return (byte[]) list.get(0) ;
		  }
		  return null;
	}
	
	@Override
	public List<Object[]> fetchRecptData(Integer rptId, Short tenantId) {
		List<Object> list = new ArrayList<Object>();
		list.add(rptId);

		list.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FetchRecptData", list, 0, -1); 
	}

	
	/**
	 * @method - fetches the unread reports count of logged in user
	 * @param staffId - holds staff id
	 * @param rptStatusId - holds status id - published 
	 * @param tenantId - holds logged in user tenant id 
	 * @return List<Object[]> - holds report type and its count
	 */
	@Override
	public List<Object[]> getUserReportsCount(Integer staffId ,Integer rptStatusId, short tenantId,Long algmntId) {		
		List<Object> list = new ArrayList<Object>();
		list.add(staffId);
		list.add(rptStatusId);
		list.add(tenantId);
		list.add(algmntId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("GetUserRportsCountByStaff", list,0,-1);		 
	}
	
	@Override
	public List<Object[]> getUserRportsByStaffAlignment(final Integer staffId ,Integer rptTypeId,Integer rptStatusId, short tenantId,Long alignId,Integer index,Integer maxResult) {		
		List<Object> list = new ArrayList<Object>();
		list.add(staffId);
		list.add(rptTypeId);
		list.add(rptStatusId);
		list.add(tenantId);
		list.add(alignId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("GetUserRportsByStaffAlignment", list,index,maxResult);		 
	}
	
	
}
