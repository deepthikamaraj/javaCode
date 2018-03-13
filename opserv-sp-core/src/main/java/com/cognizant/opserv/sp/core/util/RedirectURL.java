/* COPYRIGHT (C) 2014 Cognizant, all rights reserved.*/
package com.cognizant.opserv.sp.core.util;

import com.cognizant.opserv.common.CommonPropertiesUtil;
import com.cognizant.opserv.tenant.ctx.TenantContext;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;


// TODO: Auto-generated Javadoc
/**
 * ****************************************************************************.
 *
 * @class RedirectURL
 * @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * @since 27/11/2014
 * ***************************************************************************
 */
public class RedirectURL {
	
	/** The Constant LOGGER. */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(RedirectURL.class);
	
	/**
	 * Instantiates a new redirect url.
	 */
	private RedirectURL(){
		
	}


	/**
	 * Gets the redirect url.
	 *
	 * @param client the client
	 * @param url the url
	 * @return the redirect url
	 */
	public static String getRedirectURL(String client, String url) {
		if(client == null){
			return "redirect:/"+url;
		} else {
			return "redirect:/"+client+"/"+url;
		}
	}
	
	/**
	 * Gets the redirect url.
	 *
	 * @param url the url
	 * @return the redirect url
	 */
	public static String getRedirectURL(String url) {
		
		String client = TenantContext.getTenantKey();
		boolean isMultitenant = CommonPropertiesUtil.isMultiTenancyEnabled();
		LOGGER.debug("Client - "+client+" isMultiTenant - "+isMultitenant);		 
		String redirectURL = "";
		if(client != null && client.trim().length() >0 && isMultitenant) {
			redirectURL = "redirect:/"+client+"/"+url;
		} else {
			redirectURL = "redirect:/"+url;
		}
		LOGGER.info("redirect url  = "+redirectURL);
		return redirectURL;
		
	}	
	
}
