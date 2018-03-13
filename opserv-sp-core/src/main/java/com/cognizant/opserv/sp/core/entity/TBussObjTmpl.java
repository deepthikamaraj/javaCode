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
import javax.persistence.QueryHint;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.hibernate.envers.RelationTargetAuditMode;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/** 
 * JPA class representing TBussObjTmpl 
 * The corresponding mapping table is t_buss_obj_tmpl 
 */
/*@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Cacheable(value = true)*/
@Entity
@Audited
@NamedQueries({
		@NamedQuery(name = "FindAllTBussObjTmpls", query = "select myTBussObjTmpl from TBussObjTmpl myTBussObjTmpl"),
		@NamedQuery(name = "CountTBussObjTmpls", query = "Select Count(c) from TBussObjTmpl c"),
		
		@NamedQuery(name = "FindTBussObjTmplByTBussObj", query = "select myTBussObjTmpl from TBussObjTmpl myTBussObjTmpl where myTBussObjTmpl.tBussObj = ?1", hints = {
				@QueryHint(name = "org.hibernate.cacheable", value = "true"),
				@QueryHint(name = "org.hibernate.cacheRegion", value = "query.namedQueryCache")

		}),
		
		/*@NamedQuery(name = "FindTBussObjTmplByTBussObj", query = "select myTBussObjTmpl from TBussObjTmpl myTBussObjTmpl where myTBussObjTmpl.tBussObj = ?1 "),*/
		@NamedQuery(name = "CountTBussObjTmplsByTBussObj", query = "select Count(myTBussObjTmpl) from TBussObjTmpl myTBussObjTmpl where myTBussObjTmpl.tBussObj = ?1 "),
		@NamedQuery(name = "FindTBussObjTmplByTBussObjId", query = "select myTBussObjTmpl from TBussObjTmpl myTBussObjTmpl where myTBussObjTmpl.tBussObj.bussObjId = ?1 and myTBussObjTmpl.tenantId =?2 "),
		@NamedQuery(name = "FindActiveTBussObjTmplByTBussObj", query = "select myTBussObjTmpl from TBussObjTmpl myTBussObjTmpl where myTBussObjTmpl.defFlag = 'Y' and myTBussObjTmpl.tBussObj = ?1"),
		@NamedQuery(name = "FindDefTBussObjTmpl", query = "select myTBussObjTmpl from TBussObjTmpl myTBussObjTmpl where myTBussObjTmpl.tBussObj.bussObjId in " +
				"(select myTBussObj.bussObjId from TBussObj myTBussObj where myTBussObj.bussFunctionType = ?1 ) " +
				"and myTBussObjTmpl.defFlag = ?2 and myTBussObjTmpl.activeFlag = ?3 and myTBussObjTmpl.effStartDt <= ?4 " +
				"and (myTBussObjTmpl.effEndDt >= ?5 or myTBussObjTmpl.effEndDt is null)" ),
		// added to get buss_obj_id from tmpl_id	
		@NamedQuery(name = "FindBussObjIdByTmplId", query = "select myTBussObjTmpl from TBussObjTmpl myTBussObjTmpl where myTBussObjTmpl.activeFlag ='Y' and myTBussObjTmpl.tmplId = ?1 and myTBussObjTmpl.tenantId =?2 "),		
		})
