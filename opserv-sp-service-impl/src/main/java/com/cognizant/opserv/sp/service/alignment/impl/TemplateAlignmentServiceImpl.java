package com.cognizant.opserv.sp.service.alignment.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.opserv.sp.common.CommonConstants;
import com.cognizant.opserv.sp.common.EntityType;
import com.cognizant.opserv.sp.exception.AlignmentServiceException;
import com.cognizant.opserv.sp.exception.AlignmentServiceException.AlignmentServiceExceptionCode;
import com.cognizant.opserv.sp.model.Alignment;
import com.cognizant.opserv.sp.model.EntityTemplate;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.persistence.dao.service.TemplateAlignmentDAOService;
import com.cognizant.opserv.sp.service.alignment.TemplateAlignmentService;
import com.cognizant.peg.core.exception.OpservDataAccessException;

/**
 * ****************************************************************************.
 *
 * @class TemplateAlignmentServiceImpl contains all the services for TemplateAlignment
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 04/05/2016
 * ***************************************************************************
 */
@Service("templateAlignmentService")
@Transactional(rollbackFor = AlignmentServiceException.class)
public class TemplateAlignmentServiceImpl implements  TemplateAlignmentService{
	
	/** The template alignment dao service. */
	@Autowired
	TemplateAlignmentDAOService templateAlignmentDAOService;
	
	/**
	 * Gets the entity template.
	 *
	 * @param entity the entity
	 * @param alignment the alignment
	 * @param userDetails the user details
	 * @return the entity template
	 * @throws AlignmentServiceException the alignment service exception
	 */
	@Override
	@Cacheable(value=CommonConstants.TEMPLATEALINMENT_ENTITY_CACHE,key="{#entity.id,#alignment.id,#userDetails.tenantId}")
	public EntityTemplate getEntityTemplate(EntityType entity,Alignment alignment, UserDetails userDetails)
			throws AlignmentServiceException {	
	
		try {
			if(null != entity 
					&& null != entity.getId() 
					&& null != alignment 
					&& null != alignment.getId() 
					&& null !=  alignment.getSalesTeam().getBusinessUnit()
					&& null !=  alignment.getSalesTeam().getBusinessUnit().getId()
					&& null !=  alignment.getSalesTeam()
					&& null !=  alignment.getSalesTeam().getId()){
				
			        return templateAlignmentDAOService.getEntityTemplate(entity, alignment, userDetails);
			}
			else{
				Object[] args  = new Object[]{"entityId","AlgId","BuId","StId"};
				throw new AlignmentServiceException(args);
			}
		} catch(OpservDataAccessException e) {
			 Object[] args = new Object[]{entity,alignment};
			 throw new AlignmentServiceException(AlignmentServiceExceptionCode.ALGN_SER_EX_CD_202,"error while fetching entity template",args,e);
		}
		
	}
}
