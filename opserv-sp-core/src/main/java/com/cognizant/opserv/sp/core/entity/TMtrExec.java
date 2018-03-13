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
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/** 
 * JPA class representing TMtrExec 
 * The corresponding mapping table is t_mtr_exec 
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "FindAllTMtrExecs", query = "select myTMtrExec from TMtrExec myTMtrExec"),
		@NamedQuery(name = "CountTMtrExecs", query = "Select Count(c) from TMtrExec c"),
		@NamedQuery(name = "FindTMtrExecByTExecutionStatus", query = "select myTMtrExec from TMtrExec myTMtrExec where myTMtrExec.tExecutionStatus = ?1 "),
		@NamedQuery(name = "CountTMtrExecsByTExecutionStatus", query = "select Count(*) from TMtrExec myTMtrExec where myTMtrExec.tExecutionStatus = ?1 "),
		@NamedQuery(name = "FindTMtrExecByTMtrExecConfig", query = "select myTMtrExec from TMtrExec myTMtrExec where myTMtrExec.tMtrExecConfig = ?1 "),
		@NamedQuery(name = "CountTMtrExecsByTMtrExecConfig", query = "select Count(*) from TMtrExec myTMtrExec where myTMtrExec.tMtrExecConfig = ?1 "),
		@NamedQuery(name = "FindTMtrExecByTSalesPos", query = "select myTMtrExec from TMtrExec myTMtrExec where myTMtrExec.tSalesPos = ?1 "),
		@NamedQuery(name = "CountTMtrExecsByTSalesPos", query = "select Count(*) from TMtrExec myTMtrExec where myTMtrExec.tSalesPos = ?1 "),
		@NamedQuery(name = "getMaxid", query = "select max(myTMtrExec.execId) from TMtrExec myTMtrExec where myTMtrExec.tenantId = ?1  ")
		})
@Table(name = "t_mtr_exec")
public class TMtrExec implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "exec_id", nullable = false, length = 255)
	private Long execId;
	/**
	 * 
	 * @generated
	 */
	@Column(name = "exec_dt_tm", nullable = true, length = 19)
	private Date execDtTm;

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
	@Column(name = "mtr_id", nullable = false, length = 255)
	private Integer mtrId;

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
	@JoinColumn(name = "exec_status_id", referencedColumnName = "execution_status_id", nullable = false, insertable = true, updatable = true)
	@Valid
	private TExecutionStatus tExecutionStatus;

	@ManyToOne
	@JoinColumn(name = "config_id", referencedColumnName = "config_id", nullable = false, insertable = false, updatable = false)
	@Valid
	private TMtrExecConfig tMtrExecConfig;
	
	@NotNull
	@Column(name = "sales_pos_id", nullable = false, length = 255)
	private Long salesPosId;
	@NotNull
	@Column(name = "sales_hier_id", nullable = false, length = 255)
	private Long salesHierId;

	@ManyToOne
	@JoinColumn(name = "sales_pos_id", referencedColumnName = "sales_pos_id", nullable = false, insertable = false, updatable = false)
	//@Valid
	private TSalesPos tSalesPos;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tMtrExec")
	private Set<TMtrExecLog> tMtrExecLogss;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tMtrExec")
	private Set<TSalesPosMtrValue> tSalesPosMtrValuess;
	
	
	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tMtrExec")
	private Set<TGeoMtrValue> tGeoMtrValuess;


	/**
	 *
	 * @generated
	 */
	public TMtrExec() {
	}

	/**
	 * 
	 * @generated
	 */
	public Long getExecId() {
		return execId;
	}

	/**
	 * 
	 * @generated
	 */
	public void setExecId(Long execId) {
		this.execId = execId;
	}

	/**
	 * 
	 * @generated
	 */
	public void setExecDtTm(final Date execDtTm) {
		if (execDtTm != null) {
			this.execDtTm = (Date) execDtTm.clone();
		} else {
			this.execDtTm = null;
		}
	}

	/**
	 * 
	 * @generated
	 */
	public Date getExecDtTm() {
		if (this.execDtTm != null) {
			return (Date) this.execDtTm.clone();
		} else {
			return null;
		}
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

	public void setMtrId(final Integer mtrId) {
		this.mtrId = mtrId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getMtrId() {
		return this.mtrId;
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
	public TExecutionStatus getTExecutionStatus() {
		return this.tExecutionStatus;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTExecutionStatus(final TExecutionStatus tExecutionStatus) {
		this.tExecutionStatus = tExecutionStatus;

	}

	/**
	 * 
	 * @generated
	 */
	public TMtrExecConfig getTMtrExecConfig() {
		return this.tMtrExecConfig;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTMtrExecConfig(final TMtrExecConfig tMtrExecConfig) {
		this.tMtrExecConfig = tMtrExecConfig;
		if (this.tMtrExecConfig != null && this.tMtrExecConfig.getConfigId() != null) {

			this.tMtrExecConfig.setConfigId(this.tMtrExecConfig.getConfigId());

		}

	}

	/**
	 * 
	 * @generated
	 */
	public TSalesPos getTSalesPos() {
		return this.tSalesPos;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTSalesPos(final TSalesPos tSalesPos) {
		this.tSalesPos = tSalesPos;

	}

	/**
	 * 
	 * @generated
	 */
	public Set<TMtrExecLog> getTMtrExecLogss() {
		return this.tMtrExecLogss;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTMtrExecLogss(final Set<TMtrExecLog> tMtrExecLogss) {
		this.tMtrExecLogss = tMtrExecLogss;
	}

	/**
	 * 
	 * @generated
	 */
	public Set<TSalesPosMtrValue> getTSalesPosMtrValuess() {
		return this.tSalesPosMtrValuess;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTSalesPosMtrValuess(final Set<TSalesPosMtrValue> tSalesPosMtrValuess) {
		this.tSalesPosMtrValuess = tSalesPosMtrValuess;
	}

	public Set<TGeoMtrValue> gettGeoMtrValuess() {
		return tGeoMtrValuess;
	}

	public void settGeoMtrValuess(Set<TGeoMtrValue> tGeoMtrValuess) {
		this.tGeoMtrValuess = tGeoMtrValuess;
	}
	
	public void setSalesPosId(Long salesPosId) {
		this.salesPosId = salesPosId;
	}
	public Long getSalesPosId() {
		return salesPosId;
	}
	public void setSalesHierId(Long salesHierId) {
		this.salesHierId = salesHierId;
	}
	public Long getSalesHierId() {
		return salesHierId;
	}
	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TMtrExec that) {
		setExecId(that.getExecId());
		setExecDtTm(that.getExecDtTm());
		setAlgmntId(that.getAlgmntId());
		setBussUnitId(that.getBussUnitId());
		setSalesTeamId(that.getSalesTeamId());
		setMtrId(that.getMtrId());
		setCreatedBy(that.getCreatedBy());
		setCreateDt(that.getCreateDt());
		setUpdatedBy(that.getUpdatedBy());
		setUpdateDt(that.getUpdateDt());
		setTenantId(that.getTenantId());
		setSalesPosId(that.getSalesPosId());
		setSalesHierId(that.getSalesHierId());
	}

	/**
	 * @generated
	 * 
	 */
	@Override
	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = prime * result + ((execId == null) ? 0 : execId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("execId=[").append(execId).append("] ");
		buffer.append("execDtTm=[").append(execDtTm).append("] ");
		buffer.append("algmntId=[").append(algmntId).append("] ");
		buffer.append("bussUnitId=[").append(bussUnitId).append("] ");
		buffer.append("salesTeamId=[").append(salesTeamId).append("] ");
		buffer.append("mtrId=[").append(mtrId).append("] ");
		buffer.append("createdBy=[").append(createdBy).append("] ");
		buffer.append("createDt=[").append(createDt).append("] ");
		buffer.append("updatedBy=[").append(updatedBy).append("] ");
		buffer.append("updateDt=[").append(updateDt).append("] ");
		buffer.append("tenantId=[").append(tenantId).append("] ");
		buffer.append("salesPosId=[").append(salesPosId).append("] ");
		buffer.append("salesHierId=[").append(salesHierId).append("] ");

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
		final TMtrExec other = (TMtrExec) obj;
		if (execId == null) {
			if (other.execId != null) {
				return false;
			}
		} else if (!execId.equals(other.execId)) {
			return false;
		}
		return true;
	}
}
