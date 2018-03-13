package com.cognizant.opserv.sp.core.dao;


import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.cognizant.opserv.query.IQuery;
import com.cognizant.opserv.query.InvalidQueryException;

/**
 * Interface represents API's of TAlgmntViewStore DAO.
 * 
 * @author Cognizant Technology Solutions
 * @version 1.0
 * 
 */
public interface TAlgmntViewStoreDAO {

	
	/**
	 * @param tableEntityName
	 * @param headerInfoQueryGeneration 
	 * @param QueryCriteria 
	 * @return List<ViewData>
	 * @throws InvalidQueryException 
	 */
	List<Object []> findTCustAlgmntViewStoreByNativeQuery(String tableEntityName, List<String> selectParameterList, Map<String, String> headerInfoQueryGeneration, IQuery queryStructure) throws InvalidQueryException;
	
	/**
	 * @param entityTableName
	 * @param queryStructure
	 * @param headerInfoQueryGeneration 
	 * @return
	 * @throws InvalidQueryException
	 */
	BigInteger findResultCountBasedOnSearchCriteria(String entityTableName, IQuery queryStructure, Map<String, String> headerInfoQueryGeneration) throws InvalidQueryException;

}
