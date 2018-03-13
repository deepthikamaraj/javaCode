package com.cognizant.opserv.sp.common;
/**
 * ****************************************************************************.
 *
 * @enum GenderType 
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 * ***************************************************************************
 */
public enum GenderType {

	MALE(1,"M"), FEMALE(2,"F");
	
	private Integer id;

	private String gender;

	private GenderType(Integer id,String gender) {
		this.gender = gender;
		this.id = id;
	}

	public String getGender(){
		return this.gender;
	}
	
	public Integer getId() {
		return this.id;
	}
	
	
	/**
	 * Gets the gender type.
	 *
	 * @param gender the gender
	 * @return the gender type
	 */
	public static GenderType getGenderType(String gender) {
		for (GenderType genderType : GenderType.values()) {
			if (genderType.getGender().equals(gender)) {
				return genderType;
			}
		}
		return null;
	}

}
