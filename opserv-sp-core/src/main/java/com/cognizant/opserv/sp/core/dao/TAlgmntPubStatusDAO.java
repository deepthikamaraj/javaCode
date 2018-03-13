package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TAlgmntPubStatus;

// TODO: Auto-generated Javadoc
/**
 * The Interface TAlgmntPubStatusDAO.
 * @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * @since 01/11/2014
 */
public interface TAlgmntPubStatusDAO {
	
	/**
	 * Creates the algmnt publish.
	 *
	 * @param tAlgmntPubStatus the t algmnt pub status
	 * @return the t algmnt pub status
	 * @throws Exception the exception
	 */
	public TAlgmntPubStatus createAlgmntPublish(final TAlgmntPubStatus tAlgmntPubStatus) throws Exception;

	/**
	 * Fetch all publish algmnt.
	 *
	 * @param short1 the short1
	 * @return the list
	 */
	public List<TAlgmntPubStatus> fetchAllPublishAlgmnt(Short short1);

	/**
	 * Fetch all publish algmnt with ids.
	 *
	 * @param tenentID the tenent id
	 * @param algmntId the algmnt id
	 * @param bussUnitId the buss unit id
	 * @param salesTeamId the sales team id
	 * @param category the category
	 * @return the list
	 */
	List<TAlgmntPubStatus> fetchAllPublishAlgmntWithIds(Short tenentID,Long algmntId, Long bussUnitId, Long salesTeamId, String category );
	
	/**
	 * Find all t algmnts id al bu st with category.
	 *
	 * @param tenentID the tenent id
	 * @param algmntId the algmnt id
	 * @param bussUnitId the buss unit id
	 * @param salesTeamId the sales team id
	 * @return the list
	 */
	List<TAlgmntPubStatus> FindAllTAlgmntsIdAlBuStWithCategory(Short tenentID,Long algmntId, Long bussUnitId, Long salesTeamId);
	
}
