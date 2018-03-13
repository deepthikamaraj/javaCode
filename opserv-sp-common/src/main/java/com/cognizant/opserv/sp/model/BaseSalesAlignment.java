package com.cognizant.opserv.sp.model;

import com.cognizant.opserv.sp.common.ChangeRequestStatusType;
import com.cognizant.opserv.sp.model.attrb.BaseExtdModel;
/**
 * ****************************************************************************.
 *
 * @class BaseSalesAlignment
 * @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * @since 11/05/2016
 * ***************************************************************************
 */
public class BaseSalesAlignment extends BaseExtdModel {

	private SalesPosition salesPosition;

	/** status is the status. */
	private ChangeRequestStatusType status;

	/**
	 * @return the salesPosition
	 */
	public SalesPosition getSalesPosition() {
		return salesPosition;
	}

	/**
	 * @param salesPosition
	 *            the salesPosition to set
	 */
	public void setSalesPosition(SalesPosition salesPosition) {
		this.salesPosition = salesPosition;
	}

	/**
	 * @return the status
	 */
	public ChangeRequestStatusType getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(ChangeRequestStatusType status) {
		this.status = status;
	}

}
