package com.cognizant.opserv.sp.service.internal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.opserv.sp.exception.CustomerServiceException;
import com.cognizant.opserv.sp.exception.CustomerServiceException.CustomerServiceExceptionCode;
import com.cognizant.opserv.sp.model.Customer;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.persistence.dao.service.CustomerDAOService;
import com.cognizant.peg.core.exception.OpservDataAccessException;

/**
 * The Class CustomerInternalServiceImpl.
 */
@Service
public class CustomerInternalServiceImpl implements CustomerInternalService{

	/** The customer dao service. */
	@Autowired
	private CustomerDAOService customerDAOService;
	
	/* (non-Javadoc)
	 * @see com.cognizant.opserv.sp.service.internal.CustomerInternalService#editCustomerDetails(com.cognizant.opserv.sp.model.Customer, com.cognizant.opserv.sp.model.auth.UserDetails)
	 */
	@Override
	@Transactional
	public void editCustomerDetails(Customer customer, UserDetails userDetails) throws CustomerServiceException{
		try{
			if(customer.getExtdAttributes()!=null || !customer.getExtdAttributes().isEmpty()){
				customerDAOService.editCustomerDetails(customer, userDetails);	
			}
		}catch(OpservDataAccessException e){
			throw new CustomerServiceException(CustomerServiceExceptionCode.CUST_SER_EX_CD_010,"exception while editing customer's extended attributes", null, e);
		}
	}

}
