package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/** 
 * JPA class representing TMtrCategory 
 * The corresponding mapping table is t_mtr_category 
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "FindAllTMtrCategorys", query = "select myTMtrCategory from TMtrCategory myTMtrCategory"),
		@NamedQuery(name = "CountTMtrCategorys", query = "Select Count(c) from TMtrCategory c"),
		@NamedQuery(name = "FindTMtrCategory", query = "Select myTMtrCategory.tMtrCategoryId.mtrCategoryId, myTMtrCategory.mtrCategoryDesc from TMtrCategory myTMtrCategory where myTMtrCategory.tenantId = ?1 AND myTMtrCategory.tMtrCategoryId.localeId = ?2 order by myTMtrCategory.mtrCategoryDesc "),
		@NamedQuery(name = "FindAllTMtrCategorysByIds", query = "Select myTMtrCategory from TMtrCategory myTMtrCategory where myTMtrCategory.tMtrCategoryId.mtrCategoryId = ?1 AND myTMtrCategory.tMtrCategoryId.localeId = ?2"),
		@NamedQuery(name = "FindActiveTMtrCategorys", query = "Select myTMtrCategory from TMtrCategory myTMtrCategory where myTMtrCategory.activeFlag = 'Y' AND myTMtrCategory.tenantId = ?1 ORDER BY myTMtrCategory.mtrCategoryDesc"),
		@NamedQuery(name = "GetTMtrCategorysByLocale", query = "Select myTMtrCategory from TMtrCategory myTMtrCategory where myTMtrCategory.activeFlag = 'Y' AND myTMtrCategory.tenantId = ?1 AND myTMtrCategory.tMtrCategoryId.localeId = ?2 ORDER BY myTMtrCategory.mtrCategoryDesc"),
		@NamedQuery(name = "FindActiveTMtrCategoryByLocaleId", query = "Select myTMtrCategory from TMtrCategory myTMtrCategory where myTMtrCategory.activeFlag = ?1 AND myTMtrCategory.tMtrCategoryId.localeId = ?2 AND myTMtrCategory.tenantId = ?3  ")
		
})
@Table(name = "t_mtr_category", uniqueConstraints = @UniqueConstraint(columnNames = { "mtr_category_id" }))
public class TMtrCategory implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	
	/**
	 * 
	 * @generated
	 */
	@EmbeddedId
	@Valid
	private TMtrCategoryId tMtrCategoryId;
	
	/**
	 * 
	 * @generated
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "mtr_category_id", nullable = false, length = 255)
	private Integer mtrCategoryId;

	/**
	 * 
	 * @generated
	 */
	@NotEmpty
	@Length(max = 75)
	@Column(name = "mtr_category_desc", nullable = false, length = 75)
	private String mtrCategoryDesc;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 15)
	@Column(name = "mtr_category_cd", nullable = true, length = 15)
	private String mtrCategoryCd;

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

/*	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tMtrCategory")
	private Set<TMtr> tMtrss;*/

	/**
	 *
	 * @generated
	 */
	public TMtrCategory() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setMtrCategoryId(final Integer mtrCategoryId) {
		this.mtrCategoryId = mtrCategoryId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getMtrCategoryId() {
		return this.mtrCategoryId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setMtrCategoryDesc(final String mtrCategoryDesc) {
		this.mtrCategoryDesc = mtrCategoryDesc;
	}

	/**
	 * 
	 * @generated
	 */
	public String getMtrCategoryDesc() {
		return this.mtrCategoryDesc;
	}

	/**
	 * 
	 * @generated
	 */

	public void setMtrCategoryCd(final String mtrCategoryCd) {
		this.mtrCategoryCd = mtrCategoryCd;
	}

	/**
	 * 
	 * @generated
	 */
	public String getMtrCategoryCd() {
		return this.mtrCategoryCd;
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
/*	public Set<TMtr> getTMtrss() {
		return this.tMtrss;
	}

	*//**
	 * 
	 * @generated
	 *//*
	public void setTMtrss(final Set<TMtr> tMtrss) {
		this.tMtrss = tMtrss;
	}*/

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TMtrCategory that) {
		setMtrCategoryId(that.getMtrCategoryId());
		setMtrCategoryDesc(that.getMtrCategoryDesc());
		setMtrCategoryCd(that.getMtrCategoryCd());
		setActiveFlag(that.getActiveFlag());
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
		result = prime * result + ((gettMtrCategoryId() == null) ? 0 : gettMtrCategoryId().hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("mtrCategoryId=[").append(gettMtrCategoryId()).append("] ");
		buffer.append("mtrCategoryDesc=[").append(mtrCategoryDesc).append("] ");
		buffer.append("mtrCategoryCd=[").append(mtrCategoryCd).append("] ");
		buffer.append("activeFlag=[").append(activeFlag).append("] ");
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
		final TMtrCategory other = (TMtrCategory) obj;
		if (gettMtrCategoryId() == null) {
			if (other.gettMtrCategoryId() != null) {
				return false;
			}
		} else if (!gettMtrCategoryId().equals(other.gettMtrCategoryId())) {
			return false;
		}
		return true;
	}
	
	public TMtrCategoryId gettMtrCategoryId() {
		return tMtrCategoryId;
	}


	public void settMtrCategoryId(TMtrCategoryId tMtrCategoryId) {
		this.tMtrCategoryId = tMtrCategoryId;
	}
	
}
