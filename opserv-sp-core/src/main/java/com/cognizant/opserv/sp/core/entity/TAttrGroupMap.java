package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;
import org.hibernate.validator.constraints.Length;

/** 
 * JPA class representing TAttrGroupMap 
 * The corresponding mapping table is t_attr_group_map 
 */

@Entity
@Audited
@NamedQueries({
		@NamedQuery(name = "FindAllTAttrGroupMaps", query = "select myTAttrGroupMap from TAttrGroupMap myTAttrGroupMap"),
		@NamedQuery(name = "CountTAttrGroupMaps", query = "Select Count(c) from TAttrGroupMap c"),
		@NamedQuery(name = "FindTAttrGroupMapByAttrName", query = "select myTAttrGroupMap from TAttrGroupMap myTAttrGroupMap where myTAttrGroupMap.tAttrDef.attrName like ?1"),
		@NamedQuery(name = "FindTAttrGroupMapByAttrNameAndTAttrGroup", query = "select myTAttrGroupMap from TAttrGroupMap myTAttrGroupMap where myTAttrGroupMap.tAttrDef.attrName like ?1 and myTAttrGroupMap.tAttrGroup.groupName = ?2 and myTAttrGroupMap.activeFlag = 'Y' "),
		@NamedQuery(name = "FindTAttrGroupMapByTAttrGroup", query = "select myTAttrGroupMap from TAttrGroupMap myTAttrGroupMap where myTAttrGroupMap.tAttrGroup = ?1 "),
		@NamedQuery(name = "CountTAttrGroupMapsByTAttrGroup", query = "select Count(*) from TAttrGroupMap myTAttrGroupMap where myTAttrGroupMap.tAttrGroup = ?1 "),
		@NamedQuery(name = "FindTAttrGroupMapByTAttrDef", query = "select myTAttrGroupMap from TAttrGroupMap myTAttrGroupMap where myTAttrGroupMap.tAttrDef = ?1 "),
		@NamedQuery(name = "CountTAttrGroupMapsByTAttrDef", query = "select Count(*) from TAttrGroupMap myTAttrGroupMap where myTAttrGroupMap.tAttrDef = ?1 "),
		@NamedQuery(name = "findTAttrGroupMapsByAttrId", query = "select myTAttrGroupMap from TAttrGroupMap myTAttrGroupMap where myTAttrGroupMap.tAttrGroupMapId.attrId = ?1 "),
		@NamedQuery(name = "findTAttrGroupMapsByAttrIdList", query = "select myTAttrGroupMap from TAttrGroupMap myTAttrGroupMap where myTAttrGroupMap.tAttrGroupMapId.attrId IN ?1 "),
		@NamedQuery(name = "findTAttrGroupMapsByAttrgroupId", query = "select myTAttrGroupMap from TAttrGroupMap myTAttrGroupMap where myTAttrGroupMap.tAttrGroupMapId.attrGroupId = ?1 and  myTAttrGroupMap.tenantId=?2 "),
		@NamedQuery(name = "findTAttrGroupMapsByAttrgroupIdList", query = "select myTAttrGroupMap from TAttrGroupMap myTAttrGroupMap where myTAttrGroupMap.tAttrGroupMapId.attrGroupId IN ?1 "),
		@NamedQuery(name = "FindTAttrGroupMapsByTAttrGroupAndTAttrDef", query = "select myTAttrGroupMap from TAttrGroupMap myTAttrGroupMap where myTAttrGroupMap.tAttrGroup = ?1 and myTAttrGroupMap.tAttrDef = ?2 "),
		@NamedQuery(name = "findGroupMapByAttrId", query = "select myTAttrGroupMap.tAttrGroupMapId from TAttrGroupMap myTAttrGroupMap where " +
				"myTAttrGroupMap.tAttrGroupMapId.attrId = ?1 and  myTAttrGroupMap.tenantId=?2 and myTAttrGroupMap.activeFlag = 'Y'"),
		})
