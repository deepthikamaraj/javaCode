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
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

/** 
 * JPA class representing TGeoMtrValue 
 * The corresponding mapping table is t_geo_mtr_value 
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "FindAllTGeoMtrValues", query = "select myTGeoMtrValue from TGeoMtrValue myTGeoMtrValue"),
		@NamedQuery(name = "CountTGeoMtrValues", query = "Select Count(c) from TGeoMtrValue c"),
		@NamedQuery(name = "FindTGeoMtrValueByTTerrZipAlgmnt", query = "select myTGeoMtrValue from TGeoMtrValue myTGeoMtrValue  "),
		@NamedQuery(name = "FindTGeoMtrValueByPosCodesAndMtrId", query = "select myTGeoMtrValue.salesPosId,myTGeoMtrValue.mtrValue,myTGeoMtrValue.postalCd from TGeoMtrValue myTGeoMtrValue,TTerrZipAlgmnt myTTerrZipAlgmnt where myTTerrZipAlgmnt.tSalesPos.salesPosId = myTGeoMtrValue.salesPosId AND myTTerrZipAlgmnt.tSalesPos.tAlgmntSalesHier.salesHierId=myTGeoMtrValue.salesHierId AND  myTGeoMtrValue.mtrId = ?1 AND myTTerrZipAlgmnt.postalCd IN ?2 AND myTGeoMtrValue.postalCd = myTTerrZipAlgmnt.postalCd AND myTGeoMtrValue.tenantId= ?3 AND myTTerrZipAlgmnt.activeFlag='Y' AND myTTerrZipAlgmnt.activeFlag = myTGeoMtrValue.activeFlag"),
		@NamedQuery(name = "FindTGeoMtrValueByIDs", query = "select myTGeoMtrValue from TGeoMtrValue myTGeoMtrValue where myTGeoMtrValue.salesPosId=?1 AND myTGeoMtrValue.salesHierId=?2 AND  myTGeoMtrValue.mtrId = ?3 AND myTGeoMtrValue.postalCd =?4 AND myTGeoMtrValue.tenantId= ?5 and myTGeoMtrValue.activeFlag = 'Y' order by myTGeoMtrValue.updateDt DESC"),
		@NamedQuery(name = "findTGeoMtrValueByIDsForHeatMap", query = "select myTGeoMtrValue.mtrValue,myTGeoMtrValue.postalCd,myTGeoMtrValue.salesPosId from TGeoMtrValue myTGeoMtrValue where myTGeoMtrValue.mtrId = ?1 AND myTGeoMtrValue.postalCd IN ?2 AND myTGeoMtrValue.mtrValueTypeId = ?3 AND myTGeoMtrValue.tenantId= ?4 and myTGeoMtrValue.activeFlag = 'Y' order by myTGeoMtrValue.updateDt DESC"),
		@NamedQuery(name = "FindTGeoMtrValueByPostalCd", query = "select myTGeoMtrValue.mtrId,myTGeoMtrValue.mtrValue,myTGeoMtrValue.postalCd,myTGeoMtrValue.salesPosId,myTGeoMtrValue.mtrValueTypeId,myTGeoMtrValue.updateDt from TGeoMtrValue myTGeoMtrValue where myTGeoMtrValue.postalCd IN ?1 AND myTGeoMtrValue.tenantId= ?2 and myTGeoMtrValue.activeFlag = 'Y' order by myTGeoMtrValue.updateDt DESC"),
		@NamedQuery(name = "findMinAndMaxGeoMtrValueByMtrId", query = "select min(myTGeoMtrValue.mtrValue),max(myTGeoMtrValue.mtrValue) from TGeoMtrValue myTGeoMtrValue where myTGeoMtrValue.mtrId = ?1 and myTGeoMtrValue.tenantId = ?2 and myTGeoMtrValue.activeFlag = 'Y'"),
		@NamedQuery(name = "getMtrValueByPostalCodesAndSP", query = "select myTGeoMtrValue.mtrId,myTGeoMtrValue.mtrValue,myTGeoMtrValue.postalCd,myTGeoMtrValue.salesPosId,myTGeoMtrValue.mtrValueTypeId,myTGeoMtrValue.updateDt from TGeoMtrValue myTGeoMtrValue where myTGeoMtrValue.mtrId IN ?1 AND myTGeoMtrValue.postalCd = ?2 AND myTGeoMtrValue.salesPosId= ?3 AND myTGeoMtrValue.mtrValueTypeId IN ?4 AND myTGeoMtrValue.tenantId= ?5 and myTGeoMtrValue.activeFlag = 'Y' order by myTGeoMtrValue.updateDt DESC"),
		//@NamedQuery(name = "CountTGeoMtrValuesByTTerrZipAlgmnt", query = "select Count(myTGeoMtrValue) from TGeoMtrValue myTGeoMtrValue where myTGeoMtrValue.tTerrZipAlgmnt = ?1 "),
		})
@Table(name = "t_geo_mtr_value", uniqueConstraints = @UniqueConstraint(columnNames = { "seq_number" }))
public class TGeoMtrValue implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "seq_number", nullable = false, length = 255)
	private Integer seqNumber;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "exec_dt_tm", nullable = true, length = 10)
	private Date execDtTm;

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
	@Column(name = "mtr_value", nullable = true, length = 255)
	private Float mtrValue;

	/**
	 * 
	 * @generated
	 */
	@NotNull
	@Column(name = "exec_id", nullable = false, length = 255)
	private Long execId;
	
	
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
	@Column(name = "mtr_id", nullable = false, length = 255)
	private Integer mtrId;
	
	@NotNull
	@Column(name = "sales_hier_id", nullable = false, length = 255)
	private Long salesHierId;
	
	@NotNull
	@Column(name = "sales_pos_id", nullable = false, length = 255)
	private Long salesPosId;
	
	
	@Length(max = 20)
	@Column(name = "postal_cd", nullable = true, length = 20)
	private String postalCd;
	
	@Column(name = "mtr_value_type_id", nullable = true, length = 255)
	private Integer mtrValueTypeId;
	
	/*@ManyToOne
	@JoinColumns({
			@JoinColumn(name = "sales_pos_id", referencedColumnName = "sales_pos_id", nullable = false, insertable = true, updatable = true),
			@JoinColumn(name = "sales_hier_id", referencedColumnName = "sales_hier_id", nullable = false, insertable = true, updatable = true),
			@JoinColumn(name = "postal_cd", referencedColumnName = "postal_cd", nullable = false, insertable = true, updatable = true) })
	@Valid
	private TTerrZipAlgmnt tTerrZipAlgmnt;*/
	
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
	
	
	public Long getSalesHierId() {
		return salesHierId;
	}

	public void setSalesHierId(Long salesHierId) {
		this.salesHierId = salesHierId;
	}

	public Long getSalesPosId() {
		return salesPosId;
	}

	public void setSalesPosId(Long salesPosId) {
		this.salesPosId = salesPosId;
	}

	public String getPostalCd() {
		return postalCd;
	}

	public void setPostalCd(String postalCd) {
		this.postalCd = postalCd;
	}

	@ManyToOne
	@JoinColumn(name = "exec_id", referencedColumnName = "exec_id", nullable = false, insertable = false, updatable = false)
	//@Valid
	private TMtrExec tMtrExec;


	/**
	 *
	 * @generated
	 */
	public TGeoMtrValue() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setSeqNumber(final Integer seqNumber) {
		this.seqNumber = seqNumber;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getSeqNumber() {
		return this.seqNumber;
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

	public void setExecId(final Long execId) {
		this.execId = execId;
	}

	/**
	 * 
	 * @generated
	 */
	public Long getExecId() {
		return this.execId;
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
	/*public TTerrZipAlgmnt getTTerrZipAlgmnt() {
		return this.tTerrZipAlgmnt;
	}

	*//**
	 * 
	 * @generated
	 *//*
	public void setTTerrZipAlgmnt(final TTerrZipAlgmnt tTerrZipAlgmnt) {
		this.tTerrZipAlgmnt = tTerrZipAlgmnt;

	}*/

	public TMtrExec gettMtrExec() {
		return tMtrExec;
	}

	public void settMtrExec(TMtrExec tMtrExec) {
		this.tMtrExec = tMtrExec;
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TGeoMtrValue that) {
		setSeqNumber(that.getSeqNumber());
		setExecDtTm(that.getExecDtTm());
		setActiveFlag(that.getActiveFlag());
		setMtrValue(that.getMtrValue());
		setCreatedBy(that.getCreatedBy());
		setCreateDt(that.getCreateDt());
		setUpdatedBy(that.getUpdatedBy());
		setUpdateDt(that.getUpdateDt());
		setTenantId(that.getTenantId());
		setMtrId(that.getMtrId());
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
		buffer.append("execDtTm=[").append(execDtTm).append("] ");
		buffer.append("activeFlag=[").append(activeFlag).append("] ");
		buffer.append("mtrValue=[").append(mtrValue).append("] ");
		/*buffer.append("execId=[").append(execId).append("] ");
		buffer.append("configId=[").append(configId).append("] ");*/
		buffer.append("createdBy=[").append(createdBy).append("] ");
		buffer.append("createDt=[").append(createDt).append("] ");
		buffer.append("updatedBy=[").append(updatedBy).append("] ");
		buffer.append("updateDt=[").append(updateDt).append("] ");
		buffer.append("tenantId=[").append(tenantId).append("] ");
		buffer.append("mtrId=[").append(mtrId).append("] ");
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
		final TGeoMtrValue other = (TGeoMtrValue) obj;
		if (seqNumber == null) {
			if (other.seqNumber != null) {
				return false;
			}
		} else if (!seqNumber.equals(other.seqNumber)) {
			return false;
		}
		return true;
	}
}
