package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/** 
 * JPA class representing TRecipientAttr 
 * The corresponding mapping table is t_recipient_attr 
 */

@Entity
@Audited
@NamedQueries({
		@NamedQuery(name = "FindAllTRecipientAttrs", query = "select myTRecipientAttr from TRecipientAttr myTRecipientAttr"),
		@NamedQuery(name = "CountTRecipientAttrs", query = "Select Count(c) from TRecipientAttr c"),
		@NamedQuery(name = "FindTRecipientAttrByTTargetRecipientPref", query = "select myTRecipientAttr from TRecipientAttr myTRecipientAttr where myTRecipientAttr.tTargetRecipientPref = ?1 "),
		@NamedQuery(name = "CountTRecipientAttrsByTTargetRecipientPref", query = "select Count(*) from TRecipientAttr myTRecipientAttr where myTRecipientAttr.tTargetRecipientPref = ?1 "),
		@NamedQuery(name = "FindTRecipientAttrByTAttr", query = "select myTRecipientAttr from TRecipientAttr myTRecipientAttr where myTRecipientAttr.tAttr = ?1 "),
		@NamedQuery(name = "CountTRecipientAttrsByTAttr", query = "select Count(*) from TRecipientAttr myTRecipientAttr where myTRecipientAttr.tAttr = ?1 "),
		@NamedQuery(name = "FindTRecipientAttrsByRecipientPrefId", query = "select myTRecipientAttr.tRecipientAttrId.attrId, myTRecipientAttr.comparisonFactor ," +
				" myTRecipientAttr.operator, myTPrefAttrValue.value" +
				" from TRecipientAttr myTRecipientAttr , TPrefAttrValue myTPrefAttrValue " +
				" where myTPrefAttrValue.tRecipientAttr.tRecipientAttrId.recipientPrefId = myTRecipientAttr.tRecipientAttrId.recipientPrefId " +
				" and myTRecipientAttr.tRecipientAttrId.recipientPrefId = ?1 ") 
		})
@Table(name = "t_recipient_attr")
public class TRecipientAttr implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@EmbeddedId
	@Valid
	private TRecipientAttrId tRecipientAttrId;

	/**
	 * 
	 * @generated
	 */
	@NotEmpty
	@Length(max = 50)
	@Column(name = "comparison_factor", nullable = false, length = 50)
	private String comparisonFactor;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 5)
	@Column(name = "operator", nullable = true, length = 5)
	private String operator;

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
	@NotNull
	@Column(name = "tenant_id", nullable = false, length = 255)
	private Short tenantId;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "recipient_pref_id", referencedColumnName = "recipient_pref_id", nullable = false, insertable = false, updatable = false)
	@Valid
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private TTargetRecipientPref tTargetRecipientPref;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "attr_id", referencedColumnName = "attr_id", nullable = false, insertable = false, updatable = false)
	@Valid
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private TAttr tAttr;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tRecipientAttr")
	@Audited
	private Set<TPrefAttrValue> tPrefAttrValuess;

	/**
	 *
	 * @generated
	 */
	public TRecipientAttr() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setTRecipientAttrId(final TRecipientAttrId tRecipientAttrId) {
		this.tRecipientAttrId = tRecipientAttrId;
	}

	/**
	 * 
	 * @generated
	 */
	public TRecipientAttrId getTRecipientAttrId() {
		return this.tRecipientAttrId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setComparisonFactor(final String comparisonFactor) {
		this.comparisonFactor = comparisonFactor;
	}

	/**
	 * 
	 * @generated
	 */
	public String getComparisonFactor() {
		return this.comparisonFactor;
	}

	/**
	 * 
	 * @generated
	 */

	public void setOperator(final String operator) {
		this.operator = operator;
	}

	/**
	 * 
	 * @generated
	 */
	public String getOperator() {
		return this.operator;
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
	public TTargetRecipientPref getTTargetRecipientPref() {
		return this.tTargetRecipientPref;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTTargetRecipientPref(final TTargetRecipientPref tTargetRecipientPref) {
		this.tTargetRecipientPref = tTargetRecipientPref;
		if (this.tTargetRecipientPref != null && this.tTargetRecipientPref.getRecipientPrefId() != null) {

			this.tRecipientAttrId.setRecipientPrefId(this.tTargetRecipientPref.getRecipientPrefId());

		}

	}

	/**
	 * 
	 * @generated
	 */
	public TAttr getTAttr() {
		return this.tAttr;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTAttr(final TAttr tAttr) {
		this.tAttr = tAttr;
		if (this.tAttr != null && this.tAttr.getAttrId() != null) {

			this.tRecipientAttrId.setAttrId(this.tAttr.getAttrId());

		}

	}

	/**
	 * 
	 * @generated
	 */
	public Set<TPrefAttrValue> getTPrefAttrValuess() {
		return this.tPrefAttrValuess;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTPrefAttrValuess(final Set<TPrefAttrValue> tPrefAttrValuess) {
		this.tPrefAttrValuess = tPrefAttrValuess;
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TRecipientAttr that) {
		setTRecipientAttrId(that.getTRecipientAttrId());
		setComparisonFactor(that.getComparisonFactor());
		setOperator(that.getOperator());
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
		result = prime * result + ((tRecipientAttrId == null) ? 0 : tRecipientAttrId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("tRecipientAttrId=[").append(tRecipientAttrId).append("] ");
		buffer.append("comparisonFactor=[").append(comparisonFactor).append("] ");
		buffer.append("operator=[").append(operator).append("] ");
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
		final TRecipientAttr other = (TRecipientAttr) obj;
		if (tRecipientAttrId == null) {
			if (other.tRecipientAttrId != null) {
				return false;
			}
		} else if (!tRecipientAttrId.equals(other.tRecipientAttrId)) {
			return false;
		}
		return true;
	}
}
