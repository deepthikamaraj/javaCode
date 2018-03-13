package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
 * JPA class representing TPrdAttr 
 * The corresponding mapping table is t_prd_attr 
 */

@Entity
@Audited
@NamedQueries({
		@NamedQuery(name = "FindAllTPrdAttrs", query = "select myTPrdAttr from TPrdAttr myTPrdAttr"),
		@NamedQuery(name = "CountTPrdAttrs", query = "Select Count(c) from TPrdAttr c"),
		@NamedQuery(name = "FindTPrdAttrByTPrd", query = "select myTPrdAttr from TPrdAttr myTPrdAttr where myTPrdAttr.tPrd = ?1 "),
		@NamedQuery(name = "FindTPrdAttrByPrdAttrId", query = "select myTPrdAttr from TPrdAttr myTPrdAttr where myTPrdAttr.tPrdAttrId = ?1 "),
		@NamedQuery(name = "FindPrdExtAttrByPrdIdAndAttrList", query = "select myTPrdAttr" +
				" from TPrdAttr myTPrdAttr" +
				" where myTPrdAttr.tPrdAttrId IN ?1"),
		@NamedQuery(name = "CountTPrdAttrsByTPrd", query = "select Count(*) from TPrdAttr myTPrdAttr where myTPrdAttr.tPrd = ?1 "),
		@NamedQuery(name = "FindTPrdAttrByTAttrDef", query = "select myTPrdAttr from TPrdAttr myTPrdAttr where myTPrdAttr.tAttrDef = ?1 "),
		@NamedQuery(name = "FindTPrdAttrByTAttGrpMap", query = "select myTAttrGroupMap.displayName, myTPrdAttr.attrValue from TAlgmntTmpl myTAlgmntTmpl, TAttrGroup myTAttrGroup, TAttrGroupMap myTAttrGroupMap, TPrdAttr myTPrdAttr where myTAlgmntTmpl.tAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId =?1 AND myTAlgmntTmpl.tAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId =?2 AND myTAlgmntTmpl.tAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId = ?3 AND myTAlgmntTmpl.tBussObjTmpl.tmplId = myTAttrGroup.tBussObjTmpl.tmplId AND myTAttrGroup.attrGroupId = myTAttrGroupMap.tAttrGroupMapId.attrGroupId AND myTAttrGroupMap.tAttrGroupMapId.attrId = myTPrdAttr.tPrdAttrId.attrId AND myTPrdAttr.tPrdAttrId.prdId= ?4 AND myTPrdAttr.tenantId=?5 "),
		@NamedQuery(name = "GetTPrdAttrById", query = "select myTPrdAttr from  TPrdAttr myTPrdAttr where myTPrdAttr.tPrdAttrId.prdId=?1 AND myTPrdAttr.tenantId=?2"),
		@NamedQuery(name = "FindTPrdAttrByPrdId", query = "select myTPrdAttr from  TPrdAttr myTPrdAttr where myTPrdAttr.tPrdAttrId.prdId=?1  AND myTPrdAttr.tPrdAttrId.attrId=?2 AND myTPrdAttr.tenantId=?3"),
		@NamedQuery(name = "GetTPrdAttrByProdIds", query = "select myTPrdAttr from  TPrdAttr myTPrdAttr where myTPrdAttr.tPrdAttrId.prdId IN (?1) AND myTPrdAttr.tenantId=?2"),
		@NamedQuery(name = "CountTPrdAttrsByTAttrDef", query = "select Count(*) from TPrdAttr myTPrdAttr where myTPrdAttr.tAttrDef = ?1 "),
		@NamedQuery(name = "GetTPrdAttrByAttrId", query = "select myTprdAttr from  TPrdAttr myTprdAttr where myTprdAttr.tAttrDef.attrId=?1 AND myTprdAttr.attrValue=?2 AND myTprdAttr.tenantId=?3 AND myTprdAttr.tPrdAttrId.prdId <> ?4"),
		/*@NamedQuery(name = "FindTPrdAttrByTAttGrpMapDefine", query = "select DISTINCT myTAttrGroupMap.displayName from TAlgmntTmpl myTAlgmntTmpl, TAttrGroup myTAttrGroup, TAttrGroupMap myTAttrGroupMap, TPrdAttr myTPrdAttr where myTAttrGroupMap.activeFlag = 'Y' and myTAlgmntTmpl.tAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId =?1 AND myTAlgmntTmpl.tAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId =?2 AND myTAlgmntTmpl.tAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId = ?3 AND myTAlgmntTmpl.tBussObjTmpl.tmplId = myTAttrGroup.tBussObjTmpl.tmplId AND myTAttrGroup.attrGroupId = myTAttrGroupMap.tAttrGroupMapId.attrGroupId AND myTAttrGroupMap.tAttrGroupMapId.attrId = myTPrdAttr.tPrdAttrId.attrId AND myTPrdAttr.tenantId=?4 AND myTPrdAttr.activeFlag = 'Y' "),*/

		@NamedQuery(name = "FindTPrdAttrByTAttGrpMapDefine", query = " SELECT DISTINCT myTAttrGroupMap.displayName " +
				"FROM TAlgmntTmpl myTAlgmntTmpl, TAttrGroup myTAttrGroup, TAttrGroupMap myTAttrGroupMap,TBussObjTmpl myTBussObjTmpl,TBussObj myTBussObj " +
				"WHERE myTBussObj.bussObjName = ?1 " +
				"AND myTBussObj.bussObjId = myTBussObjTmpl.tBussObj.bussObjId " +
				"AND myTBussObj.tenantId = myTBussObjTmpl.tenantId " +
				"AND myTBussObjTmpl.defFlag ='N' " +
				"AND myTBussObjTmpl.activeFlag = 'Y' " +
				"AND myTBussObjTmpl.tmplId = myTAlgmntTmpl.tBussObjTmpl.tmplId " +
				"AND myTBussObjTmpl.tenantId = myTAlgmntTmpl.tenantId " +
				"AND myTAlgmntTmpl.tAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId =?2 " +
				"AND myTAlgmntTmpl.tAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId =?3 " +
				"AND myTAlgmntTmpl.tAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId = ?4 " +
				"AND myTAlgmntTmpl.activeFlag = 'Y' " +
				"AND myTAlgmntTmpl.tBussObjTmpl.tmplId = myTAttrGroup.tBussObjTmpl.tmplId " +
				"AND myTAlgmntTmpl.tenantId = myTAttrGroup.tenantId " +
				"AND myTAttrGroup.defFlag ='N' " +
				"AND myTAttrGroup.activeFlag = 'Y' " +
				"AND myTAttrGroup.attrGroupId = myTAttrGroupMap.tAttrGroupMapId.attrGroupId " +
				"AND myTAttrGroup.tenantId = myTAttrGroupMap.tenantId " +
				"AND myTAttrGroupMap.activeFlag = 'Y' " +
				"AND myTAlgmntTmpl.tenantId = ?5 "),
		
		
		/*	@NamedQuery(name = "FindTPrdAttrByTAttGrpMapValues", query = "select myTPrdAttr.tPrd.prdId ,myTAttrGroupMap.displayName,myTPrdAttr.attrValue " +
				"from TAlgmntTmpl myTAlgmntTmpl, TAttrGroup myTAttrGroup, TAttrGroupMap myTAttrGroupMap, TPrdAttr myTPrdAttr, TPrd myTPrd " +
				"where myTPrdAttr.tPrd.prdId IN ?1 " +
				"AND myTAlgmntTmpl.tAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId =?2 " +
				"AND myTAlgmntTmpl.tAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId =?3 " +
				"AND myTAlgmntTmpl.tAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId = ?4 " +
				"AND myTAlgmntTmpl.tBussObjTmpl.tmplId = myTAttrGroup.tBussObjTmpl.tmplId " +
				"AND myTAttrGroup.attrGroupId = myTAttrGroupMap.tAttrGroupMapId.attrGroupId " +
				"AND myTAttrGroupMap.tAttrGroupMapId.attrId = myTPrdAttr.tPrdAttrId.attrId " +
				"AND myTPrdAttr.tenantId=?5 " +
				"AND myTPrdAttr.activeFlag = 'Y' "+
				"GROUP BY myTPrdAttr.tPrdAttrId.attrId,myTAttrGroupMap.displayName,myTPrdAttr.attrValue " +
				"ORDER BY myTPrdAttr.tPrdAttrId.attrId ")*/
		@NamedQuery(name = "FindTPrdAttrByTAttGrpMapValues", query = "select myTPrdAttr.tPrd.prdId ,myAttrDef.displayName,myTPrdAttr.attrValue " +
				"from  TPrdAttr myTPrdAttr LEFT JOIN myTPrdAttr.tAttrDef myAttrDef  " +
				"where myTPrdAttr.tPrd.prdId IN ?1 " +
				"AND myTPrdAttr.tenantId=?2 " +
				"AND myTPrdAttr.activeFlag = 'Y' "+
				"GROUP BY myTPrdAttr.tPrdAttrId.attrId,myAttrDef.displayName,myTPrdAttr.attrValue " +
				"ORDER BY myTPrdAttr.tPrdAttrId.attrId ")

		
})
@Table(name = "t_prd_attr")
public class TPrdAttr implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@EmbeddedId
	@Valid
	private TPrdAttrId tPrdAttrId;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 3000)
	@Column(name = "attr_value", nullable = true, length = 3000)
	private String attrValue;

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
	@Column(name = "created_by", nullable = false, length = 255, updatable = false)
	private Integer createdBy;

	/**
	 * 
	 * @generated
	 */
	@NotNull
	@Column(name = "create_dt", nullable = false, length = 19, updatable = false)
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

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "prd_id", referencedColumnName = "prd_id", nullable = false, insertable = false, updatable = false)
	@Valid
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private TPrd tPrd;

	@ManyToOne
	@JoinColumn(name = "attr_id", referencedColumnName = "attr_id", nullable = false, insertable = false, updatable = false)
	@Valid
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private TAttrDef tAttrDef;

	/**
	 *
	 * @generated
	 */
	public TPrdAttr() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setTPrdAttrId(final TPrdAttrId tPrdAttrId) {
		this.tPrdAttrId = tPrdAttrId;
	}

	/**
	 * 
	 * @generated
	 */
	public TPrdAttrId getTPrdAttrId() {
		return this.tPrdAttrId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setAttrValue(final String attrValue) {
		this.attrValue = attrValue;
	}

	/**
	 * 
	 * @generated
	 */
	public String getAttrValue() {
		return this.attrValue;
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
	public TPrd getTPrd() {
		return this.tPrd;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTPrd(final TPrd tPrd) {
		this.tPrd = tPrd;
		if (this.tPrd != null && this.tPrd.getPrdId() != null) {

			this.tPrdAttrId.setPrdId(this.tPrd.getPrdId());

		}

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
		if (this.tAttrDef != null && this.tAttrDef.getAttrId() != null) {

			this.tPrdAttrId.setAttrId(this.tAttrDef.getAttrId());

		}

	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TPrdAttr that) {
		setTPrdAttrId(that.getTPrdAttrId());
		setAttrValue(that.getAttrValue());
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
		result = prime * result + ((tPrdAttrId == null) ? 0 : tPrdAttrId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("tPrdAttrId=[").append(tPrdAttrId).append("] ");
		buffer.append("attrValue=[").append(attrValue).append("] ");
		buffer.append("activeFlag=[").append(activeFlag).append("] ");
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
		final TPrdAttr other = (TPrdAttr) obj;
		if (tPrdAttrId == null) {
			if (other.tPrdAttrId != null) {
				return false;
			}
		} else if (!tPrdAttrId.equals(other.tPrdAttrId)) {
			return false;
		}
		return true;
	}
}
