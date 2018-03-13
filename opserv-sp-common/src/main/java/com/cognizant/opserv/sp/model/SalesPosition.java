package com.cognizant.opserv.sp.model;

import java.io.Serializable;
import java.util.List;

import com.cognizant.opserv.sp.common.MirrorSalesPositionType;
import com.cognizant.opserv.sp.common.SalesPositionType;
import com.cognizant.opserv.sp.model.attrb.BaseExtdModel;

/**
 * ****************************************************************************.
 *
 * @class SalesPosition
 * @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * @since 11/05/2016
 * ***************************************************************************
 */
public class SalesPosition extends BaseExtdModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -361414839169323637L;

	private SalesOrgHierarchy hierarchy;

	private boolean isJobShared;

	private boolean isRoot;

	private Alignment alignmment;

	private List<SalesPosition> childSalesPositions;

	private SalesPosition parentSalesPosition;

	private SalesPositionType salesPositionType;

	private MirrorSalesPositionType mirrorSalesPositionType;

	private boolean isPrimaryMirror;

	/**
	 * @return the isJobShared
	 */
	public boolean isJobShared() {
		return isJobShared;
	}

	/**
	 * @param isJobShared
	 *            the isJobShared to set
	 */
	public void setJobShared(boolean isJobShared) {
		this.isJobShared = isJobShared;
	}

	/**
	 * @return the childSalesPositions
	 */
	public List<SalesPosition> getChildSalesPositions() {
		return childSalesPositions;
	}

	/**
	 * @param childSalesPositions
	 *            the childSalesPositions to set
	 */
	public void setChildSalesPositions(List<SalesPosition> childSalesPositions) {
		this.childSalesPositions = childSalesPositions;
	}

	/**
	 * @return the isRoot
	 */
	public boolean isRoot() {
		return isRoot;
	}

	/**
	 * @param isRoot
	 *            the isRoot to set
	 */
	public void setRoot(boolean isRoot) {
		this.isRoot = isRoot;
	}

	/**
	 * @return the parentSalesPosition
	 */
	public SalesPosition getParentSalesPosition() {
		return parentSalesPosition;
	}

	/**
	 * @param parentSalesPosition
	 *            the parentSalesPosition to set
	 */
	public void setParentSalesPosition(SalesPosition parentSalesPosition) {
		this.parentSalesPosition = parentSalesPosition;
	}

	/**
	 * @return the alignmment
	 */
	public Alignment getAlignmment() {
		return alignmment;
	}

	/**
	 * @param alignmment
	 *            the alignmment to set
	 */
	public void setAlignmment(Alignment alignmment) {
		this.alignmment = alignmment;
	}

	/**
	 * @return salesPositionType
	 */
	public SalesPositionType getSalesPositionType() {
		return salesPositionType;
	}

	/**
	 * @param salesPositionType
	 */
	public void setSalesPositionType(SalesPositionType salesPositionType) {
		this.salesPositionType = salesPositionType;
	}

	/**
	 * @return mirrorSalesPositionType
	 */
	public MirrorSalesPositionType getMirrorSalesPositionType() {
		return mirrorSalesPositionType;
	}

	/**
	 * @param mirrorSalesPositionType
	 */
	public void setMirrorSalesPositionType(
			MirrorSalesPositionType mirrorSalesPositionType) {
		this.mirrorSalesPositionType = mirrorSalesPositionType;
	}

	/**
	 * @return the hierarchy
	 */
	public SalesOrgHierarchy getHierarchy() {
		return hierarchy;
	}

	/**
	 * @param hierarchy
	 *            the hierarchy to set
	 */
	public void setHierarchy(SalesOrgHierarchy hierarchy) {
		this.hierarchy = hierarchy;
	}

	public boolean isPrimaryMirror() {
		return isPrimaryMirror;
	}

	public void setPrimaryMirror(boolean isPrimaryMirror) {
		this.isPrimaryMirror = isPrimaryMirror;
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SalesPosition [hierarchy=" + hierarchy + ", isJobShared="
				+ isJobShared + ", isRoot=" + isRoot + ", alignmment="
				+ alignmment + ", childSalesPositions=" + childSalesPositions
				+ ", parentSalesPosition=" + parentSalesPosition
				+ ", salesPositionType=" + salesPositionType
				+ ", mirrorSalesPositionType=" + mirrorSalesPositionType + "]";
	}

}
