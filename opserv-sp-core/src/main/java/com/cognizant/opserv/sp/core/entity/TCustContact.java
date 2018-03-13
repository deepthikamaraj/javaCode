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

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.validator.constraints.Length;
import org.jasypt.hibernate4.type.EncryptedStringType;

/** 
 * JPA class representing TCustContact 
 * The corresponding mapping table is t_cust_contact 
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "FindAllTCustContacts", query = "select myTCustContact from TCustContact myTCustContact"),
		@NamedQuery(name = "CountTCustContacts", query = "Select Count(c) from TCustContact c"),
		@NamedQuery(name = "FindTCustContactByTCust", query = "select myTCustContact from TCustContact myTCustContact where myTCustContact.tCust = ?1 "),
		@NamedQuery(name = "CountTCustContactsByTCust", query = "select Count(*) from TCustContact myTCustContact where myTCustContact.tCust = ?1 "),
		@NamedQuery(name = "FindTCustContactByTContactType", query = "select myTCustContact from TCustContact myTCustContact where myTCustContact.tContactType = ?1 "),
		@NamedQuery(name = "CountTCustContactsByTContactType", query = "select Count(*) from TCustContact myTCustContact where myTCustContact.tContactType = ?1 ")
		/*@NamedQuery(name = "FindTCustContactByTAddrType", query = "select myTCustContact from TCustContact myTCustContact where myTCustContact.tAddrType = ?1 "),*/
		/*@NamedQuery(name = "CountTCustContactsByTAddrType", query = "select Count(*) from TCustContact myTCustContact where myTCustContact.tAddrType = ?1 ")*/ })

@TypeDef (name="encryptedString", typeClass= EncryptedStringType.class,
parameters= {
@Parameter(name="encryptorRegisteredName",  value="hibernateStringEncryptor")
})
@Table(name = "t_cust_contact")
public class TCustContact implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@EmbeddedId
	@Valid
	private TCustContactId tCustContactId;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 500)
	@Column(name = "contact_detail", nullable = true, length = 200)
	@Type(type="encryptedString")
	private String contactDetail;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "pr_contact_flag", nullable = true, length = 1)
	private Character prContactFlag;

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

	@ManyToOne
	@JoinColumn(name = "cust_id", referencedColumnName = "cust_id", nullable = false, insertable = false, updatable = false)
	@Valid
	private TCust tCust;

	@ManyToOne
	@JoinColumn(name = "contact_type_id", referencedColumnName = "contact_type_id", nullable = false, insertable = true, updatable = true)
	@Valid
	private TContactType tContactType;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 20)
	@Column(name = "contact_extn", nullable = true, length = 20)
	private String contactExtn;

	/*@ManyToOne
	@JoinColumn(name = "addr_type_id", referencedColumnName = "addr_type_id", nullable = true, insertable = true, updatable = true)
	@Valid
	private TAddrType tAddrType;*/

	/**
	 *
	 * @generated
	 */
	/*public TCustContact() {
	}*/

	/**
	 * 
	 * @generated
	 */

	public void setTCustContactId(final TCustContactId tCustContactId) {
		this.tCustContactId = tCustContactId;
	}

	/**
	 * 
	 * @generated
	 */
	public TCustContactId getTCustContactId() {
		return this.tCustContactId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setContactDetail(final String contactDetail) {
		this.contactDetail = contactDetail;
	}

	/**
	 * 
	 * @generated
	 */
	@Type(type="encryptedString")
	public String getContactDetail() {
		return this.contactDetail;
	}

	/**
	 * 
	 * @generated
	 */

	public void setPrContactFlag(final Character prContactFlag) {
		this.prContactFlag = prContactFlag;
	}

	/**
	 * 
	 * @generated
	 */
	public Character getPrContactFlag() {
		return this.prContactFlag;
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
		if (createDt == null) {
			Date x=null;
			this.createDt = x;
			
		} else {
			this.createDt = (Date) createDt.clone();
		}
	}

	/**
	 * 
	 * @generated
	 */
	public Date getCreateDt() {
		if (this.createDt == null) {
			return null;
			
		} else {
			return (Date) this.createDt.clone();
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
		if (updateDt == null) {
			Date x=null;
			this.updateDt = x;
		} else {
			
			this.updateDt = (Date) updateDt.clone();
		}
	}

	/**
	 * 
	 * @generated
	 */
	public Date getUpdateDt() {
		if (this.updateDt == null) {
			return null;
		} else {
			
			return (Date) this.updateDt.clone();
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

			this.tCustContactId.setCustId(this.tCust.getCustId());

		}

	}

	/**
	 * 
	 * @generated
	 */
	public TContactType getTContactType() {
		return this.tContactType;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTContactType(final TContactType tContactType) {
		this.tContactType = tContactType;

	}

	/**
	 * 
	 * @generated
	 */
	/*public TAddrType getTAddrType() {
		return this.tAddrType;
	}

	*//**
	 * 
	 * @generated
	 *//*
	public void setTAddrType(final TAddrType tAddrType) {
		this.tAddrType = tAddrType;

	}*/

	/**
	 * 
	 * @generated
	 */

	public void setContactExtn(final String contactExtn) {
		this.contactExtn = contactExtn;
	}

	/**
	 * 
	 * @generated
	 */
	public String getContactExtn() {
		return this.contactExtn;
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TCustContact that) {
		setTCustContactId(that.getTCustContactId());
		setContactDetail(that.getContactDetail());
		setPrContactFlag(that.getPrContactFlag());
		setActiveFlag(that.getActiveFlag());
		setCreatedBy(that.getCreatedBy());
		setCreateDt(that.getCreateDt());
		setUpdatedBy(that.getUpdatedBy());
		setUpdateDt(that.getUpdateDt());
		setTenantId(that.getTenantId());
		setContactExtn(that.getContactExtn());
	}

	/**
	 * @generated
	 * 
	 */
	@Override
	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = prime * result + ((tCustContactId == null) ? 0 : tCustContactId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("tCustContactId=[").append(tCustContactId).append("] ");
		buffer.append("contactDetail=[").append(contactDetail).append("] ");
		buffer.append("prContactFlag=[").append(prContactFlag).append("] ");
		buffer.append("activeFlag=[").append(activeFlag).append("] ");
		buffer.append("createdBy=[").append(createdBy).append("] ");
		buffer.append("createDt=[").append(createDt).append("] ");
		buffer.append("updatedBy=[").append(updatedBy).append("] ");
		buffer.append("updateDt=[").append(updateDt).append("] ");
		buffer.append("tenantId=[").append(tenantId).append("] ");
		buffer.append("contactExtn=[").append(contactExtn).append("] ");
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
		final TCustContact other = (TCustContact) obj;
		if (tCustContactId == null) {
			if (other.tCustContactId != null) {
				return false;
			}
		} else if (!tCustContactId.equals(other.tCustContactId)) {
			return false;
		}
		return true;
	}
}
