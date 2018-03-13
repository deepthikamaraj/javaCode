package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

/** 
 * JPA class representing TSalesPosMtrValue 
 * The corresponding mapping table is t_sales_pos_mtr_value 
 */

@Entity
@Audited
@NamedQueries({
		@NamedQuery(name = "FindAllTSalesPosMtrValues", query = "select myTSalesPosMtrValue from TSalesPosMtrValue myTSalesPosMtrValue"),
		@NamedQuery(name = "CountTSalesPosMtrValues", query = "Select Count(c) from TSalesPosMtrValue c"),
		@NamedQuery(name = "FindTSalesPosMtrValueByTMtrExec", query = "select myTSalesPosMtrValue from TSalesPosMtrValue myTSalesPosMtrValue where myTSalesPosMtrValue.tMtrExec = ?1 "),
		@NamedQuery(name = "FindTSalesPosMtrValueByIds", query = "select myTSalesPos.salesPosId,myTSalesPosMtrValue.mtrValue,myTSalesPosMtrValue.updateDt from TSalesPos myTSalesPos,TSalesPosMtrValue myTSalesPosMtrValue where myTSalesPos.prnSalesPosId = ?1 AND myTSalesPos.prnSalesHierId = ?2 AND myTSalesPos.salesPosId = myTSalesPosMtrValue.salesPosId AND myTSalesPos.tAlgmntSalesHier.salesHierId = myTSalesPosMtrValue.salesHierId AND myTSalesPosMtrValue.mtrId = ?3  ORDER BY myTSalesPosMtrValue.updateDt DESC"),
		@NamedQuery(name = "FindTSalesPosMtrValueBySalesPosMtrId", query = "select myTSalesPosMtrValue.mtrValue,myTSalesPosMtrValue.salesPosId from TSalesPosMtrValue myTSalesPosMtrValue where myTSalesPosMtrValue.salesPosId = ?1 AND myTSalesPosMtrValue.salesHierId = ?2 AND myTSalesPosMtrValue.mtrId = ?3  AND myTSalesPosMtrValue.tenantId = ?4 AND myTSalesPosMtrValue.mtrValueTypeId=?5 AND myTSalesPosMtrValue.activeFlag='Y' ORDER BY myTSalesPosMtrValue.updateDt DESC"),
		@NamedQuery(name = "FindTSalesPosMtrValueByMtrId", query = "select myTSalesPosMtrValue.mtrValue,myTSalesPosMtrValue.salesPosId from TSalesPosMtrValue myTSalesPosMtrValue where myTSalesPosMtrValue.mtrId = ?1  AND myTSalesPosMtrValue.salesPosId IN ?2 AND myTSalesPosMtrValue.salesHierId = ?3 AND myTSalesPosMtrValue.tenantId = ?4 AND myTSalesPosMtrValue.mtrValueTypeId=?5 AND myTSalesPosMtrValue.activeFlag='Y' ORDER BY myTSalesPosMtrValue.updateDt DESC "),
		@NamedQuery(name = "CountTSalesPosMtrValuesByTMtrExec", query = "select Count(myTSalesPosMtrValue) from TSalesPosMtrValue myTSalesPosMtrValue where myTSalesPosMtrValue.tMtrExec = ?1 "),
		@NamedQuery(name = "getLatestMetricValueByMetricId", query = " select myTSalesPosMtrValue.mtrValue, myTSalesPosMtrValue.createDt "+
																" from TSalesPosMtrValue myTSalesPosMtrValue , TMtrExec myTMtrExec "+
																" where myTMtrExec.mtrId = ?1 "+
																" and myTMtrExec.tMtrExecConfig.configId = myTSalesPosMtrValue.tMtrExec.tMtrExecConfig.configId "+
																" and myTMtrExec.execId = myTSalesPosMtrValue.tMtrExec.execId "+
																" and myTMtrExec.mtrId = myTSalesPosMtrValue.mtrId "+
																" and myTMtrExec.tSalesPos.tAlgmntSalesHier.salesHierId = myTSalesPosMtrValue.salesHierId "+
																" and myTMtrExec.tSalesPos.salesPosId = myTSalesPosMtrValue.salesPosId " +
																" and myTMtrExec.tenantId = myTSalesPosMtrValue.tenantId and myTSalesPosMtrValue.tenantId = ?2 "+
																" group by myTSalesPosMtrValue.mtrValue, myTSalesPosMtrValue.createDt "+
																" order by myTSalesPosMtrValue.createDt desc"),
	     @NamedQuery(name = "getLatestMetricValueByBaseValueFlag", query = " select myTSalesPosMtrValue.mtrValue, myTSalesPosMtrValue.updateDt,myTSalesPosMtrValue.mtrId, myTSalesPosMtrValue.mtrValueTypeId "+
																		      " from TSalesPosMtrValue myTSalesPosMtrValue "+
																		      " where myTSalesPosMtrValue.mtrId IN ?1 "+
																		      " and myTSalesPosMtrValue.salesHierId =?2 "+
																		      " and myTSalesPosMtrValue.salesPosId =?3 " +
																		      " and myTSalesPosMtrValue.tenantId = ?4 "+
																		      " and myTSalesPosMtrValue.mtrValueTypeId = ?5 "+ 
																		      " and myTSalesPosMtrValue.activeFlag ='Y' "+
																		      // " group by myTSalesPosMtrValue.mtrValue, myTSalesPosMtrValue.updateDt "+
																		      " order by myTSalesPosMtrValue.updateDt asc"),
		@NamedQuery(name = "getLatestMetricValueByBaseFlag", query = " select myTSalesPosMtrValue.mtrValue, myTSalesPosMtrValue.updateDt,myTSalesPosMtrValue.mtrId "+
																		    		  " from TSalesPosMtrValue myTSalesPosMtrValue "+
																		    		  " where myTSalesPosMtrValue.mtrId IN ?1 "+
																		    		  " and myTSalesPosMtrValue.salesHierId =?2 "+
																		    		  " and myTSalesPosMtrValue.salesPosId =?3 " +
																		    		  " and myTSalesPosMtrValue.tenantId = ?4 "+
																		    		  " and myTSalesPosMtrValue.mtrValueTypeId = ?5 "+ 
																		    		  " group by myTSalesPosMtrValue.mtrValue, myTSalesPosMtrValue.updateDt "+
																		    		  " order by myTSalesPosMtrValue.updateDt desc"),
	    @NamedQuery(name = "FindTSalesPosMtrValueByPosIDHierIDAndTenant", query = "select c from TSalesPosMtrValue c where c.salesPosId = ?1 and c.salesHierId = ?2 and c.tenantId =?3 and c.activeFlag = ?4"),
		@NamedQuery(name = "FindMetricBySalesPosId", query = "select myTmtrValue from TSalesPosMtrValue myTmtrValue where myTmtrValue.salesPosId = ?1 "),
		@NamedQuery(name = "CheckMetricIds", query = "select myTmtrValue from TSalesPosMtrValue myTmtrValue where myTmtrValue.mtrId = ?1 and myTmtrValue.tenantId = ?2"),
		@NamedQuery(name = "UpdateMetricBaseValuesByMetricId", query = "update TSalesPosMtrValue myTmtrValue set myTmtrValue.activeFlag = 'N' where myTmtrValue.mtrId = ?1 and myTmtrValue.tenantId = ?2 and myTmtrValue.mtrValueTypeId = ?3"),
		@NamedQuery(name = "CheckIfMetricsExecuted", query = "select myTmtrValue.mtrId from TSalesPosMtrValue myTmtrValue where myTmtrValue.mtrId IN ?1 and myTmtrValue.mtrValueTypeId = ?2 and  myTmtrValue.activeFlag = ?3 and myTmtrValue.tenantId = ?4 group by myTmtrValue.mtrId"),
		@NamedQuery(name = "InactivateSPMetricValues", query = "update TSalesPosMtrValue myTmtrValue set myTmtrValue.activeFlag = 'N' where myTmtrValue.mtrId IN ?1 and myTmtrValue.mtrValueTypeId = ?2 and  myTmtrValue.activeFlag = ?3 and myTmtrValue.tenantId = ?4 and myTmtrValue.salesPosId = ?5 "),
		@NamedQuery(name = "GetMtrValueBySpAndValueType", query = "select myTmtrValue.mtrId,myTmtrValue.mtrValue,myTmtrValue.updateDt from TSalesPosMtrValue myTmtrValue where myTmtrValue.salesPosId = ?1 and myTmtrValue.mtrValueTypeId = ?2 and myTmtrValue.tenantId = ?3 and myTmtrValue.activeFlag = 'Y' order by myTmtrValue.createDt desc"),
		@NamedQuery(name = "findMinAndMaxValueByMtrId", query = "select min(myTmtrValue.mtrValue),max(myTmtrValue.mtrValue) from TSalesPosMtrValue myTmtrValue where myTmtrValue.mtrId = ?1 and myTmtrValue.tenantId = ?2 and myTmtrValue.activeFlag = 'Y'"),
		@NamedQuery(name = "GetMtrValueBySpListAndValueType", query = "select myTmtrValue.salesPosId,myTmtrValue.mtrValue,myTmtrValue.updateDt from TSalesPosMtrValue myTmtrValue where myTmtrValue.salesPosId in ?1 and myTmtrValue.mtrValueTypeId = ?2 and myTmtrValue.mtrId = ?3 and myTmtrValue.tenantId = ?4 and myTmtrValue.activeFlag = 'Y' order by myTmtrValue.createDt desc"),
		
		@NamedQuery(name = "GetMtrValueByMtrSpAndBaseFlag", query = "select myTmtrValue.mtrId,myTmtrValue.mtrValue,myTmtrValue.updateDt,myTmtrValue.mtrValueTypeId from TSalesPosMtrValue myTmtrValue where myTmtrValue.mtrId IN ?1 and myTmtrValue.salesPosId = ?2 and myTmtrValue.mtrValueTypeId IN ?3 and myTmtrValue.tenantId = ?4 and myTmtrValue.activeFlag = 'Y' order by myTmtrValue.updateDt desc"),
		@NamedQuery(name = "GetMtrValueBySpAndValueTypeAndMtrIdAndConfigId", query = "select myTmtrValue.mtrId,myTmtrValue.mtrValue,myTmtrValue.updateDt,myTmtrValue.tMtrExec.tMtrExecConfig.configId from TSalesPosMtrValue myTmtrValue where myTmtrValue.salesPosId = ?1 and myTmtrValue.mtrValueTypeId = ?2 and  myTmtrValue.activeFlag = ?3 and myTmtrValue.mtrId IN ?4 and myTmtrValue.tMtrExec.tMtrExecConfig.configId IN ?5 and myTmtrValue.tenantId = ?6 order by myTmtrValue.createDt desc")
		})
