package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/** 
 * JPA class representing TRefCd 
 * The corresponding mapping table is t_ref_cd 
 */

@Entity
@NamedQueries({ @NamedQuery(name = "FindAllTRefCds", query = "select myTRefCd from TRefCd myTRefCd"),
		@NamedQuery(name = "CountTRefCds", query = "Select Count(c) from TRefCd c") })
@Table(name = "t_ref_cd", uniqueConstraints = @UniqueConstraint(columnNames = { "cd_id" }))
public class TRefCd implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cd_id", nullable = false, length = 255)
	private Integer cdId;

	/**
	 * 
	 * @generated
	 */
	@NotEmpty
	@Length(max = 30)
	@Column(name = "lookup_tbl_name", nullable = false, length = 30)
	private String lookupTblName;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 30)
	@Column(name = "lookup_col_name", nullable = true, length = 30)
	private String lookupColName;

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
	@Column(name = "col_name", nullable = true, length = 30)
	private String colName;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tRefCd")
	private Set<TPrdTblSch> tPrdTblSchss;

	/**
	 *
	 * @generated
	 */
	public TRefCd() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setCdId(final Integer cdId) {
		this.cdId = cdId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getCdId() {
		return this.cdId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setLookupTblName(final String lookupTblName) {
		this.lookupTblName = lookupTblName;
	}

	/**
	 * 
	 * @generated
	 */
	public String getLookupTblName() {
		return this.lookupTblName;
	}

	/**
	 * 
	 * @generated
	 */

	public void setLookupColName(final String lookupColName) {
		this.lookupColName = lookupColName;
	}

	/**
	 * 
	 * @generated
	 */
	public String getLookupColName() {
		return this.lookupColName;
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
	public Set<TPrdTblSch> getTPrdTblSchss() {
		return this.tPrdTblSchss;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTPrdTblSchss(final Set<TPrdTblSch> tPrdTblSchss) {
		this.tPrdTblSchss = tPrdTblSchss;
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TRefCd that) {
		setCdId(that.getCdId());
		setLookupTblName(that.getLookupTblName());
		setLookupColName(that.getLookupColName());
		setTenantId(that.getTenantId());
		setColName(that.getColName());
	}

	/**
	 * @generated
	 * 
	 */
	@Override
	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = prime * result + ((cdId == null) ? 0 : cdId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("cdId=[").append(cdId).append("] ");
		buffer.append("lookupTblName=[").append(lookupTblName).append("] ");
		buffer.append("lookupColName=[").append(lookupColName).append("] ");
		buffer.append("tenantId=[").append(tenantId).append("] ");
		buffer.append("colName=[").append(colName).append("] ");

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
		final TRefCd other = (TRefCd) obj;
		if (cdId == null) {
			if (other.cdId != null) {
				return false;
			}
		} else if (!cdId.equals(other.cdId)) {
			return false;
		}
		return true;
	}
}
