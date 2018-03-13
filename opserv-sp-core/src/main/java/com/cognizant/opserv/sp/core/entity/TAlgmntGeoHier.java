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
 * JPA class representing TAlgmntGeoHier 
 * The corresponding mapping table is t_algmnt_geo_hier 
 */

@Entity
@NamedQueries({
	@NamedQuery(name = "FindAllTAlgmntGeoHiers", query = "select myTAlgmntGeoHier from TAlgmntGeoHier myTAlgmntGeoHier"),
	@NamedQuery(name = "CountTAlgmntGeoHiers", query = "Select Count(c) from TAlgmntGeoHier c"),
	@NamedQuery(name = "FindTAlgmntGeoHierByTAlgmntGeoHier", query = "select myTAlgmntGeoHier from TAlgmntGeoHier myTAlgmntGeoHier where myTAlgmntGeoHier.tAlgmntGeoHier = ?1 "),
	@NamedQuery(name = "CountTAlgmntGeoHiersByTAlgmntGeoHier", query = "select Count(myTAlgmntGeoHier) from TAlgmntGeoHier myTAlgmntGeoHier where myTAlgmntGeoHier.tAlgmntGeoHier = ?1 "),
	@NamedQuery(name = "FindTAlgmntGeoHierByTGeoHierList", query = "select myTAlgmntGeoHier from TAlgmntGeoHier myTAlgmntGeoHier where myTAlgmntGeoHier.tGeoHierList = ?1 "),
	@NamedQuery(name = "CountTAlgmntGeoHiersByTGeoHierList", query = "select Count(myTAlgmntGeoHier) from TAlgmntGeoHier myTAlgmntGeoHier where myTAlgmntGeoHier.tGeoHierList = ?1 "),
	@NamedQuery(name = "IsGeoLevelMappedToAlignments", query = "select count(myTAlgmntGeoHier.geoHierId) from TAlgmntGeoHier myTAlgmntGeoHier where myTAlgmntGeoHier.tGeoHierList.geoHierId = ?1 and myTAlgmntGeoHier.tenantId = ?2 and myTAlgmntGeoHier.activeFlag = ?3  "),
	@NamedQuery(name = "GetSelectedAlignmentLevels", query = "select myTAlgmntGeoHier.geoHierId,myTAlgmntGeoHier.hierName,myTAlgmntGeoHier.tGeoHierList.geoHierId from TAlgmntGeoHier myTAlgmntGeoHier where myTAlgmntGeoHier.algmntId=?1 and myTAlgmntGeoHier.bussUnitId=?2 and myTAlgmntGeoHier.salesTeamId=?3 and myTAlgmntGeoHier.tenantId = ?4 and myTAlgmntGeoHier.activeFlag = ?5 order by myTAlgmntGeoHier.hierLevel"),
	@NamedQuery(name = "GetChildLevelByLevelId", query = "select myTAlgmntGeoHier.geoHierId from TAlgmntGeoHier myTAlgmntGeoHier where myTAlgmntGeoHier.tAlgmntGeoHier.geoHierId=?1 and myTAlgmntGeoHier.tenantId = ?2 and myTAlgmntGeoHier.activeFlag = ?3"),
	@NamedQuery(name = "GetAllAlignmentLevels", query = "select myTAlgmntGeoHier.algmntId,myTAlgmntGeoHier.bussUnitId,myTAlgmntGeoHier.salesTeamId,myTAlgmntGeoHier.geoHierId,myTAlgmntGeoHier.tAlgmntGeoHier.geoHierId,myTAlgmntGeoHier.hierName from TAlgmntGeoHier myTAlgmntGeoHier where myTAlgmntGeoHier.tenantId = ?1 and myTAlgmntGeoHier.activeFlag = ?2")
})
@Table(name = "t_algmnt_geo_hier", uniqueConstraints = @UniqueConstraint(columnNames = { "geo_hier_id" }))
public class TAlgmntGeoHier implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "geo_hier_id", nullable = false, length = 255)
	private Long geoHierId;

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
	@NotNull
	@Column(name = "active_flag", nullable = false, length = 1)
	private Character activeFlag;

	/**
	 * 
	 * @generated
	 */
	@NotNull
	@Column(name = "algmnt_id", nullable = false, length = 255)
	private Long algmntId;

	/**
	 * 
	 * @generated
	 */
	@NotNull
	@Column(name = "buss_unit_id", nullable = false, length = 255)
	private Long bussUnitId;

	/**
	 * 
	 * @generated
	 */
	@NotNull
	@Column(name = "sales_team_id", nullable = false, length = 255)
	private Long salesTeamId;

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

	/**
	 * 
	 * @generated
	 */
	@NotNull
	@Column(name = "eff_start_dt", nullable = false, length = 10)
	private Date effStartDt;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "eff_end_dt", nullable = true, length = 10)
	private Date effEndDt;

	@Column(name = "hier_level", nullable = false, length = 255)
	private Short hierLevel;
	
	@ManyToOne
	@JoinColumn(name = "prn_geo_hier_id", referencedColumnName = "geo_hier_id", nullable = true, insertable = true, updatable = true)
	@Valid
	private TAlgmntGeoHier tAlgmntGeoHier;

	@ManyToOne
	@JoinColumn(name = "hier_id", referencedColumnName = "geo_hier_id", nullable = false, insertable = true, updatable = true)
	@Valid
	private TGeoHierList tGeoHierList;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tAlgmntGeoHier")
	private Set<TAlgmntGeoHier> tAlgmntGeoHierss;

	/**
	 *
	 * @generated
	 */
	public TAlgmntGeoHier() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setGeoHierId(final Long geoHierId) {
		this.geoHierId = geoHierId;
	}

	/**
	 * 
	 * @generated
	 */
	public Long getGeoHierId() {
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

	public void setAlgmntId(final Long algmntId) {
		this.algmntId = algmntId;
	}

	/**
	 * 
	 * @generated
	 */
	public Long getAlgmntId() {
		return this.algmntId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setBussUnitId(final Long bussUnitId) {
		this.bussUnitId = bussUnitId;
	}

	/**
	 * 
	 * @generated
	 */
	public Long getBussUnitId() {
		return this.bussUnitId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setSalesTeamId(final Long salesTeamId) {
		this.salesTeamId = salesTeamId;
	}

	/**
	 * 
	 * @generated
	 */
	public Long getSalesTeamId() {
		return this.salesTeamId;
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

	public void setEffStartDt(final Date effStartDt) {
		if (effStartDt != null) {
			this.effStartDt = (Date) effStartDt.clone();
		} else {
			this.effStartDt = null;
		}
	}

	/**
	 * 
	 * @generated
	 */
	public Date getEffStartDt() {
		if (this.effStartDt != null) {
			return (Date) this.effStartDt.clone();
		} else {
			return null;
		}
	}

	/**
	 * 
	 * @generated
	 */

	public void setEffEndDt(final Date effEndDt) {
		if (effEndDt != null) {
			this.effEndDt = (Date) effEndDt.clone();
		} else {
			this.effEndDt = null;
		}
	}

	/**
	 * 
	 * @generated
	 */
	public Date getEffEndDt() {
		if (this.effEndDt != null) {
			return (Date) this.effEndDt.clone();
		} else {
			return null;
		}
	}

	/**
	 * 
	 * @generated
	 */
	public TAlgmntGeoHier getTAlgmntGeoHier() {
		return this.tAlgmntGeoHier;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTAlgmntGeoHier(final TAlgmntGeoHier tAlgmntGeoHier) {
		this.tAlgmntGeoHier = tAlgmntGeoHier;

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

	public Short getHierLevel() {
		return hierLevel;
	}

	public void setHierLevel(Short hierLevel) {
		this.hierLevel = hierLevel;
	}
	
	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TAlgmntGeoHier that) {
		setGeoHierId(that.getGeoHierId());
		setHierName(that.getHierName());
		setActiveFlag(that.getActiveFlag());
		setAlgmntId(that.getAlgmntId());
		setBussUnitId(that.getBussUnitId());
		setSalesTeamId(that.getSalesTeamId());
		setCreatedBy(that.getCreatedBy());
		setCreateDt(that.getCreateDt());
		setUpdatedBy(that.getUpdatedBy());
		setUpdateDt(that.getUpdateDt());
		setTenantId(that.getTenantId());
		setEffStartDt(that.getEffStartDt());
		setEffEndDt(that.getEffEndDt());
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
		buffer.append("activeFlag=[").append(activeFlag).append("] ");
		buffer.append("algmntId=[").append(algmntId).append("] ");
		buffer.append("bussUnitId=[").append(bussUnitId).append("] ");
		buffer.append("salesTeamId=[").append(salesTeamId).append("] ");
		buffer.append("createdBy=[").append(createdBy).append("] ");
		buffer.append("createDt=[").append(createDt).append("] ");
		buffer.append("updatedBy=[").append(updatedBy).append("] ");
		buffer.append("updateDt=[").append(updateDt).append("] ");
		buffer.append("tenantId=[").append(tenantId).append("] ");
		buffer.append("effStartDt=[").append(effStartDt).append("] ");
		buffer.append("effEndDt=[").append(effEndDt).append("] ");

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
		final TAlgmntGeoHier other = (TAlgmntGeoHier) obj;
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
