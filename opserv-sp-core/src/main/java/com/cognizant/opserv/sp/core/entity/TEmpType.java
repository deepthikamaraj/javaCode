package com.cognizant.opserv.sp.core.entity;


/**************************************************************************************
 **************************************************************************************/
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.QueryHint;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;


/**
 * This TEmpType class is a data object class which will be used for data transfer
 *
 *
 */	
/*@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Cacheable(value = true)*/
@Entity
@NamedQueries({ @NamedQuery(name = "FindAllTEmpType", query = "select myTEmpType from TEmpType myTEmpType where myTEmpType.tenantId=?1 AND myTEmpType.tEmpTypeId.localeId=?2",

hints = {

		@QueryHint(name = "org.hibernate.cacheable", value = "true"),
		@QueryHint(name = "org.hibernate.cacheRegion", value = "query.namedQueryCache")

}),
@NamedQuery(name = "FindAllTEmpTypeNameAndId", query ="Select myTEmpType.tEmpTypeId.empTypeId,myTEmpType.empTypeDesc from TEmpType myTEmpType where myTEmpType.tenantId=?1 AND myTEmpType.tEmpTypeId.localeId=?2", 
hints = {
	@QueryHint(name = "org.hibernate.cacheable", value = "true"),
	@QueryHint(name = "org.hibernate.cacheRegion", value = "query.namedQueryCache")		
})
})
@Table(name = "t_emp_type")
public class TEmpType implements Serializable {

    /**
     * serialVersionUID to validate serialized object
     */
    private static final long serialVersionUID = -4756770077466033966L;
    
    /**
	 * 
	 * @generated
	 */
	@EmbeddedId
	@Valid
	private TEmpTypeId tEmpTypeId;

    /**
     *  empTypeId - 
     */
    @Id
    @Column(name = "emp_type_id", unique = true, nullable = false)
    private int empTypeId;

    /**
     *  empTypeCd - 
     */
    @Column(name = "emp_type_cd", nullable = false, length = 20)
    private String empTypeCd;

    /**
     *  empTypeDesc - 
     */
    @Column(name = "emp_type_desc", length = 20)
    private String empTypeDesc;

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
     *  tPerses - 
     */
   /* @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "tEmpType")
    @Fetch(value = FetchMode.SUBSELECT)
    private List<TPers> tPerses = new ArrayList<TPers>();*/

    /**
     *  This method is used to get the empTypeId  
     * @return EmpTypeId 
     */ 
    public int getEmpTypeId() {	
        return this.empTypeId;
    }	

    /**
     * This method is used to set the empTypeId 
     * @param empTypeId EmpTypeId which has to be set
     */ 
    public void setEmpTypeId(final int empTypeId) {
        this.empTypeId = empTypeId;
    }

    /**
     *  This method is used to get the empTypeCd  
     * @return EmpTypeCd 
     */ 
    public String getEmpTypeCd() {	
        return this.empTypeCd;
    }	

    /**
     * This method is used to set the empTypeCd 
     * @param empTypeCd EmpTypeCd which has to be set
     */ 
    public void setEmpTypeCd(final String empTypeCd) {
        this.empTypeCd = empTypeCd;
    }

    /**
     *  This method is used to get the empTypeDesc  
     * @return EmpTypeDesc 
     */ 
    public String getEmpTypeDesc() {	
        return this.empTypeDesc;
    }	

    /**
     * This method is used to set the empTypeDesc 
     * @param empTypeDesc EmpTypeDesc which has to be set
     */ 
    public void setEmpTypeDesc(final String empTypeDesc) {
        this.empTypeDesc = empTypeDesc;
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
     *  This method is used to get the tPerses  
     * @return TPerses 
     */ 
/*    public List<TPers> getTPerses() {
        return this.tPerses;
    }		
*/
    /**
     * This method is used to set the tPerses 
     * @param tPerses TPerses which has to be set
     */ 
/*    public void setTPerses(final List<TPers> tPerses) {
        this.tPerses = (ArrayList<TPers>) tPerses;
    }*/

    @Override
    public String toString() {
        final StringBuilder strBuilder = new StringBuilder();

        strBuilder.append("TEmpType (").append(super.toString()).append("\t").append("empTypeId = ").append(this.empTypeId).append("\t").append("empTypeCd = ").append(this.empTypeCd).append("\t").append("empTypeDesc = ").append(this.empTypeDesc).append("\t").append("createdBy = ").append(this.createdBy).append("\t").append("createDt = ").append(this.createDt).append("\t").append("updatedBy = ").append(this.updatedBy).append("\t").append("updateDt = ").append(this.updateDt).append("\t").append("tenantId = ").append(this.tenantId).append("\t").append(
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
