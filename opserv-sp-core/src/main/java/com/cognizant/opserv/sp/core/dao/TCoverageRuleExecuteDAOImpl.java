package com.cognizant.opserv.sp.core.dao;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;

/**
 * Class provides API implementation for TCustAddrDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository
public class TCoverageRuleExecuteDAOImpl implements TCoverageRuleExecuteDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TCoverageRuleExecuteDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	/**
	 * Fetch query by native query.
	 *
	 * @param finalQuery the final query
	 * @return the list
	 */
	@Override
	public List<Object> fetchQueryByNativeQuery(final String finalQuery) {
		LOGGER.info("=========== fetchQueryByNativeQuery ===========");
		return genericDAO.findByNativeQuery(finalQuery);
	}

	

}
