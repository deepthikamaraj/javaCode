package com.cognizant.opserv.sp.rest.util;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cognizant.opserv.tenant.common.TenantHolder;
import com.cognizant.opserv.tenant.ctx.TenantContext;
import com.cognizant.opserv.tenant.service.TenantService;
import com.cognizant.opserv.tenant.util.DataSourceHolder;
import com.cognizant.opserv.tenant.vo.Tenant;
import com.cognizant.peg.core.logger.LoggerConstants;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;

public class TenenatHandlerInceptor extends HandlerInterceptorAdapter {

	/** The tenant service. */
	@Autowired
	private TenantService tenantService;

	/** The data source holder. */
	@Autowired
	private DataSourceHolder dataSourceHolder;

	/** The default tenant. */
	@Value("${multitenancy.default.tenant}")
	private final String defaultTenant = null;

	/** The is multitenancy. */
	@Value("${multiTenancy.enabled.flag}")
	private final String isMultitenancy = null;
	
	/** logger constant. */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(HandlerInterceptorAdapter.class.getName());

	/**
	 * Pre handle.
	 *
	 * @param request the request
	 * @param response the response
	 * @param handler the handler
	 * @return true, if successful
	 * @throws IOException the IO exception
	 * @Method preHandle - This method prehandles the http request and response
	 */
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws IOException {


		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;

		LOGGER.debug("entering into doFilter method of TenantInterceptor for context path "
				+ httpServletRequest.getContextPath()
				+ " with URL "
				+ httpServletRequest.getRequestURL());

		String urlTenantIDStr = httpServletRequest.getParameter("client");
		LOGGER.debug("checking for tenant code in the URL");
		if (urlTenantIDStr == null || urlTenantIDStr.trim() == "") {
			LOGGER.debug("tenant code is not present in the requrst URL hence checking in the request header");
			urlTenantIDStr = httpServletRequest.getHeader("X-TENANT-CODE");
			if (urlTenantIDStr == null || urlTenantIDStr.trim() == "") {
				LOGGER.debug("tenant code is not present in the request header also hence checking for isMultitenancy flag");
				if ("true".equalsIgnoreCase(isMultitenancy)) {
					LOGGER.debug("multitenancy is enabled");
					throw new RuntimeException("multitenancy is enabled but tenant code is not passed hence throwing exception");
				} else {
					LOGGER.debug("multitenancy is disabled hence checking the tenant code specified in the multitenancy.default.tenant property");
					if (defaultTenant == null || defaultTenant.trim() == "") {
						throw new RuntimeException("multitenancy is disabled but tenant code is not specified in multitenancy.default.tenant property hence throwing exception.");
					}
					urlTenantIDStr = defaultTenant;
				}
			}
		}
		LOGGER.debug("finally printing tenant code and converting it into lowercase------>"+ urlTenantIDStr);
		urlTenantIDStr = urlTenantIDStr.toLowerCase();
		LOGGER.debug("lowercase converted tenant code------>" + urlTenantIDStr);

		LOGGER.debug("setting application context and tenant code in MDC");
		MDC.put(LoggerConstants.APP_CODE_PARAM, httpServletRequest.getContextPath().replace("/", ""));
		MDC.put(LoggerConstants.TENANT_CODE_PARAM, urlTenantIDStr);

		try {
			synchronized (this) {

				Tenant tenant = TenantHolder.getTenant(urlTenantIDStr);
				if (tenant == null) {
					Tenant authenticatedTenant = tenantService.authenticateTenant(urlTenantIDStr);
					if (authenticatedTenant == null) {
						LOGGER.debug("tenant does not exist in database hence throwing exception");
						throw new RuntimeException("tenant does not exist in database.");
					} else {
						LOGGER.info("call to load new tenant data");
						this.populateTenant(authenticatedTenant);
					}
					// TenantHolder.addTenant(urlTenantIDStr,
					// authenticatedTenant);
				}
			}
			TenantContext.setTenantKey(urlTenantIDStr);

//			chain.doFilter(httpServletRequest, httpServletResponse);
		} catch (Exception e) {
			LOGGER.error("unable to process tenant info", e);
			throw new RuntimeException("unable to process tenant info",e);
		} finally {
//			TenantContext.clearTenantKey();
//			DatabaseContext.clearDatabase();
			MDC.remove(LoggerConstants.TENANT_CODE_PARAM);
			MDC.remove(LoggerConstants.APP_CODE_PARAM);
		}
		LOGGER.debug("leaving from doFilter method of TenantInterceptor for context path "
				+ httpServletRequest.getContextPath()
				+ " with URL "
				+ httpServletRequest.getRequestURL());
		return true;
	
	}
	/**
	 * Populate tenant.
	 *
	 * @param tenant the tenant
	 * @throws Exception the exception
	 */
	private void populateTenant(Tenant tenant) throws Exception {
		dataSourceHolder.dataSourceSetter(tenant);
		// tenantService.retrieveTenantsInfo(tenant);
	}
	

}
