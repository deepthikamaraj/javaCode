package com.cognizant.opserv.sp.json;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;	
/**
 * ****************************************************************************.
 *
 * @class ProductDetails
 * @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * @since 11/05/2016
 * ***************************************************************************
 */
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class ProductDetails implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 8345618260142498442L;

	/** The prod id. */
	@JsonProperty("pId")
	private Long prodId;

	/** The prod align id. */
	@JsonProperty("pAId")
	private Long prodAlignId;

	/** The prod name. */
	@JsonProperty("pNa")
	private String prodName;

	/** The prod code. */
	@JsonProperty("pCo")
	private String prodCode;
	
	/** The is comp applicable. */
	@JsonProperty("coAp")
	private boolean isCompApplicable;

	/** The effective start date. */
	@JsonProperty("sDt")
	private Date effectiveStartDate;

	/** The effective end date. */
	@JsonProperty("eDt")
	private Date effectiveEndDate;
	
	/** The prod ext attrs. */
	@JsonProperty("pEa")
	private List<ExtendedAttribute> prodExtAttrs;

	/** The cust prod ext attrs. */
	@JsonProperty("cpEa")
	private List<ExtendedAttribute> custProdExtAttrs;
	

	/**
	 * Sets the prod id.
	 *
	 * @param prodId the new prod id
	 */
	public void setProdId(Long prodId) {
		this.prodId = prodId;
	}

	/**
	 * Gets the prod id.
	 *
	 * @return the prod id
	 */
	public Long getProdId() {
		return prodId;
	}
	
	/**
	 * Gets the prod align id.
	 *
	 * @return the prod align id
	 */
	public Long getProdAlignId() {
		return prodAlignId;
	}

	/**
	 * Sets the prod align id.
	 *
	 * @param prodAlignId the new prod align id
	 */
	public void setProdAlignId(Long prodAlignId) {
		this.prodAlignId = prodAlignId;
	}

	/**
	 * Gets the prod name.
	 *
	 * @return the prod name
	 */
	public String getProdName() {
		return prodName;
	}

	/**
	 * Sets the prod name.
	 *
	 * @param prodName the new prod name
	 */
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	/**
	 * Gets the prod code.
	 *
	 * @return the prod code
	 */
	public String getProdCode() {
		return prodCode;
	}

	/**
	 * Sets the prod code.
	 *
	 * @param prodCode the new prod code
	 */
	public void setProdCode(String prodCode) {
		this.prodCode = prodCode;
	}
	
	/**
	 * Checks if is comp applicable.
	 *
	 * @return true, if is comp applicable
	 */
	public boolean isCompApplicable() {
		return isCompApplicable;
	}
	
	/**
	 * Sets the comp applicable.
	 *
	 * @param isCompApplicable the new comp applicable
	 */
	public void setCompApplicable(boolean isCompApplicable) {
		this.isCompApplicable = isCompApplicable;
	}

	/**
	 * Gets the effective start date.
	 *
	 * @return the effective start date
	 */
	public Date getEffectiveStartDate() {
		return effectiveStartDate;
	}

	/**
	 * Sets the effective start date.
	 *
	 * @param effectiveStartDate the new effective start date
	 */
	public void setEffectiveStartDate(Date effectiveStartDate) {
		this.effectiveStartDate = effectiveStartDate;
	}

	/**
	 * Gets the effective end date.
	 *
	 * @return the effective end date
	 */
	public Date getEffectiveEndDate() {
		return effectiveEndDate;
	}

	/**
	 * Sets the effective end date.
	 *
	 * @param effectiveEndDate the new effective end date
	 */
	public void setEffectiveEndDate(Date effectiveEndDate) {
		this.effectiveEndDate = effectiveEndDate;
	}

	/**
	 * Gets the serialversionuid.
	 *
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * Gets the prod ext attrs.
	 *
	 * @return the prod ext attrs
	 */
	public List<ExtendedAttribute> getProdExtAttrs() {
		return prodExtAttrs;
	}

	/**
	 * Sets the prod ext attrs.
	 *
	 * @param prodExtAttrs the new prod ext attrs
	 */
	public void setProdExtAttrs(List<ExtendedAttribute> prodExtAttrs) {
		this.prodExtAttrs = prodExtAttrs;
	}

	/**
	 * Gets the cust prod ext attrs.
	 *
	 * @return the cust prod ext attrs
	 */
	public List<ExtendedAttribute> getCustProdExtAttrs() {
		return custProdExtAttrs;
	}

	/**
	 * Sets the cust prod ext attrs.
	 *
	 * @param custProdExtAttrs the new cust prod ext attrs
	 */
	public void setCustProdExtAttrs(List<ExtendedAttribute> custProdExtAttrs) {
		this.custProdExtAttrs = custProdExtAttrs;
	}
	
	

}
