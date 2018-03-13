package com.cognizant.opserv.sp.common;
/**
 * ****************************************************************************.
 *
 * @enum MeasureType 
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 * ***************************************************************************
 */
public enum MeasureType {

	PERCENTAGE(1), UNIT(2), CURRENCY(3);

	private Integer id;

	private MeasureType(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}
	
	/**
	 * Gets the measure type.
	 *
	 * @param id the id
	 * @return the measure type
	 */
	public static MeasureType getMeasureType(Integer id) {
		for (MeasureType measureType : MeasureType.values()) {
			if (measureType.getId().equals(id)) {
				return measureType;
			}
		}
		return null;
	}
}
