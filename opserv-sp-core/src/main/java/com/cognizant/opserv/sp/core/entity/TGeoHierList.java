package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/** 
 * JPA class representing TGeoHierList 
 * The corresponding mapping table is t_geo_hier_list 
 */

@Entity
@NamedQueries({
	@NamedQuery(name = "FindAllTGeoHierLists", query = "select myTGeoHierList from TGeoHierList myTGeoHierList"),
	@NamedQuery(name = "CountTGeoHierLists", query = "Select Count(c) from TGeoHierList c"),
	@NamedQuery(name = "FindTGeoHierListByTGeoHierList", query = "select myTGeoHierList from TGeoHierList myTGeoHierList where myTGeoHierList.tGeoHierList = ?1 "),
	@NamedQuery(name = "CountTGeoHierListsByTGeoHierList", query = "select Count(myTGeoHierList) from TGeoHierList myTGeoHierList where myTGeoHierList.tGeoHierList = ?1 "),
	@NamedQuery(name = "GetGeoHierLevels", query = "select myTGeoHierList.geoHierId,myTGeoHierList.hierName from TGeoHierList myTGeoHierList where myTGeoHierList.tenantId = ?1 and myTGeoHierList.activeFlag = ?2 "),
	@NamedQuery(name = "IsLevelNameExists", query = "select count(myTGeoHierList.geoHierId) from TGeoHierList myTGeoHierList where myTGeoHierList.hierName=?1 and myTGeoHierList.tenantId = ?2 and myTGeoHierList.activeFlag = ?3 ")
	})
@Table(name = "t_geo_hier_list", uniqueConstraints = @UniqueConstraint(columnNames = { "geo_hier_id" }))
public class TGeoHierList implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "geo_hier_id", nullable = false, length = 255)
	private Integer geoHierId;

	/**
	 * 
	 * @generated
	 */
	@NotEmpty
	@Length(max = 50)
	@Column(name = "hier_name", nullable = false, length = 50)
	private String hierName;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 75)
	@Column(name = "hier_desc", nullable = true, length = 75)
	private String hierDesc;

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
	@JoinColumn(name = "prn_geo_hier_id", referencedColumnName = "geo_hier_id", nullable = true, insertable = true, updatable = true)
	@Valid
	private TGeoHierList tGeoHierList;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tGeoHierList")
	private Set<TGeoHierList> tGeoHierListss;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tGeoHierList")
	private Set<TAlgmntGeoHier> tAlgmntGeoHierss;

	/**
	 *
	 * @generated
	 */
	public TGeoHierList() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setGeoHierId(final Integer geoHierId) {
		this.geoHierId = geoHierId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getGeoHierId() {
		return this.geoHierId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setHierName(final String hierName) {
		this.hierName = hierName;
	}

	/**
	 * 
	 * @generated
	 */
	public String getHierName() {
		return this.hierName;
	}

	/**
	 * 
	 * @generated
	 */

	public void setHierDesc(final String hierDesc) {
		this.hierDesc = hierDesc;
	}

	/**
	 * 
	 * @generated
	 */
	public String getHierDesc() {
		return this.hierDesc;
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
	public TGeoHierList getTGeoHierList() {
		return this.tGeoHierList;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTGeoHierList(final TGeoHierList tGeoHierList) {
		this.tGeoHierList = tGeoHierList;

	}

	/**
	 * 
	 * @generated
	 */
	public Set<TGeoHierList> getTGeoHierListss() {
		return this.tGeoHierListss;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTGeoHierListss(final Set<TGeoHierList> tGeoHierListss) {
		this.tGeoHierListss = tGeoHierListss;
	}

	/**
	 * 
	 * @generated
	 */
	public Set<TAlgmntGeoHier> getTAlgmntGeoHierss() {
		return this.tAlgmntGeoHierss;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTAlgmntGeoHierss(final Set<TAlgmntGeoHier> tAlgmntGeoHierss) {
		this.tAlgmntGeoHierss = tAlgmntGeoHierss;
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TGeoHierList that) {
		setGeoHierId(that.getGeoHierId());
		setHierName(that.getHierName());
		setHierDesc(that.getHierDesc());
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
		result = prime * result + ((geoHierId == null) ? 0 : geoHierId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("geoHierId=[").append(geoHierId).append("] ");
		buffer.append("hierName=[").append(hierName).append("] ");
		buffer.append("hierDesc=[").append(hierDesc).append("] ");
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
		final TGeoHierList other = (TGeoHierList) obj;
		if (geoHierId == null) {
			if (other.geoHierId != null) {
				return false;
			}
		} else if (!geoHierId.equals(other.geoHierId)) {
			return false;
		}
		return true;
	}
}
