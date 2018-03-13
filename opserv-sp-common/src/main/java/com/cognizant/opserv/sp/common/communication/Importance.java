package com.cognizant.opserv.sp.common.communication;

public enum Importance {

	HEIGH('h'),
	LOW('L'),
	NONE('N');
	
	/** The id. */
	Character flag;

	/**
	 * The Constructor.
	 *
	 * @param id the id
	 */
	private Importance(Character flag) {
		this.flag = flag;
	}

	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public int getValue() {
		return this.flag;
	}
}
