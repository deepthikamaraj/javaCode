package com.cognizant.opserv.sp.core.dao;

import java.util.Date;
import java.util.List;

import com.cognizant.opserv.sp.core.entity.TComm;
import com.cognizant.opserv.sp.core.entity.TCommStatus;
import com.cognizant.opserv.sp.core.entity.TCommType;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TComm DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TCommDAO {

	/**
	 * Stores a new TComm entity object in to the persistent store
	 * 
	 * @param tComm
	 *            TComm Entity object to be persisted
	 * @return tComm Persisted TComm object
	 */
	TComm createTComm(TComm tComm);

	/**
	 * Deletes a TComm entity object from the persistent store
	 * 
	 * @param tComm
	 *            TComm Entity object to be deleted
	 */
	void deleteTComm(Long commId);

	/**
	 * Updates a TComm entity object in to the persistent store
	 * 
	 * @param tComm
	 *            TComm Entity object to be updated
	 * @return tComm Persisted TComm object
	 */
	TComm updateTComm(TComm tComm);

	/**
	 * Retrieve an TComm object based on given commId.
	 * 
	 * @param commId
	 *            the primary key value of the TComm Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TComm findTCommById(Long commId);

	/**
	 * Retrieve TComm based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TComm> list of TComms if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TComm> findTComms(SearchFilter<TComm> searchFilter);

	/**
	 * Count TComm based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTComms(SearchFilter<TComm> searchFilter);

	/**
	 * Retrieve TComm based on given search criteria using JPA named Query.
	 * The search criteria is of TCommType type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TComm> list of TComms if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TComm> getTCommsByTCommType(SearchFilter<TCommType> searchFilter);

	/**
	 * Count TComm based on given search criteria using JPA named Query.
	 * The search criteria is of TCommType type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTCommsByTCommType(SearchFilter<TCommType> searchFilter);

	/**
	 * Retrieve TComm based on given search criteria using JPA named Query.
	 * The search criteria is of TCommStatus type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TComm> list of TComms if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TComm> getTCommsByTCommStatus(SearchFilter<TCommStatus> searchFilter);

	/**
	 * Count TComm based on given search criteria using JPA named Query.
	 * The search criteria is of TCommStatus type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTCommsByTCommStatus(SearchFilter<TCommStatus> searchFilter);
	
	/**
	 * Retrieve TComm based on given search criteria using JPA named Query.
	 * The search criteria is of TCommStatus type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TComm> list of TComms if it exists against given
	 *         criteria. Returns null if not found
	 */
	
	/**
	 * Retrieve TComm based on given search criteria using JPA named Query.
	 * The search criteria is of TCommStatus type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TComm> list of TComms if it exists against given
	 *         criteria. Returns null if not found
	 */
	
	//List<TComm> getTCommsByTCommTypeStatus_PlanDoc(final SearchFilter<TCommStatus> searchFilter);
	
	/**
	 * Retrieve TComm based on given search criteria using JPA named Query.
	 * The search criteria is of TCommStatus type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TComm> list of TComms if it exists against given
	 *         criteria. Returns null if not found
	 */
	
	List<TComm> getTCommsByTCommTypeStatus(final SearchFilter<TComm> searchFilter);
	
	/**
	 * Gets the t comms by t comm type status_ activity.
	 *
	 * @param searchFilter the search filter
	 * @return the t comms by t comm type status_ activity
	 */
	List<TComm> getTCommsByTCommTypeStatusActivity(final SearchFilter<TCommStatus> searchFilter);
	
	/**
	 * Gets the t comms by month.
	 *
	 * @param searchFilter the search filter
	 * @param statusIdentity the status identity
	 * @return the t comms by month
	 */
	List<TComm> getTCommsByMonth(final SearchFilter<TComm> searchFilter,Integer statusIdentity);
	
	/**
	 * Gets the t comms by created by type status date for published.
	 *
	 * @param searchFilter the search filter
	 * @param statusIdentity the status identity
	 * @return the t comms by created by type status date for published
	 */
	List<TComm> getTCommsByCreatedByTypeStatusDateForPublished(final SearchFilter<TComm> searchFilter,Integer statusIdentity);
	
	/**
	 * Gets the t comms by advance search.
	 *
	 * @param searchFilter the search filter
	 * @return the t comms by advance search
	 */
	List<TComm> getTCommsByAdvanceSearch(SearchFilter<TComm> searchFilter);
	
	/**
	 * Find last published comm id.
	 *
	 * @param searchFilter the search filter
	 * @return the object
	 */
	Object findLastPublishedCommId(final SearchFilter<TComm> searchFilter);

	/**
	 * Gets the t comms draft by t comm type status.
	 *
	 * @param searchFilter the search filter
	 * @return the t comms draft by t comm type status
	 */
	List<TComm> getTCommsDraftByTCommTypeStatus(SearchFilter<TComm> searchFilter);
	
	/**
	 * Gets the t comms by date.
	 *
	 * @param searchFilter the search filter
	 * @return the t comms by date
	 */
	List<TComm> getTCommsByDate(final SearchFilter<TComm> searchFilter);

	/**
	 * Find activity for logged user.
	 *
	 * @param searchFilter the search filter
	 * @return the list
	 */
	List<TComm> findActivityForLoggedUser(final SearchFilter<TComm> searchFilter);

	/**
	 * Piblish announcement.
	 *
	 * @param communicationId the communication id
	 * @param publishedDate the published date
	 * @param userId the user id
	 */
	void piblishAnnouncement(Long communicationId,Date publishedDate,Integer userId,int status);
	
	/**
	 * getAnnouncements By User
	 * @param userId the user id
	 * @param tenantId the tenant id
	 */
	List<TComm> getAnnouncementsByUser(Integer userId, Short tenantId);
	
	/**
	 * getAnnouncements By Publisher
	 * @param userId the user id
	 * @param type the type
	 * @param paginInfo the paginInfo
	 * @param tenantId the tenant id
	 */
	List<TComm> getAnnouncementsByPublisher(Integer userId, Integer typeId, 
			PaginationInfo paginInfo, Short tenantId);
}
