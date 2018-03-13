package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
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

import org.hibernate.validator.constraints.Length;

/** 
 * JPA class representing TPrdTblSch 
 * The corresponding mapping table is t_prd_tbl_sch 
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "FindAllTPrdTblSchs", query = "select myTPrdTblSch from TPrdTblSch myTPrdTblSch"),
		@NamedQuery(name = "FindTPrdTblSchByTenantId", query = "select myTPrdTblSch from TPrdTblSch myTPrdTblSch where myTPrdTblSch.tenantId = ?1"),
		@NamedQuery(name = "FindTPrdTblSchByTblAlias", query = "select myTPrdTblSch from TPrdTblSch myTPrdTblSch where myTPrdTblSch.tenantId = ?1 and myTPrdTblSch.tblAlias=?2"),
		@NamedQuery(name = "CountTPrdTblSchs", query = "Select Count(c) from TPrdTblSch c"),
		/*@NamedQuery(name = "FindTPrdTblSchByTBussEntity", query = "select myTPrdTblSch from TPrdTblSch myTPrdTblSch where myTPrdTblSch.tBussEntity = ?1 "),*/
		/*@NamedQuery(name = "CountTPrdTblSchsByTBussEntity", query = "select Count(myTPrdTblSch) from TPrdTblSch myTPrdTblSch where myTPrdTblSch.tBussEntity = ?1 "),*/
		@NamedQuery(name = "FindTPrdTblSchByTRefCd", query = "select myTPrdTblSch from TPrdTblSch myTPrdTblSch where myTPrdTblSch.tRefCd = ?1 "),
		@NamedQuery(name = "CountTPrdTblSchsByTRefCd", query = "select Count(myTPrdTblSch) from TPrdTblSch myTPrdTblSch where myTPrdTblSch.tRefCd = ?1 ") })
