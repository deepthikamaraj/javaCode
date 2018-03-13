package com.cognizant.opserv.sp.model;

import java.io.Serializable;


/**
 * ****************************************************************************.
 *
 * @class Address
 * @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * @since 11/05/2016
 * ***************************************************************************
 */
public class Address extends BaseModel implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 345874937996740698L;

	/** customer is the Customer data. */
	private Customer customer;
	
	/**doorNumber is the door number. */
	private String doorNumber;
	
	/** addressLine is the address line. */
	private String addressLine;
	
	/** addressLine2 is the address2 line. */
	private String addressLine2;	
	
	/** addressLine3 is the address3 line. */
	private String addressLine3;
	
	/** addressLine4 is the address4 line. */
	private String addressLine4;		
	
	/** streetName is the street name. */
	private String streetName;
	
	/** city is the name of the city. */
	private String city;
	
	/** state is the name of the state. */
	private String state;
	
	/** country  is the name of the country. */
	private String country;
	
	/** primaryAddrFlag is the name of the primary address flag. */
	private boolean isPrimaryAddr;
	
	/** addressType  is the address type. */
	private Integer addressType;
	
	/** postalCode is the postal code. */
	private String postalCode;
	
	/** postalExtension is the postal extension. */
	private String postalExtension;

	/**
	 * Gets the customer.
	 *
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * Sets the customer.
	 *
	 * @param customer the new customer
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	/**
	 * Gets the door number.
	 *
	 * @return the door number
	 */
	public String getDoorNumber() {
		return doorNumber;
	}

	/**
	 * Sets the door number.
	 *
	 * @param doorNumber the new door number
	 */
	public void setDoorNumber(String doorNumber) {
		this.doorNumber = doorNumber;
	}

	/**
	 * Gets the address line.
	 *
	 * @return the address line
	 */
	public String getAddressLine() {
		return addressLine;
	}

	/**
	 * Sets the address line.
	 *
	 * @param addressLine the new address line
	 */
	public void setAddressLine(String addressLine) {
		this.addressLine = addressLine;
	}

	/**
	 * Gets the street name.
	 *
	 * @return the street name
	 */
	public String getStreetName() {
		return streetName;
	}

	/**
	 * Sets the street name.
	 *
	 * @param streetName the new street name
	 */
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	/**
	 * Gets the city.
	 *
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * Sets the city.
	 *
	 * @param city the new city
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * Gets the state.
	 *
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * Sets the state.
	 *
	 * @param state the new state
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * Gets the country.
	 *
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * Sets the country.
	 *
	 * @param country the new country
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	
	/**
	 * Gets the address type.
	 *
	 * @return the address type
	 */
	public Integer getAddressType() {
		return addressType;
	}

	/**
	 * Sets the address type.
	 *
	 * @param addressType the new address type
	 */
	public void setAddressType(Integer addressType) {
		this.addressType = addressType;
	}

	/**
	 * Gets the postal code.
	 *
	 * @return the postal code
	 */
	public String getPostalCode() {
		return postalCode;
	}

	/**
	 * Sets the postal code.
	 *
	 * @param postalCode the new postal code
	 */
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	/**
	 * Gets the postal extension.
	 *
	 * @return the postal extension
	 */
	public String getPostalExtension() {
		return postalExtension;
	}

	/**
	 * Sets the postal extension.
	 *
	 * @param postalExtension the new postal extension
	 */
	public void setPostalExtension(String postalExtension) {
		this.postalExtension = postalExtension;
	}
	
	
	/**
	 * Checks if is primary addr.
	 *
	 * @return true, if is primary addr
	 */
	public boolean isPrimaryAddr() {
		return isPrimaryAddr;
	}

	/**
	 * Sets the primary addr.
	 *
	 * @param isPrimaryAddr the new primary addr
	 */
	public void setPrimaryAddr(boolean isPrimaryAddr) {
		this.isPrimaryAddr = isPrimaryAddr;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getAddressLine3() {
		return addressLine3;
	}

	public void setAddressLine3(String addressLine3) {
		this.addressLine3 = addressLine3;
	}

	public String getAddressLine4() {
		return addressLine4;
	}

	public void setAddressLine4(String addressLine4) {
		this.addressLine4 = addressLine4;
	}
	
	
}
