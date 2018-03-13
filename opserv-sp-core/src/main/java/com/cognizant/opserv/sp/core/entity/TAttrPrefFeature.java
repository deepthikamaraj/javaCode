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
import org.hibernate.validator.constraints.NotEmpty;

/** 
 * JPA class representing TAttrPrefFeature 
 * The corresponding mapping table is t_attr_pref_feature 
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "FindAllTAttrPrefFeatures", query = "select myTAttrPrefFeature from TAttrPrefFeature myTAttrPrefFeature"),
		@NamedQuery(name = "CountTAttrPrefFeatures", query = "Select Count(c) from TAttrPrefFeature c") })
@Table(name = "t_attr_pref_feature", uniqueConstraints = @UniqueConstraint(columnNames = { "feature_id" }))
public class TAttrPrefFeature implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "feature_id", nullable = false, length = 255)
	private Integer featureId;

	/**
	 * 
	 * @generated
	 */
	@NotEmpty
	@Length(max = 50)
	@Column(name = "feature_name", nullable = false, length = 50)
	private String featureName;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 200)
	@Column(name = "feature_desc", nullable = true, length = 200)
	private String featureDesc;

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
	@Length(max = 50)
	@Column(name = "mod_name", nullable = true, length = 50)
	private String modName;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 150)
	@Column(name = "mod_desc", nullable = true, length = 150)
	private String modDesc;

	/**
	 *
	 * @generated
	 */
	public TAttrPrefFeature() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setFeatureId(final Integer featureId) {
		this.featureId = featureId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getFeatureId() {
		return this.featureId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setFeatureName(final String featureName) {
		this.featureName = featureName;
	}

	/**
	 * 
	 * @generated
	 */
	public String getFeatureName() {
		return this.featureName;
	}

	/**
	 * 
	 * @generated
	 */

	public void setFeatureDesc(final String featureDesc) {
		this.featureDesc = featureDesc;
	}

	/**
	 * 
	 * @generated
	 */
	public String getFeatureDesc() {
		return this.featureDesc;
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

	public void setModName(final String modName) {
		this.modName = modName;
	}

	/**
	 * 
	 * @generated
	 */
	public String getModName() {
		return this.modName;
	}

	/**
	 * 
	 * @generated
	 */

	public void setModDesc(final String modDesc) {
		this.modDesc = modDesc;
	}

	/**
	 * 
	 * @generated
	 */
	public String getModDesc() {
		return this.modDesc;
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TAttrPrefFeature that) {
		setFeatureId(that.getFeatureId());
		setFeatureName(that.getFeatureName());
		setFeatureDesc(that.getFeatureDesc());
		setActiveFlag(that.getActiveFlag());
		setCreatedBy(that.getCreatedBy());
		setCreateDt(that.getCreateDt());
		setUpdatedBy(that.getUpdatedBy());
		setUpdateDt(that.getUpdateDt());
		setModName(that.getModName());
		setModDesc(that.getModDesc());
	}

	/**
	 * @generated
	 * 
	 */
	@Override
	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = prime * result + ((featureId == null) ? 0 : featureId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("featureId=[").append(featureId).append("] ");
		buffer.append("featureName=[").append(featureName).append("] ");
		buffer.append("featureDesc=[").append(featureDesc).append("] ");
		buffer.append("activeFlag=[").append(activeFlag).append("] ");
		buffer.append("createdBy=[").append(createdBy).append("] ");
		buffer.append("createDt=[").append(createDt).append("] ");
		buffer.append("updatedBy=[").append(updatedBy).append("] ");
		buffer.append("updateDt=[").append(updateDt).append("] ");
		buffer.append("modName=[").append(modName).append("] ");
		buffer.append("modDesc=[").append(modDesc).append("] ");

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
		final TAttrPrefFeature other = (TAttrPrefFeature) obj;
		if (featureId == null) {
			if (other.featureId != null) {
				return false;
			}
		} else if (!featureId.equals(other.featureId)) {
			return false;
		}
		return true;
	}
}