@Table(name = "t_attr_group_map")
public class TAttrGroupMap implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@EmbeddedId
	@Valid
	private TAttrGroupMapId tAttrGroupMapId;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "order_seq", nullable = true, length = 255)
	private Integer orderSeq;

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
	@Length(max = 50)
	@Column(name = "display_name", nullable = true, length = 50)
	private String displayName;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "unique_flag", nullable = true, length = 1)
	private Character uniqueFlag;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "mandatory_flag", nullable = true, length = 1)
	private Character mandatoryFlag;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "mask_value_flag", nullable = true, length = 1)
	private Character maskValueFlag;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "editable_flag", nullable = true, length = 1)
	private Character editableFlag;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "visible_flag", nullable = true, length = 1)
	private Character visibleFlag;
	
	/**
	 * 
	 * @generated
	 */
	@Column(name = "sort_flag", nullable = true, length = 1)
	private Character sortFlag;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "srch_flag", nullable = true, length = 1)
	private Character srchFlag;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 300)
	@Column(name = "tooltip", nullable = true, length = 300)
	private String tooltip;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 50)
	@Column(name = "attr_desc", nullable = true, length = 50)
	private String attrDesc;

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
	@JoinColumn(name = "attr_group_id", referencedColumnName = "attr_group_id", nullable = false, insertable = false, updatable = false)
	@Valid
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private TAttrGroup tAttrGroup;

	@ManyToOne
	@JoinColumn(name = "attr_id", referencedColumnName = "attr_id", nullable = false, insertable = false, updatable = false)
	@Valid
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private TAttrDef tAttrDef;

	/**
	 *
	 * @generated
	 */
	public TAttrGroupMap() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setTAttrGroupMapId(final TAttrGroupMapId tAttrGroupMapId) {
		this.tAttrGroupMapId = tAttrGroupMapId;
	}

	/**
	 * 
	 * @generated
	 */
	public TAttrGroupMapId getTAttrGroupMapId() {
		return this.tAttrGroupMapId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setOrderSeq(final Integer orderSeq) {
		this.orderSeq = orderSeq;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getOrderSeq() {
		return this.orderSeq;
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

	public void setDisplayName(final String displayName) {
		this.displayName = displayName;
	}

	/**
	 * 
	 * @generated
	 */
	public String getDisplayName() {
		return this.displayName;
	}

	/**
	 * 
	 * @generated
	 */

	public void setUniqueFlag(final Character uniqueFlag) {
		this.uniqueFlag = uniqueFlag;
	}

	/**
	 * 
	 * @generated
	 */
	public Character getUniqueFlag() {
		return this.uniqueFlag;
	}

	/**
	 * 
	 * @generated
	 */

	public void setMandatoryFlag(final Character mandatoryFlag) {
		this.mandatoryFlag = mandatoryFlag;
	}

	/**
	 * 
	 * @generated
	 */
	public Character getMandatoryFlag() {
		return this.mandatoryFlag;
	}

	/**
	 * 
	 * @generated
	 */

	public void setMaskValueFlag(final Character maskValueFlag) {
		this.maskValueFlag = maskValueFlag;
	}

	/**
	 * 
	 * @generated
	 */
	public Character getMaskValueFlag() {
		return this.maskValueFlag;
	}

	/**
	 * 
	 * @generated
	 */

	public void setEditableFlag(final Character editableFlag) {
		this.editableFlag = editableFlag;
	}

	/**
	 * 
	 * @generated
	 */
	public Character getEditableFlag() {
		return this.editableFlag;
	}

	/**
	 * 
	 * @generated
	 */

	public void setVisibleFlag(final Character visibleFlag) {
		this.visibleFlag = visibleFlag;
	}

	/**
	 * 
	 * @generated
	 */
	public Character getVisibleFlag() {
		return this.visibleFlag;
	}

	/**
	 * 
	 * @generated
	 */

	public void setTooltip(final String tooltip) {
		this.tooltip = tooltip;
	}

	/**
	 * 
	 * @generated
	 */
	public String getTooltip() {
		return this.tooltip;
	}

	/**
	 * 
	 * @generated
	 */

	public void setAttrDesc(final String attrDesc) {
		this.attrDesc = attrDesc;
	}

	/**
	 * 
	 * @generated
	 */
	public String getAttrDesc() {
		return this.attrDesc;
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
	public TAttrGroup getTAttrGroup() {
		return this.tAttrGroup;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTAttrGroup(final TAttrGroup tAttrGroup) {
		this.tAttrGroup = tAttrGroup;
		/*if (this.tAttrGroup != null && this.tAttrGroup.getAttrGroupId() != null) {

			//this.tAttrGroupMapId.setAttrGroupId(this.tAttrGroup.getAttrGroupId());
			this.tAttrGroupMapId.settAttrGroup(this.tAttrGroup);

		}
*/
	}

	/**
	 * 
	 * @generated
	 */
	public TAttrDef getTAttrDef() {
		return this.tAttrDef;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTAttrDef(final TAttrDef tAttrDef) {
		this.tAttrDef = tAttrDef;
		/*if (this.tAttrDef != null && this.tAttrDef.getAttrId() != null) {

			//this.tAttrGroupMapId.setAttrId(this.tAttrDef.getAttrId());
			this.tAttrGroupMapId.settAttrDef(this.tAttrDef);

		}*/

	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TAttrGroupMap that) {
		setTAttrGroupMapId(that.getTAttrGroupMapId());
		setOrderSeq(that.getOrderSeq());
		setActiveFlag(that.getActiveFlag());
		setDisplayName(that.getDisplayName());
		setUniqueFlag(that.getUniqueFlag());
		setMandatoryFlag(that.getMandatoryFlag());
		setMaskValueFlag(that.getMaskValueFlag());
		setEditableFlag(that.getEditableFlag());
		setVisibleFlag(that.getVisibleFlag());
		setTooltip(that.getTooltip());
		setAttrDesc(that.getAttrDesc());
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
		result = prime * result + ((tAttrGroupMapId == null) ? 0 : tAttrGroupMapId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("tAttrGroupMapId=[").append(tAttrGroupMapId).append("] ");
		buffer.append("orderSeq=[").append(orderSeq).append("] ");
		buffer.append("activeFlag=[").append(activeFlag).append("] ");
		buffer.append("displayName=[").append(displayName).append("] ");
		buffer.append("uniqueFlag=[").append(uniqueFlag).append("] ");
		buffer.append("mandatoryFlag=[").append(mandatoryFlag).append("] ");
		buffer.append("maskValueFlag=[").append(maskValueFlag).append("] ");
		buffer.append("editableFlag=[").append(editableFlag).append("] ");
		buffer.append("visibleFlag=[").append(visibleFlag).append("] ");
		buffer.append("tooltip=[").append(tooltip).append("] ");
		buffer.append("attrDesc=[").append(attrDesc).append("] ");
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
		final TAttrGroupMap other = (TAttrGroupMap) obj;
		if (tAttrGroupMapId == null) {
			if (other.tAttrGroupMapId != null) {
				return false;
			}
		} else if (!tAttrGroupMapId.equals(other.tAttrGroupMapId)) {
			return false;
		}
		return true;
	}

	public Character getSortFlag() {
		return sortFlag;
	}

	public void setSortFlag(Character sortFlag) {
		this.sortFlag = sortFlag;
	}

	/**
	 * @return the srchFlag
	 */
	public Character getSrchFlag() {
		return srchFlag;
	}

	/**
	 * @param srchFlag the srchFlag to set
	 */
	public void setSrchFlag(Character srchFlag) {
		this.srchFlag = srchFlag;
	}
	
}
