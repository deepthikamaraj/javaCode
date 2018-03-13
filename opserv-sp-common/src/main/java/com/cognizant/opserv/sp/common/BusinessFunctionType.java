package com.cognizant.opserv.sp.common;

public enum BusinessFunctionType {

	ONLINE_REPORT("OnlineReports"), ATTRIBUTE_MANAGEMENT("AttributeManagement");

	private BusinessFunctionType(String title) {
		this.title = title;
	}

	private String title;

	public String getTitle() {
		return title;
	}

}
