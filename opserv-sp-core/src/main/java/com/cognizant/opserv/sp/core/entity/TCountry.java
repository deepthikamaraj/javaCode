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
import javax.persistence.QueryHint;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/** 
 * JPA class representing TCountry 
 * The corresponding mapping table is t_country 
 */

@Entity
@NamedQueries({ @NamedQuery(name = "FindAllTCountrys", query = "select myTCountry from TCountry myTCountry"),
		@NamedQuery(name = "CountTCountrys", query = "Select Count(c) from TCountry c"),
		@NamedQuery(name = "FindAllCountryNames", query = "select c.countryName from TCountry c",
		hints = {
				@QueryHint(name = "org.hibernate.cacheable", value = "true"),
				@QueryHint(name = "org.hibernate.cacheRegion", value = "query.namedQueryCache")

		}),
		@NamedQuery(name = "GetAllCountryNamesByOrder", query = "select c.countryName from TCountry c order by c.countryName",
		hints = {
				@QueryHint(name = "org.hibernate.cacheable", value = "true"),
				@QueryHint(name = "org.hibernate.cacheRegion", value = "query.namedQueryCache")

		}),
})
@Table(name = "t_country", uniqueConstraints = @UniqueConstraint(columnNames = { "country_id" }))
public class TCountry implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "country_id", nullable = false, length = 255)
	private Integer countryId;

	/**
	 * 
	 * @generated
	 */
	@NotEmpty
	@Length(max = 150)
	@Column(name = "country_name", nullable = false, length = 150)
	private String countryName;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 20)
	@Column(name = "postal_cd_name", nullable = true, length = 20)
	private String postalCdName;

	/**
	 * @OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tCountry")
	 * @JoinColumn(name="country_id")
	 * private Set<TUsaState> tUsaStatess;
	 */  

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tCountry")
	@Fetch(FetchMode.SUBSELECT)
	private Set<TCountryDiv> tCountryDivss;

	/**
	 *
	 * @generated
	 */
	public TCountry() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setCountryId(final Integer countryId) {
		this.countryId = countryId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getCountryId() {
		return this.countryId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setCountryName(final String countryName) {
		this.countryName = countryName;
	}

	/**
	 * 
	 * @generated
	 */
	public String getCountryName() {
		return this.countryName;
	}

	/**
	 * 
	 * @generated
	 */

	public void setPostalCdName(final String postalCdName) {
		this.postalCdName = postalCdName;
	}

	/**
	 * 
	 * @generated
	 */
	public String getPostalCdName() {
		return this.postalCdName;
	}

	/**
	 * 
	 * @generated
	 */
//	public Set<TUsaState> getTUsaStatess() {
//		return this.tUsaStatess;
//	}

	/**
	 * 
	 * @generated
	 */
//	public void setTUsaStatess(final Set<TUsaState> tUsaStatess) {
//		this.tUsaStatess = tUsaStatess;
//	}

	/**
	 * 
	 * @generated
	 */
	public Set<TCountryDiv> getTCountryDivss() {
		return this.tCountryDivss;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTCountryDivss(final Set<TCountryDiv> tCountryDivss) {
		this.tCountryDivss = tCountryDivss;
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TCountry that) {
		setCountryId(that.getCountryId());
		setCountryName(that.getCountryName());
		setPostalCdName(that.getPostalCdName());
	}

	/**
	 * @generated
	 * 
	 */
	@Override
	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = prime * result + ((countryId == null) ? 0 : countryId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("countryId=[").append(countryId).append("] ");
		buffer.append("countryName=[").append(countryName).append("] ");
		buffer.append("postalCdName=[").append(postalCdName).append("] ");

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
		final TCountry other = (TCountry) obj;
		if (countryId == null) {
			if (other.countryId != null) {
				return false;
			}
		} else if (!countryId.equals(other.countryId)) {
			return false;
		}
		return true;
	}
}
