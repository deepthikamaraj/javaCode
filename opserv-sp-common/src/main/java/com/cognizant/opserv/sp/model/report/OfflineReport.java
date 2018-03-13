package com.cognizant.opserv.sp.model.report;

import java.io.Serializable;
import java.util.List;
/**
 * ****************************************************************************.
 *
 * @class OfflineReport
 * @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * @since 11/05/2016
 * ***************************************************************************
 */
public class OfflineReport extends BaseReport  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6019502712625337497L;
	
	
	private List<ReportDocument> docs;

	/**
	 * @return the docs
	 */
	public List<ReportDocument> getDocs() {
		return docs;
	}

	/**
	 * @param docs
	 *            the docs to set
	 */
	public void setDocs(List<ReportDocument> docs) {
		this.docs = docs;
	}

}
