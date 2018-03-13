package com.cognizant.opserv.sp.core.dao;

import java.util.List;

/**
 * Interface represents API's of TBelgiumZip DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TBelgiumZipDAO {

	public List<String> getTBelgiumZips(String query);
	
	public List<String> findTBelgiumZipsPolygon();

}
