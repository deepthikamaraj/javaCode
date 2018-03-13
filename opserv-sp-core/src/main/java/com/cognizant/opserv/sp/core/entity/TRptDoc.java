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

import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/** 
 * JPA class representing TRptDoc 
 * The corresponding mapping table is t_rpt_doc 
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "FindAllTRptDocs", query = "select myTRptDoc from TRptDoc myTRptDoc"),
		@NamedQuery(name = "CountTRptDocs", query = "Select Count(c) from TRptDoc c"),
		@NamedQuery(name = "FindTRptDocByTRpt", query = "select myTRptDoc from TRptDoc myTRptDoc where myTRptDoc.tRpt = ?1 "),
		@NamedQuery(name = "CountTRptDocsByTRpt", query = "select Count(*) from TRptDoc myTRptDoc where myTRptDoc.tRpt = ?1 ") })
@Table(name = "t_rpt_doc")
public class TRptDoc implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@EmbeddedId
	@Valid
	@Audited
	private TRptDocId tRptDocId;

	/**
	 * 
	 * @generated
	 */
	@NotEmpty
	@Length(max = 1024)
	@Column(name = "doc_name", nullable = false, length = 50)
	private String docName;

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
	private Date updateDate;

	/**
	 * 
	 * @generated
	 */
	@NotNull
	@Column(name = "tenant_id", nullable = false, length = 255)
	private Short tenantId;

	@ManyToOne
	@JoinColumn(name = "rpt_id", referencedColumnName = "rpt_id", nullable = false, insertable = false, updatable = false)
	@Valid
	private TRpt tRpt;

	/**
	 *
	 * @generated
	 */
	public TRptDoc() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setTRptDocId(final TRptDocId tRptDocId) {
		this.tRptDocId = tRptDocId;
	}

	/**
	 * 
	 * @generated
	 */
	public TRptDocId getTRptDocId() {
		return this.tRptDocId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setDocName(final String docName) {
		this.docName = docName;
	}

	/**
	 * 
	 * @generated
	 */
	public String getDocName() {
		return this.docName;
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

	public void setUpdateDate(final Date updateDate) {
		if (updateDate != null) {
			this.updateDate = (Date) updateDate.clone();
		} else {
			this.updateDate = null;
		}
	}

	/**
	 * 
	 * @generated
	 */
	public Date getUpdateDate() {
		if (this.updateDate != null) {
			return (Date) this.updateDate.clone();
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
	public TRpt getTRpt() {
		return this.tRpt;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTRpt(final TRpt tRpt) {
		this.tRpt = tRpt;
		if (this.tRpt != null && this.tRpt.getRptId() != null) {

			this.tRptDocId.setRptId(this.tRpt.getRptId());

		}

	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TRptDoc that) {
		setTRptDocId(that.getTRptDocId());
		setDocName(that.getDocName());
		setActiveFlag(that.getActiveFlag());
		setCreatedBy(that.getCreatedBy());
		setCreateDt(that.getCreateDt());
		setUpdatedBy(that.getUpdatedBy());
		setUpdateDate(that.getUpdateDate());
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
		result = prime * result + ((tRptDocId == null) ? 0 : tRptDocId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("tRptDocId=[").append(tRptDocId).append("] ");
		buffer.append("docName=[").append(docName).append("] ");
		buffer.append("activeFlag=[").append(activeFlag).append("] ");
		buffer.append("createdBy=[").append(createdBy).append("] ");
		buffer.append("createDt=[").append(createDt).append("] ");
		buffer.append("updatedBy=[").append(updatedBy).append("] ");
		buffer.append("updateDate=[").append(updateDate).append("] ");
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
		final TRptDoc other = (TRptDoc) obj;
		if (tRptDocId == null) {
			if (other.tRptDocId != null) {
				return false;
			}
		} else if (!tRptDocId.equals(other.tRptDocId)) {
			return false;
		}
		return true;
	}
}
