package com.cognizant.opserv.sp.persistence.dao.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cognizant.opserv.sp.assembler.EntityTemplateModelAssembler;
import com.cognizant.opserv.sp.core.dao.TAlgmntSalesTeamDAO;
import com.cognizant.opserv.sp.core.dao.TAlgmntTmplDAO;
import com.cognizant.opserv.sp.core.dao.TAttrGroupDAO;
import com.cognizant.opserv.sp.core.dao.TAttrGroupMapDAO;
import com.cognizant.opserv.sp.core.dao.TBussEntityDAO;
import com.cognizant.opserv.sp.core.dao.TBussObjTmplDAO;
import com.cognizant.opserv.sp.core.entity.TAlgmntSalesTeam;
import com.cognizant.opserv.sp.core.entity.TAlgmntSalesTeamId;
import com.cognizant.opserv.sp.core.entity.TAlgmntTmpl;
import com.cognizant.opserv.sp.core.entity.TAttrGroup;
import com.cognizant.opserv.sp.core.entity.TAttrGroupMap;
import com.cognizant.opserv.sp.core.entity.TBussEntity;
import com.cognizant.opserv.sp.core.entity.TBussObj;
import com.cognizant.opserv.sp.core.entity.TBussObjTmpl;
import com.cognizant.opserv.sp.model.EntityTemplate;
import com.cognizant.opserv.sp.model.EntityTemplateAlignment;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.persistence.dao.service.EntityTemplateDAOService;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.exception.OpservDataAccessException;

@Component
public class EntityTemplateDAOServiceImpl implements EntityTemplateDAOService {

	@Autowired
	private TBussObjTmplDAO tBussObjTmplDAO;

	@Autowired
	private TAttrGroupDAO tAttrGroupDAO;

	@Autowired
	private TAttrGroupMapDAO tAttrGroupMapDAO;

	@Autowired
	private TBussEntityDAO tBussEntityDAO;

	@Autowired
	private TAlgmntTmplDAO tAlgmntTmplDAO;

	@Autowired
	private TAlgmntSalesTeamDAO tAlgmntSalesTeamDAO;

	@Override
	public EntityTemplate getEntityTemplateById(EntityTemplate template,
			UserDetails userDetails) throws OpservDataAccessException {
		try {

			TBussObjTmpl tBussObjTmpl = tBussObjTmplDAO
					.findAllBussObjIdByTmplId(template.getId().intValue(),
							userDetails.getTenantId());
			TBussEntity tBussEntity = tBussEntityDAO.getTBussEntityByBussObjId(
					tBussObjTmpl.getTBussObj(),
					userDetails.getTenantId());

			return EntityTemplateModelAssembler.convertTBussEntityToTempModel(
					tBussEntity, tBussObjTmpl, userDetails);
		}

		catch (RuntimeException exception) {
			throw new OpservDataAccessException(
					"Error occurred while fetching entity template", exception);
		}
	}

	@Override
	public List<EntityTemplate> getEntityTemplateByEntity(
			EntityTemplate template, UserDetails userDetails)
			throws OpservDataAccessException {
		try {
			

			TBussEntity tBussEntity = tBussEntityDAO
					.findTBussEntityById(template.getEntity().getId());
			TBussObj tBussObj = tBussEntity.getTBussObj();
			List<TBussObjTmpl> list = tBussObjTmplDAO
					.findTBussObjTmplByBussObjId(tBussObj.getBussObjId(),
							userDetails.getTenantId());

			 
			return EntityTemplateModelAssembler
					.convertEntityTempModel(list);
		}

		catch (RuntimeException exception) {
			throw new OpservDataAccessException(
					"Error occurred while fetching entity template", exception);
		}
	}

	@Override
	public List<EntityTemplateAlignment> getAlignmentEntityTempaltes(
			EntityTemplate template, UserDetails userDetails)
			throws OpservDataAccessException {
		try {
			TBussObjTmpl baseTemplateDetails = tBussObjTmplDAO
					.findTBussObjTmplById(template.getId().intValue());

			SearchFilter<TBussObjTmpl> searchFilter = new SearchFilter<TBussObjTmpl>();
			OperatorInfo operator = new OperatorInfo();
			PaginationInfo pageInfo = new PaginationInfo(0, 1000);
			operator.setLogicalOperator(LogicalOperatorEnum.AND);
			searchFilter.setOperatorInfo(operator);
			searchFilter.setPaginationInfo(pageInfo);
			searchFilter.setEntityClass(baseTemplateDetails);
			List<TAlgmntTmpl> alignms = tAlgmntTmplDAO
					.getTAlgmntTmplsByTBussObjTmpl(searchFilter);

			 
			return EntityTemplateModelAssembler
					.convertTAlgmntTmplToEntityTempAlgnmntModel(alignms,
							baseTemplateDetails, template);
		}

		catch (RuntimeException exception) {
			throw new OpservDataAccessException(
					"Error occurred while fetching alignments for entity template",
					exception);
		}
	}

