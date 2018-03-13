package com.cognizant.opserv.sp.service.product.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.opserv.sp.common.CommonConstants;
import com.cognizant.opserv.sp.exception.ProductServiceException;
import com.cognizant.opserv.sp.exception.ProductServiceException.ProductServiceExceptionExceptionCode;
import com.cognizant.opserv.sp.model.Product;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.persistence.dao.service.ProductDAOService;
import com.cognizant.opserv.sp.service.product.ProductService;
import com.cognizant.peg.core.exception.OpservDataAccessException;
/**
 * ****************************************************************************.
 *
 * @class ProductServiceimpl contains all the product services 
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 * ***************************************************************************
 */
@Service("productService")
@Transactional(rollbackFor = ProductServiceException.class)
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductDAOService productDAOService;

	/**
	 * Gets the product details.
	 *
	 * @method getProductDetails
	 * @param productId the product id
	 * @param userDetails the user details
	 * @return the product details
	 * @throws ProductServiceException the product service exception
	 * @method getProductDetails
	 */
	@Override
	@Cacheable(value=CommonConstants.PRODUCT_SERVICE_CACHE,key="{#productId,#userDetails.tenantId}")
	public Product getProductDetails(Long productId, UserDetails userDetails)
			throws ProductServiceException {
		List<Long> prodIds= new ArrayList<Long>();
		try{
			if(null!=productId && productId!=0 && null!=userDetails && null!=userDetails.getTenantId() && userDetails.getTenantId()!=0){ 
				prodIds.add(productId);
				List<Product> prodList = productDAOService.getProductDetails(prodIds, userDetails);
				if(null!=prodList && prodList.size()>0){
					return 	prodList.get(0);
				}
			}else{
					Object[] args = new Object[]{"productId"};
					throw new ProductServiceException(args);
			}
		}catch(OpservDataAccessException e) {
		       Object[] args = new Object[]{productId};
		       throw new ProductServiceException(ProductServiceExceptionExceptionCode.PROD_SER_EX_CD_001,"Fetch exception",args,e);
		}

		return null;
	}
	
	/**
	 * Gets the product details.
	 *
	 * @method getProductDetails
	 * @param productIds the product ids
	 * @param userDetails the user details
	 * @return the product details
	 * @throws ProductServiceException the product service exception
	 * @method getProductDetails
	 */
	@Override
	public List<Product> getProductDetails(List<Long> productIds,
			UserDetails userDetails) throws ProductServiceException {
		try{
			if(null!=productIds && productIds.size()>0 && null!=userDetails && null!=userDetails.getTenantId() && userDetails.getTenantId()!=0){ 
				return productDAOService.getProductDetails(productIds, userDetails);
			}else{
				Object[] args = new Object[]{"productIds"};
				throw new ProductServiceException("Invalid Input Parameters","Invalid Input Parameters");
			}
		}catch(OpservDataAccessException e) {
		       Object[] args = new Object[]{"productIds"};
		       throw new ProductServiceException(args);
		}
	}
	
	@Override
//	@Transactional
	public Product createProduct(Product product, UserDetails userDetails)
			throws ProductServiceException {
		
		try{
				if(product == null || product.getName() == null || product.getCode() == null || product.getCreatedBy() == null || product.getCreatedDate() == null ||
				userDetails == null || userDetails.getTenantId() == null ){
					String params = "Required field is missing";
					Object[] args = new Object[]{params};
					throw new  ProductServiceException(args);
				}
			return productDAOService.createNewProduct(product, userDetails);	
		}catch(OpservDataAccessException accessException){
			 Object[] args = new Object[]{"Product creation failed!!"};
			throw new ProductServiceException(ProductServiceExceptionExceptionCode.PROD_SER_EX_CD_002, "Creation_Exception", args, accessException);
		}
	}
	
	@Override
//	@Transactional
	public void updateProduct(Product product, UserDetails userDetails)throws ProductServiceException {
		try{
			if(product == null || product.getName() == null || product.getCode() == null || product.getCreatedBy() == null || product.getCreatedDate() == null ||
			userDetails == null || userDetails.getTenantId() == null ){
			String params = "Required field is missing";
			Object[] args = new Object[]{params};
			throw new  ProductServiceException(args);
				}
		 productDAOService.updateProductInfo(product, userDetails);	
		}catch(OpservDataAccessException accessException){
			 Object[] args = new Object[]{"Product updation failed!!"};
			throw new ProductServiceException(ProductServiceExceptionExceptionCode.PROD_SER_EX_CD_003, "updation_Exception", args, accessException);
		}
	}
	
}
