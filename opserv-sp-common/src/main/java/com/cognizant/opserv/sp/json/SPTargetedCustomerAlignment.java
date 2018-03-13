package com.cognizant.opserv.sp.json;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;
import org.codehaus.jackson.annotate.JsonProperty;
// TODO: Auto-generated Javadoc
/**
 * ****************************************************************************.
 *
 * @class SPTargetedCustomerAlignment
 * @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * @since 11/05/2016
 * ***************************************************************************
 */
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class SPTargetedCustomerAlignment implements Serializable, ISPTargetedDocument {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 313085157305941706L;
	
	/** The salespos id. */
	@JsonProperty("spId")
	private Long salesposId;
	
	/** The alignment id. */
	@JsonProperty("alId")
	private Long alignmentId;
	
	/** The buss id. */
	@JsonProperty("buId")
	private Long bussId;
	
	/** The sales team id. */
	@JsonProperty("stmId")
	private Long salesTeamId;
	
	/** The cust aligns. */
	@JsonProperty("cAl")
	private List<CustomerAlignment> custAligns;
	
	/** The doc created date. */
	@JsonProperty("dcDt")
	private Date docCreatedDate;
	
	/** The doc updated date. */
	@JsonProperty("duDt")
	private Date docUpdatedDate;
	
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
	 * Gets the alignment id.
	 *
	 * @return the alignment id
	 */
	public Long getAlignmentId() {
		return alignmentId;
	}
	
	/**
	 * Sets the alignment id.
	 *
	 * @param alignmentId the new alignment id
	 */
	public void setAlignmentId(Long alignmentId) {
		this.alignmentId = alignmentId;
	}
	
	/**
	 * Gets the buss id.
	 *
	 * @return the buss id
	 */
	public Long getBussId() {
		return bussId;
	}
	
	/**
	 * Sets the buss id.
	 *
	 * @param bussId the new buss id
	 */
	public void setBussId(Long bussId) {
		this.bussId = bussId;
	}
	
	/**
	 * Gets the sales team id.
	 *
	 * @return the sales team id
	 */
	public Long getSalesTeamId() {
		return salesTeamId;
	}
	
	/**
	 * Sets the sales team id.
	 *
	 * @param salesTeamId the new sales team id
	 */
	public void setSalesTeamId(Long salesTeamId) {
		this.salesTeamId = salesTeamId;
	}
	
	/**
	 * Gets the cust aligns.
	 *
	 * @return the cust aligns
	 */
	public List<CustomerAlignment> getCustAligns() {
		return custAligns;
	}
	
	/**
	 * Sets the cust aligns.
	 *
	 * @param custAligns the new cust aligns
	 */
	public void setCustAligns(List<CustomerAlignment> custAligns) {
		this.custAligns = custAligns;
	}
	
	
	
}
