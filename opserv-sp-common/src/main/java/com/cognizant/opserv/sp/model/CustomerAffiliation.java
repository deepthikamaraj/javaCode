package com.cognizant.opserv.sp.model;

import java.io.Serializable;
import java.util.List;

import com.cognizant.opserv.sp.common.AffiliationRelationType;
import com.cognizant.opserv.sp.common.StatusType;

/**
 * ****************************************************************************.
 *
 * @class CustomerAffiliation
 * @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * @since 11/05/2016
 * ***************************************************************************
 */
public class CustomerAffiliation extends BaseModel implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2079512931726851449L;
	
	/** customer is the customer. */
	private Customer customer;
	
	/** affiliatedCustomers is the list of affiliated customer. */
	private List<CustomerAffiliation> affiliatedCustomers;
		
	/** alignment is the Alignment data. */
	private Alignment alignment;
		
	/** salesOrg is the sales organization data. */
	private SalesOrg salesOrg;

	/**status is the status of affiliation */
	private StatusType status;
	
	/** primaryAffFlag is the primary affiliation flag. */
	private boolean isPrimaryAffiliation;
	
	/** relationshipType is the relationship type. */
	//private Integer relationshipType;
	
	/** affRelationType is the affRelationType type. */
	private AffiliationRelationType affRelationType;
	
	/** isRootAff is the root affiliation flag. */
	private boolean isRoot;

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	/**
	 * Gets the affiliatedCustomers.
	 *
	 * @return the list of customer affiliation
	 */
	public List<CustomerAffiliation> getAffiliatedCustomers() {
		return affiliatedCustomers;
	}

	/**
	 * Sets the affiliatedCustomers.
	 *
	 * @param list of affiliatedCustomers the affiliatedCustomers to set
	 */
	public void setAffiliatedCustomers(List<CustomerAffiliation> affiliatedCustomers) {
		this.affiliatedCustomers = affiliatedCustomers;
	}

	/**
	 * Gets the alignment.
	 *
	 * @return the alignment
	 */
	public Alignment getAlignment() {
		return alignment;
	}

	/**
	 * Sets the alignment.
	 *
	 * @param alignment the alignment to set
	 */
	public void setAlignment(Alignment alignment) {
		this.alignment = alignment;
	}

	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public StatusType getStatus() {
		return status;
	}

	/**
	 * Sets the status.
	 *
	 * @param status the status to set
	 */
	public void setStatus(StatusType status) {
		this.status = status;
	}

	/**
	 * Gets the sales org.
	 *
	 * @return the sales org
	 */
	public SalesOrg getSalesOrg() {
		return salesOrg;
	}

	/**
	 * Sets the sales org.
	 *
	 * @param salesOrg the new sales org
	 */
	public void setSalesOrg(SalesOrg salesOrg) {
		this.salesOrg = salesOrg;
	}

	
	/**
	 * Checks if is isPrimaryAffiliation.
	 *
	 * @return true, if is isPrimary Affiliation
	 */
	public boolean isPrimaryAffiliation() {
		return isPrimaryAffiliation;
	}

	/**
	 * Sets the isPrimaryAffiliation.
	 *
	 * @param isPrimaryAffiliation the new primary affiliation
	 */
	public void setPrimaryAffiliation(boolean isPrimaryAffiliation) {
		this.isPrimaryAffiliation = isPrimaryAffiliation;
	}

	/**
	 * Gets the relationship type.
	 *
	 * @return the relationship type
	 */
	/*public Integer getRelationshipType() {
		return relationshipType;
	}*/

	/**
	 * Sets the relationship type.
	 *
	 * @param relationshipType the new relationship type
	 */
	/*public void setRelationshipType(Integer relationshipType) {
		this.relationshipType = relationshipType;
	}*/

	public AffiliationRelationType getAffRelationType() {
		return affRelationType;
	}

	public void setAffRelationType(AffiliationRelationType affRelationType) {
		this.affRelationType = affRelationType;
	}

	/**
	 * Checks if is root aff.
	 *
	 * @return true, if is root aff
	 */
	public boolean isRoot() {
		return isRoot;
	}

	/**
	 * Sets the rootAff.
	 *
	 * @param isRootAff the new rootAaff
	 */
	public void setRoot(boolean isRoot) {
		this.isRoot = isRoot;
	}

	@Override
	public String toString() {
		return "CustomerAffiliation [customer=" + customer
				+ ", affiliatedCustomers=" + affiliatedCustomers
				+ ", alignment=" + alignment + ", salesOrg=" + salesOrg
				+ ", status=" + status + ", isPrimaryAffiliation="
				+ isPrimaryAffiliation + ", affRelationType=" + affRelationType
				+ ", isRoot=" + isRoot + "]";
	}

}
