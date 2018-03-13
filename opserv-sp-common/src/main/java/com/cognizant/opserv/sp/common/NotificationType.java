package com.cognizant.opserv.sp.common;

public enum NotificationType {
	/**
	 * values from table -t_note_type
	 */
	BOTH(3),
	EMAIL(1),
	DASHBOARD(2);
	
	private Integer id;
	
	private NotificationType(Integer id){
		this.id = id;
	}
	public Integer getId(){
		return this.id;
	}
	
	public static NotificationType getNotificationType(Integer id) {
		for (NotificationType notificationType : NotificationType.values()) {
			if (notificationType.getId() == id) {
				return notificationType;
			}
		}
		return null;
	}

}
