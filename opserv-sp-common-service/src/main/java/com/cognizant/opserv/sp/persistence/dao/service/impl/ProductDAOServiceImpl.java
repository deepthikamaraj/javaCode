package com.cognizant.opserv.sp.persistence.dao.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cognizant.opserv.sp.assembler.ProductModelAssembler;
import com.cognizant.opserv.sp.common.util.DateUtil;
import com.cognizant.opserv.sp.core.dao.TCustPrdSalesTeamDAO;
import com.cognizant.opserv.sp.core.dao.TPrdDAO;
import com.cognizant.opserv.sp.core.entity.TCustPrdSalesTeam;
import com.cognizant.opserv.sp.core.entity.TPrd;
import com.cognizant.opserv.sp.exception.ProductServiceException;
import com.cognizant.opserv.sp.model.Alignment;
import com.cognizant.opserv.sp.model.Product;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.persistence.dao.service.ProductDAOService;
import com.cognizant.peg.core.exception.OpservDataAccessException;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;

/**
 * ****************************************************************************.
 *
 * @class ProductDAOServiceImpl contains all the DAO product services
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 * ***************************************************************************
 *        
 */
@Component
public class ProductDAOServiceImpl implements ProductDAOService {

	@Autowired
	private TPrdDAO tPrdDAO;

	@Autowired
	private TCustPrdSalesTeamDAO tCustPrdSalesTeamDAO;
	
	@Autowired
	private ProductModelAssembler productModelAssembler;
	
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(ProductDAOServiceImpl.class);

	/**
	 * Gets the product details.
	 *
	 * @method getProductDetails
	 * @param productIds
	 *            the product ids
	 * @param userDetails
	 *            the user details
	 * @return the product details
	 * @throws ProductServiceException
	 *             the product service exception
	 */
	@Override
	public List<Product> getProductDetails(List<Long> productIds,
			UserDetails userDetails) throws OpservDataAccessException {
		List<Product> listProduct = new ArrayList<Product>();
		Product prod = new Product();
		try {
			List<TPrd> tprdList = tPrdDAO.findTPrdsByPrdId(productIds,
					userDetails.getTenantId());
			if (null != tprdList && !tprdList.isEmpty() ) {
				for (TPrd tprd : tprdList) {
					prod = productModelAssembler
							.convertTProdDtsToProdModel(tprd);
					listProduct.add(prod);
				}
			}
		} catch (RuntimeException re) {
			LOGGER.error("This issue is occurred while Fetching Product Dts By List of ProductIds.",re.getMessage());
			throw new OpservDataAccessException(
					"This issue is occurred while Fetching Product Dts By List of ProductIds.",
					re);
		}
		return listProduct;
	}
	/**
	 * Gets the prd fr cust st.
	 *
	 * @param customerId the customer id
	 * @param alignment the alignment
	 * @param userDetails the user details
	 * @return the prd fr cust st
	 */
	public List<Product> getPrdFrCustST(Long customerId, Alignment alignment,
			UserDetails userDetails) {
		List<TCustPrdSalesTeam> prdListByCustIdAlBuSt = tCustPrdSalesTeamDAO.getPrdListByCustIdAlBuSt(customerId.intValue(),
				alignment.getId(), alignment.getSalesTeam().getId(), alignment
						.getSalesTeam().getBusinessUnit().getId(), userDetails);
		
		List<Product> prdList = new ArrayList<Product>();
		if(prdListByCustIdAlBuSt != null && !prdListByCustIdAlBuSt.isEmpty()){
			
			for(TCustPrdSalesTeam tCustPrdSalesTeam : prdListByCustIdAlBuSt){
				Product prd = productModelAssembler.convertTProdDtsToProdModel(tCustPrdSalesTeam.getTPrd());
				prdList.add(prd);
			}
		}
		return prdList;
	}
	
	@Override
	public Product createNewProduct(Product product, UserDetails userDetails)
			throws OpservDataAccessException {
		try{
			TPrd tPrd = null;
			//new entry for Product with mapper
			TPrd mappedTPrd = productModelAssembler.mapProductModelToEntity(product, userDetails);
			tPrd = tPrdDAO.createTPrd(mappedTPrd);
			return productModelAssembler.convertTProdDtsToProdModel(tPrd);
		}catch(RuntimeException runtimeException){
			throw new OpservDataAccessException("Error during creating new Product",runtimeException);
		}
	}
	@Override
	public TPrd updateProductInfo(Product product, UserDetails userDetails)
			throws OpservDataAccessException {
		try{
			TPrd tPrd = null;
			if(null != product.getId()){
				tPrd = tPrdDAO.findTPrdById(product.getId());
			}
			//mapped product model to entity
			tPrd = productModelAssembler.convertProductModelToEntity(tPrd, product, userDetails);
			tPrd.setUpdateDt(DateUtil.getCurrentDate());
			if(userDetails.getUserId()!=null){
				tPrd.setUpdatedBy(userDetails.getUserId());				
			}
			return tPrdDAO.updateTPrd(tPrd);
		}catch(RuntimeException runtimeException){
			throw new OpservDataAccessException("Error during updating Product information",runtimeException);
		}
	}
}
