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
 * JPA class representing TAlgmntGeo 
 * The corresponding mapping table is t_algmnt_geo 
 */

@Entity
@NamedQueries({
	@NamedQuery(name = "FindAllTAlgmntGeos", query = "select myTAlgmntGeo from TAlgmntGeo myTAlgmntGeo"),
	@NamedQuery(name = "CountTAlgmntGeos", query = "Select Count(c) from TAlgmntGeo c"),
	@NamedQuery(name = "FindTAlgmntGeoByTAlgmntGeo", query = "select myTAlgmntGeo from TAlgmntGeo myTAlgmntGeo where myTAlgmntGeo.tAlgmntGeo = ?1 "),
	@NamedQuery(name = "CountTAlgmntGeosByTAlgmntGeo", query = "select Count(myTAlgmntGeo) from TAlgmntGeo myTAlgmntGeo where myTAlgmntGeo.tAlgmntGeo = ?1 "),
	@NamedQuery(name = "FindTAlgmntGeoByTAlgmntGeoHier", query = "select myTAlgmntGeo from TAlgmntGeo myTAlgmntGeo where myTAlgmntGeo.tAlgmntGeoHier = ?1 "),
	@NamedQuery(name = "CountTAlgmntGeosByTAlgmntGeoHier", query = "select Count(myTAlgmntGeo) from TAlgmntGeo myTAlgmntGeo where myTAlgmntGeo.tAlgmntGeoHier = ?1 "),
	@NamedQuery(name = "IsAlignmentGeoHierIdsAreMapped", query = "select count(myTAlgmntGeo.tAlgmntGeoHier.geoHierId) from TAlgmntGeo myTAlgmntGeo where myTAlgmntGeo.tAlgmntGeoHier.geoHierId in (?1) and myTAlgmntGeo.tenantId=?2 and myTAlgmntGeo.activeFlag=?3"),
	@NamedQuery(name = "GetAlignmentGeoHierIdsByAlBuSt", query = "select myTAlgmntGeo.tAlgmntGeoHier.geoHierId from TAlgmntGeo myTAlgmntGeo where myTAlgmntGeo.algmntId=?1 and myTAlgmntGeo.bussUnitId=?2 and myTAlgmntGeo.salesTeamId=?3 and myTAlgmntGeo.tenantId=?4 and myTAlgmntGeo.activeFlag=?5"),
	@NamedQuery(name = "GetAllAlignmentGeoHiers", query = "select myTAlgmntGeo.algmntId,myTAlgmntGeo.bussUnitId,myTAlgmntGeo.salesTeamId,myTAlgmntGeo.geoPosId,myTAlgmntGeo.tAlgmntGeoHier.geoHierId,myTAlgmntGeo.posName,myTAlgmntGeo.posCd,pAlgmntGeo.geoPosId,pAlgmntGeo.tAlgmntGeoHier.geoHierId,myTAlgmntGeo.effStartDt,myTAlgmntGeo.effEndDt from TAlgmntGeo myTAlgmntGeo left join myTAlgmntGeo.tAlgmntGeo pAlgmntGeo where myTAlgmntGeo.tenantId=?1 and myTAlgmntGeo.activeFlag=?2")
})
@Table(name = "t_algmnt_geo", uniqueConstraints = @UniqueConstraint(columnNames = { "geo_pos_id" }))
public class TAlgmntGeo implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "geo_pos_id", nullable = false, length = 255)
	private Long geoPosId;

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
	@NotEmpty
	@Length(max = 75)
	@Column(name = "pos_name", nullable = false, length = 75)
	private String posName;

	/**
	 * 
	 * @generated
	 */
	@NotEmpty
	@Length(max = 20)
	@Column(name = "pos_cd", nullable = false, length = 20)
	private String posCd;

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

	/**
	 * 
	 * @generated
	 */
	@Column(name = "eff_start_dt", nullable = true, length = 10)
	private Date effStartDt;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "eff_end_dt", nullable = true, length = 10)
	private Date effEndDt;

	@ManyToOne
	@JoinColumn(name = "prn_geo_pos_id", referencedColumnName = "geo_pos_id", nullable = true, insertable = true, updatable = true)
	@Valid
	private TAlgmntGeo tAlgmntGeo;

	@ManyToOne
	@JoinColumn(name = "geo_hier_id", referencedColumnName = "geo_hier_id", nullable = false, insertable = true, updatable = true)
	@Valid
	private TAlgmntGeoHier tAlgmntGeoHier;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tAlgmntGeo")
	private Set<TAlgmntGeo> tAlgmntGeoss;

	/**
	 *
	 * @generated
	 */
	public TAlgmntGeo() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setGeoPosId(final Long geoPosId) {
		this.geoPosId = geoPosId;
	}

	/**
	 * 
	 * @generated
	 */
	public Long getGeoPosId() {
		return this.geoPosId;
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

	public void setPosName(final String posName) {
		this.posName = posName;
	}

	/**
	 * 
	 * @generated
	 */
	public String getPosName() {
		return this.posName;
	}

	/**
	 * 
	 * @generated
	 */

	public void setPosCd(final String posCd) {
		this.posCd = posCd;
	}

	/**
	 * 
	 * @generated
	 */
	public String getPosCd() {
		return this.posCd;
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
	public TAlgmntGeo getTAlgmntGeo() {
		return this.tAlgmntGeo;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTAlgmntGeo(final TAlgmntGeo tAlgmntGeo) {
		this.tAlgmntGeo = tAlgmntGeo;

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
	public Set<TAlgmntGeo> getTAlgmntGeoss() {
		return this.tAlgmntGeoss;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTAlgmntGeoss(final Set<TAlgmntGeo> tAlgmntGeoss) {
		this.tAlgmntGeoss = tAlgmntGeoss;
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TAlgmntGeo that) {
		setGeoPosId(that.getGeoPosId());
		setAlgmntId(that.getAlgmntId());
		setBussUnitId(that.getBussUnitId());
		setSalesTeamId(that.getSalesTeamId());
		setPosName(that.getPosName());
		setPosCd(that.getPosCd());
		setActiveFlag(that.getActiveFlag());
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
		result = prime * result + ((geoPosId == null) ? 0 : geoPosId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("geoPosId=[").append(geoPosId).append("] ");
		buffer.append("algmntId=[").append(algmntId).append("] ");
		buffer.append("bussUnitId=[").append(bussUnitId).append("] ");
		buffer.append("salesTeamId=[").append(salesTeamId).append("] ");
		buffer.append("posName=[").append(posName).append("] ");
		buffer.append("posCd=[").append(posCd).append("] ");
		buffer.append("activeFlag=[").append(activeFlag).append("] ");
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
		final TAlgmntGeo other = (TAlgmntGeo) obj;
		if (geoPosId == null) {
			if (other.geoPosId != null) {
				return false;
			}
		} else if (!geoPosId.equals(other.geoPosId)) {
			return false;
		}
		return true;
	}
}
