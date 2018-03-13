package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/** 
 * JPA class representing TCustPrd 
 * The corresponding mapping table is t_cust_prd 
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "FindAllTCustPrds", query = "select myTCustPrd from TCustPrdSales myTCustPrd"),
		@NamedQuery(name = "CountTCustPrds", query = "Select Count(c) from TCustPrdSales c"),
		@NamedQuery(name = "FindTPrdByTCust", query = "select myTCustPrd.tPrd from TCustPrdSales myTCustPrd where myTCustPrd.tCust = ?1 and myTCustPrd.tenantId = ?2"),
		@NamedQuery(name = "FindTCustPrdByTCust", query = "select myTCustPrd from TCustPrdSales myTCustPrd where myTCustPrd.tCust = ?1 and myTCustPrd.tenantId = ?2"),
		@NamedQuery(name = "CountTCustPrdsByTCust", query = "select Count(*) from TCustPrdSales myTCustPrd where myTCustPrd.tCust = ?1 "),
		@NamedQuery(name = "FindTCustPrdByTPrd", query = "select myTCustPrd from TCustPrdSales myTCustPrd where myTCustPrd.tPrd = ?1 "),
		@NamedQuery(name = "CountTCustPrdsByTPrd", query = "select Count(*) from TCustPrdSales myTCustPrd where myTCustPrd.tPrd = ?1 ") })
@Table(name = "t_cust_prd_sales")
public class TCustPrdSales implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@EmbeddedId
	@Valid
	private TCustPrdSalesId tCustPrdId;

	/**
	 * 
	 * @generated
	 */
	@NotNull
	@Column(name = "active_flag", nullable = false, length = 1)
	private Character activeFlag;

	/**
	 * 
	 * @generated
	 */
	@NotNull
	@Column(name = "created_by", nullable = false, length = 255)
	private Integer createdBy;

	/**
	 * 
	 * @generated
	 */
	@NotNull
	@Column(name = "create_dt", nullable = false, length = 19)
	private Date createDt;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "updated_by", nullable = true, length = 255)
	private Integer updatedBy;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "update_dt", nullable = true, length = 19)
	private Date updateDt;

	/**
	 * 
	 * @generated
	 */
	@NotNull
	@Column(name = "tenant_id", nullable = false, length = 255)
	private Short tenantId;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "tm_per_id", nullable = true, length = 255)
	private Integer tmPerId;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "brd_dlr_sale_value", nullable = true, length = 255)
	private Float brdDlrSaleValue;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "brd_unit_sale_value", nullable = true, length = 255)
	private Float brdUnitSaleValue;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "mkt_dlr_sale_value", nullable = true, length = 255)
	private Float mktDlrSaleValue;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "mkt_unit_sale_value", nullable = true, length = 255)
	private Float mktUnitSaleValue;

	@ManyToOne
	@JoinColumn(name = "cust_id", referencedColumnName = "cust_id", nullable = false, insertable = false, updatable = false)
	@Valid
	private TCust tCust;

	@ManyToOne
	@JoinColumn(name = "prd_id", referencedColumnName = "prd_id", nullable = false, insertable = false, updatable = false)
	@Valid
	private TPrd tPrd;

	/**
	 *
	 * @generated
	 */
	public TCustPrdSales() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setTCustPrdId(final TCustPrdSalesId tCustPrdId) {
		this.tCustPrdId = tCustPrdId;
	}

	/**
	 * 
	 * @generated
	 */
	public TCustPrdSalesId getTCustPrdId() {
		return this.tCustPrdId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setActiveFlag(final Character activeFlag) {
		this.activeFlag = activeFlag;
	}

	/**
	 * 
	 * @generated
	 */
	public Character getActiveFlag() {
		return this.activeFlag;
	}

	/**
	 * 
	 * @generated
	 */

	public void setCreatedBy(final Integer createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getCreatedBy() {
		return this.createdBy;
	}

	/**
	 * 
	 * @generated
	 */

	public void setCreateDt(final Date createDt) {
		if (createDt != null) {
			this.createDt = (Date) createDt.clone();
		} else {
			this.createDt = null;
		}
	}

	/**
	 * 
	 * @generated
	 */
	public Date getCreateDt() {
		if (this.createDt != null) {
			return (Date) this.createDt.clone();
		} else {
			return null;
		}
	}

	/**
	 * 
	 * @generated
	 */

	public void setUpdatedBy(final Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getUpdatedBy() {
		return this.updatedBy;
	}

	/**
	 * 
	 * @generated
	 */

	public void setUpdateDt(final Date updateDt) {
		if (updateDt != null) {
			this.updateDt = (Date) updateDt.clone();
		} else {
			this.updateDt = null;
		}
	}

	/**
	 * 
	 * @generated
	 */
	public Date getUpdateDt() {
		if (this.updateDt != null) {
			return (Date) this.updateDt.clone();
		} else {
			return null;
		}
	}

	/**
	 * 
	 * @generated
	 */

	public void setTenantId(final Short tenantId) {
		this.tenantId = tenantId;
	}

	/**
	 * 
	 * @generated
	 */
	public Short getTenantId() {
		return this.tenantId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setTmPerId(final Integer tmPerId) {
		this.tmPerId = tmPerId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getTmPerId() {
		return this.tmPerId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setBrdDlrSaleValue(final Float brdDlrSaleValue) {
		this.brdDlrSaleValue = brdDlrSaleValue;
	}

	/**
	 * 
	 * @generated
	 */
	public Float getBrdDlrSaleValue() {
		return this.brdDlrSaleValue;
	}

	/**
	 * 
	 * @generated
	 */

	public void setBrdUnitSaleValue(final Float brdUnitSaleValue) {
		this.brdUnitSaleValue = brdUnitSaleValue;
	}

	/**
	 * 
	 * @generated
	 */
	public Float getBrdUnitSaleValue() {
		return this.brdUnitSaleValue;
	}

	/**
	 * 
	 * @generated
	 */

	public void setMktDlrSaleValue(final Float mktDlrSaleValue) {
		this.mktDlrSaleValue = mktDlrSaleValue;
	}

	/**
	 * 
	 * @generated
	 */
	public Float getMktDlrSaleValue() {
		return this.mktDlrSaleValue;
	}

	/**
	 * 
	 * @generated
	 */

	public void setMktUnitSaleValue(final Float mktUnitSaleValue) {
		this.mktUnitSaleValue = mktUnitSaleValue;
	}

	/**
	 * 
	 * @generated
	 */
	public Float getMktUnitSaleValue() {
		return this.mktUnitSaleValue;
	}

	/**
	 * 
	 * @generated
	 */
	public TCust getTCust() {
		return this.tCust;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTCust(final TCust tCust) {
		this.tCust = tCust;
		if (this.tCust != null && this.tCust.getCustId() != null) {

			this.tCustPrdId.setCustId(this.tCust.getCustId());

		}

	}

	/**
	 * 
	 * @generated
	 */
	public TPrd getTPrd() {
		return this.tPrd;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTPrd(final TPrd tPrd) {
		this.tPrd = tPrd;
		if (this.tPrd != null && this.tPrd.getPrdId() != null) {

			this.tCustPrdId.setPrdId(this.tPrd.getPrdId());

		}

	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TCustPrdSales that) {
		setTCustPrdId(that.getTCustPrdId());
		setActiveFlag(that.getActiveFlag());
		setCreatedBy(that.getCreatedBy());
		setCreateDt(that.getCreateDt());
		setUpdatedBy(that.getUpdatedBy());
		setUpdateDt(that.getUpdateDt());
		setTenantId(that.getTenantId());
		setTmPerId(that.getTmPerId());
		setBrdDlrSaleValue(that.getBrdDlrSaleValue());
		setBrdUnitSaleValue(that.getBrdUnitSaleValue());
		setMktDlrSaleValue(that.getMktDlrSaleValue());
		setMktUnitSaleValue(that.getMktUnitSaleValue());
	}

	/**
	 * @generated
	 * 
	 */
	@Override
	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = prime * result + ((tCustPrdId == null) ? 0 : tCustPrdId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("tCustPrdId=[").append(tCustPrdId).append("] ");
		buffer.append("activeFlag=[").append(activeFlag).append("] ");
		buffer.append("createdBy=[").append(createdBy).append("] ");
		buffer.append("createDt=[").append(createDt).append("] ");
		buffer.append("updatedBy=[").append(updatedBy).append("] ");
		buffer.append("updateDt=[").append(updateDt).append("] ");
		buffer.append("tenantId=[").append(tenantId).append("] ");
		buffer.append("tmPerId=[").append(tmPerId).append("] ");
		buffer.append("brdDlrSaleValue=[").append(brdDlrSaleValue).append("] ");
		buffer.append("brdUnitSaleValue=[").append(brdUnitSaleValue).append("] ");
		buffer.append("mktDlrSaleValue=[").append(mktDlrSaleValue).append("] ");
		buffer.append("mktUnitSaleValue=[").append(mktUnitSaleValue).append("] ");

		return buffer.toString();
	}

	/**
	 * @generated
	 * 
	 */
	@Override
	public boolean equals(final Object obj) {

		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final TCustPrdSales other = (TCustPrdSales) obj;
		if (tCustPrdId == null) {
			if (other.tCustPrdId != null) {
				return false;
			}
		} else if (!tCustPrdId.equals(other.tCustPrdId)) {
			return false;
		}
		return true;
	}
}
