package com.cognizant.opserv.sp.service.alignment.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.opserv.sp.exception.AlignmentServiceException;
import com.cognizant.opserv.sp.exception.AlignmentServiceException.AlignmentServiceExceptionCode;
import com.cognizant.opserv.sp.model.Customer;
import com.cognizant.opserv.sp.model.CustomerProductAlignment;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.persistence.dao.service.CustomerProductAlignmentDAOService;
import com.cognizant.opserv.sp.service.alignment.CustomerProductAlignmentService;
import com.cognizant.peg.core.exception.OpservDataAccessException;
/**
 * ****************************************************************************.
 *
 * @class ProductAlignmentServiceImpl contains all the services for product  alignment
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 * ***************************************************************************
 */
@Service("customerProductAlignmentService")
@Transactional(rollbackFor = AlignmentServiceException.class)
public class CustomerProductAlignmentServiceImpl implements CustomerProductAlignmentService {
	
	
	/** The customerproduct alignment dao service. */
	@Autowired
	private CustomerProductAlignmentDAOService customerproductAlignmentDAOService;

	

	/**
	 * Gets the all product alignments by sales position.
	 *
	 * @method getAllProductAlignmentsBySalesPosition
	 * @param salesPos the sales pos
	 * @param userDetails the user details
	 * @return the all product alignments by sales position
	 * @throws AlignmentServiceException the alignment service exception
	 */
	@Override
	public List<CustomerProductAlignment> getAllCustomerProducts(
			Customer customer, SalesPosition salesPos, UserDetails userDetails)
			throws OpservDataAccessException, AlignmentServiceException {
		try{
			if (null != customer && null != customer.getId()
					&& customer.getId() != 0 && null != salesPos
					&& null != salesPos.getId() && salesPos.getId() != 0
					&& null != userDetails && null != userDetails.getTenantId()
					&& userDetails.getTenantId() != 0) {
				return customerproductAlignmentDAOService
						.getAllCustomerProducts(customer, salesPos, userDetails);
			} else {
				  Object[] args = new Object[]{"CustomerId","SalesPositionId"};
				throw new AlignmentServiceException(args);
			}
		}catch(OpservDataAccessException e) {
		       Object[] args = new Object[]{salesPos.getId(),customer.getId()};
		       throw new AlignmentServiceException(AlignmentServiceExceptionCode.ALGN_SER_EX_CD_203,"Fetch exception",args,e);
		}
	}
	

	/**
	 * Gets the all product alignments by sales position.
	 *
	 * @method getAllProductAlignmentsBySalesPosition
	 * @param salesPos the sales pos
	 * @param userDetails the user details
	 * @return the all product alignments by sales position
	 * @throws AlignmentServiceException the alignment service exception
	 */
	@Override
	public List<CustomerProductAlignment> getAllCustomerProducts(
			SalesPosition salesPos, UserDetails userDetails)
			throws AlignmentServiceException {
		try{
			if (null != salesPos && null != salesPos.getId()
					&& salesPos.getId() != 0 && null != userDetails
					&& null != userDetails.getTenantId()
					&& userDetails.getTenantId() != 0) {
				return customerproductAlignmentDAOService
						.getAllCustomerProducts(new Customer(), salesPos,
								userDetails);
			} else {
				Object[] args = new Object[]{"Sales PositionId"};
				throw new AlignmentServiceException("Invalid Input Parameters","Invalid Input Parameters");
			}
		}catch(OpservDataAccessException e) {
		       Object[] args = new Object[]{salesPos.getId()};
		       throw new AlignmentServiceException(AlignmentServiceExceptionCode.ALGN_SER_EX_CD_204,"Fetch exception",args,e);
		}
	}

}
