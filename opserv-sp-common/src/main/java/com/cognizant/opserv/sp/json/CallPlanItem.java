package com.cognizant.opserv.sp.json;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The Class CallPlanItem.
 *
 * @author 426111
 */
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class CallPlanItem implements Serializable {


	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2329939404996371615L;

	/** The prod id. */
	@JsonProperty("pId")
	private Long prodId;
	
	/** The planned calls. */
	@JsonProperty("pCal")
	private int plannedCalls;

	/**
	 * Gets the prod id.
	 *
	 * @return the prod id
	 */
	public Long getProdId() {
		return prodId;
	}

	/**
	 * Sets the prod id.
	 *
	 * @param prodId the new prod id
	 */
	public void setProdId(Long prodId) {
		this.prodId = prodId;
	}

	/**
	 * Gets the planned calls.
	 *
	 * @return the planned calls
	 */
	public int getPlannedCalls() {
		return plannedCalls;
	}

	/**
	 * Sets the planned calls.
	 *
	 * @param plannedCalls the new planned calls
	 */
	public void setPlannedCalls(int plannedCalls) {
		this.plannedCalls = plannedCalls;
	}
	
	

}
