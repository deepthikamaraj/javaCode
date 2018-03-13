package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.QueryHint;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/** 
 * JPA class representing TAttrGroup 
 * The corresponding mapping table is t_attr_group 
 */

@Entity
@Audited
@NamedQueries({
		@NamedQuery(name = "FindAllTAttrGroups", query = "select myTAttrGroup from TAttrGroup myTAttrGroup"),

		@NamedQuery(name = "findTAttrDefByAlgTemplId", query = "Select myTAttrDef from TAttrDef myTAttrDef, TAttrGroup tAttrGroup ,TAttrGroupMap tAttrGroupMapss where tAttrGroup.attrGroupId = tAttrGroupMapss.tAttrGroupMapId.attrGroupId and tAttrGroupMapss.tAttrGroupMapId.attrId = myTAttrDef.attrId and tAttrGroupMapss.activeFlag = 'Y' and tAttrGroup.activeFlag= 'Y' and myTAttrDef.activeFlag= 'Y' and tAttrGroup.tBussObjTmpl.tmplId=?1 and tAttrGroup.tenantId=?2 order by myTAttrDef.displayName asc"),
		@NamedQuery(name = "CountTAttrGroups", query = "Select Count(c) from TAttrGroup c"),
		@NamedQuery(name = "FindTAttrGroupByGroupName", query = "select myTAttrGroup from TAttrGroup myTAttrGroup where myTAttrGroup.groupName like ?1 "),

		@NamedQuery(name = "FindTAttrGroupByGroupNameAndTBussObjTmpl", query = "select myTAttrGroup from TAttrGroup myTAttrGroup where myTAttrGroup.groupName like ?1 and myTAttrGroup.tBussObjTmpl.tmplId = ?2 and myTAttrGroup.activeFlag = ?3 and myTAttrGroup.tenantId = ?4"),
		
		@NamedQuery(name = "FindTAttrGroupByTBussObjTmpl", query = "select myTAttrGroup from TAttrGroup myTAttrGroup where myTAttrGroup.tBussObjTmpl = ?1", hints = {
				@QueryHint(name = "org.hibernate.cacheable", value = "true"),
				@QueryHint(name = "org.hibernate.cacheRegion", value = "query.namedQueryCache")

		}),
		/*@NamedQuery(name = "FindTAttrGroupByTBussObjTmpl", query = "select myTAttrGroup from TAttrGroup myTAttrGroup where myTAttrGroup.tBussObjTmpl = ?1 "),*/
		@NamedQuery(name = "CountTAttrGroupsByTBussObjTmpl", query = "select Count(myTAttrGroup) from TAttrGroup myTAttrGroup where myTAttrGroup.tBussObjTmpl = ?1 "),
		@NamedQuery(name = "FindTAttrGroupByTmplId", query = "select myTAttrGroup from TAttrGroup myTAttrGroup where  myTAttrGroup.tBussObjTmpl.tmplId = ?1 and myTAttrGroup.activeFlag =?2 and myTAttrGroup.tenantId = ?3"),
		@NamedQuery(name = "FindNonActiveTAttrGroupByTBussObjTmpl", query = "select myTAttrGroup from TAttrGroup myTAttrGroup where myTAttrGroup.defFlag='N' and myTAttrGroup.tBussObjTmpl = ?1  "),
		@NamedQuery(name = "FindActiveTAttrGroupByTBussObjTmpl", query = "select myTAttrGroup.attrGroupId, myTAttrGroup.groupName, myTAttrGroup.defFlag, " +
				" myTAttrDef.attrId, myTAttrDef.attrName, myTAttrDef.displayName, " +
				" myTAttrDef.dynAttrFlag, myTAttrDef.attrDataTypeId," +
				" myTAttrDef.entityId " +
				" from TAttrGroup myTAttrGroup INNER JOIN myTAttrGroup.tAttrGroupMapss myTAttrGrpMap " +
				" INNER JOIN myTAttrGroup.tAttrDefss myTAttrDef " +
				" where myTAttrGroup.tBussObjTmpl.tmplId = ?1 and myTAttrGroup.tenantId = ?2 and myTAttrGroup.activeFlag = ?3 and myTAttrDef.activeFlag = ?3 " +
				" and myTAttrGrpMap.activeFlag = ?3 and myTAttrGrpMap.tAttrGroupMapId.attrId = myTAttrDef.attrId "),
		@NamedQuery(name = "FindActiveGroupsByTBussObjTmpl", query = " select myTAttrGroup.attrGroupId, myTAttrGroup.groupName, myTAttrGroup.defFlag from TAttrGroup myTAttrGroup  " +
				" where myTAttrGroup.tBussObjTmpl.tmplId = ?1  and myTAttrGroup.activeFlag = ?2 and myTAttrGroup.tenantId = ?3 " +
				" and myTAttrGroup.attrGroupId NOT IN ( select myTAttrGrpMap.tAttrGroupMapId.attrGroupId from myTAttrGroup.tAttrGroupMapss myTAttrGrpMap where myTAttrGrpMap.activeFlag = ?2 )  ")
		})
