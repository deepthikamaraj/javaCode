package com.cognizant.opserv.sp.model;

import java.io.Serializable;
import java.util.List;
/**
 * ****************************************************************************.
 *
 * @class SalesPositionAssignments
 * @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * @since 11/05/2016
 * ***************************************************************************
 */
public class SalesPositionAssignments extends BaseSalesAlignment implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7586017123096570352L;

	private List<CustomerAlignment> customerAssignments;

	private List<ProductAlignment> productAssignments;

	private List<EmployeeAlignment> employeeAssignments;

	private List<CustomerProductAlignment> custProdAssignments;

	private List<GeoCustomerAlignment> geoAssignments;

	/**
	 * @return the customerAssignments
	 */
	public List<CustomerAlignment> getCustomerAssignments() {
		return customerAssignments;
	}

	/**
	 * @param customerAssignments
	 *            the customerAssignments to set
	 */
	public void setCustomerAssignments(
			List<CustomerAlignment> customerAssignments) {
		this.customerAssignments = customerAssignments;
	}

	/**
	 * @return the productAssignments
	 */
	public List<ProductAlignment> getProductAssignments() {
		return productAssignments;
	}

	/**
	 * @param productAssignments
	 *            the productAssignments to set
	 */
	public void setProductAssignments(List<ProductAlignment> productAssignments) {
		this.productAssignments = productAssignments;
	}

	/**
	 * @return the employeeAssignments
	 */
	public List<EmployeeAlignment> getEmployeeAssignments() {
		return employeeAssignments;
	}

	/**
	 * @param employeeAssignments
	 *            the employeeAssignments to set
	 */
	public void setEmployeeAssignments(
			List<EmployeeAlignment> employeeAssignments) {
		this.employeeAssignments = employeeAssignments;
	}

	/**
	 * @return the custProdAssignments
	 */
	public List<CustomerProductAlignment> getCustProdAssignments() {
		return custProdAssignments;
	}

	/**
	 * @param custProdAssignments
	 *            the custProdAssignments to set
	 */
	public void setCustProdAssignments(
			List<CustomerProductAlignment> custProdAssignments) {
		this.custProdAssignments = custProdAssignments;
	}

	/**
	 * @return the geoAssignments
	 */
	public List<GeoCustomerAlignment> getGeoAssignments() {
		return geoAssignments;
	}

	/**
	 * @param geoAssignments
	 *            the geoAssignments to set
	 */
	public void setGeoAssignments(List<GeoCustomerAlignment> geoAssignments) {
		this.geoAssignments = geoAssignments;
	}

}
