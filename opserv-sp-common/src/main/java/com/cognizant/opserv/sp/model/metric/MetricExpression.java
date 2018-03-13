package com.cognizant.opserv.sp.model.metric;

import java.io.Serializable;

import com.cognizant.opserv.sp.common.MetricExpressionType;
import com.cognizant.opserv.sp.model.BaseModel;
/**
 * ****************************************************************************.
 *
 * @class MetricExpression
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 11/05/2016
 * ***************************************************************************
 */
public class MetricExpression extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6149980383111906904L;

	private String expression;

	private MetricExpressionType type;

	/**
	 * @return the expression
	 */
	public String getExpression() {
		return expression;
	}

	/**
	 * @param expression
	 *            the expression to set
	 */
	public void setExpression(String expression) {
		this.expression = expression;
	}

	/**
	 * @return the type
	 */
	public MetricExpressionType getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(MetricExpressionType type) {
		this.type = type;
	}

}
