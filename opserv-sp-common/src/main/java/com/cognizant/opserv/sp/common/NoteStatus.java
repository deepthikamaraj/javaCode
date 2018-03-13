package com.cognizant.opserv.sp.common;

public enum NoteStatus {
	
	NOTIFIED(1);
	
  private Integer id;
	
	private NoteStatus(Integer id){
		this.id = id;
	}
	public Integer getId(){
		return this.id;
	}
	
	public static NoteStatus getNoteStatus(Integer id) {
		for (NoteStatus noteStatus : NoteStatus.values()) {
			if (noteStatus.getId().equals(id)) {
				return noteStatus;
			}
		}
		return null;
	}

}
