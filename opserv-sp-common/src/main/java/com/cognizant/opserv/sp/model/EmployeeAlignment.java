package com.cognizant.opserv.sp.model;

import java.io.Serializable;

import com.cognizant.opserv.sp.common.AllocationType;
import com.cognizant.opserv.sp.common.SystemRoleType;
/**
 * ****************************************************************************.
 *
 * @class EmployeeAlignment
 * @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * @since 11/05/2016
 * ***************************************************************************
 */
public class EmployeeAlignment extends BaseSalesAlignment implements
		Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2251227294031006185L;

	/** The emp algmnt id. */
	private long empAlgmntId;
	
	/** The employee. */
	private Employee employee;

	/** The org role. */
	private SalesOrgRole orgRole;

	/** The alloc percentage. */
	private int allocPercentage;

	/** The system role. */
	private SystemRoleType systemRole;

	/** The allocation. */
	private AllocationType allocation;

	/**
	 * Gets the employee.
	 *
	 * @return the employee
	 */
	public Employee getEmployee() {
		return employee;
	}

	/**
	 * Sets the employee.
	 *
	 * @param employee the employee to set
	 */
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	/**
	 * Gets the org role.
	 *
	 * @return the orgRole
	 */
	public SalesOrgRole getOrgRole() {
		return orgRole;
	}

	/**
	 * Sets the org role.
	 *
	 * @param orgRole the orgRole to set
	 */
	public void setOrgRole(SalesOrgRole orgRole) {
		this.orgRole = orgRole;
	}

	/**
	 * Gets the alloc percentage.
	 *
	 * @return the allocPercentage
	 */
	public int getAllocPercentage() {
		return allocPercentage;
	}

	/**
	 * Sets the alloc percentage.
	 *
	 * @param allocPercentage the allocPercentage to set
	 */
	public void setAllocPercentage(int allocPercentage) {
		this.allocPercentage = allocPercentage;
	}

	/**
	 * Gets the system role.
	 *
	 * @return the systemRole
	 */
	public SystemRoleType getSystemRole() {
		return systemRole;
	}

	/**
	 * Sets the system role.
	 *
	 * @param systemRole the systemRole to set
	 */
	public void setSystemRole(SystemRoleType systemRole) {
		this.systemRole = systemRole;
	}

	/**
	 * Gets the allocation.
	 *
	 * @return the allocation
	 */
	public AllocationType getAllocation() {
		return allocation;
	}

	/**
	 * Sets the allocation.
	 *
	 * @param allocation the allocation to set
	 */
	public void setAllocation(AllocationType allocation) {
		this.allocation = allocation;
	}

	/**
	 * Gets the emp algmnt id.
	 *
	 * @return the emp algmnt id
	 */
	public long getEmpAlgmntId() {
		return empAlgmntId;
	}

	/**
	 * Sets the emp algmnt id.
	 *
	 * @param empAlgmntId the new emp algmnt id
	 */
	public void setEmpAlgmntId(long empAlgmntId) {
		this.empAlgmntId = empAlgmntId;
	}

	
}
