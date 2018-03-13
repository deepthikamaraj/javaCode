package com.cognizant.opserv.sp.model.report;

import java.util.Date;

import com.cognizant.opserv.sp.model.BaseModel;
/**
 * ****************************************************************************.
 *
 * @class BaseReport
 * @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * @since 11/05/2016
 * ***************************************************************************
 */
public class BaseReport extends BaseModel {

	private Date publishedDate;

	/**
	 * @return the publishedDate
	 */
	public Date getPublishedDate() {
		return publishedDate;
	}

	/**
	 * @param publishedDate
	 *            the publishedDate to set
	 */
	public void setPublishedDate(Date publishedDate) {
		this.publishedDate = publishedDate;
	}

}
