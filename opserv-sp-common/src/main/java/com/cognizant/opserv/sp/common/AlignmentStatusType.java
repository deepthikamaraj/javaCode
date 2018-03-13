package com.cognizant.opserv.sp.common;

public enum AlignmentStatusType {

	ACTIVE(1), INACTIVE(2), PROPOSED(3), SUSPENDED(4);

	private AlignmentStatusType(int id) {
		this.id = id;
	}

	private int id;

	public int getId() {
		return id;
	}

}
