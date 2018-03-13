package com.cognizant.opserv.sp.service.product;

import java.util.List;

import com.cognizant.opserv.sp.exception.ProductServiceException;
import com.cognizant.opserv.sp.model.Product;
import com.cognizant.opserv.sp.model.auth.UserDetails;

/**
 * ****************************************************************************.
 *
 * @class ProductService contains all the product services 
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 * ***************************************************************************
 */
public interface ProductService {
	
	/**
	 * Gets the product details.
	 *
	 * @method getProductDetails
	 * @param productId the product id
	 * @param userDetails the user details
	 * @return the product details
	 * @throws ProductServiceException the product service exception
	 */
	Product getProductDetails(Long productId,UserDetails userDetails) throws ProductServiceException;
	
	/**
	 * Gets the product details.
	 *
	 * @method getProductDetails
	 * @param productIds the product ids
	 * @param userDetails the user details
	 * @return the product details
	 * @throws ProductServiceException the product service exception
	 */
	List<Product> getProductDetails(List<Long> productIds,UserDetails userDetails) throws ProductServiceException;
	
	
	/**
	 * Creates the product.
	 *
	 * @param product the product
	 * @param userDetails the user details
	 * @return the product
	 * @throws ProductServiceException the product service exception
	 */
	Product createProduct(Product product,UserDetails userDetails) throws ProductServiceException;
    
    /**
     * Update product.
     *
     * @param product the product
     * @param userDetails the user details
     * @throws ProductServiceException the product service exception
     */
    void updateProduct(Product product,UserDetails userDetails) throws ProductServiceException;

	
}
