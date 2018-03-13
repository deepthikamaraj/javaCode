package com.cognizant.opserv.sp.persistence.dao.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.common.CommonPropertiesUtil;
import com.cognizant.opserv.sp.batch.common.DataRepType;
import com.cognizant.opserv.sp.batch.dao.TenantSchemaDAO;
import com.cognizant.opserv.sp.persistence.dao.service.ITriggerStoredProcDAO;
import com.cognizant.opserv.sp.storeproc.ExecuteStoreProc;
import com.cognizant.opserv.tenant.ctx.TenantContext;
import com.cognizant.opserv.tenant.vo.TenantSchema;
import com.cognizant.peg.core.exception.OpservDataAccessException;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.mchange.v2.c3p0.ComboPooledDataSource;

@Repository
public class TriggerSPStoredProcDAOImpl implements ITriggerStoredProcDAO {
	
	/** The tenantSchemaDAO. */
	@Autowired 
	private TenantSchemaDAO tenantSchemaDAO;
	
	/** The spStore jdbc template. */
	@Autowired
	@Qualifier("defaultJdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	
	@Value("${sp.stored.proc.name}")
	private String spStoredProcName;
	
	private Map<String,JdbcTemplate> tenantConfigJdbcTemplates = new HashMap<String,JdbcTemplate>();

	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TriggerSPStoredProcDAOImpl.class);
	
	/* (non-Javadoc)
	 * @see com.cognizant.opserv.sp.persistence.dao.service.ITriggerStoredProcDAO#triggerStoredProc()
	 */
	public void triggerStoredProc() throws OpservDataAccessException {
		
		LOGGER.info("triggerStoredProc for SP DB");
		List<String> tenantCodes = null;
		if(CommonPropertiesUtil.isMultiTenancyEnabled()) {
			tenantCodes = getTenantCodes();
		}else{
			tenantCodes = new ArrayList<String>();
			tenantCodes.add("defaultTenant");
		}
		if(tenantCodes != null && !tenantCodes.isEmpty()){
			try {
				final CountDownLatch latch = new CountDownLatch(tenantCodes.size());
				LOGGER.info("CountDownLatch latch : "+latch.getCount());
				
				for(String tenantCd : tenantCodes){
					LOGGER.info("triggerStoredProc for SP DB. tenantCd : "+tenantCd + ", spStoredProcName : "+spStoredProcName);
					TenantContext.setTenantKey(tenantCd);
					JdbcTemplate jdbcTemplate = getJdbcTemplate(DataRepType.APPDB.getDataRepTypeId());
					ExecuteStoreProc executeSPStoreProc = new ExecuteStoreProc(jdbcTemplate, spStoredProcName, latch);
					Thread executeSPThread = new Thread(executeSPStoreProc,tenantCd);
					executeSPThread.start();
				}
				
				latch.await();
			} catch (InterruptedException interruptEx) {
				LOGGER.error("triggerStoredProc, Main thread interrupted : "+interruptEx.getMessage());
				throw new OpservDataAccessException("triggerStoredProc, Main thread interrupted : ", interruptEx);
			}
		}
	}
	
	/**
	 * @return
	 * @throws OpservDataAccessException
	 */
	private List<String> getTenantCodes() throws OpservDataAccessException {
		LOGGER.info("getTenantCodes");
		return tenantSchemaDAO.getTenantCodes();
	}

	
	
	/**
	 * @Method getJdbcTemplate-  To getJdbcTemplate.
	 *
	 * @return JdbcTemplate to hold the staging DataSource
	 */	
	private JdbcTemplate getJdbcTemplate(int dataRepType) throws OpservDataAccessException {
		LOGGER.info("getJdbcTemplate");

		if(!CommonPropertiesUtil.isMultiTenancyEnabled()) {
			return jdbcTemplate;
		}
		
		TenantSchema tenantSchema = tenantSchemaDAO.getSPDataSourceByTenantCd(dataRepType, TenantContext.getTenantKey());
		if(tenantSchema == null) {
			throw new OpservDataAccessException("SP Schema for tenant cannot be found");
		}
		
		String jdbcUrl = "jdbc:mysql://"+ tenantSchema.getServerName() + ":3306/"+tenantSchema.getDbName();
		if(tenantConfigJdbcTemplates .containsKey(jdbcUrl)) {
			return tenantConfigJdbcTemplates.get(jdbcUrl);
		} 
		LOGGER.info("jdbcUrl :: "+jdbcUrl);
		ComboPooledDataSource ds = tenantSchemaDAO.createComboPooledDS(tenantSchema);
		
    	JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);
    	tenantConfigJdbcTemplates.put(jdbcUrl,jdbcTemplate);
    	return jdbcTemplate;		
	}
	

}
