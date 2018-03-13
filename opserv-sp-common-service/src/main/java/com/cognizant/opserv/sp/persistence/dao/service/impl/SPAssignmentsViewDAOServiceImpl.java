package com.cognizant.opserv.sp.persistence.dao.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.common.BussObj;
import com.cognizant.opserv.sp.common.CommonConstants;
import com.cognizant.opserv.sp.core.dao.TBussAttrDAO;
import com.cognizant.opserv.sp.core.dao.TBussEntityDAO;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.persistence.dao.service.SPAssignmentsViewDAOService;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.exception.OpservDataAccessException;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;

@Repository
@Qualifier("sPAssignmentsViewDAOServiceImpl")
public class SPAssignmentsViewDAOServiceImpl implements SPAssignmentsViewDAOService {
	
	/**
	 * The TBussAttrDAO
	 */
	@Autowired
	private TBussAttrDAO bussAttrDAO;
	
	/**
	 * The TBussEntityDAO
	 */
	@Autowired
	private TBussEntityDAO bussEntityDAO; 
	
	/**
	 * The GenericDAO
	 */
	@Autowired
	private GenericDAO genericDAO;
	
	/**
	 * Map for storing the EntityTableNames corresponding to the bussFunctionType
	 */
	private Map<String, List<String>> mappedEntityTableNames = new HashMap<String, List<String>>();
	
	/**
	 * Map for storing the time of creation of table names corresponding to the bussFunctionType
	 */
	private Map<String, Long> tableStoreTime = new HashMap<String, Long>();
	
	@Value("${time.diff.for.refresh.matViewName}")
	private Long maxTimeDiffToRefresh;
	
	/** The Constant LOGGER. */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(SPAssignmentsViewDAOServiceImpl.class);

	/* (non-Javadoc)
	 * @see com.cognizant.opserv.sp.persistence.dao.service.SPAssignmentsViewDAOService#markCustAlgmntFlagAsDirty(java.lang.Long, java.lang.Long, com.cognizant.opserv.sp.model.auth.UserDetails)
	 */
	@Override
	public void markCustAlgmntFlagAsDirty(Long spId, Long entityId, UserDetails userDetails) throws OpservDataAccessException {
		LOGGER.info("============Inside SPAssignmentsViewDAOServiceImpl.markCustAlgmntFlagAsDirty() DAOService=============");
		try{
			String bussObjName = BussObj.CUSTOMER_ALIGNMENT_VIEW.getBussObjName();
			String bussFunctionType = CommonConstants.CUSTOMER_MAT_VIEW;
			List<String> entityTableNames = getTableName(bussFunctionType, userDetails);
			if(null != entityTableNames && entityTableNames.size() != 0){
				for(String entityTableName : entityTableNames){
					LOGGER.info("============Materialized view exists...starting=============");
					LOGGER.info("Printing entityTableName ... "+entityTableName);
					String updateQuery = "update "+entityTableName+" set dirty_flag = 'Y' where SalesPositionID = "+spId+" and Customer_ID = "+entityId+" ;";
					LOGGER.info(updateQuery);
					genericDAO.executeUpdateOnNativeQuery(updateQuery);
					LOGGER.info("============Materialized view execution completed=============");
				}
			}	
		}catch(RuntimeException runtimeException){
			throw new OpservDataAccessException("Error during markCustAlgmntFlagAsDirty",runtimeException);
		}
		LOGGER.info("============Transaction Completed=============");
	}
	
	/* (non-Javadoc)
	 * @see com.cognizant.opserv.sp.persistence.dao.service.SPAssignmentsViewDAOService#markChangeRequestFlagAsDirty(java.lang.Long, java.lang.Long, com.cognizant.opserv.sp.model.auth.UserDetails)
	 */
	@Override
	public void markChangeRequestFlagAsDirty(Long entityId, UserDetails userDetails) throws OpservDataAccessException {
		LOGGER.info("============Inside SPAssignmentsViewDAOServiceImpl.markChangeRequestFlagAsDirty() DAOService=============");
		try{
			String bussObjName = BussObj.CHANGE_REQUEST_VIEW.getBussObjName();
			String bussFunctionType = CommonConstants.CR_MAT_VIEW;
			List<String> entityTableNames = getTableName(bussFunctionType, userDetails);
			if(null != entityTableNames && entityTableNames.size() != 0){
				for(String entityTableName : entityTableNames){
					LOGGER.info("============Materialized view exists...starting=============");
					String updateQuery = "update "+entityTableName+" set dirty_flag = 'Y' where CR_ID = "+entityId+" ;";
					LOGGER.info(updateQuery);
					genericDAO.executeUpdateOnNativeQuery(updateQuery);
					LOGGER.info("============Materialized view execution completed=============");
				}
			}
		}catch(RuntimeException runtimeException){
			throw new OpservDataAccessException("Error during markChangeRequestFlagAsDirty",runtimeException);
		}
		LOGGER.info("============Transaction Completed=============");
	}

