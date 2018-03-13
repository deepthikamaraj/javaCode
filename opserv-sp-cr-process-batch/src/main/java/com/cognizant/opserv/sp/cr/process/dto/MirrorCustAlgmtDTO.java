package com.cognizant.opserv.sp.cr.process.dto;

import java.io.Serializable;
import java.util.List;

import com.cognizant.opserv.sp.model.CustomerAlignment;
import com.cognizant.opserv.sp.model.CustomerProductAlignment;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.model.cr.ChangeRequest;

public class MirrorCustAlgmtDTO implements Serializable {

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
	private List<CustomerAlignment> sourceMirrorCustAlgmts;
	
	/**
	 * List of customers alignment for the mirror
	 */
	private List<CustomerAlignment> targetMirrorCustAlgmts;	
	
	/**
	 * Customer Product Alignments
	 */
	private List<CustomerProductAlignment> sourcemirrorCustProdAlgmts;

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

	public List<CustomerAlignment> getSourceMirrorCustAlgmts() {
		return sourceMirrorCustAlgmts;
	}

	public void setSourceMirrorCustAlgmts(
			List<CustomerAlignment> sourceMirrorCustAlgmts) {
		this.sourceMirrorCustAlgmts = sourceMirrorCustAlgmts;
	}
	
	public List<CustomerAlignment> getTargetMirrorCustAlgmts() {
		return targetMirrorCustAlgmts;
	}

	public void setTargetMirrorCustAlgmts(
			List<CustomerAlignment> targetMirrorCustAlgmts) {
		this.targetMirrorCustAlgmts = targetMirrorCustAlgmts;
	}

	public List<CustomerProductAlignment> getSourcemirrorCustProdAlgmts() {
		return sourcemirrorCustProdAlgmts;
	}

	public void setSourcemirrorCustProdAlgmts(
			List<CustomerProductAlignment> sourcemirrorCustProdAlgmts) {
		this.sourcemirrorCustProdAlgmts = sourcemirrorCustProdAlgmts;
	}	
	
	
}
