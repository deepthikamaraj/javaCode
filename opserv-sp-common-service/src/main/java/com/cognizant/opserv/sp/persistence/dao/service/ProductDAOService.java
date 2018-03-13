package com.cognizant.opserv.sp.persistence.dao.service;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TPrd;
import com.cognizant.opserv.sp.exception.ProductServiceException;
import com.cognizant.opserv.sp.model.Alignment;
import com.cognizant.opserv.sp.model.Product;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.peg.core.exception.OpservDataAccessException;


/**
 * ****************************************************************************.
 *
 * @class ProductDAOService contains all the DAO product services 
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 * ***************************************************************************
 */
public interface ProductDAOService {

	
	
	/**
	 * Gets the product details.
	 *
	 * @method getProductDetails
	 * @param productIds the product ids
	 * @param userDetails the user details
	 * @return the product details
	 * @throws ProductServiceException the product service exception
	 */
	List<Product> getProductDetails(List<Long> productIds,UserDetails userDetails) throws OpservDataAccessException;
	
	/**
	 * Gets the prd fr cust st.
	 *
	 * @param customerId the customer id
	 * @param alignment the alignment
	 * @param userDetails the user details
	 * @return the prd fr cust st
	 */
	List<Product> getPrdFrCustST(Long customerId, Alignment alignment, UserDetails userDetails);
	
	/**
	 * Creates the new product.
	 *
	 * @param product the product
	 * @param userDetails the user details
	 * @return the product
	 * @throws OpservDataAccessException the opserv data access exception
	 */
	Product createNewProduct(Product product, UserDetails userDetails) throws OpservDataAccessException;	
	
	/**
	 * Update product info.
	 *
	 * @param product the product
	 * @param userDetails the user details
	 * @return the t prd
	 * @throws OpservDataAccessException the opserv data access exception
	 */
	TPrd updateProductInfo(Product product,UserDetails userDetails) throws OpservDataAccessException;
}

