package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TZipLoad;

// TODO: Auto-generated Javadoc
/**
 * The Interface TZipLoadDAO.
 * @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * @since 01/11/2014
 */
public interface TZipLoadDAO {
	
	/**
	 * Creates the t zip load.
	 *
	 * @param tZipLoad the t zip load
	 * @return the t zip load
	 */
	public TZipLoad createTZipLoad(final TZipLoad tZipLoad) ;
		
	/**
	 * Find t zip load.
	 *
	 * @param tZipLoad the t zip load
	 * @return the list
	 */
	public List<TZipLoad> findTZipLoad(final TZipLoad tZipLoad) ;
		
		

}
