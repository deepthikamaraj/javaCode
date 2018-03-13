package com.cognizant.opserv.sp.view.persistence.dao.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.common.CommonPropertiesUtil;
import com.cognizant.opserv.sp.batch.common.DataRepType;
import com.cognizant.opserv.sp.batch.dao.TenantSchemaDAO;
import com.cognizant.opserv.sp.common.CommonConstants;
import com.cognizant.opserv.sp.common.EventType;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.view.persistence.dao.service.IZipViewGeneratorDAO;
import com.cognizant.opserv.tenant.ctx.TenantContext;
import com.cognizant.opserv.tenant.vo.TenantSchema;
import com.cognizant.peg.core.exception.OpservDataAccessException;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * ****************************************************************************.
 *
 * @class ZipViewGeneratorDAOImpl
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 11/05/2016
 * ***************************************************************************
 */

@Repository("zipViewGeneratorDAO")
public class ZipViewGeneratorDAOImpl implements IZipViewGeneratorDAO{
	
	/** The spStore jdbc template. */
	@Autowired
	@Qualifier("defaultJdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	
	/** The tenantSchemaDAO. */
	@Autowired 
	private TenantSchemaDAO tenantSchemaDAO;
	
	/** The Constant LOGGER. */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(ZipViewGeneratorDAOImpl.class);
	
	private Map<String,JdbcTemplate> tenantConfigJdbcTemplates = new HashMap<String,JdbcTemplate>();

	@Override
	public void generateZipViewDAO(Long salesPosId, String zipCode, String entityType, EventType eventType, UserDetails userDetails) throws OpservDataAccessException {
		LOGGER.info("Inside generateZipViewDAO in ZipViewGeneratorDAOImpl Class");
		try{
			String procName = CommonConstants.ZIP_PROC_NAME;
			LOGGER.info("procName : "+procName);
			StringBuffer storedProcCall = getStoredProcCall(salesPosId, zipCode, procName);
			LOGGER.info("StoredProcCall Query" + storedProcCall);
			tenantSchemaDAO.getJdbcTemplate(tenantConfigJdbcTemplates, jdbcTemplate).execute(storedProcCall.toString());
			LOGGER.info("StoredProc Query exceuted successfully");
		} catch (DataAccessException dataAccessEx) {
			LOGGER.error("Data Access Exception occurred in ZipViewGeneratorDAOImpl generateZipViewDAO execute, most specific cause : "+dataAccessEx.getMostSpecificCause());
			throw new OpservDataAccessException("Data Access Exception occurred in generateZipViewDAO", dataAccessEx);
		} catch (Exception e) {
			throw new OpservDataAccessException("Exception occurred in generateZipViewDAO", e);
		}
	}
	
//	/**
//	 * @Method getJdbcTemplate-  To getJdbcTemplate.
//	 *
//	 * @return JdbcTemplate to hold the staging DataSource
//	 */	
//	private JdbcTemplate getJdbcTemplate() throws OpservDataAccessException {
//
//		if(!CommonPropertiesUtil.isMultiTenancyEnabled()) {
//			return jdbcTemplate;
//		}
//		
//		TenantSchema tenantSchema = tenantSchemaDAO.getSPDataSourceByTenantCd(DataRepType.APPDB.getDataRepTypeId(), TenantContext.getTenantKey());
//		if(tenantSchema == null) {
//			throw new OpservDataAccessException("SP Schema for tenant cannot be found");
//		}
//		
//		String jdbcUrl = "jdbc:mysql://"+ tenantSchema.getServerName() + ":3306/"+tenantSchema.getDbName();
//		if(tenantConfigJdbcTemplates .containsKey(jdbcUrl)) {
//			return tenantConfigJdbcTemplates.get(jdbcUrl);
//		} 
//		
//		ComboPooledDataSource ds = tenantSchemaDAO.createComboPooledDS(tenantSchema);
//		
//    	JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);
//    	tenantConfigJdbcTemplates.put(jdbcUrl,jdbcTemplate);
//    	return jdbcTemplate;		
//	}

	/**
	 * @param salesPosId
	 * @param customerId
	 * @param procName
	 * @return storedProcCall
	 */
	private StringBuffer getStoredProcCall(Long salesPosId, String zipCode, String procName) {
		StringBuffer storedProcCall = new StringBuffer();
		
		storedProcCall.append(" CALL "+ procName + " ( "+ salesPosId + ", "+ zipCode + ") ");
		return storedProcCall;
	}
	
}
