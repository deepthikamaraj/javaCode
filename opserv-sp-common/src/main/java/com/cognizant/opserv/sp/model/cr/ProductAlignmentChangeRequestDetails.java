package com.cognizant.opserv.sp.model.cr;

import java.io.Serializable;

import com.cognizant.opserv.sp.model.ProductAlignment;
/**
 * ****************************************************************************.
 *
 * @class ProductAlignmentChangeRequestDetails
 * @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * @since 11/05/2016
 * ***************************************************************************
 */
public class ProductAlignmentChangeRequestDetails 
											extends ChangeRequestLineItem 
											implements Serializable 
{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 6371309524342112174L;
	/**
	 * 
	 */
	private ProductAlignment oldProductAlignment;
	/**
	 * 
	 */
	private ProductAlignment newProductAlignment;
	
	/**
	 * @param parentChangeRequest
	 */
	ProductAlignmentChangeRequestDetails(ChangeRequest parentChangeRequest)
	{
		super.setParentChangeRequest(parentChangeRequest);
	}
	
	/**
	 * @return the oldProductAlignment
	 */
	public ProductAlignment getOldProductAlignment() {
		return oldProductAlignment;
	}
	/**
	 * @param oldProductAlignment the oldProductAlignment to set
	 */
	public void setOldProductAlignment(ProductAlignment oldProductAlignment) {
		this.oldProductAlignment = oldProductAlignment;
	}
	/**
	 * @return the newProductAlignment
	 */
	public ProductAlignment getNewProductAlignment() {
		return newProductAlignment;
	}
	/**
	 * @param newProductAlignment the newProductAlignment to set
	 */
	public void setNewProductAlignment(ProductAlignment newProductAlignment) {
		this.newProductAlignment = newProductAlignment;
	}
}
