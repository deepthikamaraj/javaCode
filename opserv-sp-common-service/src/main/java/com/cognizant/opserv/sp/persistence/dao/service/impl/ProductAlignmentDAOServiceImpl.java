package com.cognizant.opserv.sp.persistence.dao.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cognizant.opserv.sp.assembler.ProductAlignmentEntityAssembler;
import com.cognizant.opserv.sp.assembler.ProductAlignmentModelAssembler;
import com.cognizant.opserv.sp.common.util.DateUtil;
import com.cognizant.opserv.sp.core.dao.TPrdAlgmntDAO;
import com.cognizant.opserv.sp.core.entity.TPrdAlgmnt;
import com.cognizant.opserv.sp.exception.AlignmentServiceException;
import com.cognizant.opserv.sp.model.ProductAlignment;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.persistence.dao.service.ProductAlignmentDAOService;
import com.cognizant.peg.core.exception.OpservDataAccessException;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
/**
 * ****************************************************************************.
 *
 * @class ProductAlignmentServiceImpl contains all the services for product  alignment
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 * ***************************************************************************
 */
@Component
public class ProductAlignmentDAOServiceImpl implements ProductAlignmentDAOService {
	
	@Autowired
	private TPrdAlgmntDAO tPrdAlgmntDAO;
	
	@Autowired
	private ProductAlignmentModelAssembler productAlignmentModelAssembler;
	
	@Autowired
	private ProductAlignmentEntityAssembler productAlignmentEntityAssembler;
	
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(ProductAlignmentDAOServiceImpl.class);
	
	/**
	 *  @Method getAllProductAlignmentsBySalesPosition - This method is to fetch the
	 *         Products Alignment dts By Sales Position
	 * @param salesPos
	 * @param userDetails
	 * @return List<ProductAlignment> - list of the ProductAlignment Details
	 * @throws AlignmentServiceException
	 */
	@Override
	public List<ProductAlignment> getAllProductAlignmentsBySalesPosition(
			SalesPosition salesPos, UserDetails userDetails)
			throws OpservDataAccessException {
		
		List<ProductAlignment> prodAlgModelList = new ArrayList<ProductAlignment>();
		try{
		List<TPrdAlgmnt> tPrdAlgmntList = new ArrayList<TPrdAlgmnt>();
		//tPrdAlgmntList = tPrdAlgmntDAO.findProductsBySalePosId(salesPos.getId(), 7l, 1l, 1l, 0, -1, userDetails.getTenantId());
		tPrdAlgmntList = tPrdAlgmntDAO.findProductsBySalePosId(salesPos.getId(), salesPos.getAlignmment().getId(), salesPos.getAlignmment().getSalesTeam().getBusinessUnit().getId(), salesPos.getAlignmment().getSalesTeam().getId(), 0, -1, userDetails.getTenantId());
		if(null!= tPrdAlgmntList && !tPrdAlgmntList.isEmpty()){
			for(TPrdAlgmnt tprdAlgmnt:tPrdAlgmntList){
				prodAlgModelList.add(productAlignmentModelAssembler.convertTProdAlgDtsToProdAlg(tprdAlgmnt));
			}
		}
		}catch (RuntimeException re) {
			LOGGER.error("This issue is occurred while Fetching All Product Alignment Dts By Sales Postion Fails.",re.getMessage());
			throw new OpservDataAccessException("This issue is occurred while Fetching All Product Alignment Dts By Sales Postion Fails.",re);
		} 
		return prodAlgModelList;
	}

	/**
	 * Save product alignment change request.
	 *
	 * @param productAlignment the product alignment
	 * @throws OpservDataAccessException the opserv data access exception
	 * @method saveProductAlignment
	 */
	@Override
	public void saveProductAlignmentChangeRequest(ProductAlignment productAlignment) throws OpservDataAccessException {
		try{
			TPrdAlgmnt tPrdAlgmnt = productAlignmentEntityAssembler.convertProdAlgmntModeltoEntity(productAlignment);
			tPrdAlgmntDAO.createTPrdAlgmnt(tPrdAlgmnt);
		}catch (RuntimeException re) {
			LOGGER.error("Exception occurred while creating ProductAlignmentChangeRequest",re.getMessage());
			throw new OpservDataAccessException("Exception occurred while creating ProductAlignmentChangeRequest",re);
		} 
	}

	/**
	 * Update product alignment change request.
	 *
	 * @param productAlignment the product alignment
	 * @throws OpservDataAccessException the opserv data access exception
	 * @method updateProductAlignment
	 */
	@Override
	public void updateProductAlignmentChangeRequest(ProductAlignment productAlignment) throws OpservDataAccessException {
		try{
			TPrdAlgmnt tPrdAlgmnt = productAlignmentEntityAssembler.convertProdAlgmntModeltoEntity(productAlignment);
			tPrdAlgmntDAO.updateTPrdAlgmnt(tPrdAlgmnt);
		}
		catch (RuntimeException re) {
			LOGGER.error("Exception occurred while updating ProductAlignmentChangeRequest",re.getMessage());
			throw new OpservDataAccessException("Exception occurred while updating ProductAlignmentChangeRequest",re);
		} 
	}

