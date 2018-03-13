package com.cognizant.opserv.sp.model.communication;

import java.util.Date;
import java.util.List;

import com.cognizant.opserv.sp.common.communication.CommunicationStatusType;
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
public class Communication extends BaseModel {
	
	private Date publishedDate;
	private Date draftedDate;
	private String details;
	private CommunicationStatusType status;
	private List<Recipient> recipients;
	private RecipientsSearchParam recipientsSearchParams;
	
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
	 * @return the draftedDate
	 */
	public Date getDraftedDate() {
		return draftedDate;
	}
	
	/**
	 * @param draftedDate
	 *            the draftedDate to set
	 */
	public void setDraftedDate(Date draftedDate) {
		this.draftedDate = draftedDate;
	}
	
	/**
	 * @return the details
	 */
	public String getDetails() {
		return details;
	}
	
	/**
	 * @param details
	 *            the details to set
	 */
	public void setDetails(String details) {
		this.details = details;
	}
	
	/**
	 * @return the status
	 */
	public CommunicationStatusType getStatus() {
		return status;
	}
	
	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(CommunicationStatusType status) {
		this.status = status;
	}
	
	/**
	 * @return the recipients
	 */
	public List<Recipient> getRecipients() {
		return recipients;
	}
	
	/**
	 * @param recipients
	 *            the recipients to set
	 */
	public void setRecipients(List<Recipient> recipients) {
		this.recipients = recipients;
	}

	public RecipientsSearchParam getRecipientsSearchParams() {
		return recipientsSearchParams;
	}

	public void setRecipientsSearchParams(
			RecipientsSearchParam recipientsSearchParams) {
		this.recipientsSearchParams = recipientsSearchParams;
	}

	
}