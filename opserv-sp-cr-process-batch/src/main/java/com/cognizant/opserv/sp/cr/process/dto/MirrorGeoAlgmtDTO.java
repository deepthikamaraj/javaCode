package com.cognizant.opserv.sp.cr.process.dto;

import java.io.Serializable;

import com.cognizant.opserv.sp.model.GeographyAlignment;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.model.cr.ChangeRequest;

public class MirrorGeoAlgmtDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7174246814773220573L;

	/**
	 * The CR created for this mirror
	 */
	private ChangeRequest childCR;

	/**
	 * The source SP of the mirror
	 */
	private SalesPosition sourceSP;

	/**
	 * The target SP of the mirror
	 */
	private SalesPosition targetSP;

	/**
	 * List of customers alignment for the mirror
	 */
	private GeographyAlignment sourceMirrorGeoAlgmt;
	
	
	private GeographyAlignment targetMirrorGeoAlgmt;

	public ChangeRequest getChildCR() {
		return childCR;
	}

	public void setChildCR(ChangeRequest childCR) {
		this.childCR = childCR;
	}

	public SalesPosition getSourceSP() {
		return sourceSP;
	}

	public void setSourceSP(SalesPosition sourceSP) {
		this.sourceSP = sourceSP;
	}

	public SalesPosition getTargetSP() {
		return targetSP;
	}

	public void setTargetSP(SalesPosition targetSP) {
		this.targetSP = targetSP;
	}

	public GeographyAlignment getSourceMirrorGeoAlgmt() {
		return sourceMirrorGeoAlgmt;
	}

	public void setSourceMirrorGeoAlgmt(GeographyAlignment sourceMirrorGeoAlgmt) {
		this.sourceMirrorGeoAlgmt = sourceMirrorGeoAlgmt;
	}

	public GeographyAlignment getTargetMirrorGeoAlgmt() {
		return targetMirrorGeoAlgmt;
	}

	public void setTargetMirrorGeoAlgmt(GeographyAlignment targetMirrorGeoAlgmt) {
		this.targetMirrorGeoAlgmt = targetMirrorGeoAlgmt;
	}



}
