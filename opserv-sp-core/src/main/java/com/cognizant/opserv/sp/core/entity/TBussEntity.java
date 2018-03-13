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
import javax.persistence.QueryHint;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/** 
 * JPA class representing TBussEntity 
 * The corresponding mapping table is t_buss_entity 
 */
/*@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Cacheable(value = true)*/
@Entity
@NamedQueries({
	@NamedQuery(name = "FindTBussEntityByTBussObjWithBaseFlag", query = "select myTBussEntity from TBussEntity myTBussEntity where myTBussEntity.tBussObj = ?1 and myTBussEntity.tenantId =?2 and myTBussEntity.baseEntityFlag='Y' "),
		@NamedQuery(name = "FindAllTBussEntitys", query = "select myTBussEntity from TBussEntity myTBussEntity",hints = {
				@QueryHint(name = "org.hibernate.cacheable", value = "true"),
				@QueryHint(name = "org.hibernate.cacheRegion", value = "query.namedQueryCache")		
		}),
		@NamedQuery(name = "FindAllTBussEntitysByTenantId", query = "select myTBussEntity from TBussEntity myTBussEntity where myTBussEntity.tenantId =?1",hints={
				@QueryHint(name = "org.hibernate.cacheable", value = "true"),
				@QueryHint(name = "org.hibernate.cacheRegion", value = "query.namedQueryCache")	
		}),
		@NamedQuery(name = "CountTBussEntitys", query = "Select Count(c) from TBussEntity c"),
		@NamedQuery(name = "FindTBussEntityByTBussObj", query = "select myTBussEntity from TBussEntity myTBussEntity where myTBussEntity.tBussObj = ?1 "),
		@NamedQuery(name = "CountTBussEntitysByTBussObj", query = "select Count(myTBussEntity) from TBussEntity myTBussEntity where myTBussEntity.tBussObj = ?1 "),
		@NamedQuery(name = "GetTBussEntitysByPrnId", query = "select myTBussEntity from TBussEntity myTBussEntity where myTBussEntity.prnEntityId = ?1 "),
		@NamedQuery(name = "FindAllTBussEntitysByDrvFlag", query = "select myTBussEntity from TBussEntity myTBussEntity where myTBussEntity.drvEntityFlag = 'Y'"),
		@NamedQuery(name = "FindAllTBussEntitysByEntityName", query = "select myTBussEntity from TBussEntity myTBussEntity where myTBussEntity.entityName = ?1 and myTBussEntity.tenantId = ?2"),
		@NamedQuery(name = "FindTBussEntityNameByBussFuncType", query = "SELECT myTBussEntity.entityName FROM TBussEntity myTBussEntity WHERE myTBussEntity.entityType = ?1 AND myTBussEntity.tenantId = ?2"),
		@NamedQuery(name = "FindAllTBussEntitysByBussFuncType", query = "select myTBussEntity from TBussEntity myTBussEntity where myTBussEntity.tBussObj.bussObjName = ?1 and myTBussEntity.tBussObj.tenantId = ?2 and myTBussEntity.tBussObj.bussFunctionType = ?3")})
	    
