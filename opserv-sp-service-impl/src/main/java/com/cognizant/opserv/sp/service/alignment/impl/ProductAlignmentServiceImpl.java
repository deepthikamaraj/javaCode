package com.cognizant.opserv.sp.service.alignment.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.opserv.sp.exception.AlignmentServiceException;
import com.cognizant.opserv.sp.exception.AlignmentServiceException.AlignmentServiceExceptionCode;
import com.cognizant.opserv.sp.model.ProductAlignment;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.persistence.dao.service.ProductAlignmentDAOService;
import com.cognizant.opserv.sp.service.alignment.ProductAlignmentService;
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
@Service("productAlignmentService")
@Transactional(rollbackFor = AlignmentServiceException.class)
public class ProductAlignmentServiceImpl implements ProductAlignmentService {
	
	
	/** The product alignment dao service. */
	@Autowired
	private ProductAlignmentDAOService productAlignmentDAOService;

	
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
	public List<ProductAlignment> getAllProductAlignmentsBySalesPosition(
			SalesPosition salesPos, UserDetails userDetails)
			throws AlignmentServiceException {
			try{
					if (null != salesPos
							&& null != salesPos.getId()
							&& null != salesPos.getAlignmment()
							&& null != salesPos.getAlignmment().getId()
							&& null != salesPos.getAlignmment().getSalesTeam()
							&& null != salesPos.getAlignmment().getSalesTeam().getId()
							&& null != salesPos.getAlignmment().getSalesTeam()
									.getBusinessUnit()
							&& null != salesPos.getAlignmment().getSalesTeam()
									.getBusinessUnit().getId()
							&& salesPos.getAlignmment().getId() != 0
							&& salesPos.getAlignmment().getSalesTeam()
									.getBusinessUnit().getId() != 0
							&& salesPos.getAlignmment().getSalesTeam().getId() != 0
							&& salesPos.getId() != 0 && null != userDetails
							&& null != userDetails.getTenantId()
							&& userDetails.getTenantId() != 0) {
						return productAlignmentDAOService
								.getAllProductAlignmentsBySalesPosition(salesPos,
										userDetails);
					} else {
						Object[] args = new Object[]{"SpId","AlgId","BuId","StId"};
						throw new AlignmentServiceException(args);
						
					}
			}catch(OpservDataAccessException e) {
			       Object[] args = new Object[]{salesPos.getId()};
			       throw new AlignmentServiceException(AlignmentServiceExceptionCode.ALGN_SER_EX_CD_001,"Fetch exception",args,e);
			}


	}


	/**
	 * 
	 * @method saveProductAlignment
	 * @param productAlignment
	 * @throws AlignmentServiceException
	 */
	@Override
	public void saveProductAlignmentChangeRequest(ProductAlignment productAlignment) throws AlignmentServiceException {
		
		try{
			 productAlignmentDAOService.saveProductAlignmentChangeRequest(productAlignment);
		}catch(OpservDataAccessException e) {
		       Object[] args = new Object[]{productAlignment.getId()};
		       throw new AlignmentServiceException(AlignmentServiceExceptionCode.ALGN_SER_EX_CD_215,"Exception while creating product alignment",args,e);
		}
		
	}


	/**
	 * 
	 * @method updateProductAlignment
	 * @param productAlignment
	 * @throws AlignmentServiceException
	 */
	@Override
	public void updateProductAlignmentChangeRequest(ProductAlignment productAlignment) throws AlignmentServiceException {
		
		try{
			 productAlignmentDAOService.updateProductAlignmentChangeRequest(productAlignment);
		}catch(OpservDataAccessException e) {
		       Object[] args = new Object[]{productAlignment.getId()};
		       throw new AlignmentServiceException(AlignmentServiceExceptionCode.ALGN_SER_EX_CD_216,"Exception while updating product alignment",args,e);
		}
		
	}

}
