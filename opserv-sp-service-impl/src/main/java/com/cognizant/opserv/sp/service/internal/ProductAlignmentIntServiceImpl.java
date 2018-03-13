/**
 * 
 */
package com.cognizant.opserv.sp.service.internal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.opserv.sp.exception.AlignmentServiceException;
import com.cognizant.opserv.sp.exception.AlignmentServiceException.AlignmentServiceExceptionCode;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.persistence.dao.service.ProductAlignmentDAOService;
import com.cognizant.peg.core.exception.OpservDataAccessException;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;

/**
 * ****************************************************************************.
 *
 * @class ProductAlignmentIntService contains all the internal services for Product
 *        alignment
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 * ***************************************************************************
 */
@Service("productAlignmentIntService")
public class ProductAlignmentIntServiceImpl implements ProductAlignmentIntService{

	@Autowired
	ProductAlignmentDAOService productAlignmentDAOService;
	
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(ProductAlignmentIntServiceImpl.class);
	
	/**
	 * Gets the product count for sales position.
	 *
	 * @param spId the sp id
	 * @param userDetails the user details
	 * @return the product count for sales position
	 * @throws AlignmentServiceException 
	 */
	
	@Override
	public long getProductCountForSalesPosition(SalesPosition salesPosition,
			UserDetails userDetails) throws AlignmentServiceException {
		try{
			if(null != salesPosition
					&& null != salesPosition.getId()
					&& null != salesPosition.getHierarchy()
					&& null != salesPosition.getHierarchy().getId()
					&& salesPosition.getId() != 0
					&& salesPosition.getHierarchy().getId() != 0 
					&& null != userDetails
					&& null != userDetails.getTenantId()
					&& userDetails.getTenantId() != 0){
				
				return productAlignmentDAOService.getProductCountForSalesPosition(salesPosition, userDetails);
			}
			else{
				LOGGER.info("*****************INPUT PARAMETER SALESPOSITION IS NULL OR USER DETAILS IS NULL*****************");
				Object[] args = {"salesPosition is null or userDetails is null"};
				throw new AlignmentServiceException(args);
			}
		}catch(OpservDataAccessException opEx){
			LOGGER.info("*****************ERROR WHILE FETCHING PRODUCT COUNT FOR SALES POSITION*****************");
			Object[] args = new Object[1];
			args[0] = salesPosition.getId();
			throw new AlignmentServiceException(AlignmentServiceExceptionCode.ALGN_SER_EX_CD_249, "Error while fetching Product Count for Sales Position", args, opEx);
		}
	}

	/**
	 * Gets the product names for sales position.
	 *
	 * @param salesPosition the salesPosition
	 * @param userDetails the user details
	 * @return the product names for sales position
	 * @throws AlignmentServiceException 
	 */
	@Override
	public List<String> getProductNamesForSalesPosition(SalesPosition salesPosition,
			UserDetails userDetails) throws AlignmentServiceException {
		try{
			if(null != salesPosition
					&& null != salesPosition.getId()
					&& null != salesPosition.getHierarchy()
					&& null != salesPosition.getHierarchy().getId()
					&& salesPosition.getId() != 0
					&& salesPosition.getHierarchy().getId() != 0 
					&& null != userDetails
					&& null != userDetails.getTenantId()
					&& userDetails.getTenantId() != 0){
				
				return productAlignmentDAOService.getProductNamesForSalesPosition(salesPosition, userDetails);
			}
			else{
				LOGGER.info("*****************INPUT PARAMETER SALESPOSITION IS NULL OR USER DETAILS IS NULL*****************");
				Object[] args = {"salesPosition is null or userDetails is null"};
				throw new AlignmentServiceException(args);
			}
		}catch(OpservDataAccessException opEx){
			LOGGER.info("*****************ERROR WHILE FETCHING PRODUCT NAMES FOR SALES POSITION*****************");
			Object[] args = new Object[1];
			args[0] = salesPosition.getId();
			throw new AlignmentServiceException(AlignmentServiceExceptionCode.ALGN_SER_EX_CD_250, "Error while fetching Product Names for Sales Position", args, opEx);
		}
	}

}
