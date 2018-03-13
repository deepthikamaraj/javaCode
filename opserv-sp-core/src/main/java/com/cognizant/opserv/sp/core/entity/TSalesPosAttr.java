package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

/** 
 * JPA class representing TSalesPosAttr 
 * The corresponding mapping table is t_sales_pos_attr 
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "FindAllTSalesPosAttrs", query = "select myTSalesPosAttr from TSalesPosAttr myTSalesPosAttr"),
		@NamedQuery(name = "CountTSalesPosAttrs", query = "Select Count(c) from TSalesPosAttr c"),
		@NamedQuery(name = "FindTSalesPosAttrByTSalesPos", query = "select myTSalesPosAttr from TSalesPosAttr myTSalesPosAttr where myTSalesPosAttr.tSalesPos = ?1 "),
		@NamedQuery(name = "CountTSalesPosAttrsByTSalesPos", query = "select Count(*) from TSalesPosAttr myTSalesPosAttr where myTSalesPosAttr.tSalesPos = ?1 "),
		@NamedQuery(name = "FindTSalesPosAttrByTAttrDef", query = "select myTSalesPosAttr from TSalesPosAttr myTSalesPosAttr where myTSalesPosAttr.tAttrDef = ?1 "),
		@NamedQuery(name = "CountTSalesPosAttrsByTAttrDef", query = "select Count(*) from TSalesPosAttr myTSalesPosAttr where myTSalesPosAttr.tAttrDef = ?1 "),
		@NamedQuery(name = "FindTSalesPosAttrByTAttrDefPosIdAndTenant", query = "select myTSalesPosAttr from TSalesPosAttr myTSalesPosAttr where myTSalesPosAttr.tSalesPosAttrId.attrId = ?1 and " +
				" myTSalesPosAttr.tSalesPosAttrId.salesPosId = ?2 and myTSalesPosAttr.tenantId=?3"),
		@NamedQuery(name = "GetTSalesPosAttrById", query = "select myTSalesPosAttr from TSalesPosAttr myTSalesPosAttr where myTSalesPosAttr.tSalesPosAttrId.salesPosId=?1 " +
				"AND myTSalesPosAttr.tSalesPosAttrId.salesHierId=?2 AND myTSalesPosAttr.tenantId=?3 "),
				@NamedQuery(name = "GetTSalesPosAttrByAttrId", query = "select myTSalesPosAttr from TSalesPosAttr myTSalesPosAttr where myTSalesPosAttr.tAttrDef.attrId=?1 AND myTSalesPosAttr.attrValue=?2 AND myTSalesPosAttr.tenantId=?3 AND myTSalesPosAttr.tSalesPosAttrId.salesPosId <> ?4 AND myTSalesPosAttr.tSalesPosAttrId.salesHierId <> ?5"),
		@NamedQuery(name = "GetTSalesPosAttrValBySP", query = "select myTSalesPosAttr.tSalesPosAttrId.attrId,myTSalesPosAttr.attrValue from TSalesPosAttr myTSalesPosAttr where myTSalesPosAttr.tSalesPosAttrId.salesPosId = ?1 and myTSalesPosAttr.tenantId=?2 and myTSalesPosAttr.activeFlag = 'Y'")
})
@Table(name = "t_sales_pos_attr")
public class TSalesPosAttr implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@EmbeddedId
	@Valid
	private TSalesPosAttrId tSalesPosAttrId;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "active_flag", nullable = true, length = 1)
	private Character activeFlag;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 3000)
	@Column(name = "attr_value", nullable = true, length = 3000)
	private String attrValue;

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

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "sales_pos_id", referencedColumnName = "sales_pos_id", nullable = false, insertable = false, updatable = false)
	@Valid
	private TSalesPos tSalesPos;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "attr_id", referencedColumnName = "attr_id", nullable = false, insertable = false, updatable = false)
	@Valid
	private TAttrDef tAttrDef;

	/**
	 *
	 * @generated
	 */
	public TSalesPosAttr() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setTSalesPosAttrId(final TSalesPosAttrId tSalesPosAttrId) {
		this.tSalesPosAttrId = tSalesPosAttrId;
	}

	/**
	 * 
	 * @generated
	 */
	public TSalesPosAttrId getTSalesPosAttrId() {
		return this.tSalesPosAttrId;
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

	public void setAttrValue(final String attrValue) {
		this.attrValue = attrValue;
	}

	/**
	 * 
	 * @generated
	 */
	public String getAttrValue() {
		return this.attrValue;
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
	public TSalesPos getTSalesPos() {
		return this.tSalesPos;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTSalesPos(final TSalesPos tSalesPos) {
		this.tSalesPos = tSalesPos;
		if (this.tSalesPos != null && this.tSalesPos.getSalesPosId() != null) {

			this.tSalesPosAttrId.setSalesPosId(this.tSalesPos.getSalesPosId());

		}
		if (this.tSalesPos != null && this.tSalesPos.getSalesPosId() != null) {

			this.tSalesPosAttrId.setSalesHierId(this.tSalesPos.getTAlgmntSalesHier().getSalesHierId());

		}

	}

	/**
	 * 
	 * @generated
	 */
	public TAttrDef getTAttrDef() {
		return this.tAttrDef;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTAttrDef(final TAttrDef tAttrDef) {
		this.tAttrDef = tAttrDef;
		if (this.tAttrDef != null && this.tAttrDef.getAttrId() != null) {

			this.tSalesPosAttrId.setAttrId(this.tAttrDef.getAttrId());

		}

	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TSalesPosAttr that) {
		setTSalesPosAttrId(that.getTSalesPosAttrId());
		setActiveFlag(that.getActiveFlag());
		setAttrValue(that.getAttrValue());
		setCreatedBy(that.getCreatedBy());
		setCreateDt(that.getCreateDt());
		setUpdatedBy(that.getUpdatedBy());
		setUpdateDt(that.getUpdateDt());
		setTenantId(that.getTenantId());
	}

	/**
	 * @generated
	 * 
	 */
	@Override
	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = prime * result + ((tSalesPosAttrId == null) ? 0 : tSalesPosAttrId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("tSalesPosAttrId=[").append(tSalesPosAttrId).append("] ");
		buffer.append("activeFlag=[").append(activeFlag).append("] ");
		buffer.append("attrValue=[").append(attrValue).append("] ");
		buffer.append("createdBy=[").append(createdBy).append("] ");
		buffer.append("createDt=[").append(createDt).append("] ");
		buffer.append("updatedBy=[").append(updatedBy).append("] ");
		buffer.append("updateDt=[").append(updateDt).append("] ");
		buffer.append("tenantId=[").append(tenantId).append("] ");

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
		final TSalesPosAttr other = (TSalesPosAttr) obj;
		if (tSalesPosAttrId == null) {
			if (other.tSalesPosAttrId != null) {
				return false;
			}
		} else if (!tSalesPosAttrId.equals(other.tSalesPosAttrId)) {
			return false;
		}
		return true;
	}
}
