package com.cognizant.opserv.sp.json;

import java.io.Serializable;
import java.util.List;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;
import org.codehaus.jackson.annotate.JsonProperty;

import com.cognizant.opserv.sp.model.BusinessReason;
import com.cognizant.opserv.sp.model.CustomerAlignment;
import com.cognizant.opserv.sp.model.CustomerProductAlignment;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.model.cr.ChangeRequest;
// TODO: Auto-generated Javadoc
/**
 * ****************************************************************************.
 *
 * @class CustomerBlobDetails
 * @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * @since 11/05/2016
 * ***************************************************************************
 */
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class CustomerBlobDetails implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1867882069949814187L;

	
	/** The src customer alignment. */
	@JsonProperty("srcCustomerAlignment")
	private CustomerAlignment srcCustomerAlignment;
	
	/** The tar customer alignment. */
	@JsonProperty("targetCustomerAlignment")
	private CustomerAlignment tarCustomerAlignment;
	
	/** The comments. */
	@JsonProperty("comments")
	private String comments ;
	
	/** The user details. */
	@JsonProperty("userDetails")
	private UserDetails userDetails;
	
	/** The buss reason. */
	@JsonProperty("bussReason")
	private BusinessReason bussReason;
	
	@JsonProperty("oldCustomerProductAlignments")
	private List<CustomerProductAlignment> oldCustomerProductAlignments;
	
	@JsonProperty("newCustomerProductAlignments")
	private List<CustomerProductAlignment> newCustomerProductAlignments;
	
	private ChangeRequest changeRequest;
	
	/**
	 * Gets the buss reason.
	 *
	 * @return the bussReason
	 */
	public BusinessReason getBussReason() {
		return bussReason;
	}

	/**
	 * Sets the buss reason.
	 *
	 * @param bussReason the bussReason to set
	 */
	public void setBussReason(BusinessReason bussReason) {
		this.bussReason = bussReason;
	}

	/**
	 * Gets the src customer alignment.
	 *
	 * @return the src customer alignment
	 */
	public CustomerAlignment getSrcCustomerAlignment() {
		return srcCustomerAlignment;
	}

	/**
	 * Sets the src customer alignment.
	 *
	 * @param srcCustomerAlignment the new src customer alignment
	 */
	public void setSrcCustomerAlignment(CustomerAlignment srcCustomerAlignment) {
		this.srcCustomerAlignment = srcCustomerAlignment;
	}

	/**
	 * Gets the tar customer alignment.
	 *
	 * @return the tar customer alignment
	 */
	public CustomerAlignment getTarCustomerAlignment() {
		return tarCustomerAlignment;
	}

	/**
	 * Sets the tar customer alignment.
	 *
	 * @param tarCustomerAlignment the new tar customer alignment
	 */
	public void setTarCustomerAlignment(CustomerAlignment tarCustomerAlignment) {
		this.tarCustomerAlignment = tarCustomerAlignment;
	}

	/**
	 * Gets the comments.
	 *
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * Sets the comments.
	 *
	 * @param comments the new comments
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}

	/**
	 * Gets the user details.
	 *
	 * @return the user details
	 */
	public UserDetails getUserDetails() {
		return userDetails;
	}

	/**
	 * Sets the user details.
	 *
	 * @param userDetails the new user details
	 */
	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}

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

	public ChangeRequest getChangeRequest() {
		return changeRequest;
	}

	public void setChangeRequest(ChangeRequest changeRequest) {
		this.changeRequest = changeRequest;
	}

	
	
	
}
