package com.cognizant.opserv.sp.persistence.dao.service.impl;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cognizant.opserv.sp.assembler.AlignmentEntityAssembler;
import com.cognizant.opserv.sp.assembler.AlignmentModelAssembler;
import com.cognizant.opserv.sp.core.dao.TAlgmntDAO;
import com.cognizant.opserv.sp.core.dao.TAlgmntSalesTeamDAO;
import com.cognizant.opserv.sp.core.entity.TAlgmnt;
import com.cognizant.opserv.sp.core.entity.TAlgmntSalesTeam;
import com.cognizant.opserv.sp.model.Alignment;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.persistence.dao.service.AlignmentSetupDAOService;
import com.cognizant.peg.core.common.ISearchCriteria;
import com.cognizant.peg.core.exception.OpservDataAccessException;
/**
 * ****************************************************************************.
 *
 * @class AlignmentSetupDAOServiceImpl contains all the DAO Alignment setup services 
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 09/06/2016
 * ***************************************************************************
 */
@Component
public class AlignmentSetupDAOServiceImpl implements AlignmentSetupDAOService {

	/** The t algmnt dao. */
	@Autowired
	private TAlgmntDAO tAlgmntDAO;
	
	/** The t algmnt sales team dao. */
	@Autowired
	private TAlgmntSalesTeamDAO tAlgmntSalesTeamDAO;
	
	/** The alignment model assembler. */
	@Autowired
	private AlignmentModelAssembler alignmentModelAssembler;
	
	/**
	 * Create Alignment service.
	 *
	 * @param alignment the alignment
	 * @param userDetails the user details
	 * @return the alignment
	 * @throws OpservDataAccessException the opserv data access exception
	 */
	@Override
	public Alignment createAlignment(Alignment alignment,
			UserDetails userDetails) throws OpservDataAccessException {
		TAlgmnt tAlgmnt = new TAlgmnt();
		try{
			 tAlgmnt=	AlignmentEntityAssembler.convertAlgmntModeltoEntityForCreateAlignment(alignment,userDetails);
			 tAlgmnt= tAlgmntDAO.createTAlgmnt(tAlgmnt);
			 TAlgmntSalesTeam	 tAlgmntSalesTeam=AlignmentEntityAssembler.convertTAlgmntSalesTeamEntityForCreateAlignment(tAlgmnt, alignment, userDetails);
			 tAlgmntSalesTeamDAO.createTAlgmntSalesTeam(tAlgmntSalesTeam);
			 alignment.setId(tAlgmnt.getAlgmntId());
					
		}catch (RuntimeException re) {
			throw new OpservDataAccessException(
					"This issue is occurred while creating the  Alignment .",
					re);
		}
		return alignment;
	}

	
	/**
	 * Update Alignment Service.
	 *
	 * @param alignment the alignment
	 * @param userDetails the user details
	 * @throws OpservDataAccessException the opserv data access exception
	 */
	@Override
	public void updateAlignment(Alignment alignment, UserDetails userDetails)
			throws OpservDataAccessException {
		TAlgmnt tAlgmnt = new TAlgmnt();
		TAlgmntSalesTeam tAlgmntSalesTeam = new TAlgmntSalesTeam();
		try{
		TAlgmnt alg=	tAlgmntDAO.findAlgmntByAlgmntId(alignment.getId(), userDetails.getTenantId());
		
		if(alg!=null)
		{
		 tAlgmnt=	AlignmentEntityAssembler.convertAlgmntModeltoAlgmntEntity(alg,alignment,userDetails);
		 tAlgmnt= tAlgmntDAO.updateTAlgmnt(tAlgmnt);
		 List<TAlgmntSalesTeam> list = new ArrayList<TAlgmntSalesTeam>(tAlgmnt.getTAlgmntSalesTeamss());
		 tAlgmntSalesTeam=AlignmentEntityAssembler.convertTAlgmntSalesTeamEntity(list.get(0), alignment, userDetails);
		 tAlgmntSalesTeamDAO.updateTAlgmntSalesTeam(tAlgmntSalesTeam);
		 
		}
		}catch (RuntimeException re) {
			throw new OpservDataAccessException(
					"This issue is occurred while updating the Alignment .",
					re);
		}
	}

	
	/**
	 * Inactivate Alignment.
	 *
	 * @param alignment the alignment
	 * @param userDetails the user details
	 * @throws OpservDataAccessException the opserv data access exception
	 */
	@Override
	public void inactiveAlignment(Alignment alignment, UserDetails userDetails)
			throws OpservDataAccessException {
		
		TAlgmnt tAlgmnt = new TAlgmnt();
		try {
		TAlgmnt alg=	tAlgmntDAO.findAlgmntByAlgmntId(alignment.getId(), userDetails.getTenantId());
		if(alg!=null)
		{
		 tAlgmnt=	AlignmentEntityAssembler.convertAlgmntModeltoEntity(alg,alignment,userDetails);
		}
		
		tAlgmntDAO.updateTAlgmnt(tAlgmnt);
		}catch (RuntimeException re) {
			throw new OpservDataAccessException(
					"This issue is occurred while inactive the Alignment .",
					re);
		}
		
	}

	
	/**
	 * Gets the alignment details.
	 *
	 * @param alignmentId the alignment id
	 * @param userDetails the user details
	 * @return the alignment details
	 * @throws OpservDataAccessException the opserv data access exception
	 */
	@Override
	public Alignment getAlignmentDetails(long alignmentId,
			UserDetails userDetails) throws OpservDataAccessException {
		Alignment alignment =new Alignment();
		try{
		TAlgmnt alg=	tAlgmntDAO.findAlgmntByAlgmntId(alignmentId, userDetails.getTenantId());
		if(alg!=null )
		{
			alignment = alignmentModelAssembler.convertTAlgmntToAlignmentModel(alg);
		}
		}catch (RuntimeException re) {
			throw new OpservDataAccessException(
					"This issue is occurred while fetching  the Alignment details.",
					re);
		}
		return alignment;
	}

	
	/**
	 * get the Alignment details.
	 *
	 * @param criteria the criteria
	 * @param userDetails the user details
	 * @return the list
	 * @throws OpservDataAccessException the opserv data access exception
	 */
	@Override
	public List<Alignment> fetchAlignments(ISearchCriteria criteria,
			UserDetails userDetails) throws OpservDataAccessException {
		List<Alignment> alignmentList = new ArrayList<Alignment>();
		try{
			List<TAlgmnt> tAlgmntList = tAlgmntDAO.findTAlgnmntBySearchCriteria(criteria , userDetails.getTenantId());
			if(tAlgmntList!=null && tAlgmntList.size()>0)
			{
				for(TAlgmnt alg  : tAlgmntList){				
					Alignment alignment = alignmentModelAssembler.convertTAlgmntToAlignmentModel(alg);
					alignmentList.add(alignment);
				}
			}
		} catch (RuntimeException re) {
			throw new OpservDataAccessException(
					"This issue is occurred while fetching  the Alignment list.",
					re);
		}
		return alignmentList;
	}
}
