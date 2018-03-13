package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;

import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/** 
 * JPA class representing TPrefAttrValue 
 * The corresponding mapping table is t_pref_attr_value 
 */

@Entity
@Audited
@NamedQueries({
		@NamedQuery(name = "FindAllTPrefAttrValues", query = "select myTPrefAttrValue from TPrefAttrValue myTPrefAttrValue"),
		@NamedQuery(name = "CountTPrefAttrValues", query = "Select Count(c) from TPrefAttrValue c"),
		@NamedQuery(name = "FindTPrefAttrValueByTRecipientAttr", query = "select myTPrefAttrValue from TPrefAttrValue myTPrefAttrValue where myTPrefAttrValue.tRecipientAttr = ?1 "),
		@NamedQuery(name = "CountTPrefAttrValuesByTRecipientAttr", query = "select Count(myTPrefAttrValue) from TPrefAttrValue myTPrefAttrValue where myTPrefAttrValue.tRecipientAttr = ?1 ") })
@Table(name = "t_pref_attr_value", uniqueConstraints = @UniqueConstraint(columnNames = { "attr_value_sequence_id" }))
public class TPrefAttrValue implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "attr_value_sequence_id", nullable = false, length = 255)
	private Integer attrValueSequenceId;

	/**
	 * 
	 * @generated
	 */
	@NotEmpty
	@Length(max = 300)
	@Column(name = "value", nullable = false, length = 300)
	private String value;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "created_by", nullable = true, length = 255)
	private Integer createdBy;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "create_dt", nullable = true, length = 19)
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
	@Column(name = "tenant_id", nullable = true, length = 255)
	private Short tenantId;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "recipient_pref_id", referencedColumnName = "recipient_pref_id", nullable = true, insertable = true, updatable = true),
			@JoinColumn(name = "attr_id", referencedColumnName = "attr_id", nullable = true, insertable = true, updatable = true),
			@JoinColumn(name = "sequence_id", referencedColumnName = "sequence_id", nullable = true, insertable = true, updatable = true) })
	@Valid
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private TRecipientAttr tRecipientAttr;

	/**
	 *
	 * @generated
	 */
	public TPrefAttrValue() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setAttrValueSequenceId(final Integer attrValueSequenceId) {
		this.attrValueSequenceId = attrValueSequenceId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getAttrValueSequenceId() {
		return this.attrValueSequenceId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setValue(final String value) {
		this.value = value;
	}

	/**
	 * 
	 * @generated
	 */
	public String getValue() {
		return this.value;
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
	public TRecipientAttr getTRecipientAttr() {
		return this.tRecipientAttr;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTRecipientAttr(final TRecipientAttr tRecipientAttr) {
		this.tRecipientAttr = tRecipientAttr;

	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TPrefAttrValue that) {
		setAttrValueSequenceId(that.getAttrValueSequenceId());
		setValue(that.getValue());
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
		result = prime * result + ((attrValueSequenceId == null) ? 0 : attrValueSequenceId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("attrValueSequenceId=[").append(attrValueSequenceId).append("] ");
		buffer.append("value=[").append(value).append("] ");
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
		final TPrefAttrValue other = (TPrefAttrValue) obj;
		if (attrValueSequenceId == null) {
			if (other.attrValueSequenceId != null) {
				return false;
			}
		} else if (!attrValueSequenceId.equals(other.attrValueSequenceId)) {
			return false;
		}
		return true;
	}
}
