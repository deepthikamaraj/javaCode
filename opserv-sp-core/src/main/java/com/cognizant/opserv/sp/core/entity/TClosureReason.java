package com.cognizant.opserv.sp.core.entity;


/**************************************************************************************
 **************************************************************************************/
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;


/**
 * This TClosureReason class is a data object class which will be used for data transfer
 *
 *
 */	
@Entity
@Table(name = "t_closure_reason")
public class TClosureReason implements Serializable {

    /**
     * serialVersionUID to validate serialized object
     */
    private static final long serialVersionUID = 8406982833222020464L;

    /**
     *  reasonId - 
     */
    @Id
    @Column(name = "reason_id", unique = true, nullable = false)
    private int reasonId;

    /**
     *  reason - 
     */
    @Column(name = "reason", length = 50)
    private String reason;

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
     *  tEmpAlgmnts - 
     */
 /*   @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "tClosureReason")
    @Fetch(value = FetchMode.SUBSELECT)
    private List<TEmpAlgmnt> tEmpAlgmnts = new ArrayList<TEmpAlgmnt>();*/

    /**
     *  This method is used to get the reasonId  
     * @return ReasonId 
     */ 
    public int getReasonId() {	
        return this.reasonId;
    }	

    /**
     * This method is used to set the reasonId 
     * @param reasonId ReasonId which has to be set
     */ 
    public void setReasonId(final int reasonId) {
        this.reasonId = reasonId;
    }

    /**
     *  This method is used to get the reason  
     * @return Reason 
     */ 
    public String getReason() {	
        return this.reason;
    }	

    /**
     * This method is used to set the reason 
     * @param reason Reason which has to be set
     */ 
    public void setReason(final String reason) {
        this.reason = reason;
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
     *  This method is used to get the tEmpAlgmnts  
     * @return TEmpAlgmnts 
     */ 
 /*   public List<TEmpAlgmnt> getTEmpAlgmnts() {
        return this.tEmpAlgmnts;
    }		

    *//**
     * This method is used to set the tEmpAlgmnts 
     * @param tEmpAlgmnts TEmpAlgmnts which has to be set
     *//* 
    public void setTEmpAlgmnts(final List<TEmpAlgmnt> tEmpAlgmnts) {
        this.tEmpAlgmnts = (ArrayList<TEmpAlgmnt>) tEmpAlgmnts;
    }*/

    @Override
    public String toString() {
        final StringBuilder strBuilder = new StringBuilder();

        strBuilder.append("TClosureReason (").append(super.toString()).append("\t").append("reasonId = ").append(this.reasonId).append("\t").append("reason = ").append(this.reason).append("\t").append("activeFlag = ").append(this.activeFlag).append("\t").append("createdBy = ").append(this.createdBy).append("\t").append("createDt = ").append(this.createDt).append("\t").append("updatedBy = ").append(this.updatedBy).append("\t").append("updateDt = ").append(this.updateDt).append("\t").append("tenantId = ").append(this.tenantId).append("\t").append(
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
