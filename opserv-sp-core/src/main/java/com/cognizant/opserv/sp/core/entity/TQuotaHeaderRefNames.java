package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.hibernate.validator.constraints.Length;

/**
 * JPA class representing TQuotaHeaderRefNames The corresponding mapping table
 * is t_quota_header_ref_names
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "FindAllTQuotaHeaderRefNamess", query = "select myTQuotaHeaderRefNames from TQuotaHeaderRefNames myTQuotaHeaderRefNames"),
		@NamedQuery(name = "CountTQuotaHeaderRefNamess", query = "Select Count(c) from TQuotaHeaderRefNames c"),
		@NamedQuery(name = "FindTQuotaHeaderRefNamesForMarketShare", query = "select myTQuotaHeaderRefNames from TQuotaHeaderRefNames myTQuotaHeaderRefNames where myTQuotaHeaderRefNames.marketShare=?"),
		@NamedQuery(name = "FindTQuotaHeaderRefNamesForSalesGoal", query = "select myTQuotaHeaderRefNames from TQuotaHeaderRefNames myTQuotaHeaderRefNames where myTQuotaHeaderRefNames.salesGoal=?") })
@Table(name = "t_quota_header_ref_names", uniqueConstraints = @UniqueConstraint(columnNames = { "id" }))
public class TQuotaHeaderRefNames implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false, length = 255)
	private Long id;

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
	@Column(name = "display_name", nullable = true, length = 45)
	private String displayName;

	@Length(max = 1)
	@Column(name = "mrkt_share", nullable = true, length = 1)
	private Character marketShare;

	@Length(max = 1)
	@Column(name = "sales_goal", nullable = true, length = 1)
	private Character salesGoal;

	@Length(max = 20)
	@Column(name = "data_type", nullable = true, length = 20)
	private String dataType;

	
	@Length(max = 1)
	@Column(name = "display_flag", nullable = true, length = 1)
	private Character displayFlag;
	
	@Length(max = 1)
	@Column(name = "mapped_flag", nullable = true, length = 1)
	private Character mappedFlag;
	
	/**
	 * 
	 * @generated
	 */
	public TQuotaHeaderRefNames() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setId(final Long id) {
		this.id = id;
	}

	/**
	 * 
	 * @generated
	 */
	public Long getId() {
		return this.id;
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

	public Character getMarketShare() {
		return marketShare;
	}

	public void setMarketShare(Character marketShare) {
		this.marketShare = marketShare;
	}

	public Character getSalesGoal() {
		return salesGoal;
	}

	public void setSalesGoal(Character salesGoal) {
		this.salesGoal = salesGoal;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	
	

	public Character getDisplayFlag() {
		return displayFlag;
	}

	public void setDisplayFlag(Character displayFlag) {
		this.displayFlag = displayFlag;
	}
	
	

	public Character getMappedFlag() {
		return mappedFlag;
	}

	public void setMappedFlag(Character mappedFlag) {
		this.mappedFlag = mappedFlag;
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TQuotaHeaderRefNames that) {
		setId(that.getId());
		setQuotaTableAttr(that.getQuotaTableAttr());
		setDisplayName(that.getDisplayName());
	}

	/**
	 * @generated
	 * 
	 */
	@Override
	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("quotaTableAttr=[").append(quotaTableAttr).append("] ");
		buffer.append("displayName=[").append(displayName).append("] ");

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
		final TQuotaHeaderRefNames other = (TQuotaHeaderRefNames) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}
}
