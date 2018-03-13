package com.cognizant.opserv.sp.service.internal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.opserv.sp.exception.AlignmentServiceException;
import com.cognizant.opserv.sp.exception.AlignmentServiceException.AlignmentServiceExceptionCode;
import com.cognizant.opserv.sp.model.CustomerAlignment;
import com.cognizant.opserv.sp.model.CustomerProductAlignment;
import com.cognizant.opserv.sp.model.ProductAlignment;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.persistence.dao.service.CustomerProductAlignmentDAOService;
import com.cognizant.peg.core.exception.OpservDataAccessException;

@Service
public class CustomerProductAlignmentIntServiceImpl implements CustomerProductAlignmentIntService{

	/** The customerproduct alignment dao service. */
	@Autowired
	private CustomerProductAlignmentDAOService customerproductAlignmentDAOService;
	
	/**
	 * Update cust prod algnmt.
	 *
	 * @param customerAlignment the customer alignment
	 * @param userDetails the user details
	 * @return the list
	 * @throws AlignmentServiceException the alignment service exception
	 */
	@Override
	public List<CustomerProductAlignment> updateCustProdAlgnmt(
			CustomerAlignment customerAlignment, UserDetails userDetails)
			throws AlignmentServiceException {
		try{
			return customerproductAlignmentDAOService.updateCustProdAlgnmt(customerAlignment, userDetails);
			}catch(OpservDataAccessException e) {
			       Object[] args = new Object[]{customerAlignment.getId()};
			       throw new AlignmentServiceException(AlignmentServiceExceptionCode.ALGN_SER_EX_CD_223,"Update Customer Product Alignment Exception",args,e);
			}
	}


	//rajesh services

	/**
	 * update customer product alignmnets allocation perc.
	 *
	 * @param custPrdAlignList the customer product alignment list
	 * @param userDetails the userdetails
	 * @return the list : the customer product alignment list
	 * @throws AlignmentServiceException the alignment service exception
	 */
	@Override
	@Transactional
	public List<CustomerProductAlignment> updateAllocPerc(List<CustomerProductAlignment> custPrdAlignList,
			UserDetails userDetails) throws AlignmentServiceException {
		// TODO Auto-generated method stub
		try{
			if(custPrdAlignList!=null && !custPrdAlignList.isEmpty()){
				return customerproductAlignmentDAOService.updateCustProdAlgnmtAllocPerc(custPrdAlignList, userDetails);
			}
			else{
				Object[] args = new Object[]{"CustomerAlgmntId","PrdAlgmntId","AllocationPercentage"};
				throw new AlignmentServiceException(args);	
			}
		}catch(OpservDataAccessException e) {
			Object[] args = new Object[]{custPrdAlignList};
			throw new AlignmentServiceException(AlignmentServiceExceptionCode.ALGN_SER_EX_CD_223,"Update Customer Product Alignment Exception",args,e);
		}
	}

	/**
	 * insert customer product alignmnets 
	 *
	 * @param custPrdAlignList the customer product alignment list
	 * @param userDetails the userdetails
	 * @return the list : the customer product alignment list
	 * @throws AlignmentServiceException the alignment service exception
	 */
	@Override
	@Transactional
	public List<CustomerProductAlignment> insertCustomerProductAlignment(
			List<CustomerProductAlignment> customerProdAligmntList,
			UserDetails userDetails) throws AlignmentServiceException {
		// TODO Auto-generated method stub
		try{
			if(customerProdAligmntList!=null && !customerProdAligmntList.isEmpty()){
				return customerproductAlignmentDAOService.insertCustomerProductAlignmnets(customerProdAligmntList);
			}
			else{
				Object[] args = new Object[]{"CustomerAlgmntId","PrdAlgmntId","AllocationPercentage"};
				throw new AlignmentServiceException(args);	
			}
		}catch(OpservDataAccessException e) {
			Object[] args = new Object[]{customerProdAligmntList};
			throw new AlignmentServiceException(AlignmentServiceExceptionCode.ALGN_SER_EX_CD_222,"Save Customer Product Alignment Exception",args,e);
		}	
	}	

	
}




