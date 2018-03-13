package com.cognizant.opserv.sp.core.dao;


import java.text.ParseException;
import java.util.Date;
import java.util.List;

import com.cognizant.opserv.sp.core.entity.TRpt;
import com.cognizant.opserv.sp.core.entity.TRptSchedExecution;
import com.cognizant.opserv.sp.core.entity.TRptStatusType;
import com.cognizant.opserv.sp.core.entity.TRptType;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TRpt DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TRptDAO {

	/**
	 * Stores a new TRpt entity object in to the persistent store
	 * 
	 * @param tRpt
	 *            TRpt Entity object to be persisted
	 * @return tRpt Persisted TRpt object
	 */
	TRpt createTRpt(TRpt tRpt);

	/**
	 * Deletes a TRpt entity object from the persistent store
	 * 
	 * @param tRpt
	 *            TRpt Entity object to be deleted
	 */
	void deleteTRpt(Integer rptId);

	/**
	 * Updates a TRpt entity object in to the persistent store
	 * 
	 * @param tRpt
	 *            TRpt Entity object to be updated
	 * @return tRpt Persisted TRpt object
	 */
	TRpt updateTRpt(TRpt tRpt);

	/**
	 * Retrieve an TRpt object based on given rptId.
	 * 
	 * @param rptId
	 *            the primary key value of the TRpt Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TRpt findTRptById(Integer rptId);

	/**
	 * Retrieve TRpt based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRpt> list of TRpts if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TRpt> findTRpts(SearchFilter<TRpt> searchFilter);

	/**
	 * Count TRpt based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTRpts(SearchFilter<TRpt> searchFilter);

	/**
	 * Retrieve TRpt based on given search criteria using JPA named Query.
	 * The search criteria is of TRptStatusType type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRpt> list of TRpts if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TRpt> getTRptsByTRptStatusType(SearchFilter<TRptStatusType> searchFilter);

	/**
	 * Count TRpt based on given search criteria using JPA named Query.
	 * The search criteria is of TRptStatusType type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTRptsByTRptStatusType(SearchFilter<TRptStatusType> searchFilter);

	List<TRpt> getTRptsByRecipient(SearchFilter<Integer> searchFilter);
	
	List<TRpt> findTRptsByMonth(SearchFilter<TRpt> searchFilter);
	
	List<TRpt> findOlderRptsByType(final Integer staffId, Date date, int rptStatusVal, int rptType, Short tenantId, Integer index, Integer maxresult);
	
	/**
	 * Retrieve TRpt based on given search criteria using JPA named Query.
	 * The search criteria is of TRptSchedExecution type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRpt> list of TRpts if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TRpt> getTRptsByTRptSchedExecution(SearchFilter<TRptSchedExecution> searchFilter);

	/**
	 * Count TRpt based on given search criteria using JPA named Query.
	 * The search criteria is of TRptSchedExecution type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTRptsByTRptSchedExecution(SearchFilter<TRptSchedExecution> searchFilter);
	
	List<TRpt> getTRptsByRecipientAndTRptType(final Integer staffId,Integer rptTypeId, Integer rptStatusId,short tenantId);
	
	Object findLastPublishedRptId(SearchFilter<TRpt> searchFilter);
	
	/**
	 * Retrieve TRpt based on given search criteria using JPA named Query.
	 * The search criteria is of TRptStatusType and TRptType type.
	 * @param tenantId 
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRpt> list of TRpts if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TRpt> getTRptsByTRptStatusTypeAndTRptType(TRptStatusType tRptStatusType, TRptType tRptType, Short tenantId, Integer index, Integer maxresult);

	List<TRpt> findTRptsByMonthAndType(final Integer staffId, Date date, int rptStatusVal, int rptType, Short tenantId, Integer index, Integer maxresult) ;
	
	List<TRpt> searchFFQuery(List<Integer> staffId,String rptTitle,Date date,String ackStatus,String category, Short tenantId,Integer rptTypeId);
	
	List<Object[]> reportSearchQuery(List<Integer> staffId,String rptTitle,Date date,Integer ackStatus,String category, Short tenantId,Integer rptTypeId, boolean isSearch,Integer statusTypeId,List<Long> SP,Long algmntId,Integer start) throws ParseException;
	
	List<TRpt> findRptsByRptType(final Integer staffId ,Integer rptTypeId,Integer rptStatusId, Date date, short tenantId);

	List<Object[]> getUserReports(Integer staffId, Integer rptTypeId,Integer rptStatusId, short tenantId,Long alignId,Integer index,Integer maxresult);

	List<Object[]> getMonthReports(Integer staffId,Integer rptTypeId, Integer rptStatusId,	Date date, short tenantId,Long algmntId,Integer index,Integer maxResult);
	
	List<Object[]> getSalesPosReports(List<Long> spIds,Integer rptTypeId, Integer rptStatusId,	Date date, short tenantId,Integer index,Integer maxResult);

	String getReportTitle(Integer rpId);
	
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
	byte[] fetchRptDesignOfConfigReport(Integer rptId) ;
	
	List<Object[]> fetchRecptData(Integer rptId, Short tenantId);
	
	/**
	 * @method - fetches the unread reports count of logged in user
	 * @param staffId - holds staff id
	 * @param rptStatusId - holds status id - published 
	 * @param tenantId - holds logged in user tenant id 
	 * @return List<Object[]> - holds report type and its count
	 */
	List<Object[]> getUserReportsCount(Integer staffId ,Integer rptStatusId, short tenantId,Long algmntId);
	
	List<Object[]> getUserRportsByStaffAlignment(final Integer staffId ,Integer rptTypeId,Integer rptStatusId, short tenantId,Long alignId,Integer index,Integer maxResult);
}
