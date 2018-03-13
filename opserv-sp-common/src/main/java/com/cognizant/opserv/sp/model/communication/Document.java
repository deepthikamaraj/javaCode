package com.cognizant.opserv.sp.model.communication;

import java.util.Date;

import com.cognizant.opserv.sp.model.BaseModel;
/**
 * ****************************************************************************.
 * Model class for Communication
 * @class Communication
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 23/06/2016
 * ***************************************************************************
 */
public class Document  extends BaseModel{
	
	private  String docLocation;
	private Date publishedDate;	
	private String docName;
	
	/**
	 * @return the docLocation
	 */
	public String getDocLocation() {
		return docLocation;
	}
	
	/**
	 * @param docLocation
	 *            the docLocation to set
	 */
	public void setDocLocation(String docLocation) {
		this.docLocation = docLocation;
	}
	
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
	
	/**
	 * @return the docName
	 */
	public String getDocName() {
		return docName;
	}
	
	/**
	 * @param docName
	 *            the docName to set
	 */
	public void setDocName(String docName) {
		this.docName = docName;
	}
	
	

}
