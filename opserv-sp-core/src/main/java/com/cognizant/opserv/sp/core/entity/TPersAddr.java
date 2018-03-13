package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;
import java.util.Date;

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
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.hibernate.validator.constraints.Length;

/**
 * JPA class representing TPersAddr The corresponding mapping table is
 * t_pers_addr
 */

@Entity
@Audited
@NamedQueries({
		@NamedQuery(name = "FindAllTPersAddrs", query = "select myTPersAddr from TPersAddr myTPersAddr"),
		@NamedQuery(name = "CountTPersAddrs", query = "Select Count(c) from TPersAddr c"),
		@NamedQuery(name = "FindTPersAddrByTPers", query = "select myTPersAddr from TPersAddr myTPersAddr where myTPersAddr.tPers = ?1 "),
		@NamedQuery(name = "CountTPersAddrsByTPers", query = "select Count(*) from TPersAddr myTPersAddr where myTPersAddr.tPers = ?1 "),
		@NamedQuery(name = "FindAddressByAddrId", query = "select myTPersAddr from TPersAddr myTPersAddr where myTPersAddr.addrId = ?1  and myTPersAddr.staffId=?2 and myTPersAddr.tenantId=?3 and myTPersAddr.activeFlag='Y'"),
		@NamedQuery(name = "FindPriAddressForEmp", query = "select myTPersAddr from TPersAddr myTPersAddr where myTPersAddr.staffId IN ?1 " +
				" and myTPersAddr.tenantId = ?2 and myTPersAddr.prAddrFlag = ?3  and myTPersAddr.activeFlag = ?4 "),
		@NamedQuery(name = "FindAllAddressesForEmp", query = "select myTPersAddr.staffId,myTPersAddr.addrId,myTPersAddr.addrTypeId,myTPersAddr.prAddrFlag,myTPersAddr.doorNumber,myTPersAddr.streetName,myTPersAddr.addrLine,"+
						"myTPersAddr.city,myTPersAddr.state,myTPersAddr.cntry,myTPersAddr.postalCd,myTAddrType.addrTypeDesc" +
						" from TPersAddr myTPersAddr,TAddrType myTAddrType where myTPersAddr.addrTypeId=myTAddrType.tAddrTypeId.addrTypeId and "+
						" myTPersAddr.staffId = ?1 and myTPersAddr.tenantId = ?2 and myTAddrType.tAddrTypeId.localeId=?3 and myTPersAddr.activeFlag = 'Y' ")})
