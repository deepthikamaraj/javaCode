package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/** 
 * JPA class representing TCallDirPrdWt 
 * The corresponding mapping table is t_call_dir_prd_wt 
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "FindAllTCallDirPrdWts", query = "select myTCallDirPrdWt from TCallDirPrdWt myTCallDirPrdWt"),
		@NamedQuery(name = "CountTCallDirPrdWts", query = "Select Count(c) from TCallDirPrdWt c"),
		@NamedQuery(name = "FindTCallDirPrdWtByTCallDirConfig", query = "select myTCallDirPrdWt from TCallDirPrdWt myTCallDirPrdWt where myTCallDirPrdWt.tCallDirPrdWtId.tCallDirConfig = ?1 "),
		@NamedQuery(name = "CountTCallDirPrdWtsByTCallDirConfig", query = "select Count(*) from TCallDirPrdWt myTCallDirPrdWt where myTCallDirPrdWt.tCallDirPrdWtId.tCallDirConfig = ?1 "),
        //@NamedQuery(name = "FindTCallDirPrdWtByTPrdPrtType", query = "select myTCallDirPrdWt from TCallDirPrdWt myTCallDirPrdWt where myTCallDirPrdWt.tPrdPrtType = ?1 "),
		//@NamedQuery(name = "CountTCallDirPrdWtsByTPrdPrtType", query = "select Count(*) from TCallDirPrdWt myTCallDirPrdWt where myTCallDirPrdWt.tPrdPrtType = ?1 "),
		@NamedQuery(name = "getWeightageMatrix", query = "Select tcdc.prdCnt,tcdpw.tCallDirPrdWtId.tCallDirConfig.callDirConfigId,tcdpw.tCallDirPrdWtId.prdPrtTypeId,tcdpw.prdWtg "+
				" from TCallDirPrdWt tcdpw , TCallPlanConfig tcpc, TCallDirConfig tcdc ,TCustAlgmnt tca ,TCustCallPlan tccp  "+
				" WHERE tcdc.tCallPlanConfig.callPlanConfigId = tcpc.callPlanConfigId "+
				" and tccp.tCustAlgmnt.custAlgmntId = tca.custAlgmntId "+
				" and tca.algmntId = tcpc.tAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId and tca.bussUnitId = tcpc.tAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId "+
				" and tca.salesTeamId = tcpc.tAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId and tcdpw.tCallDirPrdWtId.tCallDirConfig.callDirConfigId = tcdc.callDirConfigId "+
				" and tccp.custCallPlanId= ?1 and tccp.tenantId=?2"+
				" group by tcdc.prdCnt,tcdpw.tCallDirPrdWtId.tCallDirConfig.callDirConfigId, tcdpw.tCallDirPrdWtId.prdPrtTypeId "+
				" order by tcdc.prdCnt,tcdpw.tCallDirPrdWtId.tCallDirConfig.callDirConfigId, tcdpw.tCallDirPrdWtId.prdPrtTypeId "),
				
		@NamedQuery(name = "getWeightageMatrixByCallDirection" , query="SELECT tcdc.prdCnt,tcdpw.tCallDirPrdWtId.tCallDirConfig.callDirConfigId,tcdpw.tCallDirPrdWtId.prdPrtTypeId,tcdpw.prdWtg "+
				" FROM TCallDirPrdWt tcdpw , TCallDirConfig tcdc , TCallDir tcd "+
				" WHERE tcd.tCallDirConfig.callDirConfigId = tcdc.callDirConfigId "+
				" AND tcdpw.tCallDirPrdWtId.tCallDirConfig.callDirConfigId = tcd.tCallDirConfig.callDirConfigId "+
				" AND tcd.tCustCallPlan.custCallPlanId= ?1 "+
				" GROUP BY tcdc.prdCnt,tcdpw.tCallDirPrdWtId.tCallDirConfig.callDirConfigId, tcdpw.tCallDirPrdWtId.prdPrtTypeId "+
				" ORDER BY tcdc.prdCnt,tcdpw.tCallDirPrdWtId.tCallDirConfig.callDirConfigId, tcdpw.tCallDirPrdWtId.prdPrtTypeId "),
		@NamedQuery(name="getProductSplitFlag", query="Select tcpc.productSpiltFlag from TCallDirPrdWt tcdpw , TCallPlanConfig tcpc, TCallDirConfig tcdc ,TCustAlgmnt tca ,TCustCallPlan tccp  "+
                        " WHERE tcdc.tCallPlanConfig.callPlanConfigId = tcpc.callPlanConfigId "+
                        " and tccp.tCustAlgmnt.custAlgmntId = tca.custAlgmntId "+
                        " and tca.algmntId = tcpc.tAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId and tca.bussUnitId = tcpc.tAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId "+
                        " and tca.salesTeamId = tcpc.tAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId and tcdpw.tCallDirPrdWtId.tCallDirConfig.callDirConfigId = tcdc.callDirConfigId "+
                        " and tccp.custCallPlanId= ?1 and tcpc.tenantId= ?2 "+
                        " and tcdpw.tenantId = tcpc.tenantId "+
                        " and tcpc.tenantId = tcdc.tenantId "+
                        " and tcdc.tenantId = tca.tenantId "+
                        " and tca.tenantId  = tccp.tenantId ")


 })
@Table(name = "t_call_dir_prd_wt")
public class TCallDirPrdWt implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@EmbeddedId
	@Valid
	private TCallDirPrdWtId tCallDirPrdWtId;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "prd_wtg", nullable = true, length = 255)
	private Double prdWtg;

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

	// @ManyToOne
	// @JoinColumn(name = "call_dir_config_id", referencedColumnName =
	// "call_dir_config_id", nullable = false, insertable = false, updatable =
	// false)
	// @Valid
	// private TCallDirConfig tCallDirConfig;

	/*@ManyToOne
	@JoinColumn(name = "prd_prt_type_id", referencedColumnName = "prd_prt_type_id", nullable = false, insertable = false, updatable = false)
	@Valid
	private TPrdPrtType tPrdPrtType;
*/
	/**
	 *
	 * @generated
	 *//*
	public TCallDirPrdWt() {
	}*/

	/**
	 * 
	 * @generated
	 */

	public void setTCallDirPrdWtId(final TCallDirPrdWtId tCallDirPrdWtId) {
		this.tCallDirPrdWtId = tCallDirPrdWtId;
	}

	/**
	 * 
	 * @generated
	 */
	public TCallDirPrdWtId getTCallDirPrdWtId() {
		return this.tCallDirPrdWtId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setPrdWtg(final Double prdWtg) {
		this.prdWtg = prdWtg;
	}

	/**
	 * 
	 * @generated
	 */
	public Double getPrdWtg() {
		return this.prdWtg;
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

	// /**
	// *
	// * @generated
	// */
	// public TCallDirConfig getTCallDirConfig() {
	// return this.tCallDirConfig;
	// }
	//
	// /**
	// *
	// * @generated
	// */
	// public void setTCallDirConfig(final TCallDirConfig tCallDirConfig) {
	// this.tCallDirConfig = tCallDirConfig;
	// if (this.tCallDirConfig != null
	// && this.tCallDirConfig.getCallDirConfigId() != null) {
	//
	// this.tCallDirPrdWtId.setCallDirConfigId(this.tCallDirConfig
	// .getCallDirConfigId());
	//
	// }
	//
	// }

	/**
	 * 
	 * @generated
	 */
/*	public TPrdPrtType getTPrdPrtType() {
		return this.tPrdPrtType;
	}

	*//**
	 * 
	 * @generated
	 *//*
	public void setTPrdPrtType(final TPrdPrtType tPrdPrtType) {
		this.tPrdPrtType = tPrdPrtType;
		if (this.tPrdPrtType != null && this.tPrdPrtType.getPrdPrtTypeId() != null) {

			this.tCallDirPrdWtId.setPrdPrtTypeId(this.tPrdPrtType.getPrdPrtTypeId());

		}

	}*/

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TCallDirPrdWt that) {
		setTCallDirPrdWtId(that.getTCallDirPrdWtId());
		setPrdWtg(that.getPrdWtg());
		setActiveFlag(that.getActiveFlag());
		setCreatedBy(that.getCreatedBy());
		setCreateDt(that.getCreateDt());
		setUpdatedBy(that.getUpdatedBy());
		setUpdateDt(that.getUpdateDt());
		setTenantId(that.getTenantId());
	}

//	/**
//	 * @generated
//	 * 
//	 */
//	@Override
//	public int hashCode() {
//		int prime = 31;
//		int result = 1;
//		result = prime * result
//				+ ((tCallDirPrdWtId == null) ? 0 : tCallDirPrdWtId.hashCode())
//				+ (int) Math.random();
//		return result;
//	}
//
//	/**
//	 * Returns a textual representation of a bean.
//	 * 
//	 * @generated
//	 */
//	public String toString() {
//
//		final StringBuilder buffer = new StringBuilder();
//
//		buffer.append("tCallDirPrdWtId=[").append(tCallDirPrdWtId).append("] ");
//		buffer.append("prdWtg=[").append(prdWtg).append("] ");
//		buffer.append("activeFlag=[").append(activeFlag).append("] ");
//		buffer.append("createdBy=[").append(createdBy).append("] ");
//		buffer.append("createDt=[").append(createDt).append("] ");
//		buffer.append("updatedBy=[").append(updatedBy).append("] ");
//		buffer.append("updateDt=[").append(updateDt).append("] ");
//		buffer.append("tenantId=[").append(tenantId).append("] ");
//
//		return buffer.toString();
//	}
//
//	/**
//	 * @generated
//	 * 
//	 */
//	@Override
//	public boolean equals(final Object obj) {
//
//		if (this == obj) {
//			return true;
//		}
//		if (obj == null) {
//			return false;
//		}
//		if (getClass() != obj.getClass()) {
//			return false;
//		}
//		final TCallDirPrdWt other = (TCallDirPrdWt) obj;
//		if (tCallDirPrdWtId == null) {
//			if (other.tCallDirPrdWtId != null) {
//				return false;
//			}
//		} else if (!tCallDirPrdWtId.equals(other.tCallDirPrdWtId)) {
//			return false;
//		}
//		return true;
//	}
}
