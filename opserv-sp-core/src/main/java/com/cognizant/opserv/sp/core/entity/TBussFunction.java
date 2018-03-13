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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.QueryHint;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/** 
 * JPA class representing TBussFunction 
 * The corresponding mapping table is t_buss_function 
 */
/*@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Cacheable(value = true)*/
@Entity
@NamedQueries({
		@NamedQuery(name = "FindAllTBussFunctions", query = "select myTBussFunction from TBussFunction myTBussFunction",hints = {
				@QueryHint(name = "org.hibernate.cacheable", value = "true"),
				@QueryHint(name = "org.hibernate.cacheRegion", value = "query.namedQueryCache")			
		}),
		@NamedQuery(name = "FindAllTBussFuncByTenantId", query = "select myTBussFunction from TBussFunction myTBussFunction where myTBussFunction.tenantId = ?1"),
		@NamedQuery(name = "CountTBussFunctions", query = "Select Count(c) from TBussFunction c") })
@Table(name = "t_buss_function", uniqueConstraints = @UniqueConstraint(columnNames = { "buss_function_id" }))
public class TBussFunction implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "buss_function_id", nullable = false, length = 255)
	private Integer bussFunctionId;

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
	@NotEmpty
	@Length(max = 50)
	@Column(name = "buss_function_name", nullable = false, length = 50)
	private String bussFunctionName;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 100)
	@Column(name = "buss_function_desc", nullable = true, length = 100)
	private String bussFunctionDesc;

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

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tBussFunction")
	private Set<TBussObjAssoc> tBussObjAssocss;

	
	
	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tBussFunction")
	private Set<TCvgRule> tCvgRule;
	
	
	public Set<TCvgRule> getTCvgRules() {
		return this.tCvgRule;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTCvgRule(final Set<TCvgRule> tCvgRule) {
		this.tCvgRule = tCvgRule;
	}

	
	/**
	 *
	 * @generated
	 */
	public TBussFunction() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setBussFunctionId(final Integer bussFunctionId) {
		this.bussFunctionId = bussFunctionId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getBussFunctionId() {
		return this.bussFunctionId;
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

	public void setBussFunctionName(final String bussFunctionName) {
		this.bussFunctionName = bussFunctionName;
	}

	/**
	 * 
	 * @generated
	 */
	public String getBussFunctionName() {
		return this.bussFunctionName;
	}

	/**
	 * 
	 * @generated
	 */

	public void setBussFunctionDesc(final String bussFunctionDesc) {
		this.bussFunctionDesc = bussFunctionDesc;
	}

	/**
	 * 
	 * @generated
	 */
	public String getBussFunctionDesc() {
		return this.bussFunctionDesc;
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
	public Set<TBussObjAssoc> getTBussObjAssocss() {
		return this.tBussObjAssocss;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTBussObjAssocss(final Set<TBussObjAssoc> tBussObjAssocss) {
		this.tBussObjAssocss = tBussObjAssocss;
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TBussFunction that) {
		setBussFunctionId(that.getBussFunctionId());
		setActiveFlag(that.getActiveFlag());
		setBussFunctionName(that.getBussFunctionName());
		setBussFunctionDesc(that.getBussFunctionDesc());
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
		result = prime * result + ((bussFunctionId == null) ? 0 : bussFunctionId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("bussFunctionId=[").append(bussFunctionId).append("] ");
		buffer.append("activeFlag=[").append(activeFlag).append("] ");
		buffer.append("bussFunctionName=[").append(bussFunctionName).append("] ");
		buffer.append("bussFunctionDesc=[").append(bussFunctionDesc).append("] ");
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
		final TBussFunction other = (TBussFunction) obj;
		if (bussFunctionId == null) {
			if (other.bussFunctionId != null) {
				return false;
			}
		} else if (!bussFunctionId.equals(other.bussFunctionId)) {
			return false;
		}
		return true;
	}
}
