package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TUsrWdg;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TUsrWdgDAO.
 * 
 * @author Bhaskar Reddy Lacchannagari
 * @version 1.0
 * 
 */
public interface TUsrWdgDAO {

	/**
	 * Stores a new TUsrWdg entity object in to the persistent store
	 * 
	 * @param tUsrWdg
	 *            tUsrWdg Entity object to be persisted
	 * @return tUsrWdg Persisted TUsrWdg object
	 */
	TUsrWdg createTUsrWdg(TUsrWdg tUsrWdg);
	
	
	/**
	 * Updates a TUsrWdg entity object in to the persistent store
	 * 
	 * @param tUsrWdg
	 *            TUsrWdg Entity object to be updated
	 * @return tUsrWdg Persisted TUsrWdg object
	 */
	TUsrWdg updateTUsrWdg(TUsrWdg tUsrWdg);


	/**
	 * Retrieve an TUsrWdg object based on given roleId and usrId.
	 * 
	 * @param roleId and usrId are
	 *            the foreign key values of the TUsrWdg Entity.
	 * @return an Object if it exists against given foreign key values. Returns null of
	 *         not found
	 */
	//List<TUsrWdg> findTUsrWdgsByIds(Integer roleId,Integer usrId);

	List<TUsrWdg> findTUsrWdgsByIds(SearchFilter<TUsrWdg> filter);


	List<TUsrWdg> findTUsrWdgsByWdgIds(SearchFilter<TUsrWdg> filter);
}
