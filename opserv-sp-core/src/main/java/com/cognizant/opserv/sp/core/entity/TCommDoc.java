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
import org.hibernate.envers.NotAudited;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/** 
 * JPA class representing TCommDoc 
 * The corresponding mapping table is t_comm_doc 
 */

@Entity
@Audited
@NamedQueries({
		@NamedQuery(name = "FindAllTCommDocs", query = "select myTCommDoc from TCommDoc myTCommDoc"),
		@NamedQuery(name = "CountTCommDocs", query = "Select Count(c) from TCommDoc c"),
		@NamedQuery(name = "FindTCommDocByTComm", query = "select myTCommDoc from TCommDoc myTCommDoc where myTCommDoc.tComm = ?1 "),
		@NamedQuery(name = "CountTCommDocsByTCommids", query = "select Count(*), myTCommDoc.tCommDocId.commId from TCommDoc myTCommDoc where myTCommDoc.tComm.commId in ?1 and myTCommDoc.tenantId = ?2 GROUP BY myTCommDoc.tCommDocId.commId "),
		@NamedQuery(name = "CountTCommDocsByTComm", query = "select Count(*) from TCommDoc myTCommDoc where myTCommDoc.tComm = ?1 ") })
@Table(name = "t_comm_doc")
public class TCommDoc implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@EmbeddedId
	@Valid
	private TCommDocId tCommDocId;

	/**
	 * 
	 * @generated
	 */
	@NotEmpty
	@Length(max = 1024)
	@Column(name = "doc_location", nullable = false, length = 1024)
	private String docLocation;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "published_dt", nullable = true, length = 10)
	private Date publishedDt;

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

	/**
	 * 
	 * @generated
	 */
	@NotEmpty
	@Length(max = 50)
	@Column(name = "doc_name", nullable = false, length = 20)
	private String docName;

	@ManyToOne
	@JoinColumn(name = "comm_id", referencedColumnName = "comm_id", nullable = false, insertable = false, updatable = false)
	@Valid
	@NotAudited
	private TComm tComm;

	/**
	 *
	 * @generated
	 */
	public TCommDoc() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setTCommDocId(final TCommDocId tCommDocId) {
		this.tCommDocId = tCommDocId;
	}

	/**
	 * 
	 * @generated
	 */
	public TCommDocId getTCommDocId() {
		return this.tCommDocId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setDocLocation(final String docLocation) {
		this.docLocation = docLocation;
	}

	/**
	 * 
	 * @generated
	 */
	public String getDocLocation() {
		return this.docLocation;
	}

	/**
	 * 
	 * @generated
	 */

	public void setPublishedDt(final Date publishedDt) {
		if (publishedDt != null) {
			this.publishedDt = (Date) publishedDt.clone();
		} else {
			this.publishedDt = null;
		}
	}

	/**
	 * 
	 * @generated
	 */
	public Date getPublishedDt() {
		if (this.publishedDt != null) {
			return (Date) this.publishedDt.clone();
		} else {
			return null;
		}
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
	public TComm getTComm() {
		return this.tComm;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTComm(final TComm tComm) {
		this.tComm = tComm;
		if (this.tComm != null && this.tComm.getCommId() != null) {

			this.tCommDocId.setCommId(this.tComm.getCommId());

		}

	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TCommDoc that) {
		setTCommDocId(that.getTCommDocId());
		setDocLocation(that.getDocLocation());
		setPublishedDt(that.getPublishedDt());
		setActiveFlag(that.getActiveFlag());
		setCreatedBy(that.getCreatedBy());
		setCreateDt(that.getCreateDt());
		setUpdatedBy(that.getUpdatedBy());
		setUpdateDate(that.getUpdateDate());
		setTenantId(that.getTenantId());
		setDocName(that.getDocName());
	}

	/**
	 * @generated
	 * 
	 */
	@Override
	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = prime * result + ((tCommDocId == null) ? 0 : tCommDocId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("tCommDocId=[").append(tCommDocId).append("] ");
		buffer.append("docLocation=[").append(docLocation).append("] ");
		buffer.append("publishedDt=[").append(publishedDt).append("] ");
		buffer.append("activeFlag=[").append(activeFlag).append("] ");
		buffer.append("createdBy=[").append(createdBy).append("] ");
		buffer.append("createDt=[").append(createDt).append("] ");
		buffer.append("updatedBy=[").append(updatedBy).append("] ");
		buffer.append("updateDate=[").append(updateDate).append("] ");
		buffer.append("tenantId=[").append(tenantId).append("] ");
		buffer.append("docName=[").append(docName).append("] ");

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
		final TCommDoc other = (TCommDoc) obj;
		if (tCommDocId == null) {
			if (other.tCommDocId != null) {
				return false;
			}
		} else if (!tCommDocId.equals(other.tCommDocId)) {
			return false;
		}
		return true;
	}
}
