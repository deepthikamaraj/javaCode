package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
 * JPA class representing TBussObj The corresponding mapping table is t_buss_obj
 */
/*@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Cacheable(value = true)*/
@Entity
@NamedQueries({
		@NamedQuery(name = "FindAllTBussObjs", query = "select myTBussObj from TBussObj myTBussObj"),
		@NamedQuery(name = "CountTBussObjs", query = "Select Count(c) from TBussObj c"),

		@NamedQuery(name = "findTBussObjByBussFuncType", query = "Select myTBussObj from TBussObj myTBussObj where myTBussObj.bussFunctionType=?1 and myTBussObj.tenantId=?2", hints = {
				@QueryHint(name = "org.hibernate.cacheable", value = "true"),
				@QueryHint(name = "org.hibernate.cacheRegion", value = "query.namedQueryCache")

		}),

		/*@NamedQuery(name = "findTBussObjByBussFuncType", query = "Select myTBussObj from TBussObj myTBussObj where myTBussObj.bussFunctionType=?1"),*/
		@NamedQuery(name = "findTBussObjByBussAssoc", query = "Select myTBussObj from TBussObj myTBussObj,TBussObjAssoc myBussObj where myTBussObj.bussObjId = myBussObj.tBussObjAssocId.bussObjId AND myBussObj.tBussObjAssocId.bussFunctionId=?1 and myBussObj.tenantId = ?2"),
		@NamedQuery(name = "findTAttrDefByBussObj", query = "Select myTAttrDef from TBussObj myTBussObj,TBussEntity myTBussEntity,TAttrDef myTAttrDef where myTBussObj.bussObjId = myTBussEntity.tBussObj.bussObjId and myTBussEntity.entityId = myTAttrDef.entityId and myTBussObj.bussObjId = ?1 and myTBussObj.tenantId=?2 "), 
		@NamedQuery(name = "findTBussObjByBussFuncTypeBussNameAndTenant", query = "Select myTBussObj from TBussObj myTBussObj where myTBussObj.bussFunctionType=?1 and myTBussObj.bussObjName = ?2" +
				" and myTBussObj.tenantId = ?3")
		})
@Table(name = "t_buss_obj", uniqueConstraints = @UniqueConstraint(columnNames = { "buss_obj_id" }))
public class TBussObj implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@Id
	@NotEmpty
	@Column(name = "buss_obj_id", nullable = false, length = 20)
	private Integer bussObjId;

	/**
	 * 
	 * @generated
	 */
	@NotEmpty
	@Length(max = 100)
	@Column(name = "buss_obj_name", nullable = false, length = 100)
	private String bussObjName;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "created_by", nullable = true, length = 255)
	private Integer createdBy;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "create_dt", nullable = true, length = 19)
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
	@Length(max = 30)
	@Column(name = "buss_function_type", nullable = true, length = 30)
	private String bussFunctionType;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST,
			CascadeType.MERGE }, mappedBy = "tBussObj")
	private Set<TBussAttr> tBussAttrss;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST,
			CascadeType.MERGE }, mappedBy = "tBussObj")
	private Set<TBussEntity> tBussEntitiess;

	/**
	 * 
	 * @generated
	 */
	public TBussObj() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setBussObjId(final Integer bussObjId) {
		this.bussObjId = bussObjId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getBussObjId() {
		return this.bussObjId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setBussObjName(final String bussObjName) {
		this.bussObjName = bussObjName;
	}

	/**
	 * 
	 * @generated
	 */
	public String getBussObjName() {
		return this.bussObjName;
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

	public void setBussFunctionType(final String bussFunctionType) {
		this.bussFunctionType = bussFunctionType;
	}

	/**
	 * 
	 * @generated
	 */
	public String getBussFunctionType() {
		return this.bussFunctionType;
	}

	/**
	 * 
	 * @generated
	 */
	public Set<TBussAttr> getTBussAttrss() {
		return this.tBussAttrss;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTBussAttrss(final Set<TBussAttr> tBussAttrss) {
		this.tBussAttrss = tBussAttrss;
	}

	/**
	 * 
	 * @generated
	 */
	public Set<TBussEntity> getTBussEntitiess() {
		return this.tBussEntitiess;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTBussEntitiess(final Set<TBussEntity> tBussEntitiess) {
		this.tBussEntitiess = tBussEntitiess;
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TBussObj that) {
		setBussObjId(that.getBussObjId());
		setBussObjName(that.getBussObjName());
		setCreatedBy(that.getCreatedBy());
		setCreateDt(that.getCreateDt());
		setUpdatedBy(that.getUpdatedBy());
		setUpdateDt(that.getUpdateDt());
		setTenantId(that.getTenantId());
		setBussFunctionType(that.getBussFunctionType());
	}

	/**
	 * @generated
	 * 
	 */
	@Override
	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = prime * result
				+ ((bussObjId == null) ? 0 : bussObjId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("bussObjId=[").append(bussObjId).append("] ");
		buffer.append("bussObjName=[").append(bussObjName).append("] ");
		buffer.append("createdBy=[").append(createdBy).append("] ");
		buffer.append("createDt=[").append(createDt).append("] ");
		buffer.append("updatedBy=[").append(updatedBy).append("] ");
		buffer.append("updateDt=[").append(updateDt).append("] ");
		buffer.append("tenantId=[").append(tenantId).append("] ");
		buffer.append("bussFunctionType=[").append(bussFunctionType)
				.append("] ");

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
		final TBussObj other = (TBussObj) obj;
		if (bussObjId == null) {
			if (other.bussObjId != null) {
				return false;
			}
		} else if (!bussObjId.equals(other.bussObjId)) {
			return false;
		}
		return true;
	}
}
