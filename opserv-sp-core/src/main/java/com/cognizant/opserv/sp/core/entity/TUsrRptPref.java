package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/** 
 * JPA class representing TUsrRptPref 
 * The corresponding mapping table is t_usr_rpt_pref 
 */
@Entity
@NamedQueries({
		@NamedQuery(name = "FindAllTUsrRptPrefs", query = "select myTUsrRptPref from TUsrRptPref myTUsrRptPref"),
		@NamedQuery(name = "CountTUsrRptPrefs", query = "Select Count(c) from TUsrRptPref c"),
		@NamedQuery(name = "FindTUsrRptPrefByTUsrRptPref", query = "select myTUsrRptPref from TUsrRptPref myTUsrRptPref where myTUsrRptPref.tUsrRptPref = ?1"),
		@NamedQuery(name = "CountTUsrRptPrefsByTUsrRptPref", query = "select Count(*) from TUsrRptPref myTUsrRptPref where myTUsrRptPref.tUsrRptPref = ?1 "),
		@NamedQuery(name = "FindTUsrRptPrefByTPers", query = "select myTUsrRptPref from TUsrRptPref myTUsrRptPref where myTUsrRptPref.tPers = ?1"),
		@NamedQuery(name = "CountTUsrRptPrefsByTPers", query = "select Count(*) from TUsrRptPref myTUsrRptPref where myTUsrRptPref.tPers = ?1"),
		@NamedQuery(name = "FindTUsrRptPrefByParentId", query = "select myTUsrRptPref from TUsrRptPref myTUsrRptPref where myTUsrRptPref.parentFolderId = ?1 and myTUsrRptPref.tenantId = ?2"),
		@NamedQuery(name = "FindTUsrRptPrefByFolderName", query = "select myTUsrRptPref from TUsrRptPref myTUsrRptPref where myTUsrRptPref.folderName = ?1 and myTUsrRptPref.parentFolderId = ?2 and myTUsrRptPref.staffId = ?3 and myTUsrRptPref.tenantId = ?4"),
		@NamedQuery(name = "FindTUsrRptPrefsByStaffAndRptType", query = "select myTUsrRptPref from TUsrRptPref myTUsrRptPref where myTUsrRptPref.parentFolderId = ?1 and myTUsrRptPref.staffId = ?2 and myTUsrRptPref.tenantId = ?3"),
		@NamedQuery(name = "FetchUserFoldersByRptType", query = "select myTUsrRptPref.prefId, myTUsrRptPref.folderName from TUsrRptPref myTUsrRptPref where" +
				" myTUsrRptPref.parentFolderId = ?1 and myTUsrRptPref.staffId = ?2 and myTUsrRptPref.tenantId = ?3 and myTUsrRptPref.activeFlag = 'Y' "),
		@NamedQuery(name = "CountByFolderNameAndParentFolder", query = "select Count(c) from TUsrRptPref c where c.folderName = ?1 and c.parentFolderId = ?2" +
				" and c.staffId = ?3 and c.tenantId = ?4"),
		@NamedQuery(name = "CountOfSubFolders", query = "select Count(c) from TUsrRptPref c where c.parentFolderId = ?1 and c.tenantId = ?2"),
})
@Table(name = "t_usr_rpt_pref")
public class TUsrRptPref implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 * @generated
	 */
	@Id
	//@NotNull
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "pref_id", nullable = false, length = 255)
	private Integer prefId;

	/**
	 * 
	 * @generated
	 */
	/*@EmbeddedId
	@Valid
	private TUsrRptPrefId tUsrRptPrefId;*/
	
	@NotNull
	@Column(name = "staff_id", nullable = false, length = 255)
	private Integer staffId;
	
	/**
	 * 
	 * @generated
	 */
	@NotEmpty
	@Length(max = 40)
	@Column(name = "folder_name", unique= true, nullable = true, length = 40)
	private String folderName;

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
	@Length(max = 75)
	@Column(name = "folder_desc", nullable = true, length = 75)
	private String folderDesc;

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
	@Column(name = "tenant_id", nullable = true, length = 255)
	private Short tenantId;
	
	/**
	 * 
	 * @generated
	 */	
	@Column(name = "prn_folder_id", nullable = true, length = 255)
	private Integer parentFolderId;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "prn_folder_id", referencedColumnName = "pref_id", nullable = true, insertable = false, updatable = false),
			@JoinColumn(name = "staff_id", referencedColumnName = "staff_id", nullable = true, insertable = false, updatable = false) })
	@Valid
	private TUsrRptPref tUsrRptPref;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "staff_id", referencedColumnName = "staff_id", nullable = false, insertable = false, updatable = false)
	@Valid
	private TPers tPers;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tUsrRptPref")
	private Set<TUsrRptPref> tUsrRptPrefss;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tUsrRptPref")
	private Set<TRptTUsrRptPref> tRptTUsrRptPrefss;

	public Integer getPrefId() {
		return prefId;
	}

	public void setPrefId(Integer prefId) {
		this.prefId = prefId;
	}

	public Integer getStaffId() {
		return staffId;
	}

	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setFolderName(final String folderName) {
		this.folderName = folderName;
	}

	/**
	 * 
	 * @generated
	 */
	public String getFolderName() {
		return this.folderName;
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

	public void setFolderDesc(final String folderDesc) {
		this.folderDesc = folderDesc;
	}

	/**
	 * 
	 * @generated
	 */
	public String getFolderDesc() {
		return this.folderDesc;
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
	
	public Integer getParentFolderId() {
		return parentFolderId;
	}
	
	/**
	 * 
	 * @generated
	 */
	
	public void setParentFolderId(Integer parentFolderId) {
		this.parentFolderId = parentFolderId;
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
	public TUsrRptPref getTUsrRptPref() {
		return this.tUsrRptPref;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTUsrRptPref(final TUsrRptPref tUsrRptPref) {
		this.tUsrRptPref = tUsrRptPref;		
	}

	/**
	 * 
	 * @generated
	 */
	public TPers getTPers() {
		return this.tPers;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTPers(final TPers tPers) {
		this.tPers = tPers;
		/*if (this.tPers != null && this.tPers.getStaffId() != null) {
			this.tUsrRptPrefId.setStaffId(this.tPers.getStaffId());
		}*/
	}

	/**
	 * 
	 * @generated
	 */
	public Set<TUsrRptPref> getTUsrRptPrefss() {
		return this.tUsrRptPrefss;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTUsrRptPrefss(final Set<TUsrRptPref> tUsrRptPrefss) {
		this.tUsrRptPrefss = tUsrRptPrefss;
	}

	/**
	 * 
	 * @generated
	 */
	public Set<TRptTUsrRptPref> getTRptTUsrRptPrefss() {
		return this.tRptTUsrRptPrefss;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTRptTUsrRptPrefss(final Set<TRptTUsrRptPref> tRptTUsrRptPrefss) {
		this.tRptTUsrRptPrefss = tRptTUsrRptPrefss;
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TUsrRptPref that) {
		//setTUsrRptPrefId(that.getTUsrRptPrefId());
		setFolderName(that.getFolderName());
		setActiveFlag(that.getActiveFlag());
		setFolderDesc(that.getFolderDesc());
		setCreatedBy(that.getCreatedBy());
		setCreateDt(that.getCreateDt());
		setUpdatedBy(that.getUpdatedBy());
		setUpdateDate(that.getUpdateDate());
		setTenantId(that.getTenantId());
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		//buffer.append("tUsrRptPrefId=[").append(tUsrRptPrefId).append("] ");
		buffer.append("folderName=[").append(folderName).append("] ");
		buffer.append("activeFlag=[").append(activeFlag).append("] ");
		buffer.append("folderDesc=[").append(folderDesc).append("] ");
		buffer.append("createdBy=[").append(createdBy).append("] ");
		buffer.append("createDt=[").append(createDt).append("] ");
		buffer.append("updatedBy=[").append(updatedBy).append("] ");
		buffer.append("updateDate=[").append(updateDate).append("] ");
		buffer.append("tenantId=[").append(tenantId).append("] ");

		return buffer.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((prefId == null) ? 0 : prefId.hashCode());
		result = prime * result + ((staffId == null) ? 0 : staffId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TUsrRptPref other = (TUsrRptPref) obj;
		if (prefId == null) {
			if (other.prefId != null)
				return false;
		} else if (!prefId.equals(other.prefId))
			return false;
		if (staffId == null) {
			if (other.staffId != null)
				return false;
		} else if (!staffId.equals(other.staffId))
			return false;
		return true;
	}

}
