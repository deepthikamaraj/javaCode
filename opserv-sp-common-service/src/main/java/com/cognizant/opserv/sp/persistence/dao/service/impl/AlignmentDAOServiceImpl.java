package com.cognizant.opserv.sp.persistence.dao.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cognizant.opserv.sp.assembler.AlignmentModelAssembler;
import com.cognizant.opserv.sp.core.dao.TAlgmntDAO;
import com.cognizant.opserv.sp.core.entity.TAlgmnt;
import com.cognizant.opserv.sp.model.Alignment;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.persistence.dao.service.AlignmentDAOService;
import com.cognizant.peg.core.common.ISearchCriteria;
import com.cognizant.peg.core.exception.OpservDataAccessException;

// TODO: Auto-generated Javadoc
/**
 * ****************************************************************************.
 *
 * @class AlignmentDAOServiceImpl contains all the Alignment Management services Impl
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 13/05/2016
 * ***************************************************************************
 */
@Component
public class AlignmentDAOServiceImpl implements AlignmentDAOService{
	
	/**
	 * TAlgmntDAO has the services to fetch Alignment data.
	 */
	@Autowired
	private TAlgmntDAO tAlgmntDAO;
	
	/** The alignment model assembler. */
	@Autowired
	private AlignmentModelAssembler alignmentModelAssembler;
	
	/**
	 * @method 	fetchAlignments -  this method is to fetch alignment based on search criteria
	 * @param criteria - holds search criteria like alignment name, status etc
	 * @param userDetails - user data
	 * @return List<Alignment>
	 * @throws OpservDataAccessException
	 */
	@Override
	public List<Alignment> fetchAlignments(ISearchCriteria criteria,
			UserDetails userDetails) {
		try {
			List<Alignment> alignListModel = null;
			if(criteria != null){
				List<TAlgmnt> alignmentList =  tAlgmntDAO
						.findTAlgnmntBySearchCriteria(criteria, userDetails.getTenantId());
				if(alignmentList != null && !alignmentList.isEmpty()){
					alignListModel = new ArrayList<Alignment>();
					for(TAlgmnt tAlign : alignmentList){
						alignListModel.add(alignmentModelAssembler.convertTAlgnmntToAlignmentModel(tAlign));
					}
				}
			}
			return alignListModel;
		} catch (OpservDataAccessException e) {
			throw new OpservDataAccessException(
					"Error while fetching Alignment data ", e);
		}
	
	}
	
}
