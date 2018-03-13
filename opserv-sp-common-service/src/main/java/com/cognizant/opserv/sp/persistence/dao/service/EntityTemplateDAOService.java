package com.cognizant.opserv.sp.persistence.dao.service;

import java.util.List;

import com.cognizant.opserv.sp.model.EntityTemplate;
import com.cognizant.opserv.sp.model.EntityTemplateAlignment;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.peg.core.exception.OpservDataAccessException;

public interface EntityTemplateDAOService {

	
	/*EntityTemplate createEntityTemplate(EntityTemplate template,
			UserDetails userDetails);
	*/
	EntityTemplate copyAndCreateEntityTemplate(EntityTemplate srcTemplate,EntityTemplate newTemplate,UserDetails userDetails) throws OpservDataAccessException;
	
	List<EntityTemplate> getEntityTemplateByEntity(EntityTemplate template,
			UserDetails userDetails) throws OpservDataAccessException;
	
	List<EntityTemplateAlignment> getAlignmentEntityTempaltes(EntityTemplate template,UserDetails userDetails) throws OpservDataAccessException;
	
	void assignTemplatesToAlignment(List<EntityTemplateAlignment> templateAlignments,UserDetails userDetails) throws OpservDataAccessException;

	EntityTemplate getEntityTemplateById(EntityTemplate template,
			UserDetails userDetails) throws OpservDataAccessException;
}
