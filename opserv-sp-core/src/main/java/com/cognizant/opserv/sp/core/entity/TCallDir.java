package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/** 
 * JPA class representing TCallDir 
 * The corresponding mapping table is t_call_dir 
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "FindAllTCallDirs", query = "select myTCallDir from TCallDir myTCallDir"),
		@NamedQuery(name = "CountTCallDirs", query = "Select Count(c) from TCallDir c"),
		@NamedQuery(name = "FindTCallDirByTCustCallPlan", query = "select myTCallDir from TCallDir myTCallDir where myTCallDir.tCustCallPlan = ?1 "),
		@NamedQuery(name = "CountTCallDirsByTCustCallPlan", query = "select Count(myTCallDir) from TCallDir myTCallDir where myTCallDir.tCustCallPlan = ?1 "),
		/*@NamedQuery(name = "getCallDirectionData" , query="SELECT tcd.callDirId,tcd.numCalls,tcdp.tPrdPrtType.prdPrtTypeId,tcdp.prdId,tp.prdDesc "+
				" FROM TCallDir tcd , TCustCallPlan tccp , TCallDirPrd tcdp, TPrd tp "+
				" WHERE tcd.tCustCallPlan.custCallPlanId = ?1 "+
				" AND tcd.callDirId = tcdp.tCallDirPrdId.callDirId "+
				" AND tcdp.prdId = tp.prdId"+
				" AND tcd.activeFlag='Y' "+
				" GROUP BY tcd.callDirId,tcd.numCalls,tcdp.tPrdPrtType.prdPrtTypeId,tcdp.prdId "+
				" ORDER BY tcd.callDirId,tcd.numCalls,tcdp.tPrdPrtType.prdPrtTypeId,tcdp.prdId "),*/
		@NamedQuery(name = "getCallDirectionData" , query="SELECT tcd.callDirId,tcd.numCalls,tcdp.tCallDirPrdId.prdPrtTypeId,tcdp.prdId,tp.prdDesc "+
				" FROM TCallDir tcd , TCustCallPlan tccp , TCallDirPrd tcdp, TPrd tp, TPrdPrtType tppt "+
				" WHERE tcd.tCustCallPlan.custCallPlanId = ?1 "+
				" AND tcd.callDirId = tcdp.tCallDirPrdId.callDirId "+
				" AND tcdp.prdId = tp.prdId"+
				" AND tcd.activeFlag='Y' AND tcdp.tCallDirPrdId.prdPrtTypeId = tppt.tPrdPrtTypeId.prdPrtTypeId AND tppt.tPrdPrtTypeId.localeId = ?2 "+
				" AND tcd.tenantId = tccp.tenantId AND tccp.tenantId =  tcdp.tenantId AND tcd.tenantId = ?3 "+
				" GROUP BY tcd.callDirId,tcd.numCalls,tcdp.tCallDirPrdId.prdPrtTypeId,tcdp.prdId "+
				" ORDER BY tcd.callDirId,tcd.numCalls,tcdp.tCallDirPrdId.prdPrtTypeId,tcdp.prdId "),
       @NamedQuery(name="deleteCallDirection",query="Update TCallDir tcallDir set tcallDir.activeFlag='N' where tcallDir.callDirId = ?1 AND tcallDir.tenantId= ?2")
		})
@Table(name = "t_call_dir", uniqueConstraints = @UniqueConstraint(columnNames = { "call_dir_id" }))
public class TCallDir implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "call_dir_id", nullable = false, length = 255)
	private Integer callDirId;

	/**
	 * 
	 * @generated
	 */
	@NotNull
	@Column(name = "num_calls", nullable = false, length = 255)
	private Integer numCalls;

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
	@JoinColumn(name = "cust_call_plan_id", referencedColumnName = "cust_call_plan_id", nullable = false, insertable = true, updatable = true)
	@Valid
	private TCustCallPlan tCustCallPlan;

	@OneToMany(fetch=FetchType.LAZY,cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tCallDir")
	private Set<TCallDirPrd> tCallDirPrdss;
	
	/**
	 *
	 * @generated
	 */
	
	@ManyToOne
	@JoinColumn(name="call_dir_config_id",referencedColumnName="call_dir_config_id",nullable = false, insertable = true, updatable = true)
	private TCallDirConfig tCallDirConfig;
	

	/**
	 *
	 * @generated
	 */
//	public TCallDir() {
//	}

	/**
	 * 
	 * @generated
	 */

	public void setCallDirId(final Integer callDirId) {
		this.callDirId = callDirId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getCallDirId() {
		return this.callDirId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setNumCalls(final Integer numCalls) {
		this.numCalls = numCalls;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getNumCalls() {
		return this.numCalls;
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
			this.createDt=x;
			
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
			this.updateDt=x;
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

	/**
	 * 
	 * @generated
	 */
	public TCustCallPlan getTCustCallPlan() {
		return this.tCustCallPlan;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTCustCallPlan(final TCustCallPlan tCustCallPlan) {
		this.tCustCallPlan = tCustCallPlan;

	}

	/**
	 * 
	 * @generated
	 */
	public Set<TCallDirPrd> getTCallDirPrdss() {
		return this.tCallDirPrdss;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTCallDirPrdss(final Set<TCallDirPrd> tCallDirPrdss) {
		this.tCallDirPrdss = tCallDirPrdss;
	}

	/**
	 * @return the tCallDirConfig
	 */
	public TCallDirConfig gettCallDirConfig() {
		return tCallDirConfig;
	}

	/**
	 * @param tCallDirConfig the tCallDirConfig to set
	 */
	public void settCallDirConfig(TCallDirConfig tCallDirConfig) {
		this.tCallDirConfig = tCallDirConfig;
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TCallDir that) {
		setCallDirId(that.getCallDirId());
		setNumCalls(that.getNumCalls());
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
		result = prime * result + ((callDirId == null) ? 0 : callDirId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("callDirId=["+callDirId+"] ");
		buffer.append("numCalls=["+numCalls+"] ");
		buffer.append("activeFlag=["+activeFlag+"] ");
		buffer.append("createdBy=["+createdBy+"] ");
		buffer.append("createDt=["+createDt+"] ");
		buffer.append("updatedBy=["+updatedBy+"] ");
		buffer.append("updateDt=["+updateDt+"] ");
		buffer.append("tenantId=["+tenantId+"] ");

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
		final TCallDir other = (TCallDir) obj;
		if (callDirId == null) {
			if (other.callDirId != null) {
				return false;
			}
		} else if (!callDirId.equals(other.callDirId)) {
			return false;
		}
		return true;
	}
}