	/**
	 * Gets the prd al list for sp.
	 *
	 * @param salesPosition the sales position
	 * @param userDetails the user details
	 * @param prdIdList the prd id list
	 * @return the prd al list for sp
	 */
	@Override
	public List<ProductAlignment> getPrdAlListForSP(SalesPosition salesPosition,UserDetails userDetails, List<Long> prdIdList) throws OpservDataAccessException{
		List<ProductAlignment> prdAlgmntList = new ArrayList<ProductAlignment>();
	try{
		List<TPrdAlgmnt> tPrdAlgmntList=tPrdAlgmntDAO.getPrdAlListForSP(salesPosition,userDetails.getTenantId(),prdIdList );
		if(tPrdAlgmntList != null && !tPrdAlgmntList.isEmpty()){
			for(TPrdAlgmnt prdAlgmnt : tPrdAlgmntList){
			ProductAlignment prdAlgnmt= productAlignmentModelAssembler.convertTProdAlgDtsToProdAlg(prdAlgmnt);
			prdAlgmntList.add(prdAlgnmt);
			}
		}
		return prdAlgmntList;
	}catch (RuntimeException re) {
		LOGGER.error("Exception occurred while fetching ProductAlignments ",re.getMessage());
		throw new OpservDataAccessException("Exception occurred while fetching ProductAlignments ",re);
	} 
		
	}

	/**
	 * Gets the product count for sales position.
	 *
	 * @param spId the sp id
	 * @param userDetails the user details
	 * @return the product count for sales position
	 */
	@Override
	public long getProductCountForSalesPosition(SalesPosition salesPosition,
			UserDetails userDetails) throws OpservDataAccessException{
		
		try{
			long spId = salesPosition.getId();
			long shId = salesPosition.getHierarchy().getId();
		
			long prdCount = tPrdAlgmntDAO.fetchCountOfPrdForSP(spId, shId,userDetails.getTenantId(), DateUtil.getCurrentDate());
			
			return prdCount;
		}catch (RuntimeException re) {
			LOGGER.error("Exception occurred while getting product count for SP ",re.getMessage());
			throw new OpservDataAccessException("Exception occurred while getting product count for SP ",re);
		} 
	}

	
	@Override
	public List<String> getProductNamesForSalesPosition(
			SalesPosition salesPosition, UserDetails userDetails) throws OpservDataAccessException {
		List<String> prdNames = new ArrayList<String>();
		try{
			long spId = salesPosition.getId();
			long shId = salesPosition.getHierarchy().getId();
			Date currentDate = DateUtil.getCurrentDate();
			
			prdNames = tPrdAlgmntDAO.getProductNamesForSalesPosition(spId, shId,currentDate, userDetails.getTenantId());
			return prdNames;
			
		}
		catch(RuntimeException ex){
			LOGGER.error("*****************ERROR WHILE FETCHING PRODUCT NAMES FOR SALES POSITION*****************");
			throw new OpservDataAccessException("Error while fetching Product names for Sales Position", ex);
		}
	

	}

	@Override
	public Long getProductCountForSalesPosition(
			List<SalesPosition> salesPositions, UserDetails userDetails) throws OpservDataAccessException {
		try{
		List<Long> spIdList = new ArrayList<Long>();
		for (SalesPosition salesPosition : salesPositions) {
			if (null != salesPosition.getId()
					&& salesPosition.getId() != 0) {
				spIdList.add(salesPosition.getId());
			}
		}
		return tPrdAlgmntDAO.fetchCountOfPrdForSalesPositions(spIdList, userDetails.getTenantId());
		}catch(RuntimeException ex){
			LOGGER.error("*****************ERROR WHILE FETCHING PRODUCT COUNT FOR SALES POSITIONS*****************");
			throw new OpservDataAccessException("Error while fetching product count for sales positions", ex);
		}

	}

	@Override
	public List<String> getProductNamesForSalesPosition(
			List<SalesPosition> salesPositions, UserDetails userDetails) throws OpservDataAccessException {
		try{
				List<Long> spIdList = new ArrayList<Long>();
				for (SalesPosition salesPosition : salesPositions) {
					if (null != salesPosition.getId()
							&& salesPosition.getId() != 0) {
						spIdList.add(salesPosition.getId());
					}
				}
				return tPrdAlgmntDAO.getProductNamesForSalesPositions(spIdList, userDetails.getTenantId());
				}catch(RuntimeException ex){
					LOGGER.error("*****************ERROR WHILE FETCHING PRODUCT NAMES FOR SALES POSITIONS*****************");
					throw new OpservDataAccessException("Error while fetching product names for sales positions", ex);
				}
		}
	
}

