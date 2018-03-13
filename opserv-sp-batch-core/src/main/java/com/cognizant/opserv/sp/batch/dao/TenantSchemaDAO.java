package com.cognizant.opserv.sp.batch.dao;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.common.CommonPropertiesUtil;
import com.cognizant.opserv.sp.batch.common.DataRepType;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.tenant.ctx.TenantContext;
import com.cognizant.opserv.tenant.vo.TenantSchema;
import com.cognizant.peg.core.exception.OpservDataAccessException;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.mchange.v2.c3p0.ComboPooledDataSource;

// TODO: Auto-generated Javadoc
/**
 * ****************************************************************************.
 *
 * @class TenantSchemaDAO
 * @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * @since 27/11/2014
 * ***************************************************************************
 */

@Repository
public class TenantSchemaDAO {

	@Autowired
	private JdbcTemplate tenantConfigJdbcTemplate;
	
	@Value("${stagingTenantDB.driverClassName}")
	private String driverClassName;
	
	/** The Constant LOGGER. */
	private static final OpservLogger  LOGGER = OpservLoggerFactory.getOpservLogger(TenantSchemaDAO.class);
	
	
	/**
	 * @param tenantSchema
	 * @return ComboPooledDataSource
	 */
	public ComboPooledDataSource createComboPooledDS(TenantSchema tenantSchema) {
		ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
		try {
			comboPooledDataSource.setDriverClass(driverClassName);
			comboPooledDataSource.setJdbcUrl("jdbc:mysql://"+ tenantSchema.getServerName() + ":3306/"+tenantSchema.getDbName() + "?autoReconnect=true");
			comboPooledDataSource.setUser(tenantSchema.getUsrName());
			comboPooledDataSource.setPassword(tenantSchema.getPasswrd());
			// TODO : Make it configurable
			comboPooledDataSource.setInitialPoolSize(5);
			comboPooledDataSource.setMaxPoolSize(10);
			comboPooledDataSource.setMinPoolSize(10);
			LOGGER.info("pooled data source for db url = "
					+ comboPooledDataSource.getJdbcUrl() + " init pool size = "
					+ comboPooledDataSource.getInitialPoolSize()
					+ " min pool size = "
					+ comboPooledDataSource.getMinPoolSize()
					+ " max pool size = "
					+ comboPooledDataSource.getMaxPoolSize()
					+ " driver class = "
					+ comboPooledDataSource.getDriverClass());
			return comboPooledDataSource;
		} catch (Exception e) {
			LOGGER.error("Error while createComboPooledDS : "+e.getMessage());
			return null;
		}
	}
	

