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
 * @class ProductAlignment
 * @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * @since 11/05/2016
 * ***************************************************************************
 */
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class ProductAlignment implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -100842061577052591L;
	
	/** The salespos id. */
	@JsonProperty("spId")
	private Long salesposId;
	
	/** The salespos name. */
	@JsonProperty("spnm")
	private String salesposName;
	
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
	
	/** The weightage. */
	@JsonProperty("wt")
	private String weightage;
	
	/** The class value. */
	@JsonProperty("clv")
    private String classValue;
	
	/** The class type. */
	@JsonProperty("clty")
    private String classType;
	
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
	
	/** The prod align ext attrs. */
	@JsonProperty("paEa")
	private List<ExtendedAttribute> prodAlignExtAttrs;
	

	
	
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
	 * Gets the salespos name.
	 *
	 * @return the salespos name
	 */
	public String getSalesposName() {
		return salesposName;
	}
	
	/**
	 * Sets the salespos name.
	 *
	 * @param salesposName the new salespos name
	 */
	public void setSalesposName(String salesposName) {
		this.salesposName = salesposName;
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
	 * Sets the prod id.
	 *
	 * @param prodId the new prod id
	 */
	public void setProdId(Long prodId) {
		this.prodId = prodId;
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
	 * Gets the weightage.
	 *
	 * @return the weightage
	 */
	public String getWeightage() {
		return weightage;
	}
	
	/**
	 * Sets the weightage.
	 *
	 * @param weightage the new weightage
	 */
	public void setWeightage(String weightage) {
		this.weightage = weightage;
	}
	
	/**
	 * Gets the class value.
	 *
	 * @return the class value
	 */
	public String getClassValue() {
		return classValue;
	}
	
	/**
	 * Sets the class value.
	 *
	 * @param classValue the new class value
	 */
	public void setClassValue(String classValue) {
		this.classValue = classValue;
	}
	
	/**
	 * Gets the class type.
	 *
	 * @return the class type
	 */
	public String getClassType() {
		return classType;
	}
	
	/**
	 * Sets the class type.
	 *
	 * @param classType the new class type
	 */
	public void setClassType(String classType) {
		this.classType = classType;
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
	 * Gets the prod align ext attrs.
	 *
	 * @return the prod align ext attrs
	 */
	public List<ExtendedAttribute> getProdAlignExtAttrs() {
		return prodAlignExtAttrs;
	}
	
	/**
	 * Sets the prod align ext attrs.
	 *
	 * @param prodAlignExtAttrs the new prod align ext attrs
	 */
	public void setProdAlignExtAttrs(List<ExtendedAttribute> prodAlignExtAttrs) {
		this.prodAlignExtAttrs = prodAlignExtAttrs;
	}
	

	
}
