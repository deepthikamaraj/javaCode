package com.cognizant.opserv.sp.json;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;
import org.codehaus.jackson.annotate.JsonProperty;
/**
 * ****************************************************************************.
 *
 * @class SPTargetedCallPlan
 * @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * @since 11/05/2016
 * ***************************************************************************
 */
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class SPTargetedCallPlan implements Serializable, ISPTargetedDocument {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -686458135294261795L;

	/** The salespos id. */
	@JsonProperty("spId")
	private Long salesposId;
	
	/** The customer call plans. */
	@JsonProperty("cCp")
	private List<CustomerCallPlan> customerCallPlans;
	
	/** The doc created date. */
	@JsonProperty("dcDt")
	private Date docCreatedDate;
	
	/** The doc updated date. */
	@JsonProperty("duDt")
	private Date docUpdatedDate;

	/**
	 * Gets the salespos id.
	 *
	 * @return the salespos id
	 */
	public Long getSalesposId() {
		return salesposId;
	}

	/**
	 * Sets the salespos id.
	 *
	 * @param salesposId the new salespos id
	 */
	public void setSalesposId(Long salesposId) {
		this.salesposId = salesposId;
	}

	/**
	 * Gets the customer call plans.
	 *
	 * @return the customer call plans
	 */
	public List<CustomerCallPlan> getCustomerCallPlans() {
		return customerCallPlans;
	}

	/**
	 * Sets the customer call plans.
	 *
	 * @param customerCallPlans the new customer call plans
	 */
	public void setCustomerCallPlans(List<CustomerCallPlan> customerCallPlans) {
		this.customerCallPlans = customerCallPlans;
	}

	/**
	 * Gets the doc created date.
	 *
	 * @return the doc created date
	 */
	public Date getDocCreatedDate() {
		return docCreatedDate;
	}

	/**
	 * Sets the doc created date.
	 *
	 * @param docCreatedDate the new doc created date
	 */
	public void setDocCreatedDate(Date docCreatedDate) {
		this.docCreatedDate = docCreatedDate;
	}

	/**
	 * Gets the doc updated date.
	 *
	 * @return the doc updated date
	 */
	public Date getDocUpdatedDate() {
		return docUpdatedDate;
	}

	/**
	 * Sets the doc updated date.
	 *
	 * @param docUpdatedDate the new doc updated date
	 */
	public void setDocUpdatedDate(Date docUpdatedDate) {
		this.docUpdatedDate = docUpdatedDate;
	}

}
