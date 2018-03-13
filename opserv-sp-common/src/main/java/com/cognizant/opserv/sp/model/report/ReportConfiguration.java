package com.cognizant.opserv.sp.model.report;

import java.io.Serializable;

import com.cognizant.opserv.sp.common.ConfigStatusType;
import com.cognizant.opserv.sp.model.BaseModel;
/**
 * ****************************************************************************.
 *
 * @class ReportConfiguration
 * @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * @since 11/05/2016
 * ***************************************************************************
 */
public class ReportConfiguration extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4097190212406385131L;

	private boolean isAckReq;

	private ConfigStatusType confStatus;

	/**
	 * @return the isAckReq
	 */
	public boolean isAckReq() {
		return isAckReq;
	}

	/**
	 * @param isAckReq
	 *            the isAckReq to set
	 */
	public void setAckReq(boolean isAckReq) {
		this.isAckReq = isAckReq;
	}

	/**
	 * @return the confStatus
	 */
	public ConfigStatusType getConfStatus() {
		return confStatus;
	}

	/**
	 * @param confStatus
	 *            the confStatus to set
	 */
	public void setConfStatus(ConfigStatusType confStatus) {
		this.confStatus = confStatus;
	}

}
