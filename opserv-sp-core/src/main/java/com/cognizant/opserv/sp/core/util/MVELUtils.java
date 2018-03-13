package com.cognizant.opserv.sp.core.util;

/******************************************************************************
 *  
 * @class MVELUtils provides MVEL Utility Info.
 * @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * @since 10/29/2014
 * COPYRIGHT (C) 2014 Cognizant, all rights reserved.
 *****************************************************************************/

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.mvel2.MVEL;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.integration.impl.MapVariableResolverFactory;

// TODO: Auto-generated Javadoc
/**
 * The Class MVELUtils.
 *  @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * @since 01/11/2014
 */
public class MVELUtils {

	/**
	 * Instantiates a new mVEL utils.
	 */
	private MVELUtils(){
		
	}
	
	/**
	 * Execute expression.
	 *
	 * @param mtrExprD the mtr expr d
	 * @param valueMap the value map
	 * @return the object
	 */
	public static Object executeExpression(String mtrExprD,
			HashMap<String, Object> valueMap) {
		VariableResolverFactory myVarFactory = new MapVariableResolverFactory();
		Serializable s = MVEL.compileExpression(mtrExprD, valueMap);
		return MVEL.executeExpression(s, myVarFactory);
	}
	
	/**
	 * Creates the message.
	 *
	 * @param failureMap the failure map
	 * @return the string
	 */
	public static String createMessage(Map<String, Set<String>> failureMap){
		Set<String> keySet = failureMap.keySet();
		StringBuilder buffer = new StringBuilder(" Rules are below <br/> ");
		for (String key : keySet) {
			Set<String> ruleSet = failureMap.get(key);
			String rules = StringUtils.join(ruleSet.toArray(), ",");
			buffer.append(key+" : "+rules+"<br/>");
		}
		return buffer.toString();
	}
}