	/**
	 * @param tenantCode
	 * @return TenantSchema
	 */
	public TenantSchema getSPDataSourceByTenantCd(int dataRepType, String tenantCode) {
		LOGGER.info("getSPDataSourceByTenantCd dataRepType : "+dataRepType+ ", tenantCode : "+tenantCode);
		
		StringBuffer selectSQL = new StringBuffer(" SELECT DISTINCT server_name, db_name, usr_name, pwd ")
		.append(" FROM t_sch_config s , t_tenant t where ")
		.append(" s.data_rep_type_id = ? AND ")
		.append(" t.tenant_cd = ? AND ")
		.append(" t.tenant_id = s.tenant_id AND ") 
		.append(" s.active_flag = t.active_flag AND ")
		.append(" t.active_flag = 'Y' ");

		LOGGER.info("getSPDataSourceByTenantCd selectSQL : "+selectSQL);

		Object [] args = {dataRepType,tenantCode};
		List<TenantSchema> tenantSchemas = null;
		
		try {
				tenantSchemas = tenantConfigJdbcTemplate.query(selectSQL.toString(),args, new RowMapper<TenantSchema>() {
					public TenantSchema mapRow(ResultSet rs, int rowNum) throws SQLException {
						TenantSchema tenantSchema = new TenantSchema();
						tenantSchema.setServerName(rs.getString("server_name"));
						tenantSchema.setDbName(rs.getString("db_name"));
						tenantSchema.setUsrName(rs.getString("usr_name"));
						tenantSchema.setPasswrd(rs.getString("pwd"));
						return tenantSchema;
					}
				});
				if(tenantSchemas != null && !tenantSchemas.isEmpty()) {
					LOGGER.info("getSPDataSourceByTenantCd tenantSchemas not null");
					return tenantSchemas.get(0);
				}
		} catch (Exception sqle) {
			LOGGER.error("Error in getSPDataSourceByTenantId", sqle);
		}
		return null;
	}	
	
	
	/**
	 * @return List<String> tenantCodes
	 */
	public List<UserDetails> getUserdetails(){
		StringBuffer selectSQL = new StringBuffer(" SELECT DISTINCT tenant_cd, tenant_id FROM t_tenant ");
		LOGGER.info("getTenantCodes selectSQL : "+selectSQL);
		
		List<UserDetails> userdetailList = null;
		try {
			userdetailList = tenantConfigJdbcTemplate.query(selectSQL.toString(), new RowMapper<UserDetails>() {
				public UserDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
					UserDetails userDetails = new UserDetails();
					userDetails.setTenantCode(rs.getString("tenant_cd"));
					userDetails.setTenantId(rs.getShort("tenant_id"));
					return userDetails;
				}
			});
			if(userdetailList != null && !userdetailList.isEmpty()){
				return userdetailList;
			}
	} catch (Exception sqle) {
		LOGGER.error("Error in getTenantCodes", sqle);
	}
	return null;
	}
	
	/**
	 * @return List<String> tenantCodes
	 */
	public List<String> getTenantCodes(){
		StringBuffer selectSQL = new StringBuffer(" SELECT DISTINCT tenant_cd FROM t_tenant ");
		LOGGER.info("getTenantCodes selectSQL : "+selectSQL);
		
		List<String> tenantCodes = null;
		try {
			tenantCodes = tenantConfigJdbcTemplate.query(selectSQL.toString(), new RowMapper<String>() {
				public String mapRow(ResultSet rs, int rowNum) throws SQLException {
					String tenantCode = rs.getString("tenant_cd");
					return tenantCode;
				}
			});
			if(tenantCodes != null && !tenantCodes.isEmpty()){
				return tenantCodes;
			}
	} catch (Exception sqle) {
		LOGGER.error("Error in getTenantCodes", sqle);
	}
	return null;
	}
	
	/**
	 * @Method getJdbcTemplate-  To getJdbcTemplate.
	 *
	 * @return JdbcTemplate to hold the staging DataSource
	 */	
	public JdbcTemplate getJdbcTemplate(Map<String,JdbcTemplate> tenantConfigJdbcTemplates, JdbcTemplate jdbcTemplate)
			throws OpservDataAccessException {

		if(!CommonPropertiesUtil.isMultiTenancyEnabled()) {
			return jdbcTemplate;
		}
		
		TenantSchema tenantSchema = this.getSPDataSourceByTenantCd(DataRepType.APPDB.getDataRepTypeId(), TenantContext.getTenantKey());
		if(tenantSchema == null) {
			throw new OpservDataAccessException("SP Schema for tenant cannot be found");
		}
		
		String jdbcUrl = "jdbc:mysql://"+ tenantSchema.getServerName() + ":3306/"+tenantSchema.getDbName() + "?autoReconnect=true";
//		LOGGER.info("jdbcUrl : "+jdbcUrl);
		if(tenantConfigJdbcTemplates .containsKey(jdbcUrl)) {
			return tenantConfigJdbcTemplates.get(jdbcUrl);
		} 
		
		ComboPooledDataSource ds = this.createComboPooledDS(tenantSchema);
		
    	JdbcTemplate jdbcTemplateMultiTenant = new JdbcTemplate(ds);
    	tenantConfigJdbcTemplates.put(jdbcUrl,jdbcTemplateMultiTenant);
    	
    	return jdbcTemplateMultiTenant;		
	}
}
	
	
