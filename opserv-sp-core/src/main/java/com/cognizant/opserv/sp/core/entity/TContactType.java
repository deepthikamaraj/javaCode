package com.cognizant.opserv.sp.core.entity;

/**************************************************************************************
 **************************************************************************************/
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * This TContactType class is a data object class which will be used for data transfer
 *
 *
 */	
@Entity
@Table(name = "t_contact_type")
public class TContactType implements Serializable {

    /**
     * serialVersionUID to validate serialized object
     */
    private static final long serialVersionUID = -4133451844564250357L;

    /**
     *  contactTypeId - 
     */
    @Id
    @Column(name = "contact_type_id", unique = true, nullable = false)
    private int contactTypeId;

    /**
     *  contactTypeName - 
     */
    @Column(name = "contact_type_name", nullable = false, length = 20)
    private String contactTypeName;

    /**
     *  contactTypeDesc - 
     */
    @Column(name = "contact_type_desc", length = 75)
    private String contactTypeDesc;

    /**
     *  activeFlag - 
     */
    @Column(name = "active_flag", nullable = false, length = 1)
    private char activeFlag;

    /**
     *  createdBy - 
     */
    @Column(name = "created_by", nullable = false)
    private int createdBy;

    /**
     *  createDt - 
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_dt", nullable = false, length = 19)
    private Date createDt;

    /**
     *  updatedBy - 
     */
    @Column(name = "updated_by")
    private Integer updatedBy;

    /**
     *  updateDt - 
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_dt", length = 19)
    private Date updateDt;

    /**
     *  tenantId - 
     */
    @Column(name = "tenant_id", nullable = false)
    private short tenantId;

    /**
     *  tCustContacts - 
     */
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.DETACH, mappedBy = "tContactType")
    @Fetch(value = FetchMode.SUBSELECT)
    private List<TCustContact> tCustContacts = new ArrayList<TCustContact>();

    /**
     *  This method is used to get the contactTypeId  
     * @return ContactTypeId 
     */ 
    public int getContactTypeId() {	
        return this.contactTypeId;
    }	

    /**
     * This method is used to set the contactTypeId 
     * @param contactTypeId ContactTypeId which has to be set
     */ 
    public void setContactTypeId(final int contactTypeId) {
        this.contactTypeId = contactTypeId;
    }

    /**
     *  This method is used to get the contactTypeName  
     * @return ContactTypeName 
     */ 
    public String getContactTypeName() {	
        return this.contactTypeName;
    }	

    /**
     * This method is used to set the contactTypeName 
     * @param contactTypeName ContactTypeName which has to be set
     */ 
    public void setContactTypeName(final String contactTypeName) {
        this.contactTypeName = contactTypeName;
    }

    /**
     *  This method is used to get the contactTypeDesc  
     * @return ContactTypeDesc 
     */ 
    public String getContactTypeDesc() {	
        return this.contactTypeDesc;
    }	

    /**
     * This method is used to set the contactTypeDesc 
     * @param contactTypeDesc ContactTypeDesc which has to be set
     */ 
    public void setContactTypeDesc(final String contactTypeDesc) {
        this.contactTypeDesc = contactTypeDesc;
    }

    /**
     *  This method is used to get the activeFlag  
     * @return ActiveFlag 
     */ 
    public char getActiveFlag() {	
        return this.activeFlag;
    }	

    /**
     * This method is used to set the activeFlag 
     * @param activeFlag ActiveFlag which has to be set
     */ 
    public void setActiveFlag(final char activeFlag) {
        this.activeFlag = activeFlag;
    }

    /**
     *  This method is used to get the createdBy  
     * @return CreatedBy 
     */ 
    public int getCreatedBy() {	
        return this.createdBy;
    }	

    /**
     * This method is used to set the createdBy 
     * @param createdBy CreatedBy which has to be set
     */ 
    public void setCreatedBy(final int createdBy) {
        this.createdBy = createdBy;
    }

    /**
     *  This method is used to get the createDt  
     * @return CreateDt 
     */ 
    public Date getCreateDt() {	
        return this.createDt;
    }	

    /**
     * This method is used to set the createDt 
     * @param createDt CreateDt which has to be set
     */ 
    public void setCreateDt(final Date createDt) {
        this.createDt = createDt;
    }

    /**
     *  This method is used to get the updatedBy  
     * @return UpdatedBy 
     */ 
    public Integer getUpdatedBy() {	
        return this.updatedBy;
    }	

    /**
     * This method is used to set the updatedBy 
     * @param updatedBy UpdatedBy which has to be set
     */ 
    public void setUpdatedBy(final Integer updatedBy) {
        this.updatedBy = updatedBy;
    }

    /**
     *  This method is used to get the updateDt  
     * @return UpdateDt 
     */ 
    public Date getUpdateDt() {	
        return this.updateDt;
    }	

    /**
     * This method is used to set the updateDt 
     * @param updateDt UpdateDt which has to be set
     */ 
    public void setUpdateDt(final Date updateDt) {
        this.updateDt = updateDt;
    }

    /**
     *  This method is used to get the tenantId  
     * @return TenantId 
     */ 
    public short getTenantId() {	
        return this.tenantId;
    }	

    /**
     * This method is used to set the tenantId 
     * @param tenantId TenantId which has to be set
     */ 
    public void setTenantId(final short tenantId) {
        this.tenantId = tenantId;
    }

    /**
     *  This method is used to get the tCustContacts  
     * @return TCustContacts 
     */ 
    public List<TCustContact> getTCustContacts() {
        return this.tCustContacts;
    }		

    /**
     * This method is used to set the tCustContacts 
     * @param tCustContacts TCustContacts which has to be set
     */ 
    public void setTCustContacts(final List<TCustContact> tCustContacts) {
        this.tCustContacts = (ArrayList<TCustContact>) tCustContacts;
    }

    @Override
    public String toString() {
        final StringBuilder strBuilder = new StringBuilder();

        strBuilder.append("TContactType (").append(super.toString()).append("\t").append("contactTypeId = ").append(this.contactTypeId).append("\t").append("contactTypeName = ").append(this.contactTypeName).append("\t").append("contactTypeDesc = ").append(this.contactTypeDesc).append("\t").append("activeFlag = ").append(this.activeFlag).append("\t").append("createdBy = ").append(this.createdBy).append("\t").append("createDt = ").append(this.createDt).append("\t").append("updatedBy = ").append(this.updatedBy).append("\t").append("updateDt = ").append(this.updateDt).append("\t").append("tenantId = ").append(this.tenantId).append("\t").append(
                " )");
        return strBuilder.toString();
    }

    @Override
    public boolean equals(final Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }
}
