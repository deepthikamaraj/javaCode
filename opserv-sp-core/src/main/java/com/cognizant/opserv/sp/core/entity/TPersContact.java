package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.Length;
import org.jasypt.hibernate4.type.EncryptedStringType;

/** 
 * JPA class representing TPersContact 
 * The corresponding mapping table is t_pers_contact 
 */

@Entity
@Audited
@NamedQueries({
		@NamedQuery(name = "FindAllTPersContacts", query = "select myTPersContact from TPersContact myTPersContact"),
		@NamedQuery(name = "CountTPersContacts", query = "Select Count(c) from TPersContact c"),
		@NamedQuery(name = "FindTPersContactByTPers", query = "select myTPersContact from TPersContact myTPersContact where myTPersContact.staffId = ?1 "),
		@NamedQuery(name = "CountTPersContactsByTPers", query = "select Count(*) from TPersContact myTPersContact where myTPersContact.staffId = ?1 "),
		@NamedQuery(name = "findMaxTPersContact", query = "select MAX(myTPersContact.persContactId) from TPersContact myTPersContact"),
		@NamedQuery(name = "FindTPersContactByStaffId", query = "select myTPersContact from TPersContact myTPersContact where myTPersContact.staffId = ?1 "),
		@NamedQuery(name = "FindTPersContactByStaffAndType", query = "select myTPersContact from TPersContact myTPersContact where myTPersContact.contactTypeId = ?1 AND myTPersContact.staffId = ?2 AND myTpersContact.prContactFlag = ?3 AND myTpersContact.activeFlag =?4 AND myTpersContact.tenantId =?5"),
		@NamedQuery(name = "FindTPersContactByTPersContactType", query = "select myTPersContact from TPersContact myTPersContact where myTPersContact.contactTypeId = ?1 "),
		@NamedQuery(name = "CountTPersContactsByTPersContactType", query = "select Count(*) from TPersContact myTPersContact where myTPersContact.contactTypeId = ?1 "),
		@NamedQuery(name = "FindTPersContactDetailsByStaffId", query = "select myTPersContact.contactTypeId,myTPersContact.contactDetail,myTPersContact.prContactFlag,myTPersContact.persContactId from TPersContact myTPersContact where myTPersContact.staffId = ?1"),
		@NamedQuery(name = "getTPersContactsByStaffIds",query = "select myTPersContact from TPersContact myTPersContact where myTPersContact.staffId IN ?1 AND myTPersContact.tenantId =?2 AND myTPersContact.activeFlag ='Y'"),
		@NamedQuery(name = "FindTPersEmailByStaffId", query = "select myTPersContact.contactDetail from  TPersContact myTPersContact where myTPersContact.staffId IN ?1 AND myTPersContact.activeFlag ='Y' AND myTPersContact.contactTypeId = ?2 "),
})
@TypeDef (name="encryptedString", typeClass= EncryptedStringType.class,
parameters= {
@Parameter(name="encryptorRegisteredName",  value="hibernateStringEncryptor")
})
@Table(name = "t_pers_contact",uniqueConstraints = @UniqueConstraint(columnNames = { "pers_contact_id" }))
public class TPersContact implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	/*@EmbeddedId
	@Valid
	private TPersContactId tPersContactId;*/
	

	/**
	 * 
	 * @generated
	 */
	@NotNull
	@Column(name = "contact_type_id", nullable = false, length = 255)
	private Integer contactTypeId;

	/**
	 * 
	 * @generated
	 */
	@NotNull
	@Length(max = 500)
	@Column(name = "contact_detail", nullable = false, length = 200)
	@Type(type="encryptedString")
	private String contactDetail;

	/**
	 * 
	 * @generated
	 */
	
	@Column(name = "created_by", nullable = false,  updatable = false, length = 255)
	private Integer createdBy;

	/**
	 * 
	 * @generated
	 */
	
	@Column(name = "create_dt", nullable = false, length = 19, updatable = false)
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
	@NotNull
    @Column(name = "staff_id",insertable = true, updatable = true, nullable = false, length = 255)
    private Integer staffId;


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "pers_contact_id", nullable = false, length = 255)
	private Integer persContactId;
	
	@Column(name = "update_dt", nullable = true, length = 19)
	private Date updateDt;
	
	public Character getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(Character activeFlag) {
		this.activeFlag = activeFlag;
	}

	public String getPrContactFlag() {
		return prContactFlag;
	}

	public void setPrContactFlag(String prContactFlag) {
		this.prContactFlag = prContactFlag;
	}

	@Column(name = "active_flag", nullable = false, length = 1)
	private Character activeFlag;

	
	@Length(max = 20)
	@Column(name = "pr_contact_flag", nullable = true, length = 20)
	private String prContactFlag;
	/**
	 * 
	 * @generated
	 */
	@NotNull
	@Column(name = "tenant_id", nullable = false, length = 255, updatable = false)
	private Short tenantId;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 20)
	@Column(name = "contact_extn", nullable = true, length = 20)
	private String contactExtn;
	
	/*@ManyToOne
	@JoinColumn(name = "staff_id", referencedColumnName = "staff_id", nullable = false, insertable = false, updatable = false)
	@Valid
	@NotAudited
	private TPers tPers;
*/
	/**
	 *
	 * @generated
	 */
	public TPersContact() {
	}

	
	/**
	 * 
	 * @generated
	 */

	public void setContactTypeId(final Integer contactTypeId) {
		this.contactTypeId = contactTypeId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getContactTypeId() {
		return this.contactTypeId;
	}

	/**
	 * 
	 * @generated
	 */
	@Type(type="encryptedString")
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
	/*public TPers getTPers() {
		return this.tPers;
	}*/

	/**
	 * 
	 * @generated
	 */
	/*public void setTPers(final TPers tPers) {
		this.tPers = tPers;
		if (this.tPers != null && this.tPers.getStaffId() != null) {

			this.setStaffId(this.tPers.getStaffId());

		}

	}*/

	public Integer getStaffId() {
		return staffId;
	}

	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}

	public Integer getPersContactId() {
		return persContactId;
	}

	public void setPersContactId(Integer persContactId) {
		this.persContactId = persContactId;
	}

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
	public void copy(final TPersContact that) {
		/*setTPersContactId(that.getTPersContactId());*/
		setContactTypeId(that.getContactTypeId());
		setContactDetail(that.getContactDetail());
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
	

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		//buffer.append("tPersContactId=[").append(tPersContactId).append("] ");
		buffer.append("contactTypeId=[").append(contactTypeId).append("] ");
		buffer.append("contactDetail=[").append(contactDetail).append("] ");
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
	/*@Override
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
		final TPersContact other = (TPersContact) obj;
		if (tPersContactId == null) {
			if (other.tPersContactId != null) {
				return false;
			}
		} else if (!tPersContactId.equals(other.tPersContactId)) {
			return false;
		}
		return true;
	}*/
}
