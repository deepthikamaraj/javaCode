package com.cognizant.opserv.sp.model;

import java.util.Map;

public class EmpSearch {
	
	
	/**
	 * firstName – Employee Name
	 */
	private String name;

	/**
	 * firstName - Employee id
	 */
	private String id;

	/**
	 * firstName - Employee Zip
	 */
	private String zip;

	/**
	 * firstName - Employee City
	 */
	private String city;

	/**
	 * firstName - Employee Type
	 */
	private String type;

	/**
	 * firstName - Employee State
	 */
	private String state;

	/**
	 * firstName - Employee Code
	 */
	private String code;

	/**
	 * firstName - Employee Attribute Map
	 */
	private Map<String, String> attrMap;

	/**
	 * firstName - Employee Type Id
	 */
	private Integer empTypeId;

	/**
	 * firstName - Employee Type
	 */
	private String empType;

	/**
	 * @method getName - Gets the Employee Name
	 * 
	 * @return name - the Employee Name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @method setName - Sets the Employee Name.
	 * 
	 * @param name - the Employee Name
	 * 
	 * @return void
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @method getId - Gets the Employee Id
	 * 
	 * @return id - the Employee Id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @method setId - Sets the Employee Id.
	 * 
	 * @param id - the Employee Id
	 * 
	 * @return void
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @method getZip - Gets the Employee Zip
	 * 
	 * @return zip - the Employee Zip
	 */
	public String getZip() {
		return zip;
	}

	/**
	 * @method setZip - Sets the Employee Zip.
	 * 
	 * @param zip - the Employee Zip
	 * 
	 * @return void
	 */
	public void setZip(String zip) {
		this.zip = zip;
	}

	/**
	 * @method getCity - Gets the Start Date
	 * 
	 * @return city - the Employee City
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @method setCity - Sets the Employee City.
	 * 
	 * @param city - the Employee City
	 * 
	 * @return void
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @method getType - Gets the Employee Type
	 * 
	 * @return type - the Employee Type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @method setType - Sets the Employee Type.
	 * 
	 * @param type - the Employee Type
	 * 
	 * @return void
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @method getState - Gets the Employee State
	 * 
	 * @return state - the Employee State
	 */
	public String getState() {
		return state;
	}

	/**
	 * @method setState - Sets the Employee State.
	 * 
	 * @param state - the Employee State
	 * 
	 * @return void
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @method getCode - Gets the Employee Code
	 * 
	 * @return code - the Employee Code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @method setCode - Sets the Employee Code.
	 * 
	 * @param code - the Employee Code
	 * 
	 * @return void
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @method getAttrMap - Gets the Attribute Map
	 * 
	 * @return attrMap - the Attribute Map
	 */
	public Map<String, String> getAttrMap() {
		return attrMap;
	}

	/**
	 * @method setAttrMap - Sets the Attribute Map.
	 * 
	 * @param attrMap - the Attribute Map
	 * 
	 * @return void
	 */
	public void setAttrMap(Map<String, String> attrMap) {
		this.attrMap = attrMap;
	}

	/**
	 * @method getEmpTypeId - Gets the Employee Type Id
	 * 
	 * @return empTypeId - the Employee Type Id
	 */
	public Integer getEmpTypeId() {
		return empTypeId;
	}

	/**
	 * @method setEmpTypeId - Sets the Employee Type Id.
	 * 
	 * @param empTypeId - the Employee Type Id
	 * 
	 * @return void
	 */
	public void setEmpTypeId(Integer empTypeId) {
		this.empTypeId = empTypeId;
	}

	/**
	 * @method getEmpType - Gets the Employee Type
	 * 
	 * @return empType - the Employee Type
	 */
	public String getEmpType() {
		return empType;
	}

	/**
	 * @method setEmpType - Sets the Employee Type.
	 * 
	 * @param empType - the Employee Type
	 * 
	 * @return void
	 */
	public void setEmpType(String empType) {
		this.empType = empType;
	}

	/** Method toString
	 * @method toString - toString
	 * @return String - returns string
	 **/
	@Override
	public String toString() {
		return "EmpSearchVO [name=" + name + ", id=" + id + ", zip=" + zip
				+ ", city=" + city + ", type=" + type + ", state=" + state
				+ ", code=" + code + ", attrMap=" + attrMap + ", empTypeId="
				+ empTypeId + ", empType=" + empType + "]";
	}

}