@Table(name = "t_pers_addr")
public class TPersAddr implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@NotNull
	@Column(name = "staff_id", nullable = false, length = 255)
	private Integer staffId;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "addr_id", nullable = false, length = 255)
	private Integer addrId;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 20)
	@Column(name = "door_number", nullable = true, length = 20)
	private String doorNumber;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 20)
	@Column(name = "street_name", nullable = true, length = 20)
	private String streetName;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 200)
	@Column(name = "addr_line", nullable = true, length = 200)
	private String addrLine;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 20)
	@Column(name = "city", nullable = true, length = 20)
	private String city;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 20)
	@Column(name = "state", nullable = true, length = 20)
	private String state;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 20)
	@Column(name = "cntry", nullable = true, length = 20)
	private String cntry;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 20)
	@Column(name = "pr_addr_flag", nullable = true, length = 20)
	private String prAddrFlag;

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
	@Column(name = "addr_type_id", nullable = false, length = 255)
	private Integer addrTypeId;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 20)
	@Column(name = "postal_cd", nullable = true, length = 20)
	private String postalCd;

	/**
	 * 
	 * @generated
	 */	
	@Column(name = "created_by", nullable = false, length = 255,updatable=false)
	private Integer createdBy;

	/**
	 * 
	 * @generated
	 */	
	@Column(name = "create_dt", nullable = false, length = 19,updatable=false)
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
	@Length(max = 20)
	@Column(name = "postal_extn", nullable = true, length = 20)
	private String postalExtn;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 100)
	@Column(name = "addr_line_2", nullable = true, length = 100)
	private String addrLine2;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 100)
	@Column(name = "addr_line_3", nullable = true, length = 100)
	private String addrLine3;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 100)
	@Column(name = "addr_line_4", nullable = true, length = 100)
	private String addrLine4;
	
	/**
	 * 
	 * @generated
	 */
	@NotNull
	@Column(name = "tenant_id", nullable = false, length = 255)
	private Short tenantId;

	@ManyToOne
	@JoinColumn(name = "staff_id", referencedColumnName = "staff_id", nullable = false, insertable = false, updatable = false)
	@Valid
	@NotAudited
	private TPers tPers;

	/**
	 * 
	 * @generated
	 */
	public TPersAddr() {
	}

	public Integer getStaffId() {
		return staffId;
	}

	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}

	public Integer getAddrId() {
		return addrId;
	}

	public void setAddrId(Integer addrId) {
		this.addrId = addrId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setDoorNumber(final String doorNumber) {
		this.doorNumber = doorNumber;
	}

	/**
	 * 
	 * @generated
	 */
	public String getDoorNumber() {
		return this.doorNumber;
	}

	/**
	 * 
	 * @generated
	 */

	public void setStreetName(final String streetName) {
		this.streetName = streetName;
	}

	/**
	 * 
	 * @generated
	 */
	public String getStreetName() {
		return this.streetName;
	}

	/**
	 * 
	 * @generated
	 */

	public void setAddrLine(final String addrLine) {
		this.addrLine = addrLine;
	}

	/**
	 * 
	 * @generated
	 */
	public String getAddrLine() {
		return this.addrLine;
	}

	/**
	 * 
	 * @generated
	 */

	public void setCity(final String city) {
		this.city = city;
	}

	/**
	 * 
	 * @generated
	 */
	public String getCity() {
		return this.city;
	}

	/**
	 * 
	 * @generated
	 */

	public void setState(final String state) {
		this.state = state;
	}

	/**
	 * 
	 * @generated
	 */
	public String getState() {
		return this.state;
	}

	/**
	 * 
	 * @generated
	 */

	public void setCntry(final String cntry) {
		this.cntry = cntry;
	}

	/**
	 * 
	 * @generated
	 */
	public String getCntry() {
		return this.cntry;
	}

	/**
	 * 
	 * @generated
	 */

	public void setPrAddrFlag(final String prAddrFlag) {
		this.prAddrFlag = prAddrFlag;
	}

	/**
	 * 
	 * @generated
	 */
	public String getPrAddrFlag() {
		return this.prAddrFlag;
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

	public void setAddrTypeId(final Integer addrTypeId) {
		this.addrTypeId = addrTypeId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getAddrTypeId() {
		return this.addrTypeId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setPostalCd(final String postalCd) {
		this.postalCd = postalCd;
	}

	/**
	 * 
	 * @generated
	 */
	public String getPostalCd() {
		return this.postalCd;
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
	public TPers getTPers() {
		return this.tPers;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTPers(final TPers tPers) {
		this.tPers = tPers;
		if (this.tPers != null && this.tPers.getStaffId() != null) {
			this.staffId = this.tPers.getStaffId();
		}

	}

	/**
	 * 
	 * @generated
	 */

	public void setPostalExtn(final String postalExtn) {
		this.postalExtn = postalExtn;
	}

	/**
	 * 
	 * @generated
	 */
	public String getPostalExtn() {
		return this.postalExtn;
	}

	/**
	 * 
	 * @generated
	 */

	public void setAddrLine2(final String addrLine2) {
		this.addrLine2 = addrLine2;
	}

	/**
	 * 
	 * @generated
	 */
	public String getAddrLine2() {
		return this.addrLine2;
	}

	/**
	 * 
	 * @generated
	 */

	public void setAddrLine3(final String addrLine3) {
		this.addrLine3 = addrLine3;
	}

	/**
	 * 
	 * @generated
	 */
	public String getAddrLine3() {
		return this.addrLine3;
	}

	/**
	 * 
	 * @generated
	 */

	public void setAddrLine4(final String addrLine4) {
		this.addrLine4 = addrLine4;
	}

	/**
	 * 
	 * @generated
	 */
	public String getAddrLine4() {
		return this.addrLine4;
	}
	
	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TPersAddr that) {
		setAddrId(that.getAddrId());
		setStaffId(that.getStaffId());
		setDoorNumber(that.getDoorNumber());
		setStreetName(that.getStreetName());
		setAddrLine(that.getAddrLine());
		setCity(that.getCity());
		setState(that.getState());
		setCntry(that.getCntry());
		setPrAddrFlag(that.getPrAddrFlag());
		setActiveFlag(that.getActiveFlag());
		setAddrTypeId(that.getAddrTypeId());
		setPostalCd(that.getPostalCd());
		setCreatedBy(that.getCreatedBy());
		setCreateDt(that.getCreateDt());
		setUpdatedBy(that.getUpdatedBy());
		setUpdateDt(that.getUpdateDt());
		setTenantId(that.getTenantId());
		setPostalExtn(that.getPostalExtn());
		setAddrLine2(that.getAddrLine2());
		setAddrLine3(that.getAddrLine3());
		setAddrLine4(that.getAddrLine4());
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("addrId=[").append(addrId).append("] ");
		buffer.append("staffId=[").append(staffId).append("] ");
		buffer.append("doorNumber=[").append(doorNumber).append("] ");
		buffer.append("streetName=[").append(streetName).append("] ");
		buffer.append("addrLine=[").append(addrLine).append("] ");
		buffer.append("city=[").append(city).append("] ");
		buffer.append("state=[").append(state).append("] ");
		buffer.append("cntry=[").append(cntry).append("] ");
		buffer.append("prAddrFlag=[").append(prAddrFlag).append("] ");
		buffer.append("activeFlag=[").append(activeFlag).append("] ");
		buffer.append("addrTypeId=[").append(addrTypeId).append("] ");
		buffer.append("postalCd=[").append(postalCd).append("] ");
		buffer.append("createdBy=[").append(createdBy).append("] ");
		buffer.append("createDt=[").append(createDt).append("] ");
		buffer.append("updatedBy=[").append(updatedBy).append("] ");
		buffer.append("updateDt=[").append(updateDt).append("] ");
		buffer.append("tenantId=[").append(tenantId).append("] ");
		buffer.append("postalExtn=[").append(postalExtn).append("] ");
		buffer.append("addrLine2=[").append(addrLine2).append("] ");
		buffer.append("addrLine3=[").append(addrLine3).append("] ");
		buffer.append("addrLine4=[").append(addrLine4).append("] ");

		return buffer.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((addrId == null) ? 0 : addrId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TPersAddr other = (TPersAddr) obj;
		if (addrId == null) {
			if (other.addrId != null)
				return false;
		} else if (!addrId.equals(other.addrId))
			return false;
		return true;
	}

}
