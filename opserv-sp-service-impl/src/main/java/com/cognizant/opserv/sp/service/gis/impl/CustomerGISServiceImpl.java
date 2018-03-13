package com.cognizant.opserv.sp.service.gis.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.opserv.query.IExpressionCriteria;
import com.cognizant.opserv.query.IQuery;
import com.cognizant.opserv.sp.common.CommonConstants;
import com.cognizant.opserv.sp.exception.AlignmentServiceException;
import com.cognizant.opserv.sp.exception.AlignmentServiceException.AlignmentServiceExceptionCode;
import com.cognizant.opserv.sp.model.Alignment;
import com.cognizant.opserv.sp.model.CustomerAlignment;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.persistence.dao.service.CustomerGISDAOService;
import com.cognizant.opserv.sp.service.gis.CustomerGISService;
import com.cognizant.peg.core.common.ISearchCriteria;
import com.cognizant.peg.core.common.SearchCriteria;
import com.cognizant.peg.core.exception.OpservDataAccessException;

/**
 * ****************************************************************************.
 *
 * @class CustomerGISServiceImpl contains all the customer gis services 
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 * ***************************************************************************
 */
@Service("customerGISService")
public class CustomerGISServiceImpl implements CustomerGISService{
	
	@Autowired
	private CustomerGISDAOService customerGISDAOService;

	/**
	 * Gets the customers.
	 *
	 * @method getCustomers
	 * @param criteria the criteria
	 * @param alignment the alignment
	 * @param userDetails the user details
	 * @return the customers
	 * @throws AlignmentServiceException the alignment service exception
	 * @method getCustomers
	 */
	@Override
	@Transactional
	public List<CustomerAlignment> getCustomers(ISearchCriteria criteria,
			Alignment alignment, UserDetails userDetails)
			throws AlignmentServiceException {
		try{
			if(alignment == null || criteria == null){
				throw new AlignmentServiceException(new Object[] {"alignment","criteria"});
			}
			else{
				SearchCriteria searchCriteria = (SearchCriteria) criteria;
				String attribute = searchCriteria.getCriteriaObject().getAttribute();
				if(attribute.equals(CommonConstants.SALES_POS_ID) || attribute.equals(CommonConstants.POSTAL_CD) ||
						attribute.equals(CommonConstants.CUST_NAME) || attribute.equals(CommonConstants.CUST_CD) ||
						attribute.equals(CommonConstants.SALES_HIER_ID)){
					return customerGISDAOService.getCustomers(criteria, alignment, userDetails);
				}
				else{
					throw new AlignmentServiceException(new Object[] {"criteria"});
				}
			}
		}
		catch(OpservDataAccessException e){
			throw new AlignmentServiceException(AlignmentServiceExceptionCode.ALGN_SER_EX_CD_001, 
					"exception while fetching customer details", null, e);
		}
	}

	@Override
	public List<CustomerAlignment> getCustomers(IQuery query,
			Alignment alignment, UserDetails userDetails)
			throws AlignmentServiceException {
		try{
			if(alignment == null || query == null){
				throw new AlignmentServiceException(new Object[] {"alignment","query"});
			}
			else{
				IExpressionCriteria expressionCriteria = (IExpressionCriteria) query.getCriteria();
				String attribute = expressionCriteria.getAttribute();
				if(attribute.equals(CommonConstants.SALES_POS_ID) || attribute.equals(CommonConstants.POSTAL_CD) ||
						attribute.equals(CommonConstants.CUST_NAME) || attribute.equals(CommonConstants.CUST_CD) ||
						attribute.equals(CommonConstants.SALES_HIER_ID)){
					return customerGISDAOService.getCustomers(query, alignment, userDetails);
				}
				else{
					throw new AlignmentServiceException(new Object[] {"query"});
				}
			}
		}
		catch(OpservDataAccessException e){
			throw new AlignmentServiceException(AlignmentServiceExceptionCode.ALGN_SER_EX_CD_001, 
					"exception while fetching customer details", null, e);
		}
	}

}