@Table(name = "t_buss_entity", uniqueConstraints = @UniqueConstraint(columnNames = { "entity_id" }))
public class TBussEntity implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "entity_id", nullable = false, length = 255)
	private Integer entityId;

	/**
	 * 
	 * @generated
	 */
	@NotEmpty
	@Length(max = 15)
	@Column(name = "entity_type", nullable = false, length = 15)
	private String entityType;

	/**
	 * 
	 * @generated
	 */
	@NotEmpty
	@Length(max = 75)
	@Column(name = "entity_name", nullable = false, length = 75)
	private String entityName;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 200)
	@Column(name = "entity_alias_name", nullable = true, length = 200)
	private String entityAliasName;

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
	@Column(name = "base_entity_flag", nullable = true, length = 1)
	private Character baseEntityFlag;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 300)
	@Column(name = "ref_cond", nullable = true, length = 300)
	private String refCond;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "prn_entity_id", nullable = true, length = 255)
	private Integer prnEntityId;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "drv_entity_flag", nullable = true, length = 1)
	private Character drvEntityFlag;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "buss_obj_id", referencedColumnName = "buss_obj_id", nullable = false, insertable = true, updatable = true)
	@Valid
	private TBussObj tBussObj;


	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tBussEntity")
	private Set<TBussAttr> tBussAttrss;
	
	
	@OneToMany(cascade = { CascadeType.REFRESH }, mappedBy = "tBussEntity")
	private Set<TAttrDef> tAttrDefss;


	/**
	 *
	 * @generated
	 */
	public TBussEntity() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setEntityId(final Integer entityId) {
		this.entityId = entityId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getEntityId() {
		return this.entityId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setEntityType(final String entityType) {
		this.entityType = entityType;
	}

	/**
	 * 
	 * @generated
	 */
	public String getEntityType() {
		return this.entityType;
	}

	/**
	 * 
	 * @generated
	 */

	public void setEntityName(final String entityName) {
		this.entityName = entityName;
	}

	/**
	 * 
	 * @generated
	 */
	public String getEntityName() {
		return this.entityName;
	}

	/**
	 * 
	 * @generated
	 */

	public void setEntityAliasName(final String entityAliasName) {
		this.entityAliasName = entityAliasName;
	}

	/**
	 * 
	 * @generated
	 */
	public String getEntityAliasName() {
		return this.entityAliasName;
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

	public void setBaseEntityFlag(final Character baseEntityFlag) {
		this.baseEntityFlag = baseEntityFlag;
	}

	/**
	 * 
	 * @generated
	 */
	public Character getBaseEntityFlag() {
		return this.baseEntityFlag;
	}

	/**
	 * 
	 * @generated
	 */

	public void setRefCond(final String refCond) {
		this.refCond = refCond;
	}

	/**
	 * 
	 * @generated
	 */
	public String getRefCond() {
		return this.refCond;
	}

	/**
	 * 
	 * @generated
	 */

	public void setPrnEntityId(final Integer prnEntityId) {
		this.prnEntityId = prnEntityId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getPrnEntityId() {
		return this.prnEntityId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setDrvEntityFlag(final Character drvEntityFlag) {
		this.drvEntityFlag = drvEntityFlag;
	}

	/**
	 * 
	 * @generated
	 */
	public Character getDrvEntityFlag() {
		return this.drvEntityFlag;
	}

	/**
	 * 
	 * @generated
	 */
	public TBussObj getTBussObj() {
		return this.tBussObj;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTBussObj(final TBussObj tBussObj) {
		this.tBussObj = tBussObj;

	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TBussEntity that) {
		setEntityId(that.getEntityId());
		setEntityType(that.getEntityType());
		setEntityName(that.getEntityName());
		setEntityAliasName(that.getEntityAliasName());
		setCreatedBy(that.getCreatedBy());
		setCreateDt(that.getCreateDt());
		setUpdatedBy(that.getUpdatedBy());
		setUpdateDt(that.getUpdateDt());
		setTenantId(that.getTenantId());
		setBaseEntityFlag(that.getBaseEntityFlag());
		setRefCond(that.getRefCond());
		setPrnEntityId(that.getPrnEntityId());
		setDrvEntityFlag(that.getDrvEntityFlag());
	}

	/**
	 * @generated
	 * 
	 */
	@Override
	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = prime * result + ((entityId == null) ? 0 : entityId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("entityId=[").append(entityId).append("] ");
		buffer.append("entityType=[").append(entityType).append("] ");
		buffer.append("entityName=[").append(entityName).append("] ");
		buffer.append("entityAliasName=[").append(entityAliasName).append("] ");
		buffer.append("createdBy=[").append(createdBy).append("] ");
		buffer.append("createDt=[").append(createDt).append("] ");
		buffer.append("updatedBy=[").append(updatedBy).append("] ");
		buffer.append("updateDt=[").append(updateDt).append("] ");
		buffer.append("tenantId=[").append(tenantId).append("] ");
		buffer.append("baseEntityFlag=[").append(baseEntityFlag).append("] ");
		buffer.append("refCond=[").append(refCond).append("] ");
		buffer.append("prnEntityId=[").append(prnEntityId).append("] ");
		buffer.append("drvEntityFlag=[").append(drvEntityFlag).append("] ");

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
		final TBussEntity other = (TBussEntity) obj;
		if (entityId == null) {
			if (other.entityId != null) {
				return false;
			}
		} else if (!entityId.equals(other.entityId)) {
			return false;
		}
		return true;
	}
}
