package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
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
import javax.validation.constraints.NotNull;

/** 
 * JPA class representing TUsrAttrPref 
 * The corresponding mapping table is t_usr_attr_pref 
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "FindAllTUsrAttrPrefs", query = "select myTUsrAttrPref from TUsrAttrPref myTUsrAttrPref"),
		@NamedQuery(name = "CountTUsrAttrPrefs", query = "Select Count(c) from TUsrAttrPref c"),
		@NamedQuery(name = "FindTUsrAttrPrefByTAttrGroupMap", query = "select myTUsrAttrPref from TUsrAttrPref myTUsrAttrPref where myTUsrAttrPref.tAttrGroupMap = ?1 "),
		@NamedQuery(name = "CountTUsrAttrPrefsByTAttrGroupMap", query = "select Count(myTUsrAttrPref) from TUsrAttrPref myTUsrAttrPref where myTUsrAttrPref.tAttrGroupMap = ?1 "),
		@NamedQuery(name = "FindTUsrAttrPrefByTUsrPref", query = "select myTUsrAttrPref from TUsrAttrPref myTUsrAttrPref where myTUsrAttrPref.tUsrPref = ?1 "),
		@NamedQuery(name = "CountTUsrAttrPrefsByTUsrPref", query = "select Count(myTUsrAttrPref) from TUsrAttrPref myTUsrAttrPref where myTUsrAttrPref.tUsrPref = ?1 "),
		@NamedQuery(name = "FindTUsrAttrPrefsByTUsrPref", query = "select myTUsrAttrPref.tAttrGroupMap.tAttrGroupMapId.attrId from TUsrAttrPref myTUsrAttrPref " +
				" where  myTUsrAttrPref.tUsrPref.tUsrPrefId.featureId = ?1 AND myTUsrAttrPref.tUsrPref.tUsrPrefId.usrId = ?2 AND myTUsrAttrPref.tUsrPref.tUsrPrefId.roleId = ?3 " +
				" AND myTUsrAttrPref.activeFlag = ?4 AND myTUsrAttrPref.tenantId = ?5 ")
		})
@Table(name = "t_usr_attr_pref", uniqueConstraints = @UniqueConstraint(columnNames = { "pref_id" }))
public class TUsrAttrPref implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "pref_id", nullable = false, length = 255)
	private Integer prefId;

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

	@ManyToOne
	@JoinColumns({
			@JoinColumn(name = "attr_group_id", referencedColumnName = "attr_group_id", nullable = false, insertable = true, updatable = true),
			@JoinColumn(name = "attr_id", referencedColumnName = "attr_id", nullable = false, insertable = true, updatable = true) })
	@Valid
	private TAttrGroupMap tAttrGroupMap;

	@ManyToOne
	@JoinColumns({
			@JoinColumn(name = "feature_id", referencedColumnName = "feature_id", nullable = false, insertable = true, updatable = true),
			@JoinColumn(name = "usr_id", referencedColumnName = "usr_id", nullable = false, insertable = true, updatable = true),
			@JoinColumn(name = "role_id", referencedColumnName = "role_id", nullable = false, insertable = true, updatable = true) })
	@Valid
	private TUsrPref tUsrPref;

	/**
	 *
	 * @generated
	 */
	public TUsrAttrPref() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setPrefId(final Integer prefId) {
		this.prefId = prefId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getPrefId() {
		return this.prefId;
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
	public TAttrGroupMap getTAttrGroupMap() {
		return this.tAttrGroupMap;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTAttrGroupMap(final TAttrGroupMap tAttrGroupMap) {
		this.tAttrGroupMap = tAttrGroupMap;

	}

	/**
	 * 
	 * @generated
	 */
	public TUsrPref getTUsrPref() {
		return this.tUsrPref;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTUsrPref(final TUsrPref tUsrPref) {
		this.tUsrPref = tUsrPref;

	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TUsrAttrPref that) {
		setPrefId(that.getPrefId());
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
		result = prime * result + ((prefId == null) ? 0 : prefId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("prefId=[").append(prefId).append("] ");
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
		final TUsrAttrPref other = (TUsrAttrPref) obj;
		if (prefId == null) {
			if (other.prefId != null) {
				return false;
			}
		} else if (!prefId.equals(other.prefId)) {
			return false;
		}
		return true;
	}
}