@Table(name = "t_attr_group", uniqueConstraints = @UniqueConstraint(columnNames = { "attr_group_id" }))
public class TAttrGroup implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "attr_group_id", nullable = false, length = 255)
	private Integer attrGroupId;

	/**
	 * 
	 * @generated
	 */
	@NotEmpty
	@Length(max = 50)
	@Column(name = "group_name", nullable = false, length = 50)
	private String groupName;

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
	@Column(name = "order_seq", nullable = true, length = 255)
	private Byte orderSeq;

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
	@JoinColumn(name = "tmpl_id", referencedColumnName = "tmpl_id", nullable = false, insertable = true, updatable = true)
	@Valid
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private TBussObjTmpl tBussObjTmpl;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tAttrGroup")
	@Fetch(FetchMode.SUBSELECT)
	@Audited
	private Set<TAttrGroupMap> tAttrGroupMapss;


	
	@OneToMany
	@Fetch(FetchMode.SUBSELECT)
    @JoinTable(name = "t_attr_group_map",
        joinColumns = @JoinColumn(name="attr_group_id"),
        inverseJoinColumns = @JoinColumn(name="attr_id")
    )
	@Audited
	private List<TAttrDef> tAttrDefss;
	


	/**
	 *
	 * @generated
	 */
	public TAttrGroup() {
	}
	
	public List<TAttrDef> getTAttrDefss() {
		return tAttrDefss;
	}
	
	public void setTAttrDefss(List<TAttrDef> tAttrDefss) {
		this.tAttrDefss = tAttrDefss;
	}

	/**
	 * 
	 * @generated
	 */

	public void setAttrGroupId(final Integer attrGroupId) {
		this.attrGroupId = attrGroupId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getAttrGroupId() {
		return this.attrGroupId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setGroupName(final String groupName) {
		this.groupName = groupName;
	}

	/**
	 * 
	 * @generated
	 */
	public String getGroupName() {
		return this.groupName;
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

	public void setOrderSeq(final Byte orderSeq) {
		this.orderSeq = orderSeq;
	}

	/**
	 * 
	 * @generated
	 */
	public Byte getOrderSeq() {
		return this.orderSeq;
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
	public TBussObjTmpl getTBussObjTmpl() {
		return this.tBussObjTmpl;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTBussObjTmpl(final TBussObjTmpl tBussObjTmpl) {
		this.tBussObjTmpl = tBussObjTmpl;

	}

	/**
	 * 
	 * @generated
	 */
	public Set<TAttrGroupMap> getTAttrGroupMapss() {
		return this.tAttrGroupMapss;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTAttrGroupMapss(final Set<TAttrGroupMap> tAttrGroupMapss) {
		this.tAttrGroupMapss = tAttrGroupMapss;
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TAttrGroup that) {
		setAttrGroupId(that.getAttrGroupId());
		setGroupName(that.getGroupName());
		setEffStartDt(that.getEffStartDt());
		setEffEndDt(that.getEffEndDt());
		setOrderSeq(that.getOrderSeq());
		setDefFlag(that.getDefFlag());
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
		result = prime * result + ((attrGroupId == null) ? 0 : attrGroupId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("attrGroupId=[").append(attrGroupId).append("] ");
		buffer.append("groupName=[").append(groupName).append("] ");
		buffer.append("effStartDt=[").append(effStartDt).append("] ");
		buffer.append("effEndDt=[").append(effEndDt).append("] ");
		buffer.append("orderSeq=[").append(orderSeq).append("] ");
		buffer.append("defFlag=[").append(defFlag).append("] ");
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
		final TAttrGroup other = (TAttrGroup) obj;
		if (attrGroupId == null) {
			if (other.attrGroupId != null) {
				return false;
			}
		} else if (!attrGroupId.equals(other.attrGroupId)) {
			return false;
		}
		return true;
	}

	public Set<TAttr> getTAttrss() {
		// TODO Auto-generated method stub
		return null;
	}
}