@Table(name = "t_prd_tbl_sch", uniqueConstraints = @UniqueConstraint(columnNames = { "tbl_id" }))
public class TPrdTblSch implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "tbl_id", nullable = false, length = 255)
	private Integer tblId;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 100)
	@Column(name = "tbl_name", nullable = true, length = 100)
	private String tblName;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 100)
	@Column(name = "col_name", nullable = true, length = 100)
	private String colName;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 100)
	@Column(name = "col_alias", nullable = true, length = 100)
	private String colAlias;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 50)
	@Column(name = "data_type", nullable = true, length = 50)
	private String dataType;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "key_col", nullable = true, length = 1)
	private Character keyCol;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 100)
	@Column(name = "tbl_alias", nullable = true, length = 100)
	private String tblAlias;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "null_option", nullable = true, length = 1)
	private Character nullOption;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "order_seq", nullable = true, length = 255)
	private Short orderSeq;

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
	@Column(name = "prn_tbl_flag", nullable = true, length = 1)
	private Character prnTblFlag;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 30)
	@Column(name = "dyn_tbl_name", nullable = true, length = 30)
	private String dynTblName;

	@ManyToOne
	@JoinColumn(name = "cd_id", referencedColumnName = "cd_id", nullable = true, insertable = true, updatable = true)
	@Valid
	private TRefCd tRefCd;

	
	@Column(name = "entity_id", nullable = true, length = 255)
	private Integer entityId;
	
	@Column(name = "col_len", nullable = true, length = 255)
	private Integer colLen;
	
	
	public Integer getColLen() {
		return colLen;
	}

	public void setColLen(Integer colLen) {
		this.colLen = colLen;
	}
	public Integer getEntityId() {
		return entityId;
	}

	public void setEntityId(Integer entityId) {
		this.entityId = entityId;
	}

	/**
	 *
	 * @generated
	 */
	public TPrdTblSch() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setTblId(final Integer tblId) {
		this.tblId = tblId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getTblId() {
		return this.tblId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setTblName(final String tblName) {
		this.tblName = tblName;
	}

	/**
	 * 
	 * @generated
	 */
	public String getTblName() {
		return this.tblName;
	}

	/**
	 * 
	 * @generated
	 */

	public void setColName(final String colName) {
		this.colName = colName;
	}

	/**
	 * 
	 * @generated
	 */
	public String getColName() {
		return this.colName;
	}

	/**
	 * 
	 * @generated
	 */

	public void setColAlias(final String colAlias) {
		this.colAlias = colAlias;
	}

	/**
	 * 
	 * @generated
	 */
	public String getColAlias() {
		return this.colAlias;
	}

	/**
	 * 
	 * @generated
	 */

	public void setDataType(final String dataType) {
		this.dataType = dataType;
	}

	/**
	 * 
	 * @generated
	 */
	public String getDataType() {
		return this.dataType;
	}

	/**
	 * 
	 * @generated
	 */

	public void setKeyCol(final Character keyCol) {
		this.keyCol = keyCol;
	}

	/**
	 * 
	 * @generated
	 */
	public Character getKeyCol() {
		return this.keyCol;
	}

	/**
	 * 
	 * @generated
	 */

	public void setTblAlias(final String tblAlias) {
		this.tblAlias = tblAlias;
	}

	/**
	 * 
	 * @generated
	 */
	public String getTblAlias() {
		return this.tblAlias;
	}

	/**
	 * 
	 * @generated
	 */

	public void setNullOption(final Character nullOption) {
		this.nullOption = nullOption;
	}

	/**
	 * 
	 * @generated
	 */
	public Character getNullOption() {
		return this.nullOption;
	}

	/**
	 * 
	 * @generated
	 */

	public void setOrderSeq(final Short orderSeq) {
		this.orderSeq = orderSeq;
	}

	/**
	 * 
	 * @generated
	 */
	public Short getOrderSeq() {
		return this.orderSeq;
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

	public void setPrnTblFlag(final Character prnTblFlag) {
		this.prnTblFlag = prnTblFlag;
	}

	/**
	 * 
	 * @generated
	 */
	public Character getPrnTblFlag() {
		return this.prnTblFlag;
	}

	/**
	 * 
	 * @generated
	 */

	public void setDynTblName(final String dynTblName) {
		this.dynTblName = dynTblName;
	}

	/**
	 * 
	 * @generated
	 */
	public String getDynTblName() {
		return this.dynTblName;
	}


	/**
	 * 
	 * @generated
	 */
	public TRefCd getTRefCd() {
		return this.tRefCd;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTRefCd(final TRefCd tRefCd) {
		this.tRefCd = tRefCd;

	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TPrdTblSch that) {
		setTblId(that.getTblId());
		setTblName(that.getTblName());
		setColName(that.getColName());
		setColAlias(that.getColAlias());
		setDataType(that.getDataType());
		setKeyCol(that.getKeyCol());
		setTblAlias(that.getTblAlias());
		setNullOption(that.getNullOption());
		setOrderSeq(that.getOrderSeq());
		setTenantId(that.getTenantId());
		setPrnTblFlag(that.getPrnTblFlag());
		setDynTblName(that.getDynTblName());
		setColLen(that.getColLen());
		
	}

	/**
	 * @generated
	 * 
	 */
	@Override
	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = prime * result + ((tblId == null) ? 0 : tblId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("tblId=[").append(tblId).append("] ");
		buffer.append("tblName=[").append(tblName).append("] ");
		buffer.append("colName=[").append(colName).append("] ");
		buffer.append("colAlias=[").append(colAlias).append("] ");
		buffer.append("dataType=[").append(dataType).append("] ");
		buffer.append("keyCol=[").append(keyCol).append("] ");
		buffer.append("tblAlias=[").append(tblAlias).append("] ");
		buffer.append("nullOption=[").append(nullOption).append("] ");
		buffer.append("orderSeq=[").append(orderSeq).append("] ");
		buffer.append("tenantId=[").append(tenantId).append("] ");
		buffer.append("prnTblFlag=[").append(prnTblFlag).append("] ");
		buffer.append("dynTblName=[").append(dynTblName).append("] ");
		buffer.append("colLen=[").append(colLen).append("] ");
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
		final TPrdTblSch other = (TPrdTblSch) obj;
		if (tblId == null) {
			if (other.tblId != null) {
				return false;
			}
		} else if (!tblId.equals(other.tblId)) {
			return false;
		}
		return true;
	}
}
