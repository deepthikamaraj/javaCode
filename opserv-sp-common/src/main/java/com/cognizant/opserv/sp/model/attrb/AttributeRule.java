package com.cognizant.opserv.sp.model.attrb;

import com.cognizant.opserv.sp.common.RuleType;
import com.cognizant.opserv.sp.model.BaseModel;

public class AttributeRule extends BaseModel{
	
	private String pattern;
	
	private float minValue;
	
	private float maxValue;
	
	private RuleType ruleType;

	public RuleType getRuleType() {
		return ruleType;
	}

	public void setRuleType(RuleType ruleType) {
		this.ruleType = ruleType;
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	public float getMinValue() {
		return minValue;
	}

	public void setMinValue(float minValue) {
		this.minValue = minValue;
	}

	public float getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(float maxValue) {
		this.maxValue = maxValue;
	}	
	
}
