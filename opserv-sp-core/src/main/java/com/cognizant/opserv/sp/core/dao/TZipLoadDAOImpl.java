package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TZipLoad;
import com.cognizant.peg.core.dao.GenericDAO;


// TODO: Auto-generated Javadoc
/**
 * The Class TZipLoadDAOImpl.
 * @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * @since 01/11/2014
 */
@Repository("tZipLoadDAO")
public class TZipLoadDAOImpl implements TZipLoadDAO {

	
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
	public void setGenericDAO(GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}
	
	
	/**
	 * createTZipLoad
	 * @method 	createTZipLoad
	 * @param  TZipLoad - holds TZipLoad
	 * @return TZipLoad- returns the TZipLoad
	 **/
	public TZipLoad createTZipLoad(final TZipLoad tZipLoad) {
	//	LOGGER.info("=========== Create TAlgmntBussRule ===========");
		return genericDAO.store(tZipLoad);
		
		
	}
	
	
	/**
	 * findTZipLoad
	 * @method 	findTZipLoad
	 * @param  TZipLoad - holds tZipLoad
	 * @return list- returns the list
	 **/
	public List<TZipLoad> findTZipLoad(final TZipLoad tZipLoad) {
		List<Object> params = new ArrayList<Object>();
		params.add(tZipLoad.getDsName());
		params.add(tZipLoad.getTenantId());
			
		return genericDAO.findEntitiesByNamedQuery("FindtZipLoad" , params);
		
	
		}
	
	

}
