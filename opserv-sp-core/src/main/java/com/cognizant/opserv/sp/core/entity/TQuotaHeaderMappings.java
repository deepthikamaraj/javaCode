package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

/**
 * JPA class representing TQuotaHeaderMappings The corresponding mapping table
 * is t_quota_header_mappings
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "FindAllTQuotaHeaderMappingss", query = "select myTQuotaHeaderMappings from TQuotaHeaderMappings myTQuotaHeaderMappings"),
		@NamedQuery(name = "CountTQuotaHeaderMappingss", query = "Select Count(c) from TQuotaHeaderMappings c"),
		@NamedQuery(name = "FindTQuotaHeaderNames", query = "SELECT tqhm.quotaTableAttr,tqhm.mappedDispName,tqhm.confId,"
				+ "tqc.quotaType,tqc.refinementType,tqc.rolldownOpt "
				+ "FROM TQuotaConfig tqc,TQuotaHeaderMappings tqhm "
				+ "WHERE tqc.algmntId = ?1 AND tqc.bussUnitId = ?2 AND "
				+ "tqc.salesTeamId = ?3 AND CURDATE() BETWEEN tqc.refinementStartDate AND "
				+ "tqc.refinementEndDate AND tqc.confId=tqhm.confId AND "
				+ "tqhm.custColumn='N'"),
		@NamedQuery(name = "FindTQuotaCustHeaderNames", query = "SELECT tqhm.quotaTableAttr,tqhm.mappedDispName "
				+ "FROM TQuotaHeaderMappings tqhm "
				+ "WHERE tqhm.confId = ?1 and tqhm.custColumn = 'Y'") })
@Table(name = "t_quota_header_mappings", uniqueConstraints = @UniqueConstraint(columnNames = { "mapping_id" }))
public class TQuotaHeaderMappings implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "mapping_id", nullable = false, length = 255)
	private Long mappingId;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "config_id", nullable = false, length = 255)
	private Long confId;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 45)
	@Column(name = "quota_table_attr", nullable = true, length = 45)
	private String quotaTableAttr;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 45)
	@Column(name = "mapped_disp_name", nullable = true, length = 45)
	private String mappedDispName;

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
	@Column(name = "created_dt", nullable = true, length = 19)
	private Date createdDt;

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
	@Column(name = "updated_dt", nullable = true, length = 19)
	private Date updatedDt;

	/**
	 * 
	 * @generated
	 */
	@NotNull
	@Column(name = "cust_col", nullable = false, length = 1)
	private Character custColumn;

	/**
	 * 
	 * @generated
	 */
	public TQuotaHeaderMappings() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setMappingId(final Long mappingId) {
		this.mappingId = mappingId;
	}

	/**
	 * 
	 * @generated
	 */
	public Long getMappingId() {
		return this.mappingId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setQuotaTableAttr(final String quotaTableAttr) {
		this.quotaTableAttr = quotaTableAttr;
	}

	/**
	 * 
	 * @generated
	 */
	public String getQuotaTableAttr() {
		return this.quotaTableAttr;
	}

	/**
	 * 
	 * @generated
	 */

	public void setMappedDispName(final String mappedDispName) {
		this.mappedDispName = mappedDispName;
	}

	/**
	 * 
	 * @generated
	 */
	public String getMappedDispName() {
		return this.mappedDispName;
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

	public void setCreatedDt(final Date createdDt) {
		if (createdDt != null) {
			this.createdDt = (Date) createdDt.clone();
		} else {
			this.createdDt = null;
		}
	}

	/**
	 * 
	 * @generated
	 */
	public Date getCreatedDt() {
		if (this.createdDt != null) {
			return (Date) this.createdDt.clone();
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

	public void setUpdatedDt(final Date updatedDt) {
		if (updatedDt != null) {
			this.updatedDt = (Date) updatedDt.clone();
		} else {
			this.updatedDt = null;
		}
	}

	/**
	 * 
	 * @generated
	 */
	public Date getUpdatedDt() {
		if (this.updatedDt != null) {
			return (Date) this.updatedDt.clone();
		} else {
			return null;
		}
	}

	public Long getConfId() {
		return confId;
	}

	public void setConfId(Long confId) {
		this.confId = confId;
	}

	public Character getCustColumn() {
		return custColumn;
	}

	public void setCustColumn(Character custColumn) {
		this.custColumn = custColumn;
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TQuotaHeaderMappings that) {
		setMappingId(that.getMappingId());
		setConfId(that.confId);
		setQuotaTableAttr(that.getQuotaTableAttr());
		setMappedDispName(that.getMappedDispName());
		setCreatedBy(that.getCreatedBy());
		setCreatedDt(that.getCreatedDt());
		setUpdatedBy(that.getUpdatedBy());
		setUpdatedDt(that.getUpdatedDt());
		setCustColumn(that.getCustColumn());
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
				+ ((mappingId == null) ? 0 : mappingId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("mappingId=[").append(mappingId).append("] ");
		buffer.append("confId=[").append(confId).append("] ");
		buffer.append("quotaTableAttr=[").append(quotaTableAttr).append("] ");
		buffer.append("mappedDispName=[").append(mappedDispName).append("] ");
		buffer.append("createdBy=[").append(createdBy).append("] ");
		buffer.append("createdDt=[").append(createdDt).append("] ");
		buffer.append("updatedBy=[").append(updatedBy).append("] ");
		buffer.append("updatedDt=[").append(updatedDt).append("] ");
		buffer.append("custColumn=[").append(custColumn).append("] ");

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
		final TQuotaHeaderMappings other = (TQuotaHeaderMappings) obj;
		if (mappingId == null) {
			if (other.mappingId != null) {
				return false;
			}
		} else if (!mappingId.equals(other.mappingId)) {
			return false;
		}
		return true;
	}
}
