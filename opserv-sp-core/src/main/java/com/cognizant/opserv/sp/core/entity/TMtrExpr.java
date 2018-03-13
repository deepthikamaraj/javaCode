package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;
import java.util.Date;

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
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

/** 
 * JPA class representing TMtrExpr 
 * The corresponding mapping table is t_mtr_expr 
 */

@Entity
//@Audited
@NamedQueries({
		@NamedQuery(name = "FindAllTMtrExprs", query = "select myTMtrExpr from TMtrExpr myTMtrExpr"),
		@NamedQuery(name = "CountTMtrExprs", query = "Select Count(c) from TMtrExpr c"),
		@NamedQuery(name = "FindTMtrExprByTMtr", query = "select myTMtrExpr from TMtrExpr myTMtrExpr where myTMtrExpr.tMtr = ?1 "),
		@NamedQuery(name = "CountTMtrExprsByTMtr", query = "select Count(myTMtrExpr) from TMtrExpr myTMtrExpr where myTMtrExpr.tMtr = ?1 "),
		@NamedQuery(name = "getMetricExpression", query = "select myTMtrExpr from TMtrExpr myTMtrExpr where  myTMtrExpr.tMtr.mtrId = ?1 and myTMtrExpr.exprType = ?2 and myTMtrExpr.tenantId = ?3 "),
		@NamedQuery(name = "FindExprIdByMtrId", query = "select myTMtrExpr from TMtrExpr myTMtrExpr where myTMtrExpr.tMtr.mtrId = ?1"),
		@NamedQuery(name = "FindTMtrExprByMtrId", query = "select myTMtrExpr.tMtr.mtrId, myTMtrExpr.mtrExpr from TMtrExpr myTMtrExpr where myTMtrExpr.tMtr.mtrId IN ?1 " +
				" and myTMtrExpr.exprType IN ?2 and  myTMtrExpr.tenantId= ?3 ")		
		})
@Table(name = "t_mtr_expr", uniqueConstraints = @UniqueConstraint(columnNames = { "expr_id" }))
public class TMtrExpr implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * audit
	 * 
	 * @generated
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "expr_id", nullable = false, length = 255)
	private Integer exprId;

	/**
	 * 
	 * @generated
	 */
	@NotEmpty
	/*@Length(max = 255)*/
	@Column(name = "mtr_expr", nullable = false)
	private String mtrExpr;

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
	@Column(name = "expr_type", nullable = false, length = 1)
	private Character exprType;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "mtr_id", referencedColumnName = "mtr_id", nullable = false, insertable = true, updatable = true)
	@Valid
//	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private TMtr tMtr;

	/**
	 *
	 * @generated
	 */
	public TMtrExpr() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setExprId(final Integer exprId) {
		this.exprId = exprId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getExprId() {
		return this.exprId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setMtrExpr(final String mtrExpr) {
		this.mtrExpr = mtrExpr;
	}

	/**
	 * 
	 * @generated
	 */
	public String getMtrExpr() {
		return this.mtrExpr;
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

	public void setExprType(final Character exprType) {
		this.exprType = exprType;
	}

	/**
	 * 
	 * @generated
	 */
	public Character getExprType() {
		return this.exprType;
	}

	/**
	 * 
	 * @generated
	 */
	public TMtr getTMtr() {
		return this.tMtr;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTMtr(final TMtr tMtr) {
		this.tMtr = tMtr;

	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TMtrExpr that) {
		setExprId(that.getExprId());
		setMtrExpr(that.getMtrExpr());
		setCreatedBy(that.getCreatedBy());
		setCreateDt(that.getCreateDt());
		setUpdatedBy(that.getUpdatedBy());
		setUpdateDt(that.getUpdateDt());
		setTenantId(that.getTenantId());
		setExprType(that.getExprType());
	}

	/**
	 * @generated
	 * 
	 */
	@Override
	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = prime * result + ((exprId == null) ? 0 : exprId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("exprId=[").append(exprId).append("] ");
		buffer.append("mtrExpr=[").append(mtrExpr).append("] ");
		buffer.append("createdBy=[").append(createdBy).append("] ");
		buffer.append("createDt=[").append(createDt).append("] ");
		buffer.append("updatedBy=[").append(updatedBy).append("] ");
		buffer.append("updateDt=[").append(updateDt).append("] ");
		buffer.append("tenantId=[").append(tenantId).append("] ");
		buffer.append("exprType=[").append(exprType).append("] ");

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
		final TMtrExpr other = (TMtrExpr) obj;
		if (exprId == null) {
			if (other.exprId != null) {
				return false;
			}
		} else if (!exprId.equals(other.exprId)) {
			return false;
		}
		return true;
	}
}