	/* (non-Javadoc)
	 * @see com.cognizant.opserv.sp.persistence.dao.service.SPAssignmentsViewDAOService#markZipSalesPosFlagAsDirty(java.lang.Long, java.lang.String, com.cognizant.opserv.sp.model.auth.UserDetails)
	 */
	@Override
	public void markZipSalesPosFlagAsDirty(Long spId, String zipCode, UserDetails userDetails) throws OpservDataAccessException {
		LOGGER.info("============Inside SPAssignmentsViewDAOServiceImpl.markZipSalesPosFlagAsDirty() DAOService=============");
		try{
			String bussObjName = BussObj.ZIP_SALESPOS_VIEW.getBussObjName();
			String bussFunctionType = CommonConstants.ZIP_MAT_VIEW;
			List<String> entityTableNames = getTableName(bussFunctionType, userDetails);
			if(null != entityTableNames && entityTableNames.size() != 0){
				for(String entityTableName : entityTableNames){
					LOGGER.info("============Materialized view exists...starting=============");
					String updateQuery = "update "+entityTableName+" set dirty_flag = 'Y' where SalesPositionID = "+spId+" and zipCode = "+zipCode+" ;";
					genericDAO.executeUpdateOnNativeQuery(updateQuery);
					LOGGER.info("============Materialized view execution completed=============");
				}
			}
		}catch(RuntimeException runtimeException){
			throw new OpservDataAccessException("Error during markZipSalesPosFlagAsDirty",runtimeException);
		}
		LOGGER.info("============Transaction Completed=============");
	}
	
	/**
	 * @param bussFunctionType
	 * @param userDetails
	 * @return
	 */
	private List<String> getTableName(String bussFunctionType, UserDetails userDetails){
		LOGGER.info("============Inside SPAssignmentsViewDAOServiceImpl.getTableName() DAOService for fetching list of tables based on condition=============");
		
		List<String> entityTableNames = null;
		try{
			if (!mappedEntityTableNames.containsKey(bussFunctionType)) {
				LOGGER.info("============Hitting the DB for the first time to fetch table names...started=============");
				entityTableNames = getTableNameFromDB(bussFunctionType, userDetails);				//hit the DB for the first time
				LOGGER.info("============Hitting the DB for the first time to fetch table names...ended=============");
			}
			else {
				Long storedTime = tableStoreTime.get(bussFunctionType);
				if(System.currentTimeMillis() - storedTime > maxTimeDiffToRefresh){
					LOGGER.info("============Hitting the DB due to timeout to fetch table names...started=============");
					entityTableNames = getTableNameFromDB(bussFunctionType, userDetails);			//hit the DB again as timeout has occurred
					LOGGER.info("============Hitting the DB due to timeout to fetch table names...ended=============");
				}
				else{
					LOGGER.info("============fetching table names from data stored in map...started=============");
					entityTableNames = mappedEntityTableNames.get(bussFunctionType);				//fetching from data stored in map
					LOGGER.info("============fetching table names from data stored in map...ended=============");
				}
			}
		}catch(RuntimeException runtimeException){
			throw new OpservDataAccessException("Error during getTableName",runtimeException);
		}	
		return entityTableNames;
	}
	
	/**
	 * @param bussFunctionType
	 * @param userDetails
	 * @return
	 */
	private List<String> getTableNameFromDB(String bussFunctionType, UserDetails userDetails){
		LOGGER.info("============Inside SPAssignmentsViewDAOServiceImpl.getTableNameFromDB() DAOService for fetching list of tables=============");
		
		List<String> entityTableNames = null;
		
		try {
			LOGGER.info("============Hitting the DB to fetch table names...started=============");
			entityTableNames = bussEntityDAO.getTBussEntityNameByEntityType(bussFunctionType, userDetails.getTenantId());
			mappedEntityTableNames.put(bussFunctionType, entityTableNames);
			tableStoreTime.put(bussFunctionType, System.currentTimeMillis());
			LOGGER.info("============Hitting the DB to fetch table names...ended=============");
		} catch (RuntimeException rte) {
			throw new OpservDataAccessException("RunTime Exceptiion occurs in getTableName of SPAssignmentsViewDAOServiceImpl :: "+ rte.getMessage());
		}
		return entityTableNames;
	}
	
}
