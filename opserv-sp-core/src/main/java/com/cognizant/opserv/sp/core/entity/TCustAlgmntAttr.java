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

import org.hibernate.validator.constraints.Length;

/** 
 * JPA class representing TCustAlgmntAttr 
 * The corresponding mapping table is t_cust_algmnt_attr 
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "FindAllTCustAlgmntAttrs", query = "select myTCustAlgmntAttr from TCustAlgmntAttr myTCustAlgmntAttr"),
		@NamedQuery(name = "FindAllTCustAlgmntAttrsByCustAlgmntId", query = "select myTCustAlgmntAttr from TCustAlgmntAttr myTCustAlgmntAttr, TCustAlgmnt myTCustAlgmnt where myTCustAlgmnt.custAlgmntId = myTCustAlgmntAttr.tCustAlgmntAttrId.custAlgmntId and myTCustAlgmnt.custAlgmntId = ?1 "),
		@NamedQuery(name = "CountTCustAlgmntAttrs", query = "Select Count(c) from TCustAlgmntAttr c"),
		@NamedQuery(name = "FindTCustAlgmntAttrByTCustAlgmnt", query = "select myTCustAlgmntAttr from TCustAlgmntAttr myTCustAlgmntAttr where myTCustAlgmntAttr.tCustAlgmnt = ?1 "),
		@NamedQuery(name = "CountTCustAlgmntAttrsByTCustAlgmnt", query = "select Count(*) from TCustAlgmntAttr myTCustAlgmntAttr where myTCustAlgmntAttr.tCustAlgmnt = ?1 "),
		@NamedQuery(name = "FindTCustAlgmntAttrByTAttrDef", query = "select myTCustAlgmntAttr from TCustAlgmntAttr myTCustAlgmntAttr where myTCustAlgmntAttr.tAttrDef = ?1 "),
		@NamedQuery(name = "FindTCustAlgmntAttrByAttrId", query = "select myTCustAlgmntAttr from TCustAlgmntAttr myTCustAlgmntAttr where myTCustAlgmntAttr.tCustAlgmntAttrId.custAlgmntId = ?1 and myTCustAlgmntAttr.tCustAlgmntAttrId.attrId = ?2 and myTCustAlgmntAttr.tenantId =?3"),
		@NamedQuery(name = "getTCustAlgmntAttrById", query = "select myTCustAlgmntAttr from  TCustAlgmntAttr myTCustAlgmntAttr where myTCustAlgmntAttr.tCustAlgmntAttrId.custAlgmntId=?1 AND myTCustAlgmntAttr.tenantId=?2"),
		@NamedQuery(name = "CountTCustAlgmntAttrsByTAttrDef", query = "select Count(*) from TCustAlgmntAttr myTCustAlgmntAttr where myTCustAlgmntAttr.tAttrDef = ?1 "),
		@NamedQuery(name = "GetTCustAlgmntAttrByAttrId", query = "select myTCustAlgmntAttr from  TCustAlgmntAttr myTCustAlgmntAttr where myTCustAlgmntAttr.tAttrDef.attrId=?1 AND myTCustAlgmntAttr.attrValue=?2 AND myTCustAlgmntAttr.tenantId=?3 AND myTCustAlgmntAttr.tCustAlgmntAttrId.custAlgmntId <> ?4" ),
		@NamedQuery(name = "GetTCustAlgmntAttrByIdList", query = "select myTCustAlgmntAttr from  TCustAlgmntAttr myTCustAlgmntAttr where myTCustAlgmntAttr.tCustAlgmntAttrId.custAlgmntId IN ?1 AND myTCustAlgmntAttr.tenantId=?2"),
		@NamedQuery(name = "FindAllAttrsForCustAlgmnt", query = "select myTCustAlgmntAttr from  TCustAlgmntAttr myTCustAlgmntAttr where " +
				"myTCustAlgmntAttr.tCustAlgmntAttrId.custAlgmntId = ?1 AND myTCustAlgmntAttr.tCustAlgmntAttrId.attrId IN ?2 AND myTCustAlgmntAttr.activeFlag = 'Y' "),
		@NamedQuery(name = "FindAllAttrsForAllCustAlgmnt", query = "select myTCustAlgmntAttr from  TCustAlgmntAttr myTCustAlgmntAttr where " +
				"myTCustAlgmntAttr.tCustAlgmntAttrId.custAlgmntId IN ?1 AND myTCustAlgmntAttr.tCustAlgmntAttrId.attrId IN ?2 AND myTCustAlgmntAttr.tenantId = ?3"),
				

})
@Table(name = "t_cust_algmnt_attr")
public class TCustAlgmntAttr implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@EmbeddedId
	@Valid
	private TCustAlgmntAttrId tCustAlgmntAttrId;

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

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "cust_algmnt_id", referencedColumnName = "cust_algmnt_id", nullable = false, insertable = false, updatable = false)
	@Valid
	private TCustAlgmnt tCustAlgmnt;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "attr_id", referencedColumnName = "attr_id", nullable = false, insertable = false, updatable = false)
	@Valid
	private TAttrDef tAttrDef;
	
	/**
	 *
	 * @generated
	 */
	/*public TCustAlgmntAttr() {
	}*/

	/**
	 * 
	 * @generated
	 */

	public void setTCustAlgmntAttrId(final TCustAlgmntAttrId tCustAlgmntAttrId) {
		this.tCustAlgmntAttrId = tCustAlgmntAttrId;
	}

	/**
	 * 
	 * @generated
	 */
	public TCustAlgmntAttrId getTCustAlgmntAttrId() {
		return this.tCustAlgmntAttrId;
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
		if (createDt == null) {
			Date x=null;
			this.createDt = x;
			
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
			this.updateDt = x;
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
	public TCustAlgmnt getTCustAlgmnt() {
		return this.tCustAlgmnt;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTCustAlgmnt(final TCustAlgmnt tCustAlgmnt) {
		this.tCustAlgmnt = tCustAlgmnt;
		if (this.tCustAlgmnt != null && this.tCustAlgmnt.getCustAlgmntId() != null) {

			this.tCustAlgmntAttrId.setCustAlgmntId(this.tCustAlgmnt.getCustAlgmntId());

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

			this.tCustAlgmntAttrId.setAttrId(this.tAttrDef.getAttrId());

		}

	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TCustAlgmntAttr that) {
		setTCustAlgmntAttrId(that.getTCustAlgmntAttrId());
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
		result = prime * result + ((tCustAlgmntAttrId == null) ? 0 : tCustAlgmntAttrId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("tCustAlgmntAttrId=[").append(tCustAlgmntAttrId).append("] ");
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
		final TCustAlgmntAttr other = (TCustAlgmntAttr) obj;
		if (tCustAlgmntAttrId == null) {
			if (other.tCustAlgmntAttrId != null) {
				return false;
			}
		} else if (!tCustAlgmntAttrId.equals(other.tCustAlgmntAttrId)) {
			return false;
		}
		return true;
	}
}
