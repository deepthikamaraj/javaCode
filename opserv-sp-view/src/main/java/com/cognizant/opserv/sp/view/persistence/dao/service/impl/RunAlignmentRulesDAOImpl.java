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
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.view.persistence.dao.service.IRunAlignmentRulesDAO;
import com.cognizant.opserv.tenant.ctx.TenantContext;
import com.cognizant.opserv.tenant.vo.TenantSchema;
import com.cognizant.peg.core.exception.OpservDataAccessException;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * ****************************************************************************.
 *
 * @class RunAlignmentRulesDAOImpl  
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 * ***************************************************************************
 */
@Repository("runAlignmentRulesDAO")
public class RunAlignmentRulesDAOImpl implements IRunAlignmentRulesDAO{
	
	/** The default sp jdbc template. */
	@Autowired
	@Qualifier("defaultJdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	
	/** The tenantSchemaDAO. */
	@Autowired 
	private TenantSchemaDAO tenantSchemaDAO;
	
	/** The Constant LOGGER. */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(RunAlignmentRulesDAOImpl.class);
	
	private Map<String,JdbcTemplate> tenantConfigJdbcTemplates = new HashMap<String,JdbcTemplate>();


	@Override
	public void runAlgmntRules(Long salesPosId, Long entityId, String entityType, UserDetails userDetails) {
		LOGGER.info("Inside runAlgmntRules in RunAlignmentRulesDAOImpl Class");
		try{
			String procName = getStoredProcName();
			LOGGER.info("procName : "+procName);
			StringBuffer storedProcCall = getStoredProcCall(salesPosId, entityId, procName);
			LOGGER.info("StoredProcCall Query" + storedProcCall);
			tenantSchemaDAO.getJdbcTemplate(tenantConfigJdbcTemplates, jdbcTemplate).execute(storedProcCall.toString());
			LOGGER.info("StoredProc Query exceuted successfully");
		} catch (DataAccessException dataAccessEx) {
			LOGGER.error("Data Access Exception occurred in RunAlignmentRulesDAOImpl runAlgmntRules execute, most specific cause : "+dataAccessEx.getMostSpecificCause());
			throw new OpservDataAccessException("Data Access Exception occurred in runAlgmntRules", dataAccessEx);
		} catch (Exception e) {
			throw new OpservDataAccessException("Exception occurred in runAlgmntRules", e);
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
	 * @param type
	 * @param userDetails
	 * @return
	 */
	private String getStoredProcName(){
		String procName = "SP_ALIGNMENT_RULES";
		return procName;
	}

	
	
	/**
	 * @param salesPosId
	 * @param customerId
	 * @param procName
	 * @return storedProcCall
	 */
	private StringBuffer getStoredProcCall(Long salesPosId, Long entityId,
			String procName) {
		StringBuffer storedProcCall = new StringBuffer();
		
		storedProcCall.append(" CALL "+ procName + " ( "+ salesPosId + ", "+ entityId + " ) ");
//		storedProcCall.append(" CALL "+ procName + " ( )");
		return storedProcCall;
	}

}
