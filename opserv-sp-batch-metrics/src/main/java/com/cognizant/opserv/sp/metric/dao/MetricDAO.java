package com.cognizant.opserv.sp.metric.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.common.CommonPropertiesUtil;
import com.cognizant.opserv.sp.common.DataRepType;
import com.cognizant.opserv.tenant.ctx.TenantContext;
import com.cognizant.opserv.tenant.vo.TenantSchema;
import com.cognizant.peg.core.exception.OpservDataAccessException;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * ****************************************************************************.
 *
 * @class MetricDAO
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 * ***************************************************************************
 */
@Repository
public class MetricDAO {

	/** The default jdbcTemplate */
	@Autowired
	public JdbcTemplate defaultJdbcTemplate;
	
	/** The default jdbcTemplate */
	@Autowired
	public NamedParameterJdbcTemplate defaultNamedParameterJdbcTemplate;
	
	/** The jdbcTemplate - for multi tenancy */
	@Autowired
	private JdbcTemplate tenantConfigJdbcTemplate;
	
	/** The tenantConfigJdbcTemplates */
	private Map<String,JdbcTemplate> tenantConfigJdbcTemplates = new HashMap<String,JdbcTemplate>();
	
	/** The namedParameterJdbcTemplate */
	private Map<String,NamedParameterJdbcTemplate> tenantConfigNamedParameterJdbcTemplates = new HashMap<String,NamedParameterJdbcTemplate>();
	
	/** The driverClassName */
	@Value("${stagingTenantDB.driverClassName}")
	private String driverClassName;
	
	/** The Constant LOGGER. */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(MetricDAO.class);
	
	/**
	 * @method createComboPooledDS
	 * @param tenantSchema
	 * @return ComboPooledDataSource
	 */
	public ComboPooledDataSource createComboPooledDS(TenantSchema tenantSchema) {
		ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
		try {
			comboPooledDataSource.setDriverClass(driverClassName);
			comboPooledDataSource.setJdbcUrl("jdbc:mysql://"+ tenantSchema.getServerName() + ":3306/"+tenantSchema.getDbName());
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
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * @method getSPDataSourceByTenantCd
	 * @param tenantCode
	 * @return TenantSchema
	 */
	public TenantSchema getSPDataSourceByTenantCd(String tenantCode) {
		StringBuffer selectSQL = new StringBuffer(" SELECT DISTINCT server_name, db_name, usr_name, pwd ")
		.append(" FROM t_sch_config s , t_tenant t where ")
		.append(" s.data_rep_type_id = ? AND ")
		.append(" t.tenant_cd = ? AND ")
		.append(" t.tenant_id = s.tenant_id AND ") 
		.append(" s.active_flag = t.active_flag AND ")
		.append(" t.active_flag = 'Y' ");

		Object [] args = {DataRepType.APPDB.getDataRepTypeId(),tenantCode};
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
				return tenantSchemas.get(0);
			}
		} catch (Exception sqle) {
			sqle.printStackTrace();
			LOGGER.error("Error in getSPDataSourceByTenantId", sqle);
		}
		return null;
	}	


	/**
	 * @method getJdbcTemplate
	 * @return JdbcTemplate
	 * @throws OpservDataAccessException
	 */
	public JdbcTemplate getJdbcTemplate() throws OpservDataAccessException {

		if(!CommonPropertiesUtil.isMultiTenancyEnabled()) {
			LOGGER.info("==Single Tenant===");
			return defaultJdbcTemplate;
		}

		TenantSchema tenantSchema = getSPDataSourceByTenantCd(TenantContext.getTenantKey());
		if(tenantSchema == null) {
			throw new OpservDataAccessException("SP Schema for tenant cannot be found");
		}
		LOGGER.info("=== tenantSchema === ");
		LOGGER.info(tenantSchema.toString());

		String jdbcUrl = "jdbc:mysql://"+ tenantSchema.getServerName() + ":3306/"+tenantSchema.getDbName();
		if(tenantConfigJdbcTemplates .containsKey(jdbcUrl)) {
			return tenantConfigJdbcTemplates.get(jdbcUrl);
		} 

		ComboPooledDataSource ds = createComboPooledDS(tenantSchema);

		JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);
		tenantConfigJdbcTemplates.put(jdbcUrl,jdbcTemplate);
		LOGGER.info("==Multi Tenant===");
		LOGGER.info("=== jdbcTemplate === " + jdbcTemplate.toString());
		return jdbcTemplate;		
	}

	/**
	 * @method getNamedParameterJdbcTemplate
	 * @return NamedParameterJdbcTemplate
	 * @throws OpservDataAccessException
	 */
	public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() throws OpservDataAccessException {

		LOGGER.info("===in getNamedParameterJdbcTemplate === ");
		if(!CommonPropertiesUtil.isMultiTenancyEnabled()) {
			LOGGER.info("===return jdbcTemplate === ");
			return defaultNamedParameterJdbcTemplate;
		}
		LOGGER.info("Getting the Named Parameter Jdbc Template ");

		TenantSchema tenantSchema = getSPDataSourceByTenantCd(TenantContext.getTenantKey());
		if(tenantSchema == null) {
			throw new OpservDataAccessException("SP Schema for tenant cannot be found");
		}
		LOGGER.info("=== tenantSchema === ");
		LOGGER.info(tenantSchema.toString());

		String jdbcUrl = "jdbc:mysql://"+ tenantSchema.getServerName() + ":3306/"+tenantSchema.getDbName();
		if(tenantConfigNamedParameterJdbcTemplates .containsKey(jdbcUrl)) {
			return tenantConfigNamedParameterJdbcTemplates.get(jdbcUrl);
		} 

		ComboPooledDataSource ds = createComboPooledDS(tenantSchema);

		NamedParameterJdbcTemplate defaultNamedParameterJdbcTemplate = new NamedParameterJdbcTemplate(ds);
		tenantConfigNamedParameterJdbcTemplates.put(jdbcUrl,defaultNamedParameterJdbcTemplate);
		LOGGER.info("==Multi Tenant===");
		LOGGER.info("=== namedParameterJdbcTemplate === " + defaultNamedParameterJdbcTemplate.toString());
		return defaultNamedParameterJdbcTemplate;
	}
}
