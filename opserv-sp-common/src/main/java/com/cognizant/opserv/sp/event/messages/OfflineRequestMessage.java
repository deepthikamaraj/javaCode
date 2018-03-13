package com.cognizant.opserv.sp.event.messages;

import java.io.Serializable;
import java.util.List;

import com.cognizant.opserv.sp.model.BusinessReason;
import com.cognizant.opserv.sp.model.CustomerProductAlignment;
import com.cognizant.opserv.sp.model.auth.UserDetails;

// TODO: Auto-generated Javadoc
/**
 * The Class TempCRBlobMessage.
 */
public class OfflineRequestMessage implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5395431178072715776L;

	/** tenantCode. */
	private String tenantCode;
	
	/** tenantId. */
	private Short tenantId;
	
	/** OffLine Req Id. */
	private Long offlineReqId;
	
	/** chngReqID. */
	private Long chngReqID;

	/** userDetails. */
	private UserDetails userDetails;
	
	/** triggerId. */
	
	private Integer triggerId;
	
	/** The src sp name list. */
	private List<String> srcSpNameList;
	

	/** The tar sp name list. */
	private List<String> tarSpNameList;
	
	/** The cust name list. */
	private String custName;
	
	
	/** The emp email id list. */
	private List<String> empEmailIdList;
	
	/** The emp email id list. */
	private List<Integer> staffIdList;
	
	/**
	 * businessReason
	 */
	private BusinessReason businessReason;
	
	private List<CustomerProductAlignment> oldCustomerProductAlignments;
	private List<CustomerProductAlignment> newCustomerProductAlignments;


	/**
	 * @return the oldCustomerProductAlignments
	 */
	public List<CustomerProductAlignment> getOldCustomerProductAlignments() {
		return oldCustomerProductAlignments;
	}

	/**
	 * @param oldCustomerProductAlignments the oldCustomerProductAlignments to set
	 */
	public void setOldCustomerProductAlignments(
			List<CustomerProductAlignment> oldCustomerProductAlignments) {
		this.oldCustomerProductAlignments = oldCustomerProductAlignments;
	}

	/**
	 * @return the newCustomerProductAlignments
	 */
	public List<CustomerProductAlignment> getNewCustomerProductAlignments() {
		return newCustomerProductAlignments;
	}

	/**
	 * @param newCustomerProductAlignments the newCustomerProductAlignments to set
	 */
	public void setNewCustomerProductAlignments(
			List<CustomerProductAlignment> newCustomerProductAlignments) {
		this.newCustomerProductAlignments = newCustomerProductAlignments;
	}

	/**
	 * Gets the tenant code.
	 *
	 * @return tenantCode
	 */
	public String getTenantCode() {
		return tenantCode;
	}

	/**
	 * Sets the tenant code.
	 *
	 * @param tenantCode the new tenant code
	 */
	public void setTenantCode(String tenantCode) {
		this.tenantCode = tenantCode;
	}

	/**
	 * Gets the tenant id.
	 *
	 * @return tenantId
	 */
	public Short getTenantId() {
		return tenantId;
	}

	/**
	 * Sets the tenant id.
	 *
	 * @param tenantId the new tenant id
	 */
	public void setTenantId(Short tenantId) {
		this.tenantId = tenantId;
	}

	/**
	 * Get Off Req id
	 * 
	 * @return
	 */
	public Long getOfflineReqId() {
		return offlineReqId;
	}

	/**
	 * Set Offline req id
	 * @param offlineReqId
	 */
	public void setOfflineReqId(Long offlineReqId) {
		this.offlineReqId = offlineReqId;
	}

	/**
	 * Gets the chng req id.
	 *
	 * @return chngReqID
	 */
	public Long getChngReqID() {
		return chngReqID;
	}

	/**
	 * Sets the chng req id.
	 *
	 * @param chngReqID the new chng req id
	 */
	public void setChngReqID(Long chngReqID) {
		this.chngReqID = chngReqID;
	}

	/**
	 * Gets the user details.
	 *
	 * @return the userDetails
	 */
	public UserDetails getUserDetails() {
		return userDetails;
	}

	/**
	 * Sets the user details.
	 *
	 * @param userDetails the userDetails to set
	 */
	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}

	/**
	 * Gets the trigger id.
	 *
	 * @return the trigger id
	 */
	public Integer getTriggerId() {
		return triggerId;
	}

	/**
	 * Sets the trigger id.
	 *
	 * @param triggerId the new trigger id
	 */
	public void setTriggerId(Integer triggerId) {
		this.triggerId = triggerId;
	}

	/**
	 * Gets the src sp name list.
	 *
	 * @return the srcSpNameList
	 */
	public List<String> getSrcSpNameList() {
		return srcSpNameList;
	}

	/**
	 * Sets the src sp name list.
	 *
	 * @param srcSpNameList the srcSpNameList to set
	 */
	public void setSrcSpNameList(List<String> srcSpNameList) {
		this.srcSpNameList = srcSpNameList;
	}


	/**
	 * Gets the tar sp name list.
	 *
	 * @return the tarSpNameList
	 */
	public List<String> getTarSpNameList() {
		return tarSpNameList;
	}

	/**
	 * Sets the tar sp name list.
	 *
	 * @param tarSpNameList the tarSpNameList to set
	 */
	public void setTarSpNameList(List<String> tarSpNameList) {
		this.tarSpNameList = tarSpNameList;
	}
	
	

	/**
	 * @return the empEmailIdList
	 */
	public List<String> getEmpEmailIdList() {
		return empEmailIdList;
	}

	/**
	 * @param empEmailIdList the empEmailIdList to set
	 */
	public void setEmpEmailIdList(List<String> empEmailIdList) {
		this.empEmailIdList = empEmailIdList;
	}

	public BusinessReason getBusinessReason() {
		return businessReason;
	}

	public void setBusinessReason(BusinessReason businessReason) {
		this.businessReason = businessReason;
	}

	public List<Integer> getStaffIdList() {
		return staffIdList;
	}

	public void setStaffIdList(List<Integer> staffIdList) {
		this.staffIdList = staffIdList;
	}

	/**
	 * @return the custName
	 */
	public String getCustName() {
		return custName;
	}

	/**
	 * @param custName the custName to set
	 */
	public void setCustName(String custName) {
		this.custName = custName;
	}

	private String postalCode;

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	
	@Override
	public String toString() {
		return "TempCRBlobMessage [tenantCode=" + tenantCode + ", tenantId="
				+ tenantId + ", offlineReqId=" + offlineReqId + ", chngReqID="
				+ chngReqID + ", userDetails=" + userDetails + ", triggerId="
				+ triggerId + ", srcSpNameList=" + srcSpNameList
				+ ", tarSpNameList=" + tarSpNameList + ", custName="
				+ custName + ", empEmailIdList=" + empEmailIdList
				+ ", businessReason=" + businessReason + "]";
	}

	
}
