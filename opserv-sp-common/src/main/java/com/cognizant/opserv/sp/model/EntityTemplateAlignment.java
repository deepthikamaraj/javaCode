package com.cognizant.opserv.sp.model;


public class EntityTemplateAlignment extends BaseModel {

	private Alignment alignment;

	private EntityTemplate template;

	public Alignment getAlignment() {
		return alignment;
	}

	public void setAlignment(Alignment alignment) {
		this.alignment = alignment;
	}

	public EntityTemplate getTemplate() {
		return template;
	}

	public void setTemplate(EntityTemplate template) {
		this.template = template;
	}

}
