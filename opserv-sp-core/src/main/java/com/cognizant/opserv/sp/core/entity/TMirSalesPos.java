package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/** 
 * JPA class representing TMirSalesPos 
 * The corresponding mapping table is t_mir_sales_pos 
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "FindAllTMirSalesPoss", query = "select myTMirSalesPos from TMirSalesPos myTMirSalesPos"),
		@NamedQuery(name = "CountTMirSalesPoss", query = "Select Count(c) from TMirSalesPos c"),
		@NamedQuery(name = "FindTMirSalesPosByTSalesPosByTMirSalesPosIbfk1", query = "select myTMirSalesPos from TMirSalesPos myTMirSalesPos where myTMirSalesPos.tSalesPosByTMirSalesPosIbfk1 = ?1 "),
		@NamedQuery(name = "CountTMirSalesPossByTSalesPosByTMirSalesPosIbfk1", query = "select Count(myTMirSalesPos) from TMirSalesPos myTMirSalesPos where myTMirSalesPos.tSalesPosByTMirSalesPosIbfk1 = ?1 "),
		@NamedQuery(name = "FindTMirSalesPosByTSalesPosByTMirSalesPosIbfk2", query = "select myTMirSalesPos from TMirSalesPos myTMirSalesPos where myTMirSalesPos.tSalesPosByTMirSalesPosIbfk2 = ?1 "),
		@NamedQuery(name = "getPrimarySalesPosBySalesPos", query = "select myTMirSalesPos from TMirSalesPos myTMirSalesPos where myTMirSalesPos.tSalesPosByTMirSalesPosIbfk1 = ?1 and myTMirSalesPos.prMirFlag ='Y' "),
		@NamedQuery(name = "CountTMirSalesPossByTSalesPosByTMirSalesPosIbfk2", query = "select Count(myTMirSalesPos) from TMirSalesPos myTMirSalesPos where myTMirSalesPos.tSalesPosByTMirSalesPosIbfk2 = ?1 "),
		@NamedQuery(name = "getTMirSalesPossByTSalesPosition", query = "select myTMirSalesPos.tSalesPosByTMirSalesPosIbfk2.salesPosId, myTMirSalesPos.tSalesPosByTMirSalesPosIbfk2.tAlgmntSalesHier.salesHierId,myTMirSalesPos.tSalesPosByTMirSalesPosIbfk2.tAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId," +
							" myTMirSalesPos.tSalesPosByTMirSalesPosIbfk2.tAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId,myTMirSalesPos.tSalesPosByTMirSalesPosIbfk2.tAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId," +
							" myTMirSalesPos.prMirFlag from TMirSalesPos myTMirSalesPos where myTMirSalesPos.tSalesPosByTMirSalesPosIbfk1.salesPosId = ?1 and myTMirSalesPos.tenantId = ?2 and myTMirSalesPos.activeFlag = 'Y' ")})
@Table(name = "t_mir_sales_pos", uniqueConstraints = @UniqueConstraint(columnNames = { "sales_pos_mir_id" }))
public class TMirSalesPos implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "sales_pos_mir_id", nullable = false, length = 255)
	private Long salesPosMirId;

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
	@Column(name = "eff_start_dt", nullable = true, length = 10)
	private Date effStartDt;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "eff_end_dt", nullable = true, length = 10)
	private Date effEndDt;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "pr_mir_flag", nullable = true, length = 1)
	private Character prMirFlag;

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
	@Column(name = "tenant_id", nullable = true, length = 255)
	private Short tenantId;

	@ManyToOne
	@JoinColumn(name = "base_sales_pos_id", referencedColumnName = "sales_pos_id", nullable = false, insertable = true, updatable = true)
	@Valid
	private TSalesPos tSalesPosByTMirSalesPosIbfk1;

	@ManyToOne
	@JoinColumn(name = "mir_sales_pos_id", referencedColumnName = "sales_pos_id", nullable = false, insertable = true, updatable = true)
	@Valid
	private TSalesPos tSalesPosByTMirSalesPosIbfk2;

	/**
	 *
	 * @generated
	 */
	public TMirSalesPos() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setSalesPosMirId(final Long salesPosMirId) {
		this.salesPosMirId = salesPosMirId;
	}

	/**
	 * 
	 * @generated
	 */
	public Long getSalesPosMirId() {
		return this.salesPosMirId;
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

	public void setPrMirFlag(final Character prMirFlag) {
		this.prMirFlag = prMirFlag;
	}

	/**
	 * 
	 * @generated
	 */
	public Character getPrMirFlag() {
		return this.prMirFlag;
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
	public TSalesPos getTSalesPosByTMirSalesPosIbfk1() {
		return this.tSalesPosByTMirSalesPosIbfk1;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTSalesPosByTMirSalesPosIbfk1(final TSalesPos tSalesPosByTMirSalesPosIbfk1) {
		this.tSalesPosByTMirSalesPosIbfk1 = tSalesPosByTMirSalesPosIbfk1;

	}

	/**
	 * 
	 * @generated
	 */
	public TSalesPos getTSalesPosByTMirSalesPosIbfk2() {
		return this.tSalesPosByTMirSalesPosIbfk2;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTSalesPosByTMirSalesPosIbfk2(final TSalesPos tSalesPosByTMirSalesPosIbfk2) {
		this.tSalesPosByTMirSalesPosIbfk2 = tSalesPosByTMirSalesPosIbfk2;

	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TMirSalesPos that) {
		setSalesPosMirId(that.getSalesPosMirId());
		setActiveFlag(that.getActiveFlag());
		setEffStartDt(that.getEffStartDt());
		setEffEndDt(that.getEffEndDt());
		setPrMirFlag(that.getPrMirFlag());
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
		result = prime * result + ((salesPosMirId == null) ? 0 : salesPosMirId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("salesPosMirId=[").append(salesPosMirId).append("] ");
		buffer.append("activeFlag=[").append(activeFlag).append("] ");
		buffer.append("effStartDt=[").append(effStartDt).append("] ");
		buffer.append("effEndDt=[").append(effEndDt).append("] ");
		buffer.append("prMirFlag=[").append(prMirFlag).append("] ");
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
		final TMirSalesPos other = (TMirSalesPos) obj;
		if (salesPosMirId == null) {
			if (other.salesPosMirId != null) {
				return false;
			}
		} else if (!salesPosMirId.equals(other.salesPosMirId)) {
			return false;
		}
		return true;
	}
}
