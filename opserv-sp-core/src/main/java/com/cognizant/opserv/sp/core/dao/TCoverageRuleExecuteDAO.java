package com.cognizant.opserv.sp.core.dao;

import java.util.List;



/**
 * Interface represents API's of TAccStatusType DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */

public interface TCoverageRuleExecuteDAO {

	/**
	 * Fetch query by native query.
	 *
	 * @param finalQuery the final query
	 * @return the list
	 */
	List<Object> fetchQueryByNativeQuery(String finalQuery);

	
}
