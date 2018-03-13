package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.hibernate.validator.constraints.NotEmpty;

/** 
 * JPA class representing TTblSz 
 * The corresponding mapping table is t_tbl_sz 
 */

@Entity
@NamedQueries({ @NamedQuery(name = "FindAllTTblSzs", query = "select myTTblSz from TTblSz myTTblSz"),
		@NamedQuery(name = "CountTTblSzs", query = "Select Count(c) from TTblSz c") })
@Table(name = "t_tbl_sz", uniqueConstraints = @UniqueConstraint(columnNames = { "tbl_name" }))
public class TTblSz implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@Id
	@NotEmpty
	@Column(name = "tbl_name", nullable = false, length = 100)
	private String tblName;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "allow_row_sz", nullable = true, length = 255)
	private Integer allowRowSz;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "act_row_sz", nullable = true, length = 255)
	private Integer actRowSz;

	/**
	 *
	 * @generated
	 */
	public TTblSz() {
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

	public void setAllowRowSz(final Integer allowRowSz) {
		this.allowRowSz = allowRowSz;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getAllowRowSz() {
		return this.allowRowSz;
	}

	/**
	 * 
	 * @generated
	 */

	public void setActRowSz(final Integer actRowSz) {
		this.actRowSz = actRowSz;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getActRowSz() {
		return this.actRowSz;
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TTblSz that) {
		setTblName(that.getTblName());
		setAllowRowSz(that.getAllowRowSz());
		setActRowSz(that.getActRowSz());
	}

	/**
	 * @generated
	 * 
	 */
	@Override
	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = prime * result + ((tblName == null) ? 0 : tblName.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("tblName=[").append(tblName).append("] ");
		buffer.append("allowRowSz=[").append(allowRowSz).append("] ");
		buffer.append("actRowSz=[").append(actRowSz).append("] ");

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
		final TTblSz other = (TTblSz) obj;
		if (tblName == null) {
			if (other.tblName != null) {
				return false;
			}
		} else if (!tblName.equals(other.tblName)) {
			return false;
		}
		return true;
	}
}
