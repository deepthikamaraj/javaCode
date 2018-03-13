package com.cognizant.opserv.sp.service.extdattr;

import java.util.List;

import com.cognizant.opserv.sp.exception.AlignmentServiceException;
import com.cognizant.opserv.sp.exception.AttributeServiceException;
import com.cognizant.opserv.sp.model.EntityTemplate;
import com.cognizant.opserv.sp.model.EntityTemplateAlignment;
import com.cognizant.opserv.sp.model.auth.UserDetails;

public interface EntityTemplateSetupService {
	
	/**
	 * 
	 * @param template
	 * @return
	 * @throws AttributeServiceException
	 */
	EntityTemplate createEntityTemplate(EntityTemplate template,UserDetails userDetails) throws AttributeServiceException;
	
	
	
	/**
	 * Copy and create entity template.
	 *
	 * @param srcTemplate the src template
	 * @param newTemplate the new template
	 * @param userDetails the user details
	 * @return the entity template
	 * @throws AttributeServiceException the attribute service exception
	 */
	EntityTemplate copyAndCreateEntityTemplate(EntityTemplate srcTemplate,EntityTemplate newTemplate,UserDetails userDetails) throws AttributeServiceException; 
	
	
	
	/**
	 * 
	 * @param template
	 * @param userDetails
	 * @return
	 * @throws AttributeServiceException
	 */
	EntityTemplate getEntityTemplateById(EntityTemplate template,UserDetails userDetails) throws AttributeServiceException;
	
	/**
	 * 
	 * @param template
	 * @param userDetails
	 * @return
	 * @throws AlignmentServiceException
	 */
	List<EntityTemplateAlignment> getAlignmentEntityTempaltes(EntityTemplate template,UserDetails userDetails) throws AlignmentServiceException;
	
	/**
	 * 
	 * @param templateAlignments
	 * @param userDetails
	 * @throws AlignmentServiceException
	 */
	void assignTemplatesToAlignment(List<EntityTemplateAlignment> templateAlignments,UserDetails userDetails) throws AlignmentServiceException;

	/**
	 * 
	 * @param template
	 * @param userDetails
	 * @return
	 * @throws AttributeServiceException
	 */

	List<EntityTemplate> getEntityTemplateByEntity(EntityTemplate template,UserDetails userDetails) throws AttributeServiceException;



	

}