@Table(name = "t_sales_pos_mtr_value", uniqueConstraints = @UniqueConstraint(columnNames = { "seq_number" }))
public class TSalesPosMtrValue implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "seq_number", nullable = false, length = 255)
	private Long seqNumber;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "mtr_value", nullable = true, length = 255)
	private Float mtrValue;

	/**
	 * 
	 * @generated
	 */
	@NotNull
	@Column(name = "sales_pos_id", nullable = false, length = 255)
	private Long salesPosId;

	/**
	 * 
	 * @generated
	 */
	@NotNull
	@Column(name = "sales_hier_id", nullable = false, length = 255)
	private Long salesHierId;

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
	
	@Column(name = "active_flag", nullable = false, length = 1)
	private Character activeFlag;
	
	@NotNull
	@Column(name = "exec_id", nullable = false, length = 255)
	private Long execId;
	
	@Column(name = "mtr_value_type_id", nullable = true, length = 255)
	private Integer mtrValueTypeId;

	@ManyToOne
	@NotAudited
	@JoinColumn(name = "exec_id", referencedColumnName = "exec_id", nullable = false, insertable = false, updatable = false)
	private TMtrExec tMtrExec;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "mtr_id", referencedColumnName = "mtr_id", nullable = false, insertable = false, updatable = false)	
	private TMtr tMtrs;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "sales_pos_id", referencedColumnName = "sales_pos_id", nullable = false, insertable = false, updatable = false)
	@Valid
	private TSalesPos tSalesPos;
	/**
	 *
	 * @generated
	 */
	public TSalesPosMtrValue() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setSeqNumber(final Long seqNumber) {
		this.seqNumber = seqNumber;
	}

	/**
	 * 
	 * @generated
	 */
	public Long getSeqNumber() {
		return this.seqNumber;
	}

	/**
	 * 
	 * @generated
	 */

	public void setMtrValue(final Float mtrValue) {
		this.mtrValue = mtrValue;
	}

	/**
	 * 
	 * @generated
	 */
	public Float getMtrValue() {
		return this.mtrValue;
	}

	/**
	 * 
	 * @generated
	 */

	public void setSalesPosId(final Long salesPosId) {
		this.salesPosId = salesPosId;
	}

	/**
	 * 
	 * @generated
	 */
	public Long getSalesPosId() {
		return this.salesPosId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setSalesHierId(final Long salesHierId) {
		this.salesHierId = salesHierId;
	}

	/**
	 * 
	 * @generated
	 */
	public Long getSalesHierId() {
		return this.salesHierId;
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
	
	public void setActiveFlag(final Character activeFlag) {
		this.activeFlag = activeFlag;
	}

	public Character getActiveFlag() {
		return this.activeFlag;
	}

	/**
	 * 
	 * @generated
	 */
	public TMtrExec getTMtrExec() {
		return this.tMtrExec;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTMtrExec(final TMtrExec tMtrExec) {
		this.tMtrExec = tMtrExec;

	}

	/**
	 * 
	 * @generated
	 */

	public void setMtrValueTypeId(final Integer mtrValueTypeId) {
		this.mtrValueTypeId = mtrValueTypeId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getMtrValueTypeId() {
		return this.mtrValueTypeId;
	}
	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TSalesPosMtrValue that) {
		setSeqNumber(that.getSeqNumber());
		setMtrValue(that.getMtrValue());
		setSalesPosId(that.getSalesPosId());
		setSalesHierId(that.getSalesHierId());
		setExecDtTm(that.getExecDtTm());
		setMtrId(that.getMtrId());
		setCreatedBy(that.getCreatedBy());
		setCreateDt(that.getCreateDt());
		setUpdatedBy(that.getUpdatedBy());
		setUpdateDt(that.getUpdateDt());
		setTenantId(that.getTenantId());
		setActiveFlag(that.getActiveFlag());
		setMtrValueTypeId(that.getMtrValueTypeId());
	}

	/**
	 * @generated
	 * 
	 */
	@Override
	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = prime * result + ((seqNumber == null) ? 0 : seqNumber.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("seqNumber=[").append(seqNumber).append("] ");
		buffer.append("mtrValue=[").append(mtrValue).append("] ");
		buffer.append("salesPosId=[").append(salesPosId).append("] ");
		buffer.append("salesHierId=[").append(salesHierId).append("] ");
		buffer.append("execDtTm=[").append(execDtTm).append("] ");
		buffer.append("mtrId=[").append(mtrId).append("] ");
		buffer.append("createdBy=[").append(createdBy).append("] ");
		buffer.append("createDt=[").append(createDt).append("] ");
		buffer.append("updatedBy=[").append(updatedBy).append("] ");
		buffer.append("updateDt=[").append(updateDt).append("] ");
		buffer.append("tenantId=[").append(tenantId).append("] ");
		buffer.append("activeFlag=[").append(activeFlag).append("] ");
		buffer.append("mtrValueTypeId=[").append(mtrValueTypeId).append("] ");
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
		final TSalesPosMtrValue other = (TSalesPosMtrValue) obj;
		if (seqNumber == null) {
			if (other.seqNumber != null) {
				return false;
			}
		} else if (!seqNumber.equals(other.seqNumber)) {
			return false;
		}
		return true;
	}

	public Long getExecId() {
		return execId;
	}

	public void setExecId(Long execId) {
		this.execId = execId;
	}
}