	@Override
	public void assignTemplatesToAlignment(
			List<EntityTemplateAlignment> templateAlignments,
			UserDetails userDetails) throws OpservDataAccessException {
		try {
			TAlgmntTmpl tAlgmntTmpl = new TAlgmntTmpl();
			TAlgmntSalesTeamId tAlgmntSalesTeamId = new TAlgmntSalesTeamId();
			for (EntityTemplateAlignment entityTmplAlg : templateAlignments) {

				TBussObjTmpl tBussObjTmpl = tBussObjTmplDAO
						.findTBussObjTmplById(entityTmplAlg.getTemplate()
								.getId().intValue());
				
				
				tAlgmntSalesTeamId.setAlgmntId(entityTmplAlg.getAlignment()
						.getId());
				tAlgmntSalesTeamId.setBussUnitId(entityTmplAlg.getAlignment()
						.getSalesTeam().getBusinessUnit().getId());
				tAlgmntSalesTeamId.setSalesTeamId(entityTmplAlg.getAlignment()
						.getSalesTeam().getId());
				TAlgmntSalesTeam tAlgmntSalesTeam = tAlgmntSalesTeamDAO
						.findTAlgmntSalesTeamById(tAlgmntSalesTeamId);
				tAlgmntTmpl = EntityTemplateModelAssembler
						.convertEntityTempAlgmntmodelToTalgmntTmplEntity(
								tBussObjTmpl, tAlgmntSalesTeam, userDetails);

				tAlgmntTmplDAO.createTAlgmntTmpl(tAlgmntTmpl);
			}
		} catch (RuntimeException exception) {
			throw new OpservDataAccessException(
					"Error occurred while assgning template to alignment",
					exception);
		}

	}

	@Override
	public EntityTemplate copyAndCreateEntityTemplate(
			EntityTemplate srcTemplate, EntityTemplate newTemplate,
			UserDetails userDetails) throws OpservDataAccessException{
		try {
			TBussObjTmpl baseTemplate = tBussObjTmplDAO
					.findTBussObjTmplById(srcTemplate.getId().intValue());

			TBussObjTmpl tBussObjTmpl = EntityTemplateModelAssembler
					.convertEntityTempModelToTBussObjTmpl(baseTemplate,
							newTemplate, userDetails);

			TBussObjTmpl newTempl = tBussObjTmplDAO
					.createTBussObjTmpl(tBussObjTmpl);
			newTemplate.setId((long) newTempl.getTmplId().intValue());

			createNewEntityTemplateAttributeGroups(newTempl, baseTemplate,
					userDetails);

			return newTemplate;
		}

		catch (RuntimeException exception) {
			throw new OpservDataAccessException(
					"Error occurred while creating entity template", exception);
		}
	}

	private void createNewEntityTemplateAttributeGroups(
			TBussObjTmpl newTemplate, TBussObjTmpl baseTemplate,
			UserDetails userDetails) throws OpservDataAccessException{
		try {
			TBussObjTmpl baseTemplateDetails = tBussObjTmplDAO
					.findTBussObjTmplById(baseTemplate.getTmplId());
			Set<TAttrGroup> tAttrGroup =  baseTemplateDetails
					.getTAttrGroupss();
			TAttrGroup newAttrGroup = EntityTemplateModelAssembler
					.convertToTAttrGroupToNewTAttrGroup(baseTemplateDetails,
							newTemplate, userDetails);

			TAttrGroup newTempAttrGroup = tAttrGroupDAO
					.createTAttrGroup(newAttrGroup);
			createNewEntityTemplateAttrGroupMap(tAttrGroup, newTempAttrGroup,
					userDetails);
		}

		catch (RuntimeException exception) {
			throw new OpservDataAccessException(
					"Error occurred while creating template attribute groups ",
					exception);
		}
	}

	private void createNewEntityTemplateAttrGroupMap(Set<TAttrGroup> tAttrGroup,
			TAttrGroup newAttrGroup, UserDetails userDetails) throws OpservDataAccessException{
		TAttrGroupMap newTAttrGroupMap = EntityTemplateModelAssembler
				.convertNewEntityTemplateAttrGroupMap(tAttrGroup, newAttrGroup,
						userDetails);
		try {
			tAttrGroupMapDAO.createTAttrGroupMap(newTAttrGroupMap);
		}

		catch (RuntimeException exception) {
			throw new OpservDataAccessException(
					"Error occurred while creating template attribute groupmap",
					exception);
		}
	}

}