@Table(name = "t_buss_obj_tmpl", uniqueConstraints = @UniqueConstraint(columnNames = { "tmpl_id" }))
public class TBussObjTmpl implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "tmpl_id", nullable = false, length = 255)
	private Integer tmplId;

	/**
	 * 
	 * @generated
	 */
	@NotEmpty
	@Length(max = 75)
	@Column(name = "tmpl_name", nullable = false, length = 75)
	private String tmplName;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 150)
	@Column(name = "tmpl_desc", nullable = true, length = 150)
	private String tmplDesc;

	/**
	 * 
	 * @generated
	 */
	@NotNull
	@Column(name = "def_flag", nullable = false, length = 1)
	private Character defFlag;

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
	
	@Column(name = "active_flag", nullable = true, length = 1)
	private Character activeFlag;

	@ManyToOne
	@JoinColumn(name = "buss_obj_id", referencedColumnName = "buss_obj_id", nullable = false, insertable = true, updatable = true)
	@Valid
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private TBussObj tBussObj;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tBussObjTmpl")
	@Audited
	private Set<TAttrGroup> tAttrGroupss;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tBussObjTmpl")
	@NotAudited
	private Set<TAlgmntTmpl> tAlgmntTmplss;

	/**
	 *
	 * @generated
	 */
	public TBussObjTmpl() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setTmplId(final Integer tmplId) {
		this.tmplId = tmplId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getTmplId() {
		return this.tmplId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setTmplName(final String tmplName) {
		this.tmplName = tmplName;
	}

	/**
	 * 
	 * @generated
	 */
	public String getTmplName() {
		return this.tmplName;
	}

	/**
	 * 
	 * @generated
	 */

	public void setTmplDesc(final String tmplDesc) {
		this.tmplDesc = tmplDesc;
	}

	/**
	 * 
	 * @generated
	 */
	public String getTmplDesc() {
		return this.tmplDesc;
	}

	/**
	 * 
	 * @generated
	 */

	public void setDefFlag(final Character defFlag) {
		this.defFlag = defFlag;
	}

	/**
	 * 
	 * @generated
	 */
	public Character getDefFlag() {
		return this.defFlag;
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
	
	public Character getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(Character activeFlag) {
		this.activeFlag = activeFlag;
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
	 * 
	 * @generated
	 */
	public Set<TAttrGroup> getTAttrGroupss() {
		return this.tAttrGroupss;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTAttrGroupss(final Set<TAttrGroup> tAttrGroupss) {
		this.tAttrGroupss = tAttrGroupss;
	}

	/**
	 * 
	 * @generated
	 */
	public Set<TAlgmntTmpl> getTAlgmntTmplss() {
		return this.tAlgmntTmplss;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTAlgmntTmplss(final Set<TAlgmntTmpl> tAlgmntTmplss) {
		this.tAlgmntTmplss = tAlgmntTmplss;
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TBussObjTmpl that) {
		setTmplId(that.getTmplId());
		setTmplName(that.getTmplName());
		setTmplDesc(that.getTmplDesc());
		setDefFlag(that.getDefFlag());
		setEffStartDt(that.getEffStartDt());
		setEffEndDt(that.getEffEndDt());
		setCreatedBy(that.getCreatedBy());
		setCreateDt(that.getCreateDt());
		setUpdatedBy(that.getUpdatedBy());
		setUpdateDt(that.getUpdateDt());
		setTenantId(that.getTenantId());
		setActiveFlag(that.getActiveFlag());
	}

	/**
	 * @generated
	 * 
	 */
	@Override
	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = prime * result + ((tmplId == null) ? 0 : tmplId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("tmplId=[").append(tmplId).append("] ");
		buffer.append("tmplName=[").append(tmplName).append("] ");
		buffer.append("tmplDesc=[").append(tmplDesc).append("] ");
		buffer.append("defFlag=[").append(defFlag).append("] ");
		buffer.append("effStartDt=[").append(effStartDt).append("] ");
		buffer.append("effEndDt=[").append(effEndDt).append("] ");
		buffer.append("createdBy=[").append(createdBy).append("] ");
		buffer.append("createDt=[").append(createDt).append("] ");
		buffer.append("updatedBy=[").append(updatedBy).append("] ");
		buffer.append("updateDt=[").append(updateDt).append("] ");
		buffer.append("tenantId=[").append(tenantId).append("] ");
		buffer.append("activeFlag=[").append(activeFlag).append("] ");

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
		final TBussObjTmpl other = (TBussObjTmpl) obj;
		if (tmplId == null) {
			if (other.tmplId != null) {
				return false;
			}
		} else if (!tmplId.equals(other.tmplId)) {
			return false;
		}
		return true;
	}
}
