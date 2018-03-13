package com.cognizant.opserv.sp.service.alignment.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.opserv.sp.common.CommonConstants;
import com.cognizant.opserv.sp.exception.AlignmentServiceException;
import com.cognizant.opserv.sp.model.Alignment;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.persistence.dao.service.AlignmentDAOService;
import com.cognizant.opserv.sp.service.alignment.AlignmentService;
import com.cognizant.peg.core.common.ISearchCriteria;
import com.cognizant.peg.core.common.SearchCriteria;
import com.cognizant.peg.core.exception.OpservDataAccessException;
/**
 * ****************************************************************************.
 *
 * @class AlignmentServiceImpl contains all the Alignment Management services Impl
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 13/05/2016
 * ***************************************************************************
 */
@Service("alignmentService")
@Transactional(rollbackFor = AlignmentServiceException.class)
public class AlignmentServiceImpl implements AlignmentService {
	
	/**
	 * AlignmentDAOService has the services to fetch Alignment data.
	 */
	@Autowired
	private AlignmentDAOService alignmentDAOService;
	
	/**
	 * @method 	fetchAlignments -  this method is to fetch alignment based on search criteria
	 * @param criteria - holds search criteria like alignment name, status etc
	 * @param userDetails - user data
	 * @return List<Alignment>
	 * @throws AlignmentServiceException
	 */
	@Override
	public List<Alignment> fetchAlignments(ISearchCriteria criteria,
			UserDetails userDetails) throws AlignmentServiceException {
		try {
			if(criteria == null){
				throw new AlignmentServiceException(new Object[] {"criteria"});
			}
			else{
				SearchCriteria searchCriteria = (SearchCriteria) criteria;
				String attribute = searchCriteria.getCriteriaObject().getAttribute();
				if(attribute.equals(CommonConstants.ALIGN_NAME) || attribute.equals(CommonConstants.ALIGN_STATUS_ID) ||
						attribute.equals(CommonConstants.SALES_TEAM_NAME) || attribute.equals(CommonConstants.BU_NAME)){
					return	alignmentDAOService.fetchAlignments(criteria,userDetails);
				}
				else{
					throw new AlignmentServiceException(new Object[] {"criteria"});
				}
			}
		} catch (OpservDataAccessException e) {
			Object[] args = new Object[] { };
			throw new AlignmentServiceException(
					AlignmentServiceException.AlignmentServiceExceptionCode.ALGN_SER_EX_CD_248,
					"Exception in fetching Attribute data", args, e);
		}
	}
}
