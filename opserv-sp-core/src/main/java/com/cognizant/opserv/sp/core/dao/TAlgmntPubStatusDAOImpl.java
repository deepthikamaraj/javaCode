package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TAlgmntPubStatus;
import com.cognizant.peg.core.dao.GenericDAO;


// TODO: Auto-generated Javadoc
/**
 * The Class TAlgmntPubStatusDAOImpl.
 * @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * @since 01/11/2014
 */
@Repository("tAlgmntPubStatusDAO")
public class TAlgmntPubStatusDAOImpl implements TAlgmntPubStatusDAO {
	
	
	/** The generic dao. */
	@Autowired
	private GenericDAO genericDAO;

	/**
	 * Gets the generic dao.
	 *
	 * @return the generic dao
	 */
	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	/**
	 * Sets the generic dao.
	 *
	 * @param genericDAO the new generic dao
	 */
	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}


	/**
	 * createAlgmntPublish
	 * @method 	createAlgmntPublish
	 * @param  TAlgmntPubStatus - holds tAlgmntPubStatus
	 * @return TAlgmntPubStatus- returns the TAlgmntPubStatus
	 **/
	public TAlgmntPubStatus createAlgmntPublish(final TAlgmntPubStatus tAlgmntPubStatus) {
				
		return genericDAO.store(tAlgmntPubStatus);
	}
	

	/**
	 * fetchAllPublishAlgmnt
	 * @method 	fetchAllPublishAlgmnt
	 * @param  Short - holds tenantId
	 * @return list- returns the list
	 **/
	@Override
	public List<TAlgmntPubStatus> fetchAllPublishAlgmnt(Short tenentID) {
		List<Object> params = new ArrayList<Object>();
		params.add(tenentID);
		return genericDAO.findEntitiesByNamedQuery("FindAllTAlgmntsPub", params);
		
	}

	
	/**
	 * fetchAllPublishAlgmntWithIds
	 * @method 	fetchAllPublishAlgmntWithIds
	 * @param  Short - holds tenantId
	 * @param Long - holds algmntId
	 * @param Long - holds bussUnitId
	 * @param Long - holds salesteam id
	 * @param String - holds category
	 * @return list- returns the list
	 **/
	@Override
	public List<TAlgmntPubStatus> fetchAllPublishAlgmntWithIds(Short tenentID,Long algmntId, Long bussUnitId, Long salesTeamId, String category )  {
		List<Object> params = new ArrayList<Object>();
		params.add(tenentID);
		params.add(algmntId);
		params.add(bussUnitId);
		params.add(salesTeamId);
		params.add(category);
		return genericDAO.findEntitiesByNamedQuery("FindAllTAlgmntsIdAlBuSt", params);
		
	}
	
	
	/**
	 * FindAllTAlgmntsIdAlBuStWithCategory
	 * @method 	FindAllTAlgmntsIdAlBuStWithCategory
	 * @param  Short - holds tenantId
	 * @param Long - holds algmntId
	 * @param Long - holds bussUnitId
	 * @param Long - holds salesteam id
	 * @return list- returns the list
	 **/
	public List<TAlgmntPubStatus> FindAllTAlgmntsIdAlBuStWithCategory(Short tenentID,Long algmntId, Long bussUnitId, Long salesTeamId )  {
		List<Object> params = new ArrayList<Object>();
		params.add(tenentID);
		params.add(algmntId);
		params.add(bussUnitId);
		params.add(salesTeamId);
		//params.add(category);
		return genericDAO.findEntitiesByNamedQuery("FindAllTAlgmntsIdAlBuStWithCategory", params);
		
	}
	

}
