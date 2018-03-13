package com.cognizant.opserv.sp.json;

import java.io.Serializable;
import java.util.List;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;
import org.codehaus.jackson.annotate.JsonProperty;

// TODO: Auto-generated Javadoc
/**
 * The Class CustomerCallPlan.
 *
 * @author 426111
 */
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class CustomerCallPlan implements Serializable {


	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4165137025084295549L;

	/** The call plan id. */
	@JsonProperty("cpId")
	private Long callPlanId;
	
	/** The salespos id. */
	@JsonProperty("spId")
	private Long salesposId;

	/** The customer id. */
	@JsonProperty("cId")
	private Long customerId;
	
	/** The planned calls. */
	@JsonProperty("pCal")
	private int plannedCalls;
	
	/** The call plan type. */
	@JsonProperty("cpT")
	private String callPlanType;
	
	/** The call plan items. */
	@JsonProperty("cpI")
	private List<CallPlanItem> callPlanItems;
	
	// Call Plan config
	/** The is affiliation based. */
	@JsonProperty("cCp")
	private boolean isAffiliationBased;
	
	/** The is direction based. */
	@JsonProperty("diB")
	private boolean isDirectionBased;
	
	/** The is product split. */
	@JsonProperty("pSpl")
	private boolean isProductSplit;
	
	/** The max products. */
	@JsonProperty("mp")
	private int maxProducts;
	
	/** The cust algmnt id. */
	@JsonProperty("cAId")
	private Long custAlgmntId;
	

	/**
	 * Gets the call plan id.
	 *
	 * @return the call plan id
	 */
	public Long getCallPlanId() {
		return callPlanId;
	}

	/**
	 * Sets the call plan id.
	 *
	 * @param callPlanId the new call plan id
	 */
	public void setCallPlanId(Long callPlanId) {
		this.callPlanId = callPlanId;
	}

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
	 * Gets the customer id.
	 *
	 * @return the customer id
	 */
	public Long getCustomerId() {
		return customerId;
	}

	/**
	 * Sets the customer id.
	 *
	 * @param customerId the new customer id
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
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

	/**
	 * Gets the call plan type.
	 *
	 * @return the call plan type
	 */
	public String getCallPlanType() {
		return callPlanType;
	}

	/**
	 * Sets the call plan type.
	 *
	 * @param callPlanType the new call plan type
	 */
	public void setCallPlanType(String callPlanType) {
		this.callPlanType = callPlanType;
	}

	/**
	 * Gets the call plan items.
	 *
	 * @return the call plan items
	 */
	public List<CallPlanItem> getCallPlanItems() {
		return callPlanItems;
	}

	/**
	 * Sets the call plan items.
	 *
	 * @param callPlanItems the new call plan items
	 */
	public void setCallPlanItems(List<CallPlanItem> callPlanItems) {
		this.callPlanItems = callPlanItems;
	}

	/**
	 * Checks if is affiliation based.
	 *
	 * @return true, if is affiliation based
	 */
	public boolean isAffiliationBased() {
		return isAffiliationBased;
	}

	/**
	 * Sets the affiliation based.
	 *
	 * @param isAffiliationBased the new affiliation based
	 */
	public void setAffiliationBased(boolean isAffiliationBased) {
		this.isAffiliationBased = isAffiliationBased;
	}

	/**
	 * Checks if is direction based.
	 *
	 * @return true, if is direction based
	 */
	public boolean isDirectionBased() {
		return isDirectionBased;
	}

	/**
	 * Sets the direction based.
	 *
	 * @param isDirectionBased the new direction based
	 */
	public void setDirectionBased(boolean isDirectionBased) {
		this.isDirectionBased = isDirectionBased;
	}

	/**
	 * Checks if is product split.
	 *
	 * @return true, if is product split
	 */
	public boolean isProductSplit() {
		return isProductSplit;
	}

	/**
	 * Sets the product split.
	 *
	 * @param isProductSplit the new product split
	 */
	public void setProductSplit(boolean isProductSplit) {
		this.isProductSplit = isProductSplit;
	}

	/**
	 * Gets the max products.
	 *
	 * @return the max products
	 */
	public int getMaxProducts() {
		return maxProducts;
	}

	/**
	 * Sets the max products.
	 *
	 * @param maxProducts the new max products
	 */
	public void setMaxProducts(int maxProducts) {
		this.maxProducts = maxProducts;
	}

	/**
	 * Gets the cust algmnt id.
	 *
	 * @return the cust algmnt id
	 */
	public Long getCustAlgmntId() {
		return custAlgmntId;
	}

	/**
	 * Sets the cust algmnt id.
	 *
	 * @param custAlgmntId the new cust algmnt id
	 */
	public void setCustAlgmntId(Long custAlgmntId) {
		this.custAlgmntId = custAlgmntId;
	}
	
	
	
}
