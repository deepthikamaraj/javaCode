package com.cognizant.opserv.sp.model.cr;

import java.io.Serializable;

import com.cognizant.opserv.sp.model.GeographyAlignment;
/**
 * ****************************************************************************.
 *
 * @class ZipAlignmentChangeRequestDetails
 * @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * @since 11/05/2016
 * ***************************************************************************
 */
public class ZipAlignmentChangeRequestDetails 
											extends ChangeRequestLineItem
											implements Serializable 
{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1593705252123945865L;

	private GeographyAlignment oldGeographyAlignmentObject;
	
	private GeographyAlignment newGeographyAlignmentObject;
	
	private ChangeRequestBusinessReason changeRequestBusinessReason;

	/**
	 * @return the oldGeographyAlignmentObject
	 */
	public GeographyAlignment getOldGeographyAlignmentObject() {
		return oldGeographyAlignmentObject;
	}

	/**
	 * @param oldGeographyAlignmentObject the oldGeographyAlignmentObject to set
	 */
	public void setOldGeographyAlignmentObject(
			GeographyAlignment oldGeographyAlignmentObject) {
		this.oldGeographyAlignmentObject = oldGeographyAlignmentObject;
	}

	/**
	 * @return the newGeographyAlignmentObject
	 */
	public GeographyAlignment getNewGeographyAlignmentObject() {
		return newGeographyAlignmentObject;
	}

	/**
	 * @param newGeographyAlignmentObject the newGeographyAlignmentObject to set
	 */
	public void setNewGeographyAlignmentObject(
			GeographyAlignment newGeographyAlignmentObject) {
		this.newGeographyAlignmentObject = newGeographyAlignmentObject;
	}

	/**
	 * @return the changeRequestBusinessReason
	 */
	public ChangeRequestBusinessReason getChangeRequestBusinessReason() {
		return changeRequestBusinessReason;
	}

	/**
	 * @param changeRequestBusinessReason the changeRequestBusinessReason to set
	 */
	public void setChangeRequestBusinessReason(ChangeRequestBusinessReason changeRequestBusinessReason) {
		this.changeRequestBusinessReason = changeRequestBusinessReason;
	}

}

