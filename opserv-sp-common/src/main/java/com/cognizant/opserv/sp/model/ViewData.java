package com.cognizant.opserv.sp.model;

import java.io.Serializable;
import java.util.List;
/**
 * ****************************************************************************.
 *
 * @class ViewData
 * @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * @since 11/05/2016
 * ***************************************************************************
 */
public class ViewData implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6611725531307447675L;

	private ViewHeader headerData;

	private List<ViewRowData> dataList;

	/**
	 * @return the headerData
	 */
	public ViewHeader getHeaderData() {
		return headerData;
	}

	/**
	 * @param headerData
	 *            the headerData to set
	 */
	public void setHeaderData(ViewHeader headerData) {
		this.headerData = headerData;
	}

	/**
	 * @return the dataList
	 */
	public List<ViewRowData> getDataList() {
		return dataList;
	}

	/**
	 * @param dataList
	 *            the dataList to set
	 */
	public void setDataList(List<ViewRowData> dataList) {
		this.dataList = dataList;
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ViewData [headerData=" + headerData + ", dataList=" + dataList
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}


}
