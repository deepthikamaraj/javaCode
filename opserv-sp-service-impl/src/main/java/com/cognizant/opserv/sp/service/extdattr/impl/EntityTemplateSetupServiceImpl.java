package com.cognizant.opserv.sp.service.extdattr.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.opserv.sp.exception.AlignmentServiceException;
import com.cognizant.opserv.sp.exception.AlignmentServiceException.AlignmentServiceExceptionCode;
import com.cognizant.opserv.sp.exception.AttributeServiceException;
import com.cognizant.opserv.sp.exception.AttributeServiceException.AttributeServiceExceptionCode;
import com.cognizant.opserv.sp.model.EntityTemplate;
import com.cognizant.opserv.sp.model.EntityTemplateAlignment;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.persistence.dao.service.EntityTemplateDAOService;
import com.cognizant.opserv.sp.service.extdattr.EntityTemplateSetupService;
import com.cognizant.peg.core.exception.OpservDataAccessException;

@Service("entityTemplateSetupService")
public class EntityTemplateSetupServiceImpl implements
		EntityTemplateSetupService {

	@Autowired
	private EntityTemplateDAOService entityTemplateDAOService;

	@Override
	public EntityTemplate createEntityTemplate(EntityTemplate template,
			UserDetails userDetails) throws AttributeServiceException {

		try {
			EntityTemplate temp = new EntityTemplate();
			// temp = entityTemplateDAOService.createEntityTemplate(template,
			// userDetails);

			return temp;
		} catch (RuntimeException exception) {
			throw new OpservDataAccessException(
					"Error occurred while creating entityTemplate", exception);
		}
	}

	@Override
	@Transactional
	public EntityTemplate getEntityTemplateById(EntityTemplate template,
			UserDetails userDetails) throws AttributeServiceException {

		if ( template.getId() == null || userDetails.getTenantId() == null) {
			String params = "Required Data Missing";
			Object[] args = new Object[] { params };
			throw new AttributeServiceException(args);
		}
		try {
			return entityTemplateDAOService.getEntityTemplateById(template,
					userDetails);
		} catch (OpservDataAccessException accessException) {
			Object[] args = new Object[] { "exception occured while fetching entity templates" };
			throw new AttributeServiceException(
					AttributeServiceExceptionCode.ATTR_SER_EX_CD_005,
					"fetch_exception", args, accessException);

		}
	}

	@Override
	@Transactional
	public List<EntityTemplate> getEntityTemplateByEntity(EntityTemplate template,
			UserDetails userDetails) throws AttributeServiceException {

		try {
			
		if (  userDetails.getTenantId() == null) {
			String params = "Required Data Missing";
			Object[] args = new Object[] { params };
			throw new AttributeServiceException(args);
		}
		
			return entityTemplateDAOService.getEntityTemplateByEntity(template,
					userDetails);
		} catch (OpservDataAccessException accessException) {
			Object[] args = new Object[] { "exception occured while fetching entity templates" };
			throw new AttributeServiceException(
					AttributeServiceExceptionCode.ATTR_SER_EX_CD_005,
					"fetch_exception", args, accessException);
		}
	}
	
	@Override
	@Transactional
	public List<EntityTemplateAlignment> getAlignmentEntityTempaltes(
			EntityTemplate template, UserDetails userDetails)
			throws AlignmentServiceException {
		try {
		if (template.getId() == null 
				|| userDetails.getTenantId() == null) {
			String params = "Required Data Missing";
			Object[] args = new Object[] { params };
			throw new AlignmentServiceException(args);
		}
			return entityTemplateDAOService.getAlignmentEntityTempaltes(
					template, userDetails);
		}
		catch (OpservDataAccessException accessException) {
			Object[] args = new Object[] { "exception occured while fetching entitytemplate by alignment" };
			throw new AlignmentServiceException(
					AlignmentServiceExceptionCode.ALGN_SER_EX_CD_246,
					"fetch_exception", args, accessException);
		}
	}

	@Override
	@Transactional
	public void assignTemplatesToAlignment(
			List<EntityTemplateAlignment> templateAlignments,
			UserDetails userDetails) throws AlignmentServiceException {

		try {
		if (userDetails.getTenantId() == null) {
			String params = "Required Data Missing";
			Object[] args = new Object[] { params };
			throw new AlignmentServiceException(args);
		}
			entityTemplateDAOService.assignTemplatesToAlignment(
					templateAlignments, userDetails);
		} catch (OpservDataAccessException accessException) {
			Object[] args = new Object[] { "exception occured while assigning templates to alignment" };
			throw new AlignmentServiceException(
					AlignmentServiceExceptionCode.ALGN_SER_EX_CD_247,
					"assign_exception", args, accessException);
		}

	}

	@Override
	@Transactional
	public EntityTemplate copyAndCreateEntityTemplate(
			EntityTemplate srcTemplate, EntityTemplate newTemplate,
			UserDetails userDetails) throws AttributeServiceException {
		try {
		if ( srcTemplate.getId() == null
				|| userDetails.getTenantId() == null) {
			String params = "Required Data Missing";
			Object[] args = new Object[] { params };
			throw new AttributeServiceException(args);
		}
			return entityTemplateDAOService.copyAndCreateEntityTemplate(
					srcTemplate, newTemplate, userDetails);
		}

		catch (OpservDataAccessException accessException) {
			Object[] args = new Object[] { "exception occured while creating entityTemplate" };
			throw new AttributeServiceException(
					AttributeServiceExceptionCode.ATTR_SER_EX_CD_006,
					"create_exception", args, accessException);

		}

	}
}
